/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

public abstract class AbstractDependency<T> implements Dependency
{
	protected final T element;
	
	public AbstractDependency(T element) {
		this.element = element;
//		assert element != null; -- happens for references to unresolved proxy types
	}

	@Override
	public abstract boolean canExecute();

	public T getElement() {
		return element;
	}		
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + '(' + element + ')';
	}

	public static AbstractDependency<?>[] combine(AbstractDependency<?> dependency1, AbstractDependency<?> dependency2) {
		if (dependency1 != null) {
			if (dependency2 != null) {
				return new AbstractDependency<?>[] {dependency1, dependency2};
			}
			else {
				return new AbstractDependency<?>[] {dependency1};
			}
		}
		else {
			if (dependency2 != null) {
				return new AbstractDependency<?>[] {dependency2};
			}
			else {
				return new AbstractDependency<?>[] {};
			}
		}
	}		
}