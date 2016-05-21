/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInvocationDelegate;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.Query;
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 * An implementation of an operation-invocation delegate for OCL body
 * expressions.
 */
public class OCLInvocationDelegate extends BasicInvocationDelegate
{
	protected final OCLDelegateDomain delegateDomain;
	private Operation operation;
	private ExpressionInOCL query;

	/**
	 * Initializes me with my operation.
	 * 
	 * @param operation
	 *            the operation that I handle
	 */
	public OCLInvocationDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EOperation operation) {
		super(operation);
		this.delegateDomain = delegateDomain;
	}

	@Override
	public Object dynamicInvoke(InternalEObject target, EList<?> arguments) throws InvocationTargetException {
		try {
			OCL ocl = delegateDomain.getOCL();
			MetamodelManager metamodelManager = ocl.getMetamodelManager();
			ExpressionInOCL query2 = query;
			if (query2 == null) {
				Operation operation2 = operation;
				NamedElement namedElement = delegateDomain.getPivot(NamedElement.class, ClassUtil.nonNullEMF(eOperation));
				if (namedElement instanceof Operation) {
					operation2 = operation = (Operation) namedElement;
					query2 = query = InvocationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, operation2);
					InvocationBehavior.INSTANCE.validate(operation2);
				}
				else if (namedElement instanceof Constraint) {
					Constraint constraint = (Constraint)namedElement;
					query2 = query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, constraint);
					ValidationBehavior.INSTANCE.validate(constraint);
				}
				else if (namedElement != null) {
					throw new OCLDelegateException(new SemanticException("Unsupported InvocationDelegate for a " + namedElement.eClass().getName())) ;
				}
				else {
					throw new OCLDelegateException(new SemanticException("Unsupported InvocationDelegate for a null")) ;
				}
			}
			return evaluate(ocl, query2, target, arguments);
		}
		catch (EvaluationException e) {
			throw new OCLDelegateException(new EvaluationException(e, PivotMessagesInternal.EvaluationResultIsInvalid_ERROR_, operation));
		}
	}

	protected Object evaluate(@NonNull OCL ocl, @NonNull ExpressionInOCL query2, InternalEObject target, List<?> arguments) {
		IdResolver idResolver = ocl.getIdResolver();
		Query query = ocl.createQuery(query2);
		EvaluationEnvironment env = query.getEvaluationEnvironment(target);
		Object object = target;
		Object value = idResolver.boxedValueOf(target);
		env.add(ClassUtil.nonNullModel( query2.getOwnedContext()), value);
		List<Variable> parms =  query2.getOwnedParameters();
		if (!parms.isEmpty()) {
			// bind arguments to parameter names
			for (int i = 0; i < parms.size(); i++) {
				object = arguments.get(i);
				value = idResolver.boxedValueOf(object);
				env.add(ClassUtil.nonNullModel(parms.get(i)), value);
			}
		}
		Object ecoreResult = query.evaluateEcore(eOperation.getEType().getInstanceClass(), target);
		return ecoreResult;
	}

	public @NonNull Operation getOperation() {
		Operation operation2 = operation;
		if (operation2 == null) {
			NamedElement pivot = delegateDomain.getPivot(NamedElement.class, ClassUtil.nonNullEMF(eOperation));
			if (pivot instanceof Operation) {
				operation2 = operation = (Operation) pivot;
			}
			if (operation2 == null) {
				throw new OCLDelegateException(new SemanticException("No pivot property for " + eOperation)) ;
			}
		}
		return operation2;
	}

	public @NonNull ExpressionInOCL getQueryOrThrow(@NonNull MetamodelManager metamodelManager, @NonNull Constraint constraint) {
		LanguageExpression specification = constraint.getOwnedSpecification();
		if (specification == null) {
			throw new OCLDelegateException(new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, constraint.getContext(), PivotConstantsInternal.BODY_EXPRESSION_ROLE));
		}
		try {
			return metamodelManager.parseSpecification(specification);
		} catch (ParserException e) {
			throw new OCLDelegateException(e);
		}
	}
	
	@Override
	public String toString() {
		if (operation != null) {
			return "<" + delegateDomain.getURI() + ":invocation> " + operation; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			String name = eOperation.getEContainingClass().getEPackage().getName()
			+ "::" + eOperation.getEContainingClass().getName()
			+ "." + eOperation.getName();
			return "<" + delegateDomain.getURI() + ":invocation> " + name; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
