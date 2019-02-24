/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.options;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.util.PivotPlugin;

/**
 * Options applicable to validation using Pivot.ocl defined constraints.
 */
public class PivotValidationOptions
{
	/**
	 * @since 1.4
	 */
	public static final @NonNull EnumeratedOption<StatusCodes.Severity> EcoreValidation = new EnumeratedOption<StatusCodes.Severity>(
			PivotPlugin.PLUGIN_ID, "ecore.validation", StatusCodes.Severity.ERROR, StatusCodes.Severity.class); //$NON-NLS-1$

	public static final @NonNull EnumeratedOption<StatusCodes.Severity> MissingSafeNavigation = new EnumeratedOption<StatusCodes.Severity>(
			PivotPlugin.PLUGIN_ID, "missing.safe.navigation", StatusCodes.Severity.IGNORE, StatusCodes.Severity.class); //$NON-NLS-1$

	public static final @NonNull EnumeratedOption<StatusCodes.Severity> RedundantSafeNavigation = new EnumeratedOption<StatusCodes.Severity>(
			PivotPlugin.PLUGIN_ID, "redundant.safe.navigation", StatusCodes.Severity.IGNORE, StatusCodes.Severity.class); //$NON-NLS-1$

	/**
	 * A Map from all the safe navigation constraint names to the validation options that control them.
	 * This avoids the need for distinct options for Operation/Property/Iteration control of the same concept.
	 */
	@Deprecated /* @deprecated use safeValidationOperation2severityOption */
	public static final @NonNull Map<String, EnumeratedOption<StatusCodes.Severity>> safeValidationName2severityOption = new HashMap<>();

	static {
		safeValidationName2severityOption.put(PivotTables.STR_CallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		// safeValidationName2severityOption.put(PivotTables.STR_CallExp_c_c_SafeSourceCannotBeMap, PivotValidationOptions.MissingSafeNavigation); -- a real not discretionary bug
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_SafeIteratorIsRequired, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_SafeIteratorIsRequired, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_OperationCallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_OperationCallExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_OppositePropertyCallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_OppositePropertyCallExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_PropertyCallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_PropertyCallExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
	}

	/**
	 * A Map from all the safe navigation constraint validation operation literals to the validation options that control them.
	 * This avoids the need for distinct options for Operation/Property/Iteration control of the same concept.
	 *
	 * @since 1.7
	 */
	public static final @NonNull Map</*@NonNull*/ EOperation, @NonNull EnumeratedOption<StatusCodes.Severity>> safeValidationOperation2severityOption = new HashMap<>();

	static {
		safeValidationOperation2severityOption.put(PivotPackage.Literals.CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		// safeValidationOperation2severityOption.put(PivotTables.STR_CallExp_c_c_SafeSourceCannotBeMap, PivotValidationOptions.MissingSafeNavigation); -- a real not discretionary bug
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATE_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATE_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATE_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.MissingSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.ITERATOR_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.MissingSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.MissingSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.OPPOSITE_PROPERTY_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.OPPOSITE_PROPERTY_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.MissingSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.PROPERTY_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationOperation2severityOption.put(PivotPackage.Literals.PROPERTY_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP, PivotValidationOptions.MissingSafeNavigation);
	}

	private PivotValidationOptions() {
		super();
	}
}
