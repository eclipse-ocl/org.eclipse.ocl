/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.modelspecs;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.genmodel.MethodSpec;
import org.eclipse.ocl.examples.codegen.genmodel.ModelSpec;

/**
 * CGElementModelSpec supports generation of the CGElement.getChildren()/getParent() method hierarchy.
 */
public class CGElementModelSpec extends ModelSpec
{
	protected abstract static class MyMethodSpec extends MethodSpec
	{
		public MyMethodSpec(@NonNull Class<?> rootClass, @NonNull String interfaceDecl, @Nullable String variableDecl, @Nullable String comment) {
			super(rootClass, interfaceDecl, variableDecl, comment);
		}

		@Override
		protected final @Nullable String getBody(@NonNull ModelSpec modelSpec, @NonNull GenModel genModel) {
			if (modelSpec instanceof CGElementModelSpec) {
				return getBody((CGElementModelSpec)modelSpec, genModel);
			}
			else {
				return null;
			}
		}

		protected abstract @Nullable String getBody(@NonNull CGElementModelSpec cgModelSpec, @NonNull GenModel genModel);
	}

	protected static MethodSpec getChildren = new MyMethodSpec(CGElement.class, "@NonNull Iterable<@NonNull ? extends " + classRef(CGElement.class) + "> getChildren()", null,
			"Return the child CGElements.")
	{
		@Override
		protected @Nullable String getBody(@NonNull CGElementModelSpec modelSpec, @NonNull GenModel genModel) {
			if (modelSpec.cgClass == rootClass) {
				return "@SuppressWarnings({\"null\", \"unchecked\"}) @NonNull Iterable<@NonNull ? extends CGElement> eContents = (Iterable<@NonNull ? extends CGElement>) eContents();\n" +
						"		return eContents;";
			}
			else {
				return null;
			}
		}
	};

	protected static MethodSpec getParent = new MyMethodSpec(CGElement.class, "@Nullable " + classRef(CGElement.class) + " getParent()", null,
			"Return the parent node in a CG tree, null at the root.")
	{
		@Override
		protected @Nullable String getBody(@NonNull CGElementModelSpec modelSpec, @NonNull GenModel genModel) {
			if (modelSpec.cgClass == rootClass) {
				return "return (" + classRef(CGElement.class) + ")eContainer();";
			}
			else {
				return null;
			}
		}
	};

	public static class Register {
		public Register() {
			new CGElementModelSpec(CGElement.class);
		}
	}

	protected CGElementModelSpec(@NonNull Class<?> cgClass) {
		super(cgClass);
	}

	@Override
	public void generate(@NonNull StringBuilder s, @NonNull GenModel genModel, boolean isImplementation) {
		getChildren.generate(s, this, genModel, isImplementation);
		getParent.generate(s, this, genModel, isImplementation);
	}
}
