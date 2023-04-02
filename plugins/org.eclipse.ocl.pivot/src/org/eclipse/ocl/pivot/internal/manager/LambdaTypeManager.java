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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

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

	public LambdaTypeManager(@NonNull StandardLibrary standardLibrary, boolean useWeakReferences) {
		super(standardLibrary, useWeakReferences);
		this.lambdaTypes = useWeakReferences ? new WeakHashMap<>() : new HashMap<>();
	}

	@Override
	public void dispose() {
		lambdaTypes.clear();
	}

	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getLambdaType(typeName, contextType, parameterTypes, resultType);
		}
		else {
			Type specializedContextType = standardLibrary.getSpecializedType(contextType, bindings);
			List<@NonNull Type> specializedParameterTypes = new ArrayList<>();
			for (@NonNull Type parameterType : parameterTypes) {
				specializedParameterTypes.add(standardLibrary.getSpecializedType(parameterType, bindings));
			}
			Type specializedResultType = standardLibrary.getSpecializedType(resultType, bindings);
			return getLambdaType(typeName, specializedContextType, specializedParameterTypes, specializedResultType);
		}
	}

	protected @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType) {
		StandardLibrary standardLibrary = getStandardLibrary();
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
			LambdaType candidateLambda;
			if (useWeakReferences) {
				@SuppressWarnings("unchecked")
				WeakReference<@NonNull LambdaType> ref = (WeakReference<@NonNull LambdaType>)lambdasElement;
				candidateLambda = ref.get();
			}
			else {
				candidateLambda = (LambdaType)lambdasElement;
			}
			if ((candidateLambda != null) && (resultType == candidateLambda.getResultType())) {
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
		standardLibrary.addOrphanClass(lambdaType);
		lambdasList.add(useWeakReferences ? new WeakReference<@NonNull LambdaType>(lambdaType) : lambdaType);
		return lambdaType;
	}
}