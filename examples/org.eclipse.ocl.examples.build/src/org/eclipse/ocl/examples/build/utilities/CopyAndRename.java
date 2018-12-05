/*
 * <copyright>
 *
 * Copyright (c) 2015, 2018 Willink Transformations and others.
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
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent2;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Convert the line endings of all files in a directory tree to use Unix line endings.
 * Binary file extensions may be excluded from conversion.
 */
public class CopyAndRename extends AbstractWorkflowComponent2 {

	private static final String COMPONENT_NAME = "Copy and Rename";

	private static final Log LOG = LogFactory.getLog(CopyAndRename.class);

	private String fromProject;
	private String script;
	private String toProject;
	private final @NonNull Map<String, String> packageCopyMap = new HashMap<String, String>();
	private final @NonNull Map<String, String> packageRenameMap = new HashMap<String, String>();

	/**
	 * Defines a package copy and rename from some package to another package.
	 */
	public void addPackageCopy(Mapping mapping) {
		packageCopyMap.put(mapping.getFrom(), mapping.getTo());
	}

	/**
	 * Defines a package rename only from some package to another package.
	 */
	public void addPackageRename(Mapping mapping) {
		packageRenameMap.put(mapping.getFrom(), mapping.getTo());
	}

	/**
	 * Sets the source project and package path. e.g. "org.eclipse.qvtd.runtime/src".
	 */
	public void setFromProject(final String fromProject) {
		this.fromProject = fromProject;
	}

	/**
	 * Sets the invoking script for use in the auto-copied text pre-amble.
	 */
	public void setScript(final String script) {
		this.script = script;
	}

	/**
	 * Sets the target project and package path. e.g. "org.eclipse.ocl.pivot/src-gen".
	 */
	public void setToProject(final String toProject) {
		this.toProject = toProject;
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getLogMessage()
	 */
	@Override
	public String getLogMessage() {
		return "copying from '" + fromProject + "' to '" + toProject + "'";
	}

	@Override
	protected void invokeInternal(final WorkflowContext model, final ProgressMonitor monitor, final Issues issues) {
		if (fromProject != null) {
			for (Map.Entry<String, String> entry : packageCopyMap.entrySet()) {
				String fromPackage = entry.getKey();
				String toPackage = entry.getValue();
				String fromFolder = fromProject + "/" + fromPackage.replace('.',  '/');
				String toFolder = toProject + "/" + toPackage.replace('.',  '/');
				File from = new File(fromFolder);
				File to = new File(toFolder);
//				if (f.exists() && f.isDirectory()) {// && f.getName().endsWith(".java")) {
					LOG.info("Copying " + from.getAbsolutePath() + " to " + to.getAbsolutePath());
					try {
						copyFolder(from, to);
					}
					catch (FileNotFoundException e) {
						issues.addError(e.getMessage());
					}
//				}
			}
		}
	}

	@Override
	protected void checkConfigurationInternal(final Issues issues) {
		if (fromProject == null) {
			issues.addWarning("No fromProject specified!");
		}
		if (script == null) {
			issues.addWarning("No script specified!");
		}
		if (toProject == null) {
			issues.addWarning("No toProject specified!");
		}
	}

	/**
	 * Deletes all files and subdirectories under dir. Returns true if all
	 * deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns false.
	 */
	public void copyFolder(@NonNull File fromFolder, @NonNull File toFolder) throws FileNotFoundException {
		if (!fromFolder.exists()) {
			throw new FileNotFoundException(fromFolder.getAbsolutePath());
		}
		if (!toFolder.exists()) {
			toFolder.mkdirs();
		}
		LOG.info("Copying folder " + fromFolder.getPath());
		FileFilter myFilter = new FileFilter() {
			public boolean accept(File path) {
				return !path.isDirectory() && path.getName().endsWith(".java");
			}
		};
		final File[] contents = fromFolder.listFiles(myFilter);
		for (int j = 0; contents != null && j < contents.length; j++) {
			final File fromFile = contents[j];
			copyFile(fromFile, new File(toFolder, fromFile.getName()));
		}
	}

	private void copyFile(File fromFile, File toFile) {
		String editedText = null;
		LOG.info("Copying file " + fromFile.getPath() + " to " + toFile.getPath());
		try {
			String fromText = readFile(fromFile);
			editedText = fromText;
			if (editedText != null) {
				editedText = editedText.replace("@since", "at-since");
				for (Map.Entry<String, String> entry : packageCopyMap.entrySet()) {
					String fromPackage = entry.getKey();
					String toPackage = entry.getValue();
					editedText = editedText.replace(fromPackage, toPackage);
				}
				for (Map.Entry<String, String> entry : packageRenameMap.entrySet()) {
					String fromPackage = entry.getKey();
					String toPackage = entry.getValue();
					editedText = editedText.replace(fromPackage, toPackage);
				}
				editedText =
						"/**\n" +
						" * This file was copied and re-packaged automatically by\n" +
						" *     " + script + "\n" +
						" * from \n" +
						" *     " + fromFile + "\n" +
						" *\n" +
						" * Do not edit this file. \n" +
						" */\n" + editedText;
			}
		} catch (FileNotFoundException e) {
			LOG.error("Failed to open '" + fromFile + "'", e);
			return;
		}
		String toText = null;
		try {
			toText = readFile(toFile);
		} catch (FileNotFoundException e) {}
		if ((editedText != null) && !editedText.equals(toText)) {
			try {
				Writer writer = new FileWriter(toFile);
				try {
					writer.write(editedText);
					writer.flush();
				} catch (IOException e) {
					LOG.error("Failed to write '" + toFile + "'", e);
					return;
				} finally {
					writer.close();
				}
			} catch (IOException e) {
				LOG.error("Failed to re-open '" + toFile + "'", e);
				return;
			}
			LOG.debug("Copied " + fromFile);
		}
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}

	public @Nullable String readFile(@NonNull File file) throws FileNotFoundException {
		Reader reader = new FileReader(file);
		StringBuilder s = new StringBuilder();
		try {
			for (int c; (c = reader.read()) >= 0; ) {
				s.append((char)c);
			}
		} catch (IOException e) {
			LOG.error("Failed to read '" + file + "'", e);
			return null;
		}
		try {
			reader.close();
		} catch (IOException e) {
			LOG.error("Failed to close '" + file + "'", e);
			return null;
		}
		return s.toString();
	}
}
