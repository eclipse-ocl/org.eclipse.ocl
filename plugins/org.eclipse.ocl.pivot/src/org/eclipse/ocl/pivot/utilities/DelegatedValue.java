package org.eclipse.ocl.pivot.utilities;

import org.eclipse.jdt.annotation.Nullable;

/**
 * A DelegatedValue supports use of the current value of a changing value such as an Iterator into the EvaluationEnvironment.
 *
 * @since 7.0
 */
public interface DelegatedValue
{
	@Nullable Object get();
}