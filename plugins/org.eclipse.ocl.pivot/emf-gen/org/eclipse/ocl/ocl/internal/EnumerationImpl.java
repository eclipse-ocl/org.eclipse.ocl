/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ocl.Behavior;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.DataType;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.Enumeration;
import org.eclipse.ocl.ocl.EnumerationLiteral;
import org.eclipse.ocl.ocl.NamedElement;
import org.eclipse.ocl.ocl.Namespace;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;

import org.eclipse.ocl.ocl.Operation;
import org.eclipse.ocl.ocl.Property;
import org.eclipse.ocl.ocl.StereotypeExtender;
import org.eclipse.ocl.ocl.TemplateBinding;
import org.eclipse.ocl.ocl.TemplateSignature;
import org.eclipse.ocl.ocl.TemplateableElement;
import org.eclipse.ocl.ocl.Type;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOclContainer <em>Ocl Container</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOclContents <em>Ocl Contents</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getAnnotatingComments <em>Annotating Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedComments <em>Owned Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedExtensions <em>Owned Extensions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedBindings <em>Owned Bindings</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getUnspecializedElement <em>Unspecialized Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getExtenders <em>Extenders</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getIsActive <em>Is Active</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedBehaviors <em>Owned Behaviors</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedInvariants <em>Owned Invariants</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedOperations <em>Owned Operations</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedProperties <em>Owned Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwningPackage <em>Owning Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getSuperClasses <em>Super Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getBehavioralClass <em>Behavioral Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getIsSerializable <em>Is Serializable</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.EnumerationImpl#getOwnedLiterals <em>Owned Literals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationImpl extends PivotObjectImpl implements Enumeration
{
	/**
	 * The number of structural features of the '<em>Enumeration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUMERATION_FEATURE_COUNT = 26;

	/**
	 * The number of operations of the '<em>Enumeration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUMERATION_OPERATION_COUNT = 0;


	/**
	 * The cached value of the '{@link #getOclContainer() <em>Ocl Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOclContainer()
	 * @generated
	 * @ordered
	 */
	protected OclElement oclContainer;

	/**
	 * The cached value of the '{@link #getOclContents() <em>Ocl Contents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOclContents()
	 * @generated
	 * @ordered
	 */
	protected EList<OclElement> oclContents;

	/**
	 * The cached value of the '{@link #getAnnotatingComments() <em>Annotating Comments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotatingComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> annotatingComments;

	/**
	 * The cached value of the '{@link #getOwnedAnnotations() <em>Owned Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> ownedAnnotations;

	/**
	 * The cached value of the '{@link #getOwnedComments() <em>Owned Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> ownedComments;

	/**
	 * The cached value of the '{@link #getOwnedExtensions() <em>Owned Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementExtension> ownedExtensions;

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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedConstraints;

	/**
	 * The cached value of the '{@link #getOwnedBindings() <em>Owned Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateBinding> ownedBindings;

	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignature ownedSignature;

	/**
	 * The cached value of the '{@link #getUnspecializedElement() <em>Unspecialized Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnspecializedElement()
	 * @generated
	 * @ordered
	 */
	protected TemplateableElement unspecializedElement;

	/**
	 * The cached value of the '{@link #getExtenders() <em>Extenders</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtenders()
	 * @generated
	 * @ordered
	 */
	protected EList<StereotypeExtender> extenders;

	/**
	 * The default value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClassName()
	 * @generated
	 * @ordered
	 */
	protected String instanceClassName = INSTANCE_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ABSTRACT_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected Boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsActive() <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsActive()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ACTIVE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsActive() <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsActive()
	 * @generated
	 * @ordered
	 */
	protected Boolean isActive = IS_ACTIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsInterface() <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsInterface()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_INTERFACE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsInterface() <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsInterface()
	 * @generated
	 * @ordered
	 */
	protected Boolean isInterface = IS_INTERFACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedBehaviors() <em>Owned Behaviors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBehaviors()
	 * @generated
	 * @ordered
	 */
	protected EList<Behavior> ownedBehaviors;

	/**
	 * The cached value of the '{@link #getOwnedInvariants() <em>Owned Invariants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInvariants()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedInvariants;

	/**
	 * The cached value of the '{@link #getOwnedOperations() <em>Owned Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> ownedOperations;

	/**
	 * The cached value of the '{@link #getOwnedProperties() <em>Owned Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> ownedProperties;

	/**
	 * The cached value of the '{@link #getSuperClasses() <em>Super Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.ocl.ocl.Class> superClasses;

	/**
	 * The cached value of the '{@link #getBehavioralClass() <em>Behavioral Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehavioralClass()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.ocl.ocl.Class behavioralClass;

	/**
	 * The default value of the '{@link #getIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SERIALIZABLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSerializable = IS_SERIALIZABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getOwnedLiterals() <em>Owned Literals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLiterals()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumerationLiteral> ownedLiterals;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationImpl()
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
		return OCLASPackage.Literals.ENUMERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OclElement getOclContainer()
	{
		if (oclContainer != null && oclContainer.eIsProxy())
		{
			InternalEObject oldOclContainer = (InternalEObject)oclContainer;
			oclContainer = (OclElement)eResolveProxy(oldOclContainer);
			if (oclContainer != oldOclContainer)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 0, oldOclContainer, oclContainer));
			}
		}
		return oclContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclElement basicGetOclContainer()
	{
		return oclContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOclContainer(OclElement newOclContainer)
	{
		OclElement oldOclContainer = oclContainer;
		oclContainer = newOclContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldOclContainer, oclContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<OclElement> getOclContents()
	{
		if (oclContents == null)
		{
			oclContents = new EObjectResolvingEList<OclElement>(OclElement.class, this, 1);
		}
		return oclContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getAnnotatingComments()
	{
		if (annotatingComments == null)
		{
			annotatingComments = new EObjectWithInverseResolvingEList.ManyInverse<Comment>(Comment.class, this, 2, 6);
		}
		return annotatingComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Element> getOwnedAnnotations()
	{
		if (ownedAnnotations == null)
		{
			ownedAnnotations = new EObjectContainmentEList<Element>(Element.class, this, 3);
		}
		return ownedAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getOwnedComments()
	{
		if (ownedComments == null)
		{
			ownedComments = new EObjectContainmentWithInverseEList<Comment>(Comment.class, this, 4, 8);
		}
		return ownedComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ElementExtension> getOwnedExtensions()
	{
		if (ownedExtensions == null)
		{
			ownedExtensions = new EObjectContainmentWithInverseEList<ElementExtension>(ElementExtension.class, this, 5, 22);
		}
		return ownedExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Constraint> getOwnedConstraints()
	{
		if (ownedConstraints == null)
		{
			ownedConstraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, 7);
		}
		return ownedConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateBinding> getOwnedBindings()
	{
		if (ownedBindings == null)
		{
			ownedBindings = new EObjectContainmentWithInverseEList<TemplateBinding>(TemplateBinding.class, this, 8, 7);
		}
		return ownedBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignature getOwnedSignature()
	{
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignature newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignature oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 9, oldOwnedSignature, newOwnedSignature);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedSignature(TemplateSignature newOwnedSignature)
	{
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, 7, TemplateSignature.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, 7, TemplateSignature.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 9, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateableElement getUnspecializedElement()
	{
		return unspecializedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnspecializedElement(TemplateableElement newUnspecializedElement)
	{
		TemplateableElement oldUnspecializedElement = unspecializedElement;
		unspecializedElement = newUnspecializedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, oldUnspecializedElement, unspecializedElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<StereotypeExtender> getExtenders()
	{
		if (extenders == null)
		{
			extenders = new EObjectWithInverseResolvingEList<StereotypeExtender>(StereotypeExtender.class, this, 11, 6);
		}
		return extenders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInstanceClassName()
	{
		return instanceClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInstanceClassName(String newInstanceClassName)
	{
		String oldInstanceClassName = instanceClassName;
		instanceClassName = newInstanceClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 12, oldInstanceClassName, instanceClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsAbstract()
	{
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsAbstract(Boolean newIsAbstract)
	{
		Boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, oldIsAbstract, isAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsActive()
	{
		return isActive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsActive(Boolean newIsActive)
	{
		Boolean oldIsActive = isActive;
		isActive = newIsActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 14, oldIsActive, isActive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsInterface()
	{
		return isInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInterface(Boolean newIsInterface)
	{
		Boolean oldIsInterface = isInterface;
		isInterface = newIsInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 15, oldIsInterface, isInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Behavior> getOwnedBehaviors()
	{
		if (ownedBehaviors == null)
		{
			ownedBehaviors = new EObjectContainmentEList<Behavior>(Behavior.class, this, 16);
		}
		return ownedBehaviors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Constraint> getOwnedInvariants()
	{
		if (ownedInvariants == null)
		{
			ownedInvariants = new EObjectContainmentEList<Constraint>(Constraint.class, this, 17);
		}
		return ownedInvariants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Operation> getOwnedOperations()
	{
		if (ownedOperations == null)
		{
			ownedOperations = new EObjectContainmentWithInverseEList<Operation>(Operation.class, this, 18, 25);
		}
		return ownedOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Property> getOwnedProperties()
	{
		if (ownedProperties == null)
		{
			ownedProperties = new EObjectContainmentWithInverseEList<Property>(Property.class, this, 19, 28);
		}
		return ownedProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.ocl.Package getOwningPackage()
	{
		if (eContainerFeatureID() != (20)) return null;
		return (org.eclipse.ocl.ocl.Package)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPackage(org.eclipse.ocl.ocl.Package newOwningPackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPackage, 20, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPackage(org.eclipse.ocl.ocl.Package newOwningPackage)
	{
		if (newOwningPackage != eInternalContainer() || (eContainerFeatureID() != (20) && newOwningPackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPackage != null)
				msgs = ((InternalEObject)newOwningPackage).eInverseAdd(this, 11, org.eclipse.ocl.ocl.Package.class, msgs);
			msgs = basicSetOwningPackage(newOwningPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 20, newOwningPackage, newOwningPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<org.eclipse.ocl.ocl.Class> getSuperClasses()
	{
		if (superClasses == null)
		{
			superClasses = new EObjectResolvingEList<org.eclipse.ocl.ocl.Class>(org.eclipse.ocl.ocl.Class.class, this, 21);
		}
		return superClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.ocl.Class getBehavioralClass()
	{
		if (behavioralClass != null && behavioralClass.eIsProxy())
		{
			InternalEObject oldBehavioralClass = (InternalEObject)behavioralClass;
			behavioralClass = (org.eclipse.ocl.ocl.Class)eResolveProxy(oldBehavioralClass);
			if (behavioralClass != oldBehavioralClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 22, oldBehavioralClass, behavioralClass));
			}
		}
		return behavioralClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.ocl.ocl.Class basicGetBehavioralClass()
	{
		return behavioralClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBehavioralClass(org.eclipse.ocl.ocl.Class newBehavioralClass)
	{
		org.eclipse.ocl.ocl.Class oldBehavioralClass = behavioralClass;
		behavioralClass = newBehavioralClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 22, oldBehavioralClass, behavioralClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsSerializable()
	{
		return isSerializable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsSerializable(Boolean newIsSerializable)
	{
		Boolean oldIsSerializable = isSerializable;
		isSerializable = newIsSerializable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 23, oldIsSerializable, isSerializable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue()
	{
		// TODO: implement this method to return the 'Value' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<EnumerationLiteral> getOwnedLiterals()
	{
		if (ownedLiterals == null)
		{
			ownedLiterals = new EObjectContainmentWithInverseEList<EnumerationLiteral>(EnumerationLiteral.class, this, 25, 12);
		}
		return ownedLiterals;
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
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 4:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 5:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 8:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case 9:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (9), null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case 11:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case 18:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case 19:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case 20:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.ocl.Package)otherEnd, msgs);
			case 25:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedLiterals()).basicAdd(otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 7:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 8:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case 9:
				return basicSetOwnedSignature(null, msgs);
			case 11:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case 16:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case 17:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case 18:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case 19:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case 20:
				return basicSetOwningPackage(null, msgs);
			case 25:
				return ((InternalEList<?>)getOwnedLiterals()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case 20:
				return eInternalContainer().eInverseRemove(this, 11, org.eclipse.ocl.ocl.Package.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case 0:
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getOwnedConstraints();
			case 8:
				return getOwnedBindings();
			case 9:
				return getOwnedSignature();
			case 10:
				return getUnspecializedElement();
			case 11:
				return getExtenders();
			case 12:
				return getInstanceClassName();
			case 13:
				return getIsAbstract();
			case 14:
				return getIsActive();
			case 15:
				return getIsInterface();
			case 16:
				return getOwnedBehaviors();
			case 17:
				return getOwnedInvariants();
			case 18:
				return getOwnedOperations();
			case 19:
				return getOwnedProperties();
			case 20:
				return getOwningPackage();
			case 21:
				return getSuperClasses();
			case 22:
				if (resolve) return getBehavioralClass();
				return basicGetBehavioralClass();
			case 23:
				return getIsSerializable();
			case 24:
				return getValue();
			case 25:
				return getOwnedLiterals();
		}
		return eDynamicGet(featureID, resolve, coreType);
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
			case 0:
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 7:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 8:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case 9:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case 10:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case 11:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case 12:
				setInstanceClassName((String)newValue);
				return;
			case 13:
				setIsAbstract((Boolean)newValue);
				return;
			case 14:
				setIsActive((Boolean)newValue);
				return;
			case 15:
				setIsInterface((Boolean)newValue);
				return;
			case 16:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case 17:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 18:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 19:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case 20:
				setOwningPackage((org.eclipse.ocl.ocl.Package)newValue);
				return;
			case 21:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.ocl.Class>)newValue);
				return;
			case 22:
				setBehavioralClass((org.eclipse.ocl.ocl.Class)newValue);
				return;
			case 23:
				setIsSerializable((Boolean)newValue);
				return;
			case 25:
				getOwnedLiterals().clear();
				getOwnedLiterals().addAll((Collection<? extends EnumerationLiteral>)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
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
			case 0:
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 7:
				getOwnedConstraints().clear();
				return;
			case 8:
				getOwnedBindings().clear();
				return;
			case 9:
				setOwnedSignature((TemplateSignature)null);
				return;
			case 10:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case 11:
				getExtenders().clear();
				return;
			case 12:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case 13:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case 14:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case 15:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case 16:
				getOwnedBehaviors().clear();
				return;
			case 17:
				getOwnedInvariants().clear();
				return;
			case 18:
				getOwnedOperations().clear();
				return;
			case 19:
				getOwnedProperties().clear();
				return;
			case 20:
				setOwningPackage((org.eclipse.ocl.ocl.Package)null);
				return;
			case 21:
				getSuperClasses().clear();
				return;
			case 22:
				setBehavioralClass((org.eclipse.ocl.ocl.Class)null);
				return;
			case 23:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
				return;
			case 25:
				getOwnedLiterals().clear();
				return;
		}
		eDynamicUnset(featureID);
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
			case 0:
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 8:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case 9:
				return ownedSignature != null;
			case 10:
				return unspecializedElement != null;
			case 11:
				return extenders != null && !extenders.isEmpty();
			case 12:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case 13:
				return IS_ABSTRACT_EDEFAULT == null ? isAbstract != null : !IS_ABSTRACT_EDEFAULT.equals(isAbstract);
			case 14:
				return IS_ACTIVE_EDEFAULT == null ? isActive != null : !IS_ACTIVE_EDEFAULT.equals(isActive);
			case 15:
				return IS_INTERFACE_EDEFAULT == null ? isInterface != null : !IS_INTERFACE_EDEFAULT.equals(isInterface);
			case 16:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case 17:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case 18:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case 19:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case 20:
				return getOwningPackage() != null;
			case 21:
				return superClasses != null && !superClasses.isEmpty();
			case 22:
				return behavioralClass != null;
			case 23:
				return IS_SERIALIZABLE_EDEFAULT == null ? isSerializable != null : !IS_SERIALIZABLE_EDEFAULT.equals(isSerializable);
			case 24:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
			case 25:
				return ownedLiterals != null && !ownedLiterals.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == Element.class)
		{
			switch (derivedFeatureID)
			{
				case 2: return 2;
				case 3: return 3;
				case 4: return 4;
				case 5: return 5;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class)
		{
			switch (derivedFeatureID)
			{
				case 6: return 6;
				default: return -1;
			}
		}
		if (baseClass == Type.class)
		{
			switch (derivedFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == Namespace.class)
		{
			switch (derivedFeatureID)
			{
				case 7: return 7;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (derivedFeatureID)
			{
				case 8: return 6;
				case 9: return 7;
				case 10: return 8;
				default: return -1;
			}
		}
		if (baseClass == org.eclipse.ocl.ocl.Class.class)
		{
			switch (derivedFeatureID)
			{
				case 11: return 11;
				case 12: return 12;
				case 13: return 13;
				case 14: return 14;
				case 15: return 15;
				case 16: return 16;
				case 17: return 17;
				case 18: return 18;
				case 19: return 19;
				case 20: return 20;
				case 21: return 21;
				default: return -1;
			}
		}
		if (baseClass == DataType.class)
		{
			switch (derivedFeatureID)
			{
				case 22: return 22;
				case 23: return 23;
				case 24: return 24;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == Element.class)
		{
			switch (baseFeatureID)
			{
				case 2: return 2;
				case 3: return 3;
				case 4: return 4;
				case 5: return 5;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class)
		{
			switch (baseFeatureID)
			{
				case 6: return 6;
				default: return -1;
			}
		}
		if (baseClass == Type.class)
		{
			switch (baseFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == Namespace.class)
		{
			switch (baseFeatureID)
			{
				case 7: return 7;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (baseFeatureID)
			{
				case 6: return 8;
				case 7: return 9;
				case 8: return 10;
				default: return -1;
			}
		}
		if (baseClass == org.eclipse.ocl.ocl.Class.class)
		{
			switch (baseFeatureID)
			{
				case 11: return 11;
				case 12: return 12;
				case 13: return 13;
				case 14: return 14;
				case 15: return 15;
				case 16: return 16;
				case 17: return 17;
				case 18: return 18;
				case 19: return 19;
				case 20: return 20;
				case 21: return 21;
				default: return -1;
			}
		}
		if (baseClass == DataType.class)
		{
			switch (baseFeatureID)
			{
				case 22: return 22;
				case 23: return 23;
				case 24: return 24;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", instanceClassName: "); //$NON-NLS-1$
		result.append(instanceClassName);
		result.append(", isAbstract: "); //$NON-NLS-1$
		result.append(isAbstract);
		result.append(", isActive: "); //$NON-NLS-1$
		result.append(isActive);
		result.append(", isInterface: "); //$NON-NLS-1$
		result.append(isInterface);
		result.append(", isSerializable: "); //$NON-NLS-1$
		result.append(isSerializable);
		result.append(')');
		return result.toString();
	}


} //EnumerationImpl
