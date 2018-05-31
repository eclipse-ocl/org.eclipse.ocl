/*
 * Copyright (c) 2015, 2018 Christian W. Damus and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *
 */

package org.eclipse.ocl.examples.pivot.tests;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * A test case that demonstrates a memory leak in the OCL Validation infrastructure.
 */
public class LeakTests extends PivotTestCaseWithAutoTearDown
{
	/**
	 * This is based on the way Papyrus does validation.
	 */
	static class MyDiagnostician extends Diagnostician
	{
		private boolean validatingStereotype;

		@Override
		public Map<Object, Object> createDefaultContext() {
			Map<Object, Object> context = super.createDefaultContext();
			if (context != null) {
				OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(context);
			}
			return context;
		}

		@Override
		public BasicDiagnostic createDefaultDiagnostic(EObject eObject) {
			ResourceSet resourceSet = eObject.eResource().getResourceSet();
			if (resourceSet != null) {
				OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
			}
			return super.createDefaultDiagnostic(eObject);
		}

		protected boolean doValidateStereotypeApplications(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			if (validatingStereotype) {
				// this function is called recursively. Avoid trying to obtain stereotype applications, if we are
				// already examining a stereotype
				return true;
			}
			List<EObject> stereotypeApplications = eObject instanceof Element ? ((Element) eObject).getStereotypeApplications() : Collections.<EObject> emptyList();
			if (!stereotypeApplications.isEmpty()) {
				Iterator<EObject> i = stereotypeApplications.iterator();
				boolean result;
				validatingStereotype = true;
				try {
					result = validate(i.next(), diagnostics, context);
					while (i.hasNext() && (result || diagnostics != null)) {
						result &= validate(i.next(), diagnostics, context);
					}
				} finally {
					validatingStereotype = false;
				}
				return result;
			} else {
				return true;
			}
		}

		@Override
		protected boolean doValidateContents(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			boolean result = doValidateStereotypeApplications(eObject, diagnostics, context);
			if (result || diagnostics != null) {
				result &= super.doValidateContents(eObject, diagnostics, context);
			}
			return result;
		}

		@Override
		public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			if (!context.containsKey(this)) {
				// put instance of the UMLDiagnostician into context map to identify first invocation
				// (validate is called recursively)
				context.put(this, null);
				BasicDiagnostic newDiagChain = createDefaultDiagnostic(eObject);
				boolean ok = super.validate(eObject, newDiagChain, context);
				// replace markers here instead of using a validation adapter, see
				// bug 410457 - [Validation] Ghost markers when validating profile constraints
				// bug 410119 - [Validation] markers related to stereotype applications are not updated in diagrams
				// bug 410059 - [Validation] delete subtree does not remove markers associated with stereotypes
				for (Diagnostic d : newDiagChain.getChildren()) {
					Object data[] = d.getData().toArray();
					if (data.length > 0) {
						Object target = data[0];
						if (target instanceof EObject) {
							EObject base = UMLUtil.getBaseElement((EObject) target);
							if (base != null) {
								data[0] = base;
							}
						}
					}
					diagnostics.add(new BasicDiagnostic(d.getSeverity(), d.getSource(), d.getCode(), d.getMessage(), data));
				}
				return ok;
			} else {
				return super.validate(eObject, diagnostics, context);
			}
		}
	}

	/**
	 * A test that demonstrates the memory leak from validation of the model.
	 * @throws ParserException
	 */
	public void testValidateProfileLeak() throws InterruptedException, ParserException {	// Bug 459276
		GlobalEnvironmentFactory.disposeInstance();
		//		PartialModels.PARTIAL_MODELS.setState(true);
		//		UML2AS.CONVERT_RESOURCE.setState(true);
		UMLStandaloneSetup.init();
		ResourceSet resourceSet = new ResourceSetImpl();
		UMLResourcesUtil.init(resourceSet);
		URI testModelURI = getTestModelURI("models/uml/Bug459276.uml");
		EClass package1 = UMLPackage.Literals.PACKAGE;
		Package umlModel = UML2Util.load(resourceSet, testModelURI, package1);
		EcoreUtil.resolveAll(resourceSet);
		Profile umlProfile = umlModel.getAppliedProfile("j2ee");
		assertNotNull("No UML Profile for leak test", umlProfile);

		// Validate the model
		Diagnostician diagnostician = new MyDiagnostician();
		diagnostician.validate(umlModel);

		// It doesn't matter what the results of validation are, only that
		// OCL constraints were parsed by the OCL validation delegate

		GlobalEnvironmentFactory globalEnvironmentFactory = GlobalEnvironmentFactory.getInstance();
		org.eclipse.ocl.pivot.Profile asProfile = globalEnvironmentFactory.getASOf(org.eclipse.ocl.pivot.Profile.class, umlProfile);
		assertNotNull("No AS Profile for leak", asProfile);
		WeakReference<org.eclipse.uml2.uml.Profile> umlProfileRef = new WeakReference<org.eclipse.uml2.uml.Profile>(umlProfile);
		WeakReference<org.eclipse.ocl.pivot.Profile> asProfileRef = new WeakReference<org.eclipse.ocl.pivot.Profile>(asProfile);
		assertNotNull("No UML Profile for leak test", umlProfileRef.get());
		assertNotNull("No AS Profile for leak", asProfileRef.get());
		//
		// Eliminate our references
		//
		diagnostician = null; // There is no "dispose" API
		umlModel = null;
		umlProfile = null;
		asProfile = null;
		disposeResourceSet(resourceSet);
		resourceSet = null;
		globalEnvironmentFactory = null;
		//
		// Garbage collect and check that there are no other references
		//
		System.gc();
		assertNull("UML Profile has leaked", umlProfileRef.get());
		assertNull("AS Profile has leaked", asProfileRef.get());
	}
}
