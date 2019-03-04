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
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.values.CollectionValue;

public class CollectIteration2Java extends AbstractAccumulation2Java
{
	public static final @NonNull CollectIteration2Java INSTANCE = new CollectIteration2Java();

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		//		CGTypeId cgBodyTypeId = cgBody.getTypeId();
		//		ElementId elementId = cgBodyTypeId.getElementId();
		//		Class<?> boxedClass = elementId != null ? js.getCodeGenerator().getBoxedClass(elementId) : Object.class;
		TypeDescriptor bodyTypeDescriptor = js.getCodeGenerator().getTypeDescriptor(cgBody);
		if (bodyTypeDescriptor.isAssignableTo(CollectionValue.class)) {
			js.append("for (Object value : ");
			js.appendValueName(cgBody);
			js.append(".flatten().getElements()) {\n");
			{
				js.pushIndentation(null);
				js.appendValueName(cgAccumulator);
				js.append(".add(value);\n");
				js.popIndentation();
			}
			js.append("}\n");
		}
		else if (bodyTypeDescriptor.getJavaClass().isAssignableFrom(CollectionValue.class)) {
			js.append("if (");
			js.appendValueName(cgBody);
			js.append(" instanceof ");
			js.appendClassReference(null, CollectionValue.class);
			js.append(") {\n");
			{
				js.pushIndentation(null);
				js.append("for (Object value : ((");
				js.appendClassReference(null, CollectionValue.class);
				js.append(")");
				js.appendValueName(cgBody);
				js.append(").flatten().getElements()) {\n");
				{
					js.pushIndentation(null);
					js.appendValueName(cgAccumulator);
					js.append(".add(value);\n");
					js.popIndentation();
				}
				js.append("}\n");
				js.popIndentation();
			}
			js.append("}\n");
			js.append("else {\n");
			{
				js.pushIndentation(null);
				js.appendValueName(cgAccumulator);
				js.append(".add(");
				js.appendValueName(cgBody);
				js.append(");\n");
				js.popIndentation();
			}
			js.append("}\n");
		}
		else {
			js.appendValueName(cgAccumulator);
			js.append(".add(");
			js.appendValueName(cgBody);
			js.append(");\n");
		}
		return true;
	}
}