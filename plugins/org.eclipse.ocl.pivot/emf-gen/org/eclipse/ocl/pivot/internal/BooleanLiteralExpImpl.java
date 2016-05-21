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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.BooleanLiteralExpImpl#isBooleanSymbol <em>Boolean Symbol</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanLiteralExpImpl
		extends PrimitiveLiteralExpImpl
		implements BooleanLiteralExp {

	/**
	 * The default value of the '{@link #isBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_SYMBOL_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final int BOOLEAN_SYMBOL_EFLAG = 1 << 9;

	/**
	 * The flag representing whether the Boolean Symbol attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int BOOLEAN_SYMBOL_ESETFLAG = 1 << 10;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanLiteralExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.BOOLEAN_LITERAL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBooleanSymbol() {
		return (eFlags & BOOLEAN_SYMBOL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBooleanSymbol(boolean newBooleanSymbol) {
		boolean oldBooleanSymbol = (eFlags & BOOLEAN_SYMBOL_EFLAG) != 0;
		if (newBooleanSymbol) eFlags |= BOOLEAN_SYMBOL_EFLAG; else eFlags &= ~BOOLEAN_SYMBOL_EFLAG;
		boolean oldBooleanSymbolESet = (eFlags & BOOLEAN_SYMBOL_ESETFLAG) != 0;
		eFlags |= BOOLEAN_SYMBOL_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL, oldBooleanSymbol, newBooleanSymbol, !oldBooleanSymbolESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetBooleanSymbol() {
		boolean oldBooleanSymbol = (eFlags & BOOLEAN_SYMBOL_EFLAG) != 0;
		boolean oldBooleanSymbolESet = (eFlags & BOOLEAN_SYMBOL_ESETFLAG) != 0;
		if (BOOLEAN_SYMBOL_EDEFAULT) eFlags |= BOOLEAN_SYMBOL_EFLAG; else eFlags &= ~BOOLEAN_SYMBOL_EFLAG;
		eFlags &= ~BOOLEAN_SYMBOL_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL, oldBooleanSymbol, BOOLEAN_SYMBOL_EDEFAULT, oldBooleanSymbolESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetBooleanSymbol() {
		return (eFlags & BOOLEAN_SYMBOL_ESETFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsBoolean(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * 
		 * inv validateTypeIsBoolean:
		 *   let severity : Integer[1] = 'BooleanLiteralExp::TypeIsBoolean'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = self.type = Boolean
		 *       in
		 *         'BooleanLiteralExp::TypeIsBoolean'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_BooleanLiteralExp_c_c_TypeIsBoolean);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
		    symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
		    /*@Caught*/ @NonNull Object CAUGHT_status;
		    try {
		        final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Boolean = idResolver.getClass(TypeId.BOOLEAN, null);
		        final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
		        final /*@Thrown*/ boolean status = (type != null) ? (type.getTypeId() == TYP_Boolean.getTypeId()) : false;
		        CAUGHT_status = status;
		    }
		    catch (Exception e) {
		        CAUGHT_status = ValueUtil.createInvalidValue(e);
		    }
		    final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_BooleanLiteralExp_c_c_TypeIsBoolean, this, null, diagnostics, context, null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case PivotPackage.BOOLEAN_LITERAL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.BOOLEAN_LITERAL_EXP__NAME:
				return getName();
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
				return isBooleanSymbol();
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
			case PivotPackage.BOOLEAN_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
				setBooleanSymbol((Boolean)newValue);
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
			case PivotPackage.BOOLEAN_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
				unsetBooleanSymbol();
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
			case PivotPackage.BOOLEAN_LITERAL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.BOOLEAN_LITERAL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.BOOLEAN_LITERAL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.BOOLEAN_LITERAL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE:
				return type != null;
			case PivotPackage.BOOLEAN_LITERAL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
				return isSetBooleanSymbol();
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
			case PivotPackage.BOOLEAN_LITERAL_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.BOOLEAN_LITERAL_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.BOOLEAN_LITERAL_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.BOOLEAN_LITERAL_EXP___VALIDATE_TYPE_IS_BOOLEAN__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsBoolean((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitBooleanLiteralExp(this);
	}
} //BooleanLiteralExpImpl
