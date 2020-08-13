/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.DeclarativeSerializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;

import com.google.inject.Inject;

/**
 * The DeclarativeSerializerFragment collaborates with the DeclarativeFormatterFragment to replace the
 * backtracking serializer and the Xtend-dependent formatting specifiation approach of the 'new infrastructure'
 * by a largely statically determined serializer and declarative idiom-based formatter.
 */
@SuppressWarnings("restriction")
public abstract class DeclarativeSerializerFragment extends SerializerFragment2
{
	private static final Logger LOG = Logger.getLogger(DeclarativeSerializerFragment.class);

	@Inject
	private XtextGeneratorNaming xtextGeneratorNaming;

	@Inject
	private  FileAccessFactory fileAccessFactory;

	private @NonNull OCL ocl = OCL.newInstance();
	private @NonNull MetamodelManager metamodelManager = ocl.getMetamodelManager();
	private @NonNull GenModelHelper genModelHelper = new AbstractGenModelHelper((PivotMetamodelManager)metamodelManager);

	protected abstract StringConcatenationClient doGetAnalysisProviderContent(@NonNull GrammarAnalysis grammarAnalysis);

	protected void doGenerateAnalysisStubFile() {
		JavaFileAccess javaFile = this.doGetAnalysisStubFile();
		if (javaFile != null) {
			javaFile.setMarkedAsGenerated(true);		// FIXME There must be a smarter way
			javaFile.writeTo(this.getProjectConfig().getRuntime().getSrcGen());
		}
	}

	protected JavaFileAccess doGetAnalysisStubFile() {
		if (!isGenerateStub()) {
			return null;
		}
		if (isGenerateXtendStub()) {
			String name = getClass().getName();
			LOG.error(name + " has been configured to generate an Xtend stub, but that\'s not yet supported.");
			return null;
		}
		IXtextGeneratorLanguage language = getLanguage();
		Grammar grammar = getGrammar();
		assert grammar != null;
		TypeReference analysisProviderStub = getAnalysisProviderClass(grammar);
		JavaFileAccess javaFile = fileAccessFactory.createJavaFile(analysisProviderStub);
		javaFile.setResourceSet(language.getResourceSet());
		GrammarAnalysis grammarAnalysis = new GrammarAnalysis(grammar);
		grammarAnalysis.analyze();
		javaFile.setContent(doGetAnalysisProviderContent(grammarAnalysis));
		return javaFile;
	}

	protected String emitLiteral(@NonNull EClassifier eClassifier) {
		return new TypeReference(genModelHelper.getQualifiedPackageInterfaceName(eClassifier.getEPackage())) + ".Literals." + genModelHelper.getLiteralName(eClassifier);
	}

	protected String emitLiteral(@NonNull EStructuralFeature eStructuralFeature) {
		return new TypeReference(genModelHelper.getQualifiedPackageInterfaceName(eStructuralFeature.getEContainingClass().getEPackage())) + ".Literals." + genModelHelper.getEcoreLiteralName(eStructuralFeature);
	}

	protected String emitQualifiedLiteral(@NonNull EPackage ePackage) {
		return genModelHelper.getQualifiedPackageInterfaceName(ePackage);
	}

	@Override
	public void generate() {
		Grammar grammar = this.getGrammar();
		GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
		GuiceModuleAccess runtimeGenModule = this.getLanguage().getRuntimeGenModule();
		bindingFactory.addTypeToType(TypeReference.typeRef(ISerializer.class), TypeReference.typeRef(DeclarativeSerializer.class)).contributeTo(runtimeGenModule);
		bindingFactory.addTypeToType(TypeReference.typeRef(AbstractAnalysisProvider.class), getAnalysisProviderClass(grammar)).contributeTo(runtimeGenModule);
		doGenerateAnalysisStubFile();
	}

	@Override
	protected void generateAbstractSemanticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateAbstractSyntacticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateGrammarConstraints() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateSemanticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void generateSyntacticSequencer() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getAbstractSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getAbstractSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected TypeReference getAnalysisProviderClass(Grammar grammar) {
		return new TypeReference(getSerializerBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "AnalysisProvider");
	}

	protected TypeReference getAnalysisProviderSuperClass(Grammar grammar) {
		return new TypeReference(AbstractAnalysisProvider.class);
	}

	@Override
	protected String getGrammarConstraintsPath(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TypeReference getSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getSerializerBasePackage(final Grammar grammar) {
		return xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".serializer";
	}

	private @Nullable Map<@NonNull RTSerializationStep, @NonNull String> serializationStep2id = null;

	protected @NonNull Iterable<@NonNull RTSerializationStep> getSortedSerializationSteps(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull RTSerializationStep, @NonNull String> serializationStep2id2 = serializationStep2id;
		if (serializationStep2id2 == null) {
			serializationStep2id = serializationStep2id2 = new HashMap<>();
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			for(@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass).getSerializationRules()) {
				for(@NonNull RTSerializationStep serializationStep : serializationRule.getBasicSerializationRule().getRuntime().getSerializationSteps()) {
					serializationStep2id2.put(serializationStep, "");
				}
			}
		}
		List<@NonNull RTSerializationStep> steps = new ArrayList<>(serializationStep2id2.keySet());
		Collections.sort(steps, NameUtil.TO_STRING_COMPARATOR);
		int i = 0;
		for (@NonNull RTSerializationStep step : steps) {
			serializationStep2id2.put(step, String.format("Step%04d", i++));
		}
		return steps;
	}

	protected @NonNull String getSerializationStepId(@NonNull RTSerializationStep step) {
		assert serializationStep2id != null;
		String id = serializationStep2id.get(step);
		assert id != null;
		return id;
	}

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}
}
