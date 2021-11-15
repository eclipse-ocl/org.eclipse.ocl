/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
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
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal.MetamodelManagerInternalExtension2;
import org.eclipse.ocl.pivot.internal.manager.PivotExecutorManager;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#isIsMany <em>Is Many</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TypedElementImpl
extends NamedElementImpl
implements TypedElement {

	/**
	 * The number of structural features of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPED_ELEMENT_FEATURE_COUNT = NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 3;
	/**
	 * The number of operations of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPED_ELEMENT_OPERATION_COUNT = NamedElementImpl.NAMED_ELEMENT_OPERATION_COUNT + 2;
	/**
	 * The default value of the '{@link #isIsMany() <em>Is Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMany()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MANY_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REQUIRED_EDEFAULT = true;
	/**
	 * The flag representing the value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_REQUIRED_EFLAG = 1 << 8;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedElementImpl() {
		super();
		eFlags |= IS_REQUIRED_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TYPED_ELEMENT;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsRequired()
	{
		return (eFlags & IS_REQUIRED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getType() {
		if (type != null && type.eIsProxy())
		{
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 7, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 7, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean CompatibleBody(final ValueSpecification bodySpecification)
	{
		/**
		 * bodySpecification.type?.conformsTo(self.type)
		 */
		final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
		final /*@NonInvalid*/ @Nullable Type type = bodySpecification.getType();
		final /*@NonInvalid*/ @NonNull Object conformsTo = type == null;
		/*@Thrown*/ @Nullable Boolean safe_conformsTo_source;
		if (conformsTo == Boolean.TRUE) {
			safe_conformsTo_source = null;
		}
		else {
			if (type == null) {
				throw new InvalidValueException("Null \'\'Type\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@NonInvalid*/ @Nullable Type type_0 = this.getType();
			final /*@Thrown*/ boolean conformsTo_0 = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type, type_0).booleanValue();
			safe_conformsTo_source = conformsTo_0;
		}
		if (safe_conformsTo_source == null) {
			throw new InvalidValueException("Null body for \'pivot::TypedElement::CompatibleBody(ValueSpecification[1]) : Boolean[1]\'");
		}
		return safe_conformsTo_source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private @Nullable String mayBeInvalidReason()
	{
		ExpressionInOCL expressionInOCL = PivotUtil.getContainingExpressionInOCL(this);
		if (expressionInOCL == null) {		// e.g. Operation / Property
			return null;
		}
		Executor executor = PivotUtil.getExecutor(this);
		MetamodelManagerInternalExtension2 metamodelManager = (MetamodelManagerInternalExtension2)executor.getMetamodelManager();
		SymbolicAnalysis symbolicAnalysis = metamodelManager.getSymbolicAnalysis(expressionInOCL);
		SymbolicValue symbolicValue = symbolicAnalysis.getSymbolicValue(this);
		SymbolicReason mayBeInvalidReason = symbolicValue.mayBeInvalidReason();
		if (mayBeInvalidReason == null) {
			return null;
		}
		for (EObject eChild : eContents()) {
			if (eChild instanceof TypedElement) {		// MapLiteralPart ??
				SymbolicValue childSymbolicValue = symbolicAnalysis.getSymbolicValue((TypedElement)eChild);
				SymbolicReason childMayBeInvalidReason = childSymbolicValue.mayBeInvalidReason();
				if (childMayBeInvalidReason != null) {
					return null;		// Let child report result
				}
			}
		}
		for (EObject eChild : new TreeIterable(this, true)) {
			if (eChild instanceof InvalidLiteralExp) {
				return null;		// Suppress transitive explicit invalid for now
			}
		}
		return mayBeInvalidReason.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean validateUnconditionallyValid(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "TypedElement::UnconditionallyValid";
		try {
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this, context);
			final /*@NonInvalid*/ @NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.TYPED_ELEMENT___VALIDATE_UNCONDITIONALLY_VALID__DIAGNOSTICCHAIN_MAP);
			if (OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue()) {
				return true;
			}
			if (executor instanceof PivotExecutorManager) {
				StatusCodes.Severity invalidResultSeverity = executor.getEnvironmentFactory().getValue(PivotValidationOptions.PotentialInvalidResult);		// FIXME Phase out this duplication
				assert invalidResultSeverity != null;
				if (invalidResultSeverity == StatusCodes.Severity.IGNORE) {
					return true;
				}
			}
			final /*@NonInvalid*/ String mayBeInvalidReason = mayBeInvalidReason();
			if (mayBeInvalidReason == null) {
				return true;
			}
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(this, context);
				String emfMessage = StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfiedWithReason_ERROR_, new Object[]{constraintName, objectLabel, mayBeInvalidReason});
				Object emfData[] = new Object [] { this };
				int emfSeverity = ValueUtil.asIntegerValue(severity_0).intValue();
				diagnostics.add(new BasicDiagnostic(emfSeverity, PivotValidator.DIAGNOSTIC_SOURCE, 0, emfMessage, emfData));
			}
			return false;
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
	public void setIsRequired(boolean newIsRequired)
	{
		boolean oldIsRequired = (eFlags & IS_REQUIRED_EFLAG) != 0;
		if (newIsRequired) eFlags |= IS_REQUIRED_EFLAG; else eFlags &= ~IS_REQUIRED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, oldIsRequired, newIsRequired));
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
		}
		return eDynamicIsSet(featureID);
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
				return validateUnconditionallyValid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
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
		return visitor.visitTypedElement(this);
	}

	@Override
	public @NonNull TypeId getTypeId() {
		Type type2 = getType();
		return type2 != null ? type2.getTypeId() : TypeId.OCL_INVALID;
	}

	@Override
	public boolean isIsMany() {
		Type type = getType();
		if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			UnlimitedNaturalValue upperValue = collectionType.getUpperValue();
			return (upperValue instanceof UnlimitedValue) || (upperValue.intValue() > 1);
		}
		return false;
	}
} //TypedElementImpl
