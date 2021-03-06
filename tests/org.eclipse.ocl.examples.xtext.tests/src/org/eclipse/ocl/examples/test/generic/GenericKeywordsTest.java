/*******************************************************************************
 * Copyright (c) 2010, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Bugs 296409, 295166
 *******************************************************************************/

package org.eclipse.ocl.examples.test.generic;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.PivotTestSuite;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * Tests for usages of model features whose names coincide with "keywords"
 * defined as reserved words by the OCL specification.
 *
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("null")
public abstract class GenericKeywordsTest extends PivotTestSuite
{
	public static class MyOCL extends TestOCL
	{
		org.eclipse.ocl.pivot.Package smalltalk;
		org.eclipse.ocl.pivot.Class collection;
		org.eclipse.ocl.pivot.Class block;
		protected org.eclipse.ocl.pivot.Class elseClass;
		org.eclipse.ocl.pivot.Class clazz;
		org.eclipse.ocl.pivot.Class property;
		org.eclipse.ocl.pivot.Class constraint;

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name, @Nullable ResourceSet externalResourceSet) {
			super(testFileSystem, testPackageName, name, OCL.NO_PROJECTS, externalResourceSet);
			MetamodelManager metamodelManager = getMetamodelManager();
			StandardLibrary standardLibrary = metamodelManager.getStandardLibrary();

			// create a little test model for a Smalltalk-like collection class that
			// defines operations corresponding to OCL iterators
			smalltalk = PivotUtil.createPackage(org.eclipse.ocl.pivot.Package.class, ClassUtil.nonNullEMF(PivotPackage.Literals.PACKAGE), "Smalltalk", null, null);
			registerPackage(smalltalk, "st", "foo://smalltalk");

			org.eclipse.ocl.pivot.Class object = createOwnedClass(smalltalk, "Object", false);
			collection = createOwnedClass(smalltalk, "Collection", false);
			block = createOwnedClass(smalltalk, "Block", false);
			clazz = createOwnedClass(smalltalk, "Class", false);
			property = createOwnedClass(smalltalk, "Property", false);
			constraint = createOwnedClass(smalltalk, "Constraint", false);
			addSupertype(constraint, object);
			addSupertype(property, object);
			addSupertype(clazz, object);
			addSupertype(block, object);
			addSupertype(collection, object);

			org.eclipse.ocl.pivot.Enumeration visibilityKind = createOwnedEnumeration(smalltalk, "VisibilityKind");
			createOwnedLiteral(visibilityKind, "public");
			createOwnedLiteral(visibilityKind, "protected");
			createOwnedLiteral(visibilityKind, "package");
			createOwnedLiteral(visibilityKind, "private");

			createOwnedAttribute(clazz, "name", standardLibrary.getStringType());
			createOwnedAttribute(clazz, "visibility", visibilityKind);
			createOwnedReference(clazz, "package", object);
			createOwnedAttribute(property, "isUnique", standardLibrary.getBooleanType());
			createOwnedAttribute(property, "upper", standardLibrary.getIntegerType());
			createOwnedReference(constraint, "context", clazz);

			EList<String> names = new BasicEList<String>(1);
			EList<Type> types = new BasicEList<Type>(1);

			names.add("object");
			types.add(object);
			createOwnedOperation(block, "context",
				names, types, clazz, true);

			names.set(0, "block");
			types.set(0, block);
			createOwnedOperation(collection, "isUnique",
				names, types, standardLibrary.getBooleanType(), true);
			createOwnedOperation(collection, "select",
				names, types, collection, true);

			names.clear();
			types.clear();
			createOwnedOperation(collection, "isEmpty",
				names, types, standardLibrary.getBooleanType(), true);

			// create some qualified classifier and package names that need
			// escaping of one or more segments. Likewise state names
			org.eclipse.ocl.pivot.Package nested = PivotUtil.createOwnedPackage(smalltalk, "runtime");
			org.eclipse.ocl.pivot.Package contextPackage = PivotUtil.createOwnedPackage(nested, "context");

			createOwnedClass(contextPackage, "language", false);
			elseClass = createOwnedClass(contextPackage, "else", false);
			createOwnedOperation(elseClass, "doIt",
				null, null, null, false);
		}
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), null);
	}

	public void test_isUnique_162300() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.property, "self.isUnique implies self.upper > 1");
		ocl.assertInvariant(ocl.property, "isUnique implies self.upper > 1");
		ocl.dispose();
	}

	public void test_package_162300() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.clazz, "self._package.oclIsUndefined() implies Set{VisibilityKind::protected, VisibilityKind::_package}->excludes(self.visibility)");
		ocl.assertInvariant(ocl.clazz, "_package.oclIsUndefined() implies Set{VisibilityKind::protected, VisibilityKind::_package}->excludes(self.visibility)");
		ocl.assertInvariant(ocl.clazz, "_'package'.oclIsUndefined() implies Set{VisibilityKind::protected, VisibilityKind::_package}->excludes(self.visibility)");
		ocl.dispose();
	}

	public void test_context_162300() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.constraint, "self._context <> null implies _context.oclIsKindOf(Class)");
		ocl.assertInvariant(ocl.constraint, "_context <> null implies self._context.oclIsKindOf(Class)");
		ocl.assertInvariant(ocl.constraint, "_'con''te' 'x'\n't' <> null implies self._context.oclIsKindOf(Class)");
		ocl.dispose();
	}

	/*    public void test_isUniqueOperation_162300() {
		MyOCL ocl = createMyOCL();
        ocl.createVariableInEnvironment("aBlock", ocl.block);
        ocl.assertInvariant(ocl.collection, "self.isUnique(aBlock)");
        ocl.assertInvariant(ocl.collection, "isUnique(aBlock)");
		ocl.dispose();
    }

    public void test_selectOperation_162300() {
		MyOCL ocl = createMyOCL();
        ocl.createVariableInEnvironment("aBlock", ocl.block);
        ocl.assertInvariant(ocl.collection, "self.select(aBlock).isEmpty()");
        ocl.assertInvariant(ocl.collection, "select(aBlock).isEmpty()");
		ocl.dispose();
   } */

	public void test_contextOperation_162300() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.block, "self._context(self).name = 'Block'");
		ocl.assertInvariant(ocl.block, "_context(self).name = 'Block'");
		ocl.assertInvariant(ocl.block, "_'context'(self).name = 'Block'");
		ocl.dispose();
	}

	public void test_reservedPackageName_183362() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.block, "Smalltalk::runtime::_context::language.allInstances()->notEmpty()");
		ocl.dispose();
	}

	public void test_reservedPackageName_packageContext_183362() {
		MyOCL ocl = createOCL();
		ocl.createDocument("package Smalltalk::runtime::_context context language inv: true endpackage");
		ocl.dispose();
	}

	public void test_reservedClassName_183362() {
		MyOCL ocl = createOCL();
		ocl.assertInvariant(ocl.elseClass, "let e : _else = self in _else.allInstances()->forAll(oclIsKindOf(_else))");
		ocl.assertInvariant(ocl.elseClass, "self.oclIsKindOf(_context::_else)");
		ocl.dispose();
	}

	public void test_reservedClassName_operationContext_183362() {
		MyOCL ocl = createOCL();
		ocl.createDocument("package Smalltalk context runtime::_context::_else::doIt() : pre: true endpackage");
		ocl.createDocument("package Smalltalk::runtime::_context context _else::doIt() : pre: true endpackage");
		ocl.dispose();
	}

	public void test_unexpectedCharacter_283509() {
		MyOCL ocl = createOCL();
		try {
			ocl.createInvariant(ocl.property, "self = #self");
			fail("Missing exception");
		} catch (Exception e) {
			assertEquals("2:6 \"#\" unexpected character ignored", e.getLocalizedMessage());
		}
		ocl.dispose();
	}
}
