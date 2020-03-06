/*******************************************************************************
 * Copyright (c) 2013, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.modelspecs;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLocalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNumber;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSettableVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.CatchPlace;
import org.eclipse.ocl.examples.codegen.cse.ControlPlace;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.examples.codegen.cse.IfPlaces;
import org.eclipse.ocl.examples.codegen.cse.InnerStackPlace;
import org.eclipse.ocl.examples.codegen.cse.LetPlaces;
import org.eclipse.ocl.examples.codegen.cse.LocalPlace;
import org.eclipse.ocl.examples.codegen.cse.OuterStackPlace;
import org.eclipse.ocl.examples.codegen.cse.StackPlace;
import org.eclipse.ocl.examples.codegen.cse.ThrowPlace;
import org.eclipse.ocl.examples.codegen.genmodel.MethodSpec;
import org.eclipse.ocl.examples.codegen.genmodel.ModelSpec;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.examples.codegen.utilities.EquivalenceUtil;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * CGValuedElementModelSpec supports generation of the many methods that contribute to the CGValuedElement.xxxx() method hierarchy.
 * <p>
 * The exposition here as a two-dimensional Java table provides a readable, type-checked presentation that would be harder to achieve
 * using a spreadsheet (poor checking) or a model (no 2D tools).
 */
public class CGValuedElementModelSpec extends ModelSpec
{
	protected abstract static class MyMethodSpec extends MethodSpec
	{
		public MyMethodSpec(@NonNull Class<?> rootClass, @NonNull String interfaceDecl, @Nullable String variableDecl, @Nullable String comment) {
			super(rootClass, interfaceDecl, variableDecl, comment);
		}

		@Override
		protected final @Nullable String getBody(@NonNull ModelSpec cgModelSpec, @NonNull GenModel genModel) {
			if (cgModelSpec instanceof CGValuedElementModelSpec) {
				return getBody((CGValuedElementModelSpec)cgModelSpec, genModel);
			}
			else {
				return null;
			}
		}

		protected abstract @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
	}

	/**
	 * The algorithm options for getEcoreClassifier()/isBoxed()/isEcore()/isUnboxed()
	 */
	public interface Box {
		@Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Box ALL = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};

		public static final @NonNull Box BOX = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box DELEG = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getEcoreClassifier() : null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isBoxed();";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isEcore();";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isUnboxed();";
			}
		};

		public static final @NonNull Box E_CNV = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eClassifier;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box E_OP = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eOperation != null ? eOperation.getEType() : null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box E_PRC = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eStructuralFeature != null ? eStructuralFeature.getEType() : null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box E_PRO = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eStructuralFeature != null ? eStructuralFeature.getEContainingClass() : null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box E_SHC = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eClass;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box E_SHD = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return eDataType;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Box IF = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return thenExpression.isBoxed() && elseExpression.isBoxed();";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return thenExpression.isEcore() && elseExpression.isEcore();";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return thenExpression.isUnboxed() && elseExpression.isUnboxed();";
			}
		};

		public static final @NonNull Box PARAM = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (ast instanceof " + classRef(Variable.class) + ") {\n" +
						"			" + classRef(Parameter.class) + " asParameter = ((" + classRef(Variable.class) + ")ast).getRepresentedParameter();\n" +
						"			if (asParameter != null) {\n" +
						"				" + classRef(EObject.class) + " eObject = asParameter.getESObject();\n" +
						"				if (eObject instanceof " + classRef(ETypedElement.class) + ") {\n" +
						"					return ((" + classRef(ETypedElement.class) + ")eObject).getEType();\n" +
						"				}\n" +
						"			}\n" +
						"		}\n" +
						"		return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGCallable.class) + " callable = getCallable();\n" +
						"		return (init != null) ? init.isBoxed() : callable != null ? callable.isBoxed() : true;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGCallable.class) + " callable = getCallable();\n" +
						"		return (init != null) ? init.isEcore() : callable != null ? callable.isEcore() : false;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGCallable.class) + " callable = getCallable();\n" +
						"		return (init != null) ? init.isUnboxed() : callable != null ? callable.isUnboxed() : false;";
			}
		};

		public static final @NonNull Box RANGE = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return isRange() || first.isBoxed();";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return isRange() || first.isEcore();";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return isRange() || first.isUnboxed();";
			}
		};

		public static final @NonNull Box ROOT = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"//		CGValuedElement value = getNamedValue();\n" +
						"		assert referredValue != this : \"isBoxed must be overridden for a \" + getClass().getSimpleName() + \" since referredValue returns this\";\n" +
						"		return referredValue.isBoxed();";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"//		CGValuedElement value = getNamedValue();\n" +
						"		assert referredValue != this : \"isEcore must be overridden for a \" + getClass().getSimpleName() + \" since referredValue returns this\";\n" +
						"		return referredValue.isEcore();";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"//		CGValuedElement value = getNamedValue();\n" +
						"		assert referredValue != this : \"isUnboxed must be overridden for a \" + getClass().getSimpleName() + \" since referredValue returns this\";\n" +
						"		return referredValue.isUnboxed();";
			}
		};

		public static final @NonNull Box UNBOX = new Box() {
			@Override public @Nullable String generateGetEcoreClassifier(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsBoxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsEcore(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @Nullable String generateIsUnboxed(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};

		public static MethodSpec getEcoreClassifier = new MyMethodSpec(CGValuedElement.class, "@Nullable " + classRef(EClassifier.class) + " getEcoreClassifier()", null,
				"Return a non-null EClassifier if this value has a known EClassifier to define its instance class.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Box box = cgModelSpec.box;
				return box != null ? box.generateGetEcoreClassifier(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isBoxed = new MyMethodSpec(CGValuedElement.class, "boolean isBoxed()", null,
				"Return true if this value is a boxed value.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Box box = cgModelSpec.box;
				return box != null ? box.generateIsBoxed(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isEcore = new MyMethodSpec(CGValuedElement.class, "boolean isEcore()", null,
				"Return true if this value is an Ecore value.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Box box = cgModelSpec.box;
				return box != null ? box.generateIsEcore(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isUnboxed = new MyMethodSpec(CGValuedElement.class, "boolean isUnboxed()", null,
				"Return true if this value is an unboxed value.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Box box = cgModelSpec.box;
				return box != null ? box.generateIsUnboxed(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isCommonable()/isUncommonable()
	 */
	public static interface Com {
		@Nullable String generateIsCommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsUncommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Com DELEG = new Com() {
			@Override public @NonNull String generateIsCommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isCommonable();";
			}
			@Override public @NonNull String generateIsUncommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isUncommonable();";
			}
		};
		public static final @NonNull Com FALSE = new Com() {
			@Override public @NonNull String generateIsCommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsUncommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};
		public static final @NonNull Com MAY = new Com() {
			@Override public @NonNull String generateIsCommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateIsUncommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};
		public static final @NonNull Com MUST = new Com() {
			@Override public @NonNull String generateIsCommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateIsUncommonable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static MethodSpec isCommonable = new MyMethodSpec(CGValuedElement.class, "boolean isCommonable()", null,
				"Return true if this value can be shared as part of a Common Subexpression.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Com com = cgModelSpec.com;
				return com != null ? com.generateIsCommonable(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isUncommonable = new MyMethodSpec(CGValuedElement.class, "boolean isUncommonable()", null,
				"Return true if this value does not have to be shared as part of a Common Subexpression.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Com com = cgModelSpec.com;
				return com != null ? com.generateIsUncommonable(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isConstant()
	 */
	public static interface Con {
		@Nullable String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Con CPART = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return first.isConstant() && ((last == null) || last.isConstant());";
		}};
		public static final @NonNull Con DELEG = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isConstant();";
		}};
		public static final @NonNull Con EQUAL = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return source.isConstant() && argument.isConstant();";
		}};
		public static final @NonNull Con FALSE = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};
		public static final @NonNull Con ISKND = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return source.isConstant();";
		}};
		public static final @NonNull Con MPART = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return key.isConstant() && value.isConstant();";
		}};
		public static final @NonNull Con PARTS = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "for (" + cgModelSpec.delegate + " cgPart : getParts()) {\n" +
					"			if (!cgPart.isConstant()) {\n" +
					"				return false;\n" +
					"			}\n" +
					"		}\n" +
					"		return true;";
		}};
		public static final @NonNull Con ROOT = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
					"		return (referredValue != this) && referredValue.isConstant();";
		}};
		public static final @NonNull Con TORF = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return isFalse() || isTrue();";
		}};
		public static final @NonNull Con TRUE = new Con() { @Override public @NonNull String generateIsConstant(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return true;";
		}};

		public static MethodSpec isConstant = new MyMethodSpec(CGValuedElement.class, "boolean isConstant()", null,
				"Return true if this value is a local constant (dependent of the user type system).")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Con con = cgModelSpec.con;
				return con != null ? con.generateIsConstant(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isCaught()/setCaught()
	 */
	public static interface Ct {
		@Nullable String generateIsCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateSetCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Ct FALSE = new Ct() {
			@Override public @NonNull String generateIsCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateSetCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "assert !isCaught;";
			}
		};
		public static final @NonNull Ct ROOT = new Ct() {
			@Override public @NonNull String generateIsCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return caught;";
			}
			@Override public @NonNull String generateSetCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "caught = isCaught;";
			}
		};
		public static final @NonNull Ct TRUE = new Ct() {
			@Override public @NonNull String generateIsCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateSetCaught(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "assert isCaught;\n" +
						"		super.setCaught(true);";
			}
		};

		public static MethodSpec isCaught = new MyMethodSpec(CGValuedElement.class, "boolean isCaught()", null,
				"Returns true if any exception associated with this value has been caught and consequently the value may be an InvalidValueException.\nReturns true if any exception has been thrown.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Ct ct = cgModelSpec.ct;
				return ct != null ? ct.generateIsCaught(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec setCaught = new MyMethodSpec(CGValuedElement.class, "void setCaught(boolean isCaught)", "boolean caught = false",
				"Set the caught status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Ct ct = cgModelSpec.ct;
				return ct != null ? ct.generateSetCaught(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for getPlace()
	 */
	public interface Ctl {
		@NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Ctl BODY = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(OuterStackPlace.class) + ".createOuterStackPlace(element2place, this);";
		}};
		public static final @NonNull Ctl CATCH = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(CatchPlace.class) + ".createCatchPlace(element2place, this);";
		}};
		public static final @NonNull Ctl CNTRL = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ControlPlace.class) + ".createControlPlace(element2place, this);";
		}};
		public static final @NonNull Ctl GLOBL = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(GlobalPlace.class) + ".createGlobalPlace(element2place, this);";
		}};
		public static final @NonNull Ctl IF = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(IfPlaces.class) + ".createIfPlaces(element2place, this);";
		}};
		public static final @NonNull Ctl INNER = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(InnerStackPlace.class) + ".createInnerStackPlace(element2place, this);";
		}};
		public static final @NonNull Ctl LET = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(LetPlaces.class) + ".createLetPlaces(element2place, this);";
		}};
		public static final @NonNull Ctl LORG = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(LocalPlace.class) + ".createLocalPlace(element2place, this);";
		}};
		public static final @NonNull Ctl PARAM = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(StackPlace.class) + ".createStackPlace(element2place, this);";
		}};
		public static final @NonNull Ctl THROW = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ThrowPlace.class) + ".createThrowPlace(element2place, this);";
		}};
		public static final @NonNull Ctl UNSUP = new Ctl() { @Override public @NonNull String generate(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "throw new UnsupportedOperationException();";
		}};

		public static MethodSpec getPlace = new MyMethodSpec(CGElement.class, "@Nullable " + classRef(AbstractPlace.class) + " getPlace(@NonNull " + classRef(Map.class) + "<@Nullable " + classRef(CGElement.class) + ", @NonNull " + classRef(AbstractPlace.class) + "> element2place)", null,
				"Returns the place for this element, updating and reusing element2place as required.\nAll parent elements have entries in element2place.\nNo child elements have entries in element2place.\nThe global place is accessible as the null element.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Ctl ctl = cgModelSpec.ctl;
				return ctl != null ? ctl.generate(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isContext()
	 */
	public static interface Ctx {
		@Nullable String generateIsContext(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Ctx FALSE = new Ctx() { @Override public @NonNull String generateIsContext(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};
		public static final @NonNull Ctx TRUE = new Ctx() { @Override public @NonNull String generateIsContext(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return true;";
		}};

		public static MethodSpec isContext = new MyMethodSpec(CGElement.class, "boolean isContext()", null,
				"Returns true if this node may have its own symbol name context.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Ctx ctx = cgModelSpec.ctx;
				return ctx != null ? ctx.generateIsContext(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for getConstantValue()
	 */
	public static interface Cvl {
		@Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Cvl BOOL = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return booleanValue == true;";
		}};
		public static final @NonNull Cvl EL_ID = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ClassUtil.class) + ".nonNullState(elementId);";
		}};
		public static final @NonNull Cvl INVLD = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ValueUtil.class) + ".INVALID_VALUE;";
		}};
		public static final @NonNull Cvl NULL = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ValueUtil.class) + ".NULL_VALUE;";
		}};
		public static final @NonNull Cvl NUMBR = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ClassUtil.class) + ".nonNullState(numericValue);";
		}};
		public static final @NonNull Cvl ROOT = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return null;
		}};
		public static final @NonNull Cvl STRNG = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ClassUtil.class) + ".nonNullState(stringValue);";
		}};
		public static final @NonNull Cvl TEXT = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ClassUtil.class) + ".nonNullState(textValue);";
		}};
		public static final @NonNull Cvl UNLMT = new Cvl() { @Override public @Nullable String generateGetConstantValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return " + classRef(ValueUtil.class) + ".UNLIMITED_VALUE;";
		}};

		public static MethodSpec getConstantValue = new MyMethodSpec(CGConstant.class, "@NonNull Object getConstantValue()", null,
				"Return the constant (boxed) value of this element.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Cvl cvl = cgModelSpec.cvl;
				return cvl != null ? cvl.generateGetConstantValue(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isEquivalentTo()/isEquivalentToInternal()
	 */
	public static interface Eq {
		@Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Eq AST = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (getClass() == thatValue.getClass()) ? this.ast == thatValue.getAst() : null;";
			}
		};

		public static final @NonNull Eq BOOL = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (this == thatValue) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (booleanValue) {\n" +
						"			if (thatValue.isTrue()) {\n" +
						"				return Boolean.TRUE;\n" +
						"			}\n" +
						"			else if (thatValue.isFalse()) {\n" +
						"				return Boolean.FALSE;\n" +
						"			}\n" +
						"		}\n" +
						"		else {\n" +
						"			if (thatValue.isTrue()) {\n" +
						"				return Boolean.FALSE;\n" +
						"			}\n" +
						"			else if (thatValue.isFalse()) {\n" +
						"				return Boolean.TRUE;\n" +
						"			}\n" +
						"		}\n" +
						"		if (thatValue.isConstant()) {\n" +
						"			return Boolean.FALSE;\n" +
						"		}\n" +
						"		else {\n" +
						"			return null;\n" +
						"		}";
			}
		};
		public static final @NonNull Eq DELEG = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? thatValue.isEquivalentTo(" + cgModelSpec.delegate + ") : super.isEquivalentTo(thatValue);";
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? thatValue.isEquivalentToInternal(" + cgModelSpec.delegate + ") : null;";
			}
		};
		public static final @NonNull Eq EL_ID = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (!thatValue.isNonInvalid()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		CGValuedElement value = thatValue.getNamedValue();\n" +
						"		if (this == value) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (!value.isConstant()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		else if (value instanceof CGElementId) {\n" +
						"			return getASTypeId() == ((CGElementId)thatValue).getASTypeId();\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};
		public static final @NonNull Eq EQUIV = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				String equivalenceUtilImport = ImportUtils.getAffixedName(EquivalenceUtil.class);
				return "return (getClass() == thatValue.getClass()) ? "+ equivalenceUtilImport + ".isEquivalent(this, (" + cgModelSpec.cgClass.getSimpleName() + ")thatValue) : null;";
			}
		};
		public static final @NonNull Eq INVLD = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (thatValue.isInvalid()) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		else if (thatValue.isNonInvalid()) {\n" +
						"			return Boolean.FALSE;\n" +
						"		}\n" +
						//				"		if (thatValue.isConstant()) {\n" +
						//				"			return Boolean.FALSE;\n" +
						//				"		}\n" +
						"		else {\n" +
						"			return null;\n" +
						"		}";
			}
		};

		public static final @NonNull Eq ITRTR = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return thatValue.isEquivalentToInternal(this);";
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (this == thatValue) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		else {\n" +
						"			return null;\n" +
						"		}";
			}
		};
		public static final @NonNull Eq NULL = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (thatValue.isNull()) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		else if (thatValue.isNonNull()) {\n" +
						"			return Boolean.FALSE;\n" +
						"		}\n" +
						//				"		if (thatValue.isConstant()) {\n" +
						//				"			return Boolean.FALSE;\n" +
						//				"		}\n" +
						"		else {\n" +
						"			return null;\n" +
						"		}";
			}
		};
		public static final @NonNull Eq NUMBR = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				String equivalenceUtilImport = ImportUtils.getAffixedName(EquivalenceUtil.class);
				return "if (!thatValue.isNonInvalid()) {\n" +
				"			return null;\n" +
				"		}\n" +
				"		CGValuedElement value = thatValue.getNamedValue();\n" +
				"		if (this == value) {\n" +
				"			return Boolean.TRUE;\n" +
				"		}\n" +
				"		if (!value.isConstant()) {\n" +
				"			return null;\n" +
				"		}\n" +
				"		else if (value instanceof CGNumber) {\n" +
				"			return "+ equivalenceUtilImport + ".isEquivalent(this, (CGNumber)value);\n" +
				"		}\n" +
				"		else {\n" +
				"			return Boolean.FALSE;\n" +
				"		}";
			}
		};

		public static final @NonNull Eq ROOT = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return thatValue.isEquivalentToInternal(this);";
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null; //"throw new UnsupportedOperationException(getClass().getName() + \".isEquivalentToInternal()\");";
			}
		};

		public static final @NonNull Eq SELF = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (this == thatValue) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};

		public static final @NonNull Eq STRNG = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (!thatValue.isNonInvalid()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		CGValuedElement value = thatValue.getNamedValue();\n" +
						"		if (this == value) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (!value.isConstant()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		else if (value instanceof CGString) {\n" +
						"			return stringValue.equals(((CGString)thatValue).getStringValue());\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};

		public static final @NonNull Eq TEXT = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (!thatValue.isNonInvalid()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		CGValuedElement value = thatValue.getNamedValue();\n" +
						"		if (this == value) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (!value.isConstant()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		else if (value instanceof CGText) {\n" +
						"			return textValue.equals(((CGText)thatValue).getTextValue());\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};

		public static final @NonNull Eq TPARM = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (thatValue instanceof CGTemplateParameterExp) {\n" +
						"			return Boolean.FALSE;\n" +
						"		}\n" +
						"		CGTemplateParameterExp thatExp = (CGTemplateParameterExp)thatValue;\n" +
						"		int thatIndex = thatExp.getIndex();\n" +
						"		if (this.index != thatIndex) {\n" +
						"			return Boolean.FALSE;\n" +
						"		}\n" +
						"		return templateableElement.isEquivalentTo(" + classRef(ClassUtil.class) + ".nonNullState(thatExp.getTemplateableElement()));";
			}
		};

		public static final @NonNull Eq TYPE = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (!thatValue.isNonInvalid()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		CGValuedElement value = thatValue.getNamedValue();\n" +
						"		if (this == value) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (!value.isConstant()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		else if (value instanceof CGExecutorType) {\n" +
						"			return getASTypeId() == ((CGExecutorType)thatValue).getASTypeId();\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};

		public static final @NonNull Eq UNLMT = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (!thatValue.isNonInvalid()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		CGValuedElement value = thatValue.getNamedValue();\n" +
						"		if (this == value) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		if (!value.isConstant()) {\n" +
						"			return null;\n" +
						"		}\n" +
						"		else if (value instanceof CGUnlimited) {\n" +
						"			return Boolean.TRUE;\n" +
						"		}\n" +
						"		else {\n" +
						"			return Boolean.FALSE;\n" +
						"		}";
			}
		};
		public static final @NonNull Eq UNSUP = new Eq() {
			@Override public @Nullable String generateIsEquivalentTo(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsEquivalentToInternal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "throw new UnsupportedOperationException(getClass().getName() + \".isEquivalentToInternal()\");";
			}
		};

		public static MethodSpec isEquivalentTo = new MyMethodSpec(CGValuedElement.class, "@Nullable Boolean isEquivalentTo(@NonNull " + classRef(CGValuedElement.class) + " thatValue)", null,
				"Returns true/false if this value can be determined to have deep value equivalence/inequivalence to thatValue, null if no determination can be made.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Eq eq = cgModelSpec.eq;
				return eq != null ? eq.generateIsEquivalentTo(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isEquivalentToInternal = new MyMethodSpec(CGValuedElement.class, "@Nullable Boolean isEquivalentToInternal(@NonNull " + classRef(CGValuedElement.class) + " thatValue)", null,
				"Provided that thatValue does not delegate its equivalence computation, return true/false if this value can be determined to have deep value equivalence/inequivalence to thatValue, null if no determination can be made.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Eq eq = cgModelSpec.eq;
				return eq != null ? eq.generateIsEquivalentToInternal(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isGlobal()
	 */
	public static interface Glo {
		@Nullable String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Glo CPART = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return first.isGlobal() && ((last == null) || last.isGlobal());";
		}};
		public static final @NonNull Glo DELEG = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isGlobal();";
		}};
		public static final @NonNull Glo FALSE = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};
		public static final @NonNull Glo MPART = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return key.isGlobal() && value.isGlobal();";
		}};
		public static final @NonNull Glo PARTS = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "for (" + cgModelSpec.delegate + " cgPart : getParts()) {\n" +
					"			if (!cgPart.isGlobal()) {\n" +
					"				return false;\n" +
					"			}\n" +
					"		}\n" +
					"		return true;";
		}};
		public static final @NonNull Glo ROOT = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "for (" + classRef(CGValuedElement.class) + " cgElement : getDependsOn()) {\n" +
					"			if (!cgElement.isGlobal()) {\n" +
					"				return false;\n" +
					"			}\n" +
					"		}\n" +
					"		" + classRef(CGValuedElement.class) + " referredValue = getNamedValue();\n" +
					"		return (referredValue != this) && referredValue.isGlobal();";
		}};
		public static final @NonNull Glo TRUE = new Glo() { @Override public @NonNull String generateIsGlobal(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return true;";
		}};

		public static MethodSpec isGlobal = new MyMethodSpec(CGValuedElement.class, "boolean isGlobal()", null,
				"Return true if this value is a global constant (independent of the user type system).")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Glo glo = cgModelSpec.glo;
				return glo != null ? glo.generateIsGlobal(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isInlined()
	 */
	public static interface Inl {
		@Nullable String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Inl CPART = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return (last == null) && first.isInlined();";
		}};
		public static final @NonNull Inl FALSE = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};
		public static final @NonNull Inl ISCON = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return isConstant();";
		}};
		public static final @NonNull Inl ROOT = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
					"		return (referredValue != this) && referredValue.isInlined();";
		}};
		public static final @NonNull Inl TRUE = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return true;";
		}};
		public static final @NonNull Inl T_ID = new Inl() { @Override public @NonNull String generateIsInlined(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return (elementId != null) && " + classRef(CGUtil.class) + ".isInlinedId(elementId);";
		}};

		public static MethodSpec isInlined = new MyMethodSpec(CGValuedElement.class, "boolean isInlined()", null,
				"Return true if this value is inlined and so has no local or global declaration.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Inl inl = cgModelSpec.inl;
				return inl != null ? inl.generateIsInlined(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for getInvaliValue()/isInvalid()/isNonInvalid()/setNonInvalid()/setNonInvalidValue()
	 */
	public interface Inv {
		@Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Inv ALWAY = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Inv CPART = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = first.getInvalidValue();\n" +
						"		if ((invalidValue == null) && (last != null)) {\n" +
						"			invalidValue = last.getInvalidValue();\n" +
						"		}\n" +
						"		return invalidValue;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return first.isNonInvalid() && ((last == null) || last.isNonInvalid());";
			}
		};

		public static final @NonNull Inv EQUAL = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = source.getInvalidValue();\n" +
						"		if (invalidValue == null) {\n" +
						"			invalidValue = argument.getInvalidValue();\n" +
						"		}\n" +
						"		return invalidValue;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return source.isNonInvalid() && argument.isNonInvalid();";
			}
		};

		public static final @NonNull Inv GUARD = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = super.getInvalidValue();\n" +
						"		if ((invalidValue == null) && (source != null) && source.isNull()) {\n" +
						"			invalidValue = " + classRef(CGModelFactory.class) + ".eINSTANCE.createCGInvalid();\n" +
						"		}\n" +
						"		return invalidValue;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return super.isNonInvalid() && (source != null) && source.isNonNull();";
			}
		};

		public static final @NonNull Inv IF = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = condition.getInvalidValue();\n" +
						"		if (invalidValue == null) {\n" +
						"			if (condition.isTrue()) {\n" +
						"				invalidValue = thenExpression.getInvalidValue();\n" +
						"			}\n" +
						"			else if (condition.isFalse()) {\n" +
						"				invalidValue = elseExpression.getInvalidValue();\n" +
						"			}\n" +
						"			else {\n" +
						"				invalidValue = elseExpression.getInvalidValue();\n" +
						"				if (invalidValue != null) {				// If both then and else invalid propagate then\n" +
						"					invalidValue = thenExpression.getInvalidValue();\n" +
						"				}\n" +
						"			}\n" +
						"		}\n" +
						"		return invalidValue;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (condition.isTrue()) {\n" +
						"			return thenExpression.isNonInvalid();\n" +
						"		}\n" +
						"		else if (condition.isFalse()) {\n" +
						"			return elseExpression.isNonInvalid();\n" +
						"		}\n" +
						"		else {\n" +
						"			return thenExpression.isNonInvalid() && elseExpression.isNonInvalid();\n" +
						"		}";
			}
		};

		public static final @NonNull Inv ITRTE = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (referredIteration == null) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		if (referredIteration.isIsValidating()) {\n" +
						"			if (referredIteration.isIsInvalidating()) {\n" +
						"				// e.g AND, forAll - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"			else {\n" +
						"				return true;				// e.g oclIsInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		else {\n" +
						"			if (referredIteration.isIsInvalidating()) {\n" +
						"				return false;				// e.g divide-by-zero\n" +
						"			}\n" +
						"			else {\n" +
						"				// normal use case - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		if (!source.isNonNull() || !source.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		for (@NonNull " + classRef(CGValuedElement.class) + " iterator : " + classRef(ClassUtil.class) + ".nullFree(getIterators())) {\n" +
						"			if (!iterator.isNonNull() || !iterator.isNonInvalid()) {\n" +
						"				return false;\n" +
						"			}\n" +
						"		}\n" +
						"		if ((result == null) || !result.isNonNull() || !result.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		if ((body == null) || !body.isNonNull() || !body.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		return true;";
			}
		};

		public static final @NonNull Inv ITRTN = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (referredIteration == null) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		if (referredIteration.isIsValidating()) {\n" +
						"			if (referredIteration.isIsInvalidating()) {\n" +
						"				// e.g AND, forAll - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"			else {\n" +
						"				return true;				// e.g oclIsInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		else {\n" +
						"			if (referredIteration.isIsInvalidating()) {\n" +
						"				return false;				// e.g divide-by-zero\n" +
						"			}\n" +
						"			else {\n" +
						"				// normal use case - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		if (!source.isNonNull() || !source.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		for (@NonNull " + classRef(CGValuedElement.class) + " iterator : " + classRef(ClassUtil.class) + ".nullFree(getIterators())) {\n" +
						"			if (!iterator.isNonNull() || !iterator.isNonInvalid()) {\n" +
						"				return false;\n" +
						"			}\n" +
						"		}\n" +
						"		if ((body == null) || !body.isNonNull() || !body.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		return true;";
			}
		};

		public static final @NonNull Inv MPART = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = key.getInvalidValue();\n" +
						"		if (invalidValue == null) {\n" +
						"			invalidValue = value.getInvalidValue();\n" +
						"		}\n" +
						"		return invalidValue;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return key.isNonInvalid() && value.isNonInvalid();";
			}
		};

		public static final @NonNull Inv NEVER = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return null;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};

		public static final @NonNull Inv OPRTN = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "if (referredOperation == null) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		if (referredOperation.isIsValidating()) {\n" +
						"			if (referredOperation.isIsInvalidating()) {\n" +
						"				// e.g AND, forAll - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"			else {\n" +
						"				return true;				// e.g oclIsInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		else {\n" +
						"			if (referredOperation.isIsInvalidating()) {\n" +
						"				return false;				// e.g divide-by-zero\n" +
						"			}\n" +
						"			else {\n" +
						"				// normal use case - nonInvalid if all inputs nonInvalid\n" +
						"			}\n" +
						"		}\n" +
						"		if (!source.isNonNull() || !source.isNonInvalid()) {\n" +
						"			return false;\n" +
						"		}\n" +
						"		for (@NonNull " + classRef(CGValuedElement.class) + " argument : " + classRef(ClassUtil.class) + ".nullFree(getArguments())) {\n" +
						"			if (!argument.isNonNull() || !argument.isNonInvalid()) {\n" +
						"				return false;\n" +
						"			}\n" +
						"		}\n" +
						"		return true;";
			}
		};

		public static final @NonNull Inv PARTS = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "for (" + cgModelSpec.delegate + " cgPart : getParts()) {\n" +
						"			" + classRef(CGInvalid.class) + " invalidValue = cgPart.getInvalidValue();\n" +
						"			if (invalidValue != null) {\n" +
						"				return invalidValue;\n" +
						"			}\n" +
						"		}\n" +
						"		return null;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "for (" + cgModelSpec.delegate + " cgPart : getParts()) {\n" +
						"			if (!cgPart.isNonInvalid()) {\n" +
						"				return false;\n" +
						"			}\n" +
						"		}\n" +
						"		return true;";
			}
		};

		public static final @NonNull Inv PRPTY = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " source = getSource();\n" +
						"		return source.isNonNull() && source.isNonInvalid();";
			}
		};

		public static final @NonNull Inv ROOT = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " sourceValue = getReferredValue();\n" +
						"		return sourceValue != this ? sourceValue.getInvalidValue() : null;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return getInvalidValue() != null;";
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"		return (referredValue != this) && referredValue.isNonInvalid();";
			}
		};

		public static final @NonNull Inv VAL = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return !nonInvalid ? super.getInvalidValue() : null;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonInvalid;";
			}
		};

		public static final @NonNull Inv VAR = new Inv() {
			@Override public @Nullable String generateGetInvalidValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return !nonInvalid ? super.getInvalidValue() : null;";
			}
			@Override public @Nullable String generateIsInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
			@Override public @Nullable String generateIsNonInvalid(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonInvalid || super.isNonInvalid();";
			}
		};

		public static MethodSpec getInvalidValue = new MyMethodSpec(CGValuedElement.class, "@Nullable " + classRef(CGInvalid.class) + " getInvalidValue()", null,
				"Return a non-null invalid value if this value is invalid.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Inv inv = cgModelSpec.inv;
				return inv != null ? inv.generateGetInvalidValue(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isInvalid = new MyMethodSpec(CGValuedElement.class, "boolean isInvalid()", null,
				"Return true if this value is invalid.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Inv inv = cgModelSpec.inv;
				return inv != null ? inv.generateIsInvalid(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isNonInvalid = new MyMethodSpec(CGValuedElement.class, "boolean isNonInvalid()", null,
				"Return true if this value is not invalid.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Inv inv = cgModelSpec.inv;
				return inv != null ? inv.generateIsNonInvalid(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec setNonInvalid = new MyMethodSpec(CGVariable.class, "void setNonInvalid()", "boolean nonInvalid = false",
				"Set the non-invalid status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "nonInvalid = true;";
			}
		};

		public static MethodSpec setNonInvalidValue = new MyMethodSpec(CGAccumulator.class, "void setNonInvalid(boolean nonInvalid)", "boolean nonInvalid = false",
				"Set the non-invalid status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "this.nonInvalid = nonInvalid;";
			}
		};
	}

	/**
	 * The algorithm options for isFalse()/isTrue()
	 */
	public interface Log {
		@NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Log BOOL = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return booleanValue == false;";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return booleanValue == true;";
			}
		};

		public static final @NonNull Log DELEG = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isConstant() && !" + cgModelSpec.delegate + ".isTrue();";
				// FIXME Why not return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isFalse();";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isTrue();";
			}
		};

		public static final @NonNull Log EQUAL = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && (argument != null) && (source.isEquivalentTo(argument) == Boolean.valueOf(notEquals));";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && (argument != null) && (source.isEquivalentTo(argument) == Boolean.valueOf(!notEquals));";
			}
		};

		public static final @NonNull Log EQUL2 = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && (argument != null) && (source.isEquivalentTo(argument) == Boolean.FALSE);";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && (argument != null) && (source.isEquivalentTo(argument) == Boolean.TRUE);";
			}
		};

		public static final @NonNull Log FALSE = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Log ISINV = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && source.isNonInvalid();";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && source.isInvalid();";
			}
		};

		public static final @NonNull Log ISKND = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				String cgUtilImport = ImportUtils.getAffixedName(CGUtil.class);
				return "return (source != null) && (executorType != null) && (" + cgUtilImport + ".isKindOf(source, executorType) == Boolean.FALSE);";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				String cgUtilImport = ImportUtils.getAffixedName(CGUtil.class);
				return "return (source != null) && (executorType != null) && (" + cgUtilImport + ".isKindOf(source, executorType) == Boolean.TRUE);";
			}
		};

		public static final @NonNull Log ISUND = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && source.isNonInvalid() && source.isNonNull();";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (source != null) && (source.isInvalid() || source.isNull());";
			}
		};

		public static final @NonNull Log ROOT = new Log() {
			@Override public @NonNull String generateIsFalse(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"		return (referredValue != this) && referredValue.isFalse();";
			}
			@Override public @NonNull String generateIsTrue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"		return (referredValue != this) && referredValue.isTrue();";
			}
		};

		public static MethodSpec isFalse = new MyMethodSpec(CGValuedElement.class, "boolean isFalse()", null,
				"Return true if this value is false.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Log log = cgModelSpec.log;
				return log != null ? log.generateIsFalse(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isTrue = new MyMethodSpec(CGValuedElement.class, "boolean isTrue()", null,
				"Return true if this value is true.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Log log = cgModelSpec.log;
				return log != null ? log.generateIsTrue(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isAssertedNonNull()/isNonNull()/isNull()/isUndeclaredNonNull()/setNonNull1()/setNonNull2()/setNonNull3()
	 */
	public interface Nul {
		@NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Nul ALWAY = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
		};

		public static final @NonNull Nul ASSRT = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul CPART = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return first.isNonNull() || ((last != null) && last.isNonNull());";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return first.isNull() && (last == null);";
			}
		};

		public static final @NonNull Nul DELEG = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isIsRequired();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul ECORE = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return isRequired();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul FEAT = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return (" + cgModelSpec.delegate + " != null) && " + cgModelSpec.delegate + ".isIsRequired();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul IF = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return condition.isTrue() ? thenExpression.isNonNull() : condition.isFalse() ? elseExpression.isNonNull() : thenExpression.isNonNull() && elseExpression.isNonNull();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return condition.isTrue() ? thenExpression.isNull() : condition.isFalse() ? elseExpression.isNull() : thenExpression.isNull() && elseExpression.isNull();";
			}
		};

		public static final @NonNull Nul ITER = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonNull || ((referredIteration != null) && referredIteration.isIsRequired());";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul MAYBE = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonNull;";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul NEVER = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return true;";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul OPRTN = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return referredOperation != null ? referredOperation.isIsRequired() : isRequired();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul ROOT = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"		return (referredValue != this) && referredValue.isNonNull();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " referredValue = getReferredValue();\n" +
						"		return (referredValue != this) && referredValue.isNull();";
			}
		};

		public static final @NonNull Nul VAL = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonNull;";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
		};

		public static final @NonNull Nul VAR = new Nul() {
			@Override public @NonNull String generateIsAssertedNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return false;";
			}
			@Override public @NonNull String generateIsNonNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return nonNull || super.isNonNull();";
			}
			@Override public @NonNull String generateIsNull(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return !nonNull && super.isNull();";
			}
		};

		public static MethodSpec isAssertedNonNull = new MyMethodSpec(CGValuedElement.class, "boolean isAssertedNonNull()", null,
				"Return true if this value is not null, possibly with the aid of an assertion.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Nul nul = cgModelSpec.nul;
				return nul != null ? nul.generateIsAssertedNonNull(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isNonNull = new MyMethodSpec(CGValuedElement.class, "boolean isNonNull()", null,
				"Return true if this value is not null.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Nul nul = cgModelSpec.nul;
				return nul != null ? nul.generateIsNonNull(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec isNull = new MyMethodSpec(CGValuedElement.class, "boolean isNull()", null,
				"Return true if this value is null.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Nul nul = cgModelSpec.nul;
				return nul != null ? nul.generateIsNull(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec setNonNull1 = new MyMethodSpec(CGVariable.class, "void setNonNull()", "boolean nonNull = false",
				"Set the non-null status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "nonNull = true;";
			}
		};
		public static MethodSpec setNonNull2 = new MyMethodSpec(CGBuiltInIterationCallExp.class, "void setNonNull()", "boolean nonNull = false",
				"Set the non-null status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "nonNull = true;";
			}
		};
		public static MethodSpec setNonNull3 = new MyMethodSpec(CGNativeProperty.class, "void setNonNull()", "boolean nonNull = false",
				"Set the non-null status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "nonNull = true;";
			}
		};
	}

	/**
	 * The algorithm options for rewriteAs()
	 */
	public static interface Rew {
		@Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Rew PART = new Rew() { @Override public @Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "if (oldValue == executorPart) {\n" +
					"			setExecutorPart((" + classRef(CGExecutorShadowPart.class) + ")newValue);\n" +
					"			return true;\n" +
					"		}\n" +
					"		return false;";
		}};
		public static final @NonNull Rew PROP = new Rew() { @Override public @Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "if (oldValue == executorProperty) {\n" +
					"			setExecutorProperty((" + classRef(CGExecutorProperty.class) + ")newValue);\n" +
					"			return true;\n" +
					"		}\n" +
					"		return false;";
		}};
		public static final @NonNull Rew TYPE = new Rew() { @Override public @Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "if (oldValue == executorType) {\n" +
					"			setExecutorType((" + classRef(CGExecutorType.class) + ")newValue);\n" +
					"			return true;\n" +
					"		}\n" +
					"		return false;";
		}};
		public static final @NonNull Rew UNSUP = new Rew() { @Override public @Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "throw new UnsupportedOperationException(getClass().getName() + \".rewriteAs()\");";
		}};
		public static final @NonNull Rew VAREX = new Rew() { @Override public @Nullable String generateRewriteAs(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};

		public static MethodSpec rewriteAs = new MyMethodSpec(CGElement.class, "boolean rewriteAs(@NonNull " + classRef(CGValuedElement.class) + " oldValue, @NonNull " + classRef(CGValuedElement.class) + " newValue)", null,
				"Rewrite the reference to oldValue by newValue.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Rew rew = cgModelSpec.rew;
				return rew != null ? rew.generateRewriteAs(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for isRange()
	 */
	public static interface Rng {
		public static MethodSpec isRange = new MyMethodSpec(CGCollectionPart.class, "boolean isRange()", null,
				"Return true if this is a collection range.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "return last != null;";
			}
		};
	}

	/**
	 * The algorithm options for isSettable()/setSettable()
	 */
	public static interface Set {
		@Nullable String generateIsSettable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Set FALSE = new Set() { @Override public @Nullable String generateIsSettable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return false;";
		}};
		public static final @NonNull Set TRUE = new Set() { @Override public @Nullable String generateIsSettable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return true;";
		}};
		public static final @NonNull Set VAL = new Set() { @Override public @Nullable String generateIsSettable(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
			return "return settable;";
		}};

		public static MethodSpec isSettable = new MyMethodSpec(CGValuedElement.class, "boolean isSettable()", null,
				"Return true if this value can be inlined as an expression term.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Set set = cgModelSpec.set;
				return set != null ? set.generateIsSettable(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec setSettable = new MyMethodSpec(CGNativeProperty.class, "void setSettable()", "boolean settable = false",
				"Set the settable status.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "settable = true;";
			}
		};
	}

	/**
	 * The algorithm options for getThisValue()
	 */
	public static interface Ths {
		@Nullable String generateGetThisValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Ths CPART = new Ths() {
			@Override public @Nullable String generateGetThisValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = getInvalidValue();\n" +
						"		if (invalidValue != null) {\n" +
						"			return invalidValue;\n" +
						"		}\n" +
						"		else if (last != null) {\n" +
						"			return this;\n" +
						"		}\n" +
						"		else {\n" +
						"			assert first != null;\n" +
						"			return first;\n" +
						"		}";
				/*				return classRef(CGValuedElement.class) + " first2 = first;\n" +
				"		if (first2 != null) {\n" +
				"			if (first2.isInvalid()) {\n" +
				"				return first2;\n" +
				"			}\n" +
				"			" + classRef(CGValuedElement.class) + " last2 = last;\n" +
				"			if (last2 != null) {\n" +
				"				if (last2.isInvalid()) {\n" +
				"					return last2;\n" +
				"				}\n" +
				"			}\n" +
				"			else {\n" +
				"				return first2;\n" +
				"			}\n" +
				"		}\n" +
				"		return this;"; */
			}
		};

		public static final @NonNull Ths DELEG = new Ths() {
			@Override public @Nullable String generateGetThisValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getNamedValue() : this;";
			}
		};

		public static final @NonNull Ths PARTS = new Ths() {
			@Override public @Nullable String generateGetThisValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGInvalid.class) + " invalidValue = getInvalidValue();\n" +
						"		if (invalidValue != null) {\n" +
						"			return invalidValue;\n" +
						"		}\n" +
						"		else {\n" +
						"			return this;\n" +
						"		}";
			}
		};

		public static final @NonNull Ths ROOT = new Ths() {
			@Override public @Nullable String generateGetThisValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				/*				return classRef(CGInvalid.class) + " invalidValue = getInvalidValue();\n" +
				"		if (invalidValue != null) {\n" +
				"			return invalidValue;\n" +
				"		}\n" +
				"		else {\n" +
				"			return this;\n" +
				"		}"; */
				return "return this;";
			}
		};

		public static MethodSpec getThisValue = new MyMethodSpec(CGValuedElement.class, "@NonNull " + classRef(CGValuedElement.class) + " getThisValue()", null,
			"Return a simpler CGValuedElement that is fully equivalent to this element.\n"+
					"May return CGInvalid if this element is invalid.\n"+
					"May return the internal value of a CollectionPart or TuplePart.\n"+
					"May follow a delegation when the delegation has no semantic significance.\n"+
				"Returns this if no simplification occurs.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Ths ths = cgModelSpec.ths;
				return ths != null ? ths.generateGetThisValue(cgModelSpec, genModel) : null;
			}
		};
	}

	/**
	 * The algorithm options for getNamedValue()/getReferredValue()/getSourceValue()/getTypedValue()/getValueName()
	 */
	public interface Val {
		@Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);
		@Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel);

		public static final @NonNull Val DELEG = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getNamedValue() : this;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + " : this;";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getSourceValue() : this;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getTypedValue() : this;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getValueName() : null;";
			}
		};

		public static final @NonNull Val DELNM = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getNamedValue() : this;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + " : this;";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getSourceValue() : this;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getValueName() : null;";
			}
		};

		public static final @NonNull Val DELTY = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + " : this;";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getSourceValue() : this;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return source != null ? source.getTypedValue() : this;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
		};

		public static final @NonNull Val DELVL = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + " : this;";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return " + cgModelSpec.delegate + " != null ? " + cgModelSpec.delegate + ".getSourceValue() : this;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
		};

		public static final @NonNull Val ROOT = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " value = getThisValue();\n" +
						"		return value != this ? value.getNamedValue() : value;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return getThisValue();";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " value = getThisValue();\n" +
						"		return value != this ? value.getSourceValue() : value;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " value = getThisValue();\n" +
						"		return value != this ? value.getTypedValue() : value;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return classRef(CGValuedElement.class) + " value = getThisValue();\n" +
						"		return value != this ? value.getValueName() : valueName;";
			}
		};

		public static final @NonNull Val THIS = new Val() {
			@Override public @Nullable String generateGetNamedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetReferredValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			};
			@Override public @Nullable String generateGetSourceValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			};
			@Override public @Nullable String generateGetTypedValue(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return "return this;";
			}
			@Override public @Nullable String generateGetValueName(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				return null;
			}
		};

		public static MethodSpec getNamedValue = new MyMethodSpec(CGValuedElement.class, "@NonNull " + classRef(CGValuedElement.class) + " getNamedValue()", null,
			"Return the CGValuedElement that provides the name of a declaration from which the value of this CGValuedElement may be obtained.\n"+
					"Fundamental elements such as constants and operation calls provide the named value themselves.\n"+
				"More complex elements such as VariableExp and ThrowExp may delegate.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Val val = cgModelSpec.val;
				return val != null ? val.generateGetNamedValue(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec getReferredValue = new MyMethodSpec(CGValuedElement.class, "@NonNull " + classRef(CGValuedElement.class) + " getReferredValue()", null,
			"Return the CGValuedElement to which the value of this CGValuedElement delegates.\n"+
					"Fundamental elements such as constants and operation calls provide the named value themselves.\n"+
				"More complex elements such as VariableExp and ThrowExp may delegate.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Val val = cgModelSpec.val;
				return val != null ? val.generateGetReferredValue(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec getSourceValue = new MyMethodSpec(CGValuedElement.class, "@NonNull " + classRef(CGValuedElement.class) + " getSourceValue()", null,
			"Return the CGValuedElement which is the source of the information value of this element.\n"+
					"Note that the source value may be differently guarded, caught/thrown and boxed/unboxed to this value.\n"+
				"Returns this if no delegation occurs.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Val val = cgModelSpec.val;
				return val != null ? val.generateGetSourceValue(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec getTypedValue = new MyMethodSpec(CGValuedElement.class, "@NonNull " + classRef(CGValuedElement.class) + " getTypedValue()", null,
			"Return the CGValuedElement that provides the narrowest type declaration from which the type of this CGValuedElement may be obtained.\n"+
					"Fundamental elements such as constants and operations call provide their own narrow type.\n"+
					"More complex elements such as CatchExp have a wide type that includes invalid and so delegate to identify the narrow type.\n"+
				"More complex elements such as GuardExp/ThrowExp propagate an unchanged value with a narrower type than their source.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Val val = cgModelSpec.val;
				return val != null ? val.generateGetTypedValue(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec getValueName = new MyMethodSpec(CGValuedElement.class, "@Nullable String getValueName()", null,
			"Return the declared name value of this element. The text is valid for use in the target language and\n"+
				"unique within the context in which this element is declared.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				Val val = cgModelSpec.val;
				return val != null ? val.generateGetValueName(cgModelSpec, genModel) : null;
			}
		};

		public static MethodSpec setValueName = new MyMethodSpec(CGValuedElement.class, "void setValueName(@NonNull String valueName)", "String valueName = null",
				"Set the name of the value.")
		{
			@Override
			protected @Nullable String getBody(@NonNull CGValuedElementModelSpec cgModelSpec, @NonNull GenModel genModel) {
				if (cgModelSpec.cgClass != rootClass) {
					return null;
				}
				return "this.valueName = valueName;";
			}
		};
	}

	// FIXME why is CGUnboxExpr CON.FALSE
	// FIXME Why isNonNull FEAT isMany
	public static class Register {
		public Register() {
			new CGValuedElementModelSpec(CGElement.class, null,							null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , Ctx.TRUE , Ctl.GLOBL, null     , Rew.UNSUP, null    );

			new CGValuedElementModelSpec(CGValuedElement.class, null,					Box.ROOT , Ths.ROOT , Log.ROOT , Nul.ROOT , Inv.ROOT , Glo.ROOT , Inl.ROOT , Set.FALSE, Ct.ROOT , Con.ROOT , Val.ROOT , null     , Ctx.FALSE, Ctl.CNTRL, Com.MAY  , Rew.VAREX, Eq.ROOT );

			new CGValuedElementModelSpec(CGConstant.class, null,						Box.ALL  , null     , Log.FALSE, Nul.NEVER, Inv.NEVER, Glo.TRUE , Inl.FALSE, null     , null    , Con.TRUE , null     , Cvl.ROOT , null     , null     , Com.FALSE, null     , null    );
			new CGValuedElementModelSpec(CGBoolean.class, "booleanValue",				null     , null     , Log.BOOL , null     , null     , null     , Inl.TRUE , null     , null    , null     , null     , Cvl.BOOL , null     , null     , null     , null     , Eq.BOOL );
			new CGValuedElementModelSpec(CGInvalid.class, null,							null     , null     , null     , null     , Inv.ALWAY, null     , Inl.TRUE , null     , null    , null     , null     , Cvl.INVLD, null     , null     , null     , null     , Eq.INVLD);
			new CGValuedElementModelSpec(CGNull.class, null,							null     , null     , null     , Nul.ALWAY, null     , null     , Inl.TRUE , null     , null    , null     , null     , Cvl.NULL , null     , null     , null     , null     , Eq.NULL );
			new CGValuedElementModelSpec(CGNumber.class, null,							Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , Cvl.NUMBR, null     , null     , Com.MUST , null     , Eq.NUMBR);
			new CGValuedElementModelSpec(CGString.class, null,							null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , Cvl.STRNG, null     , null     , Com.MUST , null     , Eq.STRNG);
			new CGValuedElementModelSpec(CGText.class, null,							null     , null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , null     , null     , Cvl.TEXT , null     , null     , Com.MUST , null     , Eq.TEXT );
			new CGValuedElementModelSpec(CGUnlimited.class, null,						Box.BOX  , null     , null     , null     , null     , null     , Inl.TRUE , null     , null    , null     , null     , Cvl.UNLMT, null     , null     , null     , null     , Eq.UNLMT);
			new CGValuedElementModelSpec(CGElementId.class, null,						Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , Cvl.EL_ID, null     , null     , Com.MUST , null     , Eq.EL_ID);
			new CGValuedElementModelSpec(CGTypeId.class, null,							null     , null     , null     , null     , null     , null     , Inl.T_ID , null     , null    , null     , null     , null     , null     , null     , Com.MUST , null     , null    );

			new CGValuedElementModelSpec(CGCallExp.class, null,							null     , null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGAssertNonNullExp.class, "source",			Box.DELEG, null     , null     , Nul.NEVER, null,      null     , null     , null     , null    , null     , Val.DELNM, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGCastExp.class, "source",	                    Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELVL, null     , null     , Ctl.CNTRL, null     , null     , null    );
			new CGValuedElementModelSpec(CGBoxExp.class, "source",						Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELVL, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGCatchExp.class, "source",					Box.DELEG, null     , null     , null     , null     , null     , null     , Set.TRUE , Ct.TRUE , null     , Val.DELTY, null     , null     , Ctl.CATCH, null     , null     , null    );
			new CGValuedElementModelSpec(CGEcoreExp.class, "source",					Box.E_CNV, null     , null     , null     , null     , null     , null     , null     , null    , Con.FALSE, Val.DELVL, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGGuardExp.class, "source",					Box.DELEG, null     , null     , Nul.NEVER, Inv.GUARD, null     , null     , null     , null    , null     , Val.DELNM, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGIsInvalidExp.class, "source",				Box.ALL  , null     , Log.ISINV, Nul.NEVER, Inv.NEVER, null     , Inl.ISCON, null     , Ct.FALSE, Con.TORF , Val.DELVL, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGIsUndefinedExp.class, "source",				Box.ALL  , null     , Log.ISUND, Nul.NEVER, Inv.NEVER, null     , Inl.ISCON, null     , Ct.FALSE, Con.TORF , Val.DELVL, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGIsEqualExp.class, null,						Box.ALL  , null     , Log.EQUAL, Nul.NEVER, Inv.EQUAL, null     , Inl.FALSE, null     , Ct.ROOT , Con.EQUAL, null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGIsEqual2Exp.class, null,						Box.ALL  , null     , Log.EQUL2, Nul.NEVER, Inv.NEVER, null     , Inl.FALSE, null     , Ct.ROOT , Con.EQUAL, null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGIsKindOfExp.class, null,						Box.ALL  , null     , Log.ISKND, Nul.NEVER, Inv.NEVER, null     , Inl.FALSE, null     , Ct.ROOT , Con.ISKND, null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGThrowExp.class, "source",					Box.DELEG, null     , null     , null     , null     , null     , null     , null     , Ct.FALSE, null     , Val.DELNM, null     , null     , Ctl.THROW, null     , null     , null    );
			new CGValuedElementModelSpec(CGUnboxExp.class, "source",					Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , Con.FALSE, Val.DELVL, null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGIterationCallExp.class, "referredIteration",	null     , null     , null     , Nul.DELEG, Inv.ITRTN, null     , null     , null     , null    , null     , null     , null     , null     , Ctl.INNER, null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGBuiltInIterationCallExp.class, null,			Box.BOX  , null     , null     , Nul.ITER , null     , null     , null     , Set.TRUE , Ct.FALSE, null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGLibraryIterateCallExp.class, null,			Box.BOX  , null     , null     , null     , Inv.ITRTE, null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGLibraryIterationCallExp.class, null,			Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGOperationCallExp.class, "referredOperation",	null     , null     , null     , Nul.OPRTN, Inv.OPRTN, null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGCachedOperationCallExp.class, null,			Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGEcoreOperationCallExp.class, null,			Box.E_OP , null     , null     , Nul.NEVER, null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGExecutorOperationCallExp.class, null,		Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGLibraryOperationCallExp.class, null,			Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGNativeOperationCallExp.class, null,			Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGNavigationCallExp.class, "referredProperty",	null     , null     , null     , Nul.FEAT , Inv.PRPTY, null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGEcoreOppositePropertyCallExp.class, null,	Box.E_PRO, null     , null     , Nul.ECORE, null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGEcorePropertyCallExp.class, null,			Box.E_PRC, null     , null     , Nul.ECORE, null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGExecutorOppositePropertyCallExp.class, null,	Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , Rew.PROP , null    );
			new CGValuedElementModelSpec(CGExecutorPropertyCallExp.class, null,			Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , Rew.PROP , null    );
			new CGValuedElementModelSpec(CGLibraryPropertyCallExp.class, null,			Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGNativePropertyCallExp.class, null,			Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGTuplePartCallExp.class, null,				Box.BOX  , null     , null     , null     , Inv.PRPTY, null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGShadowExp.class, "CGShadowPart",				Box.BOX  , Ths.PARTS, null     , Nul.NEVER, Inv.PARTS, Glo.FALSE, null     , null     , null    , Con.PARTS, null     , null     , null     , null     , Com.FALSE, Rew.TYPE , null    );
			new CGValuedElementModelSpec(CGEcoreClassShadowExp.class, null,				Box.E_SHC, null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , Con.FALSE, null     , null     , null     , null     , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGEcoreDataTypeShadowExp.class, null,			Box.E_SHD, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.EQUIV);

			new CGValuedElementModelSpec(CGVariable.class, "init",						Box.DELEG, null     , null     , Nul.VAR  , Inv.VAR  , null     , null     , null     , null    , null     , Val.DELVL, null     , null     , null     , Com.FALSE, null     , Eq.DELEG    );
			new CGValuedElementModelSpec(CGFinalVariable.class, "init",					null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELEG, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGLocalVariable.class, "init",					null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELEG, null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGSettableVariable.class, null,				null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGParameter.class, "init",						Box.PARAM, null     , null     , null     , Inv.NEVER, Glo.DELEG, null     , null     , null    , Con.DELEG, Val.DELVL, null     , null     , Ctl.PARAM, null     , null     , null    );
			new CGValuedElementModelSpec(CGIterator.class, null,						null     , null     , null     , null     , null     , Glo.FALSE, null     , Set.TRUE , null    , Con.FALSE, Val.ROOT , null     , null     , Ctl.CNTRL, null     , null     , Eq.ITRTR);
			new CGValuedElementModelSpec(CGAccumulator.class, null,						null     , null     , null     , null     , Inv.VAL  , null     , null     , null     , null    , Con.FALSE, Val.ROOT , null     , null     , null     , null     , null     , Eq.ITRTR);

			new CGValuedElementModelSpec(CGCollectionExp.class, "CGCollectionPart",		Box.BOX  , Ths.PARTS, null     , Nul.NEVER, Inv.PARTS, Glo.PARTS, null     , null     , null    , Con.PARTS, null     , null     , null     , Ctl.LORG , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGCollectionPart.class, null,					Box.RANGE, Ths.CPART, null     , Nul.CPART, Inv.CPART, Glo.CPART, Inl.CPART, null     , null    , Con.CPART, null     , null     , null     , null     , Com.FALSE, null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGConstantExp.class, "referredConstant",		Box.DELEG, Ths.DELEG, null     , null     , null     , Glo.DELEG, null     , null     , null    , null     , Val.DELEG, null     , null     , null     , Com.DELEG, null     , Eq.DELEG);
			new CGValuedElementModelSpec(CGExecutorOperation.class, null,				Box.BOX  , null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , Con.TRUE , null     , null     , Ctx.TRUE , null     , Com.MUST , null     , Eq.UNSUP);
			new CGValuedElementModelSpec(CGExecutorProperty.class, null,				Box.BOX  , null     , null     , Nul.NEVER, Inv.NEVER, Glo.FALSE, Inl.FALSE, null     , Ct.FALSE, Con.TRUE , null     , null     , Ctx.TRUE , Ctl.CNTRL, Com.MUST , null     , Eq.UNSUP);
			new CGValuedElementModelSpec(CGExecutorShadowPart.class, null,				Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.AST);
			new CGValuedElementModelSpec(CGExecutorType.class, null,					Box.BOX  , null     , null     , Nul.NEVER, Inv.NEVER, Glo.FALSE, null     , null     , null    , Con.TRUE , null     , null     , null     , null     , Com.MUST , null     , Eq.TYPE );
			new CGValuedElementModelSpec(CGIfExp.class, null,							Box.IF   , null     , null     , Nul.IF   , Inv.IF   , Glo.FALSE, null     , Set.TRUE , null    , null     , null     , null     , null     , Ctl.IF   , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGLetExp.class, "in",							Box.DELEG, null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , null     , Val.DELEG, null     , null     , Ctl.LET  , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGMapExp.class, "CGMapPart",					Box.BOX  , Ths.PARTS, null     , Nul.NEVER, Inv.PARTS, Glo.PARTS, null     , null     , null    , Con.PARTS, null     , null     , null     , Ctl.LORG , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGMapPart.class, null,							Box.BOX  , null     , null     , Nul.NEVER, Inv.MPART, Glo.MPART, null     , null     , null    , Con.MPART, null     , null     , null     , null     , Com.FALSE, null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGShadowPart.class, "init",					Box.BOX  , null     , null     , null     , null     , Glo.FALSE, null     , null     , null    , Con.FALSE, Val.DELEG, null     , null     , null     , Com.FALSE, Rew.PART , Eq.DELEG);

			new CGValuedElementModelSpec(CGCallable.class, null,						null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , Ctx.TRUE , Ctl.BODY , null     , null     , Eq.UNSUP);
			new CGValuedElementModelSpec(CGConstraint.class, null,						Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGOperation.class, null,						null     , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGCachedOperation.class, null,					Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGEcoreOperation.class, null,					Box.E_OP , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGLibraryOperation.class, null,				Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGNativeOperation.class, null,					Box.UNBOX, null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , null    );

			new CGValuedElementModelSpec(CGProperty.class, null,						Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , Ctx.TRUE , Ctl.BODY , null     , null     , Eq.UNSUP);
			new CGValuedElementModelSpec(CGNativeProperty.class, null,					Box.UNBOX, null     , null     , Nul.VAL  , null     , null     , null     , Set.VAL  , null    , null     , null     , null     , null     , null     , null     , null     , null    );
			new CGValuedElementModelSpec(CGTupleExp.class, "CGTuplePart",				Box.BOX  , Ths.PARTS, null     , Nul.NEVER, Inv.PARTS, Glo.PARTS, null     , null     , null    , Con.PARTS, null     , null     , null     , null     , null     , null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGTuplePart.class, "init",						Box.BOX  , null     , null     , null     , null     , null     , Inl.TRUE , null     , null    , null     , Val.DELEG, null     , null     , null     , Com.FALSE, null     , Eq.EQUIV);
			new CGValuedElementModelSpec(CGTemplateParameterExp.class, null,			Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , null     , null     , null     , null     , null     , null     , Eq.TPARM);
			new CGValuedElementModelSpec(CGTypeExp.class, "executorType",				Box.BOX  , null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELEG, null     , null     , null     , null     , Rew.TYPE , Eq.DELEG);
			new CGValuedElementModelSpec(CGVariableExp.class, "referredVariable",		Box.DELEG, null     , null     , null     , null     , null     , null     , null     , null    , null     , Val.DELEG, null     , null     , null     , Com.FALSE, null     , Eq.DELEG);
		}
	}

	protected final @Nullable String delegate;
	protected final @Nullable Box box;
	protected final @Nullable Ths ths;
	protected final @Nullable Log log;
	protected final @Nullable Nul nul;
	protected final @Nullable Inv inv;
	protected final @Nullable Glo glo;
	protected final @Nullable Inl inl;
	protected final @Nullable Set set;
	protected final @Nullable Ct ct;
	protected final @Nullable Con con;
	protected final @Nullable Val val;
	protected final @Nullable Cvl cvl;
	protected final @Nullable Ctx ctx;
	protected final @Nullable Ctl ctl;
	protected final @Nullable Com com;
	protected final @Nullable Rew rew;
	protected final @Nullable Eq eq;

	protected CGValuedElementModelSpec(@NonNull Class<?> cgClass, @Nullable String delegate,
			@Nullable Box box, @Nullable Ths ths, @Nullable Log log, @Nullable Nul nul, @Nullable Inv inv,
			@Nullable Glo glo, @Nullable Inl inl, @Nullable Set set, @Nullable Ct ct, @Nullable Con con,
			@Nullable Val val, @Nullable Cvl cvl, @Nullable Ctx ctx, @Nullable Ctl ctl, @Nullable Com com, @Nullable Rew rew, @Nullable Eq eq) {
		super(cgClass);
		this.delegate = delegate;
		this.box = box;
		this.ths = ths;
		this.log = log;
		this.nul = nul;
		this.inv = inv;
		this.glo = glo;
		this.inl = inl;
		this.set = set;
		this.ct = ct;
		this.con = con;
		this.val = val;
		this.cvl = cvl;
		this.ctx = ctx;
		this.ctl = ctl;
		this.com = com;
		this.rew = rew;
		this.eq = eq;
		assert (inl != Inl.TRUE) || (glo != Glo.TRUE);
		if (glo == Glo.TRUE) assert con == Con.TRUE;
		if (glo == Glo.TRUE) assert inl == Inl.FALSE;

		//		assert (box != null) || (ref == null) : "Box must be defined for '" + cgClass.getSimpleName() + "' since Ref is";
	}

	@Override
	public void generate(@NonNull StringBuilder s, @NonNull GenModel genModel, boolean isImplementation) {
		Cvl.getConstantValue.generate(s, this, genModel, isImplementation);
		Box.getEcoreClassifier.generate(s, this, genModel, isImplementation);
		Inv.getInvalidValue.generate(s, this, genModel, isImplementation);
		Val.getNamedValue.generate(s, this, genModel, isImplementation);
		Ctl.getPlace.generate(s, this, genModel, isImplementation);
		Val.getReferredValue.generate(s, this, genModel, isImplementation);
		Val.getSourceValue.generate(s, this, genModel, isImplementation);
		Ths.getThisValue.generate(s, this, genModel, isImplementation);
		Val.getTypedValue.generate(s, this, genModel, isImplementation);
		Val.getValueName.generate(s, this, genModel, isImplementation);
		Nul.isAssertedNonNull.generate(s, this, genModel, isImplementation);
		Box.isBoxed.generate(s, this, genModel, isImplementation);
		Ct.isCaught.generate(s, this, genModel, isImplementation);
		Com.isCommonable.generate(s, this, genModel, isImplementation);
		Con.isConstant.generate(s, this, genModel, isImplementation);
		Ctx.isContext.generate(s, this, genModel, isImplementation);
		Box.isEcore.generate(s, this, genModel, isImplementation);
		Eq.isEquivalentTo.generate(s, this, genModel, isImplementation);
		Eq.isEquivalentToInternal.generate(s, this, genModel, isImplementation);
		Log.isFalse.generate(s, this, genModel, isImplementation);
		Glo.isGlobal.generate(s, this, genModel, isImplementation);
		Inl.isInlined.generate(s, this, genModel, isImplementation);
		Inv.isInvalid.generate(s, this, genModel, isImplementation);
		Inv.isNonInvalid.generate(s, this, genModel, isImplementation);
		Nul.isNonNull.generate(s, this, genModel, isImplementation);
		Nul.isNull.generate(s, this, genModel, isImplementation);
		Rng.isRange.generate(s, this, genModel, isImplementation);
		Set.isSettable.generate(s, this, genModel, isImplementation);
		Log.isTrue.generate(s, this, genModel, isImplementation);
		Box.isUnboxed.generate(s, this, genModel, isImplementation);
		Com.isUncommonable.generate(s, this, genModel, isImplementation);
		Rew.rewriteAs.generate(s, this, genModel, isImplementation);
		Ct.setCaught.generate(s, this, genModel, isImplementation);
		Inv.setNonInvalid.generate(s, this, genModel, isImplementation);
		Inv.setNonInvalidValue.generate(s, this, genModel, isImplementation);
		Nul.setNonNull1.generate(s, this, genModel, isImplementation);
		Nul.setNonNull2.generate(s, this, genModel, isImplementation);
		Nul.setNonNull3.generate(s, this, genModel, isImplementation);
		Set.setSettable.generate(s, this, genModel, isImplementation);
		Val.setValueName.generate(s, this, genModel, isImplementation);
	}
}
