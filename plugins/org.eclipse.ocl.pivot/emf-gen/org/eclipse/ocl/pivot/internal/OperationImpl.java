/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.types.TemplateParameters;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.SetValue.Accumulator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getGeneric <em>Generic</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedTemplateArguments <em>Owned Template Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedTemplateParameters <em>Owned Template Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedWildcards <em>Owned Wildcards</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getBodyExpression <em>Body Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsInvalidating <em>Is Invalidating</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsTransient <em>Is Transient</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsTypeof <em>Is Typeof</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsValidating <em>Is Validating</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedPostconditions <em>Owned Postconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedPreconditions <em>Owned Preconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwningClass <em>Owning Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getPrecedence <em>Precedence</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getRaisedExceptions <em>Raised Exceptions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getRedefinedOperations <em>Redefined Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationImpl
extends FeatureImpl
implements Operation {

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_FEATURE_COUNT = FeatureImpl.FEATURE_FEATURE_COUNT + 17;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_OPERATION_COUNT = FeatureImpl.FEATURE_OPERATION_COUNT + 5;

	/**
	 * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedConstraints;

	/**
	 * The cached value of the '{@link #getGeneric() <em>Generic</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneric()
	 * @generated
	 * @ordered
	 */
	protected TemplateableElement generic;

	/**
	 * The cached value of the '{@link #getOwnedTemplateArguments() <em>Owned Template Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTemplateArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateArgument> ownedTemplateArguments;

	/**
	 * The cached value of the '{@link #getOwnedTemplateParameters() <em>Owned Template Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTemplateParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateParameter> ownedTemplateParameters;

	/**
	 * The cached value of the '{@link #getOwnedWildcards() <em>Owned Wildcards</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedWildcards()
	 * @generated
	 * @ordered
	 */
	protected EList<WildcardType> ownedWildcards;

	/**
	 * The cached value of the '{@link #getBodyExpression() <em>Body Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyExpression()
	 * @generated
	 * @ordered
	 */
	protected LanguageExpression bodyExpression;

	/**
	 * The default value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INVALIDATING_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_INVALIDATING_EFLAG = 1 << 10;

	/**
	 * The default value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TRANSIENT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TRANSIENT_EFLAG = 1 << 11;

	/**
	 * The default value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TYPEOF_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TYPEOF_EFLAG = 1 << 12;

	/**
	 * The default value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VALIDATING_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_VALIDATING_EFLAG = 1 << 13;

	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> ownedParameters;

	/**
	 * The cached value of the '{@link #getOwnedPostconditions() <em>Owned Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedPostconditions;

	/**
	 * The cached value of the '{@link #getOwnedPreconditions() <em>Owned Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedPreconditions;

	/**
	 * The cached value of the '{@link #getPrecedence() <em>Precedence</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecedence()
	 * @generated
	 * @ordered
	 */
	protected Precedence precedence;

	/**
	 * The cached value of the '{@link #getRaisedExceptions() <em>Raised Exceptions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRaisedExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> raisedExceptions;

	/**
	 * The cached value of the '{@link #getRedefinedOperations() <em>Redefined Operations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> redefinedOperations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Constraint> getOwnedConstraints()
	{
		if (ownedConstraints == null)
		{
			ownedConstraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, 11);
		}
		return ownedConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateArgument> getOwnedTemplateArguments()
	{
		if (ownedTemplateArguments == null)
		{
			ownedTemplateArguments = new EObjectContainmentWithInverseEList<TemplateArgument>(TemplateArgument.class, this, 13, 7);
		}
		return ownedTemplateArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Type> getRaisedExceptions()
	{
		if (raisedExceptions == null)
		{
			raisedExceptions = new EObjectResolvingEList<Type>(Type.class, this, 26);
		}
		return raisedExceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Operation> getRedefinedOperations()
	{
		if (redefinedOperations == null)
		{
			redefinedOperations = new EObjectResolvingEList<Operation>(Operation.class, this, 27);
		}
		return redefinedOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean conformsTo(final CallExp caller, final OCLExpression argument, final Parameter parameter)
	{
		/**
		 *
		 * let selfType : Class[?] = owningClass
		 * in
		 *   if selfType <> null
		 *   then
		 *     let nonNullSelfType : Class[1] = selfType
		 *     in
		 *       let parameterType : Type[?] = parameter.type
		 *       in
		 *         let
		 *           nonLambdaParameter : Parameter[?] = if
		 *             parameterType.oclIsKindOf(LambdaType)
		 *           then parameterType.oclAsType(LambdaType).ownedResult
		 *           else parameter
		 *           endif
		 *         in
		 *           let
		 *             nonLambdaParameterType : Type[?] = if nonLambdaParameter.isTypeof
		 *             then Class
		 *             else
		 *               nonLambdaParameter.type?.specializeIn(caller, nonNullSelfType)
		 *             endif
		 *           in
		 *             let nullityConforms : Boolean[?] = nonLambdaParameter.isRequired implies argument.isRequired
		 *             in
		 *               let
		 *                 typeConforms : Boolean[?] = argument.type?.conformsTo(nonLambdaParameterType)
		 *               in typeConforms and nullityConforms
		 *   else false
		 *   endif
		 */
		final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
		final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Class selfType_1 = this.getOwningClass();
		final /*@NonInvalid*/ boolean NE_selfType_1_null = selfType_1 != null;
		/*@Thrown*/ @Nullable Boolean IF_NE_selfType_1_null;
		if (NE_selfType_1_null) {
			final /*@NonInvalid*/ @Nullable Type parameterType = parameter.getType();
			/*@Caught*/ @NonNull Object CAUGHT_nonLambdaParameter;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_LambdaType_0 = idResolver.getClass(PivotTables.CLSSid_LambdaType, null);
				final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, parameterType, TYP_LambdaType_0).booleanValue();
				/*@Thrown*/ @NonNull Parameter nonLambdaParameter;
				if (oclIsKindOf) {
					@SuppressWarnings("null")
					final /*@Thrown*/ @NonNull LambdaType oclAsType = (@NonNull LambdaType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, parameterType, TYP_LambdaType_0);
					@SuppressWarnings("null")
					final /*@Thrown*/ @NonNull LambdaParameter ownedResult = oclAsType.getOwnedResult();
					nonLambdaParameter = ownedResult;
				}
				else {
					nonLambdaParameter = parameter;
				}
				CAUGHT_nonLambdaParameter = nonLambdaParameter;
			}
			catch (Exception e) {
				CAUGHT_nonLambdaParameter = ValueUtil.createInvalidValue(e);
			}
			/*@Caught*/ @Nullable Object CAUGHT_nonLambdaParameterType;
			try {
				if (CAUGHT_nonLambdaParameter instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_nonLambdaParameter;
				}
				final /*@Thrown*/ boolean isTypeof = ((Parameter)CAUGHT_nonLambdaParameter).isIsTypeof();
				/*@Thrown*/ @Nullable Type nonLambdaParameterType;
				if (isTypeof) {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_0 = idResolver.getClass(PivotTables.CLSSid_Class, null);
					nonLambdaParameterType = TYP_Class_0;
				}
				else {
					final /*@Thrown*/ @Nullable Type type = ((TypedElement)CAUGHT_nonLambdaParameter).getType();
					/*@Caught*/ @Nullable Object CAUGHT_type;
					try {
						CAUGHT_type = type;
					}
					catch (Exception e) {
						CAUGHT_type = ValueUtil.createInvalidValue(e);
					}
					final /*@NonInvalid*/ @NonNull Object EQ_CAUGHT_type_null = CAUGHT_type == null;
					/*@Thrown*/ @Nullable Type safe_specializeIn_source;
					if (EQ_CAUGHT_type_null == Boolean.TRUE) {
						safe_specializeIn_source = null;
					}
					else {
						assert type != null;
						@SuppressWarnings("null")
						final /*@Thrown*/ @NonNull Type specializeIn = type.specializeIn(caller, selfType_1);
						safe_specializeIn_source = specializeIn;
					}
					nonLambdaParameterType = safe_specializeIn_source;
				}
				CAUGHT_nonLambdaParameterType = nonLambdaParameterType;
			}
			catch (Exception e) {
				CAUGHT_nonLambdaParameterType = ValueUtil.createInvalidValue(e);
			}
			/*@Caught*/ @Nullable Object CAUGHT_nullityConforms;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_isRequired;
				try {
					if (CAUGHT_nonLambdaParameter instanceof InvalidValueException) {
						throw (InvalidValueException)CAUGHT_nonLambdaParameter;
					}
					final /*@Thrown*/ boolean isRequired = ((TypedElement)CAUGHT_nonLambdaParameter).isIsRequired();
					CAUGHT_isRequired = isRequired;
				}
				catch (Exception e) {
					CAUGHT_isRequired = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ @Nullable Boolean nullityConforms;
				if (CAUGHT_isRequired == ValueUtil.FALSE_VALUE) {
					nullityConforms = ValueUtil.TRUE_VALUE;
				}
				else {
					final /*@NonInvalid*/ boolean isRequired_0 = argument.isIsRequired();
					if (isRequired_0) {
						nullityConforms = ValueUtil.TRUE_VALUE;
					}
					else {
						if (CAUGHT_isRequired instanceof InvalidValueException) {
							throw (InvalidValueException)CAUGHT_isRequired;
						}
						nullityConforms = ValueUtil.FALSE_VALUE;
					}
				}
				CAUGHT_nullityConforms = nullityConforms;
			}
			catch (Exception e) {
				CAUGHT_nullityConforms = ValueUtil.createInvalidValue(e);
			}
			/*@Caught*/ @Nullable Object CAUGHT_safe_conformsTo_source;
			try {
				final /*@NonInvalid*/ @Nullable Type type_0 = argument.getType();
				final /*@NonInvalid*/ @NonNull Object EQ_type_0_null = type_0 == null;
				/*@Thrown*/ @Nullable Boolean safe_conformsTo_source;
				if (EQ_type_0_null == Boolean.TRUE) {
					safe_conformsTo_source = null;
				}
				else {
					if (type_0 == null) {
						throw new InvalidValueException("Null \'\'Type\'\' rather than \'\'OclVoid\'\' value required");
					}
					if (CAUGHT_nonLambdaParameterType instanceof InvalidValueException) {
						throw (InvalidValueException)CAUGHT_nonLambdaParameterType;
					}
					final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type_0, CAUGHT_nonLambdaParameterType).booleanValue();
					safe_conformsTo_source = conformsTo;
				}
				CAUGHT_safe_conformsTo_source = safe_conformsTo_source;
			}
			catch (Exception e) {
				CAUGHT_safe_conformsTo_source = ValueUtil.createInvalidValue(e);
			}
			final /*@Thrown*/ @Nullable Boolean and;
			if (CAUGHT_safe_conformsTo_source == ValueUtil.FALSE_VALUE) {
				and = ValueUtil.FALSE_VALUE;
			}
			else {
				if (CAUGHT_nullityConforms == ValueUtil.FALSE_VALUE) {
					and = ValueUtil.FALSE_VALUE;
				}
				else {
					if (CAUGHT_safe_conformsTo_source instanceof InvalidValueException) {
						throw (InvalidValueException)CAUGHT_safe_conformsTo_source;
					}
					if (CAUGHT_nullityConforms instanceof InvalidValueException) {
						throw (InvalidValueException)CAUGHT_nullityConforms;
					}
					if ((CAUGHT_safe_conformsTo_source == null) || (CAUGHT_nullityConforms == null)) {
						and = null;
					}
					else {
						and = ValueUtil.TRUE_VALUE;
					}
				}
			}
			IF_NE_selfType_1_null = and;
		}
		else {
			IF_NE_selfType_1_null = ValueUtil.FALSE_VALUE;
		}
		if (IF_NE_selfType_1_null == null) {
			throw new InvalidValueException("Null body for \'Operation::conformsTo(CallExp[1],OCLExpression[1],Parameter[1]) : Boolean[1]\'");
		}
		return IF_NE_selfType_1_null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Parameter> getOwnedParameters()
	{
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentWithInverseEList<Parameter>(Parameter.class, this, 21, 10);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Constraint> getOwnedPostconditions()
	{
		if (ownedPostconditions == null)
		{
			ownedPostconditions = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this, 22, 9);
		}
		return ownedPostconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Constraint> getOwnedPreconditions()
	{
		if (ownedPreconditions == null)
		{
			ownedPreconditions = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this, 23, 10);
		}
		return ownedPreconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateParameter> getOwnedTemplateParameters()
	{
		if (ownedTemplateParameters == null)
		{
			ownedTemplateParameters = new EObjectContainmentWithInverseEList<TemplateParameter>(TemplateParameter.class, this, 14, 6);
		}
		return ownedTemplateParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<WildcardType> getOwnedWildcards()
	{
		if (ownedWildcards == null)
		{
			ownedWildcards = new EObjectContainmentWithInverseEList<WildcardType>(WildcardType.class, this, 15, 22);
		}
		return ownedWildcards;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TemplateableElement getGenericGen()
	{
		if (generic != null && generic.eIsProxy())
		{
			InternalEObject oldGeneric = (InternalEObject)generic;
			generic = (TemplateableElement)eResolveProxy(oldGeneric);
			if (generic != oldGeneric)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 12, oldGeneric, generic));
			}
		}
		return generic;
	}
	@Override
	public Operation getGeneric()
	{
		return (Operation)getGenericGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateableElement basicGetGeneric()
	{
		return generic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGeneric(TemplateableElement newGeneric)
	{
		TemplateableElement oldGeneric = generic;
		generic = newGeneric;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 12, oldGeneric, generic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Precedence getPrecedence() {
		if (precedence != null && precedence.eIsProxy())
		{
			InternalEObject oldPrecedence = (InternalEObject)precedence;
			precedence = (Precedence)eResolveProxy(oldPrecedence);
			if (precedence != oldPrecedence)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 25, oldPrecedence, precedence));
			}
		}
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Precedence basicGetPrecedence() {
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecedence(Precedence newPrecedence) {
		Precedence oldPrecedence = precedence;
		precedence = newPrecedence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 25, oldPrecedence, precedence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LanguageExpression getBodyExpression()
	{
		return bodyExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBodyExpression(LanguageExpression newBodyExpression, NotificationChain msgs)
	{
		LanguageExpression oldBodyExpression = bodyExpression;
		bodyExpression = newBodyExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 16, oldBodyExpression, newBodyExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBodyExpression(LanguageExpression newBodyExpression)
	{
		if (newBodyExpression != bodyExpression)
		{
			NotificationChain msgs = null;
			if (bodyExpression != null)
				msgs = ((InternalEObject)bodyExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (16), null, msgs);
			if (newBodyExpression != null)
				msgs = ((InternalEObject)newBodyExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (16), null, msgs);
			msgs = basicSetBodyExpression(newBodyExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 16, newBodyExpression, newBodyExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsInvalidating()
	{
		return (eFlags & IS_INVALIDATING_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInvalidating(boolean newIsInvalidating)
	{
		boolean oldIsInvalidating = (eFlags & IS_INVALIDATING_EFLAG) != 0;
		if (newIsInvalidating) eFlags |= IS_INVALIDATING_EFLAG; else eFlags &= ~IS_INVALIDATING_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 17, oldIsInvalidating, newIsInvalidating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTransient()
	{
		return (eFlags & IS_TRANSIENT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTransient(boolean newIsTransient)
	{
		boolean oldIsTransient = (eFlags & IS_TRANSIENT_EFLAG) != 0;
		if (newIsTransient) eFlags |= IS_TRANSIENT_EFLAG; else eFlags &= ~IS_TRANSIENT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 18, oldIsTransient, newIsTransient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTypeof()
	{
		return (eFlags & IS_TYPEOF_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTypeof(boolean newIsTypeof)
	{
		boolean oldIsTypeof = (eFlags & IS_TYPEOF_EFLAG) != 0;
		if (newIsTypeof) eFlags |= IS_TYPEOF_EFLAG; else eFlags &= ~IS_TYPEOF_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 19, oldIsTypeof, newIsTypeof));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsValidating()
	{
		return (eFlags & IS_VALIDATING_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsValidating(boolean newIsValidating)
	{
		boolean oldIsValidating = (eFlags & IS_VALIDATING_EFLAG) != 0;
		if (newIsValidating) eFlags |= IS_VALIDATING_EFLAG; else eFlags &= ~IS_VALIDATING_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 20, oldIsValidating, newIsValidating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getOwningClass() {
		if (eContainerFeatureID() != (24)) return null;
		return (org.eclipse.ocl.pivot.Class)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(org.eclipse.ocl.pivot.Class newOwningClass, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, 24, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(org.eclipse.ocl.pivot.Class newOwningClass)
	{
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != (24) && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, 17, org.eclipse.ocl.pivot.Class.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 24, newOwningClass, newOwningClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleReturn(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "Operation::CompatibleReturn";
		try {
			/**
			 *
			 * inv CompatibleReturn:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = bodyExpression <> null and
			 *         bodyExpression.oclAsType(ExpressionInOCL).ownedBody <> null implies
			 *         CompatibleBody(bodyExpression)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION___VALIDATE_COMPATIBLE_RETURN__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_and;
					try {
						final /*@NonInvalid*/ @Nullable LanguageExpression bodyExpression = this.getBodyExpression();
						final /*@NonInvalid*/ boolean NE_bodyExpression_null = bodyExpression != null;
						final /*@Thrown*/ @Nullable Boolean and;
						if (!NE_bodyExpression_null) {
							and = ValueUtil.FALSE_VALUE;
						}
						else {
							/*@Caught*/ @NonNull Object CAUGHT_NE_ownedBody_null;
							try {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_ExpressionInOCL = idResolver.getClass(PivotTables.CLSSid_ExpressionInOCL, null);
								@SuppressWarnings("null")
								final /*@Thrown*/ @NonNull ExpressionInOCL oclAsType = (@NonNull ExpressionInOCL)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, bodyExpression, TYP_ExpressionInOCL);
								final /*@Thrown*/ @Nullable OCLExpression ownedBody = oclAsType.getOwnedBody();
								final /*@Thrown*/ boolean NE_ownedBody_null = ownedBody != null;
								CAUGHT_NE_ownedBody_null = NE_ownedBody_null;
							}
							catch (Exception e) {
								CAUGHT_NE_ownedBody_null = ValueUtil.createInvalidValue(e);
							}
							if (CAUGHT_NE_ownedBody_null == ValueUtil.FALSE_VALUE) {
								and = ValueUtil.FALSE_VALUE;
							}
							else {
								if (CAUGHT_NE_ownedBody_null instanceof InvalidValueException) {
									throw (InvalidValueException)CAUGHT_NE_ownedBody_null;
								}
								and = ValueUtil.TRUE_VALUE;
							}
						}
						CAUGHT_and = and;
					}
					catch (Exception e) {
						CAUGHT_and = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_and == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_CompatibleBody;
						try {
							final /*@NonInvalid*/ @Nullable LanguageExpression bodyExpression_1 = this.getBodyExpression();
							final /*@Thrown*/ boolean CompatibleBody = this.CompatibleBody(bodyExpression_1);
							CAUGHT_CompatibleBody = CompatibleBody;
						}
						catch (Exception e) {
							CAUGHT_CompatibleBody = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_CompatibleBody == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_and instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_and;
							}
							if (CAUGHT_CompatibleBody instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_CompatibleBody;
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
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateLoadableImplementation(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv LoadableImplementation: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUniquePreconditionName(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "Operation::UniquePreconditionName";
		try {
			/**
			 *
			 * inv UniquePreconditionName:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[1] = ownedPreconditions->isUnique(name)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION___VALIDATE_UNIQUE_PRECONDITION_NAME__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @NonNull Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @NonNull List<Constraint> ownedPreconditions = this.getOwnedPreconditions();
					final /*@NonInvalid*/ @NonNull SetValue BOXED_ownedPreconditions = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Constraint, ownedPreconditions);
					/*@Thrown*/ @NonNull Accumulator accumulator = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Constraint);
					@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedPreconditions.iterator();
					/*@Thrown*/ boolean result;
					while (true) {
						if (!ITERATOR__1.hasNext()) {
							result = true;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ @NonNull Constraint _1 = (@NonNull Constraint)ITERATOR__1.next();
						/**
						 * name
						 */
						final /*@NonInvalid*/ @Nullable String name = _1.getName();
						//
						if (accumulator.includes(name) == ValueUtil.TRUE_VALUE) {
							result = false;
							break;			// Abort after second find
						}
						else {
							accumulator.add(name);
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateUniquePostconditionName(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "Operation::UniquePostconditionName";
		try {
			/**
			 *
			 * inv UniquePostconditionName:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[1] = ownedPostconditions->isUnique(name)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.OPERATION___VALIDATE_UNIQUE_POSTCONDITION_NAME__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @NonNull Object CAUGHT_result;
				try {
					final /*@NonInvalid*/ @NonNull List<Constraint> ownedPostconditions = this.getOwnedPostconditions();
					final /*@NonInvalid*/ @NonNull SetValue BOXED_ownedPostconditions = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Constraint, ownedPostconditions);
					/*@Thrown*/ @NonNull Accumulator accumulator = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Constraint);
					@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedPostconditions.iterator();
					/*@Thrown*/ boolean result;
					while (true) {
						if (!ITERATOR__1.hasNext()) {
							result = true;
							break;
						}
						@SuppressWarnings("null")
						/*@NonInvalid*/ @NonNull Constraint _1 = (@NonNull Constraint)ITERATOR__1.next();
						/**
						 * name
						 */
						final /*@NonInvalid*/ @Nullable String name = _1.getName();
						//
						if (accumulator.includes(name) == ValueUtil.TRUE_VALUE) {
							result = false;
							break;			// Abort after second find
						}
						else {
							accumulator.add(name);
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 13:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTemplateArguments()).basicAdd(otherEnd, msgs);
			case 14:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTemplateParameters()).basicAdd(otherEnd, msgs);
			case 15:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedWildcards()).basicAdd(otherEnd, msgs);
			case 21:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParameters()).basicAdd(otherEnd, msgs);
			case 22:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPostconditions()).basicAdd(otherEnd, msgs);
			case 23:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPreconditions()).basicAdd(otherEnd, msgs);
			case 24:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningClass((org.eclipse.ocl.pivot.Class)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 11:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 13:
				return ((InternalEList<?>)getOwnedTemplateArguments()).basicRemove(otherEnd, msgs);
			case 14:
				return ((InternalEList<?>)getOwnedTemplateParameters()).basicRemove(otherEnd, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedWildcards()).basicRemove(otherEnd, msgs);
			case 16:
				return basicSetBodyExpression(null, msgs);
			case 21:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case 22:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case 23:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case 24:
				return basicSetOwningClass(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case 24:
				return eInternalContainer().eInverseRemove(this, 17, org.eclipse.ocl.pivot.Class.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
				return getImplementation();
			case 9:
				return getImplementationClass();
			case 10:
				return isIsStatic();
			case 11:
				return getOwnedConstraints();
			case 12:
				if (resolve) return getGeneric();
				return basicGetGeneric();
			case 13:
				return getOwnedTemplateArguments();
			case 14:
				return getOwnedTemplateParameters();
			case 15:
				return getOwnedWildcards();
			case 16:
				return getBodyExpression();
			case 17:
				return isIsInvalidating();
			case 18:
				return isIsTransient();
			case 19:
				return isIsTypeof();
			case 20:
				return isIsValidating();
			case 21:
				return getOwnedParameters();
			case 22:
				return getOwnedPostconditions();
			case 23:
				return getOwnedPreconditions();
			case 24:
				return getOwningClass();
			case 25:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
			case 26:
				return getRaisedExceptions();
			case 27:
				return getRedefinedOperations();
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
				setImplementation((LibraryFeature)newValue);
				return;
			case 9:
				setImplementationClass((String)newValue);
				return;
			case 10:
				setIsStatic((Boolean)newValue);
				return;
			case 11:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 12:
				setGeneric((TemplateableElement)newValue);
				return;
			case 13:
				getOwnedTemplateArguments().clear();
				getOwnedTemplateArguments().addAll((Collection<? extends TemplateArgument>)newValue);
				return;
			case 14:
				getOwnedTemplateParameters().clear();
				getOwnedTemplateParameters().addAll((Collection<? extends TemplateParameter>)newValue);
				return;
			case 15:
				getOwnedWildcards().clear();
				getOwnedWildcards().addAll((Collection<? extends WildcardType>)newValue);
				return;
			case 16:
				setBodyExpression((LanguageExpression)newValue);
				return;
			case 17:
				setIsInvalidating((Boolean)newValue);
				return;
			case 18:
				setIsTransient((Boolean)newValue);
				return;
			case 19:
				setIsTypeof((Boolean)newValue);
				return;
			case 20:
				setIsValidating((Boolean)newValue);
				return;
			case 21:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case 22:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 23:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 24:
				setOwningClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case 25:
				setPrecedence((Precedence)newValue);
				return;
			case 26:
				getRaisedExceptions().clear();
				getRaisedExceptions().addAll((Collection<? extends Type>)newValue);
				return;
			case 27:
				getRedefinedOperations().clear();
				getRedefinedOperations().addAll((Collection<? extends Operation>)newValue);
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
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case 9:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case 10:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case 11:
				getOwnedConstraints().clear();
				return;
			case 12:
				setGeneric((TemplateableElement)null);
				return;
			case 13:
				getOwnedTemplateArguments().clear();
				return;
			case 14:
				getOwnedTemplateParameters().clear();
				return;
			case 15:
				getOwnedWildcards().clear();
				return;
			case 16:
				setBodyExpression((LanguageExpression)null);
				return;
			case 17:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case 18:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case 19:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case 20:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case 21:
				getOwnedParameters().clear();
				return;
			case 22:
				getOwnedPostconditions().clear();
				return;
			case 23:
				getOwnedPreconditions().clear();
				return;
			case 24:
				setOwningClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case 25:
				setPrecedence((Precedence)null);
				return;
			case 26:
				getRaisedExceptions().clear();
				return;
			case 27:
				getRedefinedOperations().clear();
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
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case 9:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case 10:
				return ((eFlags & IS_STATIC_EFLAG) != 0) != IS_STATIC_EDEFAULT;
			case 11:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 12:
				return generic != null;
			case 13:
				return ownedTemplateArguments != null && !ownedTemplateArguments.isEmpty();
			case 14:
				return ownedTemplateParameters != null && !ownedTemplateParameters.isEmpty();
			case 15:
				return ownedWildcards != null && !ownedWildcards.isEmpty();
			case 16:
				return bodyExpression != null;
			case 17:
				return ((eFlags & IS_INVALIDATING_EFLAG) != 0) != IS_INVALIDATING_EDEFAULT;
			case 18:
				return ((eFlags & IS_TRANSIENT_EFLAG) != 0) != IS_TRANSIENT_EDEFAULT;
			case 19:
				return ((eFlags & IS_TYPEOF_EFLAG) != 0) != IS_TYPEOF_EDEFAULT;
			case 20:
				return ((eFlags & IS_VALIDATING_EFLAG) != 0) != IS_VALIDATING_EDEFAULT;
			case 21:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case 22:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case 23:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case 24:
				return getOwningClass() != null;
			case 25:
				return precedence != null;
			case 26:
				return raisedExceptions != null && !raisedExceptions.isEmpty();
			case 27:
				return redefinedOperations != null && !redefinedOperations.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Namespace.class)
		{
			switch (derivedFeatureID)
			{
				case 11: return 5;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (derivedFeatureID)
			{
				case 12: return 4;
				case 13: return 5;
				case 14: return 6;
				case 15: return 7;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Namespace.class)
		{
			switch (baseFeatureID)
			{
				case 5: return 11;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (baseFeatureID)
			{
				case 4: return 12;
				case 5: return 13;
				case 6: return 14;
				case 7: return 15;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID)
		{
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 4:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 6:
				return conformsTo((CallExp)arguments.get(0), (OCLExpression)arguments.get(1), (Parameter)arguments.get(2));
			case 7:
				return validateCompatibleReturn((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateLoadableImplementation((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return validateUniquePostconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 10:
				return validateUniquePreconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
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
		return visitor.visitOperation(this);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable List<@NonNull TemplateArgument> basicGetOwnedTemplateArguments() {
		if (ownedTemplateArguments == null) {
			return null;
		}
		if (ownedTemplateArguments.isEmpty()) {
			return null;
		}
		return ClassUtil.nullFree(ownedTemplateArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable List<@NonNull TemplateParameter> basicGetOwnedTemplateParameters() {
		if (ownedTemplateParameters == null) {
			return null;
		}
		if (ownedTemplateParameters.isEmpty()) {
			return null;
		}
		return ClassUtil.nullFree(ownedTemplateParameters);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public void eraseContents() {}

	@Override
	public @Nullable FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		org.eclipse.ocl.pivot.Class owningType = getOwningClass();
		if (owningType != null) {
			return standardLibrary.getFlatClass(owningType);
		}
		else {
			return null;
		}
	}

	@Override
	public int getIndex() {
		return -1;		// WIP
	}

	@Override
	public @NonNull ParametersId getParametersId() {
		return getOperationId().getParametersId();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TemplateParameters getTemplateParameters() {
		return TemplateParameters.getTemplateParameters(this);
	}

	private OperationId operationId = null;

	@Override
	public final @NonNull OperationId getOperationId() {
		OperationId operationId2 = operationId;
		if (operationId2 == null) {
			synchronized (this) {
				operationId2 = operationId;
				if (operationId2 == null) {
					operationId = operationId2 = IdManager.getOperationId(this);
				}
			}
		}
		return operationId2;
	}

	/**
	 * @since 7.0
	 */
	@Override
	protected @Nullable EObject getReloadableEObjectFromCompleteAS(@NonNull EnvironmentFactory environmentFactory) {
		CompleteClass completeClass = environmentFactory.getCompleteModel().basicGetCompleteClass(PivotUtil.getOwningClass(this));
		if (completeClass != null) {
			Iterable<@NonNull Operation> operationOverloads = completeClass.getOperationOverloads(this);
			if (operationOverloads != null) {
				for (Operation asOperation : operationOverloads) {
					EObject esObject = asOperation.getESObject();
					if (esObject != null) {
						return esObject;
					}
				}
				for (Operation asOperation : operationOverloads) {		// Fudge no-EOperation Pivot/OCLstdlib conflict
					String implementationClass = asOperation.getImplementationClass();
					if (implementationClass != null) {
						return asOperation;
					}
				}
			}
		}
		return getESObject();
	}

	@Override
	public void setName(String newName) {
		if ("conformsTo".equals(newName)) {
			getClass();		// XXX
		}
		super.setName(newName);
	}
} //OperationImpl
