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
package org.eclipse.ocl.examples.xtext2lpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.LexerGrammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserGrammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TerminalRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule;

//import org.eclipse.ocl.examples.build.acceleo.GenerateXBNF2LPG;

public abstract class GenerateLPGUtils extends GenerateLPG
{
	protected final @NonNull Comparator<CharacterRange> characterRangeComparator = new Comparator<CharacterRange>()
	{
		public int compare(CharacterRange o1, CharacterRange o2) {
			String m1 = o1.getLeft(); 
			String m2 = o2.getLeft();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			int diff = m1.compareTo(m2);
			if (diff == 0) {
				m1 = o1.getRight(); 
				m2 = o2.getRight();
				if (m1 == null) m1 = "";
				if (m2 == null) m2 = "";
				diff = m1.compareTo(m2);
			}
			return diff;
		}
	};

	protected final @NonNull Comparator<Conjunction> conjunctionComparator = new Comparator<Conjunction>()
	{
		public int compare(Conjunction o1, Conjunction o2) {
			int m1 = o1.getElements().size(); 
			int m2 = o2.getElements().size();
			int diff = m1 - m2;
			if (diff == 0) {
				for (int i = 0; i < m1; i++) {
					AbstractElement e1 = o1.getElements().get(i);
					AbstractElement e2 = o2.getElements().get(i);
					String d1 = e1.getDebug(); 
					String d2 = e2.getDebug();
					if (d1 == null) d1 = "";
					if (d2 == null) d2 = "";
					diff = d1.compareTo(d2);
					if (diff != 0) {
						break;
					}
				}
			}
			return diff;
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
	
	protected static final @NonNull Map<String, String> keyword2label = new HashMap<String, String>();
	
	static {
		keyword2label.put("_", "USCORE");
		keyword2label.put("&", "AND");
		keyword2label.put("@", "AT");
		keyword2label.put("|", "BAR");
		keyword2label.put("^", "CARET");
		keyword2label.put(":", "COLON");
		keyword2label.put(",", "COMMA");
		keyword2label.put(".", "DOT");
		keyword2label.put("=", "EQUALS");
		keyword2label.put("#", "HASH");
		keyword2label.put("<", "LANGLE");
		keyword2label.put("{", "LBRACE");
		keyword2label.put("(", "LPAREN");
		keyword2label.put("[", "LSQUARE");
		keyword2label.put("-", "MINUS");
		keyword2label.put("!", "PLING");
		keyword2label.put("+", "PLUS");
		keyword2label.put("?", "QUERY");
		keyword2label.put(">", "RANGLE");
		keyword2label.put("}", "RBRACE");
		keyword2label.put(")", "RPAREN");
		keyword2label.put("]", "RSQUARE");
		keyword2label.put(";", "SEMICOLON");
		keyword2label.put("/", "SLASH");
		keyword2label.put("*", "STAR");
		keyword2label.put("'", "SQUOTE");
		keyword2label.put("\"", "DQUOTE");
		keyword2label.put("\\", "BSLASH");
		keyword2label.put("\r", "CR");
		keyword2label.put("\n", "LF");
		keyword2label.put("\t", "HTAB");
		keyword2label.put(" ", "SPACE");
		keyword2label.put("->", "ARROW");
		keyword2label.put("..", "DOTDOT");
		keyword2label.put("<=", "LESSEQ");
		keyword2label.put(">=", "GTREQ");
		keyword2label.put("<>", "NOTEQ");
		keyword2label.put("::", "SCOPE");
	}
	
	private int state = 0;

	protected String emitKeyword(String keywordValue) {
		return keywordValue.replace("\\w","$0 ");
	}

	protected String emitLabel(Character keywordValue) {
		return emitLabel(keywordValue.toString());
	}

	protected String emitLabel(String keywordValue) {
		String label = keyword2label.get(keywordValue);
		if (label == null) {
			label = keywordValue;
		}
		if (label.matches("[a-zA-Z]*")) {
			return label;
		}
		else {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < label.length(); i++) {
				char c = label.charAt(i);
				String mapped = keyword2label.get(String.valueOf(c));
				if (mapped != null) {
					if (i > 0) {
						s.append("_");
					}
					s.append(mapped);
				}
				else {
					if (i == 0) {
						s.append("T");
					}
					s.append("_" + (int)c);
				}
			}
			return s.toString();
		}
	}

	protected String emitQuotedCharacter(Character c) {
		if (c == '\f') {
			return "FormFeed";
		}
		if (c == '\n') {
			return "NewLine";
		}
		if (c == '\r') {
			return "Return";
		}
		if (c == '\t') {
			return "HorizontalTab";
		}
		if (c == '\'') {
			return "\"'\"";
		}
		return "'" + c + "'";
	}
	
	protected String emitSyntaxName(Syntax syntax) { 
		String name = syntax.getName();
		int lastDot = name.lastIndexOf('.');
		int firstCharacter = lastDot > 0 ? lastDot+1 : 1;
		return name.substring(firstCharacter, name.length());
	}

	protected String emitSyntaxPackage(Syntax syntax) { 
		String name = syntax.getName();
		int lastDot = name.lastIndexOf('.');
		int lastCharacter = lastDot > 0 ? lastDot+1 : name.length();
		return name.substring(0, lastCharacter-1);
	}

	protected @NonNull List<String> getCharacters(@NonNull CharacterRange characterRange) {
		List<String> allCharacters = new ArrayList<String>();
		char left = characterRange.getLeft().charAt(0);
		char right = characterRange.getRight().charAt(0);
		for (char c = left; c <= right; c++) {
			allCharacters.add(String.valueOf(c));
		}
		return allCharacters;
	}

	protected @NonNull LexerGrammar getLexerGrammar(@NonNull Syntax syntax) {
		for (Grammar grammar : syntax.getGrammars()) {
			if (grammar instanceof LexerGrammar) {
				return (LexerGrammar)grammar;
			}
		}
		throw new IllegalStateException("No LexerGrammar");
	}

	protected @NonNull ParserGrammar getParserGrammar(@NonNull Syntax syntax) {
		for (Grammar grammar : syntax.getGrammars()) {
			if (grammar instanceof ParserGrammar) {
				return (ParserGrammar)grammar;
			}
		}
		throw new IllegalStateException("No ParserGrammar");
	}
	
	protected @NonNull List<String> getSortedAlphaChars(@NonNull Syntax syntax) {
		Set<Character> allElements = new HashSet<Character>();
		for (TreeIterator<EObject> tit = syntax.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if (keywordValue.matches("[a-zA-Z]+")) {
					for (char c : keywordValue.toCharArray()) {
						allElements.add(c);
					}
				}
			}
		}
		List<String> sortedElements = new ArrayList<String>();
		for (Character c : allElements) {
			sortedElements.add(c.toString());
		}
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<CharacterRange> getSortedCharacterRanges(@NonNull LexerGrammar lexerGrammar) {
		Set<CharacterRange> allElements = new HashSet<CharacterRange>();
		for (TreeIterator<EObject> tit = lexerGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof CharacterRange) {
				CharacterRange characterRange = (CharacterRange) eObject;
				String label = null;
				for (CharacterRange knownRange : allElements) {
					if (characterRange.getLeft().equals(knownRange.getLeft()) && characterRange.getRight().equals(knownRange.getRight())) {
						label = knownRange.getDebug();
					}
				}
				if (label == null) {
					allElements.add(characterRange);
					String left = characterRange.getLeft();
					String right = characterRange.getRight();
					if (left.equals("0") && right.equals("9")) {
						label = "DIGITS";
					}
					else if (left.equals("a") && right.equals("z")) {
						label = "LOWERS";
					}
					else if (left.equals("A") && right.equals("Z")) {
						label = "UPPERS";
					}
					else {
						label = "RANGE_" + allElements.size();
					}
				}
				characterRange.setDebug(label);
			}
		}
		List<CharacterRange> sortedElements = new ArrayList<CharacterRange>(allElements);
		Collections.sort(sortedElements, characterRangeComparator);
		return sortedElements;
	}

	protected @NonNull List<Conjunction> getSortedConjunctions(@NonNull Disjunction disjunction) {
		List<Conjunction> sortedConjunctions = new ArrayList<Conjunction>(disjunction.getConjunctions());
		Collections.sort(sortedConjunctions, conjunctionComparator);
		return sortedConjunctions;
	}

	protected @NonNull List<String> getSortedKWValues(@NonNull ParserGrammar parserGrammar) {
		Set<String> allElements = new HashSet<String>();
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if (keywordValue.matches("[a-zA-Z]+")) {
					allElements.add(keywordValue);
				}
			}
		}
		List<String> sortedElements = new ArrayList<String>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<TypedRule> getSortedParserRules(@NonNull ParserGrammar parserGrammar) {
		Set<TypedRule> allElements = new HashSet<TypedRule>();
		for (TypedRule subRule : parserGrammar.getRules()) {
			if (!(subRule instanceof TerminalRule)) {
				allElements.add(subRule);
			}
		}
		List<TypedRule> sortedElements = new ArrayList<TypedRule>(allElements);
		Collections.sort(sortedElements, ruleComparator);
		return sortedElements;
	}

	protected @NonNull List<Character> getSortedPunctChars(@NonNull LexerGrammar lexerGrammar) {
		Set<String> characterTerminals = new HashSet<String>();
		for (CharacterRange characterRange: getSortedCharacterRanges(lexerGrammar)) {
			for (String character: getCharacters(characterRange)) {
				characterTerminals.add(character);
			}
		}
		Set<Character> allElements = new HashSet<Character>();
		for (TreeIterator<EObject> tit = lexerGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if (!keywordValue.matches("[a-zA-Z]+")) {
					for (char c : keywordValue.toCharArray()) {
						if (!characterTerminals.contains(String.valueOf(c))) {
							allElements.add(c);
						}
					}
				}
			}
		}
		List<Character> sortedElements = new ArrayList<Character>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<String> getSortedPunctValues(@NonNull ParserGrammar parserGrammar) {
		Set<String> allElements = new HashSet<String>();
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if (!keywordValue.matches("[a-zA-Z]+")) {
					allElements.add(keywordValue);
				}
			}
		}
		List<String> sortedElements = new ArrayList<String>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<UntypedRule> getSortedSubRules(@NonNull Iterable<UntypedRule> subRules) {
		List<UntypedRule> allElements = new ArrayList<UntypedRule>();
		for (UntypedRule subRule : subRules) {
			allElements.add(subRule);
		}
		Collections.sort(allElements, ruleComparator);
		return allElements;
	}

	protected @NonNull List<TerminalRule> getSortedTerminalRules(@NonNull Syntax syntax) {
		Set<TerminalRule> allElements = new HashSet<TerminalRule>();
		for (TreeIterator<EObject> tit = syntax.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof TerminalRule) {
				allElements.add((TerminalRule)eObject);
			}
		}
		List<TerminalRule> sortedElements = new ArrayList<TerminalRule>(allElements);
		Collections.sort(sortedElements, ruleComparator);
		return sortedElements;
	}

	protected int nextState() {
		return ++state;
	}
	
	protected @NonNull <T extends AbstractRule> List<T> selectRules(@NonNull Iterable<T> rules, String name) {
		List<T> selectedRules = new ArrayList<T>();
		for (T rule : rules) {
			if (name.equals(rule.getName())) {
				selectedRules.add(rule);
			}
		}
		return selectedRules;
	}
}
