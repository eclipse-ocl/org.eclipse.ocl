/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.lookup;/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/


import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment;
import org.eclipse.ocl.pivot.internal.manager.PivotExecutorManager;
import org.eclipse.ocl.pivot.util.AbstractPivotLookupVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;


/**
 * TODO To be generated at:
 * emf-gen/org.eclipse.ocl.pivot.util
 * 
 * @since 1.1
 */
public abstract class AbstractPivotNameResolver {

	@NonNull
	final protected EnvironmentFactory envFactory;
	
	public AbstractPivotNameResolver(@NonNull EnvironmentFactory mmManager) {
		this.envFactory = mmManager;
	}
	
	@NonNull
	public SingleResultEnvironment computeLookup(@NonNull Element lookupElement,
		@NonNull String name/*, boolean isQualified*/) {
		SingleResultEnvironment env = createLookupEnvironment(envFactory, lookupElement, name);
		return computeNamedResult(lookupElement, env);
	}
	
//	@NonNull
//	public IAllResultsEnvironment computeLookup(
//			@NonNull Element lookupElement/*, boolean isQualified*/) {
//		AutoIPivotLookupContext context = createLookupContext(lookupFeature, lookupElement);
//		IAllResultsEnvironment env = createLookupEnvironment(mmManager, lookupFeature);		
//		return computeUnnamedResult(lookupElement, env, context);
//	}
	
	@NonNull
	abstract protected  AbstractPivotLookupVisitor createLookupVisitor(@NonNull LookupEnvironment env );
	
	@NonNull
	protected  SingleResultLookupEnvironment createLookupEnvironment(@NonNull EnvironmentFactory mmManager, 
		@NonNull Element lookupElement, @NonNull  String name) {
		// FIXME ask Ed how to better provide this DomainEvaluator
		return new SingleResultLookupEnvironment(new PivotExecutorManager(mmManager,  lookupElement), name);
	}
	
//	@NonNull
//	protected  IAllResultsEnvironment createLookupEnvironment(@NonNull MetaModelManager mmManager, 
//		@NonNull EStructuralFeature lookupFeature) {
//		return new AllResultsEnvironment(mmManager, lookupFeature);
//	}
	
		
	@NonNull
	protected  LookupEnvironment executeVisitor(@NonNull Element element, 
		@NonNull LookupEnvironment env) { 
		return ClassUtil.nonNullState(element.accept(createLookupVisitor(env)));
	}
	
	@NonNull
	protected SingleResultEnvironment computeNamedResult(@NonNull Element element, 
		@NonNull SingleResultEnvironment env) { 
//		FIXME resolveDuplicates might not be needed
//		SingleResultEnvironment env2= (SingleResultEnvironment) executeVisitor(element, env);	
//		return env2 instanceof SingleResultLookupEnvironment ? ((SingleResultLookupEnvironment)env2).resolveDuplicates() : env2;
		return env;
	}		
	
//	@NonNull
//	protected  AutoIUnnamedLookupResult computeUnnamedResult(@NonNull Element element, 
//		@NonNull IAllResultsEnvironment env) { 
//		AutoIPivotLookupEnvironment env2 = executeVisitor(element, env, context);		
//		return ((IAllResultsEnvironment)env2).getResult();
//	}	

	@NonNull
	public SingleResultEnvironment computeReferredOperationLookup(
			@NonNull OperationCallExp opCallExp) {
		throw new IllegalArgumentException("No auto-generation implemented");
	}

	@NonNull
	public SingleResultEnvironment computeReferredIterationLookup(
			@NonNull IteratorExp iteratorExp) {
		throw new IllegalArgumentException("No auto-generation implemented");
	}

	@NonNull
	public SingleResultEnvironment computeReferredVariableLookup(
			@NonNull VariableExp variableExp) {
		throw new IllegalArgumentException("No auto-generation implemented");
	}
}
