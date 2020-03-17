/**
 * <copyright>
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.NoreflectioncompanyPackage;
import org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.util.NoreflectioncompanyValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getDirectReports <em>Direct Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getAllReports <em>All Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#getReportingChain <em>Reporting Chain</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl#isHasNameAsAttribute <em>Has Name As Attribute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EmployeeImpl extends EObjectImpl implements Employee {
	/**
	 * The number of structural features of the '<em>Employee</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EMPLOYEE_FEATURE_COUNT = 7;

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
	 * The cached value of the '{@link #getManager() <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManager()
	 * @generated
	 * @ordered
	 */
	protected Employee manager;

	/**
	 * The cached setting delegate for the '{@link #getDirectReports() <em>Direct Reports</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirectReports()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate DIRECT_REPORTS__ESETTING_DELEGATE = ((EStructuralFeature.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE__DIRECT_REPORTS).getSettingDelegate();

	/**
	 * The cached setting delegate for the '{@link #getAllReports() <em>All Reports</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllReports()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate ALL_REPORTS__ESETTING_DELEGATE = ((EStructuralFeature.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE__ALL_REPORTS).getSettingDelegate();

	/**
	 * The cached setting delegate for the '{@link #getReportingChain() <em>Reporting Chain</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReportingChain()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate REPORTING_CHAIN__ESETTING_DELEGATE = ((EStructuralFeature.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE__REPORTING_CHAIN).getSettingDelegate();

	/**
	 * The cached setting delegate for the '{@link #isHasNameAsAttribute() <em>Has Name As Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasNameAsAttribute()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate HAS_NAME_AS_ATTRIBUTE__ESETTING_DELEGATE = ((EStructuralFeature.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE__HAS_NAME_AS_ATTRIBUTE).getSettingDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmployeeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NoreflectioncompanyPackage.Literals.EMPLOYEE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Employee getManager() {
		if (manager != null && manager.eIsProxy()) {
			InternalEObject oldManager = (InternalEObject)manager;
			manager = (Employee)eResolveProxy(oldManager);
			if (manager != oldManager) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldManager, manager));
			}
		}
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Employee basicGetManager() {
		return manager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setManager(Employee newManager) {
		Employee oldManager = manager;
		manager = newManager;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldManager, manager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Company getCompany() {
		if (eContainerFeatureID() != (2)) return null;
		return (Company)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompany(Company newCompany, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompany, 2, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCompany(Company newCompany) {
		if (newCompany != eInternalContainer() || (eContainerFeatureID() != (2) && newCompany != null)) {
			if (EcoreUtil.isAncestor(this, newCompany))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCompany != null)
				msgs = ((InternalEObject)newCompany).eInverseAdd(this, 1, Company.class, msgs);
			msgs = basicSetCompany(newCompany, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, newCompany, newCompany));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EList<Employee> getDirectReports() {
		return (EList<Employee>)DIRECT_REPORTS__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EList<Employee> getAllReports() {
		return (EList<Employee>)ALL_REPORTS__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EList<Employee> getReportingChain() {
		return (EList<Employee>)REPORTING_CHAIN__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHasNameAsAttribute() {
		return (Boolean)HAS_NAME_AS_ATTRIBUTE__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * The cached invocation delegate for the '{@link #reportsTo(org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee) <em>Reports To</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #reportsTo(org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee)
	 * @generated
	 * @ordered
	 */
	protected static final EOperation.Internal.InvocationDelegate REPORTS_TO_EMPLOYEE__EINVOCATION_DELEGATE = ((EOperation.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE.getEOperations().get(0)).getInvocationDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean reportsTo(Employee manager) {
		try {
			return (Boolean)REPORTS_TO_EMPLOYEE__EINVOCATION_DELEGATE.dynamicInvoke(this, new BasicEList.UnmodifiableEList<Object>(1, new Object[]{manager}));
		}
		catch (InvocationTargetException ite) {
			throw new WrappedException(ite);
		}
	}

	/**
	 * The cached invocation delegate for the '{@link #hasNameAsOperation() <em>Has Name As Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #hasNameAsOperation()
	 * @generated
	 * @ordered
	 */
	protected static final EOperation.Internal.InvocationDelegate HAS_NAME_AS_OPERATION__EINVOCATION_DELEGATE = ((EOperation.Internal)NoreflectioncompanyPackage.Literals.EMPLOYEE.getEOperations().get(1)).getInvocationDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean noManagerImpliesDirectReports(DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			NoreflectioncompanyValidator.validate
				(NoreflectioncompanyPackage.Literals.EMPLOYEE,
				 this,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot", //$NON-NLS-1$
				 NoreflectioncompanyPackage.Literals.EMPLOYEE.getEOperations().get(2),
				 NO_MANAGER_IMPLIES_DIRECT_REPORTS_DIAGNOSTIC_CHAIN_MAP__EEXPRESSION,
				 Diagnostic.ERROR,
				 NoreflectioncompanyValidator.DIAGNOSTIC_SOURCE,
				 NoreflectioncompanyValidator.EMPLOYEE__NO_MANAGER_IMPLIES_DIRECT_REPORTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean hasNameAsOperation() {
		try {
			return (Boolean)HAS_NAME_AS_OPERATION__EINVOCATION_DELEGATE.dynamicInvoke(this, null);
		}
		catch (InvocationTargetException ite) {
			throw new WrappedException(ite);
		}
	}

	/**
	 * The cached validation expression for the '{@link #noManagerImpliesDirectReports(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>No Manager Implies Direct Reports</em>}' invariant operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #noManagerImpliesDirectReports(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 * @ordered
	 */
	protected static final String NO_MANAGER_IMPLIES_DIRECT_REPORTS_DIAGNOSTIC_CHAIN_MAP__EEXPRESSION = "manager.oclIsUndefined() implies directReports->size() > 0"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 2:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCompany((Company)otherEnd, msgs);
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
			case 2:
				return basicSetCompany(null, msgs);
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
			case 2:
				return eInternalContainer().eInverseRemove(this, 1, Company.class, msgs);
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
				return getName();
			case 1:
				if (resolve) return getManager();
				return basicGetManager();
			case 2:
				return getCompany();
			case 3:
				return getDirectReports();
			case 4:
				return getAllReports();
			case 5:
				return getReportingChain();
			case 6:
				return isHasNameAsAttribute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case 0:
				setName((String)newValue);
				return;
			case 1:
				setManager((Employee)newValue);
				return;
			case 2:
				setCompany((Company)newValue);
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
				setName(NAME_EDEFAULT);
				return;
			case 1:
				setManager((Employee)null);
				return;
			case 2:
				setCompany((Company)null);
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
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 1:
				return manager != null;
			case 2:
				return getCompany() != null;
			case 3:
				return DIRECT_REPORTS__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
			case 4:
				return ALL_REPORTS__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
			case 5:
				return REPORTING_CHAIN__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
			case 6:
				return HAS_NAME_AS_ATTRIBUTE__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //EmployeeImpl
