/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.xtext.base.cs2text.idioms.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage
 * @generated
 */
public class IdiomsAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IdiomsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = IdiomsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomsSwitch<@Nullable Adapter> modelSwitch =
		new IdiomsSwitch<@Nullable Adapter>()
		{
			@Override
			public Adapter caseAssignmentLocator(AssignmentLocator object)
			{
				return createAssignmentLocatorAdapter();
			}
			@Override
			public Adapter caseCommentSegmentSupport(CommentSegmentSupport object)
			{
				return createCommentSegmentSupportAdapter();
			}
			@Override
			public Adapter caseCustomSegment(CustomSegment object)
			{
				return createCustomSegmentAdapter();
			}
			@Override
			public Adapter caseCustomSegmentSupport(CustomSegmentSupport object)
			{
				return createCustomSegmentSupportAdapter();
			}
			@Override
			public Adapter caseDefaultLocator(DefaultLocator object)
			{
				return createDefaultLocatorAdapter();
			}
			@Override
			public Adapter caseHalfNewLineSegment(HalfNewLineSegment object)
			{
				return createHalfNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseIdiom(Idiom object)
			{
				return createIdiomAdapter();
			}
			@Override
			public Adapter caseIdiomModel(IdiomModel object)
			{
				return createIdiomModelAdapter();
			}
			@Override
			public Adapter caseLocator(Locator object)
			{
				return createLocatorAdapter();
			}
			@Override
			public Adapter caseKeywordLocator(KeywordLocator object)
			{
				return createKeywordLocatorAdapter();
			}
			@Override
			public Adapter caseNewLineSegment(NewLineSegment object)
			{
				return createNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseNoSpaceSegment(NoSpaceSegment object)
			{
				return createNoSpaceSegmentAdapter();
			}
			@Override
			public Adapter casePopSegment(PopSegment object)
			{
				return createPopSegmentAdapter();
			}
			@Override
			public Adapter caseProducedEClassLocator(ProducedEClassLocator object)
			{
				return createProducedEClassLocatorAdapter();
			}
			@Override
			public Adapter casePushSegment(PushSegment object)
			{
				return createPushSegmentAdapter();
			}
			@Override
			public Adapter caseSegment(Segment object)
			{
				return createSegmentAdapter();
			}
			@Override
			public Adapter caseSoftNewLineSegment(SoftNewLineSegment object)
			{
				return createSoftNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseSoftSpaceSegment(SoftSpaceSegment object)
			{
				return createSoftSpaceSegmentAdapter();
			}
			@Override
			public Adapter caseStringSegment(StringSegment object)
			{
				return createStringSegmentAdapter();
			}
			@Override
			public Adapter caseSubIdiom(SubIdiom object)
			{
				return createSubIdiomAdapter();
			}
			@Override
			public Adapter caseValueSegment(ValueSegment object)
			{
				return createValueSegmentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator <em>Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator
	 * @generated
	 */
	public Adapter createAssignmentLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport <em>Comment Segment Support</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport
	 * @generated
	 */
	public Adapter createCommentSegmentSupportAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment <em>Custom Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment
	 * @generated
	 */
	public Adapter createCustomSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport <em>Custom Segment Support</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport
	 * @generated
	 */
	public Adapter createCustomSegmentSupportAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator <em>Default Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator
	 * @generated
	 */
	public Adapter createDefaultLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment <em>Half New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment
	 * @generated
	 */
	public Adapter createHalfNewLineSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom <em>Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom
	 * @generated
	 */
	public Adapter createIdiomAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel <em>Idiom Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel
	 * @generated
	 */
	public Adapter createIdiomModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Locator
	 * @generated
	 */
	public Adapter createLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator <em>Keyword Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator
	 * @generated
	 */
	public Adapter createKeywordLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment <em>New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment
	 * @generated
	 */
	public Adapter createNewLineSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment <em>No Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment
	 * @generated
	 */
	public Adapter createNoSpaceSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment <em>Pop Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment
	 * @generated
	 */
	public Adapter createPopSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator <em>Produced EClass Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator
	 * @generated
	 */
	public Adapter createProducedEClassLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment <em>Push Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment
	 * @generated
	 */
	public Adapter createPushSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Segment
	 * @generated
	 */
	public Adapter createSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment <em>Soft New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment
	 * @generated
	 */
	public Adapter createSoftNewLineSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment <em>Soft Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment
	 * @generated
	 */
	public Adapter createSoftSpaceSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment <em>String Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment
	 * @generated
	 */
	public Adapter createStringSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom <em>Sub Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom
	 * @generated
	 */
	public Adapter createSubIdiomAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment <em>Value Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment
	 * @generated
	 */
	public Adapter createValueSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //IdiomsAdapterFactory
