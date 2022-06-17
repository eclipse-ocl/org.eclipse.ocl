/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.Bug477283APackage;
import org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BPackage;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.Value;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class ImportTests extends XtextTestCase
{
	public static class SpacedOut extends AbstractSimpleUnaryOperation
	{
		public static final SpacedOut INSTANCE = new SpacedOut();

		@Override
		public Object evaluate(@Nullable Object sourceValue) {
			String string = sourceValue == null? Value.INVALID_NAME : ValueUtil.oclToString(sourceValue);
			return string;
		}
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		configurePlatformResources();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(PivotConstants.OCL_AS_FILE_EXTENSION, new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected @NonNull TestOCL createOCL() {
		TestCaseAppender.INSTANCE.uninstall();
		return new TestOCL(getTestFileSystem(), "ImportTests", getName(), getProjectMap(), null);
	}

	protected void createTestImport_OCLinEcore_Bug353793_Files()
			throws IOException {
		OCL ocl = createOCL();
		String testFileA =
				"package A1 : A2 = 'http://A3'{\n" +
						"    class A;\n" +
						"}\n";
		createOCLinEcoreFile("Bug353793A.oclinecore", testFileA);
		String testFileB =
				"package B1 : B2 = 'http://B3'{\n" +
						"    class B;\n" +
						"}\n";
		createOCLinEcoreFile("Bug353793B.oclinecore", testFileB);
		String testFileE =
				"package E1 : E2 = 'http://E3'{\n" +
						"    class E;\n" +
						"}\n";
		createEcoreFile(ocl, "Bug353793E", testFileE);
		String testFileF =
				"package F1 : F2 = 'http://F3'{\n" +
						"    class F;\n" +
						"}\n";
		createEcoreFile(ocl, "Bug353793F", testFileF);
		ocl.dispose();
	}

	protected String getNoSuchFileMessage() {
		if (isWindows()) {
			return FileNotFoundException.class.getName() + ": {0} (The system cannot find the file specified)";
		}
		else {
			return FileNotFoundException.class.getName() + ": {0} (No such file or directory)";
		}
	}

	public void testImport_CompleteOCL_Ecore() throws Exception {
		OCL ocl = createOCL();
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		String testFile =
				"import 'Names.ecore'\n" +
						"package names\n" +
						"context Named\n" +
						"inv Bogus: r.toString() = s.toString()\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_EcoreAS() throws Exception {
		OCL ocl = createOCL();
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		String testFile =
				"import 'Names.ecore.oclas#/'\n" +
						"package names\n" +
						"context Named\n" +
						"inv Bogus: r.toString() = s.toString()\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_OCLinEcore() throws Exception {
		OCL ocl = createOCL();
		getTestFileURI("Names.oclinecore", ocl, getTestModelURI("models/oclinecore/Names.oclinecore"));
		String testFile =
				"import 'Names.oclinecore'\n" +
						"package EMOF\n" +
						"context Class\n" +
						"inv Bogus: isAbstract <> null\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_OCLstdlib() throws Exception {
		TestOCL ocl = createOCL();
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		getTestFileURI("minimal.oclstdlib", ocl, getTestModelURI("models/oclstdlib/minimal.oclstdlib"));
		String testFile =
				"library 'minimal.oclstdlib'\n" +
						"import 'Names.ecore'\n" +
						"package names\n" +
						"context Named\n" +
						"inv Bogus: r.toString() and s.toString()\n" +
						"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedOperation_ERROR_, "String", "toString"));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedOperation_ERROR_, "ecore::EDouble", "toString"));
		// There are no precedences so =(s) rather than =(s.toString())
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "and", "OclInvalid"));
		doBadLoadFromString(ocl, "string.ocl", testFile, bag);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_custom_OCLstdlib() throws Exception {
		TestOCL ocl = createOCL();
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		getTestFileURI("minimal.oclstdlib", ocl, getTestModelURI("models/oclstdlib/minimal.oclstdlib"));
		String customLibrary =
				"library lib {\n" +
						"type Real : PrimitiveType {\n" +
						"operation spacedOut() : String => 'org.eclipse.ocl.examples.test.xtext.ImportTests$SpacedOut';\n" +
						"}\n" +
						"}\n";
		createOCLinEcoreFile("custom.oclstdlib", customLibrary);
		String testFile =
				"library 'minimal.oclstdlib'\n" +
						"library 'custom.oclstdlib'\n" +
						"import 'Names.ecore'\n" +
						"package names\n" +
						"context Named\n" +
						"inv Custom_Real_SpacedOut_Exists: r.spacedOut()\n" +
						"inv Standard_Real_ToString_DoesNotExist: r.toString()\n" +
						"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedOperation_ERROR_, "ecore::EDouble", "toString"));
		doBadLoadFromString(ocl, "string.ocl", testFile, bag);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_custom_OCLstdlib_eval() throws Exception {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		TestOCL ocl = createOCL();
		String customLibrary =
				"library lib : lib = 'http://custom.oclstdlib' {\n" +
						"	type Real : PrimitiveType {\n" +
						"		operation spacedOut() : String => 'org.eclipse.ocl.examples.test.xtext.ImportTests$SpacedOut';\n" +
						"	}\n" +
						"}";
		ClassLoader classLoader = org.eclipse.ocl.examples.test.xtext.ImportTests.SpacedOut.class.getClassLoader();
		assert classLoader != null;
		ocl.getMetamodelManager().addClassLoader(classLoader);
		doLoadFromString(ocl, "custom.oclstdlib", customLibrary);
		ocl.assertQueryEquals(null, "42", "42.spacedOut()");
		ocl.dispose();
	}

	public void testImport_CompleteOCL_UML() throws Exception {
		UMLStandaloneSetup.init();
		OCL ocl = createOCL();
		getTestFileURI("Names.uml", ocl, getTestModelURI("models/uml/Names.uml"));
		String testFile =
				"import 'Names.uml'\n" +
						"package unames\n" +
						"context UNamed\n" +
						"inv Bogus: r.toString() = s.toString()\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_NoSuchFile() throws Exception {
		TestOCL ocl = createOCL();
		String testFile =
				"import 'NoSuchFile1'\n" +
						"import 'NoSuchFile2.ocl'\n" +
						"import 'NoSuchFile1'\n";
		Bag<String> bag = new BagImpl<String>();
		String template2 = getNoSuchFileMessage();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile2.ocl", StringUtil.bind(template2, getTestFileURI("NoSuchFile2.ocl").toFileString())));
		doBadLoadFromString(ocl, "string.ocl", testFile, bag);
		ocl.dispose();
	}

	public void testImport_CompleteOCLAS() throws Exception {
		OCL ocl = createOCL();
		String moreCompleteOCL =
				"package ocl\n" +
						"context _'Real'\n" +
						"def: isPositive(z : Integer) : Boolean = true\n" +
						"endpackage\n";
		createOCLinEcoreFile("more.ocl", moreCompleteOCL);
		String testFile =
				"import 'more.ocl.oclas#/'\n" +
						"package ocl\n" +
						"context _'Real'\n" +
						"def: signum : Integer = 0\n" +
						"context _'Integer'\n" +
						"inv CheckIt: isPositive(1) = signum > 0\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_OCLinEcore_Bug353793_Good() throws Exception {
		createTestImport_OCLinEcore_Bug353793_Files();
		String testFileGood =
				"import 'http://www.eclipse.org/emf/2002/Ecore';\n" +
						"import A0 : 'Bug353793A.oclinecore';\n" +
						"import 'Bug353793B.oclinecore';\n" +
						"import 'Bug353793E.ecore';\n" +
						"import F0 : 'Bug353793F.ecore';\n" +
						"import G0 : 'Bug353793F.ecore#/';\n" +
						"package C1 : C2 = 'C3'\n" +
						"{\n" +
						"    class AD01 extends A0::A1::A;\n" +
						"    class BD1 extends B1::B;\n" +
						"    class ED1 extends E1::E;\n" +
						"    class FD01 extends F0::F1::F;\n" +
						"    class GD0 extends G0::F;\n" +
						"}\n";
		OCL ocl = createOCL();
		doLoadFromString(ocl, "Bug353793good.oclinecore", testFileGood);
		ocl.dispose();
	}

	public void testImport_OCLinEcore_Bug353793_Bad() throws Exception {
		createTestImport_OCLinEcore_Bug353793_Files();
		String testFileBad =
				"import 'http://www.eclipse.org/emf/2002/Ecore';\n" +
						"import A0 : 'Bug353793A.oclinecore';\n" +	// package A1 : A2 = 'http://A3'{ class A; }
						"import 'Bug353793B.oclinecore';\n" +		// package B1 : B2 = 'http://B3'{ class B; }
						"import 'Bug353793E.ecore';\n" +			// package E1 : E2 = 'http://E3'{ class E; }
						"import F0 : 'Bug353793F.ecore';\n" +		// package F1 : F2 = 'http://F3'{ class F; }
						"import G0 : 'Bug353793F.ecore#/';\n" +
						"package C1 : C2 = 'C3'\n" +
						"{\n" +
						"    class AD0 extends A0::A;\n" +
						"    class AD1 extends A1::A;\n" +
						"    class AD2 extends _'http://A3'::A;\n" +
						"    class AD3 extends A2::A;\n" +
						"    class AD4 extends A3::A;\n" +
						"    class AD011 extends A0::A1::A1::A;\n" +
						"    class BD0 extends B0::B;\n" +
						"    class BD01 extends B0::B1::B;\n" +
						"    class BD11 extends B1::B1::B;\n" +
						"    class BD2 extends B2::B;\n" +
						"    class BD3 extends B3::B;\n" +
						"    class GDC extends G0::C1::GD01;\n" +
						"    class GD01 extends G0::F1::F;\n" +
						"}\n";
		Bag<String> bag = new BagImpl<String>();
		// class AD0 extends A0::A;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", "A"));
		// class AD3 extends A2::A;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "A2"));
		// class AD4 extends A3::A;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "A3"));
		// class AD011 extends A0::A1::A1::A;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "A0::A1", "A1"));
		// class BD0 extends B0::B;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "B0"));
		// class BD01 extends B0::B1::B;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "B0"));
		// class BD11 extends B1::B1::B;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "B1", "B1"));
		// class BD2 extends B2::B;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "B2"));
		// class BD3 extends B3::B;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "B3"));
		// class GDC extends G0::C1::GD01;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "G0", "C1"));
		// class GD01 extends G0::F1::F;
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "G0", "F1"));
		TestOCL ocl = createOCL();
		doBadLoadFromString(ocl, "Bug353793bad.oclinecore", testFileBad, bag);
		ocl.dispose();
	}

	public void testImport_OCLinEcore_Ecore() throws Exception {
		TestOCL ocl = createOCL();
		getTestFileURI("Names.ecore", ocl, getTestModelURI("models/ecore/Names.ecore"));
		String testFile =
				"import 'Names.ecore';\n" +
						"import nnnn : 'Names.ecore#/';\n" +
						"package moreNames {\n" +
						"class MoreNames {\n" +
						"attribute n1 : names::Named;\n" +
						"attribute n2 : nnnn::Named;\n" +
						"attribute n3 : xyzzy::Named;\n" +
						"attribute n4 : Named;\n" +
						"}\n" +
						"}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedNamespace_ERROR_, "", "xyzzy"));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", "Named"));
		doBadLoadFromString(ocl, "string.oclinecore", testFile, bag);
		ocl.dispose();
	}

	public void testImport_OCLinEcore_OCLinEcore() throws Exception {
		TestOCL ocl = createOCL();
		getTestFileURI("Names.oclinecore", ocl, getTestModelURI("models/oclinecore/Names.oclinecore"));
		String testFile =
				"import 'Names.oclinecore';\n" +
						//FIXME			"import nnnn : 'Names.oclinecore#/';\n" +
						"package moreNames {\n" +
						"class MoreNames {\n" +
						"attribute n1 : EMOF::Class;\n" +
						//			"attribute n2 : nnnn::Class;\n" +
						//			"attribute n3 : xyzzy::Named;\n" +
						//			"attribute n4 : Named;\n" +
						"}\n" +
						"}\n";
		Bag<String> bag = new BagImpl<String>();
		//		bag.add(ClassUtil.bind(OCLMessages.UnresolvedNamespace_ERROR_, "xyzzy"));
		//		bag.add(ClassUtil.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
		//		bag.add(ClassUtil.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
		doBadLoadFromString(ocl, "string.oclinecore", testFile, bag);
		ocl.dispose();
	}

	public void testImport_OCLinEcore_NoSuchFile() throws Exception {
		TestOCL ocl = createOCL();
		String testFile =
				"import 'NoSuchFile1';\n" +
						"import 'NoSuchFile2.ecore';\n" +
						"import 'NoSuchFile1';\n";
		Bag<String> bag = new BagImpl<String>();
		String template2 = getNoSuchFileMessage();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile2.ecore", StringUtil.bind(template2, getTestFileURI("NoSuchFile2.ecore").toFileString())));
		doBadLoadFromString(ocl, "string.oclinecore", testFile, bag);
		ocl.dispose();
	}

	public void testImport_OCLstdlib_OCLstdlib() throws Exception {
		TestOCL ocl = createOCL();
		String customLibrary =
				"library ocl {\n" +
						"type Complex : PrimitiveType {\n" +
						"operation spacedOut() : String => 'org.eclipse.ocl.examples.test.xtext.ImportTests$SpacedOut';\n" +
						"}\n" +
						"}\n";
		createOCLinEcoreFile("custom.oclstdlib", customLibrary);
		String testFile =
				"import '" + LibraryConstants.STDLIB_URI + "';\n" +
						"import 'custom.oclstdlib';\n" +
						"library ocl : ocl = '" + LibraryConstants.STDLIB_URI + "' {\n" +
						"type MyType conformsTo OclAny{\n" +
						"operation mixIn(r : Real, z : Complex, t : MyType) : Boolean;\n" +
						"operation mixOut(q : WhatsThis) : Boolean;\n" +
						"}\n" +
						"}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", "WhatsThis"));
		doBadLoadFromString(ocl, "string.oclstdlib", testFile, bag);
		ocl.dispose();
	}

	public void testImport_OCLstdlib_NoSuchFile() throws Exception {
		TestOCL ocl = createOCL();
		String testFile =
				"import '" + LibraryConstants.STDLIB_URI + "';\n" +
						"import 'NoSuchFile1';\n" +
						"import 'NoSuchFile2.oclstdlib';\n" +
						"import 'NoSuchFile1';\n" +
						"library anotherOne : xxx = '" + LibraryConstants.STDLIB_URI + "'{}\n";
		Bag<String> bag = new BagImpl<String>();
		String template2 = getNoSuchFileMessage();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile1", StringUtil.bind(template2, getTestFileURI("NoSuchFile1").toFileString())));
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedImport_ERROR_, "NoSuchFile2.oclstdlib", StringUtil.bind(template2, getTestFileURI("NoSuchFile2.oclstdlib").toFileString())));
		doBadLoadFromString(ocl, "string.oclstdlib", testFile, bag);
		ocl.dispose();
	}

	public void testImport_OCLstdlib_NoURI() throws Exception {
		TestOCL ocl = createOCL();
		String testFile =
				"library anotherOne{}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(PivotMessagesInternal.MissingLibraryURI_ERROR_);
		doBadLoadFromString(ocl, "string.oclstdlib", testFile, bag);
		ocl.dispose();
	}

	/*
	 * 	Test suspended. Any uri is now allowed.
	 *
	public void testImport_OCLstdlib_WrongURI() throws Exception {
		TestOCL ocl = createOCL();
		String testFile =
			"import '" + LibraryConstants.STDLIB_URI + "';\n" +
			"library anotherOne : xxx = 'http://www.eclipse.org/ocl/3.1/OCL.oclstdlib'{}\n";		// NB 3.1 rather than 3.1.0
		Bag<String> bag = new BagImpl<String>();
		bag.add(PivotMessagesInternal.EmptyLibrary_ERROR_);
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedLibrary_ERROR_, LibraryConstants.STDLIB_URI,
			IllegalLibraryException.class.getName() + ": " +
			StringUtil.bind(PivotMessagesInternal.ImportedLibraryURI_ERROR_, LibraryConstants.STDLIB_URI, "http://www.eclipse.org/ocl/3.1/OCL.oclstdlib")));
		doBadLoadFromString(ocl, "string.oclstdlib", testFile, bag);
		ocl.dispose();
	} */

	public void testInclude_CompleteOCL() throws Exception {
		OCL ocl = createOCL();
		String moreCompleteOCL =
				"package ocl\n" +
						"context _'Real'\n" +
						"def: isPositive(z : Integer) : Boolean = true\n" +
						"endpackage\n";
		createOCLinEcoreFile("more.ocl", moreCompleteOCL);
		String testFile =
				"import 'more.ocl'\n" +
						"package ocl\n" +
						"context _'Real'\n" +
						"def: signum : Integer = 0\n" +
						"context _'Integer'\n" +
						"inv CheckIt: isPositive(1) = signum > 0\n" +
						"endpackage\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		ocl.dispose();
	}

	public void testInclude_CompleteOCL_UnresolvedOperation() throws Exception {
		TestOCL ocl = createOCL();
		String moreCompleteOCL =
				"package ocl\n" +
						"context _'Real'\n" +
						"def: isPositive() : Boolean = true\n" +
						"endpackage\n";
		createOCLinEcoreFile("more.ocl", moreCompleteOCL);
		String testFile =
				"import 'more.ocl'\n" +
						"package ocl\n" +
						"context _'Real'\n" +
						"def: signum : Integer = 0\n" +
						"context _'Integer'\n" +
						"inv CheckIt: isPositive() = signum > 0\n" +
						"inv unCheckIt: isNegative() = signum < 0\n" +
						"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(StringUtil.bind(PivotMessagesInternal.UnresolvedOperation_ERROR_, "Integer", "isNegative"));
		doBadLoadFromString(ocl, "string.ocl", testFile, bag);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_Bug450196() throws Exception {
		TestOCL ocl = createOCL();
		String moreCompleteOCL =
				"package ocl\n" +
						"context _'Integer'\n" +
						"def: isPositive() : Boolean = true\n" +
						"endpackage\n";
		createOCLinEcoreFile("imported.ocl", moreCompleteOCL);
		String testFile =
				"import 'imported.ocl'\n" +
						"package ocl\n" +
						"endpackage\n";

		ASResource resource = doLoadASResourceFromString(ocl, "importer.ocl", testFile);
		Model root = (Model) resource.getContents().get(0);
		assertEquals(1, root.getOwnedImports().size());
		assertNotNull(root.getOwnedImports().get(0));
		ocl.dispose();
	}

	public void testImport_CompleteOCL_NestedPackage_477283() throws Exception {
		EPackage.Registry.INSTANCE.put(Bug477283APackage.eNS_URI, Bug477283APackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Bug477283BPackage.eNS_URI, Bug477283BPackage.eINSTANCE);
		TestOCL ocl = createOCL();
		String testFile =
				"import 'http://www.eclipse.org/ocl/Bug477283b'\n" +
						"import 'http://www.eclipse.org/ocl/Bug477283asubsub'\n" +
						"context b::B\n" +
						"def: x(y : asubsub::ASubSub) : Boolean = true\n";
		doLoadFromString(ocl, "Bug477283.ocl", testFile);
		ocl.dispose();
	}

	public void testImport_CompleteOCL_DisposeNoISE() throws Exception {
		/*
		 * Defining an operation with a parameter of type is asub::ASub has
		 * the same effect as calling completeModel.getCompleteClass(thatClass)
		 * in PivotMetamodelManager.getEquivalentClass(...). Hence, this can
		 * also be considered as a testcase for Bug477342.
		 *
		 * Note: this test might pass even _without_ applying a fix (it
		 * 		 depends on the iteration order of es2ases.values() in
		 * 		 PivotMetamodelManager.dispose()).
		 */
		EPackage.Registry.INSTANCE.put(Bug477283APackage.eNS_URI, Bug477283APackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Bug477283BPackage.eNS_URI, Bug477283BPackage.eINSTANCE);
		TestOCL ocl = createOCL();
		String testFile =
				"import 'http://www.eclipse.org/ocl/Bug477283b'\n" +
						"import 'http://www.eclipse.org/ocl/Bug477283asub'\n" +
						"context b::B\n" +
						"def: x(a : asub::ASub) : Boolean = true\n";
		doLoadFromString(ocl, "string.ocl", testFile);
		// we test that the dispose code throws no IllegalStateException
		ocl.dispose();
	}
}
