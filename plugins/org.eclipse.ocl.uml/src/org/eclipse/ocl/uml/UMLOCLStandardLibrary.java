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
package org.eclipse.ocl.uml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.uml.internal.OCLStandardLibraryImpl;
import org.eclipse.ocl.utilities.PredefinedType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * <p>
 * An extensible implementation of the {@link OCLStandardLibrary} which provides the
 * OCL Standard Library predefined M1 types and the associated operations related to them.
 * </p>
 *
 * <p>
 * The UML-based OCL standard library represents a {@link Package} which owns all the predefined
 * OCL types defined by the specification. The library itself is built by the the internal internal
 * {@link OCLStandardLibraryImpl} singleton.
 * </p>
 *
 * @see OCLStandardLibraryImpl
 * @author Adolfo Sanchez-Barbudo Herrera (adolfosbh)
 * @since 2.0
 */
public class UMLOCLStandardLibrary implements OCLStandardLibrary<Classifier>{

	/**
	 * The OCL Standard library builder.
	 */
	private final OCLStandardLibraryImpl stdLibBuilder;

	public UMLOCLStandardLibrary() {
		super();
		resolveLibrary();
		stdLibBuilder = OCLStandardLibraryImpl.INSTANCE;
	}

	/**
	 * @since 5.23
	 */
	protected Set<Classifier> resolveLibrary() {
		// Bug 582625 eagerly initialize the whole library - invoke all oclIterators/Operations/Properties.
		// nb transitive type traversal needed to find the TupleType in the product() return.
		Set<Classifier> resolvedClassifiers = new HashSet<>();
		List<Type> ownedTypes = new ArrayList<>(OCLStandardLibraryImpl.stdlibPackage.getOwnedTypes());
		Collections.sort(ownedTypes, NamedElementComparator.INSTANCE);
		for (Type uType : ownedTypes) {
			resolveType(resolvedClassifiers, uType);
		}
		return resolvedClassifiers;
	}

	/**
	 * @since 5.23
	 */
	public static final class NamedElementComparator implements Comparator<NamedElement>
	{
		public static final NamedElementComparator INSTANCE = new NamedElementComparator();

		@Override
		public int compare(NamedElement o1, NamedElement o2) {
			if (o1 == o2) {
				return 0;		// Short circuit containment compare / independent searches
			}
			String n1 = o1.getName();
			String n2 = o2.getName();
			int comparison = safeCompareTo(n1, n2);
			if (comparison != 0) {
				return comparison;
			}
			if ((o1 instanceof org.eclipse.uml2.uml.Package) && (o2 instanceof org.eclipse.uml2.uml.Package)) {
				n1 = ((org.eclipse.uml2.uml.Package)o1).getURI();
				n2 = ((org.eclipse.uml2.uml.Package)o2).getURI();
				comparison = safeCompareTo(n1, n2);
				if (comparison != 0) {
					return comparison;
				}
			}
			EObject p1 = o1.eContainer();
			EObject p2 = o2.eContainer();
			if ((p1 instanceof NamedElement) && (p2 instanceof NamedElement)) {
				return compare((NamedElement)p1, (NamedElement)p2);
			}
			return comparison;
		}

		public static <T extends Comparable<T>> int safeCompareTo(T object, T otherObject) {
			if (object == null) {
				return otherObject == null ? 0 : 1;
			}
			else {
				return otherObject == null ? -1 : object.compareTo(otherObject);
			}
		}
	}

	/**
	 * @since 5.23
	 */
	@SuppressWarnings("unchecked")
	protected void resolveClassifier(Set<Classifier> resolvedClassifiers, Classifier uClassifier) {
		if ((uClassifier instanceof PredefinedType<?>) && resolvedClassifiers.add(uClassifier)) {
			System.out.println("Resolve " + uClassifier);
			List<Operation> oclOperations = new ArrayList<>(((PredefinedType<Operation>)uClassifier).oclOperations());
			Collections.sort(oclOperations, NamedElementComparator.INSTANCE);
			int i = 0;
			for (Operation uOperation : oclOperations) {
				System.out.println("\t " + i++ + " " + uOperation);
			}
			for (Operation uOperation : oclOperations) {
				resolveOperation(resolvedClassifiers, uOperation);
			}
			if (uClassifier instanceof CollectionType) {
				CollectionType collectionType = (CollectionType)uClassifier;
				for (Operation uOperation : collectionType.oclIterators()) {
					resolveOperation(resolvedClassifiers, uOperation);
				}
				resolveType(resolvedClassifiers, collectionType.getElementType());
			}
			else if (uClassifier instanceof MessageType) {
				for (Property uProperty : ((MessageType)uClassifier).oclProperties()) {
					resolveType(resolvedClassifiers, uProperty.getType());
				}
			}
			else if (uClassifier instanceof TupleType) {
				for (Property uProperty : ((TupleType)uClassifier).oclProperties()) {
					resolveType(resolvedClassifiers, uProperty.getType());
				}
			}
		}
	}

	/**
	 * @since 5.23
	 */
	protected void resolveType(Set<Classifier> resolvedClassifiers, Type uType) {
		if (uType instanceof Classifier) {
			resolveClassifier(resolvedClassifiers, (Classifier) uType);
		//	ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		//	if (eTypeParameter != null) {
		//		for (EGenericType eType : eTypeParameter.getEBounds()) {
		//			resolveEGenericType(resolvedClassifiers, eType);
		//		}
		//	}
		//	for (EGenericType eType : eGenericType.getETypeArguments()) {
		//		resolveEGenericType(resolvedClassifiers, eType);
		//	}
		}
	}

	/**
	 * @since 5.23
	 */
	protected void resolveOperation(Set<Classifier> resolvedClassifiers, Operation uOperation) {
		for (Parameter uParameter : uOperation.getOwnedParameters()) {
			resolveType(resolvedClassifiers, uParameter.getType());
		}
		resolveType(resolvedClassifiers, uOperation.getType());
	}

	@Override
	public Classifier getBag() {
		return stdLibBuilder.getBag();
	}

	@Override
	public Classifier getBoolean() {
		return stdLibBuilder.getBoolean();
	}

	@Override
	public Classifier getCollection() {
		return stdLibBuilder.getCollection();
	}

	@Override
	public Classifier getInteger() {
		return stdLibBuilder.getInteger();
	}

	/**
	 * @since 3.0
	 */
	@Override
	public Classifier getOclInvalid() {
		return stdLibBuilder.getOclInvalid();
	}

	@Override
	public Classifier getOclAny() {
		return stdLibBuilder.getOclAny();
	}

	@Override
	public Classifier getOclElement() {
		return stdLibBuilder.getOclElement();
	}

	@Override
	public Classifier getOclExpression() {
		return stdLibBuilder.getOclExpression();
	}

	@Override
	public Object getInvalid() {
		return stdLibBuilder.getInvalid();
	}

	@Override
	public Classifier getOclMessage() {
		return stdLibBuilder.getOclMessage();
	}

	@Override
	public Classifier getOclType() {
		return stdLibBuilder.getOclType();
	}

	@Override
	public Classifier getOclVoid() {
		return stdLibBuilder.getOclVoid();
	}

	@Override
	public Classifier getOrderedSet() {
		return stdLibBuilder.getOrderedSet();
	}

	@Override
	public Classifier getReal() {
		return stdLibBuilder.getReal();
	}

	@Override
	public Classifier getSequence() {
		return stdLibBuilder.getSequence();
	}

	@Override
	public Classifier getSet() {
		return stdLibBuilder.getSet();
	}

	@Override
	public Classifier getState() {
		return stdLibBuilder.getState();
	}

	@Override
	public Classifier getString() {
		return stdLibBuilder.getString();
	}

	@Override
	public Classifier getT() {
		return stdLibBuilder.getT();
	}

	@Override
	public Classifier getT2() {
		return stdLibBuilder.getT2();
	}

	@Override
	public Classifier getUnlimitedNatural() {
		return stdLibBuilder.getUnlimitedNatural();
	}

	/**
	 * @return the {@link Package} which contains the OCL predefined types.
	 */
	public Package getOCLStdLibPackage() {
		return OCLStandardLibraryImpl.stdlibPackage;
	}
}