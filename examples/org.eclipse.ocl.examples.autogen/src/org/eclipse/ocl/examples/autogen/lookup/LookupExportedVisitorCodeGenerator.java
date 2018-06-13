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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.java.AutoCG2JavaVisitor;
import org.eclipse.ocl.examples.autogen.java.AutoCodeGenerator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class LookupExportedVisitorCodeGenerator extends LookupVisitorsCodeGenerator{

	protected Property asImporterProperty;

	private @Nullable CGProperty cgImporterProperty = null;

	protected LookupExportedVisitorCodeGenerator(
			@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull Package asPackage, @Nullable Package asSuperPackage,
			@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage) {
		this(environmentFactory, asPackage, asSuperPackage, asBasePackage, genPackage,
			superGenPackage, baseGenPackage, LookupVisitorsClassContext.EXPORTED_ENV_NAME);
	}

	protected LookupExportedVisitorCodeGenerator(
			@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull Package asPackage, @Nullable Package asSuperPackage,
			@NonNull Package asBasePackage, @NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage,
			@NonNull String envOpName) {
		super(environmentFactory, asPackage, asSuperPackage, asBasePackage, genPackage,
			superGenPackage, baseGenPackage, envOpName);
	}

	@Override
	protected @NonNull AutoCG2JavaVisitor<@NonNull ? extends AutoCodeGenerator> createCG2JavaVisitor(
			@NonNull CGPackage cgPackage,
			@Nullable List<CGValuedElement> sortedGlobals) {
		return new LookupExportedCG2JavaVisitor(this, cgPackage, sortedGlobals);
	}

	@Override
	protected @NonNull String getLookupVisitorClassName(@NonNull String prefix) {
		String typeName = extractTypeNameFromEnvOp(LookupVisitorsClassContext.EXPORTED_ENV_NAME);
		return prefix + "Exported" + typeName + "LookupVisitor";
	}

	@Override
	protected List<Property> createAdditionalASProperties() {
		Type asOclElement = metamodelManager.getStandardLibrary().getOclElementType();
		this.asImporterProperty = createNativeProperty(LookupVisitorsClassContext.INMPORTER_NAME, asOclElement, true, true);
		return Collections.singletonList(asImporterProperty);
	}


	@Override
	protected boolean isRewrittenOperation(Operation operation) {
		return envOperationName.equals(operation.getName())
				&& operation != asElementEnvOperation
				&& operation.getOwnedParameters().size() == 1;
	}

	/**
	 * Convert  'Element'::_exported_env(importer : Element) : Environment
	 * to XXXXXExportedLookupVisitor::visit'Element'(element : 'Element') : Environment
	 *
	 * with
	 *   - self accessed as element.
	 *   - importer parameter accessed as this.importer.
	 *   - LookupEnvironment{} rewritten as this.context ...
	 *
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
		VariableExp asImporterSource = createThisVariableExp(asThisVariable);
		PropertyCallExp asImporterAccess = PivotUtil.createPropertyCallExp(asImporterSource, ClassUtil.nonNull(asImporterProperty));
		Variable asImporter = helper.createLetVariable(LookupVisitorsClassContext.CHILD_NAME, asImporterAccess);
		reDefinitions.put(envExpressionInOCL.getOwnedParameters().get(0), asImporter);

		//rewrite LookupEnvironment ShadowExp as accessing the context variable (it might be the init of let variable)
		VariableExp asContextAccess = createThisVariableExp(asContextVariable);
		OCLExpression body = envExpressionInOCL.getOwnedBody();
		OCLExpression rewrittenExp = body instanceof ShadowExp ? body : ((LetExp)body).getOwnedVariable().getOwnedInit() ;
		reDefinitions.put(rewrittenExp, asContextAccess);

		//
		Operation asOperation = createVisitorOperation("visit" + asType.getName(), operation.getType());
		reDefinitions.put(operation, asOperation);
		return asOperation;
	}

	@Override
	protected void trackCGProperty(Property asProperty, CGProperty cgProperty) {
		if (asProperty == asImporterProperty) {
			cgImporterProperty = cgProperty;
		}
	}

	public @NonNull CGProperty getImporterProperty() {
		return ClassUtil.nonNullState(cgImporterProperty);
	}
}
