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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent2;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;

import com.google.common.io.LineReader;

/**
 * Convert the files in a directory tree to use Xtext 2.3.1 compatible references.
 * Text file extensions must be included from conversion.
 * 
 * @deprecated No longer used now that Xtext 2.10 is the minimum requirement.
 */
@Deprecated
public class BackportToXtext_2_3_1 extends AbstractWorkflowComponent2 {

	private static final String COMPONENT_NAME = "Backport to Xtext 2.3.1";

	private static final Log LOG = LogFactory.getLog(BackportToXtext_2_3_1.class);

	private String directory;

	private final Collection<String> textExtensions = new HashSet<String>();

	private final Collection<String> defaultTextExtensions = Arrays.asList(new String[] { "java", "g" });

	private boolean useDefaultTextExtensions = true;

	/**
	 * Sets the directory.
	 *
	 * @param directory
	 *            name of directory
	 */
	public void setDirectory(final String directory) {
		this.directory = directory;
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getLogMessage()
	 */
	@Override
	public String getLogMessage() {
		return "backporting directory '" + directory + "'";
	}

	@Override
	protected void invokeInternal(final WorkflowContext model, final ProgressMonitor monitor, final Issues issues) {
		if (directory != null) {
			final StringTokenizer st = new StringTokenizer(directory, ",");
			while (st.hasMoreElements()) {
				final String dir = st.nextToken().trim();
				final File f = new File(dir);
				if (f.exists() && f.isDirectory()) {
					LOG.info("Backporting " + f.getAbsolutePath());
					try {
						cleanFolder(f.getAbsolutePath());
					}
					catch (FileNotFoundException e) {
						issues.addError(e.getMessage());
					}
				}
			}
		}
	}

	@Override
	protected void checkConfigurationInternal(final Issues issues) {
		if (directory == null) {
			issues.addWarning("No directories specified!");
		}
	}

	/**
	 * Deletes all files and subdirectories under dir. Returns true if all
	 * deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns false.
	 */
	public void cleanFolder(String srcGenPath) throws FileNotFoundException {
		File f = new File(srcGenPath);
		if (!f.exists())
			throw new FileNotFoundException(srcGenPath + " " + f.getAbsolutePath());
		LOG.debug("Backporting folder " + f.getPath());
		convertFolder(f, new FileFilter() {
			public boolean accept(File path) {
				return path.isDirectory() || isTextExtension(path);
			}
		}, false);
	}

	public boolean isTextExtension(File path) {
		String name = path.getName();
		int index = name.lastIndexOf('.');
		String extension = index >= 0 ? name.substring(index+1) : "";
		if (useDefaultTextExtensions && defaultTextExtensions.contains(extension))
			return true;
		return textExtensions.contains(extension);
	}

	public boolean convertFolder(File parentFolder, final FileFilter filter, boolean continueOnError) throws FileNotFoundException {
		if (!parentFolder.exists())
			throw new FileNotFoundException(parentFolder.getAbsolutePath());
		FileFilter myFilter = filter;
		if (myFilter == null) {
			myFilter = new FileFilter() {
				public boolean accept(File pathname) {
					return true;
				}
			};
		}
		LOG.debug("Backporting folder " + parentFolder.toString());
		final File[] contents = parentFolder.listFiles(myFilter);
		for (int j = 0; contents!=null && j < contents.length; j++) {
			final File file = contents[j];
			if (file.isDirectory()) {
				if (!convertFolder(file, myFilter, continueOnError) && !continueOnError)
					return false;
			}
			else {
				convertFile(file);
			}
		}
		return true;
	}

	private void convertFile(File file) {
		try {
			Reader reader = new FileReader(file);
			LineReader lineReader = new LineReader(reader);
			StringBuilder s = new StringBuilder();
			try {
				for (String line; (line = lineReader.readLine()) != null; ) {
					s.append(line);
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
			String sFirst = s.toString();
			String s1 = sFirst.replaceAll(ResourceDescriptionsProvider.class.getName().replaceAll("\\.",  "\\.") + "\\.PERSISTED_DESCRIPTIONS", "\"" + ResourceDescriptionsProvider.PERSISTED_DESCRIPTIONS + "\"");
			String s2 = s1.replaceAll(org.eclipse.xtext.resource.impl.BinaryGrammarResourceFactoryImpl.class.getName().replaceAll("\\.",  "\\."), org.eclipse.ocl.xtext.base.services.BinaryGrammarResourceFactoryImpl.class.getName());
			//
			// Bug 466354
			//
			String s3 = s2.replaceAll("import org\\.eclipse\\.xtext\\.ISetupExtension;", "");
			String s4 = s3.replaceAll("ISetup, ISetupExtension \\{", "ISetup \\{");
			String s5 = s4.replaceAll("@since 2\\.9\\s*\\*/\\s*@Override", "\\*/");
			String sLast = s5;
			if (!sLast.equals(sFirst)) {
				try {
					Writer writer = new FileWriter(file);
					try {
						writer.write(sLast);
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
				LOG.info("Backported " + file);
			}
		} catch (FileNotFoundException e) {
			LOG.error("Failed to open '" + file + "'", e);
			return;
		}
	}

	/**
	 * Returns if the default text extensions are used.
	 */
	public boolean isUseDefaultTextExtensions() {
		return useDefaultTextExtensions;
	}

	/**
	 * Sets if the default text extensions are used.
	 */
	public void setUseDefaultTextExtensions(final boolean useDefaultTextExtensions) {
		this.useDefaultTextExtensions = useDefaultTextExtensions;
	}

	/**
	 * Adds a text extension.
	 */
	public void addTextExtension(final String textExtension) {
		textExtensions.add(textExtension);
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}

}
