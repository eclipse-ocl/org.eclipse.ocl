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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.CompleteModelImpl;
import org.eclipse.ocl.pivot.internal.CompletePackageImpl;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class RootCompletePackages extends AbstractCompletePackages
{
	private static final Logger logger = Logger.getLogger(RootCompletePackages.class);

	private static final long serialVersionUID = 1L;

	public RootCompletePackages(@NonNull CompleteModelImpl owner) {
		super(CompletePackage.class, owner, PivotPackage.Literals.COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES.getFeatureID(), PivotPackage.Literals.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL.getFeatureID());
	}

	@Override
	public @NonNull CompletePackage createCompletePackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		return new CompletePackageImpl(partialPackage.getName(), partialPackage.getNsPrefix(), partialPackage.getURI());
	}

	protected @NonNull CompletePackage createRootCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (Orphanage.isOrphanage(asPackage)) {
			return getCompleteModel().getOrphanCompletePackage();
		}
		else {
			String name;
			URI semantics = PivotUtil.basicGetPackageSemantics(asPackage);
			if (semantics != null) {				// XXX just OCL AS semantics
				name = PivotConstants.METAMODEL_NAME; //   OCL_NAME;
			}
			else {
				name = asPackage.getName();
				if (name == null) {
					name = "$anon_" + Integer.toHexString(System.identityHashCode(asPackage));
				}
			}
			assert name != null;
			String prefix = asPackage.getNsPrefix();
			String uri;
			if (semantics != null) {
				URI trimmedSemantics = semantics.trimFragment();
				uri = trimmedSemantics.toString();
			}
			else {
				uri = asPackage.getURI();
				if (uri == null) {
					uri = name;
				}
			}
			CompletePackage rootCompletePackage = createCompletePackage(name, prefix, uri);
			add(rootCompletePackage);
			return rootCompletePackage;
		}
	}

	@Override
	protected void didAdd(@NonNull CompletePackage rootCompletePackage) {
		super.didAdd(rootCompletePackage);
//		String nsURI = rootCompletePackage.getURI();			// FIXME complete/package/URI/name
//		String sharedNsURI = getCompleteURI(nsURI);
//		if ((sharedNsURI != null) && (sharedNsURI == nsURI)) {
//			name2completePackage.put(nsURI, rootCompletePackage);
//		}
	}

	@Override
	protected void didRemove(int index, CompletePackage rootCompletePackage) {
		assert rootCompletePackage != null;
		super.didRemove(index, rootCompletePackage);
//		getCompleteModel().didRemoveCompletePackage(rootCompletePackage);
	}

	@Override
	@SuppressWarnings("null")
	public @NonNull CompleteModelImpl getCompleteModel() {
		return (CompleteModelImpl)owner;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CompletePackage getOwnedCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return getCompleteModel().getCompletePackage3(asPackage);
	/*	//
		//	Try to find package by packageURI
		//
		CompletePackage completePackage = getCompleteModel().getCompletePackage2(pivotPackage);
		if (completePackage != null) {
			return completePackage;
		}
		//
		//	Else generate an error for a name-less Package, fatally if also packageURI-less.
		//
		String packageURI = pivotPackage.getURI();
		String name = pivotPackage.getName();
		if (name == null) {
			String message = null;
			for (EObject eObject = pivotPackage; eObject != null; eObject = eObject.eContainer()) {
				if (eObject instanceof Model) {
					message = "Unnamed package for '" + packageURI + "' in '" + ((Model)eObject).getExternalURI() + "'";
					break;
				}
			}
			if (message == null) {
				message = "Unnamed package for '" + packageURI + "'";
			}
			logger.error(message);
			name = packageURI;
			if (name == null) {
				throw new IllegalStateException(message);
			}
		}
		//
		//	Try to find package by name, provided there is no packageURI conflict
		//
		CompletePackage rootCompletePackage = basicGetOwnedCompletePackage(name);
		if (rootCompletePackage != null) {
			String completeURI2 = PivotUtil.getURI(rootCompletePackage);
			if ((packageURI == null) || packageURI.equals(completeURI2)) {
				return rootCompletePackage;
			}
		}
		rootCompletePackage = createRootCompletePackage(pivotPackage);
		return rootCompletePackage; */
	}

	@Override
	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages() {
		return getCompleteModel().getPartialModels().getNestedPartialPackages();
	}
}