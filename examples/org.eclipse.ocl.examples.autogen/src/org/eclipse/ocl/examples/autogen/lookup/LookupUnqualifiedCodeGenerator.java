/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations, University of York and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Adolfo Sanchez-Barbudo Herrera (University of York)
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.java.AutoCG2JavaVisitor;
import org.eclipse.ocl.examples.autogen.java.AutoCodeGenerator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.library.NativeStaticOperation;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class LookupUnqualifiedCodeGenerator extends LookupVisitorsCodeGenerator {

	protected final @NonNull PivotHelper helper;
	//
	//	Expected AS elements
	//
	protected final @NonNull Operation asEnvironmentHasFinalResultOperation;
	protected final @NonNull Operation asEnvironmentNestedEnvOperation;
	protected final @NonNull Operation asElementParentEnvOperation;

	//
	//	New AS elements
	//
	protected Property asChildProperty;
	protected Operation asVisitorEnvOperation;
	protected Operation asVisitorParentEnvOperation;

	// Further required CG elements
	private @Nullable CGProperty cgChildProperty = null;

	protected LookupUnqualifiedCodeGenerator(
			@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull Package asPackage, @Nullable Package asSuperPackage,
			@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage) {
		this(environmentFactory, asPackage, asSuperPackage, asBasePackage, genPackage,
			superGenPackage, baseGenPackage, LookupVisitorsClassContext.UNQUALIFIED_ENV_NAME);
	}

	protected LookupUnqualifiedCodeGenerator(
			@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull Package asPackage, @Nullable Package asSuperPackage,
			@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage,
			@NonNull String envOperationName) {
		super(environmentFactory, asPackage, asSuperPackage, asBasePackage, genPackage,
			superGenPackage, baseGenPackage, envOperationName);
		this.helper = new PivotHelper(environmentFactory);
		ParametersId emptyParametersId = IdManager.getParametersId();
		org.eclipse.ocl.pivot.Class asOclElement = metamodelManager.getStandardLibrary().getOclElementType();
		CompleteClass asElementCompleteClass = metamodelManager.getCompletePackage(metamodelManager.getStandardLibrary().getPackage()).getCompleteClass(asOclElement);

		String parentEnvOpName = ClassUtil.nonNull(envOperationName.replace(LookupVisitorsClassContext.UNQUALIFIED_ENV_NAME, LookupVisitorsClassContext.PARENT_ENV_NAME));
		OperationId parentEnvOperationId = asOclElement.getTypeId().getOperationId(0, parentEnvOpName , emptyParametersId);
		this.asElementParentEnvOperation = ClassUtil.nonNullState(asElementCompleteClass.getOperation(parentEnvOperationId));
		CompleteClass asEnvironmentCompleteClass = metamodelManager.getCompleteClass(asEnvironmentType);
		OperationId nestedEnvOperationId = asEnvironmentType.getTypeId().getOperationId(0, LookupVisitorsClassContext.NESTED_ENV_NAME, emptyParametersId);
		this.asEnvironmentNestedEnvOperation = ClassUtil.nonNullState(asEnvironmentCompleteClass.getOperation(nestedEnvOperationId));
		OperationId hasFinalResultOperationId = asEnvironmentType.getTypeId().getOperationId(0, LookupVisitorsClassContext.HAS_FINAL_RESULT_NAME, emptyParametersId);
		this.asEnvironmentHasFinalResultOperation = ClassUtil.nonNullState(asEnvironmentCompleteClass.getOperation(hasFinalResultOperationId));
	}

	@Override
	protected @NonNull AutoCG2JavaVisitor<@NonNull ? extends AutoCodeGenerator> createCG2JavaVisitor(
			@NonNull CGPackage cgPackage,
			@Nullable List<CGValuedElement> sortedGlobals) {
		return new LookupUnqualifiedCG2JavaVisitor(this, cgPackage, sortedGlobals);
	}

	@Override
	@NonNull
	protected String getLookupVisitorClassName(@NonNull String prefix) {
		// Extract type name.
		String typeName = extractTypeNameFromEnvOp(LookupVisitorsClassContext.UNQUALIFIED_ENV_NAME);
		return prefix + "Unqualified" + typeName + "LookupVisitor";
	}

	@Override
	protected List<Property> createAdditionalASProperties() {
		Type asOclElement = metamodelManager.getStandardLibrary().getOclElementType();
		this.asChildProperty = createNativeProperty(LookupVisitorsClassContext.CHILD_NAME, asOclElement, false, true);
		return Collections.singletonList(asChildProperty);
	}

	@Override
	protected Collection<? extends Operation> createAdditionalASOperations() {
		List<Operation> result = new ArrayList<Operation>();
		Type asOclElement = metamodelManager.getStandardLibrary().getOclElementType();
		this.asVisitorEnvOperation = PivotUtil.createOperation(envOperationName, asEnvironmentType, null, null);
		asVisitorEnvOperation.getOwnedParameters().add(PivotUtil.createParameter(LookupVisitorsClassContext.ELEMENT_NAME, asOclElement, true));
		asVisitorEnvOperation.getOwnedParameters().add(PivotUtil.createParameter(LookupVisitorsClassContext.CHILD_NAME, asOclElement, false));
		this.asVisitorParentEnvOperation = PivotUtil.createOperation(LookupVisitorsClassContext.PARENT_ENV_NAME, asEnvironmentType, null, null);
		asVisitorParentEnvOperation.getOwnedParameters().add(PivotUtil.createParameter(LookupVisitorsClassContext.ELEMENT_NAME, asOclElement, true));
		asVisitorParentEnvOperation.setImplementation(NativeStaticOperation.INSTANCE);
		asVisitorParentEnvOperation.setIsRequired(false);
		result.add(asVisitorEnvOperation);
		result.add(asVisitorParentEnvOperation);
		return result;
	}

	/**
	 * Convert source.env(child) to this.env(source, child)
	 */
	protected void rewriteEnvOperationCall(@NonNull OperationCallExp asOperationCallExp, @NonNull Operation asVisitorEnvOperation, @NonNull Variable asThisVariable) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		asOperationCallExp.setOwnedSource(createThisVariableExp(asThisVariable));
		asOperationCallExp.getOwnedArguments().add(0, asSource);
		asOperationCallExp.setReferredOperation(asVisitorEnvOperation);
	}

	/**
	 * Convert "source.nestedEnv().r.e.s.i.d.u.e" to
	 * "let innerEnv = this.context.r.e.s.i.d.u.e in if innerEnv.hasFinalResult() then innerEnv else source endif"
	 *  where r.e.s.i.d.u.e does not include any nestedEnv() call.
	 *
	 *  "source.nestedEnv()" to "source"
	 */
	protected void rewriteNestedEnvOperationCall(@NonNull OperationCallExp asOperationCallExp) {
		CallExp asOuterCallExp = asOperationCallExp;
		for (EObject eContainer; (asOuterCallExp.eContainmentFeature() == PivotPackage.Literals.CALL_EXP__OWNED_SOURCE) && ((eContainer = asOuterCallExp.eContainer()) != null); asOuterCallExp = (CallExp)eContainer) {
			if (eContainer instanceof OperationCallExp) {
				OperationCallExp asParentOperationCallExp = (OperationCallExp)eContainer;
				Operation asReferredOperation = asParentOperationCallExp.getReferredOperation();
				if (asReferredOperation == asEnvironmentNestedEnvOperation) {
					break;
				}
			}
		}
		EObject eContainer = asOuterCallExp.eContainer();
		EReference eContainingFeature = asOuterCallExp.eContainmentFeature();		// This is not isMany()
		eContainer.eSet(eContainingFeature, null);									// asOuterCallExp becomes an orphan
		OCLExpression asSource = ClassUtil.nonNullState(asOperationCallExp.getOwnedSource());
		if (asOuterCallExp != asOperationCallExp) {
			CallExp asInnerCallExp = (CallExp)asOperationCallExp.eContainer();
			VariableExp asContextExp = helper.createVariableExp(asContextVariable);
			asInnerCallExp.setOwnedSource(asContextExp);										// asOperationCallExp becomes an orphan
			Variable asInnerEnv = helper.createLetVariable("inner", asOuterCallExp);
			VariableExp asInnerEnvExp1 = helper.createVariableExp(asInnerEnv);
			VariableExp asInnerEnvExp2 = helper.createVariableExp(asInnerEnv);
			OperationCallExp asCondition = helper.createOperationCallExp(asInnerEnvExp1, asEnvironmentHasFinalResultOperation, Collections.emptyList());
			IfExp asIfExp = metamodelManager.createIfExp(asCondition, asInnerEnvExp2, asSource);
			LetExp asLetExp = PivotUtil.createLetExp(asInnerEnv, asIfExp);
			eContainer.eSet(eContainingFeature, asLetExp);
		}
		else {
			eContainer.eSet(eContainingFeature, asSource);
		}
	}

	/**
	 * Convert source.parentEnv() to this.parentEnv(source)
	 */
	protected void rewriteParentEnvOperationCall(@NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		asOperationCallExp.setOwnedSource(createThisVariableExp(asThisVariable));
		asOperationCallExp.getOwnedArguments().add(asSource);
		asOperationCallExp.setReferredOperation(asVisitorParentEnvOperation);
	}

	protected boolean sameOrRedefiningOperation(@NonNull Operation redefiningOperation, @NonNull Operation baseOperation) {

		// calledOperation.getRedefinedOperations().contains(baseOperation);
		// Note: the own operation seems to be an "overload"
		for (Operation redefinedOp :  metamodelManager.getOperationOverloads(redefiningOperation)) {
			if (baseOperation == redefinedOp) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void rewriteOperationCalls(@NonNull Collection<? extends EObject> allContents) {
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : EcoreUtil.CrossReferencer.find(allContents).entrySet()) {
			EObject crossReference = entry.getKey();
			if (crossReference instanceof Operation) {
				Operation asOperation = metamodelManager.getPrimaryOperation((Operation)crossReference);
				if (sameOrRedefiningOperation(asOperation, asElementEnvOperation)) {
					for (EStructuralFeature.Setting setting : entry.getValue()) {
						EObject eObject = setting.getEObject();
						if (eObject instanceof OperationCallExp) {
							rewriteEnvOperationCall((OperationCallExp)eObject, ClassUtil.nonNullState(asVisitorEnvOperation)
								, asThisVariable);
						}
					}
				}
				else if (sameOrRedefiningOperation(asOperation, asEnvironmentNestedEnvOperation)) {
					for (EStructuralFeature.Setting setting : entry.getValue()) {
						EObject eObject = setting.getEObject();
						if (eObject instanceof OperationCallExp) {
							rewriteNestedEnvOperationCall((OperationCallExp)eObject);
						}
					}
				}
				else if (sameOrRedefiningOperation(asOperation, asElementParentEnvOperation)) {
					for (EStructuralFeature.Setting setting : entry.getValue()) {
						EObject eObject = setting.getEObject();
						if (eObject instanceof OperationCallExp) {
							rewriteParentEnvOperationCall((OperationCallExp)eObject);
						}
					}
				}
			}

		}
	}

	@Override
	protected boolean isRewrittenOperation(Operation operation) {
		return envOperationName.equals(operation.getName())
				&& operation != asElementEnvOperation
				&& operation.getOwnedParameters().size() ==1;
	}

	/**
	 * Convert  'Element'::_env(child : Element) : Environment
	 * to AutoPivotLookupVisitor::visit'Element'(element : 'Element') : Environment
	 *
	 * with
	 *     - self accessed as element.
	 *     - child accessed as this.child.
	 * @throws ParserException
	 */
	@Override
	protected @NonNull Operation createVisitOperationDeclaration(
			Map<Element, Element> reDefinitions, Operation operation) {

		ExpressionInOCL envExpressionInOCL = getExpressionInOCL(operation);
		//
		org.eclipse.ocl.pivot.Class asType = ClassUtil.nonNullState(operation.getOwningClass());
		Variable asElement = helper.createParameterVariable(LookupVisitorsClassContext.ELEMENT_NAME, asType, true);
		reDefinitions.put(envExpressionInOCL.getOwnedContext(), asElement);
		//
		VariableExp asChildSource = createThisVariableExp(asThisVariable);
		PropertyCallExp asChildAccess = PivotUtil.createPropertyCallExp(asChildSource, ClassUtil.nonNullState(asChildProperty));
		Variable asChild = helper.createLetVariable(LookupVisitorsClassContext.CHILD_NAME, asChildAccess);
		reDefinitions.put(envExpressionInOCL.getOwnedParameters().get(0), asChild);
		//
		Operation asOperation = createVisitorOperation("visit" + asType.getName(), operation.getType());
		reDefinitions.put(operation, asOperation);
		return asOperation;
	}

	@Override
	protected void trackCGProperty(Property asProperty, CGProperty cgProperty) {
		if (asProperty == asChildProperty) {
			cgChildProperty = cgProperty;
		}
	}

	public @NonNull CGProperty getChildProperty() {
		return ClassUtil.nonNullState(cgChildProperty);
	}
}
