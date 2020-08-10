/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.serializer;

import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;

public class CompleteOCLAnalysisProvider extends AbstractAnalysisProvider
{
	private static RTGrammarAnalysis analysis = null;

	@Override
	public RTGrammarAnalysis getAnalysis() {
		if (analysis == null) {
			analysis = new RTGrammarAnalysis();
		}
		return analysis;
	}
}
