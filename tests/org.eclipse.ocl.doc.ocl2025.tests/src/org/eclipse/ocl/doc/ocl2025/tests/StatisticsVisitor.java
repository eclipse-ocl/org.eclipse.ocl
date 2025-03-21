/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.doc.ocl2025.tests;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.doc.ocl2025.tests.Statistics.ClassStatistics;
import org.eclipse.ocl.doc.ocl2025.tests.Statistics.ConstraintStatistics;
import org.eclipse.ocl.doc.ocl2025.tests.Statistics.PackageStatistics;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;

/**
 * ResolveVisitor converts references to shared specializations
 * to references to local copies.
 */
public class StatisticsVisitor extends AbstractExtendingVisitor<@NonNull Integer, @NonNull Statistics>
{
	private @Nullable PackageStatistics currentPackage = null;
	private @Nullable ClassStatistics currentClass = null;
	private @Nullable ConstraintStatistics currentConstraint = null;
	private int currentDepth = 0;

	public StatisticsVisitor(@NonNull Statistics statistics) {
		super(statistics);
	}

	@Override
	public @NonNull Integer visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (currentPackage != null) {
			assert currentClass == null;
			this.currentClass = context.addClass(currentPackage, asClass);
			for (@NonNull Constraint asConstraint : asClass.getOwnedInvariants()) {
				asConstraint.accept(this);
			}
			this.currentClass = null;
		}
		return 0;
	}

	@Override
	public @NonNull Integer visitCollectionLiteralExp(@NonNull CollectionLiteralExp asCollectionLiteralExp) {
		int maxDepth = 0;
		for (@NonNull CollectionLiteralPart asPart : asCollectionLiteralExp.getOwnedParts()) {
			int depth = asPart.accept(this);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitCollectionItem(@NonNull CollectionItem asCollectionItem) {
		int maxDepth = asCollectionItem.getOwnedItem().accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitCollectionRange(@NonNull CollectionRange asCollectionRange) {
		int maxDepth = asCollectionRange.getOwnedFirst().accept(this);
		int depth = asCollectionRange.getOwnedLast().accept(this);
		if (depth > maxDepth) {
			maxDepth = depth;
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitConstraint(org.eclipse.ocl.pivot.@NonNull Constraint asConstraint) {
		if ("redefined_property_inherited".equals(asConstraint.getName())) {
			getClass();
		}
		assert currentConstraint == null;
		assert currentDepth == 0;
		assert currentClass != null;
		ConstraintStatistics constraintStatistics = context.addConstraint(currentClass, asConstraint);
		this.currentConstraint = constraintStatistics;
		LanguageExpression asSpecification = asConstraint.getOwnedSpecification();
		if (asSpecification != null) {
			int depth = asSpecification.accept(this);
			constraintStatistics.setDepth(depth);
		}
		this.currentConstraint = null;
		this.currentDepth = 0;
		return 0;
	}

	@Override
	public @NonNull Integer visitExpressionInOCL(@NonNull ExpressionInOCL asExpressionInOCL) {
		OCLExpression asBody = asExpressionInOCL.getOwnedBody();
		return asBody != null ? asBody.accept(this) : 0;
	}

	@Override
	public @NonNull Integer visitIfExp(@NonNull IfExp asIfExp) {
		int maxDepth = asIfExp.getOwnedCondition().accept(this);
		int depthThen = asIfExp.getOwnedThen().accept(this);
		int depthElse = asIfExp.getOwnedElse().accept(this);
		maxDepth += Math.max(depthThen, depthElse);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitIterateExp(@NonNull IterateExp asIterateExp) {
		int maxDepth = asIterateExp.getOwnedSource().accept(this);
		for (@NonNull Variable asIterator : asIterateExp.getOwnedIterators()) {
			maxDepth += asIterator.accept(this);
		}
		@NonNull Variable asResult = asIterateExp.getOwnedResult();
		maxDepth += asResult.accept(this);
		@NonNull OCLExpression asBody = asIterateExp.getOwnedBody();
		maxDepth += asBody.accept(this);
		Iteration asIteration = asIterateExp.getReferredIteration();
		context.addCallExp(asIterateExp, asIteration);
		assert currentConstraint != null;
		currentConstraint.addOperation(asIteration);
		maxDepth += asIteration.accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitIteratorExp(@NonNull IteratorExp asIteratorExp) {
		int maxDepth = asIteratorExp.getOwnedSource().accept(this);
		for (@NonNull Variable asIterator : asIteratorExp.getOwnedIterators()) {
			maxDepth += asIterator.accept(this);
		}
		@NonNull OCLExpression asBody = asIteratorExp.getOwnedBody();
		maxDepth += asBody.accept(this);
		Iteration asIteration = asIteratorExp.getReferredIteration();
		context.addCallExp(asIteratorExp, asIteration);
		assert currentConstraint != null;
		currentConstraint.addOperation(asIteration);
		maxDepth += asIteration.accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitLetExp(@NonNull LetExp asLetExp) {
		int maxDepth = asLetExp.getOwnedVariable().accept(this);
		int depth = asLetExp.getOwnedIn().accept(this);
		if (depth > maxDepth) {
			maxDepth = depth;
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitLiteralExp(@NonNull LiteralExp object) {
		return 0;
	}

	@Override
	public @NonNull Integer visitMapLiteralExp(@NonNull MapLiteralExp asMapLiteralExp) {
		int maxDepth = 0;
		for (@NonNull MapLiteralPart asPart : asMapLiteralExp.getOwnedParts()) {
			int depth = asPart.accept(this);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitMapLiteralPart(@NonNull MapLiteralPart asMapLiteralPart) {
		int maxDepth = asMapLiteralPart.getOwnedKey().accept(this);
		int depth = asMapLiteralPart.getOwnedValue().accept(this);
		if (depth > maxDepth) {
			maxDepth = depth;
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitModel(@NonNull Model asModel) {
		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : asModel.getOwnedPackages()) {
			asPackage.accept(this);
		}
		return 0;
	}

	@Override
	public @NonNull Integer visitOperationCallExp(@NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = asOperationCallExp.getReferredOperation();
		if ("allInstances".equals(asOperation.getName())) {
			getClass();		// XXX
		}
		int maxDepth = asOperationCallExp.getOwnedSource().accept(this);
		for (@NonNull OCLExpression asArgument : asOperationCallExp.getOwnedArguments()) {
			int depth = asArgument.accept(this);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		context.addCallExp(asOperationCallExp, asOperation);
		assert currentConstraint != null;
		currentConstraint.addOperation(asOperation);
		maxDepth += asOperation.accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitOperation(@NonNull Operation asOperation) {
		return 1;
	}

	@Override
	public @NonNull Integer visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		PackageStatistics savedCurrentPackage = this.currentPackage;
		this.currentPackage = context.addPackage(asPackage);
		try {
			for (org.eclipse.ocl.pivot.@NonNull Package asPackage2 : asPackage.getOwnedPackages()) {
				asPackage2.accept(this);
			}
			for (org.eclipse.ocl.pivot.@NonNull Class asClass : asPackage.getOwnedClasses()) {
				asClass.accept(this);
			}
		}
		finally {
			this.currentPackage = savedCurrentPackage;
		}
		return 0;
	}

	@Override
	public @NonNull Integer visitNavigationCallExp(@NonNull NavigationCallExp asNavigationCallExp) {
		int maxDepth = asNavigationCallExp.getOwnedSource().accept(this);
		//	Property asProperty = asPropertyCallExp.getReferredProperty();
		//	context.addProperty(asProperty);
		//	maxDepth += asProperty.accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitShadowExp(@NonNull ShadowExp asShadowExp) {
		int maxDepth = 0;
		for (@NonNull ShadowPart asPart : asShadowExp.getOwnedParts()) {
			int depth = asPart.accept(this);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitShadowPart(@NonNull ShadowPart asShadowPart) {
		int maxDepth = asShadowPart.getOwnedInit().accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitTupleLiteralExp(@NonNull TupleLiteralExp asTupleLiteralExp) {
		int maxDepth = 0;
		for (@NonNull TupleLiteralPart asPart : asTupleLiteralExp.getOwnedParts()) {
			int depth = asPart.accept(this);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitTupleLiteralPart(@NonNull TupleLiteralPart asTupleLiteralPart) {
		int maxDepth = asTupleLiteralPart.getOwnedInit().accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitTypeExp(@NonNull TypeExp object) {
		return 0;
	}

	@Override
	public @NonNull Integer visitVariable(@NonNull Variable asVariable) {
		int maxDepth = 0;
		OCLExpression asInit = asVariable.getOwnedInit();
		if (asInit != null) {
			maxDepth += asInit.accept(this);
		}
		return maxDepth;
	}

	@Override
	public @NonNull Integer visitVariableExp(@NonNull VariableExp asVariableExp) {
		int maxDepth = asVariableExp.getReferredVariable().accept(this);
		return maxDepth;
	}

	@Override
	public @NonNull Integer visiting(@NonNull Visitable visitable) {
		//	throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for StatisticsVisitor");
		System.out.println("Unsupported " + visitable.eClass().getName() + " for StatisticsVisitor");
		return 0;
	}
}
