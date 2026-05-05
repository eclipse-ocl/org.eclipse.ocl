/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.examples.emf.validation.validity/model/Validity.ecore
 * using:
 *   /org.eclipse.ocl.examples.emf.validation.validity/model/Validity.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity;

import java.lang.String;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
// import org.eclipse.ocl.examples.emf.validation.validity.ValidityPackage;
// import org.eclipse.ocl.examples.emf.validation.validity.ValidityTables;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumeration;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumerationLiteral;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreLibraryOppositeProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
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
	 *	The package descriptor for the package.
	 */
	public static final @NonNull EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(ValidityPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final @NonNull ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_validation_s_2013_s_Validity = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/validation/2013/Validity", null, ValidityPackage.eINSTANCE);
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
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_AbstractNode = TypeId.ORDERED_SET.getSpecializedId(ValidityTables.CLSSid_AbstractNode, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ConstrainingNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ConstrainingNode, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_Result = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_Result, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ResultSet = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ResultSet, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_RootConstrainingNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_RootConstrainingNode, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_RootValidatableNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_RootValidatableNode, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_ValidatableNode = TypeId.SET.getSpecializedId(ValidityTables.CLSSid_ValidatableNode, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);

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

		public static final @NonNull EcoreExecutorType _AbstractNode = new EcoreExecutorType(ValidityPackage.Literals.ABSTRACT_NODE, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final @NonNull EcoreExecutorType _ConstrainingNode = new EcoreExecutorType(ValidityPackage.Literals.CONSTRAINING_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ConstraintLocator = new EcoreExecutorType("ConstraintLocator", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _LeafConstrainingNode = new EcoreExecutorType(ValidityPackage.Literals.LEAF_CONSTRAINING_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Object = new EcoreExecutorType("Object", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Resource = new EcoreExecutorType("Resource", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Result = new EcoreExecutorType(ValidityPackage.Literals.RESULT, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ResultConstrainingNode = new EcoreExecutorType(ValidityPackage.Literals.RESULT_CONSTRAINING_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ResultSet = new EcoreExecutorType(ValidityPackage.Literals.RESULT_SET, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ResultValidatableNode = new EcoreExecutorType(ValidityPackage.Literals.RESULT_VALIDATABLE_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _RootConstrainingNode = new EcoreExecutorType(ValidityPackage.Literals.ROOT_CONSTRAINING_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _RootNode = new EcoreExecutorType(ValidityPackage.Literals.ROOT_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _RootValidatableNode = new EcoreExecutorType(ValidityPackage.Literals.ROOT_VALIDATABLE_NODE, PACKAGE, 0);
		public static final @NonNull EcoreExecutorEnumeration _Severity = new EcoreExecutorEnumeration(ValidityPackage.Literals.SEVERITY, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Throwable = new EcoreExecutorType("Throwable", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ValidatableNode = new EcoreExecutorType(ValidityPackage.Literals.VALIDATABLE_NODE, PACKAGE, 0);

		private static final @NonNull EcoreExecutorType @NonNull [] types = {
			_AbstractNode,
			_ConstrainingNode,
			_ConstraintLocator,
			_LeafConstrainingNode,
			_Object,
			_Resource,
			_Result,
			_ResultConstrainingNode,
			_ResultSet,
			_ResultValidatableNode,
			_RootConstrainingNode,
			_RootNode,
			_RootValidatableNode,
			_Severity,
			_Throwable,
			_ValidatableNode
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
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

		private static final @NonNull ExecutorFragment _AbstractNode__AbstractNode = new ExecutorFragment(Types._AbstractNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _AbstractNode__OclAny = new ExecutorFragment(Types._AbstractNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _AbstractNode__OclElement = new ExecutorFragment(Types._AbstractNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull ExecutorFragment _ConstrainingNode__AbstractNode = new ExecutorFragment(Types._ConstrainingNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _ConstrainingNode__ConstrainingNode = new ExecutorFragment(Types._ConstrainingNode, ValidityTables.Types._ConstrainingNode);
		private static final @NonNull ExecutorFragment _ConstrainingNode__OclAny = new ExecutorFragment(Types._ConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ConstrainingNode__OclElement = new ExecutorFragment(Types._ConstrainingNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull ExecutorFragment _ConstraintLocator__ConstraintLocator = new ExecutorFragment(Types._ConstraintLocator, ValidityTables.Types._ConstraintLocator);
		private static final @NonNull ExecutorFragment _ConstraintLocator__OclAny = new ExecutorFragment(Types._ConstraintLocator, OCLstdlibTables.Types._OclAny);

		private static final @NonNull ExecutorFragment _LeafConstrainingNode__AbstractNode = new ExecutorFragment(Types._LeafConstrainingNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _LeafConstrainingNode__ConstrainingNode = new ExecutorFragment(Types._LeafConstrainingNode, ValidityTables.Types._ConstrainingNode);
		private static final @NonNull ExecutorFragment _LeafConstrainingNode__LeafConstrainingNode = new ExecutorFragment(Types._LeafConstrainingNode, ValidityTables.Types._LeafConstrainingNode);
		private static final @NonNull ExecutorFragment _LeafConstrainingNode__OclAny = new ExecutorFragment(Types._LeafConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _LeafConstrainingNode__OclElement = new ExecutorFragment(Types._LeafConstrainingNode, OCLstdlibTables.Types._OclElement);

		private static final @NonNull ExecutorFragment _Object__Object = new ExecutorFragment(Types._Object, ValidityTables.Types._Object);
		private static final @NonNull ExecutorFragment _Object__OclAny = new ExecutorFragment(Types._Object, OCLstdlibTables.Types._OclAny);

		private static final @NonNull ExecutorFragment _Resource__OclAny = new ExecutorFragment(Types._Resource, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Resource__Resource = new ExecutorFragment(Types._Resource, ValidityTables.Types._Resource);

		private static final @NonNull ExecutorFragment _Result__OclAny = new ExecutorFragment(Types._Result, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Result__OclElement = new ExecutorFragment(Types._Result, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _Result__Result = new ExecutorFragment(Types._Result, ValidityTables.Types._Result);

		private static final @NonNull ExecutorFragment _ResultConstrainingNode__AbstractNode = new ExecutorFragment(Types._ResultConstrainingNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _ResultConstrainingNode__ConstrainingNode = new ExecutorFragment(Types._ResultConstrainingNode, ValidityTables.Types._ConstrainingNode);
		private static final @NonNull ExecutorFragment _ResultConstrainingNode__OclAny = new ExecutorFragment(Types._ResultConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ResultConstrainingNode__OclElement = new ExecutorFragment(Types._ResultConstrainingNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ResultConstrainingNode__ResultConstrainingNode = new ExecutorFragment(Types._ResultConstrainingNode, ValidityTables.Types._ResultConstrainingNode);

		private static final @NonNull ExecutorFragment _ResultSet__OclAny = new ExecutorFragment(Types._ResultSet, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ResultSet__OclElement = new ExecutorFragment(Types._ResultSet, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ResultSet__ResultSet = new ExecutorFragment(Types._ResultSet, ValidityTables.Types._ResultSet);

		private static final @NonNull ExecutorFragment _ResultValidatableNode__AbstractNode = new ExecutorFragment(Types._ResultValidatableNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _ResultValidatableNode__OclAny = new ExecutorFragment(Types._ResultValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ResultValidatableNode__OclElement = new ExecutorFragment(Types._ResultValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ResultValidatableNode__ResultValidatableNode = new ExecutorFragment(Types._ResultValidatableNode, ValidityTables.Types._ResultValidatableNode);
		private static final @NonNull ExecutorFragment _ResultValidatableNode__ValidatableNode = new ExecutorFragment(Types._ResultValidatableNode, ValidityTables.Types._ValidatableNode);

		private static final @NonNull ExecutorFragment _RootConstrainingNode__AbstractNode = new ExecutorFragment(Types._RootConstrainingNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _RootConstrainingNode__ConstrainingNode = new ExecutorFragment(Types._RootConstrainingNode, ValidityTables.Types._ConstrainingNode);
		private static final @NonNull ExecutorFragment _RootConstrainingNode__OclAny = new ExecutorFragment(Types._RootConstrainingNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _RootConstrainingNode__OclElement = new ExecutorFragment(Types._RootConstrainingNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _RootConstrainingNode__RootConstrainingNode = new ExecutorFragment(Types._RootConstrainingNode, ValidityTables.Types._RootConstrainingNode);

		private static final @NonNull ExecutorFragment _RootNode__OclAny = new ExecutorFragment(Types._RootNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _RootNode__OclElement = new ExecutorFragment(Types._RootNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _RootNode__RootNode = new ExecutorFragment(Types._RootNode, ValidityTables.Types._RootNode);

		private static final @NonNull ExecutorFragment _RootValidatableNode__AbstractNode = new ExecutorFragment(Types._RootValidatableNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _RootValidatableNode__OclAny = new ExecutorFragment(Types._RootValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _RootValidatableNode__OclElement = new ExecutorFragment(Types._RootValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _RootValidatableNode__RootValidatableNode = new ExecutorFragment(Types._RootValidatableNode, ValidityTables.Types._RootValidatableNode);
		private static final @NonNull ExecutorFragment _RootValidatableNode__ValidatableNode = new ExecutorFragment(Types._RootValidatableNode, ValidityTables.Types._ValidatableNode);

		private static final @NonNull ExecutorFragment _Severity__OclAny = new ExecutorFragment(Types._Severity, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Severity__OclElement = new ExecutorFragment(Types._Severity, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _Severity__OclEnumeration = new ExecutorFragment(Types._Severity, OCLstdlibTables.Types._OclEnumeration);
		private static final @NonNull ExecutorFragment _Severity__OclType = new ExecutorFragment(Types._Severity, OCLstdlibTables.Types._OclType);
		private static final @NonNull ExecutorFragment _Severity__Severity = new ExecutorFragment(Types._Severity, ValidityTables.Types._Severity);

		private static final @NonNull ExecutorFragment _Throwable__OclAny = new ExecutorFragment(Types._Throwable, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Throwable__Throwable = new ExecutorFragment(Types._Throwable, ValidityTables.Types._Throwable);

		private static final @NonNull ExecutorFragment _ValidatableNode__AbstractNode = new ExecutorFragment(Types._ValidatableNode, ValidityTables.Types._AbstractNode);
		private static final @NonNull ExecutorFragment _ValidatableNode__OclAny = new ExecutorFragment(Types._ValidatableNode, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ValidatableNode__OclElement = new ExecutorFragment(Types._ValidatableNode, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ValidatableNode__ValidatableNode = new ExecutorFragment(Types._ValidatableNode, ValidityTables.Types._ValidatableNode);

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

		public static final @NonNull ExecutorOperation _AbstractNode__getChildren = new ExecutorOperation("getChildren", TypeUtil.EMPTY_PARAMETER_TYPES, Types._AbstractNode,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull ExecutorOperation _AbstractNode__getParent = new ExecutorOperation("getParent", TypeUtil.EMPTY_PARAMETER_TYPES, Types._AbstractNode,
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

		public static final @NonNull ExecutorProperty _AbstractNode__enabled = new EcoreExecutorProperty(ValidityPackage.Literals.ABSTRACT_NODE__ENABLED, Types._AbstractNode, 0);
		public static final @NonNull ExecutorProperty _AbstractNode__grayed = new EcoreExecutorProperty(ValidityPackage.Literals.ABSTRACT_NODE__GRAYED, Types._AbstractNode, 1);
		public static final @NonNull ExecutorProperty _AbstractNode__label = new EcoreExecutorProperty(ValidityPackage.Literals.ABSTRACT_NODE__LABEL, Types._AbstractNode, 2);
		public static final @NonNull ExecutorProperty _AbstractNode__visible = new EcoreExecutorProperty(ValidityPackage.Literals.ABSTRACT_NODE__VISIBLE, Types._AbstractNode, 3);
		public static final @NonNull ExecutorProperty _AbstractNode__worstResult = new EcoreExecutorProperty(ValidityPackage.Literals.ABSTRACT_NODE__WORST_RESULT, Types._AbstractNode, 4);

		public static final @NonNull ExecutorProperty _ConstrainingNode__children = new EcoreExecutorProperty(ValidityPackage.Literals.CONSTRAINING_NODE__CHILDREN, Types._ConstrainingNode, 0);
		public static final @NonNull ExecutorProperty _ConstrainingNode__constrainingObject = new EcoreExecutorProperty(ValidityPackage.Literals.CONSTRAINING_NODE__CONSTRAINING_OBJECT, Types._ConstrainingNode, 1);
		public static final @NonNull ExecutorProperty _ConstrainingNode__parent = new EcoreExecutorProperty(ValidityPackage.Literals.CONSTRAINING_NODE__PARENT, Types._ConstrainingNode, 2);

		public static final @NonNull ExecutorProperty _LeafConstrainingNode__constraintLocator = new EcoreExecutorProperty(ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR, Types._LeafConstrainingNode, 0);
		public static final @NonNull ExecutorProperty _LeafConstrainingNode__constraintResource = new EcoreExecutorProperty(ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_RESOURCE, Types._LeafConstrainingNode, 1);
		public static final @NonNull ExecutorProperty _LeafConstrainingNode__constraintString = new EcoreExecutorProperty(ValidityPackage.Literals.LEAF_CONSTRAINING_NODE__CONSTRAINT_STRING, Types._LeafConstrainingNode, 2);
		public static final @NonNull ExecutorProperty _LeafConstrainingNode__Result__leafConstrainingNode = new ExecutorPropertyWithImplementation("Result", Types._LeafConstrainingNode, 3, new EcoreLibraryOppositeProperty(ValidityPackage.Literals.RESULT__LEAF_CONSTRAINING_NODE));

		public static final @NonNull ExecutorProperty _Result__diagnostic = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__DIAGNOSTIC, Types._Result, 0);
		public static final @NonNull ExecutorProperty _Result__exception = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__EXCEPTION, Types._Result, 1);
		public static final @NonNull ExecutorProperty _Result__leafConstrainingNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__LEAF_CONSTRAINING_NODE, Types._Result, 2);
		public static final @NonNull ExecutorProperty _Result__name = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__NAME, Types._Result, 3);
		public static final @NonNull ExecutorProperty _Result__resultConstrainingNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__RESULT_CONSTRAINING_NODE, Types._Result, 4);
		public static final @NonNull ExecutorProperty _Result__resultSet = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__RESULT_SET, Types._Result, 5);
		public static final @NonNull ExecutorProperty _Result__resultValidatableNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__RESULT_VALIDATABLE_NODE, Types._Result, 6);
		public static final @NonNull ExecutorProperty _Result__severity = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__SEVERITY, Types._Result, 7);
		public static final @NonNull ExecutorProperty _Result__validatableNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT__VALIDATABLE_NODE, Types._Result, 8);
		public static final @NonNull ExecutorProperty _Result__AbstractNode__worstResult = new ExecutorPropertyWithImplementation("AbstractNode", Types._Result, 9, new EcoreLibraryOppositeProperty(ValidityPackage.Literals.ABSTRACT_NODE__WORST_RESULT));

		public static final @NonNull ExecutorProperty _ResultConstrainingNode__resultValidatableNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_CONSTRAINING_NODE__RESULT_VALIDATABLE_NODE, Types._ResultConstrainingNode, 0);
		public static final @NonNull ExecutorProperty _ResultConstrainingNode__Result__resultConstrainingNode = new ExecutorPropertyWithImplementation("Result", Types._ResultConstrainingNode, 1, new EcoreLibraryOppositeProperty(ValidityPackage.Literals.RESULT__RESULT_CONSTRAINING_NODE));

		public static final @NonNull ExecutorProperty _ResultSet__name = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_SET__NAME, Types._ResultSet, 0);
		public static final @NonNull ExecutorProperty _ResultSet__results = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_SET__RESULTS, Types._ResultSet, 1);
		public static final @NonNull ExecutorProperty _ResultSet__root = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_SET__ROOT, Types._ResultSet, 2);
		public static final @NonNull ExecutorProperty _ResultSet__timestamp = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_SET__TIMESTAMP, Types._ResultSet, 3);

		public static final @NonNull ExecutorProperty _ResultValidatableNode__resultConstrainingNode = new EcoreExecutorProperty(ValidityPackage.Literals.RESULT_VALIDATABLE_NODE__RESULT_CONSTRAINING_NODE, Types._ResultValidatableNode, 0);
		public static final @NonNull ExecutorProperty _ResultValidatableNode__Result__resultValidatableNode = new ExecutorPropertyWithImplementation("Result", Types._ResultValidatableNode, 1, new EcoreLibraryOppositeProperty(ValidityPackage.Literals.RESULT__RESULT_VALIDATABLE_NODE));

		public static final @NonNull ExecutorProperty _RootConstrainingNode__rootNode = new EcoreExecutorProperty(ValidityPackage.Literals.ROOT_CONSTRAINING_NODE__ROOT_NODE, Types._RootConstrainingNode, 0);

		public static final @NonNull ExecutorProperty _RootNode__constrainingNodes = new EcoreExecutorProperty(ValidityPackage.Literals.ROOT_NODE__CONSTRAINING_NODES, Types._RootNode, 0);
		public static final @NonNull ExecutorProperty _RootNode__resultSets = new EcoreExecutorProperty(ValidityPackage.Literals.ROOT_NODE__RESULT_SETS, Types._RootNode, 1);
		public static final @NonNull ExecutorProperty _RootNode__validatableNodes = new EcoreExecutorProperty(ValidityPackage.Literals.ROOT_NODE__VALIDATABLE_NODES, Types._RootNode, 2);

		public static final @NonNull ExecutorProperty _RootValidatableNode__rootNode = new EcoreExecutorProperty(ValidityPackage.Literals.ROOT_VALIDATABLE_NODE__ROOT_NODE, Types._RootValidatableNode, 0);

		public static final @NonNull ExecutorProperty _ValidatableNode__children = new EcoreExecutorProperty(ValidityPackage.Literals.VALIDATABLE_NODE__CHILDREN, Types._ValidatableNode, 0);
		public static final @NonNull ExecutorProperty _ValidatableNode__constrainedObject = new EcoreExecutorProperty(ValidityPackage.Literals.VALIDATABLE_NODE__CONSTRAINED_OBJECT, Types._ValidatableNode, 1);
		public static final @NonNull ExecutorProperty _ValidatableNode__parent = new EcoreExecutorProperty(ValidityPackage.Literals.VALIDATABLE_NODE__PARENT, Types._ValidatableNode, 2);
		public static final @NonNull ExecutorProperty _ValidatableNode__Result__validatableNode = new ExecutorPropertyWithImplementation("Result", Types._ValidatableNode, 3, new EcoreLibraryOppositeProperty(ValidityPackage.Literals.RESULT__VALIDATABLE_NODE));
		static {
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

		private static final @NonNull ExecutorFragment @NonNull [] _AbstractNode =
			{
				Fragments._AbstractNode__OclAny /* 0 */,
				Fragments._AbstractNode__OclElement /* 1 */,
				Fragments._AbstractNode__AbstractNode /* 2 */
			};
		private static final int @NonNull [] __AbstractNode = { 1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ConstrainingNode =
			{
				Fragments._ConstrainingNode__OclAny /* 0 */,
				Fragments._ConstrainingNode__OclElement /* 1 */,
				Fragments._ConstrainingNode__AbstractNode /* 2 */,
				Fragments._ConstrainingNode__ConstrainingNode /* 3 */
			};
		private static final int @NonNull [] __ConstrainingNode = { 1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ConstraintLocator =
			{
				Fragments._ConstraintLocator__OclAny /* 0 */,
				Fragments._ConstraintLocator__ConstraintLocator /* 1 */
			};
		private static final int @NonNull [] __ConstraintLocator = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _LeafConstrainingNode =
			{
				Fragments._LeafConstrainingNode__OclAny /* 0 */,
				Fragments._LeafConstrainingNode__OclElement /* 1 */,
				Fragments._LeafConstrainingNode__AbstractNode /* 2 */,
				Fragments._LeafConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._LeafConstrainingNode__LeafConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __LeafConstrainingNode = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _Object =
			{
				Fragments._Object__OclAny /* 0 */,
				Fragments._Object__Object /* 1 */
			};
		private static final int @NonNull [] __Object = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _Resource =
			{
				Fragments._Resource__OclAny /* 0 */,
				Fragments._Resource__Resource /* 1 */
			};
		private static final int @NonNull [] __Resource = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _Result =
			{
				Fragments._Result__OclAny /* 0 */,
				Fragments._Result__OclElement /* 1 */,
				Fragments._Result__Result /* 2 */
			};
		private static final int @NonNull [] __Result = { 1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ResultConstrainingNode =
			{
				Fragments._ResultConstrainingNode__OclAny /* 0 */,
				Fragments._ResultConstrainingNode__OclElement /* 1 */,
				Fragments._ResultConstrainingNode__AbstractNode /* 2 */,
				Fragments._ResultConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._ResultConstrainingNode__ResultConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __ResultConstrainingNode = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ResultSet =
			{
				Fragments._ResultSet__OclAny /* 0 */,
				Fragments._ResultSet__OclElement /* 1 */,
				Fragments._ResultSet__ResultSet /* 2 */
			};
		private static final int @NonNull [] __ResultSet = { 1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ResultValidatableNode =
			{
				Fragments._ResultValidatableNode__OclAny /* 0 */,
				Fragments._ResultValidatableNode__OclElement /* 1 */,
				Fragments._ResultValidatableNode__AbstractNode /* 2 */,
				Fragments._ResultValidatableNode__ValidatableNode /* 3 */,
				Fragments._ResultValidatableNode__ResultValidatableNode /* 4 */
			};
		private static final int @NonNull [] __ResultValidatableNode = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _RootConstrainingNode =
			{
				Fragments._RootConstrainingNode__OclAny /* 0 */,
				Fragments._RootConstrainingNode__OclElement /* 1 */,
				Fragments._RootConstrainingNode__AbstractNode /* 2 */,
				Fragments._RootConstrainingNode__ConstrainingNode /* 3 */,
				Fragments._RootConstrainingNode__RootConstrainingNode /* 4 */
			};
		private static final int @NonNull [] __RootConstrainingNode = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _RootNode =
			{
				Fragments._RootNode__OclAny /* 0 */,
				Fragments._RootNode__OclElement /* 1 */,
				Fragments._RootNode__RootNode /* 2 */
			};
		private static final int @NonNull [] __RootNode = { 1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _RootValidatableNode =
			{
				Fragments._RootValidatableNode__OclAny /* 0 */,
				Fragments._RootValidatableNode__OclElement /* 1 */,
				Fragments._RootValidatableNode__AbstractNode /* 2 */,
				Fragments._RootValidatableNode__ValidatableNode /* 3 */,
				Fragments._RootValidatableNode__RootValidatableNode /* 4 */
			};
		private static final int @NonNull [] __RootValidatableNode = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _Severity =
			{
				Fragments._Severity__OclAny /* 0 */,
				Fragments._Severity__OclElement /* 1 */,
				Fragments._Severity__OclType /* 2 */,
				Fragments._Severity__OclEnumeration /* 3 */,
				Fragments._Severity__Severity /* 4 */
			};
		private static final int @NonNull [] __Severity = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _Throwable =
			{
				Fragments._Throwable__OclAny /* 0 */,
				Fragments._Throwable__Throwable /* 1 */
			};
		private static final int @NonNull [] __Throwable = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ValidatableNode =
			{
				Fragments._ValidatableNode__OclAny /* 0 */,
				Fragments._ValidatableNode__OclElement /* 1 */,
				Fragments._ValidatableNode__AbstractNode /* 2 */,
				Fragments._ValidatableNode__ValidatableNode /* 3 */
			};
		private static final int @NonNull [] __ValidatableNode = { 1,1,1,1 };

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

		private static final @NonNull ExecutorOperation @NonNull [] _AbstractNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AbstractNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _AbstractNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _ConstrainingNode__ConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstrainingNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstrainingNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _ConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintLocator__ConstraintLocator = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintLocator__OclAny = {
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

		private static final @NonNull ExecutorOperation @NonNull [] _LeafConstrainingNode__LeafConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LeafConstrainingNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _LeafConstrainingNode__ConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LeafConstrainingNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _LeafConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _Object__Object = {};
		private static final @NonNull ExecutorOperation @NonNull [] _Object__OclAny = {
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

		private static final @NonNull ExecutorOperation @NonNull [] _Resource__Resource = {};
		private static final @NonNull ExecutorOperation @NonNull [] _Resource__OclAny = {
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

		private static final @NonNull ExecutorOperation @NonNull [] _Result__Result = {};
		private static final @NonNull ExecutorOperation @NonNull [] _Result__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _Result__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _ResultConstrainingNode__ResultConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultConstrainingNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultConstrainingNode__ConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultConstrainingNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _ResultConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _ResultSet__ResultSet = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultSet__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _ResultSet__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _ResultValidatableNode__ResultValidatableNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultValidatableNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ResultValidatableNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _ResultValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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
		private static final @NonNull ExecutorOperation @NonNull [] _ResultValidatableNode__ValidatableNode = {};

		private static final @NonNull ExecutorOperation @NonNull [] _RootConstrainingNode__RootConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootConstrainingNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootConstrainingNode__ConstrainingNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootConstrainingNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _RootConstrainingNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _RootNode__RootNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _RootNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorOperation @NonNull [] _RootValidatableNode__RootValidatableNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootValidatableNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootValidatableNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _RootValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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
		private static final @NonNull ExecutorOperation @NonNull [] _RootValidatableNode__ValidatableNode = {};

		private static final @NonNull ExecutorOperation @NonNull [] _Severity__Severity = {};
		private static final @NonNull ExecutorOperation @NonNull [] _Severity__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _Severity__OclElement = {
			OCLstdlibTables.Operations._OclEnumeration__allInstances /* allInstances(Integer[1]) */,
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
		private static final @NonNull ExecutorOperation @NonNull [] _Severity__OclEnumeration = {
			OCLstdlibTables.Operations._OclEnumeration__allInstances /* allInstances(Integer[1]) */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _Severity__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};

		private static final @NonNull ExecutorOperation @NonNull [] _Throwable__Throwable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _Throwable__OclAny = {
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

		private static final @NonNull ExecutorOperation @NonNull [] _ValidatableNode__ValidatableNode = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ValidatableNode__AbstractNode = {
			ValidityTables.Operations._AbstractNode__getChildren /* getChildren() */,
			ValidityTables.Operations._AbstractNode__getParent /* getParent() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ValidatableNode__OclAny = {
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
		private static final @NonNull ExecutorOperation @NonNull [] _ValidatableNode__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull ExecutorProperty @NonNull [] _AbstractNode = {
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ConstrainingNode = {
			ValidityTables.Properties._ConstrainingNode__children,
			ValidityTables.Properties._ConstrainingNode__constrainingObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ConstrainingNode__parent,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ConstraintLocator = {};

		private static final @NonNull ExecutorProperty @NonNull [] _LeafConstrainingNode = {
			ValidityTables.Properties._ConstrainingNode__children,
			ValidityTables.Properties._ConstrainingNode__constrainingObject,
			ValidityTables.Properties._LeafConstrainingNode__constraintLocator,
			ValidityTables.Properties._LeafConstrainingNode__constraintResource,
			ValidityTables.Properties._LeafConstrainingNode__constraintString,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ConstrainingNode__parent,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _Object = {};

		private static final @NonNull ExecutorProperty @NonNull [] _Resource = {};

		private static final @NonNull ExecutorProperty @NonNull [] _Result = {
			ValidityTables.Properties._Result__diagnostic,
			ValidityTables.Properties._Result__exception,
			ValidityTables.Properties._Result__leafConstrainingNode,
			ValidityTables.Properties._Result__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._Result__resultConstrainingNode,
			ValidityTables.Properties._Result__resultSet,
			ValidityTables.Properties._Result__resultValidatableNode,
			ValidityTables.Properties._Result__severity,
			ValidityTables.Properties._Result__validatableNode
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ResultConstrainingNode = {
			ValidityTables.Properties._ConstrainingNode__children,
			ValidityTables.Properties._ConstrainingNode__constrainingObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ConstrainingNode__parent,
			ValidityTables.Properties._ResultConstrainingNode__resultValidatableNode,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ResultSet = {
			ValidityTables.Properties._ResultSet__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ResultSet__results,
			ValidityTables.Properties._ResultSet__root,
			ValidityTables.Properties._ResultSet__timestamp
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ResultValidatableNode = {
			ValidityTables.Properties._ValidatableNode__children,
			ValidityTables.Properties._ValidatableNode__constrainedObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ValidatableNode__parent,
			ValidityTables.Properties._ResultValidatableNode__resultConstrainingNode,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _RootConstrainingNode = {
			ValidityTables.Properties._ConstrainingNode__children,
			ValidityTables.Properties._ConstrainingNode__constrainingObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ConstrainingNode__parent,
			ValidityTables.Properties._RootConstrainingNode__rootNode,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _RootNode = {
			ValidityTables.Properties._RootNode__constrainingNodes,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._RootNode__resultSets,
			ValidityTables.Properties._RootNode__validatableNodes
		};

		private static final @NonNull ExecutorProperty @NonNull [] _RootValidatableNode = {
			ValidityTables.Properties._ValidatableNode__children,
			ValidityTables.Properties._ValidatableNode__constrainedObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ValidatableNode__parent,
			ValidityTables.Properties._RootValidatableNode__rootNode,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
		};

		private static final @NonNull ExecutorProperty @NonNull [] _Severity = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull ExecutorProperty @NonNull [] _Throwable = {};

		private static final @NonNull ExecutorProperty @NonNull [] _ValidatableNode = {
			ValidityTables.Properties._ValidatableNode__children,
			ValidityTables.Properties._ValidatableNode__constrainedObject,
			ValidityTables.Properties._AbstractNode__enabled,
			ValidityTables.Properties._AbstractNode__grayed,
			ValidityTables.Properties._AbstractNode__label,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			ValidityTables.Properties._ValidatableNode__parent,
			ValidityTables.Properties._AbstractNode__visible,
			ValidityTables.Properties._AbstractNode__worstResult
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

		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__UNKNOWN = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("UNKNOWN"), Types._Severity, 0);
		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__OK = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("OK"), Types._Severity, 1);
		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__INFO = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("INFO"), Types._Severity, 2);
		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__WARNING = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("WARNING"), Types._Severity, 3);
		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__ERROR = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("ERROR"), Types._Severity, 4);
		public static final @NonNull EcoreExecutorEnumerationLiteral _Severity__FATAL = new EcoreExecutorEnumerationLiteral(ValidityPackage.Literals.SEVERITY.getEEnumLiteral("FATAL"), Types._Severity, 5);
		private static final @NonNull EcoreExecutorEnumerationLiteral @NonNull [] _Severity = {
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
			Types._Severity.initLiterals(_Severity);

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
