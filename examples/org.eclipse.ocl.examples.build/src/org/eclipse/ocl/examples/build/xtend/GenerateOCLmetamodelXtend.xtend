/*******************************************************************************
 * Copyright (c) 2013 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import org.eclipse.ocl.pivot.Model
import org.eclipse.ocl.pivot.Package
import org.eclipse.ocl.pivot.utilities.ClassUtil

public class GenerateOCLmetamodelXtend extends GenerateOCLmetamodel
{
	protected override String declareClassTypes(/*@NonNull*/ Model root) {
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
					private final @NonNull «type.eClass().name» «type.getPrefixedSymbolName("_"+type.partialName())» = create«type.eClass().name»("«type.name»");
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

	protected override String generateMetamodel(Model root) {
		thisModel = root;
		var Package pkg = root.ownedPackages.findPackage();
		if (pkg == null) {
			return null;
		}
		var externalPackages = root.getSortedExternalPackages();
		'''
			/*******************************************************************************
			 * Copyright (c) 2010, 2015 Willink Transformations and others.
			 * All rights reserved. This program and the accompanying materials
			 * are made available under the terms of the Eclipse Public License v1.0
			 * which accompanies this distribution, and is available at
			 * http://www.eclipse.org/legal/epl-v10.html
			 *
			 * Contributors:
			 *     E.D.Willink - initial API and implementation
			 *******************************************************************************
			 * This code is 100% auto-generated
			 * from: «sourceFile»
			 * by: org.eclipse.ocl.examples.build.xtend.GenerateOCLmetamodel.xtend
			 * and: org.eclipse.ocl.examples.build.GeneratePivotMetamodel.mwe2
			 *
			 * Do not edit it.
			 *******************************************************************************/
			package	«javaPackageName»;
			
			import java.math.BigInteger;
			import java.util.List;
			
			import org.eclipse.emf.common.util.URI;
			import org.eclipse.emf.ecore.resource.Resource;
			import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import org.eclipse.ocl.pivot.*;
			import org.eclipse.ocl.pivot.Class;
			import org.eclipse.ocl.pivot.Package;
			import org.eclipse.ocl.pivot.ids.IdManager;
			import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
			import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
			import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
			import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
			import org.eclipse.ocl.pivot.library.LibraryFeature;
			import org.eclipse.ocl.pivot.model.OCLstdlib;
			import org.eclipse.ocl.pivot.utilities.ClassUtil;
			import org.eclipse.ocl.pivot.utilities.PivotConstants;
			import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
			import org.eclipse.ocl.pivot.utilities.PivotUtil;
			«IF ((externalPackages != null) && !externalPackages.isEmpty())»
			
			«FOR externalPackage : externalPackages»
				«externalPackage.declarePackageImport()»
			«ENDFOR»
			«ENDIF»
			
			/**
			 * This is the pivot representation of the «uri» metamodel
			 * auto-generated from «sourceFile».
			 * It facilitates efficient model loading without the overheads of model reading.
			 */
			@SuppressWarnings({"nls", "unused"})
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
			
				public static @NonNull Package create(@NonNull StandardLibraryInternal standardLibrary, @NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI) {
					«javaClassName» resource = new «javaClassName»(ClassUtil.nonNullEMF(URI.createURI(PIVOT_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION)));
					Contents contents = new Contents(standardLibrary.getPackage(), name, nsPrefix, nsURI);
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
						metamodel = INSTANCE = new «javaClassName»(ClassUtil.nonNullEMF(URI.createURI(PIVOT_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION)));
						Contents contents = new Contents(OCLstdlib.getDefaultPackage(), "«pkg.name»", "«pkg.nsPrefix»", PIVOT_URI);
						metamodel.getContents().add(contents.getModel());
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
						«pkge.getSymbolName()» = create«pkge.eClass().getName()»("«pkge.getName()»", "«pkge.getNsPrefix()»", "«pkge.getURI()»", «pkge.getGeneratedPackageId()»);
						((PivotObjectImpl)«pkge.getSymbolName()»).setESObject(«getEcoreLiteral(pkge)»);
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
					«root.declareClassTypes()»
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