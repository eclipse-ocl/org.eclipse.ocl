/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;

/**
 * MergeWriter accumulates contributions to an output file which is written when closed.
 * If the output file already exists, any comments preceding the </copyright> declaration are
 * preserved from the existing content and the new leading comments are discarded.
 * <p>
 * New lines are also normalized to a simple new-line.
 */
public class MergeWriter
{
	public static final String DEFAULT_COPYRIGHT = "<copyright>\n\n</copyright>";

	public static final String NL_REGEX = "(\r\n?)|(\n\r?)";

	public static String getCopyright(String copyright) {
		if ((copyright == null) || (copyright.length() <= 0)) {
			return DEFAULT_COPYRIGHT;
		}
		else {
			return copyright.trim().replaceAll(NL_REGEX, "\n");
		}
	}
	
	protected Logger log = Logger.getLogger(getClass());
	
	private @NonNull StringBuilder s = new StringBuilder();
	private @NonNull String fileName;
	
	public MergeWriter(@NonNull String fileName) throws IOException {
		this.fileName = fileName;
		log.info("Generating '" + fileName + "'");
	}

	public void append(@NonNull String string) {
		s.append(string);
	}

	public void close() throws IOException {
		String newContents = s.toString().replaceAll(NL_REGEX, "\n");
		String merged = newContents;
		try {
			FileReader in = new FileReader(fileName);
			StringBuilder s = new StringBuilder();
			char[] cbuf = new char[4096];
			int len;
			while ((len = in.read(cbuf)) > 0) {
				s.append(cbuf, 0, len);
			}
			in.close();
			String oldContents = s.toString().replaceAll(NL_REGEX, "\n");
			int oldIndex = oldContents.indexOf("</copyright>");
			int newIndex = newContents.indexOf("</copyright>");
			if ((oldIndex >= 0) && (newIndex >= 0)) {
				merged = oldContents.substring(0, oldIndex) + newContents.substring(newIndex, newContents.length());
			}
		}
		catch (IOException e) {}
/*		FileInputStream in = new FileInputStream(fileName);
		JControlModel jControlModel = new JControlModel();
		FacadeHelper astFacadeHelper = CodeGenUtil.instantiateFacadeHelper (JMerger.DEFAULT_FACADE_HELPER_CLASS);
		astFacadeHelper.setCompilerCompliance("1.5");
		jControlModel.initialize(astFacadeHelper, "emf-merge.xml");
		JMerger jmerger = new JMerger(jControlModel);
		JCompilationUnit sourceCompilationUnit = jmerger.createCompilationUnitForContents(generated);
		jmerger.setSourceCompilationUnit(sourceCompilationUnit);
		JCompilationUnit targetCompilationUnit = jmerger.createCompilationUnitForInputStream(in);
		jmerger.setTargetCompilationUnit(targetCompilationUnit);
		jmerger.merge();
		String merged = jmerger.getTargetCompilationUnit().getContents(); */
		File file = new File(fileName);
		file.getParentFile().mkdirs();
		FileWriter w = new FileWriter(file);
		w.write(merged);
		w.close();
	}
}
