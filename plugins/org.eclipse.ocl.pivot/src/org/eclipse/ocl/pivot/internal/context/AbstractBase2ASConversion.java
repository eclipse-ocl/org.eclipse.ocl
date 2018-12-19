/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractBase2ASConversion provides the Xtext independent support for Concrete Syntax
 * to Pivot conversion.
 */
public abstract class AbstractBase2ASConversion extends AbstractConversion implements Base2ASConversion
{
	/**
	 * Set of all expression nodes whose type involves an UnspecifiedType. These are
	 * created during the left2right pass and are finally resolved to
	 * minimize invalidity.
	 *
	 * @deprecated no longer used
	 */
	@Deprecated
	private HashSet<TypedElement> underspecifiedTypedElements = null;

	protected AbstractBase2ASConversion(@NonNull EnvironmentFactoryInternal environmentFactory) {
		super(environmentFactory);
	}

	/* @deprecated no longer used */
	@Deprecated
	protected void addUnderspecifiedTypedElement(@NonNull TypedElement pivotElement) {
		if (underspecifiedTypedElements == null) {
			underspecifiedTypedElements  = new HashSet<>();
		}
		underspecifiedTypedElements.add(pivotElement);
	}

	/* @deprecated use PivotHelper.refreshName() */
	@Deprecated
	public void refreshName(@NonNull NamedElement pivotNamedElement, @Nullable String newName) {
		String oldName = pivotNamedElement.getName();
		if ((newName != oldName) && ((newName == null) || !newName.equals(oldName))) {
			pivotNamedElement.setName(newName);
		}
	}

	/* @deprecated no longer used / use PivotHelper.refreshNsURI() */
	@Deprecated
	public void refreshNsURI(org.eclipse.ocl.pivot.@NonNull Package pivotPackage, String newNsURI) {
		String oldNsURI = pivotPackage.getURI();
		if ((newNsURI != oldNsURI) && ((newNsURI == null) || !newNsURI.equals(oldNsURI))) {
			pivotPackage.setURI(newNsURI);
		}
	}

	/* @deprecated use PivotHelper.setBehavioralType() */
	@Deprecated
	public void setBehavioralType(@NonNull TypedElement targetElement, @NonNull TypedElement sourceElement) {
		if (!sourceElement.eIsProxy()) {
			Type type = PivotUtil.getBehavioralType(sourceElement);
			if ((type != null) && type.eIsProxy()) {
				type = null;
			}
			boolean isRequired = sourceElement.isIsRequired();
			setType(targetElement, type, isRequired);
		}
	}

	@Override
	public void setClassifierContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Type contextType) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if (contextVariable != null) {
			if (contextType.eIsProxy()) {
				setType(contextVariable, null, false);
			}
			else {
				setType(contextVariable, contextType, true);
			}
		}
	}

	@Override
	public void setContextVariable(@NonNull ExpressionInOCL pivotSpecification, @NonNull String selfVariableName, @Nullable Type contextType, @Nullable Type contextInstance) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if (contextVariable == null) {
			@NonNull ParameterVariable nonNullContextVariable = PivotFactory.eINSTANCE.createParameterVariable();
			contextVariable = nonNullContextVariable;
			pivotSpecification.setOwnedContext(contextVariable);
			if (contextType == null) {
				contextType = standardLibrary.getOclVoidType();
			}
		}
		refreshName(contextVariable, selfVariableName);
		setType(contextVariable, contextType, contextVariable.isIsRequired(), contextInstance);
	}

	public void setOperationContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Operation contextOperation, @Nullable String resultName) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		//		pivotSpecification.getParameterVariable().clear();
		if ((contextVariable != null) && !contextOperation.eIsProxy()) {
			setType(contextVariable, contextOperation.getOwningClass(), true);
			setParameterVariables(pivotSpecification, ClassUtil.nonNullEMF(contextOperation.getOwnedParameters()));
		}
		if (resultName != null) {
			setResultVariable(pivotSpecification, contextOperation, resultName);
		}
	}

	@Override
	public void setParameterVariables(@NonNull ExpressionInOCL pivotSpecification, @NonNull List<Parameter> parameters) {
		List<Variable> oldVariables = new ArrayList<>(pivotSpecification.getOwnedParameters());
		List<Variable> newVariables = new ArrayList<>();
		for (Parameter parameter : parameters) {
			String name = parameter.getName();
			Variable param = NameUtil.getNameable(oldVariables, name);
			if (param != null) {
				oldVariables.remove(param);
			}
			else {
				param = PivotFactory.eINSTANCE.createParameterVariable();
				param.setName(name);
			}
			setBehavioralType(param, parameter);
			param.setRepresentedParameter(parameter);
			newVariables.add(param);
		}
		refreshList(ClassUtil.nonNullModel(pivotSpecification.getOwnedParameters()), newVariables);
	}

	@Override
	public void setParameterVariables(@NonNull ExpressionInOCL pivotSpecification, @NonNull Map<String, Type> parameters) {
		List<Variable> oldVariables = new ArrayList<>(pivotSpecification.getOwnedParameters());
		List<Variable> newVariables = new ArrayList<>();
		for (String name : parameters.keySet()) {
			Type type = parameters.get(name);
			Variable param = NameUtil.getNameable(oldVariables, name);
			if (param != null) {
				oldVariables.remove(param);
			}
			else {
				param = PivotFactory.eINSTANCE.createParameterVariable();
				param.setName(name);
			}
			setType(param, type, param.isIsRequired());
			//		    param.setRepresentedParameter(parameter);
			newVariables.add(param);
		}
		refreshList(ClassUtil.nonNullModel(pivotSpecification.getOwnedParameters()), newVariables);
	}

	public void setPropertyContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Property contextProperty) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if ((contextVariable != null) && !contextProperty.eIsProxy()) {
			setType(contextVariable, contextProperty.getOwningClass(), true);
		}
	}

	@Override
	public void setResultVariable(@NonNull ExpressionInOCL pivotSpecification, @NonNull Operation contextOperation, @NonNull String resultName) {
		Type returnType = contextOperation.getType();
		if (returnType != null) {					// FIXME BUG 385711 Use OclVoid rather than null
			Variable resultVariable = pivotSpecification.getOwnedResult();
			if (resultVariable == null) {
				resultVariable = PivotFactory.eINSTANCE.createParameterVariable();
			}
			resultVariable.setName(resultName);
			setBehavioralType(resultVariable, contextOperation);
			pivotSpecification.setOwnedResult(resultVariable);
		}
		else {
			pivotSpecification.setOwnedResult(null);
		}
	}

	/**
	 * Set the type and so potentially satisfy some TypeOfDependency. This method ensures that
	 * type is not set to null.
	 *
	 * @deprecated no longer used / use PivotHelper.refreshNsURI()
	 */
	@Deprecated
	public void setType(@NonNull TypedElement pivotElement, Type type) {
		setType(pivotElement, type, pivotElement.isIsRequired());
	}

	@Deprecated /* @deprecated no longer used */
	public void setType(@NonNull OCLExpression pivotElement, Type type, boolean isRequired, @Nullable Type typeValue) {	// FIXME redirect to PivotHelper
		setType(pivotElement, type, isRequired);
		Type primaryTypeValue = typeValue != null ? metamodelManager.getPrimaryType(typeValue) : null;
		if (primaryTypeValue != pivotElement.getTypeValue()) {
			pivotElement.setTypeValue(primaryTypeValue);
		}
	}
	@Deprecated /* @deprecated only used locally */
	public void setType(@NonNull VariableDeclaration pivotElement, Type type, boolean isRequired, @Nullable Type typeValue) {	// FIXME redirect to PivotHelper
		setType(pivotElement, type, isRequired);
		Type primaryTypeValue = typeValue != null ? metamodelManager.getPrimaryType(typeValue) : null;
		if (primaryTypeValue != pivotElement.getTypeValue()) {
			pivotElement.setTypeValue(primaryTypeValue);
		}
	}

	/* @deprecated only used locally / use PivotHelper.setType() */
	@Deprecated
	public void setType(@NonNull TypedElement pivotElement, Type type, boolean isRequired) {
		Type primaryType = type != null ? metamodelManager.getPrimaryType(type) : null;
		if (primaryType != pivotElement.getType()) {
			pivotElement.setType(primaryType);
		}
		boolean wasRequired = pivotElement.isIsRequired();
		if (wasRequired != isRequired) {
			pivotElement.setIsRequired(isRequired);
		}
		if (primaryType != null) {
			if (!PivotUtil.debugWellContainedness(primaryType)) {
				primaryType = type != null ? metamodelManager.getPrimaryType(type) : null;
			}
		}
	}
}