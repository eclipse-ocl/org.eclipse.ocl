/*******************************************************************************
 * Copyright (c) 2002, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.internal.helper;

import org.eclipse.ocl.helper.Choice;
import org.eclipse.ocl.helper.ChoiceKind;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.util.ObjectUtil;


/**
 * Default implementation of the {@link Choice} interface.
 * 
 * @see OCLHelper#getSyntaxHelp(String)
 * 
 * @author Yasser Lulu 
 */
class ChoiceImpl implements Choice {

	private String name;

	private String description;

	private ChoiceKind kind;
	
	private Object element;

	/**
	 * Initializes me with all of my fields.
	 * 
	 * @param name my name, suitable for display in a UI list
	 * @param description my description, suitable for display in a UI tool tip
	 *     or pop-up help window
	 * @param kind the kind of choice that I am
	 * @param element the element that I choose
	 */
	ChoiceImpl(String name, String description, ChoiceKind kind, Object element) {
		this.name = name;
		this.description = description;
		this.kind = kind;
		this.element = element;
	}

    // implements the inherited specification
	@Override
	public String getName() {
		return name;
	}

    // implements the inherited specification
	@Override
	public String getDescription() {
		return description;
	}

    // implements the inherited specification
	@Override
	public ChoiceKind getKind() {
		return kind;
	}
	
    // implements the inherited specification
	@Override
	public Object getElement() {
		return element;
	}

	@Override
    public int hashCode() {
		return (getName() == null) ? 0 : getName().hashCode();
	}
	
	@Override
    public boolean equals(Object obj) {
		boolean result = obj instanceof ChoiceImpl;
		
		if (result) {
			ChoiceImpl other = (ChoiceImpl) obj;
			
			result = getKind() == other.getKind()
				&& ObjectUtil.equal(getName(), other.getName());
		}
		
		return result;
	}
	
	@Override
    public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Choice["); //$NON-NLS-1$
		result.append(getKind().name());
		result.append(", "); //$NON-NLS-1$
		result.append(getName());
		result.append(", "); //$NON-NLS-1$
		result.append(getDescription());
		result.append(']');
		
		return result.toString();
	}
}