/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.library.string.StringConcatOperation;
import org.eclipse.ocl.pivot.library.string.StringStartsWithOperation;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ShadowExpImpl#getOwnedParts <em>Owned Parts</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ShadowExpImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShadowExpImpl extends OCLExpressionImpl implements ShadowExp
{
	/**
	 * The cached value of the '{@link #getOwnedParts() <em>Owned Parts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParts()
	 * @generated
	 * @ordered
	 */
	protected EList<ShadowPart> ownedParts;
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShadowExpImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.SHADOW_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<ShadowPart> getOwnedParts()
	{
		if (ownedParts == null)
		{
			ownedParts = new EObjectContainmentEList<ShadowPart>(ShadowPart.class, this, PivotPackage.SHADOW_EXP__OWNED_PARTS);
		}
		return ownedParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue()
	{
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(String newValue)
	{
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.SHADOW_EXP__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateClassHasNoStringValueInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv ClassHasNoStringValueInitializer: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateDataTypeHasNoPartInitializers(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv DataTypeHasNoPartInitializers: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateDataTypeHasOnePartInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv DataTypeHasOnePartInitializer:
		 *   let
		 *     severity : Integer[1] = 'ShadowExp::DataTypeHasOnePartInitializer'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : Boolean[?] = type.oclIsKindOf(DataType) implies
		 *         ownedParts->size() = 1
		 *       in
		 *         'ShadowExp::DataTypeHasOnePartInitializer'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_ShadowExp_c_c_DataTypeHasOnePartInitializer);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_DataType = idResolver.getClass(PivotTables.CLSSid_DataType, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_DataType).booleanValue();
					CAUGHT_oclIsKindOf = oclIsKindOf;
				}
				catch (Exception e) {
					CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					final /*@Thrown*/ java.util.@NonNull List<ShadowPart> ownedParts = this.getOwnedParts();
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedParts = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_ShadowPart, ownedParts);
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedParts);
					final /*@Thrown*/ boolean eq = size.equals(PivotTables.INT_1);
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_oclIsKindOf, CAUGHT_eq);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_ShadowExp_c_c_DataTypeHasOnePartInitializer, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateDataTypeHasStringValueInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv DataTypeHasStringValueInitializer: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateInitializesAllClassProperties(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv InitializesAllClassProperties:
		 *   let
		 *     severity : Integer[1] = 'ShadowExp::InitializesAllClassProperties'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : OclAny[1] = if type.oclIsKindOf(DataType)
		 *         then
		 *           let status : Boolean[1] = true
		 *           in
		 *             if status = true
		 *             then true
		 *             else Tuple{status = status, message = ''}
		 *             endif
		 *         else
		 *           let
		 *             partProperties : Set(Property) = ownedParts.referredProperty->asSet()
		 *           in
		 *             let
		 *               allProperties : Set(Property) = type.oclAsType(Class)
		 *               ->closure(superClasses)
		 *               ?.ownedProperties->asSet()
		 *             in
		 *               let
		 *                 classProperties : Set(Property) = allProperties->reject(isDerived or isImplicit or isStatic or isTransient)
		 *                 ->reject(name.startsWith('ocl'))
		 *               in
		 *                 let
		 *                   requiredClassProperties : Set(Property) = classProperties->reject(defaultValueString <> null)
		 *                   ->reject(isVolatile or not isRequired)
		 *                   ->reject(type.oclIsKindOf(CollectionType))
		 *                   ->reject(opposite <> null and opposite.isComposite)
		 *                 in
		 *                   let
		 *                     extraProperties : Set(NamedElement) = partProperties->excludingAll(classProperties)
		 *                   in
		 *                     let
		 *                       missingProperties : Set(NamedElement) = requiredClassProperties->excludingAll(partProperties)
		 *                     in
		 *                       if extraProperties->notEmpty()
		 *                       then
		 *                         let status : Boolean[1] = false
		 *                         in
		 *                           if status = true
		 *                           then true
		 *                           else
		 *                             Tuple{
		 *                               status = status, message = extraProperties->sortedBy(name)
		 *                               ->iterate(p; acc : String[?] = 'Unexpected initializers:' | acc + ' ' + p.name)
		 *                             }
		 *                           endif
		 *                       else
		 *                         if missingProperties->notEmpty()
		 *                         then
		 *                           let status : Boolean[1] = false
		 *                           in
		 *                             if status = true
		 *                             then true
		 *                             else
		 *                               Tuple{
		 *                                 status = status, message = missingProperties->sortedBy(name)
		 *                                 ->iterate(p; acc : String[?] = 'Missing initializers:' | acc + ' ' + p.name)
		 *                               }
		 *                             endif
		 *                         else
		 *                           let status : Boolean[1] = true
		 *                           in
		 *                             if status = true
		 *                             then true
		 *                             else Tuple{status = status, message = ''}
		 *                             endif
		 *                         endif
		 *                       endif
		 *         endif
		 *       in
		 *         'ShadowExp::InitializesAllClassProperties'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull StandardLibrary standardLibrary = idResolver.getStandardLibrary();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_ShadowExp_c_c_InitializesAllClassProperties);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ java.lang.@NonNull Object symbol_7;
		if (le) {
			symbol_7 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_DataType_0 = idResolver.getClass(PivotTables.CLSSid_DataType, null);
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_DataType_0).booleanValue();
				/*@Thrown*/ java.lang.@NonNull Object result;
				if (oclIsKindOf) {
					result = ValueUtil.TRUE_VALUE;
				}
				else {
					final /*@Thrown*/ java.util.@NonNull List<ShadowPart> ownedParts = this.getOwnedParts();
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedParts = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_ShadowPart, ownedParts);
					/*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(PivotTables.SEQ_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedParts.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SequenceValue collect;
					while (true) {
						if (!ITERATOR__1.hasNext()) {
							collect = accumulator;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull ShadowPart _1 = (ShadowPart)ITERATOR__1.next();
						/**
						 * referredProperty
						 */
						@SuppressWarnings("null")
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Property referredProperty = _1.getReferredProperty();
						//
						accumulator.add(referredProperty);
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue partProperties = CollectionAsSetOperation.INSTANCE.evaluate(collect);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_0 = idResolver.getClass(PivotTables.CLSSid_Class, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Class oclAsType = ClassUtil.nonNullState((org.eclipse.ocl.pivot.Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_Class_0));
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_Class, oclAsType);
					final org.eclipse.ocl.pivot.@NonNull Class TYPE_closure_0 = executor.getStaticTypeOf(oclAsSet);
					final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_closure_0 = (LibraryIteration.LibraryIterationExtension)TYPE_closure_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
					final @NonNull Object ACC_closure_0 = IMPL_closure_0.createAccumulatorValue(executor, PivotTables.SET_CLSSid_Class, PivotTables.SET_CLSSid_Class);
					/**
					 * Implementation of the iterator body.
					 */
					final @NonNull AbstractBinaryOperation BODY_closure_0 = new AbstractBinaryOperation()
					{
						/**
						 * superClasses
						 */
						@Override
						public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ java.lang.@Nullable Object _1_0) {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Class symbol_0 = (org.eclipse.ocl.pivot.Class)_1_0;
							if (symbol_0 == null) {
								throw new InvalidValueException("Null source for \'Class::superClasses\'");
							}
							final /*@Thrown*/ java.util.@NonNull List<org.eclipse.ocl.pivot.Class> superClasses = symbol_0.getSuperClasses();
							final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue BOXED_superClasses = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Class, superClasses);
							return BOXED_superClasses;
						}
					};
					final @NonNull  ExecutorSingleIterationManager MGR_closure_0 = new ExecutorSingleIterationManager(executor, PivotTables.SET_CLSSid_Class, BODY_closure_0, oclAsSet, ACC_closure_0);
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue closure = ClassUtil.nonNullState((SetValue)IMPL_closure_0.evaluateIteration(MGR_closure_0));
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue safe_collect_sources = (SetValue)CollectionExcludingOperation.INSTANCE.evaluate(closure, (Object)null);
					/*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createBagAccumulatorValue(PivotTables.BAG_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_1 = safe_collect_sources.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull BagValue collect_0;
					while (true) {
						if (!ITERATOR__1_1.hasNext()) {
							collect_0 = accumulator_0;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class _1_1 = (org.eclipse.ocl.pivot.Class)ITERATOR__1_1.next();
						/**
						 * ownedProperties
						 */
						final /*@Thrown*/ java.util.@NonNull List<Property> ownedProperties = _1_1.getOwnedProperties();
						final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Property, ownedProperties);
						//
						for (Object value : BOXED_ownedProperties.flatten().getElements()) {
							accumulator_0.add(value);
						}
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue allProperties = CollectionAsSetOperation.INSTANCE.evaluate(collect_0);
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_2 = allProperties.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue reject;
					while (true) {
						if (!ITERATOR__1_2.hasNext()) {
							reject = accumulator_1;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_2 = (Property)ITERATOR__1_2.next();
						/**
						 * isDerived or isImplicit or isStatic or isTransient
						 */
						/*@Caught*/ @Nullable Object CAUGHT_or_0;
						try {
							/*@Caught*/ @Nullable Object CAUGHT_or;
							try {
								/*@Caught*/ @NonNull Object CAUGHT_isDerived;
								try {
									final /*@Thrown*/ boolean isDerived = _1_2.isIsDerived();
									CAUGHT_isDerived = isDerived;
								}
								catch (Exception e) {
									CAUGHT_isDerived = ValueUtil.createInvalidValue(e);
								}
								/*@Caught*/ @Nullable Object CAUGHT_isImplicit;
								try {
									final /*@Thrown*/ java.lang.@Nullable Boolean isImplicit = _1_2.isIsImplicit();
									CAUGHT_isImplicit = isImplicit;
								}
								catch (Exception e) {
									CAUGHT_isImplicit = ValueUtil.createInvalidValue(e);
								}
								final /*@Thrown*/ java.lang.@Nullable Boolean or = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_isDerived, CAUGHT_isImplicit);
								CAUGHT_or = or;
							}
							catch (Exception e) {
								CAUGHT_or = ValueUtil.createInvalidValue(e);
							}
							/*@Caught*/ @NonNull Object CAUGHT_isStatic;
							try {
								final /*@Thrown*/ boolean isStatic = _1_2.isIsStatic();
								CAUGHT_isStatic = isStatic;
							}
							catch (Exception e) {
								CAUGHT_isStatic = ValueUtil.createInvalidValue(e);
							}
							final /*@Thrown*/ java.lang.@Nullable Boolean or_0 = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_or, CAUGHT_isStatic);
							CAUGHT_or_0 = or_0;
						}
						catch (Exception e) {
							CAUGHT_or_0 = ValueUtil.createInvalidValue(e);
						}
						/*@Caught*/ @NonNull Object CAUGHT_isTransient;
						try {
							final /*@Thrown*/ boolean isTransient = _1_2.isIsTransient();
							CAUGHT_isTransient = isTransient;
						}
						catch (Exception e) {
							CAUGHT_isTransient = ValueUtil.createInvalidValue(e);
						}
						final /*@Thrown*/ java.lang.@Nullable Boolean or_1 = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_or_0, CAUGHT_isTransient);
						if (or_1 == null) {
							throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
						}
						//
						if (or_1 == ValueUtil.FALSE_VALUE) {
							accumulator_1.add(_1_2);
						}
					}
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_3 = reject.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue classProperties;
					while (true) {
						if (!ITERATOR__1_3.hasNext()) {
							classProperties = accumulator_2;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_3 = (Property)ITERATOR__1_3.next();
						/**
						 * name.startsWith('ocl')
						 */
						final /*@Thrown*/ java.lang.@Nullable String name = _1_3.getName();
						final /*@Thrown*/ boolean startsWith = StringStartsWithOperation.INSTANCE.evaluate(name, PivotTables.STR_ocl).booleanValue();
						//
						if (startsWith == ValueUtil.FALSE_VALUE) {
							accumulator_2.add(_1_3);
						}
					}
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_3 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_4 = classProperties.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue reject_2;
					while (true) {
						if (!ITERATOR__1_4.hasNext()) {
							reject_2 = accumulator_3;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_4 = (Property)ITERATOR__1_4.next();
						/**
						 * defaultValueString <> null
						 */
						final /*@Thrown*/ java.lang.@Nullable String defaultValueString = _1_4.getDefaultValueString();
						final /*@Thrown*/ boolean ne = defaultValueString != null;
						//
						if (ne == ValueUtil.FALSE_VALUE) {
							accumulator_3.add(_1_4);
						}
					}
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_4 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_5 = reject_2.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue reject_1;
					while (true) {
						if (!ITERATOR__1_5.hasNext()) {
							reject_1 = accumulator_4;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_5 = (Property)ITERATOR__1_5.next();
						/**
						 * isVolatile or not isRequired
						 */
						/*@Caught*/ @NonNull Object CAUGHT_isVolatile;
						try {
							final /*@Thrown*/ boolean isVolatile = _1_5.isIsVolatile();
							CAUGHT_isVolatile = isVolatile;
						}
						catch (Exception e) {
							CAUGHT_isVolatile = ValueUtil.createInvalidValue(e);
						}
						/*@Caught*/ @NonNull Object CAUGHT_isRequired;
						try {
							final /*@Thrown*/ boolean isRequired = _1_5.isIsRequired();
							CAUGHT_isRequired = isRequired;
						}
						catch (Exception e) {
							CAUGHT_isRequired = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_isRequired);
						final /*@Thrown*/ java.lang.@Nullable Boolean or_2 = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_isVolatile, not);
						if (or_2 == null) {
							throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
						}
						//
						if (or_2 == ValueUtil.FALSE_VALUE) {
							accumulator_4.add(_1_5);
						}
					}
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_5 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_6 = reject_1.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue reject_0;
					while (true) {
						if (!ITERATOR__1_6.hasNext()) {
							reject_0 = accumulator_5;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_6 = (Property)ITERATOR__1_6.next();
						/**
						 * type.oclIsKindOf(CollectionType)
						 */
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_0 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_1 = _1_6.getType();
						final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_1, TYP_CollectionType_0).booleanValue();
						//
						if (oclIsKindOf_0 == ValueUtil.FALSE_VALUE) {
							accumulator_5.add(_1_6);
						}
					}
					/*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_6 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
					@NonNull Iterator<Object> ITERATOR__1_7 = reject_0.iterator();
					/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue requiredClassProperties;
					while (true) {
						if (!ITERATOR__1_7.hasNext()) {
							requiredClassProperties = accumulator_6;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Property _1_7 = (Property)ITERATOR__1_7.next();
						/**
						 * opposite <> null and opposite.isComposite
						 */
						/*@Caught*/ @NonNull Object CAUGHT_ne_0;
						try {
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Property opposite = _1_7.getOpposite();
							final /*@Thrown*/ boolean ne_0 = opposite != null;
							CAUGHT_ne_0 = ne_0;
						}
						catch (Exception e) {
							CAUGHT_ne_0 = ValueUtil.createInvalidValue(e);
						}
						/*@Caught*/ @NonNull Object CAUGHT_isComposite;
						try {
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Property opposite_0 = _1_7.getOpposite();
							if (opposite_0 == null) {
								throw new InvalidValueException("Null source for \'Property::isComposite\'");
							}
							final /*@Thrown*/ boolean isComposite = opposite_0.isIsComposite();
							CAUGHT_isComposite = isComposite;
						}
						catch (Exception e) {
							CAUGHT_isComposite = ValueUtil.createInvalidValue(e);
						}
						final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_ne_0, CAUGHT_isComposite);
						if (and == null) {
							throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
						}
						//
						if (and == ValueUtil.FALSE_VALUE) {
							accumulator_6.add(_1_7);
						}
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue extraProperties = (SetValue)CollectionExcludingAllOperation.INSTANCE.evaluate(partProperties, classProperties);
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue missingProperties = (SetValue)CollectionExcludingAllOperation.INSTANCE.evaluate(requiredClassProperties, partProperties);
					final /*@Thrown*/ boolean notEmpty = CollectionNotEmptyOperation.INSTANCE.evaluate(extraProperties).booleanValue();
					/*@Thrown*/ java.lang.@NonNull Object symbol_6;
					if (notEmpty) {
						final org.eclipse.ocl.pivot.@NonNull Class TYPE_sortedBy_1 = executor.getStaticTypeOf(extraProperties);
						final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_sortedBy_1 = (LibraryIteration.LibraryIterationExtension)TYPE_sortedBy_1.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__sortedBy);
						final @NonNull Object ACC_sortedBy_1 = IMPL_sortedBy_1.createAccumulatorValue(executor, PivotTables.ORD_CLSSid_NamedElement, TypeId.STRING);
						/**
						 * Implementation of the iterator body.
						 */
						final @NonNull AbstractBinaryOperation BODY_sortedBy_1 = new AbstractBinaryOperation()
						{
							/**
							 * name
							 */
							@Override
							public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object extraProperties, final /*@NonInvalid*/ java.lang.@Nullable Object _1_8) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable NamedElement symbol_1 = (NamedElement)_1_8;
								if (symbol_1 == null) {
									throw new InvalidValueException("Null source for \'NamedElement::name\'");
								}
								final /*@Thrown*/ java.lang.@Nullable String name_0 = symbol_1.getName();
								return name_0;
							}
						};
						final @NonNull  ExecutorSingleIterationManager MGR_sortedBy_1 = new ExecutorSingleIterationManager(executor, PivotTables.ORD_CLSSid_NamedElement, BODY_sortedBy_1, extraProperties, ACC_sortedBy_1);
						final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue sortedBy = ClassUtil.nonNullState((OrderedSetValue)IMPL_sortedBy_1.evaluateIteration(MGR_sortedBy_1));
						/*@NonInvalid*/ java.lang.@Nullable String acc = PivotTables.STR_Unexpected_32_initializers_c;
						@NonNull Iterator<Object> ITERATOR_p_0 = sortedBy.iterator();
						/*@Thrown*/ java.lang.@Nullable String iterate;
						while (true) {
							if (!ITERATOR_p_0.hasNext()) {
								iterate = acc;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull NamedElement p_0 = (NamedElement)ITERATOR_p_0.next();
							/**
							 * acc + ' ' + p.name
							 */
							final /*@Thrown*/ java.lang.@NonNull String sum = StringConcatOperation.INSTANCE.evaluate(acc, PivotTables.STR__32);
							final /*@Thrown*/ java.lang.@Nullable String name_1 = p_0.getName();
							final /*@Thrown*/ java.lang.@NonNull String sum_0 = StringConcatOperation.INSTANCE.evaluate(sum, name_1);
							//
							acc = sum_0;
						}
						final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull TupleValue symbol_2 = ValueUtil.createTupleOfEach(PivotTables.TUPLid_, iterate, ValueUtil.FALSE_VALUE);
						symbol_6 = symbol_2;
					}
					else {
						final /*@Thrown*/ boolean notEmpty_0 = CollectionNotEmptyOperation.INSTANCE.evaluate(missingProperties).booleanValue();
						/*@Thrown*/ java.lang.@NonNull Object symbol_5;
						if (notEmpty_0) {
							final org.eclipse.ocl.pivot.@NonNull Class TYPE_sortedBy_0_0 = executor.getStaticTypeOf(missingProperties);
							final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_sortedBy_0_0 = (LibraryIteration.LibraryIterationExtension)TYPE_sortedBy_0_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__sortedBy);
							final @NonNull Object ACC_sortedBy_0_0 = IMPL_sortedBy_0_0.createAccumulatorValue(executor, PivotTables.ORD_CLSSid_NamedElement, TypeId.STRING);
							/**
							 * Implementation of the iterator body.
							 */
							final @NonNull AbstractBinaryOperation BODY_sortedBy_0_0 = new AbstractBinaryOperation()
							{
								/**
								 * name
								 */
								@Override
								public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object missingProperties, final /*@NonInvalid*/ java.lang.@Nullable Object _1_9) {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable NamedElement symbol_3 = (NamedElement)_1_9;
									if (symbol_3 == null) {
										throw new InvalidValueException("Null source for \'NamedElement::name\'");
									}
									final /*@Thrown*/ java.lang.@Nullable String name_2 = symbol_3.getName();
									return name_2;
								}
							};
							final @NonNull  ExecutorSingleIterationManager MGR_sortedBy_0_0 = new ExecutorSingleIterationManager(executor, PivotTables.ORD_CLSSid_NamedElement, BODY_sortedBy_0_0, missingProperties, ACC_sortedBy_0_0);
							final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue sortedBy_0 = ClassUtil.nonNullState((OrderedSetValue)IMPL_sortedBy_0_0.evaluateIteration(MGR_sortedBy_0_0));
							/*@NonInvalid*/ java.lang.@Nullable String acc_0 = PivotTables.STR_Missing_32_initializers_c;
							@NonNull Iterator<Object> ITERATOR_p_1 = sortedBy_0.iterator();
							/*@Thrown*/ java.lang.@Nullable String iterate_0;
							while (true) {
								if (!ITERATOR_p_1.hasNext()) {
									iterate_0 = acc_0;
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull NamedElement p_1 = (NamedElement)ITERATOR_p_1.next();
								/**
								 * acc + ' ' + p.name
								 */
								final /*@Thrown*/ java.lang.@NonNull String sum_1 = StringConcatOperation.INSTANCE.evaluate(acc_0, PivotTables.STR__32);
								final /*@Thrown*/ java.lang.@Nullable String name_3 = p_1.getName();
								final /*@Thrown*/ java.lang.@NonNull String sum_2 = StringConcatOperation.INSTANCE.evaluate(sum_1, name_3);
								//
								acc_0 = sum_2;
							}
							final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull TupleValue symbol_4 = ValueUtil.createTupleOfEach(PivotTables.TUPLid_, iterate_0, ValueUtil.FALSE_VALUE);
							symbol_5 = symbol_4;
						}
						else {
							symbol_5 = ValueUtil.TRUE_VALUE;
						}
						symbol_6 = symbol_5;
					}
					result = symbol_6;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_ShadowExp_c_c_InitializesAllClassProperties, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_7 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_7;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsNotInvalid(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv TypeIsNotInvalid:
		 *   let severity : Integer[1] = 'ShadowExp::TypeIsNotInvalid'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = type <> OclInvalid
		 *       in
		 *         'ShadowExp::TypeIsNotInvalid'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_ShadowExp_c_c_TypeIsNotInvalid);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OclInvalid_0 = idResolver.getClass(TypeId.OCL_INVALID, null);
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				final /*@Thrown*/ boolean result = (type != null) ? (type.getTypeId() != TYP_OclInvalid_0.getTypeId()) : true;
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_ShadowExp_c_c_TypeIsNotInvalid, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.SHADOW_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.SHADOW_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.SHADOW_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.SHADOW_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.SHADOW_EXP__OWNED_PARTS:
				return ((InternalEList<?>)getOwnedParts()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case PivotPackage.SHADOW_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.SHADOW_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.SHADOW_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.SHADOW_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.SHADOW_EXP__NAME:
				return getName();
			case PivotPackage.SHADOW_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.SHADOW_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.SHADOW_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.SHADOW_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.SHADOW_EXP__OWNED_PARTS:
				return getOwnedParts();
			case PivotPackage.SHADOW_EXP__VALUE:
				return getValue();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PivotPackage.SHADOW_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SHADOW_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.SHADOW_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SHADOW_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.SHADOW_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.SHADOW_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.SHADOW_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.SHADOW_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.SHADOW_EXP__OWNED_PARTS:
				getOwnedParts().clear();
				getOwnedParts().addAll((Collection<? extends ShadowPart>)newValue);
				return;
			case PivotPackage.SHADOW_EXP__VALUE:
				setValue((String)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.SHADOW_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.SHADOW_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.SHADOW_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.SHADOW_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.SHADOW_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.SHADOW_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.SHADOW_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.SHADOW_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.SHADOW_EXP__OWNED_PARTS:
				getOwnedParts().clear();
				return;
			case PivotPackage.SHADOW_EXP__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.SHADOW_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.SHADOW_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.SHADOW_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.SHADOW_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.SHADOW_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.SHADOW_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.SHADOW_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.SHADOW_EXP__TYPE:
				return type != null;
			case PivotPackage.SHADOW_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.SHADOW_EXP__OWNED_PARTS:
				return ownedParts != null && !ownedParts.isEmpty();
			case PivotPackage.SHADOW_EXP__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.SHADOW_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.SHADOW_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.SHADOW_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.SHADOW_EXP___IS_NON_NULL:
				return isNonNull();
			case PivotPackage.SHADOW_EXP___IS_NULL:
				return isNull();
			case PivotPackage.SHADOW_EXP___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_CLASS_HAS_NO_STRING_VALUE_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateClassHasNoStringValueInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_NO_PART_INITIALIZERS__DIAGNOSTICCHAIN_MAP:
				return validateDataTypeHasNoPartInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_ONE_PART_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateDataTypeHasOnePartInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_STRING_VALUE_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateDataTypeHasStringValueInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_INITIALIZES_ALL_CLASS_PROPERTIES__DIAGNOSTICCHAIN_MAP:
				return validateInitializesAllClassProperties((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.SHADOW_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitShadowExp(this);
	}

	@Override
	public org.eclipse.ocl.pivot.Class getType() {
		return (org.eclipse.ocl.pivot.Class)super.getType();
	}
} //ConstructorExpImpl
