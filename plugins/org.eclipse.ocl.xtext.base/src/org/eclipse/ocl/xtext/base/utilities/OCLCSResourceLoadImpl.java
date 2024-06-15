package org.eclipse.ocl.xtext.base.utilities;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ContentTypeFirstResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Triple;

/**
 * OCLCSResourceLoadImpl is an experimental implementation of BaseCSResource that ensures that references to CS/ES elements
 * are resolved as equivalent AS references.
 *
 */
public class OCLCSResourceLoadImpl extends XMIResourceImpl implements BaseCSResource
{
	protected final @NonNull ASResourceFactory asResourceFactory;

	/**
	 * Creates an instance of the resource.
	 */
	public OCLCSResourceLoadImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri);
		this.asResourceFactory = asResourceFactory;
	}

	protected @NonNull ASResource createASResource(@NonNull ResourceSet asResourceSet) {
		URI uri = ClassUtil.nonNullState(getURI());
		URI asURI = getASURI(uri);
	//	if (uri.fileExtension().equals(PivotConstants.ESSENTIAL_OCL_FILE_EXTENSION)) {	// FIXME use csResource.getASResource(metamodelManager);
	//		return new TransientASResource(asResourceSet, asURI);
	//	}
		ASResource asResource = (ASResource) asResourceSet.getResource(asURI, false);
		if (asResource != null) {		// This happens for a *.ecore load for an OCLinEcore edit - see Bug 560196
			return asResource;
		}
		@SuppressWarnings("null")@NonNull Resource asResource2 = ContentTypeFirstResourceFactoryRegistry.createResource(asResourceSet, asURI, getASContentType());
		if (asResource2 instanceof ASResource) {
			((ASResource)asResource2).setSaveable(false);
		}
		return (ASResource) asResource2;
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
	//	return new CompleteOCLCS2AS(environmentFactory, this, asResource);
	//	throw new UnsupportedOperationException();
		return (CS2AS)asResourceFactory.createCS2AS(environmentFactory, this, asResource);
	}

	@Override
	public void dispose() {
	//	throw new UnsupportedOperationException();
		CS2AS cs2as = findCS2AS();
		if (cs2as != null) {
			cs2as.dispose();
		}
	}

	@Override
	public @Nullable CS2AS findCS2AS() {
//		throw new UnsupportedOperationException();
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory != null) {
			CSI2ASMapping csi2asMapping = CSI2ASMapping.basicGetCSI2ASMapping(environmentFactory);
			if (csi2asMapping != null) {
				CS2AS cs2as = csi2asMapping.getCS2AS(this);
				if (cs2as != null) {
					return cs2as;
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull String getASContentType() {
		return asResourceFactory.getContentType();
	}

	@Override
	public @NonNull ASResource getASResource() {
		CS2AS cs2as = getCS2AS();
		ASResource asResource = cs2as.getASResource();
		return asResource;
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return asResourceFactory;
	}

	@Override
	public @NonNull URI getASURI(@NonNull URI csURI) {
		return csURI.appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION);
	}

	@Override
	public @NonNull CS2AS getCS2AS() {
	//	ParserContext parserContext = getParserContext();
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		assert environmentFactory != null;
		CSI2ASMapping csi2asMapping = CSI2ASMapping.basicGetCSI2ASMapping(environmentFactory);
		if (csi2asMapping != null) {
			CS2AS cs2as = csi2asMapping.getCS2AS(this);
			if (cs2as != null) {
				return cs2as;
			}
		}
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		ClassLoader classLoader = getClass().getClassLoader();
		if (classLoader != null) {
			metamodelManager.addClassLoader(classLoader);
		}
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		@SuppressWarnings("null")@NonNull Registry resourceFactoryRegistry = asResourceSet.getResourceFactoryRegistry();
		initializeResourceFactory(resourceFactoryRegistry);
		ASResource asResource = createASResource(asResourceSet);
		CS2AS cs2as = null;
	//	if (parserContext instanceof ExtendedParserContext) {
	//		cs2as = ((ExtendedParserContext)parserContext).createCS2AS(this, asResource);
	//	}
		if (cs2as == null) {
			cs2as = createCS2AS(environmentFactory, asResource);
		}
		return cs2as;
	}

	@Override
	public @Nullable ParserContext getParserContext() {
		return null;
	}

	@Override
	public @NonNull ProjectManager getProjectManager() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setParserContext(@Nullable ParserContext parserContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProjectManager(@Nullable ProjectManager projectManager) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFrom(@NonNull ASResource asResource, @NonNull EnvironmentFactory environmentFactory) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(int index, int length, String newString) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Install any required extension/content-type registrations to enable AS Resources
	 * to be created satisfactorily.
	 */
	protected void initializeResourceFactory(Resource.Factory.@NonNull Registry resourceFactoryRegistry) {}

	@Override
	public boolean isDerived() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDerived(boolean isDerived) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createAndAddDiagnostic(Triple<EObject, EReference, INode> triple) {
	//	throw new UnsupportedOperationException();
		return;			// XXX
	}

	@Override
	public @NonNull AS2CS createAS2CS(@NonNull Map<@NonNull ? extends BaseCSResource, @NonNull ? extends ASResource> cs2asResourceMap,
			@NonNull EnvironmentFactoryInternal environmentFactory) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CS2AS getCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull ASResource asResource) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull String getEditorName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable NamedElement isPathable(@NonNull EObject element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull URI resolve(@NonNull URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(@NonNull IDiagnosticConsumer diagnosticsConsumer) {
		throw new UnsupportedOperationException();
	}

//	@Override
//	protected @NonNull XMIHelperImpl createXMLHelper() {
//		return new XMICSHelper(this);
//	}

/*	@Override
	public @NonNull Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> defaultSaveOptions2 = defaultSaveOptions;
		if (defaultSaveOptions2 == null) {
			defaultSaveOptions = defaultSaveOptions2 = XMIUtil.createPivotSaveOptions();
		}
		return defaultSaveOptions2;
	}

	@Override
	protected boolean useIDs() {		// XXX ???
		return true;
	} */
}
