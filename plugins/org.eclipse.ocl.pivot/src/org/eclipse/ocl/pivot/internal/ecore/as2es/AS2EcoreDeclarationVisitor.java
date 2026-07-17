/*******************************************************************************
 * Copyright (c) 2010, 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.as2es;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.xmi.impl.EMOFExtendedMetaData;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EntryType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.ecore.annotations.EAnnotationConverter;
import org.eclipse.ocl.pivot.internal.ecore.annotations.OCL_Import_EAnnotationConverter;
import org.eclipse.ocl.pivot.internal.ecore.annotations.OCL_Pivot_Operation_EAnnotationConverter;
import org.eclipse.ocl.pivot.internal.library.JavaCompareToOperation;
import org.eclipse.ocl.pivot.internal.utilities.OppositePropertyDetails;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.OrderedSet;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class AS2EcoreDeclarationVisitor
extends AbstractExtendingVisitor<Object, AS2Ecore>
{
	/**
	 * @since 7.0
	 */
	public static final @NonNull CopyOperationsFilter copyOperationsFilter = new CopyOperationsFilter();
	public static final @NonNull DuplicateConstraintsFilter duplicateConstraintsFilter = new DuplicateConstraintsFilter();
	public static final @NonNull DuplicateOperationsFilter duplicateOperationsFilter = new DuplicateOperationsFilter();
	public static final @NonNull DuplicatePropertiesFilter duplicatePropertiesFilter = new DuplicatePropertiesFilter();
	public static final @NonNull NonDuplicateConstraintsFilter nonDuplicateConstraintsFilter = new NonDuplicateConstraintsFilter();
	public static final @NonNull NonDuplicateOperationsFilter nonDuplicateOperationsFilter = new NonDuplicateOperationsFilter();
	public static final @NonNull NonDuplicatePropertiesFilter nonDuplicatePropertiesFilter = new NonDuplicatePropertiesFilter();

	/**
	 * @since 7.0
	 */
	protected static class CopyOperationsFilter implements Predicate<Operation>
	{
		@Override
		public boolean apply(@Nullable Operation anOperation) {
			if (anOperation == null) {
				return false;
			}
			if (anOperation.getImplementation() instanceof JavaCompareToOperation) {
				return false;
			}
			if (anOperation.getRedefinedOperations().size() == 0) {
				return true;
			}
			if ("containingActivity".equals(anOperation.getName()) && "ActivityNode".equals(anOperation.getOwningClass().getName())) {
				return true;		// FIXME Bug 405061 workaround
			}
			return false;
			//			return (anOperation != null) && (anOperation.getRedefinedOperation().size() == 0);
		}
	}

	protected static class DuplicateConstraintsFilter implements Predicate<Constraint>
	{
		@Override
		public boolean apply(@Nullable Constraint aConstraint) {
			if (aConstraint == null) {
				return false;
			}
			if (aConstraint.getRedefinedConstraints().size() == 0) {
				return false;
			}
			return true;
		}
	}

	protected static class DuplicateOperationsFilter implements Predicate<Operation>
	{
		@Override
		public boolean apply(@Nullable Operation anOperation) {
			if (anOperation == null) {
				return false;
			}
			if (anOperation.getRedefinedOperations().size() == 0) {
				return false;
			}
			if ("containingActivity".equals(anOperation.getName()) && "ActivityNode".equals(anOperation.getOwningClass().getName())) {
				return false;		// FIXME Bug 405061 workaround
			}
			return true;
			//			return (anOperation != null) && (anOperation.getRedefinedOperation().size() != 0);
		}
	}

	protected static class DuplicatePropertiesFilter implements Predicate<Property>
	{
		@Override
		public boolean apply(@Nullable Property aProperty) {
			if (aProperty == null) {
				return false;
			}
			if (aProperty.getRedefinedProperties().size() == 0) {
				return false;
			}
			return Objects.equals(aProperty.getName(), aProperty.getRedefinedProperties().get(0).getName());
		}
	}

	protected static class NonDuplicateConstraintsFilter implements Predicate<Constraint>
	{
		@Override
		public boolean apply(@Nullable Constraint aConstraint) {
			if (aConstraint == null) {
				return false;
			}
			if (aConstraint.getRedefinedConstraints().size() == 0) {
				return true;
			}
			return false;
		}
	}

	protected static class NonDuplicateOperationsFilter implements Predicate<Operation>
	{
		@Override
		public boolean apply(@Nullable Operation anOperation) {
			if (anOperation == null) {
				return false;
			}
			if (anOperation.getRedefinedOperations().size() == 0) {
				return true;
			}
			if ("containingActivity".equals(anOperation.getName()) && "ActivityNode".equals(anOperation.getOwningClass().getName())) {
				return true;		// FIXME Bug 405061 workaround
			}
			return false;
			//			return (anOperation != null) && (anOperation.getRedefinedOperation().size() == 0);
		}
	}

	protected static class NonDuplicatePropertiesFilter implements Predicate<Property>
	{
		@Override
		public boolean apply(@Nullable Property aProperty) {
			if (aProperty == null) {
				return false;
			}
			if (aProperty.getRedefinedProperties().size() == 0) {
				return true;
			}
			return !Objects.equals(aProperty.getName(), aProperty.getRedefinedProperties().get(0).getName());
			//			return (aProperty != null) && (aProperty.getRedefinedProperty().size() == 0);
		}
	}

	private static @NonNull String getEOperationName(@NonNull Operation asOperation) {
		String name = PivotUtil.getName(asOperation);
		assert !"inActivity".equals(name);				// Debugging	// FIXME Bug 420330 workaround
		if ("containingActivity".equals(name)) {		// FIXME Bug 405061 workaround
			EObject eContainer = asOperation.eContainer();
			if ((eContainer instanceof Type) && "ActivityNode".equals(((Type)eContainer).getName())) {
				return "ActivityNode_" + name;
			}
		}
		if (asOperation instanceof Iteration) {
			return name + ((Iteration)asOperation).getOwnedIterators().size();
		}
		return name;
	}

	private static @NonNull String getEStructuralFeatureName(@NonNull Property asProperty) {
		String name = PivotUtil.getName(asProperty);
		assert !"containingActivity".equals(name);		// Debugging	// FIXME Bug 405061 workaround
		if ("inActivity".equals(name)) {				// FIXME Bug 420330 workaround
			EObject eContainer = asProperty.eContainer();
			if ((eContainer instanceof Type) && "StructuredActivityNode".equals(((Type)eContainer).getName())) {
				name = "activity";
			}
		}
		return name;
	}

	private static @NonNull String wellFormedIdentifier(@NonNull String name) {
		if (name.length() == 0) {
			return name;
		}
		boolean isOk = Character.isJavaIdentifierStart(name.charAt(0));
		for (int i = 1; isOk && (i < name.length()); ++i) {
			if (!Character.isJavaIdentifierPart(name.charAt(i))) {
				isOk = false;
			}
		}
		if (isOk) {
			return name;
		}
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < name.length(); ++i) {
			char ch = name.charAt(i);
			if (i == 0 ? Character.isJavaIdentifierStart(ch) : Character.isJavaIdentifierPart(ch)) {
				s.append(ch);
			}
			else {
				s.append("_" + Integer.toString(ch));
			}
		}
		return s.toString();
	}

	protected final @NonNull DelegateInstaller delegateInstaller;

	public AS2EcoreDeclarationVisitor(@NonNull AS2Ecore context) {
		super(context);
		this.delegateInstaller = context.getDelegateInstaller();
	}

	protected void copyClassifier(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		copyNamedElement(eClassifier, pivotType);
		@SuppressWarnings("null")@NonNull List<ETypeParameter> eTypeParameters = eClassifier.getETypeParameters();
		copyTemplateParameters(eTypeParameters, pivotType);
		if (pivotType.eIsSet(PivotPackage.Literals.CLASS__INSTANCE_CLASS_NAME)) {
			eClassifier.setInstanceClassName(pivotType.getInstanceClassName());
		}
		else {
			eClassifier.eUnset(EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME);
		}
		//		visitAll(eClassifier.getETypeParameters(), pivotType.getTypeParameters());
		delegateInstaller.installDelegates(eClassifier, pivotType);
		for (Constraint pivotInvariant : pivotType.getOwnedInvariants()) {
			if (!pivotInvariant.isIsCallable()) {
				safeVisit(pivotInvariant);		// Results are inserted directly
			}
		}
	}

	/**
	 * @since 7.0
	 */
	protected @Nullable EStringToStringMapEntryImpl copyConstraintDetail(@NonNull EModelElement eModelElement, @NonNull Constraint pivotConstraint) {
		EStructuralFeature eContainingFeature = pivotConstraint.eContainingFeature();
		EStringToStringMapEntryImpl eDetail = delegateInstaller.createConstraintDelegateDetail(eModelElement, pivotConstraint, context.getEcoreURI());
		if (eDetail != null) {
			EAnnotation eAnnotation = (EAnnotation)eDetail.eContainer();
			assert eAnnotation != null;
			if (eModelElement instanceof EOperation) {
				if (eContainingFeature != PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
					AS2Ecore.copyAnnotationComments(eAnnotation, pivotConstraint);			// XXX eDetail
				}
				assert (pivotConstraint.getESObject() == null) || (pivotConstraint.getESObject().eClass() == eModelElement.eClass());
				((ElementImpl)pivotConstraint).setESObject(eModelElement);
			}
			else {
				AS2Ecore.copyCommentsAndDocumentation(eAnnotation, pivotConstraint);	// XXX eDetail
				assert (pivotConstraint.getESObject() == null) || (pivotConstraint.getESObject().eClass() == eDetail.eClass());
				((ElementImpl)pivotConstraint).setESObject(eDetail);
			}
		}
		return eDetail;
	}

	protected void copyDataTypeOrEnum(@NonNull EDataType eDataType, @NonNull DataType pivotDataType) {
		copyClassifier(eDataType, pivotDataType);
		eDataType.setSerializable(pivotDataType.isIsSerializable());
	}

	protected void copyDetails(@NonNull EAnnotation eAnnotation, @NonNull Annotation pivotAnnotation) {
		copyModelElement(eAnnotation, pivotAnnotation);
		@SuppressWarnings("null")@NonNull List<EAnnotation> eAnnotations = eAnnotation.getEAnnotations();
		safeVisitAll(eAnnotations, pivotAnnotation.getOwnedAnnotations());
		EAnnotationConverter eAnnotationConverter = EAnnotationConverter.getEAnnotationConverter(pivotAnnotation.getName());
		for (Detail asDetail : pivotAnnotation.getOwnedDetails()) {
			assert asDetail != null;
			eAnnotationConverter.convertDetail(asDetail, eAnnotation);
		}
	}

	protected void copyModelElement(@NonNull EModelElement eModelElement, @NonNull Element pivotModelElement) {
		context.putCreated(pivotModelElement, eModelElement);
		safeVisitAll(ClassUtil.requireNonNull(eModelElement.getEAnnotations()), pivotModelElement.getOwnedAnnotations());
		AS2Ecore.copyCommentsAndDocumentation(eModelElement, pivotModelElement);
	}

	protected void copyNamedElement(@NonNull ENamedElement eNamedElement, @NonNull NamedElement pivotNamedElement) {
		copyModelElement(eNamedElement, pivotNamedElement);
		String name;
		String implementationClassName = null;
		if (pivotNamedElement instanceof Operation) {
			Operation asOperation = (Operation)pivotNamedElement;
			name = getEOperationName(asOperation);
			implementationClassName = asOperation.getImplementationClass();
		}
		else if (pivotNamedElement instanceof Property) {
			Property asProperty = (Property)pivotNamedElement;
			name = getEStructuralFeatureName(asProperty);
			implementationClassName = asProperty.getImplementationClass();
		}
		else {
			name = pivotNamedElement.getName();
		}
		if (name != null) {
			String validName = wellFormedIdentifier(name);
			if (!name.equals(validName)) {
				NameUtil.setOriginalName(eNamedElement, name);
				name = validName;
			}
		}
		eNamedElement.setName(name);
		EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eNamedElement);
		if (implementationClassName != null) {
			if (eAnnotation == null) {
				eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(OCLCommon.OCL_DELEGATE_URI_PIVOT);
				eNamedElement.getEAnnotations().add(eAnnotation);
			}
			EMap<String, String> details = eAnnotation.getDetails();
			details.put(PivotConstants.CLASS_DETAIL, implementationClassName);
		}
		else {
			if (eAnnotation != null) {
				EMap<String, String> details = eAnnotation.getDetails();
				details.removeKey(PivotConstants.CLASS_DETAIL);
				if (details.isEmpty()) {
					eNamedElement.getEAnnotations().remove(eAnnotation);
				}
			}
		}
	}

	/**
	 * @since 7.0
	 */
	protected void copyOperations(@NonNull EDataType eDataType, @NonNull DataType pivotDataType) {
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Operation> nonDuplicateOperations = Iterables.filter(pivotDataType.getOwnedOperations(), copyOperationsFilter);
		if (!Iterables.isEmpty(nonDuplicateOperations)) {
			EAnnotation eCoClass = getCoEClass(eDataType);
			@SuppressWarnings("null")@NonNull List<EObject> eOperations = eCoClass.getContents();
			safeVisitAll(eOperations, nonDuplicateOperations);
		}
	}

	/**
	 * @since 7.0
	 */
	protected void copyProperties(@NonNull EDataType eDataType, @NonNull DataType pivotDataType) {
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> nonDuplicateProperties = Iterables.filter(pivotDataType.getOwnedProperties(), nonDuplicatePropertiesFilter);
		if (!Iterables.isEmpty(nonDuplicateProperties)) {
			EAnnotation eCoClass = getCoEClass(eDataType);
			@SuppressWarnings("null")@NonNull List<EObject> eProperties = eCoClass.getContents();
			safeVisitAll(eProperties, nonDuplicateProperties);
		}
	}

	/**
	 * @since 7.0
	 */
	protected void copyTemplateParameters(@NonNull List<ETypeParameter> eTypeParameters, @NonNull TemplateableElement pivotElement) {
		Iterable<@NonNull TemplateParameter> asTemplateParameters = pivotElement.basicGetOwnedTemplateParameters();
		if (asTemplateParameters != null) {
			safeVisitAll(eTypeParameters, asTemplateParameters);
		}
	}

	protected void copyTypedElement(@NonNull ETypedElement eTypedElement, @NonNull TypedElement pivotTypedElement) {
		copyNamedElement(eTypedElement, pivotTypedElement);
		context.defer(pivotTypedElement);		// Defer type/multiplicity setting
	}

	protected @Nullable EAnnotation createOppositeEAnnotation(@NonNull Property property) {
		OppositePropertyDetails oppositePropertyDetails = OppositePropertyDetails.createFromProperty(property);
		if (oppositePropertyDetails == null) {
			return null;
		}
		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setSource(EMOFExtendedMetaData.EMOF_PROPERTY_OPPOSITE_ROLE_NAME_ANNOTATION_SOURCE);
		EMap<String, String> details = eAnnotation.getDetails();
		assert details != null;
		oppositePropertyDetails.addToDetails(details);
		return eAnnotation;
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull EAnnotation getCoEClass(@NonNull EDataType eDataType) {
		EAnnotation eAnnotation = eDataType.getEAnnotation(PivotConstants.CO_ECLASS_ANNOTATION_SOURCE);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(PivotConstants.CO_ECLASS_ANNOTATION_SOURCE);
			eDataType.getEAnnotations().add(eAnnotation);
		}
		return eAnnotation;
	}

	public <T extends EObject> void safeVisitAll(@NonNull List<T> eObjects, @NonNull Iterable<? extends Element> pivotObjects) {
		for (Element pivotObject : pivotObjects) {
			@SuppressWarnings("unchecked")
			T eObject = (T) safeVisit(pivotObject);
			if (eObject != null) {
				eObjects.add(eObject);
			}
			// else error
		}
	}

	@Override
	public EObject visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for AS2Ecore Declaration pass");
	}

	@Override
	public EObject visitAnnotation(@NonNull Annotation pivotAnnotation) {
		@SuppressWarnings("null")
		@NonNull EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		copyDetails(eAnnotation, pivotAnnotation);
		eAnnotation.setSource(pivotAnnotation.getName());
		@SuppressWarnings("null")@NonNull List<EObject> contents = eAnnotation.getContents();
		safeVisitAll(contents, pivotAnnotation.getOwnedContents());
		if (!pivotAnnotation.getReferences().isEmpty()) {
			context.defer(pivotAnnotation);
		}
		return eAnnotation;
	}

	@Override
	public EObject visitAnyType(@NonNull AnyType pivotAnyType) {
		if (pivotAnyType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		copyClassifier(eClass, pivotAnyType);
		Class<?> instanceClass = null;
		String name = pivotAnyType.getName();
		if (TypeId.OCL_ANY_NAME.equals(name)) {
			instanceClass = Object.class;
		}
		eClass.setInstanceClass(instanceClass);
		eClass.setAbstract(true);
		eClass.setInterface(true);
		return eClass;
	}

	@Override
	public EObject visitClass(org.eclipse.ocl.pivot.@NonNull Class pivotClass) {
		if (pivotClass.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		copyClassifier(eClass, pivotClass);
		eClass.setAbstract(pivotClass.isIsAbstract());
		eClass.setInterface(pivotClass.isIsInterface());
		context.defer(pivotClass);		// Defer superclass resolution
		@SuppressWarnings("null")@NonNull List<EOperation> eOperations = eClass.getEOperations();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Constraint> nonDuplicateConstraints = Iterables.filter(pivotClass.getOwnedInvariants(), nonDuplicateConstraintsFilter);
		//		safeVisitAll(eOperations, nonDuplicateConstraints);
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Operation> nonDuplicateOperations = Iterables.filter(pivotClass.getOwnedOperations(), nonDuplicateOperationsFilter);
		safeVisitAll(eOperations, nonDuplicateOperations);
		@SuppressWarnings("null")@NonNull List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> nonDuplicateProperties = Iterables.filter(pivotClass.getOwnedProperties(), nonDuplicatePropertiesFilter);
		safeVisitAll(eStructuralFeatures, nonDuplicateProperties);
		Map<@NonNull String, @Nullable Object> options = context.getOptions();
		String invariantPrefix = AS2Ecore.getInvariantPrefix(options);
		for (Constraint pivotInvariant : nonDuplicateConstraints) {
			if (pivotInvariant.isIsCallable()) {
				@NonNull String name = PivotUtil.getName(pivotInvariant);
				if (invariantPrefix != null) {
					name = invariantPrefix + name;
				}
				EOperation eOperation = AS2Ecore.createConstraintEOperation(pivotInvariant, name, options);
				eOperations.add(eOperation);
				context.putCreated(pivotInvariant, eOperation);
				copyConstraintDetail(eOperation, pivotInvariant);
			}
		}
		if (!context.isSuppressDuplicates()) {
			List<ETypedElement> eDuplicates = null;
			@SuppressWarnings("null")@NonNull Iterable<@NonNull Constraint> duplicateConstraints = Iterables.filter(pivotClass.getOwnedInvariants(), duplicateConstraintsFilter);
			for (Constraint asConstraint : duplicateConstraints) {
				if (eDuplicates == null) {
					eDuplicates = new ArrayList<>();
				}
				//				Object eOperation = safeVisit(asConstraint);
				if (asConstraint.isIsCallable()) {
					EOperation eOperation = AS2Ecore.createConstraintEOperation(asConstraint, PivotUtil.getName(asConstraint), options);
					eOperations.add(eOperation);
					context.putCreated(asConstraint, eOperation);
					copyConstraintDetail(eOperation, asConstraint);
					eDuplicates.add(eOperation);
					context.defer(asConstraint);		// Defer references
				}
			}
			@SuppressWarnings("null")@NonNull Iterable<@NonNull Operation> duplicateOperations = Iterables.filter(pivotClass.getOwnedOperations(), duplicateOperationsFilter);
			for (Operation asOperation : duplicateOperations) {
				if (eDuplicates == null) {
					eDuplicates = new ArrayList<>();
				}
				Object eOperation = safeVisit(asOperation);
				if (eOperation instanceof EOperation) {
					eDuplicates.add((EOperation)eOperation);
				}
			}
			@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> duplicateProperties = Iterables.filter(pivotClass.getOwnedProperties(), duplicatePropertiesFilter);
			for (Property asProperty : duplicateProperties) {
				if (eDuplicates == null) {
					eDuplicates = new ArrayList<>();
				}
				Object eStructuralFeature = safeVisit(asProperty);
				if (eStructuralFeature instanceof EStructuralFeature) {
					eDuplicates.add((EStructuralFeature) eStructuralFeature);
				}
			}
			if (eDuplicates != null) {
				EAnnotation eAnnotation = eClass.getEAnnotation(PivotConstantsInternal.DUPLICATES_ANNOTATION_SOURCE);
				if (eAnnotation == null) {
					eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
					eAnnotation.setSource(PivotConstantsInternal.DUPLICATES_ANNOTATION_SOURCE);
					eClass.getEAnnotations().add(eAnnotation);
				}
				PivotUtil.refreshList(eAnnotation.getContents(), true, eDuplicates);
			}
		}
		return eClass;
	}

	@Override
	public EObject visitCollectionType(@NonNull CollectionType pivotCollectionType) {
		if (pivotCollectionType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		copyClassifier(eClass, pivotCollectionType);
		Class<?> instanceClass = null;
		String name = pivotCollectionType.getName();
		if ("Bag".equals(name)) {
			instanceClass = Bag.class;
		}
		else if ("Collection".equals(name)) {
			instanceClass = Collection.class;
		}
		else if ("OrderedCollection".equals(name)) {
			instanceClass = Collection.class;
		}
		else if ("OrderedSet".equals(name)) {
			instanceClass = OrderedSet.class;
		}
		else if ("Sequence".equals(name)) {
			instanceClass = List.class;
		}
		else if ("Set".equals(name)) {
			instanceClass = Set.class;
		}
		else if ("UniqueCollection".equals(name)) {
			instanceClass = Collection.class;
		}
		@SuppressWarnings("null")@NonNull List<EOperation> eOperations = eClass.getEOperations();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Operation> nonDuplicateOperations = Iterables.filter(pivotCollectionType.getOwnedOperations(), nonDuplicateOperationsFilter);
		safeVisitAll(eOperations, nonDuplicateOperations);
		@SuppressWarnings("null")@NonNull List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> nonDuplicateProperties = Iterables.filter(pivotCollectionType.getOwnedProperties(), nonDuplicatePropertiesFilter);
		safeVisitAll(eStructuralFeatures, nonDuplicateProperties);
//		copyOperations(eDataType, pivotCollectionType);
//		copyProperties(eDataType, pivotCollectionType);
		eClass.setInstanceClass(instanceClass);
		eClass.setAbstract(true);
		eClass.setInterface(true);
		context.defer(pivotCollectionType);		// Defer superclass resolution
		return eClass;
	}

	@Override
	public EObject visitConstraint(@NonNull Constraint pivotConstraint) {
		Element eContainer = (Element)pivotConstraint.eContainer();
		if (eContainer != null) {
			EModelElement eModelElement = context.getCreated(EModelElement.class, eContainer);
			if (eModelElement != null) {
				copyConstraintDetail(eModelElement, pivotConstraint);
				return null;
			}
		}
		return null;
	}

	@Override
	public EObject visitDataType(@NonNull DataType pivotDataType) {
		if (pivotDataType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
		copyDataTypeOrEnum(eDataType, pivotDataType);
		copyOperations(eDataType, pivotDataType);
		copyProperties(eDataType, pivotDataType);
		return eDataType;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public EObject visitEntryType(@NonNull EntryType pivotEntryType) {
		if (pivotEntryType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		copyClassifier(eClass, pivotEntryType);
		@SuppressWarnings("null")@NonNull List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> nonDuplicateProperties = Iterables.filter(pivotEntryType.getOwnedProperties(), nonDuplicatePropertiesFilter);
		safeVisitAll(eStructuralFeatures, nonDuplicateProperties);
		eClass.setInstanceClass(java.util.Map.Entry.class);
		eClass.setAbstract(true);
		eClass.setInterface(true);
		context.defer(pivotEntryType);		// Defer superclass resolution
		return eClass;
	}

	@Override
	public EObject visitEnumeration(@NonNull Enumeration pivotEnumeration) {
		if (pivotEnumeration.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
		copyDataTypeOrEnum(eEnum, pivotEnumeration);
		@SuppressWarnings("null")@NonNull List<EEnumLiteral> eLiterals = eEnum.getELiterals();
		safeVisitAll(eLiterals, pivotEnumeration.getOwnedLiterals());
		return eEnum;
	}

	@Override
	public EObject visitEnumerationLiteral(@NonNull EnumerationLiteral pivotEnumLiteral) {
		@SuppressWarnings("null")
		@NonNull EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
		copyNamedElement(eEnumLiteral, pivotEnumLiteral);
		if (pivotEnumLiteral.eIsSet(PivotPackage.Literals.ENUMERATION_LITERAL__LITERAL)) {
			eEnumLiteral.setLiteral(pivotEnumLiteral.getLiteral());
		}
		else {
			eEnumLiteral.eUnset(EcorePackage.Literals.EENUM_LITERAL__LITERAL);
		}
		if (pivotEnumLiteral.eIsSet(PivotPackage.Literals.ENUMERATION_LITERAL__VALUE)) {
			eEnumLiteral.setValue(pivotEnumLiteral.getValue().intValue());
		}
		else {
			eEnumLiteral.eUnset(EcorePackage.Literals.EENUM_LITERAL__VALUE);
		}
		return eEnumLiteral;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public EObject visitMapType(@NonNull MapType pivotMapType) {
		if (pivotMapType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		copyClassifier(eClass, pivotMapType);
		@SuppressWarnings("null")@NonNull List<EOperation> eOperations = eClass.getEOperations();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Operation> nonDuplicateOperations = Iterables.filter(pivotMapType.getOwnedOperations(), nonDuplicateOperationsFilter);
		safeVisitAll(eOperations, nonDuplicateOperations);
		@SuppressWarnings("null")@NonNull List<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
		@SuppressWarnings("null")@NonNull Iterable<@NonNull Property> nonDuplicateProperties = Iterables.filter(pivotMapType.getOwnedProperties(), nonDuplicatePropertiesFilter);
		safeVisitAll(eStructuralFeatures, nonDuplicateProperties);
//		copyOperations(eDataType, pivotMapType);
//		copyProperties(eDataType, pivotMapType);
		eClass.setInstanceClass(Map.class);
		eClass.setAbstract(true);
		eClass.setInterface(true);
		context.defer(pivotMapType);		// Defer superclass resolution
		return eClass;
	}

	@Override
	public Object visitModel(@NonNull Model pivotModel) {
		EModelElement firstElement = null;
		List<EObject> outputObjects = new ArrayList<>();
		for (org.eclipse.ocl.pivot.@NonNull Package pivotPackage : PivotUtil.getOwnedPackages(pivotModel)) {
			if (!PivotUtil.isImplicitPackage(pivotPackage)) {
				Object ecoreObject = safeVisit(pivotPackage);
				if (ecoreObject instanceof EObject) {
					outputObjects.add((EObject) ecoreObject);
					if ((firstElement == null) && (ecoreObject instanceof EModelElement)) {
						firstElement = (EModelElement) ecoreObject;
					}
				}
			}
		}
		@SuppressWarnings("null") List<@NonNull Import> imports = pivotModel.getOwnedImports();
		if (imports.size() > 0) {
			if (imports.size() > 0) {
				imports = new ArrayList<>(imports);
				Collections.sort(imports, new Comparator<Import>()
				{
					@Override
					public int compare(Import o1, Import o2) {
						String n1 = o1.getName();
						String n2 = o2.getName();
						if (n1 == null) n1 = "";
						if (n2 == null) n2 = "";
						return n1.compareTo(n2);
					}
				}
						);
			}
			URI ecoreURI = context.getEcoreURI();
			int noNames = 0;
			for (Import anImport : imports) {
				Namespace importedNamespace = anImport.getImportedNamespace();
				if (importedNamespace != null) {
					String key = anImport.getName();
					if ((key == null) || "".equals(key)) {
						noNames++;
					}
				}
			}
			if (firstElement != null)  {
				for (Import anImport : imports) {
					assert anImport != null;
					OCL_Import_EAnnotationConverter.getInstance().convertAnnotations(anImport, ecoreURI, noNames, firstElement);
				}
			}
		}
		return outputObjects;
	}

	@Override
	public EObject visitOperation(@NonNull Operation pivotOperation) {
		if (pivotOperation.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
		copyTypedElement(eOperation, pivotOperation);
		@SuppressWarnings("null")@NonNull List<ETypeParameter> eTypeParameters = eOperation.getETypeParameters();
		copyTemplateParameters(eTypeParameters, pivotOperation);
		@SuppressWarnings("null")@NonNull List<EParameter> eParameters = eOperation.getEParameters();
		safeVisitAll(eParameters, pivotOperation.getOwnedParameters());
		//		safeVisitAll(eOperation.getEGenericExceptions(), pivotOperation.getRaisedException());
		LanguageExpression bodyExpression = pivotOperation.getBodyExpression();
		if (bodyExpression != null) {
			EAnnotation eBodyConstraint = delegateInstaller.createOperationDelegate(eOperation, bodyExpression, context.getEcoreURI());
			if (eBodyConstraint != null) {
				//				AS2Ecore.copyComments(eBodyConstraint, bodyExpression);
			}
		}
		for (Constraint pivotConstraint : pivotOperation.getOwnedPreconditions()) {
			safeVisit(pivotConstraint);		// Results are inserted directly
		}
		for (Constraint pivotConstraint : pivotOperation.getOwnedPostconditions()) {
			safeVisit(pivotConstraint);		// Results are inserted directly
		}
		OCL_Pivot_Operation_EAnnotationConverter.getInstance().convertAnnotations(pivotOperation, eOperation);
		return eOperation;
	}

	@Override
	public EObject visitPackage(@NonNull Package pivotPackage) {
		@SuppressWarnings("null")
		@NonNull EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		copyNamedElement(ePackage, pivotPackage);
		context.defer(pivotPackage);		// Defer delegate annotation analysis
		if (pivotPackage.eIsSet(PivotPackage.Literals.PACKAGE__NS_PREFIX)) {
			ePackage.setNsPrefix(pivotPackage.getNsPrefix());
		}
		if (pivotPackage.eIsSet(PivotPackage.Literals.PACKAGE__URI)) {
			ePackage.setNsURI(pivotPackage.getURI());
		}
		@SuppressWarnings("null")@NonNull List<EPackage> eSubpackages = ePackage.getESubpackages();
		safeVisitAll(eSubpackages, pivotPackage.getOwnedPackages());
		@SuppressWarnings("null")@NonNull List<EClassifier> eClassifiers = ePackage.getEClassifiers();
		safeVisitAll(eClassifiers, pivotPackage.getOwnedClasses());
		return ePackage;
	}

	@Override
	public EObject visitParameter(@NonNull Parameter pivotParameter) {
		@SuppressWarnings("null")
		@NonNull EParameter eParameter = EcoreFactory.eINSTANCE.createEParameter();
		copyTypedElement(eParameter, pivotParameter);
		return eParameter;
	}

/*	@Override
	public EObject visitPrimitiveType(@NonNull PrimitiveType pivotPrimitiveType) {
		if (pivotPrimitiveType.basicGetOwnedTemplateArguments() != null) {
			return null;
		}
		@SuppressWarnings("null")
		@NonNull EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
		copyDataTypeOrEnum(eDataType, pivotPrimitiveType);
		/ *		Class<?> instanceClass = null;
		String name = pivotPrimitiveType.getName();
		if ("Boolean".equals(name)) {
			instanceClass = Boolean.class;
		}
		else if ("Integer".equals(name)) {
			instanceClass = IntegerValue.class;
		}
		else if ("Real".equals(name)) {
			instanceClass = RealValue.class;
		}
		else if ("String".equals(name)) {
			instanceClass = String.class;
		}
		else if ("UnlimitedNatural".equals(name)) {
			instanceClass = UnlimitedNaturalValue.class;
		}
		eDataType.setInstanceClass(instanceClass); * /
		return eDataType;
	} */

	@Override
	public EObject visitProperty(@NonNull Property pivotProperty) {
		if (pivotProperty.isIsImplicit()) {
			return null;
		}
		EStructuralFeature eStructuralFeature;
		Type type = pivotProperty.getType();
		CollectionType ecoreCollectionType = context.isEcoreCollection(type);
		if (ecoreCollectionType != null) {
			type = ecoreCollectionType.getElementType();
		}
		if (type instanceof MapType) {
			EReference eReference = EcoreFactory.eINSTANCE.createEReference();
			eReference.setContainment(pivotProperty.isIsComposite());
			eReference.setResolveProxies(pivotProperty.isIsResolveProxies());
			eStructuralFeature = eReference;
		}
		else if (type instanceof DataType) {
			EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
			eAttribute.setID(pivotProperty.isIsID());
			eStructuralFeature = eAttribute;
		}
		else {
			EReference eReference = EcoreFactory.eINSTANCE.createEReference();
			if ((pivotProperty.getOpposite() != null) || !pivotProperty.getKeys().isEmpty()) {
				context.defer(pivotProperty);
			}
			eReference.setContainment(pivotProperty.isIsComposite());
			eReference.setResolveProxies(pivotProperty.isIsResolveProxies());
			eStructuralFeature = eReference;
		}
		Property opposite = pivotProperty.getOpposite();
		if ((opposite != null) && opposite.isIsImplicit()) {
			EAnnotation eAnnotation = createOppositeEAnnotation(opposite);
			if (eAnnotation != null) {
				eStructuralFeature.getEAnnotations().add(eAnnotation);
			}
		}
		copyTypedElement(eStructuralFeature, pivotProperty);
		eStructuralFeature.setChangeable(!pivotProperty.isIsReadOnly());
		eStructuralFeature.setDerived(pivotProperty.isIsDerived());
		eStructuralFeature.setTransient(pivotProperty.isIsTransient());
		eStructuralFeature.setUnsettable(pivotProperty.isIsUnsettable());
		eStructuralFeature.setVolatile(pivotProperty.isIsVolatile());
		//		Object defaultValue = pivotProperty.getDefaultValue();
		String defaultValueLiteral = pivotProperty.getDefaultValueString();
		/*		if (defaultValue != null) {
			if (defaultValue instanceof String) {
				defaultValueLiteral = (String)defaultValue;
			}
			else if (defaultValue instanceof Boolean) {
				defaultValueLiteral = defaultValue.toString();
			}
			else if (defaultValue instanceof Value) {
				defaultValueLiteral = defaultValue.toString();
			}
			else if (defaultValue instanceof EnumerationLiteral) {
				defaultValueLiteral = ((EnumerationLiteral)defaultValue).getName();
			}
//			else if (defaultValue instanceof EnumerationLiteralId) {						// type is Enumeration
//				defaultValueLiteral = ((EnumerationLiteralId)defaultValue).getName();
//			}
			else {			// FIXME Use URI for lack of Ecore support
				defaultValueLiteral = String.valueOf(defaultValue);		// FIXME need init EAnnotation for generality
			}
/ *			String defaultValueLiteral = eObject.getDefaultValueLiteral();
			Object boxedValue;
			EClassifier eType = eObject.getEType();
			if (type instanceof DataType) {
				EDataType eDataType = (EDataType)eType;
				EFactory eFactoryInstance = eDataType.getEPackage().getEFactoryInstance();
				Object unboxedValue = eFactoryInstance.createFromString(eDataType, defaultValueLiteral);
				boxedValue = metamodelManager.getIdResolver().boxedValueOf(unboxedValue);
				pivotElement.setDefaultValue(boxedValue);
			}
			else {
				URI uri = URI.createURI(defaultValueLiteral);
				boxedValue = metamodelManager.getExternalResourceSet().getEObject(uri, false);
			}
			pivotElement.setDefaultValue(boxedValue); * /


		} */
		if (defaultValueLiteral != null) {
			eStructuralFeature.setDefaultValueLiteral(defaultValueLiteral);
		}
		else {
			eStructuralFeature.eUnset(EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL);
		}
		LanguageExpression defaultExpression = pivotProperty.getOwnedExpression();
		if (defaultExpression != null) {
			delegateInstaller.createPropertyDelegate(eStructuralFeature, defaultExpression, context.getEcoreURI());
		}
		/*		for (Property redefinedProperty : pivotProperty.getRedefinedProperty()) {
			EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(PivotConstants.REDEFINES_ANNOTATION_SOURCE);
			eStructuralFeature.getEAnnotations().add(eAnnotation);
		} */
		return eStructuralFeature;
	}

	@Override
	public EObject visitTemplateParameter(@NonNull TemplateParameter pivotTemplateParameter) {
		ETypeParameter eTypeParameter = EcoreFactory.eINSTANCE.createETypeParameter();
		eTypeParameter.setName(pivotTemplateParameter.getName());
		context.putCreated(pivotTemplateParameter, eTypeParameter);
		if (pivotTemplateParameter.basicGetConstrainingClasses() != null) {
			context.defer(pivotTemplateParameter);
		}
		return eTypeParameter;
	}
}
