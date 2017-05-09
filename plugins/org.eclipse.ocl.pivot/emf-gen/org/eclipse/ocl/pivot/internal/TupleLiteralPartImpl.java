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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple Literal Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TupleLiteralPartImpl#getOwnedInit <em>Owned Init</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TupleLiteralPartImpl
extends VariableDeclarationImpl
implements TupleLiteralPart {

	/**
	 * The cached value of the '{@link #getOwnedInit() <em>Owned Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInit()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedInit;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleLiteralPartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TUPLE_LITERAL_PART;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedInit()
	{
		return ownedInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInit(OCLExpression newOwnedInit, NotificationChain msgs)
	{
		OCLExpression oldOwnedInit = ownedInit;
		ownedInit = newOwnedInit;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT, oldOwnedInit, newOwnedInit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedInit(OCLExpression newOwnedInit)
	{
		if (newOwnedInit != ownedInit)
		{
			NotificationChain msgs = null;
			if (ownedInit != null)
				msgs = ((InternalEObject)ownedInit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT, null, msgs);
			if (newOwnedInit != null)
				msgs = ((InternalEObject)newOwnedInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT, null, msgs);
			msgs = basicSetOwnedInit(newOwnedInit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT, newOwnedInit, newOwnedInit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleInitialiserType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleInitialiserType:
		 *   let
		 *     severity : Integer[1] = 'TupleLiteralPart::CompatibleInitialiserType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedInit <> null and ownedInit.type <> null implies
		 *         ownedInit.type.conformsTo(type)
		 *       in
		 *         'TupleLiteralPart::CompatibleInitialiserType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_TupleLiteralPart_c_c_CompatibleInitialiserType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_and;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit = this.getOwnedInit();
					final /*@NonInvalid*/ boolean ne = ownedInit != null;
					/*@Thrown*/ boolean and;
					if (ne) {
						if (ownedInit == null) {
							throw new InvalidValueException("Null source for \'TypedElement::type\'");
						}
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedInit.getType();
						final /*@Thrown*/ boolean ne_0 = type != null;
						and = ne_0;
					}
					else {
						and = ValueUtil.FALSE_VALUE;
					}
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit_1 = this.getOwnedInit();
					if (ownedInit_1 == null) {
						throw new InvalidValueException("Null source for \'TypedElement::type\'");
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedInit_1.getType();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_1 = this.getType();
					final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type_0, type_1).booleanValue();
					CAUGHT_conformsTo = conformsTo;
				}
				catch (Exception e) {
					CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_conformsTo);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_TupleLiteralPart_c_c_CompatibleInitialiserType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateTypeIsNotInvalid(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv TypeIsNotInvalid:
		 *   let
		 *     severity : Integer[1] = 'TupleLiteralPart::TypeIsNotInvalid'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = type <> OclInvalid
		 *       in
		 *         'TupleLiteralPart::TypeIsNotInvalid'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_TupleLiteralPart_c_c_TypeIsNotInvalid);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OclInvalid = idResolver.getClass(TypeId.OCL_INVALID, null);
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
			final /*@NonInvalid*/ boolean result = (type != null) ? (type.getTypeId() != TYP_OclInvalid.getTypeId()) : true;
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_TupleLiteralPart_c_c_TypeIsNotInvalid, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PivotPackage.TUPLE_LITERAL_PART__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT:
				return basicSetOwnedInit(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case PivotPackage.TUPLE_LITERAL_PART__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TUPLE_LITERAL_PART__NAME:
				return getName();
			case PivotPackage.TUPLE_LITERAL_PART__IS_MANY:
				return isIsMany();
			case PivotPackage.TUPLE_LITERAL_PART__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.TUPLE_LITERAL_PART__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.TUPLE_LITERAL_PART__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT:
				return getOwnedInit();
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
			case PivotPackage.TUPLE_LITERAL_PART__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT:
				setOwnedInit((OCLExpression)newValue);
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
			case PivotPackage.TUPLE_LITERAL_PART__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TUPLE_LITERAL_PART__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT:
				setOwnedInit((OCLExpression)null);
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
			case PivotPackage.TUPLE_LITERAL_PART__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TUPLE_LITERAL_PART__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.TUPLE_LITERAL_PART__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.TUPLE_LITERAL_PART__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE:
				return type != null;
			case PivotPackage.TUPLE_LITERAL_PART__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.TUPLE_LITERAL_PART__OWNED_INIT:
				return ownedInit != null;
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
		if (baseClass == VariableDeclaration.class)
		{
			switch (baseOperationID)
			{
				case PivotPackage.VARIABLE_DECLARATION___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP: return PivotPackage.TUPLE_LITERAL_PART___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
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
			case PivotPackage.TUPLE_LITERAL_PART___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.TUPLE_LITERAL_PART___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.TUPLE_LITERAL_PART___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.TUPLE_LITERAL_PART___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.TUPLE_LITERAL_PART___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.TUPLE_LITERAL_PART___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.TUPLE_LITERAL_PART___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleInitialiserType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitTupleLiteralPart(this);
	}

	private TuplePartId partId = null;

	@Override
	public @Nullable TuplePartId getPartId() {
		TupleTypeId tupleTypeId = (TupleTypeId) ((TupleLiteralExp)eContainer()).getTypeId();
		TuplePartId partId2 = partId;
		if (partId2 == null) {
			String name2 = getName();
			assert name2 != null;
			partId = partId2 = tupleTypeId.getPartId(name2);
		}
		return partId2;
	}
} //TupleLiteralPartImpl
