/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions.SimpleTemplateParameterSubstitutions;

/**
 * A TemplateSpecialisation supports resolution of template parameter within an element referenced from an OCL expression.
 * For instance a PropertyCallExp.referredProperty references the unspecialised Property and consequently the type and owningType
 * of the referredProperty may have unresolved template parameters. These may be resolved by exploiting the bindings of
 * the ProperyCallExp.source.
 * <p>
 * Invocation should first invoke needsSpecialisation() to discover whether the cost of constructing a TemplateSpecialisation
 * can be bypassed. If specialisation is needed a TemplateSpecialisation should be constructed for the prevailing OCL Standard
 * Library, and known type equivalences installed by invoking installEquivalence() for each. getSpecialisation may then be used
 * to resolve the type.
 */
public class TemplateSpecialisation extends SimpleTemplateParameterSubstitutions
{
	/**
	 * Return true if a referencedType transitively declares a TemplateParameter.
	 */
	public static boolean needsCompletion(@Nullable Type asType) {
		if (asType instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)asType;
			TemplateSignature asTemplateSignature = asClass.getOwnedSignature();
			if (asTemplateSignature != null) {
				return true;
			}
			for (TemplateBinding asTemplateBinding : PivotUtil.getOwnedBindings(asClass)) {
				for (TemplateParameterSubstitution asTemplateParameterSubstitution : PivotUtil.getOwnedSubstitutions(asTemplateBinding)) {
					if (needsCompletion(asTemplateParameterSubstitution.getActual())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Return true if a referencedType transitively references a TemplateParameter.
	 */
	public static boolean needsSpecialisation(@Nullable Type referencedType) {	// XXX simplify to template argument scan
		return needsCompletion(referencedType);
	//	return (referencedType != null) && !(referencedType instanceof TemplateParameter) && needsSpecialisation(referencedType, referencedType);
	}

	private static boolean needsSpecialisation(@NonNull Type zzouterType, @NonNull Type referencedType) {	// XXX simplify to template argument scan
		if (referencedType instanceof TemplateParameter) {
			return true; //referencedType != outerType;
		}
		else if (referencedType instanceof PrimitiveType) {							// Fast non-generic bypass
		}
		else if (referencedType instanceof LambdaType) {
		/*	LambdaType lambdaType = (LambdaType)referencedType;
			Type contextType = PivotUtil.getContextType(lambdaType);
			if (needsSpecialisation(outerType, contextType)) {
				return true;
			}
			Type resultType = PivotUtil.getResultType(lambdaType);
			if (needsSpecialisation(outerType, resultType)) {
				return true;
			}
			for (Type parameterType : PivotUtil.getParameterTypes(lambdaType)) {
				if (needsSpecialisation(outerType, parameterType)) {
					return true;
				}
			} */
		}
		else if (referencedType instanceof TupleType) {
		/*	TupleType tupleType = (TupleType)referencedType;
			for (Property tuplePart : PivotUtil.getOwnedProperties(tupleType)) {
				Type tuplePartType = PivotUtil.getType(tuplePart);
				if (needsSpecialisation(outerType, tuplePartType)) {
					return true;
				}
			} */
		}
		else if (referencedType instanceof org.eclipse.ocl.pivot.Class) {			// includes CollectionTYpe/MapType
			TemplateSignature templateSignature = ((org.eclipse.ocl.pivot.Class)referencedType).getOwnedSignature();
			if (templateSignature != null) {
			//	for (TemplateParameter parameter : PivotUtil.getOwnedParameters(templateSignature)) {
			//		if (needsSpecialisation(outerType, parameter)) {
						return true;
			//		}
			//	}
			}
		}
		return false;
	}

	protected final @NonNull StandardLibrary standardLibrary;

	public TemplateSpecialisation(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	public void installEquivalence(@Nullable Type resolvedType, @Nullable Type referencedType) {
		if ((resolvedType == null) || (referencedType == null)) {
	//		return;
		}
		else if (referencedType instanceof TemplateParameter) {
			TemplateParameter templateParameter = (TemplateParameter)referencedType;
			Type old = formal2actual.put(templateParameter, resolvedType);
			if ((old == null) || (old == resolvedType)) {
				return;
			}
		}
		else if (referencedType instanceof LambdaType) {
			if (resolvedType instanceof LambdaType) {
				LambdaType referencedLambdaType = (LambdaType)referencedType;
				LambdaType resolvedLambdaType = (LambdaType)resolvedType;
				installEquivalence(resolvedLambdaType.getContextType(), referencedLambdaType.getContextType());
				installEquivalence(resolvedLambdaType.getResultType(), referencedLambdaType.getResultType());
				List<? extends Type> resolvedParameterTypes = resolvedLambdaType.getParameterTypes();
				List<? extends Type> referencedParameterTypes = referencedLambdaType.getParameterTypes();
				int iSize = referencedParameterTypes.size();
				assert iSize == resolvedParameterTypes.size();
				for (int i = 0; i < iSize; i++) {
					Type resolvedParameterType = resolvedParameterTypes.get(i);
					Type referencedParameterType = referencedParameterTypes.get(i);
					installEquivalence(resolvedParameterType, referencedParameterType);
				}
				return;
			}
		}
		else if (referencedType instanceof TupleType) {
			if (resolvedType instanceof TupleType) {
				TupleType referencedTupleType = (TupleType)referencedType;
				TupleType resolvedTupleType = (TupleType)resolvedType;
				Iterable<? extends Property> referencedTupleParts = referencedTupleType.getOwnedProperties();
				for (Property resolvedTuplePart : resolvedTupleType.getOwnedProperties()) {
					Property referencedTuplePart = NameUtil.getNameable(referencedTupleParts, resolvedTuplePart.getName());
					if (referencedTuplePart != null) {
						Type resolvedTuplePartType = resolvedTuplePart.getType();
						Type referencedTuplePartType = referencedTuplePart.getType();
						installEquivalence(resolvedTuplePartType, referencedTuplePartType);
					}
				}
				return;
			}
		}
		else if (referencedType instanceof org.eclipse.ocl.pivot.Class) {		// include CollectionType and MapType
			if (resolvedType instanceof org.eclipse.ocl.pivot.Class) {
				TemplateSignature referencedTemplateSignature = ((org.eclipse.ocl.pivot.Class)referencedType).getOwnedSignature();
				TemplateSignature resolvedTemplateSignature = ((org.eclipse.ocl.pivot.Class)resolvedType).getOwnedSignature();
				if ((referencedTemplateSignature != null) && (resolvedTemplateSignature != null)) {
					List<@NonNull TemplateParameter> referencedParameters = PivotUtilInternal.getOwnedParametersList(referencedTemplateSignature);
					List<@NonNull TemplateParameter> resolvedParameters = PivotUtilInternal.getOwnedParametersList(resolvedTemplateSignature);
					int iSize = referencedParameters.size();
					assert iSize == resolvedParameters.size();
					for (int i = 0; i < iSize; i++) {
						TemplateParameter referencedParameter = referencedParameters.get(i);
						TemplateParameter resolvedParameter = resolvedParameters.get(i);
						installEquivalence(referencedParameter, resolvedParameter);
					}
				}
				return;
			}
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType) {
		throw new UnsupportedOperationException();
	}
}