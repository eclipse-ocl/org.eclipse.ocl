/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.common;

import java.util.LinkedHashSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

public class PivotQueries
{
	public static @NonNull LinkedHashSet<Operation> getOperations(org.eclipse.ocl.pivot.@NonNull Class type) {
		EnvironmentFactory environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
		PivotMetamodelManager metamodelManager = (PivotMetamodelManager) environmentFactory.getMetamodelManager();
		LinkedHashSet<Operation> operations = new LinkedHashSet<Operation>();
		for (Operation operation : metamodelManager.getMemberOperations(type, false)) {
			operations.add(operation);
		}
		for (Operation operation : metamodelManager.getMemberOperations(type, true)) {
			operations.add(operation);
		}
		return operations;
	}

	public static @NonNull LinkedHashSet<Property> getProperties(org.eclipse.ocl.pivot.@NonNull Class type) {
		EnvironmentFactory environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
		PivotMetamodelManager metamodelManager = (PivotMetamodelManager) environmentFactory.getMetamodelManager();
		LinkedHashSet<Property> properties = new LinkedHashSet<Property>();
		for (Property property : metamodelManager.getMemberProperties(type, false)) {
			properties.add(property);
		}
		for (Property property : metamodelManager.getMemberProperties(type, true)) {
			properties.add(property);
		}
		return properties;
	}
}
