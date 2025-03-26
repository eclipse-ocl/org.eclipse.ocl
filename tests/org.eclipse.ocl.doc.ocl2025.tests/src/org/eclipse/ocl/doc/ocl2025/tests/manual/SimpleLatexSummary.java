package org.eclipse.ocl.doc.ocl2025.tests.manual;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleLatexSummary
{
	protected PrintWriter writer;

	public SimpleLatexSummary(String testName) throws IOException {
		File file = new File("results/" + testName + ".latex");
		file.getParentFile().mkdirs();
		writer = new PrintWriter(new FileWriter(file));
	}

	public void dispose() throws IOException {
		if (writer != null) {
			writer.close();
		}
	}

	public void printf(String format, Object ... args) {
		if (writer != null) {
			writer.printf(format, args);
		}
	}
}