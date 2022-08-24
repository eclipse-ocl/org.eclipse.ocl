/*******************************************************************************
 * Copyright (c) 2013, 2020 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.BoxedDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.EcoreDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.UnboxedDescriptor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.iterator.IterateIteration;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * A BoxingAnalyzer performs a bottom up tree-traversal inserting:
 * <p>CGBoxExp or CGUnboxExp whereever a conversion from boxed to unboxed or vice-versa is required.
 * <p>CGCastExp whereever the apparent type is not available (e.g. Parameters passed as Object).
 * <p>CGGuardExp whereever a non-null value is required.
 * <p>
 * No attempt at optimisation is made, since this can be performed by Common SubExpression Elimination.
 * <p>
 * <h2>Simple (both boxed and unboxed)</h2>
 * Boolean, String, null, EObject (except Types)
 * <h2>Boxed/Unboxed</h2>
 * IntegerValue/Number, RealValue/Number, TypeValue/EObject, InvalidValue/Exception, CollectionValue/List
 * <h2>Boxed</h2>
 * TupleValue
 * <h2>Boxed Protocol</h2>
 * Executor/Library Iteration/Operation/PropertyCall
 * <h2>Unboxed Protocol</h2>
 * Ecore Operation/PropertyCall
 */
public class BoxingAnalyzer extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull CodeGenAnalyzer>
{
	protected final @NonNull CodeGenerator codeGenerator;

	public BoxingAnalyzer(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
		codeGenerator = analyzer.getCodeGenerator();
	}

	protected boolean hasOclVoidOperation(@NonNull OperationId operationId) {
		PivotMetamodelManager metamodelManager = codeGenerator.getEnvironmentFactory().getMetamodelManager();
		CompleteClass completeClass = metamodelManager.getCompleteClass(metamodelManager.getStandardLibrary().getOclVoidType());
		Operation memberOperation = completeClass.getOperation(operationId);
		if (memberOperation == null) {
			return false;
		}
		org.eclipse.ocl.pivot.Class owningType = memberOperation.getOwningClass();
		if (owningType == null) {
			return false;
		}
		CompleteClass owningCompleteClass = metamodelManager.getCompleteClass(owningType);
		return completeClass == owningCompleteClass;
	}

	/**
	 * Insert a CGAssertNonNullExp around cgChild.
	 */
	protected @Nullable CGValuedElement rewriteAsAssertNonNulled(@Nullable CGValuedElement cgChild) {
		if ((cgChild == null) || cgChild.isNonNull() /*|| (cgParent instanceof CGGuardExp)*/) {
			return cgChild;
		}
		CGAssertNonNullExp cgAssertExp = CGModelFactory.eINSTANCE.createCGAssertNonNullExp();
		CGUtil.wrap(cgAssertExp, cgChild);
		return cgAssertExp;
	}

	/**
	 * Return true if cgCallExp uses a safe navigation operator.
	 */
	protected boolean isSafe(@NonNull CGCallExp cgCallExp) {
		Element asElement = cgCallExp.getAst();
		return (asElement instanceof CallExp) && ((CallExp)asElement).isIsSafe();
	}

	/**
	 * Insert a CGBoxExp around cgChild.
	 */
	protected CGValuedElement rewriteAsBoxed(@Nullable CGValuedElement cgChild) {
		if ((cgChild == null) || cgChild.isBoxed()) {
			return cgChild;
		}
		CGTypeId cgTypeId = cgChild.getTypeId();
		ElementId elementId = cgTypeId.getElementId();
		if (elementId != null) {
			BoxedDescriptor boxedDescriptor = codeGenerator.getBoxedDescriptor(elementId);
			if (cgChild.isEcore()) {
				EClassifier eClassifier = cgChild.getEcoreClassifier();
				Class<?> instanceClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
				EcoreDescriptor ecoreDescriptor = boxedDescriptor.getEcoreDescriptor(codeGenerator, instanceClass);
				if (boxedDescriptor == ecoreDescriptor) {
					return cgChild;
				}
			}
			else {
				UnboxedDescriptor unboxedDescriptor = boxedDescriptor.getUnboxedDescriptor(codeGenerator);
				if (unboxedDescriptor == boxedDescriptor) {
					return cgChild;
				}
			}
		}
		CGBoxExp cgBoxExp = CGModelFactory.eINSTANCE.createCGBoxExp();
		CGUtil.wrap(cgBoxExp, cgChild);
		return cgBoxExp;
	}

	/**
	 * Insert a CGEcoreExp around cgChild.
	 */
	protected CGValuedElement rewriteAsEcore(@Nullable CGValuedElement cgChild, /*@NonNull*/ EClassifier eClassifier) {
		if ((cgChild == null) || cgChild.isEcore()) {
			return cgChild;
		}
		CGTypeId cgTypeId = cgChild.getTypeId();
		ElementId elementId = cgTypeId.getElementId();
		if (elementId != null) {
			BoxedDescriptor boxedDescriptor = codeGenerator.getBoxedDescriptor(elementId);
			//			EClassifier eClassifier = cgChild.getEcoreClassifier();
			Class<?> instanceClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
			EcoreDescriptor ecoreDescriptor = boxedDescriptor.getEcoreDescriptor(codeGenerator, instanceClass);
			if (cgChild.isUnboxed()) {
				UnboxedDescriptor unboxedDescriptor = boxedDescriptor.getUnboxedDescriptor(codeGenerator);
				if (ecoreDescriptor == unboxedDescriptor) {
					return cgChild;
				}
			}
			else {
				if (ecoreDescriptor == boxedDescriptor) {
					return cgChild;
				}
			}
		}
		CGEcoreExp cgEcoreExp = CGModelFactory.eINSTANCE.createCGEcoreExp();
		cgEcoreExp.setEClassifier(eClassifier);
		CGUtil.wrap(cgEcoreExp, cgChild);
		return cgEcoreExp;
	}

	/**
	 * Insert a CGGuardExp around cgChild.
	 */
	protected @Nullable CGValuedElement rewriteAsGuarded(@Nullable CGValuedElement cgChild, boolean isSafe, @NonNull String message) {
		if ((cgChild == null) || cgChild.isNonNull() /*|| (cgParent instanceof CGGuardExp)*/) {
			return cgChild;
		}
		CGGuardExp cgGuardExp = CGModelFactory.eINSTANCE.createCGGuardExp();
		cgGuardExp.setMessage(message);
		cgGuardExp.setSafe(isSafe);
		CGUtil.wrap(cgGuardExp, cgChild);
		return cgGuardExp;
	}

	/**
	 * Insert a CGCastExp around cgChild if required.
	 */
	protected CGValuedElement rewriteAsCast(@NonNull CGVariableExp cgChild) {
		CGVariable cgVariable = cgChild.getReferredVariable();
		CGTypeId cgRequiredTypeId = cgChild.getTypeId();
		CGTypeId cgActualTypeId = cgVariable.getTypeId();
		if (cgRequiredTypeId == cgActualTypeId) {
			return cgChild;
		}
		TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgChild);
		if (typeDescriptor.getJavaClass() == Object.class) {
			return cgChild;
		}
		TypedElement asChild = (TypedElement) cgChild.getAst();
		Type asType = asChild.getType();
		CGCastExp cgCastExp = CGModelFactory.eINSTANCE.createCGCastExp();
		CGUtil.wrap(cgCastExp, cgChild);
		cgCastExp.setAst(asChild);
		if (asType != null) {
			CGExecutorType cgExecutorType = context.createExecutorType(asType);
			cgCastExp.setExecutorType(cgExecutorType);
		}
		cgCastExp.setTypeId(codeGenerator.getAnalyzer().getTypeId(asChild.getTypeId()));
		return cgCastExp;
	}

	/**
	 * Insert a CGUnboxExp around cgChild.
	 */
	protected CGValuedElement rewriteAsUnboxed(@Nullable CGValuedElement cgChild) {
		if ((cgChild == null) || cgChild.isUnboxed()) {
			return cgChild;
		}
		CGTypeId cgTypeId = cgChild.getTypeId();
		ElementId elementId = cgTypeId.getElementId();
		if (elementId != null) {
			//			boolean maybePrimitive = codeGenerator.maybePrimitive(cgChild);
			//			TypeDescriptor boxedTypeDescriptor = codeGenerator.getTypeDescriptor(elementId, true, maybePrimitive);
			//			TypeDescriptor unboxedTypeDescriptor = codeGenerator.getTypeDescriptor(elementId, false, maybePrimitive);
			TypeDescriptor boxedDescriptor = codeGenerator.getBoxedDescriptor(elementId);
			TypeDescriptor unboxedDescriptor = boxedDescriptor.getUnboxedDescriptor(codeGenerator);
			if (cgChild.isEcore()) {
				EClassifier eClassifier = cgChild.getEcoreClassifier();
				Class<?> instanceClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
				EcoreDescriptor ecoreDescriptor = boxedDescriptor.getEcoreDescriptor(codeGenerator, instanceClass);
				if ((unboxedDescriptor == ecoreDescriptor) || (ecoreDescriptor instanceof UnboxedDescriptor)) {
					return cgChild;
				}
			}
			else {
				if (unboxedDescriptor == boxedDescriptor) {
					return cgChild;
				}
			}
		}
		CGUnboxExp cgUnboxExp = CGModelFactory.eINSTANCE.createCGUnboxExp();
		CGUtil.wrap(cgUnboxExp, cgChild);
		return cgUnboxExp;
	}

	@Override
	@Nullable
	public Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitCGBuiltInIterationCallExp(@NonNull CGBuiltInIterationCallExp cgElement) {
		super.visitCGBuiltInIterationCallExp(cgElement);
		rewriteAsBoxed(rewriteAsGuarded(cgElement.getSource(), isSafe(cgElement), "source for '" + cgElement.getReferredIteration() + "'"));
		CGValuedElement cgBody = cgElement.getBody();
		if (cgBody.isRequired()) {
			rewriteAsBoxed(rewriteAsGuarded(cgBody, false, "body for '" + cgElement.getReferredIteration() + "'"));
		}
		else {
			rewriteAsBoxed(cgBody);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGCachedOperationCallExp(@NonNull CGCachedOperationCallExp cgElement) {
		super.visitCGCachedOperationCallExp(cgElement);
		CGValuedElement cgSource = cgElement.getSource();
		rewriteAsGuarded(cgSource, isSafe(cgElement), "source for '" + cgElement.getReferredOperation() + "'");
		rewriteAsBoxed(cgSource);
		List<CGValuedElement> cgArguments = cgElement.getArguments();
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			rewriteAsBoxed(cgArguments.get(i));
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGEcoreOperation(@NonNull CGEcoreOperation cgElement) {
		super.visitCGEcoreOperation(cgElement);
		CGValuedElement body = cgElement.getBody();
		if (body != null) {
			rewriteAsEcore(body, cgElement.getEOperation().getEType());
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGEcoreOperationCallExp(@NonNull CGEcoreOperationCallExp cgElement) {
		super.visitCGEcoreOperationCallExp(cgElement);
		CGValuedElement cgSource = cgElement.getSource();
		rewriteAsGuarded(cgSource, isSafe(cgElement), "source for '" + cgElement.getReferredOperation() + "'");
		EOperation eOperation = cgElement.getEOperation();
		List<EParameter> eParameters = eOperation.getEParameters();
		rewriteAsEcore(cgSource, eOperation.getEContainingClass());
		List<CGValuedElement> cgArguments = cgElement.getArguments();
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			rewriteAsEcore(cgArguments.get(i), eParameters.get(i).getEType());
		}
		if (eOperation.isMany()) {
			rewriteAsAssertNonNulled(cgElement);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGEcoreOppositePropertyCallExp(@NonNull CGEcoreOppositePropertyCallExp cgElement) {
		super.visitCGEcoreOppositePropertyCallExp(cgElement);
		rewriteAsEcore(cgElement.getSource(), cgElement.getEStructuralFeature().getEType());
		if (cgElement.getEStructuralFeature().isMany()) {
			rewriteAsAssertNonNulled(cgElement);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGEcorePropertyCallExp(@NonNull CGEcorePropertyCallExp cgElement) {
		super.visitCGEcorePropertyCallExp(cgElement);
		rewriteAsEcore(cgElement.getSource(), cgElement.getEStructuralFeature().getEContainingClass());
		if (cgElement.getEStructuralFeature().isMany()) {
			rewriteAsAssertNonNulled(cgElement);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGExecutorOppositePropertyCallExp(@NonNull CGExecutorOppositePropertyCallExp cgElement) {
		super.visitCGExecutorOppositePropertyCallExp(cgElement);
		rewriteAsUnboxed(cgElement.getSource());
		CGTypedElement cgParent = (CGTypedElement) cgElement.getParent();
		if (cgParent != null) {
			rewriteAsBoxed(cgElement);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGExecutorPropertyCallExp(@NonNull CGExecutorPropertyCallExp cgElement) {
		super.visitCGExecutorPropertyCallExp(cgElement);
		rewriteAsUnboxed(cgElement.getSource());
		CGTypedElement cgParent = (CGTypedElement) cgElement.getParent();
		if (cgParent != null) {
			rewriteAsBoxed(cgElement);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
		for (@NonNull CGElement cgChild : cgElement.getChildren()) {
			cgChild.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIfExp(@NonNull CGIfExp cgElement) {
		super.visitCGIfExp(cgElement);
		rewriteAsGuarded(cgElement.getCondition(), false, "if condition");
		CGValuedElement thenExpression = cgElement.getThenExpression();
		CGValuedElement elseExpression = cgElement.getElseExpression();
		if ((thenExpression != null) && (elseExpression != null)) {
			boolean thenIsBoxed = thenExpression.isBoxed();
			boolean elseIsBoxed = elseExpression.isBoxed();
			if (thenIsBoxed != elseIsBoxed) {
				if (thenIsBoxed) {
					rewriteAsBoxed(cgElement.getElseExpression());
				}
				else {
					rewriteAsBoxed(cgElement.getThenExpression());
				}
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIsEqualExp(@NonNull CGIsEqualExp cgElement) {
		super.visitCGIsEqualExp(cgElement);
		CGValuedElement cgSource = cgElement.getSource();
		CGValuedElement cgArgument = cgElement.getArgument();
		boolean sourceIsBoxed = cgSource.isBoxed();
		boolean argumentIsBoxed = cgArgument.isBoxed();
		if (sourceIsBoxed != argumentIsBoxed) {				// FIXME also needs-boxing
			if (!sourceIsBoxed) {
				rewriteAsBoxed(cgSource);
			}
			if (!argumentIsBoxed) {
				rewriteAsBoxed(cgArgument);
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp cgElement) {
		super.visitCGIsEqual2Exp(cgElement);
		CGValuedElement cgSource = cgElement.getSource();
		CGValuedElement cgArgument = cgElement.getArgument();
		boolean sourceIsBoxed = cgSource.isBoxed();
		boolean argumentIsBoxed = cgArgument.isBoxed();
		if (sourceIsBoxed != argumentIsBoxed) {				// FIXME also needs-boxing
			if (!sourceIsBoxed) {
				rewriteAsBoxed(cgSource);
			}
			if (!argumentIsBoxed) {
				rewriteAsBoxed(cgArgument);
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGLibraryIterateCallExp(@NonNull CGLibraryIterateCallExp cgElement) {
		super.visitCGLibraryIterateCallExp(cgElement);
		rewriteAsGuarded(cgElement.getSource(), isSafe(cgElement), "source for '" + cgElement.getReferredIteration() + "'");
		rewriteAsBoxed(cgElement.getSource());
		LibraryIteration libraryIteration = cgElement.getLibraryIteration();
		if (!(libraryIteration instanceof IterateIteration)) {
			rewriteAsBoxed(cgElement.getBody());
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGLibraryIterationCallExp(@NonNull CGLibraryIterationCallExp cgElement) {
		super.visitCGLibraryIterationCallExp(cgElement);
		rewriteAsGuarded(cgElement.getSource(), isSafe(cgElement), "source for '" + cgElement.getReferredIteration() + "'");
		rewriteAsBoxed(cgElement.getSource());
		LibraryIteration libraryIteration = cgElement.getLibraryIteration();
		if (!(libraryIteration instanceof IterateIteration)) {
			rewriteAsBoxed(cgElement.getBody());
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGLibraryOperation(@NonNull CGLibraryOperation cgLibraryOperation) {
		super.visitCGLibraryOperation(cgLibraryOperation);
		rewriteAsBoxed(cgLibraryOperation.getBody());
		return null;
	}

	@Override
	public @Nullable Object visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp cgElement) {
		super.visitCGLibraryOperationCallExp(cgElement);
		Operation referredOperation = cgElement.getReferredOperation();
		OperationId operationId = referredOperation.getOperationId();
		boolean sourceMayBeNull = hasOclVoidOperation(operationId);
		CGValuedElement cgSource = cgElement.getSource();
		if (!sourceMayBeNull) {
			if (cgSource.isNull()) {
//				CGInvalid cgInvalid = context.getInvalid("null value1 for source parameter");
				CGInvalid cgInvalid = context.getInvalid("''" + referredOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
				CGConstantExp cgLiteralExp = context.createCGConstantExp(CGUtil.getAST(cgElement), cgInvalid);
				CGUtil.replace(cgElement, cgLiteralExp);
				return null;
			}
		}
		List<Parameter> ownedParameters = referredOperation.getOwnedParameters();
		List<CGValuedElement> cgArguments = cgElement.getArguments();
		int iMax = cgArguments.size();
		if (!referredOperation.isIsValidating()) {
			for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
				CGValuedElement cgArgument = cgArguments.get(i);
				Parameter asParameter = ownedParameters.get(i);
				if (asParameter.isIsRequired()) {
					if (cgArgument.isNull()) {
	//					CGInvalid cgInvalid = context.getInvalid("null value2 for " + asParameter.getName() + " parameter");
						CGInvalid cgInvalid = context.getInvalid("''" + asParameter.getType().getName() + "'' rather than ''OclVoid'' value required");
						CGConstantExp cgLiteralExp = context.createCGConstantExp(CGUtil.getAST(cgElement), cgInvalid);
						CGUtil.replace(cgElement, cgLiteralExp);
						return null;
					}
				}
			}
		}
		rewriteAsBoxed(cgSource);
		if (!sourceMayBeNull && !cgSource.isNonNull()) {
//			rewriteAsGuarded(cgSource, false, "value3 for source parameter");
			rewriteAsGuarded(cgSource, false, "''" + referredOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
		}
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
			rewriteAsBoxed(cgArgument);
			Parameter asParameter = ownedParameters.get(i);
			if (asParameter.isIsRequired() && !cgArgument.isNonNull()) {
//				rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
				rewriteAsGuarded(cgArgument, false, "''" + asParameter.getType().getName() + "'' rather than ''OclVoid'' value required");
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGNativeOperationCallExp(@NonNull CGNativeOperationCallExp cgElement) {
		super.visitCGNativeOperationCallExp(cgElement);
		CGValuedElement cgSource = cgElement.getSource();
		rewriteAsGuarded(cgSource, isSafe(cgElement), "source for '" + cgElement.getReferredOperation() + "'");
		rewriteAsUnboxed(cgSource);
		List<CGValuedElement> cgArguments = cgElement.getArguments();
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			rewriteAsUnboxed(cgArguments.get(i));
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGNavigationCallExp(@NonNull CGNavigationCallExp cgElement) {
		super.visitCGNavigationCallExp(cgElement);
		Property referredProperty = cgElement.getReferredProperty();
		String referredPropertyName;
		if (referredProperty == null) {
			referredPropertyName = "unknown";
		}
		else if (referredProperty.eContainer() instanceof TupleType) {
			referredPropertyName = referredProperty.getName();
		}
		else {
			PropertyId referredPropertyId = referredProperty.getPropertyId();
			referredPropertyName = ValueUtil.getElementIdName(referredPropertyId);
		}
		rewriteAsGuarded(cgElement.getSource(), isSafe(cgElement), "source for '" + referredPropertyName + "'");
		return null;
	}

	@Override
	public @Nullable Object visitCGOperation(@NonNull CGOperation cgElement) {
		super.visitCGOperation(cgElement);
		//		if ("isAttribute".equals(cgElement.getName())) {
		//			System.out.println("visitCGOperation for " + cgElement.getAst().toString());
		//		}
		if (cgElement.isRequired()) {
			CGValuedElement body = cgElement.getBody();
			if (body != null) {
				rewriteAsGuarded(body, false, "body for '" + cgElement.getAst() + "'");
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGProperty(@NonNull CGProperty cgElement) {
		super.visitCGProperty(cgElement);
		if (cgElement.isRequired()) {
			CGValuedElement body = cgElement.getBody();
			if (body != null) {
				rewriteAsGuarded(body, false, "body for '" + cgElement.getAst() + "'");
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGShadowPart(@NonNull CGShadowPart cgShadowPart) {
		rewriteAsUnboxed(cgShadowPart.getInit());
		return super.visitCGShadowPart(cgShadowPart);
	}

	@Override
	public @Nullable Object visitCGTypeExp(@NonNull CGTypeExp cgTypeExp) {
		super.visitCGTypeExp(cgTypeExp);
	//	CGExecutorType executorType = cgTypeExp.getExecutorType();
	//	rewriteAsCast(cgTypeExp);
		return null;
	}

	@Override
	public @Nullable Object visitCGVariableExp(@NonNull CGVariableExp cgElement) {
		super.visitCGVariableExp(cgElement);
		CGVariable referredVariable = cgElement.getReferredVariable();
		if (referredVariable instanceof CGIterator) {
			CGIterator cgIterator = (CGIterator)referredVariable;
			EObject cgOperation = cgIterator.eContainer();
			if ((cgOperation instanceof CGIterationCallExp) && !(cgOperation instanceof CGBuiltInIterationCallExp)) {
				rewriteAsCast(cgElement);
			}
		}
		else if (referredVariable instanceof CGParameter) {
			CGParameter cgParameter = (CGParameter)referredVariable;
			EObject cgOperation = cgParameter.eContainer();
			if (cgOperation instanceof CGLibraryOperation) {
				rewriteAsCast(cgElement);
			}
		}
		return null;
	}
}
