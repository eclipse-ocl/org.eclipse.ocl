/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
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
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.resource.ASSaver;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

public abstract class GenerateOCLstdlib extends GenerateOCLCommonXtend
{
	protected Model thisModel;
	protected String ecoreFile;
	protected String libraryName;
	protected String libraryNsPrefix;
	protected boolean useOCLstdlib = false;

	protected abstract /*@NonNull*/ String generateMetamodel(/*@NonNull*/ Model pivotModel);

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
	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<CollectionType>> getSortedCollectionTypes(@NonNull Model root) {
		return super.getSortedCollectionTypes(root, monikerComparator);
	}

	@Override
	protected Model getThisModel() {
		return thisModel;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		if (!useOCLstdlib) {
			StandardLibraryContribution.REGISTRY.remove(OCLstdlib.STDLIB_URI);
		}
		String rootPath = StandaloneSetup.getPlatformRootPath();
		File folder = new File(rootPath + javaFolder + "/" + javaPackageName.replace(".", "/"));
		try {
			sourceFile = "/" + projectName + "/" + modelFile;
			URI fileURI = URI.createPlatformResourceURI(sourceFile, true);
			log.info("Loading OCL library '" + fileURI);
			ResourceSet resourceSet = getResourceSet();
			BaseCSResource xtextResource = (BaseCSResource)resourceSet.getResource(fileURI, true);
			String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(xtextResource.getErrors()), "OCLstdlib parse failure", "\n");
			if (message != null) {
				issues.addError(this, message, null, null, null);
				return;
			}
			ASResource asResource = xtextResource.getASResource();
			Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
			for (EObject eObject : asResource.getContents()) {
				Diagnostic diagnostic = PivotDiagnostician.INSTANCE.validate(eObject, validationContext);
				if (diagnostic.getSeverity() > Diagnostic.INFO) {
					message = PivotUtil.formatDiagnostics(diagnostic, "\n");
					issues.addError(this, message, null, null, null);
					return;
				}
			}
//			if (asResource == null) {
//				return;
//			}
			EObject pivotModel = ClassUtil.nonNullState(asResource.getContents().get(0));
			setEnvironmentFactory(ClassUtil.nonNullState(PivotUtilInternal.findEnvironmentFactory(pivotModel)));
			ASSaver saver = new ASSaver(asResource);
			saver.localizeSpecializations();
			String fileName = folder + "/" + javaClassName + ".java";
			log.info("Generating '" + fileName + "'");
			@SuppressWarnings("null")@NonNull String metamodel = generateMetamodel((Model)pivotModel);
			MergeWriter fw = new MergeWriter(fileName);
			fw.append(metamodel);
			fw.close();
			String saveFile = "/" + projectName + "/" + modelFile.replace("model", "model-gen").replace("oclstdlib", "oclas");
			URI saveURI = URI.createPlatformResourceURI(saveFile, true);
			log.info("Loading '" + saveURI + "'");
			log.info("Saving '" + saveURI + "'");
			asResource.setURI(saveURI);
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(ASResource.OPTION_NORMALIZE_CONTENTS, Boolean.TRUE);
			asResource.save(options);
			if (ecoreFile != null) {
				@NonNull URI ecoreURI = URI.createPlatformResourceURI(ecoreFile, true);
				AS2Ecore converter = new AS2Ecore(getEnvironmentFactory(), ecoreURI, null);
				XMLResource eResource = converter.convertResource(asResource, ecoreURI);
				EPackage ePackage = (EPackage) ClassUtil.nonNullState(eResource.getContents().get(0));
				if (libraryName != null) {
					ePackage.setName(libraryName);
				}
				if (libraryNsPrefix != null) {
					ePackage.setNsPrefix(libraryNsPrefix);
				}
//				setInstanceClassName(ePackage, "Bag", Bag.class, null);
				setInstanceClassName(ePackage, "Boolean", Boolean.class, null);
//				setInstanceClassName(ePackage, "Collection", Collection.class, null);
				setInstanceClassName(ePackage, "Integer", IntegerValue.class, null);
//				setInstanceClassName(ePackage, "OclAny", Object.class, "This Ecore representation of the pivot OclAny exists solely to support serialization of Ecore metamodels.\nTRue functionality is only available once converted to a Pivot model.");
	//			setInstanceClassName(ePackage, "OclInvalid", InvalidValue.class, null);
	//			setInstanceClassName(ePackage, "OclVoid", NullValue.class, null);
//				setInstanceClassName(ePackage, "OrderedSet", OrderedSet.class, null);
				setInstanceClassName(ePackage, "Real", RealValue.class, null);
//				setInstanceClassName(ePackage, "Sequence", List.class, null);
//				setInstanceClassName(ePackage, "Set", Set.class, null);
				setInstanceClassName(ePackage, "String", String.class, null);
//				setInstanceClassName(ePackage, "UniqueCollection", Set.class, null);
				setInstanceClassName(ePackage, "UnlimitedNatural", UnlimitedNaturalValue.class, null);
				EList<EClassifier> eClassifiers = ePackage.getEClassifiers();
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
/*					if (name.equals("OclComparable")
					 || name.equals("OclElement")
					 || name.equals("OclLambda")
					 || name.equals("OclMessage")
					 || name.equals("OclState")
					 || name.equals("OclSummable")
					 || name.equals("OclTuple")
					 || name.equals("OclType")) {
						((EClass)eClassifier).setAbstract(true);
					} */
					if (name.equals("OclStereotype")) {						// FIXME some old classes probably rely on historic model-gen/oclstdlib.ecore content
						((EClass)eClassifier).setAbstract(true);
						((EClass)eClassifier).setInterface(true);
						eClassifier.setInstanceClass(Object.class);
					}
					else if ((eClassifier.getInstanceClassName() == null)
							  && !name.equals("OclAny")
							  && !name.equals("OclInvalid")
							  && !name.equals("OclVoid")) {
								eClassifiers.remove(eClassifier);
							}
	//				eClassifier.setName(LibraryConstants.ECORE_STDLIB_PREFIX + name);
	//				eResource.setID(eClassifier, name);
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
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE);
				ePackage.getEAnnotations().add(eAnnotation);
				log.info("Saving '" + ecoreURI + "'");
				eResource.save(null);
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	private void setInstanceClassName(@NonNull EPackage ePackage, String typeName, Class<?> javaClass, @Nullable String comment) {
		EClassifier eClassifier = ePackage.getEClassifier(typeName);
		if (eClassifier != null) {
			if (eClassifier instanceof EClass) {
				String name = eClassifier.getName();
				ePackage.getEClassifiers().remove(eClassifier);
				eClassifier = EcoreFactory.eINSTANCE.createEDataType();
				eClassifier.setName(name);
				ePackage.getEClassifiers().add(eClassifier);
			}
			eClassifier.setInstanceClassName(javaClass.getName());
			if (comment != null) {
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(GenModelPackage.eNS_URI);
				eAnnotation.getDetails().put("body", comment);
				eClassifier.getEAnnotations().add(eAnnotation);
			}
		}
	}

	/**
	 * The Name to be applied to the library
	 */
	public void setEcoreFile(String ecoreFile) {
		this.ecoreFile = ecoreFile;
	}

	/**
	 * The platform relative path to the Java generated source folder (e.g. "/org.eclipse.ocl.pivot/emf-src")
	 */
	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}

	/**
	 * The Name to be applied to the library
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	/**
	 * The NsPrefix to be applied to the library
	 */
	public void setLibraryNsPrefix(String libraryNsPrefix) {
		this.libraryNsPrefix = libraryNsPrefix;
	}
	
	/**
	 * Set true if this library uses and so requires the OCL stnadard library to be registered.
	 */
	public void setUseOCLstdlib(boolean useOCLstdlib) {
		this.useOCLstdlib = useOCLstdlib;
	}
}
