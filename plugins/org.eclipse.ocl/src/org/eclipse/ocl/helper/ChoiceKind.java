/*******************************************************************************
 * Copyright (c) 2002, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.helper;


/**
 * An enumeration of OCL syntax completion {@linkplain Choice choice} types.
 *
 * @see Choice
 * @see OCLHelper#getSyntaxHelp(ConstraintKind, String)
 * 
 * @author Christian W. Damus (cdamus)
 */
public enum ChoiceKind {
	/** Indicates a property completion choice. */
	PROPERTY,

	/** Indicates an operation completion choice. */
	OPERATION,

	/** Indicates a signal completion choice. */
	SIGNAL,
	
	/** Indicates an enumeration literal completion choice. */
	ENUMERATION_LITERAL,

	/** Indicates a state name completion choice. */
	STATE,
	
	/** Indicates a type name completion choice. */
	TYPE,
	
	/** Indicates an association-class navigation choice. */
	ASSOCIATION_CLASS,
	
	/** Indicates a package choice (which helps to complete type choices). */
	PACKAGE,

	/** Indicates a variable name completion choice. */
	VARIABLE;
}