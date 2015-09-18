/*******************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 424034
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.common.NameQueries;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ParameterType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.ParameterableElement;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.ValueId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorLambdaType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSpecializedCollectionType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSpecializedMapType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTupleType;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.xtext.util.Strings;

public class OCLinEcoreTablesUtils
{	
	public Comparator<ParameterTypes> templateBindingNameComparator = new Comparator<ParameterTypes>()
	{
		@Override
		public int compare(ParameterTypes o1, ParameterTypes o2) {
			assert o1 != null && o2 != null;
			String n1 = getTemplateBindingsName(o1);
			String n2 = getTemplateBindingsName(o2);
			return n1.compareTo(n2);
		}
	};

	public static Comparator<Nameable> nameComparator = new Comparator<Nameable>()
	{
		@Override
		public int compare(Nameable o1, Nameable o2) {
			String n1 = String.valueOf(o1.getName());
			String n2 = String.valueOf(o2.getName());
			return n1.compareTo(n2);
		}
	};

	public static final @NonNull Comparator<Property> propertyComparator = new Comparator<Property>()
	{
		@Override
		public int compare(Property p1, Property p2) {
			boolean b1 = p1.isIsImplicit();
			boolean b2 = p2.isIsImplicit();
			if (b1 != b2) {
				return b1 ? 1 : -1;
			}
			String n1 = String.valueOf(p1.getName());
			String n2 = String.valueOf(p2.getName());
			int diff = n1.compareTo(n2);
			if (diff != 0) {
				return diff;
			}
			Property o1 = p1.getOpposite();
			Property o2 = p2.getOpposite();
			if (o1 == null) {
				if (o2 == null) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				if (o2 == null) {
					return -1;
				}
				else {
					n1 = String.valueOf(o1.getName());
					n2 = String.valueOf(o2.getName());
					return n1.compareTo(n2);
				}
			}
		}
	};
	
	public static Comparator<Operation> signatureComparator = new Comparator<Operation>()
	{
		@Override
		public int compare(Operation o1, Operation o2) {
			assert o1 != null && o2 != null;
			String n1 = String.valueOf(getSignature(o1));
			String n2 = String.valueOf(getSignature(o2));
			return n1.compareTo(n2);
		}
	};

	private static <T extends GenPackage> T getLibraryGenPackage(List<T> genPackages) {
		for (T genPackage : genPackages) {
			EPackage ecorePackage = genPackage.getEcorePackage();
			EClassifier eClassifier = ecorePackage.getEClassifier("_Dummy");		// FIXME
			if (eClassifier != null) {
				return genPackage;
			}
		}		
		return null;
	}

	private static @Nullable <T extends GenPackage> T getMetamodelGenPackage(@NonNull List<T> genPackages) {
		for (T genPackage : genPackages) {
			EPackage ecorePackage = genPackage.getEcorePackage();
			EClassifier eClassifier = ecorePackage.getEClassifier("Element");
			if (eClassifier != null) {
				return genPackage;
			}
		}		
		return null;
	}

	private static @Nullable <T extends GenClassifier> T getNamedElement1(@Nullable List<T> genClasses, @NonNull String name) {
		if (genClasses != null) {
			for (T genClass : genClasses) {
				if (genClass.getName().equals(name)) {
					return genClass;
				}
			}
		}
		return null;
	}

	private static @Nullable <T extends GenFeature> T getNamedElement2(@Nullable List<T> genClasses, @NonNull String name) {
		if (genClasses != null) {
			for (T genClass : genClasses) {
				if (genClass.getName().equals(name)) {
					return genClass;
				}
			}
		}
		return null;
	}

	public @NonNull String getPagedName(@NonNull String name, int i, int iMax) {
		if (i < iMax) {
			return name + i;
		}
		else {
			return name;
		}
	}
	
	public static @NonNull Boolean isBuiltInType(@NonNull Type type) {
//		System.out.println(ClassUtil.debugSimpleName(type) + " + " + ClassUtil.debugSimpleName(type.getTypeId()) + " + " + type.getTypeId());
		return type.getTypeId() instanceof BuiltInTypeId;
	}

	private static @NonNull GenPackage loadGenPackage(@NonNull ResourceSet resourceSet, @NonNull URI genModelURI) {
		Resource resource = resourceSet.getResource(genModelURI, true);
		GenModel genModel = (GenModel) resource.getContents().get(0);
		GenPackage genPackage = genModel.getGenPackages().get(0);
		assert genPackage != null;
		return genPackage;
	}
	
	public static class CodeGenString
	{
		private final @NonNull StringBuilder s = new StringBuilder();
		private Map<String, String> classReferences = new HashMap<String, String>();
		
		protected final @NonNull Map<Type, String> typeNameMap = new HashMap<Type, String>();
		protected final @NonNull Set<String> typeNameUse = new HashSet<String>();
		
		public void append(@Nullable String string) {
			if (string != null) {
				s.append(string);
			}
		}

		public void addClassReference(@NonNull Class<?> referencedClass) {
			@SuppressWarnings("null")@NonNull String simpleName = referencedClass.getSimpleName();
			@SuppressWarnings("null")@NonNull String fullName = referencedClass.getName();
			addClassReference(simpleName, fullName);
		}

		protected String addClassReference(@NonNull String simpleName, @NonNull String fullName) {
			return classReferences.put(simpleName, fullName);
		}

		public void appendClassReference(@NonNull Class<?> referencedClass) {
//			s.append("<%");
			s.append(referencedClass.getSimpleName());
//			s.append("%>");
			addClassReference(referencedClass);
		}

		public void appendClassReference(@NonNull String referencedClass) {
			String key = referencedClass;
			int i = referencedClass.lastIndexOf(".");
			if (i > 0) {
				@SuppressWarnings("null")@NonNull String trimmedKey = referencedClass.substring(i+1);
				key = trimmedKey;
				s.append(key);
			}
			else {
//				s.append("<%");
				s.append(referencedClass);
//				s.append("%>");
			}
			addClassReference(key, referencedClass);
		}

		public void appendName(@NonNull NamedElement namedElement) {
			s.append(AbstractGenModelHelper.encodeName(namedElement));
		}

		public void appendParameterName(@NonNull NamedElement namedElement) {
			s.append(AbstractGenModelHelper.encodeName(namedElement));
		}

		/**
		 * Append the encoded name of a type with an _ prefix. The usage of the name is known to be unique to a particular package.
		 */
		public void appendScopedTypeName(@NonNull Type theType) {
			s.append("_" + AbstractGenModelHelper.encodeName(theType));
		}

		protected void appendString(@NonNull String string) {
			@SuppressWarnings("null")@NonNull String javaString = Strings.convertToJavaString(string);
			s.append("\"");
			s.append(javaString);
			s.append("\"");
		}

		/**
		 * Append the encoded name of a type with a suffix if disambiguation acros packages is required.
		 * @param metamodelManager 
		 */
		public void appendUnscopedTypeName(@NonNull PivotMetamodelManager metamodelManager, @NonNull Type theType) {
			s.append(getTypeName(metamodelManager.getPrimaryType(theType)));
		}
		
		private @NonNull String getTypeName(@NonNull Type theType) {
			String name = typeNameMap.get(theType);
			if (name != null) {
				return name;
			}
			name = AbstractGenModelHelper.encodeName(theType);
			if (typeNameUse.contains(name)) {
				int index = 1;
				String candidateName = name + '_' + index;
				while (typeNameUse.contains(name + '_' + index)) {
					index++;
				}
				name = candidateName;
			}
			typeNameMap.put(theType, name);
			typeNameUse.add(name);
			return name;
		}

		public @NonNull List<String> getClassReferences() {
			List<String> names = new ArrayList<String>(classReferences.values());
			Collections.sort(names);
			return names;
		}
		
		@Override
		public @NonNull String toString() {
			@SuppressWarnings("null")@NonNull String string = s.toString();
			return string;
		}
	}

	public class DeclareParameterTypeVisitor extends AbstractExtendingVisitor<Object, Object>
	{
		protected DeclareParameterTypeVisitor(@NonNull Object context) {
			super(context);
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported DeclareParameterTypeVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitClass(@NonNull org.eclipse.ocl.pivot.Class type) {
//			TemplateParameter owningTemplateParameter = type.isTemplateParameter();
//			if (owningTemplateParameter == null) {
				type.accept(emitQualifiedLiteralVisitor);
/*			}
			else if (owningTemplateParameter.getSignature().getTemplate() instanceof org.eclipse.ocl.pivot.Class) {
				org.eclipse.ocl.pivot.Class containerType = (org.eclipse.ocl.pivot.Class) owningTemplateParameter.getSignature().getTemplate();
				assert containerType != null;
				String prefix = getQualifiedTablesClassName(containerType);
				if (prefix.length() <= 0) {
					s.append("(");
					s.appendClassReference(DomainType.class);
					s.append(")null/*containerType._package.name/");
				}
				else {
					s.appendClassReference(prefix);
					s.append(".TypeParameters.");
					s.appendScopedTypeName(containerType);
					s.append("_");
					s.appendParameterName(type);
				}
			}
			else if (owningTemplateParameter.getSignature().getTemplate() instanceof Operation) {
				Operation containerOperation  = (Operation) owningTemplateParameter.getSignature().getTemplate();
				org.eclipse.ocl.pivot.Class containerType = containerOperation.getOwningClass();
				assert containerType != null;
				String prefix = getQualifiedTablesClassName(containerType);
				if (prefix.length() <= 0) {
					s.append("(");
					s.appendClassReference(DomainType.class);
					s.append(")null/*containerOperation.owningType._package.name/");
				}
				else {
					s.appendClassReference(prefix);
					s.append(".TypeParameters._");
					containerOperation.accept(emitLiteralVisitor);
					s.append("_");
					s.appendParameterName(type);
				}
			} */
			return null;
		}		

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType type) {
			s.append("new ");
			s.appendClassReference(ExecutorSpecializedCollectionType.class);
			s.append("(");
			s.appendString(ClassUtil.nonNullModel(type.getName()));
			s.append(", ");
			type.getElementType().accept(this);
			s.append(".getTypeId(), ");
			s.appendClassReference(ValueId.class);
			s.append(type.isIsNullFree() ? ".TRUE_ID" : ".FALSE_ID");
			s.append(")");
			return null;
		}

		@Override
		public @Nullable Object visitLambdaType(@NonNull LambdaType lambdaType) {
			s.append("new ");
			s.appendClassReference(ExecutorLambdaType.class);
			s.append("(");
			s.appendString(ClassUtil.nonNullModel(lambdaType.getName()));
			for (ParameterType parameterType : lambdaType.getOwnedParameterTypes()) {
				s.append(", ");
				parameterType.accept(this);
			}
			s.append(")");
			return null;
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType type) {
			s.append("new ");
			s.appendClassReference(ExecutorSpecializedMapType.class);
			s.append("(");
			s.appendString(ClassUtil.nonNullModel(type.getName()));
			s.append(", ");
			type.getKeyType().accept(this);
			s.append(", ");
			type.getValueType().accept(this);
			s.append(")");
			return null;
		}

		@Override
		public @Nullable Object visitTupleType(@NonNull TupleType tupleType) {
			s.append("new ");
			s.appendClassReference(ExecutorTupleType.class);
			s.append("(");
			s.appendString(ClassUtil.nonNullModel(tupleType.getName()));
			s.append(", ");
			for (Property part : tupleType.getOwnedProperties()) {
				s.append(", ");
				part.getType().accept(this);
			}
			s.append(")");
			return null;
		}		

		@Override
		public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter type) {
			TemplateableElement template = type.getOwningSignature().getOwningElement();
			if (template instanceof org.eclipse.ocl.pivot.Class) {
				org.eclipse.ocl.pivot.Class containerType = (org.eclipse.ocl.pivot.Class) template;
				assert containerType != null;
				String prefix = getQualifiedTablesClassName(containerType);
				if (prefix.length() <= 0) {
					s.append("(");
					s.appendClassReference(Type.class);
					s.append(")null/*containerType._package.name/");
				}
				else {
					s.appendClassReference(prefix);
					s.append(".TypeParameters.");
					s.appendScopedTypeName(containerType);
					s.append("_");
					s.appendParameterName(type);
				}
			}
			else if (template instanceof Operation) {
				Operation containerOperation  = (Operation) template;
				org.eclipse.ocl.pivot.Class containerType = containerOperation.getOwningClass();
				assert containerType != null;
				String prefix = getQualifiedTablesClassName(containerType);
				if (prefix.length() <= 0) {
					s.append("(");
					s.appendClassReference(Type.class);
					s.append(")null/*containerOperation.owningType._package.name/");
				}
				else {
					s.appendClassReference(prefix);
					s.append(".TypeParameters._");
					containerOperation.accept(emitLiteralVisitor);
					s.append("_");
					s.appendParameterName(type);
				}
			}
			return null;
		}		
	}

	public class EmitLiteralVisitor extends AbstractExtendingVisitor<Object, Object>
	{
		protected EmitLiteralVisitor(@NonNull Object context) {
			super(context);
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported EmitLiteralVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitClass(@NonNull org.eclipse.ocl.pivot.Class type) {
			s.append("Types.");
			s.appendScopedTypeName(type);
			return null;
		}		

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType type) {
			CollectionType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(type);
//			s.appendClassReference(getQualifiedTablesClassName(unspecializedType));
			s.append("Types.");
			s.appendScopedTypeName(unspecializedType);
			return null;
		}

		@Override
		public @Nullable Object visitConstraint(@NonNull Constraint constraint) {
			Type type = ClassUtil.nonNullModel((Type) constraint.eContainer());
			s.appendScopedTypeName(type);
			s.append("__");
			s.append(NameQueries.getUniqueText(type, constraint));
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationLiteral(@NonNull EnumerationLiteral enumerationLiteral) {
			Enumeration enumeration = ClassUtil.nonNullModel(enumerationLiteral.getOwningEnumeration());
//			s.appendClassReference(getQualifiedTablesClassName(enumeration));
			s.append("EnumerationLiterals.");
			s.appendScopedTypeName(enumeration);
			s.append("__");
			s.appendName(enumerationLiteral);
			return null;
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType type) {
			MapType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(type);
//			s.appendClassReference(getQualifiedTablesClassName(unspecializedType));
			s.append("Types.");
			s.appendScopedTypeName(unspecializedType);
			return null;
		}

		@Override
		public @Nullable Object visitOperation(@NonNull Operation operation) {
			s.appendScopedTypeName(ClassUtil.nonNullModel(operation.getOwningClass()));
			s.append("__");
			s.appendName(operation);
			return null;
		}

		@Override
		public @Nullable Object visitPackage(@NonNull org.eclipse.ocl.pivot.Package pkge) {
			s.append("_");
			s.appendName(pkge);
			return null;
		}

		@Override
		public @Nullable Object visitProperty(@NonNull Property property) {
			s.appendScopedTypeName(ClassUtil.nonNullModel(property.getOwningClass()));
			s.append("__");
			s.appendName(property);
			if (property.isIsImplicit()) {
				Property opposite = property.getOpposite();
				if (opposite != null) {
					s.append("__");
					s.appendName(opposite);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter type) {
			s.append("null");
			return null;
		}		
	}
	
	public class EmitQualifiedLiteralVisitor extends EmitLiteralVisitor
	{
		protected EmitQualifiedLiteralVisitor(@NonNull Object context) {
			super(context);
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported EmitQualifiedLiteralVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitClass(@NonNull org.eclipse.ocl.pivot.Class type) {
			s.appendClassReference(getQualifiedTablesClassName(type));
			s.append(".Types.");
			s.appendScopedTypeName(type);
			return null;
		}		

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType object) {
			CollectionType unspecializedObject = PivotUtil.getUnspecializedTemplateableElement(object);
			s.appendClassReference(getQualifiedTablesClassName(unspecializedObject));
			s.append(".Types.");
			s.appendScopedTypeName(unspecializedObject);
			return null;
		}

		@Override
		public @Nullable Object visitDataType(@NonNull DataType object) {
			DataType unspecializedObject = PivotUtil.getUnspecializedTemplateableElement(object);
			s.appendClassReference(getQualifiedTablesClassName(unspecializedObject));
			s.append(".Types.");
			s.appendScopedTypeName(unspecializedObject);
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationLiteral(@NonNull EnumerationLiteral enumerationLiteral) {
			Enumeration enumeration = ClassUtil.nonNullModel(enumerationLiteral.getOwningEnumeration());
			s.appendClassReference(getQualifiedTablesClassName(enumeration));
			s.append(".EnumerationLiterals.");
			s.appendScopedTypeName(enumeration);
			s.append("__");
			s.appendName(enumerationLiteral);
			return null;
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType object) {
			MapType unspecializedObject = PivotUtil.getUnspecializedTemplateableElement(object);
			s.appendClassReference(getQualifiedTablesClassName(unspecializedObject));
			s.append(".Types.");
			s.appendScopedTypeName(unspecializedObject);
			return null;
		}

		@Override
		public @Nullable Object visitOperation(@NonNull Operation operation) {
			org.eclipse.ocl.pivot.Class type = ClassUtil.nonNullModel(operation.getOwningClass());
			s.appendClassReference(getQualifiedTablesClassName(type));
			s.append(".Operations.");
			return super.visitOperation(operation);
		}

		@Override
		public @Nullable Object visitProperty(@NonNull Property property) {
			org.eclipse.ocl.pivot.Class type = ClassUtil.nonNullModel(property.getOwningClass());
			s.appendClassReference(getQualifiedTablesClassName(type));
			s.append(".Properties.");
			return super.visitProperty(property);
		}

		@Override
		public @Nullable Object visitTupleType(@NonNull TupleType type) {
			s.appendClassReference(getQualifiedTablesClassName(type));
			s.append(".tuple_type_");			// 
			s.appendUnscopedTypeName(metamodelManager, type);
			return null;
//			[ast.getTablesClassName(genPackage).getPrefixedSymbolName('tuple_type_')/][/template]
		}
	}

	protected final @NonNull CodeGenString s = new CodeGenString();
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull StandardLibraryInternal standardLibrary;
	protected final @NonNull org.eclipse.ocl.pivot.Package pPackage;
	protected final @NonNull DeclareParameterTypeVisitor declareParameterTypeVisitor = new DeclareParameterTypeVisitor(s);
	protected final @NonNull EmitLiteralVisitor emitLiteralVisitor = new EmitLiteralVisitor(s);
	protected final @NonNull EmitQualifiedLiteralVisitor emitQualifiedLiteralVisitor = new EmitQualifiedLiteralVisitor(s);
	protected final @NonNull Iterable<org.eclipse.ocl.pivot.Class> activeClassesSortedByName;
	protected final @NonNull Map<ParameterTypes, String> templateBindingsNames = new HashMap<ParameterTypes, String>();
	

	protected OCLinEcoreTablesUtils(@NonNull GenPackage genPackage) {
		this.genPackage = genPackage;
		Resource genModelResource = genPackage.eResource();
		ResourceSet genModelResourceSet = genModelResource.getResourceSet();
		assert genModelResourceSet != null;
		EnvironmentFactoryAdapter resourceSetAdapter = OCLInternal.adapt(genModelResourceSet);
		this.metamodelManager = resourceSetAdapter.getMetamodelManager();
		this.environmentFactory = metamodelManager.getEnvironmentFactory();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.pPackage = ClassUtil.nonNullModel(getPivotPackage(genPackage));
		activeClassesSortedByName = getActiveClassesSortedByName(pPackage);
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.Class> getActiveClassesSortedByName(@NonNull org.eclipse.ocl.pivot.Package pPackage) {
		List<org.eclipse.ocl.pivot.Class> sortedClasses = new ArrayList<org.eclipse.ocl.pivot.Class>(getActiveTypes(pPackage));
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}
	
	protected @NonNull Set<? extends org.eclipse.ocl.pivot.Class> getActiveTypes(@NonNull org.eclipse.ocl.pivot.Package pPackage) {
		Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Package pivotMetamodel = metamodelManager.getASmetamodel();
		Type elementType = metamodelManager.getASClass("Element");
		if (oclstdlibPackage == pPackage) {
			VoidType oclVoidType = metamodelManager.getStandardLibrary().getOclVoidType();
			Set<org.eclipse.ocl.pivot.Class> types = new HashSet<org.eclipse.ocl.pivot.Class>();
			for (org.eclipse.ocl.pivot.Class type : oclstdlibPackage.getOwnedClasses()) {
				assert type != null;
				CompleteClass completeClass = metamodelManager.getCompleteClass(type);
				if ((elementType == null) || !isElementType(completeClass, elementType, oclVoidType)) {
					types.add(type);
				}
			}
			return types;
		}
		else if (pivotMetamodel == pPackage) {
			Set<org.eclipse.ocl.pivot.Class> types = new HashSet<org.eclipse.ocl.pivot.Class>();
			for (org.eclipse.ocl.pivot.Class type : pivotMetamodel.getOwnedClasses()) {
				assert type != null;
				boolean pruned = false;
				Type myType = null;
				CompleteClass completeClass = metamodelManager.getCompleteClass(type);
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					org.eclipse.ocl.pivot.Package partialPackage = partialClass.getOwningPackage();
					if (partialPackage == oclstdlibPackage) {
						if ((elementType != null) && !completeClass.conformsTo(elementType)) {
//							System.out.println("Prune " + type.getName());
							pruned = true;
						}
					}
					else if (partialPackage == pPackage) {
						myType = type;
					}
				}
				if (!pruned && (myType instanceof org.eclipse.ocl.pivot.Class)) {
					types.add((org.eclipse.ocl.pivot.Class)myType);
				}
			}
//			if (oclstdlibPackage != null) {
//				for (DomainType type : oclstdlibPackage.getOwnedType()) {
//					types.remove(type.getName());
//				}
//			}
			return types;
		}
		else {
			return new HashSet<org.eclipse.ocl.pivot.Class>(pPackage.getOwnedClasses());
		}
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.Class> getAllProperSupertypesSortedByName(@NonNull org.eclipse.ocl.pivot.Class pClass) {
		org.eclipse.ocl.pivot.Class theClass = metamodelManager.getPrimaryClass(pClass);
		Map<org.eclipse.ocl.pivot.Class, Integer> results = new HashMap<org.eclipse.ocl.pivot.Class, Integer>();
		getAllSuperClasses(results, theClass);
		List<org.eclipse.ocl.pivot.Class> sortedClasses = new ArrayList<org.eclipse.ocl.pivot.Class>(results.keySet());
		sortedClasses.remove(theClass);
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getAllSupertypesSortedByName(@NonNull org.eclipse.ocl.pivot.Class pClass) {
		Map<org.eclipse.ocl.pivot.Class, Integer> results = new HashMap<org.eclipse.ocl.pivot.Class, Integer>();
		getAllSuperClasses(results, pClass);
		List<org.eclipse.ocl.pivot.Class> sortedClasses = new ArrayList<org.eclipse.ocl.pivot.Class>(results.keySet());
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}
	
	@SuppressWarnings("null")
	protected int getAllSuperClasses(@NonNull Map<org.eclipse.ocl.pivot.Class, Integer> results, @NonNull org.eclipse.ocl.pivot.Class aClass) {
		org.eclipse.ocl.pivot.Class theClass = metamodelManager.getPrimaryClass(aClass);
		Integer depth = results.get(theClass);
		if (depth != null) {
			return depth;
		}
		int myDepth = 0;
		for (@NonNull CompleteClass superCompleteClass : metamodelManager.getAllSuperCompleteClasses(theClass)) {
			org.eclipse.ocl.pivot.Class superClass = superCompleteClass.getPrimaryClass();
			if (superClass != theClass) {
				superClass = PivotUtil.getUnspecializedTemplateableElement(superClass);
				int superDepth = getAllSuperClasses(results, superClass);
				if (superDepth >= myDepth) {
					myDepth = superDepth+1;
				}
			}
		}
		results.put(theClass, myDepth);
		return myDepth;
	}
	
	protected @Nullable org.eclipse.ocl.pivot.Package getExtendedPackage(@NonNull org.eclipse.ocl.pivot.Package pPackage) {
		Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Package pivotMetamodel = metamodelManager.getASmetamodel();
		if (oclstdlibPackage == pPackage) {
			return null;
		}
		else if (pivotMetamodel == pPackage) {
			return oclstdlibPackage;
		}
		else {
			return null;
		}
	}

	public @NonNull GenPackage getGenPackage() {
		return genPackage;
	}
	
	protected @Nullable GenPackage getGenPackage(@NonNull org.eclipse.ocl.pivot.Class type) {
		org.eclipse.ocl.pivot.Package pPackage = type.getOwningPackage();
		assert pPackage != null;
		Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Class elementType = metamodelManager.getASClass("Element");
		if ((elementType != null) && (oclstdlibPackage != null)) {
			VoidType oclVoidType = metamodelManager.getStandardLibrary().getOclVoidType();
			org.eclipse.ocl.pivot.Package pivotMetamodel = elementType.getOwningPackage();
			assert pivotMetamodel != null;
			if (oclstdlibPackage == pPackage) {
				CompleteClass completeClass = metamodelManager.getCompleteClass(type);
				if (isElementType(completeClass, elementType, oclVoidType)) {
					return getGenPackage(pivotMetamodel);
				}
				else {
					return getGenPackage(oclstdlibPackage);
				}
			}
			else if (pivotMetamodel == pPackage) {
				CompleteClass completeClass = metamodelManager.getCompleteClass(type);
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					org.eclipse.ocl.pivot.Package partialPackage = partialClass.getOwningPackage();
					if (partialPackage == oclstdlibPackage) {
						if (!isElementType(completeClass, elementType, oclVoidType)) {
							return getGenPackage(oclstdlibPackage);
						}
					}
				}
				return getGenPackage(pivotMetamodel);
			}
		}
		return getGenPackage(pPackage);
	}
	
	protected @Nullable GenPackage getGenPackage(@NonNull org.eclipse.ocl.pivot.Package asPackage) {
		EPackage firstEPackage = genPackage.getEcorePackage();
		if (firstEPackage.getName().equals(asPackage.getName())) {
			return genPackage;
		}
		List<GenPackage> usedGenPackages = genPackage.getGenModel().getUsedGenPackages();
		assert usedGenPackages != null;
//		String nsURI = asPackage.getNsURI();
//		String name = asType.getName();
//		GenPackage usedGenPackage = getNsURIGenPackage(usedGenPackages, nsURI, name);
//		if (usedGenPackage != null) {
//			return usedGenPackage;
//		}		
		Resource genModelResource = genPackage.eResource();
		ResourceSet genModelResourceSet = genModelResource.getResourceSet();
		assert genModelResourceSet != null;
		org.eclipse.ocl.pivot.Package metamodelPackage = metamodelManager.getASmetamodel();
		org.eclipse.ocl.pivot.Package libraryPackage = metamodelManager.getLibraries().get(0);
		if (asPackage == libraryPackage) {
			GenPackage libraryGenPackage = getLibraryGenPackage(usedGenPackages);
			if (libraryGenPackage == null) {
				libraryGenPackage = loadGenPackage(genModelResourceSet, LibraryConstants.GEN_MODEL_URI);
			}
			return libraryGenPackage;
		}
		if (asPackage == metamodelPackage) {
			GenPackage metamodelGenPackage = getMetamodelGenPackage(usedGenPackages);
			if (metamodelGenPackage == null) {
				metamodelGenPackage = loadGenPackage(genModelResourceSet, PivotConstantsInternal.GEN_MODEL_URI);
			}
			return metamodelGenPackage;
		}
		String nsURI = asPackage.getURI();
		if (nsURI != null) {
			GenPackage genPackage2 = metamodelManager.getGenPackage(nsURI);
			if (genPackage2 != null) {
				return genPackage2;
			}
			for (GenPackage usedGenPackage : usedGenPackages) {
				if (usedGenPackage != null) {
					metamodelManager.addGenPackage(usedGenPackage);
				}
			}
			genPackage2 = metamodelManager.getGenPackage(nsURI);
			if (genPackage2 != null) {
				return genPackage2;
			}
		}
		throw new IllegalStateException("No GenPackage for '" + nsURI + "'");
	}

	protected @NonNull String getImplementationName(@NonNull Operation operation) {
		if (operation.getImplementationClass() != null) {
			return operation.getImplementationClass() + ".INSTANCE";
		}
		else {
//		    List<Constraint> constraints = operation.getOwnedRule();
//			if (constraints.size() > 0) {
//				return getQualifiedBodiesClassName(ClassUtil.nonNullModel(operation.getOwningType())) + "._" + operation.getName() + "_" + constraints.get(0).getStereotype() + "_.INSTANCE";
//			}
//			else {
				return "null";
//			}
		}
	}

	protected @NonNull Iterable<Operation> getLocalOperationsSortedBySignature(@NonNull org.eclipse.ocl.pivot.Class pClass) {
		// cls.getOperations()->sortedBy(op2 : Operation | op2.getSignature())
		List<Operation> sortedOperations = new ArrayList<Operation>(getOperations(pClass));
		Collections.sort(sortedOperations, signatureComparator);
		return sortedOperations;
	}

	protected @NonNull List<Property> getLocalPropertiesSortedByName(@NonNull org.eclipse.ocl.pivot.Class pClass) {
		List<Property> sortedProperties = new ArrayList<Property>();
		for (/*@NonNull*/ Property property : getProperties(pClass)) {
			assert property != null;
			if (isProperty(property)) {
				sortedProperties.add(property);
			}
		}
		Collections.sort(sortedProperties, propertyComparator);
		return sortedProperties;
	}
	
	protected @NonNull LinkedHashSet<Operation> getOperations(@NonNull org.eclipse.ocl.pivot.Class type) {
		LinkedHashSet<Operation> operations = new LinkedHashSet<Operation>();
		for (Operation operation : metamodelManager.getMemberOperations(type, false)) {
			operations.add(operation);
		}
		for (Operation operation : metamodelManager.getMemberOperations(type, true)) {
			operations.add(operation);
		}
		return operations;
	}

	protected @NonNull Operation getOverloadOp(@NonNull org.eclipse.ocl.pivot.Class pClass, @NonNull Operation baseOp) {
		String baseSignature = getSignature(baseOp);
		Map<org.eclipse.ocl.pivot.Class, Integer> results = new HashMap<org.eclipse.ocl.pivot.Class, Integer>();
		getAllSuperClasses(results, pClass);
		int bestDepth = -1;
		Operation best = null;
		for (org.eclipse.ocl.pivot.Class aClass : results.keySet()) {
			int aDepth = results.get(aClass);
			for (Operation op : getOperations(ClassUtil.nonNullState(aClass))) {
				if (baseSignature.equals(getSignature(ClassUtil.nonNullState(op))) && (aDepth > bestDepth)) {
					bestDepth = aDepth;
					best = op;
				}
			}
		}
		assert best != null;
		return best;
	}
	
	protected org.eclipse.ocl.pivot.Package getPivotPackage(@NonNull GenPackage genPackage) {
		EPackage ePackage = genPackage.getEcorePackage();
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return null;
		}
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
		org.eclipse.ocl.pivot.Package asPackage = ecore2as.getCreated(org.eclipse.ocl.pivot.Package.class, ePackage);
		if (asPackage == null) {
			return null;
		}
		if (asPackage.getURI().equals(LibraryConstants.STDLIB_URI)) {				// If generating OCLstdlibTables ...
			mergeLibrary(asPackage);			// FIXME: redundant once M2T scans all partial types
		}
		return asPackage;
	}
	
	protected @NonNull LinkedHashSet<Property> getProperties(@NonNull org.eclipse.ocl.pivot.Class type) {
		Set<String> names = new HashSet<String>();
		LinkedHashSet<Property> properties = new LinkedHashSet<Property>();
		for (@SuppressWarnings("null")@NonNull Property property : metamodelManager.getMemberProperties(type, true)) {
			names.add(property.getName());
			properties.add(metamodelManager.getPrimaryProperty(property));
		}
		for (@SuppressWarnings("null")@NonNull Property property : metamodelManager.getMemberProperties(type, false)) {
			if (!names.contains(property.getName())) {
				properties.add(metamodelManager.getPrimaryProperty(property));
			}
		}
		return properties;
	}
	
	protected @NonNull String getQualifiedTablesClassName(@NonNull org.eclipse.ocl.pivot.Class type) {
		GenPackage genPackage = getGenPackage(type);
		if (genPackage != null) {
			return genPackage.getReflectionPackageName() + "." + getTablesClassName(genPackage);
		}
		else {
			return "UnknownMetamodelTables";
		}
	}
	
	protected @NonNull String getQualifiedTablesClassName(@NonNull org.eclipse.ocl.pivot.Package pPackage) {
		GenPackage genPackage = getGenPackage(pPackage);
		if (genPackage != null) {
			return genPackage.getReflectionPackageName() + "." + getTablesClassName(genPackage);
		}
		else {
			return "UnknownMetamodelTables";
		}
	}
	
	protected @NonNull String getSharedLibrary() {
		org.eclipse.ocl.pivot.Package thisPackage = getPivotPackage(genPackage);
		if (thisPackage != null) {
			PrimitiveType booleanType = standardLibrary.getBooleanType();
			org.eclipse.ocl.pivot.Package libraryPackage = booleanType.getOwningPackage();
			if (libraryPackage != null) {
				GenPackage gPackage = getGenPackage(libraryPackage);
				if (gPackage != null) {
					return gPackage.getReflectionPackageName() + "." + gPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
				}
			}
		}
/*		TypeServer typeServer = metamodelManager.getTypeServer(booleanType);
		for (DomainType type : typeServer.getPartialTypes()) {
			org.eclipse.ocl.pivot.Package asPackage = type.getPackage();
			if ((asPackage != null) && (asPackage != thisPackage)) {
				GenPackage gPackage = getGenPackage(genPackage, asPackage);
				if (gPackage != null) {
					return getInterfacePackageName(gPackage) + "." + gPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
				}
			}
		} */
		return "";
	}
	
	public static @NonNull String getSignature(@NonNull Operation anOperation) {
		org.eclipse.ocl.pivot.Class owningType = anOperation.getOwningClass();
		if (owningType == null) {
			return "null";
		}
		String qualifiedSignature = PrettyPrinter.printType(anOperation, owningType);
		int colonColonIndex = qualifiedSignature.indexOf("::");
		int parenthesisIndex = qualifiedSignature.indexOf("(");
		if ((parenthesisIndex < 0) ? (colonColonIndex > 0) : (colonColonIndex < parenthesisIndex)) {	// FIXME use a decent inherently right algorithm
			@SuppressWarnings("null")@NonNull String substring = qualifiedSignature.substring(colonColonIndex+1);
			return substring;
		}
		else {
			return qualifiedSignature;	// FIXME with PrettyPrintOptions
		}
	}

	public @NonNull String getTablesClassName() {
		return getTablesClassName(genPackage);
	}

	protected @NonNull String getTablesClassName(@NonNull GenPackage genPackage) {
		return genPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
	}

	protected @NonNull String getTemplateBindingsName(@NonNull ParameterTypes templateBindings) {
		String name2 = templateBindingsNames.get(templateBindings);
		if (name2 == null) {
			StringBuilder s = new StringBuilder();
			s.append("_");
			if (templateBindings.size() > 0 ) {
				for (int i = 0; i < templateBindings.size(); i++) {
					if (i > 0) {
						s.append("___");
					}
					Type element = templateBindings.get(i);
					getTemplateBindingsName(s, element);
				}
			}
			@SuppressWarnings("null")@NonNull String string = s.toString();
			name2 = string;
			templateBindingsNames.put(templateBindings, name2);
		}
		return name2;
	}
	private void getTemplateBindingsName(@NonNull StringBuilder s, @NonNull ParameterableElement element) {
		TemplateParameter templateParameter = element.asTemplateParameter();
		if (templateParameter != null) {
			TemplateableElement template = templateParameter.getOwningSignature().getOwningElement();
			if (template instanceof Operation) {
				s.append(AbstractGenModelHelper.encodeName(ClassUtil.nonNullModel(((Operation) template).getOwningClass())));
				s.append("_");
			}
			s.append(AbstractGenModelHelper.encodeName(ClassUtil.nonNullModel((NamedElement) template)));
			s.append("_");
		}
		s.append(AbstractGenModelHelper.encodeName((NamedElement)element));
		if (element instanceof TemplateableElement) {
			List<TemplateBinding> templateBindings = ((TemplateableElement)element).getOwnedBindings();
			if (templateBindings.size() > 0) {
				s.append("_");
				for (TemplateBinding templateBinding : templateBindings) {
					for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getOwnedSubstitutions()) {
						s.append("_");
						getTemplateBindingsName(s, ClassUtil.nonNullModel(templateParameterSubstitution.getActual()));
					}
				}
				s.append("__");
			}
		}
		if (element instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)element;
			for (/*@NonNull*/ ParameterType parameterType : lambdaType.getOwnedParameterTypes()) {
				assert parameterType != null;
				s.append("_");
				getTemplateBindingsName(s, ClassUtil.nonNullModel(parameterType.getType()));
				if (parameterType.isIsNonNull()) {
					s.append("_NullFree");
				}
			}
			s.append("_");
			ParameterType resultType = lambdaType.getOwnedResultType();
			getTemplateBindingsName(s, ClassUtil.nonNullModel(resultType.getType()));
			if (resultType.isIsNonNull()) {
				s.append("_NullFree");
			}
		}
	}
	
	/**
	 * Return  true if property has an Ecore counterpart. Non-navigable opposites may have a Property
	 * but no Ecore EReference.
	 */
	protected @NonNull Boolean hasEcore(@NonNull Property property) {
		org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
		if (owningType == null) {
			return false;
		}
		String typeName = owningType.getName();
		if (typeName == null) {
			return false;
		}
		GenClass genClass = getNamedElement1(genPackage.getGenClasses(), typeName);
		if (genClass == null) {
			return false;
		}
		String propertyName = property.getName();
		if (propertyName == null) {
			return false;
		}
		GenFeature genFeature = getNamedElement2(genClass.getAllGenFeatures(), propertyName);
		if (genFeature == null) {
			return false;
		}
		return true;
	}

	protected @NonNull Boolean hasSharedLibrary() {
		org.eclipse.ocl.pivot.Package thisPackage = getPivotPackage(genPackage);
		PrimitiveType booleanType = standardLibrary.getBooleanType();
		org.eclipse.ocl.pivot.Package libraryPackage = booleanType.getOwningPackage();
		return thisPackage != libraryPackage;
	}

	/**
	 * Return true if completeComplass conforms to elementType but not to oclVoidType.
	 */
	protected boolean isElementType(@NonNull CompleteClass completeClass, @NonNull Type elementType, @NonNull VoidType oclVoidType) {
		return completeClass.conformsTo(elementType) && !completeClass.conformsTo(oclVoidType);
	}

	protected boolean isLambdaParameterList(@NonNull ParametersId parametersId) {
		for (TypeId typeId : parametersId) {
			if (typeId instanceof LambdaTypeId) {
				return true;
			}
		}
		return false;
	}

	protected boolean isProperty(@NonNull Property prop) {
		if (hasEcore(prop)) {
			return true;
		}
		Property opposite = prop.getOpposite();
		return (opposite != null) && hasEcore(opposite);
	}
	
	/**
	 * Return true if type has an Ecore counterpart. The Standard Library genmodel has
	 * no Ecore types, unless the Pivot model is also in use.
	 */
	protected @NonNull Boolean hasEcore(@NonNull Type type) {
		String typeName = type.getName();
		if (typeName != null) {
			GenClass genClass = getNamedElement1(genPackage.getGenClasses(), typeName);
			if (genClass != null) {
				return true;
			}
			GenEnum genEnum = getNamedElement1(genPackage.getGenEnums(), typeName);
			if (genEnum != null) {
				return true;
			}
		}
		return false;
	}
	
	protected void mergeLibrary(@NonNull org.eclipse.ocl.pivot.Package primaryPackage) {
//		primaryPackage.setName("ocl");
		List<org.eclipse.ocl.pivot.Class> primaryTypes = primaryPackage.getOwnedClasses();
		for (Library library : metamodelManager.getLibraries()) {
			Map<org.eclipse.ocl.pivot.Class,org.eclipse.ocl.pivot.Class> typeMap = new HashMap<org.eclipse.ocl.pivot.Class,org.eclipse.ocl.pivot.Class>();
			ArrayList<org.eclipse.ocl.pivot.Class> libraryTypes = new ArrayList<org.eclipse.ocl.pivot.Class>(library.getOwnedClasses());
			for (org.eclipse.ocl.pivot.Class secondaryType : libraryTypes) {
				org.eclipse.ocl.pivot.Class primaryType = NameUtil.getNameable(primaryTypes, secondaryType.getName());
				if (primaryType != null) {
					typeMap.put(secondaryType, primaryType);
				}
				else {
					primaryTypes.add(secondaryType);
				}
			}
			for (org.eclipse.ocl.pivot.Class secondaryType : libraryTypes) {
				org.eclipse.ocl.pivot.Class primaryType = typeMap.get(secondaryType);
				if (primaryType != null) {
					List<org.eclipse.ocl.pivot.Class> primarySuperClasses = primaryType.getSuperClasses();
					for (org.eclipse.ocl.pivot.Class secondarySuperClass : secondaryType.getSuperClasses()) {
						org.eclipse.ocl.pivot.Class primarySuperClass = typeMap.get(secondarySuperClass);
						if (primarySuperClass == null) {
							primarySuperClasses.add(secondarySuperClass);
						}
						else if (!primarySuperClasses.contains(primarySuperClass)) {
							primarySuperClasses.add(primarySuperClass);
						}
					}
					primaryType.getOwnedOperations().addAll(secondaryType.getOwnedOperations());
					primaryType.getOwnedProperties().addAll(secondaryType.getOwnedProperties());
				}
			}
		}
		for (org.eclipse.ocl.pivot.Class primaryType : primaryTypes) {
			List<org.eclipse.ocl.pivot.Class> primarySuperClasses = primaryType.getSuperClasses();
			Type classType = NameUtil.getNameable(primarySuperClasses, TypeId.CLASS_NAME);
			Type metaclass = NameUtil.getNameable(primarySuperClasses, "Classifier");
			if ((classType != null) && (metaclass != null)) {
				primarySuperClasses.remove(classType);		// WIP FIXME fix at source
			}
		}
	}
}

