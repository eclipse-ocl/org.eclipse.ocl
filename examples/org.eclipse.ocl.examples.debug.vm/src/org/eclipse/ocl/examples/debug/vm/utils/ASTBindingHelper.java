/*******************************************************************************
 * Copyright (c) 2014 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.vm.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.xtext.base.basecs.ModelElementCS;
import org.eclipse.ocl.examples.xtext.base.utilities.ElementUtil;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class ASTBindingHelper
{
	private static final class UnitLocation2 implements IModuleSourceInfo, LineNumberProvider		// FIXME Unify with UnitLocation/INode
	{
		private @Nullable ModelElementCS csElement;
		private @Nullable ICompositeNode node;
		
		private UnitLocation2(@NonNull ModelElementCS csElement) {
			this.csElement = csElement;
			if (csElement != null) {
				this.node = NodeModelUtils.getNode(csElement);
			}
		}
		
		@Override
		public int getLineCount() {
			ICompositeNode node2 = node;
			return node2 != null ? node2.getStartLine() : 0;
		}

		@Override
		public int getLineEnd(int lineNumber) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int getLineNumber(int offset) {
			ICompositeNode node2 = node;
			if (node2 != null) {
				int nodeOffset = node2.getOffset();
				if (offset == nodeOffset) {
					return node2.getStartLine();
				}
				ICompositeNode rootNode = node2.getRootNode();
				if (rootNode != null) {
					ILeafNode leafNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, offset);
					if (leafNode != null) {
						return leafNode.getStartLine();
					}
				}
			}
			return 0;
//			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull LineNumberProvider getLineNumberProvider() {
			return this;
		}

		@Override
		public @Nullable URI getSourceURI() {
			ModelElementCS csElement2 = csElement;
			return csElement2 != null ? csElement2.eResource().getURI() : null;
		}
	}


	public static int getEndPosition(@NonNull Element element) {
		ModelElementCS csElement = ElementUtil.getCsElement(element);
		if (csElement == null) {
			return 0;
		}
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		if (node == null) {
			return 0;
		}
		return ElementUtil.getEndOffset(node);
	}
	
//	private static int getNodeLength(@NonNull Element element) {
//		return getEndPosition(element) - getStartPosition(element);
//	}
	
	public static int getStartPosition(@NonNull Element element) {
		ModelElementCS csElement = ElementUtil.getCsElement(element);
		if (csElement == null) {
			return 0;
		}
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		if (node == null) {
			return 0;
		}
		return node.getOffset();
	}

	public static IModuleSourceInfo getModuleSourceBinding(@NonNull Element asElement) {
		ModelElementCS csElement = ElementUtil.getCsElement(asElement);
		return  csElement != null ? new UnitLocation2(csElement) : null;
	}
}
