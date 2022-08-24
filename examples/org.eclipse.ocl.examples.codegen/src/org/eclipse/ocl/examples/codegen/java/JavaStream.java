/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.AbstractCodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.CodeGenOptions;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.EObjectDescriptor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.xtext.util.Strings;

/**
 * A JavaStream provides many appendXXX helper methods and a push/pop facility for auto-indentation of Java text.
 */
public class JavaStream
{
	/**
	 * Return an encoding of theString that is suitable for use as a Java Identifier.
	 * Awkward characters are replaced by an underscore prefixed decimal string.
	 * The first character and the character following a decimal string may not be a decimal.
	 */
	public static @NonNull String convertToJavaIdentifier(@NonNull String theString) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuilder outBuffer = new StringBuilder(bufLen);
		boolean isStart = true;
		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			if (/*(aChar != '_') &&*/ isStart ? Character.isJavaIdentifierStart(aChar) : Character.isJavaIdentifierPart(aChar)) {
				outBuffer.append(aChar);
				isStart = false;
			}
			else {
				outBuffer.append("_");
				outBuffer.append((int)aChar);
				isStart = true;
			}
		}
		return outBuffer.toString();
	}

	public static PrettyPrintOptions.@NonNull Global createOptions(@NonNull Visitable element) {
		Namespace scope = null;
		if (element instanceof EObject) {
			for (EObject eObject = (EObject) element; eObject != null; ) {
				if (eObject instanceof Model) {
					break;
				}
				if (eObject instanceof Type) {
					scope = (Namespace) eObject;
					break;
				}
				if (eObject instanceof org.eclipse.ocl.pivot.Package) {
					scope = (Namespace) eObject;
					break;
				}
				if ((eObject instanceof ExpressionInOCL) && (((ExpressionInOCL)eObject).getOwnedContext() != null)) {
					eObject = ((ExpressionInOCL)eObject).getOwnedContext().getType();
				}
				else {
					eObject = eObject.eContainer();
				}
			}
		}
		PrettyPrintOptions.Global createOptions = PrettyPrinter.createOptions(scope);
		createOptions.setLinelength(80);
		if (element instanceof EObject) {
			Resource asResource = EcoreUtil.getRootContainer((EObject)element).eResource();
			if (asResource != null) {
				ResourceSet asResourceSet = asResource.getResourceSet();
				if (asResourceSet != null) {
					PivotMetamodelManager metamodelManager = PivotMetamodelManager.getAdapter(asResourceSet);
					createOptions.setEnvironmentFactory(metamodelManager.getEnvironmentFactory());
				}
			}
		}
		return createOptions;
	}

	public static interface SubStream
	{
		void append();
	}

	/**
	 * A derived TypeRepresentation appends a construct that involves a type declaration whose representation may vary
	 * according to the prevailing calling protocol.
	 */
	public static interface TypeRepresentation
	{
		void appendClassReference(@Nullable Boolean isRequired, @Nullable CGValuedElement cgValue);
		void appendDeclaration(@NonNull CGValuedElement cgElement);
		void appendTypeDeclaration(@NonNull CGValuedElement cgElement);
	}

	private abstract static class AbstractTypeRepresentation implements TypeRepresentation
	{
		protected final @NonNull JavaStream js;
		protected final @NonNull JavaCodeGenerator codeGenerator;

		public AbstractTypeRepresentation(@NonNull JavaStream javaStream) {
			this.js = javaStream;
			this.codeGenerator = js.getCodeGenerator();
		}

		@Override
		public void appendClassReference(@Nullable Boolean isRequired, @Nullable CGValuedElement cgValue) {
			if (cgValue == null) {
				js.append("<<null->>");
			}
			else if (cgValue.getNamedValue().isCaught()) {
				js.appendClassReference(isRequired, Object.class);
			}
			else {
				TypeDescriptor typeDescriptor = getTypeDescriptor(cgValue);
				if ((cgValue instanceof CGParameter) && (cgValue.eContainer() instanceof CGOperation) && (typeDescriptor instanceof EObjectDescriptor)) {		// FIXME eliminate reclassing
					Class<?> originalJavaClass = ((EObjectDescriptor)typeDescriptor).getOriginalJavaClass();
					js.appendClassReference(isRequired, originalJavaClass);
				}
				else {
					typeDescriptor.append(js, isRequired);
				}
			}
		}

		@Override
		public void appendDeclaration(@NonNull CGValuedElement cgElement) {
			boolean isGlobal = cgElement.isGlobal();
			if (isGlobal) {
				js.append("public static ");
			}
			if (!cgElement.isSettable()) {
				js.append("final ");
			}
			appendTypeDeclaration(cgElement);
			js.append(" ");
			String valueName = js.cg2java.getValueName(cgElement);
			js.append(valueName);
		}

		@Override
		public void appendTypeDeclaration(@NonNull CGValuedElement cgElement) {
			Boolean isRequired = codeGenerator.isRequired(cgElement);
			js.appendIsCaught(cgElement.isNonInvalid(), cgElement.isCaught());
			js.append(" ");
			appendClassReference(isRequired, cgElement);
		}

		protected @NonNull TypeDescriptor getTypeDescriptor(@NonNull CGValuedElement cgValue) {
			return codeGenerator.getTypeDescriptor(cgValue);
		}
	}

	private static class BoxedTypeRepresentation extends AbstractTypeRepresentation
	{
		public BoxedTypeRepresentation(@NonNull JavaStream javaStream) {
			super(javaStream);
		}
	}

	// FIXME Bug 569113 This class is never used.
	private static class EcoreTypeRepresentation extends AbstractTypeRepresentation
	{
		protected final @Nullable Class<?> instanceClass;

		public EcoreTypeRepresentation(@NonNull JavaStream javaStream, @Nullable Class<?> instanceClass) {
			super(javaStream);
			this.instanceClass = instanceClass;
		}

		@Override
		protected @NonNull TypeDescriptor getTypeDescriptor(@NonNull CGValuedElement cgValue) {
			return super.getTypeDescriptor(cgValue).getEcoreDescriptor(codeGenerator, instanceClass);
		}
	}

	private static class UnboxedTypeRepresentation extends AbstractTypeRepresentation
	{
		public UnboxedTypeRepresentation(@NonNull JavaStream javaStream) {
			super(javaStream);
		}

		@Override
		protected @NonNull TypeDescriptor getTypeDescriptor(@NonNull CGValuedElement cgValue) {
			return super.getTypeDescriptor(cgValue).getUnboxedDescriptor(codeGenerator);
		}
	}

	protected @NonNull JavaCodeGenerator codeGenerator;
	protected @NonNull CG2JavaVisitor<@NonNull ?> cg2java;
	protected @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull Id2JavaExpressionVisitor id2JavaExpressionVisitor;
	protected final boolean useNullAnnotations;
	protected final boolean suppressNullWarnings;

	private @NonNull StringBuilder s = new StringBuilder();
	private @NonNull Stack<@NonNull String> indentationStack = new Stack<>();
	private @NonNull String defaultIndentationString = "\t";
	private @NonNull Stack<@NonNull String> classNameStack = new Stack<>();

	private final @NonNull TypeRepresentation boxedTypeRepresentation;
	private final @NonNull TypeRepresentation unboxedTypeRepresentation;

	public JavaStream(@NonNull JavaCodeGenerator codeGenerator, @NonNull CG2JavaVisitor<@NonNull ?> cg2java) {
		this.codeGenerator = codeGenerator;
		this.cg2java = cg2java;
		this.analyzer = codeGenerator.getAnalyzer();
		this.id2JavaExpressionVisitor = cg2java.createId2JavaExpressionVisitor(this);
		CodeGenOptions options = codeGenerator.getOptions();
		this.useNullAnnotations = options.useNullAnnotations();
		this.suppressNullWarnings = useNullAnnotations && options.suppressNonNullWarningsForEMFCollections();
		this.boxedTypeRepresentation = new BoxedTypeRepresentation(this);
		this.unboxedTypeRepresentation = new UnboxedTypeRepresentation(this);
	}

	public void append(@Nullable String string) {
		if (string != null) {
			if (indentationStack.isEmpty()) {
				s.append(string);
			}
			else {
				int sLength = s.length();
				boolean atStartOfLine = (sLength == 0) || (s.charAt(sLength-1) == '\n');
				for (int i = 0; i < string.length(); i++) {
					char c = string.charAt(i);
					if (c == '\r') { /* ignore */ }
					else {
						if (atStartOfLine){
							atStartOfLine = false;
							s.append(indentationStack.peek());
						}
						boolean newLine = c == '\n';
						if (newLine) {
							for (int len = s.length(); len-- > 0; ) {
								char ch = s.charAt(len);
								if ((ch != '\n') && Character.isWhitespace(ch)) {
									s.setLength(len);
								}
								else {
									break;
								}
							}
						}
						s.append(c);
						atStartOfLine = newLine;
					}
				}
			}
		}
	}

	public void appendAssignBooleanLiteral(boolean hasDeclaration, @NonNull CGValuedElement cgValue, boolean value) {
		if (!hasDeclaration) {
			appendDeclaration(cgValue);
		}
		else {
			appendValueName(cgValue);
		}
		append(" = ");
		if (cgValue.isNonNull()) {
			appendBooleanString(value);
		}
		else {
			appendClassReference(null, ValueUtil.class);
			append(".");
			append(value ? "TRUE_VALUE" : "FALSE_VALUE");
		}
		append(";\n");
	}

	public boolean appendAssignment(@NonNull CGValuedElement toVariable, @NonNull CGValuedElement cgExpression) {
		CGInvalid cgInvalidValue = cgExpression.getInvalidValue();
		if (cgInvalidValue != null) {
			append("throw ");
			//			append("(");
			//			appendClassReference(null, InvalidValueException.class);
			//			append(")");
			appendValueName(cgInvalidValue);
			append(";\n");
			return false;
		}
		else {
			TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(toVariable);
			if (!appendLocalStatements(cgExpression)) {
				return false;
			}
			appendValueName(toVariable);
			append(" = ");
			appendReferenceTo(typeDescriptor, cgExpression);
			append(";\n");
		}
		return true;
	}

	public void appendAtomicReferenceTo(@NonNull Class<?> requiredClass, @Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendAtomicReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			Class<?> actualClass = actualTypeDescriptor.getJavaClass();
			if (cgValue.getNamedValue().isCaught() || !requiredClass.isAssignableFrom(actualClass)) {
				append("((");
				appendClassReference(null, requiredClass.getName());
				append(")");
				appendValueName(cgValue);
				append(")");
			}
			else {
				appendValueName(cgValue);
			}
		}
	}

	public void appendAtomicReferenceTo(@Nullable Boolean isRequired, @NonNull Class<?> requiredClass, boolean useExtends, @Nullable CGValuedElement cgValue, @NonNull Class<?>... typeParameters) {
		if (cgValue == null) {
			append("<<null-appendAtomicReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			Class<?> actualClass = actualTypeDescriptor.getJavaClass();
			if (cgValue.getNamedValue().isCaught() || !requiredClass.isAssignableFrom(actualClass)) {
				append("((");
				appendClassReference(isRequired, requiredClass, useExtends, typeParameters);
				append(")");
				appendValueName(cgValue);
				append(")");
			}
			else {
				appendValueName(cgValue);
			}
		}
	}

	public void appendAtomicReferenceTo(@NonNull TypeDescriptor requiredTypeDescriptor, @Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendAtomicReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			if (cgValue.getNamedValue().isCaught() || !requiredTypeDescriptor.isAssignableFrom(actualTypeDescriptor)) {
				append("(");
				SubStream castBody = new SubStream() {
					@Override
					public void append() {
						appendValueName(cgValue);
					}
				};
				requiredTypeDescriptor.appendCast(this, null, null, castBody);
				append(")");
			}
			else {
				appendValueName(cgValue);
			}
		}
	}

	public void appendAtomicReferenceTo(@Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendAtomicReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			if (cgValue.getNamedValue().isCaught()) {
				append("(");
				SubStream castBody = new SubStream() {
					@Override
					public void append() {
						appendValueName(cgValue);
					}
				};
				actualTypeDescriptor.appendCast(this, null, null, castBody);
				append(")");
			}
			else {
				appendValueName(cgValue);
			}
		}
	}

	public void appendBooleanString(boolean isTrue) {
		append(isTrue ? "true" : "false");
	}

	public void appendBooleanValueName(@NonNull CGValuedElement cgValue, boolean isTrue) {
		@NonNull TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
		if (!cgValue.isNonNull() || cgValue.isCaught() || cgValue.getNamedValue().isCaught() || (typeDescriptor.getJavaClass() == Object.class)) {
			appendValueName(cgValue);
			append(" == ");
			append(isTrue ? "Boolean.TRUE" : "Boolean.FALSE");
		}
		else {
			if (!isTrue) {
				append("!");
			}
			appendValueName(cgValue);
		}
	}

	@Deprecated /* @deprecated pass actualJavaClass so that redundant casts are suppressed */
	public void appendClassCast(@Nullable CGValuedElement cgValue, @NonNull SubStream subStream) {
		if (cgValue == null) {
			append("<<null-appendClassCast>>");
		}
		else {
			TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			Boolean isRequired = codeGenerator.isRequired(cgValue);
			typeDescriptor.appendCast(this, isRequired, null, subStream);
		}
	}

	public void appendClassCast(@NonNull CGValuedElement cgValue, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream) {
		@NonNull TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
		Class<?> requiredJavaClass = typeDescriptor.getJavaClass();
		if ((actualJavaClass == null) || !requiredJavaClass.isAssignableFrom(actualJavaClass)) {
			typeDescriptor.appendCast(this, isRequired, actualJavaClass, subStream);
		}
		else {
			subStream.append();
		}
	}

	public void appendClassHeader(@Nullable CGPackage cgPackage) {
		appendCopyrightHeader();
		if (cgPackage != null) {
			append("package ");
			appendClassHeaderInternal(cgPackage);
			append(";\n");
		}
		append("\n");
		append(ImportUtils.IMPORTS_MARKER + "\n");
	}
	private void appendClassHeaderInternal(@NonNull CGPackage cgPackage) {
		CGPackage cgParentPackage = cgPackage.getContainingPackage();
		if (cgParentPackage != null) {
			appendClassHeaderInternal(cgParentPackage);
			append(".");
		}
		append(String.valueOf(cgPackage.getName()));
	}

	public void appendClassReference(@Nullable Boolean isRequired, @Nullable CGValuedElement cgValue) {
		boxedTypeRepresentation.appendClassReference(isRequired, cgValue);
	}

	public void appendClassReference(@Nullable Boolean isRequired, @Nullable CGValuedElement cgValue, @Nullable TypeDescriptor typeDescriptor) {
		if (cgValue == null) {
			append("<<null->>");
		}
		else if (cgValue.getNamedValue().isCaught()) {
			appendClassReference(isRequired, Object.class);
		}
		else {
			if (typeDescriptor == null) {
				typeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			}
			if ((cgValue instanceof CGParameter) && (cgValue.eContainer() instanceof CGOperation) && (typeDescriptor instanceof EObjectDescriptor)) {		// FIXME eliminate reclassing
				Class<?> originalJavaClass = ((EObjectDescriptor)typeDescriptor).getOriginalJavaClass();
				appendClassReference(isRequired, originalJavaClass);
			}
			else {
				typeDescriptor.append(this, isRequired);
			}
		}
	}

	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@Nullable Class<?> javaClass) {
		appendClassReference(null, javaClass);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @Nullable Class<?> javaClass) {
		if (javaClass != null) {
			if (JavaCodeGenerator.javaPrimitiveClasses.containsKey(javaClass)) {
				append(javaClass.getName());
			}
			else {
				appendClassReference(isRequired, javaClass.getName());
				TypeVariable<?>[] typeParameters = javaClass.getTypeParameters();
				if (typeParameters.length > 0) {
					append("<");
					for (int i = 0; i < typeParameters.length; i++) {
						if (i != 0) {
							append(",");
						}
						append("?");
					}
					append(">");
				}
			}
		}
		else {
			if (isRequired != null) {
				appendIsRequired(isRequired);
				append(" ");
			}
			appendClassReference(null, Object.class);
		}
	}

	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@NonNull TypeDescriptor typeDescriptor) {
		appendClassReference(null, typeDescriptor);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @NonNull TypeDescriptor typeDescriptor) {
		typeDescriptor.append(this, isRequired);
	}

	@Deprecated /* @deprecated not used, provide isRequired, useExtends arguments */
	public void appendClassReference(@Nullable Class<?> javaClass, @NonNull Class<?>... typeParameters) {
		appendClassReference(null, javaClass, false, typeParameters);
	}
	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@Nullable Class<?> javaClass, boolean useExtends, @NonNull Class<?>... typeParameters) {
		appendClassReference(null, javaClass, useExtends, typeParameters);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @Nullable Class<?> javaClass, boolean useExtends, @NonNull Class<?>... typeParameters) {
		if (javaClass != null) {
			appendClassReference(isRequired, javaClass.getName());
			appendTypeParameters(useExtends, typeParameters);
		}
		else {
			appendClassReference(isRequired, Object.class);
		}
	}

	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@Nullable Class<?> javaClass, boolean useExtends, @NonNull String... typeParameters) {
		appendClassReference(null, javaClass, useExtends, typeParameters);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @Nullable Class<?> javaClass, boolean useExtends, @NonNull String... typeParameters) {
		if (javaClass != null) {
			appendClassReference(isRequired, javaClass.getName());
			appendTypeParameters(useExtends, typeParameters);
		}
		else {
			appendClassReference(isRequired, Object.class);
		}
	}

	/*	public void appendClassReference(@Nullable String className, boolean useExtends, @NonNull Class<?>... typeParameters) {
		if (className != null) {
			appendClassReference(className);
			appendTypeParameters(useExtends, typeParameters);
		}
		else {
			appendClassReference(Object.class);
		}
	} */

	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@Nullable Class<?> javaClass, boolean useExtends, @NonNull TypeDescriptor ... typeDescriptors) {
		appendClassReference(null, javaClass, useExtends, typeDescriptors);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @Nullable Class<?> javaClass, boolean useExtends, @NonNull TypeDescriptor ... typeDescriptors) {
		if (javaClass != null) {
			appendClassReference(isRequired, javaClass.getName());
			if (typeDescriptors != null) {
				append("<");
				for (int i = 0; i < typeDescriptors.length; i++) {
					if (i != 0) {
						append(",");
					}
					if (useExtends) {
						append("? extends ");
					}
					TypeDescriptor typeDescriptor = typeDescriptors[i];
					typeDescriptor.append(this, null);
					//					Class<?> javaClass2 = typeDescriptor.getJavaClass();
					//					if ((javaClass2 != null) && (javaClass2 != Object.class)) {
					//						appendClassReference(javaClass2, new Class<?>[]{});
					//					}
					//					else {
					//						appendClassReference(typeDescriptor.getClassName());
					//					}
				}
				append(">");
			}
		}
		else {
			appendClassReference(null, Object.class);
		}
	}

	@Deprecated /* @deprecated not used, provide isRequired argument */
	public void appendClassReference(@Nullable String className) {
		appendClassReference(null, className);
	}
	public void appendClassReference(@Nullable Boolean isRequired, @Nullable String className) {
		assert className != null;
		append(cg2java.addImport(useNullAnnotations ? isRequired : null, className));
	}

	public void appendClassReference(@NonNull CGClass cgClass) {
		StringBuilder s = new StringBuilder();
		CGPackage cgPackage = cgClass.getContainingPackage();
		if ((cgPackage != null) && (cgPackage.getName() != null)) {
			appendQualifyingPackage(s, cgPackage);
			s.append(cgClass.getName());
			appendClassReference(null, s.toString());
			List<CGClass> cgTemplateParameters = cgClass.getTemplateParameters();
			if (cgTemplateParameters.size() > 0) {
				append("<");
				boolean isFirst = true;
				for (CGClass cgTemplateParameter : cgTemplateParameters) {
					if (!isFirst) {
						append(", ");
					}
					if (cgTemplateParameter != null) {
						appendClassReference(cgTemplateParameter);
					}
					else {
						append("?");
					}
					isFirst = false;
				}
				append(">");
			}
		}
		else {
			append(cgClass.getName());		// the ? wildcard
		}
	}

	public void appendCommentWithOCL(@Nullable String title, @Nullable Element element) {
		append("/**\n");
		pushIndentation(" * ");
		if (title != null) {
			append(title + "\n");
		}
		if (element != null) {
			PrettyPrintOptions.Global createOptions = createOptions(element);
			append(PrettyPrinter.print(element, createOptions).replace("*/",  "* /") + "\n");
			//			append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
		}
		popIndentation();
		append(" */\n");
	}

	public void appendCopyrightHeader() {
		append("/*******************************************************************************\n");
		pushIndentation(" *");
		append(" «codeGenHelper.getCopyright(' * ')»\n");
		append("\n");
		append(" This code is 100% auto-generated\n");
		append(" using: " + getCodeGenerator().getClass().getName() + "\n");
		append("\n");
		append(" Do not edit it.\n");
		append("*******************************************************************************/\n");
		popIndentation();
		append("\n");
	}

	public void appendDeclaration(@NonNull CGValuedElement cgElement) {
	//	TypeDescriptor javaTypeDescriptor = codeGenerator.getTypeDescriptor(cgElement);
		TypeRepresentation typeRepresentation = boxedTypeRepresentation;
		if (cgElement.isEcore()) {
			typeRepresentation = boxedTypeRepresentation;
		}
		typeRepresentation.appendDeclaration(cgElement);
	//	typeRepresentation.appendTypeDeclaration(cgElement);
	//	javaTypeDescriptor.append
	}

	/**
	 * Append the value of cgValue, ensuring that it has the returnClassname type.
	 */
	public void appendEcoreValue(@NonNull String returnClassName, @NonNull CGValuedElement cgValue) {
		TypeDescriptor javaTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
		Class<?> javaClass = javaTypeDescriptor.hasJavaClass();
		if (cgValue.getNamedValue() instanceof CGParameter) {
			appendValueName(cgValue);
		}
		else if (javaClass == null) {
			append("(");
			appendClassReference(null, returnClassName);
			append(")");
			appendValueName(cgValue);
		}
		else if (!returnClassName.equals(javaClass.getName())) {
			if (javaClass == Boolean.class) {
				appendValueName(cgValue);
				//				if ("boolean".equals(returnClassName) || "java.lang.Boolean".equals(returnClassName)) {
				//					append(".booleanValue()");
				//				}
			}
			else if (javaClass == Number.class) {						// Real or Integer or UnlimitedNatural (source isn't a Character but target may be)
				if ("java.math.BigDecimal".equals(returnClassName)) {
					appendClassReference(null, ValueUtil.class);
					append(".bigDecimalValueOf(");
					appendValueName(cgValue);
					append(")");
				}
				else if ("java.math.BigInteger".equals(returnClassName)) {
					appendClassReference(null, ValueUtil.class);
					append(".bigIntegerValueOf(");
					appendValueName(cgValue);
					append(")");
				}
				else if ("char".equals(returnClassName)) {
					append("(char)");
					appendAtomicReferenceTo(cgValue);
					append(".intValue()");
				}
				else if ("java.lang.Character".equals(returnClassName)) {
					appendClassReference(null, Character.class);
					append(".valueOf((char)");
					appendAtomicReferenceTo(cgValue);
					append(".intValue())");
				}
				else if ("java.lang.Double".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".doubleValue()");
				}
				else if ("java.lang.Float".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".floatValue()");
				}
				else if ("java.lang.Integer".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".intValue()");
				}
				else if ("java.lang.Long".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".longValue()");
				}
				else if ("java.lang.Short".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".shortValue()");
				}
				else if ("double".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".doubleValue()");
				}
				else if ("float".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".floatValue()");
				}
				else if ("int".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".intValue()");
				}
				else if ("long".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".longValue()");
				}
				else if ("short".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".shortValue()");
				}
				else {
					appendValueName(cgValue);
				}
			}
			else if ((javaClass == Object.class)						// Integer or UnlimitedNatural (source isn't a Real)
					|| (javaClass == byte.class)
					|| (javaClass == char.class)
					|| (javaClass == int.class)
					|| (javaClass == long.class)
					|| (javaClass == short.class)) {
				if ("java.math.BigDecimal".equals(returnClassName)) {
					appendClassReference(null, ValueUtil.class);
					append(".bigDecimalValueOf(");
					appendValueName(cgValue);
					append(")");
				}
				else if ("java.math.BigInteger".equals(returnClassName)) {
					appendClassReference(null, ValueUtil.class);
					append(".bigIntegerValueOf(");
					appendValueName(cgValue);
					append(")");
				}
				else if ("char".equals(returnClassName) || "java.lang.Character".equals(returnClassName)) {
					appendClassReference(null, ValueUtil.class);
					append(".characterValueOf(");
					appendValueName(cgValue);
					append(")");
				}
				else if ("java.lang.Double".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".doubleValue()");
				}
				else if ("java.lang.Float".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".floatValue()");
				}
				else if ("java.lang.Integer".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".intValue()");
				}
				else if ("java.lang.Long".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".longValue()");
				}
				else if ("java.lang.Short".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".shortValue()");
				}
				else if ("double".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".doubleValue()");
				}
				else if ("float".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".floatValue()");
				}
				else if ("int".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".intValue()");
				}
				else if ("long".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".longValue()");
				}
				else if ("short".equals(returnClassName)) {
					appendAtomicReferenceTo(Number.class, cgValue);
					append(".shortValue()");
				}
				else {
					appendValueName(cgValue);
				}
			}
			else {
				append("(");
				appendClassReference(null, returnClassName);
				append(")");
				appendValueName(cgValue);
			}
		}
		else {
			appendValueName(cgValue);
		}
	}

	public void appendEqualsBoolean(@NonNull CGValuedElement cgValue, boolean value) {
		if (cgValue.isNonNull() && cgValue.isNonInvalid()) {
			if (!value) {
				append("!");
			}
			appendValueName(cgValue);
		}
		else {
			appendValueName(cgValue);
			append(" == ");
			appendClassReference(null, ValueUtil.class);
			append(".");
			append(value ? "TRUE_VALUE" : "FALSE_VALUE");
		}
	}

	public void appendFalse() {
		appendClassReference(null, ValueUtil.class);
		append(".FALSE_VALUE");
	}

	public void appendIdReference(@Nullable ElementId elementId) {
		if (elementId == null) {
			append("<<null-appendIdReference>>");
		}
		else if (CGUtil.isInlinedId(elementId)) {
			elementId.accept(id2JavaExpressionVisitor);
		}
		else {
			appendValueName(analyzer.getElementId(elementId));
		}
	}

	public void appendIdReference2(@NonNull ElementId elementId) {
		elementId.accept(id2JavaExpressionVisitor);
	}

	public void appendIntegerString(int value) {
		append(Integer.toString(value));
	}

	public void appendIsCaught(boolean isNonInvalid, boolean isCaught) {
		append("/*");
		append(isNonInvalid ? "@NonInvalid" : isCaught ? "@Caught" : "@Thrown");
		append("*/");
	}

	public void appendIsRequired(boolean isRequired) {
		if (!useNullAnnotations) {
			append("/*");
			append(isRequired ? "@NonNull" : "@Nullable");
			append("*/");
		}
		else {
			append("@");
			appendClassReference(null, isRequired ? AbstractCodeGenerator.ORG_ECLIPSE_JDT_ANNOTATION_NON_NULL
				: AbstractCodeGenerator.ORG_ECLIPSE_JDT_ANNOTATION_NULLABLE);
		}
	}

	/**
	 * Append the complete statements for cgElement for use with in a local operation context.
	 * Inline and global contributions are excluded.
	 */
	public boolean appendLocalStatements(@NonNull CGValuedElement cgElement) {
		//		if (!cgElement.isInlineable() && !cgElement.isConstant() && !cgElement.isGlobal()) {			// Exclude global constants and inline constants
		if (!cgElement.isInlined()			// Exclude inline constants
				&& !cgElement.isGlobal()) {			// Exclude global constant expressions
			return cgElement.accept(cg2java) == Boolean.TRUE;
		}
		return true;
	}

	public void appendNotEqualsBoolean(@NonNull CGValuedElement cgValue, boolean value) {
		if (cgValue.isNonNull() && cgValue.isNonInvalid()) {
			if (value) {
				append("!");
			}
			appendValueName(cgValue);
		}
		else {
			appendValueName(cgValue);
			append(" != ");
			appendClassReference(null, ValueUtil.class);
			append(".");
			append(value ? "TRUE_VALUE" : "FALSE_VALUE");
		}
	}

	public void appendQualifiedLiteralName(@NonNull Operation anOperation) {
		org.eclipse.ocl.pivot.Class type = anOperation.getOwningClass();
		if (type != null) {
			GenModelHelper genModelHelper = codeGenerator.getGenModelHelper();
			GenPackage genPackage = genModelHelper.getGenPackage(type);
			if (genPackage != null) {
				String qualifiedPackageName = genPackage.getQualifiedPackageName() + AbstractGenModelHelper.TABLES_PACKAGE_NAME;
				String tablesClassName = genPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
				appendClassReference(null, qualifiedPackageName + "." + tablesClassName);
				append("." + AbstractGenModelHelper.OPERATIONS_PACKAGE_NAME + "._" + type.getName() + "__" + AbstractGenModelHelper.encodeName(anOperation));
			}
		}
	}

	private void appendQualifyingPackage(@NonNull StringBuilder s, @NonNull CGPackage cgPackage) {
		CGPackage cgParentPackage = cgPackage.getContainingPackage();
		if (cgParentPackage != null) {
			appendQualifyingPackage(s, cgParentPackage);
		}
		String packageName = cgPackage.getName();
		if (packageName != null) {
			s.append(packageName);
			s.append(".");
		}
	}

	public void appendReferenceTo(@NonNull Class<?> requiredClass, @Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			if (cgValue.getNamedValue().isCaught() || !actualTypeDescriptor.isAssignableTo(requiredClass)) {
				append("(");
				appendClassReference(null, requiredClass.getName());
				append(")");
			}
			appendValueName(cgValue);
		}
	}

	public void appendReferenceTo(@NonNull TypeDescriptor requiredTypeDescriptor, @Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendReferenceTo>>");
		}
		else {
			TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			if (!cgValue.isNull()) {
				boolean isCaught = cgValue.getNamedValue().isCaught();
				if (isCaught || !requiredTypeDescriptor.isAssignableFrom(actualTypeDescriptor)) {
					Boolean isRequired = null;
					SubStream castBody = new SubStream() {
						@Override
						public void append() {
							appendValueName(cgValue);
						}
					};
					requiredTypeDescriptor.appendCast(this, isRequired, isCaught ? null : actualTypeDescriptor.getJavaClass(), castBody);
					return;
				}
				else if (requiredTypeDescriptor.isPrimitive()) { // && cgValue.isConstant() && (cgValue.getTypeId() == TypeId.BOOLEAN)) {
					if (cgValue.isTrue()) {
						append("true");
						return;
					}
					else if (cgValue.isFalse()) {
						append("false");
						return;
					}
				}
			}
			appendValueName(cgValue);
		}
	}

	public void appendReferenceTo(@Nullable CGValuedElement cgValue) {
		if (cgValue == null) {
			append("<<null-appendReferenceTo>>");
		}
		else {
			if (cgValue.getNamedValue().isCaught()) {
				TypeDescriptor actualTypeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
				append("(");
				actualTypeDescriptor.append(this, null);
				append(")");
			}
			appendValueName(cgValue);
		}
	}

	public void appendString(@NonNull String string) {
		@SuppressWarnings("null")@NonNull String javaString = Strings.convertToJavaString(string);
		append("\"");
		append(javaString);
		append("\"");
	}

	public boolean appendSuppressWarningsNull(boolean newLine) {
		if (!isUseNullAnnotations()) {
			return false;
		}
		append("@SuppressWarnings(\"null\")");
		if (newLine) {
			append("\n");
		}
		return true;
	}

	@Deprecated /* @deprecated this is QVTi specific */
	public void appendThis(@NonNull String className) {
		String currentClassName = classNameStack.peek();
		if (!className.equals(currentClassName)) {
			append(className);
			append(".");
		}
		append("this");
	}

	public boolean appendThrowBooleanInvalidValueException(/*@NonNull*/ String message, @NonNull String... arguments) {
		appendClassReference(null, ValueUtil.class);
		append(".throwBooleanInvalidValueException(");
		appendString(ClassUtil.nonNullState(message));
		for (String argument : arguments) {
			append(", ");
			appendString(ClassUtil.nonNullState(argument));
		}
		append(");\n");
		return false;
	}

	public boolean appendThrowInvalidValueException(/*@NonNull*/ String message, @NonNull String... arguments) {
		append("throw new ");
		appendClassReference(null, InvalidValueException.class);
		append("(");
		appendString(ClassUtil.nonNullState(message));
		for (String argument : arguments) {
			append(", ");
			appendString(ClassUtil.nonNullState(argument));
		}
		append(");\n");
		return false;
	}

	public void appendTrue() {
		appendClassReference(null, ValueUtil.class);
		append(".TRUE_VALUE");
	}

	public void appendTypeDeclaration(@NonNull CGValuedElement cgElement) {
		boxedTypeRepresentation.appendTypeDeclaration(cgElement);;
	}

	public void appendTypeParameters(boolean useExtends, @NonNull Class<?>... typeParameters) {
		if (typeParameters.length > 0) {
			append("<");
			for (int i = 0; i < typeParameters.length; i++) {
				if (i != 0) {
					append(",");
				}
				if (useExtends) {
					append("? extends ");
				}
				appendClassReference(null, typeParameters[i]);
			}
			append(">");
		}
	}

	public void appendTypeParameters(boolean useExtends, @NonNull String... typeParameters) {
		if (typeParameters.length > 0) {
			append("<");
			for (int i = 0; i < typeParameters.length; i++) {
				if (i != 0) {
					append(",");
				}
				if (useExtends) {
					append("? extends ");
				}
				appendClassReference(null, typeParameters[i]);
			}
			append(">");
		}
	}

	/**
	 * Append the code name for the value of cgElement, lazily creating one if necessary.
	 */
	public void appendValueName(@Nullable CGValuedElement cgElement) {
		if (cgElement == null) {
			append("<<null-appendValueName>>");
		}
		else if (cgElement.isInlined()) {
			if (cgElement instanceof CGVariableExp) {			// FIXME without this we get a blank name for a VariableExp
				appendValueName(cgElement.getNamedValue());
			}
			else {
				cgElement.accept(cg2java);
			}
		}
		//		else if (cgElement.isInlined() && (cgElement.isInvalid() || cgElement.isNull() || cgElement.isTrue() || cgElement.isFalse() || !cgElement.isGlobal())) {	// FIXME
		//			CGValuedElement cgValue = cgElement;
		//			for (CGValuedElement cgNext; (cgNext = cgValue.getReferredValuedElement()) != cgValue; cgValue = cgNext) {}
		//			cgValue.accept(cg2java);
		//		}
		else if ("this".equals(cgElement.toString())) {		// FIXME use a CGThisVariable that is inlined
			appendThis(cg2java.getThisName(cgElement));		// FIXME Move to QVTiCG
		}
		else {
			if (cgElement.isGlobal()) {
				cg2java.appendGlobalPrefix();
			}
			String valueName = cg2java.getValueName(cgElement);
			append(valueName);
		}
	}

	public @NonNull TypeRepresentation getBoxedTypeRepresentation() {
		return boxedTypeRepresentation;
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	public @NonNull TypeRepresentation getEcoreTypeRepresentation(@Nullable Class<?> instanceClass) {
		return new EcoreTypeRepresentation(this, instanceClass);
	}

	public @Nullable GenPackage getGenPackage() {
		return cg2java.getGenPackage();
	}

	public @NonNull TypeRepresentation getUnboxedTypeRepresentation() {
		return unboxedTypeRepresentation;
	}

	protected @NonNull String getValueName(@NonNull CGValuedElement cgElement) {
		String name = cgElement.getValueName();
		if (name == null) {
			name = cgElement.getName();
		}
		if (name == null) {
			name = "<null-" + cgElement.eClass().getName() + ">";
		}
		return name;
	}

	/** @deprecated use isPrimitive() */
	@Deprecated
	public boolean is_boolean(@NonNull CGValuedElement cgValue) {
		if (cgValue.getNamedValue().isCaught()) {
			return false;
		}
		else {
			TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgValue);
			Class<?> javaClass = typeDescriptor.getJavaClass();
			return (javaClass == boolean.class) || ((javaClass == Boolean.class) && cgValue.isNonNull());
		}
	}

	/**
	 * Return true is this is a built-in primitive type such as boolean or int.
	 * Such types cannot have @NonNull annotations.
	 */
	public boolean isPrimitive(@NonNull CGValuedElement cgValue) {
		return codeGenerator.isPrimitive(cgValue);
	}

	public boolean isUseNullAnnotations() {
		return useNullAnnotations;
	}

	public int length() {
		return s.length();
	}

	public @Nullable String peekClassNameStack() {
		return classNameStack.isEmpty() ? null : classNameStack.peek();
	}

	public void popClassBody(boolean isAnonymous) {
		popIndentation();
		append("}");
		if (isAnonymous) {
			append(";");
		}
		append("\n");
		classNameStack.pop();
	}

	public void popIndentation() {
		indentationStack.pop();
	}

	public void pushClassBody(@NonNull String className) {
		classNameStack.push(className);
		append("\n");
		append("{\n");
		pushIndentation(null);
	}

	public void pushIndentation(@Nullable String extraIndentation) {
		if (extraIndentation == null) {
			extraIndentation = defaultIndentationString;
		}
		if (indentationStack.isEmpty()) {
			indentationStack.push(extraIndentation);
		}
		else {
			indentationStack.push(indentationStack.peek() + extraIndentation);
		}
	}

	public void resetStream() {
		s.setLength(0);
	}

	@Override
	public @NonNull String toString() {
		return s.toString();
	}
}
