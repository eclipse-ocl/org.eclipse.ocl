/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.markup.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.ocl.xtext.markup.ide.contentassist.antlr.internal.InternalMarkupParser;
import org.eclipse.ocl.xtext.markup.services.MarkupGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class MarkupParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {

		private final Map<AbstractElement, String> mappings;

		@Inject
		public NameMappings(MarkupGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}

		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}

		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, MarkupGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getMarkupKeywordAccess().getAlternatives(), "rule__MarkupKeyword__Alternatives");
			builder.put(grammarAccess.getMarkupElementAccess().getAlternatives(), "rule__MarkupElement__Alternatives");
			builder.put(grammarAccess.getFontElementAccess().getFontAlternatives_0_0(), "rule__FontElement__FontAlternatives_0_0");
			builder.put(grammarAccess.getTextElementAccess().getAlternatives(), "rule__TextElement__Alternatives");
			builder.put(grammarAccess.getTextElementAccess().getTextAlternatives_0_0(), "rule__TextElement__TextAlternatives_0_0");
			builder.put(grammarAccess.getBulletElementAccess().getGroup(), "rule__BulletElement__Group__0");
			builder.put(grammarAccess.getBulletElementAccess().getGroup_2(), "rule__BulletElement__Group_2__0");
			builder.put(grammarAccess.getFontElementAccess().getGroup(), "rule__FontElement__Group__0");
			builder.put(grammarAccess.getFigureElementAccess().getGroup(), "rule__FigureElement__Group__0");
			builder.put(grammarAccess.getFigureElementAccess().getGroup_1(), "rule__FigureElement__Group_1__0");
			builder.put(grammarAccess.getFigureElementAccess().getGroup_4(), "rule__FigureElement__Group_4__0");
			builder.put(grammarAccess.getFigureElementAccess().getGroup_4_2(), "rule__FigureElement__Group_4_2__0");
			builder.put(grammarAccess.getFigureElementAccess().getGroup_4_2_2(), "rule__FigureElement__Group_4_2_2__0");
			builder.put(grammarAccess.getFigureRefElementAccess().getGroup(), "rule__FigureRefElement__Group__0");
			builder.put(grammarAccess.getFootnoteElementAccess().getGroup(), "rule__FootnoteElement__Group__0");
			builder.put(grammarAccess.getHeadingElementAccess().getGroup(), "rule__HeadingElement__Group__0");
			builder.put(grammarAccess.getHeadingElementAccess().getGroup_2(), "rule__HeadingElement__Group_2__0");
			builder.put(grammarAccess.getNullElementAccess().getGroup(), "rule__NullElement__Group__0");
			builder.put(grammarAccess.getOCLCodeElementAccess().getGroup(), "rule__OCLCodeElement__Group__0");
			builder.put(grammarAccess.getOCLEvalElementAccess().getGroup(), "rule__OCLEvalElement__Group__0");
			builder.put(grammarAccess.getOCLTextElementAccess().getGroup(), "rule__OCLTextElement__Group__0");
			builder.put(grammarAccess.getMarkupAccess().getElementsAssignment(), "rule__Markup__ElementsAssignment");
			builder.put(grammarAccess.getBulletElementAccess().getLevelAssignment_2_1(), "rule__BulletElement__LevelAssignment_2_1");
			builder.put(grammarAccess.getBulletElementAccess().getElementsAssignment_4(), "rule__BulletElement__ElementsAssignment_4");
			builder.put(grammarAccess.getFontElementAccess().getFontAssignment_0(), "rule__FontElement__FontAssignment_0");
			builder.put(grammarAccess.getFontElementAccess().getElementsAssignment_2(), "rule__FontElement__ElementsAssignment_2");
			builder.put(grammarAccess.getFigureElementAccess().getDefAssignment_1_1(), "rule__FigureElement__DefAssignment_1_1");
			builder.put(grammarAccess.getFigureElementAccess().getSrcAssignment_3(), "rule__FigureElement__SrcAssignment_3");
			builder.put(grammarAccess.getFigureElementAccess().getAltAssignment_4_1(), "rule__FigureElement__AltAssignment_4_1");
			builder.put(grammarAccess.getFigureElementAccess().getRequiredWidthAssignment_4_2_1(), "rule__FigureElement__RequiredWidthAssignment_4_2_1");
			builder.put(grammarAccess.getFigureElementAccess().getRequiredHeightAssignment_4_2_2_1(), "rule__FigureElement__RequiredHeightAssignment_4_2_2_1");
			builder.put(grammarAccess.getFigureRefElementAccess().getRefAssignment_2(), "rule__FigureRefElement__RefAssignment_2");
			builder.put(grammarAccess.getFootnoteElementAccess().getElementsAssignment_3(), "rule__FootnoteElement__ElementsAssignment_3");
			builder.put(grammarAccess.getHeadingElementAccess().getLevelAssignment_2_1(), "rule__HeadingElement__LevelAssignment_2_1");
			builder.put(grammarAccess.getHeadingElementAccess().getElementsAssignment_4(), "rule__HeadingElement__ElementsAssignment_4");
			builder.put(grammarAccess.getNewLineElementAccess().getTextAssignment(), "rule__NewLineElement__TextAssignment");
			builder.put(grammarAccess.getNullElementAccess().getElementsAssignment_2(), "rule__NullElement__ElementsAssignment_2");
			builder.put(grammarAccess.getOCLCodeElementAccess().getElementsAssignment_3(), "rule__OCLCodeElement__ElementsAssignment_3");
			builder.put(grammarAccess.getOCLEvalElementAccess().getElementsAssignment_3(), "rule__OCLEvalElement__ElementsAssignment_3");
			builder.put(grammarAccess.getOCLTextElementAccess().getElementsAssignment_3(), "rule__OCLTextElement__ElementsAssignment_3");
			builder.put(grammarAccess.getTextElementAccess().getTextAssignment_0(), "rule__TextElement__TextAssignment_0");
			builder.put(grammarAccess.getTextElementAccess().getTextAssignment_1(), "rule__TextElement__TextAssignment_1");
		}
	}

	@Inject
	private NameMappings nameMappings;

	@Inject
	private MarkupGrammarAccess grammarAccess;

	@Override
	protected InternalMarkupParser createParser() {
		InternalMarkupParser result = new InternalMarkupParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] {  };
	}

	public MarkupGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(MarkupGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	public NameMappings getNameMappings() {
		return nameMappings;
	}

	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
