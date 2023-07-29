/*******************************************************************************
 * Copyright (c) 2010, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************
 * This code is 100% auto-generated
 * from: /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
 * by: org.eclipse.ocl.examples.build.xtend.generateOCLstdlib.xtend
 * and: org.eclipse.ocl.examples.build.GenerateOCLstdlibModel.mwe2
 *
 * Do not edit it.
 *******************************************************************************/
package	org.eclipse.ocl.pivot.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * This is the http://www.eclipse.org/ocl/2015/Library Standard Library
 * auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib.
 * It facilitates efficient library loading without the overheads of model reading.
 * <p>
 * This Standard Library may be registered as the definition of a Standard Library for
 * the OCL evaluation framework by invoking {@link #install}.
 * <p>
 * The Standard Library is normally activated when the MetamodelManager attempts
 * to locate a library type when its default Standard Library URI is the same
 * as this Standard Library.
 */
@SuppressWarnings("unused")
public class OCLstdlib extends ASResourceImpl
{
	/**
	 *	The static package-of-types pivot model of the Standard Library.
	 */
//	private static OCLstdlib INSTANCE = null;

	/**
	 *	The URI of this Standard Library.
	 */
	public static final @NonNull String STDLIB_URI = "http://www.eclipse.org/ocl/2015/Library";

	/**
	 *	The URI of the AS representation of this Standard Library.
	 */
	public static final @NonNull URI STDLIB_AS_URI = URI.createURI("http://www.eclipse.org/ocl/2015/Library" + PivotConstants.DOT_OCL_AS_FILE_EXTENSION);

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/Library standard Library Resource
	 * if it jas been created, or null if not.
	 *  This static definition auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 * It cannot be unloaded or rather unloading has no effect.
	 */
//	public static @Nullable OCLstdlib basicGetDefault() {
//		return INSTANCE;
//	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/Library standard Library Resource.
	 *  This static definition auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 * It cannot be unloaded or rather unloading has no effect.
	 */
	public static @NonNull OCLstdlib getDefault() {
//		OCLstdlib oclstdlib = INSTANCE;
//		if (oclstdlib == null) {
			String asURI = STDLIB_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
			OCLstdlib oclstdlib = new ReadOnly(asURI);
			Contents contents = new Contents(oclstdlib, "http://www.eclipse.org/ocl/2015/Library");
			oclstdlib.setSaveable(false);
//		}
		return oclstdlib;
	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/Library standard Library model.
	 *  This static definition auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 */
	public static @NonNull Model getDefaultModel() {
		Model model = (Model)(getDefault().getContents().get(0));
		assert model != null;
		return model;
	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/Library standard Library package.
	 *  This static definition auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 */
	public static @NonNull Package getDefaultPackage() {
		Package pkge = getDefaultModel().getOwnedPackages().get(0);
		assert pkge != null;
		return pkge;
	}

	/**
	 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
	 * and the {@link OCLASResourceFactory#REGISTRY}.
	 * This method may be invoked by standalone applications to replicate
	 * the registration that should appear as a standard_library plugin
	 * extension when running within Eclipse.
	 */
	public static void install() {
		EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, OCLstdlibPackage.eINSTANCE);
		Loader contribution = new Loader();
		StandardLibraryContribution.REGISTRY.put(STDLIB_URI, contribution);
		OCLASResourceFactory.REGISTRY.put(STDLIB_AS_URI, contribution);
	}

	/**
	 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
	 * and the {@link OCLASResourceFactory#REGISTRY}
	 * unless some other library contribution has already been installed.
	 */
	public static void lazyInstall() {
		if (StandardLibraryContribution.REGISTRY.get(STDLIB_URI) == null) {
			install();
		}
	}

	/**
	 * Uninstall this library from the {@link StandardLibraryContribution#REGISTRY}
	 * and the {@link OCLASResourceFactory#REGISTRY}.
	 * This method may be invoked by standalone applications to release the library
	 * resources for garbage collection and memory leakage detection.
	 */
	public static void uninstall() {
		StandardLibraryContribution.REGISTRY.remove(STDLIB_URI);
		OCLASResourceFactory.REGISTRY.remove(STDLIB_AS_URI);
	//	INSTANCE = null;
	}

	/**
	 * The Loader shares the Standard Library instance whenever this default library
	 * is loaded from the registry of Standard Libraries populated by the standard_library
	 * extension point.
	 */
	public static class Loader implements StandardLibraryContribution
	{
		@Override
		public @NonNull StandardLibraryContribution getContribution() {
			return this;
		}

		@Override
		public @NonNull Resource getResource() {
			return getDefault();
		}
	}

	/**
	 * A ReadOnly OCLstdlib overrides inherited functionality to impose immutable shared behaviour.
	 */
	protected static class ReadOnly extends OCLstdlib implements ImmutableResource
	{
		protected ReadOnly(@NonNull String asURI) {
			super(asURI);
		}

		/**
		 * Overridden to inhibit entry of the shared instance in any ResourceSet.
		 */
		@Override
		public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
			return notifications;
		}

		/**
		 * Overridden to inhibit unloading of the shared instance.
		 */
		@Override
		protected void doUnload() {}

		@Override
		public boolean isCompatibleWith(@NonNull String metamodelURI) {
			return org.eclipse.ocl.pivot.model.OCLmetamodel.PIVOT_URI.equals(metamodelURI);
		}

		/**
		 * Overridden to trivialise loading of the shared instance.
		 */
		@Override
		public void load(Map<?, ?> options) throws IOException {
		//	if (this != INSTANCE) {
				super.load(options);
		//	}
		//	else {
		//		setLoaded(true);
		//	}
		}

		/**
		 * Overridden to inhibit unloading of the shared instance.
		 */
		@Override
		protected Notification setLoaded(boolean isLoaded) {
			if (isLoaded) {
				return super.setLoaded(isLoaded);
			}
			else {
				return null;
			}
		}
	}

	/**
	 *	Construct a copy of the OCL Standard Library with specified resource URI,
	 *  and package name, prefix and namespace URI.
	 */
	public static @NonNull OCLstdlib create(@NonNull String asURI) {
		OCLstdlib oclstdlib = new OCLstdlib(asURI);
		Contents contents = new Contents(oclstdlib, asURI);
		return oclstdlib;
	}

	/**
	 *	Construct an OCL Standard Library with specified resource URI and library content.
	 */
	private OCLstdlib(@NonNull String asURI) {
		super(ClassUtil.nonNullState(URI.createURI(asURI)), OCLASResourceFactory.getInstance());
		assert PivotUtilInternal.isASURI(asURI);
	}

	private static class Contents extends AbstractContents
	{
		private final @NonNull Model model;
		private final @NonNull Library ocl;

		private Contents(@NonNull Resource resource, @NonNull String asURI)
		{
			model = createModel(asURI);
			resource.getContents().add(model);
			ocl = createLibrary("ocl", "ocl", "http://www.eclipse.org/ocl/2015/Library", IdManager.METAMODEL, OCLstdlibPackage.eINSTANCE);
			installComment(ocl, "This clause describes the OCL Standard Library of predefined types, their operations, and predefined expression templates in the OCL.\nThis sub clause contains all standard types defined within OCL, including all the operations defined on those types.\nFor each operation the signature and a description of the semantics is given.\nWithin the description, the reserved word \u2018result\u2019 is used to refer to the value that results from evaluating the operation.\nIn several places, post conditions are used to describe properties of the result.\nWhen there is more than one postcondition, all postconditions must be true.\nA similar thing is true for multiple preconditions.\nIf these are used, the operation is only defined if all preconditions evaluate to oclText[true].\n\nheading:1[Introduction]\n\nThe structure, syntax, and semantics of the OCL is defined in Clauses 8 (\u201CAbstract Syntax\u201D), 9 (\u201CConcrete Syntax\u201D),\nand 10 (\u201CSemantics Described using UML\u201D).\nThis sub clause adds another part to the OCL definition: a library of predefined types and operations.\nAny implementation of OCL must include this library package. This approach has also been taken by e.g., the Java definition,\nwhere the language definition and the standard libraries are both mandatory parts of the complete language definition.\n\nThe OCL standard library defines a number of types.\nIt includes several primitive types: UnlimitedNatural, Integer, Real, String, and Boolean.\nThese are familiar from many other languages. The second part of the standard library consists of the collection types.\nThey are Bag, Set, Sequence, and Collection where Collection is an abstract type.\nNote that all types defined in the OCL standard library are instances of an abstract syntax class.\nThe OCL standard library exists at the modeling level, also referred to as the M1 level, where the abstract syntax is the metalevel or M2 level.\n\nNext to definitions of types the OCL standard library defines a number of template expressions.\nMany operations defined on collections map not on the abstract syntax metaclass FeatureCallExp, but on the IteratorExp.\nFor each of these a template expression that defines the name and format of the expression is defined in 11.8, Predefined Iterator Expressions.\n\nThe Standard Library may be extended with new types, new operations and new iterators.\nIn particular new operations can be defined for collections.\n\nCertain String operations depend on the prevailing locale to ensure that Strings are collated and characters are case-converted\nin an appropriate fashion.\nA locale is defined as a concatenation of up to three character sequences separated by underscores,\nwith the first sequence identifying the language and the second sequence identifying the country.\nThe third sequence is empty but may encode an implementation-specific variant.\nTrailing empty strings and separators may be omitted.\n\nThe character sequences for languages are defined by ISO 639.\n\nThe character sequences for countries are defined by ISO 3166.\n\n\u2018fr_CA\u2019 therefore identifies the locale for the French language in the Canada country.\n\nComparison of strings and consequently the collation order of Collection::sortedBy()\nconforms to the Unicode Collation algorithm defined by Unicode Technical Standard#10.\n\nThe locale is \u2018en_us\u2019 by default but may be configured by a property constraint on OclAny::oclLocale.\n\nThe prevailing locale is defined by the prevailing value of oclLocale within the current environment;\nit may therefore be changed temporarily by using a Let expression.\nlet oclLocale : String = \u2018fr_CA\u2019 in aString.toUpperCase()\n\nheading:1[Iterators]\n\nThis sub clause defines the standard OCL iterator expressions.\nIn the abstract syntax these are all instances of IteratorExp.\nThese iterator expressions always have a collection expression as their source,\nas is defined in the well-formedness rules in Clause 8 (\u201CAbstract Syntax\u201D).\nThe defined iterator expressions are shown per source collection type.\nThe semantics of each iterator expression is defined through a mapping from the iterator to the \u2018iterate\u2019 construct.\nThis means that the semantics of the iterator expressions do not need to be defined separately in the semantics sub clauses.\n\nIn all of the following OCL expressions, the lefthand side of the equals sign is the IteratorExp to be defined,\nand the righthand side of the equals sign is the equivalent as an IterateExp.\nThe names source, body, and iterator refer to the role names in the abstract syntax:\n\nsource\tThe source expression of the IteratorExp.\n\nbody\tThe body expression of the IteratorExp.\n\niterator\tThe iterator variable of the IteratorExp.\n\nresult\tThe result variable of the IterateExp.\n\nheading:2[Extending the Standard Library with Iterator Expressions]\n\nIt is possible to add new iterator expressions in the standard library.\nIf this is done the semantics of a new iterator should be defined by mapping it to existing constructs,\nin the same way the semantics of pre-defined iterators is done (see sub clause 11.9)");
			installPackages();
			installPrecedences();
			installSlots0();
			installSlots1();
			installSlots2();
			installSlots3a();
			installSlots3b();
			installSlots3c();
			installSlots3d();
			installSlots3e();
			installSlots4a();
			installSlots4b();
			installSlots4c();
			installSlots4d();
			installSlots4e();
			installSlots4f();
			installSlots5a();
			installSlots5b();
			installSlots6a();
			installSlots6b();
			installSlots6c();
			installSlots7a();
			installSlots7b();
			installSlots8();
			installSlots9a();
			installSlots9b();
			installSlots10();
			installSlots11();
			installSlots12();
			installProperties();
		}

		public @NonNull Model getModel() {
			return model;
		}

		private void installPackages() {
			model.getOwnedPackages().add(ocl);
		//	model.getOwnedPackages().add(orphanage);
		}

		private Precedence prec_ADDITIVE;
		private Precedence prec_AND;
		private Precedence prec_EQUALITY;
		private Precedence prec_IMPLIES;
		private Precedence prec_MULTIPLICATIVE;
		private Precedence prec_NAVIGATION;
		private Precedence prec_OR;
		private Precedence prec_RELATIONAL;
		private Precedence prec_UNARY;
		private Precedence prec_XOR;

		private void installPrecedences() {
			List<Precedence> ownedPrecedences;

			ownedPrecedences = ocl.getOwnedPrecedences();
			ownedPrecedences.add(prec_NAVIGATION = createPrecedence("NAVIGATION", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_UNARY = createPrecedence("UNARY", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_MULTIPLICATIVE = createPrecedence("MULTIPLICATIVE", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_ADDITIVE = createPrecedence("ADDITIVE", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_RELATIONAL = createPrecedence("RELATIONAL", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_EQUALITY = createPrecedence("EQUALITY", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_AND = createPrecedence("AND", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_OR = createPrecedence("OR", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_XOR = createPrecedence("XOR", AssociativityKind.LEFT));
			ownedPrecedences.add(prec_IMPLIES = createPrecedence("IMPLIES", AssociativityKind.LEFT));
		}

		// declare ALL_FRAGMENTS Model
		// declare ALL_OPERATIONS Model
		// declare ALL_PROPERTIES Model
		// declare ALL_TYPES Model
		private PrimitiveType _Boolean;
		// declare CTOR Model
		// declare CTOR Library
		// declare CTOR Package
		private PrimitiveType _Integer;
		private PrimitiveType _Real;
		private PrimitiveType _String;
		private PrimitiveType _UnlimitedNatural;
		private Class _OclAny;
		private Class _BooleanType;
		private Class _Class;
		private Class _CollectionType;
		private Class _Enumeration;
		private Class _EnumerationLiteral;
		private Class _InvalidType;
		private Class _MapType;
		private Class _OclComparable;
		private Class _OclElement;
		private Class _OclEnumeration;
		private Class _OclLambda;
		private Class _OclMessage;
		private Class _OclState;
		private Class _OclStereotype;
		private Class _OclSummable;
		private Class _OclTuple;
		private Class _OclType;
		private Class _State;
		private Class _Stereotype;
		private Class _Type;
		private Class _VoidType;
		private Class _OclInvalid;
		private Class _OclSelf;
		private Class _OclVoid;
		private TemplateParameter tp_UnlimitedNatural_oclAsType_TT;
		private TemplateParameter tp_Bag_collectNested_V;
		private TemplateParameter tp_Bag_collect_V;
		private TemplateParameter tp_Bag_flatten_T2;
		private TemplateParameter tp_Bag_selectByKind_TT;
		private TemplateParameter tp_Bag_selectByType_TT;
		private TemplateParameter tp_Bag_T;
		private TemplateParameter tp_Collection_collectBy_V;
		private TemplateParameter tp_Collection_collectNested_V;
		private TemplateParameter tp_Collection_collect_V;
		private TemplateParameter tp_Collection_excludesAll_T2;
		private TemplateParameter tp_Collection_flatten_T2;
		private TemplateParameter tp_Collection_includesAll_T2;
		private TemplateParameter tp_Collection_iterate_Tacc;
		private TemplateParameter tp_Collection_product_T2;
		private TemplateParameter tp_Collection_selectByKind_TT;
		private TemplateParameter tp_Collection_selectByType_TT;
		private TemplateParameter tp_Collection_T;
		private TemplateParameter tp_Map_collectBy_V2;
		private TemplateParameter tp_Map_collectNested_V2;
		private TemplateParameter tp_Map_collect_V2;
		private TemplateParameter tp_Map_excludesAll_K2;
		private TemplateParameter tp_Map_excludesMap_K2;
		private TemplateParameter tp_Map_excludesMap_V2;
		private TemplateParameter tp_Map_excludingMap_K2;
		private TemplateParameter tp_Map_excludingMap_V2;
		private TemplateParameter tp_Map_includesAll_K2;
		private TemplateParameter tp_Map_includesMap_K2;
		private TemplateParameter tp_Map_includesMap_V2;
		private TemplateParameter tp_Map_includingMap_K2;
		private TemplateParameter tp_Map_includingMap_V2;
		private TemplateParameter tp_Map_iterate_Tacc;
		private TemplateParameter tp_Map_K;
		private TemplateParameter tp_Map_V;
		private TemplateParameter tp_OclAny_oclAsType_TT;
		private TemplateParameter tp_OclElement_oclAsModelType_TT;
		private TemplateParameter tp_OclInvalid_oclAsType_TT;
		private TemplateParameter tp_OclVoid_oclAsType_TT;
		private TemplateParameter tp_OrderedCollection_T;
		private TemplateParameter tp_OrderedSet_collectNested_V;
		private TemplateParameter tp_OrderedSet_collect_V;
		private TemplateParameter tp_OrderedSet_flatten_T2;
		private TemplateParameter tp_OrderedSet_selectByKind_TT;
		private TemplateParameter tp_OrderedSet_selectByType_TT;
		private TemplateParameter tp_OrderedSet_T;
		private TemplateParameter tp_Sequence_collectNested_V;
		private TemplateParameter tp_Sequence_collect_V;
		private TemplateParameter tp_Sequence_flatten_T2;
		private TemplateParameter tp_Sequence_selectByKind_TT;
		private TemplateParameter tp_Sequence_selectByType_TT;
		private TemplateParameter tp_Sequence_T;
		private TemplateParameter tp_Set_collectNested_V;
		private TemplateParameter tp_Set_collect_V;
		private TemplateParameter tp_Set_flatten_T2;
		private TemplateParameter tp_Set_selectByKind_TT;
		private TemplateParameter tp_Set_selectByType_TT;
		private TemplateParameter tp_Set_T;
		private TemplateParameter tp_UniqueCollection_T;
		private CollectionType _Collection_Integer_T;
		private CollectionType _Collection_String_T;
		private CollectionType _Collection_Tuple_T;
		private CollectionType _Collection_Bag_collectNested_V_T;
		private CollectionType _Collection_Bag_collect_V_T;
		private CollectionType _Collection_Bag_flatten_T2_T;
		private CollectionType _Collection_Bag_selectByKind_TT_T;
		private CollectionType _Collection_Bag_selectByType_TT_T;
		private CollectionType _Collection_Bag_T_T;
		private CollectionType _Collection_CollectionType_T;
		private CollectionType _Collection_Collection_collectNested_V_T;
		private CollectionType _Collection_Collection_collect_V_T;
		private CollectionType _Collection_Collection_excludesAll_T2_T;
		private CollectionType _Collection_Collection_flatten_T2_T;
		private CollectionType _Collection_Collection_includesAll_T2_T;
		private CollectionType _Collection_Collection_product_T2_T;
		private CollectionType _Collection_Collection_selectByKind_TT_T;
		private CollectionType _Collection_Collection_selectByType_TT_T;
		private CollectionType _Collection_Collection_T_T;
		private CollectionType _Collection_MapType_T;
		private CollectionType _Collection_Map_collect_V2_T;
		private CollectionType _Collection_Map_excludesAll_K2_T;
		private CollectionType _Collection_Map_includesAll_K2_T;
		private CollectionType _Collection_Map_K_T;
		private CollectionType _Collection_Map_V_T;
		private CollectionType _Collection_OclAny_T;
		private CollectionType _Collection_OclElement_T;
		private CollectionType _Collection_OclInvalid_T;
		private CollectionType _Collection_OclSelf_T;
		private CollectionType _Collection_OrderedCollection_T_T;
		private CollectionType _Collection_OrderedSet_collectNested_V_T;
		private CollectionType _Collection_OrderedSet_collect_V_T;
		private CollectionType _Collection_OrderedSet_flatten_T2_T;
		private CollectionType _Collection_OrderedSet_selectByKind_TT_T;
		private CollectionType _Collection_OrderedSet_selectByType_TT_T;
		private CollectionType _Collection_OrderedSet_T_T;
		private CollectionType _Collection_Sequence_collectNested_V_T;
		private CollectionType _Collection_Sequence_collect_V_T;
		private CollectionType _Collection_Sequence_flatten_T2_T;
		private CollectionType _Collection_Sequence_selectByKind_TT_T;
		private CollectionType _Collection_Sequence_selectByType_TT_T;
		private CollectionType _Collection_Sequence_T_T;
		private CollectionType _Collection_Set_collectNested_V_T;
		private CollectionType _Collection_Set_collect_V_T;
		private CollectionType _Collection_Set_flatten_T2_T;
		private CollectionType _Collection_Set_selectByKind_TT_T;
		private CollectionType _Collection_Set_selectByType_TT_T;
		private CollectionType _Collection_Set_T_T;
		private CollectionType _Collection_UniqueCollection_T_T;
		private CollectionType _Collection_Collection_T;
		private CollectionType _OrderedCollection_Integer_T;
		private CollectionType _OrderedCollection_String_T;
		private CollectionType _OrderedCollection_Bag_T_T;
		private CollectionType _OrderedCollection_Collection_T_T;
		private CollectionType _OrderedCollection_OrderedSet_collectNested_V_T;
		private CollectionType _OrderedCollection_OrderedSet_collect_V_T;
		private CollectionType _OrderedCollection_OrderedSet_flatten_T2_T;
		private CollectionType _OrderedCollection_OrderedSet_selectByKind_TT_T;
		private CollectionType _OrderedCollection_OrderedSet_selectByType_TT_T;
		private CollectionType _OrderedCollection_OrderedSet_T_T;
		private CollectionType _OrderedCollection_Sequence_collectNested_V_T;
		private CollectionType _OrderedCollection_Sequence_collect_V_T;
		private CollectionType _OrderedCollection_Sequence_flatten_T2_T;
		private CollectionType _OrderedCollection_Sequence_selectByKind_TT_T;
		private CollectionType _OrderedCollection_Sequence_selectByType_TT_T;
		private CollectionType _OrderedCollection_Sequence_T_T;
		private CollectionType _OrderedCollection_Set_T_T;
		private CollectionType _OrderedCollection_UniqueCollection_T_T;
		private CollectionType _OrderedCollection_OrderedCollection_T;
		private CollectionType _UniqueCollection_Tuple_T;
		private CollectionType _UniqueCollection_Bag_T_T;
		private CollectionType _UniqueCollection_Collection_T_T;
		private CollectionType _UniqueCollection_Map_K_T;
		private CollectionType _UniqueCollection_OclAny_T;
		private CollectionType _UniqueCollection_OclElement_T;
		private CollectionType _UniqueCollection_OclSelf_T;
		private CollectionType _UniqueCollection_OrderedSet_flatten_T2_T;
		private CollectionType _UniqueCollection_OrderedSet_selectByKind_TT_T;
		private CollectionType _UniqueCollection_OrderedSet_selectByType_TT_T;
		private CollectionType _UniqueCollection_OrderedSet_T_T;
		private CollectionType _UniqueCollection_Sequence_T_T;
		private CollectionType _UniqueCollection_Set_flatten_T2_T;
		private CollectionType _UniqueCollection_Set_selectByKind_TT_T;
		private CollectionType _UniqueCollection_Set_selectByType_TT_T;
		private CollectionType _UniqueCollection_Set_T_T;
		private CollectionType _UniqueCollection_UniqueCollection_T_T;
		private CollectionType _UniqueCollection_UniqueCollection_T;
		private MapType _Map_Collection_T_F_Collection_collectBy_V_F;
		private MapType _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T;
		private MapType _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T;
		private MapType _Map_Map_includesMap_K2_T_Map_includesMap_V2_T;
		private MapType _Map_Map_includingMap_K2_T_Map_includingMap_V2_T;
		private MapType _Map_Map_K_F_Map_collectBy_V2_F;
		private MapType _Map_Map_K_F_Map_collectNested_V2_F;
		private MapType _Map_Map_K_T_Map_V_T;
		private MapType _Map_Map_K_F_Map_V_F;
		private MapType _Map_Map_K_Map_V;
		private CollectionType _Bag_Bag_collectNested_V_T;
		private CollectionType _Bag_Bag_collect_V_T;
		private CollectionType _Bag_Bag_flatten_T2_T;
		private CollectionType _Bag_Bag_selectByKind_TT_T;
		private CollectionType _Bag_Bag_selectByType_TT_T;
		private CollectionType _Bag_Bag_T_T;
		private CollectionType _Bag_CollectionType_T;
		private CollectionType _Bag_Collection_T_T;
		private CollectionType _Bag_MapType_T;
		private CollectionType _Bag_Map_collect_V2_T;
		private CollectionType _Bag_Map_V_T;
		private CollectionType _Bag_OclElement_T;
		private CollectionType _Bag_OclInvalid_T;
		private CollectionType _Bag_Set_collectNested_V_T;
		private CollectionType _Bag_Set_collect_V_T;
		private CollectionType _Bag_Bag_T;
		private CollectionType _OrderedSet_Collection_T_T;
		private CollectionType _OrderedSet_OrderedSet_flatten_T2_T;
		private CollectionType _OrderedSet_OrderedSet_selectByKind_TT_T;
		private CollectionType _OrderedSet_OrderedSet_selectByType_TT_T;
		private CollectionType _OrderedSet_OrderedSet_T_T;
		private CollectionType _OrderedSet_Sequence_T_T;
		private CollectionType _OrderedSet_Set_T_T;
		private CollectionType _OrderedSet_UniqueCollection_T_T;
		private CollectionType _OrderedSet_OrderedSet_T;
		private CollectionType _Sequence_Integer_T;
		private CollectionType _Sequence_String_T;
		private CollectionType _Sequence_Bag_T_T;
		private CollectionType _Sequence_Collection_T_T;
		private CollectionType _Sequence_OrderedSet_collectNested_V_T;
		private CollectionType _Sequence_OrderedSet_collect_V_T;
		private CollectionType _Sequence_Sequence_collectNested_V_T;
		private CollectionType _Sequence_Sequence_collect_V_T;
		private CollectionType _Sequence_Sequence_flatten_T2_T;
		private CollectionType _Sequence_Sequence_selectByKind_TT_T;
		private CollectionType _Sequence_Sequence_selectByType_TT_T;
		private CollectionType _Sequence_Sequence_T_T;
		private CollectionType _Sequence_Sequence_T;
		private CollectionType _Set_Tuple_T;
		private CollectionType _Set_Bag_T_T;
		private CollectionType _Set_Collection_T_T;
		private CollectionType _Set_Map_K_T;
		private CollectionType _Set_OclElement_T;
		private CollectionType _Set_OclSelf_T;
		private CollectionType _Set_OclSelf_F;
		private CollectionType _Set_Set_flatten_T2_T;
		private CollectionType _Set_Set_selectByKind_TT_T;
		private CollectionType _Set_Set_selectByType_TT_T;
		private CollectionType _Set_Set_T_T;
		private CollectionType _Set_UniqueCollection_T_T;
		private CollectionType _Set_Set_T;
		private TupleType _Tuple;
		private LambdaType _Lambda_Bag_T_Boolean;
		private LambdaType _Lambda_Bag_T_Bag_collectNested_V;
		private LambdaType _Lambda_Bag_T_Bag_collect_V;
		private LambdaType _Lambda_Bag_T_OclAny;
		private LambdaType _Lambda_Bag_T_Set;
		private LambdaType _Lambda_Collection_T_Boolean;
		private LambdaType _Lambda_Collection_T_Collection_collectBy_V;
		private LambdaType _Lambda_Collection_T_Collection_collectNested_V;
		private LambdaType _Lambda_Collection_T_Collection_collect_V;
		private LambdaType _Lambda_Collection_T_Collection_iterate_Tacc;
		private LambdaType _Lambda_Collection_T_OclAny;
		private LambdaType _Lambda_Map_K_Boolean;
		private LambdaType _Lambda_Map_K_Map_collectBy_V2;
		private LambdaType _Lambda_Map_K_Map_collectNested_V2;
		private LambdaType _Lambda_Map_K_Map_collect_V2;
		private LambdaType _Lambda_Map_K_Map_iterate_Tacc;
		private LambdaType _Lambda_Map_K_OclAny;
		private LambdaType _Lambda_OrderedSet_T_Boolean;
		private LambdaType _Lambda_OrderedSet_T_OclAny;
		private LambdaType _Lambda_OrderedSet_T_OrderedSet;
		private LambdaType _Lambda_OrderedSet_T_OrderedSet_collectNested_V;
		private LambdaType _Lambda_OrderedSet_T_OrderedSet_collect_V;
		private LambdaType _Lambda_Sequence_T_Boolean;
		private LambdaType _Lambda_Sequence_T_OclAny;
		private LambdaType _Lambda_Sequence_T_OrderedSet;
		private LambdaType _Lambda_Sequence_T_Sequence_collectNested_V;
		private LambdaType _Lambda_Sequence_T_Sequence_collect_V;
		private LambdaType _Lambda_Set_T_Boolean;
		private LambdaType _Lambda_Set_T_OclAny;
		private LambdaType _Lambda_Set_T_Set;
		private LambdaType _Lambda_Set_T_Set_collectNested_V;
		private LambdaType _Lambda_Set_T_Set_collect_V;
		private LambdaType _Lambda_UniqueCollection_T_OclAny;
		private Iteration it_Bag_closure;
		private Iteration it_Bag_collectNested;
		private Iteration it_Bag_collect;
		private Iteration it_Bag_reject;
		private Iteration it_Bag_select;
		private Iteration it_Bag_sortedBy;
		private Iteration it_Collection_any;
		private Iteration it_Collection_collectBy;
		private Iteration it_Collection_collectNested;
		private Iteration it_Collection_collect;
		private Iteration it_Collection_exists;
		private Iteration it_Collection_exists_1;
		private Iteration it_Collection_exists_2;
		private Iteration it_Collection_forAll;
		private Iteration it_Collection_forAll_1;
		private Iteration it_Collection_forAll_2;
		private Iteration it_Collection_isUnique;
		private Iteration it_Collection_iterate;
		private Iteration it_Collection_one;
		private Iteration it_Collection_reject;
		private Iteration it_Collection_select;
		private Iteration it_Collection_sortedBy;
		private Iteration it_Map_any;
		private Iteration it_Map_collectBy;
		private Iteration it_Map_collectNested;
		private Iteration it_Map_collect;
		private Iteration it_Map_exists;
		private Iteration it_Map_exists_1;
		private Iteration it_Map_exists_2;
		private Iteration it_Map_forAll;
		private Iteration it_Map_forAll_1;
		private Iteration it_Map_forAll_2;
		private Iteration it_Map_isUnique;
		private Iteration it_Map_iterate;
		private Iteration it_Map_one;
		private Iteration it_Map_reject;
		private Iteration it_Map_select;
		private Iteration it_OrderedSet_closure;
		private Iteration it_OrderedSet_collectNested;
		private Iteration it_OrderedSet_collect;
		private Iteration it_OrderedSet_reject;
		private Iteration it_OrderedSet_select;
		private Iteration it_OrderedSet_sortedBy;
		private Iteration it_Sequence_closure;
		private Iteration it_Sequence_collectNested;
		private Iteration it_Sequence_collect;
		private Iteration it_Sequence_reject;
		private Iteration it_Sequence_select;
		private Iteration it_Sequence_sortedBy;
		private Iteration it_Set_closure;
		private Iteration it_Set_collectNested;
		private Iteration it_Set_collect;
		private Iteration it_Set_reject;
		private Iteration it_Set_select;
		private Iteration it_Set_sortedBy;
		private Iteration it_UniqueCollection_sortedBy;
		private Operation op_Boolean__lt__gt_;
		private Operation op_Boolean__eq_;
		private Operation op_Boolean_allInstances;
		private Operation op_Boolean_and;
		private Operation op_Boolean_and2;
		private Operation op_Boolean_implies;
		private Operation op_Boolean_implies2;
		private Operation op_Boolean_not;
		private Operation op_Boolean_not2;
		private Operation op_Boolean_or;
		private Operation op_Boolean_or2;
		private Operation op_Boolean_toString;
		private Operation op_Boolean_xor;
		private Operation op_Boolean_xor2;
		private Operation op_Integer__mul_;
		private Operation op_Integer__add_;
		private Operation op_Integer__neg_;
		private Operation op_Integer__neg__1;
		private Operation op_Integer__div_;
		private Operation op_Integer_abs;
		private Operation op_Integer_div;
		private Operation op_Integer_max;
		private Operation op_Integer_min;
		private Operation op_Integer_mod;
		private Operation op_Integer_toString;
		private Operation op_Integer_toUnlimitedNatural;
		private Operation op_Real__mul_;
		private Operation op_Real__add_;
		private Operation op_Real__neg_;
		private Operation op_Real__neg__1;
		private Operation op_Real__div_;
		private Operation op_Real__lt__gt_;
		private Operation op_Real__eq_;
		private Operation op_Real_abs;
		private Operation op_Real_floor;
		private Operation op_Real_max;
		private Operation op_Real_min;
		private Operation op_Real_round;
		private Operation op_Real_toString;
		private Operation op_String__add_;
		private Operation op_String__lt_;
		private Operation op_String__lt__eq_;
		private Operation op_String__lt__gt_;
		private Operation op_String__eq_;
		private Operation op_String__gt_;
		private Operation op_String__gt__eq_;
		private Operation op_String_at;
		private Operation op_String_characters;
		private Operation op_String_compareTo;
		private Operation op_String_concat;
		private Operation op_String_endsWith;
		private Operation op_String_equalsIgnoreCase;
		private Operation op_String_indexOf;
		private Operation op_String_lastIndexOf;
		private Operation op_String_matches;
		private Operation op_String_replaceAll;
		private Operation op_String_replaceFirst;
		private Operation op_String_size;
		private Operation op_String_startsWith;
		private Operation op_String_substituteAll;
		private Operation op_String_substituteFirst;
		private Operation op_String_substring;
		private Operation op_String_toBoolean;
		private Operation op_String_toInteger;
		private Operation op_String_toLower;
		private Operation op_String_toLowerCase;
		private Operation op_String_toReal;
		private Operation op_String_toString;
		private Operation op_String_toUpper;
		private Operation op_String_toUpperCase;
		private Operation op_String_tokenize;
		private Operation op_String_tokenize_1;
		private Operation op_String_tokenize_2;
		private Operation op_String_trim;
		private Operation op_UnlimitedNatural_max;
		private Operation op_UnlimitedNatural_min;
		private Operation op_UnlimitedNatural_oclAsType;
		private Operation op_UnlimitedNatural_toInteger;
		private Operation op_Bag__lt__gt_;
		private Operation op_Bag__eq_;
		private Operation op_Bag_excluding;
		private Operation op_Bag_excludingAll;
		private Operation op_Bag_flatten;
		private Operation op_Bag_including;
		private Operation op_Bag_includingAll;
		private Operation op_Bag_selectByKind;
		private Operation op_Bag_selectByType;
		private Operation op_BooleanType_allInstances;
		private Operation op_Class_allInstances;
		private Operation op_Collection__lt__gt_;
		private Operation op_Collection__eq_;
		private Operation op_Collection_asBag;
		private Operation op_Collection_asOrderedSet;
		private Operation op_Collection_asSequence;
		private Operation op_Collection_asSet;
		private Operation op_Collection_count;
		private Operation op_Collection_excludes;
		private Operation op_Collection_excludesAll;
		private Operation op_Collection_excluding;
		private Operation op_Collection_excludingAll;
		private Operation op_Collection_flatten;
		private Operation op_Collection_includes;
		private Operation op_Collection_includesAll;
		private Operation op_Collection_including;
		private Operation op_Collection_includingAll;
		private Operation op_Collection_intersection;
		private Operation op_Collection_intersection_1;
		private Operation op_Collection_isEmpty;
		private Operation op_Collection_max;
		private Operation op_Collection_min;
		private Operation op_Collection_notEmpty;
		private Operation op_Collection_product;
		private Operation op_Collection_selectByKind;
		private Operation op_Collection_selectByType;
		private Operation op_Collection_size;
		private Operation op_Collection_sum;
		private Operation op_Collection_union;
		private Operation op_Enumeration_allInstances;
		private Operation op_InvalidType_allInstances;
		private Operation op_Map__lt__gt_;
		private Operation op_Map__eq_;
		private Operation op_Map_at;
		private Operation op_Map_excludes;
		private Operation op_Map_excludes_1;
		private Operation op_Map_excludesAll;
		private Operation op_Map_excludesMap;
		private Operation op_Map_excludesValue;
		private Operation op_Map_excluding;
		private Operation op_Map_excluding_1;
		private Operation op_Map_excludingAll;
		private Operation op_Map_excludingMap;
		private Operation op_Map_includes;
		private Operation op_Map_includes_1;
		private Operation op_Map_includesAll;
		private Operation op_Map_includesMap;
		private Operation op_Map_includesValue;
		private Operation op_Map_including;
		private Operation op_Map_includingMap;
		private Operation op_Map_isEmpty;
		private Operation op_Map_keys;
		private Operation op_Map_notEmpty;
		private Operation op_Map_size;
		private Operation op_Map_values;
		private Operation op_OclAny__lt__gt_;
		private Operation op_OclAny__eq_;
		private Operation op_OclAny_oclAsSet;
		private Operation op_OclAny_oclAsType;
		private Operation op_OclAny_oclIsInState;
		private Operation op_OclAny_oclIsInvalid;
		private Operation op_OclAny_oclIsKindOf;
		private Operation op_OclAny_oclIsNew;
		private Operation op_OclAny_oclIsTypeOf;
		private Operation op_OclAny_oclIsUndefined;
		private Operation op_OclAny_oclLog;
		private Operation op_OclAny_oclLog_1;
		private Operation op_OclAny_oclType;
		private Operation op_OclAny_oclTypes;
		private Operation op_OclAny_toString;
		private Operation op_OclComparable__lt_;
		private Operation op_OclComparable__lt__eq_;
		private Operation op_OclComparable__gt_;
		private Operation op_OclComparable__gt__eq_;
		private Operation op_OclComparable_compareTo;
		private Operation op_OclElement_allInstances;
		private Operation op_OclElement_oclAsModelType;
		private Operation op_OclElement_oclBase;
		private Operation op_OclElement_oclBase_1;
		private Operation op_OclElement_oclContainer;
		private Operation op_OclElement_oclContents;
		private Operation op_OclElement_oclExtension;
		private Operation op_OclElement_oclExtensions;
		private Operation op_OclElement_oclExtensions_1;
		private Operation op_OclElement_oclIsModelKindOf;
		private Operation op_OclElement_oclModelType;
		private Operation op_OclElement_oclModelTypes;
		private Operation op_OclEnumeration_allInstances;
		private Operation op_OclInvalid__lt__gt_;
		private Operation op_OclInvalid__eq_;
		private Operation op_OclInvalid_allInstances;
		private Operation op_OclInvalid_and;
		private Operation op_OclInvalid_implies;
		private Operation op_OclInvalid_oclAsSet;
		private Operation op_OclInvalid_oclAsType;
		private Operation op_OclInvalid_oclBadOperation;
		private Operation op_OclInvalid_oclIsInvalid;
		private Operation op_OclInvalid_oclIsKindOf;
		private Operation op_OclInvalid_oclIsTypeOf;
		private Operation op_OclInvalid_oclIsUndefined;
		private Operation op_OclInvalid_oclType;
		private Operation op_OclInvalid_or;
		private Operation op_OclInvalid_toString;
		private Operation op_OclMessage_hasReturned;
		private Operation op_OclMessage_isOperationCall;
		private Operation op_OclMessage_isSignalSent;
		private Operation op_OclMessage_result;
		private Operation op_OclStereotype_allInstances;
		private Operation op_OclSummable_sum;
		private Operation op_OclSummable_zero;
		private Operation op_OclTuple__lt__gt_;
		private Operation op_OclTuple__eq_;
		private Operation op_OclType_conformsTo;
		private Operation op_OclVoid__add_;
		private Operation op_OclVoid__lt__gt_;
		private Operation op_OclVoid__eq_;
		private Operation op_OclVoid_allInstances;
		private Operation op_OclVoid_and;
		private Operation op_OclVoid_concat;
		private Operation op_OclVoid_implies;
		private Operation op_OclVoid_not;
		private Operation op_OclVoid_oclAsSet;
		private Operation op_OclVoid_oclAsType;
		private Operation op_OclVoid_oclIsInvalid;
		private Operation op_OclVoid_oclIsKindOf;
		private Operation op_OclVoid_oclIsTypeOf;
		private Operation op_OclVoid_oclIsUndefined;
		private Operation op_OclVoid_oclType;
		private Operation op_OclVoid_oclTypes;
		private Operation op_OclVoid_or;
		private Operation op_OclVoid_toString;
		private Operation op_OclVoid_xor;
		private Operation op_OrderedCollection_at;
		private Operation op_OrderedCollection_first;
		private Operation op_OrderedCollection_indexOf;
		private Operation op_OrderedCollection_last;
		private Operation op_OrderedSet__neg_;
		private Operation op_OrderedSet__lt__gt_;
		private Operation op_OrderedSet__eq_;
		private Operation op_OrderedSet_append;
		private Operation op_OrderedSet_appendAll;
		private Operation op_OrderedSet_excluding;
		private Operation op_OrderedSet_excludingAll;
		private Operation op_OrderedSet_flatten;
		private Operation op_OrderedSet_including;
		private Operation op_OrderedSet_includingAll;
		private Operation op_OrderedSet_insertAt;
		private Operation op_OrderedSet_prepend;
		private Operation op_OrderedSet_prependAll;
		private Operation op_OrderedSet_reverse;
		private Operation op_OrderedSet_selectByKind;
		private Operation op_OrderedSet_selectByType;
		private Operation op_OrderedSet_subOrderedSet;
		private Operation op_Sequence__lt__gt_;
		private Operation op_Sequence__eq_;
		private Operation op_Sequence_append;
		private Operation op_Sequence_appendAll;
		private Operation op_Sequence_excluding;
		private Operation op_Sequence_excludingAll;
		private Operation op_Sequence_flatten;
		private Operation op_Sequence_including;
		private Operation op_Sequence_includingAll;
		private Operation op_Sequence_insertAt;
		private Operation op_Sequence_prepend;
		private Operation op_Sequence_prependAll;
		private Operation op_Sequence_reverse;
		private Operation op_Sequence_selectByKind;
		private Operation op_Sequence_selectByType;
		private Operation op_Sequence_subSequence;
		private Operation op_Set__neg_;
		private Operation op_Set__lt__gt_;
		private Operation op_Set__eq_;
		private Operation op_Set_excluding;
		private Operation op_Set_excludingAll;
		private Operation op_Set_flatten;
		private Operation op_Set_including;
		private Operation op_Set_includingAll;
		private Operation op_Set_selectByKind;
		private Operation op_Set_selectByType;
		private Operation op_Stereotype_allInstances;
		private Operation op_Type_conformsTo;
		private Operation op_UniqueCollection__neg_;
		private Operation op_UniqueCollection_intersection;
		private Operation op_UniqueCollection_symmetricDifference;
		private Operation op_UniqueCollection_union;
		private Operation op_VoidType_allInstances;
		// declare ENUMERATION_LITERALS Model
		// declare FRAGMENT_OPERATIONS Model
		// declare FRAGMENT_PROPERTIES Model
		// declare OPERATIONS BooleanType
		// declare OPERATIONS PrimitiveType
		// declare OPERATIONS PrimitiveType
		// declare OPERATIONS PrimitiveType
		// declare OPERATIONS PrimitiveType
		// declare OPERATIONS AnyType
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS Class
		// declare OPERATIONS InvalidType
		// declare OPERATIONS SelfType
		// declare OPERATIONS VoidType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS CollectionType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS MapType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS BagType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS OrderedSetType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SequenceType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS SetType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare OPERATIONS LambdaType
		// declare PARAMETER_LISTS Model
		// declare PROPERTIES BooleanType
		// declare PROPERTIES PrimitiveType
		// declare PROPERTIES PrimitiveType
		// declare PROPERTIES PrimitiveType
		// declare PROPERTIES PrimitiveType
		// declare PROPERTIES AnyType
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES Class
		// declare PROPERTIES InvalidType
		// declare PROPERTIES SelfType
		// declare PROPERTIES VoidType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES CollectionType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES MapType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES BagType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES OrderedSetType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SequenceType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES SetType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare PROPERTIES LambdaType
		// declare TYPE_FRAGMENTS Model
		// declare TYPE_PARAMETERS Model

		private void installSlots0() {
			// define CTOR Model
		}

		private void installSlots1() {
			// define CTOR Library
			// define CTOR Package
			// define TYPE_PARAMETERS Model
		}

		private void installSlots2() {
			// define ALL_TYPES Model
			_Boolean = createPrimitiveType(ocl, OCLstdlibPackage.Literals.BOOLEAN);
			_Integer = createPrimitiveType(ocl, OCLstdlibPackage.Literals.INTEGER);
			_Real = createPrimitiveType(ocl, OCLstdlibPackage.Literals.REAL);
			_String = createPrimitiveType(ocl, OCLstdlibPackage.Literals.STRING);
			_UnlimitedNatural = createPrimitiveType(ocl, OCLstdlibPackage.Literals.UNLIMITED_NATURAL);
			_OclAny = createClass(ocl, OCLstdlibPackage.Literals.OCL_ANY);
			_OclAny.setIsAbstract(true);
			_BooleanType = createClass(ocl, "BooleanType");
			_Class = createClass(ocl, "Class");
			_CollectionType = createClass(ocl, "CollectionType");
			_Enumeration = createClass(ocl, "Enumeration");
			_EnumerationLiteral = createClass(ocl, "EnumerationLiteral");
			_InvalidType = createClass(ocl, "InvalidType");
			_MapType = createClass(ocl, "MapType");
			_OclComparable = createClass(ocl, OCLstdlibPackage.Literals.OCL_COMPARABLE);
			_OclComparable.setIsAbstract(true);
			_OclElement = createClass(ocl, OCLstdlibPackage.Literals.OCL_ELEMENT);
			_OclElement.setIsAbstract(true);
			_OclEnumeration = createClass(ocl, OCLstdlibPackage.Literals.OCL_ENUMERATION);
			_OclEnumeration.setIsAbstract(true);
			_OclLambda = createClass(ocl, OCLstdlibPackage.Literals.OCL_LAMBDA);
			_OclLambda.setIsAbstract(true);
			_OclMessage = createClass(ocl, OCLstdlibPackage.Literals.OCL_MESSAGE);
			_OclMessage.setIsAbstract(true);
			_OclState = createClass(ocl, OCLstdlibPackage.Literals.OCL_STATE);
			_OclState.setIsAbstract(true);
			_OclStereotype = createClass(ocl, OCLstdlibPackage.Literals.OCL_STEREOTYPE);
			_OclStereotype.setIsAbstract(true);
			_OclSummable = createClass(ocl, OCLstdlibPackage.Literals.OCL_SUMMABLE);
			_OclSummable.setIsAbstract(true);
			_OclTuple = createClass(ocl, OCLstdlibPackage.Literals.OCL_TUPLE);
			_OclTuple.setIsAbstract(true);
			_OclType = createClass(ocl, OCLstdlibPackage.Literals.OCL_TYPE);
			_OclType.setIsAbstract(true);
			_State = createClass(ocl, "State");
			_Stereotype = createClass(ocl, "Stereotype");
			_Type = createClass(ocl, "Type");
			_VoidType = createClass(ocl, "VoidType");
			_OclInvalid = createClass(ocl, OCLstdlibPackage.Literals.OCL_INVALID);
			_OclInvalid.setIsAbstract(true);
			_OclSelf = createClass(ocl, OCLstdlibPackage.Literals.OCL_SELF);
			_OclSelf.setIsAbstract(true);
			_OclVoid = createClass(ocl, OCLstdlibPackage.Literals.OCL_VOID);
			_OclVoid.setIsAbstract(true);
			//_Collection_Collection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.COLLECTION, tp_Collection_T, false, 0, -1);
			_Collection_Collection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.COLLECTION);
			//_OrderedCollection_OrderedCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_COLLECTION, tp_OrderedCollection_T, false, 0, -1);
			_OrderedCollection_OrderedCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_COLLECTION);
			//_UniqueCollection_UniqueCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.UNIQUE_COLLECTION, tp_UniqueCollection_T, false, 0, -1);
			_UniqueCollection_UniqueCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.UNIQUE_COLLECTION);
			//_Map_Map_K_Map_V = createMapType(ocl, OCLstdlibPackage.Literals.MAP, tp_Map_K, true, tp_Map_V, true);
			_Map_Map_K_Map_V = createMapType(ocl, OCLstdlibPackage.Literals.MAP);
			//_Bag_Bag_T = createCollectionType(ocl, OCLstdlibPackage.Literals.BAG, tp_Bag_T, false, 0, -1);
			_Bag_Bag_T = createCollectionType(ocl, OCLstdlibPackage.Literals.BAG);
			//_OrderedSet_OrderedSet_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_SET, tp_OrderedSet_T, false, 0, -1);
			_OrderedSet_OrderedSet_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_SET);
			//_Sequence_Sequence_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SEQUENCE, tp_Sequence_T, false, 0, -1);
			_Sequence_Sequence_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SEQUENCE);
			//_Set_Set_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SET, tp_Set_T, false, 0, -1);
			_Set_Set_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SET);
		}

		private void installSlots3a() {
			// define ALL_FRAGMENTS Model
			installComment(_Boolean, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			installComment(_Integer, "The standard type Integer represents the mathematical concept of integer.\nInteger is itself an instance of the metatype PrimitiveType (from UML).");
			installComment(_Real, "The standard type Real represents the mathematical concept of real.\nNote that Integer is a subclass of Real,\nso for each parameter of type Real, you can use an integer as the actual parameter.\nReal is itself an instance of the metatype PrimitiveType (from UML).");
			installComment(_String, "The standard type String represents strings, which can be both ASCII or Unicode.\nString is itself an instance of the metatype PrimitiveType (from UML).");
			installComment(_UnlimitedNatural, "The standard type UnlimitedNatural is used to encode the non-negative values of a multiplicity specification.\nThis includes a special e[unlimited] value (*) that encodes the upper value of  a multiplicity specification.\nUnlimitedNatural is itself an instance of the metatype UnlimitedNaturalType.\n\nNote that UnlimitedNatural is not a subclass of Integer.");
			installComment(_OclAny, "The number of elements in the collection oclText[self].essions.\nOclAny is itself an instance of the metatype AnyType.\n\nAll classes in a UML model inherit all operations defined on OclAny.\nTo avoid name conflicts between properties in the model and the properties inherited from OclAny,\nall names on the properties of OclAny start with \u2018ocl.\u2019\nAlthough theoretically there may still be name conflicts, they can be avoided.\nOne can also use qualification by OclAny (name of the type) to explicitly refer to the OclAny properties.\n\nOperations of OclAny, where the instance of OclAny is called object.");
			installComment(_BooleanType, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			installComment(_Enumeration, "@Deprecated: Use OclEnumeration\nThe Enumeration type is the type of an OrderedSet of EnumerationLiteral.");
			installComment(_EnumerationLiteral, "The standard type EnumerationLiteral represents a named constant value of an Enumeration.");
			installComment(_OclComparable, "The type OclComparable defines the compareTo operation used by the sortedBy iteration. Only types that provide a derived\ncompareTo implementation may be sorted.");
			installComment(_OclElement, "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes. Operations defined\nfor OclElement are therefore applicable to all user-defined types.");
			installComment(_OclEnumeration, "The OclEnumeration type is the implicit supertype of any user Enumeration type.\nFIXME This is probably obsolete now that static / meta-types clarified.");
			installComment(_OclLambda, "The type OclLambda is the implicit supertype of all Lambda types. The operations defined for OclLambda\ntherefore apply to all lambda expressions.");
			installComment(_OclMessage, "OclMessage\nThis sub clause contains the definition of the standard type OclMessage.\nAs defined in this sub clause, each ocl message type is actually a template type with one parameter.\n\u2018T\u2019 denotes the parameter.\nA concrete ocl message type is created by substituting an operation or signal for the T.\n\nThe predefined type OclMessage is an instance of MessageType.\nEvery OclMessage is fully determined by either the operation, or signal given as parameter.\nNote that there is conceptually an undefined (infinite) number of these types,\nas each is determined by a different operation or signal.\nThese types are unnamed. Every type has as attributes the name of the operation or signal,\nand either all formal parameters of the operation, or all attributes of the signal.\nOclMessage is itself an instance of the metatype MessageType.\n\nOclMessage has a number of predefined operations, as shown in the OCL Standard Library.");
			installComment(_OclStereotype, "The type OclStereotype is the implicit supertype of any UML stereotype. Operations defined\nfor OclStereotype are therefore applicable to all UML stereotypes.");
			installComment(_OclSummable, "The type OclSummable defines the sum and zero operations used by the Collection::sum iteration. Only types that provide derived\nsum and zero implementations may be summed.");
			installComment(_OclTuple, "The type OclTuple is the implicit supertype of all Tuple types. The operations defined for OclTuple\ntherefore apply to all tuples.");
			installComment(_OclType, "The type OclType is the implicit supertype of any UML type. Operations defined\nfor OclType are therefore applicable to all UML types.");
			installComment(_Type, "The UML Type is the supertype of anything that may be used as a type.");
			installComment(_OclInvalid, "The type OclInvalid is a type that conforms to all other types.\nIt has one single instance, identified as  oclText[invalid].\nAny property call applied on invalid results in oclText[invalid], except for the operations oclIsUndefined() and oclIsInvalid().\nOclInvalid is itself an instance of the metatype InvalidType.");
			installComment(_OclSelf, "The pseudo-type OclSelf denotes the statically determinate type of oclText[self] in Operation\nand Iteration signatures. Instances of OclSelf are never created.");
			installComment(_OclVoid, "The type OclVoid is a type that conforms to all other types except OclInvalid.\nIt has one single instance, identified as oclText[null], that corresponds with the UML LiteralNull value specification.\nAny property call applied on oclText[null] results in oclText[invalid], except for the\noclIsUndefined(), oclIsInvalid(), =(OclAny) and <>(OclAny) operations.\nHowever, by virtue of the implicit conversion to a collection literal,\nan expression evaluating to oclText[null] can be used as source of collection operations (such as \u2018isEmpty\u2019).\nIf the source is the oclText[null] literal, it is implicitly converted to Bag{}.\n\nOclVoid is itself an instance of the metatype VoidType.");
			installComment(_Collection_Collection_T, "Collection is the abstract supertype of all collection types in the OCL Standard Library.\nEach occurrence of an object in a collection is called an element.\nIf an object occurs twice in a collection, there are two elements.\n\nThis sub clause defines the properties on Collections that have identical semantics for all collection subtypes.\nSome operations may be defined within the subtype as well,\nwhich means that there is an additional postcondition or a more specialized return value.\nCollection is itself an instance of the metatype CollectionType.\n\nThe definition of several common operations is different for each subtype.\nThese operations are not mentioned in this sub clause.\n\nThe semantics of the collection operations is given in the form of a postcondition that uses the IterateExp of the IteratorExp construct.\nThe semantics of those constructs is defined in Clause 10 (\u201CSemantics Described using UML\u201D).\nIn several cases the postcondition refers to other collection operations,\nwhich in turn are defined in terms of the IterateExp or IteratorExp constructs.\n\nWell-formedness rules\n\n[1] A collection cannot contain oclText[invalid] values.\n\ncontext Collection\ninv: self->forAll(not oclIsInvalid())");
			installComment(_OrderedCollection_OrderedCollection_T, "The OrderedCollection type provides the shared functionality of the OrderedSet and Sequence\ncollections for which the elements are ordered.\nThe common supertype of OrderedCollection is Collection.");
			installComment(_UniqueCollection_UniqueCollection_T, "The UniqueCollection type provides the shared functionality of the OrderedSet and Set\ncollections for which the elements are unique.\nThe common supertype of UniqueCollection is Collection.");
			installComment(_Map_Map_K_Map_V, "A Map provides a Set of key values, each of which has an associated value.\nKeys and values may be null, but neither may be invalid.");
			installComment(_Bag_Bag_T, "A bag is a collection with duplicates allowed. That is, one object can be an element of a bag many times.\nThere is no ordering defined on the elements in a bag.\nBag is itself an instance of the metatype BagType.");
			installComment(_OrderedSet_OrderedSet_T, "The OrderedSet is a Set, the elements of which are ordered.\nIt contains no duplicates. OrderedSet is itself an instance of the metatype OrderedSetType.\nAn OrderedSet is not a subtype of Set, neither a subtype of Sequence.\nThe common supertype of Sets and OrderedSets is Collection.");
			installComment(_Sequence_Sequence_T, "A sequence is a collection where the elements are ordered.\nAn element may be part of a sequence more than once.\nSequence is itself an instance of the metatype SequenceType.\nA Sentence is not a subtype of Bag.\nThe common supertype of Sentence and Bags is Collection.");
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			tp_Bag_T = createTemplateParameter(_Bag_Bag_T, "T");
			tp_Collection_T = createTemplateParameter(_Collection_Collection_T, "T");
			tp_Map_K = createTemplateParameter(_Map_Map_K_Map_V, "K");
			tp_Map_V = createTemplateParameter(_Map_Map_K_Map_V, "V");
			tp_OrderedCollection_T = createTemplateParameter(_OrderedCollection_OrderedCollection_T, "T");
			tp_OrderedSet_T = createTemplateParameter(_OrderedSet_OrderedSet_T, "T");
			tp_Sequence_T = createTemplateParameter(_Sequence_Sequence_T, "T");
			tp_Set_T = createTemplateParameter(_Set_Set_T, "T");
			tp_UniqueCollection_T = createTemplateParameter(_UniqueCollection_UniqueCollection_T, "T");
			it_Bag_closure = createIteration(_Bag_Bag_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Bag_collectNested = createIteration(_Bag_Bag_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_Bag_collect = createIteration(_Bag_Bag_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_Bag_reject = createIteration(_Bag_Bag_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Bag_select = createIteration(_Bag_Bag_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Bag_sortedBy = createIteration(_Bag_Bag_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Collection_any = createIteration(_Collection_Collection_T, "any", "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
			it_Collection_collectBy = createIteration(_Collection_Collection_T, "collectBy", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
			it_Collection_collectNested = createIteration(_Collection_Collection_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_Collection_collect = createIteration(_Collection_Collection_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_Collection_exists = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_exists_1 = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_exists_2 = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_forAll = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_forAll_1 = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_forAll_2 = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_isUnique = createIteration(_Collection_Collection_T, "isUnique", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
			it_Collection_iterate = createIteration(_Collection_Collection_T, "iterate", "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
			it_Collection_one = createIteration(_Collection_Collection_T, "one", "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
			it_Collection_reject = createIteration(_Collection_Collection_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Collection_select = createIteration(_Collection_Collection_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Collection_sortedBy = createIteration(_Collection_Collection_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Map_any = createIteration(_Map_Map_K_Map_V, "any", "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
			it_Map_collectBy = createIteration(_Map_Map_K_Map_V, "collectBy", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE);
			it_Map_collectNested = createIteration(_Map_Map_K_Map_V, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_Map_collect = createIteration(_Map_Map_K_Map_V, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_Map_exists = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_exists_1 = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_exists_2 = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_forAll = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_forAll_1 = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_forAll_2 = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_isUnique = createIteration(_Map_Map_K_Map_V, "isUnique", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
			it_Map_iterate = createIteration(_Map_Map_K_Map_V, "iterate", "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE);
			it_Map_one = createIteration(_Map_Map_K_Map_V, "one", "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
			it_Map_reject = createIteration(_Map_Map_K_Map_V, "reject", "org.eclipse.ocl.pivot.library.iterator.MapRejectIteration", org.eclipse.ocl.pivot.library.iterator.MapRejectIteration.INSTANCE);
			it_Map_select = createIteration(_Map_Map_K_Map_V, "select", "org.eclipse.ocl.pivot.library.iterator.MapSelectIteration", org.eclipse.ocl.pivot.library.iterator.MapSelectIteration.INSTANCE);
			it_OrderedSet_closure = createIteration(_OrderedSet_OrderedSet_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_OrderedSet_collectNested = createIteration(_OrderedSet_OrderedSet_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_OrderedSet_collect = createIteration(_OrderedSet_OrderedSet_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_OrderedSet_reject = createIteration(_OrderedSet_OrderedSet_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_OrderedSet_select = createIteration(_OrderedSet_OrderedSet_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_OrderedSet_sortedBy = createIteration(_OrderedSet_OrderedSet_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Sequence_closure = createIteration(_Sequence_Sequence_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Sequence_collectNested = createIteration(_Sequence_Sequence_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_Sequence_collect = createIteration(_Sequence_Sequence_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_Sequence_reject = createIteration(_Sequence_Sequence_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Sequence_select = createIteration(_Sequence_Sequence_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Sequence_sortedBy = createIteration(_Sequence_Sequence_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		}

		private void installSlots3b() {
			it_Set_closure = createIteration(_Set_Set_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Set_collectNested = createIteration(_Set_Set_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE);
			it_Set_collect = createIteration(_Set_Set_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE);
			it_Set_reject = createIteration(_Set_Set_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Set_select = createIteration(_Set_Set_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Set_sortedBy = createIteration(_Set_Set_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_UniqueCollection_sortedBy = createIteration(_UniqueCollection_UniqueCollection_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			op_Boolean__lt__gt_ = createOperation(_Boolean, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Boolean__eq_ = createOperation(_Boolean, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Boolean_allInstances = createOperation(_Boolean, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_Boolean_and = createOperation(_Boolean, "and", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
			op_Boolean_and2 = createOperation(_Boolean, "and2", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2.INSTANCE);
			op_Boolean_implies = createOperation(_Boolean, "implies", "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE);
			op_Boolean_implies2 = createOperation(_Boolean, "implies2", "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2.INSTANCE);
			op_Boolean_not = createOperation(_Boolean, "not", "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
			op_Boolean_not2 = createOperation(_Boolean, "not2", "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2.INSTANCE);
			op_Boolean_or = createOperation(_Boolean, "or", "org.eclipse.ocl.pivot.library.logical.BooleanOrOperation", org.eclipse.ocl.pivot.library.logical.BooleanOrOperation.INSTANCE);
			op_Boolean_or2 = createOperation(_Boolean, "or2", "org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2", org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2.INSTANCE);
			op_Boolean_toString = createOperation(_Boolean, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_Boolean_xor = createOperation(_Boolean, "xor", "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
			op_Boolean_xor2 = createOperation(_Boolean, "xor2", "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2.INSTANCE);
			op_Integer__mul_ = createOperation(_Integer, "*", "org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation", org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
			op_Integer__add_ = createOperation(_Integer, "+", "org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation", org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
			op_Integer__neg_ = createOperation(_Integer, "-", "org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation", org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
			op_Integer__neg__1 = createOperation(_Integer, "-", "org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
			op_Integer__div_ = createOperation(_Integer, "/", "org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
			op_Integer_abs = createOperation(_Integer, "abs", "org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation", org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
			op_Integer_div = createOperation(_Integer, "div", "org.eclipse.ocl.pivot.library.numeric.NumericDivOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivOperation.INSTANCE);
			op_Integer_max = createOperation(_Integer, "max", "org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation", org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
			op_Integer_min = createOperation(_Integer, "min", "org.eclipse.ocl.pivot.library.numeric.NumericMinOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
			op_Integer_mod = createOperation(_Integer, "mod", "org.eclipse.ocl.pivot.library.numeric.NumericModOperation", org.eclipse.ocl.pivot.library.numeric.NumericModOperation.INSTANCE);
			op_Integer_toString = createOperation(_Integer, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_Integer_toUnlimitedNatural = createOperation(_Integer, "toUnlimitedNatural", "org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation", org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation.INSTANCE);
			op_Real__mul_ = createOperation(_Real, "*", "org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation", org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE);
			op_Real__add_ = createOperation(_Real, "+", "org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation", org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE);
			op_Real__neg_ = createOperation(_Real, "-", "org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation", org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE);
			op_Real__neg__1 = createOperation(_Real, "-", "org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE);
			op_Real__div_ = createOperation(_Real, "/", "org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE);
			op_Real__lt__gt_ = createOperation(_Real, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Real__eq_ = createOperation(_Real, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Real_abs = createOperation(_Real, "abs", "org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation", org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE);
			op_Real_floor = createOperation(_Real, "floor", "org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation", org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation.INSTANCE);
			op_Real_max = createOperation(_Real, "max", "org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation", org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE);
			op_Real_min = createOperation(_Real, "min", "org.eclipse.ocl.pivot.library.numeric.NumericMinOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE);
			op_Real_round = createOperation(_Real, "round", "org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation", org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation.INSTANCE);
			op_Real_toString = createOperation(_Real, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_String__add_ = createOperation(_String, "+", "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
			op_String__lt_ = createOperation(_String, "<", "org.eclipse.ocl.pivot.library.string.StringLessThanOperation", org.eclipse.ocl.pivot.library.string.StringLessThanOperation.INSTANCE);
			op_String__lt__eq_ = createOperation(_String, "<=", "org.eclipse.ocl.pivot.library.string.StringLessThanEqualOperation", org.eclipse.ocl.pivot.library.string.StringLessThanEqualOperation.INSTANCE);
			op_String__lt__gt_ = createOperation(_String, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_String__eq_ = createOperation(_String, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_String__gt_ = createOperation(_String, ">", "org.eclipse.ocl.pivot.library.string.StringGreaterThanOperation", org.eclipse.ocl.pivot.library.string.StringGreaterThanOperation.INSTANCE);
			op_String__gt__eq_ = createOperation(_String, ">=", "org.eclipse.ocl.pivot.library.string.StringGreaterThanEqualOperation", org.eclipse.ocl.pivot.library.string.StringGreaterThanEqualOperation.INSTANCE);
			op_String_at = createOperation(_String, "at", "org.eclipse.ocl.pivot.library.string.StringAtOperation", org.eclipse.ocl.pivot.library.string.StringAtOperation.INSTANCE);
			op_String_characters = createOperation(_String, "characters", "org.eclipse.ocl.pivot.library.string.StringCharactersOperation", org.eclipse.ocl.pivot.library.string.StringCharactersOperation.INSTANCE);
			op_String_compareTo = createOperation(_String, "compareTo", "org.eclipse.ocl.pivot.library.string.StringCompareToOperation", org.eclipse.ocl.pivot.library.string.StringCompareToOperation.INSTANCE);
			op_String_concat = createOperation(_String, "concat", "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
			op_String_endsWith = createOperation(_String, "endsWith", "org.eclipse.ocl.pivot.library.string.StringEndsWithOperation", org.eclipse.ocl.pivot.library.string.StringEndsWithOperation.INSTANCE);
			op_String_equalsIgnoreCase = createOperation(_String, "equalsIgnoreCase", "org.eclipse.ocl.pivot.library.string.StringEqualsIgnoreCaseOperation", org.eclipse.ocl.pivot.library.string.StringEqualsIgnoreCaseOperation.INSTANCE);
			op_String_indexOf = createOperation(_String, "indexOf", "org.eclipse.ocl.pivot.library.string.StringIndexOfOperation", org.eclipse.ocl.pivot.library.string.StringIndexOfOperation.INSTANCE);
			op_String_lastIndexOf = createOperation(_String, "lastIndexOf", "org.eclipse.ocl.pivot.library.string.StringLastIndexOfOperation", org.eclipse.ocl.pivot.library.string.StringLastIndexOfOperation.INSTANCE);
			op_String_matches = createOperation(_String, "matches", "org.eclipse.ocl.pivot.library.string.StringMatchesOperation", org.eclipse.ocl.pivot.library.string.StringMatchesOperation.INSTANCE);
			op_String_replaceAll = createOperation(_String, "replaceAll", "org.eclipse.ocl.pivot.library.string.StringReplaceAllOperation", org.eclipse.ocl.pivot.library.string.StringReplaceAllOperation.INSTANCE);
			op_String_replaceFirst = createOperation(_String, "replaceFirst", "org.eclipse.ocl.pivot.library.string.StringReplaceFirstOperation", org.eclipse.ocl.pivot.library.string.StringReplaceFirstOperation.INSTANCE);
			op_String_size = createOperation(_String, "size", "org.eclipse.ocl.pivot.library.string.StringSizeOperation", org.eclipse.ocl.pivot.library.string.StringSizeOperation.INSTANCE);
			op_String_startsWith = createOperation(_String, "startsWith", "org.eclipse.ocl.pivot.library.string.StringStartsWithOperation", org.eclipse.ocl.pivot.library.string.StringStartsWithOperation.INSTANCE);
			op_String_substituteAll = createOperation(_String, "substituteAll", "org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation", org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation.INSTANCE);
			op_String_substituteFirst = createOperation(_String, "substituteFirst", "org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation", org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation.INSTANCE);
			op_String_substring = createOperation(_String, "substring", "org.eclipse.ocl.pivot.library.string.StringSubstringOperation", org.eclipse.ocl.pivot.library.string.StringSubstringOperation.INSTANCE);
			op_String_toBoolean = createOperation(_String, "toBoolean", "org.eclipse.ocl.pivot.library.string.StringToBooleanOperation", org.eclipse.ocl.pivot.library.string.StringToBooleanOperation.INSTANCE);
			op_String_toInteger = createOperation(_String, "toInteger", "org.eclipse.ocl.pivot.library.string.StringToIntegerOperation", org.eclipse.ocl.pivot.library.string.StringToIntegerOperation.INSTANCE);
			op_String_toLower = createOperation(_String, "toLower", "org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation", org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
			op_String_toLowerCase = createOperation(_String, "toLowerCase", "org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation", org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE);
			op_String_toReal = createOperation(_String, "toReal", "org.eclipse.ocl.pivot.library.string.StringToRealOperation", org.eclipse.ocl.pivot.library.string.StringToRealOperation.INSTANCE);
			op_String_toString = createOperation(_String, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_String_toUpper = createOperation(_String, "toUpper", "org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation", org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
			op_String_toUpperCase = createOperation(_String, "toUpperCase", "org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation", org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE);
			op_String_tokenize = createOperation(_String, "tokenize", "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
			op_String_tokenize_1 = createOperation(_String, "tokenize", "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
			op_String_tokenize_2 = createOperation(_String, "tokenize", "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE);
			op_String_trim = createOperation(_String, "trim", "org.eclipse.ocl.pivot.library.string.StringTrimOperation", org.eclipse.ocl.pivot.library.string.StringTrimOperation.INSTANCE);
			op_UnlimitedNatural_max = createOperation(_UnlimitedNatural, "max", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation.INSTANCE);
			op_UnlimitedNatural_min = createOperation(_UnlimitedNatural, "min", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation.INSTANCE);
			op_UnlimitedNatural_oclAsType = createOperation(_UnlimitedNatural, "oclAsType", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation.INSTANCE);
			op_UnlimitedNatural_toInteger = createOperation(_UnlimitedNatural, "toInteger", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation.INSTANCE);
			op_Bag__lt__gt_ = createOperation(_Bag_Bag_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Bag__eq_ = createOperation(_Bag_Bag_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Bag_excluding = createOperation(_Bag_Bag_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Bag_excludingAll = createOperation(_Bag_Bag_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Bag_flatten = createOperation(_Bag_Bag_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
			op_Bag_including = createOperation(_Bag_Bag_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Bag_includingAll = createOperation(_Bag_Bag_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Bag_selectByKind = createOperation(_Bag_Bag_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
			op_Bag_selectByType = createOperation(_Bag_Bag_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
			op_BooleanType_allInstances = createOperation(_BooleanType, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Class_allInstances = createOperation(_Class, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Collection__lt__gt_ = createOperation(_Collection_Collection_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Collection__eq_ = createOperation(_Collection_Collection_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Collection_asBag = createOperation(_Collection_Collection_T, "asBag", "org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation.INSTANCE);
			op_Collection_asOrderedSet = createOperation(_Collection_Collection_T, "asOrderedSet", "org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation.INSTANCE);
		}

		private void installSlots3c() {
			op_Collection_asSequence = createOperation(_Collection_Collection_T, "asSequence", "org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation.INSTANCE);
			op_Collection_asSet = createOperation(_Collection_Collection_T, "asSet", "org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation.INSTANCE);
			op_Collection_count = createOperation(_Collection_Collection_T, "count", "org.eclipse.ocl.pivot.library.collection.CollectionCountOperation", org.eclipse.ocl.pivot.library.collection.CollectionCountOperation.INSTANCE);
			op_Collection_excludes = createOperation(_Collection_Collection_T, "excludes", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation.INSTANCE);
			op_Collection_excludesAll = createOperation(_Collection_Collection_T, "excludesAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation.INSTANCE);
			op_Collection_excluding = createOperation(_Collection_Collection_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Collection_excludingAll = createOperation(_Collection_Collection_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Collection_flatten = createOperation(_Collection_Collection_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
			op_Collection_includes = createOperation(_Collection_Collection_T, "includes", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation.INSTANCE);
			op_Collection_includesAll = createOperation(_Collection_Collection_T, "includesAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation.INSTANCE);
			op_Collection_including = createOperation(_Collection_Collection_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Collection_includingAll = createOperation(_Collection_Collection_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Collection_intersection = createOperation(_Collection_Collection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_Collection_intersection_1 = createOperation(_Collection_Collection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_Collection_isEmpty = createOperation(_Collection_Collection_T, "isEmpty", "org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation.INSTANCE);
			op_Collection_max = createOperation(_Collection_Collection_T, "max", "org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation", org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation.INSTANCE);
			op_Collection_min = createOperation(_Collection_Collection_T, "min", "org.eclipse.ocl.pivot.library.collection.CollectionMinOperation", org.eclipse.ocl.pivot.library.collection.CollectionMinOperation.INSTANCE);
			op_Collection_notEmpty = createOperation(_Collection_Collection_T, "notEmpty", "org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation.INSTANCE);
			op_Collection_product = createOperation(_Collection_Collection_T, "product", "org.eclipse.ocl.pivot.library.collection.CollectionProductOperation", org.eclipse.ocl.pivot.library.collection.CollectionProductOperation.INSTANCE);
			op_Collection_selectByKind = createOperation(_Collection_Collection_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
			op_Collection_selectByType = createOperation(_Collection_Collection_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
			op_Collection_size = createOperation(_Collection_Collection_T, "size", "org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation.INSTANCE);
			op_Collection_sum = createOperation(_Collection_Collection_T, "sum", "org.eclipse.ocl.pivot.library.collection.CollectionSumOperation", org.eclipse.ocl.pivot.library.collection.CollectionSumOperation.INSTANCE);
			op_Collection_union = createOperation(_Collection_Collection_T, "union", "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation", org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);
			op_Enumeration_allInstances = createOperation(_Enumeration, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_InvalidType_allInstances = createOperation(_InvalidType, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Map__lt__gt_ = createOperation(_Map_Map_K_Map_V, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Map__eq_ = createOperation(_Map_Map_K_Map_V, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Map_at = createOperation(_Map_Map_K_Map_V, "at", "org.eclipse.ocl.pivot.library.map.MapAtOperation", org.eclipse.ocl.pivot.library.map.MapAtOperation.INSTANCE);
			op_Map_excludes = createOperation(_Map_Map_K_Map_V, "excludes", "org.eclipse.ocl.pivot.library.map.MapExcludesOperation", org.eclipse.ocl.pivot.library.map.MapExcludesOperation.INSTANCE);
			op_Map_excludes_1 = createOperation(_Map_Map_K_Map_V, "excludes", "org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation", org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation.INSTANCE);
			op_Map_excludesAll = createOperation(_Map_Map_K_Map_V, "excludesAll", "org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation.INSTANCE);
			op_Map_excludesMap = createOperation(_Map_Map_K_Map_V, "excludesMap", "org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation.INSTANCE);
			op_Map_excludesValue = createOperation(_Map_Map_K_Map_V, "excludesValue", "org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation", org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation.INSTANCE);
			op_Map_excluding = createOperation(_Map_Map_K_Map_V, "excluding", "org.eclipse.ocl.pivot.library.map.MapExcludingOperation", org.eclipse.ocl.pivot.library.map.MapExcludingOperation.INSTANCE);
			op_Map_excluding_1 = createOperation(_Map_Map_K_Map_V, "excluding", "org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation", org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation.INSTANCE);
			op_Map_excludingAll = createOperation(_Map_Map_K_Map_V, "excludingAll", "org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation.INSTANCE);
			op_Map_excludingMap = createOperation(_Map_Map_K_Map_V, "excludingMap", "org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation.INSTANCE);
			op_Map_includes = createOperation(_Map_Map_K_Map_V, "includes", "org.eclipse.ocl.pivot.library.map.MapIncludesOperation", org.eclipse.ocl.pivot.library.map.MapIncludesOperation.INSTANCE);
			op_Map_includes_1 = createOperation(_Map_Map_K_Map_V, "includes", "org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation.INSTANCE);
			op_Map_includesAll = createOperation(_Map_Map_K_Map_V, "includesAll", "org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation", org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation.INSTANCE);
			op_Map_includesMap = createOperation(_Map_Map_K_Map_V, "includesMap", "org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation.INSTANCE);
			op_Map_includesValue = createOperation(_Map_Map_K_Map_V, "includesValue", "org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation", org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation.INSTANCE);
			op_Map_including = createOperation(_Map_Map_K_Map_V, "including", "org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation.INSTANCE);
			op_Map_includingMap = createOperation(_Map_Map_K_Map_V, "includingMap", "org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation.INSTANCE);
			op_Map_isEmpty = createOperation(_Map_Map_K_Map_V, "isEmpty", "org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation", org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation.INSTANCE);
			op_Map_keys = createOperation(_Map_Map_K_Map_V, "keys", "org.eclipse.ocl.pivot.library.map.MapKeysOperation", org.eclipse.ocl.pivot.library.map.MapKeysOperation.INSTANCE);
			op_Map_notEmpty = createOperation(_Map_Map_K_Map_V, "notEmpty", "org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation", org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation.INSTANCE);
			op_Map_size = createOperation(_Map_Map_K_Map_V, "size", "org.eclipse.ocl.pivot.library.map.MapSizeOperation", org.eclipse.ocl.pivot.library.map.MapSizeOperation.INSTANCE);
			op_Map_values = createOperation(_Map_Map_K_Map_V, "values", "org.eclipse.ocl.pivot.library.map.MapValuesOperation", org.eclipse.ocl.pivot.library.map.MapValuesOperation.INSTANCE);
			op_OclAny__lt__gt_ = createOperation(_OclAny, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OclAny__eq_ = createOperation(_OclAny, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OclAny_oclAsSet = createOperation(_OclAny, "oclAsSet", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
			op_OclAny_oclAsType = createOperation(_OclAny, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
			op_OclAny_oclIsInState = createOperation(_OclAny, "oclIsInState", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation.INSTANCE);
			op_OclAny_oclIsInvalid = createOperation(_OclAny, "oclIsInvalid", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
			op_OclAny_oclIsKindOf = createOperation(_OclAny, "oclIsKindOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
			op_OclAny_oclIsNew = createOperation(_OclAny, "oclIsNew", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclAny_oclIsTypeOf = createOperation(_OclAny, "oclIsTypeOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
			op_OclAny_oclIsUndefined = createOperation(_OclAny, "oclIsUndefined", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
			op_OclAny_oclLog = createOperation(_OclAny, "oclLog", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
			op_OclAny_oclLog_1 = createOperation(_OclAny, "oclLog", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE);
			op_OclAny_oclType = createOperation(_OclAny, "oclType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
			op_OclAny_oclTypes = createOperation(_OclAny, "oclTypes", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
			op_OclAny_toString = createOperation(_OclAny, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_OclComparable__lt_ = createOperation(_OclComparable, "<", "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation.INSTANCE);
			op_OclComparable__lt__eq_ = createOperation(_OclComparable, "<=", "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation.INSTANCE);
			op_OclComparable__gt_ = createOperation(_OclComparable, ">", "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation.INSTANCE);
			op_OclComparable__gt__eq_ = createOperation(_OclComparable, ">=", "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation.INSTANCE);
			op_OclComparable_compareTo = createOperation(_OclComparable, "compareTo", "org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation.INSTANCE);
			op_OclElement_allInstances = createOperation(_OclElement, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclElement_oclAsModelType = createOperation(_OclElement, "oclAsModelType", "org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation.INSTANCE);
			op_OclElement_oclBase = createOperation(_OclElement, "oclBase", "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
			op_OclElement_oclBase_1 = createOperation(_OclElement, "oclBase", "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE);
			op_OclElement_oclContainer = createOperation(_OclElement, "oclContainer", "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation.INSTANCE);
			op_OclElement_oclContents = createOperation(_OclElement, "oclContents", "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation.INSTANCE);
			op_OclElement_oclExtension = createOperation(_OclElement, "oclExtension", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation.INSTANCE);
			op_OclElement_oclExtensions = createOperation(_OclElement, "oclExtensions", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
			op_OclElement_oclExtensions_1 = createOperation(_OclElement, "oclExtensions", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE);
			op_OclElement_oclIsModelKindOf = createOperation(_OclElement, "oclIsModelKindOf", "org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation.INSTANCE);
			op_OclElement_oclModelType = createOperation(_OclElement, "oclModelType", "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation.INSTANCE);
			op_OclElement_oclModelTypes = createOperation(_OclElement, "oclModelTypes", "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation.INSTANCE);
			op_OclEnumeration_allInstances = createOperation(_OclEnumeration, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclInvalid__lt__gt_ = createOperation(_OclInvalid, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OclInvalid__eq_ = createOperation(_OclInvalid, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OclInvalid_allInstances = createOperation(_OclInvalid, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclInvalid_and = createOperation(_OclInvalid, "and", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
			op_OclInvalid_implies = createOperation(_OclInvalid, "implies", "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE);
			op_OclInvalid_oclAsSet = createOperation(_OclInvalid, "oclAsSet", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
			op_OclInvalid_oclAsType = createOperation(_OclInvalid, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
			op_OclInvalid_oclBadOperation = createOperation(_OclInvalid, "oclBadOperation", null, null);
			op_OclInvalid_oclIsInvalid = createOperation(_OclInvalid, "oclIsInvalid", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
			op_OclInvalid_oclIsKindOf = createOperation(_OclInvalid, "oclIsKindOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
			op_OclInvalid_oclIsTypeOf = createOperation(_OclInvalid, "oclIsTypeOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
			op_OclInvalid_oclIsUndefined = createOperation(_OclInvalid, "oclIsUndefined", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
			op_OclInvalid_oclType = createOperation(_OclInvalid, "oclType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
			op_OclInvalid_or = createOperation(_OclInvalid, "or", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE);
			op_OclInvalid_toString = createOperation(_OclInvalid, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_OclMessage_hasReturned = createOperation(_OclMessage, "hasReturned", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclMessage_isOperationCall = createOperation(_OclMessage, "isOperationCall", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
		}

		private void installSlots3d() {
			op_OclMessage_isSignalSent = createOperation(_OclMessage, "isSignalSent", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclMessage_result = createOperation(_OclMessage, "result", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclStereotype_allInstances = createOperation(_OclStereotype, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclSummable_sum = createOperation(_OclSummable, "sum", null, null);
			op_OclSummable_zero = createOperation(_OclSummable, "zero", null, null);
			op_OclTuple__lt__gt_ = createOperation(_OclTuple, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OclTuple__eq_ = createOperation(_OclTuple, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OclType_conformsTo = createOperation(_OclType, "conformsTo", "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation", org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE);
			op_OclVoid__add_ = createOperation(_OclVoid, "+", "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
			op_OclVoid__lt__gt_ = createOperation(_OclVoid, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OclVoid__eq_ = createOperation(_OclVoid, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OclVoid_allInstances = createOperation(_OclVoid, "allInstances", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			op_OclVoid_and = createOperation(_OclVoid, "and", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation.INSTANCE);
			op_OclVoid_concat = createOperation(_OclVoid, "concat", "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE);
			op_OclVoid_implies = createOperation(_OclVoid, "implies", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation.INSTANCE);
			op_OclVoid_not = createOperation(_OclVoid, "not", "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE);
			op_OclVoid_oclAsSet = createOperation(_OclVoid, "oclAsSet", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
			op_OclVoid_oclAsType = createOperation(_OclVoid, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE);
			op_OclVoid_oclIsInvalid = createOperation(_OclVoid, "oclIsInvalid", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE);
			op_OclVoid_oclIsKindOf = createOperation(_OclVoid, "oclIsKindOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE);
			op_OclVoid_oclIsTypeOf = createOperation(_OclVoid, "oclIsTypeOf", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE);
			op_OclVoid_oclIsUndefined = createOperation(_OclVoid, "oclIsUndefined", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE);
			op_OclVoid_oclType = createOperation(_OclVoid, "oclType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE);
			op_OclVoid_oclTypes = createOperation(_OclVoid, "oclTypes", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE);
			op_OclVoid_or = createOperation(_OclVoid, "or", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation.INSTANCE);
			op_OclVoid_toString = createOperation(_OclVoid, "toString", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE);
			op_OclVoid_xor = createOperation(_OclVoid, "xor", "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE);
			op_OrderedCollection_at = createOperation(_OrderedCollection_OrderedCollection_T, "at", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation.INSTANCE);
			op_OrderedCollection_first = createOperation(_OrderedCollection_OrderedCollection_T, "first", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation.INSTANCE);
			op_OrderedCollection_indexOf = createOperation(_OrderedCollection_OrderedCollection_T, "indexOf", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation.INSTANCE);
			op_OrderedCollection_last = createOperation(_OrderedCollection_OrderedCollection_T, "last", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation.INSTANCE);
			op_OrderedSet__neg_ = createOperation(_OrderedSet_OrderedSet_T, "-", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
			op_OrderedSet__lt__gt_ = createOperation(_OrderedSet_OrderedSet_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OrderedSet__eq_ = createOperation(_OrderedSet_OrderedSet_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OrderedSet_append = createOperation(_OrderedSet_OrderedSet_T, "append", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
			op_OrderedSet_appendAll = createOperation(_OrderedSet_OrderedSet_T, "appendAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
			op_OrderedSet_excluding = createOperation(_OrderedSet_OrderedSet_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_OrderedSet_excludingAll = createOperation(_OrderedSet_OrderedSet_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_OrderedSet_flatten = createOperation(_OrderedSet_OrderedSet_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
			op_OrderedSet_including = createOperation(_OrderedSet_OrderedSet_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_OrderedSet_includingAll = createOperation(_OrderedSet_OrderedSet_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_OrderedSet_insertAt = createOperation(_OrderedSet_OrderedSet_T, "insertAt", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
			op_OrderedSet_prepend = createOperation(_OrderedSet_OrderedSet_T, "prepend", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
			op_OrderedSet_prependAll = createOperation(_OrderedSet_OrderedSet_T, "prependAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
			op_OrderedSet_reverse = createOperation(_OrderedSet_OrderedSet_T, "reverse", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
			op_OrderedSet_selectByKind = createOperation(_OrderedSet_OrderedSet_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
			op_OrderedSet_selectByType = createOperation(_OrderedSet_OrderedSet_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
			op_OrderedSet_subOrderedSet = createOperation(_OrderedSet_OrderedSet_T, "subOrderedSet", "org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation.INSTANCE);
			op_Sequence__lt__gt_ = createOperation(_Sequence_Sequence_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Sequence__eq_ = createOperation(_Sequence_Sequence_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Sequence_append = createOperation(_Sequence_Sequence_T, "append", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
			op_Sequence_appendAll = createOperation(_Sequence_Sequence_T, "appendAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
			op_Sequence_excluding = createOperation(_Sequence_Sequence_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Sequence_excludingAll = createOperation(_Sequence_Sequence_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Sequence_flatten = createOperation(_Sequence_Sequence_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
			op_Sequence_including = createOperation(_Sequence_Sequence_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Sequence_includingAll = createOperation(_Sequence_Sequence_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Sequence_insertAt = createOperation(_Sequence_Sequence_T, "insertAt", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
			op_Sequence_prepend = createOperation(_Sequence_Sequence_T, "prepend", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
			op_Sequence_prependAll = createOperation(_Sequence_Sequence_T, "prependAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
			op_Sequence_reverse = createOperation(_Sequence_Sequence_T, "reverse", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
			op_Sequence_selectByKind = createOperation(_Sequence_Sequence_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
			op_Sequence_selectByType = createOperation(_Sequence_Sequence_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
			op_Sequence_subSequence = createOperation(_Sequence_Sequence_T, "subSequence", "org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation", org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation.INSTANCE);
			op_Set__neg_ = createOperation(_Set_Set_T, "-", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
			op_Set__lt__gt_ = createOperation(_Set_Set_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Set__eq_ = createOperation(_Set_Set_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Set_excluding = createOperation(_Set_Set_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Set_excludingAll = createOperation(_Set_Set_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Set_flatten = createOperation(_Set_Set_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE);
			op_Set_including = createOperation(_Set_Set_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Set_includingAll = createOperation(_Set_Set_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Set_selectByKind = createOperation(_Set_Set_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE);
			op_Set_selectByType = createOperation(_Set_Set_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE);
			op_Stereotype_allInstances = createOperation(_Stereotype, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Type_conformsTo = createOperation(_Type, "conformsTo", "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation", org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE);
			op_UniqueCollection__neg_ = createOperation(_UniqueCollection_UniqueCollection_T, "-", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
			op_UniqueCollection_intersection = createOperation(_UniqueCollection_UniqueCollection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_UniqueCollection_symmetricDifference = createOperation(_UniqueCollection_UniqueCollection_T, "symmetricDifference", "org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation", org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation.INSTANCE);
			op_UniqueCollection_union = createOperation(_UniqueCollection_UniqueCollection_T, "union", "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation", org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);
			op_VoidType_allInstances = createOperation(_VoidType, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS SelfType
			// define PROPERTIES BooleanType
			// define PROPERTIES PrimitiveType
			// define PROPERTIES PrimitiveType
			// define PROPERTIES PrimitiveType
			// define PROPERTIES PrimitiveType
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
		}

		private void installSlots3e() {
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES SelfType
			// define PROPERTIES VoidType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES BagType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES SequenceType
			// define PROPERTIES SetType
			addSuperClass(_Boolean, _OclAny);
			addSuperClass(_Integer, _Real);
			addSuperClass(_Real, _OclComparable);
			addSuperClass(_Real, _OclSummable);
			addSuperClass(_String, _OclComparable);
			addSuperClass(_String, _OclSummable);
			addSuperClass(_UnlimitedNatural, _OclComparable);
			addSuperClass(_BooleanType, _OclElement);
			addSuperClass(_Class, _OclElement);
			addSuperClass(_CollectionType, _OclElement);
			addSuperClass(_Enumeration, _OclType);
			addSuperClass(_EnumerationLiteral, _OclAny);
			addSuperClass(_InvalidType, _OclElement);
			addSuperClass(_MapType, _OclElement);
			addSuperClass(_OclComparable, _OclAny);
			addSuperClass(_OclElement, _OclAny);
			addSuperClass(_OclEnumeration, _OclType);
			addSuperClass(_OclLambda, _OclAny);
			addSuperClass(_OclMessage, _OclAny);
			addSuperClass(_OclState, _OclAny);
			addSuperClass(_OclStereotype, _OclType);
			addSuperClass(_OclSummable, _OclAny);
			addSuperClass(_OclTuple, _OclAny);
			addSuperClass(_OclType, _OclElement);
			addSuperClass(_State, _OclState);
			addSuperClass(_Stereotype, _OclStereotype);
			addSuperClass(_Type, _OclType);
			addSuperClass(_VoidType, _OclElement);
			addSuperClass(_OclInvalid, _OclVoid);
			addSuperClass(_OclSelf, _OclAny);
			addSuperClass(_OclVoid, _OclAny);
			addSuperClass(_Collection_Collection_T, _OclAny);
			addSuperClass(_Map_Map_K_Map_V, _OclAny);
		}

		private void installSlots4a() {
			installComment(it_Bag_closure, "The closure of applying body transitively to every distinct element of the source collection.");
			installComment(it_Bag_collectNested, "The Bag of elements which results from applying body to every member of the source nonordered collection.");
			installComment(it_Bag_reject, "The sub-bag of the source bag for which body is oclText[false].\n\noclCode[self->reject(iterator | body) = self->select(iterator | not body)].");
			installComment(it_Bag_select, "The sub-bag of the source bag for which body is oclText[true].\n\noclCode[self->select(iterator | body) =\nself->iterate(iterator; result : Bag(T) = Bag{} |\nif body then result->including(iterator)\nelse result\nendif)]");
			installComment(it_Bag_sortedBy, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");
			installComment(it_Collection_any, "Returns any element in the e[source] null-free collection for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any element,\notherwise if there are one or more elements for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the result is oclText[null].\n\nlet source : Collection(T) = ..., body : Lambda T() : Boolean = ... in\nsource->any(iterator | body) = source->select(iterator | body)->asSequence()->first()");
			installComment(it_Collection_collectBy, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			installComment(it_Collection_collectNested, "The Collection of elements which results from applying body to every member of the source collection.");
			installComment(it_Collection_collect, "The Collection of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			installComment(it_Collection_exists, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j], oclText[k] in the source collection.");
			installComment(it_Collection_exists_1, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j] in the source collection.");
			installComment(it_Collection_exists_2, "Results in oclText[true] if body evaluates to oclText[true] for at least one element oclText[i] in the source collection.");
			installComment(it_Collection_forAll, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j] in the source collection; otherwise, result is oclText[false].");
			installComment(it_Collection_forAll_1, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j], oclText[k] in the source collection; otherwise, result is oclText[false].");
			installComment(it_Collection_forAll_2, "Results in oclText[true] if the body expression evaluates to oclText[true] for each element oclText[i] in the source collection; otherwise, result is oclText[false].");
			installComment(it_Collection_isUnique, "Results in oclText[true] if body evaluates to a different value for each element oclText[i] in the source collection; otherwise, result is oclText[false].");
			installComment(it_Collection_isUnique, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			installComment(it_Collection_iterate, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source collection.");
			installComment(it_Collection_one, "Results in oclText[true] if there is exactly one element in the source collection for which body is oclText[true].");
			installComment(it_Collection_reject, "The sub-collection of the source collection for which body is oclText[false].");
			installComment(it_Collection_select, "The sub-collection of the source collection for which body is oclText[true].");
			installComment(it_Collection_sortedBy, "Results in the Collection containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");
			installComment(it_Map_any, "Returns the key of any element in the e[source] map for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any key,\notherwise if there are one or more kets for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the null is oclText[invalid].\n\nlet source : Map(K,V) = ..., body : Lambda K(V) : Boolean = ... in\nsource->any(key <- value | body) = source->select(key | let value = source->at(key) in body)->asSequence()->first()");
			installComment(it_Map_collectBy, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			installComment(it_Map_collectNested, "The Map of key and values which results from applying body to every value of the source map.");
			installComment(it_Map_collect, "The Map of key and values that results from applying body to every value of the source map.\nThe result is flattened.");
			installComment(it_Map_exists, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map.");
			installComment(it_Map_exists_1, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2] in the source map.");
			installComment(it_Map_exists_2, "Results in oclText[true] if body evaluates to oclText[true] for at least one key oclText[k] in the source map.");
			installComment(it_Map_forAll, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map; otherwise, result is oclText[false].");
			installComment(it_Map_forAll_1, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2] in the source map; otherwise, result is oclText[false].");
			installComment(it_Map_forAll_2, "Results in oclText[true] if the body expression evaluates to oclText[true] for each key oclText[k] in the source map; otherwise, result is oclText[false].");
			installComment(it_Map_isUnique, "Results in oclText[true] if body evaluates to a different value for each key oclText[k] in the source map; otherwise, result is oclText[false].");
			installComment(it_Map_iterate, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source map.");
			installComment(it_Map_one, "Results in oclText[true] if there is exactly one element in the source map for which body is oclText[true].");
			installComment(it_Map_reject, "The subset of the source set for which body is oclText[false].");
			installComment(it_Map_select, "The subset of set for which expr is oclText[true].");
			installComment(it_OrderedSet_closure, "The closure of applying body transitively to every distinct element of the source collection.");
			installComment(it_OrderedSet_collectNested, "The sequence of elements that results from applying body to every member of the source ordered collection.");
			installComment(it_OrderedSet_collect, "The Sequence of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			installComment(it_OrderedSet_reject, "The ordered set of the source ordered set for which body is oclText[false].");
			installComment(it_OrderedSet_select, "The ordered set of the source ordered set for which body is oclText[true]");
			installComment(it_OrderedSet_sortedBy, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");
			installComment(it_Sequence_closure, "The closure of applying body transitively to every distinct element of the source collection.");
			installComment(it_Sequence_collectNested, "The sequence of elements that results from applying body to every member of the source ordered collection.");
			installComment(it_Sequence_collect, "The Bag of elements that results from applying body to every member of the source sequence.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			installComment(it_Sequence_reject, "The subsequence of the source sequence for which body is oclText[false].");
			installComment(it_Sequence_select, "The subsequence of the source sequence for which body is oclText[true].");
			installComment(it_Sequence_sortedBy, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");
			installComment(it_Set_closure, "The closure of applying body transitively to every distinct element of the source collection.");
			installComment(it_Set_collectNested, "The Bag of elements which results from applying body to every member of the source nonordered collection.");
			installComment(it_Set_collect, "The Bag of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			installComment(it_Set_reject, "The subset of the source set for which body is oclText[false].");
			installComment(it_Set_select, "The subset of set for which expr is oclText[true].");
			installComment(it_Set_sortedBy, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");
			installComment(it_UniqueCollection_sortedBy, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");
			installComment(op_Boolean__lt__gt_, "Returns oclText[true] if the logical value of oclText[self] is the not same as the numeric value of object2, oclText[false] otherwise.");
			installComment(op_Boolean__eq_, "Returns oclText[true] if the logical value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");
			installComment(op_Boolean_allInstances, "Returns oclText[Set{false, true}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_Boolean_and, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid] .\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[true].");
			installComment(op_Boolean_and2, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[true].");
			installComment(op_Boolean_implies, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");
			installComment(op_Boolean_implies2, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[false].");
			installComment(op_Boolean_not, "oclText[true] if oclText[self] is oclText[false].\noclText[false] if oclText[self] is oclText[true].\noclText[null] if oclText[self] is oclText[null].\nOtherwise oclText[invalid].");
			installComment(op_Boolean_not2, "oclText[true] if oclText[self] is oclText[false].\nOtherwise oclText[false].");
			installComment(op_Boolean_or, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");
			installComment(op_Boolean_or2, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[false].");
			installComment(op_Boolean_toString, "Converts oclText[self] to a string value.");
			installComment(op_Boolean_xor, "oclText[true] if oclText[self] is oclText[true] and oclText[b] is oclText[false], or if oclText[self] is oclText[false] and oclText[b] is oclText[true].\noclText[false] if oclText[self] is oclText[true] and oclText[b] is oclText[true], or if oclText[self] is oclText[false] and oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null].");
			installComment(op_Boolean_xor2, "oclText[true] if oclText[self] <> oclText[b]\nOtherwise oclText[false].");
			installComment(op_Integer__mul_, "The value of the multiplication of oclText[self] and i.");
			installComment(op_Integer__add_, "The value of the addition of oclText[self] and i.");
			installComment(op_Integer__neg_, "The negative value of oclText[self].");
			installComment(op_Integer__neg__1, "The value of the subtraction of i from oclText[self].");
			installComment(op_Integer__div_, "The value of oclText[self] divided by i.\nEvaluates to oclText[invalid] if r is equal to zero.");
			installComment(op_Integer_abs, "The absolute value of oclText[self].");
			installComment(op_Integer_div, "The number of times that i fits completely within oclText[self].");
			installComment(op_Integer_max, "The maximum of oclText[self] an i.");
			installComment(op_Integer_min, "The minimum of oclText[self] an i.");
			installComment(op_Integer_mod, "The result is oclText[self] modulo i.");
			installComment(op_Integer_toString, "Converts oclText[self] to a string value.");
			installComment(op_Integer_toUnlimitedNatural, "Converts a non-negative oclText[self] to an UnlimitedNatural value. A negative oclText[self] is converted to oclText[invalid].\nAn automatic coersion may be synthesized if the coercion enables an operation reference to be resolved\nin an expression where no operation was available without coercion.");
			installComment(op_Real__mul_, "The value of the multiplication of oclText[self] and r.");
			installComment(op_Real__add_, "The value of the addition of oclText[self] and r.");
			installComment(op_Real__neg_, "The negative value of oclText[self].");
			installComment(op_Real__neg__1, "The value of the subtraction of r from oclText[self].");
			installComment(op_Real__div_, "The value of oclText[self] divided by r. Evaluates to oclText[invalid] if r is equal to zero.");
			installComment(op_Real__lt__gt_, "Returns oclText[true] if the numeric value of oclText[self] is the not the same as the numeric value of object2, oclText[false] otherwise.");
			installComment(op_Real__eq_, "Returns oclText[true] if the numeric value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");
			installComment(op_Real_abs, "The absolute value of oclText[self].");
			installComment(op_Real_floor, "The largest integer that is less than or equal to oclText[self].");
			installComment(op_Real_max, "The maximum of oclText[self] and r.");
			installComment(op_Real_min, "The minimum of oclText[self] and r.");
			installComment(op_Real_round, "The integer that is closest to oclText[self]. When there are two such integers, the largest one.");
		}

		private void installSlots4b() {
			installComment(op_Real_toString, "Converts oclText[self] to a string value.");
			installComment(op_String__add_, "The concatenation of oclText[self] and s.");
			installComment(op_String__lt_, "True if oclText[self] is less than s, using the locale defined by looking up oclLocale in the current environment.");
			installComment(op_String__lt__eq_, "True if oclText[self] is less than or equal to s, using the locale defined by looking up oclLocale in the current environment.");
			installComment(op_String__gt_, "True if oclText[self] is greater than s, using the locale defined by looking up oclLocale in the current environment.");
			installComment(op_String__gt__eq_, "True if oclText[self] is greater than or equal to s, using the locale defined by looking up oclLocale in the current environment.");
			installComment(op_String_at, "Queries the character at position i in oclText[self].");
			installComment(op_String_characters, "Obtains the characters of oclText[self] as a sequence.");
			installComment(op_String_compareTo, "The comparison of oclText[self] with oclText[that]. -ve if less than, 0 if equal, +ve if greater than.");
			installComment(op_String_concat, "The concatenation of oclText[self] and s.");
			installComment(op_String_endsWith, "Returns true if oclText[self] ends with the string s.\nEvery string ends with the empty string.");
			installComment(op_String_equalsIgnoreCase, "Queries whether s and oclText[self] are equivalent under case-insensitive collation.");
			installComment(op_String_indexOf, "Queries the first index in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index 1 (and also at all other indexes).");
			installComment(op_String_lastIndexOf, "Queries the last in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index oclText[self]-size()+1 (and also at all other indexes).");
			installComment(op_String_matches, "Use a regular expression match and return true if self matches regex, false otherwise.");
			installComment(op_String_replaceAll, "Return a string derived from self by replacing all matches of regex by replacement.");
			installComment(op_String_replaceFirst, "Return a string derived from self by replacing the first match of regex by replacement.");
			installComment(op_String_size, "The number of characters in oclText[self].");
			installComment(op_String_startsWith, "Returns true if oclText[self] starts with the string s.\nEvery string starts with the empty string.");
			installComment(op_String_substituteAll, "Return a string derived from self by replacing all occurrences of oldSubstring by newSubstring.");
			installComment(op_String_substituteFirst, "Return a string derived from self by replacing the first occurrence of oldSubstring by newSubstring.\nReturns invalid if there is no first occurrence.");
			installComment(op_String_substring, "The sub-string of oclText[self] starting at character number lower, up to and including character number upper. Character numbers run from 1 to self.size().");
			installComment(op_String_toBoolean, "Converts oclText[self] to a boolean value. Returns null for non-Boolean text.");
			installComment(op_String_toInteger, "Converts oclText[self] to an Integer value. Returns null for non-Integer text.");
			installComment(op_String_toLower, "This is a deprecated variant of toLowerCase() preserving compatibility with traditional Eclipse OCL behaviour.");
			installComment(op_String_toLowerCase, "Converts oclText[self] to lower case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");
			installComment(op_String_toReal, "Converts oclText[self] to a Real[1] value. Returns null for non-Real text.");
			installComment(op_String_toString, "Returns oclText[self].");
			installComment(op_String_toUpper, "This is a deprecated variant of toUpperCase() preserving compatibility with traditional Eclipse OCL behaviour.");
			installComment(op_String_toUpperCase, "Converts oclText[self] to upper case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");
			installComment(op_String_tokenize, "Partition oclText[self] into a sequence substrings separated by any of space, line-feed, carriage-return, form-feed and horizontal-tab delimiters.\nThe delimiters are omitted from the return.");
			installComment(op_String_tokenize_1, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. The delimiters are omitted from the return.");
			installComment(op_String_tokenize_2, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. If returnDelimeters is\ntrue the returned sequence includes the delimiters, otherwise the delimiters are omitted.");
			installComment(op_String_trim, "Return oclText[self] with leading and trailing whitespace removed.");
			installComment(op_UnlimitedNatural_max, "The maximum of oclText[self] an i.");
			installComment(op_UnlimitedNatural_min, "The minimum of oclText[self] an i.");
			installComment(op_UnlimitedNatural_oclAsType, "Evaluates to oclText[self], where oclText[self] is of the type identified by T.\nThe type T may be any classifier defined in the UML model;\nif the actual type of oclText[self] at evaluation time does not conform to T,\nthen the oclAsType operation evaluates to oclText[invalid].\n\nThe standard behavior is redefined for UnlimitedNatural. Numeric values may be converted to\nReal or Integer, but the e[unlimited] value may not.\nConversion of e[unlimited] to Real or Integer returns oclText[invalid].");
			installComment(op_UnlimitedNatural_toInteger, "Converts oclText[self] to an Integer value unless oclText[self] is e[unlimited] in which case oclText[self] is converted to oclText[null].");
			installComment(op_Bag__lt__gt_, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			installComment(op_Bag__eq_, "True if oclText[self] and bag contain the same elements, the same number of times.");
			installComment(op_Bag_excluding, "The bag containing all elements of oclText[self] apart from all occurrences of object.");
			installComment(op_Bag_excludingAll, "The bag containing all elements of oclText[self] apart from all occurrences of all objects.");
			installComment(op_Bag_flatten, "Redefines the Collection operation. If the element type is not a collection type, this results in the same bag as oclText[self].\nIf the element type is a collection type, the result is the bag containing all the elements of all the recursively flattened elements of oclText[self].");
			installComment(op_Bag_including, "The bag containing all elements of oclText[self] plus object.");
			installComment(op_Bag_includingAll, "The bag containing all elements of oclText[self] and objects.");
			installComment(op_Bag_selectByKind, "The bag containing all elements of oclText[self] whose type conforms to oclText[type].");
			installComment(op_Bag_selectByType, "The bag containing all elements of oclText[self] whose type is oclText[type].");
			installComment(op_BooleanType_allInstances, "Returns oclText[Set{false, true}].");
			installComment(op_Class_allInstances, "Return a set of all instances of the type and derived types of self.");
			installComment(op_Collection__lt__gt_, "True if c is not equal to oclText[self].");
			installComment(op_Collection__eq_, "True if c is a collection of the same kind as oclText[self] and contains the same elements in the same quantities and in the same order,\nin the case of an ordered collection type.");
			installComment(op_Collection_asBag, "The Bag that contains all the elements from oclText[self].");
			installComment(op_Collection_asOrderedSet, "An OrderedSet that contains all the elements from oclText[self], with duplicates removed,\nin an order dependent on the particular concrete collection type.");
			installComment(op_Collection_asSequence, "A Sequence that contains all the elements from oclText[self], in an order dependent on the particular concrete collection type.");
			installComment(op_Collection_asSet, "The Set containing all the elements from oclText[self], with duplicates removed.");
			installComment(op_Collection_count, "The number of times that object occurs in the collection oclText[self].");
			installComment(op_Collection_excludes, "True if object is not an element of oclText[self], oclText[false] otherwise.");
			installComment(op_Collection_excludesAll, "Does oclText[self] contain none of the elements of c2 ?");
			installComment(op_Collection_excluding, "The collection containing all elements of oclText[self] apart from object.");
			installComment(op_Collection_excludingAll, "The collection containing all elements of oclText[self] apart from all occurrences of all objects.");
			installComment(op_Collection_flatten, "If the element type is not a collection type, this results in the same collection as oclText[self].\nIf the element type is a collection type, the result is a collection containing all the elements of all the recursively flattened elements of oclText[self].");
			installComment(op_Collection_includes, "True if object is an element of oclText[self], oclText[false] otherwise.");
			installComment(op_Collection_includesAll, "Does oclText[self] contain all the elements of c2 ?");
			installComment(op_Collection_including, "The collection containing all elements of oclText[self] plus object.");
			installComment(op_Collection_includingAll, "The collection containing all elements of oclText[self] and objects.");
			installComment(op_Collection_intersection, "The intersection of oclText[self] and bag; the bag of all elements that are in both oclText[self] and c.");
			installComment(op_Collection_intersection_1, "The intersection of oclText[self] and a unique collection; the set of all elements that are in both oclText[self] and u.");
			installComment(op_Collection_isEmpty, "Is oclText[self] the empty collection?\n\nNote: oclText[null->isEmpty()] returns oclText[true] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");
			installComment(op_Collection_max, "The element with the maximum value of all elements in oclText[self].\nElements must be of a type supporting the max operation.\nThe max operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");
			installComment(op_Collection_min, "The element with the minimum value of all elements in oclText[self].\nElements must be of a type supporting the min operation.\nThe min operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");
			installComment(op_Collection_notEmpty, "Is oclText[self] not the empty collection?\n\noclText[null->notEmpty()] returns oclText[false] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");
			installComment(op_Collection_product, "The cartesian product operation of oclText[self] and c2.");
			installComment(op_Collection_selectByKind, "The collection containing all elements of oclText[self] whose type conforms to oclText[type].");
			installComment(op_Collection_selectByType, "The collection containing all elements of oclText[self] whose type is oclText[type].");
			installComment(op_Collection_size, "The number of elements in the collection oclText[self].");
			installComment(op_Collection_sum, "The addition of all elements in oclText[self].\nElements must be of an oclText[OclSummable] type to provide the zero() and sum() operations.\nThe e[sum] operation must be both associative: a.sum(b).sum(c) = a.sum(b.sum(c)), and commutative: a.sum(b) = b.sum(a).\nInteger and Real fulfill this condition.\n\nIf the e[sum] operation is not both associative and commutative, the e[sum] expression is not well-formed,\nwhich may result in unpredictable results during evaluation.\nIf an implementation is able to detect a lack of associativity or commutativity,\nthe implementation may bypass the evaluation and return an oclText[invalid] result.");
			installComment(op_Collection_union, "The bag consisting of all elements in oclText[self] and all elements in c.");
			installComment(op_Enumeration_allInstances, "Return a set of all enumeration values of oclText[self].");
			installComment(op_InvalidType_allInstances, "Returns oclText[invalid].");
			installComment(op_Map__lt__gt_, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			installComment(op_Map__eq_, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			installComment(op_Map_at, "The value of the map at oclText[key].");
			installComment(op_Map_excludes, "True if oclText[key] is not one of the keys of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_excludes_1, "True if oclText[key] and oclText[value] are not a key-value pair of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_excludesAll, "True if none of the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_excludesMap, "True if none of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_excludesValue, "True if oclText[value] is not one of the values of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_excluding, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key].");
			installComment(op_Map_excluding_1, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key] and whose value is oclText[key].");
			installComment(op_Map_excludingAll, "The map containing all key-value pairs of oclText[self] except any whose key is included in oclText[keys].");
			installComment(op_Map_excludingMap, "The map containing all key-value pairs of oclText[self] except any which is also included in oclText[map].");
			installComment(op_Map_includes, "True if oclText[key] is one of the keys of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_includes_1, "True if oclText[key] and oclText[value] are a key-value pair of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_includesAll, "True if all the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_includesMap, "True if all of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_includesValue, "True if oclText[value] is one of the values of oclText[self], oclText[false] otherwise.");
			installComment(op_Map_including, "The map containing all of the key-value pairs of oclText[self] and an additional key-value pair for oclText[key] and oclText[value].\nIf oclText[key] is already a key of oclText[self], the old value pair is replaced by oclText[value].");
			installComment(op_Map_includingMap, "The map containing all of the key-value pairs of oclText[self] and oclText[map].\nThe values associated with key-value pairs in oclText[map] replace those in oclText[self] where the same key is used by both maps.");
			installComment(op_Map_isEmpty, "True if oclText[self] is the empty map, oclText[false] otherwise.");
			installComment(op_Map_keys, "A Set comprising all the keys of the key-value pairs in oclText[self].");
		}

		private void installSlots4c() {
			installComment(op_Map_notEmpty, "True if oclText[self] not the empty map, oclText[false] otherwise.");
			installComment(op_Map_size, "The number of key-value pairs in oclText[self].");
			installComment(op_Map_values, "The Bag comprising all the values of the key-value pairs in oclText[self].");
			installComment(op_OclAny__lt__gt_, "True if oclText[self] is a different object from object2. Infix operator.");
			installComment(op_OclAny__eq_, "True if oclText[self] is the same object as object2. Infix operator.");
			installComment(op_OclAny_oclAsSet, "Returns a Set with oclText[self] as the sole content, unless oclText[self] is oclText[null] in which case returns an empty set,");
			installComment(op_OclAny_oclAsType, "Evaluates to oclText[self], where oclText[self] is of the type identified by oclText[TT].\nThe type oclText[TT] may be any classifier defined by OCL or a user metamodel;\nif the actual type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsType call is not well-formed if\nthe classification is ambiguous.\n\nIn the case of feature redefinition, casting an object to a supertype of its actual type\ndoes not access the supertype\u2019s definition of the feature;\naccording to the semantics of redefinition, the redefined feature simply does not exist for the object.\nHowever, when casting to a supertype, any features additionally defined by the subtype are suppressed.\n\nFIXME Bug 578060 return should be optional to support OclVoid returning null.");
			installComment(op_OclAny_oclIsInState, "Evaluates to oclText[true] if the oclText[self] is in the state identified by statespec.");
			installComment(op_OclAny_oclIsInvalid, "Evaluates to oclText[true] if the oclText[self] is equal to OclInvalid.");
			installComment(op_OclAny_oclIsKindOf, "Evaluates to oclText[true] if the type of oclText[self] conforms to oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].");
			installComment(op_OclAny_oclIsNew, "Can only be used in a postcondition.\nEvaluates to oclText[true] if the oclText[self] is created during performing the operation (for instance, it didn\u2019t exist at precondition time).");
			installComment(op_OclAny_oclIsTypeOf, "Evaluates to oclText[true] if oclText[self] is of the type oclText[type] but not a subtype of oclText[type].");
			installComment(op_OclAny_oclIsUndefined, "Evaluates to oclText[true] if the oclText[self] is equal to oclText[invalid] or equal to oclText[null].");
			installComment(op_OclAny_oclLog, "Evaluates to the self, with the side effect of generating a log message comprising self.");
			installComment(op_OclAny_oclLog_1, "Evaluates to the self, with the side effect of generating a log message comprising message followed by self.");
			installComment(op_OclAny_oclType, "Evaluates to the most derived type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified type, the return is the most derived type of the current classification which is established when the instance is\npassed to OCL, or re-established by an oclText[oclAsType()] call.");
			installComment(op_OclAny_oclTypes, "Evaluates to all of the most derived type of which oclText[self] is an instance. The return from oclText[oclTypes()]\nis normally equivalent to that from oclText[oclType()] unless oclText[self] is an instance of multiply classified type.");
			installComment(op_OclAny_toString, "Returns a string representation of oclText[self].");
			installComment(op_OclComparable__lt_, "True if oclText[self] is less than oclText[that].");
			installComment(op_OclComparable__lt__eq_, "True if oclText[self] is less than or equal to oclText[that].");
			installComment(op_OclComparable__gt_, "True if oclText[self] is greater than oclText[that].");
			installComment(op_OclComparable__gt__eq_, "True if oclText[self] is greater than or equal to oclText[that].");
			installComment(op_OclComparable_compareTo, "Return -ve, 0, +ve according to whether self is less than, equal to , or greater than that.\n\nThe compareTo operation should be commutative.");
			installComment(op_OclElement_allInstances, "Return a set of all instances of the type and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_OclElement_oclAsModelType, "Evaluates to oclText[self], where oclText[self] is of the model type identified by oclText[TT].\n\nMost model elements have metamodel types for use with oclAsType, but no model type and so the return is oclText[invalid].\n\nModel elements such as UML\'s InstnaceSpecification that do support distinct model and metamodel types return oclText[self]\nwith the cast type oclText[TT] that may be used for further navigation.\nIf the actual model type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsModelType call is not well-formed if\nthe classification is ambiguous.");
			installComment(op_OclElement_oclBase, "Returns the application class that is extended by this extension element. Returns null for an orphan extension of nothing.");
			installComment(op_OclElement_oclBase_1, "Returns the application class conforming to base extended by this extension element. Returns null if no such class.");
			installComment(op_OclElement_oclContainer, "Returns the object for which self is a composed content or null if there is no such object.");
			installComment(op_OclElement_oclContents, "Returns the composed contents of self.");
			installComment(op_OclElement_oclExtension, "Returns the application instance of the Stereotype that conforms to stereotype applied to this element. Returns invalid if more than one.");
			installComment(op_OclElement_oclExtensions, "Returns the application instances of all Stereotypes applied to this element.");
			installComment(op_OclElement_oclExtensions_1, "Returns the application instances of the Stereotypes that conform to stereotype applied to this element.");
			installComment(op_OclElement_oclIsModelKindOf, "Evaluates to oclText[true] if the type of oclText[self] conforms to the model type oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].\n\nThe return is normally oclText[false] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");
			installComment(op_OclElement_oclModelType, "Evaluates to the most derived model type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified model type, the return is the most derived type of the current classification which is established\nby an oclText[oclAsModelType()] call.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");
			installComment(op_OclElement_oclModelTypes, "Evaluates to all of the most derived model types of which oclText[self] is an instance. The return from oclText[oclModelTypes()]\nis normally equivalent to that from oclText[oclModelType()] unless oclText[self] is an instance of multiply classified model type.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");
			installComment(op_OclEnumeration_allInstances, "Return a set of all enumeration values of oclText[self].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_OclInvalid__lt__gt_, "Returns oclText[invalid].");
			installComment(op_OclInvalid__eq_, "Returns oclText[invalid].");
			installComment(op_OclInvalid_allInstances, "Returns oclText[invalid].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_OclInvalid_oclBadOperation, "An oclBadOperation may be used as a placeholder in an unsuccessfully created OCLExpression.");
			installComment(op_OclInvalid_toString, "Returns \'invalid\'.");
			installComment(op_OclMessage_hasReturned, "True if type of template parameter is an operation call, and the called operation has returned a value.\nThis implies the fact that the message has been sent. False in all other cases.");
			installComment(op_OclMessage_isOperationCall, "Returns oclText[true] if the OclMessage represents the sending of a UML Operation call.");
			installComment(op_OclMessage_isSignalSent, "Returns oclText[true] if the OclMessage represents the sending of a UML Signal.");
			installComment(op_OclMessage_result, "Returns the result of the called operation, if type of template parameter is an operation call,\nand the called operation has returned a value. Otherwise the oclText[invalid] value is returned.");
			installComment(op_OclStereotype_allInstances, "Return a set of all instances of the stereotype and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_OclSummable_sum, "Return the sum of self and that.\n\nThe sum operation should be associative.");
			installComment(op_OclSummable_zero, "Return the \'zero\' value of self to initialize a summation.\n\nzero().sum(self) = self.");
			installComment(op_OclType_conformsTo, "Returns true if type2 conforms to self.");
			installComment(op_OclVoid__add_, "The concatenation of oclText[null] and s.");
			installComment(op_OclVoid__eq_, "Redefines the OclAny operation, returning oclText[true] if object is oclText[null], oclText[invalid]\nif object is oclText[invalid], oclText[false] otherwise.");
			installComment(op_OclVoid_allInstances, "Returns oclText[Set{null}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			installComment(op_OclVoid_concat, "The concatenation of oclText[null] and s.");
			installComment(op_OclVoid_toString, "Returns oclText[null].");
			installComment(op_OrderedCollection_at, "The i-th element of ordered collection.");
			installComment(op_OrderedCollection_first, "The first element in oclText[self].");
			installComment(op_OrderedCollection_indexOf, "The index of object obj in the ordered collection. Returns null for an out of bound index.");
			installComment(op_OrderedCollection_last, "The last element in oclText[self].");
			installComment(op_OrderedSet__neg_, "The elements of oclText[self], which are not in s.");
			installComment(op_OrderedSet__lt__gt_, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			installComment(op_OrderedSet__eq_, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			installComment(op_OrderedSet_append, "The set of elements, consisting of all elements of oclText[self], followed by object.");
			installComment(op_OrderedSet_appendAll, "The set of elements, consisting of all elements of oclText[self], followed by objects.");
			installComment(op_OrderedSet_excluding, "The ordered set  containing all elements of oclText[self] apart from object.\n\nThe order of the remaining elements is not changed.");
			installComment(op_OrderedSet_excludingAll, "The ordered set containing all elements of oclText[self] apart from all occurrences of all objects.");
			installComment(op_OrderedSet_including, "The ordered set containing all elements of oclText[self] plus object added as the last element if not already present.");
			installComment(op_OrderedSet_includingAll, "The ordered set containing all elements of oclText[self] plus objects added as the last elements.");
			installComment(op_OrderedSet_insertAt, "The ordered set consisting of oclText[self] with object present at position index.");
			installComment(op_OrderedSet_prepend, "The sequence consisting of object, followed by all elements in oclText[self].");
			installComment(op_OrderedSet_prependAll, "The sequence consisting of objects, followed by all elements in oclText[self].");
			installComment(op_OrderedSet_reverse, "The ordered set of elements with same elements but with the opposite order.");
			installComment(op_OrderedSet_selectByKind, "The ordered set containing all elements of oclText[self] whose type conforms to oclText[type].");
			installComment(op_OrderedSet_selectByType, "The ordered set containing all elements of oclText[self] whose type is oclText[type].");
			installComment(op_OrderedSet_subOrderedSet, "The sub-set of oclText[self] starting at number lower, up to and including element number upper.");
			installComment(op_Sequence__lt__gt_, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			installComment(op_Sequence__eq_, "True if oclText[self] contains the same elements as s in the same order.");
			installComment(op_Sequence_append, "The sequence of elements, consisting of all elements of oclText[self], followed by object.");
			installComment(op_Sequence_appendAll, "The sequence of elements, consisting of all elements of oclText[self], followed by objects.");
			installComment(op_Sequence_excluding, "The sequence containing all elements of oclText[self] apart from all occurrences of object.\n\nThe order of the remaining elements is not changed.");
			installComment(op_Sequence_excludingAll, "The sequence containing all elements of oclText[self] apart from all occurrences of all objects.");
			installComment(op_Sequence_flatten, "Redefines the Collection operation. If the element type is not a collection type, this results in the same sequence as oclText[self].\nIf the element type is a collection type, the result is the sequence containing all the elements\nof all the recursively flattened elements of oclText[self]. The order of the elements is partial.");
			installComment(op_Sequence_including, "The sequence containing all elements of oclText[self] plus object added as the last element.");
			installComment(op_Sequence_includingAll, "The sequence containing all elements of oclText[self] plus objects added as the last elements.");
			installComment(op_Sequence_insertAt, "The sequence consisting of oclText[self] with object inserted at position index.");
			installComment(op_Sequence_prepend, "The sequence consisting of object, followed by all elements in oclText[self].");
			installComment(op_Sequence_prependAll, "The sequence consisting of objects, followed by all elements in oclText[self].");
			installComment(op_Sequence_reverse, "The sequence containing the same elements but with the opposite order.");
			installComment(op_Sequence_selectByKind, "The sequence containing all elements of oclText[self] whose type conforms to oclText[type].");
			installComment(op_Sequence_selectByType, "The sequence containing all elements of oclText[self] whose type is oclText[type].");
			installComment(op_Sequence_subSequence, "The sub-sequence of oclText[self] starting at number lower, up to and including element number upper.");
			installComment(op_Set__neg_, "The elements of oclText[self], which are not in s.");
			installComment(op_Set__lt__gt_, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			installComment(op_Set__eq_, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			installComment(op_Set_excluding, "The set containing all elements of oclText[self] without object.");
			installComment(op_Set_excludingAll, "The set containing all elements of oclText[self] apart from all occurrences of all objects.");
			installComment(op_Set_flatten, "Redefines the Collection operation. If the element type is not a collection type, this results in the same set as oclText[self].\nIf the element type is a collection type, the result is the set containing all the elements of all the recursively flattened elements of oclText[self].");
			installComment(op_Set_including, "The set containing all elements of oclText[self] plus object.");
			installComment(op_Set_includingAll, "The set containing all elements of oclText[self] and objects.");
			installComment(op_Set_selectByKind, "The set containing all elements of oclText[self] whose type conforms to oclText[type].");
			installComment(op_Set_selectByType, "The set containing all elements of oclText[self] whose type is oclText[type].");
		}

		private void installSlots4d() {
			installComment(op_Stereotype_allInstances, "Return a set of all instances of the stereotype and derived types of self.");
			installComment(op_Type_conformsTo, "Returns true if type2 conforms to self.");
			installComment(op_UniqueCollection__neg_, "The elements of oclText[self], which are not in s.");
			installComment(op_UniqueCollection_intersection, "The intersection of oclText[self] and c (i.e., the set of all elements that are in both oclText[self] and c).");
			installComment(op_UniqueCollection_symmetricDifference, "The set containing all the elements that are in oclText[self] or s, but not in both.");
			installComment(op_UniqueCollection_union, "The set consisting of all elements in oclText[self] and all elements in s.");
			installComment(op_VoidType_allInstances, "Returns oclText[Set{null}].");
			tp_UnlimitedNatural_oclAsType_TT = createTemplateParameter(op_UnlimitedNatural_oclAsType, "TT");
			tp_Bag_collectNested_V = createTemplateParameter(it_Bag_collectNested, "V");
			tp_Bag_collect_V = createTemplateParameter(it_Bag_collect, "V");
			tp_Bag_flatten_T2 = createTemplateParameter(op_Bag_flatten, "T2");
			tp_Bag_selectByKind_TT = createTemplateParameter(op_Bag_selectByKind, "TT");
			tp_Bag_selectByType_TT = createTemplateParameter(op_Bag_selectByType, "TT");
			tp_Collection_collectBy_V = createTemplateParameter(it_Collection_collectBy, "V");
			tp_Collection_collectNested_V = createTemplateParameter(it_Collection_collectNested, "V");
			tp_Collection_collect_V = createTemplateParameter(it_Collection_collect, "V");
			tp_Collection_excludesAll_T2 = createTemplateParameter(op_Collection_excludesAll, "T2");
			tp_Collection_flatten_T2 = createTemplateParameter(op_Collection_flatten, "T2");
			tp_Collection_includesAll_T2 = createTemplateParameter(op_Collection_includesAll, "T2");
			tp_Collection_iterate_Tacc = createTemplateParameter(it_Collection_iterate, "Tacc");
			tp_Collection_product_T2 = createTemplateParameter(op_Collection_product, "T2");
			tp_Collection_selectByKind_TT = createTemplateParameter(op_Collection_selectByKind, "TT");
			tp_Collection_selectByType_TT = createTemplateParameter(op_Collection_selectByType, "TT");
			tp_Map_collectBy_V2 = createTemplateParameter(it_Map_collectBy, "V2");
			tp_Map_collectNested_V2 = createTemplateParameter(it_Map_collectNested, "V2");
			tp_Map_collect_V2 = createTemplateParameter(it_Map_collect, "V2");
			tp_Map_excludesAll_K2 = createTemplateParameter(op_Map_excludesAll, "K2");
			tp_Map_excludesMap_K2 = createTemplateParameter(op_Map_excludesMap, "K2");
			tp_Map_excludesMap_V2 = createTemplateParameter(op_Map_excludesMap, "V2");
			tp_Map_excludingMap_K2 = createTemplateParameter(op_Map_excludingMap, "K2");
			tp_Map_excludingMap_V2 = createTemplateParameter(op_Map_excludingMap, "V2");
			tp_Map_includesAll_K2 = createTemplateParameter(op_Map_includesAll, "K2");
			tp_Map_includesMap_K2 = createTemplateParameter(op_Map_includesMap, "K2");
			tp_Map_includesMap_V2 = createTemplateParameter(op_Map_includesMap, "V2");
			tp_Map_includingMap_K2 = createTemplateParameter(op_Map_includingMap, "K2");
			tp_Map_includingMap_V2 = createTemplateParameter(op_Map_includingMap, "V2");
			tp_Map_iterate_Tacc = createTemplateParameter(it_Map_iterate, "Tacc");
			tp_OclAny_oclAsType_TT = createTemplateParameter(op_OclAny_oclAsType, "TT");
			tp_OclElement_oclAsModelType_TT = createTemplateParameter(op_OclElement_oclAsModelType, "TT");
			tp_OclInvalid_oclAsType_TT = createTemplateParameter(op_OclInvalid_oclAsType, "TT");
			tp_OclVoid_oclAsType_TT = createTemplateParameter(op_OclVoid_oclAsType, "TT");
			tp_OrderedSet_collectNested_V = createTemplateParameter(it_OrderedSet_collectNested, "V");
			tp_OrderedSet_collect_V = createTemplateParameter(it_OrderedSet_collect, "V");
			tp_OrderedSet_flatten_T2 = createTemplateParameter(op_OrderedSet_flatten, "T2");
			tp_OrderedSet_selectByKind_TT = createTemplateParameter(op_OrderedSet_selectByKind, "TT");
			tp_OrderedSet_selectByType_TT = createTemplateParameter(op_OrderedSet_selectByType, "TT");
			tp_Sequence_collectNested_V = createTemplateParameter(it_Sequence_collectNested, "V");
			tp_Sequence_collect_V = createTemplateParameter(it_Sequence_collect, "V");
			tp_Sequence_flatten_T2 = createTemplateParameter(op_Sequence_flatten, "T2");
			tp_Sequence_selectByKind_TT = createTemplateParameter(op_Sequence_selectByKind, "TT");
			tp_Sequence_selectByType_TT = createTemplateParameter(op_Sequence_selectByType, "TT");
			tp_Set_collectNested_V = createTemplateParameter(it_Set_collectNested, "V");
			tp_Set_collect_V = createTemplateParameter(it_Set_collect, "V");
			tp_Set_flatten_T2 = createTemplateParameter(op_Set_flatten, "T2");
			tp_Set_selectByKind_TT = createTemplateParameter(op_Set_selectByKind, "TT");
			tp_Set_selectByType_TT = createTemplateParameter(op_Set_selectByType, "TT");
			_Collection_Integer_T = getCollectionType(_Collection_Collection_T, _Integer, true, 0, -1);
			_Collection_String_T = getCollectionType(_Collection_Collection_T, _String, true, 0, -1);
			_Collection_Bag_T_T = getCollectionType(_Collection_Collection_T, tp_Bag_T, true, 0, -1);
			_Collection_CollectionType_T = getCollectionType(_Collection_Collection_T, _CollectionType, true, 0, -1);
			_Collection_Collection_T_T = getCollectionType(_Collection_Collection_T, tp_Collection_T, true, 0, -1);
			_Collection_MapType_T = getCollectionType(_Collection_Collection_T, _MapType, true, 0, -1);
			_Collection_Map_K_T = getCollectionType(_Collection_Collection_T, tp_Map_K, true, 0, -1);
			_Collection_Map_V_T = getCollectionType(_Collection_Collection_T, tp_Map_V, true, 0, -1);
			_Collection_OclAny_T = getCollectionType(_Collection_Collection_T, _OclAny, true, 0, -1);
			_Collection_OclElement_T = getCollectionType(_Collection_Collection_T, _OclElement, true, 0, -1);
			_Collection_OclInvalid_T = getCollectionType(_Collection_Collection_T, _OclInvalid, true, 0, -1);
			_Collection_OclSelf_T = getCollectionType(_Collection_Collection_T, _OclSelf, true, 0, -1);
			_Collection_OrderedCollection_T_T = getCollectionType(_Collection_Collection_T, tp_OrderedCollection_T, true, 0, -1);
			_Collection_OrderedSet_T_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_T, true, 0, -1);
			_Collection_Sequence_T_T = getCollectionType(_Collection_Collection_T, tp_Sequence_T, true, 0, -1);
			_Collection_Set_T_T = getCollectionType(_Collection_Collection_T, tp_Set_T, true, 0, -1);
			_Collection_UniqueCollection_T_T = getCollectionType(_Collection_Collection_T, tp_UniqueCollection_T, true, 0, -1);
			_Map_Map_K_T_Map_V_T = getMapType(_Map_Map_K_Map_V, tp_Map_K, true, tp_Map_V, true);
			_Map_Map_K_F_Map_V_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_V, false);
			_Lambda_Bag_T_Boolean = getLambdaType(_OclLambda, tp_Bag_T, _Boolean);
			_Lambda_Bag_T_OclAny = getLambdaType(_OclLambda, tp_Bag_T, _OclAny);
			_Lambda_Collection_T_Boolean = getLambdaType(_OclLambda, tp_Collection_T, _Boolean);
			_Lambda_Collection_T_OclAny = getLambdaType(_OclLambda, tp_Collection_T, _OclAny);
			_Lambda_Map_K_Boolean = getLambdaType(_OclLambda, tp_Map_K, _Boolean);
			_Lambda_Map_K_OclAny = getLambdaType(_OclLambda, tp_Map_K, _OclAny);
			_Lambda_OrderedSet_T_Boolean = getLambdaType(_OclLambda, tp_OrderedSet_T, _Boolean);
			_Lambda_OrderedSet_T_OclAny = getLambdaType(_OclLambda, tp_OrderedSet_T, _OclAny);
			_Lambda_Sequence_T_Boolean = getLambdaType(_OclLambda, tp_Sequence_T, _Boolean);
			_Lambda_Sequence_T_OclAny = getLambdaType(_OclLambda, tp_Sequence_T, _OclAny);
			_Lambda_Set_T_Boolean = getLambdaType(_OclLambda, tp_Set_T, _Boolean);
			_Lambda_Set_T_OclAny = getLambdaType(_OclLambda, tp_Set_T, _OclAny);
			_Lambda_UniqueCollection_T_OclAny = getLambdaType(_OclLambda, tp_UniqueCollection_T, _OclAny);
			// define OPERATIONS BooleanType
			// define OPERATIONS PrimitiveType
			// define OPERATIONS PrimitiveType
			// define OPERATIONS PrimitiveType
			// define OPERATIONS PrimitiveType
			// define OPERATIONS AnyType
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
		}

		private void installSlots4e() {
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS Class
			// define OPERATIONS InvalidType
			// define OPERATIONS VoidType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS MapType
			// define OPERATIONS BagType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS SequenceType
			// define OPERATIONS SetType
			// define PARAMETER_LISTS Model
			// define PROPERTIES AnyType
			// define PROPERTIES Class
			// define PROPERTIES CollectionType
			// define PROPERTIES MapType
			op_Boolean__lt__gt_.setType(_Boolean);
			op_Boolean__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Boolean__lt__gt_, "object2", _OclSelf, false);
			op_Boolean__eq_.setType(_Boolean);
			op_Boolean__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Boolean__eq_, "object2", _OclSelf, false);
			op_Boolean_and.setType(_Boolean);
			op_Boolean_and.setIsInvalidating(true);
			op_Boolean_and.setIsRequired(false);
			op_Boolean_and.setIsValidating(true);
			op_Boolean_and.setPrecedence(prec_AND);
			createBodyExpression(op_Boolean_and, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = false then false\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then false\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = false then false\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_and, "b", _Boolean, false);
			op_Boolean_and2.setType(_Boolean);
			op_Boolean_and2.setIsValidating(true);
			op_Boolean_and2.setPrecedence(prec_AND);
			createBodyExpression(op_Boolean_and2, _Boolean, "if self = false then false\n\t\t\t  elseif b = false then false\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_and2, "b", _Boolean, true);
			op_Boolean_implies.setType(_Boolean);
			op_Boolean_implies.setIsInvalidating(true);
			op_Boolean_implies.setIsRequired(false);
			op_Boolean_implies.setIsValidating(true);
			op_Boolean_implies.setPrecedence(prec_IMPLIES);
			createBodyExpression(op_Boolean_implies, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then b\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_implies, "b", _Boolean, false);
			op_Boolean_implies2.setType(_Boolean);
			op_Boolean_implies2.setIsValidating(true);
			op_Boolean_implies2.setPrecedence(prec_IMPLIES);
			createBodyExpression(op_Boolean_implies2, _Boolean, "if self = false then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_implies2, "b", _Boolean, true);
			op_Boolean_not.setType(_Boolean);
			op_Boolean_not.setIsInvalidating(true);
			op_Boolean_not.setIsRequired(false);
			op_Boolean_not.setIsValidating(true);
			op_Boolean_not.setPrecedence(prec_UNARY);
			createBodyExpression(op_Boolean_not, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif self = null then null\n\t\t\t  else self = false\n\t\t\t  endif", _Boolean);
			op_Boolean_not2.setType(_Boolean);
			op_Boolean_not2.setPrecedence(prec_UNARY);
			createBodyExpression(op_Boolean_not2, _Boolean, "if self then false else true endif", _Boolean);
			op_Boolean_or.setType(_Boolean);
			op_Boolean_or.setIsInvalidating(true);
			op_Boolean_or.setIsRequired(false);
			op_Boolean_or.setIsValidating(true);
			op_Boolean_or.setPrecedence(prec_OR);
			createBodyExpression(op_Boolean_or, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = true then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_or, "b", _Boolean, false);
			op_Boolean_or2.setType(_Boolean);
			op_Boolean_or2.setIsValidating(true);
			op_Boolean_or2.setPrecedence(prec_OR);
			createBodyExpression(op_Boolean_or2, _Boolean, "if self = true then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_or2, "b", _Boolean, true);
			op_Boolean_toString.setType(_String);
			op_Boolean_xor.setType(_Boolean);
			op_Boolean_xor.setIsRequired(false);
			op_Boolean_xor.setPrecedence(prec_XOR);
			createBodyExpression(op_Boolean_xor, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else self <> b\n\t\t\t  endif", _Boolean);
			createParameter(op_Boolean_xor, "b", _Boolean, false);
			op_Boolean_xor2.setType(_Boolean);
			op_Boolean_xor2.setPrecedence(prec_XOR);
			createBodyExpression(op_Boolean_xor2, _Boolean, "self <> b", _Boolean);
			createParameter(op_Boolean_xor2, "b", _Boolean, true);
			op_Integer__mul_.setType(_Integer);
			op_Integer__mul_.setPrecedence(prec_MULTIPLICATIVE);
			createParameter(op_Integer__mul_, "i", _OclSelf, true);
			op_Integer__add_.setType(_Integer);
			op_Integer__add_.setPrecedence(prec_ADDITIVE);
			createParameter(op_Integer__add_, "i", _OclSelf, true);
			op_Integer__neg_.setType(_Integer);
			op_Integer__neg_.setPrecedence(prec_UNARY);
			op_Integer__neg__1.setType(_Integer);
			op_Integer__neg__1.setPrecedence(prec_ADDITIVE);
			createParameter(op_Integer__neg__1, "i", _OclSelf, true);
			op_Integer__div_.setType(_Real);
			op_Integer__div_.setIsInvalidating(true);
			op_Integer__div_.setPrecedence(prec_MULTIPLICATIVE);
			createParameter(op_Integer__div_, "i", _OclSelf, true);
			op_Integer_abs.setType(_Integer);
			op_Integer_div.setType(_Integer);
			createParameter(op_Integer_div, "i", _Integer, true);
			op_Integer_max.setType(_Integer);
			createParameter(op_Integer_max, "i", _OclSelf, true);
			op_Integer_min.setType(_Integer);
			createParameter(op_Integer_min, "i", _OclSelf, true);
			op_Integer_mod.setType(_Integer);
			createParameter(op_Integer_mod, "i", _Integer, true);
			op_Integer_toString.setType(_String);
			op_Integer_toUnlimitedNatural.setType(_UnlimitedNatural);
			_Integer.getCoercions().add(op_Integer_toUnlimitedNatural);
			op_Real__mul_.setType(_Real);
			op_Real__mul_.setPrecedence(prec_MULTIPLICATIVE);
			createParameter(op_Real__mul_, "r", _OclSelf, true);
			op_Real__add_.setType(_Real);
			op_Real__add_.setPrecedence(prec_ADDITIVE);
			createParameter(op_Real__add_, "r", _OclSelf, true);
			op_Real__neg_.setType(_Real);
			op_Real__neg_.setPrecedence(prec_UNARY);
			op_Real__neg__1.setType(_Real);
			op_Real__neg__1.setPrecedence(prec_ADDITIVE);
			createParameter(op_Real__neg__1, "r", _OclSelf, true);
			op_Real__div_.setType(_Real);
			op_Real__div_.setIsInvalidating(true);
			op_Real__div_.setPrecedence(prec_MULTIPLICATIVE);
			createParameter(op_Real__div_, "r", _OclSelf, true);
			op_Real__lt__gt_.setType(_Boolean);
			op_Real__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Real__lt__gt_, "object2", _OclSelf, false);
			op_Real__eq_.setType(_Boolean);
			op_Real__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Real__eq_, "object2", _OclSelf, false);
			op_Real_abs.setType(_Real);
			op_Real_floor.setType(_Integer);
			op_Real_max.setType(_Real);
			createParameter(op_Real_max, "r", _OclSelf, true);
			op_Real_min.setType(_Real);
			createParameter(op_Real_min, "r", _OclSelf, true);
			op_Real_round.setType(_Integer);
			op_Real_toString.setType(_String);
			op_String__add_.setType(_String);
			op_String__add_.setPrecedence(prec_ADDITIVE);
			createParameter(op_String__add_, "s", _String, false);
			op_String__lt_.setType(_Boolean);
			op_String__lt_.setPrecedence(prec_RELATIONAL);
			createParameter(op_String__lt_, "s", _OclSelf, true);
			op_String__lt__eq_.setType(_Boolean);
			op_String__lt__eq_.setPrecedence(prec_RELATIONAL);
			createParameter(op_String__lt__eq_, "s", _OclSelf, true);
			op_String__lt__gt_.setType(_Boolean);
			op_String__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_String__lt__gt_, "object2", _OclSelf, false);
			op_String__eq_.setType(_Boolean);
			op_String__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_String__eq_, "object2", _OclSelf, false);
			op_String__gt_.setType(_Boolean);
			op_String__gt_.setPrecedence(prec_RELATIONAL);
			createParameter(op_String__gt_, "s", _OclSelf, true);
			op_String__gt__eq_.setType(_Boolean);
			op_String__gt__eq_.setPrecedence(prec_RELATIONAL);
			createParameter(op_String__gt__eq_, "s", _OclSelf, true);
			op_String_at.setType(_String);
			op_String_at.setIsInvalidating(true);
			createParameter(op_String_at, "i", _Integer, true);
			op_String_compareTo.setType(_Integer);
			createParameter(op_String_compareTo, "that", _OclSelf, true);
			op_String_concat.setType(_String);
			createParameter(op_String_concat, "s", _String, false);
			op_String_endsWith.setType(_Boolean);
			createParameter(op_String_endsWith, "s", _String, true);
			op_String_equalsIgnoreCase.setType(_Boolean);
			createParameter(op_String_equalsIgnoreCase, "s", _String, true);
			op_String_indexOf.setType(_Integer);
			createParameter(op_String_indexOf, "s", _String, true);
			op_String_lastIndexOf.setType(_Integer);
			createParameter(op_String_lastIndexOf, "s", _String, true);
			op_String_matches.setType(_Boolean);
			createParameter(op_String_matches, "regex", _String, true);
			op_String_replaceAll.setType(_String);
			createParameter(op_String_replaceAll, "regex", _String, true);
			createParameter(op_String_replaceAll, "replacement", _String, true);
			op_String_replaceFirst.setType(_String);
			createParameter(op_String_replaceFirst, "regex", _String, true);
			createParameter(op_String_replaceFirst, "replacement", _String, true);
			op_String_size.setType(_Integer);
			op_String_startsWith.setType(_Boolean);
			createParameter(op_String_startsWith, "s", _String, true);
			op_String_substituteAll.setType(_String);
			createParameter(op_String_substituteAll, "oldSubstring", _String, true);
			createParameter(op_String_substituteAll, "newSubstring", _String, true);
			op_String_substituteFirst.setType(_String);
			createParameter(op_String_substituteFirst, "oldSubstring", _String, true);
			createParameter(op_String_substituteFirst, "newSubstring", _String, true);
			op_String_substring.setType(_String);
			op_String_substring.setIsInvalidating(true);
			createParameter(op_String_substring, "lower", _Integer, true);
			createParameter(op_String_substring, "upper", _Integer, true);
			op_String_toBoolean.setType(_Boolean);
			op_String_toBoolean.setIsRequired(false);
			op_String_toInteger.setType(_Integer);
			op_String_toInteger.setIsRequired(false);
			op_String_toLower.setType(_String);
			op_String_toLowerCase.setType(_String);
			op_String_toReal.setType(_Real);
			op_String_toReal.setIsRequired(false);
			op_String_toString.setType(_String);
			op_String_toUpper.setType(_String);
			op_String_toUpperCase.setType(_String);
			op_String_trim.setType(_String);
			op_UnlimitedNatural_max.setType(_UnlimitedNatural);
			createParameter(op_UnlimitedNatural_max, "i", _OclSelf, true);
			op_UnlimitedNatural_min.setType(_UnlimitedNatural);
			createParameter(op_UnlimitedNatural_min, "i", _OclSelf, true);
			op_UnlimitedNatural_toInteger.setType(_Integer);
			op_UnlimitedNatural_toInteger.setIsRequired(false);
			op_Bag__lt__gt_.setType(_Boolean);
			op_Bag__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Bag__lt__gt_, "object2", _OclSelf, false);
			op_Bag__eq_.setType(_Boolean);
			op_Bag__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Bag__eq_, "object2", _OclSelf, false);
			op_Collection__lt__gt_.setType(_Boolean);
			op_Collection__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Collection__lt__gt_, "object2", _OclSelf, false);
			op_Collection__eq_.setType(_Boolean);
			op_Collection__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Collection__eq_, "object2", _OclSelf, false);
		}

		private void installSlots4f() {
			op_Collection_count.setType(_Integer);
			createParameter(op_Collection_count, "object", tp_Collection_T, false);
			op_Collection_excludes.setType(_Boolean);
			createParameter(op_Collection_excludes, "object", tp_Collection_T, false);
			op_Collection_includes.setType(_Boolean);
			createParameter(op_Collection_includes, "object", tp_Collection_T, false);
			op_Collection_isEmpty.setType(_Boolean);
			op_Collection_max.setType(tp_Collection_T);
			op_Collection_min.setType(tp_Collection_T);
			op_Collection_notEmpty.setType(_Boolean);
			op_Collection_size.setType(_Integer);
			op_Collection_sum.setType(tp_Collection_T);
			op_Map__lt__gt_.setType(_Boolean);
			op_Map__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Map__lt__gt_, "object2", _OclSelf, false);
			op_Map__eq_.setType(_Boolean);
			op_Map__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Map__eq_, "object2", _OclSelf, false);
			op_Map_at.setType(tp_Map_V);
			op_Map_at.setIsInvalidating(true);
			op_Map_at.setIsRequired(false);
			createParameter(op_Map_at, "key", tp_Map_K, false);
			op_Map_excludes.setType(_Boolean);
			createParameter(op_Map_excludes, "key", tp_Map_K, false);
			op_Map_excludes_1.setType(_Boolean);
			createParameter(op_Map_excludes_1, "key", tp_Map_K, false);
			createParameter(op_Map_excludes_1, "value", tp_Map_V, false);
			op_Map_excludesValue.setType(_Boolean);
			createParameter(op_Map_excludesValue, "value", tp_Map_V, false);
			op_Map_includes.setType(_Boolean);
			createParameter(op_Map_includes, "key", tp_Map_K, false);
			op_Map_includes_1.setType(_Boolean);
			createParameter(op_Map_includes_1, "key", tp_Map_K, false);
			createParameter(op_Map_includes_1, "value", tp_Map_V, false);
			op_Map_includesValue.setType(_Boolean);
			createParameter(op_Map_includesValue, "value", tp_Map_V, false);
			op_Map_isEmpty.setType(_Boolean);
			op_Map_notEmpty.setType(_Boolean);
			op_Map_size.setType(_Integer);
			op_OclAny__lt__gt_.setType(_Boolean);
			op_OclAny__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclAny__lt__gt_, "object2", _OclSelf, false);
			op_OclAny__eq_.setType(_Boolean);
			op_OclAny__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclAny__eq_, "object2", _OclSelf, false);
			op_OclAny_oclIsInState.setType(_Boolean);
			createParameter(op_OclAny_oclIsInState, "statespec", _OclState, false);
			op_OclAny_oclIsInvalid.setType(_Boolean);
			op_OclAny_oclIsInvalid.setIsValidating(true);
			op_OclAny_oclIsKindOf.setType(_Boolean);
			createParameter(op_OclAny_oclIsKindOf, "type", _OclType, true);
			op_OclAny_oclIsNew.setType(_Boolean);
			op_OclAny_oclIsTypeOf.setType(_Boolean);
			createParameter(op_OclAny_oclIsTypeOf, "type", _OclType, true);
			op_OclAny_oclIsUndefined.setType(_Boolean);
			op_OclAny_oclIsUndefined.setIsValidating(true);
			op_OclAny_oclLog.setType(_OclSelf);
			op_OclAny_oclLog_1.setType(_OclSelf);
			createParameter(op_OclAny_oclLog_1, "message", _String, true);
			op_OclAny_oclType.setType(_OclSelf);
			op_OclAny_oclType.setIsTypeof(true);
			op_OclAny_toString.setType(_String);
			op_OclComparable__lt_.setType(_Boolean);
			op_OclComparable__lt_.setPrecedence(prec_RELATIONAL);
			createParameter(op_OclComparable__lt_, "that", _OclSelf, true);
			op_OclComparable__lt__eq_.setType(_Boolean);
			op_OclComparable__lt__eq_.setPrecedence(prec_RELATIONAL);
			createParameter(op_OclComparable__lt__eq_, "that", _OclSelf, true);
			op_OclComparable__gt_.setType(_Boolean);
			op_OclComparable__gt_.setPrecedence(prec_RELATIONAL);
			createParameter(op_OclComparable__gt_, "that", _OclSelf, true);
			op_OclComparable__gt__eq_.setType(_Boolean);
			op_OclComparable__gt__eq_.setPrecedence(prec_RELATIONAL);
			createParameter(op_OclComparable__gt__eq_, "that", _OclSelf, true);
			op_OclComparable_compareTo.setType(_Integer);
			createParameter(op_OclComparable_compareTo, "that", _OclSelf, true);
			op_OclElement_oclBase.setType(_OclType);
			op_OclElement_oclBase.setIsRequired(false);
			op_OclElement_oclBase_1.setType(_OclType);
			op_OclElement_oclBase_1.setIsRequired(false);
			createParameter(op_OclElement_oclBase_1, "base", _OclType, true);
			op_OclElement_oclContainer.setType(_OclElement);
			op_OclElement_oclContainer.setIsRequired(false);
			op_OclElement_oclExtension.setType(_OclElement);
			op_OclElement_oclExtension.setIsInvalidating(true);
			op_OclElement_oclExtension.setIsRequired(false);
			createParameter(op_OclElement_oclExtension, "stereotype", _OclStereotype, true);
			op_OclElement_oclIsModelKindOf.setType(_Boolean);
			createParameter(op_OclElement_oclIsModelKindOf, "type", _OclType, true);
			op_OclElement_oclModelType.setType(_OclSelf);
			op_OclElement_oclModelType.setIsTypeof(true);
			op_OclInvalid__lt__gt_.setType(_Boolean);
			op_OclInvalid__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclInvalid__lt__gt_, "object2", _OclSelf, false);
			op_OclInvalid__eq_.setType(_Boolean);
			op_OclInvalid__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclInvalid__eq_, "object2", _OclSelf, false);
			op_OclInvalid_and.setType(_Boolean);
			op_OclInvalid_and.setIsRequired(false);
			op_OclInvalid_and.setIsValidating(true);
			op_OclInvalid_and.setPrecedence(prec_AND);
			createParameter(op_OclInvalid_and, "b", _Boolean, false);
			op_OclInvalid_implies.setType(_Boolean);
			op_OclInvalid_implies.setIsRequired(false);
			op_OclInvalid_implies.setIsValidating(true);
			op_OclInvalid_implies.setPrecedence(prec_IMPLIES);
			createParameter(op_OclInvalid_implies, "b", _Boolean, false);
			op_OclInvalid_oclBadOperation.setType(_OclAny);
			op_OclInvalid_oclBadOperation.setIsRequired(false);
			op_OclInvalid_oclIsInvalid.setType(_Boolean);
			op_OclInvalid_oclIsInvalid.setIsValidating(true);
			op_OclInvalid_oclIsKindOf.setType(_Boolean);
			createParameter(op_OclInvalid_oclIsKindOf, "type", _OclType, true);
			op_OclInvalid_oclIsTypeOf.setType(_Boolean);
			createParameter(op_OclInvalid_oclIsTypeOf, "type", _OclType, true);
			op_OclInvalid_oclIsUndefined.setType(_Boolean);
			op_OclInvalid_oclIsUndefined.setIsValidating(true);
			op_OclInvalid_oclType.setType(_OclSelf);
			op_OclInvalid_oclType.setIsTypeof(true);
			op_OclInvalid_or.setType(_Boolean);
			op_OclInvalid_or.setIsRequired(false);
			op_OclInvalid_or.setIsValidating(true);
			op_OclInvalid_or.setPrecedence(prec_OR);
			createParameter(op_OclInvalid_or, "b", _Boolean, false);
			op_OclInvalid_toString.setType(_String);
			op_OclMessage_hasReturned.setType(_Boolean);
			op_OclMessage_isOperationCall.setType(_Boolean);
			op_OclMessage_isSignalSent.setType(_Boolean);
			op_OclMessage_result.setType(_OclAny);
			op_OclMessage_result.setIsRequired(false);
			op_OclSummable_sum.setType(_OclSelf);
			createParameter(op_OclSummable_sum, "that", _OclSelf, true);
			op_OclSummable_zero.setType(_OclSelf);
			op_OclTuple__lt__gt_.setType(_Boolean);
			op_OclTuple__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclTuple__lt__gt_, "object2", _OclSelf, false);
			op_OclTuple__eq_.setType(_Boolean);
			op_OclTuple__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclTuple__eq_, "object2", _OclSelf, false);
			op_OclType_conformsTo.setType(_Boolean);
			createParameter(op_OclType_conformsTo, "type2", _OclType, false);
			op_OclVoid__add_.setType(_String);
			op_OclVoid__add_.setPrecedence(prec_ADDITIVE);
			createParameter(op_OclVoid__add_, "s", _String, false);
			op_OclVoid__lt__gt_.setType(_Boolean);
			op_OclVoid__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclVoid__lt__gt_, "object2", _OclSelf, false);
			op_OclVoid__eq_.setType(_Boolean);
			op_OclVoid__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_OclVoid__eq_, "object2", _OclSelf, false);
			op_OclVoid_and.setType(_Boolean);
			op_OclVoid_and.setIsInvalidating(true);
			op_OclVoid_and.setIsRequired(false);
			op_OclVoid_and.setIsValidating(true);
			op_OclVoid_and.setPrecedence(prec_AND);
			createParameter(op_OclVoid_and, "b", _Boolean, false);
			op_OclVoid_concat.setType(_String);
			createParameter(op_OclVoid_concat, "s", _String, false);
			op_OclVoid_implies.setType(_Boolean);
			op_OclVoid_implies.setIsInvalidating(true);
			op_OclVoid_implies.setIsRequired(false);
			op_OclVoid_implies.setIsValidating(true);
			op_OclVoid_implies.setPrecedence(prec_IMPLIES);
			createParameter(op_OclVoid_implies, "b", _Boolean, false);
			op_OclVoid_not.setType(_Boolean);
			op_OclVoid_not.setIsInvalidating(true);
			op_OclVoid_not.setIsRequired(false);
			op_OclVoid_not.setIsValidating(true);
			op_OclVoid_not.setPrecedence(prec_UNARY);
			op_OclVoid_oclIsInvalid.setType(_Boolean);
			op_OclVoid_oclIsInvalid.setIsValidating(true);
			op_OclVoid_oclIsKindOf.setType(_Boolean);
			createParameter(op_OclVoid_oclIsKindOf, "type", _OclType, true);
			op_OclVoid_oclIsTypeOf.setType(_Boolean);
			createParameter(op_OclVoid_oclIsTypeOf, "type", _OclType, true);
			op_OclVoid_oclIsUndefined.setType(_Boolean);
			op_OclVoid_oclIsUndefined.setIsValidating(true);
			op_OclVoid_oclType.setType(_OclSelf);
			op_OclVoid_oclType.setIsTypeof(true);
			op_OclVoid_or.setType(_Boolean);
			op_OclVoid_or.setIsInvalidating(true);
			op_OclVoid_or.setIsRequired(false);
			op_OclVoid_or.setIsValidating(true);
			op_OclVoid_or.setPrecedence(prec_OR);
			createParameter(op_OclVoid_or, "b", _Boolean, false);
			op_OclVoid_toString.setType(_String);
			op_OclVoid_xor.setType(_Boolean);
			op_OclVoid_xor.setIsRequired(false);
			op_OclVoid_xor.setPrecedence(prec_XOR);
			createParameter(op_OclVoid_xor, "b", _Boolean, false);
			op_OrderedCollection_at.setType(tp_OrderedCollection_T);
			op_OrderedCollection_at.setIsInvalidating(true);
			op_OrderedCollection_at.setIsRequired(false);
			createParameter(op_OrderedCollection_at, "index", _Integer, true);
			op_OrderedCollection_first.setType(tp_OrderedCollection_T);
			op_OrderedCollection_first.setIsInvalidating(true);
			op_OrderedCollection_first.setIsRequired(false);
			op_OrderedCollection_indexOf.setType(_Integer);
			op_OrderedCollection_indexOf.setIsRequired(false);
			createParameter(op_OrderedCollection_indexOf, "obj", tp_OrderedCollection_T, false);
			op_OrderedCollection_last.setType(tp_OrderedCollection_T);
			op_OrderedCollection_last.setIsInvalidating(true);
			op_OrderedCollection_last.setIsRequired(false);
			op_OrderedSet__lt__gt_.setType(_Boolean);
			op_OrderedSet__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_OrderedSet__lt__gt_, "object2", _OclSelf, false);
			op_OrderedSet__eq_.setType(_Boolean);
			op_OrderedSet__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_OrderedSet__eq_, "object2", _OclSelf, false);
			op_Sequence__lt__gt_.setType(_Boolean);
			op_Sequence__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Sequence__lt__gt_, "object2", _OclSelf, false);
			op_Sequence__eq_.setType(_Boolean);
			op_Sequence__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Sequence__eq_, "object2", _OclSelf, false);
			op_Set__lt__gt_.setType(_Boolean);
			op_Set__lt__gt_.setPrecedence(prec_EQUALITY);
			createParameter(op_Set__lt__gt_, "object2", _OclSelf, false);
			op_Set__eq_.setType(_Boolean);
			op_Set__eq_.setPrecedence(prec_EQUALITY);
			createParameter(op_Set__eq_, "object2", _OclSelf, false);
			op_Type_conformsTo.setType(_Boolean);
			createParameter(op_Type_conformsTo, "type2", _Type, false);
		}

		private void installSlots5a() {
			_Collection_Bag_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Bag_collectNested_V, true, 0, -1);
			_Collection_Bag_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Bag_collect_V, true, 0, -1);
			_Collection_Bag_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Bag_flatten_T2, true, 0, -1);
			_Collection_Bag_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Bag_selectByKind_TT, true, 0, -1);
			_Collection_Bag_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Bag_selectByType_TT, true, 0, -1);
			_Collection_Collection_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Collection_collectNested_V, true, 0, -1);
			_Collection_Collection_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Collection_collect_V, true, 0, -1);
			_Collection_Collection_excludesAll_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_excludesAll_T2, true, 0, -1);
			_Collection_Collection_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_flatten_T2, true, 0, -1);
			_Collection_Collection_includesAll_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_includesAll_T2, true, 0, -1);
			_Collection_Collection_product_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_product_T2, true, 0, -1);
			_Collection_Collection_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByKind_TT, true, 0, -1);
			_Collection_Collection_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByType_TT, true, 0, -1);
			_Collection_Map_collect_V2_T = getCollectionType(_Collection_Collection_T, tp_Map_collect_V2, true, 0, -1);
			_Collection_Map_excludesAll_K2_T = getCollectionType(_Collection_Collection_T, tp_Map_excludesAll_K2, true, 0, -1);
			_Collection_Map_includesAll_K2_T = getCollectionType(_Collection_Collection_T, tp_Map_includesAll_K2, true, 0, -1);
			_Collection_OrderedSet_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			_Collection_OrderedSet_collect_V_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collect_V, true, 0, -1);
			_Collection_OrderedSet_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			_Collection_OrderedSet_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			_Collection_OrderedSet_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			_Collection_Sequence_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Sequence_collectNested_V, true, 0, -1);
			_Collection_Sequence_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Sequence_collect_V, true, 0, -1);
			_Collection_Sequence_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Sequence_flatten_T2, true, 0, -1);
			_Collection_Sequence_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			_Collection_Sequence_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByType_TT, true, 0, -1);
			_Collection_Set_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Set_collectNested_V, true, 0, -1);
			_Collection_Set_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Set_collect_V, true, 0, -1);
			_Collection_Set_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Set_flatten_T2, true, 0, -1);
			_Collection_Set_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Set_selectByKind_TT, true, 0, -1);
			_Collection_Set_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Set_selectByType_TT, true, 0, -1);
			_Map_Collection_T_F_Collection_collectBy_V_F = getMapType(_Map_Map_K_Map_V, tp_Collection_T, false, tp_Collection_collectBy_V, false);
			_Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludesMap_K2, true, tp_Map_excludesMap_V2, true);
			_Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludingMap_K2, true, tp_Map_excludingMap_V2, true);
			_Map_Map_includesMap_K2_T_Map_includesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includesMap_K2, true, tp_Map_includesMap_V2, true);
			_Map_Map_includingMap_K2_T_Map_includingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includingMap_K2, true, tp_Map_includingMap_V2, true);
			_Map_Map_K_F_Map_collectBy_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectBy_V2, false);
			_Map_Map_K_F_Map_collectNested_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectNested_V2, false);
			_Tuple = getTupleType(_OclTuple,
				createProperty("first", tp_Collection_T),
				createProperty("second", tp_Collection_product_T2));
			_Lambda_Bag_T_Bag_collectNested_V = getLambdaType(_OclLambda, tp_Bag_T, tp_Bag_collectNested_V);
			_Lambda_Bag_T_Bag_collect_V = getLambdaType(_OclLambda, tp_Bag_T, tp_Bag_collect_V);
			_Lambda_Collection_T_Collection_collectBy_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collectBy_V);
			_Lambda_Collection_T_Collection_collectNested_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collectNested_V);
			_Lambda_Collection_T_Collection_collect_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collect_V);
			_Lambda_Collection_T_Collection_iterate_Tacc = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_iterate_Tacc);
			_Lambda_Map_K_Map_collectBy_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collectBy_V2);
			_Lambda_Map_K_Map_collectNested_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collectNested_V2);
			_Lambda_Map_K_Map_collect_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collect_V2);
			_Lambda_Map_K_Map_iterate_Tacc = getLambdaType(_OclLambda, tp_Map_K, tp_Map_iterate_Tacc);
			_Lambda_OrderedSet_T_OrderedSet_collectNested_V = getLambdaType(_OclLambda, tp_OrderedSet_T, tp_OrderedSet_collectNested_V);
			_Lambda_OrderedSet_T_OrderedSet_collect_V = getLambdaType(_OclLambda, tp_OrderedSet_T, tp_OrderedSet_collect_V);
			_Lambda_Sequence_T_Sequence_collectNested_V = getLambdaType(_OclLambda, tp_Sequence_T, tp_Sequence_collectNested_V);
			_Lambda_Sequence_T_Sequence_collect_V = getLambdaType(_OclLambda, tp_Sequence_T, tp_Sequence_collect_V);
			_Lambda_Set_T_Set_collectNested_V = getLambdaType(_OclLambda, tp_Set_T, tp_Set_collectNested_V);
			_Lambda_Set_T_Set_collect_V = getLambdaType(_OclLambda, tp_Set_T, tp_Set_collect_V);
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
		}

		private void installSlots5b() {
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			checkSuperClass(_Collection_Integer_T, _OclAny);
			checkSuperClass(_Collection_String_T, _OclAny);
			checkSuperClass(_Collection_Bag_T_T, _OclAny);
			checkSuperClass(_Collection_CollectionType_T, _OclAny);
			checkSuperClass(_Collection_Collection_T_T, _OclAny);
			checkSuperClass(_Collection_MapType_T, _OclAny);
			checkSuperClass(_Collection_Map_K_T, _OclAny);
			checkSuperClass(_Collection_Map_V_T, _OclAny);
			checkSuperClass(_Collection_OclAny_T, _OclAny);
			checkSuperClass(_Collection_OclElement_T, _OclAny);
			checkSuperClass(_Collection_OclInvalid_T, _OclAny);
			checkSuperClass(_Collection_OclSelf_T, _OclAny);
			checkSuperClass(_Collection_OrderedCollection_T_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_T_T, _OclAny);
			checkSuperClass(_Collection_Sequence_T_T, _OclAny);
			checkSuperClass(_Collection_Set_T_T, _OclAny);
			checkSuperClass(_Collection_UniqueCollection_T_T, _OclAny);
			addSuperClass(_OrderedCollection_OrderedCollection_T, _Collection_OrderedCollection_T_T);
			addSuperClass(_UniqueCollection_UniqueCollection_T, _Collection_UniqueCollection_T_T);
			checkSuperClass(_Map_Map_K_T_Map_V_T, _OclAny);
			checkSuperClass(_Map_Map_K_F_Map_V_F, _OclAny);
			addSuperClass(_Bag_Bag_T, _Collection_Bag_T_T);
			addSuperClass(_Lambda_Bag_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Bag_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Collection_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Map_K_Boolean, _OclLambda);
			addSuperClass(_Lambda_Map_K_OclAny, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Set_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Set_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_UniqueCollection_T_OclAny, _OclLambda);
			it_Collection_any.setType(tp_Collection_T);
			it_Collection_any.setIsRequired(false);
			createIterator(it_Collection_any, "i", tp_Collection_T, true);
			createParameter(it_Collection_any, "body", _Lambda_Collection_T_Boolean, true);
			it_Collection_exists.setType(_Boolean);
			it_Collection_exists.setIsInvalidating(true);
			it_Collection_exists.setIsRequired(false);
			it_Collection_exists.setIsValidating(true);
			createIterator(it_Collection_exists, "i", tp_Collection_T, false);
			createIterator(it_Collection_exists, "j", tp_Collection_T, false);
			createIterator(it_Collection_exists, "k", tp_Collection_T, false);
			createParameter(it_Collection_exists, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_exists_1.setType(_Boolean);
			it_Collection_exists_1.setIsInvalidating(true);
			it_Collection_exists_1.setIsRequired(false);
			it_Collection_exists_1.setIsValidating(true);
			createIterator(it_Collection_exists_1, "i", tp_Collection_T, false);
			createIterator(it_Collection_exists_1, "j", tp_Collection_T, false);
			createParameter(it_Collection_exists_1, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_exists_2.setType(_Boolean);
			it_Collection_exists_2.setIsInvalidating(true);
			it_Collection_exists_2.setIsRequired(false);
			it_Collection_exists_2.setIsValidating(true);
			createIterator(it_Collection_exists_2, "i", tp_Collection_T, false);
			createParameter(it_Collection_exists_2, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_forAll.setType(_Boolean);
			it_Collection_forAll.setIsInvalidating(true);
			it_Collection_forAll.setIsRequired(false);
			it_Collection_forAll.setIsValidating(true);
			createIterator(it_Collection_forAll, "i", tp_Collection_T, false);
			createIterator(it_Collection_forAll, "j", tp_Collection_T, false);
			createIterator(it_Collection_forAll, "k", tp_Collection_T, false);
			createParameter(it_Collection_forAll, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_forAll_1.setType(_Boolean);
			it_Collection_forAll_1.setIsInvalidating(true);
			it_Collection_forAll_1.setIsRequired(false);
			it_Collection_forAll_1.setIsValidating(true);
			createIterator(it_Collection_forAll_1, "i", tp_Collection_T, false);
			createIterator(it_Collection_forAll_1, "j", tp_Collection_T, false);
			createParameter(it_Collection_forAll_1, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_forAll_2.setType(_Boolean);
			it_Collection_forAll_2.setIsInvalidating(true);
			it_Collection_forAll_2.setIsRequired(false);
			it_Collection_forAll_2.setIsValidating(true);
			createIterator(it_Collection_forAll_2, "i", tp_Collection_T, false);
			createParameter(it_Collection_forAll_2, "lambda", _Lambda_Collection_T_Boolean, false);
			it_Collection_isUnique.setType(_Boolean);
			createIterator(it_Collection_isUnique, "i", tp_Collection_T, false);
			createParameter(it_Collection_isUnique, "lambda", _Lambda_Collection_T_OclAny, false);
			it_Collection_one.setType(_Boolean);
			createIterator(it_Collection_one, "i", tp_Collection_T, false);
			createParameter(it_Collection_one, "lambda", _Lambda_Collection_T_Boolean, true);
			it_Collection_reject.setType(_Collection_Collection_T_T);
			createIterator(it_Collection_reject, "i", tp_Collection_T, false);
			createParameter(it_Collection_reject, "lambda", _Lambda_Collection_T_Boolean, true);
			it_Collection_select.setType(_Collection_Collection_T_T);
			createIterator(it_Collection_select, "i", tp_Collection_T, false);
			createParameter(it_Collection_select, "lambda", _Lambda_Collection_T_Boolean, true);
			it_Map_any.setType(tp_Map_K);
			it_Map_any.setIsRequired(false);
			createIterator(it_Map_any, "k", tp_Map_K, true);
			createParameter(it_Map_any, "body", _Lambda_Map_K_Boolean, true);
			it_Map_exists.setType(_Boolean);
			it_Map_exists.setIsInvalidating(true);
			it_Map_exists.setIsRequired(false);
			it_Map_exists.setIsValidating(true);
			createIterator(it_Map_exists, "k1", tp_Map_K, false);
			createIterator(it_Map_exists, "k2", tp_Map_K, false);
			createIterator(it_Map_exists, "k3", tp_Map_K, false);
			createParameter(it_Map_exists, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_exists_1.setType(_Boolean);
			it_Map_exists_1.setIsInvalidating(true);
			it_Map_exists_1.setIsRequired(false);
			it_Map_exists_1.setIsValidating(true);
			createIterator(it_Map_exists_1, "k1", tp_Map_K, false);
			createIterator(it_Map_exists_1, "k2", tp_Map_K, false);
			createParameter(it_Map_exists_1, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_exists_2.setType(_Boolean);
			it_Map_exists_2.setIsInvalidating(true);
			it_Map_exists_2.setIsRequired(false);
			it_Map_exists_2.setIsValidating(true);
			createIterator(it_Map_exists_2, "k", tp_Map_K, false);
			createParameter(it_Map_exists_2, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_forAll.setType(_Boolean);
			it_Map_forAll.setIsInvalidating(true);
			it_Map_forAll.setIsRequired(false);
			it_Map_forAll.setIsValidating(true);
			createIterator(it_Map_forAll, "k1", tp_Map_K, false);
			createIterator(it_Map_forAll, "k2", tp_Map_K, false);
			createIterator(it_Map_forAll, "k3", tp_Map_K, false);
			createParameter(it_Map_forAll, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_forAll_1.setType(_Boolean);
			it_Map_forAll_1.setIsInvalidating(true);
			it_Map_forAll_1.setIsRequired(false);
			it_Map_forAll_1.setIsValidating(true);
			createIterator(it_Map_forAll_1, "k1", tp_Map_K, false);
			createIterator(it_Map_forAll_1, "k2", tp_Map_K, false);
			createParameter(it_Map_forAll_1, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_forAll_2.setType(_Boolean);
			it_Map_forAll_2.setIsInvalidating(true);
			it_Map_forAll_2.setIsRequired(false);
			it_Map_forAll_2.setIsValidating(true);
			createIterator(it_Map_forAll_2, "k", tp_Map_K, false);
			createParameter(it_Map_forAll_2, "lambda", _Lambda_Map_K_Boolean, false);
			it_Map_isUnique.setType(_Boolean);
			createIterator(it_Map_isUnique, "i", tp_Map_K, false);
			createParameter(it_Map_isUnique, "lambda", _Lambda_Map_K_OclAny, false);
			it_Map_one.setType(_Boolean);
			createIterator(it_Map_one, "i", tp_Map_K, false);
			createParameter(it_Map_one, "lambda", _Lambda_Map_K_Boolean, true);
			it_Map_reject.setType(_Map_Map_K_F_Map_V_F);
			createIterator(it_Map_reject, "i", tp_Map_K, false);
			createParameter(it_Map_reject, "lambda", _Lambda_Map_K_Boolean, true);
			it_Map_select.setType(_Map_Map_K_F_Map_V_F);
			createIterator(it_Map_select, "i", tp_Map_K, false);
			createParameter(it_Map_select, "lambda", _Lambda_Map_K_Boolean, true);
			op_UnlimitedNatural_oclAsType.setType(tp_UnlimitedNatural_oclAsType_TT);
			op_UnlimitedNatural_oclAsType.setIsInvalidating(true);
			createParameter(op_UnlimitedNatural_oclAsType, "type", tp_UnlimitedNatural_oclAsType_TT, true).setIsTypeof(true);
			op_Collection_excluding.setType(_Collection_Collection_T_T);
			createParameter(op_Collection_excluding, "object", tp_Collection_T, false);
			op_Collection_excludingAll.setType(_Collection_Collection_T_T);
			createParameter(op_Collection_excludingAll, "objects", _Collection_Collection_T_T, true);
			op_Collection_including.setType(_Collection_Collection_T_T);
			createParameter(op_Collection_including, "object", tp_Collection_T, false);
			op_Collection_includingAll.setType(_Collection_Collection_T_T);
			createParameter(op_Collection_includingAll, "objects", _Collection_Collection_T_T, true);
			op_Map_excluding.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_excluding, "key", tp_Map_K, false);
			op_Map_excluding_1.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_excluding_1, "key", tp_Map_K, false);
			createParameter(op_Map_excluding_1, "value", tp_Map_V, false);
			op_Map_excludingAll.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_excludingAll, "keys", _Collection_Map_K_T, true);
			op_Map_including.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_including, "key", tp_Map_K, false);
			createParameter(op_Map_including, "value", tp_Map_V, false);
			op_OclAny_oclAsType.setType(tp_OclAny_oclAsType_TT);
			op_OclAny_oclAsType.setIsInvalidating(true);
			createParameter(op_OclAny_oclAsType, "type", tp_OclAny_oclAsType_TT, true).setIsTypeof(true);
			op_OclElement_oclAsModelType.setType(tp_OclElement_oclAsModelType_TT);
			op_OclElement_oclAsModelType.setIsInvalidating(true);
			createParameter(op_OclElement_oclAsModelType, "type", tp_OclElement_oclAsModelType_TT, true).setIsTypeof(true);
			op_OclInvalid_oclAsType.setType(tp_OclInvalid_oclAsType_TT);
			createParameter(op_OclInvalid_oclAsType, "type", tp_OclInvalid_oclAsType_TT, true).setIsTypeof(true);
			op_OclVoid_oclAsType.setType(tp_OclVoid_oclAsType_TT);
			op_OclVoid_oclAsType.setIsInvalidating(true);
			op_OclVoid_oclAsType.setIsRequired(false);
			createParameter(op_OclVoid_oclAsType, "type", tp_OclVoid_oclAsType_TT, true).setIsTypeof(true);
			// define TYPE_FRAGMENTS Model
		}

		private void installSlots6a() {
			// define ALL_OPERATIONS Model
			// define CTOR Property
			// define CTOR Property
			_Collection_Tuple_T = getCollectionType(_Collection_Collection_T, _Tuple, true, 0, -1);
			_OrderedCollection_Integer_T = getCollectionType(_OrderedCollection_OrderedCollection_T, _Integer, true, 0, -1);
			_OrderedCollection_String_T = getCollectionType(_OrderedCollection_OrderedCollection_T, _String, true, 0, -1);
			_OrderedCollection_Bag_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Bag_T, true, 0, -1);
			_OrderedCollection_Collection_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Collection_T, true, 0, -1);
			_OrderedCollection_OrderedSet_collectNested_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			_OrderedCollection_OrderedSet_collect_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collect_V, true, 0, -1);
			_OrderedCollection_OrderedSet_flatten_T2_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			_OrderedCollection_OrderedSet_selectByKind_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			_OrderedCollection_OrderedSet_selectByType_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			_OrderedCollection_OrderedSet_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_T, true, 0, -1);
			_OrderedCollection_Sequence_collectNested_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collectNested_V, true, 0, -1);
			_OrderedCollection_Sequence_collect_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collect_V, true, 0, -1);
			_OrderedCollection_Sequence_flatten_T2_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_flatten_T2, true, 0, -1);
			_OrderedCollection_Sequence_selectByKind_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			_OrderedCollection_Sequence_selectByType_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByType_TT, true, 0, -1);
			_OrderedCollection_Sequence_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_T, true, 0, -1);
			_OrderedCollection_Set_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Set_T, true, 0, -1);
			_OrderedCollection_UniqueCollection_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_UniqueCollection_T, true, 0, -1);
			_UniqueCollection_Tuple_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _Tuple, true, 0, -1);
			_UniqueCollection_Bag_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Bag_T, true, 0, -1);
			_UniqueCollection_Collection_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Collection_T, true, 0, -1);
			_UniqueCollection_Map_K_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Map_K, true, 0, -1);
			_UniqueCollection_OclAny_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclAny, true, 0, -1);
			_UniqueCollection_OclElement_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclElement, true, 0, -1);
			_UniqueCollection_OclSelf_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclSelf, true, 0, -1);
			_UniqueCollection_OrderedSet_flatten_T2_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			_UniqueCollection_OrderedSet_selectByKind_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			_UniqueCollection_OrderedSet_selectByType_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			_UniqueCollection_OrderedSet_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_T, true, 0, -1);
			_UniqueCollection_Sequence_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Sequence_T, true, 0, -1);
			_UniqueCollection_Set_flatten_T2_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_flatten_T2, true, 0, -1);
			_UniqueCollection_Set_selectByKind_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByKind_TT, true, 0, -1);
			_UniqueCollection_Set_selectByType_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByType_TT, true, 0, -1);
			_UniqueCollection_Set_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_T, true, 0, -1);
			_UniqueCollection_UniqueCollection_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_UniqueCollection_T, true, 0, -1);
			_Bag_Bag_collectNested_V_T = getCollectionType(_Bag_Bag_T, tp_Bag_collectNested_V, true, 0, -1);
			_Bag_Bag_collect_V_T = getCollectionType(_Bag_Bag_T, tp_Bag_collect_V, true, 0, -1);
			_Bag_Bag_flatten_T2_T = getCollectionType(_Bag_Bag_T, tp_Bag_flatten_T2, true, 0, -1);
			_Bag_Bag_selectByKind_TT_T = getCollectionType(_Bag_Bag_T, tp_Bag_selectByKind_TT, true, 0, -1);
			_Bag_Bag_selectByType_TT_T = getCollectionType(_Bag_Bag_T, tp_Bag_selectByType_TT, true, 0, -1);
			_Bag_Bag_T_T = getCollectionType(_Bag_Bag_T, tp_Bag_T, true, 0, -1);
			_Bag_CollectionType_T = getCollectionType(_Bag_Bag_T, _CollectionType, true, 0, -1);
			_Bag_Collection_T_T = getCollectionType(_Bag_Bag_T, tp_Collection_T, true, 0, -1);
			_Bag_MapType_T = getCollectionType(_Bag_Bag_T, _MapType, true, 0, -1);
			_Bag_Map_collect_V2_T = getCollectionType(_Bag_Bag_T, tp_Map_collect_V2, true, 0, -1);
			_Bag_Map_V_T = getCollectionType(_Bag_Bag_T, tp_Map_V, true, 0, -1);
			_Bag_OclElement_T = getCollectionType(_Bag_Bag_T, _OclElement, true, 0, -1);
			_Bag_OclInvalid_T = getCollectionType(_Bag_Bag_T, _OclInvalid, true, 0, -1);
			_Bag_Set_collectNested_V_T = getCollectionType(_Bag_Bag_T, tp_Set_collectNested_V, true, 0, -1);
			_Bag_Set_collect_V_T = getCollectionType(_Bag_Bag_T, tp_Set_collect_V, true, 0, -1);
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS MapType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
		}

		private void installSlots6b() {
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES MapType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			checkSuperClass(_Collection_Bag_collectNested_V_T, _OclAny);
			checkSuperClass(_Collection_Bag_collect_V_T, _OclAny);
			checkSuperClass(_Collection_Bag_flatten_T2_T, _OclAny);
			checkSuperClass(_Collection_Bag_selectByKind_TT_T, _OclAny);
			checkSuperClass(_Collection_Bag_selectByType_TT_T, _OclAny);
			checkSuperClass(_Collection_Collection_collectNested_V_T, _OclAny);
			checkSuperClass(_Collection_Collection_collect_V_T, _OclAny);
			checkSuperClass(_Collection_Collection_excludesAll_T2_T, _OclAny);
			checkSuperClass(_Collection_Collection_flatten_T2_T, _OclAny);
			checkSuperClass(_Collection_Collection_includesAll_T2_T, _OclAny);
			checkSuperClass(_Collection_Collection_product_T2_T, _OclAny);
			checkSuperClass(_Collection_Collection_selectByKind_TT_T, _OclAny);
			checkSuperClass(_Collection_Collection_selectByType_TT_T, _OclAny);
			checkSuperClass(_Collection_Map_collect_V2_T, _OclAny);
			checkSuperClass(_Collection_Map_excludesAll_K2_T, _OclAny);
			checkSuperClass(_Collection_Map_includesAll_K2_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_collectNested_V_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_collect_V_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_flatten_T2_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_selectByKind_TT_T, _OclAny);
			checkSuperClass(_Collection_OrderedSet_selectByType_TT_T, _OclAny);
			checkSuperClass(_Collection_Sequence_collectNested_V_T, _OclAny);
			checkSuperClass(_Collection_Sequence_collect_V_T, _OclAny);
			checkSuperClass(_Collection_Sequence_flatten_T2_T, _OclAny);
			checkSuperClass(_Collection_Sequence_selectByKind_TT_T, _OclAny);
			checkSuperClass(_Collection_Sequence_selectByType_TT_T, _OclAny);
			checkSuperClass(_Collection_Set_collectNested_V_T, _OclAny);
			checkSuperClass(_Collection_Set_collect_V_T, _OclAny);
			checkSuperClass(_Collection_Set_flatten_T2_T, _OclAny);
			checkSuperClass(_Collection_Set_selectByKind_TT_T, _OclAny);
			checkSuperClass(_Collection_Set_selectByType_TT_T, _OclAny);
			checkSuperClass(_Map_Collection_T_F_Collection_collectBy_V_F, _OclAny);
			checkSuperClass(_Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, _OclAny);
			checkSuperClass(_Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, _OclAny);
			checkSuperClass(_Map_Map_includesMap_K2_T_Map_includesMap_V2_T, _OclAny);
			checkSuperClass(_Map_Map_includingMap_K2_T_Map_includingMap_V2_T, _OclAny);
			checkSuperClass(_Map_Map_K_F_Map_collectBy_V2_F, _OclAny);
			checkSuperClass(_Map_Map_K_F_Map_collectNested_V2_F, _OclAny);
		}

		private void installSlots6c() {
			addSuperClass(_Tuple, _OclTuple);
			addSuperClass(_Lambda_Bag_T_Bag_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Bag_T_Bag_collect_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collectBy_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collect_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_iterate_Tacc, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collectBy_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collectNested_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collect_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_iterate_Tacc, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet_collect_V, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Sequence_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Sequence_collect_V, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set_collect_V, _OclLambda);
			it_Collection_collectBy.setType(_Map_Collection_T_F_Collection_collectBy_V_F);
			createIterator(it_Collection_collectBy, "i", tp_Collection_T, false);
			createParameter(it_Collection_collectBy, "lambda", _Lambda_Collection_T_Collection_collectBy_V, false);
			it_Collection_collectNested.setType(_Collection_Collection_collectNested_V_T);
			createIterator(it_Collection_collectNested, "i", tp_Collection_T, false);
			createParameter(it_Collection_collectNested, "lambda", _Lambda_Collection_T_Collection_collectNested_V, false);
			it_Collection_collect.setType(_Collection_Collection_collect_V_T);
			createIterator(it_Collection_collect, "i", tp_Collection_T, false);
			createParameter(it_Collection_collect, "lambda", _Lambda_Collection_T_Collection_collect_V, false);
			it_Collection_iterate.setType(tp_Collection_iterate_Tacc);
			it_Collection_iterate.setIsRequired(false);
			createIterator(it_Collection_iterate, "i", tp_Collection_T, false);
			createAccumulator(it_Collection_iterate, "acc", tp_Collection_iterate_Tacc, false);
			createParameter(it_Collection_iterate, "lambda", _Lambda_Collection_T_Collection_iterate_Tacc, false);
			it_Map_collectBy.setType(_Map_Map_K_F_Map_collectBy_V2_F);
			createIterator(it_Map_collectBy, "k", tp_Map_K, false);
			createParameter(it_Map_collectBy, "lambda", _Lambda_Map_K_Map_collectBy_V2, false);
			it_Map_collectNested.setType(_Map_Map_K_F_Map_collectNested_V2_F);
			createIterator(it_Map_collectNested, "k", tp_Map_K, false);
			createParameter(it_Map_collectNested, "lambda", _Lambda_Map_K_Map_collectNested_V2, false);
			it_Map_iterate.setType(tp_Map_iterate_Tacc);
			it_Map_iterate.setIsRequired(false);
			createIterator(it_Map_iterate, "i", tp_Map_K, false);
			createAccumulator(it_Map_iterate, "acc", tp_Map_iterate_Tacc, false);
			createParameter(it_Map_iterate, "lambda", _Lambda_Map_K_Map_iterate_Tacc, false);
			op_Collection_excludesAll.setType(_Boolean);
			createParameter(op_Collection_excludesAll, "c2", _Collection_Collection_excludesAll_T2_T, true);
			op_Collection_flatten.setType(_Collection_Collection_flatten_T2_T);
			op_Collection_includesAll.setType(_Boolean);
			createParameter(op_Collection_includesAll, "c2", _Collection_Collection_includesAll_T2_T, true);
			op_Collection_selectByKind.setType(_Collection_Collection_selectByKind_TT_T);
			createParameter(op_Collection_selectByKind, "type", tp_Collection_selectByKind_TT, true).setIsTypeof(true);
			op_Collection_selectByType.setType(_Collection_Collection_selectByType_TT_T);
			createParameter(op_Collection_selectByType, "type", tp_Collection_selectByType_TT, true).setIsTypeof(true);
			op_Map_excludesAll.setType(_Boolean);
			createParameter(op_Map_excludesAll, "coll", _Collection_Map_excludesAll_K2_T, true);
			op_Map_excludesMap.setType(_Boolean);
			createParameter(op_Map_excludesMap, "map", _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, true);
			op_Map_excludingMap.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_excludingMap, "map", _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, true);
			op_Map_includesAll.setType(_Boolean);
			createParameter(op_Map_includesAll, "coll", _Collection_Map_includesAll_K2_T, true);
			op_Map_includesMap.setType(_Boolean);
			createParameter(op_Map_includesMap, "map", _Map_Map_includesMap_K2_T_Map_includesMap_V2_T, true);
			op_Map_includingMap.setType(_Map_Map_K_T_Map_V_T);
			createParameter(op_Map_includingMap, "map", _Map_Map_includingMap_K2_T_Map_includingMap_V2_T, true);
		}

		private void installSlots7a() {
			// define ALL_PROPERTIES Model
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define CTOR Property
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS CollectionType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define OPERATIONS BagType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES CollectionType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
		}

		private void installSlots7b() {
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			// define PROPERTIES BagType
			checkSuperClass(_Collection_Tuple_T, _OclAny);
			checkSuperClass(_OrderedCollection_Integer_T, _Collection_Integer_T);
			checkSuperClass(_OrderedCollection_String_T, _Collection_String_T);
			checkSuperClass(_OrderedCollection_Bag_T_T, _Collection_Bag_T_T);
			checkSuperClass(_OrderedCollection_Collection_T_T, _Collection_Collection_T_T);
			checkSuperClass(_OrderedCollection_OrderedSet_collectNested_V_T, _Collection_OrderedSet_collectNested_V_T);
			checkSuperClass(_OrderedCollection_OrderedSet_collect_V_T, _Collection_OrderedSet_collect_V_T);
			checkSuperClass(_OrderedCollection_OrderedSet_flatten_T2_T, _Collection_OrderedSet_flatten_T2_T);
			checkSuperClass(_OrderedCollection_OrderedSet_selectByKind_TT_T, _Collection_OrderedSet_selectByKind_TT_T);
			checkSuperClass(_OrderedCollection_OrderedSet_selectByType_TT_T, _Collection_OrderedSet_selectByType_TT_T);
			checkSuperClass(_OrderedCollection_OrderedSet_T_T, _Collection_OrderedSet_T_T);
			checkSuperClass(_OrderedCollection_Sequence_collectNested_V_T, _Collection_Sequence_collectNested_V_T);
			checkSuperClass(_OrderedCollection_Sequence_collect_V_T, _Collection_Sequence_collect_V_T);
			checkSuperClass(_OrderedCollection_Sequence_flatten_T2_T, _Collection_Sequence_flatten_T2_T);
			checkSuperClass(_OrderedCollection_Sequence_selectByKind_TT_T, _Collection_Sequence_selectByKind_TT_T);
			checkSuperClass(_OrderedCollection_Sequence_selectByType_TT_T, _Collection_Sequence_selectByType_TT_T);
			checkSuperClass(_OrderedCollection_Sequence_T_T, _Collection_Sequence_T_T);
			checkSuperClass(_OrderedCollection_Set_T_T, _Collection_Set_T_T);
			checkSuperClass(_OrderedCollection_UniqueCollection_T_T, _Collection_UniqueCollection_T_T);
			checkSuperClass(_UniqueCollection_Tuple_T, _Collection_Tuple_T);
			checkSuperClass(_UniqueCollection_Bag_T_T, _Collection_Bag_T_T);
			checkSuperClass(_UniqueCollection_Collection_T_T, _Collection_Collection_T_T);
			checkSuperClass(_UniqueCollection_Map_K_T, _Collection_Map_K_T);
			checkSuperClass(_UniqueCollection_OclAny_T, _Collection_OclAny_T);
			checkSuperClass(_UniqueCollection_OclElement_T, _Collection_OclElement_T);
			checkSuperClass(_UniqueCollection_OclSelf_T, _Collection_OclSelf_T);
			checkSuperClass(_UniqueCollection_OrderedSet_flatten_T2_T, _Collection_OrderedSet_flatten_T2_T);
			checkSuperClass(_UniqueCollection_OrderedSet_selectByKind_TT_T, _Collection_OrderedSet_selectByKind_TT_T);
			checkSuperClass(_UniqueCollection_OrderedSet_selectByType_TT_T, _Collection_OrderedSet_selectByType_TT_T);
			checkSuperClass(_UniqueCollection_OrderedSet_T_T, _Collection_OrderedSet_T_T);
			checkSuperClass(_UniqueCollection_Sequence_T_T, _Collection_Sequence_T_T);
			checkSuperClass(_UniqueCollection_Set_flatten_T2_T, _Collection_Set_flatten_T2_T);
			checkSuperClass(_UniqueCollection_Set_selectByKind_TT_T, _Collection_Set_selectByKind_TT_T);
			checkSuperClass(_UniqueCollection_Set_selectByType_TT_T, _Collection_Set_selectByType_TT_T);
			checkSuperClass(_UniqueCollection_Set_T_T, _Collection_Set_T_T);
			checkSuperClass(_UniqueCollection_UniqueCollection_T_T, _Collection_UniqueCollection_T_T);
			checkSuperClass(_Bag_Bag_collectNested_V_T, _Collection_Bag_collectNested_V_T);
			checkSuperClass(_Bag_Bag_collect_V_T, _Collection_Bag_collect_V_T);
			checkSuperClass(_Bag_Bag_flatten_T2_T, _Collection_Bag_flatten_T2_T);
			checkSuperClass(_Bag_Bag_selectByKind_TT_T, _Collection_Bag_selectByKind_TT_T);
			checkSuperClass(_Bag_Bag_selectByType_TT_T, _Collection_Bag_selectByType_TT_T);
			checkSuperClass(_Bag_Bag_T_T, _Collection_Bag_T_T);
			checkSuperClass(_Bag_CollectionType_T, _Collection_CollectionType_T);
			checkSuperClass(_Bag_Collection_T_T, _Collection_Collection_T_T);
			checkSuperClass(_Bag_MapType_T, _Collection_MapType_T);
			checkSuperClass(_Bag_Map_collect_V2_T, _Collection_Map_collect_V2_T);
			checkSuperClass(_Bag_Map_V_T, _Collection_Map_V_T);
			checkSuperClass(_Bag_OclElement_T, _Collection_OclElement_T);
			checkSuperClass(_Bag_OclInvalid_T, _Collection_OclInvalid_T);
			checkSuperClass(_Bag_Set_collectNested_V_T, _Collection_Set_collectNested_V_T);
			checkSuperClass(_Bag_Set_collect_V_T, _Collection_Set_collect_V_T);
			addSuperClass(_OrderedSet_OrderedSet_T, _OrderedCollection_OrderedSet_T_T);
			addSuperClass(_OrderedSet_OrderedSet_T, _UniqueCollection_OrderedSet_T_T);
			addSuperClass(_Sequence_Sequence_T, _OrderedCollection_Sequence_T_T);
			addSuperClass(_Set_Set_T, _UniqueCollection_Set_T_T);
			it_Bag_collectNested.setType(_Bag_Bag_collectNested_V_T);
			createIterator(it_Bag_collectNested, "i", tp_Bag_T, false);
			createParameter(it_Bag_collectNested, "lambda", _Lambda_Bag_T_Bag_collectNested_V, false);
			it_Bag_collect.setType(_Bag_Bag_collect_V_T);
			createIterator(it_Bag_collect, "i", tp_Bag_T, false);
			createParameter(it_Bag_collect, "lambda", _Lambda_Bag_T_Bag_collect_V, false);
			it_Bag_reject.setType(_Bag_Bag_T_T);
			createIterator(it_Bag_reject, "i", tp_Bag_T, false);
			createParameter(it_Bag_reject, "lambda", _Lambda_Bag_T_Boolean, true);
			it_Bag_select.setType(_Bag_Bag_T_T);
			createIterator(it_Bag_select, "i", tp_Bag_T, false);
			createParameter(it_Bag_select, "lambda", _Lambda_Bag_T_Boolean, true);
			it_Map_collect.setType(_Bag_Map_collect_V2_T);
			createIterator(it_Map_collect, "k", tp_Map_K, false);
			createParameter(it_Map_collect, "lambda", _Lambda_Map_K_Map_collect_V2, false);
			it_Set_collectNested.setType(_Bag_Set_collectNested_V_T);
			createIterator(it_Set_collectNested, "i", tp_Set_T, false);
			createParameter(it_Set_collectNested, "lambda", _Lambda_Set_T_Set_collectNested_V, false);
			it_Set_collect.setType(_Bag_Set_collect_V_T);
			createIterator(it_Set_collect, "i", tp_Set_T, false);
			createParameter(it_Set_collect, "lambda", _Lambda_Set_T_Set_collect_V, false);
			op_Bag_excluding.setType(_Bag_Bag_T_T);
			createParameter(op_Bag_excluding, "object", tp_Bag_T, false);
			op_Bag_excludingAll.setType(_Bag_Bag_T_T);
			createParameter(op_Bag_excludingAll, "objects", _Collection_Bag_T_T, true);
			op_Bag_flatten.setType(_Bag_Bag_flatten_T2_T);
			op_Bag_including.setType(_Bag_Bag_T_T);
			createParameter(op_Bag_including, "object", tp_Bag_T, false);
			op_Bag_includingAll.setType(_Bag_Bag_T_T);
			createParameter(op_Bag_includingAll, "objects", _Collection_Bag_T_T, true);
			op_Bag_selectByKind.setType(_Bag_Bag_selectByKind_TT_T);
			createParameter(op_Bag_selectByKind, "type", tp_Bag_selectByKind_TT, true).setIsTypeof(true);
			op_Bag_selectByType.setType(_Bag_Bag_selectByType_TT_T);
			createParameter(op_Bag_selectByType, "type", tp_Bag_selectByType_TT, true).setIsTypeof(true);
			op_Collection_asBag.setType(_Bag_Collection_T_T);
			op_Collection_intersection.setType(_Bag_Collection_T_T);
			createParameter(op_Collection_intersection, "c", _Collection_Collection_T_T, true);
			op_Collection_union.setType(_Bag_Collection_T_T);
			createParameter(op_Collection_union, "c", _Collection_Collection_T_T, true);
			op_Map_values.setType(_Bag_Map_V_T);
			op_UniqueCollection__neg_.setType(_UniqueCollection_UniqueCollection_T_T);
			op_UniqueCollection__neg_.setPrecedence(prec_ADDITIVE);
			createParameter(op_UniqueCollection__neg_, "s", _UniqueCollection_OclAny_T, true);
		}

		private void installSlots8() {
			_OrderedSet_Collection_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Collection_T, true, 0, -1);
			_OrderedSet_OrderedSet_flatten_T2_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			_OrderedSet_OrderedSet_selectByKind_TT_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			_OrderedSet_OrderedSet_selectByType_TT_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			_OrderedSet_OrderedSet_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_T, true, 0, -1);
			_OrderedSet_Sequence_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Sequence_T, true, 0, -1);
			_OrderedSet_Set_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Set_T, true, 0, -1);
			_OrderedSet_UniqueCollection_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_UniqueCollection_T, true, 0, -1);
			_Sequence_Integer_T = getCollectionType(_Sequence_Sequence_T, _Integer, true, 0, -1);
			_Sequence_String_T = getCollectionType(_Sequence_Sequence_T, _String, true, 0, -1);
			_Sequence_Bag_T_T = getCollectionType(_Sequence_Sequence_T, tp_Bag_T, true, 0, -1);
			_Sequence_Collection_T_T = getCollectionType(_Sequence_Sequence_T, tp_Collection_T, true, 0, -1);
			_Sequence_OrderedSet_collectNested_V_T = getCollectionType(_Sequence_Sequence_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			_Sequence_OrderedSet_collect_V_T = getCollectionType(_Sequence_Sequence_T, tp_OrderedSet_collect_V, true, 0, -1);
			_Sequence_Sequence_collectNested_V_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_collectNested_V, true, 0, -1);
			_Sequence_Sequence_collect_V_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_collect_V, true, 0, -1);
			_Sequence_Sequence_flatten_T2_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_flatten_T2, true, 0, -1);
			_Sequence_Sequence_selectByKind_TT_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			_Sequence_Sequence_selectByType_TT_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_selectByType_TT, true, 0, -1);
			_Sequence_Sequence_T_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_T, true, 0, -1);
			_Set_Tuple_T = getCollectionType(_Set_Set_T, _Tuple, true, 0, -1);
			_Set_Bag_T_T = getCollectionType(_Set_Set_T, tp_Bag_T, true, 0, -1);
			_Set_Collection_T_T = getCollectionType(_Set_Set_T, tp_Collection_T, true, 0, -1);
			_Set_Map_K_T = getCollectionType(_Set_Set_T, tp_Map_K, true, 0, -1);
			_Set_OclElement_T = getCollectionType(_Set_Set_T, _OclElement, true, 0, -1);
			_Set_OclSelf_T = getCollectionType(_Set_Set_T, _OclSelf, true, 0, -1);
			_Set_OclSelf_F = getCollectionType(_Set_Set_T, _OclSelf, false, 0, -1);
			_Set_Set_flatten_T2_T = getCollectionType(_Set_Set_T, tp_Set_flatten_T2, true, 0, -1);
			_Set_Set_selectByKind_TT_T = getCollectionType(_Set_Set_T, tp_Set_selectByKind_TT, true, 0, -1);
			_Set_Set_selectByType_TT_T = getCollectionType(_Set_Set_T, tp_Set_selectByType_TT, true, 0, -1);
			_Set_Set_T_T = getCollectionType(_Set_Set_T, tp_Set_T, true, 0, -1);
			_Set_UniqueCollection_T_T = getCollectionType(_Set_Set_T, tp_UniqueCollection_T, true, 0, -1);
			// define PROPERTIES Class
			// define PROPERTIES Class
			// define PROPERTIES InvalidType
		}

		private void installSlots9a() {
			// define CTOR Property
			_Lambda_Bag_T_Set = getLambdaType(_OclLambda, tp_Bag_T, _Set_Bag_T_T);
			_Lambda_OrderedSet_T_OrderedSet = getLambdaType(_OclLambda, tp_OrderedSet_T, _OrderedSet_OrderedSet_T_T);
			_Lambda_Sequence_T_OrderedSet = getLambdaType(_OclLambda, tp_Sequence_T, _OrderedSet_Sequence_T_T);
			_Lambda_Set_T_Set = getLambdaType(_OclLambda, tp_Set_T, _Set_Set_T_T);
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS OrderedSetType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SequenceType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define OPERATIONS SetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES OrderedSetType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SequenceType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			// define PROPERTIES SetType
			checkSuperClass(_OrderedSet_Collection_T_T, _OrderedCollection_Collection_T_T);
			checkSuperClass(_OrderedSet_Collection_T_T, _UniqueCollection_Collection_T_T);
			checkSuperClass(_OrderedSet_OrderedSet_flatten_T2_T, _OrderedCollection_OrderedSet_flatten_T2_T);
			checkSuperClass(_OrderedSet_OrderedSet_flatten_T2_T, _UniqueCollection_OrderedSet_flatten_T2_T);
			checkSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _OrderedCollection_OrderedSet_selectByKind_TT_T);
			checkSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _UniqueCollection_OrderedSet_selectByKind_TT_T);
			checkSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _OrderedCollection_OrderedSet_selectByType_TT_T);
			checkSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _UniqueCollection_OrderedSet_selectByType_TT_T);
			checkSuperClass(_OrderedSet_OrderedSet_T_T, _OrderedCollection_OrderedSet_T_T);
			checkSuperClass(_OrderedSet_OrderedSet_T_T, _UniqueCollection_OrderedSet_T_T);
			checkSuperClass(_OrderedSet_Sequence_T_T, _OrderedCollection_Sequence_T_T);
			checkSuperClass(_OrderedSet_Sequence_T_T, _UniqueCollection_Sequence_T_T);
			checkSuperClass(_OrderedSet_Set_T_T, _OrderedCollection_Set_T_T);
			checkSuperClass(_OrderedSet_Set_T_T, _UniqueCollection_Set_T_T);
			checkSuperClass(_OrderedSet_UniqueCollection_T_T, _OrderedCollection_UniqueCollection_T_T);
			checkSuperClass(_OrderedSet_UniqueCollection_T_T, _UniqueCollection_UniqueCollection_T_T);
			checkSuperClass(_Sequence_Integer_T, _OrderedCollection_Integer_T);
			checkSuperClass(_Sequence_String_T, _OrderedCollection_String_T);
			checkSuperClass(_Sequence_Bag_T_T, _OrderedCollection_Bag_T_T);
			checkSuperClass(_Sequence_Collection_T_T, _OrderedCollection_Collection_T_T);
			checkSuperClass(_Sequence_OrderedSet_collectNested_V_T, _OrderedCollection_OrderedSet_collectNested_V_T);
			checkSuperClass(_Sequence_OrderedSet_collect_V_T, _OrderedCollection_OrderedSet_collect_V_T);
			checkSuperClass(_Sequence_Sequence_collectNested_V_T, _OrderedCollection_Sequence_collectNested_V_T);
			checkSuperClass(_Sequence_Sequence_collect_V_T, _OrderedCollection_Sequence_collect_V_T);
			checkSuperClass(_Sequence_Sequence_flatten_T2_T, _OrderedCollection_Sequence_flatten_T2_T);
			checkSuperClass(_Sequence_Sequence_selectByKind_TT_T, _OrderedCollection_Sequence_selectByKind_TT_T);
			checkSuperClass(_Sequence_Sequence_selectByType_TT_T, _OrderedCollection_Sequence_selectByType_TT_T);
			checkSuperClass(_Sequence_Sequence_T_T, _OrderedCollection_Sequence_T_T);
			checkSuperClass(_Set_Tuple_T, _UniqueCollection_Tuple_T);
			checkSuperClass(_Set_Bag_T_T, _UniqueCollection_Bag_T_T);
			checkSuperClass(_Set_Collection_T_T, _UniqueCollection_Collection_T_T);
			checkSuperClass(_Set_Map_K_T, _UniqueCollection_Map_K_T);
			checkSuperClass(_Set_OclElement_T, _UniqueCollection_OclElement_T);
			checkSuperClass(_Set_OclSelf_T, _UniqueCollection_OclSelf_T);
			checkSuperClass(_Set_OclSelf_F, _UniqueCollection_OclSelf_T);
			checkSuperClass(_Set_Set_flatten_T2_T, _UniqueCollection_Set_flatten_T2_T);
			checkSuperClass(_Set_Set_selectByKind_TT_T, _UniqueCollection_Set_selectByKind_TT_T);
			checkSuperClass(_Set_Set_selectByType_TT_T, _UniqueCollection_Set_selectByType_TT_T);
			checkSuperClass(_Set_Set_T_T, _UniqueCollection_Set_T_T);
		}

		private void installSlots9b() {
			checkSuperClass(_Set_UniqueCollection_T_T, _UniqueCollection_UniqueCollection_T_T);
			it_Bag_sortedBy.setType(_Sequence_Bag_T_T);
			createIterator(it_Bag_sortedBy, "i", tp_Bag_T, false);
			createParameter(it_Bag_sortedBy, "lambda", _Lambda_Bag_T_OclAny, false);
			it_Collection_sortedBy.setType(_Sequence_Collection_T_T);
			createIterator(it_Collection_sortedBy, "i", tp_Collection_T, false);
			createParameter(it_Collection_sortedBy, "lambda", _Lambda_Collection_T_OclAny, false);
			it_OrderedSet_collectNested.setType(_Sequence_OrderedSet_collectNested_V_T);
			createIterator(it_OrderedSet_collectNested, "i", tp_OrderedSet_T, false);
			createParameter(it_OrderedSet_collectNested, "lambda", _Lambda_OrderedSet_T_OrderedSet_collectNested_V, false);
			it_OrderedSet_collect.setType(_Sequence_OrderedSet_collect_V_T);
			createIterator(it_OrderedSet_collect, "i", tp_OrderedSet_T, false);
			createParameter(it_OrderedSet_collect, "lambda", _Lambda_OrderedSet_T_OrderedSet_collect_V, false);
			it_OrderedSet_reject.setType(_OrderedSet_OrderedSet_T_T);
			createIterator(it_OrderedSet_reject, "i", tp_OrderedSet_T, false);
			createParameter(it_OrderedSet_reject, "lambda", _Lambda_OrderedSet_T_Boolean, true);
			it_OrderedSet_select.setType(_OrderedSet_OrderedSet_T_T);
			createIterator(it_OrderedSet_select, "i", tp_OrderedSet_T, false);
			createParameter(it_OrderedSet_select, "lambda", _Lambda_OrderedSet_T_Boolean, true);
			it_OrderedSet_sortedBy.setType(_OrderedSet_OrderedSet_T_T);
			createIterator(it_OrderedSet_sortedBy, "i", tp_OrderedSet_T, false);
			createParameter(it_OrderedSet_sortedBy, "lambda", _Lambda_OrderedSet_T_OclAny, false);
			it_Sequence_collectNested.setType(_Sequence_Sequence_collectNested_V_T);
			createIterator(it_Sequence_collectNested, "i", tp_Sequence_T, false);
			createParameter(it_Sequence_collectNested, "lambda", _Lambda_Sequence_T_Sequence_collectNested_V, false);
			it_Sequence_collect.setType(_Sequence_Sequence_collect_V_T);
			createIterator(it_Sequence_collect, "i", tp_Sequence_T, false);
			createParameter(it_Sequence_collect, "lambda", _Lambda_Sequence_T_Sequence_collect_V, false);
			it_Sequence_reject.setType(_Sequence_Sequence_T_T);
			createIterator(it_Sequence_reject, "i", tp_Sequence_T, false);
			createParameter(it_Sequence_reject, "lambda", _Lambda_Sequence_T_Boolean, true);
			it_Sequence_select.setType(_Sequence_Sequence_T_T);
			createIterator(it_Sequence_select, "i", tp_Sequence_T, false);
			createParameter(it_Sequence_select, "lambda", _Lambda_Sequence_T_Boolean, true);
			it_Sequence_sortedBy.setType(_Sequence_Sequence_T_T);
			createIterator(it_Sequence_sortedBy, "i", tp_Sequence_T, false);
			createParameter(it_Sequence_sortedBy, "lambda", _Lambda_Sequence_T_OclAny, false);
			it_Set_reject.setType(_Set_Set_T_T);
			createIterator(it_Set_reject, "i", tp_Set_T, false);
			createParameter(it_Set_reject, "lambda", _Lambda_Set_T_Boolean, true);
			it_Set_select.setType(_Set_Set_T_T);
			createIterator(it_Set_select, "i", tp_Set_T, false);
			createParameter(it_Set_select, "lambda", _Lambda_Set_T_Boolean, true);
			it_Set_sortedBy.setType(_OrderedSet_Set_T_T);
			createIterator(it_Set_sortedBy, "i", tp_Set_T, false);
			createParameter(it_Set_sortedBy, "lambda", _Lambda_Set_T_OclAny, false);
			it_UniqueCollection_sortedBy.setType(_OrderedSet_UniqueCollection_T_T);
			createIterator(it_UniqueCollection_sortedBy, "i", tp_UniqueCollection_T, false);
			createParameter(it_UniqueCollection_sortedBy, "lambda", _Lambda_UniqueCollection_T_OclAny, false);
			op_Boolean_allInstances.setType(_Set_OclSelf_T);
			createParameter(op_Boolean_allInstances, "dummy", _Integer, true);
			op_String_characters.setType(_Sequence_String_T);
			op_String_tokenize.setType(_Sequence_String_T);
			op_String_tokenize_1.setType(_Sequence_String_T);
			createParameter(op_String_tokenize_1, "delimiters", _String, true);
			op_String_tokenize_2.setType(_Sequence_String_T);
			createParameter(op_String_tokenize_2, "delimiters", _String, true);
			createParameter(op_String_tokenize_2, "returnDelimiters", _Boolean, true);
			op_BooleanType_allInstances.setType(_Set_OclSelf_T);
			op_Class_allInstances.setType(_Set_OclSelf_T);
			op_Collection_asOrderedSet.setType(_OrderedSet_Collection_T_T);
			op_Collection_asSequence.setType(_Sequence_Collection_T_T);
			op_Collection_asSet.setType(_Set_Collection_T_T);
			op_Collection_intersection_1.setType(_Set_Collection_T_T);
			createParameter(op_Collection_intersection_1, "u", _UniqueCollection_Collection_T_T, true);
			op_Collection_product.setType(_Set_Tuple_T);
			createParameter(op_Collection_product, "c2", _Collection_Collection_product_T2_T, true);
			op_Enumeration_allInstances.setType(_Set_OclSelf_T);
			op_InvalidType_allInstances.setType(_Set_OclSelf_T);
			op_Map_keys.setType(_Set_Map_K_T);
			op_OclAny_oclAsSet.setType(_Set_OclSelf_T);
			op_OclAny_oclTypes.setType(_Set_OclSelf_T);
			op_OclElement_allInstances.setType(_Set_OclSelf_T);
			createParameter(op_OclElement_allInstances, "dummy", _Integer, true);
			op_OclElement_oclContents.setType(_Set_OclElement_T);
			op_OclElement_oclExtensions.setType(_Set_OclElement_T);
			op_OclElement_oclExtensions_1.setType(_Set_OclElement_T);
			createParameter(op_OclElement_oclExtensions_1, "stereotype", _OclStereotype, true);
			op_OclElement_oclModelTypes.setType(_Set_OclSelf_T);
			op_OclEnumeration_allInstances.setType(_Set_OclSelf_T);
			createParameter(op_OclEnumeration_allInstances, "dummy", _Integer, true);
			op_OclInvalid_allInstances.setType(_Set_OclSelf_T);
			createParameter(op_OclInvalid_allInstances, "dummy", _Integer, true);
			op_OclInvalid_oclAsSet.setType(_Set_OclSelf_T);
			op_OclStereotype_allInstances.setType(_Set_OclSelf_T);
			createParameter(op_OclStereotype_allInstances, "dummy", _Integer, true);
			op_OclVoid_allInstances.setType(_Set_OclSelf_F);
			createParameter(op_OclVoid_allInstances, "dummy", _Integer, true);
			op_OclVoid_oclAsSet.setType(_Set_OclSelf_T);
			op_OclVoid_oclTypes.setType(_Set_OclSelf_T);
			op_OrderedSet__neg_.setType(_OrderedSet_OrderedSet_T_T);
			op_OrderedSet__neg_.setPrecedence(prec_ADDITIVE);
			createParameter(op_OrderedSet__neg_, "s", _UniqueCollection_OclAny_T, true);
			op_OrderedSet_append.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_append, "object", tp_OrderedSet_T, false);
			op_OrderedSet_appendAll.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_appendAll, "objects", _OrderedCollection_OrderedSet_T_T, true);
			op_OrderedSet_excluding.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_excluding, "object", tp_OrderedSet_T, false);
			op_OrderedSet_excludingAll.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_excludingAll, "objects", _Collection_OrderedSet_T_T, true);
			op_OrderedSet_flatten.setType(_OrderedSet_OrderedSet_flatten_T2_T);
			op_OrderedSet_including.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_including, "object", tp_OrderedSet_T, false);
			op_OrderedSet_includingAll.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_includingAll, "objects", _Collection_OrderedSet_T_T, true);
			op_OrderedSet_insertAt.setType(_OrderedSet_OrderedSet_T_T);
			op_OrderedSet_insertAt.setIsInvalidating(true);
			createParameter(op_OrderedSet_insertAt, "index", _Integer, true);
			createParameter(op_OrderedSet_insertAt, "object", tp_OrderedSet_T, false);
			op_OrderedSet_prepend.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_prepend, "object", tp_OrderedSet_T, false);
			op_OrderedSet_prependAll.setType(_OrderedSet_OrderedSet_T_T);
			createParameter(op_OrderedSet_prependAll, "objects", _OrderedCollection_OrderedSet_T_T, true);
			op_OrderedSet_reverse.setType(_OrderedSet_OrderedSet_T_T);
			op_OrderedSet_selectByKind.setType(_OrderedSet_OrderedSet_selectByKind_TT_T);
			createParameter(op_OrderedSet_selectByKind, "type", tp_OrderedSet_selectByKind_TT, true).setIsTypeof(true);
			op_OrderedSet_selectByType.setType(_OrderedSet_OrderedSet_selectByType_TT_T);
			createParameter(op_OrderedSet_selectByType, "type", tp_OrderedSet_selectByType_TT, true).setIsTypeof(true);
			op_OrderedSet_subOrderedSet.setType(_OrderedSet_OrderedSet_T_T);
			op_OrderedSet_subOrderedSet.setIsInvalidating(true);
			createParameter(op_OrderedSet_subOrderedSet, "lower", _Integer, true);
			createParameter(op_OrderedSet_subOrderedSet, "upper", _Integer, true);
			op_Sequence_append.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_append, "object", tp_Sequence_T, false);
			op_Sequence_appendAll.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_appendAll, "objects", _OrderedCollection_Sequence_T_T, true);
			op_Sequence_excluding.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_excluding, "object", tp_Sequence_T, false);
			op_Sequence_excludingAll.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_excludingAll, "objects", _Collection_Sequence_T_T, true);
			op_Sequence_flatten.setType(_Sequence_Sequence_flatten_T2_T);
			op_Sequence_including.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_including, "object", tp_Sequence_T, false);
			op_Sequence_includingAll.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_includingAll, "objects", _Collection_Sequence_T_T, true);
			op_Sequence_insertAt.setType(_Sequence_Sequence_T_T);
			op_Sequence_insertAt.setIsInvalidating(true);
			createParameter(op_Sequence_insertAt, "index", _Integer, true);
			createParameter(op_Sequence_insertAt, "object", tp_Sequence_T, false);
			op_Sequence_prepend.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_prepend, "object", tp_Sequence_T, false);
			op_Sequence_prependAll.setType(_Sequence_Sequence_T_T);
			createParameter(op_Sequence_prependAll, "objects", _OrderedCollection_Sequence_T_T, true);
			op_Sequence_reverse.setType(_Sequence_Sequence_T_T);
			op_Sequence_selectByKind.setType(_Sequence_Sequence_selectByKind_TT_T);
			createParameter(op_Sequence_selectByKind, "type", tp_Sequence_selectByKind_TT, true).setIsTypeof(true);
			op_Sequence_selectByType.setType(_Sequence_Sequence_selectByType_TT_T);
			createParameter(op_Sequence_selectByType, "type", tp_Sequence_selectByType_TT, true).setIsTypeof(true);
			op_Sequence_subSequence.setType(_Sequence_Sequence_T_T);
			op_Sequence_subSequence.setIsInvalidating(true);
			createParameter(op_Sequence_subSequence, "lower", _Integer, true);
			createParameter(op_Sequence_subSequence, "upper", _Integer, true);
			op_Set__neg_.setType(_Set_Set_T_T);
			op_Set__neg_.setPrecedence(prec_ADDITIVE);
			createParameter(op_Set__neg_, "s", _UniqueCollection_OclAny_T, true);
			op_Set_excluding.setType(_Set_Set_T_T);
			createParameter(op_Set_excluding, "object", tp_Set_T, false);
			op_Set_excludingAll.setType(_Set_Set_T_T);
			createParameter(op_Set_excludingAll, "objects", _Collection_Set_T_T, true);
			op_Set_flatten.setType(_Set_Set_flatten_T2_T);
			op_Set_including.setType(_Set_Set_T_T);
			createParameter(op_Set_including, "object", tp_Set_T, false);
			op_Set_includingAll.setType(_Set_Set_T_T);
			createParameter(op_Set_includingAll, "objects", _Collection_Set_T_T, true);
			op_Set_selectByKind.setType(_Set_Set_selectByKind_TT_T);
			createParameter(op_Set_selectByKind, "type", tp_Set_selectByKind_TT, true).setIsTypeof(true);
			op_Set_selectByType.setType(_Set_Set_selectByType_TT_T);
			createParameter(op_Set_selectByType, "type", tp_Set_selectByType_TT, true).setIsTypeof(true);
			op_Stereotype_allInstances.setType(_Set_OclSelf_T);
			op_UniqueCollection_intersection.setType(_Set_UniqueCollection_T_T);
			createParameter(op_UniqueCollection_intersection, "c", _Collection_UniqueCollection_T_T, true);
			op_UniqueCollection_symmetricDifference.setType(_Set_UniqueCollection_T_T);
			createParameter(op_UniqueCollection_symmetricDifference, "s", _UniqueCollection_OclAny_T, true);
			op_UniqueCollection_union.setType(_Set_UniqueCollection_T_T);
			createParameter(op_UniqueCollection_union, "s", _UniqueCollection_UniqueCollection_T_T, true);
			op_VoidType_allInstances.setType(_Set_OclSelf_F);
		}

		private void installSlots10() {
			// define FRAGMENT_OPERATIONS Model
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define OPERATIONS LambdaType
			// define PROPERTIES Class
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			// define PROPERTIES LambdaType
			addSuperClass(_Lambda_Bag_T_Set, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_OrderedSet, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set, _OclLambda);
			it_Bag_closure.setType(_Set_Bag_T_T);
			createIterator(it_Bag_closure, "i", tp_Bag_T, true);
			createParameter(it_Bag_closure, "lambda", _Lambda_Bag_T_Set, false);
			it_OrderedSet_closure.setType(_OrderedSet_OrderedSet_T_T);
			createIterator(it_OrderedSet_closure, "i", tp_OrderedSet_T, true);
			createParameter(it_OrderedSet_closure, "lambda", _Lambda_OrderedSet_T_OrderedSet, false);
			it_Sequence_closure.setType(_OrderedSet_Sequence_T_T);
			createIterator(it_Sequence_closure, "i", tp_Sequence_T, true);
			createParameter(it_Sequence_closure, "lambda", _Lambda_Sequence_T_OrderedSet, false);
			it_Set_closure.setType(_Set_Set_T_T);
			createIterator(it_Set_closure, "i", tp_Set_T, true);
			createParameter(it_Set_closure, "lambda", _Lambda_Set_T_Set, false);
		}

		private void installSlots11() {
			// define FRAGMENT_PROPERTIES Model
		}

		private void installSlots12() {
			// define ENUMERATION_LITERALS Model
		}

		private void installProperties() {
			Property pr_CollectionType_elementType;
			Property pr_MapType_keyType;
			Property pr_MapType_valueType;
			Property pr_OclAny_OclInvalid_oclBadProperty;
			Property pr_OclElement_oclContainer;
			Property pr_OclElement_oclContents;
			Property pr_OclElement_OclElement_oclContainer;
			Property pr_OclElement_OclElement_oclContents;
			Property pr_OclInvalid_oclBadProperty;
			Property pr_Type_CollectionType_elementType;
			Property pr_Type_MapType_keyType;
			Property pr_Type_MapType_valueType;
			List<Property> ownedProperties;
			Property property;

			ownedProperties = _Collection_Collection_T.getOwnedProperties();
			ownedProperties.add(property = createProperty("elementType", tp_Collection_T));
			property.setIsResolveProxies(true);
			property.setIsStatic(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			installComment(property, "Evaluates to the type of the collection elements.\n\n@Deprecated Use regular CollectionType property");
			ownedProperties.add(property = createProperty("lower", _Integer));
			property.setIsResolveProxies(true);
			property.setIsStatic(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			installComment(property, "Evaluates to the lower bound on the number of collection elements.\n\n@Deprecated Use regular CollectionType property");
			ownedProperties.add(property = createProperty("upper", _Integer));
			property.setIsResolveProxies(true);
			property.setIsStatic(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			installComment(property, "Evaluates to the upper bound on the number of collection elements.\n\n@Deprecated Use regular CollectionType property");

			ownedProperties = _CollectionType.getOwnedProperties();
			ownedProperties.add(property = pr_CollectionType_elementType = createProperty("elementType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty.INSTANCE);
			installComment(property, "Evaluates to the type of the collection elements.");
			ownedProperties.add(property = createProperty("lower", _Integer));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.collection.CollectionLowerProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.collection.CollectionLowerProperty.INSTANCE);
			installComment(property, "Evaluates to the lower bound on the number of collection elements.");
			ownedProperties.add(property = createProperty("upper", _Integer));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.collection.CollectionUpperProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.collection.CollectionUpperProperty.INSTANCE);
			installComment(property, "Evaluates to the upper bound on the number of collection elements.");

			ownedProperties = _Map_Map_K_Map_V.getOwnedProperties();
			ownedProperties.add(property = createProperty("keyType", tp_Map_K));
			property.setIsResolveProxies(true);
			property.setIsStatic(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			installComment(property, "The key type of the key-value pairs of oclText[self].\n\n@Deprecated Use regular MapType property");
			ownedProperties.add(property = createProperty("valueType", tp_Map_V));
			property.setIsResolveProxies(true);
			property.setIsStatic(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE);
			installComment(property, "The value type of the key-value pairs of oclText[self].\n\n@Deprecated Use regular MapType property");

			ownedProperties = _MapType.getOwnedProperties();
			ownedProperties.add(property = pr_MapType_keyType = createProperty("keyType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty.INSTANCE);
			installComment(property, "The key type of the key-value pairs of oclText[self].");
			ownedProperties.add(property = pr_MapType_valueType = createProperty("valueType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.map.MapValueTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.map.MapValueTypeProperty.INSTANCE);
			installComment(property, "The value type of the key-value pairs of oclText[self].");

			ownedProperties = _OclAny.getOwnedProperties();
			ownedProperties.add(property = pr_OclAny_OclInvalid_oclBadProperty = createProperty("OclInvalid", _Bag_OclInvalid_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);

			ownedProperties = _OclElement.getOwnedProperties();
			ownedProperties.add(property = pr_OclElement_oclContainer = createProperty("oclContainer", _OclElement));
			property.setIsRequired(false);
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty.INSTANCE);
			installComment(property, "The object for which self is a composed content or null if there is no such object.");
			ownedProperties.add(property = pr_OclElement_oclContents = createProperty("oclContents", _Set_OclElement_T));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty.INSTANCE);
			installComment(property, "The composed contents of self.");
			ownedProperties.add(property = pr_OclElement_OclElement_oclContainer = createProperty("OclElement", _Bag_OclElement_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			ownedProperties.add(property = pr_OclElement_OclElement_oclContents = createProperty("OclElement", _Bag_OclElement_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);

			ownedProperties = _OclInvalid.getOwnedProperties();
			ownedProperties.add(property = pr_OclInvalid_oclBadProperty = createProperty("oclBadProperty", _OclAny));
			property.setIsRequired(false);
			property.setIsResolveProxies(true);
			installComment(property, "An oclBadProperty may be used as a placeholder in an unsuccessfully created OCLExpression.");

			ownedProperties = _Type.getOwnedProperties();
			ownedProperties.add(property = pr_Type_CollectionType_elementType = createProperty("CollectionType", _Bag_CollectionType_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			ownedProperties.add(property = pr_Type_MapType_keyType = createProperty("MapType", _Bag_MapType_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			ownedProperties.add(property = pr_Type_MapType_valueType = createProperty("MapType", _Bag_MapType_T));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);

			setOpposites(pr_CollectionType_elementType, pr_Type_CollectionType_elementType);
			setOpposites(pr_MapType_keyType, pr_Type_MapType_keyType);
			setOpposites(pr_MapType_valueType, pr_Type_MapType_valueType);
			setOpposites(pr_OclAny_OclInvalid_oclBadProperty, pr_OclInvalid_oclBadProperty);
			setOpposites(pr_OclElement_oclContainer, pr_OclElement_OclElement_oclContainer);
			setOpposites(pr_OclElement_oclContents, pr_OclElement_OclElement_oclContents);
		}
	}
}
