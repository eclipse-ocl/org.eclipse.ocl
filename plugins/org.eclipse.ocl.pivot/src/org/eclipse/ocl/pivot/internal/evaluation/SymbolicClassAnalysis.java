package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public class SymbolicClassAnalysis extends SymbolicAnalysis
{
	protected final org.eclipse.ocl.pivot.@NonNull Class selfClass;
	private @Nullable Iterable<@NonNull ExpressionInOCL> invariantBodies = null;
	private @Nullable String incompatibility;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicClassAnalysis(org.eclipse.ocl.pivot.@NonNull Class selfClass, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
		this.selfClass = selfClass;
//		this.expressionsInOCL = expressionsInOCL;
	}

	public void analyzeInvariants() {
		assert incompatibility == null;
		SymbolicValue selfVariable = new SymbolicUnknownValue("self", selfClass.getTypeId(), false, false);
		assert invariantBodies == null;
		List<@NonNull ExpressionInOCL> invariantBodies2 = new ArrayList<>();
		this.invariantBodies = invariantBodies2;
		for (@NonNull Constraint invariant : PivotUtil.getOwnedInvariants(selfClass)) {
			LanguageExpression ownedSpecification = invariant.getOwnedSpecification();
			if (ownedSpecification != null) {
				try {
					invariantBodies2.add(environmentFactory.parseSpecification(ownedSpecification));
				}
				catch (ParserException e) {
					incompatibility = e.toString();
					return;
				}
			}
		}
		analyzeInvariants(selfClass, invariantBodies2, selfVariable);
	}

	public @NonNull SymbolicAnalysis getSymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL) {
		return new SymbolicExpressionAnalysis(expressionInOCL, environmentFactory, getExecutor().getModelManager());
	}

	@Override
	public @Nullable String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment, @NonNull TypedElement hypothesizedTypedElement) {
		if (incompatibility != null) {
			return incompatibility;
		}
		Iterable<@NonNull ExpressionInOCL> invariantBodies2 = invariantBodies;
		if (invariantBodies2 != null) {
			ExpressionInOCL hypothesizedExpressionInOCL = PivotUtil.getContainingExpressionInOCL(hypothesizedTypedElement);
			for (@NonNull ExpressionInOCL invariantBody : invariantBodies2) {
				SymbolicValue symbolicValue = hypothesizedSymbolicEvaluationEnvironment.getSymbolicValue(invariantBody);
				String incompatibility = symbolicValue.asIncompatibility();
				if (incompatibility != null) {
					return incompatibility;
				}
				else if (invariantBody != hypothesizedExpressionInOCL) {
					if (symbolicValue.isFalse()) {
						return "Incompatible " + ((NamedElement)invariantBody.eContainer()).getName();
					}
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull String toString() {
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		StringBuilder s = new StringBuilder();
		for (@NonNull Constraint invariant : PivotUtil.getOwnedInvariants(selfClass)) {
			LanguageExpression ownedSpecification = invariant.getOwnedSpecification();
			if (ownedSpecification != null) {
				try {
					ExpressionInOCL expressionInOCL = environmentFactory.parseSpecification(ownedSpecification);
					for (EObject eObject : new TreeIterable(expressionInOCL, true)) {
						s.append("\n  ");
						for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
							s.append("  ");
						}
						s.append(eObject.eClass().getName());
						s.append(" : ");
						s.append(eObject.toString());
						s.append("\n  ");
						for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
							s.append("  ");
						}
						s.append("  => ");
						SymbolicValue symbolicValue = eObject instanceof TypedElement ? evaluationEnvironment.basicGetSymbolicValue((TypedElement)eObject) : null;
						if (symbolicValue == null) {
							s.append("not-computed");
						}
						else {
							s.append(symbolicValue.getClass().getSimpleName());
							s.append(" : ");
							s.append(symbolicValue);
						}
					}
				} catch (ParserException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
			}
		}
	/*	List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
		if (keys.size() > 1) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
		s.append("\t" + keys.size() + " cses");
		for (@NonNull CSEElement key : keys) {
			Object value = cseElement2symbolicValue.get(key);
			s.append("\n\t\t" + key + " => " + value);
		} */
		List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = getHypothesizedEvaluationEnvironments();
		if (hypothesizedEvaluationEnvironments != null) {
			for (HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironments : hypothesizedEvaluationEnvironments) {
				StringUtil.appendIndentation(s, 1);
				s.append("{ ");
			//	StringUtil.appendIndentation(s, 2);
				hypothesizedSymbolicEvaluationEnvironments.toString(s, 1);
				StringUtil.appendIndentation(s, 1);
				s.append("}");
			}
		}
		return s.toString();
	}
}
