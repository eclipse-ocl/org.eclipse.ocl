/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.latex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.basecs.RootCS;
import org.eclipse.ocl.xtext.markup.MarkupUtils;
import org.eclipse.ocl.xtext.markupcs.BulletElement;
import org.eclipse.ocl.xtext.markupcs.FontElement;
import org.eclipse.ocl.xtext.markupcs.Markup;
import org.eclipse.ocl.xtext.markupcs.MarkupElement;
import org.eclipse.ocl.xtext.markupcs.NewLineElement;
import org.eclipse.ocl.xtext.markupcs.NullElement;
import org.eclipse.ocl.xtext.markupcs.OCLCodeElement;
import org.eclipse.ocl.xtext.markupcs.OCLTextElement;
import org.eclipse.ocl.xtext.markupcs.TextElement;
import org.eclipse.ocl.xtext.markupcs.util.MarkupSwitch;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.ReferencedMetamodel;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;

public abstract class GenerateLaTeXUtils extends GenerateLaTeX
{
	protected final @NonNull Comparator<Iteration> iterationComparator = new Comparator<Iteration>()
	{
		public int compare(Iteration o1, Iteration o2) {
			String m1 = o1.getName(); 
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			int diff = m1.compareTo(m2);
			if (diff != 0) return diff;
			int s1 = o1.getOwnedIterators().size();
			int s2 = o2.getOwnedIterators().size();
			return s1 - s2;
		}
	};

	protected final @NonNull Comparator<AbstractMetamodelDeclaration> metamodelComparator = new Comparator<AbstractMetamodelDeclaration>()
	{
		public int compare(AbstractMetamodelDeclaration o1, AbstractMetamodelDeclaration o2) {
			String m1 = o1.getAlias(); 
			String m2 = o2.getAlias();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};

	protected final @NonNull Comparator<Nameable> nameableComparator = new Comparator<Nameable>()
	{
		public int compare(Nameable o1, Nameable o2) {
			String m1 = o1.getName(); 
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};
	
	protected final @NonNull Comparator<ENamedElement> namedComparator = new Comparator<ENamedElement>()
	{
		public int compare(ENamedElement o1, ENamedElement o2) {
			String m1 = o1.getName(); 
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};
	
	protected final @NonNull Comparator<AbstractRule> ruleComparator = new Comparator<AbstractRule>()
	{
		public int compare(AbstractRule o1, AbstractRule o2) {
			String m1 = o1.getName(); 
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};

	protected static final @NonNull Comparator<Operation> operationComparator = new OperationComparator();

	protected static final class OperationComparator implements Comparator<Operation>
	{
		public static Map<String,String> nameMap = new HashMap<String, String>();
		static {
			nameMap.put("=", " 1");
			nameMap.put("<>", " 2");
			nameMap.put("<", " 3");
			nameMap.put("<=", " 4");
			nameMap.put(">=", " 5");
			nameMap.put(">", " 6");
		}

		public int compare(Operation o1, Operation o2) {
			String m1 = o1.getName(); 
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			String k1 = nameMap.get(m1);
			String k2 = nameMap.get(m2);
			if (k1 != null) m1 = k1;
			if (k2 != null) m2 = k2;
			int diff = m1.compareTo(m2);
			if (diff != 0) return diff;
			int s1 = o1.getOwnedParameters().size();
			int s2 = o2.getOwnedParameters().size();
			return s1 - s2;
		}
	}

	protected static class MarkupToLaTeX extends MarkupSwitch<@Nullable String>
	{
		@SuppressWarnings("unused") private final Namespace scope;
		private final StringBuilder s = new StringBuilder();
		
		private enum BulletState { NO_ITEMIZE, IN_ITEM, IN_ITEMIZE };
		private BulletState bulletState = BulletState.NO_ITEMIZE;
		
		public MarkupToLaTeX(Namespace scope) {
			this.scope = scope;
		}

		@Override
		public String caseBulletElement(BulletElement element) {
			if (bulletState == BulletState.NO_ITEMIZE) {
				s.append("#\\begin#{itemize#}\n");
				bulletState = BulletState.IN_ITEMIZE;
			}
			s.append("#\\item ");
			bulletState = BulletState.IN_ITEM;
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("\n");
			bulletState = BulletState.IN_ITEMIZE;
			return s.toString();
		}

		protected String endItemize() {
			if (bulletState == BulletState.IN_ITEMIZE) {
				s.append("#\\end#{itemize#}\n");
				bulletState = BulletState.NO_ITEMIZE;
			}
			return s.toString();
		}

		@Override
		public String caseFontElement(FontElement element) {
			endItemize();
			String tag = element.getFont().equals("e") ? "textit" : "textrm";
			s.append("\\" + tag + "{");
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("}");
			return s.toString();
		}

		@Override
		public String caseMarkupElement(MarkupElement element) {
			endItemize();
			s.append("$$");
			s.append(element.eClass().getName());
			return s.toString();
		}

		@Override
		public String caseNewLineElement(NewLineElement element) {
			s.append(element.getText());
			return s.toString();
		}

		@Override
		public String caseNullElement(NullElement object) {
			endItemize();
			return s.toString();
		}

		@Override
		public String caseOCLCodeElement(OCLCodeElement element) {
			endItemize();
			s.append("bc.. \n");
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("\n");
			s.append("p. \n");
			s.append("\n");
			return s.toString();
		}

		@Override
		public String caseOCLTextElement(OCLTextElement element) {
			endItemize();
			s.append("#\\oclEmph#{");
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("#}");
			return s.toString();
		}

		@Override
		public String caseTextElement(TextElement element) {
			endItemize();
			for (String text : element.getText()) {
				s.append(text);
			}
			return s.toString();
		}
	};
	
	public static PrettyPrintOptions.@NonNull Global createOptions(@Nullable Namespace scope) {
		PrettyPrintOptions.Global options = new PrettyPrintOptions.Global(scope)
		{
			@Override
			public @Nullable Set<String> getReservedNames() {
				return null;
			}

			@Override
			public @Nullable Set<String> getRestrictedNames() {
				return null;
			}			
		};
		return options;
	}

	protected String decode(@NonNull String body, @Nullable Namespace scope) {
		IParseResult parseResult = MarkupUtils.decode(body);
		if (parseResult == null) {
			throw new NullPointerException("Missing ParseResult for \"" + body + "\"");
		}
		Markup markup = (Markup) parseResult.getRootASTElement();
		for (INode parseError : parseResult.getSyntaxErrors()) {
			System.out.println(parseError);
		}
		if (markup == null) {
			throw new NullPointerException("Missing parsed content for \"" + body + "\"");
		}
		StringBuilder s = new StringBuilder();
		MarkupToLaTeX markupToLaTeX = new MarkupToLaTeX(scope);
		for (MarkupElement element : markup.getElements()) {
			s.append(markupToLaTeX.doSwitch(element));
		}
		return markupToLaTeX.endItemize();
	}

	protected String emitAllTT(String content) {
		StringBuilder s = new StringBuilder();
		s.append("#\\begin#{alltt#}");
		int col = 0;
		int lastSpace = s.length();
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (c == '\n') {
				col = 0;
				lastSpace = s.length();
			}
			else if (c == ' ') {
				if (col > 70) {
					s.setCharAt(lastSpace, '\n');
					col = s.length() - lastSpace;
				}
				lastSpace = s.length();
			}
			s.append(c);
			col++;
		}
		s.append("#\\end#{alltt#}");
		return s.toString();
	}
	
	protected String emitBeginDefinition() {
//		return "#\\begin#{oclDefinition#}";
		return "#\\begin#{verbatim#}";
	}
	
	protected String emitCharacters(@NonNull String string) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '\b') {
				s.append("\\b");
			}
			else if (c == '\n') {
				s.append("\\n");
			}
			else if (c == '\r') {
				s.append("\\r");
			}
			else if (c == '\t') {
				s.append("\\t");
			}
			else if (c == '\\') {
				s.append("\\\\");
			}
			else if (c == '\'') {
				s.append("\\'");
			}
			else {
				s.append(c);
			}
		}
		return s.toString();
	}

	protected String emitClassDef(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		String className = asClass.getName();
		String packageName = asClass.getOwningPackage().getName();
		if (className == null) className = "<<anon>>";
		if (packageName == null) packageName = "<<anon>>";
		return  "#\\hypertarget#{" + encodeLabelText(packageName) + ":" + encodeLabelText(className) + "#}#{" /*+ encodeLabelText(className)*/ + "#}";
	}

	protected String emitClassRef(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		String className = asClass.getName();
		String packageName = asClass.getOwningPackage().getName();
		if (className == null) className = "<<anon>>";
		if (packageName == null) packageName = "<<anon>>";
		return  "#\\hyperlink#{" + encodeLabelText(packageName) + ":" + encodeLabelText(className) + "#}#{" + encodeLabelText(className) + "#}";
	}
	
	protected String emitComment(@NonNull EObject eObject) {
		ICompositeNode node = NodeModelUtils.getNode(eObject);
		List<ILeafNode> documentationNodes = null;
		for (ILeafNode leafNode : node.getLeafNodes()) {
			EObject grammarElement = leafNode.getGrammarElement();
			if (!(grammarElement instanceof TerminalRule)) {
				break;
			}
			TerminalRule terminalRule = (TerminalRule) grammarElement;
			String name = terminalRule.getName();
			if ("WS".equals(name)) {
			}
			else if ("SL_COMMENT".equals(name)) {
			}
			else if ("ML_COMMENT".equals(name)) {
				if (documentationNodes == null) {
					documentationNodes = new ArrayList<ILeafNode>();
				}
				documentationNodes.add(leafNode);
			}
			else {
				break;
			}
		}
		if (documentationNodes == null) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		for (ILeafNode documentationNode : documentationNodes) {
			String text = documentationNode.getText().replace("\r", "");
			if (text.startsWith("/*") && text.endsWith("*/")) {
				String contentString = text.substring(2, text.length()-2).trim();
				for (String string : contentString.split("\n")) {
					String trimmedString = string.trim();
					if (s.length() > 0) {
						s.append("\n");
					}
					s.append(trimmedString.startsWith("*") ? trimmedString.substring(1).trim() : trimmedString);
				}
			}
			else {
				s.append(text.trim());
			}
		}
		s.append("\n\n");	
		@NonNull String body = s.toString();
		return decode(body, null);
	}
	
	protected String emitEmphasis(@NonNull String name) {
		return "#\\oclEmph#{" + encodeSectionText(name) + "#}";
	}
	
	protected String emitEndDefinition() {
//		return "#\\end#{oclDefinition#}";
		return "#\\end#{verbatim#}";
	}
	
	protected String emitHeading0a(String name) {
		return name != null ? "#\\oclHeadingZero#{" + encodeSectionText(name) + "#}" : "";
	}
	
	protected String emitHeading0b(String name) {
		return name != null ? "#\\oclHeadingZero#{\t" + encodeSectionText(name) + "#}" : "";
	}
	
	protected String emitHeading1(String name, @Nullable String label) {
		return name != null ? "#\\oclHeadingOne#{" + encodeSectionText(name) + "#}" + emitLabel(label) : "";
	}
	
	protected String emitHeading2(String name, @Nullable String label) {
		return name != null ? "#\\oclHeadingTwo#{" + encodeSectionText(name) + "#}" + emitLabel(label) : "";
	}
	
	protected String emitHeading3(String name, @Nullable String label) {
		return name != null ? "#\\oclHeadingThree#{" + encodeSectionText(name) + "#}" + emitLabel(label) : "";
	}
	
	protected String emitHeading4(String name, @Nullable String label) {
		return name != null ? "#\\oclHeadingFour#{" + encodeSectionText(name) + "#}" + emitLabel(label) : "";
	}
	
	protected String emitLabel(@Nullable String label) {
		return label != null ? "#\\label#{" + encodeLabelText(label) + "#}" : "";
	}

	protected String emitRuleDef(@NonNull AbstractRule gRule) {
		String ruleName = gRule.getName();
		String packageName = "Rule"; //asClass.getOwningPackage().getName();
		if (ruleName == null) ruleName = "<<anon>>";
//		if (packageName == null) packageName = "<<anon>>";
		return  "#\\hypertarget#{" + encodeLabelText(packageName) + ":" + encodeLabelText(ruleName) + "#}#{" + encodeLabelText(ruleName) + "#}";
	}

	protected String emitRuleRef(@NonNull AbstractRule gRule) {
		String ruleName = gRule.getName();
		String packageName = "Rule"; //asClass.getOwningPackage().getName();
		if (ruleName == null) ruleName = "<<anon>>";
//		if (packageName == null) packageName = "<<anon>>";
		return  "#\\hyperlink#{" + encodeLabelText(packageName) + ":" + encodeLabelText(ruleName) + "#}#{" + encodeLabelText(ruleName) + "#}";
	}

	/**
	 * Re-encode latexContent to ensure that it is intelligible to LaTeX.
	 * <p>
	 * In: all characters as their logical equivalents. Anything that needs to be handled specially by
	 * LaTeX needs to have a # escape to avoid the application of LaTeX escapes such as textbackslash
	 * for characters that must not be misinterpreted by LaTeX.
	 * <p>
	 * Use "##" for "#", "#anything else for anything else", e.g. "#{" for a LaTeX "{".
	 */
	protected String encodeForLaTeX(String latexContent) {
		StringBuilder s = new StringBuilder();
		int length = latexContent.length();
		for (int i = 0; i < length; ) {
			char c = latexContent.charAt(i++);
			if (c == '#') {
				if (i < length) {
					c = latexContent.charAt(i++);
				}
				if (c == '#') {
					s.append("\\#");
				}
				else {
					s.append(c);
				}
			}
			else if (c == '\\') {
				s.append("\\textbackslash{}");
			}
			else if (c == '<') {
				s.append("\\textless{}");
			}
			else if (c == '>') {
				s.append("\\textgreater{}");
			}
			else if (c == '|') {
				s.append("\\textbar{}");
			}
			else if (c == '_') {
				s.append("\\_");
			}
			else if (c == '$') {
				s.append("\\$");
			}
			else if (c == '&') {
				s.append("\\&");
			}
			else if (c == '%') {
				s.append("\\%");
			}
			else if (c == '{') {
				s.append("\\{");
			}
			else if (c == '}') {
				s.append("\\}");
			}
			else if (c == '~') {
				s.append("\\~{}");
			}
			else if (c == '\'') {
				s.append("'");
			}
			else if (c == '"') {
				s.append("''");
			}
			else if (c == '<') {
				s.append("\\textless{}");
			}
			else {
				s.append(c);
			}
		}
		return s.toString();
	}
	
	protected String encodeLabelText(@NonNull String string) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '_') {
				s.append("#_");
			}
			else {
				s.append(c);
			}
		}
		return s.toString();
	}
	
	protected String encodeSectionText(@NonNull String string) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '_') {
				s.append("#\\#_");
			}
			else {
				s.append(c);
			}
		}
		return s.toString();
	}
	
	protected @Nullable Library getLibrary(@NonNull Model root) {
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Library) {
				return (Library) eObject;
			}
		}
		return null;
	}
	
	protected Iterable<Precedence> getPrecedences(@NonNull Library asLibrary) {
		List<Precedence> precedences = new ArrayList<Precedence>(asLibrary.getOwnedPrecedences());
		return precedences;
	}
	
	protected org.eclipse.ocl.pivot.@Nullable Package getPrimaryPackage(@NonNull PivotMetamodelManager metamodelManager, @Nullable Resource oclResource) {
		if (oclResource != null) {
			for (EObject eContent : oclResource.getContents()) {
				if (eContent instanceof RootCS) {
					Element asRoot = ((RootCS)eContent).getPivot();
					if (asRoot instanceof Model) {
						for (org.eclipse.ocl.pivot.Package asPackage : ((Model)asRoot).getOwnedPackages()) {
							return asPackage;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected org.eclipse.ocl.pivot.@Nullable Package getSecondaryPackage(@NonNull MetamodelManager metamodelManager, @Nullable Resource oclResource) {
		if (oclResource != null) {
			for (EObject eContent : oclResource.getContents()) {
				if (eContent instanceof RootCS) {
					Element asRoot = ((RootCS)eContent).getPivot();
					if (asRoot instanceof Model) {
						for (org.eclipse.ocl.pivot.Package asPackage : ((Model)asRoot).getOwnedPackages()) {
							return asPackage;
						}
					}
				}
			}
		}
		return null;
	}

	protected @NonNull List<Property> getSortedAssociations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Property> allElements = new HashSet<Property>();
		for (Property asProperty : asClass.getOwnedProperties()) {
//			[let pAssociations : Sequence(Property) = pClass.ownedAttribute->select(e | not e.type.oclIsKindOf(DataType) and e.type.owningTemplateParameter->isEmpty())->asSequence()]
			if (!(asProperty.getType() instanceof DataType) && !asProperty.isIsImplicit()) {
				allElements.add(asProperty);
			}
		}
		List<Property> sortedElements = new ArrayList<Property>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Property> getSortedAttributes(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Property> allElements = new HashSet<Property>();
		for (Property asProperty : asClass.getOwnedProperties()) {
			if (asProperty.getType() instanceof DataType) {
				allElements.add(asProperty);
			}
		}
		List<Property> sortedElements = new ArrayList<Property>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSortedClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		Set<org.eclipse.ocl.pivot.Class> allElements = new HashSet<org.eclipse.ocl.pivot.Class>();
		allElements.addAll(asPackage.getOwnedClasses());
		List<org.eclipse.ocl.pivot.Class> sortedElements = new ArrayList<org.eclipse.ocl.pivot.Class>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Constraint> getSortedConstraints(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>();
		for (Constraint asConstraint : asOperation.getOwnedConstraints()) {
			allElements.add(asConstraint);
		}
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}
	
	protected @NonNull List<EClass> getSortedEClasses(@NonNull EPackage ePackage) {
		Set<EClass> allElements = new HashSet<EClass>();
		TreeIterator<EObject> tit = ePackage.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof EClass) {
				allElements.add((EClass)eObject);
			}
		}
		List<EClass> sortedElements = new ArrayList<EClass>(allElements);
		Collections.sort(sortedElements, namedComparator);
		return sortedElements;
	}

	protected @NonNull List<Iteration> getSortedIterations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Iteration> allElements = new HashSet<Iteration>();
		for (Operation asOperation : asClass.getOwnedOperations()) {
			if (asOperation instanceof Iteration) {
				allElements.add((Iteration) asOperation);
			}
		}
		List<Iteration> sortedElements = new ArrayList<Iteration>(allElements);
		Collections.sort(sortedElements, iterationComparator);
		return sortedElements;
	}
	
	protected @NonNull List<ReferencedMetamodel> getSortedMetamodelDeclarations(@NonNull Grammar grammar) {
		List<ReferencedMetamodel> sortedMetamodels = new ArrayList<ReferencedMetamodel>();
		for (AbstractMetamodelDeclaration metamodelDeclaration : grammar.getMetamodelDeclarations()) {
			String alias = metamodelDeclaration.getAlias();
			if ((metamodelDeclaration instanceof ReferencedMetamodel) && (alias != null) && !alias.equals("ecore")) {
				sortedMetamodels.add((ReferencedMetamodel) metamodelDeclaration);
			}
		}
		Collections.sort(sortedMetamodels, metamodelComparator);
		return sortedMetamodels;
	}

	protected @NonNull List<Operation> getSortedOperations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Operation> allElements = new HashSet<Operation>();
		for (Operation asOperation : asClass.getOwnedOperations()) {
			if (!(asOperation instanceof Iteration)) {
				allElements.add(asOperation);
			}
		}
		List<Operation> sortedElements = new ArrayList<Operation>(allElements);
		Collections.sort(sortedElements, operationComparator);
		return sortedElements;
	}

	protected @NonNull List<ParserRule> getSortedParserRules(@NonNull Grammar grammar) {
		List<ParserRule> sortedRules = new ArrayList<ParserRule>();
		for (AbstractRule rule : grammar.getRules()) {
			if (rule instanceof ParserRule) {
				sortedRules.add((ParserRule) rule);
			}
		}
		Collections.sort(sortedRules, ruleComparator);
		return sortedRules;
	}

	protected @NonNull List<ParserRule> getSortedParserRules(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull Grammar grammar) {
		EClassifier eClassifier = (EClassifier) ((PivotObjectImpl)asClass).getESObject();
		List<ParserRule> sortedRules = new ArrayList<ParserRule>();
		for (AbstractRule rule : grammar.getRules()) {
			if (rule instanceof ParserRule) {
				ParserRule parserRule = (ParserRule) rule;
				TypeRef type = parserRule.getType();
				if (type.getClassifier() == eClassifier) {
					sortedRules.add(parserRule);
				}
			}
		}
		Collections.sort(sortedRules, ruleComparator);
		return sortedRules;
	}

	protected @NonNull List<Constraint> getSortedPostconditions(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>(asOperation.getOwnedPostconditions());
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Constraint> getSortedPreconditions(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>(asOperation.getOwnedPreconditions());
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<TerminalRule> getSortedTerminalRules(@NonNull Grammar grammar) {
		List<TerminalRule> sortedRules = new ArrayList<TerminalRule>();
		for (AbstractRule rule : grammar.getRules()) {
			if (rule instanceof TerminalRule) {
				sortedRules.add((TerminalRule) rule);
			}
		}
		Collections.sort(sortedRules, ruleComparator);
		return sortedRules;
	}

	protected String prettyPrint(@NonNull Comment comment, Namespace scope) {
		String body = comment.getBody();
		if (body == null) {
			throw new NullPointerException("Missing Comment body");
		}
		return decode(body, scope);
	}
	
	protected String prettyPrint(@NonNull Constraint constraint, Namespace scope) {
		PrettyPrintOptions options = createOptions(scope);
//		PrettyPrintExprVisitor visitor = new PrettyPrintExprVisitor(options);
		PrettyPrinter printer = PrettyPrinter.createPrinter(constraint, options);
		try {
			printer.appendElement(constraint);
			String string = printer.toString(options.getIndentStep(), options.getLinelength());
			//				System.out.println("Expr-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
			return string;
		}
		catch (Exception e) {
			e.printStackTrace();
			return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
		}
	}

	protected String prettyPrint(@NonNull Element element, Namespace scope) {
		PrettyPrintOptions options = createOptions(scope);
		PrettyPrinter printer = PrettyPrinter.createNamePrinter(element, options);
		try {
			printer.appendElement(element);
			String string = printer.toString(options.getIndentStep(), options.getLinelength());
//			System.out.println("Name-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
			return string;
		}
		catch (Exception e) {
			e.printStackTrace();
			return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
		}
	}

//	protected String prettyPrint(MarkupElement element, Namespace scope) {
//		MarkupToLaTeX markupToLaTeX = new MarkupToLaTeX(scope);
//		markupToLaTeX.doSwitch(element);
//		return markupToLaTeX.endItemize();
//	}
}
