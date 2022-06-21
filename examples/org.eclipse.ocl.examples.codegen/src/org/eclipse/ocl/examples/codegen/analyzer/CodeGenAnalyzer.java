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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.VirtualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.SpecializedId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A CodeGenAnalyzer performs the analysis of a Pivot AST in preparation for code generation.
 * <p>
 * Pass 1: AS2CGAnalysisVisitor
 * <br>
 * Each AS Element is converted to a CGElement
 * <br>
 * This conversion creates objects such as CGLibraryOperationCallEXp that are more atuned to CG
 * and provides a tree that can be rewritten by optimizations.
 * <p>
 * Pass 2: CGPreAnalysisVisitor
 * <br>
 * Traversal of the CG containment tree performs
 * <br>
 * constant folding
 * <p>
 * <p>
 * Pass N-2: CG2JavaPreVisitor
 * <br>
 * Traversal of the CG containment tree prepares for Java CG by
 * <br>
 * gathering imports
 * <p>
 * Pass N-1: CommonSubexpressionEliminator
 * <br>
 * Traversal of the CG tree to share common terms and remove dead code
 * <p>
 * Pass N: CG2JavaVisitor
 * <br>
 * Traversal of the CG containment tree emits code
 */
public class CodeGenAnalyzer
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull GlobalNameManager globalNameManager;
	private final @NonNull Map<@NonNull ElementId, @NonNull CGElementId> cgElementIds = new HashMap<>();
	protected final @NonNull CGBoolean cgFalse;
	protected final @NonNull CGBoolean cgTrue;
	private /*@LazyNonNull*/ CGUnlimited cgUnlimited = null;
	private /*@LazyNonNull*/ CGInvalid cgInvalid = null;
	protected final @NonNull CGNull cgNull;
	private final @NonNull Map<@NonNull Number, @NonNull CGInteger> cgIntegers = new HashMap<>();
//	private final @NonNull Map<@NonNull Property, @NonNull CGProperty> cgProperties = new HashMap<>();
	private final @NonNull Map<@NonNull Number, @NonNull CGReal> cgReals = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull CGString> cgStrings = new HashMap<>();
	private /*@LazyNonNull*/ Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis = null;

	/**
	 * The root of the generated CG hierarchy.
	 */
	private /*@LazyNonNull*/ CGClass cgRootClass = null;

	/**
	 * The referenced AS Features that are not part of the source hierarchy. Their CG representations are folded into
	 * the CG hierarchy.
	 * </br>
	 * A UniqueList allows recursive discovery of more external Features
	 */
	private /*@LazyNonNull*/ UniqueList<@NonNull Feature> externalFeatures = null;

	/**
	 * Mapping from each AS Class to its corresponding CG Class.
	 */
	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull CGClass> asClass2cgClass = new HashMap<>();

	/**
	 * Mapping from each AS Operation to its corresponding CG Operation.
	 */
	private @NonNull Map<@NonNull Operation, @NonNull CGOperation> asOperation2cgOperation = new HashMap<>();

	/**
	 * Mapping from each AS Property to its corresponding CG Property.
	 */
	private @NonNull Map<@NonNull Property, @NonNull CGProperty> asProperty2cgProperty = new HashMap<>();

	/**
	 * Mapping from each AS Operation that has overrides to its corresponding virtual dispatching CG Operation.
	 */
	private final @NonNull Map<@NonNull Operation, @NonNull CGOperation> asVirtualOperation2cgOperation = new HashMap<>();

	public CodeGenAnalyzer(@NonNull JavaCodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.cgFalse = createCGBoolean(false);
		this.cgTrue = createCGBoolean(true);
		this.cgNull = createCGNull();
	}

	public void addCGOperation(@NonNull CGOperation cgOperation) {
		assert cgOperation.getCallingConvention() != VirtualOperationCallingConvention.INSTANCE;
		Operation asOperation = CGUtil.getAST(cgOperation);
		CGOperation old = asOperation2cgOperation.put(asOperation, cgOperation);
		assert old == null;
		OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
		if (callingConvention.needsGeneration()) {
			assert cgRootClass != null;
			cgRootClass.getOperations().add(cgOperation);
		}
	}

	public void addCGProperty(@NonNull CGProperty cgProperty) {
		Property asProperty = CGUtil.getAST(cgProperty);
		CGProperty old = asProperty2cgProperty.put(asProperty, cgProperty);
		assert old == null;
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		if (callingConvention.needsGeneration()) {
			assert cgRootClass != null;
			cgRootClass.getProperties().add(cgProperty);
		}
	}

	public void addExternalFeature(@NonNull Feature asFeature) {
		UniqueList<@NonNull Feature> externalFeatures2 = externalFeatures;
		if (externalFeatures2 == null) {
			externalFeatures = externalFeatures2 = new UniqueList<>();
		}
		externalFeatures2.add(asFeature);
	}

	public void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globalNameManager.addGlobal(cgGlobal);
	}

	public void addVirtualCGOperation(@NonNull Operation asOperation, @NonNull CGCachedOperation cgOperation) {
	//	assert cgOperation.getAst() == asOperation;
		assert cgOperation.getCallingConvention() == VirtualOperationCallingConvention.INSTANCE;
		CGOperation oldCGOperation = basicGetCGOperation(asOperation);
		assert (oldCGOperation != null) && (oldCGOperation != cgOperation);
		oldCGOperation = asVirtualOperation2cgOperation.put(asOperation, cgOperation);
		assert oldCGOperation == null;
	//	addCGOperation(cgOperation);
		OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
		if (callingConvention.needsGeneration()) {
			assert cgRootClass != null;
			cgRootClass.getOperations().add(cgOperation);
		}
	}

	public void analyze(@NonNull CGElement cgRoot) {
		AnalysisVisitor analysisVisitor = codeGenerator.createAnalysisVisitor();
		cgRoot.accept(analysisVisitor);
		//
		BoxingAnalyzer boxingAnalyzer = codeGenerator.createBoxingAnalyzer();
		cgRoot.accept(boxingAnalyzer);
		//
		FieldingAnalyzer fieldingAnalyzer = codeGenerator.createFieldingAnalyzer();
		fieldingAnalyzer.analyze(cgRoot, false);
	}

	public @Nullable Iterable<@NonNull CGClass> analyzeExternalFeatures(@NonNull AS2CGVisitor as2cgVisitor) {
		UniqueList<@NonNull Feature> externalFeatures = getExternalFeatures();
		if (externalFeatures == null) {
			return null;
		}
		List<@NonNull CGClass> cgExternalClasses = new ArrayList<>();
		Map <@NonNull String, @NonNull CGClass> name2class = new HashMap<>();
		for (int i = 0; i < externalFeatures.size(); i++) {
			@NonNull Feature asExternalFeature = externalFeatures.get(i);
			CGNamedElement cgExternalFeature = asExternalFeature.accept(as2cgVisitor);
			if (cgExternalFeature instanceof CGOperation) {
				CGOperation cgOperation = (CGOperation)cgExternalFeature;
				OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
				CGClass cgParentClass = callingConvention.needsNestedClass() ? createExternalCGClass(cgExternalClasses, name2class, asExternalFeature) : cgRootClass;
				cgParentClass.getOperations().add(cgOperation);
			}
			else if (cgExternalFeature instanceof CGProperty) {
				CGProperty cgProperty = (CGProperty)cgExternalFeature;
				PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
				CGClass cgParentClass = callingConvention.needsNestedClass() ? createExternalCGClass(cgExternalClasses, name2class, asExternalFeature) : cgRootClass;
				cgParentClass.getProperties().add(cgProperty);
			}
			else if (cgExternalFeature != null) {
				throw new UnsupportedOperationException("Expected an external feature rather than a " + cgExternalFeature.getClass().getSimpleName());
			}
		}
		List<CGClass> cgNestedClasses = cgRootClass.getClasses();
		for (@NonNull CGClass cgExternalClass : cgExternalClasses) {
			cgNestedClasses.add(cgExternalClass);
		}
		return cgExternalClasses;
	}

	public @Nullable CGClass basicGetCGClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return asClass2cgClass.get(asClass);
	}

	public @Nullable CGOperation basicGetCGOperation(@NonNull Operation asOperation) {
		return asOperation2cgOperation.get(asOperation);
	}

	public @Nullable CGProperty basicGetCGProperty(@NonNull Property asProperty) {
		return asProperty2cgProperty.get(asProperty);
	}

	public @Nullable CGOperation basicGetVirtualCGOperation(@NonNull Operation asOperation) {
		return asVirtualOperation2cgOperation.get(asOperation);
	}

	public @NonNull CGBoolean createCGBoolean(boolean booleanValue) {
		CGBoolean cgBoolean = CGModelFactory.eINSTANCE.createCGBoolean();
		cgBoolean.setBooleanValue(booleanValue);
		cgBoolean.setTypeId(getCGTypeId(TypeId.BOOLEAN));
		globalNameManager.getNameResolution(cgBoolean);
		return cgBoolean;
	}

	public @NonNull CGCastExp createCGCastExp(@NonNull CGExecutorType cgExecutorType, @NonNull CGValuedElement cgValue) {
		CGCastExp cgCastExp = CGModelFactory.eINSTANCE.createCGCastExp();
		cgCastExp.setSource(cgValue);
		cgCastExp.setExecutorType(cgExecutorType);
		cgCastExp.setTypeId(codeGenerator.getAnalyzer().getCGTypeId(CGUtil.getAST(cgExecutorType).getTypeId()));
		return cgCastExp;
	}

	public @NonNull CGValuedElement createCGConstantExp(@NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(cgConstant.getAst());
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(cgConstant.getTypeId());
		return cgConstantExp;
	}

	public @NonNull CGConstantExp createCGConstantExp(@NonNull TypedElement element, @NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(element);
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(getCGTypeId(element.getTypeId()));
		return cgConstantExp;
	}

	public @NonNull CGIfExp createCGIfExp(@NonNull CGValuedElement cgCondition, @NonNull CGValuedElement cgThenExpression, @NonNull CGValuedElement cgElseExpression) {
		CGIfExp cgIfExp = CGModelFactory.eINSTANCE.createCGIfExp();
		cgIfExp.setCondition(cgCondition);
		cgIfExp.setThenExpression(cgThenExpression);
		cgIfExp.setElseExpression(cgElseExpression);
		cgIfExp.setTypeId(cgThenExpression.getTypeId());		// FIXME common type
		cgIfExp.setRequired(cgThenExpression.isRequired() && cgElseExpression.isRequired());
		return cgIfExp;
	}

	public @NonNull CGValuedElement createCGIsEqual(@NonNull CGValuedElement cgLeft, @NonNull CGValuedElement cgRight) {
		CGIsEqualExp cgIsEqual = CGModelFactory.eINSTANCE.createCGIsEqualExp();
		cgIsEqual.setSource(cgLeft);
		cgIsEqual.setArgument(cgRight);
		cgIsEqual.setTypeId(getCGTypeId(TypeId.BOOLEAN));
		cgIsEqual.setRequired(true);
		return cgIsEqual;
	}

	public @NonNull CGLetExp createCGLetExp(@NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		cgLetExp.setTypeId(cgIn.getTypeId());
		cgLetExp.setRequired(cgIn.isRequired());
		return cgLetExp;
	}

	protected @NonNull CGLetExp createCGLetExp(@NonNull TypedElement asElement, @NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		cgLetExp.setAst(asElement);
		cgLetExp.setTypeId(getCGTypeId(asElement.getTypeId()));
		return cgLetExp;
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {		// XXX @NonNull
	//	assert method != null;
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setMethod(method);		// Use cc
		Operation asOperation = getNativeOperation(method, callingConvention);
		CGOperation cgOperation = getCGOperation(asOperation);
		cgNativeOperationCallExp.setReferredOperation(cgOperation);
	//	callingConvention.createCGOperationCallExp(null, cgOperation, null, cgOperation, null)
		return cgNativeOperationCallExp;
	}

	public @NonNull CGNativePropertyCallExp createCGNativePropertyCallExp(@NonNull Field field, @NonNull PropertyCallingConvention callingConvention) {		// XXX @NonNull
	//	assert method != null;
		CGNativePropertyCallExp cgNativePropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
		cgNativePropertyCallExp.setField(field);		// Use cc
		Property asProperty = getNativeProperty(field, callingConvention);
		CGProperty cgProperty = getCGProperty(asProperty);
		cgNativePropertyCallExp.setReferredProperty(cgProperty);
	//	callingConvention.createCGOperationCallExp(null, cgOperation, null, cgOperation, null)
		return cgNativePropertyCallExp;
	}

	public @NonNull CGNull createCGNull() {
		CGNull cgNull = CGModelFactory.eINSTANCE.createCGNull();
		cgNull.setTypeId(getCGTypeId(TypeId.OCL_VOID));
		globalNameManager.getNameResolution(cgNull);
		return cgNull;
	}

	public @NonNull CGParameter createCGParameter(@NonNull NameResolution nameResolution, @NonNull CGTypeId typeId, boolean isRequired) {
		CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
		nameResolution.addCGElement(cgParameter);
		cgParameter.setTypeId(typeId);
		cgParameter.setRequired(isRequired);
		if (isRequired) {
			cgParameter.setNonNull();
		}
		return cgParameter;
	}

	public @NonNull CGVariableExp createCGVariableExp(@NonNull CGVariable cgVariable) {
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
	//	setAst(cgVariableExp, asVariableExp);
		cgVariableExp.setReferredVariable(cgVariable);
		cgVariableExp.setTypeId(cgVariable.getTypeId());
	//	cgVariableExp.setRequired(cgVariable.getIs);
//		cgVariable.getNameResolution().addCGElement(cgVariableExp);
		return cgVariableExp;
	}

/*	public @NonNull CGExecutorOperation createExecutorOperation(@NonNull Operation asOperation) {
		OperationId operationId = asOperation.getOperationId();
		CGExecutorOperation cgOperation = CGModelFactory.eINSTANCE.createCGExecutorOperation();
		CGElementId cgOperationId = getElementId(operationId);
	//	cgOperation.setTypeId(getTypeId(asOperation.getTypeId()));
		cgOperation.setUnderlyingOperationId(cgOperationId);
		cgOperation.setAst(asOperation);
		globalNameManager.declareLazyName(cgOperation);
		cgOperation.getDependsOn().add(cgOperationId);
		return cgOperation;
	} */

	public @NonNull CGExecutorProperty createExecutorOppositeProperty(@NonNull Property asProperty) {
		PropertyId propertyId = asProperty.getPropertyId();
		CGExecutorProperty cgProperty = null;
		CGElementId cgPropertyId = getCGElementId(propertyId);
		Property asOppositeProperty = ClassUtil.nonNullState(asProperty.getOpposite());
		if (asOppositeProperty.isIsComposite()) {
			cgPropertyId = getCGElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorCompositionProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asOppositeProperty);
			cgProperty.setTypeId(getCGTypeId(JavaConstants.UNBOXED_COMPOSITION_PROPERTY_TYPE_ID));
			globalNameManager.getNameResolution(cgProperty);
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		else {
			cgPropertyId = getCGElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorOppositeProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asProperty);
			globalNameManager.getNameResolution(cgProperty);
			cgProperty.setTypeId(getCGTypeId(JavaConstants.UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID));
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		return cgProperty;
	}

/*	@Deprecated / * @deprecated use callingConvention * /
	public @NonNull CGExecutorProperty createExecutorProperty(@NonNull Property asProperty) {
		assert !asProperty.isIsStatic();			// static is inlined
		// XXX asProperty.esObject == null => ForeignProperty
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = getCGElementId(propertyId);
		CGExecutorProperty cgProperty = CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
		cgProperty.setUnderlyingPropertyId(cgPropertyId);
		cgProperty.setAst(asProperty);
		globalNameManager.declareLazyName(cgProperty);
		TypeId javaPropertyTypeId = JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID;
		cgProperty.setTypeId(getCGTypeId(javaPropertyTypeId));
		cgProperty.getDependsOn().add(cgPropertyId);
		return cgProperty;
	} */

	public @NonNull CGExecutorType createExecutorType(@NonNull Type asType) {
		TypeId typeId = asType.getTypeId();
		CGExecutorType cgType = CGModelFactory.eINSTANCE.createCGExecutorType();
		CGTypeId cgTypeId = getCGTypeId(typeId);
		cgType.setUnderlyingTypeId(cgTypeId);
		cgType.setAst(asType);
	//	getGlobalNameManager().declareLazyName(cgType);
	//	cgType.setTypeId(getTypeId(JavaConstants.CLASS_TYPE_ID));
	//	cgType.setTypeId(getTypeId(asType.getTypeId()));
		getGlobalNameManager().getNameResolution(cgType);
		cgType.setTypeId(getCGTypeId(JavaConstants.CLASS_TYPE_ID));
		cgType.getDependsOn().add(cgTypeId);
		return cgType;
	}

	protected CGClass createExternalCGClass(@NonNull List<@NonNull CGClass> cgExternalClasses,
			@NonNull Map<@NonNull String, @NonNull CGClass> name2class, @NonNull Feature asExternalFeature) {
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		org.eclipse.ocl.pivot.Class asExternalClass = PivotUtil.getOwningClass(asExternalFeature);
		String externalClassName = codeGenerator.getExternalClassName(asExternalClass);
		CGClass cgExternalClass = name2class.get(externalClassName);
		if (cgExternalClass == null) {
			importNameManager.reserveLocalName(externalClassName);
			cgExternalClass = CGModelFactory.eINSTANCE.createCGClass();
			cgExternalClass.setName(externalClassName);
		//	cgStaticClass.setAst(foreignClass);  -- the real class has the AS element
			cgExternalClasses.add(cgExternalClass);
			name2class.put(externalClassName, cgExternalClass);
		}
		return cgExternalClass;
	}

	public boolean equals(@NonNull Element asElement1, @NonNull Element asElement2) {
		ExpressionInOCL asExpressionInOCL1 = PivotUtil.getContainingExpressionInOCL(asElement1);
		ExpressionInOCL asExpressionInOCL2 = PivotUtil.getContainingExpressionInOCL(asElement2);
		if ((asExpressionInOCL1 == null) || (asExpressionInOCL2 == null)) {// || (asExpressionInOCL1 != asExpressionInOCL2)) {
			return false;
		}
		Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis2 = expression2cseAnalsis;
		if (expression2cseAnalsis2 == null) {
			expression2cseAnalsis = expression2cseAnalsis2 = new HashMap<>();
		}
		CommonSubExpressionAnalysis cseAnalysis = expression2cseAnalsis2.get(asExpressionInOCL1);
		if (cseAnalysis == null) {
			cseAnalysis = new CommonSubExpressionAnalysis();
		}
		cseAnalysis.analyze(asExpressionInOCL1);
		cseAnalysis.analyze(asExpressionInOCL2);
		CSEElement cseElement1 = cseAnalysis.getCSEElement(asElement1);
		CSEElement cseElement2 = cseAnalysis.getCSEElement(asElement2);
		return cseElement1 == cseElement2;
	}

	public @NonNull CGBoolean getCGBoolean(boolean aBoolean) {
		return aBoolean ? cgTrue : cgFalse;
	}

	public @NonNull CGClass getCGClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = asClass2cgClass.get(asClass);
		if (cgClass == null) {
			cgClass = CGModelFactory.eINSTANCE.createCGClass();
			cgClass.setAst(asClass);
			cgClass.setName(PivotUtil.getName(asClass));
			asClass2cgClass.put(asClass, cgClass);
			if (cgRootClass == null) {
				cgRootClass = cgClass;
			}
			else {
				cgRootClass.getClasses().add(cgClass);
			}
		}
		else {
			assert cgClass.getAst() == asClass;
		}
		return cgClass;
	}

	public @NonNull CGElementId getCGElementId(@NonNull ElementId elementId) {
		CGElementId cgElementId = cgElementIds.get(elementId);
		if (cgElementId == null) {
			if (elementId instanceof TypeId) {
				return getCGTypeId((TypeId)elementId);
			}
			cgElementId = CGModelFactory.eINSTANCE.createCGElementId();
			cgElementId.setElementId(elementId);
			cgElementId.setTypeId(getCGTypeId(TypeId.OCL_ANY));		// XXX do better
			cgElementIds.put(elementId, cgElementId);
		}
		return cgElementId;
	}

	public @NonNull Iterable<@NonNull CGElementId> getCGElementIds() {
		return cgElementIds.values();
	}

	public @NonNull CGValuedElement getCGExpression(@Nullable CGValuedElement cgExpression) {
		if (cgExpression == null) {
			CGConstantExp cgLiteralExp = CGModelFactory.eINSTANCE.createCGConstantExp();
			//	cgLiteralExp.setAst(element);
			cgLiteralExp.setReferredConstant(getCGInvalid());
			cgLiteralExp.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
			cgExpression = cgLiteralExp;
		};
		return cgExpression;
	}

	public @NonNull CGInteger getCGInteger(@NonNull Number aNumber) {
		CGInteger cgInteger = cgIntegers.get(aNumber);
		if (cgInteger == null) {
			cgInteger = CGModelFactory.eINSTANCE.createCGInteger();
			cgInteger.setNumericValue(aNumber);
			cgInteger.setTypeId(getCGTypeId(TypeId.INTEGER));
			globalNameManager.getNameResolution(cgInteger);
			cgIntegers.put(aNumber, cgInteger);
		}
		return cgInteger;
	}

	public @NonNull CGInvalid getCGInvalid() {
		CGInvalid cgInvalid2 = cgInvalid;
		if (cgInvalid2 == null) {
			cgInvalid2 = CGModelFactory.eINSTANCE.createCGInvalid();
			//	cgInvalid.setAst(ValuesUtil.INVALID_VALUE);
			cgInvalid2.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
			globalNameManager.getNameResolution(cgInvalid2);
			cgInvalid = cgInvalid2;
		}
		return cgInvalid2;
	}

	public @NonNull CGInvalid getCGInvalid(/*@NonNull*/ String messageTemplate, Object... bindings) {
		CGInvalid cgInvalid = CGModelFactory.eINSTANCE.createCGInvalid();
		cgInvalid.setTypeId(getCGTypeId(TypeId.OCL_INVALID));
		cgInvalid.setMessageTemplate(messageTemplate);
		for (Object binding : bindings) {
			cgInvalid.getBindings().add(binding);
		}
		globalNameManager.getNameResolution(cgInvalid);
		return cgInvalid;
	}

	//	public @NonNull NameManager getNameManager() {
//		return ClassUtil.nonNullState(nameManager);
//	}

	public @NonNull CGNull getCGNull() {
		return cgNull;
	}

	public @NonNull CGOperation getCGOperation(@NonNull Operation asOperation) {
		return ClassUtil.nonNullState(asOperation2cgOperation.get(asOperation));
	}

	public @NonNull CGProperty getCGProperty(@NonNull Property asProperty) {
		return ClassUtil.nonNullState(asProperty2cgProperty.get(asProperty));
	}

	public @NonNull CGReal getCGReal(@NonNull Number aNumber) {
		CGReal cgReal = cgReals.get(aNumber);
		if (cgReal == null) {
			cgReal = CGModelFactory.eINSTANCE.createCGReal();
			cgReal.setNumericValue(aNumber);
			cgReal.setTypeId(getCGTypeId(TypeId.REAL));
			globalNameManager.getNameResolution(cgReal);
			cgReals.put(aNumber, cgReal);
		}
		return cgReal;
	}

	public @NonNull CGString getCGString(@NonNull String aString) {
		CGString cgString = cgStrings.get(aString);
		if (cgString == null) {
			cgString = CGModelFactory.eINSTANCE.createCGString();
			cgString.setStringValue(aString);
			cgString.setTypeId(getCGTypeId(TypeId.STRING));
			globalNameManager.getNameResolution(cgString);
		//	globalNameManager.declareLazyName(cgString);
			cgStrings.put(aString, cgString);
		}
		return cgString;
	}

	public @NonNull CGTypeId getCGTypeId(@NonNull TypeId typeId) {
		CGElementId cgElementId = cgElementIds.get(typeId);
		CGTypeId cgTypeId = (CGTypeId)cgElementId;
		if (cgTypeId == null) {
			cgTypeId = CGModelFactory.eINSTANCE.createCGTypeId();
			cgTypeId.setElementId(typeId);
			globalNameManager.getNameResolution(cgTypeId);
		//	globalNameManager.declareLazyName(cgTypeId);
			cgElementIds.put(typeId, cgTypeId);
			if (typeId instanceof SpecializedId) {
				BindingsId templateBindings = ((SpecializedId)typeId).getTemplateBindings();
				for (int i = 0; i < templateBindings.elementIdSize(); i++) {
					ElementId elementId = templateBindings.getElementId(i);
					getCGElementId(elementId);
				}
			}
			cgTypeId.setTypeId(getCGTypeId(TypeId.OCL_ANY)); // XXX better tyoe ??
		}
		return cgTypeId;
	}

	public @NonNull CGUnlimited getCGUnlimited() {
		CGUnlimited cgUnlimited2 = cgUnlimited;
		if (cgUnlimited2 == null) {
			cgUnlimited2 = CGModelFactory.eINSTANCE.createCGUnlimited();
			cgUnlimited2.setTypeId(getCGTypeId(TypeId.UNLIMITED_NATURAL));
		//	globalNameManager.declareLazyName(cgUnlimited2); -- inlined so missing AST etc ok
			cgUnlimited = cgUnlimited2;
		}
		return cgUnlimited2;
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	public @Nullable UniqueList<@NonNull Feature> getExternalFeatures() {
		return externalFeatures;
	}

	public GenModelHelper getGenModelHelper() {
		return codeGenerator.getGenModelHelper();
	}

	public @NonNull GlobalNameManager getGlobalNameManager() {
		return ClassUtil.nonNullState(globalNameManager);
	}

	private @NonNull JavaLanguageSupport getJavaLanguageSupport() {
		return (JavaLanguageSupport)ClassUtil.nonNullState(codeGenerator.getEnvironmentFactory().getLanguageSupport("java"));
	}

	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return codeGenerator.getEnvironmentFactory().getMetamodelManager();
	}

	/*
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method method) {
		return getJavaLanguageSupport().getNativeOperation(method);
	}

	/*
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {
		Operation asOperation = getNativeOperation(method);
		CGOperation cgOperation = asOperation2cgOperation.get(asOperation);
		if (cgOperation == null) {
			CGNativeOperation cgNativeOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();
			cgNativeOperation.setAst(asOperation);
			TypeId asTypeId = asOperation.getTypeId();
			globalNameManager.getNameResolution(cgNativeOperation);
			cgNativeOperation.setTypeId(getCGTypeId(asTypeId));
			cgNativeOperation.setRequired(asOperation.isIsRequired());
			cgNativeOperation.setCallingConvention(callingConvention);
			cgNativeOperation.setAst(asOperation);
			/*NestedNameManager nameManager =*/ globalNameManager.createNestedNameManager(null, cgNativeOperation);
			List<CGParameter> cgParameters = cgNativeOperation.getParameters();
			for (org.eclipse.ocl.pivot.Parameter asParameter : asOperation.getOwnedParameters()) {
				Type asParameterType = asParameter.getType();
				boolean isRequired = asParameter.isIsRequired();
				CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
				cgParameter.setAst(asParameter);
//				nameManager.declarePreferredName(cgParameter);
				cgParameter.setTypeId(getCGTypeId(asParameterType.getTypeId()));
				cgParameter.setRequired(isRequired);
				cgParameters.add(cgParameter);
			}
			addCGOperation(cgNativeOperation);		// XXX Use installOperation and then inline addCGOperation
		}
		return asOperation;
	}

	/*
	 * Return a native property for field.
	 */
	public @NonNull Property getNativeProperty(@NonNull Field field) {
		return getJavaLanguageSupport().getNativeProperty(field);
	}

	/*
	 * Return a native property for field.
	 */
	public @NonNull Property getNativeProperty(@NonNull Field field, @NonNull PropertyCallingConvention callingConvention) {
		Property asProperty = getNativeProperty(field);
		CGProperty cgProperty = asProperty2cgProperty.get(asProperty);
		if (cgProperty == null) {
			CGNativeProperty cgNativeProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
			cgNativeProperty.setAst(asProperty);
			TypeId asTypeId = asProperty.getTypeId();
			globalNameManager.getNameResolution(cgNativeProperty);
			cgNativeProperty.setTypeId(getCGTypeId(asTypeId));
			cgNativeProperty.setRequired(asProperty.isIsRequired());
			cgNativeProperty.setCallingConvention(callingConvention);
			cgNativeProperty.setAst(asProperty);
			/*NestedNameManager nameManager =*/ globalNameManager.createNestedNameManager(null, cgNativeProperty);
			addCGProperty(cgNativeProperty);		// XXX Use installProperty and then inline addCGProperty
		}
		return asProperty;
	}

	public boolean hasOclVoidOperation(@NonNull OperationId operationId) {
		PivotMetamodelManager metamodelManager = codeGenerator.getEnvironmentFactory().getMetamodelManager();
		CompleteClass completeClass = metamodelManager.getCompleteClass(metamodelManager.getStandardLibrary().getOclVoidType());
		Operation memberOperation = completeClass.getOperation(operationId);
		if (memberOperation == null) {
			return false;
		}
		org.eclipse.ocl.pivot.Class owningType = memberOperation.getOwningClass();
		if (owningType == null) {
			return false;
		}
		CompleteClass owningCompleteClass = metamodelManager.getCompleteClass(owningType);
		return completeClass == owningCompleteClass;
	}

	public @NonNull CGOperation installOperation(@NonNull Operation asOperation, @NonNull CGOperation cgOperation, @NonNull OperationCallingConvention callingConvention) {
		assert cgOperation.getAst() == null;
		assert cgOperation.getCallingConvention() == null;
//		System.out.println("installOperation " + callingConvention.getClass().getSimpleName() + " " + NameUtil.debugSimpleName(cgOperation) + " " + NameUtil.debugSimpleName(asOperation) + " : " + asOperation);
		cgOperation.setAst(asOperation);
		cgOperation.setTypeId(getCGTypeId(asOperation.getTypeId()));
		cgOperation.setRequired(asOperation.isIsRequired());
		cgOperation.setCallingConvention(callingConvention);
		if (callingConvention == VirtualOperationCallingConvention.INSTANCE) {		// XXX move to OperationCallingConvention
			addVirtualCGOperation(asOperation, (CGCachedOperation)cgOperation);
		}
		else {
			addCGOperation(cgOperation);
		}
		return cgOperation;
	}

	public boolean isExternal(@NonNull Feature asFeature) {
		return (externalFeatures != null) && externalFeatures.contains(asFeature);
	}

	/**
	 * Replace oldElement by newElement and return oldElement which is orphaned by the replacement.
	 */
	public @NonNull CGValuedElement replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement,
			/*@NonNull*/ String messageTemplate, Object... bindings) {
		if (oldElement.isRequired() && newElement.isNull()) {
			newElement = getCGInvalid(messageTemplate, bindings);
		}
		return CGUtil.replace(oldElement, newElement);
	}

	public void setCGConstant(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement aConstant) {
		CGConstantExp newElement = CGModelFactory.eINSTANCE.createCGConstantExp();		// FIXME wrapper not needed
		newElement.setReferredConstant(aConstant);
		newElement.setTypeId(oldElement.getTypeId());
		newElement.setAst(oldElement.getAst());
		CGUtil.replace(oldElement, newElement);
	}

	public void setCGRootClass(@NonNull CGClass cgClass) {
		assert cgRootClass == null;
		cgRootClass = cgClass;
	}
}
