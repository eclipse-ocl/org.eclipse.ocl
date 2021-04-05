/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileObject;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.mwe.core.ConfigurationException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.dynamic.ExplicitClassLoader;
import org.eclipse.ocl.examples.codegen.dynamic.JavaClasspath;
import org.eclipse.ocl.examples.codegen.dynamic.JavaFileUtil;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.examples.pivot.tests.PivotTestSuite;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.evaluation.AbstractModelManager;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractExecutor;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorManager;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.AbstractEnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.ecore.importer.UMLImporter;
import org.eclipse.uml2.uml.editor.presentation.UMLEditor;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.util.EmfFormatter;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a
 * result.
 */
public class UsageTests extends PivotTestSuite// XtextTestCase
{
	private static final class TestUMLImporter extends UMLImporter
	{
		protected final EPackage.@NonNull Registry packageRegistry;

		public TestUMLImporter(EPackage.@NonNull Registry packageRegistry) {
			this.packageRegistry = packageRegistry;
		}

		@Override
		public ResourceSet createResourceSet() {
			ResourceSet umlResourceSet = super.createResourceSet();
			UMLResourcesUtil.init(umlResourceSet);
			umlResourceSet.getPackageRegistry().putAll(packageRegistry);
			return umlResourceSet;
		}

		public void reloadGenModel(@NonNull Path genModelPath) throws Exception {
			defineOriginalGenModelPath(genModelPath);
			BasicMonitor monitor = new BasicMonitor();
			Diagnostic diagnostic = computeEPackages(monitor);
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				String s = PivotUtil.formatDiagnostics(diagnostic, "\n");
				fail("Reload failure" + s);
			}
			prepareGenModelAndEPackages(monitor);
			Map<?, ?> saveOptions = getGenModelSaveOptions();
			for (Resource resource : computeResourcesToBeSaved()) {
				resource.save(saveOptions);
			}
		}
	}

	public Logger log;

	/**
	 * Checks all resources in a resource set for any errors or warnings.
	 *
	 * @param resourceSet
	 * @throws ConfigurationException
	 *             if any error present
	 */
	public void checkResourceSet(@NonNull ResourceSet resourceSet)
			throws ConfigurationException {
		int errorCount = 0;
		for (Resource aResource : resourceSet.getResources()) {
			List<Resource.Diagnostic> errors = aResource.getErrors();
			if (errors.size() > 0) {
				for (Resource.Diagnostic error : errors) {
					if (error instanceof ExceptionDiagnostic) {
						log.error("Error for '" + aResource.getURI() + "'",
							((ExceptionDiagnostic) error).getException());
					} else {
						log.error(error + " for '" + aResource.getURI() + "'");
					}
					errorCount++;
				}
			}
			List<Resource.Diagnostic> warnings = aResource.getWarnings();
			if (warnings.size() > 0) {
				for (Resource.Diagnostic warning : warnings) {
					if (warning instanceof ExceptionDiagnostic) {
						log.warn("Warning for '" + aResource.getURI() + "'",
							((ExceptionDiagnostic) warning).getException());
					} else {
						log.warn(warning + " for '" + aResource.getURI() + "'");
					}
				}
			}
		}
		if (errorCount > 0) {
			throw new RuntimeException(errorCount + " errors in ResourceSet");
		}
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), getTestProjectManager(), null);
	}

	/**
	 * Return the URI of a file in the test harness models folder.
	 */
	protected @NonNull URI getModelsURI(@NonNull String filePath) {
		return URI.createPlatformResourceURI(getTestBundleName() + "/models/" + filePath, true);
	}

	@Override
	protected @NonNull File getProjectFile() {
		return getTestProject().getFile();
	}

	@Override
	protected @NonNull URI getProjectFileURI(@NonNull String referenceName) {
		return getTestFileURI(referenceName);
	}

	@Override
	protected void setUp() throws Exception {
	//	TEST_START.setState(true);
	//	AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		super.setUp();
		log = Logger.getLogger(UsageTests.class);
		// AcceleoNature.class.getName(); // Pull in the plugin for Hudson
		TestUtil.doOCLinEcoreSetup();
		configurePlatformResources();
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		//			.put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown() throws Exception {
	//	ThreadLocalExecutor.waitForGC();
		log = null;
		uninstall();
		super.tearDown();
	}

	public static @NonNull String createClassPath(@NonNull List<String> projectNames) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String pathSeparator = null;
		StringBuilder s = new StringBuilder();
		for (String projectName : projectNames) {
			String projectPath = null;
			IProject project = root.getProject(projectName);
			if (project != null) {
				IPath location = project.getLocation();
				if (location != null) {
					projectPath = location.toString() + "/";
				}
			}
			if (projectPath == null) {
				Bundle bundle = Platform.getBundle(projectName);
				if (bundle != null) {
					projectPath = bundle.getLocation();
				}
			}

			if (projectPath != null) {
				if (projectPath.startsWith("reference:")) {
					projectPath = projectPath.substring(10);
				}
				URI uri = URI.createURI(projectPath);
				if (uri.isFile()) {
					String fileString = uri.toFileString();
					assert fileString != null;
					projectPath =  fileString.replace("\\", "/");
				}
				assert projectPath != null;
				if (projectPath.endsWith("/")) {
					projectPath = projectPath + JavaFileUtil.TEST_BIN_FOLDER_NAME;
				}
				if (pathSeparator != null) {
					s.append(pathSeparator);
				}
				else {
					pathSeparator = System.getProperty("path.separator");
				}
				s.append(projectPath);
			}
		}
		return s.toString();
	}

	public @NonNull String createEcoreGenModelContent(@NonNull String fileName, @Nullable Map<@NonNull String, @Nullable String> genOptions) throws Exception {
		String interfacePackageSuffix = genOptions != null ? genOptions.get("interfacePackageSuffix") : null;
		String metaDataPackageSuffix = genOptions != null ? genOptions.get("metaDataPackageSuffix") : null;
		String usedGenPackages = genOptions != null ? genOptions.get("usedGenPackages") : null;
		String modelDirectory = genOptions != null ? genOptions.get("modelDirectory") : null;
		String dynamicTemplates = genOptions != null ? genOptions.get("dynamicTemplates") : null;
		String templateDirectory = genOptions != null ? genOptions.get("templateDirectory") : null;
		if (modelDirectory == null) {
			modelDirectory = getTestProject().getName() + "/" + JavaFileUtil.TEST_SRC_FOLDER_NAME;
		}
		StringBuilder s = new StringBuilder();
		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		s.append("<genmodel:GenModel xmi:version=\"2.0\"\n");
		s.append("    xmlns:xmi=\"http://www.omg.org/XMI\"\n");
		s.append("    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n");
		s.append("    xmlns:genmodel=\"http://www.eclipse.org/emf/2002/GenModel\"\n");
		s.append("    modelDirectory=\"/" + modelDirectory + "\"\n");
		s.append("    modelPluginID=\"" + fileName + "\"\n");
		s.append("    modelName=\"" + fileName + "\"\n");
		s.append("    importerID=\"org.eclipse.emf.importer.ecore\"\n");
		s.append("    complianceLevel=\"8.0\"\n");
		s.append("    operationReflection=\"true\"\n");
		s.append("    copyrightFields=\"false\"\n");
		s.append("    bundleManifest=\"false\"\n");
		s.append("    pluginKey=\"\"\n");
		if (dynamicTemplates != null) {
			s.append("    dynamicTemplates=\"" + dynamicTemplates + "\"\n");
		}
		if (templateDirectory != null) {
			s.append("    templateDirectory=\"" + templateDirectory + "\"\n");
		}
		s.append("    usedGenPackages=\"");
		if (usedGenPackages != null) {
			s.append(usedGenPackages + " ");
		}
		s.append("platform:/resource/org.eclipse.ocl.pivot/model/oclstdlib.genmodel#//oclstdlib\"\n");
		s.append("    updateClasspath=\"false\">\n");
		s.append("  <genAnnotations source=\"http://www.eclipse.org/OCL/GenModel\">\n");
		s.append("    <details key=\"Use Delegates\" value=\"false\"/>\n");
		s.append("    <details key=\"Use Null Annotations\" value=\"true\"/>\n");
		s.append("  </genAnnotations>\n");
		s.append("  <foreignModel>" + fileName + ".ecore</foreignModel>\n");
		s.append("  <genPackages prefix=\"" + fileName + "\"\n");
		s.append("    disposableProviderFactory=\"true\"\n");
		s.append("    ecorePackage=\"" + fileName + ".ecore#/\"\n");
		if (interfacePackageSuffix != null) {
			s.append("    interfacePackageSuffix=\"" + interfacePackageSuffix + "\"\n");
		}
		if (metaDataPackageSuffix != null) {
			s.append("    metaDataPackageSuffix=\"" + metaDataPackageSuffix + "\"\n");
		}
		s.append("  />\n");
		s.append("</genmodel:GenModel>\n");
		s.append("\n");;
		return s.toString();
	}

	public void createManifestFile() throws IOException {
		String bundleName = "_" + getTestProject().getName();
		TestFile testFile = getTestProject().getOutputFile("META-INF/MANIFEST.MF");
		File file = testFile.getFile();
		Writer writer = new FileWriter(file);
		writer.append("Manifest-Version: 1.0\n");
		writer.append("Bundle-ManifestVersion: 2\n");
		writer.append("Bundle-Name: " + bundleName + "\n");
		writer.append("Bundle-SymbolicName: " + bundleName + ";singleton:=true\n");
		writer.append("Bundle-Version: 1.0.0.qualifier\n");
		writer.close();
	}

	protected @NonNull URI createModels(@NonNull String testFileStem, @Nullable String oclinecoreFile, @NonNull String genmodelContent)
			throws Exception {
		OCL ocl2 = OCL.newInstance(getTestProjectManager());
		try {
			if (oclinecoreFile != null) {
				createEcoreFile(ocl2, testFileStem, oclinecoreFile);
			}
			URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".genmodel"), genmodelContent);
			// System.out.println("Generating Ecore Model using '" + genModelURI + "'");
			return genModelURI;
		}
		finally {
			ocl2.dispose();
		}
	}

	public @NonNull URI createTestFileWithContent(@NonNull TestFile testFile, String fileContent) throws IOException {
		File file = testFile.getFile();
		Writer writer = new FileWriter(file);
		writer.append(fileContent);
		writer.close();
		return testFile.getURI();
	}

	/**
	 * Create a seed *.ecore from the *.uml profile with correct genPackage declarations s that a
	 * reload / reconcile corrects the genClasses etc.
	 */
	public @NonNull String createUMLEcoreModelContent(@NonNull Resource umlProfileResource) throws Exception {
		StringBuilder s = new StringBuilder();
		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		s.append("<xmi:XMI xmi:version=\"2.0\"\n");
		s.append("    xmlns:xmi=\"http://www.omg.org/XMI\"\n");
		s.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		s.append("    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\">\n");
		for (EObject eRootObject : umlProfileResource.getContents()) {
			if (eRootObject instanceof Model) {
				for (org.eclipse.uml2.uml.Package umlPackage : ((Model)eRootObject).getNestedPackages()) {
					assert umlPackage != null;
					appendEcorePackage(s, umlPackage);
				}
			}
			else if (eRootObject instanceof org.eclipse.uml2.uml.Package) {
				appendEcorePackage(s, (org.eclipse.uml2.uml.Package)eRootObject);
			}
		}
		s.append("</xmi:XMI>\n");
		return s.toString();
	}

	private void appendEcorePackage(@NonNull StringBuilder s, org.eclipse.uml2.uml.@NonNull Package umlPackage) {
		Stereotype ecoreStereotype = umlPackage.getAppliedStereotype("Ecore::EPackage");
		String basePackage = (String)umlPackage.getValue(ecoreStereotype, "basePackage");
		String nsPrefix = (String)umlPackage.getValue(ecoreStereotype, "nsPrefix");
		String nsURI = (String)umlPackage.getValue(ecoreStereotype, "nsURI");
		String packageName = (String)umlPackage.getValue(ecoreStereotype, "packageName");
		String prefix = (String)umlPackage.getValue(ecoreStereotype, "prefix");
		s.append("  <ecore:EPackage");
		s.append(" name=\"" + (packageName != null ? packageName : umlPackage.getName()) + "\"");
		if (basePackage != null) {
			s.append(" basePackage=\"" + basePackage + "\"");
		}
		if (nsPrefix != null) {
			s.append(" nsPrefix=\"" + nsPrefix + "\"");
		}
		if (nsURI != null) {
			s.append(" nsURI=\"" + nsURI + "\"");
		}
		if (prefix != null) {
			s.append(" prefix=\"" + prefix + "\"");
		}
		s.append(">\n");
		s.append("    <eClassifiers xsi:type=\"ecore:EClass\" name=\"Dummy\"/>\n");
		s.append("  </ecore:EPackage>\n");
	}

	public @NonNull String createUMLGenModelContent(@NonNull Resource umlProfileResource, @NonNull String fileName, @Nullable Map<@NonNull String, @Nullable String> genOptions) throws Exception {
		String interfacePackageSuffix = genOptions != null ? genOptions.get("interfacePackageSuffix") : null;
		String metaDataPackageSuffix = genOptions != null ? genOptions.get("metaDataPackageSuffix") : null;
		String usedGenPackages = genOptions != null ? genOptions.get("usedGenPackages") : null;
		String modelDirectory = genOptions != null ? genOptions.get("modelDirectory") : null;
		String dynamicTemplates = genOptions != null ? genOptions.get("dynamicTemplates") : null;
		String templateDirectory = genOptions != null ? genOptions.get("templateDirectory") : null;
		if (modelDirectory == null) {
			modelDirectory = getTestProject().getName() + "/" + JavaFileUtil.TEST_SRC_FOLDER_NAME;
		}
		StringBuilder s = new StringBuilder();
		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		s.append("<genmodel:GenModel xmi:version=\"2.0\"\n");
		s.append("    xmlns:xmi=\"http://www.omg.org/XMI\"\n");
		s.append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		s.append("    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n");
		s.append("    xmlns:genmodel=\"http://www.eclipse.org/uml2/2.2.0/GenModel\"\n");
		s.append("    modelDirectory=\"/" + modelDirectory + "\"\n");
		s.append("    modelPluginID=\"" + fileName + "\"\n");
		s.append("    modelName=\"" + fileName + "\"\n");
		s.append("    importerID=\"org.eclipse.uml2.uml.ecore.importer\"\n");
		s.append("    complianceLevel=\"8.0\"\n");
		s.append("    operationReflection=\"true\"\n");
		s.append("    copyrightFields=\"false\"\n");
		s.append("    bundleManifest=\"false\"\n");
		s.append("    pluginKey=\"\"\n");
		if (dynamicTemplates != null) {
			s.append("    dynamicTemplates=\"" + dynamicTemplates + "\"\n");
		}
		if (templateDirectory != null) {
			s.append("    templateDirectory=\"" + templateDirectory + "\"\n");
		}
		s.append("    updateClasspath=\"false\">\n");
		s.append("  <genAnnotations source=\"http://www.eclipse.org/emf/2002/GenModel/importer/org.eclipse.uml2.uml.ecore.importer\">\n");
		s.append("    <details key=\"OPPOSITE_ROLE_NAMES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"DUPLICATE_FEATURES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"ANNOTATION_DETAILS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"PROPERTY_DEFAULT_EXPRESSIONS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"DUPLICATE_FEATURE_INHERITANCE\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"COMMENTS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"DERIVED_FEATURES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"SUPER_CLASS_ORDER\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"DUPLICATE_OPERATION_INHERITANCE\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"REDEFINING_OPERATIONS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"INVARIANT_CONSTRAINTS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"UNION_PROPERTIES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"DUPLICATE_OPERATIONS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"NON_API_INVARIANTS\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"CAMEL_CASE_NAMES\" value=\"IGNORE\"/>\n");
		s.append("    <details key=\"SUBSETTING_PROPERTIES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"OPERATION_BODIES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"ECORE_TAGGED_VALUES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"UNTYPED_PROPERTIES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"REDEFINING_PROPERTIES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"INVOCATION_DELEGATES\" value=\"PROCESS\"/>\n");
		s.append("    <details key=\"VALIDATION_DELEGATES\" value=\"PROCESS\"/>\n");
		s.append("  </genAnnotations>\n");
		s.append("  <genAnnotations source=\"http://www.eclipse.org/OCL/GenModel\">\n");
		s.append("    <details key=\"Use Delegates\" value=\"false\"/>\n");
		s.append("    <details key=\"Use Null Annotations\" value=\"true\"/>\n");
		s.append("  </genAnnotations>\n");
		s.append("  <foreignModel>" + umlProfileResource.getURI().lastSegment() + "</foreignModel>\n");
		for (EObject eRootObject : umlProfileResource.getContents()) {
			if (eRootObject instanceof Model) {
				for (org.eclipse.uml2.uml.Package umlPackage : ((Model)eRootObject).getNestedPackages()) {
					assert umlPackage != null;
					appendGenModelPackage(s, umlPackage, interfacePackageSuffix, metaDataPackageSuffix, fileName);
				}
			}
			else if (eRootObject instanceof org.eclipse.uml2.uml.Package) {
				appendGenModelPackage(s, (org.eclipse.uml2.uml.Package)eRootObject, interfacePackageSuffix, metaDataPackageSuffix, fileName);
			}
		}
		if (usedGenPackages != null) {
			for (String usedGenPackage : usedGenPackages.split(" ")) {
				s.append("  <usedGenPackages href=\"" + usedGenPackage + "\"/>\\n");
			}
		}
		s.append("  <usedGenPackages href=\"platform:/resource/org.eclipse.emf.ecore/model/Ecore.genmodel#//ecore\"/>\n");
		s.append("  <usedGenPackages href=\"platform:/resource/org.eclipse.uml2.types/model/Types.genmodel#//types\"/>\n");
		s.append("  <usedGenPackages href=\"platform:/resource/org.eclipse.uml2.uml/model/UML.genmodel#//uml\"/>\n");
	//	s.append("  <usedGenPackages href=\"platform:/resource/org.eclipse.ocl.pivot/model/oclstdlib.genmodel#//oclstdlib\"/>\n");
		s.append("</genmodel:GenModel>\n");


		return s.toString();
	}

	private void appendGenModelPackage(StringBuilder s, org.eclipse.uml2.uml.@NonNull Package umlPackage, String interfacePackageSuffix, String metaDataPackageSuffix, @NonNull String fileStem) {
		String packageName = umlPackage.getName();
		s.append("  <genPackages xsi:type=\"genmodel:GenPackage\" prefix=\"" + packageName + "\"\n");
		s.append("    disposableProviderFactory=\"true\"\n");
		s.append("    ecorePackage=\"" + fileStem + ".profile.ecore#/\"\n");
		s.append("    basePackage=\"" + fileStem + "\"\n");
		if (interfacePackageSuffix != null) {
			s.append("    interfacePackageSuffix=\"" + interfacePackageSuffix + "\"\n");
		}
		if (metaDataPackageSuffix != null) {
			s.append("    metaDataPackageSuffix=\"" + metaDataPackageSuffix + "\"\n");
		}
		s.append("  />\n");
	}

	protected boolean doCompile(@NonNull OCL ocl, @NonNull JavaClasspath classpath, @NonNull String... testProjectNames) throws Exception {
		List<@NonNull JavaFileObject> compilationUnits = new ArrayList<>();
		StringBuilder sources = new StringBuilder();
		if (testProjectNames != null) {
			for (@NonNull String testProjectName : testProjectNames) {
				TestFile srcTestFile = getTestProject().getOutputFile(JavaFileUtil.TEST_SRC_FOLDER_NAME + "/" + testProjectName);
				JavaFileUtil.gatherCompilationUnits(compilationUnits, srcTestFile.getFile());
				if (sources.length() > 0) {
					sources.append(", ");
				}
				sources.append(srcTestFile.getFileString());
			}
		}
		String objectPath = getTestProject().getOutputFile(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFileString();
		classpath.addClass(getClass());
		String problemMessage = JavaFileUtil.compileClasses(compilationUnits, sources.toString(), objectPath, classpath);
		if (problemMessage != null) {
			fail(problemMessage);
		}
		return true;
	}

	protected boolean doEcoreCompile(@NonNull OCL ocl, @NonNull String... testProjectNames) throws Exception {
		JavaClasspath classpath = JavaFileUtil.createDefaultOCLClasspath();
		return doCompile(ocl, classpath, testProjectNames);
	}

	protected void doGenModel(@NonNull URI genmodelURI) throws Exception {
		OCL ocl= createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		try {
			URI fileURI = genmodelURI; //getProjectFileURI(testFileStem + ".genmodel");
			// System.out.println("Generating Ecore Model using '" + fileURI + "'");
			//		metamodelManager2.dispose();
			ProjectManager projectMap = ocl.getProjectManager();
		//	ocl.dispose();
			projectMap.configure(resourceSet, StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
			resourceSet.getPackageRegistry().put(org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI,  org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
			//FIXME this is needed so long as Pivot.genmodel is a UML genmodel
			resourceSet.getPackageRegistry().put(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI,  org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
			OCLGenModelUtil.initializeGeneratorAdapterFactoryRegistry();
		/*	if (resourceSet instanceof ResourceSetImpl) {
				ResourceSetImpl resourceSetImpl = (ResourceSetImpl) resourceSet;
				Map<URI, Resource> uriResourceMap = resourceSetImpl.getURIResourceMap();
				if (uriResourceMap != null) {
					uriResourceMap.clear();
				}
			} */
		//	resourceSet.getResources().clear();
			Resource resource = resourceSet.getResource(fileURI, true);
			// EcoreUtil.resolveAll(resourceSet); -- genModel can fail if
			// proxies resolved here
			// problem arises if genmodel has an obsolete feature for a feature
			// moved up the inheritance hierarchy
			// since the proxy seems to be successfully resolved giving a double
			// feature
			checkResourceSet(resourceSet);
			EObject eObject = resource.getContents().get(0);
			if (!(eObject instanceof GenModel)) {
				throw new ConfigurationException("No GenModel found in '" + resource.getURI() + "'");
			}
			GenModel genModel = (GenModel) eObject;
			genModel.reconcile();
			checkResourceSet(resourceSet);
			// genModel.setCanGenerate(true);
			// validate();

			genModel.setValidateModel(true); // The more checks the better
			// genModel.setCodeFormatting(true); // Normalize layout
			genModel.setForceOverwrite(false); // Don't overwrite read-only files
			genModel.setCanGenerate(true);
			// genModel.setFacadeHelperClass(null); // Non-null gives JDT
			// default NPEs
			// genModel.setFacadeHelperClass(StandaloneASTFacadeHelper.class.getName());
			// // Bug 308069
			// genModel.setValidateModel(true);
			genModel.setBundleManifest(false); // New manifests should be generated manually
			genModel.setUpdateClasspath(false); // New class-paths should be generated manually
			//		genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
			// genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
			Diagnostic diagnostic = genModel.diagnose();
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				String s = PivotUtil.formatDiagnostics(diagnostic, "\n");
				fail("Diagnose failure " + s);
			}

			String oldGenModelStr = EmfFormatter.objToStr(genModel);
			Generator generator = GenModelUtil.createGenerator(genModel);
			Monitor monitor = new BasicMonitor();
			diagnostic = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, monitor);
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				String s = PivotUtil.formatDiagnostics(diagnostic, "\n");
				fail("Generation failure" + s);
			}
			genModel.reconcile();			// Delete the GenOperations
			String newGenModelStr = EmfFormatter.objToStr(genModel);
			TestCase.assertEquals(oldGenModelStr, newGenModelStr);
			//		metamodelManager.dispose();
		}
		finally {
			ocl.dispose();
			ocl = null;
			unloadResourceSet(resourceSet);
			ThreadLocalExecutor.reset();		// Ensure that the Diagnostician's validateContext releases its WeakOCLReference.
		}
	}

	protected EPackage doLoadPackage(@NonNull ExplicitClassLoader classLoader, @NonNull String qualifiedModelPackageName) throws Exception {
		Class<?> testClass = classLoader.loadClass(qualifiedModelPackageName);
		//		System.out.println("Loaded " + testClass.getName());
		Object eInstance = testClass.getDeclaredField("eINSTANCE").get(null);
		return (EPackage) eInstance;
	}

	protected void doUMLCompile(@NonNull TestOCL ocl, @NonNull String testProjectName) throws Exception {
		JavaClasspath classpath = JavaFileUtil.createDefaultOCLClasspath();
		classpath.addClass(org.eclipse.uml2.types.TypesPackage.class);
		classpath.addClass(org.eclipse.uml2.uml.UMLPackage.class);
		doCompile(ocl, classpath, testProjectName);
	}

	@Override
	protected void initializeResourceSet() {
		resourceSet = null; //createResourceSet();
	//	standardResources = new ArrayList<Resource>(resourceSet.getResources());
	}

	protected @NonNull Resource loadUmlProfile(@NonNull ResourceSet resourceSet, @NonNull URI umlProfileURI) {
		UMLResourcesUtil.init(resourceSet);
		Resource umlProfileResource = resourceSet.getResource(umlProfileURI, true);
		assert umlProfileResource != null;
		assertNoResourceErrors("Profile load", umlProfileResource);
		assertNoValidationErrors("Profile validation", umlProfileResource);
		return umlProfileResource;
	}

	protected @NonNull Resource validateUmlModel(@NonNull URI umlModelURI, @NonNull String qualifiedPackageClassName, @NonNull String pathMapName, @Nullable Map<URI, URI> extraUriMap) throws Exception, IllegalAccessException {
		File projectFile = getTestProject().getFile();
		File explicitClassPath = new File(projectFile, "test-bin");
		URL url = explicitClassPath.toURI().toURL();
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, getClass().getClassLoader());
		Class<?> packageImplClass = urlClassLoader.loadClass(qualifiedPackageClassName);
		Field field = packageImplClass.getField("eINSTANCE");
		EPackage packageImpl = (EPackage)field.get(null);
		assert packageImpl != null;
		String nsURI = packageImpl.getNsURI();
		EPackage.Registry.INSTANCE.remove(nsURI);	// We don't need it in the global registry and another test may get a CCE if same nsURI.
		//
		ResourceSet resourceSet = new ResourceSetImpl();
		UMLResourcesUtil.init(resourceSet);
		resourceSet.getPackageRegistry().put(nsURI, packageImpl);
		URI pathMapURI = URI.createURI(pathMapName, true);
		URI profileFolderURI = umlModelURI.trimSegments(1).appendSegment("");
		Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
		uriMap.put(pathMapURI, profileFolderURI);
		if (extraUriMap != null) {
			uriMap.putAll(extraUriMap);
		}
		//
		Resource umlModelResource = resourceSet.getResource(umlModelURI, true);
		assert umlModelResource != null;
		assertNoResourceErrors("Model load", umlModelResource);
		assertNoValidationErrors("Model validation", umlModelResource);
		urlClassLoader.close();
		return umlModelResource;
	}

	public void testBug370824() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug370824";
				String testProjectName = "bug370824";
				String oclinecoreFile = "package bug370824 : bug370824 = 'http://bug370824'\n"
						+ "{\n"
						+ "    class Clase1\n"
						+ "    {\n"
						+ "        invariant : self.name.size() > 0;\n"
						+ "        attribute name : String[?] { ordered };\n"
						+ "    }\n"
						+ "}\n";
				String genmodelFile = createEcoreGenModelContent("Bug370824", null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testBug409650() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug409650";
				String testProjectName = "bug409650";
				String oclinecoreFile = "package bug409650 : bug409650 = 'http://bug409650'\n"
						+ "{\n"
						+ "    class Clase1\n"
						+ "    {\n"
						+ "        invariant : self.name.size() > 0;\n"
						+ "        attribute name : String[?] { ordered };\n"
						+ "        operation copy(b : Boolean) : Boolean { body: b; }\n"
						+ "        operation complement(b : Boolean) : Boolean { body: not b; }\n"
						+ "        operation myPrefixedName(s1 : String, s2 : String) : String { body: s1 + name + s2; }\n"
						+ "        operation me() : Clase1 { body: self.oclAsType(Clase1); }\n"
						+ "    }\n" + "}\n";
				String genmodelFile = createEcoreGenModelContent("Bug409650", null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("Clase1");
				EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature("name");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryTrue(eObject, "name = null");
				ocl.assertQueryTrue(eObject, "complement(true) = false");
				eObject.eSet(eStructuralFeature, "testing");
				ocl.assertQueryFalse(eObject, "name = null");
				ocl.assertQueryTrue(eObject, "name = 'testing'");
				ocl.assertQueryEquals(eObject, "XtestingY", "self.myPrefixedName('X', 'Y')");
				ocl.assertQueryEquals(eObject, eObject, "self.me()");
				ocl.dispose();
			}
		});
	}

	public void testBug415782() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug415782";
				String testProjectName = "bug415782";
				String oclinecoreFile =
						"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
								+ "package bug415782 : bug415782 = 'http://bug415782'\n"
								+ "{\n"
								+ "    class MyClass\n"
								+ "    {\n"
								+ "    	   attribute manyDates : ecore::EDate[*] { ordered };\n"
								+ "        attribute aBool : Boolean;\n"
								+ "        operation anOp() : MyClass {"
								+ "             body : MyClass {"
								+ "               manyDates = OrderedSet{},\n"
								+ "               aBool = manyDates->isEmpty()\n"
								+ "             };"
								+ "        }\n"
								+ "    }\n"
								+ "}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testBug416421() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStemA = "Bug416421A";
				String testProjectNameA = "bug416421A";
				String oclinecoreFileA =
						"package bug416421A : bug416421A = 'example.org/bug416421A'\n"
								+ "{\n"
								+ "	class ClassA\n"
								+ "	{\n"
								+ "		operation getFalse() : Boolean\n"
								+ "		{\n"
								+ "			body: false;\n"
								+ "		}\n"
								+ "	}\n"
								+ "}\n";
				String genmodelFileA = createEcoreGenModelContent(testFileStemA, null);
				String testFileStemB = "Bug416421B";
				String testProjectNameB = "bug416421B";
				String oclinecoreFileB =
						"import bug416421A : 'Bug416421A.ecore#/';\n"
								+ "package bug416421B : bug416421B = 'example.org/bug416421B'\n"
								+ "{\n"
								+ "	class ClassB extends bug416421A::ClassA\n"
								+ "	{\n"
								+ "		operation getTrue() : Boolean\n"
								+ "		{\n"
								+ "			body: true;\n"
								+ "		}\n"
								+ "	}\n"
								+ "}\n";
				createManifestFile();
				Map <@NonNull String, @Nullable String> genOptions = new HashMap<>();
				genOptions.put("usedGenPackages", "Bug416421A.genmodel#//bug416421A");
				String genmodelFileB = createEcoreGenModelContent(testFileStemB, genOptions);
				//		doDelete(testProjectNameA);
				//		doDelete(testProjectNameB);
				URI genModelURIA = createModels(testFileStemA, oclinecoreFileA, genmodelFileA);
				URI genModelURIB = createModels(testFileStemB, oclinecoreFileB, genmodelFileB);
				// B first demonstrates the demand load of Bug416421A to fix Bug 416421
				doGenModel(genModelURIB);
				doGenModel(genModelURIA);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectNameA, testProjectNameB);
				ocl.dispose();
			}
		});
	}

	public void testBug458722() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug458722";
				String testProjectName = "bug458722";
				String oclinecoreFile =
						"import ecore : 'http://www.eclipse.org/emf/2002/Ecore';\n" +
								"\n" +
								"package bug458722 : bug458722 = 'http://www.example.com/bug458722/rootPackage/2.0'\n" +
								"{\n" +
								"	package subPackage : subPackage = 'http://www.example.com/bug458722/subPackage/2.0'\n" +
								"	{\n" +
								"		class SubElement\n" +
								"		{\n" +
								"			operation op(tokens : String[*] { ordered !unique }) : Boolean\n" +
								"			{\n" +
								"				body: \n" +
								"				\n" +
								"				if tokens->at(1) = '1'\n" +
								"				then\n" +
								"					op2(tokens)\n" +
								"			    else\n" +
								"			    	true\n" +
								"			    endif;\n" +
								"			}\n" +
								"			operation op2(tokens : String[*] { ordered !unique }) : Boolean\n" +
								"			{\n" +
								"				body: \n" +
								"				true;\n" +
								"			}\n" +
								"		}\n" +
								"	}\n" +
								"	abstract class Element\n" +
								"	{\n" +
								"		attribute name : String = '';\n" +
								"	}\n" +
								"}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testBug458723() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug458723";
				String testProjectName = "bug458723";
				String oclinecoreFile =
						"import ecore : 'http://www.eclipse.org/emf/2002/Ecore';\n" +
								"\n" +
								"package bug458723 : bug458723 = 'http://www.example.com/bug458723/rootPackage/1.0'\n" +
								"{\n" +
								"    package subPackage : subPackage = 'http://www.example.com/bug458723/subPackage/1.0'\n" +
								"    {\n" +
								"        class Element extends bug458723::Element\n" +
								"        {\n" +
								"\n" +
								"            /*\n" +
								"             * Error also occurs with Bag(OclAny) in signature without\n" +
								"{!unique}\n" +
								"             */\n" +
								"            operation op() : ocl::OclAny[*] { !unique }\n" +
								"            {\n" +
								"                body: \n" +
								"                Bag{};\n" +
								"            }\n" +
								"        }\n" +
								"    }\n" +
								"    abstract class Element\n" +
								"    {\n" +
								"        attribute name : String = '';\n" +
								"    }\n" +
								"}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testBug458724() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug458724";
				String testProjectName = "bug458724";
				String oclinecoreFile =
						"import ecore : 'http://www.eclipse.org/emf/2002/Ecore';\n" +
								"\n" +
								"package bug458724 : bug458724 = 'http://www.example.com/bug458724/rootPackage/2.0'\n" +
								"{\n" +
								"    class Element\n" +
								"    {\n" +
								"        attribute name : String = '';\n" +
								"        invariant\n" +
								"        elementNameNotReservedWord: \n" +
								"            let name: String = self.name.toLower() in\n" +
								"            name <> 'reserved_1' and\n" +
								"            name <> 'reserved_2' and\n" +
								"            name <> 'reserved_3' and\n" +
								"            name <> 'reserved_4' and\n" +
								"            name <> 'reserved_5' and\n" +
								"            name <> 'reserved_6' and\n" +
								"            name <> 'reserved_7' and\n" +
								"            name <> 'reserved_8' and\n" +
								"            name <> 'reserved_9' and\n" +
								"            name <> 'reserved_10' and\n" +
								"            name <> 'reserved_11' and\n" +
								"            name <> 'reserved_12' and\n" +
								"            name <> 'reserved_13' and\n" +
								"            name <> 'reserved_14' and\n" +
								"            name <> 'reserved_15' and\n" +
								"            name <> 'reserved_16' and\n" +
								"            name <> 'reserved_17' and\n" +
								"            name <> 'reserved_18' and\n" +
								"            name <> 'reserved_19';\n" +
								"    }\n" +
								"}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testBug567919() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug567919";
				String testProjectName = "bug567919";
				String oclinecoreFile =
						"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
								+ "package bug567919 : bug567919 = 'http://bug567919'\n"
								+ "{\n"
								+ "    class Backlog\n"
								+ "	{\n"
								+ "		property workItems : WorkItem[+|1] { ordered composes };\n"
								+ "	}\n"
								+ "	class WorkItem\n"
								+ "	{\n"
								+ "		attribute effort : ecore::EInt[1];\n"
								+ "	}\n"
								+ "	class Plan\n"
								+ "	{\n"
								+ "		property backlog : Backlog[1] { composes };\n"
								+ "		attribute maxTeamVelocity : ecore::EInt[1];\n"
								+ "		attribute derivedMinSprintCount : ecore::EInt[1] { derived readonly volatile }\n"
								+ "		{\n"
								+ "			initial: self.backlog.workItems->collect(effort)->sum();\n"
								+ "		}\n"
								+ "	}\n"
								+ "}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);


				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				String qualifiedPackageName = testProjectName + ".Bug567919Package";
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass ePlanClass = (EClass) ePackage.getEClassifier("Plan");
				EReference ePlan_backlog = (EReference) ePlanClass.getEStructuralFeature("backlog");
				EAttribute ePlan_derivedMinSprintCount = (EAttribute) ePlanClass.getEStructuralFeature("derivedMinSprintCount");
				EClass eBacklogClass = (EClass) ePackage.getEClassifier("Backlog");
				EReference eBacklog_workItems = (EReference) eBacklogClass.getEStructuralFeature("workItems");
				EClass eWorkItemClass = (EClass) ePackage.getEClassifier("WorkItem");
				EAttribute eWorkItem_effort = (EAttribute) eWorkItemClass.getEStructuralFeature("effort");
				EFactory eFactory = ePackage.getEFactoryInstance();
				EObject plan = eFactory.create(ePlanClass);
				EObject backlog = eFactory.create(eBacklogClass);
				EObject workItem = eFactory.create(eWorkItemClass);
				workItem.eSet(eWorkItem_effort, 3);
				@SuppressWarnings("unchecked")
				List<EObject> castWorkItems = (List<EObject>)backlog.eGet(eBacklog_workItems);
				castWorkItems.add(workItem);
				plan.eSet(ePlan_backlog, backlog);

				int count = (int) plan.eGet(ePlan_derivedMinSprintCount);
				assertEquals(3, count);
				ocl.dispose();
			}
		});
	}

/*	FIXME Bug 569113
	public void testBug569113() throws Exception {
		String testFileStem = "Bug569113";
		String testProjectName = "bug569113";
		String oclinecoreFile =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package bug569113 : bug569113 = 'http://bug569113'\n"
						+ "{\n"
						+ "    class MyClass\n"
						+ "    {\n"
						+ "    	   attribute eInts : ecore::EInt[*];\n"
						+ "        operation sum() : Integer {\n"
						+ "             body : eInts->sum();\n"
						+ "        }\n"
						+ "    }\n"
						+ "}\n";
		String genmodelFile = createEcoreGenModelContent(testFileStem, null);
		createManifestFile();
		URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(genModelURI);
		TestOCL ocl = createOCL();
		doEcoreCompile(ocl, testProjectName);
		ocl.dispose();
	} */

	/**
	 * Verify that the static profile in Bug570717.uml model can be generated and compiled.
	 */
	public void testBug570717_uml() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug570717";
				String testProjectName = testFileStem; //"bug570717";
				TestOCL ocl0 = createOCL();
				URI genModelURI = null;
				Resource umlProfileResource = null;
				try {
					ResourceSet resourceSet0 = ocl0.getResourceSet();
					TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl0, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
					umlProfileResource = loadUmlProfile(resourceSet0, umlProfileFile.getURI());
					String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
					String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
					createManifestFile();
					createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
					genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
					Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
					//
					TestUMLImporter importer = new TestUMLImporter(resourceSet0.getPackageRegistry());
					importer.reloadGenModel(genModelPath);
				}
				finally {
					ocl0.dispose();
				}
				//
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl = createOCL();
				try {
					doUMLCompile(ocl, testProjectName);

					// Execute the profile
				}
				finally {
					ocl.dispose();
				}
			}
		});
	}

	/**
	 * Verify that the static profile in Bug570717.uml model can be generated and compiled.
	 */
	public void testBug570717a_uml() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				TestOCL ocl0 = createOCL();
				String testFileStem = "Bug570717a";
				String testProjectName = testFileStem; //"bug570717a";
				TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl0, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
				Resource umlProfileResource = loadUmlProfile(ocl0.getResourceSet(), umlProfileFile.getURI());
				String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
				String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
				createManifestFile();
				createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
				URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
				Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
				//
				TestUMLImporter importer = new TestUMLImporter(ocl0.getResourceSet().getPackageRegistry());
				importer.reloadGenModel(genModelPath);
				ocl0.dispose();
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl = createOCL();
				doUMLCompile(ocl, testProjectName);

				// Execute the profile

				ocl.dispose();
			}
		});
	}

	public void testBug570802() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug570802";
				String testProjectName = "bug570802";
				String oclinecoreFile = "package bug570802 : bug570802 = 'http://Bug570802'\n"
						+ "{\n"
						+ "	class Bug570802\n"
						+ "	{\n"
						+ "		property generics : BaseGeneric(?)[*|1] { ordered composes };\n"
						+ "		property genericsA : BaseGeneric(?)[*|1] { ordered derived readonly transient volatile }\n"
						+ "		{\n"
						+ "			initial: self.generics;\n"
						+ "		}\n"
						+ "	}\n"
						+ "	abstract class BaseGeneric(T);\n"
						+ "}";
				String genmodelFile = createEcoreGenModelContent("Bug570802", null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	/**
	 * Verify that the static profile in Bug570891.uml model can be generated and compiled.
	 */
	public void testBug570891_uml() throws Throwable {
		if (OCLGenModelUtil.INSTANCE.hasDoubleOverrideBug547424()) {				// Avoid UML BUG 547424
			System.err.println(getName() + " has been disabled -see UML Bug 547424");
			return;
		}
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				TestOCL ocl0 = createOCL();
				String testFileStem = "Bug570891";
				String testProjectName = testFileStem; //"bug570891";
				//		TestFile umlModelFile = getTestFile(testFileStem + ".uml", ocl0, getTestModelURI("models/uml/" + testFileStem + ".uml"));
				TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl0, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
				Resource umlProfileResource = loadUmlProfile(ocl0.getResourceSet(), umlProfileFile.getURI());
				String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
				String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
				createManifestFile();
				createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
				URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
				Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
				//
				TestUMLImporter importer = new TestUMLImporter(ocl0.getResourceSet().getPackageRegistry());
				importer.reloadGenModel(genModelPath);
				ocl0.dispose();
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl = createOCL();
				doUMLCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
		if (testProjectManager != null) {
			((StandaloneProjectMap)testProjectManager).reset();
			testProjectManager = null;
		}
	}

	/**
	 * Verify that the static profile in 570892.profile.uml model can be generated and compiled
	 * and that the 570892.uml model can then validate.
	 */
	public void testBug570892_uml() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				TestOCL ocl1 = createOCL();
				String testFileStem = "Bug570892";
				String testProjectName = testFileStem; //"bug570892";
				TestFile umlModelFile = getTestFile(testFileStem + ".uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".uml"));
				TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
				Resource umlProfileResource = loadUmlProfile(ocl1.getResourceSet(), umlProfileFile.getURI());
				String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
				String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
				createManifestFile();
				createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
				URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
				Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
				//
				TestUMLImporter importer = new TestUMLImporter(ocl1.getResourceSet().getPackageRegistry());
				importer.reloadGenModel(genModelPath);
				ocl1.dispose();
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl2 = createOCL();
				doUMLCompile(ocl2, testProjectName);

				//
				int oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT = AbstractEnvironmentFactory.CONSTRUCTION_COUNT;
				int oldAbstractModelManager_CONSTRUCTION_COUNT = AbstractModelManager.CONSTRUCTION_COUNT;
				int oldExecutorManager_CONSTRUCTION_COUNT = ExecutorManager.CONSTRUCTION_COUNT;
				int oldAbstractExecutor_CONSTRUCTION_COUNT = AbstractExecutor.CONSTRUCTION_COUNT;
				// Execute the profile
				String qualifiedPackageClassName = "Bug570892.validationproblem.ValidationProblemPackage";
				String pathMapName = "pathmap://VALIDATIONPROBLEM_PROFILE/";
				@SuppressWarnings("unused")
				Resource umlModelResource = validateUmlModel(umlModelFile.getURI(), qualifiedPackageClassName, pathMapName, null);
				assertEquals("AbstractEnvironmentFactory.CONSTRUCTION_COUNT", 0, AbstractEnvironmentFactory.CONSTRUCTION_COUNT-oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT);
				assertEquals("AbstractModelManager.CONSTRUCTION_COUNT", 1, AbstractModelManager.CONSTRUCTION_COUNT-oldAbstractModelManager_CONSTRUCTION_COUNT);
				assertEquals("ExecutorManager.CONSTRUCTION_COUNT", 1, ExecutorManager.CONSTRUCTION_COUNT-oldExecutorManager_CONSTRUCTION_COUNT);
				assertEquals("AbstractExecutor.CONSTRUCTION_COUNT", 0, AbstractExecutor.CONSTRUCTION_COUNT-oldAbstractExecutor_CONSTRUCTION_COUNT);
				//	Model model = (Model)umlModelResource.getContents().get(0);
				ocl2.dispose();
			}
		});
	}

	/**
	 * Verify that the static profile in Bug570894.profile.uml model can be generated and compiled
	 * and that the Bug570894.uml model can then validate.
	 */
	public void testBug570894_uml() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				TestOCL ocl1 = createOCL();
				String testFileStem = "Bug570894";
				String testProjectName = testFileStem; //"bug570894";
				TestFile umlModelFile = getTestFile(testFileStem + ".uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".uml"));
				TestFile umlLibraryFile = getTestFile(testFileStem + ".library.uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".library.uml"));
				TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
				Resource umlProfileResource = loadUmlProfile(ocl1.getResourceSet(), umlProfileFile.getURI());
				String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
				String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
				createManifestFile();
				createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
				URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
				Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
				//
				TestUMLImporter importer = new TestUMLImporter(ocl1.getResourceSet().getPackageRegistry());
				importer.reloadGenModel(genModelPath);
				ocl1.dispose();
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl2 = createOCL();
				doUMLCompile(ocl2, testProjectName);

				//
				int oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT = AbstractEnvironmentFactory.CONSTRUCTION_COUNT;
				int oldAbstractModelManager_CONSTRUCTION_COUNT = AbstractModelManager.CONSTRUCTION_COUNT;
				int oldExecutorManager_CONSTRUCTION_COUNT = ExecutorManager.CONSTRUCTION_COUNT;
				int oldAbstractExecutor_CONSTRUCTION_COUNT = AbstractExecutor.CONSTRUCTION_COUNT;
				// Execute the profile
				String qualifiedPackageClassName = "Bug570894.validationproblem.ValidationProblemPackage";
				String pathMapName = "pathmap://VALIDATIONPROBLEM_PROFILE/";
				Map<URI, URI> extraUriMap = new HashMap<URI, URI>();
				extraUriMap.put(URI.createURI("pathmap://VALIDATIONPROBLEM_LIBRARY/ValidationProblem-Library.uml"), umlLibraryFile.getURI());
				extraUriMap.put(URI.createURI("pathmap://VALIDATIONPROBLEM_PROFILE/ValidationProblem.profile.uml"), umlProfileFile.getURI());
				Resource umlModelResource = validateUmlModel(umlModelFile.getURI(), qualifiedPackageClassName, pathMapName, extraUriMap);
				assertEquals("AbstractEnvironmentFactory.CONSTRUCTION_COUNT", 0, AbstractEnvironmentFactory.CONSTRUCTION_COUNT-oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT);
				assertEquals("AbstractModelManager.CONSTRUCTION_COUNT", 1, AbstractModelManager.CONSTRUCTION_COUNT-oldAbstractModelManager_CONSTRUCTION_COUNT);
				assertEquals("ExecutorManager.CONSTRUCTION_COUNT", 1, ExecutorManager.CONSTRUCTION_COUNT-oldExecutorManager_CONSTRUCTION_COUNT);
				assertEquals("AbstractExecutor.CONSTRUCTION_COUNT", 0, AbstractExecutor.CONSTRUCTION_COUNT-oldAbstractExecutor_CONSTRUCTION_COUNT);
				for (@NonNull EObject eObject : umlModelResource.getContents()) {
					EClass eClass = eObject.eClass();
					if ("Farm".equals(eClass.getName())) {
						EStructuralFeature animalFeature = eClass.getEStructuralFeature("animal");
						EStructuralFeature chickenFeature = eClass.getEStructuralFeature("chicken");
						EStructuralFeature duckFeature = eClass.getEStructuralFeature("duck");
						Object animals = eObject.eGet(animalFeature);
						assertEquals(4, ((Collection<?>)animals).size());
						@SuppressWarnings("unused")
						Object chicken = eObject.eGet(chickenFeature);
						@SuppressWarnings("unused")
						Object duck = eObject.eGet(duckFeature);
					}
				}
				ocl2.dispose();
			}
		});
	}

	/**
	 * Verify that the static profile in Bug571407.profile.uml model can be generated and compiled.
	 */
	public void testBug571407_uml() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				TestOCL ocl1 = createOCL();
				String testFileStem = "Bug571407";
				String testProjectName = testFileStem; //"bug571407";
			//	TestFile umlModelFile = getTestFile(testFileStem + ".uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".uml"));
			//	TestFile umlLibraryFile = getTestFile(testFileStem + ".library.uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".library.uml"));
				TestFile umlProfileFile = getTestFile(testFileStem + ".profile.uml", ocl1, getTestModelURI("models/uml/" + testFileStem + ".profile.uml"));
				Resource umlProfileResource = loadUmlProfile(ocl1.getResourceSet(), umlProfileFile.getURI());
				String ecoreFileContent = createUMLEcoreModelContent(umlProfileResource);
				String genmodelFileContent = createUMLGenModelContent(umlProfileResource, testFileStem, null);
				createManifestFile();
				createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.ecore"), ecoreFileContent);
				URI genModelURI = createTestFileWithContent(getTestProject().getOutputFile(testFileStem + ".profile.genmodel"), genmodelFileContent);
				Path genModelPath = new Path("/" + getTestProject().getName() + "/" + testFileStem + ".profile.genmodel");
				//
				TestUMLImporter importer = new TestUMLImporter(ocl1.getResourceSet().getPackageRegistry());
				importer.reloadGenModel(genModelPath);
				ocl1.dispose();
				//
				doGenModel(genModelURI);
				//
				TestOCL ocl2 = createOCL();
				doUMLCompile(ocl2, testProjectName);

				//
			/*	int oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT = AbstractEnvironmentFactory.CONSTRUCTION_COUNT;
				int oldAbstractModelManager_CONSTRUCTION_COUNT = AbstractModelManager.CONSTRUCTION_COUNT;
				int oldExecutorManager_CONSTRUCTION_COUNT = ExecutorManager.CONSTRUCTION_COUNT;
				int oldAbstractExecutor_CONSTRUCTION_COUNT = AbstractExecutor.CONSTRUCTION_COUNT;
				// Execute the profile
				String qualifiedPackageClassName = "Bug570894.validationproblem.ValidationProblemPackage";
				String pathMapName = "pathmap://VALIDATIONPROBLEM_PROFILE/";
				Map<URI, URI> extraUriMap = new HashMap<URI, URI>();
				extraUriMap.put(URI.createURI("pathmap://VALIDATIONPROBLEM_LIBRARY/ValidationProblem-Library.uml"), umlLibraryFile.getURI());
				extraUriMap.put(URI.createURI("pathmap://VALIDATIONPROBLEM_PROFILE/ValidationProblem.profile.uml"), umlProfileFile.getURI());
				Resource umlModelResource = validateUmlModel(umlModelFile.getURI(), qualifiedPackageClassName, pathMapName, extraUriMap);
				assertEquals("AbstractEnvironmentFactory.CONSTRUCTION_COUNT", 0, AbstractEnvironmentFactory.CONSTRUCTION_COUNT-oldAbstractEnvironmentFactory_CONSTRUCTION_COUNT);
				assertEquals("AbstractModelManager.CONSTRUCTION_COUNT", 1, AbstractModelManager.CONSTRUCTION_COUNT-oldAbstractModelManager_CONSTRUCTION_COUNT);
				assertEquals("ExecutorManager.CONSTRUCTION_COUNT", 1, ExecutorManager.CONSTRUCTION_COUNT-oldExecutorManager_CONSTRUCTION_COUNT);
				assertEquals("AbstractExecutor.CONSTRUCTION_COUNT", 0, AbstractExecutor.CONSTRUCTION_COUNT-oldAbstractExecutor_CONSTRUCTION_COUNT);
				for (@NonNull EObject eObject : umlModelResource.getContents()) {
					EClass eClass = eObject.eClass();
					if ("Farm".equals(eClass.getName())) {
						EStructuralFeature animalFeature = eClass.getEStructuralFeature("animal");
						EStructuralFeature chickenFeature = eClass.getEStructuralFeature("chicken");
						EStructuralFeature duckFeature = eClass.getEStructuralFeature("duck");
						Object animals = eObject.eGet(animalFeature);
						assertEquals(4, ((Collection<?>)animals).size());
						@SuppressWarnings("unused")
						Object chicken = eObject.eGet(chickenFeature);
						@SuppressWarnings("unused")
						Object duck = eObject.eGet(duckFeature);
					}
				}
				ocl2.dispose(); */
			}
		});
	}

	public void testCSE() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
				//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
				//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
				//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
				//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
				String testFileStem = "CSEs";
				String testProjectName = "cses";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package cses : cses = 'http://cses'\n"
						+ "{\n"
						+ "    class CSEs\n"
						+ "    {\n"
						//			+ "        attribute a : ecore::EInt = '3' { readonly };\n"
						+ "        operation test(a : ecore::EInt, b : ecore::EInt, c : ecore::EInt) : ecore::EInt { body: if a + b + c > 0 then a + b + c else a + b endif; }\n"
						+ "    }\n"
						+ "}\n";
				Map <@NonNull String, @Nullable String> genOptions = new HashMap<>();
				genOptions.put("interfacePackageSuffix", "coreI");
				genOptions.put("metaDataPackageSuffix", "coreM");
				String genmodelFile = createEcoreGenModelContent(testFileStem, genOptions);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				String qualifiedPackageName = testProjectName + ".coreM." + testFileStem + "Package";
				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("CSEs");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				//			OCLHelper helper = getHelper();
				//			org.eclipse.ocl.pivot.Class contextType = helper.getOCL().getMetamodelManager().getType(idResolver.getStaticTypeOf(eObject));
				//			helper.setContext(contextType);
				//			ExpressionInOCL query = helper.createQuery("test(3, 2, 1)");
				//			ocl.assertCallCount(query, null, 2);
				//			ocl.assertCallCount(query, NumericPlusOperation.INSTANCE, 2);
				ocl.assertQueryEquals(eObject, 6, "test(3, 2, 1)");
				ocl.assertQueryEquals(eObject, -5, "test(3, -8, 1)");
				ocl.dispose();
			}
		});
	}

	public void testCodegenCompany() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testProjectName = "org/eclipse/ocl/examples/xtext/tests/codegen/company";
				URI sourceGenModelURI = getTestModelURI("models/genmodel/CodeGenCompany.genmodel");
				URI targetGenModelURI = getTestURI("CodeGenCompany.genmodel");
				TestOCL ocl0 = createOCL();
				try {
					//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
					//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
					//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
					//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
					//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
					//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
					//
					Resource genModelResource = ocl0.getResourceSet().getResource(sourceGenModelURI, true);
					GenModel genModel = (GenModel) genModelResource.getContents().get(0);
					genModel.setModelDirectory(getTestProject().getName() + "/" + JavaFileUtil.TEST_SRC_FOLDER_NAME);
					genModelResource.setURI(targetGenModelURI);
					genModelResource.save(XMIUtil.createSaveOptions());
					//
					createManifestFile();
				}
				finally {
					ocl0.dispose();
				}
				doGenModel(targetGenModelURI);
				TestOCL ocl = createOCL();
				try {
					doEcoreCompile(ocl, testProjectName);
				}
				finally {
					ocl.dispose();
				}
			}
		});
	}

	public void testEcoreLists570717() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug570717";
				String testProjectName = "bug570717";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package bug570717 : bug570717 = 'http://bug570717'\n"
						+ "{\n"
						+ "    datatype ENumber : 'java.lang.Number' { serializable };\n"
						+ "    class EcoreLists\n"
						+ "    {\n"
						+ "        attribute eBigDecimal : ecore::EBigDecimal { derived readonly volatile } { derivation: negEBigDecimal(1); }\n"
						+ "        attribute eBigDecimals : ecore::EBigDecimal[*] { derived readonly volatile } { derivation: negEBigDecimals(Set{1}); }\n"
						+ "        attribute eBigInteger : ecore::EBigInteger { derived readonly volatile } { derivation: negEBigInteger(1); }\n"
						+ "        attribute eBigIntegers : ecore::EBigInteger[*] { derived readonly volatile } { derivation: negEBigIntegers(Set{1}); }\n"
						+ "        attribute eBoolean : ecore::EBoolean { derived readonly volatile } { derivation: notEBoolean(true); }\n"
						+ "        attribute eBooleans : ecore::EBoolean[*] { derived readonly volatile } { derivation: notEBooleans(Set{true}); }\n"
						+ "        attribute eBooleanObject : ecore::EBooleanObject { derived readonly volatile } { derivation: notEBooleanObject(true); }\n"
						+ "        attribute eBooleanObjects : ecore::EBooleanObject[*] { derived readonly volatile } { derivation: notEBooleanObjects(Set{true}); }\n"
						+ "        attribute eChar : ecore::EChar { derived readonly volatile } { derivation: negEChar(1); }\n"
						+ "        attribute eChars : ecore::EChar[*] { derived readonly volatile } { derivation: negEChars(Set{1}); }\n"
						+ "        attribute eCharacterObject : ecore::ECharacterObject { derived readonly volatile } { derivation: negECharacterObject(1); }\n"
						+ "        attribute eCharacterObjects : ecore::ECharacterObject[*] { derived readonly volatile } { derivation: negECharacterObjects(Set{1}); }\n"
						+ "        attribute eDouble : ecore::EDouble { derived readonly volatile } { derivation: negEDouble(1); }\n"
						+ "        attribute eDoubles : ecore::EDouble[*] { derived readonly volatile } { derivation: negEDoubles(Set{1}); }\n"
						+ "        attribute eDoubleObject : ecore::EDoubleObject { derived readonly volatile } { derivation: negEDoubleObject(1); }\n"
						+ "        attribute eDoubleObjects : ecore::EDoubleObject[*] { derived readonly volatile } { derivation: negEDoubleObjects(Set{1}); }\n"
						+ "        attribute eFloat : ecore::EFloat { derived readonly volatile } { derivation: negEFloat(1); }\n"
						+ "        attribute eFloats : ecore::EFloat[*] { derived readonly volatile } { derivation: negEFloats(Set{1}); }\n"
						+ "        attribute eFloatObject : ecore::EFloatObject { derived readonly volatile } { derivation: negEFloatObject(1); }\n"
						+ "        attribute eFloatObjects : ecore::EFloatObject[*] { derived readonly volatile } { derivation: negEFloatObjects(Set{1}); }\n"
						+ "        attribute eInt : ecore::EInt { derived readonly volatile } { derivation: negEInt(1); }\n"
						+ "        attribute eInts : ecore::EInt[*] { derived readonly volatile } { derivation: negEInts(Set{1}); }\n"
						+ "        attribute eIntegerObject : ecore::EIntegerObject { derived readonly volatile } { derivation: negEIntegerObject(1); }\n"
						+ "        attribute eIntegerObjects : ecore::EIntegerObject[*] { derived readonly volatile } { derivation: negEIntegerObjects(Set{1}); }\n"
						+ "        attribute eLong : ecore::ELong { derived readonly volatile } { derivation: negELong(1); }\n"
						+ "        attribute eLongs : ecore::ELong[*] { derived readonly volatile } { derivation: negELongs(Set{1}); }\n"
						+ "        attribute eLongObject : ecore::ELongObject { derived readonly volatile } { derivation: negELongObject(1); }\n"
						+ "        attribute eLongObjects : ecore::ELongObject[*] { derived readonly volatile } { derivation: negELongObjects(Set{1}); }\n"
						+ "        attribute eShort : ecore::EShort { derived readonly volatile } { derivation: negEShort(1); }\n"
						+ "        attribute eShorts : ecore::EShort[*] { derived readonly volatile } { derivation: negEShorts(Set{1}); }\n"
						+ "        attribute eShortObject : ecore::EShortObject { derived readonly volatile } { derivation: negEShortObject(1); }\n"
						+ "        attribute eShortObjects : ecore::EShortObject[*] { derived readonly volatile } { derivation: negEShortObjects(Set{1}); }\n"
						//			+ "        attribute eNumber : ENumber { derived readonly volatile } { derivation: negENumber(ENumber{'1'}); }\n"
						+ "        attribute eString : ecore::EString { derived readonly volatile } { derivation: upCase('abc'); }\n"
						+ "        attribute eStrings : ecore::EString[*] { derived readonly volatile } { derivation: upCases(Set{'abc'}); }\n"
						+ "        operation negEBigDecimal(b : ecore::EBigDecimal) : ecore::EBigDecimal { body: -b; }\n"
						+ "        operation negEBigDecimals(b : ecore::EBigDecimal[*]) : ecore::EBigDecimal[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEBigInteger(b : ecore::EBigInteger) : ecore::EBigInteger { body: -b; }\n"
						+ "        operation negEBigIntegers(b : ecore::EBigInteger[*]) : ecore::EBigInteger[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation notEBoolean(b : ecore::EBoolean) : ecore::EBoolean { body: not b; }\n"
						+ "        operation notEBooleans(b : ecore::EBoolean[*]) : ecore::EBoolean[*] { body: b->collect(e | not e)->asSet(); }\n"
						+ "        operation notEBooleanObject(b : ecore::EBooleanObject) : ecore::EBooleanObject { body: not b; }\n"
						+ "        operation notEBooleanObjects(b : ecore::EBooleanObject[*]) : ecore::EBooleanObject[*] { body: b->collect(e | not e)->asSet(); }\n"
						+ "        operation negEChar(b : ecore::EChar) : ecore::EChar { body: -b; }\n"
						+ "        operation negEChars(b : ecore::EChar[*]) : ecore::EChar[*] { body: b->collect(e | (-e).oclAsType(ecore::EChar))->asSet(); }\n"
						+ "        operation negECharacterObject(b : ecore::ECharacterObject) : ecore::ECharacterObject { body: -b; }\n"
						+ "        operation negECharacterObjects(b : ecore::ECharacterObject[*]) : ecore::ECharacterObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEDouble(b : ecore::EDouble) : ecore::EDouble { body: -b; }\n"
						+ "        operation negEDoubles(b : ecore::EDouble[*]) : ecore::EDouble[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEDoubleObject(b : ecore::EDoubleObject) : ecore::EDoubleObject { body: -b; }\n"
						+ "        operation negEDoubleObjects(b : ecore::EDoubleObject[*]) : ecore::EDoubleObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEFloat(b : ecore::EFloat) : ecore::EFloat { body: -b; }\n"
						+ "        operation negEFloats(b : ecore::EFloat[*]) : ecore::EFloat[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEFloatObject(b : ecore::EFloatObject) : ecore::EFloatObject { body: -b; }\n"
						+ "        operation negEFloatObjects(b : ecore::EFloatObject[*]) : ecore::EFloatObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEInt(b : ecore::EInt) : ecore::EInt { body: -b; }\n"
						+ "        operation negEInts(b : ecore::EInt[*]) : ecore::EInt[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEIntegerObject(b : ecore::EIntegerObject) : ecore::EIntegerObject { body: -b; }\n"
						+ "        operation negEIntegerObjects(b : ecore::EIntegerObject[*]) : ecore::EIntegerObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negELong(b : ecore::ELong) : ecore::ELong { body: -b; }\n"
						+ "        operation negELongs(b : ecore::ELong[*]) : ecore::ELong[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negELongObject(b : ecore::ELongObject) : ecore::ELongObject { body: -b; }\n"
						+ "        operation negELongObjects(b : ecore::ELongObject[*]) : ecore::ELongObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEShort(b : ecore::EShort) : ecore::EShort { body: -b; }\n"
						+ "        operation negEShorts(b : ecore::EShort[*]) : ecore::EShort[*] { body: b->collect(e | -e)->asSet(); }\n"
						+ "        operation negEShortObject(b : ecore::EShortObject) : ecore::EShortObject { body: -b; }\n"
						+ "        operation negEShortObjects(b : ecore::EShortObject[*]) : ecore::EShortObject[*] { body: b->collect(e | -e)->asSet(); }\n"
						//			+ "        operation negENumber(b : ENumber) : ENumber { body: (-(b.oclAsType(Integer))).oclAsType(ENumber); }\n"
						+ "        operation upCase(b : ecore::EString) : ecore::EString { body: b.toUpper(); }\n"
						+ "        operation upCases(b : ecore::EString[*]) : ecore::EString[*] { body: b->collect(e | e.toUpper())->asSet(); }\n"
						+ "    }\n" + "}\n";
				String genmodelFile = createEcoreGenModelContent("Bug570717", null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				try {
					doEcoreCompile(ocl, testProjectName);
					String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
					File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
					List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
					ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
					EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
					EClass eClass = (EClass) ePackage.getEClassifier("EcoreLists");
					EFactory eFactory = ePackage.getEFactoryInstance();
					//
					EObject eObject = eFactory.create(eClass);
					// FIXME BUG 570800		ocl.assertQueryTrue(eObject, "eChars = Set{eChar}");
					// FIXME BUG 570800		ocl.assertQueryTrue(eObject, "negEChars(eChars) = Set{negEChar(eChar)}");
					// FIXME BUG 570800		ocl.assertQueryTrue(eObject, "eCharacterObjects = Set{eCharacterObject}");
					// FIXME BUG 570800		ocl.assertQueryTrue(eObject, "negECharacterObjects(eCharacterObjects) = Set{negECharacterObject(eCharacterObject)}");
					ocl.assertQueryTrue(eObject, "eBigDecimals = Set{eBigDecimal}");
					ocl.assertQueryTrue(eObject, "negEBigDecimals(eBigDecimals) = Set{negEBigDecimal(eBigDecimal)}");
					ocl.assertQueryTrue(eObject, "eBigIntegers = Set{eBigInteger}");
					ocl.assertQueryTrue(eObject, "negEBigIntegers(eBigIntegers) = Set{negEBigInteger(eBigInteger)}");
					ocl.assertQueryTrue(eObject, "eBoolean = eBooleanObject");
					ocl.assertQueryTrue(eObject, "eBooleans = eBooleanObjects");
					ocl.assertQueryTrue(eObject, "notEBooleans(eBooleans) = Set{notEBoolean(eBoolean)}");
					ocl.assertQueryTrue(eObject, "eBooleanObjects = Set{eBooleanObject}");
					ocl.assertQueryTrue(eObject, "notEBooleanObjects(eBooleanObjects) = Set{notEBooleanObject(eBooleanObject)}");
					ocl.assertQueryTrue(eObject, "eDoubles = Set{eDouble}");
					ocl.assertQueryTrue(eObject, "negEDoubles(eDoubles) = Set{negEDouble(eDouble)}");
					ocl.assertQueryTrue(eObject, "eDoubleObjects = Set{eDoubleObject}");
					ocl.assertQueryTrue(eObject, "negEDoubleObjects(eDoubleObjects) = Set{negEDoubleObject(eDoubleObject)}");
					ocl.assertQueryTrue(eObject, "eFloats = Set{eFloat}");
					ocl.assertQueryTrue(eObject, "negEFloats(eFloats) = Set{negEFloat(eFloat)}");
					ocl.assertQueryTrue(eObject, "eFloatObjects = Set{eFloatObject}");
					ocl.assertQueryTrue(eObject, "negEFloatObjects(eFloatObjects) = Set{negEFloatObject(eFloatObject)}");
					ocl.assertQueryTrue(eObject, "eInts = Set{eInt}");
					ocl.assertQueryTrue(eObject, "negEInts(eInts) = Set{negEInt(eInt)}");
					ocl.assertQueryTrue(eObject, "eIntegerObjects = Set{eIntegerObject}");
					ocl.assertQueryTrue(eObject, "negEIntegerObjects(eIntegerObjects) = Set{negEIntegerObject(eIntegerObject)}");
					ocl.assertQueryTrue(eObject, "eLongs = Set{eLong}");
					ocl.assertQueryTrue(eObject, "negELongs(eLongs) = Set{negELong(eLong)}");
					ocl.assertQueryTrue(eObject, "eLongObjects = Set{eLongObject}");
					ocl.assertQueryTrue(eObject, "negELongObjects(eLongObjects) = Set{negELongObject(eLongObject)}");
					ocl.assertQueryTrue(eObject, "eShorts = Set{eShort}");
					ocl.assertQueryTrue(eObject, "negEShorts(eShorts) = Set{negEShort(eShort)}");
					ocl.assertQueryTrue(eObject, "eShortObjects = Set{eShortObject}");
					ocl.assertQueryTrue(eObject, "negEShortObjects(eShortObjects) = Set{negEShortObject(eShortObject)}");
					ocl.assertQueryTrue(eObject, "eBigInteger = eBigDecimal");
					//			ocl.assertQueryTrue(eObject, "eNumber = eFloat");				-- waiting for BUG 370087
					ocl.assertQueryTrue(eObject, "eString = 'ABC'");
					ocl.assertQueryTrue(eObject, "eStrings = Set{eString}");
				}
				finally {
					ocl.dispose();
				}
			}
		});
	}

	public void testEcoreTypes412736() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug412736";
				String testProjectName = "bug412736";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package bug412736 : bug412736 = 'http://bug412736'\n"
						+ "{\n"
						+ "    datatype ENumber : 'java.lang.Number' { serializable };\n"
						+ "    class EcoreDataTypes\n"
						+ "    {\n"
						+ "        attribute eBigDecimal : ecore::EBigDecimal { derived readonly volatile } { derivation: negEBigDecimal(1); }\n"
						+ "        attribute eBigInteger : ecore::EBigInteger { derived readonly volatile } { derivation: negEBigInteger(1); }\n"
						+ "        attribute eBooleanObject : ecore::EBooleanObject { derived readonly volatile } { derivation: notEBooleanObject(true); }\n"
						+ "        attribute eBoolean : ecore::EBoolean { derived readonly volatile } { derivation: notEBoolean(true); }\n"
						+ "        attribute eCharacterObject : ecore::ECharacterObject { derived readonly volatile } { derivation: negECharacterObject(1); }\n"
						+ "        attribute eChar : ecore::EChar { derived readonly volatile } { derivation: negEChar(1); }\n"
						+ "        attribute eDoubleObject : ecore::EDoubleObject { derived readonly volatile } { derivation: negEDoubleObject(1); }\n"
						+ "        attribute eDouble : ecore::EDouble { derived readonly volatile } { derivation: negEDouble(1); }\n"
						+ "        attribute eFloatObject : ecore::EFloatObject { derived readonly volatile } { derivation: negEFloatObject(1); }\n"
						+ "        attribute eFloat : ecore::EFloat { derived readonly volatile } { derivation: negEFloat(1); }\n"
						+ "        attribute eIntegerObject : ecore::EIntegerObject { derived readonly volatile } { derivation: negEIntegerObject(1); }\n"
						+ "        attribute eInt : ecore::EInt { derived readonly volatile } { derivation: negEInt(1); }\n"
						+ "        attribute eLongObject : ecore::ELongObject { derived readonly volatile } { derivation: negELongObject(1); }\n"
						+ "        attribute eLong : ecore::ELong { derived readonly volatile } { derivation: negELong(1); }\n"
						//			+ "        attribute eNumber : ENumber { derived readonly volatile } { derivation: negENumber(ENumber{'1'}); }\n"
						+ "        attribute eShortObject : ecore::EShortObject { derived readonly volatile } { derivation: negEShortObject(1); }\n"
						+ "        attribute eShort : ecore::EShort { derived readonly volatile } { derivation: negEShort(1); }\n"
						+ "        attribute eString : ecore::EString { derived readonly volatile } { derivation: upCase('abc'); }\n"
						+ "        operation negEBigDecimal(b : ecore::EBigDecimal) : ecore::EBigDecimal { body: -b; }\n"
						+ "        operation negEBigInteger(b : ecore::EBigInteger) : ecore::EBigInteger { body: -b; }\n"
						+ "        operation negEChar(b : ecore::EChar) : ecore::EChar { body: -b; }\n"
						+ "        operation negECharacterObject(b : ecore::ECharacterObject) : ecore::ECharacterObject { body: -b; }\n"
						+ "        operation negEDouble(b : ecore::EDouble) : ecore::EDouble { body: -b; }\n"
						+ "        operation negEDoubleObject(b : ecore::EDoubleObject) : ecore::EDoubleObject { body: -b; }\n"
						+ "        operation negEFloat(b : ecore::EFloat) : ecore::EFloat { body: -b; }\n"
						+ "        operation negEFloatObject(b : ecore::EFloatObject) : ecore::EFloatObject { body: -b; }\n"
						+ "        operation negEInt(b : ecore::EInt) : ecore::EInt { body: -b; }\n"
						+ "        operation negEIntegerObject(b : ecore::EIntegerObject) : ecore::EIntegerObject { body: -b; }\n"
						+ "        operation negELong(b : ecore::ELong) : ecore::ELong { body: -b; }\n"
						+ "        operation negELongObject(b : ecore::ELongObject) : ecore::ELongObject { body: -b; }\n"
						//			+ "        operation negENumber(b : ENumber) : ENumber { body: (-(b.oclAsType(Integer))).oclAsType(ENumber); }\n"
						+ "        operation negEShort(b : ecore::EShort) : ecore::EShort { body: -b; }\n"
						+ "        operation negEShortObject(b : ecore::EShortObject) : ecore::EShortObject { body: -b; }\n"
						+ "        operation notEBoolean(b : ecore::EBoolean) : ecore::EBoolean { body: not b; }\n"
						+ "        operation notEBooleanObject(b : ecore::EBooleanObject) : ecore::EBooleanObject { body: not b; }\n"
						+ "        operation upCase(b : ecore::EString) : ecore::EString { body: b.toUpper(); }\n"
						+ "    }\n" + "}\n";
				String genmodelFile = createEcoreGenModelContent("Bug412736", null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("EcoreDataTypes");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryTrue(eObject, "eBigInteger = eBigDecimal");
				ocl.assertQueryTrue(eObject, "eChar = eCharacterObject");
				ocl.assertQueryTrue(eObject, "eBoolean = eBooleanObject");
				ocl.assertQueryTrue(eObject, "eDouble = eDoubleObject");
				ocl.assertQueryTrue(eObject, "eFloat = eFloatObject");
				ocl.assertQueryTrue(eObject, "eInt = eIntegerObject");
				ocl.assertQueryTrue(eObject, "eLong = eLongObject");
				//			ocl.assertQueryTrue(eObject, "eNumber = eFloat");				-- waiting for BUG 370087
				ocl.assertQueryTrue(eObject, "eShort = eShortObject");
				ocl.assertQueryTrue(eObject, "eString = 'ABC'");
				ocl.dispose();
			}
		});
	}

	public void testEnumTypes412685() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				// FIXME next line compensates an uninstall overenthusiasm
				EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, OCLstdlibPackage.eINSTANCE);
				String testFileStem = "Bug412685";
				String testProjectName = "bug412685";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package bug412685 : bug412685 = 'http://bug412685'\n"
						+ "{\n"
						+ "    enum Color { serializable } {\n"
						+ "    	literal BLACK;\n"
						+ "    	literal WHITE;\n"
						+ "    }\n"
						+ "    class EnumTypes\n"
						+ "    {\n"
						+ "        attribute eBlack : Color = 'BLACK' { readonly };\n"
						+ "        attribute eWhite : Color = 'WHITE' { readonly };\n"
						+ "        attribute eColor : Color { derived readonly volatile } { derivation: otherColor(Color::BLACK); }\n"
						+ "        operation opaqueColor(eColor : Color) : OclAny { body: eColor; }\n"
						+ "        operation otherColor(eColor : Color) : Color { body: if eColor = Color::BLACK then Color::WHITE else Color::BLACK endif; }\n"
						+ "    }\n"
						+ "}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("EnumTypes");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryTrue(eObject, "let aWhite : OclAny = opaqueColor(eWhite) in eColor = aWhite");
				ocl.assertQueryTrue(eObject, "let aWhite : OclAny = eWhite.oclAsType(OclAny) in eColor = aWhite");
				ocl.assertQueryTrue(eObject, "eColor = eWhite");
				ocl.assertQueryTrue(eObject, "eColor = Color::WHITE");
				ocl.dispose();
			}
		});
	}

	public void testEvaluators() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
				//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
				//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
				//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
				//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
				//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
				String testFileStem = "Evaluators";
				String testProjectName = "evaluators";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package evaluators : evaluators = 'http://evaluators'\n"
						+ "{\n"
						+ "    class Evaluators\n"
						+ "    {\n"
						+ "        attribute name : String[?];\n"
						+ "        operation test() : String { body: \n"
						+ "        let severity : String[1] = 'testString'.replaceFirst('xx', 'yy') \n"
						+ "        in if severity = '' \n"
						+ "        then '' \n"
						+ "        else \n"
						+ "        'testString'.replaceAll('z1','z2') \n"
						+ "        endif; }\n"
						+ "    }\n"
						+ "}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
				File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
				List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
				ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
				EPackage ePackage = doLoadPackage(classLoader, qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("Evaluators");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryEquals(eObject, "testString", "test()");
				ocl.dispose();
			}
		});
	}

	public void testSysML_QUDV() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testProjectName = "SysML_ValueTypes_QUDV";
				URI targetGenModelURI = getTestURI("SysML_ValueTypes_QUDV.genmodel");
				TestOCL ocl0 = createOCL();
				try {
					//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
					//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
					//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
					//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
					//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
					//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
					createManifestFile();
					//
					URI sourceGenModelURI = getTestModelURI("models/genmodel/SysML_ValueTypes_QUDV.genmodel");
					Resource genModelResource = ocl0.getResourceSet().getResource(sourceGenModelURI, true);
					GenModel genModel = (GenModel) genModelResource.getContents().get(0);
					genModel.setModelDirectory(getTestProject().getName() + "/" + JavaFileUtil.TEST_SRC_FOLDER_NAME);
					genModelResource.setURI(targetGenModelURI);
					genModelResource.save(XMIUtil.createSaveOptions());
				}
				finally {
					ocl0.dispose();
				}

				//			getTestFileURI("SysML_ValueTypes_QUDV.ecore", ocl, getTestModelURI("models/genmodel/SysML_ValueTypes_QUDV.ecore"));
				//			URI targetGenModelURI = getTestFileURI("SysML_ValueTypes_QUDV.genmodel", ocl, getTestModelURI("models/genmodel/SysML_ValueTypes_QUDV.genmodel"));
				//
				doGenModel(targetGenModelURI);

				TestOCL ocl = createOCL();
				try {
					doEcoreCompile(ocl, testProjectName);
					File classFilePath = getTestProject().getOutputFolder(JavaFileUtil.TEST_BIN_FOLDER_NAME + "/").getFile();
					List<@NonNull String> packagePaths = JavaFileUtil.gatherPackageNames(classFilePath, null);
					ExplicitClassLoader classLoader = new ExplicitClassLoader(classFilePath, packagePaths, getClass().getClassLoader());
					String qualifiedPackageName1 = testProjectName + ".QUDV.QUDVPackage";
					EPackage ePackage1 = doLoadPackage(classLoader, qualifiedPackageName1);
					EClass eClass1 = (EClass) ePackage1.getEClassifier("DerivedQuantityKind");
					EFactory eFactory1 = ePackage1.getEFactoryInstance();
					//
					EObject eObject1 = eFactory1.create(eClass1);
					ocl.assertQueryTrue(eObject1, "dependsOnQuantityKinds() <> null");
					//
					String qualifiedPackageName2 = testProjectName + ".PrimitiveValueTypes.PrimitiveValueTypesPackage";
					EPackage ePackage2 = doLoadPackage(classLoader, qualifiedPackageName2);
					EClass eClass2 = (EClass) ePackage2.getEClassifier("Complex");
					EFactory eFactory2 = ePackage2.getEFactoryInstance();
					//
					EObject eObject2 = eFactory2.create(eClass2);
					ocl.assertQueryTrue(eObject2, "imaginaryPart = realPart");
					ocl.assertQueryTrue(eObject2, "oclType() <> null");
					ocl.assertQueryTrue(eObject2, "oclType().oclIsKindOf(OclAny)");
				}
				finally {
					ocl.dispose();
				}
			}
		});
	}

	public void testInitStatics() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				assertTrue(ValueUtil.initAllStatics());
				assertFalse(ValueUtil.initAllStatics());
			}
		});
	}

	/**
	 * Verify that the Pivot metamodel can be loaded and validated as a *.oclas file by the
	 * Sample Reflective Ecore Editor.
	 * @throws Throwable
	 */
	public void testOpen_Pivot_oclas() throws Throwable {
		try {
			doTestRunnable(new TestRunnable() {
				@Override
				public void runWithThrowable() throws Exception {
					TestOCL ocl = createOCL();
					if (EMFPlugin.IS_ECLIPSE_RUNNING) {
						TestCaseAppender.INSTANCE.uninstall();
						TestUIUtil.suppressGitPrefixPopUp();
						IWorkbench workbench = PlatformUI.getWorkbench();
						IIntroManager introManager = workbench.getIntroManager();
						introManager.closeIntro(introManager.getIntro());
						TestUIUtil.flushEvents();

						String testProjectName = "Open_Pivot";
						ResourceSet resourceSet1 = new ResourceSetImpl();
						Resource resource = resourceSet1.getResource(URI.createURI(PivotPackage.eNS_URI, true), true);
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						resource.setURI(URI.createPlatformResourceURI(testProjectName + "/" + "Pivot.oclas", true));
						resource.save(outputStream, XMIUtil.createSaveOptions());

						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IProject project = workspace.getRoot().getProject(testProjectName);
						if (!project.exists()) {
							project.create(null);
						}
						project.open(null);
						IFile file = project.getFile("Pivot.oclas");
						file.create(new ByteArrayInputStream(outputStream.toByteArray()), true, null);

						IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
						EcoreEditor openEditor = (EcoreEditor) IDE.openEditor(activePage, file, "org.eclipse.emf.ecore.presentation.ReflectiveEditorID", true);
						TestUIUtil.flushEvents();
						ResourceSet resourceSet = openEditor.getEditingDomain().getResourceSet();
						EList<Resource> resources = resourceSet.getResources();
						assertEquals(1, resources.size());
						Resource resource2 = ClassUtil.nonNullState(resources.get(0));
						assertNoResourceErrors("Load", resource2);
						assertNoValidationErrors("Validate", resource2);
						openEditor.getSite().getPage().closeEditor(openEditor, false);
						TestUIUtil.flushEvents();
					}
					ocl.dispose();
				}
			});
		}
		catch (UnsupportedClassVersionError e) {
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {		// Standalone test using Java 8 fail on latest platform classes
				throw e;
			}
		}
	}

	/**
	 * Verify that the Bug469251.uml model can be loaded and validated as a *.uml file by the
	 * UML MOdel Ecore Editor.
	 * @throws Throwable
	 */
	public void testOpen_Bug469251_uml() throws Throwable {
		try {
			doTestRunnable(new TestRunnable() {
				@Override
				public void runWithThrowable() throws Exception {
					TestOCL ocl = createOCL();
					if (EMFPlugin.IS_ECLIPSE_RUNNING) {
						TestCaseAppender.INSTANCE.uninstall();
						TestUIUtil.suppressGitPrefixPopUp();
						IWorkbench workbench = PlatformUI.getWorkbench();
						TestUIUtil.closeIntro();
						TestUIUtil.flushEvents();
						//
						getTestFile("Bug469251.profile.uml", ocl, getTestModelURI("models/uml/Bug469251.profile.uml"));
						getTestFile("Bug469251.uml", ocl, getTestModelURI("models/uml/Bug469251.uml"));
						//
						IProject iProject = getTestProject().getIProject();
						IFile modelFile = iProject.getFile("Bug469251.uml");
						IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
						UMLEditor umlEditor = (UMLEditor) IDE.openEditor(activePage, modelFile, "org.eclipse.uml2.uml.editor.presentation.UMLEditorID", true);
						TestUIUtil.flushEvents();
						/**
						 * This progresses the dialog but there is no clue as to what it did.
						 * ?? surely it was an attempt at a manual Validate menu action ??
						 *
						String validateName = EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_menu_item");
						IMenuManager menuManager = umlEditor.getActionBars().getMenuManager();
						IContributionManager validateItem1 = (IContributionManager) menuManager.findUsingPath("org.eclipse.uml2.umlMenuID");
						for (IContributionItem item : validateItem1.getItems()) {
							if (item instanceof ActionContributionItem){
								IAction action = ((ActionContributionItem)item).getAction();
								if (action.getText().equals(validateName)) {
									final Display display = Display.getCurrent();
									display.timerExec(5000, new Runnable()
									{
										public void run() {
											Event event = new Event();
											event.type = SWT.KeyDown;
											event.character = '\r';
											display.post(event);
										}
									});
									action.run();
									break;
								}
							}
						} */
						ResourceSet resourceSet = umlEditor.getEditingDomain().getResourceSet();
						EList<Resource> resources = resourceSet.getResources();
						assertEquals(2, resources.size());
						Resource umlResource = ClassUtil.nonNullState(resources.get(0));
						Model model = (Model) umlResource.getContents().get(0);
						org.eclipse.uml2.uml.Type xx = model.getOwnedType("Class1");
						assertNoResourceErrors("Load", umlResource);
						assertValidationDiagnostics("Validate", umlResource, getMessages(
							EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[]{"Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx)})));
						umlEditor.getSite().getPage().closeEditor(umlEditor, false);
						TestUIUtil.flushEvents();
					}
					ocl.dispose();
				}
			});
		}
		catch (UnsupportedClassVersionError e) {
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {		// Standalone test using Java 8 fail on latest platform classes
				throw e;
			}
		}
	}

	public void testPivotMetamodelImport414855() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				String testFileStem = "Bug414855";
				String testProjectName = "bug414855";
				String oclinecoreFile =
						"import pivot : 'http://www.eclipse.org/ocl/2015/Pivot#/';\n"
								+ "package bug414855 : bug414855 = 'http://bug414855'\n"
								+ "{\n"
								+ "    datatype MyString : 'java.lang.String' { serializable };\n"
								+ "    class ClassExtension extends pivot::Class {}\n"
								+ "}\n";
				Map <@NonNull String, @Nullable String> genOptions = new HashMap<>();
				genOptions.put("dynamicTemplates", "false");
				genOptions.put("templateDirectory", "/org.eclipse.ocl.examples.codegen/templates");
				genOptions.put("usedGenPackages", "platform:/plugin/org.eclipse.ocl.pivot/model/Pivot.genmodel#//pivot");
				String genmodelFile = createEcoreGenModelContent(testFileStem, genOptions);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}

	public void testTemplateTypes471201() throws Throwable {
		doTestRunnable(new TestRunnable() {
			@Override
			public void runWithThrowable() throws Exception {
				// FIXME next line compensates an uninstall overenthusiasm
				EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, OCLstdlibPackage.eINSTANCE);
				String testFileStem = "Bug471201";
				String testProjectName = "bug471201";
				String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
						+ "package bug471201 : bug471201 = 'http://bug471201'\n"
						+ "{\n"
						+ "    class NamedElement {}\n"
						+ "    class LookupEnvironment\n"
						+ "    {\n"
						+ "		operation(NE extends NamedElement) addElements(elements : NE[*] { ordered }) : LookupEnvironment[1]\n"
						+"		{\n"
						+ "			body: if elements->notEmpty() then addElements(OrderedSet(NamedElement){}) else self endif;\n"
						+ "		}\n"
						+ "    }\n"
						+ "}\n";
				String genmodelFile = createEcoreGenModelContent(testFileStem, null);
				createManifestFile();
				URI genModelURI = createModels(testFileStem, oclinecoreFile, genmodelFile);
				doGenModel(genModelURI);
				TestOCL ocl = createOCL();
				doEcoreCompile(ocl, testProjectName);
				ocl.dispose();
			}
		});
	}
}
