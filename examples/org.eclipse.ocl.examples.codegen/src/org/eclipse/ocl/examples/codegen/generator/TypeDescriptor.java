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
package org.eclipse.ocl.examples.codegen.generator;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.java.types.CollectionDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.EcoreDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.UnboxedDescriptor;

/**
 * TypeDescriptor captures the characteristics of a Java type and supports serialization to a javaStream.
 * <p>
 * Derived classes support boxed/unboxed types, static/dynamic Ecore and collections.
 */
public interface TypeDescriptor
{
	/**
	 * Append the declaration of this type to a JavaStream. e.g. "typename"
	 */
	void append(@NonNull JavaStream js, @Nullable Boolean isRequired);

	/**
	 * Append the appropriate statements to js to create the boxed value for cgBboxExp from its unboxedValue.
	 */
	@NonNull Boolean appendBox(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGBoxExp cgBoxExp, @NonNull CGValuedElement unboxedValue);

	/**
	 * Append the actualJavaClass subStream to js wrapped in a cast to this type.g. "(typename)subStream"
	 */
	void appendCast(@NonNull JavaStream js, @Nullable Boolean isRequired, @Nullable Class<?> actualJavaClass, @NonNull SubStream subStream);

	/**
	 * Append a cgElement to js wrapped in a cast to this type
	 */
	void appendCastTerm(@NonNull JavaStream js, @NonNull CGValuedElement cgElement);

	/**
	 * Append the appropriate statements to js to create the ecore value for cgEcoreExp from its boxedValue.
	 */
	@NonNull Boolean appendEcoreStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext2,
			@NonNull CGEcoreExp cgEcoreExp, @NonNull CGValuedElement boxedValue);

	/**
	 * Append cgValue to js casting an internally typed Ecore value to requiredClassName. This is primarily used to
	 * cast a List&lt;...&gt; to an EList&lt;...&gt;. The default just appends the value name of cgValue.
	 */
	void appendEcoreValue(@NonNull JavaStream js, @NonNull String requiredClassName, @NonNull CGValuedElement cgValue);

	/**
	 * Append an expression term that evaluates whether (this TypedDescriptor and) thisValue is not equal to thatTypeDescriptor and thatName.
	 */
	void appendNotEqualsTerm(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull TypeDescriptor thatTypeDescriptor, @NonNull String thatName);

	/**
	 * Append an expression term that evaluates whether (this TypedDescriptor and) thisValue is not equals/ notEquals to thatValue.
	 * <br>
	 * It is assumed that all the degenerate constant cases have been optimzed away.
	 */
	void appendEqualsValue(@NonNull JavaStream js, @NonNull CGValuedElement thisValue, @NonNull CGValuedElement thatValue, boolean notEquals);

	/**
	 * Append the appropriate statements to js to create the unboxed value for cgUnboxExp from its boxedValue.
	 */
	@NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue);

	/**
	 * Return a non-null Collection type descriptor if this type descriptor describes a Collection.
	 */
	@Nullable CollectionDescriptor asCollectionDescriptor();

	/**
	 * Return the fully qualified Java class name described by this type. In the case of collection types, this method returns
	 * the class name of the collection elements.
	 */
	@NonNull String getClassName();

	/**
	 * Return the Ecore EClassifier described by this type. In the case of collection types, this method returns
	 * the EClassifier of the collection elements. May return null when no EClssifier available.
	 */
	@Nullable EClassifier getEClassifier();

	/**
	 * Return the type descriptor for use when an Ecore type would be appropriate. For EDataTypes an instanceClass may
	 * be used to disambiguate the many forms of Real and Integer.
	 * Returns this when this is an Ecore descriptor.
	 */
	@NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass);

	/**
	 * Return the basic Java class for this descriptor. e.g. List&lt;?&gt; for an unboxed collection.
	 */
	@NonNull Class<?> getJavaClass();

	/**
	 * Return the type descriptor for use when a primitive type would be appropriate.
	 * Returns this when there is no distinction for primitive types.
	 */
	@NonNull TypeDescriptor getPrimitiveDescriptor();

	/**
	 * Return the type descriptor for use when an unboxed type would be appropriate.
	 * Returns this when this is an unboxed descriptor.
	 */
	@NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator);

	/**
	 * Return the basic Java class for this descriptor. e.g. List&lt;?&gt; for an unboxed collection.
	 * Returns null for no Java class known.
	 */
	@Nullable Class<?> hasJavaClass();

	/**
	 * Return true if an instance described by typeDescriptor may be assigned to an instance described by this.
	 */
	boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor);

	/**
	 * Return true if an instance described by this typeDescriptor may be assigned to a javaClass.
	 */
	boolean isAssignableTo(@NonNull Class<?> javaClass);

	/**
	 * Return true is this is a built-in primitive type such as boolean or int.
	 * Such types cannot have @NonNull annotations.
	 */
	boolean isPrimitive();
}
