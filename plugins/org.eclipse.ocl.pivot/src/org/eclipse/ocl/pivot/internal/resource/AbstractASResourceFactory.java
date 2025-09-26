/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.EssentialOCLPrettyPrintVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EcoreTechnology;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.Technology;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.AS2MonikerVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverNormalizeVisitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.ToStringVisitor;
import org.eclipse.ocl.pivot.utilities.XMIUtil.IdResourceEntityHandler;

/**
 * AbstractASResourceFactory provides the abstract functionality for creating and maintaining
 * Pivot Abstract Syntax Resources via the standard EMF contentType/fileExtension Resource creation APIs.
 */
public abstract class AbstractASResourceFactory extends ResourceFactoryImpl implements ASResourceFactory
{
	/**
	 * @since 1.10
	 */
	@SuppressWarnings("unchecked")
	protected static <T extends AbstractASResourceFactory> @NonNull T getInstances(@NonNull String contentType, @NonNull String asFileExtension, @Nullable String csFileExtension, @NonNull Class<? extends T> resourceFactoryClass) {
		@Nullable T newInstance = null;
		T contentTypeInstance;
		Map<String, Object> contentTypeToFactoryMap = Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap();
		Object object1 = contentTypeToFactoryMap.get(contentType);
		if (object1 instanceof Resource.Factory.Descriptor) {
			contentTypeInstance = (T)((Resource.Factory.Descriptor)object1).createFactory();	// Create the registered singleton
		}
		else if (object1 != null) {
			contentTypeInstance = (T)object1;													// Reuse as our own singleton
		}
		else  {
			try {
				newInstance = contentTypeInstance = resourceFactoryClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}													// Create our own singleton
			contentTypeToFactoryMap.put(contentType, contentTypeInstance);
		}
		T extensionInstance;
		Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		Object object2 = extensionToFactoryMap.get(asFileExtension);
		if (object2 instanceof Resource.Factory.Descriptor) {
			extensionInstance = (T)((Resource.Factory.Descriptor)object2).createFactory();	// Create the registered singleton
		}
		else if (object2 != null) {
			extensionInstance = (T)object2;													// Reuse as our own singleton
		}
		else if (newInstance != null) {
			extensionInstance = newInstance;													// Reuse as our own singleton
		}
		else  {
			try {
				newInstance = extensionInstance = resourceFactoryClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}													// Create our own singleton
			extensionToFactoryMap.put(asFileExtension, extensionInstance);
		}
		assert contentTypeInstance != null;
		contentTypeInstance.install(csFileExtension, null);
//		ASResourceFactoryRegistry.INSTANCE.addASResourceFactory(contentType, csFileExtension, null, contentTypeInstance);
		assert contentTypeInstance != null;
		return contentTypeInstance;
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

	/**
	 * The EMF ResourceFactoryRegistry ContentTypeToFactoryMap key at which this ASResourceFactory is stored.
	 * @since 7.0
	 */
	protected final @NonNull String asContentType;

	/**
	 * The EMF ResourceFactoryRegistry ExtensionToFactoryMap key at which this ASResourceFactory is stored.
	 * A null key suppresses ExtensionToFactoryMap registrations for the many ASResourceFactory instances that
	 * share the *.oclas extension.
	 *
	 * FIXME can the many *.oclas ASRefesourceFatories be folded into one exploiting CSawareASResourceFactory ?
	 *
	 * @since 7.0
	 */
	protected final @Nullable String asFileExtension;

	/**
	 * @since 1.10
	 */
	protected AbstractASResourceFactory(@NonNull String asContentType, @Nullable String asFileExtension) {
		this.asContentType = asContentType;
		this.asFileExtension = asFileExtension;
	}

	@Override
	public @NonNull ASResourceFactory basicGetASResourceFactory() {
		return this;
	}

	/**
	 * @since 1.10
	 */
	protected void configureASResourceSet(@NonNull ResourceSet asResourceSet, @NonNull ResourceSet csResourceSet) {
		Resource.Factory.Registry resourceFactoryRegistry = asResourceSet.getResourceFactoryRegistry();
		resourceFactoryRegistry.getContentTypeToFactoryMap().put(asContentType, this);
		if (asFileExtension != null) {
			ASResourceFactory extensionASResourceFactory = createResourceSetAwareASResourceFactory(csResourceSet);
			if (extensionASResourceFactory == null) {
				extensionASResourceFactory = this;
			}
			resourceFactoryRegistry.getExtensionToFactoryMap().put(asFileExtension, extensionASResourceFactory);
		}
	}

	/**
	 * @since 1.10
	 */
	protected void configureCSResourceSet(@NonNull ResourceSet csResourceSet) {
		Resource.Factory.Registry resourceFactoryRegistry = csResourceSet.getResourceFactoryRegistry();
		resourceFactoryRegistry.getContentTypeToFactoryMap().put(asContentType, this);
	}

	protected void configureResource(@NonNull ASResource asResource) {
		asResource.setEncoding(ASResource.DEFAULT_ENCODING);
		Map<Object, Object> defaultSaveOptions = asResource.getDefaultSaveOptions();
		defaultSaveOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		defaultSaveOptions.put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
		defaultSaveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		defaultSaveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		defaultSaveOptions.put(XMLResource.OPTION_RESOURCE_ENTITY_HANDLER, new IdResourceEntityHandler());
	}

	@Override
	public void configureResourceFactoryRegistry(@NonNull ResourceSet resourceSet) {}

	@Override
	public void configureResourceSets(@Nullable ResourceSet asResourceSet, @NonNull ResourceSet csResourceSet) {
		if (asResourceSet != null) {
			configureASResourceSet(asResourceSet, csResourceSet);
		}
		configureCSResourceSet(csResourceSet);
	}

	@Override
	public @NonNull AS2MonikerVisitor createAS2MonikerVisitor(@NonNull AS2Moniker as2moniker) {
		return new AS2MonikerVisitor(as2moniker);
	}

	@Override
	public @NonNull ASSaverNormalizeVisitor createASSaverNormalizeVisitor(@NonNull ASSaver asSaver) {
		return new ASSaverNormalizeVisitor(asSaver);
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

	/**
	 * @since 1.10
	 */
	protected @Nullable ASResourceFactory createResourceSetAwareASResourceFactory(@NonNull ResourceSet csResourceSet) {
		return this;
	}

	@Override
	public @NonNull TemplateParameterSubstitutionVisitor createTemplateParameterSubstitutionVisitor(@NonNull EnvironmentFactory environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
		// assert selfTypeValue == null;			// Bug 580791 Enforcing redundant argument
		return environmentFactory.createTemplateParameterSubstitutionVisitor(selfType, selfTypeValue);
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
				assert !element.eIsProxy();
			/*	if (element.eIsProxy()) {
					assert false;			// XXX XXX
					CSResource csResource = (CSResource)eObject.eResource();			// XXX cast
					ASResource asResource = environmentFactory.reload(csResource);
					element = ((Pivotable)eObject).getPivot();
					if (element == null) {
						return null;
					}
				} */
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

	/**
	 * @since 1.10
	 */
	protected @Nullable String getASfileExtension() {
		return asFileExtension;
	}

	@Override
	public @NonNull ASResourceFactory getContribution() {
		return this;
	}

	@Override
	public @NonNull String getContentType() {
		return asContentType;
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
		Resource asResource;
		if (resource instanceof ASResource) {
			asResource = resource;
		}
		else {
			ICS2AS cs2as = ((CSResource)resource).getCS2AS(environmentFactory);
			asResource = cs2as.getASResource();
		}
		List<@NonNull EObject> contents = asResource.getContents();
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

	/**
	 * Install this ASResourceFactory within the ASResourceFactoryRegistry.INSTANCE wrt contentType,
	 * nonASextension and resourceClassName. The resourceClassName complexity is solely for the benefit
	 * of UML which may not be loaded so we cannot use UML classes. See Bug 526813.
	 */
	protected void install(@Nullable String nonASextension, @Nullable String resourceClassName) {
		ASResourceFactoryRegistry.INSTANCE.addASResourceFactory(asContentType, nonASextension, resourceClassName, this);
	}

	@Override
	public boolean isCompatibleResource(@NonNull Resource newResource, @NonNull Resource oldResource) {
		return false;
	}

	@Override
	public void registerMetaPackages(@NonNull CompleteModel completeModel) {
/*		CompletePackage libraryCompletePackage = completeModel.getCompletePackage(PivotConstants.METAMODEL_ID, OCLstdlibPackage.eINSTANCE.getNsPrefix(), PivotConstants.METAMODEL_NAME);
		completeModel.registerCompletePackageContribution(libraryCompletePackage, OCLstdlibPackage.eINSTANCE.getNsURI());
		//
		CompletePackage pivotCompletePackage = completeModel.getCompletePackage(PivotConstants.METAMODEL_ID, PivotPackage.eINSTANCE.getNsPrefix(), PivotConstants.METAMODEL_NAME);
		completeModel.registerCompletePackageContribution(pivotCompletePackage, PivotPackage.eINSTANCE.getNsURI()); */
	}

	@Override
	public @NonNull String toString() {
		return "«basic» " + asContentType;
	}
}
