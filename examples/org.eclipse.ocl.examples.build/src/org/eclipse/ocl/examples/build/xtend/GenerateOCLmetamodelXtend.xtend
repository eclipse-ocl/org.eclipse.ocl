/*******************************************************************************
 * Copyright (c) 2013, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import org.eclipse.ocl.pivot.Model
import java.util.Collection
import java.util.GregorianCalendar
import org.eclipse.ocl.pivot.utilities.ClassUtil
import org.eclipse.ocl.pivot.Library

class GenerateOCLmetamodelXtend extends GenerateOCLmetamodel
{
	protected override String defineExternals(/*@NonNull*/ Model root) {
		var externals = root.getSortedExternals();
		if (externals.isEmpty()) return "";
		'''

			«FOR name : externals»«var element = ClassUtil.nonNullState(name2external.get(name))»
			«IF element instanceof Library»
			private final @NonNull Package «getPrefixedSymbolName(element, name)» = «element.getExternalReference()»;
			«ENDIF»
			«ENDFOR»
		'''
	}

	protected override String generateMetamodel(/*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var Model root = thisModel;
		var org.eclipse.ocl.pivot.Package pkg = root.ownedPackages.findPackage();
		if (pkg === null) {
			return null;
		}
		var externalPackages = root.getSortedExternalPackages();
		var year = new GregorianCalendar().get(GregorianCalendar.YEAR);
		'''
			/*******************************************************************************
			 * Copyright (c) 2010, «year» Willink Transformations and others.
			 * All rights reserved. This program and the accompanying materials
			 * are made available under the terms of the Eclipse Public License v2.0
			 * which accompanies this distribution, and is available at
			 * http://www.eclipse.org/legal/epl-v20.html
			 *
			 * Contributors:
			 *   E.D.Willink - initial API and implementation
			 *******************************************************************************
			 * This code is 100% auto-generated
			 * from: «sourceFile»
			 * by: org.eclipse.ocl.examples.build.xtend.GenerateOCLmetamodel.xtend
			 * and: org.eclipse.ocl.examples.build.GeneratePivotMetamodel.mwe2
			 *
			 * Do not edit it.
			 *******************************************************************************/
			package	«javaPackageName»;

			import java.io.IOException;
			import java.util.List;
			import java.util.Map;

			import org.eclipse.emf.common.notify.Notification;
			import org.eclipse.emf.common.notify.NotificationChain;
			import org.eclipse.emf.common.util.URI;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.ecore.resource.ResourceSet;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import org.eclipse.ocl.pivot.AnyType;
			import org.eclipse.ocl.pivot.BooleanType;
			import org.eclipse.ocl.pivot.Class;
			import org.eclipse.ocl.pivot.CollectionType;
			import org.eclipse.ocl.pivot.DataType;
			import org.eclipse.ocl.pivot.Enumeration;
			import org.eclipse.ocl.pivot.EnumerationLiteral;
			import org.eclipse.ocl.pivot.Iteration;
			import org.eclipse.ocl.pivot.Model;
			import org.eclipse.ocl.pivot.Operation;
			import org.eclipse.ocl.pivot.Orphanage;
			import org.eclipse.ocl.pivot.Package;
			import org.eclipse.ocl.pivot.Parameter;
			import org.eclipse.ocl.pivot.PrimitiveType;
			import org.eclipse.ocl.pivot.Property;
			import org.eclipse.ocl.pivot.StandardLibrary;
			import org.eclipse.ocl.pivot.TemplateParameter;
			import org.eclipse.ocl.pivot.ids.IdManager;
			import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
			import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
			import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
			import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
			import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
			import org.eclipse.ocl.pivot.model.OCLstdlib;
			import org.eclipse.ocl.pivot.resource.ASResource;
			import org.eclipse.ocl.pivot.utilities.AnnotationUtil;
			import org.eclipse.ocl.pivot.utilities.PivotConstants;
			import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
			«IF ((externalPackages !== null) && !externalPackages.isEmpty())»

			«FOR externalPackage : externalPackages»
				«externalPackage.declarePackageImport()»
			«ENDFOR»
			«ENDIF»

			/**
			 * This is the pivot representation of the «uri» metamodel
			 * auto-generated from «sourceFile».
			 * It facilitates efficient model loading without the overheads of model reading.
			 */
			@SuppressWarnings("unused")
			public class «javaClassName» extends ASResourceImpl
			{
				/**
				 *	The URI of this Metamodel.
				 */
				public static final @NonNull String PIVOT_URI = "«uri»";
			
				/**
				 *	The URI of the AS representation of this Metamodel.
				 */
				public static final @NonNull URI PIVOT_AS_URI = URI.createURI("«uri»" + PivotConstants.DOT_OCL_AS_FILE_EXTENSION);

				public static @NonNull Package create(@NonNull StandardLibrary standardLibrary, @NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI) {
					«javaClassName» metamodelResource = new ReadOnly(PIVOT_AS_URI);
					Package standardLibraryPackage = standardLibrary.getOclAnyType().getOwningPackage();
					assert standardLibraryPackage != null;
					Contents contents = new Contents(metamodelResource, standardLibraryPackage, name, nsPrefix, nsURI);
					Model model = contents.getModel();
					@SuppressWarnings("null")@NonNull Package pkge = model.getOwnedPackages().get(0);
					return pkge;
				}
			
				/**
				 * Return the default «uri» metamodel Resource using the default OCL Standard Library. 
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered. 
				 */
				public static @NonNull «javaClassName» getDefault() {
					EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
					ReadOnly metamodelResource = environmentFactory.basicGetInstance(ReadOnly.class);
					if (metamodelResource == null) {
						metamodelResource = new ReadOnly(PIVOT_AS_URI);
						Contents contents = new Contents(metamodelResource, OCLstdlib.getDefaultPackage(), "«pkg.name»", "«pkg.nsPrefix»", PIVOT_URI);
						metamodelResource.setSaveable(false);
						environmentFactory.setInstance(ReadOnly.class, metamodelResource);
					}
					return metamodelResource;
				}

				/**
				 * Return the default «uri» metamodel Model using the default OCL Standard Library. 
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered. 
				 */
				public static @NonNull Model getDefaultModel() {
					Model model = (Model)(getDefault().getContents().get(0));
					assert model != null;
					return model;
				}
				«IF (externalPackages.size() == 2)»

				/**
				 * Return the default «uri» metamodel Package using the default OCL Standard Library. 
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered. 
				 */
				public static @NonNull Package getDefaultPackage() {
					Package pkge = getDefaultModel().getOwnedPackages().get(0);
					assert pkge != null;
					return pkge;
				}
				«ENDIF»
			
				/**
				 * Install this metamodel in the {@link OCLASResourceFactory#REGISTRY}.
				 * This method may be invoked by standalone applications to replicate
				 * the registration that should appear as a standard_library plugin
				 * extension when running within Eclipse.
				 */
				public static void install() {
					Loader contribution = new Loader();
					OCLASResourceFactory.REGISTRY.put(PIVOT_AS_URI, contribution);
				}
			
				/**
				 * Install this metamodel in the {@link OCLASResourceFactory#REGISTRY}
				 * unless some other metamodel contribution has already been installed.
				 */
				public static void lazyInstall() {
					if (OCLASResourceFactory.REGISTRY.get(PIVOT_AS_URI) == null) {
						install();
					}
				}
			
				/**
				 * Uninstall this metamodel from the {@link OCLASResourceFactory#REGISTRY}.
				 * This method may be invoked by standalone applications to release the library
				 * resources for garbage collection and memory leakage detection.
				 */
				public static void uninstall() {
					OCLASResourceFactory.REGISTRY.remove(PIVOT_AS_URI);
				}
			
				protected «javaClassName»(@NonNull URI uri) {
					super(uri, OCLASResourceFactory.getInstance());
				}
			
				protected static class LibraryContents extends AbstractContents
				{
					protected final @NonNull Package libraryPackage;
			
					protected LibraryContents(@NonNull Package libraryPackage) {
						this.libraryPackage = libraryPackage;
					}
				}
			
				/**
				 * The Loader shares the metamodel instance whenever this default metamodel
				 * is loaded from the registry of known pivot metamodels.
				 */
				public static class Loader implements StandardLibraryContribution
				{
					@Override
					public @NonNull StandardLibraryContribution getContribution() {
						return this;
					}
			
					@Override
					public @NonNull ASResource getResource() {
						return getDefault();
					}
				}
			
				/**
				 * A ReadOnly «javaClassName» overrides inherited functionality to impose immutable shared behaviour.
				 */
				protected static class ReadOnly extends «javaClassName» implements ImmutableResource
				{
					protected ReadOnly(@NonNull URI uri) {
						super(uri);
					}
			
					/**
					 * Overridden to inhibit entry of the shared instance in any ResourceSet.
					 */
					@Override
					public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
						return notifications;
					}
			
					/**
					 * Overridden to inhibit unloading of the shared instance.
					 */
					@Override
					protected void doUnload() {}

					@Override
					public boolean isCompatibleWith(@NonNull String metamodelURI) {
						return org.eclipse.ocl.pivot.model.OCLmetamodel.PIVOT_URI.equals(metamodelURI);
					}
			
					/**
					 * Overridden to inhibit unloading of the shared instance.
					 */
					@Override
					protected Notification setLoaded(boolean isLoaded) {
						if (isLoaded) {
							return super.setLoaded(isLoaded);
						}
						else {
							return null;
						}
					}
				}

				private static class Contents extends LibraryContents
				{
					private final @NonNull Model «root.getPrefixedSymbolName("root")»;
					«FOR pkge : root.getSortedPackages()»
					private final @NonNull «pkge.eClass().getName()» «pkge.getPrefixedSymbolName(if (pkge == root.getOrphanPackage()) "orphanage" else pkge.getName())»;
					«ENDFOR»

					protected Contents(@NonNull «javaClassName» metamodelResource, @NonNull Package libraryPackage, @NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI) {
						super(libraryPackage);
						«root.getSymbolName()» = createModel("«pkg.getURI»");
						metamodelResource.getContents().add(«root.getSymbolName()»);
						«FOR pkge : root.getSortedPackages()»
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", "«pkge.getNsPrefix()»", "«pkge.getURI()»", «pkge.getGeneratedPackageId()», «getEcoreLiteral(pkge)»);
						createAnnotation(«pkge.getSymbolName()», AnnotationUtil.PACKAGE_AS_METAMODEL_ANNOTATION_SOURCE);
						«FOR comment : pkge.ownedComments»
							installComment(«pkge.getSymbolName()», "«comment.javaString()»");
						«ENDFOR»
						«ENDFOR»
						«root.installAll()»
					}
					
					public @NonNull Model getModel() {
						return «root.getSymbolName()»;
					}
					«root.defineAll(excludedEClassifierNames)»
				}
			}
		'''
	}
}