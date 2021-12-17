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
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLocalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModel;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.utilities.CGModelResource;
import org.eclipse.ocl.examples.codegen.utilities.CGModelResourceFactory;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * Converts an OCL expression to a string for debugging. This is not intended to
 * be used by client applications as an AST-to-text transformation.
 */
public class CG2StringVisitor extends AbstractExtendingCGModelVisitor<@Nullable String, @Nullable Object>
{
	private static final Logger logger = Logger.getLogger(CG2StringVisitor.class);

	public static interface Factory extends Adapter {
		@NonNull CG2StringVisitor createToStringVisitor();
		@NonNull EPackage getEPackage();
	}

	private static @NonNull Map<EPackage, Factory> factoryMap = new HashMap<EPackage, Factory>();

	public static synchronized void addFactory(@NonNull Factory factory) {
		factoryMap.put(factory.getEPackage(), factory);
	}

	public static String toString(@NonNull CGElement cgElement) {
		try {
			Resource resource = cgElement.eResource();
			if (resource instanceof CGModelResource) {
				CGModelResourceFactory resourceFactory = ((CGModelResource)resource).getResourceFactory();
				CG2StringVisitor v = resourceFactory.createToStringVisitor();
				cgElement.accept(v);
				return v.toString();
			}
			EPackage ePackage = cgElement.eClass().getEPackage();
			Factory factory = factoryMap.get(ePackage);
			if (factory == null) {
				logger.error("No CG2StringVisitor Factory registered for " + ePackage.getName());
				return "null";
			}
			CG2StringVisitor v = factory.createToStringVisitor();
			cgElement.accept(v);
			return v.toString();
		}
		catch (Throwable e) {
			return e.toString();
		}
	}

	protected abstract static class AbstractFactory extends AdapterImpl implements CG2StringVisitor.Factory
	{
		@Override
		public boolean isAdapterForType(Object type) {
			return type == CG2StringVisitor.Factory.class;
		}
	}

	private static final class MyFactory extends AbstractFactory
	{
		private MyFactory() {
			CG2StringVisitor.addFactory(this);
		}

		@Override
		public @NonNull CG2StringVisitor createToStringVisitor() {
			return new CG2StringVisitor();
		}

		@Override
		public @NonNull EPackage getEPackage() {
			CGModelPackage eInstance = CGModelPackage.eINSTANCE;
			assert eInstance != null;
			return eInstance;
		}
	}

	public static CG2StringVisitor.@NonNull Factory FACTORY = new MyFactory();

	/**
	 * Indicates where a required element in the AST was <code>null</code>, so
	 * that it is evident in the debugger that something was missing. We don't
	 * want just <code>"null"</code> because that would look like the OclVoid
	 * literal.
	 */
	protected static @NonNull String NULL_PLACEHOLDER = "\"<null>\""; //$NON-NLS-1$

	protected @NonNull StringBuilder result = new StringBuilder();

	/**
	 * Initializes me.
	 */
	public CG2StringVisitor() {
		super(Object.class);						// Useless dummy object as context
	}

	protected void append(Number number) {
		if (number != null) {
			result.append(number.toString());
		}
		else {
			result.append(NULL_PLACEHOLDER);
		}
	}

	protected void append(String string) {
		if (string != null) {
			result.append(string);
		}
		else {
			result.append(NULL_PLACEHOLDER);
		}
	}

	protected void appendElementType(@Nullable CGTypedElement cgTypedElement) {
		if (cgTypedElement == null) {
			append(NULL_PLACEHOLDER);
		}
		else {
			safeVisit(cgTypedElement.getTypeId());
			if (!cgTypedElement.isRequired()) {			// FIXME use CGVariable isNonNull too
				append("[?]");
			}
		}
	}

	protected void appendName(CGNamedElement cgNamedElement) {
		if (cgNamedElement == null) {
			result.append(NULL_PLACEHOLDER);
		}
		else {
			result.append(cgNamedElement.getName());
		}
	}

	protected void appendName(NamedElement namedElement) {
		if (namedElement == null) {
			result.append(NULL_PLACEHOLDER);
		}
		else {
			result.append(namedElement.getName());
		}
	}

	protected void appendOperationSignature(CGOperation cgOperation) {
		appendName(cgOperation);
		append("(");
		boolean comma = false;
		for (java.util.Iterator<CGParameter> iter = cgOperation.getParameters().iterator(); iter.hasNext();) {
			CGParameter parm = iter.next();

			if (comma) {
				append(", "); //$NON-NLS-1$
			} else {
				comma = true;
			}

			appendName(parm);
			append(" : "); //$NON-NLS-1$

			if (parm.getTypeId() != null) {
				appendElementType(parm);
			} else {
				append(TypeId.OCL_VOID_NAME);
			}
		}

		append(") :"); //$NON-NLS-1$
		if (cgOperation.getTypeId() != null) {
			append(" ");
			appendElementType(cgOperation);
		}
	}

	protected void appendPropertySignature(CGTypedElement cgElement) {
		appendName(cgElement);
		if (cgElement.getTypeId() != null) {
			append(" : ");
			appendElementType(cgElement);
		}
	}

	protected void appendQualifiedName(CGNamedElement parent, String separator, CGNamedElement child) {
		if (parent != null) {
			appendQualifiedName(parent);
			append(separator);
		}
		appendName(child);
	}

	protected void appendQualifiedName(@Nullable CGNamedElement object) {
		if (object == null) {
			result.append(NULL_PLACEHOLDER);
		}
		else {
			EObject container = object.eContainer();
			if (container instanceof CGNamedElement) {
				appendQualifiedName((CGNamedElement) container);
				append("::"); //$NON-NLS-1$
			}
			appendName(object);
		}
	}

	@Override
	public String toString() {
		return result.toString();
	}

	@Override
	public @Nullable String visitCGAssertNonNullExp(@NonNull CGAssertNonNullExp cgAssertNonNullExp) {
		append("$ASSERT_NON_NULL("); //$NON-NLS-1$
		safeVisit(cgAssertNonNullExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGBoxExp(@NonNull CGBoxExp cgBoxExp) {
		append("$BOX("); //$NON-NLS-1$
		safeVisit(cgBoxExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGBuiltInIterationCallExp(@NonNull CGBuiltInIterationCallExp cgExp) {
		safeVisit(cgExp.getSource());
		append("->");
		appendName(cgExp.getReferredIteration());
		append("("); //$NON-NLS-1$
		boolean isFirst = true;
		List<CGIterator> cgIterators = cgExp.getIterators();
		List<CGIterator> cgCoIterators = cgExp.getCoIterators();
		for (int i = 0; i < cgIterators.size(); i++) {
			CGValuedElement cgIterator = cgIterators.get(i);
			if (!isFirst) {
				append(", ");
			}
			safeVisit(cgIterator);
			if (i < cgCoIterators.size()) {
				CGValuedElement cgCoIterator = cgCoIterators.get(i);
				append(" with ");
				safeVisit(cgCoIterator);
			}
			isFirst = false;
		}
		CGIterator cgAccumulator = cgExp.getAccumulator();
		if (cgAccumulator != null) {
			append("; ");
			safeVisit(cgAccumulator);
		}
		append(" | ");
		safeVisit(cgExp.getBody());
		append(")");//$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGCastExp(@NonNull CGCastExp cgCastExp) {
		append("$CAST("); //$NON-NLS-1$
		safeVisit(cgCastExp.getExecutorType());
		append(","); //$NON-NLS-1$
		safeVisit(cgCastExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGCatchExp(@NonNull CGCatchExp cgCatchExp) {
		append("$CATCH("); //$NON-NLS-1$
		safeVisit(cgCatchExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public String visitCGClass(@NonNull CGClass cgClass) {
		appendQualifiedName(cgClass);
		return null;
	}

	@Override
	public @Nullable String visitCGCollectionExp(@NonNull CGCollectionExp cgCollectionExp) {
		append(((CollectionLiteralExp)cgCollectionExp.getAst()).getKind() + "{");//$NON-NLS-1$
		boolean isFirst = true;
		for (CGCollectionPart cgPart : cgCollectionExp.getParts()) {
			if (!isFirst) {
				append(", ");
			}
			safeVisit(cgPart);
			isFirst = false;
		}
		append("}");
		return null;
	}

	@Override
	public @Nullable String visitCGCollectionPart(@NonNull CGCollectionPart cgCollectionPart) {
		safeVisit(cgCollectionPart.getFirst());
		if (cgCollectionPart.isRange()) {
			append(" .. ");
			safeVisit(cgCollectionPart.getLast());
		}
		return null;
	}

	@Override
	public @Nullable String visitCGConstant(@NonNull CGConstant cgConstant) {
		append(cgConstant.getConstantValue().toString());
		return null;
	}

	@Override
	public @Nullable String visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		CGValuedElement referredConstant = cgConstantExp.getReferredConstant();
		if (referredConstant != null) {
			referredConstant.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable String visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		appendName(cgConstraint);
		append(": ");
		safeVisit(cgConstraint.getBody());
		return null;
	}

	@Override
	public @Nullable String visitCGEcoreExp(@NonNull CGEcoreExp cgEcoreExp) {
		String simpleName = "null";
		EClassifier eClassifier = cgEcoreExp.getEClassifier();
		if (eClassifier != null) {
			Class<?> instanceClass = eClassifier.getInstanceClass();
			if (instanceClass != null) {
				simpleName = instanceClass.getSimpleName();
			}
		}
		append("$ECORE("); //$NON-NLS-1$
		append(simpleName);
		append(","); //$NON-NLS-1$
		safeVisit(cgEcoreExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGGuardExp(@NonNull CGGuardExp cgGuardExp) {
		append("$GUARD("); //$NON-NLS-1$
		safeVisit(cgGuardExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIfExp(@NonNull CGIfExp cgExp) {
		append("if ");  //$NON-NLS-1$
		safeVisit(cgExp.getCondition());
		append(" then "); //$NON-NLS-1$
		safeVisit(cgExp.getThenExpression());
		append(" else "); //$NON-NLS-1$
		safeVisit(cgExp.getElseExpression());
		append(" endif"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGInvalid(@NonNull CGInvalid cgInvalid) {
		String messageTemplate = cgInvalid.getMessageTemplate();
		if (messageTemplate != null) {
			append("$INVALID(" + messageTemplate + ")");
		}
		else {
			append("invalid");
		}
		return null;
	}

	@Override
	public @Nullable String visitCGIsEqualExp(@NonNull CGIsEqualExp cgIsEqualExp) {
		append(cgIsEqualExp.isNotEquals() ? "$isNotEQUAL(" : "$isEQUAL("); //$NON-NLS-1$
		safeVisit(cgIsEqualExp.getSource());
		append(","); //$NON-NLS-1$
		safeVisit(cgIsEqualExp.getArgument());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp cgIsEqualExp) {
		append("$isEQUAL2("); //$NON-NLS-1$
		safeVisit(cgIsEqualExp.getSource());
		append(","); //$NON-NLS-1$
		safeVisit(cgIsEqualExp.getArgument());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgIsInvalidExp) {
		append("$isINVALID("); //$NON-NLS-1$
		safeVisit(cgIsInvalidExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIsKindOfExp(@NonNull CGIsKindOfExp cgIsKindOfExp) {
		append("$isKindOf("); //$NON-NLS-1$
		safeVisit(cgIsKindOfExp.getExecutorType());
		append(","); //$NON-NLS-1$
		safeVisit(cgIsKindOfExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
		append("$isUNDEFINED("); //$NON-NLS-1$
		safeVisit(cgIsUndefinedExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGIterationCallExp(@NonNull CGIterationCallExp ic) {
		CGValuedElement source = ic.getSource();
		safeVisit(source);
		LoopExp iterationCallExp = (LoopExp) ic.getAst();
		Iteration iter = iterationCallExp.getReferredIteration();
		Type sourceType = source != null ? iterationCallExp.getOwnedSource().getType() : null;
		append(PivotUtil.getNavigationOperator(false/*iterationCallExp.isIsSafe()*/, PivotUtil.isAggregate(sourceType)));
		appendName(iter);
		append("(");
		String prefix = "";//$NON-NLS-1$
		List<CGIterator> cgIterators = ic.getIterators();
		List<CGIterator> cgCoIterators = ic.getCoIterators();
		for (int i = 0; i < cgIterators.size(); i++) {
			CGValuedElement cgIterator = cgIterators.get(i);
			append(prefix);
			safeVisit(cgIterator);
			if (i < cgCoIterators.size()) {
				CGValuedElement cgCoIterator = cgCoIterators.get(i);
				append(" with ");
				safeVisit(cgCoIterator);
			}
			prefix = ", ";//$NON-NLS-1$
		}
		if (ic instanceof CGLibraryIterateCallExp) {
			append("; ");
			safeVisit(((CGLibraryIterateCallExp)ic).getResult());
		}
		append(" | ");
		safeVisit(ic.getBody());
		append(")");
		//		appendAtPre(oc);
		return null;
	}

	@Override
	public @Nullable String visitCGIterator(@NonNull CGIterator cgElement) {
		appendName(cgElement);
		CGTypeId type = cgElement.getTypeId();
		if (type != null) {
			append(" : ");
			appendElementType(cgElement);
		}
		CGValuedElement init = cgElement.getInit();
		if (init != null) {
			append(" = ");
			init.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable String visitCGLetExp(@NonNull CGLetExp cgLetExp) {
		append("let "); //$NON-NLS-1$
		safeVisit(cgLetExp.getInit());
		append(" in ("); //$NON-NLS-1$
		safeVisit(cgLetExp.getIn());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp oc) {
		OperationCallExp operationCallExp = (OperationCallExp) oc.getAst();
		if (operationCallExp == null) {
			String name;
			LibraryOperation libraryOperation = oc.getLibraryOperation();
			if (libraryOperation == CollectionExcludingOperation.INSTANCE) {
				name = "excluding";
			}
			else {
				name = libraryOperation.getClass().getSimpleName();
			}
			CGValuedElement source = oc.getSource();
			safeVisit(source);
			append(".");
			append(name);
			append("(");
			String prefix = "";//$NON-NLS-1$
			for (CGValuedElement argument : oc.getArguments()) {
				append(prefix);
				safeVisit(argument);
				prefix = ", ";//$NON-NLS-1$
			}
			append(")");
			//			appendAtPre(oc);
			return null;
		}
		return visitCGOperationCallExp(oc);
	}

	@Override
	public @Nullable String visitCGLocalVariable(@NonNull CGLocalVariable cgVariable) {
		appendName(cgVariable);
		CGTypeId type = cgVariable.getTypeId();
		if (type != null) {
			append(" : ");
			appendElementType(cgVariable);
		}
		CGValuedElement initExpression = cgVariable.getInit();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}
		return null;
	}

	@Override
	public @Nullable String visitCGMapExp(@NonNull CGMapExp cgMapExp) {
		append("Map{");//$NON-NLS-1$
		boolean isFirst = true;
		for (CGMapPart cgPart : cgMapExp.getParts()) {
			if (!isFirst) {
				append(", ");
			}
			safeVisit(cgPart);
			isFirst = false;
		}
		append("}");
		return null;
	}

	@Override
	public @Nullable String visitCGMapPart(@NonNull CGMapPart cgMapPart) {
		safeVisit(cgMapPart.getKey());
		append(" with ");
		safeVisit(cgMapPart.getValue());
		return null;
	}

	@Override
	public @Nullable String visitCGModel(@NonNull CGModel cgModel) {
		appendName(cgModel);
		return null;
	}

	@Override
	public @Nullable String visitCGOperation(@NonNull CGOperation cgOperation) {
		appendQualifiedName(cgOperation.getContainingClass(), ".", cgOperation);
		append("(");
		boolean isFirst = true;
		for (CGParameter cgParameter : cgOperation.getParameters()) {
			if (!isFirst) {
				append(",");
			}
			appendElementType(cgParameter);
			isFirst = false;
		}
		append(") : ");
		appendElementType(cgOperation);
		return null;
	}

	@Override
	public @Nullable String visitCGOperationCallExp(@NonNull CGOperationCallExp oc) {
		CGValuedElement source = oc.getSource();
		safeVisit(source);
		OperationCallExp operationCallExp = (OperationCallExp) oc.getAst();
		Operation oper = operationCallExp.getReferredOperation();
		Type sourceType = operationCallExp.getOwnedSource() != null ? operationCallExp.getOwnedSource().getType() : null;
		append(PivotUtil.getNavigationOperator(false/*operationCallExp.isIsSafe()*/, PivotUtil.isAggregate(sourceType)));
		appendName(oper);
		append("(");
		String prefix = "";//$NON-NLS-1$
		for (CGValuedElement argument : oc.getArguments()) {
			append(prefix);
			safeVisit(argument);
			prefix = ", ";//$NON-NLS-1$
		}
		append(")");
		//		appendAtPre(oc);
		return null;
	}

	@Override
	public @Nullable String visitCGOppositePropertyCallExp(@NonNull CGOppositePropertyCallExp pc) {
		// source is null when the property call expression is an
		//    association class navigation qualifier
		CGValuedElement source = pc.getSource();
		safeVisit(source);
		OppositePropertyCallExp propertyCallExp = (OppositePropertyCallExp) pc.getAst();
		Property oppositeProperty = propertyCallExp.getReferredProperty();
		Property property = oppositeProperty.getOpposite();
		Type sourceType = source != null ? propertyCallExp.getOwnedSource().getType() : null;
		append(PivotUtil.getNavigationOperator(false/*propertyCallExp.isIsSafe()*/, PivotUtil.isAggregate(sourceType)));
		appendName(property);
		/*		appendAtPre(pc);
        List<CGValuedElement> qualifiers = pc.getQualifier();
		if (!qualifiers.isEmpty()) {
			append("["); //$NON-NLS-1$
			String prefix = ""; //$NON-NLS-1$
			for (OCLExpression qualifier : qualifiers) {
				append(prefix);
				safeVisit(qualifier);
				prefix = ", "; //$NON-NLS-1$
			}
			append("]");
		} */
		return null;
	}

	@Override
	public @Nullable String visitCGPackage(@NonNull CGPackage cgPackage) {
		appendQualifiedName(cgPackage);
		return null;
	}

	@Override
	public @Nullable String visitCGPropertyCallExp(@NonNull CGPropertyCallExp pc) {
		// source is null when the property call expression is an
		//    association class navigation qualifier
		CGValuedElement source = pc.getSource();
		safeVisit(source);
		PropertyCallExp propertyCallExp = (PropertyCallExp) pc.getAst();
		if (propertyCallExp != null) {
			Property property = propertyCallExp.getReferredProperty();
			Type sourceType = source != null ? propertyCallExp.getOwnedSource().getType() : null;
			append(PivotUtil.getNavigationOperator(false/*propertyCallExp.isIsSafe()*/, PivotUtil.isAggregate(sourceType)));
			appendName(property);
		}
		else {
			append("\"<.>\"");
			appendName((CGNamedElement)null);
		}
		/*		appendAtPre(pc);
        List<CGValuedElement> qualifiers = pc.getQualifier();
		if (!qualifiers.isEmpty()) {
			append("["); //$NON-NLS-1$
			String prefix = ""; //$NON-NLS-1$
			for (OCLExpression qualifier : qualifiers) {
				append(prefix);
				safeVisit(qualifier);
				prefix = ", "; //$NON-NLS-1$
			}
			append("]");
		} */
		return null;
	}

	//	@Override
	//	public @Nullable String visitCGSelfParameter(@NonNull CGSelfParameter cgSelfParameter) {
	//		append("this");
	//		return null;
	//	}

	@Override
	public @Nullable String visitCGShadowExp(@NonNull CGShadowExp cgShadowExp) {
		appendName(((ShadowExp)cgShadowExp.getAst()).getType());
		append("{");//$NON-NLS-1$
		boolean isFirst = true;
		for (CGShadowPart cgPart : cgShadowExp.getParts()) {
			if (!isFirst) {
				append(", ");
			}
			safeVisit(cgPart);
			isFirst = false;
		}
		append("}");
		return null;
	}

	@Override
	public @Nullable String visitCGShadowPart(@NonNull CGShadowPart cgShadowPart) {
		appendName(((ShadowPart)cgShadowPart.getAst()).getReferredProperty());
		append(" with ");
		safeVisit(cgShadowPart.getInit());
		return null;
	}

	@Override
	public @Nullable String visitCGString(@NonNull CGString cgString) {
		append("'");
		append(cgString.getStringValue());
		append("'");
		return null;
	}

	@Override
	public @Nullable String visitCGThrowExp(@NonNull CGThrowExp cgThrowExp) {
		append("$THROW("); //$NON-NLS-1$
		safeVisit(cgThrowExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGTupleExp(@NonNull CGTupleExp cgTupleExp) {
		append("Tuple{");//$NON-NLS-1$
		String prefix = "";
		for (CGTuplePart part : cgTupleExp.getParts()) {
			append(prefix);
			safeVisit(part);
			prefix = ", ";//$NON-NLS-1$
		}
		append("}");
		return null;
	}

	@Override
	public @Nullable String visitCGTuplePart(@NonNull CGTuplePart cgTuplePart) {
		appendName(cgTuplePart);
		CGTypeId type = cgTuplePart.getTypeId();
		if (type != null) {
			append(" : ");
			appendElementType(cgTuplePart);
		}
		CGValuedElement initExpression = cgTuplePart.getInit();
		if (initExpression != null) {
			append(" = ");
			safeVisit(initExpression);
		}
		return null;
	}

	@Override
	public @Nullable String visitCGUnboxExp(@NonNull CGUnboxExp cgUnboxExp) {
		append("$UNBOX("); //$NON-NLS-1$
		safeVisit(cgUnboxExp.getSource());
		append(")"); //$NON-NLS-1$
		return null;
	}

	@Override
	public @Nullable String visitCGValuedElement(@NonNull CGValuedElement cgElement) {
		appendName(cgElement);
		//		CGTypeId type = cgElement.getTypeId();
		//		append(" : ");
		//		if (type != null) {
		//			appendElementType(cgElement);
		//		}
		return null;
	}

	@Override
	public @Nullable String visitCGVariable(@NonNull CGVariable cgElement) {
		appendName(cgElement);
		CGValuedElement init = cgElement.getInit();
		if (init != null) {
			append(" = ");
			init.accept(this);
		}
		else {
			CGTypeId type = cgElement.getTypeId();
			if (type != null) {
				append(" : ");
				appendElementType(cgElement);
			}
		}
		return null;
	}

	@Override
	public @Nullable String visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		appendName(cgVariableExp.getReferredVariable());
		return null;
	}

	@Override
	public @Nullable String visiting(@NonNull CGElement visitable) {
		append(visitable.getClass().getName());
		return null;
	}
}
