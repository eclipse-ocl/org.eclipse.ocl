/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

/**
 * If a TestCaseAppender is installed duiring a test, any console output causes a test failure.
 */
public final class TestCaseAppender extends ConsoleAppender
{
	private static Logger rootLogger = Logger.getRootLogger();
	public static TestCaseAppender INSTANCE = new TestCaseAppender();

	private boolean installed = false;
	
	private TestCaseAppender() {
		super(new SimpleLayout(), SYSTEM_OUT); 
		setName("TestCaseAppender");
	}
	
	@Override
	public void append(LoggingEvent event) {
		if (event.getLevel().isGreaterOrEqual(Level.INFO)) {
			String renderedMessage = event.getRenderedMessage();
			ThrowableInformation throwableInformation = event.getThrowableInformation();
			Throwable throwable = throwableInformation != null ? throwableInformation.getThrowable() : null;
			throw new Error(renderedMessage, throwable);
		}
//		super.append(event);
	}
	
	public void install() {
		if (!installed) {
			rootLogger.addAppender(this);
			installed = true;
		}
	}
	
	public void uninstall() {
		rootLogger.removeAppender(this);
		installed = false;
	}
}
