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
package org.eclipse.ocl.examples.codegen.generator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.AnalysisVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.BuiltInIterationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.BuiltInOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.DefaultOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ConstrainedPropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ContextClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.EcoreForeignOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.EcoreOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.EcoreOppositePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.EcorePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ExecutorCompositionPropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ExecutorOppositePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ExecutorPropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ForeignOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ForeignPropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.InlinedOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.LibraryOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.LibraryPropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.NativeOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.NativePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.TuplePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.VirtualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.VolatileOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport.JavaNativeOperation;
import org.eclipse.ocl.examples.codegen.library.NativeVisitorOperation;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.AbstractStaticOperation;
import org.eclipse.ocl.pivot.internal.library.CompositionProperty;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.ConstrainedProperty;
import org.eclipse.ocl.pivot.internal.library.EInvokeOperation;
import org.eclipse.ocl.pivot.internal.library.ExplicitNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.ExtensionProperty;
import org.eclipse.ocl.pivot.internal.library.ForeignProperty;
import org.eclipse.ocl.pivot.internal.library.ImplicitNonCompositionProperty;
import org.eclipse.ocl.pivot.internal.library.StaticProperty;
import org.eclipse.ocl.pivot.internal.library.StereotypeProperty;
import org.eclipse.ocl.pivot.internal.library.TuplePartProperty;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.NativeOperation;
import org.eclipse.ocl.pivot.library.NativeProperty;
//import org.eclipse.ocl.pivot.library.NativeOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionElementTypeProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionLowerProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionUpperProperty;
import org.eclipse.ocl.pivot.library.map.MapKeyTypeProperty;
import org.eclipse.ocl.pivot.library.map.MapValueTypeProperty;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotHelper;

import com.google.common.collect.Iterables;

public abstract class AbstractCodeGenerator implements CodeGenerator
{
	public static final @NonNull String ORG_ECLIPSE_JDT_ANNOTATION_NON_NULL = "org.eclipse.jdt.annotation.NonNull";
	public static final @NonNull String ORG_ECLIPSE_JDT_ANNOTATION_NULLABLE = "org.eclipse.jdt.annotation.Nullable";

	protected final @NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull PivotHelper asHelper;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull GenModelHelper genModelHelper;
	private /*@LazyNonNull*/ Set<@NonNull Operation> constrainedOperations = null;

	private /*@LazyNonNull*/ CodeGenOptions options = null;
	//
	private /*@LazyNonNull*/ List<@NonNull Exception> problems = null;
	private @NonNull String defaultIndent = "    ";

//	private final @NonNull Map<@NonNull CGNamedElement, @NonNull DebugLocalContext> debugContexts = new HashMap<>();

/*	public class DebugLocalContext
	{
		private @Nullable DebugLocalContext outerContext;
		private @NonNull CGNamedElement cgNamedElement;
		private @NonNull NamedElement asNamedElement;
		private @NonNull LocalContext localContext;

		public DebugLocalContext(@Nullable DebugLocalContext outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
			this.outerContext = outerContext;
			this.cgNamedElement = cgNamedElement;
			this.asNamedElement = asNamedElement;
			assert asNamedElement == cgNamedElement.getAst();
			JavaLocalContext<@NonNull ?> outerContext2 = outerContext  != null ? (JavaLocalContext<@NonNull ?>) outerContext.getLocalContext() : null;
			localContext = ((JavaGlobalContext<?>)getGlobalContext()).createLocalContext(outerContext2, cgNamedElement, asNamedElement);
		}

		public @NonNull LocalContext getLocalContext() {
			return localContext;
		}
	} */

//	public DebugLocalContext debugGet(CGNamedElement cgNamedElement) {
//		return debugContexts.get(cgNamedElement);
//	}

	protected AbstractCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel) {
		this.environmentFactory = (EnvironmentFactoryInternalExtension) environmentFactory;
		this.asHelper = new PivotHelper(environmentFactory);
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.globalNameManager = createGlobalNameManager();
		this.genModelHelper = createGenModelHelper(genModel);
		JavaLanguageSupport.FACTORY.install();
	}

	protected AbstractCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull GlobalNameManager globalNameManager,
			@NonNull GenModelHelper genModelHelper) {
		this.environmentFactory = (EnvironmentFactoryInternalExtension) environmentFactory;
		this.asHelper = new PivotHelper(environmentFactory);
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.globalNameManager = globalNameManager;
		this.genModelHelper = genModelHelper;
		JavaLanguageSupport.FACTORY.install();
	}

	@Override
	public boolean addConstrainedOperation(@NonNull Operation constrainedOperation) {
		if (constrainedOperations == null) {
			constrainedOperations = new HashSet<>();
		}
		return constrainedOperations.add(constrainedOperation);
	}

	@Override
	public void addProblem(@NonNull Exception problem) {
		List<@NonNull Exception> problems2 = problems;
		if (problems2 == null) {
			problems = problems2 = new ArrayList<>();
		}
		problems2.add(problem);
	}

	@Override
	public @NonNull AnalysisVisitor createAnalysisVisitor() {
		return new AnalysisVisitor(getAnalyzer());
	}

	protected abstract @NonNull GenModelHelper createGenModelHelper(@Nullable GenModel genModel);

	protected abstract @NonNull GlobalNameManager createGlobalNameManager();

	public abstract @NonNull ImportNameManager createImportNameManager();

	protected @NonNull CodeGenOptions createOptions() {
		return new CodeGenOptions();
	}

	public abstract @NonNull AS2CGVisitor createAS2CGVisitor(@NonNull CodeGenAnalyzer codeGenAnalyzer);

	public @NonNull PivotHelper getASHelper() {
		return asHelper;
	}

	public @NonNull ClassCallingConvention getCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ContextClassCallingConvention.getInstance(asClass);
	}

	@Override
	public @NonNull OperationCallingConvention getCallingConvention(@NonNull Operation asOperation, boolean maybeVirtual) {
		if (asOperation instanceof Iteration) {
			return BuiltInIterationCallingConvention.getInstance(asOperation, maybeVirtual);
		}
		LibraryOperation libraryOperation = (LibraryOperation)metamodelManager.getImplementation(asOperation);
		if (BuiltInOperationCallingConvention.getInstance(asOperation, maybeVirtual).canHandle(libraryOperation)) {
			return BuiltInOperationCallingConvention.getInstance(asOperation, maybeVirtual);
		}
		if (libraryOperation instanceof AbstractStaticOperation) {
			return ForeignOperationCallingConvention.getInstance(asOperation, maybeVirtual);
		}
		if (libraryOperation instanceof NativeOperation) {
			if (libraryOperation instanceof JavaNativeOperation) {
				Method jMethod = ((JavaNativeOperation)libraryOperation).getMethod();
				if (SupportOperationCallingConvention.getInstance(jMethod).canHandle(jMethod)) {
					return SupportOperationCallingConvention.getInstance(asOperation, maybeVirtual);
				}
			}
			return NativeOperationCallingConvention.getInstance(asOperation, maybeVirtual);
		}
		if (libraryOperation instanceof NativeVisitorOperation) {		// XXX this might be obsolete
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression == null) {
				return NativeOperationCallingConvention.getInstance(asOperation, maybeVirtual);
			}
		//	CGValuedElement cgOperationCallExp2 = inlineOperationCall(element, bodyExpression);
		//	if (cgOperationCallExp2 != null) {
		//		return cgOperationCallExp2;
		//	}
		//	return NativeOperationCallingConvention.getInstance();
			throw new UnsupportedOperationException();
		}
		if (libraryOperation instanceof ConstrainedOperation) {
			if ("classes::Visitable::_lookupUnqualifiedPackage(String[1]) : classes::Package[?]".equals(libraryOperation.toString())) {
				getClass();			// XXX
			}
			if ("classes::Package::_unqualified_env_Class(OclElement[1]) : lookup::LookupEnvironment[1]".equals(libraryOperation.toString())) {
				getClass();			// XXX
			}
			org.eclipse.ocl.pivot.Package asPackage = asOperation.getOwningClass().getOwningPackage();
			if (asPackage instanceof Library) {
				return VolatileOperationCallingConvention.getInstance(asOperation, maybeVirtual);			// Library operations handle polymorphism internally
			}
			else {
				FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
				if (maybeVirtual) {
					Iterable<@NonNull Operation> asOverrides = finalAnalysis.getOverrides(asOperation);
					if (Iterables.size(asOverrides) > 1) {
						return VirtualOperationCallingConvention.getInstance(asOperation, maybeVirtual);		// Need a polymorphic dispatcher
					}
					LanguageExpression bodyExpression = asOperation.getBodyExpression();
					if (bodyExpression != null) {
						Set<@NonNull Operation> referencedFinalOperations = new HashSet<>();
						getTransitivelyReferencedFinalOperations(referencedFinalOperations, finalAnalysis, bodyExpression);
						if (!referencedFinalOperations.contains(asOperation)) {
							Iterable<@NonNull Operation> referencedNonFinalOperations = getReferencedNonFinalOperations(finalAnalysis, bodyExpression);
							if (referencedNonFinalOperations == null) {
								// FIXME a simple heavy wrt call count heuristic might avoid some unpleasant bloat
								return InlinedOperationCallingConvention.getInstance(asOperation, maybeVirtual);
							}
						}
					}
				}
				return DefaultOperationCallingConvention.getInstance(asOperation, maybeVirtual);
			}
		}
		if ((libraryOperation instanceof EObjectOperation) || (libraryOperation instanceof EInvokeOperation)) {
			if (EcoreOperationCallingConvention.getInstance(asOperation, maybeVirtual).canHandle(this, asOperation)) {
				return EcoreOperationCallingConvention.getInstance(asOperation, maybeVirtual);
			}
			org.eclipse.ocl.pivot.Class asType = asOperation.getOwningClass();
			String className = asType.getInstanceClassName();
			if (className != null) {
				return NativeOperationCallingConvention.getInstance(asOperation, maybeVirtual);
			}
			else {
				return EcoreForeignOperationCallingConvention.getInstance(asOperation, maybeVirtual);
			}
		}
		return LibraryOperationCallingConvention.getInstance(asOperation, maybeVirtual);
	}

	@Override
	public @NonNull PropertyCallingConvention getCallingConvention(@NonNull Property asProperty) {
		LibraryProperty libraryProperty = metamodelManager.getImplementation(null, null, asProperty);
		if (libraryProperty instanceof NativeProperty) {
			return NativePropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof OclElementOclContainerProperty) {
			return EcorePropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof CompositionProperty) {
		/*	EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					getGenModelHelper().getGetAccessor(eStructuralFeature);
					return EcoreOppositePropertyCallingConvention.getInstance();
				} catch (GenModelException e) {
					addProblem(e);		// FIXME drop through to better default
				}
			} */
			return ExecutorCompositionPropertyCallingConvention.getInstance(asProperty);
		}
		else if (/*(libraryProperty instanceof CompositionProperty) ||*/ (libraryProperty instanceof ImplicitNonCompositionProperty)) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					getGenModelHelper().getGetAccessor(eStructuralFeature);
					return EcoreOppositePropertyCallingConvention.getInstance(asProperty);
				} catch (GenModelException e) {
					addProblem(e);		// FIXME drop through to better default
				}
			}
			return ExecutorOppositePropertyCallingConvention.getInstance(asProperty);
		}
		if (libraryProperty instanceof ExtensionProperty) {
			return ExecutorOppositePropertyCallingConvention.getInstance(asProperty);	// opposite
		}
		else if (libraryProperty instanceof StaticProperty) {
			return ForeignPropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof TuplePartProperty) {
			return TuplePropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof ForeignProperty) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature)asProperty.getESObject();
			assert eStructuralFeature == null;
			return ForeignPropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof StereotypeProperty) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature)asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					getGenModelHelper().getGetAccessor(eStructuralFeature);
					return EcorePropertyCallingConvention.getInstance(asProperty);
				} catch (GenModelException e) {
					addProblem(e);		// FIXME drop through to better default
				}
			}
			return ConstrainedPropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof ConstrainedProperty) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			assert eStructuralFeature != null;
			try {
				getGenModelHelper().getGetAccessor(eStructuralFeature);
				return EcorePropertyCallingConvention.getInstance(asProperty);
			} catch (GenModelException e) {
				addProblem(e);		// FIXME drop through to better default
			}
			return ForeignPropertyCallingConvention.getInstance(asProperty);
		}
		else if (libraryProperty instanceof ExplicitNavigationProperty) {
				//	|| (libraryProperty instanceof CompositionProperty)
				//	|| (libraryProperty instanceof ImplicitNonCompositionProperty)		// FIXME surely this isn't Ecore
				//	|| (libraryProperty instanceof StaticProperty)
				//	|| (libraryProperty instanceof StereotypeProperty)) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					getGenModelHelper().getGetAccessor(eStructuralFeature);
					return EcorePropertyCallingConvention.getInstance(asProperty);
				} catch (GenModelException e) {
					addProblem(e);		// FIXME drop through to better default
				}
			}
			return ExecutorPropertyCallingConvention.getInstance(asProperty);		// Used by e.g. test_oclAsSet_351512 - JUnit of dynamic Ecore
		}
		else if ((libraryProperty instanceof OclElementOclContentsProperty)
				  || (libraryProperty instanceof CollectionElementTypeProperty)
				  || (libraryProperty instanceof CollectionLowerProperty)
				  || (libraryProperty instanceof CollectionUpperProperty)
				  || (libraryProperty instanceof MapKeyTypeProperty)
				  || (libraryProperty instanceof MapValueTypeProperty)) {
			return LibraryPropertyCallingConvention.getInstance(asProperty);
		}
		return LibraryPropertyCallingConvention.getInstance(asProperty);
	}

	protected @Nullable Iterable<@NonNull Operation> getConstrainedOperations() {
		return constrainedOperations;
	}

	@Override
	public @NonNull String getDefaultIndent() {
		return defaultIndent;
	}

	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	@Override
	public @NonNull GlobalNameManager getGlobalNameManager() {
		return globalNameManager;
	}

	@Override
	public @NonNull ImportNameManager getImportNameManager() {
		return globalNameManager.getImportNameManager();
	}

/*	@Override
	public @NonNull NameManager getNameManager() {
		return nameManager;
	} */

	@Override
	public @NonNull CodeGenOptions getOptions() {
		CodeGenOptions options2 = options;
		if (options2 == null) {
			options = options2 = createOptions();
		}
		return options2;
	}

	@Override
	public @Nullable List<@NonNull Exception> getProblems() {
		return problems;
	}

	/**
	 * Return all final operations directly referenced by opaqueExpression, or null if none.
	 * @since 1.3
	 */
	protected @Nullable Iterable<@NonNull Operation> getReferencedFinalOperations(@NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression specification) {
		ExpressionInOCL prototype = null;
		try {
			prototype = environmentFactory.parseSpecification(specification);
		}
		catch (ParserException e) {
			// FIXME log error
			e.printStackTrace();
		}
		if (prototype == null) {
			return null;
		}
		Set<@NonNull Operation> referencedOperations = null;
		for (EObject crossReference : EcoreUtil.ExternalCrossReferencer.find(prototype).keySet()) {
			if (crossReference instanceof Operation) {
				Operation operation = (Operation) crossReference;
				if (finalAnalysis.isFinal(operation)) {
					if (referencedOperations == null) {
						referencedOperations = new HashSet<>();
					}
					referencedOperations.add(operation);
				}
			}
		}
		return referencedOperations;
	}

	/*protected*/ public @Nullable Iterable<@NonNull Operation> getReferencedNonFinalOperations(@NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression specification) {
		ExpressionInOCL prototype = null;
		try {
			prototype = environmentFactory.parseSpecification(specification);
		}
		catch (ParserException e) {
			// FIXME log error
			e.printStackTrace();
		}
		if (prototype == null) {
			return null;
		}
		Set<@NonNull Operation> referencedOperations = null;
		for (EObject crossReference : EcoreUtil.ExternalCrossReferencer.find(prototype).keySet()) {
			if (crossReference instanceof Operation) {
				Operation operation = (Operation) crossReference;
				if (!finalAnalysis.isFinal(operation)) {
					if (referencedOperations == null) {
						referencedOperations = new HashSet<>();
					}
					referencedOperations.add(operation);
				}
			}
		}
		return referencedOperations;
	}

	/**
	 * Return all final operations transitively referenced by opaqueExpression, or null if none.
	 * @since 1.3
	 */
	/*protected*/ public void getTransitivelyReferencedFinalOperations(@NonNull Set<@NonNull Operation> alreadyReferencedFinalOperations, @NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression expressionInOCL) {
		Iterable<@NonNull Operation> newlyReferencedFinalOperations = getReferencedFinalOperations(finalAnalysis, expressionInOCL);
		if (newlyReferencedFinalOperations != null) {
			for (@NonNull Operation newlyReferencedFinalOperation : newlyReferencedFinalOperations) {
				if (alreadyReferencedFinalOperations.add(newlyReferencedFinalOperation)) {
					LanguageExpression anotherExpressionInOCL = newlyReferencedFinalOperation.getBodyExpression();
					if (anotherExpressionInOCL != null) {
						getTransitivelyReferencedFinalOperations(alreadyReferencedFinalOperations, finalAnalysis, anotherExpressionInOCL);
					}
				}
			}
		}
	}

	@Override
	public @Nullable Operation isFinal(@NonNull Operation anOperation, org.eclipse.ocl.pivot.@NonNull Class staticType) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		return finalAnalysis.isFinal(anOperation, metamodelManager.getCompleteClass(staticType));
	}

	/**
	 * Return true if anOperation has an overload for invalid values.
	 *
	public boolean mayEvaluateForInvalid(@NonNull Operation anOperation) {
		Type targetType = metamodelManager.getOclInvalidType();
		String name = anOperation.getName();
		if (name == null) {
			return false;
		}
		DomainInheritance inheritance = targetType.getInheritance(metamodelManager);
		DomainInheritance[] arguments;
		List<Parameter> parameters = anOperation.getOwnedParameter();
		int iSize = parameters.size();
		if (iSize > 0) {
			arguments = new DomainInheritance[iSize];
			for (int i = 0; i < iSize; i++) {
				Parameter parameter = parameters.get(i);
				Type type = parameter.getType();
				if (type == null) {
					return false;
				}
				if (type.getOwningTemplateParameter() != null) {
					return false;					// FIX ME invalid not supported for templated operations
				}
				arguments[i] = type.getInheritance(metamodelManager);
			}
		}
		else {
			arguments = DomainInheritance.EMPTY_ARRAY;
		}
		DomainOperation localOperation = inheritance.lookupLocalOperation(metamodelManager, name, arguments);
		return localOperation != null;
	} */

	//	protected abstract void resetLocals();
}
