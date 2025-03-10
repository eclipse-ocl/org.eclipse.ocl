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
package org.eclipse.ocl.examples.codegen.java.iteration;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.pivot.LoopExp;

public class SearchIteration2Java extends AbstractIteration2Java
{
	public static final @NonNull SearchIteration2Java INSTANCE = new SearchIteration2Java();

	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		js.appendTrue();
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable CGTypeId getAccumulatorTypeId(@NonNull CodeGenAnalyzer analyzer, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return analyzer.getTypeId(new JavaTypeId(Object.class));
	}

	@Override
	public boolean isNonNullAccumulator(@NonNull LoopExp element) {
		return element.isNonNull();
	}
}
