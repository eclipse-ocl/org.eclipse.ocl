/*******************************************************************************
 * Copyright (c) 2011,2013 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.essentialocl.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainMetaclass;
import org.eclipse.ocl.examples.domain.elements.DomainType;
import org.eclipse.ocl.examples.domain.utilities.DomainUtil;
import org.eclipse.ocl.examples.pivot.CollectionType;
import org.eclipse.ocl.examples.pivot.Iteration;
import org.eclipse.ocl.examples.pivot.LambdaType;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.ParameterableElement;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.lookup.AutoIPivotLookupEnvironment;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.scoping.EnvironmentView;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.InvocationExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.NavigatingArgCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.NavigationRole;

public class OperationFilter extends AbstractOperationFilter<Operation>
{
	protected final @NonNull List<NavigatingArgCS> csArguments;
	protected final int iterators;
	protected final int accumulators;
	protected final int expressions;
	
	public OperationFilter(@Nullable Type sourceType, @NonNull InvocationExpCS csNavigatingExp) {
		super(sourceType);
		int accumulators = 0;
		int iterators = 0;
		int expressions = 0;
		@SuppressWarnings("null") @NonNull List<NavigatingArgCS> csArguments = csNavigatingExp.getArgument();
		this.csArguments = csArguments;
		for (NavigatingArgCS csNavigatingArg : csArguments) {
			if (csNavigatingArg.getRole() == NavigationRole.ITERATOR) {
				iterators++;
			}
			else if (csNavigatingArg.getRole() == NavigationRole.ACCUMULATOR) {
				accumulators++;
			}
			else if (csNavigatingArg.getRole() == NavigationRole.EXPRESSION) {
				expressions++;
			}
		}
		this.iterators = iterators;
		this.accumulators = accumulators;
		this.expressions = expressions;
	}

	@Override
	public int compareMatches(@NonNull MetaModelManager metaModelManager, @NonNull Object match1, @Nullable Map<TemplateParameter, ParameterableElement> referenceBindings,
			@NonNull Object match2, @Nullable Map<TemplateParameter, ParameterableElement> candidateBindings) {
		@NonNull Operation reference = (Operation) match1;
		@NonNull Operation candidate = (Operation) match2;
		Type referenceType = PivotUtil.getType(PivotUtil.getOwningType(reference));
		Type candidateType = PivotUtil.getType(PivotUtil.getOwningType(candidate));
		Type specializedReferenceType = metaModelManager.getSpecializedType(referenceType, referenceBindings);
		Type specializedCandidateType = metaModelManager.getSpecializedType(candidateType, candidateBindings);
		if ((reference instanceof Iteration) && (candidate instanceof Iteration)) {
			int iteratorCountDelta = ((Iteration)candidate).getOwnedIterator().size() - ((Iteration)reference).getOwnedIterator().size();
			if (iteratorCountDelta != 0) {
				return iteratorCountDelta;
			}
			if (referenceType != candidateType) {
				if (metaModelManager.conformsTo(specializedReferenceType, specializedCandidateType, null)) {
					return 1;
				}
				else if (metaModelManager.conformsTo(specializedCandidateType, specializedReferenceType, null)) {
					return -1;
				}
			}
		}
		int referenceConversions = 0;
		int candidateConversions = 0;
		DomainType comparedSourceType = sourceType;
		if (comparedSourceType instanceof DomainMetaclass) {
			comparedSourceType = ((DomainMetaclass)comparedSourceType).getInstanceType();
		}
		if (comparedSourceType != specializedReferenceType) {
			referenceConversions++;
		}
		if (comparedSourceType != specializedCandidateType) {
			candidateConversions++;
		}
		List<Parameter> candidateParameters = candidate.getOwnedParameter();
		List<Parameter> referenceParameters = reference.getOwnedParameter();
		for (int i = 0; i < candidateParameters.size(); i++) {
			NavigatingArgCS csArgument = csArguments.get(i);
			OCLExpression pivotArgument = PivotUtil.getPivot(OCLExpression.class, csArgument);
			if (pivotArgument == null) {
				return 0;
			}
			Type argumentType = pivotArgument.getType();
			Parameter referenceParameter = referenceParameters.get(i);
			Parameter candidateParameter = candidateParameters.get(i);
			if ((referenceParameter == null) || (candidateParameter == null)) {					// Doesn't happen (just a supurious NPE guard)
				referenceConversions = Integer.MIN_VALUE;
				candidateConversions = Integer.MIN_VALUE;
			}
			else {
				referenceType = PivotUtil.getType(DomainUtil.nonNullModel(referenceParameter.getType()));
				candidateType = PivotUtil.getType(DomainUtil.nonNullModel(candidateParameter.getType()));
				specializedReferenceType = metaModelManager.getSpecializedType(referenceType, referenceBindings);
				specializedCandidateType = metaModelManager.getSpecializedType(candidateType, candidateBindings);
				if (argumentType != specializedReferenceType) {
					referenceConversions++;
				}
				if (argumentType != specializedCandidateType) {
					candidateConversions++;
				}
			}
		}
		if (candidateConversions != referenceConversions) {
			return candidateConversions - referenceConversions;
		}
		int verdict = metaModelManager.compareOperationMatches(reference, referenceBindings, candidate, candidateBindings);
		return verdict;
	}

	protected @Nullable OCLExpression getExpressionArgument(int index) {
		int expIndex = 0;
		for (NavigatingArgCS csNavigatingArg : csArguments) {
			if (csNavigatingArg.getRole() == NavigationRole.EXPRESSION) {
				if (expIndex == index) {
					return PivotUtil.getPivot(OCLExpression.class, csNavigatingArg);
				}
				expIndex++;
			}
		}
		return null;
	}

	protected @Nullable Map<TemplateParameter, ParameterableElement> getIterationBindings(@NonNull MetaModelManager metaModelManager, @NonNull Iteration candidateIteration) {
		Type sourceType = this.sourceType;
		if (!(sourceType instanceof CollectionType) && (candidateIteration.getOwningType() instanceof CollectionType) && (sourceType != null)) {
			sourceType = metaModelManager.getSetType(sourceType, null, null);		// Implicit oclAsSet()
		}
		if (!(sourceType instanceof CollectionType)) {			// May be InvalidType
			return null;
		}
		HashMap<TemplateParameter, ParameterableElement> bindings = new HashMap<TemplateParameter, ParameterableElement>();
		bindings.put(candidateIteration.getOwningType().getOwnedTemplateSignature().getOwnedParameter().get(0), ((CollectionType)sourceType).getElementType());
		PivotUtil.getAllTemplateParameterSubstitutions(bindings, sourceType);
		TemplateSignature templateSignature = candidateIteration.getOwnedTemplateSignature();
		if (templateSignature != null) {
			List<TemplateParameter> templateParameters = templateSignature.getOwnedParameter();
			int accIndex = 0;
			for (NavigatingArgCS csArgument : csArguments) {
				if (csArgument.getRole() == NavigationRole.ACCUMULATOR) {
					if (accIndex < templateParameters.size()) {
						Variable argument = PivotUtil.getPivot(Variable.class, csArgument);
						if (argument != null) {
							Type argumentType = argument.getType();
							TemplateParameter accParameter = templateParameters.get(accIndex);
							bindings.put(accParameter, argumentType);
						}
					}
					accIndex++;
				}
			}
		}
		return bindings;
	}

	@Override
	protected @Nullable Map<TemplateParameter, ParameterableElement> getOperationBindings(@NonNull MetaModelManager metaModelManager, @NonNull Operation candidateOperation) {
		Type sourceType = this.sourceType;
		Map<TemplateParameter, ParameterableElement> bindings = null;
		Type containingType = candidateOperation.getOwningType();
		if ((containingType instanceof CollectionType) && (sourceType != null)) {
			if (!(sourceType instanceof CollectionType)) {
				sourceType = metaModelManager.getSetType(sourceType, null, null);		// Implicit oclAsSet()
			}			
			Type elementType;
			if (sourceType instanceof CollectionType) {
				elementType = ((CollectionType)sourceType).getElementType();
			}
			else {
				elementType = metaModelManager.getOclInvalidType();
			}
			bindings = new HashMap<TemplateParameter, ParameterableElement>();
			bindings.put(containingType.getOwnedTemplateSignature().getOwnedParameter().get(0), elementType);
		}			
		bindings = PivotUtil.getAllTemplateParameterSubstitutions(bindings, sourceType);
		TemplateSignature templateSignature = candidateOperation.getOwnedTemplateSignature();
		if (templateSignature != null) {
			for (TemplateParameter templateParameter : templateSignature.getOwnedParameter()) {
				if (bindings == null) {
					bindings = new HashMap<TemplateParameter, ParameterableElement>();
				}
				bindings.put(templateParameter, null);
			}
		}
		return bindings;
	}

	@Override
	protected void installBindings(@NonNull EnvironmentView environmentView, @NonNull Object object,
			@Nullable Map<TemplateParameter, ParameterableElement> bindings) {
		List<Parameter> parameters = ((Operation)object).getOwnedParameter();
		int iMax = parameters.size();
		if (iMax > 0) {
			for (int i = 0; i < iMax; i++) {
				Parameter parameter = parameters.get(i);
				OCLExpression argument = getExpressionArgument(i);
				if (argument != null) {
					Type parameterType = parameter.getType();
					if (parameterType instanceof LambdaType) {
						PivotUtil.getAllTemplateParameterSubstitutions(bindings, argument.getType(), (LambdaType) parameterType);
					}
				}
			}
		}
		super.installBindings(environmentView, object, bindings);
	}

	public boolean matches(@NonNull EnvironmentView environmentView, @NonNull Object object) {
		
		MetaModelManager mmManager = environmentView.getMetaModelManager();
		Map<TemplateParameter, ParameterableElement> bindings  = getBindings(mmManager, object);
		if (bindings != null) {
			installBindings(environmentView, object, bindings);
			return true;
		}
		return false;
	}

	public boolean matches(@NonNull AutoIPivotLookupEnvironment<Operation> lookupResult,
			@NonNull Operation object) {
	
		MetaModelManager mmManager = lookupResult.getMetaModelManager();
		Map<TemplateParameter, ParameterableElement> bindings  = getBindings(mmManager, object);
		if (bindings != null) {
			installBindings(lookupResult, object, bindings);
			return true;
		}
		return false;
	}
	
	private Map<TemplateParameter, ParameterableElement> getBindings(@NonNull MetaModelManager metaModelManager, 
		@NonNull Object object) {
		if (object instanceof Iteration) {
			Iteration candidateIteration = (Iteration)object;
			int iteratorCount = candidateIteration.getOwnedIterator().size();
			if ((0 < iterators) && (iteratorCount != iterators)) {
				return null;
			}
			int accumulatorCount = candidateIteration.getOwnedAccumulator().size();
			if (accumulatorCount != accumulators) {
				return null;
			}
			Map<TemplateParameter, ParameterableElement> bindings = getIterationBindings(metaModelManager, candidateIteration);			
			return bindings == null ? new HashMap<TemplateParameter, ParameterableElement>() : bindings;
		}
		else if (object instanceof Operation) {
			if (iterators > 0) {
				return null;
			}
			if (accumulators > 0) {
				return null;
			}
			Operation candidateOperation = (Operation)object;
			List<Parameter> candidateParameters = candidateOperation.getOwnedParameter();
			if (expressions != candidateParameters.size()) {
				return null;
			}
			Map<TemplateParameter, ParameterableElement> bindings = getOperationBindings(metaModelManager, candidateOperation);
			for (int i = 0; i < expressions; i++) {
				Parameter candidateParameter = candidateParameters.get(i);
				if (candidateParameter != null) {
					NavigatingArgCS csExpression = csArguments.get(i);
					OCLExpression expression = PivotUtil.getPivot(OCLExpression.class, csExpression);
					Type candidateType = PivotUtil.getType(candidateParameter);
					Type expressionType = PivotUtil.getType(expression);
					if ((expressionType == null) || (candidateType == null)) {
						return null;
					}
					if (!metaModelManager.conformsTo(expressionType, candidateType, bindings)) {
						return null;
					}
				}
			}			
			return bindings == null ? new HashMap<TemplateParameter, ParameterableElement>() : bindings;
		}
		else {
			return null;
		}
	}
}
