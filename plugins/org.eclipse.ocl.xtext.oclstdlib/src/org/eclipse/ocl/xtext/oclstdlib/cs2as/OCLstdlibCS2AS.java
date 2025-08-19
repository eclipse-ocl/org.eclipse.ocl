/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - Bug 397429
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.cs2as;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.JavaClassCS;
import org.eclipse.ocl.xtext.basecs.JavaImplementationCS;
import org.eclipse.ocl.xtext.essentialocl.cs2as.EssentialOCLCS2AS;
import org.eclipse.ocl.xtext.oclstdlibcs.MetaclassNameCS;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSFactory;
import org.eclipse.ocl.xtext.oclstdlibcs.util.OCLstdlibCSVisitor;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class OCLstdlibCS2AS extends EssentialOCLCS2AS
{
	private @NonNull Map<@NonNull String, @NonNull MetaclassNameCS> metaTypeNames = new HashMap<>();
	private @NonNull Map<@NonNull String, @NonNull Precedence> name2precedence = new HashMap<>();

	public OCLstdlibCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull CSResource csResource, @NonNull ASResource asResource) {
		super(environmentFactory, csResource, asResource);
		Resource metaTypeResource = new ResourceImpl(URI.createURI("internal_list;;//of_meta-type_names"));
		List<@NonNull EObject> metaTypes = metaTypeResource.getContents();
		for (EClassifier eClassifier : PivotPackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				if (PivotPackage.Literals.CLASS.isSuperTypeOf((EClass) eClassifier)) {
					MetaclassNameCS csMetaclassName = OCLstdlibCSFactory.eINSTANCE.createMetaclassNameCS();
					String name = eClassifier.getName();
					assert name != null;
					csMetaclassName.setName(name);
					metaTypeNames.put(name, csMetaclassName);
					metaTypes.add(csMetaclassName);			// Avoid detection of orphans by EnvironmentView.addElement()
				}
			}
		}
	}

	public OCLstdlibCS2AS(@NonNull OCLstdlibCS2AS cs2as) {
		super(cs2as);
		metaTypeNames = cs2as.metaTypeNames;
	}

	@Override
	protected @NonNull OCLstdlibCSVisitor<Continuation<?>> createContainmentVisitor(@NonNull CS2ASConversion converter) {
		return new OCLstdlibCSContainmentVisitor(converter);
	}

	@Override
	protected @NonNull OCLstdlibCSVisitor<Element> createLeft2RightVisitor(@NonNull CS2ASConversion converter) {
		return new OCLstdlibCSLeft2RightVisitor(converter);
	}

	@Override
	protected @NonNull OCLstdlibCSVisitor<Continuation<?>> createPostOrderVisitor(@NonNull CS2ASConversion converter) {
		return new OCLstdlibCSPostOrderVisitor(converter);
	}

	@Override
	protected @NonNull OCLstdlibCSVisitor<Continuation<?>> createPreOrderVisitor(@NonNull CS2ASConversion converter) {
		return new OCLstdlibCSPreOrderVisitor(converter);
	}

	public @Nullable MetaclassNameCS getMetaclassNameCS(@NonNull String metaclassName) {
		return metaTypeNames.get(metaclassName);
	}

	public @Nullable Precedence getPrecedence(@NonNull String precedenceName) {
		return name2precedence.get(precedenceName);
	}

	public @Nullable String resolveJavaClassCS(@NonNull JavaImplementationCS csJavaImplementation) {
		JavaClassCS csJavaClass;
		String text = null;
		List<INode> featureNodes = NodeModelUtils.findNodesForFeature(csJavaImplementation, BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);
		if ((featureNodes != null) && (featureNodes.size() > 0)) {			// If Xtext has parsed a reference
			INode node = featureNodes.get(0);
			text = NodeModelUtils.getTokenText(node).replace("'", "");
		}
		else {
			csJavaClass = (JavaClassCS)csJavaImplementation.eGet(BaseCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, false);
			if (csJavaClass != null) {
				if (csJavaClass.eIsProxy()) {								// If CS XMI load has loaded an ocl:#xyzzy reference
					text = ((InternalEObject)csJavaClass).eProxyURI().fragment();
				}
				else {														// If redundantly reloading
					text = csJavaClass.getName();
				}
			}
		}
		if (text != null) {
			csJavaClass = getJavaClassCS(text);
			csJavaImplementation.setImplementation(csJavaClass);
		}
		return text;
	}

	public void setPrecedences(@NonNull Iterable</*@NonNull*/ Precedence> asPrecedences) {
		name2precedence.clear();
		for (Precedence asPrecedence : asPrecedences) {
			assert asPrecedence != null;
			Precedence old = name2precedence.put(PivotUtil.getName(asPrecedence), asPrecedence);
			assert old == null;
		}
	}

	@Override
	public synchronized void update(@NonNull IDiagnosticConsumer diagnosticsConsumer) {
		standardLibrary.setLibraryLoadInProgress(standardLibrary.getLibraryResource() == null);
		try {
			getMetaclassNameCS("");								// create all metatypes eagerly to avoid lazy creation debugging confusion
			super.update(diagnosticsConsumer);
		} finally {
			standardLibrary.setLibraryLoadInProgress(false);
		}
	}
}
