/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone;

/**
 * A representation of the literals of the enumeration '<em><b>StandaloneResponse</b></em>',
 * and utility methods for working with them.
 */
public enum StandaloneResponse
{
	/**
	 * The '<em><b>OK</b></em>' literal object.
	 */
	OK("ok"), //$NON-NLS-1$
	/**
	 * The '<em><b>FAIL</b></em>' literal object.
	 */
	FAIL("fail"); //$NON-NLS-1$

	private String label;

	StandaloneResponse(String responseLabel) {
		label = responseLabel;
	}

	public String getLabel() {
		return label;
	}
}
