/**
 */
package bug477283.a.asub.asubsub.impl;

import bug477283.a.asub.asubsub.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bug477283AsubsubFactoryImpl extends EFactoryImpl implements Bug477283AsubsubFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Bug477283AsubsubFactory init() {
		try {
			Bug477283AsubsubFactory theBug477283AsubsubFactory = (Bug477283AsubsubFactory)EPackage.Registry.INSTANCE.getEFactory(Bug477283AsubsubPackage.eNS_URI);
			if (theBug477283AsubsubFactory != null) {
				return theBug477283AsubsubFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Bug477283AsubsubFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bug477283AsubsubFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Bug477283AsubsubPackage.ASUB_SUB: return createASubSub();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASubSub createASubSub() {
		ASubSubImpl aSubSub = new ASubSubImpl();
		return aSubSub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bug477283AsubsubPackage getBug477283AsubsubPackage() {
		return (Bug477283AsubsubPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Bug477283AsubsubPackage getPackage() {
		return Bug477283AsubsubPackage.eINSTANCE;
	}

} //Bug477283AsubsubFactoryImpl
