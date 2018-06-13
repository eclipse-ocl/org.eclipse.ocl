/*******************************************************************************
 * Copyright (c) 2009, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.expressions.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.ocl.expressions.EnumLiteralExp;
import org.eclipse.ocl.expressions.ExpressionsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.ocl.expressions.EnumLiteralExp} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumLiteralExpItemProvider
		extends LiteralExpItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumLiteralExpItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addReferredEnumLiteralPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Referred Enum Literal feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReferredEnumLiteralPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
			((ComposeableAdapterFactory) adapterFactory)
				.getRootAdapterFactory(),
			getResourceLocator(),
			getString("_UI_EnumLiteralExp_referredEnumLiteral_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
				"_UI_EnumLiteralExp_referredEnumLiteral_feature", //$NON-NLS-1$
				"_UI_EnumLiteralExp_type"), //$NON-NLS-1$
			ExpressionsPackage.Literals.ENUM_LITERAL_EXP__REFERRED_ENUM_LITERAL,
			true, false, false, null, null, null));
	}

	/**
	 * This returns EnumLiteralExp.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
			getResourceLocator().getImage("full/obj16/EnumLiteralExp")); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		EnumLiteralExp<?, ?> enumLiteralExp = (EnumLiteralExp<?, ?>) object;
		return getString("_UI_EnumLiteralExp_type") + " " //$NON-NLS-1$//$NON-NLS-2$
			+ enumLiteralExp.getStartPosition();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(
			Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
