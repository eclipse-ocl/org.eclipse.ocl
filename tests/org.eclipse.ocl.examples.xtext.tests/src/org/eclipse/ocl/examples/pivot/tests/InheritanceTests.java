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

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
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
				return OCLstdlib.create(libraryClone + PivotConstants.OCL_AS_FILE_EXTENSION);
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
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		try {
			CompleteInheritance oclAnyInheritance = standardLibrary.getInheritance(standardLibrary.getOclAnyType());
			PrimitiveType booleanType = standardLibrary.getBooleanType();
			CompleteInheritance booleanInheritance = standardLibrary.getInheritance(booleanType);
			assert booleanInheritance.getDepth() == 1;
			Iterator<InheritanceFragment> allSuperInheritances = booleanInheritance.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert allSuperInheritances.next().getBaseInheritance() == booleanInheritance;
			assert !allSuperInheritances.hasNext();
			Iterator<InheritanceFragment> depth0Inheritances = booleanInheritance.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert !depth0Inheritances.hasNext();
			Iterator<InheritanceFragment> depth1Inheritances = booleanInheritance.getSuperFragments(1).iterator();
			assert depth1Inheritances.next().getBaseInheritance() == booleanInheritance;
			assert !depth1Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_OclAny() {
		TestOCL ocl = createOCL();
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		try {
			AnyType oclAnyType = standardLibrary.getOclAnyType();
			CompleteInheritance oclAnyInheritance = standardLibrary.getInheritance(oclAnyType);
			assert oclAnyInheritance.getDepth() == 0;
			Iterator<InheritanceFragment> allSuperInheritances = oclAnyInheritance.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert !allSuperInheritances.hasNext();
			Iterator<InheritanceFragment> depth0Inheritances = oclAnyInheritance.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert !depth0Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_Set() {
		TestOCL ocl = createOCL();
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		try {
			CompleteInheritance oclAnyInheritance = standardLibrary.getInheritance(standardLibrary.getOclAnyType());
			//		InheritanceInheritance collectionInheritance = metamodelManager.getStandardLibrary().getInheritance(metamodelManager.getStandardLibrary().getCollectionType());
			SetType setType = standardLibrary.getSetType();
			CompleteInheritance setInheritance = standardLibrary.getInheritance(setType);
			assert setInheritance.getDepth() == 3;
			Iterator<InheritanceFragment> allSuperInheritances = setInheritance.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseInheritance() == oclAnyInheritance;
			//		assert allSuperInheritances.next().getBaseInheritance() == collectionInheritance;
			CompleteInheritance next = allSuperInheritances.next().getBaseInheritance();
			while (allSuperInheritances.hasNext()) {
				next = allSuperInheritances.next().getBaseInheritance();
			}
			assert next == setInheritance;
			assert !allSuperInheritances.hasNext();
			Iterator<InheritanceFragment> depth0Inheritances = setInheritance.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert !depth0Inheritances.hasNext();
			//		Iterator<InheritanceInheritance> depth1Inheritances = setInheritance.getSuperFragments(1).iterator();
			//		assert depth1Inheritances.next() == collectionInheritance;
			//		assert !depth1Inheritances.hasNext();
			Iterator<InheritanceFragment> depth3Inheritances = setInheritance.getSuperFragments(3).iterator();
			assert depth3Inheritances.next().getBaseInheritance() == setInheritance;
			assert !depth3Inheritances.hasNext();
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_IfExp() {
		TestOCL ocl = createOCL();
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		try {
			CompleteInheritance oclAnyInheritance = standardLibrary.getInheritance(standardLibrary.getOclAnyType());
			CompleteInheritance ifInheritance = standardLibrary.getInheritance(ClassUtil.nonNullState(standardLibrary.getASClass("IfExp")));
			Iterator<InheritanceFragment> allSuperInheritances = ifInheritance.getAllSuperFragments().iterator();
			assert allSuperInheritances.next().getBaseInheritance() == oclAnyInheritance;
			CompleteInheritance next = allSuperInheritances.next().getBaseInheritance();
			while (allSuperInheritances.hasNext()) {
				next = allSuperInheritances.next().getBaseInheritance();
			}
			assert next == ifInheritance;
			assert !allSuperInheritances.hasNext();
			Iterator<InheritanceFragment> depth0Inheritances = ifInheritance.getSuperFragments(0).iterator();
			assert depth0Inheritances.next().getBaseInheritance() == oclAnyInheritance;
			assert !depth0Inheritances.hasNext();
			Iterator<InheritanceFragment> depthNInheritances = ifInheritance.getSuperFragments(ifInheritance.getDepth()).iterator();
			assert depthNInheritances.next().getBaseInheritance() == ifInheritance;
			assert !depthNInheritances.hasNext();
			assert oclAnyInheritance.isSuperInheritanceOf(ifInheritance);
			assert !ifInheritance.isSuperInheritanceOf(oclAnyInheritance);
			CompleteInheritance oclExpressionInheritance = standardLibrary.getInheritance(ClassUtil.nonNullState(standardLibrary.getASClass("OCLExpression")));
			assert oclExpressionInheritance.isSuperInheritanceOf(ifInheritance);
			assert !ifInheritance.isSuperInheritanceOf(oclExpressionInheritance);
			CompleteInheritance loopExpInheritance = standardLibrary.getInheritance(ClassUtil.nonNullState(standardLibrary.getASClass("LoopExp")));
			assert !ifInheritance.isSuperInheritanceOf(loopExpInheritance);
			assert !loopExpInheritance.isSuperInheritanceOf(ifInheritance);
		} finally {
			ocl.dispose();
		}
	}

	public void test_Inheritance_UnlimitedNatural() {
		TestOCL ocl = createOCL();
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		try {
			CompleteInheritance oclAnyInheritance = standardLibrary.getInheritance(standardLibrary.getOclAnyType());
			//			DomainInheritance realTypeInheritance = standardLibrary.getInheritance(standardLibrary.getRealType());
			//			DomainInheritance integerTypeInheritance = standardLibrary.getInheritance(standardLibrary.getIntegerType());
			CompleteInheritance unlimitedNaturalTypeInheritance = standardLibrary.getInheritance(standardLibrary.getUnlimitedNaturalType());
			assertEquals(2, unlimitedNaturalTypeInheritance.getDepth());
			Iterator<InheritanceFragment> allSuperInheritances = unlimitedNaturalTypeInheritance.getAllSuperFragments().iterator();
			assertEquals(oclAnyInheritance, allSuperInheritances.next().getBaseInheritance());
			Iterator<InheritanceFragment> depth0Inheritances = unlimitedNaturalTypeInheritance.getSuperFragments(0).iterator();
			assertEquals(oclAnyInheritance, depth0Inheritances.next().getBaseInheritance());
			assert !depth0Inheritances.hasNext();
			//			Iterator<DomainFragment> depth2Inheritances = unlimitedNaturalTypeInheritance.getSuperFragments(2).iterator();
			//			assertEquals(realTypeInheritance, depth2Inheritances.next().getBaseInheritance());
			//			assert !depth2Inheritances.hasNext();
			//			Iterator<DomainFragment> depth3Inheritances = unlimitedNaturalTypeInheritance.getSuperFragments(3).iterator();
			//			assertEquals(integerTypeInheritance, depth3Inheritances.next().getBaseInheritance());
			//			assert !depth3Inheritances.hasNext();
			Iterator<InheritanceFragment> depth2Inheritances = unlimitedNaturalTypeInheritance.getSuperFragments(2).iterator();
			assertEquals(unlimitedNaturalTypeInheritance, depth2Inheritances.next().getBaseInheritance());
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
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			CompleteInheritance integerTypeInheritance = standardLibrary.getInheritance(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeInheritance.getDepth());
			try {
				standardLibrary.getOclComparableType().getSuperClasses().add(standardLibrary.getIntegerType());
				integerTypeInheritance.getDepth();
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
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			CompleteInheritance integerTypeInheritance = standardLibrary.getInheritance(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeInheritance.getDepth());
			try {
				standardLibrary.getRealType().getSuperClasses().add(standardLibrary.getStringType());
				assertEquals(3, standardLibrary.getInheritance(standardLibrary.getRealType()).getDepth());
				assertEquals(4, integerTypeInheritance.getDepth());
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
		StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
		standardLibrary.setDefaultStandardLibraryURI(installLibraryClone());
		try {
			CompleteInheritance integerTypeInheritance = standardLibrary.getInheritance(standardLibrary.getIntegerType());
			assertEquals(3, integerTypeInheritance.getDepth());
			try {
				standardLibrary.getRealType().getSuperClasses().clear();
				standardLibrary.getRealType().getSuperClasses().add(standardLibrary.getOclAnyType());
				assertEquals(2, integerTypeInheritance.getDepth());
				assertEquals(2, standardLibrary.getInheritance(standardLibrary.getIntegerType()).getDepth());
				assertEquals(1, standardLibrary.getInheritance(standardLibrary.getRealType()).getDepth());
			} finally {
				standardLibrary.getRealType().getSuperClasses().add(standardLibrary.getOclComparableType());
				standardLibrary.getRealType().getSuperClasses().add(standardLibrary.getOclSummableType());
			}
		} finally {
			ocl.dispose();
		}
	}
}
