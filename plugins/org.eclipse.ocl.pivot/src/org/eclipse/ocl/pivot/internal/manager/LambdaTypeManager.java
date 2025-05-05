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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * LambdaTypeManager encapsulates the knowledge about known lambda types.
 */
public class LambdaTypeManager
{
	/**
	 * Return a concrete TypedElement instance suitable for parameterizing a shared LambdaType access/creation.
	 *
	 * @since 1.23
	 */
	public static @NonNull TypedElement createCandidateLambdaParameter(@NonNull String name, @NonNull Type type, boolean isRequired) {
		TypedElement typedElement = PivotFactory.eINSTANCE.createParameter();
		typedElement.setName(name);
		typedElement.setType(type);
		typedElement.setIsRequired(isRequired);
		return typedElement;
	}

	protected final @NonNull CompleteEnvironmentInternal completeEnvironment;
	protected final org.eclipse.ocl.pivot.@NonNull Class oclLambdaType;

	/**
	 * Map from from context type via result type, to list of lambda types sharing context and result types.
	 */
	private final @NonNull Map<@NonNull TuplePartId, @NonNull Map<@NonNull TuplePartId, @NonNull List<@NonNull LambdaType>>> lambdaTypes = new HashMap<>();
	// FIXME Why does a List map give a moniker test failure
	//	private final @NonNull Map<Type, Map<List<? extends Type>, LambdaType>> lambdaTypes = new HashMap<>();

	public LambdaTypeManager(@NonNull CompleteEnvironmentInternal allCompleteClasses) {
		this.completeEnvironment = allCompleteClasses;
		this.oclLambdaType = allCompleteClasses.getOwnedStandardLibrary().getOclLambdaType();
	}

	public void dispose() {
		lambdaTypes.clear();
	}

	/**
	 * @since 1.23
	 */
	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull TypedElement context, @NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getLambdaType(typeName, context, parameters, result);
		}
		else {
			TypedElement specializedContext = specialize(context, bindings);
			List<@NonNull TypedElement> specializedParameters = new ArrayList<>();
			for (@NonNull TypedElement parameter : parameters) {
				specializedParameters.add(specialize(parameter, bindings));
			}
			TypedElement specializedResult = specialize(result, bindings);
			return getLambdaType(typeName, specializedContext, specializedParameters, specializedResult);
		}
	}

	private @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull TypedElement context, @NonNull List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		TuplePartId contextPartId = getPartTypeId(context);
		Map<@NonNull TuplePartId, @NonNull List<@NonNull LambdaType>> contextMap = lambdaTypes.get(contextPartId);
		if (contextMap == null) {
			contextMap = new HashMap<>();
			lambdaTypes.put(contextPartId, contextMap);
		}
		TuplePartId resultPartId  = getPartTypeId(result);
		List<@NonNull LambdaType> lambdasList = contextMap.get(resultPartId);
		if (lambdasList == null) {
			lambdasList = new ArrayList<>();
			contextMap.put(resultPartId, lambdasList);
		}
		int iMax = parameters.size();
		for (@NonNull LambdaType candidateLambda : lambdasList) {
			List<@NonNull LambdaParameter> candidateParameters = PivotUtilInternal.getOwnedParametersList(candidateLambda);
			if (iMax == candidateParameters.size()) {
				boolean gotIt = true;
				for (int i = 0; i < iMax; i++) {
					TypedElement parameter = parameters.get(i);
					LambdaParameter candidateParameter = candidateParameters.get(i);
					TuplePartId parameterPartId  = getPartTypeId(parameter);
					TuplePartId candidatePartId  = getPartTypeId(candidateParameter);
					if (parameterPartId != candidatePartId) {
						gotIt = false;
						break;
					}
				}
				if (gotIt) {
				//	System.out.println("Reused " + candidateLambda + " as " + NameUtil.debugSimpleName(candidateLambda) + " in " + NameUtil.debugSimpleName(candidateLambda.eContainer()));
					return candidateLambda;
				}
			}
		}
		LambdaType lambdaType = PivotFactory.eINSTANCE.createLambdaType();
		lambdaType.setName(typeName);
		lambdaType.setOwnedContext(createLambdaParameter(context));
		for (TypedElement parameter : parameters) {
			lambdaType.getOwnedParameters().add(createLambdaParameter(parameter));
		}
		lambdaType.setOwnedResult(createLambdaParameter(result));
		lambdaType.getSuperClasses().add(oclLambdaType);
		completeEnvironment.addOrphanClass(lambdaType);
		lambdasList.add(lambdaType);
	//	System.out.println("Created " + lambdaType + " as " + NameUtil.debugSimpleName(lambdaType) + " in " + NameUtil.debugSimpleName(lambdaType.eContainer()));
		return lambdaType;
	}

	private @NonNull LambdaParameter createLambdaParameter(@NonNull TypedElement typedElement) {
		LambdaParameter lambdaParameter = PivotFactory.eINSTANCE.createLambdaParameter();
		lambdaParameter.setName(typedElement.getName());
		lambdaParameter.setType(typedElement.getType());
		lambdaParameter.setIsRequired(typedElement.isIsRequired());
		return lambdaParameter;
	}

	private @NonNull TuplePartId getPartTypeId(@NonNull TypedElement typedElement) {
		TypeId contextTypeId = typedElement.getTypeId();
		return IdManager.getPartId(0, PivotUtil.getName(typedElement), contextTypeId, typedElement.isIsRequired());
	}

	private @NonNull TypedElement specialize(@NonNull TypedElement context, @Nullable TemplateParameterSubstitutions bindings) {
		String name = PivotUtil.getName(context);
		Type specializedType = completeEnvironment.getSpecializedType(PivotUtil.getType(context), bindings);
		boolean isRequired = context.isIsRequired();
		return createCandidateLambdaParameter(name, specializedType, isRequired);
	}
}