/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.xtext.oclinecore/model/OCLinEcoreCS.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.xtext.oclinecorecs.util;

import java.lang.Object;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSUnloadVisitor;

/**
 * An AbstractOCLinEcoreCSUnloadVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractOCLinEcoreCSUnloadVisitor
	extends EssentialOCLCSUnloadVisitor
	implements OCLinEcoreCSVisitor<Object>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractOCLinEcoreCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	@Override
	public @Nullable Object visitOCLinEcoreConstraintCS(org.eclipse.ocl.xtext.oclinecorecs.@NonNull OCLinEcoreConstraintCS object) {
		return visitConstraintCS(object);
	}

	@Override
	public @Nullable Object visitSysMLCS(org.eclipse.ocl.xtext.oclinecorecs.@NonNull SysMLCS object) {
		return visitAnnotationElementCS(object);
	}

	@Override
	public @Nullable Object visitTopLevelCS(org.eclipse.ocl.xtext.oclinecorecs.@NonNull TopLevelCS object) {
		return visitRootPackageCS(object);
	}
}
