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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSourcedCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
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
	private final @NonNull Map<@NonNull CGNamedElement, @NonNull NestedNameManager> cgElement2childNameManager = new HashMap<>();	// XXX not public

	/**
	 * The name manager in which the name(s) of this element is managed.
	 */
	private final @NonNull Map<@NonNull CGNamedElement, @NonNull NameManager> cgElement2selfNameManager  = new HashMap<>();	// XXX not public

	//
	//	Built-in special purpose names are dynamically reserved using a static value as the hint.
	//	The dynamic value will therefore be the same as the statc hint unless the hint is usurped by a
	//	Java reserved word. Access should therefore use globalContext.getYYY rather than JavaConstants.YYY
	//	to minimize eiting if a new Java reserved word interferes.
	//
	protected final @NonNull NameResolution anyName;
	protected final @NonNull NameResolution basicEvaluateName;
	protected final @NonNull NameResolution boxedValuesName;
	protected final @NonNull NameResolution cachedResultName;
	protected final @NonNull NameResolution contextObjectName;
//	protected final @NonNull NameResolution eName;
	protected final @NonNull NameResolution evaluateName;
	protected final @NonNull NameResolution evaluationCacheName;
	protected final @NonNull NameResolution executorName;
	protected final @NonNull NameResolution getCachedEvaluationResultName;
	protected final @NonNull NameResolution getResultName;
	protected final @NonNull NameResolution idResolverName;
	protected final @NonNull NameResolution initValueName;
	protected final @NonNull NameResolution instanceName;
	protected final @NonNull NameResolution isEqualName;
	protected final @NonNull NameResolution modelManagerName;
	protected final @NonNull NameResolution newInstanceName;
	protected final @NonNull NameResolution objectName;
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
		//	Java reserved words first
		//
		this.thisName = declareEagerName(null, JavaConstants.THIS_NAME);
		//
		this.anyName = declareEagerName(null, JavaConstants.ANY_NAME);
		this.basicEvaluateName = declareEagerName(null, JavaConstants.BASIC_EVALUATE_NAME);
		this.boxedValuesName = declareEagerName(null, JavaConstants.BOXED_VALUES_NAME);
		this.cachedResultName = globalNameManager.declareEagerName(null, JavaConstants.CACHED_RESULT_NAME);
		this.contextObjectName = declareEagerName(null, JavaConstants.CONTEXT_OBJECT_NAME);
//		this.eName = declareEagerName(null, JavaConstants.E_NAME);
		this.evaluateName = declareEagerName(null, JavaConstants.EVALUATE_NAME);
		this.evaluationCacheName = declareEagerName(null, JavaConstants.EVALUATION_CACHE_NAME);
		this.executorName = declareEagerName(null, JavaConstants.EXECUTOR_NAME);
		this.getCachedEvaluationResultName = declareEagerName(null, JavaConstants.GET_CACHED_EVALUATION_RESULT_NAME);
		this.getResultName = declareEagerName(null, JavaConstants.GET_RESULT_NAME);
		this.idResolverName = declareEagerName(null, JavaConstants.ID_RESOLVER_NAME);
		this.initValueName = declareEagerName(null, "initValue");
		this.instanceName = declareEagerName(null, JavaConstants.INSTANCE_NAME);
		this.isEqualName = declareEagerName(null, "isEqual");
		this.modelManagerName = declareEagerName(null, JavaConstants.MODEL_MANAGER_NAME);
		this.newInstanceName = globalNameManager.declareEagerName(null, JavaConstants.NEW_INSTANCE_NAME);
		this.objectName = declareEagerName(null, PivotConstants.OBJECT_NAME);
		this.selfName = declareEagerName(null, PivotConstants.SELF_NAME);
		this.sourceAndArgumentValuesName = declareEagerName(null, JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		this.standardLibraryVariableName = declareEagerName(null, JavaConstants.STANDARD_LIBRARY_NAME);
		this.typeIdName = declareEagerName(null, JavaConstants.TYPE_ID_NAME);
		this.valueName = declareEagerName(null, "value");
	}

	/**
	 * Specify childNameManager as the NameManager in which the names scoped by cgScopingElement are maintained.
	 * (The name of cgScopingElement itself is maintained in the parent of childNameManager.)
	 */
	public void addChildNameManager(@NonNull CGNamedElement cgScopingElement, @NonNull NestedNameManager childNameManager) {
	//	System.out.println("addChildNameManager " + NameUtil.debugSimpleName(cgScopingElement) + " => " + NameUtil.debugSimpleName(childNameManager)+ " : " + cgScopingElement);
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
	public void addSelfNameManager(@NonNull CGNamedElement cgElement, @NonNull NameManager nameManager) {
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

	public @Nullable NameManager basicGetSelfNameManager(@NonNull CGNamedElement cgElement) {
		return cgElement2selfNameManager.get(cgElement);
	}

	/**
	 * Return the NestedNameManager in which cgNamedElement should be defined or null if global.
	 */
	public @Nullable NameManager basicUseSelfNameManager(@NonNull CGNamedElement cgNamedElement) {
		NameManager nameManager = basicGetSelfNameManager(cgNamedElement);
		if (nameManager != null) {
			return nameManager;
		}
		for (CGNamedElement cgElement = cgNamedElement; (cgElement = (CGNamedElement)cgElement.getParent()) != null; ) {
			nameManager = globalNameManager.basicGetChildNameManager(cgElement);
			if (nameManager != null) {
				addSelfNameManager(cgNamedElement, nameManager);		// ?? are lookups frequent enough to merit caching ??
				return nameManager;
			}
			nameManager = basicGetSelfNameManager(cgElement);
			if (nameManager != null) {
				addSelfNameManager(cgNamedElement, nameManager);		// ?? are lookups frequent enough to merit caching ??
				return nameManager;
			}
		}
		return null;
	}

	public @NonNull ClassNameManager createClassNameManager(@NonNull ClassableNameManager outerNameManager, @NonNull CGClass cgClass) {
		ClassNameManager nestedNameManager = codeGenerator.createClassNameManager(outerNameManager, cgClass);
		assert cgElement2childNameManager.get(cgClass) == nestedNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return nestedNameManager;
	}

	public @NonNull ExecutableNameManager createConstraintNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		ExecutableNameManager constraintNameManager = codeGenerator.createConstraintNameManager(classNameManager, cgConstraint);
		assert cgElement2childNameManager.get(cgConstraint) == constraintNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return constraintNameManager;
	}

	public @NonNull ExecutableNameManager createLoopNameManager(@NonNull ClassNameManager classNameManager, @NonNull ExecutableNameManager parentNameManager, @NonNull CGIterationCallExp cgIterationCallExp) {
		ExecutableNameManager loopNameManager = codeGenerator.createLoopNameManager(classNameManager, parentNameManager, cgIterationCallExp);
		assert cgElement2childNameManager.get(cgIterationCallExp) == loopNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return loopNameManager;
	}

	public @NonNull ExecutableNameManager createOperationNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation) {
		ExecutableNameManager operationNameManager = codeGenerator.createOperationNameManager(classNameManager, cgOperation);
		assert cgElement2childNameManager.get(cgOperation) == operationNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return operationNameManager;
	}

	public @NonNull PackageNameManager createPackageNameManager(@Nullable PackageNameManager outerNameManager, @NonNull CGPackage cgPackage) {
		PackageNameManager packageNameManager = codeGenerator.createPackageNameManager(outerNameManager, cgPackage);
		assert cgElement2childNameManager.get(cgPackage) == packageNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return packageNameManager;
	}

	public @NonNull ExecutableNameManager createPropertyNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		ExecutableNameManager propertyNameManager = codeGenerator.createPropertyNameManager(classNameManager, cgProperty);
		assert cgElement2childNameManager.get(cgProperty) == propertyNameManager;
	//	we could populate the cgScope to parent NameManager now but any CSE rewrite could invalidate this premature action.
	//	addNameManager(cgScope, nestedNameManager.getParent());
		return propertyNameManager;
	}

	/**
	 * Declare that cgElement has a name which should immediately default to reservedName. cgElement to reserve a name
	 * such as "this" for many purposes but no specific primary element.
	 * This is typically used to provide an eager name reservation for global particularly Ecore names.
	 */
	public @NonNull NameResolution declareEagerName(@Nullable CGNamedElement cgElement, @NonNull String reservedName) {
		NameResolution.EagerGlobal baseNameResolution = new NameResolution.EagerGlobal(this, cgElement, reservedName);
		context.allocateEagerName(reservedName, cgElement);
		return baseNameResolution;
	}

	public @NonNull NameResolution getAnyNameResolution() {
		return anyName;
	}

	public @NonNull NameResolution getBasicEvaluateNameResolution() {
		return basicEvaluateName;
	}

	public @NonNull NameResolution getBoxedValuesNameResolution() {
		return boxedValuesName;
	}

	@Deprecated
	public @NonNull String getCachedResultName() {
		return cachedResultName.getResolvedName();
	}

	public @NonNull NameResolution getCachedResultNameResolution() {
		return cachedResultName;
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

	public @NonNull NameResolution getContextObjectNameResolution() {
		return contextObjectName;
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

	public @NonNull String getGetCachedEvaluationResultName() {
		return getCachedEvaluationResultName.getResolvedName();
	}

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

	public @NonNull NameResolution getNewInstanceResolution() {
		return newInstanceName;
	}

	public @NonNull String getObjectName() {
		return objectName.getResolvedName();
	}

	public @NonNull NameResolution getObjectNameResolution() {
		return objectName;
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

	public void replace(@NonNull CGValuedElement oldElement, @NonNull CGValuedElement newElement) {
		NameManager nameManager = globalNameManager.basicGetSelfNameManager(oldElement);
		CGUtil.replace(oldElement, newElement);
		if (nameManager != null) {
			globalNameManager.addSelfNameManager(newElement, nameManager);
		}
	}

	/**
	 * Insert and return a CGLetExp above cgIn for cgVariable.
	 */
	public @NonNull CGLetExp rewriteAsLet(@NonNull CGValuedElement cgIn, @NonNull CGVariable cgVariable) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setTypeId(cgIn.getTypeId());
		cgLetExp.setAst(cgIn.getAst());
		replace(cgIn, cgLetExp);
		cgLetExp.setIn(cgIn);
		cgLetExp.setInit(cgVariable);
//		System.out.println("re-let " + NameUtil.debugSimpleName(cgLetExp) + " : " + cgLetExp.toString());
//		for (EObject eObject : new TreeIterable(cgLetExp, true)) {		// XXX
//			System.out.println("\t" + NameUtil.debugSimpleName(eObject) + " : " + eObject.toString());
//		}
		return cgLetExp;
	}

	@Override
	public @NonNull String toString() {
		return "globals";
	}

	public @NonNull ClassNameManager useClassNameManager(@NonNull CGClass cgClass) {
		ClassNameManager classNameManager = (ClassNameManager)globalNameManager.basicGetChildNameManager(cgClass);
		return ClassUtil.nonNullState(classNameManager);
	}

//	public @NonNull ExecutableNameManager useConstraintNameManager(@NonNull CGConstraint cgConstraint) {
//		ExecutableNameManager executableNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgConstraint);
//		return ClassUtil.nonNullState(executableNameManager);
//	}

	public @NonNull ExecutableNameManager useExecutableNameManager(@NonNull CGNamedElement cgScopingElement) {
		ExecutableNameManager executableNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgScopingElement);
		return ClassUtil.nonNullState(executableNameManager);
	}

	public @NonNull NestedNameManager useNestedNameManager(@NonNull CGNamedElement cgScopingElement) {
		NestedNameManager nestedNameManager = globalNameManager.basicGetChildNameManager(cgScopingElement);
		return ClassUtil.nonNullState(nestedNameManager);
	}

	public @NonNull ExecutableNameManager useRootExecutableNameManager(@NonNull CGNamedElement cgScopingElement) {
		return ((ExecutableNameManager)ClassUtil.nonNullState(globalNameManager.basicUseSelfNameManager(cgScopingElement))).getRootExecutableNameManager();
	}

	public @NonNull ExecutableNameManager useSelfExecutableNameManager(@NonNull CGNamedElement cgScopingElement) {
		return (ExecutableNameManager)ClassUtil.nonNullState(globalNameManager.basicUseSelfNameManager(cgScopingElement));
	}

	public @NonNull NestedNameManager useSelfNestedNameManager(@NonNull CGNamedElement cgScopingElement) {
		return (NestedNameManager)ClassUtil.nonNullState(globalNameManager.basicUseSelfNameManager(cgScopingElement));
	}

	public @NonNull ExecutableNameManager useOperationNameManager(@NonNull CGOperation cgOperation) {
		ExecutableNameManager operationNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgOperation);
		return ClassUtil.nonNullState(operationNameManager);
	}

	public @NonNull ExecutableNameManager usePropertyNameManager(@NonNull CGProperty cgProperty) {
		ExecutableNameManager propertyNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgProperty);
		return ClassUtil.nonNullState(propertyNameManager);
	}

	public @NonNull NameManager useSelfNameManager(@NonNull CGNamedElement cgNamedElement) {
		return ClassUtil.nonNullState(basicUseSelfNameManager(cgNamedElement));
	}

	/**
	 * Use wrapExp to wrap wrappedExp.
	 */
	public void wrap(@NonNull CGSourcedCallExp wrapExp, @NonNull CGValuedElement wrappedExp) {
		wrapExp.setTypeId(wrappedExp.getTypeId());
		wrapExp.setAst(wrappedExp.getAst());
		replace(wrappedExp, wrapExp);
		wrapExp.setSource(wrappedExp);
	}
}
