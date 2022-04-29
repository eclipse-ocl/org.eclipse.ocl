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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GlobalContext;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class JavaGlobalContext<@NonNull CG extends JavaCodeGenerator> extends AbstractJavaContext<CG> implements GlobalContext
{
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull ImportNameManager importNameManager;

	private @NonNull Map<@NonNull CGElement, @NonNull JavaLocalContext<@NonNull ? extends CG>> localContexts = new HashMap<>();
	private @NonNull Set<@NonNull CGValuedElement> globals = new HashSet<>();

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

	public JavaGlobalContext(@NonNull CG codeGenerator) {
		super(codeGenerator);
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.importNameManager = codeGenerator.createImportNameManager();
		//
		this.thisName = globalNameManager.declareReservedName(null, JavaConstants.THIS_NAME);
		//
		this.anyName = globalNameManager.declareGlobalName(null, JavaConstants.ANY_NAME);
//		this.eName = globalNameManager.declareGlobalName(null, JavaConstants.E_NAME);
		this.evaluateName = globalNameManager.declareGlobalName(null, JavaConstants.EVALUATE_NAME);
		this.evaluationCacheName = globalNameManager.declareGlobalName(null, JavaConstants.EVALUATION_CACHE_NAME);
		this.executorName = globalNameManager.declareGlobalName(null, JavaConstants.EXECUTOR_NAME);
		this.getCachedEvaluationResult = globalNameManager.declareGlobalName(null, JavaConstants.GET_CACHED_EVLUATION_RESULT_NAME);
		this.idResolverName = globalNameManager.declareGlobalName(null, JavaConstants.ID_RESOLVER_NAME);
		this.initValueName = globalNameManager.declareGlobalName(null, "initValue");
		this.instanceName = globalNameManager.declareGlobalName(null, JavaConstants.INSTANCE_NAME);
		this.modelManagerName = globalNameManager.declareGlobalName(null, JavaConstants.MODEL_MANAGER_NAME);
		this.selfName = globalNameManager.declareGlobalName(null, PivotConstants.SELF_NAME);
		this.sourceAndArgumentValuesName = globalNameManager.declareGlobalName(null, JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		this.standardLibraryVariableName = globalNameManager.declareGlobalName(null, JavaConstants.STANDARD_LIBRARY_NAME);
//.		this.thisName = globalNameManager.getReservedName(null, JavaConstants.THIS_NAME);
		this.typeIdName = globalNameManager.declareGlobalName(null, JavaConstants.TYPE_ID_NAME);
		this.valueName = globalNameManager.declareGlobalName(null, "value");
	}

	protected void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globals.add(cgGlobal);
	}

	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String className) {
		return importNameManager.addImport(isRequired, className);
	}

	@Override
	public @Nullable JavaLocalContext<@NonNull ? extends CG> basicGetLocalContext(@NonNull CGNamedElement cgElement) {
		for (CGElement cgScope = cgElement; cgScope != null; cgScope = cgScope.getParent()) {
			JavaLocalContext<@NonNull ? extends CG> localContext = localContexts.get(cgScope);
			if (localContext != null) {
				return localContext;
			}
		}
		return null;
	}

/*	@Override
	public @NonNull JavaLocalContext<@NonNull ? extends CG> createLocalContext(@NonNull CGNamedElement cgElement) {
		assert basicGetLocalContext(cgElement) == null;
		JavaLocalContext<@NonNull ? extends CG> localContext = localContexts.get(cgElement);
		assert localContext == null;
	/*	CGElement cgScope = cgElement;
		CGIterationCallExp cgIterationScope = null;
		for (; cgScope != null; cgScope = cgScope.getParent()) {
			if (cgScope instanceof CGIterationCallExp) {
				cgIterationScope = (CGIterationCallExp)cgScope;
				localContext = localContexts.get(cgScope);
				if (localContext != null) {
					break;
				}
			}
			if (cgScope.isContext()) {
				break;
			}
		}
		assert cgScope != null;
		if (localContext == null) { * /
		for (LocalContext aContext : contextStack) {

		}
			localContext = localContexts.get(cgScope);
			if (localContext == null) {
				localContext = createNestedContext(cgScope);
				localContexts.put(cgScope, localContext);
			}
			if (cgIterationScope != null) {
				localContexts.put(cgIterationScope, localContext);
			}
	//	}
		localContexts.put(cgElement, localContext);
		return localContext;
	} */

	public @NonNull JavaLocalContext<@NonNull ? extends CG> createLocalContext(@Nullable JavaLocalContext<@NonNull ?> outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
		return new JavaLocalContext<CG>(this, (JavaLocalContext<@NonNull ? extends CG>)outerContext, cgNamedElement, asNamedElement);
	}

	public @NonNull NameResolution getAnyNameResolution() {
		return anyName;
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

	@Override
	public @NonNull ImportNameManager getImportNameManager() {
		return importNameManager;
	}

	@Deprecated /* deprecated use getImportManager */
	public @NonNull Set<@NonNull String> getImports() {
		return importNameManager.getLong2ShortImportNames().keySet();
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

	@Override
	public @NonNull JavaLocalContext<@NonNull ? extends CG> getLocalContext(@NonNull CGNamedElement cgElement) {
		return ClassUtil.nonNullState(basicGetLocalContext(cgElement));
	}

	public @NonNull String getModelManagerName() {
		return modelManagerName.getResolvedName();
	}

	public @NonNull NameResolution getModelManagerNameResolution() {
		return modelManagerName;
	}

	public @NonNull GlobalNameManager getNameManager() {
		return globalNameManager;
	}

/*	public @NonNull String getResolvedName(@NonNull CGValuedElement cgValuedElement) {
		return globalNameManager.getResolvedName(cgValuedElement);
	/*	JavaLocalContext<@NonNull ? extends CG> localContext = basicGetLocalContext(cgValuedElement);
		if ((localContext != null) && !cgValuedElement.isGlobal()) {
			return localContext.getValueName(cgValuedElement);
		}
		else {
			CGValuedElement cgValue = cgValuedElement.getNamedValue();
			String valueName = cgValue.getValueName();
			if (valueName == null) {
				valueName = globalNameManager.getGlobalSymbolName(cgValue, cgValue.getName());
				cgValue.setValueName(valueName);
			//	String name = globalNameManager.helper.getNameHint(anObject);
			//	globalNameManager.queueValueName(cgValue, null, name);
			}
			return valueName;
		} * /
	} */

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

	public @NonNull LocalContext initLocalContext(@Nullable LocalContext outerContext, @NonNull CGNamedElement cgNamedElement, @NonNull NamedElement asNamedElement) {
		assert cgNamedElement.getAst() != null;
//		LocalContext localContext = new DebugLocalContext(outerContext, cgNamedElement, asNamedElement); //codeGenerator.getGlobalContext().createLocalContext(cgNamedElement);
		JavaLocalContext<@NonNull ? extends CG> localContext = createLocalContext((JavaLocalContext<@NonNull ? extends CG>) outerContext, cgNamedElement, asNamedElement); //codeGenerator.getGlobalContext().createLocalContext(cgNamedElement);
		setLocalContext(cgNamedElement, localContext);
		return localContext;
	}

	public void setLocalContext(@NonNull CGNamedElement cgNamedElement, @NonNull JavaLocalContext<@NonNull ? extends CG> localContext) {
		JavaLocalContext<?> old = localContexts.put(cgNamedElement, localContext);
		assert old == null;
	}
}