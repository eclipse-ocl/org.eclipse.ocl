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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.java.AutoCG2JavaPreVisitor;
import org.eclipse.ocl.examples.autogen.java.AutoCodeGenerator;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModel;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.RereferencingCopier;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * LookupCodeGenerator supports generation of the content of a JavaClassFile for the Lookup visitor.
 */
public class LookupFilterGenerator extends AutoCodeGenerator
{
	protected final @NonNull LookupFilterClassContext classContext;
	protected final @NonNull AS2CGVisitor as2cgVisitor;
	protected final @NonNull String lookupPackageName;
	protected final @Nullable String superLookupPackageName;
	protected final @NonNull String baseLookupPackage;

	//
	//	New AS elements
	//

	protected final @NonNull List<org.eclipse.ocl.pivot.Package> asPackages;

	//
	//	Important CG elements
	//
	private @Nullable CGProperty cgEvaluatorVariable = null;
	private @Nullable CGProperty cgIdResolverVariable = null;


	private @NonNull Set<Property> filteringProps = new HashSet<Property>();
	private @NonNull Map<CGClass, List<@NonNull CGProperty>> cgClass2cgFilteringProps = new HashMap<CGClass, @NonNull List<@NonNull CGProperty>>();

	protected LookupFilterGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, org.eclipse.ocl.pivot.@NonNull Package asPackage,
			org.eclipse.ocl.pivot.@Nullable Package asSuperPackage, org.eclipse.ocl.pivot.@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage, @Nullable GenPackage baseGenPackage,
			@NonNull String lookupPackageName, @Nullable String superLookupPackageName,
			@Nullable String baseLookupPackage) {
		super(environmentFactory, asPackage, asSuperPackage, genPackage, superGenPackage, baseGenPackage);
		this.lookupPackageName = lookupPackageName;
		this.superLookupPackageName = superLookupPackageName;
		this.baseLookupPackage = baseLookupPackage != null ? baseLookupPackage :
			superLookupPackageName != null ? superLookupPackageName :
				lookupPackageName;
		this.classContext = new LookupFilterClassContext(this, asPackage);
		this.as2cgVisitor = createAS2CGVisitor();
		this.asPackages = createASPackages();
	}

	private @NonNull List<org.eclipse.ocl.pivot.Package> createASPackages() {

		List<org.eclipse.ocl.pivot.Package> result = new ArrayList<org.eclipse.ocl.pivot.Package>();
		List<Operation> filteringOps = gatherFilteringOps(asPackage);

		for (Operation filteringOp : filteringOps) {
			String filteredClassName = filteringOp.getOwningClass().getName();
			org.eclipse.ocl.pivot.Package asPackage = createASPackage(getSourcePackageName());
			result.add(asPackage);
			org.eclipse.ocl.pivot.Class asClass = createASClass(asPackage, filteredClassName + "Filter");
			// We create the properties
			Property asEvaluatorProperty = createNativeProperty(JavaConstants.EXECUTOR_NAME, Executor.class, true, true);
			Property asIdResolverProperty = createNativeProperty(JavaConstants.ID_RESOLVER_NAME, IdResolver.class, true, true);
			List<Property> asProperties = asClass.getOwnedProperties();
			asProperties.add(asEvaluatorProperty);
			asProperties.add(asIdResolverProperty);
			Variable asThisVariable = helper.createParameterVariable("this", asClass, true);
			// We create the operations
			Operation asOperation = createASMatchesOperation(filteringOp, asClass, asThisVariable);
			asClass.getOwnedOperations().add(asOperation);
		}
		return result;
	}



	/**
	 * Convert  'Element'::_appliesFilter_'Element'(filterArg : FilterArgType) : Boolean
	 * to 'Element'LookupFilter::_matches(element : 'Element') : Boolean
	 *
	 * with
	 *     - self accessed as element.
	 *     - filterArg accessed as this.filterArg (NB there might be many filterArgs).
	 * @throws ParserException
	 */
	private Operation createASMatchesOperation(@NonNull Operation filteringOp, org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull Variable thisVariable){

		Map<Element,Element> redefinitions = new HashMap<Element,Element>();
		ExpressionInOCL oldExpressionInOCL = getExpressionInOCL(filteringOp);
		ExpressionInOCL newExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();

		Type filteringOpType = filteringOp.getType();
		assert filteringOpType != null;
		Operation asOperation = PivotUtil.createOperation("_" + LookupFilterClassContext.MATCHES_OP_NAME, filteringOpType, null, null);
		asOperation.setBodyExpression(newExpressionInOCL);
		// Filtering op params are translated as asClass properties
		LetExp letRoot = null;
		LetExp letLeaf = null;
		for (Variable paramVar : oldExpressionInOCL.getOwnedParameters()) {
			String paramName = paramVar.getName();
			Type paramType = paramVar.getType();
			assert (paramName != null) && (paramType != null);
			Property asProperty = createNativeProperty(paramName, paramType, true, true);
			asClass.getOwnedProperties().add(asProperty);

			// Redefinition requires a Variable access rather than a Property
			VariableExp asThisVarExp = createThisVariableExp(thisVariable);
			PropertyCallExp asPropertyAccess = PivotUtil.createPropertyCallExp(asThisVarExp, asProperty);
			Variable asContextVar = helper.createLetVariable(paramName, asPropertyAccess);
			redefinitions.put(paramVar, asContextVar);
			filteringProps.add(asProperty);
			LetExp letExp = PivotFactory.eINSTANCE.createLetExp();
			letExp.setOwnedVariable(asContextVar);
			if (letRoot == null) {
				letRoot = letExp;
			}
			if (letLeaf != null) {
				letLeaf.setOwnedIn(letExp);
			}
			letLeaf = letExp;
		}

		// Filtering op context is translated as asOperation parameter
		org.eclipse.ocl.pivot.Class asType = ClassUtil.nonNullState(filteringOp.getOwningClass());
		Variable asParamVar = helper.createParameterVariable(LookupFilterClassContext.ELEMENT_NAME, asType,true);
		newExpressionInOCL.getOwnedParameters().add(asParamVar);
		redefinitions.put(oldExpressionInOCL.getOwnedContext(), asParamVar);

		OCLExpression asExpression = RereferencingCopier.copy(ClassUtil.nonNullState(oldExpressionInOCL.getOwnedBody()), redefinitions);
		if ((letRoot != null) && (letLeaf != null)) {
			for (LetExp letExp = letRoot; letExp != null; letExp = (LetExp) letExp.getOwnedIn()) {
				letExp.setType(asExpression.getType());
				letExp.setIsRequired(asExpression.isIsRequired());
			}
			letLeaf.setOwnedIn(asExpression);
			newExpressionInOCL.setOwnedBody(letRoot);
			newExpressionInOCL.setType(asExpression.getType());
			newExpressionInOCL.setIsRequired(asExpression.isIsRequired());
		}
		else {
			newExpressionInOCL.setOwnedBody(asExpression);
			newExpressionInOCL.setType(asExpression.getType());
			newExpressionInOCL.setIsRequired(asExpression.isIsRequired());
		}
		PivotUtil.initOperation(asOperation, newExpressionInOCL);
		return asOperation;
	}

	private List<Operation> gatherFilteringOps(
			org.eclipse.ocl.pivot.@NonNull Package asPackage) {

		return asPackage.getOwnedClasses().stream()
				.map(c -> c.getOwnedOperations())
				.flatMap(o -> o.stream())
				.filter(o -> o.getName().startsWith(LookupFilterClassContext.APPLIES_FILTER_OP_PREFIX))	// interested op
				//.map(o -> o.getName().substring(o.getName().indexOf(LookupFilterClassContext.APPLIES_FILTER_OP_PREFIX))) // type name is after appliesFilter prefix
				.collect(Collectors.toList());

	}


	protected void convertPackages(@NonNull CGModel cgModel, @NonNull List<org.eclipse.ocl.pivot.Package> asPackages) {


		for (org.eclipse.ocl.pivot.Package asPackage : asPackages) {
			CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
			cgModel.getPackages().add(cgPackage);
			cgPackage.setAst(asPackage);
			cgPackage.setName(asPackage.getName());
			convertClasses(cgPackage, asPackage.getOwnedClasses());
		}

	}

	protected void convertClasses(@NonNull CGPackage cgPackage, @NonNull List<org.eclipse.ocl.pivot.Class> asClasses) {

		for (org.eclipse.ocl.pivot.Class asClass : asClasses) {
			CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
			cgPackage.getClasses().add(cgClass);
			cgClass.setAst(asClass);
			cgClass.setName(asClass.getName());
			convertProperties(cgClass, asClass.getOwnedProperties());
			convertOperations(cgClass, asClass.getOwnedOperations());
			convertSuperTypes(cgClass);
		}
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
			if (filteringProps.contains(asProperty)) {
				List<CGProperty> cgProps = cgClass2cgFilteringProps.get(cgClass);
				if (cgProps == null) {
					cgProps = new ArrayList<CGProperty>();
					cgClass2cgFilteringProps.put(cgClass, cgProps);
				}
				cgProps.add(cgProperty);
			}
		}
	}

	/**
	 * Convert the construction context to supertypes/interfaces of cgClass.
	 *
	 * Note: convertOperation should have been called first, so that we can access the target Operation
	 * from which we can obtain the templater parameter substitution for the AbstractFilter super type
	 */
	protected void convertSuperTypes(@NonNull CGClass cgClass) {
		// GenPackage superGenPackage2 = superGenPackage;
		CGClass cgSuperClass = getExternalClass(getBaseSourcePackageName(), "Abstract" + getBasePrefix() + "LookupFilter", false);
		// The first ownedOperation is the one referring to the interested one
		CGOperation cgOp = cgClass.getOperations().get(0);
		Operation matchesOp = (Operation)cgOp.getAst();
		// The first parameter type is the filteredType
		org.eclipse.ocl.pivot.Class filteredType = (org.eclipse.ocl.pivot.Class)matchesOp.getOwnedParameters().get(0).getType();
		assert filteredType != null;
		cgSuperClass.getTemplateParameters().add(getExternalClass(filteredType));
		cgClass.getSuperTypes().add(cgSuperClass);
	}

	@Override
	protected @NonNull LookupFilterCG2JavaVisitor createCG2JavaVisitor(@NonNull CGPackage cgPackage, @Nullable List<CGValuedElement> sortedGlobals) {
		return new LookupFilterCG2JavaVisitor(this, cgPackage, sortedGlobals);
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
		convertPackages(cgModel, asPackages);
		return new ArrayList<CGPackage>(cgModel.getPackages());
	}



	protected @NonNull VariableExp createThisVariableExp(@NonNull VariableDeclaration thisVariable) {
		return PivotUtil.createVariableExp(thisVariable);
	}


	protected @NonNull NullLiteralExp createNullLiteralExp() {
		return metamodelManager.createNullLiteralExp();
	}

	public @NonNull CGValuedElement getEvaluatorVariable() {
		// When generating lookup visitors for derived languages, the common lookup visitor is no
		// not generated. Therefore we have to add this hack to provide CG for executor property
		// accesses
		if (cgEvaluatorVariable == null) {
			Property prop = createNativeProperty(JavaConstants.EXECUTOR_NAME, Executor.class, true, true);
			cgEvaluatorVariable = as2cgVisitor.visitProperty(prop);
		}
		return ClassUtil.nonNullState(cgEvaluatorVariable);
	}


	@Override
	public @NonNull LookupFilterClassContext getGlobalContext() {
		return classContext;
	}

	@Override
	public @NonNull CGValuedElement getIdResolverVariable() {
		// When generating lookup visitors for derived languages, the common lookup visitor is no
		// not generated. Therefore we have to add this hack to provide CG for idResolver property
		// accesses
		if (cgIdResolverVariable == null) {
			Property prop = createNativeProperty(JavaConstants.ID_RESOLVER_NAME, IdResolver.class, true, true);
			cgIdResolverVariable = as2cgVisitor.visitProperty(prop);
		}
		return ClassUtil.nonNullState(cgIdResolverVariable);
	}

	public @NonNull List<@NonNull CGProperty> getFilteringVars(CGClass cgClass) {
		List<@NonNull CGProperty> cgProps = cgClass2cgFilteringProps.get(cgClass);
		assert(cgProps != null);
		return cgProps;
	}
	@Override
	protected @NonNull String getSourcePackageName() {
		return lookupPackageName + ".util";
	}

	protected @Nullable String getSuperSourcePackageName() {
		return superLookupPackageName + ".util";
	}

	protected @NonNull String getBaseSourcePackageName() {
		return baseLookupPackage + ".util";
	}
}
