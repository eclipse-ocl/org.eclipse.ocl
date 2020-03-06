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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.xtext.util.Strings;

@SuppressWarnings("all")
public abstract class GenerateOCLCommon extends GenerateMetamodelWorkflowComponent
{
	private final @NonNull Map<Element, String> element2moniker = new HashMap<>();
	private final @NonNull Map<NamedElement, @NonNull String> external2name = new HashMap<>();
	protected final @NonNull Map<String, NamedElement> name2external = new HashMap<>();
	protected final @NonNull Map<String, String> generatedClassNameMap = new HashMap<>();
	protected EnvironmentFactoryInternal environmentFactory;
	protected PivotMetamodelManager metamodelManager;
	protected NameQueries nameQueries;

	public static final @NonNull Comparator<@NonNull Property> classPropertyComparator = new Comparator<@NonNull Property>()
	{
		@Override
		public int compare(@NonNull Property p1, @NonNull Property p2) {
			String c1 = String.valueOf(p1.getOwningClass().getName());
			String c2 = String.valueOf(p2.getOwningClass().getName());
			int diff = c1.compareTo(c2);
			if (diff != 0) {
				return diff;
			}
			boolean b1 = p1.isIsImplicit();
			boolean b2 = p2.isIsImplicit();
			if (b1 != b2) {
				return b1 ? 1 : -1;
			}
			String n1 = String.valueOf(p1.getName());
			String n2 = String.valueOf(p2.getName());
			diff = n1.compareTo(n2);
			if (diff != 0) {
				return diff;
			}
			Property o1 = p1.getOpposite();
			Property o2 = p2.getOpposite();
			if (o1 == null) {
				if (o2 == null) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				if (o2 == null) {
					return -1;
				}
				else {
					n1 = String.valueOf(o1.getName());
					n2 = String.valueOf(o2.getName());
					return n1.compareTo(n2);
				}
			}
		}
	};

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

	protected final @NonNull Comparator<@NonNull Element> monikerComparator = new Comparator<@NonNull Element>()
	{
		@Override
		public int compare(@NonNull Element o1, @NonNull Element o2) {
			String m1 = getMoniker(o1);
			String m2 = getMoniker(o2);
			return m1.compareTo(m2);
		}
	};

	protected final @NonNull Comparator<@NonNull Nameable> nameableComparator = new Comparator<@NonNull Nameable>()
	{
		@Override
		public int compare(@NonNull Nameable o1, @NonNull Nameable o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			return m1.compareTo(m2);
		}
	};

	protected final @NonNull Comparator<@NonNull Package> packageComparator = new Comparator<@NonNull Package>()
	{
		@Override
		public int compare(@NonNull Package o1, @NonNull Package o2) {
			String m1 = o1.getName();
			String m2 = o2.getName();
			if (PivotConstants.ORPHANAGE_NAME.equals(m1)) {
				if (PivotConstants.ORPHANAGE_NAME.equals(m2)) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else{
				if (PivotConstants.ORPHANAGE_NAME.equals(m2)) {
					return -1;
				}
				else {
					return m1.compareTo(m2);
				}
			}
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
		if ((containingModel == root) || external2name.containsKey(reference)) {
			return;
		}
		if (reference instanceof Model) {
			return;
		}
		//		boolean hasComplements = false;
		if (reference instanceof Type) {
			//			hasComplements = hasComplements((Type) reference);
			EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.findEnvironmentFactory(reference);
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
			}
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

	protected String declarePackageImport(@NonNull Package elem) {
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
			if (!"$$".equals(pkg.getName())) {
				return pkg;
			}
		}
		return null;
	}

	protected @NonNull Set<CollectionType> getAllCollectionTypes(@NonNull Model root) {
		Set<CollectionType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof CollectionType) {
				allElements.add((CollectionType)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<Enumeration> getAllEnumerations(@NonNull Model root) {
		Set<Enumeration> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Enumeration) {
				allElements.add((Enumeration)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<LambdaType> getAllLambdaTypes(@NonNull Model root) {
		Set<LambdaType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof LambdaType) {
				allElements.add((LambdaType)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<MapType> getAllMapTypes(@NonNull Model root) {
		Set<MapType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof MapType) {
				allElements.add((MapType)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Package> getAllPackages(@NonNull Model root) {
		Set<org.eclipse.ocl.pivot.Package> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				allElements.add((org.eclipse.ocl.pivot.Package)eObject);
			}
		}
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull Set<Precedence> getAllPrecedences(@NonNull Model root) {
		Set<Precedence> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Precedence) {
				allElements.add((Precedence)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<PrimitiveType> getAllPrimitiveTypes(@NonNull Model root) {
		Set<PrimitiveType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof PrimitiveType) {
				allElements.add((PrimitiveType)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull List<Property> getAllProperties(@NonNull Model root) {
		List<Property> allElements = new ArrayList<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if ((eObject instanceof Property) && !(eObject.eContainer() instanceof TupleType) &&
					(((Property)eObject).getOwningClass() != null)) {
				allElements.add((Property)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<TemplateBinding> getAllTemplateBindings(@NonNull Model root) {
		Set<TemplateBinding> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TemplateBinding) {
				allElements.add((TemplateBinding)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<TemplateSignature> getAllTemplateSignatures(@NonNull Model root) {
		Set<TemplateSignature> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TemplateSignature) {
				allElements.add((TemplateSignature)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<TupleType> getAllTupleTypes(@NonNull Model root) {
		Set<TupleType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TupleType) {
				allElements.add((TupleType)eObject);
			}
		}
		return allElements;
	}

	protected @NonNull Set<org.eclipse.ocl.pivot.Class> getAllTypes(@NonNull Model root) {
		Set<org.eclipse.ocl.pivot.Class> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.ocl.pivot.Class) {
				allElements.add((org.eclipse.ocl.pivot.Class)eObject);
			}
		}
		return allElements;
	}

	protected @Nullable List<org.eclipse.ocl.pivot.Class> getClassTypes(@NonNull Package pkge) {
		List<org.eclipse.ocl.pivot.Class> classTypes = null;
		for (org.eclipse.ocl.pivot.Class type : pkge.getOwnedClasses()) {
			boolean useIt = false;
			if (type instanceof CollectionType) {
				useIt = false;
			}
			else if (type instanceof Enumeration) {
				useIt = false;
			}
			else if (type instanceof LambdaType) {
				useIt = false;
			}
			else if (type instanceof MapType) {
				useIt = false;
			}
			else if (type instanceof PrimitiveType) {
				useIt = false;
			}
			else if (type instanceof TupleType) {
				useIt = false;
			}
			else if (type.isTemplateParameter() != null) {
				useIt = false;
			}
			else {
				useIt = true;
			}
			if (useIt) {
				if (classTypes == null) {
					classTypes = new ArrayList<>();
				}
				classTypes.add(type);
			}
		}
		return classTypes;
	}

	protected String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Class elem) {
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
			if (eContainer instanceof NamedElement) {
				return "get" + element.eClass().getName() + "(" + getPrefixedSymbolName(eContainer, ((NamedElement)eContainer).getName()) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
			else {
				return "get" + element.eClass().getName() + "(" + getSymbolName(eContainer) + ", \"" + ((NamedElement)element).getName() + "\")";
			}
		}
		return "\"" + EcoreUtil.getURI(element).toString() + "\"";
	}

	protected void getExternals(@NonNull Model root) {
		Set<org.eclipse.ocl.pivot.Class> internalTypes = new HashSet<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<org.eclipse.ocl.pivot.Class> classTypes = getClassTypes(pkge);
			if (classTypes != null) {
				for (org.eclipse.ocl.pivot.Class classType : classTypes) {
					for (org.eclipse.ocl.pivot.Class  partialClass : metamodelManager.getCompleteClass(classType).getPartialClasses()) {
						internalTypes.add(partialClass);
					}
				}
			}
		}
		Set<Element> allReferences = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof CollectionType) {
				CollectionType collectionType = (CollectionType)eObject;
				addExternalReference(collectionType, root);
				TemplateableElement unspecializedElement = collectionType.getUnspecializedElement();
				if (unspecializedElement instanceof NamedElement) {
					addExternalReference((NamedElement) unspecializedElement, root);
				}
			}
			else if (eObject instanceof TemplateParameterSubstitution) {
				TemplateParameterSubstitution templateParameterSubstitution = (TemplateParameterSubstitution)eObject;
				addExternalReference(templateParameterSubstitution.getActual(), root);
				addExternalReference(templateParameterSubstitution.getFormal(), root);
			}
			else if (eObject instanceof Import) {
				Import asImport = (Import)eObject;
				addExternalReference(asImport.getImportedNamespace(), root);
			}
			else if (eObject instanceof TypedElement) {
				TypedElement typedElement = (TypedElement)eObject;
				Type type = typedElement.getType();
				if (!internalTypes.contains(type)) {
					addExternalReference(type, root);
				}
				if (eObject instanceof Property) {
					Property property = (Property)eObject;
					Property opposite = property.getOpposite();
					if (opposite != null) {
						if (PivotUtil.getContainingModel(opposite) == PivotUtil.getContainingModel(property)) {
							addExternalReference(opposite, root);
						}
						Type oppositeType = opposite.getType();
						if (!internalTypes.contains(oppositeType)) {
							addExternalReference(oppositeType, root);
						}
					}
				}
			}
		}
		if (name2external.size() > 0) {
			StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
			addExternalReference(standardLibrary.getOclAnyType(), root);
			addExternalReference(standardLibrary.getOclElementType(), root);
		}
	}

	protected @Nullable String getGeneratedClassName(EObject eObject) {
		return (eObject instanceof Package) ? generatedClassNameMap.get(((Package)eObject).getURI()) : null;
	}

	protected @NonNull String getGeneratedPackageId(@NonNull Package pkge) {
		PackageId basicPackageId = ((PackageImpl)pkge).basicGetPackageId();
		return basicPackageId == IdManager.METAMODEL ? "IdManager.METAMODEL" : "null";
	}


	protected @NonNull String getMoniker(@NonNull Element elem) {
		String moniker = element2moniker.get(elem);
		if (moniker == null) {
			moniker = AS2Moniker.toString(elem);
			element2moniker.put(elem, moniker);
		}
		return moniker;
	}

	protected @NonNull String getNameLiteral(@NonNull Property property) {
		return '"' + property.getName() + '"';
	}

	protected org.eclipse.ocl.pivot.@Nullable Package getOrphanPackage(org.eclipse.ocl.pivot.@NonNull Package elem) {
		return getOrphanPackage(getRootPackage(elem));
	}

	protected org.eclipse.ocl.pivot.@Nullable Package getOrphanPackage(@NonNull Model elem) {
		for (org.eclipse.ocl.pivot.Package pkg : getAllPackages(elem)) {
			if (PivotConstants.ORPHANAGE_NAME.equals(pkg.getName())) {
				return pkg;
			}
		}
		return null;
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

	protected String getPrefixedSymbolName(@NonNull EObject elem, @NonNull String prefix) {
		if (!(elem instanceof org.eclipse.ocl.pivot.Package)) {
			if (!((elem instanceof Type) && hasComplements((Type)elem))) {
				elem = metamodelManager.getPrimaryElement(elem);
			}
		}
		String prefixedSymbolName = nameQueries.getPrefixedSymbolName(prefix.replace(".",  "_"), elem);
		if (prefixedSymbolName.startsWith("_Variable")) {
			getClass();
		}
		return prefixedSymbolName;
	}

	protected String getPrefixedSymbolNameWithoutNormalization(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull String prefix) {
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

	protected ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
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

	protected String getSignature(@NonNull Operation elem) {
		EObject parent = elem.eContainer();
		if (parent != null) {
			return getSignature((NamedElement)parent) + "::" + elem.getName() + "()";
		} else {
			return elem.getName() + "()";
		}
	}

	protected @NonNull List<Operation> getSortedCoercions(@NonNull Model root) {
		Set<Operation> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof PrimitiveType) {
				allElements.addAll(((PrimitiveType)eObject).getCoercions());
			}
		}
		List<Operation> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Operation> getSortedCoercions(@NonNull PrimitiveType type, @NonNull List<Operation> allCoercions) {
		Set<Operation> allElements = new HashSet<>();
		for (Operation coercion : type.getCoercions()) {
			if (allCoercions.contains(coercion)) {
				allElements.add(coercion);
			}
		}
		List<Operation> sortedElements = new ArrayList<Operation>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<org.eclipse.ocl.pivot.Class>> getSortedClassTypes(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<org.eclipse.ocl.pivot.Class>> pkge2classTypes = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<org.eclipse.ocl.pivot.Class> classTypes = getClassTypes(pkge);
			if (classTypes != null) {
				pkge2classTypes.put(pkge, classTypes);
				Collections.sort(classTypes, monikerComparator);
			}
		}
		return pkge2classTypes;
	}

	protected abstract @NonNull Map<org.eclipse.ocl.pivot.Package, List<CollectionType>> getSortedCollectionTypes(@NonNull Model root);

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<CollectionType>> getSortedCollectionTypes(@NonNull Model root, Comparator<? super CollectionType> comparator) {
		Map<org.eclipse.ocl.pivot.Package, List<CollectionType>> pkge2collectionTypes = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<CollectionType> collectionTypes = null;
			for (Type type : pkge.getOwnedClasses()) {
				if (type instanceof CollectionType) {
					if (collectionTypes == null) {
						collectionTypes = new ArrayList<>();
						pkge2collectionTypes.put(pkge, collectionTypes);
					}
					collectionTypes.add((CollectionType)type);
				}
				/*				else if (type instanceof org.eclipse.ocl.pivot.Class) {		// FIXME fails because Orphanage has no Model
					for (Property property : ((org.eclipse.ocl.pivot.Class)type).getOwnedProperties()) {
						Property opposite = property.getOpposite();
						if (opposite != null) {
							Type oppositeType = opposite.getType();
							if (oppositeType instanceof CollectionType) {
								if (collectionTypes == null) {
									collectionTypes = new ArrayList<>();
									pkge2collectionTypes.put(pkge, collectionTypes);
								}
								collectionTypes.add((CollectionType)oppositeType);
							}
						}
					}
				} */
			}
			if (collectionTypes != null) {
				Collections.sort(collectionTypes, comparator);
			}
		}
		return pkge2collectionTypes;
	}

	/*	protected @NonNull List<Element> getSortedCommentedElements(@NonNull Model root) {
		List<Element> allClassTypes = new ArrayList<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<org.eclipse.ocl.pivot.Class> classTypes = getClassTypes(pkge);
			if (classTypes != null) {
				allClassTypes.addAll(classTypes);
			}
		}
		Collections.sort(allClassTypes, monikerComparator);
		return allClassTypes;
	}*/
	protected @NonNull List<Element> getSortedCommentedElements(@NonNull Model root) {
		List<org.eclipse.ocl.pivot.Class> allClassTypes = new ArrayList<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<org.eclipse.ocl.pivot.Class> classTypes = getClassTypes(pkge);
			if (classTypes != null) {
				allClassTypes.addAll(classTypes);
			}
		}
		Collection<org.eclipse.ocl.pivot.Class> oclTypes = allClassTypes;
		Set<Element> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if ((eObject instanceof Element) && !(eObject instanceof Constraint) &&
					!((eObject instanceof Property) && (((Property)eObject).getOwningClass() == null)) &&
					!((eObject instanceof org.eclipse.ocl.pivot.Class) && !oclTypes.contains(eObject))) {
				Element t = (Element)eObject;
				if (t.getOwnedComments().size() > 0) {
					allElements.add(t);
				}
			}
		}
		List<Element> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Comment> getSortedComments(@NonNull Element element) {
		List<Comment> sortedElements = new ArrayList<>(element.getOwnedComments());
		Collections.sort(sortedElements, commentComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<Enumeration>> getSortedEnumerations(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<Enumeration>> pkge2enumerations = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<Enumeration> enumerations = null;
			for (Type type : pkge.getOwnedClasses()) {
				if (type instanceof Enumeration) {
					if (enumerations == null) {
						enumerations = new ArrayList<>();
						pkge2enumerations.put(pkge, enumerations);
					}
					enumerations.add((Enumeration)type);
				}
			}
			if (enumerations != null) {
				Collections.sort(enumerations, nameableComparator);
			}
		}
		return pkge2enumerations;
	}

	protected @NonNull List<Package> getSortedExternalPackages(@NonNull Model root) {
		if (name2external.size() <= 0) {
			getExternals(root);
		}
		List<Package> externalPackages = new ArrayList<>(root.getOwnedPackages());
		for (Import asImport : root.getOwnedImports()) {
			Namespace importedNamespace = asImport.getImportedNamespace();
			Package externalPackage = PivotUtil.getContainingPackage(importedNamespace);
			if (externalPackage != null) {
				if (!externalPackages.contains(externalPackage)) {
					externalPackages.add(externalPackage);
				}
			}
		}
		for (Element element : name2external.values()) {
			Package externalPackage = PivotUtil.getContainingPackage(element);
			if (externalPackage != null) {
				if (!externalPackages.contains(externalPackage)) {
					externalPackages.add(externalPackage);
				}
			}
		}
		if (externalPackages.size() > 1) {
			Collections.sort(externalPackages, packageComparator);
		}
		return externalPackages;
	}

	protected @NonNull List<String> getSortedExternals(@NonNull Model root) {
		if (name2external.size() <= 0) {
			getExternals(root);
		}
		List<NamedElement> sortedExternals = new ArrayList<>(name2external.values());
		Collections.sort(sortedExternals, externalComparator);
		List<String> sortedExternalNames = new ArrayList<>(sortedExternals.size());
		for (NamedElement sortedExternal : sortedExternals) {
			sortedExternalNames.add(external2name.get(sortedExternal));
		}
		return sortedExternalNames;
	}

	protected @NonNull Map<Package,String> getSortedImports(@NonNull Model model) {
		if (name2external.size() <= 0) {
			getExternals(model);
		}
		Map<Package,String> import2alias = new HashMap<>();
		for (Import asImport : model.getOwnedImports()) {
			Namespace importedNamespace = asImport.getImportedNamespace();
			if (importedNamespace instanceof Package) {
				import2alias.put((Package)importedNamespace, asImport.getName());
			}
		}
		for (Map.Entry<String, NamedElement> entry : name2external.entrySet()) {
			NamedElement value = entry.getValue();
			if ((value instanceof Library) && !import2alias.containsKey(value)) {
				import2alias.put((Library)value, null);
			}
		}
		return import2alias;
	}


	/*	protected @NonNull List<Iteration> getSortedIterations(@NonNull Model root) {
		Set<Iteration> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Iteration) {
				allElements.add((Iteration)eObject);
			}
		}
		List<Iteration> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	} */

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<Iteration>> getSortedIterations(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<Iteration>> pkge2iterations = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<Iteration> iterations = null;
			for (org.eclipse.ocl.pivot.Class type : pkge.getOwnedClasses()) {
				for (Operation operation : type.getOwnedOperations()) {
					if (operation instanceof Iteration) {
						if (iterations == null) {
							iterations = new ArrayList<>();
							pkge2iterations.put(pkge, iterations);
						}
						iterations.add((Iteration) operation);
					}
				}
			}
			if (iterations != null) {
				Collections.sort(iterations, monikerComparator);
			}
		}
		return pkge2iterations;
	}

	/*	protected @NonNull List<Iteration> getSortedIterations(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull List<Iteration> allIterations) {
		Set<Iteration> allElements = new HashSet<>();
		for (Operation operation : type.getOwnedOperations()) {
			if (allIterations.contains(operation)) {
				allElements.add((Iteration)operation);
			}
		}
		List<Iteration> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	} */

	protected @NonNull List<LambdaType> getSortedLambdaTypes(@NonNull Model root) {
		Set<LambdaType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof LambdaType) {
				allElements.add((LambdaType)eObject);
			}
		}
		List<LambdaType> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Library> getSortedLibraries(@NonNull Model root) {
		Set<Library> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof Library) {
				allElements.add((Library)eObject);
			}
		}
		List<Library> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Library> getSortedLibrariesWithPrecedence(@NonNull Model root) {
		Set<Library> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if ((eObject instanceof Library) && (((Library)eObject).getOwnedPrecedences().size() > 0)) {
				allElements.add((Library)eObject);
			}
		}
		List<Library> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull <T extends Nameable> List<T> getSortedList(@NonNull Collection<? extends T> packages) {
		List<T> sortedElements = new ArrayList<>(packages);
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<MapType>> getSortedMapTypes(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<MapType>> pkge2mapTypes = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<MapType> mapTypes = null;
			for (Type type : pkge.getOwnedClasses()) {
				if (type instanceof MapType) {
					if (mapTypes == null) {
						mapTypes = new ArrayList<>();
						pkge2mapTypes.put(pkge, mapTypes);
					}
					mapTypes.add((MapType)type);
				}
			}
			if (mapTypes != null) {
				Collections.sort(mapTypes, monikerComparator);
			}
		}
		return pkge2mapTypes;
	}

	/*	protected @NonNull List<Operation> getSortedOperations(@NonNull Model root) {
		Set<Operation> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if ((eObject instanceof Operation) && !(eObject instanceof Iteration) &&
				!isEcoreConstraint((Operation)eObject)) {
				allElements.add((Operation)eObject);
			}
		}
		List<Operation> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	} */

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<Operation>> getSortedOperations(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<Operation>> pkge2operations = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<Operation> operations = null;
			for (org.eclipse.ocl.pivot.Class type : pkge.getOwnedClasses()) {
				for (Operation operation : type.getOwnedOperations()) {
					if (!(operation instanceof Iteration)) {
						if (operations == null) {
							operations = new ArrayList<>();
							pkge2operations.put(pkge, operations);
						}
						operations.add(operation);
					}
				}
			}
			if (operations != null) {
				Collections.sort(operations, monikerComparator);
			}
		}
		return pkge2operations;
	}

	/*	protected @NonNull List<Operation> getSortedOperations(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull List<Operation> allOperations) {
		Set<Operation> allElements = new HashSet<>();
		for (Operation operation : type.getOwnedOperations()) {
			if (allOperations.contains(operation)) {
				allElements.add(operation);
			}
		}
		List<Operation> sortedElements = new ArrayList<Opration>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	} */

	protected @NonNull List<Operation> getSortedOperationsWithPrecedence(@NonNull Model root) {
		Set<Operation> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if ((eObject instanceof Operation) && !(eObject instanceof Iteration) &&
					!isEcoreConstraint((Operation)eObject)) {
				Operation operation = (Operation)eObject;
				if (!(operation instanceof Iteration) && !isEcoreConstraint(operation) &&
						(operation.getPrecedence() != null)) {
					allElements.add(operation);
				}
			}
		}
		List<Operation> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSortedOwningTypes(@NonNull List<@NonNull ? extends Operation> operations) {
		Set<org.eclipse.ocl.pivot.Class> allElements = new HashSet<>();
		for (@NonNull Operation operation : operations) {
			if (operation.getOwningClass() != null) {
				allElements.add(operation.getOwningClass());
			}
		}
		List<org.eclipse.ocl.pivot.Class> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSortedOwningTypes2(@NonNull List<@NonNull ? extends Property> properties) {
		Set<org.eclipse.ocl.pivot.Class> allElements = new HashSet<>();
		for (@NonNull Property property : properties) {
			if (property.getOwningClass() != null) {
				allElements.add(property.getOwningClass());
			}
		}
		List<org.eclipse.ocl.pivot.Class> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Package> getSortedPackages(@NonNull Model root, @NonNull Collection<? extends org.eclipse.ocl.pivot.Package> packages) {
		//		Package orphanPackage = getOrphanPackage(root);
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(packages);
		Collections.sort(sortedElements, packageComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Package> getSortedPackages(@NonNull Model root) {
		//		Set<org.eclipse.ocl.pivot.Package> allElements = new HashSet<>();
		//		TreeIterator<EObject> tit = root.eAllContents();
		//		while (tit.hasNext()) {
		//			EObject eObject = tit.next();
		//			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
		//				allElements.add((org.eclipse.ocl.pivot.Package)eObject);
		//			}
		//		}
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(root.getOwnedPackages());
		Collections.sort(sortedElements, packageComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Package> getSortedPackages(org.eclipse.ocl.pivot.@NonNull Package pkg) {
		List<org.eclipse.ocl.pivot.Package> sortedElements = new ArrayList<>(pkg.getOwnedPackages());
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSortedParameterTypes(@NonNull Model root) {
		Set<org.eclipse.ocl.pivot.Class> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.ocl.pivot.Class) {
				org.eclipse.ocl.pivot.Class t = (org.eclipse.ocl.pivot.Class)eObject;
				if (t.isTemplateParameter() != null) {			// FIXME can never happen
					allElements.add(t);
				}
			}
		}
		List<org.eclipse.ocl.pivot.Class> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Precedence> getSortedPrecedences(@NonNull Library library) {
		List<Precedence> sortedElements = new ArrayList<>(library.getOwnedPrecedences());
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<PrimitiveType>> getSortedPrimitiveTypes(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<PrimitiveType>> pkge2primitiveTypes = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<PrimitiveType> primitiveTypes = null;
			for (Type type : pkge.getOwnedClasses()) {
				if (type instanceof PrimitiveType) {
					boolean isDefinedPrimitive = false;
					Type primaryType = metamodelManager.getPrimaryElement(type);
					if (type == primaryType)  {
						isDefinedPrimitive = true;
					}
					else if (!hasComplements(type)) {
						type = primaryType;
					}
					if (type != primaryType) {
						String externalName = external2name.get(type);
						if (externalName == null) {
							isDefinedPrimitive = true;
						}
					}
					if (isDefinedPrimitive) {
						if (primitiveTypes == null) {
							primitiveTypes = new ArrayList<>();
							pkge2primitiveTypes.put(pkge, primitiveTypes);
						}
						primitiveTypes.add((PrimitiveType)type);
					}
				}
			}
			if (primitiveTypes != null) {
				Collections.sort(primitiveTypes, monikerComparator);
			}
		}
		return pkge2primitiveTypes;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.Package, List<Property>> getSortedProperties(@NonNull Model root) {
		Map<org.eclipse.ocl.pivot.Package, List<Property>> pkge2properties = new HashMap<>();
		for (org.eclipse.ocl.pivot.Package pkge : root.getOwnedPackages()) {
			List<Property> properties = null;
			for (org.eclipse.ocl.pivot.Class type : pkge.getOwnedClasses()) {
				if (!(type instanceof TupleType)) {
					for (Property property : type.getOwnedProperties()) {
						if (properties == null) {
							properties = new ArrayList<>();
							pkge2properties.put(pkge, properties);
						}
						properties.add(property);
					}
				}
			}
			if (properties != null) {
				Collections.sort(properties, classPropertyComparator);
			}
		}
		return pkge2properties;
	}

	protected @NonNull List<Property> getSortedProperties(org.eclipse.ocl.pivot.@NonNull Class type) {
		List<Property> sortedElements = new ArrayList<>(type.getOwnedProperties());
		Collections.sort(sortedElements, OCLinEcoreTablesUtils.propertyComparator);
		return sortedElements;
	}

	/*	protected @NonNull List<Property> getSortedProperties(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull List<Property> allProperties) {
		Set<Property> allElements = new HashSet<>();
		for (Property property : type.getOwnedProperties()) {
			if (allProperties.contains(property)) {
				allElements.add(property);
			}
		}
		List<Property> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, OCLinEcoreTablesUtils.propertyComparator);
		if ("OCLExpression".equals(type.getName())) {
			Collections.sort(sortedElements, OCLinEcoreTablesUtils.propertyComparator);
		}
		return sortedElements;
	} */

	protected @NonNull List<TemplateParameter> getSortedTemplateParameters(@NonNull Model root) {
		Set<TemplateParameter> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TemplateParameter) {
				allElements.add((TemplateParameter)eObject);
			}
		}
		List<TemplateParameter> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<TemplateSignature> getSortedTemplateSignatures(@NonNull Model root) {
		Set<TemplateSignature> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TemplateSignature) {
				allElements.add((TemplateSignature)eObject);
			}
		}
		List<TemplateSignature> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected List<TemplateableElement> getSortedTemplateableElements(@NonNull Model root, @Nullable Comparator<EObject> nameComparator) {
		Set<TemplateableElement> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TemplateableElement) {
				TemplateableElement asTemplateableElement = (TemplateableElement)eObject;
				if (asTemplateableElement.getOwnedBindings().size() > 0) {
					allElements.add(asTemplateableElement);
				}
			}
		}
		List<TemplateableElement> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, nameComparator != null ? nameComparator : monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<Property> getSortedTupleParts(@NonNull TupleType tupleType) {
		List<Property> sortedElements = new ArrayList<>(tupleType.getOwnedProperties());
		Collections.sort(sortedElements, nameableComparator);
		return sortedElements;
	}

	protected @NonNull List<TupleType> getSortedTupleTypes(@NonNull Model root) {
		Set<TupleType> allElements = new HashSet<>();
		TreeIterator<EObject> tit = root.eAllContents();
		while (tit.hasNext()) {
			EObject eObject = tit.next();
			if (eObject instanceof TupleType) {
				allElements.add((TupleType)eObject);
			}
		}
		List<TupleType> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, monikerComparator);
		return sortedElements;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.Class> getSuperclassesInPackage(org.eclipse.ocl.pivot.@NonNull Class type) {
		List<org.eclipse.ocl.pivot.Class> allElements = new ArrayList<>();
		for (org.eclipse.ocl.pivot.Class superclass : type.getSuperClasses()) {
			if (getRootPackage(superclass.getOwningPackage()) == getRootPackage(type.getOwningPackage())) {
				allElements.add(superclass);
			}
		}
		return allElements;
	}

	protected @NonNull List<TemplateParameterSubstitution> getTemplateParameterSubstitutions(@NonNull TemplateableElement element) {
		List<TemplateParameterSubstitution> allElements = new ArrayList<>();
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

	protected abstract Model getThisModel();

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
}
