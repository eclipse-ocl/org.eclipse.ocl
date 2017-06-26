/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InvalidableType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NullableType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.resource.ASSaver;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;

/**
 * ResolveVisitor converts references to shared specializations
 * to references to local copies.
 */
public class ASSaverResolveVisitor extends AbstractExtendingVisitor<Object, ASSaver>
{
	public ASSaverResolveVisitor(@NonNull ASSaver saver) {
		super(saver);
	}

	@Override
	public Object visitClass(org.eclipse.ocl.pivot.@NonNull Class object) {
		List<org.eclipse.ocl.pivot.@NonNull Class> superClasses = PivotUtilInternal.getSuperClassesList(object);
		for (int i = 0; i < superClasses.size(); i++) {
			org.eclipse.ocl.pivot.Class referredClass = superClasses.get(i);
			org.eclipse.ocl.pivot.Class resolvedClass = context.resolveType(referredClass);
			superClasses.set(i, resolvedClass);
		}
		return super.visitClass(object);
	}

	@Override
	public Object visitCollectionType(@NonNull CollectionType object) {
		Type referredType = ClassUtil.nonNullModel(object.getElementType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setElementType(resolvedType);
			}
		}
		return super.visitCollectionType(object);
	}

	@Override
	public Object visitInvalidableType(@NonNull InvalidableType object) {
		Type referredType = ClassUtil.nonNullModel(object.getNonNullType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setNonNullType(resolvedType);
			}
		}
		return super.visitInvalidableType(object);
	}

	@Override
	public Object visitLambdaType(@NonNull LambdaType object) {
		Type referredType = ClassUtil.nonNullModel(object.getContextType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setContextType(resolvedType);
			}
		}
		referredType = ClassUtil.nonNullModel(object.getResultType());
		referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setResultType(resolvedType);
			}
		}
		List<Type> parameterTypes = object.getParameterType();
		for (int i = 0; i < parameterTypes.size(); i++) {
			referredType = parameterTypes.get(i);
			if (referredType != null) {
				referredClass = referredType.isClass();
				if (referredClass != null) {
					Type resolvedType = context.resolveType(referredClass);
					if (resolvedType != referredType) {
						parameterTypes.set(i, resolvedType);
					}
				}
			}
		}
		return super.visitLambdaType(object);
	}

	@Override
	public Object visitLoopExp(@NonNull LoopExp object) {
		Iteration referredIteration = ClassUtil.nonNullModel(object.getReferredIteration());
		Iteration resolvedIteration = context.resolveOperation(referredIteration);
		object.setReferredIteration(resolvedIteration);
		return super.visitLoopExp(object);
	}

	@Override
	public Object visitMapType(@NonNull MapType object) {
		Type referredType = ClassUtil.nonNullModel(object.getKeyType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setKeyType(resolvedType);
			}
		}
		referredType = ClassUtil.nonNullModel(object.getValueType());
		referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setValueType(resolvedType);
			}
		}
		return super.visitMapType(object);
	}

	@Override
	public Object visitNullableType(@NonNull NullableType object) {
		Type referredType = ClassUtil.nonNullModel(object.getNonNullType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setNonNullType(resolvedType);
			}
		}
		return super.visitNullableType(object);
	}

	@Override
	public Object visitOperationCallExp(@NonNull OperationCallExp object) {	// FIXME Obsolete once referredOperation is not a specialization
		Operation referredOperation = PivotUtil.getReferredOperation(object);
		Operation resolvedOperation = context.resolveOperation(referredOperation);
		if (resolvedOperation != referredOperation) {
			object.setReferredOperation(resolvedOperation);
		}
		return super.visitOperationCallExp(object);
	}

	@Override
	public Object visitPropertyCallExp(@NonNull PropertyCallExp object) {
		Property referredProperty = PivotUtil.getReferredProperty(object);
		Property resolvedProperty = context.resolveProperty(referredProperty);
		if (resolvedProperty != referredProperty) {
			object.setReferredProperty(resolvedProperty);
		}
		return super.visitPropertyCallExp(object);
	}

	@Override
	public Object visitTemplateParameter(@NonNull TemplateParameter object) {
		List<org.eclipse.ocl.pivot.Class> constrainingTypes = object.getConstrainingClasses();
		for (int i = 0; i < constrainingTypes.size(); i++) {
			org.eclipse.ocl.pivot.Class referredType = constrainingTypes.get(i);
			if (referredType != null) {
				org.eclipse.ocl.pivot.Class resolvedType = context.resolveType(referredType);
				constrainingTypes.set(i, resolvedType);
			}
		}
		return null;
	}

	@Override
	public Object visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution object) {
		Type referredType = ClassUtil.nonNullModel(object.getActual());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setActual(resolvedType);
			}
		}
		return null;
	}

	@Override
	public Object visitType(@NonNull Type object) {
		NullableType referredNullableType = object.getNullableType();
		if (referredNullableType != null) {
			NullableType resolvedNullableType = context.resolveType(referredNullableType);
			if (resolvedNullableType != referredNullableType) {
				object.setNullableType(resolvedNullableType);
			}
		}
		InvalidableType referredInvalidableType = object.getInvalidableType();
		if (referredInvalidableType != null) {
			InvalidableType resolvedInvalidableType = context.resolveType(referredInvalidableType);
			if (resolvedInvalidableType != referredInvalidableType) {
				object.setInvalidableType(resolvedInvalidableType);
			}
		}
		return null;
	}

	@Override
	public Object visitTypeExp(@NonNull TypeExp object) {
		Type referredType = ClassUtil.nonNullEMF(object.getReferredType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setReferredType(resolvedType);
				object.setTypeValue(resolvedType);
			}
		}
		return super.visitTypeExp(object);
	}

	@Override
	public Object visitTypedElement(@NonNull TypedElement object) {
		Type referredType = ClassUtil.nonNullEMF(object.getRawType());
		org.eclipse.ocl.pivot.Class referredClass = referredType.isClass();
		if (referredClass != null) {
			Type resolvedType = context.resolveType(referredClass);
			if (resolvedType != referredType) {
				object.setType(resolvedType);
			}
		}
		return null;
	}

	@Override
	public Object visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for PivotSaver Resolve pass");
	}
}
