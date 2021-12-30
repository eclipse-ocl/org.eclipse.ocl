/*******************************************************************************
 * Copyright (c) 2012, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.internal.options.CodeGenerationMode;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.codegen.common.PivotQueries;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.model.CGLibrary;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.resource.BasicProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.uml2.codegen.ecore.genmodel.util.UML2GenModelUtil;

public class OCLinEcoreGenModelGeneratorAdapter extends GenBaseGeneratorAdapter
{
	public static final @NonNull String OCL_GENMODEL_URI = "http://www.eclipse.org/OCL/GenModel";
	public static final @NonNull String TABLES_POSTAMBLE_KEY = "Tables Postamble";
	public static final @NonNull String USE_DELEGATES_KEY = "Use Delegates";
	public static final @NonNull String USE_NULL_ANNOTATIONS_KEY = "Use Null Annotations";
	public static final @NonNull String INVARIANT_PREFIX_KEY = "Invariant Prefix";

	/**
	 * If the genModel has a {@link #OCL_GENMODEL_URI} GenAnnotation with a
	 * {@link #INVARIANT_PREFIX_KEY} detail returns its value otherwise return null.
	 */
	public static @Nullable String getInvariantPrefix(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			EMap<String, String> details = genAnnotation.getDetails();
			if (details.containsKey(INVARIANT_PREFIX_KEY)) {
				return String.valueOf(details.get(INVARIANT_PREFIX_KEY));
			}
		}
		return null;
	}

	/**
	 * Return some non-null text to append before the final brace of the generated Tables file.
	 * This may be used to insert manual text that remedies reported API changes.
	 */
	public static @Nullable String tablesPostamble(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			EMap<String, String> details = genAnnotation.getDetails();
			if (details.containsKey(TABLES_POSTAMBLE_KEY)) {
				return String.valueOf(details.get(TABLES_POSTAMBLE_KEY));
			}
		}
		return null;
	}

	/**
	 * Return true if the genModel has a {@link #OCL_GENMODEL_URI} GenAnnotation with a
	 * {@link #USE_DELEGATES_KEY} detail set to true, or if there is no such GenAnnotation and the
	 * global preference {@link CommonOptions#CODE_GENERATION_MODE}
	 * has been set to {@link CodeGenerationMode#DELEGATED}
	 */
	public static boolean useDelegates(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			EMap<String, String> details = genAnnotation.getDetails();
			if (details.containsKey(USE_DELEGATES_KEY)) {
				return Boolean.valueOf(details.get(USE_DELEGATES_KEY));
			}
		}
		CodeGenerationMode preference = CommonOptions.CODE_GENERATION_MODE.getPreferredValue();
		if (preference == CodeGenerationMode.DELEGATED) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if the genModel has a {@link #OCL_GENMODEL_URI} GenAnnotation with a
	 * {@link #USE_NULL_ANNOTATIONS_KEY} detail set to true.
	 */
	public static boolean useNullAnnotations(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			EMap<String, String> details = genAnnotation.getDetails();
			if (details.containsKey(USE_NULL_ANNOTATIONS_KEY)) {
				return Boolean.valueOf(details.get(USE_NULL_ANNOTATIONS_KEY));
			}
		}
		//		CodeGenerationMode preference = OCLCommon.getPreference(CommonOptions.CODE_GENERATION_MODE, null);
		//		if (preference == CodeGenerationMode.DELEGATED) {
		//			return true;
		//		}
		return false;
	}

	protected static interface Edit
	{
		void undo();
	}

	/**
	 * OCLinEcoreStateAdapter caches properties of the input model during doPreGenerate and accumulates all
	 * in-memory modifications so that they are reverted during doPostGenerate.
	 */
	protected class OCLinEcoreStateAdapter implements Adapter
	{
		protected class AddEAnnotation implements Edit
		{
			private final @NonNull List<EAnnotation> eAnnotations;
			private final @NonNull EAnnotation eAnnotation;

			@SuppressWarnings("null")
			public AddEAnnotation(/*@NonNull*/ List<EAnnotation> eAnnotations, @NonNull EAnnotation eAnnotation) {
				this.eAnnotations = eAnnotations;
				this.eAnnotation = eAnnotation;
				eAnnotations.add(eAnnotation);
			}

			@Override
			public void undo() {
				eAnnotations.remove(eAnnotation);
			}
		}

		protected class AddEOperation implements Edit
		{
			private final @NonNull List<EOperation> eOperations;
			private final @NonNull EOperation eOperation;

			@SuppressWarnings("null")
			public AddEOperation(/*@NonNull*/ List<EOperation> eOperations, @NonNull EOperation eOperation) {
				this.eOperations = eOperations;
				this.eOperation = eOperation;
				eOperations.add(eOperation);
			}

			@Override
			public void undo() {
				eOperations.remove(eOperation);
			}
		}

		protected class AddModelPluginVariable implements Edit
		{
			private final @NonNull String modelPluginVariable;

			public AddModelPluginVariable(@NonNull String modelPluginVariable) {
				this.modelPluginVariable = modelPluginVariable;
				genModel.getModelPluginVariables().add(modelPluginVariable);
			}

			@Override
			public void undo() {
				genModel.getModelPluginVariables().remove(modelPluginVariable);
			}
		}

		protected class RemoveEAnnotation implements Edit
		{
			private final @NonNull EAnnotation eAnnotation;
			private final @NonNull List<EAnnotation> eAnnotations;
			private final int index;

			@SuppressWarnings("null")
			public RemoveEAnnotation(@NonNull EAnnotation eAnnotation) {
				this.eAnnotation = eAnnotation;
				this.eAnnotations = ((EModelElement)eAnnotation.eContainer()).getEAnnotations();
				this.index = eAnnotations.indexOf(eAnnotation);
				eAnnotations.remove(eAnnotation);
			}

			@Override
			public void undo() {
				eAnnotations.add(index, eAnnotation);
			}
		}

		protected class SetEAnnotationDetail implements Edit
		{
			private final @NonNull EAnnotation eAnnotation;
			private final @NonNull String detailName;
			private final int index;
			private final @Nullable String value;

			public SetEAnnotationDetail(@NonNull EAnnotation eAnnotation, @NonNull String detailName) {		// RemoveEAnnotationDetail
				this.eAnnotation = eAnnotation;
				this.detailName = detailName;
				EMap<String, String> details = eAnnotation.getDetails();
				this.index = details.indexOfKey(detailName);
				this.value = details.get(detailName);
				if (index >= 0) {
					details.remove(index);
				}
			}

			public SetEAnnotationDetail(@NonNull EAnnotation eAnnotation, @NonNull String detailName, @Nullable String value) {
				this.eAnnotation = eAnnotation;
				this.detailName = detailName;
				EMap<String, String> details = eAnnotation.getDetails();
				this.index = details.indexOfKey(detailName);
				this.value = details.put(detailName, value);
			}

			@Override
			public void undo() {
				EMap<String, String> details = eAnnotation.getDetails();
				if (index < 0) {
					details.removeKey(detailName);
				}
				else {
					details.put(detailName, value);
					int newIndex = details.indexOfKey(detailName);
					if (newIndex != index) {
						details.move(index, newIndex);
					}
				}
			}
		}

		protected class SetInvocationDelegates implements Edit
		{
			private final @NonNull EPackage ePackage;
			private final @NonNull List<String> invocationDelegates;

			@SuppressWarnings("null")
			public SetInvocationDelegates(@NonNull EPackage ePackage) {
				this.ePackage = ePackage;
				this.invocationDelegates = EcoreUtil.getInvocationDelegates(ePackage);
				EcoreUtil.setInvocationDelegates(ePackage, pruneDelegates(invocationDelegates));
			}

			@Override
			public void undo() {
				EcoreUtil.setInvocationDelegates(ePackage, invocationDelegates);
			}
		}

		protected class SetSettingDelegates implements Edit
		{
			private final @NonNull EPackage ePackage;
			private final @NonNull List<String> settingDelegates;

			@SuppressWarnings("null")
			public SetSettingDelegates(@NonNull EPackage ePackage) {
				this.ePackage = ePackage;
				this.settingDelegates = EcoreUtil.getSettingDelegates(ePackage);
				EcoreUtil.setSettingDelegates(ePackage, pruneDelegates(settingDelegates));
			}

			@Override
			public void undo() {
				EcoreUtil.setSettingDelegates(ePackage, settingDelegates);
			}
		}

		protected class SetValidationDelegates implements Edit
		{
			private final @NonNull EPackage ePackage;
			private final @NonNull List<String> validationDelegates;

			@SuppressWarnings("null")
			public SetValidationDelegates(@NonNull EPackage ePackage) {
				this.ePackage = ePackage;
				this.validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
				EcoreUtil.setValidationDelegates(ePackage, pruneDelegates(validationDelegates));
			}

			@Override
			public void undo() {
				EcoreUtil.setValidationDelegates(ePackage, validationDelegates);
			}
		}

		protected final @NonNull OCLInternal ocl;
		protected final @NonNull GenModel genModel;

		/**
		 * The Java source text defining the constants used by operation and property bodies that must be emitted
		 * as part of the Tables class.
		 */
		private @NonNull Map<@NonNull GenPackage, @NonNull String> constantTexts = new HashMap<>();

		/**
		 * The edits applied to the in-memory GenModel that must be undone during postGenerate.
		 */
		private @NonNull List<Edit> edits = new ArrayList<>();

		private OCLinEcoreStateAdapter(@NonNull GenModel genModel) {
			Resource eResource = genModel.eResource();
			ResourceSet resourceSet = eResource != null ? eResource.getResourceSet() : null;
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ProjectManager projectMap = BasicProjectManager.createDefaultProjectManager();
				projectMap.initializeResourceSet(resourceSet);
				this.ocl = OCLInternal.newInstance(projectMap, resourceSet);
			}
			else {
				this.ocl = OCLInternal.newInstance(environmentFactory);
			}
			this.genModel = genModel;
			genModel.eAdapters().add(this);
		}

		protected void addEAnnotationDetail(@NonNull EModelElement eModelElement, /*@NonNull*/ String sourceURI, @NonNull String detailName, @NonNull String value) {
			EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
			if (eAnnotation == null) {
				eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(sourceURI);
				edits.add(new AddEAnnotation(eModelElement.getEAnnotations(), eAnnotation));
			}
			edits.add(new SetEAnnotationDetail(eAnnotation, detailName, value));
		}

		protected void addEOperation(@NonNull EClass eClass, @NonNull EOperation eOperation) {
			edits.add(new AddEOperation(eClass.getEOperations(), eOperation));
		}

		protected void addModelPluginVariable(@NonNull String modelPluginVariable) {
			edits.add(new AddModelPluginVariable(modelPluginVariable));
		}

		protected void convertConstraintToOperation(@NonNull Ecore2AS ecore2as, @NonNull GenModel genModel, @NonNull EClass eClass, @NonNull String key, @NonNull String body, @Nullable String message) {
			org.eclipse.ocl.pivot.Class pType = ecore2as.getCreated(org.eclipse.ocl.pivot.Class.class, eClass);
			if (pType != null) {
				List<Constraint> ownedInvariants = pType.getOwnedInvariants();
				if (ownedInvariants.size() > 0) {
					String prefix = UML2GenModelUtil.getInvariantPrefix(genModel);
					if (prefix == null) {
						prefix = getInvariantPrefix(genModel);
						if (prefix == null) {
							prefix = "";
						}
					}
					String names = "";
					for (Constraint rule : ownedInvariants) {
						String ruleName = rule.getName();
						if (ruleName == null) {
							ruleName = "";
						}
						if (ruleName.equals(key)) {
							String prefixedName = prefix + ruleName;
							names = names.length() == 0 ? prefixedName : names + " " + prefixedName;
							EOperation eOperation = AS2Ecore.createConstraintEOperation(rule, prefixedName, null);
							addEOperation(eClass, eOperation);
							ecore2as.addMapping(eOperation, rule);
							if (message != null) {
								body = PivotUtil.createTupleValuedConstraint(body, null, message);
							}
							addEAnnotationDetail(eOperation, PivotConstants.OCL_DELEGATE_URI_PIVOT, "body", body);
						}
					}
					addEAnnotationDetail(eClass, EcorePackage.eNS_URI, "constraints", names);
				}
			}
		}

		protected void convertConstraintsToOperations(@NonNull MetamodelManagerInternal metamodelManager) {
			List<GenPackage> genPackages = genModel.getAllGenPackagesWithClassifiers();
			for (GenPackage genPackage : genPackages) {
				EPackage ecorePackage = genPackage.getEcorePackage();
				removeEAnnotation(ecorePackage.getEAnnotation(PivotConstants.IMPORT_ANNOTATION_SOURCE));
				Resource ecoreResource = ecorePackage.eResource();
				if (ecoreResource != null) {
					Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, metamodelManager.getEnvironmentFactory());
					for (GenClass genClass : genPackage.getGenClasses()) {
						EClass eClass = genClass.getEcoreClass();
						if (eClass != null) {
							List<EAnnotation> obsoleteAnnotations = null;
							for (EAnnotation eAnnotation : new ArrayList<>(eClass.getEAnnotations())) {
								String source = eAnnotation.getSource();
								if (OCLCommon.isDelegateURI(source)) {
									@SuppressWarnings("deprecation")
									String messageAnnotationDetailSuffix = PivotConstantsInternal.MESSAGE_ANNOTATION_DETAIL_SUFFIX;
									EMap<String, String> details = eAnnotation.getDetails();
									for (String key : details.keySet()) {
										if ((key != null) && !key.endsWith(messageAnnotationDetailSuffix)) {
											String expression = details.get(key);
											String messageExpression = details.get(key + messageAnnotationDetailSuffix);
											if (expression != null) {
												convertConstraintToOperation(ecore2as, genModel, eClass, key, expression, messageExpression);
											}
										}
									}
									if (obsoleteAnnotations == null) {
										obsoleteAnnotations = new ArrayList<>();
									}
									obsoleteAnnotations.add(eAnnotation);
								}
								if (EcorePackage.eNS_URI.equals(source)) {
									removeEAnnotationDetail(eAnnotation, "constraints");
								}
							}
							if (obsoleteAnnotations != null) {
								for (EAnnotation eAnnotation : obsoleteAnnotations) {
									removeEAnnotation(eAnnotation);
								}
							}
							genClass.initialize(eClass);
						}
					}
				}
			}
		}

		/**
		 * Create a Map of feature identification to body to be embedded in the EMF model.
		 * @throws IOException
		 */
		public @NonNull Map<@NonNull String, @NonNull String> createFeatureBodies(@NonNull GenModel genModel) throws IOException {
			Map<@NonNull String, @NonNull String> allResults = new HashMap<>();
			@SuppressWarnings("null")@NonNull List<GenPackage> allGenPackagesWithClassifiers = genModel.getAllGenPackagesWithClassifiers();
			List<@NonNull GenPackage> genPackages = ClassUtil.nullFree(allGenPackagesWithClassifiers);
			for (GenPackage genPackage : genPackages) {
				OCLinEcoreCodeGenerator.generatePackage(genPackage, allResults, constantTexts);
			}
			List<@NonNull String> resultsKeys = new ArrayList<>(allResults.keySet());
			Collections.sort(resultsKeys);
			return allResults;
		}

		public void dispose() {
			genModel.eAdapters().remove(this);
			for (int i = edits.size(); --i >= 0; ) {
				Edit edit = edits.get(i);
				edit.undo();
			}
			ocl.dispose();
		}

		public @NonNull Map<@NonNull GenPackage, @NonNull String> getConstantTexts() {
			return constantTexts;
		}

		protected @NonNull OCLinEcoreGenModelGeneratorAdapter getGenModelGeneratorAdapter() {
			return OCLinEcoreGenModelGeneratorAdapter.this;
		}

		public @NonNull MetamodelManagerInternal getMetamodelManager() {
			return ocl.getMetamodelManager();
		}

		@Override
		public @NonNull GenModel getTarget() {
			return genModel;
		}

		protected void installJavaBodies(@NonNull MetamodelManagerInternal metamodelManager, @NonNull GenModel genModel, @NonNull Map<String, String> results) {
			List<GenPackage> genPackages = genModel.getAllGenPackagesWithClassifiers();
			for (GenPackage genPackage : genPackages) {
				EPackage ecorePackage = genPackage.getEcorePackage();
				Resource ecoreResource = ecorePackage.eResource();
				if (ecoreResource != null) {
					Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, metamodelManager.getEnvironmentFactory());
					for (GenClass genClass : genPackage.getGenClasses()) {
						EClass eClass = genClass.getEcoreClass();
						if (eClass != null) {
							for (@SuppressWarnings("null")@NonNull EOperation eOperation : eClass.getEOperations()) {
								installOperation(ecore2as, eOperation, results);
							}
							for (@SuppressWarnings("null")@NonNull EStructuralFeature eFeature : eClass.getEStructuralFeatures()) {
								installProperty(ecore2as, eFeature, results);
							}
						}
					}
				}
			}
		}

		protected void installOperation(@NonNull Ecore2AS ecore2as, @NonNull EOperation eOperation, @NonNull Map<String, String> results) {
			Element pOperation = ecore2as.getCreated(Element.class, eOperation);
			String fragmentURI = null;
			if (pOperation instanceof Operation) {
				fragmentURI = String.valueOf(EcoreUtil.getURI(pOperation).fragment());
			}
			else if (pOperation instanceof Constraint) {
				Constraint constraint = (Constraint) pOperation;
				fragmentURI = String.valueOf(EcoreUtil.getURI(constraint.eContainer()).fragment()) + "==" + constraint.getName();
			}
			String body = fragmentURI != null ? results.get(fragmentURI) : null;
			if ((body == null) || ((body = body.trim()).length() == 0)) {
				String javaBody = EcoreUtil.getAnnotation(eOperation, GenModelPackage.eNS_URI, "body");
				if (javaBody != null) {
					return;		// Leave an existing Java body unaffected
				}
				body = "throw new UnsupportedOperationException();  // FIXME Unimplemented " + (pOperation != null ? AS2Moniker.toString(pOperation) : "");
			}
			addEAnnotationDetail(eOperation, GenModelPackage.eNS_URI, "body", body);
			//	removeEAnnotation(eOperation.getEAnnotation(OCLConstants.OCL_DELEGATE_URI));
			//	removeEAnnotation(eOperation.getEAnnotation(OCLConstants.OCL_DELEGATE_URI_LPG));
			//	removeEAnnotation(eOperation.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT));
			removeEAnnotation(eOperation.getEAnnotation(UML2GenModelUtil.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI));
		}

		protected void installProperty(@NonNull Ecore2AS ecore2as, @NonNull EStructuralFeature eFeature, @NonNull Map<String, String> results) {
			Property pProperty = ecore2as.getCreated(Property.class, eFeature);
			String fragmentURI = String.valueOf(EcoreUtil.getURI(pProperty).fragment());
			String body = results.get(fragmentURI);
			if (body == null) {
				String javaBody = EcoreUtil.getAnnotation(eFeature, GenModelPackage.eNS_URI, "get");
				if (javaBody != null) {
					return;		// Leave an existing Java body unaffected
				}
				body = "throw new UnsupportedOperationException();  // FIXME Unimplemented " + (pProperty != null ? AS2Moniker.toString(pProperty) : "");
			}
			addEAnnotationDetail(eFeature, GenModelPackage.eNS_URI, "get", body);
			//			addEAnnotationDetail(eFeature, GenModelPackage.eNS_URI, "body", body);
			removeEAnnotation(eFeature.getEAnnotation(OCLConstants.OCL_DELEGATE_URI));
			removeEAnnotation(eFeature.getEAnnotation(OCLConstants.OCL_DELEGATE_URI_LPG));
			removeEAnnotation(eFeature.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT));
			removeEAnnotation(eFeature.getEAnnotation(UML2GenModelUtil.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI));
		}


		/**
		 * Eliminate all OCL validation/setting/invocation delegates.
		 */
		protected void pruneDelegates(@NonNull GenModel genModel) {
			for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
				EPackage ePackage = genPackage.getEcorePackage();
				if ((ePackage != null) && hasDelegates(ePackage)) {
					edits.add(new SetValidationDelegates(ePackage));
					edits.add(new SetSettingDelegates(ePackage));
					edits.add(new SetInvocationDelegates(ePackage));
				}
			}
		}
		protected @NonNull List<String> pruneDelegates(@Nullable List<String> oldDelegates) {
			List<String> newDelegates = new ArrayList<>();
			if (oldDelegates != null) {
				for (String aDelegate : oldDelegates) {
					if (!OCLCommon.isDelegateURI(aDelegate)) {
						newDelegates.add(aDelegate);
					}
				}
			}
			return newDelegates;
		}

		protected void removeEAnnotation(@Nullable EAnnotation oclAnnotation) {
			if (oclAnnotation != null) {
				edits.add(new RemoveEAnnotation(oclAnnotation));
			}
		}

		protected void removeEAnnotationDetail(@NonNull EAnnotation eAnnotation, @NonNull String detailName) {
			edits.add(new SetEAnnotationDetail(eAnnotation, detailName));
		}

		@Override
		public boolean isAdapterForType(Object type) {
			return false;
		}

		@Override
		public void notifyChanged(Notification notification) {}

		@Override
		public void setTarget(Notifier newTarget) {
			assert (newTarget == null) || (newTarget == genModel);
		}
	}


	public OCLinEcoreGenModelGeneratorAdapter(@NonNull OCLinEcoreGeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	protected void createDispatchTables(@NonNull GenModel genModel, @NonNull Monitor monitor) throws IOException {
		try {
			Map<@NonNull GenPackage, @NonNull String> constantTexts = getStateAdapter(genModel).getConstantTexts();
			String lineDelimiter = getLineDelimiter(genModel);
			genModel.setLineDelimiter(lineDelimiter);
			File projectFolder = getProjectFolder(genModel);
			List<GenPackage> genPackages = genModel.getAllGenPackagesWithClassifiers();
			for (@SuppressWarnings("null")@NonNull GenPackage genPackage : genPackages) {
				OCLinEcoreTables generateTables = new OCLinEcoreTables(genPackage);
				generateTables.analyzeExpressions();
				String tablesClass = generateTables.getTablesClassName();
				String dir = genPackage.getReflectionPackageName().replace(".", "/");
				String constants = constantTexts.get(genPackage);
				generateTables.generateTablesClass(constants);
				String str = generateTables.toString();
				File tablesFolder = new File(projectFolder, dir);
				tablesFolder.mkdirs();
				File file = new File(tablesFolder, tablesClass + ".java");
				FileWriter testFile = new FileWriter(file);
				testFile.append(str);
				testFile.close();
			}
		}
		finally {
			genModel.setLineDelimiter(null);
		}
	}

	@Override
	protected Diagnostic doPostGenerate(Object object, Object projectType) {
		assert object != null;
		GenModel genModel = (GenModel) object;
		OCLinEcoreStateAdapter stateAdapter = findStateAdapter(genModel);
		if (stateAdapter != null) {
			stateAdapter.dispose();
			stateAdapter = null;
		}
		return super.doPostGenerate(object, projectType);
	}

	@Override
	protected Diagnostic doPreGenerate(Object object, Object projectType) {
		assert object != null;
		GenModel genModel = (GenModel) object;
		try {
			if ((projectType == MODEL_PROJECT_TYPE) && !useDelegates(genModel) && hasDelegates(genModel)) {
				OCLinEcoreStateAdapter stateAdapter = getStateAdapter(genModel);
				List<String> modelPluginVariables = genModel.getModelPluginVariables();
				if (!modelPluginVariables.contains(PivotPlugin.PLUGIN_ID)) {	// FIXME delete me BUG 401862
					stateAdapter.addModelPluginVariable(PivotPlugin.PLUGIN_ID);
				}
				if (!modelPluginVariables.contains("org.eclipse.ocl.examples.codegen")) {	// FIXME delete me BUG 401862
					stateAdapter.addModelPluginVariable("org.eclipse.ocl.examples.codegen");
				}
				if (useNullAnnotations(genModel) && !modelPluginVariables.contains("org.eclipse.jdt.annotation")) {
					stateAdapter.addModelPluginVariable("org.eclipse.jdt.annotation");
				}
				for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
					createImportManager(genPackage.getReflectionPackageName(), genPackage.getFactoryInterfaceName() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX);	// Only used to suppress NPE
				}
				Resource genResource = genModel.eResource();
				ResourceSet resourceSet = genResource.getResourceSet();
				if (resourceSet == null) {
					throw new NullPointerException("No ResourceSet for genmodel");
				}
				MetamodelManagerInternal metamodelManager = stateAdapter.getMetamodelManager();
				metamodelManager.getStandardLibrary().getOclAnyType();
				for (GenPackage genPackage : genModel.getGenPackages()) {
					EPackage ecorePackage = genPackage.getEcorePackage();
					org.eclipse.ocl.pivot.Package asPackage = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Package.class, ecorePackage);
					assert asPackage != null;
				}
				metamodelManager.installRoot(CGLibrary.getDefaultModel());
				stateAdapter.convertConstraintsToOperations(metamodelManager);
				Map<@NonNull String, @NonNull String> results = stateAdapter.createFeatureBodies(genModel);
				for (String key : results.keySet()) {
					String oldBody = results.get(key);
					assert oldBody != null;
					String newBody = ImportUtils.rewriteManagedImports(oldBody, null);	// FIXME transfer imports between CG sessions
					results.put(key, newBody);
				}
				stateAdapter.installJavaBodies(metamodelManager, genModel, results);
				stateAdapter.pruneDelegates(genModel);
			}
		} catch (Exception e) {
			Throwable t = e instanceof WrappedException ? ((WrappedException)e).getCause() : e;;
			BasicDiagnostic thisDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, getClass().getPackage().getName(), 0, "Failed to pre-generate " + genModel.getModelPluginID() + " constraints", new Object[]{t});
			Diagnostic thatDiagnostic = super.doPreGenerate(object, projectType);
			if (thatDiagnostic.getSeverity() == Diagnostic.OK) {
				return thisDiagnostic;
			}
			else {
				thatDiagnostic.getChildren().add(thisDiagnostic);
				return thatDiagnostic;
			}
		}
		return super.doPreGenerate(object, projectType);
	}

	protected @Nullable OCLinEcoreStateAdapter findStateAdapter(@NonNull GenModel genModel) {
		for (Adapter adapter : genModel.eAdapters()) {
			if ((adapter instanceof OCLinEcoreStateAdapter) && (((OCLinEcoreStateAdapter)adapter).getGenModelGeneratorAdapter() == this)) {
				return (OCLinEcoreStateAdapter) adapter;
			}
		}
		return null;
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		assert object != null;
		GenModel genModel = (GenModel) object;
		OCLinEcoreStateAdapter stateAdapter = findStateAdapter(genModel);
		try {
			//			if (!useDelegates(genModel) && hadDelegates.contains(genModel)) {
			if (stateAdapter != null) {
				monitor.beginTask("", 4);
				monitor.subTask("Generating Dispatch Tables");
				ensureProjectExists
				(genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));
				if (getImportManager() == null) {
					for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
						createImportManager(genPackage.getReflectionPackageName(), genPackage.getFactoryInterfaceName() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX);	// Only used to suppress NPE
					}
				}
				createDispatchTables(genModel, monitor);
				monitor.worked(1);
				monitor.worked(1);
				if (EMFPlugin.IS_ECLIPSE_RUNNING) {
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					String modelProjectDirectory = genModel.getModelProjectDirectory();
					IProject modelProject = workspace.getRoot().getProject(modelProjectDirectory);
					modelProject.refreshLocal(IResource.DEPTH_INFINITE, BasicMonitor.toIProgressMonitor(monitor));
				}
				monitor.worked(1);
			}
		} catch (Exception e) {
			BasicDiagnostic thisDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, getClass().getPackage().getName(), 0, "Failed to generate " + genModel.getModelPluginID() + " tables and bodies", new Object[]{e});
			//			Diagnostic thatDiagnostic = super.generateModel(object, monitor);
			//			if (thatDiagnostic.getSeverity() == Diagnostic.OK) {
			return thisDiagnostic;
			//			}
			//			else {
			//				thatDiagnostic.getChildren().add(thisDiagnostic);
			//				return thatDiagnostic;
			//			}
		}
		return super.generateModel(object, monitor);
	}

	/**
	 * Deduce the required line delimiter from the usage in the .project file.
	 */
	protected String getLineDelimiter(GenModel genModel) {
		String modelProjectDirectory = genModel.getModelProjectDirectory() + "/.project";
		URI workspacePath = URI.createURI(modelProjectDirectory);
		String targetFileEncoding = getEncoding(workspacePath);
		return getLineDelimiter(workspacePath, targetFileEncoding);
	}

	protected @NonNull File getProjectFolder(@NonNull GenModel genModel) {
		String modelProjectDirectory = genModel.getModelProjectDirectory();
		String modelDirectory = genModel.getModelDirectory();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProject modelProject = workspace.getRoot().getProject(modelProjectDirectory);
			IPath javaSource = new Path(modelDirectory);
			IFolder folder = modelProject.getParent().getFolder(javaSource);
			java.net.URI locationURI = ClassUtil.nonNullState(folder.getLocationURI());
			return ClassUtil.nonNullState(URIUtil.toFile(locationURI));
		}
		else {
			URI locationURI = URI.createPlatformResourceURI(modelDirectory, true);
			ResourceSet resourceSet = genModel.eResource().getResourceSet();
			URIConverter uriConverter = resourceSet != null ? resourceSet.getURIConverter() : URIConverter.INSTANCE;
			URI normalizedURI = uriConverter.normalize(locationURI);
			return new File(normalizedURI.toFileString());
		}
	}

	protected @NonNull OCLinEcoreStateAdapter getStateAdapter(@NonNull GenModel genModel) {
		for (Adapter adapter : genModel.eAdapters()) {
			if ((adapter instanceof OCLinEcoreStateAdapter) && (((OCLinEcoreStateAdapter)adapter).getGenModelGeneratorAdapter() == this)) {
				return (OCLinEcoreStateAdapter) adapter;
			}
		}
		return new OCLinEcoreStateAdapter(genModel);
	}

	protected boolean hasConstraints(org.eclipse.ocl.pivot.Class pivotClass) {
		if (pivotClass.getOwnedInvariants().size() > 0) {
			return true;
		}
		for (Operation operation : PivotQueries.getOperations(pivotClass)) {
			if (operation.getOwnedPreconditions().size() > 0) {
				return true;
			}
			if (operation.getOwnedPostconditions().size() > 0) {
				return true;
			}
			if (operation.getBodyExpression() != null) {
				return true;
			}
		}
		for (Property property : PivotQueries.getProperties(pivotClass)) {
			if (property.getOwnedExpression() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return true if any local GenPackage is for an EPackage that has OCL validation/setting/invocation delegates.
	 */
	protected boolean hasDelegates(@NonNull GenModel genModel) {
		for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
			EPackage ePackage = genPackage.getEcorePackage();
			if ((ePackage != null) && hasDelegates(ePackage)) {
				return true;
			}
		}
		return false;
	}
	protected boolean hasDelegates(@NonNull EPackage ePackage) {
		List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
		for (String validationDelegate : validationDelegates) {
			if (OCLCommon.isDelegateURI(validationDelegate)) {
				return true;
			}
		}
		List<String> settingDelegates = EcoreUtil.getSettingDelegates(ePackage);
		for (String settingDelegate : settingDelegates) {
			if (OCLCommon.isDelegateURI(settingDelegate)) {
				return true;
			}
		}
		List<String> invocationDelegates = EcoreUtil.getInvocationDelegates(ePackage);
		for (String invocationDelegate : invocationDelegates) {
			if (OCLCommon.isDelegateURI(invocationDelegate)) {
				return true;
			}
		}
		return false;
	}
}
