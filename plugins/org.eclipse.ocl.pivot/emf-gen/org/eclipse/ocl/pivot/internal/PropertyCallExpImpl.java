/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialisation;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyCallExpImpl#getReferredProperty <em>Referred Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyCallExpImpl
extends NavigationCallExpImpl
implements PropertyCallExp {

	/**
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referredProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.PROPERTY_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredProperty() {
		if (referredProperty != null && referredProperty.eIsProxy())
		{
			InternalEObject oldReferredProperty = (InternalEObject)referredProperty;
			referredProperty = (Property)eResolveProxy(oldReferredProperty);
			if (referredProperty != oldReferredProperty)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
			}
		}
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetReferredProperty() {
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(Property newReferredProperty) {
		Property oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PROPERTY_CALL_EXP__NAME:
				return getName();
			case PivotPackage.PROPERTY_CALL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.PROPERTY_CALL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.PROPERTY_CALL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.PROPERTY_CALL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.PROPERTY_CALL_EXP__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.PROPERTY_CALL_EXP__IS_SAFE:
				return isIsSafe();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_SOURCE:
				return getOwnedSource();
			case PivotPackage.PROPERTY_CALL_EXP__IS_PRE:
				return isIsPre();
			case PivotPackage.PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				if (resolve) return getNavigationSource();
				return basicGetNavigationSource();
			case PivotPackage.PROPERTY_CALL_EXP__QUALIFIERS:
				return getQualifiers();
			case PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				if (resolve) return getReferredProperty();
				return basicGetReferredProperty();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_SAFE:
				setIsSafe((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_PRE:
				setIsPre((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				setNavigationSource((Property)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__QUALIFIERS:
				getQualifiers().clear();
				getQualifiers().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				setReferredProperty((Property)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PROPERTY_CALL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_SAFE:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)null);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__IS_PRE:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				setNavigationSource((Property)null);
				return;
			case PivotPackage.PROPERTY_CALL_EXP__QUALIFIERS:
				getQualifiers().clear();
				return;
			case PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				setReferredProperty((Property)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PROPERTY_CALL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PROPERTY_CALL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.PROPERTY_CALL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE:
				return type != null;
			case PivotPackage.PROPERTY_CALL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.PROPERTY_CALL_EXP__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.PROPERTY_CALL_EXP__IS_SAFE:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case PivotPackage.PROPERTY_CALL_EXP__OWNED_SOURCE:
				return ownedSource != null;
			case PivotPackage.PROPERTY_CALL_EXP__IS_PRE:
				return ((eFlags & IS_PRE_EFLAG) != 0) != IS_PRE_EDEFAULT;
			case PivotPackage.PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				return navigationSource != null;
			case PivotPackage.PROPERTY_CALL_EXP__QUALIFIERS:
				return qualifiers != null && !qualifiers.isEmpty();
			case PivotPackage.PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				return referredProperty != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass)
	{
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case PivotPackage.REFERRING_ELEMENT___GET_REFERRED_ELEMENT: return PivotPackage.PROPERTY_CALL_EXP___GET_REFERRED_ELEMENT;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.PROPERTY_CALL_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.PROPERTY_CALL_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.PROPERTY_CALL_EXP___IS_NON_NULL:
				return isNonNull();
			case PivotPackage.PROPERTY_CALL_EXP___IS_NULL:
				return isNull();
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___GET_REFERRED_ELEMENT:
				return getReferredElement();
			case PivotPackage.PROPERTY_CALL_EXP___GET_SPECIALIZED_REFERRED_PROPERTY_OWNING_TYPE:
				return getSpecializedReferredPropertyOwningType();
			case PivotPackage.PROPERTY_CALL_EXP___GET_SPECIALIZED_REFERRED_PROPERTY_TYPE:
				return getSpecializedReferredPropertyType();
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_COMPATIBLE_RESULT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleResultType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_NON_STATIC_SOURCE_TYPE_IS_CONFORMANT__DIAGNOSTICCHAIN_MAP:
				return validateNonStaticSourceTypeIsConformant((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP:
				return validateUnsafeSourceCanNotBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitPropertyCallExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		return getReferredProperty();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getSpecializedReferredPropertyOwningType()
	{
		Property referredProperty = getReferredProperty();
		org.eclipse.ocl.pivot.Class referencedType = referredProperty.getOwningClass();
		if (TemplateSpecialisation.needsSpecialisation(referencedType)) {
			Executor executor = PivotUtilInternal.getExecutor(this);
			TemplateSpecialisation templateSpecialization = new TemplateSpecialisation(executor.getCompleteEnvironment());
			Type resultType = getType();
			//			if (resultType instanceof DomainMetaclass) {
			//				resultType = ((DomainMetaclass)resultType).getInstanceType();
			//			}
			templateSpecialization.installEquivalence(resultType, referredProperty.getType());
			if (referencedType != null) {
				return templateSpecialization.getSpecialisation(referencedType);
			}
		}
		if (referencedType != null) {
			return referencedType;
		}
		else {
			Executor executor = PivotUtilInternal.getExecutor(this);
			return executor.getCompleteEnvironment().getOwnedStandardLibrary().getOclInvalidType();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getSpecializedReferredPropertyType()
	{
		Property referredProperty = getReferredProperty();
		Type referencedType = referredProperty.getType();
		Type specializedType = referencedType;
		if ((referencedType != null) && TemplateSpecialisation.needsSpecialisation(referencedType)) {
			Executor executor = PivotUtilInternal.getExecutor(this);
			TemplateSpecialisation templateSpecialization = new TemplateSpecialisation(executor.getCompleteEnvironment());
			Type resultType = getType();
			templateSpecialization.installEquivalence(resultType, referredProperty.getType());
			specializedType = templateSpecialization.getSpecialisation(referencedType);
		}
		if (specializedType instanceof org.eclipse.ocl.pivot.Class) {
			return (org.eclipse.ocl.pivot.Class)specializedType;
		}
		else {
			Executor executor = PivotUtilInternal.getExecutor(this);
			return executor.getCompleteEnvironment().getOwnedStandardLibrary().getOclInvalidType();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateNonStaticSourceTypeIsConformant(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv NonStaticSourceTypeIsConformant:
		 *   let
		 *     severity : Integer[1] = 'PropertyCallExp::NonStaticSourceTypeIsConformant'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = not referredProperty?.isStatic implies
		 *         ownedSource?.type.conformsTo(
		 *           getSpecializedReferredPropertyOwningType())
		 *       in
		 *         'PropertyCallExp::NonStaticSourceTypeIsConformant'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_PropertyCallExp_c_c_NonStaticSourceTypeIsConformant);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_2;
		if (le) {
			symbol_2 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_not;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_safe_isStatic_source;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Property referredProperty = this.getReferredProperty();
						/*@Caught*/ @Nullable Object CAUGHT_referredProperty;
						try {
							CAUGHT_referredProperty = referredProperty;
						}
						catch (Exception e) {
							CAUGHT_referredProperty = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_referredProperty == null;
						/*@Thrown*/ java.lang.@Nullable Boolean safe_isStatic_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_isStatic_source = null;
						}
						else {
							assert referredProperty != null;
							final /*@Thrown*/ boolean isStatic = referredProperty.isIsStatic();
							safe_isStatic_source = isStatic;
						}
						CAUGHT_safe_isStatic_source = safe_isStatic_source;
					}
					catch (Exception e) {
						CAUGHT_safe_isStatic_source = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_safe_isStatic_source);
					CAUGHT_not = not;
				}
				catch (Exception e) {
					CAUGHT_not = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
				try {
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					/*@Caught*/ @Nullable Object CAUGHT_ownedSource;
					try {
						CAUGHT_ownedSource = ownedSource;
					}
					catch (Exception e) {
						CAUGHT_ownedSource = ValueUtil.createInvalidValue(e);
					}
					final /*@NonInvalid*/ @NonNull Object symbol_1 = CAUGHT_ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_1 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
						safe_type_source = type;
					}
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Class getSpecializedReferredPropertyOwningType = this.getSpecializedReferredPropertyOwningType();
					final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, safe_type_source, getSpecializedReferredPropertyOwningType).booleanValue();
					CAUGHT_conformsTo = conformsTo;
				}
				catch (Exception e) {
					CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_not, CAUGHT_conformsTo);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_PropertyCallExp_c_c_NonStaticSourceTypeIsConformant, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_2 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SafeSourceCanBeNull:
		 *   let
		 *     severity : Integer[1] = 'PropertyCallExp::SafeSourceCanBeNull'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedSource <> null and isSafe implies
		 *         not ownedSource.isNonNull()
		 *       in
		 *         'PropertyCallExp::SafeSourceCanBeNull'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_PropertyCallExp_c_c_SafeSourceCanBeNull);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_and;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_ne;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@Thrown*/ boolean ne = ownedSource != null;
						CAUGHT_ne = ne;
					}
					catch (Exception e) {
						CAUGHT_ne = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @Nullable Object CAUGHT_isSafe;
					try {
						final /*@Thrown*/ java.lang.@Nullable Boolean isSafe = this.isIsSafe();
						CAUGHT_isSafe = isSafe;
					}
					catch (Exception e) {
						CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_ne, CAUGHT_isSafe);
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @Nullable Object CAUGHT_not;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_isNonNull;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
						if (ownedSource_0 == null) {
							throw new InvalidValueException("Null source for \'pivot::OCLExpression::isNonNull() : Boolean[1]\'");
						}
						final /*@Thrown*/ boolean isNonNull = ownedSource_0.isNonNull();
						CAUGHT_isNonNull = isNonNull;
					}
					catch (Exception e) {
						CAUGHT_isNonNull = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_isNonNull);
					CAUGHT_not = not;
				}
				catch (Exception e) {
					CAUGHT_not = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_not);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_PropertyCallExp_c_c_SafeSourceCanBeNull, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUnsafeSourceCanNotBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv UnsafeSourceCanNotBeNull:
		 *   let
		 *     severity : Integer[1] = 'PropertyCallExp::UnsafeSourceCanNotBeNull'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedSource <> null and not isSafe implies
		 *         ownedSource.isNonNull()
		 *       in
		 *         'PropertyCallExp::UnsafeSourceCanNotBeNull'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_PropertyCallExp_c_c_UnsafeSourceCanNotBeNull);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_and;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_ne;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@Thrown*/ boolean ne = ownedSource != null;
						CAUGHT_ne = ne;
					}
					catch (Exception e) {
						CAUGHT_ne = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @Nullable Object CAUGHT_not;
					try {
						/*@Caught*/ @Nullable Object CAUGHT_isSafe;
						try {
							final /*@Thrown*/ java.lang.@Nullable Boolean isSafe = this.isIsSafe();
							CAUGHT_isSafe = isSafe;
						}
						catch (Exception e) {
							CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
						}
						final /*@Thrown*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_isSafe);
						CAUGHT_not = not;
					}
					catch (Exception e) {
						CAUGHT_not = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_ne, CAUGHT_not);
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_isNonNull;
				try {
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
					if (ownedSource_0 == null) {
						throw new InvalidValueException("Null source for \'pivot::OCLExpression::isNonNull() : Boolean[1]\'");
					}
					final /*@Thrown*/ boolean isNonNull = ownedSource_0.isNonNull();
					CAUGHT_isNonNull = isNonNull;
				}
				catch (Exception e) {
					CAUGHT_isNonNull = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_isNonNull);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_PropertyCallExp_c_c_UnsafeSourceCanNotBeNull, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleResultType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleResultType:
		 *   let
		 *     severity : Integer[1] = 'PropertyCallExp::CompatibleResultType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = type = getSpecializedReferredPropertyType()
		 *       in
		 *         'PropertyCallExp::CompatibleResultType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_PropertyCallExp_c_c_CompatibleResultType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				@SuppressWarnings("null")
				final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Class getSpecializedReferredPropertyType = this.getSpecializedReferredPropertyType();
				final /*@Thrown*/ boolean result = (type != null) ? (type.getTypeId() == getSpecializedReferredPropertyType.getTypeId()) : false;
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_PropertyCallExp_c_c_CompatibleResultType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}
} //PropertyCallExpImpl
