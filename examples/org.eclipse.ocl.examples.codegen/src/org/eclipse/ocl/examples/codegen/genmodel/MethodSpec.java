/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.genmodel;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * MethodSpec captures the requirements for insering a merthod hierarchy into a genmodel interface/implementation hierarchy.
 * The derived getBody() method provides the implementation contents.
 */
public abstract class MethodSpec
{
	/**
	 * The class at which this method hierarchy is rooted.
	 */
	protected final @NonNull Class<?> rootClass;

	/**
	 * The declaration of the interface less prefixes: e.g. "boolean method(int arg)"
	 */
	protected final @NonNull String interfaceDecl;

	/**
	 * Optional variable declaration to be made priovate in the implementation root.
	 */
	protected final @Nullable String variableDecl;

	/**
	 * Comment to accompany the root interface declaration.
	 */
	protected final @Nullable String comment;

	public MethodSpec(@NonNull Class<?> rootClass, @NonNull String interfaceDecl, @Nullable String variableDecl, @Nullable String comment) {
		this.rootClass = rootClass;
		this.interfaceDecl = interfaceDecl;
		this.variableDecl = variableDecl;
		this.comment = comment;
	}

	protected void appendHeader(@NonNull StringBuilder s, @Nullable String comment) {
		s.append("\n");
		s.append("\t/**\n");
		if ("".equals(comment)) {
		}
		else if (comment != null) {
			for (String commentLine : comment.split("\n")) {
				s.append("\t * " + commentLine + "\n");
			}
			s.append("\t *\n");
		}
		else {
			s.append("\t * {@inheritDoc}\n");
		}
		s.append("\t * @generated\n");
		s.append("\t */\n");
	}

	public void generate(@NonNull StringBuilder s, @NonNull ModelSpec modelSpec, @NonNull GenModel genModel, boolean isImplementation) {
		if (!rootClass.isAssignableFrom(modelSpec.getCgClass())) {
			return;
		}
		boolean isRootSpec = modelSpec.getCgClass() == rootClass;
		if (!isImplementation) {
			if (isRootSpec) {
				String resolvedDeclaration = OCLGenModelUtil.resolveImports(genModel, resolveJDTAnnotations(genModel, interfaceDecl));
				appendHeader(s, comment);
				s.append("\t// Generated from " + getClass().getName() + "\n");
				s.append("\t" + resolvedDeclaration + ";\n");
			}
		}
		else {
			if (isRootSpec && (variableDecl != null)) {
				appendHeader(s, "");
				s.append("\tprotected " + variableDecl + ";\n");
			}
			String body = getBody(modelSpec, genModel);
			String superBody = getSuperBody(modelSpec, genModel);
			if ((body != null) && !body.equals(superBody)) {
				String resolvedDeclaration = OCLGenModelUtil.resolveImports(genModel, resolveJDTAnnotations(genModel, interfaceDecl));
				String resolvedBody = OCLGenModelUtil.resolveImports(genModel, body);
				appendHeader(s, null);
				if (genModel.useClassOverrideAnnotation() && (superBody != null)) {
					s.append("\t@Override\n");
				}
				s.append("\tpublic " + resolvedDeclaration + " {\n");
				s.append("\t\t" + resolvedBody + "\n");
				s.append("\t}\n");
			}
		}
	}

	/**
	 * Return the text to appear as the body of cgModelSpec. If the return is null the definition is omitted.
	 */
	protected abstract @Nullable String getBody(@NonNull ModelSpec modelSpec, @NonNull GenModel genModel);

	/**
	 * Return the non-null body implementation of of the primary inheritance if there is one,
	 * or null if there is no inherited body.
	 */
	protected @Nullable String getSuperBody(@NonNull ModelSpec modelSpec, @NonNull GenModel genModel) {
		Class<?> cgClass = modelSpec.getCgClass();
		while (cgClass != null) {
			List<ModelSpec> specs = null;
			Class<?> cgSuperclass = cgClass.getSuperclass();
			if (cgSuperclass != null) {
				String name = cgSuperclass.getName();
				specs = ModelSpec.get(name);
			}
			if (specs == null) {
				for (Class<?> cgSuperInterface : cgClass.getInterfaces()) {
					if (cgSuperInterface != null) {
						String name = cgSuperInterface.getName();
						specs = ModelSpec.get(name);
						cgSuperclass = cgSuperInterface;
						break;
					}
				}
			}
			if (specs != null) {
				for (ModelSpec spec : specs) {
					if (spec != null) {
						String body = getBody(spec, genModel);
						if (body != null) {
							return body;
						}
					}
				}
			}
			cgClass = cgSuperclass;
		}
		return null;
	}

	protected @NonNull String resolveJDTAnnotations(@NonNull GenModel genModel, @NonNull String text) {
		if (text.contains("@NonNull ")) {
			String atNonNull = OCLGenModelUtil.atNonNull(genModel);
			text = text.replace("@NonNull ", atNonNull);
		}
		if (text.contains("@Nullable ")) {
			String atNullable = OCLGenModelUtil.atNullable(genModel);
			text = text.replace("@Nullable ", atNullable);
		}
		return text;
	}

	@Override
	public String toString() {
		return interfaceDecl;
	}
}