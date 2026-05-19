/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.xtext.tests/models/genmodel/Dummy.ecore
 * using:
 *   /org.eclipse.ocl.xtext.tests/models/genmodel/Dummy.genmodel
 *   org.eclipse.ocl.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.dummy;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.internal.library.PartialStandardLibrary;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
// import org.eclipse.ocl.xtext.tests.dummy.DummyPackage;
// import org.eclipse.ocl.xtext.tests.dummy.DummyTables;

/**
 * DummyTables provides the dispatch tables for the dummy for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
public class DummyTables extends AbstractTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The overall library of all packages and types.
	 */
	public static final PartialStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	The AS package for the DummyPackage.eINSTANCE EPackage.
	 */
	public static final org.eclipse.ocl.pivot.Package PACKAGE = LIBRARY.createPackage(DummyPackage.eINSTANCE);

	/**
	 *	The AS model for the AS package and its additional orphans.
	 */
	public static final AbstractTables.BuiltInModel MODEL = LIBRARY.createModel(PACKAGE);

	/**
	 *	The EMF Resource containing the AS model, its AS package and its additional orphans.
	 */
	public static final Resource RESOURCE = LIBRARY.createResource(MODEL);

	/**
	 * A <code>Descriptor</code> may be used by the {@link BuiltInASResourceFactory.Registry}
	 * to defer loading of a built-in model until needed.
	 *
	 * @since 7.0
	 */
	public static class Descriptor implements CompletePackageIdRegistryReader.Descriptor
	{
		@Override
		public Model getModel() {
			return MODEL;
		}
	}

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Dummy_ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/test/Pivot/Dummy.ecore", "dummy", DummyPackage.eINSTANCE);
	public static final /*@NonInvalid*/ ClassId CLSSid_Dummy = DummyTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_test_s_Pivot_s_Dummy_ecore.getClassId("Dummy", 0);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			DummyTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::TypeParameters and all preceding sub-packages.
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

		public static final org.eclipse.ocl.pivot.Class _Dummy = LIBRARY.createClass(PivotPackage.Literals.CLASS, DummyPackage.Literals.DUMMY, null, 0);

		/*
		 * AS Class indexed by EClassifier.getClassifierID().
		 */
		private static final org.eclipse.ocl.pivot.Class /*@NonNull*/ [] eClassifierID2asClass = {
			/* 0 */ _Dummy
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			LIBRARY.initPackage(PACKAGE, eClassifierID2asClass);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::Types and all preceding sub-packages.
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

		private static final FlatFragment _Dummy__Dummy = LIBRARY.createFragment(Types._Dummy, Types._Dummy);
		private static final FlatFragment _Dummy__OclAny = LIBRARY.createFragment(Types._Dummy, OCLstdlibTables.Types._OclAny);
		private static final FlatFragment _Dummy__OclElement = LIBRARY.createFragment(Types._Dummy, OCLstdlibTables.Types._OclElement);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::Fragments and all preceding sub-packages.
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
		 * Force initialization of the fields of DummyTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The invariant descriptors for each invariant of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Constraints {
		static {
			Init.initStart();
			Parameters.init();
		}

		public static final Constraint _Dummy__dummyInvariant = LIBRARY.createConstraint(Types._Dummy, DummyPackage.Literals.DUMMY___DUMMY_INVARIANT__DIAGNOSTICCHAIN_MAP, "dummyInvariant", "true");

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::Constraints and all preceding sub-packages.
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
			Constraints.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::Operations and all preceding sub-packages.
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

		public static final Property _Dummy__name = LIBRARY.createProperty(Types._Dummy, DummyPackage.Literals.DUMMY__NAME, OCLstdlibTables.Types._String, 0 | IsRequired | IsResolveProxies);

		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::Properties and all preceding sub-packages.
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

		private static final FlatFragment /*@NonNull*/ [] _Dummy =
			{
				Fragments._Dummy__OclAny /* 0 */,
				Fragments._Dummy__OclElement /* 1 */,
				Fragments._Dummy__Dummy /* 2 */
			};
		private static final int /*@NonNull*/ [] __Dummy = { 0,1,2 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Dummy.initFragments(_Dummy, __Dummy);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::TypeFragments and all preceding sub-packages.
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

		private static final Operation /*@NonNull*/ [] _Dummy__Dummy = {};
		private static final Operation /*@NonNull*/ [] _Dummy__OclAny = {
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
		private static final Operation /*@NonNull*/ [] _Dummy__OclElement = {
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
			Fragments._Dummy__Dummy.initOperations(_Dummy__Dummy);
			Fragments._Dummy__OclAny.initOperations(_Dummy__OclAny);
			Fragments._Dummy__OclElement.initOperations(_Dummy__OclElement);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::FragmentOperations and all preceding sub-packages.
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

		private static final Property /*@NonNull*/ [] _Dummy = {
			Properties._Dummy__name,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Dummy__Dummy.initProperties(_Dummy);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of DummyTables::FragmentProperties and all preceding sub-packages.
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
		 * Force initialization of the fields of DummyTables::EnumerationLiterals and all preceding sub-packages.
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
		new DummyTables();
	}

	private DummyTables() {
		super(DummyPackage.eNS_URI);
	}
}
