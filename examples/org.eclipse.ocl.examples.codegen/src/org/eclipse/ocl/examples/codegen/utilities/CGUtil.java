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
package org.eclipse.ocl.examples.codegen.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

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
		Set<@NonNull String> reservedNames = new HashSet<>();
		reservedNames.add("Byte");
		reservedNames.add("Character");
		reservedNames.add("Class");
		reservedNames.add("Double");
		reservedNames.add("Enum");
		reservedNames.add("Error");
		reservedNames.add("Exception");
		reservedNames.add("Float");
		reservedNames.add("Integer");
		reservedNames.add("Long");
		reservedNames.add("Math");
		reservedNames.add("Object");
		reservedNames.add("Package");
		reservedNames.add("Process");
		reservedNames.add("Short");
		reservedNames.add("String");
		try {
			Set<@NonNull String> longImports = new HashSet<>();
			BufferedReader reader = new BufferedReader(new StringReader(classFileContent));
			StringBuilder s = new StringBuilder();
			for (String line; (line = reader.readLine()) != null; ) {
				if (line.startsWith("import ")) {
					int index = line.indexOf(";");
					if (index > 0) {
						String longImport = line.substring(7, index).trim();
						int lastIndex = longImport.lastIndexOf(".");
						if (lastIndex > 0) {
							String shortImport = longImport.substring(lastIndex+1);
							assert shortImport != null;
							if (!reservedNames.contains(shortImport)) {
								longImports.add(longImport);
							}
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
					else {
						line = line.substring(0, prefixEnd) + "@" + line.substring(annotationStart, line.length());
						break;
					}
				}
				//				if (!line.startsWith("import org.eclipse.ocl.pivot.Class;") && !line.startsWith("import org.eclipse.ocl.pivot.Package;")) {
				s.append(line);
				s.append("\n");
				//				}
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

	public static @NonNull CGIterator getAccumulator(@NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return ClassUtil.nonNullState(cgIterationCallExp.getAccumulator());
	}

	public static @NonNull OCLExpression getAST(@NonNull CGCallExp cgCallExp) {
		return ClassUtil.nonNullState((OCLExpression)cgCallExp.getAst());
	}

	public static org.eclipse.ocl.pivot.@NonNull Class getAST(@NonNull CGClass cgClass) {
		return ClassUtil.nonNullState((org.eclipse.ocl.pivot.Class)cgClass.getAst());
	}

	public static @NonNull Constraint getAST(@NonNull CGConstraint cgConstraint) {
		return ClassUtil.nonNullState((Constraint)cgConstraint.getAst());
	}

	public static @NonNull Type getAST(@NonNull CGExecutorType cgExecutorType) {
		return ClassUtil.nonNullState((Type)cgExecutorType.getAst());
	}

	public static @NonNull Variable getAST(@NonNull CGIterator cgIterator) {
		return ClassUtil.nonNullState((Variable)cgIterator.getAst());
	}

	public static @NonNull /*ParameterVariable*/ VariableDeclaration getAST(@NonNull CGParameter cgParameter) {
		return ClassUtil.nonNullState((VariableDeclaration)cgParameter.getAst());
	}

	public static @NonNull List<@NonNull CGIterator> getCoIteratorsList(@NonNull CGIterationCallExp cgIterationCallExp) {
		return ClassUtil.nullFree(cgIterationCallExp.getCoIterators());
	}

	public static @Nullable CGClass getContainingClass(@NonNull CGElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGClass) {
				return (CGClass) cgElement;
			}
		}
		return null;
	}

	public static @Nullable CGConstraint getContainingConstraint(@NonNull CGElement cgExpression) {
		for (CGElement cgElement = cgExpression; cgElement != null; cgElement = cgElement.getParent()) {
			if (cgElement instanceof CGConstraint) {
				return (CGConstraint) cgElement;
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

	public static @NonNull CGValuedElement getIn(@NonNull CGLetExp cgLetExp) {
		return ClassUtil.nonNullState(cgLetExp.getIn());
	}

	public static @NonNull CGValuedElement getInit(@NonNull CGTuplePart cgTuplePart) {
		return ClassUtil.nonNullState(cgTuplePart.getInit());
	}

	public static @NonNull CGValuedElement getInit(@NonNull CGVariable cgVariable) {
		return ClassUtil.nonNullState(cgVariable.getInit());
	}

	public static @NonNull List<@NonNull CGIterator> getIteratorsList(@NonNull CGIterationCallExp cgIterationCallExp) {
		return ClassUtil.nullFree(cgIterationCallExp.getIterators());
	}

	public static Iterable<@NonNull CGTuplePart> getParts(@NonNull CGTupleExp cgTupleExp) {
		return ClassUtil.nullFree(cgTupleExp.getParts());
	}

	public static @NonNull CGValuedElement getReferredConstant(@NonNull CGConstantExp cgConstantExp) {
		return ClassUtil.nonNullState(cgConstantExp.getReferredConstant());
	}

	public static @NonNull CGValuedElement getReferredVariable(@NonNull CGVariableExp cgVariableExp) {
		return ClassUtil.nonNullState(cgVariableExp.getReferredVariable());
	}

	public static boolean isInlinedId(@NonNull ElementId elementId) {
		return (elementId instanceof PrimitiveTypeId)
				|| (elementId instanceof OclVoidTypeId)
				|| (elementId instanceof TemplateParameterId);
	}

	public static @Nullable Boolean isKindOf(@NonNull CGValuedElement cgValue, @NonNull CGExecutorType executorType) {
		CGTypeId referenceTypeId = executorType.getUnderlyingTypeId();
		CGTypeId actualTypeId = cgValue.getTypeId();
	//	return referenceTypeId == actualTypeId ? Boolean.TRUE : null;		// FIXME support conformance somehow
		if (referenceTypeId == actualTypeId) {
			return Boolean.TRUE;
		}
		Executor executor = PivotUtil.getExecutor(cgValue);
		assert executor != null;
		IdResolver idResolver = executor.getIdResolver();
		StandardLibrary standardLibrary = executor.getStandardLibrary();
		TypeId asReferenceTypeId = referenceTypeId.getASTypeId();
		assert asReferenceTypeId != null;
		TypeId asActualTypeId = actualTypeId.getASTypeId();
		assert asActualTypeId != null;
		Type asReferenceType = idResolver.getType(asReferenceTypeId);
		Type asActualType = idResolver.getType(asActualTypeId);
		if (asActualType.conformsTo(standardLibrary, asReferenceType)) {
			return Boolean.TRUE;				// Guaranteed conformance
		}
		else if (!asReferenceType.conformsTo(standardLibrary, asActualType)) {
			return Boolean.FALSE;				// Guaranteed non-conformance
		}
		else {
			return null;						// Run-time conformance test required
		}
	}

	/**
	 * Return true if the testNameSuffix system property has been set to indicate tests are
	 * running under the supervision of the maven-surefire-plugin..
	 */
	public static boolean isMavenSurefire() {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return (testNameSuffix != null) && testNameSuffix.startsWith("maven");
	}

	/**
	 * Return true if the testNameSuffix system property has been set to indicate tests are
	 * running under the supervision of the tycho-surefire-plugin..
	 */
	public static boolean isTychoSurefire() {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return (testNameSuffix != null) && testNameSuffix.startsWith("tycho");
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
	 * Trim trailing spaces from lines.
	 */
	public static @NonNull String trimLines(@NonNull String classFileContent) {
		try {
			BufferedReader reader = new BufferedReader(new StringReader(classFileContent));
			StringBuilder s = new StringBuilder();
			for (String line; (line = reader.readLine()) != null; ) {
				int len = line.length();
				int i = len;
				while ((i > 0) && Character.isWhitespace(line.charAt(i-1))) {
					i--;
				}
				s.append(i < len ? line.substring(0, i) : line);
				s.append("\n");
			}
			return s.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classFileContent;
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
