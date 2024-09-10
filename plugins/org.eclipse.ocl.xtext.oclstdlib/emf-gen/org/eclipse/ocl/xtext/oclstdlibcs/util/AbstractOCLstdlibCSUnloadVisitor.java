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
 * from: org.eclipse.ocl.xtext.oclstdlib/model/OCLstdlibCS.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.xtext.oclstdlibcs.util;

import java.lang.Object;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSUnloadVisitor;

/**
 * An AbstractOCLstdlibCSUnloadVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractOCLstdlibCSUnloadVisitor
	extends EssentialOCLCSUnloadVisitor
	implements OCLstdlibCSVisitor<Object>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractOCLstdlibCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	@Override
	public @Nullable Object visitJavaClassCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull JavaClassCS object) {
		return visitNamedElementCS(object);
	}

	@Override
	public @Nullable Object visitJavaImplementationCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull JavaImplementationCS object) {
		return visitElementCS(object);
	}

	@Override
	public @Nullable Object visitLibClassCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibClassCS object) {
		return visitStructuredClassCS(object);
	}

	@Override
	public @Nullable Object visitLibCoercionCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibCoercionCS object) {
		return visitOperationCS(object);
	}

	@Override
	public @Nullable Object visitLibConstraintCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibConstraintCS object) {
		return visitConstraintCS(object);
	}

	@Override
	public @Nullable Object visitLibIterationCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibIterationCS object) {
		return visitOperationCS(object);
	}

	@Override
	public @Nullable Object visitLibOperationCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibOperationCS object) {
		return visitOperationCS(object);
	}

	@Override
	public @Nullable Object visitLibOppositeCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibOppositeCS object) {
		return visitFeatureCS(object);
	}

	@Override
	public @Nullable Object visitLibPackageCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibPackageCS object) {
		return visitPackageCS(object);
	}

	@Override
	public @Nullable Object visitLibPropertyCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibPropertyCS object) {
		return visitAttributeCS(object);
	}

	@Override
	public @Nullable Object visitLibRootPackageCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull LibRootPackageCS object) {
		return visitRootPackageCS(object);
	}

	@Override
	public @Nullable Object visitMetaclassNameCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull MetaclassNameCS object) {
		return visitElementCS(object);
	}

	@Override
	public @Nullable Object visitPrecedenceCS(org.eclipse.ocl.xtext.oclstdlibcs.@NonNull PrecedenceCS object) {
		return visitNamedElementCS(object);
	}
}
