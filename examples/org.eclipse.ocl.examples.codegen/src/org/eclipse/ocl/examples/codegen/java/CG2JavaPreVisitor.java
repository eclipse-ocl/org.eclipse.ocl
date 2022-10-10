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
package org.eclipse.ocl.examples.codegen.java;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
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
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * A CG2JavaPreVisitor prepares for Java code generation by performing a tree traversal
 * to gather all imports and global constants and establish the dependenccies used by the CSE.
 */
public class CG2JavaPreVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull JavaCodeGenerator>
{
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull GenModelHelper genModelHelper;
	protected final @NonNull CodeGenAnalyzer analyzer;

	public CG2JavaPreVisitor(@NonNull JavaCodeGenerator codeGenerator) {
		super(codeGenerator);
		this.globalNameManager = codeGenerator.getGlobalNameManager();
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
			CGElementId elementId = analyzer.getCGElementId(typeId);
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
		if ((cgTypeId != null) && (cgTypeId != cgTypedElement)) {		// XXX Better way to terminate mta recursion
			cgTypeId.accept(this);
		}
	}

	protected void doValuedElement(@NonNull CGValuedElement cgValuedElement) {
		CGValuedElement value = cgValuedElement.getNamedValue();
		if (value.isGlobal()) {
			globalNameManager.addGlobal(value);
		}
		TypeId asTypeId = cgValuedElement.getASTypeId();
		if (asTypeId != null) {
			addOwnedTypeId(cgValuedElement, asTypeId);
		}
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return context;
	}

	protected @NonNull ExecutableNameManager getTreeNameManager(@NonNull CGValuedElement cgElement) {
		ExecutableNameManager executableNameManager = analyzer.useExecutableNameManager(CGUtil.getAST(cgElement));
		return executableNameManager.getRootExecutableNameManager();
	}

	protected @Nullable CGVariable installExecutorVariable(@NonNull CGValuedElement cgElement) {
		ExecutableNameManager executableNameManager = getTreeNameManager(cgElement);
		return analyzer.getExecutorVariable(executableNameManager);
	}

	protected @NonNull CGVariable installIdResolverVariable(@NonNull CGValuedElement cgElement) {
		ExecutableNameManager executableNameManager = getTreeNameManager(cgElement);
		return executableNameManager.getIdResolverVariable();
	}

	protected @NonNull CGVariable installStandardLibraryVariable(@NonNull CGValuedElement cgElement) {
		ExecutableNameManager executableNameManager = getTreeNameManager(cgElement);
		return executableNameManager.getStandardLibraryVariable();
	}

	@Override
	public @Nullable Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitCGBoxExp(@NonNull CGBoxExp cgBoxExp) {
		CGValuedElement unboxedValue = cgBoxExp.getSource();
		if (unboxedValue != null) {
			TypeDescriptor unboxedTypeDescriptor = context.getTypeDescriptor(unboxedValue);
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
	public @Nullable Object visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		super.visitCGConstantExp(cgConstantExp);
		CGValuedElement referredConstant = cgConstantExp.getReferredConstant();
		if (referredConstant != null) {
			referredConstant.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGConstrainedProperty(@NonNull CGConstrainedProperty cgProperty) {
		super.visitCGConstrainedProperty(cgProperty);
		wrapLetVariables(cgProperty);
		return null;
	}

	@Override
	public @Nullable Object visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		super.visitCGConstraint(cgConstraint);
		wrapLetVariables(cgConstraint);
		return null;
	}

	@Override
	public @Nullable Object visitCGEcoreExp(@NonNull CGEcoreExp cgEcoreExp) {
		CGValuedElement ecoreValue = cgEcoreExp.getSource();
		if (ecoreValue != null) {
			TypeDescriptor boxedTypeDescriptor = context.getTypeDescriptor(ecoreValue);
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
	public @Nullable Object visitCGExecutorNavigationProperty(@NonNull CGExecutorNavigationProperty cgExecutorProperty) {
		Property asProperty = CGUtil.getAST(cgExecutorProperty);
		if (asProperty.isIsStatic()) {
			TypeId asOwningTypeId = PivotUtil.getOwningClass(asProperty).getTypeId();
			addOwnedTypeId(cgExecutorProperty, asOwningTypeId);
		}
		return super.visitCGExecutorNavigationProperty(cgExecutorProperty);
	}

/*	@Override
	public @Nullable Object visitCGExecutorOperation(@NonNull CGExecutorOperation cgExecutorOperation) {
		cgExecutorOperation.setTypeId(analyzer.getTypeId(JavaConstants.EXECUTOR_OPERATION_TYPE_ID));
		installIdResolverVariable(cgExecutorOperation);
		CGElementId cgOperationId = cgExecutorOperation.getUnderlyingOperationId();
		if (cgOperationId != null) {
			cgOperationId.accept(this);
		}
		return super.visitCGExecutorOperation(cgExecutorOperation);
	} */

/*	@Override
	public @Nullable Object visitCGExecutorOperationCallExp(@NonNull CGExecutorOperationCallExp cgExecutorOperationCallExp) {
		CGExecutorOperation cgExecutorOperation = cgExecutorOperationCallExp.getExecutorOperation();
		if (cgExecutorOperation != null) {		// FIXME this is contained so ignored by super
			cgExecutorOperation.accept(this);
		}
		return super.visitCGExecutorOperationCallExp(cgExecutorOperationCallExp);
	}  */

	@Override
	public @Nullable Object visitCGExecutorOppositePropertyCallExp(@NonNull CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp) {
		CGExecutorProperty cgExecutorProperty = cgExecutorPropertyCallExp.getExecutorProperty();
		if (cgExecutorProperty != null) {
			cgExecutorProperty.accept(this);
		}
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
		CGProperty cgProperty = cgExecutorPropertyCallExp.getReferredProperty();
		if (cgProperty != null) {
			cgProperty.accept(this);
		}
		return super.visitCGExecutorPropertyCallExp(cgExecutorPropertyCallExp);
	}

	@Override
	public @Nullable Object visitCGExecutorType(@NonNull CGExecutorType cgExecutorType) {
	//	installIdResolverVariable(cgExecutorType);		-- in visitCGTypeExp
		CGTypeId cgTypeId = cgExecutorType.getUnderlyingTypeId();
		if (cgTypeId != null) {
			cgTypeId.accept(this);
		}
		Type asType = CGUtil.getAST(cgExecutorType);
		Type asMetaType = analyzer.getCodeGenerator().getEnvironmentFactory().getStandardLibrary().getMetaclass(asType);
		cgExecutorType.setTypeId(analyzer.getCGTypeId(asMetaType.getTypeId()));
		return super.visitCGExecutorType(cgExecutorType);
	}

	@Override
	public @Nullable Object visitCGForeignProperty(@NonNull CGForeignProperty cgForeignProperty) {
		installExecutorVariable(cgForeignProperty);
		super.visitCGProperty(cgForeignProperty);
		wrapLetVariables(cgForeignProperty);
		return null;
	}

	@Override
	public @Nullable Object visitCGIsEqualExp(@NonNull CGIsEqualExp cgIsEqualExp) {
		installIdResolverVariable(cgIsEqualExp);
		return super.visitCGIsEqualExp(cgIsEqualExp);
	}

	@Override
	public @Nullable Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		doValuedElement(cgIterationCallExp);				// Resolve name in outer context
		doTypedElement(cgIterationCallExp);
		CGValuedElement cgSource = cgIterationCallExp.getSource();
		if (cgSource != null) {
			cgSource.accept(this);
		}
		for (CGIterator cgIterator : CGUtil.getIterators(cgIterationCallExp)) {
			cgIterator.accept(this);
		}
		if (cgIterationCallExp instanceof CGBuiltInIterationCallExp) {
			CGIterator cgAccumulator = ((CGBuiltInIterationCallExp)cgIterationCallExp).getAccumulator();
			if (cgAccumulator != null) {
				cgAccumulator.accept(this);
			}
		}
		CGValuedElement cgBody = cgIterationCallExp.getBody();
		if (cgBody != null) {
			cgBody.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGLibraryIterateCallExp(@NonNull CGLibraryIterateCallExp cgLibraryIterateCallExp) {
		installStandardLibraryVariable(cgLibraryIterateCallExp);
		return super.visitCGLibraryIterateCallExp(cgLibraryIterateCallExp);
	}

	@Override
	public @Nullable Object visitCGLibraryIterationCallExp(@NonNull CGLibraryIterationCallExp cgLibraryIterationCallExp) {
		installStandardLibraryVariable(cgLibraryIterationCallExp);
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
			TypeId asTypeId = cgPropertyCallExp.getASTypeId();
			if (asTypeId != null) {
				addOwnedTypeId(cgPropertyCallExp, asTypeId);
			}
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
	public @Nullable Object visitCGNativeProperty(@NonNull CGNativeProperty cgProperty) {
		super.visitCGNativeProperty(cgProperty);
		wrapLetVariables(cgProperty);
		return null;
	}

	@Override
	public @Nullable Object visitCGOperation(@NonNull CGOperation cgOperation) {
		super.visitCGOperation(cgOperation);
		wrapLetVariables(cgOperation);
		return null;
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
		//		currentNameManager.addLocalVariable(cgExecutorConstructorPart);
		installIdResolverVariable(cgShadowPart);
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
			installIdResolverVariable(cgTypeExp);
			cgType.accept(this);
		}
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
		CGValuedElement source = analyzer.getCGExpression(cgUnboxExp.getSource());
		TypeDescriptor boxedTypeDescriptor = context.getTypeDescriptor(source);
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

	protected void wrapLetVariables(@NonNull CGNamedElement cgNamedlement) {
		ExecutableNameManager executableNameManager = analyzer.useExecutableNameManager(CGUtil.getAST(cgNamedlement)).getRootExecutableNameManager();
	//	assert executableNameManager == executableNameManager.getRootExecutableNameManager();
		CGValuedElement cgTree = executableNameManager.getBody();
		if (cgTree != null) {
			cgTree = executableNameManager.wrapLetVariables(cgTree);
		}
	}
}
