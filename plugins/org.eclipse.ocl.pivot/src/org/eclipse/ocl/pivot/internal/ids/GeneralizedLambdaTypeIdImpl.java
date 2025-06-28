/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class GeneralizedLambdaTypeIdImpl extends AbstractGeneralizedIdImpl<@NonNull LambdaTypeId> implements LambdaTypeId
{
	private static class LambdaParameterId
	{
		private @NonNull String name;
		private @NonNull TypeId typeId;
		private boolean isRequired;

		public LambdaParameterId(@NonNull TypedElement typedElement) {
			this.name = PivotUtil.getName(typedElement);
			this.typeId = typedElement.getTypeId();
			this.isRequired = typedElement.isIsRequired();
		}

		public @NonNull String getName() {
			return name;
		}

		public @NonNull TypeId getTypeId() {
			return typeId;
		}

		public boolean isIsRequired() {
			return isRequired;
		}
	}

	private static class LambdaTypeIdValue extends AbstractKeyAndValue<@NonNull LambdaTypeId>
	{
		private final @NonNull IdManager idManager;
		private @NonNull String name;
		private @NonNull TypedElement context;
		private @Nullable List<@NonNull ? extends TypedElement> parameters;
		private @NonNull TypedElement result;

		private LambdaTypeIdValue(@NonNull IdManager idManager, @NonNull String name,
				@NonNull TypedElement context, @Nullable List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
			super(computeHashCode(name, context, parameters, result));
			this.idManager = idManager;
			this.name = name;
			this.context = context;
			this.parameters = parameters;
			this.result = result;
		}

		@Override
		public @NonNull LambdaTypeId createSingleton() {
			return new GeneralizedLambdaTypeIdImpl(idManager, name, context, parameters, result);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (!(that instanceof GeneralizedLambdaTypeIdImpl)) {
				return false;
			}
			GeneralizedLambdaTypeIdImpl singleton = (GeneralizedLambdaTypeIdImpl)that;
			if (!name.equals(singleton.getName())) {
				return false;
			}
			if (!parameterEquals(context, singleton.context)) {
				return false;
			}
			List<@NonNull ? extends TypedElement> parameters2 = parameters;
			if (parameters2 != null) {
				List<@NonNull LambdaParameterId> singletonParameters = singleton.parameters;
				if (singletonParameters == null) {
					return false;
				}
				int iMax = parameters2.size();
				if (iMax != singletonParameters.size()) {
					return false;
				}
				for (int i = 0; i < iMax; i++) {
					TypedElement parameter = parameters2.get(i);
					LambdaParameterId singletonParameter = singletonParameters.get(i);
					if (!parameterEquals(parameter, singletonParameter)) {
						return false;
					}
				}
			}
			if (!parameterEquals(result, singleton.result)) {
				return false;
			}
			return true;
		}

		private boolean parameterEquals(@NonNull TypedElement parameter1, @NonNull LambdaParameterId parameter2) {
			if (!parameter1.getName().equals(parameter2.getName())) {
				return false;
			}
			if (parameter1.getTypeId() != parameter2.getTypeId()) {
				return false;
			}
			if (parameter1.isIsRequired() != parameter2.isIsRequired()) {
				return false;
			}
			return true;
		}
	}

	/**
	 * @since 1.18
	 */
	public static class LambdaTypeIdSingletonScope extends AbstractSingletonScope<@NonNull LambdaTypeId, @NonNull LambdaTypeIdValue>
	{
		/**
		 * @since 7.0
		 */
		public @NonNull LambdaTypeId getSingleton(@NonNull IdManager idManager, @NonNull String name,
				@NonNull TypedElement context, @Nullable List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
			return getSingletonFor(new LambdaTypeIdValue(idManager, name, context, parameters, result));
		}
	}

	private static int computeHashCode(@NonNull String name, @NonNull TypedElement context, @Nullable List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		int hash = IdHash.createGlobalHash(LambdaTypeId.class, name);
		hash += 3 * parameterHashCode(context);
		hash += 5 * parameterHashCode(result);
		if (parameters != null) {
			int n = 7;
			for (TypedElement parameter : parameters) {
				hash += n * parameterHashCode(parameter);
				n += 2;
			}
		}
		return hash;
	}

	private static int parameterHashCode(@NonNull TypedElement parameter) {
		int hash = parameter.getName().hashCode();
		hash += 3 * parameter.getTypeId().hashCode();
		if (parameter.isIsRequired()) {
			hash += 97;
		}
		return hash;
	}

	private @NonNull LambdaParameterId context;
	private @Nullable List<@NonNull LambdaParameterId> parameters;
	private @NonNull LambdaParameterId result;

	private GeneralizedLambdaTypeIdImpl(@NonNull IdManager idManager, @NonNull String name,
			@NonNull TypedElement context, @Nullable List<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		super(computeHashCode(name, context, parameters, result), 0, name);
		this.context = new LambdaParameterId(context);
		List<@NonNull LambdaParameterId> parameters2 = null;
		if (parameters != null) {
			parameters2 = new ArrayList<>();
			for (TypedElement parameter : parameters) {
				parameters2.add(new LambdaParameterId(parameter));
			}
		}
		this.parameters = parameters2;
		this.result = new LambdaParameterId(result);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitLambdaTypeId(this);
	}

	@Override
	protected @NonNull LambdaTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
	//	return new SpecializedLambdaTypeIdImpl(this, templateBindings);
		throw new UnsupportedOperationException();			// XXX
	}

	@Override
	public @NonNull String getDisplayName() {
		StringBuilder s = new StringBuilder();
		//		if (templateParameters > 0) {
		//			s.append("<");
		//			s.append(templateParameters);
		//			s.append(">");
		//		}
		s.append(name);
		s.append(' ');
		appendParameter(s, context);
		s.append('(');
		if (parameters != null) {
			for (LambdaParameterId parameter : parameters) {
				appendParameter(s, parameter);
			}
		}
		s.append(") : ");
		appendParameter(s, result);
		return s.toString();
	}

	private void appendParameter(@NonNull StringBuilder s, @NonNull LambdaParameterId parameter) {
		s.append(parameter.getName());
		s.append(" : ");
		s.append(parameter.getTypeId().toString());
		s.append(parameter.isIsRequired() ? "[1]" : "[?]");
	}

	@Override
	public @NonNull LambdaTypeId getGeneralizedId() {
		return this;
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		if (context.getTypeId().isTemplated()) {
			return true;
		}
		if (parameters != null) {
			for (LambdaParameterId parameter : parameters) {
				if (parameter.getTypeId().isTemplated()) {
					return true;
				}
			}
		}
		if (result.getTypeId().isTemplated()) {
			return true;
		}
		return false;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull String getMetaclassName() {
		return TypeId.LAMBDA_TYPE_NAME;
	}

	@Override
	public @NonNull LambdaTypeId specialize(@NonNull BindingsId templateBindings) {
		return createSpecializedId(templateBindings);
	}
}