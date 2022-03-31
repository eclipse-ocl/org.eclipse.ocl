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
import org.eclipse.ocl.examples.codegen.calling.NativeOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
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
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

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
	private final @NonNull Map<@NonNull Number, @NonNull CGInteger> cgIntegers = new HashMap<>();
	private final @NonNull Map<@NonNull Number, @NonNull CGReal> cgReals = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull CGString> cgStrings = new HashMap<>();
	private /*@LazyNonNull*/ Map<@NonNull ExpressionInOCL, @NonNull CommonSubExpressionAnalysis> expression2cseAnalsis = null;
	// UniqueList allows recursive discovery of more foreign Features
	private /*@LazyNonNull*/ UniqueList<@NonNull Feature> foreignFeatures = null;
	private @NonNull Map<@NonNull Operation, @NonNull CGOperation> asOperation2cgOperation = new HashMap<>();
	private @Nullable Model nativeModel = null;

	public CodeGenAnalyzer(@NonNull CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		cgFalse = createCGBoolean(false);
		cgTrue = createCGBoolean(true);
		cgNull = createCGNull();
	}

	public void addForeignFeature(@NonNull Feature asFeature) {
		UniqueList<@NonNull Feature> foreignFeatures2 = foreignFeatures;
		if (foreignFeatures2 == null) {
			foreignFeatures = foreignFeatures2 = new UniqueList<>();
		}
		foreignFeatures2.add(asFeature);
	}

	public void addOperation(@NonNull Operation asOperation, @NonNull CGOperation cgOperation) {
		CGOperation old = asOperation2cgOperation.put(asOperation, cgOperation);
		assert old == null;
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
				cgStaticClass.setAst(foreignClass);
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

	public @Nullable CGOperation basicGetOperation(@NonNull Operation asOperation) {
		return asOperation2cgOperation.get(asOperation);
	}

	public @NonNull CGBoolean createCGBoolean(boolean booleanValue) {
		CGBoolean cgBoolean = CGModelFactory.eINSTANCE.createCGBoolean();
	//	setExplicitNames(cgBoolean, booleanValue);
		cgBoolean.setBooleanValue(booleanValue);
		cgBoolean.setTypeId(getTypeId(TypeId.BOOLEAN));
		globalNameManager.declareStandardName(cgBoolean);
		return cgBoolean;
	}

	public @NonNull CGValuedElement createCGConstantExp(@NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(cgConstant.getAst());
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(cgConstant.getTypeId());
		return cgConstantExp;
	}

	public @NonNull CGConstantExp createCGConstantExp(@NonNull OCLExpression element, @NonNull CGConstant cgConstant) {
		CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgConstantExp.setAst(element);
		cgConstantExp.setReferredConstant(cgConstant);
		cgConstantExp.setTypeId(getTypeId(element.getTypeId()));
		return cgConstantExp;
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@Nullable Method method) {		// XXX @NonNull
		assert method != null;
		return createCGNativeOperationCallExp(method, NativeOperationCallingConvention.INSTANCE);
	}

	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp(@NonNull Method method, @NonNull OperationCallingConvention callingConvention) {		// XXX @NonNull
		assert method != null;
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setMethod(method);
		Operation asOperation = getNativeOperation(method);
		CGOperation cgOperation = getOperation(asOperation);
		cgNativeOperationCallExp.setOperation(cgOperation);
		return cgNativeOperationCallExp;
	}

	public @NonNull CGNull createCGNull() {
		CGNull cgNull = CGModelFactory.eINSTANCE.createCGNull();
	//	setExplicitNames(cgNull, null);
		cgNull.setTypeId(getTypeId(TypeId.OCL_VOID));
		globalNameManager.declareStandardName(cgNull);
		return cgNull;
	}

	public @NonNull CGParameter createCGParameter(@NonNull String name, @NonNull CGTypeId typeId, boolean isRequired) {
		CGParameter cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
		cgParameter.setName(name);
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
		return cgVariableExp;
	}

	public @NonNull CGExecutorOperation createExecutorOperation(@NonNull Operation asOperation) {
		OperationId operationId = asOperation.getOperationId();
		CGExecutorOperation cgOperation = CGModelFactory.eINSTANCE.createCGExecutorOperation();
		CGElementId cgOperationId = getElementId(operationId);
	//	cgOperation.setTypeId(getTypeId(asOperation.getTypeId()));
		cgOperation.setUnderlyingOperationId(cgOperationId);
		cgOperation.setAst(asOperation);
		globalNameManager.declareStandardName(cgOperation);
	//	cgOperation.setName(globalNameManager.getGlobalSymbolName(asOperation));
		//		cgOperation.setValueName(cgOperation.getName());
		cgOperation.getDependsOn().add(cgOperationId);
		return cgOperation;
	}

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
			cgProperty.setName("IMPPROPid_" + asOppositeProperty.getName());
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		else {
			cgPropertyId = getElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorOppositeProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asProperty);
			cgProperty.setName("IMPPROPid_" + asProperty.getName());
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
		cgProperty.setName("IMPPROPid_" + asProperty.getName());
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
		cgPart.setName("CTORid_" + asProperty.getName());
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
		getGlobalNameManager().declareStandardName(cgType);
		//		cgType.setValueName(cgType.getName());
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
			setNames(cgElementId, elementId);
			cgElementIds.put(elementId, cgElementId);
			cgElementId.setTypeId(getTypeId(TypeId.OCL_ANY));		// XXX do better
		}
		return cgElementId;
	}

	public @NonNull CGValuedElement getExpression(@Nullable CGValuedElement cgExpression) {
		if (cgExpression == null) {
			CGConstantExp cgLiteralExp = CGModelFactory.eINSTANCE.createCGConstantExp();
			//			cgLiteralExp.setAst(element);
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
			setNames(cgInteger, aNumber);
			cgInteger.setNumericValue(aNumber);
			cgInteger.setTypeId(getTypeId(TypeId.INTEGER));
			cgIntegers.put(aNumber, cgInteger);
		}
		return cgInteger;
	}

	public @NonNull CGInvalid getInvalid() {
		CGInvalid cgInvalid2 = cgInvalid;
		if (cgInvalid2 == null) {
			cgInvalid = cgInvalid2 = CGModelFactory.eINSTANCE.createCGInvalid();
			//			cgInvalid.setAst(ValuesUtil.INVALID_VALUE);
			setNames(cgInvalid2, ValueUtil.INVALID_VALUE);
			cgInvalid2.setTypeId(getTypeId(TypeId.OCL_INVALID));
		}
		return cgInvalid2;
	}

	public @NonNull CGInvalid getInvalid(/*@NonNull*/ String messageTemplate, Object... bindings) {
		CGInvalid cgInvalid = CGModelFactory.eINSTANCE.createCGInvalid();
		setNames(cgInvalid, ValueUtil.INVALID_VALUE);
		cgInvalid.setTypeId(getTypeId(TypeId.OCL_INVALID));
		cgInvalid.setMessageTemplate(messageTemplate);
		for (Object binding : bindings) {
			cgInvalid.getBindings().add(binding);
		}
		return cgInvalid;
	}

	private org.eclipse.ocl.pivot.@NonNull Class getNativeClass(@NonNull Class<?> jClass) {
		Package jPackage = jClass.getPackage();
		assert jPackage != null;
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getNativePackage(jPackage);
		String name = jClass.getName();					// FIXME Is this the right method t capture all enclosing classes/methods ? certainly don't need enclosing package
		List<org.eclipse.ocl.pivot.Class> asClasses = asPackage.getOwnedClasses();
		org.eclipse.ocl.pivot.Class asClass = NameUtil.getNameable(asClasses, name);
		if (asClass == null) {
			asClass = PivotFactory.eINSTANCE.createClass();
			asClass.setName(name);
			asClasses.add(asClass);
		}
		return asClass;
	}

	private @NonNull Model getNativeModel() {
		Model asModel = nativeModel;
		if (asModel == null) {
			asModel = PivotFactory.eINSTANCE.createModel();
			asModel.setName("native-java");
			nativeModel = asModel;
		}
		return asModel;
	}

	private @NonNull Operation getNativeOperation2(@NonNull Method method) {
		Class<?> jClass = method.getDeclaringClass();
		assert jClass != null;
		org.eclipse.ocl.pivot.Class asClass = getNativeClass(jClass);
		String name = method.getName();
		List<Operation> asOperations = asClass.getOwnedOperations();
		Operation asOperation = NameUtil.getNameable(asOperations, name);	// FIXME overloads
		if (asOperation == null) {
			asOperation = PivotFactory.eINSTANCE.createOperation();
			asOperation.setName(name);
			//	asOperation.setType(type);
			//	asOperation.setImplementationClass(implementationClass);
			//	asOperation.setImplementation(implementation);
			asOperations.add(asOperation);
		}
		return asOperation;
	}

	public @NonNull Operation getNativeOperation(@NonNull Method method) {
		// TODO Auto-generated method stub
		return null;
	}

	private org.eclipse.ocl.pivot.@NonNull Package getNativePackage(@NonNull Package jPackage) {
		Model asModel = getNativeModel();
		String qualifiedName = jPackage.getName();
		List<org.eclipse.ocl.pivot.Package> asPackages = asModel.getOwnedPackages();
		org.eclipse.ocl.pivot.Package asPackage = NameUtil.getNameable(asPackages, qualifiedName);
		if (asPackage == null) {
			asPackage = PivotFactory.eINSTANCE.createPackage();
			asPackage.setName(qualifiedName);
			asPackages.add(asPackage);
		}
		return asPackage;
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

	public @NonNull CGReal getReal(@NonNull Number aNumber) {
		CGReal cgReal = cgReals.get(aNumber);
		if (cgReal == null) {
			cgReal = CGModelFactory.eINSTANCE.createCGReal();
			setNames(cgReal, aNumber);
			cgReal.setNumericValue(aNumber);
			cgReal.setTypeId(getTypeId(TypeId.REAL));
			cgReals.put(aNumber, cgReal);
		}
		return cgReal;
	}

	public @NonNull CGString getString(@NonNull String aString) {
		CGString cgString = cgStrings.get(aString);
		if (cgString == null) {
			cgString = CGModelFactory.eINSTANCE.createCGString();
			setNames(cgString, aString);
			cgString.setStringValue(aString);
			cgString.setTypeId(getTypeId(TypeId.STRING));
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
			globalNameManager.declareStandardName(cgTypeId);
		//	cgTypeId.setName(globalNameManager.getGlobalSymbolName(typeId));
		//	cgTypeId.setValueName(ClassUtil.nonNullState(cgTypeId.getName()));
			cgElementIds.put(typeId, cgTypeId);

			cgTypeId.setTypeId(getTypeId(TypeId.OCL_ANY)); // XXX better tyoe ??
		}
		return cgTypeId;
	}

	public @NonNull CGUnlimited getUnlimited() {
		CGUnlimited cgUnlimited2 = cgUnlimited;
		if (cgUnlimited2 == null) {
			cgUnlimited = cgUnlimited2 = CGModelFactory.eINSTANCE.createCGUnlimited();
			setNames(cgUnlimited2, ValueUtil.UNLIMITED_VALUE);
			cgUnlimited2.setTypeId(getTypeId(TypeId.UNLIMITED_NATURAL));
		}
		return cgUnlimited2;
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

/*	@Deprecated public void setExplicitNames(@NonNull CGValuedElement cgValue, @Nullable Object anObject) {
		String name = globalNameManager.getExplicitName(anObject);
	//	cgValue.setName(name);
	//	cgValue.setValueName(name);
		globalNameManager.queueValueName(cgValue, null, name);
	} */

	public void setNames(@NonNull CGValuedElement cgValue, @NonNull Object anObject) {
		String name = globalNameManager.getNameHint(anObject);
	//	String name = globalNameManager.helper.getNameHint(anObject);
	//	cgValue.setName(name);
	//	cgValue.setValueName(name);
		globalNameManager.declareStandardName(cgValue, name);
	}
}
