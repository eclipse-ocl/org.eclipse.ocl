/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (Open Canarias) - Bug 333032
 *******************************************************************************/

package org.eclipse.ocl.ecore.tests;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.EvaluationVisitor;
import org.eclipse.ocl.LookupException;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EnvironmentWithHiddenOpposites;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.ecore.utilities.ToStringVisitor;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.helper.OCLSyntaxHelper;
import org.eclipse.ocl.parser.AbstractOCLParser;
import org.eclipse.ocl.parser.OCLAnalyzer;
import org.eclipse.ocl.parser.OCLFactoryWithHistory;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.ocl.utilities.UMLReflection;
import org.eclipse.ocl.utilities.Visitor;

/**
 * Tests the extensibility of the parser API.
 *
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("nls")
public class ExtensibilityTest
extends AbstractTestSuite {

	/**
	 * Tests that we can successfully parse with an environment that does not
	 * implement the optional adapter protocols.
	 */
	public void test_noAdapters_parseSuccessfully() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		try {
			helper.createInvariant("not self.eClassifiers->isEmpty()");
		} catch (ParserException e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}

		try {
			ocl.parse(new OCLInput(
				"context ecore::EPackage\n" +
					"inv: not self.eClassifiers->isEmpty()"));
		} catch (ParserException e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Tests that we can produce warnings with an environment that does not
	 * implement the optional adapter protocols.
	 */
	public void test_noAdapters_parseWithWarning() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		try {
			helper.createInvariant("not self.\"eClassifiers\"->isEmpty()");

			Diagnostic diag = helper.getProblems();
			assertNotNull(diag);
			assertEquals(Diagnostic.WARNING, diag.getSeverity());

			debugPrintln("Got expected warning: " + diag.getMessage());
		} catch (ParserException e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}

		try {
			ocl.parse(new OCLInput(
				"context ecore::EPackage\n" +
					"inv: not self.\"eClassifiers\"->isEmpty()"));

			Diagnostic diag = ocl.getProblems();
			assertNotNull(diag);
			assertEquals(Diagnostic.WARNING, diag.getSeverity());

			debugPrintln("Got expected warning: " + diag.getMessage());
		} catch (ParserException e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Tests that we can produce errors with an environment that does not
	 * implement the optional adapter protocols.
	 */
	public void test_noAdapters_parseWithError() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		try {
			helper.createInvariant("not self.classifiers->isEmpty()");
			fail("Should not have parsed");
		} catch (ParserException e) {
			// success
			debugPrintln("Got expected error: " + e.getLocalizedMessage());
		}

		try {
			ocl.parse(new OCLInput(
				"context ecore::EPackage\n" +
					"inv: not self.classifiers->isEmpty()"));
			fail("Should not have parsed");
		} catch (ParserException e) {
			// success
			debugPrintln("Got expected error: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Tests that we can report lexer problems with an environment that does not
	 * implement the optional adapter protocols.
	 */
	public void test_noAdapters_parseWithTokenError() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		try {
			helper.createInvariant("not self.;->isEmpty()");
			fail("Should not have parsed");
		} catch (ParserException e) {
			// success
			debugPrintln("Got expected error: " + e.getLocalizedMessage());
		}

		try {
			ocl.parse(new OCLInput(
				"context ecore::EPackage\n" +
					"inv: not self.;->isEmpty()"));
			fail("Should not have parsed");
		} catch (ParserException e) {
			// success
			debugPrintln("Got expected error: " + e.getLocalizedMessage());
		}
	}

	//
	// Framework methods
	//

	@Override
	protected OCL createOCL() {
		return OCL.newInstance(new WrapperEnvironmentFactory());
	}

	class WrapperEnvironment implements EnvironmentWithHiddenOpposites {

		private final EnvironmentWithHiddenOpposites
		delegate;

		private final EnvironmentWithHiddenOpposites
		parent;

		private final WrapperEnvironmentFactory factory;

		WrapperEnvironment(WrapperEnvironmentFactory factory,
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> delegate) {
			this(factory, delegate, null);
		}

		WrapperEnvironment(WrapperEnvironmentFactory factory,
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> delegate,
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent) {

			this.factory = factory;
			this.delegate = (EnvironmentWithHiddenOpposites) delegate;
			this.parent = (EnvironmentWithHiddenOpposites) parent;
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> getParent() {
			return parent;
		}

		@Override
		public EnvironmentFactory<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> getFactory() {
			return factory;
		}

		@Override
		public boolean addElement(String name,
				Variable<EClassifier, EParameter> elem, boolean explicit) {
			return delegate.addElement(name, elem, explicit);
		}

		@Override
		public EStructuralFeature defineAttribute(EClassifier owner,
				Variable<EClassifier, EParameter> variable,
				Constraint constraint) {
			return delegate.defineAttribute(owner, variable, constraint);
		}

		@Override
		public EOperation defineOperation(EClassifier owner, String name,
				EClassifier type,
				List<Variable<EClassifier, EParameter>> params,
				Constraint constraint) {
			return delegate.defineOperation(owner, name, type, params,
				constraint);
		}

		@Override
		public void deleteElement(String name) {
			delegate.deleteElement(name);
		}

		@Override
		public List<EStructuralFeature> getAdditionalAttributes(
				EClassifier classifier) {
			return delegate.getAdditionalAttributes(classifier);
		}

		@Override
		public List<EOperation> getAdditionalOperations(EClassifier classifier) {
			return delegate.getAdditionalOperations(classifier);
		}

		@Override
		public Constraint getBodyCondition(EOperation operation) {
			return delegate.getBodyCondition(operation);
		}

		@Override
		public EClassifier getContextClassifier() {
			return delegate.getContextClassifier();
		}

		@Override
		public EOperation getContextOperation() {
			return delegate.getContextOperation();
		}

		@Override
		public EPackage getContextPackage() {
			return delegate.getContextPackage();
		}

		@Override
		public EStructuralFeature getContextProperty() {
			return delegate.getContextProperty();
		}

		@Override
		public Constraint getDefinition(Object feature) {
			return delegate.getDefinition(feature);
		}

		@Override
		public Constraint getDeriveConstraint(EStructuralFeature property) {
			return delegate.getDeriveConstraint(property);
		}

		@Override
		public Constraint getInitConstraint(EStructuralFeature property) {
			return delegate.getInitConstraint(property);
		}

		@Override
		public OCLFactory getOCLFactory() {
			return delegate.getOCLFactory();
		}

		@Override
		public OCLStandardLibrary<EClassifier> getOCLStandardLibrary() {
			return delegate.getOCLStandardLibrary();
		}

		@Override
		public Variable<EClassifier, EParameter> getSelfVariable() {
			return delegate.getSelfVariable();
		}

		@Override
		public List<EObject> getStates(EClassifier owner,
				List<String> pathPrefix) {
			return delegate.getStates(owner, pathPrefix);
		}

		@Override
		public TypeResolver<EClassifier, EOperation, EStructuralFeature> getTypeResolver() {
			return delegate.getTypeResolver();
		}

		@Override
		public UMLReflection<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint> getUMLReflection() {
			return delegate.getUMLReflection();
		}

		@Override
		public Collection<Variable<EClassifier, EParameter>> getVariables() {
			return delegate.getVariables();
		}

		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		@Override
		public boolean isInPostcondition(OCLExpression<EClassifier> exp) {
			return delegate.isInPostcondition(exp);
		}

		@Override
		public Variable<EClassifier, EParameter> lookup(String name) {
			return delegate.lookup(name);
		}

		@Override
		public EClassifier lookupAssociationClassReference(EClassifier owner,
				String name) {
			return delegate.lookupAssociationClassReference(owner, name);
		}

		@Override
		public EClassifier lookupClassifier(List<String> names) {
			return delegate.lookupClassifier(names);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForAssociationClass(
				String name) {
			return delegate.lookupImplicitSourceForAssociationClass(name);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForOperation(
				String name, List<? extends TypedElement<EClassifier>> args) {
			return delegate.lookupImplicitSourceForOperation(name, args);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForProperty(
				String name) {
			return delegate.lookupImplicitSourceForProperty(name);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForSignal(
				String name, List<? extends TypedElement<EClassifier>> args) {
			return delegate.lookupImplicitSourceForSignal(name, args);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForState(
				List<String> path)
						throws LookupException, SemanticException {
			return delegate.lookupImplicitSourceForState(path);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupLocal(String name) {
			return delegate.lookupLocal(name);
		}

		@Override
		public EOperation lookupOperation(EClassifier owner, String name,
				List<? extends TypedElement<EClassifier>> args) {
			return delegate.lookupOperation(owner, name, args);
		}

		@Override
		public EPackage lookupPackage(List<String> names) {
			return delegate.lookupPackage(names);
		}

		@Override
		public EStructuralFeature lookupProperty(EClassifier owner, String name) {
			return delegate.lookupProperty(owner, name);
		}

		@Override
		public EReference lookupOppositeProperty(EClassifier owner, String name)
				throws LookupException {
			return delegate.lookupOppositeProperty(owner, name);
		}

		@Override
		public EClassifier getOppositePropertyType(EClassifier owner, EReference property) {
			return delegate.getOppositePropertyType(owner, property);
		}

		@Override
		public EClassifier lookupSignal(EClassifier owner, String name,
				List<? extends TypedElement<EClassifier>> args) {
			return delegate.lookupSignal(owner, name, args);
		}

		@Override
		public EObject lookupState(EClassifier owner, List<String> path)
				throws LookupException, SemanticException {
			return delegate.lookupState(owner, path);
		}

		@Override
		public void setBodyCondition(EOperation operation, Constraint constraint) {
			delegate.setBodyCondition(operation, constraint);
		}

		@Override
		public void setDeriveConstraint(EStructuralFeature property,
				Constraint constraint) {
			delegate.setDeriveConstraint(property, constraint);
		}

		@Override
		public void setInitConstraint(EStructuralFeature property,
				Constraint constraint) {
			delegate.setInitConstraint(property, constraint);
		}

		@Override
		public void setParent(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env) {
			delegate.setParent(env);
		}

		@Override
		public void setSelfVariable(Variable<EClassifier, EParameter> var) {
			delegate.setSelfVariable(var);
		}

		@Override
		public void undefine(Object feature) {
			delegate.undefine(feature);
		}

		@Override
		public Map<String, EReference> getHiddenOppositeProperties(
				EClassifier classifier) {
			return delegate.getHiddenOppositeProperties(classifier);
		}

		@Override
		public Variable<EClassifier, EParameter> lookupImplicitSourceForOppositeProperty(
				String name) {
			return delegate.lookupImplicitSourceForOppositeProperty(name);
		}

		/**
		 * @since 6.22
		 */
		@Override
		public OCL createOCL() {
			return OCL.newInstance(this);
		}

		/**
		 * @since 6.22
		 */
		@Override
		public ToStringVisitor createToStringVisitor(Environment<?, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, ?, ?> environment) {
			return new ToStringVisitor(environment);
		}
	}

	class WrapperEnvironmentFactory implements EnvironmentFactory<
	EPackage, EClassifier, EOperation, EStructuralFeature,
	EEnumLiteral, EParameter,
	EObject, CallOperationAction, SendSignalAction, Constraint,
	EClass, EObject> {

		private final EnvironmentFactory<
		EPackage, EClassifier, EOperation, EStructuralFeature,
		EEnumLiteral, EParameter,
		EObject, CallOperationAction, SendSignalAction, Constraint,
		EClass, EObject> delegate;

		WrapperEnvironmentFactory(EnvironmentFactory<
			EPackage, EClassifier, EOperation, EStructuralFeature,
			EEnumLiteral, EParameter,
			EObject, CallOperationAction, SendSignalAction, Constraint,
			EClass, EObject> delegate) {
			this.delegate = delegate;
		}

		WrapperEnvironmentFactory() {
			this(new EcoreEnvironmentFactory());
		}

		private Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject>
		getWrappedEnvironment(Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env) {
			if (env instanceof WrapperEnvironment) {
				return ((WrapperEnvironment) env).delegate;
			} else {
				return env;
			}
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createAttributeContext(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent,
				EStructuralFeature property) {
			return new WrapperEnvironment(this, delegate.createAttributeContext(getWrappedEnvironment(parent), property), parent);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createClassifierContext(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent,
				EClassifier context) {
			return new WrapperEnvironment(this, delegate.createClassifierContext(getWrappedEnvironment(parent), context), parent);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEnvironment() {
			return new WrapperEnvironment(this, delegate.createEnvironment());
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEnvironment(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent) {
			return new WrapperEnvironment(this, delegate.createEnvironment(getWrappedEnvironment(parent)), parent);
		}

		@Override
		public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> createEvaluationEnvironment() {
			return delegate.createEvaluationEnvironment();
		}

		@Override
		public EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> createEvaluationEnvironment(
				EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
			return delegate.createEvaluationEnvironment(parent);
		}

		@Override
		public EvaluationVisitor<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createEvaluationVisitor(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env,
				EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> evalEnv,
				Map<? extends EClass, ? extends Set<? extends EObject>> extentMap) {
			return delegate.createEvaluationVisitor(env, evalEnv, extentMap);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createInstanceContext(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent,
				Object context) {
			return new WrapperEnvironment(this, delegate.createInstanceContext(getWrappedEnvironment(parent), context), parent);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createOperationContext(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent,
				EOperation operation) {
			return new WrapperEnvironment(this, delegate.createOperationContext(getWrappedEnvironment(parent), operation), parent);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createPackageContext(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent,
				List<String> pathname) {
			return new WrapperEnvironment(this, delegate.createPackageContext(getWrappedEnvironment(parent), pathname), parent);
		}

		@Override
		public Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> loadEnvironment(
				Resource resource) {
			return new WrapperEnvironment(this, delegate.loadEnvironment(resource));
		}

		@Override
		public Visitor<Boolean, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint> createValidationVisitor(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env) {
			return delegate.createValidationVisitor(env);
		}

		@Override
		public OCLAnalyzer<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createOCLAnalyzer(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env,
				String input) {
			return delegate.createOCLAnalyzer(env, input);
		}

		@Override
		public OCLAnalyzer<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> createOCLAnalyzer(
				AbstractOCLParser parser) {
			return delegate.createOCLAnalyzer(parser);
		}

		@Override
		public OCLFactoryWithHistory createOCLFactoryWithHistory(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env) {
			return delegate.createOCLFactoryWithHistory(env);
		}

		@Override
		public OCLSyntaxHelper createOCLSyntaxHelper(
				Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> env) {
			return delegate.createOCLSyntaxHelper(env);
		}

		@Override
		public OCL createOCL() {
			return OCL.newInstance(this);
		}

		@Override
		public OCL createOCL(Resource resource) {
			return OCL.newInstance(this, resource);
		}
	}

}
