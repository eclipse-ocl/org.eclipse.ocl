/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.common.CodeGenHelper;
import org.eclipse.ocl.examples.codegen.dynamic.JavaGenModelCodeGenHelper;
import org.eclipse.ocl.examples.xtext.tests.NoHttpURIHandlerImpl;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.examples.xtext.tests.TestProject;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorManager;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.library.LibraryUnaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager.IPackageDescriptor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;

import junit.framework.TestCase;

public class TestOCL extends OCLInternal
{
	protected final @NonNull TestFileSystem testFileSystem ;
	private TestProject testProject = null;
	protected final @NonNull String testPackageName;
	protected final @NonNull String testName;
	private int testCounter = 0;

	public TestOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String testName, @NonNull ProjectManager projectManager, @Nullable ResourceSet externalResourceSet) {
		super(ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(projectManager, externalResourceSet));
		this.testFileSystem = testFileSystem;
		ResourceSet resourceSet = getResourceSet();
		NoHttpURIHandlerImpl.install(resourceSet);
		this.testPackageName = testPackageName;
		int index = testName.indexOf("[");
		if (index > 0) {
			this.testName = testName.substring(0, index);
		}
		else {
			this.testName = testName;
		}
		EPackage.Registry packageRegistry = resourceSet.getPackageRegistry();
		packageRegistry.put(org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI, org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
		packageRegistry.put(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI, org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eINSTANCE);
		//
		// http://www.eclipse.org/emf/2002/Ecore is referenced by just about any model load
		// Ecore.core/Pivot.core  is referenced from Ecore.genmodel/Pivot.genmodel that is used by the CG to coordinate
		// Ecore/Pivot objects with their Java classes. Therefore force the Java EPackage to hide the Resource.
		//
		configureGeneratedPackage(EcorePackage.eNS_URI);
		configureGeneratedPackage(PivotPackage.eNS_URI);
		configureGeneratedPackage(OCLstdlibPackage.eNS_URI);
	}

	private void configureGeneratedPackage( /*@NonNull*/ String uriString) {
		URI nsURI = URI.createURI(uriString);
		IPackageDescriptor packageDescriptor = getProjectManager().getPackageDescriptor(nsURI);
		if (packageDescriptor != null) {
			packageDescriptor.configure(getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, null);
		}
	}

	public void addSupertype(org.eclipse.ocl.pivot.@NonNull Class aClass, org.eclipse.ocl.pivot.@NonNull Class superClass) {
		aClass.getSuperClasses().add(superClass);
	}

	/**
	 * Assert that an expression cannot be used as an invariant, because an exception is thrown
	 * with a diagnostic of severity containing a message that is the result of messageTemplate
	 * resolved by bindings.
	 */
	public void assertBadInvariant(@NonNull Class<?> exception, int severity, org.eclipse.ocl.pivot.@Nullable Class contextType,
			@NonNull String expression, /*@NonNull*/ String messageTemplate, Object... bindings) {
		CSResource resource = null;
		try {
			ParserContext semanticContext = new ClassContext(getEnvironmentFactory(), null, contextType, null);
			resource = semanticContext.createBaseResource(expression);
			PivotUtil.checkResourceErrors(StringUtil.bind(PivotMessagesInternal.ErrorsInResource, expression), resource);
			TestCase.fail("Should not have parsed \"" + expression + "\"");
		} catch (ParserException e) {
			TestCase.assertEquals("Exception for \"" + expression + "\"", exception, e.getClass());
			if (resource != null) {
				Resource.Diagnostic diagnostic = getError(resource);
				assertNoException(diagnostic, ClassCastException.class);
				assertNoException(diagnostic, NullPointerException.class);
				//            	assertEquals("Severity for \"" + expression + "\"", severity, diagnostic.getSeverity());
				String expectedMessage = StringUtil.bind(messageTemplate, bindings);
				TestCase.assertEquals("Message for \"" + expression + "\"", expectedMessage, diagnostic.getMessage());
			}
		} catch (IOException e) {
			TestCase.fail(e.getMessage());
		} finally {
			if (resource != null) {
				EnvironmentFactoryAdapter.disposeAll(resource);
			}
		}
	}

	/**
	 * Assert that an expression cannot be used as a query, because an exception is thrown
	 * with a diagnostic of severity containing a message that is the result of messageTemplate
	 * resolved by bindings.
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	public void assertBadQuery(@NonNull Class<?> exception, int severity, org.eclipse.ocl.pivot.@Nullable Class contextType, @NonNull String expression, /*@NonNull*/ String messageTemplate, Object... bindings) {
		BaseCSResource csResource = null;
		try {
			ClassContext classContext = new ClassContext(getEnvironmentFactory(), null, contextType, null);
			if (PivotMessages.UnspecifiedSelfContext.equals(messageTemplate)) {
				classContext.setSelfName("SELF");
			}
			csResource = (BaseCSResource) classContext.createBaseResource(expression);
			PivotUtil.checkResourceErrors(StringUtil.bind(PivotMessagesInternal.ErrorsInResource, expression), csResource);
			Resource asResource = csResource.getASResource();
			PivotTestSuite.assertNoValidationErrors("Validating", asResource);
			TestCase.fail("Should not have parsed \"" + expression + "\"");
		} catch (ParserException e) {
			TestCase.assertEquals("Exception for \"" + expression + "\"", exception, e.getClass());
			Resource.Diagnostic diagnostic = getError(csResource);
			String expectedMessage = StringUtil.bind(messageTemplate, bindings);
			TestCase.assertEquals("Message for \"" + expression + "\"", expectedMessage, diagnostic.getMessage());
			PivotTestSuite.appendLog(testName, contextType, expression, expectedMessage, null, null);
		} catch (IOException e) {
			TestCase.fail(e.getMessage());
		} finally {
			if (csResource != null) {
				EnvironmentFactoryAdapter.disposeAll(csResource);
			}
		}
	}

	/**
	 * Assert that an expression can be parsed as an invariant for a context and return the invariant.
	 */
	public @Nullable ExpressionInOCL assertInvariant(org.eclipse.ocl.pivot.@NonNull Class context, @NonNull String expression) {
		try {
			ExpressionInOCL result = createInvariant(context, expression);
			return result;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that an expression evaluated as an invariant for a context returns false.
	 */
	public @Nullable Object assertInvariantFalse(@Nullable Object context, @NonNull String expression) {
		try {
			Object value = check(context, expression);
			TestCase.assertEquals(expression, false, value);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that an expression evaluated as an invariant for a context returns true.
	 */
	public @Nullable Object assertInvariantTrue(@Nullable Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertEquals(expression, true, value);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Asserts that a exception of the specified kind is not signalled by
	 * the a given diagnostic or (recursively) its children.
	 *
	 * @param diagnostic a diagnostic
	 * @param excType an exception that must not be indicated by the diagnostic
	 */
	public void assertNoException(Resource.Diagnostic diagnostic, java.lang.Class<? extends Throwable> excType) {
		if (diagnostic instanceof ExceptionDiagnostic) {
			if (excType.isInstance(((ExceptionDiagnostic)diagnostic).getException())) {
				TestCase.fail("Diagnostic signals a(n) " + excType.getSimpleName());
			}

			//	    	for (Diagnostic nested : diagnostic.getChildren()) {
			//	    		assertNoException(nested, excType);
			//	    	}
		}
	}

	/**
	 * Asserts that two objects are equal using OCL semantics. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public void assertOCLEquals(String message, Object expected, Object actual) {
		IdResolver.IdResolverExtension idResolver = (IdResolver.IdResolverExtension)getIdResolver();
		if (idResolver.oclEquals(expected, actual)) {
			int expectedHash = idResolver.oclHashCode(expected);
			int actualHash = idResolver.oclHashCode(actual);
			if (expectedHash == actualHash) {
				return;
			}
			PivotTestSuite.failNotEquals(message + " badHash", expectedHash, actualHash);
		}
		PivotTestSuite.failNotEquals(message, expected, actual);
	}

	/**
	 * Asserts that two objects are not equal using OCL semantics. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public void assertOCLNotEquals(String message, Object expected, Object actual) {
		IdResolver idResolver = getIdResolver();
		if (!idResolver.oclEquals(expected, actual))
			return;
		PivotTestSuite.failSame(message);
	}

	/**
	 * Assert that an expression can be parsed as a query for a context and return the query.
	 */
	public @NonNull ExpressionInOCL assertQuery(org.eclipse.ocl.pivot.Class context, @NonNull String expression) {
		try {
			ExpressionInOCL result = createQuery(context, expression);
			return result;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			@SuppressWarnings("null")@NonNull ExpressionInOCL nullReturn = (@NonNull ExpressionInOCL)null;
			return nullReturn;				// Never happens
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is not undefined.
	 * @return the evaluation result
	 */
	public Object assertQueryDefined(Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertFalse(expression + " expected defined: ", value == null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is equal to expected.
	 * @return the evaluation result
	 */
	public @Nullable Object assertQueryEquals(@Nullable Object context, @Nullable Object expected, @NonNull String expression) {
		try {
			Object expectedValue = expected instanceof Value ? expected : getIdResolver().boxedValueOf(expected);
			//    		typeManager.addLockedElement(expectedValue.getType());
			Object value = evaluate(null, context, expression);
			//    		String expectedAsString = String.valueOf(expected);
			//    		String valueAsString = String.valueOf(value);
			assertOCLEquals(expression, expectedValue, value);
			PivotTestSuite.appendLog(testName, context, expression, null, expectedValue != null ? expectedValue.toString() : null, null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is equal to expected.
	 * @return the evaluation result
	 */
	public @Nullable Object assertQueryEquals(@Nullable Object context, @NonNull BigDecimal expected, @NonNull BigDecimal delta, @NonNull String expression) {
		try {
			BigDecimal value = ClassUtil.nonNullState((BigDecimal) evaluate(null, context, expression));
			TestCase.assertTrue(expression, (value.compareTo(expected.add(delta)) >= 0) && (value.compareTo(expected.subtract(delta)) >= 0));
			PivotTestSuite.appendLog(testName, context, expression, null, expected.toString(), delta.toString());
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is equal to expected.
	 * @return the evaluation result
	 */
	public Object assertQueryEquals(Object context, @NonNull Number expected, @NonNull String expression, double tolerance) {
		try {
			IdResolver idResolver = getIdResolver();
			@NonNull Object expectedValue = ClassUtil.nonNullState(idResolver.boxedValueOf(expected));
			@NonNull Object value = ClassUtil.nonNullState(evaluate(null, context, expression));
			BigDecimal expectedVal = ClassUtil.nonNullState(((RealValue)expectedValue).bigDecimalValue());
			BigDecimal val = ClassUtil.nonNullState(((RealValue)value).bigDecimalValue());
			double delta = val.subtract(expectedVal).doubleValue();
			if ((delta < -tolerance) || (tolerance < delta)) {
				TestCase.assertEquals(expression, expected, value);
			}
			PivotTestSuite.appendLog(testName, context, expression, null, expected.toString(), Double.toString(tolerance));
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is false.
	 * @return the evaluation result
	 */
	public Object assertQueryFalse(Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertEquals(expression, Boolean.FALSE, value);
			PivotTestSuite.appendLog(testName, context, expression, null, "false", null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is invalid.
	 * @return the evaluation result
	 */
	public Value assertQueryInvalid(Object context, @NonNull String expression) {
		try {
			Object value = evaluateWithoutValidation(null, context, expression);
			TestCase.fail(expression + " expected: invalid but was: " + value);
		} catch (InvalidValueException e) {		// OCL invalid is always an InvalidValueException
			PivotTestSuite.appendLog(testName, context, expression, null, "invalid", null);
		} catch (Exception e) {					// Something else is nasty
			PivotTestSuite.failOn(expression, e);
		}
		return null;
	}

	public Object assertQueryInvalid(Object context, @NonNull String expression, String reason, Class<?> exceptionClass) {
		try {
			Object value = evaluateWithoutValidation(null, context, expression);
			//    		if (!ValuesUtil.isInvalid(value)) {
			TestCase.fail(expression + " expected: invalid but was: " + value);
			//    		}
			//    		InvalidValue invalidValue = (InvalidValue)value;
			//              fail("Expected invalid for \"" + expression + "\"");
		} catch (InvalidValueException e) {
			Throwable ex = e;
			Throwable cause = e.getCause();
			//    		Exception cause = invalidValue.getException();
			//    		Throwable ex = cause;
			String message = e.getMessage();
			if (cause != null) {
				ex = cause;
				if (!(cause instanceof NumberFormatException)) {
					String m = ex.getMessage();
					if (m != null) {
						message = m;
					}
				}
			}
			if (reason != null) {
				TestCase.assertEquals("Invalid Value Reason", reason, message);
			}
			if (exceptionClass != null) {
				TestCase.assertEquals("Invalid Value Throwable", exceptionClass, ex.getClass());
			}
		} catch (Exception e) {
			if ((exceptionClass != null) && (exceptionClass != e.getClass())) {
				TestCase.assertEquals("Invalid Value Throwable", exceptionClass, e.getClass() + " : " + e.getMessage());
			}
			if (reason != null) {
				TestCase.assertEquals("Invalid Value Reason", reason, e.getMessage());
			}
			//    		failOn(expression, e);
		}
		return null;
	}

	/**
	 * Assert that the result of evaluating an expression as a query is not equal to expected.
	 * @return the evaluation result
	 */
	public @Nullable Object assertQueryNotEquals(@Nullable Object context, @Nullable Object expected, @NonNull String expression) {
		try {
			Object expectedValue = expected instanceof Value ? expected : getIdResolver().boxedValueOf(expected);
			//    		typeManager.addLockedElement(expectedValue.getType());
			Object value = evaluate(null, context, expression);
			//    		String expectedAsString = String.valueOf(expected);
			//    		String valueAsString = String.valueOf(value);
			assertOCLNotEquals(expression, expectedValue, value);
			PivotTestSuite.appendLog(testName, context, expression, null, expectedValue != null ? expectedValue.toString() : null, null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is not the same as expected.
	 * @return the evaluation result
	 */
	public Object assertQueryNotSame(Object context, Object expected, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertNotSame(expression, expected, value);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is OCL null.
	 * @return the evaluation result
	 */
	public Object assertQueryNull(Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertEquals(expression, null, value);
			PivotTestSuite.appendLog(testName, context, expression, null, "null", null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is equal to expected, using OCL equality regardless of boxed/ecore/unboxed representation.
	 * @return the evaluation result
	 */
	public @Nullable Object assertQueryOCLEquals(@Nullable Object context, @Nullable Object expected, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			assertOCLEquals(expression, expected, value);
			PivotTestSuite.appendLog(testName, context, expression, null, expected != null ? expected.toString() : null, null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is not equal to expected, using OCL equality regardless of boxed/ecore/unboxed representation.
	 * @return the evaluation result
	 */
	public @Nullable Object assertQueryOCLNotEquals(@Nullable Object context, @Nullable Object expected, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			assertOCLNotEquals(expression, expected, value);
			PivotTestSuite.appendLog(testName, context, expression, null, expected != null ? expected.toString() : null, null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Creates a query given the expression that is to be evaluated, then
	 * asserts its result is equal to the evaluation of the given
	 * <code>expectedResultExpression</code>.
	 * <p>
	 * If either the expected result or the expression result is a double, we'll
	 * compare the two with a margin of 0.001.
	 * </p>
	 *
	 * @param expectedResult
	 *            Object with which the query's result is to be compared.
	 * @param expression
	 *            Expression that is to be evaluated. Note that we'll use
	 *            {@link EClass} as this expression's context.
	 */
	public Object assertQueryResults(Object context, @NonNull String expectedResultExpression, @NonNull String expression) {
		try {
			Object expectedResultQuery = evaluateLocal(context, expectedResultExpression);
			Object result = assertQueryEquals(context, expectedResultQuery, expression);
			PivotTestSuite.appendLog(testName, context, expression, null, expectedResultExpression, null);
			return result;
		} catch (Exception e) {
			PivotTestSuite.failOn(expectedResultExpression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is true.
	 * @return the evaluation result
	 */
	public Object assertQueryTrue(Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			TestCase.assertEquals(expression, Boolean.TRUE, value);
			PivotTestSuite.appendLog(testName, context, expression, null, "true", null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Assert that the result of evaluating an expression as a query is an unlimited value.
	 * @return the evaluation result
	 */
	public Object assertQueryUnlimited(Object context, @NonNull String expression) {
		try {
			Object value = evaluate(null, context, expression);
			if (!ValueUtil.isUnlimited(value)) {
				TestCase.assertEquals(expression, ValueUtil.UNLIMITED_VALUE, value);
			}
			PivotTestSuite.appendLog(testName, context, expression, null, "*", null);
			return value;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Creates a query given the expression that is to be evaluated, then
	 * asserts its result contains all elements included in
	 * <code>expectedResult</code>.
	 *
	 * @param expectedResult
	 *            Collection with which the query's result is to be compared.
	 * @param expression
	 *            Expression that is to be evaluated. Note that we'll use
	 *            {@link EClass} as this expression's context.
	 */
	public Object assertResultContainsAll(Object context, @NonNull CollectionValue expectedResult, @NonNull String expression) {
		try {
			Object result = ClassUtil.nonNullState(evaluate(null, context, expression));
			TestCase.assertTrue(expectedResult.getClass().isInstance(result));
			TestCase.assertSame(expectedResult.intSize(), ((CollectionValue) result).intSize());
			Object actualResult = ((CollectionValue) result).includesAll(expectedResult);
			TestCase.assertTrue("Expected " + result + " to contain " + expectedResult, actualResult == ValueUtil.TRUE_VALUE);
			return result;
		} catch (Exception e) {
			PivotTestSuite.failOn(expression, e);
			return null;
		}
	}

	/**
	 * Creates a query given the expression that is to be evaluated, then
	 * asserts its result contains all elements included in
	 * <code>expectedResult</code>.
	 *
	 * @param expectedResultExpression
	 *            Expression which is to be evaluated to determine the expected
	 *            result.
	 * @param expression
	 *            Expression that is to be evaluated. Note that we'll use
	 *            {@link EClass} as this expression's context.
	 */
	public Object assertResultContainsAll(Object context, @NonNull String expectedResultExpression, @NonNull String expression) {
		try {
			Object expectedResultQuery = evaluateLocal(null, expectedResultExpression);
			TestCase.assertTrue(expectedResultQuery instanceof CollectionValue);
			@SuppressWarnings("null")
			Object result = assertResultContainsAll(context, (CollectionValue) expectedResultQuery, expression);
			return result;
		} catch (Exception e) {
			PivotTestSuite.failOn(expectedResultExpression, e);
			return null;
		}
	}

	public void assertSemanticErrorQuery(org.eclipse.ocl.pivot.@Nullable Class contextType, @NonNull String expression, String messageTemplate, Object... bindings) {
		assertBadQuery(SemanticException.class, Diagnostic.ERROR, contextType, expression, messageTemplate, bindings);
	}

	public void assertSemanticWarningQuery(org.eclipse.ocl.pivot.@Nullable Class contextType, @NonNull String expression, String messageTemplate, Object... bindings) {
		BaseCSResource csResource = null;
		try {
			ClassContext classContext = new ClassContext(getEnvironmentFactory(), null, contextType, null);
			if (PivotMessages.UnspecifiedSelfContext.equals(messageTemplate)) {
				classContext.setSelfName("SELF");
			}
			csResource = (BaseCSResource) classContext.createBaseResource(expression);
			PivotUtil.checkResourceErrors(StringUtil.bind(PivotMessagesInternal.ErrorsInResource, expression), csResource);
			PivotUtil.checkResourceWarnings(StringUtil.bind(PivotMessagesInternal.ErrorsInResource, expression), csResource);
			Resource asResource = csResource.getASResource();
			PivotTestSuite.assertNoValidationErrors("Validating", asResource);
			TestCase.fail("Should not have parsed \"" + expression + "\"");
		} catch (ParserException e) {
			TestCase.assertEquals("Exception for \"" + expression + "\"", SemanticException.class, e.getClass());
			assert csResource != null;
			Resource.Diagnostic diagnostic = getWarning(csResource);
			String expectedMessage = StringUtil.bind(messageTemplate, bindings);
			TestCase.assertEquals("Message for \"" + expression + "\"", expectedMessage, diagnostic.getMessage());
			PivotTestSuite.appendLog(testName, contextType, expression, expectedMessage, null, null);
		} catch (IOException e) {
			TestCase.fail(e.getMessage());
		} finally {
			if (csResource != null) {
				EnvironmentFactoryAdapter.disposeAll(csResource);
			}
		}
	}

	/**
	 * Assert that the expression is free of syntactic and semantic errors when parsed
	 * for evaluation on an object of contextType. No evaluation is performed since no
	 * object of contextType need exist.
	 */
	public void assertValidQuery(org.eclipse.ocl.pivot.@NonNull Class contextType, @NonNull String expression) throws Exception {
		ExpressionInOCL query = createQuery(contextType, expression);
		PivotTestCase.assertNoValidationErrors(expression, query);
	}

	/**
	 * Assert that an expression cannot be used as a query, because an exception is thrown
	 * with a diagnostic of severity containing a message that is the result of messageTemplate
	 * resolved by bindings.
	 *
	 * A 'this' in the bindings is replaced by the 'expression'.
	 * @throws IOException
	 */
	public void assertValidationErrorQuery(org.eclipse.ocl.pivot.@Nullable Class contextType, @NonNull String expression,
			String messageTemplate, Object... bindings) {
		BaseCSResource csResource = null;
		try {
			ParserContext classContext = new ClassContext(getEnvironmentFactory(), null, contextType, null);
			csResource = (BaseCSResource) classContext.createBaseResource(expression);
			PivotUtil.checkResourceErrors(StringUtil.bind(PivotMessagesInternal.ErrorsInResource, expression), csResource);
			Resource asResource = csResource.getASResource();
			for (int i = 0; i < bindings.length; i++) {
				if (bindings[i] == this) {
					bindings[i] = expression;
				}
			}
			String expectedMessage = StringUtil.bind(messageTemplate, bindings);
			PivotTestSuite.assertValidationDiagnostics("Validating", asResource, PivotTestCase.getMessages(expectedMessage));
			PivotTestSuite.appendLog(testName, contextType, expression, expectedMessage, null, null);
		} catch (Exception e) {
			TestCase.fail(e.getMessage());
		} finally {
			if (csResource != null) {
				EnvironmentFactoryAdapter.disposeAll(csResource);
			}
		}
	}

	public boolean check(Object context, @NonNull String expression) throws ParserException {
		MetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class contextType = getContextType(context);
		ExpressionInOCL constraint = createInvariant(contextType, expression);
		if (constraint.getOwnedBody().getType() != metamodelManager.getStandardLibrary().getBooleanType()) {
			throw new IllegalArgumentException("constraint is not boolean"); //$NON-NLS-1$
		}
		try {
			Object result = evaluate(constraint, context);
			return result == ValueUtil.TRUE_VALUE;
		} catch (Exception e) {
			return false;
		}
	}

	public void createDocument(String text) {
		throw new UnsupportedOperationException();
		//		try {
		//			ocl.parse(new OCLInput(text));
		//       } catch (Exception e) {
		//           fail("Failed to parse: " + e.getLocalizedMessage());
		//       }
	}

	public void createGeneralization(org.eclipse.ocl.pivot.Class special, org.eclipse.ocl.pivot.Class general) {
		special.getSuperClasses().add(general);
	}

	public Property createOwnedAttribute(org.eclipse.ocl.pivot.Class aClass, String name, Type type) {
		Property eAttribute = PivotFactory.eINSTANCE.createProperty();
		eAttribute.setName(name);
		eAttribute.setType(type);
		aClass.getOwnedProperties().add(eAttribute);
		return eAttribute;
	}

	public org.eclipse.ocl.pivot.@NonNull Class createOwnedClass(org.eclipse.ocl.pivot.Package aPackage, String name, boolean isAbstract) {
		org.eclipse.ocl.pivot.Class eClass = PivotFactory.eINSTANCE.createClass();
		eClass.setName(name);
		eClass.setIsAbstract(isAbstract);
		aPackage.getOwnedClasses().add(eClass);
		return eClass;
	}

	public Enumeration createOwnedEnumeration(org.eclipse.ocl.pivot.Package aPackage, String name) {
		Enumeration eEnum = PivotFactory.eINSTANCE.createEnumeration();
		eEnum.setName(name);
		aPackage.getOwnedClasses().add(eEnum);
		return eEnum;
	}

	public EnumerationLiteral createOwnedLiteral(Enumeration anEnumeration, String name) {
		EnumerationLiteral eLiteral = PivotFactory.eINSTANCE.createEnumerationLiteral();
		eLiteral.setName(name);
		anEnumeration.getOwnedLiterals().add(eLiteral);
		return eLiteral;
	}

	public Operation createOwnedOperation(org.eclipse.ocl.pivot.Class aClass, String name, List<String> paramNames, List<Type> paramTypes, Type type, boolean isQuery) {
		Operation eOperation = PivotFactory.eINSTANCE.createOperation();
		eOperation.setName(name);
		eOperation.setType(type);
		if (paramNames != null) {
			for (int i = 0; i < paramNames.size(); i++) {
				createOwnedParameter(eOperation, paramNames.get(i), paramTypes.get(i));
			}
		}
		aClass.getOwnedOperations().add(eOperation);
		return eOperation;
	}

	public Parameter createOwnedParameter(Operation eOperation, String name, Type type) {
		Parameter eParameter = PivotFactory.eINSTANCE.createParameter();
		eParameter.setName(name);
		eParameter.setType(type);
		eOperation.getOwnedParameters().add(eParameter);
		return eParameter;
	}

	public Property createOwnedReference(org.eclipse.ocl.pivot.Class aClass, String name, org.eclipse.ocl.pivot.Class type) {
		Property eReference = PivotFactory.eINSTANCE.createProperty();
		eReference.setName(name);
		eReference.setType(type);
		aClass.getOwnedProperties().add(eReference);
		return eReference;
	}

	public @Nullable Object evaluate(Object unusedHelper, @Nullable Object context, @NonNull String expression) throws Exception {
		MetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class classContext = getContextType(context);
		ParserContext parserContext = new ClassContext(getEnvironmentFactory(), null, classContext, (context instanceof Type) && !(context instanceof ElementExtension) ? (Type)context : null);
		ExpressionInOCL query = parserContext.parse(classContext, expression);
		PivotTestSuite.assertNoValidationErrors(expression, query);
		try {
			return evaluate(query, context);
		} finally {
			metamodelManager.getASResourceSet().getResources().remove(query.eResource());
		}
	}

	public @Nullable Object evaluate(@NonNull ExpressionInOCL expr, @Nullable Object self) throws Exception {
		Object result = null;

		//    	try {
		if (!PivotTestSuite.useCodeGen) {
			result = super.evaluate(self, expr);
		}
		else {
			CodeGenHelper genModelHelper = getCodeGenHelper(getEnvironmentFactory());
			//			File targetFolder = new File(PivotTestSuite.ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_TESTRESULTS + "/src-gen");
			String packagePath = testPackageName + "/" + testName;
			String packageName = testPackageName + "." + testName;
			if (testProject == null) {
				testProject = testFileSystem.getTestProject("_OCLtests", false);
				testProject.getOutputFile("test-src/" + packagePath).getFile().mkdirs();
				testProject.getOutputFile("test-bin/" + packagePath).getFile().mkdirs();
			}
			File targetFolder = testProject.getOutputFile("test-src").getFile();
			String className = "TestClass" + testCounter++;
			LibraryUnaryOperation.LibraryUnaryOperationExtension testInstance = (LibraryUnaryOperation.LibraryUnaryOperationExtension) genModelHelper.loadClass(expr, targetFolder, packageName, className, true);
			assert testInstance != null;
			Executor executor = new EcoreExecutorManager(self, PivotTables.LIBRARY);
			OperationCallExp callExp = PivotFactory.eINSTANCE.createOperationCallExp();
			callExp.setType(expr.getType());
			result = testInstance.evaluate(executor, callExp.getTypeId(), self);
		}
		//    	} catch (Exception e) {
		//    		fail("Evaluation failed: " + e.getLocalizedMessage());
		//    	}

		return result;
	}

	public @Nullable Object evaluateLocal(@Nullable Object context, @NonNull String expression) throws Exception {
		MetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class contextType = getContextType(context);
		ExpressionInOCL query = createQuery(contextType, expression);
		try {
			return evaluate(context, query);
		} finally {
			metamodelManager.getASResourceSet().getResources().remove(query.eResource());
		}
	}

	public @Nullable Object evaluateWithoutValidation(@Nullable Object unusedHelper, @Nullable Object context, @NonNull String expression) throws Exception {
		MetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class contextType = getContextType(context);
		ExpressionInOCL query = createQuery(contextType, expression);
		try {
			return evaluate(query, context);
		} finally {
			metamodelManager.getASResourceSet().getResources().remove(query.eResource());
		}
	}

	public CodeGenHelper getCodeGenHelper(@NonNull EnvironmentFactoryInternal environmentFactory) throws IOException {
		URI genModelURI = URI.createPlatformResourceURI(
			"/org.eclipse.ocl.pivot/model/Pivot.genmodel",
			true);
		ResourceSet resourceSet = getResourceSet();
		Resource genModelResource = resourceSet.getResource(genModelURI, true);
		String errorsString = PivotUtil.formatResourceDiagnostics(
			genModelResource.getErrors(), "Loading " + genModelURI, "\n");
		if (errorsString != null) {
			// issues.addError(this, errorsString, null, null, null);
			return null;
		}
		GenModel genModel = (GenModel) genModelResource.getContents().get(0);
		return new JavaGenModelCodeGenHelper(genModel, environmentFactory);
		//    	return new GenModelCodeGenHelper(genModel, metamodelManager);
	}

	public @NonNull Type getCollectionType(@NonNull String collectionName, @NonNull Type type, boolean isNullFree) {
		MetamodelManagerInternal metamodelManager = getMetamodelManager();
		Type collectionType = metamodelManager.getCollectionType(collectionName, type, isNullFree, null, null);
		metamodelManager.addLockedElement(collectionType);
		return collectionType;
	}

	@Deprecated /* @deprecated use getError */
	public Resource.Diagnostic getDiagnostic(@NonNull Resource resource) {
		return getError(resource);
	}

	public @NonNull Value getEmptyBagValue() {
		return getIdResolver().createBagOfEach(TypeId.BAG.getSpecializedId(TypeId.OCL_VOID));
	}

	public @NonNull Value getEmptyOrderedSetValue() {
		return getIdResolver().createOrderedSetOfEach(TypeId.ORDERED_SET.getSpecializedId(TypeId.OCL_VOID));
	}

	public @NonNull Value getEmptySequenceValue() {
		return getIdResolver().createSequenceOfEach(TypeId.SEQUENCE.getSpecializedId(TypeId.OCL_VOID));
	}

	public @NonNull Value getEmptySetValue() {
		return getIdResolver().createSetOfEach(TypeId.SET.getSpecializedId(TypeId.OCL_VOID));
	}

	/**
	 * Obtains the error diagnostic describing the problem in the last failed parse,
	 * asserting that it is not <code>null</code>.
	 *
	 * @return the diagnostic
	 */
	public Resource.Diagnostic getError(@NonNull Resource resource) {
		org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic = resource.getErrors().get(0);
		return diagnostic;
	}

	public org.eclipse.ocl.pivot.@NonNull Package getUMLMetamodel() {
		MetamodelManagerInternal metamodelManager = getMetamodelManager();
		return ClassUtil.nonNullState(metamodelManager.getASmetamodel());
	}

	/**
	 * Obtains the warning diagnostic describing the problem in the last failed parse,
	 * asserting that it is not <code>null</code>.
	 *
	 * @return the diagnostic
	 */
	public Resource.Diagnostic getWarning(@NonNull Resource resource) {
		TestCase.assertTrue(resource.getErrors().isEmpty());
		org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic = resource.getWarnings().get(0);
		return diagnostic;
	}

	@SuppressWarnings("null")
	public void loadEPackage(@NonNull String alias, /*@NonNull*/ EPackage ePackage) {
		Element ecoreElement = Ecore2AS.importFromEcore(getEnvironmentFactory(), alias, ePackage);
		getMetamodelManager().addGlobalNamespace(alias, (Namespace) ecoreElement);
	}

	/**
	 * Create a Resource to register a binding-dependent pkg for access with a given nsPrefix and nsUri.
	 */
	public org.eclipse.ocl.pivot.@NonNull Package registerPackage(org.eclipse.ocl.pivot.@NonNull Package pkg, @NonNull String nsPrefix, @NonNull String nsUri) {
		pkg.setNsPrefix(nsPrefix);
		pkg.setURI(nsUri);
		Resource resource = new ResourceImpl(URI.createURI(nsUri));
		resource.getContents().add(pkg);
		getResourceSet().getResources().add(resource);					// FIXME UML needs this
		getResourceSet().getPackageRegistry().put(nsUri, pkg);			//  whereas Ecore needs this
		return pkg;
	}
}