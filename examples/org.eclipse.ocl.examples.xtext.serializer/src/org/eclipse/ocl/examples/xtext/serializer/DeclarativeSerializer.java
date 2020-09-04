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
package org.eclipse.ocl.examples.xtext.serializer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.util.ReplaceRegion;

import com.google.inject.Inject;

public class DeclarativeSerializer implements ISerializer //extends Serializer
{
	@Inject
	private @NonNull UserModelAnalysis modelAnalysis;

	@Inject
	private @NonNull SerializationBuilder serializationBuilder;

	@Override
	public void serialize(EObject eObject, Writer writer, SaveOptions options) throws IOException {
		checkNotNull(eObject, "eObject must not be null.");
		checkNotNull(writer, "writer must not be null.");
		checkNotNull(options, "options must not be null.");
	//	modelAnalysis.getInjectedGrammarAnalysis().analyze();
	//	String s1 = grammarAnalysis.toString();
	//	System.out.println(s1);
	//	System.out.println("\n");
		//
		//	Analyze each element of the user model to determine the applicabale serialization rule(s).
		//
		modelAnalysis.analyze(eObject);
	//	String s2 = modelAnalysis.toString();
	//	System.out.println(s2);
		//
		//	Serialize the user model tree as a (virtual) String concatenation to the serializationBuilder.
		//
		modelAnalysis.serialize(serializationBuilder, eObject, null);
	//	System.out.println(modelAnalysis.diagnose());
		//
		//	Render the (virtual) String concatenation as a pure string for output.
		//
		String serializedOutput = serializationBuilder.toString();
	//	System.out.println(serializedOutput);
		writer.append(serializedOutput);
		writer.flush();
		Iterable<@NonNull String> errors = serializationBuilder.getErrors();
		if (errors != null) {
			StringBuilder s = new StringBuilder();
			s.append("Failed to serialize '" + EcoreUtil.getURI(eObject) + "'");
			for (@NonNull String error : errors) {
				s.append("\n\t");
				s.append(error);
			}
			throw new IOException(s.toString());
		}
	}

	@Override
	public String serialize(EObject obj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String serialize(EObject obj, SaveOptions options) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ReplaceRegion serializeReplacement(EObject obj, SaveOptions options) {
		throw new UnsupportedOperationException();
	}
}
