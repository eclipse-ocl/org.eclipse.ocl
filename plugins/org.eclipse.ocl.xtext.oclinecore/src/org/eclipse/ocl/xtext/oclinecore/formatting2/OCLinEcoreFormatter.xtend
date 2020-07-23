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
package org.eclipse.ocl.xtext.oclinecore.formatting2

import com.google.inject.Inject
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis
import org.eclipse.ocl.xtext.essentialocl.formatting2.EssentialOCLFormatter
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS
import org.eclipse.xtext.formatting2.IFormattableDocument

class OCLinEcoreFormatter extends EssentialOCLFormatter {
	
	@Inject extension OCLinEcoreGrammarAccess

	@Inject
	private UserModelAnalysis modelAnalysis;

	@Inject
	private SerializationBuilder serializationBuilder;

	def dispatch void format(TopLevelCS topLevelCS, extension IFormattableDocument document) {
		var GrammarAnalysis grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		grammarAnalysis.analyze();
		var String s1 = grammarAnalysis.toString();
		System.out.println(s1);
		System.out.println("\n");
		modelAnalysis.analyze(topLevelCS);
		var String s2 = modelAnalysis.toString();
		System.out.println(s2);
		modelAnalysis.serialize(serializationBuilder, topLevelCS);
		var String s3 = serializationBuilder.toRenderedString();
		System.out.println(s3);
	}
}
