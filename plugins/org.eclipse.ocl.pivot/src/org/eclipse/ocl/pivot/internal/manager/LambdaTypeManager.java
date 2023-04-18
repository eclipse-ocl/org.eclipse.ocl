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
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;

/**
 * LambdaTypeManager manages the LambdaTypes created within an Orphanage.
 */
public class LambdaTypeManager extends AbstractTypeManager
{
	/**
	 * Map from from context type via first parameter type, which may be null, to list of lambda types sharing context and first parameter types.
	 */
	private final @NonNull Map<@NonNull Type, @NonNull Map<@Nullable Type, @NonNull List<@NonNull Object>>> lambdaTypes;
	// FIXME Why does a List map give a moniker test failure
	//	private final @NonNull Map<Type, Map<List<? extends Type>, LambdaType>> lambdaTypes = new HashMap<>();

	public LambdaTypeManager(@NonNull Orphanage orphanage) {
		super(orphanage);
		this.lambdaTypes = new HashMap<>();
	}

	public @Nullable LambdaType basicGetLambdaType(@NonNull LambdaTypeId lambdaTypeId) {
		// TODO Auto-generated method stub
		return null;				// XXX new typeId lookup
	}

	@Override
	public void dispose() {
		lambdaTypes.clear();
	}

	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType) {
		Map<@Nullable Type, @NonNull List<@NonNull Object>> contextMap = lambdaTypes.get(contextType);
		if (contextMap == null) {
			contextMap = new HashMap<>();
			lambdaTypes.put(contextType, contextMap);
		}
		int iMax = parameterTypes.size();
		Type firstParameterType = iMax > 0 ? parameterTypes.get(0) : null;
		List<@NonNull Object> lambdasList = contextMap.get(firstParameterType);
		if (lambdasList == null) {
			lambdasList = new ArrayList<>();
			contextMap.put(firstParameterType, lambdasList);
		}
		for (@NonNull Object lambdasElement : lambdasList) {
			LambdaType candidateLambda = (LambdaType)lambdasElement;
			if (resultType == candidateLambda.getResultType()) {
				List<? extends Type> candidateTypes = candidateLambda.getParameterType();
				if (iMax == candidateTypes.size()) {
					boolean gotIt = true;
					for (int i = 1; i < iMax; i++) {
						Type requiredType = parameterTypes.get(i);
						Type candidateType = candidateTypes.get(i);
						if (requiredType != candidateType) {
							gotIt = false;
							break;
						}
					}
					if (gotIt) {
						return candidateLambda;
					}
				}
			}
		}
		LambdaType lambdaType = PivotFactory.eINSTANCE.createLambdaType();
		lambdaType.setName(typeName);
		lambdaType.setContextType(contextType);
		lambdaType.getParameterType().addAll(parameterTypes);
		lambdaType.setResultType(resultType);
		lambdaType.getSuperClasses().add(standardLibrary.getOclLambdaType());
		lambdasList.add(lambdaType);
		orphanage.addOrphanClass(lambdaType);
		return lambdaType;
	}
}