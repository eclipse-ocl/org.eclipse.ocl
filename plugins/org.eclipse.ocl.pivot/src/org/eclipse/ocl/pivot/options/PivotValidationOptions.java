/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.options;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
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
	public static final @NonNull Map<String, EnumeratedOption<StatusCodes.Severity>> safeValidationName2severityOption =
			new HashMap<String, EnumeratedOption<StatusCodes.Severity>>();

	static {
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_SafeIteratorIsRequired, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IterateExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_SafeIteratorIsRequired, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_IteratorExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_OperationCallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		//    	safeValidationName2severityOption.put(PivotTables.STR_OperationCallExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_PropertyCallExp_c_c_SafeSourceCanBeNull, PivotValidationOptions.RedundantSafeNavigation);
		safeValidationName2severityOption.put(PivotTables.STR_PropertyCallExp_c_c_UnsafeSourceCanNotBeNull, PivotValidationOptions.MissingSafeNavigation);
	}

	private PivotValidationOptions() {
		super();
	}
}
