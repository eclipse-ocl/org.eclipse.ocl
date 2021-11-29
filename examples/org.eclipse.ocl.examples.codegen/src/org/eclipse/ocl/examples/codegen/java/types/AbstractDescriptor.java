/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * An AbstractDescriptor provides the most fundamental capabilities of any type description: the correspondence to a pivot ElementId.
 */
public abstract class AbstractDescriptor implements TypeDescriptor
{
	/**
	 * NamedFuture is a placeholder for classes that have yet to be created. It should never have any real instances or references.
	 *
	 */
	protected static class NamedFuture {
		private NamedFuture() {}
	}

	/**
	 * Convert an AS javaClass to its underlying Domain interface.
	 * Obsolete FIXME Avoid two-level AS interfaces
	 */
	protected static @NonNull Class<?> reClass(@NonNull Class<?> javaClass) {
		return javaClass;
	}

	protected final @NonNull ElementId elementId;

	public AbstractDescriptor(@NonNull ElementId elementId) {
		this.elementId = elementId;
	}

	@Override			// FIXME why isn't most of this in derived Descriptors?
	public @NonNull Boolean appendBox(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGBoxExp cgBoxExp, @NonNull CGValuedElement unboxedValue) {
		TypeId typeId = unboxedValue.getASTypeId();
		js.appendDeclaration(cgBoxExp);
		js.append(" = ");
		if (!unboxedValue.isNonNull() && !js.isPrimitive(unboxedValue)) {
			js.appendReferenceTo(unboxedValue);
			js.append(" == null ? null : ");
		}
		if (isAssignableTo(Iterable.class)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be AbstractCollectionDescriptor");
		}
		else if (isAssignableTo(BigInteger.class)
				|| isAssignableTo(Long.class)
				|| isAssignableTo(Integer.class)
				|| isAssignableTo(Short.class)
				|| isAssignableTo(Byte.class)
				|| isAssignableTo(Character.class)
				|| isAssignableTo(long.class)
				|| isAssignableTo(int.class)
				|| isAssignableTo(short.class)
				|| isAssignableTo(byte.class)
				|| isAssignableTo(char.class)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".integerValueOf(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if ((getJavaClass() == Object.class) && (typeId == TypeId.INTEGER)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be IntegerObjectDescriptor");
		}
		else if (isAssignableTo(BigDecimal.class)
				|| isAssignableTo(Double.class)
				|| isAssignableTo(Float.class)
				|| isAssignableTo(double.class)
				|| isAssignableTo(float.class)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".realValueOf(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if (isAssignableTo(Number.class)) {
			if (typeId == TypeId.REAL){
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be RealObjectDescriptor");
			}
			else {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be UnlimitedNaturalObjectDescriptor");
			}
		}
		else if (isAssignableTo(EEnumLiteral.class)) {
			js.appendClassReference(null, IdManager.class);
			js.append(".getEnumerationLiteralId(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if (isAssignableTo(Enumerator.class)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be EnumerationObjectDescriptor");
		}
		else {//if (ObjectValue.class.isAssignableFrom(javaClass)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".createObjectValue(");
			js.appendIdReference(typeId);
			js.append(", ");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		js.append(";\n");
		return true;
	}

	@Override
	public void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream) {
		js.append("(");
		append(js, isRequired);
		js.append(")");
		subStream.append();
	}

	@Override
	public void appendCastTerm(@NonNull JavaStream js, @NonNull CGValuedElement cgElement) {
		js.append("(");
		append(js, null);
		js.append(")");
		js.appendReferenceTo(cgElement);
	}

	@Deprecated		// This generic code used to be inherited as appendEcore
	protected @NonNull Boolean appendEcoreLegacy(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement unboxedValue) {

		TypeId typeId = unboxedValue.getASTypeId();
		js.appendDeclaration(cgEcoreExp);
		js.append(" = ");
		if (!unboxedValue.isNonNull()) {
			js.appendReferenceTo(unboxedValue);
			js.append(" == null ? null : ");
		}
		if (isAssignableTo(Iterable.class)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be AbstractCollectionDescriptor");
		}
		else if (isAssignableTo(BigInteger.class)
				|| isAssignableTo(Long.class)
				|| isAssignableTo(Integer.class)
				|| isAssignableTo(Short.class)
				|| isAssignableTo(Byte.class)
				|| isAssignableTo(Character.class)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".integerValueOf(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if ((getJavaClass() == Object.class) && (typeId == TypeId.INTEGER)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be IntegerObjectDescriptor");
		}
		else if (isAssignableTo(BigDecimal.class)
				|| isAssignableTo(Double.class)
				|| isAssignableTo(Float.class)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".realValueOf(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if (isAssignableTo(Number.class)) {
			if (typeId == TypeId.REAL){
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be RealObjectDescriptor");
			}
			else {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be UnlimitedNaturalObjectDescriptor");
			}
		}
		else if (isAssignableTo(EEnumLiteral.class)) {
			js.appendClassReference(null, IdManager.class);
			js.append(".getEnumerationLiteralId(");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		else if (isAssignableTo(Enumerator.class)) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be EnumerationObjectDescriptor");
		}
		else {//if (ObjectValue.class.isAssignableFrom(javaClass)) {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".createObjectValue(");
			js.appendIdReference(typeId);
			js.append(", ");
			js.appendReferenceTo(unboxedValue);
			js.append(")");
		}
		js.append(";\n");
		return true;
	}

	@Override
	public void appendEcoreValue(@NonNull JavaStream js, @NonNull String requiredClassName, @NonNull CGValuedElement cgValue) {
		js.appendValueName(cgValue);
	}

	@Override
	public void appendNotEqualsTerm(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull TypeDescriptor thatTypeDescriptor, @NonNull String thatName) {
		js.append("(");
		js.appendValueName(thisValue);
		js.append(" != ");
		js.append(thatName);
		js.append(") && (");
		js.appendValueName(thisValue);
		js.append(" == null || !");
		js.appendValueName(thisValue);
		js.append(".equals(");
		js.append(thatName);
		js.append("))");
	}

	@Override
	public @NonNull Boolean appendEcoreStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement boxedValue) {
		EClassifier eClassifier = cgEcoreExp.getEClassifier();
		Class<?> ecoreClass = eClassifier != null ? eClassifier.getInstanceClass() : null;
		EcoreDescriptor ecoreDescriptor = getEcoreDescriptor(js.getCodeGenerator(), ecoreClass);
		return ecoreDescriptor.appendEcore(js, localContext, cgEcoreExp, boxedValue);
		/*		UnboxedDescriptor unboxedTypeDescriptor = getUnboxedDescriptor(js.getCodeGenerator());
		CollectionDescriptor collectionDescriptor = unboxedTypeDescriptor.asCollectionDescriptor();
		if (collectionDescriptor != null) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + " should be UnboxedValuesDescriptor");
		}
		else {
			js.appendDeclaration(cgEcoreExp);
			js.append(" = ");
			if (isAssignableTo(IntegerValue.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be IntegerValueDescriptor");
			}
			else if (isAssignableTo(RealValue.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be RealValueDescriptor");
			}
			else { //if (boxedTypeDescriptor.isAssignableTo(EnumerationLiteralId.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be EnumerationValueDescriptor");
			}
		} */
	}

	@Override
	public void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull CGValuedElement thatValue, boolean notEquals) {
		PivotMetamodelManager metamodelManager = js.getCodeGenerator().getEnvironmentFactory().getMetamodelManager();
		if (isBoxedType(metamodelManager, thisValue) && isBoxedType(metamodelManager, thatValue)) {
			boolean nullSafe = thisValue.isNonNull() && thatValue.isNonNull();
			if (!nullSafe) {
				String prefix = "";
				if (!thisValue.isNonNull()) {
					js.append("(");
					js.appendValueName(thisValue);
					js.append(" != null)");
					prefix = " && ";
				}
				if (!thatValue.isNonNull()) {
					js.append(prefix);
					js.append("(");
					js.appendValueName(thatValue);
					js.append(" != null)");
				}
				js.append(" ? (");
			}
			js.appendValueName(thisValue);
			js.append(".getTypeId()");
			js.append(notEquals ? " != " : " == ");
			js.appendValueName(thatValue);
			js.append(".getTypeId()");
			if (!nullSafe) {
				js.append(") : ");
				//				js.appendThrowBooleanInvalidValueException("null input for \"" + (notEquals ? "<>" : "=") + "\" operation");
				js.append(notEquals ? "true" : "false");
				//				js.append("true");
			}
		}
		/*		else if (zzisBoxedElement(thisValue) && zzisBoxedElement(thatValue)) {		// FIXME Is this needed ?
			js.appendValueName(thisValue);
			js.append(notEquals ? " != " : " == ");
			js.appendValueName(thatValue);
		} */
		else if (thisValue.isNonNull()) {
			if (notEquals) {
				js.append("!");
			}
			js.appendValueName(thisValue);
			js.append(".equals(");
			js.appendValueName(thatValue);
			js.append(")");
		}
		else if (thatValue.isNonNull()) {
			if (notEquals) {
				js.append("!");
			}
			js.appendValueName(thatValue);
			js.append(".equals(");
			js.appendValueName(thisValue);
			js.append(")");
		}
		else {
			js.append("(");
			js.appendValueName(thisValue);
			js.append(" != null) ? ");
			if (notEquals) {
				js.append("!");
			}
			js.appendValueName(thisValue);
			js.append(".equals(");
			js.appendValueName(thatValue);
			js.append(") : (");
			js.appendValueName(thatValue);
			js.append(notEquals ? " != " : " == ");
			js.append("null)");
		}
	}

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
		UnboxedDescriptor unboxedTypeDescriptor = getUnboxedDescriptor(js.getCodeGenerator());
		CollectionDescriptor collectionDescriptor = unboxedTypeDescriptor.asCollectionDescriptor();
		if (collectionDescriptor != null) {
			System.err.println(getClass().getSimpleName() + " should be UnboxedValuesDescriptor");		// FIXME should be redundant
			js.appendDeclaration(cgUnboxExp);;
			js.append(" = ");
			js.appendValueName(boxedValue);
			js.append(";\n");
			return true;
		}
		else {
			js.appendDeclaration(cgUnboxExp);
			js.append(" = ");
			if (isAssignableTo(IntegerValue.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be IntegerValueDescriptor");
			}
			else if (isAssignableTo(RealValue.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be RealValueDescriptor");
			}
			else { //if (boxedTypeDescriptor.isAssignableTo(EnumerationLiteralId.class)) {
				throw new UnsupportedOperationException(getClass().getSimpleName() + " should be EnumerationValueDescriptor");
			}
		}
	}

	@Override
	public @Nullable CollectionDescriptor asCollectionDescriptor() {
		return null;
	}

	@Override
	public @Nullable EClassifier getEClassifier() {
		return null;
	}

	public @NonNull ElementId getElementId() {
		return elementId;
	}

//	@Override
	public @NonNull Class<?> getNonPrimitiveJavaClass() {
		return getJavaClass(); // throw new UnsupportedOperationException(); //
	}

	@Override
	public  @NonNull TypeDescriptor getPrimitiveDescriptor() {
		return this;
	}

	@Override
	public boolean isAssignableTo(@NonNull Class<?> javaClass) {
		return javaClass == Object.class;
	}

	protected boolean isBoxedType(@NonNull PivotMetamodelManager metamodelManager, @NonNull CGValuedElement cgValue) {
		Element ast = cgValue.getAst();
		if (!(ast instanceof TypedElement)) {
			return false;
		}
		Type asType = ((TypedElement)ast).getType();
		if (asType == null) {
			return false;
		}
		if (asType instanceof VoidType) {
			return false;
		}
		Type type = asType/*.behavioralType()*/;
		if (type instanceof Enumeration) {
			return false;
		}
		Type oclTypeType = metamodelManager.getStandardLibrary().getOclTypeType();
		return metamodelManager.conformsTo(type, TemplateParameterSubstitutions.EMPTY, oclTypeType, TemplateParameterSubstitutions.EMPTY);
	}

	@Override
	public boolean isPrimitive() {			// FIXME move to derived classes
		Class<?> javaClass = getJavaClass();
		if ((javaClass == boolean.class)
				|| (javaClass == byte.class)
				|| (javaClass == char.class)
				|| (javaClass == double.class)
				|| (javaClass == float.class)
				|| (javaClass == int.class)
				|| (javaClass == long.class)
				|| (javaClass == short.class)) {
			return true;
		}
		return false;
	}

	@Override
	public @NonNull String toString() {
		return elementId + " => " + getClassName();
	}
}
