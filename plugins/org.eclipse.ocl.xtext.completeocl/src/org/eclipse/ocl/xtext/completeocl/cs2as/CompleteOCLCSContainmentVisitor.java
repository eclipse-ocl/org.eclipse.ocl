/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.cs2as;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.ContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;
import org.eclipse.ocl.xtext.completeoclcs.DefOperationCS;
import org.eclipse.ocl.xtext.completeoclcs.DefPropertyCS;
import org.eclipse.ocl.xtext.completeoclcs.OperationContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;
import org.eclipse.ocl.xtext.completeoclcs.PropertyContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.util.AbstractCompleteOCLCSContainmentVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;

public class CompleteOCLCSContainmentVisitor extends AbstractCompleteOCLCSContainmentVisitor
{
	public class OperationDeclScopeFilter implements ScopeFilter
	{
		protected final @Nullable Type sourceType;
		private final @NonNull List<ParameterCS> csParameters;
		
		public OperationDeclScopeFilter(@Nullable Type sourceType, @NonNull List<ParameterCS> csParameters) {
			this.sourceType = sourceType != null ? PivotUtilInternal.getType(sourceType) : null;		// FIXME redundant
			this.csParameters = csParameters;
		}

		@Override
		public boolean matches(@NonNull EnvironmentView environmentView, @NonNull Object object) {
			if (object instanceof Iteration) {
				return false;
			}
			if (object instanceof Operation) {
				Operation candidateOperation = (Operation)object;
				List<Parameter> candidateParameters = candidateOperation.getOwnedParameters();
				int iMax = csParameters.size();
				if (iMax != candidateParameters.size()) {
					return false;
				}
				return true;
			}
			else {
				return false;
			}
		}
	}

	public CompleteOCLCSContainmentVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}
	
/*	let classifierContextDecls = contextDecls->selectByKind(ClassifierContextDeclCS) in
	let classifierInvariants = classifierContextDecls.ownedInvariants in
	let defOperations = classifierContextDecls.ownedDefinitions->selectByKind(DefOperationCS) in
	let defProperties = classifierContextDecls.ownedDefinitions->selectByKind(DefPropertyCS) in
	let operationContextDecls = contextDecls->selectByKind(OperationContextDeclCS) in
	let propertyContextDecls = contextDecls->selectByKind(PropertyContextDeclCS) in
	let propertyInvariants = propertyContextDecls.ownedDerivedInvariants in
	ocl::Class {
		name = modelClass.name,
		ownedInvariants = classifierInvariants.ast()->union(propertyInvariants.ast()),
		ownedOperations = operationContextDecls->collect(o | contextOperation(o))->union(defOperations.ast())->sortedBy(name),
		ownedProperties = propertyContextDecls->collect(p | contextProperty(p))->union(defProperties.ast())->sortedBy(name)
	} */
	private org.eclipse.ocl.pivot.@NonNull Class contextClass(org.eclipse.ocl.pivot.@NonNull Class modelClass,
			@NonNull List<ContextDeclCS> contextDecls) {
		List<ConstraintCS> allInvariants = new ArrayList<ConstraintCS>();
		List<ClassifierContextDeclCS> classifierContextDecls = new ArrayList<ClassifierContextDeclCS>();
		List<OperationContextDeclCS> operationContextDecls = new ArrayList<OperationContextDeclCS>();
		List<PropertyContextDeclCS> propertyContextDecls = new ArrayList<PropertyContextDeclCS>();
		List<DefOperationCS> defOperations = new ArrayList<DefOperationCS>();
		List<DefPropertyCS> defProperties = new ArrayList<DefPropertyCS>();
		for (ContextDeclCS contextDecl : contextDecls) {
			if (contextDecl instanceof ClassifierContextDeclCS) {
				ClassifierContextDeclCS classifierContextDecl = (ClassifierContextDeclCS)contextDecl;
				classifierContextDecls.add(classifierContextDecl);
				allInvariants.addAll(classifierContextDecl.getOwnedInvariants());
				for (DefCS csDef : classifierContextDecl.getOwnedDefinitions()) {
					if (csDef instanceof DefOperationCS) {
						defOperations.add((DefOperationCS) csDef);
					}
					else if (csDef instanceof DefPropertyCS) {
						defProperties.add((DefPropertyCS) csDef);
					}
				}
			}
			else if (contextDecl instanceof OperationContextDeclCS) {
				operationContextDecls.add((OperationContextDeclCS) contextDecl);
			}
			else if (contextDecl instanceof PropertyContextDeclCS) {
				PropertyContextDeclCS propertyContextDecl = (PropertyContextDeclCS) contextDecl;
				propertyContextDecls.add(propertyContextDecl);
				allInvariants.addAll(propertyContextDecl.getOwnedDerivedInvariants());
			}
		}
		List<Operation> contextOperations = new ArrayList<Operation>();
		for (OperationContextDeclCS operationContextDecl : operationContextDecls) {
			if (operationContextDecl != null) {
				Operation contextOperation = contextOperation(operationContextDecl);
				contextOperations.add(contextOperation);
			}
		}
		for (DefOperationCS defOperation : defOperations) {
			Operation contextOperation = PivotUtil.getPivot(Operation.class, defOperation);
			if (contextOperation != null) {
				contextOperations.add(contextOperation);
			}
		}
		Collections.sort(contextOperations, NameUtil.NAMEABLE_COMPARATOR);
		List<Property> contextProperties = new ArrayList<Property>();
		for (PropertyContextDeclCS propertyContextDecl : propertyContextDecls) {
			if (propertyContextDecl != null) {
				Property contextProperty = contextProperty(propertyContextDecl);
				contextProperties.add(contextProperty);
			}
		}
		for (DefPropertyCS defProperty : defProperties) {
			Property contextProperty = PivotUtil.getPivot(Property.class, defProperty);
			if (contextProperty != null) {
				contextProperties.add(contextProperty);
			}
		}
		Collections.sort(contextProperties, NameUtil.NAMEABLE_COMPARATOR);
		ClassifierContextDeclCS csElement = classifierContextDecls.size() > 0 ? classifierContextDecls.get(0) : null;
		org.eclipse.ocl.pivot.Class contextClass = context.refreshModelElement(org.eclipse.ocl.pivot.Class.class, PivotPackage.Literals.CLASS, csElement);
		contextClass.setName(modelClass.getName());
		context.refreshPivotList(Constraint.class, contextClass.getOwnedInvariants(), allInvariants);
		context.refreshList(contextClass.getOwnedOperations(), contextOperations);
		context.refreshList(contextClass.getOwnedProperties(), contextProperties);
		context.refreshComments(contextClass, csElement);
		for (@SuppressWarnings("null")@NonNull ContextDeclCS contextDecl : contextDecls) {
			if (contextDecl instanceof ClassifierContextDeclCS) {
				context.installPivotUsage(contextDecl, contextClass);
			}
		}
		return contextClass;
	}

/*	let modelOperation = operationContext.referredOperation in
	ocl::Operation {
		name = modelOperation.name,
		type = modelOperation.type,
		isRequired = modelOperation.isRequired,
		ownedParameter = modelOperation.ownedParameter,
		isStatic = modelOperation.isStatic,
		bodyExpression = if operationContext.ownedBodies->notEmpty() then operationContext.ownedBodies->first().ast() else null endif,
		postcondition = operationContext.ownedPostconditions.ast(),
		precondition = operationContext.ownedPreconditions.ast()
	} */
	private @NonNull Operation contextOperation(@NonNull OperationContextDeclCS operationContextDecl) {
		Operation contextOperation = context.refreshModelElement(Operation.class, PivotPackage.Literals.OPERATION, operationContextDecl);
		Operation modelOperation = operationContextDecl.getReferredOperation();
		if (modelOperation != null) {
			context.refreshName(contextOperation, ClassUtil.nonNullModel(modelOperation.getName()));
			context.setType(contextOperation, modelOperation.getType(), modelOperation.isIsRequired());
			List<ExpSpecificationCS> ownedBodies = operationContextDecl.getOwnedBodies();
			ExpSpecificationCS ownedBody = ownedBodies.size() > 0 ? ownedBodies.get(0) : null;
			LanguageExpression languageExpression = ownedBody != null ? PivotUtil.getPivot(LanguageExpression.class,  ownedBody) : null;
			contextOperation.setBodyExpression(languageExpression);
			context.refreshPivotList(Parameter.class, contextOperation.getOwnedParameters(), operationContextDecl.getOwnedParameters());
			context.refreshPivotList(Constraint.class, contextOperation.getOwnedPreconditions(), operationContextDecl.getOwnedPreconditions());
			context.refreshPivotList(Constraint.class, contextOperation.getOwnedPostconditions(), operationContextDecl.getOwnedPostconditions());
		}
		context.refreshComments(contextOperation, operationContextDecl);
		return contextOperation;
	}

	/*	let packageInvariants = packageDecls.ownedInvariants in
	let allContextDecls = contextDecls->union(packageDecls.ownedContexts)->asSet() in
	let modelClasses = allContextDecls.referredClass()->asSet() in
	ocl::Package {
		name = modelPackage.name,
		nsPrefix = modelPackage.nsPrefix,
		URI = modelPackage.URI,
--		ownedInvariants = packageInvariants.ast(),
		ownedClasses = modelClasses->sortedBy(name)->collect(c | contextClass(c, allContextDecls->select(cd | cd.referredClass() = c)))
	} */
	private org.eclipse.ocl.pivot.@NonNull Package contextPackage(org.eclipse.ocl.pivot.@NonNull Package modelPackage,
			@NonNull List<PackageDeclarationCS> packageDecls, @NonNull List<ContextDeclCS> contextDecls) {
		List<ConstraintCS> packageInvariants = new ArrayList<ConstraintCS>();
		List<ContextDeclCS> allContextDecls = new ArrayList<ContextDeclCS>(contextDecls);
		for (PackageDeclarationCS packageDecl : packageDecls) {
			packageInvariants.addAll(packageDecl.getOwnedInvariants());
			allContextDecls.addAll(packageDecl.getOwnedContexts());
		}
		Set<org.eclipse.ocl.pivot.Class> modelClasses = new HashSet<org.eclipse.ocl.pivot.Class>();
		for (ContextDeclCS contextDecl : allContextDecls) {
			Class modelClass = getReferredClass(contextDecl);
			if (modelClass != null) {
				modelClasses.add(modelClass);
			}
		}
		List<org.eclipse.ocl.pivot.Class> sortedModelClasses = new ArrayList<org.eclipse.ocl.pivot.Class>(modelClasses);
		Collections.sort(sortedModelClasses, NameUtil.NAMEABLE_COMPARATOR);
		List<org.eclipse.ocl.pivot.Class> contextClasses = new ArrayList<org.eclipse.ocl.pivot.Class>();
		for (org.eclipse.ocl.pivot.Class modelClass : sortedModelClasses) {
			if (modelClass != null) {
				List<ContextDeclCS> selectedContexts = new ArrayList<ContextDeclCS>();
				for (ContextDeclCS csContext : allContextDecls) {
					org.eclipse.ocl.pivot.Class asClass = getReferredClass(csContext);
					if (modelClass == asClass) {
						selectedContexts.add(csContext);
					}
				}
				org.eclipse.ocl.pivot.Class contextClass = contextClass(modelClass, selectedContexts);
				contextClasses.add(contextClass);
			}
		}
		PackageDeclarationCS csElement = packageDecls.size() > 0 ? packageDecls.get(0) : null;
		org.eclipse.ocl.pivot.Package contextPackage = context.refreshModelElement(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, csElement);
		contextPackage.setName(modelPackage.getName());
		contextPackage.setURI(modelPackage.getURI());
		context.refreshList(contextPackage.getOwnedClasses(), contextClasses);
		context.refreshComments(contextPackage, csElement);
		for (int i = 1; i < packageDecls.size(); i++) {
			csElement = packageDecls.get(i);
			if (csElement != null) {
				context.installPivotUsage(csElement, contextPackage);
			}
		}
		return contextPackage;
	}

/*	let modelProperty = propertyContext.referredProperty in
	ocl::Property {
		name = modelProperty.name,
		type = modelProperty.type,
		isRequired = modelProperty.isRequired,
		isStatic = modelProperty.isStatic,
		defaultExpression = if propertyContext.ownedDefaultExpressions->notEmpty() then propertyContext.ownedDefaultExpressions->first().ast() else null endif
	} */
	private @NonNull Property contextProperty(@NonNull PropertyContextDeclCS propertyContextDecl) {
		Property contextProperty = context.refreshModelElement(Property.class, PivotPackage.Literals.PROPERTY, propertyContextDecl);
		Property modelProperty = propertyContextDecl.getReferredProperty();
		if (modelProperty != null) {
			context.refreshName(contextProperty, ClassUtil.nonNullModel(modelProperty.getName()));
			context.setType(contextProperty, modelProperty.getType(), modelProperty.isIsRequired());
			List<ExpSpecificationCS> ownedDefaultExpressions = propertyContextDecl.getOwnedDefaultExpressions();
			ExpSpecificationCS ownedDefaultExpression = ownedDefaultExpressions.size() > 0 ? ownedDefaultExpressions.get(0) : null;
			LanguageExpression languageExpression = ownedDefaultExpression != null ? PivotUtil.getPivot(LanguageExpression.class,  ownedDefaultExpression) : null;
			contextProperty.setOwnedExpression(languageExpression);
		}
		context.refreshComments(contextProperty, propertyContextDecl);
		return contextProperty;
	}

	private org.eclipse.ocl.pivot.@Nullable Class getReferredClass(ContextDeclCS csContext) {
		if (csContext instanceof ClassifierContextDeclCS) {
			return ((ClassifierContextDeclCS)csContext).getReferredClass();
		}
		else if (csContext instanceof OperationContextDeclCS) {
			Operation modelOperation = ((OperationContextDeclCS)csContext).getReferredOperation();
			return modelOperation != null ? modelOperation.getOwningClass() : null;
		}
		else if (csContext instanceof PropertyContextDeclCS) {
			Property modelProperty = ((PropertyContextDeclCS)csContext).getReferredProperty();
			return modelProperty != null ? modelProperty.getOwningClass() : null;
		}
		return null;
	}

	private org.eclipse.ocl.pivot.@Nullable Package getReferredPackage(@NonNull ContextDeclCS csContext) {
		org.eclipse.ocl.pivot.Class modelClass = getReferredClass(csContext);
		return modelClass != null ? modelClass.getOwningPackage() : null;
	}

	@Override
	public Continuation<?> visitClassifierContextDeclCS(@NonNull ClassifierContextDeclCS csElement) {
		return null;
	}
	
//	let modelPackages = ownedPackages.referredPackage->asSet()->union(ownedContexts.referredPackage()) in
//	ocl::Model {
//		name = name,
//		imports = ownedIncludes.ast(),
//		ownedPackages = modelPackages->sortedBy(name)->collect(p | contextPackage(p, ownedPackages->select(referredPackage = p), ownedContexts->select(referredPackage() = p)))
//	}
	@Override
	public Continuation<?> visitCompleteOCLDocumentCS(@NonNull CompleteOCLDocumentCS csElement) {
		Set<org.eclipse.ocl.pivot.Package> modelPackages = new HashSet<org.eclipse.ocl.pivot.Package>();
		List<PackageDeclarationCS> ownedPackages = csElement.getOwnedPackages();
		for (PackageDeclarationCS csPackage : ownedPackages) {
			org.eclipse.ocl.pivot.Package modelPackage = csPackage.getReferredPackage();
			if (modelPackage != null) {
				modelPackages.add(modelPackage);
			}
		}
		List<ContextDeclCS> ownedContexts = csElement.getOwnedContexts();
		for (ContextDeclCS csContext : ownedContexts) {
			if (csContext != null) {
				org.eclipse.ocl.pivot.Package modelPackage = getReferredPackage(csContext);
				if (modelPackage != null) {
					modelPackages.add(modelPackage);
				}
			}
		}
		List<org.eclipse.ocl.pivot.Package> sortedModelPackages = new ArrayList<org.eclipse.ocl.pivot.Package>(modelPackages);
		Collections.sort(sortedModelPackages, NameUtil.NAMEABLE_COMPARATOR);
		List<org.eclipse.ocl.pivot.Package> contextPackages = new ArrayList<org.eclipse.ocl.pivot.Package>();
		for (org.eclipse.ocl.pivot.Package modelPackage : sortedModelPackages) {
			if (modelPackage != null) {
				List<PackageDeclarationCS> selectedPackageDecls = new ArrayList<PackageDeclarationCS>();
				for (PackageDeclarationCS csPackage : ownedPackages) {
					org.eclipse.ocl.pivot.Package asPackage = csPackage.getReferredPackage();
					if (modelPackage == asPackage) {
						selectedPackageDecls.add(csPackage);
					}
				}
				List<ContextDeclCS> selectedContexts = new ArrayList<ContextDeclCS>();
				for (ContextDeclCS csContext : ownedContexts) {
					if (csContext != null) {
						org.eclipse.ocl.pivot.Package asPackage = getReferredPackage(csContext);
						if (modelPackage == asPackage) {
							selectedContexts.add(csContext);
						}
					}
				}
				org.eclipse.ocl.pivot.Package contextPackage = contextPackage(modelPackage, selectedPackageDecls, selectedContexts);
				contextPackages.add(contextPackage);
			}
		}
		@NonNull Model contextRoot = refreshRoot(Model.class, PivotPackage.Literals.MODEL, csElement);
		context.refreshPivotList(Import.class, contextRoot.getOwnedImports(), csElement.getOwnedImports());
		context.refreshList(contextRoot.getOwnedPackages(), contextPackages);
		return null;
	}

	@Override
	public Continuation<?> visitDefOperationCS(@NonNull DefOperationCS csElement) {
		@NonNull Operation contextOperation = refreshNamedElement(Operation.class, PivotPackage.Literals.OPERATION, csElement);
		context.refreshPivotList(Parameter.class, contextOperation.getOwnedParameters(), csElement.getOwnedParameters());
		ExpressionInOCL pivotSpecification = PivotUtil.getPivot(ExpressionInOCL.class, csElement.getOwnedSpecification());
		contextOperation.setBodyExpression(pivotSpecification);
		return null;
	}

	@Override
	public Continuation<?> visitDefPropertyCS(@NonNull DefPropertyCS csElement) {
		@NonNull Property contextProperty = refreshNamedElement(Property.class, PivotPackage.Literals.PROPERTY, csElement);
		contextProperty.setIsDerived(true);
		contextProperty.setIsReadOnly(true);
		contextProperty.setIsTransient(true);
		contextProperty.setIsVolatile(true);
		contextProperty.setIsResolveProxies(false);
		ExpressionInOCL pivotSpecification = PivotUtil.getPivot(ExpressionInOCL.class, csElement.getOwnedSpecification());
		contextProperty.setOwnedExpression(pivotSpecification);
		return null;
	}

	@Override
	public Continuation<?> visitOperationContextDeclCS(@NonNull OperationContextDeclCS csElement) {
		List<ParameterCS> csParameters = csElement.getOwnedParameters();
		assert csParameters != null;
		PathNameCS pathName = csElement.getOwnedPathName();
		assert pathName != null;
		CS2AS.setElementType(pathName, PivotPackage.Literals.OPERATION, csElement, new OperationDeclScopeFilter(null, csParameters));
		return null;
	}

	@Override
	public Continuation<?> visitPackageDeclarationCS(@NonNull PackageDeclarationCS csElement) {
		return null;
	}

	@Override
	public Continuation<?> visitPropertyContextDeclCS(@NonNull PropertyContextDeclCS csElement) {
		return null;
	}
}
