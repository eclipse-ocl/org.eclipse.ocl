/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.CompleteClassImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.executor.CompleteReflectiveFragment;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class CompleteFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull CompleteClassInternal completeClass;

	public CompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClassImpl completeClass) {
		super(flatModel, NameUtil.getName(completeClass), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = completeClass;
	}

	@Override
	protected @Nullable List<@NonNull Property> computeDirectProperties() {
		List<@NonNull Property> asProperties = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(partialClass);
			asProperties = gatherDirectProperties(unspecializedType, asProperties);
		}
		asProperties = gatherMetaExtensions(asProperties);
		return asProperties;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		StandardLibrary standardLibrary = getStandardLibrary();
		CompleteModelInternal completeModel = completeClass.getCompleteModel();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {			// XXX getUnspecializedElement
				if (superFlatClasses == null) {
					superFlatClasses = new ArrayList<>();
				}
				CompleteClassInternal superCompleteClass = completeModel.getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(partialSuperClass));
				FlatClass superFlatClass = superCompleteClass.getFlatClass();
				if (!superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
					superFlatClasses.add(superFlatClass);
				}
			}
		}
		if (superFlatClasses == null) {
			org.eclipse.ocl.pivot.@NonNull Class oclAnyClass = standardLibrary.getOclAnyType();
			CompleteClass completeOclAnyClass = completeModel.getCompleteClass(oclAnyClass);
			FlatClass oclAnyFlatClass = completeOclAnyClass.getFlatClass();
			superFlatClasses = Collections.singletonList(oclAnyFlatClass);
		}
		return superFlatClasses;
	}

	@Override
	protected @NonNull FlatFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new CompleteReflectiveFragment(this, baseFlatClass);
	}

	protected @Nullable Set<@NonNull Stereotype> gatherExtendingStereotypes(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable Set<@NonNull Stereotype> extendingStereotypes) {
		assert PivotUtil.getUnspecializedTemplateableElement(asClass) == asClass;		// FIXME This is much than PartialClasses.initMemberProperties
		List<StereotypeExtender> extendedBys = asClass.getExtenders();
		if (extendedBys.size() > 0) {
			if (extendingStereotypes == null) {
				extendingStereotypes = new HashSet<@NonNull Stereotype>();
			}
			for (@NonNull StereotypeExtender typeExtension : ClassUtil.nullFree(extendedBys)) {
				Stereotype stereotype = typeExtension.getOwningStereotype();
				if (stereotype != null) {
					extendingStereotypes.add(stereotype);
				}
			}
		}
		return extendingStereotypes;
	}

	/**
	 * Copy the extension_XXX properties from MetaClass for use from the instance.
	 *
	 * FIXME surely these should be cloned to have the correct containment as part of UML2AS.
	 * @param asProperties
	 */
	protected @Nullable List<@NonNull Property> gatherMetaExtensions(@Nullable List<@NonNull Property> asProperties) {
		CompletePackageInternal rootCompletePackage = completeClass.getOwningCompletePackage().getRootCompletePackage();
		org.eclipse.ocl.pivot.Package pivotPackage = rootCompletePackage.getPrimaryPackage();
		if (pivotPackage != null) {
			org.eclipse.ocl.pivot.Class pivotClass = completeClass.getPrimaryClass();
			EnvironmentFactoryInternal environmentFactory = getEnvironmentFactory();
			PackageId metapackageId = environmentFactory.getTechnology().getMetapackageId(environmentFactory, pivotPackage);
			org.eclipse.ocl.pivot.Package metapackage = ((IdResolver.IdResolverExtension)environmentFactory.getIdResolver()).basicGetPackage(metapackageId);
			if (metapackage != null) {
				CompletePackage metaCompletePackage = environmentFactory.getMetamodelManager().getCompletePackage(metapackage);
				String metatypeName = pivotClass.eClass().getName();
				Type metatype = metaCompletePackage.getType(metatypeName);
				if (metatype != null) {
					CompleteClass metaCompleteClass = environmentFactory.getCompleteModel().getCompleteClass(metatype);
					for (@NonNull Property property : metaCompleteClass.getProperties(FeatureFilter.SELECT_EXTENSION)) {
						if (asProperties == null) {
							asProperties = new ArrayList<>();
						}
						asProperties.add(property);	// FIXME Clone the M2 property to have the correct M1 container/type
					}
				}
			}
		}
		return asProperties;
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		return completeClass;
	}

	@Override
	protected @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return completeClass.getEnvironmentFactory();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeClass.getPrimaryClass();
	}

	@Override
	public @NonNull String toString() {
		return NameUtil.qualifiedNameFor(completeClass);
	}

	@Override
	public void resetFragments() {
		completeClass.uninstall();
		super.resetFragments();
	}
}
