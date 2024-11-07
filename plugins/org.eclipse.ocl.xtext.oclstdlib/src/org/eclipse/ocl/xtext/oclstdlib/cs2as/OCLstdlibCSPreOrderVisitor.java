/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.cs2as;

import java.util.List;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaClassCS;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaImplementationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibClassCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibCoercionCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibIterationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibOperationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibPropertyCS;
import org.eclipse.ocl.xtext.oclstdlibcs.MetaclassNameCS;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.PrecedenceCS;
import org.eclipse.ocl.xtext.oclstdlibcs.util.AbstractOCLstdlibCSPreOrderVisitor;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class OCLstdlibCSPreOrderVisitor extends AbstractOCLstdlibCSPreOrderVisitor
{
	protected final @NonNull OCLstdlibCS2AS converter;

	public OCLstdlibCSPreOrderVisitor(@NonNull CS2ASConversion context) {
		super(context);
		this.converter = (OCLstdlibCS2AS)context.getConverter();
	}

	protected @Nullable String resolveJavaClassCS(@NonNull JavaImplementationCS csJavaImplementation) {
		JavaClassCS csJavaClass;
		String text = null;
		List<INode> featureNodes = NodeModelUtils.findNodesForFeature(csJavaImplementation, OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);
		if ((featureNodes != null) && (featureNodes.size() > 0)) {			// If Xtext has parsed a reference
			INode node = featureNodes.get(0);
			text = NodeModelUtils.getTokenText(node).replace("'", "");
		}
		else {
			csJavaClass = (JavaClassCS)csJavaImplementation.eGet(OCLstdlibCSPackage.Literals.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION, false);
			if (csJavaClass != null) {
				if (csJavaClass.eIsProxy()) {								// If CS XMI load has loaded an ocl:#xyzzy reference
					text = ((InternalEObject)csJavaClass).eProxyURI().fragment();
				}
				else {														// If redundantly reloading
					text = csJavaClass.getName();
				}
			}
		}
		if (text != null) {
			csJavaClass = converter.getJavaClassCS(text);
			csJavaImplementation.setImplementation(csJavaClass);
		}
		return text;
	}

	protected @Nullable Precedence resolvePrecedence(@NonNull LibOperationCS csOperation) {
		String text = null;
		List<INode> featureNodes = NodeModelUtils.findNodesForFeature(csOperation, OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE);
		if ((featureNodes != null) && (featureNodes.size() > 0)) {			// If Xtext has parsed a reference
			INode node = featureNodes.get(0);
			text = NodeModelUtils.getTokenText(node).replace("'", "");
		}
		else {
			Precedence asPrecedence = (Precedence)csOperation.eGet(OCLstdlibCSPackage.Literals.LIB_OPERATION_CS__PRECEDENCE, false);
			if (asPrecedence != null) {
				if (asPrecedence.eIsProxy()) {								// If CS XMI load has loaded an ocl:#xyzzy reference
					text = ((InternalEObject)asPrecedence).eProxyURI().fragment();
				}
			//	else {														// If redundantly reloading
			//		text = asPrecedence.getName();
			//	}
			}
		}
		Precedence asPrecedence = text != null ? converter.getPrecedence(text) : null;
		csOperation.setPrecedence(asPrecedence);
		return asPrecedence;
	}

	@Override
	public Continuation<?> visitLibClassCS(@NonNull LibClassCS csClass) {
		org.eclipse.ocl.pivot.Class pivotElement = PivotUtil.getPivot(org.eclipse.ocl.pivot.Class.class, csClass);
		if (pivotElement != null) {
			/*pivotElement.setInstanceClassName(*/resolveJavaClassCS(csClass)/*)*/;
		}
		return super.visitLibClassCS(csClass);
	}

	@Override
	public Continuation<?> visitLibCoercionCS(@NonNull LibCoercionCS csCoercion) {
		Operation pivotCoercion = PivotUtil.getPivot(Operation.class, csCoercion);
		if (pivotCoercion != null) {
			pivotCoercion.setImplementationClass(resolveJavaClassCS(csCoercion));
		}
		return super.visitLibCoercionCS(csCoercion);
	}

	@Override
	public Continuation<?> visitLibIterationCS(@NonNull LibIterationCS csIteration) {
		Iteration pivotIteration = PivotUtil.getPivot(Iteration.class, csIteration);
		if (pivotIteration != null) {
			pivotIteration.setImplementationClass(resolveJavaClassCS(csIteration));
		}
		return super.visitLibIterationCS(csIteration);
	}

	@Override
	public Continuation<?> visitLibOperationCS(@NonNull LibOperationCS csOperation) {
		Operation pivotElement = PivotUtil.getPivot(Operation.class, csOperation);
		if (pivotElement != null) {
			pivotElement.setPrecedence(resolvePrecedence(csOperation));
			pivotElement.setIsStatic(csOperation.isIsStatic());
			pivotElement.setImplementationClass(resolveJavaClassCS(csOperation));
		}
		return super.visitLibOperationCS(csOperation);
	}

	@Override
	public Continuation<?> visitLibPropertyCS(@NonNull LibPropertyCS csProperty) {
		Property pivotElement = PivotUtil.getPivot(Property.class, csProperty);
		if (pivotElement != null) {
			pivotElement.setIsStatic(csProperty.isIsStatic());
			pivotElement.setImplementationClass(resolveJavaClassCS(csProperty));
		}
		return super.visitLibPropertyCS(csProperty);
	}

	@Override /* FIXME Bug 548500 workaround */
	public @Nullable Continuation<?> visitMetaclassNameCS(@NonNull MetaclassNameCS csElement) {
		return null;
	}

	@Override
	public Continuation<?> visitPrecedenceCS(@NonNull PrecedenceCS csPrecedence) {
		return null;
	}
}
