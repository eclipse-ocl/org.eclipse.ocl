/*******************************************************************************
 * Copyright (c) 2014, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

public interface CompleteEnvironmentInternal extends CompleteEnvironment
{
	void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class pivotElement);
	/**
	 * @since 7.0
	 */
	default @Nullable CompleteClassInternal basicGetCompleteClass(@NonNull Type asType) { return null; }
	boolean conformsTo(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions);
	void dispose();
	void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass);
	void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass);
	@NonNull CompleteClassInternal getCompleteClass(@NonNull Type pivotType);
	@Override
	@NonNull CompleteModelInternal getOwnedCompleteModel();
	//	@NonNull MetamodelManager getMetamodelManager();
	//	@Override
	//	@NonNull PivotStandardLibrary getStandardLibrary();
	@Override
	@NonNull StandardLibraryInternal getOwnedStandardLibrary();
	@NonNull TupleTypeManager getTupleManager();
	@NonNull CompleteEnvironmentInternal init(@NonNull EnvironmentFactoryInternal environmentFactory);
	boolean isCodeGeneration();
	void setCodeGeneration(boolean isCodeGeneration);
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
}