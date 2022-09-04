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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A NestedNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy..
 */
public abstract class NestedNameManager extends NameManager
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull NameManager parent;
//	protected final @NonNull CGNamedElement cgScope;
//	protected final @NonNull NamedElement asScope;
//	protected final @NonNull Type asType;
//	protected final boolean isStatic;

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

	protected NestedNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManager parent, @NonNull CGNamedElement cgScope) {
		super(parent, parent.helper);
		this.codeGenerator = codeGenerator;
		this.analyzer = codeGenerator.getAnalyzer();
		this.parent = parent;
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

	public abstract @Nullable CGParameter basicGetAnyParameter();

	public abstract @Nullable CGVariable basicGetExecutorVariable();

	public abstract @Nullable CGVariable basicGetIdResolverVariable();

	public @Nullable CGVariable basicGetLocalVariable(@NonNull VariableDeclaration asVariable) {
		return cgVariables.get(asVariable);
	}

	public abstract @Nullable CGVariable basicGetModelManagerVariable();

	public @Nullable CGParameter basicGetParameter(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = cgVariables.get(asVariable);
		if (cgVariable instanceof CGParameter) {
			return (CGParameter)cgVariable;
		}
		else if (cgVariable != null) {
			throw new IllegalStateException(cgVariable + " is not  a CGParameter");
		}
		else if (!(getASScope() instanceof Operation) && (parent instanceof NestedNameManager)) {				// XXX polymorphize
			return ((NestedNameManager)parent).basicGetParameter(asVariable);
		}
		else {
			return null;
		}
	}

	public abstract @Nullable CGVariable basicGetQualifiedThisVariable();

	public abstract @Nullable CGParameter basicGetSelfParameter();

	public abstract @Nullable CGVariable basicGetStandardLibraryVariable();

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

	protected abstract @NonNull CGParameter createAnyParameter();

	public abstract @NonNull CGFinalVariable createCGVariable(@NonNull VariableDeclaration asVariable);

	public abstract @NonNull CGFinalVariable createCGVariable(@NonNull CGValuedElement cgInit);

	protected abstract @NonNull CGVariable createExecutorVariable();

	public abstract @NonNull CGVariable createIdResolverVariable();

	public abstract @NonNull CGVariable createModelManagerVariable();

	public abstract @NonNull CGVariable createQualifiedThisVariable();

	protected abstract @NonNull CGParameter createSelfParameter();

	public abstract @NonNull CGVariable createStandardLibraryVariable();

	protected abstract @NonNull CGParameter createThisParameter();

	public @Nullable CGClass findCGScope() { // XXX ??? use GlobalNameManager.findNameManager
		for (NestedNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof NestedNameManager ? (NestedNameManager)nameManager.parent : null) {
			CGNamedElement cgScope = nameManager.getCGScope();
			if (cgScope instanceof CGClass) {
				return (CGClass) cgScope;
			}
		}
		return null;
	}

	public abstract @NonNull NamedElement getASScope();

	public abstract @NonNull Type getASType();

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return codeGenerator.getAnalyzer();
	}

	public abstract @NonNull CGParameter getAnyParameter();

	public abstract @Nullable CGValuedElement getBody();

	public abstract @NonNull CGNamedElement getCGScope();

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
	public abstract @Nullable NestedNameManager getClassParentNameManager();

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return ClassUtil.nonNullState(context);
	}

	public abstract @NonNull CGParameter getExecutorParameter();

	public abstract @NonNull CGVariable getExecutorVariable();

	public abstract @NonNull CGVariable getIdResolverVariable();

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

	public abstract @NonNull CGVariable getModelManagerVariable();

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

	public abstract @NonNull CGVariable getQualifiedThisVariable();

	public abstract @NonNull CGParameter getSelfParameter();

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

	public abstract @NonNull CGVariable getStandardLibraryVariable();

	public abstract @NonNull CGParameter getThisParameter();

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

	public abstract @NonNull CGParameter getTypeIdParameter();

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
