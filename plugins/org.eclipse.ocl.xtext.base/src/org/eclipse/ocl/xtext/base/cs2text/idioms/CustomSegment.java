/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

public class CustomSegment implements Segment
{
	protected Segment delegate = null;

	public CustomSegment(@NonNull Class<? extends Segment> delegatedClass, Object ... objects) {
		try {
			if (objects.length == 0) {
				this.delegate = delegatedClass.newInstance();
			}
			else {
				Constructor<Segment> matchingConstructor = null;
				for (Constructor<?> constructor : delegatedClass.getConstructors()) {
					Class<?>[] parameterTypes = constructor.getParameterTypes();
					int iMax = parameterTypes.length;
					if (iMax == objects.length) {
						boolean gotIt = true;
						for (int i = 0; i < iMax; i++) {
							if (!parameterTypes[i].isAssignableFrom(objects[i].getClass())) {
								gotIt = false;
								break;
							}
						}
						if (gotIt) {
							@SuppressWarnings("unchecked")
							Constructor<Segment> castConstructor = (Constructor<Segment>) constructor;
							matchingConstructor = castConstructor;
						}
					}
				}
				if (matchingConstructor != null) {
					this.delegate = matchingConstructor.newInstance(objects);
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void serialize(@NonNull SerializationNode serializationNode, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		delegate.serialize(serializationNode, serializer, serializationBuilder);
	}

	@Override
	public String toString() {
		return "delegate-to " + delegate;
	}
}
