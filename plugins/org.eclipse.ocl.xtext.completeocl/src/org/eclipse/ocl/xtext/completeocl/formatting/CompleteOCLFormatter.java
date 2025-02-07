/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.formatting;

import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.BodySpecificationCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.ClassifierContextDeclCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.CompleteOCLNavigationOperatorNameElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.DefOperationCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.DefPropertyCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.DeriveSpecificationCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.ImportCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.InitSpecificationCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.InvConstraintCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.OperationContextDeclCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.PackageDeclarationCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.PostConstraintCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.PreConstraintCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.PropertyContextDeclCSElements;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess.TemplateSignatureCSElements;
import org.eclipse.ocl.xtext.essentialocl.formatting.AbstractEssentialOCLFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

/**
 * This class contains custom formatting description.
 */
public class CompleteOCLFormatter extends AbstractEssentialOCLFormatter
{
	@Override
	protected void configureFormatting(FormattingConfig c) {

		c.setAutoLinewrap(120);

		CompleteOCLGrammarAccess f = (CompleteOCLGrammarAccess) getGrammarAccess();

		configureCollectionLiteralExpCS(c, f.getCollectionLiteralExpCSAccess());
		configureCollectionTypeCS(c, f.getCollectionTypeCSAccess());
		configureCurlyBracketedClauseCS(c, f.getCurlyBracketedClauseCSAccess());
		configureElseIfThenExpCS(c, f.getElseIfThenExpCSAccess());
		configureEssentialOCLNavigationOperatorCS(c, f.getEssentialOCLNavigationOperatorNameAccess());
		configureExpCS(c, f.getExpCSAccess());
		configureIfExpCS(c, f.getIfExpCSAccess());
		configureLetExpCS(c, f.getLetExpCSAccess());
		configureMapLiteralExpCS(c, f.getMapLiteralExpCSAccess());
		configureMapTypeCS(c, f.getMapTypeCSAccess());
		configureMultiplicityBoundsCS(c, f.getMultiplicityBoundsCSAccess());
		configureMultiplicityCS(c, f.getMultiplicityCSAccess());
		configureMultiplicityStringCS(c, f.getMultiplicityStringCSAccess());
		configureNameExpCS(c, f.getNameExpCSAccess());
		configureNavigatingCommaArgCS(c, f.getNavigatingCommaArgCSAccess());
		configureNavigatingSemiArgCS(c, f.getNavigatingSemiArgCSAccess());
		configureNestedExpCS(c, f.getNestedExpCSAccess());
		configurePathNameCS(c, f.getPathNameCSAccess());
		configurePrimaryExpCS(c, f.getPrimaryExpCSAccess());
		configureRoundBracketedClauseCS(c, f.getRoundBracketedClauseCSAccess());
		configureSquareBracketedClauseCS(c, f.getSquareBracketedClauseCSAccess());
		configureTemplateBindingCS(c, f.getTemplateBindingCSAccess());
		//		configureTemplateSignatureCS(c, f.getTemplateSignatureCSAccess());
		configureTupleLiteralExpCS(c, f.getTupleLiteralExpCSAccess());
		configureTupleTypeCS(c, f.getTupleTypeCSAccess());
		configureTypedTypeRefCS(c, f.getTypedTypeRefCSAccess());
		configureURIPathNameCS(c, f.getURIPathNameCSAccess());

		c.setLinewrap(2).before(f.getML_COMMENTRule());
		c.setLinewrap(1).after(f.getML_COMMENTRule());

		{
			BodySpecificationCSElements a = f.getBodySpecificationCSAccess();
			c.setLinewrap(2).before(a.getBodyKeyword_0());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
		}
		{
			ClassifierContextDeclCSElements a = f.getClassifierContextDeclCSAccess();
			c.setLinewrap(2).before(a.getContextKeyword_0());
		}
		{
			CompleteOCLNavigationOperatorNameElements a = f.getCompleteOCLNavigationOperatorNameAccess();
			c.setNoSpace().before(a.getCircumflexAccentKeyword_0());
			c.setNoSpace().after(a.getCircumflexAccentKeyword_0());
			c.setLinewrap().before(a.getCircumflexAccentCircumflexAccentKeyword_1());
			c.setNoSpace().after(a.getCircumflexAccentCircumflexAccentKeyword_1());
		}
		{
			DefOperationCSElements a = f.getDefOperationCSAccess();
			c.setLinewrap(2).before(a.getDefKeyword_1());
			setNoSpaceLineWrap(c, a.getColonKeyword_3());
			c.setNoSpace().around(a.getLeftParenthesisKeyword_6());
			c.setNoSpace().before(a.getCommaKeyword_7_1_0());
			c.setNoSpace().before(a.getRightParenthesisKeyword_8());
			setNoSpaceLineWrap(c, a.getColonKeyword_9());
			c.setLinewrap(2).after(a.getOwnedSpecificationAssignment_12());
		}
		{
			DefPropertyCSElements a = f.getDefPropertyCSAccess();
			c.setLinewrap(2).before(a.getDefKeyword_1());
			setNoSpaceLineWrap(c, a.getColonKeyword_3());
			c.setLinewrap(2).after(a.getOwnedSpecificationAssignment_8());
		}
		{
			DeriveSpecificationCSElements a = f.getDeriveSpecificationCSAccess();
			c.setLinewrap(2).before(a.getDeriveKeyword_0());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
		}
		{
			ImportCSElements a = f.getImportCSAccess();
			c.setLinewrap().before(a.getImportKeyword_0_0());
			c.setLinewrap().before(a.getIncludeKeyword_0_1());
			c.setNoSpace().around(a.getIsAllColonColonAsteriskKeyword_3_0());
		}
		{
			InitSpecificationCSElements a = f.getInitSpecificationCSAccess();
			c.setLinewrap(2).before(a.getInitKeyword_0());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
		}
		{
			InvConstraintCSElements a = f.getInvConstraintCSAccess();
			c.setLinewrap(2).before(a.getInvKeyword_0());
			c.setNoSpace().around(a.getLeftParenthesisKeyword_1_1_0());
			c.setNoSpace().around(a.getRightParenthesisKeyword_1_1_2());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
			c.setLinewrap(2).after(a.getOwnedSpecificationAssignment_3());
			//		    c.setIndentation(a.getColonKeyword_2(), a.getWSTerminalRuleCall_4());
		}
		{
			OperationContextDeclCSElements a = f.getOperationContextDeclCSAccess();
			c.setLinewrap(2).before(a.getContextKeyword_0());
			c.setNoSpace().around(a.getLeftParenthesisKeyword_3());
			c.setNoSpace().before(a.getCommaKeyword_4_1_0());
			c.setNoSpace().before(a.getRightParenthesisKeyword_5());
			c.setLinewrap(1).after(a.getOwnedTypeAssignment_7());
			c.setIndentation(a.getLeftParenthesisKeyword_3(), a.getRightParenthesisKeyword_5());
			c.setLinewrap(2).after(a.getOwnedBodiesAssignment_8_2());
		}
		{
			PackageDeclarationCSElements a = f.getPackageDeclarationCSAccess();
			c.setLinewrap(2).before(a.getPackageKeyword_0());
			c.setLinewrap(2).before(a.getEndpackageKeyword_4());
			c.setLinewrap(2).after(a.getEndpackageKeyword_4());
			c.setIndentation(a.getPackageKeyword_0(), a.getEndpackageKeyword_4());
		}
		{
			PostConstraintCSElements a = f.getPostConstraintCSAccess();
			c.setLinewrap(2).before(a.getPostKeyword_0());
			c.setNoSpace().around(a.getLeftParenthesisKeyword_1_1_0());
			c.setNoSpace().around(a.getRightParenthesisKeyword_1_1_2());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
			c.setLinewrap(2).after(a.getOwnedSpecificationAssignment_3());
			//		    c.setIndentation(a.getColonKeyword_2(), a.getWSTerminalRuleCall_4());
		}
		{
			PreConstraintCSElements a = f.getPreConstraintCSAccess();
			c.setLinewrap(2).before(a.getPreKeyword_0());
			c.setNoSpace().around(a.getLeftParenthesisKeyword_1_1_0());
			c.setNoSpace().around(a.getRightParenthesisKeyword_1_1_2());
			setNoSpaceLineWrap(c, a.getColonKeyword_2());
			c.setLinewrap(2).after(a.getOwnedSpecificationAssignment_3());
			//		    c.setIndentation(a.getColonKeyword_2(), a.getWSTerminalRuleCall_4());
		}
		{
			PropertyContextDeclCSElements a = f.getPropertyContextDeclCSAccess();
			c.setLinewrap(2).before(a.getContextKeyword_0());
			c.setLinewrap(1).after(a.getOwnedTypeAssignment_3());
			c.setLinewrap(2).after(a.getOwnedDefaultExpressionsAssignment_4());
		}
		{
			TemplateSignatureCSElements a = f.getTemplateSignatureCSAccess();
			c.setNoSpace().around(a.getLeftParenthesisKeyword_0_0());
			c.setNoSpace().before(a.getCommaKeyword_0_2_0());
			c.setNoSpace().before(a.getRightParenthesisKeyword_0_3());
			c.setIndentation(a.getLeftParenthesisKeyword_0_0(), a.getRightParenthesisKeyword_0_3());
			c.setNoSpace().around(a.getLessThanSignKeyword_1_0());
			c.setNoSpace().before(a.getCommaKeyword_1_2_0());
			c.setNoSpace().before(a.getGreaterThanSignKeyword_1_3());
			c.setIndentation(a.getLessThanSignKeyword_1_0(), a.getGreaterThanSignKeyword_1_3());
		}
		{	// comments
			c.setNoLinewrap().before(f.getSL_COMMENTRule());
		}
	}
}
