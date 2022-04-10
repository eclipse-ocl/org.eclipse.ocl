/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.OclInvalidTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TemplateBinding;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.UnspecifiedId;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

/**
 * The NameManagerHelper provides the overrideable support that provides suggestions for names for objects.
 */
public class NameManagerHelper
{
	public static final String BAG_NAME_HINT_PREFIX = "BAG";
	public static final String COLLECTION_NAME_HINT_PREFIX = "COL";
	public static final String CONSTRAINT_NAME_HINT_PREFIX = "";
	public static final String DEFAULT_GLOBAL_NAME_PREFIX = "global";
	public static final String DEFAULT_LOCAL_NAME_PREFIX = "symbol";
	//	public static final String ID_NAME_HINT_PREFIX = "TID";
	public static final String EXPRESSION_IN_OCL_NAME_HINT_PREFIX = PivotConstants.RESULT_NAME;
	public static final String IF_NAME_HINT_PREFIX = "IF_";
	public static final String INTEGER_NAME_HINT_PREFIX = "INT_";
	public static final String INVALID_NAME_HINT_PREFIX = "IVE_";
	public static final String ITERATION_NAME_HINT_PREFIX = "";
	public static final String LET_NAME_HINT_PREFIX = "WITH_";
	public static final String MAP_PART_HINT_PREFIX = "MAP_PART_";
	public static final String OPERATION_NAME_HINT_PREFIX = "OP_";
	public static final String OPERATION_CALL_EXP_NAME_HINT_PREFIX = ""; //"RES_";
	public static final String ORDERED_SET_NAME_HINT_PREFIX = "ORD";
	public static final String PROPERTY_NAME_HINT_PREFIX = "";
	public static final String REAL_NAME_HINT_PREFIX = "REA_";
	public static final String RANGE_NAME_HINT_PREFIX = "RNG";
	public static final String SEQUENCE_NAME_HINT_PREFIX = "SEQ";
	public static final String SET_NAME_HINT_PREFIX = "SET";
	public static final String SHADOW_NAME_HINT_PREFIX = "SHAD_";
	public static final String STRING_NAME_HINT_PREFIX = "STR_";
	public static final int STRING_NAME_HINT_LIMIT = 64;
	public static final String TUPLE_NAME_HINT_PREFIX = "TUP_";
	public static final String TYPE_NAME_HINT_PREFIX = "TYP_";
	public static final String UNLIMITED_NAME_HINT_PREFIX = "UNL_";
	public static final String VARIABLE_DECLARATION_NAME_HINT_PREFIX = "";

	public static class ASNameHelper extends AbstractExtendingVisitor<@NonNull String, @NonNull NameManagerHelper>
	{
		protected ASNameHelper(@NonNull NameManagerHelper context) {
			super(context);
		}

		@Override
		public @NonNull String visitCollectionLiteralExp(@NonNull CollectionLiteralExp object) {
			Type type = object.getType();
			return context.getTypeNameHint(type);
		}

		@Override
		public @NonNull String visitCollectionRange(@NonNull CollectionRange object) {
			return RANGE_NAME_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitConstraint(@NonNull Constraint object) {
			return context.getConstraintNameHint(object);
		}

		@Override
		public @NonNull String visitExpressionInOCL(@NonNull ExpressionInOCL object) {
			return EXPRESSION_IN_OCL_NAME_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitIfExp(@NonNull IfExp object) {
			return IF_NAME_HINT_PREFIX + context.getNameHint(PivotUtil.getOwnedCondition(object));
		}

		@Override
		public @NonNull String visitIntegerLiteralExp(@NonNull IntegerLiteralExp object) {
			Number numberSymbol = object.getIntegerSymbol();
			return context.getNumericNameHint(numberSymbol);
		}

		@Override
		public @NonNull String visitLetExp(@NonNull LetExp object) {
			return LET_NAME_HINT_PREFIX + context.getVariableDeclarationNameHint(PivotUtil.getOwnedVariable(object));
		}

		@Override
		public @NonNull String visitLiteralExp(@NonNull LiteralExp object) {
			return "literal";		// XXX do better
		}

		@Override
		public @NonNull String visitLoopExp(@NonNull LoopExp object) {
			Iteration referredIteration = object.getReferredIteration();
			return context.getIterationNameHint(referredIteration);
		}

		@Override
		public @NonNull String visitMapLiteralPart(@NonNull MapLiteralPart object) {
			return MAP_PART_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitNamedElement(@NonNull NamedElement object) {
			String name = object.getName();
			return name != null ? name : context.getFallBackHint("name");
		}

		@Override
		public @NonNull String visitOperation(@NonNull Operation object) {
			return context.getOperationNameHint(object);
		}

		@Override
		public @NonNull String visitOperationCallExp(@NonNull OperationCallExp object) {
			Operation referredOperation = object.getReferredOperation();
			return context.getOperationCallExpNameHint(referredOperation);
		}

		@Override
		public @NonNull String visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp object) {
			Property referredOppositeProperty = object.getReferredProperty();
			Property referredProperty = referredOppositeProperty != null ? referredOppositeProperty.getOpposite() : null;
			return context.getPropertyNameHint(referredProperty);
		}

		@Override
		public @NonNull String visitPropertyCallExp(@NonNull PropertyCallExp object) {
			Property referredProperty = object.getReferredProperty();
			return context.getPropertyNameHint(referredProperty);
		}

		@Override
		public @NonNull String visitRealLiteralExp(@NonNull RealLiteralExp object) {
			Number numberSymbol = object.getRealSymbol();
			return context.getNumericNameHint(numberSymbol);
		}

		@Override
		public @NonNull String visitShadowExp(@NonNull ShadowExp object) {
			return SHADOW_NAME_HINT_PREFIX + context.getTypeNameHint(PivotUtil.getType(object));
		}

		@Override
		public @NonNull String visitStringLiteralExp(@NonNull StringLiteralExp object) {
			String stringSymbol = object.getStringSymbol();
			return context.getStringNameHint(stringSymbol);
		}

		@Override
		public @NonNull String visitTupleLiteralExp(@NonNull TupleLiteralExp object) {
			return TUPLE_NAME_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitType(@NonNull Type object) {
			return context.getTypeNameHint(object);
		}

		@Override
		public @NonNull String visitTypeExp(@NonNull TypeExp object) {
			Type referredType = object.getType();
			return context.getTypeNameHint(referredType);
		}

		@Override
		public @NonNull String visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp object) {
			Number numberSymbol = object.getUnlimitedNaturalSymbol();
			return context.getNumericNameHint(numberSymbol);
		}

		@Override
		public @NonNull String visitVariableExp(@NonNull VariableExp object) {
			VariableDeclaration referredVariable = object.getReferredVariable();
			return context.getVariableDeclarationNameHint(referredVariable);
		}

		@Override
		public @NonNull String visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
		}
	}

	// CG names could be delegated to the AS, but we want to support AS-less CG synthesized during AS2CG
	public static class CGNameHelper extends AbstractExtendingCGModelVisitor<@NonNull String, @NonNull NameManagerHelper>
	{
		public CGNameHelper(@NonNull NameManagerHelper context) {
			super(context);
		}

		@Override
		public @NonNull String visitCGAccumulator(@NonNull CGAccumulator object) {
			return "accumulator";
		}

		@Override
		public @NonNull String visitCGAssertNonNullExp(@NonNull CGAssertNonNullExp object) {
			return "ASSERT_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGBoolean(@NonNull CGBoolean object) {
			String nameHint = object.isBooleanValue() ? "true" : "false";
			return nameHint;
		}

		@Override
		public @NonNull String visitCGBoxExp(@NonNull CGBoxExp object) {
//			return "BOXED_" + context.getNameHint(object.getSourceValue());
			return visiting(object);			// NameVariant should be eager;y declared
		}

		@Override
		public @NonNull String visitCGCachedOperation(@NonNull CGCachedOperation object) {
			return "CACHED_" + context.getNameHint(object.getAst());
		}

		@Override
		public @NonNull String visitCGCastExp(@NonNull CGCastExp object) {
			return "CAST_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGCatchExp(@NonNull CGCatchExp object) {
//			return "CAUGHT_" + context.getNameHint(object.getSourceValue());
			return visiting(object);			// NameVariant should be eager;y declared
		}

		@Override
		public @NonNull String visitCGCollectionExp(@NonNull CGCollectionExp object) {
			CollectionLiteralExp asCollectionLiteralExp = CGUtil.getAST(object);
			return context.getTypeNameHint(asCollectionLiteralExp.getType());
//			return String.valueOf(asCollectionLiteralExp.getKind().getName());
		}

		@Override
		public @NonNull String visitCGCollectionPart(@NonNull CGCollectionPart object) {
			return RANGE_NAME_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitCGConstraint(@NonNull CGConstraint object) {
			Constraint asConstraint = CGUtil.getAST(object);
			return context.getConstraintNameHint(asConstraint);
		}

		@Override
		public @NonNull String visitCGEcoreExp(@NonNull CGEcoreExp object) {
			return "ECORE_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGElementId(@NonNull CGElementId object) {
			ElementId elementId = object.getElementId();
			return elementId.accept(context.idVisitor);
		}

		@Override
		public @NonNull String visitCGExecutorShadowPart(@NonNull CGExecutorShadowPart object) {
			Property asProperty = CGUtil.getAST(object);
			return "CTORid_" + context.getPropertyNameHint(asProperty);
		}

		@Override
		public @NonNull String visitCGExecutorType(@NonNull CGExecutorType object) {
			Type asType = CGUtil.getAST(object);
			return context.getTypeNameHint(asType);
		}

		@Override
		public @NonNull String visitCGGuardExp(@NonNull CGGuardExp object) {
			return "GUARDED_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGIfExp(@NonNull CGIfExp object) {
			return IF_NAME_HINT_PREFIX + context.getNameHint(CGUtil.getCondition(object));
		}

		@Override
		public @NonNull String visitCGIsEqualExp(@NonNull CGIsEqualExp object) {
			return "IsEQ_";
		}

		@Override
		public @NonNull String visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp object) {
			return "IsEQ2_";
		}

		@Override
		public @NonNull String visitCGIsKindOfExp(@NonNull CGIsKindOfExp object) {
			return "IsKINDOF_";
		}

		@Override
		public @NonNull String visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp object) {
			return "IsUNDEF_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGInteger(@NonNull CGInteger object) {
			Number numericValue = object.getNumericValue();
			return context.getNumericNameHint(numericValue);
		}

		@Override
		public @NonNull String visitCGIterationCallExp(@NonNull CGIterationCallExp object) {
			Iteration referredIteration = ((LoopExp)object.getAst()).getReferredIteration();
			return context.getIterationNameHint(referredIteration);
		}

		@Override
		public @NonNull String visitCGInvalid(@NonNull CGInvalid object) {
			String nameHint = INVALID_NAME_HINT_PREFIX;
			return nameHint;
		}

		@Override
		public @NonNull String visitCGMapPart(@NonNull CGMapPart object) {
			String nameHint = MAP_PART_HINT_PREFIX;
			return nameHint;
		}

		@Override
		public @NonNull String visitCGNamedElement(@NonNull CGNamedElement object) {
			NamedElement asNamedElement = CGUtil.getAST(object);
			return context.getNameHint(asNamedElement);
		}

		@Override
		public @NonNull String visitCGNativeOperationCallExp(@NonNull CGNativeOperationCallExp object) {
			Method method = object.getMethod();
			return method.getName();
		}

		@Override
		public @NonNull String visitCGNull(@NonNull CGNull object) {
			String nameHint = "null";
			return nameHint;
		}

		@Override
		public @NonNull String visitCGOperation(@NonNull CGOperation object) {
			Operation asOperation = CGUtil.getAST(object);
			return context.getNameableHint(asOperation);
		}

//		@Override
//		public @NonNull String visitCGOperationCallExp(@NonNull CGOperationCallExp object) {
	//		Operation referredOperation = object.getReferredOperation();
	//		return context.getOperationCallExpNameHint(referredOperation);
//		return super.visitCGOperationCallExp(object);
//		}

//		@Override
//		public @NonNull String visitCGOppositePropertyCallExp(@NonNull CGOppositePropertyCallExp object) {
//		//	Property referredOppositeProperty = ((OppositePropertyCallExp)object.getAst()).getReferredProperty();
//			Property referredOppositeProperty = object.getReferredProperty();
//			Property referredProperty = referredOppositeProperty != null ? referredOppositeProperty.getOpposite() : null;
//			return context.getPropertyNameHint(referredProperty);
//		}

//		@Override
//		public @NonNull String visitCGPropertyCallExp(@NonNull CGPropertyCallExp object) {
//		//	Property referredProperty = ((PropertyCallExp)object.getAst()).getReferredProperty();
//			Property referredProperty = object.getReferredProperty();
//			return context.getPropertyNameHint(referredProperty);
//		}

		@Override
		public @NonNull String visitCGReal(@NonNull CGReal object) {
			Number numericValue = object.getNumericValue();
			return context.getNumericNameHint(numericValue);
		}

		@Override
		public @NonNull String visitCGShadowExp(@NonNull CGShadowExp object) {
			Type asType = CGUtil.getAST(CGUtil.getExecutorType(object));
			return SHADOW_NAME_HINT_PREFIX + context.getTypeNameHint(asType);
		}

		@Override
		public @NonNull String visitCGString(@NonNull CGString object) {
			String stringValue = object.getStringValue();
			return context.getStringNameHint(stringValue);
		}

		@Override
		public @NonNull String visitCGThrowExp(@NonNull CGThrowExp object) {
			return "THROWN_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGTupleExp(@NonNull CGTupleExp object) {
			return TUPLE_NAME_HINT_PREFIX;
		}

		@Override
		public @NonNull String visitCGUnboxExp(@NonNull CGUnboxExp object) {
			return "UNBOXED_" + context.getNameHint(object.getSourceValue());
		}

		@Override
		public @NonNull String visitCGValuedElement(@NonNull CGValuedElement object) {
			NameResolution nameResolution = object.basicGetNameResolution();
			if (nameResolution != null) {
				return nameResolution.getNameHint();
			}
			Element asElement = object.getAst();
			if (asElement != null) {
			//	return super.visitCGValuedElement(object);
				return context.getNameHint(asElement);
		}
		//	NameResolution nameResolution = object.basicGetNameResolution();
		//	assert nameResolution == null;
//				return nameResolution.getNameHint();
//			}
			CGValuedElement namedValue = object.getNamedValue();
			if (namedValue != object) {
				return context.getNameHint(namedValue);
			}
			else {
				return super.visitCGValuedElement(object);
			}
		}

//		@Override
//		public @NonNull String visitCGVariable(@NonNull CGVariable object) {
//			return visitCGNamedElement(object);		// Leapfrog getNamedValue reirection
//		}

		@Override
		public @NonNull String visitCGVariableExp(@NonNull CGVariableExp object) {
		//	CGVariable cgVariable = CGUtil.getReferredVariable(object);
		//	String name = cgVariable.accept(this);
		//	return context.getNameHint(cgVariable);
			return visiting(object);			// CGVariableExp should redirect to CGVariable when created
		}

		@Override
		public @NonNull String visiting(@NonNull CGElement visitable) {
			return context.getFallBackHint("Unsupported " + getClass().getSimpleName() + " : " + visitable.getClass().getSimpleName());
		//	throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
		//	assert false : "Unsupported " + getClass().getSimpleName() + " : " + visitable.getClass().getSimpleName();
		//	return DEFAULT_LOCAL_NAME_PREFIX;
		}
	}

	public static class IdNameHeper implements IdVisitor<@NonNull String>
	{
		protected final @NonNull NameManagerHelper context;

		public IdNameHeper(@NonNull NameManagerHelper context) {
			this.context = context;
		}

		@Override
		public @NonNull String visitClassId(@NonNull ClassId id) {
			return "CLSSid_" + id.getName();
		}

		@Override
		public @NonNull String visitCollectionTypeId(@NonNull CollectionTypeId id) {
			CollectionTypeId generalizedId = id.getGeneralizedId();
			String idPrefix;
			if (generalizedId == TypeId.BAG) {
				idPrefix = "BAG_";
			}
			else if (generalizedId == TypeId.ORDERED_SET) {
				idPrefix = "ORD_";
			}
			else if (generalizedId == TypeId.SEQUENCE) {
				idPrefix = "SEQ_";
			}
			else if (generalizedId == TypeId.SET) {
				idPrefix = "SET_";
			}
			else {
				idPrefix = "COL_";
			}
			if (generalizedId == id) {
				return idPrefix;
			}
			else {
				return idPrefix + id.getElementTypeId().accept(this);
			}
		}

		@Override
		public @NonNull String visitDataTypeId(@NonNull DataTypeId id) {
			return "DATAid_" + id.getName();
		}

		@Override
		public @NonNull String visitEnumerationId(@NonNull EnumerationId id) {
			return "ENUMid_" + id.getName();
		}

		@Override
		public @NonNull String visitEnumerationLiteralId(@NonNull EnumerationLiteralId id) {
			return "ELITid_" + id.getName();
		}

		@Override
		public @NonNull String visitInvalidId(@NonNull OclInvalidTypeId id) {
			return "INVid";
		}

		@Override
		public @NonNull String visitLambdaTypeId(@NonNull LambdaTypeId id) {
			return "LAMBid_" + id.getName();
		}

		@Override
		public @NonNull String visitMapTypeId(@NonNull MapTypeId id) {
			MapTypeId generalizedId = id.getGeneralizedId();
			String idPrefix = "MAP_";
			if (generalizedId == id) {
				return idPrefix;
			}
			else {
				return idPrefix + id.getKeyTypeId().accept(this) + id.getValueTypeId().accept(this);
			}
		}

		@Override
		public @NonNull String visitNestedPackageId(@NonNull NestedPackageId id) {
			return "PACKid_" + id.getName();
		}

		@Override
		public @NonNull String visitNsURIPackageId(@NonNull NsURIPackageId id) {
			return "PACKid_" + id.getNsURI();
		}

		@Override
		public @NonNull String visitNullId(@NonNull OclVoidTypeId id) {
			return "NULLid";
		}

		@Override
		public @NonNull String visitOperationId(@NonNull OperationId id) {
			return "OPid_" + id.getName();
		}

		@Override
		public @NonNull String visitPrimitiveTypeId(@NonNull PrimitiveTypeId id) {
			return "PRIMid_" + id.getName();
		}

		@Override
		public @NonNull String visitPropertyId(@NonNull PropertyId id) {
			return "PROPid_" + id.getName();
		}

		@Override
		public @NonNull String visitRootPackageId(@NonNull RootPackageId id) {
			return "PACKid_" + id.getName();
		}

		@Override
		public @NonNull String visitTemplateBinding(@NonNull TemplateBinding id) {
			return "BINDid_";
		}

		@Override
		public @NonNull String visitTemplateParameterId(@NonNull TemplateParameterId id) {
			return "TMPLid_";
		}

		@Override
		public @NonNull String visitTemplateableTypeId(@NonNull TemplateableTypeId id) {
			return "TYPEid_";
		}

		@Override
		public @NonNull String visitTuplePartId(@NonNull TuplePartId id) {
			return "PARTid_";
		}

		@Override
		public @NonNull String visitTupleTypeId(@NonNull TupleTypeId id) {
			return "TUPLid_";
		}

		@Override
		public @NonNull String visitUnspecifiedId(@NonNull UnspecifiedId id) {
			return "UNSPid_";
		}
	}

	protected final @NonNull ASNameHelper asVisitor;
	protected final @NonNull CGNameHelper cgVisitor;
	protected final @NonNull IdNameHeper idVisitor;

	public NameManagerHelper() {
		this.asVisitor = createASNameHelper();
		this.cgVisitor = createCGNameHelper();
		this.idVisitor = createIdNameHelper();
	}

	protected @NonNull ASNameHelper createASNameHelper() {
		return new ASNameHelper(this);
	}

	protected @NonNull CGNameHelper createCGNameHelper() {
		return new CGNameHelper(this);
	}

	protected @NonNull IdNameHeper createIdNameHelper() {
		return new IdNameHeper(this);
	}

//	protected @NonNull ASNameHelper getAsVisitor() {
//		return asVisitor;
//	}

//	protected @NonNull CGNameHelper getCgVisitor() {
//		return cgVisitor;
//	}

	protected @NonNull String getConstraintNameHint(@Nullable Constraint aConstraint) {
		if (aConstraint != null) {
			String string = aConstraint.getName();
			if (string == null) {
				string = "anon";
			}
			return CONSTRAINT_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, CONSTRAINT_NAME_HINT_PREFIX.length() > 0, aConstraint);
		}
		else {
			return getFallBackHint("constraint");
		}
	}

	/**
	 * Return defaultHint as a fall-back after failing to provide a better hint. This routine is just a communal debug opportinity.
	 */
	protected @NonNull String getFallBackHint(@NonNull String defaultHint) {
		assert false : defaultHint;
	//	throw new UnsupportedOperationException();
		return DEFAULT_LOCAL_NAME_PREFIX;
	}

//	protected @NonNull IdNameHeper getIdVisitor() {
//		return idVisitor;
//	}

	protected @NonNull String getIterationNameHint(@Nullable Iteration anIteration) {
		if (anIteration != null) {
			@SuppressWarnings("null") @NonNull String string = anIteration.getName();
			return ITERATION_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, ITERATION_NAME_HINT_PREFIX.length() > 0, anIteration);
		}
		else {
			return getFallBackHint("iteration");
		}
	}

	protected @NonNull String getKindHint(@Nullable String kind) {
		if (TypeId.BAG_NAME.equals(kind)) {
			return BAG_NAME_HINT_PREFIX;
		}
		else if (TypeId.ORDERED_SET_NAME.equals(kind)) {
			return ORDERED_SET_NAME_HINT_PREFIX;
		}
		else if (TypeId.SEQUENCE_NAME.equals(kind)) {
			return SEQUENCE_NAME_HINT_PREFIX;
		}
		else if (TypeId.SET_NAME.equals(kind)) {
			return SET_NAME_HINT_PREFIX;
		}
		else  if (kind != null) {
			return COLLECTION_NAME_HINT_PREFIX;
		}
		else {
			return getFallBackHint("collection");
		}
	}

//	public @NonNull String getLastResort(boolean isGlobal){
//		return isGlobal ? DEFAULT_GLOBAL_NAME_PREFIX : DEFAULT_LOCAL_NAME_PREFIX;
//	}

	/**
	 * Return a suggestion for the name of anObject.
	 * <p>
	 * The returned name is not guaranteed to be unique. Uniqueness is enforced when the hint is passed to getSymbolName().
	 */
	public @NonNull String getNameHint(@NonNull Object anObject) {
		if (anObject instanceof CGElement) {
			if (anObject instanceof CGProperty) {
				getClass();		// XXX;
			}
		/*	if (anObject instanceof CGValuedElement) {
				CGValuedElement cgValuedElement = ((CGValuedElement)anObject).getNamedValue();
				String name = cgValuedElement.getName();
				if (name != null) {
					return name;
				}
				anObject = cgValuedElement;
			} */
			return ((CGElement)anObject).accept(cgVisitor);
		}
		else if (anObject instanceof Visitable) {
			return ((Visitable)anObject).accept(asVisitor);
		}
		else if (anObject instanceof ElementId) {
			return ((ElementId)anObject).accept(idVisitor);
		}
		else if (anObject instanceof Number) {
			return getNumericNameHint((Number)anObject);
		}
		else if (anObject instanceof String) {
			return getStringNameHint((String)anObject);
		}
		else if (anObject instanceof CollectionValue) {
			String kind = ((CollectionValue)anObject).getKind();
			return getKindHint(kind);
		}
		else if (anObject instanceof IntegerRange) {
			return RANGE_NAME_HINT_PREFIX;
		}
		else if (anObject instanceof IntegerValue) {
			Number numberSymbol = ((IntegerValue)anObject).asNumber();
			return getNumericNameHint(numberSymbol);
		}
		else if (anObject instanceof InvalidValue) {
			return INVALID_NAME_HINT_PREFIX;
		}
		else if (anObject instanceof RealValue) {
			Number numberSymbol = ((RealValue)anObject).asNumber();
			return getNumericNameHint(numberSymbol);
		}
		//		else if (anObject instanceof TypeValue) {
		//			DomainType referredType = ((TypeValue)anObject).getInstanceType();
		//			return getTypeNameHint(referredType);
		//		}
		else if (anObject instanceof Nameable) {
			return getNameableHint((Nameable)anObject);
		}
		else if (anObject == NameManager.NOT_AN_OBJECT) {
			return getFallBackHint("not-an-object");
		}
		else if (anObject == null) {
			return getFallBackHint("null-object");
		}
		else {
			return getFallBackHint("unknown-object " + getClass().getSimpleName() + " : " + anObject.getClass().getSimpleName());
		}
	}

	public @NonNull String getNameableHint(@NonNull Nameable aNameable) {
		String name = aNameable.getName();
	//	assert name != null;		// XXX
		return name != null ? NameManager.getValidJavaIdentifier(name, false, aNameable) : "XXX-null";
	}

	protected @NonNull String getNumericNameHint(@Nullable Number aNumber) {
		if (aNumber != null) {
			@SuppressWarnings("null") @NonNull String string = aNumber.toString();
			if ((aNumber instanceof BigInteger) || (aNumber instanceof Long) || (aNumber instanceof Integer) || (aNumber instanceof Short)) {
				return INTEGER_NAME_HINT_PREFIX + string;
			}
			else if ((aNumber instanceof BigDecimal) || (aNumber instanceof Double) || (aNumber instanceof Float)) {
				return REAL_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, REAL_NAME_HINT_PREFIX.length() > 0, aNumber);
			}
			else if (aNumber instanceof UnlimitedValue) {
				return UNLIMITED_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, UNLIMITED_NAME_HINT_PREFIX.length() > 0, aNumber);
			}
		}
		return getFallBackHint("null-number");
	}

	protected @NonNull String getOperationNameHint(@Nullable Operation anOperation) {
		if (anOperation != null) {
			@SuppressWarnings("null") @NonNull String string = anOperation.toString();
			return OPERATION_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, OPERATION_NAME_HINT_PREFIX.length() > 0, anOperation);
		}
		else {
			return getFallBackHint("operation");
		}
	}

	protected @NonNull String getOperationCallExpNameHint(@Nullable Operation anOperation) {
		if (anOperation != null) {
			@SuppressWarnings("null") @NonNull String string = anOperation.getName();
			return OPERATION_CALL_EXP_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, OPERATION_CALL_EXP_NAME_HINT_PREFIX.length() > 0, anOperation);
		}
		else {
			return getFallBackHint("operation-call");
		}
	}

	protected @NonNull String getPropertyNameHint(@Nullable Property aProperty) {
		if (aProperty != null) {
			@SuppressWarnings("null") @NonNull String string = aProperty.getName();
			return PROPERTY_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, PROPERTY_NAME_HINT_PREFIX.length() > 0, aProperty);
		}
		else {
			return getFallBackHint("property");
		}
	}

	protected @NonNull String getStringNameHint(@Nullable String aString) {
		if (aString != null) {
			@NonNull String string = aString.length() > STRING_NAME_HINT_LIMIT ? aString.substring(0, STRING_NAME_HINT_LIMIT) : aString;
			return STRING_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, STRING_NAME_HINT_PREFIX.length() > 0, aString);
		}
		else {
			return getFallBackHint("string");
		}
	}

	protected @NonNull String getTypeNameHint(@Nullable Type aType) {
		if (aType instanceof CollectionType) {
			if (aType instanceof OrderedSetType) {
				return ORDERED_SET_NAME_HINT_PREFIX;
			}
			else if (aType instanceof SetType) {
				return SET_NAME_HINT_PREFIX;
			}
			else if (aType instanceof SequenceType) {
				return SEQUENCE_NAME_HINT_PREFIX;
			}
			else if (aType instanceof BagType) {
				return BAG_NAME_HINT_PREFIX;
			}
			else {
				return COLLECTION_NAME_HINT_PREFIX;
			}
		}
		else if (aType != null) {
			@SuppressWarnings("null") @NonNull String string = aType.toString();
			return TYPE_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, TYPE_NAME_HINT_PREFIX.length() > 0, aType);
		}
		else {
			return getFallBackHint("type");
		}
	}

	protected @NonNull String getVariableDeclarationNameHint(@Nullable VariableDeclaration aVariableDeclaration) {
		if (aVariableDeclaration != null) {
			String string = ClassUtil.nonNullModel(aVariableDeclaration.getName());
			return VARIABLE_DECLARATION_NAME_HINT_PREFIX + NameManager.getValidJavaIdentifier(string, VARIABLE_DECLARATION_NAME_HINT_PREFIX.length() > 0, aVariableDeclaration);
		}
		else {
			return getFallBackHint("variable");
		}
	}
}
