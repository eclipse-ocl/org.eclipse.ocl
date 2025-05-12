/*******************************************************************************
 * Copyright (c) 2024, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ContentTypeFirstResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;

/**
 * The BaseCSXMIResource implementation of BaseCSResource that ensures that loading resolves references to CS/ES elements
 * to equivalent AS references and conversely ensures that saving replaces AS references by CS/ES references.
 * <br>
 * Derived implementations provide the appropriate CS2AS mapping.
 * <br>
 * While this implementation supports saving as XMI rather than Xtext serialization, it is not intended to be used as a regular
 * Resource. It is not expected to be added to a ResourceSet or to be unloaded then reloaded. (A reload should be a load from XMI.)
 */
public abstract class BaseCSXMIResource extends XMIResourceImpl implements CSResource
{
	/**
	 * CSXMISaveHelper overloads getHREF to persist references to internal AS elements as their persistable CS/ES equivalents.
	 */
	protected static class CSXMISaveHelper extends XMIHelperImpl
	{
		protected final @NonNull CSResource csResource;
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;

		public CSXMISaveHelper(@NonNull XMLResource xmiResource, @NonNull CSResource csResource) {
			super(xmiResource);
			this.csResource = csResource;
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert environmentFactory != null : "No EnvironmentFactory when CS-saving " + NameUtil.debugSimpleName(this);
			this.environmentFactory = environmentFactory;
		}

		@Override
		public String getHREF(EObject obj) {
	//		String href2 = getHREF2(obj);
	//	//	System.out.println(obj + " => " + href2);
	//		return href2;
	//	}
	//
	//	public String getHREF2(EObject obj) {
			if (obj instanceof Model) {
				URI reloadableURI = ((Model)obj).getReloadableURI(environmentFactory);
				if (reloadableURI != null) {
					return reloadableURI.toString();
				}
			}
			else if (obj instanceof Element) {										// AS is not persisted and so not referenceable
				EObject reloadableEObject = ((Element)obj).getReloadableEObject(environmentFactory);
				if (reloadableEObject != null) {
					String href2 = super.getHREF(reloadableEObject);
					if (reloadableEObject.eResource() == csResource) {				// Internal reference within original 'Xtext' CS
						int index = href2.indexOf("#");
						if (index >= 0) {
							href2 = href2.substring(index);							// relocate to XMI CS
						}
					}
					return href2;
				}
			}
			else {
				String eClassName = obj.eClass().getName();
				assert !eClassName.contains("JavaClassCS") : "Should be using OCLstdlibCSResourceSaveImpl";
				assert !eClassName.contains("MetaclassNameCS") : "Should be using OCLstdlibCSResourceSaveImpl";
			}
			return super.getHREF(obj);								// e.g. built-in oclstdlib-defined implementation without Ecore
		}

		@Override
		public Object getValue(EObject obj, EStructuralFeature f) {
			Object value = super.getValue(obj, f);
			if (value instanceof Variable) {
				//
				//	ParameterVariable has no distinct CS equivalent so must reference its Operation/Constraint.
				//
				Variable asVariable = (Variable)value;
				EStructuralFeature eContainingFeature = asVariable.eContainingFeature();
				if (eContainingFeature == PivotPackage.Literals.EXPRESSION_IN_OCL__OWNED_PARAMETERS) {
					Parameter representedParameter = asVariable.getRepresentedParameter();
					assert representedParameter != null;
					Constraint asConstraint = PivotUtil.getContainingConstraint(asVariable);
					if (asConstraint != null) {
						value = asConstraint;
					}
					else {
						Operation asOperation = PivotUtil.getContainingOperation(asVariable);
						if (asOperation != null) {
							value = asOperation;
						}
					}
				//	value = representedParameter;
				}
				else if (eContainingFeature == PivotPackage.Literals.EXPRESSION_IN_OCL__OWNED_RESULT) {
					assert asVariable.getRepresentedParameter() == null;
					Constraint asConstraint = PivotUtil.getContainingConstraint(asVariable);
					assert asConstraint != null;
					value = asConstraint;
				}
				else {
				//	throw new UnsupportedOperationException();		// iterators/accumulator
				}
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
		protected void init(XMLResource xmlResource, Map<?, ?> options) {
			CSResource csResource = (CSResource)xmlResource;
			EnvironmentFactory environmentFactory = csResource.getEnvironmentFactory();
			Map<@NonNull Object, @Nullable Object> saveOptions = new HashMap<>();
			if (options != null) {
				for (Object key : options.keySet()) {
					saveOptions.put(String.valueOf(key), options.get(key));
				}
			}
			ResourceSet asResourceSet = environmentFactory.getASResourceSet();
			AS2ID.assignIds(asResourceSet.getResources(), saveOptions);
			super.init(xmlResource, options);
		}
	}

	protected final @NonNull ASResourceFactory asResourceFactory;
	private @Nullable CS2AS cs2as = null;

	/**
	 * Creates an instance of the resource.
	 */
	protected BaseCSXMIResource(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
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

	public abstract @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource);

	@Override
	protected abstract @NonNull XMLSave createXMLSave();

	public @NonNull String getASContentType() {
		return asResourceFactory.getContentType();
	}

	@Override
	public @NonNull ASResource getASResource() {				// deprecated CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return asResourceFactory;
	}

	public @NonNull URI getASURI(@NonNull URI csURI) {
		return csURI.appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION);
	}

	@Override
	public @NonNull CS2AS getCS2AS(@NonNull EnvironmentFactory environmentFactory) {
		if (cs2as != null) {
			return cs2as;
		}
		EnvironmentFactoryInternal environmentFactoryInternal = (EnvironmentFactoryInternal)environmentFactory;
		CSI2ASMapping csi2asMapping = CSI2ASMapping.basicGetCSI2ASMapping(environmentFactoryInternal);
		if (csi2asMapping != null) {
			cs2as = csi2asMapping.getCS2AS(this);				// XXX misses for OCLstdlibCSXMIResourceImpl reload
			if (cs2as != null) {
				return cs2as;
			}
		}
		MetamodelManager metamodelManager = environmentFactoryInternal.getMetamodelManager();
		ClassLoader classLoader = getClass().getClassLoader();
		if (classLoader != null) {
			metamodelManager.addClassLoader(classLoader);
		}
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		@SuppressWarnings("null")@NonNull Registry resourceFactoryRegistry = asResourceSet.getResourceFactoryRegistry();
		initializeResourceFactory(resourceFactoryRegistry);
		ASResource asResource = createASResource(asResourceSet);
		cs2as = createCS2AS(environmentFactoryInternal, asResource);
		return cs2as;
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
	@Deprecated /* @deprecated caller should PivotUtilInternal.getEnvironmentFactory(Notifier) */ // XXX
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return PivotUtilInternal.getEnvironmentFactory(getResourceSet());
	}

	@Override
	public @NonNull ParserContext getParserContext() {		// CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	protected void handleLoadResponse(Map<?, ?> response, Map<?, ?> options) {
		super.handleLoadResponse(response, options);
		EnvironmentFactory environmentFactory = PivotUtilInternal.getEnvironmentFactory(getResourceSet());
		CS2AS cs2as = getCS2AS(environmentFactory);
		cs2as.update();
	}

	/**
	 * Install any required extension/content-type registrations to enable AS Resources
	 * to be created satisfactorily.
	 */
	protected void initializeResourceFactory(Resource.Factory.@NonNull Registry resourceFactoryRegistry) {}

	@Override
	public boolean isDerived() {												// CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDerived(boolean isDerived) {									// CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	public void setParserContext(@Nullable ParserContext parserContext) {		// CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProjectManager(@Nullable ProjectManager projectManager) {	// Obsolete CSResource method
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(int index, int length, String newString) {				// CSResource method demoted to BaseCSResource
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFrom(@NonNull ASResource asResource, @NonNull EnvironmentFactory environmentFactory) {
		throw new UnsupportedOperationException();
	}
}
