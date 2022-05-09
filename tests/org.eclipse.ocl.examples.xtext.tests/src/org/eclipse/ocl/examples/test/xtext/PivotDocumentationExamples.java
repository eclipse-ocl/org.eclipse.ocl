/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.extlibrary.Book;
import org.eclipse.ocl.examples.extlibrary.BookCategory;
import org.eclipse.ocl.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.ocl.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.ocl.examples.extlibrary.Library;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.internal.labels.LabelSubstitutionLabelProvider;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Query;
import org.eclipse.ocl.pivot.validation.ComposedEValidator;
import org.eclipse.ocl.xtext.completeocl.validation.CompleteOCLEObjectValidator;

/**
 * Tests for the OCL delegate implementations.
 */
@SuppressWarnings("null")
public class PivotDocumentationExamples extends XtextTestCase
{
	public void debugPrintf(String format, Object... args) {
		//		System.out.printf(format,  args);
	}

	public InputStream getInputStream(String fileName) throws MalformedURLException, IOException {
		URI uri = getTestModelURI(fileName);
		URL url = new URL(uri.toString());
		return url.openStream();
	}

	public URI getInputURI(String fileName) throws MalformedURLException, IOException {
		return getTestModelURI(fileName);
	}

	private @NonNull List<Library> getLibraries(ResourceSet resourceSet) {
		URI uri = getTestModelURI("models/documentation/PivotDocumentationExamples.extlibrary");
		resourceSet.getPackageRegistry().put(EXTLibraryPackage.eNS_URI, EXTLibraryPackage.eINSTANCE);
		Resource resource = resourceSet.getResource(uri, true);
		@SuppressWarnings("unchecked") List<Library> libraries = (List<Library>)(List<?>)resource.getContents();
		return libraries;
	}

	public Library getLibrary(ResourceSet resourceSet) {
		Resource resource = resourceSet.createResource(URI.createURI("test.xmi"));
		Library library = EXTLibraryFactory.eINSTANCE.createLibrary();
		resource.getContents().add(library);
		Book aBook = EXTLibraryFactory.eINSTANCE.createBook();
		aBook.setTitle("Bleak House");
		library.getBooks().add(aBook);
		Book bBook = EXTLibraryFactory.eINSTANCE.createBook();
		bBook.setTitle("Bleak House");
		library.getBooks().add(bBook);
		return library;
	}

	/*
	 * This 'test' provides the source text for the 'Parsing Constraints and Queries' example
	 * in org.eclipse.ocl.doc/doc/6310-pivot-parsing-constraints.textile
	 */
	public void test_parsingConstraintsExample() throws IOException, ParserException {
		{

			// create an OCL instance exploiting an externally supplied ResourceSet
			ResourceSet myResourceSet = new ResourceSetImpl();
			/* ... */
			OCL ocl = OCL.newInstance(myResourceSet);

			assert (ocl != null) && (myResourceSet != null); /* Suppress the unused variable markers */
			ocl.dispose();
		}

		// create an OCL instance exploiting registered models on the Java classpath
		OCL ocl = OCL.newInstance(ProjectManager.CLASS_PATH);
		ResourceSet resourceSet = ocl.getResourceSet();

		EClass contextEClass = EXTLibraryPackage.Literals.LIBRARY;
		ExpressionInOCL invariant = ocl.createInvariant(contextEClass,
				"books->forAll(b1, b2 | b1 <> b2 implies b1.title <> b2.title)");
		ExpressionInOCL query = ocl.createQuery(contextEClass,
				"books->collect(b : Book | b.category)->asSet()");

		// define a post-condition specifying the value of EModelElement::getEAnnotation(EString).
		// This operation environment includes variables representing the operation
		// parameters (in this case, only "source : String") and the operation result
		EOperation contextEOperation = NameUtil.getENamedElement(
			EcorePackage.Literals.EMODEL_ELEMENT.getEOperations(), "getEAnnotation");
		ExpressionInOCL body = ocl.createPostcondition(contextEOperation,
				"result = self.eAnnotations->any(ann | ann.source = source)");

		// define a derivation constraint for the EReference::eReferenceType property
		EReference contextEReference = EcorePackage.Literals.EREFERENCE__EREFERENCE_TYPE;
		ExpressionInOCL derive = ocl.createQuery(contextEReference,
				"self.eType->any(true).oclAsType(EClass)");

		// syntax errors such as bad text or semantic errors such as bad names throw a ParserException
		try {
			ocl.createInvariant(contextEClass, "books->forall(true)");
		}
		catch (ParserException e) {
			/*e.printStackTrace();*/
		}

		// ensure that resources are released
		ocl.dispose();

		if ((body == derive) && (invariant == query) && (resourceSet != null)) { /* Suppress the unused variable markers */ }
	}


	/*
	 * This 'test' provides the source text for the 'Evaluating Constraints and Queries' example
	 * in org.eclipse.ocl.doc/doc/6315-pivot-evaluating-constraints.textile
	 */
	public void test_evaluatingConstraintsExample() throws IOException, ParserException {
		OCL ocl = OCL.newInstance(OCL.CLASS_PATH);
		ResourceSet resourceSet = ocl.getResourceSet();

		ExpressionInOCL invariant = ocl.createInvariant(EXTLibraryPackage.Literals.LIBRARY,
				"books->forAll(b1, b2 | b1 <> b2 implies b1.title <> b2.title)");
		ExpressionInOCL query = ocl.createQuery(EXTLibraryPackage.Literals.LIBRARY,
				"books->collect(b : Book | b.category)->asSet()");

		// create a Query to evaluate our query expression
		Query queryEval = ocl.createQuery(query);

		// create another to check our constraint
		Query constraintEval = ocl.createQuery(invariant);

		List<Library> libraries = getLibraries(resourceSet);  // hypothetical source of libraries

		// only print the set of book categories for valid libraries
		for (Library next : libraries) {
			if (constraintEval.checkEcore(next)) {
				// the OCL result type of our query expression is Set(BookCategory)
				@SuppressWarnings("unchecked")
				Set<BookCategory> categories = (Set<BookCategory>) queryEval.evaluateUnboxed(next);

				debugPrintf("%s: %s%n\n", next.getName(), categories);
			}
		}

		// Check one

		// check a single library
		Library lib = getLibrary(resourceSet);  // hypothetical source of a library

		// check whether it satisfies the constraint
		debugPrintf("%s valid: %b\n", lib.getName(), ocl.check(lib, invariant));

		// MoreSuccinct

		// only print the set of book categories for valid libraries
		for (Library next : constraintEval.selectEcore(libraries)) {
			@SuppressWarnings("unchecked")
			Set<BookCategory> categories = (Set<BookCategory>) queryEval.evaluateUnboxed(next);

			debugPrintf("%s: %s%n\n", next.getName(), categories);
		}

		ocl.dispose();
	}

	/*
	 * This 'test' provides the source text for the 'Parsing OCL Document' example
	 * in org.eclipse.ocl.doc/doc/6320-pivot-parsing-documents.textile
	 */
	public void test_parsingDocumentsExample() throws IOException, ParserException {
		OCL ocl1 = OCL.newInstance(OCL.CLASS_PATH);
		ocl1.dispose();
		//-------------------------------------------------------------------------
		//	The OCL Input
		//-------------------------------------------------------------------------

		// Create an EPackage.Registry for just the EXTLibraryPackage
		EPackage.Registry registry = new EPackageRegistryImpl();
		registry.put(EXTLibraryPackage.eNS_URI, EXTLibraryPackage.eINSTANCE);

		// Create an OCL that creates a ResourceSet using the minimal EPackage.Registry
		OCL ocl = OCL.newInstance(registry);
		ResourceSet resourceSet = ocl.getResourceSet();

		// get an OCL text file via some hypothetical API
		URI uri = getInputURI("/models/documentation/parsingDocumentsExample.ocl");

		// parse the contents as an OCL document
		Resource asResource = ClassUtil.nonNullState(ocl.parse(uri));

		// accumulate the document constraints in constraintMap and print all constraints
		Map<String, ExpressionInOCL> constraintMap = new HashMap<String, ExpressionInOCL>();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject next = tit.next();
			if (next instanceof Constraint) {
				Constraint constraint = (Constraint)next;
				ExpressionInOCL expressionInOCL = ocl.getSpecification(constraint);
				if (expressionInOCL != null) {
					String name = constraint.getName();
					if (name != null) {
						constraintMap.put(name, expressionInOCL);
						debugPrintf("%s: %s%n\n", name,
							expressionInOCL.getOwnedBody());
					}
				}
			}
		}

		//-------------------------------------------------------------------------
		//	Accessing the Constraints
		//-------------------------------------------------------------------------
		Library library = getLibrary(resourceSet);  // get library from a hypothetical source

		// use the constraints defined in the OCL document

		// use getBooks() from the document in another query to find a book
		ExpressionInOCL expression = ocl.createQuery(EXTLibraryPackage.Literals.LIBRARY,
				"getBooks('Bleak House')->asSequence()->first()");

		Book book = (Book) ocl.evaluate(library, expression);
		debugPrintf("Got book: %s%n\n", book);

		// use the unique_title constraint to validate the book
		boolean isValid = ocl.check(book, ClassUtil.nonNullState(constraintMap.get("unique_title")));
		debugPrintf("Validate book: %b%n\n", isValid);

		//-------------------------------------------------------------------------
		//	Using all the Constraints to validate a model
		//-------------------------------------------------------------------------

		// Register an additional EValidator for the Complete OCL document constraints
		ComposedEValidator newEValidator = ComposedEValidator.install(EXTLibraryPackage.eINSTANCE);
		newEValidator.addChild(new CompleteOCLEObjectValidator(EXTLibraryPackage.eINSTANCE, uri));

		// Validate the entire Resource containing the library
		Resource resource = library.eResource();
		MyDiagnostician diagnostician = new MyDiagnostician();
		Diagnostic diagnostics = diagnostician.validate(resource);

		// Print the diagnostics
		if (diagnostics.getSeverity() != Diagnostic.OK) {
			String formattedDiagnostics = PivotUtil.formatDiagnostics(diagnostics, "\n");
			debugPrintf("Validation: %s\n", formattedDiagnostics);
		}

		assertEquals(Diagnostic.ERROR, diagnostics.getSeverity());
		assertEquals(4, diagnostics.getChildren().size());		// 2 ObjectEValiador missing authors, 2 CompleteOCLEObjectValidator non-unique titles
		ocl.dispose();
	}

	public class MyDiagnostician extends Diagnostician
	{
		@Override
		public Map<Object, Object> createDefaultContext() {
			Map<Object, Object> context = super.createDefaultContext();
			context.put(EValidator.SubstitutionLabelProvider.class,
				new LabelSubstitutionLabelProvider());
			return context;
		}

		public BasicDiagnostic createDefaultDiagnostic(Resource resource) {
			return new BasicDiagnostic(EObjectValidator.DIAGNOSTIC_SOURCE, 0,
				EMFEditUIPlugin.INSTANCE.getString(
					"_UI_DiagnosisOfNObjects_message", new String[]{"1"}),
				new Object[]{resource});
		}

		public Diagnostic validate(Resource resource) {
			BasicDiagnostic diagnostics = createDefaultDiagnostic(resource);
			Map<Object, Object> context = createDefaultContext();
			for (EObject eObject : resource.getContents()) {
				validate(eObject, diagnostics, context);
			}
			return diagnostics;
		}
	}
}
