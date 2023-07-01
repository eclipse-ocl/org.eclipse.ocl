/*******************************************************************************
 * Copyright (c) 2013, 2023 Willink Transformations and others.
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
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.SynthesisAnalysis;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.OrphanageImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Iterables;

public class ContentAnalysis extends SynthesisAnalysis
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
		private final @NonNull Map<@NonNull Element, @NonNull String> element2moniker = new HashMap<>();

		@Override
		public int compare(@NonNull Element o1, @NonNull Element o2) {
			String m1 = getMoniker(o1);
			String m2 = getMoniker(o2);
			return m1.compareTo(m2);
		}

		protected @NonNull String getMoniker(@NonNull Element elem) {
			String moniker = element2moniker.get(elem);
			if (moniker == null) {
				moniker = AS2Moniker.toString(elem);
				element2moniker.put(elem, moniker);
			}
			return moniker;
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

	protected final @NonNull GenerateOCLCommon generateContext;
	protected final @NonNull Model thisModel;

	final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> package2sortedClasses = new HashMap<>();
	final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Enumeration>> package2sortedEnumerations = new HashMap<>();
	final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Iteration>> package2sortedIterations = new HashMap<>();
	final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull Operation>> package2sortedOperations = new HashMap<>();
//	final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull List<@NonNull PrimitiveType>> package2sortedPrimitiveTypes = new HashMap<>();
	final @NonNull List<@NonNull Library> sortedLibraries = new ArrayList<>();
	final @NonNull List<@NonNull Library> sortedLibrariesWithPrecedence = new ArrayList<>();
	final @NonNull List<@NonNull Operation> sortedOperationsWithPrecedence = new ArrayList<>();
	final @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> sortedPackages = new ArrayList<>();
	final @NonNull List<@NonNull Property> sortedProperties = new ArrayList<>();
//	final @NonNull List<@NonNull TemplateParameter> sortedTemplateParameters = new ArrayList<>();
	final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> internalClasses = new HashSet<>();
	private final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> externalClasses = new HashSet<>();
	private final @NonNull Set<@NonNull NamedElement> allReferences = new HashSet<>();

	private final @NonNull Map<@NonNull DataType, @NonNull List<@NonNull DataType>> using2useds = new HashMap<>();

	protected ContentAnalysis(@NonNull GenerateOCLCommon context, @NonNull Model thisModel) {
		super(context.getEnvironmentFactory());
		this.generateContext = context;
		this.thisModel = thisModel;
	}

	private void addDependency(@NonNull DataType usingElement, @NonNull Type usedType) {
		assert (usingElement instanceof IterableType) || (usingElement instanceof LambdaType) || (usingElement instanceof TupleType);
		if (!(usedType instanceof IterableType)
		 && !(usedType instanceof LambdaType)
		 && !(usedType instanceof TupleType)) {		// Ignore fixed preamble declarations
			if (!using2useds.containsKey(usingElement)) {
				List<@NonNull DataType> old = using2useds.put(usingElement, new ArrayList<>());
				assert old == null;
			}
			return;
		}
		Model model1 = PivotUtil.getContainingModel(usingElement);
		Model model2 = PivotUtil.getContainingModel(usedType);
		assert model1 == thisModel;
	//	assert model2 == thisModel;		-- not true for Pivot
		PivotMetamodelManager metamodelManager = context.getMetamodelManager();
		DataType primaryUsingElement = usingElement;//metamodelManager.getPrimaryElement(usingElement);
		DataType primaryUsedType = (DataType) usedType;//metamodelManager.getPrimaryElement(usedType);
		@Nullable DataType primaryUsedElement = primaryUsedType;
		List<@NonNull DataType> usedElements = using2useds.get(primaryUsingElement);
		if (usedElements == null) {
			usedElements = new ArrayList<>();
			List<@NonNull DataType> old = using2useds.put(primaryUsingElement, usedElements);
			assert old == null;
		}
		if ((primaryUsedElement != null) && !usedElements.contains(primaryUsedElement)) {
		//	System.out.println("\taddDependency: " + NameUtil.debugSimpleName(primaryUsedType) + " : " + primaryUsedType + " => " + NameUtil.debugSimpleName(primaryUsedElement) + " : " + primaryUsedElement);
			usedElements.add(primaryUsedElement);
		//	System.out.println("addDependency " + usingElement + " - " + usedType + " - " + primaryUsedElement);
		//	if ("UniqueCollection(OrderedSet.T)".equals(usingElement.toString())) {
		//		getClass();	// XXX
		//	}
			assert primaryUsedElement != primaryUsingElement;
			if (!using2useds.containsKey(primaryUsedElement)) {
				List<@NonNull DataType> old = using2useds.put(primaryUsedElement, new ArrayList<>());
				assert old == null;
			}
		}
	}

	private boolean addReference(@NonNull NamedElement asElement) {
		return allReferences.add(asElement);
	}

	protected void analyze(@NonNull Model thisModel) {
		for (NamedElement asElement : generateContext.external2name.keySet()) {
			if (asElement instanceof org.eclipse.ocl.pivot.Class) {
				CompleteClassInternal completeClass = context.getMetamodelManager().getCompleteClass((org.eclipse.ocl.pivot.Class)asElement);
				for (org.eclipse.ocl.pivot.@NonNull Class  asPartialClass : completeClass.getPartialClasses()) {
					externalClasses.add(asPartialClass);
				}
			}
		}
		analyzeContents(thisModel);

		Collections.sort(sortedLibraries, monikerComparator);
		Collections.sort(sortedLibrariesWithPrecedence, monikerComparator);
		Collections.sort(sortedOperationsWithPrecedence, monikerComparator);
		Collections.sort(sortedPackages, packageComparator);
		Collections.sort(sortedProperties, classPropertyComparator);
//		Collections.sort(sortedTemplateParameters, monikerComparator);

		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(thisModel)) {
			List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = package2sortedClasses.get(asPackage);
			if (sortedClasses != null) {
				Collections.sort(sortedClasses, NameUtil.NAMEABLE_COMPARATOR);
			}
			List<@NonNull Enumeration> sortedEnumerations = package2sortedEnumerations.get(asPackage);
			if (sortedEnumerations != null) {
				Collections.sort(sortedEnumerations, NameUtil.NAMEABLE_COMPARATOR);
			}
			List<@NonNull Iteration> sortedIterations = package2sortedIterations.get(asPackage);
			if (sortedIterations != null) {
				Collections.sort(sortedIterations, monikerComparator);
			}
			List<@NonNull Operation> sortedOperations = package2sortedOperations.get(asPackage);
			if (sortedOperations != null) {
				Collections.sort(sortedOperations, monikerComparator);
			}
//			List<@NonNull PrimitiveType> sortedPrimitiveTypes = package2sortedPrimitiveTypes.get(asPackage);
//			if (sortedPrimitiveTypes != null) {
//				Collections.sort(sortedPrimitiveTypes, NameUtil.NAMEABLE_COMPARATOR);
//			}
		}
		if (allReferences.size() > 0) {
			CompleteStandardLibrary standardLibrary = context.getStandardLibrary();
			addReference(standardLibrary.getOclAnyType());
			addReference(standardLibrary.getOclElementType());
		}
		for (@NonNull NamedElement asNamedElement : allReferences) {
			if (!internalClasses.contains(asNamedElement)) {
				generateContext.addExternalReference(asNamedElement, thisModel);
			}
		}
		analyzeDependencies();
	}

/*	private void analyzeDependencies(/*@NonNull* / Model root) {
		analyzeDependencies1();
		Map<EObject, Collection<Setting>> eObject2settings = EcoreUtil.CrossReferencer.find(Collections.singleton(root));
		for (Map.Entry<@NonNull DataType, @NonNull List<@NonNull DataType>> entry : using2useds.entrySet()) {
			DataType using = entry.getKey();
			List<@NonNull DataType> useds = entry.getValue();
			for (@NonNull DataType used : useds) {
				boolean gotItA = hasReference(eObject2settings, using, used);
				boolean gotItB = hasReference(eObject2settings, used, using);
			//	System.out.println(using + " referenced-by " + used + " " + gotItA + "/" + gotItB);
			}
		}
		Set<@NonNull DataType> keysDone = new HashSet<>();
		Set<@NonNull NamedElement> externals = new HashSet<>(generateContext.name2external.values());
	} */

	@Override
	protected void doSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asClass)) {
			addReference(superClass);
		//	addDependency(asClass, superClass);
		//	System.out.println("doSuperClasses " + asClass + " - " + superClass);
		}
		super.doSuperClasses(asClass);
	}

	@Override
	protected void doTemplateableElement(@NonNull TemplateableElement asTemplateableElement) {
//		TemplateSignature asTemplateSignature = asTemplateableElement.getOwnedSignature();
//		if (asTemplateSignature != null) {
//			List<TemplateParameter> asTemplateParameters = asTemplateSignature.getOwnedParameters();
//			sortedTemplateParameters.addAll(asTemplateParameters);
//		}
		TemplateableElement generic = asTemplateableElement.getGeneric();
		if (generic instanceof NamedElement) {
			addReference((NamedElement)generic);
			addDependency((DataType)asTemplateableElement, (Type)generic);
		}
		super.doTemplateableElement(asTemplateableElement);
	}

	@Override
	protected void doTypedElement(@NonNull TypedElement asTypedElement) {
		Type asType = asTypedElement.getType();
		if (asType != null) {
			addReference(asType);
		}
		super.doTypedElement(asTypedElement);
	}

	/**
	 * Return true if referencingElement transitively references some template paramtrer other than those in templateParameters.
	 */
	protected boolean hasExternalBinding(@NonNull TemplateableElement referencingElement, @NonNull Iterable<@NonNull TemplateParameter> templateParameters) {
		for (TemplateBinding asTemplateBinding : referencingElement.getOwnedBindings()) {
			for (TemplateParameterSubstitution asTemplateParameterSubstitution : asTemplateBinding.getOwnedSubstitutions()) {
				Type actual = asTemplateParameterSubstitution.getActual();
				if (actual instanceof TemplateParameter) {
					TemplateParameter asTemplateParameter = (TemplateParameter)actual;
					if (!Iterables.contains(templateParameters, asTemplateParameter)) {
						return true;
					}
				}
				else if (actual instanceof TemplateableElement) {
					if (hasExternalBinding((TemplateableElement)actual, templateParameters)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean hasReference(@NonNull Map<EObject, Collection<Setting>> eObject2settings, @NonNull DataType using, @NonNull DataType used) {
		Collection<Setting> settings = eObject2settings.get(using);
		if (settings != null) {
			for (Setting setting : settings) {
				EObject eObject = setting.getEObject();
				if (eObject == used) {
					return true;
				}
			}
		}
		return false;
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
		}
		else if (!externalClasses.contains(asClass)){
			CompleteClassInternal completeClass = context.getMetamodelManager().getCompleteClass(asClass);
			for (org.eclipse.ocl.pivot.@NonNull Class  asPartialClass : completeClass.getPartialClasses()) {
				internalClasses.add(asPartialClass);
			}
		}
		return super.visitClass(asClass);
	}

	@Override
	public @Nullable Object visitCollectionType(@NonNull CollectionType asCollectionType) {
	//	System.out.println("analyze: " + NameUtil.debugSimpleName(asCollectionType) + " : " + asCollectionType);
		for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asCollectionType)) {
			addDependency(asCollectionType, superClass);
		}
		CollectionType genericCollection = (CollectionType)asCollectionType.getGeneric();
		if (genericCollection != null) {
			addDependency(asCollectionType, genericCollection);
			Type elementType = asCollectionType.getElementType();
			if (elementType instanceof TemplateParameter) {
				TemplateableElement owningElement = ((TemplateParameter) elementType).getOwningSignature().getOwningElement();
				if (owningElement instanceof Type) {
				//	addDependency(asCollectionType, (Type)owningElement);
				}
				else {
					getClass();		// XXX operation/iteration
				}
			}
			else {
				addDependency(asCollectionType, elementType);
			}
		}
		return super.visitCollectionType(asCollectionType);
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
		return super.visitEnumeration(asEnumeration);
	}

	@Override
	public @Nullable Object visitImport(@NonNull Import asImport) {
		addReference(asImport.getImportedNamespace());
		return super.visitImport(asImport);
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
		return super.visitIteration(asIteration);
	}

	@Override
	public @Nullable Object visitLambdaType(@NonNull LambdaType asLambdaType) {
		addDependency(asLambdaType, asLambdaType.getContextType());
		addDependency(asLambdaType, asLambdaType.getResultType());
		for (@NonNull Type asParameterType : asLambdaType.getParameterTypes()) {
			addDependency(asLambdaType, asParameterType);
		}
		doSuperClasses(asLambdaType);
		for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asLambdaType)) {
			addDependency(asLambdaType, superClass);
		}
		return super.visitLambdaType(asLambdaType);
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
		for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asMapType)) {
			addDependency(asMapType, superClass);
		}
		if (asMapType.getGeneric() != null) {
			addDependency(asMapType, asMapType.getKeyType());
			addDependency(asMapType, asMapType.getValueType());
		}
		return super.visitMapType(asMapType);
	}

	@Override
	public @Nullable Object visitOperation(@NonNull Operation asOperation) {
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
		return super.visitOperation(asOperation);
	}

	@Override
	public @Nullable Object visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (!OrphanageImpl.isOrphanage(asPackage)) {
			sortedPackages.add(asPackage);
		}
	//	SynthesisSchedule.Slot slot = synthesisSchedule.getSlot(asPackage);
	//	slot.addPredecessor(getSlot(asEnumeration.eContainer()));
		return super.visitPackage(asPackage);
	}

	@Override
	public @Nullable Object visitProperty(@NonNull Property asProperty) {
		org.eclipse.ocl.pivot.Class asClass = asProperty.getOwningClass();
		if (!(asClass instanceof TupleType)) {
			sortedProperties.add(asProperty);
			Property asOpposite = asProperty.getOpposite();
			if (asOpposite != null) {
				if (PivotUtil.getContainingModel(asOpposite) == PivotUtil.getContainingModel(asProperty)) {
					addReference(asOpposite);
				}
				addReference(asOpposite.getType());
			}
		}
		return super.visitProperty(asProperty);
	}

	@Override
	public @Nullable Object visitTemplateParameterSubstitution(@NonNull TemplateParameterSubstitution asTemplateParameterSubstitution) {
		addReference(asTemplateParameterSubstitution.getActual());
		addReference(asTemplateParameterSubstitution.getFormal());
		return super.visitTemplateParameterSubstitution(asTemplateParameterSubstitution);
	}


	@Override
	public @Nullable Object visitTupleType(@NonNull TupleType asTupleType) {
		for (org.eclipse.ocl.pivot.Class superClass : PivotUtil.getSuperClasses(asTupleType)) {
			addDependency(asTupleType, superClass);
		}
		for (@NonNull Property asPart : asTupleType.getOwnedProperties()) {
			addDependency(asTupleType, asPart.getType());
		}
		return super.visitTupleType(asTupleType);
	}
}