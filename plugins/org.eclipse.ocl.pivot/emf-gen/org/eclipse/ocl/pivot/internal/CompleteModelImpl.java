/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrphanCompletePackage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveCompletePackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.complete.AbstractCompletePackages;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.PartialModels;
import org.eclipse.ocl.pivot.internal.complete.RootCompletePackages;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.utilities.CompleteElementIterable;
import org.eclipse.ocl.pivot.model.OCLmetamodel;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Model</b></em>'.
 * @extends org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getOrphanCompletePackage <em>Orphan Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getPartialModels <em>Partial Models</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getPrimitiveCompletePackage <em>Primitive Complete Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteModelImpl extends NamedElementImpl implements CompleteModel, org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal
{
	private static final Logger logger = Logger.getLogger(CompleteModelImpl.class);

	/**
	 * @since 7.0
	 */
	public static class CompleteClassPropertiesIterable extends CompleteElementIterable<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Property>
	{
		protected final Boolean selectStatic;	// null for static/non-static, true for static, false for non-static

		public CompleteClassPropertiesIterable(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> types, boolean selectStatic) {
			super(types);
			this.selectStatic = selectStatic;
		}

		@Override
		protected @NonNull Iterable<@NonNull Property> getInnerIterable(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			return PivotUtil.getOwnedProperties(asClass);
		}

		@Override
		protected @Nullable Property getInnerValue(@NonNull Property element) {
			if (selectStatic != null) {
				if (element.isIsStatic() != selectStatic.booleanValue()) {
					return null;
				}
			}
			return element;
		}
	}

	/**
	 * @since 7.0
	 */
	public static class CompleteElementInvariantsIterable extends CompleteElementIterable<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Constraint>
	{
		public CompleteElementInvariantsIterable(@NonNull Iterable<? extends org.eclipse.ocl.pivot.@NonNull Class> asClasses) {
			super(asClasses);
		}

		@Override
		protected @NonNull Iterable<@NonNull Constraint> getInnerIterable(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			return PivotUtil.getOwnedInvariants(asClass);
		}
	}

	/**
	 * @since 7.0
	 */
	public static class CompleteTypeOperationsIterable extends CompleteElementIterable<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Operation>
	{
		protected final Boolean selectStatic;	// null for static/non-static, true for static, false for non-static

		public CompleteTypeOperationsIterable(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> asClasses, boolean selectStatic) {
			super(asClasses);
			this.selectStatic = selectStatic;
		}

		@Override
		protected @NonNull Iterable<@NonNull Operation> getInnerIterable(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			return PivotUtil.getOwnedOperations(asClass);
		}

		@Override
		protected @Nullable Operation getInnerValue(@NonNull Operation element) {
			if (selectStatic != null) {
				if (element.isIsStatic() != selectStatic.booleanValue()) {
					return null;
				}
			}
			return element;
		}
	}

	/**
	 * @since 7.0
	 */
	protected static final class ConstraintExecutabilityComparator implements Comparator<@NonNull Constraint>
	{
		public static final @NonNull ConstraintExecutabilityComparator INSTANCE = new ConstraintExecutabilityComparator();

		@Override
		public int compare(@NonNull Constraint o1, @NonNull Constraint o2) {
			EObject e1 = o1.getESObject();
			EObject e2 = o2.getESObject();
			if ((e1 != null) && (e2 == null)) {
				return 1;
			}
			if ((e1 == null) && (e2 != null)) {
				return 1;
			}
			return 0;		// XXX $$complete-ocl$$ comparison
		}
	}

	/**
	 * The number of structural features of the '<em>Complete Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_MODEL_FEATURE_COUNT = NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Complete Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_MODEL_OPERATION_COUNT = NamedElementImpl.NAMED_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The cached value of the '{@link #getOrphanCompletePackage() <em>Orphan Complete Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrphanCompletePackage()
	 * @generated
	 * @ordered
	 */
	protected OrphanCompletePackage orphanCompletePackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.COMPLETE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 6:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedCompletePackages()).basicAdd(otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 6:
				return ((InternalEList<?>)getOwnedCompletePackages()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return getOrphanCompletePackage();
			case 6:
				return getOwnedCompletePackages();
			case 7:
				return getPartialModels();
			case 8:
				return getPrimitiveCompletePackage();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 6:
				getOwnedCompletePackages().clear();
				getOwnedCompletePackages().addAll((Collection<? extends CompletePackage>)newValue);
				return;
			case 7:
				getPartialModels().clear();
				getPartialModels().addAll((Collection<? extends Model>)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 6:
				getOwnedCompletePackages().clear();
				return;
			case 7:
				getPartialModels().clear();
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return orphanCompletePackage != null;
			case 6:
				return ownedCompletePackages != null && !ownedCompletePackages.isEmpty();
			case 7:
				return partialModels != null && !partialModels.isEmpty();
			case 8:
				return primitiveCompletePackage != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return getOwnedCompletePackage((String)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * The cached value of the '{@link #getOwnedCompletePackages() <em>Owned Complete Packages</em>}' containment reference list.
	 */
	protected /*final @NonNull*/ RootCompletePackages ownedCompletePackages;

	/**
	 * The cached value of the '{@link #getPartialModels() <em>Partial Roots</em>}' reference list.
	 */
	protected /*final @NonNull*/ PartialModels partialModels;

	/**
	 * The cached value of the '{@link #getPrimitiveCompletePackage() <em>Primitive Complete Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitiveCompletePackage()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveCompletePackage primitiveCompletePackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteModelImpl()
	{
		super();
	}

	/**
	 * Map of (hierarchical) complete package id to CompletePackage.
	 * (Each CompletePackage has a distinct (hierarchical) complete package id.)
	 */
	private final @NonNull Map<@NonNull CompletePackageId, @NonNull CompletePackage> completePackageId2completePackage = new HashMap<>();

	/**
	 * Map of Package to CompletePackage.
	 * (All packages have corresponding CompletePackages, duplicate Package URIs fold to the same CompletePackage.)
	 */
	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull CompletePackage> package2completePackage = new HashMap<>();

	/**
	 * Map of Package URI to CompletePackage.
	 * (Only packages with URIs have corresponding CompletePackages, duplicate Package URIs fold to the same CompletePackage.)
	 */
	private final @NonNull Map<@NonNull String, @NonNull CompletePackage> packageURI2completePackage = new HashMap<>();

	protected /*final @NonNull*/ EnvironmentFactory environmentFactory;
	private /*final @NonNull*/ CompleteStandardLibrary standardLibrary;

	private Orphanage orphanage = null;

	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull CompleteClassInternal> class2completeClass = new WeakHashMap<>();

	private boolean autoLoadASmetamodel = true;

	private org.eclipse.ocl.pivot.Package asMetamodel = null;
	private boolean asMetamodelLoadInProgress = false;

	private final @NonNull Map<@NonNull String, @NonNull Namespace> globalNamespaces = new HashMap<>();
	private final @NonNull Set<@NonNull Type> globalTypes = new HashSet<>();

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompleteModel(this);
	}

	/**
	 * Partial models such as the OCL Standard Library have their own distinct package URI. These partial
	 * models are merged by mapping the package URI to a complete URI. DomainConstants.METAMODEL_NAME is the
	 * complete URI for all contributions merged as the overall OCL metamodel.
	 *
	private void addPackageURI2completeURI(@NonNull String packageURI, @NonNull CompletePackage completePackage) {
		CompletePackage old = packageURI2completePackage.put(packageURI, completePackage);
		assert (old == null) || (old == completePackage);

		String completeURI = PivotUtil.getURI(completePackage);


	/*	String oldCompleteURI = packageURI2completeURI.get(packageURI);
		if (completePackage.equals(oldCompleteURI)) {
			return;
		}
		if (oldCompleteURI != null) {
			throw new IllegalMetamodelException(completeURI, oldCompleteURI);	// FIXME Better name
		}
	//	if (completeURI2packageURIs.containsKey(packageURI)) {
	//		throw new IllegalMetamodelException(packageURI, oldCompleteURI);	// FIXME Better name
	//	}
		packageURI2completeURI.put(packageURI, completeURI);

		completePackage.didAddPackageURI(packageURI); * /
	} */

	@Override
	public @Nullable Namespace addGlobalNamespace(@NonNull String name, @NonNull Namespace namespace) {
		return globalNamespaces.put(name, namespace);
	}

	/**
	 * @since 7.0
	 */
	public boolean addGlobalTypes(@NonNull Collection<@NonNull Type> types) {
		return globalTypes.addAll(types);
	}

	/**
	 * @since 1.23
	 */
	@Override
	public @Nullable CompleteClassInternal basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof ElementExtension) {
			Stereotype stereotype = ((ElementExtension)asClass).getStereotype();
			if (stereotype != null) {
				asClass = stereotype;
			}
		}
		return class2completeClass.get(asClass);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable CompletePackage basicGetCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return package2completePackage.get(asPackage);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable CompletePackage basicGetCompletePackage(@NonNull CompletePackageId completePackageId) {
		return completePackageId2completePackage.get(completePackageId);
	}

	@Override
	public @Nullable CompletePackage basicGetCompletePackageForURI(@NonNull String packageURI) {
		int lastIndex = packageURI.lastIndexOf("#/");
		if (lastIndex > 0) {
			@NonNull String substring = packageURI.substring(0, lastIndex);
			packageURI = substring;
		}
		return packageURI2completePackage.get(packageURI);
		/*String completePackageName = getCompleteURI(packageURI);
		return completePackageId2completePackage.get(completePackageName); */
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull CompletePackage createCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @Nullable String uri) {
		assert !completePackageId2completePackage.containsKey(completePackageId);
		CompletePackageImpl completePackage = (CompletePackageImpl)PivotFactory.eINSTANCE.createCompletePackage();
		completePackage.init(completePackageId, prefix, uri);
		return completePackage;
	}

	@Override
	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass) {
		//		assert partialClass.getUnspecializedElement() == null;
		CompleteClass oldCompleteClass = class2completeClass.put(partialClass, completeClass);
		assert (oldCompleteClass == null) || (oldCompleteClass == completeClass);
	}

	/**
	 * @since 7.0
	 */
	public void didAddCompletePackage(@NonNull CompletePackage completePackage) {
		//		if ((completePackage != completeModel.getOrphanCompletePackage()) && (completePackage != completeModel.getPrimitiveCompletePackage())) {
		CompletePackageId completePackageId = completePackage.getCompletePackageId();
		CompletePackage oldCompletePackage = completePackageId2completePackage.put(completePackageId, completePackage);
		assert oldCompletePackage == null;
		//	if (COMPLETE_URIS.isActive()) {
		//		traceURImapping(completeURI);
		//	}
	}

	/**
	 * @since 7.0
	 */
	public void didAddPackage(@NonNull CompletePackage completePackage, org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		CompletePackage old = package2completePackage.put(partialPackage, completePackage);
		assert (old == null) || (old == completePackage);
	}

	@Override
	public void didAddPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (asPackage instanceof Library) {
			standardLibrary.installLibrary((Library)asPackage);
		}
	//	CompletePackage completePackage = getCompletePackage(PivotUtil.getName(asPackage), asPackage.getNsPrefix(), packageURI);
		CompletePackage completePackage = getCompletePackage3(asPackage);
		assert completePackage != null;
		assert (completePackage instanceof PrimitiveCompletePackage) || completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
	//	CompletePackage completePackage = ownedCompletePackages.didAddPackage(asPackage);
		CompletePackage old1 = package2completePackage.put(asPackage, completePackage);
		assert (old1 == null) || (old1 == completePackage);
		String packageURI = asPackage.getURI();
		if (packageURI != null) {
			completePackage.didAddPackageURI(packageURI);
			assert Iterables.contains(completePackage.getPackageURIs(), packageURI);
		//	completePackage.didAddPackageURI(packageURI);
		//	addPackageURI2completeURI(packageURI, completePackage);
			CompletePackage old2 = packageURI2completePackage.put(packageURI, completePackage);
			assert (old2 == null) || (old2 == completePackage);
		//	packageURI2completePackage.put(packageURI, completePackage);
		}
		assert completePackageId2completePackage.get(completePackage.getCompletePackageId()) == completePackage;
		if (!(completePackage instanceof PrimitiveCompletePackage)) {
			assert completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
			assert package2completePackage.get(asPackage) == completePackage;
			if (packageURI != null) {
				assert packageURI2completePackage.get(packageURI) == completePackage;
			}
		}
	//	getCompleteClasses(asPackage);
	}

	@Override
	public void didAddPartialModel(@NonNull Model partialModel) {
		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(partialModel)) {
			didAddPackage(asPackage);
		//	CompletePackage completePackage = getCompletePackage3(asPackage);
		//	assert completePackage != null;
		//	completePackage.getPartialPackages().add(asPackage);
		}
	}

	@Override
	public void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		class2completeClass.remove(pivotType);
	}

	@Override
	public void didRemoveCompletePackage(@NonNull CompletePackage completePackage) {
		if (completePackage == primitiveCompletePackage) {
			primitiveCompletePackage = null;
		}
		CompletePackageId completePackageId = completePackage.getCompletePackageId();
		completePackageId2completePackage.remove(completePackageId);
		for (@NonNull String packageURI : completePackage.getPackageURIs()) {
			packageURI2completePackage.remove(packageURI);
		}
	/*	String completeURI = PivotUtil.getURI(completePackage);
		Set<@NonNull String> packageURIs = completeURI2packageURIs.remove(completeURI);
		if (packageURIs != null) {
			for (String packageURI : packageURIs) {
				packageURI2completeURI.remove(packageURI);
			}
		}
		if (COMPLETE_URIS.isActive()) {
			traceURImapping(completeURI);
		} */
	}

	@Override
	public void didRemoveNestedPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {				// XXX not 'Nested'
		CompletePackage completePackage = ownedCompletePackages.didRemovePackage(asPackage);			// XXX getCompletePackage
		String packageURI = asPackage.getURI();
		if (packageURI != null) {
			packageURI2completePackage.remove(packageURI);
			for (@NonNull String packageURI2 : completePackage.getPackageURIs()) {
				packageURI2completePackage.put(packageURI2, completePackage);		// Restore any duplicate residues
				completePackageId2completePackage.put(completePackage.getCompletePackageId(), completePackage);
			}
		}
	}

	@Override
	public void didRemovePartialModel(@NonNull Model partialModel) {		// UPdates occur fia didRemovePackage etc
	/*	for (org.eclipse.ocl.pivot.Package asPackage : partialModel.getOwnedPackages()) {
			String packageURI = asPackage.getURI();
			String completeURI = getCompleteURI(packageURI);
			if (completeURI == packageURI) {
				PackageId packageId = asPackage.getPackageId();
				assert packageId != IdManager.METAMODEL_ID;
				if (packageId == IdManager.METAMODEL_ID) {
					if (packageURI != null) {
						//FIXME						removePackageURI2completeURI(packageURI, DomainConstants.METAMODEL_NAME);
					}
				}
			}
		} */
	}

	@Override
	public synchronized void dispose() {
		ownedCompletePackages.dispose();
		completePackageId2completePackage.clear();
		package2completePackage.clear();
		packageURI2completePackage.clear();
		class2completeClass.clear();
		asMetamodel = null;
		globalNamespaces.clear();
		globalTypes.clear();
		Orphanage orphanage2 = orphanage;
		if (orphanage2 != null) {
			orphanage2.removePackageListener(getOrphanCompletePackage().getPartialPackages());
			orphanage = null;
		}
	}

	/**
	 * Return the pivot model class for className with the Pivot Model.
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className) {
		if ((asMetamodel == null) && !asMetamodelLoadInProgress) {
			getASmetamodel();
			if (asMetamodel == null) {
				return null;
			}
		}
		return NameUtil.getNameable(asMetamodel.getOwnedClasses(), className);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getASmetamodel() {
		try {
			if ((asMetamodel == null) && !asMetamodelLoadInProgress) {
				asMetamodelLoadInProgress = true;
				if ((asMetamodel == null) && autoLoadASmetamodel && !environmentFactory.isDisposing() && !standardLibrary.isLibraryLoadInProgress()) {
					AnyType oclAnyType = standardLibrary.getOclAnyType();				// Load a default library if necessary.
					org.eclipse.ocl.pivot.Package stdlibPackage = oclAnyType.getOwningPackage();
					if (stdlibPackage != null) {
						loadASmetamodel(stdlibPackage);
					}
				}
			}
		}
		finally {
			asMetamodelLoadInProgress = false;
			return asMetamodel;
		}
	}

	@Override
	public @Nullable Iterable<@NonNull Object> getAllCompleteInvariants(@NonNull Type asType) {
		List<@NonNull Object> knownInvariantOrInvariants = null;
		Iterable<@NonNull CompleteClass> allSuperCompleteClasses = getAllSuperCompleteClasses(asType);
		for (CompleteClass superType : allSuperCompleteClasses) {
			Map<@NonNull String, @NonNull Object> name2invariantOrInvariants = null;
			List<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = ClassUtil.nullFree(superType.getPartialClasses());
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperType : partialClasses) {
				org.eclipse.ocl.pivot.Package partialPackage = partialSuperType.getOwningPackage();
				if (!(partialPackage instanceof PackageImpl) || !((PackageImpl)partialPackage).isIgnoreInvariants()) {
					for (@NonNull Constraint asInvariant : ClassUtil.nullFree(partialSuperType.getOwnedInvariants())) {
						if (name2invariantOrInvariants == null) {
							name2invariantOrInvariants = new HashMap<>();
						}
						String name = String.valueOf(asInvariant.getName());
						Object invariantOrInvariants = name2invariantOrInvariants.get(name);
						if (invariantOrInvariants == null) {
							name2invariantOrInvariants.put(name, asInvariant);
						}
						else if (invariantOrInvariants instanceof Constraint) {
							invariantOrInvariants = Lists.newArrayList((Constraint)invariantOrInvariants, asInvariant);
							assert invariantOrInvariants != null;
							name2invariantOrInvariants.put(name, invariantOrInvariants);
						}
						else {
							@SuppressWarnings("unchecked")
							List<@NonNull Constraint> asInvariants = (List<@NonNull Constraint>)invariantOrInvariants;
							asInvariants.add(asInvariant);
						}
					}
				}
			}
			if (name2invariantOrInvariants != null) {
				if (knownInvariantOrInvariants == null) {
					knownInvariantOrInvariants = new ArrayList<>();
				}
				List<@NonNull String> names = new ArrayList<>(name2invariantOrInvariants.keySet());
				if (names.size() > 1) {
					Collections.sort(names);
				}
				for (@NonNull String name : names) {
					Object invariantOrInvariants = name2invariantOrInvariants.get(name);
					assert invariantOrInvariants != null;
					if (invariantOrInvariants instanceof Constraint) {
						knownInvariantOrInvariants.add(invariantOrInvariants);
					}
					else {
						@SuppressWarnings("unchecked")
						List<@NonNull Constraint> asInvariants = (List<@NonNull Constraint>)invariantOrInvariants;
						Collections.sort(asInvariants, ConstraintExecutabilityComparator.INSTANCE);
						knownInvariantOrInvariants.add(asInvariants);
					}
				}
			}
		}
		return knownInvariantOrInvariants;
	}

	@Override
	public @NonNull Iterable<@NonNull CompletePackage> getAllCompletePackages() {
		if ((asMetamodel == null) && !asMetamodelLoadInProgress) {
			getASmetamodel();
		}
		return completePackageId2completePackage.values();
	}

//	@Override
//	public @NonNull Iterable<@NonNull CompletePackage> getAllCompletePackagesWithUris() {
//		return packageURI2completePackage.values();
//	}

	/**
	 * Return all constraints applicable to a type and its superclasses.
	 *
	 * @deprecated use CompleteModel.getAllCompleteInvariants()
	 */
	@Override @Deprecated
	public @NonNull Iterable<Constraint> getAllInvariants(@NonNull Type pivotType) {
		List<Constraint> knownInvariants = new ArrayList<>();
		for (CompleteClass superType : getAllSuperCompleteClasses(pivotType)) {
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperType : ClassUtil.nullFree(superType.getPartialClasses())) {
				org.eclipse.ocl.pivot.Package partialPackage = partialSuperType.getOwningPackage();
				if (!(partialPackage instanceof PackageImpl) || !((PackageImpl)partialPackage).isIgnoreInvariants()) {
					knownInvariants.addAll(partialSuperType.getOwnedInvariants());
				}
			}
		}
		assert new HashSet<>(knownInvariants).size() == knownInvariants.size();
		return knownInvariants;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter) {
		CompleteClass completeClass = getCompleteClass(type);
		return completeClass.getOperations(featureFilter);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter, @NonNull String name) {
		CompleteClass completeClass = getCompleteClass(type);
		return completeClass.getOperations(featureFilter, name);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Iterable<? extends Property> getAllProperties(@NonNull Property pivotProperty) {
//		FlatClass pivotClass = pivotProperty.getFlatClass(standardLibrary);
		org.eclipse.ocl.pivot.Class pivotClass = pivotProperty.getOwningClass();
/*		if (owningType != null) {
			return standardLibrary.getInheritance(owningType);
		}
		else {
			return null;
		}
		CompleteInheritance owningInheritance = pivotProperty.getInheritance(standardLibrary); */
		if (pivotClass == null) {
			throw new IllegalStateException("Missing owning type");
		}
		CompleteClass completeClass = getCompleteClass(pivotClass/*.getPivotClass()*/);
		Iterable<? extends Property> memberProperties = completeClass.getProperties(pivotProperty.getName());
		if (memberProperties != null) {
			return memberProperties;
		}
		return Collections.singletonList(pivotProperty);
	}

	@Override
	public @NonNull Iterable<@NonNull CompleteClass> getAllSuperCompleteClasses(@NonNull Type type) {
		CompleteClass completeClass = getCompleteClass(type);
		return completeClass.getSuperCompleteClasses();
	}

/*	public @Nullable ExpressionInOCL getBodyExpression(@NonNull Operation operation) {
		ExpressionInOCL bodyExpression = null;
		for (@SuppressWarnings("null")@NonNull Operation domainOperation : getOperationOverloads(operation)) {
			LanguageExpression anExpression = domainOperation.getBodyExpression();
			if (anExpression != null) {
				if (bodyExpression != null) {
					throw new IllegalStateException("Multiple bodies for " + operation);
				}
				try {
					bodyExpression = environmentFactory.parseSpecification(anExpression);
				} catch (ParserException e) {
					String message = e.getMessage();
					if (message == null) {
						message = "";
					}
					logger.error(message);
					bodyExpression = PivotUtil.createExpressionInOCLError(message);
				}
			}
		}
		return bodyExpression;
	} */

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(@NonNull Type pivotType) {
		org.eclipse.ocl.pivot.@NonNull Class asClass = PivotUtil.getClass(pivotType, standardLibrary);
		return getCompleteClass(asClass);
	}

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof ElementExtension) {
			Stereotype stereotype = ((ElementExtension)asClass).getStereotype();
			if (stereotype != null) {
				asClass = stereotype;
			}
		}
		CompleteClassInternal completeClass = basicGetCompleteClass(asClass);
		if (completeClass != null) {
			return completeClass;
		}
		CompletePackage completePackage = getCompletePackage4(asClass);
		return (CompleteClassInternal)completePackage.getCompleteClass(asClass);
	}

	@Override
	public void getCompleteClasses(@NonNull ASResource asResource) {
		// XXX check called just once
		// XXX pass completePackage
		for (EObject eObject : asResource.getContents()) {
			if (eObject instanceof Model) {
				for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages((Model)eObject)) {
					getCompleteClasses(asPackage);
				}
			}
		}
	}

	@Override
	public void getCompleteClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage) {				// XXX migrate to lazy first getCompleteClass
		// XXX check called just once
		// XXX pass completePackage
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getOwnedClasses(asPackage)) {
			getCompleteClass(asClass);
		}
		for (org.eclipse.ocl.pivot.@NonNull Package asNestedPackage : PivotUtil.getOwnedPackages(asPackage)) {
			getCompleteClasses(asNestedPackage);
		}
	}

	@Override
	public @NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if ((asMetamodel == null) && !asMetamodelLoadInProgress) {
			getASmetamodel();
		}
		CompletePackage aT = package2completePackage.get(asPackage);
		return ClassUtil.requireNonNull(aT);
	}

	@Override
	public @NonNull CompletePackage getCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @Nullable String uri) {
		CompletePackage completePackage = completePackageId2completePackage.get(completePackageId);
		if (completePackage == null) {
			completePackage = createCompletePackage(completePackageId, prefix, uri);
	//		completePackageId2completePackage.put(completePackageName, completePackage);
			getOwnedCompletePackages().add(completePackage);
		}
		assert completePackage == completePackageId2completePackage.get(completePackageId);
	//	assert Objects.equals(prefix, completePackage.getNsPrefix());
	//	assert Objects.equals(uri, completePackage.getURI());
	//	completePackage.didAddPackageURI(packageURI);
	//	CompletePackage old = packageURI2completePackage.put(uri, completePackage);			// XXX remove
	//	assert (old == null) || (old == completePackage);
		return completePackage;
	}

	@Override
	public @Nullable CompletePackage getCompletePackage2(org.eclipse.ocl.pivot.@NonNull Package pivotPackage) {
		String packageURI = pivotPackage.getURI();
		if (packageURI == null) {
			return null;
		}
		URI semantics = PivotUtil.basicGetPackageSemantics(pivotPackage);
		String completePackageName;
		if (semantics != null) {
			completePackageName = semantics.trimFragment().toString();
		}
		else {
			completePackageName = getCompleteURI(packageURI);
		}
		if (completePackageName == null) {
			return null;
		}
		CompletePackageId completePackageId = IdManager.getCompletePackageId(completePackageName);
		return completePackageId2completePackage.get(completePackageId);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull CompletePackage getCompletePackage3(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		boolean packageAdded = false;
		CompletePackage completePackage = package2completePackage.get(asPackage);
		if (completePackage == null) {
			String packageURI = asPackage.getURI();
			if (packageURI != null) {			// QVT roots may be blank
				completePackage = packageURI2completePackage.get(packageURI);
			}
			if (completePackage == null) {
				CompletePackageId completePackageId = CompletePackageIdRegistryReader.basicGetCompletePackageId(packageURI);
				if (completePackageId == null) {
					URI semantics = PivotUtil.basicGetPackageSemantics(asPackage);
					if (semantics != null) {
						completePackageId = IdManager.getCompletePackageId(String.valueOf(semantics.trimFragment()));
					}
					else if (Orphanage.isOrphanage(asPackage)) {
						completePackageId = PivotConstants.ORPHANAGE_ID;
					}
					else if (packageURI != null) {			// QVT roots may be blank
						completePackageId = IdManager.getCompletePackageId(packageURI); // getCompleteURI(packageURI);
					}
					else {
						String packageName = PivotUtil.getName(asPackage);
						org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
						if (parentPackage != null) {
							CompletePackage parentCompletePackage = getCompletePackage3(parentPackage);
							completePackageId = IdManager.getCompletePackageId(parentCompletePackage, packageName);
						}
						else {
							completePackageId = IdManager.getCompletePackageId(packageName);
						}
					}
				}
				completePackage = completePackageId2completePackage.get(completePackageId);
				if (completePackage == null) {
					AbstractCompletePackages parentCompletePackages = ownedCompletePackages;
					org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
					if (parentPackage != null) {
						CompletePackage parentCompletePackage = getCompletePackage3(parentPackage);
						parentCompletePackages = ((CompletePackageImpl)parentCompletePackage).getOwnedCompletePackages();
					}
				//	completePackage.assertSamePackage(asPackage);		// XXX obsolete / rewrite
					if (Orphanage.isOrphanage(asPackage)) {
						completePackage = getOrphanCompletePackage();
						//	assert completePackageName.equals(completePackage.getName());
					//	assert PivotConstants.ORPHANAGE_NAME.equals(completePackage.getName());
					//	assert Objects.equals(asPackage.getNsPrefix(), completePackage.getNsPrefix());
						assert Objects.equals(packageURI, completePackage.getURI());
					}
					else {
						completePackage = createCompletePackage(completePackageId, asPackage.getNsPrefix(), packageURI);
					}
					package2completePackage.put(asPackage, completePackage);
					//		completePackageId2completePackage.put(completePackageName, completePackage);
					parentCompletePackages.add(completePackage);		//didAddCompletePackage
				//	didAddPackage(asPackage);
					assert completePackageId2completePackage.get(completePackageId) == completePackage;
					//	CompletePackage old1 = package2completePackage.put(asPackage, completePackage);
					//	CompletePackage old2 = completePackageId2completePackage.put(completePackageName, completePackage);
					assert package2completePackage.get(asPackage) == completePackage;
					completePackage.getPartialPackages().add(asPackage);
					packageAdded = true;
				//	}
				}
			//	if (packageURI != null) {
			//		completePackage.didAddPackageURI(packageURI);
			//	}
			}
		}
	//	completePackage.getPartialPackages().add(asPackage);
		if (!packageAdded) {								// Maybe folding an additional package into a CompletePackage found by name/URI.
			completePackage.getPartialPackages().add(asPackage);						// UML 2.5 recurses for nested packages mapping to a parent
			assert package2completePackage.get(asPackage) == completePackage;
		}
		if (!(completePackage instanceof PrimitiveCompletePackage)) {
			assert completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
			assert package2completePackage.get(asPackage) == completePackage;
		//	if (packageURI != null) {
		//		assert packageURI2completePackage.get(packageURI) == completePackage;
		//	}
		}


	//	assert (asPackage.getURI() == null) || (packageURI2completePackage.get(asPackage.getURI()) == completePackage);		in didAddPackage caller
		CompletePackage completePackage2 = completePackageId2completePackage.get(completePackage.getCompletePackageId());
		assert (completePackage2 == completePackage) || (completePackage2 == null);
		return completePackage;
	}

	private @NonNull CompletePackage getCompletePackage4(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CompletePackage completePackage;
		if (asClass instanceof PrimitiveType) {					// XXX ?? Any/Invalid/Void too ?? Collection/Lambda/Map/Tuple too
			completePackage = getPrimitiveCompletePackage();			// namespacelessCompletePackage
		}
		else if (asClass.eContainer() instanceof Orphanage) {			// XXX
			completePackage = getOrphanCompletePackage();
		}
		else if (/*(asClass instanceof IterableType) &&*/ (asClass.getUnspecializedElement() != null)) {
			completePackage = getOrphanCompletePackage();
		}
		else if ((asClass instanceof LambdaType) /*&& (((LambdaType)asClass).getContextType() != null)*/) {
			completePackage = getOrphanCompletePackage();
		}
		else {
			org.eclipse.ocl.pivot.Package pivotPackage = asClass.getOwningPackage();
			if (pivotPackage == null) {
				throw new IllegalStateException("type has no package");
			}
			completePackage = getCompletePackage3(pivotPackage);
		}
		return completePackage;
	}

	@Deprecated	// fold packageURI2completePackage.get() inline
	@Override
	public @Nullable String getCompleteURI(@Nullable String packageURI) {
		CompletePackage completePackage = packageURI2completePackage.get(packageURI);
		if (completePackage != null) {
			return completePackage.getURI();
		}
		else {
			return packageURI;
		}
	}

	@Override
	public @Nullable ExpressionInOCL getDefaultExpression(@NonNull Property property) {
		ExpressionInOCL defaultExpression = null;
		for (@SuppressWarnings("null")@NonNull Property domainProperty : getAllProperties(property)) {
			LanguageExpression anExpression = domainProperty.getOwnedExpression();
			if (anExpression != null) {
				if (defaultExpression != null) {
					throw new IllegalStateException("Multiple derivations for " + property);
				}
				try {
					defaultExpression = environmentFactory.parseSpecification(anExpression);
				} catch (ParserException e) {
					String message = e.getMessage();
					if (message == null) {
						message = "";
					}
					logger.error(message);
					defaultExpression = PivotUtil.createExpressionInOCLError(message);
				}
			}
		}
		return defaultExpression;
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return ClassUtil.requireNonNull(environmentFactory);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEquivalentClass(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Class thatClass) {
		CompleteClass completeClass = getCompleteClass(thatClass);					// Ensure thatPackage has a complete representation -- BUG 477342 once gave intermittent dispose() ISEs
		Model thatModel = PivotUtil.getContainingModel(thatClass);
		if ((thisModel == thatModel) || Orphanage.isOrphanage(thatModel)) {
			return thatClass;
		}
		org.eclipse.ocl.pivot.Package thatPackage = PivotUtil.getOwningPackage(thatClass);
		org.eclipse.ocl.pivot.Package thisPackage = getEquivalentPackage(thisModel, thatPackage);
		List<org.eclipse.ocl.pivot.Class> theseClasses = thisPackage.getOwnedClasses();
		String className = thatClass.getName();
	//	assert className != null;							// XXX Nameless classes such as UML Association cannot be opposites
		org.eclipse.ocl.pivot.Class thisClass = NameUtil.getNameable(theseClasses, className);
		if (thisClass != null) {
			return thisClass;
		}
		org.eclipse.ocl.pivot.Class asClass = completeClass.getPrimaryClass();
		thisClass = PivotUtil.createNamedElement(asClass);			// XXX what about template parameter??
		TemplateSignature thatSignature = asClass.getOwnedSignature();
		if (thatSignature != null) {
			TemplateSignature thisSignature = EcoreUtil.copy(thatSignature);
			thisClass.setOwnedSignature(thisSignature);
		}
		theseClasses.add(thisClass);
		completeClass.getPartialClasses().add(thisClass);			// XXX fudge why no package
		//	System.out.println("getEquivalentClass " + NameUtil.debugSimpleName(thatClass) +  " => " + NameUtil.debugSimpleName(thisClass) +  " " + thisClass.getName());
		return thisClass;
	}

	/**
	 * Return the equivalent package to thatPackage in thisModel, where equivalent is the same package name
	 * hierarchy wrt the orphan package in thisModel. This is typically used to create a merge contribution
	 * for thatClass in thisModel avoiding the need to modify thatClass.
	 * <br>
	 * i.e the equivalent of A::B::thatPackage in thatModel is $$::A::B::thatPackage in thisModel.
	 */
	private org.eclipse.ocl.pivot.@NonNull Package getEquivalentPackage(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Package thatPackage) {
		getCompletePackage(thatPackage);					// Ensure thatPackage has a complete representation
		Model thatModel = PivotUtil.basicGetContainingModel(thatPackage);
		if (thisModel == thatModel) {
			return thatPackage;
		}
		org.eclipse.ocl.pivot.Package thisParentPackage;
		org.eclipse.ocl.pivot.Package thatParentPackage = thatPackage.getOwningPackage();
		if (thatParentPackage == null) {
			thisParentPackage = Orphanage.getLocalOrphanPackage(thisModel);
		//	assert thisParentPackage.eResource().getResourceSet() != null; // xxxTables models have no Resource and so no ResourceSet
		}
		else {
			thisParentPackage = getEquivalentPackage(thisModel, thatParentPackage);
			assert thisParentPackage.eResource().getResourceSet() != null;
		}
		List<org.eclipse.ocl.pivot.@NonNull Package> thesePackages = PivotUtil.getOwnedPackagesList(thisParentPackage);
		String packageName = PivotUtil.getName(thatPackage);
		String packageURI = thatPackage.getURI();
		for (org.eclipse.ocl.pivot.@NonNull Package thisPackage : thesePackages) {
			if (packageURI.equals(thisPackage.getURI())) {
				return thisPackage;
			}
		}
		if (packageURI == null) {
			for (org.eclipse.ocl.pivot.@NonNull Package thisPackage : thesePackages) {
				if (packageName.equals(thisPackage.getName())) {
					return thisPackage;
				}
			}
			packageURI = "";
		}
		org.eclipse.ocl.pivot.Package thisPackage = PivotUtil.createPackage(packageName, thatPackage.getNsPrefix(), packageURI, thatPackage.getPackageId());
		thesePackages.add(thisPackage);
		return thisPackage;
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		org.eclipse.ocl.pivot.Class type1 = getPrimaryClass(type);
		org.eclipse.ocl.pivot.Class unspecializedType = type1.getUnspecializedElement();
		org.eclipse.ocl.pivot.Class theType = unspecializedType != null ? unspecializedType : type1;
		return getCompleteClass(theType).getFlatClass();
	}

	@Override
	public @NonNull Set<Map.@NonNull Entry<@NonNull String, @NonNull Namespace>> getGlobalNamespaces() {
		return globalNamespaces.entrySet();
	}

	@Override
	public @NonNull Iterable<@NonNull Type> getGlobalTypes() {
		return globalTypes;
	}

	@Override
	public @NonNull Iterable<@NonNull Constraint> getLocalInvariants(org.eclipse.ocl.pivot.@NonNull Class type) {
		CompleteClass completeClass = getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(type));
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(completeClass);
		return new CompleteElementInvariantsIterable(partialClasses);
	}

	@Override
	public @NonNull Iterable<@NonNull Operation> getMemberOperations(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic) {
		CompleteClass completeClass = getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(type));
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(completeClass);
		return new CompleteTypeOperationsIterable(partialClasses, selectStatic);
	}

//	public @Nullable CompletePackage getMemberPackage(@NonNull String memberPackageName) {
//		return ownedCompletePackages.basicGetOwnedCompletePackage(memberPackageName);
//	}

	public @NonNull Iterable<? extends CompletePackage> getMemberPackages() {
		assert ownedCompletePackages != null;
		return ownedCompletePackages;
	}

/*	public @NonNull Iterable<? extends CompletePackage> getMemberPackages(org.eclipse.ocl.pivot.@NonNull Package pkg) {
		return getCompletePackage(pkg).getOwnedCompletePackages();
	} */

	@Override
	public @NonNull Iterable<@NonNull Property> getMemberProperties(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic) {
		CompleteClass completeClass = getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(type));
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(completeClass);
		return new CompleteClassPropertiesIterable(partialClasses, selectStatic);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return environmentFactory.getMetamodelManager();
	}

//	@Override
	/**
	 * @since 7.0
	 */
	public org.eclipse.ocl.pivot.Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		CompletePackage completePackage = getCompletePackage(domainPackage);
		CompletePackage memberPackage = completePackage.basicGetOwnedCompletePackage(name);
		return memberPackage != null ? memberPackage.getPrimaryPackage() : null;
	}

//	@Override
	/**
	 * @since 7.0
	 */
	public org.eclipse.ocl.pivot.Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		CompletePackage completePackage = getCompletePackage(domainPackage);
		return completePackage.getMemberType(name);
	}

	@Override
	public @NonNull Iterable<? extends Operation> getOperationOverloads(@NonNull Operation pivotOperation) {
		FlatClass flatClass = pivotOperation.getFlatClass(standardLibrary);
		if (flatClass == null) {
			throw new IllegalStateException("Missing owning type");
		}
		CompleteClass completeClass = getCompleteClass(flatClass.getPivotClass());
		Iterable<? extends Operation> operationOverloads = completeClass.getOperationOverloads(pivotOperation);
		if (operationOverloads != null) {
			return operationOverloads;
		}
		return Collections.singletonList(pivotOperation);
	}

	@Override
	public @NonNull OrphanCompletePackageImpl getOrphanCompletePackage()
	{
		OrphanCompletePackage orphanCompletePackage2 = orphanCompletePackage;
		if (orphanCompletePackage2 == null) {
			orphanCompletePackage2 = orphanCompletePackage = PivotFactory.eINSTANCE.createOrphanCompletePackage();
			ownedCompletePackages.add(orphanCompletePackage2);
		}
		return (OrphanCompletePackageImpl)orphanCompletePackage2;
	}

	@Override
	public @NonNull Orphanage getOrphanage() {
		Orphanage orphanage2 = orphanage;
		if (orphanage2 == null) {
			orphanage2 = orphanage = environmentFactory.getMetamodelManager().createOrphanage();
			@SuppressWarnings("unused") CompletePackage completePackage = getCompletePackage3(orphanage2);
		//	OrphanCompletePackageImpl orphanCompletePackage2 = getOrphanCompletePackage();
		//	package2completePackage.put(orphanage2, orphanCompletePackage2);
		//	packageURI2completePackage.put(PivotConstants.ORPHANAGE_URI, orphanCompletePackage2);
			Model orphanModel = PivotUtil.getContainingModel(orphanage2);
			didAddPartialModel(orphanModel);
		//	PartialPackages partialPackages = getOrphanCompletePackage().getPartialPackages();
		//	orphanage2.addPackageListener(partialPackages);
		//	for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(orphanage2)) {
		//		didAddPackage(asPackage);
		//	}
		}
		return orphanage2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @Nullable CompletePackage getOwnedCompletePackage(/*@NonNull*/ String packageName) {
		assert packageName != null;
		assert ownedCompletePackages != null;
		return ownedCompletePackages.basicGetOwnedCompletePackage(packageName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull List<CompletePackage> getOwnedCompletePackages() {
		assert ownedCompletePackages != null;
		return ownedCompletePackages;
	}

	@Override
	public @NonNull Iterable<org.eclipse.ocl.pivot.Class> getPartialClasses(@NonNull Type pivotType) {
		CompleteClass completeClass = getCompleteClass(pivotType);
		return completeClass.getPartialClasses();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Reference types used by the auto-generated overridden body. - Bug 543180
	 * {@link EObjectResolvingEList}, {@link EObjectContainmentWithInverseEList}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull PartialModels getPartialModels() {
		assert partialModels != null;
		return partialModels;
	}

	@Override
	public @NonNull Iterable<? extends org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages(org.eclipse.ocl.pivot.@NonNull Package pkg, boolean loadASmetamodelFirst) {
		if (loadASmetamodelFirst && (asMetamodel == null)) {
			getASmetamodel();
		}
		CompletePackage completePackage = getCompletePackage(pkg);
		return ClassUtil.nullFree(completePackage.getPartialPackages());
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		if (/*(type instanceof Type) &&*/ !isTypeServeable(type)) {
			return type;
		}
		String instanceClassName = type.getInstanceClassName();
		if ((instanceClassName != null) && instanceClassName.equals(java.util.Map.Entry.class.getName())) {		// FIXME a fudge to avoid UML's profile for EStringToStringMapEntry being used
			return type;
		}
		return getCompleteClass(type).getPrimaryClass();
		//		TypeTracker typeTracker = packageManager.findTypeTracker(pivotType);
		//		if (typeTracker != null) {
		//			return typeTracker.getPrimaryType();
		//		}
		//		else {
		//			return pivotType;
		//		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public @NonNull <T extends EObject> T getPrimaryElement(@NonNull T element) {
		if (element instanceof Operation) {
			return (T) getPrimaryOperation((Operation)element);
		}
		else if (element instanceof org.eclipse.ocl.pivot.Package) {
			return (T) getPrimaryPackage((org.eclipse.ocl.pivot.Package)element);
		}
		else if (element instanceof Property) {
			return (T) getPrimaryProperty((Property)element);
		}
		else if (element instanceof Type) {
			return (T) getPrimaryType((Type)element);
		}
		return element;
	}

	@Override
	public @NonNull Operation getPrimaryOperation(@NonNull Operation pivotOperation) {
		FlatClass flatClass = pivotOperation.getFlatClass(standardLibrary);
		if (flatClass != null) {					// Null for an EAnnotation element
			CompleteClass completeClass = getCompleteClass(flatClass.getPivotClass());		// XXX why use FlatClass at all ??
			Operation operation = completeClass.getOperation(pivotOperation);
			if (operation != null) {
				return operation;
			}
		}
		return pivotOperation;
	}

	/**
	 * Lookup a primary package by its URI and optionally a sub-package path.
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getPrimaryPackage(@NonNull String nsURI, String... subPackagePath) {
		CompletePackage completePackage = basicGetCompletePackageForURI(nsURI);
		if (completePackage == null) {
			return null;
		}
		if (subPackagePath != null) {
			for (String subPackageName : subPackagePath) {
				if (subPackageName == null) {
					return null;
				}
				completePackage = completePackage.basicGetOwnedCompletePackage(subPackageName);
				if (completePackage == null) {
					return null;
				}
			}
		}
		return completePackage.getPrimaryPackage();
	}

	/**
	 * Lookup a primary sub-package.
	 *
	public @Nullable PackageServer getPrimaryPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String subPackageName) {
		PackageTracker packageTracker = packageManager.findPackageTracker(parentPackage);
		if (packageTracker != null) {
			return packageTracker.getPackageServer().getMemberPackage(subPackageName);
		}
		else {
			return PivotUtil.getNamedElement(parentPackage.getNestedPackage(), subPackageName);
		}
	} */

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPrimaryPackage(org.eclipse.ocl.pivot.@NonNull Package aPackage) {
		return ClassUtil.requireNonNull(getCompletePackage(aPackage).getPrimaryPackage());
	}

	@Override
	public @NonNull Property getPrimaryProperty(@NonNull Property pivotProperty) {
		if (pivotProperty.eContainer() instanceof TupleType) {		// FIXME Find a better way
			return pivotProperty;
		}
		if (pivotProperty.isIsImplicit()) {
			Property opposite = pivotProperty.getOpposite();
			if ((opposite != null) && !opposite.isIsImplicit()) {
				return PivotUtil.getOpposite(getPrimaryProperty(opposite));
			}
		}
//		FlatClass owningInheritance = pivotProperty.getFlatClass(standardLibrary);
		org.eclipse.ocl.pivot.Class pivotClass = pivotProperty.getOwningClass();
		if (pivotClass == null) {
			return pivotProperty;
		}
		String name = PivotUtil.getName(pivotProperty);
		CompleteClass completeClass = getCompleteClass(pivotClass/*owningInheritance.getPivotClass()*/);
		Iterable<@NonNull Property> memberProperties = completeClass.getProperties(pivotProperty.isIsStatic() ? FeatureFilter.SELECT_STATIC : FeatureFilter.SELECT_NON_STATIC, name);
		if (Iterables.size(memberProperties) <= 1) {					// No ambiguity
			return memberProperties.iterator().next();					// use merged unambiguous result (not necessarily pivotProperty)
		}
		Property opposite = pivotProperty.getOpposite();
		if (opposite == null) {											// No opposite, first one must be ok
			return memberProperties.iterator().next();
		}
		String oppositeName = PivotUtil.getName(opposite);
		CompleteClass oppositeCompleteClass = getCompleteClass(PivotUtil.getOwningClass(opposite));
		for (@NonNull Property memberProperty : memberProperties) {
			Property memberOpposite = memberProperty.getOpposite();
			if (memberOpposite != null) {
				if (oppositeName.equals(memberOpposite.getName())) {
					CompleteClass memberOppositeCompleteClass = getCompleteClass(PivotUtil.getOwningClass(memberOpposite));
					if (oppositeCompleteClass == memberOppositeCompleteClass) {
						return memberProperty;							// First exact opposite is the primary
					}
				}
			}
		}
		return memberProperties.iterator().next();						// Fallback, first one must be ok
	}

	// FIXME ASBH This should probably disappear
	@Override
	public @NonNull Type getPrimaryType(@NonNull Type type) {
		if (/*(type instanceof Type) &&*/ !isTypeServeable(type)) {
			return type;			// FIXME bad cast
		}
		org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.@Nullable Class)type;
		CompleteClassInternal completeClass = getCompleteClass(asClass);
		assert completeClass.getPartialClasses().contains(asClass);		// XXX redundant
	//	CompleteClassInternal completeClass = completeModel.getCompleteClass(type);
	//	assert completeClass.getPartialClasses().contains(type);
		return completeClass.getPrimaryClass();
		//		TypeTracker typeTracker = packageManager.findTypeTracker(pivotType);
		//		if (typeTracker != null) {
		//			return typeTracker.getPrimaryType();
		//		}
		//		else {
		//			return pivotType;
		//		}
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getPrimaryType(@NonNull String nsURI, @NonNull String path, String... extraPath) {
		CompletePackage completePackage = basicGetCompletePackageForURI(nsURI);
		if (completePackage == null) {
			return null;
		}
		if ((extraPath == null) || (extraPath.length == 0)) {
			return completePackage.getMemberType(path);
		}
		else {
			completePackage = completePackage.basicGetOwnedCompletePackage(path);
			if (completePackage == null) {
				return null;
			}
			int iMax = extraPath.length-1;
			for (int i = 0; i < iMax; i++) {
				String subPackageName = extraPath[i];
				if (subPackageName == null) {
					return null;
				}
				completePackage = completePackage.basicGetOwnedCompletePackage(subPackageName);
				if (completePackage == null) {
					return null;
				}
			}
			String subPackageName = extraPath[iMax];
			if (subPackageName == null) {
				return null;
			}
			return completePackage.getMemberType(subPackageName);
		}
	}

	@Override
	public @NonNull PrimitiveCompletePackageImpl getPrimitiveCompletePackage()
	{
		PrimitiveCompletePackage primitiveCompletePackage2 = primitiveCompletePackage;
		if (primitiveCompletePackage2 == null) {

			primitiveCompletePackage2 = primitiveCompletePackage = PivotFactory.eINSTANCE.createPrimitiveCompletePackage();
			ownedCompletePackages.add(primitiveCompletePackage2);
		}
		return (PrimitiveCompletePackageImpl) primitiveCompletePackage2;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completePackageName) {
		throw new UnsupportedOperationException();
/*		CompletePackage completePackage = basicGetCompletePackage(completePackageId);
		if ((completePackage != null) && (completePackage.eContainer() == this)) {
			return completePackage.getPrimaryPackage();
		}
		return null;*//*completePackage = getOwnedCompletePackage(completeURIorName);
		return completePackage != null ? completePackage.getPrimaryPackage() : null;*/
	}

	@Override
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		assert standardLibrary != null;
		return standardLibrary;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Iterable<@NonNull CompleteClass> getSuperCompleteClasses(@NonNull CompleteClass completeClass) {
		return completeClass.getProperSuperCompleteClasses();
	}

	@Override
	public @NonNull CompleteModel init(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.standardLibrary = environmentFactory.getStandardLibrary();
		partialModels = new PartialModels(this);
		ownedCompletePackages = new RootCompletePackages(this);
		return this;
	}

	@Override
	public boolean isSuperClassOf(org.eclipse.ocl.pivot.@NonNull Class unspecializedFirstType, org.eclipse.ocl.pivot.@NonNull Class secondType) {
		CompleteClass firstCompleteClass = getCompleteClass(unspecializedFirstType);
		CompleteClass secondCompleteClass = getCompleteClass(secondType);
		return isSuperCompleteClassOf(firstCompleteClass, secondCompleteClass);
	}

	/**
	 * @since 7.0
	 */
	public boolean isSuperCompleteClassOf(@NonNull CompleteClass unspecializedFirstType, @NonNull CompleteClass secondType) {
		CompleteClass unspecializedSecondType = getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(secondType.getPrimaryClass()));	// FIXME cast
		//		org.eclipse.ocl.pivot.Class unspecializedSecondType = PivotUtil.getUnspecializedTemplateableElement(secondType);	// FIXME cast
		if (unspecializedFirstType == unspecializedSecondType) {
			return true;
		}
		for (CompleteClass superCompleteClass : getSuperCompleteClasses(unspecializedSecondType)) {
			if (isSuperCompleteClassOf(unspecializedFirstType, superCompleteClass)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isTypeServeable(@NonNull Type type) {
		//		if (pivotType .getUnspecializedElement() != null) {
		//			return false;
		//		}
		if (type.isTemplateParameter() != null) {
			return false;
		}
		//		if (pivotType instanceof UnspecifiedType) {
		//			return false;
		//		}
		if (type instanceof LambdaType) {
			return false;
		}
		//		if (pivotType instanceof TupleType) {
		//			return false;
		//		}
		if (type.eContainer() instanceof TemplateParameterSubstitution) {
			return false;
		}
		return true;
	}

	/**
	 * Load the Pivot Metamodel of the Pivot Model to accompany a given asLibrary.
	 *
	 * If this asLibrary has an Element type it is assumed to be a complete custom meta-model and it is used as such.
	 *
	 * Otherwise the built-in Pivot Metamodel is created with name, nsPrefix and nsURI determined by the given library.
	 *
	 * @param asLibrary
	 * @since 7.0
	 */
	protected void loadASmetamodel(org.eclipse.ocl.pivot.@NonNull Package asLibrary) {
		for (org.eclipse.ocl.pivot.@NonNull Package libPackage : getPartialPackages(asLibrary, false)) {
			if (NameUtil.getNameable(libPackage.getOwnedClasses(), PivotPackage.Literals.ELEMENT.getName()) != null) {
				setASmetamodel(libPackage);	// Custom meta-model
				return;
			}
		}
		Model asModel;
		org.eclipse.ocl.pivot.Package asPackage = null;
		Resource libraryResource = standardLibrary.getLibraryResource();
		if ((libraryResource instanceof ASResourceImpl.ImmutableResource) && ((ASResourceImpl.ImmutableResource)libraryResource).isCompatibleWith(OCLmetamodel.PIVOT_URI)) {
			asModel = OCLmetamodel.getDefaultModel();
			for (org.eclipse.ocl.pivot.Package asPartialPackage : PivotUtil.getOwnedPackages(asModel))	// Workaround the spurious implicit ecore package (fixed on a wip branch)
			{
				if (!PivotUtil.isImplicitPackage(asPartialPackage)) {
					asPackage = asPartialPackage;
					break;
				}
			}
			if (asPackage == null) {
				asPackage = asModel.getOwnedPackages().get(0);
			}
			assert asPackage != null;
		}
		else {
			String name = ClassUtil.requireNonNull(asLibrary.getName());
			asPackage = OCLmetamodel.create(standardLibrary, name, asLibrary.getNsPrefix(), OCLmetamodel.PIVOT_URI);
			asModel = (Model)asPackage.eContainer();
		}
		Resource asResource = asModel.eResource();
		assert asResource != null;
		environmentFactory.getASResourceSet().getResources().add(asResource);
		setASmetamodel(asPackage);		// Standard meta-model
		//		asResourceSet.getResources().add(asResource);
		((PivotMetamodelManager)getMetamodelManager()).installResource(asResource);
	}

	@Override
	public @NonNull CompletePackageId registerCompletePackageContribution(@NonNull String metamodelName, /*@NonNull*/ EPackage ePackage) {
		assert ePackage != null;
		CompletePackageId completePackageId = IdManager.getCompletePackageId(metamodelName);
		CompletePackage completePackage = getCompletePackage(completePackageId, ePackage.getNsPrefix(), metamodelName);
		String packageURI = ePackage.getNsURI();
		assert packageURI != null;
		completePackage.didAddPackageURI(packageURI);										// not "did"
		CompletePackage old = packageURI2completePackage.put(packageURI, completePackage);
		assert (old == null) || (old == completePackage);
		return completePackageId;
	}

	/**
	 * @since 7.0
	 */
	public void setASmetamodel(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		asMetamodel = asPackage;
		String packageURI = asMetamodel.getURI();
		if (packageURI != null) {
			URI semantics = PivotUtil.basicGetPackageSemantics(asPackage);
			if (semantics != null) {
				CompletePackage completePackage = getCompletePackage3(asPackage);
			//	completeModel.addPackageURI2completeURI(uri, semantics.trimFragment().toString());
			//	completeModel.registerCompletePackageContribution(completePackage, packageURI);			// XXX completePackage.add
			}
		}
	}

	/**
	 * @since 7.0
	 */
	public void setMetamodelNsURI(@NonNull String metaNsURI) {
		if ((asMetamodel == null) && !asMetamodelLoadInProgress) {
			//			if (StandardLibraryContribution.REGISTRY.get(metaNsURI) == null) {
			//				StandardLibraryContribution.REGISTRY.put(metaNsURI, new OCLstdlib.Loader());
			//			}
			//			setDefaultStandardLibraryURI(metaNsURI);
			getASmetamodel();
		}
		else if (!metaNsURI.equals(asMetamodel.getURI())) {
		//	completeModel.addPackageURI2completeURI(metaNsURI, PivotConstants.METAMODEL_NAME);
			getCompletePackage(PivotConstants.METAMODEL_ID, asMetamodel.getNsPrefix(), metaNsURI);
			//			throw new IllegalMetamodelException(asMetamodel.getNsURI(), metaNsURI);
		}
	}

	@Override
	public void setAutoLoadASmetamodel(boolean autoLoadASmetamodel) {
		this.autoLoadASmetamodel  = autoLoadASmetamodel;
	}
} //CompleteModelImpl
