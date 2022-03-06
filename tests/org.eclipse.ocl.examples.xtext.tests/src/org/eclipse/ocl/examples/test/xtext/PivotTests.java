/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.DebugTimestamp;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.BaseCS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclinecore.as2cs.OCLinEcoreAS2CS;
import org.eclipse.ocl.xtext.oclstdlib.cs2as.OCLstdlibCS2AS;
import org.eclipse.ocl.xtext.oclstdlib.scoping.JavaClassScope;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;

/**
 * Tests.
 */
public class PivotTests extends XtextTestCase
{
	public static class Checker extends BaseCS2AS
	{
		private Checker(@NonNull BaseCS2AS aConverter) {
			super(aConverter);
		}

		public void assertContainedBy(@NonNull CS2AS thatConverter) {
			/*			Map<String, MonikeredElement> thisMoniker2asMap = metamodelManager.computeMoniker2asMap(getPivotResources());
			Map<String, MonikeredElement> thatMoniker2asMap = metamodelManager.computeMoniker2asMap(thatConverter.getPivotResources());
			List<String> theseMonikers = new ArrayList<String>(thisMoniker2asMap.keySet());
			List<String> thoseMonikers = new ArrayList<String>(thatMoniker2asMap.keySet());
			Collections.sort(theseMonikers);
			Collections.sort(thoseMonikers);
			for (String moniker : thisMoniker2asMap.keySet()) {
				MonikeredElement thisPivotElement = thisMoniker2asMap.get(moniker);
				MonikeredElement thatPivotElement = thatMoniker2asMap.get(moniker);
				if (isValidPivot(thisPivotElement) && isValidPivot(thatPivotElement)) {
					assertEquals("Preserved pivot", thisPivotElement, thatPivotElement);
				}
			} */
		}

		public void assertSameContents() { // WIP
			/*			Map<String, MonikeredElement> moniker2asMap = metamodelManager.computeMoniker2asMap(getPivotResources());
			Collection<? extends Resource> csResources = cs2asResourceMap.keySet();
			for (Resource csResource : csResources) {
				for (TreeIterator<EObject> tit = csResource.getAllContents(); tit.hasNext(); ) {
					ElementCS csElement = (ElementCS) tit.next();
					if (csElement instanceof MonikeredElementCS) { //&& !(csElement instanceof TemplateBindingCS) && !(csElement instanceof TemplateParameterSubstitutionCS)) {
						MonikeredElementCS csMonikeredElement = (MonikeredElementCS)csElement;
						if (hasCorrespondingPivot(csMonikeredElement)) {
							String csMoniker = CS2Moniker.toString(csMonikeredElement);
							MonikeredElement actualPivotElement = (MonikeredElement) csMonikeredElement.getPivot();
							if (actualPivotElement == null) {
								@SuppressWarnings("unused")
								MonikeredElement pivotElement = moniker2asMap.get(csMoniker);
								fail("Missing pivot for '" + csMoniker + "'");
							}
							else {
								String actualPivotMoniker = actualPivotElement.getMoniker();
								assertEquals("Moniker mismatch", csMoniker, actualPivotMoniker);
								MonikeredElement expectedPivotElement = moniker2asMap.get(csMoniker);
								assertEquals("Element mismatch", expectedPivotElement, actualPivotElement);
							}
						}
					}
				}
			} */
			/*			Collection<? extends Resource> pivotResources = cs2asResourceMap.values();
			Map<String, MonikeredElementCS> moniker2CSMap = computeMoniker2CSMap(csResources);
			for (Resource asResource : pivotResources) {
				for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
					Element pivotElement = (Element) tit.next();
					if (pivotElement instanceof MonikeredElement) { //&& !(pivotElement instanceof TemplateSignature) && !(pivotElement instanceof TemplateParameterSubstitution) && !(pivotElement instanceof TemplateParameter)) {
						MonikeredElement pivotNameableElement = (MonikeredElement)pivotElement;
						if (hasCorrespondingCS(pivotNameableElement)) {
							String pivotMoniker = pivotNameableElement.getMoniker();
							MonikeredElementCS csNameableElement = moniker2CSMap.get(pivotMoniker);
							MonikeredElement pivotCSElement = (MonikeredElement) csNameableElement.getPivot();
							if (pivotCSElement == null) {
								fail("Missing pivot for '" + pivotElement + "'");
							}
							else {
								assertEquals("Moniker mismatch", pivotMoniker, CS2Moniker.toString(csNameableElement));
								assertEquals("Element mismatch", pivotElement, pivotCSElement);
							}
						}
					}
				}
			} */
		}

		/*		public List<EObject> getRoots() {
			List<EObject> roots = new ArrayList<EObject>();
			for (Map.Entry<? extends Resource, ? extends Resource> entry : cs2asResourceMap.entrySet()) {
				roots.addAll(entry.getKey().getContents());
				roots.addAll(entry.getValue().getContents());
			}
			return roots;
		} */
	}

	/*	public static class Damager extends Checker
	{
		private Damager(CS2AS aConverter) {
			super(aConverter);
		}

		public List<MonikeredElement> chooseVictims() {
			List<String> pivotKeys = new ArrayList<String>(moniker2asMap.keySet());
			List<MonikeredElement> pivotElements = new ArrayList<MonikeredElement>();
			Collections.sort(pivotKeys);
			int iMax = pivotKeys.size();
			int stepSize = Math.max((int) Math.sqrt(iMax), 1);
			for (int i = iMax-1; i > 0; i -= stepSize) {
//				String moniker = pivotKeys.remove(i);
				String moniker = pivotKeys.get(i);
				MonikeredElement pivotElement = moniker2asMap.get(moniker);
				pivotElements.add(pivotElement);
			}
			return pivotElements;
		}

		public void removeAll(Collection<? extends MonikeredElement> pivotElements) {
			Collection<EObject> roots = getRoots();
			Map<EObject, Collection<EStructuralFeature.Setting>> xrefMap = EcoreUtil.CrossReferencer.find(roots);
			for (MonikeredElement pivotElement : pivotElements) {
				String moniker = pivotElement.getMoniker();
				logger.trace("Damage " + pivotElement.eClass().getName() + " : " + moniker); //$NON-NLS-1$
				moniker2asMap.remove(moniker);
				EObject eContainer = pivotElement.eContainer();
				if (eContainer != null) {
					EStructuralFeature eContainingFeature = pivotElement.eContainingFeature();
					if (eContainingFeature.isMany()) {
						((List<?>) eContainer.eGet(eContainingFeature)).remove(pivotElement);
					}
					else {
						eContainer.eSet(eContainingFeature, null);
					}
				}
				for (EStructuralFeature.Setting setting : xrefMap.get(pivotElement)) {
					setting.unset();	// Invalid -- must reinstate Xtext proxies
				}
			}
		}
	} */

	@SuppressWarnings("null")
	public BaseCSResource doLoadOCLstdlib(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		ResourceSet resourceSet = ocl.getResourceSet();
		//		CS2ASResourceSetAdapter.getAdapter(resourceSet, metamodelManager);
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		String extension = inputURI.fileExtension();
		String stem = inputURI.trimFileExtension().lastSegment();
		String outputName = stem + "." + extension + ".xmi";
		String output2Name = stem + ".saved." + extension;
		URI outputURI = getTestFileURI(outputName);
		URI output2URI = getTestFileURI(output2Name);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
		BaseCSResource xtextResource = (BaseCSResource) resourceSet.createResource(inputURI);
		JavaClassScope.getAdapter(xtextResource, getClass().getClassLoader());
		ocl.getEnvironmentFactory().adapt(xtextResource);
		xtextResource.load(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
		assertNoResourceErrors("Load failed", xtextResource);
		//		assertNoCSErrors("Load failed", xtextResource);
		//		CSAliasCreator.refreshPackageAliases(xtextResource);
		//		CS2ASResourceAdapter adapter = CS2ASResourceAdapter.getAdapter(xtextResource);
		//		Resource asResource = adapter.getPivotResource(xtextResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
		assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		//		assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		xtextResource.setURI(output2URI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		DebugTimestamp debugTimestamp = new DebugTimestamp(xtextResource.getURI().toString());
		xtextResource.save(XMIUtil.createSaveOptions());
		debugTimestamp.log("Serialization save done");
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		assertNoResourceErrors("Save failed", xtextResource);
		Resource xmiResource = resourceSet.createResource(outputURI);
		xmiResource.getContents().addAll(xtextResource.getContents());
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		xmiResource.save(XMIUtil.createSaveOptions());
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		assertNoResourceErrors("Save failed", xmiResource);
		xtextResource.getContents().addAll(xmiResource.getContents());
		return xtextResource;
	}

	protected void doPivotTestOCLstdlib(@NonNull OCL ocl, @NonNull URI inputURI) throws IOException {
		URI pivotURI = getTestFileURI(ClassUtil.nonNullState(inputURI.trimFileExtension().appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION).lastSegment()));
		BaseCSResource csResource = doLoadOCLstdlib(ocl, inputURI);
		//
		//	Create Pivot model from CS
		//
		CS2AS cs2as = ClassUtil.nonNullState(csResource.findCS2AS());
		ASResource asResource = cs2as.getASResource();
		OCLstdlibCS2AS creator = (OCLstdlibCS2AS) cs2as;
		//
		//	Check that Pivot model is ready for damage
		//
		new Checker(creator).assertContainedBy(creator);
		//
		//	Save Pivot Model for manual inspection
		//
		assertNoValidationErrors("Pivot validation problems", asResource);
		if (asResource.isSaveable()) {
			URI savedPivotURI = asResource.getURI();
			asResource.setURI(pivotURI);
			asResource.save(XMIUtil.createSaveOptions(asResource));
			asResource.setURI(savedPivotURI);
		}
		//
		//	Check CS and Pivot have consistent content
		//
		new Checker(creator).assertSameContents();
		//
		//	Update Pivot model from unchanged CS
		//
		OCLstdlibCS2AS updater = new OCLstdlibCS2AS(creator);
		updater.update(new ListBasedDiagnosticConsumer());
		new Checker(creator).assertSameContents();
		//
		// Damage the Pivot model and update to repair.
		//
		//		Damager damager = damagePivot(creator);
		//		damager.assertContainedBy(updater);
		//		damager.update();
		//		damager.assertSameContents();
		//
		ocl.dispose();
		assertPivotIsValid(pivotURI);
	}

	@SuppressWarnings("null")
	public void doPivotTestEcore(@NonNull URI inputURI) throws IOException {
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		//		long startTime = System.currentTimeMillis();
		//		System.out.println("Start at " + startTime);
		//		String libraryName = "oclstdlib.pivot";
		//		String libraryName = "oclstdlib.oclstdlib";
		//		URI libraryURI = getProjectFileURI(libraryName);
		//		BaseCSResource xtextLibraryResource = (BaseCSResource) resourceSet.getResource(libraryURI, true);
		//		CS2ASResourceAdapter adapter = CS2ASResourceAdapter.refreshPivotMappings(xtextLibraryResource, null);
		//		Resource asResource = adapter.getPivotResource(xtextLibraryResource);
		//		asResourceSet.getResource(libraryURI, true);
		String csName = inputURI.trimFileExtension().lastSegment() + ".ecore.cs";
		URI csURI = getTestFileURI(csName);
		//		URI output2URI = getProjectFileURI(output2Name);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " getResource()");
		Resource ecoreResource = ocl.getResourceSet().getResource(inputURI, true);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " gotResource()");
		assertNoResourceErrors("Load failed", ecoreResource);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " resolveProxies()");
		assertNoUnresolvedProxies("Unresolved proxies", ecoreResource);
		//		EcoreAliasCreator.createPackageAliases(ecoreResource);
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, metamodelManager.getEnvironmentFactory());
		Model pivotModel = ecore2as.getASModel();


		//		checkPivotMonikers(pivotModel);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
		//		assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
		//		xtextResource.setURI(output2URI);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		//		xtextResource.save(null);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//		assertNoResourceErrors("Save failed", xtextResource.getErrors());
		@SuppressWarnings("unused")
		ASResource asResource = (ASResource) pivotModel.eResource();
		//		CS2ASAliasCreator.createPackageAliases(asResource);
		//		Resource asResource = resourceSet.createResource(outputURI);
		//		asResource.getContents().add(pivotModel);
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " save()");
		for (Resource pResource : asResourceSet.getResources()) {
			URI uri = pResource.getURI();
			if (uri.isFile()) {
				Map<Object, Object> saveOptions = XMIUtil.createSaveOptions();
				saveOptions.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
				saveOptions.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
				pResource.save(saveOptions);
			}
			assertNoResourceErrors("Pivot Save failed", pResource);
		}
		//		System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " saved()");
		//		return asResource;
		ResourceSetImpl csResourceSet = new ResourceSetImpl();
		csResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cs", new EcoreResourceFactoryImpl());
		csResourceSet.getPackageRegistry().put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		Resource csResource = csResourceSet.createResource(csURI);
		Map<BaseCSResource, ASResource> cs2asResourceMap = new HashMap<BaseCSResource, ASResource>();
		//		cs2asResourceMap.put(csResource, asResource);
		AS2CS as2cs = new OCLinEcoreAS2CS(cs2asResourceMap, metamodelManager.getEnvironmentFactory());
		as2cs.update();
		csResource.save(XMIUtil.createSaveOptions());
		ocl.dispose();
	}

	//	public Damager damagePivot(CS2AS aConverter) {
	//		Damager damager = new Damager(aConverter);
	//		List<MonikeredElement> pivotElements = damager.chooseVictims();
	//		damager.removeAll(pivotElements);
	//		return damager;
	//	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("trace", new EcoreResourceFactoryImpl());
	}

	//	public void testPivot_mini_oclstdlib() throws IOException, InterruptedException {
	//		doPivotTestOCLstdlib("mini");
	//	}

	//	public void testPivot_midi_oclstdlib() throws IOException, InterruptedException {
	//		doPivotTestOCLstdlib("midi");
	//	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPivot_oclstdlib_oclstdlib() throws IOException, InterruptedException {
		OCL ocl = OCL.newInstance(getProjectMap());
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		getTestFileURI("oclstdlib.oclas", ocl, getTestModelURI("models/oclstdlib/oclstdlib.oclas"));
		URI testFileURI = getTestFileURI("oclstdlib.oclstdlib", ocl, getTestModelURI("models/oclstdlib/oclstdlib.oclstdlib"));
		doPivotTestOCLstdlib(ocl, testFileURI);
		ocl.dispose();
	}

	//	public void testPivot_temp_oclstdlib() throws IOException, InterruptedException {
	//		doPivotTestOCLstdlib("temp");
	//	}

	//	public void testPivot_temp2_oclstdlib() throws IOException, InterruptedException {
	//		doPivotTestOCLstdlib("temp2");
	//	}

	public void testPivot_Ecore_ecore() throws IOException, InterruptedException {
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doPivotTestEcore(getTestModelURI("models/ecore/Ecore.ecore"));
	}

	public void testPivot_Names_ecore() throws IOException, InterruptedException {
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		doPivotTestEcore(getTestModelURI("models/ecore/Names.ecore"));
	}

	//	public void testPivot_Temp_ecore() throws IOException, InterruptedException {
	//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
	//		doPivotTestEcore("Temp");
	//	}
}
