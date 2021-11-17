/*******************************************************************************
 * Copyright (c) 2016, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.context.OperationContext;
import org.eclipse.ocl.pivot.internal.context.PropertyContext;

/**
 * ExpressionInOCLUsage provides functionality to assist in the use of an ExpressionInOCL with respect to
 * its distinctive invariant, precondition, postcondition, body, initializer, ... usagee
 *
 * @since 1.17
 */
public abstract class ExpressionInOCLUsage
{
	public static @NonNull ExpressionInOCLUsage createUsage(@NonNull LanguageExpression expressionInOCL) {
		EObject eContainer = expressionInOCL.eContainer();
		EObject eContainmentFeature = expressionInOCL.eContainmentFeature();
		if (eContainer instanceof Constraint) {
			EObject eContainerContainer = eContainer.eContainer();
			EObject eContainerContainmentFeature = eContainer.eContainmentFeature();
			if (eContainerContainer instanceof org.eclipse.ocl.pivot.Class) {
				if (eContainerContainmentFeature == PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
					return new ClassInvariant((org.eclipse.ocl.pivot.Class)eContainerContainer, expressionInOCL);
				}
			}
			else if (eContainerContainer instanceof Operation) {
				if (eContainerContainmentFeature == PivotPackage.Literals.OPERATION__OWNED_POSTCONDITIONS) {
					return new OperationPostcondition((Operation)eContainerContainer, expressionInOCL);
				}
				else if (eContainerContainmentFeature == PivotPackage.Literals.OPERATION__OWNED_PRECONDITIONS) {
					return new OperationPrecondition((Operation)eContainerContainer, expressionInOCL);
				}
			}
		}
		else if (eContainer instanceof Operation) {
			if (eContainmentFeature == PivotPackage.Literals.OPERATION__BODY_EXPRESSION) {
				return new OperationBodyExpression((Operation)eContainer, expressionInOCL);
			}
		}
		else if (eContainer instanceof Property) {
			if (eContainmentFeature == PivotPackage.Literals.PROPERTY__OWNED_EXPRESSION) {
				return new PropertyBodyExpression((Property)eContainer, expressionInOCL);
			}
			else if (eContainmentFeature == PivotPackage.Literals.PROPERTY__DEFAULT_VALUE) {
				return new PropertyBodyExpression((Property)eContainer, expressionInOCL);
			}
		}
		else if (eContainer instanceof Slot) {
			if (eContainmentFeature == PivotPackage.Literals.SLOT__OWNED_VALUES) {
				return new SlotBodyExpression((Slot)eContainer, expressionInOCL);
			}
		}
		else if ((expressionInOCL instanceof ExpressionInOCL) && (eContainer == null)) {		// JUnit test config
			return new OrphanExpression((ExpressionInOCL)expressionInOCL);
		}
	//	throw new UnsupportedOperationException();
		return new FallBack(expressionInOCL);		//Never used
	}

	/**
	 * @since 1.17
	 */
	public static boolean isInvariant(@NonNull LanguageExpression expressionInOCL) {
		EObject eContainer = expressionInOCL.eContainer();
		if (!(eContainer instanceof Constraint)) {
			return false;
		}
		EObject eContainerContainer = eContainer.eContainer();
		return eContainerContainer instanceof Type;
	}

	protected static class ClassInvariant extends ExpressionInOCLUsage
	{
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected ClassInvariant(org.eclipse.ocl.pivot.@NonNull Class contextClass, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextClass = contextClass;
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new ClassContext(environmentFactory, null, contextClass, null);
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}

		@Override
		public boolean isClassInvariant() {
			return false;
		}
	}

	/**
	 * FallBack should never occur, but we'll try and find a type and use it.
	 */
	protected static class FallBack extends ExpressionInOCLUsage
	{
		protected FallBack(@NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			for (EObject eObject = expressionInOCL; eObject != null; eObject = eObject.eContainer()) {
				if ((eObject instanceof org.eclipse.ocl.pivot.Class) && (((org.eclipse.ocl.pivot.Class)eObject).getOwningPackage() != null)) {	// StateMachines etc do not have Packages
					return new ClassContext(environmentFactory, null, (org.eclipse.ocl.pivot.Class)eObject, null);
				}
			}
			return null;
		}

		@Override
		public @Nullable Type getContextType() {
			return PivotUtil.getContainingType(expressionInOCL);
		}
	}

	/**
	 * OrphanExpression is used by simple JUnit tests.
	 */
	protected static class OrphanExpression extends ExpressionInOCLUsage
	{
		protected OrphanExpression(@NonNull ExpressionInOCL expressionInOCL) {
			super(expressionInOCL);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			VariableDeclaration ownedContext = PivotUtil.getOwnedContext((ExpressionInOCL)expressionInOCL);
			org.eclipse.ocl.pivot.Class type = PivotUtil.getClass(ownedContext);
			return new ClassContext(environmentFactory, null, type, null);
		}

		@Override
		public @Nullable Type getContextType() {
			return PivotUtil.getContainingType(expressionInOCL);
		}
	}

	protected static class OperationBodyExpression extends ExpressionInOCLUsage
	{
		protected final @NonNull Operation contextOperation;
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected OperationBodyExpression(@NonNull Operation contextOperation, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextOperation = contextOperation;
			this.contextClass = PivotUtil.getOwningClass(contextOperation);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new OperationContext(environmentFactory, null, contextOperation, null);
		}

		@Override
		public @NonNull Operation getContextOperation() {
			return contextOperation;
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}
	}

	protected static class OperationPostcondition extends ExpressionInOCLUsage
	{
		protected final @NonNull Operation contextOperation;
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected OperationPostcondition(@NonNull Operation contextOperation, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextOperation = contextOperation;
			this.contextClass = PivotUtil.getOwningClass(contextOperation);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new OperationContext(environmentFactory, null, contextOperation, PivotConstants.RESULT_NAME);
		}

		@Override
		public @NonNull Operation getContextOperation() {
			return contextOperation;
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}
	}

	protected static class OperationPrecondition extends ExpressionInOCLUsage
	{
		protected final @NonNull Operation contextOperation;
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected OperationPrecondition(@NonNull Operation contextOperation, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextOperation = contextOperation;
			this.contextClass = PivotUtil.getOwningClass(contextOperation);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new OperationContext(environmentFactory, null, contextOperation, null);
		}

		@Override
		public @NonNull Operation getContextOperation() {
			return contextOperation;
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}
	}

	protected static class PropertyBodyExpression extends ExpressionInOCLUsage
	{
		protected final @NonNull Property contextProperty;
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected PropertyBodyExpression(@NonNull Property contextProperty, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextProperty = contextProperty;
			this.contextClass = PivotUtil.getOwningClass(contextProperty);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new PropertyContext(environmentFactory, null, contextProperty);
		}

		@Override
		public @NonNull Property getContextProperty() {
			return contextProperty;
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}
	}

	protected static class SlotBodyExpression extends ExpressionInOCLUsage
	{
		protected final @NonNull Slot contextSlot;
		protected final @NonNull Property contextProperty;
		protected final org.eclipse.ocl.pivot.@NonNull Class contextClass;

		protected SlotBodyExpression(@NonNull Slot contextSlot, @NonNull LanguageExpression expressionInOCL) {
			super(expressionInOCL);
			this.contextSlot = contextSlot;
			this.contextProperty = PivotUtil.getDefiningProperty(contextSlot);
			this.contextClass = PivotUtil.getOwningClass(contextProperty);
		}

		@Override
		public @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory) {
			return new ClassContext(environmentFactory, null, contextClass, null);
		}

		@Override
		public @NonNull Property getContextProperty() {
			return contextProperty;
		}

		@Override
		public @NonNull Slot getContextSlot() {
			return contextSlot;
		}

		@Override
		public @NonNull Type getContextType() {
			return contextClass;
		}
	}

	protected final @NonNull LanguageExpression expressionInOCL;

	protected ExpressionInOCLUsage(@NonNull LanguageExpression expressionInOCL) {
		this.expressionInOCL = expressionInOCL;
	}

	public abstract @Nullable ParserContext createParserContext(@NonNull EnvironmentFactory environmentFactory);

	public @Nullable Operation getContextOperation() {
		return null;
	}

	public @Nullable Property getContextProperty() {
		return null;
	}

	public @Nullable Slot getContextSlot() {
		return null;
	}

	public abstract @Nullable Type getContextType();

	public boolean isClassInvariant() {
		return false;
	}
}