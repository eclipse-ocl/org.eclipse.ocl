/**
 * <copyright>
 *
 * Copyright (c) 2011, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.library.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainInheritance;
import org.eclipse.ocl.examples.domain.elements.DomainOperation;

public class DomainReflectiveFragment extends ReflectiveFragment
{
//	protected final EClassifier eClassifier;

	public DomainReflectiveFragment(@NonNull DomainReflectiveType derivedInheritance, @NonNull DomainInheritance baseInheritance) {
		super(derivedInheritance, baseInheritance);
//		this.eClassifier = derivedInheritance.getEClassifier();		
	}

//	public final EClassifier getEClassifier() {
//		return eClassifier;
//	}

	public @Nullable DomainOperation getLocalOperation(@NonNull DomainOperation baseOperation) {
		throw new UnsupportedOperationException();		// FIXME
	}
}