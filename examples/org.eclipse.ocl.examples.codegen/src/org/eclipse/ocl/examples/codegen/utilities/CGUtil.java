/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;

public class CGUtil
{
	/**
	 * Simplify org.eclipse.jdt.annotation references that unnecessarily use long firm within a long firm.
	 * e.g. replace
	 *
	 * "X.Y.@org.eclipse.jdt.annotation.NonNull Z" by "@NonNull Z" if "X.Y.Z" is an import.
	 * "java.lang.@org.eclipse.jdt.annotation.NonNull Z" by "@NonNull Z".
	 * "Y.@org.eclipse.jdt.annotation.NonNull Z" by "Y.@NonNull Z"
	 */
	public static @NonNull String compressJDTannotations(@NonNull String classFileContent) {
		final String ORG_ECLIPSE_JDT_ANNOTATION = org.eclipse.jdt.annotation.NonNull.class.getPackage().getName();
		final String AT_ORG_ECLIPSE_JDT_ANNOTATION_DOT = "@" + ORG_ECLIPSE_JDT_ANNOTATION + ".";
		try {
			Set<@NonNull String> longImports = new HashSet<>();
			//			Map<@NonNull String, @NonNull String> shortImports = new HashMap<>();
			BufferedReader reader = new BufferedReader(new StringReader(classFileContent));
			StringBuilder s = new StringBuilder();
			for (String line; (line = reader.readLine()) != null; ) {
				if (line.startsWith("import ")) {
					int index = line.indexOf(";");
					if (index > 0) {
						String longImport = line.substring(7, index).trim();
						longImports.add(longImport);
						int lastIndex = longImport.lastIndexOf(".");
						if (lastIndex > 0) {
							String shortImport = longImport.substring(lastIndex+1);
							assert shortImport != null;
							//							String oldLongImport = shortImports.put(shortImport, longImport);
							//							if (oldLongImport != null) {
							//								shortImports.put(shortImport, shortImport);
							//							}
						}
					}
				}
				while (true) {
					int prefixEnd = line.indexOf(AT_ORG_ECLIPSE_JDT_ANNOTATION_DOT);
					if (prefixEnd < 0) {
						break;
					}
					int prefixIndex = prefixEnd;
					for (; prefixIndex > 0; --prefixIndex) {
						char c = line.charAt(prefixIndex-1);
						if ((c != '.') && !Character.isJavaIdentifierPart(c)) {
							break;
						}
					}
					String prefixName = line.substring(prefixIndex, prefixEnd-1);
					int annotationStart = prefixEnd + AT_ORG_ECLIPSE_JDT_ANNOTATION_DOT.length();
					int annotationEnd = annotationStart+1;
					for (; true; ++annotationEnd) {
						char c = line.charAt(annotationEnd);
						if (!Character.isJavaIdentifierPart(c)) {
							break;
						}
					}
					String annotationName = line.substring(annotationStart, annotationEnd);
					if (!longImports.contains(ORG_ECLIPSE_JDT_ANNOTATION + "." + annotationName)) {
						break;
					}
					int suffixStart = annotationEnd;
					for (; true; ++suffixStart) {
						char c = line.charAt(suffixStart);
						if (!Character.isWhitespace(c)) {
							break;
						}
					}
					int suffixEnd = suffixStart;
					for (; true; ++suffixEnd) {
						char c = line.charAt(suffixEnd);
						if (!Character.isJavaIdentifierPart(c)) {
							break;
						}
					}
					String suffixName = line.substring(suffixStart, suffixEnd);
					String importName = prefixName + "." + suffixName;
					if (longImports.contains(importName) || importName.startsWith("java.lang.")) {
						line = line.substring(0, prefixIndex) + "@" + line.substring(annotationStart, line.length());
					}
					else {//if (!prefixName.equals(shortImports.get(prefixName))) {
						line = line.substring(0, prefixEnd) + "@" + line.substring(annotationStart, line.length());
						break;
					}
					//					else {
					//						break;
					//					}
				}
				s.append(line);
				s.append("\n");
			}
			return s.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classFileContent;
	}

	public static @NonNull CGParameter createCGParameter(@NonNull String name, @NonNull CGTypeId typeId) {
		CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
		cgParameter.setName(name);
		cgParameter.setTypeId(typeId);
		return cgParameter;
	}

	public static @Nullable CGClass getContainingClass(@NonNull CGElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGClass) {
				return (CGClass) cgElement;
			}
		}
		return null;
	}

	/*	public static @Nullable CGOperation getContainingOperation(@NonNull CGValuedElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGOperation) {
				return (CGOperation) cgElement;
			}
		}
		return null;
	} */

	public static boolean isInlinedId(@NonNull ElementId elementId) {
		return (elementId instanceof PrimitiveTypeId)
				|| (elementId instanceof OclVoidTypeId)
				|| (elementId instanceof TemplateParameterId);
	}

	public static @Nullable Boolean isKindOf(@NonNull CGValuedElement cgValue, @NonNull CGExecutorType executorType) {
		CGTypeId referenceTypeId = executorType.getUnderlyingTypeId();
		CGTypeId actualTypeId = cgValue.getTypeId();
		return referenceTypeId == actualTypeId ? Boolean.TRUE : null;			// FIXME support conformance somehow
	}


	/**
	 * Replace oldElement by newElement and return oldElement which is orphaned by the replacement.
	 */
	public static @NonNull CGValuedElement replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement) {
		assert !oldElement.isRequired() || !newElement.isNull();
		EObject oldContainer = oldElement.eContainer();
		//		EObject newContainer = newElement.eContainer();
		//		assert (oldContainer != null) && (newContainer == null);
		EcoreUtil.replace(oldElement, newElement);
		assert oldElement.eContainer() == null;
		assert newElement.eContainer() == oldContainer;
		return oldElement;
	}

	/**
	 * Use wrapExp to wrap wrappedExp.
	 */
	public static void wrap(@NonNull CGCallExp wrapExp, @NonNull CGValuedElement wrappedExp) {
		wrapExp.setTypeId(wrappedExp.getTypeId());
		wrapExp.setAst(wrappedExp.getAst());
		replace(wrappedExp, wrapExp);
		wrapExp.setSource(wrappedExp);
	}
}
