/**
 */
package org.eclipse.ocl.ecore.tests.extlibrary;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Borrower</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ecore.tests.extlibrary.Borrower#getBorrowed <em>Borrowed</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getBorrower()
 * @model
 * @generated
 */
public interface Borrower extends Person {
	/**
	 * Returns the value of the '<em><b>Borrowed</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ecore.tests.extlibrary.Lendable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ecore.tests.extlibrary.Lendable#getBorrowers <em>Borrowers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Borrowed</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Borrowed</em>' reference list.
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getBorrower_Borrowed()
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.Lendable#getBorrowers
	 * @model opposite="borrowers"
	 * @generated
	 */
	EList<Lendable> getBorrowed();

} // Borrower
