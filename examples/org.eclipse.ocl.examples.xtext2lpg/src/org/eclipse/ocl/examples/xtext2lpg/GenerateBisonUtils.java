/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg;

import java.lang.reflect.Type;
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
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.LexerGrammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserGrammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCallAssignment;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TerminalRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule;
import org.eclipse.ocl.pivot.utilities.NameUtil;

//import org.eclipse.ocl.examples.build.acceleo.GenerateXBNF2LPG;

public abstract class GenerateBisonUtils extends GenerateBison
{
	private static final @NonNull String ALPHABETIC_REGEX = "[a-zA-Z]*";
	private static final @NonNull String KEYWORD_REGEX = ALPHABETIC_REGEX; // "[a-zA-Z:<>/?]+";

	protected final @NonNull Comparator<@NonNull CharacterRange> characterRangeComparator = new Comparator<@NonNull CharacterRange>()
	{
		@Override
		public int compare(@NonNull CharacterRange o1, @NonNull CharacterRange o2) {
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

	protected final @NonNull Comparator<@NonNull Conjunction> conjunctionComparator = new Comparator<@NonNull Conjunction>()
	{
		@Override
		public int compare(@NonNull Conjunction o1, @NonNull Conjunction o2) {
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

	protected final @NonNull Comparator<@NonNull AbstractRule> ruleComparator = new Comparator<@NonNull AbstractRule>()
	{
		@Override
		public int compare(@NonNull AbstractRule o1, @NonNull AbstractRule o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};

	protected static final @NonNull Map<String, String> keyword2label = new HashMap<>();

	static {
		keyword2label.put("_", "USCORE");
		keyword2label.put("&", "AND");
		keyword2label.put("@", "AT");
		keyword2label.put("|", "BAR");
		keyword2label.put("^", "CARET");
		keyword2label.put(":", "COLON");
		keyword2label.put(",", "COMMA");
		keyword2label.put(".", "DOT");
		keyword2label.put("=", "EQ");
		keyword2label.put("#", "HASH");
		keyword2label.put("$", "DOLLAR");
		keyword2label.put("%", "PERCENT");
		keyword2label.put("<", "LT");
		keyword2label.put("{", "LBRACE");
		keyword2label.put("(", "LPAREN");
		keyword2label.put("[", "LSQUARE");
		keyword2label.put("-", "MINUS");
		keyword2label.put("!", "PLING");
		keyword2label.put("+", "PLUS");
		keyword2label.put("?", "QUERY");
		keyword2label.put(">", "GT");
		keyword2label.put("}", "RBRACE");
		keyword2label.put(")", "RPAREN");
		keyword2label.put("]", "RSQUARE");
		keyword2label.put(";", "SEMICOLON");
		keyword2label.put("/", "SLASH");
		keyword2label.put("*", "STAR");
		keyword2label.put("'", "SQUOTE");
		keyword2label.put("`", "GRAVE");
		keyword2label.put("\"", "DQUOTE");
		keyword2label.put("\\", "BSLASH");
		keyword2label.put("~", "TILDE");
		keyword2label.put("\r", "CR");
		keyword2label.put("\n", "LF");
		keyword2label.put("\t", "HTAB");
		keyword2label.put(" ", "SPACE");
		keyword2label.put("->", "ARROW");
		keyword2label.put("..", "DOTDOT");
		keyword2label.put("<=", "LE");
		keyword2label.put(">=", "GE");
		keyword2label.put("<>", "NE");
		keyword2label.put("::", "SCOPE");
	}

	private int state = 0;

	protected String emitCharacter(char c) {
		switch (c) {
		case '\b': return "'\\b'";
		case '\f': return "'\\f'";
		case '\n': return "'\\n'";
		case '\r': return "'\r'";
		case '\t': return "'\\t'";
		case '\'': return "'\\''";
		case '\\': return "'\\\\'";
		}
		if ((' ' <= c) && (c <= '~')) {
			return "'" + c + "'";
		}
		return "'\\0x" + Integer.toHexString(c) + "'";
	}

	protected String emitContinueEObject(Conjunction conjunction) {
		String setAttributes = "Set";
		ParserRule rule = getParentRule(conjunction);
		if (rule != null) {
			String ruleName = rule.getName();
			String segments[] = ruleName.split("_");
			int segmentsLength = segments.length;
			if (segmentsLength >= 1) {
				String algorithm = segments[0];
				if ((segmentsLength >= 4) && "EcoreFeature".equals(algorithm)) {
					String packageName = segments[segmentsLength-3];
					String className = segments[segmentsLength-2];
					String featureName = segments[segmentsLength-1];
					EPackage ePackage = getEPackage(rule, packageName);
					if (ePackage !=  null) {
						EClassifier eClassifier = ePackage.getEClassifier(className);
						if (eClassifier instanceof EClass) {
							EStructuralFeature eStructuralFeature = ((EClass)eClassifier).getEStructuralFeature(featureName);
							if ((eStructuralFeature != null) && isMapFeature(eStructuralFeature)) {
								setAttributes = "Map";
							}
						}
					}
				}
			}
		}
		switch (conjunction.getElements().size()) {
		case 1: return "$$.list = create" + setAttributes + "($1.list)";
		case 2: return "$$.list = appendTo" + setAttributes + "($1.list, $2.pointer)";
		}
		return null;
	}

	public static boolean isMapFeature(EStructuralFeature eFeature) {
		EClassifier eType = eFeature.getEType();
		Class<?> instanceClass = eType.getInstanceClass();
		return java.util.Map.Entry.class.isAssignableFrom(instanceClass);
	}


	protected String emitCreateEObject(Conjunction conjunction) {
		ParserRule rule = getParentRule(conjunction);
		if (rule != null) {
			String ruleName = rule.getName();
			String segments[] = ruleName.split("_");
			int segmentsLength = segments.length;
			if (segmentsLength >= 1) {
				String algorithm = segments[0];
				if ("STRING".equals(algorithm)) {
					return "$$.string = $1.string;";
				}
				else if ("IDENTIFIER".equals(algorithm)) {
					return "$$.identifier = $1.identifier;";
				}
				else if ((segmentsLength == 2) && "Terminal".equals(algorithm)) {
					if ("String".equals(segments[1])) {
						return "$$.identifier = $1.identifier;";
					}
					else if ("Identifier".equals(segments[1])) {
						return "$$.identifier = $1.identifier;";
					}
				}
				else if ("XMLAttribute".equals(algorithm)) {
					return emitCreateXMLAttribute(rule, segments);
				}
				else if ("XMLDocument".equals(algorithm)) {
					return emitCreateXMLDocument(conjunction, segments);
				}
				else if ((segmentsLength >= 3) && "EcoreRoot".equals(algorithm)) {
					return emitCreateEcoreRoot(conjunction, segments);
				}
				else if ((segmentsLength >= 4) && "EcoreClass".equals(algorithm)) {
					return emitCreateEcoreClass(conjunction, segments);
				}
				else if ((segmentsLength >= 4) && "EcoreFeature".equals(algorithm)) {
					return emitCreateEcoreFeature(conjunction, segments);
				}
				if (segmentsLength >= 3) {
				}
			}
		}
		return "createEObject()";
	}

	private ParserRule getParentRule(EObject eObject) {
		for (EObject eContainer = eObject; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof ParserRule) {
				return (ParserRule)eContainer;
			}
		}
		return null;
	}

	private String emitCreateEcoreClass(@NonNull Conjunction conjunction, @NonNull String @NonNull [] segments) {
		int segmentsLength = segments.length;
		String packageName = segments[segmentsLength-3];
		String className = segments[segmentsLength-2];
		String featureName = segments[segmentsLength-1];
		EPackage ePackage = getEPackage(conjunction, packageName);
		assert ePackage != null;
		EClassifier eClassifier = ePackage.getEClassifier(className);
		EStructuralFeature eStructuralFeature = ((EClass)eClassifier).getEStructuralFeature(featureName);
		assert eStructuralFeature != null;
		//	String packageInterfaceName = getEPackageInterfaceName(ePackage);
		StringBuilder s = new StringBuilder();
		List<AbstractElement> elements = conjunction.getElements();
		boolean isFirst = true;
		int listIndex = -1;
		for (int i = 0; i < elements.size(); i++) {
			AbstractElement element = elements.get(i);
			if (element instanceof RuleCall) {
				if (listIndex < 0) {
					listIndex = i;
				}
				else {
					if (isFirst) {
						s.append("void **list = $");
						s.append(listIndex+1);
						s.append(".list;\n");
						isFirst = false;
					}
					s.append("list = appendToList(list, $");
					s.append(i+1);
					s.append(".pointer);\n");
				}
			}
		}
		if (listIndex < 0) {
			s.append("$$.pointer = null;\n");
		}
		else if (isFirst) {
			s.append("$$.pointer = $");
			s.append(listIndex+1);
			s.append(".pointer;\n");
		}
		else {
			s.append("$$.pointer = list;\n");
		}
		return s.toString();
	}

	private String emitCreateEcoreRoot(@NonNull Conjunction conjunction, @NonNull String @NonNull [] segments) {
		int segmentsLength = segments.length;
		String packageName = segments[segmentsLength-2];
		String className = segments[segmentsLength-1];
		EPackage ePackage = getEPackage(conjunction, packageName);
		assert ePackage != null;
		//		EClassifier eClassifier = ePackage.getEClassifier(className);
		//		assert eClassifier != null;


		String packageInterfaceName = getEPackageInterfaceName(ePackage);

		StringBuilder s = new StringBuilder();
		s.append("EPackage *lhs = $$.pointer = malloc(sizeof(EPackage));\n");
		List<AbstractElement> elements = conjunction.getElements();
		for (int i = 0; i < elements.size(); i++) {
			AbstractElement element = elements.get(i);
			if (element instanceof RuleCallAssignment) {
				EStructuralFeature eFeature = ((RuleCallAssignment)element).getFeature();
				Class<?> instanceClass = eFeature.getEType().getInstanceClass();
				s.append("lhs->s_");
				s.append(eFeature.getName());
				s.append(" = ");
				if (instanceClass == String.class) {
					s.append("$");
					s.append((i+1));
					s.append(".string");
				}
				else if (instanceClass == boolean.class) {
					s.append("booleanValueOf($");
					s.append((i+1));
					s.append(".string)");
				}
				else {
					s.append("$");
					s.append((i+1));
					s.append(".pointer");
				}
				s.append(";\n");
			}
			else if (element instanceof RuleCall) {
				s.append("/* lhs->s_" + ((RuleCall)element).getClass().getSimpleName() + " = $" + (i+1) + "; */\n");
			}
		}
		return s.toString();
	}

	protected String getEPackageInterfaceName(@NonNull EPackage ePackage) {
		EFactory eFactoryInstance = ePackage.getEFactoryInstance();
		Class<? extends EPackage> packageImplClass = eFactoryInstance.getEPackage().getClass();
		Type[] genericInterfaces = packageImplClass.getGenericInterfaces();
		String packageInterfaceName;		// FIXME Use genmodel helper
		if ((genericInterfaces.length == 1) && (genericInterfaces[0] instanceof Class<?>)) {
			packageInterfaceName = ((Class<?>)genericInterfaces[0]).getName();		// FIXME Use genmodel helper
		}
		else {
			packageInterfaceName = packageImplClass.getName().replace("impl.",  "").replace("Impl", "");
		}
		return packageInterfaceName;
	}

	private @Nullable EPackage getEPackage(@NonNull EObject eObject, @NonNull String packageName) {
		EObject eRoot = EcoreUtil.getRootContainer(eObject);
		if (eRoot instanceof Syntax) {
			for (MetamodelDeclaration metamodelDeclaration : ((Syntax)eRoot).getMetamodelDeclarations()) {
				if (packageName.equals(metamodelDeclaration.getName())) {
					return metamodelDeclaration.getReferredPackage();
				}
			}
		}
		return null;
	}

	private String emitCreateEcoreFeature(@NonNull Conjunction conjunction, @NonNull String @NonNull [] segments) {
		int segmentsLength = segments.length;
		//	String packageName = segments[segmentsLength-3];
		String className = segments[segmentsLength-2];
		String featureName = segments[segmentsLength-1];
		StringBuilder s = new StringBuilder();
		s.append("/*$$.u_");
		s.append(className);
		s.append("->s_");
		s.append(featureName);
		s.append(" = ");
		List<AbstractElement> elements = conjunction.getElements();
		for (int i = 0; i < elements.size(); i++) {
			AbstractElement element = elements.get(i);
			if (element instanceof RuleCall) {
				s.append("$" + (i+1));
				// break;  -- not breaking debugs bad multiples
			}
		}
		s.append(";*/\n");
		return s.toString();
	}

	private String emitCreateXMLAttribute(@NonNull AbstractRule rule, @NonNull String @NonNull [] segments) {
		String namespace = segments[1];
		if ("xmi".equals(namespace)) {
			return emitCreateXMIAttribute(rule, segments);
		}
		if ("xmlns".equals(namespace)) {
			return emitCreateXMLNSAttribute(rule, segments);
		}
		if ("xsi".equals(namespace)) {
			return emitCreateXSIAttribute(rule, segments);
		}
		return null;
	}


	private String emitCreateXMLDocument(@NonNull Conjunction conjunction, @NonNull String @NonNull [] segments) {
		int segmentsLength = segments.length;
		String packageName = segments[segmentsLength-2];
		String className = segments[segmentsLength-1];
		//		EPackage ePackage = getEPackage(conjunction, packageName);
		//		assert ePackage != null;


		StringBuilder s = new StringBuilder();
		s.append("EResource *lhs = $$.pointer = malloc(sizeof(EResource));\n");
		List<AbstractElement> elements = conjunction.getElements();
		for (int i = 0; i < elements.size(); i++) {
			AbstractElement element = elements.get(i);
			if (element instanceof RuleCallAssignment) {
				EStructuralFeature eFeature = ((RuleCallAssignment)element).getFeature();
				Class<?> instanceClass = eFeature.getEType().getInstanceClass();
				s.append("lhs->s_");
				s.append(eFeature.getName());
				s.append(" = ");
				if (instanceClass == String.class) {
					s.append("$");
					s.append((i+1));
					s.append(".string");
				}
				else if (instanceClass == boolean.class) {
					s.append("booleanValueOf($");
					s.append((i+1));
					s.append(".string)");
				}
				else {
					s.append("$");
					s.append((i+1));
					s.append(".pointer");
				}
				s.append(";\n");
			}
			else if (element instanceof RuleCall) {
				s.append("/* lhs->s_" + ((RuleCall)element).getClass().getSimpleName() + " = $" + (i+1) + "; */\n");
			}
		}
		return s.toString();
	}

	private String emitCreateXMIAttribute(@NonNull AbstractRule rule, @NonNull String @NonNull [] segments) {
		String name = segments[2];
		return "createXMIAttribute(\"" + name + "\")";
	}

	private String emitCreateXMLNSAttribute(@NonNull AbstractRule rule, @NonNull String @NonNull [] segments) {
		return "createXMLNSAttribute()";
	}

	private String emitCreateXSIAttribute(@NonNull AbstractRule rule, @NonNull String @NonNull [] segments) {
		String name = segments[2];
		return "createXSIAttribute(\"" + name + "\")";
	}

	protected String emitDataTypeName(@NonNull EClassifier eType) {
		Class<?> instanceClass = eType.getInstanceClass();
		if (instanceClass == boolean.class) {
			return "boolean ";
		}
		if (instanceClass == double.class) {
			return "double ";
		}
		if (instanceClass == int.class) {
			return "int ";
		}
		if (instanceClass == String.class) {
			return "const char *";
		}
		return eType.getName() + " ";
	}

	protected String emitLabel(@NonNull Character keywordValue) {
		return emitLabel(keywordValue.toString());
	}

	protected @NonNull String emitLabel(@Nullable Character keywordValue, @NonNull String defaultString) {
		return keywordValue != null ? emitLabel(keywordValue.toString()) : defaultString;
	}

	protected String emitLabel(String keywordValue) {
		String label = keyword2label.get(keywordValue);
		if (label == null) {
			label = keywordValue;
		}
		if (label.matches(ALPHABETIC_REGEX)) {
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
					if (Character.isDigit(c) || Character.isAlphabetic(c)) {
						if (i != 0) {
							s.append("_");
						}
						s.append(c);
					}
					else {
						if (i == 0) {
							s.append("T");
						}
						s.append("_" + (int)c);
					}
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
			//			return "\'\\x27'";
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

	protected @NonNull List<@NonNull Character> getAlphaNumericCharacters(@NonNull List<@NonNull Character> characters) {
		List<@NonNull Character> allCharacters = new ArrayList<>();
		for (@NonNull Character character : characters) {
			if (Character.isAlphabetic(character) || Character.isDigit(character)) {
				allCharacters.add(character);
			}
		}
		return allCharacters;
	}

	protected @NonNull List<String> getCharacters(@NonNull CharacterRange characterRange) {
		List<String> allCharacters = new ArrayList<>();
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

	protected @NonNull List<@NonNull Character> getPunctuationCharacters(@NonNull List<@NonNull Character> characters) {
		List<@NonNull Character> allCharacters = new ArrayList<>();
		for (@NonNull Character character : characters) {
			if (!Character.isAlphabetic(character) && !Character.isDigit(character)) {
				allCharacters.add(character);
			}
		}
		return allCharacters;
	}

	/*	protected @NonNull List<String> getSortedAlphaChars(@NonNull Syntax syntax) {
		Set<Character> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = syntax.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if ((keywordValue.length() > 1) && keywordValue.matches(KEYWORD_REGEX)) {
					for (char c : keywordValue.toCharArray()) {
						allElements.add(c);
					}
				}
			}
		}
		List<String> sortedElements = new ArrayList<>();
		for (Character c : allElements) {
			sortedElements.add(c.toString());
		}
		Collections.sort(sortedElements);
		return sortedElements;
	} */

	protected @NonNull List<@NonNull Character> getSortedCharacterIndexes() {
		List<@NonNull Character> allCharacters = new ArrayList<>();
		for (char c = 0; c < 127; c++) {
			allCharacters.add(c);
		}
		return allCharacters;
	}

	protected @NonNull List<CharacterRange> getSortedCharacterRanges(@NonNull LexerGrammar lexerGrammar) {
		Set<CharacterRange> allElements = new HashSet<>();
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
		List<CharacterRange> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, characterRangeComparator);
		return sortedElements;
	}

	protected @NonNull List<Conjunction> getSortedConjunctions(@NonNull Disjunction disjunction) {
		List<Conjunction> sortedConjunctions = new ArrayList<>(disjunction.getConjunctions());
		Collections.sort(sortedConjunctions, conjunctionComparator);
		return sortedConjunctions;
	}

	protected @NonNull List<@NonNull Character> getSortedCharacters(@NonNull Iterable<@NonNull String> keywords) {
		Set<Character> allCharacters = new HashSet<>();
		for (String kwValue : keywords) {
			for (char kwChar : kwValue.toCharArray()) {
				allCharacters.add(kwChar);
			}
		}
		List<Character> sortedCharacters = new ArrayList<>(allCharacters);
		Collections.sort(sortedCharacters);
		return sortedCharacters;
	}

	protected @NonNull Map<@NonNull Integer, @Nullable Character> getSortedCharacterIndexes(
			@NonNull Iterable<@NonNull Character> characters, @Nullable List<@NonNull CharacterRange> characterRanges) {
		Map<@NonNull Integer, @Nullable Character> index2character = new HashMap<>();
		for (CharacterRange characterRange : characterRanges) {
			for (String rangeCharacters : getCharacters(characterRange)) {
				for (Character character : rangeCharacters.toCharArray()) {
					index2character.put(Integer.valueOf(character.charValue()), character);
				}
			}
		}
		for (Character character : characters) {
			index2character.put(Integer.valueOf(character.charValue()), character);
		}
		return index2character;
	}

	protected @NonNull List<String> getSortedIdentifierKWValues(@NonNull ParserGrammar parserGrammar) {
		Set<String> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if ((keywordValue.length() > 1) && keywordValue.matches(KEYWORD_REGEX)) {
					allElements.add(keywordValue);
				}
			}
		}
		List<String> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<EStructuralFeature> getSortedModelFeatures(@NonNull EClass eClass) {
		Set<EStructuralFeature> allElements = new HashSet<>();
		for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
			if (eFeature.isChangeable() && !eFeature.isDerived() && !eFeature.isTransient()) {
				allElements.add(eFeature);
			}
		}
		List<EStructuralFeature> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, NameUtil.ENamedElementComparator.INSTANCE);
		return sortedElements;
	}

	protected @NonNull List<EClass> getSortedModelTypes(@NonNull ParserGrammar parserGrammar) {
		Set<EClass> allElements = new HashSet<>();
		for (EClassifier eClassifier : EcorePackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				allElements.add((EClass) eClassifier);
			}
		}
		List<EClass> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, NameUtil.ENamedElementComparator.INSTANCE);
		return sortedElements;
	}

	protected @NonNull List<String> getSortedNonIdentifierKWValues(@NonNull ParserGrammar parserGrammar) {
		Set<String> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if ((keywordValue.length() <= 1) || !keywordValue.matches(KEYWORD_REGEX)) {
					allElements.add(keywordValue);
				}
			}
		}
		List<String> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	protected @NonNull List<TypedRule> getSortedParserRules(@NonNull ParserGrammar parserGrammar) {
		Set<TypedRule> allElements = new HashSet<>();
		for (TypedRule subRule : parserGrammar.getRules()) {
			if (!(subRule instanceof TerminalRule)) {
				allElements.add(subRule);
			}
		}
		List<TypedRule> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, ruleComparator);
		return sortedElements;
	}

	protected @NonNull List<Character> getSortedPunctChars(@NonNull LexerGrammar lexerGrammar, @NonNull ParserGrammar parserGrammar) {
		Set<String> characterTerminals = new HashSet<>();
		for (CharacterRange characterRange: getSortedCharacterRanges(lexerGrammar)) {
			for (String character: getCharacters(characterRange)) {
				characterTerminals.add(character);
			}
		}
		Set<Character> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = lexerGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				//				if (!isKeyword(keywordValue)) {
				for (char c : keywordValue.toCharArray()) {
					if (!characterTerminals.contains(String.valueOf(c))) {
						allElements.add(c);
					}
				}
				//				}
			}
		}
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				//				if (!isKeyword(keywordValue)) {
				for (char c : keywordValue.toCharArray()) {
					if (!characterTerminals.contains(String.valueOf(c))) {
						allElements.add(c);
					}
				}
				//				}
			}
		}
		List<CharacterRange> characterRanges = getSortedCharacterRanges(lexerGrammar);
		for (CharacterRange characterRange : characterRanges) {
			for (String rangeCharacters : getCharacters(characterRange)) {
				for (char c : rangeCharacters.toCharArray()) {
					allElements.add(c);
				}
			}
		}
		List<Character> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	}

	/*	protected @NonNull List<String> getSortedPunctValues(@NonNull ParserGrammar parserGrammar) {
		Set<String> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = parserGrammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Keyword) {
				String keywordValue = ((Keyword) eObject).getValue();
				if ((keywordValue.length() > 1) && !keywordValue.matches(KEYWORD_REGEX)) {
					allElements.add(keywordValue);
				}
			}
		}
		List<String> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements);
		return sortedElements;
	} */

	protected @NonNull List<UntypedRule> getSortedSubRules(@NonNull Iterable<UntypedRule> subRules) {
		List<UntypedRule> allElements = new ArrayList<>();
		for (UntypedRule subRule : subRules) {
			allElements.add(subRule);
		}
		Collections.sort(allElements, ruleComparator);
		return allElements;
	}

	protected @NonNull List<TerminalRule> getSortedTerminalRules(@NonNull Syntax syntax) {
		Set<TerminalRule> allElements = new HashSet<>();
		for (TreeIterator<EObject> tit = syntax.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof TerminalRule) {
				allElements.add((TerminalRule)eObject);
			}
		}
		List<TerminalRule> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, ruleComparator);
		return sortedElements;
	}

	//	protected boolean zisKeyword(@NonNull String keywordValue) {
	//		return (keywordValue.length() > 1) && keywordValue.matches(KEYWORD_REGEX);
	//	}

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
