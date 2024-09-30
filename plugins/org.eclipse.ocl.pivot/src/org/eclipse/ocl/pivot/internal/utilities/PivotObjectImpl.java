/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotObject;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;

public abstract class PivotObjectImpl extends EObjectImpl implements PivotObject
{
	/**
	 * @since 1.23
	 */
//	public static final @NonNull URI NO_UNLOAD_PROXY_URI = URI.createURI("null://unload/proxy#/");

	private @Nullable EObject esObject;		// always null for Model.

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		if (newContainer != null) {
			EObject oldContainer = eInternalContainer();
			assert (oldContainer == null) || oldContainer.eIsProxy() || (newContainer == oldContainer) || (oldContainer.eResource() == null);
		}
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

	@Override
	public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
		for (EObject eObject : eContents()) {
			if (eObject instanceof Nameable) {
				String name = ((Nameable)eObject).getName();
				if ((name != null) && name.equals(uriFragmentSegment)) {
					return eObject;
				}
			}
		}
		return super.eObjectForURIFragmentSegment(uriFragmentSegment);
	}

	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		assert proxy != null;
		StringBuilder s = null;
		if (ASResourceImpl.RESOLVE_PROXY.isActive()) {
			s = new StringBuilder();
			s.append(NameUtil.debugSimpleName(this) + " " + NameUtil.debugSimpleName(proxy) + " " + proxy.eProxyURI());
		}
		assert (eResource() != null) && (eResource().getResourceSet() != null) : "ResourceSet required for " + eClass().getName() + " "  + this;
		EObject resolvedProxy = super.eResolveProxy(proxy);
	/*	if (resolvedProxy instanceof Pivotable) {
			Resource resource = resolvedProxy.eResource();
			if (resource instanceof CSResource) {
				((CSResource)resource).getASResource();
			}
			resolvedProxy = ((Pivotable)resolvedProxy).getPivot();
		}
		else */ if (resolvedProxy instanceof EModelElement) {
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				try {
					resolvedProxy = ((EnvironmentFactoryInternalExtension)environmentFactory).getASOf(Element.class, resolvedProxy);
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (s != null) {
			s.append(" => " + NameUtil.debugSimpleName(resolvedProxy));
			ASResourceImpl.RESOLVE_PROXY.println(s.toString());
		}
		return resolvedProxy;
	}

	@Override
	public void eSetProxyURI(URI uri) {
		StringBuilder s = null;
		ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " " + NameUtil.debugSimpleName(this) + " " + uri);
		assert (uri == null) || (eContainer == null) || !uri.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION) : "Bad AS proxy " + uri;		// eContainer null during SAX parsing
		if ((uri != null) && uri.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION)) {
			getClass();		// XXX happens for testStandaloneExecution_execute_model_self_closure
		}
		super.eSetProxyURI(uri);
	}

	public @Nullable EObject getESObject() {
		assert !(this instanceof Model) : "no ESObject for Model";
		return esObject;
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getETarget() {
		return esObject;
	}

	@Override
	public Object getImage() {
		return null;
	}

	/**
	 * @since 1.23
	 */
	public @Nullable Object getReloadableEObjectOrURI() {
		InternalEObject eInternalContainer = eInternalContainer();
		assert eInternalContainer != null;
		Notifier esProxyTarget = null;
		EObject esObject = getESObject();
		if (esObject != null) {						// If there is a known ES
			esProxyTarget = esObject;				//  use es to create proxy
		}
		else {										// else need a CS
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
				return null;
			}
			// Look for a specific CS
			ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
			if (csi2asMapping == null) {
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMappings when proxifying " + NameUtil.debugSimpleName(this));
				return null;
			}
			EObject csElement = csi2asMapping.getCSElement(this);
			if ((csElement == null) && !(this instanceof Constraint)) {		// If a CS Element references that AS Element
				csElement = csi2asMapping.getCSElement(this);			// XXX happens for UML2Ecore2AS, and for the Java-implemented Ecore constraints
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMapping when proxifying " + NameUtil.debugSimpleName(this));
			}
			esProxyTarget = csElement;
			if (esProxyTarget == null) { // XXX && !environmentFactory.isDisposing()) {
				// XXX Else any old ES
				esProxyTarget = resolveESNotifier(environmentFactory.getCompleteModel());
			}
		}
		return esProxyTarget;
	}

	/**
	 * @since 1.23
	 */
	public @Nullable URI getReloadableURI() {
		Object reloadableEObjectOrURI = getReloadableEObjectOrURI();
		if (reloadableEObjectOrURI instanceof EObject) {
			return EcoreUtil.getURI((EObject)reloadableEObjectOrURI);
		}
		else if (reloadableEObjectOrURI instanceof URI) {
			return (URI)reloadableEObjectOrURI;
		}
		else {
			return null;
		}
	}

	@Deprecated // Use getESObject()
	public @Nullable EObject getTarget() {
		return esObject;
	}

	@Override
	public String getText() {
		return toString();
	}

	/**
	 * preUnload() is invoked to support the depth-first traversal of an ASResource contents from ASResourceImpl.doUnload().
	 * The traversal assigns proxies from the esObject that is then set to null. Other pivot artefacts are also reset.
	 *
	 * @since 1.23
	 *
	public void preUnload() {
	    assert eResource() != null;
		for (EObject eObject : eContents()) {
			if (eObject instanceof PivotObjectImpl) {		// Propagate setReloadableProxy through hierarchy to
				((PivotObjectImpl)eObject).preUnload();		// proxify the esObject before the eContainer() vanishes
			}
		}
		boolean expectedIsReloadableProxy = setReloadableProxy();
	/*	if (unloadProxifies()) {
	      URI appendFragment = eResource().getURI().appendFragment(eResource().getURIFragment(this));
			if ((appendFragment != null) && appendFragment.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION)) {
			      appendFragment = eResource().getURI().appendFragment(eResource().getURIFragment(this));
				getClass();		// XXX
			}
		} * /
		URI eProxyURI = eProxyURI();
		boolean eIsProxy = eIsProxy();
		boolean isRedundantProxy = eProxyURI == NO_UNLOAD_PROXY_URI;
		boolean isReloadableProxy = eIsProxy && !isRedundantProxy;

		assert eProxyURI != null;
		assert expectedIsReloadableProxy == isReloadableProxy;
		assert expectedIsReloadableProxy == !isRedundantProxy;

	/*	if (this instanceof Annotation) {
		//	assert !eIsProxy : NameUtil.debugSimpleName(this) + ":" + this;							// XXX
		//	System.out.println(NameUtil.debugSimpleName(this) + " " + this + " " + eIsProxy);
			if (!eIsProxy) {
				getClass();		// XXX
			}
		}
		else if (this instanceof DynamicBehavior) {
			assert isRedundantProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof org.eclipse.ocl.pivot.Class) {
			boolean isSynthetic = Orphanage.isOrphan((org.eclipse.ocl.pivot.Class)this);
			assert (isSynthetic ? isRedundantProxy : isReloadableProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Constraint) {
		//	assert isReloadableProxy : NameUtil.debugSimpleName(this);							// XXX
		}
		else if (this instanceof DynamicElement) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof DynamicValueSpecification) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof EnumerationLiteral) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Import) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Operation) {
			boolean isSynthetic = ((OperationImpl)this).isIsImplicit();
			assert (isSynthetic ? isRedundantProxy : isReloadableProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof org.eclipse.ocl.pivot.Package) {
			boolean isSynthetic = Orphanage.isOrphan((org.eclipse.ocl.pivot.Package)this);
			assert (isSynthetic ? isRedundantProxy : isReloadableProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Parameter) {
			Operation asOperation = ((Parameter)this).getOwningOperation();
			boolean isSynthetic = (asOperation instanceof OperationImpl) && ((OperationImpl)asOperation).isIsImplicit();
			assert (isSynthetic ? isRedundantProxy : isReloadableProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof ParameterVariable) {
			boolean isParameter = eContainmentFeature() == PivotPackage.Literals.EXPRESSION_IN_OCL__OWNED_PARAMETERS;
			assert (isParameter ? isReloadableProxy : isRedundantProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Precedence) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof ProfileApplication) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Property) {
			Property asProperty = (Property)this;
		//	boolean isSynthetic = Orphanage.isOrphan(asProperty) || asProperty.isIsDerived() || asProperty.isIsImplicit() || asProperty.isIsTransient() || asProperty.isIsVolatile();
			boolean isSynthetic = Orphanage.isOrphan(asProperty) || asProperty.isIsImplicit();
			assert (isSynthetic ? isRedundantProxy : isReloadableProxy) : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Slot) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof StereotypeExtender) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof TemplateParameter) {
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		//
		//	Non-leaf last
		//
		else if (this instanceof Namespace) {
			// Model / Region / State / Transition
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		else if (this instanceof Vertex) {
			// ConnectionPointReference / Pseudostate / State
			assert isReloadableProxy : NameUtil.debugSimpleName(this);
		}
		//
		//	Default - commonest case
		//
		else {
			// CollectionItem
			// CollectionRange
			// Comment
			// Detail
			// ExpressionInOCL
			// IteratorVariable
			// LetVariable
			// MapLiteralPart
			// OCLExpression
			// ResultVariable
			// ShadowPart
			// TemplateBinding
			// TemplateParameterSubstitution
			// TemplateSignature
			// TupleLiteralPart
			assert isRedundantProxy : NameUtil.debugSimpleName(this);
		} * /
	} */

	/**
	 * resolveESNotifier is called from resetESObject() to locate the ES Object that provides the Proxy URI.
	 * Derived classes may navigate the complete element to find an ESObject, or access the AS2CS mapping or
	 * bypass bloated AS such as Import.
	 *
	 * @since 1.23
	 */
	protected @Nullable Notifier resolveESNotifier(@NonNull CompleteModel completeModel) {
		return null;
	}

	public void setESObject(@NonNull EObject newTarget) {
		assert !(this instanceof Model) : "no ESObject for Model";
		esObject = newTarget;
	}

	@Deprecated // Use setESObject()
	public void setTarget(@Nullable EObject newTarget) {
		esObject = newTarget;
	}

	/**
	 * setUnloadedProxy is called at the end of preUnload() to assign a proxy URI so that a reload can reconstruct references.
	 * <br>
	 * For regular AS elements with an esObject the esObjects's URI is assigned. Else if there is an associated CS element the
	 * CS element's URI is assigned.
	 * <br>
	 * For elements that cannot be referenced such as Comment/Detail/OCLExpression the NO_UNLOAD_PROXY_URI is assigned to aid debugging; it is
	 * never used.
	 * <br>
	 * For elements without 1:1 CS2AS such as ParameterVariable the Parameter / context type's URI is assigned.
	 *
	 * @since 1.23
	 *
	@Deprecated
	protected boolean setReloadableProxy() {
		Notifier esProxyTarget = getReloadableNotifier();
		boolean isReloadableProxy = setReloadableProxy(esProxyTarget);
		if (esProxyTarget == null) {
			ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No ES or CS Object when proxifying " + NameUtil.debugSimpleName(this));
		}
		return isReloadableProxy;
	} */

	/**
	 * @since 1.23
	 *
	protected boolean setReloadableProxy(@Nullable Notifier notifier) {
		if ((notifier instanceof EObjectImpl) && ((EObject)notifier).eIsProxy()) {
			eSetProxyURI(((EObjectImpl)notifier).eProxyURI());
			return true;
		}
		else if (notifier instanceof Model) {
			URI uri = URI.createURI(((Model)notifier).getExternalURI());
			if (uri.fragment() == null) {
				uri = uri.appendFragment("/");
			}
			eSetProxyURI(uri);
			return true;
		}
		else if (notifier instanceof EObject) {
			URI uri = EcoreUtil.getURI((EObject)notifier);
			eSetProxyURI(uri);
			return true;
		}
		else if (notifier instanceof Resource) {
			URI uri = ((Resource)notifier).getURI();
			eSetProxyURI(uri);
			return true;
		}
		else {
			eSetProxyURI(NO_UNLOAD_PROXY_URI);
			return false;
		}
	//	this.esObject = null;
	} */

	/**
	 * Eliminate the esObject to facilitate leaking testing after a JUnit tearDown()
	 *
	 * @since 1.23
	 */
	public void tearDownESObject() {
		if ((esObject != null) && eIsProxy()) {
			esObject = null;
		}
	}

	@Deprecated /* @deprecated no longer used, moved to preUnload() */
	public void unloaded(@NonNull ASResource asResource) {
		assert esObject == null;
		esObject = null;
	}
}
