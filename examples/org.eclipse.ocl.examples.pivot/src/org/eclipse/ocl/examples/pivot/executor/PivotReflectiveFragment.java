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
 *
 * $Id$
 */
package org.eclipse.ocl.examples.pivot.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainInheritance;
import org.eclipse.ocl.examples.domain.elements.DomainOperation;
import org.eclipse.ocl.examples.domain.ids.ParametersId;
import org.eclipse.ocl.examples.domain.ids.TypeId;
import org.eclipse.ocl.examples.library.executor.ReflectiveFragment;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.manager.TypeServer;

public class PivotReflectiveFragment extends ReflectiveFragment
{
	public PivotReflectiveFragment(@NonNull TypeServer derivedInheritance, @NonNull DomainInheritance baseInheritance) {
		super(derivedInheritance, baseInheritance);
	}

	@Override
	protected @Nullable DomainOperation getOperationOverload(@NonNull DomainOperation baseOperation) {
		Type pivotType = ((TypeServer) derivedInheritance).getPivotType();
		ParametersId baseParametersId = baseOperation.getParametersId();
		int iMax = baseParametersId.size();
		for (DomainOperation localOperation : pivotType.getOwnedOperation()) {
			if (localOperation.getName().equals(baseOperation.getName())) {
				ParametersId localParametersId = localOperation.getParametersId();
				if (iMax == localParametersId.size()) {
					int i = 0;
					for (; i < iMax; i++) {
						TypeId localParameterTypeId = localParametersId.get(i);
						TypeId baseParameterTypeId = baseParametersId.get(i);
						if (!localParameterTypeId.equals(baseParameterTypeId)) {
							break;
						}
					}
					if (i >= iMax) {
						return localOperation;
					}
				}
			}
		}
		return null;					// Not known locally, caller must try superfragments.
	}
}