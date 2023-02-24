/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * AbstractListeners maintains a list of IAbstractListener using WeakReference to allow stale lisyening to be pruned
 * during the next iteration. Derived IAbstractListener and correspondingly derived AbstractListeners classes define
 * what can be listened to.
 */
public abstract class AbstractListeners<L extends AbstractListeners.IAbstractListener>
{
	public static interface IAbstractListener {}

	protected final @NonNull List<@NonNull WeakReference<L>> listeners = new ArrayList<@NonNull WeakReference<L>>();

	/**
	 * Add aListener returning true if actually added, false if already a listener.
	 */
	public synchronized boolean addListener(@NonNull L aListener) {
		for (int i = listeners.size(); --i >= 0; ) {			// Down scan to avoid CME from removal of stale entry
			@NonNull WeakReference<L> ref = listeners.get(i);
			@Nullable L listener = ref.get();
			if (listener == aListener) {
				return false;
			}
			if (listener == null) {
				listeners.remove(i);
			}
		}
		listeners.add(new WeakReference<L>(aListener));
		return true;
	}

	protected void doFlush() {				// FIXME automate as part of custom lister-list -- obsolete with down-scan iteration
		for (int i = listeners.size(); --i >= 0; ) {
			@NonNull WeakReference<L> ref = listeners.get(i);
			@Nullable L listener = ref.get();
			if (listener == null) {
				listeners.remove(i);
			}
		}
	}

	/**
	 * Remove a listener and return true if there are no listeners left.
	 */
	public synchronized boolean removeListener(@NonNull L aListener) {
		for (int i = listeners.size(); --i >= 0; ) {			// Down scan to avoid CME from removal of stale entry
			@NonNull WeakReference<L> ref = listeners.get(i);
			@Nullable L listener = ref.get();
			if ((listener == null) || (listener == aListener)) {
				listeners.remove(i);
			}
		}
		return listeners.size() <= 0;
	}
}