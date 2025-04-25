/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterization;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.ClassCS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.DetailCS;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.StructuralFeatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.AbstractExtendingBaseCSVisitor;
import org.eclipse.ocl.xtext.basecs.util.VisitableCS;

public class BaseCSLeft2RightVisitor extends AbstractExtendingBaseCSVisitor<Element, CS2ASConversion>
{
	/**
	 * The CS2ASContext provides 'static' context applicable to the current expression conversion.
	 */
	public static class CS2ASContext
	{
		protected final @NonNull PivotHelper helper;

		protected final @Nullable Namespace namespace;			// Maybe null when parsing constant expressions wrt null.
		private /*@LazyNonNull*/ TemplateParameterization templateParameterization = null;
		private @Nullable NamedElement self = null;

		protected CS2ASContext(@NonNull PivotHelper helper, @Nullable Namespace namespace) {
			this.helper = helper;
			this.namespace = namespace;
		}

		public @NonNull EnvironmentFactory getEnvironmentFactory() {
			return helper.getEnvironmentFactory();
		}

		public @NonNull PivotHelper getHelper() {
			return helper;
		}

		public @Nullable TemplateParameter getTemplateParameter(@NonNull NormalizedTemplateParameter normalizedTemplateParameter) {
			Namespace namespace2 = namespace;
			assert namespace2 != null;
			TemplateParameterization templateParameterization2 = templateParameterization;
			if (templateParameterization2 == null) {
				templateParameterization = templateParameterization2 = TemplateParameterization.getTemplateParameterization(namespace2);
			}
			return templateParameterization2.get(normalizedTemplateParameter.getIndex());
		}

		public void popSelf(@Nullable NamedElement savedSelf) {
			this.self = savedSelf;
		}

		public @Nullable NamedElement pushSelf(@NonNull NamedElement self) {
			NamedElement savedSelf = this.self;
			this.self = self;
			return savedSelf;
		}

		public @NonNull Type resolveTemplateParameter(@NonNull NormalizedTemplateParameter normalizedTemplateParameter) {
			TemplateParameter templateParameter = getTemplateParameter(normalizedTemplateParameter);
			assert templateParameter != null;
			Type type = PivotUtil.basicGetLowerBound(templateParameter);
			if (type == null) {
				type = helper.getStandardLibrary().getOclAnyType();
			}
			return type;
		}
	}

	/**
	 * Construction helper.
	 */
	protected final @NonNull PivotHelper helper;

	private @Nullable CS2ASContext cs2asContext = null;

	public BaseCSLeft2RightVisitor(@NonNull CS2ASConversion context) {
		super(context);
		this.helper = context.getHelper();
	}

	public @NonNull CS2ASContext getCS2ASContext() {
		return Objects.requireNonNull(cs2asContext);
	}

	public <T extends Element> @Nullable T newVisit(@NonNull Class<T> pivotClass, @NonNull ElementCS csElement) {
		assert this.cs2asContext == null;
		Namespace asNamespace = ElementUtil.basicGetContainingNamespace(csElement);
		this.cs2asContext = new CS2ASContext(helper, asNamespace);
		try {
			return revisit(pivotClass, csElement);
		}
		finally {
			this.cs2asContext = null;
		}
	}

	protected<T extends Element> @Nullable T revisit(@NonNull Class<T> pivotClass, @NonNull ElementCS csElement) {
		throw new IllegalStateException();				// FIXME Should be abstract
	}

	@Override
	public Element visitAnnotationCS(@NonNull AnnotationCS object) {
		return null;
	}

	@Override
	public Element visitClassCS(@NonNull ClassCS object) {
		return null;
	}

	@Override
	public Element visitConstraintCS(@NonNull ConstraintCS object) {
		return null;
	}

	@Override
	public Element visitDetailCS(@NonNull DetailCS object) {
		return null;
	}

	@Override
	public Element visitOperationCS(@NonNull OperationCS object) {
		return null;
	}

	@Override
	public Element visitParameterCS(@NonNull ParameterCS object) {
		return null;
	}

	@Override
	public Element visitSpecificationCS(@NonNull SpecificationCS object) {
		return null;
	}

	@Override
	public Element visitStructuralFeatureCS(@NonNull StructuralFeatureCS object) {
		return null;
	}

	@Override
	public Element visitTemplateBindingCS(@NonNull TemplateBindingCS object) {
		return null;
	}

	@Override
	public Element visitTemplateParameterCS(@NonNull TemplateParameterCS object) {
		return null;
	}

	@Override
	public Element visitTemplateParameterSubstitutionCS(@NonNull TemplateParameterSubstitutionCS object) {
		return null;
	}

	@Override
	public Element visitTemplateSignatureCS(@NonNull TemplateSignatureCS object) {
		return null;
	}

	@Override
	public Element visitTuplePartCS(@NonNull TuplePartCS object) {
		return null;
	}

	@Override
	public Element visitTupleTypeCS(@NonNull TupleTypeCS object) {
		return null;
	}

	@Override
	public Element visitTypeRefCS(@NonNull TypeRefCS object) {
		return null;
	}

	@Override
	public Element visiting(@NonNull VisitableCS visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for CS2AS Left2Right pass");
	}
}