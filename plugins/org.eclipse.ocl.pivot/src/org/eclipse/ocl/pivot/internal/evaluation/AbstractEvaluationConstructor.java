package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.IdResolver;

/**
 * @since 1.3
 */
public abstract class AbstractEvaluationConstructor implements Evaluation.Constructor
{
	protected final IdResolver.@NonNull IdResolverExtension idResolver;

	/**
	 * Map from invocation hashCode to one or more evaluations with that hashCode. Single map entries use the
	 * Evaluation directly as the entry. Colliding entries use a List<@NonNull Evaluation> for the collisions.
	 * <br>
	 * This map is used to inhibit repeated computations.
	 */
	private final @NonNull Map<@NonNull Integer, @NonNull Object> hashCode2evaluations = new HashMap<@NonNull Integer, @NonNull Object>();

	//	protected final boolean debugEvaluations = false;//AbstractTransformer.INVOCATIONS.isActive();

	protected AbstractEvaluationConstructor(@NonNull IdResolver idResolver) {
		this.idResolver = (IdResolver.IdResolverExtension)idResolver;
	}

	@Override
	public @Nullable Object getUniqueEvaluationResult(@Nullable Object @NonNull ... sourceAndArgumentValues) {
		int hashCode = 0;
		for (@Nullable Object sourceAndArgumentValue : sourceAndArgumentValues) {
			hashCode = 3 * hashCode + idResolver.oclHashCode(sourceAndArgumentValue);
		}
		synchronized (hashCode2evaluations) {
			Object zeroOrMoreEvaluations = hashCode2evaluations.get(hashCode);
			Evaluation oneEvaluation = null;
			if (zeroOrMoreEvaluations instanceof Evaluation) {
				oneEvaluation = (Evaluation)zeroOrMoreEvaluations;
				if (oneEvaluation.isEqual(idResolver, sourceAndArgumentValues)) {
					//					if (debugEvaluations) {
					//						AbstractTransformer.INVOCATIONS.println("getUniqueEvaluation old:" + oneEvaluation);
					//					}
					return oneEvaluation;
				}
			}
			else if (zeroOrMoreEvaluations instanceof List<?>) {
				@SuppressWarnings("unchecked")@NonNull List<@NonNull Evaluation> zeroOrMoreEvaluations2 = (List<@NonNull Evaluation>)zeroOrMoreEvaluations;
				for (@NonNull Evaluation aEvaluation : zeroOrMoreEvaluations2) {
					if (aEvaluation.isEqual(idResolver, sourceAndArgumentValues)) {
						//						if (debugEvaluations) {
						//							AbstractTransformer.INVOCATIONS.println("getUniqueEvaluation old:" + aEvaluation);
						//						}
						return aEvaluation;
					}
				}
			}
		}
		//
		//	Must resynchronize after newInstance creation and execution in case the execution is recursive.
		//
		Evaluation theEvaluation = newInstance(sourceAndArgumentValues);
		synchronized (hashCode2evaluations) {
			Object zeroOrMoreEvaluations = hashCode2evaluations.get(hashCode);
			if (zeroOrMoreEvaluations == null) {
				hashCode2evaluations.put(hashCode, theEvaluation);
			}
			else if (zeroOrMoreEvaluations instanceof Evaluation) {
				Evaluation oneEvaluation = (Evaluation)zeroOrMoreEvaluations;
				if (oneEvaluation.isEqual(idResolver, sourceAndArgumentValues)) {
					//					if (debugEvaluations) {
					//						AbstractTransformer.INVOCATIONS.println("getUniqueEvaluation old:" + oneEvaluation);
					//					}
					return oneEvaluation;
				}
				List<@NonNull Evaluation> twoOrMoreEvaluations = new ArrayList<@NonNull Evaluation>(4);
				twoOrMoreEvaluations.add(oneEvaluation);
				twoOrMoreEvaluations.add(theEvaluation);
				hashCode2evaluations.put(hashCode, twoOrMoreEvaluations);
			}
			else if (zeroOrMoreEvaluations instanceof List<?>) {
				@SuppressWarnings("unchecked")@NonNull List<@NonNull Evaluation> twoOrMoreEvaluations = (List<@NonNull Evaluation>)zeroOrMoreEvaluations;
				for (@NonNull Evaluation aEvaluation : twoOrMoreEvaluations) {
					if (aEvaluation.isEqual(idResolver, sourceAndArgumentValues)) {
						//						if (debugEvaluations) {
						//							AbstractTransformer.INVOCATIONS.println("getUniqueEvaluation old:" + aEvaluation);
						//						}
						return aEvaluation;
					}
				}
				twoOrMoreEvaluations.add(theEvaluation);
			}
			//			if (debugEvaluations) {
			//				AbstractTransformer.INVOCATIONS.println("getUniqueEvaluation new:" + theEvaluation);
			//			}
			return theEvaluation.getResult();
		}
	}

	protected abstract @NonNull Evaluation newInstance(@Nullable Object @NonNull [] values);

}