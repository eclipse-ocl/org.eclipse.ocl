/*******************************************************************************
 * Copyright (c) 2013, 2015 Willink Transformations and others.
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.resource.ASSaver;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public abstract class GenerateOCLmetamodel extends GenerateOCLCommonXtend
{	
	protected final @NonNull Comparator<CollectionType> collectionTypeComparator = new Comparator<CollectionType>()
	{
		public int compare(CollectionType o1, CollectionType o2) {
			if ((o1 == null) || (o2 == null)) {
				return 0;
			}
			TypeId m1 = PivotUtil.getUnspecializedTemplateableElement(o1).getTypeId(); 
			TypeId m2 = PivotUtil.getUnspecializedTemplateableElement(o2).getTypeId();
			int i = m1.toString().compareTo(m2.toString());
			if (i != 0) {
				return i;
			}
			String n1 = o1.getElementType().getName(); 
			String n2 = o2.getElementType().getName();
			i = n1.compareTo(n2);
			if (i != 0) {
				return i;
			}
			IntegerValue l1 = o1.getLowerValue(); 
			IntegerValue l2 = o2.getLowerValue();
			i = l1.compareTo(l2);
			if (i != 0) {
				return i;
			}
			UnlimitedNaturalValue u1 = o1.getUpperValue(); 
			UnlimitedNaturalValue u2 = o2.getUpperValue();
			return u1.compareTo(u2);
		}
	};

	protected Model thisModel;

	protected CollectionType findCollectionType(Iterable<org.eclipse.ocl.pivot.Class> types, String name) {
		CollectionType collType = null;
		for (org.eclipse.ocl.pivot.Class type : types) {
			if (type instanceof CollectionType) {
				TemplateableElement unspecializedElement = type.getUnspecializedElement();
				if (unspecializedElement instanceof CollectionType) {
					collType = (CollectionType) unspecializedElement;
					if (collType.getName().equals(name)) {
						return collType;
					}
				}
			}
		}
		if (collType != null) {
			EObject eContainer = collType.eContainer();
			if (eContainer instanceof Library) {
				for (org.eclipse.ocl.pivot.Class type : ((Library)eContainer).getOwnedClasses()) {
					if ((type instanceof CollectionType) && (type.getName().equals(name))) {
						return (CollectionType)type;
					}
				}
			}
		}
		return null;
	}

	protected org.eclipse.ocl.pivot.Package findPackage(Iterable<org.eclipse.ocl.pivot.Package> packages) {
		for (org.eclipse.ocl.pivot.Package pkg : packages) {
			if (!"$$".equals(pkg.getName())) {
				return pkg;
			}
		}
		return null;
	}
	
	protected DataType findPrimitiveType(Iterable<org.eclipse.ocl.pivot.Class> types, String name) {
		for (Type type : types) {
			if ((type instanceof DataType) && (type.getName().equals(name))) {
				return (DataType)type;
			}
		}
		return null;
	}

	@Override
	protected String getExternalReference(@NonNull Element element) {
		if ((element instanceof Library) && (element.eResource() instanceof OCLstdlib)) {
			return "standardLibrary";
		}
		return super.getExternalReference(element);
	}

	protected abstract String generateMetamodel(@NonNull Model pivotModel);
	
	protected String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Class elem) {
		return nameQueries.getEcoreLiteral(elem);
	}

	protected String getEcoreLiteral(@NonNull EnumerationLiteral elem) {
		return nameQueries.getEcoreLiteral(elem);
	}

	@Override
	protected @NonNull Set<PrimitiveType> getAllPrimitiveTypes(@NonNull Model root) {
		@SuppressWarnings("null")@NonNull Set<PrimitiveType> emptySet = Collections.emptySet();
		return emptySet;
	}
	
	@Override
	protected @NonNull String getNameLiteral(@NonNull Property property) {
		return nameQueries.getEcoreLiteral(property);
	}

	@Override
	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<CollectionType>> getSortedCollectionTypes(@NonNull Model root) {
		return super.getSortedCollectionTypes(root, collectionTypeComparator);
	}

	@Override
	protected Model getThisModel() {
		return thisModel;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		ResourceSet resourceSet = ClassUtil.nonNullState(getResourceSet());
		StandaloneProjectMap projectMap = StandaloneProjectMap.getAdapter(resourceSet);
		assert projectName != null;
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = projectMap.getProjectDescriptor(projectName);
		if (projectDescriptor == null) {
			issues.addError(this, "Unknown project '" + projectName + "'", null, null, null);
			return;
		}
		@SuppressWarnings("null")@NonNull URI nsURI = URI.createURI(PivotPackage.eNS_URI);
		StandaloneProjectMap.IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(nsURI);
//	    if (packageDescriptor != null) {
//	    	packageDescriptor.configure(asResourceSet, LoadDynamicResourceStrategy.INSTANCE, null);
//	    }
		assert modelFile != null;
		URI inputURI = projectDescriptor.getPlatformResourceURI(modelFile);
		File outputFolder = projectDescriptor.getLocationFile(javaFolder + '/' + javaPackageName.replace('.', '/'));
		OCLstdlib.install();
		log.info("Loading Pivot Model '" + inputURI);
		try {
			OCLInternal ocl = OCLInternal.newInstance();
			setEnvironmentFactory(ocl.getEnvironmentFactory());
			ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		    if (packageDescriptor != null) {
		    	packageDescriptor.configure(asResourceSet, StandaloneProjectMap.LoadDynamicResourceStrategy.INSTANCE, null);
		    }
			Resource ecoreResource = ClassUtil.nonNullState(asResourceSet.getResource(inputURI, true));
			getEnvironmentFactory().adapt(ecoreResource);
			String ecoreErrorsString = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(ecoreResource.getErrors()), "Loading " + inputURI, "\n");
			if (ecoreErrorsString != null) {
				issues.addError(this, ecoreErrorsString, null, null, null);
				return;
			}
			Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, getEnvironmentFactory());
			Model pivotModel = ecore2as.getASModel();
			Resource asResource = pivotModel.eResource();
			String pivotErrorsString = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(asResource.getErrors()), "Converting " + inputURI, "\n");
				if (pivotErrorsString != null) {
					issues.addError(this, pivotErrorsString, null, null, null);
					return;
				}
			sourceFile = "/" + projectName + "/" + modelFile;
			EObject asRoot = asResource.getContents().get(0);
			ASSaver saver = new ASSaver(asResource);
			/*Package orphanage =*/ saver.localizeSpecializations();
//			if ((orphanage != null) && (pivotModel instanceof Root)) {
//				(pivotModel as Root).getOwnedPackages().add(orphanage);
//			}
			String fileName = outputFolder + "/" + javaClassName + ".java";
			log.info("Generating '" + fileName + "'");
			assert asRoot instanceof Model;
			Model asRoot2 = (Model)asRoot;
			StandardLibraryInternal standardLibrary = ocl.getStandardLibrary();
			getExternals(asRoot2);
			addExternalReference(standardLibrary.getBooleanType(), asRoot2);
			addExternalReference(standardLibrary.getIntegerType(), asRoot2);
			addExternalReference(standardLibrary.getOclAnyType(), asRoot2);
			addExternalReference(standardLibrary.getOclElementType(), asRoot2);
			addExternalReference(standardLibrary.getRealType(), asRoot2);
			addExternalReference(standardLibrary.getStringType(), asRoot2);
			addExternalReference(standardLibrary.getUnlimitedNaturalType(), asRoot2);
			String metamodel = generateMetamodel(asRoot2);
			MergeWriter fw = new MergeWriter(fileName);
			if (metamodel != null) {
				fw.append(metamodel);
			}
			fw.close();
			String saveFile = "/" + projectName + "/" + modelFile.replace("model", "model-gen").replace("ecore", "oclas");
			URI saveURI = URI.createPlatformResourceURI(saveFile, true);
//			log.info("Loading '" + saveURI + "'");
//			AS2XMIid as2id = AS2XMIid.load(saveURI);
			log.info("Saving '" + saveURI + "'");
			for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				if (eObject instanceof org.eclipse.ocl.pivot.Class) {
					List<Property> ownedAttribute = ((org.eclipse.ocl.pivot.Class)eObject).getOwnedProperties();
					ClassUtil.sort(ownedAttribute, OCLinEcoreTablesUtils.propertyComparator);
				}
			}
			asResource.setURI(saveURI);
//	    	as2id.assignIds(asResource.getResourceSet());
			Map<Object, Object> options = XMIUtil.createSaveOptions();
			options.put(ASResource.OPTION_NORMALIZE_CONTENTS, Boolean.TRUE);
			asResource.save(options);
			ocl.dispose();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	/**
	 * The projectName relative path to the Java generated source folder (e.g. "emf-src")
	 */
	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}
}
