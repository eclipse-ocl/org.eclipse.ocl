/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xmi.impl.EMOFExtendedMetaData;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.AnnotationImpl;
import org.eclipse.ocl.pivot.internal.utilities.OppositePropertyDetails;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.AnnotationUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

import com.google.common.collect.Sets;

/**
 * An EAnnotationConverter supports the conversion of an EAnnotation to its AS representation.
 */
public class EAnnotationConverter
{
	/**
	 * Diagnostic capability to identify EAnnotations that Ecore2AS might need special treatment for.
	 */
	public static final @NonNull TracingOption UNKNOWN_EANNOTATIONS = new TracingOption(PivotPlugin.PLUGIN_ID, "eAnnotationConverter/unknown");

	/**
	 * EAnnotation sources for which a DebugNothingEAnnotationConverter is to be used.
	 */
	public static Set<@Nullable String> knownEAnnotationSources = null;

	private static final @NonNull Map<@NonNull String, @NonNull EAnnotationConverter> source2eAnnotationConverter = new HashMap<>();

	static {
		// CDO
		add("http://www.eclipse.org/emf/CDO", CDOEAnnotationConverter.INSTANCE);
		// EMF
		add(EcorePackage.eNS_URI, EcoreEAnnotationConverter.INSTANCE);
	//	add(EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI, EMOFExtendedMetaDataEAnnotationConverter.INSTANCE);
		add(EMOFExtendedMetaData.EMOF_PACKAGE_NS_URI_2_0 + "#" + OppositePropertyDetails.PROPERTY_OPPOSITE_ROLE_NAME_KEY, EMOFExtendedMetaDataEAnnotationConverter.INSTANCE);
		add(PivotConstants.EXTENDED_META_DATA_ANNOTATION_SOURCE, ExtendedMetaDataEAnnotationConverter.INSTANCE);
		add(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE, GenModelEAnnotationConverter.INSTANCE);
	//	public static final Object PROPERTY_OPPOSITE_ROLE_UNIQUE_KEY = "Property.oppositeUnique"; //$NON-NLS-1$
	//	public static final Object PROPERTY_OPPOSITE_ROLE_ORDERED_KEY = "Property.oppositeOrdered"; //$NON-NLS-1$
	//	public static final Object PROPERTY_OPPOSITE_ROLE_LOWER_KEY = "Property.oppositeLower"; //$NON-NLS-1$
	//	public static final Object PROPERTY_OPPOSITE_ROLE_UPPER_KEY = "Property.oppositeUpper"; //$NON-NLS-1$
		// UML
		add(PivotConstantsInternal.DUPLICATES_ANNOTATION_SOURCE, DuplicatesEAnnotationConverter.INSTANCE);
		add(PivotConstantsInternal.REDEFINES_ANNOTATION_SOURCE, RedefinesEAnnotationConverter.INSTANCE);
		add(PivotConstantsInternal.SUBSETS_ANNOTATION_SOURCE, SubsetsEAnnotationConverter.INSTANCE);
		add(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI, UMLEAnnotationConverter.INSTANCE);
		add(PivotConstantsInternal.UNION_ANNOTATION_SOURCE, UnionEAnnotationConverter.INSTANCE);
		// OCL
		add(OCLConstants.OCL_DELEGATE_URI_DEBUG, DelegateURIEAnnotationConverter.INSTANCE);
		add(OCLConstants.OCL_DELEGATE_URI_LPG, DelegateURIEAnnotationConverter.INSTANCE);
		add(OCLConstants.OCL_DELEGATE_URI_PIVOT, DelegateURIEAnnotationConverter.INSTANCE);
		add(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE, ASLibraryEAnnotationConverter.INSTANCE);
		add(PivotConstants.AS_METAMODEL_ANNOTATION_SOURCE, ASMetamodelEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.CLASSIFIER_ANNOTATION_SOURCE, ClassifierEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.COLLECTION_ANNOTATION_SOURCE, CollectionEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.IMPORT_ANNOTATION_SOURCE, ImportEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.OPERATION_ANNOTATION_SOURCE, OperationEAnnotationConverter.INSTANCE);
		//	add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE, PackageEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE + "-Boolean", OriginalTypeEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE + "-Integer", OriginalTypeEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE + "-Real", OriginalTypeEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE + "-String", OriginalTypeEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PACKAGE_ANNOTATION_SOURCE + "-UnlimitedNatural", OriginalTypeEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PARAMETER_ANNOTATION_SOURCE, ParameterEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PRECEDENCE_ANNOTATION_SOURCE, PrecedenceEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.PROPERTY_ANNOTATION_SOURCE, PropertyEAnnotationConverter.INSTANCE);
		add(AnnotationUtil.TYPED_ELEMENT_ANNOTATION_SOURCE, TypedElementEAnnotationConverter.INSTANCE);
	}

	private static void add(@NonNull String source, @NonNull EAnnotationConverter eAnnotationConverter) {
		EAnnotationConverter old = source2eAnnotationConverter.put(source, eAnnotationConverter);
		assert (old == null) || (old == eAnnotationConverter);
	}

	/**
	 * Specify that source is an EAnnotation.source that is expected to occur and so
	 * need not be diagnosed as unknown.
	 */
	public static void addKnownEAnnotationSource(@Nullable String source) {
		if (knownEAnnotationSources == null) {
			knownEAnnotationSources = new HashSet<>();
		}
		knownEAnnotationSources.add(source);
	}

	public static @Nullable Annotation convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
		String source = eAnnotation.getSource();
		EAnnotationConverter eAnnotationConverter = source2eAnnotationConverter.get(source);
		if (eAnnotationConverter == null) {
			boolean isKnown = (knownEAnnotationSources != null) && knownEAnnotationSources.contains(source);
			if (isKnown) {
				eAnnotationConverter = DebugNothingEAnnotationConverter.INSTANCE;
			}
			else {
				eAnnotationConverter = DebugEverythingEAnnotationConverter.INSTANCE;
			}
		}
		Annotation asAnnotation = eAnnotationConverter.convertAll(external2AS, eAnnotation);
//		System.out.println("convert " + NameUtil.debugSimpleName(eAnnotation) + " => " + NameUtil.debugSimpleName(asAnnotation));
		return asAnnotation;
	}

	protected @Nullable Annotation convertAll(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
		return convertAll(external2AS, null, eAnnotation);
	}

	protected final @Nullable Annotation convertAll(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
		asAnnotation = convertDetails(external2AS, asAnnotation, eAnnotation);
		asAnnotation = convertContents(external2AS, asAnnotation, eAnnotation);
		asAnnotation = convertReferences(external2AS, asAnnotation, eAnnotation);
		return asAnnotation;
	}

	/**
	 * Convert the contents of eAnnotation to contribute to asAnnotation. The default just reports
	 * a lack of support.
	 */
	protected @Nullable Annotation convertContents(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
		if (!eAnnotation.getContents().isEmpty()) {
			if (traceUnknownEAnnotations()) {
				UNKNOWN_EANNOTATIONS.println("\"" + eAnnotation.getSource() + "\" EAnnotation contents");
			}
			if (asAnnotation == null) {
				asAnnotation = createAnnotation(external2as, eAnnotation);
			}
		}
		return asAnnotation;
	}

	/**
	 * Convert the key+value EAnnotation detail, returning a Detail if one should be installed
	 * in an equivalent Annotation, or null if the key+value has been installed in the AS.
	 */
	protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
		if (traceUnknownEAnnotations()) {
			UNKNOWN_EANNOTATIONS.println("\"" + eAnnotation.getSource() + "\" " + key + " : " + value);
		}
		return createDetail(external2AS, asAnnotation, eAnnotation, key, value);
	}

	/**
	 * Convert the details of eAnnotation optionally returning some AS DEtail elements for inclusion in an
	 * a corresponding Annotation. The default implementation invokes convertDetail for each detail and
	 * returns an aggregate of the returns.
	 */
	protected @Nullable Annotation convertDetails(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
		for (Map.Entry<String, String> eDetail : eAnnotation.getDetails()) {
			String key = eDetail.getKey();
			String value = eDetail.getValue();
			asAnnotation = convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
		}
		return asAnnotation;
	}

	/**
	 * Convert the references of eAnnotation to contribute to asAnnotation. The default just reports
	 * a lack of support.
	 */
	protected @Nullable Annotation convertReferences(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
		if (!eAnnotation.getReferences().isEmpty()) {
			if (traceUnknownEAnnotations()) {
				UNKNOWN_EANNOTATIONS.println("\"" + eAnnotation.getSource() + "\" EAnnotation references");
			}
			if (asAnnotation == null) {
				asAnnotation = createAnnotation(external2as, eAnnotation);
			}
			external2as.queueReference(eAnnotation);
		}
		return asAnnotation;
	}

	/**
	 * Create and return the Annotation for eAnnotation. May be overridden to return null for an eAnnotation that
	 * should be fully represented by regular AS elements.
	 */
	protected final @NonNull Annotation createAnnotation(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
		AnnotationImpl asAnnotation = (AnnotationImpl)PivotFactory.eINSTANCE.createAnnotation();
		asAnnotation.setName(eAnnotation.getSource());
		external2AS.addMapping(eAnnotation, asAnnotation);
		return asAnnotation;
	}

	protected final @NonNull Annotation createDetail(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
		if (asAnnotation == null) {
			asAnnotation = createAnnotation(external2as, eAnnotation);
		}
		Detail asDetail = PivotFactory.eINSTANCE.createDetail();
		asDetail.setName(key);
		asDetail.getValues().add(value);
		asAnnotation.getOwnedDetails().add(asDetail);
		return asAnnotation;
	}

	protected @NonNull Element getASContainer(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
		EObject eContainer = eAnnotation.eContainer();
		assert eContainer != null;
		Element asContainer = external2AS.getCreated(Element.class, eContainer);
		assert asContainer != null;
		return asContainer;
	}

	protected boolean traceUnknownEAnnotations() {
		return UNKNOWN_EANNOTATIONS.isActive();
	}

	private static class ASLibraryEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ASLibraryEAnnotationConverter();

		@Override
		protected Annotation convertAll(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
			Annotation asAnnotation = createAnnotation(external2AS, eAnnotation);
			return super.convertAll(external2AS, asAnnotation, eAnnotation);
		}
	}

	private static class ASMetamodelEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ASMetamodelEAnnotationConverter();
	}

	private static class CDOEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new CDOEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if ("filter".equals(key) || "persistent".equals(key)) {
				asAnnotation = createDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class ClassifierEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ClassifierEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (AnnotationUtil.CLASSIFIER_ROLE.equals(key)) {
				// Suppress redundant annotation
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class CollectionEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new CollectionEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (AnnotationUtil.COLLECTION_IS_NULL_FREE.equals(key)) {
				EObject eContainer = eAnnotation.eContainer();
				assert eContainer != null;
				Element asContainer = external2AS.getCreated(Element.class, eContainer);
				assert asContainer != null;
			//	if (asContainer instanceof org.eclipse.ocl.pivot.Package) {
			//		asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			//	}
			//	else {
					// Suppress redundant annotation
			//	}
			}
			else if (AnnotationUtil.COLLECTION_KIND.equals(key)) {
				// Suppress redundant annotation
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class DebugEverythingEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new DebugEverythingEAnnotationConverter();

		@Override
		protected Annotation convertAll(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
			if (traceUnknownEAnnotations()) {
				UNKNOWN_EANNOTATIONS.println("\"" + eAnnotation.getSource() + "\"");
			}
			Annotation asAnnotation = createAnnotation(external2AS, eAnnotation);
			return super.convertAll(external2AS, asAnnotation, eAnnotation);
		}
	}

	private static class DebugNothingEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new DebugNothingEAnnotationConverter();

		@Override
		protected boolean traceUnknownEAnnotations() {
			return false;
		}
	}

	private static class DelegateURIEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new DelegateURIEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetails(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			return asAnnotation;
		}
	}

	private static class DuplicatesEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new DuplicatesEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertContents(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			// contents propagate
			return asAnnotation;
		}
	}

	private static class EMOFExtendedMetaDataEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new EMOFExtendedMetaDataEAnnotationConverter();

		private static final @NonNull Set<@NonNull String> knownKeys = Sets.newHashSet(OppositePropertyDetails.BODY_KEY, OppositePropertyDetails.LOWER_KEY, OppositePropertyDetails.ORDERED_KEY, OppositePropertyDetails.UNIQUE_KEY, OppositePropertyDetails.UPPER_KEY);

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (knownKeys.contains(key)) {
				// suppress redundant annotation - key+value supported by an AS Property
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class EcoreEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new EcoreEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if ("constraints".equals(key)) {
				// Suppress redundant annotation - there is a Constraint for each element
			}
			else if ("invocationDelegates".equals(key) || "settingDelegates".equals(key) || "validationDelegates".equals(key)) {
				// Suppress redundant annotation - need for delegates is computed
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class ExtendedMetaDataEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ExtendedMetaDataEAnnotationConverter();

		private static final @NonNull Set<@NonNull String> knownKeys = Sets.newHashSet("baseType", "enumeration", "group", "itemType", "kind", "maxInclusive", "memberTypes", "minInclusive", "minLength", "name", "namespace", "pattern", "processing", "whiteSpace", "wildcards");

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (knownKeys.contains(key)) {
				asAnnotation = createDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class GenModelEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new GenModelEAnnotationConverter();

		private static final @NonNull Set<@NonNull String> knownKeys = Sets.newHashSet("body", "suppressedGetVisibility", "suppressedIsSetVisibility", "suppressedSetVisibility", "suppressedUnsetVisibility", "suppressedVisibility");

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(key)) {
				Element asContainer = getASContainer(external2AS, eAnnotation);
				external2AS.createComments(asContainer, value);
				// Suppress processed annotation
			}
			else if (knownKeys.contains(key)) {
				asAnnotation = createDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class ImportEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ImportEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			// Suppress redundant annotation
			return asAnnotation;
		}
	}

	private static class OperationEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new OperationEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Operation asOperation = external2AS.getCreated(Operation.class, eContainer);
			assert asOperation != null;
			if (AnnotationUtil.OPERATION_IMPLEMENTATION.equals(key)) {
				asOperation.setImplementationClass(value);
			}
			else if (AnnotationUtil.OPERATION_ACCUMULATORS.equals(key) || AnnotationUtil.OPERATION_ITERATORS.equals(key)) {
				// XXX	assert asOperation instanceof Iteration;
			}
			else if (AnnotationUtil.OPERATION_IS_COERCION.equals(key)) {
				org.eclipse.ocl.pivot.Class owningClass = asOperation.getOwningClass();
				if ((owningClass instanceof PrimitiveType) && Boolean.parseBoolean(value)) {
					((PrimitiveType)owningClass).getCoercions().add(asOperation);
				}
			}
			else if (AnnotationUtil.OPERATION_IS_INVALIDATING.equals(key)) {
				asOperation.setIsInvalidating((value != null) && Boolean.parseBoolean(value));
			}
			else if (AnnotationUtil.OPERATION_IS_STATIC.equals(key)) {
				asOperation.setIsStatic((value != null) && Boolean.parseBoolean(value));
			}
			else if (AnnotationUtil.OPERATION_IS_TRANSIENT.equals(key)) {
				asOperation.setIsTransient((value != null) && Boolean.parseBoolean(value));
			}
			else if (AnnotationUtil.OPERATION_IS_TYPE_OF.equals(key)) {
				asOperation.setIsTypeof(Boolean.valueOf(value));
			}
			else if (AnnotationUtil.OPERATION_IS_VALIDATING.equals(key)) {
				asOperation.setIsValidating((value != null) && Boolean.parseBoolean(value));
			}
			else if (AnnotationUtil.OPERATION_PRECEDENCE.equals(key)) {
				Library asLibrary = (Library) asOperation.getOwningClass().getOwningPackage();
				asOperation.setPrecedence(NameUtil.getNameable(asLibrary.getOwnedPrecedences(), value));
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class OriginalTypeEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new OriginalTypeEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertReferences(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			// References populate the newCreateMap in Ecore2AS.loadPackageOriginalTypeEAnnotations invoked from caseEPackage.
			return asAnnotation;
		}
	}

/*	private static class PackageEAnnotationConverter extends AbstractEAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new PackageEAnnotationConverter();

		private PackageEAnnotationConverter() {
			super(AnnotationUtil.PACKAGE_ROLE);
		}
	} */

	private static class ParameterEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new ParameterEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Parameter asParameter = external2AS.getCreated(Parameter.class, eContainer);
			assert asParameter != null;
			if (AnnotationUtil.PARAMETER_IS_TYPE_OF.equals(key)) {
				asParameter.setIsTypeof(Boolean.valueOf(value));
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class PrecedenceEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new PrecedenceEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			assert key != null;
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Library asLibrary = external2AS.getCreated(Library.class, eContainer);
			assert asLibrary != null;
			Precedence asPrecedence = PivotUtil.createPrecedence(key, AssociativityKind.getByName(value));
			asLibrary.getOwnedPrecedences().add(asPrecedence);
			return asAnnotation;
		}
	}

	private static class PropertyEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new PropertyEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Property asProperty = external2AS.getCreated(Property.class, eContainer);
			assert asProperty != null;
			if (AnnotationUtil.PROPERTY_IMPLEMENTATION.equals(key)) {
				asProperty.setImplementationClass(value);
			}
			else if (AnnotationUtil.PROPERTY_IS_STATIC.equals(key)) {
				asProperty.setIsStatic((value != null) && Boolean.parseBoolean(value));
			}
			else if (AnnotationUtil.PROPERTY_SELF.equals(key)) {
				//	Suppress redundant annotation detail
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class RedefinesEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new RedefinesEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertReferences(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			// References populate the newCreateMap in Ecore2AS.loadPackageOriginalTypeEAnnotations invoked from caseEPackage.
			return asAnnotation;
		}
	}

	private static class SubsetsEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new SubsetsEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertReferences(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			// References populate the newCreateMap in Ecore2AS.loadPackageOriginalTypeEAnnotations invoked from caseEPackage.
			return asAnnotation;
		}
	}

	private static class TypedElementEAnnotationConverter extends EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new TypedElementEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (AnnotationUtil.TYPED_ELEMENT_ORIGINAL_TYPE.equals(key)) {
				//	Suppress redundant annotation detail
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class UMLEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new UMLEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertDetail(@NonNull AbstractExternal2AS external2AS, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME.equals(key)) {
				Element asContainer = getASContainer(external2AS, eAnnotation);
				assert ClassUtil.safeEquals(((NamedElement)asContainer).getName(), value);
				//	Suppress redundant annotation detail
			}
			else {
				asAnnotation = super.convertDetail(external2AS, asAnnotation, eAnnotation, key, value);
			}
			return asAnnotation;
		}
	}

	private static class UnionEAnnotationConverter extends EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new UnionEAnnotationConverter();

		@Override
		protected @Nullable Annotation convertReferences(@NonNull AbstractExternal2AS external2as, @Nullable Annotation asAnnotation, @NonNull EAnnotation eAnnotation) {
			// References populate the newCreateMap in Ecore2AS.loadPackageOriginalTypeEAnnotations invoked from caseEPackage.
			return asAnnotation;
		}
	}
}