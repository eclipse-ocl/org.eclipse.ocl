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
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.iterator.ClosureIteration;
import org.eclipse.ocl.pivot.library.iterator.SortedByIteration;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.validation.ValidationWarning;
import org.eclipse.ocl.pivot.values.InvalidValueException;
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
		if (getReferredIteration().getImplementation() != ClosureIteration.INSTANCE) {
			return true;
		}
		Diagnostic diagnostic = null;
		Resource asResource = ClassUtil.nonNullState(eResource());
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(asResource);
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Type bodyType = getOwnedBody().getType();
		if (bodyType instanceof CollectionType) {
			bodyType = ((CollectionType)bodyType).getElementType();
		}
		Type bodyType2 = ClassUtil.nonNullState(bodyType);
		Type iteratorType = ClassUtil.nonNullState(getOwnedIterators().get(0).getType());
		//		TemplateParameterSubstitutions bindings = null; //new HashMap<TemplateParameter, Type>();
		if (!metamodelManager.conformsTo(bodyType2, TemplateParameterSubstitutions.EMPTY, iteratorType, TemplateParameterSubstitutions.EMPTY)) {
			if (diagnostics == null) {
				return false;
			}
			diagnostic = new ValidationWarning(PivotMessagesInternal.IncompatibleBodyType_WARNING_, bodyType2, iteratorType);
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
	 * @generated NOT
	 */
	@Override
	public boolean validateSortedByIteratorTypeIsComparable(DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		if (getReferredIteration().getImplementation() != SortedByIteration.INSTANCE) {
			return true;
		}
		Diagnostic diagnostic = null;
		Resource asResource = ClassUtil.nonNullState(eResource());
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(asResource);
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
				Type sourceTypeValue = source2.getTypeValue();
				Type bodyType = body2.getType();
				Type specializedBodyType = bodyType != null ? TemplateParameterSubstitutionVisitor.specializeType(bodyType, this, environmentFactory, sourceType, sourceTypeValue) : null;
				boolean isOk = false;
				if (bodyType != null) {
					PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
					specializedBodyType = specializedBodyType != null ? PivotUtilInternal.getBehavioralType(specializedBodyType) : null;
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
		/**
		 *
		 * inv UnsafeSourceCanNotBeNull:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::UnsafeSourceCanNotBeNull'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = not isSafe and
		 *         ownedIterators->exists(isRequired) implies
		 *         ownedSource?.type.oclAsType(CollectionType).isNullFree
		 *       in
		 *         'IteratorExp::UnsafeSourceCanNotBeNull'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_UnsafeSourceCanNotBeNull);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_and;
				try {
					final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
					final /*@NonInvalid*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(isSafe);
					final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
					/*@Thrown*/ java.lang.@Nullable Object accumulator = ValueUtil.FALSE_VALUE;
					@NonNull Iterator<Object> ITERATOR__1 = ValueUtil.lazyIterator(BOXED_ownedIterators);
					/*@NonInvalid*/ boolean exists;
					while (true) {
						if (!ITERATOR__1.hasNext()) {
							if (accumulator == ValueUtil.FALSE_VALUE) {
								exists = ValueUtil.FALSE_VALUE;
							}
							else {
								throw (InvalidValueException)accumulator;
							}
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Variable _1 = (Variable)ITERATOR__1.next();
						/**
						 * isRequired
						 */
						final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
						//
						if (isRequired == ValueUtil.TRUE_VALUE) {					// Normal successful body evaluation result
							exists = ValueUtil.TRUE_VALUE;
							break;														// Stop immediately
						}
						else if (isRequired == ValueUtil.FALSE_VALUE) {				// Normal unsuccessful body evaluation result
							;															// Carry on
						}
						else {															// Impossible badly typed result
							accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "exists");
						}
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(not, exists);
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_isNullFree;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_13 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
						safe_type_source = type;
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_13));
					final /*@Thrown*/ boolean isNullFree = oclAsType.isIsNullFree();
					CAUGHT_isNullFree = isNullFree;
				}
				catch (Exception e) {
					CAUGHT_isNullFree = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_isNullFree);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_UnsafeSourceCanNotBeNull, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
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
		 *
		 * inv AnyHasOneIterator:
		 *   let severity : Integer[1] = 'IteratorExp::AnyHasOneIterator'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'any' implies
		 *         ownedIterators->size() = 1
		 *       in
		 *         'IteratorExp::AnyHasOneIterator'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_AnyHasOneIterator);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
			final /*@NonInvalid*/ boolean eq = PivotTables.STR_any.equals(name);
			/*@NonInvalid*/ boolean result;
			if (eq) {
				final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedIterators);
				final /*@NonInvalid*/ boolean eq_0 = size.equals(PivotTables.INT_1);
				result = eq_0;
			}
			else {
				result = ValueUtil.TRUE_VALUE;
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_AnyHasOneIterator, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
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
	public boolean validateAnyTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv AnyTypeIsSourceElementType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::AnyTypeIsSourceElementType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'any' implies type =
		 *         ownedSource?.type?.oclAsType(CollectionType)?.elementType
		 *       in
		 *         'IteratorExp::AnyTypeIsSourceElementType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_AnyTypeIsSourceElementType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_3;
		if (le) {
			symbol_3 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_any.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedSource.getType();
						safe_type_source = type_0;
					}
					/*@Caught*/ @Nullable Object CAUGHT_safe_type_source;
					try {
						CAUGHT_safe_type_source = safe_type_source;
					}
					catch (Exception e) {
						CAUGHT_safe_type_source = ValueUtil.createInvalidValue(e);
					}
					final /*@NonInvalid*/ @NonNull Object symbol_1 = CAUGHT_safe_type_source == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable CollectionType safe_oclAsType_source;
					if (symbol_1 == Boolean.TRUE) {
						safe_oclAsType_source = null;
					}
					else {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_1 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_1));
						safe_oclAsType_source = oclAsType;
					}
					/*@Caught*/ @Nullable Object CAUGHT_safe_oclAsType_source;
					try {
						CAUGHT_safe_oclAsType_source = safe_oclAsType_source;
					}
					catch (Exception e) {
						CAUGHT_safe_oclAsType_source = ValueUtil.createInvalidValue(e);
					}
					final /*@NonInvalid*/ @NonNull Object symbol_2 = CAUGHT_safe_oclAsType_source == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_elementType_source;
					if (symbol_2 == Boolean.TRUE) {
						safe_elementType_source = null;
					}
					else {
						assert safe_oclAsType_source != null;
						@SuppressWarnings("null")
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = safe_oclAsType_source.getElementType();
						safe_elementType_source = elementType;
					}
					final /*@Thrown*/ boolean eq_0 = (type != null) && (safe_elementType_source != null) ? (type.getTypeId() == safe_elementType_source.getTypeId()) : false;
					result = eq_0;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_AnyTypeIsSourceElementType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_3 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_3;
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
		/**
		 *
		 * inv ClosureBodyElementTypeIsIteratorType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::ClosureBodyElementTypeIsIteratorType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'closure' implies
		 *         let
		 *           bodyElementType : Type[1] = ownedBody.type.oclAsType(CollectionType).elementType
		 *         in
		 *           let iteratorType : Type[?] = ownedIterators->at(1).type
		 *           in bodyElementType.conformsTo(iteratorType)
		 *       in
		 *         'IteratorExp::ClosureBodyElementTypeIsIteratorType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_ClosureBodyElementTypeIsIteratorType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_closure.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_2 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					@SuppressWarnings("null")
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedBody = this.getOwnedBody();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedBody.getType();
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_2));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type bodyElementType = oclAsType.getElementType();
					final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Variable at = (Variable)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedIterators, PivotTables.INT_1);
					if (at == null) {
						throw new InvalidValueException("Null source for \'TypedElement::type\'");
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type iteratorType = at.getType();
					final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, bodyElementType, iteratorType).booleanValue();
					result = conformsTo;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_ClosureBodyElementTypeIsIteratorType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateAnyBodyTypeIsBoolean(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv AnyBodyTypeIsBoolean:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::AnyBodyTypeIsBoolean'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'any' implies ownedBody.type = 'Boolean'
		 *       in
		 *         'IteratorExp::AnyBodyTypeIsBoolean'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_AnyBodyTypeIsBoolean);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
			final /*@NonInvalid*/ boolean eq = PivotTables.STR_any.equals(name);
			/*@NonInvalid*/ boolean result;
			if (eq) {
				@SuppressWarnings("null")
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedBody = this.getOwnedBody();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedBody.getType();
				final /*@NonInvalid*/ boolean eq_0 = PivotTables.STR_Boolean.equals(type);
				result = eq_0;
			}
			else {
				result = ValueUtil.TRUE_VALUE;
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_AnyBodyTypeIsBoolean, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
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
	public boolean validateClosureHasOneIterator(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv ClosureHasOneIterator:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::ClosureHasOneIterator'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'closure' implies
		 *         ownedIterators->size() = 1
		 *       in
		 *         'IteratorExp::ClosureHasOneIterator'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_ClosureHasOneIterator);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
			final /*@NonInvalid*/ boolean eq = PivotTables.STR_closure.equals(name);
			/*@NonInvalid*/ boolean result;
			if (eq) {
				final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedIterators);
				final /*@NonInvalid*/ boolean eq_0 = size.equals(PivotTables.INT_1);
				result = eq_0;
			}
			else {
				result = ValueUtil.TRUE_VALUE;
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_ClosureHasOneIterator, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
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
	public boolean validateClosureTypeIsUniqueCollection(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv ClosureTypeIsUniqueCollection:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::ClosureTypeIsUniqueCollection'.getSeverity()
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
		 *         'IteratorExp::ClosureTypeIsUniqueCollection'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_ClosureTypeIsUniqueCollection);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_4;
		if (le) {
			symbol_4 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_closure.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_2 = this.getType();
					/*@Caught*/ @Nullable Object CAUGHT_safe_oclIsKindOf_source;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_type_source = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
							safe_type_source = type;
						}
						/*@Caught*/ @Nullable Object CAUGHT_safe_type_source;
						try {
							CAUGHT_safe_type_source = safe_type_source;
						}
						catch (Exception e) {
							CAUGHT_safe_type_source = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_1 = CAUGHT_safe_type_source == null;
						/*@Thrown*/ java.lang.@Nullable Boolean safe_oclIsKindOf_source;
						if (symbol_1 == Boolean.TRUE) {
							safe_oclIsKindOf_source = null;
						}
						else {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_0 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
							final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_SequenceType_0).booleanValue();
							safe_oclIsKindOf_source = oclIsKindOf;
						}
						CAUGHT_safe_oclIsKindOf_source = safe_oclIsKindOf_source;
					}
					catch (Exception e) {
						CAUGHT_safe_oclIsKindOf_source = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_0;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_0 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_2 = ownedSource_0 == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source_0;
						if (symbol_2 == Boolean.TRUE) {
							safe_type_source_0 = null;
						}
						else {
							assert ownedSource_0 != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedSource_0.getType();
							safe_type_source_0 = type_0;
						}
						final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source_0, TYP_OrderedSetType_0).booleanValue();
						CAUGHT_oclIsKindOf_0 = oclIsKindOf_0;
					}
					catch (Exception e) {
						CAUGHT_oclIsKindOf_0 = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean or = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_safe_oclIsKindOf_source, CAUGHT_oclIsKindOf_0);
					if (or == null) {
						throw new InvalidValueException("Null if condition");
					}
					/*@Thrown*/ boolean symbol_3;
					if (or) {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_1 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
						final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_OrderedSetType_1).booleanValue();
						symbol_3 = oclIsKindOf_1;
					}
					else {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SetType_0 = idResolver.getClass(PivotTables.CLSSid_SetType, null);
						final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_SetType_0).booleanValue();
						symbol_3 = oclIsKindOf_2;
					}
					result = symbol_3;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_ClosureTypeIsUniqueCollection, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_4 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCollectElementTypeIsFlattenedBodyType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CollectElementTypeIsFlattenedBodyType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::CollectElementTypeIsFlattenedBodyType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'collect' implies
		 *         type.oclAsType(CollectionType).elementType =
		 *         ownedBody.type.flattenedType()
		 *       in
		 *         'IteratorExp::CollectElementTypeIsFlattenedBodyType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_CollectElementTypeIsFlattenedBodyType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_collect.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_8 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_8));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = oclAsType.getElementType();
					@SuppressWarnings("null")
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedBody = this.getOwnedBody();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedBody.getType();
					if (type_0 == null) {
						throw new InvalidValueException("Null source for \'pivot::Type::flattenedType() : Type[?]\'");
					}
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type flattenedType = type_0.flattenedType();
					final /*@Thrown*/ boolean eq_0 = elementType.getTypeId() == flattenedType.getTypeId();
					result = eq_0;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_CollectElementTypeIsFlattenedBodyType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateClosureSourceElementTypeIsBodyElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv ClosureSourceElementTypeIsBodyElementType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::ClosureSourceElementTypeIsBodyElementType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'closure' implies
		 *         ownedSource?.type.oclAsType(CollectionType).elementType =
		 *         if ownedBody.type.oclIsKindOf(CollectionType)
		 *         then
		 *           ownedBody.type.oclAsType(CollectionType).elementType
		 *         else ownedBody.type
		 *         endif
		 *       in
		 *         'IteratorExp::ClosureSourceElementTypeIsBodyElementType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_ClosureSourceElementTypeIsBodyElementType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_2;
		if (le) {
			symbol_2 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_closure.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_5 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_6 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					@SuppressWarnings("null")
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedBody_1 = this.getOwnedBody();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_2 = ownedBody_1.getType();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
						safe_type_source = type;
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_5));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = oclAsType.getElementType();
					final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_CollectionType_6).booleanValue();
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type symbol_1;
					if (oclIsKindOf) {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_7 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType_0 = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type_2, TYP_CollectionType_7));
						@SuppressWarnings("null")
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType_0 = oclAsType_0.getElementType();
						symbol_1 = elementType_0;
					}
					else {
						symbol_1 = type_2;
					}
					final /*@Thrown*/ boolean eq_0 = (symbol_1 != null) ? (elementType.getTypeId() == symbol_1.getTypeId()) : false;
					result = eq_0;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_ClosureSourceElementTypeIsBodyElementType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_2 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_2;
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
		 *
		 * inv ClosureElementTypeIsSourceElementType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::ClosureElementTypeIsSourceElementType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'closure' implies
		 *         type.oclAsType(CollectionType).elementType =
		 *         ownedSource?.type.oclAsType(CollectionType).elementType
		 *       in
		 *         'IteratorExp::ClosureElementTypeIsSourceElementType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_ClosureElementTypeIsSourceElementType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_closure.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_3 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_4 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_3));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = oclAsType.getElementType();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedSource.getType();
						safe_type_source = type_0;
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType_0 = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_4));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType_0 = oclAsType_0.getElementType();
					final /*@Thrown*/ boolean eq_0 = elementType.getTypeId() == elementType_0.getTypeId();
					result = eq_0;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_ClosureElementTypeIsSourceElementType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCollectTypeIsUnordered(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CollectTypeIsUnordered:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::CollectTypeIsUnordered'.getSeverity()
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
		 *         'IteratorExp::CollectTypeIsUnordered'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_CollectTypeIsUnordered);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_3;
		if (le) {
			symbol_3 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_collect.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_2 = this.getType();
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_1 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_type_source = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
							safe_type_source = type;
						}
						final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_SequenceType_1).booleanValue();
						CAUGHT_oclIsKindOf = oclIsKindOf;
					}
					catch (Exception e) {
						CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_0;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_2 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_1 = ownedSource_0 == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source_0;
						if (symbol_1 == Boolean.TRUE) {
							safe_type_source_0 = null;
						}
						else {
							assert ownedSource_0 != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedSource_0.getType();
							safe_type_source_0 = type_0;
						}
						final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source_0, TYP_OrderedSetType_2).booleanValue();
						CAUGHT_oclIsKindOf_0 = oclIsKindOf_0;
					}
					catch (Exception e) {
						CAUGHT_oclIsKindOf_0 = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean or = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_oclIsKindOf, CAUGHT_oclIsKindOf_0);
					if (or == null) {
						throw new InvalidValueException("Null if condition");
					}
					/*@Thrown*/ boolean symbol_2;
					if (or) {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_2 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
						final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_SequenceType_2).booleanValue();
						symbol_2 = oclIsKindOf_1;
					}
					else {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_BagType_0 = idResolver.getClass(PivotTables.CLSSid_BagType, null);
						final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_BagType_0).booleanValue();
						symbol_2 = oclIsKindOf_2;
					}
					result = symbol_2;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_CollectTypeIsUnordered, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_3 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSortedByIsOrderedIfSourceIsOrdered(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SortedByIsOrderedIfSourceIsOrdered:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::SortedByIsOrderedIfSourceIsOrdered'.getSeverity()
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
		 *         'IteratorExp::SortedByIsOrderedIfSourceIsOrdered'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_SortedByIsOrderedIfSourceIsOrdered);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_3;
		if (le) {
			symbol_3 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_sortedBy.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_2 = this.getType();
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_3 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_type_source = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
							safe_type_source = type;
						}
						final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_SequenceType_3).booleanValue();
						CAUGHT_oclIsKindOf = oclIsKindOf;
					}
					catch (Exception e) {
						CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf_0;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_BagType_1 = idResolver.getClass(PivotTables.CLSSid_BagType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_1 = ownedSource_0 == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source_0;
						if (symbol_1 == Boolean.TRUE) {
							safe_type_source_0 = null;
						}
						else {
							assert ownedSource_0 != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedSource_0.getType();
							safe_type_source_0 = type_0;
						}
						final /*@Thrown*/ boolean oclIsKindOf_0 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source_0, TYP_BagType_1).booleanValue();
						CAUGHT_oclIsKindOf_0 = oclIsKindOf_0;
					}
					catch (Exception e) {
						CAUGHT_oclIsKindOf_0 = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean or = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_oclIsKindOf, CAUGHT_oclIsKindOf_0);
					if (or == null) {
						throw new InvalidValueException("Null if condition");
					}
					/*@Thrown*/ boolean symbol_2;
					if (or) {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_SequenceType_4 = idResolver.getClass(PivotTables.CLSSid_SequenceType, null);
						final /*@Thrown*/ boolean oclIsKindOf_1 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_SequenceType_4).booleanValue();
						symbol_2 = oclIsKindOf_1;
					}
					else {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OrderedSetType_3 = idResolver.getClass(PivotTables.CLSSid_OrderedSetType, null);
						final /*@Thrown*/ boolean oclIsKindOf_2 = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type_2, TYP_OrderedSetType_3).booleanValue();
						symbol_2 = oclIsKindOf_2;
					}
					result = symbol_2;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_SortedByIsOrderedIfSourceIsOrdered, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_3 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSortedByElementTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SortedByElementTypeIsSourceElementType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::SortedByElementTypeIsSourceElementType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = name = 'sortedBy' implies
		 *         type.oclAsType(CollectionType).elementType =
		 *         ownedBody.type.oclAsType(CollectionType).elementType
		 *       in
		 *         'IteratorExp::SortedByElementTypeIsSourceElementType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_SortedByElementTypeIsSourceElementType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.lang.@Nullable String name = this.getName();
				final /*@NonInvalid*/ boolean eq = PivotTables.STR_sortedBy.equals(name);
				/*@Thrown*/ boolean result;
				if (eq) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_11 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_12 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_11));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = oclAsType.getElementType();
					@SuppressWarnings("null")
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedBody = this.getOwnedBody();
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedBody.getType();
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType_0 = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, type_0, TYP_CollectionType_12));
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType_0 = oclAsType_0.getElementType();
					final /*@Thrown*/ boolean eq_0 = elementType.getTypeId() == elementType_0.getTypeId();
					result = eq_0;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_SortedByElementTypeIsSourceElementType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateIteratorTypeIsSourceElementType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv IteratorTypeIsSourceElementType:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::IteratorTypeIsSourceElementType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : Boolean[?] = self.ownedIterators->forAll(p |
		 *           ownedSource?.type.oclAsType(CollectionType)
		 *           .elementType.conformsTo(p.type))
		 *       in
		 *         'IteratorExp::IteratorTypeIsSourceElementType'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_IteratorTypeIsSourceElementType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
				/*@Thrown*/ java.lang.@Nullable Object accumulator = ValueUtil.TRUE_VALUE;
				@NonNull Iterator<Object> ITERATOR_p_0 = ValueUtil.lazyIterator(BOXED_ownedIterators);
				/*@Thrown*/ boolean result;
				while (true) {
					if (!ITERATOR_p_0.hasNext()) {
						if (accumulator == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							throw (InvalidValueException)accumulator;
						}
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Variable p_0 = (Variable)ITERATOR_p_0.next();
					/**
					 *
					 * ownedSource?.type.oclAsType(CollectionType)
					 * .elementType.conformsTo(p.type)
					 */
					/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_9 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_type_source = null;
						}
						else {
							assert ownedSource != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
							safe_type_source = type;
						}
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_9));
						@SuppressWarnings("null")
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type elementType = oclAsType.getElementType();
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = p_0.getType();
						final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, elementType, type_0).booleanValue();
						CAUGHT_conformsTo = conformsTo;
					}
					catch (Exception e) {
						CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
					}
					//
					if (CAUGHT_conformsTo == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
						result = ValueUtil.FALSE_VALUE;
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
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_IteratorTypeIsSourceElementType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeIteratorIsRequired(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SafeIteratorIsRequired:
		 *   let
		 *     severity : Integer[1] = 'IteratorExp::SafeIteratorIsRequired'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = isSafe implies
		 *         ownedIterators->forAll(isRequired)
		 *       in
		 *         'IteratorExp::SafeIteratorIsRequired'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_SafeIteratorIsRequired);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
			/*@NonInvalid*/ boolean result;
			if (isSafe) {
				final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
				/*@Thrown*/ java.lang.@Nullable Object accumulator = ValueUtil.TRUE_VALUE;
				@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedIterators.iterator();
				/*@NonInvalid*/ boolean forAll;
				while (true) {
					if (!ITERATOR__1.hasNext()) {
						if (accumulator == ValueUtil.TRUE_VALUE) {
							forAll = ValueUtil.TRUE_VALUE;
						}
						else {
							throw (InvalidValueException)accumulator;
						}
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Variable _1 = (Variable)ITERATOR__1.next();
					/**
					 * isRequired
					 */
					final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
					//
					if (isRequired == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
						forAll = ValueUtil.FALSE_VALUE;
						break;														// Stop immediately
					}
					else if (isRequired == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
						;															// Carry on
					}
					else {															// Impossible badly typed result
						accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
					}
				}
				result = forAll;
			}
			else {
				result = ValueUtil.TRUE_VALUE;
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_SafeIteratorIsRequired, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
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
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SafeSourceCanBeNull:
		 *   let severity : Integer[1] = 'IteratorExp::SafeSourceCanBeNull'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = isSafe implies
		 *         not ownedSource?.type.oclAsType(CollectionType).isNullFree
		 *       in
		 *         'IteratorExp::SafeSourceCanBeNull'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_IteratorExp_c_c_SafeSourceCanBeNull);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ boolean isSafe = this.isIsSafe();
			/*@NonInvalid*/ java.lang.@Nullable Boolean result;
			if (isSafe) {
				/*@Caught*/ @NonNull Object CAUGHT_isNullFree;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_10 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedSource != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
						safe_type_source = type;
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull CollectionType oclAsType = ClassUtil.nonNullState((CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_10));
					final /*@Thrown*/ boolean isNullFree = oclAsType.isIsNullFree();
					CAUGHT_isNullFree = isNullFree;
				}
				catch (Exception e) {
					CAUGHT_isNullFree = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_isNullFree);
				result = not;
			}
			else {
				result = ValueUtil.TRUE_VALUE;
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_IteratorExp_c_c_SafeSourceCanBeNull, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
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
			case PivotPackage.ITERATOR_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.ITERATOR_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.ITERATOR_EXP___IS_NON_NULL:
				return isNonNull();
			case PivotPackage.ITERATOR_EXP___IS_NULL:
				return isNull();
			case PivotPackage.ITERATOR_EXP___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_NO_INITIALIZERS__DIAGNOSTICCHAIN_MAP:
				return validateNoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SOURCE_IS_COLLECTION__DIAGNOSTICCHAIN_MAP:
				return validateSourceIsCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___GET_REFERRED_ELEMENT:
				return getReferredElement();
			case PivotPackage.ITERATOR_EXP___VALIDATE_ANY_BODY_TYPE_IS_BOOLEAN__DIAGNOSTICCHAIN_MAP:
				return validateAnyBodyTypeIsBoolean((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_ANY_HAS_ONE_ITERATOR__DIAGNOSTICCHAIN_MAP:
				return validateAnyHasOneIterator((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_ANY_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateAnyTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_BODY_ELEMENT_TYPE_IS_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateClosureBodyElementTypeIsIteratorType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_BODY_TYPE_IS_CONFORMANTTO_ITERATOR_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateClosureBodyTypeIsConformanttoIteratorType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_ELEMENT_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateClosureElementTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_HAS_ONE_ITERATOR__DIAGNOSTICCHAIN_MAP:
				return validateClosureHasOneIterator((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_SOURCE_ELEMENT_TYPE_IS_BODY_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateClosureSourceElementTypeIsBodyElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_CLOSURE_TYPE_IS_UNIQUE_COLLECTION__DIAGNOSTICCHAIN_MAP:
				return validateClosureTypeIsUniqueCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_COLLECT_ELEMENT_TYPE_IS_FLATTENED_BODY_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateCollectElementTypeIsFlattenedBodyType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_COLLECT_TYPE_IS_UNORDERED__DIAGNOSTICCHAIN_MAP:
				return validateCollectTypeIsUnordered((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_ITERATOR_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateIteratorTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP:
				return validateSafeIteratorIsRequired((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SORTED_BY_ELEMENT_TYPE_IS_SOURCE_ELEMENT_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateSortedByElementTypeIsSourceElementType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SORTED_BY_IS_ORDERED_IF_SOURCE_IS_ORDERED__DIAGNOSTICCHAIN_MAP:
				return validateSortedByIsOrderedIfSourceIsOrdered((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_SORTED_BY_ITERATOR_TYPE_IS_COMPARABLE__DIAGNOSTICCHAIN_MAP:
				return validateSortedByIteratorTypeIsComparable((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.ITERATOR_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP:
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
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case PivotPackage.REFERRING_ELEMENT___GET_REFERRED_ELEMENT: return PivotPackage.ITERATOR_EXP___GET_REFERRED_ELEMENT;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

} //IteratorExpImpl
