/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Transformer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.completeocl.CompleteOCLCodeGenerator;
import org.eclipse.ocl.examples.codegen.dynamic.OCL2JavaFileObject;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.services.BaseLinkingService;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

import junit.framework.TestCase;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class OCLCompilerTests extends XtextTestCase
{
	//	@SuppressWarnings("unused")private static ComposedEValidator makeSureRequiredBundleIsLoaded = null;

	public final static @NonNull Map<Object, Object> defaultSavingOptions;

	// FIXME use a better default strategy for the saving options
	static {
		defaultSavingOptions = new HashMap<Object, Object>();
		defaultSavingOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		defaultSavingOptions.put(XMLResource.OPTION_LINE_DELIMITER, "\n");
		defaultSavingOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		defaultSavingOptions.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		defaultSavingOptions.put(XMLResource.OPTION_LINE_WIDTH, Integer.valueOf(132));

	}

	public static @NonNull Map<Object, Object> getSaveOptions() {
		return /*TestsXMLUtil.*/defaultSavingOptions;
	}

	protected static class MyOCL extends OCLInternal
	{
		public MyOCL(@NonNull MyOCLEnvironmentFactory environmentFactory) {
			super(environmentFactory);
		}

		private Class<? extends Transformer> compileTransformation(@NonNull File explicitClassPath, @NonNull CompleteOCLCodeGenerator cg) throws Exception {
			String qualifiedClassName = cg.getQualifiedName();
			String javaCodeSource = cg.generateClassFile();
			String string = explicitClassPath.toString();
			assert string != null;
			OCL2JavaFileObject.saveClass(string, qualifiedClassName, javaCodeSource);
			@SuppressWarnings("unchecked")
			Class<? extends Transformer> txClass = (Class<? extends Transformer>) OCL2JavaFileObject.loadExplicitClass(explicitClassPath, qualifiedClassName);
			return txClass;
		}

		//		public @NonNull Transformer createTransformer(@NonNull Class<? extends Transformer> txClass) throws ReflectiveOperationException {
		//			QVTiTransformationExecutor executor = new QVTiTransformationExecutor(getEnvironmentFactory(), txClass);
		//			return executor.getTransformer();
		//		}

		public @NonNull Resource doLoad_ConcreteWithOCL(@NonNull URI inputURI) throws IOException {
			URI cstURI = inputURI.appendFileExtension("xmi");//TestUtil.getFileURI(getClass(), cstName);
			URI pivotURI = inputURI.appendFileExtension("oclas");//TestUtil.getFileURI(getClass(), pivotName);
			BaseCSResource xtextResource = (BaseCSResource) getResourceSet().getResource(inputURI, true);
			assert xtextResource != null;
			assertNoResourceErrors("Load failed", xtextResource);
			ASResource asResource = xtextResource.getASResource();
			//			assertNoUnresolvedProxies("Unresolved proxies", xtextResource);
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validate()");
			assertNoValidationErrors("Validation errors", xtextResource.getContents().get(0));
			//			System.out.println(Long.toString(System.currentTimeMillis() - startTime) + " validated()");
			TestUtil.saveAsXMI(xtextResource, cstURI, getSaveOptions());
			asResource.setURI(pivotURI);

			TestUtil.doCompleteOCLSetup();
			URI oclURI = ClassUtil.nonNullState(URI.createPlatformResourceURI("/org.eclipse.qvtd.pivot.qvtimperative/model/QVTimperative.ocl", true));
			//			CompleteOCLEObjectValidator completeOCLEObjectValidator1 = new CompleteOCLEObjectValidator(QVTimperativePackage.eINSTANCE, oclURI, metamodelManager);
			//			CompleteOCLEObjectValidator completeOCLEObjectValidator2 = new CompleteOCLEObjectValidator(ClassUtil.nonNullState(QVTcoreBasePackage.eINSTANCE), oclURI, getEnvironmentFactory());
			//			CompleteOCLEObjectValidator completeOCLEObjectValidator3 = new CompleteOCLEObjectValidator(QVTbasePackage.eINSTANCE, oclURI, metamodelManager);
			//			completeOCLEObjectValidator1.initialize();
			//			completeOCLEObjectValidator2.initialize();
			//			completeOCLEObjectValidator3.initialize();
			//			PivotEObjectValidator.install(ClassUtil.nonNullState(asResource.getResourceSet()), getEnvironmentFactory());
			//			PivotEObjectValidator.install(ClassUtil.nonNullState(QVTbasePackage.eINSTANCE), null);
			//			PivotEObjectValidator.install(ClassUtil.nonNullState(QVTcoreBasePackage.eINSTANCE), null);
			//			PivotEObjectValidator.install(ClassUtil.nonNullState(QVTimperativePackage.eINSTANCE), null);

			assertNoValidationErrors("Pivot validation errors", asResource.getContents().get(0));
			asResource.save(getSaveOptions());
			return asResource;
		}

		protected @NonNull Class<? extends Transformer> generateCode(@NonNull Model asDocument, @NonNull String rootPackageNames) throws Exception {
			CompleteOCLCodeGenerator cg = new CompleteOCLCodeGenerator(getEnvironmentFactory(), asDocument, rootPackageNames);
			//			QVTiCodeGenOptions options = cg.getOptions();
			//			options.setUseNullAnnotations(true);
			//			options.setPackagePrefix("cg_qvtimperative_tests");
			cg.generateClassFile();
			cg.saveSourceFile("../org.eclipse.ocl.examples.xtext.tests/src-gen/");
			Class<? extends Transformer> txClass = compileTransformation(new File("../org.eclipse.ocl.examples.xtext.tests/bin"), cg);
			if (txClass == null) {
				TestCase.fail("Failed to compile document");
				throw new UnsupportedOperationException();
			}
			return txClass;
		}

		@Override
		public @NonNull MyOCLEnvironmentFactory getEnvironmentFactory() {
			return (MyOCLEnvironmentFactory) super.getEnvironmentFactory();
		}

		public @NonNull Model loadDocument(@NonNull URI documentURI/*, @NonNull URI genModelURI*/) throws Exception {
			OCLstdlibTables.LIBRARY.getClass();		// Ensure coherent initialization
			ResourceSet resourceSet = getResourceSet();
			//			resourceSet.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
			MetamodelManagerInternal metamodelManager = getMetamodelManager();
			getEnvironmentFactory().configureLoadFirstStrategy();
			//			Resource genResource = resourceSet.getResource(genModelURI, true);
			//			for (EObject eObject : genResource.getContents()) {
			//				if (eObject instanceof GenModel) {
			//					GenModel genModel = (GenModel)eObject;
			//					genModel.reconcile();
			//					metamodelManager.addGenModel(genModel);
			//				}
			//			}
			Resource resource = doLoad_ConcreteWithOCL(documentURI);
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof Model) {
					return (Model)eObject;
				}
			}
			TestCase.fail("Failed to load '" + documentURI /* + "', '" + genModelURI*/ + "'");
			throw new UnsupportedOperationException();	// Never gets here
		}
	}

	protected static class MyOCLEnvironmentFactory extends PivotEnvironmentFactory
	{
		public MyOCLEnvironmentFactory(@NonNull ProjectManager projectMap, @Nullable ResourceSet externalResourceSet) {
			super(projectMap, externalResourceSet);
			setEvaluationTracingEnabled(true);
		}
	}

	protected @NonNull MyOCL createMyOCL() {
		return new MyOCL(new MyOCLEnvironmentFactory(getProjectMap(), null));
	}

	//	protected @NonNull URI getProjectFileURI(String classRelativeName) {
	//		assert classRelativeName != null;
	//		return TestUtil.getFileURI(QVTiCompilerTests.class, classRelativeName);
	//	}

	@Override
	protected void setUp() throws Exception {
		BaseLinkingService.DEBUG_RETRY.setState(true);
		TestUtil.doCompleteOCLSetup();
		super.setUp();
	}

	public void testCG_Fibonacci_ocl() throws Exception {
		//		AbstractTransformer.INVOCATIONS.setState(true);
		MyOCL myOCL = createMyOCL();
		//		URI genModelURI = null; //getProjectFileURI("Tree2TallTree/Tree2TallTree.genmodel");
		URI documentURI = getProjectFileURI("Fibonacci.ocl");
		//		URI inputModelURI = getProjectFileURI("Tree2TallTree/Tree.xmi");
		//		URI outputModelURI = getProjectFileURI("Tree2TallTree/Tree2TallTree.xmi");
		//		URI referenceModelURI = getProjectFileURI("Tree2TallTree/TallTreeValidate.xmi");
		Model asModel = myOCL.loadDocument(documentURI); //, genModelURI);
		Class<? extends Transformer> txClass = myOCL.generateCode(asModel, "fibonacci");
		//		Transformer tx = myOCL.createTransformer(txClass);
		//		myOCL.loadInput(tx, "tree", inputModelURI);
		//		tx.run();
		//		myOCL.saveOutput(tx, "talltree", outputModelURI, referenceModelURI, null);
		myOCL.dispose();
	}
}
