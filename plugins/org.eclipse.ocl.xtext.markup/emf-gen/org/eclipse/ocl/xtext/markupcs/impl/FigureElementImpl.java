/*******************************************************************************
 * Copyright (c) 2011, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.markupcs.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.xtext.markupcs.FigureElement;
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fig Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getAlt <em>Alt</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getDef <em>Def</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getRequiredWidth <em>Required Width</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getRequiredHeight <em>Required Height</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getActualWidth <em>Actual Width</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.FigureElementImpl#getActualHeight <em>Actual Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FigureElementImpl extends MarkupElementImpl implements FigureElement {
	/**
	 * The number of structural features of the '<em>Figure Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIGURE_ELEMENT_FEATURE_COUNT = MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getSrc() <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
	protected static final String SRC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSrc() <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
	protected String src = SRC_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlt() <em>Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlt()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlt() <em>Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlt()
	 * @generated
	 * @ordered
	 */
	protected String alt = ALT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDef() <em>Def</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDef()
	 * @generated
	 * @ordered
	 */
	protected static final String DEF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDef() <em>Def</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDef()
	 * @generated
	 * @ordered
	 */
	protected String def = DEF_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequiredWidth() <em>Required Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUIRED_WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequiredWidth() <em>Required Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredWidth()
	 * @generated
	 * @ordered
	 */
	protected String requiredWidth = REQUIRED_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequiredHeight() <em>Required Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredHeight()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUIRED_HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequiredHeight() <em>Required Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredHeight()
	 * @generated
	 * @ordered
	 */
	protected String requiredHeight = REQUIRED_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualWidth() <em>Actual Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTUAL_WIDTH_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getActualHeight() <em>Actual Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTUAL_HEIGHT_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FigureElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MarkupPackage.Literals.FIGURE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getId() {
		return internalGetId();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSrc() {
		return src;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSrc(String newSrc) {
		String oldSrc = src;
		src = newSrc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 1, oldSrc, src));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAlt() {
		return alt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlt(String newAlt) {
		String oldAlt = alt;
		alt = newAlt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 2, oldAlt, alt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDef() {
		return def;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDef(String newDef) {
		String oldDef = def;
		def = newDef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 3, oldDef, def));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRequiredWidth() {
		return requiredWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequiredWidth(String newRequiredWidth) {
		String oldRequiredWidth = requiredWidth;
		requiredWidth = newRequiredWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 4, oldRequiredWidth, requiredWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRequiredHeight() {
		return requiredHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequiredHeight(String newRequiredHeight) {
		String oldRequiredHeight = requiredHeight;
		requiredHeight = newRequiredHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 5, oldRequiredHeight, requiredHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getActualWidth() {
		return internalGetActualWidth();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getActualHeight() {
		return internalGetActualHeight();
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
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 0:
				return getId();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 1:
				return getSrc();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 2:
				return getAlt();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 3:
				return getDef();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 4:
				return getRequiredWidth();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 5:
				return getRequiredHeight();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 6:
				return getActualWidth();
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 7:
				return getActualHeight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 1:
				setSrc((String)newValue);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 2:
				setAlt((String)newValue);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 3:
				setDef((String)newValue);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 4:
				setRequiredWidth((String)newValue);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 5:
				setRequiredHeight((String)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 1:
				setSrc(SRC_EDEFAULT);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 2:
				setAlt(ALT_EDEFAULT);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 3:
				setDef(DEF_EDEFAULT);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 4:
				setRequiredWidth(REQUIRED_WIDTH_EDEFAULT);
				return;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 5:
				setRequiredHeight(REQUIRED_HEIGHT_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 0:
				return getId() != ID_EDEFAULT;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 1:
				return SRC_EDEFAULT == null ? src != null : !SRC_EDEFAULT.equals(src);
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 2:
				return ALT_EDEFAULT == null ? alt != null : !ALT_EDEFAULT.equals(alt);
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 3:
				return DEF_EDEFAULT == null ? def != null : !DEF_EDEFAULT.equals(def);
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 4:
				return REQUIRED_WIDTH_EDEFAULT == null ? requiredWidth != null : !REQUIRED_WIDTH_EDEFAULT.equals(requiredWidth);
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 5:
				return REQUIRED_HEIGHT_EDEFAULT == null ? requiredHeight != null : !REQUIRED_HEIGHT_EDEFAULT.equals(requiredHeight);
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 6:
				return getActualWidth() != ACTUAL_WIDTH_EDEFAULT;
			case MarkupElementImpl.MARKUP_ELEMENT_FEATURE_COUNT + 7:
				return getActualHeight() != ACTUAL_HEIGHT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Start of copy from platform:/resource/org.eclipse.ocl.xtext.markup/model/FigureElement.javacopy 
	 */
	@SuppressWarnings("unused") private static int _START_OF_COPY_ = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private static int idCounter = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private int id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private int internalGetId() {
		if (id == ID_EDEFAULT) {
			id = ++idCounter;
		}
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private BufferedImage image = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private BufferedImage internalGetImage() {
		if (image == null) {
			try {
				image = ImageIO.read(new File(getSrc()));
			} catch (IOException e) {
				System.err.println("Failed to read '" + getSrc() + "'");
				e.printStackTrace();
			}
		}
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private int internalGetActualHeight() {
		BufferedImage image = internalGetImage();
		return image != null ? image.getHeight() : 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated COPY
	 */
	private int internalGetActualWidth() {
		BufferedImage image = internalGetImage();
		return image != null ? image.getWidth() : 0;
	}

	/**
	 * End of copy from platform:/resource/org.eclipse.ocl.xtext.markup/model/FigureElement.javacopy 
	 */
	@SuppressWarnings("unused") private static int _END_OF_COPY_ = 0;
} //FigElementImpl
