/*******************************************************************************
 * Copyright (c) 2013, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.genmodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGenModelGeneratorAdapter;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGeneratorAdapterFactory;
import org.eclipse.ocl.pivot.util.DerivedConstants;

/**
 * OCLGenModelUtil provides helpers for use by OCL's JET templates.
 *
 * The OCLGenModelUtil INSTANCE provides an appropriate implementation of facilities that are not
 * available on older EMF codegen distributions.
 */
public abstract class OCLGenModelUtil
{
	public static @NonNull OCLGenModelUtil INSTANCE =
			GenRuntimeVersion.get("2.26") != null ? new EMF_CodeGen_2_26() :			// 2021-06
			GenRuntimeVersion.get("2.25") != null ? new EMF_CodeGen_2_25() :			// 2021-03
			GenRuntimeVersion.get("2.17") != null ? new EMF_CodeGen_2_17() :			// 2019-03
			GenRuntimeVersion.get("2.16") != null ? new EMF_CodeGen_2_16() :			// 2018-12
			GenRuntimeVersion.get("2.14") != null ? new EMF_CodeGen_2_14() :			// Photon
			new EMF_CodeGen_Default();

	public static final @NonNull String OCL_GENMODEL_COPY_AND_PASTE_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/CopyAndPaste";
	public static final @NonNull String USE_NULL_ANNOTATIONS = "Use Null Annotations";
	public static final @NonNull String GENERATE_CLASSIFIER_INTS = "Generate Classifier ints";

	public static final @NonNull String OCL_GENMODEL_TO_STRING_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/ToString";

	public static final @NonNull String OCL_GENMODEL_VISITOR_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/Visitor";
	public static final @NonNull String ROOT_VISITOR_CLASS = "Root Visitor Class";
	public static final @NonNull String DERIVED_VISITOR_CLASS = "Derived Visitor Class";
	public static final @NonNull String VISITABLE_CLASSES = "Visitable Classes";
	public static final @NonNull String VISITABLE_INTERFACE = "Visitable Interface";

	private static final GeneratorAdapterFactory.Descriptor UML_DESCRIPTOR1 = new GeneratorAdapterFactory.Descriptor() {
		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new org.eclipse.uml2.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory();
		}
	};

	private static final GeneratorAdapterFactory.Descriptor UML_DESCRIPTOR2 = new GeneratorAdapterFactory.Descriptor() {
		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new org.eclipse.uml2.codegen.ecore.genmodel.generator.UML2GenModelGeneratorAdapterFactory();
		}
	};

	public static @NonNull String atNonNull(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			String value = genAnnotation.getDetails().get(USE_NULL_ANNOTATIONS);
			if (value != null) {
				boolean useAtNonNull = Boolean.valueOf(value);
				if (useAtNonNull) {
					return "@" + genModel.getImportedName(DerivedConstants.ORG_ECLIPSE_JDT_ANNOTATION_NON_NULL) + " ";
				}
			}
		}
		return "";
	}

	public static @NonNull String atNullable(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			String value = genAnnotation.getDetails().get(USE_NULL_ANNOTATIONS);
			if (value != null) {
				boolean useAtNonNull = Boolean.valueOf(value);
				if (useAtNonNull) {
					return "@" + genModel.getImportedName(DerivedConstants.ORG_ECLIPSE_JDT_ANNOTATION_NULLABLE) + " ";
				}
			}
		}
		return "";
	}

	public static Object copyAndPaste(@NonNull GenClass genClass) {
		String interfaceName = genClass.getQualifiedInterfaceName();
		GenModel genModel = genClass.getGenModel();
		String javaCopyFile = GenModelUtil.getAnnotation(genModel, OCL_GENMODEL_COPY_AND_PASTE_URI, interfaceName);
		if (javaCopyFile == null) {
			return "";
		}
		URI relativeURI = URI.createURI(javaCopyFile, true);
		URI baseURI = URI.createPlatformResourceURI("/" + genModel.getModelPluginID() + "/", true);
		URI uri = relativeURI.resolve(baseURI);
		StringBuilder s = new StringBuilder();
		s.append("	/**\n");
		s.append("	 * Start of copy from " + uri + " \n");
		s.append("	 */\n");
		s.append("	@SuppressWarnings(\"unused\") private static int _START_OF_COPY_ = 0;\n");
		char[] buf = new char[4096];
		try {
			InputStream iStream = URIConverter.INSTANCE.createInputStream(uri);
			Reader reader = new InputStreamReader(iStream);
			int len = 0;
			while ((len = reader.read(buf)) > 0) {
				s.append(buf, 0, len);
			}
			s.append("	/**\n");
			s.append("	 * End of copy from " + uri + " \n");
			s.append("	 */\n");
			s.append("	@SuppressWarnings(\"unused\") private static int _END_OF_COPY_ = 0;\n");
			iStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resolveImports(genModel, s.toString());
	}

	public static String getFeatureCountValue(GenClass genClass)
	{
		GenClass base = genClass.getBaseGenClass();
		if ((base == null) || base.isInterface())
		{
			return Integer.toString(genClass.getFeatureCount());
		}

		String baseCountID = getQualifiedFeatureCountID(base);
		return baseCountID + " + " + Integer.toString(genClass.getFeatureCount() - base.getFeatureCount());
	}

	public static @NonNull Iterable<GeneratorAdapterFactory.@NonNull Descriptor> getGeneratorAdapterFactoryDescriptors() {
		List<GeneratorAdapterFactory.@NonNull Descriptor> descriptors = new ArrayList<>();
		// Replacement for EMF to fix BUG 543870
		@SuppressWarnings("deprecation") GeneratorAdapterFactory.@NonNull Descriptor betterEstructureDescriptor = org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelGeneratorAdapterFactory.DESCRIPTOR;
		descriptors.add(betterEstructureDescriptor);
		// OCLinEcore embedded support - BUG 485764, BUG 485089
		descriptors.add(OCLinEcoreGeneratorAdapterFactory.DESCRIPTOR);
		return descriptors;
	}

	public static String getListConstructor(GenClass genClass, GenFeature genFeature) {
		String listConstructor = genClass.getListConstructor(genFeature);
		String f0 = genClass.getQualifiedFeatureID(genFeature);
		String f2 = getQualifiedFeatureValue(genClass, genFeature);
		String r0 = null;
		String r2 = null;
		GenFeature reverseFeature = genFeature.getReverse();
		if (reverseFeature != null) {
			GenClass reverseGenClass = reverseFeature.getGenClass();
			r0 = reverseGenClass.getQualifiedFeatureID(reverseFeature);
			r2 = getQualifiedFeatureValue(reverseGenClass, reverseFeature);
		}
		if (r0 == null) {
			listConstructor = listConstructor.replaceAll(f0, f2);
		}
		else if (r0.length() > f0.length()) {
			listConstructor = listConstructor.replaceAll(r0, r2).replaceAll(f0, f2);
		}
		else {
			listConstructor = listConstructor.replaceAll(f0, f2).replaceAll(r0, r2);
		}
		return listConstructor;
	}

	public static String getOperationCountValue(GenClass genClass)
	{
		GenClass base = genClass.getBaseGenClass();
		if ((base == null) || base.isInterface())
		{
			return Integer.toString(genClass.getOperationCount());
		}

		String baseCountID = getQualifiedOperationCountID(base);
		return baseCountID + " + " + Integer.toString(genClass.getOperationCount() - base.getOperationCount());
	}

	public static String getQualifiedFeatureValue(GenClass genClass, GenFeature genFeature) {
		List<GenFeature> allFeatures = genClass.getAllGenFeatures();
		int i = allFeatures.indexOf(genFeature);
		GenClass base = genClass.getBaseGenClass();
		for (; base != null; base = base.getBaseGenClass()) {
			if (base.getGenPackage() != genClass.getGenPackage()) {
				break;
			}
		}

		if (base == null)
		{
			return Integer.toString(i);
		}

		int baseCount = base.getFeatureCount();
		if ((i < baseCount) || base.isInterface())
		{
			return Integer.toString(i);
		}
		return getQualifiedFeatureCountID(base) + " + " + Integer.toString(i - baseCount);
	}

	public static String getQualifiedFeatureCountID(GenClass genClass)
	{
		return genClass.getGenModel().getImportedName(genClass.getQualifiedClassName()) + "." + genClass.getFeatureCountID();
	}

	public static String getQualifiedOperationValue(GenClass genClass, GenOperation genOperation)
	{
		List<GenOperation> allOperations = genClass.getAllGenOperations(false);
		int i = allOperations.indexOf(genOperation);
		GenClass base = genClass.getBaseGenClass();
		for (; base != null; base = base.getBaseGenClass()) {
			if (base.getGenPackage() != genClass.getGenPackage()) {
				break;
			}
		}

		if (base == null)
		{
			return Integer.toString(i);
		}

		int baseCount = base.getOperationCount();
		if ((i < baseCount) || base.isInterface())
		{
			return Integer.toString(i);
		}
		return getQualifiedOperationCountID(base) + " + " + Integer.toString(i - baseCount);
	}

	public static String getQualifiedOperationCountID(GenClass genClass)
	{
		return genClass.getGenModel().getImportedName(genClass.getQualifiedClassName())+ "." + genClass.getOperationCountID();
	}

	public static String getQualifiedOperationValue(GenClass genClass, GenOperation genOperation, boolean diagnosticCode) {
		return getQualifiedOperationValue(genClass, genOperation);
	}

	public static /*@Nullable*/ String getVisitableClass(@NonNull GenModel genModel) {		// @Nullable breaks Xtend
		return GenModelUtil.getAnnotation(genModel, OCL_GENMODEL_VISITOR_URI, VISITABLE_INTERFACE);
	}

	public static void initializeGeneratorAdapterFactoryRegistry() {
		GeneratorAdapterFactory.Descriptor.Registry registry = GeneratorAdapterFactory.Descriptor.Registry.INSTANCE;
		Collection<Descriptor> registryDescriptors = registry.getDescriptors(GenModelPackage.eNS_URI);
		for (GeneratorAdapterFactory.@NonNull Descriptor descriptor : getGeneratorAdapterFactoryDescriptors()) {
			if (!registryDescriptors.contains(descriptor)) {
				registry.addDescriptor(GenModelPackage.eNS_URI, descriptor);
			}
		}
		String umlGenModelNsURI = org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI;
		registry.addDescriptor(umlGenModelNsURI, UML_DESCRIPTOR1);
		registry.addDescriptor(umlGenModelNsURI, UML_DESCRIPTOR2);
		registry.addDescriptor(umlGenModelNsURI, OCLinEcoreGeneratorAdapterFactory.DESCRIPTOR);
	}

	public static boolean isGenerateClassifierInts(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			String value = genAnnotation.getDetails().get(GENERATE_CLASSIFIER_INTS);
			if (value != null) {
				boolean generateClassifierInts = Boolean.valueOf(value);
				if (generateClassifierInts) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isRootVisitableClass(@NonNull GenClass genClass) {
		String interfaceName = genClass.getQualifiedInterfaceName();
		String visitableClasses = GenModelUtil.getAnnotation(genClass.getGenModel(), OCL_GENMODEL_VISITOR_URI, VISITABLE_CLASSES);
		return (visitableClasses != null) && visitableClasses.contains(interfaceName);
	}

	public static @NonNull String resolveImports(GenModel genModel, String source)
	{
		int iMax = source.length();
		int iStart = 0;
		StringBuilder s = new StringBuilder();
		while (true) {
			int iPrefix = source.indexOf(ImportUtils.IMPORTS_PREFIX, iStart);
			if (iPrefix < 0) {
				break;
			}
			int iSuffix = source.indexOf(ImportUtils.IMPORTS_SUFFIX, iPrefix);
			if (iSuffix < 0) {
				break;
			}
			s.append(source, iStart, iPrefix);
			String longName = source.substring(iPrefix+ImportUtils.IMPORTS_PREFIX.length(), iSuffix);
			s.append(genModel.getImportedName(longName));
			iStart = iSuffix + ImportUtils.IMPORTS_SUFFIX.length();
		}
		s.append(source, iStart, iMax);
		return s.toString();
	}

	public abstract String getAPITags(GenBase genBase, String indentation);
	public abstract String getImplicitAPITags(GenBase genBase, String indentation);
	public abstract String getImplicitAPITags(GenBase genBase, String indentation, boolean excludeOwnDocumentation);
	public abstract String getRawQualifiedInterfaceName(GenClass genClass);
	public abstract boolean hasAPIDeprecatedTag(GenBase genBase);
	public abstract boolean hasAPIDeprecatedTag(Collection<?>... elements);
	public abstract boolean hasAPITags(GenBase genBase);
	public abstract boolean hasConstraintEAnnotations();	// Fixed by Bug 571760 in EMF 2.26 for 2021-06
	public abstract boolean hasDoubleOverrideBug547424();
	public abstract boolean hasImplicitAPITags(GenBase genBase);
	public abstract boolean hasImplicitAPITags(GenBase genBase, boolean excludeOwnDocumentation);
	public abstract boolean hasImplicitAPIDeprecatedTag(GenBase genBase);
	public abstract boolean hasImplicitAPIDeprecatedTag(Collection<?>... elements);
	public abstract boolean useInterfaceOverrideAnnotation(GenModel genModel);

	/**
	 * Whether the ImportManager supports <%...%> within <%...%>
	 */
	public abstract boolean useNestedImports();

	/**
	 * EMF_CodeGen_Default provides fall-back implementations of GenModel facilities not available on the
	 * the current platform.
	 */
	private static class EMF_CodeGen_Default extends OCLGenModelUtil
	{
		@Override
		public String getAPITags(GenBase genBase, String indentation) {
			return "";
		}

		@Override
		public String getImplicitAPITags(GenBase genBase, String indentation) {
			return getImplicitAPITags(genBase, indentation, false);
		}

		@Override
		public String getImplicitAPITags(GenBase genBase, String indentation, boolean excludeOwnDocumentation) {
			return "";
		}

		@Override
		public String getRawQualifiedInterfaceName(GenClass genClass) {
			return getInternalQualifiedInterfaceName(genClass, false).replace('$', '.');
		}
		private static String getInternalQualifiedInterfaceName(GenClass genClassArg, boolean includeTemplateArguments)
		{
			if (genClassArg.isDynamic()) {
				GenClass genClass = genClassArg.getBaseGenClass();
				return genClass == null ? "org.eclipse.emf.ecore.EObject" : getInternalQualifiedInterfaceName(genClass, false);
			}
			EClass ecoreClass = genClassArg.getEcoreClass();
			String instanceClassName = ecoreClass.getInstanceClassName();
			return instanceClassName != null ?
				includeTemplateArguments ? ecoreClass.getInstanceTypeName() : instanceClassName :
					genClassArg.getGenPackage().getInterfacePackageName() + "." + genClassArg.getInterfaceName();
		}

		@Override
		public boolean hasAPIDeprecatedTag(GenBase genBase) {
			return false;
		}

		@Override
		public boolean hasAPIDeprecatedTag(Collection<?>... elements) {
			return false;
		}

		@Override
		public boolean hasAPITags(GenBase genBase) {
			return false;
		}

		@Override
		public boolean hasConstraintEAnnotations() {
			return false;
		}

		@Override
		public boolean hasDoubleOverrideBug547424() {
			return false;
		}

		@Override
		public boolean hasImplicitAPITags(GenBase genBase) {
			return false;
		}

		@Override
		public boolean hasImplicitAPITags(GenBase genBase, boolean excludeOwnDocumentation) {
			return false;
		}

		@Override
		public boolean hasImplicitAPIDeprecatedTag(GenBase genBase) {
			return false;
		}

		@Override
		public boolean hasImplicitAPIDeprecatedTag(Collection<?>... elements) {
			return false;
		}

		@Override
		public boolean useInterfaceOverrideAnnotation(GenModel genModel) {
			return genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK60;
		}

		@Override
		public boolean useNestedImports() {
			return false;
		}
	}

	/**
	 * EMF_CodeGen_2_14 redirects GenModel facilities available in an EMF >= 2.14 platform to the
	 * standard implementation.
	 */
	private static class EMF_CodeGen_2_14 extends EMF_CodeGen_Default
	{
		@Override
		public String getAPITags(GenBase genBase, String indentation) {
			return genBase.getAPITags(indentation);
		}

		@Override
		public String getImplicitAPITags(GenBase genBase, String indentation) {
			return genBase.getImplicitAPITags(indentation);
		}

		@Override
		public String getImplicitAPITags(GenBase genBase, String indentation, boolean excludeOwnDocumentation) {
			return genBase.getImplicitAPITags(indentation, excludeOwnDocumentation);
		}

		@Override
		public String getRawQualifiedInterfaceName(GenClass genClass) {
			return genClass.getRawQualifiedInterfaceName();
		}

		@Override
		public boolean hasAPIDeprecatedTag(GenBase genBase) {
			return genBase.hasAPIDeprecatedTag();
		}

		@Override
		public boolean hasAPIDeprecatedTag(Collection<?>... elements) {
			return GenModelUtil.hasAPIDeprecatedTag(elements);
		}

		@Override
		public boolean hasAPITags(GenBase genBase) {
			return genBase.hasAPITags();
		}

		@Override
		public boolean hasImplicitAPITags(GenBase genBase) {
			return genBase.hasImplicitAPITags();
		}

		@Override
		public boolean hasImplicitAPITags(GenBase genBase, boolean excludeOwnDocumentation) {
			return genBase.hasImplicitAPITags(excludeOwnDocumentation);
		}

		@Override
		public boolean hasImplicitAPIDeprecatedTag(GenBase genBase) {
			return genBase.hasImplicitAPIDeprecatedTag();
		}

		@Override
		public boolean hasImplicitAPIDeprecatedTag(Collection<?>... elements) {
			return GenModelUtil.hasImplicitAPIDeprecatedTag(elements);
		}
	}

	/**
	 * EMF_CodeGen_2_17 redirects GenModel facilities available in an EMF >= 2.16 platform to the
	 * standard implementation.
	 */
	private static class EMF_CodeGen_2_16 extends EMF_CodeGen_2_14
	{
		@Override
		public boolean hasDoubleOverrideBug547424() {
			return true;	// Pre 2018-12 there were no overrides at all.
		}

		@Override
		public boolean useInterfaceOverrideAnnotation(GenModel genModel) {
			return genModel.useInterfaceOverrideAnnotation();
		}
	}

	/**
	 * EMF_CodeGen_2_17 redirects GenModel facilities available in an EMF >= 2.17 platform to the
	 * standard implementation.
	 */
	private static class EMF_CodeGen_2_17 extends EMF_CodeGen_2_16
	{
		@Override
		public boolean useNestedImports() {
			return true;
		}
	}

	/**
	 * EMF_CodeGen_2_25 redirects GenModel facilities available in an EMF >= 2.25 platform to the
	 * standard implementation.
	 */
	private static class EMF_CodeGen_2_25 extends EMF_CodeGen_2_17
	{
		@Override
		public boolean hasDoubleOverrideBug547424() {
			return false;			// Fixed in UML 5.6.0 for 2021-03
		}
	}

	/**
	 * EMF_CodeGen_2_25 redirects GenModel facilities available in an EMF >= 2.26 platform to the
	 * standard implementation.
	 */
	private static class EMF_CodeGen_2_26 extends EMF_CodeGen_2_25
	{
		@Override
		public boolean hasConstraintEAnnotations() {
			return true;			// Fixed by Bug 571760 in EMF 2.26 for 2021-06
		}
	}
}
