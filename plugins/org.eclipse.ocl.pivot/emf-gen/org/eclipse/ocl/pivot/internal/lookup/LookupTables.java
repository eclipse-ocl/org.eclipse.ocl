/*******************************************************************************
 * Copyright (c) 2014, 2023 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.pivot/model/Lookup.ecore
 * using:
 *   /org.eclipse.ocl.pivot/model/Lookup.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.lookup;

import java.lang.Object;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
// import org.eclipse.ocl.pivot.internal.lookup.LookupPackage;
// import org.eclipse.ocl.pivot.internal.lookup.LookupTables;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * LookupTables provides the dispatch tables for the lookup for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
public class LookupTables extends AbstractTables
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
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(LookupPackage.eINSTANCE, null);

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ @NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Lookup", null, LookupPackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Class = LookupTables.PACKid_$metamodel$.getClassId("Class", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Executor = LookupTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("Executor", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_LookupEnvironment = LookupTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("LookupEnvironment", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_NamedElement = LookupTables.PACKid_$metamodel$.getClassId("NamedElement", 0);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId COL_TMPLid_ = TypeId.COLLECTION.getSpecializedId(TypeId.T_1, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull DataTypeId DATAid_EBoolean = LookupTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EBoolean", 0);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_CLSSid_LookupEnvironment = TypeId.BAG.getSpecializedId(LookupTables.CLSSid_LookupEnvironment, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_NamedElement = TypeId.ORDERED_SET.getSpecializedId(LookupTables.CLSSid_NamedElement, true, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_NamedElement_0 = TypeId.ORDERED_SET.getSpecializedId(LookupTables.CLSSid_NamedElement, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull PropertyId PROPid_namedElements = LookupTables.CLSSid_LookupEnvironment.getPropertyId("namedElements");

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			LookupTables.init();
		}

		public static final @NonNull TemplateParameter _0_NE = LIBRARY.createTemplateParameter(0, "NE");

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::TypeParameters and all preceding sub-packages.
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

		public static final org.eclipse.ocl.pivot.@NonNull Class _Executor = LIBRARY.createClass(PivotPackage.Literals.CLASS, LookupPackage.Literals.EXECUTOR, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _LookupEnvironment = LIBRARY.createClass(PivotPackage.Literals.CLASS, LookupPackage.Literals.LOOKUP_ENVIRONMENT, PACKAGE, null, 0);

		private static final org.eclipse.ocl.pivot.@NonNull Class @NonNull [] types = {
			_Executor,
			_LookupEnvironment
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			LIBRARY.initPackage(PACKAGE, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Types and all preceding sub-packages.
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

		private static final @NonNull FlatFragment _Executor__Executor = LIBRARY.createFragment(Types._Executor, LookupTables.Types._Executor);
		private static final @NonNull FlatFragment _Executor__OclAny = LIBRARY.createFragment(Types._Executor, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Executor__OclElement = LIBRARY.createFragment(Types._Executor, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _LookupEnvironment__LookupEnvironment = LIBRARY.createFragment(Types._LookupEnvironment, LookupTables.Types._LookupEnvironment);
		private static final @NonNull FlatFragment _LookupEnvironment__OclAny = LIBRARY.createFragment(Types._LookupEnvironment, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _LookupEnvironment__OclElement = LIBRARY.createFragment(Types._LookupEnvironment, OCLstdlibTables.Types._OclElement);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Fragments and all preceding sub-packages.
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
		public static final @NonNull Object[] _Collection__0_NE__ = new @NonNull Object[] {OCLstdlibTables.Types._Collection, 0};
		public static final @NonNull Object[] _NamedElement = new @NonNull Object[] {PivotTables.Types._NamedElement};

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Parameters and all preceding sub-packages.
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

		public static final @NonNull Operation _LookupEnvironment__addElement = LIBRARY.createOperation("addElement", Parameters._NamedElement, Types._LookupEnvironment,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _LookupEnvironment__addElements = LIBRARY.createOperation("addElements", Parameters._Collection__0_NE__, Types._LookupEnvironment,
			1, TypeUtil.createTemplateParameters(TypeParameters._0_NE), null);
		public static final @NonNull Operation _LookupEnvironment__getExecutor = LIBRARY.createOperation("getExecutor", TypeUtil.EMPTY_PARAMETER_TYPESx2x, Types._LookupEnvironment,
			2, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _LookupEnvironment__hasFinalResult = LIBRARY.createOperation("hasFinalResult", TypeUtil.EMPTY_PARAMETER_TYPESx2x, Types._LookupEnvironment,
			3, TemplateParameters.EMPTY_LIST, null);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Operations and all preceding sub-packages.
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


		public static final @NonNull Property _LookupEnvironment__namedElements = LIBRARY.createProperty(LookupPackage.Literals.LOOKUP_ENVIRONMENT__NAMED_ELEMENTS, Types._LookupEnvironment, 0);
		public static final @NonNull Property _LookupEnvironment__parentEnv = LIBRARY.createProperty(LookupPackage.Literals.LOOKUP_ENVIRONMENT__PARENT_ENV, Types._LookupEnvironment, 1);
		public static final @NonNull Property _LookupEnvironment__LookupEnvironment__parentEnv = LIBRARY.createOppositeProperty("LookupEnvironment", Types._LookupEnvironment, 2, LookupPackage.Literals.LOOKUP_ENVIRONMENT__PARENT_ENV);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}


	// CTOR platform:/resource/org.eclipse.ocl.pivot/model/Pivot.ecore
	// CTOR http://www.eclipse.org/ocl/2015/Library
	// CTOR http://www.eclipse.org/ocl/2015/Orphanage
	// CTOR http://www.eclipse.org/emf/2002/Ecore
	// CTOR platform:/resource/org.eclipse.ocl.pivot/model/Lookup.ecore

	@SuppressWarnings("unused")
	private static Object unusedInit0 = new Object() {{
	}};

	// CTOR $$
	// CTOR ocl
	// CTOR ecore
	// CTOR lookup
	// CTOR pivot
	// CTOR pivot
	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final @NonNull FlatFragment @NonNull [] _Executor =
		{
			Fragments._Executor__OclAny /* 0 */,
			Fragments._Executor__OclElement /* 1 */,
			Fragments._Executor__Executor /* 2 */
		};
		private static final int @NonNull [] __Executor = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _LookupEnvironment =
		{
			Fragments._LookupEnvironment__OclAny /* 0 */,
			Fragments._LookupEnvironment__OclElement /* 1 */,
			Fragments._LookupEnvironment__LookupEnvironment /* 2 */
		};
		private static final int @NonNull [] __LookupEnvironment = { 1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Executor.initFragments(_Executor, __Executor);
			Types._LookupEnvironment.initFragments(_LookupEnvironment, __LookupEnvironment);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	@SuppressWarnings("unused")
	private static Object unusedInit1 = new Object() {{
		// LIBRARY.createComment(_ocl, "This clause describes the OCL Standard Library of predefined types, their operations, and predefined expression templates in the OCL.\nThis sub clause contains all standard types defined within OCL, including all the operations defined on those types.\nFor each operation the signature and a description of the semantics is given.\nWithin the description, the reserved word \u2018result\u2019 is used to refer to the value that results from evaluating the operation.\nIn several places, post conditions are used to describe properties of the result.\nWhen there is more than one postcondition, all postconditions must be true.\nA similar thing is true for multiple preconditions.\nIf these are used, the operation is only defined if all preconditions evaluate to oclText[true].\n\nheading:1[Introduction]\n\nThe structure, syntax, and semantics of the OCL is defined in Clauses 8 (\u201CAbstract Syntax\u201D), 9 (\u201CConcrete Syntax\u201D),\nand 10 (\u201CSemantics Described using UML\u201D).\nThis sub clause adds another part to the OCL definition: a library of predefined types and operations.\nAny implementation of OCL must include this library package. This approach has also been taken by e.g., the Java definition,\nwhere the language definition and the standard libraries are both mandatory parts of the complete language definition.\n\nThe OCL standard library defines a number of types.\nIt includes several primitive types: UnlimitedNatural, Integer, Real, String, and Boolean.\nThese are familiar from many other languages. The second part of the standard library consists of the collection types.\nThey are Bag, Set, Sequence, and Collection where Collection is an abstract type.\nNote that all types defined in the OCL standard library are instances of an abstract syntax class.\nThe OCL standard library exists at the modeling level, also referred to as the M1 level, where the abstract syntax is the metalevel or M2 level.\n\nNext to definitions of types the OCL standard library defines a number of template expressions.\nMany operations defined on collections map not on the abstract syntax metaclass FeatureCallExp, but on the IteratorExp.\nFor each of these a template expression that defines the name and format of the expression is defined in 11.8, Predefined Iterator Expressions.\n\nThe Standard Library may be extended with new types, new operations and new iterators.\nIn particular new operations can be defined for collections.\n\nCertain String operations depend on the prevailing locale to ensure that Strings are collated and characters are case-converted\nin an appropriate fashion.\nA locale is defined as a concatenation of up to three character sequences separated by underscores,\nwith the first sequence identifying the language and the second sequence identifying the country.\nThe third sequence is empty but may encode an implementation-specific variant.\nTrailing empty strings and separators may be omitted.\n\nThe character sequences for languages are defined by ISO 639.\n\nThe character sequences for countries are defined by ISO 3166.\n\n\u2018fr_CA\u2019 therefore identifies the locale for the French language in the Canada country.\n\nComparison of strings and consequently the collation order of Collection::sortedBy()\nconforms to the Unicode Collation algorithm defined by Unicode Technical Standard#10.\n\nThe locale is \u2018en_us\u2019 by default but may be configured by a property constraint on OclAny::oclLocale.\n\nThe prevailing locale is defined by the prevailing value of oclLocale within the current environment;\nit may therefore be changed temporarily by using a Let expression.\nlet oclLocale : String = \u2018fr_CA\u2019 in aString.toUpperCase()\n\nheading:1[Iterators]\n\nThis sub clause defines the standard OCL iterator expressions.\nIn the abstract syntax these are all instances of IteratorExp.\nThese iterator expressions always have a collection expression as their source,\nas is defined in the well-formedness rules in Clause 8 (\u201CAbstract Syntax\u201D).\nThe defined iterator expressions are shown per source collection type.\nThe semantics of each iterator expression is defined through a mapping from the iterator to the \u2018iterate\u2019 construct.\nThis means that the semantics of the iterator expressions do not need to be defined separately in the semantics sub clauses.\n\nIn all of the following OCL expressions, the lefthand side of the equals sign is the IteratorExp to be defined,\nand the righthand side of the equals sign is the equivalent as an IterateExp.\nThe names source, body, and iterator refer to the role names in the abstract syntax:\n\nsource\tThe source expression of the IteratorExp.\n\nbody\tThe body expression of the IteratorExp.\n\niterator\tThe iterator variable of the IteratorExp.\n\nresult\tThe result variable of the IterateExp.\n\nheading:2[Extending the Standard Library with Iterator Expressions]\n\nIt is possible to add new iterator expressions in the standard library.\nIf this is done the semantics of a new iterator should be defined by mapping it to existing constructs,\nin the same way the semantics of pre-defined iterators is done (see sub clause 11.9)");
	}};

	// CTOR ecore::EBoolean
	// CTOR OclElement
	// CTOR lookup::Executor
	// CTOR lookup::LookupEnvironment
	// CTOR NamedElement
	// CTOR NamedElement
	// CTOR Collection(addElements.NE)
	// CTOR Bag(lookup::LookupEnvironment)
	// CTOR OrderedSet(NamedElement)

	@SuppressWarnings("unused")
	private static Object unusedInit2 = new Object() {{
		// LIBRARY.createComment(_OclElement, "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes. Operations defined\nfor OclElement are therefore applicable to all user-defined types.");
		// LIBRARY.createComment(_NamedElement, "A NamedElement is an Element in a model that may have a name. The name may be given directly and/or via the use of a StringExpression.");
	}};

	// CTOR lookup::LookupEnvironment::LookupEnvironment
	// CTOR lookup::LookupEnvironment::namedElements
	// CTOR lookup::LookupEnvironment::parentEnv
	// CTOR pivot::NamedElement::LookupEnvironment
	// CTOR lookup::LookupEnvironment::addElement(NamedElement[?]) : lookup::LookupEnvironment[1]
	// CTOR lookup::LookupEnvironment::addElements(NE)(Collection(addElements.NE)) : lookup::LookupEnvironment[1]
	// CTOR lookup::LookupEnvironment::getExecutor() : lookup::Executor[?]
	// CTOR lookup::LookupEnvironment::hasFinalResult() : ecore::EBoolean[1]
	// SUPER_CLASSES lookup::Executor
	// SUPER_CLASSES lookup::LookupEnvironment

	@SuppressWarnings("unused")
	private static Object unusedInit3 = new Object() {{
	}};

	// TYPE lookup::LookupEnvironment::addElement(NamedElement[?]) : lookup::LookupEnvironment[1]
	// TYPE lookup::LookupEnvironment::addElements(NE)(Collection(addElements.NE)) : lookup::LookupEnvironment[1]
	// TYPE lookup::LookupEnvironment::getExecutor() : lookup::Executor[?]
	// TYPE lookup::LookupEnvironment::hasFinalResult() : ecore::EBoolean[1]

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

		private static final @NonNull Operation @NonNull [] _Executor__Executor = {};
		private static final @NonNull Operation @NonNull [] _Executor__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _Executor__OclElement = {
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

		private static final @NonNull Operation @NonNull [] _LookupEnvironment__LookupEnvironment = {
			LookupTables.Operations._LookupEnvironment__addElement /* addElement(NamedElement[?]) */,
			LookupTables.Operations._LookupEnvironment__addElements /* addElements(NE)(Collection(NE)) */,
			LookupTables.Operations._LookupEnvironment__getExecutor /* getExecutor() */,
			LookupTables.Operations._LookupEnvironment__hasFinalResult /* hasFinalResult() */
		};
		private static final @NonNull Operation @NonNull [] _LookupEnvironment__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _LookupEnvironment__OclElement = {
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
			Fragments._Executor__Executor.initOperations(_Executor__Executor);
			Fragments._Executor__OclAny.initOperations(_Executor__OclAny);
			Fragments._Executor__OclElement.initOperations(_Executor__OclElement);

			Fragments._LookupEnvironment__LookupEnvironment.initOperations(_LookupEnvironment__LookupEnvironment);
			Fragments._LookupEnvironment__OclAny.initOperations(_LookupEnvironment__OclAny);
			Fragments._LookupEnvironment__OclElement.initOperations(_LookupEnvironment__OclElement);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::FragmentOperations and all preceding sub-packages.
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

		private static final @NonNull Property @NonNull [] _Executor = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _LookupEnvironment = {
			LookupTables.Properties._LookupEnvironment__namedElements,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			LookupTables.Properties._LookupEnvironment__parentEnv
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Executor__Executor.initProperties(_Executor);
			Fragments._LookupEnvironment__LookupEnvironment.initProperties(_LookupEnvironment);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::FragmentProperties and all preceding sub-packages.
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

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::EnumerationLiterals and all preceding sub-packages.
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
		new LookupTables();
	}

	private LookupTables() {
		super(LookupPackage.eNS_URI);
	}
}
