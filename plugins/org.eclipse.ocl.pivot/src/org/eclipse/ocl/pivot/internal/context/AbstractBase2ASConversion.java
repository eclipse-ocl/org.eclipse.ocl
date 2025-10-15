/*******************************************************************************
 * Copyright (c) 2012, 2025 Willink Transformations and others.
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
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractBase2ASConversion provides the Xtext independent support for Concrete Syntax
 * to Pivot conversion.
 */
public abstract class AbstractBase2ASConversion extends AbstractConversion implements Base2ASConversion
{
	/**
	 * @since 7.0
	 */
	protected AbstractBase2ASConversion(@NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory);
	}

	@Override
	public void setClassifierContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Type contextType) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if (contextVariable != null) {
			if (contextType.eIsProxy()) {
				getHelper().setType(contextVariable, null, false);
			}
			else {
				getHelper().setType(contextVariable, contextType, true);
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
		getHelper();
		PivotUtil.refreshName(contextVariable, selfVariableName);
		getHelper().setType(contextVariable, contextType, contextVariable.isIsRequired(), contextInstance);
	}

	public void setOperationContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Operation contextOperation, @Nullable String resultName) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		//		pivotSpecification.getParameterVariable().clear();
		if ((contextVariable != null) && !contextOperation.eIsProxy()) {
		//	getHelper().setType(contextVariable, contextOperation.getOwningClass(), true);
			setParameterVariables(pivotSpecification, PivotUtil.getOwnedParametersList(contextOperation));
		}
		if (resultName != null) {
			setResultVariable(pivotSpecification, contextOperation, resultName);
		}
	}

	/**
	 * @since 1.17
	 */
	@Override
	public void setParameterVariables(@NonNull ExpressionInOCL pivotSpecification, @NonNull List<@NonNull Parameter> operationParameterVariables) {
		List<@NonNull Variable> oldSpecificationVariables = PivotUtil.getOwnedParametersList(pivotSpecification);
		List<@NonNull Variable> residualSpecificationVariables = new ArrayList<>(oldSpecificationVariables);
		List<@NonNull Variable> newSpecificationVariables = new ArrayList<>();
		for (Parameter operationParameterVariable : operationParameterVariables) {
			String name = operationParameterVariable.getName();
			Variable specificationVariable = NameUtil.getNameable(residualSpecificationVariables, name);
			if (specificationVariable != null) {
				residualSpecificationVariables.remove(specificationVariable);
			}
			else {
				specificationVariable = PivotFactory.eINSTANCE.createParameterVariable();
				specificationVariable.setName(name);
			}
			Type type = PivotUtil.getType(operationParameterVariable);
			if (type instanceof SelfType) {
				type = pivotSpecification.getOwnedContext().getType(); // standardLibrary.getOclAnyType();
			}
			if (type.eIsProxy()) {
				type = null;
			}
			boolean isRequired = operationParameterVariable.isIsRequired();
			getHelper().setType(specificationVariable, type, isRequired);
			specificationVariable.setRepresentedParameter(operationParameterVariable);
			newSpecificationVariables.add(specificationVariable);
		}
		refreshList(oldSpecificationVariables, newSpecificationVariables);
	}

	@Override
	public void setParameterVariables(@NonNull ExpressionInOCL pivotSpecification, @NonNull Map<@NonNull String, @NonNull Type> parameters) {
		List<@NonNull Variable> oldSpecificationVariables = PivotUtil.getOwnedParametersList(pivotSpecification);
		List<@NonNull Variable> residualSpecificationVariables = new ArrayList<>(oldSpecificationVariables);
		List<@NonNull Variable> newSpecificationVariables = new ArrayList<>();
		for (String name : parameters.keySet()) {
			Variable specificationVariable = NameUtil.getNameable(residualSpecificationVariables, name);
			if (specificationVariable != null) {
				residualSpecificationVariables.remove(specificationVariable);
			}
			else {
				specificationVariable = PivotFactory.eINSTANCE.createParameterVariable();
				specificationVariable.setName(name);
			}
			Type type = parameters.get(name);
		//	if (type instanceof SelfType) {
		//		type = pivotSpecification.getOwnedContext().getType(); // standardLibrary.getOclAnyType();
		//	}
			getHelper().setType(specificationVariable, type, specificationVariable.isIsRequired());
			//		    param.setRepresentedParameter(parameter);
			newSpecificationVariables.add(specificationVariable);
		}
		refreshList(oldSpecificationVariables, newSpecificationVariables);
	}

	public void setPropertyContext(@NonNull ExpressionInOCL pivotSpecification, @NonNull Property contextProperty) {
		Variable contextVariable = pivotSpecification.getOwnedContext();
		if ((contextVariable != null) && !contextProperty.eIsProxy()) {
			getHelper().setType(contextVariable, contextProperty.getOwningClass(), true);
		}
	}

	@Override
	public void setResultVariable(@NonNull ExpressionInOCL pivotSpecification, @NonNull Operation contextOperation, @NonNull String resultName) {
		Type returnType = contextOperation.getType();
		if (returnType != null) {					// FIXME BUG 385711 Use OclVoid rather than null
			Variable returnVariable = pivotSpecification.getOwnedResult();
			if (returnVariable == null) {
				returnVariable = PivotFactory.eINSTANCE.createParameterVariable();
			}
			returnVariable.setName(resultName);
			if (!contextOperation.eIsProxy()) {
				Type type = PivotUtil.getType(contextOperation);
				if (type.eIsProxy()) {
					type = null;
				}
				boolean isRequired = contextOperation.isIsRequired();
				getHelper().setType(returnVariable, type, isRequired);
			}
			pivotSpecification.setOwnedResult(returnVariable);
		}
		else {
			pivotSpecification.setOwnedResult(null);
		}
	}
}