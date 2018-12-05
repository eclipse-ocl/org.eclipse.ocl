/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *  C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	E.D.Willink - Bug 306079, 322159, 353171
 *  K.Hussey - Bug 331143
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import org.eclipse.xtext.nodemodel.impl.RootNode;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;

/**
 * Determines the Xtext version dependent characteristics
 */
public class XtextVersionUtil		// FIXME Exploit XtextVersion available since 2.9
{
	public static int XTEXT_2_8_0 = 2 * 1000000 + 8 * 1000 + 0; 
	public static int XTEXT_2_9_0 = 2 * 1000000 + 9 * 1000 + 0; 

	public static int VERSION = getVersion();

	private static int getVersion() {
		try {
			XtextSyntaxDiagnostic xtextSyntaxDiagnostic = new XtextSyntaxDiagnostic(new RootNode()
			{
				@Override
				protected int[] basicGetLineBreakOffsets() {
					return new int[]{0};
				}

				@Override
				public String getText() {
					return "";
				}
				
			});
			xtextSyntaxDiagnostic.getColumn();
			return XTEXT_2_9_0;
		}
		catch(Throwable e) {
			return XTEXT_2_8_0;
		}
	}
	
	public static boolean hasXtextSyntaxDiagnosticColumn() {
		return VERSION >= XTEXT_2_9_0;
	}
}
