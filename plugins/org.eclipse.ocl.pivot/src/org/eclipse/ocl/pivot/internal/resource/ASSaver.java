/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ASSaverNormalizeVisitor;

/**
 * ASSaverNew ensures that all references to synthesized types are terminated by local copies of the synthesized types.
 * This rewrite of ASSaver uses an EcoreUtil.Copier and EcoreUtil.CrossReferencer guaranteeing correct operation for all
 * references without requiring ASSaverLOcateVisitor or ASSaverResolveVisitor derivations with accurate overloading for
 * all references. ASSaver may well be faster but it is fragile and the improved performance is not justified.
 *
 * @since 1.18
 */
public class ASSaver
{
	/**
	 * @since 7.0
	 */
	@SuppressWarnings("serial")
	@Deprecated
	protected static class ASSaverCopier extends EcoreUtil.Copier
	{
		protected ASSaverCopier(@NonNull ASResource resource, boolean resolveProxies) {
			super(resolveProxies);
		}

		@Override
		public EObject copy(EObject eObject) {
			if (eObject instanceof NormalizedTemplateParameter) {
				return super.copy(eObject);
			}
			assert !(eObject instanceof TemplateParameter);		// Generalized class never needs localizing.
			return super.copy(eObject);
		}

		@Override
		protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
			if (eReference.isMany() && (eReference.getEOpposite() == null)) {
				@SuppressWarnings("unchecked")
				List<EObject> copyValues = (List<EObject>)copyEObject.eGet(eReference);
				copyValues.clear();						// Avoid dupicate superclasses when reloading
			}
			super.copyReference(eReference, eObject, copyEObject);
		}
	}

	/**
	 * @since 7.0
	 */
	@Deprecated
	public static class ASSaverWithInverse extends ASSaver
	{
	//	private final @NonNull Map<@NonNull EObject, @NonNull EObject> target2source = new HashMap<>();

		public ASSaverWithInverse(@NonNull ASResource resource) {
			super(resource);
		}

		@Override
		protected @NonNull ASSaverCopier createCopier(@NonNull ASResource resource) {
			return new ASSaverCopier(resource, true)
			{
				@Override
				public EObject put(EObject key, EObject value) {
					assert (key != null) && (value != null);
				//	EObject old = target2source.put(value, key);
				//	assert (old == null) || (old == key);
					return super.put(key, value);
				}

			};
		}
	}

	/**
	 * @since 7.0
	 */
	protected static class ClassByTypeIdAndEntryClassComparator implements Comparator<org.eclipse.ocl.pivot.@NonNull Class>
	{
		@Override
		public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
			TypeId t1 = o1.getTypeId();
			TypeId t2 = o2.getTypeId();
			String s1 = t1.toString();
			String s2 = t2.toString();
			int compareTo = s1.compareTo(s2);
			if (compareTo != 0) {
				return compareTo;
			}
			if ((o1 instanceof MapType) && (o2 instanceof MapType)) {
				org.eclipse.ocl.pivot.Class ec1 = ((MapType)o1).getEntryClass();
				org.eclipse.ocl.pivot.Class ec2 = ((MapType)o2).getEntryClass();
				if (ec1 == null) {
					if (ec2 != null) {
						return -1;
					}
				}
				else {
					if (ec2 == null) {
						return 1;
					}
					else {
						t1 = ec1.getTypeId();
						t2 = ec2.getTypeId();
						s1 = t1.toString();
						s2 = t2.toString();
						compareTo = s1.compareTo(s2);
					}
				}
			}
			return compareTo;
		}
	}

	private final @NonNull Resource resource;

	/**
	 * The appropriate normalization visitor for each Resource.
	 * @since 7.0
	 */
	private /*@LazyNonNull*/ Map<@NonNull Resource, @NonNull ASSaverNormalizeVisitor> resource2normalizeVisitor;

	private final @NonNull SaverStandardLibraryImpl localLibrary;

	/**
	 * @since 7.0
	 */
	public ASSaver(@NonNull ASResource resource) {
		this.resource = resource;
		this.localLibrary = new SaverStandardLibraryImpl(resource);
	}

	/**
	 * @since 7.0
	 */
	public @Nullable EObject basicGetSource(@NonNull EObject target) {
		return localLibrary.getLocal(target);
	//	return target2source.get(target);
	}

	/**
	 * @since 7.0
	 *
	public @NonNull EObject getSource(@NonNull EObject target) {		// XXX bad name
		return ClassUtil.requireNonNull(localLibrary.getLocal(target));
	} */

	/**
	 * @since 7.0
	 */
	protected @NonNull ASSaverCopier createCopier(@NonNull ASResource resource) {
		return new ASSaverCopier(resource, true);
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull ASSaverNormalizeVisitor getNormalizeVisitor(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource == null) {
			throw new IllegalStateException("Cannot locate " + ASSaverNormalizeVisitor.class.getName() + " for resource-less " + eObject.eClass().getName());
		}
		if (resource2normalizeVisitor == null) {
			resource2normalizeVisitor = new HashMap<>();
		}
		ASSaverNormalizeVisitor visitor = resource2normalizeVisitor.get(resource);
		if (visitor != null) {
			return visitor;
		}
		if (resource instanceof ASResource) {
			ASResource asResource = (ASResource)resource;
			visitor = asResource.getASResourceFactory().createASSaverNormalizeVisitor(this);
			resource2normalizeVisitor.put(resource, visitor);
			return visitor;
		}
		else {
			throw new IllegalStateException("Cannot locate " + ASSaverNormalizeVisitor.class.getName() + " for non-OCL " + resource.getClass().getName());
		}
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Resource getResource() {
		return resource;
	}

	/**
	 * Prepare a pivot resource for save by redirecting all type references to shared orphans to local copies of the orphans.
	 * @since 7.0
	 */
	public void localizeOrphans() {
		localLibrary.localizeOrphans();
	}

	/**
	 * @since 7.0
	 */
	public void normalizeContents() {
		List<@NonNull EObject> allContents = new ArrayList<>();
		for (@NonNull TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Visitable) {
				allContents.add(eObject);
			}
		}
		Map<EClass, @NonNull ASSaverNormalizeVisitor> eClass2normalizeVisitor = new HashMap<>();
		for (@NonNull EObject eObject : allContents) {
			EClass eClass = eObject.eClass();
			ASSaverNormalizeVisitor normalizeVisitor = eClass2normalizeVisitor.get(eClass);
			if (normalizeVisitor == null) {
				normalizeVisitor = getNormalizeVisitor(eObject);
				eClass2normalizeVisitor.put(eClass, normalizeVisitor);
			}
			normalizeVisitor.safeVisit((Visitable) eObject);
		}
	}

	/**
	 * Return the localized variant of eObject. If eObject is an orphan, localizeSpecializations should have created
	 * a local copy that is returned here. Else returns eObject.
	 * @since 7.0
	 */
	public @Nullable EObject resolveOrphan(@NonNull EObject eObject) {
		return localLibrary.resolveOrphan(eObject);
	}
}