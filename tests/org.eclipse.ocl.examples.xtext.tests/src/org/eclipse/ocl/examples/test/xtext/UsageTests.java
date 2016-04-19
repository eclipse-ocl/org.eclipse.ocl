/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGeneratorAdapterFactory;
import org.eclipse.ocl.examples.pivot.tests.PivotTestSuite;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.editor.presentation.UMLEditor;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.util.EmfFormatter;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a
 * result.
 */
public class UsageTests
		extends PivotTestSuite// XtextTestCase
{

	private static final class JavaSourceFileObject
			extends SimpleJavaFileObject {

		private JavaSourceFileObject(java.net.URI uri) {
			super(uri, Kind.SOURCE);
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			char[] buf = new char[4096];
			StringBuffer s = new StringBuffer();
			Reader reader = new FileReader(new File(uri));
			try {
				int len;
				while ((len = reader.read(buf)) > 0) {
					s.append(buf, 0, len);
				}
			}
			finally {
				reader.close();
			}
			return s.toString();
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
	public void checkResourceSet(ResourceSet resourceSet)
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
			throw new RuntimeException("Errors in ResourceSet");
		}
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestPackageName(), getName(), getProjectMap());
	}

	protected void getCompilationUnits(@NonNull List<JavaFileObject> compilationUnits,
			@NonNull File directory) throws Exception {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					getCompilationUnits(compilationUnits, file);
				} else if (file.isFile()) {
//					System.out.println("Compiling " + file);
					compilationUnits.add(new JavaSourceFileObject(file.toURI()));
				}
			}
		}
	}

	protected void getCompilationUnits(@NonNull List<JavaFileObject> compilationUnits,
			@NonNull IContainer container) throws CoreException {
		for (IResource member : container.members()) {
			if (member instanceof IContainer) {
				getCompilationUnits(compilationUnits, (IContainer) member);
			} else if ((member instanceof IFile)
				&& member.getFileExtension().equals("java")) {
				java.net.URI locationURI = member.getLocationURI();
//				System.out.println("Compiling " + locationURI);
				compilationUnits.add(new JavaSourceFileObject(locationURI));
			}
		}
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		log = Logger.getLogger(UsageTests.class);
		// AcceleoNature.class.getName(); // Pull in the plugin for Hudson
		TestUtil.doOCLinEcoreSetup();
		configurePlatformResources();
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
//			.put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown()
			throws Exception {
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
					projectPath =  uri.toFileString().replace("\\", "/");
				}
				assert projectPath != null;
				if (projectPath.endsWith("/")) {
					projectPath = projectPath + "bin";
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
	
	public @NonNull String createGenModelContent(@NonNull String testProjectPath, @NonNull String fileName, @Nullable String usedGenPackages) {
		StringBuilder s = new StringBuilder();
		s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		s.append("<genmodel:GenModel xmi:version=\"2.0\"\n");
		s.append("    xmlns:xmi=\"http://www.omg.org/XMI\"\n");
		s.append("    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\"\n");
		s.append("    xmlns:genmodel=\"http://www.eclipse.org/emf/2002/GenModel\"\n");
		s.append("    modelDirectory=\"/" + testProjectPath + "/src-gen\"\n");
		s.append("    modelPluginID=\"" + fileName + "." + fileName + "\"\n");
		s.append("    modelName=\"" + fileName + "\"\n");
		s.append("    importerID=\"org.eclipse.emf.importer.ecore\"\n");
		s.append("    complianceLevel=\"8.0\"\n");
		s.append("    operationReflection=\"true\"\n");
		s.append("    copyrightFields=\"false\"\n");
		s.append("    bundleManifest=\"false\"\n");
		s.append("    pluginKey=\"\"\n");
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
		s.append("    ecorePackage=\"" + fileName + ".ecore#/\">\n");
		s.append("  </genPackages>\n");
		s.append("</genmodel:GenModel>\n");
		s.append("\n");;
		return s.toString();
	}

	public @NonNull URI createGenModelFile(String fileName, String fileContent) throws IOException {
		File file = new File(getProjectFile(), fileName);
		Writer writer = new FileWriter(file);
		writer.append(fileContent);
		writer.close();
		return getProjectFileURI(fileName);
	}

	protected @NonNull URI createModels(@NonNull String testProjectName, @NonNull String testFileStem,
			@Nullable String oclinecoreFile, @NonNull String genmodelFile)
			throws CoreException, IOException {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IProject project = TestUtil.createJavaProject(testProjectName);
			TestUtil.createManifest(project, testProjectName, null, null, null);
		}
		OCL ocl2 = OCL.newInstance(getProjectMap());
		if (oclinecoreFile != null) {
			createEcoreFile(ocl2, testFileStem, oclinecoreFile);
		}
		URI genModelURI = createGenModelFile(testFileStem + ".genmodel", genmodelFile);
		// System.out.println("Generating Ecore Model using '" + genModelURI + "'");
		ocl2.dispose();
		return genModelURI;
	}

	protected boolean doCompile(@NonNull String testProjectName, @NonNull String... extraClasspathProjects) throws Exception {
		List<String> classpathProjects = new ArrayList<String>();
		classpathProjects.add("org.eclipse.emf.common");
		classpathProjects.add("org.eclipse.emf.ecore");
		classpathProjects.add("org.eclipse.jdt.annotation");
		classpathProjects.add("org.eclipse.ocl.pivot");
		classpathProjects.add("org.eclipse.osgi");
		for (String extraClasspathProject : extraClasspathProjects) {
			classpathProjects.add(extraClasspathProject);
		}
		List<String> compilationOptions = new ArrayList<String>();
		compilationOptions.add("-d");
		compilationOptions.add("bin");
//		compilationOptions.add("-source");
//		compilationOptions.add("1.5");
//		compilationOptions.add("-target");
//		compilationOptions.add("1.5");
		compilationOptions.add("-g");
		List<JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>();
		Object context = null;
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(testProjectName);
			if (project != null) {
				getCompilationUnits(compilationUnits, project);
				java.net.URI locationURI = project.getLocationURI();
				String binURI = URIUtil.toUnencodedString(locationURI) + "/bin";
				URI uri = URI.createURI(binURI);
				if (uri.isFile()) {
					binURI = uri.toFileString().replace("\\", "/");
				}
				compilationOptions.set(1, binURI);
				new File(locationURI.getPath() + "/bin").mkdirs();
				compilationOptions.add("-cp");
				String path = createClassPath(classpathProjects);
				compilationOptions.add(path);
			}
			context = project;
		} else {
			File dir = new File("src-gen/" + testProjectName);
			getCompilationUnits(compilationUnits, dir);
			context = dir;
		}

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager stdFileManager = compiler
			.getStandardFileManager(null, Locale.getDefault(), null);

		// System.out.printf("%6.3f getTask\n", 0.001 *
		// (System.currentTimeMillis()-base));
		CompilationTask compilerTask = compiler.getTask(null, stdFileManager,
			diagnostics, compilationOptions, null, compilationUnits);
		// System.out.printf("%6.3f call\n", 0.001 *
		// (System.currentTimeMillis()-base));
		if (!compilerTask.call()) {
			StringBuilder s2 = new StringBuilder();
			s2.append("javac");
			for (String compilationOption : compilationOptions) {
				s2.append(" ");
				s2.append(compilationOption);
			}
			for (JavaFileObject compilationUnit : compilationUnits) {
				s2.append("\n  ");
				s2.append(compilationUnit);
			}
			System.out.println(s2.toString());
			StringBuilder s = new StringBuilder();
			for (javax.tools.Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				s.append("\n" + diagnostic);
			}
			if (s.length() > 0) {
				throw new IOException("Failed to compile " + context + s.toString());
			}
			System.out.println("Compilation of " + context + " returned false but no diagnostics");
		}
		// System.out.printf("%6.3f close\n", 0.001 *
		// (System.currentTimeMillis()-base));
		stdFileManager.close(); // Close the file manager which re-opens automatically
		// System.out.printf("%6.3f forName\n", 0.001 *
		// (System.currentTimeMillis()-base));
		// Class<?> testClass = Class.forName(qualifiedName);
		// return testClass;

		return true;
	}

	public static void doDelete(@NonNull String testProjectName) throws Exception {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUIUtil.deleteIProject(testProjectName);
		} else {
			File dir = new File("src-gen/" + testProjectName);
			if (dir.exists()) {
				TestUtil.deleteDirectory(dir);
			}
		}
	}

	protected void doGenModel(@NonNull String testProjectName, @NonNull URI genmodelURI) throws Exception {
		OCL ocl = createOCL();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestUIUtil.suppressGitPrefixPopUp();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(testProjectName);
			if (!project.exists()) {
				project.create(null);
				project.open(null);
			}
		}
//		MetamodelManager metamodelManager2 = new MetamodelManager();
//		metamodelManager = metamodelManager2;
//		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor( org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI, OCLinEcoreGeneratorAdapterFactory.DESCRIPTOR);
		URI fileURI = genmodelURI; //getProjectFileURI(testFileStem + ".genmodel");
		// System.out.println("Generating Ecore Model using '" + fileURI + "'");
//		metamodelManager2.dispose();
		ResourceSet resourceSet = ocl.getResourceSet();
		ProjectManager projectMap = ocl.getProjectManager();
		projectMap.configure(resourceSet, StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		resourceSet.getPackageRegistry().put(org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI,  org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
		//FIXME this is needed so long as Pivot.genmodel is a UML genmodel
		resourceSet.getPackageRegistry().put(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI,  org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor( org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI, GenModelGeneratorAdapterFactory.DESCRIPTOR);
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor( org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI, OCLinEcoreGeneratorAdapterFactory.DESCRIPTOR);
		if (resourceSet instanceof ResourceSetImpl) {
			ResourceSetImpl resourceSetImpl = (ResourceSetImpl) resourceSet;
			Map<URI, Resource> uriResourceMap = resourceSetImpl.getURIResourceMap();
			if (uriResourceMap != null) {
				uriResourceMap.clear();
			}
		}
		resourceSet.getResources().clear();
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
			fail(diagnostic.toString());
		}

		/*
		 * JavaModelManager.getJavaModelManager().initializePreferences(); new
		 * JavaCorePreferenceInitializer().initializeDefaultPreferences();
		 * 
		 * GenJDKLevel genSDKcomplianceLevel = genModel.getComplianceLevel();
		 * String complianceLevel = JavaCore.VERSION_1_5; switch
		 * (genSDKcomplianceLevel) { case JDK60_LITERAL: complianceLevel =
		 * JavaCore.VERSION_1_6; case JDK14_LITERAL: complianceLevel =
		 * JavaCore.VERSION_1_4; default: complianceLevel =
		 * JavaCore.VERSION_1_5; } // Hashtable<?,?> defaultOptions =
		 * JavaCore.getDefaultOptions(); //
		 * JavaCore.setComplianceOptions(complianceLevel, defaultOptions); //
		 * JavaCore.setOptions(defaultOptions);
		 */
		
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

	public void testBug370824() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug370824";
		String testProjectName = "bug370824";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
		String oclinecoreFile = "package bug370824 : bug370824 = 'http://bug370824'\n"
			+ "{\n"
			+ "    class Clase1\n"
			+ "    {\n"
			+ "        invariant : self.name.size() > 0;\n"
			+ "        attribute name : String[?] { ordered };\n"
			+ "    }\n"
			+ "}\n";
		String genmodelFile = createGenModelContent(testProjectPath, "Bug370824", null);
		URI genModelURI = createModels(testProjectName, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		ocl.dispose();
	}

	public void testBug409650() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug409650";
		String testProjectName = "bug409650";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, "Bug409650", null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
			String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
			EPackage ePackage = doLoadPackage(qualifiedPackageName);
//			System.out.println("Loaded " + ePackage);
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
		}
		ocl.dispose();
	}

	public void testEcoreTypes412736() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug412736";
		String testProjectName = "bug412736";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, "Bug412736", null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
			String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
			EPackage ePackage = doLoadPackage(qualifiedPackageName);
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
		}
		ocl.dispose();
	}

	public void testEnumTypes412685() throws Exception {
		TestOCL ocl = createOCL();
		// FIXME next line compensates an uninstall overenthusiasm
		EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, OCLstdlibPackage.eINSTANCE);
		String testFileStem = "Bug412685";
		String testProjectName = "bug412685";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectName, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
			String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
			EPackage ePackage = doLoadPackage(qualifiedPackageName);
			EClass eClass = (EClass) ePackage.getEClassifier("EnumTypes");
			EFactory eFactory = ePackage.getEFactoryInstance();
			//
			EObject eObject = eFactory.create(eClass);
			ocl.assertQueryTrue(eObject, "let aWhite : OclAny = opaqueColor(eWhite) in eColor = aWhite");
			ocl.assertQueryTrue(eObject, "let aWhite : OclAny = eWhite.oclAsType(OclAny) in eColor = aWhite");
			ocl.assertQueryTrue(eObject, "eColor = eWhite");
			ocl.assertQueryTrue(eObject, "eColor = Color::WHITE");
		}
		ocl.dispose();
	}

	public void testTemplateTypes471201() throws Exception {
		TestOCL ocl = createOCL();
		// FIXME next line compensates an uninstall overenthusiasm
		EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, OCLstdlibPackage.eINSTANCE);
		String testFileStem = "Bug471201";
		String testProjectName = "bug471201";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectName, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}

	public void testCSE() throws Exception {
		TestOCL ocl = createOCL();
//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
		String testFileStem = "CSEs";
		String testProjectName = "cses";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
		String oclinecoreFile = "import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n"
			+ "package cses : cses = 'http://cses'\n"
			+ "{\n"
			+ "    class CSEs\n"
			+ "    {\n"
//			+ "        attribute a : ecore::EInt = '3' { readonly };\n"
			+ "        operation test(a : ecore::EInt, b : ecore::EInt, c : ecore::EInt) : ecore::EInt { body: if a + b + c > 0 then a + b + c else a + b endif; }\n"
			+ "    }\n"
			+ "}\n";
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
			doCompile(testProjectName);
			String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
			EPackage ePackage = doLoadPackage(qualifiedPackageName);
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
		}
		ocl.dispose();
	}

/*	private void assertCallCount(ExpressionInOCL query, @Nullable LibraryOperation calledOperation, int expectedCount) {
		List<CGOperationCallExp> calls = new ArrayList<CGOperationCallExp>();
		for (TreeIterator<EObject> tit = query.getBodyExpression().eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof CGOperationCallExp) {
				CGOperationCallExp callExp = (CGOperationCallExp)eObject;
				if (calledOperation == null) {
					calls.add(callExp);
				} else if (callExp.getReferredOperation().getImplementation() == calledOperation) {
					calls.add(callExp);
				}
			}
		}
		if (calledOperation == null) {
			assertEquals("Mismatching call count", expectedCount, calls.size());
		}
		else {
			assertEquals("Mismatching call count of " + calledOperation, expectedCount, calls.size());
		}
	} */
	public void testEvaluators() throws Exception {
		TestOCL ocl = createOCL();
//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
		String testFileStem = "Evaluators";
		String testProjectName = "evaluators";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
			doCompile(testProjectName);
			String qualifiedPackageName = testProjectName + "." + testFileStem + "Package";
			EPackage ePackage = doLoadPackage(qualifiedPackageName);
			EClass eClass = (EClass) ePackage.getEClassifier("Evaluators");
			EFactory eFactory = ePackage.getEFactoryInstance();
			//
			EObject eObject = eFactory.create(eClass);
			ocl.assertQueryEquals(eObject, "testString", "test()");
		}
		ocl.dispose();
	}

	public void testSysML_QUDV() throws Exception {
		TestOCL ocl = createOCL();
		try {
	//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
	//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
	//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
	//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
	//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
	//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
			String testProjectName = "SysML_ValueTypes_QUDV";
			String testProjectPath = /*EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName :*/ ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				doDelete(ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS);
				@NonNull URI ecoreURI = URI.createPlatformPluginURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/SysML_ValueTypes_QUDV.ecore", true);
				@NonNull URI genModelURI = URI.createPlatformPluginURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/SysML_ValueTypes_QUDV.genmodel", true);
				IProject project = TestUtil.createJavaProject(ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS);
				TestUtil.createClassPath(project, new @NonNull String @NonNull []{"src-gen"});
				TestUtil.createManifest(project, testProjectPath,
					new @NonNull String @NonNull []{"org.eclipse.osgi", "org.eclipse.emf.ecore", "org.eclipse.jdt.annotation", "org.eclipse.ocl.pivot"},
					null,
					new @NonNull String @NonNull []{"SysML_ValueTypes_QUDV", "SysML_ValueTypes_QUDV.PrimitiveValueTypes", "SysML_ValueTypes_QUDV.QUDV", "SysML_ValueTypes_QUDV.UnitAndQuantityKind"});
				TestUtil.copyIFile(ocl, genModelURI, project, "model/SysML_ValueTypes_QUDV.genmodel");
				TestUtil.copyIFile(ocl, ecoreURI, project, "model/SysML_ValueTypes_QUDV.ecore");
			}
			else {
				doDelete(testProjectName);
			}
			@NonNull URI genModelURI = URI.createPlatformResourceURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/SysML_ValueTypes_QUDV.genmodel", true);
			if (!ocl.getResourceSet().getURIConverter().exists(genModelURI, null)) {
				return;
			}			
			doGenModel(testProjectPath, genModelURI);
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
				doCompile(testProjectName);
				String qualifiedPackageName = testProjectName + ".QUDV.QUDVPackage";
				EPackage ePackage = doLoadPackage(qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("DerivedQuantityKind");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryTrue(eObject, "dependsOnQuantityKinds() <> null");
			}
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
				String qualifiedPackageName = testProjectName + ".PrimitiveValueTypes.PrimitiveValueTypesPackage";
				EPackage ePackage = doLoadPackage(qualifiedPackageName);
				EClass eClass = (EClass) ePackage.getEClassifier("Complex");
				EFactory eFactory = ePackage.getEFactoryInstance();
				//
				EObject eObject = eFactory.create(eClass);
				ocl.assertQueryTrue(eObject, "imaginaryPart = realPart");
				ocl.assertQueryTrue(eObject, "oclType() <> null");
				ocl.assertQueryTrue(eObject, "oclType().oclIsKindOf(OclAny)");
			}
		}
		finally {
			ocl.dispose();
		}
	}

	public void testCodegenCompany() throws Exception {
		TestOCL ocl = createOCL();
		try {
	//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
	//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
	//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
	//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
	//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
	//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
			String testProjectName = "codegen/company";
			String testProjectPath = /*EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName :*/ ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				doDelete(ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS);
				@NonNull URI ecoreURI = URI.createPlatformPluginURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/Company.ecore", true);
				@NonNull URI genModelURI = URI.createPlatformPluginURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/CodeGenCompany.genmodel", true);
				IProject project = TestUtil.createJavaProject(ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS);
				TestUtil.createClassPath(project, new @NonNull String @NonNull []{"src-gen"});
				TestUtil.createManifest(project, testProjectPath,
					new @NonNull String @NonNull []{"org.eclipse.osgi", "org.eclipse.emf.ecore", "org.eclipse.jdt.annotation", "org.eclipse.ocl.pivot"},
					null,
					new @NonNull String @NonNull []{"company"});
				TestUtil.copyIFile(ocl, genModelURI, project, "model/CodeGenCompany.genmodel");
				TestUtil.copyIFile(ocl, ecoreURI, project, "model/Company.ecore");
			}
			else {
				doDelete(testProjectName);
			}
			@NonNull URI genModelURI = URI.createPlatformResourceURI("/" + ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/model/CodeGenCompany.genmodel", true);
			if (!ocl.getResourceSet().getURIConverter().exists(genModelURI, null)) {
				return;
			}			
			doGenModel(testProjectPath, genModelURI);
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) { // FIXME find out how to get dynamic project onto classpath
				doCompile(testProjectName);
			}
		}
		finally {
			ocl.dispose();
		}
	}

	protected EPackage doLoadPackage(@NonNull String qualifiedModelPackageName) throws Exception {
		Class<?> testClass = Class.forName(qualifiedModelPackageName);
//		System.out.println("Loaded " + testClass.getName());
		Object eInstance = testClass.getDeclaredField("eINSTANCE").get(null);
		return (EPackage) eInstance;
	}

	public void testInitStatics() {
		assertTrue(ValueUtil.initAllStatics());
		assertFalse(ValueUtil.initAllStatics());
	}

	/**
	 * Verify that the Pivot metamodel can be loaded and validated as a *.oclas file by the
	 * Sample Reflective Ecore Editor.
	 */
	public void testOpen_Pivot_oclas() throws Exception {
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
			resource.save(outputStream, null);
			
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject project = workspace.getRoot().getProject(testProjectName);
			if (!project.exists()) {
				project.create(null);
			}
			project.open(null);
			IFile file = project.getFile("Pivot.oclas");
			file.create(new ByteArrayInputStream(outputStream.toByteArray()), true, null);
			
//			Bundle bundle = Platform.getBundle("org.eclipse.ocl.pivot");
//			String location = bundle.getLocation() + "/model-gen/Pivot.oclas";
//			java.net.URI uri = new java.net.URI(location.substring(location.indexOf("file:")));
			IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
			EcoreEditor openEditor = (EcoreEditor) IDE.openEditor(activePage, file, "org.eclipse.emf.ecore.presentation.ReflectiveEditorID", true);
			TestUIUtil.flushEvents();
			ResourceSet resourceSet = openEditor.getEditingDomain().getResourceSet();
			EList<Resource> resources = resourceSet.getResources();
			assertEquals(1, resources.size());
			Resource resource2 = ClassUtil.nonNullState(resources.get(0));
			assertNoResourceErrors("Load", resource2);
			assertNoValidationErrors("Validate", resource2);
//			for (int i = 0; i < 1000; i++){
//				flushEvents();
//				Thread.sleep(100);
//			}
			openEditor.dispose();
		}
		ocl.dispose();
	}

	/**
	 * Verify that the Bug469251.uml model can be loaded and validated as a *.uml file by the
	 * UML MOdel Ecore Editor.
	 */
	public void testOpen_Bug469251_uml() throws Exception {
		TestOCL ocl = createOCL();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			TestCaseAppender.INSTANCE.uninstall();
			TestUIUtil.suppressGitPrefixPopUp();
			IWorkbench workbench = PlatformUI.getWorkbench();
			IIntroManager introManager = workbench.getIntroManager();
			introManager.closeIntro(introManager.getIntro());
			TestUIUtil.flushEvents();
			
			String testProjectName = "Open_Bug469251_uml";
/*			ResourceSet resourceSet1 = new ResourceSetImpl();
			getProjectFile().("Bug469251.uml");
			Resource resource = resourceSet1.getResource(URI.createURI(PivotPackage.eNS_URI, true), true);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			resource.setURI(URI.createPlatformResourceURI(testProjectName + "/" + "Bug469251.uml", true));
			resource.save(outputStream, null); */
			
			IProject iProject = TestUIUtil.createIProject(testProjectName);
			@SuppressWarnings("unused")IFile profileFile = TestUIUtil.copyIFile(iProject.getFile("Bug469251.profile.uml"), getProjectFileURI("Bug469251.profile.uml"), null);
			IFile modelFile = TestUIUtil.copyIFile(iProject.getFile("Bug469251.uml"), getProjectFileURI("Bug469251.uml"), null);
			
//			Bundle bundle = Platform.getBundle("org.eclipse.ocl.pivot");
//			String location = bundle.getLocation() + "/model-gen/Pivot.oclas";
//			java.net.URI uri = new java.net.URI(location.substring(location.indexOf("file:")));
			IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
			UMLEditor umlEditor = (UMLEditor) IDE.openEditor(activePage, modelFile, "org.eclipse.uml2.uml.editor.presentation.UMLEditorID", true);
			TestUIUtil.flushEvents();
			/**
			 * This progresses the dialog but there is no clue as to what it did.
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
			assertValidationDiagnostics("Validate", umlResource,
				EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[]{"Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx)}));
			umlEditor.dispose();
		}
		ocl.dispose();
	}

	public void testPivotMetamodelImport414855() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug414855";
		String testProjectName = "bug414855";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
		String oclinecoreFile =
			"import pivot : 'http://www.eclipse.org/ocl/2015/Pivot#/';\n"
			+ "package bug414855 : bug414855 = 'http://bug414855'\n"
			+ "{\n"
			+ "    datatype MyString : 'java.lang.String' { serializable };\n"
			+ "    class ClassExtension extends pivot::Class {}\n"
			+ "}\n";
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, "platform:/plugin/org.eclipse.ocl.pivot/model/Pivot.genmodel#//pivot");
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}
	
	public void testBug415782() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug415782";
		String testProjectName = "bug415782";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}
	
	public void testBug416421() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStemA = "Bug416421A";
		String testProjectNameA = "bug416421A";
		String testProjectPathA = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectNameA : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFileA = createGenModelContent(testProjectPathA, testFileStemA, null);
		String testFileStemB = "Bug416421B";
		String testProjectNameB = "bug416421B";
		String testProjectPathB = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectNameB : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFileB = createGenModelContent(testProjectPathB, testFileStemB, "Bug416421A.genmodel#//bug416421A");
		doDelete(testProjectNameA);
		doDelete(testProjectNameB);
		URI genModelURIA = createModels(testProjectPathA, testFileStemA, oclinecoreFileA, genmodelFileA);
		URI genModelURIB = createModels(testProjectPathB, testFileStemB, oclinecoreFileB, genmodelFileB);
		// B first demonstrates the demand load of Bug416421A to fix Bug 416421
		doGenModel(testProjectPathB, genModelURIB);
		doGenModel(testProjectPathA, genModelURIA);
		doCompile(testProjectNameA);
		doCompile(testProjectNameB, testProjectNameA);
		ocl.dispose();
	}
	
	public void testBug458722() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug458722";
		String testProjectName = "bug458722";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}
	
	public void testBug458723() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug458723";
		String testProjectName = "bug458723";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}
	
	public void testBug458724() throws Exception {
		TestOCL ocl = createOCL();
		String testFileStem = "Bug458724";
		String testProjectName = "bug458724";
		String testProjectPath = EMFPlugin.IS_ECLIPSE_RUNNING ? testProjectName : ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS;
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
		String genmodelFile = createGenModelContent(testProjectPath, testFileStem, null);
		doDelete(testProjectName);
		URI genModelURI = createModels(testProjectPath, testFileStem, oclinecoreFile, genmodelFile);
		doGenModel(testProjectPath, genModelURI);
		doCompile(testProjectName);
		ocl.dispose();
	}
}
