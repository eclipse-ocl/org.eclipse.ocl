/*******************************************************************************
 * Copyright (c) 2013, 2020 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.emf.validation.validity.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.emf.validation.validity.Result;
import org.eclipse.ocl.emf.validation.validity.ResultSet;
import org.eclipse.ocl.emf.validation.validity.RootNode;
import org.eclipse.ocl.emf.validation.validity.Severity;
import org.eclipse.ocl.emf.validation.validity.ValidityPackage;
import org.eclipse.ocl.emf.validation.validity.ValidityTables;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.library.collection.CollectionCountOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation;
import org.eclipse.ocl.pivot.library.string.StringConcatOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.BagValue.Accumulator;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SetValue;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.emf.validation.validity.impl.ResultSetImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.eclipse.ocl.emf.validation.validity.impl.ResultSetImpl#getResults <em>Results</em>}</li>
 *   <li>{@link org.eclipse.ocl.emf.validation.validity.impl.ResultSetImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link org.eclipse.ocl.emf.validation.validity.impl.ResultSetImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResultSetImpl extends MinimalEObjectImpl.Container implements ResultSet {
	/**
	 * The number of structural features of the '<em>Result Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RESULT_SET_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Result Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RESULT_SET_OPERATION_COUNT = 0;

	/**
	 * The cached value of the '{@link #getResults() <em>Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResults()
	 * @generated
	 * @ordered
	 */
	protected EList<Result> results;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected String timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResultSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.RESULT_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RootNode getRoot() {
		if (eContainerFeatureID() != (0)) return null;
		return (RootNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(RootNode newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, 0, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRoot(RootNode newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != (0) && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, 0, RootNode.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT // Bug 414352 workaround
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull EList<Result> getResults() {
		if (results == null) {
			results = new EObjectContainmentWithInverseEList<Result>(Result.class, this, ValidityPackage.Literals.RESULT_SET__RESULTS.getFeatureID(), ValidityPackage.Literals.RESULT__RESULT_SET.getFeatureID());
		}
		return results;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimestamp(String newTimestamp) {
		timestamp = newTimestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		/**
		 *
		 * let severities : Bag(validity::Severity) = results.severity
		 * in
		 *   severities->count(Severity::ERROR)
		 *   .toString() + ' errors ' +
		 *   severities->count(Severity::WARNING)
		 *   .toString() + ' warnings ' +
		 *   severities->count(Severity::INFO)
		 *   .toString() + ' infos ' +
		 *   severities->count(Severity::OK)
		 *   .toString() + ' oks'
		 */
		final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
		final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ @NonNull List<Result> results = this.getResults();
		final /*@NonInvalid*/ @NonNull SetValue BOXED_results = idResolver.createSetOfAll(ValidityTables.SET_CLSSid_Result, results);
		/*@Thrown*/ @NonNull Accumulator accumulator = ValueUtil.createBagAccumulatorValue(ValidityTables.BAG_ENUMid_Severity);
		@Nullable Iterator<Object> ITERATOR__1 = BOXED_results.iterator();
		/*@Thrown*/ @NonNull BagValue severities;
		while (true) {
			if (!ITERATOR__1.hasNext()) {
				severities = accumulator;
				break;
			}
			/*@NonInvalid*/ @Nullable Result _1 = (@Nullable Result)ITERATOR__1.next();
			/**
			 * severity
			 */
			if (_1 == null) {
				throw new InvalidValueException("Null \'source\' for \'http://www.eclipse.org/emf/validation/2013/Validity::Result::severity\'");
			}
			@SuppressWarnings("null")
			final /*@Thrown*/ @NonNull Severity severity = _1.getSeverity();
			final /*@Thrown*/ @NonNull EnumerationLiteralId BOXED_severity = ValidityTables.ENUMid_Severity.getEnumerationLiteralId(ClassUtil.requireNonNull(severity.getName()));
			//
			accumulator.add(BOXED_severity);
		}
		final /*@Thrown*/ @NonNull IntegerValue count = CollectionCountOperation.INSTANCE.evaluate(severities, ValidityTables.ELITid_ERROR);
		final /*@Thrown*/ @NonNull String toString = OclAnyToStringOperation.INSTANCE.evaluate(count);
		final /*@Thrown*/ @NonNull String sum = StringConcatOperation.INSTANCE.evaluate(toString, ValidityTables.STR__32_errors_32);
		final /*@Thrown*/ @NonNull IntegerValue count_0 = CollectionCountOperation.INSTANCE.evaluate(severities, ValidityTables.ELITid_WARNING);
		final /*@Thrown*/ @NonNull String toString_0 = OclAnyToStringOperation.INSTANCE.evaluate(count_0);
		final /*@Thrown*/ @NonNull String sum_0 = StringConcatOperation.INSTANCE.evaluate(sum, toString_0);
		final /*@Thrown*/ @NonNull String sum_1 = StringConcatOperation.INSTANCE.evaluate(sum_0, ValidityTables.STR__32_warnings_32);
		final /*@Thrown*/ @NonNull IntegerValue count_1 = CollectionCountOperation.INSTANCE.evaluate(severities, ValidityTables.ELITid_INFO);
		final /*@Thrown*/ @NonNull String toString_1 = OclAnyToStringOperation.INSTANCE.evaluate(count_1);
		final /*@Thrown*/ @NonNull String sum_2 = StringConcatOperation.INSTANCE.evaluate(sum_1, toString_1);
		final /*@Thrown*/ @NonNull String sum_3 = StringConcatOperation.INSTANCE.evaluate(sum_2, ValidityTables.STR__32_infos_32);
		final /*@Thrown*/ @NonNull IntegerValue count_2 = CollectionCountOperation.INSTANCE.evaluate(severities, ValidityTables.ELITid_OK);
		final /*@Thrown*/ @NonNull String toString_2 = OclAnyToStringOperation.INSTANCE.evaluate(count_2);
		final /*@Thrown*/ @NonNull String sum_4 = StringConcatOperation.INSTANCE.evaluate(sum_3, toString_2);
		final /*@Thrown*/ @NonNull String sum_5 = StringConcatOperation.INSTANCE.evaluate(sum_4, ValidityTables.STR__32_oks);
		return sum_5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 0:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((RootNode)otherEnd, msgs);
			case 1:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResults()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 0:
				return basicSetRoot(null, msgs);
			case 1:
				return ((InternalEList<?>)getResults()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case 0:
				return eInternalContainer().eInverseRemove(this, 0, RootNode.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 0:
				return getRoot();
			case 1:
				return getResults();
			case 2:
				return getTimestamp();
			case 3:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case 0:
				setRoot((RootNode)newValue);
				return;
			case 1:
				getResults().clear();
				getResults().addAll((Collection<? extends Result>)newValue);
				return;
			case 2:
				setTimestamp((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case 0:
				setRoot((RootNode)null);
				return;
			case 1:
				getResults().clear();
				return;
			case 2:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case 0:
				return getRoot() != null;
			case 1:
				return results != null && !results.isEmpty();
			case 2:
				return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
			case 3:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (timestamp: ");
		result.append(timestamp);
		result.append(')');
		return result.toString();
	}

} //ResultSetImpl
