/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.DependencyVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.ReferencesVisitor;
import org.eclipse.ocl.examples.codegen.asm5.ASM5JavaAnnotationReader;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
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
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ClassableNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.naming.NameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.NestedNameManager;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils.CodeGenString;
import org.eclipse.ocl.examples.codegen.utilities.AbstractCGModelResourceFactory;
import org.eclipse.ocl.examples.codegen.utilities.CGModelResourceFactory;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
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
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;

import com.google.common.collect.Iterables;
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
	private /*@LazyNonNull*/ JavaLanguageSupport javaLanguageSupport = null;

	private final @NonNull NameVariant BODY_NameVariant;
	private final @NonNull NameVariant IMPL_NameVariant;
	private final @NonNull NameVariant ITER_NameVariant;
	private final @NonNull NameVariant MGR_NameVariant;
	private final @NonNull NameVariant THROWN_NameVariant;
	private final @NonNull NameVariant TYPE_NameVariant;

	@Deprecated /* @deprecated pass a genmodel - necessary for UML support */
	public JavaCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this(environmentFactory, null);
	}

	public JavaCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel) {
		super(environmentFactory, genModel);
		BODY_NameVariant = globalNameManager.addNameVariantPrefix("BODY_");
		IMPL_NameVariant = globalNameManager.addNameVariantPrefix("IMPL_");
		ITER_NameVariant = globalNameManager.addNameVariantPrefix("ITER_");
		MGR_NameVariant = globalNameManager.addNameVariantPrefix("MGR_");
		THROWN_NameVariant = globalNameManager.addNameVariantPrefix("THROWN_");
		TYPE_NameVariant = globalNameManager.addNameVariantPrefix("TYPE_");
	}

	@Override
	public @NonNull AS2CGVisitor createAS2CGVisitor(@NonNull CodeGenAnalyzer codeGenAnalyzer) {
		return new AS2CGVisitor(codeGenAnalyzer);
	}

	@Override
	public @NonNull BoxingAnalyzer createBoxingAnalyzer() {
		return new BoxingAnalyzer(getAnalyzer());
	}

	@Override
	public @NonNull CommonSubexpressionEliminator createCommonSubexpressionEliminator() {
		return new CommonSubexpressionEliminator(this);
	}

	public @NonNull CG2JavaNameVisitor createCG2JavaNameVisitor() {
		return new CG2JavaNameVisitor(this);
	}

	public @NonNull CG2JavaPreVisitor createCG2JavaPreVisitor() {
		return new CG2JavaPreVisitor(this);
	}

	public @NonNull ClassNameManager createClassNameManager(@NonNull ClassableNameManager outerNameManager, @NonNull CGClass cgClass) {
		return new ClassNameManager(this, outerNameManager, cgClass);
	}

	protected @NonNull CodeGenAnalyzer createCodeGenAnalyzer() {
		return new CodeGenAnalyzer(this);
	}

	protected void createConstrainedOperations(@NonNull CGClass cgClass) {
		Iterable<@NonNull Operation> constrainedOperations = getConstrainedOperations();
		if (constrainedOperations != null) {
			CodeGenAnalyzer analyzer2 = getAnalyzer();
			for (@NonNull Operation constrainedOperation : constrainedOperations) {		// FIXME recurse for nested calls
			//	CGNamedElement cgOperation = constrainedOperation.accept(as2cgVisitor);
				CGOperation cgOperation = analyzer2.createCGElement(CGOperation.class, constrainedOperation);
				cgClass.getOperations().add(cgOperation);
			}
		}
	}

	@Override
	public @NonNull DependencyVisitor createDependencyVisitor() {
		return new JavaDependencyVisitor(this, getGlobalPlace());
	}

	public @NonNull ExecutableNameManager createConstraintNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		return new ExecutableNameManager(classNameManager, classNameManager, cgConstraint, null);
	}

	public @NonNull ExecutableNameManager createLoopNameManager(@NonNull ClassNameManager classNameManager, @NonNull ExecutableNameManager parentNameManager, @NonNull CGIterationCallExp cgIterationCallExp) {
		return new ExecutableNameManager(classNameManager, parentNameManager, cgIterationCallExp, null);
	}

	public @NonNull ExecutableNameManager createOperationNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation, @Nullable TypedElement asOrigin) {
		return new ExecutableNameManager(classNameManager, classNameManager, cgOperation, asOrigin);
	}

	public @NonNull ExecutableNameManager createPropertyNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		return new ExecutableNameManager(classNameManager, classNameManager, cgProperty, null);
	}

	@Override
	public @NonNull FieldingAnalyzer createFieldingAnalyzer() {
		return new FieldingAnalyzer(getAnalyzer());
	}

	@Override
	protected @NonNull GenModelHelper createGenModelHelper(@Nullable GenModel genModel) {
		return AbstractGenModelHelper.create(metamodelManager, genModel);
	}

	@Override
	protected @NonNull GlobalNameManager createGlobalNameManager() {
		return new GlobalNameManager(this, createNameManagerHelper());
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

	public @NonNull JavaStream createJavaStream(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull ImportNameManager importNameManager) {
		return new JavaStream(this, cg2javaVisitor, importNameManager);
	}

	protected @NonNull NameManagerHelper createNameManagerHelper() {
		return new NameManagerHelper();
	}

	public @NonNull PackageNameManager createPackageNameManager(@Nullable PackageNameManager outerNameManager, @NonNull CGPackage cgPackage) {
		if (outerNameManager != null) {
			return new PackageNameManager(this, outerNameManager, cgPackage);
		}
		else {
			return new PackageNameManager(this, globalNameManager, cgPackage);
		}
	}

	@Override
	public @NonNull ReferencesVisitor createReferencesVisitor() {
		return ReferencesVisitor.INSTANCE;
	}

	/**
	 * Diagnose the over-earger declaration of element names that can inhibit the sharing of
	 * a name and consequently a CGEd variable for a shared value. In principle names should only
	 * be declared when really needed, e.g. for a let-variable or global.
	 */
	protected boolean debugCheckNameResolution(@NonNull CGValuedElement cgElement, @NonNull NameResolution nameResolution) {
		if ((cgElement instanceof CGConstraint) || (cgElement instanceof CGOperation) || (cgElement instanceof CGProperty) || (cgElement instanceof CGVariable)) {
			// ok declaration has own name
		}
	//	else if (cgElement instanceof CGCatchExp) {
	//		NameResolution sourceNameResolution = ((CGCatchExp)cgElement).getSource().basicGetNameResolution();
	//		assert sourceNameResolution == nameResolution.getNameResolution();
	//	}
	//	else if (nameResolution != nameResolution.getNameResolution()) {
	//		// variant is ok
	//	}
	//	else if (nameResolution.hasVariants()) {
	//		// with-variants is ok
	//	}
		else if (cgElement instanceof CGConstantExp) {
			NameResolution referredNameResolution = ((CGConstantExp)cgElement).getReferredValue().basicGetNameResolution();
			assert referredNameResolution == nameResolution;
		}
		else if (cgElement instanceof CGGuardExp) {
			NameResolution sourceNameResolution = ((CGGuardExp)cgElement).getSource().basicGetNameResolution();
			assert sourceNameResolution == nameResolution;
		}
		else if (cgElement instanceof CGVariableExp) {
			NameResolution variableNameResolution = ((CGVariableExp)cgElement).getReferredValue().basicGetNameResolution();
			assert (variableNameResolution == nameResolution) || (variableNameResolution == nameResolution);
		}
		else {
			EObject eContainer = cgElement.eContainer();
			if (cgElement.isGlobal() || (eContainer == null)) {
				// ok -global or root
			}
			else if (eContainer instanceof CGCatchExp) {
				NameResolution catchResolution = ((CGCatchExp)eContainer).basicGetNameResolution();
				assert (catchResolution != null) && (catchResolution == nameResolution);
			}
			else if (eContainer instanceof CGGuardExp) {
				NameResolution guardResolution = ((CGGuardExp)eContainer).basicGetNameResolution();
				assert guardResolution == nameResolution;
			}
			else if ((eContainer instanceof CGIterationCallExp) && (cgElement.eContainmentFeature() == CGModelPackage.Literals.CG_SOURCED_CALL_EXP__SOURCE)) {
				// itetation outer source ok
			}
			else if (eContainer instanceof CGVariable) {
				NameResolution containerNameResolution = ((CGValuedElement)eContainer).basicGetNameResolution();
				if (containerNameResolution != containerNameResolution) {			// XXX redundant / typo
					System.out.println("Bad " + NameUtil.debugSimpleName(nameResolution) + " for " + NameUtil.debugSimpleName(cgElement) + " below " + NameUtil.debugSimpleName(((CGValuedElement)eContainer).basicGetNameResolution()) + " for " + NameUtil.debugSimpleName(eContainer));		// XXX YYY
				}
				assert (nameResolution == containerNameResolution);
			}
			else {
			//	assert false : "Unexpected nameResolution for a " + cgValuedElement2.eClass().getName();		// XXX YYY
				System.out.println("Unexpected " + NameUtil.debugSimpleName(nameResolution) + " for " + NameUtil.debugSimpleName(cgElement) + " below " + NameUtil.debugSimpleName(((CGValuedElement)eContainer).basicGetNameResolution()) + " for " + NameUtil.debugSimpleName(eContainer));		// XXX YYY
				getClass();
			}
		}
		return true;
	}

	// Fold into visitInPostOrder
	private void gatherNames(@NonNull CGValuedElement cgElement, @NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		if (cgElement.eClass().getName().equals("CGExecutorType")) {
			getClass();		// XXX
		}
		if (!cgElement.isInlined()) {
			NameResolution nameResolution = cgElement.basicGetNameResolution();
			if (nameResolution == null) {
				Iterable<@NonNull CGValuedElement> extraChildParents = getAnalyzer().getExtraChildParents(cgElement);
				if (extraChildParents != null) {
					getClass();		// XXX
					for (@NonNull CGValuedElement extraChildParent : extraChildParents) {
						Iterable<@NonNull CGValuedElement> extraChildParentChildren = getAnalyzer().getExtraChildElements(extraChildParent);
						Iterable<@NonNull CGValuedElement> extraChildGrandParents = getAnalyzer().getExtraChildParents(extraChildParent);
						if (extraChildGrandParents != null) {
							for (@NonNull CGValuedElement extraChildGrandParent : extraChildGrandParents) {
								Iterable<@NonNull CGValuedElement> extraChildGreatGrandParents = getAnalyzer().getExtraChildParents(extraChildGrandParent);
								getClass();		// XXX
							}

						}
					}
				}
			}
			assert nameResolution != null;
			NameManager nameManager = nameResolution.getNameManager();
			if (NameResolution.NAMES_GATHER.isActive()) {
				NameResolution.NAMES_GATHER.println(NameUtil.debugSimpleName(cgElement) + " : " + NameUtil.debugSimpleName(nameResolution) + " in " + NameUtil.debugSimpleName(nameManager));
			}
			List<@NonNull CGValuedElement> namedElements = nameManager2namedElements.get(nameManager);
			if (namedElements == null) {
				namedElements = new ArrayList<>();
				nameManager2namedElements.put(nameManager, namedElements);
			}
			namedElements.add(cgElement);
		}
		else {
			if (NameResolution.NAMES_GATHER.isActive()) {
				NameResolution.NAMES_GATHER.println(NameUtil.debugSimpleName(cgElement) + " inlined");
			}
		}
		Iterable<@NonNull CGValuedElement> extraChildElements = getAnalyzer().getExtraChildElements(cgElement);
		if (extraChildElements != null) {
			for (@NonNull CGValuedElement cgExtraChild : extraChildElements) {
			//	assert !cgExtraChild.isInlined();
				gatherNames(cgExtraChild, nameManager2namedElements);
			}
		}
	}

	public @NonNull NameVariant getBODY_NameVariant() {
		return BODY_NameVariant;
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

	public org.eclipse.ocl.pivot.@NonNull Class getContextClass() {				// XXX ?? use analyzer support
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull ElementId elementId, @Nullable Class<?> instanceClass) {
		BoxedDescriptor boxedDescriptor = getBoxedDescriptor(elementId);
		return boxedDescriptor.getEcoreDescriptor(this, instanceClass);
	}

/*	@Override
	public @NonNull String getExternalClassName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CodeGenString s = new CodeGenString(environmentFactory.getMetamodelManager(), false);
		s.append(JavaConstants.EXTERNAL_CLASS_PREFIX);
		s.appendAndEncodeQualifiedName(asClass);
		return s.toString();
	} */

	@Override
	public @NonNull GlobalPlace getGlobalPlace() {
		GlobalPlace globalPlace2 = globalPlace;
		if (globalPlace2 == null) {
			globalPlace = globalPlace2 = new GlobalPlace(getAnalyzer());
		}
		return globalPlace2;
	}

	public @NonNull NameVariant getIMPL_NameVariant() {
		return IMPL_NameVariant;
	}

	public @NonNull NameVariant getITER_NameVariant() {
		return ITER_NameVariant;
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

	public @Nullable Boolean getIsNonNull(@NonNull Method method, int parameter) {
		if (annotationReader == null) {
			annotationReader = new ASM5JavaAnnotationReader();
		}
		return annotationReader.getIsNonNull(method, parameter);
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

	public @NonNull LanguageSupport getLanguageSupport() {
		JavaLanguageSupport javaLanguageSupport2 = javaLanguageSupport;
		if (javaLanguageSupport2 == null) {
			javaLanguageSupport = javaLanguageSupport2 = (JavaLanguageSupport)environmentFactory.getLanguageSupport("java");
			assert javaLanguageSupport2 != null;
		}
		return javaLanguageSupport2;
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

	public @NonNull NameVariant getMGR_NameVariant() {
		return MGR_NameVariant;
	}

/*	@Override
	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution(); //.getNameVariant(guardedNameVariant);
		if (nameResolution == null) {
			NestedNameManager nameManager = globalNameManager.findNestedNameManager(cgElement);
			nameResolution = nameManager.declareLazyName2(cgElement);
		}
		return nameResolution;
	} */

//	@Override
	public @NonNull String getNestedClassName(org.eclipse.ocl.pivot.@NonNull Feature asFeature) {
		CodeGenString s = new CodeGenString(environmentFactory.getMetamodelManager(), false);
		s.append(JavaConstants.NESTED_CLASS_PREFIX);
		s.appendAndEncodeQualifiedName(asFeature);
		return s.toString();
	}

	@Deprecated /* @deprecated obsolete approach */
	public @NonNull String getQualifiedForeignClassName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		assert false : "Unsupported getQualifiedForeignClassName";
		return PivotUtil.getName(asClass);
	}

	public @NonNull String getRequalifiedClassName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return AbstractLanguageSupport.getQualifiedName(asClass);
	}

	public @NonNull String getRequalifiedClassName(@NonNull String qualifiedClassName) {
		return qualifiedClassName;
	}

	public @NonNull NameVariant getTHROWN_NameVariant() {
		return THROWN_NameVariant;
	}

	public @NonNull NameVariant getTYPE_NameVariant() {
		return TYPE_NameVariant;
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
		CGTypeId cgTypeId = getAnalyzer().getCGTypeId(asOperation.getOwningClass().getTypeId());
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
		CGTypeId cgTypeId = getAnalyzer().getCGTypeId(asProperty.getOwningClass().getTypeId());
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
		boolean isNonNull = cgValue.isRequiredOrNonNull();
		return JavaLanguageSupport.isPrimitive(isNonNull, javaClass);
	}

	/**
	 * Return the null-ness of cgElement.
	 * - true for @NonNull
	 * - false for @Nullable
	 * - null for neither
	 */
	public @Nullable Boolean isRequired(@NonNull CGValuedElement cgElement) {
	//	invalid has no effect on declared requiredness
/*		if (cgElement.isNull()) {				// definitely null
			assert !cgElement.isRequired() : "null for isRequired";
			return Boolean.FALSE;
		}
		else if (cgElement.isNonNullUnchecked()) {		// definitely not null
			return Boolean.TRUE;
		}
		else {
		//	assert !cgElement.isRequired();
			return null;
		} */
		return cgElement.isRequired();
	}

	@Override
	public boolean maybePrimitive(@NonNull CGValuedElement cgValue) {
		if (cgValue instanceof CGOperation) {
			if (!((CGOperation)cgValue).maybePrimitive()) {
				return false;
			}
		}
		if (cgValue.getNamedValue().isCaught()) {
			return false;
		}
		else {
			return cgValue.isRequiredOrNonNull(); // need to see whether Java uses e.g boolean // Required();
		}
	}

	/**
	 * Perform the overall optimization of the CG tree culminating in CSE rewrites.
	 */
	protected void optimize(@NonNull CGPackage cgPackage) {
		optimize(Collections.singletonList(cgPackage));
	}

	/**
	 * Perform the overall optimization of the CG tree culminating in CSE rewrites.
	 */
	protected void optimize(@NonNull Iterable<@NonNull CGPackage> cgPackages) {
		NameResolution.inhibitNameResolution = true;
	//	CGModelResource resource = getCGResourceFactory().createResource(URI.createURI("cg.xmi"));
	//	for (@NonNull CGPackage cgPackage : cgPackages) {
	//		resource.getContents().add(EcoreUtil.getRootContainer(cgPackage));								// Nested GenModel has nested 'roots'
	//	}
		CodeGenAnalyzer analyzer = getAnalyzer();
		analyzer.analyze(cgPackages);
		CG2JavaPreVisitor cg2PreVisitor = createCG2JavaPreVisitor();
		cg2PreVisitor.prepare(cgPackages);
		for (CGElementId cgElementId : Lists.newArrayList(analyzer.getCGElementIds())) {			// FIXME Avoid test_allInstances CME
			cgElementId.accept(cg2PreVisitor);
		}
		CommonSubexpressionEliminator cseEliminator = createCommonSubexpressionEliminator();
		for (@NonNull CGPackage cgPackage : cgPackages) {
			cseEliminator.optimize(cgPackage);
		}
	}

	/**
	 * Perform additional analysis of the final optimized CG tree; no rewrites occur.
	 * Globals are identified and a hierarchy of name spaces is established to ensure that hierarchy unique names can be resolved.
	 */
	protected @Nullable Iterable<@NonNull CGValuedElement> pregenerate(@NonNull Iterable<@NonNull CGPackage> cgRootPackages) {
		CG2JavaNameVisitor cg2nameVisitor = createCG2JavaNameVisitor();
		UniqueList<@NonNull CGValuedElement> cgGlobals = null;
		for (@NonNull CGPackage cgRootPackage : cgRootPackages) {
			cgRootPackage.accept(cg2nameVisitor);
		}
		Iterable<@NonNull CGValuedElement> sortedGlobals = prepareGlobals();
		resolveUniqueNames(sortedGlobals, cgRootPackages);		// XXX cgRootPackages
		if (sortedGlobals != null) {
			if (cgGlobals == null) {
				cgGlobals = new UniqueList<>();
			}
			Iterables.addAll(cgGlobals, sortedGlobals);
		}
		return cgGlobals;
	}
	protected @Nullable Iterable<@NonNull CGValuedElement> pregenerate(@NonNull CGPackage cgPackage) {
		return pregenerate(Collections.singletonList(cgPackage));
	}

	/**
	 * After overall optimization, return a sorted list of global declarations.
	 */
	private @Nullable List<@NonNull CGValuedElement> prepareGlobals() {
		DependencyVisitor dependencyVisitor = createDependencyVisitor();
		Collection<@NonNull CGValuedElement> globals = globalNameManager.getGlobals();
		for (@NonNull CGValuedElement cgGlobal : globals) {
			assert cgGlobal.isGlobal();
		}
		dependencyVisitor.visitAll(globals);
		List<@NonNull CGValuedElement> sortedGlobals = getGlobalPlace().getSortedGlobals(dependencyVisitor);
		return sortedGlobals;
	}

	protected void propagateChildNameResolution(@NonNull CGElement cgElement, @NonNull CGElement cgChild, @Nullable EReference eContainmentFeature, @Nullable NameResolution parentNameResolution) {
		if (eContainmentFeature == CGModelPackage.Literals.CG_VARIABLE__INIT) {
			CGVariable cgVariable = (@NonNull CGVariable)cgElement;
			NameResolution nameResolution = cgVariable.basicGetNameResolution();
			if (nameResolution == null) {
				NestedNameManager nestedNameManager = globalNameManager.useSelfNestedNameManager(cgVariable);
				nameResolution = nestedNameManager.getNameResolution(cgVariable);
			}
			propagateNameResolution(cgChild, nameResolution);
		}
		else if (eContainmentFeature == CGModelPackage.Literals.CG_LET_EXP__INIT) {
			propagateNameResolution(cgChild, null);
		}
		else if (eContainmentFeature == CGModelPackage.Literals.CG_LET_EXP__IN) {
			propagateNameResolution(cgChild, parentNameResolution);
		}
		else if ((eContainmentFeature == CGModelPackage.Literals.CG_SOURCED_CALL_EXP__SOURCE) && (cgElement instanceof CGGuardExp)) {
			propagateNameResolution(cgChild, parentNameResolution);	// Guard is an if predicate name re-use
		}
		else {
			propagateNameResolution(cgChild, null);
		}
	}

	/**
	 * Propagate the parent name hint down to the descendants of cgElement so that initializers for variables use a name
	 * based on the user's name for the variable rather than a totally synthetic name for the functionality.
	 *
	 * If parentNameResolution is null no hint is available.
	 *
	 * If the child already has an eager name such as an Ecore parameter, then that name must be preferred.
	 */
	protected void propagateNameResolution(@NonNull CGElement cgElement, @Nullable NameResolution parentNameResolution) {
	//	System.out.println("propagateNameResolution " + NameUtil.debugSimpleName(cgElement) + ":" +  cgElement);
		if (cgElement.eClass().getName().equals("CGExecutorShadowPart")) {
			getClass();		// XXX
		}
		for (EObject eObject : cgElement.eContents()) {					// XXX Surely preorder - no post order to satisfy bottom up dependency evaluation
			if (eObject instanceof CGElement) {
				CGElement cgChild = (CGElement)eObject;
				EReference eContainmentFeature = cgChild.eContainmentFeature();
				assert eContainmentFeature != null;
				propagateChildNameResolution(cgElement, cgChild, eContainmentFeature, parentNameResolution);
			}
		}
		if (cgElement instanceof CGValuedElement) {
			Iterable<@NonNull CGValuedElement> extraChildElements = getAnalyzer().getExtraChildElements((CGValuedElement)cgElement);
			if (extraChildElements != null) {
				for (@NonNull CGValuedElement cgExtraChild : extraChildElements) {
					if (cgExtraChild.eClass().getName().equals("CGExecutorType")) {
						getClass();		// XXX
					}
				//	assert !cgExtraChild.isInlined();
					propagateChildNameResolution(cgElement, cgExtraChild, null, parentNameResolution);
				}
			}
		}
		if (cgElement instanceof CGValuedElement) {
			if (cgElement.eClass().getName().equals("CGExecutorType")) {
				getClass();		// XXX
			}
			CGValuedElement cgValuedElement2 = (CGValuedElement)cgElement;
			if (!cgValuedElement2.isInlined()) {
				NameResolution nameResolution = cgValuedElement2.basicGetNameResolution();
				if (nameResolution == null) {
					if (cgValuedElement2.isGlobal()) {
						nameResolution = globalNameManager.getNameResolution(cgValuedElement2);
					}
					else if (parentNameResolution != null) {
						parentNameResolution.addCGElement(cgValuedElement2);
					}
					else {
						NameManager nameManager = globalNameManager.useSelfNameManager(cgValuedElement2);
						nameResolution = nameManager.getNameResolution(cgValuedElement2);
					}
				}
				if (nameResolution != null) {
					nameResolution.resolveNameHint();
				}
			}
			CGValuedElement cgValuedElement5 = ((CGValuedElement)cgElement).getReferencedExtraChild(getAnalyzer());
			if (cgValuedElement5 != null) {
				propagateNameResolution(cgValuedElement5, null);
				if ((cgValuedElement5.basicGetNameResolution() == null) && !cgValuedElement5.isInlined()) {
					NestedNameManager localNameManager = globalNameManager.useSelfExecutableNameManager(cgValuedElement5);
					localNameManager.getNameResolution(cgValuedElement5);		// XXX redundant ??
				}
			}
		/*	for (EObject eObject : ((CGValuedElement)cgElement).getNotOwns()) {					// XXX Surely preorder - no post order to satisfy bottom up dependency evaluation
				if (eObject instanceof CGElement) {
					propagateNameResolution((CGElement)eObject, null);
					if (eObject instanceof CGValuedElement) {
						CGValuedElement cgValuedElement = (CGValuedElement)eObject;
						if ((cgValuedElement.basicGetNameResolution() == null) && !cgValuedElement.isInlined()) {
							NestedNameManager localNameManager = globalNameManager.useSelfExecutableNameManager(cgValuedElement);
							localNameManager.getNameResolution(cgValuedElement);		// XXX redundant ??
						}
					}
				}
			} */
		}
	}

	/**
	 * Propagate name hints down the cgElement hierarchy so that initializers for variables use a name
	 * based on the user's name for the variable rather than a totally synthetic name for the functionality.
	 */
	protected void propagateNames(@NonNull CGElement cgElement) {
		propagateNameResolution(cgElement, null);
		for (EObject eObject : new TreeIterable(cgElement, true)) {		// XXX debugging
			if (eObject instanceof CGValuedElement) {
				CGValuedElement cgValuedElement = (CGValuedElement)eObject;
				if (!cgValuedElement.isInlined()) {
					NameResolution nameResolution = cgValuedElement.basicGetNameResolution();
					assert nameResolution != null;
				}
				CGValuedElement cgValuedElement2 = cgValuedElement.getReferencedExtraChild(getAnalyzer());
				if (cgValuedElement2 != null) {
					NameResolution nameResolution = cgValuedElement2.basicGetNameResolution();
					assert nameResolution != null;
				}
			}
		}
	}

	/**
	 * Resolve the name hints as hierarchically unique names throughout the sortedGlobals and cgPackage hierarchy.
	 */
	private void resolveUniqueNames(@Nullable Iterable<@NonNull CGValuedElement> sortedGlobals, @NonNull Iterable<@NonNull CGPackage> cgRootPackages) {
		NameResolution.inhibitNameResolution = false;
		//
		//	Propagate the NameResolutions down and up the hierarchy to facilitate sharing of future names.
		//	Resolve each NameResolution.nameHint.
		//
		if (sortedGlobals != null) {
			for (@NonNull CGValuedElement global : sortedGlobals) {
				if (global instanceof CGTupleExp) {
					getClass();		// XXX
				}
				// too soon assert global.getNameResolution().getNameManager().isGlobal();
				propagateNames(global);
			}
		}
		for (@NonNull CGPackage cgRootPackage : cgRootPackages) {
			propagateNames(cgRootPackage);
		}
		//
		//	Gather all the NameResolutions.
		//
		Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements = new HashMap<>();
		if (sortedGlobals != null) {
			for (@NonNull CGNamedElement cgNamedElement : sortedGlobals) {
				if (cgNamedElement instanceof CGValuedElement) {
					gatherNames((CGValuedElement)cgNamedElement, nameManager2namedElements);
				}
			}
		}
		for (@NonNull CGPackage cgRootPackage : cgRootPackages) {
			for (EObject eObject : new TreeIterable(cgRootPackage, true)) {		// XXX debugging
				if (eObject instanceof CGValuedElement) {
					gatherNames((CGValuedElement)eObject, nameManager2namedElements);
				}
			}
		}
		//
		//	Assign the hierarchically distinct resolvedName to each NameResolution from its nameHint.
		//
		globalNameManager.assignNames(nameManager2namedElements);
		//
		//	Debug traversal to confirm that all names have been resolved.
		//
		for (@NonNull CGPackage cgRootPackage : cgRootPackages) {
			for (EObject eObject : new TreeIterable(cgRootPackage, true)) {		// XXX debugging
				if (eObject instanceof CGValuedElement) {
					CGValuedElement cgElement = (CGValuedElement)eObject;
					if (!cgElement.isInlined()) {
						if (cgElement.basicGetNameResolution() == null) {
							System.out.println("Missing NameResolution for " + cgElement.eClass().getName() + " : " + cgElement);
						}
					}
				/*	if (cgElement instanceof CGLetExp) {
						if (!cgElement.isInlined()) {
					//		assert !cgElement.isInlined();
						}
						CGValuedElement cgIn = CGUtil.getIn((CGLetExp)cgElement);
						System.out.println("NameResolution for " + NameUtil.debugSimpleName(cgElement) + " : " + NameUtil.debugSimpleName(cgElement.basicGetNameResolution()));
						System.out.println(" in " + NameUtil.debugSimpleName(cgIn) + " : " + NameUtil.debugSimpleName(cgIn.basicGetNameResolution()));
					} */
				}
			}
		}
		NameResolution.inhibitNameResolution = true;
	}
}
