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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassValue.SerializationRule_SegmentsList;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
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

import com.google.common.collect.Lists;
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

	public static int RULES_PER_PAGE = 64;

	@Inject
	private XtextGeneratorNaming xtextGeneratorNaming;

	@Inject
	private  FileAccessFactory fileAccessFactory;

	private @NonNull OCL ocl = OCL.newInstance(OCL.NO_PROJECTS);
	private @NonNull MetamodelManager metamodelManager = ocl.getMetamodelManager();
	private @NonNull GenModelHelper genModelHelper = new AbstractGenModelHelper((PivotMetamodelManager)metamodelManager);
	private @Nullable GrammarAnalysis grammarAnalysis;
	private @NonNull Set<@NonNull String> referredClassNames = new HashSet<>();

	private @Nullable Map<@NonNull EClass, @NonNull String> eClass2id = null;
	private @Nullable Map<@NonNull EnumerationValue, @NonNull String> enumValue2id = null;
	private @Nullable Map<@NonNull AbstractRuleValue, @NonNull String> grammarRuleValue2id = null;
	private @Nullable Map<@NonNull Integer, @NonNull String> grammarRuleValueIndex2ruleName = null;
	private @Nullable Map<@NonNull IndexVector, @NonNull String> indexVector2id = null;
	private @Nullable List<@NonNull IndexVector> indexVectors = null;
	private @Nullable Map<@NonNull CardinalitySolutionStep, @NonNull String> matchStep2id = null;
	private @Nullable Map<@NonNull CardinalitySolution, @NonNull String> matchTerm2id = null;
	private @Nullable Map<@NonNull List<Segment>, @NonNull String> segments2id = null;
	private @NonNull Map<@NonNull Segment [] [], @NonNull String> segmentsList2string = new HashMap<>();
	private @NonNull Map<@NonNull String, @NonNull Segment [] []> segmentsListString2segmentsList = new HashMap<>();
	private @Nullable Map<@NonNull String, @NonNull String> segmentsList2id = null;
	private @Nullable Map<@NonNull SerializationRule, @NonNull String> serializationRule2id = null;
	private @Nullable List<@NonNull SerializationRuleAnalysis> serializationRuleAnalyses = null;
	private @Nullable Map<@NonNull RTSerializationStep, @NonNull String> serializationStep2id = null;

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
		return ruleAnalysis.getRuleName();
	}

	protected @NonNull String emitLiteral(@NonNull EClassifier eClassifier) {
		return newTypeReference(genModelHelper.getQualifiedPackageInterfaceName(eClassifier.getEPackage())) + ".Literals." + genModelHelper.getLiteralName(eClassifier);
	}

	protected @NonNull String emitLiteral(@NonNull EStructuralFeature eStructuralFeature) {
		return newTypeReference(genModelHelper.getQualifiedPackageInterfaceName(eStructuralFeature.getEContainingClass().getEPackage())) + ".Literals." + genModelHelper.getEcoreLiteralName(eStructuralFeature);
	}

	protected @NonNull String emitMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality.equals(MultiplicativeCardinality.ONE)) {
			return newTypeReference(MultiplicativeCardinality.class) + ".ONE";
		}
		else if (multiplicativeCardinality.equals(MultiplicativeCardinality.ZERO_OR_ONE)) {
			return newTypeReference(MultiplicativeCardinality.class) + ".ZERO_OR_ONE";
		}
		else if (multiplicativeCardinality.equals(MultiplicativeCardinality.ZERO_OR_MORE)) {
			return newTypeReference(MultiplicativeCardinality.class) + ".ZERO_OR_MORE";
		}
		else if (multiplicativeCardinality.equals(MultiplicativeCardinality.ONE_OR_MORE)) {
			return newTypeReference(MultiplicativeCardinality.class) + ".ONE_OR_MORE";
		}
		return multiplicativeCardinality.toString();
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
		return getDigitsFormatString(domain.size());
	}
	protected @NonNull String getDigitsFormatString(int domainSize) {
		int digits = domainSize > 0 ? (int)Math.ceil(Math.log10(domainSize)) : 1;
		return "%0" + digits + "d";
	}

	protected @NonNull String getEClassId(@NonNull EClass eClass, boolean addQualifier) {
		assert eClass2id != null;
		String id = eClass2id.get(eClass);
		assert id != null;
		return addQualifier ? "ec." + id : id;
	}

	protected @NonNull Iterable<@NonNull EClass> getEClassIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull EClass, @NonNull String> eClass2id2 = eClass2id;
		if (eClass2id2 == null) {
			eClass2id = eClass2id2 = new HashMap<>();
		}
		for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			eClass2id2.put(eClassValue.getEClass(), "");
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

	protected @NonNull Iterable<@NonNull EnumerationValue> getEnumValueIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull EnumerationValue, @NonNull String> enumValue2id2 = enumValue2id;
		if (enumValue2id2 == null) {
			enumValue2id = enumValue2id2 = new HashMap<>();
		}
	//	for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			for (@NonNull EnumerationValue enumValue : grammarAnalysis.getEnumerationValues()) {
				enumValue2id2.put(enumValue, "");
	//		}
		}
		for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : eClassValue.getSerializationRuleSegmentsLists()) {
				SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
				SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
				for (@NonNull CardinalitySolutionStep solutionStep : serializationRuleAnalysis.getStaticRuleMatch().getSteps()) {
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

	protected @NonNull List<@NonNull EReference_RuleIndexes> getEReferenceRuleIndexesIterable(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EClass eClass) {
		List<@NonNull EReference_RuleIndexes> eReferenceRuleIndexes = Lists.newArrayList(grammarAnalysis.getEReferenceRuleIndexes(eClass));
		Collections.sort(eReferenceRuleIndexes, NameUtil.NAMEABLE_COMPARATOR);
		return eReferenceRuleIndexes;
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

	protected @NonNull String getGrammarRuleName(@NonNull Integer grammarRuleValueIndex) {
		assert grammarRuleValueIndex2ruleName != null;
		String id = grammarRuleValueIndex2ruleName.get(grammarRuleValueIndex);
		assert id != null;
		return id;
	}

	protected @NonNull String getGrammarRuleValueId(@NonNull AbstractRuleValue grammarRuleValue, boolean addQualifier) {
		assert grammarRuleValue2id != null;
		String id = grammarRuleValue2id.get(grammarRuleValue);
		assert id != null;
		return addQualifier ? "gr." + id : id;
	}

	protected @NonNull Iterable<@NonNull AbstractRuleValue> getGrammarRuleValueIterator(@NonNull GrammarAnalysis grammarAnalysis) {
		assert grammarRuleValue2id != null;
		List<@NonNull AbstractRuleValue> grammarRuleValues = new ArrayList<>(grammarRuleValue2id.keySet());
		Collections.sort(grammarRuleValues, NameUtil.NAMEABLE_COMPARATOR);
		return grammarRuleValues;
	}

	protected @NonNull Iterable<@NonNull String> getImportedClassNameIterable() {
		List<@NonNull String> referredClassesList = new ArrayList<>(referredClassNames);
		Collections.sort(referredClassesList);
		return referredClassesList;
	}

	protected @NonNull String getIndexVectorId(@NonNull IndexVector indexVector, boolean addQualifier) {
		assert indexVector2id != null;
		String id = indexVector2id.get(indexVector);
		return addQualifier ? "iv." + id : id;
	}

	protected @NonNull Iterable<@NonNull IndexVector> getIndexVectorIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull IndexVector, @NonNull String> indexVector2id2 = indexVector2id;
		List<@NonNull IndexVector> indexVectors2 = indexVectors;
		if ((indexVector2id2 == null) || (indexVectors2 == null)) {
			indexVector2id = indexVector2id2 = new HashMap<>();
			for (@NonNull EClass eClass : getEClassIterable(grammarAnalysis)) {
				@NonNull EReference_RuleIndexes[] eReferenceRuleIndexes = grammarAnalysis.basicGetEReferenceRuleIndexes(eClass);
				if (eReferenceRuleIndexes != null) {
					for (@NonNull EReference_RuleIndexes eReferenceRuleIndex : eReferenceRuleIndexes) {
						IndexVector assignedTargetRuleValues = eReferenceRuleIndex.getAssignedTargetRuleValueIndexes();
						indexVector2id2.put(assignedTargetRuleValues, "");
					}
				}
			}
			for (@NonNull AbstractRuleValue grammarRuleValue : getGrammarRuleValueIterator(grammarAnalysis)) {
				if (grammarRuleValue instanceof ParserRuleValue) {
					IndexVector subParserRuleValueIndexes = ((ParserRuleValue)grammarRuleValue).getSubParserRuleValueIndexes();
					if (subParserRuleValueIndexes != null) {
						indexVector2id2.put(subParserRuleValueIndexes, "");
					}
				}
			}
			for (@NonNull CardinalitySolutionStep matchStep : getMatchStepIterable(grammarAnalysis)) {
				if (matchStep instanceof CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck) {
					indexVector2id2.put(((CardinalitySolutionStep.CardinalitySolutionStep_RuleCheck)matchStep).getRuleValueIndexes(), "");
				}
			}
			indexVectors = indexVectors2 = new ArrayList<>(indexVector2id2.keySet());
			Collections.sort(indexVectors2);
			int i = 0;
			for (@NonNull IndexVector indexVector : indexVectors2) {
				if (i > 0) {
					IndexVector prevIndexVector = indexVectors2.get(i-1);
					if (!(indexVector.compareTo(prevIndexVector) > 0)) {
						assert indexVector.compareTo(prevIndexVector) > 0;
					}
				}
				indexVector2id2.put(indexVector, "_" + i++);
			}
		}
		return indexVectors2;
	}

	protected @NonNull Iterable<@NonNull CardinalitySolutionStep> getMatchStepIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull CardinalitySolutionStep, @NonNull String> matchStep2id2 = matchStep2id;
		if (matchStep2id2 == null) {
			matchStep2id = matchStep2id2 = new HashMap<>();
		}
		for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : eClassValue.getSerializationRuleSegmentsLists()) {
				SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
				SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
				for (@NonNull CardinalitySolutionStep matchStep : serializationRuleAnalysis.getStaticRuleMatch().getSteps()) {
					matchStep2id2.put(matchStep, "");
				}
			}
		}
		List<@NonNull CardinalitySolutionStep> matchSteps = new ArrayList<>(matchStep2id2.keySet());
		Collections.sort(matchSteps, NameUtil.TO_STRING_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(matchSteps);
		int i = 0;
		for (@NonNull CardinalitySolutionStep matchStep : matchSteps) {
			matchStep2id2.put(matchStep, String.format(formatString, i++));
		}
		return matchSteps;
	}

	protected @NonNull String getMatchStepId(@NonNull CardinalitySolutionStep matchStep, boolean addQualifier) {
		assert matchStep2id != null;
		String id = matchStep2id.get(matchStep);
		assert id != null;
		return addQualifier ? "ms." + id : id;
	}

	protected @NonNull Iterable<@NonNull CardinalitySolution> getMatchTermIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull CardinalitySolution, @NonNull String> matchTerm2id2 = matchTerm2id;
		if (matchTerm2id2 == null) {
			matchTerm2id = matchTerm2id2 = new HashMap<>();
		}
		for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			for(@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : eClassValue.getSerializationRuleSegmentsLists()) {
				SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
				SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
				for(@NonNull CardinalitySolutionStep solutionStep : serializationRuleAnalysis.getStaticRuleMatch().getSteps()) {
					for (@NonNull CardinalitySolution matchTerm : solutionStep.getSolutionClosure()) {
						matchTerm2id2.put(matchTerm, "");
					}
				}
			}
		}
		List<@NonNull CardinalitySolution> matchTerms = new ArrayList<>(matchTerm2id2.keySet());
		Collections.sort(matchTerms, new Comparator<@NonNull CardinalitySolution>()
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
		String formatString = "_" + getDigitsFormatString(matchTerms);
		int i = 0;
		for (@NonNull CardinalitySolution matchTerm : matchTerms) {
			matchTerm2id2.put(matchTerm, String.format(formatString, i++));
		}
		return matchTerms;
	}

	protected @NonNull String getMatchTermId(@NonNull CardinalitySolution solutionStep, boolean addQualifier) {
		assert matchTerm2id != null;
		String id = matchTerm2id.get(solutionStep);
		assert id != null;
		return addQualifier ? "mt." + id : id;
	}

	protected @NonNull String getSegmentsId(@NonNull List<Segment> segments, boolean addQualifier) {
		assert segments2id != null;
		String id = segments2id.get(segments);
	//	assert id != null;
		return addQualifier ? "ss." + id : id;
	}

	protected @NonNull Iterable<@NonNull List<Segment>> getSegmentsIterable(@NonNull GrammarAnalysis grammarAnalysis) {
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

	protected @NonNull String getSegmentsListId(@NonNull String segmentsList, boolean addQualifier) {
		assert segmentsList2id != null;
		String id = segmentsList2id.get(segmentsList);
		assert id != null;
		return addQualifier ? "sl." + id : id;
	}

	protected @NonNull Iterable<@NonNull Segment[][]> getSegmentsListIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull SerializationRule, @NonNull String> serializationRule2id2 = serializationRule2id;
		assert serializationRule2id2 != null;
		Map<@NonNull String, @NonNull String> segmentsList2id2 = segmentsList2id;
		if (segmentsList2id2 == null) {
			segmentsList2id = segmentsList2id2 = new HashMap<>();
		}
		for (@NonNull SerializationRule serializationRule : serializationRule2id2.keySet()) {
			segmentsList2id2.put(getSegmentsListString(serializationRule.getStaticSegments()), "");
		}
		List<@NonNull String> segmentsLists = new ArrayList<>(segmentsList2id2.keySet());
		Collections.sort(segmentsLists);
		String formatString = "_" + getDigitsFormatString(segmentsLists);
		@NonNull List<@NonNull Segment[][]> segmentsListArray = new ArrayList<>();
		int i = 0;
		for (@NonNull String segmentsListString : segmentsLists) {
			segmentsList2id2.put(segmentsListString, String.format(formatString, i++));
			segmentsListArray.add(segmentsListString2segmentsList.get(segmentsListString));
		}
		return segmentsListArray;
	}

	protected @NonNull String getSegmentsListString(@NonNull Segment[][] segmentsList) {
		String string = segmentsList2string.get(segmentsList);
		if (string == null) {
			StringBuilder s= new StringBuilder();
			for (@NonNull Segment[] segments: segmentsList) {
				if (segments != null) {
					for (@NonNull Segment segment: segments) {
						s.append(segment.toString());
					}
				}
				s.append(",");
			}
			string = s.toString();
			segmentsList2string.put(segmentsList, string);
			segmentsListString2segmentsList.put(string, segmentsList);		// Any duplicate will do
		}
		return string;
	}

	@Override
	protected TypeReference getSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getSerializerBasePackage(final Grammar grammar) {
		return xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".serializer";
	}

	protected @NonNull String getSerializationStepId(@NonNull RTSerializationStep step, boolean addQualifier) {
		assert serializationStep2id != null;
		String id = serializationStep2id.get(step);
		assert id != null;
		return addQualifier ? "st." + id : id;
	}



//	protected @NonNull Iterable<@NonNull SerializationRuleAnalysis> getSerializationRuleAnalysisIterable(@NonNull GrammarAnalysis grammarAnalysis) {
//		assert serializationRuleAnalyses != null;
//		return serializationRuleAnalyses;
//	}

	protected @NonNull Iterable<@NonNull SerializationRuleAnalysis> getSerializationRuleAnalysisIterable(@NonNull GrammarAnalysis grammarAnalysis, int page) {
		assert serializationRuleAnalyses != null;
		int size = serializationRuleAnalyses.size();
		int maxPage = getSerializationRulePage(size);
		int firstIndex = RULES_PER_PAGE * page;
		assert serializationRuleAnalyses != null;
		return serializationRuleAnalyses.subList(firstIndex, Math.min(firstIndex+RULES_PER_PAGE, size));
	}

	protected @NonNull String getSerializationRuleId(@NonNull SerializationRule serializationRule, boolean addQualifier) {
		assert serializationRule2id != null;
		String id = serializationRule2id.get(serializationRule);
	//	System.out.println("?? " + NameUtil.debugSimpleName(serializationRule) + " => " + id  + " : " + serializationRule.toRuleString());
		assert id != null;
		if (addQualifier) {
			return id;
		}
		int index = id.indexOf('.');
		return index >= 0 ? id.substring(index+1) : id;
	}

	protected @NonNull Iterable<@NonNull SerializationRule> getSerializationRulesIterable(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRuleValue parserRuleValue) {
		List<@NonNull SerializationRule> serializationRules = Lists.newArrayList(grammarAnalysis.getSerializationRules(parserRuleValue));
		Collections.sort(serializationRules, NameUtil.TO_STRING_COMPARATOR);	// XXX ?? Lowest slot usage first
		return serializationRules;
	}

	private int getSerializationRulePage(int serializationRuleIndex) {
		return serializationRuleIndex / RULES_PER_PAGE;
	}

	protected @NonNull Iterable<@NonNull Integer> getSerializationRulePageIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		assert serializationRuleAnalyses != null;
		int maxPage = getSerializationRulePages(serializationRuleAnalyses.size());
		List<@NonNull Integer> pages = new ArrayList<>();
		for (int i = 0; i < maxPage; i++) {
			pages.add(i);
		}
		return pages;
	}

	private int getSerializationRulePages(int serializationRuleIndex) {
		return (serializationRuleIndex + RULES_PER_PAGE - 1) / RULES_PER_PAGE;
	}

	protected @NonNull Iterable<@NonNull RTSerializationStep> getSerializationStepIterable(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull RTSerializationStep, @NonNull String> serializationStep2id2 = serializationStep2id;
		if (serializationStep2id2 == null) {
			serializationStep2id = serializationStep2id2 = new HashMap<>();
		}
		for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
			for(@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : eClassValue.getSerializationRuleSegmentsLists()) {
				SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
				for(@NonNull RTSerializationStep serializationStep : serializationRule.getSerializationSteps()) {
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

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected void initAnalysisProviderContent(@NonNull GrammarAnalysis grammarAnalysis) {
		getEnumValueIterable(grammarAnalysis);
		getSegmentsIterable(grammarAnalysis);
		getSerializationStepIterable(grammarAnalysis);
		getMatchTermIterable(grammarAnalysis);
		getMatchStepIterable(grammarAnalysis);
		initGrammarRuleValues(grammarAnalysis);
		getEClassIterable(grammarAnalysis);
		initSerializationRules(grammarAnalysis);
	}

	protected void initGrammarRuleValues(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull AbstractRuleValue, @NonNull String> grammarRuleValue2id2 = grammarRuleValue2id;
		Map<@NonNull Integer, @NonNull String> grammarRuleValueIndex2ruleName2 = grammarRuleValueIndex2ruleName;
		if (grammarRuleValue2id2 == null) {
			grammarRuleValue2id = grammarRuleValue2id2 = new HashMap<>();
		}
		if (grammarRuleValueIndex2ruleName2 == null) {
			grammarRuleValueIndex2ruleName = grammarRuleValueIndex2ruleName2 = new HashMap<>();
		}
		for (@NonNull AbstractRuleAnalysis grammarRuleAnalysis : grammarAnalysis.getRuleAnalyses()) {
		//	if (grammarRuleAnalysis instanceof ParserRuleAnalysis) {
			grammarRuleValue2id2.put(grammarRuleAnalysis.getRuleValue(), "");
			grammarRuleValueIndex2ruleName2.put(grammarRuleAnalysis.getRuleValue().getIndex(), grammarRuleAnalysis.getRuleName());
		//	}
		}
		List<@NonNull AbstractRuleValue> grammarRuleValues = new ArrayList<>(grammarRuleValue2id2.keySet());
		Collections.sort(grammarRuleValues, NameUtil.NAMEABLE_COMPARATOR);
		String formatString = "_" + getDigitsFormatString(grammarRuleValues);
		int i = 0;
		for (@NonNull AbstractRuleValue grammarRuleValue : grammarRuleValues) {
			grammarRuleValue2id2.put(grammarRuleValue, String.format(formatString, i++));
		}
	}

	protected void initSerializationRules(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull SerializationRule, @NonNull String> serializationRule2id2 = serializationRule2id;
		if (serializationRule2id2 == null) {
			Set<@NonNull SerializationRuleAnalysis> serializationRuleAnalysesSet = new HashSet<>();
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : grammarAnalysis.getRuleAnalyses()) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					for (@NonNull SerializationRuleAnalysis serializationRule : ((ParserRuleAnalysis)ruleAnalysis).getSerializationRuleAnalyses()) {
					//	System.out.println(NameUtil.debugSimpleName(serializationRule) + " => " + NameUtil.debugSimpleName(serializationRule.getSerializationRuleAnalysis()) + " => " + NameUtil.debugSimpleName(serializationRule.getSerializationRuleAnalysis().getRuntime()) + " : " + serializationRule.toString());
						serializationRuleAnalysesSet.add(serializationRule);
					}
				}
			}
			List<@NonNull SerializationRuleAnalysis> serializationRuleAnalysesList = new ArrayList<>(serializationRuleAnalysesSet);
			Collections.sort(serializationRuleAnalysesList, NameUtil.NAMEABLE_COMPARATOR);
			serializationRule2id = serializationRule2id2 = new HashMap<>();
			String formatString = "sr" + getDigitsFormatString(getSerializationRulePage(serializationRuleAnalysesSet.size())) + "._" + getDigitsFormatString(serializationRuleAnalysesList);
			int i = 0;
			for (@NonNull SerializationRuleAnalysis serializationRule : serializationRuleAnalysesList) {
				serializationRule2id2.put(serializationRule.getRuntime(), String.format(formatString, getSerializationRulePage(i), i));
				i++;
			}
			this.serializationRuleAnalyses = serializationRuleAnalysesList;
		}
	}

	protected @NonNull Integer @NonNull [] integersIterable(int size) {
		@NonNull Integer @NonNull [] integers = new @NonNull Integer[size];
		for (int i = 0; i < size; i++) {
			integers[i] = i;
		}
		return integers;
	}

	//
	//	Xtend does not support annotations so we need to manually format x.y.z @NonNull T
	//
	protected @NonNull String newTypeReference(@NonNull Class<?> referredClass) {
		return newTypeReference(referredClass.getName());
	}
	protected @NonNull String newTypeReference(@Nullable String referredClassName) {
		if (referredClassName == null) {
			return "";			// Shouldn't happen
		}
		int index = referredClassName.lastIndexOf('$');
		if (index >= 0) {
			referredClassNames.add(referredClassName);
//			referredClassNames.add(referredClassName.substring(0, index));
//			index = referredClassName.lastIndexOf('.');
			return referredClassName.substring(index+1);
		}
		else {
			referredClassNames.add(referredClassName);
			index = referredClassName.lastIndexOf('.');
			return referredClassName.substring(index+1);
		}
	}
}
