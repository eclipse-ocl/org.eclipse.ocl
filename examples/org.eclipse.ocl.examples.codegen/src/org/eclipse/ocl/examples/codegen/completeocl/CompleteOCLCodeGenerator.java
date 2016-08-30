/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.completeocl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * CompleteOCLCodeGenerator supports generation of a Java class for all the complementarty declarations
 * in a Complete OCL document.
 */
public class CompleteOCLCodeGenerator extends JavaCodeGenerator
{
	protected final @NonNull CodeGenAnalyzer cgAnalyzer;
	protected final @NonNull Model document;
	protected final @NonNull String rootPackageNames;
	private/* @LazyNonNull*/ CGPackage cgPackage;
	private/* @LazyNonNull*/ String javaSourceCode = null;
	protected final @NonNull JavaGlobalContext<@NonNull CompleteOCLCodeGenerator> globalContext = new JavaGlobalContext<>(this);

	public CompleteOCLCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Model asDocument, @NonNull String rootPackageNames) {
		super(environmentFactory);
		//		getOptions().setUseNullAnnotations(useNullAnnotations);
		this.cgAnalyzer = new CodeGenAnalyzer(this);
		this.document = asDocument;
		this.rootPackageNames = rootPackageNames;
	}

	private void appendSegmentName(@NonNull StringBuilder s, CGPackage sPackage) {
		String pName = sPackage.getName();
		if (pName != null && pName.length() > 0) {
			s.append(pName);
			s.append('.');
		}
	}

	protected @NonNull CompleteOCLAS2CGVisitor createAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer, @NonNull JavaGlobalContext<@NonNull CompleteOCLCodeGenerator> gContext) {
		return new CompleteOCLAS2CGVisitor(analyzer/*, gContext*/);
	}

	protected @NonNull CompleteOCLCG2JavaVisitor createCG2JavaVisitor(@NonNull CGPackage cgPackage, @Nullable List<CGValuedElement> sortedGlobals) {
		return new CompleteOCLCG2JavaVisitor(this, cgPackage, sortedGlobals);
	}

	protected @NonNull String createClassFileContent() {
		String javaSourceCode2;
		CompleteOCLAS2CGVisitor pivot2CGVisitor = createAS2CGVisitor(cgAnalyzer, getGlobalContext());
		CGPackage cgDocument = (CGPackage) ClassUtil.nonNullState(document.accept(pivot2CGVisitor));
		cgDocument.setName(rootPackageNames);
		cgPackage = cgDocument;
		optimize(cgDocument);
		List<CGValuedElement> sortedGlobals = prepareGlobals();
		CompleteOCLCG2JavaVisitor generator = createCG2JavaVisitor(cgDocument, sortedGlobals);
		generator.safeVisit(cgDocument);
		Set<String> allImports = generator.getAllImports();
		Map<@NonNull String, @Nullable String> long2ShortImportNames = ImportUtils.getLong2ShortImportNames(allImports);
		javaSourceCode2 = ImportUtils.resolveImports(generator.toString(), long2ShortImportNames, false);
		return javaSourceCode2;
	}

	public @NonNull String generateClassFile() {
		String javaSourceCode2 = javaSourceCode;
		if (javaSourceCode2 == null) {
			javaSourceCode = javaSourceCode2 = createClassFileContent();
		}
		return javaSourceCode2;
	}

	@Override
	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return cgAnalyzer;
	}

	@Override
	public @NonNull JavaGlobalContext<@NonNull CompleteOCLCodeGenerator> getGlobalContext() {
		return globalContext;
	}

	public @NonNull String getQualifiedName() {
		StringBuilder s =  new StringBuilder();
		CGPackage cgPackage = this.cgPackage;
		appendSegmentName(s, cgPackage);
		while (cgPackage.getPackages().size() > 0) {
			cgPackage = cgPackage.getPackages().get(0);
			appendSegmentName(s, cgPackage);
		}

		s.append(document.getName());
		return s.toString();
	}

	public @NonNull File saveSourceFile(@NonNull String savePath) throws IOException {
		File saveFile = new File(savePath);
		saveSourceFiles(ClassUtil.nonNullState(cgPackage), saveFile);
		return saveFile;
	}

	public void saveSourceFiles(@NonNull CGPackage cgPackage, @NonNull File parentFolder) throws IOException {
		File folder = new File(parentFolder, cgPackage.getName());
		for (CGPackage cgChildPackage : cgPackage.getPackages()) {
			if (cgChildPackage != null) {
				saveSourceFiles(cgChildPackage, folder);
			}
		}
		for (CGClass cgClass : cgPackage.getClasses()) {
			folder.mkdirs();
			String javaCodeSource = generateClassFile();
			Writer writer = new FileWriter(new File(folder, cgClass.getName() + ".java"));
			writer.append(javaCodeSource);
			writer.close();
		}
	}
}
