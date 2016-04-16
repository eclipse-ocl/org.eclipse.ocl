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
import org.eclipse.ocl.pivot.util.PivotExportedClassLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotExportedOperationLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotExportedPackageLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedClassLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedNamespaceLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedOperationLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedPackageLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotQualifiedPropertyLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedBehaviorLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedClassLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedEnumerationLiteralLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedNamespaceLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedOperationLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedPackageLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedPrecedenceLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedPropertyLookupVisitor;
import org.eclipse.ocl.pivot.util.PivotUnqualifiedVariableLookupVisitor;


public class PivotLookupSolver {
	
	protected final @NonNull Executor executor;
	
	public PivotLookupSolver (@NonNull Executor executor) {
		this.executor = executor;
	}
	
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Operation> _lookupExportedOperation(org.eclipse.ocl.pivot.Class context, java.lang.Object importer, java.lang.String oName, java.util.List<org.eclipse.ocl.pivot.Type> argTypes) {
		OperationFilter filter = new OperationFilter(executor, argTypes);
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.OPERATION,oName,filter);
		PivotExportedOperationLookupVisitor _lookupVisitor = new PivotExportedOperationLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Operation>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Operation.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Namespace> _lookupQualifiedNamespace(org.eclipse.ocl.pivot.Class context, java.lang.String nName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.NAMESPACE,nName);
		PivotQualifiedNamespaceLookupVisitor _lookupVisitor = new PivotQualifiedNamespaceLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Namespace>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Namespace.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Operation> _lookupQualifiedOperation(org.eclipse.ocl.pivot.Class context, java.lang.String oName, java.util.List<org.eclipse.ocl.pivot.Type> argTypes) {
		OperationFilter filter = new OperationFilter(executor, argTypes);
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.OPERATION,oName,filter);
		PivotQualifiedOperationLookupVisitor _lookupVisitor = new PivotQualifiedOperationLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Operation>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Operation.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Property> _lookupQualifiedProperty(org.eclipse.ocl.pivot.Class context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PROPERTY,pName);
		PivotQualifiedPropertyLookupVisitor _lookupVisitor = new PivotQualifiedPropertyLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Property>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Property.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupExportedPackage(org.eclipse.ocl.pivot.Model context, java.lang.Object importer, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotExportedPackageLookupVisitor _lookupVisitor = new PivotExportedPackageLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Namespace> _lookupQualifiedNamespace(org.eclipse.ocl.pivot.Namespace context, java.lang.String nName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.NAMESPACE,nName);
		PivotQualifiedNamespaceLookupVisitor _lookupVisitor = new PivotQualifiedNamespaceLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Namespace>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Namespace.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupExportedClass(org.eclipse.ocl.pivot.Package context, java.lang.Object importer, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotExportedClassLookupVisitor _lookupVisitor = new PivotExportedClassLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupExportedPackage(org.eclipse.ocl.pivot.Package context, java.lang.Object importer, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotExportedPackageLookupVisitor _lookupVisitor = new PivotExportedPackageLookupVisitor(_lookupEnv, importer);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupQualifiedClass(org.eclipse.ocl.pivot.Package context, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotQualifiedClassLookupVisitor _lookupVisitor = new PivotQualifiedClassLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Namespace> _lookupQualifiedNamespace(org.eclipse.ocl.pivot.Package context, java.lang.String nName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.NAMESPACE,nName);
		PivotQualifiedNamespaceLookupVisitor _lookupVisitor = new PivotQualifiedNamespaceLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Namespace>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Namespace.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupQualifiedPackage(org.eclipse.ocl.pivot.Package context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotQualifiedPackageLookupVisitor _lookupVisitor = new PivotQualifiedPackageLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Behavior> _lookupUnqualifiedBehavior(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String bName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.BEHAVIOR,bName);
		PivotUnqualifiedBehaviorLookupVisitor _lookupVisitor = new PivotUnqualifiedBehaviorLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Behavior>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Behavior.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Class> _lookupUnqualifiedClass(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String cName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.CLASS,cName);
		PivotUnqualifiedClassLookupVisitor _lookupVisitor = new PivotUnqualifiedClassLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Class>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Class.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.EnumerationLiteral> _lookupUnqualifiedEnumerationLiteral(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String eName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.ENUMERATION_LITERAL,eName);
		PivotUnqualifiedEnumerationLiteralLookupVisitor _lookupVisitor = new PivotUnqualifiedEnumerationLiteralLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.EnumerationLiteral>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.EnumerationLiteral.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Namespace> _lookupUnqualifiedNamespace(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String nName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.NAMESPACE,nName);
		PivotUnqualifiedNamespaceLookupVisitor _lookupVisitor = new PivotUnqualifiedNamespaceLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Namespace>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Namespace.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Operation> _lookupUnqualifiedOperation(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String oName, java.util.List<org.eclipse.ocl.pivot.Type> argTypes) {
		OperationFilter filter = new OperationFilter(executor, argTypes);
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.OPERATION,oName,filter);
		PivotUnqualifiedOperationLookupVisitor _lookupVisitor = new PivotUnqualifiedOperationLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Operation>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Operation.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Package> _lookupUnqualifiedPackage(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PACKAGE,pName);
		PivotUnqualifiedPackageLookupVisitor _lookupVisitor = new PivotUnqualifiedPackageLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Package>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Package.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Precedence> _lookupUnqualifiedPrecedence(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PRECEDENCE,pName);
		PivotUnqualifiedPrecedenceLookupVisitor _lookupVisitor = new PivotUnqualifiedPrecedenceLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Precedence>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Precedence.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Property> _lookupUnqualifiedProperty(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String pName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.PROPERTY,pName);
		PivotUnqualifiedPropertyLookupVisitor _lookupVisitor = new PivotUnqualifiedPropertyLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Property>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Property.class));
	}
	
	public PivotLookupResult<org.eclipse.ocl.pivot.Variable> _lookupUnqualifiedVariable(org.eclipse.ocl.pivot.util.Visitable context, java.lang.String vName) {
		PivotSingleResultLookupEnvironment _lookupEnv = new PivotSingleResultLookupEnvironment(executor, org.eclipse.ocl.pivot.PivotPackage.Literals.VARIABLE,vName);
		PivotUnqualifiedVariableLookupVisitor _lookupVisitor = new PivotUnqualifiedVariableLookupVisitor(_lookupEnv);
		context.accept(_lookupVisitor);
		return new PivotLookupResultImpl<org.eclipse.ocl.pivot.Variable>
				(_lookupEnv.getNamedElementsByKind(org.eclipse.ocl.pivot.Variable.class));
	}
}
