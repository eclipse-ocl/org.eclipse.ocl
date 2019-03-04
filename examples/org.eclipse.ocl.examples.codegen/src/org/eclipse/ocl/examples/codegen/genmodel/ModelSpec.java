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
package org.eclipse.ocl.examples.codegen.genmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;

/**
 * ModelSpec defines an ability to contribute code to a genmodel session.
 * <p>
 * The static class maintins a registry of all the fully qualified class names for which code can be contributed. The registry
 * is maintained automatically whenever a class instance is constructed. Multiple ModelSpec instances for the same class are
 * permitted.
 * <p>
 * Code is generated when {@link org.eclipse.ocl.examples.build.templates.model.Class} invokes
 * {@link #generate(GenClass, boolean)} for a genClass and there are one or more ModelSpecs for that genClass.
 * <p>
 * The derived ModelSpec instance must implement {@link #generate(StringBuilder, GenModel, boolean)} to add its
 * contributuions, perhaps using a MethodSpec to co-ordinate the contribution of a derived method hierarchy
 * across many ModelSpec instances.
 */
public abstract class ModelSpec
{
	private static @NonNull Map<String, List<ModelSpec>> classname2specs = new HashMap<String, List<ModelSpec>>();

	protected static @NonNull String classRef(@NonNull Class<?> javaClass) {
		return ImportUtils.getAffixedName(javaClass);
	}

	public static String generate(@NonNull GenClass genClass, boolean isImplementation) {
		StringBuilder s = new StringBuilder();
		String qualifiedInterfaceName = genClass.getQualifiedInterfaceName();
		List<ModelSpec> specs = classname2specs.get(qualifiedInterfaceName);
		if (specs != null) {
			@SuppressWarnings("null") @NonNull GenModel genModel = genClass.getGenModel();
			for (ModelSpec spec : specs) {
				spec.generate(s, genModel, isImplementation);
			}
		}
		return s.toString();
	}

	public static @Nullable List<ModelSpec> get(@NonNull String className) {
		return classname2specs.get(className);
	}

	protected final @NonNull Class<?> cgClass;

	/**
	 * Construct and install a ModelSpec to make contributions to cgClass.
	 * @param cgClass
	 */
	public ModelSpec(@NonNull Class<?> cgClass) {
		this.cgClass = cgClass;
		String className = cgClass.getName();
		List<ModelSpec> specs = classname2specs.get(className);
		if (specs == null) {
			specs = new ArrayList<ModelSpec>();
			classname2specs.put(cgClass.getName(), specs);
		}
		if (!specs.contains(this)) {
			specs.add(this);
		}
	}

	public abstract void generate(@NonNull StringBuilder s, @NonNull GenModel genModel, boolean isImplementation);

	public @NonNull Class<?> getCgClass() {
		return cgClass;
	}

	@Override
	public String toString() {
		return cgClass.getName();
	}
}
