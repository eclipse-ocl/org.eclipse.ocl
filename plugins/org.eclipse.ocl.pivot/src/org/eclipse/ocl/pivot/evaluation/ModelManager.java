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
package org.eclipse.ocl.pivot.evaluation;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * ModelManager provides the models to be used during evaluation. In particular
 * the managed models constitute the extent from which Classifier.allInstances
 * returns are made.
 */
public interface ModelManager
{
	/**
	 * @since 1.1
	 */
	public interface ModelManagerExtension extends ModelManager
	{
		void dispose();
		@NonNull TreeIterator<? extends Object> eAllContents(@NonNull Object object);
		@NonNull EClass eClass(@NonNull Object object);
		@Nullable Object eContainer(@NonNull Object object);
		@Nullable Object eGet(@NonNull Object object, @NonNull EStructuralFeature eFeature);
	}
	
	@NonNull ModelManager NULL = new ModelManager()
	{
		@Override
		public @NonNull Set<@NonNull ? extends Object> get(org.eclipse.ocl.pivot.@NonNull Class type) {
			return Collections.<@NonNull Object>emptySet();
		}
	};

	@NonNull Set<@NonNull ? extends Object> get(org.eclipse.ocl.pivot.@NonNull Class type);
}
