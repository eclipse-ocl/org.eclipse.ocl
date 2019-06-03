/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.uml.internal.utilities.UMLXMIID;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.uml2.common.util.DerivedEObjectEList;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLHandler;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLLoadImpl;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLResourceImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Assigns simple package/type/type-name xmi:ids to Packages, Types, Properties that do not already have them.
 * *.xmi inputs are handled so as to use local rather than registered OMG resources.
 */
@SuppressWarnings("restriction")
public class IdAssigner extends AbstractWorkflowComponent
{
	protected static final class LocalXMI2UMLResourceFactory extends XMI2UMLResourceFactoryImpl
	{
		public static final @NonNull LocalXMI2UMLResourceFactory INSTANCE = new LocalXMI2UMLResourceFactory();

		@Override
		public Resource createResourceGen(URI uri) {
			XMI2UMLResource result = new LocalXMI2UMLResource(uri);
			result.setEncoding(XMI2UMLResource.DEFAULT_ENCODING);
			return result;
		}
	}

	protected static final class LocalXMI2UMLResource extends XMI2UMLResourceImpl
	{
		protected LocalXMI2UMLResource(URI uri) {
			super(uri);
		}

		@Override
		protected XMLLoad createXMLLoad() {
			return new LocalXMI2UMLLoad(createXMLHelper());
		}
	}

	protected static final class LocalXMI2UMLLoad extends XMI2UMLLoadImpl
	{
		protected LocalXMI2UMLLoad(XMLHelper helper) {
			super(helper);
		}

		@Override
		protected DefaultHandler makeDefaultHandler() {
			return new LocalXMI2UMLHandler(resource, helper, options);
		}
	}

	protected static final class LocalXMI2UMLHandler extends XMI2UMLHandler
	{
		protected LocalXMI2UMLHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
			super(xmiResource, helper, options);
		}

		@Override
		protected void handleProxy(InternalEObject proxy, String uriLiteral) {
			if (uriLiteral.startsWith(XMI2UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI)) {
				URI uri = URI.createURI(uriLiteral);
				uriLiteral = uri.lastSegment() + "#" + uri.fragment();
			}
			super.handleProxy(proxy, uriLiteral);
		}
	}

	protected Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	protected Map<URI, URI> uriMapping = new HashMap<URI, URI>();
	protected boolean alphabeticize = false;
	protected boolean assignFlatIds = true;
	protected boolean normalizeEcore = false;
	protected URI normalizePrimitives = null;
	protected boolean removeEcoreStereotypes = false;
	protected boolean removeProfileApplications = false;

	/**
	 * Define a mapping from a source UML/CMOF file to a UML file with resolved assignments.
	 */
	public void addMapping(final Mapping uriMap) {
		final URI fromURI = URI.createPlatformResourceURI(uriMap.getFrom(), true);
		final URI toURI = URI.createPlatformResourceURI(uriMap.getTo(), true);
		uriMapping.put(fromURI, toURI);
	}

	@Override
	public void checkConfiguration(Issues issues) {
	}

	public String computeId(@NonNull StringBuilder s, @NonNull NamedElement namedElement) {
		EObject eContainer = namedElement.eContainer();
		if (eContainer instanceof NamedElement) {
			computeId(s, (NamedElement) eContainer);
			s.append("-");
		}
		String name = namedElement.getName();
		if (name == null) {
			Resource eResource = namedElement.eResource();
			if (eResource instanceof XMLResource) {
				UMLXMIID umlXMIid = new UMLXMIID((XMLResource) eResource);
				name = umlXMIid.doSwitch(namedElement);
			}
		}
		s.append(name);
		return s.toString();
	}

	public @NonNull ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = new ResourceSetImpl();
		}
		return resourceSet2;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		ResourceSetImpl resourceSet = (ResourceSetImpl) getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", LocalXMI2UMLResourceFactory.INSTANCE);
		List<EObject> explicitRootContents = new ArrayList<EObject>();
		Map<UMLResource, UMLResource> resourceMap = new HashMap<UMLResource, UMLResource>();
		for (URI fromURI : uriMapping.keySet()) {
			log.info("Assigning Ids from '" + fromURI + "'");
			URI toURI = uriMapping.get(fromURI);
			UMLResource fromResource = (UMLResource) resourceSet.getResource(fromURI, true);
			explicitRootContents.addAll(fromResource.getContents());
			EcoreUtil.resolveAll(fromResource);
			for (EObject eRoot : fromResource.getContents()) {
				if (eRoot instanceof org.eclipse.uml2.uml.Package) {
					org.eclipse.uml2.uml.Package umlPackage = (org.eclipse.uml2.uml.Package)eRoot;
					String uri = umlPackage.getURI();
					if (uri != null) {
						resourceSet.getURIResourceMap().put(URI.createURI(uri), fromResource);
					}
				}	// http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi
			}
			UMLResource toResource = (UMLResource) resourceSet.createResource(toURI);
			resourceMap.put(fromResource, toResource);
		}
		EcoreUtil.resolveAll(resourceSet);
		ResourceUtils.checkResourceSet(resourceSet);
		if (normalizeEcore || (normalizePrimitives != null)) {
			log.info("UMLEcoreNormalizing");
			Resource ecorePrimitiveTypes = resourceSet.getResource(URI.createURI(UMLResource.ECORE_PRIMITIVE_TYPES_LIBRARY_URI/*"pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml"*/), true);
			Resource pivotResource = (normalizePrimitives != null) ? resourceSet.getResource(normalizePrimitives, true) : null;
		    Map<EObject, Collection<EStructuralFeature.Setting>> map = EcoreUtil.ExternalCrossReferencer.find(explicitRootContents);
		    for (EObject eObject : map.keySet()) {
    			EObject eReplacement = null;
		    	Resource eResource = eObject.eResource();
		    	if ((pivotResource != null) && eResource == ecorePrimitiveTypes) {
		    		if (eObject instanceof org.eclipse.uml2.uml.PrimitiveType) {
		    			String className = ((org.eclipse.uml2.uml.PrimitiveType)eObject).getName();
		    			String replacementClassName;
		    			if ("EBooleanObject".equals(className) || "EBoolean".equals(className)) {
		    				replacementClassName = "Boolean";
		    			}
		    			else if ("EDoubleObject".equals(className) || "EDouble".equals(className)) {
		    				replacementClassName = "Real";
		    			}
		    			else if ("EIntegerObject".equals(className) || "EInt".equals(className)) {
		    				replacementClassName = "Integer";
		    			}
		    			else if ("EString".equals(className)) {
		    				replacementClassName = "String";
		    			}
		    			else {
		    				replacementClassName = null;
		    			}
		    			if (replacementClassName != null) {
		    				eReplacement = ((org.eclipse.uml2.uml.Package)pivotResource.getContents().get(0)).getOwnedType(replacementClassName);
		    			}
		    		}
		    	}
		    	else if (normalizeEcore) {
		    		EObject eRoot = EcoreUtil.getRootContainer(eObject);
			    	if (eRoot instanceof Model) {
				    	String uri = ((Model)eRoot).getURI();
				    	if (EcorePackage.eNS_URI.equals(uri)) {
				    		if (eObject instanceof org.eclipse.uml2.uml.Class) {
				    			String className = ((org.eclipse.uml2.uml.Class)eObject).getName();
				    			eReplacement = resourceSet.getEObject(URI.createURI(UMLResource.ECORE_METAMODEL_URI + "#" + className), true);
				    		}
				    	}
			    	}
		    	}
	    		if (eReplacement != null) {
		    		Collection<Setting> eSettings = map.get(eObject);
		    		assert eSettings != null;
					for (EStructuralFeature.Setting eSetting : eSettings) {
		    			if (eSetting instanceof DerivedEObjectEList<?>) {}
		    			else if (eSetting instanceof EcoreEList.UnmodifiableEList<?>) {}
		    			else {
		    				try {
			    				eSetting.set(eReplacement);
		    				}
			    			catch (Exception e) {
			    				log.error("eSetting failed for a " + eSetting.getClass().getName());
			    			}
		    			}
		    		}
	    		}
		    }
		}
		for (UMLResource fromResource : resourceMap.keySet()) {
			UMLResource toResource = resourceMap.get(fromResource);
			assert toResource != null;
			toResource.getContents().addAll(fromResource.getContents());
			if (assignFlatIds) {
				for (TreeIterator<EObject> tit = toResource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof org.eclipse.uml2.uml.Package) {
						NamedElement namedElement = (NamedElement) eObject;
						toResource.setID(eObject, namedElement.getName());
					}
	/*				else if (eObject instanceof Property) {
						Property property = (Property) eObject;
						Type type = property.getClass_();
						if (type != null) {
							String id = toResource.getID(eObject);
							if (assignIds || (id == null)) { // || !id.startsWith(type.getName())) {	// If it starts with type it may be a good name
								toResource.setID(eObject, computeId(new StringBuilder(), property));
							}
						}
					} */
				}
			}
			else {
	//			new UMLXMIID(toResource).assign();
				for (TreeIterator<EObject> tit = toResource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if ((eObject instanceof org.eclipse.ocl.pivot.Package) || (eObject instanceof Type) || (eObject instanceof Property) || (eObject instanceof Operation)) {
						@SuppressWarnings("null")@NonNull NamedElement namedElement = (NamedElement) eObject;
						toResource.setID(eObject, computeId(new StringBuilder(), namedElement));
					}
					else if ((eObject instanceof org.eclipse.uml2.uml.Package) || (eObject instanceof org.eclipse.uml2.uml.Type) || (eObject instanceof org.eclipse.uml2.uml.Property) || (eObject instanceof org.eclipse.uml2.uml.Operation)) {
						@SuppressWarnings("null")@NonNull NamedElement namedElement = (NamedElement) eObject;
						toResource.setID(eObject, computeId(new StringBuilder(), namedElement));
					}
	/*				else if (eObject instanceof Property) {
						Property property = (Property) eObject;
						Type type = property.getClass_();
						if (type != null) {
							String id = toResource.getID(eObject);
							if (assignIds || (id == null)) { // || !id.startsWith(type.getName())) {	// If it starts with type it may be a good name
								toResource.setID(eObject, computeId(new StringBuilder(), property));
							}
						}
					} */
				}
			}
		}
		if (removeEcoreStereotypes) {
			for (UMLResource toResource : resourceMap.values()) {
				List<EObject> unwanted = new ArrayList<EObject>();
				for (EObject eObject : toResource.getContents()) {
					if (!(eObject instanceof org.eclipse.uml2.uml.Element)) {
						unwanted.add(eObject);
					}
				}
				toResource.getContents().removeAll(unwanted);
			}
		}
		if (removeProfileApplications) {
			for (UMLResource toResource : resourceMap.values()) {
				if (toResource != null) {
					for (EObject eObject : toResource.getContents()) {
						if (eObject instanceof org.eclipse.uml2.uml.Package) {
							((org.eclipse.uml2.uml.Package)eObject).getProfileApplications().clear();
						}
					}
				}
			}
		}
		if (alphabeticize) {
			PackageAlphabetizer alphabeticizer = new PackageAlphabetizer();
			for (UMLResource toResource : resourceMap.values()) {
				if (toResource != null) {
					alphabeticizer.alphabeticize(toResource);
				}
			}
		}
		for (UMLResource toResource : resourceMap.values()) {
			log.info("Assigned Ids to '" + toResource.getURI() + "'");
			try {
				Map<Object, Object> saveOptions = getSaveOptions();
				XMIUtil.retainLineWidth(saveOptions, toResource);
				toResource.save(saveOptions);
			} catch (IOException e) {
				throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
			}
		}
	}

	protected Map<Object, Object> getSaveOptions() {
		Map<Object, Object> result = XMIUtil.createSaveOptions();
		result.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return result;
	}

	/**
	 * True to alphabeticize the UML packages and contents.
	 */
	public void setAlphabeticize(boolean alphabeticize) {
		this.alphabeticize = alphabeticize;
	}

	public void setAssignFlatIds(boolean assignFlatIds) {
		this.assignFlatIds = assignFlatIds;
	}

	/**
	 * True to normalize the UML models by replacing references to an ecore.uml by Ecore.metamodel.uml - Bug 453771.
	 */
	public void setNormalizeEcore(boolean normalizeEcore) {
		this.normalizeEcore = normalizeEcore;
	}

	/**
	 * Non-null to normalize the UML models by replacing references to a Ecore primitives by those prefixed by this URI.
	 */
	public void setNormalizePrimitives(String normalizePrimitives) {
		this.normalizePrimitives = URI.createPlatformResourceURI(normalizePrimitives, true);
	}

	/**
	 * True to remove Ecore stereotypes from the UML models making them OMG-like.
	 */
	public void setRemoveEcoreStereotypes(boolean removeEcoreStereotypes) {
		this.removeEcoreStereotypes = removeEcoreStereotypes;
	}

	/**
	 * True to remove all profile applications from the UML models making them OMG-like.
	 */
	public void setRemoveProfileApplications(boolean removeProfileApplications) {
		this.removeProfileApplications = removeProfileApplications;
	}

	public void setResourceSet(@NonNull ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}
