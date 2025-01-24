/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PathRole;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qualified Element Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getOwnedPathElements <em>Owned Path Elements</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getScopeFilter <em>Scope Filter</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathNameCSImpl#getRole <em>Role</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PathNameCSImpl extends ElementCSImpl implements PathNameCS
{
	/**
	 * The number of structural features of the '<em>Path Name CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PATH_NAME_CS_FEATURE_COUNT = ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 6;

	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected ElementCS context;

	/**
	 * The cached value of the '{@link #getOwnedPathElements() <em>Owned Path Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPathElements()
	 * @generated
	 * @ordered
	 */
	protected EList<PathElementCS> ownedPathElements;

	/**
	 * The default value of the '{@link #getScopeFilter() <em>Scope Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeFilter()
	 * @generated
	 * @ordered
	 */
	protected static final ScopeFilter SCOPE_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScopeFilter() <em>Scope Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeFilter()
	 * @generated
	 * @ordered
	 */
	protected ScopeFilter scopeFilter = SCOPE_FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final PathRole ROLE_EDEFAULT = PathRole.NULL;

//	private static int count = 0;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathNameCSImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return BaseCSPackage.Literals.PATH_NAME_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PathElementCS> getOwnedPathElements()
	{
		if (ownedPathElements == null)
		{
			ownedPathElements = new EObjectContainmentWithInverseEList<PathElementCS>(PathElementCS.class, this, 3, 3);
		}
		return ownedPathElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 3:
				return ((InternalEList<?>)getOwnedPathElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 2:
				return getContext();
			case 3:
				return getOwnedPathElements();
			case 4:
				if (resolve) return getReferredElement();
				return basicGetReferredElement();
			case 5:
				return getScopeFilter();
			case 6:
				return getName();
			case 7:
				return getRole();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 2:
				setContext((ElementCS)newValue);
				return;
			case 3:
				getOwnedPathElements().clear();
				getOwnedPathElements().addAll((Collection<? extends PathElementCS>)newValue);
				return;
			case 5:
				setScopeFilter((ScopeFilter)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 7:
				setRole((PathRole)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 2:
				setContext((ElementCS)null);
				return;
			case 3:
				getOwnedPathElements().clear();
				return;
			case 5:
				setScopeFilter(SCOPE_FILTER_EDEFAULT);
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 7:
				setRole(ROLE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 2:
				return context != null;
			case 3:
				return ownedPathElements != null && !ownedPathElements.isEmpty();
			case 4:
				return basicGetReferredElement() != null;
			case 5:
				return SCOPE_FILTER_EDEFAULT == null ? scopeFilter != null : !SCOPE_FILTER_EDEFAULT.equals(scopeFilter);
			case 6:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case 7:
				return getRole() != ROLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitPathNameCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPathElements()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ElementCS getContext()
	{
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContext(ElementCS newContext)
	{
		ElementCS oldContext = context;
		context = newContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldContext, context));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScopeFilter getScopeFilter()
	{
		return scopeFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScopeFilter(ScopeFilter newScopeFilter)
	{
		ScopeFilter oldScopeFilter = scopeFilter;
		scopeFilter = newScopeFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 5, oldScopeFilter, scopeFilter));
	}

	private String name = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setName(String newName)
	{
		assert name == null;
		assert (newName == null) || newName.equals(newName.trim());
		this.name = newName;
//		System.out.println("setName " + NameUtil.debugSimpleName(this) + " " + name);
	}

	private PathRole role = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public PathRole getRole()
	{
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setRole(PathRole newRole)
	{
		role = newRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Element basicGetReferredElement()
	{
		if (ownedPathElements == null) {
			return null;
		}
		int iMax = ownedPathElements.size();
		if (iMax <= 0) {
			return null;
		}
		return ownedPathElements.get(iMax-1).basicGetReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		ICompositeNode node = NodeModelUtils.getNode(this);
		if (node == null) {
			if (role == PathRole.RETURN) {
				assert ownedPathElements != null;
				assert ownedPathElements.size() == 1;
				Constraint asPostcondition = (Constraint)ownedPathElements.get(0).getReferredElement();
				ExpressionInOCL asExpression = (ExpressionInOCL)asPostcondition.getOwnedSpecification();
				/*Parameter*/Variable asReturnVariable = asExpression.getOwnedResult();
				if (asReturnVariable != null) {
					return asReturnVariable;
				}
				// XXX errors
				return asPostcondition;
			}
			if (role == PathRole.PARAMETER) {
				assert ownedPathElements != null;
				assert ownedPathElements.size() == 1;
				Element asElement = ownedPathElements.get(0).getReferredElement();
				if (asElement instanceof Constraint) {
					Constraint asConstraint = (Constraint)asElement;
					ExpressionInOCL asExpression = (ExpressionInOCL)asConstraint.getOwnedSpecification();
					for (/*Parameter*/Variable asParameterVariable : asExpression.getOwnedParameters()) {
						if (asParameterVariable.getName().equals(getName())) {
							return asParameterVariable;
						}
					}
				}
				else if (asElement instanceof Operation) {
					Operation asOperation = (Operation)asElement;
					ExpressionInOCL asExpression = (ExpressionInOCL)asOperation.getBodyExpression();
					for (/*Parameter*/Variable asParameterVariable : asExpression.getOwnedParameters()) {
						if (asParameterVariable.getName().equals(getName())) {
							return asParameterVariable;
						}
					}
				}
			/*	Parameter asParameter = (Parameter)asElement;
				Operation asOperation = asParameter.getOwningOperation();
				ExpressionInOCL asExpression = (ExpressionInOCL)asOperation.getBodyExpression();
				for (/ *Parameter* /Variable asParameterVariable : asExpression.getOwnedParameters()) {
					if (asParameterVariable.getRepresentedParameter() == asParameter) {
						return asParameterVariable;
					}
				} */
				// XXX errors
				return asElement;
			}
		}
	/*	if (serialized != null) {
			assert ownedPathElements != null;
			assert ownedPathElements.size() == 1;
			Element element = ownedPathElements.get(0).getReferredElement();
			if (PivotConstants.RESULT_NAME.equals(serialized)) {
				assert element instanceof Operation;
			}
			else {

			}
		} */
		if (ownedPathElements == null) {
			return null;
		}
		int iMax = ownedPathElements.size();
		if (iMax <= 0) {
			return null;
		}
		for (int i = 0; i < iMax-1; i++) {
			Element element = ownedPathElements.get(i).getReferredElement();
			if ((element == null) || element.eIsProxy()) {
				return null;
			}
		}
		Element element = ownedPathElements.get(iMax-1).getReferredElement();
		if ((element != null) && element.eIsProxy()) {
			element = ownedPathElements.get(iMax-1).getReferredElement();
			return null;					// XXX
		}
		if ((element == null) || element.eIsProxy()) {
			return null;
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getPivot() {
		return getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resetPivot() {}
} //QualifiedElementRefCSImpl
