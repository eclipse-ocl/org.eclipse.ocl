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
package org.eclipse.ocl.examples.codegen.java.types;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.MapTypeId;

/**
 * A BoxedValueDescriptor describes a type whose boxed representation differs from its unboxed representation. It has a pivot ElementId and a Java class.
 * <p>
 * Thus an IntegerValue is a TypeId.INTEGER and an org.eclipse.ocl.domain.values.IntegerValue.
 */
public class BoxedMapDescriptor extends AbstractValueDescriptor implements BoxedDescriptor, MapDescriptor
{
	protected final @NonNull MapDescriptor unboxedDescriptor;

	public BoxedMapDescriptor(@NonNull MapTypeId elementId, @NonNull Class<?> javaClass, @NonNull MapDescriptor unboxedDescriptor) {
		super(elementId, javaClass);
		this.unboxedDescriptor = unboxedDescriptor;
	}

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
//		if (collectionDescriptor != null) {
			js.append("final ");
//			js.appendIsRequired(true);
//			js.append(" ");
			unboxedDescriptor.append(js, true);
//			js.appendClassReference(List.class, false, unboxedTypeDescriptor.getJavaClass());
			js.append(" ");
			js.appendValueName(cgUnboxExp);
			js.append(" = ");
			js.appendValueName(boxedValue);
			js.append(".asEcoreObjects(");
			js.appendReferenceTo(localContext.getIdResolverVariable(cgUnboxExp));
			js.append(", ");
			unboxedDescriptor.appendElement(js, true);
			js.append(".class);\n");
			//
			js.append("assert ");
			js.appendValueName(cgUnboxExp);
			js.append(" != null;\n");
//		}
		return true;
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		return (EcoreDescriptor) unboxedDescriptor;
	}

	@Override
	public @NonNull MapTypeId getElementId() {
		return (MapTypeId) elementId;
	}

	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		return unboxedDescriptor;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (!(typeDescriptor instanceof BoxedDescriptor)) {
			return false;
		}
		return javaClass.isAssignableFrom(typeDescriptor.getJavaClass());
	}

	@Override
	public boolean isAssignableTo(@NonNull Class<?> javaClass) {
		return javaClass.isAssignableFrom(Set.class);
	}

	@Override
	public void append(@NonNull JavaStream javaStream, boolean reClass) {
		// TODO Auto-generated method stub
	}

	@Override
	public void appendElement(@NonNull JavaStream javaStream, boolean reClass) {
		// TODO Auto-generated method stub
	}
}