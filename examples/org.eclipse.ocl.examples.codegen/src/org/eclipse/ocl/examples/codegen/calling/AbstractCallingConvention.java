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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  AbstractCallingConvention defines the common calling convention functionality.
 */
public abstract class AbstractCallingConvention implements CallingConvention, Nameable
{
	private static @NonNull Set<@NonNull AbstractCallingConvention> callingConventions = new HashSet<>();

	public static void printAllUsages() {
		List<@NonNull AbstractCallingConvention> callingConventions = new ArrayList<>(AbstractCallingConvention.callingConventions);
		Collections.sort(callingConventions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractCallingConvention callingConvention : callingConventions) {
			System.out.println(callingConvention);
			callingConvention.printUsages();
		}
	}

	public static void resetAllUsages() {
		for (@NonNull AbstractCallingConvention callingConvention : callingConventions) {
			callingConvention.resetUsages();
		}
		callingConventions.clear();
	}

	private @NonNull Map<@NonNull String, @NonNull List<@NonNull String>> testName2usages = new HashMap<>();

	private void addInstance(@Nullable String testName, @NonNull String usage) {
		callingConventions.add(this);
		if (testName == null) {
			testName = "";
		}
		List<@NonNull String> usages = testName2usages.get(testName);
		if (usages == null) {
			usages = new ArrayList<>();
			testName2usages.put(testName, usages);
		}
		usages.add(usage);
		NameUtil.errPrintln(this + " for " + usage);
	}
	protected enum ResultStyle {
		BOOLEAN,			// The result type is precisely Boolean
		RESULT,				// The result type is that of the original AS operation.
		VOID				// There is no result type
	}
	protected enum ParameterStyle {
		BOXED_VALUES_ALL,		// The parameter is the array of original parameters passed as a boxed array of required parameters if all required
		BOXED_VALUES_OPTIONAL,	// The parameter is the array of original parameters passed as a boxed array of optional parameters
		EXECUTOR,				// The parameter is the executor
		PARAMETERS,				// The original paramters are replicated in the new operation
		OBJECT,					// The parameter is the context object for a dispatch
		SELF,					// The parameter is the original self and is called contextObject
		SKIP					// There is no parameter - a convenience for conditional parameters
	}

	/**
	 * Create the declaration for an AS Operation within asClass in support of asOriginalOperation.
	 * The declaration las a name, result and parameters configured by resultStyleOrClass and parameterStyles.
	 * The resultStyleOrClass may be an explicit class or a ResultStyle algorithm.
	 */
	protected @NonNull Operation createASOperationDeclaration(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull TypedElement asOrigin,
			@NonNull String name, @NonNull Object resultStyleOrClass, @NonNull ParameterStyle... parameterStyles) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Type asReturnType;
		boolean isRequired;
		if (resultStyleOrClass instanceof org.eclipse.ocl.pivot.Class) {
			asReturnType = (org.eclipse.ocl.pivot.Class)resultStyleOrClass;
			isRequired = true;
		}
		else if (resultStyleOrClass instanceof TypedElement) {
			TypedElement asTypedElement = (TypedElement)resultStyleOrClass;
			asReturnType = PivotUtil.getType(asTypedElement);
			isRequired = asTypedElement.isIsRequired();
		}
		else {
			ResultStyle resultStyle = (ResultStyle)resultStyleOrClass;
			switch (resultStyle) {
				case BOOLEAN: {
					EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
					asReturnType = environmentFactory.getStandardLibrary().getBooleanType();
					isRequired = true;
					break;
				}
				case RESULT: {
					asReturnType = PivotUtil.getType(asOrigin);
					isRequired = asOrigin.isIsRequired();
					break;
				}
				case VOID: {
					EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
					asReturnType = environmentFactory.getStandardLibrary().getOclVoidType();
					isRequired = true;
					break;
				}
				default: {
					throw new UnsupportedOperationException();
				}
			}
		}
		Operation asOperation = PivotUtil.createOperation(name, asReturnType, null, null);
		asOperation.setIsRequired(isRequired);
		asClass.getOwnedOperations().add(asOperation);
		if (parameterStyles.length > 0) {
			List<Parameter> asParameters = asOperation.getOwnedParameters();
			for (@NonNull ParameterStyle parameterStyle : parameterStyles) {
				switch (parameterStyle) {
					case BOXED_VALUES_ALL: {
						Parameter asParameter = createBoxedValuesParameter(codeGenerator, false);
						asParameters.add(asParameter);
						break;
					}
					case BOXED_VALUES_OPTIONAL: {
						@NonNull Operation asOriginalOperation = (Operation)asOrigin;
						Parameter asParameter = createBoxedValuesParameter(codeGenerator, PivotUtil.allParametersRequired(asOriginalOperation));
						asParameters.add(asParameter);
						break;
					}
					case EXECUTOR: {
						NameResolution executorResolution = codeGenerator.getGlobalNameManager().getExecutorNameResolution();
						String executorName = executorResolution.getResolvedName();
						LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
						org.eclipse.ocl.pivot.Class executorType = jLanguageSupport.getNativeClass(Executor.class);
						Parameter asParameter = PivotUtil.createParameter(executorName, executorType, true);
						asParameters.add(asParameter);
						break;
					}
					case OBJECT: {			// XXX redundant wrt self
						@NonNull Feature asOriginalFeature = (Feature)asOrigin;
						GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
						String objectName = globalNameManager.getObjectNameResolution().getResolvedName();
						Parameter asParameter = PivotUtil.createParameter(objectName, PivotUtil.getOwningClass(asOriginalFeature), true);
						asParameters.add(asParameter);
						break;
					}
					case PARAMETERS: {
						@NonNull Operation asOriginalOperation = (Operation)asOrigin;
						for (@NonNull Parameter asOriginalParameter : PivotUtil.getOwnedParameters(asOriginalOperation)) {
							String parameterName = PivotUtil.getName(asOriginalParameter);
							Type parameterType = PivotUtil.getType(asOriginalParameter);
							boolean parameterIsRequired = asOriginalParameter.isIsRequired();
							Parameter asParameter = PivotUtil.createParameter(parameterName, parameterType, parameterIsRequired);
							asParameters.add(asParameter);
						}
						break;
					}
					case SELF: {
						@NonNull Feature asOriginalFeature = (Feature)asOrigin;
						GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
						String contextObjectName = globalNameManager.getContextObjectNameResolution().getResolvedName();
						Parameter asParameter = PivotUtil.createParameter(contextObjectName, PivotUtil.getOwningClass(asOriginalFeature), true);
						asParameters.add(asParameter);
						break;
					}
					case SKIP: {
						break;
					}
					default:
						throw new UnsupportedOperationException();
				}
			}
		}
		return asOperation;
	}

	private @NonNull Parameter createBoxedValuesParameter(@NonNull JavaCodeGenerator codeGenerator, boolean isRequired) {
		NameResolution boxedValuesResolution = codeGenerator.getGlobalNameManager().getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		org.eclipse.ocl.pivot.Class boxedValueType = jLanguageSupport.getNativeClass(Object[].class);
		return PivotUtil.createParameter(boxedValuesName, boxedValueType, isRequired);
	}

	protected @NonNull Parameter getBoxedValuesParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		NameResolution boxedValuesResolution = analyzer.getGlobalNameManager().getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		return ClassUtil.nonNullState(NameUtil.getNameable(asOperation.getOwnedParameters(), boxedValuesName));
	}

	protected @NonNull Parameter getExecutorParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		NameResolution executorResolution = analyzer.getGlobalNameManager().getExecutorNameResolution();
		String executorName = executorResolution.getResolvedName();
		return ClassUtil.nonNullState(NameUtil.getNameable(asOperation.getOwnedParameters(), executorName));
	}

	@Override
	public @NonNull String getName() {
		return getClass().getSimpleName();
	}

	protected void logInstance(@Nullable Object usage) {
		addInstance(NameUtil.contextText, String.valueOf(usage));
	}

	protected void logInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		addInstance(NameUtil.contextText, String.valueOf(asOperation));
	}

	private void printUsages() {
		List<@NonNull String> testNames = new ArrayList<>(testName2usages.keySet());
		Collections.sort(testNames);
		for (@NonNull String testName : testNames) {
			System.out.println("\t" + testName);
			List<@NonNull String> usages = new ArrayList<>(testName2usages.get(testName));
			Map<@NonNull String, @NonNull Integer> distinctUsages = new HashMap<>();
			for (@NonNull String usage : usages) {
				Integer count = distinctUsages.get(usage);
				distinctUsages.put(usage, count != null ? count+1 : 1);
			}
			List<@NonNull String> sortedUsages = new ArrayList<>(distinctUsages.keySet());
			Collections.sort(sortedUsages);
			for (@NonNull Object usage : sortedUsages) {
				System.out.println("\t\t" + distinctUsages.get(usage) + " * " + usage);
			}
		}
	}

	private void resetUsages() {
		testName2usages.clear();
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
