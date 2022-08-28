/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.lang.reflect.Method;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * AbstractProperty provides the mandatory implementation of the LibraryProperty interface that supports
 * the polymorphic implmentation of property navigations.
 * </br><b>Regular properties</b>
 *</br>CompositionProperty - simple access to an Ecore defined containing object via its opoosite
 *</br>ExplicitNavigationProperty - simple access to an Ecore-defined slot
 *</br>TuplePartProperty - container is a Tuple
 * </br><b>Irregular properties</b>
 *</br>ConstrainedProperty - evaluation of the default expression of a derived property
 *</br>EObjectProperty - obsolete not used
 *</br>ForeignProperty - access to a non-static slot reified by the ModelManager since not defined by Ecore
 *</br>ImplicitNonCompositionProperty - access to a non-static slot using the ModelManager to invert the Ecore-defined opposite
 *</br>NativeProperty - ?? an escape to Java used by autogen ??
 *</br>StaticProperty - access to a static slot reified by the ModelManager
 * </br><b>UML properties</b>
 *</br>(UML)BaseProperty - access from a Stereotype via a base_xxx property
 *</br>(UML)ExtensionProperty - access to a Stereotype via an extension_xxx property
 *</br>InstanceSlotNavigationProperty - access to a slot in an InstanceValue
 *</br>(UML)StereotypeProperty - access to a Stereotype / ElementExtension-defined slot
 *</br>UMLRedefinedNavigationProperty - regular slot access using a redefined name
 * </br><b>Special properties - specific to a particular metamode lproperty</b>
 *</br>CollectionElementTypeProperty (obsolete replaced by regular access in metaclass)
 *</br>CollectionLowerProperty (obsolete replaced by regular access in metaclass)
 *</br>CollectionUpperProperty (obsolete replaced by regular access in metaclass)
 *</br>EnumerationOwnedLiteralProperty (obsolete replaced by regular access in metaclass)
 *</br>MapKeyTypeProperty (obsolete replaced by regular access in metaclass)
 *</br>MapValueTypeProperty (obsolete replaced by regular access in metaclass)
 *</br>OclElementOclContainerProperty
 *</br>OclElementOclContentsProperty
 *</br>(OclAny)UnsupportedOperation - placeholder for parsing failure
 * </br><b>CGed properties - selects a specific optimization</b>
 *</br>EcoreExecutorProperty - CGed reification (LibraryProperty inheritance is redundant)
 *</br>EcoreLibraryOppositeProperty - CGed reifofation of opposite (dubious inheritance)
 *</br>EcoreLibraryProperty - obsolete not used
 *</br>UnboxedCompositionProperty
 *</br>UnboxedExplicitNavigationProperty
 *</br>UnboxedOppositeNavigationProperty
 */
public abstract class AbstractProperty extends AbstractFeature implements LibraryProperty.LibraryPropertyExtension
{
	/** @deprecated use Executor
	 * @since 1.1*/
	@Deprecated
	@Override
	public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		return evaluate(getExecutor(evaluator), returnTypeId, sourceValue);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.18
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull Method getEvaluateMethod(@NonNull Property asProperty) {
		try {
			return getClass().getMethod("evaluate"/*JavaConstants.EVALUATE_NAME*/, evaluateArguments0);
		} catch (Exception e) {
			throw new UnsupportedOperationException(getClass().getName() + ".evaluate(Executor, TypeId, Object)");
		}
	}
}
