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
package org.eclipse.ocl.examples.xtext.idioms.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.ocl.examples.xtext.idioms.ide.contentassist.antlr.internal.InternalIdiomsParser;
import org.eclipse.ocl.examples.xtext.idioms.services.IdiomsGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class IdiomsParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {

		private final Map<AbstractElement, String> mappings;

		@Inject
		public NameMappings(IdiomsGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}

		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}

		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, IdiomsGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getIdiomsModelAccess().getAlternatives_5(), "rule__IdiomsModel__Alternatives_5");
			builder.put(grammarAccess.getLocatorAccess().getAlternatives(), "rule__Locator__Alternatives");
			builder.put(grammarAccess.getSegmentAccess().getAlternatives(), "rule__Segment__Alternatives");
			builder.put(grammarAccess.getIdiomAccess().getAlternatives_5(), "rule__Idiom__Alternatives_5");
			builder.put(grammarAccess.getSubIdiomAccess().getAlternatives_1(), "rule__SubIdiom__Alternatives_1");
			builder.put(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0(), "rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0");
			builder.put(grammarAccess.getIdiomsModelAccess().getGroup(), "rule__IdiomsModel__Group__0");
			builder.put(grammarAccess.getIdiomsModelAccess().getGroup_2(), "rule__IdiomsModel__Group_2__0");
			builder.put(grammarAccess.getEPackageImportAccess().getGroup(), "rule__EPackageImport__Group__0");
			builder.put(grammarAccess.getEPackageImportAccess().getGroup_2(), "rule__EPackageImport__Group_2__0");
			builder.put(grammarAccess.getIdiomsImportAccess().getGroup(), "rule__IdiomsImport__Group__0");
			builder.put(grammarAccess.getIdiomsImportAccess().getGroup_2(), "rule__IdiomsImport__Group_2__0");
			builder.put(grammarAccess.getLocatorDeclarationAccess().getGroup(), "rule__LocatorDeclaration__Group__0");
			builder.put(grammarAccess.getAnyAssignmentLocatorAccess().getGroup(), "rule__AnyAssignmentLocator__Group__0");
			builder.put(grammarAccess.getAnyElementLocatorAccess().getGroup(), "rule__AnyElementLocator__Group__0");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getGroup(), "rule__AssignmentLocator__Group__0");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getGroup_1(), "rule__AssignmentLocator__Group_1__0");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0(), "rule__AssignmentLocator__Group_1_0__0");
			builder.put(grammarAccess.getFinalLocatorAccess().getGroup(), "rule__FinalLocator__Group__0");
			builder.put(grammarAccess.getReturnsLocatorAccess().getGroup(), "rule__ReturnsLocator__Group__0");
			builder.put(grammarAccess.getReturnsLocatorAccess().getGroup_1(), "rule__ReturnsLocator__Group_1__0");
			builder.put(grammarAccess.getReferredLocatorAccess().getGroup(), "rule__ReferredLocator__Group__0");
			builder.put(grammarAccess.getReferredLocatorAccess().getGroup_0(), "rule__ReferredLocator__Group_0__0");
			builder.put(grammarAccess.getSegmentDeclarationAccess().getGroup(), "rule__SegmentDeclaration__Group__0");
			builder.put(grammarAccess.getCustomSegmentAccess().getGroup(), "rule__CustomSegment__Group__0");
			builder.put(grammarAccess.getHalfNewLineSegmentAccess().getGroup(), "rule__HalfNewLineSegment__Group__0");
			builder.put(grammarAccess.getNewLineSegmentAccess().getGroup(), "rule__NewLineSegment__Group__0");
			builder.put(grammarAccess.getNoSpaceSegmentAccess().getGroup(), "rule__NoSpaceSegment__Group__0");
			builder.put(grammarAccess.getPopSegmentAccess().getGroup(), "rule__PopSegment__Group__0");
			builder.put(grammarAccess.getPostCommentSegmentAccess().getGroup(), "rule__PostCommentSegment__Group__0");
			builder.put(grammarAccess.getPreCommentSegmentAccess().getGroup(), "rule__PreCommentSegment__Group__0");
			builder.put(grammarAccess.getPushSegmentAccess().getGroup(), "rule__PushSegment__Group__0");
			builder.put(grammarAccess.getSoftNewLineSegmentAccess().getGroup(), "rule__SoftNewLineSegment__Group__0");
			builder.put(grammarAccess.getSoftSpaceSegmentAccess().getGroup(), "rule__SoftSpaceSegment__Group__0");
			builder.put(grammarAccess.getStringSegmentAccess().getGroup(), "rule__StringSegment__Group__0");
			builder.put(grammarAccess.getValueSegmentAccess().getGroup(), "rule__ValueSegment__Group__0");
			builder.put(grammarAccess.getWrapAnchorSegmentAccess().getGroup(), "rule__WrapAnchorSegment__Group__0");
			builder.put(grammarAccess.getWrapBeginAllSegmentAccess().getGroup(), "rule__WrapBeginAllSegment__Group__0");
			builder.put(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup(), "rule__WrapBeginSomeSegment__Group__0");
			builder.put(grammarAccess.getWrapEndSegmentAccess().getGroup(), "rule__WrapEndSegment__Group__0");
			builder.put(grammarAccess.getWrapHereSegmentAccess().getGroup(), "rule__WrapHereSegment__Group__0");
			builder.put(grammarAccess.getReferredSegmentAccess().getGroup(), "rule__ReferredSegment__Group__0");
			builder.put(grammarAccess.getReferredSegmentAccess().getGroup_0(), "rule__ReferredSegment__Group_0__0");
			builder.put(grammarAccess.getIdiomAccess().getGroup(), "rule__Idiom__Group__0");
			builder.put(grammarAccess.getIdiomAccess().getGroup_3(), "rule__Idiom__Group_3__0");
			builder.put(grammarAccess.getIdiomAccess().getGroup_3_1(), "rule__Idiom__Group_3_1__0");
			builder.put(grammarAccess.getIdiomAccess().getGroup_4(), "rule__Idiom__Group_4__0");
			builder.put(grammarAccess.getIdiomAccess().getGroup_5_1(), "rule__Idiom__Group_5_1__0");
			builder.put(grammarAccess.getSubIdiomAccess().getGroup(), "rule__SubIdiom__Group__0");
			builder.put(grammarAccess.getSubIdiomAccess().getGroup_3(), "rule__SubIdiom__Group_3__0");
			builder.put(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1(), "rule__IdiomsModel__NamesAssignment_1");
			builder.put(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1(), "rule__IdiomsModel__NamesAssignment_2_1");
			builder.put(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3(), "rule__IdiomsModel__OwnedWithsAssignment_3");
			builder.put(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_4(), "rule__IdiomsModel__OwnedImportsAssignment_4");
			builder.put(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_5_0(), "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0");
			builder.put(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_5_1(), "rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1");
			builder.put(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_5_2(), "rule__IdiomsModel__OwnedIdiomsAssignment_5_2");
			builder.put(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1(), "rule__EPackageImport__EPackageAssignment_1");
			builder.put(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1(), "rule__EPackageImport__AsAssignment_2_1");
			builder.put(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1(), "rule__IdiomsImport__IdiomsModelAssignment_1");
			builder.put(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1(), "rule__IdiomsImport__AsAssignment_2_1");
			builder.put(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1(), "rule__LocatorDeclaration__NameAssignment_1");
			builder.put(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2(), "rule__LocatorDeclaration__OwnedLocatorAssignment_2");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0(), "rule__AssignmentLocator__EPackageAssignment_1_0_0");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1(), "rule__AssignmentLocator__EClassAssignment_1_1");
			builder.put(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2(), "rule__AssignmentLocator__EStructuralFeatureAssignment_2");
			builder.put(grammarAccess.getKeywordLocatorAccess().getStringAssignment(), "rule__KeywordLocator__StringAssignment");
			builder.put(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0(), "rule__ReturnsLocator__EPackageAssignment_1_0");
			builder.put(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2(), "rule__ReturnsLocator__EClassAssignment_2");
			builder.put(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0(), "rule__ReferredLocator__IdiomsModelAssignment_0_0");
			builder.put(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1(), "rule__ReferredLocator__LocatorDeclarationAssignment_1");
			builder.put(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1(), "rule__SegmentDeclaration__NameAssignment_1");
			builder.put(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2(), "rule__SegmentDeclaration__OwnedSegmentAssignment_2");
			builder.put(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1(), "rule__CustomSegment__SupportClassNameAssignment_1");
			builder.put(grammarAccess.getStringSegmentAccess().getStringAssignment_1(), "rule__StringSegment__StringAssignment_1");
			builder.put(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2(), "rule__StringSegment__PrintableAssignment_2");
			builder.put(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0(), "rule__ReferredSegment__IdiomsModelAssignment_0_0");
			builder.put(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1(), "rule__ReferredSegment__SegmentDeclarationAssignment_1");
			builder.put(grammarAccess.getIdiomAccess().getMixinAssignment_0(), "rule__Idiom__MixinAssignment_0");
			builder.put(grammarAccess.getIdiomAccess().getNameAssignment_2(), "rule__Idiom__NameAssignment_2");
			builder.put(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0(), "rule__Idiom__ForEPackageAssignment_3_1_0");
			builder.put(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2(), "rule__Idiom__ForEClassAssignment_3_2");
			builder.put(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1(), "rule__Idiom__InRuleRegexAssignment_4_1");
			builder.put(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0(), "rule__Idiom__OwnedSubIdiomsAssignment_5_0");
			builder.put(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1(), "rule__Idiom__OwnedSubIdiomsAssignment_5_1_1");
			builder.put(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0(), "rule__SubIdiom__AllAssignment_1_0");
			builder.put(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2(), "rule__SubIdiom__OwnedLocatorAssignment_2");
			builder.put(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1(), "rule__SubIdiom__OwnedSegmentsAssignment_3_1");
		}
	}

	@Inject
	private NameMappings nameMappings;

	@Inject
	private IdiomsGrammarAccess grammarAccess;

	@Override
	protected InternalIdiomsParser createParser() {
		InternalIdiomsParser result = new InternalIdiomsParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public IdiomsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(IdiomsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	public NameMappings getNameMappings() {
		return nameMappings;
	}

	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
