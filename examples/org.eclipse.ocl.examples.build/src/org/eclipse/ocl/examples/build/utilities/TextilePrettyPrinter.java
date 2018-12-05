/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.xtext.markup.MarkupUtils;
import org.eclipse.ocl.xtext.markupcs.Markup;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;

/**
 * TextilePrettyPrinter provides the static class interfaces to the PrettyPrinter for use
 * as Acceleo queries.
 */
public class TextilePrettyPrinter
{
	public static class Expr
	{
		public Expr() {}

		public String prettyPrint(@NonNull Visitable element, @Nullable Namespace scope) {
			return prettyPrint((Element)element, scope);
		}
		public @NonNull String prettyPrint(@NonNull Element element, @Nullable Namespace scope) {
			PrettyPrintOptions options = createOptions(scope);
//			PrettyPrintExprVisitor visitor = new PrettyPrintExprVisitor(options);
			PrettyPrinter printer = PrettyPrinter.createPrinter(element, options);
			try {
				printer.appendElement(element);
				String string = printer.toString(options.getIndentStep(), options.getLinelength());
				//				System.out.println("Expr-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
				return string;
			}
			catch (Exception e) {
				e.printStackTrace();
				return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
			}
		}
	}
	
	public static class Name
	{
		public Name() {}
		
		public @NonNull String prettyPrint(@NonNull Visitable element, @Nullable Namespace scope) {
			return prettyPrint((Element)element, scope);
		}
		public @NonNull String prettyPrint(@NonNull Element element, @Nullable Namespace scope) {
			PrettyPrintOptions options = createOptions(scope);
			PrettyPrinter printer = PrettyPrinter.createNamePrinter(element, options);
			try {
				printer.appendElement(element);
				String string = printer.toString(options.getIndentStep(), options.getLinelength());
//				System.out.println("Name-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
				return string;
			}
			catch (Exception e) {
				e.printStackTrace();
				return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
			}
		}
		public Markup decode(@NonNull Comment comment, @Nullable Namespace scope) {
			String body = comment.getBody();
			if (body == null) {
				throw new NullPointerException("Missing Comment body");
			}
			IParseResult parseResult = MarkupUtils.decode(body);
			if (parseResult == null) {
				throw new NullPointerException("Missing ParseResult for \"" + body + "\"");
			}
			Markup markup = (Markup) parseResult.getRootASTElement();
			for (INode parseError : parseResult.getSyntaxErrors()) {
				System.out.println(parseError);
			}
			if (markup == null) {
				throw new NullPointerException("Missing prsed content for \"" + body + "\"");
			}
			return markup;
		}
	}

	public static PrettyPrintOptions.@NonNull Global createOptions(@Nullable Namespace scope) {
		PrettyPrintOptions.Global options = new PrettyPrintOptions.Global(scope)
		{
			@Override
			public @Nullable Set<String> getReservedNames() {
				return null;
			}

			@Override
			public @Nullable Set<String> getRestrictedNames() {
				return null;
			}			
		};
//		options.setUseParentheses(false);
//		options.setUseParentheses(false);
		return options;
	}
}