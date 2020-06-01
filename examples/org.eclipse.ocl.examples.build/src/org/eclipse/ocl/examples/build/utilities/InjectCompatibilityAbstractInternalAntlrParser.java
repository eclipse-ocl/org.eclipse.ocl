/*
 * <copyright>
 *
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.build.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent2;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.xtext.base.utilities.CompatibilityAbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;

/**
 * Convert the line endings of all files in a directory tree to use Unix line endings.
 * Trailing whitespace is also removed.
 * Binary file extensions may be excluded from conversion.
 */
public class InjectCompatibilityAbstractInternalAntlrParser extends AbstractWorkflowComponent2 {

	private static final String COMPONENT_NAME = "Inject CompatibilityAbstractInternalAntlrParser";

	private static final Log LOG = LogFactory.getLog(InjectCompatibilityAbstractInternalAntlrParser.class);

	private String fileName;

	/**
	 * Sets the directory.
	 *
	 * @param directory
	 *            file name of parser
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getLogMessage()
	 */
	@Override
	public String getLogMessage() {
		return "converting '" + fileName + "'";
	}

	@Override
	protected void invokeInternal(final WorkflowContext model, final ProgressMonitor monitor, final Issues issues) {
		if (fileName != null) {
						final File f = new File(fileName);
						LOG.info("Converting " + f.getAbsolutePath());
						convertFile(f);
		}
	}

	@Override
	protected void checkConfigurationInternal(final Issues issues) {
		if (fileName == null) {
			issues.addWarning("No fileName specified!");
		}
	}
	private void convertFile(File file) {
		try {
			StringBuilder s = new StringBuilder();
		//	boolean changed = false;
		//	boolean trimmed = false;
			LineNumberReader reader = new LineNumberReader(new FileReader(file));
			try {
				String oldText = "extends " + AbstractInternalAntlrParser.class.getSimpleName();
				String newText = "extends " + CompatibilityAbstractInternalAntlrParser.class.getName();
				for (String line; (line = reader.readLine()) != null; ) {
					s.append(line.replace(oldText, newText));
					s.append("\n");
				}
			} catch (IOException e) {
				LOG.error("Failed to read '" + file + "'", e);
				return;
			}
			try {
				reader.close();
			} catch (IOException e) {
				LOG.error("Failed to close '" + file + "'", e);
				return;
			}
		//	if (changed || trimmed) {
				try {
					Writer writer = new FileWriter(file);
					try {
						writer.write(s.toString());
						writer.flush();
					} catch (IOException e) {
						LOG.error("Failed to write '" + file + "'", e);
						return;
					} finally {
						writer.close();
					}
				} catch (IOException e) {
					LOG.error("Failed to re-open '" + file + "'", e);
					return;
				}
				LOG.info("Updated " + file);
		//	}
		} catch (FileNotFoundException e) {
			LOG.error("Failed to open '" + file + "'", e);
			return;
		}
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}

}
