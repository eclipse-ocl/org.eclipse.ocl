/*
 * <copyright>
 *
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent2;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.corext.codemanipulation.OrganizeImportsOperation;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.text.edits.TextEdit;

/**
 * Convert the line endings of all files in a directory tree to use Unix line endings.
 * Binary file extensions may be excluded from conversion.
 */
public class OrganizeImports extends AbstractWorkflowComponent2 {

	private static final String COMPONENT_NAME = "Organize Imports";

	private static final Log LOG = LogFactory.getLog(OrganizeImports.class);

	private String directory;

	private final Collection<String> binaryExtensions = new HashSet<String>();

	private final Collection<String> defaultBinaryExtensions = Arrays.asList(new String[] { "xtextbin" });

	private boolean useDefaultBinaryExtensions = true;

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
		return "converting directory '" + directory + "'";
	}

	@Override
	protected void invokeInternal(final WorkflowContext model, final ProgressMonitor monitor, final Issues issues) {
		if (directory != null) {
			final StringTokenizer st = new StringTokenizer(directory, ",");
			while (st.hasMoreElements()) {
				final String dir = st.nextToken().trim();
				final File f = new File(dir);
				if (f.exists() && f.isDirectory()) {
					LOG.info("Converting " + f.getAbsolutePath());
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
		LOG.debug("Converting folder " + f.getPath());
		convertFolder(f, new FileFilter() {
			public boolean accept(File path) {
				return !isBinaryExtension(path);
			}
		}, false);
	}

	public boolean isBinaryExtension(File path) {
		String name = path.getName();
		int index = name.lastIndexOf('.');
		String extension = index >= 0 ? name.substring(index+1) : "";
		if (useDefaultBinaryExtensions && defaultBinaryExtensions.contains(extension))
			return true;
		return binaryExtensions.contains(extension);
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
		LOG.debug("Converting folder " + parentFolder.toString());
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
/*		try {
			Reader reader = new FileReader(file);
			StringBuilder s = new StringBuilder();
			boolean changed = false;
			try {
				for (int c; (c = reader.read()) >= 0; ) {
					if (c == '\r') {
						changed = true;
					}
					else {
						s.append((char)c);
					}
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
			if (changed) {
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
				LOG.info("Converted " + file);
			}
		} catch (FileNotFoundException e) {
			LOG.error("Failed to open '" + file + "'", e);
			return;
		} */
	}
	
	private void organizeImports(ICompilationUnit cu) throws OperationCanceledException, CoreException {
//		OrganizeImportsAction a;
	    cu.becomeWorkingCopy(null);
	    NullProgressMonitor pm = new NullProgressMonitor();
	    CompilationUnit unit = cu.reconcile(AST.JLS4, false, null, pm);

	    OrganizeImportsOperation op = new OrganizeImportsOperation(cu, unit, true, true, true, null);

	    TextEdit edit = op.createTextEdit(pm);
	    if (edit == null) {
	        return;
	    }

	    JavaModelUtil.applyEdit(cu, edit, true, pm);
	    cu.commitWorkingCopy(true, pm);
	    cu.save(pm, true);
	}
	/**
	 * Returns if the default binary extensions are used.
	 */
	public boolean isUseDefaultBinaryExtensions() {
		return useDefaultBinaryExtensions;
	}

	/**
	 * Sets if the default binary extensions are used.
	 */
	public void setUseDefaultBinaryExtensions(final boolean useDefaultBinaryExtensions) {
		this.useDefaultBinaryExtensions = useDefaultBinaryExtensions;
	}

	/**
	 * Adds a binary extension.
	 */
	public void addBinaryExtension(final String binaryExtension) {
		binaryExtensions.add(binaryExtension);
	}

	/**
	 * @see org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent#getComponentName()
	 */
	@Override
	public String getComponentName() {
		return COMPONENT_NAME;
	}

}
