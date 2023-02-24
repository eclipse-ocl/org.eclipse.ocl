/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteURIs;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

import com.google.common.collect.Lists;

public abstract class GenerateASModels extends GenerateOCLCommonXtend
{
	protected String stdlibFile;
	protected String ecoreFile;
//	protected String libraryName;
//	protected String libraryNsPrefix;
//	protected boolean isASLibrary = true;
//	protected boolean useOCLstdlib = false;
	protected @NonNull List<@NonNull String> excludedEClassifierNames = new ArrayList<>();

	protected Comparator<org.eclipse.ocl.pivot.@NonNull Class> superClassComparator = new Comparator<org.eclipse.ocl.pivot.@NonNull Class>()
	{
		@Override
		public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
			@NonNull List<@NonNull String> NAMES = Lists.newArrayList("Namespace");
			int i1 = o1.isIsInterface() ? 1 : 0;
			int i2 = o2.isIsInterface() ? 1 : 0;
			int diff = i1 - i2;
			if (diff != 0) {			// Non-interface first
				return diff;
			}
			StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
			FlatClass f1 = o1.getFlatClass(standardLibrary);
			FlatClass f2 = o2.getFlatClass(standardLibrary);
			int d1 = f1.getDepth();
			int d2 = f2.getDepth();
			diff = d2 - d1;
			if (diff != 0) {			// Deepest first
				return diff;
			}
			String n1 = o1.getName();
			String n2 = o2.getName();
			int x1 = NAMES.indexOf(n1);
			int x2 = NAMES.indexOf(n2);
			diff = x1 - x2;
			if (diff != 0) {			// Unknown first
				return diff;
			}
			return n1.compareTo(n2);	// Lexicographical
		}
	};

	protected abstract /*@NonNull*/ String generateMetamodel(@NonNull Collection<@NonNull String> excludedEClassifierNames);

	public void addExcludedEClassifierName(String excludedEClassifierName) {
		assert excludedEClassifierName != null;
		excludedEClassifierNames.add(excludedEClassifierName);
	}

	/*	@Override
	protected String getExternalReference(@NonNull Element element) {
		String generatedClassName = getGeneratedClassName(element);
		if (isOCLstdlib(element)) {
			return "OCLstdlib.getDefaultPackage()";
		}
		return super.getExternalReference(element);
	} */

	protected @Nullable Library getLibrary(@NonNull Model root) {
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Library) {
				return (Library) eObject;
			}
		}
		return null;
	}

	@Override
	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull CollectionType>> getSortedCollectionTypes(@NonNull Model root) {
		return super.getSortedCollectionTypes(root, contentAnalysis.monikerComparator);
	}

	@Override
	protected @NonNull Model getThisModel() {
		return ClassUtil.nonNullState(thisModel);
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		assert issues != null;
	//	StandardLibraryContribution savedContribution = null;
	//	if (!useOCLstdlib) {
	//		savedContribution = StandardLibraryContribution.REGISTRY.remove(OCLstdlib.STDLIB_URI);
	//	}
		String rootPath = StandaloneSetup.getPlatformRootPath();
		File folder = new File(rootPath + javaFolder + "/" + javaPackageName.replace(".", "/"));
		String sourceStdlibFile = "/" + projectName + "/" + stdlibFile;
		sourceFile = "/" + projectName + "/" + modelFile;
		URI pivotFileURI = URI.createPlatformResourceURI(sourceFile, true);
		URI stdlibFileURI = URI.createPlatformResourceURI(sourceStdlibFile, true);
		URI mergedFileURI = URI.createPlatformResourceURI(sourceFile + ".merged.oclas", true);
		log.info("Loading OCL library '" + stdlibFileURI);
		OCLInternal ocl = getOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		try {
			setEnvironmentFactory(ocl.getEnvironmentFactory());
			ASResource asResource1 = loadOCLstdlibFile(stdlibFileURI, issues);
			if (asResource1 == null) {
				return;
			}
			ASResource asResource2 = loadEcoreFile(pivotFileURI);
			if (asResource2 == null) {
				return;
			}
			ASResource asResource = mergeResources(mergedFileURI, asResource1, asResource2);
			if (asResource == null) {
				return;
			}
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(ASResource.OPTION_NORMALIZE_CONTENTS, Boolean.TRUE);
			options.put(AS2ID.DEBUG_LUSSID_COLLISIONS, Boolean.TRUE);
			options.put(AS2ID.DEBUG_XMIID_COLLISIONS, Boolean.TRUE);
			asResource.setURI(mergedFileURI);
			PivotUtil.getModel(asResource).setExternalURI(mergedFileURI.toString());
			asResource.setSaveable(true);
			asResource.save(options);		// XXX early debug save
			Model pivotModel = (Model)ClassUtil.nonNullState(asResource.getContents().get(0));
		//	assert pivotModel.getOwnedPackages().size() == 1;				// No orphanage, but may have an implicit package, so 100% to synthesize
		/*	ASSaverWithInverse saver = new ASSaverWithInverse(asResource);
			saver.localizeOrphans();
			String fileName = folder + "/" + javaClassName + ".java";
		//	log.info("Generating '" + fileName + "'");
			initModel(pivotModel, saver);
			@SuppressWarnings("null")@NonNull String metamodel = generateMetamodel(excludedEClassifierNames);
			MergeWriter fw = new MergeWriter(fileName);
			fw.append(metamodel);
			fw.close();
			String saveFile = "/" + projectName + "/" + modelFile.replace("model", "model-gen").replace("oclstdlib", "oclas");
			URI saveURI = URI.createPlatformResourceURI(saveFile, true);
		//	log.info("Loading '" + saveURI + "'");
			log.info("Saving '" + saveURI + "'");
			asResource.setURI(saveURI);
			asResource.setSaveable(true);
			asResource.save(options); */
			for (Resource resource : asResource.getResourceSet().getResources()) {
				String saveMessage = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getErrors()), "Save", "\n\t");
				if (saveMessage != null) {
					issues.addError(this, saveMessage, null, null, null);
					return;
				}
			}
			if (ecoreFile != null) {
				@NonNull URI ecoreURI = URI.createPlatformResourceURI(ecoreFile, true);
				AS2Ecore converter = new AS2Ecore(getEnvironmentFactory(), ecoreURI, null);
				XMLResource eResource = converter.convertResource(asResource, ecoreURI);
				EPackage ePackage = (EPackage) ClassUtil.nonNullState(eResource.getContents().get(0));
			//	if (libraryName != null) {
			//		ePackage.setName(libraryName);
			//	}
			//	if (libraryNsPrefix != null) {
			//		ePackage.setNsPrefix(libraryNsPrefix);
			//	}
				// FIXME EClass instanceClassNames are correct because they were loaded correct.
				// FIXME EClass setInstanceClassName trashes EClasses that are set explicitly
				//				setInstanceClassName(ePackage, "Bag", Bag.class, null);
				setInstanceClassName(ePackage, TypeId.BOOLEAN_NAME, Boolean.class, null);
				//				setInstanceClassName(ePackage, "Collection", Collection.class, null);
				setInstanceClassName(ePackage, TypeId.INTEGER_NAME, IntegerValue.class, null);
				//				setInstanceClassName(ePackage, "OclAny", Object.class, "This Ecore representation of the pivot OclAny exists solely to support serialization of Ecore metamodels.\nTRue functionality is only available once converted to a Pivot model.");
				//			setInstanceClassName(ePackage, "OclInvalid", InvalidValue.class, null);
				//			setInstanceClassName(ePackage, "OclVoid", NullValue.class, null);
				//				setInstanceClassName(ePackage, "OrderedSet", OrderedSet.class, null);
				setInstanceClassName(ePackage, TypeId.REAL_NAME, RealValue.class, null);
				//				setInstanceClassName(ePackage, "Sequence", List.class, null);
				//				setInstanceClassName(ePackage, "Set", Set.class, null);
				setInstanceClassName(ePackage, TypeId.STRING_NAME, String.class, null);
				//				setInstanceClassName(ePackage, "UniqueCollection", Set.class, null);
				setInstanceClassName(ePackage, TypeId.UNLIMITED_NATURAL_NAME, UnlimitedNaturalValue.class, null);
				EList<EClassifier> eClassifiers = ePackage.getEClassifiers();
				EClass eOclAny = (EClass) NameUtil.getENamedElement(eClassifiers, TypeId.OCL_ANY_NAME);
				EClass eOclElement = (EClass) NameUtil.getENamedElement(eClassifiers, TypeId.OCL_ELEMENT_NAME);
				EClass eOclType = (EClass) NameUtil.getENamedElement(eClassifiers, TypeId.OCL_TYPE_NAME);
				for (EClassifier eClassifier : new ArrayList<EClassifier>(eClassifiers)) {
					if (eClassifier instanceof EClass) {
						EClass eClass = (EClass) eClassifier;
						//						eClass.getEGenericSuperTypes().clear();
						eClass.getEOperations().clear();
						//						eClass.getEStructuralFeatures().clear();
					}
					eClassifier.getEAnnotations().clear();
					//				EAnnotation eAnnotation = eClassifier.getEAnnotation(PivotConstants.OMG_OCL_ANNOTATION_SOURCE);
					//				if (eAnnotation != null) {
					//					eClassifier.getEAnnotations().remove(eAnnotation);
					//				}
					//				eAnnotation = eClassifier.getEAnnotation(GenModelPackage.eNS_URI);
					//				if (eAnnotation != null) {
					//					eClassifier.getEAnnotations().remove(eAnnotation);
					//				}
					String name = eClassifier.getName();
					if ("OclAny".equals(name)) {
						String comment = "This Ecore representation of the pivot OclAny exists solely to support serialization of Ecore metamodels.\nTrue functionality is only available once converted to a Pivot model.";
						EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
						eAnnotation.setSource(GenModelPackage.eNS_URI);
						eAnnotation.getDetails().put("documentation", comment);
						eClassifier.getEAnnotations().add(eAnnotation);
					}
					//
					//	Non-library classes are removed from the Ecore model and so do not appear as generated Java classes.
					//
					if (excludedEClassifierNames.contains(name)) {
						eClassifiers.remove(eClassifier);
					}
					else {
						//
						//	Operations/properties referencing non-library classes are removed to avoid dangling references.
						//
						if (name.equals(TypeId.OCL_ENUMERATION_NAME)) {
							EClass eClass = (EClass)eClassifier;
							assert eClass.isAbstract();
							eClass.getEOperations().clear();
							eClass.getEStructuralFeatures().clear();
						}
						//
						//	FIXME Library classes removed for API compatibility.
						//
						//					if (name.equals("OclEnumeration")
						//							  || name.equals("OclSelf")) {
						//							eClassifiers.remove(eClassifier);
						//					}
						//
						//	Library classes have a non-null instance class name to suppress generation of a Java class
						//
						if (name.equals(TypeId.OCL_COMPARABLE_NAME)
								|| name.equals(TypeId.OCL_ELEMENT_NAME)
								|| name.equals(TypeId.OCL_ENUMERATION_NAME)
								|| name.equals(TypeId.OCL_INVALID_NAME)
								|| name.equals(TypeId.OCL_LAMBDA_NAME)
								|| name.equals(TypeId.OCL_MESSAGE_NAME)
								|| name.equals(TypeId.OCL_SELF_NAME)
								|| name.equals(TypeId.OCL_STATE_NAME)
								|| name.equals(TypeId.OCL_STEREOTYPE_NAME)
								|| name.equals(TypeId.OCL_SUMMABLE_NAME)
								|| name.equals(TypeId.OCL_TUPLE_NAME)
								|| name.equals(TypeId.OCL_TYPE_NAME)
								|| name.equals(TypeId.OCL_VOID_NAME)) {
							EClass eClass = (EClass)eClassifier;
							assert eClass.isAbstract();
						//	eClassifier.setInstanceClass(Object.class);
							eClass.setInterface(true);
							if (eClass.getESuperTypes().isEmpty()) {
						//		eClass.getESuperTypes().add(name.equals(TypeId.OCL_STEREOTYPE_NAME) ? eOclType : name.equals(TypeId.OCL_TYPE_NAME) ? eOclElement : eOclAny);
							}
						}
					}
					if ((eClassifier instanceof EDataType) && (((EDataType)eClassifier).getInstanceClassName() == null)) {
						((EDataType)eClassifier).setInstanceClass(Object.class);
					}
				}
				ePackage.getEAnnotations().clear();
				//			EAnnotation eAnnotation = ePackage.getEAnnotation(PivotConstants.OMG_OCL_ANNOTATION_SOURCE);
				//			if (eAnnotation != null) {
				//				ePackage.getEAnnotations().remove(eAnnotation);
				//			}
				//			eAnnotation = ePackage.getEAnnotation(GenModelPackage.eNS_URI);
				//			if (eAnnotation != null) {
				//				ePackage.getEAnnotations().remove(eAnnotation);
				//			}
			//	if (isASLibrary) {
					EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
					eAnnotation.setSource(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE);
					ePackage.getEAnnotations().add(eAnnotation);
			//	}
				log.info("Saving '" + ecoreURI + "'");
				eResource.save(null);
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		} finally {
			if (this.oclInstanceSetup == null) {
				ocl.dispose(true);
			}
			ocl = null;
		//	if (savedContribution != null) {
		//		StandardLibraryContribution.REGISTRY.put(OCLstdlib.STDLIB_URI, savedContribution);
		//	}
		}
	}

	protected @Nullable ASResource loadEcoreFile(@NonNull URI pivotFileURI) {
		XMLResource pivotEcoreResource = (XMLResource) environmentFactory.getResourceSet().getResource(pivotFileURI, true);
		Ecore2AS conversion = Ecore2AS.getAdapter(pivotEcoreResource, environmentFactory);
		ASResource pivotASResource = (ASResource) conversion.getASModel().eResource();
		return pivotASResource;
	}

	/**
	 * Load the stdlibFileURI resolving library references internally and without loading the Pivot AS.
	 * Returns null after reporting problems to issues.
	 */
	protected @Nullable ASResource loadOCLstdlibFile(@NonNull URI stdlibFileURI, @NonNull Issues issues) {
		String stdlibURIstring = "http://www.eclipse.org/ocl/2023/Library";
		/*StandardLibraryContribution savedContribution =*/ StandardLibraryContribution.REGISTRY.put(stdlibURIstring/*OCLstdlib.STDLIB_URI*/, new StandardLibraryContribution()
		{
			@Override
			public @NonNull StandardLibraryContribution getContribution() {
				return this;
			}

			@Override
			public @NonNull Resource getResource() {
				return metamodelManager.getASResourceSet().getResources().get(0);		// The only (partially loaded) resource.
			}
		});
		CompleteURIs completeURIs = environmentFactory.getCompleteModel().getCompleteURIs();
		completeURIs.addPackageURI2completeURI(stdlibURIstring, PivotConstants.METAMODEL_NAME);
		BaseCSResource xtextResource = (BaseCSResource)environmentFactory.getResourceSet().getResource(stdlibFileURI, true);
		String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(xtextResource.getErrors()), "OCLstdlib parse failure", "\n");
		if (message != null) {
			issues.addError(this, message, null, null, null);
			return null;
		}
		ASResource asResource = xtextResource.getASResource();
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		for (EObject eObject : asResource.getContents()) {
			Diagnostic diagnostic = PivotDiagnostician.INSTANCE.validate(eObject, validationContext);
			if (diagnostic.getSeverity() > Diagnostic.INFO) {
				message = PivotUtil.formatDiagnostics(diagnostic, "\n");
				issues.addError(this, message, null, null, null);
				return null;
			}
		}
		return asResource;
	}

	protected void mergeClass(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull CompleteClass completeClass) {
		String instanceClassName = asClass.getInstanceClassName();
		List<org.eclipse.ocl.pivot.@NonNull Class> superClasses = PivotUtilInternal.getSuperClassesList(asClass);
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			if (partialClass.isIsAbstract()) {
				asClass.setIsAbstract(true);
			}
			if (partialClass.isIsInterface()) {
				asClass.setIsInterface(true);
			}
			if (instanceClassName == null) {
				instanceClassName = partialClass.getInstanceClassName();
			}
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {
				if (!superClasses.contains(partialSuperClass)) {
					superClasses.add(partialSuperClass);
				}
			}
		}
		if (instanceClassName != null) {
			if (instanceClassName.equals(org.eclipse.ocl.pivot.AnyType.class.getName())) {	// Accomodate traditional model
				instanceClassName = "org.eclipse.ocl.as.ocl.AnyType";
			}
			else if (instanceClassName.equals(org.eclipse.ocl.pivot.InvalidType.class.getName())) {
				instanceClassName = "org.eclipse.ocl.as.ocl.InvalidType";
			}
			else if (instanceClassName.equals(org.eclipse.ocl.pivot.SelfType.class.getName())) {
				instanceClassName = "org.eclipse.ocl.as.ocl.SelfType";
			}
			else if (instanceClassName.equals(org.eclipse.ocl.pivot.VoidType.class.getName())) {
				instanceClassName = "org.eclipse.ocl.as.ocl.VoidType";
			}
			asClass.setInstanceClassName(instanceClassName);
		}
		ECollections.sort((EList<org.eclipse.ocl.pivot.@NonNull Class>)superClasses, superClassComparator);
	}

	protected void mergePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull CompletePackage completePackage) {
		for (@NonNull CompleteClass completeClass : PivotUtil.getOwnedCompleteClasses(completePackage)) {
			if ("OclAny".equals(completeClass.getName())) {
				getClass();		// XXX
			}
			org.eclipse.ocl.pivot.Class asClass = null;
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
				if (partialClass.getOwningPackage() == asPackage) {
					asClass = partialClass;
					break;
				}
			}
			if (asClass == null) {
				org.eclipse.ocl.pivot.Class primaryClass = completeClass.getPrimaryClass();
				asClass = (org.eclipse.ocl.pivot.Class) PivotFactory.eINSTANCE.create(primaryClass.eClass());
				asClass.setName(primaryClass.getName());
				asPackage.getOwnedClasses().add(asClass);
			}
			mergeClass(asClass, completeClass);
		}
	}

	protected @Nullable ASResource  mergeResources(@NonNull URI mergedURI, @NonNull ASResource asResource1, @NonNull ASResource asResource2) {
		ASResource asResource = asResource1;
		Model asModel = PivotUtil.getModel(asResource);
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
		for (CompletePackage completePackage : completeModel.getAllCompletePackages()) {
			if (PivotConstants.METAMODEL_NAME.equals(completePackage.getURI())) {
				org.eclipse.ocl.pivot.Package asPackage = null;
				for (org.eclipse.ocl.pivot.@NonNull Package partialPackage : PivotUtil.getPartialPackages(completePackage)) {
					if (partialPackage.eContainer() == asModel) {
						asPackage = partialPackage;
						break;
					}
				}
				assert asPackage != null;
				//	org.eclipse.ocl.pivot.Package primaryPackage = completePackage.getPrimaryPackage();
				//	PivotUtilInternal.resetContainer(primaryPackage);
				//	asPackage = primaryPackage;
				//	asModel.getOwnedPackages().add(asPackage);
				mergePackage(asPackage, completePackage);
			}
		}
		return asResource;
	}

	private void setInstanceClassName(@NonNull EPackage ePackage, String typeName, Class<?> javaClass, @Nullable String comment) {
		EClassifier eClassifier = ePackage.getEClassifier(typeName);
		if (eClassifier != null) {
			if (eClassifier instanceof EClass) {
				//				assert false;
				String name = eClassifier.getName();
				ePackage.getEClassifiers().remove(eClassifier);
				eClassifier = EcoreFactory.eINSTANCE.createEDataType();
				eClassifier.setName(name);
				ePackage.getEClassifiers().add(eClassifier);
			}
			if (!javaClass.getName().equals(eClassifier.getInstanceClassName())) {
				log.error("Wrong " + typeName + "::instanceClassName - " + eClassifier.getInstanceClassName() + " rather than " + javaClass.getName());
			}
		//	eClassifier.setInstanceClassName(javaClass.getName());
			if (comment != null) {
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(GenModelPackage.eNS_URI);
				eAnnotation.getDetails().put("body", comment);
				eClassifier.getEAnnotations().add(eAnnotation);
			}
		}
	}

	/**
	 * The Overall output Ecore file.
	 */
	public void setEcoreFile(String ecoreFile) {
		this.ecoreFile = ecoreFile;
	}

	/**
	 * Set true if this is to be an AS Library.
	 */
//	public void setIsASLibrary(boolean isASLibrary) {
//		this.isASLibrary = isASLibrary;
//	}

	/**
	 * The platform relative path to the Java generated source folder (e.g. "/org.eclipse.ocl.pivot/emf-src")
	 */
	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}

	/**
	 * The Name to be applied to the library
	 */
//	public void setLibraryName(String libraryName) {
//		this.libraryName = libraryName;
//	}

	/**
	 * The NsPrefix to be applied to the library
	 */
//	public void setLibraryNsPrefix(String libraryNsPrefix) {
//		this.libraryNsPrefix = libraryNsPrefix;
//	}

	/**
	 * The Name to be applied to the library
	 */
	public void setStdlibFile(String stdlibFile) {
		this.stdlibFile = stdlibFile;
	}

	/**
	 * Set true if this library uses and so requires the OCL stnadard library to be registered.
	 */
//	public void setUseOCLstdlib(boolean useOCLstdlib) {
//		this.useOCLstdlib = useOCLstdlib;
//	}
}
