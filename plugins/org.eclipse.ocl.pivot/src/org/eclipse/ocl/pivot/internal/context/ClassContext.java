/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.context;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * ClassContext supports parsing OCL expressions in the context of a Class.
 */
public class ClassContext extends AbstractParserContext
{
	protected final org.eclipse.ocl.pivot.@NonNull Class classContext;
	protected final boolean isRequired;
	protected final @Nullable Element instanceContext;
	private @NonNull String selfName = PivotConstants.SELF_NAME;

	/**
	 * Construct a context to parse the uri's contents within the context of the class whose type is classContext
	 * and for which there may be a specific instanceContext.
	 *
	 * If classContext is null, OclVoidType is used.
	 *
	 * isRequired true/false determines whether self may be null or not. a null isRequired determines the nullity
	 * of self according to whether classContext is OclVoidType or not.
	 */
	public ClassContext(@NonNull EnvironmentFactory environmentFactory, @Nullable URI uri, org.eclipse.ocl.pivot.@Nullable Class classContext, @Nullable Boolean isRequired, @Nullable Element instanceContext) {
		super(environmentFactory, uri);
		if (classContext == null) {
			classContext = environmentFactory.getStandardLibrary().getOclVoidType();
		}
		this.classContext = getMetamodelManager().getPrimaryClass(classContext);
		this.isRequired = isRequired != null ? isRequired.booleanValue() : !isOclVoidType(this.classContext);
		this.instanceContext = instanceContext;
	}

	private boolean isOclVoidType(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		VoidType oclVoidType = environmentFactory.getStandardLibrary().getOclVoidType();
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		CompleteClass completeClass = completeModel.getCompleteClass(asClass);
		CompleteClass completeVoidType = completeModel.getCompleteClass(oclVoidType);
		return completeClass == completeVoidType;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassContext() {
		return classContext;
	}

	@Override
	public @Nullable Element getInstanceContext() {
		return instanceContext;
	}

	@Override
	public void initialize(@NonNull Base2ASConversion conversion, @NonNull ExpressionInOCL expression) {
		super.initialize(conversion, expression);
		if ( !(expression.eContainer() instanceof Feature) || !((Feature)expression.eContainer()).isIsStatic()) {
			conversion.getHelper().setContextVariable(expression, selfName, classContext, isRequired, instanceContext);
		}
	}

	public boolean isRequired() {
		return isRequired;
	}


	/**
	 * @since 1.3
	 */
	public void setSelfName(@NonNull String selfName) {
		this.selfName = selfName;
	}
}
