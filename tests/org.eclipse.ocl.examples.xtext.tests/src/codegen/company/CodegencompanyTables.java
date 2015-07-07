/*******************************************************************************
 * <copyright>
 * 
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.examples.xtext.tests/model/Company.ecore
 * using:
 *   /org.eclipse.ocl.examples.xtext.tests/model/CodeGenCompany.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package codegen.company;

import codegen.company.CodegencompanyPackage;
import codegen.company.CodegencompanyTables;
import java.lang.String;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
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
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;

/**
 * CodegencompanyTables provides the dispatch tables for the company for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
@SuppressWarnings("nls")
public class CodegencompanyTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final @NonNull EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(CodegencompanyPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final @NonNull ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
    public static final @NonNull /*@NonInvalid*/ RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final @NonNull /*@NonInvalid*/ NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
    public static final @NonNull /*@NonInvalid*/ NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/test/Pivot/Company.ecore", null, CodegencompanyPackage.eINSTANCE);
    public static final @NonNull /*@NonInvalid*/ ClassId CLSSid_Class = CodegencompanyTables.PACKid_$metamodel$.getClassId("Class", 0);
    public static final @NonNull /*@NonInvalid*/ ClassId CLSSid_Company = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getClassId("Company", 0);
    public static final @NonNull /*@NonInvalid*/ ClassId CLSSid_Employee = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getClassId("Employee", 0);
    public static final @NonNull /*@NonInvalid*/ DataTypeId DATAid_EInt = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EInt", 0);
    public static final @NonNull /*@NonInvalid*/ EnumerationId ENUMid_CompanySizeKind = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getEnumerationId("CompanySizeKind");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_0 = ValueUtil.integerValueOf("0");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_100 = ValueUtil.integerValueOf("100");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_1000 = ValueUtil.integerValueOf("1000");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_1000000 = ValueUtil.integerValueOf("1000000");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_49 = ValueUtil.integerValueOf("49");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_50 = ValueUtil.integerValueOf("50");
    public static final @NonNull /*@NonInvalid*/ IntegerValue INT_999 = ValueUtil.integerValueOf("999");
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId ORD_NULLid = TypeId.ORDERED_SET.getSpecializedId(TypeId.OCL_VOID);
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId SEQ_PRIMid_Integer = TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER);
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId SET_PRIMid_String = TypeId.SET.getSpecializedId(TypeId.STRING);
    public static final @NonNull /*@NonInvalid*/ String STR_Company_c_c_dummyInvariant = "Company::dummyInvariant";
    public static final @NonNull /*@NonInvalid*/ String STR_Employee_c_c_mustHaveName = "Employee::mustHaveName";
    public static final @NonNull /*@NonInvalid*/ String STR_Employee_c_c_mustHaveNonEmptyName = "Employee::mustHaveNonEmptyName";
    public static final @NonNull /*@NonInvalid*/ String STR_Employee_c_c_noManagerImpliesDirectReports = "Employee::noManagerImpliesDirectReports";
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId BAG_CLSSid_Employee = TypeId.BAG.getSpecializedId(CodegencompanyTables.CLSSid_Employee);
    public static final @NonNull /*@NonInvalid*/ EnumerationLiteralId ELITid_large = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("large");
    public static final @NonNull /*@NonInvalid*/ EnumerationLiteralId ELITid_medium = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("medium");
    public static final @NonNull /*@NonInvalid*/ EnumerationLiteralId ELITid_small = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("small");
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId ORD_CLSSid_Employee = TypeId.ORDERED_SET.getSpecializedId(CodegencompanyTables.CLSSid_Employee);
    public static final @NonNull /*@NonInvalid*/ OrderedSetValue OrderedSet = ValueUtil.createOrderedSetOfEach(CodegencompanyTables.ORD_NULLid);
    public static final @NonNull /*@NonInvalid*/ TuplePartId PARTid_ = IdManager.getTuplePartId(0, "range", CodegencompanyTables.SEQ_PRIMid_Integer);
    public static final @NonNull /*@NonInvalid*/ TuplePartId PARTid__0 = IdManager.getTuplePartId(1, "size", CodegencompanyTables.ENUMid_CompanySizeKind);
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId SET_CLSSid_Employee = TypeId.SET.getSpecializedId(CodegencompanyTables.CLSSid_Employee);
    public static final @NonNull /*@NonInvalid*/ IntegerRange symbol_0 = ValueUtil.createRange(CodegencompanyTables.INT_0, CodegencompanyTables.INT_49);
    public static final @NonNull /*@NonInvalid*/ IntegerRange symbol_4 = ValueUtil.createRange(CodegencompanyTables.INT_1000, CodegencompanyTables.INT_1000000);
    public static final @NonNull /*@NonInvalid*/ IntegerRange symbol_2 = ValueUtil.createRange(CodegencompanyTables.INT_50, CodegencompanyTables.INT_999);
    public static final @NonNull /*@NonInvalid*/ SequenceValue Sequence = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.symbol_0);
    public static final @NonNull /*@NonInvalid*/ SequenceValue Sequence_1 = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.symbol_4);
    public static final @NonNull /*@NonInvalid*/ SequenceValue Sequence_0 = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.symbol_2);
    public static final @NonNull /*@NonInvalid*/ TupleTypeId TUPLid_ = IdManager.getTupleTypeId("Tuple", CodegencompanyTables.PARTid_, CodegencompanyTables.PARTid__0);
    public static final @NonNull /*@NonInvalid*/ CollectionTypeId SET_TUPLid_ = TypeId.SET.getSpecializedId(CodegencompanyTables.TUPLid_);
    public static final @NonNull /*@NonInvalid*/ TupleValue symbol_1 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence, CodegencompanyTables.ELITid_small);
    public static final @NonNull /*@NonInvalid*/ TupleValue symbol_5 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence_1, CodegencompanyTables.ELITid_large);
    public static final @NonNull /*@NonInvalid*/ TupleValue symbol_3 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence_0, CodegencompanyTables.ELITid_medium);
    public static final @NonNull /*@NonInvalid*/ SetValue table = ValueUtil.createSetOfEach(CodegencompanyTables.SET_TUPLid_, CodegencompanyTables.symbol_1, CodegencompanyTables.symbol_3, CodegencompanyTables.symbol_5);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			CodegencompanyTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::TypeParameters and all preceding sub-packages.
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

		public static final @NonNull EcoreExecutorType _Bug418716 = new EcoreExecutorType(CodegencompanyPackage.Literals.BUG418716, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Company = new EcoreExecutorType(CodegencompanyPackage.Literals.COMPANY, PACKAGE, 0);
		public static final @NonNull EcoreExecutorEnumeration _CompanySizeKind = new EcoreExecutorEnumeration(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _Employee = new EcoreExecutorType(CodegencompanyPackage.Literals.EMPLOYEE, PACKAGE, 0);

		private static final @NonNull EcoreExecutorType[] types = {
			_Bug418716,
			_Company,
			_CompanySizeKind,
			_Employee
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Types and all preceding sub-packages.
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

		private static final @NonNull ExecutorFragment _Bug418716__Bug418716 = new ExecutorFragment(Types._Bug418716, CodegencompanyTables.Types._Bug418716);
		private static final @NonNull ExecutorFragment _Bug418716__OclAny = new ExecutorFragment(Types._Bug418716, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Bug418716__OclElement = new ExecutorFragment(Types._Bug418716, OCLstdlibTables.Types._OclElement);

		private static final @NonNull ExecutorFragment _Company__Company = new ExecutorFragment(Types._Company, CodegencompanyTables.Types._Company);
		private static final @NonNull ExecutorFragment _Company__OclAny = new ExecutorFragment(Types._Company, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Company__OclElement = new ExecutorFragment(Types._Company, OCLstdlibTables.Types._OclElement);

		private static final @NonNull ExecutorFragment _CompanySizeKind__Class = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Class);
		private static final @NonNull ExecutorFragment _CompanySizeKind__CompanySizeKind = new ExecutorFragment(Types._CompanySizeKind, CodegencompanyTables.Types._CompanySizeKind);
		private static final @NonNull ExecutorFragment _CompanySizeKind__DataType = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._DataType);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Element = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Element);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Enumeration = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Enumeration);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Nameable = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _CompanySizeKind__NamedElement = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._NamedElement);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Namespace = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Namespace);
		private static final @NonNull ExecutorFragment _CompanySizeKind__OclAny = new ExecutorFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _CompanySizeKind__OclElement = new ExecutorFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _CompanySizeKind__OclType = new ExecutorFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclType);
		private static final @NonNull ExecutorFragment _CompanySizeKind__TemplateableElement = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._TemplateableElement);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Type = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Type);
		private static final @NonNull ExecutorFragment _CompanySizeKind__Visitable = new ExecutorFragment(Types._CompanySizeKind, PivotTables.Types._Visitable);

		private static final @NonNull ExecutorFragment _Employee__Employee = new ExecutorFragment(Types._Employee, CodegencompanyTables.Types._Employee);
		private static final @NonNull ExecutorFragment _Employee__OclAny = new ExecutorFragment(Types._Employee, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _Employee__OclElement = new ExecutorFragment(Types._Employee, OCLstdlibTables.Types._OclElement);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}

		public static final @NonNull ParameterTypes _ = TypeUtil.createParameterTypes();
		public static final @NonNull ParameterTypes _Employee = TypeUtil.createParameterTypes(CodegencompanyTables.Types._Employee);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		public static final @NonNull ExecutorOperation _Employee__hasNameAsOperation = new ExecutorOperation("hasNameAsOperation", Parameters._, Types._Employee,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull ExecutorOperation _Employee__reportsTo = new ExecutorOperation("reportsTo", Parameters._Employee, Types._Employee,
			1, TemplateParameters.EMPTY_LIST, null);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final @NonNull ExecutorProperty _Bug418716__AttributeWithInitital = new EcoreExecutorProperty(CodegencompanyPackage.Literals.BUG418716__ATTRIBUTE_WITH_INITITAL, Types._Bug418716, 0);
		public static final @NonNull ExecutorProperty _Bug418716__AttributeWithoutInitital = new EcoreExecutorProperty(CodegencompanyPackage.Literals.BUG418716__ATTRIBUTE_WITHOUT_INITITAL, Types._Bug418716, 1);

		public static final @NonNull ExecutorProperty _Company__employees = new EcoreExecutorProperty(CodegencompanyPackage.Literals.COMPANY__EMPLOYEES, Types._Company, 0);
		public static final @NonNull ExecutorProperty _Company__name = new EcoreExecutorProperty(CodegencompanyPackage.Literals.COMPANY__NAME, Types._Company, 1);
		public static final @NonNull ExecutorProperty _Company__size = new EcoreExecutorProperty(CodegencompanyPackage.Literals.COMPANY__SIZE, Types._Company, 2);

		public static final @NonNull ExecutorProperty _Employee__allReports = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__ALL_REPORTS, Types._Employee, 0);
		public static final @NonNull ExecutorProperty _Employee__company = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__COMPANY, Types._Employee, 1);
		public static final @NonNull ExecutorProperty _Employee__directReports = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__DIRECT_REPORTS, Types._Employee, 2);
		public static final @NonNull ExecutorProperty _Employee__hasNameAsAttribute = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__HAS_NAME_AS_ATTRIBUTE, Types._Employee, 3);
		public static final @NonNull ExecutorProperty _Employee__manager = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__MANAGER, Types._Employee, 4);
		public static final @NonNull ExecutorProperty _Employee__name = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__NAME, Types._Employee, 5);
		public static final @NonNull ExecutorProperty _Employee__reportingChain = new EcoreExecutorProperty(CodegencompanyPackage.Literals.EMPLOYEE__REPORTING_CHAIN, Types._Employee, 6);
		public static final @NonNull ExecutorProperty _Employee__Employee__allReports = new ExecutorPropertyWithImplementation("Employee", Types._Employee, 7, new EcoreLibraryOppositeProperty(CodegencompanyPackage.Literals.EMPLOYEE__ALL_REPORTS));
		public static final @NonNull ExecutorProperty _Employee__Employee__directReports = new ExecutorPropertyWithImplementation("Employee", Types._Employee, 8, new EcoreLibraryOppositeProperty(CodegencompanyPackage.Literals.EMPLOYEE__DIRECT_REPORTS));
		public static final @NonNull ExecutorProperty _Employee__Employee__manager = new ExecutorPropertyWithImplementation("Employee", Types._Employee, 9, new EcoreLibraryOppositeProperty(CodegencompanyPackage.Literals.EMPLOYEE__MANAGER));
		public static final @NonNull ExecutorProperty _Employee__Employee__reportingChain = new ExecutorPropertyWithImplementation("Employee", Types._Employee, 10, new EcoreLibraryOppositeProperty(CodegencompanyPackage.Literals.EMPLOYEE__REPORTING_CHAIN));
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Properties and all preceding sub-packages.
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

		private static final @NonNull ExecutorFragment[] _Bug418716 =
		{
			Fragments._Bug418716__OclAny /* 0 */,
			Fragments._Bug418716__OclElement /* 1 */,
			Fragments._Bug418716__Bug418716 /* 2 */
		};
		private static final @NonNull int[] __Bug418716 = { 1,1,1 };

		private static final @NonNull ExecutorFragment[] _Company =
		{
			Fragments._Company__OclAny /* 0 */,
			Fragments._Company__OclElement /* 1 */,
			Fragments._Company__Company /* 2 */
		};
		private static final @NonNull int[] __Company = { 1,1,1 };

		private static final @NonNull ExecutorFragment[] _CompanySizeKind =
		{
			Fragments._CompanySizeKind__OclAny /* 0 */,
			Fragments._CompanySizeKind__OclElement /* 1 */,
			Fragments._CompanySizeKind__Nameable /* 2 */,
			Fragments._CompanySizeKind__OclType /* 2 */,
			Fragments._CompanySizeKind__Visitable /* 2 */,
			Fragments._CompanySizeKind__Element /* 3 */,
			Fragments._CompanySizeKind__NamedElement /* 4 */,
			Fragments._CompanySizeKind__TemplateableElement /* 4 */,
			Fragments._CompanySizeKind__Namespace /* 5 */,
			Fragments._CompanySizeKind__Type /* 5 */,
			Fragments._CompanySizeKind__Class /* 6 */,
			Fragments._CompanySizeKind__DataType /* 7 */,
			Fragments._CompanySizeKind__Enumeration /* 8 */,
			Fragments._CompanySizeKind__CompanySizeKind /* 9 */
		};
		private static final @NonNull int[] __CompanySizeKind = { 1,1,3,1,2,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment[] _Employee =
		{
			Fragments._Employee__OclAny /* 0 */,
			Fragments._Employee__OclElement /* 1 */,
			Fragments._Employee__Employee /* 2 */
		};
		private static final @NonNull int[] __Employee = { 1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Bug418716.initFragments(_Bug418716, __Bug418716);
			Types._Company.initFragments(_Company, __Company);
			Types._CompanySizeKind.initFragments(_CompanySizeKind, __CompanySizeKind);
			Types._Employee.initFragments(_Employee, __Employee);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::TypeFragments and all preceding sub-packages.
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

		private static final @NonNull ExecutorOperation[] _Bug418716__Bug418716 = {};
		private static final @NonNull ExecutorOperation[] _Bug418716__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation[] _Bug418716__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		private static final @NonNull ExecutorOperation[] _Company__Company = {};
		private static final @NonNull ExecutorOperation[] _Company__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation[] _Company__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		private static final @NonNull ExecutorOperation[] _CompanySizeKind__CompanySizeKind = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Class = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__DataType = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Element = {
			PivotTables.Operations._Element__allOwnedElements /* allOwnedElements() */,
			PivotTables.Operations._Element__getValue /* getValue(Type[1],String[1]) */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Enumeration = {
			PivotTables.Operations._Enumeration__allInstances /* allInstances() */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Nameable = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__NamedElement = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Namespace = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__OclElement = {
			PivotTables.Operations._Enumeration__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__TemplateableElement = {};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Type = {
			PivotTables.Operations._Type__conformsTo /* conformsTo(Type[?]) */,
			PivotTables.Operations._Type__flattenedType /* flattenedType() */,
			PivotTables.Operations._Type__isClass /* isClass() */,
			PivotTables.Operations._Type__isTemplateParameter /* isTemplateParameter() */,
			PivotTables.Operations._Type__specializeIn /* specializeIn(CallExp[1],Type[1]) */
		};
		private static final @NonNull ExecutorOperation[] _CompanySizeKind__Visitable = {};

		private static final @NonNull ExecutorOperation[] _Employee__Employee = {
			CodegencompanyTables.Operations._Employee__hasNameAsOperation /* hasNameAsOperation() */,
			CodegencompanyTables.Operations._Employee__reportsTo /* reportsTo(Employee[?]) */
		};
		private static final @NonNull ExecutorOperation[] _Employee__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation[] _Employee__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Bug418716__Bug418716.initOperations(_Bug418716__Bug418716);
			Fragments._Bug418716__OclAny.initOperations(_Bug418716__OclAny);
			Fragments._Bug418716__OclElement.initOperations(_Bug418716__OclElement);

			Fragments._Company__Company.initOperations(_Company__Company);
			Fragments._Company__OclAny.initOperations(_Company__OclAny);
			Fragments._Company__OclElement.initOperations(_Company__OclElement);

			Fragments._CompanySizeKind__Class.initOperations(_CompanySizeKind__Class);
			Fragments._CompanySizeKind__CompanySizeKind.initOperations(_CompanySizeKind__CompanySizeKind);
			Fragments._CompanySizeKind__DataType.initOperations(_CompanySizeKind__DataType);
			Fragments._CompanySizeKind__Element.initOperations(_CompanySizeKind__Element);
			Fragments._CompanySizeKind__Enumeration.initOperations(_CompanySizeKind__Enumeration);
			Fragments._CompanySizeKind__Nameable.initOperations(_CompanySizeKind__Nameable);
			Fragments._CompanySizeKind__NamedElement.initOperations(_CompanySizeKind__NamedElement);
			Fragments._CompanySizeKind__Namespace.initOperations(_CompanySizeKind__Namespace);
			Fragments._CompanySizeKind__OclAny.initOperations(_CompanySizeKind__OclAny);
			Fragments._CompanySizeKind__OclElement.initOperations(_CompanySizeKind__OclElement);
			Fragments._CompanySizeKind__OclType.initOperations(_CompanySizeKind__OclType);
			Fragments._CompanySizeKind__TemplateableElement.initOperations(_CompanySizeKind__TemplateableElement);
			Fragments._CompanySizeKind__Type.initOperations(_CompanySizeKind__Type);
			Fragments._CompanySizeKind__Visitable.initOperations(_CompanySizeKind__Visitable);

			Fragments._Employee__Employee.initOperations(_Employee__Employee);
			Fragments._Employee__OclAny.initOperations(_Employee__OclAny);
			Fragments._Employee__OclElement.initOperations(_Employee__OclElement);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::FragmentOperations and all preceding sub-packages.
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

		private static final @NonNull ExecutorProperty[] _Bug418716 = {
			CodegencompanyTables.Properties._Bug418716__AttributeWithInitital,
			CodegencompanyTables.Properties._Bug418716__AttributeWithoutInitital
		};

		private static final @NonNull ExecutorProperty[] _Company = {
			CodegencompanyTables.Properties._Company__employees,
			CodegencompanyTables.Properties._Company__name,
			CodegencompanyTables.Properties._Company__size
		};

		private static final @NonNull ExecutorProperty[] _CompanySizeKind = {};

		private static final @NonNull ExecutorProperty[] _Employee = {
			CodegencompanyTables.Properties._Employee__allReports,
			CodegencompanyTables.Properties._Employee__company,
			CodegencompanyTables.Properties._Employee__directReports,
			CodegencompanyTables.Properties._Employee__hasNameAsAttribute,
			CodegencompanyTables.Properties._Employee__manager,
			CodegencompanyTables.Properties._Employee__name,
			CodegencompanyTables.Properties._Employee__reportingChain,
			CodegencompanyTables.Properties._Employee__Employee__allReports,
			CodegencompanyTables.Properties._Employee__Employee__directReports,
			CodegencompanyTables.Properties._Employee__Employee__manager,
			CodegencompanyTables.Properties._Employee__Employee__reportingChain
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Bug418716__Bug418716.initProperties(_Bug418716);
			Fragments._Company__Company.initProperties(_Company);
			Fragments._CompanySizeKind__CompanySizeKind.initProperties(_CompanySizeKind);
			Fragments._Employee__Employee.initProperties(_Employee);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::FragmentProperties and all preceding sub-packages.
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

		public static final @NonNull EcoreExecutorEnumerationLiteral _CompanySizeKind__small = new EcoreExecutorEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("small"), Types._CompanySizeKind, 0);
		public static final @NonNull EcoreExecutorEnumerationLiteral _CompanySizeKind__medium = new EcoreExecutorEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("medium"), Types._CompanySizeKind, 1);
		public static final @NonNull EcoreExecutorEnumerationLiteral _CompanySizeKind__large = new EcoreExecutorEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("large"), Types._CompanySizeKind, 2);
		private static final @NonNull EcoreExecutorEnumerationLiteral[] _CompanySizeKind = {
			_CompanySizeKind__small,
			_CompanySizeKind__medium,
			_CompanySizeKind__large
		};

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {
			Types._CompanySizeKind.initLiterals(_CompanySizeKind);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::EnumerationLiterals and all preceding sub-packages.
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
		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.
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
	public static void init() {}
}
