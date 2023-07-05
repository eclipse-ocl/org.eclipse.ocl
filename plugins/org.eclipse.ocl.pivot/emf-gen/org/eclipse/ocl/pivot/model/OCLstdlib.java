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
import org.eclipse.ocl.pivot.Parameter;
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
			return org.eclipse.ocl.pivot.model.OCLmetamodel.PIVOT_URI.equals(metamodelURI);
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

		private Contents(@NonNull Resource resource, @NonNull String asURI)
		{
			model = createModel(asURI);
			resource.getContents().add(model);
			ocl = createLibrary("ocl", "ocl", "http://www.eclipse.org/ocl/2015/Library", IdManager.METAMODEL, OCLstdlibPackage.eINSTANCE);
			installComment(ocl, "This clause describes the OCL Standard Library of predefined types, their operations, and predefined expression templates in the OCL.\nThis sub clause contains all standard types defined within OCL, including all the operations defined on those types.\nFor each operation the signature and a description of the semantics is given.\nWithin the description, the reserved word \u2018result\u2019 is used to refer to the value that results from evaluating the operation.\nIn several places, post conditions are used to describe properties of the result.\nWhen there is more than one postcondition, all postconditions must be true.\nA similar thing is true for multiple preconditions.\nIf these are used, the operation is only defined if all preconditions evaluate to oclText[true].\n\nheading:1[Introduction]\n\nThe structure, syntax, and semantics of the OCL is defined in Clauses 8 (\u201CAbstract Syntax\u201D), 9 (\u201CConcrete Syntax\u201D),\nand 10 (\u201CSemantics Described using UML\u201D).\nThis sub clause adds another part to the OCL definition: a library of predefined types and operations.\nAny implementation of OCL must include this library package. This approach has also been taken by e.g., the Java definition,\nwhere the language definition and the standard libraries are both mandatory parts of the complete language definition.\n\nThe OCL standard library defines a number of types.\nIt includes several primitive types: UnlimitedNatural, Integer, Real, String, and Boolean.\nThese are familiar from many other languages. The second part of the standard library consists of the collection types.\nThey are Bag, Set, Sequence, and Collection where Collection is an abstract type.\nNote that all types defined in the OCL standard library are instances of an abstract syntax class.\nThe OCL standard library exists at the modeling level, also referred to as the M1 level, where the abstract syntax is the metalevel or M2 level.\n\nNext to definitions of types the OCL standard library defines a number of template expressions.\nMany operations defined on collections map not on the abstract syntax metaclass FeatureCallExp, but on the IteratorExp.\nFor each of these a template expression that defines the name and format of the expression is defined in 11.8, Predefined Iterator Expressions.\n\nThe Standard Library may be extended with new types, new operations and new iterators.\nIn particular new operations can be defined for collections.\n\nCertain String operations depend on the prevailing locale to ensure that Strings are collated and characters are case-converted\nin an appropriate fashion.\nA locale is defined as a concatenation of up to three character sequences separated by underscores,\nwith the first sequence identifying the language and the second sequence identifying the country.\nThe third sequence is empty but may encode an implementation-specific variant.\nTrailing empty strings and separators may be omitted.\n\nThe character sequences for languages are defined by ISO 639.\n\nThe character sequences for countries are defined by ISO 3166.\n\n\u2018fr_CA\u2019 therefore identifies the locale for the French language in the Canada country.\n\nComparison of strings and consequently the collation order of Collection::sortedBy()\nconforms to the Unicode Collation algorithm defined by Unicode Technical Standard#10.\n\nThe locale is \u2018en_us\u2019 by default but may be configured by a property constraint on OclAny::oclLocale.\n\nThe prevailing locale is defined by the prevailing value of oclLocale within the current environment;\nit may therefore be changed temporarily by using a Let expression.\nlet oclLocale : String = \u2018fr_CA\u2019 in aString.toUpperCase()\n\nheading:1[Iterators]\n\nThis sub clause defines the standard OCL iterator expressions.\nIn the abstract syntax these are all instances of IteratorExp.\nThese iterator expressions always have a collection expression as their source,\nas is defined in the well-formedness rules in Clause 8 (\u201CAbstract Syntax\u201D).\nThe defined iterator expressions are shown per source collection type.\nThe semantics of each iterator expression is defined through a mapping from the iterator to the \u2018iterate\u2019 construct.\nThis means that the semantics of the iterator expressions do not need to be defined separately in the semantics sub clauses.\n\nIn all of the following OCL expressions, the lefthand side of the equals sign is the IteratorExp to be defined,\nand the righthand side of the equals sign is the equivalent as an IterateExp.\nThe names source, body, and iterator refer to the role names in the abstract syntax:\n\nsource\tThe source expression of the IteratorExp.\n\nbody\tThe body expression of the IteratorExp.\n\niterator\tThe iterator variable of the IteratorExp.\n\nresult\tThe result variable of the IterateExp.\n\nheading:2[Extending the Standard Library with Iterator Expressions]\n\nIt is possible to add new iterator expressions in the standard library.\nIf this is done the semantics of a new iterator should be defined by mapping it to existing constructs,\nin the same way the semantics of pre-defined iterators is done (see sub clause 11.9)");
			installPackages();
			installPrecedences();
			installTemplateParameters();
			installClassTypes();
			installPrimitiveTypes();
			installGenericAggregateTypes();
			installOperationDeclarations();
			installIterationDeclarations();
			installSpecializedAggregateTypes0();
			installSpecializedAggregateTypes1();
			installSpecializedAggregateTypes2();
			installAggregateSuperTypes();
			installOperationBodies();
			installIterationBodies();
			installProperties();
		}

		public @NonNull Model getModel() {
			return model;
		}

		private void installPackages() {
			model.getOwnedPackages().add(ocl);
			model.getOwnedPackages().add(orphanage);
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
		private Class _OclAny;
		private Class _OclComparable;
		private Class _OclElement;
		private Class _OclEnumeration;
		private Class _OclInvalid;
		private Class _OclLambda;
		private Class _OclMessage;
		private Class _OclSelf;
		private Class _OclState;
		private Class _OclStereotype;
		private Class _OclSummable;
		private Class _OclTuple;
		private Class _OclType;
		private Class _OclVoid;
		private Class _State;
		private Class _Stereotype;
		private Class _Type;
		private Class _VoidType;

		private void installClassTypes() {
			Class type;

			type = _BooleanType = createClass(ocl, "BooleanType");
			installComment(type, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			type = _Class = createClass(ocl, "Class");
			type = _CollectionType = createClass(ocl, "CollectionType");
			type = _Enumeration = createClass(ocl, "Enumeration");
			installComment(type, "@Deprecated: Use OclEnumeration\nThe Enumeration type is the type of an OrderedSet of EnumerationLiteral.");
			type = _EnumerationLiteral = createClass(ocl, "EnumerationLiteral");
			installComment(type, "The standard type EnumerationLiteral represents a named constant value of an Enumeration.");
			type = _InvalidType = createClass(ocl, "InvalidType");
			type = _MapType = createClass(ocl, "MapType");
			type = _OclAny = createClass(ocl, OCLstdlibPackage.Literals.OCL_ANY);
			type.setIsAbstract(true);
			installComment(type, "The number of elements in the collection oclText[self].essions.\nOclAny is itself an instance of the metatype AnyType.\n\nAll classes in a UML model inherit all operations defined on OclAny.\nTo avoid name conflicts between properties in the model and the properties inherited from OclAny,\nall names on the properties of OclAny start with \u2018ocl.\u2019\nAlthough theoretically there may still be name conflicts, they can be avoided.\nOne can also use qualification by OclAny (name of the type) to explicitly refer to the OclAny properties.\n\nOperations of OclAny, where the instance of OclAny is called object.");
			type = _OclComparable = createClass(ocl, OCLstdlibPackage.Literals.OCL_COMPARABLE);
			type.setIsAbstract(true);
			installComment(type, "The type OclComparable defines the compareTo operation used by the sortedBy iteration. Only types that provide a derived\ncompareTo implementation may be sorted.");
			type = _OclElement = createClass(ocl, OCLstdlibPackage.Literals.OCL_ELEMENT);
			type.setIsAbstract(true);
			installComment(type, "The type OclElement is the implicit supertype of any user-defined type that has no explicit supertypes. Operations defined\nfor OclElement are therefore applicable to all user-defined types.");
			type = _OclEnumeration = createClass(ocl, OCLstdlibPackage.Literals.OCL_ENUMERATION);
			type.setIsAbstract(true);
			installComment(type, "The OclEnumeration type is the implicit supertype of any user Enumeration type.\nFIXME This is probably obsolete now that static / meta-types clarified.");
			type = _OclInvalid = createClass(ocl, OCLstdlibPackage.Literals.OCL_INVALID);
			type.setIsAbstract(true);
			installComment(type, "The type OclInvalid is a type that conforms to all other types.\nIt has one single instance, identified as  oclText[invalid].\nAny property call applied on invalid results in oclText[invalid], except for the operations oclIsUndefined() and oclIsInvalid().\nOclInvalid is itself an instance of the metatype InvalidType.");
			type = _OclLambda = createClass(ocl, OCLstdlibPackage.Literals.OCL_LAMBDA);
			type.setIsAbstract(true);
			installComment(type, "The type OclLambda is the implicit supertype of all Lambda types. The operations defined for OclLambda\ntherefore apply to all lambda expressions.");
			type = _OclMessage = createClass(ocl, OCLstdlibPackage.Literals.OCL_MESSAGE);
			type.setIsAbstract(true);
			installComment(type, "OclMessage\nThis sub clause contains the definition of the standard type OclMessage.\nAs defined in this sub clause, each ocl message type is actually a template type with one parameter.\n\u2018T\u2019 denotes the parameter.\nA concrete ocl message type is created by substituting an operation or signal for the T.\n\nThe predefined type OclMessage is an instance of MessageType.\nEvery OclMessage is fully determined by either the operation, or signal given as parameter.\nNote that there is conceptually an undefined (infinite) number of these types,\nas each is determined by a different operation or signal.\nThese types are unnamed. Every type has as attributes the name of the operation or signal,\nand either all formal parameters of the operation, or all attributes of the signal.\nOclMessage is itself an instance of the metatype MessageType.\n\nOclMessage has a number of predefined operations, as shown in the OCL Standard Library.");
			type = _OclSelf = createClass(ocl, OCLstdlibPackage.Literals.OCL_SELF);
			type.setIsAbstract(true);
			installComment(type, "The pseudo-type OclSelf denotes the statically determinate type of oclText[self] in Operation\nand Iteration signatures. Instances of OclSelf are never created.");
			type = _OclState = createClass(ocl, OCLstdlibPackage.Literals.OCL_STATE);
			type.setIsAbstract(true);
			type = _OclStereotype = createClass(ocl, OCLstdlibPackage.Literals.OCL_STEREOTYPE);
			type.setIsAbstract(true);
			installComment(type, "The type OclStereotype is the implicit supertype of any UML stereotype. Operations defined\nfor OclStereotype are therefore applicable to all UML stereotypes.");
			type = _OclSummable = createClass(ocl, OCLstdlibPackage.Literals.OCL_SUMMABLE);
			type.setIsAbstract(true);
			installComment(type, "The type OclSummable defines the sum and zero operations used by the Collection::sum iteration. Only types that provide derived\nsum and zero implementations may be summed.");
			type = _OclTuple = createClass(ocl, OCLstdlibPackage.Literals.OCL_TUPLE);
			type.setIsAbstract(true);
			installComment(type, "The type OclTuple is the implicit supertype of all Tuple types. The operations defined for OclTuple\ntherefore apply to all tuples.");
			type = _OclType = createClass(ocl, OCLstdlibPackage.Literals.OCL_TYPE);
			type.setIsAbstract(true);
			installComment(type, "The type OclType is the implicit supertype of any UML type. Operations defined\nfor OclType are therefore applicable to all UML types.");
			type = _OclVoid = createClass(ocl, OCLstdlibPackage.Literals.OCL_VOID);
			type.setIsAbstract(true);
			installComment(type, "The type OclVoid is a type that conforms to all other types except OclInvalid.\nIt has one single instance, identified as oclText[null], that corresponds with the UML LiteralNull value specification.\nAny property call applied on oclText[null] results in oclText[invalid], except for the\noclIsUndefined(), oclIsInvalid(), =(OclAny) and <>(OclAny) operations.\nHowever, by virtue of the implicit conversion to a collection literal,\nan expression evaluating to oclText[null] can be used as source of collection operations (such as \u2018isEmpty\u2019).\nIf the source is the oclText[null] literal, it is implicitly converted to Bag{}.\n\nOclVoid is itself an instance of the metatype VoidType.");
			type = _State = createClass(ocl, "State");
			type = _Stereotype = createClass(ocl, "Stereotype");
			type = _Type = createClass(ocl, "Type");
			installComment(type, "The UML Type is the supertype of anything that may be used as a type.");
			type = _VoidType = createClass(ocl, "VoidType");

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
			PrimitiveType type;

			type = _Boolean = createPrimitiveType(ocl, OCLstdlibPackage.Literals.BOOLEAN);
			installComment(type, "The standard type Boolean represents the common true/false values.\nBoolean is itself an instance of the metatype PrimitiveType (from UML).");
			type = _Integer = createPrimitiveType(ocl, OCLstdlibPackage.Literals.INTEGER);
			installComment(type, "The standard type Integer represents the mathematical concept of integer.\nInteger is itself an instance of the metatype PrimitiveType (from UML).");
			type = _Real = createPrimitiveType(ocl, OCLstdlibPackage.Literals.REAL);
			installComment(type, "The standard type Real represents the mathematical concept of real.\nNote that Integer is a subclass of Real,\nso for each parameter of type Real, you can use an integer as the actual parameter.\nReal is itself an instance of the metatype PrimitiveType (from UML).");
			type = _String = createPrimitiveType(ocl, OCLstdlibPackage.Literals.STRING);
			installComment(type, "The standard type String represents strings, which can be both ASCII or Unicode.\nString is itself an instance of the metatype PrimitiveType (from UML).");
			type = _UnlimitedNatural = createPrimitiveType(ocl, OCLstdlibPackage.Literals.UNLIMITED_NATURAL);
			installComment(type, "The standard type UnlimitedNatural is used to encode the non-negative values of a multiplicity specification.\nThis includes a special e[unlimited] value (*) that encodes the upper value of  a multiplicity specification.\nUnlimitedNatural is itself an instance of the metatype UnlimitedNaturalType.\n\nNote that UnlimitedNatural is not a subclass of Integer.");

			addSuperClass(_Boolean, _OclAny);
			addSuperClass(_Integer, _Real);
			addSuperClass(_Real, _OclComparable);
			addSuperClass(_Real, _OclSummable);
			addSuperClass(_String, _OclComparable);
			addSuperClass(_String, _OclSummable);
			addSuperClass(_UnlimitedNatural, _OclComparable);
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
		private TupleType _Tuple;
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

		private void installGenericAggregateTypes() {
			Class type;

			type = _Bag_Bag_T = createCollectionType(ocl, OCLstdlibPackage.Literals.BAG, tp_Bag_T, false, 0, -1);
			type = _Collection_Collection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.COLLECTION, tp_Collection_T, false, 0, -1);
			type = _Map_Map_K_Map_V = createMapType(ocl, OCLstdlibPackage.Literals.MAP, tp_Map_K, true, tp_Map_V, true);
			type = _OrderedCollection_OrderedCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_COLLECTION, tp_OrderedCollection_T, false, 0, -1);
			type = _OrderedSet_OrderedSet_T = createCollectionType(ocl, OCLstdlibPackage.Literals.ORDERED_SET, tp_OrderedSet_T, false, 0, -1);
			type = _Sequence_Sequence_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SEQUENCE, tp_Sequence_T, false, 0, -1);
			type = _Set_Set_T = createCollectionType(ocl, OCLstdlibPackage.Literals.SET, tp_Set_T, false, 0, -1);
			type = _UniqueCollection_UniqueCollection_T = createCollectionType(ocl, OCLstdlibPackage.Literals.UNIQUE_COLLECTION, tp_UniqueCollection_T, false, 0, -1);
		}


		private void installSpecializedAggregateTypes0() {
			Class type;

			type = _Lambda_Bag_T_Boolean = getLambdaType(_OclLambda, tp_Bag_T, _Boolean);
			type = _Lambda_Bag_T_Bag_collectNested_V = getLambdaType(_OclLambda, tp_Bag_T, tp_Bag_collectNested_V);
			type = _Lambda_Bag_T_Bag_collect_V = getLambdaType(_OclLambda, tp_Bag_T, tp_Bag_collect_V);
			type = _Lambda_Bag_T_OclAny = getLambdaType(_OclLambda, tp_Bag_T, _OclAny);
			type = _Lambda_Collection_T_Boolean = getLambdaType(_OclLambda, tp_Collection_T, _Boolean);
			type = _Lambda_Collection_T_Collection_collectBy_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collectBy_V);
			type = _Lambda_Collection_T_Collection_collectNested_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collectNested_V);
			type = _Lambda_Collection_T_Collection_collect_V = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_collect_V);
			type = _Lambda_Collection_T_Collection_iterate_Tacc = getLambdaType(_OclLambda, tp_Collection_T, tp_Collection_iterate_Tacc);
			type = _Lambda_Collection_T_OclAny = getLambdaType(_OclLambda, tp_Collection_T, _OclAny);
			type = _Lambda_Map_K_Boolean = getLambdaType(_OclLambda, tp_Map_K, _Boolean);
			type = _Lambda_Map_K_Map_collectBy_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collectBy_V2);
			type = _Lambda_Map_K_Map_collectNested_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collectNested_V2);
			type = _Lambda_Map_K_Map_collect_V2 = getLambdaType(_OclLambda, tp_Map_K, tp_Map_collect_V2);
			type = _Lambda_Map_K_Map_iterate_Tacc = getLambdaType(_OclLambda, tp_Map_K, tp_Map_iterate_Tacc);
			type = _Lambda_Map_K_OclAny = getLambdaType(_OclLambda, tp_Map_K, _OclAny);
			type = _Lambda_OrderedSet_T_Boolean = getLambdaType(_OclLambda, tp_OrderedSet_T, _Boolean);
			type = _Lambda_OrderedSet_T_OclAny = getLambdaType(_OclLambda, tp_OrderedSet_T, _OclAny);
			type = _Lambda_OrderedSet_T_OrderedSet_collectNested_V = getLambdaType(_OclLambda, tp_OrderedSet_T, tp_OrderedSet_collectNested_V);
			type = _Lambda_OrderedSet_T_OrderedSet_collect_V = getLambdaType(_OclLambda, tp_OrderedSet_T, tp_OrderedSet_collect_V);
			type = _Lambda_Sequence_T_Boolean = getLambdaType(_OclLambda, tp_Sequence_T, _Boolean);
			type = _Lambda_Sequence_T_OclAny = getLambdaType(_OclLambda, tp_Sequence_T, _OclAny);
			type = _Lambda_Sequence_T_Sequence_collectNested_V = getLambdaType(_OclLambda, tp_Sequence_T, tp_Sequence_collectNested_V);
			type = _Lambda_Sequence_T_Sequence_collect_V = getLambdaType(_OclLambda, tp_Sequence_T, tp_Sequence_collect_V);
			type = _Lambda_Set_T_Boolean = getLambdaType(_OclLambda, tp_Set_T, _Boolean);
			type = _Lambda_Set_T_OclAny = getLambdaType(_OclLambda, tp_Set_T, _OclAny);
			type = _Lambda_Set_T_Set_collectNested_V = getLambdaType(_OclLambda, tp_Set_T, tp_Set_collectNested_V);
			type = _Lambda_Set_T_Set_collect_V = getLambdaType(_OclLambda, tp_Set_T, tp_Set_collect_V);
			type = _Lambda_UniqueCollection_T_OclAny = getLambdaType(_OclLambda, tp_UniqueCollection_T, _OclAny);
			type = _Tuple = getTupleType(_OclTuple,
				createProperty("first", tp_Collection_T),
				createProperty("second", tp_Collection_product_T2));
		}

		private void installSpecializedAggregateTypes1() {
			Class type;

			type = _Bag_Bag_collectNested_V_T = getCollectionType(_Bag_Bag_T, tp_Bag_collectNested_V, true, 0, -1);
			type = _Bag_Bag_collect_V_T = getCollectionType(_Bag_Bag_T, tp_Bag_collect_V, true, 0, -1);
			type = _Bag_Bag_flatten_T2_T = getCollectionType(_Bag_Bag_T, tp_Bag_flatten_T2, true, 0, -1);
			type = _Bag_Bag_selectByKind_TT_T = getCollectionType(_Bag_Bag_T, tp_Bag_selectByKind_TT, true, 0, -1);
			type = _Bag_Bag_selectByType_TT_T = getCollectionType(_Bag_Bag_T, tp_Bag_selectByType_TT, true, 0, -1);
			type = _Bag_Bag_T_T = getCollectionType(_Bag_Bag_T, tp_Bag_T, true, 0, -1);
			type = _Bag_CollectionType_T = getCollectionType(_Bag_Bag_T, _CollectionType, true, 0, -1);
			type = _Bag_Collection_T_T = getCollectionType(_Bag_Bag_T, tp_Collection_T, true, 0, -1);
			type = _Bag_MapType_T = getCollectionType(_Bag_Bag_T, _MapType, true, 0, -1);
			type = _Bag_Map_collect_V2_T = getCollectionType(_Bag_Bag_T, tp_Map_collect_V2, true, 0, -1);
			type = _Bag_Map_V_T = getCollectionType(_Bag_Bag_T, tp_Map_V, true, 0, -1);
			type = _Bag_OclElement_T = getCollectionType(_Bag_Bag_T, _OclElement, true, 0, -1);
			type = _Bag_OclInvalid_T = getCollectionType(_Bag_Bag_T, _OclInvalid, true, 0, -1);
			type = _Bag_Set_collectNested_V_T = getCollectionType(_Bag_Bag_T, tp_Set_collectNested_V, true, 0, -1);
			type = _Bag_Set_collect_V_T = getCollectionType(_Bag_Bag_T, tp_Set_collect_V, true, 0, -1);
			type = _Collection_Integer_T = getCollectionType(_Collection_Collection_T, _Integer, true, 0, -1);
			type = _Collection_String_T = getCollectionType(_Collection_Collection_T, _String, true, 0, -1);
			type = _Collection_Tuple_T = getCollectionType(_Collection_Collection_T, _Tuple, true, 0, -1);
			type = _Collection_Bag_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Bag_collectNested_V, true, 0, -1);
			type = _Collection_Bag_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Bag_collect_V, true, 0, -1);
			type = _Collection_Bag_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Bag_flatten_T2, true, 0, -1);
			type = _Collection_Bag_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Bag_selectByKind_TT, true, 0, -1);
			type = _Collection_Bag_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Bag_selectByType_TT, true, 0, -1);
			type = _Collection_Bag_T_T = getCollectionType(_Collection_Collection_T, tp_Bag_T, true, 0, -1);
			type = _Collection_CollectionType_T = getCollectionType(_Collection_Collection_T, _CollectionType, true, 0, -1);
			type = _Collection_Collection_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Collection_collectNested_V, true, 0, -1);
			type = _Collection_Collection_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Collection_collect_V, true, 0, -1);
			type = _Collection_Collection_excludesAll_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_excludesAll_T2, true, 0, -1);
			type = _Collection_Collection_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_flatten_T2, true, 0, -1);
			type = _Collection_Collection_includesAll_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_includesAll_T2, true, 0, -1);
			type = _Collection_Collection_product_T2_T = getCollectionType(_Collection_Collection_T, tp_Collection_product_T2, true, 0, -1);
			type = _Collection_Collection_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByKind_TT, true, 0, -1);
			type = _Collection_Collection_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Collection_selectByType_TT, true, 0, -1);
			type = _Collection_Collection_T_T = getCollectionType(_Collection_Collection_T, tp_Collection_T, true, 0, -1);
			type = _Collection_MapType_T = getCollectionType(_Collection_Collection_T, _MapType, true, 0, -1);
			type = _Collection_Map_collect_V2_T = getCollectionType(_Collection_Collection_T, tp_Map_collect_V2, true, 0, -1);
			type = _Collection_Map_excludesAll_K2_T = getCollectionType(_Collection_Collection_T, tp_Map_excludesAll_K2, true, 0, -1);
			type = _Collection_Map_includesAll_K2_T = getCollectionType(_Collection_Collection_T, tp_Map_includesAll_K2, true, 0, -1);
			type = _Collection_Map_K_T = getCollectionType(_Collection_Collection_T, tp_Map_K, true, 0, -1);
			type = _Collection_Map_V_T = getCollectionType(_Collection_Collection_T, tp_Map_V, true, 0, -1);
			type = _Collection_OclAny_T = getCollectionType(_Collection_Collection_T, _OclAny, true, 0, -1);
			type = _Collection_OclElement_T = getCollectionType(_Collection_Collection_T, _OclElement, true, 0, -1);
			type = _Collection_OclInvalid_T = getCollectionType(_Collection_Collection_T, _OclInvalid, true, 0, -1);
			type = _Collection_OclSelf_T = getCollectionType(_Collection_Collection_T, _OclSelf, true, 0, -1);
			type = _Collection_OrderedCollection_T_T = getCollectionType(_Collection_Collection_T, tp_OrderedCollection_T, true, 0, -1);
			type = _Collection_OrderedSet_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			type = _Collection_OrderedSet_collect_V_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_collect_V, true, 0, -1);
			type = _Collection_OrderedSet_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			type = _Collection_OrderedSet_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			type = _Collection_OrderedSet_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			type = _Collection_OrderedSet_T_T = getCollectionType(_Collection_Collection_T, tp_OrderedSet_T, true, 0, -1);
			type = _Collection_Sequence_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Sequence_collectNested_V, true, 0, -1);
			type = _Collection_Sequence_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Sequence_collect_V, true, 0, -1);
			type = _Collection_Sequence_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Sequence_flatten_T2, true, 0, -1);
			type = _Collection_Sequence_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			type = _Collection_Sequence_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Sequence_selectByType_TT, true, 0, -1);
			type = _Collection_Sequence_T_T = getCollectionType(_Collection_Collection_T, tp_Sequence_T, true, 0, -1);
			type = _Collection_Set_collectNested_V_T = getCollectionType(_Collection_Collection_T, tp_Set_collectNested_V, true, 0, -1);
			type = _Collection_Set_collect_V_T = getCollectionType(_Collection_Collection_T, tp_Set_collect_V, true, 0, -1);
			type = _Collection_Set_flatten_T2_T = getCollectionType(_Collection_Collection_T, tp_Set_flatten_T2, true, 0, -1);
			type = _Collection_Set_selectByKind_TT_T = getCollectionType(_Collection_Collection_T, tp_Set_selectByKind_TT, true, 0, -1);
			type = _Collection_Set_selectByType_TT_T = getCollectionType(_Collection_Collection_T, tp_Set_selectByType_TT, true, 0, -1);
			type = _Collection_Set_T_T = getCollectionType(_Collection_Collection_T, tp_Set_T, true, 0, -1);
			type = _Collection_UniqueCollection_T_T = getCollectionType(_Collection_Collection_T, tp_UniqueCollection_T, true, 0, -1);
			type = _Map_Collection_T_F_Collection_collectBy_V_F = getMapType(_Map_Map_K_Map_V, tp_Collection_T, false, tp_Collection_collectBy_V, false);
			type = _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludesMap_K2, true, tp_Map_excludesMap_V2, true);
			type = _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_excludingMap_K2, true, tp_Map_excludingMap_V2, true);
			type = _Map_Map_includesMap_K2_T_Map_includesMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includesMap_K2, true, tp_Map_includesMap_V2, true);
			type = _Map_Map_includingMap_K2_T_Map_includingMap_V2_T = getMapType(_Map_Map_K_Map_V, tp_Map_includingMap_K2, true, tp_Map_includingMap_V2, true);
			type = _Map_Map_K_F_Map_collectBy_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectBy_V2, false);
			type = _Map_Map_K_F_Map_collectNested_V2_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_collectNested_V2, false);
			type = _Map_Map_K_T_Map_V_T = getMapType(_Map_Map_K_Map_V, tp_Map_K, true, tp_Map_V, true);
			type = _Map_Map_K_F_Map_V_F = getMapType(_Map_Map_K_Map_V, tp_Map_K, false, tp_Map_V, false);
			type = _OrderedCollection_Integer_T = getCollectionType(_OrderedCollection_OrderedCollection_T, _Integer, true, 0, -1);
			type = _OrderedCollection_String_T = getCollectionType(_OrderedCollection_OrderedCollection_T, _String, true, 0, -1);
			type = _OrderedCollection_Bag_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Bag_T, true, 0, -1);
			type = _OrderedCollection_Collection_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Collection_T, true, 0, -1);
			type = _OrderedCollection_OrderedSet_collectNested_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			type = _OrderedCollection_OrderedSet_collect_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_collect_V, true, 0, -1);
			type = _OrderedCollection_OrderedSet_flatten_T2_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			type = _OrderedCollection_OrderedSet_selectByKind_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			type = _OrderedCollection_OrderedSet_selectByType_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			type = _OrderedCollection_OrderedSet_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_OrderedSet_T, true, 0, -1);
			type = _OrderedCollection_Sequence_collectNested_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collectNested_V, true, 0, -1);
			type = _OrderedCollection_Sequence_collect_V_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_collect_V, true, 0, -1);
			type = _OrderedCollection_Sequence_flatten_T2_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_flatten_T2, true, 0, -1);
			type = _OrderedCollection_Sequence_selectByKind_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			type = _OrderedCollection_Sequence_selectByType_TT_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_selectByType_TT, true, 0, -1);
			type = _OrderedCollection_Sequence_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Sequence_T, true, 0, -1);
			type = _OrderedCollection_Set_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_Set_T, true, 0, -1);
			type = _OrderedCollection_UniqueCollection_T_T = getCollectionType(_OrderedCollection_OrderedCollection_T, tp_UniqueCollection_T, true, 0, -1);
			type = _OrderedSet_Collection_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Collection_T, true, 0, -1);
			type = _OrderedSet_OrderedSet_flatten_T2_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			type = _OrderedSet_OrderedSet_selectByKind_TT_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			type = _OrderedSet_OrderedSet_selectByType_TT_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			type = _OrderedSet_OrderedSet_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_OrderedSet_T, true, 0, -1);
			type = _OrderedSet_Sequence_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Sequence_T, true, 0, -1);
			type = _OrderedSet_Set_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_Set_T, true, 0, -1);
			type = _OrderedSet_UniqueCollection_T_T = getCollectionType(_OrderedSet_OrderedSet_T, tp_UniqueCollection_T, true, 0, -1);
			type = _Sequence_Integer_T = getCollectionType(_Sequence_Sequence_T, _Integer, true, 0, -1);
			type = _Sequence_String_T = getCollectionType(_Sequence_Sequence_T, _String, true, 0, -1);
			type = _Sequence_Bag_T_T = getCollectionType(_Sequence_Sequence_T, tp_Bag_T, true, 0, -1);
			type = _Sequence_Collection_T_T = getCollectionType(_Sequence_Sequence_T, tp_Collection_T, true, 0, -1);
			type = _Sequence_OrderedSet_collectNested_V_T = getCollectionType(_Sequence_Sequence_T, tp_OrderedSet_collectNested_V, true, 0, -1);
			type = _Sequence_OrderedSet_collect_V_T = getCollectionType(_Sequence_Sequence_T, tp_OrderedSet_collect_V, true, 0, -1);
			type = _Sequence_Sequence_collectNested_V_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_collectNested_V, true, 0, -1);
			type = _Sequence_Sequence_collect_V_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_collect_V, true, 0, -1);
			type = _Sequence_Sequence_flatten_T2_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_flatten_T2, true, 0, -1);
			type = _Sequence_Sequence_selectByKind_TT_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_selectByKind_TT, true, 0, -1);
			type = _Sequence_Sequence_selectByType_TT_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_selectByType_TT, true, 0, -1);
			type = _Sequence_Sequence_T_T = getCollectionType(_Sequence_Sequence_T, tp_Sequence_T, true, 0, -1);
			type = _Set_Tuple_T = getCollectionType(_Set_Set_T, _Tuple, true, 0, -1);
			type = _Set_Bag_T_T = getCollectionType(_Set_Set_T, tp_Bag_T, true, 0, -1);
			type = _Set_Collection_T_T = getCollectionType(_Set_Set_T, tp_Collection_T, true, 0, -1);
			type = _Set_Map_K_T = getCollectionType(_Set_Set_T, tp_Map_K, true, 0, -1);
			type = _Set_OclElement_T = getCollectionType(_Set_Set_T, _OclElement, true, 0, -1);
			type = _Set_OclSelf_T = getCollectionType(_Set_Set_T, _OclSelf, true, 0, -1);
			type = _Set_OclSelf_F = getCollectionType(_Set_Set_T, _OclSelf, false, 0, -1);
			type = _Set_Set_flatten_T2_T = getCollectionType(_Set_Set_T, tp_Set_flatten_T2, true, 0, -1);
			type = _Set_Set_selectByKind_TT_T = getCollectionType(_Set_Set_T, tp_Set_selectByKind_TT, true, 0, -1);
			type = _Set_Set_selectByType_TT_T = getCollectionType(_Set_Set_T, tp_Set_selectByType_TT, true, 0, -1);
			type = _Set_Set_T_T = getCollectionType(_Set_Set_T, tp_Set_T, true, 0, -1);
			type = _Set_UniqueCollection_T_T = getCollectionType(_Set_Set_T, tp_UniqueCollection_T, true, 0, -1);
			type = _UniqueCollection_Tuple_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _Tuple, true, 0, -1);
			type = _UniqueCollection_Bag_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Bag_T, true, 0, -1);
			type = _UniqueCollection_Collection_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Collection_T, true, 0, -1);
			type = _UniqueCollection_Map_K_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Map_K, true, 0, -1);
			type = _UniqueCollection_OclAny_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclAny, true, 0, -1);
			type = _UniqueCollection_OclElement_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclElement, true, 0, -1);
			type = _UniqueCollection_OclSelf_T = getCollectionType(_UniqueCollection_UniqueCollection_T, _OclSelf, true, 0, -1);
			type = _UniqueCollection_OrderedSet_flatten_T2_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_flatten_T2, true, 0, -1);
			type = _UniqueCollection_OrderedSet_selectByKind_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByKind_TT, true, 0, -1);
			type = _UniqueCollection_OrderedSet_selectByType_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_selectByType_TT, true, 0, -1);
			type = _UniqueCollection_OrderedSet_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_OrderedSet_T, true, 0, -1);
			type = _UniqueCollection_Sequence_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Sequence_T, true, 0, -1);
			type = _UniqueCollection_Set_flatten_T2_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_flatten_T2, true, 0, -1);
			type = _UniqueCollection_Set_selectByKind_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByKind_TT, true, 0, -1);
			type = _UniqueCollection_Set_selectByType_TT_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_selectByType_TT, true, 0, -1);
			type = _UniqueCollection_Set_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_Set_T, true, 0, -1);
			type = _UniqueCollection_UniqueCollection_T_T = getCollectionType(_UniqueCollection_UniqueCollection_T, tp_UniqueCollection_T, true, 0, -1);
		}

		private void installSpecializedAggregateTypes2() {
			Class type;

			type = _Lambda_Bag_T_Set = getLambdaType(_OclLambda, tp_Bag_T, _Set_Bag_T_T);
			type = _Lambda_OrderedSet_T_OrderedSet = getLambdaType(_OclLambda, tp_OrderedSet_T, _OrderedSet_OrderedSet_T_T);
			type = _Lambda_Sequence_T_OrderedSet = getLambdaType(_OclLambda, tp_Sequence_T, _OrderedSet_Sequence_T_T);
			type = _Lambda_Set_T_Set = getLambdaType(_OclLambda, tp_Set_T, _Set_Set_T_T);
		}

		private void installAggregateSuperTypes() {
			addSuperClass(_Lambda_Bag_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Bag_T_Bag_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Bag_T_Bag_collect_V, _OclLambda);
			addSuperClass(_Lambda_Bag_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Bag_T_Set, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collectBy_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_collect_V, _OclLambda);
			addSuperClass(_Lambda_Collection_T_Collection_iterate_Tacc, _OclLambda);
			addSuperClass(_Lambda_Collection_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Map_K_Boolean, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collectBy_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collectNested_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_collect_V2, _OclLambda);
			addSuperClass(_Lambda_Map_K_Map_iterate_Tacc, _OclLambda);
			addSuperClass(_Lambda_Map_K_OclAny, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_OrderedSet_T_OrderedSet_collect_V, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_OrderedSet, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Sequence_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Sequence_T_Sequence_collect_V, _OclLambda);
			addSuperClass(_Lambda_Set_T_Boolean, _OclLambda);
			addSuperClass(_Lambda_Set_T_OclAny, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set_collectNested_V, _OclLambda);
			addSuperClass(_Lambda_Set_T_Set_collect_V, _OclLambda);
			addSuperClass(_Lambda_UniqueCollection_T_OclAny, _OclLambda);
			addSuperClass(_Tuple, _OclTuple);
			addSuperClass(_Bag_Bag_collectNested_V_T, _Collection_Bag_collectNested_V_T);
			addSuperClass(_Bag_Bag_collect_V_T, _Collection_Bag_collect_V_T);
			addSuperClass(_Bag_Bag_flatten_T2_T, _Collection_Bag_flatten_T2_T);
			addSuperClass(_Bag_Bag_selectByKind_TT_T, _Collection_Bag_selectByKind_TT_T);
			addSuperClass(_Bag_Bag_selectByType_TT_T, _Collection_Bag_selectByType_TT_T);
			addSuperClass(_Bag_Bag_T_T, _Collection_Bag_T_T);
			addSuperClass(_Bag_CollectionType_T, _Collection_CollectionType_T);
			addSuperClass(_Bag_Collection_T_T, _Collection_Collection_T_T);
			addSuperClass(_Bag_MapType_T, _Collection_MapType_T);
			addSuperClass(_Bag_Map_collect_V2_T, _Collection_Map_collect_V2_T);
			addSuperClass(_Bag_Map_V_T, _Collection_Map_V_T);
			addSuperClass(_Bag_OclElement_T, _Collection_OclElement_T);
			addSuperClass(_Bag_OclInvalid_T, _Collection_OclInvalid_T);
			addSuperClass(_Bag_Set_collectNested_V_T, _Collection_Set_collectNested_V_T);
			addSuperClass(_Bag_Set_collect_V_T, _Collection_Set_collect_V_T);
			addSuperClass(_Bag_Bag_T, _Collection_Bag_T_T);
			addSuperClass(_Collection_Integer_T, _OclAny);
			addSuperClass(_Collection_String_T, _OclAny);
			addSuperClass(_Collection_Tuple_T, _OclAny);
			addSuperClass(_Collection_Bag_collectNested_V_T, _OclAny);
			addSuperClass(_Collection_Bag_collect_V_T, _OclAny);
			addSuperClass(_Collection_Bag_flatten_T2_T, _OclAny);
			addSuperClass(_Collection_Bag_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_Bag_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_Bag_T_T, _OclAny);
			addSuperClass(_Collection_CollectionType_T, _OclAny);
			addSuperClass(_Collection_Collection_collectNested_V_T, _OclAny);
			addSuperClass(_Collection_Collection_collect_V_T, _OclAny);
			addSuperClass(_Collection_Collection_excludesAll_T2_T, _OclAny);
			addSuperClass(_Collection_Collection_flatten_T2_T, _OclAny);
			addSuperClass(_Collection_Collection_includesAll_T2_T, _OclAny);
			addSuperClass(_Collection_Collection_product_T2_T, _OclAny);
			addSuperClass(_Collection_Collection_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_Collection_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_Collection_T_T, _OclAny);
			addSuperClass(_Collection_MapType_T, _OclAny);
			addSuperClass(_Collection_Map_collect_V2_T, _OclAny);
			addSuperClass(_Collection_Map_excludesAll_K2_T, _OclAny);
			addSuperClass(_Collection_Map_includesAll_K2_T, _OclAny);
			addSuperClass(_Collection_Map_K_T, _OclAny);
			addSuperClass(_Collection_Map_V_T, _OclAny);
			addSuperClass(_Collection_OclAny_T, _OclAny);
			addSuperClass(_Collection_OclElement_T, _OclAny);
			addSuperClass(_Collection_OclInvalid_T, _OclAny);
			addSuperClass(_Collection_OclSelf_T, _OclAny);
			addSuperClass(_Collection_OrderedCollection_T_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_collectNested_V_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_collect_V_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_flatten_T2_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_OrderedSet_T_T, _OclAny);
			addSuperClass(_Collection_Sequence_collectNested_V_T, _OclAny);
			addSuperClass(_Collection_Sequence_collect_V_T, _OclAny);
			addSuperClass(_Collection_Sequence_flatten_T2_T, _OclAny);
			addSuperClass(_Collection_Sequence_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_Sequence_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_Sequence_T_T, _OclAny);
			addSuperClass(_Collection_Set_collectNested_V_T, _OclAny);
			addSuperClass(_Collection_Set_collect_V_T, _OclAny);
			addSuperClass(_Collection_Set_flatten_T2_T, _OclAny);
			addSuperClass(_Collection_Set_selectByKind_TT_T, _OclAny);
			addSuperClass(_Collection_Set_selectByType_TT_T, _OclAny);
			addSuperClass(_Collection_Set_T_T, _OclAny);
			addSuperClass(_Collection_UniqueCollection_T_T, _OclAny);
			addSuperClass(_Collection_Collection_T, _OclAny);
			addSuperClass(_Map_Collection_T_F_Collection_collectBy_V_F, _OclAny);
			addSuperClass(_Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_includesMap_K2_T_Map_includesMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_includingMap_K2_T_Map_includingMap_V2_T, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_collectBy_V2_F, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_collectNested_V2_F, _OclAny);
			addSuperClass(_Map_Map_K_T_Map_V_T, _OclAny);
			addSuperClass(_Map_Map_K_F_Map_V_F, _OclAny);
			addSuperClass(_Map_Map_K_Map_V, _OclAny);
			addSuperClass(_OrderedCollection_Integer_T, _Collection_Integer_T);
			addSuperClass(_OrderedCollection_String_T, _Collection_String_T);
			addSuperClass(_OrderedCollection_Bag_T_T, _Collection_Bag_T_T);
			addSuperClass(_OrderedCollection_Collection_T_T, _Collection_Collection_T_T);
			addSuperClass(_OrderedCollection_OrderedSet_collectNested_V_T, _Collection_OrderedSet_collectNested_V_T);
			addSuperClass(_OrderedCollection_OrderedSet_collect_V_T, _Collection_OrderedSet_collect_V_T);
			addSuperClass(_OrderedCollection_OrderedSet_flatten_T2_T, _Collection_OrderedSet_flatten_T2_T);
			addSuperClass(_OrderedCollection_OrderedSet_selectByKind_TT_T, _Collection_OrderedSet_selectByKind_TT_T);
			addSuperClass(_OrderedCollection_OrderedSet_selectByType_TT_T, _Collection_OrderedSet_selectByType_TT_T);
			addSuperClass(_OrderedCollection_OrderedSet_T_T, _Collection_OrderedSet_T_T);
			addSuperClass(_OrderedCollection_Sequence_collectNested_V_T, _Collection_Sequence_collectNested_V_T);
			addSuperClass(_OrderedCollection_Sequence_collect_V_T, _Collection_Sequence_collect_V_T);
			addSuperClass(_OrderedCollection_Sequence_flatten_T2_T, _Collection_Sequence_flatten_T2_T);
			addSuperClass(_OrderedCollection_Sequence_selectByKind_TT_T, _Collection_Sequence_selectByKind_TT_T);
			addSuperClass(_OrderedCollection_Sequence_selectByType_TT_T, _Collection_Sequence_selectByType_TT_T);
			addSuperClass(_OrderedCollection_Sequence_T_T, _Collection_Sequence_T_T);
			addSuperClass(_OrderedCollection_Set_T_T, _Collection_Set_T_T);
			addSuperClass(_OrderedCollection_UniqueCollection_T_T, _Collection_UniqueCollection_T_T);
			addSuperClass(_OrderedCollection_OrderedCollection_T, _Collection_OrderedCollection_T_T);
			addSuperClass(_OrderedSet_Collection_T_T, _OrderedCollection_Collection_T_T);
			addSuperClass(_OrderedSet_Collection_T_T, _UniqueCollection_Collection_T_T);
			addSuperClass(_OrderedSet_OrderedSet_flatten_T2_T, _OrderedCollection_OrderedSet_flatten_T2_T);
			addSuperClass(_OrderedSet_OrderedSet_flatten_T2_T, _UniqueCollection_OrderedSet_flatten_T2_T);
			addSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _OrderedCollection_OrderedSet_selectByKind_TT_T);
			addSuperClass(_OrderedSet_OrderedSet_selectByKind_TT_T, _UniqueCollection_OrderedSet_selectByKind_TT_T);
			addSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _OrderedCollection_OrderedSet_selectByType_TT_T);
			addSuperClass(_OrderedSet_OrderedSet_selectByType_TT_T, _UniqueCollection_OrderedSet_selectByType_TT_T);
			addSuperClass(_OrderedSet_OrderedSet_T_T, _OrderedCollection_OrderedSet_T_T);
			addSuperClass(_OrderedSet_OrderedSet_T_T, _UniqueCollection_OrderedSet_T_T);
			addSuperClass(_OrderedSet_Sequence_T_T, _OrderedCollection_Sequence_T_T);
			addSuperClass(_OrderedSet_Sequence_T_T, _UniqueCollection_Sequence_T_T);
			addSuperClass(_OrderedSet_Set_T_T, _OrderedCollection_Set_T_T);
			addSuperClass(_OrderedSet_Set_T_T, _UniqueCollection_Set_T_T);
			addSuperClass(_OrderedSet_UniqueCollection_T_T, _OrderedCollection_UniqueCollection_T_T);
			addSuperClass(_OrderedSet_UniqueCollection_T_T, _UniqueCollection_UniqueCollection_T_T);
			addSuperClass(_OrderedSet_OrderedSet_T, _OrderedCollection_OrderedSet_T_T);
			addSuperClass(_OrderedSet_OrderedSet_T, _UniqueCollection_OrderedSet_T_T);
			addSuperClass(_Sequence_Integer_T, _OrderedCollection_Integer_T);
			addSuperClass(_Sequence_String_T, _OrderedCollection_String_T);
			addSuperClass(_Sequence_Bag_T_T, _OrderedCollection_Bag_T_T);
			addSuperClass(_Sequence_Collection_T_T, _OrderedCollection_Collection_T_T);
			addSuperClass(_Sequence_OrderedSet_collectNested_V_T, _OrderedCollection_OrderedSet_collectNested_V_T);
			addSuperClass(_Sequence_OrderedSet_collect_V_T, _OrderedCollection_OrderedSet_collect_V_T);
			addSuperClass(_Sequence_Sequence_collectNested_V_T, _OrderedCollection_Sequence_collectNested_V_T);
			addSuperClass(_Sequence_Sequence_collect_V_T, _OrderedCollection_Sequence_collect_V_T);
			addSuperClass(_Sequence_Sequence_flatten_T2_T, _OrderedCollection_Sequence_flatten_T2_T);
			addSuperClass(_Sequence_Sequence_selectByKind_TT_T, _OrderedCollection_Sequence_selectByKind_TT_T);
			addSuperClass(_Sequence_Sequence_selectByType_TT_T, _OrderedCollection_Sequence_selectByType_TT_T);
			addSuperClass(_Sequence_Sequence_T_T, _OrderedCollection_Sequence_T_T);
			addSuperClass(_Sequence_Sequence_T, _OrderedCollection_Sequence_T_T);
			addSuperClass(_Set_Tuple_T, _UniqueCollection_Tuple_T);
			addSuperClass(_Set_Bag_T_T, _UniqueCollection_Bag_T_T);
			addSuperClass(_Set_Collection_T_T, _UniqueCollection_Collection_T_T);
			addSuperClass(_Set_Map_K_T, _UniqueCollection_Map_K_T);
			addSuperClass(_Set_OclElement_T, _UniqueCollection_OclElement_T);
			addSuperClass(_Set_OclSelf_T, _UniqueCollection_OclSelf_T);
			addSuperClass(_Set_OclSelf_F, _UniqueCollection_OclSelf_T);
			addSuperClass(_Set_Set_flatten_T2_T, _UniqueCollection_Set_flatten_T2_T);
			addSuperClass(_Set_Set_selectByKind_TT_T, _UniqueCollection_Set_selectByKind_TT_T);
			addSuperClass(_Set_Set_selectByType_TT_T, _UniqueCollection_Set_selectByType_TT_T);
			addSuperClass(_Set_Set_T_T, _UniqueCollection_Set_T_T);
			addSuperClass(_Set_UniqueCollection_T_T, _UniqueCollection_UniqueCollection_T_T);
			addSuperClass(_Set_Set_T, _UniqueCollection_Set_T_T);
			addSuperClass(_UniqueCollection_Tuple_T, _Collection_Tuple_T);
			addSuperClass(_UniqueCollection_Bag_T_T, _Collection_Bag_T_T);
			addSuperClass(_UniqueCollection_Collection_T_T, _Collection_Collection_T_T);
			addSuperClass(_UniqueCollection_Map_K_T, _Collection_Map_K_T);
			addSuperClass(_UniqueCollection_OclAny_T, _Collection_OclAny_T);
			addSuperClass(_UniqueCollection_OclElement_T, _Collection_OclElement_T);
			addSuperClass(_UniqueCollection_OclSelf_T, _Collection_OclSelf_T);
			addSuperClass(_UniqueCollection_OrderedSet_flatten_T2_T, _Collection_OrderedSet_flatten_T2_T);
			addSuperClass(_UniqueCollection_OrderedSet_selectByKind_TT_T, _Collection_OrderedSet_selectByKind_TT_T);
			addSuperClass(_UniqueCollection_OrderedSet_selectByType_TT_T, _Collection_OrderedSet_selectByType_TT_T);
			addSuperClass(_UniqueCollection_OrderedSet_T_T, _Collection_OrderedSet_T_T);
			addSuperClass(_UniqueCollection_Sequence_T_T, _Collection_Sequence_T_T);
			addSuperClass(_UniqueCollection_Set_flatten_T2_T, _Collection_Set_flatten_T2_T);
			addSuperClass(_UniqueCollection_Set_selectByKind_TT_T, _Collection_Set_selectByKind_TT_T);
			addSuperClass(_UniqueCollection_Set_selectByType_TT_T, _Collection_Set_selectByType_TT_T);
			addSuperClass(_UniqueCollection_Set_T_T, _Collection_Set_T_T);
			addSuperClass(_UniqueCollection_UniqueCollection_T_T, _Collection_UniqueCollection_T_T);
			addSuperClass(_UniqueCollection_UniqueCollection_T, _Collection_UniqueCollection_T_T);
		}
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

		private void installOperationDeclarations() {
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
			op_UnlimitedNatural_oclAsType = createOperation(_UnlimitedNatural, "oclAsType", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalOclAsTypeOperation.INSTANCE, tp_UnlimitedNatural_oclAsType_TT);
			op_UnlimitedNatural_toInteger = createOperation(_UnlimitedNatural, "toInteger", "org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation", org.eclipse.ocl.pivot.library.numeric.UnlimitedNaturalToIntegerOperation.INSTANCE);
			op_Bag__lt__gt_ = createOperation(_Bag_Bag_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Bag__eq_ = createOperation(_Bag_Bag_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Bag_excluding = createOperation(_Bag_Bag_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Bag_excludingAll = createOperation(_Bag_Bag_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Bag_flatten = createOperation(_Bag_Bag_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Bag_flatten_T2);
			op_Bag_including = createOperation(_Bag_Bag_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Bag_includingAll = createOperation(_Bag_Bag_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Bag_selectByKind = createOperation(_Bag_Bag_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Bag_selectByKind_TT);
			op_Bag_selectByType = createOperation(_Bag_Bag_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Bag_selectByType_TT);
			op_BooleanType_allInstances = createOperation(_BooleanType, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Class_allInstances = createOperation(_Class, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Collection__lt__gt_ = createOperation(_Collection_Collection_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Collection__eq_ = createOperation(_Collection_Collection_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Collection_asBag = createOperation(_Collection_Collection_T, "asBag", "org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation.INSTANCE);
			op_Collection_asOrderedSet = createOperation(_Collection_Collection_T, "asOrderedSet", "org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation.INSTANCE);
			op_Collection_asSequence = createOperation(_Collection_Collection_T, "asSequence", "org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation.INSTANCE);
			op_Collection_asSet = createOperation(_Collection_Collection_T, "asSet", "org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation", org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation.INSTANCE);
			op_Collection_count = createOperation(_Collection_Collection_T, "count", "org.eclipse.ocl.pivot.library.collection.CollectionCountOperation", org.eclipse.ocl.pivot.library.collection.CollectionCountOperation.INSTANCE);
			op_Collection_excludes = createOperation(_Collection_Collection_T, "excludes", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation.INSTANCE);
			op_Collection_excludesAll = createOperation(_Collection_Collection_T, "excludesAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation.INSTANCE, tp_Collection_excludesAll_T2);
			op_Collection_excluding = createOperation(_Collection_Collection_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Collection_excludingAll = createOperation(_Collection_Collection_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Collection_flatten = createOperation(_Collection_Collection_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Collection_flatten_T2);
			op_Collection_includes = createOperation(_Collection_Collection_T, "includes", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation.INSTANCE);
			op_Collection_includesAll = createOperation(_Collection_Collection_T, "includesAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation.INSTANCE, tp_Collection_includesAll_T2);
			op_Collection_including = createOperation(_Collection_Collection_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Collection_includingAll = createOperation(_Collection_Collection_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Collection_intersection = createOperation(_Collection_Collection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_Collection_intersection_1 = createOperation(_Collection_Collection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_Collection_isEmpty = createOperation(_Collection_Collection_T, "isEmpty", "org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation.INSTANCE);
			op_Collection_max = createOperation(_Collection_Collection_T, "max", "org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation", org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation.INSTANCE);
			op_Collection_min = createOperation(_Collection_Collection_T, "min", "org.eclipse.ocl.pivot.library.collection.CollectionMinOperation", org.eclipse.ocl.pivot.library.collection.CollectionMinOperation.INSTANCE);
			op_Collection_notEmpty = createOperation(_Collection_Collection_T, "notEmpty", "org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation", org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation.INSTANCE);
			op_Collection_product = createOperation(_Collection_Collection_T, "product", "org.eclipse.ocl.pivot.library.collection.CollectionProductOperation", org.eclipse.ocl.pivot.library.collection.CollectionProductOperation.INSTANCE, tp_Collection_product_T2);
			op_Collection_selectByKind = createOperation(_Collection_Collection_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Collection_selectByKind_TT);
			op_Collection_selectByType = createOperation(_Collection_Collection_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Collection_selectByType_TT);
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
			op_Map_excludesAll = createOperation(_Map_Map_K_Map_V, "excludesAll", "org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation.INSTANCE, tp_Map_excludesAll_K2);
			op_Map_excludesMap = createOperation(_Map_Map_K_Map_V, "excludesMap", "org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation.INSTANCE, tp_Map_excludesMap_K2, tp_Map_excludesMap_V2);
			op_Map_excludesValue = createOperation(_Map_Map_K_Map_V, "excludesValue", "org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation", org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation.INSTANCE);
			op_Map_excluding = createOperation(_Map_Map_K_Map_V, "excluding", "org.eclipse.ocl.pivot.library.map.MapExcludingOperation", org.eclipse.ocl.pivot.library.map.MapExcludingOperation.INSTANCE);
			op_Map_excluding_1 = createOperation(_Map_Map_K_Map_V, "excluding", "org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation", org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation.INSTANCE);
			op_Map_excludingAll = createOperation(_Map_Map_K_Map_V, "excludingAll", "org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation", org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation.INSTANCE);
			op_Map_excludingMap = createOperation(_Map_Map_K_Map_V, "excludingMap", "org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation", org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation.INSTANCE, tp_Map_excludingMap_K2, tp_Map_excludingMap_V2);
			op_Map_includes = createOperation(_Map_Map_K_Map_V, "includes", "org.eclipse.ocl.pivot.library.map.MapIncludesOperation", org.eclipse.ocl.pivot.library.map.MapIncludesOperation.INSTANCE);
			op_Map_includes_1 = createOperation(_Map_Map_K_Map_V, "includes", "org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation.INSTANCE);
			op_Map_includesAll = createOperation(_Map_Map_K_Map_V, "includesAll", "org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation", org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation.INSTANCE, tp_Map_includesAll_K2);
			op_Map_includesMap = createOperation(_Map_Map_K_Map_V, "includesMap", "org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation.INSTANCE, tp_Map_includesMap_K2, tp_Map_includesMap_V2);
			op_Map_includesValue = createOperation(_Map_Map_K_Map_V, "includesValue", "org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation", org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation.INSTANCE);
			op_Map_including = createOperation(_Map_Map_K_Map_V, "including", "org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation", org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation.INSTANCE);
			op_Map_includingMap = createOperation(_Map_Map_K_Map_V, "includingMap", "org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation", org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation.INSTANCE, tp_Map_includingMap_K2, tp_Map_includingMap_V2);
			op_Map_isEmpty = createOperation(_Map_Map_K_Map_V, "isEmpty", "org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation", org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation.INSTANCE);
			op_Map_keys = createOperation(_Map_Map_K_Map_V, "keys", "org.eclipse.ocl.pivot.library.map.MapKeysOperation", org.eclipse.ocl.pivot.library.map.MapKeysOperation.INSTANCE);
			op_Map_notEmpty = createOperation(_Map_Map_K_Map_V, "notEmpty", "org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation", org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation.INSTANCE);
			op_Map_size = createOperation(_Map_Map_K_Map_V, "size", "org.eclipse.ocl.pivot.library.map.MapSizeOperation", org.eclipse.ocl.pivot.library.map.MapSizeOperation.INSTANCE);
			op_Map_values = createOperation(_Map_Map_K_Map_V, "values", "org.eclipse.ocl.pivot.library.map.MapValuesOperation", org.eclipse.ocl.pivot.library.map.MapValuesOperation.INSTANCE);
			op_OclAny__lt__gt_ = createOperation(_OclAny, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_OclAny__eq_ = createOperation(_OclAny, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_OclAny_oclAsSet = createOperation(_OclAny, "oclAsSet", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation.INSTANCE);
			op_OclAny_oclAsType = createOperation(_OclAny, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclAny_oclAsType_TT);
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
			op_OclElement_oclAsModelType = createOperation(_OclElement, "oclAsModelType", "org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation.INSTANCE, tp_OclElement_oclAsModelType_TT);
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
			op_OclInvalid_oclAsType = createOperation(_OclInvalid, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclInvalid_oclAsType_TT);
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
			op_OclVoid_oclAsType = createOperation(_OclVoid, "oclAsType", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation.INSTANCE, tp_OclVoid_oclAsType_TT);
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
			op_OrderedSet_flatten = createOperation(_OrderedSet_OrderedSet_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_OrderedSet_flatten_T2);
			op_OrderedSet_including = createOperation(_OrderedSet_OrderedSet_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_OrderedSet_includingAll = createOperation(_OrderedSet_OrderedSet_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_OrderedSet_insertAt = createOperation(_OrderedSet_OrderedSet_T, "insertAt", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
			op_OrderedSet_prepend = createOperation(_OrderedSet_OrderedSet_T, "prepend", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
			op_OrderedSet_prependAll = createOperation(_OrderedSet_OrderedSet_T, "prependAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
			op_OrderedSet_reverse = createOperation(_OrderedSet_OrderedSet_T, "reverse", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
			op_OrderedSet_selectByKind = createOperation(_OrderedSet_OrderedSet_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_OrderedSet_selectByKind_TT);
			op_OrderedSet_selectByType = createOperation(_OrderedSet_OrderedSet_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_OrderedSet_selectByType_TT);
			op_OrderedSet_subOrderedSet = createOperation(_OrderedSet_OrderedSet_T, "subOrderedSet", "org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation", org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation.INSTANCE);
			op_Sequence__lt__gt_ = createOperation(_Sequence_Sequence_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Sequence__eq_ = createOperation(_Sequence_Sequence_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Sequence_append = createOperation(_Sequence_Sequence_T, "append", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation.INSTANCE);
			op_Sequence_appendAll = createOperation(_Sequence_Sequence_T, "appendAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation.INSTANCE);
			op_Sequence_excluding = createOperation(_Sequence_Sequence_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Sequence_excludingAll = createOperation(_Sequence_Sequence_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Sequence_flatten = createOperation(_Sequence_Sequence_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Sequence_flatten_T2);
			op_Sequence_including = createOperation(_Sequence_Sequence_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Sequence_includingAll = createOperation(_Sequence_Sequence_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Sequence_insertAt = createOperation(_Sequence_Sequence_T, "insertAt", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation.INSTANCE);
			op_Sequence_prepend = createOperation(_Sequence_Sequence_T, "prepend", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation.INSTANCE);
			op_Sequence_prependAll = createOperation(_Sequence_Sequence_T, "prependAll", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation.INSTANCE);
			op_Sequence_reverse = createOperation(_Sequence_Sequence_T, "reverse", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation", org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation.INSTANCE);
			op_Sequence_selectByKind = createOperation(_Sequence_Sequence_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Sequence_selectByKind_TT);
			op_Sequence_selectByType = createOperation(_Sequence_Sequence_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Sequence_selectByType_TT);
			op_Sequence_subSequence = createOperation(_Sequence_Sequence_T, "subSequence", "org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation", org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation.INSTANCE);
			op_Set__neg_ = createOperation(_Set_Set_T, "-", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
			op_Set__lt__gt_ = createOperation(_Set_Set_T, "<>", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation.INSTANCE);
			op_Set__eq_ = createOperation(_Set_Set_T, "=", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation.INSTANCE);
			op_Set_excluding = createOperation(_Set_Set_T, "excluding", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation.INSTANCE);
			op_Set_excludingAll = createOperation(_Set_Set_T, "excludingAll", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation.INSTANCE);
			op_Set_flatten = createOperation(_Set_Set_T, "flatten", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation", org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation.INSTANCE, tp_Set_flatten_T2);
			op_Set_including = createOperation(_Set_Set_T, "including", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation.INSTANCE);
			op_Set_includingAll = createOperation(_Set_Set_T, "includingAll", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation", org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation.INSTANCE);
			op_Set_selectByKind = createOperation(_Set_Set_T, "selectByKind", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation.INSTANCE, tp_Set_selectByKind_TT);
			op_Set_selectByType = createOperation(_Set_Set_T, "selectByType", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation", org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation.INSTANCE, tp_Set_selectByType_TT);
			op_Stereotype_allInstances = createOperation(_Stereotype, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
			op_Type_conformsTo = createOperation(_Type, "conformsTo", "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation", org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation.INSTANCE);
			op_UniqueCollection__neg_ = createOperation(_UniqueCollection_UniqueCollection_T, "-", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation", org.eclipse.ocl.pivot.library.collection.SetMinusOperation.INSTANCE);
			op_UniqueCollection_intersection = createOperation(_UniqueCollection_UniqueCollection_T, "intersection", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation", org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation.INSTANCE);
			op_UniqueCollection_symmetricDifference = createOperation(_UniqueCollection_UniqueCollection_T, "symmetricDifference", "org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation", org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation.INSTANCE);
			op_UniqueCollection_union = createOperation(_UniqueCollection_UniqueCollection_T, "union", "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation", org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation.INSTANCE);
			op_VoidType_allInstances = createOperation(_VoidType, "allInstances", "org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation", org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation.INSTANCE);
		}

		private void installOperationBodies() {
			Operation operation;
			Parameter parameter;

			operation = op_Boolean__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[true] if the logical value of oclText[self] is the not same as the numeric value of object2, oclText[false] otherwise.");

			operation = op_Boolean__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[true] if the logical value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");

			operation = op_Boolean_allInstances;
			operation.setType(_Set_OclSelf_T);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Returns oclText[Set{false, true}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_Boolean_and;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = false then false\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then false\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = false then false\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, false);
			installComment(operation, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid] .\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[true].");

			operation = op_Boolean_and2;
			operation.setType(_Boolean);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			createBodyExpression(operation, _Boolean, "if self = false then false\n\t\t\t  elseif b = false then false\n\t\t\t  else true\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, true);
			installComment(operation, "oclText[false] if either oclText[self] or oclText[b] is oclText[false].\nOtherwise oclText[true].");

			operation = op_Boolean_implies;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = false then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then b\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, false);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");

			operation = op_Boolean_implies2;
			operation.setType(_Boolean);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			createBodyExpression(operation, _Boolean, "if self = false then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, true);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false], or if oclText[b] is oclText[true].\nOtherwise oclText[false].");

			operation = op_Boolean_not;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_UNARY);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif self = null then null\n\t\t\t  else self = false\n\t\t\t  endif", _Boolean);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false].\noclText[false] if oclText[self] is oclText[true].\noclText[null] if oclText[self] is oclText[null].\nOtherwise oclText[invalid].");

			operation = op_Boolean_not2;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_UNARY);
			createBodyExpression(operation, _Boolean, "if self then false else true endif", _Boolean);
			installComment(operation, "oclText[true] if oclText[self] is oclText[false].\nOtherwise oclText[false].");

			operation = op_Boolean_or;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then\n\t\t\t\tif b.oclIsInvalid() then self\n\t\t\t\telseif b = true then true\n\t\t\t\telse self\n\t\t\t\tendif\n\t\t\t  elseif self = true then true\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif b = true then true\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, false);
			installComment(operation, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null] if either oclText[self] or oclText[b] is oclText[null].\nOtherwise oclText[false].");

			operation = op_Boolean_or2;
			operation.setType(_Boolean);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			createBodyExpression(operation, _Boolean, "if self = true then true\n\t\t\t  elseif b = true then true\n\t\t\t  else false\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, true);
			installComment(operation, "oclText[true] if either oclText[self] or oclText[b] is oclText[true].\nOtherwise oclText[false].");

			operation = op_Boolean_toString;
			operation.setType(_String);
			installComment(operation, "Converts oclText[self] to a string value.");

			operation = op_Boolean_xor;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			operation.setPrecedence(prec_XOR);
			createBodyExpression(operation, _Boolean, "if self.oclIsInvalid() then self\n\t\t\t  elseif b.oclIsInvalid() then b\n\t\t\t  elseif self = null then null\n\t\t\t  elseif b = null then null\n\t\t\t  else self <> b\n\t\t\t  endif", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, false);
			installComment(operation, "oclText[true] if oclText[self] is oclText[true] and oclText[b] is oclText[false], or if oclText[self] is oclText[false] and oclText[b] is oclText[true].\noclText[false] if oclText[self] is oclText[true] and oclText[b] is oclText[true], or if oclText[self] is oclText[false] and oclText[b] is oclText[false].\nOtherwise oclText[invalid] if either oclText[self] or oclText[b] is oclText[invalid].\nOtherwise oclText[null].");

			operation = op_Boolean_xor2;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_XOR);
			createBodyExpression(operation, _Boolean, "self <> b", _Boolean);
			parameter = createParameter(operation, "b", _Boolean, true);
			installComment(operation, "oclText[true] if oclText[self] <> oclText[b]\nOtherwise oclText[false].");

			operation = op_Integer__mul_;
			operation.setType(_Integer);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The value of the multiplication of oclText[self] and i.");

			operation = op_Integer__add_;
			operation.setType(_Integer);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The value of the addition of oclText[self] and i.");

			operation = op_Integer__neg_;
			operation.setType(_Integer);
			operation.setPrecedence(prec_UNARY);
			installComment(operation, "The negative value of oclText[self].");

			operation = op_Integer__neg__1;
			operation.setType(_Integer);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The value of the subtraction of i from oclText[self].");

			operation = op_Integer__div_;
			operation.setType(_Real);
			operation.setIsInvalidating(true);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The value of oclText[self] divided by i.\nEvaluates to oclText[invalid] if r is equal to zero.");

			operation = op_Integer_abs;
			operation.setType(_Integer);
			installComment(operation, "The absolute value of oclText[self].");

			operation = op_Integer_div;
			operation.setType(_Integer);
			parameter = createParameter(operation, "i", _Integer, true);
			installComment(operation, "The number of times that i fits completely within oclText[self].");

			operation = op_Integer_max;
			operation.setType(_Integer);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The maximum of oclText[self] an i.");

			operation = op_Integer_min;
			operation.setType(_Integer);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The minimum of oclText[self] an i.");

			operation = op_Integer_mod;
			operation.setType(_Integer);
			parameter = createParameter(operation, "i", _Integer, true);
			installComment(operation, "The result is oclText[self] modulo i.");

			operation = op_Integer_toString;
			operation.setType(_String);
			installComment(operation, "Converts oclText[self] to a string value.");

			operation = op_Integer_toUnlimitedNatural;
			operation.setType(_UnlimitedNatural);
			_Integer.getCoercions().add(operation);
			installComment(operation, "Converts a non-negative oclText[self] to an UnlimitedNatural value. A negative oclText[self] is converted to oclText[invalid].\nAn automatic coersion may be synthesized if the coercion enables an operation reference to be resolved\nin an expression where no operation was available without coercion.");

			operation = op_Real__mul_;
			operation.setType(_Real);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The value of the multiplication of oclText[self] and r.");

			operation = op_Real__add_;
			operation.setType(_Real);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The value of the addition of oclText[self] and r.");

			operation = op_Real__neg_;
			operation.setType(_Real);
			operation.setPrecedence(prec_UNARY);
			installComment(operation, "The negative value of oclText[self].");

			operation = op_Real__neg__1;
			operation.setType(_Real);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The value of the subtraction of r from oclText[self].");

			operation = op_Real__div_;
			operation.setType(_Real);
			operation.setIsInvalidating(true);
			operation.setPrecedence(prec_MULTIPLICATIVE);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The value of oclText[self] divided by r. Evaluates to oclText[invalid] if r is equal to zero.");

			operation = op_Real__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[true] if the numeric value of oclText[self] is the not the same as the numeric value of object2, oclText[false] otherwise.");

			operation = op_Real__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[true] if the numeric value of oclText[self] is the same as the numeric value of object2, oclText[false] otherwise.");

			operation = op_Real_abs;
			operation.setType(_Real);
			installComment(operation, "The absolute value of oclText[self].");

			operation = op_Real_floor;
			operation.setType(_Integer);
			installComment(operation, "The largest integer that is less than or equal to oclText[self].");

			operation = op_Real_max;
			operation.setType(_Real);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The maximum of oclText[self] and r.");

			operation = op_Real_min;
			operation.setType(_Real);
			parameter = createParameter(operation, "r", _OclSelf, true);
			installComment(operation, "The minimum of oclText[self] and r.");

			operation = op_Real_round;
			operation.setType(_Integer);
			installComment(operation, "The integer that is closest to oclText[self]. When there are two such integers, the largest one.");

			operation = op_Real_toString;
			operation.setType(_String);
			installComment(operation, "Converts oclText[self] to a string value.");

			operation = op_String__add_;
			operation.setType(_String);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "s", _String, false);
			installComment(operation, "The concatenation of oclText[self] and s.");

			operation = op_String__lt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "s", _OclSelf, true);
			installComment(operation, "True if oclText[self] is less than s, using the locale defined by looking up oclLocale in the current environment.");

			operation = op_String__lt__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "s", _OclSelf, true);
			installComment(operation, "True if oclText[self] is less than or equal to s, using the locale defined by looking up oclLocale in the current environment.");

			operation = op_String__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);

			operation = op_String__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);

			operation = op_String__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "s", _OclSelf, true);
			installComment(operation, "True if oclText[self] is greater than s, using the locale defined by looking up oclLocale in the current environment.");

			operation = op_String__gt__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "s", _OclSelf, true);
			installComment(operation, "True if oclText[self] is greater than or equal to s, using the locale defined by looking up oclLocale in the current environment.");

			operation = op_String_at;
			operation.setType(_String);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "i", _Integer, true);
			installComment(operation, "Queries the character at position i in oclText[self].");

			operation = op_String_characters;
			operation.setType(_Sequence_String_T);
			installComment(operation, "Obtains the characters of oclText[self] as a sequence.");

			operation = op_String_compareTo;
			operation.setType(_Integer);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "The comparison of oclText[self] with oclText[that]. -ve if less than, 0 if equal, +ve if greater than.");

			operation = op_String_concat;
			operation.setType(_String);
			parameter = createParameter(operation, "s", _String, false);
			installComment(operation, "The concatenation of oclText[self] and s.");

			operation = op_String_endsWith;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "s", _String, true);
			installComment(operation, "Returns true if oclText[self] ends with the string s.\nEvery string ends with the empty string.");

			operation = op_String_equalsIgnoreCase;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "s", _String, true);
			installComment(operation, "Queries whether s and oclText[self] are equivalent under case-insensitive collation.");

			operation = op_String_indexOf;
			operation.setType(_Integer);
			parameter = createParameter(operation, "s", _String, true);
			installComment(operation, "Queries the first index in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index 1 (and also at all other indexes).");

			operation = op_String_lastIndexOf;
			operation.setType(_Integer);
			parameter = createParameter(operation, "s", _String, true);
			installComment(operation, "Queries the last in oclText[self] at which s is a substring of oclText[self], or zero if s is not a substring of oclText[self].\nThe empty string is a substring of every string at index oclText[self]-size()+1 (and also at all other indexes).");

			operation = op_String_matches;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "regex", _String, true);
			installComment(operation, "Use a regular expression match and return true if self matches regex, false otherwise.");

			operation = op_String_replaceAll;
			operation.setType(_String);
			parameter = createParameter(operation, "regex", _String, true);
			parameter = createParameter(operation, "replacement", _String, true);
			installComment(operation, "Return a string derived from self by replacing all matches of regex by replacement.");

			operation = op_String_replaceFirst;
			operation.setType(_String);
			parameter = createParameter(operation, "regex", _String, true);
			parameter = createParameter(operation, "replacement", _String, true);
			installComment(operation, "Return a string derived from self by replacing the first match of regex by replacement.");

			operation = op_String_size;
			operation.setType(_Integer);
			installComment(operation, "The number of characters in oclText[self].");

			operation = op_String_startsWith;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "s", _String, true);
			installComment(operation, "Returns true if oclText[self] starts with the string s.\nEvery string starts with the empty string.");

			operation = op_String_substituteAll;
			operation.setType(_String);
			parameter = createParameter(operation, "oldSubstring", _String, true);
			parameter = createParameter(operation, "newSubstring", _String, true);
			installComment(operation, "Return a string derived from self by replacing all occurrences of oldSubstring by newSubstring.");

			operation = op_String_substituteFirst;
			operation.setType(_String);
			parameter = createParameter(operation, "oldSubstring", _String, true);
			parameter = createParameter(operation, "newSubstring", _String, true);
			installComment(operation, "Return a string derived from self by replacing the first occurrence of oldSubstring by newSubstring.\nReturns invalid if there is no first occurrence.");

			operation = op_String_substring;
			operation.setType(_String);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "lower", _Integer, true);
			parameter = createParameter(operation, "upper", _Integer, true);
			installComment(operation, "The sub-string of oclText[self] starting at character number lower, up to and including character number upper. Character numbers run from 1 to self.size().");

			operation = op_String_toBoolean;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to a boolean value. Returns null for non-Boolean text.");

			operation = op_String_toInteger;
			operation.setType(_Integer);
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to an Integer value. Returns null for non-Integer text.");

			operation = op_String_toLower;
			operation.setType(_String);
			installComment(operation, "This is a deprecated variant of toLowerCase() preserving compatibility with traditional Eclipse OCL behaviour.");

			operation = op_String_toLowerCase;
			operation.setType(_String);
			installComment(operation, "Converts oclText[self] to lower case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");

			operation = op_String_toReal;
			operation.setType(_Real);
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to a Real[1] value. Returns null for non-Real text.");

			operation = op_String_toString;
			operation.setType(_String);
			installComment(operation, "Returns oclText[self].");

			operation = op_String_toUpper;
			operation.setType(_String);
			installComment(operation, "This is a deprecated variant of toUpperCase() preserving compatibility with traditional Eclipse OCL behaviour.");

			operation = op_String_toUpperCase;
			operation.setType(_String);
			installComment(operation, "Converts oclText[self] to upper case, using the locale defined by looking up oclLocale in the current environment.\nOtherwise, returns the same string as oclText[self].");

			operation = op_String_tokenize;
			operation.setType(_Sequence_String_T);
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by any of space, line-feed, carriage-return, form-feed and horizontal-tab delimiters.\nThe delimiters are omitted from the return.");

			operation = op_String_tokenize_1;
			operation.setType(_Sequence_String_T);
			parameter = createParameter(operation, "delimiters", _String, true);
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. The delimiters are omitted from the return.");

			operation = op_String_tokenize_2;
			operation.setType(_Sequence_String_T);
			parameter = createParameter(operation, "delimiters", _String, true);
			parameter = createParameter(operation, "returnDelimiters", _Boolean, true);
			installComment(operation, "Partition oclText[self] into a sequence substrings separated by characters in the delimiters. If returnDelimeters is\ntrue the returned sequence includes the delimiters, otherwise the delimiters are omitted.");

			operation = op_String_trim;
			operation.setType(_String);
			installComment(operation, "Return oclText[self] with leading and trailing whitespace removed.");

			operation = op_UnlimitedNatural_max;
			operation.setType(_UnlimitedNatural);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The maximum of oclText[self] an i.");

			operation = op_UnlimitedNatural_min;
			operation.setType(_UnlimitedNatural);
			parameter = createParameter(operation, "i", _OclSelf, true);
			installComment(operation, "The minimum of oclText[self] an i.");

			operation = op_UnlimitedNatural_oclAsType;
			operation.setType(tp_UnlimitedNatural_oclAsType_TT);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "type", tp_UnlimitedNatural_oclAsType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the type identified by T.\nThe type T may be any classifier defined in the UML model;\nif the actual type of oclText[self] at evaluation time does not conform to T,\nthen the oclAsType operation evaluates to oclText[invalid].\n\nThe standard behavior is redefined for UnlimitedNatural. Numeric values may be converted to\nReal or Integer, but the e[unlimited] value may not.\nConversion of e[unlimited] to Real or Integer returns oclText[invalid].");

			operation = op_UnlimitedNatural_toInteger;
			operation.setType(_Integer);
			operation.setIsRequired(false);
			installComment(operation, "Converts oclText[self] to an Integer value unless oclText[self] is e[unlimited] in which case oclText[self] is converted to oclText[null].");

			operation = op_Bag__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");

			operation = op_Bag__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if oclText[self] and bag contain the same elements, the same number of times.");

			operation = op_Bag_excluding;
			operation.setType(_Bag_Bag_T_T);
			parameter = createParameter(operation, "object", tp_Bag_T, false);
			installComment(operation, "The bag containing all elements of oclText[self] apart from all occurrences of object.");

			operation = op_Bag_excludingAll;
			operation.setType(_Bag_Bag_T_T);
			parameter = createParameter(operation, "objects", _Collection_Bag_T_T, true);
			installComment(operation, "The bag containing all elements of oclText[self] apart from all occurrences of all objects.");

			operation = op_Bag_flatten;
			operation.setType(_Bag_Bag_flatten_T2_T);
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same bag as oclText[self].\nIf the element type is a collection type, the result is the bag containing all the elements of all the recursively flattened elements of oclText[self].");

			operation = op_Bag_including;
			operation.setType(_Bag_Bag_T_T);
			parameter = createParameter(operation, "object", tp_Bag_T, false);
			installComment(operation, "The bag containing all elements of oclText[self] plus object.");

			operation = op_Bag_includingAll;
			operation.setType(_Bag_Bag_T_T);
			parameter = createParameter(operation, "objects", _Collection_Bag_T_T, true);
			installComment(operation, "The bag containing all elements of oclText[self] and objects.");

			operation = op_Bag_selectByKind;
			operation.setType(_Bag_Bag_selectByKind_TT_T);
			parameter = createParameter(operation, "type", tp_Bag_selectByKind_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The bag containing all elements of oclText[self] whose type conforms to oclText[type].");

			operation = op_Bag_selectByType;
			operation.setType(_Bag_Bag_selectByType_TT_T);
			parameter = createParameter(operation, "type", tp_Bag_selectByType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The bag containing all elements of oclText[self] whose type is oclText[type].");

			operation = op_BooleanType_allInstances;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Returns oclText[Set{false, true}].");

			operation = op_Class_allInstances;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Return a set of all instances of the type and derived types of self.");

			operation = op_Collection__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if c is not equal to oclText[self].");

			operation = op_Collection__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if c is a collection of the same kind as oclText[self] and contains the same elements in the same quantities and in the same order,\nin the case of an ordered collection type.");

			operation = op_Collection_asBag;
			operation.setType(_Bag_Collection_T_T);
			installComment(operation, "The Bag that contains all the elements from oclText[self].");

			operation = op_Collection_asOrderedSet;
			operation.setType(_OrderedSet_Collection_T_T);
			installComment(operation, "An OrderedSet that contains all the elements from oclText[self], with duplicates removed,\nin an order dependent on the particular concrete collection type.");

			operation = op_Collection_asSequence;
			operation.setType(_Sequence_Collection_T_T);
			installComment(operation, "A Sequence that contains all the elements from oclText[self], in an order dependent on the particular concrete collection type.");

			operation = op_Collection_asSet;
			operation.setType(_Set_Collection_T_T);
			installComment(operation, "The Set containing all the elements from oclText[self], with duplicates removed.");

			operation = op_Collection_count;
			operation.setType(_Integer);
			parameter = createParameter(operation, "object", tp_Collection_T, false);
			installComment(operation, "The number of times that object occurs in the collection oclText[self].");

			operation = op_Collection_excludes;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "object", tp_Collection_T, false);
			installComment(operation, "True if object is not an element of oclText[self], oclText[false] otherwise.");

			operation = op_Collection_excludesAll;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "c2", _Collection_Collection_excludesAll_T2_T, true);
			installComment(operation, "Does oclText[self] contain none of the elements of c2 ?");

			operation = op_Collection_excluding;
			operation.setType(_Collection_Collection_T_T);
			parameter = createParameter(operation, "object", tp_Collection_T, false);
			installComment(operation, "The collection containing all elements of oclText[self] apart from object.");

			operation = op_Collection_excludingAll;
			operation.setType(_Collection_Collection_T_T);
			parameter = createParameter(operation, "objects", _Collection_Collection_T_T, true);
			installComment(operation, "The collection containing all elements of oclText[self] apart from all occurrences of all objects.");

			operation = op_Collection_flatten;
			operation.setType(_Collection_Collection_flatten_T2_T);
			installComment(operation, "If the element type is not a collection type, this results in the same collection as oclText[self].\nIf the element type is a collection type, the result is a collection containing all the elements of all the recursively flattened elements of oclText[self].");

			operation = op_Collection_includes;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "object", tp_Collection_T, false);
			installComment(operation, "True if object is an element of oclText[self], oclText[false] otherwise.");

			operation = op_Collection_includesAll;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "c2", _Collection_Collection_includesAll_T2_T, true);
			installComment(operation, "Does oclText[self] contain all the elements of c2 ?");

			operation = op_Collection_including;
			operation.setType(_Collection_Collection_T_T);
			parameter = createParameter(operation, "object", tp_Collection_T, false);
			installComment(operation, "The collection containing all elements of oclText[self] plus object.");

			operation = op_Collection_includingAll;
			operation.setType(_Collection_Collection_T_T);
			parameter = createParameter(operation, "objects", _Collection_Collection_T_T, true);
			installComment(operation, "The collection containing all elements of oclText[self] and objects.");

			operation = op_Collection_intersection;
			operation.setType(_Bag_Collection_T_T);
			parameter = createParameter(operation, "c", _Collection_Collection_T_T, true);
			installComment(operation, "The intersection of oclText[self] and bag; the bag of all elements that are in both oclText[self] and c.");

			operation = op_Collection_intersection_1;
			operation.setType(_Set_Collection_T_T);
			parameter = createParameter(operation, "u", _UniqueCollection_Collection_T_T, true);
			installComment(operation, "The intersection of oclText[self] and a unique collection; the set of all elements that are in both oclText[self] and u.");

			operation = op_Collection_isEmpty;
			operation.setType(_Boolean);
			installComment(operation, "Is oclText[self] the empty collection?\n\nNote: oclText[null->isEmpty()] returns oclText[true] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");

			operation = op_Collection_max;
			operation.setType(tp_Collection_T);
			installComment(operation, "The element with the maximum value of all elements in oclText[self].\nElements must be of a type supporting the max operation.\nThe max operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");

			operation = op_Collection_min;
			operation.setType(tp_Collection_T);
			installComment(operation, "The element with the minimum value of all elements in oclText[self].\nElements must be of a type supporting the min operation.\nThe min operation - supported by the elements - must take one parameter of type T and be both associative and commutative.\nUnlimitedNatural, Integer and Real fulfill this condition.");

			operation = op_Collection_notEmpty;
			operation.setType(_Boolean);
			installComment(operation, "Is oclText[self] not the empty collection?\n\noclText[null->notEmpty()] returns oclText[false] in virtue of the implicit casting from oclText[null] to oclText[Bag{}].");

			operation = op_Collection_product;
			operation.setType(_Set_Tuple_T);
			parameter = createParameter(operation, "c2", _Collection_Collection_product_T2_T, true);
			installComment(operation, "The cartesian product operation of oclText[self] and c2.");

			operation = op_Collection_selectByKind;
			operation.setType(_Collection_Collection_selectByKind_TT_T);
			parameter = createParameter(operation, "type", tp_Collection_selectByKind_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The collection containing all elements of oclText[self] whose type conforms to oclText[type].");

			operation = op_Collection_selectByType;
			operation.setType(_Collection_Collection_selectByType_TT_T);
			parameter = createParameter(operation, "type", tp_Collection_selectByType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The collection containing all elements of oclText[self] whose type is oclText[type].");

			operation = op_Collection_size;
			operation.setType(_Integer);
			installComment(operation, "The number of elements in the collection oclText[self].");

			operation = op_Collection_sum;
			operation.setType(tp_Collection_T);
			installComment(operation, "The addition of all elements in oclText[self].\nElements must be of an oclText[OclSummable] type to provide the zero() and sum() operations.\nThe e[sum] operation must be both associative: a.sum(b).sum(c) = a.sum(b.sum(c)), and commutative: a.sum(b) = b.sum(a).\nInteger and Real fulfill this condition.\n\nIf the e[sum] operation is not both associative and commutative, the e[sum] expression is not well-formed,\nwhich may result in unpredictable results during evaluation.\nIf an implementation is able to detect a lack of associativity or commutativity,\nthe implementation may bypass the evaluation and return an oclText[invalid] result.");

			operation = op_Collection_union;
			operation.setType(_Bag_Collection_T_T);
			parameter = createParameter(operation, "c", _Collection_Collection_T_T, true);
			installComment(operation, "The bag consisting of all elements in oclText[self] and all elements in c.");

			operation = op_Enumeration_allInstances;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Return a set of all enumeration values of oclText[self].");

			operation = op_InvalidType_allInstances;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Returns oclText[invalid].");

			operation = op_Map__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");

			operation = op_Map__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");

			operation = op_Map_at;
			operation.setType(tp_Map_V);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			installComment(operation, "The value of the map at oclText[key].");

			operation = op_Map_excludes;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			installComment(operation, "True if oclText[key] is not one of the keys of oclText[self], oclText[false] otherwise.");

			operation = op_Map_excludes_1;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "True if oclText[key] and oclText[value] are not a key-value pair of oclText[self], oclText[false] otherwise.");

			operation = op_Map_excludesAll;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "coll", _Collection_Map_excludesAll_K2_T, true);
			installComment(operation, "True if none of the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");

			operation = op_Map_excludesMap;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "map", _Map_Map_excludesMap_K2_T_Map_excludesMap_V2_T, true);
			installComment(operation, "True if none of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");

			operation = op_Map_excludesValue;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "True if oclText[value] is not one of the values of oclText[self], oclText[false] otherwise.");

			operation = op_Map_excluding;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key].");

			operation = op_Map_excluding_1;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is oclText[key] and whose value is oclText[key].");

			operation = op_Map_excludingAll;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "keys", _Collection_Map_K_T, true);
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any whose key is included in oclText[keys].");

			operation = op_Map_excludingMap;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "map", _Map_Map_excludingMap_K2_T_Map_excludingMap_V2_T, true);
			installComment(operation, "The map containing all key-value pairs of oclText[self] except any which is also included in oclText[map].");

			operation = op_Map_includes;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			installComment(operation, "True if oclText[key] is one of the keys of oclText[self], oclText[false] otherwise.");

			operation = op_Map_includes_1;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "True if oclText[key] and oclText[value] are a key-value pair of oclText[self], oclText[false] otherwise.");

			operation = op_Map_includesAll;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "coll", _Collection_Map_includesAll_K2_T, true);
			installComment(operation, "True if all the elements of oclText[coll] are keys of oclText[self], oclText[false] otherwise.");

			operation = op_Map_includesMap;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "map", _Map_Map_includesMap_K2_T_Map_includesMap_V2_T, true);
			installComment(operation, "True if all of the key-value pairs of oclText[map] are also key-value pairs of oclText[self], oclText[false] otherwise.");

			operation = op_Map_includesValue;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "True if oclText[value] is one of the values of oclText[self], oclText[false] otherwise.");

			operation = op_Map_including;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "key", tp_Map_K, false);
			parameter = createParameter(operation, "value", tp_Map_V, false);
			installComment(operation, "The map containing all of the key-value pairs of oclText[self] and an additional key-value pair for oclText[key] and oclText[value].\nIf oclText[key] is already a key of oclText[self], the old value pair is replaced by oclText[value].");

			operation = op_Map_includingMap;
			operation.setType(_Map_Map_K_T_Map_V_T);
			parameter = createParameter(operation, "map", _Map_Map_includingMap_K2_T_Map_includingMap_V2_T, true);
			installComment(operation, "The map containing all of the key-value pairs of oclText[self] and oclText[map].\nThe values associated with key-value pairs in oclText[map] replace those in oclText[self] where the same key is used by both maps.");

			operation = op_Map_isEmpty;
			operation.setType(_Boolean);
			installComment(operation, "True if oclText[self] is the empty map, oclText[false] otherwise.");

			operation = op_Map_keys;
			operation.setType(_Set_Map_K_T);
			installComment(operation, "A Set comprising all the keys of the key-value pairs in oclText[self].");

			operation = op_Map_notEmpty;
			operation.setType(_Boolean);
			installComment(operation, "True if oclText[self] not the empty map, oclText[false] otherwise.");

			operation = op_Map_size;
			operation.setType(_Integer);
			installComment(operation, "The number of key-value pairs in oclText[self].");

			operation = op_Map_values;
			operation.setType(_Bag_Map_V_T);
			installComment(operation, "The Bag comprising all the values of the key-value pairs in oclText[self].");

			operation = op_OclAny__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if oclText[self] is a different object from object2. Infix operator.");

			operation = op_OclAny__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if oclText[self] is the same object as object2. Infix operator.");

			operation = op_OclAny_oclAsSet;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Returns a Set with oclText[self] as the sole content, unless oclText[self] is oclText[null] in which case returns an empty set,");

			operation = op_OclAny_oclAsType;
			operation.setType(tp_OclAny_oclAsType_TT);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "type", tp_OclAny_oclAsType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the type identified by oclText[TT].\nThe type oclText[TT] may be any classifier defined by OCL or a user metamodel;\nif the actual type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsType call is not well-formed if\nthe classification is ambiguous.\n\nIn the case of feature redefinition, casting an object to a supertype of its actual type\ndoes not access the supertype\u2019s definition of the feature;\naccording to the semantics of redefinition, the redefined feature simply does not exist for the object.\nHowever, when casting to a supertype, any features additionally defined by the subtype are suppressed.\n\nFIXME Bug 578060 return should be optional to support OclVoid returning null.");

			operation = op_OclAny_oclIsInState;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "statespec", _OclState, false);
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is in the state identified by statespec.");

			operation = op_OclAny_oclIsInvalid;
			operation.setType(_Boolean);
			operation.setIsValidating(true);
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is equal to OclInvalid.");

			operation = op_OclAny_oclIsKindOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);
			installComment(operation, "Evaluates to oclText[true] if the type of oclText[self] conforms to oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].");

			operation = op_OclAny_oclIsNew;
			operation.setType(_Boolean);
			installComment(operation, "Can only be used in a postcondition.\nEvaluates to oclText[true] if the oclText[self] is created during performing the operation (for instance, it didn\u2019t exist at precondition time).");

			operation = op_OclAny_oclIsTypeOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);
			installComment(operation, "Evaluates to oclText[true] if oclText[self] is of the type oclText[type] but not a subtype of oclText[type].");

			operation = op_OclAny_oclIsUndefined;
			operation.setType(_Boolean);
			operation.setIsValidating(true);
			installComment(operation, "Evaluates to oclText[true] if the oclText[self] is equal to oclText[invalid] or equal to oclText[null].");

			operation = op_OclAny_oclLog;
			operation.setType(_OclSelf);
			installComment(operation, "Evaluates to the self, with the side effect of generating a log message comprising self.");

			operation = op_OclAny_oclLog_1;
			operation.setType(_OclSelf);
			parameter = createParameter(operation, "message", _String, true);
			installComment(operation, "Evaluates to the self, with the side effect of generating a log message comprising message followed by self.");

			operation = op_OclAny_oclType;
			operation.setType(_OclSelf);
			operation.setIsTypeof(true);
			installComment(operation, "Evaluates to the most derived type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified type, the return is the most derived type of the current classification which is established when the instance is\npassed to OCL, or re-established by an oclText[oclAsType()] call.");

			operation = op_OclAny_oclTypes;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Evaluates to all of the most derived type of which oclText[self] is an instance. The return from oclText[oclTypes()]\nis normally equivalent to that from oclText[oclType()] unless oclText[self] is an instance of multiply classified type.");

			operation = op_OclAny_toString;
			operation.setType(_String);
			installComment(operation, "Returns a string representation of oclText[self].");

			operation = op_OclComparable__lt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "True if oclText[self] is less than oclText[that].");

			operation = op_OclComparable__lt__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "True if oclText[self] is less than or equal to oclText[that].");

			operation = op_OclComparable__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "True if oclText[self] is greater than oclText[that].");

			operation = op_OclComparable__gt__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_RELATIONAL);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "True if oclText[self] is greater than or equal to oclText[that].");

			operation = op_OclComparable_compareTo;
			operation.setType(_Integer);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "Return -ve, 0, +ve according to whether self is less than, equal to , or greater than that.\n\nThe compareTo operation should be commutative.");

			operation = op_OclElement_allInstances;
			operation.setType(_Set_OclSelf_T);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Return a set of all instances of the type and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_OclElement_oclAsModelType;
			operation.setType(tp_OclElement_oclAsModelType_TT);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "type", tp_OclElement_oclAsModelType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "Evaluates to oclText[self], where oclText[self] is of the model type identified by oclText[TT].\n\nMost model elements have metamodel types for use with oclAsType, but no model type and so the return is oclText[invalid].\n\nModel elements such as UML\'s InstnaceSpecification that do support distinct model and metamodel types return oclText[self]\nwith the cast type oclText[TT] that may be used for further navigation.\nIf the actual model type of oclText[self] at evaluation time does not conform to oclText[TT],\nthen the oclAsType operation evaluates to oclText[invalid].\n\nIf oclText[self] is a multiply classified instance, the current classification used for OCL navigation\nis changed to the classification to which oclText[TT] conforms. The oclAsModelType call is not well-formed if\nthe classification is ambiguous.");

			operation = op_OclElement_oclBase;
			operation.setType(_OclType);
			operation.setIsRequired(false);
			installComment(operation, "Returns the application class that is extended by this extension element. Returns null for an orphan extension of nothing.");

			operation = op_OclElement_oclBase_1;
			operation.setType(_OclType);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "base", _OclType, true);
			installComment(operation, "Returns the application class conforming to base extended by this extension element. Returns null if no such class.");

			operation = op_OclElement_oclContainer;
			operation.setType(_OclElement);
			operation.setIsRequired(false);
			installComment(operation, "Returns the object for which self is a composed content or null if there is no such object.");

			operation = op_OclElement_oclContents;
			operation.setType(_Set_OclElement_T);
			installComment(operation, "Returns the composed contents of self.");

			operation = op_OclElement_oclExtension;
			operation.setType(_OclElement);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "stereotype", _OclStereotype, true);
			installComment(operation, "Returns the application instance of the Stereotype that conforms to stereotype applied to this element. Returns invalid if more than one.");

			operation = op_OclElement_oclExtensions;
			operation.setType(_Set_OclElement_T);
			installComment(operation, "Returns the application instances of all Stereotypes applied to this element.");

			operation = op_OclElement_oclExtensions_1;
			operation.setType(_Set_OclElement_T);
			parameter = createParameter(operation, "stereotype", _OclStereotype, true);
			installComment(operation, "Returns the application instances of the Stereotypes that conform to stereotype applied to this element.");

			operation = op_OclElement_oclIsModelKindOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);
			installComment(operation, "Evaluates to oclText[true] if the type of oclText[self] conforms to the model type oclText[type].\nThat is, oclText[self] is of type oclText[type] or a subtype of oclText[type].\n\nThe return is normally oclText[false] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");

			operation = op_OclElement_oclModelType;
			operation.setType(_OclSelf);
			operation.setIsTypeof(true);
			installComment(operation, "Evaluates to the most derived model type of which oclText[self] is currently an instance. If oclText[self] is an instance of a multiply\nclassified model type, the return is the most derived type of the current classification which is established\nby an oclText[oclAsModelType()] call.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");

			operation = op_OclElement_oclModelTypes;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Evaluates to all of the most derived model types of which oclText[self] is an instance. The return from oclText[oclModelTypes()]\nis normally equivalent to that from oclText[oclModelType()] unless oclText[self] is an instance of multiply classified model type.\n\nThe return is normally oclText[invalid] since few model elements have model types. UML\'s InstanceSpecification::classifier provides\na multiple classification for a model type.");

			operation = op_OclEnumeration_allInstances;
			operation.setType(_Set_OclSelf_T);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Return a set of all enumeration values of oclText[self].\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_OclInvalid__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[invalid].");

			operation = op_OclInvalid__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Returns oclText[invalid].");

			operation = op_OclInvalid_allInstances;
			operation.setType(_Set_OclSelf_T);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Returns oclText[invalid].\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_OclInvalid_and;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclInvalid_implies;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclInvalid_oclAsSet;
			operation.setType(_Set_OclSelf_T);

			operation = op_OclInvalid_oclAsType;
			operation.setType(tp_OclInvalid_oclAsType_TT);
			parameter = createParameter(operation, "type", tp_OclInvalid_oclAsType_TT, true);
			parameter.setIsTypeof(true);

			operation = op_OclInvalid_oclBadOperation;
			operation.setType(_OclAny);
			operation.setIsRequired(false);
			installComment(operation, "An oclBadOperation may be used as a placeholder in an unsuccessfully created OCLExpression.");

			operation = op_OclInvalid_oclIsInvalid;
			operation.setType(_Boolean);
			operation.setIsValidating(true);

			operation = op_OclInvalid_oclIsKindOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);

			operation = op_OclInvalid_oclIsTypeOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);

			operation = op_OclInvalid_oclIsUndefined;
			operation.setType(_Boolean);
			operation.setIsValidating(true);

			operation = op_OclInvalid_oclType;
			operation.setType(_OclSelf);
			operation.setIsTypeof(true);

			operation = op_OclInvalid_or;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclInvalid_toString;
			operation.setType(_String);
			installComment(operation, "Returns \'invalid\'.");

			operation = op_OclMessage_hasReturned;
			operation.setType(_Boolean);
			installComment(operation, "True if type of template parameter is an operation call, and the called operation has returned a value.\nThis implies the fact that the message has been sent. False in all other cases.");

			operation = op_OclMessage_isOperationCall;
			operation.setType(_Boolean);
			installComment(operation, "Returns oclText[true] if the OclMessage represents the sending of a UML Operation call.");

			operation = op_OclMessage_isSignalSent;
			operation.setType(_Boolean);
			installComment(operation, "Returns oclText[true] if the OclMessage represents the sending of a UML Signal.");

			operation = op_OclMessage_result;
			operation.setType(_OclAny);
			operation.setIsRequired(false);
			installComment(operation, "Returns the result of the called operation, if type of template parameter is an operation call,\nand the called operation has returned a value. Otherwise the oclText[invalid] value is returned.");

			operation = op_OclStereotype_allInstances;
			operation.setType(_Set_OclSelf_T);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Return a set of all instances of the stereotype and derived types of self.\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_OclSummable_sum;
			operation.setType(_OclSelf);
			parameter = createParameter(operation, "that", _OclSelf, true);
			installComment(operation, "Return the sum of self and that.\n\nThe sum operation should be associative.");

			operation = op_OclSummable_zero;
			operation.setType(_OclSelf);
			installComment(operation, "Return the \'zero\' value of self to initialize a summation.\n\nzero().sum(self) = self.");

			operation = op_OclTuple__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);

			operation = op_OclTuple__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);

			operation = op_OclType_conformsTo;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type2", _OclType, false);
			installComment(operation, "Returns true if type2 conforms to self.");

			operation = op_OclVoid__add_;
			operation.setType(_String);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "s", _String, false);
			installComment(operation, "The concatenation of oclText[null] and s.");

			operation = op_OclVoid__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);

			operation = op_OclVoid__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Redefines the OclAny operation, returning oclText[true] if object is oclText[null], oclText[invalid]\nif object is oclText[invalid], oclText[false] otherwise.");

			operation = op_OclVoid_allInstances;
			operation.setType(_Set_OclSelf_F);
			parameter = createParameter(operation, "dummy", _Integer, true);
			installComment(operation, "Returns oclText[Set{null}].\n\n@Deprecated - retained with broken signature for referential API compatibility.");

			operation = op_OclVoid_and;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_AND);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclVoid_concat;
			operation.setType(_String);
			parameter = createParameter(operation, "s", _String, false);
			installComment(operation, "The concatenation of oclText[null] and s.");

			operation = op_OclVoid_implies;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_IMPLIES);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclVoid_not;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_UNARY);

			operation = op_OclVoid_oclAsSet;
			operation.setType(_Set_OclSelf_T);

			operation = op_OclVoid_oclAsType;
			operation.setType(tp_OclVoid_oclAsType_TT);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "type", tp_OclVoid_oclAsType_TT, true);
			parameter.setIsTypeof(true);

			operation = op_OclVoid_oclIsInvalid;
			operation.setType(_Boolean);
			operation.setIsValidating(true);

			operation = op_OclVoid_oclIsKindOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);

			operation = op_OclVoid_oclIsTypeOf;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type", _OclType, true);

			operation = op_OclVoid_oclIsUndefined;
			operation.setType(_Boolean);
			operation.setIsValidating(true);

			operation = op_OclVoid_oclType;
			operation.setType(_OclSelf);
			operation.setIsTypeof(true);

			operation = op_OclVoid_oclTypes;
			operation.setType(_Set_OclSelf_T);

			operation = op_OclVoid_or;
			operation.setType(_Boolean);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			operation.setIsValidating(true);
			operation.setPrecedence(prec_OR);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OclVoid_toString;
			operation.setType(_String);
			installComment(operation, "Returns oclText[null].");

			operation = op_OclVoid_xor;
			operation.setType(_Boolean);
			operation.setIsRequired(false);
			operation.setPrecedence(prec_XOR);
			parameter = createParameter(operation, "b", _Boolean, false);

			operation = op_OrderedCollection_at;
			operation.setType(tp_OrderedCollection_T);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "index", _Integer, true);
			installComment(operation, "The i-th element of ordered collection.");

			operation = op_OrderedCollection_first;
			operation.setType(tp_OrderedCollection_T);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			installComment(operation, "The first element in oclText[self].");

			operation = op_OrderedCollection_indexOf;
			operation.setType(_Integer);
			operation.setIsRequired(false);
			parameter = createParameter(operation, "obj", tp_OrderedCollection_T, false);
			installComment(operation, "The index of object obj in the ordered collection. Returns null for an out of bound index.");

			operation = op_OrderedCollection_last;
			operation.setType(tp_OrderedCollection_T);
			operation.setIsInvalidating(true);
			operation.setIsRequired(false);
			installComment(operation, "The last element in oclText[self].");

			operation = op_OrderedSet__neg_;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "s", _UniqueCollection_OclAny_T, true);
			installComment(operation, "The elements of oclText[self], which are not in s.");

			operation = op_OrderedSet__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");

			operation = op_OrderedSet__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");

			operation = op_OrderedSet_append;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "object", tp_OrderedSet_T, false);
			installComment(operation, "The set of elements, consisting of all elements of oclText[self], followed by object.");

			operation = op_OrderedSet_appendAll;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "objects", _OrderedCollection_OrderedSet_T_T, true);
			installComment(operation, "The set of elements, consisting of all elements of oclText[self], followed by objects.");

			operation = op_OrderedSet_excluding;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "object", tp_OrderedSet_T, false);
			installComment(operation, "The ordered set  containing all elements of oclText[self] apart from object.\n\nThe order of the remaining elements is not changed.");

			operation = op_OrderedSet_excludingAll;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "objects", _Collection_OrderedSet_T_T, true);
			installComment(operation, "The ordered set containing all elements of oclText[self] apart from all occurrences of all objects.");

			operation = op_OrderedSet_flatten;
			operation.setType(_OrderedSet_OrderedSet_flatten_T2_T);

			operation = op_OrderedSet_including;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "object", tp_OrderedSet_T, false);
			installComment(operation, "The ordered set containing all elements of oclText[self] plus object added as the last element if not already present.");

			operation = op_OrderedSet_includingAll;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "objects", _Collection_OrderedSet_T_T, true);
			installComment(operation, "The ordered set containing all elements of oclText[self] plus objects added as the last elements.");

			operation = op_OrderedSet_insertAt;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "index", _Integer, true);
			parameter = createParameter(operation, "object", tp_OrderedSet_T, false);
			installComment(operation, "The ordered set consisting of oclText[self] with object present at position index.");

			operation = op_OrderedSet_prepend;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "object", tp_OrderedSet_T, false);
			installComment(operation, "The sequence consisting of object, followed by all elements in oclText[self].");

			operation = op_OrderedSet_prependAll;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createParameter(operation, "objects", _OrderedCollection_OrderedSet_T_T, true);
			installComment(operation, "The sequence consisting of objects, followed by all elements in oclText[self].");

			operation = op_OrderedSet_reverse;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			installComment(operation, "The ordered set of elements with same elements but with the opposite order.");

			operation = op_OrderedSet_selectByKind;
			operation.setType(_OrderedSet_OrderedSet_selectByKind_TT_T);
			parameter = createParameter(operation, "type", tp_OrderedSet_selectByKind_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The ordered set containing all elements of oclText[self] whose type conforms to oclText[type].");

			operation = op_OrderedSet_selectByType;
			operation.setType(_OrderedSet_OrderedSet_selectByType_TT_T);
			parameter = createParameter(operation, "type", tp_OrderedSet_selectByType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The ordered set containing all elements of oclText[self] whose type is oclText[type].");

			operation = op_OrderedSet_subOrderedSet;
			operation.setType(_OrderedSet_OrderedSet_T_T);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "lower", _Integer, true);
			parameter = createParameter(operation, "upper", _Integer, true);
			installComment(operation, "The sub-set of oclText[self] starting at number lower, up to and including element number upper.");

			operation = op_Sequence__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");

			operation = op_Sequence__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "True if oclText[self] contains the same elements as s in the same order.");

			operation = op_Sequence_append;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "object", tp_Sequence_T, false);
			installComment(operation, "The sequence of elements, consisting of all elements of oclText[self], followed by object.");

			operation = op_Sequence_appendAll;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "objects", _OrderedCollection_Sequence_T_T, true);
			installComment(operation, "The sequence of elements, consisting of all elements of oclText[self], followed by objects.");

			operation = op_Sequence_excluding;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "object", tp_Sequence_T, false);
			installComment(operation, "The sequence containing all elements of oclText[self] apart from all occurrences of object.\n\nThe order of the remaining elements is not changed.");

			operation = op_Sequence_excludingAll;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "objects", _Collection_Sequence_T_T, true);
			installComment(operation, "The sequence containing all elements of oclText[self] apart from all occurrences of all objects.");

			operation = op_Sequence_flatten;
			operation.setType(_Sequence_Sequence_flatten_T2_T);
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same sequence as oclText[self].\nIf the element type is a collection type, the result is the sequence containing all the elements\nof all the recursively flattened elements of oclText[self]. The order of the elements is partial.");

			operation = op_Sequence_including;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "object", tp_Sequence_T, false);
			installComment(operation, "The sequence containing all elements of oclText[self] plus object added as the last element.");

			operation = op_Sequence_includingAll;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "objects", _Collection_Sequence_T_T, true);
			installComment(operation, "The sequence containing all elements of oclText[self] plus objects added as the last elements.");

			operation = op_Sequence_insertAt;
			operation.setType(_Sequence_Sequence_T_T);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "index", _Integer, true);
			parameter = createParameter(operation, "object", tp_Sequence_T, false);
			installComment(operation, "The sequence consisting of oclText[self] with object inserted at position index.");

			operation = op_Sequence_prepend;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "object", tp_Sequence_T, false);
			installComment(operation, "The sequence consisting of object, followed by all elements in oclText[self].");

			operation = op_Sequence_prependAll;
			operation.setType(_Sequence_Sequence_T_T);
			parameter = createParameter(operation, "objects", _OrderedCollection_Sequence_T_T, true);
			installComment(operation, "The sequence consisting of objects, followed by all elements in oclText[self].");

			operation = op_Sequence_reverse;
			operation.setType(_Sequence_Sequence_T_T);
			installComment(operation, "The sequence containing the same elements but with the opposite order.");

			operation = op_Sequence_selectByKind;
			operation.setType(_Sequence_Sequence_selectByKind_TT_T);
			parameter = createParameter(operation, "type", tp_Sequence_selectByKind_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The sequence containing all elements of oclText[self] whose type conforms to oclText[type].");

			operation = op_Sequence_selectByType;
			operation.setType(_Sequence_Sequence_selectByType_TT_T);
			parameter = createParameter(operation, "type", tp_Sequence_selectByType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The sequence containing all elements of oclText[self] whose type is oclText[type].");

			operation = op_Sequence_subSequence;
			operation.setType(_Sequence_Sequence_T_T);
			operation.setIsInvalidating(true);
			parameter = createParameter(operation, "lower", _Integer, true);
			parameter = createParameter(operation, "upper", _Integer, true);
			installComment(operation, "The sub-sequence of oclText[self] starting at number lower, up to and including element number upper.");

			operation = op_Set__neg_;
			operation.setType(_Set_Set_T_T);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "s", _UniqueCollection_OclAny_T, true);
			installComment(operation, "The elements of oclText[self], which are not in s.");

			operation = op_Set__lt__gt_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] unless oclText[self] and s contain the same elements.");

			operation = op_Set__eq_;
			operation.setType(_Boolean);
			operation.setPrecedence(prec_EQUALITY);
			parameter = createParameter(operation, "object2", _OclSelf, false);
			installComment(operation, "Evaluates to oclText[true] if oclText[self] and s contain the same elements.");

			operation = op_Set_excluding;
			operation.setType(_Set_Set_T_T);
			parameter = createParameter(operation, "object", tp_Set_T, false);
			installComment(operation, "The set containing all elements of oclText[self] without object.");

			operation = op_Set_excludingAll;
			operation.setType(_Set_Set_T_T);
			parameter = createParameter(operation, "objects", _Collection_Set_T_T, true);
			installComment(operation, "The set containing all elements of oclText[self] apart from all occurrences of all objects.");

			operation = op_Set_flatten;
			operation.setType(_Set_Set_flatten_T2_T);
			installComment(operation, "Redefines the Collection operation. If the element type is not a collection type, this results in the same set as oclText[self].\nIf the element type is a collection type, the result is the set containing all the elements of all the recursively flattened elements of oclText[self].");

			operation = op_Set_including;
			operation.setType(_Set_Set_T_T);
			parameter = createParameter(operation, "object", tp_Set_T, false);
			installComment(operation, "The set containing all elements of oclText[self] plus object.");

			operation = op_Set_includingAll;
			operation.setType(_Set_Set_T_T);
			parameter = createParameter(operation, "objects", _Collection_Set_T_T, true);
			installComment(operation, "The set containing all elements of oclText[self] and objects.");

			operation = op_Set_selectByKind;
			operation.setType(_Set_Set_selectByKind_TT_T);
			parameter = createParameter(operation, "type", tp_Set_selectByKind_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The set containing all elements of oclText[self] whose type conforms to oclText[type].");

			operation = op_Set_selectByType;
			operation.setType(_Set_Set_selectByType_TT_T);
			parameter = createParameter(operation, "type", tp_Set_selectByType_TT, true);
			parameter.setIsTypeof(true);
			installComment(operation, "The set containing all elements of oclText[self] whose type is oclText[type].");

			operation = op_Stereotype_allInstances;
			operation.setType(_Set_OclSelf_T);
			installComment(operation, "Return a set of all instances of the stereotype and derived types of self.");

			operation = op_Type_conformsTo;
			operation.setType(_Boolean);
			parameter = createParameter(operation, "type2", _Type, false);
			installComment(operation, "Returns true if type2 conforms to self.");

			operation = op_UniqueCollection__neg_;
			operation.setType(_UniqueCollection_UniqueCollection_T_T);
			operation.setPrecedence(prec_ADDITIVE);
			parameter = createParameter(operation, "s", _UniqueCollection_OclAny_T, true);
			installComment(operation, "The elements of oclText[self], which are not in s.");

			operation = op_UniqueCollection_intersection;
			operation.setType(_Set_UniqueCollection_T_T);
			parameter = createParameter(operation, "c", _Collection_UniqueCollection_T_T, true);
			installComment(operation, "The intersection of oclText[self] and c (i.e., the set of all elements that are in both oclText[self] and c).");

			operation = op_UniqueCollection_symmetricDifference;
			operation.setType(_Set_UniqueCollection_T_T);
			parameter = createParameter(operation, "s", _UniqueCollection_OclAny_T, true);
			installComment(operation, "The set containing all the elements that are in oclText[self] or s, but not in both.");

			operation = op_UniqueCollection_union;
			operation.setType(_Set_UniqueCollection_T_T);
			parameter = createParameter(operation, "s", _UniqueCollection_UniqueCollection_T_T, true);
			installComment(operation, "The set consisting of all elements in oclText[self] and all elements in s.");

			operation = op_VoidType_allInstances;
			operation.setType(_Set_OclSelf_F);
			installComment(operation, "Returns oclText[Set{null}].");
		}

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

		private void installIterationDeclarations() {
			it_Bag_closure = createIteration(_Bag_Bag_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Bag_collectNested = createIteration(_Bag_Bag_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Bag_collectNested_V);
			it_Bag_collect = createIteration(_Bag_Bag_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Bag_collect_V);
			it_Bag_reject = createIteration(_Bag_Bag_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Bag_select = createIteration(_Bag_Bag_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Bag_sortedBy = createIteration(_Bag_Bag_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Collection_any = createIteration(_Collection_Collection_T, "any", "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
			it_Collection_collectBy = createIteration(_Collection_Collection_T, "collectBy", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE, tp_Collection_collectBy_V);
			it_Collection_collectNested = createIteration(_Collection_Collection_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Collection_collectNested_V);
			it_Collection_collect = createIteration(_Collection_Collection_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Collection_collect_V);
			it_Collection_exists = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_exists_1 = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_exists_2 = createIteration(_Collection_Collection_T, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Collection_forAll = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_forAll_1 = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_forAll_2 = createIteration(_Collection_Collection_T, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Collection_isUnique = createIteration(_Collection_Collection_T, "isUnique", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
			it_Collection_iterate = createIteration(_Collection_Collection_T, "iterate", "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE, tp_Collection_iterate_Tacc);
			it_Collection_one = createIteration(_Collection_Collection_T, "one", "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
			it_Collection_reject = createIteration(_Collection_Collection_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Collection_select = createIteration(_Collection_Collection_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Collection_sortedBy = createIteration(_Collection_Collection_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Map_any = createIteration(_Map_Map_K_Map_V, "any", "org.eclipse.ocl.pivot.library.iterator.AnyIteration", org.eclipse.ocl.pivot.library.iterator.AnyIteration.INSTANCE);
			it_Map_collectBy = createIteration(_Map_Map_K_Map_V, "collectBy", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration", org.eclipse.ocl.pivot.library.iterator.CollectByIteration.INSTANCE, tp_Map_collectBy_V2);
			it_Map_collectNested = createIteration(_Map_Map_K_Map_V, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Map_collectNested_V2);
			it_Map_collect = createIteration(_Map_Map_K_Map_V, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Map_collect_V2);
			it_Map_exists = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_exists_1 = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_exists_2 = createIteration(_Map_Map_K_Map_V, "exists", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration", org.eclipse.ocl.pivot.library.iterator.ExistsIteration.INSTANCE);
			it_Map_forAll = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_forAll_1 = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_forAll_2 = createIteration(_Map_Map_K_Map_V, "forAll", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration", org.eclipse.ocl.pivot.library.iterator.ForAllIteration.INSTANCE);
			it_Map_isUnique = createIteration(_Map_Map_K_Map_V, "isUnique", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration", org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration.INSTANCE);
			it_Map_iterate = createIteration(_Map_Map_K_Map_V, "iterate", "org.eclipse.ocl.pivot.library.iterator.IterateIteration", org.eclipse.ocl.pivot.library.iterator.IterateIteration.INSTANCE, tp_Map_iterate_Tacc);
			it_Map_one = createIteration(_Map_Map_K_Map_V, "one", "org.eclipse.ocl.pivot.library.iterator.OneIteration", org.eclipse.ocl.pivot.library.iterator.OneIteration.INSTANCE);
			it_Map_reject = createIteration(_Map_Map_K_Map_V, "reject", "org.eclipse.ocl.pivot.library.iterator.MapRejectIteration", org.eclipse.ocl.pivot.library.iterator.MapRejectIteration.INSTANCE);
			it_Map_select = createIteration(_Map_Map_K_Map_V, "select", "org.eclipse.ocl.pivot.library.iterator.MapSelectIteration", org.eclipse.ocl.pivot.library.iterator.MapSelectIteration.INSTANCE);
			it_OrderedSet_closure = createIteration(_OrderedSet_OrderedSet_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_OrderedSet_collectNested = createIteration(_OrderedSet_OrderedSet_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_OrderedSet_collectNested_V);
			it_OrderedSet_collect = createIteration(_OrderedSet_OrderedSet_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_OrderedSet_collect_V);
			it_OrderedSet_reject = createIteration(_OrderedSet_OrderedSet_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_OrderedSet_select = createIteration(_OrderedSet_OrderedSet_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_OrderedSet_sortedBy = createIteration(_OrderedSet_OrderedSet_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Sequence_closure = createIteration(_Sequence_Sequence_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Sequence_collectNested = createIteration(_Sequence_Sequence_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Sequence_collectNested_V);
			it_Sequence_collect = createIteration(_Sequence_Sequence_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Sequence_collect_V);
			it_Sequence_reject = createIteration(_Sequence_Sequence_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Sequence_select = createIteration(_Sequence_Sequence_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Sequence_sortedBy = createIteration(_Sequence_Sequence_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_Set_closure = createIteration(_Set_Set_T, "closure", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration", org.eclipse.ocl.pivot.library.iterator.ClosureIteration.INSTANCE);
			it_Set_collectNested = createIteration(_Set_Set_T, "collectNested", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration", org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration.INSTANCE, tp_Set_collectNested_V);
			it_Set_collect = createIteration(_Set_Set_T, "collect", "org.eclipse.ocl.pivot.library.iterator.CollectIteration", org.eclipse.ocl.pivot.library.iterator.CollectIteration.INSTANCE, tp_Set_collect_V);
			it_Set_reject = createIteration(_Set_Set_T, "reject", "org.eclipse.ocl.pivot.library.iterator.RejectIteration", org.eclipse.ocl.pivot.library.iterator.RejectIteration.INSTANCE);
			it_Set_select = createIteration(_Set_Set_T, "select", "org.eclipse.ocl.pivot.library.iterator.SelectIteration", org.eclipse.ocl.pivot.library.iterator.SelectIteration.INSTANCE);
			it_Set_sortedBy = createIteration(_Set_Set_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
			it_UniqueCollection_sortedBy = createIteration(_UniqueCollection_UniqueCollection_T, "sortedBy", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration", org.eclipse.ocl.pivot.library.iterator.SortedByIteration.INSTANCE);
		}

		private void installIterationBodies() {
			Iteration iteration;
			Parameter parameter;

			iteration = it_Bag_closure;
			iteration.setType(_Set_Bag_T_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, true);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_Set, false);
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");

			iteration = it_Bag_collectNested;
			iteration.setType(_Bag_Bag_collectNested_V_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_Bag_collectNested_V, false);
			installComment(iteration, "The Bag of elements which results from applying body to every member of the source nonordered collection.");

			iteration = it_Bag_collect;
			iteration.setType(_Bag_Bag_collect_V_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_Bag_collect_V, false);

			iteration = it_Bag_reject;
			iteration.setType(_Bag_Bag_T_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_Boolean, true);
			installComment(iteration, "The sub-bag of the source bag for which body is oclText[false].\n\noclCode[self->reject(iterator | body) = self->select(iterator | not body)].");

			iteration = it_Bag_select;
			iteration.setType(_Bag_Bag_T_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_Boolean, true);
			installComment(iteration, "The sub-bag of the source bag for which body is oclText[true].\n\noclCode[self->select(iterator | body) =\nself->iterate(iterator; result : Bag(T) = Bag{} |\nif body then result->including(iterator)\nelse result\nendif)]");

			iteration = it_Bag_sortedBy;
			iteration.setType(_Sequence_Bag_T_T);
			parameter = createIterator(iteration, "i", tp_Bag_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Bag_T_OclAny, false);
			installComment(iteration, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			iteration = it_Collection_any;
			iteration.setType(tp_Collection_T);
			iteration.setIsRequired(false);
			parameter = createIterator(iteration, "i", tp_Collection_T, true);
			parameter = createParameter(iteration, "body", _Lambda_Collection_T_Boolean, true);
			installComment(iteration, "Returns any element in the e[source] null-free collection for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any element,\notherwise if there are one or more elements for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the result is oclText[null].\n\nlet source : Collection(T) = ..., body : Lambda T() : Boolean = ... in\nsource->any(iterator | body) = source->select(iterator | body)->asSequence()->first()");

			iteration = it_Collection_collectBy;
			iteration.setType(_Map_Collection_T_F_Collection_collectBy_V_F);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Collection_collectBy_V, false);
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");

			iteration = it_Collection_collectNested;
			iteration.setType(_Collection_Collection_collectNested_V_T);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Collection_collectNested_V, false);
			installComment(iteration, "The Collection of elements which results from applying body to every member of the source collection.");

			iteration = it_Collection_collect;
			iteration.setType(_Collection_Collection_collect_V_T);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Collection_collect_V, false);
			installComment(iteration, "The Collection of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");

			iteration = it_Collection_exists;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createIterator(iteration, "j", tp_Collection_T, false);
			parameter = createIterator(iteration, "k", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j], oclText[k] in the source collection.");

			iteration = it_Collection_exists_1;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createIterator(iteration, "j", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of elements oclText[i], oclText[j] in the source collection.");

			iteration = it_Collection_exists_2;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one element oclText[i] in the source collection.");

			iteration = it_Collection_forAll;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createIterator(iteration, "j", tp_Collection_T, false);
			parameter = createIterator(iteration, "k", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j] in the source collection; otherwise, result is oclText[false].");

			iteration = it_Collection_forAll_1;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createIterator(iteration, "j", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of elements oclText[i], oclText[j], oclText[k] in the source collection; otherwise, result is oclText[false].");

			iteration = it_Collection_forAll_2;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each element oclText[i] in the source collection; otherwise, result is oclText[false].");

			iteration = it_Collection_isUnique;
			iteration.setType(_Boolean);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_OclAny, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to a different value for each element oclText[i] in the source collection; otherwise, result is oclText[false].");
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");

			iteration = it_Collection_iterate;
			iteration.setType(tp_Collection_iterate_Tacc);
			iteration.setIsRequired(false);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createAccumulator(iteration, "acc", tp_Collection_iterate_Tacc, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Collection_iterate_Tacc, false);
			installComment(iteration, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source collection.");

			iteration = it_Collection_one;
			iteration.setType(_Boolean);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, true);
			installComment(iteration, "Results in oclText[true] if there is exactly one element in the source collection for which body is oclText[true].");

			iteration = it_Collection_reject;
			iteration.setType(_Collection_Collection_T_T);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, true);
			installComment(iteration, "The sub-collection of the source collection for which body is oclText[false].");

			iteration = it_Collection_select;
			iteration.setType(_Collection_Collection_T_T);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_Boolean, true);
			installComment(iteration, "The sub-collection of the source collection for which body is oclText[true].");

			iteration = it_Collection_sortedBy;
			iteration.setType(_Sequence_Collection_T_T);
			parameter = createIterator(iteration, "i", tp_Collection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Collection_T_OclAny, false);
			installComment(iteration, "Results in the Collection containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			iteration = it_Map_any;
			iteration.setType(tp_Map_K);
			iteration.setIsRequired(false);
			parameter = createIterator(iteration, "k", tp_Map_K, true);
			parameter = createParameter(iteration, "body", _Lambda_Map_K_Boolean, true);
			installComment(iteration, "Returns the key of any element in the e[source] map for which e[body] evaluates to oclText[true].\nReturns oclText[invalid] if the e[body] evaluates to oclText[invalid] for any key,\notherwise if there are one or more kets for which the e[body] is oclText[true],\nan indeterminate choice of one of them is returned, otherwise the null is oclText[invalid].\n\nlet source : Map(K,V) = ..., body : Lambda K(V) : Boolean = ... in\nsource->any(key <- value | body) = source->select(key | let value = source->at(key) in body)->asSequence()->first()");

			iteration = it_Map_collectBy;
			iteration.setType(_Map_Map_K_F_Map_collectBy_V2_F);
			parameter = createIterator(iteration, "k", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Map_collectBy_V2, false);
			installComment(iteration, "The Map from each element oclText[i] of the source collection, the key, to the set of values to and values that results from applying body to every value of the source map.\nThe result is not flattened.");

			iteration = it_Map_collectNested;
			iteration.setType(_Map_Map_K_F_Map_collectNested_V2_F);
			parameter = createIterator(iteration, "k", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Map_collectNested_V2, false);
			installComment(iteration, "The Map of key and values which results from applying body to every value of the source map.");

			iteration = it_Map_collect;
			iteration.setType(_Bag_Map_collect_V2_T);
			parameter = createIterator(iteration, "k", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Map_collect_V2, false);
			installComment(iteration, "The Map of key and values that results from applying body to every value of the source map.\nThe result is flattened.");

			iteration = it_Map_exists;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k1", tp_Map_K, false);
			parameter = createIterator(iteration, "k2", tp_Map_K, false);
			parameter = createIterator(iteration, "k3", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map.");

			iteration = it_Map_exists_1;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k1", tp_Map_K, false);
			parameter = createIterator(iteration, "k2", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one permutation of keys oclText[k1], oclText[k2] in the source map.");

			iteration = it_Map_exists_2;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to oclText[true] for at least one key oclText[k] in the source map.");

			iteration = it_Map_forAll;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k1", tp_Map_K, false);
			parameter = createIterator(iteration, "k2", tp_Map_K, false);
			parameter = createIterator(iteration, "k3", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2], oclText[k3] in the source map; otherwise, result is oclText[false].");

			iteration = it_Map_forAll_1;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k1", tp_Map_K, false);
			parameter = createIterator(iteration, "k2", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each permutation of keys oclText[k1], oclText[k2] in the source map; otherwise, result is oclText[false].");

			iteration = it_Map_forAll_2;
			iteration.setType(_Boolean);
			iteration.setIsInvalidating(true);
			iteration.setIsRequired(false);
			iteration.setIsValidating(true);
			parameter = createIterator(iteration, "k", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, false);
			installComment(iteration, "Results in oclText[true] if the body expression evaluates to oclText[true] for each key oclText[k] in the source map; otherwise, result is oclText[false].");

			iteration = it_Map_isUnique;
			iteration.setType(_Boolean);
			parameter = createIterator(iteration, "i", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_OclAny, false);
			installComment(iteration, "Results in oclText[true] if body evaluates to a different value for each key oclText[k] in the source map; otherwise, result is oclText[false].");

			iteration = it_Map_iterate;
			iteration.setType(tp_Map_iterate_Tacc);
			iteration.setIsRequired(false);
			parameter = createIterator(iteration, "i", tp_Map_K, false);
			parameter = createAccumulator(iteration, "acc", tp_Map_iterate_Tacc, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Map_iterate_Tacc, false);
			installComment(iteration, "Results in the final value of the accumulator oclText[acc] whose value is updated by evaluation of oclText[lambda] for each element oclText[i] in the source map.");

			iteration = it_Map_one;
			iteration.setType(_Boolean);
			parameter = createIterator(iteration, "i", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, true);
			installComment(iteration, "Results in oclText[true] if there is exactly one element in the source map for which body is oclText[true].");

			iteration = it_Map_reject;
			iteration.setType(_Map_Map_K_F_Map_V_F);
			parameter = createIterator(iteration, "i", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, true);
			installComment(iteration, "The subset of the source set for which body is oclText[false].");

			iteration = it_Map_select;
			iteration.setType(_Map_Map_K_F_Map_V_F);
			parameter = createIterator(iteration, "i", tp_Map_K, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Map_K_Boolean, true);
			installComment(iteration, "The subset of set for which expr is oclText[true].");

			iteration = it_OrderedSet_closure;
			iteration.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, true);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_OrderedSet, false);
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");

			iteration = it_OrderedSet_collectNested;
			iteration.setType(_Sequence_OrderedSet_collectNested_V_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_OrderedSet_collectNested_V, false);
			installComment(iteration, "The sequence of elements that results from applying body to every member of the source ordered collection.");

			iteration = it_OrderedSet_collect;
			iteration.setType(_Sequence_OrderedSet_collect_V_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_OrderedSet_collect_V, false);
			installComment(iteration, "The Sequence of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");

			iteration = it_OrderedSet_reject;
			iteration.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_Boolean, true);
			installComment(iteration, "The ordered set of the source ordered set for which body is oclText[false].");

			iteration = it_OrderedSet_select;
			iteration.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_Boolean, true);
			installComment(iteration, "The ordered set of the source ordered set for which body is oclText[true]");

			iteration = it_OrderedSet_sortedBy;
			iteration.setType(_OrderedSet_OrderedSet_T_T);
			parameter = createIterator(iteration, "i", tp_OrderedSet_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_OrderedSet_T_OclAny, false);
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");

			iteration = it_Sequence_closure;
			iteration.setType(_OrderedSet_Sequence_T_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, true);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_OrderedSet, false);
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");

			iteration = it_Sequence_collectNested;
			iteration.setType(_Sequence_Sequence_collectNested_V_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_Sequence_collectNested_V, false);
			installComment(iteration, "The sequence of elements that results from applying body to every member of the source ordered collection.");

			iteration = it_Sequence_collect;
			iteration.setType(_Sequence_Sequence_collect_V_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_Sequence_collect_V, false);
			installComment(iteration, "The Bag of elements that results from applying body to every member of the source sequence.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");

			iteration = it_Sequence_reject;
			iteration.setType(_Sequence_Sequence_T_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_Boolean, true);
			installComment(iteration, "The subsequence of the source sequence for which body is oclText[false].");

			iteration = it_Sequence_select;
			iteration.setType(_Sequence_Sequence_T_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_Boolean, true);
			installComment(iteration, "The subsequence of the source sequence for which body is oclText[true].");

			iteration = it_Sequence_sortedBy;
			iteration.setType(_Sequence_Sequence_T_T);
			parameter = createIterator(iteration, "i", tp_Sequence_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Sequence_T_OclAny, false);
			installComment(iteration, "Results in the Sequence containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c then a < c).");

			iteration = it_Set_closure;
			iteration.setType(_Set_Set_T_T);
			parameter = createIterator(iteration, "i", tp_Set_T, true);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_Set, false);
			installComment(iteration, "The closure of applying body transitively to every distinct element of the source collection.");

			iteration = it_Set_collectNested;
			iteration.setType(_Bag_Set_collectNested_V_T);
			parameter = createIterator(iteration, "i", tp_Set_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_Set_collectNested_V, false);
			installComment(iteration, "The Bag of elements which results from applying body to every member of the source nonordered collection.");

			iteration = it_Set_collect;
			iteration.setType(_Bag_Set_collect_V_T);
			parameter = createIterator(iteration, "i", tp_Set_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_Set_collect_V, false);
			installComment(iteration, "The Bag of elements that results from applying body to every member of the source set.\nThe result is flattened. Notice that this is based on collectNested, which can be of different type depending on the type of source.\ncollectNested is defined individually for each subclass of CollectionType.");

			iteration = it_Set_reject;
			iteration.setType(_Set_Set_T_T);
			parameter = createIterator(iteration, "i", tp_Set_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_Boolean, true);
			installComment(iteration, "The subset of the source set for which body is oclText[false].");

			iteration = it_Set_select;
			iteration.setType(_Set_Set_T_T);
			parameter = createIterator(iteration, "i", tp_Set_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_Boolean, true);
			installComment(iteration, "The subset of set for which expr is oclText[true].");

			iteration = it_Set_sortedBy;
			iteration.setType(_OrderedSet_Set_T_T);
			parameter = createIterator(iteration, "i", tp_Set_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_Set_T_OclAny, false);
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");

			iteration = it_UniqueCollection_sortedBy;
			iteration.setType(_OrderedSet_UniqueCollection_T_T);
			parameter = createIterator(iteration, "i", tp_UniqueCollection_T, false);
			parameter = createParameter(iteration, "lambda", _Lambda_UniqueCollection_T_OclAny, false);
			installComment(iteration, "Results in the ordered set containing all elements of the source collection.\nThe element for which body has the lowest value comes first, and so on.\nThe type of the body expression must have the < operation defined.\nThe < operation must return a Boolean value and must be transitive (i.e., if a < b and b < c, then a < c).");
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
