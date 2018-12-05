/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.util;

import org.eclipse.ocl.types.TupleType;

/**
 * Interface of a tuple instance value.  OCL expressions resulting in tuples
 * yield instances of this interface.
 * 
 * @author Christian W. Damus (cdamus)
 */
public interface Tuple<O, P> {
    /**
     * Obtains the tuple's type.
     * 
     * @return its type
     */
	TupleType<O, P> getTupleType();
	
    /**
     * Queries the value of the specified tuple part.
     * 
     * @param partName the name of the part
     * @return the corresponding value
     */
	Object getValue(String partName);
    
    /**
     * Queries the value of the specified tuple part.
     * 
     * @param part the tuple part (as an attribute)
     * @return the corresponding value
     */
	Object getValue(P part);
}
