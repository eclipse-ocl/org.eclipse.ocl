/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration.LibraryIterationExtension;
import org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
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
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SequenceValue.Accumulator;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;

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
	 * The number of structural features of the '<em>Shadow Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SHADOW_EXP_FEATURE_COUNT = OCLExpressionImpl.OCL_EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The number of operations of the '<em>Shadow Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SHADOW_EXP_OPERATION_COUNT = OCLExpressionImpl.OCL_EXPRESSION_OPERATION_COUNT + 6;
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
			ownedParts = new EObjectContainmentEList<ShadowPart>(ShadowPart.class, this, 9);
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
			eNotify(new ENotificationImpl(this, Notification.SET, 10, oldValue, value));
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
		final @NonNull String constraintName = "ShadowExp::DataTypeHasOnePartInitializer";
		try {
			/**
			 *
			 * inv DataTypeHasOnePartInitializer:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[?] = type.oclIsKindOf(DataType) implies
			 *         ownedParts->size() = 1
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.SHADOW_EXP___VALIDATE_DATA_TYPE_HAS_ONE_PART_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_DataType_0 = idResolver.getClass(PivotTables.CLSSid_DataType, null);
						final /*@NonInvalid*/ @Nullable Type type = this.getType();
						final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_DataType_0).booleanValue();
						CAUGHT_oclIsKindOf = oclIsKindOf;
					}
					catch (Exception THROWN_CAUGHT_oclIsKindOf) {
						CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_CAUGHT_oclIsKindOf);
					}
					final /*@Thrown*/ @Nullable Boolean implies;
					if (CAUGHT_oclIsKindOf == ValueUtil.FALSE_VALUE) {
						implies = ValueUtil.TRUE_VALUE;
					}
					else {
						final /*@NonInvalid*/ @NonNull List<ShadowPart> ownedParts_0 = this.getOwnedParts();
						final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedParts = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_ShadowPart, ownedParts_0);
						final /*@NonInvalid*/ @NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedParts);
						final /*@NonInvalid*/ boolean IsEQ_ = size.equals(PivotTables.INT_1);
						if (IsEQ_) {
							implies = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_oclIsKindOf;
							}
							implies = ValueUtil.FALSE_VALUE;
						}
					}
					result = implies;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
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
		final @NonNull String constraintName = "ShadowExp::InitializesAllClassProperties";
		try {
			/**
			 *
			 * inv InitializesAllClassProperties:
			 *   let severity : Integer[1] = constraintName.getSeverity()
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
			 *               .ownedProperties->asSet()
			 *             in
			 *               let
			 *                 classProperties : Set(Property) = allProperties->reject(isDerived or isImplicit or isStatic or isTransient)
			 *                 ->reject(name?.startsWith('ocl'))
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
			 *                               ->iterate(p; acc : String[1] = 'Unexpected initializers:' | acc + ' ' + p.name)
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
			 *                                 ->iterate(p; acc : String[1] = 'Missing initializers:' | acc + ' ' + p.name)
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
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull StandardLibrary standardLibrary = executor.getStandardLibrary();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.SHADOW_EXP___VALIDATE_INITIALIZES_ALL_CLASS_PROPERTIES__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @NonNull Object result;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_DataType = idResolver.getClass(PivotTables.CLSSid_DataType, null);
					final /*@NonInvalid*/ @Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_DataType).booleanValue();
					/*@Thrown*/ @NonNull Object IF_oclIsKindOf;
					if (oclIsKindOf) {
						IF_oclIsKindOf = ValueUtil.TRUE_VALUE;
					}
					else {
						final /*@NonInvalid*/ @NonNull List<ShadowPart> ownedParts_0 = this.getOwnedParts();
						final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedParts = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_ShadowPart, ownedParts_0);
						/*@Thrown*/ @NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(PivotTables.SEQ_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1 = BOXED_ownedParts.iterator();
						/*@NonInvalid*/ @NonNull SequenceValue collect_0;
						while (true) {
							if (!ITER__1.hasNext()) {
								collect_0 = accumulator_0;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull ShadowPart _1 = (@NonNull ShadowPart)ITER__1.next();
							/**
							 * referredProperty
							 */
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull Property referredProperty = _1.getReferredProperty();
							//
							accumulator_0.add(referredProperty);
						}
						final /*@NonInvalid*/ @NonNull SetValue partProperties = CollectionAsSetOperation.INSTANCE.evaluate(collect_0);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_1 = idResolver.getClass(PivotTables.CLSSid_Class, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Class oclAsType = (org.eclipse.ocl.pivot.@Nullable Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_Class_1);
						final /*@Thrown*/ @NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_Class, oclAsType);
						final org.eclipse.ocl.pivot.@NonNull Class TYPE_closure = executor.getStaticTypeOfValue(null, oclAsSet);
						final @NonNull LibraryIterationExtension IMPL_closure = (LibraryIterationExtension)TYPE_closure.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
						final @NonNull Object ACC_closure = IMPL_closure.createAccumulatorValue(executor, PivotTables.SET_CLSSid_Class, PivotTables.SET_CLSSid_Class);
						/**
						 * Implementation of the iterator body.
						 */
						final @NonNull AbstractBinaryOperation BODY_closure = new AbstractBinaryOperation()
						{
							/**
							 * superClasses
							 */
							@Override
							public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ @Nullable Object _1_7) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Class CAST_1_ = (org.eclipse.ocl.pivot.Class)_1_7;
								if (CAST_1_ == null) {
									throw new InvalidValueException("Null source for \'Class::superClasses\'");
								}
								final /*@Thrown*/ @NonNull List<org.eclipse.ocl.pivot.Class> superClasses = CAST_1_.getSuperClasses();
								final /*@Thrown*/ @NonNull SetValue BOXED_superClasses = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Class, superClasses);
								return BOXED_superClasses;
							}
						};
						final @NonNull ExecutorSingleIterationManager MGR_closure = new ExecutorSingleIterationManager(executor, PivotTables.SET_CLSSid_Class, BODY_closure, oclAsSet, ACC_closure);
						@SuppressWarnings("null")
						final /*@Thrown*/ @NonNull SetValue closure = (@NonNull SetValue)IMPL_closure.evaluateIteration(MGR_closure);
						/*@Thrown*/ org.eclipse.ocl.pivot.values.BagValue.@NonNull Accumulator accumulator_1 = ValueUtil.createBagAccumulatorValue(PivotTables.BAG_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_2 = closure.iterator();
						/*@Thrown*/ @NonNull BagValue collect;
						while (true) {
							if (!ITER__1_2.hasNext()) {
								collect = accumulator_1;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class _1_2 = (org.eclipse.ocl.pivot.@NonNull Class)ITER__1_2.next();
							/**
							 * ownedProperties
							 */
							final /*@NonInvalid*/ @NonNull List<Property> ownedProperties = _1_2.getOwnedProperties();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Property, ownedProperties);
							//
							for (Object value : BOXED_ownedProperties.flatten().getElements()) {
								accumulator_1.add(value);
							}
						}
						final /*@Thrown*/ @NonNull SetValue allProperties = CollectionAsSetOperation.INSTANCE.evaluate(collect);
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator_6 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_0 = allProperties.iterator();
						/*@Thrown*/ @NonNull SetValue reject_2;
						while (true) {
							if (!ITER__1_0.hasNext()) {
								reject_2 = accumulator_6;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_0 = (@NonNull Property)ITER__1_0.next();
							/**
							 * isDerived or isImplicit or isStatic or isTransient
							 */
							/*@Caught*/ @Nullable Object CAUGHT_or;
							try {
								final /*@NonInvalid*/ boolean isDerived = _1_0.isIsDerived();
								final /*@NonInvalid*/ @Nullable Boolean or;
								if (isDerived) {
									or = ValueUtil.TRUE_VALUE;
								}
								else {
									final /*@NonInvalid*/ boolean isImplicit = _1_0.isIsImplicit();
									if (isImplicit) {
										or = ValueUtil.TRUE_VALUE;
									}
									else {
										or = ValueUtil.FALSE_VALUE;
									}
								}
								final /*@Thrown*/ @Nullable Boolean or_0;
								if (or == ValueUtil.TRUE_VALUE) {
									or_0 = ValueUtil.TRUE_VALUE;
								}
								else {
									final /*@NonInvalid*/ boolean isStatic = _1_0.isIsStatic();
									if (isStatic) {
										or_0 = ValueUtil.TRUE_VALUE;
									}
									else {
										if (or == null) {
											or_0 = null;
										}
										else {
											or_0 = ValueUtil.FALSE_VALUE;
										}
									}
								}
								CAUGHT_or = or_0;
							}
							catch (Exception THROWN_CAUGHT_or) {
								CAUGHT_or = ValueUtil.createInvalidValue(THROWN_CAUGHT_or);
							}
							final /*@Thrown*/ @Nullable Boolean or_1;
							if (CAUGHT_or == ValueUtil.TRUE_VALUE) {
								or_1 = ValueUtil.TRUE_VALUE;
							}
							else {
								final /*@NonInvalid*/ boolean isTransient = _1_0.isIsTransient();
								if (isTransient) {
									or_1 = ValueUtil.TRUE_VALUE;
								}
								else {
									if (CAUGHT_or instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_or;
									}
									if (CAUGHT_or == null) {
										or_1 = null;
									}
									else {
										or_1 = ValueUtil.FALSE_VALUE;
									}
								}
							}
							if (or_1 == null) {
								throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
							}
							//
							if (or_1 == ValueUtil.FALSE_VALUE) {
								accumulator_6.add(_1_0);
							}
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator_2 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_3 = reject_2.iterator();
						/*@Thrown*/ @NonNull SetValue classProperties;
						while (true) {
							if (!ITER__1_3.hasNext()) {
								classProperties = accumulator_2;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_3 = (@NonNull Property)ITER__1_3.next();
							/**
							 * name?.startsWith('ocl')
							 */
							final /*@NonInvalid*/ @Nullable String name = _1_3.getName();
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = name == null;
							/*@Thrown*/ @Nullable Boolean IF_IsEQ2_;
							if (IsEQ2_ == Boolean.TRUE) {
								IF_IsEQ2_ = null;
							}
							else {
								if (name == null) {
									throw new InvalidValueException("Null \'\'String\'\' rather than \'\'OclVoid\'\' value required");
								}
								final /*@Thrown*/ boolean startsWith = StringStartsWithOperation.INSTANCE.evaluate(name, PivotTables.STR_ocl).booleanValue();
								IF_IsEQ2_ = startsWith;
							}
							if (IF_IsEQ2_ == null) {
								throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
							}
							//
							if (IF_IsEQ2_ == ValueUtil.FALSE_VALUE) {
								accumulator_2.add(_1_3);
							}
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_6 = classProperties.iterator();
						/*@Thrown*/ @NonNull SetValue reject_0;
						while (true) {
							if (!ITER__1_6.hasNext()) {
								reject_0 = accumulator;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_6 = (@NonNull Property)ITER__1_6.next();
							/**
							 * defaultValueString <> null
							 */
							final /*@NonInvalid*/ @Nullable String defaultValueString = _1_6.getDefaultValueString();
							final /*@NonInvalid*/ boolean IsEQ_ = defaultValueString != null;
							//
							if (!IsEQ_) {
								accumulator.add(_1_6);
							}
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator_4 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_1 = reject_0.iterator();
						/*@Thrown*/ @NonNull SetValue reject;
						while (true) {
							if (!ITER__1_1.hasNext()) {
								reject = accumulator_4;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_1 = (@NonNull Property)ITER__1_1.next();
							/**
							 * isVolatile or not isRequired
							 */
							final /*@NonInvalid*/ boolean isVolatile = _1_1.isIsVolatile();
							final /*@Thrown*/ @Nullable Boolean or;
							if (isVolatile) {
								or = ValueUtil.TRUE_VALUE;
							}
							else {
								final /*@NonInvalid*/ boolean isRequired = _1_1.isIsRequired();
								final /*@NonInvalid*/ @Nullable Boolean not;
								if (!isRequired) {
									not = ValueUtil.TRUE_VALUE;
								}
								else {
									if (isRequired) {
										not = ValueUtil.FALSE_VALUE;
									}
									else {
										not = null;
									}
								}
								if (not == ValueUtil.TRUE_VALUE) {
									or = ValueUtil.TRUE_VALUE;
								}
								else {
									if (not == null) {
										or = null;
									}
									else {
										or = ValueUtil.FALSE_VALUE;
									}
								}
							}
							if (or == null) {
								throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
							}
							//
							if (or == ValueUtil.FALSE_VALUE) {
								accumulator_4.add(_1_1);
							}
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator_3 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_5 = reject.iterator();
						/*@Thrown*/ @NonNull SetValue reject_1;
						while (true) {
							if (!ITER__1_5.hasNext()) {
								reject_1 = accumulator_3;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_5 = (@NonNull Property)ITER__1_5.next();
							/**
							 * type.oclIsKindOf(CollectionType)
							 */
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_11 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							final /*@NonInvalid*/ @Nullable Type type_0 = _1_5.getType();
							final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_0, TYP_CollectionType_11).booleanValue();
							//
							if (oclIsKindOf_0 == ValueUtil.FALSE_VALUE) {
								accumulator_3.add(_1_5);
							}
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.values.SetValue.@NonNull Accumulator accumulator_5 = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Property);
						@NonNull Iterator<Object> ITER__1_4 = reject_1.iterator();
						/*@Thrown*/ @NonNull SetValue requiredClassProperties;
						while (true) {
							if (!ITER__1_4.hasNext()) {
								requiredClassProperties = accumulator_5;
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Property _1_4 = (@NonNull Property)ITER__1_4.next();
							/**
							 * opposite <> null and opposite.isComposite
							 */
							final /*@NonInvalid*/ @Nullable Property opposite = _1_4.getOpposite();
							final /*@NonInvalid*/ boolean IsEQ_ = opposite != null;
							final /*@Thrown*/ @Nullable Boolean and;
							if (!IsEQ_) {
								and = ValueUtil.FALSE_VALUE;
							}
							else {
								/*@Caught*/ @NonNull Object CAUGHT_isComposite;
								try {
									if (opposite == null) {
										throw new InvalidValueException("Null source for \'Property::isComposite\'");
									}
									final /*@Thrown*/ boolean isComposite = opposite.isIsComposite();
									CAUGHT_isComposite = isComposite;
								}
								catch (Exception THROWN_CAUGHT_isComposite) {
									CAUGHT_isComposite = ValueUtil.createInvalidValue(THROWN_CAUGHT_isComposite);
								}
								if (CAUGHT_isComposite == ValueUtil.FALSE_VALUE) {
									and = ValueUtil.FALSE_VALUE;
								}
								else {
									if (CAUGHT_isComposite instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_isComposite;
									}
									and = ValueUtil.TRUE_VALUE;
								}
							}
							if (and == null) {
								throw new InvalidValueException("Null body for \'Set(T).reject(Set.T[?] | Lambda T() : Boolean[1]) : Set(T)\'");
							}
							//
							if (and == ValueUtil.FALSE_VALUE) {
								accumulator_5.add(_1_4);
							}
						}
						final /*@Thrown*/ @NonNull SetValue extraProperties = (@Nullable SetValue)CollectionExcludingAllOperation.INSTANCE.evaluate(partProperties, classProperties);
						final /*@Thrown*/ @NonNull SetValue missingProperties = (@Nullable SetValue)CollectionExcludingAllOperation.INSTANCE.evaluate(requiredClassProperties, partProperties);
						final /*@Thrown*/ boolean notEmpty = CollectionNotEmptyOperation.INSTANCE.evaluate(extraProperties).booleanValue();
						/*@Thrown*/ @NonNull Object IF_notEmpty;
						if (notEmpty) {
							final org.eclipse.ocl.pivot.@NonNull Class TYPE_sortedBy = executor.getStaticTypeOfValue(null, extraProperties);
							final @NonNull LibraryIterationExtension IMPL_sortedBy = (LibraryIterationExtension)TYPE_sortedBy.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__sortedBy);
							final @NonNull Object ACC_sortedBy = IMPL_sortedBy.createAccumulatorValue(executor, PivotTables.ORD_CLSSid_NamedElement, TypeId.STRING);
							/**
							 * Implementation of the iterator body.
							 */
							final @NonNull AbstractBinaryOperation BODY_sortedBy = new AbstractBinaryOperation()
							{
								/**
								 * name
								 */
								@Override
								public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object extraProperties, final /*@NonInvalid*/ @Nullable Object _1_7) {
									final /*@NonInvalid*/ @Nullable NamedElement CAST_1_ = (NamedElement)_1_7;
									if (CAST_1_ == null) {
										throw new InvalidValueException("Null source for \'NamedElement::name\'");
									}
									final /*@Thrown*/ @Nullable String name = CAST_1_.getName();
									return name;
								}
							};
							final @NonNull ExecutorSingleIterationManager MGR_sortedBy = new ExecutorSingleIterationManager(executor, PivotTables.ORD_CLSSid_NamedElement, BODY_sortedBy, extraProperties, ACC_sortedBy);
							@SuppressWarnings("null")
							final /*@Thrown*/ @NonNull OrderedSetValue sortedBy = (@NonNull OrderedSetValue)IMPL_sortedBy.evaluateIteration(MGR_sortedBy);
							/*@NonInvalid*/ @NonNull String acc = PivotTables.STR_Unexpected_32_initializers_c;
							@NonNull Iterator<Object> ITER_p = sortedBy.iterator();
							/*@Thrown*/ @Nullable String iterate;
							while (true) {
								if (!ITER_p.hasNext()) {
									iterate = acc;
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ @NonNull NamedElement p = (@NonNull NamedElement)ITER_p.next();
								/**
								 * acc + ' ' + p.name
								 */
								final /*@NonInvalid*/ @NonNull String sum = StringConcatOperation.INSTANCE.evaluate(acc, PivotTables.STR__32);
								final /*@NonInvalid*/ @Nullable String name = p.getName();
								final /*@Thrown*/ @NonNull String sum_0 = StringConcatOperation.INSTANCE.evaluate(sum, name);
								//
								acc = sum_0;
							}
							final /*@Thrown*/ @NonNull TupleValue TUP_ = ValueUtil.createTupleOfEach(PivotTables.TUPLid_, iterate, ValueUtil.FALSE_VALUE);
							IF_notEmpty = TUP_;
						}
						else {
							final /*@Thrown*/ boolean notEmpty_0 = CollectionNotEmptyOperation.INSTANCE.evaluate(missingProperties).booleanValue();
							/*@Thrown*/ @NonNull Object IF_notEmpty_0;
							if (notEmpty_0) {
								final org.eclipse.ocl.pivot.@NonNull Class TYPE_sortedBy_0 = executor.getStaticTypeOfValue(null, missingProperties);
								final @NonNull LibraryIterationExtension IMPL_sortedBy_0 = (LibraryIterationExtension)TYPE_sortedBy_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__sortedBy);
								final @NonNull Object ACC_sortedBy_0 = IMPL_sortedBy_0.createAccumulatorValue(executor, PivotTables.ORD_CLSSid_NamedElement, TypeId.STRING);
								/**
								 * Implementation of the iterator body.
								 */
								final @NonNull AbstractBinaryOperation BODY_sortedBy_0 = new AbstractBinaryOperation()
								{
									/**
									 * name
									 */
									@Override
									public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object missingProperties, final /*@NonInvalid*/ @Nullable Object _1_7) {
										final /*@NonInvalid*/ @Nullable NamedElement CAST_1_ = (NamedElement)_1_7;
										if (CAST_1_ == null) {
											throw new InvalidValueException("Null source for \'NamedElement::name\'");
										}
										final /*@Thrown*/ @Nullable String name = CAST_1_.getName();
										return name;
									}
								};
								final @NonNull ExecutorSingleIterationManager MGR_sortedBy_0 = new ExecutorSingleIterationManager(executor, PivotTables.ORD_CLSSid_NamedElement, BODY_sortedBy_0, missingProperties, ACC_sortedBy_0);
								@SuppressWarnings("null")
								final /*@Thrown*/ @NonNull OrderedSetValue sortedBy_0 = (@NonNull OrderedSetValue)IMPL_sortedBy_0.evaluateIteration(MGR_sortedBy_0);
								/*@NonInvalid*/ @NonNull String acc_0 = PivotTables.STR_Missing_32_initializers_c;
								@NonNull Iterator<Object> ITER_p_0 = sortedBy_0.iterator();
								/*@Thrown*/ @Nullable String iterate_0;
								while (true) {
									if (!ITER_p_0.hasNext()) {
										iterate_0 = acc_0;
										break;
									}
									@SuppressWarnings("null")
									/*@NonInvalid*/ @NonNull NamedElement p_0 = (@NonNull NamedElement)ITER_p_0.next();
									/**
									 * acc + ' ' + p.name
									 */
									final /*@NonInvalid*/ @NonNull String sum = StringConcatOperation.INSTANCE.evaluate(acc_0, PivotTables.STR__32);
									final /*@NonInvalid*/ @Nullable String name = p_0.getName();
									final /*@Thrown*/ @NonNull String sum_0 = StringConcatOperation.INSTANCE.evaluate(sum, name);
									//
									acc_0 = sum_0;
								}
								final /*@Thrown*/ @NonNull TupleValue TUP__0 = ValueUtil.createTupleOfEach(PivotTables.TUPLid_, iterate_0, ValueUtil.FALSE_VALUE);
								IF_notEmpty_0 = TUP__0;
							}
							else {
								IF_notEmpty_0 = ValueUtil.TRUE_VALUE;
							}
							IF_notEmpty = IF_notEmpty_0;
						}
						IF_oclIsKindOf = IF_notEmpty;
					}
					result = IF_oclIsKindOf;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsNotInvalid(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "ShadowExp::TypeIsNotInvalid";
		try {
			/**
			 *
			 * inv TypeIsNotInvalid:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[1] = type <> OclInvalid
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.SHADOW_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OclInvalid_6 = idResolver.getClass(TypeId.OCL_INVALID, null);
				final /*@NonInvalid*/ @Nullable Type type = this.getType();
				final /*@NonInvalid*/ @NonNull InvalidType CAST_TYP_OclInvalid = (InvalidType)TYP_OclInvalid_6;
				final /*@NonInvalid*/ boolean result = (type != null) ? (type.getTypeId() != CAST_TYP_OclInvalid.getTypeId()) : true;
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 9:
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
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return isIsMany();
			case 6:
				return isIsRequired();
			case 7:
				if (resolve) return getType();
				return basicGetType();
			case 8:
				return getTypeValue();
			case 9:
				return getOwnedParts();
			case 10:
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
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 6:
				setIsRequired((Boolean)newValue);
				return;
			case 7:
				setType((Type)newValue);
				return;
			case 8:
				setTypeValue((Type)newValue);
				return;
			case 9:
				getOwnedParts().clear();
				getOwnedParts().addAll((Collection<? extends ShadowPart>)newValue);
				return;
			case 10:
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
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 6:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 7:
				setType((Type)null);
				return;
			case 8:
				setTypeValue((Type)null);
				return;
			case 9:
				getOwnedParts().clear();
				return;
			case 10:
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
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return isIsMany() != IS_MANY_EDEFAULT;
			case 6:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case 7:
				return type != null;
			case 8:
				return typeValue != null;
			case 9:
				return ownedParts != null && !ownedParts.isEmpty();
			case 10:
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
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return isNonNull();
			case 4:
				return isNull();
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 6:
				return validateClassHasNoStringValueInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateDataTypeHasNoPartInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateDataTypeHasOnePartInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return validateDataTypeHasStringValueInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 10:
				return validateInitializesAllClassProperties((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 11:
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
} //ShadowExpImpl
