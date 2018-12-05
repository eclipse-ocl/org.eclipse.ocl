/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cse;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * An AbstractAnalysis describes one or more occurrences of a structurally identical tree
 * of CGValuedElements.
 * <p>A SimpleAnalysis describes a single occurrence that may grow into:
 * <p>A CommonAnalysis describes a multiple occurrence.
 */
public abstract class AbstractAnalysis implements Comparable<AbstractAnalysis>
{
	/**
	 * Return the analysis that shares this analysis and anAnalysis
	 */
	public abstract @NonNull CommonAnalysis addAnalysis(@NonNull AbstractAnalysis anAnalysis);

	/**
	 * Return the analysis that shares this analysis and commonAnalysis
	 */
	public abstract @NonNull CommonAnalysis addCommonAnalysis(@NonNull CommonAnalysis commonAnalysis);

	/**
	 * Return the analysis that shares this analysis and simpleAnalysis
	 */
	public abstract @NonNull CommonAnalysis addSimpleAnalysis(@NonNull SimpleAnalysis simpleAnalysis);

	@Override
	public int compareTo(AbstractAnalysis o2) {
		int h1 = this.getStructuralHashCode();
		int h2 = o2.getStructuralHashCode();
		return h1 > h2 ? 1 : h1 < h2 ? -1 : 0;
	}

	/**
	 * Return the depth of the deepest shared analysis.
	 */
	public abstract int getMaxDepth();

	/**
	 * Return the depth of the shallowest shared analysis.
	 */
	public abstract int getMinDepth();

	/**
	 * Return the CG element to be used for this analysis. For common subexpressions, the return is the shallowest CG element
	 * and is visible to all other secondary elements.
	 */
	public abstract @NonNull CGValuedElement getPrimaryElement();

	/**
	 * Return the hash code derived from the tree structure of this analysis.
	 */
	public abstract int getStructuralHashCode();

	/**
	 * Return true if the tree structure of this analysis is identical to thatAnalysis.
	 */
	public abstract boolean isStructurallyEqualTo(@NonNull AbstractAnalysis thatAnalysis);

	/**
	 * Return true if the tree structure of this analysis is identical to thatAnalysis.
	 */
	public abstract boolean isStructurallyEqualTo(@NonNull SimpleAnalysis thatAnalysis);
}