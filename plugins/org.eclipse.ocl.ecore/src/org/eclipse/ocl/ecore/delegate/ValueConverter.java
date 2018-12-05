/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   C.Damus - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.ocl.ecore.OCL;

/**
 * An object that converts values according to their declared multiplicities,
 * as collections or not.
 * 
 * @since 3.0
 */
interface ValueConverter {

	ValueConverter VERBATIM = new ValueConverter() {

		public Object convert(OCL ocl, Object value) {
			return value;
		}
	};

	ValueConverter LIST = new ValueConverter() {

		public Object convert(OCL ocl, Object value) {
			Collection<?> collection = (Collection<?>) value;
			return new BasicEList.UnmodifiableEList<Object>(collection
				.size(), collection.toArray());
		}
	};

	Object convert(OCL ocl, Object value);
}
