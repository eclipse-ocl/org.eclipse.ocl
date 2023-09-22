package org.eclipse.ocl.pivot.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Normalizes an Ecore resource by
 * <br> alphabeticizing packages/classifiers/attributes/operations/constraints
 * <br> eliminating comments.
 */
public class EcoreNormalizer
{
	public void normalize(@NonNull Resource newResource) {
		List</*@NonNull*/ List</*@NonNull*/ ? extends EObject>> listOfLists = new ArrayList<>();
		List<@NonNull EObject> removals = new ArrayList<>();
		for (EObject eObject : new TreeIterable(newResource)) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				listOfLists.add(ePackage.getESubpackages());
				listOfLists.add(ePackage.getEClassifiers());
			}
			else if (eObject instanceof EEnum) {
				EEnum eEnum = (EEnum) eObject;
				listOfLists.add(eEnum.getELiterals());
			}
//			else if (eObject instanceof EEnumLiteral) {
//				EEnumLiteral eEnumLiteral = (EEnumLiteral) eObject;
//				eEnumLiteral.setValue(0);
//			}
			else if (eObject instanceof EClass) {
				EClass eClass = (EClass) eObject;
			//	listOfLists.add(eClass.getEGenericSuperTypes());
				listOfLists.add(eClass.getEStructuralFeatures());
				listOfLists.add(eClass.getEOperations());
				EList<EClass> eSuperTypes = eClass.getESuperTypes();
				for (EClass eSuperClass : eSuperTypes) {
					if ("OclElement".equals(eSuperClass.getName())) {
						eSuperTypes.remove(eSuperClass);			// XXX
						break;
					}
				}
			}
			else if (eObject instanceof EOperation) {
				EOperation eOperation = (EOperation) eObject;
//				if (EcoreUtil.isInvariant(eOperation) && eOperation.getName().startsWith("validate")) {
//					eOperation.setName(eOperation.getName().substring(8));
//				}
		//		if (EcoreUtil.isInvariant(eOperation)) {
		//			removals.add(eOperation);
		//		}
			}
			else if (eObject instanceof EReference) {
				@SuppressWarnings("unused") EReference eReference = (EReference) eObject;
//				eReference.setResolveProxies(true);
			}
			if (eObject instanceof EModelElement) {
				EModelElement eModelElement = (EModelElement) eObject;
				EAnnotation eAnnotation; // = eModelElement.getEAnnotation(GenModelPackage.eNS_URI); // "http://www.eclipse.org/emf/2002/GenModel");
			//	if (eAnnotation != null) {
			//		removals.add(eAnnotation);
			//	}
				eAnnotation = eModelElement.getEAnnotation("http://www.omg.org/ocl");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("http://schema.omg.org/spec/MOF/2.0/emof.xml#Property.oppositeRoleName");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
			/*	eAnnotation = eModelElement.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("duplicates");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("redefines");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				}
				eAnnotation = eModelElement.getEAnnotation("subsets");
				if (eAnnotation != null) {
					removals.add(eAnnotation);
				} */
			}
		}
		for (EObject removal : removals) {
			((List<?>)removal.eContainer().eGet(removal.eContainingFeature())).remove(removal);
		}
		for (List<? extends EObject> list : listOfLists) {
			@SuppressWarnings("null")
			List<@NonNull ? extends EObject> castList = (List<@NonNull ? extends EObject>)list;
			sortList(castList);
		}
	}

	protected <T extends EObject> void sortList(List<@NonNull T> list) {
		List<@NonNull T> newList = new ArrayList<>(list);
		Collections.sort(newList, new Comparator<@NonNull T>()
		{
			@Override
			public int compare(@NonNull T o1, @NonNull T o2) {
				EClass e1 = o1.eClass();
				EClass e2 = o2.eClass();
				if (e1 != e2) {
					if (EcorePackage.Literals.EATTRIBUTE.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EATTRIBUTE.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.EENUM.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EENUM.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.EDATA_TYPE.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.EDATA_TYPE.isSuperTypeOf(e2)) {
						return 1;
					}
					if (EcorePackage.Literals.ECLASS.isSuperTypeOf(e1)) {
						return -1;
					}
					else if (EcorePackage.Literals.ECLASS.isSuperTypeOf(e2)) {
						return 1;
					}
				}
				if ((o1 instanceof ENamedElement) && (o2 instanceof ENamedElement)) {
					String n1 = ((ENamedElement)o1).getName();
					String n2 = ((ENamedElement)o2).getName();
					return n1.compareTo(n2);
				}
				else {
					return System.identityHashCode(o2) - System.identityHashCode(o1);
				}
			}
		});
		list.clear();
		list.addAll(newList);
	}
}
