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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.AbstractOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ForeignClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.IterationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.VirtualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
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
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
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
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
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
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
	 * All CGClasses forming a queue between their discovery with package/supertype hierarchy resolution and
	 * the deferred content resolution. Soe classes are user defined, some are synthesized to support
	 * the additional CGClasses that have no counterpart in the original AS hierarchy. These may implement caches or
	 * may reify functionality locally for which no external implementation is available.
	 */
	private @NonNull ArrayList<@NonNull CGClass> cgClassesQueue = new ArrayList<>();
	private int cgClassesQueueHead = 0;

	/**
	 * Mapping from each AS Element to its corresponding CGNamedElement. (Variables are mapped by the prevailing
	 * ExecutablenManager since variables can be multiple synthesized.) For overloaded operations, this mapping is
	 * from the AS operation to its CG implementation; the mapping to the virtual dispatcher is in asVirtualOperation2cgOperation.
	 */
	protected @NonNull Map<@NonNull Element, @NonNull CGNamedElement> asElement2cgElement = new HashMap<>();

	/**
	 * Mapping from each AS Operation within an override hierarchy to its corresponding virtual dispatching CG Operation.
	 */
	private final @NonNull Map<@NonNull Operation, @NonNull CGOperation> asVirtualOperation2cgDispatchOperation = new HashMap<>();

	/**
	 * Mapping from each cached CG Operation to its original AS Operation when ditinct from the cached AS Operation.
	 */
	private final @NonNull Map<@NonNull CGOperation, @NonNull Operation> cgOperation2asOriginalOperation = new HashMap<>();

	/**
	 * Mapping from each cached CG Operation to its original AS Operation when ditinct from the cached AS Operation.
	 */
	private final @NonNull Map<@NonNull CGOperation, @NonNull Property> cgOperation2asOriginalProperty = new HashMap<>();

	private @NonNull Map<@NonNull Operation, @NonNull OperationCache> asOperation2operationCache = new HashMap<>();
	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull AbstractCache> asCacheClass2abstractCache = new HashMap<>();

	private @Nullable Iterable<@NonNull CGValuedElement> cgGlobals = null;

	/**
	 * The 'opposite' of the CGValuedElement to referenced/unreferencedExtraChild relationship that the
	 * prevailing CSE requires.This should go obsolete with a CSE / CGLetExp redesign.
	 */
	private final @NonNull Map<@NonNull CGValuedElement, @NonNull List<@NonNull CGValuedElement>> extraChild2parents = new HashMap<>();

	/**
	 * The unreferencedExtraChild relationship that the prevailing CSE requires.
	 * This should go obsolete with a CSE / CGLetExp redesign.
	 */
	private final @NonNull Map<@NonNull CGValuedElement, @NonNull List<@NonNull CGValuedElement>> parent2unreferencedExtraChildren = new HashMap<>();

	/**
	 * AbstractCache describes the AS class and instance that cache distinct evaluations of a derived capability.
	 */
	protected static abstract class AbstractCache
	{
		private org.eclipse.ocl.pivot.@NonNull Class asCacheClass;
		private @NonNull Property asCacheInstance;
		private org.eclipse.ocl.pivot.@NonNull Class asEntryClass;

		protected AbstractCache(@NonNull Property asCacheInstance, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
			this.asCacheClass = (org.eclipse.ocl.pivot.Class)PivotUtil.getType(asCacheInstance);
			this.asCacheInstance = asCacheInstance;
			this.asEntryClass = asEntryClass;
		}

		public org.eclipse.ocl.pivot.@NonNull Class getASCacheClass() {
			return asCacheClass;
		}

		public @NonNull Property getASCacheInstance() {
			return asCacheInstance;
		}

		public org.eclipse.ocl.pivot.@NonNull Class getASEntryClass() {
			return asEntryClass;
		}
	}

	/**
	 * OperationCache describes the AS class and instance that caches distinct evaluations of an operation.
	 */
	protected static class OperationCache extends AbstractCache
	{
		private @NonNull Operation asOperation;

		protected OperationCache(@NonNull Operation asOperation, @NonNull Property asCacheInstance, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
			super(asCacheInstance, asEntryClass);
			this.asOperation = asOperation;
		}

		public @NonNull Operation getOperation() {
			return asOperation;
		}
	}

	/**
	 * Mapping from original AS Operation to the AS Class that caches the operations evaluation.
	 */
	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Operation> asCacheClass2asOperation = new HashMap<>();

	/**
	 * The injected class within which the stubsfor fforeign properties are realized.
	 */
	private /*@LazyNonNull*/ CGClass cgForeignClass = null;

	private /*@LazyNonNull*/ Map<@NonNull Property, @NonNull CGOperation> asProperty2foreignCGOperation = null;

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

	public void addCGCachedOperation(@NonNull CGCachedOperation cgOperation, @NonNull Operation asOperation) {
		Operation oldOperation = cgOperation2asOriginalOperation.put(cgOperation, asOperation);
		assert oldOperation == null;
	}

	public void addCacheInstance(@NonNull Operation asOperation, @NonNull Property asCacheInstance, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		OperationCache operationCache = new OperationCache(asOperation, asCacheInstance, asEntryClass);
		addCacheInstanceInternal(operationCache);
		AbstractCache old = asOperation2operationCache.put(asOperation, operationCache);
		assert old == null;
	}

/*	public void addCacheInstance(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull Property asCacheInstance, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		AbstractCache cacheClassData = addCacheInstanceInternal(asClass, asCacheInstance, asEntryClass);
	//	AbstractCache old = asOperation2cacheClassData.put(asOperation, cacheClassData);
	//	assert old == null;
	} */

	protected void addCacheInstanceInternal(@NonNull AbstractCache abstractCache) {
		org.eclipse.ocl.pivot.Class asCacheClass = abstractCache.getASCacheClass();
		AbstractCache old = asCacheClass2abstractCache.put(asCacheClass, abstractCache);
		assert old == null;
		org.eclipse.ocl.pivot.Class asContextClass = codeGenerator.getContextClass();
		asContextClass.getOwnedProperties().add(abstractCache.getASCacheInstance());
	}

	public void addCachedOperation(org.eclipse.ocl.pivot.@NonNull Class asCacheClass, @NonNull Operation asOperation) {
		Operation oldOperation = asCacheClass2asOperation.put(asCacheClass, asOperation);		// XXX redundant wrt cacheData
		assert oldOperation == null;
	}

	public void addForeignCGOperation(@NonNull Property asProperty, @NonNull CGOperation cgForeignOperation) {
		if (asProperty2foreignCGOperation == null) {
			asProperty2foreignCGOperation = new HashMap<>();
		}
		CGOperation old1 = asProperty2foreignCGOperation.put(asProperty, cgForeignOperation);
		assert old1 == null;
		Property old2 = cgOperation2asOriginalProperty.put(cgForeignOperation, asProperty);
		assert old2 == null;
	}

	public void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globalNameManager.addGlobal(cgGlobal);
	}

	public void addReferencedExtraChild(@NonNull CGValuedElement extraChildParent, @NonNull CGValuedElement extraChild) {
		assert extraChild == extraChildParent.getReferencedExtraChild(this);
		List<@NonNull CGValuedElement> parents = extraChild2parents.get(extraChild);
		if (parents == null) {
			parents = new ArrayList<>();
			extraChild2parents.put(extraChild, parents);
		}
		assert !parents.contains(extraChildParent);
		parents.add(extraChildParent);
	//	System.out.println("addReferencedExtraChild " + NameUtil.debugSimpleName(extraChildParent) + ":" +  extraChildParent + "   " + NameUtil.debugSimpleName(extraChild) + ":" +  extraChild);
	}

	public void addUnreferencedExtraChild(@NonNull CGValuedElement extraChildParent, @NonNull CGValuedElement extraChild) {
		List<@NonNull CGValuedElement> unreferencedExtraChildren = parent2unreferencedExtraChildren.get(extraChildParent);
		if (unreferencedExtraChildren == null) {
			unreferencedExtraChildren = new ArrayList<>();
			parent2unreferencedExtraChildren.put(extraChildParent, unreferencedExtraChildren);
		}
		assert !unreferencedExtraChildren.contains(extraChild);
		unreferencedExtraChildren.add(extraChild);
		List<@NonNull CGValuedElement> parents = extraChild2parents.get(extraChild);
		if (parents == null) {
			parents = new ArrayList<>();
			extraChild2parents.put(extraChild, parents);
		}
		assert !parents.contains(extraChildParent);
		parents.add(extraChildParent);
	//	System.out.println("addUnreferencedExtraChild " + NameUtil.debugSimpleName(extraChildParent) + ":" +  extraChildParent + "   " + NameUtil.debugSimpleName(extraChild) + ":" +  extraChild);
	}

//	public void addVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
//		CGNamedElement old = asElement2cgElement.put(asVariable, cgVariable);
//		assert old == null;
//	}

	public @NonNull Iterable<@NonNull Operation> addVirtualCGOperations(@NonNull Operation asBaseOperation, @NonNull CGCachedOperation cgDispatchOperation) {
		assert cgDispatchOperation.getCallingConvention() == VirtualOperationCallingConvention.getInstance(asBaseOperation, true);
		addCGCachedOperation(cgDispatchOperation, asBaseOperation);
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		Iterable<@NonNull Operation> asOverrideOperations = finalAnalysis.getOverrides(asBaseOperation);
		assert Iterables.contains(asOverrideOperations, asBaseOperation);
		assert Iterables.size(asOverrideOperations) > 1;
		for (@NonNull Operation asOverrideOperation : asOverrideOperations) {
			//	System.out.println("addVirtualCGOperation " + NameUtil.debugSimpleName(cgDispatchOperation) + " => " +  NameUtil.debugSimpleName(asOperation) + " : " + asOperation);	// XXX debugging
			if (asOverrideOperation.toString().contains("::_unqualified_env_Class(O")) {
				getClass();		// XXX
			}
			assert !asVirtualOperation2cgDispatchOperation.containsKey(asOverrideOperation);		// XXX
		//	assert cgOperation.getAst() == asOperation;
			assert basicGetCGOperation(asOverrideOperation) == null : "Virtuals must be installed first";		// XXX
			CGOperation oldCGOperation = basicGetCGOperation(asOverrideOperation);
		//	assert (oldCGOperation != null) && (oldCGOperation != cgDispatchOperation);
			oldCGOperation = asVirtualOperation2cgDispatchOperation.put(asOverrideOperation, cgDispatchOperation);
			assert oldCGOperation == null;
		//	addCGOperation(cgOperation);
			OperationCallingConvention callingConvention = cgDispatchOperation.getCallingConvention();
			if (callingConvention.needsGeneration()) {
				CGClass cgRootClass = getCGRootClass(asOverrideOperation);
			//	cgRootClass.getOperations().add(cgDispatchOperation);
				// throw new UnsupportedOperationException();			// XXX cgRootClass
			}
		}
		return asOverrideOperations;
	}

	public void analyze(@NonNull Iterable<@NonNull CGPackage> cgPackages) {
		AnalysisVisitor analysisVisitor = codeGenerator.createAnalysisVisitor();
		for (@NonNull CGPackage cgPackage : cgPackages) {
			cgPackage.accept(analysisVisitor);
		}
		assert checkNameManagers(cgPackages);
		//
		BoxingAnalyzer boxingAnalyzer = codeGenerator.createBoxingAnalyzer();
		for (@NonNull CGPackage cgPackage : cgPackages) {
			cgPackage.accept(boxingAnalyzer);
		}
		//
		FieldingAnalyzer fieldingAnalyzer = codeGenerator.createFieldingAnalyzer();
		fieldingAnalyzer.analyze(cgPackages, false);
		assert checkNameManagers(cgPackages);
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

	public @Nullable CGOperation basicGetCGDispatchOperation(@NonNull Operation asOperation) {
		return asVirtualOperation2cgDispatchOperation.get(asOperation);
	}

	public @Nullable CGElement basicGetCGElement(@NonNull Operation asOperation) {
		return asElement2cgElement.get(asOperation);
	}

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

	public @Nullable Operation basicGetCachedOperation(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return asCacheClass2asOperation.get(asClass);
	}

	public @Nullable NestedNameManager basicGetNameManager() {
//		return currentNameManager;
		throw new UnsupportedOperationException();
	}

	public @Nullable Operation basicGetOriginalOperation(@NonNull CGOperation cgOperation) {
		return cgOperation2asOriginalOperation.get(cgOperation);
	}

	protected org.eclipse.ocl.pivot.@Nullable Class basicGetRootClass(@NonNull Element asElement) {	// XXX
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
		assert asPackage != null;
		CompletePackage completePackage = completeModel.getCompletePackage(asPackage);
		return completePackage2asRootClass.get(completePackage);
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
	protected boolean checkNameManagers(@NonNull Iterable<@NonNull CGPackage> cgPackages) {
		for (@NonNull CGPackage cgPackage : cgPackages) {
			for (@NonNull EObject eObject : new TreeIterable(cgPackage, true)) {
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
		cgBoolean.setRequired(true);
		globalNameManager.getNameResolution(cgBoolean);
		return cgBoolean;
	}

	public @NonNull CGCastExp createCGCastExp(@NonNull CGExecutorType cgExecutorType, @NonNull CGValuedElement cgValue) {
		CGCastExp cgCastExp = CGModelFactory.eINSTANCE.createCGCastExp();
		cgCastExp.setSource(cgValue);
		cgCastExp.setExecutorType(cgExecutorType);
		cgCastExp.setTypeId(getCGTypeId(CGUtil.getAST(cgExecutorType).getTypeId()));
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

	public @NonNull CGFinalVariable createCGFinalVariable(@Nullable NameResolution nameResolution, @NonNull CGTypeId typeId, boolean isRequired) {
		CGFinalVariable cgFinalVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgFinalVariable.setTypeId(typeId);
		cgFinalVariable.setRequired(isRequired);
		if (nameResolution != null) {
			nameResolution.addCGElement(cgFinalVariable);
		}
		return cgFinalVariable;
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

	public @NonNull CGIndexExp createCGIndexExp(@NonNull CGValuedElement cgValue, int index, @NonNull TypedElement asTypedElement) {
		CGIndexExp cgIndexExp = CGModelFactory.eINSTANCE.createCGIndexExp();
		initAst(cgIndexExp, asTypedElement, false);
		cgIndexExp.setSource(cgValue);
		cgIndexExp.setIndex(createCGConstantExp(getCGInteger(index)));
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

	public @NonNull CGValuedElement createCGIsEqual2(@NonNull CGValuedElement cgLeft, @NonNull CGValuedElement cgRight) {
		CGIsEqual2Exp cgIsEqual = CGModelFactory.eINSTANCE.createCGIsEqual2Exp();
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
//		cgLetExp.setAst(asElement);
//		cgLetExp.setTypeId(getCGTypeId(asElement.getTypeId()));
		initAst(cgLetExp, asElement, false);
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		assert cgLetExp.isRequired() == cgIn.isRequired();
		return cgLetExp;
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {
		Operation asOperation = getNativeOperation(method);
		CGOperation cgOperation = generateOperationDeclaration(asOperation, callingConvention, true);
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setMethod(method);		// Use cc
		cgNativeOperationCallExp.setReferredOperation(cgOperation);
		cgNativeOperationCallExp.setRequired(cgOperation.isRequired());
		return cgNativeOperationCallExp;
	}

	public @NonNull CGNativePropertyCallExp createCGNativePropertyCallExp(@NonNull Field field, @NonNull PropertyCallingConvention callingConvention) {
		Property asProperty = getNativeProperty(field, callingConvention);
		CGProperty cgProperty = getCGProperty(asProperty);
		CGNativePropertyCallExp cgNativePropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
		cgNativePropertyCallExp.setField(field);		// Use cc
		cgNativePropertyCallExp.setReferredProperty(cgProperty);
		cgNativePropertyCallExp.setRequired(cgProperty.isRequired());
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
		return cgParameter;
	}

	public @NonNull CGVariableExp createCGVariableExp(@NonNull CGVariable cgVariable) {
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
	//	setAst(cgVariableExp, asVariableExp);
		cgVariableExp.setReferredVariable(cgVariable);
		cgVariableExp.setTypeId(cgVariable.getTypeId());
		cgVariableExp.setRequired(cgVariable.isRequired());
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
		if (asClass.getName().equals("Boolean")) {
			getClass();		// XXX
		}
		if (cgClass == null) {
			cgClass = basicGetCGClass(asClass);
			if (cgClass == null) {
				cgClass = generateClassDeclaration(asClass, null);
				assert cgClass.eContainer() != null;
			}
		}
		getClassNameManager(cgClass, asClass);			// Nominally redundant here but needed downstream
		for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : ClassUtil.nullFree(asClass.getSuperClasses())) {
			CGClass cgSuperClass = generateClassDeclaration(asSuperClass, null);
			cgClass.getSuperTypes().add(cgSuperClass);
		}
		queueCGClassDeclaration(cgClass);
		return cgClass;
	}

	public void generateClassContents(@NonNull CGClass cgClass) {
		org.eclipse.ocl.pivot.@NonNull Class asClass = CGUtil.getAST(cgClass);
		for (@NonNull Property asProperty : ClassUtil.nullFree(asClass.getOwnedProperties())) {
			CGProperty cgProperty = createCGElement(CGProperty.class, asProperty);
		//	assert cgClass.getProperties().contains(cgProperty);
			assert cgProperty.eContainer() != null;			// May be the cgForeignClass
		}
		for (@NonNull Constraint asConstraint : ClassUtil.nullFree(asClass.getOwnedInvariants())) {
			CGConstraint cgConstraint = createCGElement(CGConstraint.class, asConstraint);
			assert cgClass.getInvariants().contains(cgConstraint);
		}
		if (asClass.getName().equals("Boolean")) {
			getClass();		// XXX
		}
		for (@NonNull Operation asOperation : ClassUtil.nullFree(asClass.getOwnedOperations())) {
			CGOperation cgOperation = createCGElement(CGOperation.class, asOperation);
			assert cgClass.getOperations().contains(cgOperation);
		}
	}

	/**
	 * Generate / share the CG declaration for asClass.
	 */
	public @NonNull CGClass generateClassDeclaration(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable ClassCallingConvention callingConvention) {
		CGClass cgClass = (CGClass)asElement2cgElement.get(asClass);
		if (cgClass == null) {
			CompleteClass completeClass = completeModel.getCompleteClass(asClass);
		//	asClass = completeClass.getPrimaryClass();
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
			initAst(cgConstraint, asConstraint, TypeId.BOOLEAN, true);
			generateConstraintBody(cgConstraint, asConstraint);
			CGClass cgClass = getCGClass(PivotUtil.getContainingClass(asConstraint));		// XXX
			cgClass.getInvariants().add(cgConstraint);
		}
		return cgConstraint;
	}

	protected void generateConstraintBody(@NonNull CGConstraint cgConstraint, @NonNull Constraint asConstraint) {
		throw new UnsupportedOperationException("generateConstraintBody only supported for OCLinEcore");
	/*		LanguageExpression specification = asConstraint.getOwnedSpecification();
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
		} */
	}

	/**
	 * Generate / share the CG declaration for asIteration.
	 */
	public @NonNull CGOperation generateIterationDeclaration(@NonNull Iteration asIteration) {
		CGOperation cgIteration = basicGetCGOperation(asIteration);
		if (cgIteration == null) {
			assert asIteration.getBodyExpression() == null;
			IterationCallingConvention callingConvention = codeGenerator.getCallingConvention(asIteration);
			cgIteration = callingConvention.createIteration(this, asIteration);
		}
		return cgIteration;
	}

	protected @NonNull CGIterationCallExp generateLoopExp(@NonNull LoopExp asLoopExp) {
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		CGValuedElement cgUnsafeSource = createCGElement(CGValuedElement.class, asLoopExp.getOwnedSource());
		CGValuedElement cgSafeSource = asLoopExp.isIsSafe() ? generateSafeExclusion(asLoopExp, cgUnsafeSource) : cgUnsafeSource;
	//	OCLExpression asSource = asLoopExp.getOwnedSource();
	//	Type asSourceType = asSource != null ? asSource.getType() : null;
		CGOperation cgIteration = generateIterationDeclaration(/*asSourceType,*/ asIteration);
		IterationCallingConvention callingConvention = (IterationCallingConvention)cgIteration.getCallingConvention();
		LibraryIteration libraryIteration = (LibraryIteration)metamodelManager.getImplementation(asIteration);
		CGIterationCallExp cgIterationCallExp = (CGIterationCallExp)asElement2cgElement.get(asLoopExp);
		assert cgIterationCallExp == null;
		cgIterationCallExp = callingConvention.createCGIterationCallExp(this, cgIteration, libraryIteration, cgSafeSource, asLoopExp);
		CGNamedElement old = asElement2cgElement.put(asLoopExp, cgIterationCallExp);
	//	assert old == cgIterationCallExp;			// XXX demonstrates that put is redundant
		return cgIterationCallExp;
	}

	/**
	 * Generate / share the CG declaration for asOperation.
	 */
	public @NonNull CGOperation generateMaybeVirtualOperationDeclaration(@NonNull Operation asOperation) {
		return generateOperationDeclaration(asOperation, null, true);
	}

	protected void generateNestedPackages(@NonNull CGPackage cgPackage, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		List<org.eclipse.ocl.pivot.@NonNull Package> asPackages = new ArrayList<>(ClassUtil.nullFree(asPackage.getOwnedPackages()));
		Collections.sort(asPackages, NameUtil.NAMEABLE_COMPARATOR);
		for (org.eclipse.ocl.pivot.@NonNull Package asNestedPackage : asPackages) {
			CGPackage cgNestedPackage2 = basicGetCGPackage(asNestedPackage);
			if (cgNestedPackage2 == null) {
				CGPackage cgNestedPackage = createCGElement(CGPackage.class, asNestedPackage);
				assert cgPackage.getPackages().contains(cgNestedPackage);
			}
			else {		// Nested support/foreign CGPackage may already exist - e.g. in testQVTcCompiler_HSVToHSL_CG
				assert NameUtil.getNameable(CGUtil.getClasses(cgPackage), asNestedPackage.getName()) != null;
			}
		}
	}

	public @NonNull CGOperation generateNonVirtualOperationDeclaration(@NonNull Operation asOperation) {
		return generateOperationDeclaration(asOperation, null, false);
	}

	public @NonNull CGOperation generateOperation(@NonNull Operation asOperation) {
	//	asOperation2cgOperation.get(asOperation);
		LanguageExpression specification = asOperation.getBodyExpression();
		CGOperation cgFinalOperation = generateNonVirtualOperationDeclaration(asOperation);
		assert cgFinalOperation.getBody() == null;
//		System.out.println("visitOperation " + NameUtil.debugSimpleName(cgFinalOperation) + " : " + asOperation);
		if (specification instanceof ExpressionInOCL) {			// Should already be parsed
//			cgFinalOperation.getCallingConvention().createCGBody(this, cgFinalOperation);
//			scanBody(specification);
		}
		CGOperation cgVirtualOperation = generateMaybeVirtualOperationDeclaration(asOperation);
		if (cgVirtualOperation != cgFinalOperation) {
			assert cgVirtualOperation.getBody() == null;
//			System.out.println("visitOperation " + NameUtil.debugSimpleName(cgVirtualOperation) + " : " + asOperation);
			getOperationNameManager(cgVirtualOperation, asOperation);
			if (specification instanceof ExpressionInOCL) {			// Should already be parsed
//				cgVirtualOperation.getCallingConvention().createCGBody(this, cgVirtualOperation);
//				scanBody(specification);
			}
		}
		return cgFinalOperation;
	}

	public @NonNull CGValuedElement generateOperationCallExp(@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		CGOperation cgOperation = generateMaybeVirtualOperationDeclaration(asOperation);
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
	private @NonNull CGOperation generateOperationDeclaration(@NonNull Operation asOperation, @Nullable OperationCallingConvention callingConvention, boolean maybeVirtual) {	// XXX rationalize as generateOperationDeclaration with later createImplementation
	//	if (asOperation.toString().contains("::_unqualified_env_Class(")) {
	//		getClass();		// XXX
	//	}
		if (maybeVirtual) {			// If virtual dispatch already known.
			CGOperation cgVirtualOperation = basicGetCGDispatchOperation(asOperation);
			if (cgVirtualOperation != null) {
				return cgVirtualOperation;
			}
		}
		CGOperation cgOperation = basicGetCGOperation(asOperation);
		if (cgOperation == null) {
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
			if (callingConvention == null) {
				callingConvention = codeGenerator.getCallingConvention(asOperation, maybeVirtual);
			}
			cgOperation = callingConvention.createOperation(this, asOperation, asExpressionInOCL);
			if (asSpecification != null) {
				scanBody(asSpecification);
			}
		}
		if (maybeVirtual) {					// If virtual dispatch now known.
			CGOperation cgVirtualOperation = basicGetCGDispatchOperation(asOperation);
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

	public @NonNull CGPackage generatePackage(@Nullable CGPackage cgPackage, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (cgPackage == null) {
			cgPackage = basicGetCGPackage(asPackage);
			if (cgPackage == null) {
				cgPackage = generatePackageDeclaration(asPackage);
			//	assert cgPackage.eContainer() != null;
			}
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = new ArrayList<>(ClassUtil.nullFree(asPackage.getOwnedClasses()));
		Collections.sort(asClasses, NameUtil.NAMEABLE_COMPARATOR);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : asClasses) {
			CGClass cgClass = createCGElement(CGClass.class, asClass);
		//	assert cgPackage.getClasses().contains(cgClass);
			assert cgClass.eContainer().eContainer() == cgPackage.eContainer();			// asClass may be a psuedo-nested class
		}
		generateNestedPackages(cgPackage, asPackage);
		return cgPackage;
	}

	/**
	 * Generate / share the CG declaration for asPackage.
	 */
	public @NonNull CGPackage generatePackageDeclaration(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CGPackage cgPackage = (CGPackage)asElement2cgElement.get(asPackage);
		if (cgPackage == null) {
			CompletePackage completePackage = completeModel.getCompletePackage(asPackage);
			org.eclipse.ocl.pivot.Package asPrimaryPackage = asPackage; // ClassUtil.nonNullState(completePackage.getPrimaryPackage());
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
		ExecutableNameManager propertyNameManager = getPropertyNameManager(cgProperty, asProperty);
		propertyNameManager.createCGParameters(AbstractOperationCallingConvention.CG_PARAMETER_STYLES_SELF, null);
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
		if ("reportingChain".equals(asProperty.getName())) {
			getClass();		// XXX
		}
		CGProperty cgProperty = basicGetCGProperty(asProperty);
		if (cgProperty == null) {
			ExpressionInOCL asExpressionInOCL = null;
			LanguageExpression asSpecification = asProperty.getOwnedExpression();
			if (asSpecification != null) {
				try {
					asExpressionInOCL = environmentFactory.parseSpecification(asSpecification);		// Redundant already parsed
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (callingConvention == null) {
				callingConvention = codeGenerator.getCallingConvention(asProperty);
			}
			cgProperty = callingConvention.createProperty(this, asProperty, asExpressionInOCL);
			if (asSpecification != null) {
				scanBody(asSpecification);
			}
		}
		return cgProperty;
	}

	public void generateQueuedClassesContents() {
		while (cgClassesQueueHead < cgClassesQueue.size()) {
			@NonNull CGClass cgClass = cgClassesQueue.get(cgClassesQueueHead++);
			generateClassContents(cgClass);
		}
	}

	protected @NonNull CGValuedElement generateSafeExclusion(@NonNull CallExp callExp, @NonNull CGValuedElement cgSource) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(CollectionExcludingOperation.INSTANCE);
		Operation asExcludingOperation = standardLibrary.getCollectionExcludingOperation();
		OCLExpression asSource = callExp.getOwnedSource();
		assert asSource != null;
		CGOperation cgOperation = generateMaybeVirtualOperationDeclaration(asExcludingOperation);
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

	public @NonNull PivotHelper getASHelper() {
		return codeGenerator.getASHelper();
	}

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
			cgInteger.setRequired(true);
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
			cgReal.setRequired(true);
			globalNameManager.getNameResolution(cgReal);
			cgReals.put(aNumber, cgReal);
		}
		return cgReal;
	}

/*	public @NonNull CGClass getCGRootClass(@NonNull CGNamedElement cgElement) {
		return ClassUtil.nonNullState(basicGetCGRootClass(cgElement));
	} */

	protected org.eclipse.ocl.pivot.@NonNull Class getASRootClass() {
		assert asCurrentRootClass != null;
		return asCurrentRootClass;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getASRootClass(@NonNull Element asElement) {
		org.eclipse.ocl.pivot.Class asRootClass = basicGetRootClass(asElement);
		if (asRootClass != null) {
			return asRootClass;
		}
		assert asCurrentRootClass != null;
		return asCurrentRootClass;
	}

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
			cgString.setRequired(true);
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

	public org.eclipse.ocl.pivot.@NonNull Class getCacheClass(@NonNull Operation asOperation) {
		OperationCache operationCache = asOperation2operationCache.get(asOperation);
		assert operationCache != null;
		return operationCache.getASCacheClass();
	}

	public @NonNull Property getCacheInstance(org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
		AbstractCache cacheClassData = asCacheClass2abstractCache.get(asCacheClass);
		assert cacheClassData != null;
		return cacheClassData.getASCacheInstance();
	}

	public @NonNull Property getCacheInstance(@NonNull Operation asOperation) {
		OperationCache operationCache = asOperation2operationCache.get(asOperation);
		assert operationCache != null;
		return operationCache.getASCacheInstance();
	}

	public @NonNull Operation getCachedOperation(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nonNullState(asCacheClass2asOperation.get(asClass));
	}

//	public @NonNull Operation getCachedOperation(@NonNull Operation asOperation) {
//		return ClassUtil.nonNullState(asOperation2operationCache.get(asOperation));
//	}

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
	//	assert completeModel.getCompleteClass(asClass).getPrimaryClass() == cgClass.getAst();
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

	public org.eclipse.ocl.pivot.@NonNull Class getEntryClass(org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
		AbstractCache cacheClassData = asCacheClass2abstractCache.get(asCacheClass);
		assert cacheClassData != null;
		return cacheClassData.getASEntryClass();
	}

//	public @NonNull CGVariable getExecutorVariable(@NonNull ExecutableNameManager executableNameManager) {		// Overridden for JUnit support
//		return executableNameManager.getExecutorVariableInternal();
//	}

	public @Nullable Iterable<@NonNull CGValuedElement> getExtraChildElements(@NonNull CGValuedElement cgElement) {
		List<@NonNull CGValuedElement> unreferencedExtraChildren = parent2unreferencedExtraChildren.get(cgElement);
		CGValuedElement referencedExtraChild = cgElement.getReferencedExtraChild(this);
		if (referencedExtraChild != null) {
			if (unreferencedExtraChildren != null) {
				List<@NonNull CGValuedElement> extraChildren = Lists.newArrayList(unreferencedExtraChildren);
				extraChildren.add(referencedExtraChild);
				return extraChildren ;
			}
			else {
				return Collections.singletonList(referencedExtraChild);
			}
		}
		else {
			return unreferencedExtraChildren;
		}
	}

	public @Nullable Iterable<@NonNull CGValuedElement> getExtraChildParents(@NonNull CGValuedElement extraChild) {
		return extraChild2parents.get(extraChild);
	}

	public @NonNull CGClass getForeignCGClass(org.eclipse.ocl.pivot.@NonNull Package asParentPackage) {
		CGClass cgForeignClass2 = cgForeignClass;
		if (cgForeignClass2 == null) {
			ForeignClassCallingConvention callingConvention = ForeignClassCallingConvention.getInstance();
			cgForeignClass2 = cgForeignClass = callingConvention.createForeignClass(this, asParentPackage);
		}
		return cgForeignClass2;
	}

	public @NonNull CGOperation getForeignCGOperation(@NonNull Property asProperty) {
		assert asProperty2foreignCGOperation != null;
		return ClassUtil.nonNullState(asProperty2foreignCGOperation.get(asProperty));
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
				cgOperation = generateMaybeVirtualOperationDeclaration(asOperation);
			}
		}
		assert (cgOperation.getAst() == asOperation) || (cgOperation.getCallingConvention() instanceof VirtualOperationCallingConvention);
		ExecutableNameManager operationNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgOperation);
		if (operationNameManager == null) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
			ClassNameManager classNameManager = getClassNameManager(null, asClass);
			operationNameManager = globalNameManager.createOperationNameManager(classNameManager, cgOperation);
		}
		return operationNameManager;
	}

	public @NonNull Operation getOperation(org.eclipse.ocl.pivot.Class asCacheClass) {
		OperationCache operationCache = (OperationCache)asCacheClass2abstractCache.get(asCacheClass);		// XXX basicGet ??
		assert operationCache != null;
		return operationCache.getOperation();
	}

	public @NonNull Operation getOriginalOperation(@NonNull CGOperation cgOperation) {
		return ClassUtil.nonNullState(cgOperation2asOriginalOperation.get(cgOperation));
	}

	public @NonNull Property getOriginalProperty(@NonNull CGOperation cgOperation) {
		return ClassUtil.nonNullState(cgOperation2asOriginalProperty.get(cgOperation));
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
	//	assert cgPackage.getAst() == completeModel.getCompletePackage(asPackage).getPrimaryPackage();
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

	public org.eclipse.ocl.pivot.@NonNull Package getRootClassParentPackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		org.eclipse.ocl.pivot.Class asRootClass = getASRootClass(asClass);
		return AbstractLanguageSupport.getCachePackage(asRootClass);
	}

	public org.eclipse.ocl.pivot.@NonNull Package getRootClassParentPackage(@NonNull Feature asFeature) {
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asFeature);
		return getRootClassParentPackage(asClass);
	}

	public @NonNull CGParameter getSelfParameter(@NonNull ExecutableNameManager executableNameManager, @NonNull VariableDeclaration asParameter) {		// Overridden for OCLinEcore support
		return executableNameManager.getSelfParameter2(asParameter);
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
		TypeId typeId = asTypedElement.getTypeId();
		boolean isRequired = asTypedElement.isIsRequired();
		initTypeId(cgElement, typeId, isRequired);
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
	public void initAst(@NonNull CGValuedElement cgElement, @NonNull Element asElement, @NonNull TypeId asTypeId, boolean isSymmetric) {		// FIXME Only used for non-TypedElement NewStatementPart
		CGTypeId cgTypeId = getCGTypeId(asTypeId);
		cgElement.setTypeId(cgTypeId);
		cgElement.setRequired(true);
		initCG2AS(cgElement, asElement, isSymmetric);
	}
	// Variant for AS reference to AS
	public void initAst(@NonNull CGTypedElement cgElement, @NonNull Element asElement, @NonNull TypedElement asTypedElement, boolean isSymmetric) {
		initTypeId(cgElement, asTypedElement.getTypeId(), asTypedElement.isIsRequired());
		initCG2AS(cgElement, asElement, isSymmetric);
	}
	public void initAst(@NonNull CGTypedElement cgElement, @NonNull Type asType) {
		initTypeId(cgElement, asType.getTypeId(), true);
		initCG2AS(cgElement, asType, false);
	}

	private void initCG2AS(@NonNull CGTypedElement cgElement, @NonNull Element asElement, boolean isSymmetric) {
		if ((asElement instanceof Operation) && asElement.toString().contains("::_unqualified_env_Package(")) {
			getClass();		// XXX
		}
		cgElement.setAst(asElement);
		if (isSymmetric) {
	//		if (asElement instanceof Operation) {
	//			System.out.println("initCG2AS " + NameUtil.debugSimpleName(cgElement) + " => " +  NameUtil.debugSimpleName(asElement) + " : " + asElement);	// XXX debugging
	//		}
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

//	public boolean isExternal(@NonNull Feature asFeature) {
//		return (externalFeatures != null) && externalFeatures.contains(asFeature);
//	}

	public void queueCGClassDeclaration(@NonNull CGClass cgClass) {
		assert !cgClassesQueue.contains(cgClass);
		cgClassesQueue.add(cgClass);
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

	public void scanBody(@NonNull Element specification) {
		for (@NonNull EObject eObject : new TreeIterable(specification, false)) {
			if (eObject instanceof OperationCallExp) {
				generateMaybeVirtualOperationDeclaration(PivotUtil.getReferredOperation((OperationCallExp)eObject));
			}
			else if (eObject instanceof NavigationCallExp) {
				generatePropertyDeclaration(PivotUtil.getReferredProperty((NavigationCallExp)eObject), null);
			}
		}
	}

	public void setCGConstant(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement aConstant) {
		CGConstantExp newElement = CGModelFactory.eINSTANCE.createCGConstantExp();		// FIXME wrapper not needed
		Element asConstant = oldElement.getAst();
		if (asConstant instanceof TypedElement) {
			initAst(newElement, (TypedElement)asConstant, false);
		}
		else {
			assert asConstant == null;
			newElement.setTypeId(oldElement.getTypeId());
			newElement.setAst(null);
			newElement.setRequired(false);
		}
		newElement.setReferredConstant(aConstant);
	//	newElement.setTypeId(oldElement.getTypeId());
	//	newElement.setAst(oldElement.getAst());
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
