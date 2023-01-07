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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.PrimitiveLiteralExp;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.library.StaticProperty;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

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
		public @Nullable Object visitCGConstrainedProperty(@NonNull CGConstrainedProperty cgProperty) {
			super.visitCGConstrainedProperty(cgProperty);
			Property asProperty = CGUtil.getAST(cgProperty);
			EObject eObject = asProperty.getESObject();
			LibraryFeature implementation = asProperty.getImplementation();
			if (implementation instanceof StaticProperty) {
				rewriteAsBoxed(cgProperty.getBody());
			}
			else if (eObject instanceof ETypedElement) {
				EClassifier eType = ((ETypedElement)eObject).getEType();
				if (eType != null) {
					rewriteAsEcore(cgProperty.getBody(), eType);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitCGForeignProperty(@NonNull CGForeignProperty cgProperty) {
			super.visitCGForeignProperty(cgProperty);
			Property asProperty = CGUtil.getAST(cgProperty);
			EObject eObject = asProperty.getESObject();
			LibraryFeature implementation = asProperty.getImplementation();
			if (implementation instanceof StaticProperty) {
				rewriteAsBoxed(cgProperty.getBody());
			}
			else if (eObject instanceof ETypedElement) {
				EClassifier eType = ((ETypedElement)eObject).getEType();
				if (eType != null) {
					rewriteAsEcore(cgProperty.getBody(), eType);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitCGNativeProperty(@NonNull CGNativeProperty cgProperty) {
			super.visitCGNativeProperty(cgProperty);
			Property asProperty = CGUtil.getAST(cgProperty);
			EObject eObject = asProperty.getESObject();
			LibraryFeature implementation = asProperty.getImplementation();
			if (implementation instanceof StaticProperty) {
				rewriteAsBoxed(cgProperty.getBody());
			}
			else if (eObject instanceof ETypedElement) {
				EClassifier eType = ((ETypedElement)eObject).getEType();
				if (eType != null) {
					rewriteAsEcore(cgProperty.getBody(), eType);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
			super.visitCGOperation(cgOperation);
		/*	Element asOperation = cgOperation.getAst();
			if (asOperation instanceof Operation) {
				EObject eObject = ((Operation)asOperation).getESObject();
				if (eObject instanceof ETypedElement) {
					EClassifier eType = ((ETypedElement)eObject).getEType();
					if (eType != null) {
						rewriteAsEcore(cgOperation.getBody(), eType);
					}
				}
			} */
			return null;
		}

		@Override
		public @Nullable Object visitCGProperty(@NonNull CGProperty cgProperty) {
			super.visitCGProperty(cgProperty);			// XXX
			return null;
		}
	}

	/**
	 * StatusAnalyzer traverses an OCLExpression tree identifying those nodes that create status and can be rewritten.
	 *
	 * During the visit a Boolean return signals:
	 * true - is a Boolean Tuple.status that can be rewritten as OclAny.
	 * false - is a Boolean Tuple.status that cannot be rewritten as OclAny.
	 * null- is not a Boolean Tuple.status
	 */
	protected static class StatusAnalyzer extends AbstractExtendingVisitor<@Nullable Boolean, @NonNull OCLinEcoreCodeGenerator>
	{
		protected final @NonNull StandardLibrary standardLibrary;
		private @NonNull Map<@NonNull VariableDeclaration, @Nullable Boolean> variable2verdict = new HashMap<>();
		private @NonNull List<@NonNull OCLExpression> canBeOclAnyExpressions = new ArrayList<>();
		private @NonNull List<@NonNull OperationCallExp> impliesExpressions = new ArrayList<>();
		private @NonNull List<@NonNull PropertyCallExp> statusAccesses = new ArrayList<>();

		protected StatusAnalyzer(@NonNull OCLinEcoreCodeGenerator context) {
			super(context);
			this.standardLibrary = context.getEnvironmentFactory().getStandardLibrary();
		}

		protected @Nullable Boolean visit(/*@NonNull*/ OCLExpression object) {
			Boolean itemVerdict = object.accept(this);
			if (itemVerdict == Boolean.TRUE) {
				assert !(object instanceof TupleLiteralExp);
				assert !canBeOclAnyExpressions.contains(object);
				canBeOclAnyExpressions.add(object);
			}
			return itemVerdict;
		}

		@Override
		public @Nullable Boolean visiting(@NonNull Visitable visitable) {
			throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		}

		//		@Override
		//		public @Nullable Boolean visitBooleanLiteralExp(@NonNull BooleanLiteralExp object) {
		//			return true;
		//		}

		@Override
		public @Nullable Boolean visitCallExp(@NonNull CallExp object) {
			if (!context.booleanType.conformsTo(standardLibrary, PivotUtil.getType(object))) {
				return null;
			}
			OCLExpression asSource = PivotUtil.getOwnedSource(object);
			Boolean sourceVerdict = visit(asSource);
			if (sourceVerdict == null) {
				return sourceVerdict;
			}
			return true;
		}

		@Override
		public @Nullable Boolean visitCollectionItem(@NonNull CollectionItem object) {
			return visit(object);
		}

		@Override
		public @Nullable Boolean visitCollectionLiteralExp(@NonNull CollectionLiteralExp object) {
			boolean canBeOclAny = true;
			for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(object)) {
				Boolean itemVerdict = visit(part);
				if (itemVerdict == null) {
					return null;
				}
				if (itemVerdict == false) {
					canBeOclAny = false;
				}
			}
			return canBeOclAny;
		}

		@Override
		public @Nullable Boolean visitCollectionRange(@NonNull CollectionRange object) {
			return null;
		}

		@Override
		public @Nullable Boolean visitExpressionInOCL(@NonNull ExpressionInOCL object) {
			return visit(object.getOwnedBody());
		}

		@Override
		public @Nullable Boolean visitIfExp(@NonNull IfExp object) {
			Boolean thenVerdict = visit(object.getOwnedThen());
			Boolean elseVerdict = visit(object.getOwnedElse());
			if ((thenVerdict == null) || (elseVerdict == null)) {
				return null;
			}
			return thenVerdict && elseVerdict;
		}

		@Override
		public @Nullable Boolean visitLetExp(@NonNull LetExp object) {
			object.getOwnedVariable().accept(this);
			return visit(object.getOwnedIn());
		}

		@Override
		public @Nullable Boolean visitLetVariable(@NonNull LetVariable object) {
			Boolean verdict = visit(object.getOwnedInit());
			variable2verdict.put(object, verdict);
			return verdict;
		}

		@Override
		public @Nullable Boolean visitLoopExp(@NonNull LoopExp object) {
			return null;
		}

		@Override
		public @Nullable Boolean visitOCLExpression(@NonNull OCLExpression object) {
			System.out.println("Unsupported " + object.eClass().getName() + " for " + getClass().getSimpleName());
			return null;
		}

		//
		// An OperationCallExp propagates a Tuple status if it is an implies guard for a Tyuple status
		//
		@Override
		public @Nullable Boolean visitOperationCallExp(@NonNull OperationCallExp object) {
			Operation referredOperation = PivotUtil.getReferredOperation(object);
			if (referredOperation.getOperationId() == OperationId.BOOLEAN_IMPLIES) {
				OCLExpression argument = PivotUtil.getOwnedArgument(object, 0);
				Boolean argumentVerdict = visit(argument);
				if (argumentVerdict != null) {
					impliesExpressions.add(object);
				}
				return argumentVerdict;

			}
			/*
		// - it can return a Boolean
		// - one of more of source/arguments are a Tuple status passable as OclAny
		//-- which is almost nothing
 			if (!context.booleanType.conformsTo(standardLibrary, PivotUtil.getType(object))) {
				return null;
			}
			OCLExpression asSource = PivotUtil.getOwnedSource(object);
			Boolean sourceVerdict = visit(asSource);
			if (sourceVerdict == null) {  // ?? ==null
				return null;
			}
			boolean canBeStatusExpression = false;
			boolean canBeOclAnyExpression = sourceVerdict.booleanValue();
			Type owningClass = PivotUtil.getOwningClass(referredOperation);
			if (!context.oclAnyType.conformsTo(standardLibrary, owningClass)) {
				canBeOclAnyExpression = false;
			}
			if (context.booleanType.conformsTo(standardLibrary, owningClass)) {
				canBeStatusExpression = true;
			}
			Iterable<@NonNull Parameter> ownedParameters = PivotUtil.getOwnedParameters(referredOperation);
			Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(object);
			Iterator<@NonNull Parameter> itParameter = ownedParameters.iterator();
			Iterator<@NonNull OCLExpression> itArgument = ownedArguments.iterator();
			while (itParameter.hasNext() && itArgument.hasNext()) {
				Parameter asParameter = itParameter.next();
				OCLExpression asArgument = itArgument.next();
				Boolean argumentVerdict = visit(asArgument);
				if (argumentVerdict == null) {
					return argumentVerdict;
				}
				Type parameterClass = PivotUtil.getType(asParameter);
				if (!context.oclAnyType.conformsTo(standardLibrary, parameterClass)) {
					canBeOclAnyExpression = false;
				}
				if (context.booleanType.conformsTo(standardLibrary, parameterClass)) {
					canBeStatusExpression = true;
				}
			}
			if (!canBeStatusExpression) {
				return null;
			}
			return canBeOclAnyExpression; */
			return null;
		}

		@Override
		public @Nullable Boolean visitPrimitiveLiteralExp(@NonNull PrimitiveLiteralExp object) {
			return null;
		}

		//
		// A PropertyCallExp can return a Tuple status if the source is a TupleLiteralExp and the status property is accessed.
		//
		@Override
		public @Nullable Boolean visitPropertyCallExp(@NonNull PropertyCallExp object) {
			OCLExpression ownedSource = object.getOwnedSource();
			if (ownedSource instanceof TupleLiteralExp){
				TupleLiteralExp tupleLiteralExp = (TupleLiteralExp)ownedSource;
				TupleType tupleType = PivotUtil.getType(tupleLiteralExp);
				Property statusPart = PivotUtilInternal.getStatusTupleTypeStatusPart(tupleType);
				if (statusPart != null){
					statusAccesses.add(object);
					return true;
				}
			}
			//			Boolean superVerdict = super.visitPropertyCallExp(object);
			//			if (superVerdict != Boolean.TRUE) {
			//				return superVerdict;
			//			}
			return null;
		}

		@Override
		public @Nullable Boolean visitTupleLiteralExp(@NonNull TupleLiteralExp object) {
			Property statusPart = PivotUtilInternal.getStatusTupleTypeStatusPart(PivotUtil.getType(object));
			if (statusPart != null){
				return true;
			}
			return null;
		}

		@Override
		public @Nullable Boolean visitTypeExp(@NonNull TypeExp object) {
			return null;
		}

		@Override
		public @Nullable Boolean visitVariableExp(@NonNull VariableExp object) {
			return variable2verdict.get(object.getReferredVariable());
		}
	}

	protected final @NonNull StandardLibraryInternal standardLibrary;
	protected final @NonNull GenModel genModel;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull AnyType oclAnyType;
	protected final @NonNull PrimitiveType booleanType;
	protected final @NonNull PrimitiveType integerType;
	protected final @NonNull PrimitiveType stringType;
	private @Nullable Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery = null;
	protected final @NonNull String qualifiedSupportClassName;

	protected OCLinEcoreCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull GenModel genModel) {
		super(environmentFactory, genModel);
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.genModel = ClassUtil.nonNullModel(genModel);
		genModel.reconcile();
		metamodelManager.addGenModel(genModel);
		getOptions().setUseNullAnnotations(OCLinEcoreGenModelGeneratorAdapter.useNullAnnotations(genModel));
		this.analyzer = createCodeGenAnalyzer();
		this.oclAnyType = standardLibrary.getOclAnyType();
		this.booleanType = standardLibrary.getBooleanType();
		this.integerType = standardLibrary.getIntegerType();
		this.stringType = standardLibrary.getStringType();
		//		CommonSubexpressionEliminator.CSE_BUILD.setState(true);
		//		CommonSubexpressionEliminator.CSE_PLACES.setState(true);
		//		CommonSubexpressionEliminator.CSE_PRUNE.setState(true);
		//		CommonSubexpressionEliminator.CSE_PULL_UP.setState(true);
		//		CommonSubexpressionEliminator.CSE_PUSH_UP.setState(true);
		//		CommonSubexpressionEliminator.CSE_REWRITE.setState(true);
		this.qualifiedSupportClassName = OCLGenModelUtil.getQualifiedSupportClassName(genModel);
	}

	@Override
	public @NonNull AS2CGVisitor createAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		return new OCLinEcoreAS2CGVisitor(analyzer);
	}

	@Override
	public @NonNull BoxingAnalyzer createBoxingAnalyzer() {
		return new EcoreBoxingAnalyzer(analyzer);
	}

	@Override
	protected @NonNull CodeGenAnalyzer createCodeGenAnalyzer() {
		return new OCLinEcoreAnalyzer(this);
	}

	@Override
	public @NonNull ImportNameManager createImportNameManager() {
		return new OCLinEcoreImportNameManager();
	}

	public @NonNull Map<@NonNull CGPackage, @Nullable GenPackage> generateDeclarations(@NonNull OCLinEcoreSupport support) {
		newQuery2oldQuery = new HashMap<>();
		List<@NonNull GenPackage> genPackages = ClassUtil.nullFree(genModel.getAllGenPackagesWithClassifiers());
		Map<@NonNull CGPackage, @Nullable GenPackage> cgPackage2genPackage = new HashMap<>();
		for (@NonNull GenPackage genPackage : genPackages) {
			EPackage ecorePackage = genPackage.getEcorePackage();
			org.eclipse.ocl.pivot.Package asPackage = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Package.class, ecorePackage);
			assert asPackage != null;
			CGPackage cgPackage = analyzer.createCGElement(CGPackage.class, asPackage);
			cgPackage2genPackage.put(cgPackage, genPackage);
			if (cgPackage.eContainer() == null) {
				String basePackage = genPackage.getBasePackage();
				if ((basePackage != null) && (basePackage.length() > 0)) {
					org.eclipse.ocl.pivot.Package asBasePackage = getLanguageSupport().getNativePackage(basePackage);
					CGPackage cgBasePackage = analyzer.basicGetCGPackage(asBasePackage);
					if (cgBasePackage == null) {
						cgBasePackage = analyzer.createCGElement(CGPackage.class, asBasePackage);
					}
					cgBasePackage.getPackages().add(cgPackage);
				}
			}
		}
		return cgPackage2genPackage;
	}

	public @NonNull Map<@NonNull String, @NonNull String> generateBodies(@NonNull OCLinEcoreSupport support, Map<@NonNull CGPackage, @Nullable GenPackage> cgPackage2genPackage) {
		Map<@NonNull String, @NonNull String> uri2body = new HashMap<>();
		Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery2 = newQuery2oldQuery;
		assert newQuery2oldQuery2 != null;
		try {
			Set<@NonNull CGPackage> cgPackageSet = cgPackage2genPackage.keySet();
			List<@NonNull CGPackage> cgRootPackages = new ArrayList<>();
			for (@NonNull CGPackage cgPackage : cgPackageSet) {			// Prune nested packages
				boolean gotIt = false;
				for (CGPackage cgSuperPackage = cgPackage.getContainingPackage(); cgSuperPackage != null; cgSuperPackage = cgSuperPackage.getContainingPackage()) {
					if (cgPackageSet.contains(cgSuperPackage)) {
						gotIt = true;
						break;
					}
				}
				if (!gotIt) {
					cgRootPackages.add(cgPackage);
				}
			}
			Collections.sort(cgRootPackages, NameUtil.NAMEABLE_COMPARATOR);
			optimize(cgRootPackages);
			Iterable<@NonNull CGValuedElement> cgGlobals = pregenerate(cgRootPackages);
			if (cgGlobals != null) {
				support.setGlobals(cgGlobals);
			}
			List<@NonNull CGPackage> cgAllPackages = new ArrayList<>(cgPackageSet);
			Collections.sort(cgAllPackages, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CGPackage cgPackage : cgAllPackages) {
				GenPackage genPackage = cgPackage2genPackage.get(cgPackage);
				if (genPackage != null) {
					OCLinEcoreCG2JavaVisitor cg2java = new OCLinEcoreCG2JavaVisitor(this, genPackage);
					Map<@NonNull String, @NonNull String> results = cg2java.generateBodies(cgPackage);
					uri2body.putAll(results);
				}
			}
			return uri2body;
		}
		finally {
			for (Map.Entry<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> entry : newQuery2oldQuery2.entrySet()) {
				ExpressionInOCL newQuery = entry.getKey();
				ExpressionInOCL oldQuery = entry.getValue();
//				System.out.println("key2: " + NameUtil.debugSimpleName(newQuery) + " : " + NameUtil.debugSimpleName(newQuery.eContainer()) + " : " + newQuery);
//				System.out.println("val2: " + NameUtil.debugSimpleName(oldQuery) + " : " + NameUtil.debugSimpleName(oldQuery.eContainer()) + " : " + oldQuery);
				assert newQuery.eContainer() != null;
				assert oldQuery.eContainer() == null;
				Constraint eContainer = (Constraint) newQuery.eContainer();
				PivotUtilInternal.resetContainer(newQuery);
				eContainer.setOwnedSpecification(oldQuery);
			}
			newQuery2oldQuery = null;
		}
	}

	@Override
	public @NonNull OCLinEcoreAnalyzer getAnalyzer() {
		return (OCLinEcoreAnalyzer)analyzer;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getContextClass() {
		return getAnalyzer().getContextClass();
	}

	public @NonNull String getQualifiedSupportClassName() {
		return qualifiedSupportClassName;
	}

	public @NonNull String getQualifiedTablesClassName(@NonNull GenPackage genPackage) {
		return getGenModelHelper().getTablesClassName(genPackage);
	}

	protected @NonNull ExpressionInOCL rewriteQuery(@NonNull ExpressionInOCL oldQuery) {
		assert oldQuery.eContainer() != null;
		OCLExpression oldBody = oldQuery.getOwnedBody();
		if ((oldBody instanceof BooleanLiteralExp) && ((BooleanLiteralExp)oldBody).isBooleanSymbol()) {
			return oldQuery;		// Unconditionally true (typically obsolete) constraint needs no added complexity
		}
		ExpressionInOCL asSynthesizedQuery = EcoreUtil.copy(oldQuery);
		StatusAnalyzer statusAnalyzer = new StatusAnalyzer(this);
		Boolean verdict = asSynthesizedQuery.accept(statusAnalyzer);
		if (verdict == Boolean.TRUE) {
			for (@NonNull PropertyCallExp asPropertyCallExp : statusAnalyzer.statusAccesses) {
				TupleLiteralExp asTupleValue = (TupleLiteralExp)asPropertyCallExp.getOwnedSource();
				assert asTupleValue != null;
				OCLExpression asExpression = rewriteTupleLiteralExp(asTupleValue);
				EObject eContainer = asPropertyCallExp.eContainer();
				EReference eContainmentFeature = asPropertyCallExp.eContainmentFeature();
				PivotUtilInternal.resetContainer(asPropertyCallExp);
				eContainer.eSet(eContainmentFeature, asExpression);		// FIXME isMany
			}
			for (@NonNull OCLExpression asExpression : statusAnalyzer.canBeOclAnyExpressions) {
				if (asExpression.eContainer() != null) {
					asExpression.setType(oclAnyType);
				}
			}
			for (@NonNull OperationCallExp impliesExpression : statusAnalyzer.impliesExpressions) {
				OCLExpression conditionExpression = PivotUtil.getOwnedSource(impliesExpression);
				OCLExpression thenExpression = PivotUtil.getOwnedArgument(impliesExpression, 0);
				OCLExpression elseExpression = asHelper.createBooleanLiteralExp(true);
				PivotUtilInternal.resetContainer(conditionExpression);
				PivotUtilInternal.resetContainer(thenExpression);
				OCLExpression ifExpression = asHelper.createIfExp(conditionExpression, thenExpression, elseExpression);
				EObject eContainer = impliesExpression.eContainer();
				EReference eContainmentFeature = impliesExpression.eContainmentFeature();
				PivotUtilInternal.resetContainer(impliesExpression);
				eContainer.eSet(eContainmentFeature, ifExpression);		// FIXME isMany
			}
			asSynthesizedQuery.setBody(null);
		}
		Variable asSelfVariable = ClassUtil.nonNullState(asSynthesizedQuery.getOwnedContext());
		Variable asDiagnosticsVariable = asHelper.createParameterVariable(JavaConstants.CONSTRAINT_DIAGNOSTICS_NAME, oclAnyType, false);
		asSynthesizedQuery.getOwnedParameters().add(asDiagnosticsVariable);
		Variable asConstraintNameNameVariable = asHelper.createParameterVariable(JavaConstants.CONSTRAINT_NAME_NAME, stringType, true);
		asSynthesizedQuery.getOwnedParameters().add(asConstraintNameNameVariable);
		Variable asContextVariable = asHelper.createParameterVariable(JavaConstants.CONSTRAINT_CONTEXT_NAME, oclAnyType, false);
		asSynthesizedQuery.getOwnedParameters().add(asContextVariable);
		//
		//	Cache the result in a let-variable
		//
		OCLExpression asResultVariableInit = asSynthesizedQuery.getOwnedBody();
		assert asResultVariableInit != null;
		PivotUtilInternal.resetContainer(asResultVariableInit);
		LetVariable asResultVariable = asHelper.createLetVariable("result", asResultVariableInit);
		//
		//	Cache the severity in a let-variable
		//
		OCLExpression asSeverityVariableInit = asHelper.createOperationCallExp(asHelper.createVariableExp(asConstraintNameNameVariable), "getSeverity");
		LetVariable asSeverityVariable = asHelper.createLetVariable("severity", integerType, asSeverityVariableInit.isIsRequired(), asSeverityVariableInit);
		//
		//	Build from the bottom, starting with logging the status.
		//
		OCLExpression asLogExpression = asHelper.createOperationCallExp(asHelper.createVariableExp(asConstraintNameNameVariable), "logDiagnostic",
			asHelper.createVariableExp(asSelfVariable), asHelper.createNullLiteralExp(),
			asHelper.createVariableExp(asDiagnosticsVariable), asHelper.createVariableExp(asContextVariable),
			asHelper.createNullLiteralExp()/*asMessageExp*/, asHelper.createVariableExp(asSeverityVariable),
			asHelper.createVariableExp(asResultVariable), asHelper.createIntegerLiteralExp(0));
		//
		//	Wrapped in the status let-variable
		//
		OCLExpression asResultExpression = asHelper.createLetExp(asResultVariable, asLogExpression);
		//
		//	Wrapped in an interesting severity guard
		//
		OCLExpression asCondition = asHelper.createOperationCallExp(asHelper.createVariableExp(asSeverityVariable), "<=", asHelper.createIntegerLiteralExp(0));
		OCLExpression asSeverityExpression = asHelper.createIfExp(asCondition, asHelper.createBooleanLiteralExp(true), asResultExpression);
		//
		//	Wrapped in the severity let-variable
		//
		asSynthesizedQuery.setOwnedBody(asHelper.createLetExp(asSeverityVariable, asSeverityExpression));
		//
		//	Install replacement query in the original Constraint.
		//
		Constraint eContainer = (Constraint) oldQuery.eContainer();
		PivotUtilInternal.resetContainer(oldQuery);
		eContainer.setOwnedSpecification(asSynthesizedQuery);
		Map<@NonNull ExpressionInOCL, @NonNull ExpressionInOCL> newQuery2oldQuery2 = newQuery2oldQuery;
		assert newQuery2oldQuery2 != null;
//		System.out.println("key1: " + NameUtil.debugSimpleName(asSynthesizedQuery) + " : " + NameUtil.debugSimpleName(asSynthesizedQuery.eContainer()) + " : " + asSynthesizedQuery);
//		System.out.println("val1: " + NameUtil.debugSimpleName(oldQuery) + " : " + NameUtil.debugSimpleName(oldQuery.eContainer()) + " : " + oldQuery);
		assert !newQuery2oldQuery2.containsKey(asSynthesizedQuery);
		assert !newQuery2oldQuery2.containsKey(oldQuery);
		newQuery2oldQuery2.put(asSynthesizedQuery, oldQuery);
		assert asSynthesizedQuery.eContainer() != null;
		assert oldQuery.eContainer() == null;
		return asSynthesizedQuery;
	}

	private @NonNull OCLExpression rewriteTupleLiteralExp(@NonNull TupleLiteralExp asTupleLiteralExp) {
		Iterable<@NonNull TupleLiteralPart> asTupleParts = PivotUtil.getOwnedParts(asTupleLiteralExp);
		LetVariable asStatusVariable = null;
		TupleLiteralPart asStatusPart = NameUtil.getNameable(asTupleParts, PivotConstants.STATUS_PART_NAME);
		if (asStatusPart == null) {
			return asTupleLiteralExp;
		}
		OCLExpression asStatusInit = asStatusPart.getOwnedInit();
		if (asStatusInit == null) {
			return asTupleLiteralExp;
		}
		//
		//	Cache the status in a let-variable
		//
		PivotUtilInternal.resetContainer(asStatusInit);
		asStatusVariable = asHelper.createLetVariable("status", standardLibrary.getBooleanType(), asStatusInit.isIsRequired(), asStatusInit);
		asStatusPart.setOwnedInit(asHelper.createVariableExp(asStatusVariable));
		//
		//	Wrap the tuple in a status guard for failure.
		//
		PivotUtilInternal.resetContainer(asTupleLiteralExp);
		OCLExpression asCondition = asHelper.createOperationCallExp(asHelper.createVariableExp(asStatusVariable), "=", asHelper.createBooleanLiteralExp(true));
		OCLExpression asStatusExp = asHelper.createIfExp(asCondition, asHelper.createBooleanLiteralExp(true), asTupleLiteralExp);
		return asHelper.createLetExp(asStatusVariable, asStatusExp);
	}
}
