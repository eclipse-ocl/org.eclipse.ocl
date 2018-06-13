/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.common;

import java.io.File;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.library.LibraryOperation;

public interface CodeGenHelper
{
	/**
	 * Return a copyright notice using indentation to indent intermediate lines.
	 */
	@NonNull String getCopyright(@NonNull String indentation);
	
	/**
	 * Return the &lt;%...%&gt; escaped name for a type.
	 */
	@Nullable LibraryOperation loadClass(@NonNull ExpressionInOCL query, @NonNull File targetFolder,
			@NonNull String packageName, @NonNull String className, boolean saveSource) throws Exception;
	
	/**
	 * Return the GenPackage for a type.
	 */
	@NonNull GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Class type);
}
