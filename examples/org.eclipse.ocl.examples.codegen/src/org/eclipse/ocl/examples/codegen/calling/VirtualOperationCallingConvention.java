/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.library.LibraryOperation;

import com.google.common.collect.Iterables;

/**
 *  VirtualOperationCallingConvention defines the support for the call of a Complete OCL-defined operation
 *  that requires dynamic redirection to the appropriate overload.
 */
public class VirtualOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ForeignOperationCallingConvention
{
	public static final @NonNull VirtualOperationCallingConvention INSTANCE = new VirtualOperationCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();
/*		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		CGClass currentClass = as2cgVisitor.basicGetCurrentClass();
		assert currentClass == null;
		CGOperationCallExp cgCallExp = null;
		if (finalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
				assert cgOperationCallExp2 == null;
				cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, finalOperation, (ConstrainedOperation)libraryOperation);
			}
		}
		if (cgCallExp == null) {
			Operation baseOperation = asOperation;	// FIXME
			cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, baseOperation, (ConstrainedOperation)libraryOperation);
		}
		cgCallExp.setCgOperation(cgOperation);
		return cgCallExp; */
	}

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @Nullable Type asSourceType, @NonNull Operation asOperation) {
		FinalAnalysis finalAnalysis = as2cgVisitor.getMetamodelManager().getFinalAnalysis();
		Iterable<@NonNull Operation> asOverrideOperations = finalAnalysis.getOverrides(asOperation);
		assert Iterables.contains(asOverrideOperations, asOperation);
		for (@NonNull Operation asOverrideOperation : asOverrideOperations) {
			CGOperation cgOverrideOperation = as2cgVisitor.generateOperationDeclaration(null, asOverrideOperation, true);


		/*	CGOperation cgOperation = asFinalOperation2cgOperation.get(asOverride);
			if (cgOperation == null) {
				cgOperation = createFinalCGOperationWithoutBody(asOverride);
				pushLocalContext(cgOperation, asOverride);
				popLocalContext(cgOperation);
				asNewOperations.add(asOverride);
			}
			cgOperations.add((CGCachedOperation) cgOperation); */
		}
	//		if (Iterables.size(asOverrides) > 1) {
	//			return VirtualOperationCallingConvention.INSTANCE;		// Need a polymorphic dispatcher
	//		}
	//	}
	//	throw new UnsupportedOperationException();
/*		assert as2cgVisitor.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation(); */
	//	CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
	//	cgOperationCallExp.setSource(cgSource);
	//	cgOperationCallExp.setThisIsSelf(false);
	//	for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
	//		CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
	//		cgOperationCallExp.getArguments().add(cgArgument);
	//	}
	//	setAst(cgOperationCallExp, element);
	//	cgOperationCallExp.setReferredOperation(asOperation);
	//	if (asOverrideOperations != null) {
	//		CGOperation cgOperation = createVirtualCGOperationWithoutBody(asOperation, cgOperations);
				//				asNewOperations.add(asOperation);
	//			currentClass.getOperations().add(cgOperation);
	//		}
	//	}
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		as2cgVisitor.getAnalyzer().addVirtualCGOperation(asOperation, cgOperation);
		return cgOperation;
	}

	/*protected public @NonNull CGOperation createVirtualCGOperationWithoutBody(@NonNull Operation asOperation, @NonNull List<@NonNull CGCachedOperation> cgOperations) {
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		initAst(cgOperation, asOperation);
		LocalContext savedLocalContext = pushLocalContext(cgOperation, asOperation);
		cgOperation.setRequired(asOperation.isIsRequired());
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification != null) {
			Variables savedVariablesStack = variablesStack;
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				variablesStack = new Variables(null);
				createParameters(cgOperation, query);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				variablesStack = savedVariablesStack;
			}
		}
		cgOperation.getFinalOperations().addAll(cgOperations);
		context.addVirtualCGOperation(asOperation, cgOperation);
		popLocalContext(savedLocalContext);
		return cgOperation;
	} */

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
	//	super.createCGParameters(as2cgVisitor, cgOperation, bodyExpression);
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();
	/*	// XXX should have been ForeignOperationCallingConvention
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}


	/ *	Operation pOperation = cgOperationCallExp.getReferredOperation();
		boolean thisIsSelf = cgOperationCallExp.isThisIsSelf();
	//	CGValuedElement source = cg2javaVisitor.getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
	//	if (!js.appendLocalStatements(source)) {
	//		return false;
	//	}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		} * /




		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2javaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true; */
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
	//	return super.generateJavaDeclaration(cg2javaVisitor, js, cgOperation);
		throw new UnsupportedOperationException();
	}

/*	protected @NonNull CGOperationCallExp volatileOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull Operation finalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(constrainedOperation);
		if (cgSource != null) {
			cgOperationCallExp.getCgArguments().add(cgSource);
		}
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getCgArguments().add(cgArgument);
		}
		as2cgVisitor.initAst(cgOperationCallExp, element);
	//	as2cgVisitor.declareLazyName(cgOperationCallExp);
		cgOperationCallExp.setReferredOperation(finalOperation);
		if (as2cgVisitor.getCodeGenerator().addConstrainedOperation(finalOperation)) {
		}
		return cgOperationCallExp;
	} */
}
