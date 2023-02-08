/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * ProjectTestFileSystemHelper refines the basic TestFileSystemHelper to provide the support for creating
 * stamndard test project artefacts.
 */
public class ProjectTestFileSystemHelper extends TestFileSystemHelper
{
	private @NonNull List<@NonNull String> exportedPackages = new ArrayList<>();
	private @NonNull List<@NonNull String> requiredBundles = new ArrayList<>();

	public void addExportedPackage(@NonNull String exportedPackage) {
		if (!exportedPackages.contains(exportedPackage)) {
			exportedPackages.add(exportedPackage);
		}
	}

	public void addRequiredBundle(@NonNull String requiredBundle) {
		if (!requiredBundles.contains(requiredBundle)) {
			requiredBundles.add(requiredBundle);
		}
	}
	@Override
	protected void appendBuildSpec(@NonNull Writer s) throws IOException {
		s.append("\t<buildSpec>\n");
		s.append("\t\t<buildCommand>\n");
		s.append("\t\t\t<name>org.eclipse.jdt.core.javabuilder</name>\n");
		s.append("\t\t\t<arguments>\n");
		s.append("\t\t\t</arguments>\n");
		s.append("\t\t</buildCommand>\n");
		s.append("\t\t<buildCommand>\n");
		s.append("\t\t\t<name>org.eclipse.pde.ManifestBuilder</name>\n");
		s.append("\t\t\t<arguments>\n");
		s.append("\t\t\t</arguments>\n");
		s.append("\t\t</buildCommand>\n");
		s.append("\t\t<buildCommand>\n");
		s.append("\t\t\t<name>org.eclipse.pde.SchemaBuilder</name>\n");
		s.append("\t\t\t<arguments>\n");
		s.append("\t\t\t</arguments>\n");
		s.append("\t\t</buildCommand>\n");
		s.append("\t</buildSpec>\n");
	}

	@Override
	protected void appendNatures(@NonNull Writer s) throws IOException {
		s.append("\t<natures>\n");
		s.append("\t\t<nature>org.eclipse.pde.PluginNature</nature>\n");
		s.append("\t\t<nature>org.eclipse.jdt.core.javanature</nature>\n");
		s.append("\t</natures>\n");
	}

	@Override
	protected @Nullable File createBuildDotProperties(@NonNull File projectFolder, @NonNull String projectName) {
		File file = new File(projectFolder, "build.properties");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("bin.includes = META-INF/\n");
			s.append("source.. = test-src/\n");
			s.append("");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

	@Override
	protected @Nullable File createDotClasspathFile(@NonNull File projectFolder, @NonNull String projectName) {
		File file = new File(projectFolder, ".classpath");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("<classpath>\n");
			s.append("  <classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER\"/>\n");
			s.append("  <classpathentry kind=\"con\" path=\"org.eclipse.pde.core.requiredPlugins\"/>\n");
			s.append("  <classpathentry kind=\"src\" path=\"test-src\"/>\n");
			s.append("  <classpathentry kind=\"output\" path=\"test-bin\"/>\n");
			s.append("</classpath>\n");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

	@Override
	public void createFiles(@NonNull File projectFolder, @NonNull String projectName) {
		super.createFiles(projectFolder, projectName);
		createJdtCorePrefsFile(projectFolder, projectName);
	}

	protected @Nullable File createJdtCorePrefsFile(@NonNull File projectFolder, @NonNull String projectName) {
		File metaInfFolder = new File(projectFolder, ".settings");
		metaInfFolder.mkdir();
		File file = new File(metaInfFolder, "org.eclipse.jdt.core.prefs");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("org.eclipse.jdt.core.compiler.annotation.nullanalysis=enabled\n");
			s.append("org.eclipse.jdt.core.compiler.problem.includeNullInfoFromAsserts=enabled\n");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

/*	@Override
	public @Nullable File createManifestFile(@NonNull File projectFolder, @NonNull String projectName) {
		File projectFolder2 = new File(projectFolder, "META-INF");
		projectFolder2.mkdir();
		File file = new File(projectFolder2, "MANIFEST.MF");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("Manifest-Version: 1.0\n");
			s.append("Automatic-Module-Name: " + projectName + "\n");
			s.append("Bundle-ManifestVersion: 2\n");
			s.append("Bundle-Name: " + projectName + "\n");
			s.append("Bundle-SymbolicName: " + projectName + ";singleton:=true\n");
			s.append("Bundle-Version: 1.0.0.qualifier\n");
			s.append("Require-Bundle: org.eclipse.qvtd.runtime\n");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	} */

	@Override
	protected @Nullable File createManifestFile(@NonNull File projectFolder, @NonNull String projectName) {
		File metaInfFolder = new File(projectFolder, "META-INF");
		metaInfFolder.mkdir();
		File file = new File(metaInfFolder, "MANIFEST.MF");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("Manifest-Version: 1.0\n");
			s.append("Automatic-Module-Name: " + projectName + "\n");
			s.append("Bundle-ManifestVersion: 2\n");
			s.append("Bundle-Name: " + projectName + "\n");
			s.append("Bundle-SymbolicName: " + projectName + ";singleton:=true\n");
			s.append("Bundle-Version: 1.0.0.qualifier\n");
			s.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.8\n");
			boolean isFirst = true;
			for (@NonNull String requiredBundle : getRequiredBundles()) {
				s.append(isFirst ? "Require-Bundle: " : ",\n ");
				s.append(requiredBundle);
				isFirst = false;
			}
			s.append("\n");
			isFirst = true;
			List<@NonNull String> exportedPackages = getExportedPackages();
			for (@NonNull String exportedPackage : exportedPackages) {
				s.append(isFirst ? "Export-Package: " : ",\n ");
				s.append(exportedPackage);
				isFirst = false;
			}
			s.append("\n");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

	protected @NonNull List<@NonNull String> getExportedPackages() {
		return exportedPackages;
	}

	protected @NonNull List<@NonNull String> getRequiredBundles() {
		return requiredBundles;
	}
}