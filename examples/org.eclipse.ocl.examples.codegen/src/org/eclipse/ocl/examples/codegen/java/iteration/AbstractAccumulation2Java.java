/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

public abstract class AbstractAccumulation2Java extends AbstractIteration2Java
{
	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGTypeId cgAccumulatorId = cgIterationCallExp.getTypeId();
		CollectionTypeId elementId = (CollectionTypeId)cgAccumulatorId.getElementId();
		String name = elementId.getGeneralizedId().getName();
		js.appendClassReference(null, ValueUtil.class);
		js.append(".create" + name + "AccumulatorValue(");
		js.appendIdReference(elementId);
		js.append(")");
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		return js.appendAssignment(cgIterationCallExp, cgAccumulator);
	}

	@SuppressWarnings("null")
	protected @NonNull CGIterator getAccumulator(@NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return cgIterationCallExp.getAccumulator();
	}

	protected @NonNull Class<?> getAccumulatorClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CollectionTypeId typeId) {
		JavaCodeGenerator codeGenerator = (JavaCodeGenerator)analyzer.getCodeGenerator();
		TypeDescriptor boxedTypeDescriptor = codeGenerator.getBoxedDescriptor(typeId);
		Class<?> boxedClass = boxedTypeDescriptor.getJavaClass();
		for (Class<?> nestedClass : boxedClass.getClasses()) {
			if ("Accumulator".equals(nestedClass.getSimpleName())) {
				return nestedClass;
			}
		}
		return Object.class;
	}

	@Override
	public @Nullable CGTypeId getAccumulatorTypeId(@NonNull CodeGenAnalyzer analyzer, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGTypeId cgAccumulatorId = cgIterationCallExp.getTypeId();
		ElementId elementId = cgAccumulatorId.getElementId();
		Class<?> accumulatorClass = elementId instanceof CollectionTypeId ? getAccumulatorClass(analyzer, (CollectionTypeId) elementId) : Object.class;
		return analyzer.getTypeId(JavaConstants.getJavaTypeId(accumulatorClass));
	}
}