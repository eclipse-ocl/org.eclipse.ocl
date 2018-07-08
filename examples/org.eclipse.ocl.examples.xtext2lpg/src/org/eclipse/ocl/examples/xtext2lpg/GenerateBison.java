/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
import org.eclipse.xtext.XtextStandaloneSetup;

/**
 * Generates the javaFolder/'javaPackageName'/visitorClassName.java file providing
 * a static Java-creation of the libraryFile OCL standard library definition.
 */
public abstract class GenerateBison extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	protected String javaFolder;
	protected String javaPackageName;
	protected String in;
	protected String syntaxName;

	@Override
	public void checkConfiguration(Issues issues) {
		if (javaPackageName == null) {
			issues.addError(this, "javaPackageName not specified.");
		}
		if (in == null) {
			issues.addError(this, "in not specified.");
		}
	}

	protected abstract @NonNull String generateBisonModel(@NonNull Syntax syntax);
	protected abstract @NonNull String generateBisonLexer(@NonNull Syntax syntax);
	protected abstract @NonNull String generateBisonParser(@NonNull Syntax syntax);

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		PivotStandaloneSetup.doSetup();
		XtextStandaloneSetup.doSetup();
		return resourceSet;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues issues) {
		System.setProperty("line.separator", "\n");
		XBNFPackage.eINSTANCE.getName();
		URI fileURI = URI.createURI(in, true);
		//		File folder = new File(javaFolder + '/' + javaPackageName.replace('.', '/'));
		log.info("Loading '" + fileURI);
		try {
			new File(javaFolder).mkdirs();
			ResourceSet resourceSet = getResourceSet();
			Resource ecoreResource = resourceSet.getResource(fileURI, true);
			Syntax syntax = (Syntax) ecoreResource.getContents().get(0);
			String kwLexer = generateBisonModel(syntax);
			String fileName = javaFolder + "/" + syntaxName + ".h";
			FileWriter fw = new FileWriter(fileName);
			log.info("Generating to ' " + fileName + "'");
			fw.append(kwLexer);
			fw.close();
			String lexer = generateBisonLexer(syntax);
			fileName = javaFolder + "/" + syntaxName + ".lex";
			log.info("Generating to ' " + fileName + "'");
			fw = new FileWriter(fileName);
			fw.append(lexer);
			fw.close();
			String parser = generateBisonParser(syntax);
			fileName = javaFolder + "/" + syntaxName + ".y";
			log.info("Generating to ' " + fileName + "'");
			fw = new FileWriter(fileName);
			fw.append(parser);
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	public void setIn(String in) {
		this.in = in;
	}

	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}

	public void setJavaPackageName(String javaPackageName) {
		this.javaPackageName = javaPackageName;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void setSyntaxName(String syntaxName) {
		this.syntaxName = syntaxName;
	}
}
