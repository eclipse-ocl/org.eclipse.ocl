/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibrarySimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryUntypedOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * A CG2JavaPreVisitor prepares for Java code generation by performing a tree traversal
 * to gather all imports and global constants.
 */
public class CG2JavaPreVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull JavaGlobalContext<@NonNull ? extends JavaCodeGenerator>>
{
	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull GenModelHelper genModelHelper;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected JavaLocalContext<@NonNull ?> localContext;

	public CG2JavaPreVisitor(@NonNull JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> javaContext) {
		super(javaContext);
		this.codeGenerator = javaContext.getCodeGenerator();
		this.analyzer = codeGenerator.getAnalyzer();
		this.genModelHelper = codeGenerator.getGenModelHelper();
	}

	protected void addOwnedTypeId(@NonNull CGValuedElement cgElement, @NonNull ElementId typeId) {
		if (typeId instanceof OclVoidTypeId) {	// FIXME tuples etc etc
			;
		}
		else if (typeId instanceof PrimitiveTypeId) {
			;
		}
		//		else if (typeId instanceof JavaTypeId) {
		//			;
		//		}
		else {
			CGElementId elementId = analyzer.getElementId(typeId);
			CGElementId cgTypeId = elementId;
			CGConstantExp cgConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
			cgConstantExp.setReferredConstant(cgTypeId);
			if (elementId instanceof CGTypeId) {
				cgConstantExp.setTypeId((CGTypeId) elementId);
			}
			//			if (cgElement != null) {
			cgElement.getOwns().add(cgConstantExp);		// FIXME suppress/detect duplicates
			//			}
			if (typeId instanceof CollectionTypeId) {
				addOwnedTypeId(cgConstantExp, ((CollectionTypeId)typeId).getElementTypeId());
			}
			else if (typeId instanceof TupleTypeId) {
				for (TuplePartId partId : ((TupleTypeId)typeId).getPartIds()) {
					addOwnedTypeId(cgConstantExp, partId);
				}
			}
			else if (typeId instanceof NestedTypeId) {
				addOwnedTypeId(cgConstantExp, ((NestedTypeId)typeId).getParent());
			}
		}
	}

	protected void doTypedElement(@NonNull CGTypedElement cgTypedElement) {
		CGTypeId cgTypeId = cgTypedElement.getTypeId();
		if (cgTypeId != null) {
			cgTypeId.accept(this);
		}
	}

	protected void doValuedElement(@NonNull CGValuedElement cgValuedElement) {
		CGValuedElement value = cgValuedElement.getNamedValue();
		if (value.isGlobal()) {
			context.addGlobal(value);
		}
		TypeId asTypeId = cgValuedElement.getASTypeId();
		if (asTypeId != null) {
			addOwnedTypeId(cgValuedElement, asTypeId);
		}
		if (cgValuedElement.getNamedValue() == cgValuedElement) {
			if ((localContext != null) && !cgValuedElement.isGlobal()) {
				localContext.getValueName(cgValuedElement);
			}
			else {
				context.getValueName(cgValuedElement);
			}
		}
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	/**
	 * Return the non-null name of a validation contrext if there is one available for cgValuedElement.
	 */
	private @Nullable String getContextName(@NonNull CGValuedElement cgValuedElement) {
		CGConstraint cgConstraint = CGUtil.getContainingConstraint(cgValuedElement);
		if (cgConstraint == null) {
			return null;
		}
		return "context";
	}

	protected @Nullable CGValuedElement installExecutorVariable(@NonNull CGValuedElement cgValuedElement) {
		CGValuedElement executorVariable = localContext.createExecutorVariable(getContextName(cgValuedElement));
		if (executorVariable != null) {
			cgValuedElement.getOwns().add(executorVariable);
		}
		return executorVariable;
	}


	protected @Nullable CGValuedElement installIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		CGValuedElement idResolverVariable = localContext.createIdResolverVariable(getContextName(cgValuedElement));
		cgValuedElement.getOwns().add(idResolverVariable);
		return idResolverVariable;
	}

	protected @NonNull CGText installStandardLibraryVariable(@NonNull CGValuedElement cgValuedElement) {
		CGText standardLibraryVariable = localContext.createStandardLibraryVariable(getContextName(cgValuedElement));
		cgValuedElement.getOwns().add(standardLibraryVariable);
		installIdResolverVariable(standardLibraryVariable);
		return standardLibraryVariable;
	}

	@Override
	public @Nullable Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitCGBoxExp(@NonNull CGBoxExp cgBoxExp) {
		CGValuedElement unboxedValue = cgBoxExp.getSource();
		if (unboxedValue != null) {
			TypeDescriptor unboxedTypeDescriptor = codeGenerator.getTypeDescriptor(unboxedValue);
			if (unboxedTypeDescriptor.isAssignableTo(Iterable.class)) {
				installIdResolverVariable(cgBoxExp);
			}
		}
		return super.visitCGBoxExp(cgBoxExp);
	}

	@Override
	public @Nullable Object visitCGBuiltInIterationCallExp(@NonNull CGBuiltInIterationCallExp cgBuiltInIterationCallExp) {
		TypeId asTypeId = cgBuiltInIterationCallExp.getASTypeId();
		if (asTypeId != null) {
			addOwnedTypeId(cgBuiltInIterationCallExp, asTypeId);
		}
		return super.visitCGBuiltInIterationCallExp(cgBuiltInIterationCallExp);
	}

	@Override
	public @Nullable Object visitCGCollectionExp(@NonNull CGCollectionExp cgCollectionExp) {
		CollectionLiteralExp collectionExp = (CollectionLiteralExp)cgCollectionExp.getAst();
		if (collectionExp != null) {
			TypeId asTypeId = cgCollectionExp.getASTypeId();
			if (asTypeId != null) {
				addOwnedTypeId(cgCollectionExp, asTypeId);
			}
		}
		return super.visitCGCollectionExp(cgCollectionExp);
	}

	@Override
	public @Nullable Object visitCGCollectionPart(@NonNull CGCollectionPart cgCollectionPart) {
		boolean isRange = cgCollectionPart.isRange();
		if (cgCollectionPart.isConstant() && isRange) {
			//			context.addGlobal(cgCollectionPart);
		}
		if (isRange) {
			//			context.getFinalVariable(cgCollectionPart);
		}
		return super.visitCGCollectionPart(cgCollectionPart);
	}

	@Override
	public @Nullable Object visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		super.visitCGConstantExp(cgConstantExp);
		CGValuedElement referredConstant = cgConstantExp.getReferredConstant();
		if (referredConstant != null) {
			referredConstant.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		localContext = context.getLocalContext(cgConstraint);
		try {
			return super.visitCGConstraint(cgConstraint);
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @Nullable Object visitCGEcoreExp(@NonNull CGEcoreExp cgEcoreExp) {
		CGValuedElement ecoreValue = cgEcoreExp.getSource();
		if (ecoreValue != null) {
			TypeDescriptor boxedTypeDescriptor = codeGenerator.getTypeDescriptor(ecoreValue);
			if (boxedTypeDescriptor.isAssignableTo(Iterable.class)
					|| boxedTypeDescriptor.isAssignableTo(EnumerationLiteralId.class)) {
				installIdResolverVariable(cgEcoreExp);
			}
		}
		return super.visitCGEcoreExp(cgEcoreExp);
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
		List<?> owns = cgElement instanceof CGValuedElement ? ((CGValuedElement)cgElement).getOwns() : null;
		for (CGElement cgChild : cgElement.getChildren()) {
			if ((owns == null) || !owns.contains(cgChild)) {
				cgChild.accept(this);
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGExecutorCompositionProperty(@NonNull CGExecutorCompositionProperty cgExecutorProperty) {
		Property asProperty = (Property) cgExecutorProperty.getAst();
		if ((asProperty != null) && asProperty.isIsComposite()) {
			TypeId javaPropertyTypeId = JavaConstants.UNBOXED_COMPOSITION_PROPERTY_TYPE_ID;
			cgExecutorProperty.setTypeId(analyzer.getTypeId(javaPropertyTypeId));
		}
		return super.visitCGExecutorCompositionProperty(cgExecutorProperty);
	}

	@Override
	public @Nullable Object visitCGExecutorNavigationProperty(@NonNull CGExecutorNavigationProperty cgExecutorProperty) {
		TypeId javaPropertyTypeId = JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID;
		cgExecutorProperty.setTypeId(analyzer.getTypeId(javaPropertyTypeId));
		return super.visitCGExecutorNavigationProperty(cgExecutorProperty);
	}

	@Override
	public @Nullable Object visitCGExecutorOperation(@NonNull CGExecutorOperation cgExecutorOperation) {
		installIdResolverVariable(cgExecutorOperation);
		CGElementId cgOperationId = cgExecutorOperation.getUnderlyingOperationId();
		if (cgOperationId != null) {
			cgOperationId.accept(this);
		}
		return super.visitCGExecutorOperation(cgExecutorOperation);
	}

	@Override
	public @Nullable Object visitCGExecutorOperationCallExp(@NonNull CGExecutorOperationCallExp cgExecutorOperationCallExp) {
		//		Operation referredOperation = cgExecutorOperationCallExp.getReferredOperation();
		//		OperationId operationId = referredOperation.getOperationId();
		//		CGExecutorOperation cgExecutorOperation = analyzer.getExecutorOperation(operationId);
		//		cgExecutorOperation.accept(this);
		//		localContext.getOuterContext().addLocalVariable(cgExecutorOperation);
		return super.visitCGExecutorOperationCallExp(cgExecutorOperationCallExp);
	}

	@Override
	public @Nullable Object visitCGExecutorOppositeProperty(@NonNull CGExecutorOppositeProperty cgExecutorProperty) {
		TypeId javaPropertyTypeId = JavaConstants.UNBOXED_OPPOSITE_NAVIGATION_PROPERTY_TYPE_ID;
		cgExecutorProperty.setTypeId(analyzer.getTypeId(javaPropertyTypeId));
		return super.visitCGExecutorOppositeProperty(cgExecutorProperty);
	}

	@Override
	public @Nullable Object visitCGExecutorOppositePropertyCallExp(@NonNull CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp) {
		CGExecutorProperty cgExecutorProperty = cgExecutorPropertyCallExp.getExecutorProperty();
		if (cgExecutorProperty != null) {
			cgExecutorProperty.accept(this);
		}
		//		Property referredProperty = cgExecutorPropertyCallExp.getReferredProperty();
		//		PropertyId propertyId = referredProperty.getPropertyId();
		//		CGExecutorProperty cgExecutorProperty = cgExecutorPropertyCallExp.getExecutorProperty();
		//		CGExecutorProperty cgExecutorProperty = analyzer.createExecutorProperty(referredProperty);
		//		cgExecutorPropertyCallExp.getUses().add(cgExecutorProperty);
		//		cgExecutorProperty.accept(this);
		//		localContext.getOuterContext().addLocalVariable(cgExecutorProperty);
		//		cgExecutorProperty.getDependsOn().add(installIdResolverVariable());
		return super.visitCGExecutorOppositePropertyCallExp(cgExecutorPropertyCallExp);
	}

	@Override
	public @Nullable Object visitCGExecutorProperty(@NonNull CGExecutorProperty cgExecutorProperty) {
		CGElementId cgPropertyId = cgExecutorProperty.getUnderlyingPropertyId();
		if (cgPropertyId != null) {
			cgPropertyId.accept(this);
		}
		return super.visitCGExecutorProperty(cgExecutorProperty);
	}

	@Override
	public @Nullable Object visitCGExecutorPropertyCallExp(@NonNull CGExecutorPropertyCallExp cgExecutorPropertyCallExp) {
		CGExecutorProperty cgExecutorProperty = cgExecutorPropertyCallExp.getExecutorProperty();
		if (cgExecutorProperty != null) {
			cgExecutorProperty.accept(this);
		}
		//		Property referredProperty = cgExecutorPropertyCallExp.getReferredProperty();
		//		PropertyId propertyId = referredProperty.getPropertyId();
		//		CGExecutorProperty cgExecutorProperty = cgExecutorPropertyCallExp.getExecutorProperty();
		//		CGExecutorProperty cgExecutorProperty = analyzer.createExecutorProperty(referredProperty);
		//		cgExecutorPropertyCallExp.getUses().add(cgExecutorProperty);
		//		cgExecutorProperty.accept(this);
		//		localContext.getOuterContext().addLocalVariable(cgExecutorProperty);
		//		cgExecutorProperty.getDependsOn().add(installIdResolverVariable());
		return super.visitCGExecutorPropertyCallExp(cgExecutorPropertyCallExp);
	}

	@Override
	public @Nullable Object visitCGExecutorType(@NonNull CGExecutorType cgExecutorType) {
		installIdResolverVariable(cgExecutorType);
		CGTypeId cgTypeId = cgExecutorType.getUnderlyingTypeId();
		if (cgTypeId != null) {
			cgTypeId.accept(this);
		}
		Type asType = CGUtil.getAST(cgExecutorType);
		Type asMetaType = codeGenerator.getEnvironmentFactory().getStandardLibrary().getMetaclass(asType);
	//	cgExecutorType.setTypeId(analyzer.getTypeId(JavaConstants.CLASS_TYPE_ID));		// FIXME
		cgExecutorType.setTypeId(analyzer.getTypeId(asMetaType.getTypeId()));
		return super.visitCGExecutorType(cgExecutorType);
	}

	@Override
	public @Nullable Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		doValuedElement(cgIterationCallExp);				// Resolve name in outer context
		doTypedElement(cgIterationCallExp);
		CGValuedElement cgSource = cgIterationCallExp.getSource();
		if (cgSource != null) {
			cgSource.accept(this);
		}
		for (CGIterator cgIterator : cgIterationCallExp.getIterators()) {
			CGValuedElement cgInit = cgIterator.getInit();
			if (cgInit != null) {
				cgInit.accept(this);
			}
		}
		JavaLocalContext<@NonNull ?> savedLocalContext = localContext;
		localContext = context.getLocalContext(cgIterationCallExp);
		try {
			CGValuedElement cgBody = cgIterationCallExp.getBody();
			if (cgBody != null) {
				cgBody.accept(this);
			}
			for (CGIterator cgIterator : cgIterationCallExp.getIterators()) {
				cgIterator.accept(this);
			}
			return null;
		}
		finally {
			localContext = savedLocalContext;
		}
	}

	@Override
	public @Nullable Object visitCGLetExp(@NonNull CGLetExp cgLetExp) {
		CGValuedElement in = cgLetExp.getIn();
		if (in != null) {
			//			context.getFinalVariable(in);
		}
		return super.visitCGLetExp(cgLetExp);
	}

	@Override
	public @Nullable Object visitCGLibraryIterateCallExp(@NonNull CGLibraryIterateCallExp cgLibraryIterateCallExp) {
		installStandardLibraryVariable(cgLibraryIterateCallExp);
		return super.visitCGLibraryIterateCallExp(cgLibraryIterateCallExp);
	}

	@Override
	public @Nullable Object visitCGLibraryIterationCallExp(@NonNull CGLibraryIterationCallExp cgLibraryIterationCallExp) {
		installStandardLibraryVariable(cgLibraryIterationCallExp);
		//		TypeId asTypeId = cgLibraryIterationCallExp.getASTypeId();
		//		if (asTypeId != null) {
		//			addOwnedTypeId(cgLibraryIterationCallExp, asTypeId);
		//		}
		return super.visitCGLibraryIterationCallExp(cgLibraryIterationCallExp);
	}

	@Override
	public @Nullable Object visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		LibraryOperation libraryOperation = cgOperationCallExp.getLibraryOperation();
		if (!(libraryOperation instanceof LibrarySimpleOperation)) {
			installExecutorVariable(cgOperationCallExp);
			if (!(libraryOperation instanceof LibraryUntypedOperation)) {
				TypeId asTypeId = cgOperationCallExp.getASTypeId();
				if (asTypeId != null) {
					addOwnedTypeId(cgOperationCallExp, asTypeId);
				}
			}
		}
		return super.visitCGLibraryOperationCallExp(cgOperationCallExp);
	}

	@Override
	public @Nullable Object visitCGLibraryPropertyCallExp(@NonNull CGLibraryPropertyCallExp cgPropertyCallExp) {
		//		LibraryProperty libraryProperty = cgPropertyCallExp.getLibraryProperty();
		try {
			installExecutorVariable(cgPropertyCallExp);
			return super.visitCGLibraryPropertyCallExp(cgPropertyCallExp);
		}
		finally {
			//			installEvaluatorParameter(cgPropertyCallExp);
			//			if (!(libraryOperation instanceof LibraryUntypedOperation)) {
			CGTypeId cgTypeId = cgPropertyCallExp.getTypeId();
			if (cgTypeId != null) {
				//					context.addGlobal(cgTypeId);
			}
			TypeId asTypeId = cgPropertyCallExp.getASTypeId();
			if (asTypeId != null) {
				addOwnedTypeId(cgPropertyCallExp, asTypeId);
			}
			//			}
		}
	}

	@Override
	public @Nullable Object visitCGMapExp(@NonNull CGMapExp cgMapExp) {
		MapLiteralExp mapExp = (MapLiteralExp)cgMapExp.getAst();
		if (mapExp != null) {
			TypeId asTypeId = cgMapExp.getASTypeId();
			if (asTypeId != null) {
				addOwnedTypeId(cgMapExp, asTypeId);
			}
		}
		return super.visitCGMapExp(cgMapExp);
	}

	@Override
	public @Nullable Object visitCGMapPart(@NonNull CGMapPart cgMapPart) {
		return super.visitCGMapPart(cgMapPart);
	}

	@Override
	public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
		localContext = context.getLocalContext(cgOperation);
		try {
			return super.visitCGOperation(cgOperation);
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @Nullable Object visitCGProperty(@NonNull CGProperty cgProperty) {
		localContext = context.getLocalContext(cgProperty);
		try {
			return super.visitCGProperty(cgProperty);
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @Nullable Object visitCGShadowExp(@NonNull CGShadowExp cgShadowExp) {
		CGExecutorType cgType = cgShadowExp.getExecutorType();
		if (cgType != null) {
			cgType.accept(this);
		}
		return super.visitCGShadowExp(cgShadowExp);
	}

	@Override
	public @Nullable Object visitCGShadowPart(@NonNull CGShadowPart cgShadowPart) {
		CGExecutorShadowPart cgExecutorConstructorPart = cgShadowPart.getExecutorPart();
		cgExecutorConstructorPart.accept(this);
		TypeId javaPropertyTypeId = JavaConstants.PROPERTY_TYPE_ID;
		cgExecutorConstructorPart.setTypeId(analyzer.getTypeId(javaPropertyTypeId));
		//		localContext.addLocalVariable(cgExecutorConstructorPart);
		installIdResolverVariable(cgExecutorConstructorPart);
		cgShadowPart.getOwns().add(cgExecutorConstructorPart);
		cgShadowPart.getDependsOn().add(cgExecutorConstructorPart);
		//		cgShadowPart.getDependsOn().add(cgShadowPart.getShadowExp());
		return super.visitCGShadowPart(cgShadowPart);
	}

	@Override
	public @Nullable Object visitCGTemplateParameterExp(@NonNull CGTemplateParameterExp cgTemplateParameterExp) {
		TypeId asTypeId = cgTemplateParameterExp.getASTypeId();
		if (asTypeId != null) {
			addOwnedTypeId(cgTemplateParameterExp, asTypeId);
		}
		CGValuedElement cgTemplateableElement = cgTemplateParameterExp.getTemplateableElement();
		if (cgTemplateableElement != null) {
			cgTemplateableElement.accept(this);
		}
		//		CGExecutorType cgType = cgTypeExp.getExecutorType();
		//		String name = cgType.getValueName();
		//		if (name == null) {
		//			name = localContext.getNameManagerContext().getSymbolName(cgType);
		//			cgType.setValueName(name);
		//		}
		return super.visitCGTemplateParameterExp(cgTemplateParameterExp);
	}

	@Override
	public @Nullable Object visitCGTypeExp(@NonNull CGTypeExp cgTypeExp) {
		TypeId asTypeId = cgTypeExp.getASTypeId();
		if (asTypeId != null) {
			addOwnedTypeId(cgTypeExp, asTypeId);
		}
		CGExecutorType cgType = cgTypeExp.getExecutorType();
		if (cgType != null) {
			cgType.accept(this);
		}
		//		CGExecutorType cgType = cgTypeExp.getExecutorType();
		//		String name = cgType.getValueName();
		//		if (name == null) {
		//			name = localContext.getNameManagerContext().getSymbolName(cgType);
		//			cgType.setValueName(name);
		//		}
		return super.visitCGTypeExp(cgTypeExp);
	}

	@Override
	public @Nullable Object visitCGTypedElement(@NonNull CGTypedElement cgTypedElement) {
		super.visitCGTypedElement(cgTypedElement);
		doTypedElement(cgTypedElement);
		return null;
	}

	@Override
	public @Nullable Object visitCGUnboxExp(@NonNull CGUnboxExp cgUnboxExp) {
		CGValuedElement source = analyzer.getExpression(cgUnboxExp.getSource());
		TypeDescriptor boxedTypeDescriptor = codeGenerator.getTypeDescriptor(source);
		if (boxedTypeDescriptor.isAssignableTo(CollectionValue.class)
				|| boxedTypeDescriptor.isAssignableTo(EnumerationLiteralId.class)) {
			installIdResolverVariable(cgUnboxExp);
		}
		return super.visitCGUnboxExp(cgUnboxExp);
	}

	@Override
	public @Nullable Object visitCGValuedElement(@NonNull CGValuedElement cgValuedElement) {
		super.visitCGValuedElement(cgValuedElement);
		doValuedElement(cgValuedElement);
		return null;
	}
}
