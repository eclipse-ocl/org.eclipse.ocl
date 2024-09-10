/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.xtext.essentialocl/model/EssentialOCLCS.genmodel
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.xtext.essentialoclcs.util;

import java.lang.Object;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.base.utilities.BaseCSUnloadVisitor;

/**
 * An AbstractEssentialOCLCSUnloadVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class' first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractEssentialOCLCSUnloadVisitor
	extends BaseCSUnloadVisitor
	implements EssentialOCLCSVisitor<Object>
{
	/**
	 * Initializes me with an initial value for my result.
	 *
	 * @param context my initial result value
	 */
	protected AbstractEssentialOCLCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	@Override
	public @Nullable Object visitAbstractNameExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull AbstractNameExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitAssociationClassCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull AssociationClassCallExpCS object) {
		return visitCallExpCS(object);
	}

	@Override
	public @Nullable Object visitBooleanLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull BooleanLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CallExpCS object) {
		return visitAbstractNameExpCS(object);
	}

	@Override
	public @Nullable Object visitCollectionLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CollectionLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitCollectionLiteralPartCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CollectionLiteralPartCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitCollectionPatternCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CollectionPatternCS object) {
		return visitTypedRefCS(object);
	}

	@Override
	public @Nullable Object visitCollectionTypeCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CollectionTypeCS object) {
		return visitTypedRefCS(object);
	}

	@Override
	public @Nullable Object visitContextCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull ContextCS object) {
		return visitNamedElementCS(object);
	}

	@Override
	public @Nullable Object visitCurlyBracketedClauseCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull CurlyBracketedClauseCS object) {
		return visitContextLessElementCS(object);
	}

	@Override
	public @Nullable Object visitExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull ExpCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitExpSpecificationCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull ExpSpecificationCS object) {
		return visitSpecificationCS(object);
	}

	@Override
	public @Nullable Object visitIfExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull IfExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitIfThenExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull IfThenExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitInfixExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull InfixExpCS object) {
		return visitOperatorExpCS(object);
	}

	@Override
	public @Nullable Object visitInvalidLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull InvalidLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitIterateCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull IterateCallExpCS object) {
		return visitIterationCallExpCS(object);
	}

	@Override
	public @Nullable Object visitIterationCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull IterationCallExpCS object) {
		return visitCallExpCS(object);
	}

	@Override
	public @Nullable Object visitLambdaLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull LambdaLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitLetExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull LetExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitLetVariableCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull LetVariableCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull LiteralExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitMapLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull MapLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitMapLiteralPartCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull MapLiteralPartCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitMapTypeCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull MapTypeCS object) {
		return visitTypedRefCS(object);
	}

	@Override
	public @Nullable Object visitNameExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull NameExpCS object) {
		return visitAssociationClassCallExpCS(object);
	}

	@Override
	public @Nullable Object visitNavigatingArgCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull NavigatingArgCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitNestedExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull NestedExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitNullLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull NullLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitNumberLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull NumberLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitOperationCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull OperationCallExpCS object) {
		return visitCallExpCS(object);
	}

	@Override
	public @Nullable Object visitOperatorExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull OperatorExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitPatternExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull PatternExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitPrefixExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull PrefixExpCS object) {
		return visitOperatorExpCS(object);
	}

	@Override
	public @Nullable Object visitPrimitiveLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull PrimitiveLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitPropertyCallExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull PropertyCallExpCS object) {
		return visitCallExpCS(object);
	}

	@Override
	public @Nullable Object visitRoundBracketedClauseCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull RoundBracketedClauseCS object) {
		return visitContextLessElementCS(object);
	}

	@Override
	public @Nullable Object visitSelfExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull SelfExpCS object) {
		return visitExpCS(object);
	}

	@Override
	public @Nullable Object visitShadowExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull ShadowExpCS object) {
		return visitAbstractNameExpCS(object);
	}

	@Override
	public @Nullable Object visitShadowPartCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull ShadowPartCS object) {
		return visitModelElementCS(object);
	}

	@Override
	public @Nullable Object visitSquareBracketedClauseCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull SquareBracketedClauseCS object) {
		return visitContextLessElementCS(object);
	}

	@Override
	public @Nullable Object visitStringLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull StringLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitTupleLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull TupleLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitTupleLiteralPartCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull TupleLiteralPartCS object) {
		return visitVariableCS(object);
	}

	@Override
	public @Nullable Object visitTypeLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull TypeLiteralExpCS object) {
		return visitLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitTypeNameExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull TypeNameExpCS object) {
		return visitTypedRefCS(object);
	}

	@Override
	public @Nullable Object visitUnlimitedNaturalLiteralExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull UnlimitedNaturalLiteralExpCS object) {
		return visitPrimitiveLiteralExpCS(object);
	}

	@Override
	public @Nullable Object visitVariableCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull VariableCS object) {
		return visitNamedElementCS(object);
	}

	@Override
	public @Nullable Object visitVariableExpCS(org.eclipse.ocl.xtext.essentialoclcs.@NonNull VariableExpCS object) {
		return visitAbstractNameExpCS(object);
	}
}
