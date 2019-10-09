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

import org.eclipse.ocl.pivot.DataType
import org.eclipse.ocl.pivot.Model
import org.eclipse.ocl.pivot.Package
import org.eclipse.ocl.pivot.utilities.ClassUtil
import java.util.Collection
import java.util.GregorianCalendar

class GenerateOCLstdlibXtend extends GenerateOCLstdlib
{
	protected override String declareClassTypes(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var Package pkg = root.ownedPackages.findPackage();
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

			«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
				«IF pkg == pkge && !excludedEClassifierNames.contains(type.name)»
					private final @NonNull «type.eClass().name» «type.getPrefixedSymbolName("_"+type.partialName())» = create«type.eClass().name»(«getEcoreLiteral(type)»);
				«ELSE»
					private final @NonNull «type.eClass().name» «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())» = create«type.eClass().name»("«type.name»");
				«ENDIF»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected def String defineConstantType(DataType type) {'''
		«IF "Boolean".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._Boolean;«ELSEIF "Classifier".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._Classifier;«ELSEIF "Integer".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._Integer;«ELSEIF "Real".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._Real;«ELSEIF "String".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._String;«ELSEIF "UnlimitedNatural".equals(type.name)»
			private void PrimitiveType «type.getPrefixedSymbolName("_"+type.partialName())» = OCLstdlib._UnlimitedNatural;«ELSE»
			private void DataType «type.getPrefixedSymbolName("_"+type.partialName())» = createDataType("«type.name»");«ENDIF»
	'''}

	/*@NonNull*/ protected override String generateMetamodel(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		thisModel = root;
		var lib = ClassUtil.nonNullState(root.getLibrary());
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
			 *     E.D.Willink - initial API and implementation
			 *******************************************************************************
			 * This code is 100% auto-generated
			 * from: «sourceFile»
			 * by: org.eclipse.ocl.examples.build.xtend.generateOCLstdlib.xtend
			 * and: org.eclipse.ocl.examples.build.GenerateOCLstdlibModel.mwe2
			 *
			 * Do not edit it.
			 *******************************************************************************/
			package	«javaPackageName»;
			
			import java.io.IOException;
			import java.util.ArrayList;
			import java.util.List;
			import java.util.Map;
			import java.util.Set;
			import java.util.WeakHashMap;
			
			import org.eclipse.emf.common.notify.Notification;
			import org.eclipse.emf.common.notify.NotificationChain;
			import org.eclipse.emf.common.util.TreeIterator;
			import org.eclipse.emf.common.util.URI;
			import org.eclipse.emf.ecore.EObject;
			import org.eclipse.emf.ecore.EReference;
			import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.ecore.resource.ResourceSet;
			import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import org.eclipse.ocl.pivot.*;
			import org.eclipse.ocl.pivot.Class;
			import org.eclipse.ocl.pivot.Package;
			import org.eclipse.ocl.pivot.ids.IdManager;
			import org.eclipse.ocl.pivot.ids.PackageId;
			import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
			import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
			import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
			import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
			import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
			import org.eclipse.ocl.pivot.model.OCLmetamodel;
			import org.eclipse.ocl.pivot.utilities.ClassUtil;
			import org.eclipse.ocl.pivot.utilities.MetamodelManager;
			import org.eclipse.ocl.pivot.utilities.PivotConstants;
			import org.eclipse.ocl.pivot.utilities.PivotUtil;
			«IF ((externalPackages !== null) && !externalPackages.isEmpty())»
			
			«FOR externalPackage : externalPackages»
				«externalPackage.declarePackageImport()»
			«ENDFOR»
			«ENDIF»
			
			/**
			 * This is the «uri» Standard Library
			 * auto-generated from «sourceFile».
			 * It facilitates efficient library loading without the overheads of model reading.
			 * <p>
			 * This Standard Library may be registered as the definition of a Standard Library for
			 * the OCL evaluation framework by invoking {@link #install}.
			 * <p>
			 * The Standard Library is normally activated when the MetamodelManager attempts
			 * to locate a library type when its default Standard Library URI is the same
			 * as this Standard Library.
			 */
			@SuppressWarnings("unused")
			public class «javaClassName» extends ASResourceImpl
			{
				/**
				 *	The static package-of-types pivot model of the Standard Library.
				 */
				private static «javaClassName» INSTANCE = null;
			
				/**
				 *	The URI of this Standard Library.
				 */
				public static final @NonNull String STDLIB_URI = "«uri»";
			
				/**
				 *	The URI of the AS representation of this Standard Library.
				 */
				public static final @NonNull URI STDLIB_AS_URI = URI.createURI("«uri»" + PivotConstants.DOT_OCL_AS_FILE_EXTENSION);
			
				/**
				 * Return the default «uri» standard Library Resource
				 * if it jas been created, or null if not.
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered.
				 * It cannot be unloaded or rather unloading has no effect.
				 */
				public static @Nullable «javaClassName» basicGetDefault() {
					return INSTANCE;
				}
			
				/**
				 * Return the default «uri» standard Library Resource.
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered.
				 * It cannot be unloaded or rather unloading has no effect.
				 */
				public static @NonNull «javaClassName» getDefault() {
					«javaClassName» oclstdlib = INSTANCE;
					if (oclstdlib == null) {
						Contents contents = new Contents("«lib.getURI»");
						String asURI = STDLIB_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
						oclstdlib = INSTANCE = new ReadOnly(asURI, contents.getModel());
					}
					return oclstdlib;
				}

				/**
				 * Return the default «uri» standard Library model.
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
				 * Return the default «uri» standard Library package.
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
				 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
				 * and the {@link OCLASResourceFactory#REGISTRY}.
				 * This method may be invoked by standalone applications to replicate
				 * the registration that should appear as a standard_library plugin
				 * extension when running within Eclipse.
				 */
				public static void install() {
					Loader contribution = new Loader();
					StandardLibraryContribution.REGISTRY.put(STDLIB_URI, contribution);
					OCLASResourceFactory.REGISTRY.put(STDLIB_AS_URI, contribution);
				}
			
				/**
				 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
				 * and the {@link OCLASResourceFactory#REGISTRY}
				 * unless some other library contribution has already been installed.
				 */
				public static void lazyInstall() {
					if (StandardLibraryContribution.REGISTRY.get(STDLIB_URI) == null) {
						install();
					}
				}
			
				/**
				 * Uninstall this library from the {@link StandardLibraryContribution#REGISTRY}
				 * and the {@link OCLASResourceFactory#REGISTRY}.
				 * This method may be invoked by standalone applications to release the library
				 * resources for garbage collection and memory leakage detection.
				 */
				public static void uninstall() {
					StandardLibraryContribution.REGISTRY.remove(STDLIB_URI);
					OCLASResourceFactory.REGISTRY.remove(STDLIB_AS_URI);
					INSTANCE = null;
				}
			
				/**
				 * The Loader shares the Standard Library instance whenever this default library
				 * is loaded from the registry of Standard Libraries populated by the standard_library
				 * extension point.
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
					protected ReadOnly(@NonNull String asURI, @NonNull Model libraryModel) {
						super(asURI, libraryModel);
						setSaveable(false);
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
						return OCLmetamodel.PIVOT_URI.equals(metamodelURI);
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
			
				/**
				 *	Construct a copy of the OCL Standard Library with specified resource URI,
				 *  and package name, prefix and namespace URI.
				 */
				public static @NonNull «javaClassName» create(@NonNull String asURI) {
					Contents contents = new Contents(asURI);
					return new «javaClassName»(asURI, contents.getModel());
				}
			
				/**
				 *	Construct an OCL Standard Library with specified resource URI and library content.
				 */
				private «javaClassName»(@NonNull String asURI, @NonNull Model libraryModel) {
					super(ClassUtil.nonNullState(URI.createURI(asURI)), OCLASResourceFactory.getInstance());
					assert PivotUtilInternal.isASURI(asURI);
					getContents().add(libraryModel);
				}
			
				private static class Contents extends AbstractContents
				{
					private final @NonNull Model «root.getPrefixedSymbolName("model")»;
					«FOR pkge : root.getSortedPackages()»
					private final @NonNull «pkge.eClass().getName()» «pkge.getPrefixedSymbolName(if (pkge == root.getOrphanPackage()) "orphanage" else pkge.getName())»;
					«ENDFOR»
			
					private Contents(@NonNull String asURI)
					{
						«root.getSymbolName()» = createModel(asURI);
						«FOR pkge : root.getSortedPackages()»
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", "«pkge.getNsPrefix()»", "«pkge.getURI()»", «pkge.getGeneratedPackageId()»);
						«ENDFOR»
						«root.installPackages()»
						«root.installClassTypes()»
						«root.installPrimitiveTypes()»
						«root.installEnumerations()»
						«root.installCollectionTypes()»
						«root.installMapTypes()»
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
					«root.declareTupleTypes()»
					«root.declareCollectionTypes()»
					«root.declareMapTypes()»
					«root.defineClassTypes()»
					«root.definePrimitiveTypes()»
					«root.defineEnumerations()»
					«root.defineCollectionTypes()»
					«root.defineMapTypes()»
					«root.defineTupleTypes()»
					«root.defineLambdaTypes()»
					«root.defineOperations()»
					«root.defineIterations()»
					«root.defineCoercions()»
					«root.declareProperties()»
					«root.defineProperties()»
					«root.defineTemplateBindings()»
					«root.definePrecedences()»
					«root.defineComments()»
				}
			}
		'''
	}
}
