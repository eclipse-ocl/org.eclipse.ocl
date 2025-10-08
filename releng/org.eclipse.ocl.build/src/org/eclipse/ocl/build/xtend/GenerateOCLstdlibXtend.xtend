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
package org.eclipse.ocl.build.xtend

import org.eclipse.ocl.pivot.DataType
import org.eclipse.ocl.pivot.Model
import org.eclipse.ocl.pivot.utilities.ClassUtil
import java.util.Collection
import java.util.GregorianCalendar
import org.eclipse.ocl.pivot.internal.manager.Orphanage

class GenerateOCLstdlibXtend extends GenerateOCLstdlib
{
	protected override String declareClassTypes(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var org.eclipse.ocl.pivot.Package pkg = root.ownedPackages.findPackage();
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

			«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
				«IF pkg == pkge && !excludedEClassifierNames.contains(type.name)»
					private final «getEClassReference(true, type)» «type.getPrefixedSymbolName("_"+type.partialName())» = create«type.eClass().name»(«getEcoreLiteral(type)»);
				«ELSE»
					private final «getEClassReference(true, type)» «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())» = create«type.eClass().name»("«type.name»");
				«ENDIF»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected override String declarePrimitiveTypes(/*@NonNull*/ Model root) {
		var pkge2primitiveTypes = root.getSortedPrimitiveTypes();
		if (pkge2primitiveTypes.isEmpty()) return "";
		var org.eclipse.ocl.pivot.Package pkg = root.ownedPackages.findPackage();
		var sortedPackages = root.getSortedPackages(pkge2primitiveTypes.keySet());
		'''
			«FOR pkge : sortedPackages»

				«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
					«IF pkg == pkge && !excludedEClassifierNames.contains(type.name)»
						private final @NonNull PrimitiveType «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())» = createPrimitiveType(«getEcoreLiteral(type)»);
					«ELSE»
						private final @NonNull PrimitiveType «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())» = createPrimitiveType("«type.name»");
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

	/*@NonNull*/ protected override String generateMetamodel(/*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		// initModel(root); in caller
		var lib = ClassUtil.requireNonNull(thisModel.getLibrary());
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
			 * by: org.eclipse.ocl.build.xtend.generateOCLstdlib.xtend
			 * and: org.eclipse.ocl.build.GenerateOCLstdlibModel.mwe2
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
			import org.eclipse.ocl.pivot.AssociativityKind;
			import org.eclipse.ocl.pivot.BagType;
			import org.eclipse.ocl.pivot.CollectionType;
			import org.eclipse.ocl.pivot.InvalidType;
			import org.eclipse.ocl.pivot.Iteration;
			import org.eclipse.ocl.pivot.LambdaType;
			import org.eclipse.ocl.pivot.Library;
			import org.eclipse.ocl.pivot.MapType;
			import org.eclipse.ocl.pivot.Model;
			import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
			import org.eclipse.ocl.pivot.Operation;
			import org.eclipse.ocl.pivot.OrderedSetType;
			import org.eclipse.ocl.pivot.Parameter;
			import org.eclipse.ocl.pivot.Precedence;
			import org.eclipse.ocl.pivot.PrimitiveType;
			import org.eclipse.ocl.pivot.Property;
			import org.eclipse.ocl.pivot.SelfType;
			import org.eclipse.ocl.pivot.SequenceType;
			import org.eclipse.ocl.pivot.SetType;
			import org.eclipse.ocl.pivot.TemplateParameter;
			import org.eclipse.ocl.pivot.TupleType;
			import org.eclipse.ocl.pivot.VoidType;
			import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
			import org.eclipse.ocl.pivot.internal.manager.Orphanage;
			import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
			import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
			import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
			import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
			import org.eclipse.ocl.pivot.model.OCLmetamodel;
			import org.eclipse.ocl.pivot.utilities.ClassUtil;
			import org.eclipse.ocl.pivot.utilities.PivotConstants;
			import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
			import org.eclipse.ocl.pivot.utilities.PivotUtil;
			«FOR importedClassName : thisModel.getSortedImportedJavaClassNames()»
				import «importedClassName»;
			«ENDFOR»

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
						oclstdlib.setSaveable(false);
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

				/**
				 * Return the default «uri» standard Library package.
				 *  This static definition auto-generated from «sourceFile»
				 *  is used as the default when no overriding copy is registered.
				 */
				public static org.eclipse.ocl.pivot.@NonNull Package getDefaultPackage() {
					org.eclipse.ocl.pivot.Package pkge = getDefaultModel().getOwnedPackages().get(0);
					assert pkge != null;
					return pkge;
				}
			
				/**
				 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
				 * and the {@link OCLASResourceFactory#REGISTRY}.
				 * This method may be invoked by standalone applications to replicate
				 * the registration that should appear as a standard_library plugin
				 * extension when running within Eclipse.
				 */
				public static void install() {
					PivotStandaloneSetup.init(OCLstdlibPackage.eINSTANCE);
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
						setASonly(true);
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
					 * Overridden to trivialize loading of the shared instance.
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
					 * Overridden to avoid computing proxies for the shared instance.
					 *
					 * @since 7.0
					 */
					@Override
					public void preUnload(@NonNull EnvironmentFactoryInternal environmentFactory) {}
			
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
				 *	Construct a copy of the OCL Standard Library with specified AS resource URI,
				 *  and external URI.
				 * @since 7.0
				 */
				public static @NonNull «javaClassName» create(@NonNull String asURI, @NonNull String externalURI) {
					Contents contents = new Contents(externalURI);
					return new «javaClassName»(asURI, contents.getModel());
				}
			
				/**
				 *	Construct an OCL Standard Library with specified resource URI and library content.
				 */
				private «javaClassName»(@NonNull String asURI, @NonNull Model libraryModel) {
					super(ClassUtil.requireNonNull(URI.createURI(asURI)), OCLASResourceFactory.getInstance());
					assert PivotUtil.isASURI(uri);
					getContents().add(libraryModel);
				}
			
				private static class AbstractLibraryContents extends AbstractContents
				{
					«var stdlib = getReferencedStandardLibrary()»
					«IF stdlib === null»
					«FOR pkge : thisModel.getSortedLocalPackages()»
					protected final «getEClassReference(true, pkge)» «pkge.getSymbolName()»;
					«ENDFOR»
					«ELSE»
					protected final org.eclipse.ocl.pivot.@NonNull Package «stdlib.getSymbolName()»;
					«ENDIF»
					«FOR normalizedTemplateParameter : thisModel.getNormalizedTemplateParameters()»
					protected final @NonNull NormalizedTemplateParameter «normalizedTemplateParameter.getPrefixedSymbolName(normalizedTemplateParameter.getName())»;
					«ENDFOR»
			
					protected AbstractLibraryContents() {
						«IF stdlib === null»
						«FOR pkge : thisModel.getSortedLocalPackages()»
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", «pkge.getNsPrefix() !== null ? "\""+pkge.getNsPrefix()+"\"" : "null"», "«pkge.getURI()»", «pkge.getGeneratedPackageId()», «getEcoreLiteral(pkge)»);
						«ENDFOR»
						«ELSE»
						«stdlib.getSymbolName()» = «stdlib.getExternalReference()»;
						«ENDIF»
						«FOR normalizedTemplateParameter : thisModel.getNormalizedTemplateParameters()»
						«normalizedTemplateParameter.getSymbolName()» = Orphanage.getNormalizedTemplateParameter(«thisModel.getOrphanPackage().getSymbolName()», «normalizedTemplateParameter.getIndex()»);
						«ENDFOR»
					}
				}
			
				private static class Contents extends AbstractLibraryContents
				{
					private final @NonNull Model «thisModel.getPrefixedSymbolName("model")»;
					«IF stdlib !== null»
					«FOR pkge : thisModel.getSortedAllPackages()»
					«IF pkge == stdlib»
					«ELSEIF (pkge.eContainer() != thisModel) && !Orphanage.isOrphan(pkge)»
					«ELSE»
					private final org.eclipse.ocl.pivot.@NonNull Package «pkge.getSymbolName()»;
					«ENDIF»
					«ENDFOR»
					«ENDIF»
			
					private Contents(@NonNull String asURI)
					{
						«thisModel.getSymbolName()» = createModel(asURI);
						«IF stdlib !== null»
						«FOR pkge : thisModel.getSortedAllPackages()»
						«IF pkge == stdlib»
						«ELSEIF (pkge.eContainer() != thisModel) && !Orphanage.isOrphan(pkge)»
						«ELSE»
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", «pkge.getNsPrefix() !== null ? "\""+stdlib.getNsPrefix()+"\"" : "null"», "«stdlib.getURI()»", «pkge.getGeneratedPackageId()», «getEcoreLiteral(pkge)»);
						«ENDIF»
						«ENDFOR»
						«ENDIF»
						«thisModel.installPackages()»
						«thisModel.installClassTypes()»
						«thisModel.installPrimitiveTypes()»
						«thisModel.installEnumerations()»
						«thisModel.installCollectionTypes()»
						«thisModel.installMapTypes()»
						«thisModel.installLambdaTypes()»
						«thisModel.installTupleTypes()»
						«thisModel.installOperations()»
						«thisModel.installIterations()»
						«thisModel.installCoercions()»
						«thisModel.installProperties()»
						«thisModel.installTemplateBindings()»
						«thisModel.installPrecedences()»
						«thisModel.installComments()»
					}
			
					public @NonNull Model getModel() {
						return «thisModel.getSymbolName()»;
					}
					«thisModel.defineExternals()»
					«thisModel.definePackages()»
					«thisModel.declareClassTypes(excludedEClassifierNames)»
					«thisModel.declarePrimitiveTypes()»
					«thisModel.declareEnumerations()»
					«thisModel.defineTemplateParameters()»
					«thisModel.declareTupleTypes()»
					«thisModel.declareCollectionTypes()»
					«thisModel.declareMapTypes()»
					«thisModel.defineClassTypes()»
					«thisModel.definePrimitiveTypes()»
					«thisModel.defineEnumerations()»
					«thisModel.defineCollectionTypes()»
					«thisModel.defineMapTypes()»
					«thisModel.defineTupleTypes()»
					«thisModel.defineLambdaTypes()»
					«thisModel.defineOperations()»
					«thisModel.defineIterations()»
					«thisModel.defineCoercions()»
					«thisModel.defineProperties()»
					«thisModel.defineTemplateBindings()»
					«thisModel.definePrecedences()»
					«thisModel.defineComments()»
				}
			}
		'''
	}
}
