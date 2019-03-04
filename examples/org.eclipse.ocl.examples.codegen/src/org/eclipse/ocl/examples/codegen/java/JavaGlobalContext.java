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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.GlobalContext;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * A JavaGlobalContext maintains the Java-specific global context for generation of code.
 */
public class JavaGlobalContext<@NonNull CG extends JavaCodeGenerator> extends AbstractJavaContext<CG> implements GlobalContext
{
	protected final @NonNull NameManager nameManager;
	protected final @NonNull ImportNameManager importNameManager;

	private @NonNull Map<@NonNull CGElement, @NonNull JavaLocalContext<@NonNull ? extends CG>> localContexts = new HashMap<>();
	private @NonNull Set<@NonNull CGValuedElement> globals = new HashSet<>();

	protected final @NonNull String eName;
	protected final @NonNull String evaluateName;
	protected final @NonNull String instanceName;
	protected final @NonNull String selfName;
	protected final @NonNull String sourceAndArgumentValuesName;

	public JavaGlobalContext(@NonNull CG codeGenerator) {
		super(codeGenerator);
		this.nameManager = codeGenerator.getNameManager();
		this.importNameManager = codeGenerator.createImportNameManager();
		this.eName = nameManager.reserveName(JavaConstants.E_NAME, null);
		this.evaluateName = nameManager.reserveName(JavaConstants.EVALUATE_NAME, null);
		this.instanceName = nameManager.reserveName(JavaConstants.INSTANCE_NAME, null);
		this.selfName = nameManager.reserveName(PivotConstants.SELF_NAME, null);
		this.sourceAndArgumentValuesName = nameManager.reserveName(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME, null);
	}

	protected void addGlobal(@NonNull CGValuedElement cgGlobal) {
		globals.add(cgGlobal);
	}

	public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String className) {
		return importNameManager.addImport(isRequired, className);
	}

	protected @NonNull JavaLocalContext<@NonNull ? extends CG> createNestedContext(@NonNull CGElement cgScope) {
		return new JavaLocalContext<CG>(this, cgScope);
	}

	public @Nullable EClass getEClass(@NonNull ElementId elementId) {
		IdVisitor<EClass> id2EClassVisitor = codeGenerator.getId2EClassVisitor();
		return elementId.accept(id2EClassVisitor);
	}

	public @NonNull String getEName() {
		return eName;
	}

	public @NonNull String getEvaluateName() {
		return evaluateName;
	}

	public @NonNull Collection<@NonNull CGValuedElement> getGlobals() {
		return globals;
	}

	public @NonNull ImportNameManager getImportNameManager() {
		return importNameManager;
	}

	@Deprecated /* deprecated use getImportManager */
	public @NonNull Set<String> getImports() {
		return importNameManager.getLong2ShortImportNames().keySet();
	}

	public @NonNull String getInstanceName() {
		return instanceName;
	}

	@Override
	public @Nullable JavaLocalContext<@NonNull ? extends CG> getLocalContext(@NonNull CGElement cgElement) {
		JavaLocalContext<@NonNull ? extends CG> localContext = localContexts.get(cgElement);
		if (localContext == null) {
			CGElement cgScope = cgElement;
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
			if (cgScope == null) {
				return null;
			}
			if (localContext == null) {
				localContext = localContexts.get(cgScope);
				if (localContext == null) {
					localContext = createNestedContext(cgScope);
					localContexts.put(cgScope, localContext);
				}
				if (cgIterationScope != null) {
					localContexts.put(cgIterationScope, localContext);
				}
			}
			localContexts.put(cgElement, localContext);
		}
		return localContext;
	}

	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	public @NonNull String getSelfName() {
		return selfName;
	}

	public @NonNull String getSourceAndArgumentValuesName() {
		return sourceAndArgumentValuesName;
	}

	public @NonNull String getValueName(@NonNull CGValuedElement cgValuedElement) {
		JavaLocalContext<@NonNull ? extends CG> localContext = getLocalContext(cgValuedElement);
		if ((localContext != null) && !cgValuedElement.isGlobal()) {
			return localContext.getValueName(cgValuedElement);
		}
		else {
			CGValuedElement cgValue = cgValuedElement.getNamedValue();
			String valueName = cgValue.getValueName();
			if (valueName == null) {
				valueName = nameManager.getGlobalSymbolName(cgValue, cgValue.getName());
				cgValue.setValueName(valueName);
			}
			return valueName;
		}
	}
}