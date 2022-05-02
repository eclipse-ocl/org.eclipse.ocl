/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#isIsVirtual <em>Is Virtual</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#getOwnedArguments <em>Owned Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#getReferredOperation <em>Referred Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationCallExpImpl
extends FeatureCallExpImpl
implements OperationCallExp {

	/**
	 * The number of structural features of the '<em>Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP_FEATURE_COUNT = FeatureCallExpImpl.FEATURE_CALL_EXP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP_OPERATION_COUNT = FeatureCallExpImpl.FEATURE_CALL_EXP_OPERATION_COUNT + 6;

	/**
	 * The default value of the '{@link #isIsVirtual() <em>Is Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @see #isIsVirtual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VIRTUAL_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isIsVirtual() <em>Is Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @see #isIsVirtual()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_VIRTUAL_EFLAG = 1 << 12;

	/**
	 * The cached value of the '{@link #getOwnedArguments() <em>Owned Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<OCLExpression> ownedArguments;

	/**
	 * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation referredOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallExpImpl() {
		super();
		eFlags |= IS_VIRTUAL_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsVirtual()
	{
		return (eFlags & IS_VIRTUAL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsVirtual(boolean newIsVirtual)
	{
		boolean oldIsVirtual = (eFlags & IS_VIRTUAL_EFLAG) != 0;
		if (newIsVirtual) eFlags |= IS_VIRTUAL_EFLAG; else eFlags &= ~IS_VIRTUAL_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, oldIsVirtual, newIsVirtual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<OCLExpression> getOwnedArguments()
	{
		if (ownedArguments == null)
		{
			ownedArguments = new EObjectContainmentEList<OCLExpression>(OCLExpression.class, this, 14);
		}
		return ownedArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getReferredOperation() {
		if (referredOperation != null && referredOperation.eIsProxy())
		{
			InternalEObject oldReferredOperation = (InternalEObject)referredOperation;
			referredOperation = (Operation)eResolveProxy(oldReferredOperation);
			if (referredOperation != oldReferredOperation)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 15, oldReferredOperation, referredOperation));
			}
		}
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetReferredOperation() {
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOperation(Operation newReferredOperation) {
		Operation oldReferredOperation = referredOperation;
		referredOperation = newReferredOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 15, oldReferredOperation, referredOperation));
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 11:
				return basicSetOwnedSource(null, msgs);
			case 14:
				return ((InternalEList<?>)getOwnedArguments()).basicRemove(otherEnd, msgs);
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
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return isIsMany();
			case 6:
				return isIsRequired();
			case 7:
				if (resolve) return getType();
				return basicGetType();
			case 8:
				return getTypeValue();
			case 9:
				return isIsImplicit();
			case 10:
				return isIsSafe();
			case 11:
				return getOwnedSource();
			case 12:
				return isIsPre();
			case 13:
				return isIsVirtual();
			case 14:
				return getOwnedArguments();
			case 15:
				if (resolve) return getReferredOperation();
				return basicGetReferredOperation();
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
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 6:
				setIsRequired((Boolean)newValue);
				return;
			case 7:
				setType((Type)newValue);
				return;
			case 8:
				setTypeValue((Type)newValue);
				return;
			case 9:
				setIsImplicit((Boolean)newValue);
				return;
			case 10:
				setIsSafe((Boolean)newValue);
				return;
			case 11:
				setOwnedSource((OCLExpression)newValue);
				return;
			case 12:
				setIsPre((Boolean)newValue);
				return;
			case 13:
				setIsVirtual((Boolean)newValue);
				return;
			case 14:
				getOwnedArguments().clear();
				getOwnedArguments().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case 15:
				setReferredOperation((Operation)newValue);
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
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 6:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 7:
				setType((Type)null);
				return;
			case 8:
				setTypeValue((Type)null);
				return;
			case 9:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case 10:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case 11:
				setOwnedSource((OCLExpression)null);
				return;
			case 12:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case 13:
				setIsVirtual(IS_VIRTUAL_EDEFAULT);
				return;
			case 14:
				getOwnedArguments().clear();
				return;
			case 15:
				setReferredOperation((Operation)null);
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
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return isIsMany() != IS_MANY_EDEFAULT;
			case 6:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case 7:
				return type != null;
			case 8:
				return typeValue != null;
			case 9:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case 10:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case 11:
				return ownedSource != null;
			case 12:
				return ((eFlags & IS_PRE_EFLAG) != 0) != IS_PRE_EDEFAULT;
			case 13:
				return ((eFlags & IS_VIRTUAL_EFLAG) != 0) != IS_VIRTUAL_EDEFAULT;
			case 14:
				return ownedArguments != null && !ownedArguments.isEmpty();
			case 15:
				return referredOperation != null;
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
		if (baseClass == CallExp.class)
		{
			switch (baseOperationID)
			{
				case 6: return 13;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case 0: return 9;
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
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return isNonNull();
			case 4:
				return isNull();
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 13:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateSafeSourceCannotBeMap((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return getReferredElement();
			case 10:
				return hasOclVoidOverload();
			case 11:
				return validateArgumentCount((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 12:
				return validateArgumentTypeIsConformant((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 14:
				return validateUnsafeSourceCanNotBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOperationCallExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		return getReferredOperation();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateArgumentTypeIsConformant(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "OperationCallExp::ArgumentTypeIsConformant";
		try {
			/**
			 *
			 * inv ArgumentTypeIsConformant:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[?] = let operation : Operation[?] = self.referredOperation
			 *         in
			 *           let parameters : OrderedSet(Parameter)[?] = operation?.ownedParameters
			 *           in
			 *             let selfType : Type[?] = operation?.owningClass
			 *             in
			 *               Sequence{1..ownedArguments->size()
			 *               }
			 *               ->forAll(i |
			 *                 let argument : OCLExpression[1] = ownedArguments->at(i)
			 *                 in
			 *                   let parameter : Parameter[1] = parameters?->at(i)
			 *                   in
			 *                     let parameterType : Type[?] = parameter.type
			 *                     in
			 *                       let
			 *                         requiredType : Type[?] = if parameter.isTypeof
			 *                         then Class
			 *                         else parameterType?.specializeIn(self, selfType)
			 *                         endif
			 *                       in argument.type?.conformsTo(requiredType))
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_ARGUMENT_TYPE_IS_CONFORMANT__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					final /*@NonInvalid*/ @Nullable Operation operation = this.getReferredOperation();
					final /*@NonInvalid*/ @NonNull Object IsEQ2_ = operation == null;
					/*@Thrown*/ @Nullable OrderedSetValue parameters;
					if (IsEQ2_ == Boolean.TRUE) {
						parameters = null;
					}
					else {
						assert operation != null;
						final /*@Thrown*/ @NonNull List<Parameter> ownedParameters = operation.getOwnedParameters();
						final /*@Thrown*/ @NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Parameter, ownedParameters);
						parameters = BOXED_ownedParameters;
					}
					final /*@NonInvalid*/ @NonNull Object IsEQ2__0 = operation == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Class selfType;
					if (IsEQ2__0 == Boolean.TRUE) {
						selfType = null;
					}
					else {
						assert operation != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Class owningClass = operation.getOwningClass();
						selfType = owningClass;
					}
					final /*@NonInvalid*/ @NonNull List<OCLExpression> ownedArguments_0 = this.getOwnedArguments();
					final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedArguments = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_OCLExpression, ownedArguments_0);
					final /*@NonInvalid*/ @NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedArguments);
					final /*@NonInvalid*/ @NonNull IntegerRange RNG = ValueUtil.createRange(PivotTables.INT_1, size);
					final /*@NonInvalid*/ @NonNull SequenceValue SEQ = ValueUtil.createSequenceRange(PivotTables.SEQ_PRIMid_Integer, RNG);
					/*@Thrown*/ @Nullable Object accumulator = ValueUtil.TRUE_VALUE;
					@NonNull Iterator<Object> ITER_i = SEQ.iterator();
					/*@Thrown*/ @Nullable Boolean forAll;
					while (true) {
						if (!ITER_i.hasNext()) {
							if (accumulator == null) {
								forAll = null;
							}
							else if (accumulator == ValueUtil.TRUE_VALUE) {
								forAll = ValueUtil.TRUE_VALUE;
							}
							else {
								throw (InvalidValueException)accumulator;
							}
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ @NonNull IntegerValue i = (@NonNull IntegerValue)ITER_i.next();
						/**
						 *
						 * let argument : OCLExpression[1] = ownedArguments->at(i)
						 * in
						 *   let parameter : Parameter[1] = parameters?->at(i)
						 *   in
						 *     let parameterType : Type[?] = parameter.type
						 *     in
						 *       let
						 *         requiredType : Type[?] = if parameter.isTypeof
						 *         then Class
						 *         else parameterType?.specializeIn(self, selfType)
						 *         endif
						 *       in argument.type?.conformsTo(requiredType)
						 */
						/*@Caught*/ @Nullable Object CAUGHT_IF_IsEQ2_;
						try {
							final /*@Thrown*/ @Nullable OCLExpression argument = (@Nullable OCLExpression)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedArguments, i);
							if (parameters == null) {
								throw new InvalidValueException("Null \'\'Collection\'\' rather than \'\'OclVoid\'\' value required");
							}
							final /*@Thrown*/ @NonNull OrderedSetValue excluding = (@Nullable OrderedSetValue)CollectionExcludingOperation.INSTANCE.evaluate(parameters, (Object)null);
							final /*@Thrown*/ @Nullable Parameter parameter = (@Nullable Parameter)OrderedCollectionAtOperation.INSTANCE.evaluate(excluding, i);
							if (parameter == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type parameterType = parameter.getType();
							final /*@Thrown*/ boolean isTypeof = parameter.isIsTypeof();
							/*@Thrown*/ @Nullable Type requiredType;
							if (isTypeof) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_0 = idResolver.getClass(PivotTables.CLSSid_Class, null);
								requiredType = TYP_Class_0;
							}
							else {
								/*@Caught*/ @Nullable Object CAUGHT_parameterType;
								try {
									CAUGHT_parameterType = parameterType;
								}
								catch (Exception THROWN_CAUGHT_parameterType) {
									CAUGHT_parameterType = ValueUtil.createInvalidValue(THROWN_CAUGHT_parameterType);
								}
								final /*@NonInvalid*/ @NonNull Object IsEQ2__1 = CAUGHT_parameterType == null;
								/*@Thrown*/ @Nullable Type IF_IsEQ2__0;
								if (IsEQ2__1 == Boolean.TRUE) {
									IF_IsEQ2__0 = null;
								}
								else {
									assert parameterType != null;
									@SuppressWarnings("null")
									final /*@Thrown*/ @NonNull Type specializeIn = parameterType.specializeIn(this, selfType);
									IF_IsEQ2__0 = specializeIn;
								}
								requiredType = IF_IsEQ2__0;
							}
							if (argument == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type type = argument.getType();
							/*@Caught*/ @Nullable Object CAUGHT_type;
							try {
								CAUGHT_type = type;
							}
							catch (Exception THROWN_CAUGHT_type) {
								CAUGHT_type = ValueUtil.createInvalidValue(THROWN_CAUGHT_type);
							}
							final /*@NonInvalid*/ @NonNull Object IsEQ2__2 = CAUGHT_type == null;
							/*@Thrown*/ @Nullable Boolean IF_IsEQ2_;
							if (IsEQ2__2 == Boolean.TRUE) {
								IF_IsEQ2_ = null;
							}
							else {
								if (type == null) {
									throw new InvalidValueException("Null argument");
								}
								final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type, requiredType).booleanValue();
								IF_IsEQ2_ = conformsTo;
							}
							CAUGHT_IF_IsEQ2_ = IF_IsEQ2_;
						}
						catch (Exception THROWN_CAUGHT_IF_IsEQ2_) {
							CAUGHT_IF_IsEQ2_ = ValueUtil.createInvalidValue(THROWN_CAUGHT_IF_IsEQ2_);
						}
						//
						if (CAUGHT_IF_IsEQ2_ == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
							forAll = ValueUtil.FALSE_VALUE;
							break;														// Stop immediately
						}
						else if (CAUGHT_IF_IsEQ2_ == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
							;															// Carry on
						}
						else if (CAUGHT_IF_IsEQ2_ == null) {								// Abnormal null body evaluation result
							if (accumulator == ValueUtil.TRUE_VALUE) {
								accumulator = null;										// Cache a null failure
							}
						}
						else if (CAUGHT_IF_IsEQ2_ instanceof InvalidValueException) {		// Abnormal exception evaluation result
							accumulator = CAUGHT_IF_IsEQ2_;									// Cache an exception failure
						}
						else {															// Impossible badly typed result
							accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
						}
					}
					result = forAll;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "OperationCallExp::SafeSourceCanBeNull";
		try {
			/**
			 *
			 * inv SafeSourceCanBeNull:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = ownedSource <> null and isSafe implies
			 *         not ownedSource.isNonNull()
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ boolean IsEQ_ = ownedSource != null;
					final /*@NonInvalid*/ @Nullable Boolean and;
					if (!IsEQ_) {
						and = ValueUtil.FALSE_VALUE;
					}
					else {
						final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
						if (!isSafe) {
							and = ValueUtil.FALSE_VALUE;
						}
						else {
							and = ValueUtil.TRUE_VALUE;
						}
					}
					final /*@Thrown*/ @Nullable Boolean implies;
					if (and == ValueUtil.FALSE_VALUE) {
						implies = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_not;
						try {
							/*@Caught*/ @NonNull Object CAUGHT_isNonNull;
							try {
								if (ownedSource == null) {
									throw new InvalidValueException("Null source for \'pivot::OCLExpression::isNonNull() : Boolean[1]\'");
								}
								final /*@Thrown*/ boolean isNonNull = ownedSource.isNonNull();
								CAUGHT_isNonNull = isNonNull;
							}
							catch (Exception THROWN_CAUGHT_isNonNull) {
								CAUGHT_isNonNull = ValueUtil.createInvalidValue(THROWN_CAUGHT_isNonNull);
							}
							if (CAUGHT_isNonNull instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_isNonNull;
							}
							final /*@Thrown*/ @Nullable Boolean not;
							if (CAUGHT_isNonNull == ValueUtil.FALSE_VALUE) {
								not = ValueUtil.TRUE_VALUE;
							}
							else {
								if (CAUGHT_isNonNull == ValueUtil.TRUE_VALUE) {
									not = ValueUtil.FALSE_VALUE;
								}
								else {
									not = null;
								}
							}
							CAUGHT_not = not;
						}
						catch (Exception THROWN_CAUGHT_not) {
							CAUGHT_not = ValueUtil.createInvalidValue(THROWN_CAUGHT_not);
						}
						if (CAUGHT_not == ValueUtil.TRUE_VALUE) {
							implies = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_not instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not;
							}
							if ((and == null) || (CAUGHT_not == null)) {
								implies = null;
							}
							else {
								implies = ValueUtil.FALSE_VALUE;
							}
						}
					}
					result = implies;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUnsafeSourceCanNotBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "OperationCallExp::UnsafeSourceCanNotBeNull";
		try {
			/**
			 *
			 * inv UnsafeSourceCanNotBeNull:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[?] = not hasOclVoidOverload() implies ownedSource <> null and not isSafe implies
			 *         ownedSource.isNonNull()
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					final /*@NonInvalid*/ boolean hasOclVoidOverload_0 = this.hasOclVoidOverload();
					final /*@NonInvalid*/ @Nullable Boolean not_0;
					if (!hasOclVoidOverload_0) {
						not_0 = ValueUtil.TRUE_VALUE;
					}
					else {
						if (hasOclVoidOverload_0) {
							not_0 = ValueUtil.FALSE_VALUE;
						}
						else {
							not_0 = null;
						}
					}
					final /*@Thrown*/ @Nullable Boolean implies_0;
					if (not_0 == ValueUtil.FALSE_VALUE) {
						implies_0 = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_implies;
						try {
							/*@Caught*/ @Nullable Object CAUGHT_and;
							try {
								final /*@NonInvalid*/ @Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
								final /*@NonInvalid*/ boolean IsEQ_ = ownedSource_0 != null;
								final /*@Thrown*/ @Nullable Boolean and;
								if (!IsEQ_) {
									and = ValueUtil.FALSE_VALUE;
								}
								else {
									final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
									final /*@NonInvalid*/ @Nullable Boolean not;
									if (!isSafe) {
										not = ValueUtil.TRUE_VALUE;
									}
									else {
										if (isSafe) {
											not = ValueUtil.FALSE_VALUE;
										}
										else {
											not = null;
										}
									}
									if (not == ValueUtil.FALSE_VALUE) {
										and = ValueUtil.FALSE_VALUE;
									}
									else {
										if (not == null) {
											and = null;
										}
										else {
											and = ValueUtil.TRUE_VALUE;
										}
									}
								}
								CAUGHT_and = and;
							}
							catch (Exception THROWN_CAUGHT_and) {
								CAUGHT_and = ValueUtil.createInvalidValue(THROWN_CAUGHT_and);
							}
							final /*@Thrown*/ @Nullable Boolean implies;
							if (CAUGHT_and == ValueUtil.FALSE_VALUE) {
								implies = ValueUtil.TRUE_VALUE;
							}
							else {
								/*@Caught*/ @NonNull Object CAUGHT_isNonNull;
								try {
									final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
									if (ownedSource == null) {
										throw new InvalidValueException("Null source for \'pivot::OCLExpression::isNonNull() : Boolean[1]\'");
									}
									final /*@Thrown*/ boolean isNonNull = ownedSource.isNonNull();
									CAUGHT_isNonNull = isNonNull;
								}
								catch (Exception THROWN_CAUGHT_isNonNull) {
									CAUGHT_isNonNull = ValueUtil.createInvalidValue(THROWN_CAUGHT_isNonNull);
								}
								if (CAUGHT_isNonNull == ValueUtil.TRUE_VALUE) {
									implies = ValueUtil.TRUE_VALUE;
								}
								else {
									if (CAUGHT_and instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_and;
									}
									if (CAUGHT_isNonNull instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_isNonNull;
									}
									if (CAUGHT_and == null) {
										implies = null;
									}
									else {
										implies = ValueUtil.FALSE_VALUE;
									}
								}
							}
							CAUGHT_implies = implies;
						}
						catch (Exception THROWN_CAUGHT_implies) {
							CAUGHT_implies = ValueUtil.createInvalidValue(THROWN_CAUGHT_implies);
						}
						if (CAUGHT_implies == ValueUtil.TRUE_VALUE) {
							implies_0 = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_implies instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_implies;
							}
							if ((not_0 == null) || (CAUGHT_implies == null)) {
								implies_0 = null;
							}
							else {
								implies_0 = ValueUtil.FALSE_VALUE;
							}
						}
					}
					result = implies_0;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateArgumentCount(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "OperationCallExp::ArgumentCount";
		try {
			/**
			 *
			 * inv ArgumentCount:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[1] = ownedArguments->size() =
			 *         referredOperation?.ownedParameters?->size()
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION_CALL_EXP___VALIDATE_ARGUMENT_COUNT__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @NonNull Object result;
				try {
					final /*@NonInvalid*/ @NonNull List<OCLExpression> ownedArguments_0 = this.getOwnedArguments();
					final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedArguments = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_OCLExpression, ownedArguments_0);
					final /*@NonInvalid*/ @NonNull IntegerValue size_0 = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedArguments);
					final /*@NonInvalid*/ @Nullable Operation referredOperation_0 = this.getReferredOperation();
					final /*@NonInvalid*/ @NonNull Object IsEQ2_ = referredOperation_0 == null;
					/*@Thrown*/ @Nullable OrderedSetValue IF_IsEQ2_;
					if (IsEQ2_ == Boolean.TRUE) {
						IF_IsEQ2_ = null;
					}
					else {
						assert referredOperation_0 != null;
						final /*@Thrown*/ @NonNull List<Parameter> ownedParameters = referredOperation_0.getOwnedParameters();
						final /*@Thrown*/ @NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Parameter, ownedParameters);
						IF_IsEQ2_ = BOXED_ownedParameters;
					}
					if (IF_IsEQ2_ == null) {
						throw new InvalidValueException("Null \'\'Collection\'\' rather than \'\'OclVoid\'\' value required");
					}
					final /*@Thrown*/ @NonNull OrderedSetValue excluding = (@Nullable OrderedSetValue)CollectionExcludingOperation.INSTANCE.evaluate(IF_IsEQ2_, (Object)null);
					final /*@Thrown*/ @NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(excluding);
					final /*@Thrown*/ boolean IsEQ_ = size_0.equals(size);
					result = IsEQ_;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean hasOclVoidOverload()
	{
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtil.getExecutor(this);
		Operation referredOperation = getReferredOperation();
		OperationId baseOperationId = referredOperation.getOperationId();
		org.eclipse.ocl.pivot.Class oclVoidType = executor.getStandardLibrary().getOclVoidType();
		CompleteClass oclVoidCompleteClass = executor.getEnvironmentFactory().getCompleteModel().getCompleteClass(oclVoidType);
		Operation oclVoidOperation = oclVoidCompleteClass.getOperation(baseOperationId);
		return oclVoidOperation != null;
	}
} //OperationCallExpImpl
