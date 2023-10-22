/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.formatting.impl.AbstractNodeModelFormatter;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Strings;

import com.google.inject.Inject;

/**
 * A DeclarativeFormatter uses the grammar-specific SerializationMetaData derived from the *.xtext parsing grammar and *.idioms formatting idioms
 * to drive a re-synthesis of the whitespace in an Inode range of the user document. The re-synthesis traverses the INode tree to ensure that its
 * structure is preserved. ParserRules and comments are determined by the INodes.
 */
public class DeclarativeFormatter extends AbstractNodeModelFormatter
{
	/**
	 * The UserModelAnalysis provides the SerializationMetaData that defines comment and rule formatting instructions.
	 */
	@Inject
	private @NonNull UserModelAnalysis modelAnalysis;

	/**
	 * The SerializationBuilder aggregates the formatted text.
	 */
	@Inject
	private @NonNull SerializationBuilder serializationBuilder;

	private int selectStart;
	private int selectEnd;

	private @Nullable String darkText(@NonNull String text) {
		if (text.length() <= 0) {
			return null;
		}
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (Character.isWhitespace(c)) {
				return null;
			}
		}
		return text;
	}

	@Override
	public IFormattedRegion format(ICompositeNode root, final int selectStart, final int selectLength) {
		serializationBuilder.resetBuilder();
		this.selectStart = selectStart;
		this.selectEnd = selectStart + selectLength;
		assert root != null;
		int selectEnd = selectStart+selectLength;
		//
		//	Find selected node range.
		//
/*		XtextAbstractCommentSegmentSupport xtextCommentSegmentSupport = new XtextCommentSegmentSupport();
		SerializationMetaData serializationMetaData = modelAnalysis.getSerializationMetaData();
		List<@NonNull Comment> preComments = xtextCommentSegmentSupport.getPreComments(serializationMetaData, startNode);
		List<@NonNull Comment> postComments = xtextCommentSegmentSupport.getPostComments(serializationMetaData, endNode);
		if ((preComments != null) && (preComments.size() > 0)) {
			newStart = preComments.get(0).getNode().getOffset();
		}
		if ((postComments != null) && (postComments.size() > 0)) {
			newStart = postComments.get(postComments.size()-1).getNode().getEndOffset();
		} */
		ILeafNode startNode = NodeModelUtils.findLeafNodeAtOffset(root, selectStart);
		assert startNode != null;
		@Nullable ILeafNode endNodeOrEOF = /*selectLength > 0 ?*/ NodeModelUtils.findLeafNodeAtOffset(root, selectEnd) /*: startNode*/;		// zero length is total reformat
	//	assert zendNode != null;
		String rootText = root.getText();
		int oldStart = startNode.getTotalOffset();
		int oldEnd = endNodeOrEOF != null ? endNodeOrEOF.getTotalEndOffset() : root.getTotalEndOffset();
		@SuppressWarnings("unused") String oldSelectedText = rootText.substring(selectStart, selectEnd);
		@SuppressWarnings("unused") String oldTotalText = rootText.substring(oldStart, oldEnd);
		System.out.println("format  oldSelectedText: " + oldSelectedText.length() + " chars at " + selectStart + "-" + selectEnd + ": '" + Strings.convertToJavaString(oldSelectedText) + "'");
		System.out.println("\toldTotalText: " + oldTotalText.length() + " chars at " + oldStart + "-" + oldEnd + ": '" + Strings.convertToJavaString(oldTotalText) + "'");
		//
		//	Condition the insertion site to follow a new-line else follow all whitespace.
		//
		for (; oldStart < selectStart; oldStart++) {		// Step to first non-whitespace character
			char c = rootText.charAt(oldStart);
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		char precedingChar = 0;
		for (int i = oldStart; i > 0; --i) {		// Step to first preceding new-line / non-whitespace character
			char c = rootText.charAt(i-1);
			if (c == '\n') {
				oldStart = i;
				precedingChar = c;
				break;
			}
			else if (!Character.isWhitespace(c)) {
				precedingChar = c;
				break;
			}
		}
		//
		//	Condition the insertion site to precede a new-line else follow all whitespace.
		//
		for (; selectEnd < oldEnd; --oldEnd) {		// Step to past last non-whitespace character
			char c = rootText.charAt(oldEnd-1);
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		char followingChar = 0;
		for (int i = oldEnd; oldStart < i; i++) {	// Step to last following new-line / non-whitespace character
			char c = rootText.charAt(i);
			if (c == '\n') {
				oldEnd = i;
				followingChar = c;
				break;
			}
			else if (!Character.isWhitespace(c)) {
				followingChar = c;
				break;
			}
		}
		@SuppressWarnings("unused") String oldContextText = rootText.substring(oldStart, oldEnd);
		System.out.println("\toldContextText: " + (precedingChar != 0 ? ("'" + Strings.convertToJavaString(String.valueOf(precedingChar)) + "'") : "«BOF»") + " then " + oldStart + "-" + oldEnd + ": '" + Strings.convertToJavaString(oldContextText) + "' then " + (followingChar != 0 ? ("'" + Strings.convertToJavaString(String.valueOf(followingChar)) + "'") : "«EOF»"));
		String prefixText = rootText.substring(oldStart, selectStart);
		String suffixText = rootText.substring(selectEnd, oldEnd);
		String darkPrefixText = darkText(prefixText);
		String darkSuffixText = darkText(suffixText);
		int i = selectStart;
		for ( ; i >= oldStart; --i) {
			char c = rootText.charAt(i-1);
			if (c == '\n') {
				break;
			}
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		String whitePrefixText = i < selectStart ? rootText.substring(i, selectStart) : null;
		i = selectEnd;
		for ( ; i <= oldEnd; i++) {
			char c = rootText.charAt(i);
			if (c == '\n') {
				break;
			}
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		String whiteSuffixText = i > selectEnd ? rootText.substring(selectEnd, i) : null;
		System.out.println("\tdarkPrefixText: " + (darkPrefixText != null ? "'" + Strings.convertToJavaString(darkPrefixText) + "'" : "null"));
		System.out.println("\twhitePrefixText: " + (whitePrefixText != null ? "'" + Strings.convertToJavaString(whitePrefixText) + "'" : "null"));
		System.out.println("\tdarkSuffixText: " + (darkSuffixText != null ? "'" + Strings.convertToJavaString(darkSuffixText) + "'" : "null"));
		System.out.println("\twhiteSuffixText: " + (whitePrefixText != null ? "'" + Strings.convertToJavaString(whiteSuffixText) + "'" : "null"));
	//	System.out.println("\treplace: " + (selectEnd - selectStart) + " chars at " + selectStart + "-" + selectEnd + " by the " + newText.length() + " chars of '" + Strings.convertToJavaString(newText) + "'");
	//	EObject rootEObject = NodeModelUtils.findActualSemanticObjectFor(root);
	//	assert rootEObject != null;
	//	modelAnalysis.analyze(rootEObject);	-- SerializationRules not used
		//
		//	Format the selected nodes with globally consistent surrounding whitespace.
		//
		Stack<@NonNull INode> startNodeStack = new Stack<>();
		for (INode node = startNode; node != null; node = node.getParent()) {
			startNodeStack.push(node);
		}
		formatAncestry(startNodeStack, endNodeOrEOF, "");
		String newText = serializationBuilder.toString();
		int newStart = 0;
		int newEnd = newText.length();
		System.out.println("\tnewText: " + newText.length() + " chars of '" + Strings.convertToJavaString(newText) + "'");
	//
		//	Condition the new text to follow a new-line else follow all whitespace.
		//
		if (followingChar != '\n') {
			for (; newStart < newEnd; newStart++) {		// Step to first non-whitespace character
				char c = newText.charAt(newStart);
				if (!Character.isWhitespace(c)) {
					break;
				}
			}
		}
		//
		//	Condition the new text to precede a new-line else precede all whitespace.
		//
		if (precedingChar != '\n') {
			for (; newStart < newEnd; newEnd--) {		// Step to first non-whitespace character
				char c = newText.charAt(newEnd-1);
				if (!Character.isWhitespace(c)) {
					break;
				}
			}
		}
//		String newContextText = newText.substring(newStart, newEnd);
		if (darkPrefixText != null) {
			int index = newText.indexOf(darkPrefixText);
			if (index >= 0) {
				newText = newText.substring(index + darkPrefixText.length());
			}
		}
		if ((whitePrefixText != null) && newText.startsWith(whitePrefixText)) {
			newText = newText.substring(whitePrefixText.length());
		}
		if (darkSuffixText != null) {
			int index = newText.lastIndexOf(darkSuffixText);
			if (index >= 0) {
				newText = newText.substring(0, index);
			}
		}
		if ((whiteSuffixText != null) && newText.endsWith(whiteSuffixText)){
			newText = newText.substring(0, newText.length() - whiteSuffixText.length());
		}
//		System.out.println("\tnewContextText: '" + Strings.convertToJavaString(newContextText) + "'");
		System.out.println("\treplace: " + (selectEnd - selectStart) + " chars at " + selectStart + "-" + selectEnd + " by the " + newText.length() + " chars of '" + Strings.convertToJavaString(newText) + "'");
		return new FormattedRegion(selectStart, selectEnd - selectStart, newText);
	}

	/**
	 * Format all nodes between and including startNode and endNode with surrounding whitespace as if
	 * no other outside nodes existed.
	 */
	protected void formatAncestry(@NonNull Stack<@NonNull INode> startNodeStack, @Nullable ILeafNode endNodeOrEOF, @NonNull String indent) {
//		if (endNodeOrEOF != null) {
//			String endNodeText = NodeModelUtils.getTokenText(endNodeOrEOF);
//			System.out.println(indent + "formatAncestry endNode: " + endNodeOrEOF.getTotalOffset() + "-" + endNodeOrEOF.getOffset() + " " + endNodeOrEOF.getEndOffset() + "-" + endNodeOrEOF.getTotalEndOffset() + " " + NameUtil.debugSimpleName(endNodeOrEOF) + " '" + Strings.convertToJavaString(endNodeText) + "'");
//		}
//		else {
//			System.out.println(indent + "formatAncestry endNode: EOF");
//		}
		ICompositeNode parentStartNode = (ICompositeNode) startNodeStack.pop();
		String parentStartNodeText = NodeModelUtils.getTokenText(parentStartNode);
//		System.out.println(indent + "formatAncestry parentStartNode: " + parentStartNode.getTotalOffset() + "-" + parentStartNode.getOffset() + " .. "  + parentStartNode.getEndOffset() + "-" + parentStartNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(parentStartNode) + " '" + Strings.convertToJavaString(parentStartNodeText) + "'");
		INode childStartNode = startNodeStack.peek();
		String childStartNodeText = NodeModelUtils.getTokenText(childStartNode);
//		System.out.println(indent + "formatAncestry childStartNode: " + childStartNode.getTotalOffset() + "-" + childStartNode.getOffset() + " .. " +  + childStartNode.getEndOffset() + "-" + childStartNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(childStartNode) + " '" + Strings.convertToJavaString(childStartNodeText) + "'");
		EObject semanticElement = parentStartNode.getSemanticElement();
		assert semanticElement != null;
		AbstractElement compoundedGrammarElement = getCompoundedGrammarElement(parentStartNode);
		UserElementFormatter elementFormatter = modelAnalysis.createUserElementFormatter(parentStartNode, compoundedGrammarElement, semanticElement);
		//
		//	Format the preceding whitespace for the excluded ancestry.
		//
		for (@NonNull SerializationSegment formattingSegment : elementFormatter.getInnerFormattingSegments()) {
			if (formattingSegment.isValue()) {
				break;
			}
			if (formattingSegment.isControl()) {
				formattingSegment.format(elementFormatter, serializationBuilder);
			}
		}
		//
		//	Format the included node range.
		//
		for (@NonNull INode childNode : SerializationUtils.getChildren(parentStartNode)) {
			String childNodeText = NodeModelUtils.getTokenText(childNode);
//			System.out.println(indent + "formatAncestry childNode: " + childNode.getTotalOffset() + "-" + childNode.getOffset() + " .. " + childNode.getEndOffset() + "-" + childNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(childNode) + " '" + Strings.convertToJavaString(childNodeText) + "'");
			if (!hasStarted(childNode) && (childNode == childStartNode)&& (childStartNode instanceof ICompositeNode)) {
				formatAncestry(startNodeStack, endNodeOrEOF, indent + "  ");
			}
			else {
				formatNode(childNode, endNodeOrEOF);
			}
			if (hasEnded(childNode)) {
				break;
			}
		}
		//
		//	Format the trailing whitespace for the excluded ancestry.
		//
		boolean isTail = false;
		for (@NonNull SerializationSegment formattingSegment : elementFormatter.getInnerFormattingSegments()) {
			if (!isTail) {
				if (formattingSegment.isValue()) {
					isTail = true;;
				}
			}
			else {
				if (formattingSegment.isControl()) {
					formattingSegment.format(elementFormatter, serializationBuilder);
				}
			}
		}
	}

	protected void formatCompositeNode(@NonNull ICompositeNode compositeNode, @Nullable ILeafNode endNodeOrEOF) {
		String text = compositeNode.getText();
		assert text != null;
		EObject semanticElement = compositeNode.getSemanticElement();
		assert semanticElement != null;
		AbstractElement compoundedGrammarElement = getCompoundedGrammarElement(compositeNode);
		EList<?> assignedCollection = getAssignedCollection(compositeNode, compoundedGrammarElement);
		UserElementFormatter elementFormatter = modelAnalysis.createUserElementFormatter(compositeNode, compoundedGrammarElement, semanticElement);
		//
		//	Different previous assigned collection requires outer head formatting.
		//
		EList<?> prevAssignedCollection = null;
		if (assignedCollection != null) {
			INode prevSibling = compositeNode.getPreviousSibling();
			if (prevSibling instanceof ICompositeNode) {
				prevAssignedCollection = getAssignedCollection((ICompositeNode)prevSibling, getCompoundedGrammarElement(prevSibling));
			}
		}
		if ((assignedCollection == null) || (assignedCollection != prevAssignedCollection)) {
			@NonNull SerializationSegment [] outerFormattingSegments = elementFormatter.getOuterFormattingSegments();
			for (@NonNull SerializationSegment formattingSegment : outerFormattingSegments) {
				if (formattingSegment.isValue()) {
					break;
				}
				formattingSegment.format(elementFormatter, serializationBuilder);
			}
		}
		//
		//	Inner formatting of the specific node.
		//
		@NonNull SerializationSegment[] innerFormattingSegments = elementFormatter.getInnerFormattingSegments();
		for (@NonNull SerializationSegment formattingSegment : innerFormattingSegments) {
			if (formattingSegment.isValue()) {
				for (@NonNull INode childNode : SerializationUtils.getChildren(compositeNode)) {
					formatNode(childNode, endNodeOrEOF);
					if (hasEnded(childNode)) {
						break;
					}
				}
			}
			else if (hasStarted(compositeNode)) {
				formattingSegment.format(elementFormatter, serializationBuilder);
			}
		}
		//
		//	Different next assigned collection requires outer tail formatting.
		//
		EList<?> nextAssignedCollection = null;
		if (assignedCollection != null) {
			INode nextSibling = compositeNode.getNextSibling();
			if (nextSibling instanceof ICompositeNode) {
				nextAssignedCollection = getAssignedCollection((ICompositeNode)nextSibling, getCompoundedGrammarElement(nextSibling));
			}
		}
		if ((assignedCollection == null) || (assignedCollection != nextAssignedCollection)) {
			boolean isTail = false;
			@NonNull SerializationSegment [] outerFormattingSegments = elementFormatter.getOuterFormattingSegments();
			for (@NonNull SerializationSegment formattingSegment : outerFormattingSegments) {
				if (!isTail) {
					if (formattingSegment.isValue()) {
						isTail = true;;
					}
				}
				else {
					formattingSegment.format(elementFormatter, serializationBuilder);
				}
			}
		}
	}

	protected void formatLeafNode(@NonNull ILeafNode leafNode) {
		int offset = leafNode.getOffset();
		int endOffset = leafNode.getEndOffset();
		int totalOffset = leafNode.getTotalOffset();
		boolean hasStarted = (selectStart <= endOffset);
		//	assert progress.hasStarted() == (selectStart <= endOffset);
		String text = leafNode.getText();
		assert text != null;
		if (!leafNode.isHidden()) {
			EObject semanticElement = leafNode.getSemanticElement();
			assert semanticElement != null;
			AbstractElement compoundedGrammarElement = getCompoundedGrammarElement(leafNode);
			UserElementFormatter elementFormatter = modelAnalysis.createUserElementFormatter(leafNode, compoundedGrammarElement, semanticElement);
			//
			//	Different previous grammar element requires outer head formatting.
			//
			for (INode prevSibling = leafNode.getPreviousSibling(); (prevSibling == null) || (prevSibling instanceof ILeafNode); prevSibling = prevSibling.getPreviousSibling()) {
				if ((prevSibling == null) || !((ILeafNode)prevSibling).isHidden()) {
					AbstractElement prevCompoundedGrammarElement = prevSibling != null ? getCompoundedGrammarElement(prevSibling) : null;
					if (compoundedGrammarElement != prevCompoundedGrammarElement) {
						@NonNull SerializationSegment [] outerFormattingSegments = elementFormatter.getOuterFormattingSegments();
						for (@NonNull SerializationSegment formattingSegment : outerFormattingSegments) {
							if (formattingSegment.isValue()) {
								break;
							}
							formattingSegment.format(elementFormatter, serializationBuilder);
						}
					}
					break;
				}
			}
			//
			//	Inner formatting of the specific node.
			//
			@NonNull SerializationSegment[] innerFormattingSegments = elementFormatter.getInnerFormattingSegments();
			for (@NonNull SerializationSegment formattingSegment : innerFormattingSegments) {
				if (hasStarted || formattingSegment.isControl()) {
					formattingSegment.format(elementFormatter, serializationBuilder);
				}
			}
			//
			//	Different next grammar element requires outer tail formatting.
			//
			for (INode nextSibling = leafNode.getNextSibling(); (nextSibling == null) || (nextSibling instanceof ILeafNode); nextSibling = nextSibling.getNextSibling()) {
				if ((nextSibling == null) || !((ILeafNode)nextSibling).isHidden()) {
					AbstractElement nextCompoundedGrammarElement = nextSibling != null ? getCompoundedGrammarElement(nextSibling) : null;
					if (compoundedGrammarElement != nextCompoundedGrammarElement) {
						boolean isTail = false;
						@NonNull SerializationSegment [] outerFormattingSegments = elementFormatter.getOuterFormattingSegments();
						for (@NonNull SerializationSegment formattingSegment : outerFormattingSegments) {
							if (!isTail) {
								if (formattingSegment.isValue()) {
									isTail = true;;
								}
							}
							else {
								formattingSegment.format(elementFormatter, serializationBuilder);
							}
						}
					}
					break;
				}
			}
		}
	}

	protected void formatNode(@NonNull INode childNode, @Nullable ILeafNode endNodeOrEOF) {
		if (childNode instanceof ICompositeNode) {
			formatCompositeNode((ICompositeNode)childNode, endNodeOrEOF);
		}
		else {
			if (!((ILeafNode)childNode).isHidden()) {
				formatLeafNode((ILeafNode)childNode);
			}
		}
	}

	/**
	 * Return the EList to which compositeNode assigns if compoundedGrammarElement is an is-many feature assignment. Else null.
	 */
	protected @Nullable EList<?> getAssignedCollection(@NonNull ICompositeNode compositeNode, @NonNull AbstractElement compoundedGrammarElement) {
		if (!(compoundedGrammarElement instanceof Assignment)) {
			return null;
		}
		Assignment assignment = (Assignment)compoundedGrammarElement;
		EStructuralFeature eStructuralFeature = SerializationUtils.getEStructuralFeature(assignment);
		if (!eStructuralFeature.isMany()) {
			return null;
		}
	/*	EClass eFeatureClass = eStructuralFeature.getEContainingClass();
		StringBuilder s = new StringBuilder();
		s.append(eFeatureClass.getName() + "::" + eStructuralFeature.getName()); // + " " + assignment.getFeature() + " " + assignment.getTerminal());
		for (INode iNode = compositeNode; iNode != null; iNode = iNode.getParent()) {
			EObject eObject = null;
			if (iNode instanceof CompositeNodeWithSemanticElement) {
				eObject = ((CompositeNodeWithSemanticElement)iNode).getSemanticElement();
			}
			s.append("\n\t" + iNode.getClass().getSimpleName() + " -> " + NameUtil.debugSimpleName(eObject));
		}
		System.out.println(s.toString()); */
		EObject firstSemanticElement = null;
		EObject secondSemanticElement = null;
		for (INode iNode = compositeNode; iNode != null; iNode = iNode.getParent()) {
			if (iNode instanceof CompositeNodeWithSemanticElement) {
				CompositeNodeWithSemanticElement compositeNodeWithSemanticElement = (CompositeNodeWithSemanticElement)iNode;
				if (firstSemanticElement == null) {
					firstSemanticElement = compositeNodeWithSemanticElement.getSemanticElement();
				}
				else {
					secondSemanticElement = compositeNodeWithSemanticElement.getSemanticElement();
					break;
				}
			}
		}
		EObject theSemanticElement;
		if (compositeNode instanceof CompositeNodeWithSemanticElement) {
			theSemanticElement = secondSemanticElement;
		}
		else if (compositeNode.getFirstChild() == null) {	// FIXME This is empirical - find a better way
			theSemanticElement = secondSemanticElement;
		}
		else {
			theSemanticElement = firstSemanticElement;
		}
		assert theSemanticElement != null;
		return (EList<?>)theSemanticElement.eGet(eStructuralFeature);
	}

	/**
	 * Return the grammar element for node whose container is a CompoundElement; i.e. ascend the terminals of an assignment to return thr assignment.
	 */
	protected @NonNull AbstractElement getCompoundedGrammarElement(@NonNull INode node) {
		EObject grammarElement = node.getGrammarElement();
		for (EObject eContainer = grammarElement.eContainer(); (eContainer instanceof AbstractElement) && !(eContainer instanceof CompoundElement); eContainer = grammarElement.eContainer()) {
			grammarElement = eContainer;
		}
		return grammarElement instanceof AbstractElement ? (AbstractElement)grammarElement : SerializationUtils.getAlternatives(((AbstractRule)grammarElement));
	}

	private boolean hasEnded(@NonNull INode node) {
		int endOffset = node.getEndOffset();
		return endOffset >= selectEnd;
	}

	private boolean hasStarted(@NonNull INode node) {
		int offset = node.getOffset();
		return offset >= selectStart;
	}
}
