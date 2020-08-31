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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.xtext.base.cs2text.idioms.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage
 * @generated
 */
public class IdiomsSwitch<@Nullable T> extends Switch<T>
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IdiomsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsSwitch()
	{
		if (modelPackage == null)
		{
			modelPackage = IdiomsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage)
	{
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject)
	{
		switch (classifierID)
		{
			case 0:
			{
				AssignmentLocator assignmentLocator = (AssignmentLocator)theEObject;
				T result = caseAssignmentLocator(assignmentLocator);
				if (result == null) result = caseLocator(assignmentLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 1:
			{
				CommentSegmentSupport commentSegmentSupport = (CommentSegmentSupport)theEObject;
				T result = caseCommentSegmentSupport(commentSegmentSupport);
				if (result == null) result = caseCustomSegmentSupport(commentSegmentSupport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 2:
			{
				CustomSegment customSegment = (CustomSegment)theEObject;
				T result = caseCustomSegment(customSegment);
				if (result == null) result = caseSegment(customSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 3:
			{
				CustomSegmentSupport customSegmentSupport = (CustomSegmentSupport)theEObject;
				T result = caseCustomSegmentSupport(customSegmentSupport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 4:
			{
				HalfNewLineSegment halfNewLineSegment = (HalfNewLineSegment)theEObject;
				T result = caseHalfNewLineSegment(halfNewLineSegment);
				if (result == null) result = caseSegment(halfNewLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 5:
			{
				Idiom idiom = (Idiom)theEObject;
				T result = caseIdiom(idiom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 6:
			{
				IdiomModel idiomModel = (IdiomModel)theEObject;
				T result = caseIdiomModel(idiomModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 7:
			{
				Locator locator = (Locator)theEObject;
				T result = caseLocator(locator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 8:
			{
				KeywordLocator keywordLocator = (KeywordLocator)theEObject;
				T result = caseKeywordLocator(keywordLocator);
				if (result == null) result = caseLocator(keywordLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 9:
			{
				NewLineSegment newLineSegment = (NewLineSegment)theEObject;
				T result = caseNewLineSegment(newLineSegment);
				if (result == null) result = caseSegment(newLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 10:
			{
				NoSpaceSegment noSpaceSegment = (NoSpaceSegment)theEObject;
				T result = caseNoSpaceSegment(noSpaceSegment);
				if (result == null) result = caseSegment(noSpaceSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 11:
			{
				PopSegment popSegment = (PopSegment)theEObject;
				T result = casePopSegment(popSegment);
				if (result == null) result = caseSegment(popSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 12:
			{
				ProducedEClassLocator producedEClassLocator = (ProducedEClassLocator)theEObject;
				T result = caseProducedEClassLocator(producedEClassLocator);
				if (result == null) result = caseLocator(producedEClassLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 13:
			{
				PushSegment pushSegment = (PushSegment)theEObject;
				T result = casePushSegment(pushSegment);
				if (result == null) result = caseSegment(pushSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 14:
			{
				Segment segment = (Segment)theEObject;
				T result = caseSegment(segment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 15:
			{
				SoftNewLineSegment softNewLineSegment = (SoftNewLineSegment)theEObject;
				T result = caseSoftNewLineSegment(softNewLineSegment);
				if (result == null) result = caseSegment(softNewLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 16:
			{
				SoftSpaceSegment softSpaceSegment = (SoftSpaceSegment)theEObject;
				T result = caseSoftSpaceSegment(softSpaceSegment);
				if (result == null) result = caseSegment(softSpaceSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 17:
			{
				StringSegment stringSegment = (StringSegment)theEObject;
				T result = caseStringSegment(stringSegment);
				if (result == null) result = caseSegment(stringSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 18:
			{
				SubIdiom subIdiom = (SubIdiom)theEObject;
				T result = caseSubIdiom(subIdiom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 19:
			{
				ValueSegment valueSegment = (ValueSegment)theEObject;
				T result = caseValueSegment(valueSegment);
				if (result == null) result = caseSegment(valueSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assignment Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssignmentLocator(AssignmentLocator object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment Segment Support</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment Segment Support</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommentSegmentSupport(CommentSegmentSupport object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomSegment(CustomSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Segment Support</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Segment Support</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomSegmentSupport(CustomSegmentSupport object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Half New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Half New Line Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHalfNewLineSegment(HalfNewLineSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idiom</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdiom(Idiom object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idiom Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idiom Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdiomModel(IdiomModel object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocator(Locator object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Keyword Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Keyword Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKeywordLocator(KeywordLocator object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>New Line Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNewLineSegment(NewLineSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>No Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>No Space Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoSpaceSegment(NoSpaceSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pop Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pop Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePopSegment(PopSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Produced EClass Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Produced EClass Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProducedEClassLocator(ProducedEClassLocator object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Push Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Push Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePushSegment(PushSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSegment(Segment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Soft New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Soft New Line Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftNewLineSegment(SoftNewLineSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Soft Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Soft Space Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftSpaceSegment(SoftSpaceSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringSegment(StringSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sub Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sub Idiom</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubIdiom(SubIdiom object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSegment(ValueSegment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object)
	{
		return null;
	}

} //IdiomsSwitch
