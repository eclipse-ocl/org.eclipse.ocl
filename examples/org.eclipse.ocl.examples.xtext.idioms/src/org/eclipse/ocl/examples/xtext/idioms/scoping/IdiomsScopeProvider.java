/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.idioms.scoping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.idioms.EPackageImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;

/**
 * This class contains custom scoping description.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
public class IdiomsScopeProvider extends AbstractIdiomsScopeProvider
{
	public class ImportedEPackageScope extends AbstractScope
	{
		protected final @NonNull IdiomsModel idiomsModel;

		public ImportedEPackageScope(@NonNull IdiomsModel idiomsModel) {
			super(NULLSCOPE, false);
			this.idiomsModel = idiomsModel;
		//	for (EPackageImport ePackageImport : idiomsModel.getOwnedImports()) {
		//		EPackage ePackage = ePackageImport.getEPackage();
		//		String as = ePackageImport.getAs();
		//	}
		}

		@Override
		protected Iterable<IEObjectDescription> getAllLocalElements() {
			List<IEObjectDescription> allElements = new ArrayList<>();
			for (EPackageImport ePackageImport : idiomsModel.getOwnedImports()) {
				EPackage ePackage = ePackageImport.getEPackage();
				String as = ePackageImport.getAs();
				if (as != null) {
					allElements.add(EObjectDescription.create(QualifiedName.create(as), ePackage));
				}
			}
			return allElements;
		}
	}

	public class ImportEPackageScope extends AbstractScope
	{
		protected final @NonNull EPackageImport ePackageImport;

		public ImportEPackageScope(@NonNull EPackageImport ePackageImport) {
			super(NULLSCOPE, false);
			this.ePackageImport = ePackageImport;
		}

		@Override
		protected Iterable<IEObjectDescription> getAllLocalElements() {
			List<IEObjectDescription> allElements = new ArrayList<>();
			allElements.add(EObjectDescription.create(QualifiedName.create(ePackageImport.getAs()), ePackageImport.getEPackage()));
			return allElements;
		}
	}

	public class ImportIdiomsModelScope extends AbstractScope
	{
		protected final @NonNull IdiomsImport idiomsImport;

		public ImportIdiomsModelScope(@NonNull IdiomsImport idiomsImport) {
			super(NULLSCOPE, false);
			this.idiomsImport = idiomsImport;
		}

		@Override
		protected Iterable<IEObjectDescription> getAllLocalElements() {
			List<IEObjectDescription> allElements = new ArrayList<>();
			IdiomsModel idiomsModel = idiomsImport.getIdiomsModel();
			String as = idiomsImport.getAs();
			if (as == null) {
				as = idiomsModel.getName();
			}
			allElements.add(EObjectDescription.create(QualifiedName.create(as), idiomsModel));
			return allElements;
		}
	}

	public static class ImportedIdiomsModelScope extends AbstractScope
	{
		protected final @NonNull IdiomsModel idiomsModel;

		public ImportedIdiomsModelScope(@NonNull IdiomsModel idiomsModel) {
			super(NULLSCOPE, false);
			this.idiomsModel = idiomsModel;
		}

		@Override
		protected Iterable<IEObjectDescription> getAllLocalElements() {
			List<IEObjectDescription> allElements = new ArrayList<>();
			for (@NonNull IdiomsModel anIdiomsModel : idiomsModel.getIdiomsModels()) {
				allElements.add(EObjectDescription.create(QualifiedName.create(anIdiomsModel.getName()), anIdiomsModel));
			}
			return allElements;
		}

		@Override
		public IEObjectDescription getSingleElement(QualifiedName name) {
			String simpleName = name.toString();
			IdiomsModel referencedIdiomsModel = idiomsModel.getIdiomsModel(simpleName);
			return referencedIdiomsModel != null ? EObjectDescription.create(name, referencedIdiomsModel) : null;
		}
	}

	@Override
	public IScope getScope(EObject context, EReference reference) {
		assert context != null;
		if (reference == IdiomsPackage.Literals.EPACKAGE_IMPORT__EPACKAGE) {
			return new ImportEPackageScope((EPackageImport)context);
		}
		else if (reference == IdiomsPackage.Literals.IDIOMS_IMPORT__IDIOMS_MODEL) {
			return new ImportIdiomsModelScope((IdiomsImport)context);
		}
		EClass eReferenceType = reference.getEReferenceType();
		if (eReferenceType == EcorePackage.Literals.EPACKAGE)	{
			IdiomsModel idiomsModel = (IdiomsModel) EcoreUtil.getRootContainer(context);
			assert idiomsModel != null;
			return new ImportedEPackageScope(idiomsModel);
		}
		else if (eReferenceType == IdiomsPackage.Literals.IDIOMS_MODEL)	{
			IdiomsModel idiomsModel = (IdiomsModel) EcoreUtil.getRootContainer(context);
			assert idiomsModel != null;
			return new ImportedIdiomsModelScope(idiomsModel);
		}
		return super.getScope(context, reference);
	}

}
