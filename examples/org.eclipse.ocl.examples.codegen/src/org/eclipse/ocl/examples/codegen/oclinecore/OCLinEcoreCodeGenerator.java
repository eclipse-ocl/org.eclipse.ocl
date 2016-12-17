/*******************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotHelper;

/**
 * OCLinEcoreCodeGenerator supports generation of the inline OCL-defined content of a Ecore *Impl file.
 */
public class OCLinEcoreCodeGenerator extends JavaCodeGenerator
{
	public static class EcoreBoxingAnalyzer extends BoxingAnalyzer
	{
		private EcoreBoxingAnalyzer(@NonNull CodeGenAnalyzer analyzer) {
			super(analyzer);
		}

		@Override
		public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
			super.visitCGOperation(cgOperation);
			Element asOperation = cgOperation.getAst();
			if (asOperation instanceof Operation) {
				EObject eObject = ((Operation)asOperation).getESObject();
				if (eObject instanceof ETypedElement) {
					EClassifier eType = ((ETypedElement)eObject).getEType();
					if (eType != null) {
						rewriteAsEcore(cgOperation.getBody(), eType);
					}
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitCGProperty(@NonNull CGProperty cgProperty) {
			super.visitCGProperty(cgProperty);
			Element asProperty = cgProperty.getAst();
			if (asProperty instanceof Property) {
				EObject eObject = ((Property)asProperty).getESObject();
				if (eObject instanceof ETypedElement) {
					EClassifier eType = ((ETypedElement)eObject).getEType();
					if (eType != null) {
						rewriteAsEcore(cgProperty.getBody(), eType);
					}
				}
			}
			return null;
		}
	}

	public static void generatePackage(@NonNull GenPackage genPackage,
			@NonNull Map<String, String> uri2body, @NonNull Map<GenPackage, String> constantsTexts) {
		Resource genResource = ClassUtil.nonNullState(genPackage.eResource());
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(genResource);
		OCLinEcoreCodeGenerator generator = new OCLinEcoreCodeGenerator(environmentFactory, genPackage);
		generator.generate(uri2body, constantsTexts);
	}

	protected final @NonNull OCLinEcoreGlobalContext globalContext;
	protected final @NonNull CodeGenAnalyzer cgAnalyzer;
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull PivotHelper asHelper;
	private @Nullable Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery = null;

	protected OCLinEcoreCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull GenPackage genPackage) {
		super(environmentFactory);
		GenModel genModel = ClassUtil.nonNullModel(genPackage.getGenModel());
		genModel.reconcile();
		metamodelManager.addGenModel(genModel);
		getOptions().setUseNullAnnotations(OCLinEcoreGenModelGeneratorAdapter.useNullAnnotations(genModel));
		this.cgAnalyzer = new CodeGenAnalyzer(this);
		this.genPackage = genPackage;
		this.globalContext = new OCLinEcoreGlobalContext(this, genPackage);
		this.asHelper = new PivotHelper(environmentFactory);
		//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
		//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
		//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
		//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
		//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
		//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
	}

	@Override
	public @NonNull BoxingAnalyzer createBoxingAnalyzer() {
		return new EcoreBoxingAnalyzer(cgAnalyzer);
	}

	protected void generate(@NonNull Map<String, String> uri2body, @NonNull Map<GenPackage, String> constantsTexts) {
		Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery2 = newQuery2oldQuery = new HashMap<>();
		try {
			EPackage ecorePackage = genPackage.getEcorePackage();
			org.eclipse.ocl.pivot.Package asPackage = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Package.class, ecorePackage);
			assert asPackage != null;
			AS2CGVisitor as2cgVisitor = new OCLinEcoreAS2CGVisitor(cgAnalyzer, globalContext);
			CGPackage cgPackage = (CGPackage) ClassUtil.nonNullState(asPackage.accept(as2cgVisitor));
			optimize(cgPackage);
			OCLinEcoreCG2JavaVisitor cg2java = new OCLinEcoreCG2JavaVisitor(this, genPackage, cgPackage);
			Map<String, String> results = cg2java.generateBodies();
			for (Map.Entry<String, String> entry : results.entrySet()) {
				uri2body.put(entry.getKey(), entry.getValue());
			}
			Iterable<@NonNull CGValuedElement> sortedGlobals = prepareGlobals();
			String constantsText = cg2java.generateConstants(sortedGlobals);
			constantsTexts.put(genPackage, constantsText);
		}
		finally {
			for (Map.Entry<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> entry : newQuery2oldQuery2.entrySet()) {
				ExpressionInOCL newQuery = entry.getKey();
				ExpressionInOCL oldQuery = entry.getValue();
				Constraint eContainer = (Constraint) newQuery.eContainer();
				PivotUtilInternal.resetContainer(newQuery);
				eContainer.setOwnedSpecification(oldQuery);
			}
			newQuery2oldQuery = null;
		}
	}

	@Override
	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return cgAnalyzer;
	}

	@Override
	public @NonNull OCLinEcoreGlobalContext getGlobalContext() {
		return globalContext;
	}

	protected @NonNull ExpressionInOCL rewriteQuery(@NonNull ExpressionInOCL oldQuery, @NonNull String qualifiedConstraintName) {
		ExpressionInOCL asSynthesizedQuery = EcoreUtil.copy(oldQuery);
		StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
		AnyType oclAnyType = standardLibrary.getOclAnyType();
		PrimitiveType booleanType = standardLibrary.getBooleanType();
		PrimitiveType integerType = standardLibrary.getIntegerType();
		Variable asSelfVariable = ClassUtil.nonNullState(asSynthesizedQuery.getOwnedContext());
		Variable asDiagnosticsVariable = asHelper.createParameterVariable("diagnostics", oclAnyType, false);
		asSynthesizedQuery.getOwnedParameters().add(asDiagnosticsVariable);
		Variable asContextVariable = asHelper.createParameterVariable("context", oclAnyType, false);
		asSynthesizedQuery.getOwnedParameters().add(asContextVariable);
		OCLExpression asStatusInit = null;
		OCLExpression asMessageInit = null;
		OCLExpression asSeverityInit = null;
		OCLExpression asSource = null;
		OCLExpression originalBody = asSynthesizedQuery.getOwnedBody();
		if (originalBody instanceof PropertyCallExp) {
			PropertyCallExp asPropertyCallExp = (PropertyCallExp)originalBody;
			asSource = asPropertyCallExp.getOwnedSource();
			Property asReferredProperty = asPropertyCallExp.getReferredProperty();
			if ((asReferredProperty != null) && PivotConstants.STATUS_PART_NAME.equals(asReferredProperty.getName())) {
				if (asSource instanceof TupleLiteralExp) {
					TupleLiteralExp asTupleLiteralExp = (TupleLiteralExp)asSource;
					List<TupleLiteralPart> asTupleParts = asTupleLiteralExp.getOwnedParts();
					TupleLiteralPart asStatusPart = NameUtil.getNameable(asTupleParts, PivotConstants.STATUS_PART_NAME);
					if (asStatusPart != null) {
						asStatusInit = asStatusPart.getOwnedInit();
						if (asStatusInit != null) {
							PivotUtilInternal.resetContainer(asStatusInit);
						}
						TupleLiteralPart asMessagePart = NameUtil.getNameable(asTupleParts, PivotConstants.MESSAGE_PART_NAME);
						if (asMessagePart != null) {
							asMessageInit = asMessagePart.getOwnedInit();
							if (asMessageInit != null) {
								PivotUtilInternal.resetContainer(asMessageInit);
							}
						}
						TupleLiteralPart asSeverityPart = NameUtil.getNameable(asTupleParts, PivotConstants.SEVERITY_PART_NAME);
						if (asSeverityPart != null) {
							asSeverityInit = asSeverityPart.getOwnedInit();
							if (asSeverityInit != null) {
								PivotUtilInternal.resetContainer(asSeverityInit);
							}
						}
					}
				}
			}
		}
		//
		//	Cache the status in a let-variable
		//
		OCLExpression asStatusVariableInit;
		if (asStatusInit != null) {
			asStatusVariableInit = asStatusInit;
		}
		else if ((asSource != null) && (asSource.getType() instanceof TupleType)) {
			asStatusVariableInit = asSource;
		}
		else {
			asStatusVariableInit = originalBody;
		}
		LetVariable asStatusVariable = asHelper.createLetVariable("status", booleanType, asStatusVariableInit.isIsRequired(), asStatusVariableInit);
		//
		//	Cache the severity in a let-variable
		//
		OCLExpression asSeverityVariableInit;
		if (asSeverityInit != null) {
			asSeverityVariableInit = asSeverityInit;
		}
		else {
			StringLiteralExp asConstraintName2 = asHelper.createStringLiteralExp(qualifiedConstraintName);
			asSeverityVariableInit = asHelper.createOperationCallExp(asConstraintName2, "getSeverity");
		}
		LetVariable asSeverityVariable = asHelper.createLetVariable("severity", integerType, asSeverityVariableInit.isIsRequired(), asSeverityVariableInit);
		//
		//	Build from the bottom, starting with logging the status.
		//
		OCLExpression asCondition1 = asHelper.createOperationCallExp(asHelper.createVariableExp(asStatusVariable), "<>", asHelper.createBooleanLiteralExp(true));
		NullLiteralExp asNullExp = asHelper.createNullLiteralExp();
		OCLExpression asMessageExp = asMessageInit != null ? asHelper.createIfExp(asCondition1, asMessageInit, asNullExp) : asNullExp;
		OCLExpression asLogExpression = asHelper.createOperationCallExp(asHelper.createStringLiteralExp(qualifiedConstraintName), "logDiagnostic",
			asHelper.createVariableExp(asSelfVariable), asHelper.createNullLiteralExp(),
			asHelper.createVariableExp(asDiagnosticsVariable), asHelper.createVariableExp(asContextVariable),
			asMessageExp, asHelper.createVariableExp(asSeverityVariable),
			asHelper.createVariableExp(asStatusVariable), asHelper.createIntegerLiteralExp(0));
		//
		//	Wrapped in the status let-variable
		//
		LetExp asStatusExpression = asHelper.createLetExp(asStatusVariable, asLogExpression);
		//
		//	Wrapped in an interesting severity guard
		//
		OCLExpression asSeverityExpression;
		if (asSeverityInit != null) {
			asSeverityExpression = asStatusExpression;
		}
		else {
			OCLExpression asCondition = asHelper.createOperationCallExp(asHelper.createVariableExp(asSeverityVariable), "<=", asHelper.createIntegerLiteralExp(0));
			asSeverityExpression = asHelper.createIfExp(asCondition, asHelper.createBooleanLiteralExp(true), asStatusExpression);
		}
		//
		//	Wrapped in the severity let-variable
		//
		asSynthesizedQuery.setOwnedBody(asHelper.createLetExp(asSeverityVariable, asSeverityExpression));
		//
		//	Install replacment query in the original Constraint.
		//
		Constraint eContainer = (Constraint) oldQuery.eContainer();
		PivotUtilInternal.resetContainer(oldQuery);
		eContainer.setOwnedSpecification(asSynthesizedQuery);
		Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery2 = newQuery2oldQuery;
		assert newQuery2oldQuery2 != null;
		newQuery2oldQuery2.put(asSynthesizedQuery, oldQuery);
		return asSynthesizedQuery;
	}
}
