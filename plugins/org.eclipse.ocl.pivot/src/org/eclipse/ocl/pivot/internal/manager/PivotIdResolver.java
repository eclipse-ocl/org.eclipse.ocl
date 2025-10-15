/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.internal.CompleteModelImpl;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractIdResolver;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserException;

public class PivotIdResolver extends AbstractIdResolver
{
	private static final Logger logger = Logger.getLogger(PivotIdResolver.class);

	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull MetamodelManager metamodelManager;

	/**
	 * @since 7.0
	 */
	public PivotIdResolver(@NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory.getStandardLibrary());
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package addEPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		org.eclipse.ocl.pivot.Package asPackage = nsURI2package.get(nsURI);
		if (asPackage == null) {
			PackageId packageId = IdManager.getPackageId(ePackage);
			asPackage = metamodelManager.getASOfEcore(org.eclipse.ocl.pivot.Package.class, ePackage);
			if (asPackage == null) {
				asPackage = getPivotlessEPackage(ePackage);
				if (asPackage == null) {
					throw new IllegalStateException("EPackage " + ePackage.getName() + " : " + ePackage.getNsURI() + " has no Pivot counterpart.");
				}
			}
			nsURI2package.put(nsURI, asPackage);
			if (packageId instanceof RootPackageId) {
				assert roots2package != null;
				roots2package.put(((RootPackageId)packageId).getName(), asPackage);
			}
		}
		return asPackage;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull FlatClass getFlatClass(@NonNull EClassifier eClassifier) {
		return metamodelManager.getFlatClass(getType(eClassifier));
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type classType) {
		return environmentFactory.getMetaclass(classType);
	}

	@Override
	protected @NonNull Type getNestedClass(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		CompleteModelImpl completeModel = (CompleteModelImpl)environmentFactory.getCompleteModel();
		Type nestedType = completeModel.getNestedType(parentPackage, name);
		if (nestedType == null) {
			CompletePackage asParentCompletePackage = completeModel.getCompletePackage(parentPackage);
			CompleteClass nestedCompleteType = (CompleteClass) asParentCompletePackage.getType(name);
			nestedType = nestedCompleteType.getPrimaryClass();

			nestedType = completeModel.getNestedType(parentPackage, name);		// XXX debugging
			throw new UnsupportedOperationException();
		}
		return nestedType;
	}

	/**
	 * @since 7.0
	 */
	@Override
	protected @NonNull Type getNestedDataType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		CompleteModelImpl completeModel = (CompleteModelImpl)environmentFactory.getCompleteModel();
		Type nestedType = completeModel.getNestedType(parentPackage, name);
		if (nestedType == null) {
			nestedType = completeModel.getNestedType(parentPackage, name);
			if (nestedType == null) {
				throw new UnsupportedOperationException();
			}
		}
		return nestedType;
	}

	/**
	 * @since 7.0
	 */
	@Override
	protected @NonNull Type getNestedEnumeration(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		CompleteModelImpl completeModel = (CompleteModelImpl)environmentFactory.getCompleteModel();
		Type nestedType = completeModel.getNestedType(parentPackage, name);
		if (nestedType == null) {
			nestedType = completeModel.getNestedType(parentPackage, name);
			if (nestedType == null) {
				throw new UnsupportedOperationException();
			}
		}
		return nestedType;
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		CompleteModelImpl completeModel = (CompleteModelImpl)environmentFactory.getCompleteModel();
		org.eclipse.ocl.pivot.Package nestedPackage = completeModel.getNestedPackage(parentPackage, name);
		if (nestedPackage == null) {
			throw new UnsupportedOperationException();
		}
		return nestedPackage;
	}

	protected org.eclipse.ocl.pivot.@Nullable Package getPivotlessEPackage(@NonNull EPackage ePackage) {
		return null;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		return (CompleteStandardLibrary)super.getStandardLibrary();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getType(@NonNull EClassifier eClassifier) {
		EObject eType = eClassifier;
		EPackage ePackage = eClassifier.getEPackage();
		if (ePackage == PivotPackage.eINSTANCE){
			String typeName = eClassifier.getName();
			if (typeName != null) {
				org.eclipse.ocl.pivot.Package asMetamodel = metamodelManager.getASmetamodel();
				if (asMetamodel != null) {
					CompletePackage completePackage = metamodelManager.getCompletePackage(asMetamodel);
					org.eclipse.ocl.pivot.Class pivotType = completePackage.getMemberType(typeName);
					if (pivotType != null) {
						return pivotType;
					}
				}
			}
		}
		org.eclipse.ocl.pivot.Class pivotType;
		try {
			pivotType = environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, eType);
			if (pivotType != null) {
				return metamodelManager.getPrimaryClass(pivotType);
			}
		} catch (ParserException e) {
			logger.error("Failed to convert '" + eType + "'", e);
		}
		//		return new DomainInvalidTypeImpl(standardLibrary, "No object created by Ecore2AS");
		return environmentFactory.getStandardLibrary().getOclInvalidType();
	}

	@Override
	public @Nullable Object unboxedValueOf(@Nullable Object boxedValue) {
		if (boxedValue instanceof EnumerationLiteralId) {
			EnumerationLiteral enumerationLiteral = visitEnumerationLiteralId((EnumerationLiteralId)boxedValue);
			if (enumerationLiteral instanceof PivotObjectImpl) {
				EObject eTarget = ((PivotObjectImpl)enumerationLiteral).getESObject();
				//				if (eTarget instanceof EEnumLiteral) {				// Ecore unboxes to the Enumerator
				//					return ((EEnumLiteral)eTarget).getInstance();
				//				}
				//				else {												// UML unboxes to UML's EnumerationLiteral
				return eTarget;
				//				}
			}
			else {
				return enumerationLiteral;
			}
		}
		else {
			return super.unboxedValueOf(boxedValue);
		}
	}

	@Override
	public synchronized org.eclipse.ocl.pivot.@NonNull Package visitNsURIPackageId(@NonNull NsURIPackageId id) {
		String nsURI = id.getNsURI();
		org.eclipse.ocl.pivot.Package nsURIPackage = standardLibrary.getNsURIPackage(nsURI);
		if (nsURIPackage != null) {
			return nsURIPackage;
		}
		metamodelManager.setAutoLoadASmetamodel(true);
		org.eclipse.ocl.pivot.Package asMetamodel = metamodelManager.getASmetamodel();
		if ((asMetamodel != null) && PivotPackage.eNS_URI.equals(nsURI)) {
			return asMetamodel;
		}
		nsURIPackage = standardLibrary.getNsURIPackage(nsURI);
		if (nsURIPackage != null) {
			return nsURIPackage;
		}
		return super.visitNsURIPackageId(id);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package visitRootPackageId(@NonNull RootPackageId id) {
		CompletePackageId completePackageId = IdManager.getCompletePackageId(id.getName());
		CompletePackage completePackage = getStandardLibrary().basicGetCompletePackage(completePackageId);
		if (completePackage != null) {
			List<org.eclipse.ocl.pivot.Package> partialPackages = completePackage.getPartialPackages();
			if (partialPackages.size() > 0) {
				return partialPackages.get(0);
			}
			return null;					// XXX ??? try to load the/all packageURIs
		}
	//	org.eclipse.ocl.pivot.Package rootPackage = getStandardLibrary().basicGetCompletePackage(completePackageId);
		else {
			completePackage = getStandardLibrary().getCompleteModel().getCompletePackage(completePackageId, null, id.getName());
			throw new UnsupportedOperationException();
		//	Orphanage orphanage = environmentFactory.getOrphanage();
		//	rootPackage = NameUtil.getNameable(orphanage.getOwnedPackages(), completeURIorName);
		//	if (rootPackage == null) {
		//		return null;
		//	}
		}
	}
}
