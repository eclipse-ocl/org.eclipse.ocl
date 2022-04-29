/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.asm5.ASM5JavaAnnotationReader;
import org.eclipse.ocl.examples.codegen.library.AbstractNativeProperty;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.library.NativeOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.Invocations;
import org.eclipse.ocl.pivot.utilities.Invocations.UnresolvedInvocations;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * JavaLanguageSupport defines the upport for invocation of native Java facilities from OCL
 * .
 * @since 1.18
 */
public class JavaLanguageSupport extends LanguageSupport
{
	public static class Factory implements LanguageSupport.Factory
	{
		@Override
		public @NonNull JavaLanguageSupport createLanguageSupport(@NonNull EnvironmentFactory environmentFactory) {
			return new JavaLanguageSupport(environmentFactory, getName());
		}

		@Override
		public @NonNull String getName() {
			return "java";
		}

		@Override
		public void install() {
			addLanguageSupport(this);
		}
	}

	public static final LanguageSupport.@NonNull Factory FACTORY = new Factory();

	public static class JavaNativeOperation extends AbstractOperation implements NativeOperation
	{
		protected final @NonNull Method method;

		public JavaNativeOperation(@NonNull Method method) {
			this.method = method;
		}

		@Override
		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
			return evaluate(executor, caller.getTypeId(), sourceAndArgumentValues);
		}

		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object @NonNull [] sourceAndArgumentValues) {
			IdResolver idResolver = executor.getIdResolver();
			Object source = idResolver.unboxedValueOf(sourceAndArgumentValues[0]);
			if (Modifier.isStatic(method.getModifiers())) {
				assert source == null;
			}
			else {
				if (source == null) {
					throw new InvalidValueException("Null source for " + method);
				}
			}
			int iMax = sourceAndArgumentValues.length-1;
			Object[] arguments = new Object[iMax];
			Class<?>[] jParameterTypes = method.getParameterTypes();
			for (int i = 0; i < iMax; i++) {
				Object unboxedValue = idResolver.unboxedValueOf(sourceAndArgumentValues[i+1]);
				if (jParameterTypes[i].getComponentType() != null) {
					List<?> list = (List<?>)unboxedValue;
					assert list != null;
					unboxedValue = list.toArray(new Object[list.size()]);
				}
				arguments[i] = unboxedValue;
			}
			try {
				Object result = method.invoke(source, arguments);
				return idResolver.boxedValueOf(result);
			} catch (InvocationTargetException e) {
				Throwable cause = e.getCause();
				assert cause != null;
				throw new InvalidValueException(cause);
			} catch (IllegalAccessException | IllegalArgumentException e) {
				throw new InvalidValueException(e);
			}
		}

		public @NonNull Method getMethod() {
			return method;
		}
	}

	public static class JavaNativeProperty extends AbstractNativeProperty
	{
		protected final @NonNull Field field;

		public JavaNativeProperty(@NonNull Field field) {
			this.field = field;
		}

	//	@Override
		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
			return evaluate(executor, caller.getTypeId(), sourceAndArgumentValues);
		}

		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object @NonNull [] sourceAndArgumentValues) {
			IdResolver idResolver = executor.getIdResolver();
			Object source = idResolver.unboxedValueOf(sourceAndArgumentValues[0]);
			if (Modifier.isStatic(field.getModifiers())) {
				assert source == null;
			}
			else {
				if (source == null) {
					throw new InvalidValueException("Null source for " + field);
				}
			}
			try {
				Object result = field.get(source);
				return idResolver.boxedValueOf(result);
			} catch (IllegalAccessException | IllegalArgumentException e) {
				throw new InvalidValueException(e);
			}
		}

		public @NonNull Field getField() {
			return field;
		}
	}

	protected @NonNull EnvironmentFactory environmentFactory;
	protected @NonNull String languageName;
	protected @NonNull StandardLibrary standardLibrary;
	private @Nullable Model nativeModel = null;
	private /*@LazyNonNull*/ ASM5JavaAnnotationReader annotationReader = null;

	public JavaLanguageSupport(@NonNull EnvironmentFactory environmentFactory, @NonNull String languageName) {
		this.environmentFactory = environmentFactory;
		this.languageName = languageName;
		this.standardLibrary = environmentFactory.getStandardLibrary();
	}

	private @NonNull Type getBoxedType(@NonNull Class<?> jClass) {
		Class<?> jComponentClass = jClass.getComponentType();
		if (jComponentClass != null) {
			Type elementType = getBoxedType(jComponentClass);
			return environmentFactory.getCompleteEnvironment().getSequenceType(elementType, false, null, null);
		}
		if (Map.class.isAssignableFrom(jClass)) {
			Type keyType = standardLibrary.getOclAnyType();
			Type valueType = standardLibrary.getOclAnyType();
			return environmentFactory.getCompleteEnvironment().getMapType(standardLibrary.getMapType(), keyType, false, valueType, false);
		}
		// ?? Bag // OrderedSet
		if (Set.class.isAssignableFrom(jClass)) {
			Type elementType = standardLibrary.getOclAnyType();
			return environmentFactory.getCompleteEnvironment().getSetType(elementType, false, null, null);
		}
		if (Iterable.class.isAssignableFrom(jClass)) {
			Type elementType = standardLibrary.getOclAnyType();
			return environmentFactory.getCompleteEnvironment().getSequenceType(elementType, false, null, null);
		}
		org.eclipse.ocl.pivot.Class behavioralClass = PivotUtil.getBehavioralClass(standardLibrary, jClass);
		if (behavioralClass != null) {
			return behavioralClass;
		}
		// track EObject inheritance
		return standardLibrary.getOclAnyType(); //getNativeClass(jClass);
	}

	private @Nullable Boolean getIsNonNull(@NonNull Field field) {
		if (annotationReader == null) {
			annotationReader = new ASM5JavaAnnotationReader();
		}
		return Boolean.TRUE;		// FIXME annotationReader.getIsNonNull(field);
	}

	private @Nullable Boolean getIsNonNull(@NonNull Method method, int parameter) {
		if (annotationReader == null) {
			annotationReader = new ASM5JavaAnnotationReader();
		}
		return annotationReader.getIsNonNull(method, parameter);
	}

	/*
	 * Return a native class for jClass flattening nested classes.
	 */
	public org.eclipse.ocl.pivot.@NonNull Class getNativeClass(/*@NonNull */Class<?> jClass) {
		assert jClass != null;
		Package jPackage = jClass.getPackage();
		if (jPackage == null) {
			jPackage = jClass.getComponentType().getPackage();
		}
		assert jPackage != null;
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getNativePackage(jPackage);
		String verboseName = jClass.getName();
		int iStart = 0;
		for (int iDot; (iDot = verboseName.indexOf('.', iStart)) >= 0; ) {
			iStart = iDot+1;
		}
		String trimmedName = verboseName.substring(iStart);
		List<org.eclipse.ocl.pivot.Class> asClasses = asPackage.getOwnedClasses();
		org.eclipse.ocl.pivot.Class asClass = NameUtil.getNameable(asClasses, trimmedName);
		if (asClass == null) {
			asClass = PivotFactory.eINSTANCE.createClass();
			asClass.setName(trimmedName);
			asClasses.add(asClass);
		}
		return asClass;
	}

	@NonNull
	public Model getNativeModel() {
		Model asModel = nativeModel;
		if (asModel == null) {
			asModel = PivotFactory.eINSTANCE.createModel();
			asModel.setName("native-java");
			nativeModel = asModel;
			ResourceSet nativeResourceSet = new ResourceSetImpl();
			Resource nativeResourcet = nativeResourceSet.createResource(URI.createURI("native-java.xml"));
			nativeResourcet.getContents().add(asModel);
		}
		return asModel;
	}

	/*
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method method) { //; , @NonNull OperationCallingConvention zcallingConvention) {
		Class<?> jClass = method.getDeclaringClass();
		assert jClass != null;
		org.eclipse.ocl.pivot.Class asClass = getNativeClass(jClass);
		String verboseName = method.toGenericString();
		int iOpen = verboseName.indexOf('(');
		int iClose = verboseName.indexOf(')', iOpen);
		int iStart = verboseName.lastIndexOf('.', iOpen);
		if (iStart < 0) {
			iStart = verboseName.lastIndexOf(' ', iOpen);
		}
		String trimmedName = verboseName.substring(iStart+1, iOpen) + "::" + verboseName.substring(iOpen+1, iClose);
		List<Operation> asOperations = asClass.getOwnedOperations();
		Operation asOperation = NameUtil.getNameable(asOperations, trimmedName);
		if (asOperation == null) {
			Class<?> jReturnClass = method.getReturnType();
			Type asReturnType = jReturnClass != null ? getBoxedType(jReturnClass) : null;
			boolean isRequired = getIsNonNull(method, -1) == Boolean.TRUE;
			asOperation = PivotFactory.eINSTANCE.createOperation();
			asOperation.setName(trimmedName);
			asOperation.setType(asReturnType);
			asOperation.setIsRequired(isRequired);
			asOperation.setIsStatic(Modifier.isStatic(method.getModifiers()));
			asOperation.setImplementation(new JavaNativeOperation(method));
			asOperation.setImplementationClass(jClass.getCanonicalName());
			asOperations.add(asOperation);
			List<org.eclipse.ocl.pivot.Parameter> asParameters = asOperation.getOwnedParameters();
			for (Parameter jParameter : method.getParameters()) {
				Class<?> jParameterClass = jParameter.getType();
				assert jParameterClass != null;
				Type asParameterType = getBoxedType(jParameterClass);
				isRequired = getIsNonNull(method, asParameters.size()) == Boolean.TRUE;
				org.eclipse.ocl.pivot.Parameter asParameter = PivotFactory.eINSTANCE.createParameter();
				asParameter.setName(jParameter.getName());
				asParameter.setType(asParameterType);		// isRequired
				asParameter.setIsRequired(isRequired);
				asParameters.add(asParameter);
			}
		}
		return asOperation;
	}

	/*
	 * Return a native property for method flattening the signature into the name.
	 */
	public @NonNull Property getNativeProperty(@NonNull Field field) { //; , @NonNull PropertyCallingConvention zcallingConvention) {
		Class<?> jClass = field.getDeclaringClass();
		assert jClass != null;
		org.eclipse.ocl.pivot.Class asClass = getNativeClass(jClass);
		String trimmedName = field.getName();
		List<Property> asProperties = asClass.getOwnedProperties();
		Property asProperty = NameUtil.getNameable(asProperties, trimmedName);
		if (asProperty == null) {
			Class<?> jReturnClass = field.getType();
			Type asReturnType = jReturnClass != null ? getBoxedType(jReturnClass) : null;
			boolean isRequired = getIsNonNull(field) == Boolean.TRUE;
			asProperty = PivotFactory.eINSTANCE.createProperty();
			asProperty.setName(trimmedName);
			asProperty.setType(asReturnType);
			asProperty.setIsRequired(isRequired);
			asProperty.setIsStatic(Modifier.isStatic(field.getModifiers()));
			asProperty.setImplementation(new JavaNativeProperty(field));
			asProperty.setImplementationClass(jClass.getCanonicalName());
			asProperties.add(asProperty);
		}
		return asProperty;
	}

	/*
	 * Return a native package for jPackage flattening nested packages.
	 */
	private org.eclipse.ocl.pivot.@NonNull Package getNativePackage(@NonNull Package jPackage) {
		Model asModel = getNativeModel();
		String qualifiedName = jPackage.getName();
		List<org.eclipse.ocl.pivot.Package> asPackages = asModel.getOwnedPackages();
		org.eclipse.ocl.pivot.Package asPackage = NameUtil.getNameable(asPackages, qualifiedName);
		if (asPackage == null) {
			asPackage = PivotFactory.eINSTANCE.createPackage();
			asPackage.setName(qualifiedName);
			asPackages.add(asPackage);
		}
		return asPackage;
	}

	// Java classes are distinct singletons so we can have a distinct orphanage
/*	private @NonNull SequenceType getNativeSequence(@NonNull Class<?> jParameterClass) {
		Orphanage nativeOrphanage2 = nativeOrphanage;
		if (nativeOrphanage2 == null) {
			nativeOrphanage = nativeOrphanage2 = new Orphanage(PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI);
		}
		Class<?> jParameterComponentClass = jParameterClass.getComponentType();
		Type asParameterType = getBoxedType(jParameterComponentClass);
		for (Type type : nativeOrphanage2.getOwnedClasses()) {
			SequenceType sequenceType = (SequenceType)type;
			if (sequenceType.getElementType() == asParameterType) {
				return sequenceType;
			}
		}
		SequenceType sequenceType = PivotFactory.eINSTANCE.createSequenceType();
		sequenceType.setElementType(asParameterType);
		sequenceType.setName(jParameterClass.getName());
	//	sequenceType.setLower(unspecializedType.getLower());
	//	sequenceType.setUpper(unspecializedType.getUpper());
	//	sequenceType.setUnspecializedElement(asParameterType);
		sequenceType.setOwningPackage(nativeOrphanage2);

//		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
//		List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		TemplateParameter formalParameter = PivotFactory.eINSTANCE.createTemplateParameter();	// FIXME share
		assert formalParameter != null;
		TemplateParameterSubstitution templateParameterSubstitution = CompleteInheritanceImpl.createTemplateParameterSubstitution(formalParameter, asParameterType);
		templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
		sequenceType.getOwnedBindings().add(templateBinding);



		return sequenceType;
	} */

	@Override
	public @Nullable Invocations resolveInvocations(@NonNull Type requiredSourceType, boolean hasExplicitSourceExp, @NonNull String qualifiedOperationName) {
		int lastDot = qualifiedOperationName.lastIndexOf(".");
		if (lastDot >= 0) {
			String className = qualifiedOperationName.substring(0, lastDot);
			String methodName = qualifiedOperationName.substring(lastDot+1);
			try {
				List<@NonNull NamedElement> invocations = null;
				boolean hasNonStaticMethodNameMatches = false;
				boolean hasStaticMethodNameMatches = false;
				Class<?> loadedClass = Thread.currentThread().getContextClassLoader().loadClass(className);
				for (Method method : loadedClass.getDeclaredMethods()) {
					if (methodName.equals(method.getName())) {
						Operation nativeOperation = null;
						if (Modifier.isStatic(method.getModifiers())) {
							hasStaticMethodNameMatches = true;
							if (!hasExplicitSourceExp) {
								nativeOperation = getNativeOperation(method);
							}
						}
						else {
							hasNonStaticMethodNameMatches = true;
							Class<?> declaringClass = method.getDeclaringClass();
							assert declaringClass != null;
							Type candidateSourceType = getBoxedType(declaringClass);
							if (requiredSourceType.conformsTo(standardLibrary, candidateSourceType)) {
								nativeOperation = getNativeOperation(method);
							}
						}
						if (nativeOperation != null) {
							if (invocations == null) {
								invocations = new ArrayList<>();
							}
							invocations.add(nativeOperation);
						}
					}
				}
				if (invocations == null) {
					if (hasNonStaticMethodNameMatches) {
						return new Invocations.UnresolveableInvocations(PivotMessages.NoCompatibleLanguageSupport, languageName, qualifiedOperationName, requiredSourceType);
					}
					else if (hasStaticMethodNameMatches) {
						return new Invocations.UnresolveableInvocations(PivotMessages.NoNonStaticLanguageSupport, languageName, qualifiedOperationName);
					}
					else {
						return new Invocations.UnresolveableInvocations(PivotMessages.NoLoadableLanguageSupport, languageName, qualifiedOperationName, "unknown declared method");
					}
				}
				else {
					if (hasNonStaticMethodNameMatches) {
						if (!requiredSourceType.conformsTo(standardLibrary, standardLibrary.getOclVoidType())) {			// Java has no OclVoid overloads - but diagnose class loader failure first
							return new UnresolvedInvocations(requiredSourceType, invocations);
						}
					}
					else if (hasStaticMethodNameMatches) {
						return new UnresolvedInvocations(requiredSourceType, invocations);
					}
					else {
						return new UnresolvedInvocations(requiredSourceType, invocations);
					}
				}
			} catch (ClassNotFoundException e) {
				return new Invocations.UnresolveableInvocations(PivotMessages.NoLoadableLanguageSupport, languageName, qualifiedOperationName, e.toString());
			}
		}
		return new Invocations.UnresolveableInvocations(PivotMessages.NoCompatibleLanguageSupport, languageName, qualifiedOperationName, requiredSourceType);
	}
}