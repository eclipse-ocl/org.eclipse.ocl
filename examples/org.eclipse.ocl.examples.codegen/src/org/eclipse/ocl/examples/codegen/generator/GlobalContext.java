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
package org.eclipse.ocl.examples.codegen.generator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;

public interface GlobalContext
{
	void addGlobal(@NonNull CGValuedElement cgGlobal);
	@Nullable LocalContext basicFindLocalContext(@NonNull CGNamedElement asElement);
	@Nullable LocalContext basicGetLocalContext(@NonNull CGNamedElement asElement);
	@NonNull LocalContext findLocalContext(@NonNull CGNamedElement asElement);
	@NonNull ImportNameManager getImportNameManager();
	@NonNull LocalContext getLocalContext(@NonNull CGNamedElement asElement);
}