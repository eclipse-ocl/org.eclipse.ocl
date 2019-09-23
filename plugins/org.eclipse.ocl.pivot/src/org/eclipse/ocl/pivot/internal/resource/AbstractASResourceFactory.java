/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.EssentialOCLPrettyPrintVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.AS2XMIid;
import org.eclipse.ocl.pivot.internal.utilities.EcoreTechnology;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.Technology;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.AS2MonikerVisitor;
import org.eclipse.ocl.pivot.utilities.AS2XMIidVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverLocateVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverNormalizeVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverResolveVisitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.ToStringVisitor;

/**
 * AbstractASResourceFactory provides the abstract functionality for creating and maintaining
 * OCL Abstract Syntax Resources.
 */
public abstract class AbstractASResourceFactory extends ResourceFactoryImpl implements ASResourceFactory.ASResourceFactoryExtension2
{
	/**
	 * A DelegatingASResourceFactory may be installed in the local ResourceFactoryRegistry to enable a miss
	 * on an AS URI to be redirected to the CS URI re-using the CS ResourceSet.
	 *
	 * @since 1.10
	 */
	protected static final class DelegatingASResourceFactory implements ASResourceFactory.ASResourceFactoryExtension2
	{
		private final @NonNull AbstractASResourceFactory abstractASResourceFactory;
		private final @NonNull ResourceSet csResourceSet;
		private final @NonNull String csFileExtension;

		protected DelegatingASResourceFactory(@NonNull AbstractASResourceFactory abstractASResourceFactory, @NonNull ResourceSet csResourceSet, @NonNull String csFileExtension) {
			this.abstractASResourceFactory = abstractASResourceFactory;
			this.csResourceSet = csResourceSet;
			this.csFileExtension = csFileExtension;
		}

		@Override
		public @Nullable ASResourceFactory basicGetASResourceFactory() {
			throw new UnsupportedOperationException();
		//	return abstractASResourceFactory;
		}

		@Override
		public void configure(@NonNull ResourceSet resourceSet) {
			abstractASResourceFactory.configure(null, resourceSet);
		}

		@Override
		public void configure(@Nullable ResourceSet asResourceSet, @NonNull ResourceSet csResourceSet) {
			abstractASResourceFactory.configure(asResourceSet, csResourceSet);
		}

		@Override
		public @NonNull AS2MonikerVisitor createAS2MonikerVisitor(@NonNull AS2Moniker as2moniker) {
			return abstractASResourceFactory.createAS2MonikerVisitor(as2moniker);
		}

		@Override
		public @NonNull AS2XMIidVisitor createAS2XMIidVisitor(@NonNull AS2XMIid as2id) {
			return abstractASResourceFactory.createAS2XMIidVisitor(as2id);
		}

		@Override
		public @NonNull ASSaverLocateVisitor createASSaverLocateVisitor(@NonNull ASSaver saver) {
			return abstractASResourceFactory.createASSaverLocateVisitor(saver);
		}

		@Override
		public @NonNull ASSaverNormalizeVisitor createASSaverNormalizeVisitor(@NonNull ASSaver saver) {
			return abstractASResourceFactory.createASSaverNormalizeVisitor(saver);
		}

		@Override
		public @NonNull ASSaverResolveVisitor createASSaverResolveVisitor(@NonNull ASSaver saver) {
			return abstractASResourceFactory.createASSaverResolveVisitor(saver);
		}

		@Override
		public @NonNull EnvironmentFactoryInternal createEnvironmentFactory(@NonNull ProjectManager projectManager) {
			return abstractASResourceFactory.createEnvironmentFactory(projectManager);
		}

		@Override
		public @NonNull LUSSIDs createLUSSIDs(@NonNull ASResource asResource, @NonNull Map<@NonNull Object, @Nullable Object> options) {
			return abstractASResourceFactory.createLUSSIDs(asResource, options);
		}

		@Override
		public @NonNull PrettyPrintVisitor createPrettyPrintVisitor(@NonNull PrettyPrinter prettyPrinter) {
			return abstractASResourceFactory.createPrettyPrintVisitor(prettyPrinter);
		}

		@Override
		public Resource createResource(URI uri) {
			if (uri.isPlatform() || uri.isFile() || uri.isArchive()) { // not http:
				URIConverter uriConverter = csResourceSet.getURIConverter();
				//
				//	If *.xxxas exists use it.
				//
				if (!uriConverter.exists(uri, null)) {	// NB this expects a (Standalone)PlatformURIHandlerImpl to be installed
					URI csURI = uri.trimFileExtension().appendFileExtension(csFileExtension);
					//
					//	If *.xxx exists use it.
					//
					if (uriConverter.exists(csURI, null)) {	// NB this expects a (Standalone)PlatformURIHandlerImpl to be installed
						CSResource csResource = (CSResource) csResourceSet.createResource(csURI);
						ASResource asResource = csResource.getASResource();
						abstractASResourceFactory.configureResource(asResource);
						return asResource;
					}
				}
			}
			return abstractASResourceFactory.createResource(uri);
		}

		@Override
		public @NonNull TemplateParameterSubstitutionVisitor createTemplateParameterSubstitutionVisitor(
				@NonNull EnvironmentFactory environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
			return abstractASResourceFactory.createTemplateParameterSubstitutionVisitor(environmentFactory, selfType, selfTypeValue);
		}

		@Override
		public @NonNull ToStringVisitor createToStringVisitor(@NonNull StringBuilder s) {
			return abstractASResourceFactory.createToStringVisitor(s);
		}

		@Override
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();		// Not forbidden but really not as intended
		//	return abstractASResourceFactory.equals(obj);
		}

		@Override
		public @NonNull ASResourceFactory getASResourceFactory() {
			return abstractASResourceFactory;
		}

		@Override
		public <T extends Element> @Nullable T getASElement(@NonNull EnvironmentFactoryInternal environmentFactory,
				@NonNull Class<T> requiredClass, @NonNull EObject eObject) throws ParserException {
			return abstractASResourceFactory.getASElement(environmentFactory, requiredClass, eObject);
		}

		@Override
		public @NonNull ASResourceFactory getContribution() {
			return abstractASResourceFactory.getContribution();
		}

		@Override
		public @NonNull String getContentType() {
			return abstractASResourceFactory.getContentType();
		}

		@Override
		public @Nullable EOperation getEOperation(@NonNull ASResource asResource, @NonNull EObject eObject) {
			return abstractASResourceFactory.getEOperation(asResource, eObject);
		}

		@Override
		public @Nullable EReference getEReference(@NonNull ASResource asResource, @NonNull EObject eObject) {
			return abstractASResourceFactory.getEReference(asResource, eObject);
		}

		@Override
		public @Nullable String getMetamodelNsURI(@NonNull EPackage ePackage) {
			return abstractASResourceFactory.getMetamodelNsURI(ePackage);
		}

		@Override
		public @Nullable URI getPackageURI(@NonNull EObject eObject) {
			return abstractASResourceFactory.getPackageURI(eObject);
		}

		@Override
		public @Nullable Integer getPriority() {
			return abstractASResourceFactory.getPriority();
		}

		@Override
		public @Nullable String getResourceClassName() {
			return abstractASResourceFactory.getResourceClassName();
		}

		@Override
		public @NonNull Technology getTechnology() {
			return abstractASResourceFactory.getTechnology();
		}

		@Override
		public int hashCode() {
			throw new UnsupportedOperationException();		// Not forbidden but really not as intended
		//	return abstractASResourceFactory.hashCode();
		}

		@Override
		public @Nullable Element importFromResource(@NonNull EnvironmentFactoryInternal environmentFactory,
				@NonNull Resource resource, @Nullable URI uri) throws ParserException {
			return abstractASResourceFactory.importFromResource(environmentFactory, resource, uri);
		}

		@Override
		public void initializeEValidatorRegistry(org.eclipse.emf.ecore.EValidator.@NonNull Registry eValidatorRegistry) {
			abstractASResourceFactory.initializeEValidatorRegistry(eValidatorRegistry);
		}

		@Override
		public boolean isCompatibleResource(@NonNull Resource newResource, @NonNull Resource oldResource) {
			return abstractASResourceFactory.isCompatibleResource(newResource, oldResource);
		}

		@Override
		public @NonNull String toString() {
			return abstractASResourceFactory.toString();
		}
	}

	public static void installContentHandler(int priority, @NonNull ContentHandler contentHandler) {
		List<ContentHandler> contentHandlers = ContentHandler.Registry.INSTANCE.get(priority);
		if (contentHandlers == null) {
			contentHandlers = new ArrayList<ContentHandler>();
			ContentHandler.Registry.INSTANCE.put(priority, contentHandlers);
		}
		if (!contentHandlers.contains(contentHandler)) {
			contentHandlers.add(contentHandler);
		}
	}

	protected final @NonNull String contentType;
	private final @Nullable String asFileExtension;
	private final @Nullable String csFileExtension;

	@Deprecated /* @deprecated use null asFileExtension argument */
	protected AbstractASResourceFactory(@NonNull String contentType) {
		this(contentType, null, null);
	}

	/**
	 * @since 1.10
	 */
	protected AbstractASResourceFactory(@NonNull String contentType, @Nullable String asFileExtension, @Nullable String csFileExtension) {
		this.contentType = contentType;
		this.asFileExtension = asFileExtension;
		this.csFileExtension = csFileExtension;
	}

	@Override
	public @NonNull ASResourceFactory basicGetASResourceFactory() {
		return this;
	}

	@Override
	public void configure(@NonNull ResourceSet resourceSet) {
		Resource.Factory.Registry resourceFactoryRegistry = resourceSet.getResourceFactoryRegistry();
		resourceFactoryRegistry.getContentTypeToFactoryMap().put(contentType, this);
		if (asFileExtension != null) {
			resourceFactoryRegistry = resourceSet.getResourceFactoryRegistry();
			resourceFactoryRegistry.getExtensionToFactoryMap().put(asFileExtension, this);
		}
	}

	@Override
	public void configure(@Nullable ResourceSet asResourceSet, @NonNull ResourceSet csResourceSet) {
		if (asResourceSet != null) {
//			configure(asResourceSet);
			Resource.Factory.Registry resourceFactoryRegistry = asResourceSet.getResourceFactoryRegistry();
			resourceFactoryRegistry.getContentTypeToFactoryMap().put(contentType, this);
			if ((asFileExtension != null) && (csFileExtension != null)) {
				DelegatingASResourceFactory contextualResourceFactory = new DelegatingASResourceFactory(this, csResourceSet, csFileExtension);
				resourceFactoryRegistry.getExtensionToFactoryMap().put(asFileExtension, contextualResourceFactory);
			}
		}
		configure(csResourceSet);
	}

	protected void configureResource(@NonNull ASResource asResource) {
		asResource.setEncoding(ASResource.DEFAULT_ENCODING);
		Map<Object, Object> defaultSaveOptions = asResource.getDefaultSaveOptions();
		defaultSaveOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		defaultSaveOptions.put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
		defaultSaveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		defaultSaveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
	}

	@Override
	public @NonNull AS2MonikerVisitor createAS2MonikerVisitor(@NonNull AS2Moniker as2moniker) {
		return new AS2MonikerVisitor(as2moniker);
	}

	@SuppressWarnings("deprecation")
	@Override
	public org.eclipse.ocl.pivot.utilities.@NonNull AS2XMIidVisitor createAS2XMIidVisitor(org.eclipse.ocl.pivot.internal.utilities.@NonNull AS2XMIid as2id) {
		return new org.eclipse.ocl.pivot.utilities.AS2XMIidVisitor(as2id);
	}

	@Override
	public @NonNull ASSaverLocateVisitor createASSaverLocateVisitor(@NonNull ASSaver saver) {
		return new ASSaverLocateVisitor(saver);
	}

	@Override
	public @NonNull ASSaverNormalizeVisitor createASSaverNormalizeVisitor(@NonNull ASSaver saver) {
		return new ASSaverNormalizeVisitor(saver);
	}

	@Override
	public @NonNull ASSaverResolveVisitor createASSaverResolveVisitor(@NonNull ASSaver saver) {
		return new ASSaverResolveVisitor(saver);
	}

	@Override
	public @NonNull EnvironmentFactoryInternal createEnvironmentFactory(@NonNull ProjectManager projectManager) {
		return new PivotEnvironmentFactory(projectManager, null);
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @NonNull LUSSIDs createLUSSIDs(@NonNull ASResource asResource, @NonNull Map<@NonNull Object, @Nullable Object> options) {
		return new PivotLUSSIDs(asResource, options);
	}

	@Override
	public @NonNull PrettyPrintVisitor createPrettyPrintVisitor(@NonNull PrettyPrinter prettyPrinter) {
		return new EssentialOCLPrettyPrintVisitor(prettyPrinter);
	}

	@Override
	public @NonNull TemplateParameterSubstitutionVisitor createTemplateParameterSubstitutionVisitor(@NonNull EnvironmentFactory environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
		return new TemplateParameterSubstitutionVisitor((EnvironmentFactoryInternal) environmentFactory, selfType, selfTypeValue);
	}

	@Override
	public @NonNull ToStringVisitor createToStringVisitor(@NonNull StringBuilder s) {
		return new ToStringVisitor(s);
	}

	/**
	 * Creates an instance of the resource.
	 */
	@Override
	public Resource createResource(URI uri) {
		assert uri != null;
		ASResource result = new ASResourceImpl(uri, this);
		configureResource(result);
		return result;
	}

	@Override
	public @Nullable <T extends Element> T getASElement(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Class<T> requiredClass, @NonNull EObject eObject) throws ParserException {
		if (eObject instanceof Pivotable) {
			Element element = ((Pivotable)eObject).getPivot();
			if (element != null) {
				if (!requiredClass.isAssignableFrom(element.getClass())) {
					throw new ClassCastException(element.getClass().getName() + " is not assignable to " + requiredClass.getName());
				}
				@SuppressWarnings("unchecked")
				T castElement = (T) element;
				return castElement;
			}
		}
		return null;
	}

	@Override
	public @NonNull ASResourceFactory getContribution() {
		return this;
	}

	@Override
	public @NonNull String getContentType() {
		return contentType;
	}

	@Override
	public @Nullable EOperation getEOperation(@NonNull ASResource asResource, @NonNull EObject eObject) {
		return null;
	}

	@Override
	public @Nullable EReference getEReference(@NonNull ASResource asResource, @NonNull EObject eObject) {
		return null;
	}

	@Override
	public @Nullable String getMetamodelNsURI(@NonNull EPackage ePackage) {
		return null;
	}

	@Override
	public @Nullable URI getPackageURI(@NonNull EObject eObject) {
		return null;
	}

	@Override
	public @Nullable Integer getPriority() {
		return null;
	}

	@Override
	public @Nullable String getResourceClassName() {
		return null;
	}

	@Override
	public @NonNull Technology getTechnology() {
		return EcoreTechnology.INSTANCE;
	}

	@Override
	public @Nullable Element importFromResource(@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull Resource resource, @Nullable URI uri) throws ParserException {
		Resource asResource = resource instanceof ASResource ? resource : ((CSResource)resource).getASResource();
		List<EObject> contents = asResource.getContents();
		if (contents.size() <= 0) {
			return null;
		}
		if (uri == null) {
			return (Element) contents.get(0);
		}
		String fragment = uri.fragment();
		if (fragment == null) {
			return (Element) contents.get(0);
		}
		else {
			EObject eObject = asResource.getEObject(fragment);
			if (eObject instanceof Element) {
				return (Element)eObject;
			}
			return null;
		}
	}

	@Override
	public void initializeEValidatorRegistry(EValidator.@NonNull Registry eValidatorRegistry) {}

	protected void install(@Nullable String oclasExtension, @Nullable String resourceClassName) {
		ASResourceFactoryRegistry.INSTANCE.addASResourceFactory(contentType, oclasExtension, resourceClassName, this);
	}

	@Override
	public boolean isCompatibleResource(@NonNull Resource newResource, @NonNull Resource oldResource) {
		return false;
	}

	@Override
	public @NonNull String toString() {
		return contentType;
	}
}
