package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public class SymbolicExpressionAnalysis extends SymbolicAnalysis
{
	protected final @NonNull ExpressionInOCL expressionInOCL;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicExpressionAnalysis(@NonNull ExpressionInOCL expressionInOCL,
			@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
		this.expressionInOCL = expressionInOCL;
	}

	@Override
	@Nullable
	public String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment, @NonNull TypedElement hypothesizedTypedElement) {
		SymbolicValue symbolicValue = hypothesizedSymbolicEvaluationEnvironment.getSymbolicValue(expressionInOCL);
		return symbolicValue.asIncompatibility();
	}


	@Override
	public @NonNull String toString() {
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		StringBuilder s = new StringBuilder();
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
