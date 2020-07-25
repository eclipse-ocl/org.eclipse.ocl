package org.eclipse.ocl.xtext.base.cs2text;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.impl.Serializer;

import com.google.inject.Inject;


public class MySerializer extends Serializer
{
	@Inject
	private UserModelAnalysis modelAnalysis;

	@Inject
	private SerializationBuilder serializationBuilder;

	@Override
	protected void serialize(EObject obj, Appendable appendable, SaveOptions options) throws IOException {
		GrammarAnalysis grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		grammarAnalysis.analyze();
		String s1 = grammarAnalysis.toString();
		System.out.println(s1);
		System.out.println("\n");
		modelAnalysis.analyze(obj);
		String s2 = modelAnalysis.toString();
		System.out.println(s2);
		modelAnalysis.serialize(serializationBuilder, obj);
		String s3 = serializationBuilder.toRenderedString();
		System.out.println(s3);
		appendable.append(s3);
		if (serializationBuilder.hasErrors()) {
			if (appendable instanceof Writer) {
				((Writer)appendable).flush();
			}
			serializationBuilder.throwErrors();
		}
	}
}
