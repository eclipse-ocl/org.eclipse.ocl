/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.label;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.labels.LabelGeneratorRegistry;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class LabelTests extends TestCase
{
	protected ILabelGenerator.@NonNull Registry registry = new LabelGeneratorRegistry(ILabelGenerator.Registry.INSTANCE);
	
	@Override
	public void setUp() {
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			LabelGeneratorRegistry.initialize(registry);
		}
		EcorePackage.eINSTANCE.getClass();
	}
	
/*	public void testEcoreURIDebugLabel() {
		String testURI = "http://xyzzy/jj";
		URI uri = URI.createURI(testURI);
		String actualLabel = DebugString.debugLabelFor(uri);
		String expectedLabel = "'" + testURI + "'";
		assertEquals(expectedLabel, actualLabel);
	} */
	
	public void testEcoreURILabel() {
		String testURI = "http://xyzzy/jj";
		URI uri = URI.createURI(testURI);
		String actualLabel = registry.labelFor(uri);
		String expectedLabel = testURI;
		assertEquals(expectedLabel, actualLabel);
	}
	
	public void testEcoreFeatureLabel() {
		String actualLabel = registry.labelFor(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		String expectedLabel = "ecore/ENamedElement/name";
		assertEquals(expectedLabel, actualLabel);
	}
	
	public void testEcoreFeatureQualifiedName() {
		String actualLabel = NameUtil.qualifiedNameFor(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		String expectedLabel = "ecore::ENamedElement::name";
		assertEquals(expectedLabel, actualLabel);
	}
	
	public void testEcoreFeatureName() {
		String actualLabel = NameUtil.simpleNameFor(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		String expectedLabel = "name";
		assertEquals(expectedLabel, actualLabel);
	}
	
/*	public void testEcoreFeatureDebugLabel() {
		String actualLabel = DebugString.debugLabelFor(EcorePackage.Literals.ENAMED_ELEMENT__NAME);
		String expectedLabel = "ecore/ENamedElement/name";
		assertEquals(expectedLabel, actualLabel);
	} */
}
