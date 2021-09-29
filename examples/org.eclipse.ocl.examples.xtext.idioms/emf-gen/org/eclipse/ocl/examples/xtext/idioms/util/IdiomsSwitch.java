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
package org.eclipse.ocl.examples.xtext.idioms.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.AnyElementLocator;
import org.eclipse.ocl.examples.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.CustomSegment;
import org.eclipse.ocl.examples.xtext.idioms.EPackageImport;
import org.eclipse.ocl.examples.xtext.idioms.FinalLocator;
import org.eclipse.ocl.examples.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsElement;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.NewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.PopSegment;
import org.eclipse.ocl.examples.xtext.idioms.PostCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PreCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PushSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.ReferredSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReturnsLocator;
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.SoftNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.SoftSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.StringSegment;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.ValueSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapAnchorSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapBeginAllSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapBeginSomeSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapEndSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapHereSegment;

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
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage
 * @generated
 */
public class IdiomsSwitch<@Nullable T>
		extends Switch<T> {

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
	public IdiomsSwitch() {
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
	protected boolean isSwitchFor(EPackage ePackage) {
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID)
		{
			case 0:
			{
				AnyAssignmentLocator anyAssignmentLocator = (AnyAssignmentLocator)theEObject;
				T result = caseAnyAssignmentLocator(anyAssignmentLocator);
				if (result == null) result = caseLocator(anyAssignmentLocator);
				if (result == null) result = caseIdiomsElement(anyAssignmentLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 1:
			{
				AnyElementLocator anyElementLocator = (AnyElementLocator)theEObject;
				T result = caseAnyElementLocator(anyElementLocator);
				if (result == null) result = caseLocator(anyElementLocator);
				if (result == null) result = caseIdiomsElement(anyElementLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 2:
			{
				AssignmentLocator assignmentLocator = (AssignmentLocator)theEObject;
				T result = caseAssignmentLocator(assignmentLocator);
				if (result == null) result = caseLocator(assignmentLocator);
				if (result == null) result = caseIdiomsElement(assignmentLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 3:
			{
				CustomSegment customSegment = (CustomSegment)theEObject;
				T result = caseCustomSegment(customSegment);
				if (result == null) result = caseSegment(customSegment);
				if (result == null) result = caseIdiomsElement(customSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 4:
			{
				EPackageImport ePackageImport = (EPackageImport)theEObject;
				T result = caseEPackageImport(ePackageImport);
				if (result == null) result = caseIdiomsElement(ePackageImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 5:
			{
				FinalLocator finalLocator = (FinalLocator)theEObject;
				T result = caseFinalLocator(finalLocator);
				if (result == null) result = caseLocator(finalLocator);
				if (result == null) result = caseIdiomsElement(finalLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 6:
			{
				HalfNewLineSegment halfNewLineSegment = (HalfNewLineSegment)theEObject;
				T result = caseHalfNewLineSegment(halfNewLineSegment);
				if (result == null) result = caseSegment(halfNewLineSegment);
				if (result == null) result = caseIdiomsElement(halfNewLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 7:
			{
				Idiom idiom = (Idiom)theEObject;
				T result = caseIdiom(idiom);
				if (result == null) result = caseIdiomsElement(idiom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 8:
			{
				IdiomsElement idiomsElement = (IdiomsElement)theEObject;
				T result = caseIdiomsElement(idiomsElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 9:
			{
				IdiomsImport idiomsImport = (IdiomsImport)theEObject;
				T result = caseIdiomsImport(idiomsImport);
				if (result == null) result = caseIdiomsElement(idiomsImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 10:
			{
				IdiomsModel idiomsModel = (IdiomsModel)theEObject;
				T result = caseIdiomsModel(idiomsModel);
				if (result == null) result = caseIdiomsElement(idiomsModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 11:
			{
				KeywordLocator keywordLocator = (KeywordLocator)theEObject;
				T result = caseKeywordLocator(keywordLocator);
				if (result == null) result = caseLocator(keywordLocator);
				if (result == null) result = caseIdiomsElement(keywordLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 12:
			{
				Locator locator = (Locator)theEObject;
				T result = caseLocator(locator);
				if (result == null) result = caseIdiomsElement(locator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 13:
			{
				LocatorDeclaration locatorDeclaration = (LocatorDeclaration)theEObject;
				T result = caseLocatorDeclaration(locatorDeclaration);
				if (result == null) result = caseIdiomsElement(locatorDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 14:
			{
				NewLineSegment newLineSegment = (NewLineSegment)theEObject;
				T result = caseNewLineSegment(newLineSegment);
				if (result == null) result = caseSegment(newLineSegment);
				if (result == null) result = caseIdiomsElement(newLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 15:
			{
				NoSpaceSegment noSpaceSegment = (NoSpaceSegment)theEObject;
				T result = caseNoSpaceSegment(noSpaceSegment);
				if (result == null) result = caseSegment(noSpaceSegment);
				if (result == null) result = caseIdiomsElement(noSpaceSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 16:
			{
				PopSegment popSegment = (PopSegment)theEObject;
				T result = casePopSegment(popSegment);
				if (result == null) result = caseSegment(popSegment);
				if (result == null) result = caseIdiomsElement(popSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 17:
			{
				PostCommentSegment postCommentSegment = (PostCommentSegment)theEObject;
				T result = casePostCommentSegment(postCommentSegment);
				if (result == null) result = caseSegment(postCommentSegment);
				if (result == null) result = caseIdiomsElement(postCommentSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 18:
			{
				PreCommentSegment preCommentSegment = (PreCommentSegment)theEObject;
				T result = casePreCommentSegment(preCommentSegment);
				if (result == null) result = caseSegment(preCommentSegment);
				if (result == null) result = caseIdiomsElement(preCommentSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 19:
			{
				ReferredLocator referredLocator = (ReferredLocator)theEObject;
				T result = caseReferredLocator(referredLocator);
				if (result == null) result = caseLocator(referredLocator);
				if (result == null) result = caseIdiomsElement(referredLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 20:
			{
				ReferredSegment referredSegment = (ReferredSegment)theEObject;
				T result = caseReferredSegment(referredSegment);
				if (result == null) result = caseSegment(referredSegment);
				if (result == null) result = caseIdiomsElement(referredSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 21:
			{
				ReturnsLocator returnsLocator = (ReturnsLocator)theEObject;
				T result = caseReturnsLocator(returnsLocator);
				if (result == null) result = caseLocator(returnsLocator);
				if (result == null) result = caseIdiomsElement(returnsLocator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 22:
			{
				PushSegment pushSegment = (PushSegment)theEObject;
				T result = casePushSegment(pushSegment);
				if (result == null) result = caseSegment(pushSegment);
				if (result == null) result = caseIdiomsElement(pushSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 23:
			{
				Segment segment = (Segment)theEObject;
				T result = caseSegment(segment);
				if (result == null) result = caseIdiomsElement(segment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 24:
			{
				SegmentDeclaration segmentDeclaration = (SegmentDeclaration)theEObject;
				T result = caseSegmentDeclaration(segmentDeclaration);
				if (result == null) result = caseIdiomsElement(segmentDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 25:
			{
				SoftNewLineSegment softNewLineSegment = (SoftNewLineSegment)theEObject;
				T result = caseSoftNewLineSegment(softNewLineSegment);
				if (result == null) result = caseSegment(softNewLineSegment);
				if (result == null) result = caseIdiomsElement(softNewLineSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 26:
			{
				SoftSpaceSegment softSpaceSegment = (SoftSpaceSegment)theEObject;
				T result = caseSoftSpaceSegment(softSpaceSegment);
				if (result == null) result = caseSegment(softSpaceSegment);
				if (result == null) result = caseIdiomsElement(softSpaceSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 27:
			{
				StringSegment stringSegment = (StringSegment)theEObject;
				T result = caseStringSegment(stringSegment);
				if (result == null) result = caseSegment(stringSegment);
				if (result == null) result = caseIdiomsElement(stringSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 28:
			{
				SubIdiom subIdiom = (SubIdiom)theEObject;
				T result = caseSubIdiom(subIdiom);
				if (result == null) result = caseIdiomsElement(subIdiom);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 29:
			{
				ValueSegment valueSegment = (ValueSegment)theEObject;
				T result = caseValueSegment(valueSegment);
				if (result == null) result = caseSegment(valueSegment);
				if (result == null) result = caseIdiomsElement(valueSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 30:
			{
				WrapAnchorSegment wrapAnchorSegment = (WrapAnchorSegment)theEObject;
				T result = caseWrapAnchorSegment(wrapAnchorSegment);
				if (result == null) result = caseSegment(wrapAnchorSegment);
				if (result == null) result = caseIdiomsElement(wrapAnchorSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 31:
			{
				WrapBeginSomeSegment wrapBeginSomeSegment = (WrapBeginSomeSegment)theEObject;
				T result = caseWrapBeginSomeSegment(wrapBeginSomeSegment);
				if (result == null) result = caseSegment(wrapBeginSomeSegment);
				if (result == null) result = caseIdiomsElement(wrapBeginSomeSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 32:
			{
				WrapBeginAllSegment wrapBeginAllSegment = (WrapBeginAllSegment)theEObject;
				T result = caseWrapBeginAllSegment(wrapBeginAllSegment);
				if (result == null) result = caseSegment(wrapBeginAllSegment);
				if (result == null) result = caseIdiomsElement(wrapBeginAllSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 33:
			{
				WrapEndSegment wrapEndSegment = (WrapEndSegment)theEObject;
				T result = caseWrapEndSegment(wrapEndSegment);
				if (result == null) result = caseSegment(wrapEndSegment);
				if (result == null) result = caseIdiomsElement(wrapEndSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 34:
			{
				WrapHereSegment wrapHereSegment = (WrapHereSegment)theEObject;
				T result = caseWrapHereSegment(wrapHereSegment);
				if (result == null) result = caseSegment(wrapHereSegment);
				if (result == null) result = caseIdiomsElement(wrapHereSegment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Assignment Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyAssignmentLocator(AnyAssignmentLocator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Element Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Element Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyElementLocator(AnyElementLocator object) {
		return null;
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
	public T caseAssignmentLocator(AssignmentLocator object) {
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
	public T caseCustomSegment(CustomSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EPackage Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EPackage Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEPackageImport(EPackageImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Final Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Final Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFinalLocator(FinalLocator object) {
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
	public T caseHalfNewLineSegment(HalfNewLineSegment object) {
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
	public T caseIdiom(Idiom object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdiomsElement(IdiomsElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdiomsImport(IdiomsImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdiomsModel(IdiomsModel object) {
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
	public T caseLocator(Locator object) {
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
	public T caseKeywordLocator(KeywordLocator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Locator Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Locator Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocatorDeclaration(LocatorDeclaration object) {
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
	public T caseNewLineSegment(NewLineSegment object) {
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
	public T caseNoSpaceSegment(NoSpaceSegment object) {
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
	public T casePopSegment(PopSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Post Comment Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Post Comment Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePostCommentSegment(PostCommentSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pre Comment Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pre Comment Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePreCommentSegment(PreCommentSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referred Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referred Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferredLocator(ReferredLocator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Referred Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referred Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferredSegment(ReferredSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Returns Locator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Returns Locator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReturnsLocator(ReturnsLocator object) {
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
	public T casePushSegment(PushSegment object) {
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
	public T caseSegment(Segment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Segment Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Segment Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSegmentDeclaration(SegmentDeclaration object) {
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
	public T caseSoftNewLineSegment(SoftNewLineSegment object) {
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
	public T caseSoftSpaceSegment(SoftSpaceSegment object) {
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
	public T caseStringSegment(StringSegment object) {
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
	public T caseSubIdiom(SubIdiom object) {
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
	public T caseValueSegment(ValueSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap Anchor Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap Anchor Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapAnchorSegment(WrapAnchorSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap Begin Some Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap Begin Some Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapBeginSomeSegment(WrapBeginSomeSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap Begin All Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap Begin All Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapBeginAllSegment(WrapBeginAllSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap End Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap End Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapEndSegment(WrapEndSegment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap Here Segment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap Here Segment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWrapHereSegment(WrapHereSegment object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //IdiomsSwitch
