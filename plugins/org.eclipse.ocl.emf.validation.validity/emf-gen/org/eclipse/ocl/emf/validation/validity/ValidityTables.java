/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.emf.validation.validity/model/Validity.ecore
 * using:
 *   /org.eclipse.ocl.emf.validation.validity/model/Validity.genmodel
 *   org.eclipse.ocl.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.emf.validation.validity;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
// import org.eclipse.ocl.emf.validation.validity.ValidityTables;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.PartialStandardLibrary;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * ValidityTables provides the dispatch tables for the validity for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
public class ValidityTables extends AbstractTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The overall library of all packages and types.
	 */
	public static final @NonNull PartialStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	The AS package for the ValidityPackage.eINSTANCE EPackage.
	 */
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(ValidityPackage.eINSTANCE);

	/**
	 *	The AS model for the AS package and its additional orphans.
	 */
	public static final AbstractTables.@NonNull BuiltInModel MODEL = LIBRARY.createModel(PACKAGE);

	/**
	 *	The EMF Resource containing the AS model, its AS package and its additional orphans.
	 */
	public static final @NonNull Resource RESOURCE = LIBRARY.createResource(MODEL);

	/**
	 * A <code>Descriptor</code> may be used by the {@link BuiltInASResourceFactory.Registry}
	 * to defer loading of a built-in model until needed.
	 *
	 * @since 7.0
	 */
	public static class Descriptor implements CompletePackageIdRegistryReader.Descriptor
	{
		@Override
		public @NonNull Model getModel() {
			return MODEL;
		}
	}

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", "ecore", EcorePackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull RootPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity = IdManager.getRootPackageId("http://www.eclipse.org/emf/validation/2013/Validity");
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_AbstractNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("AbstractNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_ConstrainingNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("ConstrainingNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_EObject = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getClassId("EObject", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_LeafConstrainingNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("LeafConstrainingNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Result = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("Result", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_ResultConstrainingNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("ResultConstrainingNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_ResultSet = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("ResultSet", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_ResultValidatableNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("ResultValidatableNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_RootConstrainingNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("RootConstrainingNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_RootNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("RootNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_RootValidatableNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("RootValidatableNode", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_ValidatableNode = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getClassId("ValidatableNode", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_ConstraintLocator = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getDataTypeId("ConstraintLocator", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_Object = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getDataTypeId("Object", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_Resource = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getDataTypeId("Resource", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_Throwable = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getDataTypeId("Throwable", 0);
	public static final /*@NonInvalid*/ @NonNull EnumerationId ENUMid_Severity = ValidityTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity.getEnumerationId("Severity");
	public static final /*@NonInvalid*/ @NonNull String STR__32_c_32 = " : ";
	public static final /*@NonInvalid*/ @NonNull String STR__32_errors_32 = " errors ";
	public static final /*@NonInvalid*/ @NonNull String STR__32_infos_32 = " infos ";
	public static final /*@NonInvalid*/ @NonNull String STR__32_oks = " oks";
	public static final /*@NonInvalid*/ @NonNull String STR__32_warnings_32 = " warnings ";
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_CLSSid_AbstractNode = TypeId.BAG.getSpecializedId(ValidityTables.CLSSid_AbstractNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_CLSSid_Result = TypeId.BAG.getSpecializedId(ValidityTables.CLSSid_Result, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_ENUMid_Severity = TypeId.BAG.getSpecializedId(ValidityTables.ENUMid_Severity, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_ERROR = ValidityTables.ENUMid_Severity.getEnumerationLiteralId("ERROR");
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_INFO = ValidityTables.ENUMid_Severity.getEnumerationLiteralId("INFO");
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_OK = ValidityTables.ENUMid_Severity.getEnumerationLiteralId("OK");
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_WARNING = ValidityTables.ENUMid_Severity.getEnumerationLiteralId("WARNING");
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_AbstractNode = TypeId.ORDERED_SET.getSpecializedId(ValidityTables.CLSSid_AbstractNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ConstrainingNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ConstrainingNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_Result = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_Result, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ResultSet = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ResultSet, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_RootConstrainingNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_RootConstrainingNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_RootValidatableNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_RootValidatableNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ValidatableNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ValidatableNode, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			ValidityTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::TypeParameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The type descriptors for each type.
	 */
	public static class Types {
		static {
			Init.initStart();
			TypeParameters.init();
		}

		public static final org.eclipse.ocl.pivot.@NonNull Class _AbstractNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.ABSTRACT_NODE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ConstrainingNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.CONSTRAINING_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ConstraintLocator = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.CONSTRAINT_LOCATOR, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _LeafConstrainingNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.LEAF_CONSTRAINING_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Object = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.OBJECT, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Resource = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.RESOURCE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Result = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.RESULT, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ResultConstrainingNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.RESULT_CONSTRAINING_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ResultSet = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.RESULT_SET, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ResultValidatableNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.RESULT_VALIDATABLE_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _RootConstrainingNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.ROOT_CONSTRAINING_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _RootNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.ROOT_NODE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _RootValidatableNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.ROOT_VALIDATABLE_NODE, null, 0);
		public static final @NonNull Enumeration _Severity = LIBRARY.createEnumeration(ValidityPackage.Literals.SEVERITY);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Throwable = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.THROWABLE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _ValidatableNode = LIBRARY.createClass(PivotPackage.Literals.CLASS, ValidityPackage.Literals.VALIDATABLE_NODE, null, 0);

		/*
		 * AS Class indexed by EClassifier.getClassifierID().
		 */
		private static final org.eclipse.ocl.pivot.@NonNull Class @NonNull [] eClassifierID2asClass = {
			/* 0 */ _AbstractNode,
			/* 1 */ _ConstrainingNode,
			/* 2 */ _LeafConstrainingNode,
			/* 3 */ _Result,
			/* 4 */ _ResultConstrainingNode,
			/* 5 */ _ResultSet,
			/* 6 */ _ResultValidatableNode,
			/* 7 */ _RootNode,
			/* 8 */ _RootConstrainingNode,
			/* 9 */ _RootValidatableNode,
			/* 10 */ _ValidatableNode,
			/* 11 */ _ConstraintLocator,
			/* 12 */ _Object,
			/* 13 */ _Resource,
			/* 14 */ _Throwable,
			/* 15 */ _Severity
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			LIBRARY.initPackage(PACKAGE, eClassifierID2asClass);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::Types and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragment descriptors for the local elements of each type and its supertypes.
	 */
	public static class Fragments {
		static {
			Init.initStart();
			Types.init();
		}

		private static final @NonNull FlatFragment _AbstractNode__AbstractNode = LIBRARY.createFragment(Types._AbstractNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _AbstractNode__OclAny = LIBRARY.createFragment(Types._AbstractNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _AbstractNode__OclElement = LIBRARY.createFragment(Types._AbstractNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _ConstrainingNode__AbstractNode = LIBRARY.createFragment(Types._ConstrainingNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _ConstrainingNode__ConstrainingNode = LIBRARY.createFragment(Types._ConstrainingNode, Types._ConstrainingNode);
		private static final @NonNull FlatFragment _ConstrainingNode__OclAny = LIBRARY.createFragment(Types._ConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _ConstrainingNode__OclElement = LIBRARY.createFragment(Types._ConstrainingNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _ConstraintLocator__ConstraintLocator = LIBRARY.createFragment(Types._ConstraintLocator, Types._ConstraintLocator);
		private static final @NonNull FlatFragment _ConstraintLocator__OclAny = LIBRARY.createFragment(Types._ConstraintLocator, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _LeafConstrainingNode__AbstractNode = LIBRARY.createFragment(Types._LeafConstrainingNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _LeafConstrainingNode__ConstrainingNode = LIBRARY.createFragment(Types._LeafConstrainingNode, Types._ConstrainingNode);
		private static final @NonNull FlatFragment _LeafConstrainingNode__LeafConstrainingNode = LIBRARY.createFragment(Types._LeafConstrainingNode, Types._LeafConstrainingNode);
		private static final @NonNull FlatFragment _LeafConstrainingNode__OclAny = LIBRARY.createFragment(Types._LeafConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _LeafConstrainingNode__OclElement = LIBRARY.createFragment(Types._LeafConstrainingNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _Object__Object = LIBRARY.createFragment(Types._Object, Types._Object);
		private static final @NonNull FlatFragment _Object__OclAny = LIBRARY.createFragment(Types._Object, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _Resource__OclAny = LIBRARY.createFragment(Types._Resource, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Resource__Resource = LIBRARY.createFragment(Types._Resource, Types._Resource);

		private static final @NonNull FlatFragment _Result__OclAny = LIBRARY.createFragment(Types._Result, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Result__OclElement = LIBRARY.createFragment(Types._Result, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _Result__Result = LIBRARY.createFragment(Types._Result, Types._Result);

		private static final @NonNull FlatFragment _ResultConstrainingNode__AbstractNode = LIBRARY.createFragment(Types._ResultConstrainingNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _ResultConstrainingNode__ConstrainingNode = LIBRARY.createFragment(Types._ResultConstrainingNode, Types._ConstrainingNode);
		private static final @NonNull FlatFragment _ResultConstrainingNode__OclAny = LIBRARY.createFragment(Types._ResultConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _ResultConstrainingNode__OclElement = LIBRARY.createFragment(Types._ResultConstrainingNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _ResultConstrainingNode__ResultConstrainingNode = LIBRARY.createFragment(Types._ResultConstrainingNode, Types._ResultConstrainingNode);

		private static final @NonNull FlatFragment _ResultSet__OclAny = LIBRARY.createFragment(Types._ResultSet, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _ResultSet__OclElement = LIBRARY.createFragment(Types._ResultSet, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _ResultSet__ResultSet = LIBRARY.createFragment(Types._ResultSet, Types._ResultSet);

		private static final @NonNull FlatFragment _ResultValidatableNode__AbstractNode = LIBRARY.createFragment(Types._ResultValidatableNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _ResultValidatableNode__OclAny = LIBRARY.createFragment(Types._ResultValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _ResultValidatableNode__OclElement = LIBRARY.createFragment(Types._ResultValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _ResultValidatableNode__ResultValidatableNode = LIBRARY.createFragment(Types._ResultValidatableNode, Types._ResultValidatableNode);
		private static final @NonNull FlatFragment _ResultValidatableNode__ValidatableNode = LIBRARY.createFragment(Types._ResultValidatableNode, Types._ValidatableNode);

		private static final @NonNull FlatFragment _RootConstrainingNode__AbstractNode = LIBRARY.createFragment(Types._RootConstrainingNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _RootConstrainingNode__ConstrainingNode = LIBRARY.createFragment(Types._RootConstrainingNode, Types._ConstrainingNode);
		private static final @NonNull FlatFragment _RootConstrainingNode__OclAny = LIBRARY.createFragment(Types._RootConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _RootConstrainingNode__OclElement = LIBRARY.createFragment(Types._RootConstrainingNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _RootConstrainingNode__RootConstrainingNode = LIBRARY.createFragment(Types._RootConstrainingNode, Types._RootConstrainingNode);

		private static final @NonNull FlatFragment _RootNode__OclAny = LIBRARY.createFragment(Types._RootNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _RootNode__OclElement = LIBRARY.createFragment(Types._RootNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _RootNode__RootNode = LIBRARY.createFragment(Types._RootNode, Types._RootNode);

		private static final @NonNull FlatFragment _RootValidatableNode__AbstractNode = LIBRARY.createFragment(Types._RootValidatableNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _RootValidatableNode__OclAny = LIBRARY.createFragment(Types._RootValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _RootValidatableNode__OclElement = LIBRARY.createFragment(Types._RootValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _RootValidatableNode__RootValidatableNode = LIBRARY.createFragment(Types._RootValidatableNode, Types._RootValidatableNode);
		private static final @NonNull FlatFragment _RootValidatableNode__ValidatableNode = LIBRARY.createFragment(Types._RootValidatableNode, Types._ValidatableNode);

		private static final @NonNull FlatFragment _Severity__OclAny = LIBRARY.createFragment(Types._Severity, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Severity__OclElement = LIBRARY.createFragment(Types._Severity, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _Severity__OclEnumeration = LIBRARY.createFragment(Types._Severity, OCLstdlibTables.Types._OclEnumeration);
		private static final @NonNull FlatFragment _Severity__OclType = LIBRARY.createFragment(Types._Severity, OCLstdlibTables.Types._OclType);
		private static final @NonNull FlatFragment _Severity__Severity = LIBRARY.createFragment(Types._Severity, Types._Severity);

		private static final @NonNull FlatFragment _Throwable__OclAny = LIBRARY.createFragment(Types._Throwable, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Throwable__Throwable = LIBRARY.createFragment(Types._Throwable, Types._Throwable);

		private static final @NonNull FlatFragment _ValidatableNode__AbstractNode = LIBRARY.createFragment(Types._ValidatableNode, Types._AbstractNode);
		private static final @NonNull FlatFragment _ValidatableNode__OclAny = LIBRARY.createFragment(Types._ValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _ValidatableNode__OclElement = LIBRARY.createFragment(Types._ValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _ValidatableNode__ValidatableNode = LIBRARY.createFragment(Types._ValidatableNode, Types._ValidatableNode);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		public static final @NonNull Operation _AbstractNode__getChildren = LIBRARY.createOperation(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE___GET_CHILDREN, ParameterTypes.EMPTY_LIST, MODEL.getCollectionType(OCLstdlibTables.Types._OrderedSet, Types._AbstractNode, false),
			0 | IsRequired, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _AbstractNode__getParent = LIBRARY.createOperation(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE___GET_PARENT, ParameterTypes.EMPTY_LIST, Types._AbstractNode,
			1, TemplateParameters.EMPTY_LIST, null);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final @NonNull Property _AbstractNode__enabled = LIBRARY.createProperty(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE__ENABLED, OCLstdlibTables.Types._Boolean, 0 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _AbstractNode__grayed = LIBRARY.createProperty(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE__GRAYED, OCLstdlibTables.Types._Boolean, 1 | IsReadOnly | IsRequired | IsResolveProxies);
		public static final @NonNull Property _AbstractNode__label = LIBRARY.createProperty(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE__LABEL, OCLstdlibTables.Types._String, 2 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _AbstractNode__visible = LIBRARY.createProperty(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE__VISIBLE, OCLstdlibTables.Types._Boolean, 3 | IsReadOnly | IsRequired | IsResolveProxies);
		public static final @NonNull Property _AbstractNode__worstResult = LIBRARY.createProperty(Types._AbstractNode, ValidityPackage.Literals.ABSTRACT_NODE__WORST_RESULT, Types._Result, 4 | IsDerived | IsTransient);

		public static final @NonNull Property _ConstrainingNode__children = LIBRARY.createProperty(Types._ConstrainingNode, ValidityPackage.Literals.CONSTRAINING_NODE__CHILDREN, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._ConstrainingNode, false), 0 | IsComposite | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ConstrainingNode__constrainingObject = LIBRARY.createProperty(Types._ConstrainingNode, ValidityPackage.Literals.CONSTRAINING_NODE__CONSTRAINING_OBJECT, Types._Object, 1 | IsRequired | IsResolveProxies | IsTransient);
		public static final @NonNull Property _ConstrainingNode__parent = LIBRARY.createProperty(Types._ConstrainingNode, ValidityPackage.Literals.CONSTRAINING_NODE__PARENT, Types._ConstrainingNode, 2 | IsResolveProxies);

		public static final @NonNull Property _LeafConstrainingNode__constraintLocator = LIBRARY.createProperty(Types._LeafConstrainingNode, ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR, Types._ConstraintLocator, 0 | IsRequired | IsResolveProxies | IsTransient);
		public static final @NonNull Property _LeafConstrainingNode__constraintResource = LIBRARY.createProperty(Types._LeafConstrainingNode, ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_RESOURCE, Types._Resource, 1 | IsDerived | IsReadOnly | IsResolveProxies | IsTransient | IsVolatile);
		public static final @NonNull Property _LeafConstrainingNode__constraintString = LIBRARY.createProperty(Types._LeafConstrainingNode, ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_STRING, OCLstdlibTables.Types._String, 2 | IsDerived | IsReadOnly | IsResolveProxies | IsTransient | IsVolatile);
		public static final @NonNull Property _LeafConstrainingNode__Result__leafConstrainingNode = LIBRARY.createOppositeProperty(Types._LeafConstrainingNode, "Result", MODEL.getCollectionType(OCLstdlibTables.Types._Bag, Types._Result, false), 3 | IsImplicit | IsRequired | IsResolveProxies);

		public static final @NonNull Property _Result__diagnostic = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__DIAGNOSTIC, Types._Object, 0 | IsResolveProxies | IsTransient);
		public static final @NonNull Property _Result__exception = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__EXCEPTION, Types._Throwable, 1 | IsResolveProxies);
		public static final @NonNull Property _Result__leafConstrainingNode = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__LEAF_CONSTRAINING_NODE, Types._LeafConstrainingNode, 2 | IsDerived | IsReadOnly | IsRequired | IsTransient | IsVolatile);
		public static final @NonNull Property _Result__name = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__NAME, OCLstdlibTables.Types._String, 3 | IsReadOnly | IsRequired | IsResolveProxies | IsTransient | IsVolatile);
		public static final @NonNull Property _Result__resultConstrainingNode = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__RESULT_CONSTRAINING_NODE, Types._ResultConstrainingNode, 4 | IsDerived | IsReadOnly | IsRequired | IsTransient | IsVolatile);
		public static final @NonNull Property _Result__resultSet = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__RESULT_SET, Types._ResultSet, 5 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _Result__resultValidatableNode = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__RESULT_VALIDATABLE_NODE, Types._ResultValidatableNode, 6 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _Result__severity = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__SEVERITY, Types._Severity, 7 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _Result__validatableNode = LIBRARY.createProperty(Types._Result, ValidityPackage.Literals.RESULT__VALIDATABLE_NODE, Types._ValidatableNode, 8 | IsDerived | IsReadOnly | IsRequired | IsTransient | IsVolatile);
		public static final @NonNull Property _Result__AbstractNode__worstResult = LIBRARY.createOppositeProperty(Types._Result, "AbstractNode", MODEL.getCollectionType(OCLstdlibTables.Types._Bag, Types._AbstractNode, false), 9 | IsImplicit | IsRequired | IsResolveProxies);

		public static final @NonNull Property _ResultConstrainingNode__resultValidatableNode = LIBRARY.createProperty(Types._ResultConstrainingNode, ValidityPackage.Literals.RESULT_CONSTRAINING_NODE__RESULT_VALIDATABLE_NODE, Types._ResultValidatableNode, 0 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ResultConstrainingNode__Result__resultConstrainingNode = LIBRARY.createOppositeProperty(Types._ResultConstrainingNode, "Result", MODEL.getCollectionType(OCLstdlibTables.Types._Bag, Types._Result, false), 1 | IsImplicit | IsRequired | IsResolveProxies);

		public static final @NonNull Property _ResultSet__name = LIBRARY.createProperty(Types._ResultSet, ValidityPackage.Literals.RESULT_SET__NAME, OCLstdlibTables.Types._String, 0 | IsReadOnly | IsRequired | IsResolveProxies | IsTransient | IsVolatile);
		public static final @NonNull Property _ResultSet__results = LIBRARY.createProperty(Types._ResultSet, ValidityPackage.Literals.RESULT_SET__RESULTS, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._Result, false), 1 | IsComposite | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ResultSet__root = LIBRARY.createProperty(Types._ResultSet, ValidityPackage.Literals.RESULT_SET__ROOT, Types._RootNode, 2 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ResultSet__timestamp = LIBRARY.createProperty(Types._ResultSet, ValidityPackage.Literals.RESULT_SET__TIMESTAMP, OCLstdlibTables.Types._String, 3 | IsResolveProxies);

		public static final @NonNull Property _ResultValidatableNode__resultConstrainingNode = LIBRARY.createProperty(Types._ResultValidatableNode, ValidityPackage.Literals.RESULT_VALIDATABLE_NODE__RESULT_CONSTRAINING_NODE, Types._ResultConstrainingNode, 0 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ResultValidatableNode__Result__resultValidatableNode = LIBRARY.createOppositeProperty(Types._ResultValidatableNode, "Result", MODEL.getCollectionType(OCLstdlibTables.Types._Bag, Types._Result, false), 1 | IsImplicit | IsRequired | IsResolveProxies);

		public static final @NonNull Property _RootConstrainingNode__rootNode = LIBRARY.createProperty(Types._RootConstrainingNode, ValidityPackage.Literals.ROOT_CONSTRAINING_NODE__ROOT_NODE, Types._RootNode, 0 | IsRequired | IsResolveProxies);

		public static final @NonNull Property _RootNode__constrainingNodes = LIBRARY.createProperty(Types._RootNode, ValidityPackage.Literals.ROOT_NODE__CONSTRAINING_NODES, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._RootConstrainingNode, false), 0 | IsComposite | IsRequired | IsResolveProxies);
		public static final @NonNull Property _RootNode__resultSets = LIBRARY.createProperty(Types._RootNode, ValidityPackage.Literals.ROOT_NODE__RESULT_SETS, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._ResultSet, false), 1 | IsComposite | IsRequired | IsResolveProxies);
		public static final @NonNull Property _RootNode__validatableNodes = LIBRARY.createProperty(Types._RootNode, ValidityPackage.Literals.ROOT_NODE__VALIDATABLE_NODES, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._RootValidatableNode, false), 2 | IsComposite | IsRequired | IsResolveProxies);

		public static final @NonNull Property _RootValidatableNode__rootNode = LIBRARY.createProperty(Types._RootValidatableNode, ValidityPackage.Literals.ROOT_VALIDATABLE_NODE__ROOT_NODE, Types._RootNode, 0 | IsRequired | IsResolveProxies);

		public static final @NonNull Property _ValidatableNode__children = LIBRARY.createProperty(Types._ValidatableNode, ValidityPackage.Literals.VALIDATABLE_NODE__CHILDREN, MODEL.getCollectionType(OCLstdlibTables.Types._Set, Types._ValidatableNode, false), 0 | IsComposite | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ValidatableNode__constrainedObject = LIBRARY.createProperty(Types._ValidatableNode, ValidityPackage.Literals.VALIDATABLE_NODE__CONSTRAINED_OBJECT, getASClass(EcorePackage.Literals.EOBJECT), 1 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _ValidatableNode__parent = LIBRARY.createProperty(Types._ValidatableNode, ValidityPackage.Literals.VALIDATABLE_NODE__PARENT, Types._ValidatableNode, 2 | IsResolveProxies);
		public static final @NonNull Property _ValidatableNode__Result__validatableNode = LIBRARY.createOppositeProperty(Types._ValidatableNode, "Result", MODEL.getCollectionType(OCLstdlibTables.Types._Bag, Types._Result, false), 3 | IsImplicit | IsRequired | IsResolveProxies);

		static {
			_AbstractNode__enabled.setDefaultValueString("true");
			_AbstractNode__grayed.setDefaultValueString("false");
			_AbstractNode__label.setDefaultValueString("");
			_AbstractNode__visible.setDefaultValueString("true");
			_AbstractNode__worstResult.setOpposite(_Result__AbstractNode__worstResult);

			_ConstrainingNode__children.setOpposite(_ConstrainingNode__parent);
			_ConstrainingNode__parent.setOpposite(_ConstrainingNode__children);

			_LeafConstrainingNode__Result__leafConstrainingNode.setOpposite(_Result__leafConstrainingNode);

			_Result__leafConstrainingNode.setOpposite(_LeafConstrainingNode__Result__leafConstrainingNode);
			_Result__resultConstrainingNode.setOpposite(_ResultConstrainingNode__Result__resultConstrainingNode);
			_Result__resultSet.setOpposite(_ResultSet__results);
			_Result__resultValidatableNode.setOpposite(_ResultValidatableNode__Result__resultValidatableNode);
			_Result__severity.setDefaultValueString("UNKNOWN");
			_Result__validatableNode.setOpposite(_ValidatableNode__Result__validatableNode);
			_Result__AbstractNode__worstResult.setOpposite(_AbstractNode__worstResult);

			_ResultConstrainingNode__resultValidatableNode.setOpposite(_ResultValidatableNode__resultConstrainingNode);
			_ResultConstrainingNode__Result__resultConstrainingNode.setOpposite(_Result__resultConstrainingNode);

			_ResultSet__results.setOpposite(_Result__resultSet);
			_ResultSet__root.setOpposite(_RootNode__resultSets);

			_ResultValidatableNode__resultConstrainingNode.setOpposite(_ResultConstrainingNode__resultValidatableNode);
			_ResultValidatableNode__Result__resultValidatableNode.setOpposite(_Result__resultValidatableNode);

			_RootConstrainingNode__rootNode.setOpposite(_RootNode__constrainingNodes);

			_RootNode__constrainingNodes.setOpposite(_RootConstrainingNode__rootNode);
			_RootNode__resultSets.setOpposite(_ResultSet__root);
			_RootNode__validatableNodes.setOpposite(_RootValidatableNode__rootNode);

			_RootValidatableNode__rootNode.setOpposite(_RootNode__validatableNodes);

			_ValidatableNode__children.setOpposite(_ValidatableNode__parent);
			LIBRARY.createOpposite("ValidatableNode", _ValidatableNode__constrainedObject);
			_ValidatableNode__parent.setOpposite(_ValidatableNode__children);
			_ValidatableNode__Result__validatableNode.setOpposite(_Result__validatableNode);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final @NonNull FlatFragment @NonNull [] _AbstractNode =
			{
				Fragments._AbstractNode__OclAny /* 0 */,
				Fragments._AbstractNode__OclElement /* 1 */,
				Fragments._AbstractNode__AbstractNode /* 2 */
			};
		private static final int @NonNull [] __AbstractNode = { 0,1,2 };

		private static final @NonNull FlatFragment @NonNull [] _ConstrainingNode =
			{
				Fragments._ConstrainingNode__OclAny /* 0 */,
				Fragments._ConstrainingNode__OclElement /* 1 */,
				Fragments._ConstrainingNode__AbstractNode /* 2 */,
				Fragments._ConstrainingNode__ConstrainingNode /* 3 */
			};
		private static final int @NonNull [] __ConstrainingNode = { 0,1,2,3 };

		private static final @NonNull FlatFragment @NonNull [] _ConstraintLocator =
			{
				Fragments._ConstraintLocator__OclAny /* 0 */,
				Fragments._ConstraintLocator__ConstraintLocator /* 1 */
			};
		private static final int @NonNull [] __ConstraintLocator = { 0,1 };

		private static final @NonNull FlatFragment @NonNull [] _LeafConstrainingNode =
			{
				Fragments._LeafConstrainingNode__OclAny /* 0 */,
				Fragments._LeafConstrainingNode__OclElement /* 1 */,
				Fragments._LeafConstrainingNode__AbstractNode /* 2 */,
				Fragments._LeafConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._LeafConstrainingNode__LeafConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __LeafConstrainingNode = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _Object =
			{
				Fragments._Object__OclAny /* 0 */,
				Fragments._Object__Object /* 1 */
			};
		private static final int @NonNull [] __Object = { 0,1 };

		private static final @NonNull FlatFragment @NonNull [] _Resource =
			{
				Fragments._Resource__OclAny /* 0 */,
				Fragments._Resource__Resource /* 1 */
			};
		private static final int @NonNull [] __Resource = { 0,1 };

		private static final @NonNull FlatFragment @NonNull [] _Result =
			{
				Fragments._Result__OclAny /* 0 */,
				Fragments._Result__OclElement /* 1 */,
				Fragments._Result__Result /* 2 */
			};
		private static final int @NonNull [] __Result = { 0,1,2 };

		private static final @NonNull FlatFragment @NonNull [] _ResultConstrainingNode =
			{
				Fragments._ResultConstrainingNode__OclAny /* 0 */,
				Fragments._ResultConstrainingNode__OclElement /* 1 */,
				Fragments._ResultConstrainingNode__AbstractNode /* 2 */,
				Fragments._ResultConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._ResultConstrainingNode__ResultConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __ResultConstrainingNode = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _ResultSet =
			{
				Fragments._ResultSet__OclAny /* 0 */,
				Fragments._ResultSet__OclElement /* 1 */,
				Fragments._ResultSet__ResultSet /* 2 */
			};
		private static final int @NonNull [] __ResultSet = { 0,1,2 };

		private static final @NonNull FlatFragment @NonNull [] _ResultValidatableNode =
			{
				Fragments._ResultValidatableNode__OclAny /* 0 */,
				Fragments._ResultValidatableNode__OclElement /* 1 */,
				Fragments._ResultValidatableNode__AbstractNode /* 2 */,
				Fragments._ResultValidatableNode__ValidatableNode /* 3 */,
				Fragments._ResultValidatableNode__ResultValidatableNode /* 4 */
			};
		private static final int @NonNull [] __ResultValidatableNode = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _RootConstrainingNode =
			{
				Fragments._RootConstrainingNode__OclAny /* 0 */,
				Fragments._RootConstrainingNode__OclElement /* 1 */,
				Fragments._RootConstrainingNode__AbstractNode /* 2 */,
				Fragments._RootConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._RootConstrainingNode__RootConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __RootConstrainingNode = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _RootNode =
			{
				Fragments._RootNode__OclAny /* 0 */,
				Fragments._RootNode__OclElement /* 1 */,
				Fragments._RootNode__RootNode /* 2 */
			};
		private static final int @NonNull [] __RootNode = { 0,1,2 };

		private static final @NonNull FlatFragment @NonNull [] _RootValidatableNode =
			{
				Fragments._RootValidatableNode__OclAny /* 0 */,
				Fragments._RootValidatableNode__OclElement /* 1 */,
				Fragments._RootValidatableNode__AbstractNode /* 2 */,
				Fragments._RootValidatableNode__ValidatableNode /* 3 */,
				Fragments._RootValidatableNode__RootValidatableNode /* 4 */
			};
		private static final int @NonNull [] __RootValidatableNode = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _Severity =
			{
				Fragments._Severity__OclAny /* 0 */,
				Fragments._Severity__OclElement /* 1 */,
				Fragments._Severity__OclType /* 2 */,
				Fragments._Severity__OclEnumeration /* 3 */,
				Fragments._Severity__Severity /* 4 */
			};
		private static final int @NonNull [] __Severity = { 0,1,2,3,4 };

		private static final @NonNull FlatFragment @NonNull [] _Throwable =
			{
				Fragments._Throwable__OclAny /* 0 */,
				Fragments._Throwable__Throwable /* 1 */
			};
		private static final int @NonNull [] __Throwable = { 0,1 };

		private static final @NonNull FlatFragment @NonNull [] _ValidatableNode =
			{
				Fragments._ValidatableNode__OclAny /* 0 */,
				Fragments._ValidatableNode__OclElement /* 1 */,
				Fragments._ValidatableNode__AbstractNode /* 2 */,
				Fragments._ValidatableNode__ValidatableNode /* 3 */
			};
		private static final int @NonNull [] __ValidatableNode = { 0,1,2,3 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._AbstractNode.initFragments(_AbstractNode, __AbstractNode);
			Types._ConstrainingNode.initFragments(_ConstrainingNode, __ConstrainingNode);
			Types._ConstraintLocator.initFragments(_ConstraintLocator, __ConstraintLocator);
			Types._LeafConstrainingNode.initFragments(_LeafConstrainingNode, __LeafConstrainingNode);
			Types._Object.initFragments(_Object, __Object);
			Types._Resource.initFragments(_Resource, __Resource);
			Types._Result.initFragments(_Result, __Result);
			Types._ResultConstrainingNode.initFragments(_ResultConstrainingNode, __ResultConstrainingNode);
			Types._ResultSet.initFragments(_ResultSet, __ResultSet);
			Types._ResultValidatableNode.initFragments(_ResultValidatableNode, __ResultValidatableNode);
			Types._RootConstrainingNode.initFragments(_RootConstrainingNode, __RootConstrainingNode);
			Types._RootNode.initFragments(_RootNode, __RootNode);
			Types._RootValidatableNode.initFragments(_RootValidatableNode, __RootValidatableNode);
			Types._Severity.initFragments(_Severity, __Severity);
			Types._Throwable.initFragments(_Throwable, __Throwable);
			Types._ValidatableNode.initFragments(_ValidatableNode, __ValidatableNode);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final @NonNull Operation @NonNull [] _AbstractNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _AbstractNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _AbstractNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _ConstrainingNode__ConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _ConstrainingNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _ConstrainingNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _ConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _ConstraintLocator__ConstraintLocator = {};
		private static final @NonNull Operation @NonNull [] _ConstraintLocator__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _LeafConstrainingNode__LeafConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _LeafConstrainingNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _LeafConstrainingNode__ConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _LeafConstrainingNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _LeafConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _Object__Object = {};
		private static final @NonNull Operation @NonNull [] _Object__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _Resource__Resource = {};
		private static final @NonNull Operation @NonNull [] _Resource__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _Result__Result = {};
		private static final @NonNull Operation @NonNull [] _Result__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _Result__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _ResultConstrainingNode__ResultConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _ResultConstrainingNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _ResultConstrainingNode__ConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _ResultConstrainingNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _ResultConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _ResultSet__ResultSet = {};
		private static final @NonNull Operation @NonNull [] _ResultSet__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _ResultSet__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _ResultValidatableNode__ResultValidatableNode = {};
		private static final @NonNull Operation @NonNull [] _ResultValidatableNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _ResultValidatableNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _ResultValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _ResultValidatableNode__ValidatableNode = {};

		private static final @NonNull Operation @NonNull [] _RootConstrainingNode__RootConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _RootConstrainingNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _RootConstrainingNode__ConstrainingNode = {};
		private static final @NonNull Operation @NonNull [] _RootConstrainingNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _RootConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _RootNode__RootNode = {};
		private static final @NonNull Operation @NonNull [] _RootNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _RootNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _RootValidatableNode__RootValidatableNode = {};
		private static final @NonNull Operation @NonNull [] _RootValidatableNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _RootValidatableNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _RootValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _RootValidatableNode__ValidatableNode = {};

		private static final @NonNull Operation @NonNull [] _Severity__Severity = {};
		private static final @NonNull Operation @NonNull [] _Severity__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _Severity__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _Severity__OclEnumeration = {};
		private static final @NonNull Operation @NonNull [] _Severity__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};

		private static final @NonNull Operation @NonNull [] _Throwable__Throwable = {};
		private static final @NonNull Operation @NonNull [] _Throwable__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _ValidatableNode__ValidatableNode = {};
		private static final @NonNull Operation @NonNull [] _ValidatableNode__AbstractNode = {
			Operations._AbstractNode__getChildren /* getChildren() */,
			Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull Operation @NonNull [] _ValidatableNode__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _ValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AbstractNode__AbstractNode.initOperations(_AbstractNode__AbstractNode);
			Fragments._AbstractNode__OclAny.initOperations(_AbstractNode__OclAny);
			Fragments._AbstractNode__OclElement.initOperations(_AbstractNode__OclElement);

			Fragments._ConstrainingNode__AbstractNode.initOperations(_ConstrainingNode__AbstractNode);
			Fragments._ConstrainingNode__ConstrainingNode.initOperations(_ConstrainingNode__ConstrainingNode);
			Fragments._ConstrainingNode__OclAny.initOperations(_ConstrainingNode__OclAny);
			Fragments._ConstrainingNode__OclElement.initOperations(_ConstrainingNode__OclElement);

			Fragments._ConstraintLocator__ConstraintLocator.initOperations(_ConstraintLocator__ConstraintLocator);
			Fragments._ConstraintLocator__OclAny.initOperations(_ConstraintLocator__OclAny);

			Fragments._LeafConstrainingNode__AbstractNode.initOperations(_LeafConstrainingNode__AbstractNode);
			Fragments._LeafConstrainingNode__ConstrainingNode.initOperations(_LeafConstrainingNode__ConstrainingNode);
			Fragments._LeafConstrainingNode__LeafConstrainingNode.initOperations(_LeafConstrainingNode__LeafConstrainingNode);
			Fragments._LeafConstrainingNode__OclAny.initOperations(_LeafConstrainingNode__OclAny);
			Fragments._LeafConstrainingNode__OclElement.initOperations(_LeafConstrainingNode__OclElement);

			Fragments._Object__Object.initOperations(_Object__Object);
			Fragments._Object__OclAny.initOperations(_Object__OclAny);

			Fragments._Resource__OclAny.initOperations(_Resource__OclAny);
			Fragments._Resource__Resource.initOperations(_Resource__Resource);

			Fragments._Result__OclAny.initOperations(_Result__OclAny);
			Fragments._Result__OclElement.initOperations(_Result__OclElement);
			Fragments._Result__Result.initOperations(_Result__Result);

			Fragments._ResultConstrainingNode__AbstractNode.initOperations(_ResultConstrainingNode__AbstractNode);
			Fragments._ResultConstrainingNode__ConstrainingNode.initOperations(_ResultConstrainingNode__ConstrainingNode);
			Fragments._ResultConstrainingNode__OclAny.initOperations(_ResultConstrainingNode__OclAny);
			Fragments._ResultConstrainingNode__OclElement.initOperations(_ResultConstrainingNode__OclElement);
			Fragments._ResultConstrainingNode__ResultConstrainingNode.initOperations(_ResultConstrainingNode__ResultConstrainingNode);

			Fragments._ResultSet__OclAny.initOperations(_ResultSet__OclAny);
			Fragments._ResultSet__OclElement.initOperations(_ResultSet__OclElement);
			Fragments._ResultSet__ResultSet.initOperations(_ResultSet__ResultSet);

			Fragments._ResultValidatableNode__AbstractNode.initOperations(_ResultValidatableNode__AbstractNode);
			Fragments._ResultValidatableNode__OclAny.initOperations(_ResultValidatableNode__OclAny);
			Fragments._ResultValidatableNode__OclElement.initOperations(_ResultValidatableNode__OclElement);
			Fragments._ResultValidatableNode__ResultValidatableNode.initOperations(_ResultValidatableNode__ResultValidatableNode);
			Fragments._ResultValidatableNode__ValidatableNode.initOperations(_ResultValidatableNode__ValidatableNode);

			Fragments._RootConstrainingNode__AbstractNode.initOperations(_RootConstrainingNode__AbstractNode);
			Fragments._RootConstrainingNode__ConstrainingNode.initOperations(_RootConstrainingNode__ConstrainingNode);
			Fragments._RootConstrainingNode__OclAny.initOperations(_RootConstrainingNode__OclAny);
			Fragments._RootConstrainingNode__OclElement.initOperations(_RootConstrainingNode__OclElement);
			Fragments._RootConstrainingNode__RootConstrainingNode.initOperations(_RootConstrainingNode__RootConstrainingNode);

			Fragments._RootNode__OclAny.initOperations(_RootNode__OclAny);
			Fragments._RootNode__OclElement.initOperations(_RootNode__OclElement);
			Fragments._RootNode__RootNode.initOperations(_RootNode__RootNode);

			Fragments._RootValidatableNode__AbstractNode.initOperations(_RootValidatableNode__AbstractNode);
			Fragments._RootValidatableNode__OclAny.initOperations(_RootValidatableNode__OclAny);
			Fragments._RootValidatableNode__OclElement.initOperations(_RootValidatableNode__OclElement);
			Fragments._RootValidatableNode__RootValidatableNode.initOperations(_RootValidatableNode__RootValidatableNode);
			Fragments._RootValidatableNode__ValidatableNode.initOperations(_RootValidatableNode__ValidatableNode);

			Fragments._Severity__OclAny.initOperations(_Severity__OclAny);
			Fragments._Severity__OclElement.initOperations(_Severity__OclElement);
			Fragments._Severity__OclEnumeration.initOperations(_Severity__OclEnumeration);
			Fragments._Severity__OclType.initOperations(_Severity__OclType);
			Fragments._Severity__Severity.initOperations(_Severity__Severity);

			Fragments._Throwable__OclAny.initOperations(_Throwable__OclAny);
			Fragments._Throwable__Throwable.initOperations(_Throwable__Throwable);

			Fragments._ValidatableNode__AbstractNode.initOperations(_ValidatableNode__AbstractNode);
			Fragments._ValidatableNode__OclAny.initOperations(_ValidatableNode__OclAny);
			Fragments._ValidatableNode__OclElement.initOperations(_ValidatableNode__OclElement);
			Fragments._ValidatableNode__ValidatableNode.initOperations(_ValidatableNode__ValidatableNode);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::FragmentOperations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final @NonNull Property @NonNull [] _AbstractNode = {
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _ConstrainingNode = {
			Properties._ConstrainingNode__children,
			Properties._ConstrainingNode__constrainingObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ConstrainingNode__parent,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _ConstraintLocator = {};

		private static final @NonNull Property @NonNull [] _LeafConstrainingNode = {
			Properties._ConstrainingNode__children,
			Properties._ConstrainingNode__constrainingObject,
			Properties._LeafConstrainingNode__constraintLocator,
			Properties._LeafConstrainingNode__constraintResource,
			Properties._LeafConstrainingNode__constraintString,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ConstrainingNode__parent,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _Object = {};

		private static final @NonNull Property @NonNull [] _Resource = {};

		private static final @NonNull Property @NonNull [] _Result = {
			Properties._Result__diagnostic,
			Properties._Result__exception,
			Properties._Result__leafConstrainingNode,
			Properties._Result__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._Result__resultConstrainingNode,
			Properties._Result__resultSet,
			Properties._Result__resultValidatableNode,
			Properties._Result__severity,
			Properties._Result__validatableNode
		};

		private static final @NonNull Property @NonNull [] _ResultConstrainingNode = {
			Properties._ConstrainingNode__children,
			Properties._ConstrainingNode__constrainingObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ConstrainingNode__parent,
			Properties._ResultConstrainingNode__resultValidatableNode,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _ResultSet = {
			Properties._ResultSet__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ResultSet__results,
			Properties._ResultSet__root,
			Properties._ResultSet__timestamp
		};

		private static final @NonNull Property @NonNull [] _ResultValidatableNode = {
			Properties._ValidatableNode__children,
			Properties._ValidatableNode__constrainedObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ValidatableNode__parent,
			Properties._ResultValidatableNode__resultConstrainingNode,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _RootConstrainingNode = {
			Properties._ConstrainingNode__children,
			Properties._ConstrainingNode__constrainingObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ConstrainingNode__parent,
			Properties._RootConstrainingNode__rootNode,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _RootNode = {
			Properties._RootNode__constrainingNodes,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._RootNode__resultSets,
			Properties._RootNode__validatableNodes
		};

		private static final @NonNull Property @NonNull [] _RootValidatableNode = {
			Properties._ValidatableNode__children,
			Properties._ValidatableNode__constrainedObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ValidatableNode__parent,
			Properties._RootValidatableNode__rootNode,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		private static final @NonNull Property @NonNull [] _Severity = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _Throwable = {};

		private static final @NonNull Property @NonNull [] _ValidatableNode = {
			Properties._ValidatableNode__children,
			Properties._ValidatableNode__constrainedObject,
			Properties._AbstractNode__enabled,
			Properties._AbstractNode__grayed,
			Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._ValidatableNode__parent,
			Properties._AbstractNode__visible,
			Properties._AbstractNode__worstResult
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AbstractNode__AbstractNode.initProperties(_AbstractNode);
			Fragments._ConstrainingNode__ConstrainingNode.initProperties(_ConstrainingNode);
			Fragments._ConstraintLocator__ConstraintLocator.initProperties(_ConstraintLocator);
			Fragments._LeafConstrainingNode__LeafConstrainingNode.initProperties(_LeafConstrainingNode);
			Fragments._Object__Object.initProperties(_Object);
			Fragments._Resource__Resource.initProperties(_Resource);
			Fragments._Result__Result.initProperties(_Result);
			Fragments._ResultConstrainingNode__ResultConstrainingNode.initProperties(_ResultConstrainingNode);
			Fragments._ResultSet__ResultSet.initProperties(_ResultSet);
			Fragments._ResultValidatableNode__ResultValidatableNode.initProperties(_ResultValidatableNode);
			Fragments._RootConstrainingNode__RootConstrainingNode.initProperties(_RootConstrainingNode);
			Fragments._RootNode__RootNode.initProperties(_RootNode);
			Fragments._RootValidatableNode__RootValidatableNode.initProperties(_RootValidatableNode);
			Fragments._Severity__Severity.initProperties(_Severity);
			Fragments._Throwable__Throwable.initProperties(_Throwable);
			Fragments._ValidatableNode__ValidatableNode.initProperties(_ValidatableNode);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::FragmentProperties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		public static final @NonNull EnumerationLiteral _Severity__UNKNOWN = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("UNKNOWN"), Types._Severity, 0);
		public static final @NonNull EnumerationLiteral _Severity__OK = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("OK"), Types._Severity, 1);
		public static final @NonNull EnumerationLiteral _Severity__INFO = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("INFO"), Types._Severity, 2);
		public static final @NonNull EnumerationLiteral _Severity__WARNING = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("WARNING"), Types._Severity, 3);
		public static final @NonNull EnumerationLiteral _Severity__ERROR = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("ERROR"), Types._Severity, 4);
		public static final @NonNull EnumerationLiteral _Severity__FATAL = LIBRARY.createEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("FATAL"), Types._Severity, 5);
		private static final @NonNull EnumerationLiteral @NonNull [] _Severity = {
			_Severity__UNKNOWN,
			_Severity__OK,
			_Severity__INFO,
			_Severity__WARNING,
			_Severity__ERROR,
			_Severity__FATAL
		};

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {
			LIBRARY.initLiterals(Types._Severity, _Severity);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of ValidityTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that
	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.
	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.
	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.
	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so
	 * ensure that residual construction occurs just once after all predecessors.
	 */
	private static class Init {
		/**
		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.
		 */
		private static int initCount = 0;

		/**
		 * Invoked at the start of a static construction to defer residual construction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual construction once primary constructions complete.
		 */
		private static void initEnd() {
			if (initCount > 0) {
				if (--initCount == 0) {
					initCount = -1;
					EnumerationLiterals.init();
					LIBRARY.freeze(RESOURCE);
				}
			}
		}
	}

	static {
		Init.initEnd();
	}

	/*
	 * Force initialization of outer fields. Inner fields are lazily initialized.
	 */
	public static void init() {
		new ValidityTables();
	}

	private ValidityTables() {
		super(ValidityPackage.eNS_URI);
	}
}
