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
package org.eclipse.ocl.examples.build.textile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.markup.MarkupUtils;
import org.eclipse.ocl.xtext.markupcs.FontElement;
import org.eclipse.ocl.xtext.markupcs.Markup;
import org.eclipse.ocl.xtext.markupcs.MarkupElement;
import org.eclipse.ocl.xtext.markupcs.NewLineElement;
import org.eclipse.ocl.xtext.markupcs.NullElement;
import org.eclipse.ocl.xtext.markupcs.OCLCodeElement;
import org.eclipse.ocl.xtext.markupcs.OCLTextElement;
import org.eclipse.ocl.xtext.markupcs.TextElement;
import org.eclipse.ocl.xtext.markupcs.util.MarkupSwitch;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;

public abstract class GenerateTextileForModelUtils extends GenerateTextileForModel
{
	protected final @NonNull Comparator<Nameable> nameableComparator = new Comparator<Nameable>()
	{
		@Override
		public int compare(Nameable o1, Nameable o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			return m1.compareTo(m2);
		}
	};

	protected final @NonNull Comparator<Iteration> iterationComparator = new Comparator<Iteration>()
	{
		@Override
		public int compare(Iteration o1, Iteration o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			int diff = m1.compareTo(m2);
			if (diff != 0) return diff;
			int s1 = o1.getOwnedIterators().size();
			int s2 = o2.getOwnedIterators().size();
			return s1 - s2;
		}
	};

	protected static final @NonNull Comparator<Operation> operationComparator = new OperationComparator();

	protected static final class OperationComparator implements Comparator<Operation>
	{
		public static Map<String,String> nameMap = new HashMap<String, String>();
		static {
			nameMap.put("=", " 1");
			nameMap.put("<>", " 2");
			nameMap.put("<", " 3");
			nameMap.put("<=", " 4");
			nameMap.put(">=", " 5");
			nameMap.put(">", " 6");
		}

		@Override
		public int compare(Operation o1, Operation o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			if (m1 == null) m1 = "";
			if (m2 == null) m2 = "";
			String k1 = nameMap.get(m1);
			String k2 = nameMap.get(m2);
			if (k1 != null) m1 = k1;
			if (k2 != null) m2 = k2;
			int diff = m1.compareTo(m2);
			if (diff != 0) return diff;
			int s1 = o1.getOwnedParameters().size();
			int s2 = o2.getOwnedParameters().size();
			return s1 - s2;
		}
	}

	protected static class MarkupToTextile extends MarkupSwitch<@Nullable String>
	{
		@SuppressWarnings("unused") private final Namespace scope;
		private final StringBuilder s = new StringBuilder();

		public MarkupToTextile(Namespace scope) {
			this.scope = scope;
		}

		@Override
		public String caseFontElement(FontElement element) {
			String tag = element.getFont().equals("e") ? "_" : "*";
			s.append(tag);
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append(tag);
			return s.toString();
		}

		@Override
		public String caseMarkupElement(MarkupElement element) {
			s.append("$$");
			s.append(element.eClass().getName());
			return s.toString();
		}

		@Override
		public String caseNewLineElement(NewLineElement element) {
			s.append(element.getText());
			return s.toString();
		}

		@Override
		public String caseNullElement(NullElement object) {
			return s.toString();
		}

		@Override
		public String caseOCLCodeElement(OCLCodeElement element) {
			s.append("bc.. \n");
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("\n");
			s.append("p. \n");
			s.append("\n");
			return s.toString();
		}

		@Override
		public String caseOCLTextElement(OCLTextElement element) {
			s.append("@");
			for (MarkupElement subElement : element.getElements()) {
				doSwitch(subElement);
			}
			s.append("@");
			return s.toString();
		}

		@Override
		public String caseTextElement(TextElement element) {
			for (String text : element.getText()) {
				s.append(text);
			}
			return s.toString();
		}
	};

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
		return options;
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

	protected @NonNull List<Property> getSortedAssociations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Property> allElements = new HashSet<Property>();
		for (Property asProperty : asClass.getOwnedProperties()) {
			//			[let pAssociations : Sequence(Property) = pClass.ownedAttribute->select(e | not e.type.oclIsKindOf(DataType) and e.type.owningTemplateParameter->isEmpty())->asSequence()]
			if (!(asProperty.getType() instanceof DataType)) {
				allElements.add(asProperty);
			}
		}
		List<Property> sortedElements = new ArrayList<Property>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Property> getSortedAttributes(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Property> allElements = new HashSet<Property>();
		for (Property asProperty : asClass.getOwnedProperties()) {
			if (asProperty.getType() instanceof DataType) {
				allElements.add(asProperty);
			}
		}
		List<Property> sortedElements = new ArrayList<Property>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSortedClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		List<org.eclipse.ocl.pivot.Class> sortedElements = new ArrayList<>(asPackage.getOwnedClasses());
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Constraint> getSortedConstraints(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>();
		for (Constraint asConstraint : asOperation.getOwnedConstraints()) {
			allElements.add(asConstraint);
		}
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Iteration> getSortedIterations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Iteration> allElements = new HashSet<Iteration>();
		for (Operation asOperation : asClass.getOwnedOperations()) {
			if (asOperation instanceof Iteration) {
				allElements.add((Iteration) asOperation);
			}
		}
		List<Iteration> sortedElements = new ArrayList<Iteration>(allElements);
		Collections.sort(sortedElements, iterationComparator);
		return sortedElements;
	}

	protected @NonNull List<Operation> getSortedOperations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Set<Operation> allElements = new HashSet<Operation>();
		for (Operation asOperation : asClass.getOwnedOperations()) {
			if (!(asOperation instanceof Iteration)) {
				allElements.add(asOperation);
			}
		}
		List<Operation> sortedElements = new ArrayList<Operation>(allElements);
		Collections.sort(sortedElements, operationComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Package> getSortedPackages(@NonNull Model model) {
		Set<org.eclipse.ocl.pivot.Package> allElements = new HashSet<>();
		TreeIterator<EObject> tit = model.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				allElements.add((org.eclipse.ocl.pivot.Package)eObject);
			}
		}
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Constraint> getSortedPostconditions(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>(asOperation.getOwnedPostconditions());
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<Constraint> getSortedPreconditions(@NonNull Operation asOperation) {
		Set<Constraint> allElements = new HashSet<Constraint>(asOperation.getOwnedPreconditions());
		List<Constraint> sortedElements = new ArrayList<Constraint>(allElements);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected String prettyPrint(@NonNull Comment comment, Namespace scope) {
		Markup markup = decode(comment, scope);
		StringBuilder s = new StringBuilder();
		for (MarkupElement element : markup.getElements()) {
			s.append(prettyPrint(element, scope));
		}
		return s.toString();
	}

	protected String prettyPrint(@NonNull Constraint constraint, Namespace scope) {
		PrettyPrintOptions options = createOptions(scope);
		//		PrettyPrintExprVisitor visitor = new PrettyPrintExprVisitor(options);
		PrettyPrinter printer = PrettyPrinter.createPrinter(constraint, options);
		try {
			printer.appendElement(constraint);
			String string = printer.toString(options.getIndentStep(), options.getLinelength());
			//				System.out.println("Expr-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
			return string;
		}
		catch (Exception e) {
			e.printStackTrace();
			return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
		}
	}

	protected String prettyPrint(@NonNull Element element, Namespace scope) {
		PrettyPrintOptions options = createOptions(scope);
		PrettyPrinter printer = PrettyPrinter.createNamePrinter(element, options);
		try {
			printer.appendElement(element);
			String string = printer.toString(options.getIndentStep(), options.getLinelength());
			//			System.out.println("Name-prettyPrint : " + element.eClass().getName() + "/" + element.eClass().getName() + " => " + string);
			return string;
		}
		catch (Exception e) {
			e.printStackTrace();
			return printer.toString() + " ... " + e.getClass().getName() + " - " + e.getLocalizedMessage();
		}
	}

	protected String prettyPrint(MarkupElement element, Namespace scope) {
		return new MarkupToTextile(scope).doSwitch(element);
	}
}
