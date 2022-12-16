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
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.ExecutorShadowPartCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

/**
 * The stateless AS2CGVisitor performs the first stage of code generation by converting the Pivot AST to the CG AST.
 */
public class AS2CGVisitor extends AbstractExtendingVisitor<@Nullable CGNamedElement, @NonNull CodeGenAnalyzer>
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull EnvironmentFactoryInternalExtension environmentFactory;
//	protected final @NonNull StandardLibraryInternal standardLibrary;
	protected final @NonNull GlobalNameManager globalNameManager;

	public static final class CGTuplePartNameComparator implements Comparator<@NonNull CGTuplePart>
	{
		public static final @NonNull CGTuplePartNameComparator INSTANCE = new CGTuplePartNameComparator();

		@Override
		public int compare(@NonNull CGTuplePart o1, @NonNull CGTuplePart o2) {
			return ClassUtil.safeCompareTo(CGUtil.getAST(o1).getName(), CGUtil.getAST(o2).getName());
		}
	}

	public AS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
		this.codeGenerator = context.getCodeGenerator();
		this.environmentFactory = (EnvironmentFactoryInternalExtension)codeGenerator.getEnvironmentFactory();
//		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.globalNameManager = codeGenerator.getGlobalNameManager();
	}

/*	protected void createParameters(@NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		NestedNameManager nameManager = getNameManager();
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			CGParameter cgParameter = nameManager.getSelfParameter(contextVariable);
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(contextVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			CGParameter cgParameter;
			if (cgOperation instanceof CGEcoreOperation) {
				cgParameter = nameManager.getParameter(parameterVariable, parameterVariable.getName());
			}
			else {
				cgParameter = nameManager.getParameter(parameterVariable, null);
			}
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(parameterVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
	} */

	/*protected public @NonNull CGOperation createVirtualCGOperationWithoutBody(@NonNull Operation asOperation, @NonNull List<@NonNull CGCachedOperation> cgOperations) {
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		context.initAst(cgOperation, asOperation);
		LocalContext savedLocalContext = pushLocalContext(cgOperation, asOperation);
		cgOperation.setRequired(asOperation.isIsRequired());
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification != null) {
			Variables savedVariablesStack = getVariables();
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				getVariables() = new Variables(null);
				createParameters(cgOperation, query);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				getVariables() = savedVariablesStack;
			}
		}
		cgOperation.getFinalOperations().addAll(cgOperations);
		context.addVirtualCGOperation(asOperation, cgOperation);
		popLocalContext(savedLocalContext);
		return cgOperation;
	} */

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return context;
	}

/*	protected @NonNull CGIterator getNullableIterator(@NonNull Variable iterator) {
		CGIterator cgIterator = getIterator(iterator);
		cgIterator.setTypeId(context.getTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
		return cgIterator;
	} */

	/**
	 * Return true if the asVariableExp reference to asParameter is a reference to 'this' and needs mapping to the qualifiedThisVariable equivalent.
	 *
	protected boolean isQualifiedThis(@NonNull VariableExp asVariableExp, @NonNull Parameter asParameter) {
		return false;
	} */

	/**
	 * Return true if asParameter is a 'this' parameter.
	 *
	protected boolean isThis(@NonNull Parameter asParameter) {
		return JavaConstants.THIS_NAME.equals(asParameter.getName());
	} */

	/*	@Override
	public @Nullable CGElement visitAssociationClassCallExp(@NonNull AssociationClassCallExp element) {
//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitAssociationClassCallExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitBooleanLiteralExp(@NonNull BooleanLiteralExp element) {
		CGConstant constant = context.getCGBoolean(element.isBooleanSymbol());
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull CGClass visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = context.generateClass(null, asClass);
	/*	org.eclipse.ocl.pivot.Package asCachePackage = AbstractLanguageSupport.basicGetCachePackage(asClass);
		if (asCachePackage != null) {
			List<org.eclipse.ocl.pivot.@NonNull Class> saved = Lists.newArrayList(PivotUtil.getOwnedClasses(asCachePackage));	// XXX
			for (org.eclipse.ocl.pivot.@NonNull Class asCacheClass : PivotUtil.getOwnedClasses(asCachePackage)) {
				System.out.println("visitClass-sub " + NameUtil.debugSimpleName(asClass) + " : " + asCacheClass);
				CGClass cgCacheClass = context.createCGElement(CGClass.class, asCacheClass);
				assert cgClass.getClasses().contains(cgCacheClass);
			}
		} */
		return cgClass;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionItem(@NonNull CollectionItem asElement) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		context.initAst(cgCollectionPart, asElement, true);
		cgCollectionPart.setFirst(context.createCGElement(CGValuedElement.class, asElement.getOwnedItem()));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGCollectionExp visitCollectionLiteralExp(@NonNull CollectionLiteralExp asCollectionLiteralExp) {
		CGCollectionExp cgCollectionExp = CGModelFactory.eINSTANCE.createCGCollectionExp();
		context.initAst(cgCollectionExp, asCollectionLiteralExp, true);
		List<CGCollectionPart> cgParts = cgCollectionExp.getParts();
		for (@NonNull CollectionLiteralPart asPart : ClassUtil.nullFree(asCollectionLiteralExp.getOwnedParts())) {
			cgParts.add(context.createCGElement(CGCollectionPart.class, asPart));
		}
		return cgCollectionExp;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionRange(@NonNull CollectionRange asElement) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		cgCollectionPart.setAst(asElement);
		cgCollectionPart.setTypeId(context.getCGTypeId(TypeId.INTEGER_RANGE));
		cgCollectionPart.setFirst(context.createCGElement(CGValuedElement.class, asElement.getOwnedFirst()));
		cgCollectionPart.setLast(context.createCGElement(CGValuedElement.class, asElement.getOwnedLast()));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint asConstraint) {
		return context.generateConstraint(asConstraint);
	}

	@Override
	public @Nullable CGConstantExp visitEnumLiteralExp(@NonNull EnumLiteralExp element) {
		//		CGConstant constant = context.getEnumerationLiteral(element.getReferredEnumLiteral());
		CGConstant constant = context.getCGElementId(element.getReferredLiteral().getEnumerationLiteralId());
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL query) {		// XXX Is this override necessary or even appropriate for nested ExpressionInOCL ?
		assert query.getOwnedBody() != null;
		ExecutableNameManager nameManager = context.useExecutableNameManager(query);
		Variable contextVariable = query.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = nameManager.getThisParameter();
			nameManager.addVariable(contextVariable, cgContext);
			cgContext.setNonInvalid();
			//			cgContext.setNonNull();
		}
		Iterable<@NonNull ParameterVariable> asParameters = (Iterable<@NonNull ParameterVariable>)(Object)PivotUtil.getOwnedParameters(query);
		for (@NonNull Variable parameterVariable : asParameters) {
			CGVariable cgParameterVariable = nameManager.basicGetCGParameterVariable(parameterVariable);
			if (cgParameterVariable == null) {				// XXX May be pre-mapped to a CG let variable
				@SuppressWarnings("unused") CGVariable cgParameter = nameManager.getCGParameter(parameterVariable, null);
			}
		//	@SuppressWarnings("unused") CGVariable cgParameter = nameManager.getParameter(parameterVariable, null);
		}
		CGValuedElement cgBody = context.createCGElement(CGValuedElement.class, query.getOwnedBody());
		//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	}

	@Override
	public @NonNull CGIfExp visitIfExp(@NonNull IfExp asIfExp) {
		CGValuedElement cgCondition = context.createCGElement(CGValuedElement.class, asIfExp.getOwnedCondition());
		CGValuedElement cgThenExpression = context.createCGElement(CGValuedElement.class, asIfExp.getOwnedThen());
		CGValuedElement cgElseExpression = context.createCGElement(CGValuedElement.class, asIfExp.getOwnedElse());
		CGIfExp cgIfExp = context.createCGIfExp(cgCondition, cgThenExpression, cgElseExpression);
		context.initAst(cgIfExp, asIfExp, true);
		return cgIfExp;
	}

	@Override
	public @Nullable CGConstantExp visitIntegerLiteralExp(@NonNull IntegerLiteralExp element) {
		Number integerSymbol = element.getIntegerSymbol();
		CGInteger constant = context.getCGInteger(integerSymbol != null ? integerSymbol : 0);
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGConstantExp visitInvalidLiteralExp(@NonNull InvalidLiteralExp element) {
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, context.getCGInvalid());
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGLetExp visitLetExp(@NonNull LetExp asLetExp) {
		Variable asVariable = PivotUtil.getOwnedVariable(asLetExp);
		CGFinalVariable cgVariable = context.useExecutableNameManager(asLetExp).createCGVariable(asVariable);		// FIXME Lose cast
		CGValuedElement cgInit = context.createCGElement(CGValuedElement.class, asVariable.getOwnedInit());
		context.setCGVariableInit(cgVariable, cgInit);
		CGValuedElement cgIn = context.createCGElement(CGValuedElement.class, asLetExp.getOwnedIn());
		CGLetExp cgLetExp = context.createCGLetExp(asLetExp, cgVariable, cgIn);
		NameResolution inNameResolution = cgIn.basicGetNameResolution();
		if (inNameResolution != null) {
			inNameResolution.addCGElement(cgLetExp);
		}
		return cgLetExp;
	}

	@Override
	public final @NonNull CGValuedElement visitLoopExp(@NonNull LoopExp asLoopExp) {
		return context.generateLoopExp(asLoopExp);
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralExp(@NonNull MapLiteralExp element) {
		CGMapExp cgMapExp = CGModelFactory.eINSTANCE.createCGMapExp();
		context.initAst(cgMapExp, element, true);
		List<@NonNull CGMapPart> cgParts = ClassUtil.nullFree(cgMapExp.getParts());
		for (@NonNull MapLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(context.createCGElement(CGMapPart.class, asPart));
		}
		context.getCGTypeId(element.getTypeId());
		return cgMapExp;
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralPart(@NonNull MapLiteralPart element) {
		CGMapPart cgMapPart = CGModelFactory.eINSTANCE.createCGMapPart();
		cgMapPart.setAst(element);
		cgMapPart.setTypeId(context.getCGTypeId(TypeId.MAP_ENTRY));
		cgMapPart.setKey(context.createCGElement(CGValuedElement.class, element.getOwnedKey()));
		cgMapPart.setValue(context.createCGElement(CGValuedElement.class, element.getOwnedValue()));
		return cgMapPart;
	}

	/*	@Override
	public @Nullable CGElement visitMessageExp(@NonNull MessageExp element) {
//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitMessageExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitNullLiteralExp(@NonNull NullLiteralExp element) {
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, context.getCGNull());
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGOperation visitOperation(@NonNull Operation asOperation) {
		if (asOperation.toString().contains("ENTRY_Parent_static_count(")) {
			getClass();		// XXX
		}
		CGOperation cgOperation = context.basicGetCGOperation(asOperation);
		if (cgOperation == null) {
			cgOperation = context.generateOperation(asOperation);
		}
		cgOperation.getCallingConvention().createCGBody(context, cgOperation);
		return cgOperation;
	}

	@Override
	public final @NonNull CGValuedElement visitOperationCallExp(@NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		if (asSource == null) {
			return context.generateOperationCallExp(null, asOperationCallExp);
		}
		CGValuedElement cgSource = context.createCGElement(CGValuedElement.class, asSource);
		if (!asOperationCallExp.isIsSafe()) {// && !cgSource.isNonNull()) {
			return context.generateOperationCallExp(cgSource, asOperationCallExp);
		}
		Type sourceType = asSource.getType();
		if (sourceType instanceof CollectionType) {
			if (asOperationCallExp.isIsSafe()) {
				cgSource = context.generateSafeExclusion(asOperationCallExp, cgSource);
			}
			return context.generateOperationCallExp(cgSource, asOperationCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : context.useExecutableNameManager(asOperationCallExp).createCGVariable(cgSource);
		CGVariableExp cgVariableExp1 = context.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = context.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = context.generateOperationCallExp(cgVariableExp1, asOperationCallExp);
		CGIfExp cgIfExp = context.generateSafeNavigationGuard(asOperationCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : context.createCGLetExp(asOperationCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public final @NonNull CGValuedElement visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
		OCLExpression asSource = ClassUtil.nonNullModel(asOppositePropertyCallExp.getOwnedSource());
		CGValuedElement cgSource = context.createCGElement(CGValuedElement.class, asSource);
		if (!asOppositePropertyCallExp.isIsSafe()) {
			return context.generateOppositePropertyCallExp(cgSource, asOppositePropertyCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : context.useExecutableNameManager(asOppositePropertyCallExp).createCGVariable(cgSource);
		CGVariableExp cgVariableExp1 = context.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = context.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = context.generateOppositePropertyCallExp(cgVariableExp1, asOppositePropertyCallExp);
		CGIfExp cgIfExp = context.generateSafeNavigationGuard(asOppositePropertyCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : context.createCGLetExp(asOppositePropertyCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public @Nullable CGNamedElement visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CGPackage cgPackage = context.basicGetCGPackage(asPackage);
		if (cgPackage == null) {
			cgPackage = context.generatePackageDeclaration(asPackage);
		}
		for (org.eclipse.ocl.pivot.@NonNull Class asType : ClassUtil.nullFree(asPackage.getOwnedClasses())) {
			CGClass cgClass = context.createCGElement(CGClass.class, asType);
			assert cgClass.eContainer().eContainer() == cgPackage.eContainer();			// asClass may be a psuedo-nested class
		}
		for (org.eclipse.ocl.pivot.@NonNull Package asNestedPackage : ClassUtil.nullFree(asPackage.getOwnedPackages())) {
			CGPackage cgNestedPackage = context.createCGElement(CGPackage.class, asNestedPackage);
			assert cgPackage.getPackages().contains(cgNestedPackage);
		}
		return cgPackage;
	}

	@Override
	public final @NonNull CGProperty visitProperty(@NonNull Property asProperty) {
		CGProperty cgProperty = context.basicGetCGProperty(asProperty);
		if (cgProperty == null) {
			cgProperty = context.generateProperty(asProperty);
		}
		return cgProperty;
	}

	@Override
	public final @NonNull CGValuedElement visitPropertyCallExp(@NonNull PropertyCallExp asPropertyCallExp) {
		OCLExpression asSource = asPropertyCallExp.getOwnedSource();
		if (asSource == null) {
			return context.generatePropertyCallExp(null, asPropertyCallExp);
		}
		CGValuedElement cgSource = context.createCGElement(CGValuedElement.class, asSource);
		if (!asPropertyCallExp.isIsSafe()) {
			return context.generatePropertyCallExp(cgSource, asPropertyCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : context.useExecutableNameManager(asPropertyCallExp).createCGVariable(cgSource);
		CGVariableExp cgVariableExp1 = context.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = context.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = context.generatePropertyCallExp(cgVariableExp1, asPropertyCallExp);
		CGIfExp cgIfExp = context.generateSafeNavigationGuard(asPropertyCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : context.createCGLetExp(asPropertyCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public @Nullable CGConstantExp visitRealLiteralExp(@NonNull RealLiteralExp element) {
		Number realSymbol = element.getRealSymbol();
		@SuppressWarnings("null")
		CGReal cgReal = context.getCGReal(realSymbol != null ? realSymbol : Double.valueOf(0.0));
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, cgReal);
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGShadowExp visitShadowExp(@NonNull ShadowExp element) {
		CGShadowExp cgShadowExp = null;
		Type type = element.getType();
		if (type != null) {
			CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass(type);
			EObject eTarget = null;
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : ClassUtil.nullFree(completeClass.getPartialClasses())) {
				eTarget = partialClass.getESObject();
				if (eTarget != null) {
					break;
				}
			}
			if (eTarget instanceof EDataType) {
				CGEcoreDataTypeShadowExp cgEShadowExp = CGModelFactory.eINSTANCE.createCGEcoreDataTypeShadowExp();
				cgEShadowExp.setEDataType((EDataType)eTarget);
				cgShadowExp = cgEShadowExp;
			}
			else if (eTarget instanceof EClass) {
				CGEcoreClassShadowExp cgEShadowExp = CGModelFactory.eINSTANCE.createCGEcoreClassShadowExp();
				cgEShadowExp.setEClass((EClass)eTarget);
				cgShadowExp = cgEShadowExp;
			}
		}
		if (cgShadowExp != null) {
			ExecutableNameManager executableNameManager = context.useExecutableNameManager(element);
			CGExecutorType cgExecutorType = executableNameManager.getCGExecutorType(ClassUtil.nonNullState(element.getType()));
			cgShadowExp.setExecutorType(cgExecutorType);
			cgShadowExp.getOwns().add(cgExecutorType);
			context.initAst(cgShadowExp, element, true);
			List<@NonNull ShadowPart> asParts = new ArrayList<>(ClassUtil.nullFree(element.getOwnedParts()));
			Collections.sort(asParts, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull CGShadowPart> cgParts = ClassUtil.nullFree(cgShadowExp.getParts());		// Ensure deterministic CGShadowPart order
			for (@NonNull ShadowPart asPart : asParts) {
				cgParts.add(context.createCGElement(CGShadowPart.class, asPart));
			}
		}
		return cgShadowExp;
	}

	@Override
	public @Nullable CGShadowPart visitShadowPart(@NonNull ShadowPart element) {
	//	throw new UnsupportedOperationException();
		CGShadowPart cgShadowPart = CGModelFactory.eINSTANCE.createCGShadowPart();
		context.initAst(cgShadowPart, element, true);
		cgShadowPart.setInit(context.createCGElement(CGValuedElement.class, element.getOwnedInit()));
		Property asProperty = element.getReferredProperty();
		if (asProperty != null) {
		//	CGExecutorShadowPart cgExecutorShadowPart = createExecutorShadowPart(asProperty);
		//	cgExecutorShadowPart.setCallingConvention(ExecutorShadowPartCallingConvention.getInstance());
		//	CGExecutorShadowPart cgExecutorShadowPart = (CGExecutorShadowPart)generatePropertyDeclaration(asProperty, ExecutorShadowPartCallingConvention.getInstance());
			PropertyId propertyId = asProperty.getPropertyId();
			CGExecutorShadowPart cgExecutorShadowPart = CGModelFactory.eINSTANCE.createCGExecutorShadowPart();
			CGElementId cgPropertyId = context.getCGElementId(propertyId);
			cgExecutorShadowPart.setUnderlyingPropertyId(cgPropertyId);
			cgExecutorShadowPart.setAst(asProperty);
			globalNameManager.getNameResolution(cgExecutorShadowPart);
			cgExecutorShadowPart.setTypeId(context.getCGTypeId(JavaConstants.PROPERTY_TYPE_ID));
			cgExecutorShadowPart.setCallingConvention(ExecutorShadowPartCallingConvention.getInstance(asProperty));
			cgExecutorShadowPart.getDependsOn().add(cgPropertyId);
			cgShadowPart.setExecutorPart(cgExecutorShadowPart);
		}
		return cgShadowPart;
	}

	@Override
	public @Nullable CGNamedElement visitStateExp(@NonNull StateExp element) {
		//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitStateExp(element);
	}

	@Override
	public @Nullable CGConstantExp visitStringLiteralExp(@NonNull StringLiteralExp element) {
		String stringSymbol = element.getStringSymbol();
		CGString cgString = context.getCGString(stringSymbol != null ? stringSymbol : "");
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, cgString);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGTupleExp visitTupleLiteralExp(@NonNull TupleLiteralExp element) {
		CGTupleExp cgTupleExp = CGModelFactory.eINSTANCE.createCGTupleExp();
		context.initAst(cgTupleExp, element, true);
		List<@NonNull CGTuplePart> cgParts = new ArrayList<>();
		for (@NonNull TupleLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(context.createCGElement(CGTuplePart.class, asPart));
		}
		Collections.sort(cgParts, CGTuplePartNameComparator.INSTANCE);
		cgTupleExp.getParts().addAll(cgParts);
		context.getCGTypeId(element.getTypeId());
		return cgTupleExp;
	}

	@Override
	public @Nullable CGTuplePart visitTupleLiteralPart(@NonNull TupleLiteralPart element) {
		CGTuplePart cgTuplePart = CGModelFactory.eINSTANCE.createCGTuplePart();
		context.initAst(cgTuplePart, element, true);
		cgTuplePart.setInit(context.createCGElement(CGValuedElement.class, element.getOwnedInit()));
		TuplePartId partId = element.getPartId();
		if (partId != null) {
			context.getCGElementId(partId);
		}
		return cgTuplePart;
	}

	@Override
	public @Nullable CGValuedElement visitTypeExp(@NonNull TypeExp asTypeExp) {
		ExecutableNameManager executableNameManager = context.useExecutableNameManager(asTypeExp);
		Type referredType = PivotUtil.getReferredType(asTypeExp);
		if (!(referredType instanceof TemplateParameter)) {
			CGTypeExp cgTypeExp = CGModelFactory.eINSTANCE.createCGTypeExp();
			context.initAst(cgTypeExp, asTypeExp, true);
			CGExecutorType cgExecutorType = executableNameManager.getCGExecutorType(referredType);
			cgTypeExp.setExecutorType(cgExecutorType);
			cgTypeExp.getOwns().add(cgExecutorType);	// FIXME this ownership inhibits ExecutableNameManager sharing
			return cgTypeExp;
		}
		TemplateParameter referredTemplateParameter = (TemplateParameter)referredType;
		TemplateSignature templateSignature = PivotUtil.getOwningSignature(referredTemplateParameter);
		TemplateableElement asTemplateableElement = PivotUtil.getOwningElement(templateSignature);
		CGValuedElement cgTemplateableElement;
		if (asTemplateableElement instanceof Type) {
			cgTemplateableElement = executableNameManager.getCGExecutorType((Type)asTemplateableElement);
		}
	//	else if (asTemplateableElement instanceof Operation) {
	//		cgTemplateableElement = context.createExecutorOperation((Operation)asTemplateableElement);
	//	}
		else {
			codeGenerator.addProblem(new UnsupportedOperationException("visitTypeExp for non-Type Templateparameter"));
			return null;
		}
		int index = templateSignature.getOwnedParameters().indexOf(referredTemplateParameter);
		CGTemplateParameterExp cgTemplateParameterExp = CGModelFactory.eINSTANCE.createCGTemplateParameterExp();
		cgTemplateParameterExp.setIndex(index);
		context.initAst(cgTemplateParameterExp, asTypeExp, true);
		cgTemplateParameterExp.setTemplateableElement(cgTemplateableElement);
		cgTemplateParameterExp.getOwns().add(cgTemplateableElement);
		return cgTemplateParameterExp;
	}

	@Override
	public @Nullable CGConstantExp visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp element) {
		Number unlimitedNaturalSymbol = element.getUnlimitedNaturalSymbol();
		CGConstantExp cgLiteralExp;
		if (unlimitedNaturalSymbol instanceof UnlimitedValue) {
			cgLiteralExp = context.createCGConstantExp(element, context.getCGUnlimited());
		}
		else if (unlimitedNaturalSymbol instanceof Unlimited) {
			cgLiteralExp = context.createCGConstantExp(element, context.getCGUnlimited());
		}
		else if (unlimitedNaturalSymbol != null) {
			cgLiteralExp = context.createCGConstantExp(element, context.getCGInteger(unlimitedNaturalSymbol));
		}
		else {
			cgLiteralExp = context.createCGConstantExp(element, context.getCGInteger(0));
		}
		context.initAst(cgLiteralExp, element, true);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGNamedElement visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp element) {
		//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitUnspecifiedValueExp(element);
	}

	@Override
	public @Nullable CGValuedElement visitVariableExp(@NonNull VariableExp asVariableExp) {
		return context.generateVariableExp(asVariableExp);
	}

	@Override
	public @Nullable CGValuedElement visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}
}
