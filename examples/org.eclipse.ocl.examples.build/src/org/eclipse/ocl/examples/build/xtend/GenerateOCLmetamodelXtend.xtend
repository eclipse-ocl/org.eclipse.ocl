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
import org.eclipse.ocl.pivot.Package
import org.eclipse.ocl.pivot.utilities.ClassUtil
import java.util.Collection
import java.util.GregorianCalendar

class GenerateOCLmetamodelXtend extends GenerateOCLmetamodel
{
	protected override String declareClassTypes(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var Package pkg = root.ownedPackages.findPackage();
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

			«IF pkg == pkge»
				«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
					private final @NonNull «type.eClass().name» «type.getPrefixedSymbolName("_"+type.partialName())» = create«type.eClass().name»(«getEcoreLiteral(type)»);
				«ENDFOR»
			«ELSE»
				«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
					private final @NonNull «type.eClass().name» «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())» = create«type.eClass().name»("«type.name»");
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
		'''
	}

	protected override String declareEnumerations(/*@NonNull*/ Model root) {
		var pkge2enumerations = root.getSortedEnumerations();
		if (pkge2enumerations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2enumerations.keySet());
		'''

		«FOR pkge : sortedPackages»
			«FOR enumeration : ClassUtil.nullFree(pkge2enumerations.get(pkge))»
				«var enumerationName = enumeration.getPrefixedSymbolName("_" + enumeration.partialName())»
				private final @NonNull Enumeration «enumerationName» = createEnumeration(«getEcoreLiteral(enumeration)»);
				«FOR enumerationLiteral : enumeration.ownedLiterals»
					private final @NonNull EnumerationLiteral «enumerationLiteral.getPrefixedSymbolName("el_"+enumerationName+"_"+enumerationLiteral.name)» = createEnumerationLiteral(«getEcoreLiteral(enumerationLiteral)»);
				«ENDFOR»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected override String generateMetamodel(/*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var Model root = thisModel;
		var Package pkg = root.ownedPackages.findPackage();
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
			import org.eclipse.ocl.pivot.BagType;
			import org.eclipse.ocl.pivot.BooleanType;
			import org.eclipse.ocl.pivot.Class;
			import org.eclipse.ocl.pivot.CollectionType;
			import org.eclipse.ocl.pivot.DataType;
			import org.eclipse.ocl.pivot.Enumeration;
			import org.eclipse.ocl.pivot.EnumerationLiteral;
			import org.eclipse.ocl.pivot.Model;
			import org.eclipse.ocl.pivot.Operation;
			import org.eclipse.ocl.pivot.OrderedSetType;
			import org.eclipse.ocl.pivot.Package;
			import org.eclipse.ocl.pivot.Parameter;
			import org.eclipse.ocl.pivot.PivotPackage;
			import org.eclipse.ocl.pivot.Property;
			import org.eclipse.ocl.pivot.SequenceType;
			import org.eclipse.ocl.pivot.SetType;
			import org.eclipse.ocl.pivot.TemplateParameter;
			import org.eclipse.ocl.pivot.ids.IdManager;
			import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
			import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
			import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
			import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
			import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
			import org.eclipse.ocl.pivot.model.OCLstdlib;
			import org.eclipse.ocl.pivot.utilities.PivotConstants;
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
				 *	The static package-of-types pivot model of the Pivot Metamodel.
				 */
				private static «javaClassName» INSTANCE = null;

				/**
				 *	The URI of this Metamodel.
				 */
				public static final @NonNull String PIVOT_URI = "«uri»";
			
				/**
				 *	The URI of the AS representation of this Metamodel.
				 */
				public static final @NonNull URI PIVOT_AS_URI = URI.createURI("«uri»" + PivotConstants.DOT_OCL_AS_FILE_EXTENSION);

				public static @NonNull Package create(@NonNull StandardLibraryInternal standardLibrary, @NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI) {
					«javaClassName» resource = new ReadOnly(PIVOT_AS_URI);
					Package standardLibraryPackage = standardLibrary.getOclAnyType().getOwningPackage();
					assert standardLibraryPackage != null;
					Contents contents = new Contents(standardLibraryPackage, name, nsPrefix, nsURI);
					Model model = contents.getModel();
					resource.getContents().add(model);
					@SuppressWarnings("null")@NonNull Package pkge = model.getOwnedPackages().get(0);
					return pkge;
				}
			
				/**
				 * Return the default «uri» metamodel Resource using the default OCL Standard Library. 
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered. 
				 */
				public static @NonNull «javaClassName» getDefault() {
					«javaClassName» metamodel = INSTANCE;
					if (metamodel == null) {
						metamodel = INSTANCE = new ReadOnly(PIVOT_AS_URI);
						Contents contents = new Contents(OCLstdlib.getDefaultPackage(), "«pkg.name»", "«pkg.nsPrefix»", PIVOT_URI);
						metamodel.getContents().add(contents.getModel());
						metamodel.setSaveable(false);
					}
					return metamodel;
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
					INSTANCE = null;
				}
			
				protected «javaClassName»(@NonNull URI uri) {
					super(uri, OCLASResourceFactory.getInstance());
				}
			
				protected static class LibraryContents extends AbstractContents
				{
					protected final @NonNull Package standardLibrary;
			
					protected LibraryContents(@NonNull Package standardLibrary) {
						this.standardLibrary = standardLibrary;
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
					public @NonNull Resource getResource() {
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
						return PIVOT_URI.equals(metamodelURI);
					}
			
					/**
					 * Overridden to trivialise loading of the shared instance.
					 */
					@Override
					public void load(Map<?, ?> options) throws IOException {
						if (this != INSTANCE) {
							super.load(options);
						}
						else {
							setLoaded(true);
						}
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

					protected Contents(@NonNull Package standardLibrary, @NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI) {
						super(standardLibrary);
						«root.getSymbolName()» = createModel("«pkg.getURI»");
						«FOR pkge : root.getSortedPackages()»
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", "«pkge.getNsPrefix()»", "«pkge.getURI()»", «pkge.getGeneratedPackageId()», «getEcoreLiteral(pkge)»);
						«ENDFOR»
						«root.installPackages()»
						«root.installClassTypes()»
						«root.installPrimitiveTypes()»
						«root.installEnumerations()»
						«root.installCollectionTypes()»
						«root.installLambdaTypes()»
						«root.installTupleTypes()»
						«root.installOperations()»
						«root.installIterations()»
						«root.installCoercions()»
						«root.installProperties()»
						«root.installTemplateBindings()»
						«root.installPrecedences()»
						«root.installComments()»
					}
					
					public @NonNull Model getModel() {
						return «root.getSymbolName()»;
					}
					«root.defineExternals()»
					«root.definePackages()»
					«root.declareClassTypes(excludedEClassifierNames)»
					«root.declarePrimitiveTypes()»
					«root.declareEnumerations()»
					«root.defineTemplateParameters()»
					«root.declareCollectionTypes()»
					«root.declareTupleTypes()»
					«root.defineClassTypes()»
					«root.definePrimitiveTypes()»
					«root.defineEnumerations()»
					«root.defineCollectionTypes()»
					«root.defineTupleTypes()»
					«root.defineLambdaTypes()»
					«root.defineOperations()»
					«root.defineIterations()»
					«root.defineCoercions()»
					«root.defineProperties()»
					«root.defineTemplateBindings()»
					«root.definePrecedences()»
					«root.defineComments()»
				}
			}
		'''
	}
}