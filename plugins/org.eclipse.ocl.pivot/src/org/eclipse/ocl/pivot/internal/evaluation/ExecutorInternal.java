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
package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

public interface ExecutorInternal extends Evaluator
{
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	@NonNull EvaluationVisitor getEvaluationVisitor();
	@Override
	@NonNull MetamodelManagerInternal getMetamodelManager();
	@NonNull EvaluationEnvironment initializeEvaluationEnvironment(@NonNull NamedElement executableObject);
	@Nullable Object internalExecuteNavigationCallExp(@NonNull NavigationCallExp propertyCallExp, @NonNull Property referredProperty, @Nullable Object sourceValue);
}
