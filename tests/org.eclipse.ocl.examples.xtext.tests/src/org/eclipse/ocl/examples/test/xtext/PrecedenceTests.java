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
package org.eclipse.ocl.examples.test.xtext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.internal.manager.PrecedenceManager;

/**
 * Tests the PrecedenceManager and Precedence relationships.
 */
public class PrecedenceTests extends XtextTestCase
{
	protected @NonNull Precedence createPrecedence(Library library1, String name, AssociativityKind associativity) {
		Precedence precedence = PivotFactory.eINSTANCE.createPrecedence();
		precedence.setName(name);
		precedence.setAssociativity(associativity);
		library1.getOwnedPrecedences().add(precedence);
		return precedence;
	}

	public void testOkAssignPrecedences() {
		Collection<@NonNull Library> libraries = new ArrayList<>();
		Library library1 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p1a = createPrecedence(library1, "A", AssociativityKind.LEFT);
		Precedence p1b = createPrecedence(library1, "B", AssociativityKind.LEFT);
		Precedence p1c = createPrecedence(library1, "D", AssociativityKind.LEFT);
		libraries.add(library1);
		Library library2 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p2a = createPrecedence(library2, "B", AssociativityKind.LEFT);
		Precedence p2b = createPrecedence(library2, "C", AssociativityKind.LEFT);
		Precedence p2c = createPrecedence(library2, "D", AssociativityKind.LEFT);
		libraries.add(library2);
		PrecedenceManager precedenceManager = new PrecedenceManager();
		List<String> errors = precedenceManager.compilePrecedences(libraries);
		assertEquals(0, precedenceManager.getOrder(p1a));
		assertEquals(1, precedenceManager.getOrder(p1b));
		assertEquals(3, precedenceManager.getOrder(p1c));
		assertEquals(1, precedenceManager.getOrder(p2a));
		assertEquals(2, precedenceManager.getOrder(p2b));
		assertEquals(3, precedenceManager.getOrder(p2c));
		assertEquals(0, errors.size());
	}

	public void testBadOrderingAssignPrecedences() {
		Collection<@NonNull Library> libraries = new ArrayList<>();
		Library library1 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p1a = createPrecedence(library1, "A", AssociativityKind.LEFT);
		Precedence p1b = createPrecedence(library1, "B", AssociativityKind.LEFT);
		libraries.add(library1);
		Library library2 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p2a = createPrecedence(library2, "B", AssociativityKind.LEFT);
		Precedence p2b = createPrecedence(library2, "A", AssociativityKind.LEFT);
		libraries.add(library2);
		PrecedenceManager precedenceManager = new PrecedenceManager();
		List<String> errors = precedenceManager.compilePrecedences(libraries);
		assertEquals(0, precedenceManager.getOrder(p1a));
		assertEquals(1, precedenceManager.getOrder(p1b));
		assertEquals(1, precedenceManager.getOrder(p2a));
		assertEquals(0, precedenceManager.getOrder(p2b));
		assertEquals(1, errors.size());
	}

	public void testBadAssociativityAssignPrecedences() {
		Collection<@NonNull Library> libraries = new ArrayList<>();
		Library library1 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p1a = createPrecedence(library1, "A", AssociativityKind.LEFT);
		libraries.add(library1);
		Library library2 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p2a = createPrecedence(library2, "A", AssociativityKind.RIGHT);
		libraries.add(library2);
		PrecedenceManager precedenceManager = new PrecedenceManager();
		List<String> errors = precedenceManager.compilePrecedences(libraries);
		assertEquals(0, precedenceManager.getOrder(p1a));
		assertEquals(0, precedenceManager.getOrder(p2a));
		assertEquals(1, errors.size());
	}

	public void testAmbiguousInternalAssignPrecedences() {
		Collection<@NonNull Library> libraries = new ArrayList<>();
		Library library1 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p1a = createPrecedence(library1, "A", AssociativityKind.LEFT);
		Precedence p1b = createPrecedence(library1, "B", AssociativityKind.LEFT);
		Precedence p1c = createPrecedence(library1, "D", AssociativityKind.LEFT);
		libraries.add(library1);
		Library library2 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p2a = createPrecedence(library2, "A", AssociativityKind.LEFT);
		Precedence p2b = createPrecedence(library2, "C", AssociativityKind.LEFT);
		Precedence p2c = createPrecedence(library2, "D", AssociativityKind.LEFT);
		libraries.add(library2);
		PrecedenceManager precedenceManager = new PrecedenceManager();
		List<String> errors = precedenceManager.compilePrecedences(libraries);
		assertEquals(0, precedenceManager.getOrder(p1a));
		assertEquals(2, precedenceManager.getOrder(p1b));
		assertEquals(3, precedenceManager.getOrder(p1c));
		assertEquals(0, precedenceManager.getOrder(p2a));
		assertEquals(1, precedenceManager.getOrder(p2b));
		assertEquals(3, precedenceManager.getOrder(p2c));
		assertEquals(1, errors.size());
	}

	public void testAmbiguousTailAssignPrecedences() {
		Collection<@NonNull Library> libraries = new ArrayList<>();
		Library library1 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p1a = createPrecedence(library1, "A", AssociativityKind.LEFT);
		Precedence p1b = createPrecedence(library1, "B", AssociativityKind.LEFT);
		libraries.add(library1);
		Library library2 = PivotFactory.eINSTANCE.createLibrary();
		Precedence p2a = createPrecedence(library2, "A", AssociativityKind.LEFT);
		Precedence p2b = createPrecedence(library2, "C", AssociativityKind.LEFT);
		libraries.add(library2);
		PrecedenceManager precedenceManager = new PrecedenceManager();
		List<String> errors = precedenceManager.compilePrecedences(libraries);
		assertEquals(0, precedenceManager.getOrder(p1a));
		assertEquals(2, precedenceManager.getOrder(p1b));
		assertEquals(0, precedenceManager.getOrder(p2a));
		assertEquals(1, precedenceManager.getOrder(p2b));
		assertEquals(1, errors.size());
	}
}
