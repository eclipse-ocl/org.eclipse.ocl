/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.uml25;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.XMI2UMLExtendedMetaData;

public class XMI252UMLExtendedMetaData extends XMI2UMLExtendedMetaData
{
	public XMI252UMLExtendedMetaData(EPackage.Registry registry) {
		super(registry);
	}

	@Override
	public EPackage getPackage(String namespace) {
		if (namespace != null) {
			if (XMI252UMLResourceFactoryImpl.MYUML_METAMODEL_2_5_NS_URI.equals(namespace)) {
				return UMLPackage.eINSTANCE;
			}
			else if ("http://www.omg.org/spec/MOF/20131001".equals(namespace)) {
				return demandPackage(namespace);
			}
			else if ("http://www.omg.org/spec/XMI/20131001".equals(namespace)) {
				return demandPackage(namespace);
			}
		}
		return super.getPackage(namespace);
	}
}