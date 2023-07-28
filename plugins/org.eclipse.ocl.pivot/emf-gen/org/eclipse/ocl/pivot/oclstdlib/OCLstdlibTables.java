/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
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
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.pivot.oclstdlib;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
// import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

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
	public static final @NonNull ExecutorStandardLibrary LIBRARY = new ExecutorStandardLibrary();

	/**
	 *	The package descriptor for the package.
	 */
	public static final org.eclipse.ocl.pivot.@NonNull Package PACKAGE = LIBRARY.createPackage(OCLstdlibPackage.eINSTANCE, IdManager.METAMODEL);


	// CTOR platform:/resource/org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib

	// CTOR ocl
	// CTOR $$
	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			OCLstdlibTables.init();
		}

		public static final @NonNull TemplateParameter _0_K = LIBRARY.createTemplateParameter(0, "K");
		public static final @NonNull TemplateParameter _0_T = LIBRARY.createTemplateParameter(0, "T");
		public static final @NonNull TemplateParameter _0_TT = LIBRARY.createTemplateParameter(0, "TT");
		public static final @NonNull TemplateParameter _1_T2 = LIBRARY.createTemplateParameter(1, "T2");
		public static final @NonNull TemplateParameter _1_TT = LIBRARY.createTemplateParameter(1, "TT");
		public static final @NonNull TemplateParameter _1_Tacc = LIBRARY.createTemplateParameter(1, "Tacc");
		public static final @NonNull TemplateParameter _1_V = LIBRARY.createTemplateParameter(1, "V");
		public static final @NonNull TemplateParameter _2_K2 = LIBRARY.createTemplateParameter(2, "K2");
		public static final @NonNull TemplateParameter _2_Tacc = LIBRARY.createTemplateParameter(2, "Tacc");
		public static final @NonNull TemplateParameter _2_V2 = LIBRARY.createTemplateParameter(2, "V2");
		public static final @NonNull TemplateParameter _3_V2 = LIBRARY.createTemplateParameter(3, "V2");

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

		public static final org.eclipse.ocl.pivot.@NonNull Class _Bag = LIBRARY.createClass(PivotPackage.Literals.BAG_TYPE, OCLstdlibPackage.Literals.BAG, PACKAGE, TypeId.BAG, 0, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Boolean = LIBRARY.createClass(PivotPackage.Literals.BOOLEAN_TYPE, OCLstdlibPackage.Literals.BOOLEAN, PACKAGE, TypeId.BOOLEAN, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Collection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.COLLECTION, PACKAGE, TypeId.COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Integer = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.INTEGER, PACKAGE, TypeId.INTEGER, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Map = LIBRARY.createClass(PivotPackage.Literals.MAP_TYPE, OCLstdlibPackage.Literals.MAP, PACKAGE, TypeId.MAP, 0, TypeParameters._0_K, TypeParameters._1_V);
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
		public static final org.eclipse.ocl.pivot.@NonNull Class _OrderedCollection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.ORDERED_COLLECTION, PACKAGE, TypeId.ORDERED_COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _OrderedSet = LIBRARY.createClass(PivotPackage.Literals.ORDERED_SET_TYPE, OCLstdlibPackage.Literals.ORDERED_SET, PACKAGE, TypeId.ORDERED_SET, FlatClass.ORDERED | FlatClass.UNIQUE, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Real = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.REAL, PACKAGE, TypeId.REAL, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Sequence = LIBRARY.createClass(PivotPackage.Literals.SEQUENCE_TYPE, OCLstdlibPackage.Literals.SEQUENCE, PACKAGE, TypeId.SEQUENCE, FlatClass.ORDERED, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _Set = LIBRARY.createClass(PivotPackage.Literals.SET_TYPE, OCLstdlibPackage.Literals.SET, PACKAGE, TypeId.SET, FlatClass.UNIQUE, TypeParameters._0_T);
		public static final org.eclipse.ocl.pivot.@NonNull Class _String = LIBRARY.createClass(PivotPackage.Literals.PRIMITIVE_TYPE, OCLstdlibPackage.Literals.STRING, PACKAGE, TypeId.STRING, 0);
		public static final org.eclipse.ocl.pivot.@NonNull Class _UniqueCollection = LIBRARY.createClass(PivotPackage.Literals.COLLECTION_TYPE, OCLstdlibPackage.Literals.UNIQUE_COLLECTION, PACKAGE, TypeId.UNIQUE_COLLECTION, 0 | FlatClass.ABSTRACT, TypeParameters._0_T);
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
	// CTOR Boolean
	// CTOR Integer
	// CTOR Real
	// CTOR String
	// CTOR UnlimitedNatural
	// CTOR OclAny
	// CTOR BooleanType
	// CTOR Class
	// CTOR CollectionType
	// CTOR Enumeration
	// CTOR EnumerationLiteral
	// CTOR InvalidType
	// CTOR MapType
	// CTOR OclComparable
	// CTOR OclElement
	// CTOR OclEnumeration
	// CTOR OclLambda
	// CTOR OclMessage
	// CTOR OclState
	// CTOR OclStereotype
	// CTOR OclSummable
	// CTOR OclTuple
	// CTOR OclType
	// CTOR State
	// CTOR Stereotype
	// CTOR Type
	// CTOR VoidType
	// CTOR OclInvalid
	// CTOR OclSelf
	// CTOR OclVoid
	// CTOR Collection(T)
	// CTOR OrderedCollection(T)
	// CTOR UniqueCollection(T)
	// CTOR Map(K,V)
	// CTOR Bag(T)
	// CTOR OrderedSet(T)
	// CTOR Sequence(T)
	// CTOR Set(T)

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
	// CTOR CollectionType::lower
	// CTOR CollectionType::upper
	// CTOR Collection(T)::elementType
	// CTOR Collection(T)::lower
	// CTOR Collection(T)::upper
	// CTOR Map(K,V)::keyType
	// CTOR Map(K,V)::valueType
	// CTOR OclAny::OclInvalid
	// CTOR OclElement::OclElement
	// CTOR Type::CollectionType
	// CTOR Type::MapType
	// CTOR Type::MapType
	// CTOR Bag.T
	// CTOR Collection.T
	// CTOR Map.K
	// CTOR Map.V
	// CTOR OrderedCollection.T
	// CTOR OrderedSet.T
	// CTOR Sequence.T
	// CTOR Set.T
	// CTOR UniqueCollection.T
	// CTOR Bag(T).closure(Bag.T[1] | Lambda T() : Set(Bag.T)[?]) : Set(Bag.T)
	// CTOR Bag(T).collectNested(V)(Bag.T[?] | Lambda T() : V[?]) : Bag(collectNested.V)
	// CTOR Bag(T).collect(V)(Bag.T[?] | Lambda T() : V[?]) : Bag(collect.V)
	// CTOR Bag(T).reject(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(Bag.T)
	// CTOR Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(Bag.T)
	// CTOR Bag(T).sortedBy(Bag.T[?] | Lambda T() : OclAny[?]) : Sequence(Bag.T)
	// CTOR Collection(T).any(Collection.T[1] | Lambda T() : Boolean[1]) : Collection.T[?]
	// CTOR Collection(T).collectBy(V)(Collection.T[?] | Lambda T() : V[?]) : Map(Collection.T[?],collectBy.V[?])[1]
	// CTOR Collection(T).collectNested(V)(Collection.T[?] | Lambda T() : V[?]) : Collection(collectNested.V)
	// CTOR Collection(T).collect(V)(Collection.T[?] | Lambda T() : V[?]) : Collection(collect.V)
	// CTOR Collection(T).exists(Collection.T[?], Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).exists(Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).exists(Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).forAll(Collection.T[?], Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).forAll(Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).forAll(Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// CTOR Collection(T).isUnique(Collection.T[?] | Lambda T() : OclAny[?]) : Boolean[1]
	// CTOR Collection(T).iterate(Tacc)(Collection.T[?]; iterate.Tacc[?] | Lambda T() : Tacc[?]) : iterate.Tacc[?]
	// CTOR Collection(T).one(Collection.T[?] | Lambda T() : Boolean[1]) : Boolean[1]
	// CTOR Collection(T).reject(Collection.T[?] | Lambda T() : Boolean[1]) : Collection(Collection.T)
	// CTOR Collection(T).select(Collection.T[?] | Lambda T() : Boolean[1]) : Collection(Collection.T)
	// CTOR Collection(T).sortedBy(Collection.T[?] | Lambda T() : OclAny[?]) : Sequence(Collection.T)
	// CTOR Map(K,V).any(Map.K[1] | Lambda K() : Boolean[1]) : Map.K[?]
	// CTOR Map(K,V).collectBy(V2)(Map.K[?] | Lambda K() : V2[?]) : Map(Map.K[?],collectBy.V2[?])[1]
	// CTOR Map(K,V).collectNested(V2)(Map.K[?] | Lambda K() : V2[?]) : Map(Map.K[?],collectNested.V2[?])[1]
	// CTOR Map(K,V).collect(V2)(Map.K[?] | Lambda K() : V2[?]) : Bag(collect.V2)
	// CTOR Map(K,V).exists(Map.K[?], Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).exists(Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).exists(Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).forAll(Map.K[?], Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).forAll(Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).forAll(Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// CTOR Map(K,V).isUnique(Map.K[?] | Lambda K() : OclAny[?]) : Boolean[1]
	// CTOR Map(K,V).iterate(Tacc)(Map.K[?]; iterate.Tacc[?] | Lambda K() : Tacc[?]) : iterate.Tacc[?]
	// CTOR Map(K,V).one(Map.K[?] | Lambda K() : Boolean[1]) : Boolean[1]
	// CTOR Map(K,V).reject(Map.K[?] | Lambda K() : Boolean[1]) : Map(Map.K[?],Map.V[?])[1]
	// CTOR Map(K,V).select(Map.K[?] | Lambda K() : Boolean[1]) : Map(Map.K[?],Map.V[?])[1]
	// CTOR OrderedSet(T).closure(OrderedSet.T[1] | Lambda T() : OrderedSet(OrderedSet.T)[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T).collectNested(V)(OrderedSet.T[?] | Lambda T() : V[?]) : Sequence(collectNested.V)
	// CTOR OrderedSet(T).collect(V)(OrderedSet.T[?] | Lambda T() : V[?]) : Sequence(collect.V)
	// CTOR OrderedSet(T).reject(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T).sortedBy(OrderedSet.T[?] | Lambda T() : OclAny[?]) : OrderedSet(OrderedSet.T)
	// CTOR Sequence(T).closure(Sequence.T[1] | Lambda T() : OrderedSet(Sequence.T)[?]) : OrderedSet(Sequence.T)
	// CTOR Sequence(T).collectNested(V)(Sequence.T[?] | Lambda T() : V[?]) : Sequence(collectNested.V)
	// CTOR Sequence(T).collect(V)(Sequence.T[?] | Lambda T() : V[?]) : Sequence(collect.V)
	// CTOR Sequence(T).reject(Sequence.T[?] | Lambda T() : Boolean[1]) : Sequence(Sequence.T)
	// CTOR Sequence(T).select(Sequence.T[?] | Lambda T() : Boolean[1]) : Sequence(Sequence.T)
	// CTOR Sequence(T).sortedBy(Sequence.T[?] | Lambda T() : OclAny[?]) : Sequence(Sequence.T)
	// CTOR Set(T).closure(Set.T[1] | Lambda T() : Set(Set.T)[?]) : Set(Set.T)
	// CTOR Set(T).collectNested(V)(Set.T[?] | Lambda T() : V[?]) : Bag(collectNested.V)
	// CTOR Set(T).collect(V)(Set.T[?] | Lambda T() : V[?]) : Bag(collect.V)
	// CTOR Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(Set.T)
	// CTOR Set(T).select(Set.T[?] | Lambda T() : Boolean[1]) : Set(Set.T)
	// CTOR Set(T).sortedBy(Set.T[?] | Lambda T() : OclAny[?]) : OrderedSet(Set.T)
	// CTOR UniqueCollection(T).sortedBy(UniqueCollection.T[?] | Lambda T() : OclAny[?]) : OrderedSet(UniqueCollection.T)
	// CTOR Boolean::<>(OclSelf[?]) : Boolean[1]
	// CTOR Boolean::=(OclSelf[?]) : Boolean[1]
	// CTOR Boolean::allInstances(Integer[1]) : Set(OclSelf)
	// CTOR Boolean::and(Boolean[?]) : Boolean[?]
	// CTOR Boolean::and2(Boolean[1]) : Boolean[1]
	// CTOR Boolean::implies(Boolean[?]) : Boolean[?]
	// CTOR Boolean::implies2(Boolean[1]) : Boolean[1]
	// CTOR Boolean::not() : Boolean[?]
	// CTOR Boolean::not2() : Boolean[1]
	// CTOR Boolean::or(Boolean[?]) : Boolean[?]
	// CTOR Boolean::or2(Boolean[1]) : Boolean[1]
	// CTOR Boolean::toString() : String[1]
	// CTOR Boolean::xor(Boolean[?]) : Boolean[?]
	// CTOR Boolean::xor2(Boolean[1]) : Boolean[1]
	// CTOR Integer::*(OclSelf[1]) : Integer[1]
	// CTOR Integer::+(OclSelf[1]) : Integer[1]
	// CTOR Integer::-() : Integer[1]
	// CTOR Integer::-(OclSelf[1]) : Integer[1]
	// CTOR Integer::/(OclSelf[1]) : Real[1]
	// CTOR Integer::abs() : Integer[1]
	// CTOR Integer::div(Integer[1]) : Integer[1]
	// CTOR Integer::max(OclSelf[1]) : Integer[1]
	// CTOR Integer::min(OclSelf[1]) : Integer[1]
	// CTOR Integer::mod(Integer[1]) : Integer[1]
	// CTOR Integer::toString() : String[1]
	// CTOR Integer::toUnlimitedNatural() : UnlimitedNatural[1]
	// CTOR Real::*(OclSelf[1]) : Real[1]
	// CTOR Real::+(OclSelf[1]) : Real[1]
	// CTOR Real::-() : Real[1]
	// CTOR Real::-(OclSelf[1]) : Real[1]
	// CTOR Real::/(OclSelf[1]) : Real[1]
	// CTOR Real::<>(OclSelf[?]) : Boolean[1]
	// CTOR Real::=(OclSelf[?]) : Boolean[1]
	// CTOR Real::abs() : Real[1]
	// CTOR Real::floor() : Integer[1]
	// CTOR Real::max(OclSelf[1]) : Real[1]
	// CTOR Real::min(OclSelf[1]) : Real[1]
	// CTOR Real::round() : Integer[1]
	// CTOR Real::toString() : String[1]
	// CTOR String::+(String[?]) : String[1]
	// CTOR String::<(OclSelf[1]) : Boolean[1]
	// CTOR String::<=(OclSelf[1]) : Boolean[1]
	// CTOR String::<>(OclSelf[?]) : Boolean[1]
	// CTOR String::=(OclSelf[?]) : Boolean[1]
	// CTOR String::>(OclSelf[1]) : Boolean[1]
	// CTOR String::>=(OclSelf[1]) : Boolean[1]
	// CTOR String::at(Integer[1]) : String[1]
	// CTOR String::characters() : Sequence(String)
	// CTOR String::compareTo(OclSelf[1]) : Integer[1]
	// CTOR String::concat(String[?]) : String[1]
	// CTOR String::endsWith(String[1]) : Boolean[1]
	// CTOR String::equalsIgnoreCase(String[1]) : Boolean[1]
	// CTOR String::indexOf(String[1]) : Integer[1]
	// CTOR String::lastIndexOf(String[1]) : Integer[1]
	// CTOR String::matches(String[1]) : Boolean[1]
	// CTOR String::replaceAll(String[1],String[1]) : String[1]
	// CTOR String::replaceFirst(String[1],String[1]) : String[1]
	// CTOR String::size() : Integer[1]
	// CTOR String::startsWith(String[1]) : Boolean[1]
	// CTOR String::substituteAll(String[1],String[1]) : String[1]
	// CTOR String::substituteFirst(String[1],String[1]) : String[1]
	// CTOR String::substring(Integer[1],Integer[1]) : String[1]
	// CTOR String::toBoolean() : Boolean[?]
	// CTOR String::toInteger() : Integer[?]
	// CTOR String::toLower() : String[1]
	// CTOR String::toLowerCase() : String[1]
	// CTOR String::toReal() : Real[?]
	// CTOR String::toString() : String[1]
	// CTOR String::toUpper() : String[1]
	// CTOR String::toUpperCase() : String[1]
	// CTOR String::tokenize() : Sequence(String)
	// CTOR String::tokenize(String[1]) : Sequence(String)
	// CTOR String::tokenize(String[1],Boolean[1]) : Sequence(String)
	// CTOR String::trim() : String[1]
	// CTOR UnlimitedNatural::max(OclSelf[1]) : UnlimitedNatural[1]
	// CTOR UnlimitedNatural::min(OclSelf[1]) : UnlimitedNatural[1]
	// CTOR UnlimitedNatural::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// CTOR UnlimitedNatural::toInteger() : Integer[?]
	// CTOR Bag(T)::<>(OclSelf[?]) : Boolean[1]
	// CTOR Bag(T)::=(OclSelf[?]) : Boolean[1]
	// CTOR Bag(T)::excluding(Bag.T[?]) : Bag(Bag.T)
	// CTOR Bag(T)::excludingAll(Collection(Bag.T)) : Bag(Bag.T)
	// CTOR Bag(T)::flatten(T2)() : Bag(flatten.T2)
	// CTOR Bag(T)::including(Bag.T[?]) : Bag(Bag.T)
	// CTOR Bag(T)::includingAll(Collection(Bag.T)) : Bag(Bag.T)
	// CTOR Bag(T)::selectByKind(TT)(selectByKind.TT[1]) : Bag(selectByKind.TT)
	// CTOR Bag(T)::selectByType(TT)(selectByType.TT[1]) : Bag(selectByType.TT)
	// CTOR BooleanType::allInstances() : Set(OclSelf)
	// CTOR Class::allInstances() : Set(OclSelf)
	// CTOR Collection(T)::<>(OclSelf[?]) : Boolean[1]
	// CTOR Collection(T)::=(OclSelf[?]) : Boolean[1]
	// CTOR Collection(T)::asBag() : Bag(Collection.T)
	// CTOR Collection(T)::asOrderedSet() : OrderedSet(Collection.T)
	// CTOR Collection(T)::asSequence() : Sequence(Collection.T)
	// CTOR Collection(T)::asSet() : Set(Collection.T)
	// CTOR Collection(T)::count(Collection.T[?]) : Integer[1]
	// CTOR Collection(T)::excludes(Collection.T[?]) : Boolean[1]
	// CTOR Collection(T)::excludesAll(T2)(Collection(excludesAll.T2)) : Boolean[1]
	// CTOR Collection(T)::excluding(Collection.T[?]) : Collection(Collection.T)
	// CTOR Collection(T)::excludingAll(Collection(Collection.T)) : Collection(Collection.T)
	// CTOR Collection(T)::flatten(T2)() : Collection(flatten.T2)
	// CTOR Collection(T)::includes(Collection.T[?]) : Boolean[1]
	// CTOR Collection(T)::includesAll(T2)(Collection(includesAll.T2)) : Boolean[1]
	// CTOR Collection(T)::including(Collection.T[?]) : Collection(Collection.T)
	// CTOR Collection(T)::includingAll(Collection(Collection.T)) : Collection(Collection.T)
	// CTOR Collection(T)::intersection(Collection(Collection.T)) : Bag(Collection.T)
	// CTOR Collection(T)::intersection(UniqueCollection(Collection.T)) : Set(Collection.T)
	// CTOR Collection(T)::isEmpty() : Boolean[1]
	// CTOR Collection(T)::max() : Collection.T[1]
	// CTOR Collection(T)::min() : Collection.T[1]
	// CTOR Collection(T)::notEmpty() : Boolean[1]
	// CTOR Collection(T)::product(T2)(Collection(product.T2)) : Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// CTOR Collection(T)::selectByKind(TT)(selectByKind.TT[1]) : Collection(selectByKind.TT)
	// CTOR Collection(T)::selectByType(TT)(selectByType.TT[1]) : Collection(selectByType.TT)
	// CTOR Collection(T)::size() : Integer[1]
	// CTOR Collection(T)::sum() : Collection.T[1]
	// CTOR Collection(T)::union(Collection(Collection.T)) : Bag(Collection.T)
	// CTOR Enumeration::allInstances() : Set(OclSelf)
	// CTOR InvalidType::allInstances() : Set(OclSelf)
	// CTOR Map(K,V)::<>(OclSelf[?]) : Boolean[1]
	// CTOR Map(K,V)::=(OclSelf[?]) : Boolean[1]
	// CTOR Map(K,V)::at(Map.K[?]) : Map.V[?]
	// CTOR Map(K,V)::excludes(Map.K[?]) : Boolean[1]
	// CTOR Map(K,V)::excludes(Map.K[?],Map.V[?]) : Boolean[1]
	// CTOR Map(K,V)::excludesAll(K2)(Collection(excludesAll.K2)) : Boolean[1]
	// CTOR Map(K,V)::excludesMap(K2,V2)(Map(excludesMap.K2,excludesMap.V2)[1]) : Boolean[1]
	// CTOR Map(K,V)::excludesValue(Map.V[?]) : Boolean[1]
	// CTOR Map(K,V)::excluding(Map.K[?]) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::excluding(Map.K[?],Map.V[?]) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::excludingAll(Collection(Map.K)) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::excludingMap(K2,V2)(Map(excludingMap.K2,excludingMap.V2)[1]) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::includes(Map.K[?]) : Boolean[1]
	// CTOR Map(K,V)::includes(Map.K[?],Map.V[?]) : Boolean[1]
	// CTOR Map(K,V)::includesAll(K2)(Collection(includesAll.K2)) : Boolean[1]
	// CTOR Map(K,V)::includesMap(K2,V2)(Map(includesMap.K2,includesMap.V2)[1]) : Boolean[1]
	// CTOR Map(K,V)::includesValue(Map.V[?]) : Boolean[1]
	// CTOR Map(K,V)::including(Map.K[?],Map.V[?]) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::includingMap(K2,V2)(Map(includingMap.K2,includingMap.V2)[1]) : Map(Map.K,Map.V)[1]
	// CTOR Map(K,V)::isEmpty() : Boolean[1]
	// CTOR Map(K,V)::keys() : Set(Map.K)
	// CTOR Map(K,V)::notEmpty() : Boolean[1]
	// CTOR Map(K,V)::size() : Integer[1]
	// CTOR Map(K,V)::values() : Bag(Map.V)
	// CTOR OclAny::<>(OclSelf[?]) : Boolean[1]
	// CTOR OclAny::=(OclSelf[?]) : Boolean[1]
	// CTOR OclAny::oclAsSet() : Set(OclSelf)
	// CTOR OclAny::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// CTOR OclAny::oclIsInState(OclState[?]) : Boolean[1]
	// CTOR OclAny::oclIsInvalid() : Boolean[1]
	// CTOR OclAny::oclIsKindOf(OclType[1]) : Boolean[1]
	// CTOR OclAny::oclIsNew() : Boolean[1]
	// CTOR OclAny::oclIsTypeOf(OclType[1]) : Boolean[1]
	// CTOR OclAny::oclIsUndefined() : Boolean[1]
	// CTOR OclAny::oclLog() : OclSelf[1]
	// CTOR OclAny::oclLog(String[1]) : OclSelf[1]
	// CTOR OclAny::oclType() : OclSelf[1]
	// CTOR OclAny::oclTypes() : Set(OclSelf)
	// CTOR OclAny::toString() : String[1]
	// CTOR OclComparable::<(OclSelf[1]) : Boolean[1]
	// CTOR OclComparable::<=(OclSelf[1]) : Boolean[1]
	// CTOR OclComparable::>(OclSelf[1]) : Boolean[1]
	// CTOR OclComparable::>=(OclSelf[1]) : Boolean[1]
	// CTOR OclComparable::compareTo(OclSelf[1]) : Integer[1]
	// CTOR OclElement::allInstances(Integer[1]) : Set(OclSelf)
	// CTOR OclElement::oclAsModelType(TT)(oclAsModelType.TT[1]) : oclAsModelType.TT[1]
	// CTOR OclElement::oclBase() : OclType[?]
	// CTOR OclElement::oclBase(OclType[1]) : OclType[?]
	// CTOR OclElement::oclContainer() : OclElement[?]
	// CTOR OclElement::oclContents() : Set(OclElement)
	// CTOR OclElement::oclExtension(OclStereotype[1]) : OclElement[?]
	// CTOR OclElement::oclExtensions() : Set(OclElement)
	// CTOR OclElement::oclExtensions(OclStereotype[1]) : Set(OclElement)
	// CTOR OclElement::oclIsModelKindOf(OclType[1]) : Boolean[1]
	// CTOR OclElement::oclModelType() : OclSelf[1]
	// CTOR OclElement::oclModelTypes() : Set(OclSelf)
	// CTOR OclEnumeration::allInstances(Integer[1]) : Set(OclSelf)
	// CTOR OclInvalid::<>(OclSelf[?]) : Boolean[1]
	// CTOR OclInvalid::=(OclSelf[?]) : Boolean[1]
	// CTOR OclInvalid::allInstances(Integer[1]) : Set(OclSelf)
	// CTOR OclInvalid::and(Boolean[?]) : Boolean[?]
	// CTOR OclInvalid::implies(Boolean[?]) : Boolean[?]
	// CTOR OclInvalid::oclAsSet() : Set(OclSelf)
	// CTOR OclInvalid::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// CTOR OclInvalid::oclBadOperation() : OclAny[?]
	// CTOR OclInvalid::oclIsInvalid() : Boolean[1]
	// CTOR OclInvalid::oclIsKindOf(OclType[1]) : Boolean[1]
	// CTOR OclInvalid::oclIsTypeOf(OclType[1]) : Boolean[1]
	// CTOR OclInvalid::oclIsUndefined() : Boolean[1]
	// CTOR OclInvalid::oclType() : OclSelf[1]
	// CTOR OclInvalid::or(Boolean[?]) : Boolean[?]
	// CTOR OclInvalid::toString() : String[1]
	// CTOR OclMessage::hasReturned() : Boolean[1]
	// CTOR OclMessage::isOperationCall() : Boolean[1]
	// CTOR OclMessage::isSignalSent() : Boolean[1]
	// CTOR OclMessage::result() : OclAny[?]
	// CTOR OclStereotype::allInstances(Integer[1]) : Set(OclSelf)
	// CTOR OclSummable::sum(OclSelf[1]) : OclSelf[1]
	// CTOR OclSummable::zero() : OclSelf[1]
	// CTOR OclTuple::<>(OclSelf[?]) : Boolean[1]
	// CTOR OclTuple::=(OclSelf[?]) : Boolean[1]
	// CTOR OclType::conformsTo(OclType[?]) : Boolean[1]
	// CTOR OclVoid::+(String[?]) : String[1]
	// CTOR OclVoid::<>(OclSelf[?]) : Boolean[1]
	// CTOR OclVoid::=(OclSelf[?]) : Boolean[1]
	// CTOR OclVoid::allInstances(Integer[1]) : Set(OclSelf[*|?])
	// CTOR OclVoid::and(Boolean[?]) : Boolean[?]
	// CTOR OclVoid::concat(String[?]) : String[1]
	// CTOR OclVoid::implies(Boolean[?]) : Boolean[?]
	// CTOR OclVoid::not() : Boolean[?]
	// CTOR OclVoid::oclAsSet() : Set(OclSelf)
	// CTOR OclVoid::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[?]
	// CTOR OclVoid::oclIsInvalid() : Boolean[1]
	// CTOR OclVoid::oclIsKindOf(OclType[1]) : Boolean[1]
	// CTOR OclVoid::oclIsTypeOf(OclType[1]) : Boolean[1]
	// CTOR OclVoid::oclIsUndefined() : Boolean[1]
	// CTOR OclVoid::oclType() : OclSelf[1]
	// CTOR OclVoid::oclTypes() : Set(OclSelf)
	// CTOR OclVoid::or(Boolean[?]) : Boolean[?]
	// CTOR OclVoid::toString() : String[1]
	// CTOR OclVoid::xor(Boolean[?]) : Boolean[?]
	// CTOR OrderedCollection(T)::at(Integer[1]) : OrderedCollection.T[?]
	// CTOR OrderedCollection(T)::first() : OrderedCollection.T[?]
	// CTOR OrderedCollection(T)::indexOf(OrderedCollection.T[?]) : Integer[?]
	// CTOR OrderedCollection(T)::last() : OrderedCollection.T[?]
	// CTOR OrderedSet(T)::-(UniqueCollection(OclAny)) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::<>(OclSelf[?]) : Boolean[1]
	// CTOR OrderedSet(T)::=(OclSelf[?]) : Boolean[1]
	// CTOR OrderedSet(T)::append(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::appendAll(OrderedCollection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::excluding(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::excludingAll(Collection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::flatten(T2)() : OrderedSet(flatten.T2)
	// CTOR OrderedSet(T)::including(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::includingAll(Collection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::insertAt(Integer[1],OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::prepend(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::prependAll(OrderedCollection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::reverse() : OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(T)::selectByKind(TT)(selectByKind.TT[1]) : OrderedSet(selectByKind.TT)
	// CTOR OrderedSet(T)::selectByType(TT)(selectByType.TT[1]) : OrderedSet(selectByType.TT)
	// CTOR OrderedSet(T)::subOrderedSet(Integer[1],Integer[1]) : OrderedSet(OrderedSet.T)
	// CTOR Sequence(T)::<>(OclSelf[?]) : Boolean[1]
	// CTOR Sequence(T)::=(OclSelf[?]) : Boolean[1]
	// CTOR Sequence(T)::append(Sequence.T[?]) : Sequence(Sequence.T)
	// CTOR Sequence(T)::appendAll(OrderedCollection(Sequence.T)) : Sequence(Sequence.T)
	// CTOR Sequence(T)::excluding(Sequence.T[?]) : Sequence(Sequence.T)
	// CTOR Sequence(T)::excludingAll(Collection(Sequence.T)) : Sequence(Sequence.T)
	// CTOR Sequence(T)::flatten(T2)() : Sequence(flatten.T2)
	// CTOR Sequence(T)::including(Sequence.T[?]) : Sequence(Sequence.T)
	// CTOR Sequence(T)::includingAll(Collection(Sequence.T)) : Sequence(Sequence.T)
	// CTOR Sequence(T)::insertAt(Integer[1],Sequence.T[?]) : Sequence(Sequence.T)
	// CTOR Sequence(T)::prepend(Sequence.T[?]) : Sequence(Sequence.T)
	// CTOR Sequence(T)::prependAll(OrderedCollection(Sequence.T)) : Sequence(Sequence.T)
	// CTOR Sequence(T)::reverse() : Sequence(Sequence.T)
	// CTOR Sequence(T)::selectByKind(TT)(selectByKind.TT[1]) : Sequence(selectByKind.TT)
	// CTOR Sequence(T)::selectByType(TT)(selectByType.TT[1]) : Sequence(selectByType.TT)
	// CTOR Sequence(T)::subSequence(Integer[1],Integer[1]) : Sequence(Sequence.T)
	// CTOR Set(T)::-(UniqueCollection(OclAny)) : Set(Set.T)
	// CTOR Set(T)::<>(OclSelf[?]) : Boolean[1]
	// CTOR Set(T)::=(OclSelf[?]) : Boolean[1]
	// CTOR Set(T)::excluding(Set.T[?]) : Set(Set.T)
	// CTOR Set(T)::excludingAll(Collection(Set.T)) : Set(Set.T)
	// CTOR Set(T)::flatten(T2)() : Set(flatten.T2)
	// CTOR Set(T)::including(Set.T[?]) : Set(Set.T)
	// CTOR Set(T)::includingAll(Collection(Set.T)) : Set(Set.T)
	// CTOR Set(T)::selectByKind(TT)(selectByKind.TT[1]) : Set(selectByKind.TT)
	// CTOR Set(T)::selectByType(TT)(selectByType.TT[1]) : Set(selectByType.TT)
	// CTOR Stereotype::allInstances() : Set(OclSelf)
	// CTOR Type::conformsTo(Type[?]) : Boolean[1]
	// CTOR UniqueCollection(T)::-(UniqueCollection(OclAny)) : UniqueCollection(UniqueCollection.T)
	// CTOR UniqueCollection(T)::intersection(Collection(UniqueCollection.T)) : Set(UniqueCollection.T)
	// CTOR UniqueCollection(T)::symmetricDifference(UniqueCollection(OclAny)) : Set(UniqueCollection.T)
	// CTOR UniqueCollection(T)::union(UniqueCollection(UniqueCollection.T)) : Set(UniqueCollection.T)
	// CTOR VoidType::allInstances() : Set(OclSelf[*|?])
	// OPERATIONS CollectionType
	// OPERATIONS EnumerationLiteral
	// OPERATIONS MapType
	// OPERATIONS OclLambda
	// OPERATIONS OclState
	// OPERATIONS State
	// OPERATIONS OclSelf
	// PROPERTIES Boolean
	// PROPERTIES Integer
	// PROPERTIES Real
	// PROPERTIES String
	// PROPERTIES UnlimitedNatural
	// PROPERTIES BooleanType
	// PROPERTIES Class
	// PROPERTIES Enumeration
	// PROPERTIES EnumerationLiteral
	// PROPERTIES InvalidType
	// PROPERTIES OclComparable
	// PROPERTIES OclEnumeration
	// PROPERTIES OclLambda
	// PROPERTIES OclMessage
	// PROPERTIES OclState
	// PROPERTIES OclStereotype
	// PROPERTIES OclSummable
	// PROPERTIES OclTuple
	// PROPERTIES OclType
	// PROPERTIES State
	// PROPERTIES Stereotype
	// PROPERTIES VoidType
	// PROPERTIES OclSelf
	// PROPERTIES OclVoid
	// PROPERTIES OrderedCollection(T)
	// PROPERTIES UniqueCollection(T)
	// PROPERTIES Bag(T)
	// PROPERTIES OrderedSet(T)
	// PROPERTIES Sequence(T)
	// PROPERTIES Set(T)
	// SUPER_CLASSES Boolean
	// SUPER_CLASSES Integer
	// SUPER_CLASSES Real
	// SUPER_CLASSES String
	// SUPER_CLASSES UnlimitedNatural
	// SUPER_CLASSES BooleanType
	// SUPER_CLASSES Class
	// SUPER_CLASSES CollectionType
	// SUPER_CLASSES Enumeration
	// SUPER_CLASSES EnumerationLiteral
	// SUPER_CLASSES InvalidType
	// SUPER_CLASSES MapType
	// SUPER_CLASSES OclComparable
	// SUPER_CLASSES OclElement
	// SUPER_CLASSES OclEnumeration
	// SUPER_CLASSES OclLambda
	// SUPER_CLASSES OclMessage
	// SUPER_CLASSES OclState
	// SUPER_CLASSES OclStereotype
	// SUPER_CLASSES OclSummable
	// SUPER_CLASSES OclTuple
	// SUPER_CLASSES OclType
	// SUPER_CLASSES State
	// SUPER_CLASSES Stereotype
	// SUPER_CLASSES Type
	// SUPER_CLASSES VoidType
	// SUPER_CLASSES OclInvalid
	// SUPER_CLASSES OclSelf
	// SUPER_CLASSES OclVoid
	// SUPER_CLASSES Collection(T)
	// SUPER_CLASSES Map(K,V)

	// CTOR oclAsType.TT
	// CTOR collectNested.V
	// CTOR collect.V
	// CTOR flatten.T2
	// CTOR selectByKind.TT
	// CTOR selectByType.TT
	// CTOR collectBy.V
	// CTOR collectNested.V
	// CTOR collect.V
	// CTOR excludesAll.T2
	// CTOR flatten.T2
	// CTOR includesAll.T2
	// CTOR iterate.Tacc
	// CTOR product.T2
	// CTOR selectByKind.TT
	// CTOR selectByType.TT
	// CTOR collectBy.V2
	// CTOR collectNested.V2
	// CTOR collect.V2
	// CTOR excludesAll.K2
	// CTOR excludesMap.K2
	// CTOR excludesMap.V2
	// CTOR excludingMap.K2
	// CTOR excludingMap.V2
	// CTOR includesAll.K2
	// CTOR includesMap.K2
	// CTOR includesMap.V2
	// CTOR includingMap.K2
	// CTOR includingMap.V2
	// CTOR iterate.Tacc
	// CTOR oclAsType.TT
	// CTOR oclAsModelType.TT
	// CTOR oclAsType.TT
	// CTOR oclAsType.TT
	// CTOR collectNested.V
	// CTOR collect.V
	// CTOR flatten.T2
	// CTOR selectByKind.TT
	// CTOR selectByType.TT
	// CTOR collectNested.V
	// CTOR collect.V
	// CTOR flatten.T2
	// CTOR selectByKind.TT
	// CTOR selectByType.TT
	// CTOR collectNested.V
	// CTOR collect.V
	// CTOR flatten.T2
	// CTOR selectByKind.TT
	// CTOR selectByType.TT
	// CTOR Collection(Integer)
	// CTOR Collection(String)
	// CTOR Collection(Bag.T)
	// CTOR Collection(CollectionType)
	// CTOR Collection(Collection.T)
	// CTOR Collection(MapType)
	// CTOR Collection(Map.K)
	// CTOR Collection(Map.V)
	// CTOR Collection(OclAny)
	// CTOR Collection(OclElement)
	// CTOR Collection(OclInvalid)
	// CTOR Collection(OclSelf)
	// CTOR Collection(OrderedCollection.T)
	// CTOR Collection(OrderedSet.T)
	// CTOR Collection(Sequence.T)
	// CTOR Collection(Set.T)
	// CTOR Collection(UniqueCollection.T)
	// CTOR Map(Map.K,Map.V)
	// CTOR Map(Map.K[?],Map.V[?])
	// CTOR Lambda T() : Boolean
	// CTOR Lambda T() : OclAny
	// CTOR Lambda T() : Boolean
	// CTOR Lambda T() : OclAny
	// CTOR Lambda K() : Boolean
	// CTOR Lambda K() : OclAny
	// CTOR Lambda T() : Boolean
	// CTOR Lambda T() : OclAny
	// CTOR Lambda T() : Boolean
	// CTOR Lambda T() : OclAny
	// CTOR Lambda T() : Boolean
	// CTOR Lambda T() : OclAny
	// CTOR Lambda T() : OclAny
	// OPERATIONS Boolean
	// OPERATIONS Integer
	// OPERATIONS Real
	// OPERATIONS String
	// OPERATIONS UnlimitedNatural
	// OPERATIONS OclAny
	// OPERATIONS BooleanType
	// OPERATIONS Class
	// OPERATIONS Enumeration
	// OPERATIONS InvalidType
	// OPERATIONS OclComparable
	// OPERATIONS OclElement
	// OPERATIONS OclEnumeration
	// OPERATIONS OclMessage
	// OPERATIONS OclStereotype
	// OPERATIONS OclSummable
	// OPERATIONS OclTuple
	// OPERATIONS OclType
	// OPERATIONS Stereotype
	// OPERATIONS Type
	// OPERATIONS VoidType
	// OPERATIONS OclInvalid
	// OPERATIONS OclVoid
	// OPERATIONS Collection(T)
	// OPERATIONS OrderedCollection(T)
	// OPERATIONS UniqueCollection(T)
	// OPERATIONS Map(K,V)
	// OPERATIONS Bag(T)
	// OPERATIONS OrderedSet(T)
	// OPERATIONS Sequence(T)
	// OPERATIONS Set(T)
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
		public static final @NonNull Object[] _0_K = new @NonNull Object[] {0};
		public static final @NonNull Object[] _0_K___1_V = new @NonNull Object[] {0, 1};
		public static final @NonNull Object[] _0_T = new @NonNull Object[] {0};
		public static final @NonNull Object[] _0_TT = new @NonNull Object[] {0};
		public static final @NonNull Object[] _1_TT = new @NonNull Object[] {1};
		public static final @NonNull Object[] _1_V = new @NonNull Object[] {1};
		public static final @NonNull Object[] _Boolean = new @NonNull Object[] {OCLstdlibTables.Types._Boolean};
		public static final @NonNull Object[] _Collection__0_K__ = new @NonNull Object[] {OCLstdlibTables.Types._Collection, 0};
		public static final @NonNull Object[] _Collection__0_T__ = new @NonNull Object[] {OCLstdlibTables.Types._Collection, 0};
		public static final @NonNull Object[] _Collection__1_T2__ = new @NonNull Object[] {OCLstdlibTables.Types._Collection, 1};
		public static final @NonNull Object[] _Collection__2_K2__ = new @NonNull Object[] {OCLstdlibTables.Types._Collection, 2};
		public static final @NonNull Object[] _Integer = new @NonNull Object[] {OCLstdlibTables.Types._Integer};
		public static final @NonNull Object[] _Integer___0_T = new @NonNull Object[] {OCLstdlibTables.Types._Integer, 0};
		public static final @NonNull Object[] _Integer___Integer = new @NonNull Object[] {OCLstdlibTables.Types._Integer, OCLstdlibTables.Types._Integer};
		public static final @NonNull Object[] _Lambda_0_K_2_Tacc = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_K_2_V2 = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_K_Boolean = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_K_OclAny = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_1_Tacc = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_1_V = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_Boolean = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_OclAny = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_OrderedSet__0_T__ = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Lambda_0_T_Set__0_T__ = new @NonNull Object[] {"Lambda", 0};
		public static final @NonNull Object[] _Map__2_K2_3_V2__ = new @NonNull Object[] {OCLstdlibTables.Types._Map, 2, 3};
		public static final @NonNull Object[] _OclSelf = new @NonNull Object[] {OCLstdlibTables.Types._OclSelf};
		public static final @NonNull Object[] _OclState = new @NonNull Object[] {OCLstdlibTables.Types._OclState};
		public static final @NonNull Object[] _OclStereotype = new @NonNull Object[] {OCLstdlibTables.Types._OclStereotype};
		public static final @NonNull Object[] _OclType = new @NonNull Object[] {OCLstdlibTables.Types._OclType};
		public static final @NonNull Object[] _OrderedCollection__0_T__ = new @NonNull Object[] {OCLstdlibTables.Types._OrderedCollection, 0};
		public static final @NonNull Object[] _String = new @NonNull Object[] {OCLstdlibTables.Types._String};
		public static final @NonNull Object[] _String___Boolean = new @NonNull Object[] {OCLstdlibTables.Types._String, OCLstdlibTables.Types._Boolean};
		public static final @NonNull Object[] _String___String = new @NonNull Object[] {OCLstdlibTables.Types._String, OCLstdlibTables.Types._String};
		public static final @NonNull Object[] _UniqueCollection__0_T__ = new @NonNull Object[] {OCLstdlibTables.Types._UniqueCollection, 0};
		public static final @NonNull Object[] _UniqueCollection__OclAny__ = new @NonNull Object[] {OCLstdlibTables.Types._UniqueCollection, OCLstdlibTables.Types._OclAny};

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}
	// PROPERTIES OclAny
	// PROPERTIES Type
	// PROPERTIES Collection(T)
	// PROPERTIES Map(K,V)
	// TYPE Boolean::<>(OclSelf[?]) : Boolean[1]
	// TYPE Boolean::=(OclSelf[?]) : Boolean[1]
	// TYPE Boolean::and(Boolean[?]) : Boolean[?]
	// TYPE Boolean::and2(Boolean[1]) : Boolean[1]
	// TYPE Boolean::implies(Boolean[?]) : Boolean[?]
	// TYPE Boolean::implies2(Boolean[1]) : Boolean[1]
	// TYPE Boolean::not() : Boolean[?]
	// TYPE Boolean::not2() : Boolean[1]
	// TYPE Boolean::or(Boolean[?]) : Boolean[?]
	// TYPE Boolean::or2(Boolean[1]) : Boolean[1]
	// TYPE Boolean::toString() : String[1]
	// TYPE Boolean::xor(Boolean[?]) : Boolean[?]
	// TYPE Boolean::xor2(Boolean[1]) : Boolean[1]
	// TYPE Integer::*(OclSelf[1]) : Integer[1]
	// TYPE Integer::+(OclSelf[1]) : Integer[1]
	// TYPE Integer::-() : Integer[1]
	// TYPE Integer::-(OclSelf[1]) : Integer[1]
	// TYPE Integer::/(OclSelf[1]) : Real[1]
	// TYPE Integer::abs() : Integer[1]
	// TYPE Integer::div(Integer[1]) : Integer[1]
	// TYPE Integer::max(OclSelf[1]) : Integer[1]
	// TYPE Integer::min(OclSelf[1]) : Integer[1]
	// TYPE Integer::mod(Integer[1]) : Integer[1]
	// TYPE Integer::toString() : String[1]
	// TYPE Integer::toUnlimitedNatural() : UnlimitedNatural[1]
	// TYPE Real::*(OclSelf[1]) : Real[1]
	// TYPE Real::+(OclSelf[1]) : Real[1]
	// TYPE Real::-() : Real[1]
	// TYPE Real::-(OclSelf[1]) : Real[1]
	// TYPE Real::/(OclSelf[1]) : Real[1]
	// TYPE Real::<>(OclSelf[?]) : Boolean[1]
	// TYPE Real::=(OclSelf[?]) : Boolean[1]
	// TYPE Real::abs() : Real[1]
	// TYPE Real::floor() : Integer[1]
	// TYPE Real::max(OclSelf[1]) : Real[1]
	// TYPE Real::min(OclSelf[1]) : Real[1]
	// TYPE Real::round() : Integer[1]
	// TYPE Real::toString() : String[1]
	// TYPE String::+(String[?]) : String[1]
	// TYPE String::<(OclSelf[1]) : Boolean[1]
	// TYPE String::<=(OclSelf[1]) : Boolean[1]
	// TYPE String::<>(OclSelf[?]) : Boolean[1]
	// TYPE String::=(OclSelf[?]) : Boolean[1]
	// TYPE String::>(OclSelf[1]) : Boolean[1]
	// TYPE String::>=(OclSelf[1]) : Boolean[1]
	// TYPE String::at(Integer[1]) : String[1]
	// TYPE String::compareTo(OclSelf[1]) : Integer[1]
	// TYPE String::concat(String[?]) : String[1]
	// TYPE String::endsWith(String[1]) : Boolean[1]
	// TYPE String::equalsIgnoreCase(String[1]) : Boolean[1]
	// TYPE String::indexOf(String[1]) : Integer[1]
	// TYPE String::lastIndexOf(String[1]) : Integer[1]
	// TYPE String::matches(String[1]) : Boolean[1]
	// TYPE String::replaceAll(String[1],String[1]) : String[1]
	// TYPE String::replaceFirst(String[1],String[1]) : String[1]
	// TYPE String::size() : Integer[1]
	// TYPE String::startsWith(String[1]) : Boolean[1]
	// TYPE String::substituteAll(String[1],String[1]) : String[1]
	// TYPE String::substituteFirst(String[1],String[1]) : String[1]
	// TYPE String::substring(Integer[1],Integer[1]) : String[1]
	// TYPE String::toBoolean() : Boolean[?]
	// TYPE String::toInteger() : Integer[?]
	// TYPE String::toLower() : String[1]
	// TYPE String::toLowerCase() : String[1]
	// TYPE String::toReal() : Real[?]
	// TYPE String::toString() : String[1]
	// TYPE String::toUpper() : String[1]
	// TYPE String::toUpperCase() : String[1]
	// TYPE String::trim() : String[1]
	// TYPE UnlimitedNatural::max(OclSelf[1]) : UnlimitedNatural[1]
	// TYPE UnlimitedNatural::min(OclSelf[1]) : UnlimitedNatural[1]
	// TYPE UnlimitedNatural::toInteger() : Integer[?]
	// TYPE Bag(T)::<>(OclSelf[?]) : Boolean[1]
	// TYPE Bag(T)::=(OclSelf[?]) : Boolean[1]
	// TYPE Collection(T)::<>(OclSelf[?]) : Boolean[1]
	// TYPE Collection(T)::=(OclSelf[?]) : Boolean[1]
	// TYPE Collection(T)::count(Collection.T[?]) : Integer[1]
	// TYPE Collection(T)::excludes(Collection.T[?]) : Boolean[1]
	// TYPE Collection(T)::includes(Collection.T[?]) : Boolean[1]
	// TYPE Collection(T)::isEmpty() : Boolean[1]
	// TYPE Collection(T)::max() : Collection.T[1]
	// TYPE Collection(T)::min() : Collection.T[1]
	// TYPE Collection(T)::notEmpty() : Boolean[1]
	// TYPE Collection(T)::size() : Integer[1]
	// TYPE Collection(T)::sum() : Collection.T[1]
	// TYPE Map(K,V)::<>(OclSelf[?]) : Boolean[1]
	// TYPE Map(K,V)::=(OclSelf[?]) : Boolean[1]
	// TYPE Map(K,V)::at(Map.K[?]) : Map.V[?]
	// TYPE Map(K,V)::excludes(Map.K[?]) : Boolean[1]
	// TYPE Map(K,V)::excludes(Map.K[?],Map.V[?]) : Boolean[1]
	// TYPE Map(K,V)::excludesValue(Map.V[?]) : Boolean[1]
	// TYPE Map(K,V)::includes(Map.K[?]) : Boolean[1]
	// TYPE Map(K,V)::includes(Map.K[?],Map.V[?]) : Boolean[1]
	// TYPE Map(K,V)::includesValue(Map.V[?]) : Boolean[1]
	// TYPE Map(K,V)::isEmpty() : Boolean[1]
	// TYPE Map(K,V)::notEmpty() : Boolean[1]
	// TYPE Map(K,V)::size() : Integer[1]
	// TYPE OclAny::<>(OclSelf[?]) : Boolean[1]
	// TYPE OclAny::=(OclSelf[?]) : Boolean[1]
	// TYPE OclAny::oclIsInState(OclState[?]) : Boolean[1]
	// TYPE OclAny::oclIsInvalid() : Boolean[1]
	// TYPE OclAny::oclIsKindOf(OclType[1]) : Boolean[1]
	// TYPE OclAny::oclIsNew() : Boolean[1]
	// TYPE OclAny::oclIsTypeOf(OclType[1]) : Boolean[1]
	// TYPE OclAny::oclIsUndefined() : Boolean[1]
	// TYPE OclAny::oclLog() : OclSelf[1]
	// TYPE OclAny::oclLog(String[1]) : OclSelf[1]
	// TYPE OclAny::oclType() : OclSelf[1]
	// TYPE OclAny::toString() : String[1]
	// TYPE OclComparable::<(OclSelf[1]) : Boolean[1]
	// TYPE OclComparable::<=(OclSelf[1]) : Boolean[1]
	// TYPE OclComparable::>(OclSelf[1]) : Boolean[1]
	// TYPE OclComparable::>=(OclSelf[1]) : Boolean[1]
	// TYPE OclComparable::compareTo(OclSelf[1]) : Integer[1]
	// TYPE OclElement::oclBase() : OclType[?]
	// TYPE OclElement::oclBase(OclType[1]) : OclType[?]
	// TYPE OclElement::oclContainer() : OclElement[?]
	// TYPE OclElement::oclExtension(OclStereotype[1]) : OclElement[?]
	// TYPE OclElement::oclIsModelKindOf(OclType[1]) : Boolean[1]
	// TYPE OclElement::oclModelType() : OclSelf[1]
	// TYPE OclInvalid::<>(OclSelf[?]) : Boolean[1]
	// TYPE OclInvalid::=(OclSelf[?]) : Boolean[1]
	// TYPE OclInvalid::and(Boolean[?]) : Boolean[?]
	// TYPE OclInvalid::implies(Boolean[?]) : Boolean[?]
	// TYPE OclInvalid::oclBadOperation() : OclAny[?]
	// TYPE OclInvalid::oclIsInvalid() : Boolean[1]
	// TYPE OclInvalid::oclIsKindOf(OclType[1]) : Boolean[1]
	// TYPE OclInvalid::oclIsTypeOf(OclType[1]) : Boolean[1]
	// TYPE OclInvalid::oclIsUndefined() : Boolean[1]
	// TYPE OclInvalid::oclType() : OclSelf[1]
	// TYPE OclInvalid::or(Boolean[?]) : Boolean[?]
	// TYPE OclInvalid::toString() : String[1]
	// TYPE OclMessage::hasReturned() : Boolean[1]
	// TYPE OclMessage::isOperationCall() : Boolean[1]
	// TYPE OclMessage::isSignalSent() : Boolean[1]
	// TYPE OclMessage::result() : OclAny[?]
	// TYPE OclSummable::sum(OclSelf[1]) : OclSelf[1]
	// TYPE OclSummable::zero() : OclSelf[1]
	// TYPE OclTuple::<>(OclSelf[?]) : Boolean[1]
	// TYPE OclTuple::=(OclSelf[?]) : Boolean[1]
	// TYPE OclType::conformsTo(OclType[?]) : Boolean[1]
	// TYPE OclVoid::+(String[?]) : String[1]
	// TYPE OclVoid::<>(OclSelf[?]) : Boolean[1]
	// TYPE OclVoid::=(OclSelf[?]) : Boolean[1]
	// TYPE OclVoid::and(Boolean[?]) : Boolean[?]
	// TYPE OclVoid::concat(String[?]) : String[1]
	// TYPE OclVoid::implies(Boolean[?]) : Boolean[?]
	// TYPE OclVoid::not() : Boolean[?]
	// TYPE OclVoid::oclIsInvalid() : Boolean[1]
	// TYPE OclVoid::oclIsKindOf(OclType[1]) : Boolean[1]
	// TYPE OclVoid::oclIsTypeOf(OclType[1]) : Boolean[1]
	// TYPE OclVoid::oclIsUndefined() : Boolean[1]
	// TYPE OclVoid::oclType() : OclSelf[1]
	// TYPE OclVoid::or(Boolean[?]) : Boolean[?]
	// TYPE OclVoid::toString() : String[1]
	// TYPE OclVoid::xor(Boolean[?]) : Boolean[?]
	// TYPE OrderedCollection(T)::at(Integer[1]) : OrderedCollection.T[?]
	// TYPE OrderedCollection(T)::first() : OrderedCollection.T[?]
	// TYPE OrderedCollection(T)::indexOf(OrderedCollection.T[?]) : Integer[?]
	// TYPE OrderedCollection(T)::last() : OrderedCollection.T[?]
	// TYPE OrderedSet(T)::<>(OclSelf[?]) : Boolean[1]
	// TYPE OrderedSet(T)::=(OclSelf[?]) : Boolean[1]
	// TYPE Sequence(T)::<>(OclSelf[?]) : Boolean[1]
	// TYPE Sequence(T)::=(OclSelf[?]) : Boolean[1]
	// TYPE Set(T)::<>(OclSelf[?]) : Boolean[1]
	// TYPE Set(T)::=(OclSelf[?]) : Boolean[1]
	// TYPE Type::conformsTo(Type[?]) : Boolean[1]

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
		public static final @NonNull Operation _Bag__closure = LIBRARY.createOperation("closure", Parameters._Lambda_0_T_Set__0_T__, Types._Bag,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Bag__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_T_1_V, Types._Bag,
			3, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_T_1_V, Types._Bag,
			4, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Bag__excluding = LIBRARY.createOperation("excluding", Parameters._0_T, Types._Bag,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Bag__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_T__, Types._Bag,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Bag__flatten = LIBRARY.createOperation("flatten", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Bag,
			7, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Bag__including = LIBRARY.createOperation("including", Parameters._0_T, Types._Bag,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Bag__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__0_T__, Types._Bag,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Bag__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_T_Boolean, Types._Bag,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__select = LIBRARY.createOperation("select", Parameters._Lambda_0_T_Boolean, Types._Bag,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Bag__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._1_TT, Types._Bag,
			12, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Bag__selectByType = LIBRARY.createOperation("selectByType", Parameters._1_TT, Types._Bag,
			13, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Bag__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._Bag,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);

		public static final @NonNull Operation _Boolean___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Boolean,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Boolean___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Boolean,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__and = LIBRARY.createOperation("and", Parameters._Boolean, Types._Boolean,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__implies = LIBRARY.createOperation("implies", Parameters._Boolean, Types._Boolean,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__not = LIBRARY.createOperation("not", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Boolean,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__or = LIBRARY.createOperation("or", Parameters._Boolean, Types._Boolean,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanOrOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__xor = LIBRARY.createOperation("xor", Parameters._Boolean, Types._Boolean,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._Boolean,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__and2 = LIBRARY.createOperation("and2", Parameters._Boolean, Types._Boolean,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__implies2 = LIBRARY.createOperation("implies2", Parameters._Boolean, Types._Boolean,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__not2 = LIBRARY.createOperation("not2", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Boolean,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__or2 = LIBRARY.createOperation("or2", Parameters._Boolean, Types._Boolean,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2.INSTANCE);
		public static final @NonNull Operation _Boolean__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Boolean,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _Boolean__xor2 = LIBRARY.createOperation("xor2", Parameters._Boolean, Types._Boolean,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2.INSTANCE);

		public static final @NonNull Operation _Collection___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Collection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Collection___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Collection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Collection__any = LIBRARY.createOperation("any", Parameters._Lambda_0_T_Boolean, Types._Collection,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
		public static final @NonNull Operation _Collection__asBag = LIBRARY.createOperation("asBag", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asOrderedSet = LIBRARY.createOperation("asOrderedSet", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asSequence = LIBRARY.createOperation("asSequence", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation.INSTANCE);
		public static final @NonNull Operation _Collection__asSet = LIBRARY.createOperation("asSet", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation.INSTANCE);
		public static final @NonNull Operation _Collection__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_T_1_V, Types._Collection,
			7, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__collectBy = LIBRARY.createOperation("collectBy", Parameters._Lambda_0_T_1_V, Types._Collection,
			8, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
		public static final @NonNull Operation _Collection__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_T_1_V, Types._Collection,
			9, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Collection__count = LIBRARY.createOperation("count", Parameters._0_T, Types._Collection,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionCountOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludes = LIBRARY.createOperation("excludes", Parameters._0_T, Types._Collection,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludesAll = LIBRARY.createOperation("excludesAll", Parameters._Collection__1_T2__, Types._Collection,
			12, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excluding = LIBRARY.createOperation("excluding", Parameters._0_T, Types._Collection,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Collection__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_T__, Types._Collection,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__2_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_T_Boolean, Types._Collection,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__1_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_T_Boolean, Types._Collection,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__0_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_T_Boolean, Types._Collection,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Collection__flatten = LIBRARY.createOperation("flatten", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			18, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Collection__2_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_T_Boolean, Types._Collection,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__1_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_T_Boolean, Types._Collection,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__0_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_T_Boolean, Types._Collection,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Collection__includes = LIBRARY.createOperation("includes", Parameters._0_T, Types._Collection,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation.INSTANCE);
		public static final @NonNull Operation _Collection__includesAll = LIBRARY.createOperation("includesAll", Parameters._Collection__1_T2__, Types._Collection,
			23, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__including = LIBRARY.createOperation("including", Parameters._0_T, Types._Collection,
			24, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Collection__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__0_T__, Types._Collection,
			25, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Collection__0_intersection = LIBRARY.createOperation("intersection", Parameters._Collection__0_T__, Types._Collection,
			26, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _Collection__1_intersection = LIBRARY.createOperation("intersection", Parameters._UniqueCollection__0_T__, Types._Collection,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _Collection__isEmpty = LIBRARY.createOperation("isEmpty", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			28, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Collection__isUnique = LIBRARY.createOperation("isUnique", Parameters._Lambda_0_T_OclAny, Types._Collection,
			29, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
		public static final @NonNull Operation _Collection__iterate = LIBRARY.createOperation("iterate", Parameters._Lambda_0_T_1_Tacc, Types._Collection,
			30, TypeUtil.createTemplateParameters(TypeParameters._1_Tacc), org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
		public static final @NonNull Operation _Collection__max = LIBRARY.createOperation("max", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			31, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation.INSTANCE);
		public static final @NonNull Operation _Collection__min = LIBRARY.createOperation("min", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			32, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionMinOperation.INSTANCE);
		public static final @NonNull Operation _Collection__notEmpty = LIBRARY.createOperation("notEmpty", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Collection__one = LIBRARY.createOperation("one", Parameters._Lambda_0_T_Boolean, Types._Collection,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
		public static final @NonNull Operation _Collection__product = LIBRARY.createOperation("product", Parameters._Collection__1_T2__, Types._Collection,
			35, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionProductOperation.INSTANCE);
		public static final @NonNull Operation _Collection__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_T_Boolean, Types._Collection,
			36, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__select = LIBRARY.createOperation("select", Parameters._Lambda_0_T_Boolean, Types._Collection,
			37, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Collection__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._1_TT, Types._Collection,
			38, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Collection__selectByType = LIBRARY.createOperation("selectByType", Parameters._1_TT, Types._Collection,
			39, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Collection__size = LIBRARY.createOperation("size", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			40, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation.INSTANCE);
		public static final @NonNull Operation _Collection__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._Collection,
			41, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _Collection__sum = LIBRARY.createOperation("sum", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Collection,
			42, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionSumOperation.INSTANCE);
		public static final @NonNull Operation _Collection__union = LIBRARY.createOperation("union", Parameters._Collection__0_T__, Types._Collection,
			43, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);

		public static final @NonNull Operation _Integer___mul_ = LIBRARY.createOperation("*", Parameters._OclSelf, Types._Integer,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
		public static final @NonNull Operation _Integer___add_ = LIBRARY.createOperation("+", Parameters._OclSelf, Types._Integer,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
		public static final @NonNull Operation _Integer___neg_ = LIBRARY.createOperation("-", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Integer,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
		public static final @NonNull Operation _Integer___sub_ = LIBRARY.createOperation("-", Parameters._OclSelf, Types._Integer,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
		public static final @NonNull Operation _Integer___div_ = LIBRARY.createOperation("/", Parameters._OclSelf, Types._Integer,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
		public static final @NonNull Operation _Integer__abs = LIBRARY.createOperation("abs", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Integer,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
		public static final @NonNull Operation _Integer__div = LIBRARY.createOperation("div", Parameters._Integer, Types._Integer,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivOperation.INSTANCE);
		public static final @NonNull Operation _Integer__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._Integer,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
		public static final @NonNull Operation _Integer__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._Integer,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
		public static final @NonNull Operation _Integer__mod = LIBRARY.createOperation("mod", Parameters._Integer, Types._Integer,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericModOperation.INSTANCE);
		public static final @NonNull Operation _Integer__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Integer,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _Integer__toUnlimitedNatural = LIBRARY.createOperation("toUnlimitedNatural", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Integer,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation.INSTANCE);

		public static final @NonNull Operation _Map___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Map,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Map___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Map,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Map__any = LIBRARY.createOperation("any", Parameters._Lambda_0_K_Boolean, Types._Map,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
		public static final @NonNull Operation _Map__at = LIBRARY.createOperation("at", Parameters._0_K, Types._Map,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapAtOperation.INSTANCE);
		public static final @NonNull Operation _Map__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_K_2_V2, Types._Map,
			4, TypeUtil.createTemplateParameters(TypeParameters._2_V2), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Map__collectBy = LIBRARY.createOperation("collectBy", Parameters._Lambda_0_K_2_V2, Types._Map,
			5, TypeUtil.createTemplateParameters(TypeParameters._2_V2), org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
		public static final @NonNull Operation _Map__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_K_2_V2, Types._Map,
			6, TypeUtil.createTemplateParameters(TypeParameters._2_V2), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_excludes = LIBRARY.createOperation("excludes", Parameters._0_K, Types._Map,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_excludes = LIBRARY.createOperation("excludes", Parameters._0_K___1_V, Types._Map,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesAll = LIBRARY.createOperation("excludesAll", Parameters._Collection__2_K2__, Types._Map,
			9, TypeUtil.createTemplateParameters(TypeParameters._2_K2), org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesMap = LIBRARY.createOperation("excludesMap", Parameters._Map__2_K2_3_V2__, Types._Map,
			10, TypeUtil.createTemplateParameters(TypeParameters._2_K2, TypeParameters._3_V2), org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludesValue = LIBRARY.createOperation("excludesValue", Parameters._1_V, Types._Map,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation.INSTANCE);
		public static final @NonNull Operation _Map__0_excluding = LIBRARY.createOperation("excluding", Parameters._0_K, Types._Map,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_excluding = LIBRARY.createOperation("excluding", Parameters._0_K___1_V, Types._Map,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_K__, Types._Map,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__excludingMap = LIBRARY.createOperation("excludingMap", Parameters._Map__2_K2_3_V2__, Types._Map,
			15, TypeUtil.createTemplateParameters(TypeParameters._2_K2, TypeParameters._3_V2), org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__2_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_K_Boolean, Types._Map,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__1_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_K_Boolean, Types._Map,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_exists = LIBRARY.createOperation("exists", Parameters._Lambda_0_K_Boolean, Types._Map,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
		public static final @NonNull Operation _Map__2_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_K_Boolean, Types._Map,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__1_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_K_Boolean, Types._Map,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_forAll = LIBRARY.createOperation("forAll", Parameters._Lambda_0_K_Boolean, Types._Map,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
		public static final @NonNull Operation _Map__0_includes = LIBRARY.createOperation("includes", Parameters._0_K, Types._Map,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesOperation.INSTANCE);
		public static final @NonNull Operation _Map__1_includes = LIBRARY.createOperation("includes", Parameters._0_K___1_V, Types._Map,
			23, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesAll = LIBRARY.createOperation("includesAll", Parameters._Collection__2_K2__, Types._Map,
			24, TypeUtil.createTemplateParameters(TypeParameters._2_K2), org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesMap = LIBRARY.createOperation("includesMap", Parameters._Map__2_K2_3_V2__, Types._Map,
			25, TypeUtil.createTemplateParameters(TypeParameters._2_K2, TypeParameters._3_V2), org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__includesValue = LIBRARY.createOperation("includesValue", Parameters._1_V, Types._Map,
			26, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation.INSTANCE);
		public static final @NonNull Operation _Map__including = LIBRARY.createOperation("including", Parameters._0_K___1_V, Types._Map,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation.INSTANCE);
		public static final @NonNull Operation _Map__includingMap = LIBRARY.createOperation("includingMap", Parameters._Map__2_K2_3_V2__, Types._Map,
			28, TypeUtil.createTemplateParameters(TypeParameters._2_K2, TypeParameters._3_V2), org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation.INSTANCE);
		public static final @NonNull Operation _Map__isEmpty = LIBRARY.createOperation("isEmpty", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Map,
			29, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Map__isUnique = LIBRARY.createOperation("isUnique", Parameters._Lambda_0_K_OclAny, Types._Map,
			30, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
		public static final @NonNull Operation _Map__iterate = LIBRARY.createOperation("iterate", Parameters._Lambda_0_K_2_Tacc, Types._Map,
			31, TypeUtil.createTemplateParameters(TypeParameters._2_Tacc), org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
		public static final @NonNull Operation _Map__keys = LIBRARY.createOperation("keys", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Map,
			32, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapKeysOperation.INSTANCE);
		public static final @NonNull Operation _Map__notEmpty = LIBRARY.createOperation("notEmpty", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Map,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation.INSTANCE);
		public static final @NonNull Operation _Map__one = LIBRARY.createOperation("one", Parameters._Lambda_0_K_Boolean, Types._Map,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
		public static final @NonNull Operation _Map__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_K_Boolean, Types._Map,
			35, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.MapRejectIteration.INSTANCE);
		public static final @NonNull Operation _Map__select = LIBRARY.createOperation("select", Parameters._Lambda_0_K_Boolean, Types._Map,
			36, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.MapSelectIteration.INSTANCE);
		public static final @NonNull Operation _Map__size = LIBRARY.createOperation("size", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Map,
			37, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapSizeOperation.INSTANCE);
		public static final @NonNull Operation _Map__values = LIBRARY.createOperation("values", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Map,
			38, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.map.MapValuesOperation.INSTANCE);

		public static final @NonNull Operation _OclAny___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OclAny,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclAny___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OclAny,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclAsSet = LIBRARY.createOperation("oclAsSet", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._0_TT, Types._OclAny,
			3, TypeUtil.createTemplateParameters(TypeParameters._0_TT), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsInState = LIBRARY.createOperation("oclIsInState", Parameters._OclState, Types._OclAny,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclAny,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsNew = LIBRARY.createOperation("oclIsNew", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclAny,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__0_oclLog = LIBRARY.createOperation("oclLog", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__1_oclLog = LIBRARY.createOperation("oclLog", Parameters._String, Types._OclAny,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclType = LIBRARY.createOperation("oclType", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__oclTypes = LIBRARY.createOperation("oclTypes", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
		public static final @NonNull Operation _OclAny__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclAny,
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

		public static final @NonNull Operation _OclElement__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._OclElement,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclAsModelType = LIBRARY.createOperation("oclAsModelType", Parameters._0_TT, Types._OclElement,
			1, TypeUtil.createTemplateParameters(TypeParameters._0_TT), org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__0_oclBase = LIBRARY.createOperation("oclBase", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__1_oclBase = LIBRARY.createOperation("oclBase", Parameters._OclType, Types._OclElement,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclContainer = LIBRARY.createOperation("oclContainer", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclContents = LIBRARY.createOperation("oclContents", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclExtension = LIBRARY.createOperation("oclExtension", Parameters._OclStereotype, Types._OclElement,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__1_oclExtensions = LIBRARY.createOperation("oclExtensions", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__0_oclExtensions = LIBRARY.createOperation("oclExtensions", Parameters._OclStereotype, Types._OclElement,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclIsModelKindOf = LIBRARY.createOperation("oclIsModelKindOf", Parameters._OclType, Types._OclElement,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclModelType = LIBRARY.createOperation("oclModelType", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclElement__oclModelTypes = LIBRARY.createOperation("oclModelTypes", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclElement,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation.INSTANCE);

		public static final @NonNull Operation _OclEnumeration__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._OclEnumeration,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

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
		public static final @NonNull Operation _OclInvalid__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._OclInvalid,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclAsSet = LIBRARY.createOperation("oclAsSet", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._0_TT, Types._OclInvalid,
			7, TypeUtil.createTemplateParameters(TypeParameters._0_TT), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclBadOperation = LIBRARY.createOperation("oclBadOperation", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			8, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _OclInvalid__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclInvalid,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclInvalid,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__oclType = LIBRARY.createOperation("oclType", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclInvalid__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclInvalid,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _OclMessage__hasReturned = LIBRARY.createOperation("hasReturned", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclMessage,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__isOperationCall = LIBRARY.createOperation("isOperationCall", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclMessage,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__isSignalSent = LIBRARY.createOperation("isSignalSent", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclMessage,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclMessage__result = LIBRARY.createOperation("result", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclMessage,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

		public static final @NonNull Operation _OclStereotype__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._OclStereotype,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

		public static final @NonNull Operation _OclSummable__sum = LIBRARY.createOperation("sum", Parameters._OclSelf, Types._OclSummable,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull Operation _OclSummable__zero = LIBRARY.createOperation("zero", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclSummable,
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
		public static final @NonNull Operation _OclVoid__not = LIBRARY.createOperation("not", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__or = LIBRARY.createOperation("or", Parameters._Boolean, Types._OclVoid,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__xor = LIBRARY.createOperation("xor", Parameters._Boolean, Types._OclVoid,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__allInstances = LIBRARY.createOperation("allInstances", Parameters._Integer, Types._OclVoid,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__concat = LIBRARY.createOperation("concat", Parameters._String, Types._OclVoid,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclAsSet = LIBRARY.createOperation("oclAsSet", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._0_TT, Types._OclVoid,
			11, TypeUtil.createTemplateParameters(TypeParameters._0_TT), org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsInvalid = LIBRARY.createOperation("oclIsInvalid", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsKindOf = LIBRARY.createOperation("oclIsKindOf", Parameters._OclType, Types._OclVoid,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsTypeOf = LIBRARY.createOperation("oclIsTypeOf", Parameters._OclType, Types._OclVoid,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclIsUndefined = LIBRARY.createOperation("oclIsUndefined", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclType = LIBRARY.createOperation("oclType", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__oclTypes = LIBRARY.createOperation("oclTypes", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
		public static final @NonNull Operation _OclVoid__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OclVoid,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _OrderedCollection__at = LIBRARY.createOperation("at", Parameters._Integer, Types._OrderedCollection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__first = LIBRARY.createOperation("first", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OrderedCollection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__indexOf = LIBRARY.createOperation("indexOf", Parameters._0_T, Types._OrderedCollection,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation.INSTANCE);
		public static final @NonNull Operation _OrderedCollection__last = LIBRARY.createOperation("last", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OrderedCollection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation.INSTANCE);

		public static final @NonNull Operation _OrderedSet___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._OrderedSet,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._OrderedSet,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._OrderedSet,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__append = LIBRARY.createOperation("append", Parameters._0_T, Types._OrderedSet,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__appendAll = LIBRARY.createOperation("appendAll", Parameters._OrderedCollection__0_T__, Types._OrderedSet,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__closure = LIBRARY.createOperation("closure", Parameters._Lambda_0_T_OrderedSet__0_T__, Types._OrderedSet,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_T_1_V, Types._OrderedSet,
			6, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_T_1_V, Types._OrderedSet,
			7, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__excluding = LIBRARY.createOperation("excluding", Parameters._0_T, Types._OrderedSet,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_T__, Types._OrderedSet,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__flatten = LIBRARY.createOperation("flatten", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OrderedSet,
			10, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__including = LIBRARY.createOperation("including", Parameters._0_T, Types._OrderedSet,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__0_T__, Types._OrderedSet,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__insertAt = LIBRARY.createOperation("insertAt", Parameters._Integer___0_T, Types._OrderedSet,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__prepend = LIBRARY.createOperation("prepend", Parameters._0_T, Types._OrderedSet,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__prependAll = LIBRARY.createOperation("prependAll", Parameters._OrderedCollection__0_T__, Types._OrderedSet,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_T_Boolean, Types._OrderedSet,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__reverse = LIBRARY.createOperation("reverse", TypeUtil.EMPTY_PARAMETER_TYPES, Types._OrderedSet,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__select = LIBRARY.createOperation("select", Parameters._Lambda_0_T_Boolean, Types._OrderedSet,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._1_TT, Types._OrderedSet,
			19, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__selectByType = LIBRARY.createOperation("selectByType", Parameters._1_TT, Types._OrderedSet,
			20, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _OrderedSet__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._OrderedSet,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _OrderedSet__subOrderedSet = LIBRARY.createOperation("subOrderedSet", Parameters._Integer___Integer, Types._OrderedSet,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation.INSTANCE);

		public static final @NonNull Operation _Real___mul_ = LIBRARY.createOperation("*", Parameters._OclSelf, Types._Real,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
		public static final @NonNull Operation _Real___add_ = LIBRARY.createOperation("+", Parameters._OclSelf, Types._Real,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
		public static final @NonNull Operation _Real___neg_ = LIBRARY.createOperation("-", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Real,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
		public static final @NonNull Operation _Real___sub_ = LIBRARY.createOperation("-", Parameters._OclSelf, Types._Real,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
		public static final @NonNull Operation _Real___div_ = LIBRARY.createOperation("/", Parameters._OclSelf, Types._Real,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
		public static final @NonNull Operation _Real___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Real,
			5, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Real___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Real,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Real__abs = LIBRARY.createOperation("abs", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Real,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
		public static final @NonNull Operation _Real__floor = LIBRARY.createOperation("floor", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Real,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation.INSTANCE);
		public static final @NonNull Operation _Real__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._Real,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
		public static final @NonNull Operation _Real__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._Real,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
		public static final @NonNull Operation _Real__round = LIBRARY.createOperation("round", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Real,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation.INSTANCE);
		public static final @NonNull Operation _Real__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Real,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);

		public static final @NonNull Operation _Sequence___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Sequence,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Sequence___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Sequence,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__append = LIBRARY.createOperation("append", Parameters._0_T, Types._Sequence,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__appendAll = LIBRARY.createOperation("appendAll", Parameters._OrderedCollection__0_T__, Types._Sequence,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__closure = LIBRARY.createOperation("closure", Parameters._Lambda_0_T_OrderedSet__0_T__, Types._Sequence,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_T_1_V, Types._Sequence,
			5, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_T_1_V, Types._Sequence,
			6, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__excluding = LIBRARY.createOperation("excluding", Parameters._0_T, Types._Sequence,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_T__, Types._Sequence,
			8, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__flatten = LIBRARY.createOperation("flatten", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Sequence,
			9, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__including = LIBRARY.createOperation("including", Parameters._0_T, Types._Sequence,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__0_T__, Types._Sequence,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__insertAt = LIBRARY.createOperation("insertAt", Parameters._Integer___0_T, Types._Sequence,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__prepend = LIBRARY.createOperation("prepend", Parameters._0_T, Types._Sequence,
			13, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__prependAll = LIBRARY.createOperation("prependAll", Parameters._OrderedCollection__0_T__, Types._Sequence,
			14, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_T_Boolean, Types._Sequence,
			15, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__reverse = LIBRARY.createOperation("reverse", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Sequence,
			16, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__select = LIBRARY.createOperation("select", Parameters._Lambda_0_T_Boolean, Types._Sequence,
			17, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._1_TT, Types._Sequence,
			18, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__selectByType = LIBRARY.createOperation("selectByType", Parameters._1_TT, Types._Sequence,
			19, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Sequence__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._Sequence,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _Sequence__subSequence = LIBRARY.createOperation("subSequence", Parameters._Integer___Integer, Types._Sequence,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation.INSTANCE);

		public static final @NonNull Operation _Set___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._Set,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _Set___lt__gt_ = LIBRARY.createOperation("<>", Parameters._OclSelf, Types._Set,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
		public static final @NonNull Operation _Set___eq_ = LIBRARY.createOperation("=", Parameters._OclSelf, Types._Set,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
		public static final @NonNull Operation _Set__closure = LIBRARY.createOperation("closure", Parameters._Lambda_0_T_Set__0_T__, Types._Set,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
		public static final @NonNull Operation _Set__collect = LIBRARY.createOperation("collect", Parameters._Lambda_0_T_1_V, Types._Set,
			4, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
		public static final @NonNull Operation _Set__collectNested = LIBRARY.createOperation("collectNested", Parameters._Lambda_0_T_1_V, Types._Set,
			5, TypeUtil.createTemplateParameters(TypeParameters._1_V), org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
		public static final @NonNull Operation _Set__excluding = LIBRARY.createOperation("excluding", Parameters._0_T, Types._Set,
			6, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
		public static final @NonNull Operation _Set__excludingAll = LIBRARY.createOperation("excludingAll", Parameters._Collection__0_T__, Types._Set,
			7, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Set__flatten = LIBRARY.createOperation("flatten", TypeUtil.EMPTY_PARAMETER_TYPES, Types._Set,
			8, TypeUtil.createTemplateParameters(TypeParameters._1_T2), org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
		public static final @NonNull Operation _Set__including = LIBRARY.createOperation("including", Parameters._0_T, Types._Set,
			9, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
		public static final @NonNull Operation _Set__includingAll = LIBRARY.createOperation("includingAll", Parameters._Collection__0_T__, Types._Set,
			10, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
		public static final @NonNull Operation _Set__reject = LIBRARY.createOperation("reject", Parameters._Lambda_0_T_Boolean, Types._Set,
			11, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
		public static final @NonNull Operation _Set__select = LIBRARY.createOperation("select", Parameters._Lambda_0_T_Boolean, Types._Set,
			12, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
		public static final @NonNull Operation _Set__selectByKind = LIBRARY.createOperation("selectByKind", Parameters._1_TT, Types._Set,
			13, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
		public static final @NonNull Operation _Set__selectByType = LIBRARY.createOperation("selectByType", Parameters._1_TT, Types._Set,
			14, TypeUtil.createTemplateParameters(TypeParameters._1_TT), org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
		public static final @NonNull Operation _Set__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._Set,
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
		public static final @NonNull Operation _String__characters = LIBRARY.createOperation("characters", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
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
		public static final @NonNull Operation _String__size = LIBRARY.createOperation("size", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			18, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSizeOperation.INSTANCE);
		public static final @NonNull Operation _String__startsWith = LIBRARY.createOperation("startsWith", Parameters._String, Types._String,
			19, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringStartsWithOperation.INSTANCE);
		public static final @NonNull Operation _String__substituteAll = LIBRARY.createOperation("substituteAll", Parameters._String___String, Types._String,
			20, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation.INSTANCE);
		public static final @NonNull Operation _String__substituteFirst = LIBRARY.createOperation("substituteFirst", Parameters._String___String, Types._String,
			21, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation.INSTANCE);
		public static final @NonNull Operation _String__substring = LIBRARY.createOperation("substring", Parameters._Integer___Integer, Types._String,
			22, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringSubstringOperation.INSTANCE);
		public static final @NonNull Operation _String__toBoolean = LIBRARY.createOperation("toBoolean", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			23, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToBooleanOperation.INSTANCE);
		public static final @NonNull Operation _String__toInteger = LIBRARY.createOperation("toInteger", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			24, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToIntegerOperation.INSTANCE);
		public static final @NonNull Operation _String__toLower = LIBRARY.createOperation("toLower", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			25, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toLowerCase = LIBRARY.createOperation("toLowerCase", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			26, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toReal = LIBRARY.createOperation("toReal", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			27, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToRealOperation.INSTANCE);
		public static final @NonNull Operation _String__toString = LIBRARY.createOperation("toString", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			28, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
		public static final @NonNull Operation _String__toUpper = LIBRARY.createOperation("toUpper", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			29, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__toUpperCase = LIBRARY.createOperation("toUpperCase", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			30, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
		public static final @NonNull Operation _String__0_tokenize = LIBRARY.createOperation("tokenize", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			31, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__1_tokenize = LIBRARY.createOperation("tokenize", Parameters._String, Types._String,
			32, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__2_tokenize = LIBRARY.createOperation("tokenize", Parameters._String___Boolean, Types._String,
			33, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
		public static final @NonNull Operation _String__trim = LIBRARY.createOperation("trim", TypeUtil.EMPTY_PARAMETER_TYPES, Types._String,
			34, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.string.StringTrimOperation.INSTANCE);

		public static final @NonNull Operation _UniqueCollection___sub_ = LIBRARY.createOperation("-", Parameters._UniqueCollection__OclAny__, Types._UniqueCollection,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__intersection = LIBRARY.createOperation("intersection", Parameters._Collection__0_T__, Types._UniqueCollection,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__sortedBy = LIBRARY.createOperation("sortedBy", Parameters._Lambda_0_T_OclAny, Types._UniqueCollection,
			2, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__symmetricDifference = LIBRARY.createOperation("symmetricDifference", Parameters._UniqueCollection__OclAny__, Types._UniqueCollection,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation.INSTANCE);
		public static final @NonNull Operation _UniqueCollection__union = LIBRARY.createOperation("union", Parameters._UniqueCollection__0_T__, Types._UniqueCollection,
			4, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);

		public static final @NonNull Operation _UnlimitedNatural__max = LIBRARY.createOperation("max", Parameters._OclSelf, Types._UnlimitedNatural,
			0, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__min = LIBRARY.createOperation("min", Parameters._OclSelf, Types._UnlimitedNatural,
			1, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__oclAsType = LIBRARY.createOperation("oclAsType", Parameters._0_TT, Types._UnlimitedNatural,
			2, TypeUtil.createTemplateParameters(TypeParameters._0_TT), org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation.INSTANCE);
		public static final @NonNull Operation _UnlimitedNatural__toInteger = LIBRARY.createOperation("toInteger", TypeUtil.EMPTY_PARAMETER_TYPES, Types._UnlimitedNatural,
			3, TemplateParameters.EMPTY_LIST, org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation.INSTANCE);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of OCLstdlibTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}

	}
	// CTOR Collection(collectNested.V)
	// CTOR Collection(collect.V)
	// CTOR Collection(flatten.T2)
	// CTOR Collection(selectByKind.TT)
	// CTOR Collection(selectByType.TT)
	// CTOR Collection(collectNested.V)
	// CTOR Collection(collect.V)
	// CTOR Collection(excludesAll.T2)
	// CTOR Collection(flatten.T2)
	// CTOR Collection(includesAll.T2)
	// CTOR Collection(product.T2)
	// CTOR Collection(selectByKind.TT)
	// CTOR Collection(selectByType.TT)
	// CTOR Collection(collect.V2)
	// CTOR Collection(excludesAll.K2)
	// CTOR Collection(includesAll.K2)
	// CTOR Collection(collectNested.V)
	// CTOR Collection(collect.V)
	// CTOR Collection(flatten.T2)
	// CTOR Collection(selectByKind.TT)
	// CTOR Collection(selectByType.TT)
	// CTOR Collection(collectNested.V)
	// CTOR Collection(collect.V)
	// CTOR Collection(flatten.T2)
	// CTOR Collection(selectByKind.TT)
	// CTOR Collection(selectByType.TT)
	// CTOR Collection(collectNested.V)
	// CTOR Collection(collect.V)
	// CTOR Collection(flatten.T2)
	// CTOR Collection(selectByKind.TT)
	// CTOR Collection(selectByType.TT)
	// CTOR Map(Collection.T[?],collectBy.V[?])
	// CTOR Map(excludesMap.K2,excludesMap.V2)
	// CTOR Map(excludingMap.K2,excludingMap.V2)
	// CTOR Map(includesMap.K2,includesMap.V2)
	// CTOR Map(includingMap.K2,includingMap.V2)
	// CTOR Map(Map.K[?],collectBy.V2[?])
	// CTOR Map(Map.K[?],collectNested.V2[?])
	// CTOR Tuple(first:Collection.T[1],second:product.T2[1])
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : Tacc
	// CTOR Lambda K() : V2
	// CTOR Lambda K() : V2
	// CTOR Lambda K() : V2
	// CTOR Lambda K() : Tacc
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// CTOR Lambda T() : V
	// OPERATIONS Collection(Integer)
	// OPERATIONS Collection(String)
	// OPERATIONS Collection(Bag.T)
	// OPERATIONS Collection(CollectionType)
	// OPERATIONS Collection(Collection.T)
	// OPERATIONS Collection(MapType)
	// OPERATIONS Collection(Map.K)
	// OPERATIONS Collection(Map.V)
	// OPERATIONS Collection(OclAny)
	// OPERATIONS Collection(OclElement)
	// OPERATIONS Collection(OclInvalid)
	// OPERATIONS Collection(OclSelf)
	// OPERATIONS Collection(OrderedCollection.T)
	// OPERATIONS Collection(OrderedSet.T)
	// OPERATIONS Collection(Sequence.T)
	// OPERATIONS Collection(Set.T)
	// OPERATIONS Collection(UniqueCollection.T)
	// OPERATIONS Map(Map.K,Map.V)
	// OPERATIONS Map(Map.K[?],Map.V[?])
	// OPERATIONS Lambda T() : Boolean
	// OPERATIONS Lambda T() : OclAny
	// OPERATIONS Lambda T() : Boolean
	// OPERATIONS Lambda T() : OclAny
	// OPERATIONS Lambda K() : Boolean
	// OPERATIONS Lambda K() : OclAny
	// OPERATIONS Lambda T() : Boolean
	// OPERATIONS Lambda T() : OclAny
	// OPERATIONS Lambda T() : Boolean
	// OPERATIONS Lambda T() : OclAny
	// OPERATIONS Lambda T() : Boolean
	// OPERATIONS Lambda T() : OclAny
	// OPERATIONS Lambda T() : OclAny
	// PROPERTIES Collection(Integer)
	// PROPERTIES Collection(String)
	// PROPERTIES Collection(Bag.T)
	// PROPERTIES Collection(CollectionType)
	// PROPERTIES Collection(Collection.T)
	// PROPERTIES Collection(MapType)
	// PROPERTIES Collection(Map.K)
	// PROPERTIES Collection(Map.V)
	// PROPERTIES Collection(OclAny)
	// PROPERTIES Collection(OclElement)
	// PROPERTIES Collection(OclInvalid)
	// PROPERTIES Collection(OclSelf)
	// PROPERTIES Collection(OrderedCollection.T)
	// PROPERTIES Collection(OrderedSet.T)
	// PROPERTIES Collection(Sequence.T)
	// PROPERTIES Collection(Set.T)
	// PROPERTIES Collection(UniqueCollection.T)
	// PROPERTIES Map(Map.K,Map.V)
	// PROPERTIES Map(Map.K[?],Map.V[?])
	// PROPERTIES Lambda T() : Boolean
	// PROPERTIES Lambda T() : OclAny
	// PROPERTIES Lambda T() : Boolean
	// PROPERTIES Lambda T() : OclAny
	// PROPERTIES Lambda K() : Boolean
	// PROPERTIES Lambda K() : OclAny
	// PROPERTIES Lambda T() : Boolean
	// PROPERTIES Lambda T() : OclAny
	// PROPERTIES Lambda T() : Boolean
	// PROPERTIES Lambda T() : OclAny
	// PROPERTIES Lambda T() : Boolean
	// PROPERTIES Lambda T() : OclAny
	// PROPERTIES Lambda T() : OclAny
	// SUPER_CLASSES Collection(Integer)
	// SUPER_CLASSES Collection(String)
	// SUPER_CLASSES Collection(Bag.T)
	// SUPER_CLASSES Collection(CollectionType)
	// SUPER_CLASSES Collection(Collection.T)
	// SUPER_CLASSES Collection(MapType)
	// SUPER_CLASSES Collection(Map.K)
	// SUPER_CLASSES Collection(Map.V)
	// SUPER_CLASSES Collection(OclAny)
	// SUPER_CLASSES Collection(OclElement)
	// SUPER_CLASSES Collection(OclInvalid)
	// SUPER_CLASSES Collection(OclSelf)
	// SUPER_CLASSES Collection(OrderedCollection.T)
	// SUPER_CLASSES Collection(OrderedSet.T)
	// SUPER_CLASSES Collection(Sequence.T)
	// SUPER_CLASSES Collection(Set.T)
	// SUPER_CLASSES Collection(UniqueCollection.T)
	// SUPER_CLASSES OrderedCollection(T)
	// SUPER_CLASSES UniqueCollection(T)
	// SUPER_CLASSES Map(Map.K,Map.V)
	// SUPER_CLASSES Map(Map.K[?],Map.V[?])
	// SUPER_CLASSES Bag(T)
	// SUPER_CLASSES Lambda T() : Boolean
	// SUPER_CLASSES Lambda T() : OclAny
	// SUPER_CLASSES Lambda T() : Boolean
	// SUPER_CLASSES Lambda T() : OclAny
	// SUPER_CLASSES Lambda K() : Boolean
	// SUPER_CLASSES Lambda K() : OclAny
	// SUPER_CLASSES Lambda T() : Boolean
	// SUPER_CLASSES Lambda T() : OclAny
	// SUPER_CLASSES Lambda T() : Boolean
	// SUPER_CLASSES Lambda T() : OclAny
	// SUPER_CLASSES Lambda T() : Boolean
	// SUPER_CLASSES Lambda T() : OclAny
	// SUPER_CLASSES Lambda T() : OclAny
	// TYPE Collection(T).any(Collection.T[1] | Lambda T() : Boolean[1]) : Collection.T[?]
	// TYPE Collection(T).exists(Collection.T[?], Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).exists(Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).exists(Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).forAll(Collection.T[?], Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).forAll(Collection.T[?], Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).forAll(Collection.T[?] | Lambda T() : Boolean[?]) : Boolean[?]
	// TYPE Collection(T).isUnique(Collection.T[?] | Lambda T() : OclAny[?]) : Boolean[1]
	// TYPE Collection(T).one(Collection.T[?] | Lambda T() : Boolean[1]) : Boolean[1]
	// TYPE Collection(T).reject(Collection.T[?] | Lambda T() : Boolean[1]) : Collection(Collection.T)
	// TYPE Collection(T).select(Collection.T[?] | Lambda T() : Boolean[1]) : Collection(Collection.T)
	// TYPE Map(K,V).any(Map.K[1] | Lambda K() : Boolean[1]) : Map.K[?]
	// TYPE Map(K,V).exists(Map.K[?], Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).exists(Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).exists(Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).forAll(Map.K[?], Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).forAll(Map.K[?], Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).forAll(Map.K[?] | Lambda K() : Boolean[?]) : Boolean[?]
	// TYPE Map(K,V).isUnique(Map.K[?] | Lambda K() : OclAny[?]) : Boolean[1]
	// TYPE Map(K,V).one(Map.K[?] | Lambda K() : Boolean[1]) : Boolean[1]
	// TYPE Map(K,V).reject(Map.K[?] | Lambda K() : Boolean[1]) : Map(Map.K[?],Map.V[?])[1]
	// TYPE Map(K,V).select(Map.K[?] | Lambda K() : Boolean[1]) : Map(Map.K[?],Map.V[?])[1]
	// TYPE UnlimitedNatural::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// TYPE Collection(T)::excluding(Collection.T[?]) : Collection(Collection.T)
	// TYPE Collection(T)::excludingAll(Collection(Collection.T)) : Collection(Collection.T)
	// TYPE Collection(T)::including(Collection.T[?]) : Collection(Collection.T)
	// TYPE Collection(T)::includingAll(Collection(Collection.T)) : Collection(Collection.T)
	// TYPE Map(K,V)::excluding(Map.K[?]) : Map(Map.K,Map.V)[1]
	// TYPE Map(K,V)::excluding(Map.K[?],Map.V[?]) : Map(Map.K,Map.V)[1]
	// TYPE Map(K,V)::excludingAll(Collection(Map.K)) : Map(Map.K,Map.V)[1]
	// TYPE Map(K,V)::including(Map.K[?],Map.V[?]) : Map(Map.K,Map.V)[1]
	// TYPE OclAny::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// TYPE OclElement::oclAsModelType(TT)(oclAsModelType.TT[1]) : oclAsModelType.TT[1]
	// TYPE OclInvalid::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]
	// TYPE OclVoid::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[?]

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


		public static final @NonNull Property _Collection__elementType = LIBRARY.createProperty("elementType", Types._Collection, 0, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Property _Collection__lower = LIBRARY.createProperty("lower", Types._Collection, 1, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Property _Collection__upper = LIBRARY.createProperty("upper", Types._Collection, 2, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

		public static final @NonNull Property _Map__keyType = LIBRARY.createProperty("keyType", Types._Map, 0, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		public static final @NonNull Property _Map__valueType = LIBRARY.createProperty("valueType", Types._Map, 1, org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);

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
	// CTOR $$::Tuple::first
	// CTOR $$::Tuple::second
	// CTOR Collection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// CTOR OrderedCollection(Integer)
	// CTOR OrderedCollection(String)
	// CTOR OrderedCollection(Bag.T)
	// CTOR OrderedCollection(Collection.T)
	// CTOR OrderedCollection(collectNested.V)
	// CTOR OrderedCollection(collect.V)
	// CTOR OrderedCollection(flatten.T2)
	// CTOR OrderedCollection(selectByKind.TT)
	// CTOR OrderedCollection(selectByType.TT)
	// CTOR OrderedCollection(OrderedSet.T)
	// CTOR OrderedCollection(collectNested.V)
	// CTOR OrderedCollection(collect.V)
	// CTOR OrderedCollection(flatten.T2)
	// CTOR OrderedCollection(selectByKind.TT)
	// CTOR OrderedCollection(selectByType.TT)
	// CTOR OrderedCollection(Sequence.T)
	// CTOR OrderedCollection(Set.T)
	// CTOR OrderedCollection(UniqueCollection.T)
	// CTOR UniqueCollection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// CTOR UniqueCollection(Bag.T)
	// CTOR UniqueCollection(Collection.T)
	// CTOR UniqueCollection(Map.K)
	// CTOR UniqueCollection(OclAny)
	// CTOR UniqueCollection(OclElement)
	// CTOR UniqueCollection(OclSelf)
	// CTOR UniqueCollection(flatten.T2)
	// CTOR UniqueCollection(selectByKind.TT)
	// CTOR UniqueCollection(selectByType.TT)
	// CTOR UniqueCollection(OrderedSet.T)
	// CTOR UniqueCollection(Sequence.T)
	// CTOR UniqueCollection(flatten.T2)
	// CTOR UniqueCollection(selectByKind.TT)
	// CTOR UniqueCollection(selectByType.TT)
	// CTOR UniqueCollection(Set.T)
	// CTOR UniqueCollection(UniqueCollection.T)
	// CTOR Bag(collectNested.V)
	// CTOR Bag(collect.V)
	// CTOR Bag(flatten.T2)
	// CTOR Bag(selectByKind.TT)
	// CTOR Bag(selectByType.TT)
	// CTOR Bag(Bag.T)
	// CTOR Bag(CollectionType)
	// CTOR Bag(Collection.T)
	// CTOR Bag(MapType)
	// CTOR Bag(collect.V2)
	// CTOR Bag(Map.V)
	// CTOR Bag(OclElement)
	// CTOR Bag(OclInvalid)
	// CTOR Bag(collectNested.V)
	// CTOR Bag(collect.V)
	// OPERATIONS Collection(collectNested.V)
	// OPERATIONS Collection(collect.V)
	// OPERATIONS Collection(flatten.T2)
	// OPERATIONS Collection(selectByKind.TT)
	// OPERATIONS Collection(selectByType.TT)
	// OPERATIONS Collection(collectNested.V)
	// OPERATIONS Collection(collect.V)
	// OPERATIONS Collection(excludesAll.T2)
	// OPERATIONS Collection(flatten.T2)
	// OPERATIONS Collection(includesAll.T2)
	// OPERATIONS Collection(product.T2)
	// OPERATIONS Collection(selectByKind.TT)
	// OPERATIONS Collection(selectByType.TT)
	// OPERATIONS Collection(collect.V2)
	// OPERATIONS Collection(excludesAll.K2)
	// OPERATIONS Collection(includesAll.K2)
	// OPERATIONS Collection(collectNested.V)
	// OPERATIONS Collection(collect.V)
	// OPERATIONS Collection(flatten.T2)
	// OPERATIONS Collection(selectByKind.TT)
	// OPERATIONS Collection(selectByType.TT)
	// OPERATIONS Collection(collectNested.V)
	// OPERATIONS Collection(collect.V)
	// OPERATIONS Collection(flatten.T2)
	// OPERATIONS Collection(selectByKind.TT)
	// OPERATIONS Collection(selectByType.TT)
	// OPERATIONS Collection(collectNested.V)
	// OPERATIONS Collection(collect.V)
	// OPERATIONS Collection(flatten.T2)
	// OPERATIONS Collection(selectByKind.TT)
	// OPERATIONS Collection(selectByType.TT)
	// OPERATIONS Map(Collection.T[?],collectBy.V[?])
	// OPERATIONS Map(excludesMap.K2,excludesMap.V2)
	// OPERATIONS Map(excludingMap.K2,excludingMap.V2)
	// OPERATIONS Map(includesMap.K2,includesMap.V2)
	// OPERATIONS Map(includingMap.K2,includingMap.V2)
	// OPERATIONS Map(Map.K[?],collectBy.V2[?])
	// OPERATIONS Map(Map.K[?],collectNested.V2[?])
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : Tacc
	// OPERATIONS Lambda K() : V2
	// OPERATIONS Lambda K() : V2
	// OPERATIONS Lambda K() : V2
	// OPERATIONS Lambda K() : Tacc
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// OPERATIONS Lambda T() : V
	// PROPERTIES Collection(collectNested.V)
	// PROPERTIES Collection(collect.V)
	// PROPERTIES Collection(flatten.T2)
	// PROPERTIES Collection(selectByKind.TT)
	// PROPERTIES Collection(selectByType.TT)
	// PROPERTIES Collection(collectNested.V)
	// PROPERTIES Collection(collect.V)
	// PROPERTIES Collection(excludesAll.T2)
	// PROPERTIES Collection(flatten.T2)
	// PROPERTIES Collection(includesAll.T2)
	// PROPERTIES Collection(product.T2)
	// PROPERTIES Collection(selectByKind.TT)
	// PROPERTIES Collection(selectByType.TT)
	// PROPERTIES Collection(collect.V2)
	// PROPERTIES Collection(excludesAll.K2)
	// PROPERTIES Collection(includesAll.K2)
	// PROPERTIES Collection(collectNested.V)
	// PROPERTIES Collection(collect.V)
	// PROPERTIES Collection(flatten.T2)
	// PROPERTIES Collection(selectByKind.TT)
	// PROPERTIES Collection(selectByType.TT)
	// PROPERTIES Collection(collectNested.V)
	// PROPERTIES Collection(collect.V)
	// PROPERTIES Collection(flatten.T2)
	// PROPERTIES Collection(selectByKind.TT)
	// PROPERTIES Collection(selectByType.TT)
	// PROPERTIES Collection(collectNested.V)
	// PROPERTIES Collection(collect.V)
	// PROPERTIES Collection(flatten.T2)
	// PROPERTIES Collection(selectByKind.TT)
	// PROPERTIES Collection(selectByType.TT)
	// PROPERTIES Map(Collection.T[?],collectBy.V[?])
	// PROPERTIES Map(excludesMap.K2,excludesMap.V2)
	// PROPERTIES Map(excludingMap.K2,excludingMap.V2)
	// PROPERTIES Map(includesMap.K2,includesMap.V2)
	// PROPERTIES Map(includingMap.K2,includingMap.V2)
	// PROPERTIES Map(Map.K[?],collectBy.V2[?])
	// PROPERTIES Map(Map.K[?],collectNested.V2[?])
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : Tacc
	// PROPERTIES Lambda K() : V2
	// PROPERTIES Lambda K() : V2
	// PROPERTIES Lambda K() : V2
	// PROPERTIES Lambda K() : Tacc
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// PROPERTIES Lambda T() : V
	// SUPER_CLASSES Collection(collectNested.V)
	// SUPER_CLASSES Collection(collect.V)
	// SUPER_CLASSES Collection(flatten.T2)
	// SUPER_CLASSES Collection(selectByKind.TT)
	// SUPER_CLASSES Collection(selectByType.TT)
	// SUPER_CLASSES Collection(collectNested.V)
	// SUPER_CLASSES Collection(collect.V)
	// SUPER_CLASSES Collection(excludesAll.T2)
	// SUPER_CLASSES Collection(flatten.T2)
	// SUPER_CLASSES Collection(includesAll.T2)
	// SUPER_CLASSES Collection(product.T2)
	// SUPER_CLASSES Collection(selectByKind.TT)
	// SUPER_CLASSES Collection(selectByType.TT)
	// SUPER_CLASSES Collection(collect.V2)
	// SUPER_CLASSES Collection(excludesAll.K2)
	// SUPER_CLASSES Collection(includesAll.K2)
	// SUPER_CLASSES Collection(collectNested.V)
	// SUPER_CLASSES Collection(collect.V)
	// SUPER_CLASSES Collection(flatten.T2)
	// SUPER_CLASSES Collection(selectByKind.TT)
	// SUPER_CLASSES Collection(selectByType.TT)
	// SUPER_CLASSES Collection(collectNested.V)
	// SUPER_CLASSES Collection(collect.V)
	// SUPER_CLASSES Collection(flatten.T2)
	// SUPER_CLASSES Collection(selectByKind.TT)
	// SUPER_CLASSES Collection(selectByType.TT)
	// SUPER_CLASSES Collection(collectNested.V)
	// SUPER_CLASSES Collection(collect.V)
	// SUPER_CLASSES Collection(flatten.T2)
	// SUPER_CLASSES Collection(selectByKind.TT)
	// SUPER_CLASSES Collection(selectByType.TT)
	// SUPER_CLASSES Map(Collection.T[?],collectBy.V[?])
	// SUPER_CLASSES Map(excludesMap.K2,excludesMap.V2)
	// SUPER_CLASSES Map(excludingMap.K2,excludingMap.V2)
	// SUPER_CLASSES Map(includesMap.K2,includesMap.V2)
	// SUPER_CLASSES Map(includingMap.K2,includingMap.V2)
	// SUPER_CLASSES Map(Map.K[?],collectBy.V2[?])
	// SUPER_CLASSES Map(Map.K[?],collectNested.V2[?])
	// SUPER_CLASSES Tuple(first:Collection.T[1],second:product.T2[1])
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : Tacc
	// SUPER_CLASSES Lambda K() : V2
	// SUPER_CLASSES Lambda K() : V2
	// SUPER_CLASSES Lambda K() : V2
	// SUPER_CLASSES Lambda K() : Tacc
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// SUPER_CLASSES Lambda T() : V
	// TYPE Collection(T).collectBy(V)(Collection.T[?] | Lambda T() : V[?]) : Map(Collection.T[?],collectBy.V[?])[1]
	// TYPE Collection(T).collectNested(V)(Collection.T[?] | Lambda T() : V[?]) : Collection(collectNested.V)
	// TYPE Collection(T).collect(V)(Collection.T[?] | Lambda T() : V[?]) : Collection(collect.V)
	// TYPE Collection(T).iterate(Tacc)(Collection.T[?]; iterate.Tacc[?] | Lambda T() : Tacc[?]) : iterate.Tacc[?]
	// TYPE Map(K,V).collectBy(V2)(Map.K[?] | Lambda K() : V2[?]) : Map(Map.K[?],collectBy.V2[?])[1]
	// TYPE Map(K,V).collectNested(V2)(Map.K[?] | Lambda K() : V2[?]) : Map(Map.K[?],collectNested.V2[?])[1]
	// TYPE Map(K,V).iterate(Tacc)(Map.K[?]; iterate.Tacc[?] | Lambda K() : Tacc[?]) : iterate.Tacc[?]
	// TYPE Collection(T)::excludesAll(T2)(Collection(excludesAll.T2)) : Boolean[1]
	// TYPE Collection(T)::flatten(T2)() : Collection(flatten.T2)
	// TYPE Collection(T)::includesAll(T2)(Collection(includesAll.T2)) : Boolean[1]
	// TYPE Collection(T)::selectByKind(TT)(selectByKind.TT[1]) : Collection(selectByKind.TT)
	// TYPE Collection(T)::selectByType(TT)(selectByType.TT[1]) : Collection(selectByType.TT)
	// TYPE Map(K,V)::excludesAll(K2)(Collection(excludesAll.K2)) : Boolean[1]
	// TYPE Map(K,V)::excludesMap(K2,V2)(Map(excludesMap.K2,excludesMap.V2)[1]) : Boolean[1]
	// TYPE Map(K,V)::excludingMap(K2,V2)(Map(excludingMap.K2,excludingMap.V2)[1]) : Map(Map.K,Map.V)[1]
	// TYPE Map(K,V)::includesAll(K2)(Collection(includesAll.K2)) : Boolean[1]
	// TYPE Map(K,V)::includesMap(K2,V2)(Map(includesMap.K2,includesMap.V2)[1]) : Boolean[1]
	// TYPE Map(K,V)::includingMap(K2,V2)(Map(includingMap.K2,includingMap.V2)[1]) : Map(Map.K,Map.V)[1]

	// CTOR CollectionType::elementType
	// CTOR MapType::keyType
	// CTOR MapType::valueType
	// CTOR OclElement::oclContainer
	// CTOR OclElement::oclContents
	// CTOR OclInvalid::oclBadProperty
	// OPERATIONS Collection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// OPERATIONS OrderedCollection(Integer)
	// OPERATIONS OrderedCollection(String)
	// OPERATIONS OrderedCollection(Bag.T)
	// OPERATIONS OrderedCollection(Collection.T)
	// OPERATIONS OrderedCollection(collectNested.V)
	// OPERATIONS OrderedCollection(collect.V)
	// OPERATIONS OrderedCollection(flatten.T2)
	// OPERATIONS OrderedCollection(selectByKind.TT)
	// OPERATIONS OrderedCollection(selectByType.TT)
	// OPERATIONS OrderedCollection(OrderedSet.T)
	// OPERATIONS OrderedCollection(collectNested.V)
	// OPERATIONS OrderedCollection(collect.V)
	// OPERATIONS OrderedCollection(flatten.T2)
	// OPERATIONS OrderedCollection(selectByKind.TT)
	// OPERATIONS OrderedCollection(selectByType.TT)
	// OPERATIONS OrderedCollection(Sequence.T)
	// OPERATIONS OrderedCollection(Set.T)
	// OPERATIONS OrderedCollection(UniqueCollection.T)
	// OPERATIONS UniqueCollection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// OPERATIONS UniqueCollection(Bag.T)
	// OPERATIONS UniqueCollection(Collection.T)
	// OPERATIONS UniqueCollection(Map.K)
	// OPERATIONS UniqueCollection(OclAny)
	// OPERATIONS UniqueCollection(OclElement)
	// OPERATIONS UniqueCollection(OclSelf)
	// OPERATIONS UniqueCollection(flatten.T2)
	// OPERATIONS UniqueCollection(selectByKind.TT)
	// OPERATIONS UniqueCollection(selectByType.TT)
	// OPERATIONS UniqueCollection(OrderedSet.T)
	// OPERATIONS UniqueCollection(Sequence.T)
	// OPERATIONS UniqueCollection(flatten.T2)
	// OPERATIONS UniqueCollection(selectByKind.TT)
	// OPERATIONS UniqueCollection(selectByType.TT)
	// OPERATIONS UniqueCollection(Set.T)
	// OPERATIONS UniqueCollection(UniqueCollection.T)
	// OPERATIONS Bag(collectNested.V)
	// OPERATIONS Bag(collect.V)
	// OPERATIONS Bag(flatten.T2)
	// OPERATIONS Bag(selectByKind.TT)
	// OPERATIONS Bag(selectByType.TT)
	// OPERATIONS Bag(Bag.T)
	// OPERATIONS Bag(CollectionType)
	// OPERATIONS Bag(Collection.T)
	// OPERATIONS Bag(MapType)
	// OPERATIONS Bag(collect.V2)
	// OPERATIONS Bag(Map.V)
	// OPERATIONS Bag(OclElement)
	// OPERATIONS Bag(OclInvalid)
	// OPERATIONS Bag(collectNested.V)
	// OPERATIONS Bag(collect.V)
	// PROPERTIES Collection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// PROPERTIES OrderedCollection(Integer)
	// PROPERTIES OrderedCollection(String)
	// PROPERTIES OrderedCollection(Bag.T)
	// PROPERTIES OrderedCollection(Collection.T)
	// PROPERTIES OrderedCollection(collectNested.V)
	// PROPERTIES OrderedCollection(collect.V)
	// PROPERTIES OrderedCollection(flatten.T2)
	// PROPERTIES OrderedCollection(selectByKind.TT)
	// PROPERTIES OrderedCollection(selectByType.TT)
	// PROPERTIES OrderedCollection(OrderedSet.T)
	// PROPERTIES OrderedCollection(collectNested.V)
	// PROPERTIES OrderedCollection(collect.V)
	// PROPERTIES OrderedCollection(flatten.T2)
	// PROPERTIES OrderedCollection(selectByKind.TT)
	// PROPERTIES OrderedCollection(selectByType.TT)
	// PROPERTIES OrderedCollection(Sequence.T)
	// PROPERTIES OrderedCollection(Set.T)
	// PROPERTIES OrderedCollection(UniqueCollection.T)
	// PROPERTIES UniqueCollection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// PROPERTIES UniqueCollection(Bag.T)
	// PROPERTIES UniqueCollection(Collection.T)
	// PROPERTIES UniqueCollection(Map.K)
	// PROPERTIES UniqueCollection(OclAny)
	// PROPERTIES UniqueCollection(OclElement)
	// PROPERTIES UniqueCollection(OclSelf)
	// PROPERTIES UniqueCollection(flatten.T2)
	// PROPERTIES UniqueCollection(selectByKind.TT)
	// PROPERTIES UniqueCollection(selectByType.TT)
	// PROPERTIES UniqueCollection(OrderedSet.T)
	// PROPERTIES UniqueCollection(Sequence.T)
	// PROPERTIES UniqueCollection(flatten.T2)
	// PROPERTIES UniqueCollection(selectByKind.TT)
	// PROPERTIES UniqueCollection(selectByType.TT)
	// PROPERTIES UniqueCollection(Set.T)
	// PROPERTIES UniqueCollection(UniqueCollection.T)
	// PROPERTIES Bag(collectNested.V)
	// PROPERTIES Bag(collect.V)
	// PROPERTIES Bag(flatten.T2)
	// PROPERTIES Bag(selectByKind.TT)
	// PROPERTIES Bag(selectByType.TT)
	// PROPERTIES Bag(Bag.T)
	// PROPERTIES Bag(CollectionType)
	// PROPERTIES Bag(Collection.T)
	// PROPERTIES Bag(MapType)
	// PROPERTIES Bag(collect.V2)
	// PROPERTIES Bag(Map.V)
	// PROPERTIES Bag(OclElement)
	// PROPERTIES Bag(OclInvalid)
	// PROPERTIES Bag(collectNested.V)
	// PROPERTIES Bag(collect.V)
	// SUPER_CLASSES Collection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// SUPER_CLASSES OrderedCollection(Integer)
	// SUPER_CLASSES OrderedCollection(String)
	// SUPER_CLASSES OrderedCollection(Bag.T)
	// SUPER_CLASSES OrderedCollection(Collection.T)
	// SUPER_CLASSES OrderedCollection(collectNested.V)
	// SUPER_CLASSES OrderedCollection(collect.V)
	// SUPER_CLASSES OrderedCollection(flatten.T2)
	// SUPER_CLASSES OrderedCollection(selectByKind.TT)
	// SUPER_CLASSES OrderedCollection(selectByType.TT)
	// SUPER_CLASSES OrderedCollection(OrderedSet.T)
	// SUPER_CLASSES OrderedCollection(collectNested.V)
	// SUPER_CLASSES OrderedCollection(collect.V)
	// SUPER_CLASSES OrderedCollection(flatten.T2)
	// SUPER_CLASSES OrderedCollection(selectByKind.TT)
	// SUPER_CLASSES OrderedCollection(selectByType.TT)
	// SUPER_CLASSES OrderedCollection(Sequence.T)
	// SUPER_CLASSES OrderedCollection(Set.T)
	// SUPER_CLASSES OrderedCollection(UniqueCollection.T)
	// SUPER_CLASSES UniqueCollection(Tuple(first:Collection.T[1],second:product.T2[1]))
	// SUPER_CLASSES UniqueCollection(Bag.T)
	// SUPER_CLASSES UniqueCollection(Collection.T)
	// SUPER_CLASSES UniqueCollection(Map.K)
	// SUPER_CLASSES UniqueCollection(OclAny)
	// SUPER_CLASSES UniqueCollection(OclElement)
	// SUPER_CLASSES UniqueCollection(OclSelf)
	// SUPER_CLASSES UniqueCollection(flatten.T2)
	// SUPER_CLASSES UniqueCollection(selectByKind.TT)
	// SUPER_CLASSES UniqueCollection(selectByType.TT)
	// SUPER_CLASSES UniqueCollection(OrderedSet.T)
	// SUPER_CLASSES UniqueCollection(Sequence.T)
	// SUPER_CLASSES UniqueCollection(flatten.T2)
	// SUPER_CLASSES UniqueCollection(selectByKind.TT)
	// SUPER_CLASSES UniqueCollection(selectByType.TT)
	// SUPER_CLASSES UniqueCollection(Set.T)
	// SUPER_CLASSES UniqueCollection(UniqueCollection.T)
	// SUPER_CLASSES Bag(collectNested.V)
	// SUPER_CLASSES Bag(collect.V)
	// SUPER_CLASSES Bag(flatten.T2)
	// SUPER_CLASSES Bag(selectByKind.TT)
	// SUPER_CLASSES Bag(selectByType.TT)
	// SUPER_CLASSES Bag(Bag.T)
	// SUPER_CLASSES Bag(CollectionType)
	// SUPER_CLASSES Bag(Collection.T)
	// SUPER_CLASSES Bag(MapType)
	// SUPER_CLASSES Bag(collect.V2)
	// SUPER_CLASSES Bag(Map.V)
	// SUPER_CLASSES Bag(OclElement)
	// SUPER_CLASSES Bag(OclInvalid)
	// SUPER_CLASSES Bag(collectNested.V)
	// SUPER_CLASSES Bag(collect.V)
	// SUPER_CLASSES OrderedSet(T)
	// SUPER_CLASSES Sequence(T)
	// SUPER_CLASSES Set(T)
	// TYPE Bag(T).collectNested(V)(Bag.T[?] | Lambda T() : V[?]) : Bag(collectNested.V)
	// TYPE Bag(T).collect(V)(Bag.T[?] | Lambda T() : V[?]) : Bag(collect.V)
	// TYPE Bag(T).reject(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(Bag.T)
	// TYPE Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(Bag.T)
	// TYPE Map(K,V).collect(V2)(Map.K[?] | Lambda K() : V2[?]) : Bag(collect.V2)
	// TYPE Set(T).collectNested(V)(Set.T[?] | Lambda T() : V[?]) : Bag(collectNested.V)
	// TYPE Set(T).collect(V)(Set.T[?] | Lambda T() : V[?]) : Bag(collect.V)
	// TYPE Bag(T)::excluding(Bag.T[?]) : Bag(Bag.T)
	// TYPE Bag(T)::excludingAll(Collection(Bag.T)) : Bag(Bag.T)
	// TYPE Bag(T)::flatten(T2)() : Bag(flatten.T2)
	// TYPE Bag(T)::including(Bag.T[?]) : Bag(Bag.T)
	// TYPE Bag(T)::includingAll(Collection(Bag.T)) : Bag(Bag.T)
	// TYPE Bag(T)::selectByKind(TT)(selectByKind.TT[1]) : Bag(selectByKind.TT)
	// TYPE Bag(T)::selectByType(TT)(selectByType.TT[1]) : Bag(selectByType.TT)
	// TYPE Collection(T)::asBag() : Bag(Collection.T)
	// TYPE Collection(T)::intersection(Collection(Collection.T)) : Bag(Collection.T)
	// TYPE Collection(T)::union(Collection(Collection.T)) : Bag(Collection.T)
	// TYPE Map(K,V)::values() : Bag(Map.V)
	// TYPE UniqueCollection(T)::-(UniqueCollection(OclAny)) : UniqueCollection(UniqueCollection.T)
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

	// CTOR OrderedSet(Collection.T)
	// CTOR OrderedSet(flatten.T2)
	// CTOR OrderedSet(selectByKind.TT)
	// CTOR OrderedSet(selectByType.TT)
	// CTOR OrderedSet(OrderedSet.T)
	// CTOR OrderedSet(Sequence.T)
	// CTOR OrderedSet(Set.T)
	// CTOR OrderedSet(UniqueCollection.T)
	// CTOR Sequence(Integer)
	// CTOR Sequence(String)
	// CTOR Sequence(Bag.T)
	// CTOR Sequence(Collection.T)
	// CTOR Sequence(collectNested.V)
	// CTOR Sequence(collect.V)
	// CTOR Sequence(collectNested.V)
	// CTOR Sequence(collect.V)
	// CTOR Sequence(flatten.T2)
	// CTOR Sequence(selectByKind.TT)
	// CTOR Sequence(selectByType.TT)
	// CTOR Sequence(Sequence.T)
	// CTOR Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// CTOR Set(Bag.T)
	// CTOR Set(Collection.T)
	// CTOR Set(Map.K)
	// CTOR Set(OclElement)
	// CTOR Set(OclSelf)
	// CTOR Set(OclSelf[*|?])
	// CTOR Set(flatten.T2)
	// CTOR Set(selectByKind.TT)
	// CTOR Set(selectByType.TT)
	// CTOR Set(Set.T)
	// CTOR Set(UniqueCollection.T)
	// PROPERTIES CollectionType
	// PROPERTIES MapType
	// PROPERTIES OclInvalid

	// CTOR OclElement::OclElement
	// CTOR Lambda T() : Set(Bag.T)
	// CTOR Lambda T() : OrderedSet(OrderedSet.T)
	// CTOR Lambda T() : OrderedSet(Sequence.T)
	// CTOR Lambda T() : Set(Set.T)
	// OPERATIONS OrderedSet(Collection.T)
	// OPERATIONS OrderedSet(flatten.T2)
	// OPERATIONS OrderedSet(selectByKind.TT)
	// OPERATIONS OrderedSet(selectByType.TT)
	// OPERATIONS OrderedSet(OrderedSet.T)
	// OPERATIONS OrderedSet(Sequence.T)
	// OPERATIONS OrderedSet(Set.T)
	// OPERATIONS OrderedSet(UniqueCollection.T)
	// OPERATIONS Sequence(Integer)
	// OPERATIONS Sequence(String)
	// OPERATIONS Sequence(Bag.T)
	// OPERATIONS Sequence(Collection.T)
	// OPERATIONS Sequence(collectNested.V)
	// OPERATIONS Sequence(collect.V)
	// OPERATIONS Sequence(collectNested.V)
	// OPERATIONS Sequence(collect.V)
	// OPERATIONS Sequence(flatten.T2)
	// OPERATIONS Sequence(selectByKind.TT)
	// OPERATIONS Sequence(selectByType.TT)
	// OPERATIONS Sequence(Sequence.T)
	// OPERATIONS Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// OPERATIONS Set(Bag.T)
	// OPERATIONS Set(Collection.T)
	// OPERATIONS Set(Map.K)
	// OPERATIONS Set(OclElement)
	// OPERATIONS Set(OclSelf)
	// OPERATIONS Set(OclSelf[*|?])
	// OPERATIONS Set(flatten.T2)
	// OPERATIONS Set(selectByKind.TT)
	// OPERATIONS Set(selectByType.TT)
	// OPERATIONS Set(Set.T)
	// OPERATIONS Set(UniqueCollection.T)
	// PROPERTIES OrderedSet(Collection.T)
	// PROPERTIES OrderedSet(flatten.T2)
	// PROPERTIES OrderedSet(selectByKind.TT)
	// PROPERTIES OrderedSet(selectByType.TT)
	// PROPERTIES OrderedSet(OrderedSet.T)
	// PROPERTIES OrderedSet(Sequence.T)
	// PROPERTIES OrderedSet(Set.T)
	// PROPERTIES OrderedSet(UniqueCollection.T)
	// PROPERTIES Sequence(Integer)
	// PROPERTIES Sequence(String)
	// PROPERTIES Sequence(Bag.T)
	// PROPERTIES Sequence(Collection.T)
	// PROPERTIES Sequence(collectNested.V)
	// PROPERTIES Sequence(collect.V)
	// PROPERTIES Sequence(collectNested.V)
	// PROPERTIES Sequence(collect.V)
	// PROPERTIES Sequence(flatten.T2)
	// PROPERTIES Sequence(selectByKind.TT)
	// PROPERTIES Sequence(selectByType.TT)
	// PROPERTIES Sequence(Sequence.T)
	// PROPERTIES Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// PROPERTIES Set(Bag.T)
	// PROPERTIES Set(Collection.T)
	// PROPERTIES Set(Map.K)
	// PROPERTIES Set(OclElement)
	// PROPERTIES Set(OclSelf)
	// PROPERTIES Set(OclSelf[*|?])
	// PROPERTIES Set(flatten.T2)
	// PROPERTIES Set(selectByKind.TT)
	// PROPERTIES Set(selectByType.TT)
	// PROPERTIES Set(Set.T)
	// PROPERTIES Set(UniqueCollection.T)
	// SUPER_CLASSES OrderedSet(Collection.T)
	// SUPER_CLASSES OrderedSet(flatten.T2)
	// SUPER_CLASSES OrderedSet(selectByKind.TT)
	// SUPER_CLASSES OrderedSet(selectByType.TT)
	// SUPER_CLASSES OrderedSet(OrderedSet.T)
	// SUPER_CLASSES OrderedSet(Sequence.T)
	// SUPER_CLASSES OrderedSet(Set.T)
	// SUPER_CLASSES OrderedSet(UniqueCollection.T)
	// SUPER_CLASSES Sequence(Integer)
	// SUPER_CLASSES Sequence(String)
	// SUPER_CLASSES Sequence(Bag.T)
	// SUPER_CLASSES Sequence(Collection.T)
	// SUPER_CLASSES Sequence(collectNested.V)
	// SUPER_CLASSES Sequence(collect.V)
	// SUPER_CLASSES Sequence(collectNested.V)
	// SUPER_CLASSES Sequence(collect.V)
	// SUPER_CLASSES Sequence(flatten.T2)
	// SUPER_CLASSES Sequence(selectByKind.TT)
	// SUPER_CLASSES Sequence(selectByType.TT)
	// SUPER_CLASSES Sequence(Sequence.T)
	// SUPER_CLASSES Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// SUPER_CLASSES Set(Bag.T)
	// SUPER_CLASSES Set(Collection.T)
	// SUPER_CLASSES Set(Map.K)
	// SUPER_CLASSES Set(OclElement)
	// SUPER_CLASSES Set(OclSelf)
	// SUPER_CLASSES Set(OclSelf[*|?])
	// SUPER_CLASSES Set(flatten.T2)
	// SUPER_CLASSES Set(selectByKind.TT)
	// SUPER_CLASSES Set(selectByType.TT)
	// SUPER_CLASSES Set(Set.T)
	// SUPER_CLASSES Set(UniqueCollection.T)
	// TYPE Bag(T).sortedBy(Bag.T[?] | Lambda T() : OclAny[?]) : Sequence(Bag.T)
	// TYPE Collection(T).sortedBy(Collection.T[?] | Lambda T() : OclAny[?]) : Sequence(Collection.T)
	// TYPE OrderedSet(T).collectNested(V)(OrderedSet.T[?] | Lambda T() : V[?]) : Sequence(collectNested.V)
	// TYPE OrderedSet(T).collect(V)(OrderedSet.T[?] | Lambda T() : V[?]) : Sequence(collect.V)
	// TYPE OrderedSet(T).reject(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T).sortedBy(OrderedSet.T[?] | Lambda T() : OclAny[?]) : OrderedSet(OrderedSet.T)
	// TYPE Sequence(T).collectNested(V)(Sequence.T[?] | Lambda T() : V[?]) : Sequence(collectNested.V)
	// TYPE Sequence(T).collect(V)(Sequence.T[?] | Lambda T() : V[?]) : Sequence(collect.V)
	// TYPE Sequence(T).reject(Sequence.T[?] | Lambda T() : Boolean[1]) : Sequence(Sequence.T)
	// TYPE Sequence(T).select(Sequence.T[?] | Lambda T() : Boolean[1]) : Sequence(Sequence.T)
	// TYPE Sequence(T).sortedBy(Sequence.T[?] | Lambda T() : OclAny[?]) : Sequence(Sequence.T)
	// TYPE Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(Set.T)
	// TYPE Set(T).select(Set.T[?] | Lambda T() : Boolean[1]) : Set(Set.T)
	// TYPE Set(T).sortedBy(Set.T[?] | Lambda T() : OclAny[?]) : OrderedSet(Set.T)
	// TYPE UniqueCollection(T).sortedBy(UniqueCollection.T[?] | Lambda T() : OclAny[?]) : OrderedSet(UniqueCollection.T)
	// TYPE Boolean::allInstances(Integer[1]) : Set(OclSelf)
	// TYPE String::characters() : Sequence(String)
	// TYPE String::tokenize() : Sequence(String)
	// TYPE String::tokenize(String[1]) : Sequence(String)
	// TYPE String::tokenize(String[1],Boolean[1]) : Sequence(String)
	// TYPE BooleanType::allInstances() : Set(OclSelf)
	// TYPE Class::allInstances() : Set(OclSelf)
	// TYPE Collection(T)::asOrderedSet() : OrderedSet(Collection.T)
	// TYPE Collection(T)::asSequence() : Sequence(Collection.T)
	// TYPE Collection(T)::asSet() : Set(Collection.T)
	// TYPE Collection(T)::intersection(UniqueCollection(Collection.T)) : Set(Collection.T)
	// TYPE Collection(T)::product(T2)(Collection(product.T2)) : Set(Tuple(first:Collection.T[1],second:product.T2[1]))
	// TYPE Enumeration::allInstances() : Set(OclSelf)
	// TYPE InvalidType::allInstances() : Set(OclSelf)
	// TYPE Map(K,V)::keys() : Set(Map.K)
	// TYPE OclAny::oclAsSet() : Set(OclSelf)
	// TYPE OclAny::oclTypes() : Set(OclSelf)
	// TYPE OclElement::allInstances(Integer[1]) : Set(OclSelf)
	// TYPE OclElement::oclContents() : Set(OclElement)
	// TYPE OclElement::oclExtensions() : Set(OclElement)
	// TYPE OclElement::oclExtensions(OclStereotype[1]) : Set(OclElement)
	// TYPE OclElement::oclModelTypes() : Set(OclSelf)
	// TYPE OclEnumeration::allInstances(Integer[1]) : Set(OclSelf)
	// TYPE OclInvalid::allInstances(Integer[1]) : Set(OclSelf)
	// TYPE OclInvalid::oclAsSet() : Set(OclSelf)
	// TYPE OclStereotype::allInstances(Integer[1]) : Set(OclSelf)
	// TYPE OclVoid::allInstances(Integer[1]) : Set(OclSelf[*|?])
	// TYPE OclVoid::oclAsSet() : Set(OclSelf)
	// TYPE OclVoid::oclTypes() : Set(OclSelf)
	// TYPE OrderedSet(T)::-(UniqueCollection(OclAny)) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::append(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::appendAll(OrderedCollection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::excluding(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::excludingAll(Collection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::flatten(T2)() : OrderedSet(flatten.T2)
	// TYPE OrderedSet(T)::including(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::includingAll(Collection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::insertAt(Integer[1],OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::prepend(OrderedSet.T[?]) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::prependAll(OrderedCollection(OrderedSet.T)) : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::reverse() : OrderedSet(OrderedSet.T)
	// TYPE OrderedSet(T)::selectByKind(TT)(selectByKind.TT[1]) : OrderedSet(selectByKind.TT)
	// TYPE OrderedSet(T)::selectByType(TT)(selectByType.TT[1]) : OrderedSet(selectByType.TT)
	// TYPE OrderedSet(T)::subOrderedSet(Integer[1],Integer[1]) : OrderedSet(OrderedSet.T)
	// TYPE Sequence(T)::append(Sequence.T[?]) : Sequence(Sequence.T)
	// TYPE Sequence(T)::appendAll(OrderedCollection(Sequence.T)) : Sequence(Sequence.T)
	// TYPE Sequence(T)::excluding(Sequence.T[?]) : Sequence(Sequence.T)
	// TYPE Sequence(T)::excludingAll(Collection(Sequence.T)) : Sequence(Sequence.T)
	// TYPE Sequence(T)::flatten(T2)() : Sequence(flatten.T2)
	// TYPE Sequence(T)::including(Sequence.T[?]) : Sequence(Sequence.T)
	// TYPE Sequence(T)::includingAll(Collection(Sequence.T)) : Sequence(Sequence.T)
	// TYPE Sequence(T)::insertAt(Integer[1],Sequence.T[?]) : Sequence(Sequence.T)
	// TYPE Sequence(T)::prepend(Sequence.T[?]) : Sequence(Sequence.T)
	// TYPE Sequence(T)::prependAll(OrderedCollection(Sequence.T)) : Sequence(Sequence.T)
	// TYPE Sequence(T)::reverse() : Sequence(Sequence.T)
	// TYPE Sequence(T)::selectByKind(TT)(selectByKind.TT[1]) : Sequence(selectByKind.TT)
	// TYPE Sequence(T)::selectByType(TT)(selectByType.TT[1]) : Sequence(selectByType.TT)
	// TYPE Sequence(T)::subSequence(Integer[1],Integer[1]) : Sequence(Sequence.T)
	// TYPE Set(T)::-(UniqueCollection(OclAny)) : Set(Set.T)
	// TYPE Set(T)::excluding(Set.T[?]) : Set(Set.T)
	// TYPE Set(T)::excludingAll(Collection(Set.T)) : Set(Set.T)
	// TYPE Set(T)::flatten(T2)() : Set(flatten.T2)
	// TYPE Set(T)::including(Set.T[?]) : Set(Set.T)
	// TYPE Set(T)::includingAll(Collection(Set.T)) : Set(Set.T)
	// TYPE Set(T)::selectByKind(TT)(selectByKind.TT[1]) : Set(selectByKind.TT)
	// TYPE Set(T)::selectByType(TT)(selectByType.TT[1]) : Set(selectByType.TT)
	// TYPE Stereotype::allInstances() : Set(OclSelf)
	// TYPE UniqueCollection(T)::intersection(Collection(UniqueCollection.T)) : Set(UniqueCollection.T)
	// TYPE UniqueCollection(T)::symmetricDifference(UniqueCollection(OclAny)) : Set(UniqueCollection.T)
	// TYPE UniqueCollection(T)::union(UniqueCollection(UniqueCollection.T)) : Set(UniqueCollection.T)
	// TYPE VoidType::allInstances() : Set(OclSelf[*|?])

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
			OCLstdlibTables.Operations._Bag__closure /* closure(T[1]|Lambda T() : Set(T)[?]) */,
			OCLstdlibTables.Operations._Bag__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Bag__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Bag__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Bag__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Bag__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Bag__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Bag__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Bag__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Bag__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Bag__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */
		};
		private static final @NonNull Operation @NonNull [] _Bag__Collection = {
			OCLstdlibTables.Operations._Bag___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Bag___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Bag__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Bag__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Bag__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Bag__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Bag__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Bag__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Bag__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Bag__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Bag__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Bag__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Bag__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Bag__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._Boolean__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Collection__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._Map__any /* any(K[1]|Lambda K() : Boolean[1]) */,
			OCLstdlibTables.Operations._Map__at /* at(K[?]) */,
			OCLstdlibTables.Operations._Map__collect /* collect(V2)(K[?]|Lambda K() : V2[?]) */,
			OCLstdlibTables.Operations._Map__collectBy /* collectBy(V2)(K[?]|Lambda K() : V2[?]) */,
			OCLstdlibTables.Operations._Map__collectNested /* collectNested(V2)(K[?]|Lambda K() : V2[?]) */,
			OCLstdlibTables.Operations._Map__0_excludes /* excludes(K[?]) */,
			OCLstdlibTables.Operations._Map__1_excludes /* excludes(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__excludesAll /* excludesAll(K2)(Collection(K2)) */,
			OCLstdlibTables.Operations._Map__excludesMap /* excludesMap(K2, V2)(Map(K2[1],V2[1])[1]) */,
			OCLstdlibTables.Operations._Map__excludesValue /* excludesValue(V[?]) */,
			OCLstdlibTables.Operations._Map__0_excluding /* excluding(K[?]) */,
			OCLstdlibTables.Operations._Map__1_excluding /* excluding(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__excludingAll /* excludingAll(Collection(K)) */,
			OCLstdlibTables.Operations._Map__excludingMap /* excludingMap(K2, V2)(Map(K2[1],V2[1])[1]) */,
			OCLstdlibTables.Operations._Map__2_exists /* exists(K[?],K[?],K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__1_exists /* exists(K[?],K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__0_exists /* exists(K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__2_forAll /* forAll(K[?],K[?],K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__1_forAll /* forAll(K[?],K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__0_forAll /* forAll(K[?]|Lambda K() : Boolean[?]) */,
			OCLstdlibTables.Operations._Map__0_includes /* includes(K[?]) */,
			OCLstdlibTables.Operations._Map__1_includes /* includes(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__includesAll /* includesAll(K2)(Collection(K2)) */,
			OCLstdlibTables.Operations._Map__includesMap /* includesMap(K2, V2)(Map(K2[1],V2[1])[1]) */,
			OCLstdlibTables.Operations._Map__includesValue /* includesValue(V[?]) */,
			OCLstdlibTables.Operations._Map__including /* including(K[?],V[?]) */,
			OCLstdlibTables.Operations._Map__includingMap /* includingMap(K2, V2)(Map(K2[1],V2[1])[1]) */,
			OCLstdlibTables.Operations._Map__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Map__isUnique /* isUnique(K[?]|Lambda K() : OclAny[?]) */,
			OCLstdlibTables.Operations._Map__iterate /* iterate(Tacc)(K[?];Tacc[?]|Lambda K() : Tacc[?]) */,
			OCLstdlibTables.Operations._Map__keys /* keys() */,
			OCLstdlibTables.Operations._Map__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Map__one /* one(K[?]|Lambda K() : Boolean[1]) */,
			OCLstdlibTables.Operations._Map__reject /* reject(K[?]|Lambda K() : Boolean[1]) */,
			OCLstdlibTables.Operations._Map__select /* select(K[?]|Lambda K() : Boolean[1]) */,
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
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull Operation @NonNull [] _OclEnumeration__OclEnumeration = {
			OCLstdlibTables.Operations._OclEnumeration__allInstances /* allInstances(Integer[1]) */
		};
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
			OCLstdlibTables.Operations._OclEnumeration__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._OclInvalid__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._OclInvalid__allInstances /* allInstances(Integer[1]) */,
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

		private static final @NonNull Operation @NonNull [] _OclStereotype__OclStereotype = {
			OCLstdlibTables.Operations._OclStereotype__allInstances /* allInstances(Integer[1]) */
		};
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
			OCLstdlibTables.Operations._OclStereotype__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._OclVoid__allInstances /* allInstances(Integer[1]) */,
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
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};
		private static final @NonNull Operation @NonNull [] _OrderedCollection__Collection = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Collection__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._OrderedSet__appendAll /* appendAll(OrderedCollection(T)) */,
			OCLstdlibTables.Operations._OrderedSet__closure /* closure(T[1]|Lambda T() : OrderedSet(T)[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._OrderedSet__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._OrderedSet__including /* including(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._OrderedSet__insertAt /* insertAt(Integer[1],T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__prepend /* prepend(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__prependAll /* prependAll(OrderedCollection(T)) */,
			OCLstdlibTables.Operations._OrderedSet__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._OrderedSet__reverse /* reverse() */,
			OCLstdlibTables.Operations._OrderedSet__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._OrderedSet__subOrderedSet /* subOrderedSet(Integer[1],Integer[1]) */
		};
		private static final @NonNull Operation @NonNull [] _OrderedSet__Collection = {
			OCLstdlibTables.Operations._OrderedSet___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OrderedSet___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._OrderedSet__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._OrderedSet__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._OrderedSet__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._OrderedSet__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._OrderedSet__including /* including(T[?]) */,
			OCLstdlibTables.Operations._OrderedSet__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._OrderedSet__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._OrderedSet__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._OrderedSet__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};
		private static final @NonNull Operation @NonNull [] _OrderedSet__UniqueCollection = {
			OCLstdlibTables.Operations._OrderedSet___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._OrderedSet__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection(T)) */
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
			OCLstdlibTables.Operations._Sequence__appendAll /* appendAll(OrderedCollection(T)) */,
			OCLstdlibTables.Operations._Sequence__closure /* closure(T[1]|Lambda T() : OrderedSet(T)[?]) */,
			OCLstdlibTables.Operations._Sequence__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Sequence__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Sequence__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Sequence__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Sequence__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Sequence__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Sequence__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Sequence__insertAt /* insertAt(Integer[1],T[?]) */,
			OCLstdlibTables.Operations._Sequence__prepend /* prepend(T[?]) */,
			OCLstdlibTables.Operations._Sequence__prependAll /* prependAll(OrderedCollection(T)) */,
			OCLstdlibTables.Operations._Sequence__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Sequence__reverse /* reverse() */,
			OCLstdlibTables.Operations._Sequence__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Sequence__subSequence /* subSequence(Integer[1],Integer[1]) */
		};
		private static final @NonNull Operation @NonNull [] _Sequence__Collection = {
			OCLstdlibTables.Operations._Sequence___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Sequence___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Sequence__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Sequence__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Sequence__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Sequence__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Sequence__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Sequence__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Sequence__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__0_intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Sequence__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Sequence__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Sequence__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Sequence__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._OrderedCollection__indexOf /* indexOf(T[?]) */,
			OCLstdlibTables.Operations._OrderedCollection__last /* last() */
		};

		private static final @NonNull Operation @NonNull [] _Set__Set = {
			OCLstdlibTables.Operations._Set___sub_ /* _'-'(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._Set___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set__closure /* closure(T[1]|Lambda T() : Set(T)[?]) */,
			OCLstdlibTables.Operations._Set__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Set__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Set__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Set__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Set__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Set__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Set__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Set__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Set__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Set__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */
		};
		private static final @NonNull Operation @NonNull [] _Set__Collection = {
			OCLstdlibTables.Operations._Set___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Set___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Set__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Set__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Set__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Set__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Set__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Set__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Set__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Set__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Set__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Set__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Set__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Set__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection(T)) */
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
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._UniqueCollection__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._UniqueCollection__symmetricDifference /* symmetricDifference(UniqueCollection(OclAny)) */,
			OCLstdlibTables.Operations._UniqueCollection__union /* union(UniqueCollection(T)) */
		};
		private static final @NonNull Operation @NonNull [] _UniqueCollection__Collection = {
			OCLstdlibTables.Operations._Collection___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._Collection__any /* any(T[1]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__asBag /* asBag() */,
			OCLstdlibTables.Operations._Collection__asOrderedSet /* asOrderedSet() */,
			OCLstdlibTables.Operations._Collection__asSequence /* asSequence() */,
			OCLstdlibTables.Operations._Collection__asSet /* asSet() */,
			OCLstdlibTables.Operations._Collection__collect /* collect(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectBy /* collectBy(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__collectNested /* collectNested(V)(T[?]|Lambda T() : V[?]) */,
			OCLstdlibTables.Operations._Collection__count /* count(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludes /* excludes(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludesAll /* excludesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__excluding /* excluding(T[?]) */,
			OCLstdlibTables.Operations._Collection__excludingAll /* excludingAll(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__2_exists /* exists(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_exists /* exists(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_exists /* exists(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__flatten /* flatten(T2)() */,
			OCLstdlibTables.Operations._Collection__2_forAll /* forAll(T[?],T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__1_forAll /* forAll(T[?],T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__0_forAll /* forAll(T[?]|Lambda T() : Boolean[?]) */,
			OCLstdlibTables.Operations._Collection__includes /* includes(T[?]) */,
			OCLstdlibTables.Operations._Collection__includesAll /* includesAll(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__including /* including(T[?]) */,
			OCLstdlibTables.Operations._Collection__includingAll /* includingAll(Collection(T)) */,
			OCLstdlibTables.Operations._UniqueCollection__intersection /* intersection(Collection(T)) */,
			OCLstdlibTables.Operations._Collection__1_intersection /* intersection(UniqueCollection(T)) */,
			OCLstdlibTables.Operations._Collection__isEmpty /* isEmpty() */,
			OCLstdlibTables.Operations._Collection__isUnique /* isUnique(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__iterate /* iterate(Tacc)(T[?];Tacc[?]|Lambda T() : Tacc[?]) */,
			OCLstdlibTables.Operations._Collection__max /* max() */,
			OCLstdlibTables.Operations._Collection__min /* min() */,
			OCLstdlibTables.Operations._Collection__notEmpty /* notEmpty() */,
			OCLstdlibTables.Operations._Collection__one /* one(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__product /* product(T2)(Collection(T2)) */,
			OCLstdlibTables.Operations._Collection__reject /* reject(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__select /* select(T[?]|Lambda T() : Boolean[1]) */,
			OCLstdlibTables.Operations._Collection__selectByKind /* selectByKind(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__selectByType /* selectByType(TT)(TT[1]) */,
			OCLstdlibTables.Operations._Collection__size /* size() */,
			OCLstdlibTables.Operations._UniqueCollection__sortedBy /* sortedBy(T[?]|Lambda T() : OclAny[?]) */,
			OCLstdlibTables.Operations._Collection__sum /* sum() */,
			OCLstdlibTables.Operations._Collection__union /* union(Collection(T)) */
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

	// OPERATIONS Lambda T() : Set(Bag.T)
	// OPERATIONS Lambda T() : OrderedSet(OrderedSet.T)
	// OPERATIONS Lambda T() : OrderedSet(Sequence.T)
	// OPERATIONS Lambda T() : Set(Set.T)
	// PROPERTIES OclElement
	// PROPERTIES Lambda T() : Set(Bag.T)
	// PROPERTIES Lambda T() : OrderedSet(OrderedSet.T)
	// PROPERTIES Lambda T() : OrderedSet(Sequence.T)
	// PROPERTIES Lambda T() : Set(Set.T)
	// SUPER_CLASSES Lambda T() : Set(Bag.T)
	// SUPER_CLASSES Lambda T() : OrderedSet(OrderedSet.T)
	// SUPER_CLASSES Lambda T() : OrderedSet(Sequence.T)
	// SUPER_CLASSES Lambda T() : Set(Set.T)
	// TYPE Bag(T).closure(Bag.T[1] | Lambda T() : Set(Bag.T)[?]) : Set(Bag.T)
	// TYPE OrderedSet(T).closure(OrderedSet.T[1] | Lambda T() : OrderedSet(OrderedSet.T)[?]) : OrderedSet(OrderedSet.T)
	// TYPE Sequence(T).closure(Sequence.T[1] | Lambda T() : OrderedSet(Sequence.T)[?]) : OrderedSet(Sequence.T)
	// TYPE Set(T).closure(Set.T[1] | Lambda T() : Set(Set.T)[?]) : Set(Set.T)

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final @NonNull Property @NonNull [] _Bag = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _Boolean = {};

		private static final @NonNull Property @NonNull [] _Collection = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _Integer = {};

		private static final @NonNull Property @NonNull [] _Map = {
			OCLstdlibTables.Properties._Map__keyType,
			OCLstdlibTables.Properties._Map__valueType
		};

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

		private static final @NonNull Property @NonNull [] _OrderedCollection = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _OrderedSet = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _Real = {};

		private static final @NonNull Property @NonNull [] _Sequence = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _Set = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

		private static final @NonNull Property @NonNull [] _String = {};

		private static final @NonNull Property @NonNull [] _UniqueCollection = {
			OCLstdlibTables.Properties._Collection__elementType,
			OCLstdlibTables.Properties._Collection__lower,
			OCLstdlibTables.Properties._Collection__upper
		};

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
	//	OrphanageImpl.checkSuperClasses = true;				// XXX
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
