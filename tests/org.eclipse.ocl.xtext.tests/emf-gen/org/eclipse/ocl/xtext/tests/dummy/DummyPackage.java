/**
 */
package org.eclipse.ocl.xtext.tests.dummy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Dummy provides the org.eclipse.emf.ecore.generated_package entry required to ensure the test plugin is recognised by the ExtensionProcessor
 * <!-- end-model-doc -->
 * @see org.eclipse.ocl.xtext.tests.dummy.DummyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore"
 * @generated
 */
public interface DummyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dummy";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/test/Pivot/Dummy.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dummy";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DummyPackage eINSTANCE = org.eclipse.ocl.xtext.tests.dummy.impl.DummyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.tests.dummy.impl.DummyImpl <em>Dummy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.tests.dummy.impl.DummyImpl
	 * @see org.eclipse.ocl.xtext.tests.dummy.impl.DummyPackageImpl#getDummy()
	 * @generated
	 */
	int DUMMY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Dummy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Dummy Invariant</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY___DUMMY_INVARIANT__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The number of operations of the '<em>Dummy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.tests.dummy.Dummy <em>Dummy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dummy</em>'.
	 * @see org.eclipse.ocl.xtext.tests.dummy.Dummy
	 * @generated
	 */
	EClass getDummy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.tests.dummy.Dummy#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.tests.dummy.Dummy#getName()
	 * @see #getDummy()
	 * @generated
	 */
	EAttribute getDummy_Name();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.xtext.tests.dummy.Dummy#dummyInvariant(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Dummy Invariant</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Dummy Invariant</em>' operation.
	 * @see org.eclipse.ocl.xtext.tests.dummy.Dummy#dummyInvariant(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getDummy__DummyInvariant__DiagnosticChain_Map();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DummyFactory getDummyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.tests.dummy.impl.DummyImpl <em>Dummy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.tests.dummy.impl.DummyImpl
		 * @see org.eclipse.ocl.xtext.tests.dummy.impl.DummyPackageImpl#getDummy()
		 * @generated
		 */
		EClass DUMMY = eINSTANCE.getDummy();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DUMMY__NAME = eINSTANCE.getDummy_Name();

		/**
		 * The meta object literal for the '<em><b>Dummy Invariant</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DUMMY___DUMMY_INVARIANT__DIAGNOSTICCHAIN_MAP = eINSTANCE.getDummy__DummyInvariant__DiagnosticChain_Map();

	}

} //DummyPackage
