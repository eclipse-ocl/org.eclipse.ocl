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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
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
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
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
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
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
	protected final @NonNull CodeGenerator codeGenerator;
	protected final @NonNull GlobalNameManager globalNameManager;
	private final @NonNull Map<@NonNull ElementId, @NonNull CGElementId> cgElementIds = new HashMap<>();
	protected final @NonNull CGBoolean cgFalse;
	protected final @NonNull CGBoolean cgTrue;
	private /*@LazyNonNull*/ CGUnlimited cgUnlimited = null;
	private /*@LazyNonNull*/ CGInvalid cgInvalid = null;
	protected final @NonNull CGNull cgNull;
	private /*@LazyNonNull*/ CGClass cgRootClass = null;
	private final @NonNull Map<@NonNull Number, @NonNull CGInteger> cgIntegers = new HashMap<>();
	private final @NonNull Map<@NonNull Number, @NonNull CGReal> cgReals = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull CGString> cgStrings = new HashMap<>();
	private /*@LazyNonNull*/ Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis = null;
	// UniqueList allows recursive discovery of more foreign Features
	private /*@LazyNonNull*/ UniqueList<@NonNull Feature> foreignFeatures = null;
	private @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull CGClass> asClass2cgClass = new HashMap<>();
	private @NonNull Map<@NonNull Operation, @NonNull CGOperation> asOperation2cgOperation = new HashMap<>();
	private @NonNull Map<@NonNull Property, @NonNull CGProperty> asProperty2cgProperty = new HashMap<>();

	public CodeGenAnalyzer(@NonNull CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.cgFalse = createCGBoolean(false);
		this.cgTrue = createCGBoolean(true);
		this.cgNull = createCGNull();
	}

	public void addForeignFeature(@NonNull Feature asFeature) {
		UniqueList<@NonNull Feature> foreignFeatures2 = foreignFeatures;
		if (foreignFeatures2 == null) {
			foreignFeatures = foreignFeatures2 = new UniqueList<>();
		}
		foreignFeatures2.add(asFeature);
	}

	public void addCGClass(@NonNull CGClass cgClass) {
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		CGClass old = asClass2cgClass.put(asClass, cgClass);
		assert old == null;
		assert cgRootClass != null;
		cgRootClass.getClasses().add(cgClass);
	}

	public void addCGOperation(@NonNull CGOperation cgOperation) {
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

	public @Nullable Iterable<@NonNull CGClass> analyzeForeignFeatures(@NonNull AS2CGVisitor as2cgVisitor) {
		UniqueList<@NonNull Feature> foreignFeatures = getForeignFeatures();
		if (foreignFeatures == null) {
			return null;
		}
		List<@NonNull CGClass> cgForeignClasses = new ArrayList<>();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		Map <@NonNull String, @NonNull CGClass> name2class = new HashMap<>();
		for (int i = 0; i < foreignFeatures.size(); i++) {
			@NonNull Feature foreignFeature = foreignFeatures.get(i);
			org.eclipse.ocl.pivot.Class foreignClass = PivotUtil.getOwningClass(foreignFeature);
			String foreignClassName = codeGenerator.getForeignClassName(foreignClass);
			CGClass cgStaticClass = name2class.get(foreignClassName);
			if (cgStaticClass == null) {
				importNameManager.reserveLocalName(foreignClassName);
				cgStaticClass = CGModelFactory.eINSTANCE.createCGClass();
				cgStaticClass.setName(foreignClassName);
			//	cgStaticClass.setAst(foreignClass);  -- the real class has the AS element
				cgForeignClasses.add(cgStaticClass);
				name2class.put(foreignClassName, cgStaticClass);
			}
			CGNamedElement cgForeignFeature = foreignFeature.accept(as2cgVisitor);
			if (cgForeignFeature instanceof CGOperation) {
				cgStaticClass.getOperations().add((CGOperation) cgForeignFeature);
			}
			else if (cgForeignFeature instanceof CGProperty) {
				cgStaticClass.getProperties().add((CGProperty) cgForeignFeature);
			}
			else if (cgForeignFeature != null) {
				throw new UnsupportedOperationException("Expected a foreign feature rather than a " + cgForeignFeature.getClass().getSimpleName());
			}
		}
		return cgForeignClasses;
	}

	public @Nullable CGClass basicGetClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return asClass2cgClass.get(asClass);
	}

	public @Nullable CGOperation basicGetOperation(@NonNull Operation asOperation) {
		return asOperation2cgOperation.get(asOperation);
	}

	public @Nullable CGProperty basicGetProperty(@NonNull Property asProperty) {
		return asProperty2cgProperty.get(asProperty);
	}

	public @NonNull CGBoolean createCGBoolean(boolean booleanValue) {
		CGBoolean cgBoolean = CGModelFactory.eINSTANCE.createCGBoolean();
		cgBoolean.setBooleanValue(booleanValue);
		cgBoolean.setTypeId(getTypeId(TypeId.BOOLEAN));
		globalNameManager.declareLazyName(cgBoolean);
		return cgBoolean;
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
		cgConstantExp.setTypeId(getTypeId(element.getTypeId()));
		return cgConstantExp;
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {		// XXX @NonNull
		assert method != null;
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setMethod(method);		// Use cc
		Operation asOperation = getNativeOperation(method, callingConvention);
		CGOperation cgOperation = getOperation(asOperation);
		cgNativeOperationCallExp.setCgOperation(cgOperation);
	//	callingConvention.createCGOperationCallExp(null, cgOperation, null, cgOperation, null)
		return cgNativeOperationCallExp;
	}

	private @NonNull JavaLanguageSupport getJavaLanguageSupport() {
		return (JavaLanguageSupport)ClassUtil.nonNullState(codeGenerator.getEnvironmentFactory().getLanguageSupport("java"));
	}

	public @NonNull CGNull createCGNull() {
		CGNull cgNull = CGModelFactory.eINSTANCE.createCGNull();
		cgNull.setTypeId(getTypeId(TypeId.OCL_VOID));
		globalNameManager.declareLazyName(cgNull);
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
		cgVariable.getNameResolution().addCGElement(cgVariableExp);
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
		CGElementId cgPropertyId = getElementId(propertyId);
		Property asOppositeProperty = ClassUtil.nonNullState(asProperty.getOpposite());
		if (asOppositeProperty.isIsComposite()) {
			cgPropertyId = getElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorCompositionProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asOppositeProperty);
			cgProperty.setTypeId(getTypeId(JavaConstants.UNBOXED_COMPOSITION_PROPERTY_TYPE_ID));
			globalNameManager.declareLazyName(cgProperty);
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		else {
			cgPropertyId = getElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorOppositeProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asProperty);
			globalNameManager.declareLazyName(cgProperty);
			cgProperty.setTypeId(getTypeId(JavaConstants.UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID));
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		return cgProperty;
	}

	public @NonNull CGExecutorProperty createExecutorProperty(@NonNull Property asProperty) {
		assert !asProperty.isIsStatic();			// static is inlined
		// XXX asProperty.esObject == null => ForeignProperty
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = getElementId(propertyId);
		CGExecutorProperty cgProperty = CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
		cgProperty.setUnderlyingPropertyId(cgPropertyId);
		cgProperty.setAst(asProperty);
		globalNameManager.declareLazyName(cgProperty);
		TypeId javaPropertyTypeId = JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID;
		cgProperty.setTypeId(getTypeId(javaPropertyTypeId));
		cgProperty.getDependsOn().add(cgPropertyId);
		return cgProperty;
	}

	public @NonNull CGExecutorShadowPart createExecutorShadowPart(@NonNull Property asProperty) {
		PropertyId propertyId = asProperty.getPropertyId();
		CGExecutorShadowPart cgPart = CGModelFactory.eINSTANCE.createCGExecutorShadowPart();
		CGElementId cgPropertyId = getElementId(propertyId);
		cgPart.setUnderlyingPropertyId(cgPropertyId);
		cgPart.setAst(asProperty);
		globalNameManager.declareLazyName(cgPart);
		cgPart.setTypeId(getTypeId(JavaConstants.PROPERTY_TYPE_ID));
		cgPart.getDependsOn().add(cgPropertyId);
		return cgPart;
	}

	public @NonNull CGExecutorType createExecutorType(@NonNull Type asType) {
		TypeId typeId = asType.getTypeId();
		CGExecutorType cgType = CGModelFactory.eINSTANCE.createCGExecutorType();
		CGTypeId cgTypeId = getTypeId(typeId);
		cgType.setUnderlyingTypeId(cgTypeId);
		cgType.setAst(asType);
		getGlobalNameManager().declareLazyName(cgType);
		cgType.setTypeId(getTypeId(JavaConstants.CLASS_TYPE_ID));
		cgType.getDependsOn().add(cgTypeId);
		return cgType;
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

	public @NonNull CGBoolean getBoolean(boolean aBoolean) {
		return aBoolean ? cgTrue : cgFalse;
	}

	public @NonNull CGClass getClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nonNullState(asClass2cgClass.get(asClass));
	}

	public @NonNull CodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	public @NonNull CGElementId getElementId(@NonNull ElementId elementId) {
		CGElementId cgElementId = cgElementIds.get(elementId);
		if (cgElementId == null) {
			if (elementId instanceof TypeId) {
				return getTypeId((TypeId)elementId);
			}
			cgElementId = CGModelFactory.eINSTANCE.createCGElementId();
			cgElementId.setElementId(elementId);
			cgElementId.setTypeId(getTypeId(TypeId.OCL_ANY));		// XXX do better
			cgElementIds.put(elementId, cgElementId);
		}
		return cgElementId;
	}

	public @NonNull CGValuedElement getExpression(@Nullable CGValuedElement cgExpression) {
		if (cgExpression == null) {
			CGConstantExp cgLiteralExp = CGModelFactory.eINSTANCE.createCGConstantExp();
			//	cgLiteralExp.setAst(element);
			cgLiteralExp.setReferredConstant(getInvalid());
			cgLiteralExp.setTypeId(getTypeId(TypeId.OCL_INVALID));
			cgExpression = cgLiteralExp;
		};
		return cgExpression;
	}

	public @Nullable UniqueList<@NonNull Feature> getForeignFeatures() {
		return foreignFeatures;
	}

	public @NonNull GlobalNameManager getGlobalNameManager() {
		return ClassUtil.nonNullState(globalNameManager);
	}

	public @NonNull CGInteger getInteger(@NonNull Number aNumber) {
		CGInteger cgInteger = cgIntegers.get(aNumber);
		if (cgInteger == null) {
			cgInteger = CGModelFactory.eINSTANCE.createCGInteger();
			cgInteger.setNumericValue(aNumber);
			cgInteger.setTypeId(getTypeId(TypeId.INTEGER));
			globalNameManager.declareLazyName(cgInteger);
			cgIntegers.put(aNumber, cgInteger);
		}
		return cgInteger;
	}

	public @NonNull CGInvalid getInvalid() {
		CGInvalid cgInvalid2 = cgInvalid;
		if (cgInvalid2 == null) {
			cgInvalid2 = CGModelFactory.eINSTANCE.createCGInvalid();
			//	cgInvalid.setAst(ValuesUtil.INVALID_VALUE);
			cgInvalid2.setTypeId(getTypeId(TypeId.OCL_INVALID));
			globalNameManager.declareLazyName(cgInvalid2);
			cgInvalid = cgInvalid2;
		}
		return cgInvalid2;
	}

	public @NonNull CGInvalid getInvalid(/*@NonNull*/ String messageTemplate, Object... bindings) {
		CGInvalid cgInvalid = CGModelFactory.eINSTANCE.createCGInvalid();
		cgInvalid.setTypeId(getTypeId(TypeId.OCL_INVALID));
		cgInvalid.setMessageTemplate(messageTemplate);
		for (Object binding : bindings) {
			cgInvalid.getBindings().add(binding);
		}
		globalNameManager.declareLazyName(cgInvalid);
		return cgInvalid;
	}

	/*
	 * Return a native operation for method flattening the signature into the name.
	 */
	public @NonNull Operation getNativeOperation(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {
		Operation asOperation = getJavaLanguageSupport().getNativeOperation(method);
		CGOperation cgOperation = asOperation2cgOperation.get(asOperation);
		if (cgOperation == null) {
			CGNativeOperation cgNativeOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();
			cgNativeOperation.setAst(asOperation);
			TypeId asTypeId = asOperation.getTypeId();
			globalNameManager.declareLazyName(cgNativeOperation);
			cgNativeOperation.setTypeId(getTypeId(asTypeId));
			cgNativeOperation.setRequired(asOperation.isIsRequired());
			cgNativeOperation.setCallingConvention(callingConvention);
			cgNativeOperation.setAst(asOperation);
			JavaGlobalContext<?> globalContext = (JavaGlobalContext<?>)codeGenerator.getGlobalContext();
			LocalContext localContext = globalContext.initLocalContext(null, cgNativeOperation, asOperation);
			List<CGParameter> cgParameters = cgNativeOperation.getParameters();
			NestedNameManager nameManager = localContext.getNameManager();
			for (org.eclipse.ocl.pivot.Parameter asParameter : asOperation.getOwnedParameters()) {
				Type asParameterType = asParameter.getType();
				boolean isRequired = asParameter.isIsRequired();
				CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
				cgParameter.setAst(asParameter);
				nameManager.declarePreferredName(cgParameter);
				cgParameter.setTypeId(getTypeId(asParameterType.getTypeId()));
				cgParameter.setRequired(isRequired);
				cgParameters.add(cgParameter);
			}
			addCGOperation(cgNativeOperation);
		}
		return asOperation;
	}

//	public @NonNull NameManager getNameManager() {
//		return ClassUtil.nonNullState(nameManager);
//	}

	public @NonNull CGNull getNull() {
		return cgNull;
	}

	public @NonNull CGOperation getOperation(@NonNull Operation asOperation) {
		return ClassUtil.nonNullState(asOperation2cgOperation.get(asOperation));
	}

	public @NonNull CGProperty getProperty(@NonNull Property asProperty) {
		return ClassUtil.nonNullState(asProperty2cgProperty.get(asProperty));
	}

	public @NonNull CGReal getReal(@NonNull Number aNumber) {
		CGReal cgReal = cgReals.get(aNumber);
		if (cgReal == null) {
			cgReal = CGModelFactory.eINSTANCE.createCGReal();
			cgReal.setNumericValue(aNumber);
			cgReal.setTypeId(getTypeId(TypeId.REAL));
			globalNameManager.declareLazyName(cgReal);
			cgReals.put(aNumber, cgReal);
		}
		return cgReal;
	}

	public @NonNull CGString getString(@NonNull String aString) {
		CGString cgString = cgStrings.get(aString);
		if (cgString == null) {
			cgString = CGModelFactory.eINSTANCE.createCGString();
			cgString.setStringValue(aString);
			cgString.setTypeId(getTypeId(TypeId.STRING));
			globalNameManager.declareLazyName(cgString);
			cgStrings.put(aString, cgString);
		}
		return cgString;
	}

	public @NonNull CGTypeId getTypeId(@NonNull TypeId typeId) {
		CGElementId cgElementId = cgElementIds.get(typeId);
		CGTypeId cgTypeId = (CGTypeId)cgElementId;
		if (cgTypeId == null) {
			cgTypeId = CGModelFactory.eINSTANCE.createCGTypeId();
			cgTypeId.setElementId(typeId);
			globalNameManager.declareLazyName(cgTypeId);
			cgElementIds.put(typeId, cgTypeId);

			cgTypeId.setTypeId(getTypeId(TypeId.OCL_ANY)); // XXX better tyoe ??
		}
		return cgTypeId;
	}

	public @NonNull CGUnlimited getUnlimited() {
		CGUnlimited cgUnlimited2 = cgUnlimited;
		if (cgUnlimited2 == null) {
			cgUnlimited2 = CGModelFactory.eINSTANCE.createCGUnlimited();
			cgUnlimited2.setTypeId(getTypeId(TypeId.UNLIMITED_NATURAL));
		//	globalNameManager.declareLazyName(cgUnlimited2); -- inlined so missing AST etc ok
			cgUnlimited = cgUnlimited2;
		}
		return cgUnlimited2;
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

	public boolean isForeign(@NonNull Feature asFeature) {
		return (foreignFeatures != null) && foreignFeatures.contains(asFeature);
	}

	/**
	 * Replace oldElement by newElement and return oldElement which is orphaned by the replacement.
	 */
	public @NonNull CGValuedElement replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement,
			/*@NonNull*/ String messageTemplate, Object... bindings) {
		if (oldElement.isRequired() && newElement.isNull()) {
			newElement = getInvalid(messageTemplate, bindings);
		}
		return CGUtil.replace(oldElement, newElement);
	}

	public void setConstant(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement aConstant) {
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
