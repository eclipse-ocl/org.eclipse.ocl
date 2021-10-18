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
	superClass=AbstractInternalContentAssistParser;
	backtrack=true;
}

@lexer::header {
package org.eclipse.ocl.examples.xtext.idioms.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import.
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.ocl.examples.xtext.idioms.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.ocl.examples.xtext.idioms.services.IdiomsGrammarAccess;

}
@parser::members {
	private IdiomsGrammarAccess grammarAccess;

	public void setGrammarAccess(IdiomsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleIdiomsModel
entryRuleIdiomsModel
:
{ before(grammarAccess.getIdiomsModelRule()); }
	 ruleIdiomsModel
{ after(grammarAccess.getIdiomsModelRule()); }
	 EOF
;

// Rule IdiomsModel
ruleIdiomsModel
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getGroup()); }
		(rule__IdiomsModel__Group__0)
		{ after(grammarAccess.getIdiomsModelAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEPackageImport
entryRuleEPackageImport
:
{ before(grammarAccess.getEPackageImportRule()); }
	 ruleEPackageImport
{ after(grammarAccess.getEPackageImportRule()); }
	 EOF
;

// Rule EPackageImport
ruleEPackageImport
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEPackageImportAccess().getGroup()); }
		(rule__EPackageImport__Group__0)
		{ after(grammarAccess.getEPackageImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIdiomsImport
entryRuleIdiomsImport
:
{ before(grammarAccess.getIdiomsImportRule()); }
	 ruleIdiomsImport
{ after(grammarAccess.getIdiomsImportRule()); }
	 EOF
;

// Rule IdiomsImport
ruleIdiomsImport
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIdiomsImportAccess().getGroup()); }
		(rule__IdiomsImport__Group__0)
		{ after(grammarAccess.getIdiomsImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleLocatorDeclaration
entryRuleLocatorDeclaration
:
{ before(grammarAccess.getLocatorDeclarationRule()); }
	 ruleLocatorDeclaration
{ after(grammarAccess.getLocatorDeclarationRule()); }
	 EOF
;

// Rule LocatorDeclaration
ruleLocatorDeclaration
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getLocatorDeclarationAccess().getGroup()); }
		(rule__LocatorDeclaration__Group__0)
		{ after(grammarAccess.getLocatorDeclarationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleLocator
entryRuleLocator
:
{ before(grammarAccess.getLocatorRule()); }
	 ruleLocator
{ after(grammarAccess.getLocatorRule()); }
	 EOF
;

// Rule Locator
ruleLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getLocatorAccess().getAlternatives()); }
		(rule__Locator__Alternatives)
		{ after(grammarAccess.getLocatorAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAnyAssignmentLocator
entryRuleAnyAssignmentLocator
:
{ before(grammarAccess.getAnyAssignmentLocatorRule()); }
	 ruleAnyAssignmentLocator
{ after(grammarAccess.getAnyAssignmentLocatorRule()); }
	 EOF
;

// Rule AnyAssignmentLocator
ruleAnyAssignmentLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAnyAssignmentLocatorAccess().getGroup()); }
		(rule__AnyAssignmentLocator__Group__0)
		{ after(grammarAccess.getAnyAssignmentLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAnyElementLocator
entryRuleAnyElementLocator
:
{ before(grammarAccess.getAnyElementLocatorRule()); }
	 ruleAnyElementLocator
{ after(grammarAccess.getAnyElementLocatorRule()); }
	 EOF
;

// Rule AnyElementLocator
ruleAnyElementLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAnyElementLocatorAccess().getGroup()); }
		(rule__AnyElementLocator__Group__0)
		{ after(grammarAccess.getAnyElementLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAssignmentLocator
entryRuleAssignmentLocator
:
{ before(grammarAccess.getAssignmentLocatorRule()); }
	 ruleAssignmentLocator
{ after(grammarAccess.getAssignmentLocatorRule()); }
	 EOF
;

// Rule AssignmentLocator
ruleAssignmentLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAssignmentLocatorAccess().getGroup()); }
		(rule__AssignmentLocator__Group__0)
		{ after(grammarAccess.getAssignmentLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFinalLocator
entryRuleFinalLocator
:
{ before(grammarAccess.getFinalLocatorRule()); }
	 ruleFinalLocator
{ after(grammarAccess.getFinalLocatorRule()); }
	 EOF
;

// Rule FinalLocator
ruleFinalLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFinalLocatorAccess().getGroup()); }
		(rule__FinalLocator__Group__0)
		{ after(grammarAccess.getFinalLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleKeywordLocator
entryRuleKeywordLocator
:
{ before(grammarAccess.getKeywordLocatorRule()); }
	 ruleKeywordLocator
{ after(grammarAccess.getKeywordLocatorRule()); }
	 EOF
;

// Rule KeywordLocator
ruleKeywordLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getKeywordLocatorAccess().getStringAssignment()); }
		(rule__KeywordLocator__StringAssignment)
		{ after(grammarAccess.getKeywordLocatorAccess().getStringAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReturnsLocator
entryRuleReturnsLocator
:
{ before(grammarAccess.getReturnsLocatorRule()); }
	 ruleReturnsLocator
{ after(grammarAccess.getReturnsLocatorRule()); }
	 EOF
;

// Rule ReturnsLocator
ruleReturnsLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReturnsLocatorAccess().getGroup()); }
		(rule__ReturnsLocator__Group__0)
		{ after(grammarAccess.getReturnsLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReferredLocator
entryRuleReferredLocator
:
{ before(grammarAccess.getReferredLocatorRule()); }
	 ruleReferredLocator
{ after(grammarAccess.getReferredLocatorRule()); }
	 EOF
;

// Rule ReferredLocator
ruleReferredLocator
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReferredLocatorAccess().getGroup()); }
		(rule__ReferredLocator__Group__0)
		{ after(grammarAccess.getReferredLocatorAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSegmentDeclaration
entryRuleSegmentDeclaration
:
{ before(grammarAccess.getSegmentDeclarationRule()); }
	 ruleSegmentDeclaration
{ after(grammarAccess.getSegmentDeclarationRule()); }
	 EOF
;

// Rule SegmentDeclaration
ruleSegmentDeclaration
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSegmentDeclarationAccess().getGroup()); }
		(rule__SegmentDeclaration__Group__0)
		{ after(grammarAccess.getSegmentDeclarationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSegment
entryRuleSegment
:
{ before(grammarAccess.getSegmentRule()); }
	 ruleSegment
{ after(grammarAccess.getSegmentRule()); }
	 EOF
;

// Rule Segment
ruleSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSegmentAccess().getAlternatives()); }
		(rule__Segment__Alternatives)
		{ after(grammarAccess.getSegmentAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleCustomSegment
entryRuleCustomSegment
:
{ before(grammarAccess.getCustomSegmentRule()); }
	 ruleCustomSegment
{ after(grammarAccess.getCustomSegmentRule()); }
	 EOF
;

// Rule CustomSegment
ruleCustomSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getCustomSegmentAccess().getGroup()); }
		(rule__CustomSegment__Group__0)
		{ after(grammarAccess.getCustomSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleHalfNewLineSegment
entryRuleHalfNewLineSegment
:
{ before(grammarAccess.getHalfNewLineSegmentRule()); }
	 ruleHalfNewLineSegment
{ after(grammarAccess.getHalfNewLineSegmentRule()); }
	 EOF
;

// Rule HalfNewLineSegment
ruleHalfNewLineSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getHalfNewLineSegmentAccess().getGroup()); }
		(rule__HalfNewLineSegment__Group__0)
		{ after(grammarAccess.getHalfNewLineSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNewLineSegment
entryRuleNewLineSegment
:
{ before(grammarAccess.getNewLineSegmentRule()); }
	 ruleNewLineSegment
{ after(grammarAccess.getNewLineSegmentRule()); }
	 EOF
;

// Rule NewLineSegment
ruleNewLineSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNewLineSegmentAccess().getGroup()); }
		(rule__NewLineSegment__Group__0)
		{ after(grammarAccess.getNewLineSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNoSpaceSegment
entryRuleNoSpaceSegment
:
{ before(grammarAccess.getNoSpaceSegmentRule()); }
	 ruleNoSpaceSegment
{ after(grammarAccess.getNoSpaceSegmentRule()); }
	 EOF
;

// Rule NoSpaceSegment
ruleNoSpaceSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNoSpaceSegmentAccess().getGroup()); }
		(rule__NoSpaceSegment__Group__0)
		{ after(grammarAccess.getNoSpaceSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePopSegment
entryRulePopSegment
:
{ before(grammarAccess.getPopSegmentRule()); }
	 rulePopSegment
{ after(grammarAccess.getPopSegmentRule()); }
	 EOF
;

// Rule PopSegment
rulePopSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPopSegmentAccess().getGroup()); }
		(rule__PopSegment__Group__0)
		{ after(grammarAccess.getPopSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePostCommentSegment
entryRulePostCommentSegment
:
{ before(grammarAccess.getPostCommentSegmentRule()); }
	 rulePostCommentSegment
{ after(grammarAccess.getPostCommentSegmentRule()); }
	 EOF
;

// Rule PostCommentSegment
rulePostCommentSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPostCommentSegmentAccess().getGroup()); }
		(rule__PostCommentSegment__Group__0)
		{ after(grammarAccess.getPostCommentSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePreCommentSegment
entryRulePreCommentSegment
:
{ before(grammarAccess.getPreCommentSegmentRule()); }
	 rulePreCommentSegment
{ after(grammarAccess.getPreCommentSegmentRule()); }
	 EOF
;

// Rule PreCommentSegment
rulePreCommentSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPreCommentSegmentAccess().getGroup()); }
		(rule__PreCommentSegment__Group__0)
		{ after(grammarAccess.getPreCommentSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePushSegment
entryRulePushSegment
:
{ before(grammarAccess.getPushSegmentRule()); }
	 rulePushSegment
{ after(grammarAccess.getPushSegmentRule()); }
	 EOF
;

// Rule PushSegment
rulePushSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPushSegmentAccess().getGroup()); }
		(rule__PushSegment__Group__0)
		{ after(grammarAccess.getPushSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSoftNewLineSegment
entryRuleSoftNewLineSegment
:
{ before(grammarAccess.getSoftNewLineSegmentRule()); }
	 ruleSoftNewLineSegment
{ after(grammarAccess.getSoftNewLineSegmentRule()); }
	 EOF
;

// Rule SoftNewLineSegment
ruleSoftNewLineSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSoftNewLineSegmentAccess().getGroup()); }
		(rule__SoftNewLineSegment__Group__0)
		{ after(grammarAccess.getSoftNewLineSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSoftSpaceSegment
entryRuleSoftSpaceSegment
:
{ before(grammarAccess.getSoftSpaceSegmentRule()); }
	 ruleSoftSpaceSegment
{ after(grammarAccess.getSoftSpaceSegmentRule()); }
	 EOF
;

// Rule SoftSpaceSegment
ruleSoftSpaceSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSoftSpaceSegmentAccess().getGroup()); }
		(rule__SoftSpaceSegment__Group__0)
		{ after(grammarAccess.getSoftSpaceSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStringSegment
entryRuleStringSegment
:
{ before(grammarAccess.getStringSegmentRule()); }
	 ruleStringSegment
{ after(grammarAccess.getStringSegmentRule()); }
	 EOF
;

// Rule StringSegment
ruleStringSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStringSegmentAccess().getGroup()); }
		(rule__StringSegment__Group__0)
		{ after(grammarAccess.getStringSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleValueSegment
entryRuleValueSegment
:
{ before(grammarAccess.getValueSegmentRule()); }
	 ruleValueSegment
{ after(grammarAccess.getValueSegmentRule()); }
	 EOF
;

// Rule ValueSegment
ruleValueSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getValueSegmentAccess().getGroup()); }
		(rule__ValueSegment__Group__0)
		{ after(grammarAccess.getValueSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWrapAnchorSegment
entryRuleWrapAnchorSegment
:
{ before(grammarAccess.getWrapAnchorSegmentRule()); }
	 ruleWrapAnchorSegment
{ after(grammarAccess.getWrapAnchorSegmentRule()); }
	 EOF
;

// Rule WrapAnchorSegment
ruleWrapAnchorSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWrapAnchorSegmentAccess().getGroup()); }
		(rule__WrapAnchorSegment__Group__0)
		{ after(grammarAccess.getWrapAnchorSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWrapBeginAllSegment
entryRuleWrapBeginAllSegment
:
{ before(grammarAccess.getWrapBeginAllSegmentRule()); }
	 ruleWrapBeginAllSegment
{ after(grammarAccess.getWrapBeginAllSegmentRule()); }
	 EOF
;

// Rule WrapBeginAllSegment
ruleWrapBeginAllSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWrapBeginAllSegmentAccess().getGroup()); }
		(rule__WrapBeginAllSegment__Group__0)
		{ after(grammarAccess.getWrapBeginAllSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWrapBeginSomeSegment
entryRuleWrapBeginSomeSegment
:
{ before(grammarAccess.getWrapBeginSomeSegmentRule()); }
	 ruleWrapBeginSomeSegment
{ after(grammarAccess.getWrapBeginSomeSegmentRule()); }
	 EOF
;

// Rule WrapBeginSomeSegment
ruleWrapBeginSomeSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup()); }
		(rule__WrapBeginSomeSegment__Group__0)
		{ after(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWrapEndSegment
entryRuleWrapEndSegment
:
{ before(grammarAccess.getWrapEndSegmentRule()); }
	 ruleWrapEndSegment
{ after(grammarAccess.getWrapEndSegmentRule()); }
	 EOF
;

// Rule WrapEndSegment
ruleWrapEndSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWrapEndSegmentAccess().getGroup()); }
		(rule__WrapEndSegment__Group__0)
		{ after(grammarAccess.getWrapEndSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleWrapHereSegment
entryRuleWrapHereSegment
:
{ before(grammarAccess.getWrapHereSegmentRule()); }
	 ruleWrapHereSegment
{ after(grammarAccess.getWrapHereSegmentRule()); }
	 EOF
;

// Rule WrapHereSegment
ruleWrapHereSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getWrapHereSegmentAccess().getGroup()); }
		(rule__WrapHereSegment__Group__0)
		{ after(grammarAccess.getWrapHereSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReferredSegment
entryRuleReferredSegment
:
{ before(grammarAccess.getReferredSegmentRule()); }
	 ruleReferredSegment
{ after(grammarAccess.getReferredSegmentRule()); }
	 EOF
;

// Rule ReferredSegment
ruleReferredSegment
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReferredSegmentAccess().getGroup()); }
		(rule__ReferredSegment__Group__0)
		{ after(grammarAccess.getReferredSegmentAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIdiom
entryRuleIdiom
:
{ before(grammarAccess.getIdiomRule()); }
	 ruleIdiom
{ after(grammarAccess.getIdiomRule()); }
	 EOF
;

// Rule Idiom
ruleIdiom
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIdiomAccess().getGroup()); }
		(rule__Idiom__Group__0)
		{ after(grammarAccess.getIdiomAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSubIdiom
entryRuleSubIdiom
:
{ before(grammarAccess.getSubIdiomRule()); }
	 ruleSubIdiom
{ after(grammarAccess.getSubIdiomRule()); }
	 EOF
;

// Rule SubIdiom
ruleSubIdiom
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSubIdiomAccess().getGroup()); }
		(rule__SubIdiom__Group__0)
		{ after(grammarAccess.getSubIdiomAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Alternatives_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_5_0()); }
		(rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0)
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_5_0()); }
	)
	|
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_5_1()); }
		(rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1)
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_5_1()); }
	)
	|
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_5_2()); }
		(rule__IdiomsModel__OwnedIdiomsAssignment_5_2)
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_5_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Locator__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0()); }
		ruleAnyAssignmentLocator
		{ after(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1()); }
		ruleAnyElementLocator
		{ after(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2()); }
		ruleAssignmentLocator
		{ after(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3()); }
		ruleFinalLocator
		{ after(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4()); }
		ruleKeywordLocator
		{ after(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5()); }
		ruleReferredLocator
		{ after(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5()); }
	)
	|
	(
		{ before(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6()); }
		ruleReturnsLocator
		{ after(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Segment__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0()); }
		ruleCustomSegment
		{ after(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1()); }
		ruleHalfNewLineSegment
		{ after(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2()); }
		ruleNewLineSegment
		{ after(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3()); }
		ruleNoSpaceSegment
		{ after(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4()); }
		rulePopSegment
		{ after(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5()); }
		rulePostCommentSegment
		{ after(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6()); }
		rulePreCommentSegment
		{ after(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7()); }
		rulePushSegment
		{ after(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8()); }
		ruleSoftNewLineSegment
		{ after(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9()); }
		ruleSoftSpaceSegment
		{ after(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10()); }
		ruleStringSegment
		{ after(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11()); }
		ruleValueSegment
		{ after(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12()); }
		ruleWrapAnchorSegment
		{ after(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13()); }
		ruleWrapBeginAllSegment
		{ after(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14()); }
		ruleWrapBeginSomeSegment
		{ after(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15()); }
		ruleWrapEndSegment
		{ after(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15()); }
	)
	|
	(
		{ before(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16()); }
		ruleWrapHereSegment
		{ after(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Alternatives_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0()); }
		(rule__Idiom__OwnedSubIdiomsAssignment_5_0)
		{ after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0()); }
	)
	|
	(
		{ before(grammarAccess.getIdiomAccess().getGroup_5_1()); }
		(rule__Idiom__Group_5_1__0)
		{ after(grammarAccess.getIdiomAccess().getGroup_5_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Alternatives_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0()); }
		(rule__SubIdiom__AllAssignment_1_0)
		{ after(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getSubIdiomAccess().getEachKeyword_1_1()); }
		'each'
		{ after(grammarAccess.getSubIdiomAccess().getEachKeyword_1_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0()); }
		ruleSegment
		{ after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1()); }
		ruleReferredSegment
		{ after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__0__Impl
	rule__IdiomsModel__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getModelKeyword_0()); }
	'model'
	{ after(grammarAccess.getIdiomsModelAccess().getModelKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__1__Impl
	rule__IdiomsModel__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1()); }
	(rule__IdiomsModel__NamesAssignment_1)
	{ after(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__2__Impl
	rule__IdiomsModel__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getGroup_2()); }
	(rule__IdiomsModel__Group_2__0)*
	{ after(grammarAccess.getIdiomsModelAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__3__Impl
	rule__IdiomsModel__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3()); }
	(rule__IdiomsModel__OwnedWithsAssignment_3)*
	{ after(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__4__Impl
	rule__IdiomsModel__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_4()); }
	(rule__IdiomsModel__OwnedImportsAssignment_4)*
	{ after(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getAlternatives_5()); }
	(rule__IdiomsModel__Alternatives_5)*
	{ after(grammarAccess.getIdiomsModelAccess().getAlternatives_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IdiomsModel__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group_2__0__Impl
	rule__IdiomsModel__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0()); }
	'.'
	{ after(grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsModel__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1()); }
	(rule__IdiomsModel__NamesAssignment_2_1)
	{ after(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EPackageImport__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group__0__Impl
	rule__EPackageImport__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getImportKeyword_0()); }
	'import'
	{ after(grammarAccess.getEPackageImportAccess().getImportKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group__1__Impl
	rule__EPackageImport__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1()); }
	(rule__EPackageImport__EPackageAssignment_1)
	{ after(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group__2__Impl
	rule__EPackageImport__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getGroup_2()); }
	(rule__EPackageImport__Group_2__0)?
	{ after(grammarAccess.getEPackageImportAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3()); }
	(';')?
	{ after(grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EPackageImport__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group_2__0__Impl
	rule__EPackageImport__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getAsKeyword_2_0()); }
	'as'
	{ after(grammarAccess.getEPackageImportAccess().getAsKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EPackageImport__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1()); }
	(rule__EPackageImport__AsAssignment_2_1)
	{ after(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IdiomsImport__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group__0__Impl
	rule__IdiomsImport__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getWithKeyword_0()); }
	'with'
	{ after(grammarAccess.getIdiomsImportAccess().getWithKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group__1__Impl
	rule__IdiomsImport__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1()); }
	(rule__IdiomsImport__IdiomsModelAssignment_1)
	{ after(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group__2__Impl
	rule__IdiomsImport__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getGroup_2()); }
	(rule__IdiomsImport__Group_2__0)?
	{ after(grammarAccess.getIdiomsImportAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3()); }
	(';')?
	{ after(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IdiomsImport__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group_2__0__Impl
	rule__IdiomsImport__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0()); }
	'as'
	{ after(grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IdiomsImport__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1()); }
	(rule__IdiomsImport__AsAssignment_2_1)
	{ after(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__LocatorDeclaration__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__LocatorDeclaration__Group__0__Impl
	rule__LocatorDeclaration__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0()); }
	'locator'
	{ after(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__LocatorDeclaration__Group__1__Impl
	rule__LocatorDeclaration__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1()); }
	(rule__LocatorDeclaration__NameAssignment_1)
	{ after(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__LocatorDeclaration__Group__2__Impl
	rule__LocatorDeclaration__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2()); }
	(rule__LocatorDeclaration__OwnedLocatorAssignment_2)
	{ after(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__LocatorDeclaration__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3()); }
	';'
	{ after(grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AnyAssignmentLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AnyAssignmentLocator__Group__0__Impl
	rule__AnyAssignmentLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyAssignmentLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0()); }
	()
	{ after(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyAssignmentLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AnyAssignmentLocator__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyAssignmentLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1()); }
	'any-assignment'
	{ after(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AnyElementLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AnyElementLocator__Group__0__Impl
	rule__AnyElementLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyElementLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0()); }
	()
	{ after(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyElementLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AnyElementLocator__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AnyElementLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1()); }
	'any-element'
	{ after(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AssignmentLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group__0__Impl
	rule__AssignmentLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0()); }
	'assignment'
	{ after(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group__1__Impl
	rule__AssignmentLocator__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getGroup_1()); }
	(rule__AssignmentLocator__Group_1__0)?
	{ after(grammarAccess.getAssignmentLocatorAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2()); }
	(rule__AssignmentLocator__EStructuralFeatureAssignment_2)
	{ after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AssignmentLocator__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group_1__0__Impl
	rule__AssignmentLocator__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0()); }
	(rule__AssignmentLocator__Group_1_0__0)?
	{ after(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group_1__1__Impl
	rule__AssignmentLocator__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1()); }
	(rule__AssignmentLocator__EClassAssignment_1_1)
	{ after(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2()); }
	'::'
	{ after(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AssignmentLocator__Group_1_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group_1_0__0__Impl
	rule__AssignmentLocator__Group_1_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0()); }
	(rule__AssignmentLocator__EPackageAssignment_1_0_0)
	{ after(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AssignmentLocator__Group_1_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__Group_1_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1()); }
	'::'
	{ after(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FinalLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FinalLocator__Group__0__Impl
	rule__FinalLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FinalLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0()); }
	()
	{ after(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FinalLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FinalLocator__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FinalLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1()); }
	'final'
	{ after(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReturnsLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReturnsLocator__Group__0__Impl
	rule__ReturnsLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0()); }
	'returns'
	{ after(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReturnsLocator__Group__1__Impl
	rule__ReturnsLocator__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReturnsLocatorAccess().getGroup_1()); }
	(rule__ReturnsLocator__Group_1__0)?
	{ after(grammarAccess.getReturnsLocatorAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReturnsLocator__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2()); }
	(rule__ReturnsLocator__EClassAssignment_2)
	{ after(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReturnsLocator__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReturnsLocator__Group_1__0__Impl
	rule__ReturnsLocator__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0()); }
	(rule__ReturnsLocator__EPackageAssignment_1_0)
	{ after(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReturnsLocator__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1()); }
	'::'
	{ after(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferredLocator__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredLocator__Group__0__Impl
	rule__ReferredLocator__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredLocatorAccess().getGroup_0()); }
	(rule__ReferredLocator__Group_0__0)?
	{ after(grammarAccess.getReferredLocatorAccess().getGroup_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredLocator__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1()); }
	(rule__ReferredLocator__LocatorDeclarationAssignment_1)
	{ after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferredLocator__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredLocator__Group_0__0__Impl
	rule__ReferredLocator__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0()); }
	(rule__ReferredLocator__IdiomsModelAssignment_0_0)
	{ after(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredLocator__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1()); }
	'::'
	{ after(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SegmentDeclaration__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SegmentDeclaration__Group__0__Impl
	rule__SegmentDeclaration__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0()); }
	'segment'
	{ after(grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SegmentDeclaration__Group__1__Impl
	rule__SegmentDeclaration__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1()); }
	(rule__SegmentDeclaration__NameAssignment_1)
	{ after(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SegmentDeclaration__Group__2__Impl
	rule__SegmentDeclaration__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2()); }
	(rule__SegmentDeclaration__OwnedSegmentAssignment_2)
	{ after(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SegmentDeclaration__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3()); }
	';'
	{ after(grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__CustomSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__CustomSegment__Group__0__Impl
	rule__CustomSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__CustomSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCustomSegmentAccess().getCustomKeyword_0()); }
	'custom'
	{ after(grammarAccess.getCustomSegmentAccess().getCustomKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__CustomSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__CustomSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__CustomSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1()); }
	(rule__CustomSegment__SupportClassNameAssignment_1)
	{ after(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__HalfNewLineSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__HalfNewLineSegment__Group__0__Impl
	rule__HalfNewLineSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__HalfNewLineSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0()); }
	()
	{ after(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__HalfNewLineSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__HalfNewLineSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__HalfNewLineSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1()); }
	'half-new-line'
	{ after(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NewLineSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewLineSegment__Group__0__Impl
	rule__NewLineSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NewLineSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0()); }
	()
	{ after(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NewLineSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NewLineSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NewLineSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1()); }
	'new-line'
	{ after(grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NoSpaceSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NoSpaceSegment__Group__0__Impl
	rule__NoSpaceSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NoSpaceSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0()); }
	()
	{ after(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NoSpaceSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NoSpaceSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NoSpaceSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1()); }
	'no-space'
	{ after(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PopSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PopSegment__Group__0__Impl
	rule__PopSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PopSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0()); }
	()
	{ after(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PopSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PopSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PopSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPopSegmentAccess().getPopKeyword_1()); }
	'pop'
	{ after(grammarAccess.getPopSegmentAccess().getPopKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PostCommentSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PostCommentSegment__Group__0__Impl
	rule__PostCommentSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PostCommentSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0()); }
	()
	{ after(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PostCommentSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PostCommentSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PostCommentSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1()); }
	'post-comment'
	{ after(grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PreCommentSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PreCommentSegment__Group__0__Impl
	rule__PreCommentSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PreCommentSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0()); }
	()
	{ after(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PreCommentSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PreCommentSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PreCommentSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1()); }
	'pre-comment'
	{ after(grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PushSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PushSegment__Group__0__Impl
	rule__PushSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PushSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0()); }
	()
	{ after(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PushSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PushSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PushSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPushSegmentAccess().getPushKeyword_1()); }
	'push'
	{ after(grammarAccess.getPushSegmentAccess().getPushKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SoftNewLineSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SoftNewLineSegment__Group__0__Impl
	rule__SoftNewLineSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftNewLineSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0()); }
	()
	{ after(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftNewLineSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SoftNewLineSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftNewLineSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1()); }
	'soft-new-line'
	{ after(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SoftSpaceSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SoftSpaceSegment__Group__0__Impl
	rule__SoftSpaceSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftSpaceSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0()); }
	()
	{ after(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftSpaceSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SoftSpaceSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SoftSpaceSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1()); }
	'soft-space'
	{ after(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StringSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringSegment__Group__0__Impl
	rule__StringSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringSegmentAccess().getStringKeyword_0()); }
	'string'
	{ after(grammarAccess.getStringSegmentAccess().getStringKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringSegment__Group__1__Impl
	rule__StringSegment__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringSegmentAccess().getStringAssignment_1()); }
	(rule__StringSegment__StringAssignment_1)
	{ after(grammarAccess.getStringSegmentAccess().getStringAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringSegment__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2()); }
	(rule__StringSegment__PrintableAssignment_2)
	{ after(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ValueSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValueSegment__Group__0__Impl
	rule__ValueSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0()); }
	()
	{ after(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValueSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueSegmentAccess().getValueKeyword_1()); }
	'value'
	{ after(grammarAccess.getValueSegmentAccess().getValueKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WrapAnchorSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapAnchorSegment__Group__0__Impl
	rule__WrapAnchorSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapAnchorSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0()); }
	()
	{ after(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapAnchorSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapAnchorSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapAnchorSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1()); }
	'wrap-anchor'
	{ after(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WrapBeginAllSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapBeginAllSegment__Group__0__Impl
	rule__WrapBeginAllSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginAllSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0()); }
	()
	{ after(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginAllSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapBeginAllSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginAllSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1()); }
	'wrap-begin-all'
	{ after(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WrapBeginSomeSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapBeginSomeSegment__Group__0__Impl
	rule__WrapBeginSomeSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginSomeSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0()); }
	()
	{ after(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginSomeSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapBeginSomeSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapBeginSomeSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1()); }
	'wrap-begin-some'
	{ after(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WrapEndSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapEndSegment__Group__0__Impl
	rule__WrapEndSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapEndSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0()); }
	()
	{ after(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapEndSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapEndSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapEndSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1()); }
	'wrap-end'
	{ after(grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__WrapHereSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapHereSegment__Group__0__Impl
	rule__WrapHereSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapHereSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0()); }
	()
	{ after(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapHereSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__WrapHereSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__WrapHereSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1()); }
	'wrap-here'
	{ after(grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferredSegment__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredSegment__Group__0__Impl
	rule__ReferredSegment__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredSegmentAccess().getGroup_0()); }
	(rule__ReferredSegment__Group_0__0)?
	{ after(grammarAccess.getReferredSegmentAccess().getGroup_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredSegment__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1()); }
	(rule__ReferredSegment__SegmentDeclarationAssignment_1)
	{ after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferredSegment__Group_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredSegment__Group_0__0__Impl
	rule__ReferredSegment__Group_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0()); }
	(rule__ReferredSegment__IdiomsModelAssignment_0_0)
	{ after(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferredSegment__Group_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__Group_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1()); }
	'::'
	{ after(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Idiom__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__0__Impl
	rule__Idiom__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getMixinAssignment_0()); }
	(rule__Idiom__MixinAssignment_0)?
	{ after(grammarAccess.getIdiomAccess().getMixinAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__1__Impl
	rule__Idiom__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getIdiomKeyword_1()); }
	'idiom'
	{ after(grammarAccess.getIdiomAccess().getIdiomKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__2__Impl
	rule__Idiom__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getNameAssignment_2()); }
	(rule__Idiom__NameAssignment_2)
	{ after(grammarAccess.getIdiomAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__3__Impl
	rule__Idiom__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getGroup_3()); }
	(rule__Idiom__Group_3__0)?
	{ after(grammarAccess.getIdiomAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__4__Impl
	rule__Idiom__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getGroup_4()); }
	(rule__Idiom__Group_4__0)?
	{ after(grammarAccess.getIdiomAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getAlternatives_5()); }
	(rule__Idiom__Alternatives_5)
	{ after(grammarAccess.getIdiomAccess().getAlternatives_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Idiom__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_3__0__Impl
	rule__Idiom__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getForKeyword_3_0()); }
	'for'
	{ after(grammarAccess.getIdiomAccess().getForKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_3__1__Impl
	rule__Idiom__Group_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getGroup_3_1()); }
	(rule__Idiom__Group_3_1__0)?
	{ after(grammarAccess.getIdiomAccess().getGroup_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2()); }
	(rule__Idiom__ForEClassAssignment_3_2)
	{ after(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Idiom__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_3_1__0__Impl
	rule__Idiom__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0()); }
	(rule__Idiom__ForEPackageAssignment_3_1_0)
	{ after(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1()); }
	'::'
	{ after(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Idiom__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_4__0__Impl
	rule__Idiom__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getInKeyword_4_0()); }
	'in'
	{ after(grammarAccess.getIdiomAccess().getInKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1()); }
	(rule__Idiom__InRuleRegexAssignment_4_1)
	{ after(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Idiom__Group_5_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_5_1__0__Impl
	rule__Idiom__Group_5_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_5_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0()); }
	'{'
	{ after(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_5_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_5_1__1__Impl
	rule__Idiom__Group_5_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_5_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1()); }
	(rule__Idiom__OwnedSubIdiomsAssignment_5_1_1)*
	{ after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_5_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Idiom__Group_5_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__Group_5_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2()); }
	'}'
	{ after(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SubIdiom__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group__0__Impl
	rule__SubIdiom__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getAtKeyword_0()); }
	'at'
	{ after(grammarAccess.getSubIdiomAccess().getAtKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group__1__Impl
	rule__SubIdiom__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getAlternatives_1()); }
	(rule__SubIdiom__Alternatives_1)?
	{ after(grammarAccess.getSubIdiomAccess().getAlternatives_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group__2__Impl
	rule__SubIdiom__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2()); }
	(rule__SubIdiom__OwnedLocatorAssignment_2)
	{ after(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group__3__Impl
	rule__SubIdiom__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getGroup_3()); }
	(rule__SubIdiom__Group_3__0)?
	{ after(grammarAccess.getSubIdiomAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4()); }
	';'
	{ after(grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SubIdiom__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group_3__0__Impl
	rule__SubIdiom__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getDoKeyword_3_0()); }
	'do'
	{ after(grammarAccess.getSubIdiomAccess().getDoKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SubIdiom__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1()); }
	(rule__SubIdiom__OwnedSegmentsAssignment_3_1)*
	{ after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IdiomsModel__NamesAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__NamesAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0()); }
		RULE_ID
		{ after(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__OwnedWithsAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0()); }
		ruleIdiomsImport
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__OwnedImportsAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0()); }
		ruleEPackageImport
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0()); }
		ruleLocatorDeclaration
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0()); }
		ruleSegmentDeclaration
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsModel__OwnedIdiomsAssignment_5_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0()); }
		ruleIdiom
		{ after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__EPackageAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0()); }
		(
			{ before(grammarAccess.getEPackageImportAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1()); }
			RULE_STRING
			{ after(grammarAccess.getEPackageImportAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EPackageImport__AsAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0()); }
		RULE_ID
		{ after(grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__IdiomsModelAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0()); }
		(
			{ before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1()); }
			RULE_STRING
			{ after(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IdiomsImport__AsAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0()); }
		RULE_ID
		{ after(grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__LocatorDeclaration__OwnedLocatorAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0()); }
		ruleLocator
		{ after(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__EPackageAssignment_1_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0()); }
		(
			{ before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_0_1()); }
			RULE_ID
			{ after(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_0_1()); }
		)
		{ after(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__EClassAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0()); }
		(
			{ before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassIDTerminalRuleCall_1_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getAssignmentLocatorAccess().getEClassEClassIDTerminalRuleCall_1_1_0_1()); }
		)
		{ after(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AssignmentLocator__EStructuralFeatureAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0()); }
		(
			{ before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1()); }
			RULE_ID
			{ after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__KeywordLocator__StringAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0()); }
		RULE_STRING
		{ after(grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__EPackageAssignment_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0()); }
		(
			{ before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_1()); }
		)
		{ after(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReturnsLocator__EClassAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0()); }
		(
			{ before(grammarAccess.getReturnsLocatorAccess().getEClassEClassIDTerminalRuleCall_2_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReturnsLocatorAccess().getEClassEClassIDTerminalRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__IdiomsModelAssignment_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0()); }
		(
			{ before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1()); }
		)
		{ after(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredLocator__LocatorDeclarationAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0()); }
		(
			{ before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SegmentDeclaration__OwnedSegmentAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0()); }
		ruleSegment
		{ after(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__CustomSegment__SupportClassNameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0()); }
		RULE_STRING
		{ after(grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__StringAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0()); }
		RULE_STRING
		{ after(grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringSegment__PrintableAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0()); }
		(
			{ before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0()); }
			'printable'
			{ after(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0()); }
		)
		{ after(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__IdiomsModelAssignment_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0()); }
		(
			{ before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1()); }
		)
		{ after(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferredSegment__SegmentDeclarationAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0()); }
		(
			{ before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__MixinAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0()); }
		(
			{ before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0()); }
			'mixin'
			{ after(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0()); }
		)
		{ after(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__ForEPackageAssignment_3_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0()); }
		(
			{ before(grammarAccess.getIdiomAccess().getForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1()); }
			RULE_ID
			{ after(grammarAccess.getIdiomAccess().getForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1()); }
		)
		{ after(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__ForEClassAssignment_3_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0()); }
		(
			{ before(grammarAccess.getIdiomAccess().getForEClassEClassIDTerminalRuleCall_3_2_0_1()); }
			RULE_ID
			{ after(grammarAccess.getIdiomAccess().getForEClassEClassIDTerminalRuleCall_3_2_0_1()); }
		)
		{ after(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__InRuleRegexAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0()); }
		RULE_STRING
		{ after(grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__OwnedSubIdiomsAssignment_5_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0()); }
		ruleSubIdiom
		{ after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Idiom__OwnedSubIdiomsAssignment_5_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0()); }
		ruleSubIdiom
		{ after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__AllAssignment_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0()); }
		(
			{ before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0()); }
			'all'
			{ after(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0()); }
		)
		{ after(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__OwnedLocatorAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0()); }
		ruleLocator
		{ after(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SubIdiom__OwnedSegmentsAssignment_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0()); }
		(rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0)
		{ after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
