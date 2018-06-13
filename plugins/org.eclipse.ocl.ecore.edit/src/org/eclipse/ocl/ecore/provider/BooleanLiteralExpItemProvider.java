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
package org.eclipse.ocl.ecore.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.ocl.ecore.BooleanLiteralExp;
import org.eclipse.ocl.ecore.EcorePackage;

import org.eclipse.ocl.expressions.ExpressionsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.ocl.ecore.BooleanLiteralExp} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BooleanLiteralExpItemProvider
		extends PrimitiveLiteralExpItemProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteralExpItemProvider(AdapterFactory adapterFactory) {
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

			addBooleanSymbolPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Boolean Symbol feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBooleanSymbolPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
			((ComposeableAdapterFactory) adapterFactory)
				.getRootAdapterFactory(),
			getResourceLocator(),
			getString("_UI_BooleanLiteralExp_booleanSymbol_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
				"_UI_BooleanLiteralExp_booleanSymbol_feature", //$NON-NLS-1$
				"_UI_BooleanLiteralExp_type"), //$NON-NLS-1$
			ExpressionsPackage.Literals.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL,
			true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
			null, null));
	}

	/**
	 * This returns BooleanLiteralExp.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object,
			getResourceLocator().getImage("full/obj16/BooleanLiteralExp")); //$NON-NLS-1$
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
		String label = ((BooleanLiteralExp) object).getName();
		return label == null || label.length() == 0
			? getString("_UI_BooleanLiteralExp_type") //$NON-NLS-1$
			: getString("_UI_BooleanLiteralExp_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(BooleanLiteralExp.class)) {
			case EcorePackage.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL :
				fireNotifyChanged(new ViewerNotification(notification,
					notification.getNotifier(), false, true));
				return;
		}
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
