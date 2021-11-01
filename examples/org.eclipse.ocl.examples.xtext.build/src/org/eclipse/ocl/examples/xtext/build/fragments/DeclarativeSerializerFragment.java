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
package org.eclipse.ocl.examples.xtext.build.fragments;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.AbstractRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.ParserRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.clones.DebugTimestamp;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsStandaloneSetup;
import org.eclipse.ocl.examples.xtext.serializer.AbstractSerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.DeclarativeFormatter;
import org.eclipse.ocl.examples.xtext.serializer.DeclarativeSerializer;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue.EReference_TargetGrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEReferenceSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMetaData;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationAttribute;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationFeature;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationReference;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAbstractFeature;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignKeyword;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssigns;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepKeyword;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorLanguage;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.serializer.SerializerFragment2;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The DeclarativeSerializerFragment collaborates with the DeclarativeFormatterFragment to replace the
 * backtracking serializer and the Xtend-dependent formatting specifiation approach of the 'new infrastructure'
 * by a largely statically determined serializer and declarative idiom-based formatter.
 */
public abstract class DeclarativeSerializerFragment extends SerializerFragment2
{
	@Inject CodeConfig codeConfig;

	protected final class DeclarativeSerializerFragmentDiagnosticStringBuilder extends DiagnosticStringBuilder
	{
		@Override
		public void appendRuleName(int ruleValueIndex) {
			assert grammarRuleValueIndex2ruleName != null;
			appendObject(grammarRuleValueIndex2ruleName.get(ruleValueIndex));
		}
	}

	protected static final class SerializationStepComparator extends SerializationUtils.ToStringComparator<@NonNull SerializationStep>
	{
		protected final @NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id;
		private @NonNull Map<@NonNull SerializationStep, @NonNull String> step2key = new HashMap<>();
		private @NonNull Map<@NonNull String, @NonNull Object> key2indexOrIndexes = new HashMap<>();

		public SerializationStepComparator(@NonNull List<@NonNull SerializationStep> serializationStepList, @NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id) {
			this.serializationSegments2id = serializationSegments2id;
			int index = 0;
			for (@NonNull SerializationStep step : serializationStepList) {
				String key = step.getGlobalSortKey(serializationSegments2id);
				step2key.put(step, key);
				Object indexOrIndexes = SerializationUtils.maybeNull(key2indexOrIndexes.get(key));
				if (indexOrIndexes == null) {
					key2indexOrIndexes.put(key, index);
				}
				else if (!(indexOrIndexes instanceof List)) {
					List<@NonNull Integer> indexes = new ArrayList<>();
					key2indexOrIndexes.put(key, indexes);
					indexes.add((Integer)indexOrIndexes);
					indexes.add(index);
				}
				else {
					@SuppressWarnings("unchecked")
					List<@NonNull Integer> indexes = (List<@NonNull Integer>)indexOrIndexes;
					indexes.add(index);
				}
				index++;
			}
		}

		@Override
		public int compare(@NonNull SerializationStep s1, @NonNull SerializationStep s2) {
			String k1 = SerializationUtils.nonNullState(step2key.get(s1));
			String k2 = SerializationUtils.nonNullState(step2key.get(s2));
			Class<?> c1 = SerializationUtils.nonNullState(key2indexOrIndexes.get(k1)).getClass();
			Class<?> c2 = SerializationUtils.nonNullState(key2indexOrIndexes.get(k2)).getClass();
			if (c1 != c2) {
				return c1 == Integer.class ? -1 : 1;
			}
			int comparison = k1.compareTo(k2);
			if (comparison != 0) {
				return comparison;
			}
			int r1 = -999;
			int r2 = -999;
			if (s1 instanceof SerializationStepSequence) {
				r1 = ((SerializationStepSequence)s1).getStepsRange();
			}
			if (s2 instanceof SerializationStepSequence) {
				r2 = ((SerializationStepSequence)s2).getStepsRange();
			}
			if (r1 != r2) {
				return r1 - r2;
			}
			return super.compare(s1, s2);
		}

		public boolean isGloballyConsistent(@NonNull SerializationStep step) {
			return key2indexOrIndexes.get(step2key.get(step)) instanceof Integer;
		}
	}

	public static DebugTimestamp timestamp = new DebugTimestamp("DeclarativeSerializerFragment")
	{
		@Override
		protected void doLog(String message) {}
	};
	private static final Logger LOG = Logger.getLogger(DeclarativeSerializerFragment.class);
	@SuppressWarnings("null")
	private static final @NonNull List<@Nullable Integer> UNPAGED_PAGE_NUMBER_LIST = Collections.singletonList(null);

	// Following are empirically set to comfortably avoid 64k limits.
	public static int ECLASS_VALUES_PER_PAGE = 128;
	public static int ENUM_VALUES_PER_PAGE = 512;
	public static int GRAMMAR_RULE_VALUES_PER_PAGE = 256;
	public static int GRAMMAR_RULE_VECTORS_PER_PAGE = 512;
	public static int MATCH_STEPS_PER_PAGE = 512;
	public static int MATCH_TERMS_PER_PAGE = 512;
	public static int SERIALIZATION_RULES_PER_PAGE = 64;
	public static int SERIALIZATION_SEGMENTS_PER_PAGE = 512;
	public static int SERIALIZATION_STEPS_PER_PAGE = 512;

	@Inject
	private XtextGeneratorNaming xtextGeneratorNaming;

	@Inject
	private  FileAccessFactory fileAccessFactory;

	@Inject
	private @NonNull Provider<ResourceSet> resourceSetProvider;

	private @Nullable GrammarAnalysis grammarAnalysis;
	private @NonNull Set<@NonNull String> referredClassNames = new HashSet<>();
	private @Nullable GenModelHelper genModelHelper = null;

	private @Nullable Map<@NonNull EClass, @Nullable Integer> eClass2id = null;
	private @Nullable List<@NonNull EClass> eClassList = null;

	private @Nullable Map<@NonNull EnumerationValue, @Nullable Integer> enumerationValue2id = null;
	private @Nullable List<@NonNull EnumerationValue> enumerationValuesList = null;

	private @Nullable Map<@NonNull GrammarRuleValue, @Nullable Integer> grammarRuleValue2id = null;
	private @Nullable Map<@NonNull Integer, @NonNull String> grammarRuleValueIndex2ruleName = null;
	private @Nullable List<@NonNull GrammarRuleValue> grammarRuleValueList = null;

	private @Nullable Map<@NonNull GrammarRuleVector, @Nullable Integer> grammarRuleVector2id = null;
	private @Nullable List<@NonNull GrammarRuleVector> grammarRuleVectors = null;

	private @Nullable Map<@NonNull SerializationMatchStep, @Nullable Integer> matchStep2id = null;
	private @Nullable List<@NonNull SerializationMatchStep> matchStepList = null;

	private @Nullable Map<@NonNull SerializationMatchTerm, @Nullable Integer> matchTerm2id = null;
	private @Nullable List<@NonNull SerializationMatchTerm> matchTermList = null;

	private @Nullable List<@NonNull List<@NonNull SerializationSegment>> serializationSegmentsList = null;
	private @Nullable Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id = null;

	private @Nullable Map<@NonNull SerializationRule, @Nullable Integer> serializationRule2id = null;
	private @Nullable List<@NonNull SerializationRuleAnalysis> serializationRuleAnalysisList = null;

	private @Nullable Map<@NonNull SerializationStep, @Nullable Integer> serializationStep2id = null;
	private @Nullable List<@NonNull SerializationStep> serializationStepList = null;
	private int firstGlobalSerializationStepAssignmentIndex = -1;
	private int firstGlobalSerializationStepLiteralIndex = -1;
	protected int firstStepLiteralIndex;
	private int lastGlobalSerializationStepAssignmentIndex = -1;
	private int lastGlobalSerializationStepLiteralIndex = -1;

	protected abstract StringConcatenationClient doGetSerializationMetaDataContent(@NonNull GrammarAnalysis grammarAnalysis);

	protected void doGenerateAnalysisStubFile() {
		JavaFileAccess javaFile = this.doGetAnalysisStubFile();
		timestamp.log("generate analysis stub");
		if (javaFile != null) {
			javaFile.setMarkedAsGenerated(true);		// FIXME There must be a smarter way to force output
			javaFile.writeTo(this.getProjectConfig().getRuntime().getSrcGen());
		}
	}

/*	protected void doGenerateIdiomsStubFile() {
		TextFileAccess idiomsFile = this.doGetIdiomsStubFile();
		timestamp.log("generate idioms stub");
		if (idiomsFile != null) {
		//	idiomsFile.setMarkedAsGenerated(true);		// FIXME There must be a smarter way to force output
			idiomsFile.writeTo(this.getProjectConfig().getRuntime().getSrc());
		}
	} */

	protected @Nullable JavaFileAccess doGetAnalysisStubFile() {
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
		TypeReference serializationMetaDataStub = getSerializationMetaDataClass(grammar);
		JavaFileAccess javaFile = fileAccessFactory.createJavaFile(serializationMetaDataStub);
		javaFile.setResourceSet(language.getResourceSet());
		GrammarAnalysis grammarAnalysis = getGrammarAnalysis();
		javaFile.setContent(doGetSerializationMetaDataContent(grammarAnalysis));
		return javaFile;
	}

	protected void doGetIdiomsStubFile() throws IOException {
		if (!isGenerateStub()) {
			return;
		}
		IXtextGeneratorLanguage language = getLanguage();
		Grammar grammar = getGrammar();
		assert grammar != null;
		URI xtextURI = grammar.eResource().getURI();
		URI idiomsURI = xtextURI.trimFileExtension().appendFileExtension("idioms");
		ResourceSet resourceSet = language.getResourceSet();
		URIConverter uriConverter = resourceSet.getURIConverter();
		boolean idiomsExists = uriConverter.exists(idiomsURI, null);
		if (idiomsExists) {
			return;
		}
		OutputStream outputStream = uriConverter.createOutputStream(idiomsURI);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream);
		writer.append(codeConfig.getFileHeader());
		writer.append("\nmodel " + grammar.getName() + "\n");
		Iterable<@NonNull Grammar> usedGrammars = SerializationUtils.getUsedGrammars(grammar);
		if (!Iterables.isEmpty(usedGrammars)) {
			writer.append("\n/** Inherit idioms from inherited grammars. */\n");
			for (@NonNull Grammar usedGrammar : usedGrammars) {
				writer.append("// with \"platform:/resource/... " + usedGrammar.eResource().getURI().toString() + "...idioms\"\n");
			}
		}

		writer.append("\n/** Import any Ecore metamodels used by EClass or assignment-location. */\n");
		writer.append("// import \"platform:/resource/...ecore#/\" as mymodel\n");

		writer.append("\n/** Mix-in idiom weaving standard Xtext comments everywhere visible. */\n");
		writer.append("mixin idiom COMMENTS at final do pre-comment value post-comment;\n");

		writer.append("\n/** Idiom indenting text between braces */\n");
		writer.append("idiom BRACES {\n");
		writer.append("\tat \"{\" do soft-space value push soft-new-line;\n");
		writer.append("\tat \"}\" do pop soft-space value soft-new-line;\n");
		writer.append("}\n");

		writer.append("\n/** Returned EClass-Localized idiom overiding a subsequent global idiom imposing spacing around colons */\n");
		writer.append("//idiom COLON for mymodel::MyClass at \":\" do soft-space value soft-space;\n");

		writer.append("\n/** Rule-Localized idiom overiding a subsequent global idiom imposing lines around colons */\n");
		writer.append("//idiom COLON in MyRule at \":\" do soft-new-line value soft-new-line;\n");

		writer.append("\n/** Idiom suppressing spaces before all colons */\n");
		writer.append("idiom COLON at \":\" do no-space value soft-space;\n");

		writer.append("\n/** Idiom separating repeated XXX assignments by a blank line */\n");
		writer.append("//idiom XXX_SPACING at each assignment mymodel::MyClass::XXX do half-new-line value half-new-line;\n");

		writer.append("\n/** Idiom surrounding repeated YYY assignments by a blank line */\n");
		writer.append("//idiom YYY_SPACING at all assignment mymodel::MyClass::YYY do new-line soft-new-line value soft-new-line;\n");

		writer.append("\n/** Default idiom imposing spacing for visible leaf terms must be last */\n");
		writer.append("idiom FINAL at final do soft-space value soft-space;\n");
		writer.close();
	}

	protected @NonNull String emitCalledRule(@NonNull CrossReference crossReference) {
		RuleCall ruleCall = (RuleCall)SerializationUtils.getTerminal(crossReference);
		AbstractRule abstractRule = SerializationUtils.getRule(ruleCall);
		AbstractRuleAnalysis ruleAnalysis = getGrammarAnalysis().getRuleAnalysis(abstractRule);
		return ruleAnalysis.getName();
	}

	protected @NonNull String emitLiteral(@NonNull EClassifier eClassifier) {
		return newTypeReference(getGenModelHelper().getQualifiedPackageInterfaceName(SerializationUtils.getEPackage(eClassifier))) + ".Literals." + getGenModelHelper().getLiteralName(eClassifier);
	}

	protected @NonNull String emitLiteral(@NonNull EStructuralFeature eStructuralFeature) {
		return newTypeReference(getGenModelHelper().getQualifiedPackageInterfaceName(SerializationUtils.getEPackage(SerializationUtils.getEContainingClass(eStructuralFeature)))) + ".Literals." + getGenModelHelper().getEcoreLiteralName(eStructuralFeature);
	}

	protected @NonNull String emitGrammarCardinality(@NonNull GrammarCardinality grammarCardinality) {
		if (grammarCardinality.equals(GrammarCardinality.ONE)) {
			return newTypeReference(GrammarCardinality.class) + ".ONE";
		}
		else if (grammarCardinality.equals(GrammarCardinality.ZERO_OR_ONE)) {
			return newTypeReference(GrammarCardinality.class) + ".ZERO_OR_ONE";
		}
		else if (grammarCardinality.equals(GrammarCardinality.ZERO_OR_MORE)) {
			return newTypeReference(GrammarCardinality.class) + ".ZERO_OR_MORE";
		}
		else if (grammarCardinality.equals(GrammarCardinality.ONE_OR_MORE)) {
			return newTypeReference(GrammarCardinality.class) + ".ONE_OR_MORE";
		}
		return grammarCardinality.toString();
	}

	protected @NonNull String emitQualifiedLiteral(@NonNull EPackage ePackage) {
		return SerializationUtils.nonNullState(getGenModelHelper().getQualifiedPackageInterfaceName(ePackage));
	}

	private void gatherFormattingTexts(@NonNull AbstractElement grammarElement, @NonNull List<@NonNull String> formattingTexts) {
		StringBuilder s = new StringBuilder();
		if (grammarElement instanceof Assignment) {
			Assignment assignment = (Assignment)grammarElement;
			s.append(assignment.getFeature());
			s.append(assignment.getOperator());
			AbstractElement terminal = assignment.getTerminal();
			assert terminal != null;
			gatherFormattingTexts(terminal, s);
		}
		else if (grammarElement instanceof Action) {
			s.append("{");
			s.append(((Action)grammarElement).getType().getClassifier().getName());
			s.append("}");
		}
		else if (grammarElement instanceof Group) {
			s.append("Group");
		}
		else if (grammarElement instanceof Alternatives) {
			s.append("Alternatives");
		}
		else {
			gatherFormattingTexts(grammarElement, s);
		}
		String cardinality = grammarElement.getCardinality();
		if (cardinality != null) {
			s.append(cardinality);
		}
		String string = s.toString();
		assert string != null;
		formattingTexts.add(string);
		if (grammarElement instanceof CompoundElement) {
			for (AbstractElement nestedElement : ((CompoundElement)grammarElement).getElements()) {
				assert nestedElement != null;
				gatherFormattingTexts(nestedElement, formattingTexts);
			}
		}
	}

	private void gatherFormattingTexts(@NonNull AbstractElement grammarElement, @NonNull StringBuilder s) {
		if (grammarElement instanceof Keyword) {
			s.append("\"");
			s.append(Strings.convertToJavaString(((Keyword)grammarElement).getValue()));
			s.append("\"");
		}
		else if (grammarElement instanceof CrossReference) {
			CrossReference crossReference = (CrossReference)grammarElement;
			AbstractElement terminal = crossReference.getTerminal();
			assert terminal != null;
			gatherFormattingTexts(terminal, s);
			String cardinality = terminal.getCardinality();
			if (cardinality != null) {
				s.append(cardinality);
			}
		}
		else if (grammarElement instanceof RuleCall) {
			RuleCall ruleCall = (RuleCall)grammarElement;
			s.append(ruleCall.getRule().getName());
		}
		else if (grammarElement instanceof Alternatives) {
			s.append("(");
			boolean isFirst = true;
			for (AbstractElement nestedElement : ((Alternatives)grammarElement).getElements()) {
				assert nestedElement != null;
				if (!isFirst) {
					s.append("|");
				}
				gatherFormattingTexts(nestedElement, s);
				String cardinality = grammarElement.getCardinality();
				if (cardinality != null) {
					s.append(cardinality);
				}
				isFirst = false;
			}
			s.append(")");
		}
	//	else {
	//		s.append(grammarElement.getClass().getSimpleName());
	//	}
	}

	@Override
	public void generate() {
		try {
			timestamp.log("generate start");
			IdiomsStandaloneSetup.doSetup();
			Grammar grammar = this.getGrammar();
			timestamp.log("generate gotGrammar");
			GuiceModuleAccess.BindingFactory bindingFactory = new GuiceModuleAccess.BindingFactory();
			GuiceModuleAccess runtimeGenModule = this.getLanguage().getRuntimeGenModule();
			bindingFactory.addTypeToType(TypeReference.typeRef(INodeModelFormatter.class), TypeReference.typeRef(DeclarativeFormatter.class)).contributeTo(runtimeGenModule);
			bindingFactory.addTypeToType(TypeReference.typeRef(ISerializer.class), TypeReference.typeRef(DeclarativeSerializer.class)).contributeTo(runtimeGenModule);
	//		bindingFactory.addTypeToType(TypeReference.typeRef(AbstractSerializationMetaData.class), getSerializationMetaDataClass(grammar)).contributeTo(runtimeGenModule);
			bindingFactory.addTypeToType(TypeReference.typeRef(SerializationMetaData.Provider.class), getSerializationMetaDataProviderClass(grammar)).contributeTo(runtimeGenModule);
			timestamp.log("generate idioms stub");
			doGetIdiomsStubFile();
			timestamp.log("generate analysis stub");
			doGenerateAnalysisStubFile();
			timestamp.log("generate end");
		}
		catch (AssertionError e) {
			LOG.error("Assertion failed", e);
		}
		catch (IOException e) {
			LOG.error("File access failed", e);
		}
		catch (Exception e) {
			LOG.error("Failure", e);
			throw e;
		}
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

	protected int getEClassCount() {
		assert eClass2id != null;
		return eClass2id.size();
	}

	protected int getEClassIndex(@NonNull EClass eClass) {
		assert eClass2id != null;
		Integer id = eClass2id.get(eClass);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull EClass> getEClassList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull EClass> eClassList2 = eClassList;
		if (eClassList2 == null) {
			timestamp.log("getEClassList start");
			Map<@NonNull EClass, @Nullable Integer> eClass2id2 = eClass2id;
			if (eClass2id2 == null) {
				eClass2id = eClass2id2 = new HashMap<>();
			}
			for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
				eClass2id2.put(eClassValue.getEClass(), null);
			}
			eClassList2 = eClassList = new ArrayList<>(eClass2id2.keySet());
			Collections.sort(eClassList2, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
			int i = 0;
			for (@NonNull EClass eClass : eClassList2) {
				eClass2id2.put(eClass, i++);
			}
		}
		return eClassList2;
	}

	protected int getEnumerationValueCount() {
		assert enumerationValue2id != null;
		return enumerationValue2id.size();
	}

	protected int getEnumerationValueIndex(@NonNull EnumerationValue enumerationValue) {
		if (enumerationValue.isNull()) {
			return -1;
		}
		assert enumerationValue2id != null;
		Integer id = enumerationValue2id.get(enumerationValue);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull EnumerationValue> getEnumerationValueList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull EnumerationValue> enumerationValuesList2 = enumerationValuesList;
		if (enumerationValuesList2 == null) {
			timestamp.log("getEnumerationValueList start");
			Map<@NonNull EnumerationValue, @Nullable Integer> enumerationValue2id2 = enumerationValue2id;
			if (enumerationValue2id2 == null) {
				enumerationValue2id = enumerationValue2id2 = new HashMap<>();
			}
		//	for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
				for (@NonNull EnumerationValue enumerationValue : grammarAnalysis.getEnumerationValues()) {
					enumerationValue2id2.put(enumerationValue, null);
		//		}
			}
			for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
				for (@NonNull SerializationRule serializationRule : eClassValue.getSerializationRules()) {
					SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
					for (@NonNull SerializationMatchStep solutionStep : serializationRuleAnalysis.getSerializationMatchSteps()) {
						for (@NonNull SerializationMatchTerm solution : solutionStep.getMatchTermClosure()) {
							if (solution instanceof SerializationMatchTermEAttributeSize) {
								enumerationValue2id2.put(((SerializationMatchTermEAttributeSize)solution).getEnumerationValue(), null);
							}
						}
					}
					for(@NonNull SerializationStep serializationStep : serializationRule.getSerializationSteps()) {
						if (serializationStep instanceof SerializationStepAssignKeyword) {
							enumerationValue2id2.put(((SerializationStepAssignKeyword)serializationStep).getEnumerationValue(), null);
						}
					}
					@NonNull SerializationAttribute[] serializationAttributes = serializationRuleAnalysis.basicGetSerializationAttributes();
					if (serializationAttributes != null) {
						for (@NonNull SerializationAttribute serializationAttribute : serializationAttributes) {
							for (@NonNull EnumerationValue enumerationValue : serializationAttribute.getEnumerationValues()) {
								enumerationValue2id2.put(enumerationValue, null);
							}
						}
					}
				}
			}
			enumerationValuesList2 = enumerationValuesList = new ArrayList<>(enumerationValue2id2.keySet());
			Collections.sort(enumerationValuesList2, SerializationUtils.NAMEABLE_COMPARATOR);
			int i = 0;
			for (@NonNull EnumerationValue enumerationValue : enumerationValuesList2) {
				enumerationValue2id2.put(enumerationValue, i++);
			}
		}
		return enumerationValuesList2;
	}

	protected @NonNull List<@NonNull EReference_TargetGrammarRuleVector> getEReferenceRuleIndexesIterable(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EClass eClass) {
		List<@NonNull EReference_TargetGrammarRuleVector> eReferenceRuleIndexes = Lists.newArrayList(grammarAnalysis.getEReferenceRuleIndexes(eClass));
		assert eReferenceRuleIndexes != null;
		Collections.sort(eReferenceRuleIndexes, SerializationUtils.NAMEABLE_COMPARATOR);
		return eReferenceRuleIndexes;
	}

	protected int getFirstGlobalSerializationStepAssignmentIndex() {
		assert serializationStep2id != null;
		return firstGlobalSerializationStepAssignmentIndex;
	}

	protected int getFirstGlobalSerializationStepLiteralIndex() {
		assert serializationStep2id != null;
		return firstGlobalSerializationStepLiteralIndex;
	}

	protected @NonNull List<@NonNull List<@NonNull SerializationSegment>> getFormattingSegmentsInnerList(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRuleValue parserRuleValue) {
	//	timestamp.log("getSerializationStepList " +  parserRuleValue.getName() + " start");
		@NonNull SerializationSegment @NonNull [] @NonNull [] innerFormattingSegmentsArray = grammarAnalysis.getInnerFormattingSegments(parserRuleValue);
		List<@NonNull List<@NonNull SerializationSegment>> serializationSegmentsList = new ArrayList<>(innerFormattingSegmentsArray.length);
		for (@NonNull SerializationSegment @NonNull [] innerFormattingSegments : innerFormattingSegmentsArray) {
			serializationSegmentsList.add(SerializationUtils.nonNullState(Lists.newArrayList(innerFormattingSegments)));
		}
		return serializationSegmentsList;
	}

	protected @NonNull List<@NonNull List<@NonNull SerializationSegment>> getFormattingSegmentsOuterList(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRuleValue parserRuleValue) {
	//	timestamp.log("getSerializationStepList " +  parserRuleValue.getName() + " start");
		@NonNull SerializationSegment @NonNull [] @NonNull [] outerFormattingSegmentsArray = grammarAnalysis.getOuterFormattingSegments(parserRuleValue);
		List<@NonNull List<@NonNull SerializationSegment>> serializationSegmentsList = new ArrayList<>(outerFormattingSegmentsArray.length);
		for (@NonNull SerializationSegment @NonNull [] outerFormattingSegments : outerFormattingSegmentsArray) {
			serializationSegmentsList.add(SerializationUtils.nonNullState(Lists.newArrayList(outerFormattingSegments)));
		}
		return serializationSegmentsList;
	}

	protected @NonNull List<@NonNull String> getFormattingTexts(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRuleValue parserRuleValue) {
		@NonNull List<@NonNull String> formattingTexts = new ArrayList<>();
		AbstractElement alternatives = grammarAnalysis.getRuleAnalysis(parserRuleValue.getIndex()).getRule().getAlternatives();
		assert alternatives != null;
		gatherFormattingTexts(alternatives, formattingTexts);
		return formattingTexts;
	}

	private @NonNull GenModelHelper getGenModelHelper() {
		GenModelHelper genModelHelper2 = genModelHelper;
		if (genModelHelper2 == null) {
			ResourceSet resourceSet = resourceSetProvider.get();
			assert resourceSet != null;
			genModelHelper = genModelHelper2 = new GenModelHelper(resourceSet /* new ResourceSetImpl() */);
		}
		return genModelHelper2;
	}

	protected @NonNull GrammarAnalysis getGrammarAnalysis() {
		GrammarAnalysis grammarAnalysis2 = grammarAnalysis;
		if (grammarAnalysis2 == null) {
			Grammar grammar = getGrammar();
			assert grammar != null;
			this.grammarAnalysis = grammarAnalysis2 = new GrammarAnalysis(grammar);
			timestamp.log("grammarAnalysis.analyze() start");
			grammarAnalysis2.analyze();
			timestamp.log("grammarAnalysis.analyze() end");
		}
		return grammarAnalysis2;
	}

	@Override
	protected String getGrammarConstraintsPath(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected @NonNull String getGrammarRuleName(@NonNull Integer grammarRuleValueIndex) {
		assert grammarRuleValueIndex2ruleName != null;
		String id = SerializationUtils.maybeNull(grammarRuleValueIndex2ruleName.get(grammarRuleValueIndex));
		assert id != null;
		return id;
	}

	protected int getGrammarRuleValueCount() {
		assert grammarRuleValue2id != null;
		return grammarRuleValue2id.size();
	}

	protected int getGrammarRuleValueIndex(@NonNull GrammarRuleValue grammarRuleValue) {
		assert grammarRuleValue2id != null;
		Integer id = grammarRuleValue2id.get(grammarRuleValue);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull GrammarRuleValue> getGrammarRuleValueList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull GrammarRuleValue> grammarRuleValueList2 = grammarRuleValueList;
		if (grammarRuleValueList2 == null) {
			timestamp.log("getGrammarRuleValueList start");
			Map<@NonNull GrammarRuleValue, @Nullable Integer> grammarRuleValue2id2 = grammarRuleValue2id;
			Map<@NonNull Integer, @NonNull String> grammarRuleValueIndex2ruleName2 = grammarRuleValueIndex2ruleName;
			if (grammarRuleValue2id2 == null) {
				grammarRuleValue2id = grammarRuleValue2id2 = new HashMap<>();
			}
			if (grammarRuleValueIndex2ruleName2 == null) {
				grammarRuleValueIndex2ruleName = grammarRuleValueIndex2ruleName2 = new HashMap<>();
			}
			for (@NonNull AbstractRuleAnalysis grammarRuleAnalysis : grammarAnalysis.getRuleAnalyses()) {
				grammarRuleValue2id2.put(grammarRuleAnalysis.getRuleValue(), null);
				grammarRuleValueIndex2ruleName2.put(grammarRuleAnalysis.getRuleValue().getIndex(), grammarRuleAnalysis.getName());
			}
			grammarRuleValueList2 = grammarRuleValueList = new ArrayList<>(grammarRuleValue2id2.keySet());
			Collections.sort(grammarRuleValueList2, SerializationUtils.NAMEABLE_COMPARATOR);
			int i = 0;
			for (@NonNull GrammarRuleValue grammarRuleValue : grammarRuleValueList2) {
				grammarRuleValue2id2.put(grammarRuleValue, i++);
			}
		}
		return grammarRuleValueList2;
	}

	protected int getGrammarRuleVectorCount() {
		assert grammarRuleVector2id != null;
		return grammarRuleVector2id.size();
	}

	protected int getGrammarRuleVectorIndex(@NonNull GrammarRuleVector grammarRuleVector) {
		assert grammarRuleVector2id != null;
		Integer id = grammarRuleVector2id.get(grammarRuleVector);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull GrammarRuleVector> getGrammarRuleVectorList(@NonNull GrammarAnalysis grammarAnalysis) {
		Map<@NonNull GrammarRuleVector, @Nullable Integer> grammarRuleVector2id2 = grammarRuleVector2id;
		List<@NonNull GrammarRuleVector> grammarRuleVectors2 = grammarRuleVectors;
		if ((grammarRuleVector2id2 == null) || (grammarRuleVectors2 == null)) {
			timestamp.log("getGrammarRuleVectorList start");
			grammarRuleVector2id = grammarRuleVector2id2 = new HashMap<>();
			for (@NonNull EClass eClass : getEClassList(grammarAnalysis)) {
				@NonNull EReference_TargetGrammarRuleVector[] eReferenceRuleIndexes = grammarAnalysis.basicGetEReferenceRuleIndexes(eClass);
				if (eReferenceRuleIndexes != null) {
					for (@NonNull EReference_TargetGrammarRuleVector eReferenceRuleIndex : eReferenceRuleIndexes) {
						GrammarRuleVector assignedTargetRuleValues = eReferenceRuleIndex.getTargetGrammarRuleVector();
						grammarRuleVector2id2.put(assignedTargetRuleValues, null);
					}
				}
			}
			for (@NonNull GrammarRuleValue grammarRuleValue : getGrammarRuleValueList(grammarAnalysis)) {
				if (grammarRuleValue instanceof ParserRuleValue) {
					GrammarRuleVector subParserRuleValueIndexes = ((ParserRuleValue)grammarRuleValue).getSubParserRuleValueIndexes();
					if (subParserRuleValueIndexes != null) {
						grammarRuleVector2id2.put(subParserRuleValueIndexes, null);
					}
				}
			}
			for (@NonNull SerializationMatchStep matchStep : getMatchStepList(grammarAnalysis)) {
				if (matchStep instanceof SerializationMatchStep.MatchStep_RuleCheck) {
					grammarRuleVector2id2.put(((SerializationMatchStep.MatchStep_RuleCheck)matchStep).getRuleValueIndexes(), null);
				}
			}
			for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
				for (@NonNull SerializationRule serializationRule : eClassValue.getSerializationRules()) {
					SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
					for (@NonNull SerializationMatchStep solutionStep : serializationRuleAnalysis.getSerializationMatchSteps()) {
						for (@NonNull SerializationMatchTerm solution : solutionStep.getMatchTermClosure()) {
							if (solution instanceof SerializationMatchTermEReferenceSize) {
								grammarRuleVector2id2.put(((SerializationMatchTermEReferenceSize)solution).getGrammarRuleVector(), null);
							}
						}
					}
					for (@NonNull SerializationStep serializationStep : serializationRule.getSerializationSteps()) {
						if (serializationStep instanceof SerializationStepAssigns) {
							int [] calledRuleIndexes = ((SerializationStepAssigns)serializationStep).getCalledRuleIndexes();
							grammarRuleVector2id2.put(new GrammarRuleVector(calledRuleIndexes), null);
						}
					}
					@NonNull
					SerializationFeature[] serializationFeatures = serializationRule.basicGetSerializationFeatures();
					if (serializationFeatures != null) {
						for (@NonNull SerializationFeature serializationFeature : serializationFeatures) {
							if (serializationFeature instanceof SerializationReference) {
								GrammarRuleVector grammarRuleVector = ((SerializationReference)serializationFeature).getTargetGrammarRuleVector();
							// FIXME	assert grammarRuleVector != null;
								if (grammarRuleVector != null) {
									grammarRuleVector2id2.put(grammarRuleVector, null);
								}
							}
						}
					}
				}
			}
			grammarRuleVectors = grammarRuleVectors2 = new ArrayList<>(grammarRuleVector2id2.keySet());
			Collections.sort(grammarRuleVectors2);
			int i = 0;
			for (@NonNull GrammarRuleVector grammarRuleVector : grammarRuleVectors2) {
				if (i > 0) {
					GrammarRuleVector prevGrammarRuleVector = SerializationUtils.nonNullState(grammarRuleVectors2.get(i-1));
					if (!(grammarRuleVector.compareTo(prevGrammarRuleVector) > 0)) {
						assert grammarRuleVector.compareTo(prevGrammarRuleVector) > 0;
					}
				}
				grammarRuleVector2id2.put(grammarRuleVector, i++);
			}
		}
		return grammarRuleVectors2;
	}

	protected @NonNull Iterable<@NonNull String> getImportedClassNameIterable() {
		List<@NonNull String> referredClassesList = new ArrayList<>(referredClassNames);
		Collections.sort(referredClassesList);

		return referredClassesList;
	}

	protected int getLastGlobalSerializationStepAssignmentIndex() {
		assert serializationStep2id != null;
		return lastGlobalSerializationStepAssignmentIndex;
	}

	protected int getLastGlobalSerializationStepLiteralIndex() {
		assert serializationStep2id != null;
		return lastGlobalSerializationStepLiteralIndex;
	}

	protected int getMatchStepCount() {
		assert matchStepList != null;
		return matchStepList.size();
	}

	protected int getMatchStepIndex(@NonNull SerializationMatchStep matchStep) {
		assert matchStep2id != null;
		Integer id = matchStep2id.get(matchStep);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull SerializationMatchStep> getMatchStepList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull SerializationMatchStep> matchStepList2 = matchStepList;
		if (matchStepList2 == null) {
			timestamp.log("getMatchStepList start");
			Map<@NonNull SerializationMatchStep, @Nullable Integer> matchStep2id2 = matchStep2id;
			if (matchStep2id2 == null) {
				matchStep2id = matchStep2id2 = new HashMap<>();
				for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
					for (@NonNull SerializationRule serializationRule : eClassValue.getSerializationRules()) {
						SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
						for (@NonNull SerializationMatchStep matchStep : serializationRuleAnalysis.getSerializationMatchSteps()) {
							matchStep2id2.put(matchStep, null);
						}
					}
				}
			}
			matchStepList = matchStepList2 = new ArrayList<>(matchStep2id2.keySet());
			Collections.sort(matchStepList2, new SerializationUtils.ToStringComparator<@NonNull SerializationMatchStep>()); // FIXME per-class so that that internally ENamed
			int i = 0;
			for (@NonNull SerializationMatchStep matchStep : matchStepList2) {
				matchStep2id2.put(matchStep, Integer.valueOf(i++));
			}
		}
		return matchStepList2;
	}

	protected int getMatchTermCount() {
		assert matchTermList != null;
		return matchTermList.size();
	}

	protected int getMatchTermIndex(@NonNull SerializationMatchTerm matchTerm) {
		assert matchTerm2id != null;
		Integer id = matchTerm2id.get(matchTerm);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull SerializationMatchTerm> getMatchTermList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull SerializationMatchTerm> matchTermList2 = matchTermList;
		if (matchTermList2 == null) {
			timestamp.log("getMatchTermList start");
			Map<@NonNull SerializationMatchTerm, @Nullable Integer> matchTerm2id2 = matchTerm2id;
			if (matchTerm2id2 == null) {
				assert matchTermList == null;
				matchTerm2id = matchTerm2id2 = new HashMap<>();
				for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
					for(@NonNull SerializationRule serializationRule : eClassValue.getSerializationRules()) {
						SerializationRuleAnalysis serializationRuleAnalysis = grammarAnalysis.getSerializationRuleAnalysis(serializationRule);
						for(@NonNull SerializationMatchStep solutionStep : serializationRuleAnalysis.getSerializationMatchSteps()) {
							for (@NonNull SerializationMatchTerm matchTerm : solutionStep.getMatchTermClosure()) {
								matchTerm2id2.put(matchTerm, null);
							}
						}
					}
				}
			}
			matchTermList = matchTermList2 = new ArrayList<>(matchTerm2id2.keySet());
			Collections.sort(matchTermList2, new Comparator<@NonNull SerializationMatchTerm>()
			{
				private final @NonNull Map<@NonNull Object, String> object2string = new HashMap<>();

				@Override
				public int compare(@NonNull SerializationMatchTerm o1, @NonNull SerializationMatchTerm o2) {
					int i1 = o1.getChildClosure().size();
					int i2 = o2.getChildClosure().size();
					if (i1 != i2) {
						return i1 - i2;
					}
					String s1 = getString(o1);
					String s2 = getString(o2);
					return SerializationUtils.safeCompareTo(s1, s2);
				}

				private String getString(@NonNull Object o) {
					String string = object2string.get(o);
					if (string == null) {
						string = o.toString();
						object2string.put(o, string);
					}
					return string;
				}
			});
			int i = 0;
			for (@NonNull SerializationMatchTerm matchTerm : matchTermList2) {
				matchTerm2id2.put(matchTerm, i++);
			}
		}
		return matchTermList2;
	}

	private static @Nullable String deduceMidfix(@NonNull String prefix, @NonNull String suffix) {
		for (int i = 0; i < prefix.length(); i++) {
			String midfix = prefix.substring(i);
			if (suffix.startsWith(midfix)) {
				StringBuilder s = new StringBuilder();
				for (int j = 0; j < i; j++) {
					s.append(" ");
				}
				s.append(midfix);
				return s.toString();
			}
		}
		return null;
	}


	protected List<@Nullable String> getMultipleLineCommentMidfixes(@NonNull Map<@NonNull String, @NonNull String> multipleLineCommentCharacterRanges) {
		List<@Nullable String> list = new ArrayList<>();
		for (Map.Entry<@NonNull String, @NonNull String> entry : multipleLineCommentCharacterRanges.entrySet()) {
			list.add(deduceMidfix(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	protected @NonNull Iterable<@NonNull Integer> getPageElementList(@Nullable Integer page, int size, int pageSize) {
		int pageStart;
		int pageEnd;
		if (page != null) {
			pageStart = page * pageSize;
			pageEnd = Math.min(pageStart + pageSize, size);
		}
		else {
			pageStart = 0;
			pageEnd = size;
		}
		List<@NonNull Integer> elements = new ArrayList<>(size);
		for (int i = pageStart; i < pageEnd; i++) {
			elements.add(i);
		}
		return elements;
	}

	protected @NonNull Iterable<@Nullable Integer> getPageNumberList(int size, int pageSize) {
		if (size > pageSize) {
			int pageCount = (size + pageSize - 1) / pageSize;
			List<@Nullable Integer> pages = new ArrayList<>(pageCount);
			for (int i = 0; i < pageCount; i++) {
				pages.add(i);
			}
			return pages;
		}
		else {
			return UNPAGED_PAGE_NUMBER_LIST;
		}
	}

	@Override
	protected TypeReference getSemanticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected TypeReference getSerializationMetaDataClass(Grammar grammar) {
		return new TypeReference(getSerializerBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "SerializationMetaData");
	}

	protected TypeReference getSerializationMetaDataProviderClass(Grammar grammar) {
		return new TypeReference(getSerializerBasePackage(grammar), GrammarUtil.getSimpleName(grammar) + "SerializationMetaData.Provider");
	}

	protected TypeReference getSerializationMetaDataSuperClass(Grammar grammar) {
		return new TypeReference(AbstractSerializationMetaData.class);
	}

/*	protected @NonNull SerializationRuleAnalysis getSerializationRuleAnalysis(int ruleIndex) {
		assert serializationRuleAnalysisList != null;
		SerializationRuleAnalysis serializationRuleAnalysis = serializationRuleAnalysisList.get(ruleIndex);
		assert serializationRuleAnalysis != null;
		return serializationRuleAnalysis;
	} */

	protected @NonNull List<@NonNull SerializationRuleAnalysis> getSerializationRuleAnalysisList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull SerializationRuleAnalysis> serializationRuleAnalysisList2 = serializationRuleAnalysisList;
		if (serializationRuleAnalysisList2 == null) {
			timestamp.log("getSerializationRuleAnalysisList start");
			Set<@NonNull SerializationRuleAnalysis> serializationRuleAnalysesSet = new HashSet<>();
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : grammarAnalysis.getRuleAnalyses()) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					for (@NonNull SerializationRuleAnalysis serializationRule : ((ParserRuleAnalysis)ruleAnalysis).getSerializationRuleAnalyses()) {
					//	System.out.println(SerializationUtils.debugSimpleName(serializationRule) + " => " + SerializationUtils.debugSimpleName(serializationRule.getSerializationRuleAnalysis()) + " => " + SerializationUtils.debugSimpleName(serializationRule.getSerializationRuleAnalysis().getRuntime()) + " : " + serializationRule.toString());
						serializationRuleAnalysesSet.add(serializationRule);
					}
				}
			}
			serializationRuleAnalysisList2 = serializationRuleAnalysisList = new ArrayList<>(serializationRuleAnalysesSet);
			Collections.sort(serializationRuleAnalysisList2, new SerializationUtils.ToStringComparator<@NonNull SerializationRuleAnalysis>());
			Map<@NonNull SerializationRule, @Nullable Integer> serializationRule2id2 = serializationRule2id;
			serializationRule2id = serializationRule2id2 = new HashMap<>();
			int i = 0;
			for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : serializationRuleAnalysisList2) {
				serializationRule2id2.put(serializationRuleAnalysis.getSerializationRule(), i++);
			}
		}
		return serializationRuleAnalysisList2;
	}

	protected int getSerializationRuleCount() {
		assert serializationRuleAnalysisList != null;
		return serializationRuleAnalysisList.size();
	}

	protected int getSerializationRuleIndex(@NonNull SerializationRule serializationRule) {
		assert serializationRule2id != null;
		Integer id = serializationRule2id.get(serializationRule);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull SerializationRule> getSerializationRuleList(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRuleValue parserRuleValue) {
	//	timestamp.log("getSerializationRulesList " +  parserRuleValue.getName() + " start");
		List<@NonNull SerializationRule> serializationRules = Lists.newArrayList(grammarAnalysis.getSerializationRules(parserRuleValue));
		assert serializationRules != null;
		Collections.sort(serializationRules, new SerializationUtils.ToStringComparator<@NonNull SerializationRule>());	// FIXME ?? Lowest slot usage first
		return serializationRules;
	}

	protected int getSerializationSegmentsCount() {
		assert serializationSegments2id != null;
		return serializationSegments2id.size();
	}

	protected int getSerializationSegmentsIndex(@NonNull SerializationSegment @Nullable [] segments) {
		if (segments == null) {
			return -1;
		}
		ArrayList<@NonNull SerializationSegment> newArrayList = Lists.newArrayList(segments);
		assert newArrayList != null;
		return getSerializationSegmentsIndex(newArrayList);
	}

	protected int getSerializationSegmentsIndex(@NonNull List<@NonNull SerializationSegment> segments) {
		assert serializationSegments2id != null;
		Integer id = serializationSegments2id.get(segments);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> getSerializationSegments2id() {
		assert serializationSegments2id != null;
		return serializationSegments2id;
	}

	protected @NonNull List<@NonNull List<@NonNull SerializationSegment>> getSerializationSegmentsList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull List<@NonNull SerializationSegment>> serializationSegmentsList2 = serializationSegmentsList;
		if (serializationSegmentsList2 == null) {
			timestamp.log("getSerializationSegmentsList start");
			Map<@NonNull List<@NonNull SerializationSegment>, @Nullable Integer> serializationSegments2id2 = serializationSegments2id;
			if (serializationSegments2id2 == null) {
				serializationSegments2id = serializationSegments2id2 = new HashMap<>();
				serializationSegments2id2.put(SerializationSegment.VALUE_SEGMENTS_LIST, null);
				for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis: getSerializationRuleAnalysisList(grammarAnalysis)) {
					for (@NonNull SerializationStep serializationStep : serializationRuleAnalysis.getSerializationRule().getSerializationSteps()) {
						@NonNull SerializationSegment[] staticSegments = serializationStep.getSerializationSegments();
						if ((staticSegments != null) && (staticSegments.length > 0)) {
							ArrayList<@NonNull SerializationSegment> serializationSegments = Lists.newArrayList(staticSegments);
							assert serializationSegments != null;
							serializationSegments2id2.put(serializationSegments, null);
						}
					}
				}
			}
			serializationSegmentsList2 = serializationSegmentsList = new ArrayList<>(serializationSegments2id2.keySet());
			Collections.sort(serializationSegmentsList2, new SerializationUtils.ToStringComparator<@NonNull List<@NonNull SerializationSegment>>() {
				// Shortest first so that {value} is at index 0.
				@Override
				public int compare(@NonNull List<@NonNull SerializationSegment> o1, @NonNull List<@NonNull SerializationSegment> o2) {
					int s1 = o1.size();
					int s2 = o2.size();
					int diff = s1 - s2;
					if (diff != 0) {
						return diff;
					}
					return super.compare(o1, o2);
				}

			});
			int i = 0;
			for (@NonNull List<@NonNull SerializationSegment> segmentList : serializationSegmentsList2) {
				serializationSegments2id2.put(segmentList, i++);
			}
		}
		assert SerializationSegment.VALUE_SEGMENTS_LIST.equals(serializationSegmentsList2.get(0));
		return serializationSegmentsList2;
	}

	protected int getSerializationStepCount() {
		assert serializationStep2id != null;
		return serializationStep2id.size();
	}

	protected int getSerializationStepIndex(@NonNull SerializationStep serializationStep) {
		assert serializationStep2id != null;
		Integer id = serializationStep2id.get(serializationStep);
		assert id != null;
		return id.intValue();
	}

	protected @NonNull List<@NonNull SerializationStep> getSerializationStepList(@NonNull GrammarAnalysis grammarAnalysis) {
		List<@NonNull SerializationStep> serializationStepList2 = serializationStepList;
		if (serializationStepList2 == null) {
			timestamp.log("getSerializationStepList start");
			Map<@NonNull SerializationStep, @Nullable Integer> serializationStep2id2 = serializationStep2id;
			if (serializationStep2id2 == null) {
				serializationStep2id = serializationStep2id2 = new HashMap<>();
			}
			for (@NonNull EClassValue eClassValue : grammarAnalysis.getSortedProducedEClassValues()) {
				for(@NonNull SerializationRule serializationRule : eClassValue.getSerializationRules()) {
					for(@NonNull SerializationStep serializationStep : serializationRule.getSerializationSteps()) {
						serializationStep2id2.put(serializationStep, null);
					}
				}
			}
			serializationStepList2 = serializationStepList = new ArrayList<>(serializationStep2id2.keySet());
			SerializationStepComparator serializationStepComparator = new SerializationStepComparator(serializationStepList2, getSerializationSegments2id());
			Collections.sort(serializationStepList2, serializationStepComparator);
			int i = 0;
			for (@NonNull SerializationStep step : serializationStepList2) {
				if (serializationStepComparator.isGloballyConsistent(step)) {
					if (step instanceof SerializationStepAbstractFeature) {
						if (firstGlobalSerializationStepAssignmentIndex < 0) {
							firstGlobalSerializationStepAssignmentIndex = i;
						}
						lastGlobalSerializationStepAssignmentIndex = i;
					}
					else if (step instanceof SerializationStepKeyword) {
						if (firstGlobalSerializationStepLiteralIndex < 0) {
							firstGlobalSerializationStepLiteralIndex = i;
						}
						lastGlobalSerializationStepLiteralIndex = i;
					}
				}
				serializationStep2id2.put(step, i++);
			}
		}
		return serializationStepList2;
	}

	@Override
	protected String getSerializerBasePackage(final Grammar grammar) {
		return xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".serializer";
	}

	@Override
	protected TypeReference getSyntacticSequencerClass(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}

	protected void initSerializationMetaDataContent(@NonNull GrammarAnalysis grammarAnalysis) {
		getEnumerationValueList(grammarAnalysis);
		getSerializationSegmentsList(grammarAnalysis);
		getSerializationStepList(grammarAnalysis);
		getMatchTermList(grammarAnalysis);
		getMatchStepList(grammarAnalysis);
		getGrammarRuleValueList(grammarAnalysis);
		getGrammarRuleVectorList(grammarAnalysis);
		getEClassList(grammarAnalysis);
		getSerializationRuleAnalysisList(grammarAnalysis);
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
			String substring = referredClassName.substring(index+1);
			assert substring != null;
			return substring;
		}
		else {
			referredClassNames.add(referredClassName);
			index = referredClassName.lastIndexOf('.');
			String substring = referredClassName.substring(index+1);
			assert substring != null;
			return substring;
		}
	}

	public @NonNull String toString(@NonNull GrammarRuleVector grammarRuleVector) {
		DiagnosticStringBuilder s = new DeclarativeSerializerFragmentDiagnosticStringBuilder();
		grammarRuleVector.toString(s);
		return s.toString();
	}

	public @NonNull String toString(@NonNull SerializationMatchStep matchStep) {
		DiagnosticStringBuilder s = new DeclarativeSerializerFragmentDiagnosticStringBuilder();
		matchStep.toString(s);
		return s.toString();
	}

	public @NonNull String toString(@NonNull SerializationRule serializationRule) {
		DiagnosticStringBuilder s = new DeclarativeSerializerFragmentDiagnosticStringBuilder();
		s.append(serializationRule.getName());
		s.append(": ");
		serializationRule.toRuleString(s);
		return s.toString();
	}

	public @NonNull String toString(@NonNull SerializationStep serializationStep) {
		DiagnosticStringBuilder s = new DeclarativeSerializerFragmentDiagnosticStringBuilder();
		serializationStep.toString(s, 0);
		return s.toString();
	}
}
