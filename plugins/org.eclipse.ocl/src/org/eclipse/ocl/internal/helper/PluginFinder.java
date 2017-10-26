/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EDW - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.internal.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * PluginFinder assists in the resolution of plugin locations in a standalone environment.
 * It should be replaced by StandaloneProjectMap once promoted to ocl.common.
 *
 * @since 3.2
 */
public class PluginFinder // BUG 375640 Stolen from StandaloneProjectMap
{
	private final Set<String> requiredMappings;
	private final Map<String, String> resolvedMappings = new HashMap<String, String>();

	public PluginFinder(String... requiredProjects) {
		this.requiredMappings = new HashSet<String>();
		for (String requiredProject : requiredProjects) {
			addRequiredProject(requiredProject);
		}
	}

	public void addRequiredProject(String requiredProject) {
		if (!resolvedMappings.isEmpty()) {
			throw new IllegalStateException("Cannot addRequiredProject to PluginFinder after resolve()"); //$NON-NLS-1$
		}
		requiredMappings.add(requiredProject);
	}

	public String get(String pluginId) {
		return resolvedMappings.get(pluginId);
	}

	private boolean registerBundle(File f) throws IOException {
		JarFile jarFile = new JarFile(f);
		try {
			Manifest manifest = jarFile.getManifest();
			if (manifest != null) {
				String project = manifest.getMainAttributes().getValue("Bundle-SymbolicName"); //$NON-NLS-1$
				if (project != null) {
					int indexOf = project.indexOf(';');
					if (indexOf > 0) {
						project = project.substring(0, indexOf);
					}
					if (requiredMappings.contains(project)) {
						resolvedMappings.put(project, "archive:" + f.toURI() + "!/"); //$NON-NLS-1$ //$NON-NLS-2$
						return true;
					}
				}
			}
			return false;
		}
		finally {
			jarFile.close();
		}
	}

	private boolean registerProject(File file) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
			String project = document.getDocumentElement().getElementsByTagName("name").item(0).getTextContent(); //$NON-NLS-1$
			if (requiredMappings.contains(project)) {
				resolvedMappings.put(project, file.getParentFile().getCanonicalPath() + File.separator);
				return true;
			}
		}
		catch (Exception e) {
			//			logException(file, new WrappedException("Couldn't read " + file, e));
			//			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {}
			}
		}
		return false;
	}

	public void resolve() {
		String property = System.getProperty("java.class.path"); //$NON-NLS-1$
		if (property == null) {
			return;
		}
		String separator = System.getProperty("path.separator"); //$NON-NLS-1$
		String[] entries = property.split(separator);
		for (String entry : entries) {
			File fileEntry = new File(entry);
			try {
				File f = fileEntry.getCanonicalFile();
				if (f.getPath().endsWith(".jar")) { //$NON-NLS-1$
					registerBundle(f);
				} else if (scanFolder(f, new HashSet<String>(), 0)) {

				} else {
					// eclipse bin folder? Tycho target/classes folder?
					while ((f = f.getParentFile()) != null) {
						File dotProject = new File(f, ".project"); //$NON-NLS-1$
						if (dotProject.exists()) {
							registerProject(dotProject);
							break;
						}
					}
				}
				if (resolvedMappings.size() >= requiredMappings.size()) {
					break;
				}
			}
			catch (Exception e) {
			}
		}
	}

	private boolean scanFolder(File f,  Set<String> alreadyVisited, int depth) throws IOException {
		if (!alreadyVisited.add(f.getCanonicalPath())) {
			return true;
		}
		File[] files = f.listFiles();
		boolean containsProject = false;
		File dotProject = null;
		if (files != null) {
			for (File file : files) {
				if (file.exists() && file.isDirectory() && (depth < 2) && !file.getName().startsWith(".")) { //$NON-NLS-1$
					containsProject |= scanFolder(file, alreadyVisited, depth+1);
				} else if (".project".equals(file.getName())) { //$NON-NLS-1$
					dotProject = file;
				} else if (file.getName().endsWith(".jar")) { //$NON-NLS-1$
					registerBundle(file);
				}
			}
		}
		if (!containsProject && dotProject != null)
			registerProject(dotProject);
		return containsProject || dotProject != null;
	}
}
