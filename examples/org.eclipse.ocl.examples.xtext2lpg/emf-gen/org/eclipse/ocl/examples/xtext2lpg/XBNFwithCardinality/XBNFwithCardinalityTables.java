/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.examples.xtext2lpg/model/XBNFwithCardinality.ecore
 * using:
 *   /org.eclipse.ocl.examples.xtext2lpg/model/XBNFwithCardinality.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFTables;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityTables;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;

/**
 * XBNFwithCardinalityTables provides the dispatch tables for the XBNFwithCardinality for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
@SuppressWarnings("nls")
public class XBNFwithCardinalityTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final /*@NonNull*/ EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(XBNFwithCardinalityPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final /*@NonNull*/ ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_XBNF = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/XBNF", null, org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage.eINSTANCE);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.ClassId CLSSid_AbstractElement = org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_XBNF.getClassId("AbstractElement", 0);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.CollectionTypeId ORD_CLSSid_AbstractElement = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityTables.CLSSid_AbstractElement);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			XBNFwithCardinalityTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::TypeParameters and all preceding sub-packages.
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

		public static final /*@NonNull*/ EcoreExecutorType _Alternatives = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.ALTERNATIVES, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _CompoundElement = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.COMPOUND_ELEMENT, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final /*@NonNull*/ EcoreExecutorType _MultiplicityElement = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.MULTIPLICITY_ELEMENT, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final /*@NonNull*/ EcoreExecutorType _OneOrMore = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.ONE_OR_MORE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Succession = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.SUCCESSION, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _ZeroOrMore = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.ZERO_OR_MORE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _ZeroOrOne = new EcoreExecutorType(XBNFwithCardinalityPackage.Literals.ZERO_OR_ONE, PACKAGE, 0);

		private static final /*@NonNull*/ EcoreExecutorType /*@NonNull*/ [] types = {
			_Alternatives,
			_CompoundElement,
			_MultiplicityElement,
			_OneOrMore,
			_Succession,
			_ZeroOrMore,
			_ZeroOrOne
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::Types and all preceding sub-packages.
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

		private static final /*@NonNull*/ ExecutorFragment _Alternatives__AbstractElement = new ExecutorFragment(Types._Alternatives, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Alternatives__Alternatives = new ExecutorFragment(Types._Alternatives, XBNFwithCardinalityTables.Types._Alternatives);
		private static final /*@NonNull*/ ExecutorFragment _Alternatives__CompoundElement = new ExecutorFragment(Types._Alternatives, XBNFwithCardinalityTables.Types._CompoundElement);
		private static final /*@NonNull*/ ExecutorFragment _Alternatives__OclAny = new ExecutorFragment(Types._Alternatives, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Alternatives__OclElement = new ExecutorFragment(Types._Alternatives, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _CompoundElement__AbstractElement = new ExecutorFragment(Types._CompoundElement, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _CompoundElement__CompoundElement = new ExecutorFragment(Types._CompoundElement, XBNFwithCardinalityTables.Types._CompoundElement);
		private static final /*@NonNull*/ ExecutorFragment _CompoundElement__OclAny = new ExecutorFragment(Types._CompoundElement, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _CompoundElement__OclElement = new ExecutorFragment(Types._CompoundElement, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _MultiplicityElement__AbstractElement = new ExecutorFragment(Types._MultiplicityElement, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _MultiplicityElement__MultiplicityElement = new ExecutorFragment(Types._MultiplicityElement, XBNFwithCardinalityTables.Types._MultiplicityElement);
		private static final /*@NonNull*/ ExecutorFragment _MultiplicityElement__OclAny = new ExecutorFragment(Types._MultiplicityElement, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _MultiplicityElement__OclElement = new ExecutorFragment(Types._MultiplicityElement, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _OneOrMore__AbstractElement = new ExecutorFragment(Types._OneOrMore, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _OneOrMore__MultiplicityElement = new ExecutorFragment(Types._OneOrMore, XBNFwithCardinalityTables.Types._MultiplicityElement);
		private static final /*@NonNull*/ ExecutorFragment _OneOrMore__OclAny = new ExecutorFragment(Types._OneOrMore, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _OneOrMore__OclElement = new ExecutorFragment(Types._OneOrMore, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _OneOrMore__OneOrMore = new ExecutorFragment(Types._OneOrMore, XBNFwithCardinalityTables.Types._OneOrMore);

		private static final /*@NonNull*/ ExecutorFragment _Succession__AbstractElement = new ExecutorFragment(Types._Succession, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Succession__CompoundElement = new ExecutorFragment(Types._Succession, XBNFwithCardinalityTables.Types._CompoundElement);
		private static final /*@NonNull*/ ExecutorFragment _Succession__OclAny = new ExecutorFragment(Types._Succession, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Succession__OclElement = new ExecutorFragment(Types._Succession, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _Succession__Succession = new ExecutorFragment(Types._Succession, XBNFwithCardinalityTables.Types._Succession);

		private static final /*@NonNull*/ ExecutorFragment _ZeroOrMore__AbstractElement = new ExecutorFragment(Types._ZeroOrMore, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrMore__MultiplicityElement = new ExecutorFragment(Types._ZeroOrMore, XBNFwithCardinalityTables.Types._MultiplicityElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrMore__OclAny = new ExecutorFragment(Types._ZeroOrMore, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrMore__OclElement = new ExecutorFragment(Types._ZeroOrMore, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrMore__ZeroOrMore = new ExecutorFragment(Types._ZeroOrMore, XBNFwithCardinalityTables.Types._ZeroOrMore);

		private static final /*@NonNull*/ ExecutorFragment _ZeroOrOne__AbstractElement = new ExecutorFragment(Types._ZeroOrOne, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrOne__MultiplicityElement = new ExecutorFragment(Types._ZeroOrOne, XBNFwithCardinalityTables.Types._MultiplicityElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrOne__OclAny = new ExecutorFragment(Types._ZeroOrOne, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrOne__OclElement = new ExecutorFragment(Types._ZeroOrOne, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _ZeroOrOne__ZeroOrOne = new ExecutorFragment(Types._ZeroOrOne, XBNFwithCardinalityTables.Types._ZeroOrOne);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::Fragments and all preceding sub-packages.
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
		 * Force initialization of the fields of XBNFwithCardinalityTables::Parameters and all preceding sub-packages.
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

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::Operations and all preceding sub-packages.
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


		public static final /*@NonNull*/ ExecutorProperty _CompoundElement__elements = new EcoreExecutorProperty(XBNFwithCardinalityPackage.Literals.COMPOUND_ELEMENT__ELEMENTS, Types._CompoundElement, 0);

		public static final /*@NonNull*/ ExecutorProperty _MultiplicityElement__element = new EcoreExecutorProperty(XBNFwithCardinalityPackage.Literals.MULTIPLICITY_ELEMENT__ELEMENT, Types._MultiplicityElement, 0);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::Properties and all preceding sub-packages.
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

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Alternatives =
		{
			Fragments._Alternatives__OclAny /* 0 */,
			Fragments._Alternatives__OclElement /* 1 */,
			Fragments._Alternatives__AbstractElement /* 2 */,
			Fragments._Alternatives__CompoundElement /* 3 */,
			Fragments._Alternatives__Alternatives /* 4 */
		};
		private static final int /*@NonNull*/ [] __Alternatives = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _CompoundElement =
		{
			Fragments._CompoundElement__OclAny /* 0 */,
			Fragments._CompoundElement__OclElement /* 1 */,
			Fragments._CompoundElement__AbstractElement /* 2 */,
			Fragments._CompoundElement__CompoundElement /* 3 */
		};
		private static final int /*@NonNull*/ [] __CompoundElement = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _MultiplicityElement =
		{
			Fragments._MultiplicityElement__OclAny /* 0 */,
			Fragments._MultiplicityElement__OclElement /* 1 */,
			Fragments._MultiplicityElement__AbstractElement /* 2 */,
			Fragments._MultiplicityElement__MultiplicityElement /* 3 */
		};
		private static final int /*@NonNull*/ [] __MultiplicityElement = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _OneOrMore =
		{
			Fragments._OneOrMore__OclAny /* 0 */,
			Fragments._OneOrMore__OclElement /* 1 */,
			Fragments._OneOrMore__AbstractElement /* 2 */,
			Fragments._OneOrMore__MultiplicityElement /* 3 */,
			Fragments._OneOrMore__OneOrMore /* 4 */
		};
		private static final int /*@NonNull*/ [] __OneOrMore = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Succession =
		{
			Fragments._Succession__OclAny /* 0 */,
			Fragments._Succession__OclElement /* 1 */,
			Fragments._Succession__AbstractElement /* 2 */,
			Fragments._Succession__CompoundElement /* 3 */,
			Fragments._Succession__Succession /* 4 */
		};
		private static final int /*@NonNull*/ [] __Succession = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _ZeroOrMore =
		{
			Fragments._ZeroOrMore__OclAny /* 0 */,
			Fragments._ZeroOrMore__OclElement /* 1 */,
			Fragments._ZeroOrMore__AbstractElement /* 2 */,
			Fragments._ZeroOrMore__MultiplicityElement /* 3 */,
			Fragments._ZeroOrMore__ZeroOrMore /* 4 */
		};
		private static final int /*@NonNull*/ [] __ZeroOrMore = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _ZeroOrOne =
		{
			Fragments._ZeroOrOne__OclAny /* 0 */,
			Fragments._ZeroOrOne__OclElement /* 1 */,
			Fragments._ZeroOrOne__AbstractElement /* 2 */,
			Fragments._ZeroOrOne__MultiplicityElement /* 3 */,
			Fragments._ZeroOrOne__ZeroOrOne /* 4 */
		};
		private static final int /*@NonNull*/ [] __ZeroOrOne = { 1,1,1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Alternatives.initFragments(_Alternatives, __Alternatives);
			Types._CompoundElement.initFragments(_CompoundElement, __CompoundElement);
			Types._MultiplicityElement.initFragments(_MultiplicityElement, __MultiplicityElement);
			Types._OneOrMore.initFragments(_OneOrMore, __OneOrMore);
			Types._Succession.initFragments(_Succession, __Succession);
			Types._ZeroOrMore.initFragments(_ZeroOrMore, __ZeroOrMore);
			Types._ZeroOrOne.initFragments(_ZeroOrOne, __ZeroOrOne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::TypeFragments and all preceding sub-packages.
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

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Alternatives__Alternatives = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Alternatives__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Alternatives__CompoundElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Alternatives__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Alternatives__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CompoundElement__CompoundElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CompoundElement__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CompoundElement__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CompoundElement__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MultiplicityElement__MultiplicityElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MultiplicityElement__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MultiplicityElement__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MultiplicityElement__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _OneOrMore__OneOrMore = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _OneOrMore__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _OneOrMore__MultiplicityElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _OneOrMore__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _OneOrMore__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Succession__Succession = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Succession__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Succession__CompoundElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Succession__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Succession__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrMore__ZeroOrMore = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrMore__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrMore__MultiplicityElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrMore__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrMore__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrOne__ZeroOrOne = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrOne__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrOne__MultiplicityElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrOne__OclAny = {
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
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ZeroOrOne__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Alternatives__AbstractElement.initOperations(_Alternatives__AbstractElement);
			Fragments._Alternatives__Alternatives.initOperations(_Alternatives__Alternatives);
			Fragments._Alternatives__CompoundElement.initOperations(_Alternatives__CompoundElement);
			Fragments._Alternatives__OclAny.initOperations(_Alternatives__OclAny);
			Fragments._Alternatives__OclElement.initOperations(_Alternatives__OclElement);

			Fragments._CompoundElement__AbstractElement.initOperations(_CompoundElement__AbstractElement);
			Fragments._CompoundElement__CompoundElement.initOperations(_CompoundElement__CompoundElement);
			Fragments._CompoundElement__OclAny.initOperations(_CompoundElement__OclAny);
			Fragments._CompoundElement__OclElement.initOperations(_CompoundElement__OclElement);

			Fragments._MultiplicityElement__AbstractElement.initOperations(_MultiplicityElement__AbstractElement);
			Fragments._MultiplicityElement__MultiplicityElement.initOperations(_MultiplicityElement__MultiplicityElement);
			Fragments._MultiplicityElement__OclAny.initOperations(_MultiplicityElement__OclAny);
			Fragments._MultiplicityElement__OclElement.initOperations(_MultiplicityElement__OclElement);

			Fragments._OneOrMore__AbstractElement.initOperations(_OneOrMore__AbstractElement);
			Fragments._OneOrMore__MultiplicityElement.initOperations(_OneOrMore__MultiplicityElement);
			Fragments._OneOrMore__OclAny.initOperations(_OneOrMore__OclAny);
			Fragments._OneOrMore__OclElement.initOperations(_OneOrMore__OclElement);
			Fragments._OneOrMore__OneOrMore.initOperations(_OneOrMore__OneOrMore);

			Fragments._Succession__AbstractElement.initOperations(_Succession__AbstractElement);
			Fragments._Succession__CompoundElement.initOperations(_Succession__CompoundElement);
			Fragments._Succession__OclAny.initOperations(_Succession__OclAny);
			Fragments._Succession__OclElement.initOperations(_Succession__OclElement);
			Fragments._Succession__Succession.initOperations(_Succession__Succession);

			Fragments._ZeroOrMore__AbstractElement.initOperations(_ZeroOrMore__AbstractElement);
			Fragments._ZeroOrMore__MultiplicityElement.initOperations(_ZeroOrMore__MultiplicityElement);
			Fragments._ZeroOrMore__OclAny.initOperations(_ZeroOrMore__OclAny);
			Fragments._ZeroOrMore__OclElement.initOperations(_ZeroOrMore__OclElement);
			Fragments._ZeroOrMore__ZeroOrMore.initOperations(_ZeroOrMore__ZeroOrMore);

			Fragments._ZeroOrOne__AbstractElement.initOperations(_ZeroOrOne__AbstractElement);
			Fragments._ZeroOrOne__MultiplicityElement.initOperations(_ZeroOrOne__MultiplicityElement);
			Fragments._ZeroOrOne__OclAny.initOperations(_ZeroOrOne__OclAny);
			Fragments._ZeroOrOne__OclElement.initOperations(_ZeroOrOne__OclElement);
			Fragments._ZeroOrOne__ZeroOrOne.initOperations(_ZeroOrOne__ZeroOrOne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::FragmentOperations and all preceding sub-packages.
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

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Alternatives = {
			XBNFwithCardinalityTables.Properties._CompoundElement__elements
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _CompoundElement = {
			XBNFwithCardinalityTables.Properties._CompoundElement__elements
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _MultiplicityElement = {
			XBNFwithCardinalityTables.Properties._MultiplicityElement__element
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _OneOrMore = {
			XBNFwithCardinalityTables.Properties._MultiplicityElement__element
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Succession = {
			XBNFwithCardinalityTables.Properties._CompoundElement__elements
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _ZeroOrMore = {
			XBNFwithCardinalityTables.Properties._MultiplicityElement__element
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _ZeroOrOne = {
			XBNFwithCardinalityTables.Properties._MultiplicityElement__element
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Alternatives__Alternatives.initProperties(_Alternatives);
			Fragments._CompoundElement__CompoundElement.initProperties(_CompoundElement);
			Fragments._MultiplicityElement__MultiplicityElement.initProperties(_MultiplicityElement);
			Fragments._OneOrMore__OneOrMore.initProperties(_OneOrMore);
			Fragments._Succession__Succession.initProperties(_Succession);
			Fragments._ZeroOrMore__ZeroOrMore.initProperties(_ZeroOrMore);
			Fragments._ZeroOrOne__ZeroOrOne.initProperties(_ZeroOrOne);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::FragmentProperties and all preceding sub-packages.
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

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFwithCardinalityTables::EnumerationLiterals and all preceding sub-packages.
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
