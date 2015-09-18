/*******************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.ParameterType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.NameUtil;

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
public class TemplateSpecialisation
{
	/**
	 * Return true if a referencedType needs specialisation to resolve a template parameter.
	 */
	public static boolean needsSpecialisation(@Nullable Type referencedType)
	{
		if (referencedType == null) {
			return true;
		}
		boolean templateParameter = referencedType.isTemplateParameter();
		if (templateParameter) {
			return true;
		}
		if (referencedType instanceof CollectionType) {
			Type elementType = ((CollectionType)referencedType).getElementType();
			return needsSpecialisation(elementType);
		}
		if (referencedType instanceof TupleType) {
			TupleType tupleType = (TupleType)referencedType;
			for (Property tuplePart : tupleType.getOwnedProperties()) {
				Type tuplePartType = tuplePart.getType();
				if (needsSpecialisation(tuplePartType)) {
					return true;
				}
			}
			return false;
		}
		if (referencedType instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)referencedType;
			ParameterType resultType = lambdaType.getOwnedResultType();
			if (needsSpecialisation(resultType.getType())) {
				return true;
			}
			for (ParameterType parameterType : lambdaType.getOwnedParameterTypes()) {
				if (needsSpecialisation(parameterType.getType())) {
					return true;
				}
			}
			return false;
		}
		if (referencedType instanceof org.eclipse.ocl.pivot.Class) {
			TemplateSignature templateSignature = ((org.eclipse.ocl.pivot.Class)referencedType).getOwnedSignature();
			if (templateSignature != null) {
				return true;
			}
		}
		return false;
	}

	protected final @NonNull CompleteEnvironment environment;
//	protected final @NonNull DomainStandardLibrary standardLibrary;
	protected /*@LazyNonNull*/ Map<TemplateParameter, Type> bindings = null;

	public TemplateSpecialisation(@NonNull CompleteEnvironment environment) {
		this.environment = environment;
//		this.standardLibrary = environment.getStandardLibrary();
	}
	
	/**
	 * Return the specialisation of referencedType if distinct from referencedType.
	 * Returns null if specialisation not available or not distinct from referencedType.
	 */
	private @Nullable Type getResolution(@Nullable Type referencedType) {
		if (referencedType != null) {
			return bindings != null ? bindings.get(referencedType) : null;
		}
		return null;
	}

	public @NonNull org.eclipse.ocl.pivot.Class getSpecialisation(@NonNull Type referredType) {
		Type specialisation = getResolution(referredType);
		return (org.eclipse.ocl.pivot.Class) (specialisation != null ? specialisation : referredType);	// FIXME cast
	}

	public void installEquivalence(@Nullable ParameterType resolvedParameterType, @Nullable ParameterType referencedParameterType) {
		installEquivalence(resolvedParameterType != null ? resolvedParameterType.getType() : null,
							referencedParameterType != null ? referencedParameterType.getType() : null);
	}
	
	public void installEquivalence(@Nullable Type resolvedType, @Nullable Type referencedType) {
		if (resolvedType == null) {
			return;
		}
		if (referencedType == null) {
			return;
		}
		TemplateParameter templateParameter = referencedType.asTemplateParameter();
		if (templateParameter != null) {
			if (bindings == null) {
				bindings = new HashMap<TemplateParameter, Type>();
			}
			if (bindings.put(templateParameter, resolvedType) != null) {
				bindings.put(templateParameter, null);
			}
			return;
		}
		if (referencedType instanceof CollectionType) {
			if (resolvedType instanceof CollectionType) {
				Type resolvedElementType = ((CollectionType)resolvedType).getElementType();
				Type referencedElementType = ((CollectionType)referencedType).getElementType();
				installEquivalence(resolvedElementType, referencedElementType);
			}
			return;
		}
		if (referencedType instanceof TupleType) {
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
			}
			return;
		}
		if (referencedType instanceof LambdaType) {
			if (resolvedType instanceof LambdaType) {
				LambdaType referencedLambdaType = (LambdaType)referencedType;
				LambdaType resolvedLambdaType = (LambdaType)resolvedType;
				installEquivalence(resolvedLambdaType.getOwnedResultType(), referencedLambdaType.getOwnedResultType());
				List<ParameterType> resolvedParameterTypes = resolvedLambdaType.getOwnedParameterTypes();
				List<ParameterType> referencedParameterTypes = referencedLambdaType.getOwnedParameterTypes();
				for (int i = 0; i < Math.min(resolvedParameterTypes.size(), referencedParameterTypes.size()); i++) {
					ParameterType resolvedParameterType = resolvedParameterTypes.get(i);
					ParameterType referencedParameterType = referencedParameterTypes.get(i);
					installEquivalence(resolvedParameterType, referencedParameterType);
				}
			}
			return;
		}
		return;
	}
}