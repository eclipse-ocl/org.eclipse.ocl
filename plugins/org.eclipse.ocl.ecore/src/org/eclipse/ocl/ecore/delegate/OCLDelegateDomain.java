/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 360072
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import java.lang.reflect.Constructor;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;
import org.eclipse.ocl.common.internal.delegate.OCLInvocationDelegateMapping;
import org.eclipse.ocl.common.internal.delegate.OCLQueryDelegateMapping;
import org.eclipse.ocl.common.internal.delegate.OCLSettingDelegateMapping;
import org.eclipse.ocl.common.internal.delegate.OCLValidationDelegateMapping;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OppositePropertyCallExp;
import org.eclipse.ocl.ecore.opposites.EcoreEnvironmentFactoryWithHiddenOpposites;

/**
 * An implementation of a delegate domain for an OCL enhanced package. The domain
 * maintains an OCL facade to be shared by all delegates within the package.
 * 
 * @since 3.0
 */
public class OCLDelegateDomain implements DelegateDomain
{
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
	 * an {@link #KEY_FOR_ENVIRONMENT_FACTORY_CLASS environmentFactoryClass} key whose value is the fully qualified
	 * class name for the {@link EnvironmentFactory}. If no key is specified either the {@link EcoreEnvironmentFactory}
	 * or {@link EcoreEnvironmentFactoryWithHiddenOpposites} class are used.
	 * <br>
	 * a {@link #OCL_DELEGATES_USE_HIDDEN_OPPOSITES_KEY hiddenOpposites} key that may have a <tt>true</tt> value to
	 * use the {@link EcoreEnvironmentFactoryWithHiddenOpposites} class rather than the {@link EcoreEnvironmentFactory}
	 * when no {@link #KEY_FOR_ENVIRONMENT_FACTORY_CLASS environmentFactoryClass} key is specified.
	 * <p>
	 * Note that the delegate OCL functionality must be enabled by an EPackage Ecore annotation specifying this URI
	 * as the value of <tt>invocationDelegates</tt>, <tt>settingDelegates</tt> and <tt>validationDelegates</tt> details
	 * keys.
	 * <p>
	 * Note also that validation must be enabled by specifying an EClassifier Ecore annotation with a space separated list
	 * of invariant <tt><i>constraintName</i></tt>s as the value of the <tt>constraints</tt> details key. 
	 * <p>
	 * See <tt>/org.eclipse.ocl.ecore.tests/model/Company.ecore</tt> or <tt>http://wiki.eclipse.org/MDT/OCLinEcore</tt> for an example.
	 * 
	 * @deprecated use OCLConstants.OCL_DELEGATE_URI
	 */
	@Deprecated
	public static final String OCL_DELEGATE_URI = OCLConstants.OCL_DELEGATE_URI;

	 /**
	 * If the EPackage annotation with source {@link #OCL_DELEGATE_URI} has a detail using this key,
	 * the value is the fully qualified name of the class to be used as the {@link EnvironmentFactory}
	 * for parsing and evaluation for OCL constraints defined in the EPackage. The class must have a
	 * a constructor that takes a single {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} argument.
	 * 
	 * @since 3.1
	 */
	public static final String KEY_FOR_ENVIRONMENT_FACTORY_CLASS = "environmentFactoryClass"; //$NON-NLS-1$
	
	/**
	 * If the EPackage annotation with source {@link #OCL_DELEGATE_URI} has a detail using this key with a
	 * value of "true", the {@link EcoreEnvironmentFactoryWithHiddenOpposites} is used instead of
	 * the default {@link EcoreEnvironmentFactory}, making the OCL environment used by the delegates
	 * support "hidden opposites" and the {@link OppositePropertyCallExp}.
	 * 
	 * @since 3.1
	 */
	public static final String OCL_DELEGATES_USE_HIDDEN_OPPOSITES_KEY = "hiddenOpposites"; //$NON-NLS-1$

	/**
	 * Initialize the resourceSet registries, if non-null, or the global registries, if null,
	 * to support usage of the LPG OCL Delegate Evaluator for the LPG OCL Delegate URI. 
	 *
	 * @since 3.2
	 */
	public static void initialize(ResourceSet resourceSet) {
		initialize(resourceSet, OCLConstants.OCL_DELEGATE_URI_LPG);
		initializeMappingFrom(resourceSet, OCLConstants.OCL_DELEGATE_URI);
	}
	
	/**
	 * Initialize the resourceSet registries, if non-null, or the global registries, if null,
	 * to support usage of the LPG OCL Delegate Evaluator for the oclDelegateURI. 
	 *
	 * @since 3.2
	 */
	public static void initialize(ResourceSet resourceSet, String oclDelegateURI) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// Install the 'plugin' registrations
			EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI, new OCLInvocationDelegateFactory.Global());
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI, new OCLSettingDelegateFactory.Global());
			EValidator.ValidationDelegate.Registry.INSTANCE.put(oclDelegateURI, new OCLValidationDelegateFactory.Global());
			QueryDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI, new OCLQueryDelegateFactory.Global());
		}
		if (resourceSet != null) {
			// Install a DelegateResourceSetAdapter to supervise local registries and resource post-loading
			DelegateResourceSetAdapter adapter = DelegateResourceSetAdapter.getAdapter(resourceSet);
			VirtualDelegateMapping delegationMode = CommonOptions.DEFAULT_DELEGATION_MODE;
			adapter.putRegistry(VirtualDelegateMapping.class, new VirtualDelegateMapping(delegationMode.getPluginId(), delegationMode.getKey(), delegationMode.getPreferredValue()));
	
			// Install a local DelegateDomain.Factory
			DelegateDomain.Factory.Registry.Impl delegateDomainFactory = new DelegateDomain.Factory.Registry.Impl();
			delegateDomainFactory.put(oclDelegateURI, new OCLDelegateDomainFactory());
			adapter.putRegistry(DelegateDomain.Factory.Registry.class, delegateDomainFactory);
					
			// Install a local ValidationDelegate.Factory
			ValidationDelegate.Factory.Registry validationDelegateFactoryRegistry = new ValidationDelegate.Factory.Registry.Impl();
			validationDelegateFactoryRegistry.put(oclDelegateURI, new OCLValidationDelegateFactory(oclDelegateURI));
			adapter.putRegistry(ValidationDelegate.Factory.Registry.class, validationDelegateFactoryRegistry);
	
			// Install a local SettingDelegate.Factory
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry settingDelegateFactoryRegistry = new EStructuralFeature.Internal.SettingDelegate.Factory.Registry.Impl();
			settingDelegateFactoryRegistry.put(oclDelegateURI, new OCLSettingDelegateFactory(oclDelegateURI));
			adapter.putRegistry(EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class, settingDelegateFactoryRegistry);
	
			// Install a local InvocationDelegate.Factory
			EOperation.Internal.InvocationDelegate.Factory.Registry invocationDelegateFactoryRegistry = new EOperation.Internal.InvocationDelegate.Factory.Registry.Impl();
			invocationDelegateFactoryRegistry.put(oclDelegateURI, new OCLInvocationDelegateFactory(oclDelegateURI));
			adapter.putRegistry(EOperation.Internal.InvocationDelegate.Factory.Registry.class, invocationDelegateFactoryRegistry);	
	
			// Install a local QueryDelegate.Factory
			QueryDelegate.Factory.Registry queryDelegateFactoryRegistry = new QueryDelegate.Factory.Registry.Impl();
			queryDelegateFactoryRegistry.put(oclDelegateURI, new OCLQueryDelegateFactory(oclDelegateURI));
			adapter.putRegistry(QueryDelegate.Factory.Registry.class, queryDelegateFactoryRegistry);
		}
	}
	
	/**
	 * @since 3.2
	 */
	public static void initializeMappingFrom(ResourceSet resourceSet, String oclDelegateURI) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {		// Install the 'plugin' registrations
			EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
				new OCLInvocationDelegateMapping());
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
				new OCLSettingDelegateMapping());
			EValidator.ValidationDelegate.Registry.INSTANCE.put(oclDelegateURI,
				new OCLValidationDelegateMapping());
			QueryDelegate.Factory.Registry.INSTANCE.put(oclDelegateURI,
				new OCLQueryDelegateMapping());
		}
		if (resourceSet != null) {
			// Install a DelegateResourceSetAdapter to supervise local registries and resource post-loading
			DelegateResourceSetAdapter adapter = DelegateResourceSetAdapter.getAdapter(resourceSet);
			VirtualDelegateMapping virtualDelegateMapping = adapter.getRegistry(VirtualDelegateMapping.class);
			if (virtualDelegateMapping == null) {
				virtualDelegateMapping = CommonOptions.DEFAULT_DELEGATION_MODE;
			}
	
			// Install a local DelegateDomain.Factory
			DelegateDomain.Factory.Registry delegateDomainFactory = adapter.getRegistry(DelegateDomain.Factory.Registry.class);
			if (delegateDomainFactory != null) {
				delegateDomainFactory.put(oclDelegateURI, new OCLDelegateDomainFactory.Delegator(delegateDomainFactory));
			}
					
			// Install a local ValidationDelegate.Mapping
			ValidationDelegate.Factory.Registry validationDelegateFactoryRegistry = adapter.getRegistry(ValidationDelegate.Factory.Registry.class);
			if (validationDelegateFactoryRegistry != null) {
				validationDelegateFactoryRegistry.put(oclDelegateURI, new OCLValidationDelegateMapping(validationDelegateFactoryRegistry, virtualDelegateMapping));
			}
	
			// Install a local SettingDelegate.Mapping
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry settingDelegateFactoryRegistry = adapter.getRegistry(EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class);
			if (settingDelegateFactoryRegistry != null) {
				settingDelegateFactoryRegistry.put(oclDelegateURI, new OCLSettingDelegateMapping(settingDelegateFactoryRegistry, virtualDelegateMapping));
			}
	
			// Install a local InvocationDelegate.Mapping
			EOperation.Internal.InvocationDelegate.Factory.Registry invocationDelegateFactoryRegistry = adapter.getRegistry(EOperation.Internal.InvocationDelegate.Factory.Registry.class);
			if (invocationDelegateFactoryRegistry != null) {
				invocationDelegateFactoryRegistry.put(oclDelegateURI, new OCLInvocationDelegateMapping(invocationDelegateFactoryRegistry, virtualDelegateMapping));
			}
	
			// Install a local QueryDelegate.Mapping
			QueryDelegate.Factory.Registry queryDelegateFactoryRegistry = adapter.getRegistry(QueryDelegate.Factory.Registry.class);
			if (queryDelegateFactoryRegistry != null) {
				queryDelegateFactoryRegistry.put(oclDelegateURI, new OCLQueryDelegateMapping(queryDelegateFactoryRegistry, virtualDelegateMapping));
			}
		}
	}
	
	protected final String uri;
	protected final EPackage ePackage;
	protected final OCL ocl;
	
	/**
	 * Initializes me with my delegate URI and package.
	 * 
	 * @param delegateURI
	 *            the delegate namespace I handle
	 * @param ePackage
	 *            the package that I handle
	 * 
	 * @throws ParserException
	 *             if the operation's OCL body expression is invalid
	 */
	public OCLDelegateDomain(String delegateURI, EPackage ePackage) {
		this.uri = delegateURI;
		this.ePackage = ePackage;
		Resource res = ePackage.eResource();
		EPackage.Registry packageRegistry = null;
		if (res != null) {
			DelegateResourceAdapter.getAdapter(res);				// Ensure we get unloaded when the resource is unloaded
			ResourceSet resourceSet = res.getResourceSet();
			if (resourceSet != null) {
				// it's a dynamic package. Use the local package registry
				packageRegistry = resourceSet.getPackageRegistry();
			}
		}
		if (packageRegistry == null) {			// Deprecated compatibility
			packageRegistry = EPackage.Registry.INSTANCE;
		}
		EcoreEnvironmentFactory envFactory = null;
		EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(ePackage);
		if (eAnnotation != null) {
			EMap<String, String> details = eAnnotation.getDetails();
			String clsName = details.get(KEY_FOR_ENVIRONMENT_FACTORY_CLASS);
			if (clsName != null) {
				ClassLoader classLoader = ePackage.getClass().getClassLoader();
				try {
					Class<?> cls = classLoader.loadClass(clsName);
					Constructor<?> con = cls.getConstructor(EPackage.Registry.class);
					envFactory = (EcoreEnvironmentFactory) con.newInstance(packageRegistry);
				} catch (Exception e) {
					throw new WrappedException(
						"Error instantiating " + KEY_FOR_ENVIRONMENT_FACTORY_CLASS + " " + clsName + //$NON-NLS-1$ //$NON-NLS-2$
							": " + e.getMessage(), e); //$NON-NLS-1$
				}
			} else {
				String value = details.get(OCL_DELEGATES_USE_HIDDEN_OPPOSITES_KEY);
				if (value != null && Boolean.valueOf(value)) {
					envFactory = new EcoreEnvironmentFactoryWithHiddenOpposites(
						packageRegistry);
				}
			}
		}
		if (envFactory == null) {
			envFactory = new EcoreEnvironmentFactory(packageRegistry);
		}
		this.ocl = OCL.newInstance(envFactory);
	}

	public void dispose() {
		if (ocl != null) {
			ocl.dispose();
		}
	}

	public OCL getOCL() {
		return ocl;
	}
	
	public String getURI() {
		return uri;
	}

	public String toString() {
		return ePackage.getName() + " : " + getURI(); //$NON-NLS-1$
	}
}
