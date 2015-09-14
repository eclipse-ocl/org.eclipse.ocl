/**
 */
package bug477283.a.asub.asubsub.impl;

import bug477283.a.Bug477283APackage;

import bug477283.a.asub.Bug477283subPackage;

import bug477283.a.asub.asubsub.ASubSub;
import bug477283.a.asub.asubsub.Bug477283AsubsubFactory;
import bug477283.a.asub.asubsub.Bug477283AsubsubPackage;

import bug477283.a.asub.impl.Bug477283subPackageImpl;

import bug477283.a.impl.Bug477283APackageImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bug477283AsubsubPackageImpl extends EPackageImpl implements Bug477283AsubsubPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aSubSubEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see bug477283.a.asub.asubsub.Bug477283AsubsubPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Bug477283AsubsubPackageImpl() {
		super(eNS_URI, Bug477283AsubsubFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Bug477283AsubsubPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Bug477283AsubsubPackage init() {
		if (isInited) return (Bug477283AsubsubPackage)EPackage.Registry.INSTANCE.getEPackage(Bug477283AsubsubPackage.eNS_URI);

		// Obtain or create and register package
		Bug477283AsubsubPackageImpl theBug477283AsubsubPackage = (Bug477283AsubsubPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Bug477283AsubsubPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Bug477283AsubsubPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		Bug477283APackageImpl theBug477283APackage = (Bug477283APackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Bug477283APackage.eNS_URI) instanceof Bug477283APackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Bug477283APackage.eNS_URI) : Bug477283APackage.eINSTANCE);
		Bug477283subPackageImpl theBug477283subPackage = (Bug477283subPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Bug477283subPackage.eNS_URI) instanceof Bug477283subPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Bug477283subPackage.eNS_URI) : Bug477283subPackage.eINSTANCE);

		// Create package meta-data objects
		theBug477283AsubsubPackage.createPackageContents();
		theBug477283APackage.createPackageContents();
		theBug477283subPackage.createPackageContents();

		// Initialize created meta-data
		theBug477283AsubsubPackage.initializePackageContents();
		theBug477283APackage.initializePackageContents();
		theBug477283subPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBug477283AsubsubPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Bug477283AsubsubPackage.eNS_URI, theBug477283AsubsubPackage);
		return theBug477283AsubsubPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASubSub() {
		return aSubSubEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Bug477283AsubsubFactory getBug477283AsubsubFactory() {
		return (Bug477283AsubsubFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		aSubSubEClass = createEClass(ASUB_SUB);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(aSubSubEClass, ASubSub.class, "ASubSub", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //Bug477283AsubsubPackageImpl
