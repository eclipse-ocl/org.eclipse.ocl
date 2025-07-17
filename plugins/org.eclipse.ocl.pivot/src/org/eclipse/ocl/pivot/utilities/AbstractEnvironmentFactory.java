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
package org.eclipse.ocl.pivot.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EMOFResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.evaluation.NullModelManager;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.context.OperationContext;
import org.eclipse.ocl.pivot.internal.context.PropertyContext;
import org.eclipse.ocl.pivot.internal.ecore.EcoreASResourceFactory;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractCustomizable;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.library.ImplementationManager;
import org.eclipse.ocl.pivot.internal.library.executor.LazyEcoreModelManager;
import org.eclipse.ocl.pivot.internal.manager.FlowAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ContentTypeFirstResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ICS2AS;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.Technology;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.ObjectValue;

/**
 * Partial implementation of the {@link EnvironmentFactoryInternal} interface, useful
 * for subclassing to define the Pivot binding for a metamodel.
 */
public abstract class AbstractEnvironmentFactory extends AbstractCustomizable implements EnvironmentFactoryInternal
{
	/**
	 * @since 1.4
	 */
	public static final @NonNull TracingOption ENVIRONMENT_FACTORY_ATTACH = new TracingOption(PivotPlugin.PLUGIN_ID, "environmentFactory/attach");

	private static final Logger logger = Logger.getLogger(AbstractEnvironmentFactory.class);

	/**
	 * @since 7.0
	 */
	public static void diagnoseLiveEnvironmentFactories() {
		if ((liveEnvironmentFactories != null) && !liveEnvironmentFactories.isEmpty()) {
			StringBuilder s = new StringBuilder();
			s.append(" live");
			for (@NonNull EnvironmentFactory key : new ArrayList<>(liveEnvironmentFactories.keySet())) {
				s.append(" @" + Integer.toHexString(key.hashCode()));
			}
			System.out.println(s.toString());
		}
	}

	private boolean traceEvaluation;
	protected final @NonNull ProjectManager projectManager;
	private final @Nullable ResourceSet userResourceSet;			// XXX may be multiple ResourceSets
	protected final @NonNull ResourceSet externalResourceSet;
	private final @NonNull ResourceSet asResourceSet;
	private /*@LazyNonNull*/ MetamodelManager metamodelManager = null;
	/**
	 * @since 7.0
	 */
	protected final @NonNull CompleteEnvironmentInternal completeEnvironment;
	/**
	 * @since 7.0
	 */
	protected final @NonNull CompleteStandardLibrary standardLibrary;
	private @Nullable ICSI2ASMapping csi2asMapping;
	/**
	 * The known packages.
	 * @since 7.0
	 */
	protected final @NonNull CompleteModelInternal completeModel;

	private /*@LazyNonNull*/ IdResolver idResolver;

	/**
	 * Count of the number of OCL instances that are using the EnvironmentFactory. auto-disposes on count down to zero.
	 * -ve once disposed.
	 */
	private int attachCount = 0;

	/**
	 * Debug lust of the System.identityHashCode of each active owners of an attach
	 *
	 * System.identityHashCode avoids problems with finalized attachOwners.
	 */
	private List<@NonNull Integer> attachOwners = new ArrayList<>();

	private @NonNull Technology technology = ASResourceFactoryRegistry.INSTANCE.getTechnology();

	/**
	 * Configuration of validation preferences.
	 */
	private /*LazyNonNull*/ Map<Object, StatusCodes.Severity> validationKey2severity = null;

	/*
	 * True once dispose() has started.
	 */
	private boolean isDisposing = false;

	/**
	 * Leak debugging aid. Set non-null to diagnose EnvironmentFactory construction and finalization.
	 * Beware, stale EnvironmentFactory instances may live on beyond a test until GC catches up. To ensure
	 * timely GC, set DEBUG_GC (and probably DEBUG_ID) true in the PivotTestCase static initialization.
	 *
	 * @since 1.14
	 */
	public static WeakHashMap<@NonNull AbstractEnvironmentFactory, @Nullable Object> liveEnvironmentFactories = null;

	/**
	 * @since 1.7
	 */
	public static int CONSTRUCTION_COUNT = 0;

	/**
	 * @since 1.10
	 */
	protected AbstractEnvironmentFactory(final @NonNull ProjectManager projectManager, final @Nullable ResourceSet userResourceSet, final @Nullable ResourceSet zzASResourceSet) {
		assert zzASResourceSet == null;
		CONSTRUCTION_COUNT++;
		if (liveEnvironmentFactories != null) {
			liveEnvironmentFactories.put(this, null);
			PivotUtil.debugPrintln("Create " + toDebugString()
			+ " " + NameUtil.debugSimpleName(userResourceSet) + " " + NameUtil.debugSimpleName(zzASResourceSet));
		}
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {			// This is the unique start point for OCL so
			PivotStandaloneSetup.doSetup();				//  do the non-UI initialization (guarded in doSetup())
		}
		this.projectManager = projectManager;
		this.userResourceSet = userResourceSet;
		this.asResourceSet = createASResourceSet();
		this.externalResourceSet = createExternalResourceSet(userResourceSet);
		ASResourceFactoryRegistry.INSTANCE.configureResourceSets(asResourceSet, externalResourceSet);
		if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
			ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " Create(" + attachCount + ") " + toDebugString() + " => " + NameUtil.debugSimpleName(externalResourceSet) + ", " + NameUtil.debugSimpleName(asResourceSet));
		}
	//	adapt(externalResourceSet);
		this.standardLibrary = createStandardLibrary();
		this.completeModel = createCompleteModel();
		this.completeEnvironment = createCompleteEnvironment();

		this.completeModel.init(this);
		this.standardLibrary.init(this);


		PivotUtil.initializeLoadOptionsToSupportSelfReferences(getResourceSet());
		ThreadLocalExecutor.attachEnvironmentFactory(this);
		//	System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " EnvironmentFactory.ctor " + NameUtil.debugSimpleName(this) + " es " + NameUtil.debugSimpleName(externalResourceSet) + " as " + NameUtil.debugSimpleName(asResourceSet));
		if (userResourceSet != null) {
			Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)externalResourceSet).getURIResourceMap();
			assert uriResourceMap != null;
			List<@NonNull EPackage> allEPackages = new UniqueList<>();
			List</* @NonNull */Resource> transitiveExternalResources = new UniqueList<>(uriResourceMap.values());
			for (int i = 0; i < transitiveExternalResources.size(); i++) {
				Resource esResource = transitiveExternalResources.get(i);
				if (esResource != null) {
					for (@NonNull EObject eObject : new TreeIterable(esResource)) {
						EClass eClass = eObject.eClass();
						EPackage ePackage = eClass.getEPackage();
						assert ePackage != null : "No EPackage for " + eClass;
						if (allEPackages.add(ePackage)) {		// EPackage.nsURI schizophrenia is ok (e.g http:/... vs platform:/.../*.ecore)
							Resource resource = ePackage.eResource();
							assert resource != null : "No eResource for " + ePackage;
							if (transitiveExternalResources.add(resource)) {
								URI uri = resource.getURI();
								Resource old = uriResourceMap.put(uri, resource);		// Resource.uri schizophrenia is not ok ??? why not ???
								if ((old != null) && (old != resource)) {
									uriResourceMap.put(uri, old);						// Stick with the first
									logger.error(StringUtil.bind(PivotMessages.ConflictingResource, uri));
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @since 1.23
	 */
	@Override
	public void activate() {
		EnvironmentFactoryInternal basicGetEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
	//	System.out.println("[" + Thread.currentThread().getName() + "] activate: environmentFactory = " + NameUtil.debugSimpleName(this));
	//	System.out.println("[" + Thread.currentThread().getName() + "] activate: ThreadLocalExecutor.basicGetEnvironmentFactory() = " + NameUtil.debugSimpleName(basicGetEnvironmentFactory));
		if ((basicGetEnvironmentFactory != this) && (basicGetEnvironmentFactory != null)) {
			ThreadLocalExecutor.resetEnvironmentFactory();
		}
		ThreadLocalExecutor.attachEnvironmentFactory(this);
	}

	@Override
	public void addExternal2AS(@NonNull External2AS external2as) {
		@NonNull Resource resource = external2as.getResource();
		if (ClassUtil.isRegistered(resource)) {
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
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class pivotElement) {
		if (pivotElement.getUnspecializedElement() != null) {
			assert pivotElement.getUnspecializedElement().getUnspecializedElement() == null;
		}
		else {
			assert (pivotElement instanceof LambdaType)
			|| (pivotElement instanceof TupleType);
		}
		pivotElement.setOwningPackage(getOrphanage());
	}

	@Override
	public void analyzeExpressions(@NonNull EObject eRootObject,
			@NonNull Set<@NonNull CompleteClass> allInstancesCompleteClasses, @NonNull Set<@NonNull Property> implicitOppositeProperties) {
		@SuppressWarnings("unused") Type oclElementType = standardLibrary.getOclElementType();
		Type classType = standardLibrary.getClassType();
		OperationId allInstancesOperationId = classType.getTypeId().getOperationId(0, "allInstances", IdManager.getParametersId());
		for (EObject eObject : new TreeIterable(eRootObject, true)) {
			if (eObject instanceof OppositePropertyCallExp) {
				OppositePropertyCallExp oppositePropertyCallExp = (OppositePropertyCallExp)eObject;
				Property navigableProperty = oppositePropertyCallExp.getReferredProperty();
				if ((navigableProperty != null) && !navigableProperty.isIsComposite()) {
					implicitOppositeProperties.add(navigableProperty);
				}
			}
			else if (eObject instanceof OperationCallExp) {
				OperationCallExp operationCallExp = (OperationCallExp)eObject;
				Operation referredOperation = operationCallExp.getReferredOperation();
				if (referredOperation != null) {
					OperationId operationId = referredOperation.getOperationId();
					if (operationId == allInstancesOperationId) {
						OCLExpression source = operationCallExp.getOwnedSource();
						if (source != null) {
							Type asType = source.getTypeValue();
							if (asType == null) {
								asType = source.getType();
							}
							if (asType instanceof org.eclipse.ocl.pivot.Class) {
								assert !(asType instanceof PrimitiveType);
								assert !(asType instanceof CollectionType);
								CompleteClass completeClass = completeModel.getCompleteClass(asType);
								allInstancesCompleteClasses.add(completeClass);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public synchronized void attach(@NonNull Object attachOwner) {
	//	EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
	//	assert (environmentFactory == null) || (environmentFactory == this) : ThreadLocalExecutor.getBracketedThreadName() + " " + toDebugString() + " should be " + NameUtil.debugSimpleName(environmentFactory);
		if (isDisposed()) {
			if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
				ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " Attach(" + attachCount + ") " + toDebugString() + " " + NameUtil.debugSimpleName(attachOwner));
			}
			throw new IllegalStateException(getClass().getName() + " disposed");
		}
		attachCount++;
		attachOwners.add(System.identityHashCode(attachOwner));
		if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
			ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " Attach(" + (attachCount-1) + ":" + attachCount + ") " + toDebugString() + " " + NameUtil.debugSimpleName(attachOwner));
			getClass();					// Debugging breakpoint opportunity
		}
	}

	/**
	 * @since 7.0
	 */
	protected @Nullable MetamodelManager basicGetMetamodelManager() {
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
		asResourceSet.setResourceFactoryRegistry(new ContentTypeFirstResourceFactoryRegistry(asResourceSet));
	//	StandaloneProjectMap.initializeURIResourceMap(asResourceSet);
		EPackage.Registry packageRegistry = asResourceSet.getPackageRegistry();
	//	packageRegistry.put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		projectManager.initializeResourceSet(asResourceSet);
		packageRegistry.put(PivotPackage.eNS_URI, PivotPackage.eINSTANCE);
		return asResourceSet;
	}

	@Override
	public @NonNull CompleteEnvironmentInternal createCompleteEnvironment() {
		CompleteEnvironmentInternal completeEnvironment = (CompleteEnvironmentInternal)PivotFactory.eINSTANCE.createCompleteEnvironment();
		completeEnvironment.init(this);
		return completeEnvironment;
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull CompleteModelInternal createCompleteModel() {
		return (CompleteModelInternal)PivotFactory.eINSTANCE.createCompleteModel();
	}

	@Override
	public @NonNull EvaluationEnvironment createEvaluationEnvironment(@NonNull NamedElement executableObject, @NonNull ModelManager modelManager) {
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		assert executor == null;
		executor = createExecutor(modelManager);
//		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		assert executor != null;
		ExecutorInternal interpretedExecutor = executor.basicGetInterpretedExecutor();
		assert interpretedExecutor != null;
		assert interpretedExecutor.getModelManager() == modelManager;
		return interpretedExecutor.initializeEvaluationEnvironment(executableObject);
	}

	@Override
	public @NonNull EvaluationVisitor createEvaluationVisitor(@Nullable Object context, @NonNull ExpressionInOCL expression, @Nullable ModelManager modelManager) {
		ThreadLocalExecutor.setExecutor(null);					// Eliminate obsolete dropping from previous EvaluationVisitor
		if (modelManager == null) {
			// let the evaluation environment create one
			modelManager = createModelManager(context);
		}
		// can determine a more appropriate context from the context
		// variable of the expression, to account for stereotype constraints
		//		context = HelperUtil.getConstraintContext(rootEnvironment, context, expression);
		ExecutorInternal executorInternal = createExecutor(modelManager);
		EvaluationEnvironment evaluationEnvironment = executorInternal.initializeEvaluationEnvironment(expression);
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
		return executorInternal.getEvaluationVisitor();
	}

	@Override
	public @NonNull EvaluationVisitor createEvaluationVisitor(@NonNull EvaluationEnvironment evaluationEnvironment) {
		ExecutorInternal executor = evaluationEnvironment.getExecutor();
		return executor.getEvaluationVisitor();
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull ExecutorInternal createExecutor(@NonNull ModelManager modelManager) {
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		ExecutorInternal interpretedExecutor = executor != null ? executor.basicGetInterpretedExecutor() : null;
		if (executor == null) {
//			interpretedExecutor = new BasicOCLExecutor(this, modelManager);
//			executor.setInterpretedExecutor(interpretedExecutor);
		}
		else {
			assert executor.getModelManager() == modelManager;
			if (executor != interpretedExecutor) {
				executor.setInterpretedExecutor(interpretedExecutor);
			}
		}
		if (interpretedExecutor == null) {
			interpretedExecutor = new BasicOCLExecutor(this, modelManager);
			if (executor == null) {
				ThreadLocalExecutor.setExecutor(interpretedExecutor);
			}
			else {
				executor.setInterpretedExecutor(interpretedExecutor);
			}
		}


/*		if (executor != null) {
			ExecutorInternal interpretedExecutor = executor.basicGetInterpretedExecutor();
		//	assert interpretedExecutor == null;
		}
		BasicOCLExecutor interpretedExecutor = new BasicOCLExecutor(this, modelManager);
		if (executor == null) {
			ThreadLocalExecutor.setExecutor(interpretedExecutor);
		}
		else {
			executor.setInterpretedExecutor(interpretedExecutor);
		} */
		return interpretedExecutor;
	}

	/**
	 * Create the ResourceSet 'using' whatever external user resources and other facilities from the userResourceSet.
	 * We cannot just re-use the userResourceSet since any additional ES facilities resulting from OCL processing would be
	 * inflicted on unsuspecting callers such as the Sample Ecore Model Editor. Copying the user resources is undesirable and
	 * re-using impossible since they are contained by the userResourceSet. We therefore copy the resources as URIResourceMap entries.
	 *
	 * @since 7.0
	 */
	protected @NonNull ResourceSet createExternalResourceSet(@Nullable ResourceSet userResourceSet) {
		ResourceSetImpl externalResourceSet = new ResourceSetImpl();
		projectManager.initializeResourceSet(externalResourceSet);
		Resource.Factory.Registry externalResourceFactoryRegistry = externalResourceSet.getResourceFactoryRegistry();
		if (userResourceSet != null) {
			externalResourceSet.setURIConverter(userResourceSet.getURIConverter());
			externalResourceSet.getPackageRegistry().putAll(userResourceSet.getPackageRegistry());
			Resource.Factory.Registry userResourceFactoryRegistry = userResourceSet.getResourceFactoryRegistry();
			externalResourceFactoryRegistry.getContentTypeToFactoryMap().putAll(userResourceFactoryRegistry.getContentTypeToFactoryMap());
			externalResourceFactoryRegistry.getExtensionToFactoryMap().putAll(userResourceFactoryRegistry.getExtensionToFactoryMap());
			externalResourceFactoryRegistry.getProtocolToFactoryMap().putAll(userResourceFactoryRegistry.getProtocolToFactoryMap());
			Map<URI, Resource> uriResourceMap = externalResourceSet.getURIResourceMap();
			if (userResourceSet instanceof ResourceSetImpl) {
				Map<URI, Resource> userResourceMap = ((ResourceSetImpl)userResourceSet).getURIResourceMap();
				if (userResourceMap != null) {
					uriResourceMap.putAll(userResourceMap);
				}
			}
			for (Resource resource : userResourceSet.getResources()) {
				Resource old = uriResourceMap.put(resource.getURI(), resource);
				assert (old == null) || (old == resource);
			}
		}
		else {
			Map<String, Object> extensionToFactoryMap = externalResourceFactoryRegistry.getExtensionToFactoryMap();
			extensionToFactoryMap.put("ecore", new EcoreResourceFactoryImpl()); //$NON-NLS-1$
			extensionToFactoryMap.put("emof", new EMOFResourceFactoryImpl()); //$NON-NLS-1$
		}
		return externalResourceSet;
	}

	/**
	 * @since 1.7
	 */
	@Override
	public @NonNull FlowAnalysis createFlowAnalysis(@NonNull OCLExpression contextExpression) {
		return new FlowAnalysis(this, contextExpression);
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
	public @NonNull MetamodelManager createMetamodelManager() {
		assert metamodelManager == null;
		metamodelManager = new PivotMetamodelManager(this, asResourceSet);
		assert metamodelManager != null;
		return metamodelManager;
	}

	protected @NonNull ModelManager createModelManager() {
		return NullModelManager.INSTANCE;
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
			return new LazyEcoreModelManager((EObject)object);
		}
		return NullModelManager.INSTANCE;
	}

	@Override
	public @NonNull OCLInternal createOCL() {
		return new OCLInternal(this);
	}

	/**
	 * Return a ParserContext suitable for parsing OCL expressions in the context of a pivot element.
	 *
	 * @since 1.4
	 */
	@Override
	public @Nullable ParserContext createParserContext(@NonNull Element element) {
		Element pivotElement = element;
		if (element instanceof ExpressionInOCL) {
			EObject pivotContainer = pivotElement.eContainer();
			if (pivotContainer instanceof Operation) {							// Operation.bodyExpression
				Operation pivotOperation = (Operation) pivotContainer;
				return new OperationContext(this, null, pivotOperation, null);
			}
			if (pivotContainer instanceof Property) {
				Property pivotProperty = (Property) pivotContainer;
				return new PropertyContext(this, null, pivotProperty);
			}
			if (pivotContainer instanceof Constraint) {							// Operation.pre/postCondition
				EObject pivotContainerContainer = pivotContainer.eContainer();
				if (pivotContainerContainer instanceof Operation) {
					Operation pivotOperation = (Operation) pivotContainerContainer;
					String resultName = null;
					if (pivotOperation.getOwnedPostconditions().contains(pivotContainer)) {
						Type resultType = pivotOperation.getType();
						if ((resultType != null) && !(resultType instanceof VoidType)) {
							resultName = PivotConstants.RESULT_NAME;
						}
					}
					return new OperationContext(this, null, pivotOperation, resultName);
				}
				if (pivotContainerContainer instanceof org.eclipse.ocl.pivot.Class) {
					org.eclipse.ocl.pivot.Class pivotType = (org.eclipse.ocl.pivot.Class) pivotContainerContainer;
					return new ClassContext(this, null, pivotType, null);
				}
			}
			if (pivotContainer instanceof Slot) {
				Property asDefiningFeature = ((Slot)pivotContainer).getDefiningProperty();
				if (asDefiningFeature != null) {
					org.eclipse.ocl.pivot.Class pivotType = asDefiningFeature.getOwningClass();
					if (pivotType != null) {
						return new ClassContext(this, null, pivotType, null);
					}
				}
			}
		}
		//
		//	The JUnit tests are satisfied by the new code above. The following provides legacy support, perhaps satisfying unusual invocations
		//
		if (pivotElement instanceof Constraint) {
			EObject pivotContainer = pivotElement.eContainer();
			if (pivotContainer instanceof Operation) {
				Operation pivotOperation = (Operation) pivotContainer;
				String resultName = null;
				if (pivotOperation.getOwnedPostconditions().contains(pivotElement)) {
					Type resultType = pivotOperation.getType();
					if ((resultType != null) && !(resultType instanceof VoidType)) {
						resultName = PivotConstants.RESULT_NAME;
					}
				}
				return new OperationContext(this, null, pivotOperation, resultName);
			}
		}

		if (pivotElement instanceof Property) {
			return new PropertyContext(this, null, (Property) pivotElement);
		}
		else if (pivotElement instanceof Operation) {
			return new OperationContext(this, null, (Operation) pivotElement, null);
		}
		else if (pivotElement instanceof OppositePropertyCallExp) {
			Property referredOppositeProperty = ((OppositePropertyCallExp) pivotElement).getReferredProperty();
			if (referredOppositeProperty != null) {
				Property referredProperty = referredOppositeProperty.getOpposite();
				if (referredProperty != null) {
					return new PropertyContext(this, null, referredProperty);
				}
			}
		}
		else if (pivotElement instanceof PropertyCallExp) {
			Property referredProperty = ((PropertyCallExp) pivotElement).getReferredProperty();
			if (referredProperty != null) {
				return new PropertyContext(this, null, referredProperty);
			}
		}
		else if (pivotElement instanceof OperationCallExp) {
			Operation referredOperation = ((OperationCallExp) pivotElement).getReferredOperation();
			if (referredOperation != null) {
				return new OperationContext(this, null, referredOperation, null);
			}
		}
		else if (pivotElement instanceof LoopExp) {
			Iteration referredIteration = ((LoopExp) pivotElement).getReferredIteration();
			if (referredIteration != null) {
				return new OperationContext(this, null, referredIteration, null);
			}
		}
		//		else if (pivotElement instanceof Stereotype) {
		//			Stereotype pivotStereotype = (Stereotype) pivotElement;
		//			return new ClassContext(this, null, pivotStereotype);
		//		}
		//		else if (pivotElement instanceof org.eclipse.ocl.pivot.Class) {
		//			org.eclipse.ocl.pivot.Class pivotClass = (org.eclipse.ocl.pivot.Class) pivotElement;
		////			Metaclass<?> metaClass = getMetaclass(pivotClass);
		//			return new ClassContext(this, null, pivotClass);
		//		}
		else {		// Class, Stereotype, State
			for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
				if ((eObject instanceof org.eclipse.ocl.pivot.Class) && (((org.eclipse.ocl.pivot.Class)eObject).getOwningPackage() != null)) {	// StateMachines etc do not have Packages
					return new ClassContext(this, null, (org.eclipse.ocl.pivot.Class)eObject, null);
				}
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull CompleteStandardLibrary createStandardLibrary() {
		return PivotFactory.eINSTANCE.createCompleteStandardLibrary();
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull TemplateParameterSubstitutionVisitor createTemplateParameterSubstitutionVisitor(
			@Nullable Type selfType, @Nullable Type selfTypeValue) {
		return new PivotTemplateParameterSubstitutionVisitor(this, selfType, null);
	}

	protected @NonNull HashMap<Object, StatusCodes.Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public synchronized void detach(@NonNull Object attachOwner) {
		if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
			ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " Detach(" + attachCount + ":" + (attachCount-1) + ") " + toDebugString() + " " + NameUtil.debugSimpleName(attachOwner));
		}
		if (isDisposed()) {
			return;					// Ignore detach after dispose
		}
		if (attachCount == 0) {
			throw new IllegalStateException(getClass().getName() + " not attached");
		}
		@SuppressWarnings("unused")
		boolean wasRemoved = attachOwners.remove(Integer.valueOf(System.identityHashCode(attachOwner)));
	// assert wasRemoved;
		if (--attachCount <= 0) {
			dispose();
		}
	}

	@Override
	public void detachRedundantThreadLocal() {
		if (attachCount == 1) {
			ThreadLocalExecutor.detachEnvironmentFactory(this);
		}
	}

	@Override
	public void dispose() {
		if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
			ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " Dispose(" + attachCount + ") " + toDebugString());
		}
		if (isDisposed()) {
			throw new IllegalStateException(getClass().getName() + " already disposed");
		}
		//	attachCount = -1;
		isDisposing = true;
		try {
			List<@NonNull Resource> asResources = asResourceSet.getResources();
			int savedSize = asResources.size();
			for (int i = 0; i < asResources.size(); i++) {
				@NonNull Resource asResource = asResources.get(i);
				if (i >= savedSize) {			// Observed to happen in testQVTiInterpreter_HSV2HSL when OCLmetaModel not eagerly loaded.
					logger.warn("Additional AS resource appeared during preUnload : '" + asResource.getURI() + "'");
				}
				if ((asResource.getResourceSet() != null) && (asResource instanceof ASResourceImpl)) {			// Ignore built-in resources
					ASResourceImpl asResourceImpl = (ASResourceImpl)asResource;
					if (!asResourceImpl.isASonly()) {
						asResourceImpl.preUnload(this);
					}
				}
			}
		}
		finally {		// Even in preUnload crashes proceed with dispose
			disposeInternal();
		}
	}

	protected void disposeInternal() {
		assert !isDisposed() && isDisposing();
	//	ThreadLocalExecutor.removeEnvironmentFactory(this);  -- maybe wrong thread if GCed - wait for lazy isDisposed() test
		try {
			if (metamodelManager != null) {
				metamodelManager.dispose();
				metamodelManager = null;
			}
			attachCount = -1;		// Wait in isDisposing() state while unload proxifies
			EList<Adapter> externalResourceSetAdapters = externalResourceSet.eAdapters();
			//			System.out.println("dispose CS " + ClassUtil.debugSimpleName(externalResourceSet));
			projectManager.unload(externalResourceSet);
			externalResourceSetAdapters.remove(projectManager);							// cf PivotTestSuite.disposeResourceSet
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
			if (idResolver != null) {
				idResolver.dispose();
				idResolver = null;
			}
			if (csi2asMapping != null) {
				csi2asMapping.dispose();
				csi2asMapping = null;
			}
		//	completeEnvironment = null;
		//	standardLibrary = null;
		//	completeModel = null;
			//		if (ENVIRONMENT_FACTORY_ATTACH.isActive()) {
			//			ENVIRONMENT_FACTORY_ATTACH.println(ThreadLocalExecutor.getBracketedThreadName() + " disposeInternal " + toDebugString() + " => " + NameUtil.debugSimpleName(PivotUtil.findEnvironmentFactory(externalResourceSet)));
			//		}

			projectManager.unload(asResourceSet);
			projectManager.unload(externalResourceSet);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		finally {
			attachCount = -1;		// Wait in isDisposing() state while unload proxifies
			ThreadLocalExecutor.detachEnvironmentFactory(this);
		//	System.gc();
		//	System.runFinalization();
		}
	}

/*	@Override
	protected void finalize() throws Throwable {
//		PivotUtil.debugPrintln("Finalize " + toDebugString());
		diagnoseLiveEnvironmentFactories(this);
	} */

	/**
	 * Return the pivot model class for className with the Pivot Model.
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className) {
		return getMetamodelManager().getASClass(className);
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @Nullable <T extends Element> T getASOf(@NonNull Class<T> pivotClass, @Nullable EObject eObject) throws ParserException {
		if (eObject != null) {
			Resource eResource = eObject.eResource();
			ASResourceFactory bestHelper = eResource != null ? ASResourceFactoryRegistry.INSTANCE.getASResourceFactory(eResource) : EcoreASResourceFactory.getInstance();
			if (bestHelper != null) {
				return bestHelper.getASElement(this, pivotClass, eObject);
			}
		}
		return null;
	}

	/**
	 * @since 1.10
	 */
	@Override
	public @NonNull ResourceSet getASResourceSet() {
		return asResourceSet;
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
		MetamodelManager metamodelManager = getMetamodelManager();
		org.eclipse.ocl.pivot.Class dClass = getIdResolver().getStaticClassOf(context);
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

	/**
	 * @since 7.0
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type asInstanceType) {
		if (asInstanceType instanceof ElementExtension) {
			Stereotype asStereotype = ((ElementExtension)asInstanceType).getStereotype();
			return asStereotype != null ? asStereotype : standardLibrary.getOclInvalidType();
		}
		EClass eClass = asInstanceType.eClass();
		String metaclassName = eClass.getName();
		EPackage ePackage = eClass.getEPackage();
		assert ePackage != null;
		Model asMetamodel = getMetamodel(ePackage);
		for (org.eclipse.ocl.pivot.Package asMetapackage : asMetamodel.getOwnedPackages()) {
			org.eclipse.ocl.pivot.Class asMetaClass = NameUtil.getNameable(asMetapackage.getOwnedClasses(), metaclassName);
			if (asMetaClass != null) {
				return asMetaClass;
			}
		}
	/*	if (ePackage == )
		String metaPackageName = ePackage.getName().toLowerCase();
	//	CompleteClassInternal completeClass = completeModel.getCompleteClass(asInstanceType);
	//	CompletePackageInternal completePackage = completeClass.getOwningCompletePackage();
	//	String metaPackageName = completePackage.getMetaPackageName();
		Iterable<@NonNull CompletePackage> metaCompletePackages = completeModel.getAllMetaCompletePackages(metaPackageName);
		if (metaCompletePackages != null) {
			for (CompletePackage metaCompletePackage : metaCompletePackages) {
				CompleteClass metaCompleteClass = metaCompletePackage.getOwnedCompleteClass(metaClassName);
				if (metaCompleteClass != null) {
					return metaCompleteClass.getPrimaryClass();
				}
			}
		} */
		throw new InvalidValueException("Metaclass '" + metaclassName + "' not found in '" + asMetamodel);
	}

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull Model getMetamodel(@NonNull EPackage ePackage);

	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		MetamodelManager metamodelManager2 = metamodelManager;
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
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		return standardLibrary;
	}

	@Override
	public @NonNull Technology getTechnology() {
		return technology;
	}

	/**
	 * @since 1.23
	 */
	@Override
	public @NonNull ResourceSet getUserResourceSet() {
		return userResourceSet != null ? userResourceSet : externalResourceSet;
	}

	@Override
	public boolean isDisposed() {
		return attachCount < 0;
	}

	/**
	 * @since 1.22
	 */
	@Override
	public boolean isDisposing() {
		return isDisposing;
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
	public @Nullable ASResource loadCompleteOCLResource(@NonNull EPackage ePackage, @NonNull URI oclURI) throws ParserException {
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return null;
		}
		External2AS ecore2as = External2AS.findAdapter(ecoreResource, this);
		if (ecore2as != null) {
			if (ecore2as.getResource() != ecoreResource) {
				throw new ParserException(StringUtil.bind(PivotMessages.ConflictingResource, ecoreResource.getURI()));
			}
		}
		else {
			ecore2as = External2AS.getAdapter(ecoreResource, this);
			List<Diagnostic> errors = ecoreResource.getErrors();
			assert errors != null;
			String message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
			if (message != null) {
				throw new ParserException("Failed to load Ecore '" + ecoreResource.getURI() + message);
			}
		}
		Model pivotModel = ecore2as.getASModel();				// XXX only need ASResource
		List<Diagnostic> errors = pivotModel.eResource().getErrors();
		assert errors != null;
		String message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			throw new ParserException("Failed to load Pivot from '" + ecoreResource.getURI() + message);
		}
		CSResource xtextResource = (CSResource)externalResourceSet.getResource(oclURI, true);
		errors = xtextResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			throw new ParserException("Failed to load '" + oclURI + message);
		}
		ICS2AS cs2as = xtextResource.getCS2AS(this);
		ASResource asResource = cs2as.getASResource();
		errors = asResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			throw new ParserException("Failed to load Pivot from '" + oclURI + message);
		}
		return asResource;
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

	/**
	 * Return the compiled query for a specification resolving a String body into a non-null bodyExpression.
	 * Throws a ParserException if conversion fails.
	 *
	 * @since 1.4
	 */
	@Override
	public @NonNull ExpressionInOCL parseSpecification(@NonNull LanguageExpression specification) throws ParserException {
		if ((specification instanceof ExpressionInOCL) && ((ExpressionInOCL)specification).getOwnedBody() != null) {
			return (ExpressionInOCL)specification;
		}
		EObject contextElement = ClassUtil.requireNonNull(specification.eContainer());
		String expression = specification.getBody();
		if (expression == null) {
			throw new ParserException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, NameUtil.qualifiedNameFor(contextElement), PivotUtil.getSpecificationRole(specification));
		}
	//	expression = PivotUtil.getBodyExpression(expression);
		ParserContext parserContext = createParserContext(specification);
		if (parserContext == null) {
			throw new ParserException(PivotMessagesInternal.UnknownContextType_ERROR_, NameUtil.qualifiedNameFor(contextElement), PivotUtil.getSpecificationRole(specification));
		}
		parserContext.setRootElement(specification);
		return parserContext.parse(contextElement, expression);
	}

	/**
	 * @since 1.17
	 */
	@Override
	public void preDispose() {
		if (attachCount >= 2) {
			if (ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.isActive()) {
				ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.println(ThreadLocalExecutor.getBracketedThreadName() + " gc()-" + 0);
			}
			System.gc();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
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
		for (EOperation key : PivotValidationOptions.safeValidationOperation2severityOption.keySet()) {
			if (key != null) {
				setSeverity(key, severity);
			}
		}
	}

	@Override
	public synchronized StatusCodes.@Nullable Severity setSeverity(/*@NonNull*/ Object validationKey, StatusCodes.@Nullable Severity severity) {
		Map<Object, StatusCodes.Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.put(validationKey, severity);
	}

	/**
	 * @since 1.20
	 */
	public Object toDebugString() {
		return NameUtil.debugSimpleName(this) + "(" + attachCount + ")";
	}

	/**
	 * @since 7.0
	 */
	public void unload(@NonNull CSResource csResource) {
		ICSI2ASMapping csi2asMapping2 = csi2asMapping;
		assert csi2asMapping2 != null;
		ASResource asResource = csi2asMapping2.getASResource(csResource);
		assert asResource != null;
		Model asModel = PivotUtil.getModel(asResource);
		completeModel.getPartialModels().remove(asModel);
		asResource.unload();
		asResourceSet.getResources().remove(asResource);
		csi2asMapping2.removeCSResource(csResource);
		csResource.unload();
		externalResourceSet.getResources().remove(csResource);
	}
}
