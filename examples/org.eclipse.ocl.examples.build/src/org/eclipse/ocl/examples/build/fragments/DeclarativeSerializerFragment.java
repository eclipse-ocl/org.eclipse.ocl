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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.DeclarativeSerializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
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

	private @NonNull OCL ocl = OCL.newInstance(OCL.NO_PROJECTS);
	private @NonNull MetamodelManager metamodelManager = ocl.getMetamodelManager();
	private @NonNull GenModelHelper genModelHelper = new AbstractGenModelHelper((PivotMetamodelManager)metamodelManager);
	private @Nullable GrammarAnalysis grammarAnalysis;

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
		GrammarAnalysis grammarAnalysis = getGrammarAnalysis();
		javaFile.setContent(doGetAnalysisProviderContent(grammarAnalysis));
		return javaFile;
	}

	protected @NonNull String emitCalledRule(@NonNull CrossReference crossReference) {
		RuleCall ruleCall = (RuleCall)XtextGrammarUtil.getTerminal(crossReference);
		AbstractRule abstractRule = XtextGrammarUtil.getRule(ruleCall);
		AbstractRuleAnalysis ruleAnalysis = getGrammarAnalysis().getRuleAnalysis(abstractRule);
		return getRuleValueId(ruleAnalysis.getRuleValue(), true);
	}

	protected @NonNull String emitLiteral(@NonNull EClassifier eClassifier) {
		return new TypeReference(genModelHelper.getQualifiedPackageInterfaceName(eClassifier.getEPackage())) + ".Literals." + genModelHelper.getLiteralName(eClassifier);
	}

	protected @NonNull String emitLiteral(@NonNull EStructuralFeature eStructuralFeature) {
		return new TypeReference(genModelHelper.getQualifiedPackageInterfaceName(eStructuralFeature.getEContainingClass().getEPackage())) + ".Literals." + genModelHelper.getEcoreLiteralName(eStructuralFeature);
	}

	protected @NonNull String emitQualifiedLiteral(@NonNull EPackage ePackage) {
		return ClassUtil.nonNullState(genModelHelper.getQualifiedPackageInterfaceName(ePackage));
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

	/**
	 * Return the formString that encodes a fixed width zero padded element of domain.
	 */
	protected @NonNull String getDigitsFormatString(@NonNull Collection<?> domain) {
		int digits = domain.size() > 0 ? (int)Math.ceil(Math.log10(domain.size())) : 1;
		return "%0" + digits + "d";
	}

	protected @NonNull GrammarAnalysis getGrammarAnalysis() {
		GrammarAnalysis grammarAnalysis2 = grammarAnalysis;
		if (grammarAnalysis2 == null) {
			Grammar grammar = getGrammar();
			assert grammar != null;
			GrammarAnalysis grammarAnalysis = grammarAnalysis2 = new GrammarAnalysis(grammar);
			grammarAnalysis.analyze();
		}
		return grammarAnalysis2;
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
			for(@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
				for(@NonNull RTSerializationStep serializationStep : serializationRule.getBasicSerializationRule().getRuntime().getSerializationSteps()) {
					serializationStep2id2.put(serializationStep, "");
				}
			}
		}
		List<@NonNull RTSerializationStep> steps = new ArrayList<>(serializationStep2id2.keySet());
		Collections.sort(steps, NameUtil.TO_STRING_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(steps);
		int i = 0;
		for (@NonNull RTSerializationStep step : steps) {
			serializationStep2id2.put(step, String.format(formatString, i++));
		}
		return steps;
	}

	protected @NonNull String getSerializationStepId(@NonNull RTSerializationStep step, boolean addQualifier) {
		assert serializationStep2id != null;
		String id = serializationStep2id.get(step);
		assert id != null;
		return addQualifier ? "st." + id : id;
	}

	private @Nullable Map<@NonNull List<Segment>, @NonNull String> segments2id = null;

	protected @NonNull Iterable<@NonNull List<Segment>> getSortedSegments(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull List<Segment>, @NonNull String> segments2id2 = segments2id;
		if (segments2id2 == null) {
			segments2id = segments2id2 = new HashMap<>();
		}
		for (@NonNull Idiom idiom : grammarAnalysis.getIdioms()) {
			List<SubIdiom> staticSubIdioms = idiom.getOwnedSubIdioms();
			for(@NonNull SubIdiom subIdiom : staticSubIdioms) {
				segments2id2.put(subIdiom.getSegments(), "");
			}
		}
		List<@NonNull List<Segment>> segmentLists = new ArrayList<>(segments2id2.keySet());
		Collections.sort(segmentLists, NameUtil.TO_STRING_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(segmentLists);
		int i = 0;
		for (@NonNull List<Segment> segmentList : segmentLists) {
			segments2id2.put(segmentList, String.format(formatString, i++));
		}
		return segmentLists;
	}

	protected @NonNull String getSegmentsId(@NonNull List<Segment> segments, boolean addQualifier) {
		assert segments2id != null;
		String id = segments2id.get(segments);
		assert id != null;
		return addQualifier ? "ss." + id : id;
	}

	private @Nullable Map<@NonNull CardinalitySolutionStep, @NonNull String> solutionStep2id = null;

	protected @NonNull Iterable<@NonNull CardinalitySolutionStep> getSortedSolutionSteps(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull CardinalitySolutionStep, @NonNull String> solutionStep2id2 = solutionStep2id;
		if (solutionStep2id2 == null) {
			solutionStep2id = solutionStep2id2 = new HashMap<>();
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			for(@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
				for(@NonNull CardinalitySolutionStep solutionStep : serializationRule.getBasicSerializationRule().getStaticRuleMatch().getSteps()) {
					solutionStep2id2.put(solutionStep, "");
				}
			}
		}
		List<@NonNull CardinalitySolutionStep> solutionSteps = new ArrayList<>(solutionStep2id2.keySet());
		Collections.sort(solutionSteps, NameUtil.TO_STRING_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(solutionSteps);
		int i = 0;
		for (@NonNull CardinalitySolutionStep solutionStep : solutionSteps) {
			solutionStep2id2.put(solutionStep, String.format(formatString, i++));
		}
		return solutionSteps;
	}

	protected @NonNull String getSolutionStepId(@NonNull CardinalitySolutionStep solutionStep, boolean addQualifier) {
		assert solutionStep2id != null;
		String id = solutionStep2id.get(solutionStep);
		assert id != null;
		return addQualifier ? "ms." + id : id;
	}

	private @Nullable Map<@NonNull CardinalitySolution, @NonNull String> solution2id = null;

	protected @NonNull Iterable<@NonNull CardinalitySolution> getSortedSolutions(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull CardinalitySolution, @NonNull String> solution2id2 = solution2id;
		if (solution2id2 == null) {
			solution2id = solution2id2 = new HashMap<>();
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			for(@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
				for(@NonNull CardinalitySolutionStep solutionStep : serializationRule.getBasicSerializationRule().getStaticRuleMatch().getSteps()) {
					for (@NonNull CardinalitySolution solution : solutionStep.getSolutionClosure()) {
						solution2id2.put(solution, "");
					}
				}
			}
		}
		List<@NonNull CardinalitySolution> solutions = new ArrayList<>(solution2id2.keySet());
		Collections.sort(solutions, new Comparator<@NonNull CardinalitySolution>()
		{
			@Override
			public int compare(@NonNull CardinalitySolution o1, @NonNull CardinalitySolution o2) {
				int s1 = o1.getChildClosure().size();
				int s2 = o2.getChildClosure().size();
				if (s1 != s2) {
					return s1 - s2;
				}
				return o1.toString().compareTo(o2.toString());
			}
		});
		String formatString = "_" + getDigitsFormatString(solutions);
		int i = 0;
		for (@NonNull CardinalitySolution solution : solutions) {
			solution2id2.put(solution, String.format(formatString, i++));
		}
		return solutions;
	}

	protected @NonNull String getSolutionId(@NonNull CardinalitySolution solutionStep, boolean addQualifier) {
		assert solution2id != null;
		String id = solution2id.get(solutionStep);
		assert id != null;
		return addQualifier ? "mt." + id : id;
	}

	private @Nullable Map<@NonNull EnumerationValue, @NonNull String> enumValue2id = null;

	protected @NonNull Iterable<@NonNull EnumerationValue> getSortedEnumValues(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull EnumerationValue, @NonNull String> enumValue2id2 = enumValue2id;
		if (enumValue2id2 == null) {
			enumValue2id = enumValue2id2 = new HashMap<>();
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			for (@NonNull EnumerationValue enumValue : grammarAnalysis.getEnumerationValues()) {
				enumValue2id2.put(enumValue, "");
			}
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			for (@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
				for (@NonNull CardinalitySolutionStep solutionStep : serializationRule.getBasicSerializationRule().getStaticRuleMatch().getSteps()) {
					for (@NonNull CardinalitySolution solution : solutionStep.getSolutionClosure()) {
						if (solution instanceof EAttributeSizeCardinalitySolution) {
							enumValue2id2.put(((EAttributeSizeCardinalitySolution)solution).getEnumerationValue(), "");
						}
					}
				}
			}
		}
		List<@NonNull EnumerationValue> enumValues = new ArrayList<>(enumValue2id2.keySet());
		Collections.sort(enumValues, NameUtil.NAMEABLE_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(enumValues);
		int i = 0;
		for (@NonNull EnumerationValue enumValue : enumValues) {
			enumValue2id2.put(enumValue, String.format(formatString, i++));
		}
		return enumValues;
	}

	protected @NonNull String getEnumValueId(@NonNull EnumerationValue enumValue, boolean addQualifier) {
		assert enumValue2id != null;
		String id = enumValue2id.get(enumValue);
		assert id != null;
		return addQualifier ? "ev." + id : id;
	}

	private @Nullable Map<@NonNull AbstractRuleValue, @NonNull String> ruleValue2id = null;
	private @Nullable Map<@NonNull Integer, @NonNull String> ruleValueIndex2ruleName = null;

	protected void initRuleValues(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull AbstractRuleValue, @NonNull String> ruleValue2id2 = ruleValue2id;
		Map<@NonNull Integer, @NonNull String> ruleValueIndex2ruleName2 = ruleValueIndex2ruleName;
		if (ruleValue2id2 == null) {
			ruleValue2id = ruleValue2id2 = new HashMap<>();
		}
		if (ruleValueIndex2ruleName2 == null) {
			ruleValueIndex2ruleName = ruleValueIndex2ruleName2 = new HashMap<>();
		}
		for (@NonNull AbstractRuleAnalysis ruleAnalysis : grammarAnalysis.getRuleAnalyses()) {
		//	if (ruleAnalysis instanceof ParserRuleAnalysis) {
			ruleValue2id2.put(ruleAnalysis.getRuleValue(), "");
			ruleValueIndex2ruleName2.put(ruleAnalysis.getRuleValue().getIndex(), ruleAnalysis.getRuleName());
		//	}
		}
		List<@NonNull AbstractRuleValue> ruleValues = new ArrayList<>(ruleValue2id2.keySet());
		Collections.sort(ruleValues, NameUtil.NAMEABLE_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(ruleValues);
		int i = 0;
		for (@NonNull AbstractRuleValue ruleValue : ruleValues) {
			ruleValue2id2.put(ruleValue, String.format(formatString, i++));
		}
	}

	protected @NonNull Iterable<@NonNull AbstractRuleValue> getSortedRuleValues(@NonNull GrammarAnalysis grammarAnalysis) {
		assert ruleValue2id != null;
		List<@NonNull AbstractRuleValue> ruleValues = new ArrayList<>(ruleValue2id.keySet());
		Collections.sort(ruleValues, NameUtil.NAMEABLE_COMPARATOR);
		return ruleValues;
	}

	protected @NonNull String getRuleValueId(@NonNull AbstractRuleValue ruleValue, boolean addQualifier) {
		assert ruleValue2id != null;
		String id = ruleValue2id.get(ruleValue);
		assert id != null;
		return addQualifier ? "rv." + id : id;
	}

	protected @NonNull String getRuleName(@NonNull Integer ruleValueIndex) {
		assert ruleValueIndex2ruleName != null;
		String id = ruleValueIndex2ruleName.get(ruleValueIndex);
		assert id != null;
		return id;
	}

	private @Nullable Map<@NonNull RTSerializationRule, @NonNull String> serializationRule2id = null;
	private @Nullable List<@NonNull RTSerializationRule> serializationRules = null;

	protected void initSerializationRule2id(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull RTSerializationRule, @NonNull String> serializationRule2id2 = serializationRule2id;
		if (serializationRule2id2 == null) {
			serializationRule2id = serializationRule2id2 = new HashMap<>();
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : grammarAnalysis.getRuleAnalyses()) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					for (@NonNull SerializationRule serializationRule : ((ParserRuleAnalysis)ruleAnalysis).getSerializationRules()) {
					//	System.out.println(NameUtil.debugSimpleName(serializationRule) + " => " + NameUtil.debugSimpleName(serializationRule.getBasicSerializationRule()) + " => " + NameUtil.debugSimpleName(serializationRule.getBasicSerializationRule().getRuntime()) + " : " + serializationRule.toString());
						serializationRule2id2.put(serializationRule.getBasicSerializationRule().getRuntime(), "");
					}
				}
			}
			List<@NonNull RTSerializationRule> serializationRules = new ArrayList<>(serializationRule2id2.keySet());
			Collections.sort(serializationRules, NameUtil.NAMEABLE_COMPARATOR);
			String formatString = "_" + getDigitsFormatString(serializationRules);
			int i = 0;
			for (@NonNull RTSerializationRule serializationRule : serializationRules) {
				serializationRule2id2.put(serializationRule, String.format(formatString, i++));
			}
			this.serializationRules = serializationRules;
		}
	}

	protected @NonNull Iterable<@NonNull RTSerializationRule> getSortedSerializationRules(@NonNull GrammarAnalysis grammarAnalysis) {
		assert serializationRules != null;
		return serializationRules;
	}

	protected @NonNull String getSerializationRuleId(@NonNull RTSerializationRule serializationRule, boolean addQualifier) {
		assert serializationRule2id != null;
		String id = serializationRule2id.get(serializationRule);
	//	System.out.println("?? " + NameUtil.debugSimpleName(serializationRule) + " => " + id  + " : " + serializationRule.toRuleString());
		assert id != null;
		return addQualifier ? "sr." + id : id;
	}

	private @Nullable Map<@NonNull EClass, @NonNull String> eClass2id = null;

	protected @NonNull Iterable<@NonNull EClass> getSortedEClasses(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull EClass, @NonNull String> eClass2id2 = eClass2id;
		if (eClass2id2 == null) {
			eClass2id = eClass2id2 = new HashMap<>();
		}
		for (@NonNull EClass eClass : grammarAnalysis.getSortedProducedEClasses()) {
			eClass2id2.put(eClass, "");
		}
		List<@NonNull EClass> eClasses = new ArrayList<>(eClass2id2.keySet());
		Collections.sort(eClasses, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(eClasses);
		int i = 0;
		for (@NonNull EClass eClass : eClasses) {
			eClass2id2.put(eClass, String.format(formatString, i++));
		}
		return eClasses;
	}

	protected @NonNull String getEClassId(@NonNull EClass eClass, boolean addQualifier) {
		assert eClass2id != null;
		String id = eClass2id.get(eClass);
		assert id != null;
		return addQualifier ? "ec." + id : id;
	}

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected void initAnalysisProviderContent(@NonNull GrammarAnalysis grammarAnalysis) {
		getSortedEnumValues(grammarAnalysis);
		getSortedSegments(grammarAnalysis);
		getSortedSerializationSteps(grammarAnalysis);
		getSortedSolutions(grammarAnalysis);
		getSortedSolutionSteps(grammarAnalysis);
		initRuleValues(grammarAnalysis);
		getSortedEClasses(grammarAnalysis);
		initSerializationRule2id(grammarAnalysis);
	}
}
