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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport;

public interface SerializationSegment
{

	public static final @NonNull SerializationSegment HALF_NEW_LINE = new StringSerializationSegment(SerializationBuilder.HALF_NEW_LINE);
	public static final @NonNull SerializationSegment NO_SPACE = new StringSerializationSegment(SerializationBuilder.NO_SPACE);
	public static final @NonNull SerializationSegment POP = new StringSerializationSegment(SerializationBuilder.POP);
	public static final @NonNull SerializationSegment PUSH = new StringSerializationSegment(SerializationBuilder.PUSH);
	public static final @NonNull SerializationSegment SOFT_NEW_LINE = new StringSerializationSegment(SerializationBuilder.SOFT_NEW_LINE);
	public static final @NonNull SerializationSegment SOFT_SPACE = new StringSerializationSegment(SerializationBuilder.SOFT_SPACE);
	public static final @NonNull SerializationSegment VALUE = new ValueSerializationSegment();
//	public static final @NonNull StringSerializationSegment NEW_LINE = new StringSerializationSegment(SerializationBuilder.NEW_LINE);


//	public static @NonNull SerializationSegment createCustomSegment(Object object, Class<@NonNull BaseCommentSegmentSupport> class1) {
//		return new CustomSerializationSegment(((CustomSegment) object).getSupportClassName());				// XXX
//	}

	void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder);

	public static class CustomSerializationSegment implements SerializationSegment
	{
	//	protected final @NonNull CustomSegment customSegment;
		private @Nullable CustomSegmentSupport support = null;
		private @NonNull String supportClassName;

		public CustomSerializationSegment(@NonNull String supportClassName) {
			this.supportClassName = supportClassName;
		}

		public CustomSerializationSegment(@NonNull Class<?> supportClass) {
			this.supportClassName = supportClass.getName();
		}

		public @NonNull String getSupportClassName() {
			return supportClassName;
		}

		@Override
		public void serialize(@NonNull SerializationStep serializationStep, @NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		//	assert segment == customSegment;
		//	CustomSegment customSegment = (CustomSegment)segment;;
			if (support == null) {
				Class<?> supportClass = null; //customSegment.getSupportClass();
				if ((supportClass == null) && (supportClassName/*customSegment.getSupportClassName()*/ != null)) {
					EObject eObject = serializer.getElement();
					ClassLoader classLoader = eObject.getClass().getClassLoader();
					try {
						supportClass = classLoader.loadClass(supportClassName/*customSegment.getSupportClassName()*/);
					} catch (ClassNotFoundException e) {
					//	return null;
					}
				}
				if (supportClass != null) {
					try {
						support = (CustomSegmentSupport) supportClass.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
					//	return null;
					}
				}
			}
			if (support == null) {
				Class<?> supportClass = null; //customSegment.getSupportClass();
				String className = supportClassName; //supportClass != null ? supportClass.getName() : customSegment.getSupportClassName();
				serializationBuilder.appendError("\n\n«missing " + className + "»\n\n");
			}
			else {
				assert support != null;
				support.serialize(serializationStep, serializer, serializationBuilder);
			}
		}

		@Override
		public String toString() {
			return "supported by " + supportClassName;
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
