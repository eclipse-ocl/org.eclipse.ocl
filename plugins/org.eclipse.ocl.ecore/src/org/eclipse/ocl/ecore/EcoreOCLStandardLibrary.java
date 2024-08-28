/*******************************************************************************
 * Copyright (c) 2009, 2018 Open Canarias S.L. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     A. Sanchez-Barbudo Herrera - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.ocl.ecore.internal.OCLStandardLibraryImpl;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.utilities.PredefinedType;

/**
 * <p>
 * An extensible implementation of the {@link OCLStandardLibrary} which provides the
 * OCL Standard Library predefined M1 types and the associated operations related to them.
 * </p>
 *
 * <p>
 * The Ecore-based OCL Standard library represents an {@link EPackage} which owns all the predefined
 * OCL types defined by the specification. The library itself is built by the the internal internal
 * {@link OCLStandardLibraryImpl} singleton.
 * </p>
 *
 * @see OCLStandardLibraryImpl
 * @author Adolfo Sanchez-Barbudo Herrera (adolfosbh)
 * @since 1.3
 */
public class EcoreOCLStandardLibrary implements OCLStandardLibrary<EClassifier>{

	/**
	 * The OCL Standard library builder.
	 */
	private final OCLStandardLibraryImpl stdLibBuilder;

	public EcoreOCLStandardLibrary() {
		super();
		resolveLibrary();
		stdLibBuilder = OCLStandardLibraryImpl.INSTANCE;
	}

	/**
	 * @since 3.23
	 */
	protected Set<EClassifier> resolveLibrary() {
		// Bug 582625 eagerly initialize the whole library - invoke all oclIterators/Operations/Properties.
		// nb transitive type traversal needed to find the TupleType in the product() return.
		Set<EClassifier> resolvedEClassifiers = new HashSet<>();
		for (EClassifier eClassifier : OCLStandardLibraryImpl.stdlibPackage.getEClassifiers()) {
			resolveEClassifier(resolvedEClassifiers, eClassifier);
		}
		return resolvedEClassifiers;
	}

	/**
	 * @since 3.23
	 */
	@SuppressWarnings("unchecked")
	protected void resolveEClassifier(Set<EClassifier> resolvedEClassifiers, EClassifier eClassifier) {
		if ((eClassifier instanceof PredefinedType<?>) && resolvedEClassifiers.add(eClassifier)) {
		//	System.out.println("Resolve " + eClassifier);
			for (EOperation eOperation : ((PredefinedType<EOperation>)eClassifier).oclOperations()) {
				resolveEOperation(resolvedEClassifiers, eOperation);
			}
			if (eClassifier instanceof CollectionType) {
				CollectionType collectionType = (CollectionType)eClassifier;
				for (EOperation eOperation : collectionType.oclIterators()) {
					resolveEOperation(resolvedEClassifiers, eOperation);
				}
				resolveEClassifier(resolvedEClassifiers, collectionType.getElementType());
			}
			else if (eClassifier instanceof MessageType) {
				for (EStructuralFeature eStructuralFeature : ((MessageType)eClassifier).oclProperties()) {
					resolveEGenericType(resolvedEClassifiers, eStructuralFeature.getEGenericType());
				}
			}
			else if (eClassifier instanceof TupleType) {
				for (EStructuralFeature eStructuralFeature : ((TupleType)eClassifier).oclProperties()) {
					resolveEGenericType(resolvedEClassifiers, eStructuralFeature.getEGenericType());
				}
			}
		}
	}

	/**
	 * @since 3.23
	 */
	protected void resolveEGenericType(Set<EClassifier> resolvedEClassifiers, EGenericType eGenericType) {
		if (eGenericType != null) {
			resolveEClassifier(resolvedEClassifiers, eGenericType.getEClassifier());
			ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
			if (eTypeParameter != null) {
				for (EGenericType eType : eTypeParameter.getEBounds()) {
					resolveEGenericType(resolvedEClassifiers, eType);
				}
			}
			for (EGenericType eType : eGenericType.getETypeArguments()) {
				resolveEGenericType(resolvedEClassifiers, eType);
			}
		}
	}

	/**
	 * @since 3.23
	 */
	protected void resolveEOperation(Set<EClassifier> resolvedEClassifiers, EOperation eOperation) {
		for (EParameter eParameter : eOperation.getEParameters()) {
			resolveEGenericType(resolvedEClassifiers, eParameter.getEGenericType());
		}
		resolveEGenericType(resolvedEClassifiers, eOperation.getEGenericType());
	}

	@Override
	public EClassifier getBag() {
		return stdLibBuilder.getBag();
	}

	@Override
	public EClassifier getBoolean() {
		return stdLibBuilder.getBoolean();
	}

	@Override
	public EClassifier getCollection() {
		return stdLibBuilder.getCollection();
	}

	@Override
	public EClassifier getInteger() {
		return stdLibBuilder.getInteger();
	}

	/**
	 * @since 3.0
	 */
	@Override
	public EClassifier getOclInvalid() {
		return stdLibBuilder.getOclInvalid();
	}

	@Override
	public EClassifier getOclAny() {
		return stdLibBuilder.getOclAny();
	}

	@Override
	public EClassifier getOclElement() {
		return stdLibBuilder.getOclElement();
	}

	@Override
	public EClassifier getOclExpression() {
		return stdLibBuilder.getOclExpression();
	}

	@Override
	public Object getInvalid() {
		return stdLibBuilder.getInvalid();
	}

	@Override
	public EClassifier getOclMessage() {
		return stdLibBuilder.getOclMessage();
	}

	@Override
	public EClassifier getOclType() {
		return stdLibBuilder.getOclType();
	}

	@Override
	public EClassifier getOclVoid() {
		return stdLibBuilder.getOclVoid();
	}

	@Override
	public EClassifier getOrderedSet() {
		return stdLibBuilder.getOrderedSet();
	}

	@Override
	public EClassifier getReal() {
		return stdLibBuilder.getReal();
	}

	@Override
	public EClassifier getSequence() {
		return stdLibBuilder.getSequence();
	}

	@Override
	public EClassifier getSet() {
		return stdLibBuilder.getSet();
	}

	@Override
	public EClassifier getState() {
		return stdLibBuilder.getState();
	}

	@Override
	public EClassifier getString() {
		return stdLibBuilder.getString();
	}

	@Override
	public EClassifier getT() {
		return stdLibBuilder.getT();
	}

	@Override
	public EClassifier getT2() {
		return stdLibBuilder.getT2();
	}

	@Override
	public EClassifier getUnlimitedNatural() {
		return stdLibBuilder.getUnlimitedNatural();
	}

	/**
	 * @return the {@link EPackage} which contains the OCL predefined types.
	 */
	public EPackage getOCLStdLibPackage() {
		return OCLStandardLibraryImpl.stdlibPackage;
	}
}