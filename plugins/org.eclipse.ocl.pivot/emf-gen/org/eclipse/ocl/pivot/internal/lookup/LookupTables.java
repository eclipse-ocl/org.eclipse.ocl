/*******************************************************************************
 * Copyright (c) 2014, 2017 Willink Transformations Ltd., University of York and others.
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
 *   org.eclipse.ocl.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.lookup;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
// import org.eclipse.ocl.pivot.internal.lookup.LookupPackage;
// import org.eclipse.ocl.pivot.internal.lookup.LookupTables;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
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
	public static final PartialStandardLibraryImpl.@NonNull ReadOnly LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	The package descriptor for the package.
	 */
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(LookupPackage.eINSTANCE);

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ @NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
	public static final /*@NonInvalid*/ @NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Lookup", "lookup", LookupPackage.eINSTANCE);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Class = LookupTables.PACKid_$metamodel$.getClassId("Class", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_Executor = LookupTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("Executor", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_LookupEnvironment = LookupTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("LookupEnvironment", 0);
	public static final /*@NonInvalid*/ @NonNull ClassId CLSSid_NamedElement = LookupTables.PACKid_$metamodel$.getClassId("NamedElement", 0);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId COL_TMPLid_ = TypeId.COLLECTION.getSpecializedId(TypeId.T_1, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId BAG_CLSSid_LookupEnvironment = TypeId.BAG.getSpecializedId(LookupTables.CLSSid_LookupEnvironment, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull CollectionTypeId ORD_CLSSid_NamedElement = TypeId.ORDERED_SET.getSpecializedId(LookupTables.CLSSid_NamedElement, false, ValueUtil.ZERO_VALUE, ValueUtil.UNLIMITED_VALUE);
	public static final /*@NonInvalid*/ @NonNull PropertyId PROPid_namedElements = LookupTables.CLSSid_LookupEnvironment.getPropertyId("namedElements");

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			LookupTables.init();
		}

		public static final @NonNull TemplateParameter $$0 = LIBRARY.createTemplateParameter(0, "$$0");

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

		public static final org.eclipse.ocl.pivot.@NonNull Class _Executor = LIBRARY.createClass(PivotPackage.Literals.CLASS, LookupPackage.Literals.EXECUTOR, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _LookupEnvironment = LIBRARY.createClass(PivotPackage.Literals.CLASS, LookupPackage.Literals.LOOKUP_ENVIRONMENT, null, 0);

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

		private static final @NonNull FlatFragment _Executor__Executor = LIBRARY.createFragment(Types._Executor, Types._Executor);
		private static final @NonNull FlatFragment _Executor__OclAny = LIBRARY.createFragment(Types._Executor, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Executor__OclElement = LIBRARY.createFragment(Types._Executor, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _LookupEnvironment__LookupEnvironment = LIBRARY.createFragment(Types._LookupEnvironment, Types._LookupEnvironment);
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

		public static final @NonNull ParameterTypes _Collection__NE__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._Collection, TypeParameters.$$0));
		public static final @NonNull ParameterTypes _NamedElement = new ParameterTypes(PivotTables.Types._NamedElement);

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

		public static final @NonNull Operation _LookupEnvironment__addElement = LIBRARY.createOperation(Types._LookupEnvironment, "addElement", Parameters._NamedElement, Types._LookupEnvironment,
			0 | IsRequired, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _LookupEnvironment__addElements = LIBRARY.createOperation(Types._LookupEnvironment, "addElements", Parameters._Collection__NE__, Types._LookupEnvironment,
			1 | IsRequired, new TemplateParameters(TypeParameters.$$0), null);
		public static final @NonNull Operation _LookupEnvironment__getExecutor = LIBRARY.createOperation(Types._LookupEnvironment, "getExecutor", ParameterTypes.EMPTY_LIST, Types._Executor,
			2, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _LookupEnvironment__hasFinalResult = LIBRARY.createOperation(Types._LookupEnvironment, "hasFinalResult", ParameterTypes.EMPTY_LIST, OCLstdlibTables.Types._Boolean,
			3 | IsRequired, TemplateParameters.EMPTY_LIST, null);

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

		public static final @NonNull Property _LookupEnvironment__namedElements = LIBRARY.createProperty(Types._LookupEnvironment, LookupPackage.Literals.LOOKUP_ENVIRONMENT__NAMED_ELEMENTS, LIBRARY.getCollectionType(OCLstdlibTables.Types._OrderedSet, PivotTables.Types._NamedElement), 0 | IsRequired | IsResolveProxies);
		public static final @NonNull Property _LookupEnvironment__parentEnv = LIBRARY.createProperty(Types._LookupEnvironment, LookupPackage.Literals.LOOKUP_ENVIRONMENT__PARENT_ENV, Types._LookupEnvironment, 1 | IsResolveProxies);
		public static final @NonNull Property _LookupEnvironment__LookupEnvironment__parentEnv = LIBRARY.createOppositeProperty(Types._LookupEnvironment, "LookupEnvironment", LIBRARY.getCollectionType(OCLstdlibTables.Types._Bag, Types._LookupEnvironment), 2 | IsImplicit | IsRequired | IsResolveProxies, LookupPackage.Literals.LOOKUP_ENVIRONMENT__PARENT_ENV);

		static {
			_LookupEnvironment__namedElements.setOpposite(createOpposite(PivotPackage.Literals.NAMED_ELEMENT, "LookupEnvironment", _LookupEnvironment__namedElements));
			_LookupEnvironment__parentEnv.setOpposite(_LookupEnvironment__LookupEnvironment__parentEnv);
			_LookupEnvironment__LookupEnvironment__parentEnv.setOpposite(_LookupEnvironment__parentEnv);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of LookupTables::Properties and all preceding sub-packages.
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
			Operations._LookupEnvironment__addElement /* addElement(NamedElement[?]) */,
			Operations._LookupEnvironment__addElements /* addElements(NE)(Collection(NE)) */,
			Operations._LookupEnvironment__getExecutor /* getExecutor() */,
			Operations._LookupEnvironment__hasFinalResult /* hasFinalResult() */
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
			Properties._LookupEnvironment__namedElements,
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents,
			Properties._LookupEnvironment__parentEnv
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
