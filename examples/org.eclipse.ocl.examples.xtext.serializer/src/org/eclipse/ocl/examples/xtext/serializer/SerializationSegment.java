/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public interface SerializationSegment
{
	public static final @NonNull SerializationSegment HALF_NEW_LINE = new StringSerializationSegment(SerializationBuilder.HALF_NEW_LINE);
//	public static final @NonNull StringSerializationSegment NEW_LINE = new StringSerializationSegment(SerializationBuilder.NEW_LINE);
	public static final @NonNull SerializationSegment NO_SPACE = new StringSerializationSegment(SerializationBuilder.NO_SPACE);
	public static final @NonNull SerializationSegment POP = new StringSerializationSegment(SerializationBuilder.POP);
	public static final @NonNull SerializationSegment PUSH = new StringSerializationSegment(SerializationBuilder.PUSH);
	public static final @NonNull SerializationSegment SOFT_NEW_LINE = new StringSerializationSegment(SerializationBuilder.SOFT_NEW_LINE);
	public static final @NonNull SerializationSegment SOFT_SPACE = new StringSerializationSegment(SerializationBuilder.SOFT_SPACE);
	public static final @NonNull SerializationSegment VALUE = new ValueSerializationSegment();

	void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);

	public static class CustomSerializationSegment implements SerializationSegment
	{
		private @Nullable String supportClassName;
		private @Nullable Class<?> supportClass;
		private @Nullable CustomSegmentSupport supportInstance = null;

		public CustomSerializationSegment(@NonNull String supportClassName) {
			this.supportClassName = supportClassName;
			this.supportClass = null;
			this.supportInstance = null;
		}

		public CustomSerializationSegment(@NonNull Class<?> supportClass) {
			this.supportClassName = null;
			this.supportClass = supportClass;
			this.supportInstance = null;
		}

		public @NonNull String getSupportClassName() {
			if (supportClassName != null) {
				return supportClassName;
			}
			assert supportClass != null;
			@SuppressWarnings("null")
			@NonNull String castName = (@NonNull String)supportClass.getName();
			return castName;
		}

		@Override
		public void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			CustomSegmentSupport supportInstance2 = supportInstance;
			if (supportInstance2 == null) {
				if ((supportClass == null) && (supportClassName != null)) {
					EObject eObject = serializer.getElement();
					ClassLoader classLoader = eObject.getClass().getClassLoader();
					try {
						supportClass = classLoader.loadClass(supportClassName);
					} catch (ClassNotFoundException e) {
					//	return null;
					}
				}
				if (supportClass != null) {
					try {
						supportInstance = supportInstance2 = (CustomSegmentSupport) supportClass.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
					//	return null;
					}
				}
			}
			if (supportInstance2 == null) {
				serializationBuilder.appendError("\n\n«missing " + getSupportClassName() + "»\n\n");
			}
			else {
				supportInstance2.serialize(serializationStep, serializer, serializationBuilder);
			}
		}

		@Override
		public String toString() {
			return "supported by " + getSupportClassName();
		}
	}

	public static class StringSerializationSegment implements SerializationSegment
	{
		protected final @NonNull String string;

		public StringSerializationSegment(@NonNull String string) {
			this.string = string;
		}

		public @NonNull String getString() {
			return string;
		}

		@Override
		public void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			serializationBuilder.append(string);
		}

		@Override
		public @NonNull String toString() {
			return string;
		}
	}

	public static class ValueSerializationSegment implements SerializationSegment
	{
		@Override
		public void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
			serializationStep.serialize(serializer, serializationBuilder);
		}

		@Override
		public @NonNull String toString() {
			return "«value»";
		}
	}
}
