/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import java.math.BigInteger;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Tests that exercise the Xtext OCL Console using simple Ecore models.
 */
public class EcoreConsoleTests extends AbstractConsoleTests
{
	public static final @NonNull String ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS = "org.eclipse.ocl.xtext.tests";

	@Override
	protected void setUp() throws Exception {
		String testProjectPath = /*EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName :*/ ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
		doDelete(testProjectPath);
		super.setUp();
	}

	public void testConsole_Ecore() throws Exception {
		assertConsoleResult(consolePage, EcorePackage.Literals.ECLASS, "self.name", "'EClass'\n");
		//		assertConsoleResult(consolePage, EcorePackage.Literals.ECLASS, "self.instanceType.eAttributes.name", "");
		//		assertConsoleResult(consolePage, EcorePackage.Literals.ECLASS, "self.ownedAttribute.name->iterate(s : String ; acc : String = '' | acc + ' ' + s)", "' abstract interface'");
	}

	public void testConsole_OCLinEcoreTutorial() throws Exception {
		doDelete(PLUGIN_ID);
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate Ecore Editor's AdapterFactoryEditingDomainResourceSet
		URI testModelURI = getTestModelURI("models/documentation/OCLinEcoreTutorialForPivot.xmi");
		Resource xmiResource = resourceSet.getResource(testModelURI, true);
		EObject xmiLibrary = xmiResource.getContents().get(0);
		EClass ecoreLibrary = xmiLibrary.eClass();
		EStructuralFeature ecoreBooks = ecoreLibrary.getEStructuralFeature("books");
		EStructuralFeature ecoreLoans = ecoreLibrary.getEStructuralFeature("loans");
		EClass ecoreBook = (EClass) ecoreBooks.getEType();
		EClass ecoreLoan = (EClass) ecoreLoans.getEType();
		EStructuralFeature bookName = ecoreBook.getEStructuralFeature("name");
		EStructuralFeature loanBook = ecoreLoan.getEStructuralFeature("book");
		EStructuralFeature bookCopies = ecoreBook.getEStructuralFeature("copies");
		@SuppressWarnings("unchecked")
		List<EObject> xmiBooks = (List<EObject>) xmiLibrary.eGet(ecoreBooks);
		EObject b1Book = null;
		EObject b2Book = null;
		for (EObject xmiBook : xmiBooks) {
			if (xmiBook.eGet(bookName).equals("b1")) {
				b1Book = xmiBook;
			}
			else if (xmiBook.eGet(bookName).equals("b2")) {
				b2Book = xmiBook;
			}
		}
		if (b2Book == null) {
			fail();
			return;
		}
		assertConsoleResult(consolePage, b1Book, "hasSpareCopies(0)", "true\n");
		assertConsoleResult(consolePage, b1Book, "hasSpareCopies(5)", "false\n");
		@SuppressWarnings("unchecked")
		EObject aLoan = ((List<EObject>) xmiLibrary.eGet(ecoreLoans)).get(0);
		//
		assertConsoleResult(consolePage, xmiLibrary, "books->sortedBy(name)", "Library::lib::Book::b1\nLibrary::lib::Book::b2\n");
		assertConsoleResult(consolePage, xmiLibrary, "isAvailable()", "<b><error>Parsing failure\n</error></b><error>\n1:1: Unresolved Operation '::isAvailable()'\n</error>");
		assertConsoleResult(consolePage, b2Book, "isAvailable()", "false\n");
		assertConsoleResult(consolePage, b1Book, "isAvailable()", "true\n");
		aLoan.eSet(loanBook, b1Book);
		assertConsoleResult(consolePage, b2Book, "isAvailable()", "false\n");
		assertConsoleResult(consolePage, b1Book, "isAvailable()", "false\n");
		b2Book.eSet(bookCopies, BigInteger.valueOf(3));
		assertConsoleResult(consolePage, b2Book, "isAvailable()", "true\n");
		assertConsoleResult(consolePage, b1Book, "isAvailable()", "false\n");
		//
		assertConsoleResult(consolePage, b1Book, "hasSpareCopies(5)", "false\n");
		//
		assertConsoleResult(consolePage, ecoreBook, "name", "'Book'\n");
		assertConsoleResult(consolePage, ecoreBook, "copies", "<b><error>Parsing failure\n</error></b><error>\n1:1: Unresolved Element '::copies'\n</error>");
		assertConsoleResult(consolePage, ecoreBook, "oclType().ownedProperties->sortedBy(name)",
			"ecore::EClass::EClass : Bag(ecore::EClass[*|?])\n"
			+ "ecore::EClass::EClass : Bag(ecore::EClass[*|?])\n"
			+ "ecore::EClass::EReference : Bag(ecore::EReference[*|?])\n"
			+ "ecore::EClass::abstract : Boolean[1]\n"
			+ "ecore::EClass::eAllAttributes : OrderedSet(ecore::EAttribute)\n"
			+ "ecore::EClass::eAllContainments : OrderedSet(ecore::EReference)\n"
			+ "ecore::EClass::eAllGenericSuperTypes : OrderedSet(ecore::EGenericType)\n"
			+ "ecore::EClass::eAllOperations : OrderedSet(ecore::EOperation)\n"
			+ "ecore::EClass::eAllReferences : OrderedSet(ecore::EReference)\n"
			+ "ecore::EClass::eAllStructuralFeatures : OrderedSet(ecore::EStructuralFeature)\n"
			+ "ecore::EClass::eAllSuperTypes : OrderedSet(ecore::EClass)\n"
			+ "ecore::EClass::eAttributes : OrderedSet(ecore::EAttribute)\n"
			+ "ecore::EClass::eGenericSuperTypes : OrderedSet(ecore::EGenericType)\n"
			+ "ecore::EClass::eIDAttribute : ecore::EAttribute[?]\n"
			+ "ecore::EClass::eOperations : OrderedSet(ecore::EOperation)\n"
			+ "ecore::EClass::eReferences : OrderedSet(ecore::EReference)\n"
			+ "ecore::EClass::eStructuralFeatures : OrderedSet(ecore::EStructuralFeature)\n"
			+ "ecore::EClass::eSuperTypes : OrderedSet(ecore::EClass)\n"
			+ "ecore::EClass::interface : Boolean[1]\n");
	}
}
