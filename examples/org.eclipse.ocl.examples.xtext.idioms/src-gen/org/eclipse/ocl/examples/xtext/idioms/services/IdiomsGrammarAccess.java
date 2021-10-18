/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.idioms.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class IdiomsGrammarAccess extends AbstractElementFinder.AbstractGrammarElementFinder {

	public class IdiomsModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.IdiomsModel");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cModelKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNamesAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNamesIDTerminalRuleCall_1_0 = (RuleCall)cNamesAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cNamesAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cNamesIDTerminalRuleCall_2_1_0 = (RuleCall)cNamesAssignment_2_1.eContents().get(0);
		private final Assignment cOwnedWithsAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cOwnedWithsIdiomsImportParserRuleCall_3_0 = (RuleCall)cOwnedWithsAssignment_3.eContents().get(0);
		private final Assignment cOwnedImportsAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cOwnedImportsEPackageImportParserRuleCall_4_0 = (RuleCall)cOwnedImportsAssignment_4.eContents().get(0);
		private final Alternatives cAlternatives_5 = (Alternatives)cGroup.eContents().get(5);
		private final Assignment cOwnedLocatorDeclarationsAssignment_5_0 = (Assignment)cAlternatives_5.eContents().get(0);
		private final RuleCall cOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0 = (RuleCall)cOwnedLocatorDeclarationsAssignment_5_0.eContents().get(0);
		private final Assignment cOwnedSegmentDeclarationsAssignment_5_1 = (Assignment)cAlternatives_5.eContents().get(1);
		private final RuleCall cOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0 = (RuleCall)cOwnedSegmentDeclarationsAssignment_5_1.eContents().get(0);
		private final Assignment cOwnedIdiomsAssignment_5_2 = (Assignment)cAlternatives_5.eContents().get(2);
		private final RuleCall cOwnedIdiomsIdiomParserRuleCall_5_2_0 = (RuleCall)cOwnedIdiomsAssignment_5_2.eContents().get(0);

		//IdiomsModel:
		//    'model' names+=ID ('.' names+=ID)* ownedWiths+=IdiomsImport* ownedImports+=EPackageImport* (ownedLocatorDeclarations+=LocatorDeclaration | ownedSegmentDeclarations+=SegmentDeclaration | ownedIdioms+=Idiom)* ;
		@Override public ParserRule getRule() { return rule; }

		//'model' names+=ID ('.' names+=ID)* ownedWiths+=IdiomsImport* ownedImports+=EPackageImport* (ownedLocatorDeclarations+=LocatorDeclaration | ownedSegmentDeclarations+=SegmentDeclaration | ownedIdioms+=Idiom)*
		public Group getGroup() { return cGroup; }

		//'model'
		public Keyword getModelKeyword_0() { return cModelKeyword_0; }

		//names+=ID
		public Assignment getNamesAssignment_1() { return cNamesAssignment_1; }

		//ID
		public RuleCall getNamesIDTerminalRuleCall_1_0() { return cNamesIDTerminalRuleCall_1_0; }

		//('.' names+=ID)*
		public Group getGroup_2() { return cGroup_2; }

		//'.'
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }

		//names+=ID
		public Assignment getNamesAssignment_2_1() { return cNamesAssignment_2_1; }

		//ID
		public RuleCall getNamesIDTerminalRuleCall_2_1_0() { return cNamesIDTerminalRuleCall_2_1_0; }

		//ownedWiths+=IdiomsImport*
		public Assignment getOwnedWithsAssignment_3() { return cOwnedWithsAssignment_3; }

		//IdiomsImport
		public RuleCall getOwnedWithsIdiomsImportParserRuleCall_3_0() { return cOwnedWithsIdiomsImportParserRuleCall_3_0; }

		//ownedImports+=EPackageImport*
		public Assignment getOwnedImportsAssignment_4() { return cOwnedImportsAssignment_4; }

		//EPackageImport
		public RuleCall getOwnedImportsEPackageImportParserRuleCall_4_0() { return cOwnedImportsEPackageImportParserRuleCall_4_0; }

		//(ownedLocatorDeclarations+=LocatorDeclaration | ownedSegmentDeclarations+=SegmentDeclaration | ownedIdioms+=Idiom)*
		public Alternatives getAlternatives_5() { return cAlternatives_5; }

		//ownedLocatorDeclarations+=LocatorDeclaration
		public Assignment getOwnedLocatorDeclarationsAssignment_5_0() { return cOwnedLocatorDeclarationsAssignment_5_0; }

		//LocatorDeclaration
		public RuleCall getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0() { return cOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0; }

		//ownedSegmentDeclarations+=SegmentDeclaration
		public Assignment getOwnedSegmentDeclarationsAssignment_5_1() { return cOwnedSegmentDeclarationsAssignment_5_1; }

		//SegmentDeclaration
		public RuleCall getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0() { return cOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0; }

		//ownedIdioms+=Idiom
		public Assignment getOwnedIdiomsAssignment_5_2() { return cOwnedIdiomsAssignment_5_2; }

		//Idiom
		public RuleCall getOwnedIdiomsIdiomParserRuleCall_5_2_0() { return cOwnedIdiomsIdiomParserRuleCall_5_2_0; }
	}
	public class EPackageImportElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.EPackageImport");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cImportKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cEPackageAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cEPackageEPackageCrossReference_1_0 = (CrossReference)cEPackageAssignment_1.eContents().get(0);
		private final RuleCall cEPackageEPackageSTRINGTerminalRuleCall_1_0_1 = (RuleCall)cEPackageEPackageCrossReference_1_0.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cAsKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cAsAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cAsIDTerminalRuleCall_2_1_0 = (RuleCall)cAsAssignment_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//EPackageImport:
		//    'import' ePackage=[ecore::EPackage|STRING] ('as' ^as=ID)? ';'?;
		@Override public ParserRule getRule() { return rule; }

		//'import' ePackage=[ecore::EPackage|STRING] ('as' ^as=ID)? ';'?
		public Group getGroup() { return cGroup; }

		//'import'
		public Keyword getImportKeyword_0() { return cImportKeyword_0; }

		//ePackage=[ecore::EPackage|STRING]
		public Assignment getEPackageAssignment_1() { return cEPackageAssignment_1; }

		//[ecore::EPackage|STRING]
		public CrossReference getEPackageEPackageCrossReference_1_0() { return cEPackageEPackageCrossReference_1_0; }

		//STRING
		public RuleCall getEPackageEPackageSTRINGTerminalRuleCall_1_0_1() { return cEPackageEPackageSTRINGTerminalRuleCall_1_0_1; }

		//('as' ^as=ID)?
		public Group getGroup_2() { return cGroup_2; }

		//'as'
		public Keyword getAsKeyword_2_0() { return cAsKeyword_2_0; }

		//^as=ID
		public Assignment getAsAssignment_2_1() { return cAsAssignment_2_1; }

		//ID
		public RuleCall getAsIDTerminalRuleCall_2_1_0() { return cAsIDTerminalRuleCall_2_1_0; }

		//';'?
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class IdiomsImportElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.IdiomsImport");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWithKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cIdiomsModelAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cIdiomsModelIdiomsModelCrossReference_1_0 = (CrossReference)cIdiomsModelAssignment_1.eContents().get(0);
		private final RuleCall cIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1 = (RuleCall)cIdiomsModelIdiomsModelCrossReference_1_0.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cAsKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cAsAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cAsIDTerminalRuleCall_2_1_0 = (RuleCall)cAsAssignment_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//IdiomsImport:
		//    'with' idiomsModel=[IdiomsModel|STRING] ('as' ^as=ID)? ';'?;
		@Override public ParserRule getRule() { return rule; }

		//'with' idiomsModel=[IdiomsModel|STRING] ('as' ^as=ID)? ';'?
		public Group getGroup() { return cGroup; }

		//'with'
		public Keyword getWithKeyword_0() { return cWithKeyword_0; }

		//idiomsModel=[IdiomsModel|STRING]
		public Assignment getIdiomsModelAssignment_1() { return cIdiomsModelAssignment_1; }

		//[IdiomsModel|STRING]
		public CrossReference getIdiomsModelIdiomsModelCrossReference_1_0() { return cIdiomsModelIdiomsModelCrossReference_1_0; }

		//STRING
		public RuleCall getIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1() { return cIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1; }

		//('as' ^as=ID)?
		public Group getGroup_2() { return cGroup_2; }

		//'as'
		public Keyword getAsKeyword_2_0() { return cAsKeyword_2_0; }

		//^as=ID
		public Assignment getAsAssignment_2_1() { return cAsAssignment_2_1; }

		//ID
		public RuleCall getAsIDTerminalRuleCall_2_1_0() { return cAsIDTerminalRuleCall_2_1_0; }

		//';'?
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class LocatorDeclarationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.LocatorDeclaration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLocatorKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cOwnedLocatorAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cOwnedLocatorLocatorParserRuleCall_2_0 = (RuleCall)cOwnedLocatorAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//LocatorDeclaration:
		//    'locator' name=ID  ownedLocator=Locator ';';
		@Override public ParserRule getRule() { return rule; }

		//'locator' name=ID  ownedLocator=Locator ';'
		public Group getGroup() { return cGroup; }

		//'locator'
		public Keyword getLocatorKeyword_0() { return cLocatorKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//ownedLocator=Locator
		public Assignment getOwnedLocatorAssignment_2() { return cOwnedLocatorAssignment_2; }

		//Locator
		public RuleCall getOwnedLocatorLocatorParserRuleCall_2_0() { return cOwnedLocatorLocatorParserRuleCall_2_0; }

		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class LocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.Locator");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cAnyAssignmentLocatorParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cAnyElementLocatorParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cAssignmentLocatorParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cFinalLocatorParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cKeywordLocatorParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cReferredLocatorParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cReturnsLocatorParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);

		//Locator:
		//    AnyAssignmentLocator | AnyElementLocator | AssignmentLocator | FinalLocator | KeywordLocator | ReferredLocator | ReturnsLocator;
		@Override public ParserRule getRule() { return rule; }

		//AnyAssignmentLocator | AnyElementLocator | AssignmentLocator | FinalLocator | KeywordLocator | ReferredLocator | ReturnsLocator
		public Alternatives getAlternatives() { return cAlternatives; }

		//AnyAssignmentLocator
		public RuleCall getAnyAssignmentLocatorParserRuleCall_0() { return cAnyAssignmentLocatorParserRuleCall_0; }

		//AnyElementLocator
		public RuleCall getAnyElementLocatorParserRuleCall_1() { return cAnyElementLocatorParserRuleCall_1; }

		//AssignmentLocator
		public RuleCall getAssignmentLocatorParserRuleCall_2() { return cAssignmentLocatorParserRuleCall_2; }

		//FinalLocator
		public RuleCall getFinalLocatorParserRuleCall_3() { return cFinalLocatorParserRuleCall_3; }

		//KeywordLocator
		public RuleCall getKeywordLocatorParserRuleCall_4() { return cKeywordLocatorParserRuleCall_4; }

		//ReferredLocator
		public RuleCall getReferredLocatorParserRuleCall_5() { return cReferredLocatorParserRuleCall_5; }

		//ReturnsLocator
		public RuleCall getReturnsLocatorParserRuleCall_6() { return cReturnsLocatorParserRuleCall_6; }
	}
	public class AnyAssignmentLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.AnyAssignmentLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cAnyAssignmentLocatorAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cAnyAssignmentKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//AnyAssignmentLocator:
		//    {AnyAssignmentLocator} 'any-assignment';
		@Override public ParserRule getRule() { return rule; }

		//{AnyAssignmentLocator} 'any-assignment'
		public Group getGroup() { return cGroup; }

		//{AnyAssignmentLocator}
		public Action getAnyAssignmentLocatorAction_0() { return cAnyAssignmentLocatorAction_0; }

		//'any-assignment'
		public Keyword getAnyAssignmentKeyword_1() { return cAnyAssignmentKeyword_1; }
	}
	public class AnyElementLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.AnyElementLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cAnyElementLocatorAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cAnyElementKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//AnyElementLocator:
		//    {AnyElementLocator} 'any-element';
		@Override public ParserRule getRule() { return rule; }

		//{AnyElementLocator} 'any-element'
		public Group getGroup() { return cGroup; }

		//{AnyElementLocator}
		public Action getAnyElementLocatorAction_0() { return cAnyElementLocatorAction_0; }

		//'any-element'
		public Keyword getAnyElementKeyword_1() { return cAnyElementKeyword_1; }
	}
	public class AssignmentLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.AssignmentLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAssignmentKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cGroup_1.eContents().get(0);
		private final Assignment cEPackageAssignment_1_0_0 = (Assignment)cGroup_1_0.eContents().get(0);
		private final CrossReference cEPackageEPackageCrossReference_1_0_0_0 = (CrossReference)cEPackageAssignment_1_0_0.eContents().get(0);
		private final RuleCall cEPackageEPackageIDTerminalRuleCall_1_0_0_0_1 = (RuleCall)cEPackageEPackageCrossReference_1_0_0_0.eContents().get(1);
		private final Keyword cColonColonKeyword_1_0_1 = (Keyword)cGroup_1_0.eContents().get(1);
		private final Assignment cEClassAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final CrossReference cEClassEClassCrossReference_1_1_0 = (CrossReference)cEClassAssignment_1_1.eContents().get(0);
		private final RuleCall cEClassEClassIDTerminalRuleCall_1_1_0_1 = (RuleCall)cEClassEClassCrossReference_1_1_0.eContents().get(1);
		private final Keyword cColonColonKeyword_1_2 = (Keyword)cGroup_1.eContents().get(2);
		private final Assignment cEStructuralFeatureAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cEStructuralFeatureEStructuralFeatureCrossReference_2_0 = (CrossReference)cEStructuralFeatureAssignment_2.eContents().get(0);
		private final RuleCall cEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1 = (RuleCall)cEStructuralFeatureEStructuralFeatureCrossReference_2_0.eContents().get(1);

		//AssignmentLocator:
		//    'assignment' ((ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID] '::')? eStructuralFeature=[ecore::EStructuralFeature];
		@Override public ParserRule getRule() { return rule; }

		//'assignment' ((ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID] '::')? eStructuralFeature=[ecore::EStructuralFeature]
		public Group getGroup() { return cGroup; }

		//'assignment'
		public Keyword getAssignmentKeyword_0() { return cAssignmentKeyword_0; }

		//((ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID] '::')?
		public Group getGroup_1() { return cGroup_1; }

		//(ePackage=[ecore::EPackage|ID] '::')?
		public Group getGroup_1_0() { return cGroup_1_0; }

		//ePackage=[ecore::EPackage|ID]
		public Assignment getEPackageAssignment_1_0_0() { return cEPackageAssignment_1_0_0; }

		//[ecore::EPackage|ID]
		public CrossReference getEPackageEPackageCrossReference_1_0_0_0() { return cEPackageEPackageCrossReference_1_0_0_0; }

		//ID
		public RuleCall getEPackageEPackageIDTerminalRuleCall_1_0_0_0_1() { return cEPackageEPackageIDTerminalRuleCall_1_0_0_0_1; }

		//'::'
		public Keyword getColonColonKeyword_1_0_1() { return cColonColonKeyword_1_0_1; }

		//eClass=[ecore::EClass|ID]
		public Assignment getEClassAssignment_1_1() { return cEClassAssignment_1_1; }

		//[ecore::EClass|ID]
		public CrossReference getEClassEClassCrossReference_1_1_0() { return cEClassEClassCrossReference_1_1_0; }

		//ID
		public RuleCall getEClassEClassIDTerminalRuleCall_1_1_0_1() { return cEClassEClassIDTerminalRuleCall_1_1_0_1; }

		//'::'
		public Keyword getColonColonKeyword_1_2() { return cColonColonKeyword_1_2; }

		//eStructuralFeature=[ecore::EStructuralFeature]
		public Assignment getEStructuralFeatureAssignment_2() { return cEStructuralFeatureAssignment_2; }

		//[ecore::EStructuralFeature]
		public CrossReference getEStructuralFeatureEStructuralFeatureCrossReference_2_0() { return cEStructuralFeatureEStructuralFeatureCrossReference_2_0; }

		//ID
		public RuleCall getEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1() { return cEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1; }
	}
	public class FinalLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.FinalLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cFinalLocatorAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cFinalKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//FinalLocator:
		//    {FinalLocator} 'final';
		@Override public ParserRule getRule() { return rule; }

		//{FinalLocator} 'final'
		public Group getGroup() { return cGroup; }

		//{FinalLocator}
		public Action getFinalLocatorAction_0() { return cFinalLocatorAction_0; }

		//'final'
		public Keyword getFinalKeyword_1() { return cFinalKeyword_1; }
	}
	public class KeywordLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.KeywordLocator");
		private final Assignment cStringAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cStringSTRINGTerminalRuleCall_0 = (RuleCall)cStringAssignment.eContents().get(0);

		//KeywordLocator:
		//    string=STRING;
		@Override public ParserRule getRule() { return rule; }

		//string=STRING
		public Assignment getStringAssignment() { return cStringAssignment; }

		//STRING
		public RuleCall getStringSTRINGTerminalRuleCall_0() { return cStringSTRINGTerminalRuleCall_0; }
	}
	public class ReturnsLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ReturnsLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cReturnsKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cEPackageAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final CrossReference cEPackageEPackageCrossReference_1_0_0 = (CrossReference)cEPackageAssignment_1_0.eContents().get(0);
		private final RuleCall cEPackageEPackageIDTerminalRuleCall_1_0_0_1 = (RuleCall)cEPackageEPackageCrossReference_1_0_0.eContents().get(1);
		private final Keyword cColonColonKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cEClassAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cEClassEClassCrossReference_2_0 = (CrossReference)cEClassAssignment_2.eContents().get(0);
		private final RuleCall cEClassEClassIDTerminalRuleCall_2_0_1 = (RuleCall)cEClassEClassCrossReference_2_0.eContents().get(1);

		//ReturnsLocator:
		//    'returns' (ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID];
		@Override public ParserRule getRule() { return rule; }

		//'returns' (ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID]
		public Group getGroup() { return cGroup; }

		//'returns'
		public Keyword getReturnsKeyword_0() { return cReturnsKeyword_0; }

		//(ePackage=[ecore::EPackage|ID] '::')?
		public Group getGroup_1() { return cGroup_1; }

		//ePackage=[ecore::EPackage|ID]
		public Assignment getEPackageAssignment_1_0() { return cEPackageAssignment_1_0; }

		//[ecore::EPackage|ID]
		public CrossReference getEPackageEPackageCrossReference_1_0_0() { return cEPackageEPackageCrossReference_1_0_0; }

		//ID
		public RuleCall getEPackageEPackageIDTerminalRuleCall_1_0_0_1() { return cEPackageEPackageIDTerminalRuleCall_1_0_0_1; }

		//'::'
		public Keyword getColonColonKeyword_1_1() { return cColonColonKeyword_1_1; }

		//eClass=[ecore::EClass|ID]
		public Assignment getEClassAssignment_2() { return cEClassAssignment_2; }

		//[ecore::EClass|ID]
		public CrossReference getEClassEClassCrossReference_2_0() { return cEClassEClassCrossReference_2_0; }

		//ID
		public RuleCall getEClassEClassIDTerminalRuleCall_2_0_1() { return cEClassEClassIDTerminalRuleCall_2_0_1; }
	}
	public class ReferredLocatorElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ReferredLocator");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cGroup.eContents().get(0);
		private final Assignment cIdiomsModelAssignment_0_0 = (Assignment)cGroup_0.eContents().get(0);
		private final CrossReference cIdiomsModelIdiomsModelCrossReference_0_0_0 = (CrossReference)cIdiomsModelAssignment_0_0.eContents().get(0);
		private final RuleCall cIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1 = (RuleCall)cIdiomsModelIdiomsModelCrossReference_0_0_0.eContents().get(1);
		private final Keyword cColonColonKeyword_0_1 = (Keyword)cGroup_0.eContents().get(1);
		private final Assignment cLocatorDeclarationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cLocatorDeclarationLocatorDeclarationCrossReference_1_0 = (CrossReference)cLocatorDeclarationAssignment_1.eContents().get(0);
		private final RuleCall cLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1 = (RuleCall)cLocatorDeclarationLocatorDeclarationCrossReference_1_0.eContents().get(1);

		//ReferredLocator:
		//    (idiomsModel=[IdiomsModel|ID] '::')? locatorDeclaration=[LocatorDeclaration];
		@Override public ParserRule getRule() { return rule; }

		//(idiomsModel=[IdiomsModel|ID] '::')? locatorDeclaration=[LocatorDeclaration]
		public Group getGroup() { return cGroup; }

		//(idiomsModel=[IdiomsModel|ID] '::')?
		public Group getGroup_0() { return cGroup_0; }

		//idiomsModel=[IdiomsModel|ID]
		public Assignment getIdiomsModelAssignment_0_0() { return cIdiomsModelAssignment_0_0; }

		//[IdiomsModel|ID]
		public CrossReference getIdiomsModelIdiomsModelCrossReference_0_0_0() { return cIdiomsModelIdiomsModelCrossReference_0_0_0; }

		//ID
		public RuleCall getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1() { return cIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1; }

		//'::'
		public Keyword getColonColonKeyword_0_1() { return cColonColonKeyword_0_1; }

		//locatorDeclaration=[LocatorDeclaration]
		public Assignment getLocatorDeclarationAssignment_1() { return cLocatorDeclarationAssignment_1; }

		//[LocatorDeclaration]
		public CrossReference getLocatorDeclarationLocatorDeclarationCrossReference_1_0() { return cLocatorDeclarationLocatorDeclarationCrossReference_1_0; }

		//ID
		public RuleCall getLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1() { return cLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1; }
	}
	public class SegmentDeclarationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.SegmentDeclaration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSegmentKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cOwnedSegmentAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cOwnedSegmentSegmentParserRuleCall_2_0 = (RuleCall)cOwnedSegmentAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//SegmentDeclaration:
		//    'segment' name=ID ownedSegment=Segment ';' ;
		@Override public ParserRule getRule() { return rule; }

		//'segment' name=ID ownedSegment=Segment ';'
		public Group getGroup() { return cGroup; }

		//'segment'
		public Keyword getSegmentKeyword_0() { return cSegmentKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//ownedSegment=Segment
		public Assignment getOwnedSegmentAssignment_2() { return cOwnedSegmentAssignment_2; }

		//Segment
		public RuleCall getOwnedSegmentSegmentParserRuleCall_2_0() { return cOwnedSegmentSegmentParserRuleCall_2_0; }

		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class SegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.Segment");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cCustomSegmentParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cHalfNewLineSegmentParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cNewLineSegmentParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cNoSpaceSegmentParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cPopSegmentParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cPostCommentSegmentParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cPreCommentSegmentParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);
		private final RuleCall cPushSegmentParserRuleCall_7 = (RuleCall)cAlternatives.eContents().get(7);
		private final RuleCall cSoftNewLineSegmentParserRuleCall_8 = (RuleCall)cAlternatives.eContents().get(8);
		private final RuleCall cSoftSpaceSegmentParserRuleCall_9 = (RuleCall)cAlternatives.eContents().get(9);
		private final RuleCall cStringSegmentParserRuleCall_10 = (RuleCall)cAlternatives.eContents().get(10);
		private final RuleCall cValueSegmentParserRuleCall_11 = (RuleCall)cAlternatives.eContents().get(11);
		private final RuleCall cWrapAnchorSegmentParserRuleCall_12 = (RuleCall)cAlternatives.eContents().get(12);
		private final RuleCall cWrapBeginAllSegmentParserRuleCall_13 = (RuleCall)cAlternatives.eContents().get(13);
		private final RuleCall cWrapBeginSomeSegmentParserRuleCall_14 = (RuleCall)cAlternatives.eContents().get(14);
		private final RuleCall cWrapEndSegmentParserRuleCall_15 = (RuleCall)cAlternatives.eContents().get(15);
		private final RuleCall cWrapHereSegmentParserRuleCall_16 = (RuleCall)cAlternatives.eContents().get(16);

		//Segment:
		//    CustomSegment | HalfNewLineSegment | NewLineSegment | NoSpaceSegment | PopSegment | PostCommentSegment |
		//    PreCommentSegment | PushSegment | SoftNewLineSegment | SoftSpaceSegment | StringSegment | ValueSegment |
		//    WrapAnchorSegment | WrapBeginAllSegment | WrapBeginSomeSegment | WrapEndSegment | WrapHereSegment;
		@Override public ParserRule getRule() { return rule; }

		//CustomSegment | HalfNewLineSegment | NewLineSegment | NoSpaceSegment | PopSegment | PostCommentSegment |
		//PreCommentSegment | PushSegment | SoftNewLineSegment | SoftSpaceSegment | StringSegment | ValueSegment |
		//WrapAnchorSegment | WrapBeginAllSegment | WrapBeginSomeSegment | WrapEndSegment | WrapHereSegment
		public Alternatives getAlternatives() { return cAlternatives; }

		//CustomSegment
		public RuleCall getCustomSegmentParserRuleCall_0() { return cCustomSegmentParserRuleCall_0; }

		//HalfNewLineSegment
		public RuleCall getHalfNewLineSegmentParserRuleCall_1() { return cHalfNewLineSegmentParserRuleCall_1; }

		//NewLineSegment
		public RuleCall getNewLineSegmentParserRuleCall_2() { return cNewLineSegmentParserRuleCall_2; }

		//NoSpaceSegment
		public RuleCall getNoSpaceSegmentParserRuleCall_3() { return cNoSpaceSegmentParserRuleCall_3; }

		//PopSegment
		public RuleCall getPopSegmentParserRuleCall_4() { return cPopSegmentParserRuleCall_4; }

		//PostCommentSegment
		public RuleCall getPostCommentSegmentParserRuleCall_5() { return cPostCommentSegmentParserRuleCall_5; }

		//PreCommentSegment
		public RuleCall getPreCommentSegmentParserRuleCall_6() { return cPreCommentSegmentParserRuleCall_6; }

		//PushSegment
		public RuleCall getPushSegmentParserRuleCall_7() { return cPushSegmentParserRuleCall_7; }

		//SoftNewLineSegment
		public RuleCall getSoftNewLineSegmentParserRuleCall_8() { return cSoftNewLineSegmentParserRuleCall_8; }

		//SoftSpaceSegment
		public RuleCall getSoftSpaceSegmentParserRuleCall_9() { return cSoftSpaceSegmentParserRuleCall_9; }

		//StringSegment
		public RuleCall getStringSegmentParserRuleCall_10() { return cStringSegmentParserRuleCall_10; }

		//ValueSegment
		public RuleCall getValueSegmentParserRuleCall_11() { return cValueSegmentParserRuleCall_11; }

		//WrapAnchorSegment
		public RuleCall getWrapAnchorSegmentParserRuleCall_12() { return cWrapAnchorSegmentParserRuleCall_12; }

		//WrapBeginAllSegment
		public RuleCall getWrapBeginAllSegmentParserRuleCall_13() { return cWrapBeginAllSegmentParserRuleCall_13; }

		//WrapBeginSomeSegment
		public RuleCall getWrapBeginSomeSegmentParserRuleCall_14() { return cWrapBeginSomeSegmentParserRuleCall_14; }

		//WrapEndSegment
		public RuleCall getWrapEndSegmentParserRuleCall_15() { return cWrapEndSegmentParserRuleCall_15; }

		//WrapHereSegment
		public RuleCall getWrapHereSegmentParserRuleCall_16() { return cWrapHereSegmentParserRuleCall_16; }
	}
	public class CustomSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.CustomSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cCustomKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cSupportClassNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cSupportClassNameSTRINGTerminalRuleCall_1_0 = (RuleCall)cSupportClassNameAssignment_1.eContents().get(0);

		//CustomSegment:
		//    'custom' supportClassName=STRING;
		@Override public ParserRule getRule() { return rule; }

		//'custom' supportClassName=STRING
		public Group getGroup() { return cGroup; }

		//'custom'
		public Keyword getCustomKeyword_0() { return cCustomKeyword_0; }

		//supportClassName=STRING
		public Assignment getSupportClassNameAssignment_1() { return cSupportClassNameAssignment_1; }

		//STRING
		public RuleCall getSupportClassNameSTRINGTerminalRuleCall_1_0() { return cSupportClassNameSTRINGTerminalRuleCall_1_0; }
	}
	public class HalfNewLineSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.HalfNewLineSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cHalfNewLineSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cHalfNewLineKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//HalfNewLineSegment:
		//    {HalfNewLineSegment} 'half-new-line';
		@Override public ParserRule getRule() { return rule; }

		//{HalfNewLineSegment} 'half-new-line'
		public Group getGroup() { return cGroup; }

		//{HalfNewLineSegment}
		public Action getHalfNewLineSegmentAction_0() { return cHalfNewLineSegmentAction_0; }

		//'half-new-line'
		public Keyword getHalfNewLineKeyword_1() { return cHalfNewLineKeyword_1; }
	}
	public class NewLineSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.NewLineSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cNewLineSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cNewLineKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//NewLineSegment:
		//    {NewLineSegment} 'new-line';
		@Override public ParserRule getRule() { return rule; }

		//{NewLineSegment} 'new-line'
		public Group getGroup() { return cGroup; }

		//{NewLineSegment}
		public Action getNewLineSegmentAction_0() { return cNewLineSegmentAction_0; }

		//'new-line'
		public Keyword getNewLineKeyword_1() { return cNewLineKeyword_1; }
	}
	public class NoSpaceSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.NoSpaceSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cNoSpaceSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cNoSpaceKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//NoSpaceSegment:
		//    {NoSpaceSegment} 'no-space';
		@Override public ParserRule getRule() { return rule; }

		//{NoSpaceSegment} 'no-space'
		public Group getGroup() { return cGroup; }

		//{NoSpaceSegment}
		public Action getNoSpaceSegmentAction_0() { return cNoSpaceSegmentAction_0; }

		//'no-space'
		public Keyword getNoSpaceKeyword_1() { return cNoSpaceKeyword_1; }
	}
	public class PopSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.PopSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cPopSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cPopKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//PopSegment:
		//    {PopSegment} 'pop';
		@Override public ParserRule getRule() { return rule; }

		//{PopSegment} 'pop'
		public Group getGroup() { return cGroup; }

		//{PopSegment}
		public Action getPopSegmentAction_0() { return cPopSegmentAction_0; }

		//'pop'
		public Keyword getPopKeyword_1() { return cPopKeyword_1; }
	}
	public class PostCommentSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.PostCommentSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cPostCommentSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cPostCommentKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//PostCommentSegment:
		//    {PostCommentSegment} 'post-comment';
		@Override public ParserRule getRule() { return rule; }

		//{PostCommentSegment} 'post-comment'
		public Group getGroup() { return cGroup; }

		//{PostCommentSegment}
		public Action getPostCommentSegmentAction_0() { return cPostCommentSegmentAction_0; }

		//'post-comment'
		public Keyword getPostCommentKeyword_1() { return cPostCommentKeyword_1; }
	}
	public class PreCommentSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.PreCommentSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cPreCommentSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cPreCommentKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//PreCommentSegment:
		//    {PreCommentSegment} 'pre-comment';
		@Override public ParserRule getRule() { return rule; }

		//{PreCommentSegment} 'pre-comment'
		public Group getGroup() { return cGroup; }

		//{PreCommentSegment}
		public Action getPreCommentSegmentAction_0() { return cPreCommentSegmentAction_0; }

		//'pre-comment'
		public Keyword getPreCommentKeyword_1() { return cPreCommentKeyword_1; }
	}
	public class PushSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.PushSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cPushSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cPushKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//PushSegment:
		//    {PushSegment} 'push';
		@Override public ParserRule getRule() { return rule; }

		//{PushSegment} 'push'
		public Group getGroup() { return cGroup; }

		//{PushSegment}
		public Action getPushSegmentAction_0() { return cPushSegmentAction_0; }

		//'push'
		public Keyword getPushKeyword_1() { return cPushKeyword_1; }
	}
	public class SoftNewLineSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.SoftNewLineSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cSoftNewLineSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cSoftNewLineKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//SoftNewLineSegment:
		//    {SoftNewLineSegment} 'soft-new-line';
		@Override public ParserRule getRule() { return rule; }

		//{SoftNewLineSegment} 'soft-new-line'
		public Group getGroup() { return cGroup; }

		//{SoftNewLineSegment}
		public Action getSoftNewLineSegmentAction_0() { return cSoftNewLineSegmentAction_0; }

		//'soft-new-line'
		public Keyword getSoftNewLineKeyword_1() { return cSoftNewLineKeyword_1; }
	}
	public class SoftSpaceSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.SoftSpaceSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cSoftSpaceSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cSoftSpaceKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//SoftSpaceSegment:
		//    {SoftSpaceSegment} 'soft-space';
		@Override public ParserRule getRule() { return rule; }

		//{SoftSpaceSegment} 'soft-space'
		public Group getGroup() { return cGroup; }

		//{SoftSpaceSegment}
		public Action getSoftSpaceSegmentAction_0() { return cSoftSpaceSegmentAction_0; }

		//'soft-space'
		public Keyword getSoftSpaceKeyword_1() { return cSoftSpaceKeyword_1; }
	}
	public class StringSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.StringSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cStringKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cStringAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cStringSTRINGTerminalRuleCall_1_0 = (RuleCall)cStringAssignment_1.eContents().get(0);
		private final Assignment cPrintableAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final Keyword cPrintablePrintableKeyword_2_0 = (Keyword)cPrintableAssignment_2.eContents().get(0);

		//StringSegment:
		//    'string' string=STRING printable?='printable';
		@Override public ParserRule getRule() { return rule; }

		//'string' string=STRING printable?='printable'
		public Group getGroup() { return cGroup; }

		//'string'
		public Keyword getStringKeyword_0() { return cStringKeyword_0; }

		//string=STRING
		public Assignment getStringAssignment_1() { return cStringAssignment_1; }

		//STRING
		public RuleCall getStringSTRINGTerminalRuleCall_1_0() { return cStringSTRINGTerminalRuleCall_1_0; }

		//printable?='printable'
		public Assignment getPrintableAssignment_2() { return cPrintableAssignment_2; }

		//'printable'
		public Keyword getPrintablePrintableKeyword_2_0() { return cPrintablePrintableKeyword_2_0; }
	}
	public class ValueSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ValueSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cValueSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cValueKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//ValueSegment:
		//    {ValueSegment} 'value';
		@Override public ParserRule getRule() { return rule; }

		//{ValueSegment} 'value'
		public Group getGroup() { return cGroup; }

		//{ValueSegment}
		public Action getValueSegmentAction_0() { return cValueSegmentAction_0; }

		//'value'
		public Keyword getValueKeyword_1() { return cValueKeyword_1; }
	}
	public class WrapAnchorSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WrapAnchorSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cWrapAnchorSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cWrapAnchorKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//WrapAnchorSegment:
		//    {WrapAnchorSegment} 'wrap-anchor';
		@Override public ParserRule getRule() { return rule; }

		//{WrapAnchorSegment} 'wrap-anchor'
		public Group getGroup() { return cGroup; }

		//{WrapAnchorSegment}
		public Action getWrapAnchorSegmentAction_0() { return cWrapAnchorSegmentAction_0; }

		//'wrap-anchor'
		public Keyword getWrapAnchorKeyword_1() { return cWrapAnchorKeyword_1; }
	}
	public class WrapBeginAllSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WrapBeginAllSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cWrapBeginAllSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cWrapBeginAllKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//WrapBeginAllSegment:
		//    {WrapBeginAllSegment} 'wrap-begin-all';
		@Override public ParserRule getRule() { return rule; }

		//{WrapBeginAllSegment} 'wrap-begin-all'
		public Group getGroup() { return cGroup; }

		//{WrapBeginAllSegment}
		public Action getWrapBeginAllSegmentAction_0() { return cWrapBeginAllSegmentAction_0; }

		//'wrap-begin-all'
		public Keyword getWrapBeginAllKeyword_1() { return cWrapBeginAllKeyword_1; }
	}
	public class WrapBeginSomeSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WrapBeginSomeSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cWrapBeginSomeSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cWrapBeginSomeKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//WrapBeginSomeSegment:
		//    {WrapBeginSomeSegment} 'wrap-begin-some';
		@Override public ParserRule getRule() { return rule; }

		//{WrapBeginSomeSegment} 'wrap-begin-some'
		public Group getGroup() { return cGroup; }

		//{WrapBeginSomeSegment}
		public Action getWrapBeginSomeSegmentAction_0() { return cWrapBeginSomeSegmentAction_0; }

		//'wrap-begin-some'
		public Keyword getWrapBeginSomeKeyword_1() { return cWrapBeginSomeKeyword_1; }
	}
	public class WrapEndSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WrapEndSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cWrapEndSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cWrapEndKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//WrapEndSegment:
		//    {WrapEndSegment} 'wrap-end';
		@Override public ParserRule getRule() { return rule; }

		//{WrapEndSegment} 'wrap-end'
		public Group getGroup() { return cGroup; }

		//{WrapEndSegment}
		public Action getWrapEndSegmentAction_0() { return cWrapEndSegmentAction_0; }

		//'wrap-end'
		public Keyword getWrapEndKeyword_1() { return cWrapEndKeyword_1; }
	}
	public class WrapHereSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WrapHereSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cWrapHereSegmentAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cWrapHereKeyword_1 = (Keyword)cGroup.eContents().get(1);

		//WrapHereSegment:
		//    {WrapHereSegment} 'wrap-here';
		@Override public ParserRule getRule() { return rule; }

		//{WrapHereSegment} 'wrap-here'
		public Group getGroup() { return cGroup; }

		//{WrapHereSegment}
		public Action getWrapHereSegmentAction_0() { return cWrapHereSegmentAction_0; }

		//'wrap-here'
		public Keyword getWrapHereKeyword_1() { return cWrapHereKeyword_1; }
	}
	public class ReferredSegmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ReferredSegment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cGroup.eContents().get(0);
		private final Assignment cIdiomsModelAssignment_0_0 = (Assignment)cGroup_0.eContents().get(0);
		private final CrossReference cIdiomsModelIdiomsModelCrossReference_0_0_0 = (CrossReference)cIdiomsModelAssignment_0_0.eContents().get(0);
		private final RuleCall cIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1 = (RuleCall)cIdiomsModelIdiomsModelCrossReference_0_0_0.eContents().get(1);
		private final Keyword cColonColonKeyword_0_1 = (Keyword)cGroup_0.eContents().get(1);
		private final Assignment cSegmentDeclarationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cSegmentDeclarationSegmentDeclarationCrossReference_1_0 = (CrossReference)cSegmentDeclarationAssignment_1.eContents().get(0);
		private final RuleCall cSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1 = (RuleCall)cSegmentDeclarationSegmentDeclarationCrossReference_1_0.eContents().get(1);

		//ReferredSegment:
		//    (idiomsModel=[IdiomsModel|ID] '::')? segmentDeclaration=[SegmentDeclaration];
		@Override public ParserRule getRule() { return rule; }

		//(idiomsModel=[IdiomsModel|ID] '::')? segmentDeclaration=[SegmentDeclaration]
		public Group getGroup() { return cGroup; }

		//(idiomsModel=[IdiomsModel|ID] '::')?
		public Group getGroup_0() { return cGroup_0; }

		//idiomsModel=[IdiomsModel|ID]
		public Assignment getIdiomsModelAssignment_0_0() { return cIdiomsModelAssignment_0_0; }

		//[IdiomsModel|ID]
		public CrossReference getIdiomsModelIdiomsModelCrossReference_0_0_0() { return cIdiomsModelIdiomsModelCrossReference_0_0_0; }

		//ID
		public RuleCall getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1() { return cIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1; }

		//'::'
		public Keyword getColonColonKeyword_0_1() { return cColonColonKeyword_0_1; }

		//segmentDeclaration=[SegmentDeclaration]
		public Assignment getSegmentDeclarationAssignment_1() { return cSegmentDeclarationAssignment_1; }

		//[SegmentDeclaration]
		public CrossReference getSegmentDeclarationSegmentDeclarationCrossReference_1_0() { return cSegmentDeclarationSegmentDeclarationCrossReference_1_0; }

		//ID
		public RuleCall getSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1() { return cSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1; }
	}
	public class IdiomElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.Idiom");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cMixinAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Keyword cMixinMixinKeyword_0_0 = (Keyword)cMixinAssignment_0.eContents().get(0);
		private final Keyword cIdiomKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cForKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cGroup_3.eContents().get(1);
		private final Assignment cForEPackageAssignment_3_1_0 = (Assignment)cGroup_3_1.eContents().get(0);
		private final CrossReference cForEPackageEPackageCrossReference_3_1_0_0 = (CrossReference)cForEPackageAssignment_3_1_0.eContents().get(0);
		private final RuleCall cForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1 = (RuleCall)cForEPackageEPackageCrossReference_3_1_0_0.eContents().get(1);
		private final Keyword cColonColonKeyword_3_1_1 = (Keyword)cGroup_3_1.eContents().get(1);
		private final Assignment cForEClassAssignment_3_2 = (Assignment)cGroup_3.eContents().get(2);
		private final CrossReference cForEClassEClassCrossReference_3_2_0 = (CrossReference)cForEClassAssignment_3_2.eContents().get(0);
		private final RuleCall cForEClassEClassIDTerminalRuleCall_3_2_0_1 = (RuleCall)cForEClassEClassCrossReference_3_2_0.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cInKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cInRuleRegexAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cInRuleRegexSTRINGTerminalRuleCall_4_1_0 = (RuleCall)cInRuleRegexAssignment_4_1.eContents().get(0);
		private final Alternatives cAlternatives_5 = (Alternatives)cGroup.eContents().get(5);
		private final Assignment cOwnedSubIdiomsAssignment_5_0 = (Assignment)cAlternatives_5.eContents().get(0);
		private final RuleCall cOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0 = (RuleCall)cOwnedSubIdiomsAssignment_5_0.eContents().get(0);
		private final Group cGroup_5_1 = (Group)cAlternatives_5.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_5_1_0 = (Keyword)cGroup_5_1.eContents().get(0);
		private final Assignment cOwnedSubIdiomsAssignment_5_1_1 = (Assignment)cGroup_5_1.eContents().get(1);
		private final RuleCall cOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0 = (RuleCall)cOwnedSubIdiomsAssignment_5_1_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_5_1_2 = (Keyword)cGroup_5_1.eContents().get(2);

		//Idiom:
		//    mixin?='mixin'? 'idiom' name=ID ('for' (forEPackage=[ecore::EPackage|ID] '::')? forEClass=[ecore::EClass])? ('in' inRuleRegex=STRING)?
		//    ((ownedSubIdioms+=SubIdiom) | ('{' (ownedSubIdioms+=SubIdiom)* '}'));
		@Override public ParserRule getRule() { return rule; }

		//mixin?='mixin'? 'idiom' name=ID ('for' (forEPackage=[ecore::EPackage|ID] '::')? forEClass=[ecore::EClass])? ('in' inRuleRegex=STRING)?
		//((ownedSubIdioms+=SubIdiom) | ('{' (ownedSubIdioms+=SubIdiom)* '}'))
		public Group getGroup() { return cGroup; }

		//mixin?='mixin'?
		public Assignment getMixinAssignment_0() { return cMixinAssignment_0; }

		//'mixin'
		public Keyword getMixinMixinKeyword_0_0() { return cMixinMixinKeyword_0_0; }

		//'idiom'
		public Keyword getIdiomKeyword_1() { return cIdiomKeyword_1; }

		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }

		//('for' (forEPackage=[ecore::EPackage|ID] '::')? forEClass=[ecore::EClass])?
		public Group getGroup_3() { return cGroup_3; }

		//'for'
		public Keyword getForKeyword_3_0() { return cForKeyword_3_0; }

		//(forEPackage=[ecore::EPackage|ID] '::')?
		public Group getGroup_3_1() { return cGroup_3_1; }

		//forEPackage=[ecore::EPackage|ID]
		public Assignment getForEPackageAssignment_3_1_0() { return cForEPackageAssignment_3_1_0; }

		//[ecore::EPackage|ID]
		public CrossReference getForEPackageEPackageCrossReference_3_1_0_0() { return cForEPackageEPackageCrossReference_3_1_0_0; }

		//ID
		public RuleCall getForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1() { return cForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1; }

		//'::'
		public Keyword getColonColonKeyword_3_1_1() { return cColonColonKeyword_3_1_1; }

		//forEClass=[ecore::EClass]
		public Assignment getForEClassAssignment_3_2() { return cForEClassAssignment_3_2; }

		//[ecore::EClass]
		public CrossReference getForEClassEClassCrossReference_3_2_0() { return cForEClassEClassCrossReference_3_2_0; }

		//ID
		public RuleCall getForEClassEClassIDTerminalRuleCall_3_2_0_1() { return cForEClassEClassIDTerminalRuleCall_3_2_0_1; }

		//('in' inRuleRegex=STRING)?
		public Group getGroup_4() { return cGroup_4; }

		//'in'
		public Keyword getInKeyword_4_0() { return cInKeyword_4_0; }

		//inRuleRegex=STRING
		public Assignment getInRuleRegexAssignment_4_1() { return cInRuleRegexAssignment_4_1; }

		//STRING
		public RuleCall getInRuleRegexSTRINGTerminalRuleCall_4_1_0() { return cInRuleRegexSTRINGTerminalRuleCall_4_1_0; }

		//((ownedSubIdioms+=SubIdiom) | ('{' (ownedSubIdioms+=SubIdiom)* '}'))
		public Alternatives getAlternatives_5() { return cAlternatives_5; }

		//(ownedSubIdioms+=SubIdiom)
		public Assignment getOwnedSubIdiomsAssignment_5_0() { return cOwnedSubIdiomsAssignment_5_0; }

		//SubIdiom
		public RuleCall getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0() { return cOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0; }

		//('{' (ownedSubIdioms+=SubIdiom)* '}')
		public Group getGroup_5_1() { return cGroup_5_1; }

		//'{'
		public Keyword getLeftCurlyBracketKeyword_5_1_0() { return cLeftCurlyBracketKeyword_5_1_0; }

		//(ownedSubIdioms+=SubIdiom)*
		public Assignment getOwnedSubIdiomsAssignment_5_1_1() { return cOwnedSubIdiomsAssignment_5_1_1; }

		//SubIdiom
		public RuleCall getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0() { return cOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0; }

		//'}'
		public Keyword getRightCurlyBracketKeyword_5_1_2() { return cRightCurlyBracketKeyword_5_1_2; }
	}
	public class SubIdiomElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.SubIdiom");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAtKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Assignment cAllAssignment_1_0 = (Assignment)cAlternatives_1.eContents().get(0);
		private final Keyword cAllAllKeyword_1_0_0 = (Keyword)cAllAssignment_1_0.eContents().get(0);
		private final Keyword cEachKeyword_1_1 = (Keyword)cAlternatives_1.eContents().get(1);
		private final Assignment cOwnedLocatorAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cOwnedLocatorLocatorParserRuleCall_2_0 = (RuleCall)cOwnedLocatorAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cDoKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cOwnedSegmentsAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final Alternatives cOwnedSegmentsAlternatives_3_1_0 = (Alternatives)cOwnedSegmentsAssignment_3_1.eContents().get(0);
		private final RuleCall cOwnedSegmentsSegmentParserRuleCall_3_1_0_0 = (RuleCall)cOwnedSegmentsAlternatives_3_1_0.eContents().get(0);
		private final RuleCall cOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1 = (RuleCall)cOwnedSegmentsAlternatives_3_1_0.eContents().get(1);
		private final Keyword cSemicolonKeyword_4 = (Keyword)cGroup.eContents().get(4);

		//SubIdiom:
		//    'at' (all?='all' | 'each')? ownedLocator=Locator ('do' ownedSegments+=(Segment|ReferredSegment)*)? ';';
		@Override public ParserRule getRule() { return rule; }

		//'at' (all?='all' | 'each')? ownedLocator=Locator ('do' ownedSegments+=(Segment|ReferredSegment)*)? ';'
		public Group getGroup() { return cGroup; }

		//'at'
		public Keyword getAtKeyword_0() { return cAtKeyword_0; }

		//(all?='all' | 'each')?
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//all?='all'
		public Assignment getAllAssignment_1_0() { return cAllAssignment_1_0; }

		//'all'
		public Keyword getAllAllKeyword_1_0_0() { return cAllAllKeyword_1_0_0; }

		//'each'
		public Keyword getEachKeyword_1_1() { return cEachKeyword_1_1; }

		//ownedLocator=Locator
		public Assignment getOwnedLocatorAssignment_2() { return cOwnedLocatorAssignment_2; }

		//Locator
		public RuleCall getOwnedLocatorLocatorParserRuleCall_2_0() { return cOwnedLocatorLocatorParserRuleCall_2_0; }

		//('do' ownedSegments+=(Segment|ReferredSegment)*)?
		public Group getGroup_3() { return cGroup_3; }

		//'do'
		public Keyword getDoKeyword_3_0() { return cDoKeyword_3_0; }

		//ownedSegments+=(Segment|ReferredSegment)*
		public Assignment getOwnedSegmentsAssignment_3_1() { return cOwnedSegmentsAssignment_3_1; }

		//(Segment|ReferredSegment)
		public Alternatives getOwnedSegmentsAlternatives_3_1_0() { return cOwnedSegmentsAlternatives_3_1_0; }

		//Segment
		public RuleCall getOwnedSegmentsSegmentParserRuleCall_3_1_0_0() { return cOwnedSegmentsSegmentParserRuleCall_3_1_0_0; }

		//ReferredSegment
		public RuleCall getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1() { return cOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1; }

		//';'
		public Keyword getSemicolonKeyword_4() { return cSemicolonKeyword_4; }
	}


	private final IdiomsModelElements pIdiomsModel;
	private final EPackageImportElements pEPackageImport;
	private final IdiomsImportElements pIdiomsImport;
	private final LocatorDeclarationElements pLocatorDeclaration;
	private final LocatorElements pLocator;
	private final AnyAssignmentLocatorElements pAnyAssignmentLocator;
	private final AnyElementLocatorElements pAnyElementLocator;
	private final AssignmentLocatorElements pAssignmentLocator;
	private final FinalLocatorElements pFinalLocator;
	private final KeywordLocatorElements pKeywordLocator;
	private final ReturnsLocatorElements pReturnsLocator;
	private final ReferredLocatorElements pReferredLocator;
	private final SegmentDeclarationElements pSegmentDeclaration;
	private final SegmentElements pSegment;
	private final CustomSegmentElements pCustomSegment;
	private final HalfNewLineSegmentElements pHalfNewLineSegment;
	private final NewLineSegmentElements pNewLineSegment;
	private final NoSpaceSegmentElements pNoSpaceSegment;
	private final PopSegmentElements pPopSegment;
	private final PostCommentSegmentElements pPostCommentSegment;
	private final PreCommentSegmentElements pPreCommentSegment;
	private final PushSegmentElements pPushSegment;
	private final SoftNewLineSegmentElements pSoftNewLineSegment;
	private final SoftSpaceSegmentElements pSoftSpaceSegment;
	private final StringSegmentElements pStringSegment;
	private final ValueSegmentElements pValueSegment;
	private final WrapAnchorSegmentElements pWrapAnchorSegment;
	private final WrapBeginAllSegmentElements pWrapBeginAllSegment;
	private final WrapBeginSomeSegmentElements pWrapBeginSomeSegment;
	private final WrapEndSegmentElements pWrapEndSegment;
	private final WrapHereSegmentElements pWrapHereSegment;
	private final ReferredSegmentElements pReferredSegment;
	private final IdiomElements pIdiom;
	private final SubIdiomElements pSubIdiom;
	private final TerminalRule tID;
	private final TerminalRule tINT;
	private final TerminalRule tSTRING;
	private final TerminalRule tML_COMMENT;
	private final TerminalRule tSL_COMMENT;
	private final TerminalRule tWS;
	private final TerminalRule tANY_OTHER;

	private final Grammar grammar;

	@Inject
	public IdiomsGrammarAccess(GrammarProvider grammarProvider) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.pIdiomsModel = new IdiomsModelElements();
		this.pEPackageImport = new EPackageImportElements();
		this.pIdiomsImport = new IdiomsImportElements();
		this.pLocatorDeclaration = new LocatorDeclarationElements();
		this.pLocator = new LocatorElements();
		this.pAnyAssignmentLocator = new AnyAssignmentLocatorElements();
		this.pAnyElementLocator = new AnyElementLocatorElements();
		this.pAssignmentLocator = new AssignmentLocatorElements();
		this.pFinalLocator = new FinalLocatorElements();
		this.pKeywordLocator = new KeywordLocatorElements();
		this.pReturnsLocator = new ReturnsLocatorElements();
		this.pReferredLocator = new ReferredLocatorElements();
		this.pSegmentDeclaration = new SegmentDeclarationElements();
		this.pSegment = new SegmentElements();
		this.pCustomSegment = new CustomSegmentElements();
		this.pHalfNewLineSegment = new HalfNewLineSegmentElements();
		this.pNewLineSegment = new NewLineSegmentElements();
		this.pNoSpaceSegment = new NoSpaceSegmentElements();
		this.pPopSegment = new PopSegmentElements();
		this.pPostCommentSegment = new PostCommentSegmentElements();
		this.pPreCommentSegment = new PreCommentSegmentElements();
		this.pPushSegment = new PushSegmentElements();
		this.pSoftNewLineSegment = new SoftNewLineSegmentElements();
		this.pSoftSpaceSegment = new SoftSpaceSegmentElements();
		this.pStringSegment = new StringSegmentElements();
		this.pValueSegment = new ValueSegmentElements();
		this.pWrapAnchorSegment = new WrapAnchorSegmentElements();
		this.pWrapBeginAllSegment = new WrapBeginAllSegmentElements();
		this.pWrapBeginSomeSegment = new WrapBeginSomeSegmentElements();
		this.pWrapEndSegment = new WrapEndSegmentElements();
		this.pWrapHereSegment = new WrapHereSegmentElements();
		this.pReferredSegment = new ReferredSegmentElements();
		this.pIdiom = new IdiomElements();
		this.pSubIdiom = new SubIdiomElements();
		this.tID = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
		this.tINT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.INT");
		this.tSTRING = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");
		this.tML_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ML_COMMENT");
		this.tSL_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.SL_COMMENT");
		this.tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.WS");
		this.tANY_OTHER = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.ocl.examples.xtext.idioms.Idioms.ANY_OTHER");
	}

	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.ocl.examples.xtext.idioms.Idioms".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}

	@Override
	public Grammar getGrammar() {
		return grammar;
	}



	//IdiomsModel:
	//    'model' names+=ID ('.' names+=ID)* ownedWiths+=IdiomsImport* ownedImports+=EPackageImport* (ownedLocatorDeclarations+=LocatorDeclaration | ownedSegmentDeclarations+=SegmentDeclaration | ownedIdioms+=Idiom)* ;
	public IdiomsModelElements getIdiomsModelAccess() {
		return pIdiomsModel;
	}

	public ParserRule getIdiomsModelRule() {
		return getIdiomsModelAccess().getRule();
	}

	//EPackageImport:
	//    'import' ePackage=[ecore::EPackage|STRING] ('as' ^as=ID)? ';'?;
	public EPackageImportElements getEPackageImportAccess() {
		return pEPackageImport;
	}

	public ParserRule getEPackageImportRule() {
		return getEPackageImportAccess().getRule();
	}

	//IdiomsImport:
	//    'with' idiomsModel=[IdiomsModel|STRING] ('as' ^as=ID)? ';'?;
	public IdiomsImportElements getIdiomsImportAccess() {
		return pIdiomsImport;
	}

	public ParserRule getIdiomsImportRule() {
		return getIdiomsImportAccess().getRule();
	}

	//LocatorDeclaration:
	//    'locator' name=ID  ownedLocator=Locator ';';
	public LocatorDeclarationElements getLocatorDeclarationAccess() {
		return pLocatorDeclaration;
	}

	public ParserRule getLocatorDeclarationRule() {
		return getLocatorDeclarationAccess().getRule();
	}

	//Locator:
	//    AnyAssignmentLocator | AnyElementLocator | AssignmentLocator | FinalLocator | KeywordLocator | ReferredLocator | ReturnsLocator;
	public LocatorElements getLocatorAccess() {
		return pLocator;
	}

	public ParserRule getLocatorRule() {
		return getLocatorAccess().getRule();
	}

	//AnyAssignmentLocator:
	//    {AnyAssignmentLocator} 'any-assignment';
	public AnyAssignmentLocatorElements getAnyAssignmentLocatorAccess() {
		return pAnyAssignmentLocator;
	}

	public ParserRule getAnyAssignmentLocatorRule() {
		return getAnyAssignmentLocatorAccess().getRule();
	}

	//AnyElementLocator:
	//    {AnyElementLocator} 'any-element';
	public AnyElementLocatorElements getAnyElementLocatorAccess() {
		return pAnyElementLocator;
	}

	public ParserRule getAnyElementLocatorRule() {
		return getAnyElementLocatorAccess().getRule();
	}

	//AssignmentLocator:
	//    'assignment' ((ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID] '::')? eStructuralFeature=[ecore::EStructuralFeature];
	public AssignmentLocatorElements getAssignmentLocatorAccess() {
		return pAssignmentLocator;
	}

	public ParserRule getAssignmentLocatorRule() {
		return getAssignmentLocatorAccess().getRule();
	}

	//FinalLocator:
	//    {FinalLocator} 'final';
	public FinalLocatorElements getFinalLocatorAccess() {
		return pFinalLocator;
	}

	public ParserRule getFinalLocatorRule() {
		return getFinalLocatorAccess().getRule();
	}

	//KeywordLocator:
	//    string=STRING;
	public KeywordLocatorElements getKeywordLocatorAccess() {
		return pKeywordLocator;
	}

	public ParserRule getKeywordLocatorRule() {
		return getKeywordLocatorAccess().getRule();
	}

	//ReturnsLocator:
	//    'returns' (ePackage=[ecore::EPackage|ID] '::')? eClass=[ecore::EClass|ID];
	public ReturnsLocatorElements getReturnsLocatorAccess() {
		return pReturnsLocator;
	}

	public ParserRule getReturnsLocatorRule() {
		return getReturnsLocatorAccess().getRule();
	}

	//ReferredLocator:
	//    (idiomsModel=[IdiomsModel|ID] '::')? locatorDeclaration=[LocatorDeclaration];
	public ReferredLocatorElements getReferredLocatorAccess() {
		return pReferredLocator;
	}

	public ParserRule getReferredLocatorRule() {
		return getReferredLocatorAccess().getRule();
	}

	//SegmentDeclaration:
	//    'segment' name=ID ownedSegment=Segment ';' ;
	public SegmentDeclarationElements getSegmentDeclarationAccess() {
		return pSegmentDeclaration;
	}

	public ParserRule getSegmentDeclarationRule() {
		return getSegmentDeclarationAccess().getRule();
	}

	//Segment:
	//    CustomSegment | HalfNewLineSegment | NewLineSegment | NoSpaceSegment | PopSegment | PostCommentSegment |
	//    PreCommentSegment | PushSegment | SoftNewLineSegment | SoftSpaceSegment | StringSegment | ValueSegment |
	//    WrapAnchorSegment | WrapBeginAllSegment | WrapBeginSomeSegment | WrapEndSegment | WrapHereSegment;
	public SegmentElements getSegmentAccess() {
		return pSegment;
	}

	public ParserRule getSegmentRule() {
		return getSegmentAccess().getRule();
	}

	//CustomSegment:
	//    'custom' supportClassName=STRING;
	public CustomSegmentElements getCustomSegmentAccess() {
		return pCustomSegment;
	}

	public ParserRule getCustomSegmentRule() {
		return getCustomSegmentAccess().getRule();
	}

	//HalfNewLineSegment:
	//    {HalfNewLineSegment} 'half-new-line';
	public HalfNewLineSegmentElements getHalfNewLineSegmentAccess() {
		return pHalfNewLineSegment;
	}

	public ParserRule getHalfNewLineSegmentRule() {
		return getHalfNewLineSegmentAccess().getRule();
	}

	//NewLineSegment:
	//    {NewLineSegment} 'new-line';
	public NewLineSegmentElements getNewLineSegmentAccess() {
		return pNewLineSegment;
	}

	public ParserRule getNewLineSegmentRule() {
		return getNewLineSegmentAccess().getRule();
	}

	//NoSpaceSegment:
	//    {NoSpaceSegment} 'no-space';
	public NoSpaceSegmentElements getNoSpaceSegmentAccess() {
		return pNoSpaceSegment;
	}

	public ParserRule getNoSpaceSegmentRule() {
		return getNoSpaceSegmentAccess().getRule();
	}

	//PopSegment:
	//    {PopSegment} 'pop';
	public PopSegmentElements getPopSegmentAccess() {
		return pPopSegment;
	}

	public ParserRule getPopSegmentRule() {
		return getPopSegmentAccess().getRule();
	}

	//PostCommentSegment:
	//    {PostCommentSegment} 'post-comment';
	public PostCommentSegmentElements getPostCommentSegmentAccess() {
		return pPostCommentSegment;
	}

	public ParserRule getPostCommentSegmentRule() {
		return getPostCommentSegmentAccess().getRule();
	}

	//PreCommentSegment:
	//    {PreCommentSegment} 'pre-comment';
	public PreCommentSegmentElements getPreCommentSegmentAccess() {
		return pPreCommentSegment;
	}

	public ParserRule getPreCommentSegmentRule() {
		return getPreCommentSegmentAccess().getRule();
	}

	//PushSegment:
	//    {PushSegment} 'push';
	public PushSegmentElements getPushSegmentAccess() {
		return pPushSegment;
	}

	public ParserRule getPushSegmentRule() {
		return getPushSegmentAccess().getRule();
	}

	//SoftNewLineSegment:
	//    {SoftNewLineSegment} 'soft-new-line';
	public SoftNewLineSegmentElements getSoftNewLineSegmentAccess() {
		return pSoftNewLineSegment;
	}

	public ParserRule getSoftNewLineSegmentRule() {
		return getSoftNewLineSegmentAccess().getRule();
	}

	//SoftSpaceSegment:
	//    {SoftSpaceSegment} 'soft-space';
	public SoftSpaceSegmentElements getSoftSpaceSegmentAccess() {
		return pSoftSpaceSegment;
	}

	public ParserRule getSoftSpaceSegmentRule() {
		return getSoftSpaceSegmentAccess().getRule();
	}

	//StringSegment:
	//    'string' string=STRING printable?='printable';
	public StringSegmentElements getStringSegmentAccess() {
		return pStringSegment;
	}

	public ParserRule getStringSegmentRule() {
		return getStringSegmentAccess().getRule();
	}

	//ValueSegment:
	//    {ValueSegment} 'value';
	public ValueSegmentElements getValueSegmentAccess() {
		return pValueSegment;
	}

	public ParserRule getValueSegmentRule() {
		return getValueSegmentAccess().getRule();
	}

	//WrapAnchorSegment:
	//    {WrapAnchorSegment} 'wrap-anchor';
	public WrapAnchorSegmentElements getWrapAnchorSegmentAccess() {
		return pWrapAnchorSegment;
	}

	public ParserRule getWrapAnchorSegmentRule() {
		return getWrapAnchorSegmentAccess().getRule();
	}

	//WrapBeginAllSegment:
	//    {WrapBeginAllSegment} 'wrap-begin-all';
	public WrapBeginAllSegmentElements getWrapBeginAllSegmentAccess() {
		return pWrapBeginAllSegment;
	}

	public ParserRule getWrapBeginAllSegmentRule() {
		return getWrapBeginAllSegmentAccess().getRule();
	}

	//WrapBeginSomeSegment:
	//    {WrapBeginSomeSegment} 'wrap-begin-some';
	public WrapBeginSomeSegmentElements getWrapBeginSomeSegmentAccess() {
		return pWrapBeginSomeSegment;
	}

	public ParserRule getWrapBeginSomeSegmentRule() {
		return getWrapBeginSomeSegmentAccess().getRule();
	}

	//WrapEndSegment:
	//    {WrapEndSegment} 'wrap-end';
	public WrapEndSegmentElements getWrapEndSegmentAccess() {
		return pWrapEndSegment;
	}

	public ParserRule getWrapEndSegmentRule() {
		return getWrapEndSegmentAccess().getRule();
	}

	//WrapHereSegment:
	//    {WrapHereSegment} 'wrap-here';
	public WrapHereSegmentElements getWrapHereSegmentAccess() {
		return pWrapHereSegment;
	}

	public ParserRule getWrapHereSegmentRule() {
		return getWrapHereSegmentAccess().getRule();
	}

	//ReferredSegment:
	//    (idiomsModel=[IdiomsModel|ID] '::')? segmentDeclaration=[SegmentDeclaration];
	public ReferredSegmentElements getReferredSegmentAccess() {
		return pReferredSegment;
	}

	public ParserRule getReferredSegmentRule() {
		return getReferredSegmentAccess().getRule();
	}

	//Idiom:
	//    mixin?='mixin'? 'idiom' name=ID ('for' (forEPackage=[ecore::EPackage|ID] '::')? forEClass=[ecore::EClass])? ('in' inRuleRegex=STRING)?
	//    ((ownedSubIdioms+=SubIdiom) | ('{' (ownedSubIdioms+=SubIdiom)* '}'));
	public IdiomElements getIdiomAccess() {
		return pIdiom;
	}

	public ParserRule getIdiomRule() {
		return getIdiomAccess().getRule();
	}

	//SubIdiom:
	//    'at' (all?='all' | 'each')? ownedLocator=Locator ('do' ownedSegments+=(Segment|ReferredSegment)*)? ';';
	public SubIdiomElements getSubIdiomAccess() {
		return pSubIdiom;
	}

	public ParserRule getSubIdiomRule() {
		return getSubIdiomAccess().getRule();
	}

	//terminal ID: '^'?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
	public TerminalRule getIDRule() {
		return tID;
	}

	//terminal INT returns ecore::EInt: ('0'..'9')+;
	public TerminalRule getINTRule() {
		return tINT;
	}

	//terminal STRING:
	//            '"' ( '\\' . /* 'b'|'t'|'n'|'f'|'r'|'u'|'"'|"'"|'\\' */ | !('\\'|'"') )* '"' |
	//            "'" ( '\\' . /* 'b'|'t'|'n'|'f'|'r'|'u'|'"'|"'"|'\\' */ | !('\\'|"'") )* "'"
	//        ;
	public TerminalRule getSTRINGRule() {
		return tSTRING;
	}

	//terminal ML_COMMENT : '/*' -> '*/';
	public TerminalRule getML_COMMENTRule() {
		return tML_COMMENT;
	}

	//terminal SL_COMMENT : '//' !('\n'|'\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return tSL_COMMENT;
	}

	//terminal WS         : (' '|'\t'|'\r'|'\n')+;
	public TerminalRule getWSRule() {
		return tWS;
	}

	//terminal ANY_OTHER: .;
	public TerminalRule getANY_OTHERRule() {
		return tANY_OTHER;
	}
}
