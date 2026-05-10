/*******************************************************************************
 * Copyright (c) 2014, 2018 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.utilities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class ValidityUtils
{
	public static @NonNull List<@NonNull ? extends AbstractNode> getChildren(@NonNull AbstractNode node) {
		return ClassUtil.nullFree(node.getChildren());
	}

	public static @NonNull List<@NonNull ConstrainingNode> getChildren(@NonNull ConstrainingNode node) {
		return ClassUtil.nullFree(node.getChildren());
	}

	public static @NonNull List<@NonNull ValidatableNode> getChildren(@NonNull ValidatableNode node) {
		return ClassUtil.nullFree(node.getChildren());
	}

	public static Iterable<@NonNull RootConstrainingNode> getConstrainingNodes(@NonNull RootNode rootNode) {
		return ClassUtil.nullFree(rootNode.getConstrainingNodes());
	}

	/**
	 * Return all enabled result constraining nodes at and below constrainingNode.
	 */
	public static @NonNull List<ResultConstrainingNode> getEnabledResultConstrainingNodes(@NonNull ConstrainingNode constrainingNode) {
		List<ResultConstrainingNode> resultConstrainingNodes = new ArrayList<ResultConstrainingNode>();
		if (constrainingNode instanceof ResultConstrainingNode) {
			ResultConstrainingNode resultConstrainingNode = (ResultConstrainingNode) constrainingNode;
			if (resultConstrainingNode.isEnabled()) {
				resultConstrainingNodes.add(resultConstrainingNode);
			}
		}
		for (TreeIterator<@NonNull EObject> tit = constrainingNode.eAllContents(); tit.hasNext(); ) {
			Object eObject = tit.next();
			if (eObject instanceof ResultConstrainingNode) {
				ResultConstrainingNode resultConstrainingNode = (ResultConstrainingNode) eObject;
				if (resultConstrainingNode.isEnabled()) {
					resultConstrainingNodes.add(resultConstrainingNode);
				}
			}
		}
		return resultConstrainingNodes;
	}

	/**
	 * Return all enabled result validatable nodes at and below validatableNode.
	 */
	public static @NonNull List<ResultValidatableNode> getEnabledResultValidatableNodes(@NonNull ValidatableNode validatableNode) {
		List<ResultValidatableNode> resultValidatableNodes = new ArrayList<ResultValidatableNode>();
		if (validatableNode instanceof ResultValidatableNode) {
			ResultValidatableNode resultValidatableNode = (ResultValidatableNode) validatableNode;
			if (resultValidatableNode.isEnabled()) {
				resultValidatableNodes.add(resultValidatableNode);
			}
		}
		for (TreeIterator<@NonNull EObject> tit = validatableNode.eAllContents(); tit.hasNext(); ) {
			Object eObject = tit.next();
			if (eObject instanceof ResultValidatableNode) {
				ResultValidatableNode resultValidatableNode = (ResultValidatableNode) eObject;
				if (resultValidatableNode.isEnabled()) {
					resultValidatableNodes.add(resultValidatableNode);
				}
			}
		}
		return resultValidatableNodes;
	}

	public static @NonNull ResultConstrainingNode getResultConstrainingNode(@NonNull ResultValidatableNode node) {
		return ClassUtil.nonNullState(node.getResultConstrainingNode());
	}

	public static @NonNull List<@NonNull RootValidatableNode> getValidatableNodes(@NonNull RootNode rootNode) {
		return ClassUtil.nullFree(rootNode.getValidatableNodes());
	}

	public static @NonNull RootConstrainingNode getRootConstrainingNode(@NonNull ConstrainingNode constrainingNode) {
		for (ConstrainingNode node = constrainingNode; node != null; node = node.getParent()) {
			if (node instanceof RootConstrainingNode)
				return (RootConstrainingNode)node;
		}
		throw new IllegalStateException();
	}
}
