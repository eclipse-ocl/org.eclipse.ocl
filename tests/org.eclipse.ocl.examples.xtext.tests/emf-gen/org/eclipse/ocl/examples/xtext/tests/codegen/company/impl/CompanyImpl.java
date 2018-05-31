/**
 * <copyright>
 *
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.xtext.tests.codegen.company.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.Company;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.TupleValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CompanyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CompanyImpl#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CompanyImpl#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompanyImpl extends EObjectImpl implements Company {
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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEmployees() <em>Employees</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmployees()
	 * @generated
	 * @ordered
	 */
	protected EList<Employee> employees;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final CompanySizeKind SIZE_EDEFAULT = CompanySizeKind.SMALL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompanyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CodegencompanyPackage.Literals.COMPANY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegencompanyPackage.COMPANY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Employee> getEmployees() {
		if (employees == null) {
			employees = new EObjectContainmentWithInverseEList<Employee>(Employee.class, this, CodegencompanyPackage.COMPANY__EMPLOYEES, CodegencompanyPackage.EMPLOYEE__COMPANY);
		}
		return employees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompanySizeKind getSize() {
		/**
		 *
		 * let
		 *   table : Set(Tuple(range:Sequence(Integer[*|?]), size:company::CompanySizeKind[1])) = Set{
		 *     Tuple{range = Sequence{0..49}, size = CompanySizeKind::small
		 *     }
		 *     , Tuple{range = Sequence{50..999}, size = CompanySizeKind::medium
		 *     }
		 *     , Tuple{range = Sequence{1000..1000000}, size = CompanySizeKind::large
		 *     }
		 *   }
		 * in
		 *   table->any(range->includes(employees->size()))?.size
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		@NonNull Iterator<Object> ITERATOR__1 = CodegencompanyTables.table.iterator();
		/*@Thrown*/ org.eclipse.ocl.pivot.values.@Nullable TupleValue any;
		while (true) {
			if (!ITERATOR__1.hasNext()) {
				throw new InvalidValueException("Nothing to return for ''any''");
			}
			@SuppressWarnings("null")
			/*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull TupleValue _1 = (TupleValue)ITERATOR__1.next();
			/**
			 * range->includes(employees->size())
			 */
			@SuppressWarnings("null")
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull SequenceValue range = ClassUtil.nonNullState((SequenceValue)_1.getValue(0/*range*/));
			@SuppressWarnings("null")
			final /*@NonInvalid*/ java.util.@NonNull List<Employee> employees = this.getEmployees();
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_employees = idResolver.createOrderedSetOfAll(CodegencompanyTables.ORD_CLSSid_Employee, employees);
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_employees);
			final /*@NonInvalid*/ boolean includes = CollectionIncludesOperation.INSTANCE.evaluate(range, size).booleanValue();
			//
			if (includes != ValueUtil.FALSE_VALUE) {			// Carry on till something found
				any = _1;
				break;
			}
		}
		/*@Caught*/ @Nullable Object CAUGHT_any;
		try {
			CAUGHT_any = any;
		}
		catch (Exception e) {
			CAUGHT_any = ValueUtil.createInvalidValue(e);
		}
		final /*@NonInvalid*/ @NonNull Object size_0 = CAUGHT_any == null;
		/*@Thrown*/ org.eclipse.ocl.pivot.ids.@Nullable EnumerationLiteralId safe_size_source;
		if (size_0 == Boolean.TRUE) {
			safe_size_source = null;
		}
		else {
			assert any != null;
			@SuppressWarnings("null")
			final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId size_1 = ClassUtil.nonNullState((EnumerationLiteralId)any.getValue(1/*size*/));
			safe_size_source = size_1;
		}
		if (safe_size_source == null) {
			throw new InvalidValueException("Null body for \'company::Company::size\'");
		}
		final /*@Thrown*/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@NonNull CompanySizeKind ECORE_safe_size_source = (CompanySizeKind)idResolver.ecoreValueOf(Enumerator.class, safe_size_source);
		return ECORE_safe_size_source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean dummyInvariant(final DiagnosticChain diagnostics, final Map<Object, Object> context) {
		/**
		 * inv dummyInvariant: true
		 */
		return ValueUtil.TRUE_VALUE;
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
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEmployees()).basicAdd(otherEnd, msgs);
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
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				return ((InternalEList<?>)getEmployees()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CodegencompanyPackage.COMPANY__NAME:
				return getName();
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				return getEmployees();
			case CodegencompanyPackage.COMPANY__SIZE:
				return getSize();
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
			case CodegencompanyPackage.COMPANY__NAME:
				setName((String)newValue);
				return;
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				getEmployees().clear();
				getEmployees().addAll((Collection<? extends Employee>)newValue);
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
			case CodegencompanyPackage.COMPANY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				getEmployees().clear();
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
			case CodegencompanyPackage.COMPANY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CodegencompanyPackage.COMPANY__EMPLOYEES:
				return employees != null && !employees.isEmpty();
			case CodegencompanyPackage.COMPANY__SIZE:
				return getSize() != SIZE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CodegencompanyPackage.COMPANY___DUMMY_INVARIANT__DIAGNOSTICCHAIN_MAP:
				return dummyInvariant((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //CompanyImpl
