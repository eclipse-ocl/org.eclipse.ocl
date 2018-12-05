/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A TestCaseLogger gathers any console output for review at the end of a test.
 */
public final class TestCaseLogger extends ConsoleAppender
	{
		public static final @NonNull TestCaseLogger INSTANCE = new TestCaseLogger();

		private static Logger rootLogger = Logger.getRootLogger();

		private boolean installed = false;
		private @NonNull StringBuilder s = new StringBuilder();
		
		private TestCaseLogger() {
			super(new SimpleLayout(), SYSTEM_OUT); 
			setName("TestCaseLogger");
		}
		
		@Override
		public void append(LoggingEvent event) {
			if (event.getLevel().isGreaterOrEqual(Level.INFO)) {
				String renderedMessage = event.getRenderedMessage();
				s.append(renderedMessage);
			}
//			super.append(event);
		}

		public void clear() {
			s.setLength(0);
		}

		public String get() {
			return s.toString();
		}
		
		public @Nullable Iterable<Appender> install() {
			List<Appender> removedAppenders = null;
			if (!installed) {
				Enumeration<?> allAppenders = rootLogger.getAllAppenders();
				while (allAppenders.hasMoreElements()) {
					Object element = allAppenders.nextElement();
					if (element instanceof Appender) {
						Appender appender = (Appender)element;
						if (removedAppenders == null) {
							removedAppenders = new ArrayList<Appender>();
						}
						removedAppenders.add(appender);
					}
				}
				if (removedAppenders != null) {
					for (Appender appender : removedAppenders) {
						rootLogger.removeAppender(appender);
					}
				}
				rootLogger.addAppender(this);
				installed = true;
			}
			clear();
			return removedAppenders;
		}
		
		public void uninstall(@Nullable Iterable<Appender> removedAppenders) {
			rootLogger.removeAppender(this);
			if (removedAppenders != null) {
				for (Appender appender : removedAppenders) {
					rootLogger.addAppender(appender);
				}
			}
			installed = false;
		}
	}