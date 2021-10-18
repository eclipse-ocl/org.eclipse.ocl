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
grammar InternalIdioms;

options {
	superClass=AbstractInternalAntlrParser;
	backtrack=true;
}

@lexer::header {
package org.eclipse.ocl.examples.xtext.idioms.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import.
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.ocl.examples.xtext.idioms.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.ocl.examples.xtext.idioms.services.IdiomsGrammarAccess;

}

@parser::members {

/*
  This grammar contains a lot of empty actions to work around a bug in ANTLR.
  Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
*/

 	private IdiomsGrammarAccess grammarAccess;

    public InternalIdiomsParser(TokenStream input, IdiomsGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "IdiomsModel";
   	}

   	@Override
   	protected IdiomsGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleIdiomsModel
entryRuleIdiomsModel returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIdiomsModelRule()); }
	iv_ruleIdiomsModel=ruleIdiomsModel
	{ $current=$iv_ruleIdiomsModel.current; }
	EOF;

// Rule IdiomsModel
ruleIdiomsModel returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='model'
		{
			newLeafNode(otherlv_0, grammarAccess.getIdiomsModelAccess().getModelKeyword_0());
		}
		(
			(
				lv_names_1_0=RULE_ID
				{
					newLeafNode(lv_names_1_0, grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getIdiomsModelRule());
					}
					addWithLastConsumed(
						$current,
						"names",
						lv_names_1_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
				}
			)
		)
		(
			otherlv_2='.'
			{
				newLeafNode(otherlv_2, grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0());
			}
			(
				(
					lv_names_3_0=RULE_ID
					{
						newLeafNode(lv_names_3_0, grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getIdiomsModelRule());
						}
						addWithLastConsumed(
							$current,
							"names",
							lv_names_3_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
					}
				)
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0());
				}
				lv_ownedWiths_4_0=ruleIdiomsImport
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
					}
					add(
						$current,
						"ownedWiths",
						lv_ownedWiths_4_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.IdiomsImport");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0());
				}
				lv_ownedImports_5_0=ruleEPackageImport
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
					}
					add(
						$current,
						"ownedImports",
						lv_ownedImports_5_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.EPackageImport");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0());
					}
					lv_ownedLocatorDeclarations_6_0=ruleLocatorDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
						}
						add(
							$current,
							"ownedLocatorDeclarations",
							lv_ownedLocatorDeclarations_6_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.LocatorDeclaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0());
					}
					lv_ownedSegmentDeclarations_7_0=ruleSegmentDeclaration
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
						}
						add(
							$current,
							"ownedSegmentDeclarations",
							lv_ownedSegmentDeclarations_7_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.SegmentDeclaration");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0());
					}
					lv_ownedIdioms_8_0=ruleIdiom
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
						}
						add(
							$current,
							"ownedIdioms",
							lv_ownedIdioms_8_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.Idiom");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleEPackageImport
entryRuleEPackageImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEPackageImportRule()); }
	iv_ruleEPackageImport=ruleEPackageImport
	{ $current=$iv_ruleEPackageImport.current; }
	EOF;

// Rule EPackageImport
ruleEPackageImport returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='import'
		{
			newLeafNode(otherlv_0, grammarAccess.getEPackageImportAccess().getImportKeyword_0());
		}
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEPackageImportRule());
					}
				}
				otherlv_1=RULE_STRING
				{
					newLeafNode(otherlv_1, grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0());
				}
			)
		)
		(
			otherlv_2='as'
			{
				newLeafNode(otherlv_2, grammarAccess.getEPackageImportAccess().getAsKeyword_2_0());
			}
			(
				(
					lv_as_3_0=RULE_ID
					{
						newLeafNode(lv_as_3_0, grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getEPackageImportRule());
						}
						setWithLastConsumed(
							$current,
							"as",
							lv_as_3_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
					}
				)
			)
		)?
		(
			otherlv_4=';'
			{
				newLeafNode(otherlv_4, grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3());
			}
		)?
	)
;

// Entry rule entryRuleIdiomsImport
entryRuleIdiomsImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIdiomsImportRule()); }
	iv_ruleIdiomsImport=ruleIdiomsImport
	{ $current=$iv_ruleIdiomsImport.current; }
	EOF;

// Rule IdiomsImport
ruleIdiomsImport returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='with'
		{
			newLeafNode(otherlv_0, grammarAccess.getIdiomsImportAccess().getWithKeyword_0());
		}
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getIdiomsImportRule());
					}
				}
				otherlv_1=RULE_STRING
				{
					newLeafNode(otherlv_1, grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());
				}
			)
		)
		(
			otherlv_2='as'
			{
				newLeafNode(otherlv_2, grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());
			}
			(
				(
					lv_as_3_0=RULE_ID
					{
						newLeafNode(lv_as_3_0, grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getIdiomsImportRule());
						}
						setWithLastConsumed(
							$current,
							"as",
							lv_as_3_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
					}
				)
			)
		)?
		(
			otherlv_4=';'
			{
				newLeafNode(otherlv_4, grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());
			}
		)?
	)
;

// Entry rule entryRuleLocatorDeclaration
entryRuleLocatorDeclaration returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getLocatorDeclarationRule()); }
	iv_ruleLocatorDeclaration=ruleLocatorDeclaration
	{ $current=$iv_ruleLocatorDeclaration.current; }
	EOF;

// Rule LocatorDeclaration
ruleLocatorDeclaration returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='locator'
		{
			newLeafNode(otherlv_0, grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getLocatorDeclarationRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
				}
				lv_ownedLocator_2_0=ruleLocator
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getLocatorDeclarationRule());
					}
					set(
						$current,
						"ownedLocator",
						lv_ownedLocator_2_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Locator");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=';'
		{
			newLeafNode(otherlv_3, grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3());
		}
	)
;

// Entry rule entryRuleLocator
entryRuleLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getLocatorRule()); }
	iv_ruleLocator=ruleLocator
	{ $current=$iv_ruleLocator.current; }
	EOF;

// Rule Locator
ruleLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0());
		}
		this_AnyAssignmentLocator_0=ruleAnyAssignmentLocator
		{
			$current = $this_AnyAssignmentLocator_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1());
		}
		this_AnyElementLocator_1=ruleAnyElementLocator
		{
			$current = $this_AnyElementLocator_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2());
		}
		this_AssignmentLocator_2=ruleAssignmentLocator
		{
			$current = $this_AssignmentLocator_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3());
		}
		this_FinalLocator_3=ruleFinalLocator
		{
			$current = $this_FinalLocator_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4());
		}
		this_KeywordLocator_4=ruleKeywordLocator
		{
			$current = $this_KeywordLocator_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5());
		}
		this_ReferredLocator_5=ruleReferredLocator
		{
			$current = $this_ReferredLocator_5.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6());
		}
		this_ReturnsLocator_6=ruleReturnsLocator
		{
			$current = $this_ReturnsLocator_6.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleAnyAssignmentLocator
entryRuleAnyAssignmentLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAnyAssignmentLocatorRule()); }
	iv_ruleAnyAssignmentLocator=ruleAnyAssignmentLocator
	{ $current=$iv_ruleAnyAssignmentLocator.current; }
	EOF;

// Rule AnyAssignmentLocator
ruleAnyAssignmentLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0(),
					$current);
			}
		)
		otherlv_1='any-assignment'
		{
			newLeafNode(otherlv_1, grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());
		}
	)
;

// Entry rule entryRuleAnyElementLocator
entryRuleAnyElementLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAnyElementLocatorRule()); }
	iv_ruleAnyElementLocator=ruleAnyElementLocator
	{ $current=$iv_ruleAnyElementLocator.current; }
	EOF;

// Rule AnyElementLocator
ruleAnyElementLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0(),
					$current);
			}
		)
		otherlv_1='any-element'
		{
			newLeafNode(otherlv_1, grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());
		}
	)
;

// Entry rule entryRuleAssignmentLocator
entryRuleAssignmentLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAssignmentLocatorRule()); }
	iv_ruleAssignmentLocator=ruleAssignmentLocator
	{ $current=$iv_ruleAssignmentLocator.current; }
	EOF;

// Rule AssignmentLocator
ruleAssignmentLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='assignment'
		{
			newLeafNode(otherlv_0, grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());
		}
		(
			(
				(
					(
						{
							/* */
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getAssignmentLocatorRule());
							}
						}
						otherlv_1=RULE_ID
						{
							newLeafNode(otherlv_1, grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());
						}
					)
				)
				otherlv_2='::'
				{
					newLeafNode(otherlv_2, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());
				}
			)?
			(
				(
					{
						/* */
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getAssignmentLocatorRule());
						}
					}
					otherlv_3=RULE_ID
					{
						newLeafNode(otherlv_3, grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());
					}
				)
			)
			otherlv_4='::'
			{
				newLeafNode(otherlv_4, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());
			}
		)?
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getAssignmentLocatorRule());
					}
				}
				otherlv_5=RULE_ID
				{
					newLeafNode(otherlv_5, grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());
				}
			)
		)
	)
;

// Entry rule entryRuleFinalLocator
entryRuleFinalLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFinalLocatorRule()); }
	iv_ruleFinalLocator=ruleFinalLocator
	{ $current=$iv_ruleFinalLocator.current; }
	EOF;

// Rule FinalLocator
ruleFinalLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0(),
					$current);
			}
		)
		otherlv_1='final'
		{
			newLeafNode(otherlv_1, grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());
		}
	)
;

// Entry rule entryRuleKeywordLocator
entryRuleKeywordLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getKeywordLocatorRule()); }
	iv_ruleKeywordLocator=ruleKeywordLocator
	{ $current=$iv_ruleKeywordLocator.current; }
	EOF;

// Rule KeywordLocator
ruleKeywordLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_string_0_0=RULE_STRING
			{
				newLeafNode(lv_string_0_0, grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getKeywordLocatorRule());
				}
				setWithLastConsumed(
					$current,
					"string",
					lv_string_0_0,
					"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");
			}
		)
	)
;

// Entry rule entryRuleReturnsLocator
entryRuleReturnsLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReturnsLocatorRule()); }
	iv_ruleReturnsLocator=ruleReturnsLocator
	{ $current=$iv_ruleReturnsLocator.current; }
	EOF;

// Rule ReturnsLocator
ruleReturnsLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='returns'
		{
			newLeafNode(otherlv_0, grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());
		}
		(
			(
				(
					{
						/* */
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getReturnsLocatorRule());
						}
					}
					otherlv_1=RULE_ID
					{
						newLeafNode(otherlv_1, grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());
					}
				)
			)
			otherlv_2='::'
			{
				newLeafNode(otherlv_2, grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());
			}
		)?
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReturnsLocatorRule());
					}
				}
				otherlv_3=RULE_ID
				{
					newLeafNode(otherlv_3, grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());
				}
			)
		)
	)
;

// Entry rule entryRuleReferredLocator
entryRuleReferredLocator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferredLocatorRule()); }
	iv_ruleReferredLocator=ruleReferredLocator
	{ $current=$iv_ruleReferredLocator.current; }
	EOF;

// Rule ReferredLocator
ruleReferredLocator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(
					{
						/* */
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getReferredLocatorRule());
						}
					}
					otherlv_0=RULE_ID
					{
						newLeafNode(otherlv_0, grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
					}
				)
			)
			otherlv_1='::'
			{
				newLeafNode(otherlv_1, grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());
			}
		)?
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReferredLocatorRule());
					}
				}
				otherlv_2=RULE_ID
				{
					newLeafNode(otherlv_2, grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());
				}
			)
		)
	)
;

// Entry rule entryRuleSegmentDeclaration
entryRuleSegmentDeclaration returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSegmentDeclarationRule()); }
	iv_ruleSegmentDeclaration=ruleSegmentDeclaration
	{ $current=$iv_ruleSegmentDeclaration.current; }
	EOF;

// Rule SegmentDeclaration
ruleSegmentDeclaration returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='segment'
		{
			newLeafNode(otherlv_0, grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSegmentDeclarationRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0());
				}
				lv_ownedSegment_2_0=ruleSegment
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSegmentDeclarationRule());
					}
					set(
						$current,
						"ownedSegment",
						lv_ownedSegment_2_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Segment");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=';'
		{
			newLeafNode(otherlv_3, grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3());
		}
	)
;

// Entry rule entryRuleSegment
entryRuleSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSegmentRule()); }
	iv_ruleSegment=ruleSegment
	{ $current=$iv_ruleSegment.current; }
	EOF;

// Rule Segment
ruleSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0());
		}
		this_CustomSegment_0=ruleCustomSegment
		{
			$current = $this_CustomSegment_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1());
		}
		this_HalfNewLineSegment_1=ruleHalfNewLineSegment
		{
			$current = $this_HalfNewLineSegment_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2());
		}
		this_NewLineSegment_2=ruleNewLineSegment
		{
			$current = $this_NewLineSegment_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3());
		}
		this_NoSpaceSegment_3=ruleNoSpaceSegment
		{
			$current = $this_NoSpaceSegment_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4());
		}
		this_PopSegment_4=rulePopSegment
		{
			$current = $this_PopSegment_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5());
		}
		this_PostCommentSegment_5=rulePostCommentSegment
		{
			$current = $this_PostCommentSegment_5.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6());
		}
		this_PreCommentSegment_6=rulePreCommentSegment
		{
			$current = $this_PreCommentSegment_6.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7());
		}
		this_PushSegment_7=rulePushSegment
		{
			$current = $this_PushSegment_7.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8());
		}
		this_SoftNewLineSegment_8=ruleSoftNewLineSegment
		{
			$current = $this_SoftNewLineSegment_8.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9());
		}
		this_SoftSpaceSegment_9=ruleSoftSpaceSegment
		{
			$current = $this_SoftSpaceSegment_9.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10());
		}
		this_StringSegment_10=ruleStringSegment
		{
			$current = $this_StringSegment_10.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11());
		}
		this_ValueSegment_11=ruleValueSegment
		{
			$current = $this_ValueSegment_11.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12());
		}
		this_WrapAnchorSegment_12=ruleWrapAnchorSegment
		{
			$current = $this_WrapAnchorSegment_12.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13());
		}
		this_WrapBeginAllSegment_13=ruleWrapBeginAllSegment
		{
			$current = $this_WrapBeginAllSegment_13.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14());
		}
		this_WrapBeginSomeSegment_14=ruleWrapBeginSomeSegment
		{
			$current = $this_WrapBeginSomeSegment_14.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15());
		}
		this_WrapEndSegment_15=ruleWrapEndSegment
		{
			$current = $this_WrapEndSegment_15.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			/* */
		}
		{
			newCompositeNode(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16());
		}
		this_WrapHereSegment_16=ruleWrapHereSegment
		{
			$current = $this_WrapHereSegment_16.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleCustomSegment
entryRuleCustomSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCustomSegmentRule()); }
	iv_ruleCustomSegment=ruleCustomSegment
	{ $current=$iv_ruleCustomSegment.current; }
	EOF;

// Rule CustomSegment
ruleCustomSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='custom'
		{
			newLeafNode(otherlv_0, grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());
		}
		(
			(
				lv_supportClassName_1_0=RULE_STRING
				{
					newLeafNode(lv_supportClassName_1_0, grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getCustomSegmentRule());
					}
					setWithLastConsumed(
						$current,
						"supportClassName",
						lv_supportClassName_1_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");
				}
			)
		)
	)
;

// Entry rule entryRuleHalfNewLineSegment
entryRuleHalfNewLineSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getHalfNewLineSegmentRule()); }
	iv_ruleHalfNewLineSegment=ruleHalfNewLineSegment
	{ $current=$iv_ruleHalfNewLineSegment.current; }
	EOF;

// Rule HalfNewLineSegment
ruleHalfNewLineSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='half-new-line'
		{
			newLeafNode(otherlv_1, grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1());
		}
	)
;

// Entry rule entryRuleNewLineSegment
entryRuleNewLineSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNewLineSegmentRule()); }
	iv_ruleNewLineSegment=ruleNewLineSegment
	{ $current=$iv_ruleNewLineSegment.current; }
	EOF;

// Rule NewLineSegment
ruleNewLineSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='new-line'
		{
			newLeafNode(otherlv_1, grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1());
		}
	)
;

// Entry rule entryRuleNoSpaceSegment
entryRuleNoSpaceSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNoSpaceSegmentRule()); }
	iv_ruleNoSpaceSegment=ruleNoSpaceSegment
	{ $current=$iv_ruleNoSpaceSegment.current; }
	EOF;

// Rule NoSpaceSegment
ruleNoSpaceSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='no-space'
		{
			newLeafNode(otherlv_1, grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1());
		}
	)
;

// Entry rule entryRulePopSegment
entryRulePopSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPopSegmentRule()); }
	iv_rulePopSegment=rulePopSegment
	{ $current=$iv_rulePopSegment.current; }
	EOF;

// Rule PopSegment
rulePopSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getPopSegmentAccess().getPopSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='pop'
		{
			newLeafNode(otherlv_1, grammarAccess.getPopSegmentAccess().getPopKeyword_1());
		}
	)
;

// Entry rule entryRulePostCommentSegment
entryRulePostCommentSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPostCommentSegmentRule()); }
	iv_rulePostCommentSegment=rulePostCommentSegment
	{ $current=$iv_rulePostCommentSegment.current; }
	EOF;

// Rule PostCommentSegment
rulePostCommentSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='post-comment'
		{
			newLeafNode(otherlv_1, grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1());
		}
	)
;

// Entry rule entryRulePreCommentSegment
entryRulePreCommentSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPreCommentSegmentRule()); }
	iv_rulePreCommentSegment=rulePreCommentSegment
	{ $current=$iv_rulePreCommentSegment.current; }
	EOF;

// Rule PreCommentSegment
rulePreCommentSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='pre-comment'
		{
			newLeafNode(otherlv_1, grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1());
		}
	)
;

// Entry rule entryRulePushSegment
entryRulePushSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPushSegmentRule()); }
	iv_rulePushSegment=rulePushSegment
	{ $current=$iv_rulePushSegment.current; }
	EOF;

// Rule PushSegment
rulePushSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getPushSegmentAccess().getPushSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='push'
		{
			newLeafNode(otherlv_1, grammarAccess.getPushSegmentAccess().getPushKeyword_1());
		}
	)
;

// Entry rule entryRuleSoftNewLineSegment
entryRuleSoftNewLineSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSoftNewLineSegmentRule()); }
	iv_ruleSoftNewLineSegment=ruleSoftNewLineSegment
	{ $current=$iv_ruleSoftNewLineSegment.current; }
	EOF;

// Rule SoftNewLineSegment
ruleSoftNewLineSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='soft-new-line'
		{
			newLeafNode(otherlv_1, grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1());
		}
	)
;

// Entry rule entryRuleSoftSpaceSegment
entryRuleSoftSpaceSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSoftSpaceSegmentRule()); }
	iv_ruleSoftSpaceSegment=ruleSoftSpaceSegment
	{ $current=$iv_ruleSoftSpaceSegment.current; }
	EOF;

// Rule SoftSpaceSegment
ruleSoftSpaceSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='soft-space'
		{
			newLeafNode(otherlv_1, grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1());
		}
	)
;

// Entry rule entryRuleStringSegment
entryRuleStringSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStringSegmentRule()); }
	iv_ruleStringSegment=ruleStringSegment
	{ $current=$iv_ruleStringSegment.current; }
	EOF;

// Rule StringSegment
ruleStringSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='string'
		{
			newLeafNode(otherlv_0, grammarAccess.getStringSegmentAccess().getStringKeyword_0());
		}
		(
			(
				lv_string_1_0=RULE_STRING
				{
					newLeafNode(lv_string_1_0, grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getStringSegmentRule());
					}
					setWithLastConsumed(
						$current,
						"string",
						lv_string_1_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");
				}
			)
		)
		(
			(
				lv_printable_2_0='printable'
				{
					newLeafNode(lv_printable_2_0, grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getStringSegmentRule());
					}
					setWithLastConsumed($current, "printable", lv_printable_2_0 != null, "printable");
				}
			)
		)
	)
;

// Entry rule entryRuleValueSegment
entryRuleValueSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getValueSegmentRule()); }
	iv_ruleValueSegment=ruleValueSegment
	{ $current=$iv_ruleValueSegment.current; }
	EOF;

// Rule ValueSegment
ruleValueSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getValueSegmentAccess().getValueSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='value'
		{
			newLeafNode(otherlv_1, grammarAccess.getValueSegmentAccess().getValueKeyword_1());
		}
	)
;

// Entry rule entryRuleWrapAnchorSegment
entryRuleWrapAnchorSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWrapAnchorSegmentRule()); }
	iv_ruleWrapAnchorSegment=ruleWrapAnchorSegment
	{ $current=$iv_ruleWrapAnchorSegment.current; }
	EOF;

// Rule WrapAnchorSegment
ruleWrapAnchorSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='wrap-anchor'
		{
			newLeafNode(otherlv_1, grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1());
		}
	)
;

// Entry rule entryRuleWrapBeginAllSegment
entryRuleWrapBeginAllSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWrapBeginAllSegmentRule()); }
	iv_ruleWrapBeginAllSegment=ruleWrapBeginAllSegment
	{ $current=$iv_ruleWrapBeginAllSegment.current; }
	EOF;

// Rule WrapBeginAllSegment
ruleWrapBeginAllSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='wrap-begin-all'
		{
			newLeafNode(otherlv_1, grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1());
		}
	)
;

// Entry rule entryRuleWrapBeginSomeSegment
entryRuleWrapBeginSomeSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWrapBeginSomeSegmentRule()); }
	iv_ruleWrapBeginSomeSegment=ruleWrapBeginSomeSegment
	{ $current=$iv_ruleWrapBeginSomeSegment.current; }
	EOF;

// Rule WrapBeginSomeSegment
ruleWrapBeginSomeSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='wrap-begin-some'
		{
			newLeafNode(otherlv_1, grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1());
		}
	)
;

// Entry rule entryRuleWrapEndSegment
entryRuleWrapEndSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWrapEndSegmentRule()); }
	iv_ruleWrapEndSegment=ruleWrapEndSegment
	{ $current=$iv_ruleWrapEndSegment.current; }
	EOF;

// Rule WrapEndSegment
ruleWrapEndSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='wrap-end'
		{
			newLeafNode(otherlv_1, grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1());
		}
	)
;

// Entry rule entryRuleWrapHereSegment
entryRuleWrapHereSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWrapHereSegmentRule()); }
	iv_ruleWrapHereSegment=ruleWrapHereSegment
	{ $current=$iv_ruleWrapHereSegment.current; }
	EOF;

// Rule WrapHereSegment
ruleWrapHereSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				/* */
			}
			{
				$current = forceCreateModelElement(
					grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0(),
					$current);
			}
		)
		otherlv_1='wrap-here'
		{
			newLeafNode(otherlv_1, grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1());
		}
	)
;

// Entry rule entryRuleReferredSegment
entryRuleReferredSegment returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferredSegmentRule()); }
	iv_ruleReferredSegment=ruleReferredSegment
	{ $current=$iv_ruleReferredSegment.current; }
	EOF;

// Rule ReferredSegment
ruleReferredSegment returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				(
					{
						/* */
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getReferredSegmentRule());
						}
					}
					otherlv_0=RULE_ID
					{
						newLeafNode(otherlv_0, grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
					}
				)
			)
			otherlv_1='::'
			{
				newLeafNode(otherlv_1, grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());
			}
		)?
		(
			(
				{
					/* */
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReferredSegmentRule());
					}
				}
				otherlv_2=RULE_ID
				{
					newLeafNode(otherlv_2, grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());
				}
			)
		)
	)
;

// Entry rule entryRuleIdiom
entryRuleIdiom returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIdiomRule()); }
	iv_ruleIdiom=ruleIdiom
	{ $current=$iv_ruleIdiom.current; }
	EOF;

// Rule Idiom
ruleIdiom returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_mixin_0_0='mixin'
				{
					newLeafNode(lv_mixin_0_0, grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getIdiomRule());
					}
					setWithLastConsumed($current, "mixin", lv_mixin_0_0 != null, "mixin");
				}
			)
		)?
		otherlv_1='idiom'
		{
			newLeafNode(otherlv_1, grammarAccess.getIdiomAccess().getIdiomKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getIdiomRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");
				}
			)
		)
		(
			otherlv_3='for'
			{
				newLeafNode(otherlv_3, grammarAccess.getIdiomAccess().getForKeyword_3_0());
			}
			(
				(
					(
						{
							/* */
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getIdiomRule());
							}
						}
						otherlv_4=RULE_ID
						{
							newLeafNode(otherlv_4, grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());
						}
					)
				)
				otherlv_5='::'
				{
					newLeafNode(otherlv_5, grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());
				}
			)?
			(
				(
					{
						/* */
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getIdiomRule());
						}
					}
					otherlv_6=RULE_ID
					{
						newLeafNode(otherlv_6, grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());
					}
				)
			)
		)?
		(
			otherlv_7='in'
			{
				newLeafNode(otherlv_7, grammarAccess.getIdiomAccess().getInKeyword_4_0());
			}
			(
				(
					lv_inRuleRegex_8_0=RULE_STRING
					{
						newLeafNode(lv_inRuleRegex_8_0, grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getIdiomRule());
						}
						setWithLastConsumed(
							$current,
							"inRuleRegex",
							lv_inRuleRegex_8_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");
					}
				)
			)
		)?
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0());
					}
					lv_ownedSubIdioms_9_0=ruleSubIdiom
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getIdiomRule());
						}
						add(
							$current,
							"ownedSubIdioms",
							lv_ownedSubIdioms_9_0,
							"org.eclipse.ocl.examples.xtext.idioms.Idioms.SubIdiom");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				otherlv_10='{'
				{
					newLeafNode(otherlv_10, grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0());
						}
						lv_ownedSubIdioms_11_0=ruleSubIdiom
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getIdiomRule());
							}
							add(
								$current,
								"ownedSubIdioms",
								lv_ownedSubIdioms_11_0,
								"org.eclipse.ocl.examples.xtext.idioms.Idioms.SubIdiom");
							afterParserOrEnumRuleCall();
						}
					)
				)*
				otherlv_12='}'
				{
					newLeafNode(otherlv_12, grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());
				}
			)
		)
	)
;

// Entry rule entryRuleSubIdiom
entryRuleSubIdiom returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSubIdiomRule()); }
	iv_ruleSubIdiom=ruleSubIdiom
	{ $current=$iv_ruleSubIdiom.current; }
	EOF;

// Rule SubIdiom
ruleSubIdiom returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='at'
		{
			newLeafNode(otherlv_0, grammarAccess.getSubIdiomAccess().getAtKeyword_0());
		}
		(
			(
				(
					lv_all_1_0='all'
					{
						newLeafNode(lv_all_1_0, grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getSubIdiomRule());
						}
						setWithLastConsumed($current, "all", lv_all_1_0 != null, "all");
					}
				)
			)
			    |
			otherlv_2='each'
			{
				newLeafNode(otherlv_2, grammarAccess.getSubIdiomAccess().getEachKeyword_1_1());
			}
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
				}
				lv_ownedLocator_3_0=ruleLocator
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSubIdiomRule());
					}
					set(
						$current,
						"ownedLocator",
						lv_ownedLocator_3_0,
						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Locator");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='do'
			{
				newLeafNode(otherlv_4, grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());
			}
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0());
						}
						lv_ownedSegments_5_1=ruleSegment
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSubIdiomRule());
							}
							add(
								$current,
								"ownedSegments",
								lv_ownedSegments_5_1,
								"org.eclipse.ocl.examples.xtext.idioms.Idioms.Segment");
							afterParserOrEnumRuleCall();
						}
						    |
						{
							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1());
						}
						lv_ownedSegments_5_2=ruleReferredSegment
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSubIdiomRule());
							}
							add(
								$current,
								"ownedSegments",
								lv_ownedSegments_5_2,
								"org.eclipse.ocl.examples.xtext.idioms.Idioms.ReferredSegment");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_6=';'
		{
			newLeafNode(otherlv_6, grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4());
		}
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
