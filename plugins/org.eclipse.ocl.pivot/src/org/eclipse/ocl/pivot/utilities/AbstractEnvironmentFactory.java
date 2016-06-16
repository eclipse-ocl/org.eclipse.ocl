/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EMOFResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.context.ModelContext;
import org.eclipse.ocl.pivot.internal.context.OperationContext;
import org.eclipse.ocl.pivot.internal.context.PropertyContext;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractCustomizable;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.PivotModelManager;
import org.eclipse.ocl.pivot.internal.library.ImplementationManager;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.Technology;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.messages.StatusCodes.Severity;
import org.eclipse.ocl.pivot.options.EnumeratedOption;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.values.ObjectValue;

/**
 * Partial implementation of the {@link EnvironmentFactoryInternal} interface, useful
 * for subclassing to define the Pivot binding for a metamodel.
 */
public abstract class AbstractEnvironmentFactory extends AbstractCustomizable implements EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension
{
    private boolean traceEvaluation;
    protected final @NonNull ProjectManager projectManager;
    protected final @NonNull ResourceSet externalResourceSet;
    protected final boolean externalResourceSetWasNull;
    private /*@LazyNonNull*/ PivotMetamodelManager metamodelManager;
	private final @NonNull CompleteEnvironmentInternal completeEnvironment;
	private final @NonNull StandardLibraryInternal standardLibrary;
	private @Nullable ICSI2ASMapping csi2asMapping;
	/**
	 * The known packages.
	 */
	private final @NonNull CompleteModelInternal completeModel;

	private /*@LazyNonNull*/ IdResolver idResolver;
    
    /**
     * Count of the number of OCL instances that are using the EnvironmentFactory. auto-disposes on count down to zero.
     * -ve once disposed.
     */
    private int attachCount = 0;

    //    private List<WeakReference<Object>> attachers = null;;
    
    private @NonNull Technology technology = ASResourceFactoryRegistry.INSTANCE.getTechnology();
    
    /**
     * Configuration of validation preferences.
     */
	private /*LazyNonNull*/ Map<Object, StatusCodes.Severity> validationKey2severity = null;

	/**
	 * @param projectManager
	 */
	protected AbstractEnvironmentFactory(@NonNull ProjectManager projectManager, @Nullable ResourceSet externalResourceSet) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {			// This is the unique start point for OCL so
			PivotStandaloneSetup.doSetup();				//  do the non-UI initialization (guarded in doSetup())
		}
		this.projectManager = projectManager;
		if (externalResourceSet != null) {
			this.externalResourceSetWasNull = false;
			this.externalResourceSet = externalResourceSet;
		}
		else {
			this.externalResourceSetWasNull = true;
			this.externalResourceSet = externalResourceSet = new ResourceSetImpl();
			projectManager.initializeResourceSet(externalResourceSet);			
			externalResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("emof", new EMOFResourceFactoryImpl()); //$NON-NLS-1$
			ASResourceFactoryRegistry.INSTANCE.configureResourceSet(externalResourceSet);
		}
		adapt(externalResourceSet);
		this.metamodelManager = null;
		this.completeEnvironment = createCompleteEnvironment();
		this.standardLibrary = completeEnvironment.getOwnedStandardLibrary();
		this.completeModel = completeEnvironment.getOwnedCompleteModel();
	}
	
	@Override
	public @NonNull EnvironmentFactoryAdapter adapt(@NonNull Notifier notifier) {
		List<Adapter> eAdapters = ClassUtil.nonNullEMF(notifier.eAdapters());
		EnvironmentFactoryAdapter adapter = ClassUtil.getAdapter(EnvironmentFactoryAdapter.class, eAdapters);
		if (adapter == null) {
			adapter = new EnvironmentFactoryAdapter(this, notifier);
			eAdapters.add(adapter);
		}
		return adapter;
	}

	@Override
	public void addExternal2AS(@NonNull External2AS external2as) {
		Resource resource = external2as.getResource();
		if ((resource != null) && ClassUtil.isRegistered(resource)) {
			ResourceSet externalResourceSet2 = getResourceSet();
			projectManager.useGeneratedResource(resource, externalResourceSet2);
		}
		getMetamodelManager().addExternal2AS(external2as);
	}

	/**
	 * Add all resources in ResourceSet to the externalResourceSet.
	 */
	@Override
	public void addExternalResources(@NonNull ResourceSet resourceSet) {
		ResourceSet externalResourceSet = getResourceSet();
		if (externalResourceSet instanceof ResourceSetImpl) {
			Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)externalResourceSet).getURIResourceMap();
			if (uriResourceMap != null) {
				for (Resource eResource : resourceSet.getResources()) {
					URI uri = eResource.getURI();
					if (uri != null) {
						uriResourceMap.put(uri, eResource);
					}
				}
				if (resourceSet instanceof ResourceSetImpl) {
					Map<URI, Resource> contextResourceMap = ((ResourceSetImpl)resourceSet).getURIResourceMap();
					if ((contextResourceMap != null) && (contextResourceMap != uriResourceMap)) {
						for (URI uri : contextResourceMap.keySet()) {
							uriResourceMap.put(uri, contextResourceMap.get(uri));
						}
					}
				}
			}
		}
	}

	@Override
	public synchronized void attach(Object object) {
		if (attachCount < 0) {
			throw new IllegalStateException(getClass().getName() + " disposed");
		}
		attachCount++;
//		if (attachers == null) {
//			attachers = new ArrayList<WeakReference<Object>>();
//		}
//		attachers.add(new WeakReference<Object>(object));
	}

	protected @Nullable PivotMetamodelManager basicGetMetamodelManager() {
		return metamodelManager;
	}

	@Override
	public void configureLoadFirstStrategy() {
		configureLoadStrategy(StandaloneProjectMap.LoadFirstStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
	}

	@Override
	public void configureLoadStrategy(ProjectManager.@NonNull IResourceLoadStrategy packageLoadStrategy, ProjectManager.@Nullable IConflictHandler conflictHandler) {
		ResourceSet externalResourceSet = getResourceSet();
		projectManager.configure(externalResourceSet, packageLoadStrategy, conflictHandler);
	}

	@Override
	public @NonNull ResourceSetImpl createASResourceSet() {
		ResourceSetImpl asResourceSet = new ResourceSetImpl();
		StandaloneProjectMap.initializeURIResourceMap(asResourceSet);
		ASResourceFactoryRegistry.INSTANCE.configureResourceSet(asResourceSet);
		EPackage.Registry packageRegistry = asResourceSet.getPackageRegistry();
		packageRegistry.put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		asResourceSet.eAdapters().add(projectManager);
		projectManager.initializeResourceSet(asResourceSet);
		return asResourceSet;
	}

	@Override
	public @NonNull CompleteEnvironmentInternal createCompleteEnvironment() {
		CompleteEnvironmentInternal completeEnvironment = (CompleteEnvironmentInternal)PivotFactory.eINSTANCE.createCompleteEnvironment();
		completeEnvironment.init(this);
		return completeEnvironment;
	}

	@Override
	public @NonNull EvaluationEnvironment createEvaluationEnvironment(@NonNull NamedElement executableObject, @NonNull ModelManager modelManager) {
		ExecutorInternal executor = createExecutor(modelManager);
		return executor.initializeEvaluationEnvironment(executableObject);
	}

	/** @deprecated no longer used */
	@Deprecated
	@Override
	public @NonNull EvaluationEnvironment createEvaluationEnvironment(@NonNull EvaluationEnvironment parent, @NonNull NamedElement executableObject) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull EvaluationVisitor createEvaluationVisitor(@Nullable Object context, @NonNull ExpressionInOCL expression, @Nullable ModelManager modelManager) {
		if (modelManager == null) {
			// let the evaluation environment create one
			modelManager = createModelManager(context);
		}
		// can determine a more appropriate context from the context
		// variable of the expression, to account for stereotype constraints
//		context = HelperUtil.getConstraintContext(rootEnvironment, context, expression);
		ExecutorInternal executor = createExecutor(modelManager);
		EvaluationEnvironment evaluationEnvironment = executor.initializeEvaluationEnvironment(expression);
		Variable contextVariable = expression.getOwnedContext();
		if (contextVariable != null) {
			IdResolver idResolver = getIdResolver();
			Object value = idResolver.boxedValueOf(context);
			evaluationEnvironment.add(contextVariable, value);
		}
		for (Variable parameterVariable : expression.getOwnedParameters()) {
			if (parameterVariable != null) {
				evaluationEnvironment.add(parameterVariable, null);
			}
		}
		return executor.getEvaluationVisitor();
	}
	
	@Override
	public @NonNull EvaluationVisitor createEvaluationVisitor(@NonNull EvaluationEnvironment evaluationEnvironment) {
		ExecutorInternal executor = ((EvaluationEnvironment.EvaluationEnvironmentExtension)evaluationEnvironment).getExecutor();
		return executor.getEvaluationVisitor();
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull ExecutorInternal createExecutor(@NonNull ModelManager modelManager) {
		return new BasicOCLExecutor(this, modelManager);
	}

	@Override
	public  @NonNull IdResolver createIdResolver() {
		return technology.createIdResolver(this);
	}

	@Override
	public @NonNull ImplementationManager createImplementationManager() {
		return new ImplementationManager(this);
	}

	@Override
	public @NonNull PivotMetamodelManager createMetamodelManager() {
		assert metamodelManager == null;
		metamodelManager = new PivotMetamodelManager(this, createASResourceSet());
		assert metamodelManager != null;
		return metamodelManager;
	}

	protected @NonNull ModelManager createModelManager() {
		return ModelManager.NULL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NonNull ModelManager createModelManager(@Nullable Object object) {
		if (object instanceof ObjectValue) {
			object = ((ObjectValue) object).getObject();
		}
		if (object instanceof EObject) {
			return new PivotModelManager(this, (EObject) object);
		}
		return ModelManager.NULL;
	}

	@Override
	public @NonNull OCLInternal createOCL() {
		return new OCLInternal(this);
	}

	@Override
	public @NonNull ParserContext createParserContext(@Nullable EObject context) throws ParserException {
    	PivotMetamodelManager metamodelManager = getMetamodelManager();
		Element asContext;
		if (context instanceof Element) {
			asContext =  (Element)context;
		}
		else {
			asContext = metamodelManager.getASOf(Element.class, context);
		}
		if (asContext instanceof org.eclipse.ocl.pivot.Class) {
			return new ClassContext(this, null, (org.eclipse.ocl.pivot.Class)asContext, null);
        }
        else if (asContext instanceof Operation) {
    		return new OperationContext(this, null, (Operation)asContext, null);
        }
        else if (asContext instanceof Property) {
        	return new PropertyContext(this, null, (Property)asContext);
        }
/*        else if (context instanceof EClassifier) {
        	org.eclipse.ocl.pivot.Class contextClass = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Class.class, context);
        	return new ClassContext(this, null, contextClass, null);
        }
        else if (context instanceof EOperation) {
    		Operation asOperation = metamodelManager.getASOfEcore(Operation.class, context);
    		if (asOperation != null) {
    			return new OperationContext(this, null, asOperation, null);
    		}
        }
        else if (context instanceof EStructuralFeature) {
        	Property asProperty = metamodelManager.getASOfEcore(Property.class, context);
    		if (asProperty != null) {
    			return new PropertyContext(this, null, asProperty);
    		}
        } */
        return new ModelContext(this, null);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull TemplateParameterSubstitutionVisitor createTemplateParameterSubstitutionVisitor(
			@Nullable Type selfType, @Nullable Type selfTypeValue) {
		return new TemplateParameterSubstitutionVisitor(this, selfType, selfTypeValue);
	}

	protected @NonNull HashMap<Object, StatusCodes.Severity> createValidationKey2severityMap() {
		HashMap<Object, StatusCodes.Severity> map = new HashMap<Object, StatusCodes.Severity>();
		Set<Entry<String, EnumeratedOption<Severity>>> entrySet = PivotValidationOptions.safeValidationName2severityOption.entrySet();
		for (Map.Entry<String, EnumeratedOption<StatusCodes.Severity>> entry : entrySet) {
			EnumeratedOption<StatusCodes.Severity> value = entry.getValue();
			if (value != null) {
				map.put(entry.getKey(), getValue(value));
			}
		}
		return map;
	}
	
	@Override
	public synchronized void detach(Object object) {
		if (attachCount < 0) {
			return;					// Ignore detach after dispose
		}
		if (attachCount == 0) {
			throw new IllegalStateException(getClass().getName() + " not attached");
		}
//		if (attachers != null) {
//			for (WeakReference<Object> attacher : attachers) {
//				if (attacher.get() == object) {
//					attachers.remove(attacher);
//					break;
//				}
//			}
//		}
		if (--attachCount <= 0) {
			dispose();
		}
	}

	@Override
	public void dispose() {
		if (attachCount < 0) {
			throw new IllegalStateException(getClass().getName() + " already disposed");
		}
		attachCount = -1;
		disposeInternal();
	}

	protected void disposeInternal() {
		assert attachCount == -1;
		boolean isGlobal = this == GlobalEnvironmentFactory.basicGetInstance();
		if (metamodelManager != null) {
			metamodelManager.dispose();
			metamodelManager = null;
		}
		EList<Adapter> externalResourceSetAdapters = externalResourceSet.eAdapters();
		if (externalResourceSetWasNull || isGlobal) {
//			System.out.println("dispose CS " + ClassUtil.debugSimpleName(externalResourceSet));
			projectManager.unload(externalResourceSet);
			externalResourceSetAdapters.remove(projectManager);
//			StandaloneProjectMap.dispose(externalResourceSet2);
			externalResourceSet.setPackageRegistry(null);
			externalResourceSet.setResourceFactoryRegistry(null);
			externalResourceSet.setURIConverter(null);
			if (externalResourceSet instanceof ResourceSetImpl) {
				((ResourceSetImpl)externalResourceSet).setURIResourceMap(null);
			}
			for (Resource resource : new ArrayList<Resource>(externalResourceSet.getResources())) {
				if (Thread.currentThread().getContextClassLoader() == null) {		// If finalizing, avoid NPE from EPackageRegistryImpl$Delegator.deegateRegistry()
				    // This guard is needed to ensure that clear doesn't make the resource become loaded.
				    //
				    if (!resource.getContents().isEmpty())
				    {
				    	resource.getContents().clear();
				    }
				    resource.getErrors().clear();
				    resource.getWarnings().clear();
/*				    if (idToEObjectMap != null)
				    {
				      idToEObjectMap.clear();
				    }

				    if (eObjectToIDMap != null)
				    {
				      eObjectToIDMap.clear();
				    }

				    if (eObjectToExtensionMap != null)
				    {
				      eObjectToExtensionMap.clear();
				    } */
					
				}
				else {
					resource.unload();
				}
				resource.eAdapters().clear();
			}
			externalResourceSetAdapters.clear();
//			externalResourceSet = null;
		}
		else {
			for (Adapter adapter : externalResourceSetAdapters) {
				if ((adapter instanceof EnvironmentFactoryAdapter) && (((EnvironmentFactoryAdapter)adapter).getEnvironmentFactory() == this)) {
					externalResourceSetAdapters.remove(adapter);
					break;
				}
			}
		}
		if (idResolver != null) {
			idResolver.dispose();
			idResolver = null;
		}
		if (csi2asMapping != null) {
			csi2asMapping.dispose();
			csi2asMapping = null;
		}
	}

	/**
	 * The abstract environment factory implementation is adaptable.  The
	 * default implementation adapts to and interface actually implemented by
	 * the receiver.
	 * <p>
	 * Subclasses may override or extend this implementation.
	 * </p>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> @Nullable T getAdapter(java.lang.Class<T> adapterType) {
		@Nullable T result;
		
		if (adapterType.isAssignableFrom(getClass())) {
			result = (T) this;
		} else {
			result = null;
		}
		
		return result;
	}

	/**
     * Obtains client metamodel's classifier for the specified
     * <code>context</code> object, which may be an instance of a classifier
     * in the user model or may actually be a classifier in the user model.
     * 
     * @param context a context object or classifier
     * @return the user model's classifier for this context object, or the
     *     context itself if it is a classifier
     */
	protected org.eclipse.ocl.pivot.@NonNull Class getClassifier(@NonNull Object context) {
		PivotMetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class dClass = getIdResolver().getStaticTypeOf(context);
		return metamodelManager.getPrimaryClass(dClass);
	}

	@Override
	public @NonNull CompleteEnvironmentInternal getCompleteEnvironment() {
		return completeEnvironment; //completeModel.getCompleteEnvironment();
	}

	@Override
	public @NonNull CompleteModelInternal getCompleteModel() {
		return completeModel;
	}

	@Override
	public @Nullable ICSI2ASMapping getCSI2ASMapping() {
		return csi2asMapping;
	}

	@Override
	public @Nullable String getDoSetupName(@NonNull URI uri) {
		String fileExtension = uri.fileExtension();
		if (PivotConstants.ESSENTIAL_OCL_FILE_EXTENSION.equals(fileExtension)) {
			return "EssentialOCLStandaloneSetup.doSetup()";
		}
		else if (PivotConstants.OCL_FILE_EXTENSION.equals(fileExtension)) {
			return "CompleteOCLStandaloneSetup.doSetup()";
		}
		else if (PivotConstants.OCLINECORE_FILE_EXTENSION.equals(fileExtension)) {
			return "OCLinEcoreStandaloneSetup.doSetup()";
		}
		else if (PivotConstants.OCLSTDLIB_FILE_EXTENSION.equals(fileExtension)) {
			return "OCLstdlibStandaloneSetup.doSetup()";
		}
		return null;
	}

	@Override
	public @NonNull IdResolver getIdResolver() {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2;
	}

	@Override
	public @NonNull PivotMetamodelManager getMetamodelManager() {
		PivotMetamodelManager metamodelManager2 = metamodelManager;
		if (metamodelManager2 == null) {
			metamodelManager = metamodelManager2 = createMetamodelManager();
		}
		return metamodelManager2;
	}

	@Override
	protected @Nullable EnvironmentFactoryInternal getParent() {
		return null;
	}

	/**
	 * Return the ProjectMap used to resolve EPackages.
	 */
	@Override
	public @NonNull ProjectManager getProjectManager() {
		return projectManager;
	}
	
	@Override
	public @NonNull ResourceSet getResourceSet() {
		return externalResourceSet;
	}

	@Override
	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<Object, StatusCodes.Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	@Override
	public @NonNull StandardLibraryInternal getStandardLibrary() {
		return standardLibrary;
	}

	@Override
	public @NonNull Technology getTechnology() {
		return technology;
	}

	/**
     * Queries whether tracing of evaluation is enabled.  Tracing
     * logs the progress of evaluation to the console, which may
     * be of use in diagnosing problems.
     * <p>
     * In an Eclipse environment, tracing is also enabled by turning on the
     * <tt>org.eclipse.ocl/debug/evaluation</tt> debug option. 
     * </p>
     * 
     * @return whether evaluation tracing is enabled
     * 
     * @see #setEvaluationTracingEnabled(boolean)
	 * @since 1.1
     */
	@Override
	public boolean isEvaluationTracingEnabled() {
        return traceEvaluation;
    }

	@Override
	public EPackage loadEPackage(@NonNull EPackage ePackage) {
		return externalResourceSet.getPackageRegistry().getEPackage(ePackage.getNsURI());
	}

	@Override
	public @Nullable Element loadResource(@NonNull Resource resource, @Nullable URI uri) throws ParserException {
		ASResourceFactory bestFactory = ASResourceFactoryRegistry.INSTANCE.getASResourceFactory(resource);
		if (bestFactory != null) {
			ResourceSet resourceSet = resource.getResourceSet();
			if ((resourceSet != null) && (resourceSet != externalResourceSet)) {
				addExternalResources(resourceSet);
			}
			return bestFactory.importFromResource(this, resource, uri);
		}
		throw new ParserException("Cannot create pivot from '" + uri + "'");
//		logger.warn("Cannot convert to pivot for package with URI '" + uri + "'");
	}
	
	public void resetSeverities() {
		validationKey2severity = null;
	}

	@Override
	public void setCSI2ASMapping(ICSI2ASMapping csi2asMapping) {
		this.csi2asMapping = csi2asMapping;
	}
	
    /**
     * Sets whether tracing of evaluation is enabled.  Tracing logs
     * the progress of parsing to the console, which may be of use in diagnosing
     * problems.
     * <p>
     * In an Eclipse environment, tracing is also enabled by turning on the
     * <tt>org.eclipse.ocl/debug/evaluation</tt> debug option. 
     * </p>
     * 
     * param b whether evaluation tracing is enabled
     * 
     * @see #isEvaluationTracingEnabled()
     */
    @Override
	public void setEvaluationTracingEnabled(boolean b) {
        traceEvaluation = b;
    }

	@Override
	public void setProject(@Nullable IProject project) {}

	/**
	 * Configure safe navigation validation severities.
	 */
	@Override
	public void setSafeNavigationValidationSeverity(StatusCodes.@NonNull Severity severity) {
		for (String key : PivotValidationOptions.safeValidationName2severityOption.keySet()) {
			if (key != null) {
				setSeverity(key, severity);
			}
		}
	}

	@Override
	public synchronized StatusCodes.@Nullable Severity setSeverity(@NonNull Object validationKey, StatusCodes.Severity severity) {
		Map<Object, StatusCodes.Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.put(validationKey, severity);
	}
}
