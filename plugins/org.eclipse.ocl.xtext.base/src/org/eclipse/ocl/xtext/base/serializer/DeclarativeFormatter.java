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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
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
 * to drive a re-synthesis of the whitespace in an INode range of the user document. The re-synthesis traverses the INode tree to ensure that its
 * structure is preserved. ParserRules and comments are determined by the INodes.
 */
public class DeclarativeFormatter extends AbstractNodeModelFormatter
{
	enum Darkness {
		DARK,			// contains non-hidden content
		COMMENT,		// contains hidden comment
		WHITE			// contains hidden, non-comment whitespace
	}

	protected static class Position
	{
		private final int lineNumber;			// 0-based line number
		private final int columnNumber;			// 0-based column number
	//	private final int trailingTabs;
	//	private final int trailingSpaces;

		public Position(int lineNumber, int columnNumber) {
			this.lineNumber = lineNumber;
			this.columnNumber = columnNumber;
		}

		/**
		 * Return an update of this position by advancing over the whitespace of text from startIndex to endIndex.
		 */
		public @NonNull Position createPosition(@NonNull String text, int startIndex, int endIndex, boolean isWhite) {
		//	String debugText = text.substring(startIndex, endIndex);
			int lines = lineNumber;
			int columns = columnNumber;
			for (int index = startIndex; index < endIndex; ) {
				char c = text.charAt(index++);
				if (c == '\n') {
					lines++;
					columns = 0;
				}
				else if (c == '\r') {
				}
				else if (c == '\t') {
					columns = (columns + 4) & ~3;
				}
				else if ((c == ' ') || !isWhite) {
					columns++;
				}
				else {
					assert false;
				}
			}
			return new Position(lines, columns);
		}

		public int getColumnNumber() {
			return columnNumber;
		}

		public int getLineNumber() {
			return lineNumber;
		}

//		public int getTrailingSpaces() {
//			return trailingSpaces;
//		}

//		public int getTrailingTabs() {
//			return trailingTabs;
//		}

		@Override
		public @NonNull String toString() {
			return lineNumber + ":" + columnNumber;
		}
	}

	/**
	 * Boundary abstract the handling of a selection start/finish wrt the INode at the start/finish.
	 */
	protected static abstract class Boundary
	{
		protected final @NonNull DeclarativeFormatter formatter;
		protected final @NonNull String name;
		protected final int selectIndex;
		protected final @NonNull ILeafNode selectedNode;
		protected final int relativeSelectIndex;
		protected int selectedNodeReformattedEndIndex;

		protected Boundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			this.formatter = formatter;
			this.name = name;
			this.selectedNode = selectedNode;
			this.selectIndex = selectIndex;
			this.relativeSelectIndex = selectIndex - selectedNode.getOffset();
			assert selectedNode.getOffset() == selectedNode.getTotalOffset();
			assert selectedNode.getEndOffset() == selectedNode.getTotalEndOffset();
		}

		public abstract void debugPrintln(@NonNull String indent);

		protected void debugPrintlnIdent(@NonNull String indent) {
			System.out.println(indent + name + " @ " + selectIndex);
		}

		protected @NonNull String debugText(@Nullable INode node) {
			if (node != null) {
				return node.getTotalOffset() + "-" + node.getOffset() + " .. " +  + node.getEndOffset() + "-" + node.getTotalEndOffset() /*+ " " + NameUtil.debugSimpleName(node)*/ + " '" + Strings.convertToJavaString(node.getText()) + "'";
			}
			else {
				return "null";
			}
		}

		public int getExtendedEndIndex() {
			return selectedNode.getTotalEndOffset();
		}

		public int getExtendedStartIndex() {
			return selectedNode.getTotalOffset();
		}

		public @NonNull String getName() {
			return name;
		}

		/**
		 * Return the index of the selection start/end within the reformattedText.
		 */
		public abstract int getReformattedSelectIndex();

		public int getSelectIndex() {
			return selectIndex;
		}

		public @NonNull ILeafNode getSelectedNode() {
			return selectedNode;
		}

		@Override
		public @NonNull String toString() {
			return name + ":" + selectIndex + debugText(selectedNode);
		}

		/**
		 * Specify the index within the reformattedText at which the text for leafNode.getEndOffset() occurs.
		 */
		public void setLeafNodeAt(@NonNull ILeafNode leafNode, int endIndex) {
			if (leafNode == selectedNode) {
				selectedNodeReformattedEndIndex = endIndex;
			}
		}

		/**
		 * Specify the reformattedText that spans at least this node and requisite predecessors/successors.
		 */
		public void setReformattedText(@NonNull String reformattedText) {}
	}

	/**
	 * A CommentBoundary handles the case where a selection start/finish occurs within a hidden comment ILeafNode.
	 */
	protected static class CommentBoundary extends HiddenBoundary
	{
		private int reformattedSelectIndex = -1;

		public CommentBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			assert selectedNode.isHidden();
			assert (selectedNode.getOffset() <= selectIndex) && (selectIndex < selectedNode.getEndOffset());
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			super.debugPrintln(indent);
			System.out.println(indent + "\tselection: " + (selectIndex - relativeSelectIndex) + " .. " + selectIndex + " .. " + (selectIndex - relativeSelectIndex + selectedNode.getLength())
				+ " => " + (reformattedSelectIndex - relativeSelectIndex)+ " .. " + reformattedSelectIndex + " .. " + (reformattedSelectIndex - relativeSelectIndex + selectedNode.getLength()));
		}

		@Override
		public int getReformattedSelectIndex() {
			return reformattedSelectIndex;
		}

		@Override
		public void setReformattedText(@NonNull String newText) {
			for (int i = precedingNodeNewEndOffset; i < newText.length(); i++) {
				char c = newText.charAt(i);
				if (!Character.isWhitespace(c)) {
					reformattedSelectIndex = i + relativeSelectIndex;
					return;
				}
			}
			assert false;
		}
	}

	/**
	 * A DarkBoundary handles the case where a selection start/finish occurs within a non-hidden ILeafNode.
	 */
	protected static class DarkBoundary extends Boundary
	{
		public DarkBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			assert !selectedNode.isHidden();
			assert (selectedNode.getOffset() <= selectIndex) && (selectIndex < selectedNode.getEndOffset());
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			debugPrintlnIdent(indent);
			System.out.println(indent + "\tselectedNode: " + debugText(selectedNode) + " => " + (selectedNodeReformattedEndIndex - selectedNode.getLength()) + " .. " + selectedNodeReformattedEndIndex);
		}

		@Override
		public int getReformattedSelectIndex() {
			return selectedNodeReformattedEndIndex + selectIndex - selectedNode.getEndOffset();
		}
	}

	/**
	 * EndBoundary handles the special case of a selection that finishes at the end of text for which
	 * there is no associated leaf node. The preceding node is therefore used as the selected node.
	 */
	protected static class EndBoundary extends Boundary
	{
		private @Nullable String reformattedText = null;

		public EndBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			assert selectIndex == selectedNode.getTotalEndOffset();
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			debugPrintlnIdent(indent);
			if (reformattedText != null) {
				System.out.println(indent + "\tselectedNode: " + debugText(selectedNode) + " => '" + Strings.convertToJavaString(reformattedText) + "'");
			}
			else {
				System.out.println(indent + "\tselectedNode: " + debugText(selectedNode));
			}
		}

		@Override
		public int getReformattedSelectIndex() {
			assert reformattedText != null;
			return reformattedText.length();
		}

		@Override
		public void setReformattedText(@NonNull String text) {
			this.reformattedText = text;
		}
	}

	/**
	 * A HiddenBoundary handles the case where a selection start/finish occurs within a hidden ILeafNode, which might be a comment.
	 */
	protected static abstract class HiddenBoundary extends Boundary
	{
		protected final @Nullable ILeafNode precedingNode;
		protected final @Nullable ILeafNode followingNode;
		protected int precedingNodeNewEndOffset;
		protected int followingNodeNewOffset;

		protected HiddenBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			assert selectedNode.isHidden();
			assert (selectedNode.getOffset() <= selectIndex) && (selectIndex < selectedNode.getEndOffset());
			this.precedingNode = previousDarkNode(selectedNode);
			this.followingNode = nextDarkNode(selectedNode);
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			debugPrintlnIdent(indent);
			debugPrintlnPrecedingNode(indent);
			debugPrintlnSelectedNode(indent);
			debugPrintlnFollowingNode(indent);
		}

		protected void debugPrintlnFollowingNode(@NonNull String indent) {
			ILeafNode followingNode2 = followingNode;
			if (followingNode2 != null) {
				System.out.println(indent + "\tfollowingNode: " + debugText(followingNode2) + " => " + followingNodeNewOffset + " .. " + (followingNodeNewOffset + followingNode2.getLength()));
			}
			else {
				System.out.println(indent + "\tfollowingNode: null");
			}
		}

		protected void debugPrintlnPrecedingNode(@NonNull String indent) {
			ILeafNode precedingNode2 = precedingNode;
			if (precedingNode2 != null) {
				System.out.println(indent + "\tprecedingNode: " + debugText(precedingNode2) + " => " + (precedingNodeNewEndOffset - precedingNode2.getLength()) + " .. " + precedingNodeNewEndOffset);
			}
			else {
				System.out.println(indent + "\tprecedingNode: null");
			}
		}

		protected void debugPrintlnSelectedNode(@NonNull String indent) {
			System.out.println(indent + "\tselectedNode: " + debugText(selectedNode));
		}

		@Override
		public int getExtendedEndIndex() {
			return followingNode != null ? followingNode.getTotalEndOffset() : selectedNode.getTotalEndOffset();
		}

		@Override
		public int getExtendedStartIndex() {
			return precedingNode != null ? precedingNode.getTotalOffset() : 0;
		}

		@Override
		public void setLeafNodeAt(@NonNull ILeafNode leafNode, int index) {
			super.setLeafNodeAt(leafNode, index);
			if (leafNode == precedingNode) {
				precedingNodeNewEndOffset = index;
			}
			if (leafNode == followingNode) {
				followingNodeNewOffset = index - leafNode.getLength();
			}
		}
	}

	/**
	 * A WhiteBoundary handles the case where a selection start/finish occurs within a hidden non-comment ILeafNode.
	 */
	protected static abstract class WhiteBoundary extends HiddenBoundary
	{
		protected String newWhitespace;

		protected WhiteBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
		}

		@Override
		protected void debugPrintlnSelectedNode(@NonNull String indent) {
			if (newWhitespace != null) {
				System.out.println(indent + "\tselectedNode: " + debugText(selectedNode) + " => '" + Strings.convertToJavaString(newWhitespace) + "'");
			}
			else {
				System.out.println(indent + "\tselectedNode: " + debugText(selectedNode));
			}
		}
	}

	protected static class WhiteEndBoundary extends WhiteBoundary
	{
		private @NonNull Position prePosition;			// Absolute line/column position of start of selectedNode
		private @NonNull Position selectedPosition;		// Absolute line/column position of start/end boundary within selectedNode
		private @NonNull Position postPosition;			// Absolute line/column position of end of selectedNode
		private int reformattedSelectIndex;

		public WhiteEndBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			String rootText = formatter.getRootText();
			int selectedOffset = selectedNode.getOffset();
			this.prePosition = new Position(0, 0).createPosition(rootText, 0, selectedOffset, false);
			this.selectedPosition = prePosition.createPosition(rootText, selectedOffset, selectIndex, true);
			int nextNodeOffset = followingNode != null ? followingNode.getOffset() : rootText.length();
			this.postPosition = selectedPosition.createPosition(rootText, selectIndex, nextNodeOffset, true);
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			super.debugPrintln(indent);
			System.out.println(indent + "\told pre: " + prePosition + " at: " + selectedPosition + " post: " + postPosition);
		}

		@Override
		public int getReformattedSelectIndex() {
			return reformattedSelectIndex;
		}

		@Override
		public void setReformattedText(@NonNull String newText) {
			int newLength = newText.length();
			if ((followingNodeNewOffset != 0) && (followingNodeNewOffset < newLength)) {
				newLength = followingNodeNewOffset;
			}
			this.newWhitespace = newText.substring(precedingNodeNewEndOffset, newLength);
			Position newPostSelection = prePosition.createPosition(newWhitespace, 0, newWhitespace.length(), true);
			int targetLineNumber = newPostSelection.getLineNumber();
			int targetColumnNumber = newPostSelection.getColumnNumber();
			int mandatoryLines = postPosition.getLineNumber() - selectedPosition.getLineNumber();
			int mandatoryColumns = mandatoryLines > 0 ? postPosition.getColumnNumber() : (postPosition.getColumnNumber() - selectedPosition.getColumnNumber());
			int candidateLineNumber = prePosition.getLineNumber();
			int candidateColumnNumber = prePosition.getColumnNumber();
			int candidateIndex = 0;
			for ( ; candidateIndex < newWhitespace.length(); candidateIndex++) {
				int postCandidateLineNumber = candidateLineNumber + mandatoryLines;
				int postCandidateColumnNumber = mandatoryLines > 0 ? mandatoryColumns : (candidateColumnNumber + mandatoryColumns);
				if (postCandidateLineNumber > targetLineNumber) {
					break;
				}
				else if (postCandidateLineNumber == targetLineNumber) {
					if (postCandidateColumnNumber >= targetColumnNumber) {
						break;
					}
				}
				char c = newWhitespace.charAt(candidateIndex);
				if (c == '\n') {
					candidateLineNumber++;
					candidateColumnNumber = 0;
				}
				else if (c == '\r') {
				}
				else if (c == '\t') {
					candidateColumnNumber = (candidateColumnNumber+4) & ~3;
				}
				else if (c == ' '){
					candidateColumnNumber++;
				}
				else {
					assert false;
				}
				postCandidateLineNumber = candidateLineNumber + mandatoryLines;
				if (postCandidateLineNumber > targetLineNumber) {	// Line overshoot is not acceptable.
					break;
				}
			}
			reformattedSelectIndex = precedingNodeNewEndOffset + candidateIndex;
		}
	}

	protected static class WhiteStartBoundary extends WhiteBoundary
	{
		protected final @NonNull String oldWhitespace;
		private @NonNull Position prePosition;			// Absolute line/column position of start of selectedNode
		private @NonNull Position selectedPosition;		// Absolute line/column position of start/end boundary within selectedNode
		private int reformattedSelectIndex;

		public WhiteStartBoundary(@NonNull DeclarativeFormatter formatter, @NonNull String name, @NonNull ILeafNode selectedNode, int selectIndex) {
			super(formatter, name, selectedNode, selectIndex);
			String rootText = formatter.getRootText();
			ILeafNode followingNode2 = followingNode;
			if (followingNode2 != null) {
				oldWhitespace = rootText.substring(selectedNode.getOffset(), followingNode2.getOffset());			// Include following multi-whitespace
			}
			else {
				oldWhitespace = ClassUtil.nonNull(selectedNode.getText());
			}
			int selectedOffset = selectedNode.getOffset();
			this.prePosition = new Position(0, 0).createPosition(rootText, 0, selectedOffset, false);
			this.selectedPosition = prePosition.createPosition(rootText, selectedOffset, selectIndex, true);
		}

		@Override
		public void debugPrintln(@NonNull String indent) {
			super.debugPrintln(indent);
			System.out.println(indent + "\told columns pre: " + prePosition + " at: " + selectedPosition);
		}

		protected @NonNull List<@NonNull Integer> getLastFirstNewLineIndexes(@NonNull String whitespace) {
			List<@NonNull Integer> oldNewLines = new ArrayList<>();
			for (int i = 0; i < whitespace.length(); i++) {
				char c = whitespace.charAt(i);
				if (c == '\n') {
					oldNewLines.add(0, i);		// last first
				}
			}
			return oldNewLines;
		}

		@Override
		public int getReformattedSelectIndex() {
			return reformattedSelectIndex;
		}

		@Override
		public void setReformattedText(@NonNull String newText) {
			int newLength = newText.length();
			if ((followingNodeNewOffset != 0) && (followingNodeNewOffset < newLength)) {
				newLength = followingNodeNewOffset;
			}
			for (int i = precedingNodeNewEndOffset; i < newLength; i++) {
				char c = newText.charAt(i);
				if (!Character.isWhitespace(c)) {
					newLength = i;
					break;
				}
			}
			this.newWhitespace = newText.substring(precedingNodeNewEndOffset, newLength);
			Position newPostSelection = prePosition.createPosition(newWhitespace, 0, newWhitespace.length(), true);
			int targetLineNumber = newPostSelection.getLineNumber();
			int targetColumnNumber = newPostSelection.getColumnNumber();
			Position candidatePosition = selectedPosition;
			int candidateIndex = 0;
			int bestIndex = candidateIndex;
			int bestLineNumberOvershoot = Integer.MAX_VALUE;
			int bestColumnNumberOvershoot = Integer.MAX_VALUE;
			for ( ; candidateIndex < newWhitespace.length(); candidateIndex++) {
				Position consequentPosition = candidatePosition.createPosition(newWhitespace, candidateIndex,  newWhitespace.length(), true);
				int lineNumberOverShoot = consequentPosition.getLineNumber() - targetLineNumber;
				int columnNumberOverShoot = consequentPosition.getColumnNumber() - targetColumnNumber;
				boolean isBetter = false;
				if ((0 <= lineNumberOverShoot) && (lineNumberOverShoot < bestLineNumberOvershoot)) {
					isBetter = true;;
				}
				else if (lineNumberOverShoot == 0) {
					if ((0 <= columnNumberOverShoot) && (columnNumberOverShoot < bestColumnNumberOvershoot)) {
						isBetter = true;;
					}
				}
				if (isBetter) {
					bestIndex = candidateIndex;
					bestLineNumberOvershoot = lineNumberOverShoot;
					bestColumnNumberOvershoot = columnNumberOverShoot;
				}
			}
			reformattedSelectIndex = precedingNodeNewEndOffset + bestIndex;
		}
	}

/*	private static @NonNull List<@NonNull String> indents = new ArrayList<>(10);

	private static @NonNull String getIndent(int indent) {
		if (indents.size() <= indent) {
			for (int i = indents.size(); i <= indent; i++) {
				indents.add(i == 0 ? "" : (indents.get(i-1) + "  "));
			}
		}
		return indents.get(indent);
	} */

	/**
	 * Return the darkness of node.
	 */
	private static @NonNull Darkness getDarkness(@NonNull INode node) {
		if (!(node instanceof ILeafNode)) {
			return Darkness.DARK;
		}
		if (!((ILeafNode)node).isHidden()) {
			return Darkness.DARK;
		}
		String text = node.getText();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!Character.isWhitespace(c)) {
				return Darkness.COMMENT;
			}
		}
		return Darkness.WHITE;
	}

	@SuppressWarnings("null")
	protected static @Nullable ILeafNode nextDarkNode(@NonNull INode node) {
		@SuppressWarnings("unused") String text = node.getText();
		while ((node != null) && (getDarkness(node) != Darkness.DARK)) {
			while ((node != null) && !node.hasNextSibling()) {
				node = node.getParent();
				text = node != null ? node.getText() : null;
			}
			if (node != null) {
				node = node.getNextSibling();
				text = node != null ? node.getText() : null;
			}
			while ((node instanceof ICompositeNode) && ((ICompositeNode)node).hasChildren()) {
				node = ((ICompositeNode)node).getFirstChild();
				text = node != null ? node.getText() : null;
			}
		}
		return (ILeafNode)node;
	}

	@SuppressWarnings("null")
	protected static @Nullable ILeafNode previousDarkNode(@NonNull INode node) {
		while ((node != null) && (!(node instanceof ILeafNode) || ((ILeafNode)node).isHidden())) {
			while ((node != null) && !node.hasPreviousSibling()) {
				node = node.getParent();
			}
			if (node != null) {
				node = node.getPreviousSibling();
			}
			while ((node instanceof ICompositeNode) && ((ICompositeNode)node).hasChildren()) {
				node = ((ICompositeNode)node).getLastChild();
			}
		}
		return (ILeafNode)node;
	}

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

//	private ICompositeNode rootNode;		// Root node for the whole text.
//	private int selectStart;				// Index within the rootText of the start of the selected range.
	private int selectEnd;					// Index within the rootText of the end of the selected range.
	protected String rootText;				// rootNode.getText();
	private int extendedSelectStart;		// Index within the rootText of the start of the extended selected range.
	private int extendedSelectEnd;			// Index within the rootText of the end of the extended selected range.
	private Boundary start;
	private Boundary end;

	private @NonNull Boundary createEndBoundary(@NonNull ICompositeNode rootNode, int selectIndex) {
		assert selectIndex <= rootNode.getTotalEndOffset();
		if (selectIndex == rootNode.getTotalEndOffset()) {
			ILeafNode selectedNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, selectIndex - 1);
			assert selectedNode != null;
			return new EndBoundary(this, "end", selectedNode, selectIndex);
		}
		ILeafNode selectedNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, selectIndex < rootNode.getTotalEndOffset() ? selectIndex : (rootNode.getTotalEndOffset() - 1));
		assert selectedNode != null;
		Darkness darkness = getDarkness(selectedNode);
		if (darkness == Darkness.DARK) {
			return new DarkBoundary(this, "dark-end", selectedNode, selectIndex);
		}
		else if (darkness == Darkness.COMMENT) {
			return new CommentBoundary(this, "comment-end", selectedNode, selectIndex);
		}
		else {
			return new WhiteEndBoundary(this, "white-end", selectedNode, selectIndex);
		}
	}

	private @NonNull Boundary createStartBoundary(@NonNull ICompositeNode rootNode, int selectIndex) {
		assert selectIndex >= 0;
		ILeafNode selectedNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, selectIndex);
		assert selectedNode != null;
		Darkness darkness = getDarkness(selectedNode);
		if (darkness == Darkness.DARK) {
			return new DarkBoundary(this, "dark-start", selectedNode, selectIndex);
		}
		else if (darkness == Darkness.COMMENT) {
			return new CommentBoundary(this, "comment-start", selectedNode, selectIndex);
		}
		else {
			return new WhiteStartBoundary(this, "white-start", selectedNode, selectIndex);
		}
	}

/*	private @Nullable String darkText(@NonNull String text) {
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
	} */

	@Override
	public IFormattedRegion format(ICompositeNode rootNode, final int selectStart, final int selectLength) {
		assert rootNode != null;
		serializationBuilder.resetBuilder();
//		this.rootNode = rootNode;
//		this.selectStart = selectStart;
		this.selectEnd = selectStart + selectLength;
		this.rootText = rootNode.getText();
//		int rootTextLength = rootText.length();


//		int selectEnd = selectStart+selectLength;
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
		String selectedText = rootText.substring(selectStart, selectEnd);
		System.out.println("format\tselectedText: " + selectedText.length() + " chars at " + selectStart + "-" + selectEnd + ": '" + Strings.convertToJavaString(selectedText) + "'");


		start = createStartBoundary(rootNode, selectStart);
		end = createEndBoundary(rootNode, selectEnd);

	//	int oldStart = start.getSelectedNode().getTotalOffset();
	//	int oldEnd = end.getSelectedNode().getTotalEndOffset();
	//	String oldTotalText = rootText.substring(oldStart, oldEnd);
	//	System.out.println("\toldTotalText: " + oldTotalText.length() + " chars at " + oldStart + "-" + oldEnd + ": '" + Strings.convertToJavaString(oldTotalText) + "'");


	/*	String prefixText = rootText.substring(oldStart, selectStart);
		String darkPrefixText = darkText(prefixText);
		int iStart = selectStart;
		for ( ; iStart >= oldStart; --iStart) {
			if (iStart == 0) {			// Emulate a new-line at start of file
				break;
			}
			char c = rootText.charAt(iStart-1);
			if (c == '\n') {
				break;
			}
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		String whitePrefixText = iStart < selectStart ? rootText.substring(iStart, selectStart) : null; */




	/*	String suffixText = rootText.substring(selectEnd, oldEnd);
		String darkSuffixText = darkText(suffixText);
		int iEnd = selectEnd;
		for ( ; iEnd <= oldEnd; iEnd++) {
			if ((iEnd < 0) || (iEnd >= rootText.length())) {			// Emulate a new-line at start/end of file
				break;
			}
			char c = rootText.charAt(iEnd);
			if (c == '\n') {
				break;
			}
			if (!Character.isWhitespace(c)) {
				break;
			}
		}
		String whiteSuffixText = iEnd > selectEnd ? rootText.substring(selectEnd, iEnd) : null; */
	//	System.out.println("\tdarkPrefixText: " + (darkPrefixText != null ? "'" + Strings.convertToJavaString(darkPrefixText) + "'" : "null"));
	//	System.out.println("\twhitePrefixText: " + (whitePrefixText != null ? "'" + Strings.convertToJavaString(whitePrefixText) + "'" : "null"));
	//	System.out.println("\tdarkSuffixText: " + (darkSuffixText != null ? "'" + Strings.convertToJavaString(darkSuffixText) + "'" : "null"));
	//	System.out.println("\twhiteSuffixText: " + (whiteSuffixText != null ? "'" + Strings.convertToJavaString(whiteSuffixText) + "'" : "null"));



//		this.darkStartNode = start.getSelectedNode();
//		if (!isDark(darkStartNode)) {
//			this.darkStartNode = null;
//			this.darkStartNode1 = previousDarkNode(start.getSelectedNode());
//			this.darkStartNode2 = nextDarkNode(start.getSelectedNode());
//		}
//		System.out.println("\tdarkStartNode: " + debugText(darkStartNode));
//		System.out.println("\tdarkStartNode1: " + debugText(darkStartNode1));
//		System.out.println("\tdarkStartNode2: " + debugText(darkStartNode2));
//		this.selectStartToDarkStartEnd = darkStartNode.getEndOffset() - selectStart;
	//	System.out.println("format darkDarkStart: " + darkStartEndIndex + " " + debugText(darkStartNode));

//		String optionalWhiteStartText = null;
//		String requiredWhiteStartText = null;
/*		int darkStartStart = darkStartNode.getOffset();
		int selectStartToDarkStartStart = darkStartStart - selectStart;
		if (selectStartToDarkStartStart > 0) {


			int i = darkStartStart;
			while (--i >= 0) {
				char c = rootText.charAt(i);
				if ((c == '\n') || !Character.isWhitespace(c)) {
					break;
				}
			}
			optionalWhiteStartText = rootText.substring(i + 1, darkStartStart);
			System.out.println("\toptionalWhiteStartText: " + (i+1) + " '" + Strings.convertToJavaString(optionalWhiteStartText) + "'");
			int j = i+1;
			while (--j >= 0) {
				char c = rootText.charAt(j);
				if ((c == '\n') || !Character.isWhitespace(c)) {
					break;
				}
			}
			requiredWhiteStartText = rootText.substring(j+1, i+1);
			System.out.println("\trequiredWhiteStartText: " + (j+1) + " '" + Strings.convertToJavaString(requiredWhiteStartText) + "'");
		} */
//		System.out.println("\tselectedEndNode: " + debugText(end.getSelectedNode()));
//		this.darkEndNode = end.getSelectedNode();
//		if (!isDark(darkEndNode)) {
//			this.darkEndNode = null;
//			this.darkEndNode1 = previousDarkNode(end.getSelectedNode());
//			this.darkEndNode2 = nextDarkNode(end.getSelectedNode());
//		}
//		System.out.println("\tdarkEndNode: " + debugText(darkEndNode));
//		System.out.println("\tdarkEndNode1: " + debugText(darkEndNode1));
//		System.out.println("\tdarkEndNode2: " + debugText(darkEndNode2));
//		end.debugPrintln("\t");
//		this.selectEndToEndNodeEnd = darkEndNode.getEndOffset() - selectEnd;
//		System.out.println("\tdarkEndNode: " + darkEndEndIndex + " " + debugText(darkEndNode));
//		start.debugPrintln("\t");
//		end.debugPrintln("\t");

		extendedSelectStart = start.getExtendedStartIndex(); //Math.min(darkStartNode.getTotalOffset(), end.getSelectedNode().getTotalOffset());
		extendedSelectEnd = end.getExtendedEndIndex(); //Math.max(darkEndNode.getTotalEndOffset(), end.getSelectedNode().getTotalEndOffset());
		String extendedText = rootText.substring(extendedSelectStart, extendedSelectEnd);
		System.out.println("\textendedText: " + extendedText.length() + " chars at " + extendedSelectStart + "-" + extendedSelectEnd + ": '" + Strings.convertToJavaString(extendedText) + "'");




		formatNode(rootNode, 0);
		INode lastNode = getLastLeafNode(rootNode);
	//	assert lastNode != null;
		if ((lastNode != null) && isFormatting(lastNode) /*&& !lastNode.getText().endsWith("\n")*/ && rootText.endsWith("\n")) {
			serializationBuilder.append(SerializationBuilder.NEW_LINE);				// trailing new-line got lost - must make explicit
		}
		String reformattedText = serializationBuilder.toString();
		System.out.println("\treformattedText: " + reformattedText.length() + " chars of '" + Strings.convertToJavaString(reformattedText) + "'");

		start.setReformattedText(reformattedText);
		end.setReformattedText(reformattedText);

		start.debugPrintln("\t");
		end.debugPrintln("\t");
		//
		//	Condition the insertion site to follow a new-line else follow all whitespace.
		//
//		for (; oldStart < selectStart; oldStart++) {		// Step to first non-whitespace character
//			char c = rootText.charAt(oldStart);
//			if (!Character.isWhitespace(c)) {
//				break;
//			}
//		}
//		@SuppressWarnings("unused") String oldContextText = rootText.substring(oldStart, oldEnd);
//		System.out.println("\toldContextText: " + (precedingChar != 0 ? ("'" + Strings.convertToJavaString(String.valueOf(precedingChar)) + "'") : "«BOF»") + " then " + oldStart + "-" + oldEnd + ": '" + Strings.convertToJavaString(oldContextText) + "' then " + (followingChar != 0 ? ("'" + Strings.convertToJavaString(String.valueOf(followingChar)) + "'") : "«EOF»"));

//		System.out.println("\tdarkStartEndIndex: " + darkStartEndIndex);
//		System.out.println("\tdarkEndEndIndex: " + darkEndEndIndex);

		System.out.println("\tselect: " + selectStart + " .. " + selectEnd);
//		System.out.println("\tselectStartToDarkStartEnd: " + selectStartToDarkStartEnd);
//		System.out.println("\tselectEndToEndNodeEnd: " + selectEndToEndNodeEnd);

		int startIndex = start.getReformattedSelectIndex(); //darkStartEndIndex - selectStartToDarkStartEnd;
		int endIndex = end.getReformattedSelectIndex(); //darkEndEndIndex - selectEndToEndNodeEnd;
		System.out.println("\treformatted: " + startIndex + " .. " + endIndex);
		String newNewText = reformattedText.substring(startIndex, endIndex);
	/*	if (darkPrefixText != null) {
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
		} */
		System.out.println("\treplace: " + (selectEnd - selectStart) + " chars at " + selectStart + "-" + selectEnd + " by the " + newNewText.length() + " chars of '" + Strings.convertToJavaString(newNewText) + "'");
		return new FormattedRegion(selectStart, selectEnd - selectStart, newNewText);
	}

	protected void formatCompositeNode(final @NonNull ICompositeNode compositeNode, final int indent) {
		String text = compositeNode.getText();
		assert text != null;
		EObject semanticElement = compositeNode.getSemanticElement();
		assert semanticElement != null;
		AbstractElement compoundedGrammarElement = getCompoundedGrammarElement(compositeNode);
		EList<?> assignedCollection = getAssignedCollection(compositeNode, compoundedGrammarElement);
		UserElementFormatter elementFormatter = modelAnalysis.createUserElementFormatter(compositeNode, compoundedGrammarElement, semanticElement);
//		System.out.println(getIndent(indent) + "formatCompositeNode compositeNode: " + compositeNode.getTotalOffset() + "-" + compositeNode.getOffset() + " .. " +  + compositeNode.getEndOffset() + "-" + compositeNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(compositeNode) + " '" + Strings.convertToJavaString(text) + "'");
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
		INode firstChild = compositeNode.getFirstChild();
		if (firstChild != null) {
			if (getDarkness(firstChild) != Darkness.DARK) {
				firstChild = nextDarkNode(firstChild);
			}
			if (firstChild != null) {
				if ((assignedCollection == null) || (assignedCollection != prevAssignedCollection)) {
					boolean isFormatting = isFormatting(firstChild);
					@NonNull SerializationSegment [] outerFormattingSegments = elementFormatter.getOuterFormattingSegments();
					for (@NonNull SerializationSegment formattingSegment : outerFormattingSegments) {
						if (formattingSegment.isValue()) {
							break;
						}
						if (isFormatting || formattingSegment.isControl()) {					// PUSH/POP even when not started
							formattingSegment.format(elementFormatter, serializationBuilder);
						}
					}
				}
			}
		}
		boolean isFormatting = isFormatting(compositeNode);
		//
		//	Inner formatting of the specific node.
		//
		boolean hasProlog = false;
		for (@NonNull INode childNode : SerializationUtils.getChildren(compositeNode)) {
			if (isProlog(childNode)) {
				hasProlog = true;
				break;
			}
		}
		@NonNull SerializationSegment[] innerFormattingSegments = elementFormatter.getInnerFormattingSegments();
		for (@NonNull SerializationSegment formattingSegment : innerFormattingSegments) {
			if (formattingSegment.isValue()) {
				//	hasValue = true;
				for (@NonNull INode childNode : SerializationUtils.getChildren(compositeNode)) {
					if (isEpilog(childNode)) {
						break;
					}
					formatNode(childNode, indent + 1);
				//	int totalOffset = childNode.getTotalOffset();
				//	boolean hasEnded = selectEnd <= totalOffset;
				//	if (hasEnded) { //(childNode)) {
				//		break;
				//	}
				}
			}
			else if ((!hasProlog && isFormatting) || formattingSegment.isControl()) {		// only if >= VALUE
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
				else if (isFormatting || formattingSegment.isControl()) {
					formattingSegment.format(elementFormatter, serializationBuilder);
				}
			}
		}
	}

	protected void formatLeafNode(@NonNull ILeafNode leafNode, int indent) {
		String text = leafNode.getText();
		assert text != null;
//		System.out.println(getIndent(indent) + "formatLeafNode compositeNode: " + leafNode.getTotalOffset() + "-" + leafNode.getOffset() + " .. " +  + leafNode.getEndOffset() + "-" + leafNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(leafNode) + " '" + Strings.convertToJavaString(text) + "'");
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
			boolean isFormatting = isFormatting(leafNode);
			@NonNull SerializationSegment[] innerFormattingSegments = elementFormatter.getInnerFormattingSegments();
			for (@NonNull SerializationSegment formattingSegment : innerFormattingSegments) {
				if (isFormatting || formattingSegment.isControl()) {					// PUSH/POP even when not started
					formattingSegment.format(elementFormatter, serializationBuilder);
					if (formattingSegment.isValue()) {				// no point tracking the less stable hidden nodes
						int index = serializationBuilder.length();
						start.setLeafNodeAt(leafNode, index);
						end.setLeafNodeAt(leafNode, index);
					}
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

	protected void formatNode(@NonNull INode childNode, int indent) {
		if (childNode instanceof ICompositeNode) {
			formatCompositeNode((ICompositeNode)childNode, indent);
		}
		else if (!((ILeafNode)childNode).isHidden()) {
			formatLeafNode((ILeafNode)childNode, indent);
		}
		else {
//			String childNodeText = childNode.getText();
//			System.out.println(getIndent(indent) + "formatNode childNode: " + childNode.getTotalOffset() + "-" + childNode.getOffset() + " .. " +  + childNode.getEndOffset() + "-" + childNode.getTotalEndOffset() + " " + NameUtil.debugSimpleName(childNode) + " '" + Strings.convertToJavaString(childNodeText) + "'");
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

	private @Nullable ILeafNode getLastLeafNode(@NonNull ICompositeNode rootNode) {
		INode lastNode = rootNode;
		while ((lastNode instanceof ICompositeNode) && ((ICompositeNode)lastNode).hasChildren()) {
			lastNode = ((ICompositeNode)lastNode).getLastChild();
		}
		return lastNode instanceof ILeafNode ? (ILeafNode)lastNode : null;
	}

	public @NonNull String getRootText() {
		assert rootText != null;
		return rootText;
	}

	private boolean isEpilog(@NonNull INode node) {
	//	String text = node.getText();
		int totalOffset = node.getOffset();
	//	int totalSelectEndOffset = selectedEndNode.getTotalEndOffset();
	//	boolean isEpilog =  extendedSelectEnd < totalOffset;
	//	if (isEpilog) {
	//		getClass();		// XXX
	//	}
		return extendedSelectEnd < totalOffset;
	}

	/**
	 * Return true if node has an overlap with the selected range.
	 */
	private boolean isFormatting(@NonNull INode node) {
		return !isProlog(node) && !isEpilog(node);
	}

	private boolean isProlog(@NonNull INode node) {
	//	String text = node.getText();
		int totalEndOffset = node.getTotalEndOffset();
		return totalEndOffset <= extendedSelectStart;
	}
}
