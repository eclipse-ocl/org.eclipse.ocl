/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.OrphanageImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.resource.ASSaverNew;
import org.eclipse.ocl.pivot.internal.resource.ASSaverNew.ASSaverWithInverse;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;

@SuppressWarnings("all")
public abstract class GenerateOCLCommon extends GenerateMetamodelWorkflowComponent
{
	protected final @NonNull Map<@NonNull NamedElement, @NonNull String> external2name = new HashMap<>();
	protected final @NonNull Map<@NonNull String, @NonNull NamedElement> name2external = new HashMap<>();
	protected final @NonNull Map<@NonNull String, @NonNull String> generatedClassNameMap = new HashMap<>();
	protected EnvironmentFactoryInternal environmentFactory;
	protected PivotMetamodelManager metamodelManager;
	protected NameQueries nameQueries;
	protected Model thisModel;
	private List<@NonNull Element> orphans;
	protected ContentAnalysis contentAnalysis;
	protected /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames;

	protected final @NonNull Comparator<@NonNull Comment> commentComparator = new Comparator<@NonNull Comment>()
	{
		@Override
		public int compare(@NonNull Comment o1, @NonNull Comment o2) {
			String m1 = o1.getBody();
			String m2 = o2.getBody();
			return m1.compareTo(m2);
		}
	};

	protected final @NonNull Comparator<@NonNull NamedElement> externalComparator = new Comparator<@NonNull NamedElement>()
	{
		@Override
		public int compare(@NonNull NamedElement o1, @NonNull NamedElement o2) {
			int d1 = depth(o1);
			int d2 = depth(o2);
			int diff = d1 - d2;
			if (diff != 0) {
				return diff;
			}
			String m1 = external2name.get(o1);
			String m2 = external2name.get(o2);
			assert (m1 != null) && (m2 != null);
			return m1.compareTo(m2);
		}

		private int depth(EObject o) {
			EObject eContainer = o.eContainer();
			if (eContainer != null) {
				return depth(eContainer) + 1;
			}
			return 0;
		}
	};

	protected final @NonNull Comparator<@NonNull EObject> symbolNameComparator = new Comparator<@NonNull EObject>()
	{
		@Override
		public int compare(@NonNull EObject o1, @NonNull EObject o2) {
			String m1 = getSymbolName(o1);
			String m2 = getSymbolName(o2);
			return m1.compareTo(m2);
		}
	};

	protected void addExternalReference(@Nullable NamedElement reference, @NonNull Model root) {
		if (reference == null) {
			return;
		}
		Model containingModel = PivotUtil.getContainingModel(reference);
		if ((containingModel == root) || external2name.containsKey(reference) || OrphanageImpl.isOrphanage(containingModel)) {
			return;
		}
		if (reference instanceof Model) {
			return;
		}
		if (contentAnalysis.internalClasses.contains(reference)) {
			return;
		}
		//		boolean hasComplements = false;
		if (reference instanceof Type) {
			assert !"Annotation".equals(reference.getName());
			//			hasComplements = hasComplements((Type) reference);
		/*	The following now loads existing classes when we are generating new ones for GeneratePivotModel.
		 * EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.findEnvironmentFactory(reference);
			//			assert environmentFactory == this.environmentFactory;
			if (environmentFactory != null) {	// FIXME this conveniently does not relocate the built-in PrimitiveTypes
				CompleteClassInternal completeClass = environmentFactory.getMetamodelManager().getCompleteClass((Type)reference);
				for (Type partialType : completeClass.getPartialClasses())  {
					Model containingModel2 = PivotUtil.getContainingModel(partialType);
					if (containingModel2 == root) {
						return;
					}
					if (containingModel2 == null) {		// Orphanage
						return;
					}
				}
				reference = completeClass.getPrimaryClass();
			} */
		}
		else if (!(reference instanceof org.eclipse.ocl.pivot.Package)) {
			reference = metamodelManager.getPrimaryElement(reference);
		}
		if (external2name.containsKey(reference)) {
			return;
		}
		EObject eContainer = reference.eContainer();
		String name;
		if (reference instanceof TemplateParameter) {
			TemplateParameter templateParameter = (TemplateParameter)reference;
			TemplateSignature owningSignature = templateParameter.getOwningSignature();
			TemplateableElement owningElement = owningSignature != null ? owningSignature.getOwningElement() : null;
			if (owningElement instanceof NamedElement) {
				name = "_" + ((NamedElement)owningElement).getName() + "_" + templateParameter.getName();
			}
			else {
				name = "_" + templateParameter.getName();
			}
		}
		else if (reference instanceof Model) {
			name = "_" + reference.getName().toLowerCase();
		}
		else if (reference instanceof NamedElement) {
			name = "_" + reference.getName();
		}
		else {
			name = "X_" + name2external.size();
		}
		if (name2external.containsKey(name)) {
			if (reference instanceof PrimitiveType) {
				return;
			}
			for (int i = 0; true; i++) {
				String suffixedName = name + "_" + i;
				if (!name2external.containsKey(suffixedName)) {
					name = suffixedName;
					break;
				}
			}
		}
	//	System.out.println("addExternalReference: " + name + " <=> "+ NameUtil.debugSimpleName(reference) + " : " + reference);		// XXX
		//		if (!hasComplements) {
		name2external.put(name, reference);
		//		}
		external2name.put(reference, name);
		if ((getGeneratedClassName(reference) == null) && (eContainer instanceof NamedElement)) {
			addExternalReference((NamedElement)eContainer, root);
		}
	}

	public void addGeneratedClassNameMap(Mapping mapping) {
		generatedClassNameMap.put(mapping.getFrom(), mapping.getTo());
	}

	protected @NonNull ContentAnalysis createContentAnalysis(@NonNull Model thisModel) {
		return new ContentAnalysis(this, thisModel);
	}

	protected String declarePackageImport(org.eclipse.ocl.pivot.@NonNull Package elem) {
		//		String generatedClassName = getGeneratedClassName(elem);
		//		if (generatedClassName != null) {
		//			return null;//"import " + generatedClassName + ";";
		//		}
		String ecoreQualifiedPackageInterfaceName = nameQueries.getEcoreQualifiedPackageInterfaceName(elem);
		if (ecoreQualifiedPackageInterfaceName != null) {
			return "import " + ecoreQualifiedPackageInterfaceName + ";";
		}
		return null;
	}

	protected org.eclipse.ocl.pivot.Package findPackage(Iterable<org.eclipse.ocl.pivot.Package> packages) {
		for (org.eclipse.ocl.pivot.Package pkg : packages) {
			if (!PivotConstants.ORPHANAGE_NAME.equals(pkg.getName())) {
				return pkg;
			}
		}
		return null;
	}

	protected String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Class elem) {
		return nameQueries.getEcoreLiteral(elem);
	}

	protected String getEcoreLiteral(@NonNull EnumerationLiteral elem) {
		return nameQueries.getEcoreLiteral(elem);
	}

	protected String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Package elem) {
		return nameQueries.getEcoreLiteral(elem);
	}

	protected @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		assert environmentFactory != null;
		return environmentFactory;
	}

	protected String getExternalReference(@NonNull Element element) {
		EObject eContainer = element.eContainer();
		if (eContainer == null) {
			if (element instanceof Model) {
				return ((Model)element).eResource().getClass().getName() + ".getDefaultModel()";
			}
		}
		else {
			String generatedClassName = getGeneratedClassName(element);
			if (generatedClassName != null) {
				return "getPackage(" + generatedClassName + ".getDefaultModel(), \"" + ((NamedElement)element).getName() + "\")";
			}
			if ((element instanceof TemplateParameter) && (eContainer instanceof TemplateSignature)) {
				TemplateSignature templateSignature = (TemplateSignature)eContainer;
				TemplateableElement templateableElement = templateSignature.getOwningElement();
				if (templateableElement != null) {
					return "get" + element.eClass().getName() + "(" + getSymbolName(eContainer.eContainer()) + ", " + templateSignature.getOwnedParameters().indexOf(element) + ")";
				}
			}
			if (element instanceof CollectionType) {
				return "getCollectionType(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (element instanceof AnyType) {
				return "getClass(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (element instanceof InvalidType) {
				return "getClass(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (element instanceof PrimitiveType) {
				return "getClass(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (element instanceof SelfType) {
				return "getClass(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (element instanceof VoidType) {
				return "getClass(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else if (eContainer instanceof NamedElement) {
				return "get" + element.eClass().getName() + "(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else {
				return "get" + element.eClass().getName() + "(" + getSymbolName(eContainer) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
		}
		return "\"" + EcoreUtil.getURI(element).toString() + "\"";
	}

	protected @Nullable String getGeneratedClassName(EObject eObject) {
		return (eObject instanceof org.eclipse.ocl.pivot.Package) ? generatedClassNameMap.get(((org.eclipse.ocl.pivot.Package)eObject).getURI()) : null;
	}

	protected @NonNull String getGeneratedPackageId(org.eclipse.ocl.pivot.@NonNull Package pkge) {
		PackageId basicPackageId = ((PackageImpl)pkge).basicGetPackageId();
		return basicPackageId == IdManager.METAMODEL ? "IdManager.METAMODEL" : "null";
	}


	protected @NonNull String getNameLiteral(@NonNull Operation operation) {
		return '"' + operation.getName() + '"';
	}

	protected @NonNull String getNameLiteral(@NonNull Property property) {
		return '"' + property.getName() + '"';
	}

	protected org.eclipse.ocl.pivot.@Nullable Package getOrphanPackage(org.eclipse.ocl.pivot.@NonNull Package elem) {
		return getOrphanPackage(getRootPackage(elem));
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getOrphanPackage(@NonNull Model elem) {
		for (org.eclipse.ocl.pivot.@NonNull Package pkg : PivotUtil.getOwnedPackages(elem)) {
			if (PivotConstants.ORPHANAGE_NAME.equals(pkg.getName())) {
				return pkg;
			}
		}
		throw new IllegalStateException();
	}

	protected @NonNull String getPartialName(@NonNull Property property) {
		org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
		if (owningType == null) {
			return "null_" + javaName(property);
		}
		String simpleName = partialName(owningType) + "_" + javaName(property);
		if (!property.isIsImplicit()) {
			return simpleName;
		}
		Property opposite = property.getOpposite();
		if (opposite == null) {
			return simpleName;
		}
		else {
			return simpleName + "_" + javaName(opposite);
		}
	}

	protected @NonNull String getPrefixedSymbolName(@NonNull EObject elem, @NonNull String prefix) {
		return nameQueries.getPrefixedSymbolName(prefix.replace(".",  "_"), elem);
	}

	protected @NonNull String getPrefixedSymbolNameWithoutNormalization(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull String prefix) {
		CompleteClass completeClass = metamodelManager.getCompleteClass(type);
		org.eclipse.ocl.pivot.@NonNull Class primaryType = completeClass.getPrimaryClass();
		String normalizedSymbol = nameQueries.basicGetSymbolName(completeClass);
		if ((type == primaryType) && (normalizedSymbol != null)) {
			return normalizedSymbol;
		}
		String localSymbolName = nameQueries.getPrefixedSymbolNameWithoutNormalization(prefix.replace(".",  "_"), type);
		if (normalizedSymbol == null) {
			nameQueries.putSymbolName(completeClass, localSymbolName);
		}
		return localSymbolName;
	}

	protected @NonNull Model getRootPackage(org.eclipse.ocl.pivot.@Nullable Package elem) {
		EObject eObject = elem;
		while (eObject != null) {
			if (eObject instanceof Model) {
				return (Model)eObject;
			}
			eObject = eObject.eContainer();
		}
		throw new IllegalStateException("Missing Root");
	}

	protected String getSignature(@NonNull NamedElement elem) {
		EObject parent = elem.eContainer();
		if (parent != null) {
			return getSignature((NamedElement)parent) + "::" + elem.getName();
		} else {
			return elem.getName();
		}
	}

	protected @NonNull String getSignature(@NonNull Operation elem) {
		EObject parent = elem.eContainer();
		if (parent != null) {
			return getSignature((NamedElement)parent) + "::" + elem.getName() + "()";
		} else {
			return elem.getName() + "()";
		}
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> getSortedClassTypes(@NonNull Model root) {
		return contentAnalysis.package2sortedClasses;
	}

	protected @NonNull List<@NonNull Comment> getSortedComments(@NonNull Element element) {
		List<Comment> sortedElements = new ArrayList<>(element.getOwnedComments());
		Collections.sort(sortedElements, commentComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Enumeration>> getSortedEnumerations(@NonNull Model root) {
		return contentAnalysis.package2sortedEnumerations;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getSortedExternalPackages(@NonNull Model root) {
		List<org.eclipse.ocl.pivot.@NonNull Package> externalPackages = new ArrayList<>();
		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : root.getOwnedPackages()) {
			if (!OrphanageImpl.isOrphanage(asPackage)) {
				externalPackages.add(asPackage);
			}
		}
		for (Import asImport : root.getOwnedImports()) {
			Namespace importedNamespace = asImport.getImportedNamespace();
			org.eclipse.ocl.pivot.Package externalPackage = PivotUtil.getContainingPackage(importedNamespace);
			if (externalPackage != null) {
				if (!externalPackages.contains(externalPackage)) {
					externalPackages.add(externalPackage);
				}
			}
		}
		for (Element element : name2external.values()) {
			org.eclipse.ocl.pivot.Package externalPackage = PivotUtil.getContainingPackage(element);
			if (externalPackage != null) {
				if (!externalPackages.contains(externalPackage)) {
					externalPackages.add(externalPackage);
				}
			}
		}
		if (externalPackages.size() > 1) {
			Collections.sort(externalPackages, contentAnalysis.packageComparator);
		}
		return externalPackages;
	}

	protected @NonNull List<String> getSortedExternals(@NonNull Model root) {
		List<NamedElement> sortedExternals = new ArrayList<>(name2external.values());
		Collections.sort(sortedExternals, externalComparator);
		List<String> sortedExternalNames = new ArrayList<>(sortedExternals.size());
		for (NamedElement sortedExternal : sortedExternals) {
			sortedExternalNames.add(external2name.get(sortedExternal));
		}
		return sortedExternalNames;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package,String> getSortedImports(@NonNull Model model) {
		Map<org.eclipse.ocl.pivot.@NonNull Package,String> import2alias = new HashMap<>();
		for (Import asImport : model.getOwnedImports()) {
			Namespace importedNamespace = asImport.getImportedNamespace();
			if (importedNamespace instanceof org.eclipse.ocl.pivot.Package) {
				import2alias.put((org.eclipse.ocl.pivot.Package)importedNamespace, asImport.getName());
			}
		}
		for (Map.Entry<@NonNull String, @NonNull NamedElement> entry : name2external.entrySet()) {
			NamedElement value = entry.getValue();
			if ((value instanceof Library) && !import2alias.containsKey(value)) {
				import2alias.put((Library)value, null);
			}
		}
		return import2alias;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Iteration>> getSortedIterations(@NonNull Model root) {
		return contentAnalysis.package2sortedIterations;
	}

	protected @NonNull List<@NonNull Library> getSortedLibraries(@NonNull Model root) {
		return contentAnalysis.sortedLibraries;
	}

	protected @NonNull List<@NonNull Library> getSortedLibrariesWithPrecedence(@NonNull Model root) {
		return contentAnalysis.sortedLibrariesWithPrecedence;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Operation>> getSortedOperations(@NonNull Model root) {
		return contentAnalysis.package2sortedOperations;
	}

	protected @NonNull List<@NonNull Operation> getSortedOperationsWithPrecedence(@NonNull Model root) {
		return contentAnalysis.sortedOperationsWithPrecedence;
	}

/*	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSortedOwningTypes(@NonNull List<@NonNull ? extends Operation> operations) {
		Set<org.eclipse.ocl.pivot.@NonNull Class> allElements = new HashSet<>();
		for (@NonNull Operation operation : operations) {
			if (operation.getOwningClass() != null) {
				allElements.add(operation.getOwningClass());
			}
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, contentAnalysis.monikerComparator);
		return sortedElements;
	} */

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getSortedPackages(@NonNull Model root, @NonNull Collection<? extends org.eclipse.ocl.pivot.Package> packages) {
		List<org.eclipse.ocl.pivot.@NonNull Package> sortedElements = new ArrayList<>(packages);
		Collections.sort(sortedElements, contentAnalysis.packageComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getSortedPackages(@NonNull Model root) {
		return contentAnalysis.sortedPackages;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getSortedPackages(org.eclipse.ocl.pivot.@NonNull Package pkg) {
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(pkg.getOwnedPackages());
		Collections.sort(sortedElements, NameUtil.NAMEABLE_COMPARATOR);
		return sortedElements;
	}

	protected @NonNull List<@NonNull Precedence> getSortedPrecedences(@NonNull Library library) {
		List<@NonNull Precedence> sortedElements = new ArrayList<>(library.getOwnedPrecedences());
		Collections.sort(sortedElements, NameUtil.NAMEABLE_COMPARATOR);
		return sortedElements;
	}

/*	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull PrimitiveType>> getSortedPrimitiveTypes(@NonNull Model root) {
		return contentAnalysis.package2sortedPrimitiveTypes;
	} */

	protected @NonNull List<@NonNull Property> getSortedProperties(@NonNull Model root) {
		return contentAnalysis.sortedProperties;
	}

	protected @NonNull List<@NonNull Property> getSortedProperties(org.eclipse.ocl.pivot.@NonNull Class type) {
		List<@NonNull Property> sortedElements = new ArrayList<>(type.getOwnedProperties());
		Collections.sort(sortedElements, OCLinEcoreTablesUtils.propertyComparator);
		return sortedElements;
	}

/*	protected @NonNull List<@NonNull TemplateParameter> getSortedTemplateParameters(@NonNull Model root) {
		return contentAnalysis.sortedTemplateParameters;
	} */

	protected @NonNull List<@NonNull Property> getSortedTupleParts(@NonNull TupleType tupleType) {
		List<@NonNull Property> sortedElements = Lists.newArrayList(PivotUtil.getOwnedProperties(tupleType));
		Collections.sort(sortedElements, NameUtil.NAMEABLE_COMPARATOR);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSuperclassesInPackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {		// Redundant filter
		if ("BooleanType".equals(asClass.toString())) {
			getClass();		// XXX
		}
		StringBuilder s = new StringBuilder();
		List<org.eclipse.ocl.pivot.@NonNull Class> allElements = new ArrayList<>();
		s.append("getSuperclassesInPackage " + NameUtil.debugSimpleName(asClass) + " " + asClass);
		for (org.eclipse.ocl.pivot.@NonNull Class superclass : asClass.getSuperClasses()) {
		//	if (getRootPackage(superclass.getOwningPackage()) == getRootPackage(type.getOwningPackage())) {
				allElements.add(superclass);
				s.append("\n\t" + NameUtil.debugSimpleName(superclass) + " " + superclass);
		//	}
		}
	//	System.out.println(s.toString());
		return allElements;
	}

	protected @NonNull List<@NonNull TemplateParameterSubstitution> getTemplateParameterSubstitutions(@NonNull TemplateableElement element) {
		List<@NonNull TemplateParameterSubstitution> allElements = new ArrayList<>();
		for (TemplateBinding templateBinding : element.getOwnedBindings()) {
			allElements.addAll(templateBinding.getOwnedSubstitutions());
		}
		return allElements;
	}

	protected String getSymbolName(@NonNull EObject elem) {
		String name = nameQueries.basicGetSymbolName(elem);
		if (name != null) {
			return name;
		}
		EObject primaryElement;
		if (!(elem instanceof org.eclipse.ocl.pivot.Package)) {
			primaryElement = metamodelManager.getPrimaryElement(elem);
		}
		else {
			primaryElement = elem;
		}
		name = external2name.get(primaryElement);
		if (name != null) {
			return name;
		}
		Model thatModel = PivotUtil.getContainingModel(primaryElement);
		if (getThisModel() == thatModel) {
			return nameQueries.getSymbolName(primaryElement);
		}
		return nameQueries.getSymbolName(primaryElement);
		//		throw new IllegalStateException("No external name defined for " + EcoreUtil.getURI(elem));
	}

	protected String getSymbolNameWithoutNormalization(@NonNull EObject elem) {
		String name = external2name.get(elem);
		if (name != null) {
			return name;
		}
		Model thatModel = PivotUtil.getContainingModel(elem);
		if (getThisModel() == thatModel) {
			return nameQueries.getSymbolNameWithoutNormalization(elem);
		}
		return nameQueries.getSymbolNameWithoutNormalization(elem);
		//		throw new IllegalStateException("No external name defined for " + EcoreUtil.getURI(elem));
	}

	protected abstract @NonNull Model getThisModel();

	protected boolean hasComplements(@NonNull Type type) {
		if (type instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)type;
			org.eclipse.ocl.pivot.Class asPrimaryClass = metamodelManager.getPrimaryElement(asClass);
			if ((asClass != asPrimaryClass) && (!asClass.getOwnedOperations().isEmpty() || !asClass.getOwnedProperties().isEmpty())) {
				return true;
			}
		}
		return false;
	}

	protected void initLocalTypes() {
		contentAnalysis.analyze(thisModel);
	}

	public void initModel1(@NonNull Model thisModel) {
		this.thisModel = thisModel;
		this.contentAnalysis = createContentAnalysis(thisModel);
	//	initLocalTypes();
	//	initOrphanSymbolNames(asSaver);
	}

	public void initModel2(ASSaverNew.@NonNull ASSaverWithInverse asSaver) {
	//	this.thisModel = thisModel;
	//	this.contentAnalysis = createContentAnalysis(thisModel);
		initLocalTypes();
		initOrphanSymbolNames(asSaver);
	}

	/**
	 * Assign a unique symbol name for each localized orphan and assign the same symbol name to the shared orphan#
	 * from which the local was cloned so that synthesis of references to the shared element are serialized as if the
	 * local copy had been corrupted to point at the local.
	 */
	protected void initOrphanSymbolNames(@NonNull ASSaverWithInverse asSaver) {
		org.eclipse.ocl.pivot.Package localOrphanage = getOrphanPackage(thisModel);
		for (EObject localOrphan : new TreeIterable(localOrphanage, true)) {
			StringBuilder s = new StringBuilder();
			if (localOrphan instanceof CollectionType) {
				CollectionType type = (CollectionType)localOrphan;
				s.append("_" + type.getName());
				s.append("_" + partialName(type.getElementType()));
				s.append("_" + (type.isIsNullFree() ? "T" : "F"));
				if (type.getLowerValue() != ValueUtil.ZERO_VALUE) {
					s.append("_L" + type.getLowerValue());
				}
				if (type.getUpperValue() != ValueUtil.UNLIMITED_VALUE) {
					s.append("_U" + type.getUpperValue());
				}
			}
			else if (localOrphan instanceof LambdaType) {
				LambdaType type = (LambdaType)localOrphan;
				s.append("_" + type.getName());
				s.append("_" + partialName(type.getContextType()));
				for (Type parameterType : type.getParameterTypes()) {
					s.append("_" + partialName(parameterType));
				}
				s.append("_" + partialName(type.getResultType()));
			}
			else if (localOrphan instanceof MapType) {
				MapType type = (MapType)localOrphan;
				s.append("_" + type.getName());
				s.append("_" + partialName(type.getKeyType()));
				s.append("_" + (type.isKeysAreNullFree() ? "T" : "F"));
				s.append("_" + partialName(type.getValueType()));
				s.append("_" + (type.isValuesAreNullFree() ? "T" : "F"));
			}
			else if (localOrphan instanceof Property) {
				s.append("_" + partialName(localOrphan));
			}
		//	else if (localOrphan instanceof TemplateParameter) {
		//		TemplateParameter type = (TemplateParameter)localOrphan;
		//		s.append("tp_" + type.getTemplateParameterId().getIndex());
		//	}
			else if (localOrphan instanceof TupleType) {
				s.append("_" + partialName(localOrphan));
			}
			else if (localOrphan instanceof TemplateBinding) {
			}
			else if (localOrphan instanceof TemplateParameterSubstitution) {
			}
			else if ((localOrphan instanceof org.eclipse.ocl.pivot.Class) && OrphanageImpl.isOrphan((org.eclipse.ocl.pivot.Class)localOrphan)) {
				s.append("orphanClass");
			}
			else if ((localOrphan instanceof org.eclipse.ocl.pivot.Package) && OrphanageImpl.isOrphan((org.eclipse.ocl.pivot.Package)localOrphan)) {		// XXX
				s.append("orphanage");			// XXXX
			}
			else {
				System.out.println("Unexpected localOrphan: " + NameUtil.debugSimpleName(localOrphan));
				s.append("_" + partialName(localOrphan));
			}
			if (s.length() > 0) {
				String symbolName = getPrefixedSymbolName(localOrphan, s.toString());
				EObject sharedOrphan = asSaver.basicGetSource(localOrphan);
				if (sharedOrphan != null) {
					nameQueries.putSymbolName(sharedOrphan, symbolName);
				}
				else if (localOrphan == localOrphanage) {
				//	Orphanage sharedOrphanage = /*OrphanageImpl.getOrphanage(*/environmentFactory.getCompleteModel().getSharedOrphanage()/*)*/;
				//	nameQueries.putSymbolName(sharedOrphanage, symbolName);
				}
				else {
					System.out.println("Missing orphan mapping for " + NameUtil.debugSimpleName(localOrphan) + " : " + localOrphan);
				}
			}
		}
	}

	protected Boolean isEcoreConstraint(@NonNull Operation operation) {
		for (Parameter p : operation.getOwnedParameters()) {
			if (p.getName().equals("diagnostics") && p.getType().getName().equals("EDiagnosticChain")) {
				return true;
			}
		}
		return false;
	}

	protected @NonNull String javaName(@NonNull NamedElement element) {
		return NameQueries.rawEncodeName(element.getName(), 0);
	}

	protected @NonNull String javaName(@Nullable Object element, @NonNull String string) {
		return NameQueries.rawEncodeName(string, 0);
	}

	protected @NonNull String javaString(@NonNull Comment aComment) {
		return Strings.convertToJavaString(aComment.getBody().trim());
	}

	protected @NonNull String javaString(@NonNull LanguageExpression anExpression) {
		return Strings.convertToJavaString(anExpression.getBody().trim());
	}

	protected abstract /*@NonNull*/ String partialName(EObject element);

	protected void setEnvironmentFactory(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
		nameQueries = new NameQueries(metamodelManager);
	}

/*	protected @NonNull Iterable<@NonNull Property> sortedOpposites() {
		List<@NonNull Property> withBothOpposites = new ArrayList<>();
		for (List<@NonNull Property> properties : getSortedProperties(thisModel).values()) {
			for (@NonNull Property property : properties) {
				if (property.getOpposite() != null) {
					withBothOpposites.add(property);
				}
			}
		}
		Collections.sort(withBothOpposites, contentAnalysis.monikerComparator);
		Set<@NonNull Property> withOneOppositeSet = new HashSet<>();
		List<@NonNull Property> withOneOpposites = new ArrayList<>();
		for (@NonNull Property property : withBothOpposites) {
			if (!withOneOpposites.contains(property.getOpposite()) && withOneOppositeSet.add(property)) {
				withOneOpposites.add(property);
			}
		}
		return withOneOpposites;
	} */

	protected @NonNull Iterable<@NonNull Property> sortedOpposites(@NonNull List<Property> properties) {
		ArrayList<@NonNull Property> withOpposites = new ArrayList<>();
		for (@NonNull Property property : properties) {
			Property opposite = property.getOpposite();
			if (opposite != null) {
				if (!withOpposites.contains(property) && !withOpposites.contains(opposite)) {
					withOpposites.add(property);
				//	withOpposites.add(opposite);
				}
			}
		}
		return withOpposites;
	}
}
