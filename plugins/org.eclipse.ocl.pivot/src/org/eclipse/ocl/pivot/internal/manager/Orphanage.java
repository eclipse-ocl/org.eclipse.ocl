/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.AbstractEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * An Orphanage provides a Package that weakly contains elements such as type specializations that
 * should require a container for the purposes of validation, but which should be eligible for
 * garbage collection whenever no longer in use.
 */
public class Orphanage extends PackageImpl
{
	protected static class OrphanResource extends ASResourceImpl
	{
		protected OrphanResource(@NonNull URI uri) {
			super(uri, OCLASResourceFactory.getInstance());
			setSaveable(false);
		}

		@Override
		protected void doUnload() {
			if (contents != null) {
				for (EObject aContent : contents) {
					if (aContent instanceof Orphanage) {
						((Orphanage)aContent).dispose();
					}
				}
				contents = null;
			}
			//			super.doUnload();
		}

		@Override
		public boolean isOrphanage() {
			return true;
		}
	}

	/**
	 * WeakEList enables a WeakHashMap to be used as a Package.ownedType. The weakness allows stale synthesized types to vanish.
	 * The Map ensures that duplicates are avoided.
	 * <br>
	 * Only the minimal support necessary to make aType.setPackage() and subsequent usage is provided.
	 * <br>
	 * A cached sorted list copy of the map is created on demand and may be shared by multiple iterators. However it must not be modified
	 * since its staleness is detected by a simple size comparison with the map.
	 */
	private static class WeakEList<T> extends AbstractEList<T> implements InternalEList<T>
	{
		/**
		 * A simple immutable iterator that caches the list image on construction to avoid changes.
		 */
		protected static class ListIterator<T> implements java.util.ListIterator<T>
		{
			protected final @NonNull List<Map.Entry<T,Integer>> list;
			private final int size;
			private int cursor;

			public ListIterator(@NonNull List<Map.Entry<T,Integer>> list, int index) {
				this.list = list;
				this.size = list.size();
				this.cursor = index;
				if ((cursor < 0) || (size < cursor)) {
					throw new NoSuchElementException(cursor + "/" + size);
				}
			}

			@Override
			public void add(T o) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean hasNext() {
				return cursor < size;
			}

			@Override
			public boolean hasPrevious() {
				return 0 < cursor;
			}

			@Override
			public T next() {
				if ((cursor < 0) || (size <= cursor)) {
					throw new NoSuchElementException(cursor + "/" + size);
				}
				return list.get(cursor++).getKey();
			}

			@Override
			public int nextIndex() {
				return cursor;
			}

			@Override
			public T previous() {
				int previousCursor = cursor - 1;
				if ((previousCursor < 0) || (size <= previousCursor)) {
					throw new NoSuchElementException(previousCursor + "/" + size);
				}
				cursor = previousCursor;
				return list.get(previousCursor).getKey();
			}

			@Override
			public int previousIndex() {
				return cursor - 1;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(T o) {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Map of content-value to list-index.
		 */
		private final @NonNull WeakHashMap<T, Integer> weakMap = new WeakHashMap<T, Integer>();

		/**
		 * Incrermenting counter used to sort the list into a predictable order.
		 */
		private int counter = 0;

		/**
		 * The most recent ordered view of the weakMap.
		 */
		private @Nullable List<Entry<T, Integer>> weakList = null;

		@Override
		public boolean addAllUnique(Object[] objects, int start, int end) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAllUnique(int index, Object[] objects, int start, int end) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAllUnique(Collection<? extends T> collection) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAllUnique(int index, Collection<? extends T> collection) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addUnique(T object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addUnique(int index, T object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public NotificationChain basicAdd(T object, NotificationChain notifications) {
			synchronized (weakMap) {
				if (!weakMap.containsKey(object)) {
					weakMap.put(object, Integer.valueOf(counter++));
					weakList = null;
				}
			}
			return notifications;
		}

		@Override
		public boolean basicContains(Object object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean basicContainsAll(Collection<?> collection) {
			throw new UnsupportedOperationException();
		}

		@Override
		public T basicGet(int index) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int basicIndexOf(Object object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<T> basicIterator() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int basicLastIndexOf(Object object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<T> basicList() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ListIterator<T> basicListIterator() {
			return listIterator();
		}

		@Override
		public ListIterator<T> basicListIterator(int index) {
			return listIterator(index);
		}

		@Override
		public NotificationChain basicRemove(Object object, NotificationChain notifications) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object[] basicToArray() {
			throw new UnsupportedOperationException();
		}

		@Override
		public <T2> T2[] basicToArray(T2[] array) {
			throw new UnsupportedOperationException();
		}

		public void dispose() {
			synchronized (weakMap) {
				weakMap.clear();
				weakList = null;
			}
		}

		@Override
		public T get(int index) {
			List<Entry<T, Integer>> weakList2 = getList();
			return weakList2.get(index).getKey();
		}

		private @NonNull List<Entry<T, Integer>> getList() {
			List<Entry<T, Integer>> weakList2;
			synchronized (weakMap) {
				weakList2 = weakList;
				if (weakList2 == null) {
					weakList2 = weakList = new ArrayList<Entry<T, Integer>>(weakMap.entrySet());
					Collections.sort(weakList2, new Comparator<Entry<T, Integer>>()
					{
						@Override
						public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2) {
							return o1.getValue().intValue() - o2.getValue().intValue();
						}
					});
				}
			}
			return weakList2;
		}

		@Override
		public boolean isEmpty() {
			return weakMap.size() == 0;
		}

		//		@Override
		//		protected boolean isUnique() {
		//			return true;		-- implementing this makes things really really slow.
		//		}

		@Override
		public @NonNull Iterator<T> iterator() {
			return listIterator();
		}

		@Override
		public @NonNull ListIterator<T> listIterator() {
			return new ListIterator<T>(getList(), 0);
		}

		@Override
		public @NonNull ListIterator<T> listIterator(int index) {
			return new ListIterator<T>(getList(), index);
		}

		@Override
		public void move(int newPosition, T object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public T move(int newPosition, int oldPosition) {
			throw new UnsupportedOperationException();
		}

		@Override
		protected T primitiveGet(int index) {
			throw new UnsupportedOperationException();
		}

		@Override
		public T remove(int index) {
			throw new UnsupportedOperationException();
		}

		@Override
		public T setUnique(int index, T object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			return weakMap.size();
		}

		@Override
		public String toString() {
			return weakMap.toString();
		}
	}

	public static final @NonNull URI ORPHANAGE_URI = ClassUtil.nonNullEMF(URI.createURI(PivotConstants.ORPHANAGE_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION));
	private static Orphanage INSTANCE = null;

	public static void disposeInstance() {
		if (INSTANCE != null) {
			INSTANCE.dispose();
			INSTANCE = null;
		}
	}

	/**
	 * Return the Orphanage for an eObject, which is the Orphanage resource in the same ResourceSet as
	 * the eObject, else the global Orphanage.
	 */
	public static @Nullable Orphanage getOrphanage(@NonNull EObject eObject) {
		//		if (eObject == null) {
		//			return null;
		//		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		return getOrphanage(resourceSet);
	}

	/**
	 * Return the Orphanage for an eObject, which is the Orphanage resource in the resourceSet
	 * if non-null, else the global Orphanage.
	 */
	public static @NonNull Orphanage getOrphanage(@Nullable ResourceSet resourceSet) {
		if (resourceSet == null) {
			Orphanage instance2 = INSTANCE;
			if (instance2 == null) {
				instance2 = INSTANCE = new Orphanage(PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI);
			}
			return instance2;
		}
		for (Resource aResource : resourceSet.getResources()) {
			for (EObject eContent : aResource.getContents()) {
				if (eContent instanceof Model) {
					for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eContent).getOwnedPackages()) {
						if (asPackage instanceof Orphanage) {
							return (Orphanage) asPackage;
						}
					}
				}
			}
		}
		Orphanage orphanage = new Orphanage(PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI);
		Model orphanModel = PivotFactory.eINSTANCE.createModel();
		orphanModel.setName(PivotConstants.ORPHANAGE_NAME);;
		orphanModel.setExternalURI(PivotConstants.ORPHANAGE_URI);
		orphanModel.getOwnedPackages().add(orphanage);
		Resource orphanageResource = new OrphanResource(ORPHANAGE_URI);
		orphanageResource.getContents().add(orphanModel);
		resourceSet.getResources().add(orphanageResource);
		return orphanage;
	}

	/**
	 * Return true if asPackage is an orphanage for synthesized types.
	 */
	public static boolean isTypeOrphanage(org.eclipse.ocl.pivot.@Nullable Package asPackage) {
		if (asPackage == null) {
			return false;
		}
		else {
			String uri = asPackage.getURI();
			return PivotConstants.ORPHANAGE_URI.equals(uri) || PivotConstantsInternal.OLD_ORPHANAGE_URI.equals(uri);
		}
	}

	public Orphanage(@NonNull String name, @NonNull String nsURI) {
		//		super(uri);
		//		setLoaded(true);
		setName(name);
		setURI(nsURI);
	}

	public void dispose() {
		if (ownedClasses != null) {
			((WeakEList<?>)ownedClasses).dispose();
		}
	}

	@Override
	public @NonNull EList<org.eclipse.ocl.pivot.Class> getOwnedClasses() {
		EList<org.eclipse.ocl.pivot.Class> ownedType2 = ownedClasses;
		if (ownedType2 == null)
		{
			ownedType2 = ownedClasses = new WeakEList<org.eclipse.ocl.pivot.Class>(/*WeakReference.class, this, PivotPackage.PACKAGE__OWNED_TYPE, PivotPackage.TYPE__PACKAGE*/);
		}
		return ownedType2;
	}
}