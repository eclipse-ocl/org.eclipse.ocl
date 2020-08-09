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
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.*;
import org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsFactory;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdiomsFactoryImpl extends EFactoryImpl implements IdiomsFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IdiomsFactory init()
	{
		try
		{
			IdiomsFactory theIdiomsFactory = (IdiomsFactory)EPackage.Registry.INSTANCE.getEFactory(IdiomsPackage.eNS_URI);
			if (theIdiomsFactory != null)
			{
				return theIdiomsFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IdiomsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case 1: return createAssignmentLocator();
			case 2: return createCustomSegment();
			case 3: return createDefaultLocator();
			case 4: return createHalfNewLineSegment();
			case 5: return createIdiom();
			case 6: return createIdiomModel();
			case 8: return createKeywordLocator();
			case 9: return createNewLineSegment();
			case 10: return createNoSpaceSegment();
			case 11: return createPopSegment();
			case 12: return createProducedEClassLocator();
			case 13: return createPushSegment();
			case 15: return createSoftNewLineSegment();
			case 16: return createSoftSpaceSegment();
			case 17: return createStringSegment();
			case 18: return createSubIdiom();
			case 19: return createValueSegment();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case 23:
				return createSerializationNodeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case 23:
				return convertSerializationNodeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssignmentLocator createAssignmentLocator()
	{
		AssignmentLocatorImpl assignmentLocator = new AssignmentLocatorImpl();
		return assignmentLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomSegment createCustomSegment()
	{
		CustomSegmentImpl customSegment = new CustomSegmentImpl();
		return customSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DefaultLocator createDefaultLocator()
	{
		DefaultLocatorImpl defaultLocator = new DefaultLocatorImpl();
		return defaultLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HalfNewLineSegment createHalfNewLineSegment()
	{
		HalfNewLineSegmentImpl halfNewLineSegment = new HalfNewLineSegmentImpl();
		return halfNewLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Idiom createIdiom()
	{
		IdiomImpl idiom = new IdiomImpl();
		return idiom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomModel createIdiomModel()
	{
		IdiomModelImpl idiomModel = new IdiomModelImpl();
		return idiomModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KeywordLocator createKeywordLocator()
	{
		KeywordLocatorImpl keywordLocator = new KeywordLocatorImpl();
		return keywordLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NewLineSegment createNewLineSegment()
	{
		NewLineSegmentImpl newLineSegment = new NewLineSegmentImpl();
		return newLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NoSpaceSegment createNoSpaceSegment()
	{
		NoSpaceSegmentImpl noSpaceSegment = new NoSpaceSegmentImpl();
		return noSpaceSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PopSegment createPopSegment()
	{
		PopSegmentImpl popSegment = new PopSegmentImpl();
		return popSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProducedEClassLocator createProducedEClassLocator()
	{
		ProducedEClassLocatorImpl producedEClassLocator = new ProducedEClassLocatorImpl();
		return producedEClassLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PushSegment createPushSegment()
	{
		PushSegmentImpl pushSegment = new PushSegmentImpl();
		return pushSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SoftNewLineSegment createSoftNewLineSegment()
	{
		SoftNewLineSegmentImpl softNewLineSegment = new SoftNewLineSegmentImpl();
		return softNewLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SoftSpaceSegment createSoftSpaceSegment()
	{
		SoftSpaceSegmentImpl softSpaceSegment = new SoftSpaceSegmentImpl();
		return softSpaceSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringSegment createStringSegment()
	{
		StringSegmentImpl stringSegment = new StringSegmentImpl();
		return stringSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SubIdiom createSubIdiom()
	{
		SubIdiomImpl subIdiom = new SubIdiomImpl();
		return subIdiom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValueSegment createValueSegment()
	{
		ValueSegmentImpl valueSegment = new ValueSegmentImpl();
		return valueSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SerializationNode createSerializationNodeFromString(EDataType eDataType, String initialValue)
	{
		return (SerializationNode)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSerializationNodeToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsPackage getIdiomsPackage()
	{
		return (IdiomsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IdiomsPackage getPackage()
	{
		return IdiomsPackage.eINSTANCE;
	}

} //IdiomsFactoryImpl
