/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * LambdaTypeManager encapsulates the knowledge about known lambda types.
 * @since 7.0
 */
public abstract class AbstractLambdaTypeManager implements LambdaTypeManager
{
	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * Map from from context type via first parameter type, which may be null, to list of lambda types sharing context and first parameter types.
	 */
	private final @NonNull Map<@NonNull PartId, @NonNull Map<@NonNull PartId, @NonNull List<@NonNull LambdaType>>> lambdaTypes = new HashMap<>();
	// FIXME Why does a List map give a moniker test failure
	//	private final @NonNull Map<Type, Map<List<? extends Type>, LambdaType>> lambdaTypes = new HashMap<>();

	protected AbstractLambdaTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public boolean conformsToLambdaType(@NonNull LambdaType actualType, @Nullable TemplateParameterSubstitutions actualSubstitutions,
			@NonNull LambdaType requiredType, @Nullable TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity) {
		LambdaParameter actualContext = PivotUtil.getOwnedContext(actualType);
		LambdaParameter requiredContext = PivotUtil.getOwnedContext(requiredType);
		Type actualContextType = PivotUtil.getType(actualContext);
		Type requiredContextType = PivotUtil.getType(requiredContext);
		if (enforceNullity) {
			boolean actualIsRequired = actualContext.isIsRequired();
			boolean requiredIsRequired = requiredContext.isIsRequired();
			if (!standardLibrary.conformsTo(actualContextType, actualIsRequired, actualSubstitutions, requiredContextType, requiredIsRequired, requiredSubstitutions)) {
				return false;
			}
		}
		else {
			if (!standardLibrary.conformsTo(actualContextType, actualSubstitutions, requiredContextType, requiredSubstitutions, false)) {
				return false;
			}
		}
		LambdaParameter actualResult = PivotUtil.getOwnedResult(actualType);
		LambdaParameter requiredResult = PivotUtil.getOwnedResult(requiredType);
		Type actualResultType = PivotUtil.getType(actualResult);
		Type requiredResultType = PivotUtil.getType(requiredResult);
		if (enforceNullity) {
			boolean actualIsRequired = actualResult.isIsRequired();
			boolean requiredIsRequired = requiredResult.isIsRequired();
			if (!standardLibrary.conformsTo(requiredResultType, requiredIsRequired, requiredSubstitutions, actualResultType, actualIsRequired, actualSubstitutions)) {	// contravariant
				return false;
			}
		}
		else {
			if (!standardLibrary.conformsTo(actualResultType, actualSubstitutions, requiredResultType, requiredSubstitutions, false)) {
				return false;
			}
		}
		List<@NonNull LambdaParameter> actualParameters = PivotUtil.getOwnedParametersList(actualType);
		List<@NonNull LambdaParameter> requiredParameters = PivotUtil.getOwnedParametersList(requiredType);
		int iMax = actualParameters.size();
		if (iMax != requiredParameters.size()) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			LambdaParameter actualParameter = actualParameters.get(i);
			LambdaParameter requiredParameter = requiredParameters.get(i);
			Type actualParameterType = PivotUtil.getType(actualParameter);
			Type requiredParameterType = PivotUtil.getType(requiredParameter);
			if (enforceNullity) {
				boolean actualIsRequired = actualParameter.isIsRequired();
				boolean requiredIsRequired = requiredParameter.isIsRequired();
				if (!standardLibrary.conformsTo(actualParameterType, actualIsRequired, actualSubstitutions, requiredParameterType, requiredIsRequired, requiredSubstitutions)) {
					return false;
				}
			}
			else {
				if (!standardLibrary.conformsTo(actualParameterType, actualSubstitutions, requiredParameterType, requiredSubstitutions, false)) {
					return false;
				}
			}
		}
		return true;
	}

	protected @NonNull LambdaParameter createLambdaParameter(@NonNull TypedElement typedElement) {
		LambdaParameter lambdaParameter = PivotFactory.eINSTANCE.createLambdaParameter();
		lambdaParameter.setName(typedElement.getName());
		lambdaParameter.setType(typedElement.getType());
		lambdaParameter.setIsRequired(typedElement.isIsRequired());
		return lambdaParameter;
	}

	protected @NonNull LambdaType createLambdaType(@NonNull TypedElement context,
			@NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		LambdaType lambdaType = PivotFactory.eINSTANCE.createLambdaType();
		lambdaType.setName(TypeId.LAMBDA_NAME);
		lambdaType.setOwnedContext(createLambdaParameter(context));
		for (TypedElement parameter : parameters) {
			lambdaType.getOwnedParameters().add(createLambdaParameter(parameter));
		}
		lambdaType.setOwnedResult(createLambdaParameter(result));
		return lambdaType;
	}

	@Override
	public void dispose() {
		lambdaTypes.clear();
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull TypedElement context, @NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getLambdaType(context, parameters, result);
		}
		else {
			TypedElement specializedContext = specialize(context, bindings);
			List<@NonNull TypedElement> specializedParameters = new ArrayList<>();
			for (@NonNull TypedElement parameter : parameters) {
				specializedParameters.add(specialize(parameter, bindings));
			}
			TypedElement specializedResult = specialize(result, bindings);
			return getLambdaType(specializedContext, specializedParameters, specializedResult);
		}
	}

	private @NonNull LambdaType getLambdaType(@NonNull TypedElement context, @NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		PartId contextPartId = getPartTypeId(context);
		Map<@NonNull PartId, @NonNull List<@NonNull LambdaType>> contextMap = lambdaTypes.get(contextPartId);
		if (contextMap == null) {
			contextMap = new HashMap<>();
			lambdaTypes.put(contextPartId, contextMap);
		}
		PartId resultPartId  = getPartTypeId(result);
		List<@NonNull LambdaType> lambdasList = contextMap.get(resultPartId);
		if (lambdasList == null) {
			lambdasList = new ArrayList<>();
			contextMap.put(resultPartId, lambdasList);
		}
		int iMax = parameters.size();
		for (@NonNull LambdaType candidateLambda : lambdasList) {
			List<@NonNull LambdaParameter> candidateParameters = PivotUtil.getOwnedParametersList(candidateLambda);
			if (iMax == candidateParameters.size()) {
				boolean gotIt = true;
				for (int i = 0; i < iMax; i++) {
					TypedElement parameter = parameters.get(i);
					LambdaParameter candidateParameter = candidateParameters.get(i);
					PartId parameterPartId  = getPartTypeId(parameter);
					PartId candidatePartId  = getPartTypeId(candidateParameter);
					if (parameterPartId != candidatePartId) {
						gotIt = false;
						break;
					}
				}
				if (gotIt) {
					return candidateLambda;
				}
			}
		}
		LambdaType lambdaType = createLambdaType(context, parameters, result);
		lambdasList.add(lambdaType);
		return lambdaType;
	}

	private @NonNull PartId getPartTypeId(@NonNull TypedElement typedElement) {
		TypeId contextTypeId = typedElement.getTypeId();
		return IdManager.getPartId(0, PivotUtil.getName(typedElement), contextTypeId, typedElement.isIsRequired());
	}

	private @NonNull TypedElement specialize(@NonNull TypedElement context, @Nullable TemplateParameterSubstitutions bindings) {
		String name = PivotUtil.getName(context);
		Type specializedType = standardLibrary.getSpecializedType(PivotUtil.getType(context), bindings);
		boolean isRequired = context.isIsRequired();
		return LambdaTypeManager.createCandidateLambdaParameter(name, specializedType, isRequired);
	}
}