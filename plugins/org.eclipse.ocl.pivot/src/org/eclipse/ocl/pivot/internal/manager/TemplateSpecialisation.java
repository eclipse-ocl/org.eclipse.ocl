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
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.NameUtil;
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
	 * Return true if a referencedType transitively references a TemplateParamter.
	 */
	public static boolean needsSpecialisation(@Nullable Type referencedType)	// XXX simplify to template argument scan
	{
		if (referencedType == null) {
			return false;
		}
		else if (referencedType instanceof TemplateParameter) {
			return true;
		}
		else if (referencedType instanceof CollectionType) {
			Type elementType = ((CollectionType)referencedType).getElementType();
			return needsSpecialisation(elementType);
		}
		else if (referencedType instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)referencedType;
			Type contextType = lambdaType.getContextType();
			if (needsSpecialisation(contextType)) {
				return true;
			}
			Type resultType = lambdaType.getResultType();
			if (needsSpecialisation(resultType)) {
				return true;
			}
			for (Type parameterType : lambdaType.getParameterTypes()) {
				if (needsSpecialisation(parameterType)) {
					return true;
				}
			}
			return false;
		}
		else if (referencedType instanceof MapType) {
			final MapType mapType = (MapType)referencedType;
			Type keyType = mapType.getKeyType();
			Type valueType = mapType.getValueType();
			return needsSpecialisation(keyType) ||  needsSpecialisation(valueType);
		}
		else if (referencedType instanceof TupleType) {
			TupleType tupleType = (TupleType)referencedType;
			for (Property tuplePart : tupleType.getOwnedProperties()) {
				Type tuplePartType = tuplePart.getType();
				if (needsSpecialisation(tuplePartType)) {
					return true;
				}
			}
			return false;
		}
		else if (referencedType instanceof org.eclipse.ocl.pivot.Class) {
			TemplateSignature templateSignature = ((org.eclipse.ocl.pivot.Class)referencedType).getOwnedSignature();
			if (templateSignature != null) {
				return true;
			}
		}
		return false;
	}

	protected final @NonNull StandardLibrary standardLibrary;

	public TemplateSpecialisation(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	public void installEquivalence(@Nullable Type resolvedType, @Nullable Type referencedType) {
		if (resolvedType == null) {
			return;
		}
		if (referencedType == null) {
			return;
		}
		if (referencedType instanceof CollectionType) {
			if (resolvedType instanceof CollectionType) {
				Type resolvedElementType = ((CollectionType)resolvedType).getElementType();
				Type referencedElementType = ((CollectionType)referencedType).getElementType();
				installEquivalence(resolvedElementType, referencedElementType);
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
				for (int i = 0; i < Math.min(resolvedParameterTypes.size(), referencedParameterTypes.size()); i++) {
					Type resolvedParameterType = resolvedParameterTypes.get(i);
					Type referencedParameterType = referencedParameterTypes.get(i);
					installEquivalence(resolvedParameterType, referencedParameterType);
				}
			}
		}
		else if (referencedType instanceof MapType) {
			if (resolvedType instanceof MapType) {
				Type resolvedKeyType = ((MapType)resolvedType).getKeyType();
				Type resolvedValueType = ((MapType)resolvedType).getValueType();
				Type referencedKeyType = ((MapType)referencedType).getKeyType();
				Type referencedValueType = ((MapType)referencedType).getValueType();
				installEquivalence(resolvedKeyType, referencedKeyType);
				installEquivalence(resolvedValueType, referencedValueType);
			}
		}
		else if (referencedType instanceof TemplateParameter) {
			TemplateParameter templateParameter = (TemplateParameter)referencedType;
			if (formal2actual.put(templateParameter, resolvedType) != null) {
			//	formal2actual.put(templateParameter, null);
				throw new UnsupportedOperationException();		// XXX
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
			}
		}
		else if (referencedType instanceof org.eclipse.ocl.pivot.Class) {
			if (resolvedType instanceof org.eclipse.ocl.pivot.Class) {
				throw new UnsupportedOperationException();		// scan parameters/arguments
			}
		}
	}

	@Override
	public @Nullable Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType) {
		throw new UnsupportedOperationException();
	}
}