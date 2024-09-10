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
 * from: org.eclipse.ocl.xtext.completeocl/model/CompleteOCLCS.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.xtext.completeoclcs.util;

import java.lang.Object;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSUnloadVisitor;

/**
 * An AbstractCompleteOCLCSUnloadVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractCompleteOCLCSUnloadVisitor
	extends EssentialOCLCSUnloadVisitor
	implements CompleteOCLCSVisitor<Object>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractCompleteOCLCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	@Override
	public @Nullable Object visitClassifierContextDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull ClassifierContextDeclCS object) {
		return visitContextDeclCS(object);
	}

	@Override
	public @Nullable Object visitCompleteOCLDocumentCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull CompleteOCLDocumentCS object) {
		return visitNamespaceCS(object);
	}

	@Override
	public @Nullable Object visitContextDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull ContextDeclCS object) {
		return visitPathNameDeclCS(object);
	}

	@Override
	public @Nullable Object visitDefCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull DefCS object) {
		return visitTypedElementCS(object);
	}

	@Override
	public @Nullable Object visitDefOperationCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull DefOperationCS object) {
		return visitDefCS(object);
	}

	@Override
	public @Nullable Object visitDefPropertyCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull DefPropertyCS object) {
		return visitDefCS(object);
	}

	@Override
	public @Nullable Object visitFeatureContextDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull FeatureContextDeclCS object) {
		return visitContextDeclCS(object);
	}

	@Override
	public @Nullable Object visitOCLMessageArgCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull OCLMessageArgCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitOperationContextDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull OperationContextDeclCS object) {
		return visitFeatureContextDeclCS(object);
	}

	@Override
	public @Nullable Object visitPackageDeclarationCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull PackageDeclarationCS object) {
		return visitPathNameDeclCS(object);
	}

	@Override
	public @Nullable Object visitPathNameDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull PathNameDeclCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitPropertyContextDeclCS(org.eclipse.ocl.xtext.completeoclcs.@NonNull PropertyContextDeclCS object) {
		return visitFeatureContextDeclCS(object);
	}
}
