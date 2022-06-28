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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A NestedNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public class NestedNameManager extends NameManager
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull NameManager parent;
	protected final @NonNull CGNamedElement cgScope;
	protected final @NonNull NamedElement asScope;
	protected final @NonNull Type asType;
	protected final boolean isStatic;

	/**
	 * Names that must be used within a nested namespace. Typically these are Ecore assigned property/operation/parameter
	 * names whose spelling is not adjustable.
	 */
	private @Nullable List<@NonNull NameResolution> reservedNameResolutions = null;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * Additional variants of an element's resolvedName for which further unique names are required.
	 */
	private @NonNull Map<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> element2nameVariant2name = new HashMap<>();

	/**
	 * Mapping from an AS Variable to the CG Variable defined in this cgScope.
	 */
	private @NonNull Map<@NonNull VariableDeclaration, @NonNull CGVariable> cgVariables = new HashMap<>();

	private /*@LazyNonNull*/ CGParameter anyParameter = null;				// A local parameter spelled "any" to be added to the static signature
	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor parameter / caached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGVariable qualifiedThisVariable = null;		// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of execitpr.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter selfParameter = null;				// A local parameter spelled "self" to be added to the signature
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// A local orphan parameter spelled "this"
	private /*@LazyNonNull*/ CGParameter typeIdParameter = null;			// A local orphan parameter spelled "typeId"

	public NestedNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(parent, parent.helper);
		this.codeGenerator = codeGenerator;
		this.analyzer = codeGenerator.getAnalyzer();
		this.parent = parent;
		this.cgScope = cgScope;
		this.asScope = CGUtil.getAST(cgScope);
		if (parent instanceof GlobalNameManager) {
			this.asType = ClassUtil.nonNullState(PivotUtil.getContainingType(asScope));
		}
		else {
			this.asType = ((NestedNameManager)parent).asType;
		}
		boolean staticFeature = (asScope instanceof Feature) && ((Feature)asScope).isIsStatic();
		this.isStatic = /*(asScope == null) ||*/ staticFeature;
		assert !(parent instanceof NestedNameManager) || (((NestedNameManager)parent).cgScope != cgScope);		// XXX
	// XXX	assert at most one ancestral class
		parent.addChild(this);
		if ((cgScope instanceof CGClass) || (cgScope instanceof CGPackage)) {
			assert (parent instanceof GlobalNameManager) || (((NestedNameManager)parent).cgScope instanceof CGPackage);
		}
	}

	public void addNameVariant(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
	//	String resolvedName = getNameResolution().getResolvedName();
	//	assert resolvedName == null;
	//	assert (resolvedName == null) || ((NestedNameManager)getNameManager()).isReserved(this) : "Cannot addNameVariant after name is resolved";
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		if (nameVariant2name == null) {
			nameVariant2name = new HashMap<>();
			element2nameVariant2name.put(cgElement, nameVariant2name);
		}
		String old = nameVariant2name.put(nameVariant, null);
		assert old == null;
	}

	public void addVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
		// XXX verify scope / class consistency
		CGVariable old = cgVariables.put(asVariable, cgVariable);
		assert old == null;
	}

	/**
	 * Assign all the secondary names that prefix a primary name.
	 */
	protected void assignExtraNames(@NonNull Context context) {
		for (Entry<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> entry1 : element2nameVariant2name.entrySet()) {
			Map<@NonNull NameVariant, @Nullable String> nameVariant2name = entry1.getValue();
			if (nameVariant2name != null) {
				CGNamedElement cgElement = entry1.getKey();
				assert cgElement.eContainer() != null;		// Not eliminated by CSE		-- ?? obsolete
				String resolvedName;
				if (cgElement instanceof CGValuedElement) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					NameResolution nameResolution = getNameResolution(cgValuedElement);
					nameResolution.resolveNameHint();;
					nameResolution.resolveIn(context, cgValuedElement);
					resolvedName = nameResolution.getResolvedName();
				}
				else {
					resolvedName = CGUtil.getName(cgElement);
				}
				for (Entry<@NonNull NameVariant, @Nullable String> entry2 : nameVariant2name.entrySet()) {
					NameVariant nameVariant = entry2.getKey();
					String name = entry2.getValue();
					assert name == null;
					String variantNameHint = nameVariant.getName(resolvedName);
					String variantName = context.allocateUniqueName(variantNameHint, cgElement);
					nameVariant2name.put(nameVariant, variantName);
				}
			}
		}
	}

	public void assignNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		Context context2 = context;
		assert context2 == null;
		this.context = context2 = new Context(this);
		assignReservedNames(context2);
		assignLocalNames(context2, nameManager2namedElements);
		assignExtraNames(context2);
		assignNestedNames(nameManager2namedElements);
	}

	/**
	 * Assign all the reserved names (first).
	 */
	protected void assignReservedNames(@NonNull Context context) {
		if (reservedNameResolutions != null) {
			for (@NonNull NameResolution nameResolution : reservedNameResolutions) {
				String resolvedName = nameResolution.getResolvedName();
				CGNamedElement primaryElement = nameResolution.getPrimaryElement();
				context.reserveName(resolvedName, primaryElement);
			}
		}
	}

	public @Nullable CGParameter basicGetAnyParameter() {
		return anyParameter;
	}

	public @Nullable CGVariable basicGetExecutorVariable() {
		return (executorVariable != null) && !(executorVariable instanceof CGParameter) ? executorVariable : null;
	}

	public @Nullable CGVariable basicGetIdResolverVariable() {
		return idResolverVariable;
	}

	public @Nullable CGVariable basicGetLocalVariable(@NonNull VariableDeclaration asVariable) {
		return cgVariables.get(asVariable);
	}

	public @Nullable CGVariable basicGetModelManagerVariable() {
		return modelManagerVariable;
	}

	public @Nullable CGParameter basicGetParameter(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = cgVariables.get(asVariable);
		if (cgVariable instanceof CGParameter) {
			return (CGParameter)cgVariable;
		}
		else if (cgVariable != null) {
			throw new IllegalStateException(cgVariable + " is not  a CGParameter");
		}
		else if (parent instanceof NestedNameManager) {				// XXX polymorphize
			return ((NestedNameManager)parent).basicGetParameter(asVariable);
		}
		else {
			return null;
		}
	}

	public @Nullable CGVariable basicGetQualifiedThisVariable() {
		return qualifiedThisVariable;
	}

	public @Nullable CGParameter basicGetSelfParameter() {
		return selfParameter;
	}

	public @Nullable CGVariable basicGetStandardLibraryVariable() {
		return standardLibraryVariable;
	}

	public @Nullable CGVariable basicGetVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = cgVariables.get(asVariable);
		if (cgVariable != null) {
			return cgVariable;
		}
		else if (parent instanceof NestedNameManager) {				// XXX polymorphize
			return ((NestedNameManager)parent).basicGetVariable(asVariable);
		}
		else {
			return null;
		}
	}

	public @Nullable String basicGetVariantResolvedName(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		return nameVariant2name != null ? nameVariant2name.get(nameVariant) : null;
	}

	protected @NonNull CGParameter createAnyParameter() {
		assert isStatic;
		NameResolution anyName = globalNameManager.getAnyNameResolution();
		CGParameter anyParameter = analyzer.createCGParameter(anyName, analyzer.getCGTypeId(TypeId.OCL_ANY), false);
		anyParameter.setNonInvalid();
		return anyParameter;
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull VariableDeclaration asVariable) {
		assert basicGetVariable(asVariable) == null;
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgVariable.setAst(asVariable);
		cgVariable.setTypeId(analyzer.getCGTypeId(asVariable.getTypeId()));
//		declarePreferredName(cgVariable);
		addVariable(asVariable, cgVariable);
		return cgVariable;
	}

	public @NonNull CGFinalVariable createCGVariable(@NonNull CGValuedElement cgInit) {
//		NameResolution nameResolution = getNameResolution(cgInit);
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgVariable.setAst(cgInit.getAst());
		cgVariable.setTypeId(cgInit.getTypeId());
		cgVariable.setInit(cgInit);
//		nameResolution.addCGElement(cgVariable);
		return cgVariable;
	}

	protected @NonNull CGVariable createExecutorVariable() {
		CGNativeOperationCallExp executorInit = analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution executorNameResolution = globalNameManager.getExecutorNameResolution();
	//	executorNameResolution.addCGElement(executorInit);
		executorInit.setTypeId(analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
		CGValuedElement contextParameter;
		if (!isStatic) {
			contextParameter = analyzer.createCGVariableExp(getThisParameter());
		}
		else {
			CGParameter selfParameter = basicGetSelfParameter();
			if (selfParameter != null) {
				contextParameter = analyzer.createCGVariableExp(selfParameter);
			}
			else {
				CGParameter anyParameter = basicGetAnyParameter();
				if (anyParameter != null) {
					contextParameter = analyzer.createCGVariableExp(anyParameter);
				}
				else {
					contextParameter = analyzer.createCGNull();
				}
			}
		}
		executorInit.getArguments().add(contextParameter);
		executorInit.setRequired(true);
		executorInit.setInvalidating(false);
		CGVariable executorVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		executorVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
		executorVariable.setInit(executorInit);
		executorVariable.setNonInvalid();
		executorVariable.setNonNull();
		executorNameResolution.addCGElement(executorVariable);
		return executorVariable;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
		idResolverNameResolution.addCGElement(idResolverInit);
		idResolverInit.setTypeId(analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID));
		idResolverInit.setCgThis(analyzer.createCGVariableExp(getExecutorVariable()));
		idResolverInit.setRequired(true);
		idResolverInit.setInvalidating(false);
		CGVariable idResolverVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		idResolverVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID));
		idResolverVariable.setInit(idResolverInit);
		idResolverVariable.setNonInvalid();
		idResolverVariable.setNonNull();
		idResolverNameResolution.addCGElement(idResolverVariable);
		return idResolverVariable;
	}

	public @NonNull CGVariable createModelManagerVariable() {
		CGNativeOperationCallExp modelManagerInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_MODEL_MANAGER_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution modelManagerNameResolution = globalNameManager.getModelManagerNameResolution();
		modelManagerNameResolution.addCGElement(modelManagerInit);
		modelManagerInit.setTypeId(analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID));
		modelManagerInit.setCgThis(analyzer.createCGVariableExp(getExecutorVariable()));
		modelManagerInit.setRequired(true);
		modelManagerInit.setInvalidating(false);
		CGVariable modelManagerVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		modelManagerVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.MODEL_MANAGER_TYPE_ID));
		modelManagerVariable.setInit(modelManagerInit);
		modelManagerVariable.setNonInvalid();
		modelManagerVariable.setNonNull();
		modelManagerNameResolution.addCGElement(modelManagerVariable);
		return modelManagerVariable;
	}

	public @NonNull CGVariable createQualifiedThisVariable() {
		NameResolution qualifiedThisNameResolution = globalNameManager.declareGlobalName(null, asType.getName() + "_" + JavaConstants.THIS_NAME);
		CGVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		qualifiedThisVariable.setTypeId(analyzer.getCGTypeId(asType.getTypeId()));
		qualifiedThisVariable.setInit(getThisParameter());
		qualifiedThisVariable.setNonInvalid();
		qualifiedThisVariable.setNonNull();
		qualifiedThisNameResolution.addCGElement(qualifiedThisVariable);
		return qualifiedThisVariable;
	}

	protected @NonNull CGParameter createSelfParameter() {
	//	assert !isStatic;
		if (cgScope instanceof CGForeignProperty) {
		//	Property referredProperty = CGUtil.getAST(((CGForeignProperty)scope));
		//	OperationId operationId = referredOperation.getOperationId();
			boolean sourceMayBeNull = false; //analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			NameResolution selfName = globalNameManager.getSelfNameResolution();
			CGParameter selfParameter = analyzer.createCGParameter(selfName, analyzer.getCGTypeId(asType.getTypeId()), !sourceMayBeNull);
			selfParameter.setIsSelf(true);
			selfParameter.setNonInvalid();
			selfParameter.setNonNull();
			return selfParameter;
		}
		else if (cgScope instanceof CGOperation) {
			Operation referredOperation = CGUtil.getAST(((CGOperation)cgScope));
			OperationId operationId = referredOperation.getOperationId();
			boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			NameResolution selfName = globalNameManager.getSelfNameResolution();
			CGParameter selfParameter = analyzer.createCGParameter(selfName, analyzer.getCGTypeId(asType.getTypeId()), !sourceMayBeNull);
			selfParameter.setIsSelf(true);
			selfParameter.setNonInvalid();
			selfParameter.setNonNull();
			return selfParameter;
		}
		else {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ".createSelfParameter for " + cgScope.eClass().getName());
		}
	}

	public @NonNull CGVariable createStandardLibraryVariable() {
		CGNativeOperationCallExp standardLibraryInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution standardLibraryNameResolution = globalNameManager.getStandardLibraryVariableNameResolution();
		standardLibraryNameResolution.addCGElement(standardLibraryInit);
		standardLibraryInit.setTypeId(analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
		standardLibraryInit.setCgThis(analyzer.createCGVariableExp(getExecutorVariable()));
		standardLibraryInit.setRequired(true);
		standardLibraryInit.setInvalidating(false);
		CGVariable standardLibraryVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		standardLibraryVariable.setTypeId(analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
		standardLibraryVariable.setInit(standardLibraryInit);
		standardLibraryVariable.setNonInvalid();
		standardLibraryVariable.setNonNull();
		standardLibraryNameResolution.addCGElement(standardLibraryVariable);
		return standardLibraryVariable;
	}

	protected @NonNull CGParameter createThisParameter() {
		assert !isStatic;
		NameResolution thisName = globalNameManager.getThisNameResolution();
		CGParameter thisParameter = analyzer.createCGParameter(thisName, analyzer.getCGTypeId(asType.getTypeId()), true);
		thisParameter.setIsThis(true);
		thisParameter.setNonInvalid();
		thisParameter.setNonNull();
		return thisParameter;
	}

	public @Nullable CGClass findCGScope() { // XXX ??? use GlobalNameManager.findNameManager
		for (NestedNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof NestedNameManager ? (NestedNameManager)nameManager.parent : null) {
			CGNamedElement cgScope = nameManager.cgScope;
			if (cgScope instanceof CGClass) {
				return (CGClass) cgScope;
			}
		}
		return null;
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return codeGenerator.getAnalyzer();
	}

	public @NonNull CGParameter getAnyParameter() {
	//	assert !isStatic;
		CGParameter anyParameter2 = anyParameter;
		if (anyParameter2 == null) {
			anyParameter = anyParameter2 = createAnyParameter();
		}
		return anyParameter2;
	}

	public @Nullable CGValuedElement getBody() {
		if (cgScope instanceof CGConstraint) {
			return ((CGConstraint)cgScope).getBody();
		}
		else if (cgScope instanceof CGOperation) {
			return ((CGOperation)cgScope).getBody();
		}
		else if (cgScope instanceof CGBodiedProperty) {
			return ((CGBodiedProperty)cgScope).getBody();
		}
		assert false;;
		return null;
	}

//	public @NonNull CGNamedElement getCGScope() {
//		return cgScope;
//	}

	public @NonNull CGVariable getCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = basicGetVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = createCGVariable(asVariable);
			if (asVariable.isIsRequired()) {
				cgVariable.setNonInvalid();
				cgVariable.setNonNull();
			}
		}
		return cgVariable;
	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	public @Nullable NestedNameManager getClassParentNameManager() {
		for (NestedNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof NestedNameManager ? (NestedNameManager)nameManager.parent : null) {
			CGNamedElement cgScope = nameManager.cgScope;
			if (cgScope instanceof CGClass) {
				return nameManager.parent instanceof NestedNameManager ? (NestedNameManager)nameManager.parent : null;
			}
		}
		return null;
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	public @NonNull CGParameter getExecutorParameter() {
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = codeGenerator.createExecutorParameter();
		}
		return (CGParameter)executorVariable2;
	}

	public @NonNull CGVariable getExecutorVariable() {
		if (asScope instanceof CallExp) {
			return ((NestedNameManager)parent).getExecutorVariable();
		}
		CGVariable executorVariable2 = executorVariable;
		if (executorVariable2 == null) {
			executorVariable = executorVariable2 = createExecutorVariable();
		}
		return executorVariable2;
	}

	public @NonNull CGVariable getIdResolverVariable() {
		if (asScope instanceof CallExp) {
			return ((NestedNameManager)parent).getIdResolverVariable();
		}
		CGVariable idResolverVariable2 = idResolverVariable;
		if (idResolverVariable2 == null) {
			idResolverVariable = idResolverVariable2 = createIdResolverVariable();
		}
		return idResolverVariable2;
	}

	public @NonNull CGIterator getIterator(@NonNull VariableDeclaration asVariable) {
		CGIterator cgIterator = (CGIterator)basicGetVariable(asVariable);
		if (cgIterator == null) {
			cgIterator = CGModelFactory.eINSTANCE.createCGIterator();
			cgIterator.setAst(asVariable);
			cgIterator.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// FIXME Java-specific type of polymorphic operation parameter
//			declarePreferredName(cgIterator);
			addVariable(asVariable, cgIterator);
		}
		return cgIterator;
	}

	public @NonNull CGVariable getModelManagerVariable() {
		if (asScope instanceof CallExp) {
			return ((NestedNameManager)parent).getModelManagerVariable();
		}
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 == null) {
			modelManagerVariable = modelManagerVariable2 = createModelManagerVariable();
		}
		return modelManagerVariable2;
	}

	@Override
	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution == null) {
			CGValuedElement cgNamedValue = cgElement.getNamedValue();
			nameResolution = cgNamedValue.basicGetNameResolution();
			if (nameResolution == null) {
				nameResolution = new NameResolution(this, cgNamedValue, null);
			}
			if (cgElement != cgNamedValue) {
				nameResolution.addCGElement(cgElement);
			}
		}
		return nameResolution;
	}

	public @NonNull CGParameter getParameter(@NonNull VariableDeclaration asParameter, @Nullable String explicitName) {
		CGParameter cgParameter = basicGetParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			if (explicitName == null) {
			//	analyzer.setNames(cgParameter, aParameter);

			//	String name = analyzer.getGlobalNameManager().getNameHint(aParameter);
				//	String name = globalNameManager.helper.getNameHint(anObject);
				//	cgValue.setName(name);
				//	cgValue.setValueName(name);
//				declarePreferredName(cgParameter);


			//	NameResolution nameResolution = cgParameter.getNameResolution();
			//	nameResolution.setResolvedName(parameterVariable.getName());
			//	getNameManager().addNameResolution(nameResolution);
			}
			else {
				assert explicitName.equals(asParameter.getName());
				Operation asOperation = PivotUtil.getContainingOperation(asParameter);
				Constraint asConstraint = PivotUtil.getContainingConstraint(asParameter);
				assert ((asOperation != null) && (asOperation.getESObject() instanceof EOperation)) || ((asConstraint != null) && (asConstraint.getESObject() instanceof EOperation));
			//	assert is-ecore-parameter
			//	cgParameter.setName(explicitName);
			//	cgParameter.setValueName(explicitName);
//				/*NameResolution nameResolution =*/ declareReservedName(cgParameter, explicitName);
			//	nameResolution.setResolvedName(explicitName);
			}
			//			cgParameter.setTypeId(analyzer.getTypeId(aParameter.getTypeId()));
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

/*	public @NonNull CGParameter getParameter(@NonNull Variable asParameter, @NonNull NameResolution nameResolution) {
		CGParameter cgParameter = basicGetParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setName(asParameter.getName());
			nameResolution.addCGElement(cgParameter);
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			declareLazyName(cgParameter);
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	} */

	public @NonNull NameManager getParent() {
		return parent;
	}

	public @NonNull CGVariable getQualifiedThisVariable() {
		if (asScope != asType) {
			return ((NestedNameManager)parent).getQualifiedThisVariable();
		}
		CGVariable qualifiedThisVariable2 = qualifiedThisVariable;
		if (qualifiedThisVariable2 == null) {
			qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
		}
		return qualifiedThisVariable2;
	}

	public @NonNull CGParameter getSelfParameter() {
	//	assert !isStatic;
		CGParameter selfParameter2 = selfParameter;
		if (selfParameter2 == null) {
			selfParameter = selfParameter2 = createSelfParameter();
		}
		return selfParameter2;
	}

	public @NonNull CGParameter getSelfParameter(@NonNull VariableDeclaration asParameter) {		// XXX Why with/without arg variants
		CGParameter cgParameter = basicGetParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			globalNameManager.getSelfNameResolution().addCGElement(cgParameter);
			addVariable(asParameter, cgParameter);
			boolean isRequired = asParameter.isIsRequired();
			cgParameter.setRequired(isRequired);
			if (isRequired) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	public @NonNull CGVariable getStandardLibraryVariable() {
		if (asScope instanceof CallExp) {
			return ((NestedNameManager)parent).getStandardLibraryVariable();
		}
		CGVariable standardLibraryVariable2 = standardLibraryVariable;
		if (standardLibraryVariable2 == null) {
			standardLibraryVariable = standardLibraryVariable2 = createStandardLibraryVariable();
		}
		return standardLibraryVariable2;
	}

	public @NonNull CGParameter getThisParameter() {
		assert !isStatic;
		CGParameter thisParameter2 = thisParameter;
		if (thisParameter2 == null) {
			thisParameter = thisParameter2 = createThisParameter();
		}
		return thisParameter2;
	}

	public @NonNull CGParameter getThisParameter(@NonNull VariableDeclaration asParameter) {		// XXX Why with/without arg variants
		CGParameter cgParameter = basicGetParameter(asParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setAst(asParameter);
			cgParameter.setTypeId(analyzer.getCGTypeId(asParameter.getTypeId()));
			globalNameManager.getThisNameResolution().addCGElement(cgParameter);
			addVariable(asParameter, cgParameter);
			cgParameter.setRequired(asParameter.isIsRequired());
			if (asParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
			cgParameter.setIsSelf(true);
		}
		return cgParameter;
	}

	public @NonNull CGParameter getTypeIdParameter() {
	//	assert !isStatic;
		CGParameter typeIdParameter2 = typeIdParameter;
		if (typeIdParameter2 == null) {
			typeIdParameter = typeIdParameter2 = codeGenerator.createTypeIdParameter();
		}
		return typeIdParameter2;
	}

/*	public @NonNull CGParameter getTypeIdParameter() {		-- no addVariable - no AST
		//	assert !isStatic;
		CGParameter typeIdParameter2 = typeIdParameter;
		if (typeIdParameter2 == null) {
			typeIdParameter = typeIdParameter2 = codeGenerator.createTypeIdParameter();
		}
		assert typeIdParameter2.eContainer() == null;
		addVariable(CGUtil.getAST(typeIdParameter2), typeIdParameter2);
		return typeIdParameter2;
	} */

/*	public @NonNull String getVariantResolvedName(@NonNull CGValuedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		assert nameVariant2name != null;
		String name = nameVariant2name.get(nameVariant);
		assert name != null;
		return name;
	} */

	@Override
	public boolean isGlobal() {
		return false;
	}

	public boolean isReserved(@NonNull NameResolution nameResolution) {
		return (reservedNameResolutions != null) && reservedNameResolutions.contains(nameResolution);
	}

	public void setNameVariant(@NonNull CGValuedElement cgElement, @NonNull NameVariant nameVariant, @NonNull String variantName) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		assert nameVariant2name != null;
		String old = nameVariant2name.put(nameVariant, variantName);
		assert old == null;
	}

	@Override
	public @NonNull String toString() {
		return "locals-" + cgScope.eClass().getName() + "-" + CGUtil.getAST(cgScope).getName();
	}

	public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
		CGVariable qualifiedThisVariable = basicGetQualifiedThisVariable();
		if (qualifiedThisVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, qualifiedThisVariable);
		}
		CGVariable standardLibraryVariable = basicGetStandardLibraryVariable();
		if (standardLibraryVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, standardLibraryVariable);
		}
		CGVariable modelManagerVariable = basicGetModelManagerVariable();
		if (modelManagerVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, modelManagerVariable);
		}
		CGVariable idResolverVariable = basicGetIdResolverVariable();
		if (idResolverVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, idResolverVariable);
		}
		CGVariable executorVariable = basicGetExecutorVariable();
		if (executorVariable != null) {
			cgTree = CGUtil.rewriteAsLet(cgTree, executorVariable);
		}
		return cgTree;
	}
}
