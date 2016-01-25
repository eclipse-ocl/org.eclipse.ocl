/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * This code is auto-generated
 * from: org.eclipse.ocl.pivot/model/Pivot.genmodel
 * template: org.eclipse.ocl.examples.build.xtend.GenerateAutoLookupInfrastructureXtend
 *
 * Only the copyright statement is editable.
 *******************************************************************************/
package	org.eclipse.ocl.pivot.internal.lookup.util;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotExportedLookupVisitor;

public class PivotLookupSolver {
	
	protected final @NonNull Executor executor;
	
	public PivotLookupSolver (@NonNull Executor executor) {
		this.executor = executor;
	}
	
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Operation> _lookupQualifiedOperation(org.eclipse.ocl.pivot.Class context, java.lang.String oName, java.util.List<org.eclipse.ocl.pivot.Type> argTypes) {
		OperationFilter filter = new OperationFilter(executor, argTypes);
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.OPERATION,oName,filter);
		PivotQualifiedLookupVisitor _lookupVisitor = new PivotQualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Operation>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Operation.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Property> _lookupQualifiedProperty(org.eclipse.ocl.pivot.Class context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PROPERTY,pName);
		PivotQualifiedLookupVisitor _lookupVisitor = new PivotQualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Property>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Property.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupExportedPackage(org.eclipse.ocl.pivot.Model context, java.lang.Object importer, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotExportedLookupVisitor _lookupVisitor = new PivotExportedLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupExportedClass(org.eclipse.ocl.pivot.Package context, java.lang.Object importer, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotExportedLookupVisitor _lookupVisitor = new PivotExportedLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupQualifiedClass(org.eclipse.ocl.pivot.Package context, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotQualifiedLookupVisitor _lookupVisitor = new PivotQualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupQualifiedPackage(org.eclipse.ocl.pivot.Package context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotQualifiedLookupVisitor _lookupVisitor = new PivotQualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupClass(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Namespace> _lookupNamespace(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String nName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.NAMESPACE,nName);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Namespace>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Namespace.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Operation> _lookupOperation(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String oName, java.util.List<org.eclipse.ocl.pivot.Type> argTypes) {
		OperationFilter filter = new OperationFilter(executor, argTypes);
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.OPERATION,oName,filter);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Operation>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Operation.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupPackage(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Property> _lookupProperty(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PROPERTY,pName);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Property>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Property.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Variable> _lookupVariable(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String vName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.VARIABLE,vName);
		PivotUnqualifiedLookupVisitor _lookupVisitor = new PivotUnqualifiedLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Variable>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Variable.class));
	}
}
