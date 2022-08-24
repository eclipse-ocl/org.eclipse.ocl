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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
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
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.resource.ASSaverNew;
import org.eclipse.ocl.pivot.internal.resource.ASSaverNew.ASSaverWithInverse;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;

@SuppressWarnings("all")
public abstract class GenerateOCLCommon extends GenerateMetamodelWorkflowComponent
{
	protected static class ContentAnalysis extends AbstractExtendingVisitor<@Nullable Object, @NonNull GenerateOCLCommon>
	{
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

		protected final @NonNull Comparator<@NonNull CollectionType> collectionTypeComparator = new Comparator<@NonNull CollectionType>()
		{
			@Override
			public int compare(@NonNull CollectionType o1, @NonNull CollectionType o2) {
				TypeId m1 = o1.getTypeId();
				TypeId m2 = o2.getTypeId();
				int i = m1.toString().compareTo(m2.toString());
				if (i != 0) {
					return i;
				}
				String n1 = o1.getElementType().getName();
				String n2 = o2.getElementType().getName();
				i = n1.compareTo(n2);
				if (i != 0) {
					return i;
				}
				IntegerValue l1 = o1.getLowerValue();
				IntegerValue l2 = o2.getLowerValue();
				i = l1.compareTo(l2);
				if (i != 0) {
					return i;
				}
				UnlimitedNaturalValue u1 = o1.getUpperValue();
				UnlimitedNaturalValue u2 = o2.getUpperValue();
				return u1.compareTo(u2);
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

		protected final @NonNull Comparator<org.eclipse.ocl.pivot.@NonNull Package> packageComparator = new Comparator<org.eclipse.ocl.pivot.@NonNull Package>()
		{
			@Override
			public int compare(org.eclipse.ocl.pivot.@NonNull Package o1, org.eclipse.ocl.pivot.@NonNull Package o2) {
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

		private final @NonNull Map<@NonNull Element, @NonNull String> element2moniker = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> package2sortedClasses = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull CollectionType>> package2sortedCollectionTypes = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Enumeration>> package2sortedEnumerations = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Iteration>> package2sortedIterations = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull MapType>> package2sortedMapTypes = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Operation>> package2sortedOperations = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull PrimitiveType>> package2sortedPrimitiveTypes = new HashMap<>();
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Property>> package2sortedProperties = new HashMap<>();
		private final @NonNull List<@NonNull Operation> sortedCoercions = new ArrayList<>();
		private final @NonNull List<@NonNull Element> sortedCommentedElements = new ArrayList<>();
		private final @NonNull List<@NonNull LambdaType> sortedLambdaTypes = new ArrayList<>();
		private final @NonNull List<@NonNull Library> sortedLibraries = new ArrayList<>();
		private final @NonNull List<@NonNull Library> sortedLibrariesWithPrecedence = new ArrayList<>();
		private final @NonNull List<@NonNull Operation> sortedOperationsWithPrecedence = new ArrayList<>();
		private final @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> sortedPackages = new ArrayList<>();
		private final @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> sortedParameterTypes = new ArrayList<>();
		private final @NonNull List<@NonNull TemplateParameter> sortedTemplateParameters = new ArrayList<>();
		private final @NonNull List<@NonNull TemplateSignature> sortedTemplateSignatures = new ArrayList<>();
		private final @NonNull List<@NonNull TemplateableElement> sortedTemplateableElements = new ArrayList<>();
		private final @NonNull List<@NonNull TupleType> sortedTupleTypes = new ArrayList<>();
		private final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> internalClasses = new HashSet<>();
		private final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> externalClasses = new HashSet<>();
		private final @NonNull Set<@NonNull NamedElement> allReferences = new HashSet<>();

		protected ContentAnalysis(@NonNull GenerateOCLCommon context) {
			super(context);
		}

		protected void analyze(@NonNull Model thisModel) {
			for (NamedElement asElement : context.external2name.keySet()) {
				if (asElement instanceof org.eclipse.ocl.pivot.Class) {
					CompleteClassInternal completeClass = context.metamodelManager.getCompleteClass((org.eclipse.ocl.pivot.Class)asElement);
					for (org.eclipse.ocl.pivot.@NonNull Class  asPartialClass : completeClass.getPartialClasses()) {
						externalClasses.add(asPartialClass);
					}
				}
			}
			for (@NonNull EObject eObject : new TreeIterable(thisModel, true)) {
				((Element)eObject).accept(this);
			}
			Collections.sort(sortedCoercions, monikerComparator);
			Collections.sort(sortedCommentedElements, monikerComparator);
			Collections.sort(sortedLambdaTypes, monikerComparator);
			Collections.sort(sortedLibraries, monikerComparator);
			Collections.sort(sortedLibrariesWithPrecedence, monikerComparator);
			Collections.sort(sortedOperationsWithPrecedence, monikerComparator);
			Collections.sort(sortedPackages, packageComparator);
			Collections.sort(sortedParameterTypes, monikerComparator);
			Collections.sort(sortedTupleTypes, monikerComparator);
			Collections.sort(sortedTemplateParameters, monikerComparator);
			Collections.sort(sortedTemplateSignatures, monikerComparator);
			Collections.sort(sortedTemplateableElements, monikerComparator);

			for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(thisModel)) {
				List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = package2sortedClasses.get(asPackage);
				if (sortedClasses != null) {
					Collections.sort(sortedClasses, NameUtil.NAMEABLE_COMPARATOR);
				}
				List<@NonNull CollectionType> sortedCollectionTypes = package2sortedCollectionTypes.get(asPackage);
				if (sortedCollectionTypes != null) {
					Collections.sort(sortedCollectionTypes, collectionTypeComparator);
				}
				List<@NonNull Enumeration> sortedEnumerations = package2sortedEnumerations.get(asPackage);
				if (sortedEnumerations != null) {
					Collections.sort(sortedEnumerations, NameUtil.NAMEABLE_COMPARATOR);
				}
				List<@NonNull Iteration> sortedIterations = package2sortedIterations.get(asPackage);
				if (sortedIterations != null) {
					Collections.sort(sortedIterations, monikerComparator);
				}
				List<@NonNull MapType> sortedMapTypes = package2sortedMapTypes.get(asPackage);
				if (sortedMapTypes != null) {
					Collections.sort(sortedMapTypes, monikerComparator);
				}
				List<@NonNull Operation> sortedOperations = package2sortedOperations.get(asPackage);
				if (sortedOperations != null) {
					Collections.sort(sortedOperations, monikerComparator);
				}
				List<@NonNull PrimitiveType> sortedPrimitiveTypes = package2sortedPrimitiveTypes.get(asPackage);
				if (sortedPrimitiveTypes != null) {
					Collections.sort(sortedPrimitiveTypes, NameUtil.NAMEABLE_COMPARATOR);
				}
				List<@NonNull Property> sortedProperties = package2sortedProperties.get(asPackage);
				if (sortedProperties != null) {
					Collections.sort(sortedProperties, classPropertyComparator);
				}
			}
			if (allReferences.size() > 0) {
				StandardLibrary standardLibrary = context.environmentFactory.getStandardLibrary();
				allReferences.add(standardLibrary.getOclAnyType());
				allReferences.add(standardLibrary.getOclElementType());
			}
			for (@NonNull NamedElement asNamedElement : allReferences) {
				if (!internalClasses.contains(asNamedElement)) {
					context.addExternalReference(asNamedElement, thisModel);
				}
			}
		}

		protected void doSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asClass)) {
				allReferences.add(superClass);
			}
		}

		protected void doTemplateableElement(@NonNull TemplateableElement asTemplateableElement) {
			if (asTemplateableElement.getOwnedBindings().size() > 0) {
				sortedTemplateableElements.add(asTemplateableElement);
			}
			TemplateSignature asTemplateSignature = asTemplateableElement.getOwnedSignature();
			if (asTemplateSignature != null) {
				sortedTemplateParameters.addAll(asTemplateSignature.getOwnedParameters());
			}
			TemplateableElement unspecializedElement = asTemplateableElement.getUnspecializedElement();
			if (unspecializedElement instanceof NamedElement) {
				allReferences.add((NamedElement)unspecializedElement);
			}
		}

		protected @NonNull String getMoniker(@NonNull Element elem) {
			String moniker = element2moniker.get(elem);
			if (moniker == null) {
				moniker = AS2Moniker.toString(elem);
				element2moniker.put(elem, moniker);
			}
			return moniker;
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			System.out.println("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
			return null;
		}

		@Override
		public @Nullable Object visitAnnotation(@NonNull Annotation asAnnotation) {
			return null;
		}

		@Override
		public @Nullable Object visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
			List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = package2sortedClasses.get(asPackage);
			if (sortedClasses == null) {
				sortedClasses = new ArrayList<>();
				package2sortedClasses.put(asPackage, sortedClasses);
			}
			sortedClasses.add(asClass);
			if (asClass.isTemplateParameter() != null) {			// FIXME can never happen
				sortedParameterTypes.add(asClass);
			}
			else if (!externalClasses.contains(asClass)){
				CompleteClassInternal completeClass = context.metamodelManager.getCompleteClass(asClass);
				for (org.eclipse.ocl.pivot.@NonNull Class  asPartialClass : completeClass.getPartialClasses()) {
					internalClasses.add(asPartialClass);
				}
			}
			doSuperClasses(asClass);
			doTemplateableElement(asClass);
			return null;
		}

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType asCollectionType) {
			org.eclipse.ocl.pivot.Package asPackage = asCollectionType.getOwningPackage();
			List<@NonNull CollectionType> sortedCollectionTypes = package2sortedCollectionTypes.get(asPackage);
			if (sortedCollectionTypes == null) {
				sortedCollectionTypes = new ArrayList<>();
				package2sortedCollectionTypes.put(asPackage, sortedCollectionTypes);
			}
			sortedCollectionTypes.add(asCollectionType);
			doSuperClasses(asCollectionType);
			doTemplateableElement(asCollectionType);
			return null;
		}

		@Override
		public @Nullable Object visitComment(@NonNull Comment asComment) {
			Element owningElement = asComment.getOwningElement();
			if (!externalClasses.contains(owningElement)) {
				sortedCommentedElements.add(owningElement);
			}
			return null;
		}

		@Override
		public @Nullable Object visitConstraint(@NonNull Constraint asConstraint) {
			return null;
		}

		@Override
		public @Nullable Object visitDetail(@NonNull Detail asDetail) {
			return null;
		}

		@Override
		public @Nullable Object visitEnumeration(@NonNull Enumeration asEnumeration) {
			org.eclipse.ocl.pivot.Package asPackage = asEnumeration.getOwningPackage();
			List<@NonNull Enumeration> sortedEnumerations = package2sortedEnumerations.get(asPackage);
			if (sortedEnumerations == null) {
				sortedEnumerations = new ArrayList<>();
				package2sortedEnumerations.put(asPackage, sortedEnumerations);
			}
			sortedEnumerations.add(asEnumeration);
			doSuperClasses(asEnumeration);
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationLiteral(@NonNull EnumerationLiteral asEnumerationLiteral) {
			return null;
		}

		@Override
		public @Nullable Object visitImport(@NonNull Import asImport) {
			allReferences.add(asImport.getImportedNamespace());
			return null;
		}

		@Override
		public @Nullable Object visitIteration(@NonNull Iteration asIteration) {
			org.eclipse.ocl.pivot.Class asClass = asIteration.getOwningClass();
			org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
			List<@NonNull Iteration> sortedIterations = package2sortedIterations.get(asPackage);
			if (sortedIterations == null) {
				sortedIterations = new ArrayList<>();
				package2sortedIterations.put(asPackage, sortedIterations);
			}
			sortedIterations.add(asIteration);
			doTemplateableElement(asIteration);
			return super.visitIteration(asIteration);
		}

		@Override
		public @Nullable Object visitLambdaType(@NonNull LambdaType asLambdaType) {
			sortedLambdaTypes.add(asLambdaType);
			doSuperClasses(asLambdaType);
			return null;
		}

		@Override
		public @Nullable Object visitLibrary(@NonNull Library asLibrary) {
			sortedLibraries.add(asLibrary);
			if (asLibrary.getOwnedPrecedences().size() > 0) {
				sortedLibrariesWithPrecedence.add(asLibrary);
			}
			return super.visitLibrary(asLibrary);
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType asMapType) {
			org.eclipse.ocl.pivot.Package asPackage = asMapType.getOwningPackage();
			List<@NonNull MapType> sortedMapTypes = package2sortedMapTypes.get(asPackage);
			if (sortedMapTypes == null) {
				sortedMapTypes = new ArrayList<>();
				package2sortedMapTypes.put(asPackage, sortedMapTypes);
			}
			sortedMapTypes.add(asMapType);
			doTemplateableElement(asMapType);
			doSuperClasses(asMapType);
			return null;
		}

		@Override
		public @Nullable Object visitModel(@NonNull Model asModel) {
			return null;
		}

		@Override
		public @Nullable Object visitOperation(@NonNull Operation asOperation) {
			if (!(asOperation instanceof Iteration)) {
				org.eclipse.ocl.pivot.Class asClass = asOperation.getOwningClass();
				org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
				List<@NonNull Operation> sortedOperations = package2sortedOperations.get(asPackage);
				if (sortedOperations == null) {
					sortedOperations = new ArrayList<>();
					package2sortedOperations.put(asPackage, sortedOperations);
				}
				sortedOperations.add(asOperation);
				if (/*!context.isEcoreConstraint(asOperation) &&*/ (asOperation.getPrecedence() != null)) {
					sortedOperationsWithPrecedence.add(asOperation);
				}
				doTemplateableElement(asOperation);
			}
			return super.visitOperation(asOperation);
		}

		@Override
		public @Nullable Object visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
			sortedPackages.add(asPackage);
			return null;
		}

		@Override
		public @Nullable Object visitPrecedence(@NonNull Precedence asPrecedence) {
			return null;
		}

		@Override
		public @Nullable Object visitPrimitiveType(final @NonNull PrimitiveType asPrimitiveType) {
			org.eclipse.ocl.pivot.Package asPackage = asPrimitiveType.getOwningPackage();
			List<@NonNull PrimitiveType> sortedPrimitiveTypes = package2sortedPrimitiveTypes.get(asPackage);
			if (sortedPrimitiveTypes == null) {
				sortedPrimitiveTypes = new ArrayList<>();
				package2sortedPrimitiveTypes.put(asPackage, sortedPrimitiveTypes);
			}
			sortedPrimitiveTypes.add(asPrimitiveType);
			sortedCoercions.addAll(asPrimitiveType.getCoercions());
			doSuperClasses(asPrimitiveType);
			return null;
		}

		@Override
		public @Nullable Object visitProperty(@NonNull Property asProperty) {
			org.eclipse.ocl.pivot.Class asClass = asProperty.getOwningClass();
			if (!(asClass instanceof TupleType)) {
				org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
				List<@NonNull Property> sortedProperties = package2sortedProperties.get(asPackage);
				if (sortedProperties == null) {
					sortedProperties = new ArrayList<>();
					package2sortedProperties.put(asPackage, sortedProperties);
				}
				sortedProperties.add(asProperty);
				Property asOpposite = asProperty.getOpposite();
				if (asOpposite != null) {
					if (PivotUtil.getContainingModel(asOpposite) == PivotUtil.getContainingModel(asProperty)) {
						allReferences.add(asOpposite);
					}
					allReferences.add(asOpposite.getType());
				}
			}
			return super.visitProperty(asProperty);
		}

		@Override
		public @Nullable Object visitTemplateBinding(@NonNull TemplateBinding asTemplateBinding) {
			return null;
		}

		@Override
		public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter asTemplateParameter) {
			return null;
		}

		@Override
		public @Nullable Object visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution asTemplateParameterSubstitution) {
			allReferences.add(asTemplateParameterSubstitution.getActual());
			allReferences.add(asTemplateParameterSubstitution.getFormal());
			return null;
		}

		@Override
		public @Nullable Object visitTemplateSignature(@NonNull TemplateSignature asTemplateSignature) {
			sortedTemplateSignatures.add(asTemplateSignature);
			return null;
		}

		@Override
		public @Nullable Object visitTupleType(@NonNull TupleType asTupleType) {
			sortedTupleTypes.add(asTupleType);
			doSuperClasses(asTupleType);
			return null;
		}

		@Override
		public @Nullable Object visitTypedElement(@NonNull TypedElement asTypedElement) {
			allReferences.add(asTypedElement.getType());
			return null;
		}
	}

	private final @NonNull Map<@NonNull NamedElement, @NonNull String> external2name = new HashMap<>();
	protected final @NonNull Map<@NonNull String, @NonNull NamedElement> name2external = new HashMap<>();
	protected final @NonNull Map<@NonNull String, @NonNull String> generatedClassNameMap = new HashMap<>();
	protected EnvironmentFactoryInternal environmentFactory;
	protected PivotMetamodelManager metamodelManager;
	protected NameQueries nameQueries;
	protected Model thisModel;
	private List<@NonNull Element> orphans;
	protected final @NonNull ContentAnalysis contentAnalysis = createContentAnalysis();

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
		if ((containingModel == root) || external2name.containsKey(reference) || Orphanage.isOrphanage(containingModel)) {
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

	protected @NonNull ContentAnalysis createContentAnalysis() {
		return new ContentAnalysis(this);
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
			if (eContainer instanceof NamedElement) {
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

	protected @NonNull List<@NonNull Operation> getSortedCoercions(@NonNull Model root) {
		return contentAnalysis.sortedCoercions;
	}

	protected @NonNull List<@NonNull Operation> getSortedCoercions(@NonNull PrimitiveType type, @NonNull List<@NonNull Operation> allCoercions) {
		Set<@NonNull Operation> allElements = new HashSet<>();
		for (Operation coercion : type.getCoercions()) {
			if (allCoercions.contains(coercion)) {
				allElements.add(coercion);
			}
		}
		List<@NonNull Operation> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, contentAnalysis.monikerComparator);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> getSortedClassTypes(@NonNull Model root) {
		return contentAnalysis.package2sortedClasses;
	}

	protected abstract @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull CollectionType>> getSortedCollectionTypes(@NonNull Model root);

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull CollectionType>> getSortedCollectionTypes(@NonNull Model root, Comparator<@NonNull ? super CollectionType> comparator) {
		return contentAnalysis.package2sortedCollectionTypes;
	}

	protected @NonNull List<@NonNull Element> getSortedCommentedElements(@NonNull Model root) {
		return contentAnalysis.sortedCommentedElements;
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
		List<org.eclipse.ocl.pivot.@NonNull Package> externalPackages = new ArrayList<>(root.getOwnedPackages());
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

	protected @NonNull List<@NonNull LambdaType> getSortedLambdaTypes(@NonNull Model root) {
		return contentAnalysis.sortedLambdaTypes;
	}

	protected @NonNull List<@NonNull Library> getSortedLibraries(@NonNull Model root) {
		return contentAnalysis.sortedLibraries;
	}

	protected @NonNull List<@NonNull Library> getSortedLibrariesWithPrecedence(@NonNull Model root) {
		return contentAnalysis.sortedLibrariesWithPrecedence;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull MapType>> getSortedMapTypes(@NonNull Model root) {
		return contentAnalysis.package2sortedMapTypes;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Operation>> getSortedOperations(@NonNull Model root) {
		return contentAnalysis.package2sortedOperations;
	}

	protected @NonNull List<@NonNull Operation> getSortedOperationsWithPrecedence(@NonNull Model root) {
		return contentAnalysis.sortedOperationsWithPrecedence;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSortedOwningTypes(@NonNull List<@NonNull ? extends Operation> operations) {
		Set<org.eclipse.ocl.pivot.@NonNull Class> allElements = new HashSet<>();
		for (@NonNull Operation operation : operations) {
			if (operation.getOwningClass() != null) {
				allElements.add(operation.getOwningClass());
			}
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> sortedElements = new ArrayList<>(allElements);
		Collections.sort(sortedElements, contentAnalysis.monikerComparator);
		return sortedElements;
	}

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

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSortedParameterTypes(@NonNull Model root) {
		return contentAnalysis.sortedParameterTypes;
	}

	protected @NonNull List<@NonNull Precedence> getSortedPrecedences(@NonNull Library library) {
		List<@NonNull Precedence> sortedElements = new ArrayList<>(library.getOwnedPrecedences());
		Collections.sort(sortedElements, NameUtil.NAMEABLE_COMPARATOR);
		return sortedElements;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull PrimitiveType>> getSortedPrimitiveTypes(@NonNull Model root) {
		return contentAnalysis.package2sortedPrimitiveTypes;
	}

	protected @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Property>> getSortedProperties(@NonNull Model root) {
		return contentAnalysis.package2sortedProperties;
	}

	protected @NonNull List<@NonNull Property> getSortedProperties(org.eclipse.ocl.pivot.@NonNull Class type) {
		List<@NonNull Property> sortedElements = new ArrayList<>(type.getOwnedProperties());
		Collections.sort(sortedElements, OCLinEcoreTablesUtils.propertyComparator);
		return sortedElements;
	}

	protected @NonNull List<@NonNull TemplateParameter> getSortedTemplateParameters(@NonNull Model root) {
		return contentAnalysis.sortedTemplateParameters;
	}

	protected @NonNull List<@NonNull TemplateSignature> getSortedTemplateSignatures(@NonNull Model root) {
		return contentAnalysis.sortedTemplateSignatures;
	}

	protected @NonNull List<@NonNull TemplateableElement> getSortedTemplateableElements(@NonNull Model root) {
		return contentAnalysis.sortedTemplateableElements;
	}

	protected @NonNull List<@NonNull TemplateableElement> getSortedTemplateableElements(@NonNull Model root, @NonNull Comparator<EObject> nameComparator) {
		List<@NonNull TemplateableElement> sortedElements = Lists.newArrayList(contentAnalysis.sortedTemplateableElements);
		Collections.sort(sortedElements, nameComparator);
		return sortedElements;
	}

	protected @NonNull List<@NonNull Property> getSortedTupleParts(@NonNull TupleType tupleType) {
		List<@NonNull Property> sortedElements = Lists.newArrayList(PivotUtil.getOwnedProperties(tupleType));
		Collections.sort(sortedElements, NameUtil.NAMEABLE_COMPARATOR);
		return sortedElements;
	}

	protected @NonNull List<TupleType> getSortedTupleTypes(@NonNull Model root) {
		return contentAnalysis.sortedTupleTypes;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSuperclassesInPackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {		// Redundant filter
		List<org.eclipse.ocl.pivot.@NonNull Class> allElements = new ArrayList<>();
		for (org.eclipse.ocl.pivot.@NonNull Class superclass : asClass.getSuperClasses()) {
		//	if (getRootPackage(superclass.getOwningPackage()) == getRootPackage(type.getOwningPackage())) {
				allElements.add(superclass);
		//	}
		}
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

	public void initModel(@NonNull Model thisModel, ASSaverNew.@NonNull ASSaverWithInverse asSaver) {
		this.thisModel = thisModel;
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
			else if ((localOrphan instanceof org.eclipse.ocl.pivot.Class) && Orphanage.isOrphan((org.eclipse.ocl.pivot.Class)localOrphan)) {
				s.append("orphanClass");
			}
			else if ((localOrphan instanceof org.eclipse.ocl.pivot.Package) && Orphanage.isOrphan((org.eclipse.ocl.pivot.Package)localOrphan)) {
				s.append("orphanPackage");
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
					Orphanage sharedOrphanage = Orphanage.getOrphanage(environmentFactory.getCompleteModel().getOrphanage());
					nameQueries.putSymbolName(sharedOrphanage, symbolName);
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
}
