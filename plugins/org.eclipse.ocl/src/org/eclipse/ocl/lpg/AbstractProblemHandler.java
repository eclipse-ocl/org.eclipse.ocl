/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   IBM - Refactor severity and phase as types for localization
 *******************************************************************************/
package org.eclipse.ocl.lpg;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.ocl.internal.l10n.OCLMessages;

import lpg.runtime.ParseErrorCodes;

/**
 * Partial implementation of the {@link ProblemHandler} API, useful for
 * subclasses to selectively override behaviour.
 */
// TODO: Why implement ParseErrorCodes?
public abstract class AbstractProblemHandler implements ProblemHandler, ParseErrorCodes
{
	private AbstractParser parser;
	private int errorReportLineOffset = 0;

	/**
	 * Initializes me with the parser that can supply line number locations for
	 * problems.
	 *
	 * @param parser my parser
	 */
	protected AbstractProblemHandler(AbstractParser parser) {
		this.parser = parser;
	}

	/**
	 * The default implementation just prints the message using <code>System.out.println</code>.
	 * @param message the problem description
	 */
	protected void addProblem(String message) {
		System.out.println(message);
	}

	@Override
	public void analyzerProblem(Severity problemSeverity, String problemMessage,
			String processingContext, int startOffset, int endOffset) {
		handleProblem(problemSeverity, Phase.ANALYZER, problemMessage,
				processingContext, startOffset, endOffset);
	}

	/**
	 * This default implementation does nothing.
	 */
	@Override
	public void beginParse() {
		// nothing to do
	}

	/**
	 * This default implementation does nothing.
	 */
	@Override
	public void endParse() {
		// nothing to do
	}

	/**
	 * This default implementation does nothing.
	 */
	@Override
	public void beginValidation() {
		// nothing to do
	}

	/**
	 * This default implementation does nothing.
	 */
	@Override
	public void endValidation() {
		// nothing to do
	}

	@Override
	public void flush(Monitor monitor) {
		// nothing to do
	}

	@Override
	public void setParser(AbstractParser parser) {
		this.parser = parser;
	}

	@Override
	public AbstractParser getParser() {
		return parser;
	}

	/**
	 * Implements the interface, invoking <code>addProblem</code> with a line comprising
	 * <code>processingPhase-problemSeverity in processingContext; lineNumber : problemMessage</code>.
	 */
	@Override
	public void handleProblem(Severity problemSeverity, Phase processingPhase,
			String problemMessage, String processingContext, int startOffset, int endOffset) {
		//
		//	The following lines were refactored to workaround around a Java >= 12 bug for Java 8. See bug 416470.
		//
		String lineNumberObject = Integer.toString(parser.getIPrsStream().getTokenAtCharacter(startOffset).getLine());
		String processingPhaseObject = processingPhase != null ? processingPhase.toString() : "?"; //$NON-NLS-1$
		String problemSeverityObject = problemSeverity != null ? problemSeverity.toString() : "?"; //$NON-NLS-1$
		String processingContextObject = processingContext != null ? processingContext : "?"; //$NON-NLS-1$
		String problemMessageObject = problemMessage != null ? problemMessage : "?"; //$NON-NLS-1$
		String message = OCLMessages.bind(
				OCLMessages.ProblemMessage_ERROR_,
				new Object[] { processingPhaseObject, problemSeverityObject,  processingContextObject, lineNumberObject, problemMessageObject});
		addProblem(message);
	}

	@Override
	public void lexerProblem(Severity problemSeverity, String problemMessage,
			String processingContext, int startOffset, int endOffset) {
		handleProblem(problemSeverity, Phase.LEXER, problemMessage,
				processingContext, startOffset, endOffset);
	}

	@Override
	public void parserProblem(Severity problemSeverity, String problemMessage,
			String processingContext, int startOffset, int endOffset) {
		handleProblem(problemSeverity, Phase.PARSER, problemMessage,
				processingContext, startOffset, endOffset);
	}

	@Override
	public void utilityProblem(Severity problemSeverity, String problemMessage,
			String processingContext, int startOffset, int endOffset) {
		handleProblem(problemSeverity, Phase.UTILITY, problemMessage,
				processingContext, startOffset, endOffset);
	}

	@Override
	public void validatorProblem(Severity problemSeverity, String problemMessage,
			String processingContext, int startOffset, int endOffset) {
		handleProblem(problemSeverity, Phase.VALIDATOR, problemMessage,
				processingContext, startOffset, endOffset);
	}

	@Override
	public void setErrorReportLineOffset(int offset) {
		errorReportLineOffset = offset;
	}

	@Override
	public int getErrorReportLineOffset() {
		return errorReportLineOffset;
	}
}
