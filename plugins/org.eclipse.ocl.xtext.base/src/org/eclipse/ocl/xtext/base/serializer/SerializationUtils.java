/*******************************************************************************
 * Copyright (c) 2020, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.XtextSwitch;

public class SerializationUtils
{
	public static @NonNull String defaultIndentation = "    ";

	public static final @NonNull IndexedComparator INDEXED_COMPARATOR = IndexedComparator.INSTANCE;

	public static final class IndexedComparator implements Comparator<@NonNull Indexed>
	{
		public static final @NonNull IndexedComparator INSTANCE = new IndexedComparator();

		@Override
		public int compare(@NonNull Indexed o1, @NonNull Indexed o2) {
			int n1 = o1.getIndex();
			int n2 = o2.getIndex();
			return n1 - n2;
		}
	}


	/**
	 * NodeIterator provides a depth first traversal of an Xtext INode tree, returning each IComposite node twice
	 * first with {@link isChildrenPending()} true for pre-order usage and later with {@link isChildrenPending()} false
	 * for post-order usage.
	 * <br/>
	 * The iterator is constructed with a particular node, which is returned  by the first call to {@link next()}.
	 */
	public static class NodeIterator implements Iterator<@NonNull INode>
	{
		private @Nullable INode node;
		private boolean hasNext;
		private boolean childrenPending;

		public NodeIterator(@NonNull INode node) {
			this.node = node;
			this.hasNext = true;
			this.childrenPending = node instanceof ICompositeNode;
		}

		public NodeIterator(@NonNull NodeIterator nodeIterator) {
			this.node = nodeIterator.node;
			this.hasNext = nodeIterator.hasNext;
			this.childrenPending = nodeIterator.childrenPending;
		}

		@Override
		public boolean hasNext() {
			if (hasNext) {
				return true;
			}
			INode thisNode = node;
			if (thisNode == null) {
				return false;
			}
		//	String thisText =  thisNode.getText();
			INode nextNode = null;
		//	String nextText;
			if (childrenPending) {
				nextNode = ((ICompositeNode)thisNode).getFirstChild();
		//		nextText = nextNode != null ? nextNode.getText() : null;
				this.childrenPending = nextNode instanceof ICompositeNode;
			}
			if (nextNode == null) {
				nextNode = thisNode.getNextSibling();
				if (nextNode == null) {
					nextNode = thisNode.getParent();
		//			nextText = nextNode != null ? nextNode.getText() : null;
					this.childrenPending = false;
				}
				else {
		//			nextText = nextNode.getText();
					this.childrenPending = nextNode instanceof ICompositeNode;
				}
			}
			this.node = nextNode;
			this.hasNext = nextNode != null;
			return hasNext;
		}

		/**
		 * Return true if current node is an ICompositeNode in pre-order, else false if in post-order.
		 */
		public boolean isChildrenPending() {
			assert node instanceof ICompositeNode;
			return childrenPending;
		}

		@Override
		public @NonNull INode next() {
			this.hasNext = false;
			if (node != null) {
				return node;
			}
			throw new NoSuchElementException();
		}
	}

	public static class XtextToString extends XtextSwitch<Object>
	{
		protected final @NonNull StringBuilder s;
		protected final boolean showIds;

		public XtextToString(@NonNull StringBuilder s, boolean showIds) {
			this.s = s;
			this.showIds = showIds;
		}

		protected void appendCardinality(AbstractElement object) {
			String cardinality = object.getCardinality();
			if (cardinality != null) {
				s.append(cardinality);
			}
		}

		protected void appendElement(EObject object) {
			if (showIds) {
				s.append(object.eClass().getName());
				s.append("@");
				s.append(Integer.toHexString(System.identityHashCode(object)));
			}
		}

		@Override
		public Object caseAction(Action object) {
			appendElement(object);
			s.append("{");
			doSwitch(object.getType());
			s.append(".");
			s.append(object.getFeature());
			s.append(object.getOperator());
			s.append("current}");
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseAlternatives(Alternatives object) {
			appendElement(object);
			s.append("(");
			boolean isFirst = true;
			for (AbstractElement g : object.getElements()) {
				if (!isFirst) {
					s.append("|");
				}
				doSwitch(g);
				isFirst = false;
			}
			s.append(")");
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseAssignment(Assignment object) {
			appendElement(object);
			s.append(object.getFeature());
			s.append(object.getOperator());
			doSwitch(object.getTerminal());
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseCrossReference(CrossReference object) {
			appendElement(object);
			doSwitch(object.getTerminal());
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseGroup(Group object) {
			appendElement(object);
			s.append("(");
			boolean isFirst = true;
			for (AbstractElement g : object.getElements()) {
				if (!isFirst) {
					s.append(" ");
				}
				doSwitch(g);
				isFirst = false;
			}
			s.append(")");
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseKeyword(Keyword object) {
			appendElement(object);
			s.append("'");
			s.append(object.getValue());
			s.append("'");
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseParserRule(ParserRule object) {
			appendElement(object);
			s.append(object.getName());
			return this;
		}

		@Override
		public Object caseRuleCall(RuleCall object) {
			appendElement(object);
			doSwitch(object.getRule());
			appendCardinality(object);
			return this;
		}

		@Override
		public Object caseTerminalRule(TerminalRule object) {
			appendElement(object);
			s.append(object.getName());
			return this;
		}

		@Override
		public Object caseTypeRef(TypeRef object) {
			appendElement(object);
		//	s.append("->");
			EClassifier eClassifier = object.getClassifier();
			s.append(eClassifier.getEPackage().getName());
			s.append("::");
			s.append(eClassifier.getName());
			return this;
		}

		@Override
		public Object defaultCase(EObject object) {
			s.append("**");
			if (showIds) {
				appendElement(object);
			}
			else {
				s.append(object.eClass().getName());
			}
			s.append("**");
			return this;
		}
	}

	public static @NonNull List<@NonNull String> indents = new ArrayList<>(10);

	public static void appendIndentation(@NonNull StringBuilder s, int depth) {
		appendIndentation(s, depth, defaultIndentation);
	}
	public static void appendIndentation(@NonNull StringBuilder s, int depth, @NonNull String string) {
		if (depth >= 0) {
			s.append("\n");
		}
		for (int i = 0; i < depth; i++) {
			s.append(string);
		}
	}

	public static @NonNull EClass eClass(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eClass());
	}

	public static @NonNull EStructuralFeature eContainingFeature(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eContainingFeature());
	}

	public static @NonNull EReference eContainmentFeature(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eContainmentFeature());
	}

	public static @NonNull EObject eContainer(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eContainer());
	}

	public static @NonNull AbstractElement getAlternatives(@NonNull AbstractRule abstractRule) {
		return ClassUtil.requireNonNull(abstractRule.getAlternatives());
	}

	public static @NonNull Iterable<@NonNull INode> getChildren(@NonNull ICompositeNode compositeNode) {
		return ClassUtil.nullFree(compositeNode.getChildren());
	}

	public static @NonNull EClassifier getClassifier(TypeRef type) {
		return ClassUtil.requireNonNull(type.getClassifier());
	}

	public static @NonNull EClass getEClassScope(@NonNull AbstractElement abstractElement) {
		TypeRef type = null;
		for (EObject eObject = abstractElement, eChild = null; (type == null) && (eObject != null); eChild = eObject, eObject = eObject.eContainer()) {
			if (eObject instanceof ParserRule) {
				type = ((ParserRule)eObject).getType();
			}
			else if ((eObject instanceof Action) && (((Action)eObject).getFeature() != null))  {
				type = ((Action)eObject).getType();
				break;
			}
			else if ((eObject instanceof Group) && (eChild != null)) {
				List<@NonNull AbstractElement> elements = getElements((Group)eObject);
				int index = elements.indexOf(eChild);
				assert index >= 0;
				for (int i = index; --i >= 0; ) {
					AbstractElement element = elements.get(i);
					if (element instanceof Action) {
						type = ((Action)element).getType();
						break;
					}
				}
			}
		}
		if (type != null) {
			return (EClass)getClassifier(type);
		}
		throw new IllegalStateException();
	}

	public static @NonNull EClass getEContainingClass(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.requireNonNull(eFeature.getEContainingClass());
	}

	public static @NonNull EPackage getEPackage(@NonNull EClassifier eClassifier) {
		return ClassUtil.requireNonNull(eClassifier.getEPackage());
	}

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull CompoundElement compoundElement) {
		return ClassUtil.nullFree(compoundElement.getElements());
	}

	public static @NonNull Grammar getEContainingGrammar(@NonNull EObject eObject) {
		for (EObject eCursor = eObject; (eCursor != null); eCursor = eCursor.eContainer()) {
			if (eCursor instanceof Grammar) {
				return (Grammar)eCursor;
			}
		}
		throw new IllegalStateException();
	}

	public static @NonNull EClass getEReferenceType(@NonNull EReference eReference) {
		return ClassUtil.requireNonNull(eReference.getEReferenceType());
	}

	public static @NonNull EStructuralFeature getEStructuralFeature(@NonNull EClass eClass, @NonNull String featureName) {
		return ClassUtil.requireNonNull(eClass.getEStructuralFeature(featureName));
	}

	public static @NonNull EStructuralFeature getEStructuralFeature(@NonNull Action action) {
		return getEStructuralFeature(getEClassScope(action), getFeature(action));
	}

	public static @NonNull EStructuralFeature getEStructuralFeature(@NonNull Assignment assignment) {
		return getEStructuralFeature(getEClassScope(assignment), getFeature(assignment));
	}

	public static @NonNull EClass getSubTypeOf(@NonNull EClass thisEClass, @NonNull EClass thatEClass) {
		if (thisEClass == thatEClass) {
			return thisEClass;
		}
		else if (thisEClass.isSuperTypeOf(thatEClass)) {
			return thatEClass;
		}
		else if (thatEClass.isSuperTypeOf(thisEClass)) {
			return thisEClass;
		}
		else {
			throw new IllegalStateException("No common subtype");
		}
	}

	public static @NonNull String getFeature(@NonNull Action action) {
		return ClassUtil.requireNonNull(action.getFeature());
	}

	public static @NonNull String getFeature(@NonNull Assignment assignment) {
		return ClassUtil.requireNonNull(assignment.getFeature());
	}

	public static @NonNull String getIndent(int indent) {
		if (indents.size() <= indent) {
			for (int i = indents.size(); i <= indent; i++) {
				indents.add(i == 0 ? "" : (indents.get(i-1) + "  "));
			}
		}
		return indents.get(indent);
	}

	public static @NonNull Keyword getLeft(@NonNull CharacterRange characterRange) {
		return ClassUtil.requireNonNull(characterRange.getLeft());
	}

	public static @NonNull String getName(@NonNull AbstractRule abstractRule) {
		return ClassUtil.requireNonNull(abstractRule.getName());
	}

	public static @NonNull String getName(@NonNull ENamedElement eNamedElement) {
		return ClassUtil.requireNonNull(eNamedElement.getName());
	}

	public static @NonNull Resource getResource(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eResource());
	}
	public static @NonNull Keyword getRight(@NonNull CharacterRange characterRange) {
		return ClassUtil.requireNonNull(characterRange.getRight());
	}
	public static @NonNull AbstractRule getRule(@NonNull RuleCall ruleCall) {
		return ClassUtil.requireNonNull(ruleCall.getRule());
	}
	public static @NonNull String getSafeName(@Nullable Nameable aNameable) {
		if (aNameable == null) {
			return "";
		}
		String name = aNameable.getName();
		if (name == null) {
			name = "";
		}
		return name;
	}

	public static @NonNull AbstractElement getTerminal(@NonNull Assignment assignment) {
		return ClassUtil.requireNonNull(assignment.getTerminal());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull CrossReference crossReference) {
		return ClassUtil.requireNonNull(crossReference.getTerminal());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull UntilToken untilToken) {
		return ClassUtil.requireNonNull(untilToken.getTerminal());
	}

	public static @NonNull TypeRef getType(@NonNull AbstractRule abstractRule) {
		return ClassUtil.requireNonNull(abstractRule.getType());
	}

	public static @NonNull TypeRef getType(@NonNull Action action) {
		return ClassUtil.requireNonNull(action.getType());
	}

	public static @NonNull Iterable<@NonNull Grammar> getUsedGrammars(@NonNull Grammar grammar) {
		return ClassUtil.nullFree(grammar.getUsedGrammars());
	}

	public static @NonNull String getValue(@NonNull Keyword keyword) {
		return ClassUtil.requireNonNull(keyword.getValue());
	}
}
