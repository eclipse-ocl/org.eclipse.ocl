/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.java.AutoCG2JavaPreVisitor;
import org.eclipse.ocl.examples.autogen.java.AutoVisitorsCodeGenerator;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModel;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.library.NativeVisitorOperation;
import org.eclipse.ocl.examples.codegen.utilities.RereferencingCopier;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * LookupCodeGenerator supports generation of the content of a JavaClassFile for the Lookup visitor.
 */
public abstract class LookupVisitorsCodeGenerator extends AutoVisitorsCodeGenerator
{
	protected final @NonNull String packageName;
	protected final @NonNull String visitorClassName;

	protected final @NonNull LookupVisitorsClassContext classContext;
	protected final @NonNull AS2CGVisitor as2cgVisitor;

	protected final @NonNull String envOperationName;
	//
	//	Expected AS elements
	//
	protected final @NonNull Operation asElementEnvOperation;
	protected final org.eclipse.ocl.pivot.@NonNull Class asEnvironmentType;

	//
	//	New AS elements
	//
	protected final org.eclipse.ocl.pivot.@NonNull Class asVisitorClass;
	protected final @NonNull Variable asThisVariable;
	protected final @NonNull Variable asContextVariable;
	protected final @NonNull Property asEvaluatorProperty;
	protected final @NonNull Property asIdResolverProperty;

	//
	//	Important CG elements
	//
	private @Nullable CGProperty cgEvaluatorVariable = null;
	private @Nullable CGProperty cgIdResolverVariable = null;

	protected LookupVisitorsCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, org.eclipse.ocl.pivot.@NonNull Package asPackage,
			org.eclipse.ocl.pivot.@Nullable Package asSuperPackage, org.eclipse.ocl.pivot.@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage, @Nullable GenPackage baseGenPackage, @NonNull String envOpName) {
		super(environmentFactory, asPackage, asSuperPackage, genPackage, superGenPackage, baseGenPackage);
		this.envOperationName = envOpName;
		this.packageName = getSourcePackageName();
		this.visitorClassName = getLookupVisitorClassName(getProjectPrefix());

		this.classContext = new LookupVisitorsClassContext(this, asPackage);
		this.as2cgVisitor = createAS2CGVisitor();
		//
		//	Find expected AS elements
		//
		org.eclipse.ocl.pivot.Class asOclElement = metamodelManager.getStandardLibrary().getOclElementType();
		// org.eclipse.ocl.pivot.Class asOclAny = metamodelManager.getStandardLibrary().getOclAnyType();
		CompleteClass asElementCompleteClass = metamodelManager.getCompletePackage(metamodelManager.getStandardLibrary().getPackage()).getCompleteClass(asOclElement);
		OperationId envOperationId = asOclElement.getTypeId().getOperationId(0, envOpName, IdManager.getParametersId(asOclElement.getTypeId()));
		this.asElementEnvOperation = ClassUtil.nonNullState(asElementCompleteClass.getOperation(envOperationId));
		this.asEnvironmentType = ClassUtil.nonNullState(asElementEnvOperation.getType().isClass());

		//
		//	Create new AS elements
		//
		org.eclipse.ocl.pivot.Package asVisitorPackage = createASPackage(packageName);
		this.asVisitorClass = createASClass(asVisitorPackage, visitorClassName);
		this.asThisVariable = helper.createParameterVariable("this", asVisitorClass, true);
		this.asContextVariable = helper.createParameterVariable(LookupVisitorsClassContext.CONTEXT_NAME, asEnvironmentType, true);
		CGVariable cgVariable = as2cgVisitor.getVariable(asContextVariable);
		nameManager.reserveName(LookupVisitorsClassContext.CONTEXT_NAME, cgVariable);

		//
		//	Create new AS Visitor helper properties
		//
		this.asEvaluatorProperty = createNativeProperty(JavaConstants.EXECUTOR_NAME, Executor.class, true, true);
		this.asIdResolverProperty = createNativeProperty(JavaConstants.ID_RESOLVER_NAME, IdResolver.class, true, true);
		List<Property> asVisitorProperties = asVisitorClass.getOwnedProperties();
		asVisitorProperties.add(asEvaluatorProperty);
		asVisitorProperties.add(asIdResolverProperty);
		asVisitorProperties.addAll(createAdditionalASProperties());

		//
		//	Create new AS Visitor helper operations
		//
		List<Operation> asVisitorOperations = asVisitorClass.getOwnedOperations();
		asVisitorOperations.addAll(createAdditionalASOperations());
	}



	protected Collection<? extends Operation> createAdditionalASOperations() {
		// By default no additional properties
		return Collections.emptyList();
	}

	protected List<Property> createAdditionalASProperties() {
		// By default no additional properties
		return Collections.emptyList();
	}

	/**
	 * Convert the construction context to supertypes/interfaces of cgClass.
	 */
	protected void convertSuperTypes(@NonNull CGClass cgClass) {

		String superClassName = "Abstract" + getProjectPrefix() + "CommonLookupVisitor"; // The default Abstract Visitor generated for the language
		CGClass superClass = getExternalClass(getVisitorPackageName(), superClassName, false);
		cgClass.getSuperTypes().add(superClass);
	}

	@Override
	public @NonNull CG2JavaPreVisitor createCG2JavaPreVisitor() {
		return new AutoCG2JavaPreVisitor(classContext);
	}

	/**
	 * Synthesize an AS package by simple AS2AS conversions and convert the AS package to a CG package for onward code generation.
	 * @throws ParserException
	 */
	@Override
	protected @NonNull List<CGPackage> createCGPackages() throws ParserException {
		CGModel cgModel = CGModelFactory.eINSTANCE.createCGModel();

		String visitorPackage = getSourcePackageName();
		Map<Element,Element> reDefinitions = new HashMap<Element,Element>();
		createCGPackageForVisitor(cgModel, visitorPackage, visitorClassName, asVisitorClass,
			createVisitOperationDeclarations(reDefinitions), reDefinitions);
		List<CGPackage> result = new ArrayList<CGPackage>();
		result.addAll(cgModel.getPackages());
		return result;
	}

	protected void createCGPackageForVisitor(@NonNull CGModel cgModel, @NonNull String packageName, @NonNull String classNAme, org.eclipse.ocl.pivot.@NonNull Class asClass,
			@NonNull Map<Operation, @NonNull Operation> envOperation2asOperation, @NonNull Map<Element,Element> reDefinitions) throws ParserException {

		CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
		cgPackage.setName(getVisitorPackageName());
		cgModel.getPackages().add(cgPackage);
		CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
		cgClass.setName(classNAme);
		cgPackage.getClasses().add(cgClass);
		convertSuperTypes(cgClass);
		convertProperties(cgClass, asClass.getOwnedProperties());
		rewriteVisitOperationBodies(reDefinitions, envOperation2asOperation);
		Collection<Operation> asOperations = envOperation2asOperation.values();
		rewriteOperationCalls(asOperations);
		asClass.getOwnedOperations().addAll(asOperations);
		convertOperations(cgClass, asOperations);
		createConstrainedOperations(as2cgVisitor, cgClass);
		/*Resource dummyResource = EssentialOCLASResourceFactory.getInstance().createResource(URI.createURI("dummy.essentialocl"));
		dummyResource.getContents().addAll(asOperations);		// PrettyPrinter needs containment*/
	}

	/**
	 * Convert the asOperations to cgOperations of cgClass.
	 */
	protected void convertOperations(@NonNull CGClass cgClass, @NonNull Collection<Operation> asOperations) {
		List<Operation> sortedOperations = new ArrayList<Operation>(asOperations);
		Collections.sort(sortedOperations, NameUtil.NAMEABLE_COMPARATOR);
		for (Operation asOperation : sortedOperations) {
			CGOperation cgOperation = as2cgVisitor.doVisit(CGOperation.class, asOperation);
			cgClass.getOperations().add(cgOperation);
		}
	}

	/**
	 * Convert the asProperties to cgProperties of cgClass.
	 */
	protected void convertProperties(@NonNull CGClass cgClass, @NonNull List<Property> asProperties) {
		for (Property asProperty : asProperties) {
			CGProperty cgProperty = as2cgVisitor.doVisit(CGProperty.class, asProperty);
			cgClass.getProperties().add(cgProperty);
			if (asProperty == asEvaluatorProperty) {
				cgEvaluatorVariable = cgProperty;
			}
			else if (asProperty == asIdResolverProperty) {
				cgIdResolverVariable = cgProperty;
			}
			else {
				trackCGProperty(asProperty, cgProperty);
			}
		}
	}

	/**
	 * Give a chance to derived lookup visitors to track the created CGProperty
	 *
	 * @param asProperty
	 * @param cgProperty
	 */
	protected void trackCGProperty(Property asProperty, CGProperty cgProperty) {
		// By default do nothing
	}

	protected @NonNull Map<Operation, @NonNull Operation> createVisitOperationDeclarations(
			Map<Element, Element> reDefinitions) {


		Map<Operation, @NonNull Operation> oldOperation2rewrittenOperation = new HashMap<Operation, @NonNull Operation>();
		for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Class asType : asPackage.getOwnedClasses()) {
			for (Operation oldOperation : asType.getOwnedOperations()) {
				if (isRewrittenOperation(oldOperation)) {
					Operation asOperation = createVisitOperationDeclaration(reDefinitions, oldOperation);
					oldOperation2rewrittenOperation.put(oldOperation, asOperation);
					reDefinitions.put(oldOperation, asOperation);
				}
			}
		}
		return oldOperation2rewrittenOperation;
	}
	abstract protected boolean isRewrittenOperation(Operation operation);

	abstract protected @NonNull Operation createVisitOperationDeclaration(Map<Element, Element> reDefinitions, Operation operation);

	/**
	 * Helper operation to be used by specific lookup visitor generators
	 *
	 * @param opName
	 * @param resultType
	 * @return
	 */
	protected @NonNull Operation createVisitorOperation(String opName, Type resultType) {
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(opName);
		asOperation.setImplementation(NativeVisitorOperation.INSTANCE);
		asOperation.setType(resultType);
		return asOperation;
	}

	protected @NonNull VariableExp createThisVariableExp(@NonNull Variable thisVariable) {
		return PivotUtil.createVariableExp((VariableDeclaration)thisVariable);
	}

	abstract protected @NonNull String getLookupVisitorClassName(@NonNull String prefix);

	public @NonNull CGValuedElement getEvaluatorVariable() {
		return ClassUtil.nonNullState(cgEvaluatorVariable);
	}

	@Override
	public @NonNull LookupVisitorsClassContext getGlobalContext() {
		return classContext;
	}

	@Override
	public @NonNull CGValuedElement getIdResolverVariable() {
		return ClassUtil.nonNullState(cgIdResolverVariable);
	}

	protected @NonNull String getSuperLookupVisitorClassName() {
		String superProjectPrefix = ClassUtil.nonNullState(getSuperProjectPrefix());
		String superLangVisitorName = getLookupVisitorClassName(superProjectPrefix);
		return getSuperVisitorPackageName() + "." + superLangVisitorName;
	}

	@Override
	public @NonNull Class<?> getVisitorResultClass() {
		return getEnvironmentClass();
	}

	public @NonNull Class<?> getEnvironmentClass() {
		return genModelHelper.getEcoreInterfaceClass(asEnvironmentType);
	}

	@Override
	protected @NonNull String getSourcePackageName() {
		return getVisitorPackageName();
	}

	protected @NonNull String getSuperSourcePackageName() {
		return getSuperVisitorPackageName();
	}

	protected @NonNull String extractTypeNameFromEnvOp(@NonNull String envOpName) {
		boolean isGeneralLookup = envOperationName.equals(envOpName);
		return isGeneralLookup ? "" : envOperationName.substring(envOpName.length() + 1 /*extra underscore */);
	}

	/**
	 * Copy all the visitXXX operation bodies from the _env bodies replacing references to redefined parameters.
	 */
	protected void rewriteVisitOperationBodies(@NonNull Map<Element, Element> reDefinitions, @NonNull Map<Operation, @NonNull Operation> envOperation2asOperation) throws ParserException {
		for (@SuppressWarnings("null")@NonNull Operation envOperation : envOperation2asOperation.keySet()) {
			Operation asOperation = envOperation2asOperation.get(envOperation);
			assert asOperation != null;
			LanguageExpression envSpecification = ClassUtil.nonNullState(envOperation.getBodyExpression());
			ExpressionInOCL envExpressionInOCL = environmentFactory.parseSpecification(envSpecification);
			Variable asElement = (Variable) reDefinitions.get(envExpressionInOCL.getOwnedContext());
			OCLExpression asExpression = RereferencingCopier.copy(ClassUtil.nonNullState(envExpressionInOCL.getOwnedBody()), reDefinitions);
			ExpressionInOCL asExpressionInOCL = PivotUtil.createExpressionInOCL(null, asExpression, asElement);
			PivotUtil.initOperation(asOperation, asExpressionInOCL);
			asOperation.setType(asEnvironmentType);
			asOperation.setIsRequired(false);
		}
	}

	/**
	 * Replace selected OperationCallExps by alternative implementations
	 *
	 * Gives a chance to specific lookup visitor to also rewrite operation calls of rewrite operations
	 *
	 * By default no rewrite takes place
	 */
	protected void rewriteOperationCalls(@NonNull Collection<? extends EObject> allContents) {
		// By default no operation call rewrite takes place
	}
}
