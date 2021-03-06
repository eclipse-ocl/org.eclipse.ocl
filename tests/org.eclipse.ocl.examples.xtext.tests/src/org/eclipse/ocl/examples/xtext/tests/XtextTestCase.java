/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.PivotTestCaseWithAutoTearDown;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.context.ModelContext;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.essentialocl.attributes.NavigationUtil;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.InfixExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NavigatingArgCS;
import org.eclipse.ocl.xtext.essentialoclcs.NestedExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.PrefixExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.SquareBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.TypeNameExpCS;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;

public class XtextTestCase extends PivotTestCaseWithAutoTearDown
{
	public static interface Normalizer {
		void denormalize();
		void normalize();
	}

	public static class EAnnotationsNormalizer implements Normalizer
	{
		protected final @NonNull EModelElement eModelElement;
		protected final List<EAnnotation> oldOrder;

		public EAnnotationsNormalizer(@NonNull EModelElement eModelElement) {
			this.eModelElement = eModelElement;
			this.oldOrder = new ArrayList<EAnnotation>(eModelElement.getEAnnotations());
		}

		@Override
		public void denormalize() {
			EList<EAnnotation> eList = eModelElement.getEAnnotations();
			eList.clear();
			eList.addAll(oldOrder);
		}

		@Override
		public void normalize() {
			EList<EAnnotation> eList = eModelElement.getEAnnotations();
			List<EAnnotation> newOrder = new ArrayList<EAnnotation>(eList);
			Collections.sort(newOrder, NameUtil.EAnnotationComparator.INSTANCE);
			eList.clear();
			eList.addAll(newOrder);
		}
	}

	public static class EAnnotationConstraintsNormalizer implements Normalizer
	{
		protected final @NonNull EAnnotation eAnnotation;
		protected final @Nullable String oldConstraints;

		public EAnnotationConstraintsNormalizer(@NonNull EAnnotation eAnnotation) {
			this.eAnnotation = eAnnotation;
			this.oldConstraints = eAnnotation.getDetails().get("constraints");
		}

		@Override
		public void denormalize() {
			eAnnotation.getDetails().put("constraints", oldConstraints);
		}

		@Override
		public void normalize() {
			StringBuilder s1 = new StringBuilder();
			if (oldConstraints != null) {
				String[] s = oldConstraints.split(" ");
				Arrays.sort(s);
				for (int i = 0; i < s.length; i++) {
					if (i > 0) {
						s1.append(" ");
					}
					s1.append(s[i]);
				}
			}
			eAnnotation.getDetails().put("constraints", s1.toString());
		}
	}

	public static class EDetailsNormalizer implements Normalizer
	{
		protected final @NonNull EAnnotation eAnnotation;
		protected final List<Map.Entry<String, String>> oldOrder;

		public EDetailsNormalizer(@NonNull EAnnotation eAnnotation) {
			this.eAnnotation = eAnnotation;
			this.oldOrder = new ArrayList<Map.Entry<String, String>>(eAnnotation.getDetails());
		}

		@Override
		public void denormalize() {
			EList<Map.Entry<String, String>> eDetails = eAnnotation.getDetails();
			eDetails.clear();
			eDetails.addAll(oldOrder);
		}

		@Override
		public void normalize() {
			List<Map.Entry<String, String>> eDetails = eAnnotation.getDetails();
			List<Map.Entry<String, String>> newOrder = new ArrayList<Map.Entry<String, String>>(eDetails);
			Collections.sort(newOrder, new Comparator<Map.Entry<String, String>>()
			{
				@Override
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					String n1 = o1.getKey();
					String n2 = o2.getKey();
					return n1.compareTo(n2);
				}
			}
					);
			eDetails.clear();
			eDetails.addAll(newOrder);
		}
	}

	/*	public static class ENamedElementNormalizer implements Normalizer
	{
		protected final @NonNull ENamedElement eNamedElement;

		public ENamedElementNormalizer(@NonNull ENamedElement eNamedElement) {
			this.eNamedElement = eNamedElement;
		}

		@Override
		public void denormalize() {
			eNamedElement.setName(eNamedElement.getName().substring(1));
		}

		@Override
		public void normalize() {
			eNamedElement.setName("#" + eNamedElement.getName());
		}
	} */

	public static class EOperationsNormalizer implements Normalizer
	{
		protected final @NonNull EClass eClass;
		protected final List<EOperation> oldOrder;

		public EOperationsNormalizer(@NonNull EClass eClass) {
			this.eClass = eClass;
			this.oldOrder = new ArrayList<EOperation>(eClass.getEOperations());
		}

		@Override
		public void denormalize() {
			EList<EOperation> eOperations = eClass.getEOperations();
			eOperations.clear();
			eOperations.addAll(oldOrder);
		}

		@Override
		public void normalize() {
			EList<EOperation> eOperations = eClass.getEOperations();
			List<EOperation> newOrder = new ArrayList<EOperation>(eOperations);
			Collections.sort(newOrder, new Comparator<EOperation>()
			{
				@Override
				public int compare(EOperation o1, EOperation o2) {
					String n1 = o1.getName();
					String n2 = o2.getName();
					return n1.compareTo(n2);
				}
			}
					);
			eOperations.clear();
			eOperations.addAll(newOrder);
		}
	}

	public static class ETypedElementNormalizer implements Normalizer
	{
		protected final @NonNull ETypedElement eTypedElement;
		protected final EClassifier wasType;
		protected final int wasLower;
		protected final boolean wasOrdered;
		protected final boolean wasUnique;

		public ETypedElementNormalizer(@NonNull ETypedElement eTypedElement) {
			this.eTypedElement = eTypedElement;
			this.wasType = eTypedElement.getEType();
			this.wasLower = eTypedElement.getLowerBound();
			this.wasOrdered = eTypedElement.isOrdered();
			this.wasUnique = eTypedElement.isUnique();
		}

		@Override
		public void denormalize() {
			if (wasType == null) {
				eTypedElement.setLowerBound(wasLower);
			}
			eTypedElement.setOrdered(wasOrdered);
			eTypedElement.setUnique(wasUnique);
		}

		@Override
		public void normalize() {
			EClassifier wasType2 = wasType;
			if (wasType2 == null) {
				eTypedElement.setLowerBound(0);
			}
			else if ((wasType2 instanceof EDataType) && ElementUtil.isPrimitiveInstanceClass((EDataType) wasType2)) {
				eTypedElement.setLowerBound(1);
			}
			eTypedElement.setOrdered(true);
			eTypedElement.setUnique(true);
		}
	}

	@SuppressWarnings("null")
	protected void assertPivotIsValid(URI pivotURI) {
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet reloadResourceSet = ocl.getMetamodelManager().getASResourceSet();
		//		reloadResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new EcoreResourceFactoryImpl());
		Resource reloadedPivotResource = reloadResourceSet.getResource(pivotURI, true);
		//		MetamodelManager metamodelManager = PivotUtilInternal.getMetamodelManager(reloadedPivotResource);
		assertNoValidationErrors("Pivot reload validation problems", reloadedPivotResource);
		unloadResourceSet(reloadResourceSet);
		ocl.dispose();
	}

	@Deprecated
	public static void assertSameModel(@NonNull Resource expectedResource, @NonNull Resource actualResource) throws IOException, InterruptedException {
		TestUtil.assertSameModel(expectedResource, actualResource);
	}

	protected void doBadLoadFromString(@NonNull OCLInternal ocl, @NonNull String fileName, @NonNull String testContents, @NonNull Bag<String> expectedErrorMessages) throws Exception {
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		metamodelManager.addClassLoader(ClassUtil.nonNullState(getClass().getClassLoader()));
		InputStream inputStream = new URIConverter.ReadableInputStream(testContents, "UTF-8");
		URI libraryURI = getTestFileURI(fileName, inputStream);
		@SuppressWarnings("null")@NonNull BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().createResource(libraryURI);
		@SuppressWarnings("null")@NonNull ClassLoader classLoader = getClass().getClassLoader();
		JavaClassScope.getAdapter(xtextResource, classLoader);
		xtextResource.load(null);
		Bag<String> actualErrorMessages = new BagImpl<String>();
		for (Resource.Diagnostic actualError : xtextResource.getErrors()) {
			actualErrorMessages.add(actualError.getMessage());
		}
		String s = formatMessageDifferences(expectedErrorMessages, actualErrorMessages);
		if (s != null) {
			fail("Inconsistent load errors (expected/actual) message" + s);
		}
	}

	protected void doLoadFromString(@NonNull OCL ocl, @NonNull String fileName, @NonNull String testContents) throws Exception {
		InputStream inputStream = new URIConverter.ReadableInputStream(testContents, "UTF-8");
		URI libraryURI = getTestFileURI(fileName, inputStream);
		ResourceSet resourceSet = ocl.getResourceSet();
		BaseCSResource xtextResource = (BaseCSResource) resourceSet.createResource(libraryURI);
		xtextResource.load(null);
		assertNoResourceErrors("Load failed", xtextResource);
		Resource asResource = xtextResource.getASResource();
		assert asResource != null;
		assertNoResourceErrors("File Model", asResource);
		assertNoUnresolvedProxies("File Model", asResource);
		assertNoValidationErrors("File Model", asResource);
	}

	protected ASResource doLoadASResourceFromString(@NonNull OCL ocl, @NonNull String fileName, @NonNull String testContents) throws Exception {
		InputStream inputStream = new URIConverter.ReadableInputStream(testContents, "UTF-8");
		URI libraryURI = getTestFileURI(fileName, inputStream);
		EnvironmentFactoryInternal environmentFactory = (EnvironmentFactoryInternal) ocl.getEnvironmentFactory();
		ModelContext modelContext = new ModelContext(environmentFactory, libraryURI);
		BaseCSResource xtextResource = (BaseCSResource) modelContext.createBaseResource(null);
		assertNoResourceErrors("Load failed", xtextResource);
		ASResource asResource = xtextResource.getASResource();
		assert asResource != null;
		assertNoResourceErrors("File Model", asResource);
		assertNoUnresolvedProxies("File Model", asResource);
		assertNoValidationErrors("File Model", asResource);
		return asResource;
	}

	protected static boolean hasCorrespondingCS(Element pivotElement) {
		if (!isValidPivot(pivotElement)) {
			return false;
		}
		if (pivotElement instanceof ExpressionInOCL) {
			return false;
		}
		if ((pivotElement instanceof Variable) && (pivotElement.eContainer() instanceof ExpressionInOCL)) {
			return false;
		}
		if ((pivotElement instanceof Variable) && (pivotElement.eContainer() instanceof LoopExp)
				&& Character.isDigit((((Variable)pivotElement).getName().charAt(0)))) {
			return false;
		}
		//		if (pivotElement instanceof TemplateBinding) {
		//			return false;
		//		}
		//		if ((pivotElement instanceof TemplateableElement) && (((TemplateableElement)pivotElement).getTemplateBinding().size() > 0)) {
		//			return false;
		//		}
		return true;
	}

	//	protected static boolean hasOptionalCS(MonikeredElement pivotElement) {
	//		if ((pivotElement instanceof LetExp) && (pivotElement.eContainer() instanceof LetExp)) {
	//			return false;
	//		}
	//		return true;
	//	}

	public static boolean hasCorrespondingPivot(ModelElementCS csElement) {
		if (csElement instanceof TupleTypeCS) {
			return true;
		}
		if (csElement instanceof TuplePartCS) {		// FIXME orphanage ambiguity
			return false;
		}
		//		if (csElement instanceof TypeRefCS) {
		//			return false;
		//		}
		if (csElement instanceof InfixExpCS) {
			return false;
		}
		if (csElement instanceof NestedExpCS) {
			return false;
		}
		if (csElement instanceof PrefixExpCS) {
			return false;
		}
		if (csElement instanceof NavigatingArgCS) {
			return false;
		}
		if (csElement instanceof CurlyBracketedClauseCS) {
			return false;
		}
		if (csElement instanceof RoundBracketedClauseCS) {
			return false;
		}
		if (csElement instanceof SquareBracketedClauseCS) {
			return false;
		}
		if (NavigationUtil.isNavigationInfixExp(csElement)) {
			return false;
		}
		if (csElement instanceof CollectionTypeCS) {
			return false;
		}
		if (csElement instanceof TypeNameExpCS) {
			return false;
		}
		return true;
	}

	public static boolean hasUniqueMoniker(ModelElementCS csElement) {
		if (csElement instanceof TupleTypeCS) {
			return false;
		}
		if (csElement instanceof TypeRefCS) {
			return false;
		}
		if (csElement instanceof InfixExpCS) {
			return false;
		}
		if (csElement instanceof NestedExpCS) {
			return false;
		}
		if (csElement instanceof PrefixExpCS) {
			return false;
		}
		if (csElement instanceof CurlyBracketedClauseCS) {
			return false;
		}
		if (csElement instanceof RoundBracketedClauseCS) {
			return false;
		}
		if (csElement instanceof SquareBracketedClauseCS) {
			return false;
		}
		if (NavigationUtil.isNavigationInfixExp(csElement)) {
			return false;
		}
		if (csElement instanceof CollectionTypeCS) {
			return false;
		}
		return true;
	}

	protected static boolean isValidPivot(Element pivotElement) {
		if (pivotElement instanceof org.eclipse.ocl.pivot.Package) {
			if ((pivotElement.eContainer() == null) && PivotConstants.ORPHANAGE_NAME.equals(((NamedElement) pivotElement).getName())) {
				return false;
			}
		}
		if ((pivotElement instanceof TemplateableElement) && (((TemplateableElement)pivotElement).getOwnedBindings().size() > 0)) {
			return false;
		}
		if (pivotElement instanceof LambdaType) {
			return false;
		}
		if (pivotElement instanceof TupleType) {
			return false;
		}
		if (pivotElement instanceof Type) {
			EObject eContainer = pivotElement.eContainer();
			if ((eContainer instanceof org.eclipse.ocl.pivot.Package) && (eContainer.eContainer() == null)
					&& PivotConstants.ORPHANAGE_NAME.equals(((NamedElement) pivotElement).getName())
					&& PivotConstants.ORPHANAGE_NAME.equals(((NamedElement) eContainer).getName())) {
				return false;
			}
		}
		if ((pivotElement instanceof Property) && (pivotElement.eContainer() instanceof TupleType)) {
			return false;
		}
		if ((pivotElement instanceof VariableExp) && (pivotElement.eContainer() instanceof OperationCallExp)) {
			return false;
		}
		return true;
	}

	public @NonNull String createEcoreString(@NonNull OCL ocl, @NonNull String fileName, @NonNull String fileContent, boolean assignIds) throws IOException {
		String inputName = fileName + ".oclinecore";
		createOCLinEcoreFile(inputName, fileContent);
		URI inputURI = getTestFileURI(inputName);
		URI ecoreURI = getTestFileURI(fileName + ".ecore");
		BaseCSResource xtextResource = null;
		try {
			ResourceSet resourceSet2 = ocl.getResourceSet();
			xtextResource = ClassUtil.nonNullState((BaseCSResource) resourceSet2.getResource(inputURI, true));
			assertNoResourceErrors("Load failed", xtextResource);
			//			adapter = xtextResource.getCS2ASAdapter(null);
			Resource asResource = xtextResource.getASResource();
			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
			XMLResource ecoreResource = AS2Ecore.createResource((EnvironmentFactoryInternal) ocl.getEnvironmentFactory(), asResource, ecoreURI, null);
			assertNoResourceErrors("To Ecore errors", ecoreResource);
			if (assignIds) {
				for (TreeIterator<EObject> tit = ecoreResource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					ecoreResource.setID(eObject,  EcoreUtil.generateUUID());
				}
			}
			Writer writer = new StringWriter();
			ecoreResource.save(writer, XMIUtil.createSaveOptions());
			return ClassUtil.nonNullState(writer.toString());
		}
		finally {
			if (xtextResource != null) {
				xtextResource.dispose();
			}
		}
	}

	/**
	 * Some example files have inconsistent self references so map the URI back to
	 * the resource.
	 */
	protected void mapOwnURI(Resource resource) {
		List<EObject> contents = resource.getContents();
		if (contents.size() == 1) {
			EObject root = contents.get(0);
			if (root instanceof EPackage) {
				EPackage rootPackage = (EPackage) root;
				String nsURI = rootPackage.getNsURI();
				if (nsURI != null) {
					ResourceSet resourceSet = resource.getResourceSet();
					Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)resourceSet).getURIResourceMap();
					if (uriResourceMap == null) {
						uriResourceMap = new HashMap<URI, Resource>();
						((ResourceSetImpl)resourceSet).setURIResourceMap(uriResourceMap);
					}
					uriResourceMap.put(URI.createURI(nsURI), resource);
				}
			}
		}
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestCaseAppender.INSTANCE.install();
		TestUtil.doCompleteOCLSetup();
		TestUtil.doOCLinEcoreSetup();
		TestUtil.doOCLstdlibSetup();
	//	ResourceSet resourceSet = new ResourceSetImpl();
	//	ProjectMap.initializeURIResourceMap(resourceSet);
	//	Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
	//	if (EMFPlugin.IS_ECLIPSE_RUNNING) {
	//		uriMap.putAll(EcorePlugin.computePlatformURIMap());
	//	}
		//		for (Map.Entry<URI,URI> entry : uriMap.entrySet()) {
		//			System.out.println(entry.getKey() + " => " + entry.getValue());
		//		}
		//		URI platformOCLstdlibURI = URI.createURI(StandardDocumentAttribution.OCLSTDLIB_URI);
		//		URI projectURI = getProjectFileURI("dummy");
		//		URI projectOCLstdlibURI = URI.createURI("oclstdlib.oclstdlib").resolve(projectURI);
		//		uriMap.put(platformOCLstdlibURI, projectOCLstdlibURI);
		OCLstdlib.install();
		OCLDelegateDomain.initialize(null);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
