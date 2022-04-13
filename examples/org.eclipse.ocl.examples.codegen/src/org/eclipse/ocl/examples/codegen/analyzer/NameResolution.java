/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * A NameResolution represents a future name for the value of an expression and for all unmodified clients of that value.
 *
 * The need for a neme is first declared and an actual name, hierarchically unique within the NameManager unique,
 * is assigned just before the CG2Java generation.
 *  </p>
 *  Declarations occur at various timres.
 *  </br>Java reserved words are excluded.
 *  </br>However certain reserved words such as "this" my be assigned for precisely their Java purpose.
 *  </br>Global names may be declared in the GlobalNameManager.
 *  </br>Local names may be declared in the appropriate NestedNameManager.
 *  </p>
 *  Reserved globals are typically declared and assigned during CodeGenerator/GlobalContext construction.
 *  </br>Incidental globals are typically declared during AS2CG and possibly CG3JavaPre
 *  </br>Locals may be pre-emptively declared during AS2CG or CG3JavaPre often to support additional variants.
 *  </br>Residual locals are declared at the start of CodeGenerator resolveNames.
 *  </br>Unassigned names are resolved at the end of CodeGenerator resolveNames.
 *  </br>All names are resolved when needed by CG2Java.
 */
public interface NameResolution
{
	@NonNull NameResolution addNameVariant(@NonNull NameVariant nameVariant);

	void addCGElement(@NonNull CGValuedElement cgElement);

	@Nullable String basicGetResolvedName();

	@NonNull BaseNameResolution getBaseNameResolution();

	@NonNull String getNameHint();

	@NonNull NameManager getNameManager();

//	@NonNull CGValuedElement getPrimaryElement();

	@NonNull String getResolvedName();

	@NonNull VariantNameResolution getNameVariant(@NonNull NameVariant nameVariant);

	@NonNull String getVariantResolvedName(@NonNull NameVariant nameVariant);

	boolean isUnresolved();

	void resolveNameHint();

	@Override
	@NonNull String toString();
}