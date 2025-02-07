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
package org.eclipse.ocl.xtext.base.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.ocl.xtext.base.ide.contentassist.antlr.internal.InternalBaseParser;
import org.eclipse.ocl.xtext.base.services.BaseGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class BaseParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {

		private final Map<AbstractElement, String> mappings;

		@Inject
		public NameMappings(BaseGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}

		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}

		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, BaseGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getMultiplicityCSAccess().getAlternatives_1(), "rule__MultiplicityCS__Alternatives_1");
			builder.put(grammarAccess.getMultiplicityCSAccess().getAlternatives_2(), "rule__MultiplicityCS__Alternatives_2");
			builder.put(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAlternatives_0(), "rule__MultiplicityStringCS__StringBoundsAlternatives_0");
			builder.put(grammarAccess.getTypeRefCSAccess().getAlternatives(), "rule__TypeRefCS__Alternatives");
			builder.put(grammarAccess.getIDAccess().getAlternatives(), "rule__ID__Alternatives");
			builder.put(grammarAccess.getUPPERAccess().getAlternatives(), "rule__UPPER__Alternatives");
			builder.put(grammarAccess.getMultiplicityBoundsCSAccess().getGroup(), "rule__MultiplicityBoundsCS__Group__0");
			builder.put(grammarAccess.getMultiplicityBoundsCSAccess().getGroup_1(), "rule__MultiplicityBoundsCS__Group_1__0");
			builder.put(grammarAccess.getMultiplicityCSAccess().getGroup(), "rule__MultiplicityCS__Group__0");
			builder.put(grammarAccess.getPathNameCSAccess().getGroup(), "rule__PathNameCS__Group__0");
			builder.put(grammarAccess.getPathNameCSAccess().getGroup_1(), "rule__PathNameCS__Group_1__0");
			builder.put(grammarAccess.getUnreservedPathNameCSAccess().getGroup(), "rule__UnreservedPathNameCS__Group__0");
			builder.put(grammarAccess.getUnreservedPathNameCSAccess().getGroup_1(), "rule__UnreservedPathNameCS__Group_1__0");
			builder.put(grammarAccess.getTemplateBindingCSAccess().getGroup(), "rule__TemplateBindingCS__Group__0");
			builder.put(grammarAccess.getTemplateBindingCSAccess().getGroup_1(), "rule__TemplateBindingCS__Group_1__0");
			builder.put(grammarAccess.getTemplateSignatureCSAccess().getGroup(), "rule__TemplateSignatureCS__Group__0");
			builder.put(grammarAccess.getTemplateSignatureCSAccess().getGroup_2(), "rule__TemplateSignatureCS__Group_2__0");
			builder.put(grammarAccess.getTypeParameterCSAccess().getGroup(), "rule__TypeParameterCS__Group__0");
			builder.put(grammarAccess.getTypeParameterCSAccess().getGroup_1(), "rule__TypeParameterCS__Group_1__0");
			builder.put(grammarAccess.getTypeParameterCSAccess().getGroup_1_2(), "rule__TypeParameterCS__Group_1_2__0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getGroup(), "rule__TypedTypeRefCS__Group__0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getGroup_1(), "rule__TypedTypeRefCS__Group_1__0");
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getGroup(), "rule__WildcardTypeRefCS__Group__0");
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getGroup_2(), "rule__WildcardTypeRefCS__Group_2__0");
			builder.put(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundAssignment_0(), "rule__MultiplicityBoundsCS__LowerBoundAssignment_0");
			builder.put(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundAssignment_1_1(), "rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1");
			builder.put(grammarAccess.getMultiplicityCSAccess().getIsNullFreeAssignment_2_1(), "rule__MultiplicityCS__IsNullFreeAssignment_2_1");
			builder.put(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAssignment(), "rule__MultiplicityStringCS__StringBoundsAssignment");
			builder.put(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_0(), "rule__PathNameCS__OwnedPathElementsAssignment_0");
			builder.put(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_1_1(), "rule__PathNameCS__OwnedPathElementsAssignment_1_1");
			builder.put(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsAssignment_0(), "rule__UnreservedPathNameCS__OwnedPathElementsAssignment_0");
			builder.put(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsAssignment_1_1(), "rule__UnreservedPathNameCS__OwnedPathElementsAssignment_1_1");
			builder.put(grammarAccess.getFirstPathElementCSAccess().getReferredElementAssignment(), "rule__FirstPathElementCS__ReferredElementAssignment");
			builder.put(grammarAccess.getNextPathElementCSAccess().getReferredElementAssignment(), "rule__NextPathElementCS__ReferredElementAssignment");
			builder.put(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_0(), "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0");
			builder.put(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_1_1(), "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1");
			builder.put(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityAssignment_2(), "rule__TemplateBindingCS__OwnedMultiplicityAssignment_2");
			builder.put(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterAssignment(), "rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment");
			builder.put(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersAssignment_1(), "rule__TemplateSignatureCS__OwnedParametersAssignment_1");
			builder.put(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersAssignment_2_1(), "rule__TemplateSignatureCS__OwnedParametersAssignment_2_1");
			builder.put(grammarAccess.getTypeParameterCSAccess().getNameAssignment_0(), "rule__TypeParameterCS__NameAssignment_0");
			builder.put(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_1(), "rule__TypeParameterCS__OwnedExtendsAssignment_1_1");
			builder.put(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_2_1(), "rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_0(), "rule__TypedTypeRefCS__OwnedPathNameAssignment_0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingAssignment_1_1(), "rule__TypedTypeRefCS__OwnedBindingAssignment_1_1");
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsAssignment_2_1(), "rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1");
		}
	}

	@Inject
	private NameMappings nameMappings;

	@Inject
	private BaseGrammarAccess grammarAccess;

	@Override
	protected InternalBaseParser createParser() {
		InternalBaseParser result = new InternalBaseParser(null);
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

	public BaseGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(BaseGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	public NameMappings getNameMappings() {
		return nameMappings;
	}

	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
