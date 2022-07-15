/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.DependencyVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.ReferencesVisitor;
import org.eclipse.ocl.examples.codegen.asm5.ASM5JavaAnnotationReader;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cse.CommonSubexpressionEliminator;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.examples.codegen.generator.AbstractCodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.iteration.AnyIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.CollectIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.CollectNestedIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.ExistsIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.ForAllIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.IsUniqueIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.IterateIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.OneIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.RejectIteration2Java;
import org.eclipse.ocl.examples.codegen.java.iteration.SelectIteration2Java;
import org.eclipse.ocl.examples.codegen.java.types.BoxedDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.EcoreDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.Id2BoxedDescriptorVisitor;
import org.eclipse.ocl.examples.codegen.java.types.UnboxedDescriptor;
import org.eclipse.ocl.examples.codegen.utilities.AbstractCGModelResourceFactory;
import org.eclipse.ocl.examples.codegen.utilities.CGModelResource;
import org.eclipse.ocl.examples.codegen.utilities.CGModelResourceFactory;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.iterator.AnyIteration;
import org.eclipse.ocl.pivot.library.iterator.CollectIteration;
import org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration;
import org.eclipse.ocl.pivot.library.iterator.ExistsIteration;
import org.eclipse.ocl.pivot.library.iterator.ForAllIteration;
import org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration;
import org.eclipse.ocl.pivot.library.iterator.IterateIteration;
import org.eclipse.ocl.pivot.library.iterator.OneIteration;
import org.eclipse.ocl.pivot.library.iterator.RejectIteration;
import org.eclipse.ocl.pivot.library.iterator.SelectIteration;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.collect.Lists;

/**
 * OCL2JavaClass supports generation of the content of a JavaClassFile to
 * provide the polymorphic implementation of an ExpressionInOCL.
 */
public abstract class JavaCodeGenerator extends AbstractCodeGenerator
{
	public static Map<Class<?>, Class<?>> javaPrimitiveClasses = new HashMap<Class<?>, Class<?>>();

	public static Map<String, Class<?>> javaPrimitiveNames = new HashMap<String, Class<?>>();
	{
		initPrimitive(boolean.class, Boolean.class);
		initPrimitive(byte.class, Byte.class);
		initPrimitive(char.class, Character.class);
		initPrimitive(double.class, Double.class);
		initPrimitive(float.class, Float.class);
		initPrimitive(int.class, Integer.class);
		initPrimitive(long.class, Long.class);
		initPrimitive(short.class, Short.class);
	}

	private static final @NonNull AbstractCGModelResourceFactory CG_RESOURCE_FACTORY = new AbstractCGModelResourceFactory();

	/**
	 * The known classes that templates may use in unqualified form. The list is
	 * here in a Java form to reduce the impact of refactoring templates.
	 *
	 * @deprecated no longer used; the import generator is smart enough to analyze what is actually used
	 */
	@Deprecated
	public static final @NonNull Class<?>[] knownClasses = {
		java.lang.Class.class,
		java.lang.Object.class,
		java.lang.Package.class,
		java.util.Iterator.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumeration.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumerationLiteral.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorInvalidType.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorManager.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType.class,
		org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorVoidType.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorLambdaType.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleIterationManager.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleMapIterationManager.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleMapIterationManager.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorSpecializedType.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorType.class,
		org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter.class,
		org.eclipse.ocl.pivot.CollectionType.class,
		org.eclipse.ocl.pivot.Element.class,
		org.eclipse.ocl.pivot.ParameterTypes.class,
		org.eclipse.ocl.pivot.PivotPackage.class,
		org.eclipse.ocl.pivot.Property.class,
		org.eclipse.ocl.pivot.StandardLibrary.class,
		org.eclipse.ocl.pivot.TupleType.class,
		org.eclipse.ocl.pivot.Type.class,
		org.eclipse.ocl.pivot.TypedElement.class,
		org.eclipse.ocl.pivot.TemplateParameters.class,
		org.eclipse.ocl.pivot.evaluation.Executor.class,
		org.eclipse.ocl.pivot.ids.ClassId.class,
		org.eclipse.ocl.pivot.ids.CollectionTypeId.class,
		org.eclipse.ocl.pivot.ids.DataTypeId.class,
		org.eclipse.ocl.pivot.ids.EnumerationId.class,
		org.eclipse.ocl.pivot.ids.IdManager.class,
		org.eclipse.ocl.pivot.ids.PackageId.class,
		org.eclipse.ocl.pivot.ids.PrimitiveTypeId.class,
		org.eclipse.ocl.pivot.ids.TemplateParameterId.class,
		org.eclipse.ocl.pivot.ids.TuplePartId.class,
		org.eclipse.ocl.pivot.ids.TupleTypeId.class,
		org.eclipse.ocl.pivot.ids.TypeId.class,
		org.eclipse.ocl.pivot.library.AbstractBinaryOperation.class,
		org.eclipse.ocl.pivot.library.AbstractProperty.class,
		org.eclipse.ocl.pivot.library.AbstractSimpleOperation.class,
		org.eclipse.ocl.pivot.library.AbstractTernaryOperation.class,
		org.eclipse.ocl.pivot.library.AbstractUnaryOperation.class,
		org.eclipse.ocl.pivot.library.LibraryBinaryOperation.class,
		org.eclipse.ocl.pivot.library.LibraryIteration.class,
		org.eclipse.ocl.pivot.library.LibraryProperty.class,
		org.eclipse.ocl.pivot.library.LibraryTernaryOperation.class,
		org.eclipse.ocl.pivot.library.LibraryUnaryOperation.class,
		org.eclipse.ocl.pivot.messages.PivotMessages.class,
		org.eclipse.ocl.pivot.values.BagValue.class,
		org.eclipse.ocl.pivot.values.CollectionValue.class,
		org.eclipse.ocl.pivot.values.IntegerRange.class,
		org.eclipse.ocl.pivot.values.IntegerValue.class,
		org.eclipse.ocl.pivot.values.InvalidValue.class,
		org.eclipse.ocl.pivot.values.MapValue.class,
		org.eclipse.ocl.pivot.values.OrderedSetValue.class,
		org.eclipse.ocl.pivot.values.RealValue.class,
		org.eclipse.ocl.pivot.values.SequenceValue.class,
		org.eclipse.ocl.pivot.values.SetValue.class,
		org.eclipse.ocl.pivot.values.TupleValue.class,
		org.eclipse.ocl.pivot.values.UnlimitedValue.class,
		org.eclipse.ocl.pivot.values.Value.class,
		org.eclipse.ocl.pivot.values.InvalidValueException.class,
		org.eclipse.ocl.pivot.utilities.ClassUtil.class,
		org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal.class,
		org.eclipse.ocl.pivot.utilities.ValueUtil.class,
		org.eclipse.osgi.util.NLS.class
	};

	public static void initPrimitive(Class<?> class1, Class<?> class2) {
		javaPrimitiveClasses.put(class1, class2);
		javaPrimitiveNames.put(class1.getName(), class2);
	}

	private /*@LazyNonNull*/ Id2EClassVisitor id2EClassVisitor = null;
	//	protected final @NonNull Id2JavaInterfaceVisitor id2JavaInterfaceVisitor;
	private /*@LazyNonNull*/ Id2BoxedDescriptorVisitor id2BoxedDescriptorVisitor = null;
	private /*@LazyNonNull*/ GlobalPlace globalPlace = null;
	private @NonNull Map<ElementId, BoxedDescriptor> boxedDescriptors = new HashMap<ElementId, BoxedDescriptor>();
	private /*@LazyNonNull*/ ASM5JavaAnnotationReader annotationReader = null;

	@Deprecated /* @deprecated pass a genmodel - necessary for UML support */
	public JavaCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this(environmentFactory, null);
	}

	public JavaCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel) {
		super(environmentFactory, genModel);
	}

	@Override
	public @NonNull BoxingAnalyzer createBoxingAnalyzer() {
		return new BoxingAnalyzer(getAnalyzer());
	}

	@Override
	public @NonNull CommonSubexpressionEliminator createCommonSubexpressionEliminator() {
		return new CommonSubexpressionEliminator(this);
	}

	public @NonNull CG2JavaPreVisitor createCG2JavaPreVisitor() {
		return new CG2JavaPreVisitor(getGlobalContext());
	}

	protected void createConstrainedOperations(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGClass cgClass) {
		Iterable<@NonNull Operation> constrainedOperations = getConstrainedOperations();
		if (constrainedOperations != null) {
			for (@NonNull Operation constrainedOperation : constrainedOperations) {		// FIXME recurse for nested calls
				CGNamedElement cgOperation = constrainedOperation.accept(as2cgVisitor);
				cgClass.getOperations().add((CGOperation)cgOperation);
			}
		}

	}

	@Override
	public @NonNull DependencyVisitor createDependencyVisitor() {
		return new JavaDependencyVisitor(getAnalyzer(), getGlobalContext(), getGlobalPlace());
	}

	@Override
	public @NonNull FieldingAnalyzer createFieldingAnalyzer() {
		return new FieldingAnalyzer(getAnalyzer());
	}

	@Override
	protected @NonNull GenModelHelper createGenModelHelper(@Nullable GenModel genModel) {
		return AbstractGenModelHelper.create(metamodelManager, genModel);
	}

	protected @NonNull Id2EClassVisitor createId2EClassVisitor() {
		return new Id2EClassVisitor(metamodelManager);
	}

	protected @NonNull Id2BoxedDescriptorVisitor createId2BoxedDescriptorVisitor() {
		return new Id2BoxedDescriptorVisitor(this);
	}

	@Override
	public @NonNull ImportNameManager createImportNameManager() {
		return new JavaImportNameManager();
	}

	public @NonNull JavaStream createJavaStream(@NonNull CG2JavaVisitor<@NonNull ?> cg2JavaVisitor) {
		return new JavaStream(this, cg2JavaVisitor);
	}

	@Override
	protected @NonNull NameManager createNameManager() {
		return new NameManager();
	}

	@Override
	@NonNull
	public ReferencesVisitor createReferencesVisitor() {
		return ReferencesVisitor.INSTANCE;
	}

	@Override
	public @NonNull BoxedDescriptor getBoxedDescriptor(@NonNull ElementId elementId) {
		BoxedDescriptor boxedDescriptor = boxedDescriptors.get(elementId);
		if (boxedDescriptor != null) {
			return boxedDescriptor;
		}
		boxedDescriptor = elementId.accept(getId2BoxedDescriptorVisitor());
		assert boxedDescriptor != null;
		boxedDescriptors.put(elementId, boxedDescriptor);
		return boxedDescriptor;
	}

	public @NonNull CGModelResourceFactory getCGResourceFactory() {
		return CG_RESOURCE_FACTORY;
	}

	@Override
	public @Nullable String getConstantsClass() {
		return null;
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull ElementId elementId, @Nullable Class<?> instanceClass) {
		BoxedDescriptor boxedDescriptor = getBoxedDescriptor(elementId);
		return boxedDescriptor.getEcoreDescriptor(this, instanceClass);
	}

	@Override
	public abstract @NonNull JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> getGlobalContext();

	@Override
	public @NonNull GlobalPlace getGlobalPlace() {
		GlobalPlace globalPlace2 = globalPlace;
		if (globalPlace2 == null) {
			globalPlace = globalPlace2 = new GlobalPlace(getAnalyzer());
		}
		return globalPlace2;
	}

	public @NonNull Id2BoxedDescriptorVisitor getId2BoxedDescriptorVisitor() {
		Id2BoxedDescriptorVisitor id2BoxedDescriptorVisitor2 = id2BoxedDescriptorVisitor;
		if (id2BoxedDescriptorVisitor2 == null) {
			id2BoxedDescriptorVisitor = id2BoxedDescriptorVisitor2 = createId2BoxedDescriptorVisitor();
		}
		return id2BoxedDescriptorVisitor2;
	}

	public @NonNull Id2EClassVisitor getId2EClassVisitor() {
		Id2EClassVisitor id2EClassVisitor2 = id2EClassVisitor;
		if (id2EClassVisitor2 == null) {
			id2EClassVisitor = id2EClassVisitor2 = createId2EClassVisitor();
		}
		return id2EClassVisitor2;
	}

	/**
	 * Return true for an @NonNull annotation, false for an @Nullable annotation, null otherwise.
	 */
	public @Nullable Boolean getIsNonNull(@NonNull Method method) {
		if (annotationReader == null) {
			annotationReader = new ASM5JavaAnnotationReader();
		}
		return annotationReader.getIsNonNull(method);
	}

	@Override
	public @Nullable Iteration2Java getIterationHelper(@NonNull Iteration asIteration) {
		LibraryIteration libraryIteration = (LibraryIteration) metamodelManager.getImplementation(asIteration);
		if (asIteration.getOwnedIterators().size() != 1) {
			return null;
		}
		if (libraryIteration instanceof AnyIteration) {
			return AnyIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof CollectIteration) {
			return CollectIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof CollectNestedIteration) {
			return CollectNestedIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof ExistsIteration) {
			return ExistsIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof ForAllIteration) {
			return ForAllIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof IsUniqueIteration) {
			return IsUniqueIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof IterateIteration) {
			return IterateIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof OneIteration) {
			return OneIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof RejectIteration) {
			return RejectIteration2Java.INSTANCE;
		}
		else if (libraryIteration instanceof SelectIteration) {
			return SelectIteration2Java.INSTANCE;
		}
		else {
			return null;			// closure, sortedBy
		}
	}

	public @Nullable Method getLeastDerivedMethod(@NonNull Class<?> requiredClass, @NonNull String getAccessor) {
		Method leastDerivedMethod = getLeastDerivedMethodInternal(requiredClass, getAccessor);
		if (leastDerivedMethod != null) {
			return leastDerivedMethod;
		}
		else {
			try {
				return requiredClass.getMethod(getAccessor);
			} catch (Throwable e) {
				return null;
			}
		}
	}

	private @Nullable Method getLeastDerivedMethodInternal(@NonNull Class<?> requiredClass, @NonNull String getAccessor) {
		Class<?> superClass = requiredClass.getSuperclass();
		if (superClass != null) {
			try {
				Method lessDerivedSuperMethod = getLeastDerivedMethodInternal(superClass, getAccessor);
				if (lessDerivedSuperMethod != null) {
					return lessDerivedSuperMethod;
				}
				Method method = superClass.getMethod(getAccessor);
				if (method != null) {
					return method;
				}
			} catch (Throwable e) {
			}
		}
		for (@SuppressWarnings("null")@NonNull Class<?> superInterface : requiredClass.getInterfaces()) {
			Method lessDerivedSuperMethod = getLeastDerivedMethodInternal(superInterface, getAccessor);
			if (lessDerivedSuperMethod != null) {
				return lessDerivedSuperMethod;
			}
			try {
				Method method = superInterface.getMethod(getAccessor);
				if (method != null) {
					return method;
				}
			} catch (Throwable e) {
			}
		}
		return null;
	}

	@Override
	public @NonNull TypeDescriptor getTypeDescriptor(@NonNull CGValuedElement cgElement) {
		CGTypeId typeId = cgElement.getTypeId();
		if (typeId == null) {
			typeId = cgElement.getTypeId();
		}
		CGTypeId cgTypeId = ClassUtil.nonNullState(typeId);
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		TypeDescriptor typeDescriptor = getBoxedDescriptor(elementId);
		if (cgElement.isEcore()) {
			EClassifier eClassifier = cgElement.getEcoreClassifier();
			Class<?> instanceClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
			typeDescriptor = typeDescriptor.getEcoreDescriptor(this, instanceClass);
		}
		else if (cgElement.isUnboxed()) {
			typeDescriptor = typeDescriptor.getUnboxedDescriptor(this);
		}
		if (maybePrimitive(cgElement)) {
			typeDescriptor = typeDescriptor.getPrimitiveDescriptor();
		}
		return typeDescriptor;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull ElementId elementId) {
		BoxedDescriptor boxedDescriptor = getBoxedDescriptor(elementId);
		return boxedDescriptor.getUnboxedDescriptor(this);
	}

	@Override
	public @Nullable Boolean isNonNull(@NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = asOperationCallExp.getReferredOperation();
		EObject eOperation = asOperation.getESObject();
		if (!(eOperation instanceof EOperation)) {
			return null;
		}
		CGTypeId cgTypeId = getAnalyzer().getTypeId(asOperation.getOwningClass().getTypeId());
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = getUnboxedDescriptor(elementId);
		String getAccessor = genModelHelper.getOperationAccessor(asOperation);
		Class<?> requiredJavaClass = requiredTypeDescriptor.hasJavaClass();
		if (requiredJavaClass == null) {
			return null;
		}
		Method leastDerivedMethod = getLeastDerivedMethod(requiredJavaClass, getAccessor);
		if (leastDerivedMethod == null) {
			return null;
		}
		return getIsNonNull(leastDerivedMethod) == Boolean.TRUE;
	}

	@Override
	public @Nullable Boolean isNonNull(@NonNull Property asProperty) {
		EObject eStructuralFeature = asProperty.getESObject();
		if (!(eStructuralFeature instanceof EStructuralFeature)) {
			return null;
		}
		CGTypeId cgTypeId = getAnalyzer().getTypeId(asProperty.getOwningClass().getTypeId());
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = getUnboxedDescriptor(elementId);
		String getAccessor = genModelHelper.getGetAccessor((EStructuralFeature)eStructuralFeature);
		Class<?> requiredJavaClass = requiredTypeDescriptor.hasJavaClass();
		if (requiredJavaClass == null) {
			return null;
		}
		Method leastDerivedMethod = getLeastDerivedMethod(requiredJavaClass, getAccessor);
		if (leastDerivedMethod == null) {
			return null;
		}
		return getIsNonNull(leastDerivedMethod) == Boolean.TRUE;
	}

	/**
	 * Return true is this is a built-in primitive type such as boolean or int.
	 * Such types cannot have @NonNull annotations.
	 */
	@Override
	public boolean isPrimitive(@NonNull CGValuedElement cgValue) {
		if (cgValue.getNamedValue().isCaught()) {
			return false;
		}
		TypeDescriptor typeDescriptor = getTypeDescriptor(cgValue);
		Class<?> javaClass = typeDescriptor.getJavaClass();		// FIXME Rationalize with TypeDescriptor.isPrimitive()
		if ((javaClass == boolean.class) || ((javaClass == Boolean.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == byte.class) || ((javaClass == Byte.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == char.class) || ((javaClass == Character.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == double.class) || ((javaClass == Double.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == float.class) || ((javaClass == Float.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == int.class) || ((javaClass == Integer.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == long.class) || ((javaClass == Long.class) && cgValue.isNonNull())) {
			return true;
		}
		if ((javaClass == short.class) || ((javaClass == Short.class) && cgValue.isNonNull())) {
			return true;
		}
		return false;
	}

	/**
	 * Return the null-ness of cgElement.
	 * - true for @NonNull
	 * - false for @Nullable
	 * - null for neither
	 */
	public @Nullable Boolean isRequired(@NonNull CGValuedElement cgElement) {
		//		boolean isPrimitive = isPrimitive(cgElement);
		//		boolean isRequired = !isPrimitive && !cgElement.isAssertedNonNull() && cgElement.isNonNull() && !(cgElement instanceof CGUnboxExp)/*|| cgElement.isRequired()*/;	// FIXME Ugh!
		//		Boolean isJavaRequired = isPrimitive ? null : isRequired;			// FIXME migrate isPrimitove to recipients
		//		return isJavaRequired;
		boolean isRequired = !cgElement.isAssertedNonNull() && cgElement.isNonNull() && !(cgElement instanceof CGUnboxExp)/*|| cgElement.isRequired()*/;	// FIXME Ugh!
		return isRequired;
	}

	@Override
	public boolean maybePrimitive(@NonNull CGValuedElement cgValue) {
		if (cgValue.getNamedValue().isCaught()) {
			return false;
		}
		else {
			return cgValue.isNonNull();
		}
	}

	/**
	 * Perform the overall optimization of the CG tree.
	 */
	protected void optimize(@NonNull CGPackage cgPackage) {
		CGModelResource resource = getCGResourceFactory().createResource(URI.createURI("cg.xmi"));
		resource.getContents().add(cgPackage);
		getAnalyzer().analyze(cgPackage);
		CG2JavaPreVisitor cg2PreVisitor = createCG2JavaPreVisitor();
		cgPackage.accept(cg2PreVisitor);
		for (CGElementId cgElementId : Lists.newArrayList(getAnalyzer().getCGElementIds())) {			// FIXME Avoid test_allInstances CME
			cgElementId.accept(cg2PreVisitor);
		}
		CommonSubexpressionEliminator cseEliminator = createCommonSubexpressionEliminator();
		cseEliminator.optimize(cgPackage);
	}

	/**
	 * After overall optimization, return a sorted list of global declarations.
	 */
	public @Nullable List<@NonNull CGValuedElement> prepareGlobals() {
		DependencyVisitor dependencyVisitor = createDependencyVisitor();
		Collection<@NonNull CGValuedElement> globals = getGlobalContext().getGlobals();
		for (@NonNull CGValuedElement cgGlobal : globals) {
			assert cgGlobal.isGlobal();
		}
		dependencyVisitor.visitAll(globals);
		List<@NonNull CGValuedElement> sortedGlobals = getGlobalPlace().getSortedGlobals(dependencyVisitor);
		return sortedGlobals;
	}
}
