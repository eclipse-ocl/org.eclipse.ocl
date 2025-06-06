/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.tests.pivot.tests;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.junit.After;
import org.junit.Before;

/**
 * Tests for OclAny operations.
 */
public class InheritanceTests extends PivotTestSuite
{
	public InheritanceTests() {
		super(false);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	public @NonNull String installLibraryClone() {
		String libraryClone = "http://www.eclipse.org/ocl/2015/LibraryClone";
		StandardLibraryContribution mutableLibrary = new OCLstdlib.Loader() {
			@Override
			public @NonNull Resource getResource() {
				return OCLstdlib.create(libraryClone + PivotConstants.DOT_OCL_AS_FILE_EXTENSION, libraryClone);
			}
		};
		StandardLibraryContribution.REGISTRY.put(libraryClone, mutableLibrary );
		return libraryClone;
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_Inheritance_Boolean() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		try {
			FlatClass oclAnyFlatClass = standardLibrary.getFlatClass(standardLibrary.getOclAnyType());
			PrimitiveType booleanType = standardLibrary.getBooleanType();
			FlatClass booleanFlatClass = standardLibrary.getFlatClass(booleanType);
			assert booleanFlatClass.getDepth() == 1;
			Iterator<@NonNull FlatFragment> allSuperInheritances = booleanFlatClass.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert allSuperInheritances.next().getBaseFlatClass() == booleanFlatClass;
			assert !allSuperInheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth0Inheritances = booleanFlatClass.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert !depth0Inheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth1Inheritances = booleanFlatClass.getSuperFragments(1).iterator();
			assert depth1Inheritances.next().getBaseFlatClass() == booleanFlatClass;
			assert !depth1Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_OclAny() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		try {
			AnyType oclAnyType = standardLibrary.getOclAnyType();
			FlatClass oclAnyFlatClass = standardLibrary.getFlatClass(oclAnyType);
			assert oclAnyFlatClass.getDepth() == 0;
			Iterator<@NonNull FlatFragment> allSuperInheritances = oclAnyFlatClass.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert !allSuperInheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth0Inheritances = oclAnyFlatClass.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert !depth0Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_Set() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		try {
			FlatClass oclAnyFlatClass = standardLibrary.getFlatClass(standardLibrary.getOclAnyType());
			//		InheritanceInheritance collectionInheritance = metamodelManager.getStandardLibrary().getInheritance(metamodelManager.getStandardLibrary().getCollectionType());
			SetType setType = standardLibrary.getSetType();
			FlatClass setFlatClass = standardLibrary.getFlatClass(setType);
			assert setFlatClass.getDepth() == 3;
			Iterator<@NonNull FlatFragment> allSuperInheritances = setFlatClass.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			//		assert allSuperInheritances.next().getBaseInheritance() == collectionInheritance;
			FlatClass next = allSuperInheritances.next().getBaseFlatClass();
			while (allSuperInheritances.hasNext()) {
				next = allSuperInheritances.next().getBaseFlatClass();
			}
			assert next == setFlatClass;
			assert !allSuperInheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth0Inheritances = setFlatClass.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert !depth0Inheritances.hasNext();
			//		Iterator<InheritanceInheritance> depth1Inheritances = setInheritance.getSuperFragments(1).iterator();
			//		assert depth1Inheritances.next() == collectionInheritance.getFlatClass();
			//		assert !depth1Inheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth3Inheritances = setFlatClass.getSuperFragments(3).iterator();
			assert depth3Inheritances.next().getBaseFlatClass() == setFlatClass;
			assert !depth3Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_IfExp() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		try {
			FlatClass oclAnyFlatClass = standardLibrary.getFlatClass(standardLibrary.getOclAnyType());
			FlatClass ifFlatClass = standardLibrary.getFlatClass(ClassUtil.requireNonNull(standardLibrary.getASClass("IfExp")));
			Iterator<@NonNull FlatFragment> allSuperInheritances = ifFlatClass.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			FlatClass next = allSuperInheritances.next().getBaseFlatClass();
			while (allSuperInheritances.hasNext()) {
				next = allSuperInheritances.next().getBaseFlatClass();
			}
			assert next == ifFlatClass;
			assert !allSuperInheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth0Inheritances = ifFlatClass.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseFlatClass() == oclAnyFlatClass;
			assert !depth0Inheritances.hasNext();
			Iterator<@NonNull FlatFragment> depthNInheritances = ifFlatClass.getSuperFragments(ifFlatClass.getDepth()).iterator();
			assert depthNInheritances.next().getBaseFlatClass() == ifFlatClass;
			assert !depthNInheritances.hasNext();
			assert oclAnyFlatClass.isSuperFlatClassOf(ifFlatClass);
			assert !ifFlatClass.isSuperFlatClassOf(oclAnyFlatClass);
			FlatClass oclExpressionFlatClass = standardLibrary.getFlatClass(ClassUtil.requireNonNull(standardLibrary.getASClass("OCLExpression")));
			assert oclExpressionFlatClass.isSuperFlatClassOf(ifFlatClass);
			assert !ifFlatClass.isSuperFlatClassOf(oclExpressionFlatClass);
			FlatClass loopExpFlatClass = standardLibrary.getFlatClass(ClassUtil.requireNonNull(standardLibrary.getASClass("LoopExp")));
			assert !ifFlatClass.isSuperFlatClassOf(loopExpFlatClass);
			assert !loopExpFlatClass.isSuperFlatClassOf(ifFlatClass);
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_UnlimitedNatural() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		try {
			FlatClass oclAnyFlatClass = standardLibrary.getFlatClass(standardLibrary.getOclAnyType());
			//			DomainInheritance realTypeInheritance = standardLibrary.getInheritance(standardLibrary.getRealType());
			//			DomainInheritance integerTypeInheritance = standardLibrary.getInheritance(standardLibrary.getIntegerType());
			FlatClass unlimitedNaturalTypeFlatClass = standardLibrary.getFlatClass(standardLibrary.getUnlimitedNaturalType());
			assertEquals(2, unlimitedNaturalTypeFlatClass.getDepth());
			Iterator<@NonNull FlatFragment> allSuperInheritances = unlimitedNaturalTypeFlatClass.getAllSuperFragments().iterator();
			assertEquals(oclAnyFlatClass, allSuperInheritances.next().getBaseFlatClass());
			Iterator<@NonNull FlatFragment> depth0Inheritances = unlimitedNaturalTypeFlatClass.getSuperFragments(0).iterator();
			assertEquals(oclAnyFlatClass, depth0Inheritances.next().getBaseFlatClass());
			assert !depth0Inheritances.hasNext();
			//			Iterator<DomainFragment> depth2Inheritances = unlimitedNaturalTypeFlatClass.getSuperFragments(2).iterator();
			//			assertEquals(realTypeInheritance, depth2Inheritances.next().getBaseInheritance());
			//			assert !depth2Inheritances.hasNext();
			//			Iterator<DomainFragment> depth3Inheritances = unlimitedNaturalTypeFlatClass.getSuperFragments(3).iterator();
			//			assertEquals(integerTypeInheritance, depth3Inheritances.next().getBaseInheritance());
			//			assert !depth3Inheritances.hasNext();
			Iterator<@NonNull FlatFragment> depth2Inheritances = unlimitedNaturalTypeFlatClass.getSuperFragments(2).iterator();
			assertEquals(unlimitedNaturalTypeFlatClass, depth2Inheritances.next().getBaseFlatClass());
			assert !depth2Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	/**
	 * Check that an inheritance loop is diagnosed.
	 */
	public void test_Inheritance_Loop() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			FlatClass integerTypeFlatClass = standardLibrary.getFlatClass(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeFlatClass.getDepth());
			try {
				standardLibrary.getOclComparableType().getSuperClasses().add(standardLibrary.getIntegerType());
				integerTypeFlatClass.getDepth();
				fail("Missing IllegalStateException");
			} catch (IllegalStateException e) {
				// FIXME validate body
			} finally {
				standardLibrary.getOclComparableType().getSuperClasses().remove(standardLibrary.getIntegerType());
			}
		} finally {
			ocl.dispose();
		}
	}

	/**
	 * Check that addition of a supertype invalidates cached inheritances.
	 */
	public void test_Inheritance_Addition() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			FlatClass integerTypeFlatClass = standardLibrary.getFlatClass(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeFlatClass.getDepth());
			try {
				standardLibrary.getRealType().getSuperClasses().add(standardLibrary.getStringType());
				assertEquals(3, standardLibrary.getFlatClass(standardLibrary.getRealType()).getDepth());
				assertEquals(4, integerTypeFlatClass.getDepth());
			} finally {
				standardLibrary.getRealType().getSuperClasses().remove(standardLibrary.getStringType());
			}
		} finally {
			ocl.dispose();
		}
	}


	/**
	 * Check that removal of a supertype invalidates cached inheritances.
	 */
	public void test_Inheritance_Removal() {
		TestOCL ocl = createOCL();
		CompleteStandardLibrary standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			FlatClass integerTypeFlatClass = standardLibrary.getFlatClass(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeFlatClass.getDepth());
			List<org.eclipse.ocl.pivot.Class> superClasses = standardLibrary.getRealType().getSuperClasses();
			try {
				superClasses.clear();
				superClasses.add(standardLibrary.getOclAnyType());
				assertEquals(2, integerTypeFlatClass.getDepth());
				assertEquals(2, standardLibrary.getFlatClass(standardLibrary.getIntegerType()).getDepth());
				assertEquals(1, standardLibrary.getFlatClass(standardLibrary.getRealType()).getDepth());
			} finally {
				superClasses.add(standardLibrary.getOclComparableType());
				superClasses.add(standardLibrary.getOclSummableType());
			}
		} finally {
			ocl.dispose();
		}
	}
}
