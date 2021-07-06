/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common;

/**
 * Constants common to all Eclipse OCL facilities.
 */
public interface OCLConstants
{
	public static final String PLUGIN_ID = OCLConstants.class.getPackage().getName();

	/**
	 * The EAnnotation source URI for delegate OCL annotations.
	 * <p>
	 * For an EOperation, the EAnnotation details may include
	 * <br>
	 * a <tt>body</tt> key to provide an OCL expression value that specifies <tt>body:</tt> of the operation.
	 * <br>
	 * a <tt>precondition</tt> key to provide an OCL expression value that specifies <tt>pre:</tt> for the operation.
	 * <br>
	 * a <tt>postcondition</tt> key to provide an OCL expression value that specifies <tt>post:</tt> for the operation.
	 * <p>
	 * For an EStructuralFeature, the EAnnotation details may include
	 * <br>
	 * a <tt>derivation</tt> key to provide an OCL expression value that specifies <tt>derive:</tt> for the property.
	 * <br>
	 * a <tt>initial</tt> key to provide an OCL expression value that specifies <tt>initial:</tt> for the operation.
	 * <p>
	 * For an EClassifier (EClass, EDataType), the EAnnotation details may include
	 * <br>
	 * a <tt><i>constraintName</i></tt> key to provide an OCL expression value that specifies <tt>inv <i>constraintName</i>:</tt> for the classifier.
	 * <p>
	 * For an EPackage, the EAnnotation details may include
	 * <br>
	 * an {@link org.eclipse.ocl.ecore.delegate.OCLDelegateDomain#KEY_FOR_ENVIRONMENT_FACTORY_CLASS environmentFactoryClass} key whose value is the fully qualified
	 * class name for the {@link org.eclipse.ocl.EnvironmentFactory}. If no key is specified either the {@link org.eclipse.ocl.ecore.EcoreEnvironmentFactory}
	 * or {@link org.eclipse.ocl.ecore.opposites.EcoreEnvironmentFactoryWithHiddenOpposites} class are used.
	 * <br>
	 * a {@link org.eclipse.ocl.ecore.delegate.OCLDelegateDomain#OCL_DELEGATES_USE_HIDDEN_OPPOSITES_KEY hiddenOpposites} key that may have a <tt>true</tt> value to
	 * use the {@link org.eclipse.ocl.ecore.opposites.EcoreEnvironmentFactoryWithHiddenOpposites} class rather than the {@link org.eclipse.ocl.ecore.EcoreEnvironmentFactory}
	 * when no {@link org.eclipse.ocl.ecore.delegate.OCLDelegateDomain#KEY_FOR_ENVIRONMENT_FACTORY_CLASS environmentFactoryClass} key is specified.
	 * <p>
	 * Note that the delegate OCL functionality must be enabled by an EPackage Ecore annotation specifying this URI
	 * as the value of <tt>invocationDelegates</tt>, <tt>settingDelegates</tt> and <tt>validationDelegates</tt> details
	 * keys.
	 * <p>
	 * Note also that validation must be enabled by specifying an EClassifier Ecore annotation with a space separated list
	 * of invariant <tt><i>constraintName</i></tt>s as the value of the <tt>constraints</tt> details key.
	 * <p>
	 * See <tt>/org.eclipse.ocl.ecore.tests/model/Company.ecore</tt> or <tt>http://wiki.eclipse.org/MDT/OCLinEcore</tt> for an example.
	 */
	public static final String OCL_DELEGATE_URI = org.eclipse.emf.ecore.EcorePackage.eNS_URI + "/OCL"; //$NON-NLS-1$
	public static final String OCL_DELEGATE_URI_SLASH = OCL_DELEGATE_URI + "/"; //$NON-NLS-1$

	/**
	 * @since 1.16
	 */
	public static final String OCL_DELEGATE_URI_DEBUG = OCL_DELEGATE_URI_SLASH + "Debug"; //$NON-NLS-1$
	public static final String OCL_DELEGATE_URI_LPG = OCL_DELEGATE_URI_SLASH + "LPG"; //$NON-NLS-1$

	/**
	 * @since 1.16
	 */
	public static final String OCL_DELEGATE_URI_PIVOT = OCL_DELEGATE_URI_SLASH + "Pivot"; //$NON-NLS-1$
}
