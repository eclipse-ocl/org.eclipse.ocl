/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations ltd, University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;

/**
 * Component expected data
 * <ul>
 * <li>derivedVisitorInterfaceName: A mandatory name of the visitor interface which will be
 * 		weaved into the target Ecore model</li>
 * <li>derivedVisitorInterfaceName: A mandatory qualified name of the visitor interface
 *		type instance. </li>
 * <li>in: three mandatory Ecore models URI (String) with this particular order:
 * 		<ol>
 *			<li> A mandatory target Ecore model URI to which the new derived Visitor interface will belong to </li>
 *			<li> A mandatory super Ecore model URI to which the super Visitor interface belongs to</li>
 *			<li> A mandatory root Ecore model URI to which the root Visitor interface will belongs to (for complex language composition cases) </li>
 *		</ol></li>
 *
 *  TBD
 * @author adolfosbh
 */
public class DerivedVisitorPatternWeaver extends PredefinedQVToTransformationExecutor {
	private String rootVisitorInterfaceName;
	private String rootVisitorInterfaceQualifiedName;
	private String superVisitorInterfaceName;
	private String superVisitorInterfaceQualifiedName;
	private String derivedVisitorInterfaceName;
	private String derivedVisitorInterfaceQualifiedName;
	
	public String getRootVisitorInterfaceName() {
		return rootVisitorInterfaceName;
	}
	
	public void setRootVisitorInterfaceName(String rootVisitorInterfaceName) {
		this.rootVisitorInterfaceName = rootVisitorInterfaceName;
	}
	
	public String getRootVisitorInterfaceQualifiedName() {
		return rootVisitorInterfaceQualifiedName;
	}

	public void setRootVisitorInterfaceQualifiedName(
			String rootVisitorInterfaceQualifiedName) {
		this.rootVisitorInterfaceQualifiedName = rootVisitorInterfaceQualifiedName;
	}

	public String getDerivedVisitorInterfaceName() {
		return derivedVisitorInterfaceName;
	}
	
	public void setDerivedVisitorInterfaceName(String derivedVisitorInterfaceName) {
		this.derivedVisitorInterfaceName = derivedVisitorInterfaceName;
	}	
	
	public String getDerivedVisitorInterfaceQualifiedName() {
		return derivedVisitorInterfaceQualifiedName;
	}

	
	public void setDerivedVisitorInterfaceQualifiedName(
			String derivedVisitorInterfaceQualifiedName) {
		this.derivedVisitorInterfaceQualifiedName = derivedVisitorInterfaceQualifiedName;
	}

	public String getSuperVisitorInterfaceName() {
		return superVisitorInterfaceName;
	}

	public void setSuperVisitorInterfaceName(String superVisitorInterfaceName) {
		this.superVisitorInterfaceName = superVisitorInterfaceName;
	}

	public String getSuperVisitorInterfaceQualifiedName() {
		return superVisitorInterfaceQualifiedName;
	}

	public void setSuperVisitorInterfaceQualifiedName(
			String superVisitorInterfaceQualifiedName) {
		this.superVisitorInterfaceQualifiedName = superVisitorInterfaceQualifiedName;
	}

	@Override
	public void checkConfiguration(Issues issues) {
		if (derivedVisitorInterfaceName == null) {
			issues.addError(this, "derivedVisitorInterfaceName not specified.");
		}
		if (derivedVisitorInterfaceQualifiedName == null) {
			issues.addError(this, "derivedVisitorInterfaceQualifiedName not specified.");
		}
	}
	
	@Override
	protected void initializeConfigurationProperties(ExecutionContextImpl context) {
		context.setConfigProperty("derivedVisitorInterfaceName", getDerivedVisitorInterfaceName());
		context.setConfigProperty("derivedVisitorInterfaceQualifiedName", getDerivedVisitorInterfaceQualifiedName());
		context.setConfigProperty("superVisitorInterfaceName", getSuperVisitorInterfaceName());
		context.setConfigProperty("superVisitorInterfaceQualifiedName", getSuperVisitorInterfaceQualifiedName());
		context.setConfigProperty("rootVisitorInterfaceName", getRootVisitorInterfaceName() == null ? 
				getSuperVisitorInterfaceName() : getRootVisitorInterfaceName());
		context.setConfigProperty("rootVisitorInterfaceQualifiedName", getRootVisitorInterfaceQualifiedName() == null ?
				getSuperVisitorInterfaceQualifiedName() : getRootVisitorInterfaceQualifiedName());
	}
	
	@Override
	protected String getPredefinedTransformationURI() {
		return "platform:/plugin/org.eclipse.ocl.examples.build/src/org/eclipse/ocl/examples/build/qvto/DerivedVisitorPatternTransf.qvto";		
	}
}