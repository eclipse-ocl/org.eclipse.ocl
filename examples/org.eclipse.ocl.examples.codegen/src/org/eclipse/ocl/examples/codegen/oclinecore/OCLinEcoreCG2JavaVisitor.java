/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreCodeGenerator.FeatureBody;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreCodeGenerator.FeatureLocality;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TemplateableId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.AbstractStaticOperation;
import org.eclipse.ocl.pivot.internal.library.AbstractStaticProperty;
import org.eclipse.ocl.pivot.internal.library.ForeignOperation;
import org.eclipse.ocl.pivot.internal.library.ForeignProperty;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * An OCLinEcoreCG2JavaVisitor supports generation of the OCL embedded in an Ecore model
 * into the Java bodies of the code producxed by GenModel.
 */
public class OCLinEcoreCG2JavaVisitor extends CG2JavaVisitor<@NonNull OCLinEcoreCodeGenerator>
{
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull CGPackage cgPackage;
	protected ExpressionInOCL expInOcl;
	protected Feature feature;

	public OCLinEcoreCG2JavaVisitor(@NonNull OCLinEcoreCodeGenerator codeGenerator,
			@NonNull GenPackage genPackage, @NonNull CGPackage cgPackage) {
		super(codeGenerator);
		this.genPackage = genPackage;
		this.cgPackage = cgPackage;
	}

	@Override
	protected void appendGlobalPrefix() {
		js.append(getGlobalContext().getTablesClassName());
		js.append(".");
	}

	protected @Nullable Boolean doGetSeverity(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		// OCL, OCL-CG has no support for EOperation litetals so the original validation check severity predicate used a magic String.
		// Now we recognise the old-style call and re-implement as a check on the EOperation litetal that we can compute anyway.
		CGConstraint cgConstraint = CGUtil.getContainingConstraint(cgOperationCallExp);
		if (cgConstraint != null) {
			Constraint asConstraint = CGUtil.getAST(cgConstraint);
			EObject eOperation = asConstraint.getESObject();
			CGClass cgClass = CGUtil.getContainingClass(cgConstraint);
			if (cgClass != null) {
				org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
				GenModelHelper genModelHelper = context.getGenModelHelper();
				GenClassifier genClassifier = genModelHelper.getGenClassifier(asClass);
				if (genClassifier instanceof GenClass) {
					GenClass genClass = (GenClass)genClassifier;
					for (GenOperation genOperation : genClass.getGenOperations()) {
						if (genOperation.getEcoreOperation() == eOperation) {
							js.appendDeclaration(cgOperationCallExp);
							js.append(" = ");
							js.appendClassReference(null, CGStringGetSeverityOperation.class);
							js.append(".");
							js.append(globalContext.getInstanceName());
							js.append(".");
							js.append(globalContext.getEvaluateName());
							js.append("(");
							js.append(globalContext.getExecutorName());
							js.append(", ");
							js.appendClassReference(null, genClassifier.getGenPackage().getQualifiedPackageInterfaceName());
							js.append(".Literals.");
							js.append(genClass.getOperationID(genOperation, false));
							js.append(");\n");
							return Boolean.TRUE;
						}
					}
				}
			}
		}
		return null;
	}

	public @NonNull Map<@NonNull String, @NonNull FeatureBody> generateBodies() {
		Map<@NonNull String, @NonNull FeatureBody> bodies = new HashMap<>();
		for (CGClass cgClass : cgPackage.getClasses()) {
			for (CGConstraint cgConstraint : cgClass.getInvariants()) {
				CGValuedElement cgBody = cgConstraint.getBody();
				org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
				Element asElement = cgConstraint.getAst();
				if ((cgBody != null) && (asElement instanceof Constraint)) {
					Constraint asConstraint = (Constraint) asElement;
					localContext = globalContext.basicGetLocalContext(cgConstraint);
					String bodyText = generateValidatorBody(cgBody, asConstraint, asClass);
					String fragmentURI = getFragmentURI(asClass) + "==" + getRuleName(asConstraint);
					String foreignPackageName = genPackage.getReflectionPackageName();//getGlobalContext().getTablesClassName();
					String foreignClassName = context.getForeignClassName(asClass);
					bodies.put(fragmentURI, new FeatureBody(fragmentURI, asConstraint, FeatureLocality.ECORE_IMPL, foreignPackageName,foreignClassName, bodyText));
				}
			}
			for (@NonNull CGOperation cgOperation : ClassUtil.nullFree(cgClass.getOperations())) {
				CGValuedElement cgBody = cgOperation.getBody();
				if (cgBody != null) {
					FeatureBody body = generateOperationBody(cgOperation, cgBody);
					bodies.put(body.getURI(), body);
				}
			}
			for (CGProperty cgProperty : cgClass.getProperties()) {
				CGValuedElement cgBody = cgProperty.getBody();
				if (cgBody != null) {
					FeatureBody body = generatePropertyBody(cgProperty, cgBody);
					bodies.put(body.getURI(), body);
				}
			}
		}
		localContext = null;
		return bodies;
	}

	protected @NonNull String generateBody(@Nullable List<CGParameter> cgParameters, @NonNull CGValuedElement cgBody, @NonNull String returnClassName) {
		js.resetStream();
		js.appendCommentWithOCL(null, cgBody.getAst());
		if (cgParameters != null) {
			for (@SuppressWarnings("null")@NonNull CGParameter cgParameter : cgParameters) {
				VariableDeclaration asParameter = CGUtil.getAST(cgParameter);
				Type asType = PivotUtil.getType(asParameter);
				if (asType instanceof CollectionType) {
					js.append("assert ");
					js.appendValueName(cgParameter);
					js.append(" != null;\n");
				}
			}
		}
		js.appendLocalStatements(cgBody);
		CGInvalid cgInvalidValue = cgBody.getInvalidValue();
		if (cgInvalidValue  != null) {
			js.append("throw ");
			js.appendValueName(cgInvalidValue);
		}
		else {
			TypeDescriptor typeDescriptor = context.getTypeDescriptor(cgBody);
			//			String className = typeDescriptor.getClassName();
			//			Class<?> javaClass = typeDescriptor.getJavaClass();
			js.append("return ");
			//			if (returnClassName.contains("<")) {
			//				js.append("(" + returnClassName + ")");
			//			}
			//			js.appendValueName(cgBody);
			typeDescriptor.appendEcoreValue(js, returnClassName, cgBody);
		}
		js.append(";");
		return toString();
	}

	public @NonNull String generateConstants(Iterable<@NonNull CGValuedElement> sortedGlobals) {
		js.resetStream();
		js.pushIndentation(null);
		if (sortedGlobals != null) {
			generateGlobals(sortedGlobals);
		}
		return toString();
	}

	protected @NonNull FeatureBody generateOperationBody(@NonNull CGOperation cgOperation, @NonNull CGValuedElement cgBody) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		assert asOperation != null;
		Class asClass = PivotUtil.getOwningClass(asOperation);
		FeatureLocality featureLocality = getFeatureLocality(asOperation);
		String packageName;
		String className;
		String bodyText;
		if (featureLocality == FeatureLocality.FOREIGN_STATIC) {
			localContext = globalContext.basicGetLocalContext(cgOperation);
			js.resetStream();
		//	js.appendCommentWithOCL(null, cgOperation.getAst());
			cgOperation.accept(this);
			bodyText = toString();
			packageName = genPackage.getReflectionPackageName();
			className = context.getForeignClassName(asClass);
		}
		else if (featureLocality == FeatureLocality.FOREIGN_IMPL) {
			localContext = globalContext.basicGetLocalContext(cgOperation);
			js.resetStream();
			js.appendCommentWithOCL(null, cgOperation.getAst());
			cgOperation.accept(this);
			bodyText = toString();
			packageName = genPackage.getReflectionPackageName();
			className = context.getForeignClassName(asClass);
		}
		else if (featureLocality == FeatureLocality.ECORE_IMPL) {
			featureLocality = FeatureLocality.ECORE_IMPL;
			String returnClassName = genModelHelper.getOperationReturnType(asOperation);
			localContext = globalContext.basicGetLocalContext(cgOperation);
			bodyText = generateBody(cgOperation.getParameters(), cgBody, returnClassName);
			packageName = genPackage.getReflectionPackageName();//getGlobalContext().getTablesClassName();
			className = context.getForeignClassName(asClass);
		}
		else {
			assert false;
			packageName = "xyzzy";
			className = "xyzzy";
			bodyText = "xyzzy";
		}
		String fragmentURI = getFragmentURI(asOperation);
		assert packageName != null;		// XXX
		return new FeatureBody(fragmentURI, asOperation, featureLocality, packageName, className, bodyText);
	}

	protected @NonNull FeatureBody generatePropertyBody(@NonNull CGProperty cgProperty, @NonNull CGValuedElement cgBody) {
		Property asProperty = CGUtil.getAST(cgProperty);
		Class asClass = PivotUtil.getOwningClass(asProperty);
		FeatureLocality featureLocality = getFeatureLocality(asProperty);
		String packageName;
		String className;
		String bodyText;
		if (featureLocality == FeatureLocality.FOREIGN_IMPL) {
			localContext = globalContext.basicGetLocalContext(cgProperty);
			js.resetStream();
			js.appendCommentWithOCL(null, cgProperty.getAst());
			cgProperty.accept(this);
			bodyText = toString();
			packageName = genPackage.getReflectionPackageName();
			className = context.getForeignClassName(asClass);
		}
		else if (featureLocality == FeatureLocality.FOREIGN_STATIC) {
			localContext = globalContext.basicGetLocalContext(cgProperty);
			js.resetStream();
			js.appendCommentWithOCL(null, cgProperty.getAst());
			cgProperty.accept(this);
			bodyText = toString();
			packageName = genPackage.getReflectionPackageName();
			className = context.getForeignClassName(asClass);
		}
		else if (featureLocality == FeatureLocality.ECORE_IMPL) {
			localContext = globalContext.getLocalContext(cgProperty);
			String returnClassName = genModelHelper.getPropertyResultType(asProperty);
			bodyText = generateBody(null, cgBody, returnClassName);
			packageName = genPackage.getReflectionClassPackageName();
			GenClass genClass = (GenClass)genModelHelper.getGenClassifier(asClass);
			assert genClass != null;
			className = genClass.getClassName();
		}
		else {
			assert false;
			packageName = "xyzzy";
			className = "xyzzy";
			bodyText = "xyzzy";
		}
		assert packageName != null;
		assert className != null;
		String fragmentURI = getFragmentURI(asProperty);
		return new FeatureBody(fragmentURI, asProperty, featureLocality, packageName, className, bodyText);
	}

	protected @NonNull FeatureBody generateStaticOperation(@NonNull CGOperation cgOperation) {
		localContext = globalContext.getLocalContext(cgOperation);
		js.resetStream();
		//
		Operation asOperation = CGUtil.getAST(cgOperation);
		assert asOperation != null;
		assert asOperation.isIsStatic();
		String className = "SO_" + context.getForeignClassName(PivotUtil.getOwningClass(asOperation));
		List<CGParameter> cgParameters = cgOperation.getParameters();
		LanguageExpression expressionInOCL = asOperation.getBodyExpression();
		CGValuedElement cgBody = cgOperation.getBody();
		assert cgBody != null;
		String title = PrettyPrinter.printName(asOperation);
		js.append("\n");
		js.append("/**\n");
		js.append(" *\t");
		js.append(title);
		js.append("\n");
		js.append(" */\n");
		js.append("public static class " + className + " extends ");
		js.appendClassReference(null, AbstractStaticOperation.class);
		js.pushClassBody(className);

		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(className);
		js.append(" ");
		js.append(globalContext.getInstanceName());
		js.append(" = new ");
		js.append(className);
		js.append("();\n");
		js.append("\n");

		js.appendCommentWithOCL(null, expressionInOCL);
	//	js.append("@Override\n");
		js.append("public ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.append(globalContext.getEvaluateName());
		js.append("(");
		boolean isFirst = true;
		for (@SuppressWarnings("null")@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		appendReturn(cgBody);
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(false);
		assert js.peekClassNameStack() == null;
		String bodyText = toString();
		String fragmentURI = getFragmentURI(asOperation);
		String foreignPackageName = getGlobalContext().getTablesClassName();
		FeatureLocality featureLocality = FeatureLocality.FOREIGN_STATIC;
		return new FeatureBody(fragmentURI, asOperation, featureLocality, foreignPackageName, className, bodyText);
	}

	protected @NonNull FeatureBody generateStaticProperty(@NonNull CGProperty cgProperty) {
		localContext = globalContext.getLocalContext(cgProperty);
		js.resetStream();
		//
		Property asProperty = CGUtil.getAST(cgProperty);
		assert asProperty.isIsStatic();
		String className = "SP_" + context.getForeignClassName(PivotUtil.getOwningClass(asProperty));
		LanguageExpression expressionInOCL = asProperty.getOwnedExpression();
		CGValuedElement cgBody = cgProperty.getBody();
		assert cgBody != null;
		String title = PrettyPrinter.printName(asProperty);
		js.append("\n");
		js.append("/**\n");
		js.append(" *\t");
		js.append(title);
		js.append("\n");
		js.append(" */\n");
		js.append("public static class " + className + " extends ");
		js.appendClassReference(null, AbstractStaticProperty.class);
		js.pushClassBody(className);

		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(className);
		js.append(" ");
		js.append(globalContext.getInstanceName());
		js.append(" = new ");
		js.append(className);
		js.append("(");
		appendIdPath(asProperty);
		js.append(");\n");
		js.append("\n");

		js.append("private ");
		js.append(className);
		js.append("() {\n");
		js.pushIndentation(null);
		js.append("super(");
	//	js.append(className);
		js.append(");\n");
		js.popIndentation();
		js.append("}\n");
		js.append("\n");

		js.appendCommentWithOCL(null, expressionInOCL);
		js.append("@Override\n");
		js.append("public ");
		boolean cgPropertyIsInvalid = cgProperty.getInvalidValue() != null;
		js.appendIsCaught(!cgPropertyIsInvalid, cgPropertyIsInvalid);
		js.append(" ");
		js.appendClassReference(false, Object.class);
		js.append(" initialValue(");
		js.appendClassReference(true, Executor.class);
		js.append(JavaConstants.EXECUTOR_NAME);
		js.append(") {\n");
		js.pushIndentation(null);
		appendReturn(cgBody);
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(false);
		assert js.peekClassNameStack() == null;
		String bodyText = toString();
		String fragmentURI = getFragmentURI(asProperty);
		String foreignPackageName = getGlobalContext().getTablesClassName();
		FeatureLocality featureLocality = FeatureLocality.FOREIGN_STATIC;
		return new FeatureBody(fragmentURI, asProperty, featureLocality, foreignPackageName, className, bodyText);
	}

	private void appendIdPath(@NonNull Element element) {
		EObject parent = element.eContainer();
		if ((parent instanceof Element) && !(parent instanceof Model)) {
			appendIdPath((Element)parent);
			js.append(", ");
		}
		if (element instanceof org.eclipse.ocl.pivot.Package) {
			js.appendString(((org.eclipse.ocl.pivot.Package)element).getPackageId().getDisplayName());
		}
		else if (element instanceof Type) {
			js.appendString(((TemplateableId)((Type)element).getTypeId()).getName());
		}
		else if (element instanceof Property) {
			js.appendString(((Property)element).getPropertyId().getName());
		}
		else {
			throw new UnsupportedOperationException("OCLinEcoreCG2JavaVisitor.appendIdPath for " + element.getClass().getSimpleName());
		}
	}

	protected @NonNull String generateValidatorBody(@NonNull CGValuedElement cgBody, @NonNull Constraint asConstraint, org.eclipse.ocl.pivot.@NonNull Class asType) {
		js.resetStream();
		boolean isBombproof = false;
		if (cgBody.isTrue() || cgBody.isFalse()) {				// FIXME Deeper bombproof analysis
			isBombproof = true;
		}
		String constraintNameName = JavaConstants.CONSTRAINT_NAME_NAME; //context.getGlobalContext().getConstraintNameName();
		String constraintName = PivotUtil.getName(asConstraint);
		EObject eContainer = asConstraint.eContainer();
		if (eContainer instanceof NamedElement) {
			String containerName = ((NamedElement)eContainer).getName();
			if (containerName != null) {
				constraintName = containerName + "::" + constraintName;
			}
		}
		if (!isBombproof) {
			js.append("final ");
			js.appendClassReference(true, String.class);
			js.append(" ");
			js.append(constraintNameName);
			js.append(" = ");
			js.appendString(constraintName);
			js.append(";\n");
			js.append("try {\n");  								// See Bug 543178 for design rationale
			js.pushIndentation(null);
		}
		GenClassifier genClassifier = genModelHelper.getGenClassifier(asType);
		String genClassifierName = genClassifier != null ? genClassifier.getName() : null;
		if (genClassifierName == null) {
			genClassifierName = "";
		}
		js.appendCommentWithOCL(null, asConstraint);
		js.appendLocalStatements(cgBody);
		js.append("return ");
		if (!cgBody.isNonNull() || (cgBody.getASTypeId() != TypeId.BOOLEAN)) {
			js.append("Boolean.TRUE == ");
		}
		js.appendValueName(cgBody);
		js.append(";\n");
		if (!isBombproof) {
			js.popIndentation();
			js.append("}\n");
			js.append("catch (");
			js.appendClassReference(null, Throwable.class);
			js.append(" e) {\n");
			js.pushIndentation(null);
			js.append("return ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".validationFailedDiagnostic(");
			js.append(constraintNameName);
			js.append(", this, diagnostics, context, e);\n");
			js.popIndentation();
			js.append("}\n");
		}
		return toString();
	}

	protected @NonNull FeatureLocality getFeatureLocality(@NonNull Operation asOperation) {
		boolean isStatic = asOperation.isIsStatic();
		LibraryFeature operationImplementation = asOperation.getImplementation();
		if (isStatic) {
			if (operationImplementation instanceof ForeignOperation) {
				return FeatureLocality.FOREIGN_STATIC;			// XXX FIXME
			}
			else {
				return FeatureLocality.FOREIGN_STATIC;
			}
		}
		else {
			if (operationImplementation instanceof ForeignOperation) {
				return FeatureLocality.FOREIGN_IMPL;
			}
			else {
				return FeatureLocality.ECORE_IMPL;
			}
		}
	}

	protected @NonNull FeatureLocality getFeatureLocality(@NonNull Property asProperty) {
		boolean isStatic = asProperty.isIsStatic();
		LibraryFeature propertyImplementation = asProperty.getImplementation();
		if (isStatic) {
			if (propertyImplementation instanceof ForeignProperty) {
				return FeatureLocality.FOREIGN_STATIC;		// XXX FIXME
			}
			else {
				return FeatureLocality.FOREIGN_STATIC;
			}
		}
		else {
			if (propertyImplementation instanceof ForeignProperty) {
				return FeatureLocality.FOREIGN_IMPL;
			}
			else {
				return FeatureLocality.ECORE_IMPL;
			}
		}
	}

	protected @NonNull String getFragmentURI(@NonNull Element element) {
		return String.valueOf(EcoreUtil.getURI(element).fragment());
	}

	@Override
	public @Nullable GenPackage getGenPackage() {
		return genPackage;
	}

	protected @NonNull OCLinEcoreGlobalContext getGlobalContext() {
		return (OCLinEcoreGlobalContext) globalContext;
	}

	protected @NonNull OCLinEcoreLocalContext getLocalContext() {
		return ClassUtil.nonNullState((OCLinEcoreLocalContext) localContext);
	}

	protected String getRuleName(@NonNull Constraint constraint) {
		String name = constraint.getName();
		return name != null ? name : "";
	}

	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
		return true;
	}

	@Override
	public @NonNull Boolean visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		CGValuedElement globalConstant = cgConstantExp.getReferredConstant();
		if (globalConstant instanceof CGExecutorType) {		// FIXME Why is this necessary to avoid a CG failure in CodeGenCompany
			return true;
		}
		if (globalConstant != null) {
			js.appendValueName(globalConstant);
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		LibraryOperation libraryOperation = cgOperationCallExp.getLibraryOperation();
		if (libraryOperation == CGStringGetSeverityOperation.INSTANCE) {
			Boolean flowContinues = doGetSeverity(cgOperationCallExp);
			if (flowContinues != null) {
				return flowContinues;
			}
		}
		return super.visitCGLibraryOperationCallExp(cgOperationCallExp);
	}

	@Override
	public @NonNull Boolean visitCGOperation(@NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		assert asOperation != null;
		FeatureLocality featureLocality = getFeatureLocality(asOperation);
		return doOperation(cgOperation, featureLocality.isForeign());
	}

	@Override
	public @NonNull Boolean visitCGPackage(@NonNull CGPackage cgPackage) {
		return true;
	}

	@Override
	public @NonNull Boolean visitCGProperty(@NonNull CGProperty cgProperty) {
		// TODO Auto-generated method stub
		return super.visitCGProperty(cgProperty);
	}
}
