/*******************************************************************************
 * Copyright (c) 2011, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.LazyModelManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotObject;

/**
 * An EcoreExecutorManager instance provides the bridge between a conventional EMF execution context
 * and the richer OCL Pivot concepts. Since the OCL concepts are not needed for simple expressions
 * that make no use of types, the default construction is lightweight deferring construction costs
 * until actually needed.
 */
public class EcoreExecutorManager extends ExecutorManager
{
	private final @Nullable Object contextObject;
	private ModelManager modelManager = null;
	private /*@LazyNonNull*/ IdResolver idResolver = null;

	/**
	 * Construct an EMF to OCL execution bridge.
	 * <p>
	 * The user objects that contribute to allInstances are determined from the contents of all resources
	 * in the ResourceSet containing the user object (which should be an EObject).
	 * <p>
	 * The user meta-models are determined from the eClass()'s of all user objects and the transitive closure
	 * of all references.
	 * <p>
	 * Since determination of the domain of user models and meta-models can be expensive, the EcoreExecutorManager
	 * is cached for re-use as an adapter on the contextObject's ResourceSet.
	 *
	 * @param contextObject a user object from which the user objects and their meta-models will be deduced
	 * @param standardLibrary the OCL facilities
	 */
	public EcoreExecutorManager(@Nullable Object contextObject, @NonNull ExecutorStandardLibrary standardLibrary) {
		super(standardLibrary);
		this.contextObject = contextObject;
	}

	protected @NonNull IdResolver createIdResolver() {
		if (!(contextObject instanceof EObject)) {
			@NonNull List<EObject> emptyList = Collections.<EObject>emptyList();
			return new EcoreIdResolver(emptyList, getStandardLibrary());
		}
		EObject rootContainer = EcoreUtil.getRootContainer((EObject)contextObject);
		Notifier notifier;
		Resource resource = rootContainer.eResource();
		if (resource != null) {
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				notifier = resourceSet;
			}
			else {
				notifier = resource;
			}
		}
		else {
			notifier = rootContainer;
		}
		synchronized (notifier) {
			EList<Adapter> eAdapters = notifier.eAdapters();
			for (Adapter adapter : eAdapters) {
				if (adapter instanceof EcoreIdResolver) {
					return (EcoreIdResolver) adapter;
				}
			}
			List<EObject> roots = null;
			if (resource != null) {
				ResourceSet resourceSet = resource.getResourceSet();
				if (resourceSet != null) {
					roots = new ArrayList<EObject>();
					for (Resource r : resourceSet.getResources()) {
						roots.addAll(r.getContents());
					}
				}
				else {
					roots = resource.getContents();
				}
			}
			org.eclipse.ocl.pivot.Package root = standardLibrary.getPackage();
			if (root instanceof PivotObject) {
				if (roots == null) {
					roots = new ArrayList<EObject>();
				}
				roots.add(root);
			}
			if (roots == null) {
				roots = Collections.singletonList(rootContainer);
			}
			assert roots != null;
			EcoreIdResolver adapter = new EcoreIdResolver(roots, getStandardLibrary());
			eAdapters.add(adapter);
			return adapter;
		}
	}

	@Override
	public @NonNull Type getDynamicTypeOf(@Nullable Object value) {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2.getDynamicTypeOf(value);
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
	public @NonNull ModelManager getModelManager() {
		ModelManager modelManager2 = modelManager;
		if (modelManager2 == null) {
			synchronized (this) {
				modelManager2 = modelManager;
				if (modelManager2 == null) {
					if (contextObject instanceof EObject) {
						modelManager2 = new LazyModelManager((EObject)contextObject)
						{
							@Override
							protected boolean isInstance(@NonNull Type type, @NonNull EObject element) {
								EClass eClass = ClassUtil.nonNullEMF(element.eClass());
								Type elementType = idResolver.getInheritance(eClass).getPivotClass();
								return elementType.conformsTo(standardLibrary, type);
							}

						};
					}
					else {
						modelManager2 = ModelManager.NULL;
					}
					modelManager = modelManager2;
				}
			}
		}
		return modelManager2;
	}

	@Override
	public int getSeverity(@Nullable Object validationKey) {
		StatusCodes.Severity severity = getStandardLibrary().getSeverity(validationKey);
		return severity != null ? severity.getStatusCode() : StatusCodes.WARNING;
	}

	@Override
	public @NonNull ExecutorStandardLibrary getStandardLibrary() {
		return (ExecutorStandardLibrary)standardLibrary;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value) {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2.getStaticTypeOfValue(null, value);
	}

	@Override
	@Deprecated /* @deprecated getStaticTypeOfValue to enable TemplateParameters to be resolved */
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @Nullable Object @NonNull ... values) {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2.getStaticTypeOf(value, values);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Iterable<?> values) {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2.getStaticTypeOf(value, values);
	}

	/**
	 * @since 1.7
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOfValue(@Nullable Type staticType, @Nullable Object value) {
		IdResolver idResolver2 = idResolver;
		if (idResolver2 == null) {
			idResolver = idResolver2 = createIdResolver();
		}
		return idResolver2.getStaticTypeOfValue(staticType, value);
	}
}
