/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 */
public class ValidationBehavior extends AbstractDelegatedBehavior<EClassifier, EValidator.ValidationDelegate.Registry, ValidationDelegate.Factory>
{
	public static final @NonNull ValidationBehavior INSTANCE = new ValidationBehavior();
	public static final @NonNull String NAME = "validationDelegates"; //$NON-NLS-1$

	/**
	 * @since 7.0
	 */
	public Constraint getConstraint(@NonNull EnvironmentFactory environmentFactory, @NonNull EClassifier eClassifier, @NonNull String constraintName) throws OCLDelegateException {
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		Resource ecoreMetamodel = ClassUtil.requireNonNull(eClassifier.eResource());
		External2AS es2as = External2AS.getAdapter(ecoreMetamodel, environmentFactory);
		Type type = es2as.getCreated(Type.class, eClassifier);
		if (type != null) {
			List<@NonNull Constraint> knownInvariants = new ArrayList<>();
			for (CompleteClass superType : completeModel.getAllSuperCompleteClasses(type)) {
				for (org.eclipse.ocl.pivot.@NonNull Class partialSuperType : ClassUtil.nullFree(superType.getPartialClasses())) {
					org.eclipse.ocl.pivot.Package partialPackage = partialSuperType.getOwningPackage();
					if (!(partialPackage instanceof PackageImpl) || !((PackageImpl)partialPackage).isIgnoreInvariants()) {
						for (Constraint asConstraint : partialSuperType.getOwnedInvariants()) {
							if (constraintName.equals(asConstraint.getName())) {
								knownInvariants.add(asConstraint);
							}
						}
					}
				}
			}
			for (@NonNull Constraint asConstraint : knownInvariants) {
				LanguageExpression asSpecification = asConstraint.getOwnedSpecification();
				if (asSpecification instanceof ExpressionInOCL) {
					ExpressionInOCL asExpression = (ExpressionInOCL)asSpecification;
					if (asExpression.getOwnedBody() != null) {
						return asConstraint;
					}
					String body = asExpression.getBody();
					if (body != null) {
						if (!PivotConstants.DUMMY_COMPLETE_OCL_BODY.equals(body)) {
							return asConstraint;
						}
					}
				}
			}
		}
		throw new OCLDelegateException(new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, type, PivotConstantsInternal.CONSTRAINT_ROLE));
	}

	@Override
	public ValidationDelegate.@Nullable Factory getDefaultFactory() {
		return (ValidationDelegate.Factory) ValidationDelegate.Factory.Registry.INSTANCE.getValidationDelegate(getName());
	}

	@Override
	public EValidator.ValidationDelegate.@NonNull Registry getDefaultRegistry() {
		return ClassUtil.requireNonNull(ValidationDelegate.Factory.Registry.INSTANCE);
	}

	@Override
	public @NonNull EPackage getEPackage(@NonNull EClassifier eClassifier) {
		return ClassUtil.requireNonNull(eClassifier.getEPackage());
	}

	@Override
	public ValidationDelegate.@Nullable Factory getFactory(@NonNull DelegateDomain delegateDomain, @NonNull EClassifier eClassifier) {
		Class<EValidator.ValidationDelegate.@NonNull Registry> castClass = ValidationDelegate.Registry.class;
		EValidator.ValidationDelegate.@NonNull Registry registry = OCLDelegateDomain.getDelegateResourceSetRegistry(eClassifier, castClass, getDefaultRegistry());
		String delegateURI = delegateDomain.getURI();
		org.eclipse.emf.ecore.EValidator.ValidationDelegate validationDelegate = registry.getValidationDelegate(delegateURI);
		return (ValidationDelegate.Factory) validationDelegate;
	}

	@Override
	public @NonNull Class<ValidationDelegate.Factory> getFactoryClass() {
		return ValidationDelegate.Factory.class;
	}

	@Override
	public @NonNull String getName() {
		return NAME;
	}

	/**
	 * Return the operation body associated with operation, if necessary using
	 * <code>ocl</code> to create the relevant parsing environment for a textual
	 * definition.
	 * @throws OCLDelegateException
	 * @since 7.0
	 */
	public @NonNull ExpressionInOCL getQueryOrThrow(@NonNull EnvironmentFactory environmentFactory, @NonNull Constraint constraint) throws OCLDelegateException {
		LanguageExpression specification = constraint.getOwnedSpecification();
		if (specification == null) {
			throw new OCLDelegateException(new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, constraint, PivotConstantsInternal.INVARIANT_ROLE));
		}
		try {
			return environmentFactory.parseSpecification(specification);
		} catch (ParserException e) {
			throw new OCLDelegateException(e);
		}
	}

	@Override
	public @NonNull Class<ValidationDelegate.Factory.@NonNull Registry> getRegistryClass() {
		return ValidationDelegate.Factory.Registry.class;
	}
}