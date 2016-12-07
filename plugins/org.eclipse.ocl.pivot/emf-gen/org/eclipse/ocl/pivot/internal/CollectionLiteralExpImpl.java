/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.CollectionKind;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionLiteralExpImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionLiteralExpImpl#getOwnedParts <em>Owned Parts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionLiteralExpImpl
		extends LiteralExpImpl
		implements CollectionLiteralExp {

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final CollectionKind KIND_EDEFAULT = CollectionKind.COLLECTION;

	/**
	 * The offset of the flags representing the value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG_OFFSET = 9;

	/**
	 * The flags representing the default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG_DEFAULT = KIND_EDEFAULT.ordinal() << KIND_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link CollectionKind Collection Kind}'
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private static final CollectionKind[] KIND_EFLAG_VALUES = CollectionKind.values();

	/**
	 * The flags representing the value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG = 0x7 << KIND_EFLAG_OFFSET;

	/**
	 * The cached value of the '{@link #getOwnedParts() <em>Owned Parts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParts()
	 * @generated
	 * @ordered
	 */
	protected EList<CollectionLiteralPart> ownedParts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionLiteralExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.COLLECTION_LITERAL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionKind getKind() {
		return KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(CollectionKind newKind) {
		CollectionKind oldKind = KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
		if (newKind == null) newKind = KIND_EDEFAULT;
		eFlags = eFlags & ~KIND_EFLAG | newKind.ordinal() << KIND_EFLAG_OFFSET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COLLECTION_LITERAL_EXP__KIND, oldKind, newKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<CollectionLiteralPart> getOwnedParts()
	{
		if (ownedParts == null)
		{
			ownedParts = new EObjectContainmentEList<CollectionLiteralPart>(CollectionLiteralPart.class, this, PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS);
		}
		return ownedParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCollectionKindIsConcrete(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CollectionKindIsConcrete:
		 *   let
		 *     severity : Integer[1] = 'CollectionLiteralExp::CollectionKindIsConcrete'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = kind <> CollectionKind::Collection
		 *       in
		 *         'CollectionLiteralExp::CollectionKindIsConcrete'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_CollectionLiteralExp_c_c_CollectionKindIsConcrete);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				@SuppressWarnings("null")
				final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionKind kind = this.getKind();
				final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId BOXED_kind = PivotTables.ENUMid_CollectionKind.getEnumerationLiteralId(ClassUtil.nonNullState(kind.getName()));
				final /*@Thrown*/ boolean status = BOXED_kind != PivotTables.ELITid_Collection;
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_CollectionLiteralExp_c_c_CollectionKindIsConcrete, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateSetKindIsSet(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SetKindIsSet:
		 *   let
		 *     severity : Integer[1] = 'CollectionLiteralExp::SetKindIsSet'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[?] = kind = CollectionKind::Set implies
		 *         type.oclIsKindOf(SetType)
		 *       in
		 *         'CollectionLiteralExp::SetKindIsSet'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_CollectionLiteralExp_c_c_SetKindIsSet);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_status;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionKind kind = this.getKind();
					final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId BOXED_kind = PivotTables.ENUMid_CollectionKind.getEnumerationLiteralId(ClassUtil.nonNullState(kind.getName()));
					final /*@Thrown*/ boolean eq = BOXED_kind == PivotTables.ELITid_Set;
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SetType = idResolver.getClass(PivotTables.CLSSid_SetType, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_SetType).booleanValue();
					CAUGHT_oclIsKindOf = oclIsKindOf;
				}
				catch (Exception e) {
					CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_oclIsKindOf);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_CollectionLiteralExp_c_c_SetKindIsSet, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateOrderedSetKindIsOrderedSet(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv OrderedSetKindIsOrderedSet:
		 *   let
		 *     severity : Integer[1] = 'CollectionLiteralExp::OrderedSetKindIsOrderedSet'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[?] = kind = CollectionKind::OrderedSet implies
		 *         type.oclIsKindOf(OrderedSetType)
		 *       in
		 *         'CollectionLiteralExp::OrderedSetKindIsOrderedSet'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_CollectionLiteralExp_c_c_OrderedSetKindIsOrderedSet);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_status;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionKind kind = this.getKind();
					final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId BOXED_kind = PivotTables.ENUMid_CollectionKind.getEnumerationLiteralId(ClassUtil.nonNullState(kind.getName()));
					final /*@Thrown*/ boolean eq = BOXED_kind == PivotTables.ELITid_OrderedSet;
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_OrderedSetType).booleanValue();
					CAUGHT_oclIsKindOf = oclIsKindOf;
				}
				catch (Exception e) {
					CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_oclIsKindOf);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_CollectionLiteralExp_c_c_OrderedSetKindIsOrderedSet, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateSequenceKindIsSequence(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SequenceKindIsSequence:
		 *   let
		 *     severity : Integer[1] = 'CollectionLiteralExp::SequenceKindIsSequence'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[?] = kind = CollectionKind::Sequence implies
		 *         type.oclIsKindOf(SequenceType)
		 *       in
		 *         'CollectionLiteralExp::SequenceKindIsSequence'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_CollectionLiteralExp_c_c_SequenceKindIsSequence);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_status;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionKind kind = this.getKind();
					final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId BOXED_kind = PivotTables.ENUMid_CollectionKind.getEnumerationLiteralId(ClassUtil.nonNullState(kind.getName()));
					final /*@Thrown*/ boolean eq = BOXED_kind == PivotTables.ELITid_Sequence;
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_SequenceType).booleanValue();
					CAUGHT_oclIsKindOf = oclIsKindOf;
				}
				catch (Exception e) {
					CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_oclIsKindOf);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_CollectionLiteralExp_c_c_SequenceKindIsSequence, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateBagKindIsBag(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv BagKindIsBag:
		 *   let
		 *     severity : Integer[1] = 'CollectionLiteralExp::BagKindIsBag'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[?] = kind = CollectionKind::Bag implies
		 *         type.oclIsKindOf(BagType)
		 *       in
		 *         'CollectionLiteralExp::BagKindIsBag'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_CollectionLiteralExp_c_c_BagKindIsBag);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_status;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionKind kind = this.getKind();
					final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@NonNull EnumerationLiteralId BOXED_kind = PivotTables.ENUMid_CollectionKind.getEnumerationLiteralId(ClassUtil.nonNullState(kind.getName()));
					final /*@Thrown*/ boolean eq = BOXED_kind == PivotTables.ELITid_Bag;
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_BagType = idResolver.getClass(PivotTables.CLSSid_BagType, null);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_BagType).booleanValue();
					CAUGHT_oclIsKindOf = oclIsKindOf;
				}
				catch (Exception e) {
					CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_oclIsKindOf);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_CollectionLiteralExp_c_c_BagKindIsBag, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PivotPackage.COLLECTION_LITERAL_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case PivotPackage.COLLECTION_LITERAL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.COLLECTION_LITERAL_EXP__NAME:
				return getName();
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.COLLECTION_LITERAL_EXP__KIND:
				return getKind();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS:
				return getOwnedParts();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PivotPackage.COLLECTION_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__KIND:
				setKind((CollectionKind)newValue);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS:
				getOwnedParts().clear();
				getOwnedParts().addAll((Collection<? extends CollectionLiteralPart>)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PivotPackage.COLLECTION_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS:
				getOwnedParts().clear();
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PivotPackage.COLLECTION_LITERAL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.COLLECTION_LITERAL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.COLLECTION_LITERAL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE:
				return type != null;
			case PivotPackage.COLLECTION_LITERAL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.COLLECTION_LITERAL_EXP__KIND:
				return (eFlags & KIND_EFLAG) != KIND_EFLAG_DEFAULT;
			case PivotPackage.COLLECTION_LITERAL_EXP__OWNED_PARTS:
				return ownedParts != null && !ownedParts.isEmpty();
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
			case PivotPackage.COLLECTION_LITERAL_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.COLLECTION_LITERAL_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.COLLECTION_LITERAL_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.COLLECTION_LITERAL_EXP___VALIDATE_BAG_KIND_IS_BAG__DIAGNOSTICCHAIN_MAP:
				return validateBagKindIsBag((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.COLLECTION_LITERAL_EXP___VALIDATE_COLLECTION_KIND_IS_CONCRETE__DIAGNOSTICCHAIN_MAP:
				return validateCollectionKindIsConcrete((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.COLLECTION_LITERAL_EXP___VALIDATE_ORDERED_SET_KIND_IS_ORDERED_SET__DIAGNOSTICCHAIN_MAP:
				return validateOrderedSetKindIsOrderedSet((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.COLLECTION_LITERAL_EXP___VALIDATE_SEQUENCE_KIND_IS_SEQUENCE__DIAGNOSTICCHAIN_MAP:
				return validateSequenceKindIsSequence((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.COLLECTION_LITERAL_EXP___VALIDATE_SET_KIND_IS_SET__DIAGNOSTICCHAIN_MAP:
				return validateSetKindIsSet((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCollectionLiteralExp(this);
	}
} //CollectionLiteralExpImpl
