/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.classifier;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.evaluation.ModelManager.EcoreModelManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractUnaryOperation;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * ClassifierAllInstancesOperation realises the Classifier::allInstances() library operation.
 */
public class ClassifierAllInstancesOperation extends AbstractUnaryOperation
{
	public static final @NonNull ClassifierAllInstancesOperation INSTANCE = new ClassifierAllInstancesOperation();

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @NonNull SetValue evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceVal) {
		return evaluate(getExecutor(evaluator), returnTypeId, sourceVal);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull SetValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceVal) {
		org.eclipse.ocl.pivot.Class type = asClass(sourceVal);
		Iterable<?> instances = null;
		ModelManager modelManager = executor.getModelManager();
		if (modelManager instanceof EcoreModelManager) {
			EcoreModelManager ecoreModelManager = (EcoreModelManager)modelManager;
			EObject esObject = type.getESObject();
			if (esObject instanceof EClass) {
				instances = ecoreModelManager.getInstances((EClass)esObject);
			}
			else {
				instances = ecoreModelManager.getInstances(type.getTypeId());
			}
		}
		else {	// Never happens always an EcoreModelManager
			instances = modelManager.get(type);
		}
		Set<Object> results = new HashSet<Object>();
		if (instances != null) {
			IdResolver idResolver = executor.getIdResolver();
			for (Object instance : instances) {
				results.add(idResolver.boxedValueOf(instance));
			}
		}
		return createSetValue((CollectionTypeId)returnTypeId, results);
	}
}
