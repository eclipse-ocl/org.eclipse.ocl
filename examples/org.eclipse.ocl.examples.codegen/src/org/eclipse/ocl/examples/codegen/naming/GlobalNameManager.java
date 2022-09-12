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
package org.eclipse.ocl.examples.codegen.naming;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public class GlobalNameManager extends AbstractNameManager
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
	protected final @NonNull ImportNameManager importNameManager;

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
	 * The name manager introduced by each scope defining elements, such as CGClass, CGOperation or CGIteratorExp for use
	 * by its hierarchically nested children. The name of the scope itself is defined in the parent name manager.
	 */
	public final @NonNull Map<@NonNull CGNamedElement, @NonNull NestedNameManager> cgElement2childNameManager = new HashMap<>();	// XXX not public

	/**
	 * The name manager in which the name(s) of this element is managed.
	 */
	public final @NonNull Map<@NonNull CGNamedElement, @NonNull NestedNameManager> cgElement2selfNameManager  = new HashMap<>();	// XXX not public

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
//	protected final @NonNull NameResolution getCachedEvaluationResultName;
	protected final @NonNull NameResolution getResultName;
	protected final @NonNull NameResolution idResolverName;
	protected final @NonNull NameResolution initValueName;
	protected final @NonNull NameResolution instanceName;
	protected final @NonNull NameResolution isEqualName;
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
		this.importNameManager = codeGenerator.createImportNameManager();
		//
		this.thisName = declareReservedName(JavaConstants.THIS_NAME);
		//
		this.anyName = declareGlobalName(JavaConstants.ANY_NAME);
//		this.eName = declareGlobalName(JavaConstants.E_NAME);
		this.evaluateName = declareGlobalName(JavaConstants.EVALUATE_NAME);
		this.evaluationCacheName = declareGlobalName(JavaConstants.EVALUATION_CACHE_NAME);
		this.executorName = declareGlobalName(JavaConstants.EXECUTOR_NAME);
//		this.getCachedEvaluationResultName = declareGlobalName(JavaConstants.GET_CACHED_EVALUATION_RESULT_NAME);
		this.getResultName = declareGlobalName(JavaConstants.GET_RESULT_NAME);
		this.idResolverName = declareGlobalName(JavaConstants.ID_RESOLVER_NAME);
		this.initValueName = declareGlobalName("initValue");
		this.instanceName = declareGlobalName(JavaConstants.INSTANCE_NAME);
		this.isEqualName = declareGlobalName("isEqual");
		this.modelManagerName = declareGlobalName(JavaConstants.MODEL_MANAGER_NAME);
		this.selfName = declareGlobalName(PivotConstants.SELF_NAME);
		this.sourceAndArgumentValuesName = declareGlobalName(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		this.standardLibraryVariableName = declareGlobalName(JavaConstants.STANDARD_LIBRARY_NAME);
//		this.thisName = getReservedName(JavaConstants.THIS_NAME);
		this.typeIdName = declareGlobalName(JavaConstants.TYPE_ID_NAME);
		this.valueName = declareGlobalName("value");
	}

	/**
	 * Specify childNameManager as the NameManager in which the names scoped by cgScopingElement are maintained.
	 * (The name of cgScopingElement itself is maintained in the parent of childNameManager.)
	 */
	public void addChildNameManager(@NonNull CGNamedElement cgScopingElement, @NonNull NestedNameManager childNameManager) {
		System.out.println("addChildNameManager " + NameUtil.debugSimpleName(cgScopingElement) + " => " + NameUtil.debugSimpleName(childNameManager)+ " : " + cgScopingElement);
		NestedNameManager old = cgElement2childNameManager.put(cgScopingElement, childNameManager);
		assert old == null;
	//	assert nestedNameManager.getCGScope() == cgScopingElement;		-- but still under construction
	//	old = asScopingElement2nestedNameManager.put(CGUtil.getAST(cgScopingElement), nestedNameManager);
	//	assert old == null;
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

	/**
	 * Specify nestedNameManager as the NameManager in which cgElement is maintained.
	 * This must be used to impose the appropriate parent/child NameManager for an inline/out-of-line Loop.
	 * It may be used to accelerate NameManager location.
	 */
	public void addSelfNameManager(@NonNull CGNamedElement cgElement, @NonNull NestedNameManager nameManager) {
		if (cgElement instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		NameManager old = cgElement2selfNameManager.put(cgElement, nameManager);
		assert (old == null) || (old == nameManager);
	}

	public void assignNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		assignLocalNames(context, nameManager2namedElements);
		assignNestedNames(nameManager2namedElements);
	}

	public @Nullable NestedNameManager basicGetChildNameManager(@NonNull CGNamedElement cgScopingElement) {
		return cgElement2childNameManager.get(cgScopingElement);
	}

	public @Nullable NestedNameManager basicGetSelfNameManager(@NonNull CGNamedElement cgElement) {
		return cgElement2selfNameManager.get(cgElement);
	}

	public @NonNull ClassNameManager createClassNameManager(@NonNull ClassableNameManager outerNameManager, @NonNull CGClass cgClass) {
		ClassNameManager nestedNameManager = codeGenerator.createClassNameManager(outerNameManager, cgClass);
		assert cgElement2childNameManager.get(cgClass) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		if (cgConstraint instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		FeatureNameManager nestedNameManager = codeGenerator.createFeatureNameManager(classNameManager, cgConstraint);
		assert cgElement2childNameManager.get(cgConstraint) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull FeatureNameManager parentNameManager, @NonNull CGIterationCallExp cgIterationCallExp) {
		if (cgIterationCallExp instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		FeatureNameManager nestedNameManager = codeGenerator.createFeatureNameManager(classNameManager, parentNameManager, cgIterationCallExp);
		assert cgElement2childNameManager.get(cgIterationCallExp) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation) {
		if (cgOperation instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		FeatureNameManager nestedNameManager = codeGenerator.createFeatureNameManager(classNameManager, cgOperation);
		assert cgElement2childNameManager.get(cgOperation) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		if (cgProperty instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX
		}
		FeatureNameManager nestedNameManager = codeGenerator.createFeatureNameManager(classNameManager, cgProperty);
		assert cgElement2childNameManager.get(cgProperty) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull PackageNameManager createPackageNameManager(@Nullable PackageNameManager outerNameManager, @NonNull CGPackage cgPackage) {
		PackageNameManager packageNameManager = codeGenerator.createPackageNameManager(outerNameManager, cgPackage);
		assert cgElement2childNameManager.get(cgPackage) == packageNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return packageNameManager;
	}

	/**
	 * Declare that cgElement has a name which should immediately default to reservedName.
	 * This is typically used to provide an eager name reservation for global particularly Ecore names.
	 */
	public @NonNull NameResolution declareGlobalName(@NonNull String reservedName) {
		return declareGlobalName(null, reservedName);
	}
	@Deprecated
	public @NonNull NameResolution declareGlobalName(@Nullable CGNamedElement cgElement, @NonNull String reservedName) {
		NameResolution baseNameResolution = new NameResolution.Global(this, cgElement, reservedName);
		context.allocateReservedName(reservedName, cgElement != null ? cgElement : AbstractNameManager.NOT_AN_OBJECT);
		return baseNameResolution;
	}

	/**
	 * Declare that cgElement has a name which should immediately equate to reservedName.
	 * This is typically used to ensure that reserved Java names are used only for their Java purpose.
	 */
	public @NonNull NameResolution declareReservedName(@NonNull String reservedName) {
		return declareReservedName(null, reservedName);
	}
	@Deprecated
	public @NonNull NameResolution declareReservedName(@Nullable CGNamedElement cgElement, @NonNull String reservedName) {
		NameResolution baseNameResolution = new NameResolution.Global(this, cgElement, reservedName);
		context.allocateReservedName(reservedName, cgElement != null ? cgElement : AbstractNameManager.NOT_AN_OBJECT);
		return baseNameResolution;
	}

	public @NonNull NameResolution getAnyNameResolution() {
		return anyName;
	}

	public @NonNull NestedNameManager getChildNameManager(@NonNull CGNamedElement cgScopingElement) {
		return ClassUtil.nonNullState(basicGetChildNameManager(cgScopingElement));
	}

	public @NonNull ClassNameManager getClassNameManager(@NonNull CGClass cgClass) {
		return (ClassNameManager)getChildNameManager(cgClass);
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return codeGenerator;
	}

	@Override
	protected @NonNull Context getContext() {
		return context;
	}

	public @Nullable EClass getEClass(@NonNull ElementId elementId) {
		IdVisitor<@Nullable EClass> id2EClassVisitor = codeGenerator.getId2EClassVisitor();
		return elementId.accept(id2EClassVisitor);
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

//	public @NonNull NameResolution getGetCachedEvaluationResultNameResolution() {
//		return getCachedEvaluationResultName;
//	}

//	public @NonNull String getGetCachedEvaluationResultName() {
//		return getCachedEvaluationResultName.getResolvedName();
//	}

	public @NonNull NameResolution getGetResultNameResolution() {
		return getResultName;
	}

	public @NonNull String getGetResultName() {
		return getResultName.getResolvedName();
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

	public @NonNull NameResolution getIsEqualNameResolution() {
		return isEqualName;
	}

	public @NonNull String getModelManagerName() {
		return modelManagerName.getResolvedName();
	}

	public @NonNull NameResolution getModelManagerNameResolution() {
		return modelManagerName;
	}

	/**
	 * Declare that cgElement must eventually have a distinct name that can default to its natural value once all other
	 * name preferences have been satisfied. This is the normal form of name resolution.
	 */
	@Override
	public @NonNull NameResolution getNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution == null) {
			CGValuedElement cgNamedValue = cgElement.getNamedValue();
			nameResolution = cgNamedValue.basicGetNameResolution();
			if (nameResolution == null) {
				String nameHint = helper.getNameHint(cgNamedValue);
				nameResolution = new NameResolution.Lazy(this, cgNamedValue, nameHint);
			}
			if (cgElement != cgNamedValue) {
				nameResolution.addCGElement(cgElement);
			}
		}
		return nameResolution;
	}

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

	@Override
	public boolean isGlobal() {
		return true;
	}

	@Override
	public @NonNull String toString() {
		return "globals";
	}
}
