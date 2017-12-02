/**
 */
package org.eclipse.ocl.examples.xtext2lpg.XBNF;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metamodel Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration#getReferredPackage <em>Referred Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getMetamodelDeclaration()
 * @model
 * @generated
 */
public interface MetamodelDeclaration extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getMetamodelDeclaration_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Referred Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Package</em>' reference.
	 * @see #setReferredPackage(EPackage)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getMetamodelDeclaration_ReferredPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getReferredPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration#getReferredPackage <em>Referred Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Package</em>' reference.
	 * @see #getReferredPackage()
	 * @generated
	 */
	void setReferredPackage(EPackage value);

} // MetamodelDeclaration
