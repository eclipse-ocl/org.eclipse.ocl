/*******************************************************************************
 * Copyright (c) 2014, 2018 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.consumers.tests;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.common.internal.preferences.CommonPreferenceInitializer;
import org.eclipse.ocl.examples.pivot.tests.AbstractValidateTests;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.papyrus.sysml.SysmlFactory;

/**
 * Tests that OCL validation works on consumer models such as SysML.
 */
public class ConsumerValidateTests extends AbstractValidateTests
{	
	public void testValidate_umlrt_profile_uml() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance(getProjectMap());
		Resource umlResource = doLoadUML(ocl, "umlrt.profile");
		assertNotNull(umlResource);
		assert umlResource != null;
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			StringUtil.bind(PivotMessagesInternal.ParsingError, "UMLRealTime::Capsule::A deliberately bad constraint::This is not OCL",
				"The 'Capsule::A deliberately bad constraint' constraint is invalid: 'This is not OCL'\n1: no viable alternative at 'is'")
			);
		ocl.dispose();
	}

	private static URI getBaseUMLResourceURI() {
		URI umlMetamodel = URI.createURI("pathmap://SysML_PROFILES/SysML.profile.uml");
		URL resultURL = SysmlFactory.class.getClassLoader().getResource(
			String.format("model/%s", umlMetamodel.lastSegment())); //$NON-NLS-1$

		URI result;

		if (resultURL != null) {
			// remove the /metamodel/UML.metamodel.uml segments of the resource
			// we found
			result = URI.createURI(resultURL.toExternalForm(), true)
				.trimSegments(2);
		} else {
			// probably, we're not running with JARs, so assume the source
			// project folder layout
			resultURL = SysmlFactory.class
				.getResource("SysmlFactory.class"); //$NON-NLS-1$

			String baseURL = resultURL.toExternalForm();
			baseURL = baseURL.substring(0, baseURL.lastIndexOf("/org/")); //$NON-NLS-1$
			result = URI.createURI(baseURL, true);
		}

		return result;
	}
	
	public static Map<URI, URI> initURIConverterURIMap(Map<URI, URI> uriMap) {
		URI baseURI = getBaseUMLResourceURI();
//		mapUMLResourceURIs(uriMap, UMLResource.METAMODELS_PATHMAP,
//			baseURI.appendSegment("metamodels")); //$NON-NLS-1$
		mapUMLResourceURIs(uriMap, "pathmap://SysML_PROFILES/",
			baseURI.appendSegment("model")); //$NON-NLS-1$
		mapUMLResourceURIs(uriMap, "pathmap://SysML_LIBRARIES/",
			baseURI.appendSegment("libraries")); //$NON-NLS-1$

		return uriMap;
	}

	private static void mapUMLResourceURIs(Map<URI, URI> uriMap, String uri,
			URI location) {

		URI prefix = URI.createURI(uri);

		// ensure trailing separator (make it a "URI prefix")
		if (!prefix.hasTrailingPathSeparator()) {
			prefix = prefix.appendSegment(""); //$NON-NLS-1$
		}

		// same with the location
		if (!location.hasTrailingPathSeparator()) {
			location = location.appendSegment(""); //$NON-NLS-1$
		}

		uriMap.put(prefix, location);

		// and platform URIs, too
		String folder = location.segment(location.segmentCount() - 2);
		String platformURI = String.format("%s/%s/", //$NON-NLS-1$
			"org.eclipse.papyrus.sysml", folder);
		uriMap.put(URI.createPlatformPluginURI(platformURI, true), location);
		uriMap.put(URI.createPlatformResourceURI(platformURI, true), location);
	}
	
	public void test_umlValidation_Bug413600() throws IOException {
		UMLStandaloneSetup.init();
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
//		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);			
//		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		OCL ocl = OCL.newInstance();
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		initURIConverterURIMap(resourceSet.getURIConverter().getURIMap());
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, "Bug413600");
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
//		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext); //,
//			DomainUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1", "Constraint1", "«Stereotype1»" + DomainUtil.getLabel(xx)));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			StringUtil.bind(PivotMessagesInternal.ParsingError, "SysMLmodel::Block1::Constraint2::self.SysMLPrimitive < 10.0",
					"The 'Block1::Constraint2' constraint is invalid: 'self.SysMLPrimitive < 10.0'\n1: Unresolved Operation 'SysMLPrimitiveTypes::Real::<(Real)'")
		);
		ocl.dispose();
	}
}
