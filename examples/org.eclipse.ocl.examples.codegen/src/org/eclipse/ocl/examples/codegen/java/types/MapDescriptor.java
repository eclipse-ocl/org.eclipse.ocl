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
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.MapTypeId;

/**
 * A MapDescriptor defines the description of a type for a map of objects.
 */
public interface MapDescriptor extends UnboxedDescriptor
{
	void append(@NonNull JavaStream javaStream, boolean reClass);
	void appendElement(@NonNull JavaStream javaStream, boolean reClass);	
	@NonNull MapTypeId getElementId();
}