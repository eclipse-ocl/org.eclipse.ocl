/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2015, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.examples.xtext.tests/models/genModel/Company.ecore
 * using:
 *   /org.eclipse.ocl.examples.xtext.tests/models/genModel/CodeGenCompanySrc.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests.codegen.company;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
// import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage;
// import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.flat.FlatFragment;
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
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
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
public class CodegencompanyTables extends AbstractTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The library of all packages and types.
	 */
	public static final @NonNull ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	The package descriptor for the package.
	 */
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(CodegencompanyPackage.eINSTANCE, null);

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ @NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/test/Pivot/Company.ecore", null, CodegencompanyPackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Bug418716 = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getClassId("Bug418716", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Class = CodegencompanyTables.PACKid_$metamodel$.getClassId("Class", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Company = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getClassId("Company", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Employee = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getClassId("Employee", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_EBoolean = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EBoolean", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_EInt = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EInt", 0);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_EString = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EString", 0);
	public static final /*@NonInvalid*/ @NonNull EnumerationId ENUMid_CompanySizeKind = CodegencompanyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Company_ecore.getEnumerationId("CompanySizeKind");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_0 = ValueUtil.integerValueOf("0");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_100 = ValueUtil.integerValueOf("100");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_1000 = ValueUtil.integerValueOf("1000");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_1000000 = ValueUtil.integerValueOf("1000000");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_49 = ValueUtil.integerValueOf("49");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_50 = ValueUtil.integerValueOf("50");
	public static final /*@NonInvalid*/ @NonNull IntegerValue INT_999 = ValueUtil.integerValueOf("999");
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_NULLid = TypeId.ORDERED_SET.getSpecializedId(TypeId.OCL_VOID, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull TuplePartId PARTid__1 = IdManager.getTuplePartId(0, "message", TypeId.STRING);
	public static final /*@NonInvalid*/ @NonNull TuplePartId PARTid__2 = IdManager.getTuplePartId(1, "status", TypeId.BOOLEAN);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SEQ_PRIMid_Integer = TypeId.SEQUENCE.getSpecializedId(TypeId.INTEGER, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull String STR_Employee_32_must_32_have_32_a_32_name = "Employee must have a name";
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_CLSSid_Employee = TypeId.BAG.getSpecializedId(CodegencompanyTables.CLSSid_Employee, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_large = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("large");
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_medium = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("medium");
	public static final /*@NonInvalid*/ @NonNull EnumerationLiteralId ELITid_small = CodegencompanyTables.ENUMid_CompanySizeKind.getEnumerationLiteralId("small");
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_Employee = TypeId.ORDERED_SET.getSpecializedId(CodegencompanyTables.CLSSid_Employee, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_Employee_0 = TypeId.ORDERED_SET.getSpecializedId(CodegencompanyTables.CLSSid_Employee, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull OrderedSetValue OrderedSet = ValueUtil.createOrderedSetOfEach(CodegencompanyTables.ORD_NULLid);
	public static final /*@NonInvalid*/ @NonNull TuplePartId PARTid_ = IdManager.getTuplePartId(0, "range", CodegencompanyTables.SEQ_PRIMid_Integer);
	public static final /*@NonInvalid*/ @NonNull TuplePartId PARTid__0 = IdManager.getTuplePartId(1, "size", CodegencompanyTables.ENUMid_CompanySizeKind);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_CLSSid_Employee = TypeId.SET.getSpecializedId(CodegencompanyTables.CLSSid_Employee, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_DATAid_EString = TypeId.SET.getSpecializedId(CodegencompanyTables.DATAid_EString, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull TupleTypeId TUPLid__0 = IdManager.getTupleTypeId("Tuple", CodegencompanyTables.PARTid__1, CodegencompanyTables.PARTid__2);
	public static final /*@NonInvalid*/ @NonNull IntegerRange global_0 = ValueUtil.createRange(CodegencompanyTables.INT_0, CodegencompanyTables.INT_49);
	public static final /*@NonInvalid*/ @NonNull IntegerRange global_4 = ValueUtil.createRange(CodegencompanyTables.INT_1000, CodegencompanyTables.INT_1000000);
	public static final /*@NonInvalid*/ @NonNull IntegerRange global_2 = ValueUtil.createRange(CodegencompanyTables.INT_50, CodegencompanyTables.INT_999);
	public static final /*@NonInvalid*/ @NonNull SequenceValue Sequence = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.global_0);
	public static final /*@NonInvalid*/ @NonNull SequenceValue Sequence_1 = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.global_4);
	public static final /*@NonInvalid*/ @NonNull SequenceValue Sequence_0 = ValueUtil.createSequenceRange(CodegencompanyTables.SEQ_PRIMid_Integer, CodegencompanyTables.global_2);
	public static final /*@NonInvalid*/ @NonNull TupleTypeId TUPLid_ = IdManager.getTupleTypeId("Tuple", CodegencompanyTables.PARTid_, CodegencompanyTables.PARTid__0);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId SET_TUPLid_ = TypeId.SET.getSpecializedId(CodegencompanyTables.TUPLid_, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull TupleValue global_1 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence, CodegencompanyTables.ELITid_small);
	public static final /*@NonInvalid*/ @NonNull TupleValue global_5 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence_1, CodegencompanyTables.ELITid_large);
	public static final /*@NonInvalid*/ @NonNull TupleValue global_3 = ValueUtil.createTupleOfEach(CodegencompanyTables.TUPLid_, CodegencompanyTables.Sequence_0, CodegencompanyTables.ELITid_medium);
	public static final /*@NonInvalid*/ @NonNull SetValue table = ValueUtil.createSetOfEach(CodegencompanyTables.SET_TUPLid_, CodegencompanyTables.global_1, CodegencompanyTables.global_3, CodegencompanyTables.global_5);

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

		public static final org.eclipse.ocl.pivot.@NonNull Class _Bug418716 = LIBRARY.createClass(PivotPackage.Literals.CLASS, CodegencompanyPackage.Literals.BUG418716, PACKAGE, null, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Company = LIBRARY.createClass(PivotPackage.Literals.CLASS, CodegencompanyPackage.Literals.COMPANY, PACKAGE, null, 0);
		public static final @NonNull Enumeration _CompanySizeKind = LIBRARY.createEnumeration(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND, PACKAGE);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Employee = LIBRARY.createClass(PivotPackage.Literals.CLASS, CodegencompanyPackage.Literals.EMPLOYEE, PACKAGE, null, 0);

		private static final org.eclipse.ocl.pivot.@NonNull Class @NonNull [] types = {
			_Bug418716,
			_Company,
			_CompanySizeKind,
			_Employee
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			LIBRARY.initPackage(PACKAGE, types);
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

		private static final @NonNull FlatFragment _Bug418716__Bug418716 = LIBRARY.createFragment(Types._Bug418716, CodegencompanyTables.Types._Bug418716);
		private static final @NonNull FlatFragment _Bug418716__OclAny = LIBRARY.createFragment(Types._Bug418716, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Bug418716__OclElement = LIBRARY.createFragment(Types._Bug418716, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _Company__Company = LIBRARY.createFragment(Types._Company, CodegencompanyTables.Types._Company);
		private static final @NonNull FlatFragment _Company__OclAny = LIBRARY.createFragment(Types._Company, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Company__OclElement = LIBRARY.createFragment(Types._Company, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _CompanySizeKind__CompanySizeKind = LIBRARY.createFragment(Types._CompanySizeKind, CodegencompanyTables.Types._CompanySizeKind);
		private static final @NonNull FlatFragment _CompanySizeKind__OclAny = LIBRARY.createFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _CompanySizeKind__OclElement = LIBRARY.createFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _CompanySizeKind__OclEnumeration = LIBRARY.createFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclEnumeration);
		private static final @NonNull FlatFragment _CompanySizeKind__OclType = LIBRARY.createFragment(Types._CompanySizeKind, OCLstdlibTables.Types._OclType);

		private static final @NonNull FlatFragment _Employee__Employee = LIBRARY.createFragment(Types._Employee, CodegencompanyTables.Types._Employee);
		private static final @NonNull FlatFragment _Employee__OclAny = LIBRARY.createFragment(Types._Employee, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Employee__OclElement = LIBRARY.createFragment(Types._Employee, OCLstdlibTables.Types._OclElement);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter type lists shared by operations.
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
		public static final @NonNull Object[] _Employee = new @NonNull Object[] {CodegencompanyTables.Types._Employee};

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

		public static final @NonNull Operation _Employee__hasNameAsOperation = LIBRARY.createOperation("hasNameAsOperation", TypeUtil.EMPTY_PARAMETER_TYPESx2x, Types._Employee,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _Employee__reportsTo = LIBRARY.createOperation("reportsTo", Parameters._Employee, Types._Employee,
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

		public static final @NonNull Property _Bug418716__AttributeWithInitital = LIBRARY.createProperty(CodegencompanyPackage.Literals.BUG418716__ATTRIBUTE_WITH_INITITAL, Types._Bug418716, 0);
		public static final @NonNull Property _Bug418716__AttributeWithoutInitital = LIBRARY.createProperty(CodegencompanyPackage.Literals.BUG418716__ATTRIBUTE_WITHOUT_INITITAL, Types._Bug418716, 1);

		public static final @NonNull Property _Company__employees = LIBRARY.createProperty(CodegencompanyPackage.Literals.COMPANY__EMPLOYEES, Types._Company, 0);
		public static final @NonNull Property _Company__name = LIBRARY.createProperty(CodegencompanyPackage.Literals.COMPANY__NAME, Types._Company, 1);
		public static final @NonNull Property _Company__size = LIBRARY.createProperty(CodegencompanyPackage.Literals.COMPANY__SIZE, Types._Company, 2);

		public static final @NonNull Property _Employee__allReports = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__ALL_REPORTS, Types._Employee, 0);
		public static final @NonNull Property _Employee__company = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__COMPANY, Types._Employee, 1);
		public static final @NonNull Property _Employee__directReports = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__DIRECT_REPORTS, Types._Employee, 2);
		public static final @NonNull Property _Employee__hasNameAsAttribute = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__HAS_NAME_AS_ATTRIBUTE, Types._Employee, 3);
		public static final @NonNull Property _Employee__manager = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__MANAGER, Types._Employee, 4);
		public static final @NonNull Property _Employee__name = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__NAME, Types._Employee, 5);
		public static final @NonNull Property _Employee__reportingChain = LIBRARY.createProperty(CodegencompanyPackage.Literals.EMPLOYEE__REPORTING_CHAIN, Types._Employee, 6);
		public static final @NonNull Property _Employee__Employee__allReports = LIBRARY.createOppositeProperty("Employee", Types._Employee, 7, CodegencompanyPackage.Literals.EMPLOYEE__ALL_REPORTS);
		public static final @NonNull Property _Employee__Employee__directReports = LIBRARY.createOppositeProperty("Employee", Types._Employee, 8, CodegencompanyPackage.Literals.EMPLOYEE__DIRECT_REPORTS);
		public static final @NonNull Property _Employee__Employee__manager = LIBRARY.createOppositeProperty("Employee", Types._Employee, 9, CodegencompanyPackage.Literals.EMPLOYEE__MANAGER);
		public static final @NonNull Property _Employee__Employee__reportingChain = LIBRARY.createOppositeProperty("Employee", Types._Employee, 10, CodegencompanyPackage.Literals.EMPLOYEE__REPORTING_CHAIN);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}


	// CTOR http://www.eclipse.org/ocl/2015/Orphanage
	// CTOR platform:/resource/org.eclipse.ocl.examples.xtext.tests/models/genModel/Company.ecore
	// CTOR http://www.eclipse.org/ocl/2015/Library
	// CTOR http://www.eclipse.org/emf/2002/Ecore

	@SuppressWarnings("unused")
	private static Object unusedInit0 = new Object() {{
	}};

	// CTOR $$
	// CTOR ocl
	// CTOR ecore
	// CTOR company
	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final @NonNull FlatFragment @NonNull [] _Bug418716 =
		{
			Fragments._Bug418716__OclAny /* 0 */,
			Fragments._Bug418716__OclElement /* 1 */,
			Fragments._Bug418716__Bug418716 /* 2 */
		};
		private static final int @NonNull [] __Bug418716 = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Company =
		{
			Fragments._Company__OclAny /* 0 */,
			Fragments._Company__OclElement /* 1 */,
			Fragments._Company__Company /* 2 */
		};
		private static final int @NonNull [] __Company = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _CompanySizeKind =
		{
			Fragments._CompanySizeKind__OclAny /* 0 */,
			Fragments._CompanySizeKind__OclElement /* 1 */,
			Fragments._CompanySizeKind__OclType /* 2 */,
			Fragments._CompanySizeKind__OclEnumeration /* 3 */,
			Fragments._CompanySizeKind__CompanySizeKind /* 4 */
		};
		private static final int @NonNull [] __CompanySizeKind = { 1,1,1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Employee =
		{
			Fragments._Employee__OclAny /* 0 */,
			Fragments._Employee__OclElement /* 1 */,
			Fragments._Employee__Employee /* 2 */
		};
		private static final int @NonNull [] __Employee = { 1,1,1 };

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

	@SuppressWarnings("unused")
	private static Object unusedInit1 = new Object() {{
		// LIBRARY.createComment(_ocl, "This clause describes the OCL Standard Library of predefined types, their operations, and predefined expression templates in the OCL.\nThis sub clause contains all standard types defined within OCL, including all the operations defined on those types.\nFor each operation the signature and a description of the semantics is given.\nWithin the description, the reserved word \u2018result\u2019 is used to refer to the value that results from evaluating the operation.\nIn several places, post conditions are used to describe properties of the result.\nWhen there is more than one postcondition, all postconditions must be true.\nA similar thing is true for multiple preconditions.\nIf these are used, the operation is only defined if all preconditions evaluate to oclText[true].\n\nheading:1[Introduction]\n\nThe structure, syntax, and semantics of the OCL is defined in Clauses 8 (\u201CAbstract Syntax\u201D), 9 (\u201CConcrete Syntax\u201D),\nand 10 (\u201CSemantics Described using UML\u201D).\nThis sub clause adds another part to the OCL definition: a library of predefined types and operations.\nAny implementation of OCL must include this library package. This approach has also been taken by e.g., the Java definition,\nwhere the language definition and the standard libraries are both mandatory parts of the complete language definition.\n\nThe OCL standard library defines a number of types.\nIt includes several primitive types: UnlimitedNatural, Integer, Real, String, and Boolean.\nThese are familiar from many other languages. The second part of the standard library consists of the collection types.\nThey are Bag, Set, Sequence, and Collection where Collection is an abstract type.\nNote that all types defined in the OCL standard library are instances of an abstract syntax class.\nThe OCL standard library exists at the modeling level, also referred to as the M1 level, where the abstract syntax is the metalevel or M2 level.\n\nNext to definitions of types the OCL standard library defines a number of template expressions.\nMany operations defined on collections map not on the abstract syntax metaclass FeatureCallExp, but on the IteratorExp.\nFor each of these a template expression that defines the name and format of the expression is defined in 11.8, Predefined Iterator Expressions.\n\nThe Standard Library may be extended with new types, new operations and new iterators.\nIn particular new operations can be defined for collections.\n\nCertain String operations depend on the prevailing locale to ensure that Strings are collated and characters are case-converted\nin an appropriate fashion.\nA locale is defined as a concatenation of up to three character sequences separated by underscores,\nwith the first sequence identifying the language and the second sequence identifying the country.\nThe third sequence is empty but may encode an implementation-specific variant.\nTrailing empty strings and separators may be omitted.\n\nThe character sequences for languages are defined by ISO 639.\n\nThe character sequences for countries are defined by ISO 3166.\n\n\u2018fr_CA\u2019 therefore identifies the locale for the French language in the Canada country.\n\nComparison of strings and consequently the collation order of Collection::sortedBy()\nconforms to the Unicode Collation algorithm defined by Unicode Technical Standard#10.\n\nThe locale is \u2018en_us\u2019 by default but may be configured by a property constraint on OclAny::oclLocale.\n\nThe prevailing locale is defined by the prevailing value of oclLocale within the current environment;\nit may therefore be changed temporarily by using a Let expression.\nlet oclLocale : String = \u2018fr_CA\u2019 in aString.toUpperCase()\n\nheading:1[Iterators]\n\nThis sub clause defines the standard OCL iterator expressions.\nIn the abstract syntax these are all instances of IteratorExp.\nThese iterator expressions always have a collection expression as their source,\nas is defined in the well-formedness rules in Clause 8 (\u201CAbstract Syntax\u201D).\nThe defined iterator expressions are shown per source collection type.\nThe semantics of each iterator expression is defined through a mapping from the iterator to the \u2018iterate\u2019 construct.\nThis means that the semantics of the iterator expressions do not need to be defined separately in the semantics sub clauses.\n\nIn all of the following OCL expressions, the lefthand side of the equals sign is the IteratorExp to be defined,\nand the righthand side of the equals sign is the equivalent as an IterateExp.\nThe names source, body, and iterator refer to the role names in the abstract syntax:\n\nsource\tThe source expression of the IteratorExp.\n\nbody\tThe body expression of the IteratorExp.\n\niterator\tThe iterator variable of the IteratorExp.\n\nresult\tThe result variable of the IterateExp.\n\nheading:2[Extending the Standard Library with Iterator Expressions]\n\nIt is possible to add new iterator expressions in the standard library.\nIf this is done the semantics of a new iterator should be defined by mapping it to existing constructs,\nin the same way the semantics of pre-defined iterators is done (see sub clause 11.9)");
	}};

	// CTOR ecore::EBoolean
	// CTOR company::CompanySizeKind
	// CTOR OclElement
	// CTOR OclEnumeration
	// CTOR company::Bug418716
	// CTOR company::Company
	// CTOR company::Employee
	// CTOR Bag(company::Employee)
	// CTOR OrderedSet(company::Employee)
	// CTOR Set(company::Employee)

	@SuppressWarnings("unused")
	private static Object unusedInit2 = new Object() {{
		// LIBRARY.createComment(_OclElement, "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes. Operations defined\nfor OclElement are therefore applicable to all user-defined types.");
		// LIBRARY.createComment(_OclEnumeration, "The OclEnumeration type is the implicit supertype of any user Enumeration type.\nFIXME This is probably obsolete now that static / meta-types clarified.");
	}};

	// CTOR company::Bug418716::AttributeWithInitital
	// CTOR company::Bug418716::AttributeWithoutInitital
	// CTOR company::Company::employees
	// CTOR company::Company::name
	// CTOR company::Company::size
	// CTOR company::Employee::Employee
	// CTOR company::Employee::Employee
	// CTOR company::Employee::Employee
	// CTOR company::Employee::Employee
	// CTOR company::Employee::allReports
	// CTOR company::Employee::company
	// CTOR company::Employee::directReports
	// CTOR company::Employee::hasNameAsAttribute
	// CTOR company::Employee::manager
	// CTOR company::Employee::name
	// CTOR company::Employee::reportingChain
	// CTOR company::Employee::hasNameAsOperation() : ecore::EBoolean[1]
	// CTOR company::Employee::reportsTo(company::Employee[?]) : ecore::EBoolean[1]
	// SUPER_CLASSES company::CompanySizeKind
	// SUPER_CLASSES company::Bug418716
	// SUPER_CLASSES company::Company
	// SUPER_CLASSES company::Employee

	@SuppressWarnings("unused")
	private static Object unusedInit3 = new Object() {{
	}};

	// TYPE company::Employee::hasNameAsOperation() : ecore::EBoolean[1]
	// TYPE company::Employee::reportsTo(company::Employee[?]) : ecore::EBoolean[1]

	@SuppressWarnings("unused")
	private static Object unusedInit4 = new Object() {{
	}};

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final @NonNull Operation @NonNull [] _Bug418716__Bug418716 = {};
		private static final @NonNull Operation @NonNull [] _Bug418716__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _Bug418716__OclElement = {
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

		private static final @NonNull Operation @NonNull [] _Company__Company = {};
		private static final @NonNull Operation @NonNull [] _Company__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _Company__OclElement = {
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

		private static final @NonNull Operation @NonNull [] _CompanySizeKind__CompanySizeKind = {};
		private static final @NonNull Operation @NonNull [] _CompanySizeKind__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _CompanySizeKind__OclElement = {
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
		private static final @NonNull Operation @NonNull [] _CompanySizeKind__OclEnumeration = {
			OCLstdlibTables.Operations._OclEnumeration__allInstances /* allInstances(Integer[1]) */
		};
		private static final @NonNull Operation @NonNull [] _CompanySizeKind__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};

		private static final @NonNull Operation @NonNull [] _Employee__Employee = {
			CodegencompanyTables.Operations._Employee__hasNameAsOperation /* hasNameAsOperation() */,
			CodegencompanyTables.Operations._Employee__reportsTo /* reportsTo(Employee[?]) */
		};
		private static final @NonNull Operation @NonNull [] _Employee__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _Employee__OclElement = {
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
			Fragments._Bug418716__Bug418716.initOperations(_Bug418716__Bug418716);
			Fragments._Bug418716__OclAny.initOperations(_Bug418716__OclAny);
			Fragments._Bug418716__OclElement.initOperations(_Bug418716__OclElement);

			Fragments._Company__Company.initOperations(_Company__Company);
			Fragments._Company__OclAny.initOperations(_Company__OclAny);
			Fragments._Company__OclElement.initOperations(_Company__OclElement);

			Fragments._CompanySizeKind__CompanySizeKind.initOperations(_CompanySizeKind__CompanySizeKind);
			Fragments._CompanySizeKind__OclAny.initOperations(_CompanySizeKind__OclAny);
			Fragments._CompanySizeKind__OclElement.initOperations(_CompanySizeKind__OclElement);
			Fragments._CompanySizeKind__OclEnumeration.initOperations(_CompanySizeKind__OclEnumeration);
			Fragments._CompanySizeKind__OclType.initOperations(_CompanySizeKind__OclType);

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


	@SuppressWarnings("unused")
	private static Object unusedInit5 = new Object() {{
	}};

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final @NonNull Property @NonNull [] _Bug418716 = {
			CodegencompanyTables.Properties._Bug418716__AttributeWithInitital,
			CodegencompanyTables.Properties._Bug418716__AttributeWithoutInitital,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _Company = {
			CodegencompanyTables.Properties._Company__employees,
			CodegencompanyTables.Properties._Company__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			CodegencompanyTables.Properties._Company__size
		};

		private static final @NonNull Property @NonNull [] _CompanySizeKind = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _Employee = {
			CodegencompanyTables.Properties._Employee__allReports,
			CodegencompanyTables.Properties._Employee__company,
			CodegencompanyTables.Properties._Employee__directReports,
			CodegencompanyTables.Properties._Employee__hasNameAsAttribute,
			CodegencompanyTables.Properties._Employee__manager,
			CodegencompanyTables.Properties._Employee__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			CodegencompanyTables.Properties._Employee__reportingChain
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


	@SuppressWarnings("unused")
	private static Object unusedInit6 = new Object() {{
	}};

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		public static final @NonNull EnumerationLiteral _CompanySizeKind__small = LIBRARY.createEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("small"), Types._CompanySizeKind, 0);
		public static final @NonNull EnumerationLiteral _CompanySizeKind__medium = LIBRARY.createEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("medium"), Types._CompanySizeKind, 1);
		public static final @NonNull EnumerationLiteral _CompanySizeKind__large = LIBRARY.createEnumerationLiteral(CodegencompanyPackage.Literals.COMPANY_SIZE_KIND.getEEnumLiteral("large"), Types._CompanySizeKind, 2);
		private static final @NonNull EnumerationLiteral @NonNull [] _CompanySizeKind = {
			_CompanySizeKind__small,
			_CompanySizeKind__medium,
			_CompanySizeKind__large
		};

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {
			LIBRARY.initLiterals(Types._CompanySizeKind, _CompanySizeKind);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of CodegencompanyTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	@SuppressWarnings("unused")
	private static Object unusedInit7 = new Object() {{
	}};

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
		new CodegencompanyTables();
	}

	private CodegencompanyTables() {
		super(CodegencompanyPackage.eNS_URI);
	}

	/*
	 * The EClasses whose instances should be cached to support allInstances().
	 */
	private static final @NonNull EClass allInstancesEClasses @NonNull [] = {
		CodegencompanyPackage.Literals.EMPLOYEE
	};

	@Override
	public @NonNull EClass @NonNull [] basicGetAllInstancesClasses() {
		return allInstancesEClasses;
	}
}
