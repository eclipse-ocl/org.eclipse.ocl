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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class NestedNameManager extends NameManager
{
	/**
	 * A JavaLocalContext maintains the Java-specific context for generation of coide from a CGOperation.
	 */
	public static class JavaLocalContext implements LocalContext
	{
		protected final @Nullable JavaLocalContext outerContext;
		protected final @NonNull NestedNameManager nameManager;

		public JavaLocalContext(@NonNull JavaCodeGenerator codeGenerator, @Nullable JavaLocalContext outerContext,
				@NonNull CGNamedElement cgScope, @NonNull NamedElement asScope) {
			this.outerContext = outerContext;
			boolean staticFeature = (asScope instanceof Feature) && ((Feature)asScope).isIsStatic();
			boolean isStatic = /*(asScope == null) ||*/ staticFeature;
			@NonNull Type asType;
			NameManager outerNameManager;
			if (outerContext != null) {
				asType = outerContext.nameManager.asType;
				outerNameManager = outerContext.getNameManager();
			}
			else {
				asType = ClassUtil.nonNullState(PivotUtil.getContainingType(asScope));
				outerNameManager = codeGenerator.getGlobalNameManager();
			}
			this.nameManager = codeGenerator.createNestedNameManager(outerNameManager, this, cgScope, asScope, asType, isStatic);
			NameManager parentNameManager = nameManager.parent;
			assert (parentNameManager instanceof NestedNameManager) ?  (outerContext == ((NestedNameManager)parentNameManager).localContext) : (outerContext == null);
		}

		protected @NonNull CGParameter createAnyParameter() {
			assert nameManager.isStatic;
			NameResolution anyName = nameManager.globalNameManager.getAnyNameResolution();
			CGParameter anyParameter = nameManager.analyzer.createCGParameter(anyName, nameManager.analyzer.getCGTypeId(TypeId.OCL_ANY), false);
			anyParameter.setNonInvalid();
			return anyParameter;
		}

		protected @NonNull CGVariable createExecutorVariable() {
		//	assert !executorIsParameter;
			CGNativeOperationCallExp executorInit = nameManager.analyzer.createCGNativeOperationCallExp(JavaConstants.PIVOT_UTIL_GET_EXECUTOR_GET_METHOD, SupportOperationCallingConvention.INSTANCE);
			NameResolution executorNameResolution = nameManager.globalNameManager.getExecutorNameResolution();
		//	executorNameResolution.addCGElement(executorInit);
			executorInit.setTypeId(nameManager.analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
			CGValuedElement contextParameter;
			if (!nameManager.isStatic) {
				contextParameter = nameManager.analyzer.createCGVariableExp(getThisParameter());
			}
			else {
				CGParameter selfParameter = nameManager.basicGetSelfParameter();
				if (selfParameter != null) {
					contextParameter = nameManager.analyzer.createCGVariableExp(selfParameter);
				}
				else {
					CGParameter anyParameter = nameManager.basicGetAnyParameter();
					if (anyParameter != null) {
						contextParameter = nameManager.analyzer.createCGVariableExp(anyParameter);
					}
					else {
						contextParameter = nameManager.analyzer.createCGNull();
					}
				}
			}
			executorInit.getCgArguments().add(contextParameter);
			executorInit.setRequired(true);
			executorInit.setInvalidating(false);
			CGVariable executorVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			executorVariable.setTypeId(nameManager.analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID));
			executorVariable.setInit(executorInit);
			executorVariable.setNonInvalid();
			executorVariable.setNonNull();
			executorNameResolution.addCGElement(executorVariable);
			return executorVariable;
		}

		public @NonNull CGVariable createQualifiedThisVariable() {
			NameResolution qualifiedThisNameResolution = nameManager.globalNameManager.declareGlobalName(null, nameManager.asType.getName() + "_" + JavaConstants.THIS_NAME);
			CGVariable qualifiedThisVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			qualifiedThisVariable.setTypeId(nameManager.analyzer.getCGTypeId(nameManager.asType.getTypeId()));
			qualifiedThisVariable.setInit(getThisParameter());
			qualifiedThisVariable.setNonInvalid();
			qualifiedThisVariable.setNonNull();
			qualifiedThisNameResolution.addCGElement(qualifiedThisVariable);
			return qualifiedThisVariable;
		}

		protected @NonNull CGParameter createSelfParameter() {
		//	assert !isStatic;
			CGNamedElement scope = getScope();
			if (scope instanceof CGForeignProperty) {
			//	Property referredProperty = CGUtil.getAST(((CGForeignProperty)scope));
			//	OperationId operationId = referredOperation.getOperationId();
				boolean sourceMayBeNull = false; //analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
				NameResolution selfName = nameManager.globalNameManager.getSelfNameResolution();
				CGParameter selfParameter = nameManager.analyzer.createCGParameter(selfName, nameManager.analyzer.getCGTypeId(nameManager.asType.getTypeId()), !sourceMayBeNull);
				selfParameter.setIsSelf(true);
				selfParameter.setNonInvalid();
				selfParameter.setNonNull();
				return selfParameter;
			}
			else if (scope instanceof CGOperation) {
				Operation referredOperation = CGUtil.getAST(((CGOperation)scope));
				OperationId operationId = referredOperation.getOperationId();
				boolean sourceMayBeNull = nameManager.analyzer.hasOclVoidOperation(operationId);	// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
				NameResolution selfName = nameManager.globalNameManager.getSelfNameResolution();
				CGParameter selfParameter = nameManager.analyzer.createCGParameter(selfName, nameManager.analyzer.getCGTypeId(nameManager.asType.getTypeId()), !sourceMayBeNull);
				selfParameter.setIsSelf(true);
				selfParameter.setNonInvalid();
				selfParameter.setNonNull();
				return selfParameter;
			}
			else {
				throw new UnsupportedOperationException(getClass().getSimpleName() + ".createSelfParameter for " + scope.eClass().getName());
			}
		}

		public @NonNull CGVariable createStandardLibraryVariable() {
			CGNativeOperationCallExp standardLibraryInit = nameManager.analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_STANDARD_LIBRARY_METHOD, SupportOperationCallingConvention.INSTANCE);
			NameResolution standardLibraryNameResolution = nameManager.globalNameManager.getStandardLibraryVariableNameResolution();
			standardLibraryNameResolution.addCGElement(standardLibraryInit);
			standardLibraryInit.setTypeId(nameManager.analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
			standardLibraryInit.setCgThis(nameManager.analyzer.createCGVariableExp(getExecutorVariable()));
			standardLibraryInit.setRequired(true);
			standardLibraryInit.setInvalidating(false);
			CGVariable standardLibraryVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			standardLibraryVariable.setTypeId(nameManager.analyzer.getCGTypeId(JavaConstants.STANDARD_LIBRARY_TYPE_ID));
			standardLibraryVariable.setInit(standardLibraryInit);
			standardLibraryVariable.setNonInvalid();
			standardLibraryVariable.setNonNull();
			standardLibraryNameResolution.addCGElement(standardLibraryVariable);
			return standardLibraryVariable;
		}

		protected @NonNull CGParameter createThisParameter() {
			assert !nameManager.isStatic;
			NameResolution thisName = nameManager.globalNameManager.getThisNameResolution();
			CGParameter thisParameter = nameManager.analyzer.createCGParameter(thisName, nameManager.analyzer.getCGTypeId(nameManager.asType.getTypeId()), true);
			thisParameter.setIsThis(true);
			thisParameter.setNonInvalid();
			thisParameter.setNonNull();
			return thisParameter;
		}

		public @NonNull CodeGenAnalyzer getAnalyzer() {
			return nameManager.analyzer;
		}

		public @NonNull CGParameter getAnyParameter() {
		//	assert !isStatic;
			CGParameter anyParameter2 = nameManager.anyParameter;
			if (anyParameter2 == null) {
				nameManager.anyParameter = anyParameter2 = createAnyParameter();
			}
			return anyParameter2;
		}

		public @Nullable CGValuedElement getBody() {
			if (nameManager.cgScope instanceof CGConstraint) {
				return ((CGConstraint)nameManager.cgScope).getBody();
			}
			else if (nameManager.cgScope instanceof CGOperation) {
				return ((CGOperation)nameManager.cgScope).getBody();
			}
			else if (nameManager.cgScope instanceof CGBodiedProperty) {
				return ((CGBodiedProperty)nameManager.cgScope).getBody();
			}
			assert false;;
			return null;
		}

		public @NonNull JavaCodeGenerator getCodeGenerator() {
			return nameManager.codeGenerator;
		}

		public @NonNull CGParameter getExecutorParameter() {
		//	assert executorIsParameter;
			CGVariable executorVariable2 = nameManager.executorVariable;
			if (executorVariable2 == null) {
				nameManager.executorVariable = executorVariable2 = nameManager.codeGenerator.createExecutorParameter();
			}
			return (CGParameter)executorVariable2;
		}

		public @NonNull CGVariable getExecutorVariable() {
			if (nameManager.asScope instanceof CallExp) {
				assert outerContext != null;
				return outerContext.getExecutorVariable();
			}
			CGVariable executorVariable2 = nameManager.executorVariable;
			if (executorVariable2 == null) {
				nameManager.executorVariable = executorVariable2 = createExecutorVariable();
			}
			return executorVariable2;
		}

		@Override
		public @NonNull NestedNameManager getNameManager() {
			return nameManager;
		}

		public @NonNull JavaLocalContext getOuterContext() {
			return outerContext != null ? outerContext.getOuterContext() : this;
		}

		public @NonNull CGValuedElement getOwned(@NonNull CGValuedElement cgValuedElement, @NonNull String name) {
			for (CGValuedElement cgOwned : cgValuedElement.getOwns()) {
				if (name.equals(cgOwned.getName())) {
					return cgOwned;
				}
				if (cgOwned instanceof CGVariableExp) {
					CGVariable cgVariable = ((CGVariableExp)cgOwned).getReferredVariable();
					if (cgVariable != null) {
						CGValuedElement cgInit = cgVariable.getInit();
						if (name.equals(cgInit.getName())) {
							return cgInit;
						}
					}
				}
			}
			throw new IllegalStateException("No '" + name + "' in " + cgValuedElement);
		}

		public @NonNull CGVariable getQualifiedThisVariable() {
			if (nameManager.asScope instanceof CallExp) {
				assert outerContext != null;
				return outerContext.getQualifiedThisVariable();
			}
			CGVariable qualifiedThisVariable2 = nameManager.qualifiedThisVariable;
			if (qualifiedThisVariable2 == null) {
				nameManager.qualifiedThisVariable = qualifiedThisVariable2 = createQualifiedThisVariable();
			}
			return qualifiedThisVariable2;
		}

		@Override
		public @NonNull CGNamedElement getScope() {
			return nameManager.cgScope;
		}

		public @NonNull CGParameter getSelfParameter() {
		//	assert !isStatic;
			CGParameter selfParameter2 = nameManager.selfParameter;
			if (selfParameter2 == null) {
				nameManager.selfParameter = selfParameter2 = createSelfParameter();
			}
			return selfParameter2;
		}

//		@Deprecated /* @deprecated unnecessary argument */
//		public @NonNull CGVariable getStandardLibraryVariable(@NonNull CGValuedElement cgValuedElement) {
//			return getStandardLibraryVariable();
//		}

		public @NonNull CGVariable getStandardLibraryVariable() {
			if (nameManager.asScope instanceof CallExp) {
				assert outerContext != null;
				return outerContext.getStandardLibraryVariable();
			}
			CGVariable standardLibraryVariable2 = nameManager.standardLibraryVariable;
			if (standardLibraryVariable2 == null) {
				nameManager.standardLibraryVariable = standardLibraryVariable2 = createStandardLibraryVariable();
			}
			return standardLibraryVariable2;
		}

		public @NonNull CGParameter getThisParameter() {
			assert !nameManager.isStatic;
			CGParameter thisParameter2 = nameManager.thisParameter;
			if (thisParameter2 == null) {
				nameManager.thisParameter = thisParameter2 = createThisParameter();
			}
			return thisParameter2;
		}

		public @NonNull CGParameter getTypeIdParameter() {
		//	assert !isStatic;
			CGParameter typeIdParameter2 = nameManager.typeIdParameter;
			if (typeIdParameter2 == null) {
				nameManager.typeIdParameter = typeIdParameter2 = nameManager.codeGenerator.createTypeIdParameter();
			}
			return typeIdParameter2;
		}

		@Override
		public @NonNull String toString() {
			return nameManager.toString();
		}

		public @NonNull CGValuedElement wrapLetVariables(@NonNull CGValuedElement cgTree) {
			CGVariable qualifiedThisVariable = nameManager.basicGetQualifiedThisVariable();
			if (qualifiedThisVariable != null) {
				cgTree = CGUtil.rewriteAsLet(cgTree, qualifiedThisVariable);
			}
			CGVariable standardLibraryVariable = nameManager.basicGetStandardLibraryVariable();
			if (standardLibraryVariable != null) {
				cgTree = CGUtil.rewriteAsLet(cgTree, standardLibraryVariable);
			}
			CGVariable modelManagerVariable = nameManager.basicGetModelManagerVariable();
			if (modelManagerVariable != null) {
				cgTree = CGUtil.rewriteAsLet(cgTree, modelManagerVariable);
			}
			CGVariable idResolverVariable = nameManager.basicGetIdResolverVariable();
			if (idResolverVariable != null) {
				cgTree = CGUtil.rewriteAsLet(cgTree, idResolverVariable);
			}
			CGVariable executorVariable = nameManager.basicGetExecutorVariable();
			if (executorVariable != null) {
				cgTree = CGUtil.rewriteAsLet(cgTree, executorVariable);
			}
			return cgTree;
		}
	}

	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull JavaLocalContext localContext;
	protected final @NonNull NameManager parent;
	protected final @NonNull CGNamedElement cgScope;
	protected final @NonNull NamedElement asScope;
	protected final @NonNull Type asType;
	protected final boolean isStatic;

	private @Nullable List<@NonNull NameResolution> reservedNameResolutions = null;

	/**
	 * The value name assignments.
	 */
	private @Nullable Context context = null;		// Non-null once value name allocation is permitted.

	/**
	 * Additional variants of resolvedName for which further unique names are required.
	 */
	private @NonNull Map<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> element2nameVariant2name = new HashMap<>();

	private /*@LazyNonNull*/ CGVariable executorVariable = null;			// Passed executor paramter / caached local thread lookup
	private /*@LazyNonNull*/ CGVariable idResolverVariable = null;			// A convenience cache of execitpr.getIdResolver()
	private /*@LazyNonNull*/ CGVariable modelManagerVariable = null;		// A convenience cache of execitpr.getModelManager()
	private /*@LazyNonNull*/ CGVariable qualifiedThisVariable = null;		// An unambiguous spelling of this for external access.
	private /*@LazyNonNull*/ CGVariable standardLibraryVariable = null;		// A convenience cache of execitpr.getStandardVariable()
	private /*@LazyNonNull*/ CGParameter selfParameter = null;				// A local parameter spelled "self" to be added to the signature
	private /*@LazyNonNull*/ CGParameter thisParameter = null;				// A local orphan parameter spelled "this"
	private /*@LazyNonNull*/ CGParameter typeIdParameter = null;			// A local orphan parameter spelled "typeId"
	private /*@LazyNonNull*/ CGParameter anyParameter = null;				// A local parameter spelled "any" to be added to the static signature

	public NestedNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull JavaLocalContext localContext,
			@NonNull CGNamedElement cgScope, @NonNull NamedElement asScope, @NonNull Type asType, boolean isStatic) {
		super(parent, parent.helper);


		this.codeGenerator = codeGenerator;
		this.analyzer = codeGenerator.getAnalyzer();
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.localContext = localContext;

		this.parent = parent;
		this.cgScope = cgScope;
		this.asScope = asScope;
		this.asType = asType;
		this.isStatic = isStatic;
		assert !(parent instanceof NestedNameManager) || (((NestedNameManager)parent).cgScope != cgScope);		// XXX
		parent.addChild(this);

		JavaLocalContext outerContext = localContext.outerContext;
		assert (outerContext == null) || (outerContext.nameManager == parent);
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

	public void assignExtraNames(@NonNull Context context) {
		for (Entry<@NonNull CGNamedElement, @Nullable Map<@NonNull NameVariant, @Nullable String>> entry1 : element2nameVariant2name.entrySet()) {
			Map<@NonNull NameVariant, @Nullable String> nameVariant2name = entry1.getValue();
			if (nameVariant2name != null) {
				CGNamedElement cgElement = entry1.getKey();
				assert cgElement.eContainer() != null;		// Not eliminated by CSE		-- ?? obsolete
				String resolvedName;
				if (cgElement instanceof CGValuedElement) {
					CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
					NameResolution nameResolution = cgValuedElement.basicGetNameResolution();
					if (nameResolution == null) {
						nameResolution = declareLazyName(cgValuedElement);
						nameResolution.resolveNameHint();;
					}
					nameResolution.resolveIn(context, cgValuedElement);
					resolvedName = nameResolution.getResolvedName();
				}
				else {
					resolvedName = cgElement.getName();
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

	protected void assignReservedNames(@NonNull Context context) {
		if (reservedNameResolutions != null) {
			for (@NonNull NameResolution nameResolution : reservedNameResolutions) {
				String resolvedName = nameResolution.getResolvedName();
				CGValuedElement primaryElement = nameResolution.getPrimaryElement();
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

	public @Nullable CGVariable basicGetModelManagerVariable() {
		return modelManagerVariable;
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

	public @Nullable String basicGetVariantResolvedName(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
		Map<@NonNull NameVariant, @Nullable String> nameVariant2name = element2nameVariant2name.get(cgElement);
		return nameVariant2name != null ? nameVariant2name.get(nameVariant) : null;
	}

	public @NonNull CGVariable createIdResolverVariable() {
		CGNativeOperationCallExp idResolverInit = analyzer.createCGNativeOperationCallExp(JavaConstants.EXECUTOR_GET_ID_RESOLVER_METHOD, SupportOperationCallingConvention.INSTANCE);
		NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
		idResolverNameResolution.addCGElement(idResolverInit);
		idResolverInit.setTypeId(analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID));
		idResolverInit.setCgThis(analyzer.createCGVariableExp(localContext.getExecutorVariable()));
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
		modelManagerInit.setCgThis(analyzer.createCGVariableExp(localContext.getExecutorVariable()));
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

	@Override
	public @NonNull NameResolution declareLazyName(@NonNull CGValuedElement cgElement) {
		if (cgElement.isGlobal()) {
			return globalNameManager.declareLazyName(cgElement);
		}
		return super.declareLazyName(cgElement);
	}

	/**
	 * Declare that cgElement has a name which can eventually default to its preferred value.
	 * This is typically used to provide an eager name resolution for a variable without reserving the name.
	 */
	public @NonNull NameResolution declarePreferredName(@NonNull CGValuedElement cgElement) {
	//	assert cgElement.getNamedValue() == cgElement;
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution != null) {
			return nameResolution;
		}
		String nameHint = helper.getNameHint(cgElement);
		return new NameResolution(this, cgElement, nameHint);
	}

	@Override
	public @NonNull NameResolution declareReservedName(@NonNull CGValuedElement cgElement, @NonNull String nameHint) {
		assert !cgElement.isGlobal();
		assert cgElement.getNamedValue() == cgElement;
		NameResolution nameResolution2 = cgElement.basicGetNameResolution();
		assert nameResolution2 == null;
		NameResolution baseNameResolution = new NameResolution(this, cgElement, nameHint);
		baseNameResolution.setResolvedName(nameHint);
		List<@NonNull NameResolution> reservedNameResolutions2 = reservedNameResolutions;
		if (reservedNameResolutions2 == null) {
			reservedNameResolutions = reservedNameResolutions2 = new ArrayList<>();
		}
		reservedNameResolutions2.add(baseNameResolution);
		return baseNameResolution;
	}

	public @Nullable CGClass findCGScope() {
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

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	public @NonNull CGVariable getIdResolverVariable() {
		if (asScope instanceof CallExp) {
			JavaLocalContext outerContext = localContext.outerContext;
			assert outerContext != null;
			return outerContext.nameManager.getIdResolverVariable();
		}
		CGVariable idResolverVariable2 = idResolverVariable;
		if (idResolverVariable2 == null) {
			idResolverVariable = idResolverVariable2 = createIdResolverVariable();
		}
		return idResolverVariable2;
	}

	@Override
	protected @Nullable String getLazyNameHint(@NonNull CGValuedElement cgNamedValue) {
		if (cgNamedValue instanceof CGForeignProperty) {
			// FIXME if we make CGForeignProperty we have worse problems with dependences for non-trivial initializers
			return getNameHint(cgNamedValue);
		}
		if (cgNamedValue instanceof CGProperty) {
			return getNameHint(cgNamedValue);
		}
		return null;
	}

	public @NonNull CGVariable getModelManagerVariable() {
		if (asScope instanceof CallExp) {
			JavaLocalContext outerContext = localContext.outerContext;
			assert outerContext != null;
			return outerContext.nameManager.getModelManagerVariable();
		}
		CGVariable modelManagerVariable2 = modelManagerVariable;
		if (modelManagerVariable2 == null) {
			modelManagerVariable = modelManagerVariable2 = createModelManagerVariable();
		}
		return modelManagerVariable2;
	}

	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution unsafeNameResolution = cgElement.basicGetNameResolution();
		if (unsafeNameResolution == null) {
			unsafeNameResolution = declareLazyName(cgElement);
		}
		return unsafeNameResolution;
	}

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

	public boolean isStatic() {
		return isStatic;
	}

	public @NonNull Type asType() {
		return asType;
	}

	public @NonNull CGNamedElement cgScope() {
		return cgScope;
	}

	public @NonNull NamedElement asScope() {
		return asScope;
	}
}
