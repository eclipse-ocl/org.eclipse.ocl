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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLmetamodel;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;

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
	private static OCLstdlib INSTANCE = null;

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
	public static @Nullable OCLstdlib basicGetDefault() {
		return INSTANCE;
	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/Library standard Library Resource.
	 *  This static definition auto-generated from /org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 * It cannot be unloaded or rather unloading has no effect.
	 */
	public static @NonNull OCLstdlib getDefault() {
		OCLstdlib oclstdlib = INSTANCE;
		if (oclstdlib == null) {
			String asURI = STDLIB_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
			oclstdlib = INSTANCE = new ReadOnly(asURI);
			Contents contents = new Contents(oclstdlib, "http://www.eclipse.org/ocl/2015/Library");
			oclstdlib.setSaveable(false);
		}
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
		INSTANCE = null;
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
			return OCLmetamodel.PIVOT_URI.equals(metamodelURI);
		}

		/**
		 * Overridden to trivialise loading of the shared instance.
		 */
		@Override
		public void load(Map<?, ?> options) throws IOException {
			if (this != INSTANCE) {
				super.load(options);
			}
			else {
				setLoaded(true);
			}
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
		private final @NonNull Package orphanPackage;

		private Contents(@NonNull  Resource resource, @NonNull String asURI)
		{
			model = createModel(asURI);
			resource.getContents().add(model);
			ocl = createLibrary("ocl", "ocl", "http://www.eclipse.org/ocl/2015/Library", IdManager.METAMODEL, OCLstdlibPackage.eINSTANCE);
			installComment(ocl, "This clause describes the OCL Standard Library of predefined types, their operations, and predefined expression templates in the OCL.\nThis sub clause contains all standard types defined within OCL, including all the operations defined on those types.\nFor each operation the signature and a description of the semantics is given.\nWithin the description, the reserved word \u2018result\u2019 is used to refer to the value that results from evaluating the operation.\nIn several places, post conditions are used to describe properties of the result.\nWhen there is more than one postcondition, all postconditions must be true.\nA similar thing is true for multiple preconditions.\nIf these are used, the operation is only defined if all preconditions evaluate to oclText[true].\n\nheading:1[Introduction]\n\nThe structure, syntax, and semantics of the OCL is defined in Clauses 8 (\u201CAbstract Syntax\u201D), 9 (\u201CConcrete Syntax\u201D),\nand 10 (\u201CSemantics Described using UML\u201D).\nThis sub clause adds another part to the OCL definition: a library of predefined types and operations.\nAny implementation of OCL must include this library package. This approach has also been taken by e.g., the Java definition,\nwhere the language definition and the standard libraries are both mandatory parts of the complete language definition.\n\nThe OCL standard library defines a number of types.\nIt includes several primitive types: UnlimitedNatural, Integer, Real, String, and Boolean.\nThese are familiar from many other languages. The second part of the standard library consists of the collection types.\nThey are Bag, Set, Sequence, and Collection where Collection is an abstract type.\nNote that all types defined in the OCL standard library are instances of an abstract syntax class.\nThe OCL standard library exists at the modeling level, also referred to as the M1 level, where the abstract syntax is the metalevel or M2 level.\n\nNext to definitions of types the OCL standard library defines a number of template expressions.\nMany operations defined on collections map not on the abstract syntax metaclass FeatureCallExp, but on the IteratorExp.\nFor each of these a template expression that defines the name and format of the expression is defined in 11.8, Predefined Iterator Expressions.\n\nThe Standard Library may be extended with new types, new operations and new iterators.\nIn particular new operations can be defined for collections.\n\nCertain String operations depend on the prevailing locale to ensure that Strings are collated and characters are case-converted\nin an appropriate fashion.\nA locale is defined as a concatenation of up to three character sequences separated by underscores,\nwith the first sequence identifying the language and the second sequence identifying the country.\nThe third sequence is empty but may encode an implementation-specific variant.\nTrailing empty strings and separators may be omitted.\n\nThe character sequences for languages are defined by ISO 639.\n\nThe character sequences for countries are defined by ISO 3166.\n\n\u2018fr_CA\u2019 therefore identifies the locale for the French language in the Canada country.\n\nComparison of strings and consequently the collation order of Collection::sortedBy()\nconforms to the Unicode Collation algorithm defined by Unicode Technical Standard#10.\n\nThe locale is \u2018en_us\u2019 by default but may be configured by a property constraint on OclAny::oclLocale.\n\nThe prevailing locale is defined by the prevailing value of oclLocale within the current environment;\nit may therefore be changed temporarily by using a Let expression.\nlet oclLocale : String = \u2018fr_CA\u2019 in aString.toUpperCase()\n\nheading:1[Iterators]\n\nThis sub clause defines the standard OCL iterator expressions.\nIn the abstract syntax these are all instances of IteratorExp.\nThese iterator expressions always have a collection expression as their source,\nas is defined in the well-formedness rules in Clause 8 (\u201CAbstract Syntax\u201D).\nThe defined iterator expressions are shown per source collection type.\nThe semantics of each iterator expression is defined through a mapping from the iterator to the \u2018iterate\u2019 construct.\nThis means that the semantics of the iterator expressions do not need to be defined separately in the semantics sub clauses.\n\nIn all of the following OCL expressions, the lefthand side of the equals sign is the IteratorExp to be defined,\nand the righthand side of the equals sign is the equivalent as an IterateExp.\nThe names source, body, and iterator refer to the role names in the abstract syntax:\n\nsource\tThe source expression of the IteratorExp.\n\nbody\tThe body expression of the IteratorExp.\n\niterator\tThe iterator variable of the IteratorExp.\n\nresult\tThe result variable of the IterateExp.\n\nheading:2[Extending the Standard Library with Iterator Expressions]\n\nIt is possible to add new iterator expressions in the standard library.\nIf this is done the semantics of a new iterator should be defined by mapping it to existing constructs,\nin the same way the semantics of pre-defined iterators is done (see sub clause 11.9)");
			orphanPackage = createPackage("$$", "orphanage", "http://www.eclipse.org/ocl/2015/Orphanage", null, null);
			installPackages();
			installPrecedences();
			installTemplateParameters();
			installClassTypes();
			installPrimitiveTypes();
			installGenericCollectionTypes();
			installGenericMapTypes();
			installTupleTypes();
			installSpecializedCollectionTypes();
			installSpecializedMapTypes();
			installLambdaTypes();
			installOperations();
			installIterations();
			installProperties();
		}

		public @NonNull Model getModel() {
			return model;
		}

		private void installPackages() {
			model.getOwnedPackages().add(ocl);
			model.getOwnedPackages().add(orphanPackage);
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

		private void installTemplateParameters() {
			tp_UnlimitedNatural_oclAsType_TT = createTemplateParameter("TT");
			tp_Bag_collectNested_V = createTemplateParameter("V");
			tp_Bag_collect_V = createTemplateParameter("V");
			tp_Bag_flatten_T2 = createTemplateParameter("T2");
			tp_Bag_selectByKind_TT = createTemplateParameter("TT");
			tp_Bag_selectByType_TT = createTemplateParameter("TT");
			tp_Bag_T = createTemplateParameter("T");
			tp_Collection_collectBy_V = createTemplateParameter("V");
			tp_Collection_collectNested_V = createTemplateParameter("V");
			tp_Collection_collect_V = createTemplateParameter("V");
			tp_Collection_excludesAll_T2 = createTemplateParameter("T2");
			tp_Collection_flatten_T2 = createTemplateParameter("T2");
			tp_Collection_includesAll_T2 = createTemplateParameter("T2");
			tp_Collection_iterate_Tacc = createTemplateParameter("Tacc");
			tp_Collection_product_T2 = createTemplateParameter("T2");
			tp_Collection_selectByKind_TT = createTemplateParameter("TT");
			tp_Collection_selectByType_TT = createTemplateParameter("TT");
			tp_Collection_T = createTemplateParameter("T");
			tp_Map_collectBy_V2 = createTemplateParameter("V2");
			tp_Map_collectNested_V2 = createTemplateParameter("V2");
			tp_Map_collect_V2 = createTemplateParameter("V2");
			tp_Map_excludesAll_K2 = createTemplateParameter("K2");
			tp_Map_excludesMap_K2 = createTemplateParameter("K2");
			tp_Map_excludesMap_V2 = createTemplateParameter("V2");
			tp_Map_excludingMap_K2 = createTemplateParameter("K2");
			tp_Map_excludingMap_V2 = createTemplateParameter("V2");
			tp_Map_includesAll_K2 = createTemplateParameter("K2");
			tp_Map_includesMap_K2 = createTemplateParameter("K2");
			tp_Map_includesMap_V2 = createTemplateParameter("V2");
			tp_Map_includingMap_K2 = createTemplateParameter("K2");
			tp_Map_includingMap_V2 = createTemplateParameter("V2");
			tp_Map_iterate_Tacc = createTemplateParameter("Tacc");
			tp_Map_K = createTemplateParameter("K");
			tp_Map_V = createTemplateParameter("V");
			tp_OclAny_oclAsType_TT = createTemplateParameter("TT");
			tp_OclElement_oclAsModelType_TT = createTemplateParameter("TT");
			tp_OclInvalid_oclAsType_TT = createTemplateParameter("TT");
			tp_OclVoid_oclAsType_TT = createTemplateParameter("TT");
			tp_OrderedCollection_T = createTemplateParameter("T");
			tp_OrderedSet_collectNested_V = createTemplateParameter("V");
			tp_OrderedSet_collect_V = createTemplateParameter("V");
			tp_OrderedSet_flatten_T2 = createTemplateParameter("T2");
			tp_OrderedSet_selectByKind_TT = createTemplateParameter("TT");
			tp_OrderedSet_selectByType_TT = createTemplateParameter("TT");
			tp_OrderedSet_T = createTemplateParameter("T");
			tp_Sequence_collectNested_V = createTemplateParameter("V");
			tp_Sequence_collect_V = createTemplateParameter("V");
			tp_Sequence_flatten_T2 = createTemplateParameter("T2");
			tp_Sequence_selectByKind_TT = createTemplateParameter("TT");
			tp_Sequence_selectByType_TT = createTemplateParameter("TT");
			tp_Sequence_T = createTemplateParameter("T");
			tp_Set_collectNested_V = createTemplateParameter("V");
			tp_Set_collect_V = createTemplateParameter("V");
			tp_Set_flatten_T2 = createTemplateParameter("T2");
			tp_Set_selectByKind_TT = createTemplateParameter("TT");
			tp_Set_selectByType_TT = createTemplateParameter("TT");
			tp_Set_T = createTemplateParameter("T");
			tp_UniqueCollection_T = createTemplateParameter("T");
		}

		private Class _BooleanType;
		private Class _Class;
		private Class _CollectionType;
		private Class _Enumeration;
		private Class _EnumerationLiteral;
		private Class _InvalidType;
		private Class _MapType;
		private AnyType _OclAny;
		private Class _OclComparable;
		private Class _OclElement;
		private Class _OclEnumeration;
		private InvalidType _OclInvalid;
		private Class _OclLambda;
		private Class _OclMessage;
		private SelfType _OclSelf;
		private Class _OclState;
		private Class _OclStereotype;
		private Class _OclSummable;
		private Class _OclTuple;
		private Class _OclType;
		private VoidType _OclVoid;
		private Class _State;
		private Class _Stereotype;
		private Class _Type;
		private Class _VoidType;

		private void installClassTypes() {
			List<Class> ownedClasses;
			Class type;

			ownedClasses = ocl.getOwnedClasses();
			ownedClasses.add(type = _BooleanType = createClass("BooleanType"));
			installComment(type, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			ownedClasses.add(type = _Class = createClass("Class"));
			ownedClasses.add(type = _CollectionType = createClass("CollectionType"));
			ownedClasses.add(type = _Enumeration = createClass("Enumeration"));
			installComment(type, "@Deprecated: Use OclEnumeration\nThe Enumeration type is the type of an OrderedSet of EnumerationLiteral.");
			ownedClasses.add(type = _EnumerationLiteral = createClass("EnumerationLiteral"));
			installComment(type, "The standard type EnumerationLiteral represents a named constant value of an Enumeration.");
			ownedClasses.add(type = _InvalidType = createClass("InvalidType"));
			ownedClasses.add(type = _MapType = createClass("MapType"));
			ownedClasses.add(type = _OclAny = createAnyType(OCLstdlibPackage.Literals.OCL_ANY));
			type.setIsAbstract(true);
			installComment(type, "The number of elements in the collection oclText[self].essions.\nOclAny is itself an instance of the metatype AnyType.\n\nAll classes in a UML model inherit all operations defined on OclAny.\nTo avoid name conflicts between properties in the model and the properties inherited from OclAny,\nall names on the properties of OclAny start with \u2018ocl.\u2019\nAlthough theoretically there may still be name conflicts, they can be avoided.\nOne can also use qualification by OclAny (name of the type) to explicitly refer to the OclAny properties.\n\nOperations of OclAny, where the instance of OclAny is called object.");
			ownedClasses.add(type = _OclComparable = createClass(OCLstdlibPackage.Literals.OCL_COMPARABLE));
			type.setIsAbstract(true);
			installComment(type, "The type OclComparable defines the compareTo operation used by the sortedBy iteration. Only types that provide a derived\ncompareTo implementation may be sorted.");
			ownedClasses.add(type = _OclElement = createClass(OCLstdlibPackage.Literals.OCL_ELEMENT));
			type.setIsAbstract(true);
			installComment(type, "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes. Operations defined\nfor OclElement are therefore applicable to all user-defined types.");
			ownedClasses.add(type = _OclEnumeration = createClass(OCLstdlibPackage.Literals.OCL_ENUMERATION));
			type.setIsAbstract(true);
			installComment(type, "The OclEnumeration type is the implicit supertype of any user Enumeration type.\nFIXME This is probably obsolete now that static / meta-types clarified.");
			ownedClasses.add(type = _OclInvalid = createInvalidType(OCLstdlibPackage.Literals.OCL_INVALID));
			type.setIsAbstract(true);
			installComment(type, "The type OclInvalid is a type that conforms to all other types.\nIt has one single instance, identified as  oclText[invalid].\nAny property call applied on invalid results in oclText[invalid], except for the operations oclIsUndefined() and oclIsInvalid().\nOclInvalid is itself an instance of the metatype InvalidType.");
			ownedClasses.add(type = _OclLambda = createClass(OCLstdlibPackage.Literals.OCL_LAMBDA));
			type.setIsAbstract(true);
			installComment(type, "The type OclLambda is the implicit supertype of all Lambda types. The operations defined for OclLambda\ntherefore apply to all lambda expressions.");
			ownedClasses.add(type = _OclMessage = createClass(OCLstdlibPackage.Literals.OCL_MESSAGE));
			type.setIsAbstract(true);
			installComment(type, "OclMessage\nThis sub clause contains the definition of the standard type OclMessage.\nAs defined in this sub clause, each ocl message type is actually a template type with one parameter.\n\u2018T\u2019 denotes the parameter.\nA concrete ocl message type is created by substituting an operation or signal for the T.\n\nThe predefined type OclMessage is an instance of MessageType.\nEvery OclMessage is fully determined by either the operation, or signal given as parameter.\nNote that there is conceptually an undefined (infinite) number of these types,\nas each is determined by a different operation or signal.\nThese types are unnamed. Every type has as attributes the name of the operation or signal,\nand either all formal parameters of the operation, or all attributes of the signal.\nOclMessage is itself an instance of the metatype MessageType.\n\nOclMessage has a number of predefined operations, as shown in the OCL Standard Library.");
			ownedClasses.add(type = _OclSelf = createSelfType(OCLstdlibPackage.Literals.OCL_SELF));
			type.setIsAbstract(true);
			installComment(type, "The pseudo-type OclSelf denotes the statically determinate type of oclText[self] in Operation\nand Iteration signatures. Instances of OclSelf are never created.");
			ownedClasses.add(type = _OclState = createClass(OCLstdlibPackage.Literals.OCL_STATE));
			type.setIsAbstract(true);
			ownedClasses.add(type = _OclStereotype = createClass(OCLstdlibPackage.Literals.OCL_STEREOTYPE));
			type.setIsAbstract(true);
			installComment(type, "The type OclStereotype is the implicit supertype of any UML stereotype. Operations defined\nfor OclStereotype are therefore applicable to all UML stereotypes.");
			ownedClasses.add(type = _OclSummable = createClass(OCLstdlibPackage.Literals.OCL_SUMMABLE));
			type.setIsAbstract(true);
			installComment(type, "The type OclSummable defines the sum and zero operations used by the Collection::sum iteration. Only types that provide derived\nsum and zero implementations may be summed.");
			ownedClasses.add(type = _OclTuple = createClass(OCLstdlibPackage.Literals.OCL_TUPLE));
			type.setIsAbstract(true);
			installComment(type, "The type OclTuple is the implicit supertype of all Tuple types. The operations defined for OclTuple\ntherefore apply to all tuples.");
			ownedClasses.add(type = _OclType = createClass(OCLstdlibPackage.Literals.OCL_TYPE));
			type.setIsAbstract(true);
			installComment(type, "The type OclType is the implicit supertype of any UML type. Operations defined\nfor OclType are therefore applicable to all UML types.");
			ownedClasses.add(type = _OclVoid = createVoidType(OCLstdlibPackage.Literals.OCL_VOID));
			type.setIsAbstract(true);
			installComment(type, "The type OclVoid is a type that conforms to all other types except OclInvalid.\nIt has one single instance, identified as oclText[null], that corresponds with the UML LiteralNull value specification.\nAny property call applied on oclText[null] results in oclText[invalid], except for the\noclIsUndefined(), oclIsInvalid(), =(OclAny) and <>(OclAny) operations.\nHowever, by virtue of the implicit conversion to a collection literal,\nan expression evaluating to oclText[null] can be used as source of collection operations (such as \u2018isEmpty\u2019).\nIf the source is the oclText[null] literal, it is implicitly converted to Bag{}.\n\nOclVoid is itself an instance of the metatype VoidType.");
			ownedClasses.add(type = _State = createClass("State"));
			ownedClasses.add(type = _Stereotype = createClass("Stereotype"));
			ownedClasses.add(type = _Type = createClass("Type"));
			installComment(type, "The UML Type is the supertype of anything that may be used as a type.");
			ownedClasses.add(type = _VoidType = createClass("VoidType"));

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
			addSuperClass(_OclInvalid, _OclVoid);
			addSuperClass(_OclLambda, _OclAny);
			addSuperClass(_OclMessage, _OclAny);
			addSuperClass(_OclSelf, _OclAny);
			addSuperClass(_OclState, _OclAny);
			addSuperClass(_OclStereotype, _OclType);
			addSuperClass(_OclSummable, _OclAny);
			addSuperClass(_OclTuple, _OclAny);
			addSuperClass(_OclType, _OclElement);
			addSuperClass(_OclVoid, _OclAny);
			addSuperClass(_State, _OclState);
			addSuperClass(_Stereotype, _OclStereotype);
			addSuperClass(_Type, _OclType);
			addSuperClass(_VoidType, _OclElement);
		}

		private PrimitiveType _Boolean;
		private PrimitiveType _Integer;
		private PrimitiveType _Real;
		private PrimitiveType _String;
		private PrimitiveType _UnlimitedNatural;

		private void installPrimitiveTypes() {
			List<Class> ownedClasses;
			PrimitiveType type;

			ownedClasses = ocl.getOwnedClasses();
			ownedClasses.add(type = _Boolean = createPrimitiveType(OCLstdlibPackage.Literals.BOOLEAN));
			installComment(type, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			ownedClasses.add(type = _Integer = createPrimitiveType(OCLstdlibPackage.Literals.INTEGER));
			installComment(type, "The standard type Integer represents the mathematical concept of integer.\nInteger is itself an instance of the metatype PrimitiveType (from UML).");
			ownedClasses.add(type = _Real = createPrimitiveType(OCLstdlibPackage.Literals.REAL));
			installComment(type, "The standard type Real represents the mathematical concept of real.\nNote that Integer is a subclass of Real,\nso for each parameter of type Real, you can use an integer as the actual parameter.\nReal is itself an instance of the metatype PrimitiveType (from UML).");
			ownedClasses.add(type = _String = createPrimitiveType(OCLstdlibPackage.Literals.STRING));
			installComment(type, "The standard type String represents strings, which can be both ASCII or Unicode.\nString is itself an instance of the metatype PrimitiveType (from UML).");
			ownedClasses.add(type = _UnlimitedNatural = createPrimitiveType(OCLstdlibPackage.Literals.UNLIMITED_NATURAL));
			installComment(type, "The standard type UnlimitedNatural is used to encode the non-negative values of a multiplicity specification.\nThis includes a special e[unlimited] value (*) that encodes the upper value of  a multiplicity specification.\nUnlimitedNatural is itself an instance of the metatype UnlimitedNaturalType.\n\nNote that UnlimitedNatural is not a subclass of Integer.");

			addSuperClass(_Boolean, _OclAny);
			addSuperClass(_Integer, _Real);
			addSuperClass(_Real, _OclComparable);
			addSuperClass(_Real, _OclSummable);
			addSuperClass(_String, _OclComparable);
			addSuperClass(_String, _OclSummable);
			addSuperClass(_UnlimitedNatural, _OclComparable);
		}

		private TupleType _Tuple;

		private void installTupleTypes() {
			final List<Class> orphanTypes = orphanPackage.getOwnedClasses();
			TupleType type;
			type = _Tuple = createTupleType("Tuple",
				createProperty("first", tp_Collection_T),
					createProperty("second", tp_Collection_product_T2));
			orphanTypes.add(type);
			addSuperClass(type, _OclTuple);
		}

		private BagType _Bag_Bag_T;
		private CollectionType _Collection_Collection_T;
		private CollectionType _OrderedCollection_OrderedCollection_T;
		private OrderedSetType _OrderedSet_OrderedSet_T;
		private SequenceType _Sequence_Sequence_T;
		private SetType _Set_Set_T;
		private CollectionType _UniqueCollection_UniqueCollection_T;


		private void installGenericCollectionTypes() {
			List<Class> ownedClasses;
			CollectionType type;

			ownedClasses = ocl.getOwnedClasses();
			ownedClasses.add(type = _Bag_Bag_T = createBagType(OCLstdlibPackage.Literals.BAG, tp_Bag_T, false, 0, -1));
			installComment(type, "A bag is a collection with duplicates allowed. That is, one object can be an element of a bag many times.\nThere is no ordering defined on the elements in a bag.\nBag is itself an instance of the metatype BagType.");
			ownedClasses.add(type = _Collection_Collection_T = createCollectionType(OCLstdlibPackage.Literals.COLLECTION, tp_Collection_T, false, 0, -1));
			installComment(type, "Collection is the abstract supertype of all collection types in the OCL Standard Library.\nEach occurrence of an object in a collection is called an element.\nIf an object occurs twice in a collection, there are two elements.\n\nThis sub clause defines the properties on Collections that have identical semantics for all collection subtypes.\nSome operations may be defined within the subtype as well,\nwhich means that there is an additional postcondition or a more specialized return value.\nCollection is itself an instance of the metatype CollectionType.\n\nThe definition of several common operations is different for each subtype.\nThese operations are not mentioned in this sub clause.\n\nThe semantics of the collection operations is given in the form of a postcondition that uses the IterateExp of the IteratorExp construct.\nThe semantics of those constructs is defined in Clause 10 (\u201CSemantics Described using UML\u201D).\nIn several cases the postcondition refers to other collection operations,\nwhich in turn are defined in terms of the IterateExp or IteratorExp constructs.\n\nWell-formedness rules\n\n[1] A collection cannot contain oclText[invalid] values.\n\ncontext Collection\ninv: self->forAll(not oclIsInvalid())");
			ownedClasses.add(type = _OrderedCollection_OrderedCollection_T = createCollectionType(OCLstdlibPackage.Literals.ORDERED_COLLECTION, tp_OrderedCollection_T, false, 0, -1));
			installComment(type, "The OrderedCollection type provides the shared functionality of the OrderedSet and Sequence\ncollections for which the elements are ordered.\nThe common supertype of OrderedCollection is Collection.");
			ownedClasses.add(type = _OrderedSet_OrderedSet_T = createOrderedSetType(OCLstdlibPackage.Literals.ORDERED_SET, tp_OrderedSet_T, false, 0, -1));
			installComment(type, "The OrderedSet is a Set, the elements of which are ordered.\nIt contains no duplicates. OrderedSet is itself an instance of the metatype OrderedSetType.\nAn OrderedSet is not a subtype of Set, neither a subtype of Sequence.\nThe common supertype of Sets and OrderedSets is Collection.");
			ownedClasses.add(type = _Sequence_Sequence_T = createSequenceType(OCLstdlibPackage.Literals.SEQUENCE, tp_Sequence_T, false, 0, -1));
			installComment(type, "A sequence is a collection where the elements are ordered.\nAn element may be part of a sequence more than once.\nSequence is itself an instance of the metatype SequenceType.\nA Sentence is not a subtype of Bag.\nThe common supertype of Sentence and Bags is Collection.");
			ownedClasses.add(type = _Set_Set_T = createSetType(OCLstdlibPackage.Literals.SET, tp_Set_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_UniqueCollection_T = createCollectionType(OCLstdlibPackage.Literals.UNIQUE_COLLECTION, tp_UniqueCollection_T, false, 0, -1));
			installComment(type, "The UniqueCollection type provides the shared functionality of the OrderedSet and Set\ncollections for which the elements are unique.\nThe common supertype of UniqueCollection is Collection.");

			ownedClasses = orphanPackage.getOwnedClasses();
		}


		private BagType _Bag_Bag_collect_V_F;
		private BagType _Bag_Bag_collectNested_V_F;
		private BagType _Bag_Bag_flatten_T2_F;
		private BagType _Bag_Bag_selectByKind_TT_T;
		private BagType _Bag_Bag_selectByType_TT_T;
		private BagType _Bag_Collection_T_F;
		private BagType _Bag_CollectionType_F;
		private BagType _Bag_Map_V_F;
		private BagType _Bag_Map_collect_V2_F;
		private BagType _Bag_MapType_F;
		private BagType _Bag_OclElement_F;
		private BagType _Bag_OclInvalid_F;
		private BagType _Bag_Set_collect_V_F;
		private BagType _Bag_Set_collectNested_V_F;
		private CollectionType _Collection_Bag_T_F;
		private CollectionType _Collection_Bag_collect_V_F;
		private CollectionType _Collection_Bag_collectNested_V_F;
		private CollectionType _Collection_Bag_flatten_T2_F;
		private CollectionType _Collection_Bag_selectByKind_TT_F;
		private CollectionType _Collection_Bag_selectByType_TT_F;
		private CollectionType _Collection_Collection_T_F;
		private CollectionType _Collection_Collection_collect_V_F;
		private CollectionType _Collection_Collection_collectNested_V_F;
		private CollectionType _Collection_Collection_excludesAll_T2_F;
		private CollectionType _Collection_Collection_flatten_T2_F;
		private CollectionType _Collection_Collection_includesAll_T2_F;
		private CollectionType _Collection_Collection_product_T2_F;
		private CollectionType _Collection_Collection_selectByKind_TT_T;
		private CollectionType _Collection_Collection_selectByType_TT_T;
		private CollectionType _Collection_CollectionType_F;
		private CollectionType _Collection_Integer_F;
		private CollectionType _Collection_Map_K_F;
		private CollectionType _Collection_Map_V_F;
		private CollectionType _Collection_Map_collect_V2_F;
		private CollectionType _Collection_Map_excludesAll_K2_F;
		private CollectionType _Collection_Map_includesAll_K2_F;
		private CollectionType _Collection_MapType_F;
		private CollectionType _Collection_OclAny_F;
		private CollectionType _Collection_OclElement_F;
		private CollectionType _Collection_OclInvalid_F;
		private CollectionType _Collection_OclSelf_F;
		private CollectionType _Collection_OrderedCollection_T_F;
		private CollectionType _Collection_OrderedSet_T_F;
		private CollectionType _Collection_OrderedSet_collect_V_F;
		private CollectionType _Collection_OrderedSet_collectNested_V_F;
		private CollectionType _Collection_OrderedSet_flatten_T2_F;
		private CollectionType _Collection_OrderedSet_selectByKind_TT_F;
		private CollectionType _Collection_OrderedSet_selectByType_TT_F;
		private CollectionType _Collection_Sequence_T_F;
		private CollectionType _Collection_Sequence_collect_V_F;
		private CollectionType _Collection_Sequence_collectNested_V_F;
		private CollectionType _Collection_Sequence_flatten_T2_F;
		private CollectionType _Collection_Sequence_selectByKind_TT_F;
		private CollectionType _Collection_Sequence_selectByType_TT_F;
		private CollectionType _Collection_Set_T_F;
		private CollectionType _Collection_Set_collect_V_F;
		private CollectionType _Collection_Set_collectNested_V_F;
		private CollectionType _Collection_Set_flatten_T2_F;
		private CollectionType _Collection_Set_selectByKind_TT_F;
		private CollectionType _Collection_Set_selectByType_TT_F;
		private CollectionType _Collection_String_F;
		private CollectionType _Collection_Tuple_F;
		private CollectionType _Collection_UniqueCollection_T_F;
		private CollectionType _OrderedCollection_Bag_T_F;
		private CollectionType _OrderedCollection_Collection_T_F;
		private CollectionType _OrderedCollection_Integer_F;
		private CollectionType _OrderedCollection_OrderedSet_T_F;
		private CollectionType _OrderedCollection_OrderedSet_collect_V_F;
		private CollectionType _OrderedCollection_OrderedSet_collectNested_V_F;
		private CollectionType _OrderedCollection_OrderedSet_flatten_T2_F;
		private CollectionType _OrderedCollection_OrderedSet_selectByKind_TT_F;
		private CollectionType _OrderedCollection_OrderedSet_selectByType_TT_F;
		private CollectionType _OrderedCollection_Sequence_T_F;
		private CollectionType _OrderedCollection_Sequence_collect_V_F;
		private CollectionType _OrderedCollection_Sequence_collectNested_V_F;
		private CollectionType _OrderedCollection_Sequence_flatten_T2_F;
		private CollectionType _OrderedCollection_Sequence_selectByKind_TT_F;
		private CollectionType _OrderedCollection_Sequence_selectByType_TT_F;
		private CollectionType _OrderedCollection_Set_T_F;
		private CollectionType _OrderedCollection_String_F;
		private CollectionType _OrderedCollection_UniqueCollection_T_F;
		private OrderedSetType _OrderedSet_Collection_T_F;
		private OrderedSetType _OrderedSet_OrderedSet_flatten_T2_F;
		private OrderedSetType _OrderedSet_OrderedSet_selectByKind_TT_T;
		private OrderedSetType _OrderedSet_OrderedSet_selectByType_TT_T;
		private OrderedSetType _OrderedSet_Sequence_T_F;
		private OrderedSetType _OrderedSet_Set_T_F;
		private OrderedSetType _OrderedSet_UniqueCollection_T_F;
		private SequenceType _Sequence_Bag_T_F;
		private SequenceType _Sequence_Collection_T_F;
		private SequenceType _Sequence_Integer_T;
		private SequenceType _Sequence_OrderedSet_collect_V_F;
		private SequenceType _Sequence_OrderedSet_collectNested_V_F;
		private SequenceType _Sequence_Sequence_collect_V_F;
		private SequenceType _Sequence_Sequence_collectNested_V_F;
		private SequenceType _Sequence_Sequence_flatten_T2_F;
		private SequenceType _Sequence_Sequence_selectByKind_TT_T;
		private SequenceType _Sequence_Sequence_selectByType_TT_T;
		private SequenceType _Sequence_String_T;
		private SetType _Set_Bag_T_F;
		private SetType _Set_Collection_T_F;
		private SetType _Set_Map_K_F;
		private SetType _Set_OclElement_T;
		private SetType _Set_OclSelf_F;
		private SetType _Set_OclSelf_T;
		private SetType _Set_Set_flatten_T2_F;
		private SetType _Set_Set_selectByKind_TT_T;
		private SetType _Set_Set_selectByType_TT_T;
		private SetType _Set_Tuple_T;
		private SetType _Set_UniqueCollection_T_F;
		private CollectionType _UniqueCollection_Bag_T_F;
		private CollectionType _UniqueCollection_Collection_T_F;
		private CollectionType _UniqueCollection_Map_K_F;
		private CollectionType _UniqueCollection_OclAny_F;
		private CollectionType _UniqueCollection_OclElement_F;
		private CollectionType _UniqueCollection_OclSelf_F;
		private CollectionType _UniqueCollection_OrderedSet_T_F;
		private CollectionType _UniqueCollection_OrderedSet_flatten_T2_F;
		private CollectionType _UniqueCollection_OrderedSet_selectByKind_TT_F;
		private CollectionType _UniqueCollection_OrderedSet_selectByType_TT_F;
		private CollectionType _UniqueCollection_Sequence_T_F;
		private CollectionType _UniqueCollection_Set_T_F;
		private CollectionType _UniqueCollection_Set_flatten_T2_F;
		private CollectionType _UniqueCollection_Set_selectByKind_TT_F;
		private CollectionType _UniqueCollection_Set_selectByType_TT_F;
		private CollectionType _UniqueCollection_Tuple_F;
		private CollectionType _UniqueCollection_UniqueCollection_T_F;

		private void installSpecializedCollectionTypes() {
			List<Class> ownedClasses;
			CollectionType type;

			ownedClasses = ocl.getOwnedClasses();

			ownedClasses = orphanPackage.getOwnedClasses();
			ownedClasses.add(type = _Bag_Bag_collect_V_F = getBagType(_Bag_Bag_T, tp_Bag_collect_V, false, 0, -1));
			ownedClasses.add(type = _Bag_Bag_collectNested_V_F = getBagType(_Bag_Bag_T, tp_Bag_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Bag_Bag_flatten_T2_F = getBagType(_Bag_Bag_T, tp_Bag_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Bag_Bag_selectByKind_TT_T = getBagType(_Bag_Bag_T, tp_Bag_selectByKind_TT, true, 0, -1));
			ownedClasses.add(type = _Bag_Bag_selectByType_TT_T = getBagType(_Bag_Bag_T, tp_Bag_selectByType_TT, true, 0, -1));
			ownedClasses.add(type = _Bag_Collection_T_F = getBagType(_Bag_Bag_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _Bag_CollectionType_F = getBagType(_Bag_Bag_T, _CollectionType, false, 0, -1));
			ownedClasses.add(type = _Bag_Map_V_F = getBagType(_Bag_Bag_T, tp_Map_V, false, 0, -1));
			ownedClasses.add(type = _Bag_Map_collect_V2_F = getBagType(_Bag_Bag_T, tp_Map_collect_V2, false, 0, -1));
			ownedClasses.add(type = _Bag_MapType_F = getBagType(_Bag_Bag_T, _MapType, false, 0, -1));
			ownedClasses.add(type = _Bag_OclElement_F = getBagType(_Bag_Bag_T, _OclElement, false, 0, -1));
			ownedClasses.add(type = _Bag_OclInvalid_F = getBagType(_Bag_Bag_T, _OclInvalid, false, 0, -1));
			ownedClasses.add(type = _Bag_Set_collect_V_F = getBagType(_Bag_Bag_T, tp_Set_collect_V, false, 0, -1));
			ownedClasses.add(type = _Bag_Set_collectNested_V_F = getBagType(_Bag_Bag_T, tp_Set_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_T_F = getCollectionType(_Collection_Collection_T, tp_Bag_T, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_collect_V_F = getCollectionType(_Collection_Collection_T, tp_Bag_collect_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_collectNested_V_F = getCollectionType(_Collection_Collection_T, tp_Bag_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_flatten_T2_F = getCollectionType(_Collection_Collection_T, tp_Bag_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_selectByKind_TT_F = getCollectionType(_Collection_Collection_T, tp_Bag_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Bag_selectByType_TT_F = getCollectionType(_Collection_Collection_T, tp_Bag_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_T_F = getCollectionType(_Collection_Collection_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_collect_V_F = getCollectionType(_Collection_Collection_T, tp_Collection_collect_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_collectNested_V_F = getCollectionType(_Collection_Collection_T, tp_Collection_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_excludesAll_T2_F = getCollectionType(_Collection_Collection_T, tp_Collection_excludesAll_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_flatten_T2_F = getCollectionType(_Collection_Collection_T, tp_Collection_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_includesAll_T2_F = getCollectionType(_Collection_Collection_T, tp_Collection_includesAll_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_product_T2_F = getCollectionType(_Collection_Collection_T, tp_Collection_product_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Collection_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByKind_TT, true, 0, -1));
			ownedClasses.add(type = _Collection_Collection_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByType_TT, true, 0, -1));
			ownedClasses.add(type = _Collection_CollectionType_F = getCollectionType(_Collection_Collection_T, _CollectionType, false, 0, -1));
			ownedClasses.add(type = _Collection_Integer_F = getCollectionType(_Collection_Collection_T, _Integer, false, 0, -1));
			ownedClasses.add(type = _Collection_Map_K_F = getCollectionType(_Collection_Collection_T, tp_Map_K, false, 0, -1));
			ownedClasses.add(type = _Collection_Map_V_F = getCollectionType(_Collection_Collection_T, tp_Map_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Map_collect_V2_F = getCollectionType(_Collection_Collection_T, tp_Map_collect_V2, false, 0, -1));
			ownedClasses.add(type = _Collection_Map_excludesAll_K2_F = getCollectionType(_Collection_Collection_T, tp_Map_excludesAll_K2, false, 0, -1));
			ownedClasses.add(type = _Collection_Map_includesAll_K2_F = getCollectionType(_Collection_Collection_T, tp_Map_includesAll_K2, false, 0, -1));
			ownedClasses.add(type = _Collection_MapType_F = getCollectionType(_Collection_Collection_T, _MapType, false, 0, -1));
			ownedClasses.add(type = _Collection_OclAny_F = getCollectionType(_Collection_Collection_T, _OclAny, false, 0, -1));
			ownedClasses.add(type = _Collection_OclElement_F = getCollectionType(_Collection_Collection_T, _OclElement, false, 0, -1));
			ownedClasses.add(type = _Collection_OclInvalid_F = getCollectionType(_Collection_Collection_T, _OclInvalid, false, 0, -1));
			ownedClasses.add(type = _Collection_OclSelf_F = getCollectionType(_Collection_Collection_T, _OclSelf, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedCollection_T_F = getCollectionType(_Collection_Collection_T, tp_OrderedCollection_T, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_T_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_T, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_collect_V_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collect_V, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_collectNested_V_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_flatten_T2_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_selectByKind_TT_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_OrderedSet_selectByType_TT_F = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_T_F = getCollectionType(_Collection_Collection_T, tp_Sequence_T, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_collect_V_F = getCollectionType(_Collection_Collection_T, tp_Sequence_collect_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_collectNested_V_F = getCollectionType(_Collection_Collection_T, tp_Sequence_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_flatten_T2_F = getCollectionType(_Collection_Collection_T, tp_Sequence_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_selectByKind_TT_F = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Sequence_selectByType_TT_F = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_T_F = getCollectionType(_Collection_Collection_T, tp_Set_T, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_collect_V_F = getCollectionType(_Collection_Collection_T, tp_Set_collect_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_collectNested_V_F = getCollectionType(_Collection_Collection_T, tp_Set_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_flatten_T2_F = getCollectionType(_Collection_Collection_T, tp_Set_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_selectByKind_TT_F = getCollectionType(_Collection_Collection_T, tp_Set_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_Set_selectByType_TT_F = getCollectionType(_Collection_Collection_T, tp_Set_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _Collection_String_F = getCollectionType(_Collection_Collection_T, _String, false, 0, -1));
			ownedClasses.add(type = _Collection_Tuple_F = getCollectionType(_Collection_Collection_T, _Tuple, false, 0, -1));
			ownedClasses.add(type = _Collection_UniqueCollection_T_F = getCollectionType(_Collection_Collection_T, tp_UniqueCollection_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Bag_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Bag_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Collection_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Integer_F = getCollectionType(_OrderedCollection_OrderedCollection_T, _Integer, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_collect_V_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collect_V, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_collectNested_V_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_flatten_T2_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_selectByKind_TT_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_OrderedSet_selectByType_TT_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_collect_V_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collect_V, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_collectNested_V_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_flatten_T2_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_selectByKind_TT_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Sequence_selectByType_TT_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_Set_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Set_T, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_String_F = getCollectionType(_OrderedCollection_OrderedCollection_T, _String, false, 0, -1));
			ownedClasses.add(type = _OrderedCollection_UniqueCollection_T_F = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_UniqueCollection_T, false, 0, -1));
			ownedClasses.add(type = _OrderedSet_Collection_T_F = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _OrderedSet_OrderedSet_flatten_T2_F = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_OrderedSet_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _OrderedSet_OrderedSet_selectByKind_TT_T = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByKind_TT, true, 0, -1));
			ownedClasses.add(type = _OrderedSet_OrderedSet_selectByType_TT_T = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByType_TT, true, 0, -1));
			ownedClasses.add(type = _OrderedSet_Sequence_T_F = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_Sequence_T, false, 0, -1));
			ownedClasses.add(type = _OrderedSet_Set_T_F = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_Set_T, false, 0, -1));
			ownedClasses.add(type = _OrderedSet_UniqueCollection_T_F = getOrderedSetType(_OrderedSet_OrderedSet_T, tp_UniqueCollection_T, false, 0, -1));
			ownedClasses.add(type = _Sequence_Bag_T_F = getSequenceType(_Sequence_Sequence_T, tp_Bag_T, false, 0, -1));
			ownedClasses.add(type = _Sequence_Collection_T_F = getSequenceType(_Sequence_Sequence_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _Sequence_Integer_T = getSequenceType(_Sequence_Sequence_T, _Integer, true, 0, -1));
			ownedClasses.add(type = _Sequence_OrderedSet_collect_V_F = getSequenceType(_Sequence_Sequence_T, tp_OrderedSet_collect_V, false, 0, -1));
			ownedClasses.add(type = _Sequence_OrderedSet_collectNested_V_F = getSequenceType(_Sequence_Sequence_T, tp_OrderedSet_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Sequence_Sequence_collect_V_F = getSequenceType(_Sequence_Sequence_T, tp_Sequence_collect_V, false, 0, -1));
			ownedClasses.add(type = _Sequence_Sequence_collectNested_V_F = getSequenceType(_Sequence_Sequence_T, tp_Sequence_collectNested_V, false, 0, -1));
			ownedClasses.add(type = _Sequence_Sequence_flatten_T2_F = getSequenceType(_Sequence_Sequence_T, tp_Sequence_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Sequence_Sequence_selectByKind_TT_T = getSequenceType(_Sequence_Sequence_T, tp_Sequence_selectByKind_TT, true, 0, -1));
			ownedClasses.add(type = _Sequence_Sequence_selectByType_TT_T = getSequenceType(_Sequence_Sequence_T, tp_Sequence_selectByType_TT, true, 0, -1));
			ownedClasses.add(type = _Sequence_String_T = getSequenceType(_Sequence_Sequence_T, _String, true, 0, -1));
			ownedClasses.add(type = _Set_Bag_T_F = getSetType(_Set_Set_T, tp_Bag_T, false, 0, -1));
			ownedClasses.add(type = _Set_Collection_T_F = getSetType(_Set_Set_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _Set_Map_K_F = getSetType(_Set_Set_T, tp_Map_K, false, 0, -1));
			ownedClasses.add(type = _Set_OclElement_T = getSetType(_Set_Set_T, _OclElement, true, 0, -1));
			ownedClasses.add(type = _Set_OclSelf_F = getSetType(_Set_Set_T, _OclSelf, false, 0, -1));
			ownedClasses.add(type = _Set_OclSelf_T = getSetType(_Set_Set_T, _OclSelf, true, 0, -1));
			ownedClasses.add(type = _Set_Set_flatten_T2_F = getSetType(_Set_Set_T, tp_Set_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _Set_Set_selectByKind_TT_T = getSetType(_Set_Set_T, tp_Set_selectByKind_TT, true, 0, -1));
			ownedClasses.add(type = _Set_Set_selectByType_TT_T = getSetType(_Set_Set_T, tp_Set_selectByType_TT, true, 0, -1));
			ownedClasses.add(type = _Set_Tuple_T = getSetType(_Set_Set_T, _Tuple, true, 0, -1));
			ownedClasses.add(type = _Set_UniqueCollection_T_F = getSetType(_Set_Set_T, tp_UniqueCollection_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Bag_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Bag_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Collection_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Collection_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Map_K_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Map_K, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OclAny_F = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclAny, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OclElement_F = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclElement, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OclSelf_F = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclSelf, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OrderedSet_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OrderedSet_flatten_T2_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OrderedSet_selectByKind_TT_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_OrderedSet_selectByType_TT_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Sequence_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Sequence_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Set_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_T, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Set_flatten_T2_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_flatten_T2, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Set_selectByKind_TT_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByKind_TT, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Set_selectByType_TT_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByType_TT, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_Tuple_F = getCollectionType(_UniqueCollection_UniqueCollection_T, _Tuple, false, 0, -1));
			ownedClasses.add(type = _UniqueCollection_UniqueCollection_T_F = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_UniqueCollection_T, false, 0, -1));

			addSuperClass(_Bag_Bag_T, _Collection_Bag_T_F);
			addSuperClass(_Collection_Collection_T, _OclAny);
			addSuperClass(_OrderedCollection_OrderedCollection_T, _Collection_OrderedCollection_T_F);
			addSuperClass(_OrderedSet_OrderedSet_T, _OrderedCollection_OrderedSet_T_F);
			addSuperClass(_OrderedSet_OrderedSet_T, _UniqueCollection_OrderedSet_T_F);
			addSuperClass(_Sequence_Sequence_T, _OrderedCollection_Sequence_T_F);
			addSuperClass(_Set_Set_T, _UniqueCollection_Set_T_F);
			addSuperClass(_UniqueCollection_UniqueCollection_T, _Collection_UniqueCollection_T_F);

			addSuperClass(_Bag_Bag_collect_V_F, _Collection_Bag_collect_V_F);
			addSuperClass(_Bag_Bag_collectNested_V_F, _Collection_Bag_collectNested_V_F);
			addSuperClass(_Bag_Bag_flatten_T2_F, _Collection_Bag_flatten_T2_F);
			addSuperClass(_Bag_Bag_selectByKind_TT_T, _Collection_Bag_selectByKind_TT_F);
			addSuperClass(_Bag_Bag_selectByType_TT_T, _Collection_Bag_selectByType_TT_F);
			addSuperClass(_Bag_Collection_T_F, _Collection_Collection_T_F);
			addSuperClass(_Bag_CollectionType_F, _Collection_CollectionType_F);
			addSuperClass(_Bag_Map_V_F, _Collection_Map_V_F);
			addSuperClass(_Bag_Map_collect_V2_F, _Collection_Map_collect_V2_F);
			addSuperClass(_Bag_MapType_F, _Collection_MapType_F);
			addSuperClass(_Bag_OclElement_F, _Collection_OclElement_F);
			addSuperClass(_Bag_OclInvalid_F, _Collection_OclInvalid_F);
			addSuperClass(_Bag_Set_collect_V_F, _Collection_Set_collect_V_F);
			addSuperClass(_Bag_Set_collectNested_V_F, _Collection_Set_collectNested_V_F);
			addSuperClass(_Collection_Bag_T_F, _OclAny);
			addSuperClass(_Collection_Bag_collect_V_F, _OclAny);
			addSuperClass(_Collection_Bag_collectNested_V_F, _OclAny);
			addSuperClass(_Collection_Bag_flatten_T2_F, _OclAny);
			addSuperClass(_Collection_Bag_selectByKind_TT_F, _OclAny);
			addSuperClass(_Collection_Bag_selectByType_TT_F, _OclAny);
			addSuperClass(_Collection_Collection_T_F, _OclAny);
			addSuperClass(_Collection_Collection_collect_V_F, _OclAny);
			addSuperClass(_Collection_Collection_collectNested_V_F, _OclAny);
			addSuperClass(_Collection_Collection_excludesAll_T2_F, _OclAny);
			addSuperClass(_Collection_Collection_flatten_T2_F, _OclAny);
			addSuperClass(_Collection_Collection_includesAll_T2_F, _OclAny);
			addSuperClass(_Collection_Collection_product_T2_F, _OclAny);
			addSuperClass(_Collection_Collection_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_Collection_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_CollectionType_F, _OclAny);
			addSuperClass(_Collection_Integer_F, _OclAny);
			addSuperClass(_Collection_Map_K_F, _OclAny);
			addSuperClass(_Collection_Map_V_F, _OclAny);
			addSuperClass(_Collection_Map_collect_V2_F, _OclAny);
			addSuperClass(_Collection_Map_excludesAll_K2_F, _OclAny);
			addSuperClass(_Collection_Map_includesAll_K2_F, _OclAny);
			addSuperClass(_Collection_MapType_F, _OclAny);
			addSuperClass(_Collection_OclAny_F, _OclAny);
			addSuperClass(_Collection_OclElement_F, _OclAny);
			addSuperClass(_Collection_OclInvalid_F, _OclAny);
			addSuperClass(_Collection_OclSelf_F, _OclAny);
			addSuperClass(_Collection_OrderedCollection_T_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_T_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_collect_V_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_collectNested_V_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_flatten_T2_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_selectByKind_TT_F, _OclAny);
			addSuperClass(_Collection_OrderedSet_selectByType_TT_F, _OclAny);
			addSuperClass(_Collection_Sequence_T_F, _OclAny);
			addSuperClass(_Collection_Sequence_collect_V_F, _OclAny);
			addSuperClass(_Collection_Sequence_collectNested_V_F, _OclAny);
			addSuperClass(_Collection_Sequence_flatten_T2_F, _OclAny);
			addSuperClass(_Collection_Sequence_selectByKind_TT_F, _OclAny);
			addSuperClass(_Collection_Sequence_selectByType_TT_F, _OclAny);
			addSuperClass(_Collection_Set_T_F, _OclAny);
			addSuperClass(_Collection_Set_collect_V_F, _OclAny);
			addSuperClass(_Collection_Set_collectNested_V_F, _OclAny);
			addSuperClass(_Collection_Set_flatten_T2_F, _OclAny);
			addSuperClass(_Collection_Set_selectByKind_TT_F, _OclAny);
			addSuperClass(_Collection_Set_selectByType_TT_F, _OclAny);
			addSuperClass(_Collection_String_F, _OclAny);
			addSuperClass(_Collection_Tuple_F, _OclAny);
			addSuperClass(_Collection_UniqueCollection_T_F, _OclAny);
			addSuperClass(_OrderedCollection_Bag_T_F, _Collection_Bag_T_F);
			addSuperClass(_OrderedCollection_Collection_T_F, _Collection_Collection_T_F);
			addSuperClass(_OrderedCollection_Integer_F, _Collection_Integer_F);
			addSuperClass(_OrderedCollection_OrderedSet_T_F, _Collection_OrderedSet_T_F);
			addSuperClass(_OrderedCollection_OrderedSet_collect_V_F, _Collection_OrderedSet_collect_V_F);
			addSuperClass(_OrderedCollection_OrderedSet_collectNested_V_F, _Collection_OrderedSet_collectNested_V_F);
			addSuperClass(_OrderedCollection_OrderedSet_flatten_T2_F, _Collection_OrderedSet_flatten_T2_F);
			addSuperClass(_OrderedCollection_OrderedSet_selectByKind_TT_F, _Collection_OrderedSet_selectByKind_TT_F);
			addSuperClass(_OrderedCollection_OrderedSet_selectByType_TT_F, _Collection_OrderedSet_selectByType_TT_F);
			addSuperClass(_OrderedCollection_Sequence_T_F, _Collection_Sequence_T_F);
			addSuperClass(_OrderedCollection_Sequence_collect_V_F, _Collection_Sequence_collect_V_F);
			addSuperClass(_OrderedCollection_Sequence_collectNested_V_F, _Collection_Sequence_collectNested_V_F);
			addSuperClass(_OrderedCollection_Sequence_flatten_T2_F, _Collection_Sequence_flatten_T2_F);
			addSuperClass(_OrderedCollection_Sequence_selectByKind_TT_F, _Collection_Sequence_selectByKind_TT_F);
			addSuperClass(_OrderedCollection_Sequence_selectByType_TT_F, _Collection_Sequence_selectByType_TT_F);
			addSuperClass(_OrderedCollection_Set_T_F, _Collection_Set_T_F);
			addSuperClass(_OrderedCollection_String_F, _Collection_String_F);
			addSuperClass(_OrderedCollection_UniqueCollection_T_F, _Collection_UniqueCollection_T_F);
			addSuperClass(_OrderedSet_Collection_T_F, _OrderedCollection_Collection_T_F);
			addSuperClass(_OrderedSet_Collection_T_F, _UniqueCollection_Collection_T_F);
			addSuperClass(_OrderedSet_OrderedSet_flatten_T2_F, _OrderedCollection_OrderedSet_flatten_T2_F);
			addSuperClass(_OrderedSet_OrderedSet_flatten_T2_F, _UniqueCollection_OrderedSet_flatten_T2_F);
			addSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _OrderedCollection_OrderedSet_selectByKind_TT_F);
			addSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _UniqueCollection_OrderedSet_selectByKind_TT_F);
			addSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _OrderedCollection_OrderedSet_selectByType_TT_F);
			addSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _UniqueCollection_OrderedSet_selectByType_TT_F);
			addSuperClass(_OrderedSet_Sequence_T_F, _OrderedCollection_Sequence_T_F);
			addSuperClass(_OrderedSet_Sequence_T_F, _UniqueCollection_Sequence_T_F);
			addSuperClass(_OrderedSet_Set_T_F, _OrderedCollection_Set_T_F);
			addSuperClass(_OrderedSet_Set_T_F, _UniqueCollection_Set_T_F);
			addSuperClass(_OrderedSet_UniqueCollection_T_F, _OrderedCollection_UniqueCollection_T_F);
			addSuperClass(_OrderedSet_UniqueCollection_T_F, _UniqueCollection_UniqueCollection_T_F);
			addSuperClass(_Sequence_Bag_T_F, _OrderedCollection_Bag_T_F);
			addSuperClass(_Sequence_Collection_T_F, _OrderedCollection_Collection_T_F);
			addSuperClass(_Sequence_Integer_T, _OrderedCollection_Integer_F);
			addSuperClass(_Sequence_OrderedSet_collect_V_F, _OrderedCollection_OrderedSet_collect_V_F);
			addSuperClass(_Sequence_OrderedSet_collectNested_V_F, _OrderedCollection_OrderedSet_collectNested_V_F);
			addSuperClass(_Sequence_Sequence_collect_V_F, _OrderedCollection_Sequence_collect_V_F);
			addSuperClass(_Sequence_Sequence_collectNested_V_F, _OrderedCollection_Sequence_collectNested_V_F);
			addSuperClass(_Sequence_Sequence_flatten_T2_F, _OrderedCollection_Sequence_flatten_T2_F);
			addSuperClass(_Sequence_Sequence_selectByKind_TT_T, _OrderedCollection_Sequence_selectByKind_TT_F);
			addSuperClass(_Sequence_Sequence_selectByType_TT_T, _OrderedCollection_Sequence_selectByType_TT_F);
			addSuperClass(_Sequence_String_T, _OrderedCollection_String_F);
			addSuperClass(_Set_Bag_T_F, _UniqueCollection_Bag_T_F);
			addSuperClass(_Set_Collection_T_F, _UniqueCollection_Collection_T_F);
			addSuperClass(_Set_Map_K_F, _UniqueCollection_Map_K_F);
			addSuperClass(_Set_OclElement_T, _UniqueCollection_OclElement_F);
			addSuperClass(_Set_OclSelf_F, _UniqueCollection_OclSelf_F);
			addSuperClass(_Set_OclSelf_T, _UniqueCollection_OclSelf_F);
			addSuperClass(_Set_Set_flatten_T2_F, _UniqueCollection_Set_flatten_T2_F);
			addSuperClass(_Set_Set_selectByKind_TT_T, _UniqueCollection_Set_selectByKind_TT_F);
			addSuperClass(_Set_Set_selectByType_TT_T, _UniqueCollection_Set_selectByType_TT_F);
			addSuperClass(_Set_Tuple_T, _UniqueCollection_Tuple_F);
			addSuperClass(_Set_UniqueCollection_T_F, _UniqueCollection_UniqueCollection_T_F);
			addSuperClass(_UniqueCollection_Bag_T_F, _Collection_Bag_T_F);
			addSuperClass(_UniqueCollection_Collection_T_F, _Collection_Collection_T_F);
			addSuperClass(_UniqueCollection_Map_K_F, _Collection_Map_K_F);
			addSuperClass(_UniqueCollection_OclAny_F, _Collection_OclAny_F);
			addSuperClass(_UniqueCollection_OclElement_F, _Collection_OclElement_F);
			addSuperClass(_UniqueCollection_OclSelf_F, _Collection_OclSelf_F);
			addSuperClass(_UniqueCollection_OrderedSet_T_F, _Collection_OrderedSet_T_F);
			addSuperClass(_UniqueCollection_OrderedSet_flatten_T2_F, _Collection_OrderedSet_flatten_T2_F);
			addSuperClass(_UniqueCollection_OrderedSet_selectByKind_TT_F, _Collection_OrderedSet_selectByKind_TT_F);
			addSuperClass(_UniqueCollection_OrderedSet_selectByType_TT_F, _Collection_OrderedSet_selectByType_TT_F);
			addSuperClass(_UniqueCollection_Sequence_T_F, _Collection_Sequence_T_F);
			addSuperClass(_UniqueCollection_Set_T_F, _Collection_Set_T_F);
			addSuperClass(_UniqueCollection_Set_flatten_T2_F, _Collection_Set_flatten_T2_F);
			addSuperClass(_UniqueCollection_Set_selectByKind_TT_F, _Collection_Set_selectByKind_TT_F);
			addSuperClass(_UniqueCollection_Set_selectByType_TT_F, _Collection_Set_selectByType_TT_F);
			addSuperClass(_UniqueCollection_Tuple_F, _Collection_Tuple_F);
			addSuperClass(_UniqueCollection_UniqueCollection_T_F, _Collection_UniqueCollection_T_F);
		}

		private MapType _Map_Map_K_Map_V;

		private void installGenericMapTypes() {
			List<Class> ownedClasses;
			MapType type;

			ownedClasses = ocl.getOwnedClasses();
			ownedClasses.add(type = _Map_Map_K_Map_V = createMapType(OCLstdlibPackage.Literals.MAP, tp_Map_K, true, tp_Map_V, true));
			installComment(type, "A Map provides a Set of key values, each of which has an associated value.\nKeys and values may be null, but neither may be invalid.");

			ownedClasses = orphanPackage.getOwnedClasses();
		}

		private MapType _Map_Collection_T_F_Collection_collectBy_V_F;
		private MapType _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T;
		private MapType _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T;
		private MapType _Map_Map_includesMap_K2_T_Map_includesMap_V2_T;
		private MapType _Map_Map_includingMap_K2_T_Map_includingMap_V2_T;
		private MapType _Map_Map_K_F_Map_collectBy_V2_F;
		private MapType _Map_Map_K_F_Map_collectNested_V2_F;
		private MapType _Map_Map_K_F_Map_V_F;

		private void installSpecializedMapTypes() {
			List<Class> ownedClasses;
			MapType type;

			ownedClasses = ocl.getOwnedClasses();

			ownedClasses = orphanPackage.getOwnedClasses();
			ownedClasses.add(type = _Map_Collection_T_F_Collection_collectBy_V_F = getMapType(_Map_Map_K_Map_V, tp_Collection_T, false, tp_Collection_collectBy_V, false));
			ownedClasses.add(type = _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludesMap_K2, true, tp_Map_excludesMap_V2, true));
			ownedClasses.add(type = _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludingMap_K2, true, tp_Map_excludingMap_V2, true));
			ownedClasses.add(type = _Map_Map_includesMap_K2_T_Map_includesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includesMap_K2, true, tp_Map_includesMap_V2, true));
			ownedClasses.add(type = _Map_Map_includingMap_K2_T_Map_includingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includingMap_K2, true, tp_Map_includingMap_V2, true));
			ownedClasses.add(type = _Map_Map_K_F_Map_collectBy_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectBy_V2, false));
			ownedClasses.add(type = _Map_Map_K_F_Map_collectNested_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectNested_V2, false));
			ownedClasses.add(type = _Map_Map_K_F_Map_V_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_V, false));

			addSuperClass(_Map_Map_K_Map_V, _OclAny);

			addSuperClass(_Map_Collection_T_F_Collection_collectBy_V_F, _OclAny);
			addSuperClass(_Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_includesMap_K2_T_Map_includesMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_includingMap_K2_T_Map_includingMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_collectBy_V2_F, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_collectNested_V2_F, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_V_F, _OclAny);
		}

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

		private void installLambdaTypes() {
			final List<Class> ownedClasses = orphanPackage.getOwnedClasses();
			LambdaType type;
			ownedClasses.add(type = _Lambda_Bag_T_Boolean = createLambdaType("Lambda", tp_Bag_T, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Bag_T_Bag_collectNested_V = createLambdaType("Lambda", tp_Bag_T, tp_Bag_collectNested_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Bag_T_Bag_collect_V = createLambdaType("Lambda", tp_Bag_T, tp_Bag_collect_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Bag_T_OclAny = createLambdaType("Lambda", tp_Bag_T, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Bag_T_Set = createLambdaType("Lambda", tp_Bag_T, _Set_Bag_T_F));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_Boolean = createLambdaType("Lambda", tp_Collection_T, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_Collection_collectBy_V = createLambdaType("Lambda", tp_Collection_T, tp_Collection_collectBy_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_Collection_collectNested_V = createLambdaType("Lambda", tp_Collection_T, tp_Collection_collectNested_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_Collection_collect_V = createLambdaType("Lambda", tp_Collection_T, tp_Collection_collect_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_Collection_iterate_Tacc = createLambdaType("Lambda", tp_Collection_T, tp_Collection_iterate_Tacc));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Collection_T_OclAny = createLambdaType("Lambda", tp_Collection_T, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_Boolean = createLambdaType("Lambda", tp_Map_K, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_Map_collectBy_V2 = createLambdaType("Lambda", tp_Map_K, tp_Map_collectBy_V2));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_Map_collectNested_V2 = createLambdaType("Lambda", tp_Map_K, tp_Map_collectNested_V2));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_Map_collect_V2 = createLambdaType("Lambda", tp_Map_K, tp_Map_collect_V2));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_Map_iterate_Tacc = createLambdaType("Lambda", tp_Map_K, tp_Map_iterate_Tacc));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Map_K_OclAny = createLambdaType("Lambda", tp_Map_K, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_OrderedSet_T_Boolean = createLambdaType("Lambda", tp_OrderedSet_T, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_OrderedSet_T_OclAny = createLambdaType("Lambda", tp_OrderedSet_T, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_OrderedSet_T_OrderedSet = createLambdaType("Lambda", tp_OrderedSet_T, _OrderedSet_OrderedSet_T));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_OrderedSet_T_OrderedSet_collectNested_V = createLambdaType("Lambda", tp_OrderedSet_T, tp_OrderedSet_collectNested_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_OrderedSet_T_OrderedSet_collect_V = createLambdaType("Lambda", tp_OrderedSet_T, tp_OrderedSet_collect_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Sequence_T_Boolean = createLambdaType("Lambda", tp_Sequence_T, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Sequence_T_OclAny = createLambdaType("Lambda", tp_Sequence_T, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Sequence_T_OrderedSet = createLambdaType("Lambda", tp_Sequence_T, _OrderedSet_Sequence_T_F));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Sequence_T_Sequence_collectNested_V = createLambdaType("Lambda", tp_Sequence_T, tp_Sequence_collectNested_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Sequence_T_Sequence_collect_V = createLambdaType("Lambda", tp_Sequence_T, tp_Sequence_collect_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Set_T_Boolean = createLambdaType("Lambda", tp_Set_T, _Boolean));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Set_T_OclAny = createLambdaType("Lambda", tp_Set_T, _OclAny));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Set_T_Set = createLambdaType("Lambda", tp_Set_T, _Set_Set_T));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Set_T_Set_collectNested_V = createLambdaType("Lambda", tp_Set_T, tp_Set_collectNested_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_Set_T_Set_collect_V = createLambdaType("Lambda", tp_Set_T, tp_Set_collect_V));
			addSuperClass(type, _OclLambda);
			ownedClasses.add(type = _Lambda_UniqueCollection_T_OclAny = createLambdaType("Lambda", tp_UniqueCollection_T, _OclAny));
			addSuperClass(type, _OclLambda);
		}

		private void installOperations() {
			List<Operation> ownedOperations;
			List<Parameter> ownedParameters;
			Operation operation;
			Parameter parameter;

			ownedOperations = _Boolean.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[true] if the logical value of oclText[self] is the not same as the numeric value of object2, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[true] if the logical value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Returns oclText[Set{false, true}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			ownedOperations.add(operation = createOperation("and", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = false then false\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then false\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = false then false\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			installComment(operation, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid] .\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[true].");
			ownedOperations.add(operation = createOperation("and2", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation2.INSTANCE));
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			createBodyExpression(operation, _Boolean, "if self = false then false\n\t\t\t  elseif b = false then false\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, true));
			installComment(operation, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[true].");
			ownedOperations.add(operation = createOperation("implies", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then b\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			installComment(operation, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");
			ownedOperations.add(operation = createOperation("implies2", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation2.INSTANCE));
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			createBodyExpression(operation, _Boolean, "if self = false then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, true));
			installComment(operation, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[false].");
			ownedOperations.add(operation = createOperation("not", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_UNARY);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif self = null then null\n\t\t\t  else self = false\n\t\t\t  endif", _Boolean);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false].\noclText[false] if oclText[self] is oclText[true].\noclText[null] if oclText[self] is oclText[null].\nOtherwise oclText[invalid].");
			ownedOperations.add(operation = createOperation("not2", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation2.INSTANCE));
			operation.setPrecedence(prec_UNARY);
			createBodyExpression(operation, _Boolean, "if self then false else true endif", _Boolean);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false].\nOtherwise oclText[false].");
			ownedOperations.add(operation = createOperation("or", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanOrOperation", org.eclipse.ocl.pivot.library.logical.BooleanOrOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = true then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			installComment(operation, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");
			ownedOperations.add(operation = createOperation("or2", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2", org.eclipse.ocl.pivot.library.logical.BooleanOrOperation2.INSTANCE));
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			createBodyExpression(operation, _Boolean, "if self = true then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, true));
			installComment(operation, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[false].");
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Converts oclText[self] to a string value.");
			ownedOperations.add(operation = createOperation("xor", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE));
			operation.setIsRequired(false);
			operation.setPrecedence(prec_XOR);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else self <> b\n\t\t\t  endif", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			installComment(operation, "oclText[true] if oclText[self] is oclText[true] and oclText[b] is oclText[false], or if oclText[self] is oclText[false] and oclText[b] is oclText[true].\noclText[false] if oclText[self] is oclText[true] and oclText[b] is oclText[true], or if oclText[self] is oclText[false] and oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null].");
			ownedOperations.add(operation = createOperation("xor2", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation2.INSTANCE));
			operation.setPrecedence(prec_XOR);
			createBodyExpression(operation, _Boolean, "self <> b", _Boolean);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, true));
			installComment(operation, "oclText[true] if oclText[self] <> oclText[b]\nOtherwise oclText[false].");

			ownedOperations = _Integer.getOwnedOperations();
			ownedOperations.add(operation = createOperation("*", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation", org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE));
			operation.setPrecedence(prec_MULTIPLICATIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The value of the multiplication of oclText[self] and i.");
			ownedOperations.add(operation = createOperation("+", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation", org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The value of the addition of oclText[self] and i.");
			ownedOperations.add(operation = createOperation("-", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation", org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE));
			operation.setPrecedence(prec_UNARY);
			installComment(operation, "The negative value of oclText[self].");
			ownedOperations.add(operation = createOperation("-", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The value of the subtraction of i from oclText[self].");
			ownedOperations.add(operation = createOperation("/", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The value of oclText[self] divided by i.\nEvaluates to oclText[invalid] if r is equal to zero.");
			ownedOperations.add(operation = createOperation("abs", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation", org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE));
			installComment(operation, "The absolute value of oclText[self].");
			ownedOperations.add(operation = createOperation("div", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericDivOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _Integer, true));
			installComment(operation, "The number of times that i fits completely within oclText[self].");
			ownedOperations.add(operation = createOperation("max", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation", org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The maximum of oclText[self] an i.");
			ownedOperations.add(operation = createOperation("min", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericMinOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The minimum of oclText[self] an i.");
			ownedOperations.add(operation = createOperation("mod", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericModOperation", org.eclipse.ocl.pivot.library.numeric.NumericModOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _Integer, true));
			installComment(operation, "The result is oclText[self] modulo i.");
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Converts oclText[self] to a string value.");
			ownedOperations.add(operation = createOperation("toUnlimitedNatural", _UnlimitedNatural, "org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation", org.eclipse.ocl.pivot.library.numeric.IntegerToUnlimitedNaturalOperation.INSTANCE));
			_Integer.getCoercions().add(operation);
			installComment(operation, "Converts a non-negative oclText[self] to an UnlimitedNatural value. A negative oclText[self] is converted to oclText[invalid].\nAn automatic coersion may be synthesized if the coercion enables an operation reference to be resolved\nin an expression where no operation was available without coercion.");

			ownedOperations = _Real.getOwnedOperations();
			ownedOperations.add(operation = createOperation("*", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation", org.eclipse.ocl.pivot.library.numeric.NumericTimesOperation.INSTANCE));
			operation.setPrecedence(prec_MULTIPLICATIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The value of the multiplication of oclText[self] and r.");
			ownedOperations.add(operation = createOperation("+", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation", org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The value of the addition of oclText[self] and r.");
			ownedOperations.add(operation = createOperation("-", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation", org.eclipse.ocl.pivot.library.numeric.NumericNegateOperation.INSTANCE));
			operation.setPrecedence(prec_UNARY);
			installComment(operation, "The negative value of oclText[self].");
			ownedOperations.add(operation = createOperation("-", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The value of the subtraction of r from oclText[self].");
			ownedOperations.add(operation = createOperation("/", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation", org.eclipse.ocl.pivot.library.numeric.NumericDivideOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The value of oclText[self] divided by r. Evaluates to oclText[invalid] if r is equal to zero.");
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[true] if the numeric value of oclText[self] is the not the same as the numeric value of object2, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[true] if the numeric value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("abs", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation", org.eclipse.ocl.pivot.library.numeric.NumericAbsOperation.INSTANCE));
			installComment(operation, "The absolute value of oclText[self].");
			ownedOperations.add(operation = createOperation("floor", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation", org.eclipse.ocl.pivot.library.numeric.NumericFloorOperation.INSTANCE));
			installComment(operation, "The largest integer that is less than or equal to oclText[self].");
			ownedOperations.add(operation = createOperation("max", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation", org.eclipse.ocl.pivot.library.numeric.NumericMaxOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The maximum of oclText[self] and r.");
			ownedOperations.add(operation = createOperation("min", _Real, "org.eclipse.ocl.pivot.library.numeric.NumericMinOperation", org.eclipse.ocl.pivot.library.numeric.NumericMinOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("r", _OclSelf, true));
			installComment(operation, "The minimum of oclText[self] and r.");
			ownedOperations.add(operation = createOperation("round", _Integer, "org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation", org.eclipse.ocl.pivot.library.numeric.NumericRoundOperation.INSTANCE));
			installComment(operation, "The integer that is closest to oclText[self]. When there are two such integers, the largest one.");
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Converts oclText[self] to a string value.");

			ownedOperations = _String.getOwnedOperations();
			ownedOperations.add(operation = createOperation("+", _String, "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, false));
			installComment(operation, "The concatenation of oclText[self] and s.");
			ownedOperations.add(operation = createOperation("<", _Boolean, "org.eclipse.ocl.pivot.library.string.StringLessThanOperation", org.eclipse.ocl.pivot.library.string.StringLessThanOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _OclSelf, true));
			installComment(operation, "True if oclText[self] is less than s, using the locale defined by looking up oclLocale in the current environment.");
			ownedOperations.add(operation = createOperation("<=", _Boolean, "org.eclipse.ocl.pivot.library.string.StringLessThanEqualOperation", org.eclipse.ocl.pivot.library.string.StringLessThanEqualOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _OclSelf, true));
			installComment(operation, "True if oclText[self] is less than or equal to s, using the locale defined by looking up oclLocale in the current environment.");
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			ownedOperations.add(operation = createOperation(">", _Boolean, "org.eclipse.ocl.pivot.library.string.StringGreaterThanOperation", org.eclipse.ocl.pivot.library.string.StringGreaterThanOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _OclSelf, true));
			installComment(operation, "True if oclText[self] is greater than s, using the locale defined by looking up oclLocale in the current environment.");
			ownedOperations.add(operation = createOperation(">=", _Boolean, "org.eclipse.ocl.pivot.library.string.StringGreaterThanEqualOperation", org.eclipse.ocl.pivot.library.string.StringGreaterThanEqualOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _OclSelf, true));
			installComment(operation, "True if oclText[self] is greater than or equal to s, using the locale defined by looking up oclLocale in the current environment.");
			ownedOperations.add(operation = createOperation("at", _String, "org.eclipse.ocl.pivot.library.string.StringAtOperation", org.eclipse.ocl.pivot.library.string.StringAtOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _Integer, true));
			installComment(operation, "Queries the character at position i in oclText[self].");
			ownedOperations.add(operation = createOperation("characters", _Sequence_String_T, "org.eclipse.ocl.pivot.library.string.StringCharactersOperation", org.eclipse.ocl.pivot.library.string.StringCharactersOperation.INSTANCE));
			installComment(operation, "Obtains the characters of oclText[self] as a sequence.");
			ownedOperations.add(operation = createOperation("compareTo", _Integer, "org.eclipse.ocl.pivot.library.string.StringCompareToOperation", org.eclipse.ocl.pivot.library.string.StringCompareToOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "The comparison of oclText[self] with oclText[that]. -ve if less than, 0 if equal, +ve if greater than.");
			ownedOperations.add(operation = createOperation("concat", _String, "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, false));
			installComment(operation, "The concatenation of oclText[self] and s.");
			ownedOperations.add(operation = createOperation("endsWith", _Boolean, "org.eclipse.ocl.pivot.library.string.StringEndsWithOperation", org.eclipse.ocl.pivot.library.string.StringEndsWithOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, true));
			installComment(operation, "Returns true if oclText[self] ends with the string s.\nEvery string ends with the empty string.");
			ownedOperations.add(operation = createOperation("equalsIgnoreCase", _Boolean, "org.eclipse.ocl.pivot.library.string.StringEqualsIgnoreCaseOperation", org.eclipse.ocl.pivot.library.string.StringEqualsIgnoreCaseOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, true));
			installComment(operation, "Queries whether s and oclText[self] are equivalent under case-insensitive collation.");
			ownedOperations.add(operation = createOperation("indexOf", _Integer, "org.eclipse.ocl.pivot.library.string.StringIndexOfOperation", org.eclipse.ocl.pivot.library.string.StringIndexOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, true));
			installComment(operation, "Queries the first index in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index 1 (and also at all other indexes).");
			ownedOperations.add(operation = createOperation("lastIndexOf", _Integer, "org.eclipse.ocl.pivot.library.string.StringLastIndexOfOperation", org.eclipse.ocl.pivot.library.string.StringLastIndexOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, true));
			installComment(operation, "Queries the last in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index oclText[self]-size()+1 (and also at all other indexes).");
			ownedOperations.add(operation = createOperation("matches", _Boolean, "org.eclipse.ocl.pivot.library.string.StringMatchesOperation", org.eclipse.ocl.pivot.library.string.StringMatchesOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("regex", _String, true));
			installComment(operation, "Use a regular expression match and return true if self matches regex, false otherwise.");
			ownedOperations.add(operation = createOperation("replaceAll", _String, "org.eclipse.ocl.pivot.library.string.StringReplaceAllOperation", org.eclipse.ocl.pivot.library.string.StringReplaceAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("regex", _String, true));
			ownedParameters.add(parameter = createParameter("replacement", _String, true));
			installComment(operation, "Return a string derived from self by replacing all matches of regex by replacement.");
			ownedOperations.add(operation = createOperation("replaceFirst", _String, "org.eclipse.ocl.pivot.library.string.StringReplaceFirstOperation", org.eclipse.ocl.pivot.library.string.StringReplaceFirstOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("regex", _String, true));
			ownedParameters.add(parameter = createParameter("replacement", _String, true));
			installComment(operation, "Return a string derived from self by replacing the first match of regex by replacement.");
			ownedOperations.add(operation = createOperation("size", _Integer, "org.eclipse.ocl.pivot.library.string.StringSizeOperation", org.eclipse.ocl.pivot.library.string.StringSizeOperation.INSTANCE));
			installComment(operation, "The number of characters in oclText[self].");
			ownedOperations.add(operation = createOperation("startsWith", _Boolean, "org.eclipse.ocl.pivot.library.string.StringStartsWithOperation", org.eclipse.ocl.pivot.library.string.StringStartsWithOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, true));
			installComment(operation, "Returns true if oclText[self] starts with the string s.\nEvery string starts with the empty string.");
			ownedOperations.add(operation = createOperation("substituteAll", _String, "org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation", org.eclipse.ocl.pivot.library.string.StringSubstituteAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("oldSubstring", _String, true));
			ownedParameters.add(parameter = createParameter("newSubstring", _String, true));
			installComment(operation, "Return a string derived from self by replacing all occurrences of oldSubstring by newSubstring.");
			ownedOperations.add(operation = createOperation("substituteFirst", _String, "org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation", org.eclipse.ocl.pivot.library.string.StringSubstituteFirstOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("oldSubstring", _String, true));
			ownedParameters.add(parameter = createParameter("newSubstring", _String, true));
			installComment(operation, "Return a string derived from self by replacing the first occurrence of oldSubstring by newSubstring.\nReturns invalid if there is no first occurrence.");
			ownedOperations.add(operation = createOperation("substring", _String, "org.eclipse.ocl.pivot.library.string.StringSubstringOperation", org.eclipse.ocl.pivot.library.string.StringSubstringOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lower", _Integer, true));
			ownedParameters.add(parameter = createParameter("upper", _Integer, true));
			installComment(operation, "The sub-string of oclText[self] starting at character number lower, up to and including character number upper. Character numbers run from 1 to self.size().");
			ownedOperations.add(operation = createOperation("toBoolean", _Boolean, "org.eclipse.ocl.pivot.library.string.StringToBooleanOperation", org.eclipse.ocl.pivot.library.string.StringToBooleanOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to a boolean value. Returns null for non-Boolean text.");
			ownedOperations.add(operation = createOperation("toInteger", _Integer, "org.eclipse.ocl.pivot.library.string.StringToIntegerOperation", org.eclipse.ocl.pivot.library.string.StringToIntegerOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to an Integer value. Returns null for non-Integer text.");
			ownedOperations.add(operation = createOperation("toLower", _String, "org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation", org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE));
			installComment(operation, "This is a deprecated variant of toLowerCase() preserving compatibility with traditional Eclipse OCL behaviour.");
			ownedOperations.add(operation = createOperation("toLowerCase", _String, "org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation", org.eclipse.ocl.pivot.library.string.StringToLowerCaseOperation.INSTANCE));
			installComment(operation, "Converts oclText[self] to lower case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");
			ownedOperations.add(operation = createOperation("toReal", _Real, "org.eclipse.ocl.pivot.library.string.StringToRealOperation", org.eclipse.ocl.pivot.library.string.StringToRealOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to a Real[1] value. Returns null for non-Real text.");
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Returns oclText[self].");
			ownedOperations.add(operation = createOperation("toUpper", _String, "org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation", org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE));
			installComment(operation, "This is a deprecated variant of toUpperCase() preserving compatibility with traditional Eclipse OCL behaviour.");
			ownedOperations.add(operation = createOperation("toUpperCase", _String, "org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation", org.eclipse.ocl.pivot.library.string.StringToUpperCaseOperation.INSTANCE));
			installComment(operation, "Converts oclText[self] to upper case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");
			ownedOperations.add(operation = createOperation("tokenize", _Sequence_String_T, "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE));
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by any of space, line-feed, carriage-return, form-feed and horizontal-tab delimiters.\nThe delimiters are omitted from the return.");
			ownedOperations.add(operation = createOperation("tokenize", _Sequence_String_T, "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("delimiters", _String, true));
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. The delimiters are omitted from the return.");
			ownedOperations.add(operation = createOperation("tokenize", _Sequence_String_T, "org.eclipse.ocl.pivot.library.string.StringTokenizeOperation", org.eclipse.ocl.pivot.library.string.StringTokenizeOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("delimiters", _String, true));
			ownedParameters.add(parameter = createParameter("returnDelimiters", _Boolean, true));
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. If returnDelimeters is\ntrue the returned sequence includes the delimiters, otherwise the delimiters are omitted.");
			ownedOperations.add(operation = createOperation("trim", _String, "org.eclipse.ocl.pivot.library.string.StringTrimOperation", org.eclipse.ocl.pivot.library.string.StringTrimOperation.INSTANCE));
			installComment(operation, "Return oclText[self] with leading and trailing whitespace removed.");

			ownedOperations = _UnlimitedNatural.getOwnedOperations();
			ownedOperations.add(operation = createOperation("max", _UnlimitedNatural, "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMaxOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The maximum of oclText[self] an i.");
			ownedOperations.add(operation = createOperation("min", _UnlimitedNatural, "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalMinOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("i", _OclSelf, true));
			installComment(operation, "The minimum of oclText[self] an i.");
			ownedOperations.add(operation = createOperation("oclAsType", tp_UnlimitedNatural_oclAsType_TT, "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation.INSTANCE, tp_UnlimitedNatural_oclAsType_TT));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_UnlimitedNatural_oclAsType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the type identified by T.\nThe type T may be any classifier defined in the UML model;\nif the actual type of oclText[self] at evaluation time does not conform to T,\nthen the oclAsType operation evaluates to oclText[invalid].\n\nThe standard behavior is redefined for UnlimitedNatural. Numeric values may be converted to\nReal or Integer, but the e[unlimited] value may not.\nConversion of e[unlimited] to Real or Integer returns oclText[invalid].");
			ownedOperations.add(operation = createOperation("toInteger", _Integer, "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to an Integer value unless oclText[self] is e[unlimited] in which case oclText[self] is converted to oclText[null].");

			ownedOperations = _Bag_Bag_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if oclText[self] and bag contain the same elements, the same number of times.");
			ownedOperations.add(operation = createOperation("excluding", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Bag_T, false));
			installComment(operation, "The bag containing all elements of oclText[self] apart from all occurrences of object.");
			ownedOperations.add(operation = createOperation("excludingAll", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Bag_T_F, true));
			installComment(operation, "The bag containing all elements of oclText[self] apart from all occurrences of all objects.");
			ownedOperations.add(operation = createOperation("flatten", _Bag_Bag_flatten_T2_F, "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Bag_flatten_T2));
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same bag as oclText[self].\nIf the element type is a collection type, the result is the bag containing all the elements of all the recursively flattened elements of oclText[self].");
			ownedOperations.add(operation = createOperation("including", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Bag_T, false));
			installComment(operation, "The bag containing all elements of oclText[self] plus object.");
			ownedOperations.add(operation = createOperation("includingAll", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Bag_T_F, true));
			installComment(operation, "The bag containing all elements of oclText[self] and objects.");
			ownedOperations.add(operation = createOperation("selectByKind", _Bag_Bag_selectByKind_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Bag_selectByKind_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Bag_selectByKind_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The bag containing all elements of oclText[self] whose type conforms to oclText[type].");
			ownedOperations.add(operation = createOperation("selectByType", _Bag_Bag_selectByType_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Bag_selectByType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Bag_selectByType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The bag containing all elements of oclText[self] whose type is oclText[type].");

			ownedOperations = _BooleanType.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Returns oclText[Set{false, true}].");

			ownedOperations = _Class.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Return a set of all instances of the type and derived types of self.");

			ownedOperations = _Collection_Collection_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if c is not equal to oclText[self].");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if c is a collection of the same kind as oclText[self] and contains the same elements in the same quantities and in the same order,\nin the case of an ordered collection type.");
			ownedOperations.add(operation = createOperation("asBag", _Bag_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation.INSTANCE));
			installComment(operation, "The Bag that contains all the elements from oclText[self].");
			ownedOperations.add(operation = createOperation("asOrderedSet", _OrderedSet_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation.INSTANCE));
			installComment(operation, "An OrderedSet that contains all the elements from oclText[self], with duplicates removed,\nin an order dependent on the particular concrete collection type.");
			ownedOperations.add(operation = createOperation("asSequence", _Sequence_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation.INSTANCE));
			installComment(operation, "A Sequence that contains all the elements from oclText[self], in an order dependent on the particular concrete collection type.");
			ownedOperations.add(operation = createOperation("asSet", _Set_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation.INSTANCE));
			installComment(operation, "The Set containing all the elements from oclText[self], with duplicates removed.");
			ownedOperations.add(operation = createOperation("count", _Integer, "org.eclipse.ocl.pivot.library.collection.CollectionCountOperation", org.eclipse.ocl.pivot.library.collection.CollectionCountOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Collection_T, false));
			installComment(operation, "The number of times that object occurs in the collection oclText[self].");
			ownedOperations.add(operation = createOperation("excludes", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Collection_T, false));
			installComment(operation, "True if object is not an element of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excludesAll", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation.INSTANCE, tp_Collection_excludesAll_T2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c2", _Collection_Collection_excludesAll_T2_F, true));
			installComment(operation, "Does oclText[self] contain none of the elements of c2 ?");
			ownedOperations.add(operation = createOperation("excluding", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Collection_T, false));
			installComment(operation, "The collection containing all elements of oclText[self] apart from object.");
			ownedOperations.add(operation = createOperation("excludingAll", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Collection_T, true));
			installComment(operation, "The collection containing all elements of oclText[self] apart from all occurrences of all objects.");
			ownedOperations.add(operation = createOperation("flatten", _Collection_Collection_flatten_T2_F, "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Collection_flatten_T2));
			installComment(operation, "If the element type is not a collection type, this results in the same collection as oclText[self].\nIf the element type is a collection type, the result is a collection containing all the elements of all the recursively flattened elements of oclText[self].");
			ownedOperations.add(operation = createOperation("includes", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Collection_T, false));
			installComment(operation, "True if object is an element of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("includesAll", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation.INSTANCE, tp_Collection_includesAll_T2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c2", _Collection_Collection_includesAll_T2_F, true));
			installComment(operation, "Does oclText[self] contain all the elements of c2 ?");
			ownedOperations.add(operation = createOperation("including", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Collection_T, false));
			installComment(operation, "The collection containing all elements of oclText[self] plus object.");
			ownedOperations.add(operation = createOperation("includingAll", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Collection_T, true));
			installComment(operation, "The collection containing all elements of oclText[self] and objects.");
			ownedOperations.add(operation = createOperation("intersection", _Bag_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c", _Collection_Collection_T, true));
			installComment(operation, "The intersection of oclText[self] and bag; the bag of all elements that are in both oclText[self] and c.");
			ownedOperations.add(operation = createOperation("intersection", _Set_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("u", _UniqueCollection_Collection_T_F, true));
			installComment(operation, "The intersection of oclText[self] and a unique collection; the set of all elements that are in both oclText[self] and u.");
			ownedOperations.add(operation = createOperation("isEmpty", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation.INSTANCE));
			installComment(operation, "Is oclText[self] the empty collection?\n\nNote: oclText[null->isEmpty()] returns oclText[true] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");
			ownedOperations.add(operation = createOperation("max", tp_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation", org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation.INSTANCE));
			installComment(operation, "The element with the maximum value of all elements in oclText[self].\nElements must be of a type supporting the max operation.\nThe max operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");
			ownedOperations.add(operation = createOperation("min", tp_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionMinOperation", org.eclipse.ocl.pivot.library.collection.CollectionMinOperation.INSTANCE));
			installComment(operation, "The element with the minimum value of all elements in oclText[self].\nElements must be of a type supporting the min operation.\nThe min operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");
			ownedOperations.add(operation = createOperation("notEmpty", _Boolean, "org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation.INSTANCE));
			installComment(operation, "Is oclText[self] not the empty collection?\n\noclText[null->notEmpty()] returns oclText[false] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");
			ownedOperations.add(operation = createOperation("product", _Set_Tuple_T, "org.eclipse.ocl.pivot.library.collection.CollectionProductOperation", org.eclipse.ocl.pivot.library.collection.CollectionProductOperation.INSTANCE, tp_Collection_product_T2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c2", _Collection_Collection_product_T2_F, true));
			installComment(operation, "The cartesian product operation of oclText[self] and c2.");
			ownedOperations.add(operation = createOperation("selectByKind", _Collection_Collection_selectByKind_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Collection_selectByKind_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Collection_selectByKind_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The collection containing all elements of oclText[self] whose type conforms to oclText[type].");
			ownedOperations.add(operation = createOperation("selectByType", _Collection_Collection_selectByType_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Collection_selectByType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Collection_selectByType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The collection containing all elements of oclText[self] whose type is oclText[type].");
			ownedOperations.add(operation = createOperation("size", _Integer, "org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation.INSTANCE));
			installComment(operation, "The number of elements in the collection oclText[self].");
			ownedOperations.add(operation = createOperation("sum", tp_Collection_T, "org.eclipse.ocl.pivot.library.collection.CollectionSumOperation", org.eclipse.ocl.pivot.library.collection.CollectionSumOperation.INSTANCE));
			installComment(operation, "The addition of all elements in oclText[self].\nElements must be of an oclText[OclSummable] type to provide the zero() and sum() operations.\nThe e[sum] operation must be both associative: a.sum(b).sum(c) = a.sum(b.sum(c)), and commutative: a.sum(b) = b.sum(a).\nInteger and Real fulfill this condition.\n\nIf the e[sum] operation is not both associative and commutative, the e[sum] expression is not well-formed,\nwhich may result in unpredictable results during evaluation.\nIf an implementation is able to detect a lack of associativity or commutativity,\nthe implementation may bypass the evaluation and return an oclText[invalid] result.");
			ownedOperations.add(operation = createOperation("union", _Bag_Collection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation", org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c", _Collection_Collection_T, true));
			installComment(operation, "The bag consisting of all elements in oclText[self] and all elements in c.");

			ownedOperations = _Enumeration.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Return a set of all enumeration values of oclText[self].");

			ownedOperations = _InvalidType.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Returns oclText[invalid].");

			ownedOperations = _Map_Map_K_Map_V.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("at", tp_Map_V, "org.eclipse.ocl.pivot.library.map.MapAtOperation", org.eclipse.ocl.pivot.library.map.MapAtOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			installComment(operation, "The value of the map at oclText[key].");
			ownedOperations.add(operation = createOperation("excludes", _Boolean, "org.eclipse.ocl.pivot.library.map.MapExcludesOperation", org.eclipse.ocl.pivot.library.map.MapExcludesOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			installComment(operation, "True if oclText[key] is not one of the keys of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excludes", _Boolean, "org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation", org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "True if oclText[key] and oclText[value] are not a key-value pair of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excludesAll", _Boolean, "org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation.INSTANCE, tp_Map_excludesAll_K2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("coll", _Collection_Map_excludesAll_K2_F, true));
			installComment(operation, "True if none of the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excludesMap", _Boolean, "org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation.INSTANCE, tp_Map_excludesMap_K2, tp_Map_excludesMap_V2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("map", _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, true));
			installComment(operation, "True if none of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excludesValue", _Boolean, "org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation", org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "True if oclText[value] is not one of the values of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("excluding", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapExcludingOperation", org.eclipse.ocl.pivot.library.map.MapExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key].");
			ownedOperations.add(operation = createOperation("excluding", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation", org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key] and whose value is oclText[key].");
			ownedOperations.add(operation = createOperation("excludingAll", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("keys", _Collection_Map_K_F, true));
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is included in oclText[keys].");
			ownedOperations.add(operation = createOperation("excludingMap", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation.INSTANCE, tp_Map_excludingMap_K2, tp_Map_excludingMap_V2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("map", _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, true));
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any which is also included in oclText[map].");
			ownedOperations.add(operation = createOperation("includes", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIncludesOperation", org.eclipse.ocl.pivot.library.map.MapIncludesOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			installComment(operation, "True if oclText[key] is one of the keys of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("includes", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "True if oclText[key] and oclText[value] are a key-value pair of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("includesAll", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation", org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation.INSTANCE, tp_Map_includesAll_K2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("coll", _Collection_Map_includesAll_K2_F, true));
			installComment(operation, "True if all the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("includesMap", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation.INSTANCE, tp_Map_includesMap_K2, tp_Map_includesMap_V2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("map", _Map_Map_includesMap_K2_T_Map_includesMap_V2_T, true));
			installComment(operation, "True if all of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("includesValue", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation", org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "True if oclText[value] is one of the values of oclText[self], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("including", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("key", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("value", tp_Map_V, false));
			installComment(operation, "The map containing all of the key-value pairs of oclText[self] and an additional key-value pair for oclText[key] and oclText[value].\nIf oclText[key] is already a key of oclText[self], the old value pair is replaced by oclText[value].");
			ownedOperations.add(operation = createOperation("includingMap", _Map_Map_K_Map_V, "org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation.INSTANCE, tp_Map_includingMap_K2, tp_Map_includingMap_V2));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("map", _Map_Map_includingMap_K2_T_Map_includingMap_V2_T, true));
			installComment(operation, "The map containing all of the key-value pairs of oclText[self] and oclText[map].\nThe values associated with key-value pairs in oclText[map] replace those in oclText[self] where the same key is used by both maps.");
			ownedOperations.add(operation = createOperation("isEmpty", _Boolean, "org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation", org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation.INSTANCE));
			installComment(operation, "True if oclText[self] is the empty map, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("keys", _Set_Map_K_F, "org.eclipse.ocl.pivot.library.map.MapKeysOperation", org.eclipse.ocl.pivot.library.map.MapKeysOperation.INSTANCE));
			installComment(operation, "A Set comprising all the keys of the key-value pairs in oclText[self].");
			ownedOperations.add(operation = createOperation("notEmpty", _Boolean, "org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation", org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation.INSTANCE));
			installComment(operation, "True if oclText[self] not the empty map, oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("size", _Integer, "org.eclipse.ocl.pivot.library.map.MapSizeOperation", org.eclipse.ocl.pivot.library.map.MapSizeOperation.INSTANCE));
			installComment(operation, "The number of key-value pairs in oclText[self].");
			ownedOperations.add(operation = createOperation("values", _Bag_Map_V_F, "org.eclipse.ocl.pivot.library.map.MapValuesOperation", org.eclipse.ocl.pivot.library.map.MapValuesOperation.INSTANCE));
			installComment(operation, "The Bag comprising all the values of the key-value pairs in oclText[self].");

			ownedOperations = _OclAny.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if oclText[self] is a different object from object2. Infix operator.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if oclText[self] is the same object as object2. Infix operator.");
			ownedOperations.add(operation = createOperation("oclAsSet", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE));
			installComment(operation, "Returns a Set with oclText[self] as the sole content, unless oclText[self] is oclText[null] in which case returns an empty set,");
			ownedOperations.add(operation = createOperation("oclAsType", tp_OclAny_oclAsType_TT, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclAny_oclAsType_TT));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OclAny_oclAsType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the type identified by oclText[TT].\nThe type oclText[TT] may be any classifier defined by OCL or a user metamodel;\nif the actual type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsType call is not well-formed if\nthe classification is ambiguous.\n\nIn the case of feature redefinition, casting an object to a supertype of its actual type\ndoes not access the supertype\u2019s definition of the feature;\naccording to the semantics of redefinition, the redefined feature simply does not exist for the object.\nHowever, when casting to a supertype, any features additionally defined by the subtype are suppressed.\n\nFIXME Bug 578060 return should be optional to support OclVoid returning null.");
			ownedOperations.add(operation = createOperation("oclIsInState", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("statespec", _OclState, false));
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is in the state identified by statespec.");
			ownedOperations.add(operation = createOperation("oclIsInvalid", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE));
			operation.setIsValidating(true);
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is equal to OclInvalid.");
			ownedOperations.add(operation = createOperation("oclIsKindOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			installComment(operation, "Evaluates to oclText[true] if the type of oclText[self] conforms to oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].");
			ownedOperations.add(operation = createOperation("oclIsNew", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			installComment(operation, "Can only be used in a postcondition.\nEvaluates to oclText[true] if the oclText[self] is created during performing the operation (for instance, it didn\u2019t exist at precondition time).");
			ownedOperations.add(operation = createOperation("oclIsTypeOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			installComment(operation, "Evaluates to oclText[true] if oclText[self] is of the type oclText[type] but not a subtype of oclText[type].");
			ownedOperations.add(operation = createOperation("oclIsUndefined", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE));
			operation.setIsValidating(true);
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is equal to oclText[invalid] or equal to oclText[null].");
			ownedOperations.add(operation = createOperation("oclLog", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE));
			installComment(operation, "Evaluates to the self, with the side effect of generating a log message comprising self.");
			ownedOperations.add(operation = createOperation("oclLog", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("message", _String, true));
			installComment(operation, "Evaluates to the self, with the side effect of generating a log message comprising message followed by self.");
			ownedOperations.add(operation = createOperation("oclType", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE));
			operation.setIsTypeof(true);
			installComment(operation, "Evaluates to the most derived type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified type, the return is the most derived type of the current classification which is established when the instance is\npassed to OCL, or re-established by an oclText[oclAsType()] call.");
			ownedOperations.add(operation = createOperation("oclTypes", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE));
			installComment(operation, "Evaluates to all of the most derived type of which oclText[self] is an instance. The return from oclText[oclTypes()]\nis normally equivalent to that from oclText[oclType()] unless oclText[self] is an instance of multiply classified type.");
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Returns a string representation of oclText[self].");

			ownedOperations = _OclComparable.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "True if oclText[self] is less than oclText[that].");
			ownedOperations.add(operation = createOperation("<=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "True if oclText[self] is less than or equal to oclText[that].");
			ownedOperations.add(operation = createOperation(">", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "True if oclText[self] is greater than oclText[that].");
			ownedOperations.add(operation = createOperation(">=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation.INSTANCE));
			operation.setPrecedence(prec_RELATIONAL);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "True if oclText[self] is greater than or equal to oclText[that].");
			ownedOperations.add(operation = createOperation("compareTo", _Integer, "org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation", org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "Return -ve, 0, +ve according to whether self is less than, equal to , or greater than that.\n\nThe compareTo operation should be commutative.");

			ownedOperations = _OclElement.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Return a set of all instances of the type and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			ownedOperations.add(operation = createOperation("oclAsModelType", tp_OclElement_oclAsModelType_TT, "org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation.INSTANCE, tp_OclElement_oclAsModelType_TT));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OclElement_oclAsModelType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the model type identified by oclText[TT].\n\nMost model elements have metamodel types for use with oclAsType, but no model type and so the return is oclText[invalid].\n\nModel elements such as UML\'s InstnaceSpecification that do support distinct model and metamodel types return oclText[self]\nwith the cast type oclText[TT] that may be used for further navigation.\nIf the actual model type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsModelType call is not well-formed if\nthe classification is ambiguous.");
			ownedOperations.add(operation = createOperation("oclBase", _OclType, "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Returns the application class that is extended by this extension element. Returns null for an orphan extension of nothing.");
			ownedOperations.add(operation = createOperation("oclBase", _OclType, "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation.INSTANCE));
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("base", _OclType, true));
			installComment(operation, "Returns the application class conforming to base extended by this extension element. Returns null if no such class.");
			ownedOperations.add(operation = createOperation("oclContainer", _OclElement, "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Returns the object for which self is a composed content or null if there is no such object.");
			ownedOperations.add(operation = createOperation("oclContents", _Set_OclElement_T, "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation.INSTANCE));
			installComment(operation, "Returns the composed contents of self.");
			ownedOperations.add(operation = createOperation("oclExtension", _OclElement, "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("stereotype", _OclStereotype, true));
			installComment(operation, "Returns the application instance of the Stereotype that conforms to stereotype applied to this element. Returns invalid if more than one.");
			ownedOperations.add(operation = createOperation("oclExtensions", _Set_OclElement_T, "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE));
			installComment(operation, "Returns the application instances of all Stereotypes applied to this element.");
			ownedOperations.add(operation = createOperation("oclExtensions", _Set_OclElement_T, "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("stereotype", _OclStereotype, true));
			installComment(operation, "Returns the application instances of the Stereotypes that conform to stereotype applied to this element.");
			ownedOperations.add(operation = createOperation("oclIsModelKindOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			installComment(operation, "Evaluates to oclText[true] if the type of oclText[self] conforms to the model type oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].\n\nThe return is normally oclText[false] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");
			ownedOperations.add(operation = createOperation("oclModelType", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation.INSTANCE));
			operation.setIsTypeof(true);
			installComment(operation, "Evaluates to the most derived model type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified model type, the return is the most derived type of the current classification which is established\nby an oclText[oclAsModelType()] call.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");
			ownedOperations.add(operation = createOperation("oclModelTypes", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation.INSTANCE));
			installComment(operation, "Evaluates to all of the most derived model types of which oclText[self] is an instance. The return from oclText[oclModelTypes()]\nis normally equivalent to that from oclText[oclModelType()] unless oclText[self] is an instance of multiply classified model type.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");

			ownedOperations = _OclEnumeration.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Return a set of all enumeration values of oclText[self].\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			ownedOperations = _OclInvalid.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[invalid].");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Returns oclText[invalid].");
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Returns oclText[invalid].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			ownedOperations.add(operation = createOperation("and", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE));
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("implies", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation", org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation.INSTANCE));
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("oclAsSet", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE));
			ownedOperations.add(operation = createOperation("oclAsType", tp_OclInvalid_oclAsType_TT, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclInvalid_oclAsType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OclInvalid_oclAsType_TT, true));
			parameter.setIsTypeof(true);
			ownedOperations.add(operation = createOperation("oclBadOperation", _OclAny, null, null));
			operation.setIsRequired(false);
			installComment(operation, "An oclBadOperation may be used as a placeholder in an unsuccessfully created OCLExpression.");
			ownedOperations.add(operation = createOperation("oclIsInvalid", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE));
			operation.setIsValidating(true);
			ownedOperations.add(operation = createOperation("oclIsKindOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			ownedOperations.add(operation = createOperation("oclIsTypeOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			ownedOperations.add(operation = createOperation("oclIsUndefined", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE));
			operation.setIsValidating(true);
			ownedOperations.add(operation = createOperation("oclType", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE));
			operation.setIsTypeof(true);
			ownedOperations.add(operation = createOperation("or", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation", org.eclipse.ocl.pivot.library.logical.BooleanAndOperation.INSTANCE));
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Returns \'invalid\'.");

			ownedOperations = _OclMessage.getOwnedOperations();
			ownedOperations.add(operation = createOperation("hasReturned", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			installComment(operation, "True if type of template parameter is an operation call, and the called operation has returned a value.\nThis implies the fact that the message has been sent. False in all other cases.");
			ownedOperations.add(operation = createOperation("isOperationCall", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			installComment(operation, "Returns oclText[true] if the OclMessage represents the sending of a UML Operation call.");
			ownedOperations.add(operation = createOperation("isSignalSent", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			installComment(operation, "Returns oclText[true] if the OclMessage represents the sending of a UML Signal.");
			ownedOperations.add(operation = createOperation("result", _OclAny, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			operation.setIsRequired(false);
			installComment(operation, "Returns the result of the called operation, if type of template parameter is an operation call,\nand the called operation has returned a value. Otherwise the oclText[invalid] value is returned.");

			ownedOperations = _OclStereotype.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Return a set of all instances of the stereotype and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			ownedOperations = _OclSummable.getOwnedOperations();
			ownedOperations.add(operation = createOperation("sum", _OclSelf, null, null));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("that", _OclSelf, true));
			installComment(operation, "Return the sum of self and that.\n\nThe sum operation should be associative.");
			ownedOperations.add(operation = createOperation("zero", _OclSelf, null, null));
			installComment(operation, "Return the \'zero\' value of self to initialize a summation.\n\nzero().sum(self) = self.");

			ownedOperations = _OclTuple.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));

			ownedOperations = _OclType.getOwnedOperations();
			ownedOperations.add(operation = createOperation("conformsTo", _Boolean, "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation", org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type2", _OclType, false));
			installComment(operation, "Returns true if type2 conforms to self.");

			ownedOperations = _OclVoid.getOwnedOperations();
			ownedOperations.add(operation = createOperation("+", _String, "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, false));
			installComment(operation, "The concatenation of oclText[null] and s.");
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Redefines the OclAny operation, returning oclText[true] if object is oclText[null], oclText[invalid]\nif object is oclText[invalid], oclText[false] otherwise.");
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("dummy", _Integer, true));
			installComment(operation, "Returns oclText[Set{null}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");
			ownedOperations.add(operation = createOperation("and", _Boolean, "org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("concat", _String, "org.eclipse.ocl.pivot.library.string.StringConcatOperation", org.eclipse.ocl.pivot.library.string.StringConcatOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _String, false));
			installComment(operation, "The concatenation of oclText[null] and s.");
			ownedOperations.add(operation = createOperation("implies", _Boolean, "org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("not", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation", org.eclipse.ocl.pivot.library.logical.BooleanNotOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_UNARY);
			ownedOperations.add(operation = createOperation("oclAsSet", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE));
			ownedOperations.add(operation = createOperation("oclAsType", tp_OclVoid_oclAsType_TT, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclVoid_oclAsType_TT));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OclVoid_oclAsType_TT, true));
			parameter.setIsTypeof(true);
			ownedOperations.add(operation = createOperation("oclIsInvalid", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation.INSTANCE));
			operation.setIsValidating(true);
			ownedOperations.add(operation = createOperation("oclIsKindOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			ownedOperations.add(operation = createOperation("oclIsTypeOf", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", _OclType, true));
			ownedOperations.add(operation = createOperation("oclIsUndefined", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation.INSTANCE));
			operation.setIsValidating(true);
			ownedOperations.add(operation = createOperation("oclType", _OclSelf, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation.INSTANCE));
			operation.setIsTypeof(true);
			ownedOperations.add(operation = createOperation("oclTypes", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation.INSTANCE));
			ownedOperations.add(operation = createOperation("or", _Boolean, "org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation", org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));
			ownedOperations.add(operation = createOperation("toString", _String, "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation.INSTANCE));
			installComment(operation, "Returns oclText[null].");
			ownedOperations.add(operation = createOperation("xor", _Boolean, "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation", org.eclipse.ocl.pivot.library.logical.BooleanXorOperation.INSTANCE));
			operation.setIsRequired(false);
			operation.setPrecedence(prec_XOR);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("b", _Boolean, false));

			ownedOperations = _OrderedCollection_OrderedCollection_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("at", tp_OrderedCollection_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("index", _Integer, true));
			installComment(operation, "The i-th element of ordered collection.");
			ownedOperations.add(operation = createOperation("first", tp_OrderedCollection_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			installComment(operation, "The first element in oclText[self].");
			ownedOperations.add(operation = createOperation("indexOf", _Integer, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation.INSTANCE));
			operation.setIsRequired(false);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("obj", tp_OrderedCollection_T, false));
			installComment(operation, "The index of object obj in the ordered collection. Returns null for an out of bound index.");
			ownedOperations.add(operation = createOperation("last", tp_OrderedCollection_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation.INSTANCE));
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			installComment(operation, "The last element in oclText[self].");

			ownedOperations = _OrderedSet_OrderedSet_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("-", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _UniqueCollection_OclAny_F, true));
			installComment(operation, "The elements of oclText[self], which are not in s.");
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("append", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_OrderedSet_T, false));
			installComment(operation, "The set of elements, consisting of all elements of oclText[self], followed by object.");
			ownedOperations.add(operation = createOperation("appendAll", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _OrderedCollection_OrderedSet_T_F, true));
			installComment(operation, "The set of elements, consisting of all elements of oclText[self], followed by objects.");
			ownedOperations.add(operation = createOperation("excluding", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_OrderedSet_T, false));
			installComment(operation, "The ordered set  containing all elements of oclText[self] apart from object.\n\nThe order of the remaining elements is not changed.");
			ownedOperations.add(operation = createOperation("excludingAll", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_OrderedSet_T_F, true));
			installComment(operation, "The ordered set containing all elements of oclText[self] apart from all occurrences of all objects.");
			ownedOperations.add(operation = createOperation("flatten", _OrderedSet_OrderedSet_flatten_T2_F, "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_OrderedSet_flatten_T2));
			ownedOperations.add(operation = createOperation("including", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_OrderedSet_T, false));
			installComment(operation, "The ordered set containing all elements of oclText[self] plus object added as the last element if not already present.");
			ownedOperations.add(operation = createOperation("includingAll", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_OrderedSet_T_F, true));
			installComment(operation, "The ordered set containing all elements of oclText[self] plus objects added as the last elements.");
			ownedOperations.add(operation = createOperation("insertAt", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("index", _Integer, true));
			ownedParameters.add(parameter = createParameter("object", tp_OrderedSet_T, false));
			installComment(operation, "The ordered set consisting of oclText[self] with object present at position index.");
			ownedOperations.add(operation = createOperation("prepend", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_OrderedSet_T, false));
			installComment(operation, "The sequence consisting of object, followed by all elements in oclText[self].");
			ownedOperations.add(operation = createOperation("prependAll", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _OrderedCollection_OrderedSet_T_F, true));
			installComment(operation, "The sequence consisting of objects, followed by all elements in oclText[self].");
			ownedOperations.add(operation = createOperation("reverse", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE));
			installComment(operation, "The ordered set of elements with same elements but with the opposite order.");
			ownedOperations.add(operation = createOperation("selectByKind", _OrderedSet_OrderedSet_selectByKind_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_OrderedSet_selectByKind_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OrderedSet_selectByKind_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The ordered set containing all elements of oclText[self] whose type conforms to oclText[type].");
			ownedOperations.add(operation = createOperation("selectByType", _OrderedSet_OrderedSet_selectByType_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_OrderedSet_selectByType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_OrderedSet_selectByType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The ordered set containing all elements of oclText[self] whose type is oclText[type].");
			ownedOperations.add(operation = createOperation("subOrderedSet", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lower", _Integer, true));
			ownedParameters.add(parameter = createParameter("upper", _Integer, true));
			installComment(operation, "The sub-set of oclText[self] starting at number lower, up to and including element number upper.");

			ownedOperations = _Sequence_Sequence_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "True if oclText[self] contains the same elements as s in the same order.");
			ownedOperations.add(operation = createOperation("append", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Sequence_T, false));
			installComment(operation, "The sequence of elements, consisting of all elements of oclText[self], followed by object.");
			ownedOperations.add(operation = createOperation("appendAll", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _OrderedCollection_Sequence_T_F, true));
			installComment(operation, "The sequence of elements, consisting of all elements of oclText[self], followed by objects.");
			ownedOperations.add(operation = createOperation("excluding", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Sequence_T, false));
			installComment(operation, "The sequence containing all elements of oclText[self] apart from all occurrences of object.\n\nThe order of the remaining elements is not changed.");
			ownedOperations.add(operation = createOperation("excludingAll", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Sequence_T_F, true));
			installComment(operation, "The sequence containing all elements of oclText[self] apart from all occurrences of all objects.");
			ownedOperations.add(operation = createOperation("flatten", _Sequence_Sequence_flatten_T2_F, "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Sequence_flatten_T2));
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same sequence as oclText[self].\nIf the element type is a collection type, the result is the sequence containing all the elements\nof all the recursively flattened elements of oclText[self]. The order of the elements is partial.");
			ownedOperations.add(operation = createOperation("including", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Sequence_T, false));
			installComment(operation, "The sequence containing all elements of oclText[self] plus object added as the last element.");
			ownedOperations.add(operation = createOperation("includingAll", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Sequence_T_F, true));
			installComment(operation, "The sequence containing all elements of oclText[self] plus objects added as the last elements.");
			ownedOperations.add(operation = createOperation("insertAt", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("index", _Integer, true));
			ownedParameters.add(parameter = createParameter("object", tp_Sequence_T, false));
			installComment(operation, "The sequence consisting of oclText[self] with object inserted at position index.");
			ownedOperations.add(operation = createOperation("prepend", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Sequence_T, false));
			installComment(operation, "The sequence consisting of object, followed by all elements in oclText[self].");
			ownedOperations.add(operation = createOperation("prependAll", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _OrderedCollection_Sequence_T_F, true));
			installComment(operation, "The sequence consisting of objects, followed by all elements in oclText[self].");
			ownedOperations.add(operation = createOperation("reverse", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE));
			installComment(operation, "The sequence containing the same elements but with the opposite order.");
			ownedOperations.add(operation = createOperation("selectByKind", _Sequence_Sequence_selectByKind_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Sequence_selectByKind_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Sequence_selectByKind_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The sequence containing all elements of oclText[self] whose type conforms to oclText[type].");
			ownedOperations.add(operation = createOperation("selectByType", _Sequence_Sequence_selectByType_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Sequence_selectByType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Sequence_selectByType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The sequence containing all elements of oclText[self] whose type is oclText[type].");
			ownedOperations.add(operation = createOperation("subSequence", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation", org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation.INSTANCE));
			operation.setIsInvalidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lower", _Integer, true));
			ownedParameters.add(parameter = createParameter("upper", _Integer, true));
			installComment(operation, "The sub-sequence of oclText[self] starting at number lower, up to and including element number upper.");

			ownedOperations = _Set_Set_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("-", _Set_Set_T, "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _UniqueCollection_OclAny_F, true));
			installComment(operation, "The elements of oclText[self], which are not in s.");
			ownedOperations.add(operation = createOperation("<>", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("=", _Boolean, "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE));
			operation.setPrecedence(prec_EQUALITY);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object2", _OclSelf, false));
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");
			ownedOperations.add(operation = createOperation("excluding", _Set_Set_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Set_T, false));
			installComment(operation, "The set containing all elements of oclText[self] without object.");
			ownedOperations.add(operation = createOperation("excludingAll", _Set_Set_T, "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Set_T_F, true));
			installComment(operation, "The set containing all elements of oclText[self] apart from all occurrences of all objects.");
			ownedOperations.add(operation = createOperation("flatten", _Set_Set_flatten_T2_F, "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Set_flatten_T2));
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same set as oclText[self].\nIf the element type is a collection type, the result is the set containing all the elements of all the recursively flattened elements of oclText[self].");
			ownedOperations.add(operation = createOperation("including", _Set_Set_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", tp_Set_T, false));
			installComment(operation, "The set containing all elements of oclText[self] plus object.");
			ownedOperations.add(operation = createOperation("includingAll", _Set_Set_T, "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("objects", _Collection_Set_T_F, true));
			installComment(operation, "The set containing all elements of oclText[self] and objects.");
			ownedOperations.add(operation = createOperation("selectByKind", _Set_Set_selectByKind_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Set_selectByKind_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Set_selectByKind_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The set containing all elements of oclText[self] whose type conforms to oclText[type].");
			ownedOperations.add(operation = createOperation("selectByType", _Set_Set_selectByType_TT_T, "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Set_selectByType_TT));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type", tp_Set_selectByType_TT, true));
			parameter.setIsTypeof(true);
			installComment(operation, "The set containing all elements of oclText[self] whose type is oclText[type].");

			ownedOperations = _Stereotype.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_T, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Return a set of all instances of the stereotype and derived types of self.");

			ownedOperations = _Type.getOwnedOperations();
			ownedOperations.add(operation = createOperation("conformsTo", _Boolean, "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation", org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("type2", _Type, false));
			installComment(operation, "Returns true if type2 conforms to self.");

			ownedOperations = _UniqueCollection_UniqueCollection_T.getOwnedOperations();
			ownedOperations.add(operation = createOperation("-", _UniqueCollection_UniqueCollection_T, "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE));
			operation.setPrecedence(prec_ADDITIVE);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _UniqueCollection_OclAny_F, true));
			installComment(operation, "The elements of oclText[self], which are not in s.");
			ownedOperations.add(operation = createOperation("intersection", _Set_UniqueCollection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("c", _Collection_UniqueCollection_T_F, true));
			installComment(operation, "The intersection of oclText[self] and c (i.e., the set of all elements that are in both oclText[self] and c).");
			ownedOperations.add(operation = createOperation("symmetricDifference", _Set_UniqueCollection_T_F, "org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation", org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _UniqueCollection_OclAny_F, true));
			installComment(operation, "The set containing all the elements that are in oclText[self] or s, but not in both.");
			ownedOperations.add(operation = createOperation("union", _Set_UniqueCollection_T_F, "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation", org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE));
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("s", _UniqueCollection_UniqueCollection_T, true));
			installComment(operation, "The set consisting of all elements in oclText[self] and all elements in s.");

			ownedOperations = _VoidType.getOwnedOperations();
			ownedOperations.add(operation = createOperation("allInstances", _Set_OclSelf_F, "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE));
			installComment(operation, "Returns oclText[Set{null}].");
		}

		private void installIterations() {
			List<Operation> ownedIterations;
			List<Parameter> ownedParameters;
			Iteration iteration;
			Parameter parameter;

			ownedIterations = _Bag_Bag_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("closure", _Set_Bag_T_F, "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_Set, false));
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");
			ownedIterations.add(iteration = createIteration("collectNested", _Bag_Bag_collectNested_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Bag_collectNested_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_Bag_collectNested_V, false));
			installComment(iteration, "The Bag of elements which results from applying body to every member of the source nonordered collection.");
			ownedIterations.add(iteration = createIteration("collect", _Bag_Bag_collect_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Bag_collect_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_Bag_collect_V, false));
			ownedIterations.add(iteration = createIteration("reject", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_Boolean, true));
			installComment(iteration, "The sub-bag of the source bag for which body is oclText[false].\n\noclCode[self->reject(iterator | body) = self->select(iterator | not body)].");
			ownedIterations.add(iteration = createIteration("select", _Bag_Bag_T, "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_Boolean, true));
			installComment(iteration, "The sub-bag of the source bag for which body is oclText[true].\n\noclCode[self->select(iterator | body) =\nself->iterate(iterator; result : Bag(T) = Bag{} |\nif body then result->including(iterator)\nelse result\nendif)]");
			ownedIterations.add(iteration = createIteration("sortedBy", _Sequence_Bag_T_F, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Bag_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Bag_T_OclAny, false));
			installComment(iteration, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			ownedIterations = _Collection_Collection_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("any", tp_Collection_T, "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE));
			iteration.setIsRequired(false);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("body", _Lambda_Collection_T_Boolean, true));
			installComment(iteration, "Returns any element in the e[source] null-free collection for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any element,\notherwise if there are one or more elements for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the result is oclText[null].\n\nlet source : Collection(T) = ..., body : Lambda T() : Boolean = ... in\nsource->any(iterator | body) = source->select(iterator | body)->asSequence()->first()");
			ownedIterations.add(iteration = createIteration("collectBy", _Map_Collection_T_F_Collection_collectBy_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE, tp_Collection_collectBy_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Collection_collectBy_V, false));
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			ownedIterations.add(iteration = createIteration("collectNested", _Collection_Collection_collectNested_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Collection_collectNested_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Collection_collectNested_V, false));
			installComment(iteration, "The Collection of elements which results from applying body to every member of the source collection.");
			ownedIterations.add(iteration = createIteration("collect", _Collection_Collection_collect_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Collection_collect_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Collection_collect_V, false));
			installComment(iteration, "The Collection of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("j", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("k", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j], oclText[k] in the source collection.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("j", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j] in the source collection.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one element oclText[i] in the source collection.");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("j", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("k", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j] in the source collection; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters.add(parameter = createParameter("j", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j], oclText[k] in the source collection; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each element oclText[i] in the source collection; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("isUnique", _Boolean, "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_OclAny, false));
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			installComment(iteration, "Results in oclText[true] if body evaluates to a different value for each element oclText[i] in the source collection; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("iterate", tp_Collection_iterate_Tacc, "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE, tp_Collection_iterate_Tacc));
			iteration.setIsRequired(false);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedAccumulators();
			ownedParameters.add(parameter = createParameter("acc", tp_Collection_iterate_Tacc, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Collection_iterate_Tacc, false));
			installComment(iteration, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source collection.");
			ownedIterations.add(iteration = createIteration("one", _Boolean, "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, true));
			installComment(iteration, "Results in oclText[true] if there is exactly one element in the source collection for which body is oclText[true].");
			ownedIterations.add(iteration = createIteration("reject", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, true));
			installComment(iteration, "The sub-collection of the source collection for which body is oclText[false].");
			ownedIterations.add(iteration = createIteration("select", _Collection_Collection_T, "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_Boolean, true));
			installComment(iteration, "The sub-collection of the source collection for which body is oclText[true].");
			ownedIterations.add(iteration = createIteration("sortedBy", _Sequence_Collection_T_F, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Collection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Collection_T_OclAny, false));
			installComment(iteration, "Results in the Collection containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			ownedIterations = _Map_Map_K_Map_V.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("any", tp_Map_K, "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE));
			iteration.setIsRequired(false);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("body", _Lambda_Map_K_Boolean, true));
			installComment(iteration, "Returns the key of any element in the e[source] map for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any key,\notherwise if there are one or more kets for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the null is oclText[invalid].\n\nlet source : Map(K,V) = ..., body : Lambda K(V) : Boolean = ... in\nsource->any(key <- value | body) = source->select(key | let value = source->at(key) in body)->asSequence()->first()");
			ownedIterations.add(iteration = createIteration("collectBy", _Map_Map_K_F_Map_collectBy_V2_F, "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE, tp_Map_collectBy_V2));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Map_collectBy_V2, false));
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");
			ownedIterations.add(iteration = createIteration("collectNested", _Map_Map_K_F_Map_collectNested_V2_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Map_collectNested_V2));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Map_collectNested_V2, false));
			installComment(iteration, "The Map of key and values which results from applying body to every value of the source map.");
			ownedIterations.add(iteration = createIteration("collect", _Bag_Map_collect_V2_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Map_collect_V2));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Map_collect_V2, false));
			installComment(iteration, "The Map of key and values that results from applying body to every value of the source map.\nThe result is flattened.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k1", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k2", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k3", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k1", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k2", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2] in the source map.");
			ownedIterations.add(iteration = createIteration("exists", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one key oclText[k] in the source map.");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k1", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k2", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k3", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k1", tp_Map_K, false));
			ownedParameters.add(parameter = createParameter("k2", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2] in the source map; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("forAll", _Boolean, "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE));
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("k", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, false));
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each key oclText[k] in the source map; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("isUnique", _Boolean, "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_OclAny, false));
			installComment(iteration, "Results in oclText[true] if body evaluates to a different value for each key oclText[k] in the source map; otherwise, result is oclText[false].");
			ownedIterations.add(iteration = createIteration("iterate", tp_Map_iterate_Tacc, "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE, tp_Map_iterate_Tacc));
			iteration.setIsRequired(false);
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Map_K, false));
			ownedParameters = iteration.getOwnedAccumulators();
			ownedParameters.add(parameter = createParameter("acc", tp_Map_iterate_Tacc, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Map_iterate_Tacc, false));
			installComment(iteration, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source map.");
			ownedIterations.add(iteration = createIteration("one", _Boolean, "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, true));
			installComment(iteration, "Results in oclText[true] if there is exactly one element in the source map for which body is oclText[true].");
			ownedIterations.add(iteration = createIteration("reject", _Map_Map_K_F_Map_V_F, "org.eclipse.ocl.pivot.library.iterator.MapRejectIteration", org.eclipse.ocl.pivot.library.iterator.MapRejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, true));
			installComment(iteration, "The subset of the source set for which body is oclText[false].");
			ownedIterations.add(iteration = createIteration("select", _Map_Map_K_F_Map_V_F, "org.eclipse.ocl.pivot.library.iterator.MapSelectIteration", org.eclipse.ocl.pivot.library.iterator.MapSelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Map_K, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Map_K_Boolean, true));
			installComment(iteration, "The subset of set for which expr is oclText[true].");

			ownedIterations = _OrderedSet_OrderedSet_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("closure", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_OrderedSet, false));
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");
			ownedIterations.add(iteration = createIteration("collectNested", _Sequence_OrderedSet_collectNested_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_OrderedSet_collectNested_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_OrderedSet_collectNested_V, false));
			installComment(iteration, "The sequence of elements that results from applying body to every member of the source ordered collection.");
			ownedIterations.add(iteration = createIteration("collect", _Sequence_OrderedSet_collect_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_OrderedSet_collect_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_OrderedSet_collect_V, false));
			installComment(iteration, "The Sequence of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			ownedIterations.add(iteration = createIteration("reject", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_Boolean, true));
			installComment(iteration, "The ordered set of the source ordered set for which body is oclText[false].");
			ownedIterations.add(iteration = createIteration("select", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_Boolean, true));
			installComment(iteration, "The ordered set of the source ordered set for which body is oclText[true]");
			ownedIterations.add(iteration = createIteration("sortedBy", _OrderedSet_OrderedSet_T, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_OrderedSet_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_OrderedSet_T_OclAny, false));
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");

			ownedIterations = _Sequence_Sequence_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("closure", _OrderedSet_Sequence_T_F, "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_OrderedSet, false));
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");
			ownedIterations.add(iteration = createIteration("collectNested", _Sequence_Sequence_collectNested_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Sequence_collectNested_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_Sequence_collectNested_V, false));
			installComment(iteration, "The sequence of elements that results from applying body to every member of the source ordered collection.");
			ownedIterations.add(iteration = createIteration("collect", _Sequence_Sequence_collect_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Sequence_collect_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_Sequence_collect_V, false));
			installComment(iteration, "The Bag of elements that results from applying body to every member of the source sequence.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			ownedIterations.add(iteration = createIteration("reject", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_Boolean, true));
			installComment(iteration, "The subsequence of the source sequence for which body is oclText[false].");
			ownedIterations.add(iteration = createIteration("select", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_Boolean, true));
			installComment(iteration, "The subsequence of the source sequence for which body is oclText[true].");
			ownedIterations.add(iteration = createIteration("sortedBy", _Sequence_Sequence_T, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Sequence_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Sequence_T_OclAny, false));
			installComment(iteration, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			ownedIterations = _Set_Set_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("closure", _Set_Set_T, "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, true));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_Set, false));
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");
			ownedIterations.add(iteration = createIteration("collectNested", _Bag_Set_collectNested_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Set_collectNested_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_Set_collectNested_V, false));
			installComment(iteration, "The Bag of elements which results from applying body to every member of the source nonordered collection.");
			ownedIterations.add(iteration = createIteration("collect", _Bag_Set_collect_V_F, "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Set_collect_V));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_Set_collect_V, false));
			installComment(iteration, "The Bag of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");
			ownedIterations.add(iteration = createIteration("reject", _Set_Set_T, "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_Boolean, true));
			installComment(iteration, "The subset of the source set for which body is oclText[false].");
			ownedIterations.add(iteration = createIteration("select", _Set_Set_T, "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_Boolean, true));
			installComment(iteration, "The subset of set for which expr is oclText[true].");
			ownedIterations.add(iteration = createIteration("sortedBy", _OrderedSet_Set_T_F, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_Set_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_Set_T_OclAny, false));
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");

			ownedIterations = _UniqueCollection_UniqueCollection_T.getOwnedOperations();
			ownedIterations.add(iteration = createIteration("sortedBy", _OrderedSet_UniqueCollection_T_F, "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE));
			ownedParameters = iteration.getOwnedIterators();
			ownedParameters.add(parameter = createParameter("i", tp_UniqueCollection_T, false));
			ownedParameters = iteration.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("lambda", _Lambda_UniqueCollection_T_OclAny, false));
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");
		}

		private void installProperties() {
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
			ownedProperties.add(property = createProperty("elementType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty.INSTANCE);
			installComment(property, "Evaluates to the type of the collection elements.");
			@NonNull Property pr_CollectionType_elementType = property;
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
			ownedProperties.add(property = createProperty("keyType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty.INSTANCE);
			installComment(property, "The key type of the key-value pairs of oclText[self].");
			@NonNull Property pr_MapType_keyType = property;
			ownedProperties.add(property = createProperty("valueType", _Type));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.map.MapValueTypeProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.map.MapValueTypeProperty.INSTANCE);
			installComment(property, "The value type of the key-value pairs of oclText[self].");
			@NonNull Property pr_MapType_valueType = property;

			ownedProperties = _OclAny.getOwnedProperties();
			ownedProperties.add(property = createProperty("OclInvalid", _Bag_OclInvalid_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_OclAny_OclInvalid_oclBadProperty = property;

			ownedProperties = _OclElement.getOwnedProperties();
			ownedProperties.add(property = createProperty("oclContainer", _OclElement));
			property.setIsRequired(false);
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty.INSTANCE);
			installComment(property, "The object for which self is a composed content or null if there is no such object.");
			@NonNull Property pr_OclElement_oclContainer = property;
			ownedProperties.add(property = createProperty("oclContents", _Set_OclElement_T));
			property.setIsResolveProxies(true);
			property.setImplementationClass("org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty");
			property.setImplementation(org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty.INSTANCE);
			installComment(property, "The composed contents of self.");
			@NonNull Property pr_OclElement_oclContents = property;
			ownedProperties.add(property = createProperty("OclElement", _Bag_OclElement_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_OclElement_OclElement_oclContainer = property;
			ownedProperties.add(property = createProperty("OclElement", _Bag_OclElement_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_OclElement_OclElement_oclContents = property;

			ownedProperties = _OclInvalid.getOwnedProperties();
			ownedProperties.add(property = createProperty("oclBadProperty", _OclAny));
			property.setIsRequired(false);
			property.setIsResolveProxies(true);
			installComment(property, "An oclBadProperty may be used as a placeholder in an unsuccessfully created OCLExpression.");
			@NonNull Property pr_OclInvalid_oclBadProperty = property;

			ownedProperties = _Type.getOwnedProperties();
			ownedProperties.add(property = createProperty("CollectionType", _Bag_CollectionType_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_Type_CollectionType_elementType = property;
			ownedProperties.add(property = createProperty("MapType", _Bag_MapType_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_Type_MapType_keyType = property;
			ownedProperties.add(property = createProperty("MapType", _Bag_MapType_F));
			property.setIsImplicit(true);
			property.setIsResolveProxies(true);
			@NonNull Property pr_Type_MapType_valueType = property;

			setOpposites(pr_CollectionType_elementType, pr_Type_CollectionType_elementType);
			setOpposites(pr_MapType_keyType, pr_Type_MapType_keyType);
			setOpposites(pr_MapType_valueType, pr_Type_MapType_valueType);
			setOpposites(pr_OclAny_OclInvalid_oclBadProperty, pr_OclInvalid_oclBadProperty);
			setOpposites(pr_OclElement_oclContainer, pr_OclElement_OclElement_oclContainer);
			setOpposites(pr_OclElement_oclContents, pr_OclElement_OclElement_oclContents);
		}
	}
}
