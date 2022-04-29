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
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.iterator.SortedByIteration;
import org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.validation.ValidationWarning;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterator Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class IteratorExpImpl extends LoopExpImpl implements IteratorExp
{
	/**
	 * The number of structural features of the '<em>Iterator Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP_FEATURE_COUNT = LoopExpImpl.LOOP_EXP_FEATURE_COUNT + 0;
	/**
	 * The number of operations of the '<em>Iterator Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP_OPERATION_COUNT = LoopExpImpl.LOOP_EXP_OPERATION_COUNT + 21;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratorExpImpl()
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
		return PivotPackage.Literals.ITERATOR_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean validateClosureBodyTypeIsConformanttoIteratorType(DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean validateSortedByIteratorTypeIsComparable(DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		if (getReferredIteration().getImplementation() != SortedByIteration.INSTANCE) {
			return true;
		}
		Diagnostic diagnostic = null;
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(this);
		StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
		try {
			org.eclipse.ocl.pivot.Class oclComparableType = standardLibrary.getOclComparableType();
			CompleteInheritance comparableInheritance = oclComparableType.getInheritance(standardLibrary);
			CompleteInheritance selfType = standardLibrary.getOclSelfType().getInheritance(standardLibrary);
			Operation staticOperation = comparableInheritance.lookupLocalOperation(standardLibrary, LibraryConstants.COMPARE_TO, selfType);
			if (staticOperation == null) {
				if (diagnostics == null) {
					return false;
				}
				diagnostic = new ValidationWarning(PivotMessagesInternal.UnresolvedOperation_ERROR_, String.valueOf(comparableInheritance), LibraryConstants.COMPARE_TO);
			}
			else {
				OCLExpression source2 = this.getOwnedSource();
				OCLExpression body2 = this.getOwnedBody();
				Type sourceType = source2.getType();
				Type bodyType = body2.getType();
				Type specializedBodyType = bodyType != null ? TemplateParameterSubstitutionVisitor.specializeType(bodyType, this, environmentFactory, sourceType, null) : null;
				boolean isOk = false;
				if (bodyType != null) {
					PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
					specializedBodyType = specializedBodyType != null ? specializedBodyType/*.behavioralType()*/ : null;
					if ((specializedBodyType != null) && metamodelManager.conformsTo(specializedBodyType, TemplateParameterSubstitutions.EMPTY, oclComparableType, TemplateParameterSubstitutions.EMPTY)) {
						isOk = true;
					}
				}
				if (!isOk) {
					if (diagnostics == null) {
						return false;
					}
					diagnostic = new ValidationWarning(PivotMessagesInternal.UnresolvedOperation_ERROR_, String.valueOf(specializedBodyType), LibraryConstants.COMPARE_TO);
				}
			}
		} catch (Exception e) {
			if (diagnostics == null) {
				return false;
			}
			diagnostic = new ValidationWarning(e.getLocalizedMessage());
		}
		if (diagnostic == null) {
			return true;
		}
		diagnostics.add(diagnostic);
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUnsafeSourceCanNotBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::UnsafeSourceCanNotBeNull";
		try {
			/**
			 *
			 * inv UnsafeSourceCanNotBeNull:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = not isSafe and
			 *         ownedIterators->exists(isRequired) implies
			 *         let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           if sourceType.oclIsKindOf(MapType)
			 *           then sourceType.oclAsType(MapType).keysAreNullFree
			 *           else sourceType.oclAsType(CollectionType).isNullFree
			 *           endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_and;
					try {
						final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
						final /*@NonInvalid*/ @Nullable Boolean not;
						if (!isSafe) {
							not = ValueUtil.TRUE_VALUE;
						}
						else {
							if (isSafe) {
								not = ValueUtil.FALSE_VALUE;
							}
							else {
								not = null;
							}
						}
						final /*@Thrown*/ @Nullable Boolean and;
						if (not == ValueUtil.FALSE_VALUE) {
							and = ValueUtil.FALSE_VALUE;
						}
						else {
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							/*@Thrown*/ @Nullable Object accumulator = ValueUtil.FALSE_VALUE;
							@NonNull Iterator<Object> ITER__1 = BOXED_ownedIterators.iterator();
							/*@NonInvalid*/ @Nullable Boolean exists;
							while (true) {
								if (!ITER__1.hasNext()) {
									if (accumulator == ValueUtil.FALSE_VALUE) {
										exists = ValueUtil.FALSE_VALUE;
									}
									else {
										throw (InvalidValueException)accumulator;
									}
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ @NonNull Variable _1 = (@NonNull Variable)ITER__1.next();
								/**
								 * isRequired
								 */
								final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
								//
								if (isRequired) {					// Normal successful body evaluation result
									exists = ValueUtil.TRUE_VALUE;
									break;														// Stop immediately
								}
								else if (!isRequired) {				// Normal unsuccessful body evaluation result
									;															// Carry on
								}
								else {															// Impossible badly typed result
									accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "exists");
								}
							}
							if (exists == ValueUtil.FALSE_VALUE) {
								and = ValueUtil.FALSE_VALUE;
							}
							else {
								if ((not == null) || (exists == null)) {
									and = null;
								}
								else {
									and = ValueUtil.TRUE_VALUE;
								}
							}
						}
						CAUGHT_and = and;
					}
					catch (Exception THROWN_and) {
						CAUGHT_and = ValueUtil.createInvalidValue(THROWN_and);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_and == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IF_oclIsKindOf;
						try {
							final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
							/*@Thrown*/ @Nullable Type SAFE_type;
							if (IsEQ2_ == Boolean.TRUE) {
								SAFE_type = null;
							}
							else {
								assert ownedSource != null;
								final /*@Thrown*/ @Nullable Type type = ownedSource.getType();
								SAFE_type = type;
							}
							final /*@Thrown*/ @Nullable Type sourceType = SAFE_type;
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_2 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
							final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, sourceType, TYP_MapType_2).booleanValue();
							/*@Thrown*/ boolean IF_oclIsKindOf;
							if (oclIsKindOf) {
								final /*@Thrown*/ @Nullable MapType oclAsType = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, sourceType, TYP_MapType_2);
								if (oclAsType == null) {
									throw new InvalidValueException("Null source for \'MapType::keysAreNullFree\'");
								}
								final /*@Thrown*/ boolean keysAreNullFree = oclAsType.isKeysAreNullFree();
								IF_oclIsKindOf = keysAreNullFree;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_9 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
								final /*@Thrown*/ @Nullable CollectionType oclAsType_0 = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, sourceType, TYP_CollectionType_9);
								if (oclAsType_0 == null) {
									throw new InvalidValueException("Null source for \'CollectionType::isNullFree\'");
								}
								final /*@Thrown*/ boolean isNullFree = oclAsType_0.isIsNullFree();
								IF_oclIsKindOf = isNullFree;
							}
							CAUGHT_IF_oclIsKindOf = IF_oclIsKindOf;
						}
						catch (Exception THROWN_IF_oclIsKindOf) {
							CAUGHT_IF_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_IF_oclIsKindOf);
						}
						if (CAUGHT_IF_oclIsKindOf == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_and instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_and;
							}
							if (CAUGHT_IF_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_oclIsKindOf;
							}
							if (CAUGHT_and == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateAnyHasOneIterator(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv AnyHasOneIterator: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateAnyTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv AnyTypeIsSourceElementType: true
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
	public boolean validateClosureBodyElementTypeIsIteratorType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::ClosureBodyElementTypeIsIteratorType";
		try {
			/**
			 *
			 * inv ClosureBodyElementTypeIsIteratorType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'closure' implies
			 *         let
			 *           bodyElementType : Type[?] = if
			 *             ownedBody.type.oclIsKindOf(CollectionType)
			 *           then
			 *             ownedBody.type.oclAsType(CollectionType).elementType
			 *           elseif ownedBody.type.oclIsKindOf(MapType)
			 *           then ownedBody.type.oclAsType(MapType).keyType
			 *           else ownedBody.type
			 *           endif
			 *         in
			 *           let iteratorType : Type[?] = ownedIterators->at(1).type
			 *           in bodyElementType?.conformsTo(iteratorType)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_CLOSURE_BODY_ELEMENT_TYPE_IS_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ_ = PivotTables.STR_closure.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ_) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_SAFE_conformsTo;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_5 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull OCLExpression ownedBody = this.getOwnedBody();
							final /*@NonInvalid*/ @Nullable Type type = ownedBody.getType();
							final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_5).booleanValue();
							/*@Thrown*/ @Nullable Type bodyElementType;
							if (oclIsKindOf_0) {
								final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_5);
								if (oclAsType == null) {
									throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
								}
								final /*@Thrown*/ @NonNull Type elementType = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
								bodyElementType = elementType;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_0 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
								final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_MapType_0).booleanValue();
								/*@Thrown*/ @Nullable Type IF_oclIsKindOf;
								if (oclIsKindOf) {
									final /*@Thrown*/ @Nullable MapType oclAsType_0 = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_MapType_0);
									if (oclAsType_0 == null) {
										throw new InvalidValueException("Null source for \'MapType::keyType\'");
									}
									final /*@Thrown*/ @NonNull Type keyType = MapKeyTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType_0);
									IF_oclIsKindOf = keyType;
								}
								else {
									IF_oclIsKindOf = type;
								}
								bodyElementType = IF_oclIsKindOf;
							}
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							final /*@Thrown*/ @Nullable Variable at = (@Nullable Variable)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedIterators, PivotTables.INT_1);
							if (at == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type iteratorType = at.getType();
							/*@Caught*/ @Nullable Object CAUGHT_bodyElementType;
							try {
								CAUGHT_bodyElementType = bodyElementType;
							}
							catch (Exception THROWN_bodyElementType) {
								CAUGHT_bodyElementType = ValueUtil.createInvalidValue(THROWN_bodyElementType);
							}
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = CAUGHT_bodyElementType == null;
							/*@Thrown*/ @Nullable Boolean SAFE_conformsTo;
							if (IsEQ2_ == Boolean.TRUE) {
								SAFE_conformsTo = null;
							}
							else {
								if (bodyElementType == null) {
									throw new InvalidValueException("Null argument");
								}
								final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, bodyElementType, iteratorType).booleanValue();
								SAFE_conformsTo = conformsTo;
							}
							CAUGHT_SAFE_conformsTo = SAFE_conformsTo;
						}
						catch (Exception THROWN_SAFE_conformsTo) {
							CAUGHT_SAFE_conformsTo = ValueUtil.createInvalidValue(THROWN_SAFE_conformsTo);
						}
						if (CAUGHT_SAFE_conformsTo == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_SAFE_conformsTo instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_SAFE_conformsTo;
							}
							if (CAUGHT_SAFE_conformsTo == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateAnyBodyTypeIsBoolean(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv AnyBodyTypeIsBoolean: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateClosureHasOneIterator(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv ClosureHasOneIterator: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateClosureResultElementTypeIsIteratorType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::ClosureResultElementTypeIsIteratorType";
		try {
			/**
			 *
			 * inv ClosureResultElementTypeIsIteratorType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'closure' implies
			 *         let
			 *           resultElementType : Type[1] = type.oclAsType(CollectionType).elementType
			 *         in
			 *           let iteratorType : Type[?] = ownedIterators->at(1).type
			 *           in iteratorType?.conformsTo(resultElementType)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_CLOSURE_RESULT_ELEMENT_TYPE_IS_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ_ = PivotTables.STR_closure.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ_) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_SAFE_conformsTo;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_6 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							final /*@NonInvalid*/ @Nullable Type type = this.getType();
							final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_6);
							if (oclAsType == null) {
								throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
							}
							final /*@Thrown*/ @NonNull Type resultElementType = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							final /*@Thrown*/ @Nullable Variable at = (@Nullable Variable)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedIterators, PivotTables.INT_1);
							if (at == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type iteratorType = at.getType();
							/*@Caught*/ @Nullable Object CAUGHT_iteratorType;
							try {
								CAUGHT_iteratorType = iteratorType;
							}
							catch (Exception THROWN_iteratorType) {
								CAUGHT_iteratorType = ValueUtil.createInvalidValue(THROWN_iteratorType);
							}
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = CAUGHT_iteratorType == null;
							/*@Thrown*/ @Nullable Boolean SAFE_conformsTo;
							if (IsEQ2_ == Boolean.TRUE) {
								SAFE_conformsTo = null;
							}
							else {
								if (iteratorType == null) {
									throw new InvalidValueException("Null argument");
								}
								final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, iteratorType, resultElementType).booleanValue();
								SAFE_conformsTo = conformsTo;
							}
							CAUGHT_SAFE_conformsTo = SAFE_conformsTo;
						}
						catch (Exception THROWN_SAFE_conformsTo) {
							CAUGHT_SAFE_conformsTo = ValueUtil.createInvalidValue(THROWN_SAFE_conformsTo);
						}
						if (CAUGHT_SAFE_conformsTo == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_SAFE_conformsTo instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_SAFE_conformsTo;
							}
							if (CAUGHT_SAFE_conformsTo == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateClosureTypeIsUniqueCollection(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::ClosureTypeIsUniqueCollection";
		try {
			/**
			 *
			 * inv ClosureTypeIsUniqueCollection:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'closure' implies
			 *         if
			 *           ownedSource?.type?.oclIsKindOf(SequenceType) or
			 *           ownedSource?.type.oclIsKindOf(OrderedSetType)
			 *         then type.oclIsKindOf(OrderedSetType)
			 *         else type.oclIsKindOf(SetType)
			 *         endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_CLOSURE_TYPE_IS_UNIQUE_COLLECTION__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ_ = PivotTables.STR_closure.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ_) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IF_or;
						try {
							final /*@NonInvalid*/ @Nullable Type type = this.getType();
							/*@Caught*/ @Nullable Object CAUGHT_SAFE_oclIsKindOf_0;
							try {
								final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
								final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
								/*@Thrown*/ @Nullable Type SAFE_type_0;
								if (IsEQ2_ == Boolean.TRUE) {
									SAFE_type_0 = null;
								}
								else {
									assert ownedSource != null;
									final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
									SAFE_type_0 = type_0;
								}
								/*@Caught*/ @Nullable Object CAUGHT_SAFE_type_0;
								try {
									CAUGHT_SAFE_type_0 = SAFE_type_0;
								}
								catch (Exception THROWN_SAFE_type_0) {
									CAUGHT_SAFE_type_0 = ValueUtil.createInvalidValue(THROWN_SAFE_type_0);
								}
								final /*@NonInvalid*/ @NonNull Object IsEQ2__1 = CAUGHT_SAFE_type_0 == null;
								/*@Thrown*/ @Nullable Boolean SAFE_oclIsKindOf_0;
								if (IsEQ2__1 == Boolean.TRUE) {
									SAFE_oclIsKindOf_0 = null;
								}
								else {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_2 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
									final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_0, TYP_SequenceType_2).booleanValue();
									SAFE_oclIsKindOf_0 = oclIsKindOf_0;
								}
								CAUGHT_SAFE_oclIsKindOf_0 = SAFE_oclIsKindOf_0;
							}
							catch (Exception THROWN_SAFE_oclIsKindOf_0) {
								CAUGHT_SAFE_oclIsKindOf_0 = ValueUtil.createInvalidValue(THROWN_SAFE_oclIsKindOf_0);
							}
							final /*@Thrown*/ @Nullable Boolean or;
							if (CAUGHT_SAFE_oclIsKindOf_0 == ValueUtil.TRUE_VALUE) {
								or = ValueUtil.TRUE_VALUE;
							}
							else {
								/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_1;
								try {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_0 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
									final /*@NonInvalid*/ @Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
									final /*@NonInvalid*/ @NonNull Object IsEQ2__0 = ownedSource_0 == null;
									/*@Thrown*/ @Nullable Type SAFE_type_1;
									if (IsEQ2__0 == Boolean.TRUE) {
										SAFE_type_1 = null;
									}
									else {
										assert ownedSource_0 != null;
										final /*@Thrown*/ @Nullable Type type_1 = ownedSource_0.getType();
										SAFE_type_1 = type_1;
									}
									final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_1, TYP_OrderedSetType_0).booleanValue();
									CAUGHT_oclIsKindOf_1 = oclIsKindOf_1;
								}
								catch (Exception THROWN_oclIsKindOf_1) {
									CAUGHT_oclIsKindOf_1 = ValueUtil.createInvalidValue(THROWN_oclIsKindOf_1);
								}
								if (CAUGHT_oclIsKindOf_1 == ValueUtil.TRUE_VALUE) {
									or = ValueUtil.TRUE_VALUE;
								}
								else {
									if (CAUGHT_SAFE_oclIsKindOf_0 instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_SAFE_oclIsKindOf_0;
									}
									if (CAUGHT_oclIsKindOf_1 instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_oclIsKindOf_1;
									}
									if (CAUGHT_SAFE_oclIsKindOf_0 == null) {
										or = null;
									}
									else {
										or = ValueUtil.FALSE_VALUE;
									}
								}
							}
							if (or == null) {
								throw new InvalidValueException("Null if condition");
							}
							/*@Thrown*/ boolean IF_or;
							if (or) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
								final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_OrderedSetType).booleanValue();
								IF_or = oclIsKindOf_2;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SetType_0 = idResolver.getClass(PivotTables.CLSSid_SetType, null);
								final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_SetType_0).booleanValue();
								IF_or = oclIsKindOf;
							}
							CAUGHT_IF_or = IF_or;
						}
						catch (Exception THROWN_IF_or) {
							CAUGHT_IF_or = ValueUtil.createInvalidValue(THROWN_IF_or);
						}
						if (CAUGHT_IF_or == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IF_or instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_or;
							}
							result = ValueUtil.FALSE_VALUE;
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateCollectElementTypeIsFlattenedBodyType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::CollectElementTypeIsFlattenedBodyType";
		try {
			/**
			 *
			 * inv CollectElementTypeIsFlattenedBodyType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'collect' implies
			 *         type.oclAsType(CollectionType).elementType =
			 *         ownedBody.type?.flattenedType()
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_COLLECT_ELEMENT_TYPE_IS_FLATTENED_BODY_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ__0 = PivotTables.STR_collect.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ__0) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IsEQ_;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_3 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							final /*@NonInvalid*/ @Nullable Type type_0 = this.getType();
							final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type_0, TYP_CollectionType_3);
							if (oclAsType == null) {
								throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
							}
							final /*@Thrown*/ @NonNull Type elementType = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull OCLExpression ownedBody = this.getOwnedBody();
							final /*@NonInvalid*/ @Nullable Type type = ownedBody.getType();
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = type == null;
							/*@Thrown*/ @Nullable Type SAFE_flattenedType;
							if (IsEQ2_ == Boolean.TRUE) {
								SAFE_flattenedType = null;
							}
							else {
								assert type != null;
								@SuppressWarnings("null")
								final /*@Thrown*/ @NonNull Type flattenedType = type.flattenedType();
								SAFE_flattenedType = flattenedType;
							}
							final /*@Thrown*/ boolean IsEQ_ = (SAFE_flattenedType != null) ? (elementType.getTypeId() == SAFE_flattenedType.getTypeId()) : false;
							CAUGHT_IsEQ_ = IsEQ_;
						}
						catch (Exception THROWN_IsEQ_) {
							CAUGHT_IsEQ_ = ValueUtil.createInvalidValue(THROWN_IsEQ_);
						}
						if (CAUGHT_IsEQ_ == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IsEQ_ instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IsEQ_;
							}
							result = ValueUtil.FALSE_VALUE;
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateClosureSourceElementTypeIsBodyElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv ClosureSourceElementTypeIsBodyElementType: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateClosureElementTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv ClosureElementTypeIsSourceElementType: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCollectTypeIsUnordered(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::CollectTypeIsUnordered";
		try {
			/**
			 *
			 * inv CollectTypeIsUnordered:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'collect' implies
			 *         if
			 *           ownedSource?.type.oclIsKindOf(SequenceType) or
			 *           ownedSource?.type.oclIsKindOf(OrderedSetType)
			 *         then type.oclIsKindOf(SequenceType)
			 *         else type.oclIsKindOf(BagType)
			 *         endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_COLLECT_TYPE_IS_UNORDERED__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ_ = PivotTables.STR_collect.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ_) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IF_or;
						try {
							final /*@NonInvalid*/ @Nullable Type type = this.getType();
							/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_2;
							try {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_5 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
								final /*@NonInvalid*/ @Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
								final /*@NonInvalid*/ @NonNull Object IsEQ2__0 = ownedSource_0 == null;
								/*@Thrown*/ @Nullable Type SAFE_type_1;
								if (IsEQ2__0 == Boolean.TRUE) {
									SAFE_type_1 = null;
								}
								else {
									assert ownedSource_0 != null;
									final /*@Thrown*/ @Nullable Type type_1 = ownedSource_0.getType();
									SAFE_type_1 = type_1;
								}
								final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_1, TYP_SequenceType_5).booleanValue();
								CAUGHT_oclIsKindOf_2 = oclIsKindOf_2;
							}
							catch (Exception THROWN_oclIsKindOf_2) {
								CAUGHT_oclIsKindOf_2 = ValueUtil.createInvalidValue(THROWN_oclIsKindOf_2);
							}
							final /*@Thrown*/ @Nullable Boolean or;
							if (CAUGHT_oclIsKindOf_2 == ValueUtil.TRUE_VALUE) {
								or = ValueUtil.TRUE_VALUE;
							}
							else {
								/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_1;
								try {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_3 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
									final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
									final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
									/*@Thrown*/ @Nullable Type SAFE_type_0;
									if (IsEQ2_ == Boolean.TRUE) {
										SAFE_type_0 = null;
									}
									else {
										assert ownedSource != null;
										final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
										SAFE_type_0 = type_0;
									}
									final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_0, TYP_OrderedSetType_3).booleanValue();
									CAUGHT_oclIsKindOf_1 = oclIsKindOf_1;
								}
								catch (Exception THROWN_oclIsKindOf_1) {
									CAUGHT_oclIsKindOf_1 = ValueUtil.createInvalidValue(THROWN_oclIsKindOf_1);
								}
								if (CAUGHT_oclIsKindOf_1 == ValueUtil.TRUE_VALUE) {
									or = ValueUtil.TRUE_VALUE;
								}
								else {
									if (CAUGHT_oclIsKindOf_2 instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_oclIsKindOf_2;
									}
									if (CAUGHT_oclIsKindOf_1 instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_oclIsKindOf_1;
									}
									or = ValueUtil.FALSE_VALUE;
								}
							}
							if (or == null) {
								throw new InvalidValueException("Null if condition");
							}
							/*@Thrown*/ boolean IF_or;
							if (or) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_0 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
								final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_SequenceType_0).booleanValue();
								IF_or = oclIsKindOf;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_BagType_2 = idResolver.getClass(PivotTables.CLSSid_BagType, null);
								final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_BagType_2).booleanValue();
								IF_or = oclIsKindOf_0;
							}
							CAUGHT_IF_or = IF_or;
						}
						catch (Exception THROWN_IF_or) {
							CAUGHT_IF_or = ValueUtil.createInvalidValue(THROWN_IF_or);
						}
						if (CAUGHT_IF_or == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IF_or instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_or;
							}
							result = ValueUtil.FALSE_VALUE;
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateSortedByIsOrderedIfSourceIsOrdered(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::SortedByIsOrderedIfSourceIsOrdered";
		try {
			/**
			 *
			 * inv SortedByIsOrderedIfSourceIsOrdered:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'sortedBy' implies
			 *         if
			 *           ownedSource?.type.oclIsKindOf(SequenceType) or
			 *           ownedSource?.type.oclIsKindOf(BagType)
			 *         then type.oclIsKindOf(SequenceType)
			 *         else type.oclIsKindOf(OrderedSetType)
			 *         endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SORTED_BY_IS_ORDERED_IF_SOURCE_IS_ORDERED__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ_ = PivotTables.STR_sortedBy.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ_) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IF_or;
						try {
							final /*@NonInvalid*/ @Nullable Type type = this.getType();
							/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
							try {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_3 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
								final /*@NonInvalid*/ @Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
								final /*@NonInvalid*/ @NonNull Object IsEQ2__0 = ownedSource_0 == null;
								/*@Thrown*/ @Nullable Type SAFE_type_1;
								if (IsEQ2__0 == Boolean.TRUE) {
									SAFE_type_1 = null;
								}
								else {
									assert ownedSource_0 != null;
									final /*@Thrown*/ @Nullable Type type_1 = ownedSource_0.getType();
									SAFE_type_1 = type_1;
								}
								final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_1, TYP_SequenceType_3).booleanValue();
								CAUGHT_oclIsKindOf = oclIsKindOf;
							}
							catch (Exception THROWN_oclIsKindOf) {
								CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_oclIsKindOf);
							}
							final /*@Thrown*/ @Nullable Boolean or;
							if (CAUGHT_oclIsKindOf == ValueUtil.TRUE_VALUE) {
								or = ValueUtil.TRUE_VALUE;
							}
							else {
								/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_0;
								try {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_BagType = idResolver.getClass(PivotTables.CLSSid_BagType, null);
									final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
									final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
									/*@Thrown*/ @Nullable Type SAFE_type_0;
									if (IsEQ2_ == Boolean.TRUE) {
										SAFE_type_0 = null;
									}
									else {
										assert ownedSource != null;
										final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
										SAFE_type_0 = type_0;
									}
									final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, SAFE_type_0, TYP_BagType).booleanValue();
									CAUGHT_oclIsKindOf_0 = oclIsKindOf_0;
								}
								catch (Exception THROWN_oclIsKindOf_0) {
									CAUGHT_oclIsKindOf_0 = ValueUtil.createInvalidValue(THROWN_oclIsKindOf_0);
								}
								if (CAUGHT_oclIsKindOf_0 == ValueUtil.TRUE_VALUE) {
									or = ValueUtil.TRUE_VALUE;
								}
								else {
									if (CAUGHT_oclIsKindOf instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_oclIsKindOf;
									}
									if (CAUGHT_oclIsKindOf_0 instanceof InvalidValueException) {
										throw (InvalidValueException)CAUGHT_oclIsKindOf_0;
									}
									or = ValueUtil.FALSE_VALUE;
								}
							}
							if (or == null) {
								throw new InvalidValueException("Null if condition");
							}
							/*@Thrown*/ boolean IF_or;
							if (or) {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
								final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_SequenceType).booleanValue();
								IF_or = oclIsKindOf_2;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_2 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
								final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_OrderedSetType_2).booleanValue();
								IF_or = oclIsKindOf_1;
							}
							CAUGHT_IF_or = IF_or;
						}
						catch (Exception THROWN_IF_or) {
							CAUGHT_IF_or = ValueUtil.createInvalidValue(THROWN_IF_or);
						}
						if (CAUGHT_IF_or == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IF_or instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_or;
							}
							result = ValueUtil.FALSE_VALUE;
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateSortedByElementTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::SortedByElementTypeIsSourceElementType";
		try {
			/**
			 *
			 * inv SortedByElementTypeIsSourceElementType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = name = 'sortedBy' implies
			 *         type.oclAsType(CollectionType).elementType =
			 *         ownedSource?.type.oclAsType(CollectionType).elementType
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SORTED_BY_ELEMENT_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @Nullable String name = this.getName();
					final /*@NonInvalid*/ boolean IsEQ__0 = PivotTables.STR_sortedBy.equals(name);
					final /*@Thrown*/ @Nullable Boolean result;
					if (!IsEQ__0) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IsEQ_;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_8 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							final /*@NonInvalid*/ @Nullable Type type = this.getType();
							final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_8);
							if (oclAsType == null) {
								throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
							}
							final /*@Thrown*/ @NonNull Type elementType_0 = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
							final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
							/*@Thrown*/ @Nullable Type SAFE_type_0;
							if (IsEQ2_ == Boolean.TRUE) {
								SAFE_type_0 = null;
							}
							else {
								assert ownedSource != null;
								final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
								SAFE_type_0 = type_0;
							}
							final /*@Thrown*/ @Nullable CollectionType oclAsType_0 = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, SAFE_type_0, TYP_CollectionType_8);
							if (oclAsType_0 == null) {
								throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
							}
							final /*@Thrown*/ @NonNull Type elementType = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType_0);
							final /*@Thrown*/ boolean IsEQ_ = elementType_0.getTypeId() == elementType.getTypeId();
							CAUGHT_IsEQ_ = IsEQ_;
						}
						catch (Exception THROWN_IsEQ_) {
							CAUGHT_IsEQ_ = ValueUtil.createInvalidValue(THROWN_IsEQ_);
						}
						if (CAUGHT_IsEQ_ == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IsEQ_ instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IsEQ_;
							}
							result = ValueUtil.FALSE_VALUE;
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateIteratorTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::IteratorTypeIsSourceElementType";
		try {
			/**
			 *
			 * inv IteratorTypeIsSourceElementType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[?] = let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           sourceType.oclIsKindOf(CollectionType) implies
			 *           let
			 *             sourceElementType : Type[1] = sourceType.oclAsType(CollectionType).elementType
			 *           in
			 *             self.ownedIterators->forAll(p |
			 *               sourceElementType.conformsTo(p.type))
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_ITERATOR_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_sourceType;
					try {
						final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
						/*@Thrown*/ @Nullable Type SAFE_type;
						if (IsEQ2_ == Boolean.TRUE) {
							SAFE_type = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ @Nullable Type type = ownedSource.getType();
							SAFE_type = type;
						}
						final /*@Thrown*/ @Nullable Type sourceType = SAFE_type;
						CAUGHT_sourceType = sourceType;
					}
					catch (Exception THROWN_sourceType) {
						CAUGHT_sourceType = ValueUtil.createInvalidValue(THROWN_sourceType);
					}
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_11 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
						if (CAUGHT_sourceType instanceof InvalidValueException) {
							throw (InvalidValueException)CAUGHT_sourceType;
						}
						final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, CAUGHT_sourceType, TYP_CollectionType_11).booleanValue();
						CAUGHT_oclIsKindOf = oclIsKindOf;
					}
					catch (Exception THROWN_oclIsKindOf) {
						CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_oclIsKindOf);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_oclIsKindOf == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_forAll;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
							if (CAUGHT_sourceType instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_sourceType;
							}
							final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, CAUGHT_sourceType, TYP_CollectionType);
							if (oclAsType == null) {
								throw new InvalidValueException("Null source for \'CollectionType::elementType\'");
							}
							final /*@Thrown*/ @NonNull Type sourceElementType = CollectionElementTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							/*@Thrown*/ @Nullable Object accumulator = ValueUtil.TRUE_VALUE;
							@NonNull Iterator<Object> ITER_p = BOXED_ownedIterators.iterator();
							/*@Thrown*/ @Nullable Boolean forAll;
							while (true) {
								if (!ITER_p.hasNext()) {
									if (accumulator == ValueUtil.TRUE_VALUE) {
										forAll = ValueUtil.TRUE_VALUE;
									}
									else {
										throw (InvalidValueException)accumulator;
									}
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ @NonNull Variable p = (@NonNull Variable)ITER_p.next();
								/**
								 * sourceElementType.conformsTo(p.type)
								 */
								/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
								try {
									final /*@NonInvalid*/ @Nullable Type type_0 = p.getType();
									final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, sourceElementType, type_0).booleanValue();
									CAUGHT_conformsTo = conformsTo;
								}
								catch (Exception THROWN_conformsTo) {
									CAUGHT_conformsTo = ValueUtil.createInvalidValue(THROWN_conformsTo);
								}
								//
								if (CAUGHT_conformsTo == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
									forAll = ValueUtil.FALSE_VALUE;
									break;														// Stop immediately
								}
								else if (CAUGHT_conformsTo == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
									;															// Carry on
								}
								else if (CAUGHT_conformsTo instanceof InvalidValueException) {		// Abnormal exception evaluation result
									accumulator = CAUGHT_conformsTo;									// Cache an exception failure
								}
								else {															// Impossible badly typed result
									accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
								}
							}
							CAUGHT_forAll = forAll;
						}
						catch (Exception THROWN_forAll) {
							CAUGHT_forAll = ValueUtil.createInvalidValue(THROWN_forAll);
						}
						if (CAUGHT_forAll == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_oclIsKindOf;
							}
							if (CAUGHT_forAll instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_forAll;
							}
							if (CAUGHT_forAll == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateIteratorTypeIsSourceKeyType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::IteratorTypeIsSourceKeyType";
		try {
			/**
			 *
			 * inv IteratorTypeIsSourceKeyType:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let
			 *         result : Boolean[?] = let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           sourceType.oclIsKindOf(MapType) implies
			 *           let
			 *             sourceKeyType : Type[1] = sourceType.oclAsType(MapType).keyType
			 *           in
			 *             self.ownedIterators->forAll(p |
			 *               sourceKeyType.conformsTo(p.type))
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_ITERATOR_TYPE_IS_SOURCE_KEY_TYPE__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_sourceType;
					try {
						final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
						/*@Thrown*/ @Nullable Type SAFE_type;
						if (IsEQ2_ == Boolean.TRUE) {
							SAFE_type = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ @Nullable Type type = ownedSource.getType();
							SAFE_type = type;
						}
						final /*@Thrown*/ @Nullable Type sourceType = SAFE_type;
						CAUGHT_sourceType = sourceType;
					}
					catch (Exception THROWN_sourceType) {
						CAUGHT_sourceType = ValueUtil.createInvalidValue(THROWN_sourceType);
					}
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_4 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
						if (CAUGHT_sourceType instanceof InvalidValueException) {
							throw (InvalidValueException)CAUGHT_sourceType;
						}
						final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, CAUGHT_sourceType, TYP_MapType_4).booleanValue();
						CAUGHT_oclIsKindOf = oclIsKindOf;
					}
					catch (Exception THROWN_oclIsKindOf) {
						CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_oclIsKindOf);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_oclIsKindOf == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_forAll;
						try {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_10 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
							if (CAUGHT_sourceType instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_sourceType;
							}
							final /*@Thrown*/ @Nullable MapType oclAsType = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, CAUGHT_sourceType, TYP_MapType_10);
							if (oclAsType == null) {
								throw new InvalidValueException("Null source for \'MapType::keyType\'");
							}
							final /*@Thrown*/ @NonNull Type sourceKeyType = MapKeyTypeProperty.INSTANCE.evaluate(executor, PivotTables.CLSSid_Type, oclAsType);
							@SuppressWarnings("null")
							final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							/*@Thrown*/ @Nullable Object accumulator = ValueUtil.TRUE_VALUE;
							@NonNull Iterator<Object> ITER_p = BOXED_ownedIterators.iterator();
							/*@Thrown*/ @Nullable Boolean forAll;
							while (true) {
								if (!ITER_p.hasNext()) {
									if (accumulator == ValueUtil.TRUE_VALUE) {
										forAll = ValueUtil.TRUE_VALUE;
									}
									else {
										throw (InvalidValueException)accumulator;
									}
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ @NonNull Variable p = (@NonNull Variable)ITER_p.next();
								/**
								 * sourceKeyType.conformsTo(p.type)
								 */
								/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
								try {
									final /*@NonInvalid*/ @Nullable Type type_0 = p.getType();
									final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, sourceKeyType, type_0).booleanValue();
									CAUGHT_conformsTo = conformsTo;
								}
								catch (Exception THROWN_conformsTo) {
									CAUGHT_conformsTo = ValueUtil.createInvalidValue(THROWN_conformsTo);
								}
								//
								if (CAUGHT_conformsTo == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
									forAll = ValueUtil.FALSE_VALUE;
									break;														// Stop immediately
								}
								else if (CAUGHT_conformsTo == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
									;															// Carry on
								}
								else if (CAUGHT_conformsTo instanceof InvalidValueException) {		// Abnormal exception evaluation result
									accumulator = CAUGHT_conformsTo;									// Cache an exception failure
								}
								else {															// Impossible badly typed result
									accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
								}
							}
							CAUGHT_forAll = forAll;
						}
						catch (Exception THROWN_forAll) {
							CAUGHT_forAll = ValueUtil.createInvalidValue(THROWN_forAll);
						}
						if (CAUGHT_forAll == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_oclIsKindOf;
							}
							if (CAUGHT_forAll instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_forAll;
							}
							if (CAUGHT_forAll == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateSafeIteratorIsRequired(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::SafeIteratorIsRequired";
		try {
			/**
			 *
			 * inv SafeIteratorIsRequired:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = isSafe implies
			 *         ownedIterators->forAll(isRequired)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
					final /*@Thrown*/ @Nullable Boolean result;
					if (!isSafe) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						@SuppressWarnings("null")
						final /*@NonInvalid*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
						final /*@NonInvalid*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
						/*@Thrown*/ @Nullable Object accumulator = ValueUtil.TRUE_VALUE;
						@NonNull Iterator<Object> ITER__1 = BOXED_ownedIterators.iterator();
						/*@NonInvalid*/ @Nullable Boolean forAll;
						while (true) {
							if (!ITER__1.hasNext()) {
								if (accumulator == ValueUtil.TRUE_VALUE) {
									forAll = ValueUtil.TRUE_VALUE;
								}
								else {
									throw (InvalidValueException)accumulator;
								}
								break;
							}
							@SuppressWarnings("null")
							/*@NonInvalid*/ @NonNull Variable _1 = (@NonNull Variable)ITER__1.next();
							/**
							 * isRequired
							 */
							final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
							//
							if (!isRequired) {					// Normal unsuccessful body evaluation result
								forAll = ValueUtil.FALSE_VALUE;
								break;														// Stop immediately
							}
							else if (isRequired) {				// Normal successful body evaluation result
								;															// Carry on
							}
							else {															// Impossible badly typed result
								accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
							}
						}
						if (forAll == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (forAll == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IteratorExp::SafeSourceCanBeNull";
		try {
			/**
			 *
			 * inv SafeSourceCanBeNull:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = isSafe implies
			 *         not let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           if sourceType.oclIsKindOf(MapType)
			 *           then sourceType.oclAsType(MapType).keysAreNullFree
			 *           else sourceType.oclAsType(CollectionType).isNullFree
			 *           endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATOR_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
					final /*@Thrown*/ @Nullable Boolean result;
					if (!isSafe) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_not;
						try {
							/*@Caught*/ @NonNull Object CAUGHT_IF_oclIsKindOf;
							try {
								final /*@NonInvalid*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
								final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedSource == null;
								/*@Thrown*/ @Nullable Type SAFE_type;
								if (IsEQ2_ == Boolean.TRUE) {
									SAFE_type = null;
								}
								else {
									assert ownedSource != null;
									final /*@Thrown*/ @Nullable Type type = ownedSource.getType();
									SAFE_type = type;
								}
								final /*@Thrown*/ @Nullable Type sourceType = SAFE_type;
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType = idResolver.getClass(PivotTables.CLSSid_MapType, null);
								final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, sourceType, TYP_MapType).booleanValue();
								/*@Thrown*/ boolean IF_oclIsKindOf;
								if (oclIsKindOf) {
									final /*@Thrown*/ @Nullable MapType oclAsType_0 = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, sourceType, TYP_MapType);
									if (oclAsType_0 == null) {
										throw new InvalidValueException("Null source for \'MapType::keysAreNullFree\'");
									}
									final /*@Thrown*/ boolean keysAreNullFree = oclAsType_0.isKeysAreNullFree();
									IF_oclIsKindOf = keysAreNullFree;
								}
								else {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_1 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
									final /*@Thrown*/ @Nullable CollectionType oclAsType = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, sourceType, TYP_CollectionType_1);
									if (oclAsType == null) {
										throw new InvalidValueException("Null source for \'CollectionType::isNullFree\'");
									}
									final /*@Thrown*/ boolean isNullFree = oclAsType.isIsNullFree();
									IF_oclIsKindOf = isNullFree;
								}
								CAUGHT_IF_oclIsKindOf = IF_oclIsKindOf;
							}
							catch (Exception THROWN_IF_oclIsKindOf) {
								CAUGHT_IF_oclIsKindOf = ValueUtil.createInvalidValue(THROWN_IF_oclIsKindOf);
							}
							if (CAUGHT_IF_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_oclIsKindOf;
							}
							final /*@Thrown*/ @Nullable Boolean not;
							if (CAUGHT_IF_oclIsKindOf == ValueUtil.FALSE_VALUE) {
								not = ValueUtil.TRUE_VALUE;
							}
							else {
								if (CAUGHT_IF_oclIsKindOf == ValueUtil.TRUE_VALUE) {
									not = ValueUtil.FALSE_VALUE;
								}
								else {
									not = null;
								}
							}
							CAUGHT_not = not;
						}
						catch (Exception THROWN_not) {
							CAUGHT_not = ValueUtil.createInvalidValue(THROWN_not);
						}
						if (CAUGHT_not == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_not instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not;
							}
							if (CAUGHT_not == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception THROWN_result) {
					CAUGHT_result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
			case 32:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateSafeSourceCannotBeMap((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return validateMatchingMapCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 10:
				return validateMatchingOrderedCollectionCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 11:
				return validateNoCoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 12:
				return validateNoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 13:
				return validateNoNotOrderedCollectionCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 14:
				return validateSourceIsCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 15:
				return validateSourceIsIterable((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 16:
				return getReferredElement();
			case 17:
				return validateAnyBodyTypeIsBoolean((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 18:
				return validateAnyHasOneIterator((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 19:
				return validateAnyTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 20:
				return validateClosureBodyElementTypeIsIteratorType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 21:
				return validateClosureBodyTypeIsConformanttoIteratorType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 22:
				return validateClosureElementTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 23:
				return validateClosureHasOneIterator((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 24:
				return validateClosureResultElementTypeIsIteratorType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 25:
				return validateClosureSourceElementTypeIsBodyElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 26:
				return validateClosureTypeIsUniqueCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 27:
				return validateCollectElementTypeIsFlattenedBodyType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 28:
				return validateCollectTypeIsUnordered((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 29:
				return validateIteratorTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 30:
				return validateIteratorTypeIsSourceKeyType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 31:
				return validateSafeIteratorIsRequired((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 33:
				return validateSortedByElementTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 34:
				return validateSortedByIsOrderedIfSourceIsOrdered((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 35:
				return validateSortedByIteratorTypeIsComparable((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 36:
				return validateUnsafeSourceCanNotBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitIteratorExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		return getReferredIteration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass)
	{
		if (baseClass == CallExp.class)
		{
			switch (baseOperationID)
			{
				case 6: return 32;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case 0: return 16;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

} //IteratorExpImpl
