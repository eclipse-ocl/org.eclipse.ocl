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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class GlobalNameManager extends NameManager
{
	/**
	 * A NameVariant specifies an algorithm for creating a variant of some name.
	 */
	public static interface NameVariant
	{
		@NonNull String getName(@NonNull String name);

		@Override
		@NonNull String toString();
	}

	/**
	 * A NameVariantPreferred specifies apreferred (local) name spelling that is likely to be acceptable but may
	 * get renamed if necessary.
	 */
	private static class NameVariantPreferred implements NameVariant
	{
		protected final @NonNull String name;

		private NameVariantPreferred(@NonNull String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object that) {
			return (that instanceof NameVariantPreferred) && this.name.equals(((NameVariantPreferred)that).name);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public @NonNull String getName(@NonNull String name) {
			return this.name;
		}

		@Override
		public @NonNull String toString() {
			return name;
		}
	}

	/**
	 * A NameVariantPrefix specifies an algorithm for creating a variant that applirs a prefix to some name.
	 */
	private static class NameVariantPrefix implements NameVariant
	{
		protected final @NonNull String prefix;

		private NameVariantPrefix(@NonNull String prefix) {
			this.prefix = prefix;
		}

		@Override
		public boolean equals(Object that) {
			return (that instanceof NameVariantPrefix) && this.prefix.equals(((NameVariantPrefix)that).prefix);
		}

		@Override
		public int hashCode() {
			return prefix.hashCode();
		}

		@Override
		public @NonNull String getName(@NonNull String name) {
			return prefix + name;
		}

		@Override
		public @NonNull String toString() {
			return prefix + "...";
		}
	}

	protected final @NonNull JavaCodeGenerator codeGenerator;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull ImportNameManager importNameManager;

	private @NonNull Map<@NonNull CGElement, @NonNull JavaLocalContext> localContexts = new HashMap<>();
	private @NonNull Set<@NonNull CGValuedElement> globals = new HashSet<>();

	/**
	 * The value name assignments.
	 */
	private final @NonNull Context context;

	/**
	 * The distinct value name spaces.
	 */
	private final @NonNull List<@NonNull NameVariant> nameVariants = new UniqueList<>();

	/**
	 * The actual unique global name for each reserved name.
	 */
	private final @NonNull Map<@NonNull String, @NonNull NameResolution> name2reservedNameResolutions = new HashMap<>();

	/**
	 * The NameManager in which an element is scoped. This may be eaferly declared to ensure that children of
	 * e.g. an iteration have the correct inner/outer scope.
	 */
	private final @NonNull Map<@NonNull CGNamedElement, @NonNull NameManager> element2nameManager = new HashMap<>();

	//
	//	Built-in special purpose names are dynamically reserved using a static value as the hint.
	//	The dynamic value will therefore be the same as the statc hint unless the hint is usurped by a
	//	Java reserved word. Access should therefore use globalContext.getYYY rather than JavaConstants.YYY
	//	to minimize eiting if a new Java reserved word interferes.
	//
	protected final @NonNull NameResolution anyName;
//	protected final @NonNull NameResolution eName;
	protected final @NonNull NameResolution evaluateName;
	protected final @NonNull NameResolution evaluationCacheName;
	protected final @NonNull NameResolution executorName;
	protected final @NonNull NameResolution getCachedEvaluationResult;
	protected final @NonNull NameResolution idResolverName;
	protected final @NonNull NameResolution initValueName;
	protected final @NonNull NameResolution instanceName;
	protected final @NonNull NameResolution modelManagerName;
	protected final @NonNull NameResolution selfName;
	protected final @NonNull NameResolution sourceAndArgumentValuesName;
	protected final @NonNull NameResolution standardLibraryVariableName;
	protected final @NonNull NameResolution thisName;
	protected final @NonNull NameResolution typeIdName;
	protected final @NonNull NameResolution valueName;

	public GlobalNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull NameManagerHelper helper) {
		super(null, helper);
		this.context = new Context(this);			// Global can allocate names straightawy
		this.codeGenerator = codeGenerator;
		this.analyzer = codeGenerator.getAnalyzer();
		this.importNameManager = codeGenerator.createImportNameManager();
		//
		this.thisName = declareReservedName(null, JavaConstants.THIS_NAME);
		//
		this.anyName = declareGlobalName(null, JavaConstants.ANY_NAME);
//		this.eName = declareGlobalName(null, JavaConstants.E_NAME);
		this.evaluateName = declareGlobalName(null, JavaConstants.EVALUATE_NAME);
		this.evaluationCacheName = declareGlobalName(null, JavaConstants.EVALUATION_CACHE_NAME);
		this.executorName = declareGlobalName(null, JavaConstants.EXECUTOR_NAME);
		this.getCachedEvaluationResult = declareGlobalName(null, JavaConstants.GET_CACHED_EVLUATION_RESULT_NAME);
		this.idResolverName = declareGlobalName(null, JavaConstants.ID_RESOLVER_NAME);
		this.initValueName = declareGlobalName(null, "initValue");
		this.instanceName = declareGlobalName(null, JavaConstants.INSTANCE_NAME);
		this.modelManagerName = declareGlobalName(null, JavaConstants.MODEL_MANAGER_NAME);
		this.selfName = declareGlobalName(null, PivotConstants.SELF_NAME);
		this.sourceAndArgumentValuesName = declareGlobalName(null, JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		this.standardLibraryVariableName = declareGlobalName(null, JavaConstants.STANDARD_LIBRARY_NAME);
//.		this.thisName = getReservedName(null, JavaConstants.THIS_NAME);
		this.typeIdName = declareGlobalName(null, JavaConstants.TYPE_ID_NAME);
		this.valueName = declareGlobalName(null, "value");
	}

	public void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globals.add(cgGlobal);
	}

	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String className) {
		return importNameManager.addImport(isRequired, className);
	}

	public @NonNull NameVariant addNameVariantPreferred(@NonNull String name) {
		NameVariant nameVariant = new NameVariantPreferred(name);
		assert nameVariants.add(nameVariant);
		return nameVariant;
	}

	public @NonNull NameVariant addNameVariantPrefix(@NonNull String prefix) {
		NameVariant nameVariant = new NameVariantPrefix(prefix);
		assert nameVariants.add(nameVariant);
		return nameVariant;
	}

	public void assignNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		assignLocalNames(context, nameManager2namedElements);
		assignNestedNames(nameManager2namedElements);
	}

	public @Nullable JavaLocalContext basicFindLocalContext(@NonNull CGNamedElement cgElement) {
		for (CGElement cgScope = cgElement; cgScope != null; cgScope = cgScope.getParent()) {
			JavaLocalContext localContext = localContexts.get(cgScope);
			if (localContext != null) {
				return localContext;
			}
		}
		return null;
	}

	public @Nullable JavaLocalContext basicGetLocalContext(@NonNull CGNamedElement cgElement) {
		return localContexts.get(cgElement);
	}

	public @Nullable NameManager bascGetScope(@NonNull CGNamedElement cgElement) {
		return element2nameManager.get(cgElement);
	}

	/**
	 * Declare that cgElement has a name which should immediately default to nameHint.
	 * This is typically used to provide an eager name reservation for globl particularly Ecore names.
	 */
	public @NonNull NameResolution declareGlobalName(@Nullable CGValuedElement cgElement, @NonNull String nameHint) {
		NameResolution baseNameResolution = new NameResolution(this, cgElement, nameHint);
		baseNameResolution.resolveIn(context);
		return baseNameResolution;
	}

	/**
	 * Declare that cgElement has a name which should immediately equate to nameHint.
	 * This is typically used to ensure that reserved Java names are used only for their Java purpose.
	 */
	@Override
	public @NonNull NameResolution declareReservedName(@Nullable CGValuedElement cgElement, @NonNull String nameHint) {
		NameResolution baseNameResolution = new NameResolution(this, cgElement, nameHint);
		assert reservedJavaNames.contains(nameHint);
		baseNameResolution.setResolvedName(nameHint);
		return baseNameResolution;
	}

	public void declareScope(@NonNull CGNamedElement cgElement, @NonNull NameManager nameManager) {
		NameManager old = element2nameManager.put(cgElement, nameManager);
		assert (old == null) || (old == nameManager);
	}

	public @NonNull JavaLocalContext findLocalContext(@NonNull CGNamedElement cgElement) {
		return ClassUtil.nonNullState(basicFindLocalContext(cgElement));
	}







//	@Deprecated /* deprecated use getImportManager */
//	public @NonNull Set<@NonNull String> getImports() {
//		return importNameManager.getLong2ShortImportNames().keySet();
//	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return analyzer;
	}

	public @NonNull NameResolution getAnyNameResolution() {
		return anyName;
	}

	public @Nullable EClass getEClass(@NonNull ElementId elementId) {
		IdVisitor<@Nullable EClass> id2EClassVisitor = codeGenerator.getId2EClassVisitor();
		return elementId.accept(id2EClassVisitor);
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return context;
	}

	public @NonNull String getEvaluateName() {
		return evaluateName.getResolvedName();
	}

	public @NonNull NameResolution getEvaluateNameResolution() {
		return evaluateName;
	}

	public @NonNull String getEvaluationCacheName() {
		return evaluationCacheName.getResolvedName();
	}

	public @NonNull String getExecutorName() {
		return executorName.getResolvedName();
	}

	public @NonNull NameResolution getExecutorNameResolution() {
		return executorName;
	}

	public @NonNull String getGetCachedEvaluationResultName() {
		return getCachedEvaluationResult.getResolvedName();
	}

	public @NonNull Collection<@NonNull CGValuedElement> getGlobals() {
		return globals;
	}

	public @NonNull String getIdResolverName() {
		return idResolverName.getResolvedName();
	}

	public @NonNull NameResolution getIdResolverNameResolution() {
		return idResolverName;
	}

	public @NonNull ImportNameManager getImportNameManager() {
		return importNameManager;
	}

	public @NonNull String getInitValueName() {
		return initValueName.getResolvedName();
	}

	public @NonNull String getInstanceName() {
		return instanceName.getResolvedName();
	}

	public @NonNull NameResolution getInstanceNameResolution() {
		return instanceName;
	}

	//	@Deprecated /* deprecated use getImportManager */
	//	public @NonNull Set<@NonNull String> getImports() {
	//		return importNameManager.getLong2ShortImportNames().keySet();
	//	}

		@Override
		protected @NonNull String getLazyNameHint(@NonNull CGValuedElement cgNamedValue) {
			return helper.getNameHint(cgNamedValue);
		}

	public @NonNull JavaLocalContext getLocalContext(@NonNull CGNamedElement cgElement) {
		return ClassUtil.nonNullState(basicGetLocalContext(cgElement));
	}

	public @NonNull String getModelManagerName() {
		return modelManagerName.getResolvedName();
	}

	public @NonNull NameResolution getModelManagerNameResolution() {
		return modelManagerName;
	}

//	public @NonNull GlobalNameManager getNameManager() {
//		return globalNameManager;
//	}

	public @NonNull String getReservedName(@NonNull String name) {
		return ClassUtil.nonNullState(name2reservedNameResolutions.get(name)).getResolvedName();
	}

	public @NonNull String getSelfName() {
		return selfName.getResolvedName();
	}

	public @NonNull NameResolution getSelfNameResolution() {
		return selfName;
	}

	public @NonNull String getSourceAndArgumentValuesName() {
		return sourceAndArgumentValuesName.getResolvedName();
	}

	public @NonNull NameResolution getStandardLibraryVariableNameResolution() {
		return standardLibraryVariableName;
	}

	public @NonNull NameResolution getThisNameResolution() {
		return thisName;
	}

	public @NonNull NameResolution getTypeIdNameResolution() {
		return typeIdName;
	}

	public @NonNull String getValueName() {
		return valueName.getResolvedName();
	}

	public @NonNull LocalContext initLocalContext(@Nullable LocalContext outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asScope) {
		assert cgNamedElement.getAst() != null;
		assert cgNamedElement.getAst() == asScope;
		@NonNull Type asType;
		NameManager outerNameManager;
		if (outerContext != null) {
			outerNameManager = outerContext.getNameManager();
			asType = ((NestedNameManager)outerNameManager).asType();
		}
		else {
			outerNameManager = this;
			asType = ClassUtil.nonNullState(PivotUtil.getContainingType(asScope));
		}
		JavaLocalContext localContext = codeGenerator.createLocalContext((JavaLocalContext)outerContext, outerNameManager, cgNamedElement, asScope, asType);
		setLocalContext(cgNamedElement, localContext);
		return localContext;
	}

	@Override
	public boolean isGlobal() {
		return true;
	}

	public void setLocalContext(@NonNull CGNamedElement cgNamedElement, @NonNull JavaLocalContext localContext) {
		JavaLocalContext old = localContexts.put(cgNamedElement, localContext);
		assert old == null;
	}

	@Override
	public @NonNull String toString() {
		return "globals";
	}
}
