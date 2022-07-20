/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.SpecializedId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
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
	protected final @NonNull NameManager nameManager;
	private @NonNull Map<@NonNull ElementId, @NonNull CGElementId> cgElementIds = new HashMap<>();
	protected final @NonNull CGBoolean cgFalse;
	protected final @NonNull CGBoolean cgTrue;
	private /*@LazyNonNull*/ CGUnlimited cgUnlimited = null;
	private /*@LazyNonNull*/ CGInvalid cgInvalid = null;
	protected final @NonNull CGNull cgNull;
	private final @NonNull Map<Number, CGInteger> cgIntegers = new HashMap<>();
	private final @NonNull Map<Number, CGReal> cgReals = new HashMap<>();
	private final @NonNull Map<String, CGString> cgStrings = new HashMap<>();

	public CodeGenAnalyzer(@NonNull CodeGenerator codeGenerator) {
		this.codeGenerator = codeGenerator;
		this.nameManager = codeGenerator.getNameManager();
		cgFalse = createCGBoolean(false);
		cgTrue = createCGBoolean(true);
		cgNull = createCGNull();
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

	protected @NonNull CGBoolean createCGBoolean(boolean booleanValue) {
		CGBoolean cgBoolean = CGModelFactory.eINSTANCE.createCGBoolean();
		setExplicitNames(cgBoolean, booleanValue);
		cgBoolean.setBooleanValue(booleanValue);
		cgBoolean.setTypeId(getTypeId(TypeId.BOOLEAN));
		return cgBoolean;
	}

	protected @NonNull CGNull createCGNull() {
		CGNull cgNull = CGModelFactory.eINSTANCE.createCGNull();
		setExplicitNames(cgNull, null);
		cgNull.setTypeId(getTypeId(TypeId.OCL_VOID));
		return cgNull;
	}

	public @NonNull CGExecutorOperation createExecutorOperation(@NonNull Operation asOperation) {
		OperationId operationId = asOperation.getOperationId();
		CGExecutorOperation cgOperation = CGModelFactory.eINSTANCE.createCGExecutorOperation();
		CGElementId cgOperationId = getElementId(operationId);
		cgOperation.setTypeId(getTypeId(asOperation.getTypeId()));
		cgOperation.setUnderlyingOperationId(cgOperationId);
		cgOperation.setAst(asOperation);
		cgOperation.setName(nameManager.getGlobalSymbolName(asOperation));
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
			cgProperty.setName("IMPPROPid_" + asOppositeProperty.getName());
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		else {
			cgPropertyId = getElementId(asOppositeProperty.getPropertyId());
			cgProperty = CGModelFactory.eINSTANCE.createCGExecutorOppositeProperty();
			cgProperty.setUnderlyingPropertyId(cgPropertyId);
			cgProperty.setAst(asProperty);
			cgProperty.setName("IMPPROPid_" + asProperty.getName());
			cgProperty.getDependsOn().add(cgPropertyId);
		}
		return cgProperty;
	}

	public @NonNull CGExecutorProperty createExecutorProperty(@NonNull Property asProperty) {
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = getElementId(propertyId);
		CGExecutorProperty cgProperty = CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
		cgProperty.setUnderlyingPropertyId(cgPropertyId);
		cgProperty.setAst(asProperty);
		cgProperty.setName("IMPPROPid_" + asProperty.getName());
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
		cgPart.getDependsOn().add(cgPropertyId);
		return cgPart;
	}

	public @NonNull CGExecutorType createExecutorType(@NonNull Type asType) {
		TypeId typeId = asType.getTypeId();
		CGExecutorType cgType = CGModelFactory.eINSTANCE.createCGExecutorType();
		CGTypeId cgTypeId = getTypeId(typeId);
		cgType.setUnderlyingTypeId(cgTypeId);
		cgType.setAst(asType);
		cgType.setName(getNameManager().getGlobalSymbolName(asType));
		//		cgType.setValueName(cgType.getName());
		cgType.getDependsOn().add(cgTypeId);
		return cgType;
	}

	public @NonNull CGBoolean getBoolean(boolean aBoolean) {
		return aBoolean ? cgTrue : cgFalse;
	}

	public @NonNull Iterable<@NonNull CGElementId> getCGElementIds() {
		return cgElementIds.values();
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

	public @NonNull CGNull getNull() {
		return cgNull;
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

	public @NonNull NameManager getNameManager() {
		return ClassUtil.nonNullState(nameManager);
	}

	public @NonNull CGTypeId getTypeId(@NonNull TypeId typeId) {
		CGElementId cgElementId = cgElementIds.get(typeId);
		CGTypeId cgTypeId = (CGTypeId)cgElementId;
		if (cgTypeId == null) {
			cgTypeId = CGModelFactory.eINSTANCE.createCGTypeId();
			cgTypeId.setElementId(typeId);
			cgTypeId.setName(nameManager.getGlobalSymbolName(typeId));
			cgTypeId.setValueName(ClassUtil.nonNullState(cgTypeId.getName()));
			cgElementIds.put(typeId, cgTypeId);
			if (typeId instanceof SpecializedId) {
				BindingsId templateBindings = ((SpecializedId)typeId).getTemplateBindings();
				for (int i = 0; i < templateBindings.elementIdSize(); i++) {
					ElementId elementId = templateBindings.getElementId(i);
					getElementId(elementId);
				}
			}
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

	public void setExplicitNames(@NonNull CGValuedElement cgValue, @Nullable Object anObject) {
		String name = nameManager.getExplicitName(anObject);
		cgValue.setName(name);
		cgValue.setValueName(name);
	}

	public void setNames(@NonNull CGValuedElement cgValue, @NonNull Object anObject) {
		String name = nameManager.getGlobalSymbolName(anObject);
		cgValue.setName(name);
		cgValue.setValueName(name);
	}
}
