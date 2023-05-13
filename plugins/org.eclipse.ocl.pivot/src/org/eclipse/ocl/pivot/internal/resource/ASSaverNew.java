/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.OrphanageImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * ASSaverNew ensures that all references to synthesized types are terminated by local copies of the synthesized types.
 * This rewrite of ASSaver uses an EcoreUtil.Copier and EcoreUtil.CrossReferencer guaranteeing correct operation for all
 * references without requiring ASSaverLOcateVisitor or ASSaverResolveVisitor derivations with accurate overloading for
 * all references. ASSaver may well be faster but it is fragile and the improved performance is not justified.
 *
 * @since 1.18
 */
public class ASSaverNew extends AbstractASSaver
{
/*	protected static class ClassByMonikerComparator implements Comparator<org.eclipse.ocl.pivot.@NonNull Class>
	{
		@Override
		public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
			String s1 = AS2Moniker.toString(o1);
			String s2 = AS2Moniker.toString(o2);
			return s1.compareTo(s2);
		}
	} */

	@SuppressWarnings("serial")
	protected static class ASSaverCopier extends EcoreUtil.Copier
	{
		private final @NonNull Model asModel;
		private @Nullable Orphanage sharedOrphanage = null;
		private @Nullable OrphanageImpl localOrphanage = null;

		protected ASSaverCopier(@NonNull ASResource resource, boolean resolveProxies) {
			super(resolveProxies);
			this.asModel = PivotUtil.getModel(resource);
			this.localOrphanage = (OrphanageImpl)OrphanageImpl.basicGetOrphanage(asModel);
			if (localOrphanage != null) {		// Pre-existing orphan content 'copies' to itself.
				for (EObject eObject : new TreeIterable(localOrphanage, false)) {
					put(eObject, eObject);
				}
			}
		}

		@Override
		public EObject copy(EObject eObject) {
			assert !(eObject instanceof TemplateParameter);		// Generalized class never needs localizing.
			return super.copy(eObject);
		}

		public void copyDefinitions(@Nullable Collection<@NonNull EObject> moreReferencingObjects) {
			while (moreReferencingObjects != null) {
				Map<EObject, Collection<Setting>> references = EcoreUtil.CrossReferencer.find(moreReferencingObjects);
				assert references != null;
				moreReferencingObjects = null;
				for (EObject eTarget : references.keySet()) {
					assert eTarget != null;
					moreReferencingObjects = localize(eTarget, references, moreReferencingObjects);
				}
			}
		}

		@Override
		protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
			if (eReference.isMany() && (eReference.getEOpposite() == null)) {
				@SuppressWarnings("unchecked")
				List<EObject> copyValues = (List<EObject>)copyEObject.eGet(eReference);
				copyValues.clear();						// Avoid duplicate superclasses when reloading
			}
			super.copyReference(eReference, eObject, copyEObject);
		}

		@Override
		public void copyReferences() {
			super.copyReferences();
			OrphanageImpl localOrphanage2 = localOrphanage;
			if (localOrphanage2 != null) {
				localOrphanage2.installProtoClasses();
				ECollections.sort((EList<org.eclipse.ocl.pivot.@NonNull Class>)localOrphanage2.getOwnedClasses(), new ClassByTypeIdAndEntryClassComparator());
			}
		}

		private void debugLocalization() {
			Map<EObject, Collection<Setting>> object2references = EcoreUtil.CrossReferencer.find(Collections.singletonList(asModel));
			for (EObject referencedObject : object2references.keySet()) {
				Collection<Setting> settings = object2references.get(referencedObject);
				assert settings != null;
				Orphanage containingOrphanage = PivotUtil.basicGetContainingOrphanage(referencedObject);
				if ((containingOrphanage != null) && (containingOrphanage != localOrphanage)) {
					for (Setting setting : settings) {
						EObject referencingObject = setting.getEObject();
						EStructuralFeature settingReference = setting.getEStructuralFeature();
					//	System.out.println("Not-localized: " + NameUtil.debugSimpleName(eSource) + " " + eSource + " " + settingReference.getEContainingClass().getName() + "::" + settingReference.getName() + " => " + NameUtil.debugSimpleName(eTarget) + " : " + eTarget);
						System.out.println("debugLocalization-bad " + NameUtil.debugSimpleName(referencingObject) + " : " + referencingObject + "::" + settingReference.getName() + "\n\t=> " + NameUtil.debugSimpleName(referencedObject) + " : " + referencedObject);
					//	NameUtil.errPrintln("Not-localized: " + NameUtil.debugSimpleName(eSource) + " " + eSource + " " + settingReference.getEContainingClass().getName() + "::" + settingReference.getName() + " => " + NameUtil.debugSimpleName(eTarget) + " : " + eTarget);
						throw new UnsupportedOperationException("Not-localized: " + NameUtil.debugSimpleName(referencingObject) + " " + referencingObject + " " + settingReference.getEContainingClass().getName() + "::" + settingReference.getName() + " => " + NameUtil.debugSimpleName(referencedObject) + " : " + referencedObject);
					//	getClass();		// XXX
					}
				}
			}
		}

		private @NonNull OrphanageImpl getLocalOrphanage() {
			OrphanageImpl localOrphanage2 = localOrphanage;
			if (localOrphanage2 == null) {
				localOrphanage = localOrphanage2 = new OrphanageImpl(sharedOrphanage != null ? sharedOrphanage.getStandardLibrary() : null);
				asModel.getOwnedPackages().add(localOrphanage2);
			}
			return localOrphanage2;
		}

		/**
		 * Prepare a pivot resource for save by redirecting all type references to shared orphans to local copies of the orphans.
		 */
		private @Nullable Collection<@NonNull EObject> localize(@NonNull EObject remoteObject, @NonNull Map<EObject, Collection<Setting>> remote2references, @Nullable Collection<@NonNull EObject> moreReferencingObjects) {
			Orphanage orphanage = PivotUtil.basicGetContainingOrphanage(remoteObject);
			if ((orphanage == null) || (orphanage == localOrphanage)) {
				return moreReferencingObjects;
			}
			final EObject localObject;
			EObject remoteContainer = remoteObject.eContainer();
			assert remoteContainer != null;
			if (remoteContainer != orphanage) {
				moreReferencingObjects = localize(remoteContainer, remote2references, moreReferencingObjects);
				localObject = get(remoteObject);
				assert localObject != null;
				System.out.println("localize-child " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " : " + remoteObject);
			}
			else {
				EObject localObject2 = get(remoteObject);
				if (localObject2 != null) {
					localObject = localObject2;
					System.out.println("localize-again " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " : " + remoteObject);
				}
				else {
					OrphanageImpl localOrphanage = getLocalOrphanage();
					localObject = copy(remoteObject);
					assert (localObject != null) && (localObject != remoteObject);
					System.out.println("localize-fresh " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " : " + remoteObject);
					Collection<@NonNull EObject> moreReferencingObjects2 = moreReferencingObjects;
					if (moreReferencingObjects2 == null) {
						moreReferencingObjects = moreReferencingObjects2 = new ArrayList<>();
					}
					moreReferencingObjects2.add(remoteObject);
					assert localObject instanceof org.eclipse.ocl.pivot.Class;
					localOrphanage.addProtoClass((org.eclipse.ocl.pivot.Class)localObject);
				}
			}
			Collection<Setting> settings = remote2references.get(remoteObject);
			if (settings != null) {
				for (Setting setting : settings) {
					EObject referencingObject = setting.getEObject();
					EStructuralFeature eReference = setting.getEStructuralFeature();
					if (!eReference.isDerived() && !eReference.isTransient()) {
						if (eReference.isMany()) {
							@SuppressWarnings("unchecked") List<EObject> referencedObjects = (List<EObject>)referencingObject.eGet(eReference);
							referencedObjects.replaceAll(new UnaryOperator<EObject>() {
								@Override
								public EObject apply(EObject t) {
									return t == remoteObject ? localObject : t;
								}
							});
						}
						else {
							referencingObject.eSet(eReference, localObject);
						}
					}
				}
			}
			return moreReferencingObjects;
		}
	}

	public static class ASSaverWithInverse extends ASSaverNew
	{
		private final @NonNull Map<@NonNull EObject, @NonNull EObject> target2source = new HashMap<>();

		public ASSaverWithInverse(@NonNull ASResource resource) {
			super(resource);
		}

		public @Nullable EObject basicGetSource(@NonNull EObject target) {
			return target2source.get(target);
		}

		@SuppressWarnings("serial")
		@Override
		protected @NonNull ASSaverCopier createCopier(@NonNull ASResource resource) {
			return new ASSaverCopier(resource, true)
			{
				@Override
				public EObject put(EObject key, EObject value) {
					assert (key != null) && (value != null);
					EObject old = target2source.put(value, key);
					assert old == null;
					return super.put(key, value);
				}

			};
		}

		public @NonNull EObject getSource(@NonNull EObject target) {
			return ClassUtil.nonNullState(target2source.get(target));
		}
	}

	protected static class ClassByTypeIdAndEntryClassComparator implements Comparator<org.eclipse.ocl.pivot.@NonNull Class>
	{
		@Override
		public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
			TypeId t1 = o1.getTypeId();
			TypeId t2 = o2.getTypeId();
			String s1 = t1.toString();
			String s2 = t2.toString();
			int compareTo = s1.compareTo(s2);
			if (compareTo != 0) {
				return compareTo;
			}
			if ((o1 instanceof MapType) && (o2 instanceof MapType)) {
				org.eclipse.ocl.pivot.Class ec1 = ((MapType)o1).getEntryClass();
				org.eclipse.ocl.pivot.Class ec2 = ((MapType)o2).getEntryClass();
				if (ec1 == null) {
					if (ec2 != null) {
						return -1;
					}
				}
				else {
					if (ec2 == null) {
						return 1;
					}
					else {
						t1 = ec1.getTypeId();
						t2 = ec2.getTypeId();
						s1 = t1.toString();
						s2 = t2.toString();
						compareTo = s1.compareTo(s2);
					}
				}
			}
			return compareTo;
		}
	}

	/**
	 * The mapping from shared orphanage elements to their local counterpart.
	 */
	private final @NonNull ASSaverCopier copier;

	public ASSaverNew(@NonNull ASResource resource) {
		super(resource);
		copier = createCopier(resource);
	}

	public @Nullable EObject basicGetTarget(@NonNull EObject source) {
		return copier.get(source);
	}

	protected @NonNull ASSaverCopier createCopier(@NonNull ASResource resource) {
		return new ASSaverCopier(resource, true);
	}

	public @NonNull EObject getTarget(@NonNull EObject source) {
		return ClassUtil.nonNullState(copier.get(source));
	}

	protected void loadOrphanage(org.eclipse.ocl.pivot.@NonNull Package localOrphanage, @NonNull Orphanage sharedOrphanage) {
		//
		//	Determine the global contents.
		//
		Map<@NonNull TypeId, org.eclipse.ocl.pivot.@NonNull Class> typeId2globalType = new HashMap<>();
		Map<@NonNull OperationId, @NonNull Operation> operationId2globalOperation = new HashMap<>();
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : PivotUtil.getOwnedClasses(sharedOrphanage)) {
			if (!PivotConstants.ORPHANAGE_NAME.equals(asClass.getName())) {
				TypeId typeId = asClass.getTypeId();
				org.eclipse.ocl.pivot.Class old = typeId2globalType.put(typeId, asClass);
				assert old == null;
			}
			else {
				for (@NonNull Operation asOperation : PivotUtil.getOwnedOperations(asClass)) {
					Operation old = operationId2globalOperation.put(asOperation.getOperationId(), asOperation);
					assert old == null;
				}
			}
		}
		//
		//	Map the local contents to the global.
		//
		for (org.eclipse.ocl.pivot.@NonNull Class asLocalClass : PivotUtil.getOwnedClasses(localOrphanage)) {
			if (!PivotConstants.ORPHANAGE_NAME.equals(asLocalClass.getName())) {
				org.eclipse.ocl.pivot.Class asGlobalClass = typeId2globalType.get(asLocalClass.getTypeId());
				if (asGlobalClass != null) {
					copier.put(asGlobalClass, asLocalClass);
				}
			}
			else {
				for (@NonNull Operation asLocalOperation : PivotUtil.getOwnedOperations(asLocalClass)) {
					Operation asGlobalOperation = operationId2globalOperation.get(asLocalOperation.getOperationId());
					if (asGlobalOperation != null) {
						copier.put(asGlobalOperation, asLocalOperation);
					}
				}
			}
		}
	}

	/**
	 * Prepare a pivot resource for save by transitively redirecting all type references of shared orphans to local copies of the orphans.
	 */
	public void localizeOrphans() {
	//	System.out.println("localizeOrphans " + NameUtil.debugSimpleName(asModel) + " : " + asModel);
	/*	OrphanageImpl localOrphanage = (OrphanageImpl) OrphanageImpl.basicGetOrphanage(asModel);
		ResourceSet resourceSet = resource.getResourceSet();
		Orphanage sharedOrphanage = resourceSet != null ? OrphanageImpl.basicGetSharedOrphanage(resourceSet) : null;
		if ((localOrphanage != null) && (sharedOrphanage != null)) {
			loadOrphanage(localOrphanage, sharedOrphanage);
		} */
		copier.copyDefinitions(resource.getContents());
		copier.copyReferences();
		copier.debugLocalization();
		// Could prune dead content left over from pre-existing content
	}

	/**
	 * Return the localized variant of eObject. If eObject is an orphan, localizeSpecializations should have created
	 * a local copy that is returned here. Else returns eObject.
	 */
	public @Nullable EObject resolveOrphan(@NonNull EObject eObject) {
		EObject localEObject = copier.get(eObject);
		EObject eObject2 = localEObject != null ? localEObject : eObject;
	//	Model containingModel = PivotUtil.getContainingModel(eObject2);
	//	assert (containingModel == null) || !Orphanage.isOrphanage(containingModel);		// ElementLiteralExp references may be anywhere.
		return eObject2;
	}
}