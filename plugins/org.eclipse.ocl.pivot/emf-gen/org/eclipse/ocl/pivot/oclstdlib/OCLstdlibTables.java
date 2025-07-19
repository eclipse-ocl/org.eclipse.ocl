/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
 *   http://www.eclipse.org/ocl/2015/Pivot
 * using:
 *   /org.eclipse.ocl.pivot/model/oclstdlib.genmodel
 *   org.eclipse.ocl.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.pivot.oclstdlib;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
// import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.utilities.AbstractTables;

/**
 * OCLstdlibTables provides the dispatch tables for the ocl for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
public class OCLstdlibTables extends AbstractTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The library of all packages and types.
	 */
	public static final PartialStandardLibraryImpl.@NonNull ReadOnly LIBRARY = new PartialStandardLibraryImpl.ReadOnly();

	/**
	 *	The package descriptor for the package.
	 */
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(OCLstdlibPackage.eINSTANCE, IdManager.METAMODEL);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			OCLstdlibTables.init();
		}

		public static final @NonNull TemplateParameter $$0 = LIBRARY.createTemplateParameter(0, "$$0");
		public static final @NonNull TemplateParameter $$1 = LIBRARY.createTemplateParameter(1, "$$1");
		public static final @NonNull TemplateParameter $$2 = LIBRARY.createTemplateParameter(2, "$$2");
		public static final @NonNull TemplateParameter $$3 = LIBRARY.createTemplateParameter(3, "$$3");

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::TypeParameters and all preceding sub-packages.
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

		public static final org.eclipse.ocl.pivot.@NonNull Class _Bag = LIBRARY.createClass(PivotPackage.Literals.BAG_TYPE, OCLstdlibPackage.Literals.BAG, PACKAGE, TypeId.BAG, 0, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Boolean = LIBRARY.createClass(PivotPackage.Literals.BOOLEAN_TYPE, OCLstdlibPackage.Literals.BOOLEAN, PACKAGE, TypeId.BOOLEAN, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Collection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.COLLECTION, PACKAGE, TypeId.COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Integer = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.INTEGER, PACKAGE, TypeId.INTEGER, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Map = LIBRARY.createClass(PivotPackage.Literals.MAP_TYPE, OCLstdlibPackage.Literals.MAP, PACKAGE, TypeId.MAP, 0, TypeParameters.$$0, TypeParameters.$$1);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclAny = LIBRARY.createClass(PivotPackage.Literals.ANY_TYPE, OCLstdlibPackage.Literals.OCL_ANY, PACKAGE, TypeId.OCL_ANY, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclComparable = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_COMPARABLE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclElement = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_ELEMENT, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclEnumeration = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_ENUMERATION, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclInvalid = LIBRARY.createClass(PivotPackage.Literals.INVALID_TYPE, OCLstdlibPackage.Literals.OCL_INVALID, PACKAGE, TypeId.OCL_INVALID, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclLambda = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_LAMBDA, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclMessage = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_MESSAGE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclSelf = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_SELF, PACKAGE, TypeId.OCL_SELF, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclState = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_STATE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclStereotype = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_STEREOTYPE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclSummable = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_SUMMABLE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclTuple = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_TUPLE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclType = LIBRARY.createClass(PivotPackage.Literals.CLASS, OCLstdlibPackage.Literals.OCL_TYPE, PACKAGE, null, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OclVoid = LIBRARY.createClass(PivotPackage.Literals.VOID_TYPE, OCLstdlibPackage.Literals.OCL_VOID, PACKAGE, TypeId.OCL_VOID, 0 | FlatClass.ABSTRACT);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OrderedCollection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.ORDERED_COLLECTION, PACKAGE, TypeId.ORDERED_COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OrderedSet = LIBRARY.createClass(PivotPackage.Literals.ORDERED_SET_TYPE, OCLstdlibPackage.Literals.ORDERED_SET, PACKAGE, TypeId.ORDERED_SET, FlatClass.ORDERED | FlatClass.UNIQUE, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Real = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.REAL, PACKAGE, TypeId.REAL, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Sequence = LIBRARY.createClass(PivotPackage.Literals.SEQUENCE_TYPE, OCLstdlibPackage.Literals.SEQUENCE, PACKAGE, TypeId.SEQUENCE, FlatClass.ORDERED, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Set = LIBRARY.createClass(PivotPackage.Literals.SET_TYPE, OCLstdlibPackage.Literals.SET, PACKAGE, TypeId.SET, FlatClass.UNIQUE, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _String = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.STRING, PACKAGE, TypeId.STRING, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _UniqueCollection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.UNIQUE_COLLECTION, PACKAGE, TypeId.UNIQUE_COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters.$$0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _UnlimitedNatural = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.UNLIMITED_NATURAL, PACKAGE, TypeId.UNLIMITED_NATURAL, 0);

		private static final org.eclipse.ocl.pivot.@NonNull Class @NonNull [] types = {
			_Bag,
			_Boolean,
			_Collection,
			_Integer,
			_Map,
			_OclAny,
			_OclComparable,
			_OclElement,
			_OclEnumeration,
			_OclInvalid,
			_OclLambda,
			_OclMessage,
			_OclSelf,
			_OclState,
			_OclStereotype,
			_OclSummable,
			_OclTuple,
			_OclType,
			_OclVoid,
			_OrderedCollection,
			_OrderedSet,
			_Real,
			_Sequence,
			_Set,
			_String,
			_UniqueCollection,
			_UnlimitedNatural
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			LIBRARY.initPackage(PACKAGE, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Types and all preceding sub-packages.
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

		private static final @NonNull FlatFragment _Bag__Bag = LIBRARY.createFragment(Types._Bag, OCLstdlibTables.Types._Bag);
		private static final @NonNull FlatFragment _Bag__Collection = LIBRARY.createFragment(Types._Bag, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _Bag__OclAny = LIBRARY.createFragment(Types._Bag, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _Boolean__Boolean = LIBRARY.createFragment(Types._Boolean, OCLstdlibTables.Types._Boolean);
		private static final @NonNull FlatFragment _Boolean__OclAny = LIBRARY.createFragment(Types._Boolean, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _Collection__Collection = LIBRARY.createFragment(Types._Collection, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _Collection__OclAny = LIBRARY.createFragment(Types._Collection, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _Integer__Integer = LIBRARY.createFragment(Types._Integer, OCLstdlibTables.Types._Integer);
		private static final @NonNull FlatFragment _Integer__OclAny = LIBRARY.createFragment(Types._Integer, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Integer__OclComparable = LIBRARY.createFragment(Types._Integer, OCLstdlibTables.Types._OclComparable);
		private static final @NonNull FlatFragment _Integer__OclSummable = LIBRARY.createFragment(Types._Integer, OCLstdlibTables.Types._OclSummable);
		private static final @NonNull FlatFragment _Integer__Real = LIBRARY.createFragment(Types._Integer, OCLstdlibTables.Types._Real);

		private static final @NonNull FlatFragment _Map__Map = LIBRARY.createFragment(Types._Map, OCLstdlibTables.Types._Map);
		private static final @NonNull FlatFragment _Map__OclAny = LIBRARY.createFragment(Types._Map, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _OclAny__OclAny = LIBRARY.createFragment(Types._OclAny, OCLstdlibTables.Types._OclAny);

		private static final @NonNull FlatFragment _OclComparable__OclAny = LIBRARY.createFragment(Types._OclComparable, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclComparable__OclComparable = LIBRARY.createFragment(Types._OclComparable, OCLstdlibTables.Types._OclComparable);

		private static final @NonNull FlatFragment _OclElement__OclAny = LIBRARY.createFragment(Types._OclElement, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclElement__OclElement = LIBRARY.createFragment(Types._OclElement, OCLstdlibTables.Types._OclElement);

		private static final @NonNull FlatFragment _OclEnumeration__OclAny = LIBRARY.createFragment(Types._OclEnumeration, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclEnumeration__OclElement = LIBRARY.createFragment(Types._OclEnumeration, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _OclEnumeration__OclEnumeration = LIBRARY.createFragment(Types._OclEnumeration, OCLstdlibTables.Types._OclEnumeration);
		private static final @NonNull FlatFragment _OclEnumeration__OclType = LIBRARY.createFragment(Types._OclEnumeration, OCLstdlibTables.Types._OclType);

		private static final @NonNull FlatFragment _OclInvalid__OclAny = LIBRARY.createFragment(Types._OclInvalid, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclInvalid__OclInvalid = LIBRARY.createFragment(Types._OclInvalid, OCLstdlibTables.Types._OclInvalid);
		private static final @NonNull FlatFragment _OclInvalid__OclVoid = LIBRARY.createFragment(Types._OclInvalid, OCLstdlibTables.Types._OclVoid);

		private static final @NonNull FlatFragment _OclLambda__OclAny = LIBRARY.createFragment(Types._OclLambda, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclLambda__OclLambda = LIBRARY.createFragment(Types._OclLambda, OCLstdlibTables.Types._OclLambda);

		private static final @NonNull FlatFragment _OclMessage__OclAny = LIBRARY.createFragment(Types._OclMessage, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclMessage__OclMessage = LIBRARY.createFragment(Types._OclMessage, OCLstdlibTables.Types._OclMessage);

		private static final @NonNull FlatFragment _OclSelf__OclAny = LIBRARY.createFragment(Types._OclSelf, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclSelf__OclSelf = LIBRARY.createFragment(Types._OclSelf, OCLstdlibTables.Types._OclSelf);

		private static final @NonNull FlatFragment _OclState__OclAny = LIBRARY.createFragment(Types._OclState, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclState__OclState = LIBRARY.createFragment(Types._OclState, OCLstdlibTables.Types._OclState);

		private static final @NonNull FlatFragment _OclStereotype__OclAny = LIBRARY.createFragment(Types._OclStereotype, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclStereotype__OclElement = LIBRARY.createFragment(Types._OclStereotype, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _OclStereotype__OclStereotype = LIBRARY.createFragment(Types._OclStereotype, OCLstdlibTables.Types._OclStereotype);
		private static final @NonNull FlatFragment _OclStereotype__OclType = LIBRARY.createFragment(Types._OclStereotype, OCLstdlibTables.Types._OclType);

		private static final @NonNull FlatFragment _OclSummable__OclAny = LIBRARY.createFragment(Types._OclSummable, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclSummable__OclSummable = LIBRARY.createFragment(Types._OclSummable, OCLstdlibTables.Types._OclSummable);

		private static final @NonNull FlatFragment _OclTuple__OclAny = LIBRARY.createFragment(Types._OclTuple, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclTuple__OclTuple = LIBRARY.createFragment(Types._OclTuple, OCLstdlibTables.Types._OclTuple);

		private static final @NonNull FlatFragment _OclType__OclAny = LIBRARY.createFragment(Types._OclType, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclType__OclElement = LIBRARY.createFragment(Types._OclType, OCLstdlibTables.Types._OclElement);
		private static final @NonNull FlatFragment _OclType__OclType = LIBRARY.createFragment(Types._OclType, OCLstdlibTables.Types._OclType);

		private static final @NonNull FlatFragment _OclVoid__OclAny = LIBRARY.createFragment(Types._OclVoid, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OclVoid__OclVoid = LIBRARY.createFragment(Types._OclVoid, OCLstdlibTables.Types._OclVoid);

		private static final @NonNull FlatFragment _OrderedCollection__Collection = LIBRARY.createFragment(Types._OrderedCollection, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _OrderedCollection__OclAny = LIBRARY.createFragment(Types._OrderedCollection, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OrderedCollection__OrderedCollection = LIBRARY.createFragment(Types._OrderedCollection, OCLstdlibTables.Types._OrderedCollection);

		private static final @NonNull FlatFragment _OrderedSet__Collection = LIBRARY.createFragment(Types._OrderedSet, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _OrderedSet__OclAny = LIBRARY.createFragment(Types._OrderedSet, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _OrderedSet__OrderedCollection = LIBRARY.createFragment(Types._OrderedSet, OCLstdlibTables.Types._OrderedCollection);
		private static final @NonNull FlatFragment _OrderedSet__OrderedSet = LIBRARY.createFragment(Types._OrderedSet, OCLstdlibTables.Types._OrderedSet);
		private static final @NonNull FlatFragment _OrderedSet__UniqueCollection = LIBRARY.createFragment(Types._OrderedSet, OCLstdlibTables.Types._UniqueCollection);

		private static final @NonNull FlatFragment _Real__OclAny = LIBRARY.createFragment(Types._Real, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Real__OclComparable = LIBRARY.createFragment(Types._Real, OCLstdlibTables.Types._OclComparable);
		private static final @NonNull FlatFragment _Real__OclSummable = LIBRARY.createFragment(Types._Real, OCLstdlibTables.Types._OclSummable);
		private static final @NonNull FlatFragment _Real__Real = LIBRARY.createFragment(Types._Real, OCLstdlibTables.Types._Real);

		private static final @NonNull FlatFragment _Sequence__Collection = LIBRARY.createFragment(Types._Sequence, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _Sequence__OclAny = LIBRARY.createFragment(Types._Sequence, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Sequence__OrderedCollection = LIBRARY.createFragment(Types._Sequence, OCLstdlibTables.Types._OrderedCollection);
		private static final @NonNull FlatFragment _Sequence__Sequence = LIBRARY.createFragment(Types._Sequence, OCLstdlibTables.Types._Sequence);

		private static final @NonNull FlatFragment _Set__Collection = LIBRARY.createFragment(Types._Set, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _Set__OclAny = LIBRARY.createFragment(Types._Set, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _Set__Set = LIBRARY.createFragment(Types._Set, OCLstdlibTables.Types._Set);
		private static final @NonNull FlatFragment _Set__UniqueCollection = LIBRARY.createFragment(Types._Set, OCLstdlibTables.Types._UniqueCollection);

		private static final @NonNull FlatFragment _String__OclAny = LIBRARY.createFragment(Types._String, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _String__OclComparable = LIBRARY.createFragment(Types._String, OCLstdlibTables.Types._OclComparable);
		private static final @NonNull FlatFragment _String__OclSummable = LIBRARY.createFragment(Types._String, OCLstdlibTables.Types._OclSummable);
		private static final @NonNull FlatFragment _String__String = LIBRARY.createFragment(Types._String, OCLstdlibTables.Types._String);

		private static final @NonNull FlatFragment _UniqueCollection__Collection = LIBRARY.createFragment(Types._UniqueCollection, OCLstdlibTables.Types._Collection);
		private static final @NonNull FlatFragment _UniqueCollection__OclAny = LIBRARY.createFragment(Types._UniqueCollection, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _UniqueCollection__UniqueCollection = LIBRARY.createFragment(Types._UniqueCollection, OCLstdlibTables.Types._UniqueCollection);

		private static final @NonNull FlatFragment _UnlimitedNatural__OclAny = LIBRARY.createFragment(Types._UnlimitedNatural, OCLstdlibTables.Types._OclAny);
		private static final @NonNull FlatFragment _UnlimitedNatural__OclComparable = LIBRARY.createFragment(Types._UnlimitedNatural, OCLstdlibTables.Types._OclComparable);
		private static final @NonNull FlatFragment _UnlimitedNatural__UnlimitedNatural = LIBRARY.createFragment(Types._UnlimitedNatural, OCLstdlibTables.Types._UnlimitedNatural);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Fragments and all preceding sub-packages.
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
		public static final @NonNull TypedElement _i_$$0_F = LIBRARY.createLambdaParameter("i", TypeParameters.$$0, false);
		public static final @NonNull TypedElement _j_$$0_F = LIBRARY.createLambdaParameter("j", TypeParameters.$$0, false);
		public static final @NonNull TypedElement _k_$$0_F = LIBRARY.createLambdaParameter("k", TypeParameters.$$0, false);
		public static final @NonNull TypedElement _result_$$1_F = LIBRARY.createLambdaParameter("result", TypeParameters.$$1, false);
		public static final @NonNull TypedElement _result_$$2_F = LIBRARY.createLambdaParameter("result", TypeParameters.$$2, false);
		public static final @NonNull TypedElement _result_$$3_F = LIBRARY.createLambdaParameter("result", TypeParameters.$$3, false);
		public static final @NonNull TypedElement _result_Boolean_F = LIBRARY.createLambdaParameter("result", OCLstdlibTables.Types._Boolean, false);
		public static final @NonNull TypedElement _result_Boolean_T = LIBRARY.createLambdaParameter("result", OCLstdlibTables.Types._Boolean, true);
		public static final @NonNull TypedElement _result_Collection__$$1___F = LIBRARY.createLambdaParameter("result", LIBRARY.getCollectionType(OCLstdlibTables.Types._Collection, TypeParameters.$$1), false);
		public static final @NonNull TypedElement _result_OclAny_F = LIBRARY.createLambdaParameter("result", OCLstdlibTables.Types._OclAny, false);
		public static final @NonNull TypedElement _result_OclComparable_F = LIBRARY.createLambdaParameter("result", OCLstdlibTables.Types._OclComparable, false);
		public static final @NonNull TypedElement _result_OrderedCollection__$$1___F = LIBRARY.createLambdaParameter("result", LIBRARY.getCollectionType(OCLstdlibTables.Types._OrderedCollection, TypeParameters.$$1), false);
		public static final @NonNull TypedElement _self_$$0_F = LIBRARY.createLambdaParameter("self", TypeParameters.$$0, false);
		public static final @NonNull TypedElement _self_$$1_F = LIBRARY.createLambdaParameter("self", TypeParameters.$$1, false);
		public static final @NonNull TypedElement _self_$$2_F = LIBRARY.createLambdaParameter("self", TypeParameters.$$2, false);

		public static final @NonNull ParameterTypes _$$0 = new ParameterTypes(TypeParameters.$$0);
		public static final @NonNull ParameterTypes _$$0___$$1 = new ParameterTypes(TypeParameters.$$0, TypeParameters.$$1);
		public static final @NonNull ParameterTypes _$$1 = new ParameterTypes(TypeParameters.$$1);
		public static final @NonNull ParameterTypes _Boolean = new ParameterTypes(OCLstdlibTables.Types._Boolean);
		public static final @NonNull ParameterTypes _Collection__$$0__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._Collection, TypeParameters.$$0));
		public static final @NonNull ParameterTypes _Collection__$$1__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._Collection, TypeParameters.$$1));
		public static final @NonNull ParameterTypes _Collection__$$2__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._Collection, TypeParameters.$$2));
		public static final @NonNull ParameterTypes _Integer = new ParameterTypes(OCLstdlibTables.Types._Integer);
		public static final @NonNull ParameterTypes _Integer___$$0 = new ParameterTypes(OCLstdlibTables.Types._Integer, TypeParameters.$$0);
		public static final @NonNull ParameterTypes _Integer___Integer = new ParameterTypes(OCLstdlibTables.Types._Integer, OCLstdlibTables.Types._Integer);
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_$$1_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_$$1_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_$$2_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_$$2_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_Boolean_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_Boolean_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_Boolean_T = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_Boolean_T));
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_OclAny_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_OclAny_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$0_F_result_OclComparable_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$0_F, _result_OclComparable_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$2_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_$$1_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_$$2_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_$$2_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _result_$$1_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _j_$$0_F, _result_$$2_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$1_F_i_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_result_$$2_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _result_$$1_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$1_F, _i_$$0_F, _result_$$2_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$1_F_result_Collection__$$1___F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$1_F, _result_Collection__$$1___F));
		public static final @NonNull ParameterTypes _Lambda_self_$$1_F_result_OrderedCollection__$$1___F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$1_F, _result_OrderedCollection__$$1___F));
		public static final @NonNull ParameterTypes _Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$3_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_$$2_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _k_$$0_F, _result_$$3_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_$$3_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _result_$$2_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _j_$$0_F, _result_$$3_F));
		public static final @NonNull ParameterTypes _Lambda_self_$$2_F_i_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_result_$$3_F = new ParameterTypes(LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _result_$$2_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _result_Boolean_F), LIBRARY.getLambdaType(_self_$$2_F, _i_$$0_F, _result_$$3_F));
		public static final @NonNull ParameterTypes _Map__$$2_$$3__ = new ParameterTypes(LIBRARY.getMapType(OCLstdlibTables.Types._Map, TypeParameters.$$2, TypeParameters.$$3));
		public static final @NonNull ParameterTypes _OclSelf = new ParameterTypes(OCLstdlibTables.Types._OclSelf);
		public static final @NonNull ParameterTypes _OclState = new ParameterTypes(OCLstdlibTables.Types._OclState);
		public static final @NonNull ParameterTypes _OclStereotype = new ParameterTypes(OCLstdlibTables.Types._OclStereotype);
		public static final @NonNull ParameterTypes _OclType = new ParameterTypes(OCLstdlibTables.Types._OclType);
		public static final @NonNull ParameterTypes _OrderedCollection__$$0__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._OrderedCollection, TypeParameters.$$0));
		public static final @NonNull ParameterTypes _String = new ParameterTypes(OCLstdlibTables.Types._String);
		public static final @NonNull ParameterTypes _String___Boolean = new ParameterTypes(OCLstdlibTables.Types._String, OCLstdlibTables.Types._Boolean);
		public static final @NonNull ParameterTypes _String___String = new ParameterTypes(OCLstdlibTables.Types._String, OCLstdlibTables.Types._String);
		public static final @NonNull ParameterTypes _UniqueCollection__$$0__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._UniqueCollection, TypeParameters.$$0));
		public static final @NonNull ParameterTypes _UniqueCollection__OclAny__ = new ParameterTypes(LIBRARY.getCollectionType(OCLstdlibTables.Types._UniqueCollection, OCLstdlibTables.Types._OclAny));

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Parameters and all preceding sub-packages.
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

		public static final @NonNull Operation _Bag___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Bag,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Bag___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Bag,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Bag__closure = LIBRARY.createOperation("closure", Parameters._Lambda_self_$$1_F_result_Collection__$$1___F, Types._Bag,
			2, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Bag__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Bag,
			3, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Bag,
			4, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Bag__excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._Bag,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Bag__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._Bag,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Bag__flatten = LIBRARY.createOperation("flatten", ParameterTypes.EMPTY_LIST, Types._Bag,
			7, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Bag__including = LIBRARY.createOperation("including", Parameters._$$0, Types._Bag,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Bag__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__$$0__, Types._Bag,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Bag__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Bag,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Bag,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._$$1, Types._Bag,
			12, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Bag__selectByType = LIBRARY.createOperation("selectByType", Parameters._$$1, Types._Bag,
			13, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Bag__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._Bag,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);

		public static final @NonNull Operation _Boolean___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Boolean,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Boolean___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Boolean,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__and = LIBRARY.createOperation("and", Parameters._Boolean, Types._Boolean,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__implies = LIBRARY.createOperation("implies", Parameters._Boolean, Types._Boolean,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__not = LIBRARY.createOperation("not", ParameterTypes.EMPTY_LIST, Types._Boolean,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__or = LIBRARY.createOperation("or", Parameters._Boolean, Types._Boolean,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanOrOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__xor = LIBRARY.createOperation("xor", Parameters._Boolean, Types._Boolean,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__and2 = LIBRARY.createOperation("and2", Parameters._Boolean, Types._Boolean,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__implies2 = LIBRARY.createOperation("implies2", Parameters._Boolean, Types._Boolean,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__not2 = LIBRARY.createOperation("not2", ParameterTypes.EMPTY_LIST, Types._Boolean,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__or2 = LIBRARY.createOperation("or2", Parameters._Boolean, Types._Boolean,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._Boolean,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__xor2 = LIBRARY.createOperation("xor2", Parameters._Boolean, Types._Boolean,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2.INSTANCE);

		public static final @NonNull Operation _Collection___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Collection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Collection___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Collection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Collection__any = LIBRARY.createOperation("any", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Collection,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
		public static final @NonNull Operation _Collection__asBag = LIBRARY.createOperation("asBag", ParameterTypes.EMPTY_LIST, Types._Collection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asOrderedSet = LIBRARY.createOperation("asOrderedSet", ParameterTypes.EMPTY_LIST, Types._Collection,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asSequence = LIBRARY.createOperation("asSequence", ParameterTypes.EMPTY_LIST, Types._Collection,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asSet = LIBRARY.createOperation("asSet", ParameterTypes.EMPTY_LIST, Types._Collection,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation.INSTANCE);
		public static final @NonNull Operation _Collection__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Collection,
			7, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__collectBy = LIBRARY.createOperation("collectBy", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Collection,
			8, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
		public static final @NonNull Operation _Collection__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Collection,
			9, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Collection__count = LIBRARY.createOperation("count", Parameters._$$0, Types._Collection,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionCountOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludes = LIBRARY.createOperation("excludes", Parameters._$$0, Types._Collection,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludesAll = LIBRARY.createOperation("excludesAll", Parameters._Collection__$$1__, Types._Collection,
			12, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._Collection,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._Collection,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__2_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__1_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__0_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__flatten = LIBRARY.createOperation("flatten", ParameterTypes.EMPTY_LIST, Types._Collection,
			18, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Collection__2_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__1_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__0_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Collection,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__gather = LIBRARY.createOperation("gather", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Collection,
			22, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.GatherIteration.INSTANCE);
		public static final @NonNull Operation _Collection__includes = LIBRARY.createOperation("includes", Parameters._$$0, Types._Collection,
			23, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation.INSTANCE);
		public static final @NonNull Operation _Collection__includesAll = LIBRARY.createOperation("includesAll", Parameters._Collection__$$1__, Types._Collection,
			24, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__including = LIBRARY.createOperation("including", Parameters._$$0, Types._Collection,
			25, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Collection__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__$$0__, Types._Collection,
			26, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__0_intersection = LIBRARY.createOperation("intersection", Parameters._Collection__$$0__, Types._Collection,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _Collection__1_intersection = LIBRARY.createOperation("intersection", Parameters._UniqueCollection__$$0__, Types._Collection,
			28, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _Collection__isEmpty = LIBRARY.createOperation("isEmpty", ParameterTypes.EMPTY_LIST, Types._Collection,
			29, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Collection__isUnique = LIBRARY.createOperation("isUnique", Parameters._Lambda_self_$$0_F_result_OclAny_F, Types._Collection,
			30, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
		public static final @NonNull Operation _Collection__iterate = LIBRARY.createOperation("iterate", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Collection,
			31, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
		public static final @NonNull Operation _Collection__max = LIBRARY.createOperation("max", ParameterTypes.EMPTY_LIST, Types._Collection,
			32, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation.INSTANCE);
		public static final @NonNull Operation _Collection__min = LIBRARY.createOperation("min", ParameterTypes.EMPTY_LIST, Types._Collection,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionMinOperation.INSTANCE);
		public static final @NonNull Operation _Collection__notEmpty = LIBRARY.createOperation("notEmpty", ParameterTypes.EMPTY_LIST, Types._Collection,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Collection__one = LIBRARY.createOperation("one", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Collection,
			35, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
		public static final @NonNull Operation _Collection__product = LIBRARY.createOperation("product", Parameters._Collection__$$1__, Types._Collection,
			36, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionProductOperation.INSTANCE);
		public static final @NonNull Operation _Collection__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Collection,
			37, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__2_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$2_F, Types._Collection,
			38, new TemplateParameters(TypeParameters.$$1, TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Collection__1_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_j_$$0_F_result_$$2_F, Types._Collection,
			39, new TemplateParameters(TypeParameters.$$1, TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Collection__0_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$1_F_i_$$0_F_result_$$1_F___Lambda_self_$$1_F_i_$$0_F_result_Boolean_F___Lambda_self_$$1_F_i_$$0_F_result_$$2_F, Types._Collection,
			40, new TemplateParameters(TypeParameters.$$1, TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Collection__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Collection,
			41, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._$$1, Types._Collection,
			42, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Collection__selectByType = LIBRARY.createOperation("selectByType", Parameters._$$1, Types._Collection,
			43, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Collection__size = LIBRARY.createOperation("size", ParameterTypes.EMPTY_LIST, Types._Collection,
			44, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation.INSTANCE);
		public static final @NonNull Operation _Collection__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._Collection,
			45, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _Collection__sum = LIBRARY.createOperation("sum", ParameterTypes.EMPTY_LIST, Types._Collection,
			46, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionSumOperation.INSTANCE);
		public static final @NonNull Operation _Collection__union = LIBRARY.createOperation("union", Parameters._Collection__$$0__, Types._Collection,
			47, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);

		public static final @NonNull Operation _Integer___mul_ = LIBRARY.createOperation("*", Parameters._OclSelf, Types._Integer,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
		public static final @NonNull Operation _Integer___add_ = LIBRARY.createOperation("+", Parameters._OclSelf, Types._Integer,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
		public static final @NonNull Operation _Integer___neg_ = LIBRARY.createOperation("-", ParameterTypes.EMPTY_LIST, Types._Integer,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
		public static final @NonNull Operation _Integer___sub_ = LIBRARY.createOperation("-", Parameters._OclSelf, Types._Integer,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
		public static final @NonNull Operation _Integer___div_ = LIBRARY.createOperation("/", Parameters._OclSelf, Types._Integer,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
		public static final @NonNull Operation _Integer__abs = LIBRARY.createOperation("abs", ParameterTypes.EMPTY_LIST, Types._Integer,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
		public static final @NonNull Operation _Integer__div = LIBRARY.createOperation("div", Parameters._Integer, Types._Integer,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivOperation.INSTANCE);
		public static final @NonNull Operation _Integer__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._Integer,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
		public static final @NonNull Operation _Integer__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._Integer,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
		public static final @NonNull Operation _Integer__mod = LIBRARY.createOperation("mod", Parameters._Integer, Types._Integer,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericModOperation.INSTANCE);
		public static final @NonNull Operation _Integer__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._Integer,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _Integer__toUnlimitedNatural = LIBRARY.createOperation("toUnlimitedNatural", ParameterTypes.EMPTY_LIST, Types._Integer,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation.INSTANCE);

		public static final @NonNull Operation _Map___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Map,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Map___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Map,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Map__any = LIBRARY.createOperation("any", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Map,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
		public static final @NonNull Operation _Map__at = LIBRARY.createOperation("at", Parameters._$$0, Types._Map,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapAtOperation.INSTANCE);
		public static final @NonNull Operation _Map__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$2_F, Types._Map,
			4, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Map__collectBy = LIBRARY.createOperation("collectBy", Parameters._Lambda_self_$$0_F_result_$$2_F, Types._Map,
			5, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
		public static final @NonNull Operation _Map__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$2_F, Types._Map,
			6, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_excludes = LIBRARY.createOperation("excludes", Parameters._$$0, Types._Map,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_excludes = LIBRARY.createOperation("excludes", Parameters._$$0___$$1, Types._Map,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesAll = LIBRARY.createOperation("excludesAll", Parameters._Collection__$$2__, Types._Map,
			9, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesMap = LIBRARY.createOperation("excludesMap", Parameters._Map__$$2_$$3__, Types._Map,
			10, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesValue = LIBRARY.createOperation("excludesValue", Parameters._$$1, Types._Map,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation.INSTANCE);
		public static final @NonNull Operation _Map__0_excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._Map,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_excluding = LIBRARY.createOperation("excluding", Parameters._$$0___$$1, Types._Map,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._Map,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludingMap = LIBRARY.createOperation("excludingMap", Parameters._Map__$$2_$$3__, Types._Map,
			15, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__2_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__1_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_exists = LIBRARY.createOperation("exists", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__2_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__1_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_self_$$0_F_result_Boolean_F, Types._Map,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__gather = LIBRARY.createOperation("gather", Parameters._Lambda_self_$$0_F_result_$$2_F, Types._Map,
			22, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.GatherIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_includes = LIBRARY.createOperation("includes", Parameters._$$0, Types._Map,
			23, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_includes = LIBRARY.createOperation("includes", Parameters._$$0___$$1, Types._Map,
			24, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesAll = LIBRARY.createOperation("includesAll", Parameters._Collection__$$2__, Types._Map,
			25, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesMap = LIBRARY.createOperation("includesMap", Parameters._Map__$$2_$$3__, Types._Map,
			26, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesValue = LIBRARY.createOperation("includesValue", Parameters._$$1, Types._Map,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation.INSTANCE);
		public static final @NonNull Operation _Map__including = LIBRARY.createOperation("including", Parameters._$$0___$$1, Types._Map,
			28, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__includingMap = LIBRARY.createOperation("includingMap", Parameters._Map__$$2_$$3__, Types._Map,
			29, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__isEmpty = LIBRARY.createOperation("isEmpty", ParameterTypes.EMPTY_LIST, Types._Map,
			30, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Map__isUnique = LIBRARY.createOperation("isUnique", Parameters._Lambda_self_$$0_F_result_OclAny_F, Types._Map,
			31, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
		public static final @NonNull Operation _Map__iterate = LIBRARY.createOperation("iterate", Parameters._Lambda_self_$$0_F_result_$$2_F, Types._Map,
			32, new TemplateParameters(TypeParameters.$$2), org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
		public static final @NonNull Operation _Map__keys = LIBRARY.createOperation("keys", ParameterTypes.EMPTY_LIST, Types._Map,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapKeysOperation.INSTANCE);
		public static final @NonNull Operation _Map__notEmpty = LIBRARY.createOperation("notEmpty", ParameterTypes.EMPTY_LIST, Types._Map,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Map__one = LIBRARY.createOperation("one", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Map,
			35, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
		public static final @NonNull Operation _Map__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Map,
			36, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.MapRejectIteration.INSTANCE);
		public static final @NonNull Operation _Map__2_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_k_$$0_F_result_$$3_F, Types._Map,
			37, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Map__1_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_j_$$0_F_result_$$3_F, Types._Map,
			38, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_search = LIBRARY.createOperation("search", Parameters._Lambda_self_$$2_F_i_$$0_F_result_$$2_F___Lambda_self_$$2_F_i_$$0_F_result_Boolean_F___Lambda_self_$$2_F_i_$$0_F_result_$$3_F, Types._Map,
			39, new TemplateParameters(TypeParameters.$$2, TypeParameters.$$3), org.eclipse.ocl.pivot.library.iterator.SearchIteration.INSTANCE);
		public static final @NonNull Operation _Map__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Map,
			40, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.MapSelectIteration.INSTANCE);
		public static final @NonNull Operation _Map__size = LIBRARY.createOperation("size", ParameterTypes.EMPTY_LIST, Types._Map,
			41, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapSizeOperation.INSTANCE);
		public static final @NonNull Operation _Map__values = LIBRARY.createOperation("values", ParameterTypes.EMPTY_LIST, Types._Map,
			42, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapValuesOperation.INSTANCE);

		public static final @NonNull Operation _OclAny___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OclAny,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclAny___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OclAny,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclAsSet = LIBRARY.createOperation("oclAsSet", ParameterTypes.EMPTY_LIST, Types._OclAny,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._$$0, Types._OclAny,
			3, new TemplateParameters(TypeParameters.$$0), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsInState = LIBRARY.createOperation("oclIsInState", Parameters._OclState, Types._OclAny,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", ParameterTypes.EMPTY_LIST, Types._OclAny,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclAny,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsNew = LIBRARY.createOperation("oclIsNew", ParameterTypes.EMPTY_LIST, Types._OclAny,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclAny,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", ParameterTypes.EMPTY_LIST, Types._OclAny,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__0_oclLog = LIBRARY.createOperation("oclLog", ParameterTypes.EMPTY_LIST, Types._OclAny,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__1_oclLog = LIBRARY.createOperation("oclLog", Parameters._String, Types._OclAny,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclType = LIBRARY.createOperation("oclType", ParameterTypes.EMPTY_LIST, Types._OclAny,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclTypes = LIBRARY.createOperation("oclTypes", ParameterTypes.EMPTY_LIST, Types._OclAny,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._OclAny,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _OclComparable___lt_ = LIBRARY.createOperation("<", Parameters._OclSelf, Types._OclComparable,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation.INSTANCE);
		public static final @NonNull Operation _OclComparable___lt__eq_ = LIBRARY.createOperation("<=", Parameters._OclSelf, Types._OclComparable,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclComparable___gt_ = LIBRARY.createOperation(">", Parameters._OclSelf, Types._OclComparable,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation.INSTANCE);
		public static final @NonNull Operation _OclComparable___gt__eq_ = LIBRARY.createOperation(">=", Parameters._OclSelf, Types._OclComparable,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclComparable__compareTo = LIBRARY.createOperation("compareTo", Parameters._OclSelf, Types._OclComparable,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation.INSTANCE);

		public static final @NonNull Operation _OclElement__oclAsModelType = LIBRARY.createOperation("oclAsModelType", Parameters._$$0, Types._OclElement,
			0, new TemplateParameters(TypeParameters.$$0), org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__0_oclBase = LIBRARY.createOperation("oclBase", ParameterTypes.EMPTY_LIST, Types._OclElement,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__1_oclBase = LIBRARY.createOperation("oclBase", Parameters._OclType, Types._OclElement,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclContainer = LIBRARY.createOperation("oclContainer", ParameterTypes.EMPTY_LIST, Types._OclElement,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclContents = LIBRARY.createOperation("oclContents", ParameterTypes.EMPTY_LIST, Types._OclElement,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclExtension = LIBRARY.createOperation("oclExtension", Parameters._OclStereotype, Types._OclElement,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__1_oclExtensions = LIBRARY.createOperation("oclExtensions", ParameterTypes.EMPTY_LIST, Types._OclElement,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__0_oclExtensions = LIBRARY.createOperation("oclExtensions", Parameters._OclStereotype, Types._OclElement,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclIsModelKindOf = LIBRARY.createOperation("oclIsModelKindOf", Parameters._OclType, Types._OclElement,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclModelType = LIBRARY.createOperation("oclModelType", ParameterTypes.EMPTY_LIST, Types._OclElement,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclModelTypes = LIBRARY.createOperation("oclModelTypes", ParameterTypes.EMPTY_LIST, Types._OclElement,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation.INSTANCE);

		public static final @NonNull Operation _OclInvalid___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OclInvalid,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OclInvalid,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__and = LIBRARY.createOperation("and", Parameters._Boolean, Types._OclInvalid,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__implies = LIBRARY.createOperation("implies", Parameters._Boolean, Types._OclInvalid,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__or = LIBRARY.createOperation("or", Parameters._Boolean, Types._OclInvalid,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclAsSet = LIBRARY.createOperation("oclAsSet", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._$$0, Types._OclInvalid,
			6, new TemplateParameters(TypeParameters.$$0), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclBadOperation = LIBRARY.createOperation("oclBadOperation", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			7, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _OclInvalid__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclInvalid,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclInvalid,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclType = LIBRARY.createOperation("oclType", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._OclInvalid,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _OclMessage__hasReturned = LIBRARY.createOperation("hasReturned", ParameterTypes.EMPTY_LIST, Types._OclMessage,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__isOperationCall = LIBRARY.createOperation("isOperationCall", ParameterTypes.EMPTY_LIST, Types._OclMessage,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__isSignalSent = LIBRARY.createOperation("isSignalSent", ParameterTypes.EMPTY_LIST, Types._OclMessage,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__result = LIBRARY.createOperation("result", ParameterTypes.EMPTY_LIST, Types._OclMessage,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

		public static final @NonNull Operation _OclSummable__sum = LIBRARY.createOperation("sum", Parameters._OclSelf, Types._OclSummable,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _OclSummable__zero = LIBRARY.createOperation("zero", ParameterTypes.EMPTY_LIST, Types._OclSummable,
			1, TemplateParameters.EMPTY_LIST, null);

		public static final @NonNull Operation _OclTuple___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OclTuple,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclTuple___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OclTuple,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);

		public static final @NonNull Operation _OclType__conformsTo = LIBRARY.createOperation("conformsTo", Parameters._OclType, Types._OclType,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE);

		public static final @NonNull Operation _OclVoid___add_ = LIBRARY.createOperation("+", Parameters._String, Types._OclVoid,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OclVoid,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OclVoid,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__and = LIBRARY.createOperation("and", Parameters._Boolean, Types._OclVoid,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__implies = LIBRARY.createOperation("implies", Parameters._Boolean, Types._OclVoid,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__not = LIBRARY.createOperation("not", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__or = LIBRARY.createOperation("or", Parameters._Boolean, Types._OclVoid,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__xor = LIBRARY.createOperation("xor", Parameters._Boolean, Types._OclVoid,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__concat = LIBRARY.createOperation("concat", Parameters._String, Types._OclVoid,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclAsSet = LIBRARY.createOperation("oclAsSet", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._$$0, Types._OclVoid,
			10, new TemplateParameters(TypeParameters.$$0), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclVoid,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclVoid,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclType = LIBRARY.createOperation("oclType", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclTypes = LIBRARY.createOperation("oclTypes", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._OclVoid,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _OrderedCollection__at = LIBRARY.createOperation("at", Parameters._Integer, Types._OrderedCollection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__first = LIBRARY.createOperation("first", ParameterTypes.EMPTY_LIST, Types._OrderedCollection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__gather = LIBRARY.createOperation("gather", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._OrderedCollection,
			2, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.GatherIteration.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__indexOf = LIBRARY.createOperation("indexOf", Parameters._$$0, Types._OrderedCollection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__last = LIBRARY.createOperation("last", ParameterTypes.EMPTY_LIST, Types._OrderedCollection,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation.INSTANCE);

		public static final @NonNull Operation _OrderedSet___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._OrderedSet,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OrderedSet,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OrderedSet,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__append = LIBRARY.createOperation("append", Parameters._$$0, Types._OrderedSet,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__appendAll = LIBRARY.createOperation("appendAll", Parameters._OrderedCollection__$$0__, Types._OrderedSet,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__closure = LIBRARY.createOperation("closure", Parameters._Lambda_self_$$1_F_result_OrderedCollection__$$1___F, Types._OrderedSet,
			5, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._OrderedSet,
			6, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._OrderedSet,
			7, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._OrderedSet,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._OrderedSet,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__flatten = LIBRARY.createOperation("flatten", ParameterTypes.EMPTY_LIST, Types._OrderedSet,
			10, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__including = LIBRARY.createOperation("including", Parameters._$$0, Types._OrderedSet,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__$$0__, Types._OrderedSet,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__insertAt = LIBRARY.createOperation("insertAt", Parameters._Integer___$$0, Types._OrderedSet,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__prepend = LIBRARY.createOperation("prepend", Parameters._$$0, Types._OrderedSet,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__prependAll = LIBRARY.createOperation("prependAll", Parameters._OrderedCollection__$$0__, Types._OrderedSet,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._OrderedSet,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__reverse = LIBRARY.createOperation("reverse", ParameterTypes.EMPTY_LIST, Types._OrderedSet,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._OrderedSet,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._$$1, Types._OrderedSet,
			19, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__selectByType = LIBRARY.createOperation("selectByType", Parameters._$$1, Types._OrderedSet,
			20, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._OrderedSet,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__subOrderedSet = LIBRARY.createOperation("subOrderedSet", Parameters._Integer___Integer, Types._OrderedSet,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation.INSTANCE);

		public static final @NonNull Operation _Real___mul_ = LIBRARY.createOperation("*", Parameters._OclSelf, Types._Real,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
		public static final @NonNull Operation _Real___add_ = LIBRARY.createOperation("+", Parameters._OclSelf, Types._Real,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
		public static final @NonNull Operation _Real___neg_ = LIBRARY.createOperation("-", ParameterTypes.EMPTY_LIST, Types._Real,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
		public static final @NonNull Operation _Real___sub_ = LIBRARY.createOperation("-", Parameters._OclSelf, Types._Real,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
		public static final @NonNull Operation _Real___div_ = LIBRARY.createOperation("/", Parameters._OclSelf, Types._Real,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
		public static final @NonNull Operation _Real___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Real,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Real___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Real,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Real__abs = LIBRARY.createOperation("abs", ParameterTypes.EMPTY_LIST, Types._Real,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
		public static final @NonNull Operation _Real__floor = LIBRARY.createOperation("floor", ParameterTypes.EMPTY_LIST, Types._Real,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation.INSTANCE);
		public static final @NonNull Operation _Real__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._Real,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
		public static final @NonNull Operation _Real__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._Real,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
		public static final @NonNull Operation _Real__round = LIBRARY.createOperation("round", ParameterTypes.EMPTY_LIST, Types._Real,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation.INSTANCE);
		public static final @NonNull Operation _Real__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._Real,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _Sequence___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Sequence,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Sequence___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Sequence,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__append = LIBRARY.createOperation("append", Parameters._$$0, Types._Sequence,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__appendAll = LIBRARY.createOperation("appendAll", Parameters._OrderedCollection__$$0__, Types._Sequence,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__closure = LIBRARY.createOperation("closure", Parameters._Lambda_self_$$1_F_result_OrderedCollection__$$1___F, Types._Sequence,
			4, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Sequence,
			5, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Sequence,
			6, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._Sequence,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._Sequence,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__flatten = LIBRARY.createOperation("flatten", ParameterTypes.EMPTY_LIST, Types._Sequence,
			9, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__including = LIBRARY.createOperation("including", Parameters._$$0, Types._Sequence,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__$$0__, Types._Sequence,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__insertAt = LIBRARY.createOperation("insertAt", Parameters._Integer___$$0, Types._Sequence,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__prepend = LIBRARY.createOperation("prepend", Parameters._$$0, Types._Sequence,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__prependAll = LIBRARY.createOperation("prependAll", Parameters._OrderedCollection__$$0__, Types._Sequence,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Sequence,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__reverse = LIBRARY.createOperation("reverse", ParameterTypes.EMPTY_LIST, Types._Sequence,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Sequence,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._$$1, Types._Sequence,
			18, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__selectByType = LIBRARY.createOperation("selectByType", Parameters._$$1, Types._Sequence,
			19, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._Sequence,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__subSequence = LIBRARY.createOperation("subSequence", Parameters._Integer___Integer, Types._Sequence,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation.INSTANCE);

		public static final @NonNull Operation _Set___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._Set,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _Set___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Set,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Set___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Set,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Set__closure = LIBRARY.createOperation("closure", Parameters._Lambda_self_$$1_F_result_Collection__$$1___F, Types._Set,
			3, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Set__collect = LIBRARY.createOperation("collect", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Set,
			4, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Set__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_self_$$0_F_result_$$1_F, Types._Set,
			5, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Set__excluding = LIBRARY.createOperation("excluding", Parameters._$$0, Types._Set,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Set__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__$$0__, Types._Set,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Set__flatten = LIBRARY.createOperation("flatten", ParameterTypes.EMPTY_LIST, Types._Set,
			8, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Set__including = LIBRARY.createOperation("including", Parameters._$$0, Types._Set,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Set__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__$$0__, Types._Set,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Set__reject = LIBRARY.createOperation("reject", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Set,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Set__select = LIBRARY.createOperation("select", Parameters._Lambda_self_$$0_F_result_Boolean_T, Types._Set,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Set__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._$$1, Types._Set,
			13, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Set__selectByType = LIBRARY.createOperation("selectByType", Parameters._$$1, Types._Set,
			14, new TemplateParameters(TypeParameters.$$1), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Set__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._Set,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);

		public static final @NonNull Operation _String___add_ = LIBRARY.createOperation("+", Parameters._String, Types._String,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
		public static final @NonNull Operation _String___lt_ = LIBRARY.createOperation("<", Parameters._OclSelf, Types._String,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringLessThanOperation.INSTANCE);
		public static final @NonNull Operation _String___lt__eq_ = LIBRARY.createOperation("<=", Parameters._OclSelf, Types._String,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringLessThanEqualOperation.INSTANCE);
		public static final @NonNull Operation _String___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._String,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _String___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._String,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _String___gt_ = LIBRARY.createOperation(">", Parameters._OclSelf, Types._String,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringGreaterThanOperation.INSTANCE);
		public static final @NonNull Operation _String___gt__eq_ = LIBRARY.createOperation(">=", Parameters._OclSelf, Types._String,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringGreaterThanEqualOperation.INSTANCE);
		public static final @NonNull Operation _String__at = LIBRARY.createOperation("at", Parameters._Integer, Types._String,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringAtOperation.INSTANCE);
		public static final @NonNull Operation _String__characters = LIBRARY.createOperation("characters", ParameterTypes.EMPTY_LIST, Types._String,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringCharactersOperation.INSTANCE);
		public static final @NonNull Operation _String__compareTo = LIBRARY.createOperation("compareTo", Parameters._OclSelf, Types._String,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringCompareToOperation.INSTANCE);
		public static final @NonNull Operation _String__concat = LIBRARY.createOperation("concat", Parameters._String, Types._String,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
		public static final @NonNull Operation _String__endsWith = LIBRARY.createOperation("endsWith", Parameters._String, Types._String,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringEndsWithOperation.INSTANCE);
		public static final @NonNull Operation _String__equalsIgnoreCase = LIBRARY.createOperation("equalsIgnoreCase", Parameters._String, Types._String,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringEqualsIgnoreCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__indexOf = LIBRARY.createOperation("indexOf", Parameters._String, Types._String,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringIndexOfOperation.INSTANCE);
		public static final @NonNull Operation _String__lastIndexOf = LIBRARY.createOperation("lastIndexOf", Parameters._String, Types._String,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringLastIndexOfOperation.INSTANCE);
		public static final @NonNull Operation _String__matches = LIBRARY.createOperation("matches", Parameters._String, Types._String,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringMatchesOperation.INSTANCE);
		public static final @NonNull Operation _String__replaceAll = LIBRARY.createOperation("replaceAll", Parameters._String___String, Types._String,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringReplaceAllOperation.INSTANCE);
		public static final @NonNull Operation _String__replaceFirst = LIBRARY.createOperation("replaceFirst", Parameters._String___String, Types._String,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringReplaceFirstOperation.INSTANCE);
		public static final @NonNull Operation _String__size = LIBRARY.createOperation("size", ParameterTypes.EMPTY_LIST, Types._String,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSizeOperation.INSTANCE);
		public static final @NonNull Operation _String__startsWith = LIBRARY.createOperation("startsWith", Parameters._String, Types._String,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringStartsWithOperation.INSTANCE);
		public static final @NonNull Operation _String__substituteAll = LIBRARY.createOperation("substituteAll", Parameters._String___String, Types._String,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation.INSTANCE);
		public static final @NonNull Operation _String__substituteFirst = LIBRARY.createOperation("substituteFirst", Parameters._String___String, Types._String,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation.INSTANCE);
		public static final @NonNull Operation _String__substring = LIBRARY.createOperation("substring", Parameters._Integer___Integer, Types._String,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstringOperation.INSTANCE);
		public static final @NonNull Operation _String__toBoolean = LIBRARY.createOperation("toBoolean", ParameterTypes.EMPTY_LIST, Types._String,
			23, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToBooleanOperation.INSTANCE);
		public static final @NonNull Operation _String__toInteger = LIBRARY.createOperation("toInteger", ParameterTypes.EMPTY_LIST, Types._String,
			24, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToIntegerOperation.INSTANCE);
		public static final @NonNull Operation _String__toLower = LIBRARY.createOperation("toLower", ParameterTypes.EMPTY_LIST, Types._String,
			25, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toLowerCase = LIBRARY.createOperation("toLowerCase", ParameterTypes.EMPTY_LIST, Types._String,
			26, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toReal = LIBRARY.createOperation("toReal", ParameterTypes.EMPTY_LIST, Types._String,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToRealOperation.INSTANCE);
		public static final @NonNull Operation _String__toString = LIBRARY.createOperation("toString", ParameterTypes.EMPTY_LIST, Types._String,
			28, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _String__toUpper = LIBRARY.createOperation("toUpper", ParameterTypes.EMPTY_LIST, Types._String,
			29, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toUpperCase = LIBRARY.createOperation("toUpperCase", ParameterTypes.EMPTY_LIST, Types._String,
			30, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__0_tokenize = LIBRARY.createOperation("tokenize", ParameterTypes.EMPTY_LIST, Types._String,
			31, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__1_tokenize = LIBRARY.createOperation("tokenize", Parameters._String, Types._String,
			32, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__2_tokenize = LIBRARY.createOperation("tokenize", Parameters._String___Boolean, Types._String,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__trim = LIBRARY.createOperation("trim", ParameterTypes.EMPTY_LIST, Types._String,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTrimOperation.INSTANCE);

		public static final @NonNull Operation _UniqueCollection___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._UniqueCollection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__intersection = LIBRARY.createOperation("intersection", Parameters._Collection__$$0__, Types._UniqueCollection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_self_$$0_F_result_OclComparable_F, Types._UniqueCollection,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__symmetricDifference = LIBRARY.createOperation("symmetricDifference", Parameters._UniqueCollection__OclAny__, Types._UniqueCollection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__union = LIBRARY.createOperation("union", Parameters._UniqueCollection__$$0__, Types._UniqueCollection,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);

		public static final @NonNull Operation _UnlimitedNatural__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._UnlimitedNatural,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._UnlimitedNatural,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._$$0, Types._UnlimitedNatural,
			2, new TemplateParameters(TypeParameters.$$0), org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__toInteger = LIBRARY.createOperation("toInteger", ParameterTypes.EMPTY_LIST, Types._UnlimitedNatural,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation.INSTANCE);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Operations and all preceding sub-packages.
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


		public static final @NonNull Property _OclAny__OclInvalid__oclBadProperty = LIBRARY.createOppositeProperty("OclInvalid", Types._OclAny, 0, OCLstdlibPackage.Literals.OCL_INVALID__OCL_BAD_PROPERTY);

		public static final @NonNull Property _OclElement__oclContainer = LIBRARY.createProperty("oclContainer", Types._OclElement, 0, org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty.INSTANCE);
		public static final @NonNull Property _OclElement__oclContents = LIBRARY.createProperty("oclContents", Types._OclElement, 1, org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty.INSTANCE);
		public static final @NonNull Property _OclElement__OclElement__oclContainer = LIBRARY.createOppositeProperty("OclElement", Types._OclElement, 2, OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER);
		public static final @NonNull Property _OclElement__OclElement__oclContents = LIBRARY.createOppositeProperty("OclElement", Types._OclElement, 3, OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTENTS);

		public static final @NonNull Property _OclInvalid__oclBadProperty = LIBRARY.createProperty(OCLstdlibPackage.Literals.OCL_INVALID__OCL_BAD_PROPERTY, Types._OclInvalid, 0);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Properties and all preceding sub-packages.
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

		private static final @NonNull FlatFragment @NonNull [] _Bag =
			{
				Fragments._Bag__OclAny /* 0 */,
				Fragments._Bag__Collection /* 1 */,
				Fragments._Bag__Bag /* 2 */
			};
		private static final int @NonNull [] __Bag = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Boolean =
			{
				Fragments._Boolean__OclAny /* 0 */,
				Fragments._Boolean__Boolean /* 1 */
			};
		private static final int @NonNull [] __Boolean = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Collection =
			{
				Fragments._Collection__OclAny /* 0 */,
				Fragments._Collection__Collection /* 1 */
			};
		private static final int @NonNull [] __Collection = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Integer =
			{
				Fragments._Integer__OclAny /* 0 */,
				Fragments._Integer__OclComparable /* 1 */,
				Fragments._Integer__OclSummable /* 1 */,
				Fragments._Integer__Real /* 2 */,
				Fragments._Integer__Integer /* 3 */
			};
		private static final int @NonNull [] __Integer = { 1,2,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Map =
			{
				Fragments._Map__OclAny /* 0 */,
				Fragments._Map__Map /* 1 */
			};
		private static final int @NonNull [] __Map = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclAny =
			{
				Fragments._OclAny__OclAny /* 0 */
			};
		private static final int @NonNull [] __OclAny = { 1 };

		private static final @NonNull FlatFragment @NonNull [] _OclComparable =
			{
				Fragments._OclComparable__OclAny /* 0 */,
				Fragments._OclComparable__OclComparable /* 1 */
			};
		private static final int @NonNull [] __OclComparable = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclElement =
			{
				Fragments._OclElement__OclAny /* 0 */,
				Fragments._OclElement__OclElement /* 1 */
			};
		private static final int @NonNull [] __OclElement = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclEnumeration =
			{
				Fragments._OclEnumeration__OclAny /* 0 */,
				Fragments._OclEnumeration__OclElement /* 1 */,
				Fragments._OclEnumeration__OclType /* 2 */,
				Fragments._OclEnumeration__OclEnumeration /* 3 */
			};
		private static final int @NonNull [] __OclEnumeration = { 1,1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclInvalid =
			{
				Fragments._OclInvalid__OclAny /* 0 */,
				Fragments._OclInvalid__OclVoid /* 1 */,
				Fragments._OclInvalid__OclInvalid /* 2 */
			};
		private static final int @NonNull [] __OclInvalid = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclLambda =
			{
				Fragments._OclLambda__OclAny /* 0 */,
				Fragments._OclLambda__OclLambda /* 1 */
			};
		private static final int @NonNull [] __OclLambda = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclMessage =
			{
				Fragments._OclMessage__OclAny /* 0 */,
				Fragments._OclMessage__OclMessage /* 1 */
			};
		private static final int @NonNull [] __OclMessage = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclSelf =
			{
				Fragments._OclSelf__OclAny /* 0 */,
				Fragments._OclSelf__OclSelf /* 1 */
			};
		private static final int @NonNull [] __OclSelf = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclState =
			{
				Fragments._OclState__OclAny /* 0 */,
				Fragments._OclState__OclState /* 1 */
			};
		private static final int @NonNull [] __OclState = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclStereotype =
			{
				Fragments._OclStereotype__OclAny /* 0 */,
				Fragments._OclStereotype__OclElement /* 1 */,
				Fragments._OclStereotype__OclType /* 2 */,
				Fragments._OclStereotype__OclStereotype /* 3 */
			};
		private static final int @NonNull [] __OclStereotype = { 1,1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclSummable =
			{
				Fragments._OclSummable__OclAny /* 0 */,
				Fragments._OclSummable__OclSummable /* 1 */
			};
		private static final int @NonNull [] __OclSummable = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclTuple =
			{
				Fragments._OclTuple__OclAny /* 0 */,
				Fragments._OclTuple__OclTuple /* 1 */
			};
		private static final int @NonNull [] __OclTuple = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclType =
			{
				Fragments._OclType__OclAny /* 0 */,
				Fragments._OclType__OclElement /* 1 */,
				Fragments._OclType__OclType /* 2 */
			};
		private static final int @NonNull [] __OclType = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OclVoid =
			{
				Fragments._OclVoid__OclAny /* 0 */,
				Fragments._OclVoid__OclVoid /* 1 */
			};
		private static final int @NonNull [] __OclVoid = { 1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OrderedCollection =
			{
				Fragments._OrderedCollection__OclAny /* 0 */,
				Fragments._OrderedCollection__Collection /* 1 */,
				Fragments._OrderedCollection__OrderedCollection /* 2 */
			};
		private static final int @NonNull [] __OrderedCollection = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _OrderedSet =
			{
				Fragments._OrderedSet__OclAny /* 0 */,
				Fragments._OrderedSet__Collection /* 1 */,
				Fragments._OrderedSet__OrderedCollection /* 2 */,
				Fragments._OrderedSet__UniqueCollection /* 2 */,
				Fragments._OrderedSet__OrderedSet /* 3 */
			};
		private static final int @NonNull [] __OrderedSet = { 1,1,2,1 };

		private static final @NonNull FlatFragment @NonNull [] _Real =
			{
				Fragments._Real__OclAny /* 0 */,
				Fragments._Real__OclComparable /* 1 */,
				Fragments._Real__OclSummable /* 1 */,
				Fragments._Real__Real /* 2 */
			};
		private static final int @NonNull [] __Real = { 1,2,1 };

		private static final @NonNull FlatFragment @NonNull [] _Sequence =
			{
				Fragments._Sequence__OclAny /* 0 */,
				Fragments._Sequence__Collection /* 1 */,
				Fragments._Sequence__OrderedCollection /* 2 */,
				Fragments._Sequence__Sequence /* 3 */
			};
		private static final int @NonNull [] __Sequence = { 1,1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _Set =
			{
				Fragments._Set__OclAny /* 0 */,
				Fragments._Set__Collection /* 1 */,
				Fragments._Set__UniqueCollection /* 2 */,
				Fragments._Set__Set /* 3 */
			};
		private static final int @NonNull [] __Set = { 1,1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _String =
			{
				Fragments._String__OclAny /* 0 */,
				Fragments._String__OclComparable /* 1 */,
				Fragments._String__OclSummable /* 1 */,
				Fragments._String__String /* 2 */
			};
		private static final int @NonNull [] __String = { 1,2,1 };

		private static final @NonNull FlatFragment @NonNull [] _UniqueCollection =
			{
				Fragments._UniqueCollection__OclAny /* 0 */,
				Fragments._UniqueCollection__Collection /* 1 */,
				Fragments._UniqueCollection__UniqueCollection /* 2 */
			};
		private static final int @NonNull [] __UniqueCollection = { 1,1,1 };

		private static final @NonNull FlatFragment @NonNull [] _UnlimitedNatural =
			{
				Fragments._UnlimitedNatural__OclAny /* 0 */,
				Fragments._UnlimitedNatural__OclComparable /* 1 */,
				Fragments._UnlimitedNatural__UnlimitedNatural /* 2 */
			};
		private static final int @NonNull [] __UnlimitedNatural = { 1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Bag.initFragments(_Bag, __Bag);
			Types._Boolean.initFragments(_Boolean, __Boolean);
			Types._Collection.initFragments(_Collection, __Collection);
			Types._Integer.initFragments(_Integer, __Integer);
			Types._Map.initFragments(_Map, __Map);
			Types._OclAny.initFragments(_OclAny, __OclAny);
			Types._OclComparable.initFragments(_OclComparable, __OclComparable);
			Types._OclElement.initFragments(_OclElement, __OclElement);
			Types._OclEnumeration.initFragments(_OclEnumeration, __OclEnumeration);
			Types._OclInvalid.initFragments(_OclInvalid, __OclInvalid);
			Types._OclLambda.initFragments(_OclLambda, __OclLambda);
			Types._OclMessage.initFragments(_OclMessage, __OclMessage);
			Types._OclSelf.initFragments(_OclSelf, __OclSelf);
			Types._OclState.initFragments(_OclState, __OclState);
			Types._OclStereotype.initFragments(_OclStereotype, __OclStereotype);
			Types._OclSummable.initFragments(_OclSummable, __OclSummable);
			Types._OclTuple.initFragments(_OclTuple, __OclTuple);
			Types._OclType.initFragments(_OclType, __OclType);
			Types._OclVoid.initFragments(_OclVoid, __OclVoid);
			Types._OrderedCollection.initFragments(_OrderedCollection, __OrderedCollection);
			Types._OrderedSet.initFragments(_OrderedSet, __OrderedSet);
			Types._Real.initFragments(_Real, __Real);
			Types._Sequence.initFragments(_Sequence, __Sequence);
			Types._Set.initFragments(_Set, __Set);
			Types._String.initFragments(_String, __String);
			Types._UniqueCollection.initFragments(_UniqueCollection, __UniqueCollection);
			Types._UnlimitedNatural.initFragments(_UnlimitedNatural, __UnlimitedNatural);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::TypeFragments and all preceding sub-packages.
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

		private static final @NonNull Operation @NonNull [] _Bag__Bag = {
			OCLstdlibTables.Operations._Bag___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Bag___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Bag__closure /* closure(E)(E[?]|Lambda $$1() : Collection($$1)[?]) */,
			OCLstdlibTables.Operations._Bag__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Bag__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Bag__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Bag__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Bag__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Bag__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Bag__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Bag__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Bag__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Bag__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */
		};
		private static final @NonNull Operation @NonNull [] _Bag__Collection = {
			OCLstdlibTables.Operations._Bag___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Bag___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Bag__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Bag__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Bag__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Bag__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Bag__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Bag__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Bag__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Bag__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Bag__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Bag__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Bag__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _Bag__OclAny = {
			OCLstdlibTables.Operations._Bag___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Bag___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _Boolean__Boolean = {
			OCLstdlibTables.Operations._Boolean___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Boolean___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Boolean__and /* _'and'(Boolean[?]) */,
			OCLstdlibTables.Operations._Boolean__implies /* _'implies'(Boolean[?]) */,
			OCLstdlibTables.Operations._Boolean__not /* _'not'() */,
			OCLstdlibTables.Operations._Boolean__or /* _'or'(Boolean[?]) */,
			OCLstdlibTables.Operations._Boolean__xor /* _'xor'(Boolean[?]) */,
			OCLstdlibTables.Operations._Boolean__and2 /* and2(Boolean[1]) */,
			OCLstdlibTables.Operations._Boolean__implies2 /* implies2(Boolean[1]) */,
			OCLstdlibTables.Operations._Boolean__not2 /* not2() */,
			OCLstdlibTables.Operations._Boolean__or2 /* or2(Boolean[1]) */,
			OCLstdlibTables.Operations._Boolean__toString /* toString() */,
			OCLstdlibTables.Operations._Boolean__xor2 /* xor2(Boolean[1]) */
		};
		private static final @NonNull Operation @NonNull [] _Boolean__OclAny = {
			OCLstdlibTables.Operations._Boolean___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Boolean___eq_ /* _'='(OclSelf[?]) */,
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
			OCLstdlibTables.Operations._Boolean__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _Collection__Collection = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Collection__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _Collection__OclAny = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _Integer__Integer = {
			OCLstdlibTables.Operations._Integer___mul_ /* _'*'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___add_ /* _'+'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___neg_ /* _'-'() */,
			OCLstdlibTables.Operations._Integer___sub_ /* _'-'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___div_ /* _'/'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer__abs /* abs() */,
			OCLstdlibTables.Operations._Integer__div /* div(Integer[1]) */,
			OCLstdlibTables.Operations._Integer__max /* max(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer__min /* min(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer__mod /* mod(Integer[1]) */,
			OCLstdlibTables.Operations._Integer__toString /* toString() */,
			OCLstdlibTables.Operations._Integer__toUnlimitedNatural /* toUnlimitedNatural() */
		};
		private static final @NonNull Operation @NonNull [] _Integer__OclAny = {
			OCLstdlibTables.Operations._Real___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Real___eq_ /* _'='(OclSelf[?]) */,
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
			OCLstdlibTables.Operations._Integer__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _Integer__OclComparable = {
			OCLstdlibTables.Operations._OclComparable___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable__compareTo /* compareTo(OclSelf[1]) */
		};
		private static final @NonNull Operation @NonNull [] _Integer__OclSummable = {
			OCLstdlibTables.Operations._OclSummable__sum /* sum(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclSummable__zero /* zero() */
		};
		private static final @NonNull Operation @NonNull [] _Integer__Real = {
			OCLstdlibTables.Operations._Integer___mul_ /* _'*'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___add_ /* _'+'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___neg_ /* _'-'() */,
			OCLstdlibTables.Operations._Integer___sub_ /* _'-'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer___div_ /* _'/'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Real___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Integer__abs /* abs() */,
			OCLstdlibTables.Operations._Real__floor /* floor() */,
			OCLstdlibTables.Operations._Integer__max /* max(OclSelf[1]) */,
			OCLstdlibTables.Operations._Integer__min /* min(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real__round /* round() */,
			OCLstdlibTables.Operations._Integer__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _Map__Map = {
			OCLstdlibTables.Operations._Map___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Map___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Map__any /* any(K[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__at /* at(K[?]) */,
			OCLstdlibTables.Operations._Map__collect /* collect(E)(K[?]|Lambda $$0() : $$2[?]) */,
			OCLstdlibTables.Operations._Map__collectBy /* collectBy(E)(K[?]|Lambda $$0() : $$2[?]) */,
			OCLstdlibTables.Operations._Map__collectNested /* collectNested(E)(K[?]|Lambda $$0() : $$2[?]) */,
			OCLstdlibTables.Operations._Map__0_excludes /* excludes(K[?]) */,
			OCLstdlibTables.Operations._Map__1_excludes /* excludes(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__excludesAll /* excludesAll(K2)(Collection($$2)) */,
			OCLstdlibTables.Operations._Map__excludesMap /* excludesMap(K2, V2)(Map($$2[1],$$3[1])[?]) */,
			OCLstdlibTables.Operations._Map__excludesValue /* excludesValue(V[?]) */,
			OCLstdlibTables.Operations._Map__0_excluding /* excluding(K[?]) */,
			OCLstdlibTables.Operations._Map__1_excluding /* excluding(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Map__excludingMap /* excludingMap(K2, V2)(Map($$2[1],$$3[1])[?]) */,
			OCLstdlibTables.Operations._Map__2_exists /* exists(K[?],K[?],K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__1_exists /* exists(K[?],K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__0_exists /* exists(K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__2_forAll /* forAll(K[?],K[?],K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__1_forAll /* forAll(K[?],K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__0_forAll /* forAll(K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__gather /* gather(E)(K[?]|Lambda $$0() : $$2[?]) */,
			OCLstdlibTables.Operations._Map__0_includes /* includes(K[?]) */,
			OCLstdlibTables.Operations._Map__1_includes /* includes(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__includesAll /* includesAll(K2)(Collection($$2)) */,
			OCLstdlibTables.Operations._Map__includesMap /* includesMap(K2, V2)(Map($$2[1],$$3[1])[?]) */,
			OCLstdlibTables.Operations._Map__includesValue /* includesValue(V[?]) */,
			OCLstdlibTables.Operations._Map__including /* including(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__includingMap /* includingMap(K2, V2)(Map($$2[1],$$3[1])[?]) */,
			OCLstdlibTables.Operations._Map__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Map__isUnique /* isUnique(K[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Map__iterate /* iterate(Tacc)(K[?];Tacc[?]|Lambda $$0() : $$2[?]) */,
			OCLstdlibTables.Operations._Map__keys /* keys() */,
			OCLstdlibTables.Operations._Map__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Map__one /* one(K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__reject /* reject(K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__2_search /* search(Q, R)(K[?],K[?],K[?];Q[?]|Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$3[?]) */,
			OCLstdlibTables.Operations._Map__1_search /* search(Q, R)(K[?],K[?];Q[?]|Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$3[?]) */,
			OCLstdlibTables.Operations._Map__0_search /* search(Q, R)(K[?];Q[?]|Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$2(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$3[?]) */,
			OCLstdlibTables.Operations._Map__select /* select(K[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__size /* size() */,
			OCLstdlibTables.Operations._Map__values /* values() */
		};
		private static final @NonNull Operation @NonNull [] _Map__OclAny = {
			OCLstdlibTables.Operations._Map___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Map___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _OclAny__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclComparable__OclComparable = {
			OCLstdlibTables.Operations._OclComparable___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable__compareTo /* compareTo(OclSelf[1]) */
		};
		private static final @NonNull Operation @NonNull [] _OclComparable__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclElement__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _OclElement__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclEnumeration__OclEnumeration = {};
		private static final @NonNull Operation @NonNull [] _OclEnumeration__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _OclEnumeration__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _OclEnumeration__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};

		private static final @NonNull Operation @NonNull [] _OclInvalid__OclInvalid = {
			OCLstdlibTables.Operations._OclInvalid___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid__and /* _'and'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclInvalid__implies /* _'implies'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclInvalid__or /* _'or'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclInvalid__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclInvalid__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclBadOperation /* oclBadOperation() */,
			OCLstdlibTables.Operations._OclInvalid__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclInvalid__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclInvalid__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclInvalid__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _OclInvalid__OclAny = {
			OCLstdlibTables.Operations._OclInvalid___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclInvalid__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclInvalid__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclInvalid__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclVoid__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclInvalid__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _OclInvalid__OclVoid = {
			OCLstdlibTables.Operations._OclVoid___add_ /* _'+'(String[?]) */,
			OCLstdlibTables.Operations._OclInvalid___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclInvalid__and /* _'and'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclInvalid__implies /* _'implies'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__not /* _'not'() */,
			OCLstdlibTables.Operations._OclInvalid__or /* _'or'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__xor /* _'xor'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__concat /* concat(String[?]) */,
			OCLstdlibTables.Operations._OclInvalid__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclInvalid__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclInvalid__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclInvalid__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclInvalid__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclVoid__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclInvalid__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _OclLambda__OclLambda = {};
		private static final @NonNull Operation @NonNull [] _OclLambda__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclMessage__OclMessage = {
			OCLstdlibTables.Operations._OclMessage__hasReturned /* hasReturned() */,
			OCLstdlibTables.Operations._OclMessage__isOperationCall /* isOperationCall() */,
			OCLstdlibTables.Operations._OclMessage__isSignalSent /* isSignalSent() */,
			OCLstdlibTables.Operations._OclMessage__result /* result() */
		};
		private static final @NonNull Operation @NonNull [] _OclMessage__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclSelf__OclSelf = {};
		private static final @NonNull Operation @NonNull [] _OclSelf__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclState__OclState = {};
		private static final @NonNull Operation @NonNull [] _OclState__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclStereotype__OclStereotype = {};
		private static final @NonNull Operation @NonNull [] _OclStereotype__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _OclStereotype__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final @NonNull Operation @NonNull [] _OclStereotype__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};

		private static final @NonNull Operation @NonNull [] _OclSummable__OclSummable = {
			OCLstdlibTables.Operations._OclSummable__sum /* sum(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclSummable__zero /* zero() */
		};
		private static final @NonNull Operation @NonNull [] _OclSummable__OclAny = {
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

		private static final @NonNull Operation @NonNull [] _OclTuple__OclTuple = {
			OCLstdlibTables.Operations._OclTuple___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclTuple___eq_ /* _'='(OclSelf[?]) */
		};
		private static final @NonNull Operation @NonNull [] _OclTuple__OclAny = {
			OCLstdlibTables.Operations._OclTuple___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclTuple___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _OclType__OclType = {
			OCLstdlibTables.Operations._OclType__conformsTo /* conformsTo(OclType[?]) */
		};
		private static final @NonNull Operation @NonNull [] _OclType__OclAny = {
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
		private static final @NonNull Operation @NonNull [] _OclType__OclElement = {
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclElement__0_oclBase /* oclBase() */,
			OCLstdlibTables.Operations._OclElement__1_oclBase /* oclBase(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclExtension /* oclExtension(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__1_oclExtensions /* oclExtensions() */,
			OCLstdlibTables.Operations._OclElement__0_oclExtensions /* oclExtensions(OclStereotype[1]) */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final @NonNull Operation @NonNull [] _OclVoid__OclVoid = {
			OCLstdlibTables.Operations._OclVoid___add_ /* _'+'(String[?]) */,
			OCLstdlibTables.Operations._OclVoid___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclVoid___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclVoid__and /* _'and'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__implies /* _'implies'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__not /* _'not'() */,
			OCLstdlibTables.Operations._OclVoid__or /* _'or'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__xor /* _'xor'(Boolean[?]) */,
			OCLstdlibTables.Operations._OclVoid__concat /* concat(String[?]) */,
			OCLstdlibTables.Operations._OclVoid__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclVoid__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclVoid__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclVoid__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclVoid__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclVoid__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclVoid__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclVoid__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclVoid__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _OclVoid__OclAny = {
			OCLstdlibTables.Operations._OclVoid___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclVoid___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclVoid__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclVoid__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclVoid__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclVoid__oclIsKindOf /* oclIsKindOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclVoid__oclIsTypeOf /* oclIsTypeOf(OclType[1]) */,
			OCLstdlibTables.Operations._OclVoid__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[1]) */,
			OCLstdlibTables.Operations._OclVoid__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclVoid__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclVoid__toString /* toString() */
		};

		private static final @NonNull Operation @NonNull [] _OrderedCollection__OrderedCollection = {
			OCLstdlibTables.Operations._OrderedCollection__at /* at(Integer[1]) */,
			OCLstdlibTables.Operations._OrderedCollection__first /* first() */,
			OCLstdlibTables.Operations._OrderedCollection__gather /* gather(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};
		private static final @NonNull Operation @NonNull [] _OrderedCollection__Collection = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Collection__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _OrderedCollection__OclAny = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _OrderedSet__OrderedSet = {
			OCLstdlibTables.Operations._OrderedSet___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._OrderedSet___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OrderedSet___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OrderedSet__append /* append(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__appendAll /* appendAll(OrderedCollection($$0)) */,
			OCLstdlibTables.Operations._OrderedSet__closure /* closure(E)(E[?]|Lambda $$1() : OrderedCollection($$1)[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._OrderedSet__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._OrderedSet__including /* including(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._OrderedSet__insertAt /* insertAt(Integer[1],T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__prepend /* prepend(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__prependAll /* prependAll(OrderedCollection($$0)) */,
			OCLstdlibTables.Operations._OrderedSet__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._OrderedSet__reverse /* reverse() */,
			OCLstdlibTables.Operations._OrderedSet__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._OrderedSet__subOrderedSet /* subOrderedSet(Integer[1],Integer[1]) */
		};
		private static final @NonNull Operation @NonNull [] _OrderedSet__Collection = {
			OCLstdlibTables.Operations._OrderedSet___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OrderedSet___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._OrderedSet__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._OrderedSet__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._OrderedSet__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._OrderedSet__including /* including(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._OrderedSet__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._OrderedSet__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _OrderedSet__OclAny = {
			OCLstdlibTables.Operations._OrderedSet___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OrderedSet___eq_ /* _'='(OclSelf[?]) */,
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
		private static final @NonNull Operation @NonNull [] _OrderedSet__OrderedCollection = {
			OCLstdlibTables.Operations._OrderedCollection__at /* at(Integer[1]) */,
			OCLstdlibTables.Operations._OrderedCollection__first /* first() */,
			OCLstdlibTables.Operations._OrderedCollection__gather /* gather(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};
		private static final @NonNull Operation @NonNull [] _OrderedSet__UniqueCollection = {
			OCLstdlibTables.Operations._OrderedSet___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection($$0)) */
		};

		private static final @NonNull Operation @NonNull [] _Real__Real = {
			OCLstdlibTables.Operations._Real___mul_ /* _'*'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real___add_ /* _'+'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real___neg_ /* _'-'() */,
			OCLstdlibTables.Operations._Real___sub_ /* _'-'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real___div_ /* _'/'(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Real___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Real__abs /* abs() */,
			OCLstdlibTables.Operations._Real__floor /* floor() */,
			OCLstdlibTables.Operations._Real__max /* max(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real__min /* min(OclSelf[1]) */,
			OCLstdlibTables.Operations._Real__round /* round() */,
			OCLstdlibTables.Operations._Real__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _Real__OclAny = {
			OCLstdlibTables.Operations._Real___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Real___eq_ /* _'='(OclSelf[?]) */,
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
			OCLstdlibTables.Operations._Real__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _Real__OclComparable = {
			OCLstdlibTables.Operations._OclComparable___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable__compareTo /* compareTo(OclSelf[1]) */
		};
		private static final @NonNull Operation @NonNull [] _Real__OclSummable = {
			OCLstdlibTables.Operations._OclSummable__sum /* sum(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclSummable__zero /* zero() */
		};

		private static final @NonNull Operation @NonNull [] _Sequence__Sequence = {
			OCLstdlibTables.Operations._Sequence___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Sequence___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Sequence__append /* append(T[?]) */,
			OCLstdlibTables.Operations._Sequence__appendAll /* appendAll(OrderedCollection($$0)) */,
			OCLstdlibTables.Operations._Sequence__closure /* closure(E)(E[?]|Lambda $$1() : OrderedCollection($$1)[?]) */,
			OCLstdlibTables.Operations._Sequence__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Sequence__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Sequence__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Sequence__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Sequence__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Sequence__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Sequence__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Sequence__insertAt /* insertAt(Integer[1],T[?]) */,
			OCLstdlibTables.Operations._Sequence__prepend /* prepend(T[?]) */,
			OCLstdlibTables.Operations._Sequence__prependAll /* prependAll(OrderedCollection($$0)) */,
			OCLstdlibTables.Operations._Sequence__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Sequence__reverse /* reverse() */,
			OCLstdlibTables.Operations._Sequence__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Sequence__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Sequence__subSequence /* subSequence(Integer[1],Integer[1]) */
		};
		private static final @NonNull Operation @NonNull [] _Sequence__Collection = {
			OCLstdlibTables.Operations._Sequence___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Sequence___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Sequence__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Sequence__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Sequence__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Sequence__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Sequence__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Sequence__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Sequence__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Sequence__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Sequence__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Sequence__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Sequence__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _Sequence__OclAny = {
			OCLstdlibTables.Operations._Sequence___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Sequence___eq_ /* _'='(OclSelf[?]) */,
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
		private static final @NonNull Operation @NonNull [] _Sequence__OrderedCollection = {
			OCLstdlibTables.Operations._OrderedCollection__at /* at(Integer[1]) */,
			OCLstdlibTables.Operations._OrderedCollection__first /* first() */,
			OCLstdlibTables.Operations._OrderedCollection__gather /* gather(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};

		private static final @NonNull Operation @NonNull [] _Set__Set = {
			OCLstdlibTables.Operations._Set___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._Set___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set__closure /* closure(E)(E[?]|Lambda $$1() : Collection($$1)[?]) */,
			OCLstdlibTables.Operations._Set__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Set__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Set__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Set__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Set__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Set__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Set__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Set__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Set__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Set__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */
		};
		private static final @NonNull Operation @NonNull [] _Set__Collection = {
			OCLstdlibTables.Operations._Set___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Set__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Set__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Set__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Set__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Set__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Set__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Set__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Set__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Set__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Set__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _Set__OclAny = {
			OCLstdlibTables.Operations._Set___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set___eq_ /* _'='(OclSelf[?]) */,
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
		private static final @NonNull Operation @NonNull [] _Set__UniqueCollection = {
			OCLstdlibTables.Operations._Set___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection($$0)) */
		};

		private static final @NonNull Operation @NonNull [] _String__String = {
			OCLstdlibTables.Operations._String___add_ /* _'+'(String[?]) */,
			OCLstdlibTables.Operations._String___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._String___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._String___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._String__at /* at(Integer[1]) */,
			OCLstdlibTables.Operations._String__characters /* characters() */,
			OCLstdlibTables.Operations._String__compareTo /* compareTo(OclSelf[1]) */,
			OCLstdlibTables.Operations._String__concat /* concat(String[?]) */,
			OCLstdlibTables.Operations._String__endsWith /* endsWith(String[1]) */,
			OCLstdlibTables.Operations._String__equalsIgnoreCase /* equalsIgnoreCase(String[1]) */,
			OCLstdlibTables.Operations._String__indexOf /* indexOf(String[1]) */,
			OCLstdlibTables.Operations._String__lastIndexOf /* lastIndexOf(String[1]) */,
			OCLstdlibTables.Operations._String__matches /* matches(String[1]) */,
			OCLstdlibTables.Operations._String__replaceAll /* replaceAll(String[1],String[1]) */,
			OCLstdlibTables.Operations._String__replaceFirst /* replaceFirst(String[1],String[1]) */,
			OCLstdlibTables.Operations._String__size /* size() */,
			OCLstdlibTables.Operations._String__startsWith /* startsWith(String[1]) */,
			OCLstdlibTables.Operations._String__substituteAll /* substituteAll(String[1],String[1]) */,
			OCLstdlibTables.Operations._String__substituteFirst /* substituteFirst(String[1],String[1]) */,
			OCLstdlibTables.Operations._String__substring /* substring(Integer[1],Integer[1]) */,
			OCLstdlibTables.Operations._String__toBoolean /* toBoolean() */,
			OCLstdlibTables.Operations._String__toInteger /* toInteger() */,
			OCLstdlibTables.Operations._String__toLower /* toLower() */,
			OCLstdlibTables.Operations._String__toLowerCase /* toLowerCase() */,
			OCLstdlibTables.Operations._String__toReal /* toReal() */,
			OCLstdlibTables.Operations._String__toString /* toString() */,
			OCLstdlibTables.Operations._String__toUpper /* toUpper() */,
			OCLstdlibTables.Operations._String__toUpperCase /* toUpperCase() */,
			OCLstdlibTables.Operations._String__0_tokenize /* tokenize() */,
			OCLstdlibTables.Operations._String__1_tokenize /* tokenize(String[1]) */,
			OCLstdlibTables.Operations._String__2_tokenize /* tokenize(String[1],Boolean[1]) */,
			OCLstdlibTables.Operations._String__trim /* trim() */
		};
		private static final @NonNull Operation @NonNull [] _String__OclAny = {
			OCLstdlibTables.Operations._String___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._String___eq_ /* _'='(OclSelf[?]) */,
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
			OCLstdlibTables.Operations._String__toString /* toString() */
		};
		private static final @NonNull Operation @NonNull [] _String__OclComparable = {
			OCLstdlibTables.Operations._String___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._String___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._String__compareTo /* compareTo(OclSelf[1]) */
		};
		private static final @NonNull Operation @NonNull [] _String__OclSummable = {
			OCLstdlibTables.Operations._OclSummable__sum /* sum(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclSummable__zero /* zero() */
		};

		private static final @NonNull Operation @NonNull [] _UniqueCollection__UniqueCollection = {
			OCLstdlibTables.Operations._UniqueCollection___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._UniqueCollection__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _UniqueCollection__Collection = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(E)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__gather /* gather(V)(T[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection($$0)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection($$0)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection($$0)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda $$0() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda $$0() : $$1[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection($$1)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__2_search /* search(Q, R)(T[?],T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],k : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__1_search /* search(Q, R)(T[?],T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?],j : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__0_search /* search(Q, R)(T[?];Q[?]|Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$1[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : Boolean[?],Lambda $$1(i : _'$$'(_'$$0', _'$$1', _'$$2', _'$$3')::_'$$0'[?]) : $$2[?]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda $$0() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._UniqueCollection__sortedBy /* sortedBy(T[?]|Lambda $$0() : OclComparable[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection($$0)) */
		};
		private static final @NonNull Operation @NonNull [] _UniqueCollection__OclAny = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
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

		private static final @NonNull Operation @NonNull [] _UnlimitedNatural__UnlimitedNatural = {
			OCLstdlibTables.Operations._UnlimitedNatural__max /* max(OclSelf[1]) */,
			OCLstdlibTables.Operations._UnlimitedNatural__min /* min(OclSelf[1]) */,
			OCLstdlibTables.Operations._UnlimitedNatural__oclAsType /* oclAsType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._UnlimitedNatural__toInteger /* toInteger() */
		};
		private static final @NonNull Operation @NonNull [] _UnlimitedNatural__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._UnlimitedNatural__oclAsType /* oclAsType(TT)(TT[1]) */,
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
		private static final @NonNull Operation @NonNull [] _UnlimitedNatural__OclComparable = {
			OCLstdlibTables.Operations._OclComparable___lt_ /* _'<'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___lt__eq_ /* _'<='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt_ /* _'>'(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable___gt__eq_ /* _'>='(OclSelf[1]) */,
			OCLstdlibTables.Operations._OclComparable__compareTo /* compareTo(OclSelf[1]) */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Bag__Bag.initOperations(_Bag__Bag);
			Fragments._Bag__Collection.initOperations(_Bag__Collection);
			Fragments._Bag__OclAny.initOperations(_Bag__OclAny);

			Fragments._Boolean__Boolean.initOperations(_Boolean__Boolean);
			Fragments._Boolean__OclAny.initOperations(_Boolean__OclAny);

			Fragments._Collection__Collection.initOperations(_Collection__Collection);
			Fragments._Collection__OclAny.initOperations(_Collection__OclAny);

			Fragments._Integer__Integer.initOperations(_Integer__Integer);
			Fragments._Integer__OclAny.initOperations(_Integer__OclAny);
			Fragments._Integer__OclComparable.initOperations(_Integer__OclComparable);
			Fragments._Integer__OclSummable.initOperations(_Integer__OclSummable);
			Fragments._Integer__Real.initOperations(_Integer__Real);

			Fragments._Map__Map.initOperations(_Map__Map);
			Fragments._Map__OclAny.initOperations(_Map__OclAny);

			Fragments._OclAny__OclAny.initOperations(_OclAny__OclAny);

			Fragments._OclComparable__OclAny.initOperations(_OclComparable__OclAny);
			Fragments._OclComparable__OclComparable.initOperations(_OclComparable__OclComparable);

			Fragments._OclElement__OclAny.initOperations(_OclElement__OclAny);
			Fragments._OclElement__OclElement.initOperations(_OclElement__OclElement);

			Fragments._OclEnumeration__OclAny.initOperations(_OclEnumeration__OclAny);
			Fragments._OclEnumeration__OclElement.initOperations(_OclEnumeration__OclElement);
			Fragments._OclEnumeration__OclEnumeration.initOperations(_OclEnumeration__OclEnumeration);
			Fragments._OclEnumeration__OclType.initOperations(_OclEnumeration__OclType);

			Fragments._OclInvalid__OclAny.initOperations(_OclInvalid__OclAny);
			Fragments._OclInvalid__OclInvalid.initOperations(_OclInvalid__OclInvalid);
			Fragments._OclInvalid__OclVoid.initOperations(_OclInvalid__OclVoid);

			Fragments._OclLambda__OclAny.initOperations(_OclLambda__OclAny);
			Fragments._OclLambda__OclLambda.initOperations(_OclLambda__OclLambda);

			Fragments._OclMessage__OclAny.initOperations(_OclMessage__OclAny);
			Fragments._OclMessage__OclMessage.initOperations(_OclMessage__OclMessage);

			Fragments._OclSelf__OclAny.initOperations(_OclSelf__OclAny);
			Fragments._OclSelf__OclSelf.initOperations(_OclSelf__OclSelf);

			Fragments._OclState__OclAny.initOperations(_OclState__OclAny);
			Fragments._OclState__OclState.initOperations(_OclState__OclState);

			Fragments._OclStereotype__OclAny.initOperations(_OclStereotype__OclAny);
			Fragments._OclStereotype__OclElement.initOperations(_OclStereotype__OclElement);
			Fragments._OclStereotype__OclStereotype.initOperations(_OclStereotype__OclStereotype);
			Fragments._OclStereotype__OclType.initOperations(_OclStereotype__OclType);

			Fragments._OclSummable__OclAny.initOperations(_OclSummable__OclAny);
			Fragments._OclSummable__OclSummable.initOperations(_OclSummable__OclSummable);

			Fragments._OclTuple__OclAny.initOperations(_OclTuple__OclAny);
			Fragments._OclTuple__OclTuple.initOperations(_OclTuple__OclTuple);

			Fragments._OclType__OclAny.initOperations(_OclType__OclAny);
			Fragments._OclType__OclElement.initOperations(_OclType__OclElement);
			Fragments._OclType__OclType.initOperations(_OclType__OclType);

			Fragments._OclVoid__OclAny.initOperations(_OclVoid__OclAny);
			Fragments._OclVoid__OclVoid.initOperations(_OclVoid__OclVoid);

			Fragments._OrderedCollection__Collection.initOperations(_OrderedCollection__Collection);
			Fragments._OrderedCollection__OclAny.initOperations(_OrderedCollection__OclAny);
			Fragments._OrderedCollection__OrderedCollection.initOperations(_OrderedCollection__OrderedCollection);

			Fragments._OrderedSet__Collection.initOperations(_OrderedSet__Collection);
			Fragments._OrderedSet__OclAny.initOperations(_OrderedSet__OclAny);
			Fragments._OrderedSet__OrderedCollection.initOperations(_OrderedSet__OrderedCollection);
			Fragments._OrderedSet__OrderedSet.initOperations(_OrderedSet__OrderedSet);
			Fragments._OrderedSet__UniqueCollection.initOperations(_OrderedSet__UniqueCollection);

			Fragments._Real__OclAny.initOperations(_Real__OclAny);
			Fragments._Real__OclComparable.initOperations(_Real__OclComparable);
			Fragments._Real__OclSummable.initOperations(_Real__OclSummable);
			Fragments._Real__Real.initOperations(_Real__Real);

			Fragments._Sequence__Collection.initOperations(_Sequence__Collection);
			Fragments._Sequence__OclAny.initOperations(_Sequence__OclAny);
			Fragments._Sequence__OrderedCollection.initOperations(_Sequence__OrderedCollection);
			Fragments._Sequence__Sequence.initOperations(_Sequence__Sequence);

			Fragments._Set__Collection.initOperations(_Set__Collection);
			Fragments._Set__OclAny.initOperations(_Set__OclAny);
			Fragments._Set__Set.initOperations(_Set__Set);
			Fragments._Set__UniqueCollection.initOperations(_Set__UniqueCollection);

			Fragments._String__OclAny.initOperations(_String__OclAny);
			Fragments._String__OclComparable.initOperations(_String__OclComparable);
			Fragments._String__OclSummable.initOperations(_String__OclSummable);
			Fragments._String__String.initOperations(_String__String);

			Fragments._UniqueCollection__Collection.initOperations(_UniqueCollection__Collection);
			Fragments._UniqueCollection__OclAny.initOperations(_UniqueCollection__OclAny);
			Fragments._UniqueCollection__UniqueCollection.initOperations(_UniqueCollection__UniqueCollection);

			Fragments._UnlimitedNatural__OclAny.initOperations(_UnlimitedNatural__OclAny);
			Fragments._UnlimitedNatural__OclComparable.initOperations(_UnlimitedNatural__OclComparable);
			Fragments._UnlimitedNatural__UnlimitedNatural.initOperations(_UnlimitedNatural__UnlimitedNatural);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::FragmentOperations and all preceding sub-packages.
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

		private static final @NonNull Property @NonNull [] _Bag = {};

		private static final @NonNull Property @NonNull [] _Boolean = {};

		private static final @NonNull Property @NonNull [] _Collection = {};

		private static final @NonNull Property @NonNull [] _Integer = {};

		private static final @NonNull Property @NonNull [] _Map = {};

		private static final @NonNull Property @NonNull [] _OclAny = {};

		private static final @NonNull Property @NonNull [] _OclComparable = {};

		private static final @NonNull Property @NonNull [] _OclElement = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _OclEnumeration = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _OclInvalid = {
			OCLstdlibTables.Properties._OclInvalid__oclBadProperty
		};

		private static final @NonNull Property @NonNull [] _OclLambda = {};

		private static final @NonNull Property @NonNull [] _OclMessage = {};

		private static final @NonNull Property @NonNull [] _OclSelf = {};

		private static final @NonNull Property @NonNull [] _OclState = {};

		private static final @NonNull Property @NonNull [] _OclStereotype = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _OclSummable = {};

		private static final @NonNull Property @NonNull [] _OclTuple = {};

		private static final @NonNull Property @NonNull [] _OclType = {
			OCLstdlibTables.Properties._OclElement__oclContainer,
			OCLstdlibTables.Properties._OclElement__oclContents
		};

		private static final @NonNull Property @NonNull [] _OclVoid = {};

		private static final @NonNull Property @NonNull [] _OrderedCollection = {};

		private static final @NonNull Property @NonNull [] _OrderedSet = {};

		private static final @NonNull Property @NonNull [] _Real = {};

		private static final @NonNull Property @NonNull [] _Sequence = {};

		private static final @NonNull Property @NonNull [] _Set = {};

		private static final @NonNull Property @NonNull [] _String = {};

		private static final @NonNull Property @NonNull [] _UniqueCollection = {};

		private static final @NonNull Property @NonNull [] _UnlimitedNatural = {};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Bag__Bag.initProperties(_Bag);
			Fragments._Boolean__Boolean.initProperties(_Boolean);
			Fragments._Collection__Collection.initProperties(_Collection);
			Fragments._Integer__Integer.initProperties(_Integer);
			Fragments._Map__Map.initProperties(_Map);
			Fragments._OclAny__OclAny.initProperties(_OclAny);
			Fragments._OclComparable__OclComparable.initProperties(_OclComparable);
			Fragments._OclElement__OclElement.initProperties(_OclElement);
			Fragments._OclEnumeration__OclEnumeration.initProperties(_OclEnumeration);
			Fragments._OclInvalid__OclInvalid.initProperties(_OclInvalid);
			Fragments._OclLambda__OclLambda.initProperties(_OclLambda);
			Fragments._OclMessage__OclMessage.initProperties(_OclMessage);
			Fragments._OclSelf__OclSelf.initProperties(_OclSelf);
			Fragments._OclState__OclState.initProperties(_OclState);
			Fragments._OclStereotype__OclStereotype.initProperties(_OclStereotype);
			Fragments._OclSummable__OclSummable.initProperties(_OclSummable);
			Fragments._OclTuple__OclTuple.initProperties(_OclTuple);
			Fragments._OclType__OclType.initProperties(_OclType);
			Fragments._OclVoid__OclVoid.initProperties(_OclVoid);
			Fragments._OrderedCollection__OrderedCollection.initProperties(_OrderedCollection);
			Fragments._OrderedSet__OrderedSet.initProperties(_OrderedSet);
			Fragments._Real__Real.initProperties(_Real);
			Fragments._Sequence__Sequence.initProperties(_Sequence);
			Fragments._Set__Set.initProperties(_Set);
			Fragments._String__String.initProperties(_String);
			Fragments._UniqueCollection__UniqueCollection.initProperties(_UniqueCollection);
			Fragments._UnlimitedNatural__UnlimitedNatural.initProperties(_UnlimitedNatural);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::FragmentProperties and all preceding sub-packages.
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
		 * Force initialization of the fields of OCLstdlibTables::EnumerationLiterals and all preceding sub-packages.
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
		new OCLstdlibTables();
	}

	private OCLstdlibTables() {
		super(OCLstdlibPackage.eNS_URI);
	}
}
