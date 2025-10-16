/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.as2es;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterization;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialization;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * @since 1.23
 */
public class AS2EcoreTypeRefVisitor extends AbstractExtendingVisitor<EObject, AS2Ecore>
{
	/**
	 * @since 7.0
	 */
	protected final @NonNull CompleteModel completeModel;
	protected final @NonNull CompleteStandardLibrary standardLibrary;

	private boolean isRequired = false;
	private @Nullable Element asScope = null;

	public AS2EcoreTypeRefVisitor(@NonNull AS2Ecore context) {
		super(context);
		this.completeModel = context.getCompleteModel();
		this.standardLibrary = context.getStandardLibrary();
	}

	private <T extends EObject> @Nullable T getESObject(@NonNull Class<T> requiredClass, org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		Iterable<org.eclipse.ocl.pivot.Class> partialClasses = completeModel.getPartialClasses(pivotType);
		for (org.eclipse.ocl.pivot.Class type : partialClasses) {
			if (type instanceof PivotObjectImpl) {
				EObject esObject = ((PivotObjectImpl)type).getESObject();
				if ((esObject != null) && requiredClass.isAssignableFrom(esObject.getClass())) {
					@SuppressWarnings("unchecked") T castEsObject = (T)esObject;
					return castEsObject;
				}
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public void popScope(@Nullable Element savedASScope) {
		if ("expressions::LiteralExp(C)".equals(String.valueOf(savedASScope))) {
			getClass();
		}
		this.asScope = savedASScope;
	}

	/**
	 * @since 7.0
	 */
	public @Nullable Element pushScope(@NonNull Element asScope) {
		if ("expressions::LiteralExp(C)".equals(String.valueOf(asScope))) {
			getClass();
		}
		Element savedAsScope = this.asScope;
		this.asScope = asScope;
		return savedAsScope;
	}

	public EGenericType resolveEGenericType(org.eclipse.ocl.pivot.@NonNull Class type) {
		EObject eType = safeVisit(type);
		if (eType instanceof EGenericType) {
			return (EGenericType) eType;
		}
		else {
			EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
			eGenericType.setEClassifier((EClassifier) eType);
			TemplateSignature templateSignature = type.getOwnedSignature();
			if (templateSignature != null) {
				for (@NonNull TemplateParameter templateParameter : PivotUtil.getOwnedParameters(templateSignature)) {
					EObject eTypeParameter = safeVisit(templateParameter);
					if (eTypeParameter instanceof EGenericType) {
						eGenericType.getETypeArguments().add((EGenericType) eTypeParameter);
					}
					else {
						assert false;			// XXX
					}
				}
			}
			return eGenericType;
		}
	}

	@Override
	public EObject safeVisit(@Nullable Visitable v) {
		if (v instanceof Type) {
			v = completeModel.getPrimaryType((Type)v);
		}
		return (v == null) ? null : v.accept(this);
	}

	/**
	 * @since 7.0
	 */
	public EObject safeVisit(@Nullable Visitable v, boolean isRequired) {
		boolean savedIsRequired = isRequired;
		try {
			this.isRequired = isRequired;
			return safeVisit(v);
		}
		finally {
			this.isRequired = savedIsRequired;
		}
	}

	public <T extends EObject> void safeVisitAll(List<T> eObjects, List<? extends Element> pivotObjects) {
		for (Element pivotObject : pivotObjects) {
			@SuppressWarnings("unchecked")
			T eObject = (T) safeVisit(pivotObject);
			if (eObject != null) {
				eObjects.add(eObject);
			}
			// else error
		}
	}

	/**
	 * @since 7.0
	 */
	public <T extends EObject> void safeVisitAll(List<T> eObjects, List<? extends Element> pivotObjects, boolean isRequired) {
		boolean savedIsRequired = isRequired;
		try {
			this.isRequired = isRequired;
			safeVisitAll(eObjects, pivotObjects);
		}
		finally {
			this.isRequired = savedIsRequired;
		}
	}

	@Override
	public EClassifier visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for AS2Ecore TypeRef pass");
	}

	@Override
	public EObject visitAnyType(@NonNull AnyType pivotType) {
		EClassifier eClassifier = context.getCreated(EClassifier.class, pivotType);
		if (eClassifier != null) {
			return eClassifier;
		}
		else {
			return OCLstdlibPackage.Literals.OCL_ANY;
		}
	}

	@Override
	public EObject visitClass(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		if (pivotType.getOwnedBindings().size() == 0) {
			EClassifier eClassifier = context.getCreated(EClassifier.class, pivotType);
			if (eClassifier != null) {
				return eClassifier;
			}
			if (completeModel.isTypeServeable(pivotType)) {
				eClassifier = getESObject(EClassifier.class, pivotType);
				if (eClassifier != null) {
					return eClassifier;
				}
			}
			else {
				if (pivotType instanceof PivotObjectImpl) {
					EObject eTarget = ((PivotObjectImpl)pivotType).getESObject();
					if (eTarget != null) {
						return eTarget;
					}
				}
			}
			return null;	// FIXME may be null if not from Ecore
		}
		TemplateSpecialization templateSpecialization = TemplateSpecialization.getTemplateSpecialization(pivotType);
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		EObject rawType = safeVisit(PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)pivotType));
		eGenericType.setEClassifier((EClassifier) rawType);
		safeVisitAll(eGenericType.getETypeArguments(), templateSpecialization.getOwnedSubstitutions());
		return eGenericType;
	}

	@Override
	public EObject visitCollectionType(@NonNull CollectionType pivotType) {
		if (pivotType.getOwnedBindings().size() == 0) {
			EClassifier eClassifier1 = context.getCreated(EClassifier.class, pivotType);
			if (eClassifier1 != null) {
				return eClassifier1;
			}
			Iterable<org.eclipse.ocl.pivot.Class> partialClasses = completeModel.getPartialClasses(pivotType);
			for (org.eclipse.ocl.pivot.Class type : partialClasses) {
				if (type instanceof PivotObjectImpl) {
					EObject eTarget = ((PivotObjectImpl)type).getESObject();
					if (eTarget != null) {
						return eTarget;
					}
				}
			}
			return OCLstdlibPackage.eINSTANCE.getEClassifier(pivotType.getName());
		}
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		EObject eClassifier2 = safeVisit(PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)pivotType));
		eGenericType.setEClassifier((EClassifier) eClassifier2);
		TemplateSpecialization templateSpecialization = TemplateSpecialization.getTemplateSpecialization(pivotType);
	//	safeVisitAll(eGenericType.getETypeArguments(), templateSpecialization.getOwnedSubstitutions());
		List<@NonNull EAnnotation> nestedEAnnotations = null;
		for (Element pivotObject : templateSpecialization.getOwnedSubstitutions()) {
			EObject eObject = safeVisit(pivotObject);
			if (eObject instanceof EGenericType) {
				EGenericType nestedEGenericType = (EGenericType)eObject;
				eGenericType.getETypeArguments().add(nestedEGenericType);
			}
			else if (eObject instanceof EAnnotation) {
				EAnnotation nestedEAnnotation = (EAnnotation)eObject;
				EGenericType nestedEGenericType = (EGenericType)nestedEAnnotation.getReferences().get(0);
				eGenericType.getETypeArguments().add(nestedEGenericType);
				if (nestedEAnnotations == null) {
					nestedEAnnotations = new ArrayList<>();
				}
				nestedEAnnotations.add(nestedEAnnotation);
			}
			// else error
		}
		// FIXME supers
		boolean isNullFree = pivotType.isIsNullFree();
		IntegerValue lowerValue = pivotType.getLowerValue();
		UnlimitedNaturalValue upperValue = pivotType.getUpperValue();
		if ((isNullFree == PivotConstants.DEFAULT_IS_NULL_FREE) && lowerValue.equals(PivotConstants.DEFAULT_LOWER_BOUND) && upperValue.equals(PivotConstants.DEFAULT_UPPER_BOUND)) {
			return eGenericType;
		}
		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setSource(PivotConstants.COLLECTION_ANNOTATION_SOURCE);
		eAnnotation.getReferences().add(eGenericType);
		EMap<String, String> eDetails = eAnnotation.getDetails();
		if (isNullFree != PivotConstants.DEFAULT_IS_NULL_FREE) {
			eDetails.put(PivotConstants.COLLECTION_IS_NULL_FREE, Boolean.toString(isNullFree));
		}
		if (!lowerValue.equals(PivotConstants.DEFAULT_LOWER_BOUND)) {
			eDetails.put(PivotConstants.COLLECTION_LOWER, lowerValue.toString());
		}
		if (!upperValue.equals(PivotConstants.DEFAULT_UPPER_BOUND)) {
			eDetails.put(PivotConstants.COLLECTION_UPPER, upperValue.toString());
		}
		if (nestedEAnnotations != null) {
			eAnnotation.getEAnnotations().addAll(nestedEAnnotations);
		}
		return eAnnotation;
	}

	@Override
	public EObject visitInvalidType(@NonNull InvalidType pivotType) {
		EClassifier eClassifier = context.getCreated(EClassifier.class, pivotType);
		if (eClassifier != null) {
			return eClassifier;
		}
		else {
			return OCLstdlibPackage.Literals.OCL_INVALID;
		}
	}

	@Override
	public EObject visitMapType(@NonNull MapType mapType) {
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		EClassifier eClassifier = OCLstdlibPackage.Literals.MAP;
		eGenericType.setEClassifier(eClassifier);
		safeVisitAll(eGenericType.getETypeArguments(), mapType.getOwnedBindings());
		// FIXME bounds, supers
		return eGenericType;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public EObject visitNormalizedTemplateParameter(@NonNull NormalizedTemplateParameter pivotNormalizedTemplateParameter) {
		assert asScope != null;
		TemplateParameterization templateParameterization = TemplateParameterization.getTemplateParameterization(asScope);
		TemplateParameter asTemplateParameter = templateParameterization.get(pivotNormalizedTemplateParameter.getIndex());
		return context.getCreated(ETypeParameter.class, asTemplateParameter);
	}

	@Override
	public EObject visitPrimitiveType(@NonNull PrimitiveType pivotType) {
		EDataType eClassifier = context.getCreated(EDataType.class, pivotType);
		if (eClassifier != null) {
			return eClassifier;
		}
	//	EDataType eClassifier2 = getESObject(EDataType.class, pivotType);  -- too simple can give String rather than EString
	//	if (eClassifier2 != null) {
	//		if (!isRequired && (eClassifier2 == EcorePackage.Literals.EBOOLEAN)) {
	//			eClassifier2 = EcorePackage.Literals.EBOOLEAN_OBJECT;
	//		}
	//		context.putCreated(pivotType, eClassifier2);
	//		return eClassifier2;
	//	}
		String uri = context.getPrimitiveTypesUriPrefix();
		if (uri != null) {
			URI proxyURI = URI.createURI(uri + pivotType.getName());
			eClassifier = EcoreFactory.eINSTANCE.createEDataType();
			((InternalEObject) eClassifier).eSetProxyURI(proxyURI);
			context.putCreated(pivotType, eClassifier);
			return eClassifier;
		}
		CompleteClass completeClass = completeModel.getCompleteClass(pivotType);
		List<org.eclipse.ocl.pivot.Class> partialClasses = completeClass.getPartialClasses();
		for (org.eclipse.ocl.pivot.Class aType : partialClasses) {
			if (!(aType instanceof PrimitiveType)) {		// FIXME This loop appears to be unnecessary
				eClassifier = context.getCreated(EDataType.class, pivotType);
				if (eClassifier != null) {
					return eClassifier;
				}
			}
		}
		for (org.eclipse.ocl.pivot.Class aType : partialClasses) {
			if (aType == standardLibrary.getStringType()) {
				return EcorePackage.Literals.ESTRING;
			}
			else if (aType == standardLibrary.getBooleanType()) {
				return isRequired ? EcorePackage.Literals.EBOOLEAN : EcorePackage.Literals.EBOOLEAN_OBJECT;
			}
			else if (aType == standardLibrary.getIntegerType()) {
				return EcorePackage.Literals.EBIG_INTEGER;
			}
			else if (aType == standardLibrary.getRealType()) {
				return EcorePackage.Literals.EBIG_DECIMAL;
			}
			else if (aType == standardLibrary.getUnlimitedNaturalType()) {
				return EcorePackage.Literals.EBIG_INTEGER;
			}
		}
		throw new IllegalArgumentException("Unsupported primitive type '" + pivotType + "' in AS2Ecore TypeRef pass");
	}

	@Override
	public EObject visitTemplateBinding(@NonNull TemplateBinding object) {
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		return eGenericType;
	}

	@Override
	public EObject visitTemplateParameter(@NonNull TemplateParameter pivotType) {
		ETypeParameter eTypeParameter = context.getCreated(ETypeParameter.class, pivotType);
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		eGenericType.setETypeParameter(eTypeParameter);
		return eGenericType;
	}

	@Override
	public EObject visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution pivotTemplateParameterSubstitution) {
		Type actual = pivotTemplateParameterSubstitution.getActual();
		EObject actualType = safeVisit(actual);
		if ((actualType instanceof EGenericType) || (actualType instanceof EAnnotation)) {
			return actualType;
		}
		EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
		if (actualType instanceof EClassifier) {
			eGenericType.setEClassifier((EClassifier) actualType);
		}
		else if (actualType instanceof ETypeParameter) {
			eGenericType.setETypeParameter((ETypeParameter) actualType);
		}
		else if (actualType == null) {			// XXX wildcard
		//	eGenericType.setETypeParameter((ETypeParameter) actualType);
		}
		else {
			assert false;
		}
		return eGenericType;
	}

	/**
	 * @since 1.17
	 */
	@Override
	public EObject visitTupleType(@NonNull TupleType pivotType) {
		EClassifier eClassifier = context.getCreated(EClassifier.class, pivotType);
		if (eClassifier != null) {
			return eClassifier;
		}
		else {
			return OCLstdlibPackage.Literals.OCL_TUPLE;
		}
	}

	@Override
	public EObject visitVoidType(@NonNull VoidType pivotType) {
		EClassifier eClassifier = context.getCreated(EClassifier.class, pivotType);
		if (eClassifier != null) {
			return eClassifier;
		}
		else {
			return OCLstdlibPackage.Literals.OCL_VOID;
		}
	}
}
