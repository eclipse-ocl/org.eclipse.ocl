/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Bugs 295166
 *******************************************************************************/

package org.eclipse.ocl.ecore.tests;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.lpg.BasicEnvironment;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.lpg.ProblemHandler.Severity;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ocl.util.OCLUtil;

/**
 * Tests for problem-reporting options.
 *
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("nls")
public class ProblemOptionTest
extends AbstractTestSuite {
	private static class EclipseSupport
	{
		public static void removePreference(PreferenceableOption<?> option, IScopeContext context) {
			try {
				IEclipsePreferences preferences = context.getNode(option.getPluginId());
				preferences.remove(option.getKey());
				preferences.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static <T> void setPreference(PreferenceableOption<T> option, IScopeContext context, T value) {
			try {
				IEclipsePreferences preferences = context.getNode(option.getPluginId());
				preferences.put(option.getKey(), value.toString());
				preferences.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Tests the severity option for the <tt>toLower()</tt> and <tt>toUpper()</tt>
	 * operations on strings.
	 */
	public void test_stringCaseConversion_option() {
		helper.setContext(EcorePackage.Literals.ESTRING);

		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), BasicEnvironment.class);

		// default severity is warning
		assertWarning("self.toUpper()");
		assertWarning("self.toLower()");

		// ignore the operation
		benv.setOption(ProblemOption.STRING_CASE_CONVERSION, ProblemHandler.Severity.OK);
		assertOK("self.toUpper()");
		assertOK("self.toLower()");

		// the operation fails to parse
		benv.setOption(ProblemOption.STRING_CASE_CONVERSION, ProblemHandler.Severity.ERROR);
		assertError("self.toUpper()");
		assertError("self.toLower()");

		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			benv.removeOption(ProblemOption.STRING_CASE_CONVERSION);
			EclipseSupport.setPreference(ProblemOption.STRING_CASE_CONVERSION, ConfigurationScope.INSTANCE, Severity.WARNING);
			assertWarning("self.toUpper()");
			benv.removeOption(ProblemOption.STRING_CASE_CONVERSION);
			EclipseSupport.setPreference(ProblemOption.STRING_CASE_CONVERSION, ConfigurationScope.INSTANCE, Severity.OK);
			assertOK("self.toUpper()");
			benv.removeOption(ProblemOption.STRING_CASE_CONVERSION);
			EclipseSupport.setPreference(ProblemOption.STRING_CASE_CONVERSION, ConfigurationScope.INSTANCE, Severity.ERROR);
			assertError("self.toUpper()");
			EclipseSupport.removePreference(ProblemOption.STRING_CASE_CONVERSION, ConfigurationScope.INSTANCE);
		}
	}

	/**
	 * Tests the severity option for the closure() iterator.
	 */
	public void test_closure_option() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), BasicEnvironment.class);

		// default severity is OK
		assertOK("self->closure(eSuperPackage)");

		// ignore the closure iterator
		benv.setOption(ProblemOption.CLOSURE_ITERATOR, ProblemHandler.Severity.WARNING);
		assertWarning("self->closure(eSuperPackage)");

		// ignore the closure iterator
		benv.setOption(ProblemOption.CLOSURE_ITERATOR, ProblemHandler.Severity.OK);
		assertOK("self->closure(eSuperPackage)");

		// the closure iterator fails to parse
		benv.setOption(ProblemOption.CLOSURE_ITERATOR, ProblemHandler.Severity.ERROR);
		assertError("self->closure(eSuperPackage)");
	}

	/**
	 * Tests the severity option for the <tt>''</tt> escape in strings.
	 */
	public void test_singleQuoteEscape_option() {
		helper.setContext(EcorePackage.Literals.ESTRING);

		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), BasicEnvironment.class);

		// default severity is warning
		//    	assertWarning("'this isn''t a nice string'");

		// ignore the single-quote
		benv.setOption(ProblemOption.STRING_SINGLE_QUOTE_ESCAPE, ProblemHandler.Severity.OK);

		assertOK("'this isn''t a nice string'");

		// the single-quote escape fails to parse
		benv.setOption(ProblemOption.STRING_SINGLE_QUOTE_ESCAPE, ProblemHandler.Severity.ERROR);
		//       	assertError("'this isn''t a nice string'");
	}

	/**
	 * Tests the severity option for the <tt>"..."</tt> escape for element names.
	 */
	public void test_doubleQuoteElementName_option() {
		helper.setContext(EcorePackage.Literals.EPACKAGE);

		BasicEnvironment benv = OCLUtil.getAdapter(ocl.getEnvironment(), BasicEnvironment.class);

		// default severity is warning
		assertWarning("self.\"eClassifiers\"");

		// ignore the double-quote
		benv.setOption(ProblemOption.ELEMENT_NAME_QUOTE_ESCAPE, ProblemHandler.Severity.OK);

		assertOK("self.\"eClassifiers\"");

		// the double-quote escape fails to parse
		benv.setOption(ProblemOption.ELEMENT_NAME_QUOTE_ESCAPE, ProblemHandler.Severity.ERROR);
		assertError("self.\"eClassifiers\"");
	}

	//
	// Framework methods
	//

	void assertOK(String oclQuery) {
		try {
			helper.createQuery(oclQuery);

			Diagnostic problem = helper.getProblems();
			assertNull(problem);
		} catch (Exception e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}
	}

	void assertWarning(String oclQuery) {
		try {
			helper.createQuery(oclQuery);

			Diagnostic problem = helper.getProblems();
			assertNotNull(problem);
			assertEquals(Diagnostic.WARNING, problem.getSeverity());
		} catch (Exception e) {
			fail("Failed to parse: " + e.getLocalizedMessage());
		}
	}

	void assertError(String oclQuery) {
		try {
			helper.createQuery(oclQuery);

			fail("Should have failed to parse");
		} catch (ParserException e) {
			// success
			debugPrintln("Got expected exception: " + e.getLocalizedMessage());
		} catch (Exception e) {
			fail("Failed to parse for unexpected reason: " + e.getLocalizedMessage());
		}
	}
}
