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
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * LambdaTypeManager encapsulates the knowledge about known lambda types.
 */
public class LambdaTypeManager
{
	protected final @NonNull CompleteEnvironmentInternal completeEnvironment;
	protected final org.eclipse.ocl.pivot.@NonNull Class oclLambdaType;

	/**
	 * Map from from context type via result type, to list of lambda types sharing context and result types.
	 */
	private final @NonNull Map<@NonNull Type, @NonNull Map<@Nullable Type, @NonNull List<@NonNull LambdaType>>> lambdaTypes = new HashMap<>();
	// FIXME Why does a List map give a moniker test failure
	//	private final @NonNull Map<Type, Map<List<? extends Type>, LambdaType>> lambdaTypes = new HashMap<>();

	public LambdaTypeManager(@NonNull CompleteEnvironmentInternal allCompleteClasses) {
		this.completeEnvironment = allCompleteClasses;
		this.oclLambdaType = allCompleteClasses.getOwnedStandardLibrary().getOclLambdaType();
	}

	public void dispose() {
		lambdaTypes.clear();
	}

	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getLambdaType(typeName, contextType, parameterTypes, resultType);
		}
		else {
			Type specializedContextType = completeEnvironment.getSpecializedType(contextType, bindings);
			List<@NonNull Type> specializedParameterTypes = new ArrayList<>();
			for (@NonNull Type parameterType : parameterTypes) {
				specializedParameterTypes.add(completeEnvironment.getSpecializedType(parameterType, bindings));
			}
			Type specializedResultType = completeEnvironment.getSpecializedType(resultType, bindings);
			return getLambdaType(typeName, specializedContextType, specializedParameterTypes, specializedResultType);
		}
	}

	private @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType) {
		Map<@Nullable Type, @NonNull List<@NonNull LambdaType>> contextMap = lambdaTypes.get(contextType);
		if (contextMap == null) {
			contextMap = new HashMap<>();
			lambdaTypes.put(contextType, contextMap);
		}
		List<@NonNull LambdaType> lambdasList = contextMap.get(resultType);
		if (lambdasList == null) {
			lambdasList = new ArrayList<>();
			contextMap.put(resultType, lambdasList);
		}
		int iMax = parameterTypes.size();
		for (@NonNull LambdaType candidateLambda : lambdasList) {
			List<? extends Type> candidateTypes = candidateLambda.getParameterType();
			if (iMax == candidateTypes.size()) {
				boolean gotIt = true;
				for (int i = 0; i < iMax; i++) {
					Type requiredType = parameterTypes.get(i);
					Type candidateType = candidateTypes.get(i);
					if (requiredType != candidateType) {
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
		lambdaType.setContextType(contextType);
		lambdaType.getParameterType().addAll(parameterTypes);
		lambdaType.setResultType(resultType);
		lambdaType.getSuperClasses().add(oclLambdaType);
		completeEnvironment.addOrphanClass(lambdaType);
		lambdasList.add(lambdaType);
	//	System.out.println("Created " + lambdaType + " as " + NameUtil.debugSimpleName(lambdaType) + " in " + NameUtil.debugSimpleName(lambdaType.eContainer()));
		return lambdaType;
	}
}