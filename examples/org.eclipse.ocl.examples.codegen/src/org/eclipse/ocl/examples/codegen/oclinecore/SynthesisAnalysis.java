/*******************************************************************************
 * Copyright (c) 2013, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.SynthesisSchedule.Slot;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * A SynthesisAnalysis analyzes the dependencies of a Model to determine valid synthesis order for the model.
 * Invoke analyzeContents(model), then analyzeDependencies(), then getSynthesisSchedule().
 */
public class SynthesisAnalysis extends AbstractExtendingVisitor<@Nullable Object, @NonNull EnvironmentFactoryInternal>
{
	private final @NonNull SynthesisSchedule synthesisSchedule = new SynthesisSchedule();

	protected SynthesisAnalysis(@NonNull EnvironmentFactoryInternal context) {
		super(context);
	}

	protected void analyzeContents(@NonNull Model asModel) {
		synthesisSchedule.addModel(asModel);
		for (@NonNull EObject eObject : new TreeIterable(asModel, true)) {
			((Element)eObject).accept(this);
		}
	}

	protected void analyzeDependencies() {
		synthesisSchedule.analyze();
	}

	protected @NonNull Slot doNamedElement(@NonNull NamedElement asNamedElement) {
		Slot slot = synthesisSchedule.getSlot(asNamedElement);
		List<Comment> asComments = asNamedElement.getOwnedComments();
		if (asComments.size() > 0) {
			synthesisSchedule.getSlot(asNamedElement, Slot.ROLE_COMMENTS);
		}
		return slot;
	}

	protected void doParameters(@NonNull TypedElement asTypedElement, @NonNull Iterable<@NonNull Parameter> asParameters) {
		for (Parameter asParameter : asParameters) {
			synthesisSchedule.addTypeDependency(asTypedElement, PivotUtil.getType(asParameter));
		}
	}

	protected void doSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		synthesisSchedule.doSuperClasses(asClass);
	}

	protected void doTemplateableElement(@NonNull TemplateableElement asTemplateableElement) {
		synthesisSchedule.doTemplateableElement(asTemplateableElement);
	}

	protected void doTypedElement(@NonNull TypedElement asTypedElement) {
		synthesisSchedule.doTypedElement(asTypedElement);
	}

	public @NonNull SynthesisSchedule getSynthesisSchedule() {
		return synthesisSchedule;
	}

	@Override
	public @Nullable Object visitAnnotation(@NonNull Annotation asAnnotation) {
		return null;
	}

	@Override
	public @Nullable Object visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		doNamedElement(asClass);
		doSuperClasses(asClass);
		doTemplateableElement(asClass);
		synthesisSchedule.doOperations(asClass);
		synthesisSchedule.doProperties(asClass);
		return null;
	}

/*	@Override
	public @Nullable Object visitCollectionType(@NonNull CollectionType asCollectionType) {
		doNamedElement(asCollectionType);
		doSuperClasses(asCollectionType);
		doTemplateableElement(asCollectionType);
		return null;
	} */

	@Override
	public @Nullable Object visitComment(@NonNull Comment asComment) {
		return null;
	}

	@Override
	public @Nullable Object visitConstraint(@NonNull Constraint asConstraint) {
		return null;
	}

	@Override
	public @Nullable Object visitDetail(@NonNull Detail asDetail) {
		return null;
	}

/*	@Override
	public @Nullable Object visitEnumeration(@NonNull Enumeration asEnumeration) {
	//	doNamedElement(asEnumeration);
	//	doSuperClasses(asEnumeration);
	//	synthesisSchedule.doOperations(asEnumeration);
	//	synthesisSchedule.doProperties(asEnumeration);
		return super.visitEnumeration(asEnumeration);
	} */

	@Override
	public @Nullable Object visitEnumerationLiteral(@NonNull EnumerationLiteral asEnumerationLiteral) {
		doNamedElement(asEnumerationLiteral);
		return null;
	}

	@Override
	public @Nullable Object visitImport(@NonNull Import asImport) {
		return null;
	}

	@Override
	public @Nullable Object visitIteration(@NonNull Iteration asIteration) {
		doNamedElement(asIteration);
		doTemplateableElement(asIteration);
		doTypedElement(asIteration);
		doParameters(asIteration, PivotUtil.getOwnedIterators(asIteration));
		doParameters(asIteration, PivotUtil.getOwnedAccumulators(asIteration));
		doParameters(asIteration, PivotUtil.getOwnedParameters(asIteration));
		return null;
	}

	@Override
	public @Nullable Object visitLambdaType(@NonNull LambdaType asLambdaType) {
	//	System.out.println("analyze: " + NameUtil.debugSimpleName(asLambdaType) + " : " + asLambdaType);
	//	Slot slot = doNamedElement(asLambdaType);
		Slot slot = synthesisSchedule.getSlot(asLambdaType);
		synthesisSchedule.addDependency(slot, asLambdaType.getContextType());
		synthesisSchedule.addDependency(slot, asLambdaType.getResultType());
		for (@NonNull Type asParameterType : PivotUtil.getParameterTypes(asLambdaType)) {
			synthesisSchedule.addDependency(slot, asParameterType);
		}
	//	doSuperClasses(asLambdaType);
		return super.visitLambdaType(asLambdaType);
	}

/*	@Override
	public @Nullable Object visitMapType(@NonNull MapType asMapType) {
		Slot slot = doNamedElement(asMapType);
		doTemplateableElement(asMapType);
		doSuperClasses(asMapType);
		if (asMapType.getGeneric() != null) {
			synthesisSchedule.addDependency(slot, asMapType.getKeyType());
			synthesisSchedule.addDependency(slot, asMapType.getValueType());
		}
		synthesisSchedule.doOperations(asMapType);
		synthesisSchedule.doProperties(asMapType);
		return null;
	} */

	@Override
	public @Nullable Object visitModel(@NonNull Model asModel) {
		synthesisSchedule.doModel(asModel);
		return null;
	}

	@Override
	public @Nullable Object visitOperation(@NonNull Operation asOperation) {
		assert !(asOperation instanceof Iteration);
		doNamedElement(asOperation);
		doTemplateableElement(asOperation);
		doTypedElement(asOperation);
		doParameters(asOperation, PivotUtil.getOwnedParameters(asOperation));
		return null;
	}

	@Override
	public @Nullable Object visitOrphanage(@NonNull Orphanage asOrphanage) {
		return null;
	}

	@Override
	public @Nullable Object visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return null;
	}

//	@Override
//	public @Nullable Object visitParameter(@NonNull Parameter asParameter) {
	//	doTypedElement(asParameter);
//		return null;
//	}

	@Override
	public @Nullable Object visitPrecedence(@NonNull Precedence asPrecedence) {
		return null;
	}

/*	@Override
	public @Nullable Object visitPrimitiveType(final @NonNull PrimitiveType asPrimitiveType) {
	//	doNamedElement(asPrimitiveType);
	//	doSuperClasses(asPrimitiveType);
		return super.visitPrimitiveType(asPrimitiveType);
	} */

	@Override
	public @Nullable Object visitProperty(@NonNull Property asProperty) {
		org.eclipse.ocl.pivot.Class asClass = asProperty.getOwningClass();
		SynthesisSchedule.Slot slot = doNamedElement(asProperty);
		if (!(asClass instanceof TupleType)) {
			Property asOpposite = asProperty.getOpposite();
			if (asOpposite != null) {
				synthesisSchedule.addDependency(slot, asOpposite.getType());
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitTemplateBinding(@NonNull TemplateBinding asTemplateBinding) {
		return null;
	}

	@Override
	public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter asTemplateParameter) {
		return null;
	}

	@Override
	public @Nullable Object visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution asTemplateParameterSubstitution) {
		return null;
	}

	@Override
	public @Nullable Object visitTemplateSignature(@NonNull TemplateSignature asTemplateSignature) {
		return null;
	}

	@Override
	public @Nullable Object visitTupleType(@NonNull TupleType asTupleType) {
		Slot slot = doNamedElement(asTupleType);
		doSuperClasses(asTupleType);
		for (@NonNull Property asPart : PivotUtil.getOwnedProperties(asTupleType)) {
			synthesisSchedule.addDependency(slot, asPart.getType());
		}
		return null;
	}

	@Override
	public @Nullable Object visitTypedElement(@NonNull TypedElement asTypedElement) {
		return null;
	}

	@Override
	public @Nullable Object visiting(@NonNull Visitable visitable) {
		System.out.println("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		return null;
	}
}