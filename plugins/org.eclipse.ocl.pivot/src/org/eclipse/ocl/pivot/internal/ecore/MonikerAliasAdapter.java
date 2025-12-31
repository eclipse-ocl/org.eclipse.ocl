/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A MonikerAliasAdapter extends a Resource to provide a set of aliases for elements,
 * typically packages, contained within that Resource. Use of an alias as the moniker
 * for a package simplifies the moniker and avoids prefix variation for monikers
 * computed for alternate domains.
 *
 * @since 7.0
 */
public class MonikerAliasAdapter extends AdapterImpl
{
	public static @Nullable MonikerAliasAdapter findAdapter(@Nullable Resource resource) {
		if (resource == null) {
			return null;
		}
		return ClassUtil.getAdapter(MonikerAliasAdapter.class, resource);
	}

	public static @NonNull MonikerAliasAdapter getAdapter(@NonNull Resource resource) {
		List<Adapter> eAdapters = ClassUtil.requireNonNull(resource.eAdapters());
		MonikerAliasAdapter adapter = ClassUtil.getAdapter(MonikerAliasAdapter.class, eAdapters);
		if (adapter == null) {
			adapter = new MonikerAliasAdapter();
			eAdapters.add(adapter);
		}
		return adapter;
	}

	public static String getAlias(EObject eElement) {
		String alias = null;
		MonikerAliasAdapter adapter = MonikerAliasAdapter.findAdapter(eElement.eResource());
		if (adapter != null) {
			alias = adapter.getAliasMap().get(eElement);
		}
		return alias;
	}

	private @NonNull Map<@NonNull EObject, @Nullable String> aliasMap = new HashMap<>();

	public @NonNull Map<@NonNull EObject, @Nullable String> getAliasMap() {
		return aliasMap;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == MonikerAliasAdapter.class;
	}
}