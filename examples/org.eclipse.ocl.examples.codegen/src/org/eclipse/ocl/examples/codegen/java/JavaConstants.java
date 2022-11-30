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
package org.eclipse.ocl.examples.codegen.java;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId.JavaTypeIdSingletonScope;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.UnboxedCompositionProperty;
import org.eclipse.ocl.pivot.internal.library.UnboxedExplicitNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.UnboxedOppositeNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

@SuppressWarnings("null")
public class JavaConstants {

	/**
	 * Map from a Java class to the corresponding JavaTypeId singleton.
	 */
	private static @NonNull JavaTypeIdSingletonScope javaTypes = new JavaTypeIdSingletonScope();

	// XXX workaound lack of org.eclipse.ocl.runtime
	public static final @NonNull String ABSTRACT_COMPUTATION_CONSTRUCTOR_CLASS_NAME = "org.eclipse.qvtd.runtime.internal.evaluation.AbstractComputationConstructor";

	public static final @NonNull String CONSTRAINT_NAME_NAME = "constraintName";
	public static final @NonNull String CONSTRAINT_CONTEXT_NAME = "context";
	public static final @NonNull String CONSTRAINT_DIAGNOSTICS_NAME = "diagnostics";

	public static final @NonNull String E_CONTAINER_NAME = "eContainer";

	public static final @NonNull String ANY_NAME = "any";
	public static final @NonNull String BOXED_VALUES_NAME = "boxedValues";
	public static final @NonNull String CACHED_RESULT_NAME = "cachedResult";
	public static final @NonNull String EVALUATE_NAME = "evaluate";
	public static final @NonNull String EVALUATION_CACHE_NAME = "evaluationCache";
	public static final @NonNull String EXECUTOR_NAME = "executor";
	public static final @NonNull String GET_CACHED_EVALUATION_RESULT_NAME = "getCachedEvaluationResult";
	public static final @NonNull String GET_RESULT_NAME = "getResult";
	public static final @NonNull String ID_RESOLVER_NAME = "idResolver";
	public static final @NonNull String INSTANCE_NAME = "INSTANCE";
	public static final @NonNull String MODEL_MANAGER_NAME = "modelManager";
	public static final @NonNull String NEW_INSTANCE_NAME = "newInstance";
	public static final @NonNull String SELF_NAME = "self";
	public static final @NonNull String SOURCE_AND_ARGUMENT_VALUES_NAME = "sourceAndArgumentValues";
	public static final @NonNull String STANDARD_LIBRARY_NAME = "standardLibrary";
	public static final @NonNull String THIS_NAME = "this";
	public static final @NonNull String TYPE_ID_NAME = "typeId";

	public static final @NonNull String EXTERNAL_CLASS_PREFIX = "EC_";
	public static final @NonNull String EXTERNAL_OPERATION_PREFIX = "EO_";
	public static final @NonNull String EXTERNAL_PROPERTY_PREFIX = "EP_";
	public static final @NonNull String NESTED_CLASS_PREFIX = "NC_";

	public static final @NonNull TypeId CLASS_TYPE_ID = getJavaTypeId(org.eclipse.ocl.pivot.Class.class);
	public static final @NonNull TypeId PROPERTY_TYPE_ID = getJavaTypeId(Property.class);
	public static final @NonNull TypeId EXECUTOR_OPERATION_TYPE_ID = getJavaTypeId(ExecutorOperation.class);
	public static final @NonNull TypeId EXECUTOR_TYPE_ID = getJavaTypeId(Executor.class);
	public static final @NonNull TypeId ID_RESOLVER_TYPE_ID = getJavaTypeId(IdResolver.class);
	public static final @NonNull TypeId MODEL_MANAGER_TYPE_ID = getJavaTypeId(ModelManager.class);
	//	public static final @NonNull TypeId SELF_TYPE_ID = getJavaTypeId(Object.class);
	public static final @NonNull TypeId STANDARD_LIBRARY_TYPE_ID = getJavaTypeId(StandardLibrary.class);
	public static final @NonNull TypeId TYPE_ID_TYPE_ID = getJavaTypeId(TypeId.class);
	public static final @NonNull TypeId UNBOXED_COMPOSITION_PROPERTY_TYPE_ID = getJavaTypeId(UnboxedCompositionProperty.class);
	public static final @NonNull TypeId UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID = getJavaTypeId(UnboxedExplicitNavigationProperty.class);
	public static final @NonNull TypeId UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID = getJavaTypeId(UnboxedOppositeNavigationProperty.class);

	public static final @NonNull Method EXECUTOR_GET_ID_RESOLVER_METHOD;
	public static final @NonNull Method EXECUTOR_GET_MODEL_MANAGER_METHOD;
	public static final @NonNull Method EXECUTOR_GET_STANDARD_LIBRARY_METHOD;

	public static final @NonNull Method MODEL_MANAGER_BASIC_GET_FOREIGN_PROPERTY_VALUE_METHOD;
	public static final @NonNull Method MODEL_MANAGER_GET_FOREIGN_PROPERTY_VALUE_METHOD;
	public static final @NonNull Method MODEL_MANAGER_SET_FOREIGN_PROPERTY_VALUE_METHOD;

	public static final @NonNull Method PIVOT_UTIL_GET_EXECUTOR_GET_METHOD;

	static {
		try {
			EXECUTOR_GET_ID_RESOLVER_METHOD = SupportOperationCallingConvention.addSupportMethod(Executor.class.getMethod("getIdResolver"));
			EXECUTOR_GET_MODEL_MANAGER_METHOD = SupportOperationCallingConvention.addSupportMethod(Executor.class.getMethod("getModelManager"));
			EXECUTOR_GET_STANDARD_LIBRARY_METHOD = SupportOperationCallingConvention.addSupportMethod(Executor.class.getMethod("getStandardLibrary"));
			MODEL_MANAGER_BASIC_GET_FOREIGN_PROPERTY_VALUE_METHOD = SupportOperationCallingConvention.addSupportMethod(ModelManager.class.getMethod("basicGetForeignPropertyValue", Object.class, PropertyId.class));
			MODEL_MANAGER_GET_FOREIGN_PROPERTY_VALUE_METHOD = SupportOperationCallingConvention.addSupportMethod(ModelManager.class.getMethod("getForeignPropertyValue", Object.class, PropertyId.class, OCLExpression.class, Object.class));
			MODEL_MANAGER_SET_FOREIGN_PROPERTY_VALUE_METHOD = SupportOperationCallingConvention.addSupportMethod(ModelManager.class.getMethod("setForeignPropertyValue", Object.class, PropertyId.class, Object.class));
			PIVOT_UTIL_GET_EXECUTOR_GET_METHOD = SupportOperationCallingConvention.addSupportMethod(PivotUtil.class.getMethod("getExecutor", EObject.class));
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return the named Java typeId.
	 */
	public static @NonNull TypeId getJavaTypeId(@NonNull Class<?> javaClass) {
		if (javaClass == Boolean.class) {
			return TypeId.BOOLEAN;
		} else if (javaClass == String.class) {
			return TypeId.STRING;
		}
		return javaTypes.getSingleton(javaClass);
	}
}
