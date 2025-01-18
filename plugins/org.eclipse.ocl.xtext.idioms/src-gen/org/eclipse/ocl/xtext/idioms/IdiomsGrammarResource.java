/*******************************************************************************
 * Copyright (c) 2015, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************
 * This code is 100% auto-generated
 * from: E:\GIT\org.eclipse.ocl\examples..\..\plugins\org.eclipse.ocl.xtext.idioms\src-gen\org\eclipse\ocl\xtext\idioms\Idioms.xtextbin
 * by: org.eclipse.ocl.examples.build.xtend.generateGrammar.xtend
 *
 * Do not edit it.
 *******************************************************************************/
package	org.eclipse.ocl.xtext.idioms;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.utilities.AbstractGrammarResource;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.ReferencedMetamodel;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * IdiomsGrammarResource provides a programmatically initialized org.eclipse.ocl.xtext.idioms.Idioms Grammar model avoiding
 * the speed limitations of the pre-Xtext 2.4 *.xmi models and the binary incompatibilities between differing *.xtextbin versions.
 * <p>
 * The grammar is immutable and is available as static INSTANCE and GRAMMAR fields.
 */
public class IdiomsGrammarResource extends AbstractGrammarResource
{
	private static final @NonNull Grammar G_Idioms = createGrammar("org.eclipse.ocl.xtext.idioms.Idioms");

	/**
	 *	The shared immutable instance of the org.eclipse.ocl.xtext.idioms.Idioms Grammar resource.
	 */
	public static final @NonNull IdiomsGrammarResource INSTANCE = new IdiomsGrammarResource();

	/**
	 *	The shared immutable instance of the org.eclipse.ocl.xtext.idioms.Idioms Grammar model.
	 */
	public static final @NonNull Grammar GRAMMAR = (Grammar)INSTANCE.getContents().get(0);

	/**
	 *	The name of the language supported by this grammar.
	 */
	public static final @NonNull String LANGUAGE_NAME = "org.eclipse.ocl.xtext.idioms.Idioms";

	protected IdiomsGrammarResource() {
		super(URI.createURI(LANGUAGE_NAME));
		List<EObject> contents = getContents();
		contents.add(_Idioms.initGrammar());
	}

	/*
	 * This class should be bound to org.eclipse.xtext.service.GrammarProvider.
	 */
	@Singleton
	public static class GrammarProvider extends org.eclipse.xtext.service.GrammarProvider
	{
		@Inject
		public GrammarProvider(Provider<XtextResourceSet> resourceSetProvider) {
			super(LANGUAGE_NAME, resourceSetProvider);
		}

		@Override
		public Grammar getGrammar(Object requestor) {
			return IdiomsGrammarResource.GRAMMAR;
		}
	}

	private static class _Idioms
	{
		private static final @NonNull ReferencedMetamodel MM = createReferencedMetamodel(org.eclipse.ocl.xtext.idioms.IdiomsPackage.eINSTANCE, null); // http://www.eclipse.org/ocl/2020/Idioms
		private static final @NonNull ReferencedMetamodel MM_ecore = createReferencedMetamodel(org.eclipse.emf.ecore.EcorePackage.eINSTANCE, "ecore"); // http://www.eclipse.org/emf/2002/Ecore
		private static final @NonNull ReferencedMetamodel MM_xtext = createReferencedMetamodel(org.eclipse.xtext.XtextPackage.eINSTANCE, "xtext"); // http://www.eclipse.org/2008/Xtext

		private static final @NonNull TerminalRule TR_ANY_OTHER = createTerminalRule("ANY_OTHER", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));
		private static final @NonNull TerminalRule TR_ID = createTerminalRule("ID", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));
		private static final @NonNull TerminalRule TR_INT = createTerminalRule("INT", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.EINT));
		private static final @NonNull TerminalRule TR_ML_COMMENT = createTerminalRule("ML_COMMENT", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));
		private static final @NonNull TerminalRule TR_SL_COMMENT = createTerminalRule("SL_COMMENT", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));
		private static final @NonNull TerminalRule TR_STRING = createTerminalRule("STRING", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));
		private static final @NonNull TerminalRule TR_WS = createTerminalRule("WS", createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING));

		private static void initTerminalRules() {
			TR_ANY_OTHER.setAlternatives(
				createWildcard());
			TR_ID.setAlternatives(
				createGroup(
					setCardinality("?", createKeyword("^")),
					createAlternatives(
						createCharacterRange(createKeyword("a"), createKeyword("z")),
						createCharacterRange(createKeyword("A"), createKeyword("Z")),
						createKeyword("_")),
					setCardinality("*", createAlternatives(
						createCharacterRange(createKeyword("a"), createKeyword("z")),
						createCharacterRange(createKeyword("A"), createKeyword("Z")),
						createKeyword("_"),
						createCharacterRange(createKeyword("0"), createKeyword("9"))))));
			TR_INT.setAlternatives(
				setCardinality("+", createCharacterRange(createKeyword("0"), createKeyword("9"))));
			TR_ML_COMMENT.setAlternatives(
				createGroup(
					createKeyword("/*"),
					createUntilToken(createKeyword("*/"))));
			TR_SL_COMMENT.setAlternatives(
				createGroup(
					createKeyword("//"),
					setCardinality("*", createNegatedToken(createAlternatives(
						createKeyword("\n"),
						createKeyword("\r")))),
					setCardinality("?", createGroup(
						setCardinality("?", createKeyword("\r")),
						createKeyword("\n")))));
			TR_STRING.setAlternatives(
				createAlternatives(
					createGroup(
						createKeyword("\""),
						setCardinality("*", createAlternatives(
							createGroup(
								createKeyword("\\"),
								createWildcard()),
							createNegatedToken(createAlternatives(
								createKeyword("\\"),
								createKeyword("\""))))),
						createKeyword("\"")),
					createGroup(
						createKeyword("\'"),
						setCardinality("*", createAlternatives(
							createGroup(
								createKeyword("\\"),
								createWildcard()),
							createNegatedToken(createAlternatives(
								createKeyword("\\"),
								createKeyword("\'"))))),
						createKeyword("\'"))));
			TR_WS.setAlternatives(
				setCardinality("+", createAlternatives(
					createKeyword(" "),
					createKeyword("\t"),
					createKeyword("\r"),
					createKeyword("\n"))));
		}

		private static final @NonNull ParserRule PR_AnyAssignmentLocator = createParserRule("AnyAssignmentLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.ANY_ASSIGNMENT_LOCATOR));
		private static final @NonNull ParserRule PR_AnyElementLocator = createParserRule("AnyElementLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.ANY_ELEMENT_LOCATOR));
		private static final @NonNull ParserRule PR_AssignmentLocator = createParserRule("AssignmentLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.ASSIGNMENT_LOCATOR));
		private static final @NonNull ParserRule PR_CustomSegment = createParserRule("CustomSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.CUSTOM_SEGMENT));
		private static final @NonNull ParserRule PR_EPackageDeclaration = createParserRule("EPackageDeclaration", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.EPACKAGE_DECLARATION));
		private static final @NonNull ParserRule PR_FinalLocator = createParserRule("FinalLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.FINAL_LOCATOR));
		private static final @NonNull ParserRule PR_GrammarDeclaration = createParserRule("GrammarDeclaration", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.GRAMMAR_DECLARATION));
		private static final @NonNull ParserRule PR_HalfNewLineSegment = createParserRule("HalfNewLineSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.HALF_NEW_LINE_SEGMENT));
		private static final @NonNull ParserRule PR_Idiom = createParserRule("Idiom", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOM));
		private static final @NonNull ParserRule PR_IdiomsImport = createParserRule("IdiomsImport", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOMS_IMPORT));
		private static final @NonNull ParserRule PR_IdiomsModel = createParserRule("IdiomsModel", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOMS_MODEL));
		private static final @NonNull ParserRule PR_KeywordLocator = createParserRule("KeywordLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.KEYWORD_LOCATOR));
		private static final @NonNull ParserRule PR_Locator = createParserRule("Locator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.LOCATOR));
		private static final @NonNull ParserRule PR_LocatorDeclaration = createParserRule("LocatorDeclaration", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.LOCATOR_DECLARATION));
		private static final @NonNull ParserRule PR_NewLineSegment = createParserRule("NewLineSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NEW_LINE_SEGMENT));
		private static final @NonNull ParserRule PR_NoNewLineSegment = createParserRule("NoNewLineSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NO_NEW_LINE_SEGMENT));
		private static final @NonNull ParserRule PR_NoSpaceSegment = createParserRule("NoSpaceSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NO_SPACE_SEGMENT));
		private static final @NonNull ParserRule PR_PopSegment = createParserRule("PopSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.POP_SEGMENT));
		private static final @NonNull ParserRule PR_PostCommentSegment = createParserRule("PostCommentSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.POST_COMMENT_SEGMENT));
		private static final @NonNull ParserRule PR_PreCommentSegment = createParserRule("PreCommentSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.PRE_COMMENT_SEGMENT));
		private static final @NonNull ParserRule PR_PushSegment = createParserRule("PushSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.PUSH_SEGMENT));
		private static final @NonNull ParserRule PR_ReferredLocator = createParserRule("ReferredLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.REFERRED_LOCATOR));
		private static final @NonNull ParserRule PR_ReferredSegment = createParserRule("ReferredSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.REFERRED_SEGMENT));
		private static final @NonNull ParserRule PR_ReturnsLocator = createParserRule("ReturnsLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.RETURNS_LOCATOR));
		private static final @NonNull ParserRule PR_RuleLocator = createParserRule("RuleLocator", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.RULE_LOCATOR));
		private static final @NonNull ParserRule PR_Segment = createParserRule("Segment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SEGMENT));
		private static final @NonNull ParserRule PR_SegmentDeclaration = createParserRule("SegmentDeclaration", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SEGMENT_DECLARATION));
		private static final @NonNull ParserRule PR_SoftNewLineSegment = createParserRule("SoftNewLineSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT));
		private static final @NonNull ParserRule PR_SoftSpaceSegment = createParserRule("SoftSpaceSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SOFT_SPACE_SEGMENT));
		private static final @NonNull ParserRule PR_StringSegment = createParserRule("StringSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.STRING_SEGMENT));
		private static final @NonNull ParserRule PR_SubIdiom = createParserRule("SubIdiom", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SUB_IDIOM));
		private static final @NonNull ParserRule PR_ValueSegment = createParserRule("ValueSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.VALUE_SEGMENT));
		private static final @NonNull ParserRule PR_WrapAnchorSegment = createParserRule("WrapAnchorSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT));
		private static final @NonNull ParserRule PR_WrapBeginAllSegment = createParserRule("WrapBeginAllSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_BEGIN_ALL_SEGMENT));
		private static final @NonNull ParserRule PR_WrapBeginSomeSegment = createParserRule("WrapBeginSomeSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT));
		private static final @NonNull ParserRule PR_WrapEndSegment = createParserRule("WrapEndSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_END_SEGMENT));
		private static final @NonNull ParserRule PR_WrapHereSegment = createParserRule("WrapHereSegment", createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_HERE_SEGMENT));

		private static void initParserRules() {
			PR_AnyAssignmentLocator.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.ANY_ASSIGNMENT_LOCATOR)),
					createKeyword("any-assignment")));
			PR_AnyElementLocator.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.ANY_ELEMENT_LOCATOR)),
					createKeyword("any-element")));
			PR_AssignmentLocator.setAlternatives(
				createGroup(
					createKeyword("assignment"),
					setCardinality("?", createGroup(
						setCardinality("?", createGroup(
							createAssignment("ePackage", "=", createCrossReference(
								createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE), createRuleCall(TR_ID))),
							createKeyword("::"))),
						createAssignment("eClass", "=", createCrossReference(
							createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS), createRuleCall(TR_ID))),
						createKeyword("::"))),
					createAssignment("eStructuralFeature", "=", createCrossReference(
						createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE), createRuleCall(TR_ID)))));
			PR_CustomSegment.setAlternatives(
				createGroup(
					createKeyword("custom"),
					createAssignment("supportClassName", "=", createRuleCall(TR_STRING))));
			PR_EPackageDeclaration.setAlternatives(
				createGroup(
					createKeyword("import"),
					createAssignment("ePackage", "=", createCrossReference(
						createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE), createRuleCall(TR_STRING))),
					setCardinality("?", createGroup(
						createKeyword("as"),
						createAssignment("as", "=", createRuleCall(TR_ID)))),
					setCardinality("?", createKeyword(";"))));
			PR_FinalLocator.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.FINAL_LOCATOR)),
					createKeyword("final")));
			PR_GrammarDeclaration.setAlternatives(
				createGroup(
					createKeyword("grammar"),
					createAssignment("grammar", "=", createCrossReference(
						createTypeRef(MM_xtext, org.eclipse.xtext.XtextPackage.Literals.GRAMMAR), createRuleCall(TR_STRING))),
					setCardinality("?", createGroup(
						createKeyword("as"),
						createAssignment("as", "=", createRuleCall(TR_ID)))),
					setCardinality("?", createKeyword(";"))));
			PR_HalfNewLineSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.HALF_NEW_LINE_SEGMENT)),
					createKeyword("half-new-line")));
			PR_Idiom.setAlternatives(
				createGroup(
					setCardinality("?", createAssignment("mixin", "?=", createKeyword("mixin"))),
					createKeyword("idiom"),
					createAssignment("name", "=", createRuleCall(TR_ID)),
					setCardinality("?", createGroup(
						createKeyword("for"),
						setCardinality("?", createGroup(
							createAssignment("forEPackage", "=", createCrossReference(
								createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE), createRuleCall(TR_ID))),
							createKeyword("::"))),
						createAssignment("forEClass", "=", createCrossReference(
							createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS), createRuleCall(TR_ID))))),
					setCardinality("?", createGroup(
						createKeyword("in"),
						createAssignment("inRuleRegex", "=", createRuleCall(TR_STRING)))),
					createAlternatives(
						createAssignment("ownedSubIdioms", "+=", createRuleCall(PR_SubIdiom)),
						createGroup(
							createKeyword("{"),
							setCardinality("*", createAssignment("ownedSubIdioms", "+=", createRuleCall(PR_SubIdiom))),
							createKeyword("}")))));
			PR_IdiomsImport.setAlternatives(
				createGroup(
					createKeyword("with"),
					createAssignment("idiomsModel", "=", createCrossReference(
						createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOMS_MODEL), createRuleCall(TR_STRING))),
					setCardinality("?", createGroup(
						createKeyword("as"),
						createAssignment("as", "=", createRuleCall(TR_ID)))),
					setCardinality("?", createKeyword(";"))));
			PR_IdiomsModel.setAlternatives(
				createGroup(
					createKeyword("model"),
					createAssignment("names", "+=", createRuleCall(TR_ID)),
					setCardinality("*", createGroup(
						createKeyword("."),
						createAssignment("names", "+=", createRuleCall(TR_ID)))),
					setCardinality("*", createAlternatives(
						createAssignment("ownedWiths", "+=", createRuleCall(PR_IdiomsImport)),
						createAssignment("ownedImportDeclarations", "+=", createRuleCall(PR_EPackageDeclaration)),
						createAssignment("ownedGrammarDeclarations", "+=", createRuleCall(PR_GrammarDeclaration)))),
					setCardinality("*", createAlternatives(
						createAssignment("ownedLocatorDeclarations", "+=", createRuleCall(PR_LocatorDeclaration)),
						createAssignment("ownedSegmentDeclarations", "+=", createRuleCall(PR_SegmentDeclaration)),
						createAssignment("ownedIdioms", "+=", createRuleCall(PR_Idiom))))));
			PR_KeywordLocator.setAlternatives(
				createAssignment("string", "=", createRuleCall(TR_STRING)));
			PR_Locator.setAlternatives(
				createAlternatives(
					createRuleCall(PR_AnyAssignmentLocator),
					createRuleCall(PR_AnyElementLocator),
					createRuleCall(PR_AssignmentLocator),
					createRuleCall(PR_FinalLocator),
					createRuleCall(PR_KeywordLocator),
					createRuleCall(PR_ReferredLocator),
					createRuleCall(PR_ReturnsLocator),
					createRuleCall(PR_RuleLocator)));
			PR_LocatorDeclaration.setAlternatives(
				createGroup(
					createKeyword("locator"),
					createAssignment("name", "=", createRuleCall(TR_ID)),
					createAssignment("ownedLocator", "=", createRuleCall(PR_Locator)),
					createKeyword(";")));
			PR_NewLineSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NEW_LINE_SEGMENT)),
					createKeyword("new-line")));
			PR_NoNewLineSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NO_NEW_LINE_SEGMENT)),
					createKeyword("no-new-line")));
			PR_NoSpaceSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.NO_SPACE_SEGMENT)),
					createKeyword("no-space")));
			PR_PopSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.POP_SEGMENT)),
					createKeyword("pop")));
			PR_PostCommentSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.POST_COMMENT_SEGMENT)),
					createKeyword("post-comment")));
			PR_PreCommentSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.PRE_COMMENT_SEGMENT)),
					createKeyword("pre-comment")));
			PR_PushSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.PUSH_SEGMENT)),
					createKeyword("push")));
			PR_ReferredLocator.setAlternatives(
				createGroup(
					setCardinality("?", createGroup(
						createAssignment("idiomsModel", "=", createCrossReference(
							createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOMS_MODEL), createRuleCall(TR_ID))),
						createKeyword("::"))),
					createAssignment("locatorDeclaration", "=", createCrossReference(
						createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.LOCATOR_DECLARATION), createRuleCall(TR_ID)))));
			PR_ReferredSegment.setAlternatives(
				createGroup(
					setCardinality("?", createGroup(
						createAssignment("idiomsModel", "=", createCrossReference(
							createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.IDIOMS_MODEL), createRuleCall(TR_ID))),
						createKeyword("::"))),
					createAssignment("segmentDeclaration", "=", createCrossReference(
						createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SEGMENT_DECLARATION), createRuleCall(TR_ID)))));
			PR_ReturnsLocator.setAlternatives(
				createGroup(
					createKeyword("returns"),
					setCardinality("?", createGroup(
						createAssignment("ePackage", "=", createCrossReference(
							createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE), createRuleCall(TR_ID))),
						createKeyword("::"))),
					createAssignment("eClass", "=", createCrossReference(
						createTypeRef(MM_ecore, org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS), createRuleCall(TR_ID)))));
			PR_RuleLocator.setAlternatives(
				createGroup(
					createKeyword("rule"),
					setCardinality("?", createGroup(
						createAssignment("referredGrammar", "=", createCrossReference(
							createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.GRAMMAR_DECLARATION), createRuleCall(TR_ID))),
						createKeyword("::"))),
					createAssignment("referredRule", "=", createCrossReference(
						createTypeRef(MM_xtext, org.eclipse.xtext.XtextPackage.Literals.ABSTRACT_RULE), createRuleCall(TR_ID)))));
			PR_Segment.setAlternatives(
				createAlternatives(
					createRuleCall(PR_CustomSegment),
					createRuleCall(PR_HalfNewLineSegment),
					createRuleCall(PR_NewLineSegment),
					createRuleCall(PR_NoNewLineSegment),
					createRuleCall(PR_NoSpaceSegment),
					createRuleCall(PR_PopSegment),
					createRuleCall(PR_PostCommentSegment),
					createRuleCall(PR_PreCommentSegment),
					createRuleCall(PR_PushSegment),
					createRuleCall(PR_SoftNewLineSegment),
					createRuleCall(PR_SoftSpaceSegment),
					createRuleCall(PR_StringSegment),
					createRuleCall(PR_ValueSegment),
					createRuleCall(PR_WrapAnchorSegment),
					createRuleCall(PR_WrapBeginAllSegment),
					createRuleCall(PR_WrapBeginSomeSegment),
					createRuleCall(PR_WrapEndSegment),
					createRuleCall(PR_WrapHereSegment)));
			PR_SegmentDeclaration.setAlternatives(
				createGroup(
					createKeyword("segment"),
					createAssignment("name", "=", createRuleCall(TR_ID)),
					createAssignment("ownedSegment", "=", createRuleCall(PR_Segment)),
					createKeyword(";")));
			PR_SoftNewLineSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT)),
					createKeyword("soft-new-line")));
			PR_SoftSpaceSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.SOFT_SPACE_SEGMENT)),
					createKeyword("soft-space")));
			PR_StringSegment.setAlternatives(
				createGroup(
					createKeyword("string"),
					createAssignment("string", "=", createRuleCall(TR_STRING)),
					setCardinality("?", createAssignment("printable", "?=", createKeyword("printable")))));
			PR_SubIdiom.setAlternatives(
				createGroup(
					createKeyword("at"),
					setCardinality("?", createAlternatives(
						createAssignment("all", "?=", createKeyword("all")),
						createKeyword("each"))),
					createAssignment("ownedLocator", "=", createRuleCall(PR_Locator)),
					setCardinality("?", createGroup(
						createKeyword("do"),
						setCardinality("*", createAssignment("ownedSegments", "+=", createAlternatives(
							createRuleCall(PR_Segment),
							createRuleCall(PR_ReferredSegment)))))),
					createKeyword(";")));
			PR_ValueSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.VALUE_SEGMENT)),
					createKeyword("value")));
			PR_WrapAnchorSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT)),
					createKeyword("wrap-anchor")));
			PR_WrapBeginAllSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_BEGIN_ALL_SEGMENT)),
					createKeyword("wrap-begin-all")));
			PR_WrapBeginSomeSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT)),
					createKeyword("wrap-begin-some")));
			PR_WrapEndSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_END_SEGMENT)),
					createKeyword("wrap-end")));
			PR_WrapHereSegment.setAlternatives(
				createGroup(
					createAction(null, null, createTypeRef(MM, org.eclipse.ocl.xtext.idioms.IdiomsPackage.Literals.WRAP_HERE_SEGMENT)),
					createKeyword("wrap-here")));
		}

		private static @NonNull Grammar initGrammar() {
			initTerminalRules();
			initParserRules();
			Grammar grammar = G_Idioms;
			grammar.setDefinesHiddenTokens(true);
			{
				List<AbstractMetamodelDeclaration> metamodelDeclarations = grammar.getMetamodelDeclarations();
				metamodelDeclarations.add(MM_ecore);
				metamodelDeclarations.add(MM_xtext);
				metamodelDeclarations.add(MM);
			}
			{
				List<AbstractRule> rules = grammar.getRules();
				rules.add(PR_IdiomsModel);
				rules.add(PR_EPackageDeclaration);
				rules.add(PR_GrammarDeclaration);
				rules.add(PR_IdiomsImport);
				rules.add(PR_LocatorDeclaration);
				rules.add(PR_Locator);
				rules.add(PR_AnyAssignmentLocator);
				rules.add(PR_AnyElementLocator);
				rules.add(PR_AssignmentLocator);
				rules.add(PR_FinalLocator);
				rules.add(PR_KeywordLocator);
				rules.add(PR_ReturnsLocator);
				rules.add(PR_ReferredLocator);
				rules.add(PR_RuleLocator);
				rules.add(PR_SegmentDeclaration);
				rules.add(PR_Segment);
				rules.add(PR_CustomSegment);
				rules.add(PR_HalfNewLineSegment);
				rules.add(PR_NewLineSegment);
				rules.add(PR_NoNewLineSegment);
				rules.add(PR_NoSpaceSegment);
				rules.add(PR_PopSegment);
				rules.add(PR_PostCommentSegment);
				rules.add(PR_PreCommentSegment);
				rules.add(PR_PushSegment);
				rules.add(PR_SoftNewLineSegment);
				rules.add(PR_SoftSpaceSegment);
				rules.add(PR_StringSegment);
				rules.add(PR_ValueSegment);
				rules.add(PR_WrapAnchorSegment);
				rules.add(PR_WrapBeginAllSegment);
				rules.add(PR_WrapBeginSomeSegment);
				rules.add(PR_WrapEndSegment);
				rules.add(PR_WrapHereSegment);
				rules.add(PR_ReferredSegment);
				rules.add(PR_Idiom);
				rules.add(PR_SubIdiom);
				rules.add(TR_ID);
				rules.add(TR_INT);
				rules.add(TR_STRING);
				rules.add(TR_ML_COMMENT);
				rules.add(TR_SL_COMMENT);
				rules.add(TR_WS);
				rules.add(TR_ANY_OTHER);
			}
			{
				List<AbstractRule> hiddenTokens = grammar.getHiddenTokens();
				hiddenTokens.add(TR_WS);
				hiddenTokens.add(TR_ML_COMMENT);
				hiddenTokens.add(TR_SL_COMMENT);
			}
			return grammar;
		}
	}
}
