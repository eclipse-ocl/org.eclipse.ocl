/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.impl.Serializer;

import com.google.inject.Inject;

public class DeclarativeSerializer extends Serializer
{
	@Inject
	private @NonNull UserModelAnalysis modelAnalysis;

	@Inject
	private @NonNull SerializationBuilder serializationBuilder;

	@Override
	protected void serialize(EObject rootEObject, Appendable appendable, SaveOptions options) throws IOException {
		assert rootEObject != null;
		assert appendable != null;
		GrammarAnalysis grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		grammarAnalysis.analyze();
	//	String s1 = grammarAnalysis.toString();
	//	System.out.println(s1);
	//	System.out.println("\n");
		modelAnalysis.analyze(rootEObject);
	//	String s2 = modelAnalysis.toString();
	//	System.out.println(s2);
		modelAnalysis.serialize(serializationBuilder, rootEObject, null);
		System.out.println(modelAnalysis.diagnose());
		String s3 = serializationBuilder.toRenderedString();
	//	System.out.println(s3);
		appendable.append(s3);
		if (serializationBuilder.hasErrors()) {
			if (appendable instanceof Writer) {
				((Writer)appendable).flush();
			}
			serializationBuilder.throwErrors();
		}
	}
}
