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
package org.eclipse.ocl.xtext.oclstdlib.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Map;
import org.eclipse.ocl.xtext.oclstdlib.ide.contentassist.antlr.internal.InternalOCLstdlibParser;
import org.eclipse.ocl.xtext.oclstdlib.services.OCLstdlibGrammarAccess;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class OCLstdlibParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {

		private final Map<AbstractElement, String> mappings;

		@Inject
		public NameMappings(OCLstdlibGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}

		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}

		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, OCLstdlibGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getIdentifierAccess().getAlternatives(), "rule__Identifier__Alternatives");
			builder.put(grammarAccess.getRestrictedKeywordsAccess().getAlternatives(), "rule__RestrictedKeywords__Alternatives");
			builder.put(grammarAccess.getNameAccess().getAlternatives(), "rule__Name__Alternatives");
			builder.put(grammarAccess.getAnyNameAccess().getAlternatives(), "rule__AnyName__Alternatives");
			builder.put(grammarAccess.getAnnotationCSAccess().getNameAlternatives_1_0(), "rule__AnnotationCS__NameAlternatives_1_0");
			builder.put(grammarAccess.getAnnotationCSAccess().getAlternatives_3(), "rule__AnnotationCS__Alternatives_3");
			builder.put(grammarAccess.getAnnotationElementCSAccess().getAlternatives(), "rule__AnnotationElementCS__Alternatives");
			builder.put(grammarAccess.getLibClassCSAccess().getAlternatives_8(), "rule__LibClassCS__Alternatives_8");
			builder.put(grammarAccess.getDetailCSAccess().getNameAlternatives_0_0(), "rule__DetailCS__NameAlternatives_0_0");
			builder.put(grammarAccess.getDetailCSAccess().getValuesAlternatives_2_0(), "rule__DetailCS__ValuesAlternatives_2_0");
			builder.put(grammarAccess.getLibCoercionCSAccess().getAlternatives_7(), "rule__LibCoercionCS__Alternatives_7");
			builder.put(grammarAccess.getLibCoercionCSAccess().getAlternatives_7_0_1(), "rule__LibCoercionCS__Alternatives_7_0_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getAlternatives_14(), "rule__LibIterationCS__Alternatives_14");
			builder.put(grammarAccess.getLibIterationCSAccess().getAlternatives_14_0_1(), "rule__LibIterationCS__Alternatives_14_0_1");
			builder.put(grammarAccess.getOperationCSAccess().getAlternatives(), "rule__OperationCS__Alternatives");
			builder.put(grammarAccess.getLibOperationCSAccess().getAlternatives_13(), "rule__LibOperationCS__Alternatives_13");
			builder.put(grammarAccess.getLibOperationCSAccess().getAlternatives_13_0_1(), "rule__LibOperationCS__Alternatives_13_0_1");
			builder.put(grammarAccess.getLibPackageCSAccess().getAlternatives_4(), "rule__LibPackageCS__Alternatives_4");
			builder.put(grammarAccess.getPackageCSAccess().getAlternatives_4(), "rule__PackageCS__Alternatives_4");
			builder.put(grammarAccess.getLibPropertyCSAccess().getAlternatives_7(), "rule__LibPropertyCS__Alternatives_7");
			builder.put(grammarAccess.getPrecedenceCSAccess().getAlternatives_0(), "rule__PrecedenceCS__Alternatives_0");
			builder.put(grammarAccess.getTypedMultiplicityRefCSAccess().getAlternatives_0(), "rule__TypedMultiplicityRefCS__Alternatives_0");
			builder.put(grammarAccess.getTypedRefCSAccess().getAlternatives(), "rule__TypedRefCS__Alternatives");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getAlternatives(), "rule__TypedTypeRefCS__Alternatives");
			builder.put(grammarAccess.getEssentialOCLReservedKeywordAccess().getAlternatives(), "rule__EssentialOCLReservedKeyword__Alternatives");
			builder.put(grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getAlternatives(), "rule__EssentialOCLUnaryOperatorName__Alternatives");
			builder.put(grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAlternatives(), "rule__EssentialOCLInfixOperatorName__Alternatives");
			builder.put(grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getAlternatives(), "rule__EssentialOCLNavigationOperatorName__Alternatives");
			builder.put(grammarAccess.getBinaryOperatorNameAccess().getAlternatives(), "rule__BinaryOperatorName__Alternatives");
			builder.put(grammarAccess.getEssentialOCLUnreservedNameAccess().getAlternatives(), "rule__EssentialOCLUnreservedName__Alternatives");
			builder.put(grammarAccess.getURIFirstPathElementCSAccess().getAlternatives(), "rule__URIFirstPathElementCS__Alternatives");
			builder.put(grammarAccess.getPrimitiveTypeIdentifierAccess().getAlternatives(), "rule__PrimitiveTypeIdentifier__Alternatives");
			builder.put(grammarAccess.getCollectionTypeIdentifierAccess().getAlternatives(), "rule__CollectionTypeIdentifier__Alternatives");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getAlternatives(), "rule__CollectionLiteralPartCS__Alternatives");
			builder.put(grammarAccess.getShadowPartCSAccess().getAlternatives(), "rule__ShadowPartCS__Alternatives");
			builder.put(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionAlternatives_0_2_0(), "rule__ShadowPartCS__OwnedInitExpressionAlternatives_0_2_0");
			builder.put(grammarAccess.getMapLiteralPartCSAccess().getAlternatives_1(), "rule__MapLiteralPartCS__Alternatives_1");
			builder.put(grammarAccess.getPrimitiveLiteralExpCSAccess().getAlternatives(), "rule__PrimitiveLiteralExpCS__Alternatives");
			builder.put(grammarAccess.getBooleanLiteralExpCSAccess().getAlternatives(), "rule__BooleanLiteralExpCS__Alternatives");
			builder.put(grammarAccess.getTypeLiteralCSAccess().getAlternatives(), "rule__TypeLiteralCS__Alternatives");
			builder.put(grammarAccess.getTypeExpWithoutMultiplicityCSAccess().getAlternatives(), "rule__TypeExpWithoutMultiplicityCS__Alternatives");
			builder.put(grammarAccess.getExpCSAccess().getAlternatives(), "rule__ExpCS__Alternatives");
			builder.put(grammarAccess.getPrefixedLetExpCSAccess().getAlternatives(), "rule__PrefixedLetExpCS__Alternatives");
			builder.put(grammarAccess.getPrefixedPrimaryExpCSAccess().getAlternatives(), "rule__PrefixedPrimaryExpCS__Alternatives");
			builder.put(grammarAccess.getPrimaryExpCSAccess().getAlternatives(), "rule__PrimaryExpCS__Alternatives");
			builder.put(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsAlternatives_2_1_0(), "rule__RoundBracketedClauseCS__OwnedArgumentsAlternatives_2_1_0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getAlternatives(), "rule__NavigatingArgCS__Alternatives");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getAlternatives_0_1(), "rule__NavigatingArgCS__Alternatives_0_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getAlternatives_0_1_0_0(), "rule__NavigatingArgCS__Alternatives_0_1_0_0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getAlternatives_0_1_1_2_0(), "rule__NavigatingArgCS__Alternatives_0_1_1_2_0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getAlternatives_0_1_2_1_0(), "rule__NavigatingArgCS__Alternatives_0_1_2_1_0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getAlternatives_2(), "rule__NavigatingCommaArgCS__Alternatives_2");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getAlternatives_2_0_0(), "rule__NavigatingCommaArgCS__Alternatives_2_0_0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getAlternatives_2_1_2_0(), "rule__NavigatingCommaArgCS__Alternatives_2_1_2_0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getAlternatives_2_2_1_0(), "rule__NavigatingCommaArgCS__Alternatives_2_2_1_0");
			builder.put(grammarAccess.getIfExpCSAccess().getOwnedConditionAlternatives_1_0(), "rule__IfExpCS__OwnedConditionAlternatives_1_0");
			builder.put(grammarAccess.getMultiplicityCSAccess().getAlternatives_1(), "rule__MultiplicityCS__Alternatives_1");
			builder.put(grammarAccess.getMultiplicityCSAccess().getAlternatives_2(), "rule__MultiplicityCS__Alternatives_2");
			builder.put(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAlternatives_0(), "rule__MultiplicityStringCS__StringBoundsAlternatives_0");
			builder.put(grammarAccess.getTypeRefCSAccess().getAlternatives(), "rule__TypeRefCS__Alternatives");
			builder.put(grammarAccess.getIDAccess().getAlternatives(), "rule__ID__Alternatives");
			builder.put(grammarAccess.getUPPERAccess().getAlternatives(), "rule__UPPER__Alternatives");
			builder.put(grammarAccess.getLibraryAccess().getGroup(), "rule__Library__Group__0");
			builder.put(grammarAccess.getLibraryAccess().getGroup_0(), "rule__Library__Group_0__0");
			builder.put(grammarAccess.getLibPathNameCSAccess().getGroup(), "rule__LibPathNameCS__Group__0");
			builder.put(grammarAccess.getLibPathNameCSAccess().getGroup_1(), "rule__LibPathNameCS__Group_1__0");
			builder.put(grammarAccess.getAccumulatorCSAccess().getGroup(), "rule__AccumulatorCS__Group__0");
			builder.put(grammarAccess.getAnnotationCSAccess().getGroup(), "rule__AnnotationCS__Group__0");
			builder.put(grammarAccess.getAnnotationCSAccess().getGroup_2(), "rule__AnnotationCS__Group_2__0");
			builder.put(grammarAccess.getAnnotationCSAccess().getGroup_2_2(), "rule__AnnotationCS__Group_2_2__0");
			builder.put(grammarAccess.getAnnotationCSAccess().getGroup_3_0(), "rule__AnnotationCS__Group_3_0__0");
			builder.put(grammarAccess.getLibClassCSAccess().getGroup(), "rule__LibClassCS__Group__0");
			builder.put(grammarAccess.getLibClassCSAccess().getGroup_4(), "rule__LibClassCS__Group_4__0");
			builder.put(grammarAccess.getLibClassCSAccess().getGroup_5(), "rule__LibClassCS__Group_5__0");
			builder.put(grammarAccess.getLibClassCSAccess().getGroup_5_2(), "rule__LibClassCS__Group_5_2__0");
			builder.put(grammarAccess.getLibClassCSAccess().getGroup_6(), "rule__LibClassCS__Group_6__0");
			builder.put(grammarAccess.getDetailCSAccess().getGroup(), "rule__DetailCS__Group__0");
			builder.put(grammarAccess.getDocumentationCSAccess().getGroup(), "rule__DocumentationCS__Group__0");
			builder.put(grammarAccess.getDocumentationCSAccess().getGroup_3(), "rule__DocumentationCS__Group_3__0");
			builder.put(grammarAccess.getDocumentationCSAccess().getGroup_3_2(), "rule__DocumentationCS__Group_3_2__0");
			builder.put(grammarAccess.getImportCSAccess().getGroup(), "rule__ImportCS__Group__0");
			builder.put(grammarAccess.getImportCSAccess().getGroup_1(), "rule__ImportCS__Group_1__0");
			builder.put(grammarAccess.getInvCSAccess().getGroup(), "rule__InvCS__Group__0");
			builder.put(grammarAccess.getInvCSAccess().getGroup_1(), "rule__InvCS__Group_1__0");
			builder.put(grammarAccess.getInvCSAccess().getGroup_1_1(), "rule__InvCS__Group_1_1__0");
			builder.put(grammarAccess.getLibCoercionCSAccess().getGroup(), "rule__LibCoercionCS__Group__0");
			builder.put(grammarAccess.getLibCoercionCSAccess().getGroup_6(), "rule__LibCoercionCS__Group_6__0");
			builder.put(grammarAccess.getLibCoercionCSAccess().getGroup_7_0(), "rule__LibCoercionCS__Group_7_0__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup(), "rule__LibIterationCS__Group__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_5(), "rule__LibIterationCS__Group_5__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_6(), "rule__LibIterationCS__Group_6__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_6_2(), "rule__LibIterationCS__Group_6_2__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_7(), "rule__LibIterationCS__Group_7__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_7_2(), "rule__LibIterationCS__Group_7_2__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_13(), "rule__LibIterationCS__Group_13__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_14_0(), "rule__LibIterationCS__Group_14_0__0");
			builder.put(grammarAccess.getLibIterationCSAccess().getGroup_14_0_1_1(), "rule__LibIterationCS__Group_14_0_1_1__0");
			builder.put(grammarAccess.getIteratorCSAccess().getGroup(), "rule__IteratorCS__Group__0");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getGroup(), "rule__LambdaTypeCS__Group__0");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getGroup_4(), "rule__LambdaTypeCS__Group_4__0");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getGroup_4_1(), "rule__LambdaTypeCS__Group_4_1__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup(), "rule__LibOperationCS__Group__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_5(), "rule__LibOperationCS__Group_5__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_5_1(), "rule__LibOperationCS__Group_5_1__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_11(), "rule__LibOperationCS__Group_11__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_12(), "rule__LibOperationCS__Group_12__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_13_0(), "rule__LibOperationCS__Group_13_0__0");
			builder.put(grammarAccess.getLibOperationCSAccess().getGroup_13_0_1_1(), "rule__LibOperationCS__Group_13_0_1_1__0");
			builder.put(grammarAccess.getLibOppositeCSAccess().getGroup(), "rule__LibOppositeCS__Group__0");
			builder.put(grammarAccess.getLibPackageCSAccess().getGroup(), "rule__LibPackageCS__Group__0");
			builder.put(grammarAccess.getLibPackageCSAccess().getGroup_2(), "rule__LibPackageCS__Group_2__0");
			builder.put(grammarAccess.getLibPackageCSAccess().getGroup_4_1(), "rule__LibPackageCS__Group_4_1__0");
			builder.put(grammarAccess.getPackageCSAccess().getGroup(), "rule__PackageCS__Group__0");
			builder.put(grammarAccess.getPackageCSAccess().getGroup_2(), "rule__PackageCS__Group_2__0");
			builder.put(grammarAccess.getParameterCSAccess().getGroup(), "rule__ParameterCS__Group__0");
			builder.put(grammarAccess.getLibPropertyCSAccess().getGroup(), "rule__LibPropertyCS__Group__0");
			builder.put(grammarAccess.getLibPropertyCSAccess().getGroup_6(), "rule__LibPropertyCS__Group_6__0");
			builder.put(grammarAccess.getLibPropertyCSAccess().getGroup_7_0(), "rule__LibPropertyCS__Group_7_0__0");
			builder.put(grammarAccess.getPostCSAccess().getGroup(), "rule__PostCS__Group__0");
			builder.put(grammarAccess.getPostCSAccess().getGroup_1(), "rule__PostCS__Group_1__0");
			builder.put(grammarAccess.getPostCSAccess().getGroup_1_1(), "rule__PostCS__Group_1_1__0");
			builder.put(grammarAccess.getPreCSAccess().getGroup(), "rule__PreCS__Group__0");
			builder.put(grammarAccess.getPreCSAccess().getGroup_1(), "rule__PreCS__Group_1__0");
			builder.put(grammarAccess.getPreCSAccess().getGroup_1_1(), "rule__PreCS__Group_1_1__0");
			builder.put(grammarAccess.getPrecedenceCSAccess().getGroup(), "rule__PrecedenceCS__Group__0");
			builder.put(grammarAccess.getTypedMultiplicityRefCSAccess().getGroup(), "rule__TypedMultiplicityRefCS__Group__0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getGroup_0(), "rule__TypedTypeRefCS__Group_0__0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getGroup_1(), "rule__TypedTypeRefCS__Group_1__0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getGroup_1_1(), "rule__TypedTypeRefCS__Group_1_1__0");
			builder.put(grammarAccess.getTuplePartCSAccess().getGroup(), "rule__TuplePartCS__Group__0");
			builder.put(grammarAccess.getURIPathNameCSAccess().getGroup(), "rule__URIPathNameCS__Group__0");
			builder.put(grammarAccess.getURIPathNameCSAccess().getGroup_1(), "rule__URIPathNameCS__Group_1__0");
			builder.put(grammarAccess.getURIFirstPathElementCSAccess().getGroup_1(), "rule__URIFirstPathElementCS__Group_1__0");
			builder.put(grammarAccess.getCollectionTypeCSAccess().getGroup(), "rule__CollectionTypeCS__Group__0");
			builder.put(grammarAccess.getCollectionTypeCSAccess().getGroup_1(), "rule__CollectionTypeCS__Group_1__0");
			builder.put(grammarAccess.getMapTypeCSAccess().getGroup(), "rule__MapTypeCS__Group__0");
			builder.put(grammarAccess.getMapTypeCSAccess().getGroup_1(), "rule__MapTypeCS__Group_1__0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getGroup(), "rule__TupleTypeCS__Group__0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getGroup_1(), "rule__TupleTypeCS__Group_1__0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getGroup_1_1(), "rule__TupleTypeCS__Group_1_1__0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getGroup_1_1_1(), "rule__TupleTypeCS__Group_1_1_1__0");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getGroup(), "rule__CollectionLiteralExpCS__Group__0");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getGroup_2(), "rule__CollectionLiteralExpCS__Group_2__0");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getGroup_2_1(), "rule__CollectionLiteralExpCS__Group_2_1__0");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getGroup_0(), "rule__CollectionLiteralPartCS__Group_0__0");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getGroup_0_1(), "rule__CollectionLiteralPartCS__Group_0_1__0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getGroup(), "rule__CollectionPatternCS__Group__0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getGroup_2(), "rule__CollectionPatternCS__Group_2__0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getGroup_2_1(), "rule__CollectionPatternCS__Group_2_1__0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getGroup_2_2(), "rule__CollectionPatternCS__Group_2_2__0");
			builder.put(grammarAccess.getShadowPartCSAccess().getGroup_0(), "rule__ShadowPartCS__Group_0__0");
			builder.put(grammarAccess.getPatternExpCSAccess().getGroup(), "rule__PatternExpCS__Group__0");
			builder.put(grammarAccess.getLambdaLiteralExpCSAccess().getGroup(), "rule__LambdaLiteralExpCS__Group__0");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getGroup(), "rule__MapLiteralExpCS__Group__0");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getGroup_2(), "rule__MapLiteralExpCS__Group_2__0");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getGroup_2_1(), "rule__MapLiteralExpCS__Group_2_1__0");
			builder.put(grammarAccess.getMapLiteralPartCSAccess().getGroup(), "rule__MapLiteralPartCS__Group__0");
			builder.put(grammarAccess.getTupleLiteralExpCSAccess().getGroup(), "rule__TupleLiteralExpCS__Group__0");
			builder.put(grammarAccess.getTupleLiteralExpCSAccess().getGroup_3(), "rule__TupleLiteralExpCS__Group_3__0");
			builder.put(grammarAccess.getTupleLiteralPartCSAccess().getGroup(), "rule__TupleLiteralPartCS__Group__0");
			builder.put(grammarAccess.getTupleLiteralPartCSAccess().getGroup_1(), "rule__TupleLiteralPartCS__Group_1__0");
			builder.put(grammarAccess.getUnlimitedNaturalLiteralExpCSAccess().getGroup(), "rule__UnlimitedNaturalLiteralExpCS__Group__0");
			builder.put(grammarAccess.getInvalidLiteralExpCSAccess().getGroup(), "rule__InvalidLiteralExpCS__Group__0");
			builder.put(grammarAccess.getNullLiteralExpCSAccess().getGroup(), "rule__NullLiteralExpCS__Group__0");
			builder.put(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getGroup(), "rule__TypeLiteralWithMultiplicityCS__Group__0");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getGroup(), "rule__TypeNameExpCS__Group__0");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getGroup_1(), "rule__TypeNameExpCS__Group_1__0");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getGroup_1_1(), "rule__TypeNameExpCS__Group_1_1__0");
			builder.put(grammarAccess.getTypeExpCSAccess().getGroup(), "rule__TypeExpCS__Group__0");
			builder.put(grammarAccess.getExpCSAccess().getGroup_0(), "rule__ExpCS__Group_0__0");
			builder.put(grammarAccess.getExpCSAccess().getGroup_0_1(), "rule__ExpCS__Group_0_1__0");
			builder.put(grammarAccess.getPrefixedLetExpCSAccess().getGroup_0(), "rule__PrefixedLetExpCS__Group_0__0");
			builder.put(grammarAccess.getPrefixedPrimaryExpCSAccess().getGroup_0(), "rule__PrefixedPrimaryExpCS__Group_0__0");
			builder.put(grammarAccess.getNameExpCSAccess().getGroup(), "rule__NameExpCS__Group__0");
			builder.put(grammarAccess.getNameExpCSAccess().getGroup_4(), "rule__NameExpCS__Group_4__0");
			builder.put(grammarAccess.getCurlyBracketedClauseCSAccess().getGroup(), "rule__CurlyBracketedClauseCS__Group__0");
			builder.put(grammarAccess.getCurlyBracketedClauseCSAccess().getGroup_2(), "rule__CurlyBracketedClauseCS__Group_2__0");
			builder.put(grammarAccess.getCurlyBracketedClauseCSAccess().getGroup_2_1(), "rule__CurlyBracketedClauseCS__Group_2_1__0");
			builder.put(grammarAccess.getRoundBracketedClauseCSAccess().getGroup(), "rule__RoundBracketedClauseCS__Group__0");
			builder.put(grammarAccess.getRoundBracketedClauseCSAccess().getGroup_2(), "rule__RoundBracketedClauseCS__Group_2__0");
			builder.put(grammarAccess.getSquareBracketedClauseCSAccess().getGroup(), "rule__SquareBracketedClauseCS__Group__0");
			builder.put(grammarAccess.getSquareBracketedClauseCSAccess().getGroup_2(), "rule__SquareBracketedClauseCS__Group_2__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0(), "rule__NavigatingArgCS__Group_0__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_0(), "rule__NavigatingArgCS__Group_0_1_0__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_0_2(), "rule__NavigatingArgCS__Group_0_1_0_2__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_1(), "rule__NavigatingArgCS__Group_0_1_1__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_1_2(), "rule__NavigatingArgCS__Group_0_1_1_2__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_1_3(), "rule__NavigatingArgCS__Group_0_1_1_3__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_2(), "rule__NavigatingArgCS__Group_0_1_2__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_2_0(), "rule__NavigatingArgCS__Group_0_1_2_0__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_0_1_2_1(), "rule__NavigatingArgCS__Group_0_1_2_1__0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getGroup_1(), "rule__NavigatingArgCS__Group_1__0");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getGroup(), "rule__NavigatingBarArgCS__Group__0");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getGroup_2(), "rule__NavigatingBarArgCS__Group_2__0");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getGroup_2_2(), "rule__NavigatingBarArgCS__Group_2_2__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup(), "rule__NavigatingCommaArgCS__Group__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_0(), "rule__NavigatingCommaArgCS__Group_2_0__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_0_2(), "rule__NavigatingCommaArgCS__Group_2_0_2__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_1(), "rule__NavigatingCommaArgCS__Group_2_1__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_1_2(), "rule__NavigatingCommaArgCS__Group_2_1_2__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_1_3(), "rule__NavigatingCommaArgCS__Group_2_1_3__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_2(), "rule__NavigatingCommaArgCS__Group_2_2__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_2_0(), "rule__NavigatingCommaArgCS__Group_2_2_0__0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getGroup_2_2_1(), "rule__NavigatingCommaArgCS__Group_2_2_1__0");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getGroup(), "rule__NavigatingSemiArgCS__Group__0");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getGroup_2(), "rule__NavigatingSemiArgCS__Group_2__0");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getGroup_2_2(), "rule__NavigatingSemiArgCS__Group_2_2__0");
			builder.put(grammarAccess.getCoIteratorVariableCSAccess().getGroup(), "rule__CoIteratorVariableCS__Group__0");
			builder.put(grammarAccess.getCoIteratorVariableCSAccess().getGroup_1(), "rule__CoIteratorVariableCS__Group_1__0");
			builder.put(grammarAccess.getIfExpCSAccess().getGroup(), "rule__IfExpCS__Group__0");
			builder.put(grammarAccess.getElseIfThenExpCSAccess().getGroup(), "rule__ElseIfThenExpCS__Group__0");
			builder.put(grammarAccess.getLetExpCSAccess().getGroup(), "rule__LetExpCS__Group__0");
			builder.put(grammarAccess.getLetExpCSAccess().getGroup_2(), "rule__LetExpCS__Group_2__0");
			builder.put(grammarAccess.getLetVariableCSAccess().getGroup(), "rule__LetVariableCS__Group__0");
			builder.put(grammarAccess.getLetVariableCSAccess().getGroup_2(), "rule__LetVariableCS__Group_2__0");
			builder.put(grammarAccess.getNestedExpCSAccess().getGroup(), "rule__NestedExpCS__Group__0");
			builder.put(grammarAccess.getSelfExpCSAccess().getGroup(), "rule__SelfExpCS__Group__0");
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
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getGroup(), "rule__WildcardTypeRefCS__Group__0");
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getGroup_2(), "rule__WildcardTypeRefCS__Group_2__0");
			builder.put(grammarAccess.getLibraryAccess().getOwnedImportsAssignment_0_0(), "rule__Library__OwnedImportsAssignment_0_0");
			builder.put(grammarAccess.getLibraryAccess().getOwnedPackagesAssignment_1(), "rule__Library__OwnedPackagesAssignment_1");
			builder.put(grammarAccess.getLibPathNameCSAccess().getOwnedPathElementsAssignment_0(), "rule__LibPathNameCS__OwnedPathElementsAssignment_0");
			builder.put(grammarAccess.getLibPathNameCSAccess().getOwnedPathElementsAssignment_1_1(), "rule__LibPathNameCS__OwnedPathElementsAssignment_1_1");
			builder.put(grammarAccess.getLibPathElementCSAccess().getReferredElementAssignment(), "rule__LibPathElementCS__ReferredElementAssignment");
			builder.put(grammarAccess.getAccumulatorCSAccess().getNameAssignment_0(), "rule__AccumulatorCS__NameAssignment_0");
			builder.put(grammarAccess.getAccumulatorCSAccess().getOwnedTypeAssignment_2(), "rule__AccumulatorCS__OwnedTypeAssignment_2");
			builder.put(grammarAccess.getAnnotationCSAccess().getNameAssignment_1(), "rule__AnnotationCS__NameAssignment_1");
			builder.put(grammarAccess.getAnnotationCSAccess().getOwnedDetailsAssignment_2_1(), "rule__AnnotationCS__OwnedDetailsAssignment_2_1");
			builder.put(grammarAccess.getAnnotationCSAccess().getOwnedDetailsAssignment_2_2_1(), "rule__AnnotationCS__OwnedDetailsAssignment_2_2_1");
			builder.put(grammarAccess.getAnnotationCSAccess().getOwnedAnnotationsAssignment_3_0_1(), "rule__AnnotationCS__OwnedAnnotationsAssignment_3_0_1");
			builder.put(grammarAccess.getLibClassCSAccess().getIsAbstractAssignment_0(), "rule__LibClassCS__IsAbstractAssignment_0");
			builder.put(grammarAccess.getLibClassCSAccess().getNameAssignment_2(), "rule__LibClassCS__NameAssignment_2");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedSignatureAssignment_3(), "rule__LibClassCS__OwnedSignatureAssignment_3");
			builder.put(grammarAccess.getLibClassCSAccess().getMetaclassNameAssignment_4_1(), "rule__LibClassCS__MetaclassNameAssignment_4_1");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedSuperTypesAssignment_5_1(), "rule__LibClassCS__OwnedSuperTypesAssignment_5_1");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedSuperTypesAssignment_5_2_1(), "rule__LibClassCS__OwnedSuperTypesAssignment_5_2_1");
			builder.put(grammarAccess.getLibClassCSAccess().getImplementationAssignment_6_1(), "rule__LibClassCS__ImplementationAssignment_6_1");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedOperationsAssignment_8_0(), "rule__LibClassCS__OwnedOperationsAssignment_8_0");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedPropertiesAssignment_8_1(), "rule__LibClassCS__OwnedPropertiesAssignment_8_1");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedConstraintsAssignment_8_2(), "rule__LibClassCS__OwnedConstraintsAssignment_8_2");
			builder.put(grammarAccess.getLibClassCSAccess().getOwnedAnnotationsAssignment_8_3(), "rule__LibClassCS__OwnedAnnotationsAssignment_8_3");
			builder.put(grammarAccess.getDetailCSAccess().getNameAssignment_0(), "rule__DetailCS__NameAssignment_0");
			builder.put(grammarAccess.getDetailCSAccess().getValuesAssignment_2(), "rule__DetailCS__ValuesAssignment_2");
			builder.put(grammarAccess.getDocumentationCSAccess().getValueAssignment_2(), "rule__DocumentationCS__ValueAssignment_2");
			builder.put(grammarAccess.getDocumentationCSAccess().getOwnedDetailsAssignment_3_1(), "rule__DocumentationCS__OwnedDetailsAssignment_3_1");
			builder.put(grammarAccess.getDocumentationCSAccess().getOwnedDetailsAssignment_3_2_1(), "rule__DocumentationCS__OwnedDetailsAssignment_3_2_1");
			builder.put(grammarAccess.getImportCSAccess().getNameAssignment_1_0(), "rule__ImportCS__NameAssignment_1_0");
			builder.put(grammarAccess.getImportCSAccess().getOwnedPathNameAssignment_2(), "rule__ImportCS__OwnedPathNameAssignment_2");
			builder.put(grammarAccess.getImportCSAccess().getIsAllAssignment_3(), "rule__ImportCS__IsAllAssignment_3");
			builder.put(grammarAccess.getInvCSAccess().getStereotypeAssignment_0(), "rule__InvCS__StereotypeAssignment_0");
			builder.put(grammarAccess.getInvCSAccess().getNameAssignment_1_0(), "rule__InvCS__NameAssignment_1_0");
			builder.put(grammarAccess.getInvCSAccess().getOwnedMessageSpecificationAssignment_1_1_1(), "rule__InvCS__OwnedMessageSpecificationAssignment_1_1_1");
			builder.put(grammarAccess.getInvCSAccess().getOwnedSpecificationAssignment_3(), "rule__InvCS__OwnedSpecificationAssignment_3");
			builder.put(grammarAccess.getLibCoercionCSAccess().getNameAssignment_1(), "rule__LibCoercionCS__NameAssignment_1");
			builder.put(grammarAccess.getLibCoercionCSAccess().getOwnedTypeAssignment_5(), "rule__LibCoercionCS__OwnedTypeAssignment_5");
			builder.put(grammarAccess.getLibCoercionCSAccess().getImplementationAssignment_6_1(), "rule__LibCoercionCS__ImplementationAssignment_6_1");
			builder.put(grammarAccess.getLibCoercionCSAccess().getOwnedAnnotationsAssignment_7_0_1_0(), "rule__LibCoercionCS__OwnedAnnotationsAssignment_7_0_1_0");
			builder.put(grammarAccess.getLibCoercionCSAccess().getOwnedPreconditionsAssignment_7_0_1_1(), "rule__LibCoercionCS__OwnedPreconditionsAssignment_7_0_1_1");
			builder.put(grammarAccess.getLibCoercionCSAccess().getOwnedPostconditionsAssignment_7_0_1_2(), "rule__LibCoercionCS__OwnedPostconditionsAssignment_7_0_1_2");
			builder.put(grammarAccess.getLibIterationCSAccess().getNameAssignment_1(), "rule__LibIterationCS__NameAssignment_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedSignatureAssignment_2(), "rule__LibIterationCS__OwnedSignatureAssignment_2");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedIteratorsAssignment_4(), "rule__LibIterationCS__OwnedIteratorsAssignment_4");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedIteratorsAssignment_5_1(), "rule__LibIterationCS__OwnedIteratorsAssignment_5_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedAccumulatorsAssignment_6_1(), "rule__LibIterationCS__OwnedAccumulatorsAssignment_6_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedAccumulatorsAssignment_6_2_1(), "rule__LibIterationCS__OwnedAccumulatorsAssignment_6_2_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedParametersAssignment_7_1(), "rule__LibIterationCS__OwnedParametersAssignment_7_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedParametersAssignment_7_2_1(), "rule__LibIterationCS__OwnedParametersAssignment_7_2_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedTypeAssignment_10(), "rule__LibIterationCS__OwnedTypeAssignment_10");
			builder.put(grammarAccess.getLibIterationCSAccess().getIsInvalidatingAssignment_11(), "rule__LibIterationCS__IsInvalidatingAssignment_11");
			builder.put(grammarAccess.getLibIterationCSAccess().getIsValidatingAssignment_12(), "rule__LibIterationCS__IsValidatingAssignment_12");
			builder.put(grammarAccess.getLibIterationCSAccess().getImplementationAssignment_13_1(), "rule__LibIterationCS__ImplementationAssignment_13_1");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedAnnotationsAssignment_14_0_1_0(), "rule__LibIterationCS__OwnedAnnotationsAssignment_14_0_1_0");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedBodyExpressionsAssignment_14_0_1_1_3(), "rule__LibIterationCS__OwnedBodyExpressionsAssignment_14_0_1_1_3");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedPreconditionsAssignment_14_0_1_2(), "rule__LibIterationCS__OwnedPreconditionsAssignment_14_0_1_2");
			builder.put(grammarAccess.getLibIterationCSAccess().getOwnedPostconditionsAssignment_14_0_1_3(), "rule__LibIterationCS__OwnedPostconditionsAssignment_14_0_1_3");
			builder.put(grammarAccess.getIteratorCSAccess().getNameAssignment_0(), "rule__IteratorCS__NameAssignment_0");
			builder.put(grammarAccess.getIteratorCSAccess().getOwnedTypeAssignment_2(), "rule__IteratorCS__OwnedTypeAssignment_2");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getNameAssignment_0(), "rule__LambdaTypeCS__NameAssignment_0");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getOwnedSignatureAssignment_1(), "rule__LambdaTypeCS__OwnedSignatureAssignment_1");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getOwnedContextTypeAssignment_2(), "rule__LambdaTypeCS__OwnedContextTypeAssignment_2");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getOwnedParameterTypesAssignment_4_0(), "rule__LambdaTypeCS__OwnedParameterTypesAssignment_4_0");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getOwnedParameterTypesAssignment_4_1_1(), "rule__LambdaTypeCS__OwnedParameterTypesAssignment_4_1_1");
			builder.put(grammarAccess.getLambdaTypeCSAccess().getOwnedResultTypeAssignment_7(), "rule__LambdaTypeCS__OwnedResultTypeAssignment_7");
			builder.put(grammarAccess.getLambdaContextTypeRefCSAccess().getOwnedPathNameAssignment(), "rule__LambdaContextTypeRefCS__OwnedPathNameAssignment");
			builder.put(grammarAccess.getLibOperationCSAccess().getIsStaticAssignment_0(), "rule__LibOperationCS__IsStaticAssignment_0");
			builder.put(grammarAccess.getLibOperationCSAccess().getNameAssignment_2(), "rule__LibOperationCS__NameAssignment_2");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedSignatureAssignment_3(), "rule__LibOperationCS__OwnedSignatureAssignment_3");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedParametersAssignment_5_0(), "rule__LibOperationCS__OwnedParametersAssignment_5_0");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedParametersAssignment_5_1_1(), "rule__LibOperationCS__OwnedParametersAssignment_5_1_1");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedTypeAssignment_8(), "rule__LibOperationCS__OwnedTypeAssignment_8");
			builder.put(grammarAccess.getLibOperationCSAccess().getIsValidatingAssignment_9(), "rule__LibOperationCS__IsValidatingAssignment_9");
			builder.put(grammarAccess.getLibOperationCSAccess().getIsInvalidatingAssignment_10(), "rule__LibOperationCS__IsInvalidatingAssignment_10");
			builder.put(grammarAccess.getLibOperationCSAccess().getPrecedenceAssignment_11_2(), "rule__LibOperationCS__PrecedenceAssignment_11_2");
			builder.put(grammarAccess.getLibOperationCSAccess().getImplementationAssignment_12_1(), "rule__LibOperationCS__ImplementationAssignment_12_1");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedAnnotationsAssignment_13_0_1_0(), "rule__LibOperationCS__OwnedAnnotationsAssignment_13_0_1_0");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedBodyExpressionsAssignment_13_0_1_1_3(), "rule__LibOperationCS__OwnedBodyExpressionsAssignment_13_0_1_1_3");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedPostconditionsAssignment_13_0_1_2(), "rule__LibOperationCS__OwnedPostconditionsAssignment_13_0_1_2");
			builder.put(grammarAccess.getLibOperationCSAccess().getOwnedPreconditionsAssignment_13_0_1_3(), "rule__LibOperationCS__OwnedPreconditionsAssignment_13_0_1_3");
			builder.put(grammarAccess.getLibOppositeCSAccess().getNameAssignment_1(), "rule__LibOppositeCS__NameAssignment_1");
			builder.put(grammarAccess.getLibOppositeCSAccess().getOwnedTypeAssignment_3(), "rule__LibOppositeCS__OwnedTypeAssignment_3");
			builder.put(grammarAccess.getLibPackageCSAccess().getNameAssignment_1(), "rule__LibPackageCS__NameAssignment_1");
			builder.put(grammarAccess.getLibPackageCSAccess().getNsPrefixAssignment_2_1(), "rule__LibPackageCS__NsPrefixAssignment_2_1");
			builder.put(grammarAccess.getLibPackageCSAccess().getNsURIAssignment_2_3(), "rule__LibPackageCS__NsURIAssignment_2_3");
			builder.put(grammarAccess.getLibPackageCSAccess().getOwnedPackagesAssignment_4_0(), "rule__LibPackageCS__OwnedPackagesAssignment_4_0");
			builder.put(grammarAccess.getLibPackageCSAccess().getOwnedPrecedencesAssignment_4_1_1(), "rule__LibPackageCS__OwnedPrecedencesAssignment_4_1_1");
			builder.put(grammarAccess.getLibPackageCSAccess().getOwnedClassesAssignment_4_2(), "rule__LibPackageCS__OwnedClassesAssignment_4_2");
			builder.put(grammarAccess.getLibPackageCSAccess().getOwnedAnnotationsAssignment_4_3(), "rule__LibPackageCS__OwnedAnnotationsAssignment_4_3");
			builder.put(grammarAccess.getPackageCSAccess().getNameAssignment_1(), "rule__PackageCS__NameAssignment_1");
			builder.put(grammarAccess.getPackageCSAccess().getNsPrefixAssignment_2_1(), "rule__PackageCS__NsPrefixAssignment_2_1");
			builder.put(grammarAccess.getPackageCSAccess().getNsURIAssignment_2_3(), "rule__PackageCS__NsURIAssignment_2_3");
			builder.put(grammarAccess.getPackageCSAccess().getOwnedPackagesAssignment_4_0(), "rule__PackageCS__OwnedPackagesAssignment_4_0");
			builder.put(grammarAccess.getPackageCSAccess().getOwnedClassesAssignment_4_1(), "rule__PackageCS__OwnedClassesAssignment_4_1");
			builder.put(grammarAccess.getPackageCSAccess().getOwnedAnnotationsAssignment_4_2(), "rule__PackageCS__OwnedAnnotationsAssignment_4_2");
			builder.put(grammarAccess.getParameterCSAccess().getNameAssignment_0(), "rule__ParameterCS__NameAssignment_0");
			builder.put(grammarAccess.getParameterCSAccess().getOwnedTypeAssignment_2(), "rule__ParameterCS__OwnedTypeAssignment_2");
			builder.put(grammarAccess.getLibPropertyCSAccess().getIsStaticAssignment_0(), "rule__LibPropertyCS__IsStaticAssignment_0");
			builder.put(grammarAccess.getLibPropertyCSAccess().getNameAssignment_2(), "rule__LibPropertyCS__NameAssignment_2");
			builder.put(grammarAccess.getLibPropertyCSAccess().getOwnedTypeAssignment_4(), "rule__LibPropertyCS__OwnedTypeAssignment_4");
			builder.put(grammarAccess.getLibPropertyCSAccess().getOwnedOppositeAssignment_5(), "rule__LibPropertyCS__OwnedOppositeAssignment_5");
			builder.put(grammarAccess.getLibPropertyCSAccess().getImplementationAssignment_6_1(), "rule__LibPropertyCS__ImplementationAssignment_6_1");
			builder.put(grammarAccess.getLibPropertyCSAccess().getOwnedAnnotationsAssignment_7_0_1(), "rule__LibPropertyCS__OwnedAnnotationsAssignment_7_0_1");
			builder.put(grammarAccess.getPostCSAccess().getStereotypeAssignment_0(), "rule__PostCS__StereotypeAssignment_0");
			builder.put(grammarAccess.getPostCSAccess().getNameAssignment_1_0(), "rule__PostCS__NameAssignment_1_0");
			builder.put(grammarAccess.getPostCSAccess().getOwnedMessageSpecificationAssignment_1_1_1(), "rule__PostCS__OwnedMessageSpecificationAssignment_1_1_1");
			builder.put(grammarAccess.getPostCSAccess().getOwnedSpecificationAssignment_3(), "rule__PostCS__OwnedSpecificationAssignment_3");
			builder.put(grammarAccess.getPreCSAccess().getStereotypeAssignment_0(), "rule__PreCS__StereotypeAssignment_0");
			builder.put(grammarAccess.getPreCSAccess().getNameAssignment_1_0(), "rule__PreCS__NameAssignment_1_0");
			builder.put(grammarAccess.getPreCSAccess().getOwnedMessageSpecificationAssignment_1_1_1(), "rule__PreCS__OwnedMessageSpecificationAssignment_1_1_1");
			builder.put(grammarAccess.getPreCSAccess().getOwnedSpecificationAssignment_3(), "rule__PreCS__OwnedSpecificationAssignment_3");
			builder.put(grammarAccess.getPrecedenceCSAccess().getIsRightAssociativeAssignment_0_1(), "rule__PrecedenceCS__IsRightAssociativeAssignment_0_1");
			builder.put(grammarAccess.getPrecedenceCSAccess().getNameAssignment_2(), "rule__PrecedenceCS__NameAssignment_2");
			builder.put(grammarAccess.getSpecificationCSAccess().getOwnedExpressionAssignment(), "rule__SpecificationCS__OwnedExpressionAssignment");
			builder.put(grammarAccess.getTypedMultiplicityRefCSAccess().getOwnedMultiplicityAssignment_1(), "rule__TypedMultiplicityRefCS__OwnedMultiplicityAssignment_1");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getIsTypeofAssignment_0_0(), "rule__TypedTypeRefCS__IsTypeofAssignment_0_0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_0_2(), "rule__TypedTypeRefCS__OwnedPathNameAssignment_0_2");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_1_0(), "rule__TypedTypeRefCS__OwnedPathNameAssignment_1_0");
			builder.put(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingAssignment_1_1_1(), "rule__TypedTypeRefCS__OwnedBindingAssignment_1_1_1");
			builder.put(grammarAccess.getTuplePartCSAccess().getNameAssignment_0(), "rule__TuplePartCS__NameAssignment_0");
			builder.put(grammarAccess.getTuplePartCSAccess().getOwnedTypeAssignment_2(), "rule__TuplePartCS__OwnedTypeAssignment_2");
			builder.put(grammarAccess.getModelAccess().getOwnedExpressionAssignment(), "rule__Model__OwnedExpressionAssignment");
			builder.put(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsAssignment_0(), "rule__URIPathNameCS__OwnedPathElementsAssignment_0");
			builder.put(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsAssignment_1_1(), "rule__URIPathNameCS__OwnedPathElementsAssignment_1_1");
			builder.put(grammarAccess.getURIFirstPathElementCSAccess().getReferredElementAssignment_0(), "rule__URIFirstPathElementCS__ReferredElementAssignment_0");
			builder.put(grammarAccess.getURIFirstPathElementCSAccess().getReferredElementAssignment_1_1(), "rule__URIFirstPathElementCS__ReferredElementAssignment_1_1");
			builder.put(grammarAccess.getSimplePathNameCSAccess().getOwnedPathElementsAssignment(), "rule__SimplePathNameCS__OwnedPathElementsAssignment");
			builder.put(grammarAccess.getPrimitiveTypeCSAccess().getNameAssignment(), "rule__PrimitiveTypeCS__NameAssignment");
			builder.put(grammarAccess.getCollectionTypeCSAccess().getNameAssignment_0(), "rule__CollectionTypeCS__NameAssignment_0");
			builder.put(grammarAccess.getCollectionTypeCSAccess().getOwnedTypeAssignment_1_1(), "rule__CollectionTypeCS__OwnedTypeAssignment_1_1");
			builder.put(grammarAccess.getCollectionTypeCSAccess().getOwnedCollectionMultiplicityAssignment_1_2(), "rule__CollectionTypeCS__OwnedCollectionMultiplicityAssignment_1_2");
			builder.put(grammarAccess.getMapTypeCSAccess().getNameAssignment_0(), "rule__MapTypeCS__NameAssignment_0");
			builder.put(grammarAccess.getMapTypeCSAccess().getOwnedKeyTypeAssignment_1_1(), "rule__MapTypeCS__OwnedKeyTypeAssignment_1_1");
			builder.put(grammarAccess.getMapTypeCSAccess().getOwnedValueTypeAssignment_1_3(), "rule__MapTypeCS__OwnedValueTypeAssignment_1_3");
			builder.put(grammarAccess.getTupleTypeCSAccess().getNameAssignment_0(), "rule__TupleTypeCS__NameAssignment_0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getOwnedPartsAssignment_1_1_0(), "rule__TupleTypeCS__OwnedPartsAssignment_1_1_0");
			builder.put(grammarAccess.getTupleTypeCSAccess().getOwnedPartsAssignment_1_1_1_1(), "rule__TupleTypeCS__OwnedPartsAssignment_1_1_1_1");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedTypeAssignment_0(), "rule__CollectionLiteralExpCS__OwnedTypeAssignment_0");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsAssignment_2_0(), "rule__CollectionLiteralExpCS__OwnedPartsAssignment_2_0");
			builder.put(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsAssignment_2_1_1(), "rule__CollectionLiteralExpCS__OwnedPartsAssignment_2_1_1");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionAssignment_0_0(), "rule__CollectionLiteralPartCS__OwnedExpressionAssignment_0_0");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedLastExpressionAssignment_0_1_1(), "rule__CollectionLiteralPartCS__OwnedLastExpressionAssignment_0_1_1");
			builder.put(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionAssignment_1(), "rule__CollectionLiteralPartCS__OwnedExpressionAssignment_1");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getOwnedTypeAssignment_0(), "rule__CollectionPatternCS__OwnedTypeAssignment_0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsAssignment_2_0(), "rule__CollectionPatternCS__OwnedPartsAssignment_2_0");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsAssignment_2_1_1(), "rule__CollectionPatternCS__OwnedPartsAssignment_2_1_1");
			builder.put(grammarAccess.getCollectionPatternCSAccess().getRestVariableNameAssignment_2_2_1(), "rule__CollectionPatternCS__RestVariableNameAssignment_2_2_1");
			builder.put(grammarAccess.getShadowPartCSAccess().getReferredPropertyAssignment_0_0(), "rule__ShadowPartCS__ReferredPropertyAssignment_0_0");
			builder.put(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionAssignment_0_2(), "rule__ShadowPartCS__OwnedInitExpressionAssignment_0_2");
			builder.put(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionAssignment_1(), "rule__ShadowPartCS__OwnedInitExpressionAssignment_1");
			builder.put(grammarAccess.getPatternExpCSAccess().getPatternVariableNameAssignment_0(), "rule__PatternExpCS__PatternVariableNameAssignment_0");
			builder.put(grammarAccess.getPatternExpCSAccess().getOwnedPatternTypeAssignment_2(), "rule__PatternExpCS__OwnedPatternTypeAssignment_2");
			builder.put(grammarAccess.getLambdaLiteralExpCSAccess().getOwnedExpressionCSAssignment_2(), "rule__LambdaLiteralExpCS__OwnedExpressionCSAssignment_2");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getOwnedTypeAssignment_0(), "rule__MapLiteralExpCS__OwnedTypeAssignment_0");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsAssignment_2_0(), "rule__MapLiteralExpCS__OwnedPartsAssignment_2_0");
			builder.put(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsAssignment_2_1_1(), "rule__MapLiteralExpCS__OwnedPartsAssignment_2_1_1");
			builder.put(grammarAccess.getMapLiteralPartCSAccess().getOwnedKeyAssignment_0(), "rule__MapLiteralPartCS__OwnedKeyAssignment_0");
			builder.put(grammarAccess.getMapLiteralPartCSAccess().getOwnedValueAssignment_2(), "rule__MapLiteralPartCS__OwnedValueAssignment_2");
			builder.put(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsAssignment_2(), "rule__TupleLiteralExpCS__OwnedPartsAssignment_2");
			builder.put(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsAssignment_3_1(), "rule__TupleLiteralExpCS__OwnedPartsAssignment_3_1");
			builder.put(grammarAccess.getTupleLiteralPartCSAccess().getNameAssignment_0(), "rule__TupleLiteralPartCS__NameAssignment_0");
			builder.put(grammarAccess.getTupleLiteralPartCSAccess().getOwnedTypeAssignment_1_1(), "rule__TupleLiteralPartCS__OwnedTypeAssignment_1_1");
			builder.put(grammarAccess.getTupleLiteralPartCSAccess().getOwnedInitExpressionAssignment_3(), "rule__TupleLiteralPartCS__OwnedInitExpressionAssignment_3");
			builder.put(grammarAccess.getNumberLiteralExpCSAccess().getSymbolAssignment(), "rule__NumberLiteralExpCS__SymbolAssignment");
			builder.put(grammarAccess.getStringLiteralExpCSAccess().getSegmentsAssignment(), "rule__StringLiteralExpCS__SegmentsAssignment");
			builder.put(grammarAccess.getBooleanLiteralExpCSAccess().getSymbolAssignment_0(), "rule__BooleanLiteralExpCS__SymbolAssignment_0");
			builder.put(grammarAccess.getBooleanLiteralExpCSAccess().getSymbolAssignment_1(), "rule__BooleanLiteralExpCS__SymbolAssignment_1");
			builder.put(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getOwnedMultiplicityAssignment_1(), "rule__TypeLiteralWithMultiplicityCS__OwnedMultiplicityAssignment_1");
			builder.put(grammarAccess.getTypeLiteralExpCSAccess().getOwnedTypeAssignment(), "rule__TypeLiteralExpCS__OwnedTypeAssignment");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getOwnedPathNameAssignment_0(), "rule__TypeNameExpCS__OwnedPathNameAssignment_0");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getOwnedCurlyBracketedClauseAssignment_1_0(), "rule__TypeNameExpCS__OwnedCurlyBracketedClauseAssignment_1_0");
			builder.put(grammarAccess.getTypeNameExpCSAccess().getOwnedPatternGuardAssignment_1_1_1(), "rule__TypeNameExpCS__OwnedPatternGuardAssignment_1_1_1");
			builder.put(grammarAccess.getTypeExpCSAccess().getOwnedMultiplicityAssignment_1(), "rule__TypeExpCS__OwnedMultiplicityAssignment_1");
			builder.put(grammarAccess.getExpCSAccess().getNameAssignment_0_1_1(), "rule__ExpCS__NameAssignment_0_1_1");
			builder.put(grammarAccess.getExpCSAccess().getOwnedRightAssignment_0_1_2(), "rule__ExpCS__OwnedRightAssignment_0_1_2");
			builder.put(grammarAccess.getPrefixedLetExpCSAccess().getNameAssignment_0_1(), "rule__PrefixedLetExpCS__NameAssignment_0_1");
			builder.put(grammarAccess.getPrefixedLetExpCSAccess().getOwnedRightAssignment_0_2(), "rule__PrefixedLetExpCS__OwnedRightAssignment_0_2");
			builder.put(grammarAccess.getPrefixedPrimaryExpCSAccess().getNameAssignment_0_1(), "rule__PrefixedPrimaryExpCS__NameAssignment_0_1");
			builder.put(grammarAccess.getPrefixedPrimaryExpCSAccess().getOwnedRightAssignment_0_2(), "rule__PrefixedPrimaryExpCS__OwnedRightAssignment_0_2");
			builder.put(grammarAccess.getNameExpCSAccess().getOwnedPathNameAssignment_0(), "rule__NameExpCS__OwnedPathNameAssignment_0");
			builder.put(grammarAccess.getNameExpCSAccess().getOwnedSquareBracketedClausesAssignment_1(), "rule__NameExpCS__OwnedSquareBracketedClausesAssignment_1");
			builder.put(grammarAccess.getNameExpCSAccess().getOwnedRoundBracketedClauseAssignment_2(), "rule__NameExpCS__OwnedRoundBracketedClauseAssignment_2");
			builder.put(grammarAccess.getNameExpCSAccess().getOwnedCurlyBracketedClauseAssignment_3(), "rule__NameExpCS__OwnedCurlyBracketedClauseAssignment_3");
			builder.put(grammarAccess.getNameExpCSAccess().getIsPreAssignment_4_0(), "rule__NameExpCS__IsPreAssignment_4_0");
			builder.put(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsAssignment_2_0(), "rule__CurlyBracketedClauseCS__OwnedPartsAssignment_2_0");
			builder.put(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsAssignment_2_1_1(), "rule__CurlyBracketedClauseCS__OwnedPartsAssignment_2_1_1");
			builder.put(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsAssignment_2_0(), "rule__RoundBracketedClauseCS__OwnedArgumentsAssignment_2_0");
			builder.put(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsAssignment_2_1(), "rule__RoundBracketedClauseCS__OwnedArgumentsAssignment_2_1");
			builder.put(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsAssignment_1(), "rule__SquareBracketedClauseCS__OwnedTermsAssignment_1");
			builder.put(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsAssignment_2_1(), "rule__SquareBracketedClauseCS__OwnedTermsAssignment_2_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedNameExpressionAssignment_0_0(), "rule__NavigatingArgCS__OwnedNameExpressionAssignment_0_0");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorAssignment_0_1_0_1(), "rule__NavigatingArgCS__OwnedCoIteratorAssignment_0_1_0_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionAssignment_0_1_0_2_1(), "rule__NavigatingArgCS__OwnedInitExpressionAssignment_0_1_0_2_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeAssignment_0_1_1_1(), "rule__NavigatingArgCS__OwnedTypeAssignment_0_1_1_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorAssignment_0_1_1_2_1(), "rule__NavigatingArgCS__OwnedCoIteratorAssignment_0_1_1_2_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionAssignment_0_1_1_3_1(), "rule__NavigatingArgCS__OwnedInitExpressionAssignment_0_1_1_3_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeAssignment_0_1_2_0_1(), "rule__NavigatingArgCS__OwnedTypeAssignment_0_1_2_0_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorAssignment_0_1_2_1_1(), "rule__NavigatingArgCS__OwnedCoIteratorAssignment_0_1_2_1_1");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionAssignment_0_1_2_3(), "rule__NavigatingArgCS__OwnedInitExpressionAssignment_0_1_2_3");
			builder.put(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeAssignment_1_1(), "rule__NavigatingArgCS__OwnedTypeAssignment_1_1");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getPrefixAssignment_0(), "rule__NavigatingBarArgCS__PrefixAssignment_0");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getOwnedNameExpressionAssignment_1(), "rule__NavigatingBarArgCS__OwnedNameExpressionAssignment_1");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getOwnedTypeAssignment_2_1(), "rule__NavigatingBarArgCS__OwnedTypeAssignment_2_1");
			builder.put(grammarAccess.getNavigatingBarArgCSAccess().getOwnedInitExpressionAssignment_2_2_1(), "rule__NavigatingBarArgCS__OwnedInitExpressionAssignment_2_2_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getPrefixAssignment_0(), "rule__NavigatingCommaArgCS__PrefixAssignment_0");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedNameExpressionAssignment_1(), "rule__NavigatingCommaArgCS__OwnedNameExpressionAssignment_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorAssignment_2_0_1(), "rule__NavigatingCommaArgCS__OwnedCoIteratorAssignment_2_0_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionAssignment_2_0_2_1(), "rule__NavigatingCommaArgCS__OwnedInitExpressionAssignment_2_0_2_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeAssignment_2_1_1(), "rule__NavigatingCommaArgCS__OwnedTypeAssignment_2_1_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorAssignment_2_1_2_1(), "rule__NavigatingCommaArgCS__OwnedCoIteratorAssignment_2_1_2_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionAssignment_2_1_3_1(), "rule__NavigatingCommaArgCS__OwnedInitExpressionAssignment_2_1_3_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeAssignment_2_2_0_1(), "rule__NavigatingCommaArgCS__OwnedTypeAssignment_2_2_0_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorAssignment_2_2_1_1(), "rule__NavigatingCommaArgCS__OwnedCoIteratorAssignment_2_2_1_1");
			builder.put(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionAssignment_2_2_3(), "rule__NavigatingCommaArgCS__OwnedInitExpressionAssignment_2_2_3");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getPrefixAssignment_0(), "rule__NavigatingSemiArgCS__PrefixAssignment_0");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedNameExpressionAssignment_1(), "rule__NavigatingSemiArgCS__OwnedNameExpressionAssignment_1");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedTypeAssignment_2_1(), "rule__NavigatingSemiArgCS__OwnedTypeAssignment_2_1");
			builder.put(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedInitExpressionAssignment_2_2_1(), "rule__NavigatingSemiArgCS__OwnedInitExpressionAssignment_2_2_1");
			builder.put(grammarAccess.getCoIteratorVariableCSAccess().getNameAssignment_0(), "rule__CoIteratorVariableCS__NameAssignment_0");
			builder.put(grammarAccess.getCoIteratorVariableCSAccess().getOwnedTypeAssignment_1_1(), "rule__CoIteratorVariableCS__OwnedTypeAssignment_1_1");
			builder.put(grammarAccess.getIfExpCSAccess().getOwnedConditionAssignment_1(), "rule__IfExpCS__OwnedConditionAssignment_1");
			builder.put(grammarAccess.getIfExpCSAccess().getOwnedThenExpressionAssignment_3(), "rule__IfExpCS__OwnedThenExpressionAssignment_3");
			builder.put(grammarAccess.getIfExpCSAccess().getOwnedIfThenExpressionsAssignment_4(), "rule__IfExpCS__OwnedIfThenExpressionsAssignment_4");
			builder.put(grammarAccess.getIfExpCSAccess().getOwnedElseExpressionAssignment_6(), "rule__IfExpCS__OwnedElseExpressionAssignment_6");
			builder.put(grammarAccess.getElseIfThenExpCSAccess().getOwnedConditionAssignment_1(), "rule__ElseIfThenExpCS__OwnedConditionAssignment_1");
			builder.put(grammarAccess.getElseIfThenExpCSAccess().getOwnedThenExpressionAssignment_3(), "rule__ElseIfThenExpCS__OwnedThenExpressionAssignment_3");
			builder.put(grammarAccess.getLetExpCSAccess().getOwnedVariablesAssignment_1(), "rule__LetExpCS__OwnedVariablesAssignment_1");
			builder.put(grammarAccess.getLetExpCSAccess().getOwnedVariablesAssignment_2_1(), "rule__LetExpCS__OwnedVariablesAssignment_2_1");
			builder.put(grammarAccess.getLetExpCSAccess().getOwnedInExpressionAssignment_4(), "rule__LetExpCS__OwnedInExpressionAssignment_4");
			builder.put(grammarAccess.getLetVariableCSAccess().getNameAssignment_0(), "rule__LetVariableCS__NameAssignment_0");
			builder.put(grammarAccess.getLetVariableCSAccess().getOwnedRoundBracketedClauseAssignment_1(), "rule__LetVariableCS__OwnedRoundBracketedClauseAssignment_1");
			builder.put(grammarAccess.getLetVariableCSAccess().getOwnedTypeAssignment_2_1(), "rule__LetVariableCS__OwnedTypeAssignment_2_1");
			builder.put(grammarAccess.getLetVariableCSAccess().getOwnedInitExpressionAssignment_4(), "rule__LetVariableCS__OwnedInitExpressionAssignment_4");
			builder.put(grammarAccess.getNestedExpCSAccess().getOwnedExpressionAssignment_1(), "rule__NestedExpCS__OwnedExpressionAssignment_1");
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
			builder.put(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsAssignment_2_1(), "rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1");
		}
	}

	@Inject
	private NameMappings nameMappings;

	@Inject
	private OCLstdlibGrammarAccess grammarAccess;

	@Override
	protected InternalOCLstdlibParser createParser() {
		InternalOCLstdlibParser result = new InternalOCLstdlibParser(null);
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

	public OCLstdlibGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(OCLstdlibGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	public NameMappings getNameMappings() {
		return nameMappings;
	}

	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
