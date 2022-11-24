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
package org.eclipse.ocl.examples.codegen.analyzer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.ClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ExternalClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.VirtualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ClassableNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.NestedNameManager;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.SpecializedId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;

/**
 * A CodeGenAnalyzer performs the analysis of a Pivot AST in preparation for code generation.
 * <p>
 * Pass 1: AS2CGAnalysisVisitor
 * <br>
 * Each AS Element is converted to a CGElement
 * <br>
 * This conversion creates objects such as CGLibraryOperationCallEXp that are more atuned to CG
 * and provides a tree that can be rewritten by optimizations.
 * <p>
 * Pass 2: CGPreAnalysisVisitor
 * <br>
 * Traversal of the CG containment tree performs
 * <br>
 * constant folding
 * <p>
 * <p>
 * Pass N-2: CG2JavaPreVisitor
 * <br>
 * Traversal of the CG containment tree prepares for Java CG by
 * <br>
 * gathering imports
 * <p>
 * Pass N-1: CommonSubexpressionEliminator
 * <br>
 * Traversal of the CG tree to share common terms and remove dead code
 * <p>
 * Pass N: CG2JavaVisitor
 * <br>
 * Traversal of the CG containment tree emits code
 */
public class CodeGenAnalyzer
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull CompleteModel completeModel;
	protected final @NonNull StandardLibraryInternal standardLibrary;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull GenModelHelper genModelHelper;
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull AS2CGVisitor as2cgVisitor;

	private final @NonNull Map<@NonNull ElementId, @NonNull CGElementId> cgElementIds = new HashMap<>();
	protected final @NonNull CGBoolean cgFalse;
	protected final @NonNull CGBoolean cgTrue;
	private /*@LazyNonNull*/ CGUnlimited cgUnlimited = null;
	private /*@LazyNonNull*/ CGInvalid cgInvalid = null;
	protected final @NonNull CGNull cgNull;
	private final @NonNull Map<@NonNull Number, @NonNull CGInteger> cgIntegers = new HashMap<>();
	private final @NonNull Map<@NonNull Number, @NonNull CGReal> cgReals = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull CGString> cgStrings = new HashMap<>();
	private /*@LazyNonNull*/ Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis = null;

	/**
	 * The parent for nested CGClass instances. When non-null newly created CGClass instances are assigned to be nested
	 * within the CGClass, rather than within the CGPackage of the containing AS Package of the corresponding AS Class.
	 * (Nested CGClasses are synthesized for caches and virtual dispatchers.
	 *
	 * The Pivot AS does not support nested classes requiring that the AS for synthesized classes use flattened names
	 * siblings which makes it difficult to automatically nested them during AS2CG. A non-null asRootClass resolves this.
	 */
	private @NonNull Map<@NonNull CompletePackage, org.eclipse.ocl.pivot.@NonNull Class> completePackage2asRootClass = new HashMap<>();
	private org.eclipse.ocl.pivot.@Nullable Class asCurrentRootClass = null;

	/**
	 * Map of the directly nested classes of cgRootClass.
	 */
	@Deprecated /* surely there is now a full regular CGPackage hierarchy  */
	private final @NonNull Map <@NonNull String, @NonNull CGClass> name2cgNestedClass = new HashMap<>();

	/**
	 * The referenced AS Features that are not part of the source hierarchy. Their CG representations are folded into
	 * the CG hierarchy.
	 * </br>
	 * A UniqueList allows recursive discovery of more external Features
	 */
	private /*@LazyNonNull*/ UniqueList<@NonNull Feature> externalFeatures = null;

	/**
	 * Mapping from each AS Element to its corresponding CGNamedElement. (Variables are mapped by the prevailing
	 * ExecutablenManager since variables can be multiple synthesized.) For overloaded operations, this mapping is
	 * from the AS operation to its CG implementation; the mapping to the virtual dispatcher is in asVirtualOperation2cgOperation.
	 */
	protected @NonNull Map<@NonNull Element, @NonNull CGNamedElement> asElement2cgElement = new HashMap<>();

	/**
	 * Mapping from each AS Operation that has overrides to its corresponding virtual dispatching CG Operation.
	 */
	private final @NonNull Map<@NonNull Operation, @NonNull CGOperation> asVirtualOperation2cgOperation = new HashMap<>();

	private @Nullable Iterable<@NonNull CGValuedElement> cgGlobals = null;

	public CodeGenAnalyzer(@NonNull JavaCodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
		this.environmentFactory = (EnvironmentFactoryInternalExtension) codeGenerator.getEnvironmentFactory();
		this.completeModel = environmentFactory.getCompleteModel();
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.genModelHelper = codeGenerator.getGenModelHelper();
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.as2cgVisitor = codeGenerator.createAS2CGVisitor(this);
		this.cgFalse = createCGBoolean(false);
		this.cgTrue = createCGBoolean(true);
		this.cgNull = createCGNull();
	}

	public void addExternalFeature(@NonNull Feature asFeature) {
		UniqueList<@NonNull Feature> externalFeatures2 = externalFeatures;
		if (externalFeatures2 == null) {
			externalFeatures = externalFeatures2 = new UniqueList<>();
		}
		externalFeatures2.add(asFeature);
	}

	public void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globalNameManager.addGlobal(cgGlobal);
	}

//	public void addVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
//		CGNamedElement old = asElement2cgElement.put(asVariable, cgVariable);
//		assert old == null;
//	}

	public void addVirtualCGOperation(@NonNull Operation asOperation, @NonNull CGCachedOperation cgDispatchOperation) {
		if (asOperation.toString().contains("::_unqualified_env_Class(")) {
			getClass();		// XXX
		}
	//	assert cgOperation.getAst() == asOperation;
		assert cgDispatchOperation.getCallingConvention() == VirtualOperationCallingConvention.INSTANCE;
		CGOperation oldCGOperation = basicGetCGOperation(asOperation);
		assert (oldCGOperation != null) && (oldCGOperation != cgDispatchOperation);
		oldCGOperation = asVirtualOperation2cgOperation.put(asOperation, cgDispatchOperation);
		assert oldCGOperation == null;
	//	addCGOperation(cgOperation);
		OperationCallingConvention callingConvention = cgDispatchOperation.getCallingConvention();
		if (callingConvention.needsGeneration()) {
			CGClass cgRootClass = getCGRootClass(asOperation);
			cgRootClass.getOperations().add(cgDispatchOperation);
			// throw new UnsupportedOperationException();			// XXX cgRootClass
		}
	}

	public void analyze(@NonNull CGElement cgRoot) {
		AnalysisVisitor analysisVisitor = codeGenerator.createAnalysisVisitor();
		cgRoot.accept(analysisVisitor);
		assert checkNameManagers(cgRoot);
		//
		BoxingAnalyzer boxingAnalyzer = codeGenerator.createBoxingAnalyzer();
		cgRoot.accept(boxingAnalyzer);
		//
		FieldingAnalyzer fieldingAnalyzer = codeGenerator.createFieldingAnalyzer();
		fieldingAnalyzer.analyze(cgRoot, false);
		assert checkNameManagers(cgRoot);
	}

	public @Nullable Iterable<@NonNull CGClass> analyzeExternalFeatures(@NonNull CGClass cgRootClass) {
	//	Collection<@NonNull CGClass> cgRootClasses = cgPackage2cgRootClass.values();
	//	assert cgRootClasses.size() == 1 : "Missing support for multiple root CGClass";			// XXX
	//	CGClass cgRootClass = cgRootClasses.iterator().next();
	//	assert cgRootClass != null;
		UniqueList<@NonNull Feature> externalFeatures = getExternalFeatures();
		if (externalFeatures == null) {
			return null;
		}
		List<@NonNull CGClass> cgExternalClasses = new ArrayList<>();
		for (int i = 0; i < externalFeatures.size(); i++) {
			@NonNull Feature asExternalFeature = externalFeatures.get(i);
		//	CGClass cgRootClass = getCGRootClass(asExternalFeature);
			CGNamedElement cgExternalFeature = asExternalFeature.accept(as2cgVisitor);
			if (cgExternalFeature instanceof CGOperation) {
				CGOperation cgOperation = (CGOperation)cgExternalFeature;
				OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
				CGClass cgParentClass = callingConvention.needsNestedClass() ? createExternalCGClass(as2cgVisitor, cgExternalClasses, asExternalFeature) : cgRootClass;
				cgParentClass.getOperations().add(cgOperation);
			}
			else if (cgExternalFeature instanceof CGProperty) {
				CGProperty cgProperty = (CGProperty)cgExternalFeature;
				PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
				CGClass cgParentClass = callingConvention.needsNestedClass() ? createExternalCGClass(as2cgVisitor, cgExternalClasses, asExternalFeature) : cgRootClass;
				cgParentClass.getProperties().add(cgProperty);
			}
			else if (cgExternalFeature != null) {
				throw new UnsupportedOperationException("Expected an external feature rather than a " + cgExternalFeature.getClass().getSimpleName());
			}
		//	throw new UnsupportedOperationException();			// XXX cgRootClass
		}
//		List<CGClass> cgNestedClasses = cgRootClass.getClasses();
		for (@NonNull CGClass cgExternalClass : cgExternalClasses) {
			org.eclipse.ocl.pivot.Class asExternalClass = CGUtil.getAST(cgExternalClass);
		//	CGClass cgRootClass = getCGRootClass(asExternalClass);
			cgRootClass.getClasses().add(cgExternalClass);
		}
		return cgExternalClasses;
	}

	public @Nullable CGClass basicGetCGClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return (CGClass)asElement2cgElement.get(asClass);
	}

	public @Nullable CGConstraint basicGetCGConstraint(@NonNull Constraint asConstraint) {
		return (CGConstraint)asElement2cgElement.get(asConstraint);
	}

//	public @Nullable CGIterationCallExp basicGetCGIterationCallExp(@NonNull LoopExp asLoopExp) {
//		return asLoopExp2cgIterationCallExp.get(asLoopExp);
//	}

	public @Nullable CGOperation basicGetCGOperation(@NonNull Operation asOperation) {
		return (CGOperation)asElement2cgElement.get(asOperation);
	}

	public @Nullable CGPackage basicGetCGPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return (CGPackage)asElement2cgElement.get(asPackage);
	}

	public @Nullable CGProperty basicGetCGProperty(@NonNull Property asProperty) {
		return (CGProperty)asElement2cgElement.get(asProperty);
	}

/*	private @Nullable CGClass basicGetCGRootClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = basicGetCGClass(asClass);
		if (cgClass == null) {					// New (nested) AS Class, search the Package ancestry for a root class
			for (Element asElement = asClass; asElement != null; asElement = (Element)asElement.eContainer()) {
				if (asElement instanceof org.eclipse.ocl.pivot.Package) {
					CGPackage cgPackage = getCGPackage((org.eclipse.ocl.pivot.Package)asElement);
					return basicGetCGRootClass(cgPackage);
				}
			}
		}
		else if (cgClass.getAst() == asClass) {		// Regular AS Class, installs as regular nested Class
			return null;
		}
		return cgClass;
	} */

/*	public @Nullable CGClass basicGetCGRootClass(@NonNull CGNamedElement cgElement) {
		CGPackage cgPackage = CGUtil.getContainingPackage(cgElement);
		CGClass asClass = asPackage2asRootClass.get(asPackage);
		return asClass;
	} */

//	public @Nullable CGVariable basicGetCGVariable(@NonNull VariableDeclaration asVariable) {
//		return (CGVariable)asElement2cgElement.get(asVariable);
//	}

	public @Nullable NestedNameManager basicGetNameManager() {
//		return currentNameManager;
		throw new UnsupportedOperationException();
	}

	protected org.eclipse.ocl.pivot.@Nullable Class basicGetRootClass(@NonNull Element asElement) {	// XXX
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
		assert asPackage != null;
		CompletePackage completePackage = completeModel.getCompletePackage(asPackage);
		return completePackage2asRootClass.get(completePackage);
	}

	public @Nullable CGOperation basicGetVirtualCGOperation(@NonNull Operation asOperation) {
		return asVirtualOperation2cgOperation.get(asOperation);
	}

	public @Nullable NameManager basicUseSelfNameManager(@NonNull Element asElement) {
		for (EObject eObject = asElement, eChild = null; eObject != null; eChild = eObject, eObject = eObject.eContainer()) {
			CGNamedElement cgElement = asElement2cgElement.get(eObject);
			if (cgElement != null) {
				if (eChild != null) {
					NameManager childNameManager = globalNameManager.basicGetChildNameManager(cgElement);
					if (childNameManager != null) {
						if (eObject instanceof LoopExp) {
							LoopExp asLoopExp = (LoopExp)eObject;
							Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
							IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
							if (iterationHelper != null) {
//								assert false : "inline iteration should not have a child NameManager";
								if (asLoopExp.getOwnedBody() == eChild) {
									return childNameManager;
								}
							}
							else {
								if (asLoopExp.getOwnedIterators().contains(eChild)) {
									return childNameManager;
								}
								if (asLoopExp.getOwnedCoIterators().contains(eChild)) {
									return childNameManager;
								}
								if (asLoopExp.getOwnedBody() == eChild) {
									return childNameManager;
								}
							}
						}
						else {
							return childNameManager;
						}
					}
				}
				NameManager selfNameManager = globalNameManager.basicGetSelfNameManager(cgElement);
				if (selfNameManager != null) {
					return selfNameManager;
				}
			}
		}
		return null;
	}

	//
	//	Assert method to verify the consistency of AS/CG NameManagers
	//
	protected boolean checkNameManagers(@NonNull CGElement cgRoot) {
		for (@NonNull EObject eObject : new TreeIterable(cgRoot, true)) {
			if ((eObject instanceof CGNamedElement) && !(eObject instanceof CGExecutorType) && !(eObject instanceof CGExecutorProperty)) {		// FIXME CGExecutorXXX.ast is a Class/Property
				CGNamedElement cgElement = (CGNamedElement)eObject;
				if (cgElement instanceof CGVariableExp) {
					getClass();		// XXX
				}
				EObject asElement = cgElement.getAst();
				if (asElement instanceof NamedElement) {
					checkNameManager(cgElement, (NamedElement)asElement);
				}
			}
		}
		return true;
	}

	protected void checkNameManager(@NonNull CGNamedElement cgElement, @NonNull NamedElement asElement) {
		NameManager cgNameManager = globalNameManager.basicUseSelfNameManager(cgElement);
		NameManager asNameManager = basicUseSelfNameManager(asElement);
		if ((asNameManager == null) || (cgNameManager == null) || (cgNameManager != asNameManager)) {
			cgNameManager = globalNameManager.basicUseSelfNameManager(cgElement);
			asNameManager = basicUseSelfNameManager(asElement);
		}
// XXX WIP	assert cgNameManager == asNameManager : "Mismatch for " + asElement.eClass().getName() + " : " + asElement;
		//	assert cgNameManager != null;
	}

	public @NonNull CGBoolean createCGBoolean(boolean booleanValue) {
		CGBoolean cgBoolean = CGModelFactory.eINSTANCE.createCGBoolean();
		cgBoolean.setBooleanValue(booleanValue);
		cgBoolean.setTypeId(getCGTypeId(TypeId.BOOLEAN));
		globalNameManager.getNameResolution(cgBoolean);
		return cgBoolean;
	}

	public @NonNull CGCastExp createCGCastExp(@NonNull CGExecutorType cgExecutorType, @NonNull CGValuedElement cgValue) {
		CGCastExp cgCastExp = CGModelFactory.eINSTANCE.createCGCastExp();
		cgCastExp.setSource(cgValue);
		cgCastExp.setExecutorType(cgExecutorType);
		cgCastExp.setTypeId(codeGenerator.getAnalyzer().getCGTypeId(CGUtil.getAST(cgExecutorType).getTypeId()));
		return cgCastExp;
	}

	public @NonNull CGValuedElement createCGConstantExp(@NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(cgConstant.getAst());
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(cgConstant.getTypeId());
		return cgConstantExp;
	}

	public @NonNull CGConstantExp createCGConstantExp(@NonNull TypedElement element, @NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(element);
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(getCGTypeId(element.getTypeId()));
		return cgConstantExp;
	}

	public @NonNull <T extends CGElement> T createCGElement(@NonNull Class<T> requiredClass, @Nullable Element asElement) {
		if (asElement == null) {
			throw new NullPointerException("null source for mapping to " + requiredClass.getName());
		}
		CGNamedElement cgElement = asElement.accept(as2cgVisitor);
		if (cgElement == null) {
			throw new NullPointerException("null result of mapping to " + requiredClass.getName());
		}
		Class<? extends CGNamedElement> actualClass = cgElement.getClass();
		if (!requiredClass.isAssignableFrom(actualClass)) {
			throw new ClassCastException("cannot cast " + actualClass.getName() + " result of mapping to " + requiredClass.getName());
		}
		@SuppressWarnings("unchecked") T cgElement2 = (T) cgElement;
		return cgElement2;
	}

	public @NonNull CGIfExp createCGIfExp(@NonNull CGValuedElement cgCondition, @NonNull CGValuedElement cgThenExpression, @NonNull CGValuedElement cgElseExpression) {
		CGIfExp cgIfExp = CGModelFactory.eINSTANCE.createCGIfExp();
		cgIfExp.setCondition(cgCondition);
		cgIfExp.setThenExpression(cgThenExpression);
		cgIfExp.setElseExpression(cgElseExpression);
		cgIfExp.setTypeId(cgThenExpression.getTypeId());		// FIXME common type
		cgIfExp.setRequired(cgThenExpression.isRequired() && cgElseExpression.isRequired());
		return cgIfExp;
	}

	public @NonNull CGIndexExp createCGIndexExp(@NonNull CGValuedElement cgValue, int index) {
		ElementId elementTypeId = cgValue.getTypeId().getElementId();
		TypeId asTypeId = null;
		if (elementTypeId instanceof JavaTypeId) {
			Class<?> jArrayClass = ((JavaTypeId)elementTypeId).getJavaClass();
			Class<?> jClass = jArrayClass.getComponentType();
			if (jClass != null) {
				asTypeId = JavaConstants.getJavaTypeId(jClass);
			}
		}
		if (asTypeId == null) {
			asTypeId = TypeId.OCL_ANY;				// Never happens
		}
		CGIndexExp cgIndexExp = CGModelFactory.eINSTANCE.createCGIndexExp();
	//	setAst(cgVariableExp, asVariableExp);			// Set by caller
		cgIndexExp.setSource(cgValue);
		cgIndexExp.setIndex(createCGConstantExp(getCGInteger(index)));
		cgIndexExp.setTypeId(getCGTypeId(asTypeId));
		cgIndexExp.setRequired(false/*cgValue.isRequired()*/);		// FIXME maintain and use inner @NonNull annotation
		return cgIndexExp;
	}

	public @NonNull CGValuedElement createCGIsEqual(@NonNull CGValuedElement cgLeft, @NonNull CGValuedElement cgRight) {
		CGIsEqualExp cgIsEqual = CGModelFactory.eINSTANCE.createCGIsEqualExp();
		cgIsEqual.setSource(cgLeft);
		cgIsEqual.setArgument(cgRight);
		cgIsEqual.setTypeId(getCGTypeId(TypeId.BOOLEAN));
		cgIsEqual.setRequired(true);
		return cgIsEqual;
	}

	public @NonNull CGLetExp createCGLetExp(@NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		cgLetExp.setTypeId(cgIn.getTypeId());
		cgLetExp.setRequired(cgIn.isRequired());
		return cgLetExp;
	}

	protected @NonNull CGLetExp createCGLetExp(@NonNull TypedElement asElement, @NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		cgLetExp.setAst(asElement);
		cgLetExp.setTypeId(getCGTypeId(asElement.getTypeId()));
		return cgLetExp;
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {		// XXX @NonNull
		Operation asOperation = getNativeOperation(method);
		CGOperation cgOperation = generateOperationDeclaration(asOperation, null, false);
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setMethod(method);		// Use cc
		cgNativeOperationCallExp.setReferredOperation(cgOperation);
		return cgNativeOperationCallExp;
	}

	public @NonNull CGNativePropertyCallExp createCGNativePropertyCallExp(@NonNull Field field, @NonNull PropertyCallingConvention callingConvention) {		// XXX @NonNull
		Property asProperty = getNativeProperty(field, callingConvention);
		CGProperty cgProperty = getCGProperty(asProperty);
		CGNativePropertyCallExp cgNativePropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
		cgNativePropertyCallExp.setField(field);		// Use cc
		cgNativePropertyCallExp.setReferredProperty(cgProperty);
	//	callingConvention.createCGOperationCallExp(null, cgOperation, null, cgOperation, null)
		return cgNativePropertyCallExp;
	}

	public @NonNull CGNull createCGNull() {
		CGNull cgNull = CGModelFactory.eINSTANCE.createCGNull();
		cgNull.setTypeId(getCGTypeId(TypeId.OCL_VOID));
		globalNameManager.getNameResolution(cgNull);
		return cgNull;
	}

	public @NonNull CGParameter createCGParameter(@NonNull NameResolution nameResolution, @NonNull CGTypeId typeId, boolean isRequired) {
		CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
		nameResolution.addCGElement(cgParameter);
		cgParameter.setTypeId(typeId);
		cgParameter.setRequired(isRequired);
		if (isRequired) {
			cgParameter.setNonNull();
		}
		return cgParameter;
	}

	public @NonNull CGVariableExp createCGVariableExp(@NonNull CGVariable cgVariable) {
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
	//	setAst(cgVariableExp, asVariableExp);
		cgVariableExp.setReferredVariable(cgVariable);
		cgVariableExp.setTypeId(cgVariable.getTypeId());
	//	cgVariableExp.setRequired(cgVariable.getIs);
//		cgVariable.getNameResolution().addCGElement(cgVariableExp);
		return cgVariableExp;
	}

/*	public @NonNull CGExecutorOperation createExecutorOperation(@NonNull Operation asOperation) {
		OperationId operationId = asOperation.getOperationId();
		CGExecutorOperation cgOperation = CGModelFactory.eINSTANCE.createCGExecutorOperation();
		CGElementId cgOperationId = getElementId(operationId);
	//	cgOperation.setTypeId(getTypeId(asOperation.getTypeId()));
		cgOperation.setUnderlyingOperationId(cgOperationId);
		cgOperation.setAst(asOperation);
		globalNameManager.declareLazyName(cgOperation);
		cgOperation.getDependsOn().add(cgOperationId);
		return cgOperation;
	} */

	public @NonNull CGExecutorProperty createExecutorOppositeProperty(@NonNull Property asProperty) {
		PropertyId propertyId = asProperty.getPropertyId();
		CGExecutorProperty cgProperty = null;
		CGElementId cgPropertyId = getCGElementId(propertyId);
		Property asOppositeProperty = ClassUtil.nonNullState(asProperty.getOpposite());
		if (asOppositeProperty.isIsComposite()) {
			cgPropertyId = getCGElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorCompositionProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asOppositeProperty);
			cgProperty.setTypeId(getCGTypeId(JavaConstants.UNBOXED_COMPOSITION_PROPERTY_TYPE_ID));
			globalNameManager.getNameResolution(cgProperty);
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		else {
			cgPropertyId = getCGElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorOppositeProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asProperty);
			globalNameManager.getNameResolution(cgProperty);
			cgProperty.setTypeId(getCGTypeId(JavaConstants.UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID));
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		return cgProperty;
	}

/*	@Deprecated / * @deprecated use callingConvention * /
	public @NonNull CGExecutorProperty createExecutorProperty(@NonNull Property asProperty) {
		assert !asProperty.isIsStatic();			// static is inlined
		// XXX asProperty.esObject == null => ForeignProperty
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = getCGElementId(propertyId);
		CGExecutorProperty cgProperty = CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
		cgProperty.setUnderlyingPropertyId(cgPropertyId);
		cgProperty.setAst(asProperty);
		globalNameManager.declareLazyName(cgProperty);
		TypeId javaPropertyTypeId = JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID;
		cgProperty.setTypeId(getCGTypeId(javaPropertyTypeId));
		cgProperty.getDependsOn().add(cgPropertyId);
		return cgProperty;
	} */

	protected @NonNull CGClass createExternalCGClass(@NonNull AS2CGVisitor as2cgVisitor, @NonNull List<@NonNull CGClass> cgExternalClasses, @NonNull Feature asExternalFeature) {
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		org.eclipse.ocl.pivot.Class asExternalClass = PivotUtil.getOwningClass(asExternalFeature);
		String externalClassName = codeGenerator.getExternalClassName(asExternalClass);
		CGClass cgExternalClass = name2cgNestedClass.get(externalClassName);
		if (cgExternalClass == null) {
			importNameManager.reserveLocalName(externalClassName);
			cgExternalClass = generateClassDeclaration(asExternalClass, ExternalClassCallingConvention.INSTANCE);
			globalNameManager.declareEagerName(cgExternalClass, externalClassName);		// XXX nest in currentNameManager
		//	cgStaticClass.setAst(foreignClass);  -- the real class has the AS element
			cgExternalClasses.add(cgExternalClass);
			name2cgNestedClass.put(externalClassName, cgExternalClass);
		}
		return cgExternalClass;
	}

	public boolean equals(@NonNull Element asElement1, @NonNull Element asElement2) {
		ExpressionInOCL asExpressionInOCL1 = PivotUtil.getContainingExpressionInOCL(asElement1);
		ExpressionInOCL asExpressionInOCL2 = PivotUtil.getContainingExpressionInOCL(asElement2);
		if ((asExpressionInOCL1 == null) || (asExpressionInOCL2 == null)) {// || (asExpressionInOCL1 != asExpressionInOCL2)) {
			return false;
		}
		Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis2 = expression2cseAnalsis;
		if (expression2cseAnalsis2 == null) {
			expression2cseAnalsis = expression2cseAnalsis2 = new HashMap<>();
		}
		CommonSubExpressionAnalysis cseAnalysis = expression2cseAnalsis2.get(asExpressionInOCL1);
		if (cseAnalysis == null) {
			cseAnalysis = new CommonSubExpressionAnalysis();
		}
		cseAnalysis.analyze(asExpressionInOCL1);
		cseAnalysis.analyze(asExpressionInOCL2);
		CSEElement cseElement1 = cseAnalysis.getCSEElement(asElement1);
		CSEElement cseElement2 = cseAnalysis.getCSEElement(asElement2);
		return cseElement1 == cseElement2;
	}

	/**
	 * Generate the full CG declaration and implementation for asClass.
	 */
	public @NonNull CGClass generateClass(@Nullable CGClass cgClass, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (cgClass == null) {
			cgClass = basicGetCGClass(asClass);
			if (cgClass == null) {
				cgClass = generateClassDeclaration(asClass, null);
				org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(asClass);
//				if (!asPackage2asRootClass.containsKey(asPackage)) {
//					setCGRootClass(cgClass);
//				}
				assert cgClass.eContainer() != null;			// XXX
			}
		}
		getClassNameManager(cgClass, asClass);			// Nominally redundant here but needed downstream
		for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : ClassUtil.nullFree(asClass.getSuperClasses())) {
			CGClass cgSuperClass = generateClassDeclaration(asSuperClass, null);
			cgClass.getSuperTypes().add(cgSuperClass);
		}
		for (@NonNull Property asProperty : ClassUtil.nullFree(asClass.getOwnedProperties())) {
			CGProperty cgProperty = createCGElement(CGProperty.class, asProperty);
			assert cgClass.getProperties().contains(cgProperty);
		}
		for (@NonNull Constraint asConstraint : ClassUtil.nullFree(asClass.getOwnedInvariants())) {
			CGConstraint cgConstraint = createCGElement(CGConstraint.class, asConstraint);
			assert cgClass.getInvariants().contains(cgConstraint);
		}
		for (@NonNull Operation asOperation : ClassUtil.nullFree(asClass.getOwnedOperations())) {
			CGOperation cgOperation = createCGElement(CGOperation.class, asOperation);
			assert cgClass.getOperations().contains(cgOperation);			// XXX
		}
	/*	org.eclipse.ocl.pivot.Package asCachePackage = AbstractLanguageSupport.basicGetCachePackage(asClass);
		if (asCachePackage != null) {
			for (org.eclipse.ocl.pivot.@NonNull Class asCacheClass : PivotUtil.getOwnedClasses(asCachePackage)) {
				CGClass cgCacheClass = generateClassDeclaration(asCacheClass, null);
				assert cgClass.getClasses().contains(cgCacheClass);
			}
		} */
		return cgClass;
	}

	/**
	 * Generate / share the CG declaration for asClass.
	 */
	public @NonNull CGClass generateClassDeclaration(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable ClassCallingConvention callingConvention) {
		CGClass cgClass = (CGClass)asElement2cgElement.get(asClass);
		if (cgClass == null) {
			CompleteClass completeClass = completeModel.getCompleteClass(asClass);
			asClass = completeClass.getPrimaryClass();
		//	System.out.println("generateClassDeclaration " + NameUtil.debugSimpleName(asClass) + " " + asClass);
			if (callingConvention == null) {
				callingConvention = codeGenerator.getCallingConvention(asClass);
			}
			cgClass = callingConvention.createCGClass(this, asClass);
			EObject eContainer = cgClass.eContainer();
			assert eContainer != null;
			cgClass.setAst(asClass);
			cgClass.setCallingConvention(callingConvention);
			for (org.eclipse.ocl.pivot.@NonNull Class asPartialClass : PivotUtil.getPartialClasses(completeClass)) {
				asElement2cgElement.put(asPartialClass, cgClass);
			}
			String name = callingConvention.getName(this, asClass);
			if (eContainer instanceof CGClass) {
				CGClass cgParentClass = (CGClass)eContainer;
				ClassNameManager classNameManager = getClassNameManager(cgParentClass, CGUtil.getAST(cgParentClass));
				new NameResolution.EagerNested(classNameManager, cgClass, name);
			}
			else {
				CGPackage cgParentPackage = (CGPackage)eContainer;
				PackageNameManager packageNameManager = getPackageNameManager(cgParentPackage, CGUtil.getAST(cgParentPackage));
				new NameResolution.EagerNested(packageNameManager, cgClass, name);
			}
		}
		return cgClass;
	}

	/**
	 * Generate / share the CG declaration and implementation for asConstraint.
	 */
	public @NonNull CGConstraint generateConstraint(@NonNull Constraint asConstraint) {
		CGConstraint cgConstraint = (CGConstraint)asElement2cgElement.get(asConstraint);
		if (cgConstraint == null) {
			cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
			cgConstraint.setAst(asConstraint);
		//	cgConstraint.setTypeId(getCGTypeId(asConstraint.getTypeId()));
			asElement2cgElement.put(asConstraint, cgConstraint);
			generateConstraintBody(cgConstraint, asConstraint);
			CGClass cgClass = getCGClass(PivotUtil.getContainingClass(asConstraint));		// XXX
			cgClass.getInvariants().add(cgConstraint);
		}
		return cgConstraint;
	}

	protected void generateConstraintBody(@NonNull CGConstraint cgConstraint, @NonNull Constraint asConstraint) {
		LanguageExpression specification = asConstraint.getOwnedSpecification();
		if (specification != null) {
			assert cgConstraint.basicGetNameResolution() == null;
		//	getNameManager().declarePreferredName(cgConstraint);
			ExecutableNameManager constraintNameManager = getConstraintNameManager(cgConstraint, asConstraint);
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					CGParameter cgParameter = constraintNameManager.getCGParameter(contextVariable, null);
					cgConstraint.getParameters().add(cgParameter);
				}
				for (@NonNull Variable parameterVariable : ClassUtil.nullFree(query.getOwnedParameters())) {
					CGParameter cgParameter = constraintNameManager.getCGParameter(parameterVariable, null);
					cgConstraint.getParameters().add(cgParameter);
				}
				cgConstraint.setBody(createCGElement(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Generate / share the CG declaration for asOperation.
	 */
	public @NonNull CGOperation generateIterationDeclaration(@NonNull Iteration asIteration) {	// XXX rationalize as generateOperationDeclaration with later createImplementation
		CGOperation cgOperation = basicGetCGOperation(asIteration);
		if (cgOperation == null) {
			OperationCallingConvention callingConvention = codeGenerator.getCallingConvention(asIteration, true);
			cgOperation = callingConvention.createCGOperation(this, asIteration);
			cgOperation.setCallingConvention(callingConvention);
			initAst(cgOperation, asIteration, true);
			ExecutableNameManager operationNameManager = getOperationNameManager(cgOperation, asIteration);
			ExpressionInOCL asExpressionInOCL = null;
			LanguageExpression asSpecification = asIteration.getBodyExpression();
			if (asSpecification != null) {
				try {
					asExpressionInOCL = environmentFactory.parseSpecification(asSpecification);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			callingConvention.createCGParameters(operationNameManager, asExpressionInOCL);
		}
		return cgOperation;
	}

	protected @NonNull CGIterationCallExp generateLoopDeclaration(@NonNull LoopExp asLoopExp) {
		CGIterationCallExp cgIterationCallExp = (CGIterationCallExp)asElement2cgElement.get(asLoopExp);
		if (cgIterationCallExp == null) {
			Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
			IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
			if (iterationHelper != null) {
				cgIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
			}
			else {
				LibraryIteration libraryIteration = (LibraryIteration) metamodelManager.getImplementation(asIteration);
				CGLibraryIterationCallExp cgLibraryIterationCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterationCallExp();
				cgLibraryIterationCallExp.setLibraryIteration(libraryIteration);
				cgIterationCallExp = cgLibraryIterationCallExp;
			}
			initAst(cgIterationCallExp, asLoopExp, true);
		}
		return cgIterationCallExp;
	}

	protected @NonNull CGIterationCallExp generateLoopExp(@NonNull LoopExp asLoopExp) {
		ExecutableNameManager parentNameManager = useExecutableNameManager((NamedElement)asLoopExp.eContainer());
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
		CGIterationCallExp cgIterationCallExp = generateLoopDeclaration(asLoopExp);
		CGValuedElement cgUnsafeSource = createCGElement(CGValuedElement.class, asLoopExp.getOwnedSource());
		CGValuedElement cgSafeSource = asLoopExp.isIsSafe() ? generateSafeExclusion(asLoopExp, cgUnsafeSource) : cgUnsafeSource;
	//	OCLExpression asSource = asLoopExp.getOwnedSource();
	//	Type asSourceType = asSource != null ? asSource.getType() : null;
		CGOperation cgOperation = generateIterationDeclaration(/*asSourceType,*/ asIteration);
		cgIterationCallExp.setAsIteration(asIteration);
		cgIterationCallExp.setReferredIteration(cgOperation);
		cgIterationCallExp.setInvalidating(asIteration.isIsInvalidating());
		cgIterationCallExp.setValidating(asIteration.isIsValidating());
		cgIterationCallExp.setSource(cgSafeSource);
		globalNameManager.addSelfNameManager(cgSafeSource, parentNameManager);										// Source always evaluated in parent context
		ExecutableNameManager childNameManager = getLoopNameManager(cgIterationCallExp, asLoopExp);
		//
		//	Iterators / co-iterators
		//
		ExecutableNameManager iteratorNameManager = iterationHelper != null ? parentNameManager : childNameManager;	// Iterators conditionally in parent/child context
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(asLoopExp)) {
			CGIterator cgIterator = iteratorNameManager.getIterator(iterator);
			if (iterationHelper != null) {
				setNullableIterator(cgIterator, iterator);
			}
			cgIterationCallExp.getIterators().add(cgIterator);
			globalNameManager.addSelfNameManager(cgIterator, iteratorNameManager);
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(asLoopExp)) {
			CGIterator cgCoIterator = iteratorNameManager.getIterator(coIterator);
			if (iterationHelper != null) {
				setNullableIterator(cgCoIterator, coIterator);
			}
			cgIterationCallExp.getCoIterators().add(cgCoIterator);
			globalNameManager.addSelfNameManager(cgCoIterator, iteratorNameManager);
		}
		if (asLoopExp instanceof IterateExp) {
			Variable accumulator = PivotUtil.getOwnedResult((IterateExp)asLoopExp);
			CGIterator cgAccumulator = iteratorNameManager.getIterator(accumulator);
			if (iterationHelper != null) {
				//				cgBuiltInIterationCallExp.setNonNull();
				setNullableIterator(cgAccumulator, accumulator);
				((CGBuiltInIterationCallExp)cgIterationCallExp).setAccumulator(cgAccumulator);
				globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
			}
			else {
				((CGLibraryIterateCallExp)cgIterationCallExp).setResult(cgAccumulator);
			}
			CGValuedElement cgInitExpression = createCGElement(CGValuedElement.class, accumulator.getOwnedInit());
			cgAccumulator.setInit(cgInitExpression);
		}
		else {
			if (iterationHelper != null) {
				CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp) cgIterationCallExp;
				CGTypeId cgAccumulatorId = iterationHelper.getAccumulatorTypeId(this, cgBuiltInIterationCallExp);
				if (cgAccumulatorId != null) {
					boolean isNonNullAccumulator = iterationHelper.isNonNullAccumulator(asLoopExp);
					CGAccumulator cgAccumulator = CGModelFactory.eINSTANCE.createCGAccumulator();
					cgAccumulator.setTypeId(cgAccumulatorId);
					if (isNonNullAccumulator) {
						cgAccumulator.setNonNull();
					}
					if (!asIteration.isIsValidating()) {
						cgAccumulator.setNonInvalid();
					}
					cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
					globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
				}
			}
		}
		//
		//	Body
		//
		boolean isRequired = asLoopExp.isIsRequired();
		CGValuedElement cgBody = createCGElement(CGValuedElement.class, asLoopExp.getOwnedBody());
		cgIterationCallExp.setBody(cgBody);
		if (iterationHelper != null) {
			if (asIteration.getOwnedParameters().get(0).isIsRequired()) {
				cgBody.setRequired(true);
			}
			if (isRequired) {
				((CGBuiltInIterationCallExp)cgIterationCallExp).setNonNull();
			}
		}
		cgIterationCallExp.setRequired(isRequired);
		return cgIterationCallExp;
	}

	public @NonNull CGOperation generateOperation(@NonNull Operation asOperation) {
	//	asOperation2cgOperation.get(asOperation);
		LanguageExpression specification = asOperation.getBodyExpression();
		CGOperation cgFinalOperation = generateOperationDeclaration(asOperation, null, true);
		assert cgFinalOperation.getBody() == null;
//		System.out.println("visitOperation " + NameUtil.debugSimpleName(cgFinalOperation) + " : " + asOperation);
		if (specification instanceof ExpressionInOCL) {			// Should already be parsed
			cgFinalOperation.getCallingConvention().createCGBody(this, cgFinalOperation);
		}
		CGOperation cgVirtualOperation = generateOperationDeclaration(asOperation, null, false);
		if (cgVirtualOperation != cgFinalOperation) {
			assert cgVirtualOperation.getBody() == null;
//			System.out.println("visitOperation " + NameUtil.debugSimpleName(cgVirtualOperation) + " : " + asOperation);
			getOperationNameManager(cgVirtualOperation, asOperation);
			if (specification instanceof ExpressionInOCL) {			// Should already be parsed
				cgVirtualOperation.getCallingConvention().createCGBody(this, cgVirtualOperation);
			}
		}
		return cgFinalOperation;
	}

	public @NonNull CGValuedElement generateOperationCallExp(@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		CGOperation cgOperation = generateOperationDeclaration(asOperation, null, false);

		assert cgOperation.eContainer() != null;		// XXX
		OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
		LibraryOperation libraryOperation = (LibraryOperation)metamodelManager.getImplementation(asOperation);
		CGValuedElement cgOperationCallExp = callingConvention.createCGOperationCallExp(this, cgOperation, libraryOperation, cgSource, asOperationCallExp);
		CGNamedElement old = asElement2cgElement.put(asOperationCallExp, cgOperationCallExp);
	//	assert old == cgOperationCallExp;			// XXX demonstrates that put is redundant
		return cgOperationCallExp;
	}

	/**
	 * Generate / share the CG declaration for asOperation.
	 */
	public @NonNull CGOperation generateOperationDeclaration(@NonNull Operation asOperation, @Nullable OperationCallingConvention callingConvention, boolean requireFinal) {	// XXX rationalize as generateOperationDeclaration with later createImplementation
		if (asOperation.toString().contains("::_unqualified_env_Class(")) {
			getClass();		// XXX
		}
		CGOperation cgOperation = basicGetCGOperation(asOperation);
		if (cgOperation == null) {
			if (callingConvention == null) {
				callingConvention = codeGenerator.getCallingConvention(asOperation, requireFinal);
			}
			cgOperation = callingConvention.createCGOperation(this, asOperation);
			cgOperation.setCallingConvention(callingConvention);
			if (cgOperation.getAst() != null) {
				assert cgOperation.getAst() == asOperation;
				assert callingConvention instanceof VirtualOperationCallingConvention;
				assert asElement2cgElement.containsKey(asOperation);
			}
			else {
				assert !asElement2cgElement.containsKey(asOperation);
				initAst(cgOperation, asOperation, true);
			}
//			System.out.println("generateOperationDeclaration " + NameUtil.debugSimpleName(cgOperation) + " : " + asOperation);
			ExecutableNameManager operationNameManager = getOperationNameManager(cgOperation, asOperation);	// Needed to support downstream useOperationNameManager()
			ExpressionInOCL asExpressionInOCL = null;
			LanguageExpression asSpecification = asOperation.getBodyExpression();
			if (asSpecification != null) {
				try {
					asExpressionInOCL = environmentFactory.parseSpecification(asSpecification);			// XXX Not appropriate for virtual dispatcher
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cgOperation.eContainer() == null) {			// Unless createCGOperation defined an alternative
				CGClass cgClass = getCGClass(PivotUtil.getOwningClass(asOperation));
				cgClass.getOperations().add(cgOperation);
			}
			callingConvention.createCGParameters(operationNameManager, asExpressionInOCL);
		}
		if (!requireFinal) {
			CGOperation cgVirtualOperation = basicGetVirtualCGOperation(asOperation);
			if (cgVirtualOperation != null) {
				return cgVirtualOperation;
			}
		}
		return cgOperation;
	}

	public @NonNull CGValuedElement generateOppositePropertyCallExp(@NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asOppositePropertyCallExp);
		CGProperty cgProperty = generatePropertyDeclaration(asProperty, null);
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(null, null, asProperty);
		CGValuedElement cgNavigationCallExp = callingConvention.createCGNavigationCallExp(this, cgProperty, libraryProperty, cgSource, asOppositePropertyCallExp);
		CGNamedElement old = asElement2cgElement.put(asOppositePropertyCallExp, cgNavigationCallExp);
		assert old == cgNavigationCallExp;			// XXX demonstrates that put is redundant
		return cgNavigationCallExp;
	}

	/**
	 * Generate / share the CG declaration for asPackage.
	 */
	public @NonNull CGPackage generatePackageDeclaration(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CGPackage cgPackage = (CGPackage)asElement2cgElement.get(asPackage);
		if (cgPackage == null) {
			CompletePackage completePackage = completeModel.getCompletePackage(asPackage);
			org.eclipse.ocl.pivot.Package asPrimaryPackage = ClassUtil.nonNullState(completePackage.getPrimaryPackage());
			cgPackage = (CGPackage)asElement2cgElement.get(asPrimaryPackage);
			if (cgPackage != null) {
				asElement2cgElement.put(asPackage, cgPackage);			// Late native discovery
			}
			else {
				cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
				cgPackage.setAst(asPrimaryPackage);
			//	cgPackage.setName(callingConvention.getName(this, asPackage));			// XXX defer via NameResolution
				for (org.eclipse.ocl.pivot.@NonNull Package asPartialPackage : PivotUtil.getPartialPackages(completePackage)) {
					asElement2cgElement.put(asPartialPackage, cgPackage);
				}
				String name = PivotUtil.getName(asPrimaryPackage);
				EObject eContainer = asPrimaryPackage.eContainer();
				if ((eContainer == null) || (eContainer instanceof Model)) {		// XXX why no Model ??
					globalNameManager.declareEagerName(cgPackage, name);
				}
				else {
					PackageNameManager parentPackageNameManager = getPackageNameManager(null, (org.eclipse.ocl.pivot.Package)eContainer);
					parentPackageNameManager.getCGPackage().getPackages().add(cgPackage);
					new NameResolution.EagerNested(parentPackageNameManager, cgPackage, name);
				}
				getPackageNameManager(cgPackage, asPrimaryPackage);
			}
		}
		return cgPackage;
	}

	public @NonNull CGProperty generateProperty(@NonNull Property asProperty) {
		CGProperty cgProperty = generatePropertyDeclaration(asProperty, null);		// XXX redundant
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
	//	getPropertyNameManager(cgProperty);
		// parse ownedExpression here to simplify createImplementation arguments
		callingConvention.createImplementation(this, cgProperty);
		return cgProperty;
	}

	public @NonNull CGValuedElement generatePropertyCallExp(@Nullable CGValuedElement cgSource, @NonNull PropertyCallExp asPropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asPropertyCallExp);
		CGProperty cgProperty = generatePropertyDeclaration(asProperty, null);
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(null, null, asProperty);
		CGValuedElement cgNavigationCallExp = callingConvention.createCGNavigationCallExp(this, cgProperty, libraryProperty, cgSource, asPropertyCallExp);
		CGNamedElement old = asElement2cgElement.put(asPropertyCallExp, cgNavigationCallExp);
		assert old == cgNavigationCallExp;			// XXX demonstrates that put is redundant
		return cgNavigationCallExp;
	}

	/**
	 * Generate / share the CG declaration for asProperty.
	 * @param callingConvention
	 */
	public final @NonNull CGProperty generatePropertyDeclaration(@NonNull Property asProperty, @Nullable PropertyCallingConvention callingConvention) {
		CGProperty cgProperty = basicGetCGProperty(asProperty);
		if (cgProperty == null) {
			if (callingConvention == null) {
				callingConvention = codeGenerator.getCallingConvention(asProperty);
			}
			cgProperty = callingConvention.createCGProperty(this, asProperty);
			cgProperty.setCallingConvention(callingConvention);
			initAst(cgProperty, asProperty, true);
			ExecutableNameManager propertyNameManager = getPropertyNameManager(cgProperty, asProperty);
			ExpressionInOCL query = null;
			LanguageExpression specification = asProperty.getOwnedExpression();
			if (specification != null) {
				try {
					query = environmentFactory.parseSpecification(specification);		// Redundant already parsed
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			callingConvention.createCGParameters(propertyNameManager, query);
			CGClass cgClass = getCGClass(PivotUtil.getOwningClass(asProperty));		// XXX
			cgClass.getProperties().add(cgProperty);
		}
		return cgProperty;
	}

	protected @NonNull CGValuedElement generateSafeExclusion(@NonNull CallExp callExp, @NonNull CGValuedElement cgSource) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(CollectionExcludingOperation.INSTANCE);
		Operation asExcludingOperation = standardLibrary.getCollectionExcludingOperation();
		OCLExpression asSource = callExp.getOwnedSource();
		assert asSource != null;
		CGOperation cgOperation = generateOperationDeclaration(asExcludingOperation, null, true);
		cgOperationCallExp.setReferredOperation(cgOperation);
		cgOperationCallExp.setTypeId(getCGTypeId(asSource.getTypeId()));
		cgOperationCallExp.setRequired(true);
		cgOperationCallExp.getArguments().add(cgSource);
		CGConstantExp cgArgument = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgArgument.setReferredConstant(getCGNull());
		cgArgument.setTypeId(getCGTypeId(TypeId.OCL_VOID));
		cgOperationCallExp.getArguments().add(cgArgument);
		return cgOperationCallExp;
	}

	protected @NonNull CGIfExp generateSafeNavigationGuard(@NonNull CallExp callExp, @NonNull CGVariableExp cgVariableExp, @NonNull CGValuedElement cgUnsafeExp) {
		CGConstantExp cgNullExpression = createCGConstantExp(callExp, getCGNull());
		//
		CGIsEqual2Exp cgCondition = CGModelFactory.eINSTANCE.createCGIsEqual2Exp();
	//	cgCondition.setAst(callExp);
	//	cgCondition.setTypeId(getCGTypeId(TypeId.BOOLEAN));
		initAst(cgCondition, callExp, TypeId.BOOLEAN, false); // Guard has different type.
		cgCondition.setSource(cgVariableExp);
		cgCondition.setArgument(cgNullExpression);
		cgCondition.setInvalidating(false);
		cgCondition.setValidating(true);
		//
		CGConstantExp cgThenExpression = createCGConstantExp(callExp, getCGNull());
		//
		CGIfExp cgIfExp = createCGIfExp(cgCondition, cgThenExpression, cgUnsafeExp);
		initAst(cgIfExp, callExp, false);
	//	assert assertInitAst(cgIfExp, callExp, true);
		//
		return cgIfExp;
	}

	public @NonNull CGValuedElement generateVariableExp(@NonNull VariableExp asVariableExp) {
		VariableDeclaration asVariable = PivotUtil.getReferredVariable(asVariableExp);
		CGVariable cgVariable = useExecutableNameManager(asVariableExp).getCGVariable(asVariable);
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		cgVariableExp.setReferredVariable(cgVariable);
		initAst(cgVariableExp, asVariableExp, true);
		return cgVariableExp;
	}

//	public @NonNull AS2CGVisitor getAS2CGVisitor() {
//		return as2cgVisitor;
//	}

	public @NonNull CGBoolean getCGBoolean(boolean aBoolean) {
		return aBoolean ? cgTrue : cgFalse;
	}

	public @NonNull CGClass getCGClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return (CGClass)ClassUtil.nonNullState(asElement2cgElement.get(asClass));
	}

	public @NonNull CGConstraint getCGConstraint(@NonNull Constraint asConstraint) {
		return (CGConstraint)ClassUtil.nonNullState(asElement2cgElement.get(asConstraint));
	}

	public @NonNull CGElementId getCGElementId(@NonNull ElementId elementId) {
		CGElementId cgElementId = cgElementIds.get(elementId);
		if (cgElementId == null) {
			if (elementId instanceof TypeId) {
				return getCGTypeId((TypeId)elementId);
			}
			cgElementId = CGModelFactory.eINSTANCE.createCGElementId();
			cgElementId.setElementId(elementId);
			cgElementId.setTypeId(getCGTypeId(TypeId.OCL_ANY));		// XXX do better
			cgElementIds.put(elementId, cgElementId);
		}
		return cgElementId;
	}

	public @NonNull Iterable<@NonNull CGElementId> getCGElementIds() {
		return cgElementIds.values();
	}

	public @NonNull CGValuedElement getCGExpression(@Nullable CGValuedElement cgExpression) {
		if (cgExpression == null) {
			CGConstantExp cgLiteralExp = CGModelFactory.eINSTANCE.createCGConstantExp();
			//	cgLiteralExp.setAst(element);
			cgLiteralExp.setReferredConstant(getCGInvalid());
			cgLiteralExp.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
			cgExpression = cgLiteralExp;
		};
		return cgExpression;
	}

	public @NonNull CGInteger getCGInteger(@NonNull Number aNumber) {
		CGInteger cgInteger = cgIntegers.get(aNumber);
		if (cgInteger == null) {
			cgInteger = CGModelFactory.eINSTANCE.createCGInteger();
			cgInteger.setNumericValue(aNumber);
			cgInteger.setTypeId(getCGTypeId(TypeId.INTEGER));
			globalNameManager.getNameResolution(cgInteger);
			cgIntegers.put(aNumber, cgInteger);
		}
		return cgInteger;
	}

	public @NonNull CGInvalid getCGInvalid() {
		CGInvalid cgInvalid2 = cgInvalid;
		if (cgInvalid2 == null) {
			cgInvalid2 = CGModelFactory.eINSTANCE.createCGInvalid();
			//	cgInvalid.setAst(ValuesUtil.INVALID_VALUE);
			cgInvalid2.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
			globalNameManager.getNameResolution(cgInvalid2);
			cgInvalid = cgInvalid2;
		}
		return cgInvalid2;
	}

	public @NonNull CGInvalid getCGInvalid(/*@NonNull*/ String messageTemplate, Object... bindings) {
		CGInvalid cgInvalid = CGModelFactory.eINSTANCE.createCGInvalid();
		cgInvalid.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
		cgInvalid.setMessageTemplate(messageTemplate);
		for (Object binding : bindings) {
			cgInvalid.getBindings().add(binding);
		}
		globalNameManager.getNameResolution(cgInvalid);
		return cgInvalid;
	}

	public @NonNull CGNull getCGNull() {
		return cgNull;
	}

	public @NonNull CGOperation getCGOperation(@NonNull Operation asOperation) {
		return (CGOperation)ClassUtil.nonNullState(asElement2cgElement.get(asOperation));
	}

	public @NonNull CGPackage getCGPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return (CGPackage)ClassUtil.nonNullState(asElement2cgElement.get(asPackage));
	}

	public @NonNull CGProperty getCGProperty(@NonNull Property asProperty) {
		return (CGProperty)ClassUtil.nonNullState(asElement2cgElement.get(asProperty));
	}

	public @NonNull CGReal getCGReal(@NonNull Number aNumber) {
		CGReal cgReal = cgReals.get(aNumber);
		if (cgReal == null) {
			cgReal = CGModelFactory.eINSTANCE.createCGReal();
			cgReal.setNumericValue(aNumber);
			cgReal.setTypeId(getCGTypeId(TypeId.REAL));
			globalNameManager.getNameResolution(cgReal);
			cgReals.put(aNumber, cgReal);
		}
		return cgReal;
	}

/*	public @NonNull CGClass getCGRootClass(@NonNull CGNamedElement cgElement) {
		return ClassUtil.nonNullState(basicGetCGRootClass(cgElement));
	} */

	public @NonNull CGClass getCGRootClass(@NonNull Element asElement) {
		org.eclipse.ocl.pivot.Class asRootClass = basicGetRootClass(asElement);
		if (asRootClass != null) {
			return getCGClass(asRootClass);
		}
		assert asCurrentRootClass != null;
		return getCGClass(asCurrentRootClass);
	}

	public @NonNull CGString getCGString(@NonNull String aString) {
		CGString cgString = cgStrings.get(aString);
		if (cgString == null) {
			cgString = CGModelFactory.eINSTANCE.createCGString();
			cgString.setStringValue(aString);
			cgString.setTypeId(getCGTypeId(TypeId.STRING));
			globalNameManager.getNameResolution(cgString);
		//	globalNameManager.declareLazyName(cgString);
			cgStrings.put(aString, cgString);
		}
		return cgString;
	}

	public @NonNull CGTypeId getCGTypeId(@NonNull TypeId typeId) {
		CGElementId cgElementId = cgElementIds.get(typeId);
		CGTypeId cgTypeId = (CGTypeId)cgElementId;
		if (cgTypeId == null) {
			cgTypeId = CGModelFactory.eINSTANCE.createCGTypeId();
			cgTypeId.setElementId(typeId);
			globalNameManager.getNameResolution(cgTypeId);
		//	globalNameManager.declareLazyName(cgTypeId);
			cgElementIds.put(typeId, cgTypeId);
			if (typeId instanceof SpecializedId) {
				BindingsId templateBindings = ((SpecializedId)typeId).getTemplateBindings();
				for (int i = 0; i < templateBindings.elementIdSize(); i++) {
					ElementId elementId = templateBindings.getElementId(i);
					getCGElementId(elementId);
				}
			}
			cgTypeId.setTypeId(getCGTypeId(TypeId.OCL_ANY)); // XXX better tyoe ??
		}
		return cgTypeId;
	}

	public @NonNull CGUnlimited getCGUnlimited() {
		CGUnlimited cgUnlimited2 = cgUnlimited;
		if (cgUnlimited2 == null) {
			cgUnlimited2 = CGModelFactory.eINSTANCE.createCGUnlimited();
			cgUnlimited2.setTypeId(getCGTypeId(TypeId.UNLIMITED_NATURAL));
		//	globalNameManager.declareLazyName(cgUnlimited2); -- inlined so missing AST etc ok
			cgUnlimited = cgUnlimited2;
		}
		return cgUnlimited2;
	}

	/**
	 * Create or use the ClassNameManager for asClass exploiting an optionally already known cgClass.
	 */
	public @NonNull ClassNameManager getClassNameManager(@Nullable CGClass cgClass, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (cgClass == null) {
			cgClass = (CGClass)asElement2cgElement.get(asClass);
			if (cgClass == null) {
				cgClass = generateClassDeclaration(asClass, null);
			}
		}
		assert completeModel.getCompleteClass(asClass).getPrimaryClass() == cgClass.getAst();
		ClassNameManager classNameManager = (ClassNameManager)globalNameManager.basicGetChildNameManager(cgClass);
		if (classNameManager == null) {
			EObject eContainer = asClass.eContainer();
			ClassableNameManager classableNameManager = null;
			if (eContainer instanceof org.eclipse.ocl.pivot.Package) {
				classableNameManager = getPackageNameManager(null, (org.eclipse.ocl.pivot.Package)eContainer);
			}
			else if (eContainer instanceof org.eclipse.ocl.pivot.Package) {
				classableNameManager = getClassNameManager(null, (org.eclipse.ocl.pivot.Class)eContainer);
			}
			assert classableNameManager != null;
			classNameManager = globalNameManager.createClassNameManager(classableNameManager, cgClass);
		}
		return classNameManager;
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	public @NonNull ExecutableNameManager getConstraintNameManager(@NonNull CGConstraint cgConstraint, @NonNull Constraint asConstraint) {
		ExecutableNameManager constraintNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgConstraint);
		if (constraintNameManager == null) {			//
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getContainingClass(asConstraint);
			ClassNameManager classNameManager = getClassNameManager(null, asClass);
			constraintNameManager = globalNameManager.createConstraintNameManager(classNameManager, cgConstraint);
		}
		return constraintNameManager;
	}

	public @NonNull CGVariable getExecutorVariable(@NonNull ExecutableNameManager executableNameManager) {		// Overridden for JUnit support
		return executableNameManager.getExecutorVariableInternal();
	}

	public @Nullable UniqueList<@NonNull Feature> getExternalFeatures() {
		return externalFeatures;
	}

	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	public @NonNull GlobalNameManager getGlobalNameManager() {
		return globalNameManager;
	}

	public @Nullable Iterable<@NonNull CGValuedElement> getGlobals() {
		return cgGlobals;
	}

	public @Nullable CGValuedElement getInitExpression(/*@NonNull CGParameter cgSelfParameter,*/ @NonNull Property asProperty) {
		LanguageExpression specification = asProperty.getOwnedExpression();
		Object defaultValue = asProperty.getDefaultValue();
		CGValuedElement initExpression = null;
		if (specification != null) {
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					useExecutableNameManager(asProperty).getCGParameter(contextVariable, (String)null);
				}
				initExpression = createCGElement(CGValuedElement.class, query.getOwnedBody());
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			CGConstant cgConstant;
			if (defaultValue instanceof Boolean) {
				cgConstant = getCGBoolean(((Boolean)defaultValue).booleanValue());
			}
			else if (defaultValue instanceof IntegerValue) {		// ?? Long etc
				cgConstant = getCGInteger(((IntegerValue)defaultValue).asNumber());
			}
			else if (defaultValue instanceof RealValue) {
				cgConstant = getCGReal(((RealValue)defaultValue).asNumber());
			}
			else if (defaultValue instanceof String) {
				cgConstant = getCGString((String)defaultValue);
			}
			else if (defaultValue instanceof Number) {
				cgConstant = getCGReal((Number)defaultValue);
			}
			else {
				cgConstant = null;
			}
			if (cgConstant != null) {
				initExpression = createCGConstantExp(asProperty, cgConstant);
			}
		}
		return initExpression;
	}

	protected @Nullable CGValuedElement getInitExpression(@NonNull PropertyCallExp asPropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asPropertyCallExp);
		return getInitExpression(asProperty);
	}

	private @NonNull JavaLanguageSupport getJavaLanguageSupport() {
		return (JavaLanguageSupport)ClassUtil.nonNullState(codeGenerator.getEnvironmentFactory().getLanguageSupport("java"));
	}

	/**
	 * Create or use the ExecutableNameManager for asLoopExp exploiting an optionally already known cgIterationCallExp.
	 */
	public @NonNull ExecutableNameManager getLoopNameManager(@Nullable CGIterationCallExp cgIterationCallExp, @NonNull LoopExp asLoopExp) {
		if (cgIterationCallExp == null) {
			cgIterationCallExp = (CGIterationCallExp)asElement2cgElement.get(asLoopExp);
			if (cgIterationCallExp == null) {
				cgIterationCallExp = generateLoopDeclaration(asLoopExp);
			}
		}
		assert cgIterationCallExp.getAst() == asLoopExp;
		ExecutableNameManager loopNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgIterationCallExp);
		if (loopNameManager == null) {			//
			ExecutableNameManager parentNameManager = useExecutableNameManager((Element)asLoopExp.eContainer());
			ClassNameManager classNameManager = parentNameManager.getClassNameManager();
			loopNameManager = globalNameManager.createLoopNameManager(classNameManager, parentNameManager, cgIterationCallExp);
		}
		return loopNameManager;
	}

	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return codeGenerator.getEnvironmentFactory().getMetamodelManager();
	}

	public @NonNull NestedNameManager getNameManager() {			// XXX eliminate
//		assert currentNameManager != null;
//		return currentNameManager;
		throw new UnsupportedOperationException();
	}

	/**
	 * Return a native class for a Java class.
	 */
	public org.eclipse.ocl.pivot.@NonNull Class getNativeClass(@NonNull Class<?> jClass) {
		return getJavaLanguageSupport().getNativeClass(jClass);
	}

	/**
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method jMethod) {
		return getJavaLanguageSupport().getNativeOperation(jMethod);
	}

	/**
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {
		Operation asOperation = getNativeOperation(method);
		CGOperation cgOperation = (CGOperation)asElement2cgElement.get(asOperation);
		if (cgOperation == null) {
		//	org.eclipse.ocl.pivot.Class asClass = getNativeClass(method.getDeclaringClass());
		//	generateClassDeclaration(asClass, callingConvention.getClassCallingConvention());
		//	getClassNameManager(asClass);																// XXX redundant
			CGNativeOperation cgNativeOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();	// Use callingConvention
			cgNativeOperation.setAst(asOperation);
			TypeId asTypeId = asOperation.getTypeId();
			globalNameManager.getNameResolution(cgNativeOperation);
			cgNativeOperation.setTypeId(getCGTypeId(asTypeId));
			cgNativeOperation.setRequired(asOperation.isIsRequired());
			cgNativeOperation.setCallingConvention(callingConvention);
			cgNativeOperation.setAst(asOperation);
			getOperationNameManager(cgNativeOperation, asOperation);
			List<CGParameter> cgParameters = cgNativeOperation.getParameters();
			for (org.eclipse.ocl.pivot.Parameter asParameter : asOperation.getOwnedParameters()) {
				Type asParameterType = asParameter.getType();
				boolean isRequired = asParameter.isIsRequired();
				CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
				cgParameter.setAst(asParameter);
//				nameManager.declarePreferredName(cgParameter);
				cgParameter.setTypeId(getCGTypeId(asParameterType.getTypeId()));
				cgParameter.setRequired(isRequired);
				cgParameters.add(cgParameter);
			}
		}
		return asOperation;
	}

	/*
	 * Return a native property for field.
	 */
	public @NonNull Property getNativeProperty(@NonNull Field field) {
		return getJavaLanguageSupport().getNativeProperty(field);
	}

	/*
	 * Return a native property for field.
	 */
	public @NonNull Property getNativeProperty(@NonNull Field field, @NonNull PropertyCallingConvention callingConvention) {
		Property asProperty = getNativeProperty(field);
		CGProperty cgProperty = (CGProperty)asElement2cgElement.get(asProperty);
		if (cgProperty == null) {
		//	org.eclipse.ocl.pivot.Class asClass = getNativeClass(field.getDeclaringClass());
		//	generateClassDeclaration(asClass, callingConvention.getClassCallingConvention());
		//	getClassNameManager(asClass);				// XXX redundant
			CGNativeProperty cgNativeProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
			cgNativeProperty.setAst(asProperty);
			TypeId asTypeId = asProperty.getTypeId();
			globalNameManager.getNameResolution(cgNativeProperty);
			cgNativeProperty.setTypeId(getCGTypeId(asTypeId));
			cgNativeProperty.setRequired(asProperty.isIsRequired());
			cgNativeProperty.setCallingConvention(callingConvention);
			cgNativeProperty.setAst(asProperty);
			getPropertyNameManager(cgNativeProperty, asProperty);
		}
		return asProperty;
	}

	/**
	 * Create or use the OperationNameManager for asOperation exploiting an optionally already known cgOperation.
	 */
	public @NonNull ExecutableNameManager getOperationNameManager(@Nullable CGOperation cgOperation, @NonNull Operation asOperation) {
		if (cgOperation == null) {
			cgOperation = (CGOperation)asElement2cgElement.get(asOperation);
			if (cgOperation == null) {
				cgOperation = generateOperationDeclaration(asOperation, null, false);
			}
		}
		assert cgOperation.getAst() == asOperation;
		ExecutableNameManager operationNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgOperation);
		if (operationNameManager == null) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
			ClassNameManager classNameManager = getClassNameManager(null, asClass);
			operationNameManager = globalNameManager.createOperationNameManager(classNameManager, cgOperation);
		}
		return operationNameManager;
	}

	/**
	 * Create or use the PackageNameManager for asPackage exploiting an optionally already known cgPackage.
	 */
	public @NonNull PackageNameManager getPackageNameManager(@Nullable CGPackage cgPackage, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (cgPackage == null) {
			cgPackage = (CGPackage)asElement2cgElement.get(asPackage);
			if (cgPackage == null) {
				cgPackage = generatePackageDeclaration(asPackage);
			}
		}
		assert cgPackage.getAst() == completeModel.getCompletePackage(asPackage).getPrimaryPackage();
		PackageNameManager packageNameManager = (PackageNameManager)globalNameManager.basicGetChildNameManager(cgPackage);
		if (packageNameManager == null) {
			org.eclipse.ocl.pivot.Package asParentPackage = asPackage.getOwningPackage();
			if (asParentPackage != null) {
				PackageNameManager parentPackageNameManager = getPackageNameManager(null, asParentPackage);
				packageNameManager = globalNameManager.createPackageNameManager(parentPackageNameManager, cgPackage);
			}
			else {
				packageNameManager = globalNameManager.createPackageNameManager(null, cgPackage);
			}
		}
		return packageNameManager;
	}

	/**
	 * Create or use the PropertyNameManager for asProperty exploiting an optionally already known cgProperty.
	 */
	public @NonNull ExecutableNameManager getPropertyNameManager(@Nullable CGProperty cgProperty, @NonNull Property asProperty) {
		if (cgProperty == null) {
			cgProperty = (CGProperty)asElement2cgElement.get(asProperty);
			if (cgProperty == null) {
				cgProperty = generatePropertyDeclaration(asProperty, null);
			}
		}
		assert cgProperty.getAst() == asProperty;
		ExecutableNameManager propertyNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgProperty);
		if (propertyNameManager == null) {			//
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
			ClassNameManager classNameManager = getClassNameManager(null, asClass);
			propertyNameManager = globalNameManager.createPropertyNameManager(classNameManager, cgProperty);
		}
		return propertyNameManager;
	}

	public @NonNull CGParameter getSelfParameter(@NonNull ExecutableNameManager executableNameManager, @NonNull VariableDeclaration asParameter) {		// Overridden for OCLinEcore support
		CGParameter cgParameter = executableNameManager.basicGetCGParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(getCGTypeId(asParameter.getTypeId()));
			globalNameManager.getSelfNameResolution().addCGElement(cgParameter);
			executableNameManager.addVariable(asParameter, cgParameter);
			boolean isRequired = asParameter.isIsRequired();
			cgParameter.setRequired(isRequired);
			if (isRequired) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	public @NonNull StandardLibraryInternal getStandardLibrary() {
		return standardLibrary;
	}

	public boolean hasOclVoidOperation(@NonNull OperationId operationId) {
		CompleteClass completeClass = metamodelManager.getCompleteClass(standardLibrary.getOclVoidType());
		Operation memberOperation = completeClass.getOperation(operationId);
		if (memberOperation == null) {
			return false;
		}
		org.eclipse.ocl.pivot.Class owningType = memberOperation.getOwningClass();
		if (owningType == null) {
			return false;
		}
		CompleteClass owningCompleteClass = metamodelManager.getCompleteClass(owningType);
		return completeClass == owningCompleteClass;
	}

	/**
	 * Establish the cgElement.ast to asElement mapping and the corresponding cgElement.typeId. If isSymmetric, install
	 * the reverse asElement2cgElement mapping.
	 */
	public void initAst(@NonNull CGValuedElement cgElement, @NonNull TypedElement asTypedElement, boolean isSymmetric) {
	//	TypeId asTypeId = asElement.getTypeId();
	//	initAst(cgElement, asElement, asTypeId, isSymmetric);
	//	CGTypeId cgTypeId = getCGTypeId(asTypeId);
	//	cgElement.setTypeId(cgTypeId);
	//	cgElement.setRequired(asElement.isIsRequired());
		initTypeId(cgElement, asTypedElement.getTypeId(), asTypedElement.isIsRequired());
		initCG2AS(cgElement, asTypedElement, isSymmetric);
	}

	/**
	 * Establish the cgElement.ast to asElement and the cgElement.typeId to asTypeId mappings. If isSymmetric, install
	 * the reverse asElement2cgElement mapping.
	 */
	public void initAst(@NonNull CGValuedElement cgElement, @NonNull TypedElement asElement, @NonNull TypeId asTypeId, boolean isSymmetric) {
		CGTypeId cgTypeId = getCGTypeId(asTypeId);
		cgElement.setTypeId(cgTypeId);
		cgElement.setRequired(asElement.isIsRequired());
		initCG2AS(cgElement, asElement, isSymmetric);
	}
	// Variant for AS reference to AS
	public void initAst(@NonNull CGTypedElement cgElement, @NonNull Element asElement, @NonNull TypedElement asTypedElement, boolean isSymmetric) {
		initTypeId(cgElement, asTypedElement.getTypeId(), asTypedElement.isIsRequired());
		initCG2AS(cgElement, asElement, isSymmetric);
	}

	private void initCG2AS(@NonNull CGTypedElement cgElement, @NonNull Element asElement, boolean isSymmetric) {
		if ((asElement instanceof Operation) && asElement.toString().contains("::_unqualified_env_Package(")) {
			getClass();		// XXX
		}
		cgElement.setAst(asElement);
		if (isSymmetric) {
			CGNamedElement old = asElement2cgElement.put(asElement, cgElement);
			assert old == null;
		}
	}

	private void initTypeId(@NonNull CGTypedElement cgElement, @NonNull TypeId asTypeId, boolean isRequired) {
		CGTypeId cgTypeId = getCGTypeId(asTypeId);
		cgElement.setTypeId(cgTypeId);
		cgElement.setRequired(isRequired);
	}

	public @Nullable CGValuedElement inlineOperationCall(@NonNull OperationCallExp callExp, @NonNull LanguageExpression specification) {
		ExpressionInOCL prototype = null;
		try {
			prototype = environmentFactory.parseSpecification(specification);
		}
		catch (ParserException e) {
			// FIXME log error
			e.printStackTrace();
		}
		if (prototype == null) {
			return null;
		}
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		Set<@NonNull Operation> referencedFinalOperations = new HashSet<>();
		codeGenerator.getTransitivelyReferencedFinalOperations(referencedFinalOperations, finalAnalysis, specification);
		if (referencedFinalOperations.contains(callExp.getReferredOperation())) {
			return null;	// Avoid an infinite inlining recursion.
		}
		Iterable<@NonNull Operation> referencedNonFinalOperations = codeGenerator.getReferencedNonFinalOperations(finalAnalysis, specification);
		if (referencedNonFinalOperations != null) {
			return null;	// Simple heavy heuristic
		}
		throw new UnsupportedOperationException();
	/*/	assert false;
		ExpressionInOCL asClone = createCopy(prototype);
		OCLExpression asExpression = ClassUtil.nonNullState(asClone.getOwnedBody());
		List<@NonNull OCLExpression> asArguments = ClassUtil.nullFree(callExp.getOwnedArguments());
		int argumentsSize = asArguments.size();
		if (argumentsSize > 0) {
			List<@NonNull Parameter> asParameters = ClassUtil.nullFree(callExp.getReferredOperation().getOwnedParameters());
			List<@NonNull Variable> asParameterVariables = ClassUtil.nullFree(asClone.getOwnedParameters());
			List<@NonNull Variable> asVariables = new ArrayList<>(asParameterVariables);
			asParameterVariables.clear();				// Defeat child-stealing detector
			for (@NonNull Variable asVariable : asVariables) {
				Parameter asParameter = asVariable.getRepresentedParameter();
				if (asParameter != null) {
					int index = asParameters.indexOf(asParameter);
					if ((0 <= index) && (index < argumentsSize)) {
						asExpression = createLetExp(asVariable, asArguments.get(index), asExpression);
					}
				}
			}
		}
		Variable asVariable = asClone.getOwnedContext();
		asClone.setOwnedContext(null);				// Defeat child-stealing detector
		asExpression = createLetExp(asVariable, callExp.getOwnedSource(), asExpression);
		ASResource asResource = (ASResource) specification.eResource();
		try {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().add(asExpression);					// Ensure that asExpression is not a Resource-less orphan; needed for FlowAnalysis
			asResource.setUpdating(wasUpdating);
			return doVisit(CGValuedElement.class, asExpression);
		}
		finally {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().remove(asExpression);
			asResource.setUpdating(wasUpdating);
		} */
	}

	public boolean isExternal(@NonNull Feature asFeature) {
		return (externalFeatures != null) && externalFeatures.contains(asFeature);
	}

	/**
	 * Replace oldElement by newElement and return oldElement which is orphaned by the replacement.
	 */
	public void replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement,
			/*@NonNull*/ String messageTemplate, Object... bindings) {
		assert oldElement.eContainer() != null;
		if (oldElement.isRequired() && newElement.isNull()) {
			newElement = getCGInvalid(messageTemplate, bindings);
		}
		assert newElement.eContainer() == null;			// Detect child stealing detector four calls sooner than eBasicSetContainer().
		globalNameManager.replace(oldElement, newElement);
	}

	public void setCGConstant(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement aConstant) {
		CGConstantExp newElement = CGModelFactory.eINSTANCE.createCGConstantExp();		// FIXME wrapper not needed
		newElement.setReferredConstant(aConstant);
		newElement.setTypeId(oldElement.getTypeId());
		newElement.setAst(oldElement.getAst());
		globalNameManager.replace(oldElement, newElement);
	}

//	public void setCGRootClass(@NonNull CGClass cgClass) {
//		CGPackage cgPackage = (CGPackage)cgClass.eContainer();
//		CGClass old = cgPackage2cgRootClass.put(cgPackage, cgClass);
//		assert old == null;
//	}

	public void setCGVariableInit(@NonNull CGVariable cgVariable, @NonNull CGValuedElement cgInit) {
	//	NameResolution variableNameResolution = cgVariable.getNameResolution();
		if (cgInit.basicGetNameResolution() == null) {
			//
			//	Propagate the variable name resolution to its initializer and intervening lets.
			//
			CGValuedElement cgElement = cgInit;
			while (cgElement.basicGetNameResolution() == null) {
			//	variableNameResolution.addCGElement(cgElement);			// XXX
				if (cgElement instanceof CGLetExp) {
					cgElement = CGUtil.getIn((CGLetExp)cgElement);
				}
				else {
					break;
				}
			}
		}
		cgVariable.setInit(cgInit);
	}

	public void setGlobals(@Nullable Iterable<@NonNull CGValuedElement> cgGlobals) {
		this.cgGlobals  = cgGlobals;
	}

	private void setNullableIterator(@NonNull CGIterator cgIterator, @NonNull Variable iterator) {
		cgIterator.setTypeId(getCGTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
	}

	public void setRootClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		asCurrentRootClass = asClass;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(asClass);
		CompletePackage completePackage = completeModel.getCompletePackage(asPackage);
		org.eclipse.ocl.pivot.Class old = completePackage2asRootClass.put(completePackage, asClass);
		assert old == null;
	}

	public @NonNull ExecutableNameManager useExecutableNameManager(@NonNull Element asElement) {
		for (EObject eObject = asElement; eObject != null; eObject = eObject.eContainer()) {
			CGNamedElement cgElement = asElement2cgElement.get(eObject);
			if (cgElement != null) {
				ExecutableNameManager executableNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgElement);
				if (executableNameManager != null) {
					return ClassUtil.nonNullState(executableNameManager);
				}
			}
			else {
				assert !(eObject instanceof Operation) : "Missing NameManager for " + asElement.eClass().getName() + ": " + asElement;
				assert !(eObject instanceof Package) : "Missing NameManager for " + asElement.eClass().getName() + ": " + asElement;
				assert !(eObject instanceof Property) : "Missing NameManager for " + asElement.eClass().getName() + ": " + asElement;
				assert !(eObject instanceof Type) : "Missing NameManager for " + asElement.eClass().getName() + ": " + asElement;
			}
		}
		throw new IllegalStateException("No ExecutableNameManager for " + asElement.eClass().getName() + ": " + asElement);
	}

//	public @NonNull NameManager useSelfNameManager(@NonNull Element asElement) {
//		return ClassUtil.nonNullState(basicUseSelfNameManager(asElement));
//	}
}
