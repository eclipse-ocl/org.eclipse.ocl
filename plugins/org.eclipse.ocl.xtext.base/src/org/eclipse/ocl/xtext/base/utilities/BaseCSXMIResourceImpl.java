/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.context.AbstractParserContext;
import org.eclipse.ocl.pivot.internal.context.Base2ASConversion;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ContentTypeFirstResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ICS2AS;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;
import org.eclipse.xtext.util.Triple;

/**
 * The BaseCSXMIResourceImpl implementation of BaseCSResource that ensures that loading resolves references to CS/ES elements
 * to equivalent AS references and conversely ensures that saving replaces AS references by CS/ES references.
 */
public abstract class BaseCSXMIResourceImpl extends XMIResourceImpl implements BaseCSResource
{
	/**
	 * CSXMISaveHelper overloads getHREF to persist references to the internal AS elements to their persistable CS/ES equivalents.
	 */
	protected static final class CSXMISaveHelper extends XMIHelperImpl
	{
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;

		protected CSXMISaveHelper(XMLResource resource) {
			super(resource);
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert environmentFactory != null : "No EnvironmentFactory when CS-saving " + NameUtil.debugSimpleName(this);
			this.environmentFactory = environmentFactory;
		}

		@Override
		public String getHREF(EObject obj) {
			if (obj instanceof ElementImpl) {
				//	Use known ES
				if (obj instanceof Model) {
					return ((Model)obj).getExternalURI();
				}
				EObject esObject = ((ElementImpl)obj).getESObject();
				if (esObject != null) {
					return super.getHREF(esObject);
				}
				//	Look for complete ES
				CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
				if (obj instanceof org.eclipse.ocl.pivot.Package) {
					CompletePackageInternal completePackage = completeModel.getCompletePackage((org.eclipse.ocl.pivot.Package)obj);
					for (org.eclipse.ocl.pivot.Package asPackage : completePackage.getPartialPackages()) {
						esObject = asPackage.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					}
				}
				else if (obj instanceof org.eclipse.ocl.pivot.Class) {
					CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					}
				}
				else if (obj instanceof Operation) {
					Operation asOperation = (Operation)obj;
					CompleteClassInternal completeClass = completeModel.getCompleteClass(asOperation.getOwningClass());
					List<org.eclipse.ocl.pivot.Class> partialClasses = completeClass.getPartialClasses();
					Iterable<@NonNull Operation> asOverloads = completeClass.getOperationOverloads(asOperation);
					if (asOverloads != null) {
						for (Operation asOverload : asOverloads) {
							if (partialClasses.contains(asOverload.getOwningClass())) {
								esObject = asOverload.getESObject();
								if (esObject != null) {
									return super.getHREF(esObject);
								}
							}
						}
					}
				}
				else if (obj instanceof Property) {
					CompleteClassInternal completeClass = completeModel.getCompleteClass(((Property)obj).getOwningClass());
					Iterable<@NonNull Property> asProperties = completeClass.getProperties((Property)obj);
					if (asProperties != null) {
						for (Property asProperty : asProperties) {
							esObject = asProperty.getESObject();
							if (esObject != null) {
								return super.getHREF(esObject);
							}
						}
					}
				}
				// Look for a specific CS
				ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
				if (csi2asMapping == null) {
					ASResourceImpl.PROXIES.println("No CSI2ASMapping when CS-saving  " + NameUtil.debugSimpleName(this));
				}
				else {
					EObject csElement = csi2asMapping.getCSElement((ElementImpl)obj);
					if (csElement != null) {						// XXX never happens CS is never externally referenced
						return super.getHREF(csElement);			// e.g. CS-defined implementation without Ecore
					}
				}
			}
			return super.getHREF(obj);								// e.g. built-in oclstdlib-defined implementation without Ecore
		}

		@Override
		public Object getValue(EObject obj, EStructuralFeature f) {
			Object value = super.getValue(obj, f);
			if (value instanceof ParameterVariable) {
				//
				//	ParameterVariable has no distinct CS equivalent so must reference its Parameter.
				//
				value = ((ParameterVariable)value).getRepresentedParameter();
			}
			return value;
		}
	}

	/**
	 * The CSXMISave ensures that a CS2AS conversion manager is available and that AS resources have AS xmi:ids.
	 */
	protected static class CSXMISave extends XMISaveImpl
	{
		public CSXMISave(@NonNull XMLHelper xmlHelper) {
			super(xmlHelper);
		}

		@Override
		protected void init(XMLResource csResource, Map<?, ?> options) {
			BaseCSResource castCSResource = (BaseCSResource)((OCLCSResourceSaveImpl)csResource).getCSResource();				// XXX cast
			EnvironmentFactory environmentFactory = castCSResource.getEnvironmentFactory();
			ICS2AS cs2as = castCSResource.getCS2AS(environmentFactory);
			Map<@NonNull Object, @Nullable Object> saveOptions = new HashMap<>();
			if (options != null) {
				for (Object key : options.keySet()) {
					saveOptions.put(String.valueOf(key), options.get(key));
				}
				// XXX cf PivotSaveImpl
			}
			ASResource asResource = cs2as.getASResource();
			ResourceSet asResourceSet = asResource.getResourceSet();
			if (asResourceSet != null) {
				AS2ID.assignIds(asResourceSet.getResources(), saveOptions);
			}
			else {
				AS2ID.assignIds(asResource, saveOptions);
			}
			super.init(csResource, options);
		}
	}

	/**
	 * A PreParsedContext is used by a directly loaded CS Resource to provide the minimal EnvironmentFactory related functionality.
	 * The inappropriate complexities of Xtext parsing are not supported.
	 */
	protected static final class PreParsedContext extends AbstractParserContext
	{
		public PreParsedContext(@NonNull EnvironmentFactoryInternal environmentFactory) {
			super(environmentFactory, null);
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull CSResource createBaseResource(@Nullable String expression) throws IOException, ParserException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void initialize(@NonNull Base2ASConversion conversion, @NonNull ExpressionInOCL expression) {
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull ExpressionInOCL parse(@Nullable EObject owner, @NonNull String expression) throws ParserException {
			throw new UnsupportedOperationException();
		}
	}

	protected final @NonNull ASResourceFactory asResourceFactory;
	private @Nullable ParserContext parserContext = null;
	private @Nullable CS2AS cs2as = null;

	/**
	 * Creates an instance of the resource.
	 */
	protected BaseCSXMIResourceImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri);
		this.asResourceFactory = asResourceFactory;
	}

	@Override
	public @NonNull AS2CS createAS2CS(@NonNull Map<@NonNull ? extends BaseCSResource, @NonNull ? extends ASResource> cs2asResourceMap,
			@NonNull EnvironmentFactoryInternal environmentFactory) {
		throw new UnsupportedOperationException();
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
	public void createAndAddDiagnostic(Triple<EObject, EReference, INode> triple) {
	//	throw new UnsupportedOperationException();
		return;			// XXX
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		throw new UnsupportedOperationException();
	}

//	@Override
//	protected @NonNull XMIHelperImpl createXMLHelper() {
//		return new BaseCSXMIResourceImpl.CSXMISaveHelper(this);
//	}

	@Override
	protected @NonNull XMLSave createXMLSave() {
		XMIHelperImpl xmlHelper = new BaseCSXMIResourceImpl.CSXMISaveHelper(this);
		return new BaseCSXMIResourceImpl.CSXMISave(xmlHelper);
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
		if (cs2as != null) {
			return cs2as;
		}
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
		assert PivotUtilInternal.debugDeprecation(getClass().getName() + ".getASResource()");
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
	final public @NonNull CS2AS getCS2AS() {
		assert PivotUtilInternal.debugDeprecation(getClass().getName() + ".getCS2AS()");
	//	if (cs2as != null) {
	//		return cs2as;
	//	}
		EnvironmentFactory environmentFactory = getEnvironmentFactory();
	//	EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(this);
	//	EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();			// XXX pass environmentFactory
	//	assert environmentFactory != null;
		return getCS2AS(environmentFactory);
	}

	@Override
	public @NonNull CS2AS getCS2AS(@NonNull EnvironmentFactory environmentFactory) {
		if (cs2as != null) {
			return cs2as;
		}
		EnvironmentFactoryInternal environmentFactoryInternal = (EnvironmentFactoryInternal)environmentFactory;
		CSI2ASMapping csi2asMapping = CSI2ASMapping.basicGetCSI2ASMapping(environmentFactoryInternal);
		if (csi2asMapping != null) {
			cs2as = csi2asMapping.getCS2AS(this);
			if (cs2as != null) {
				return cs2as;
			}
		}
		PivotMetamodelManager metamodelManager = environmentFactoryInternal.getMetamodelManager();
		ClassLoader classLoader = getClass().getClassLoader();
		if (classLoader != null) {
			metamodelManager.addClassLoader(classLoader);
		}
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		@SuppressWarnings("null")@NonNull Registry resourceFactoryRegistry = asResourceSet.getResourceFactoryRegistry();
		initializeResourceFactory(resourceFactoryRegistry);
		ASResource asResource = createASResource(asResourceSet);
	//	CS2AS cs2as = null;
	//	if (parserContext instanceof ExtendedParserContext) {
	//		cs2as = ((ExtendedParserContext)parserContext).createCS2AS(this, asResource);
	//	}
	//	if (cs2as == null) {
			cs2as = createCS2AS(environmentFactoryInternal, asResource);
	//	}
		return cs2as;
	}

	@Override
	public @NonNull CS2AS getCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> defaultSaveOptions2 = defaultSaveOptions;
		if (defaultSaveOptions2 == null) {
			defaultSaveOptions = defaultSaveOptions2 = XMIUtil.createPivotSaveOptions();
		}
		return defaultSaveOptions2;
	}

	@Override
	public @NonNull String getEditorName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory == null) {
			ResourceSet csResourceSet = ClassUtil.nonNullState(getResourceSet());			// Resource might have a ProjectMap adapting its ResourceSet
			ProjectManager projectManager = ProjectMap.findAdapter(csResourceSet);
			if (projectManager == null) {
				projectManager = ProjectManager.CLASS_PATH;
			}
			environmentFactory = ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(projectManager, csResourceSet, null);
		}
		return environmentFactory;
	//	return BaseCSResource.super.getEnvironmentFactory();
	}

	@Override
	public @NonNull ParserContext getParserContext() {
		ParserContext parserContext2 = parserContext;
		if (parserContext2 == null) {
			EnvironmentFactoryInternal environmentFactory = getCS2AS().getEnvironmentFactory();
		//	assert environmentFactory != null;
			parserContext = parserContext2 = new PreParsedContext(environmentFactory);
		}
		return parserContext2;
	}

	@Override
	public @NonNull ProjectManager getProjectManager() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void handleLoadResponse(Map<?, ?> response, Map<?, ?> options) {
		super.handleLoadResponse(response, options);
		CS2AS cs2as = getCS2AS(getEnvironmentFactory());
		ListBasedDiagnosticConsumer consumer = new ListBasedDiagnosticConsumer();
		cs2as.update(consumer);
		getErrors().addAll(consumer.getResult(Severity.ERROR));
		getWarnings().addAll(consumer.getResult(Severity.WARNING));
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
	public @Nullable NamedElement isPathable(@NonNull EObject element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull URI resolve(@NonNull URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDerived(boolean isDerived) {
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
	public void update(@NonNull IDiagnosticConsumer diagnosticsConsumer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(@NonNull EnvironmentFactory environmentFactory, @NonNull IDiagnosticConsumer diagnosticConsumer) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(int index, int length, String newString) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFrom(@NonNull ASResource asResource, @NonNull EnvironmentFactory environmentFactory) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean useIDs() {		// XXX ???
		return true;
	}
}
