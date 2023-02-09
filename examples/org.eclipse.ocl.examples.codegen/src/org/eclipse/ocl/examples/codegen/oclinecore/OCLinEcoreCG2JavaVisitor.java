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
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TemplateableId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * An OCLinEcoreCG2JavaVisitor supports generation of the OCL embedded in an Ecore model
 * into the Java bodies of the code producxed by GenModel.
 */
public class OCLinEcoreCG2JavaVisitor extends CG2JavaVisitor
{
	protected final @NonNull GenPackage genPackage;
	protected ExpressionInOCL expInOcl;
	protected Feature feature;

	public OCLinEcoreCG2JavaVisitor(@NonNull OCLinEcoreCodeGenerator codeGenerator, @NonNull GenPackage genPackage) {
		super(codeGenerator);
		this.genPackage = genPackage;
	}

	@Override
	protected void appendSupportPrefix() {
		js.appendClassReference(null, getCodeGenerator().getQualifiedSupportClassName());
		js.append(".");
	}

	@Override
	protected void appendTablesPrefix() {
		js.appendClassReference(null, getCodeGenerator().getQualifiedTablesClassName(genPackage));
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
							js.appendName(globalNameManager.getInstanceName());
							js.append(".");
							js.appendName(globalNameManager.getEvaluateName());
							js.append("(");
							js.appendName(globalNameManager.getExecutorName());
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

	public @NonNull Map<@NonNull String, @NonNull String> generateBodies(@NonNull CGPackage cgPackage) {
		Map<@NonNull String, @NonNull String> uri2body = new HashMap<>();
		for (@NonNull CGClass cgClass : CGUtil.getClasses(cgPackage)) {
			for (CGConstraint cgConstraint : cgClass.getInvariants()) {
				CGValuedElement cgBody = cgConstraint.getBody();
				org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
				Element asElement = cgConstraint.getAst();
				if ((cgBody != null) && (asElement instanceof Constraint)) {
					Constraint asConstraint = (Constraint) asElement;
					String bodyText = generateValidatorBody(cgBody, asConstraint, asClass);
					String fragmentURI = getFragmentURI(asClass) + "==" + getRuleName(asConstraint);
					uri2body.put(fragmentURI, bodyText);
				}
			}
			for (@NonNull CGOperation cgOperation : CGUtil.getOperations(cgClass)) {
				OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
				js.resetStream();
				if (callingConvention.generateEcoreBody(this, cgOperation)) {	// null body is a CallingConvention responsibility
					Operation asOperation = CGUtil.getAST(cgOperation);
					String fragmentURI = getFragmentURI(asOperation);
					String bodyText = js.toString();
					assert bodyText.trim().length() > 0;
					uri2body.put(fragmentURI, bodyText);
				}
			}
			for (@NonNull CGProperty cgProperty : CGUtil.getProperties(cgClass)) {
				PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
				js.resetStream();
				if (callingConvention.generateEcoreBody(this, cgProperty)) {	// null body is a CallingConvention responsibility
					Property asProperty = CGUtil.getAST(cgProperty);
					String fragmentURI = getFragmentURI(asProperty);
					String bodyText = js.toString();
					assert bodyText.trim().length() > 0;
					uri2body.put(fragmentURI, bodyText);
				}
			}
		}
		return uri2body;
	}

/*	protected @NonNull FeatureBody generateStaticOperation(@NonNull CGOperation cgOperation) {
		currentNameManager = globalNameManager.getNestedNameManager(cgOperation);
		js.resetStream();
		//
		Operation asOperation = CGUtil.getAST(cgOperation);
		assert asOperation != null;
		assert asOperation.isIsStatic();
		String className = "SO_" + context.getExternalClassName(PivotUtil.getOwningClass(asOperation));
		List<CGParameter> cgParameters = cgOperation.getParameters();
		LanguageExpression expressionInOCL = asOperation.getBodyExpression();
		CGValuedElement cgBody = cgOperation.getBody();
		assert cgBody != null;
		String title = PrettyPrinter.printName(asOperation);
		js.appendOptionalBlankLine();
		js.append("/**\n");
		js.append(" *\t");
		js.append(title);
		js.appendOptionalBlankLine();
		js.append(" * /\n");
		js.append("public static class " + className + " extends ");
		js.appendClassReference(null, AbstractStaticOperation.class);
		js.pushClassBody(className);

		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(className);
		js.append(" ");
		js.append(globalNameManager.getInstanceName());
		js.append(" = new ");
		js.append(className);
		js.append("();\n");
		js.appendOptionalBlankLine();

		js.appendCommentWithOCL(null, expressionInOCL);
	//	js.append("@Override\n");
		js.append("public ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.append(globalNameManager.getEvaluateName());
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
		String externalPackageName = getCodeGenerator().getTablesClassName();
		FeatureLocality featureLocality = FeatureLocality.FOREIGN_STATIC;
		return new FeatureBody(fragmentURI, asOperation, featureLocality, externalPackageName, className, bodyText);
	} */

/*	protected @NonNull FeatureBody generateStaticProperty(@NonNull CGProperty cgProperty) {
		currentNameManager = globalNameManager.getNestedNameManager(cgProperty);
		js.resetStream();
		//
		Property asProperty = CGUtil.getAST(cgProperty);
		assert asProperty.isIsStatic();
		String className = "SP_" + context.getExternalClassName(PivotUtil.getOwningClass(asProperty));
		LanguageExpression expressionInOCL = asProperty.getOwnedExpression();
		CGValuedElement cgBody = ((CGBodiedProperty)cgProperty).getBody();
		assert cgBody != null;
		String title = PrettyPrinter.printName(asProperty);
		js.appendOptionalBlankLine();
		js.append("/**\n");
		js.append(" *\t");
		js.append(title);
		js.appendOptionalBlankLine();
		js.append(" * /\n");
		js.append("public static class " + className + " extends ");
		js.appendClassReference(null, AbstractStaticProperty.class);
		js.pushClassBody(className);

		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(className);
		js.append(" ");
		js.append(globalNameManager.getInstanceName());
		js.append(" = new ");
		js.append(className);
		js.append("(");
		appendIdPath(asProperty);
		js.append(");\n");
		js.appendOptionalBlankLine();

		js.append("private ");
		js.append(className);
		js.append("() {\n");
		js.pushIndentation(null);
		js.append("super(");
	//	js.append(className);
		js.append(");\n");
		js.popIndentation();
		js.append("}\n");
		js.appendOptionalBlankLine();

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
		String externalPackageName = getCodeGenerator().getTablesClassName();
		FeatureLocality featureLocality = FeatureLocality.FOREIGN_STATIC;
		return new FeatureBody(fragmentURI, asProperty, featureLocality, externalPackageName, className, bodyText);
	} */

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
		if (!cgBody.isRequiredOrNonNull() || (cgBody.getASTypeId() != TypeId.BOOLEAN)) {
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

	@Override
	public @NonNull OCLinEcoreCodeGenerator getCodeGenerator() {
		return (OCLinEcoreCodeGenerator)context;
	}

	protected @NonNull String getFragmentURI(@NonNull Element element) {
		return String.valueOf(EcoreUtil.getURI(element).fragment());
	}

	@Override
	public @Nullable GenPackage getGenPackage() {
		return genPackage;
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
	public @NonNull Boolean visitCGPackage(@NonNull CGPackage cgPackage) {
		return true;
	}
}
