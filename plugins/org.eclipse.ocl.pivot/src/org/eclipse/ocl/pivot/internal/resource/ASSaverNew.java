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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.Orphanage;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * ASSaverNew ensures that all references to synthesized types are terminated by local copies of the synthesized types.
 * This rewrite of ASSaver uses an EcoreUtil.Copier and EcoreUtil.CrossReferencer guaranteeing correct operation for all
 * references without requiring ASSaverLocateVisitor or ASSaverResolveVisitor derivations with accurate overloading for
 * all references. ASSaver may well be faster but it is fragile and the improved performance is not justified.
 * <br>
 * While a local orphanage is created for all the synthesized types, references to these types are not adjusted thereby
 * ensuring that the normal Resource content is not corrupted. Rather PivotXMISaveHelperImpl.getHRef invokes resolveOrphan
 * to emit a reference to the local copy rather than the original.
 *
 * @since 1.18
 */
public class ASSaverNew extends AbstractASSaver
{
	@SuppressWarnings("serial")
	protected static class ASSaverCopier extends EcoreUtil.Copier
	{
		private final @NonNull Model asModel;
	//	private @Nullable Orphanage sharedOrphanage = null;
		private org.eclipse.ocl.pivot.@Nullable Package localOrphanPackage = null;
		private @Nullable Map<@NonNull TypeId, @NonNull Type> typeId2localType = null;

		protected ASSaverCopier(@NonNull ASResource resource, boolean resolveProxies) {
			super(resolveProxies);
			System.out.println("ASSaverCopier " + NameUtil.debugSimpleName(this));
			this.asModel = PivotUtil.getModel(resource);
			org.eclipse.ocl.pivot.Package localOrphanPackage2 = this.localOrphanPackage = Orphanage.basicGetOrphanPackage(asModel);
			if (localOrphanPackage2 != null) {		// Pre-existing orphan content 'copies' to itself.
				Map<@NonNull TypeId, @NonNull Type> typeId2localType2 = typeId2localType = new HashMap<>();
				for (EObject eObject : new TreeIterable(localOrphanPackage2, false)) {
					put(eObject, eObject);
					if (eObject instanceof Type) {
						Type localType = (Type)eObject;
						typeId2localType2.put(localType.getTypeId(), localType);
					}
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
			org.eclipse.ocl.pivot.Package localOrphanPackage2 = localOrphanPackage;
			if (localOrphanPackage2 != null) {
				ECollections.sort((EList<org.eclipse.ocl.pivot.@NonNull Class>)localOrphanPackage2.getOwnedClasses(), new ClassByTypeIdAndEntryClassComparator());
			}
			for (EObject eSource : keySet()) {
				String s = eSource.toString();
				if (s.contains("Sequence(String)") || s.contains("Sequence<$0:String,$1:true,$2:0,$3:*>")) {
					EObject eTarget = get(eSource);
					System.out.println("copyReferences " + NameUtil.debugSimpleName(this) + " "  + NameUtil.debugSimpleName(eSource) + " : " + eSource + "\n\t=> " + NameUtil.debugSimpleName(eTarget) + " : " + eTarget);
				}
			}
		}

	/*	private void debugLocalization() {
			Map<EObject, Collection<Setting>> object2references = EcoreUtil.CrossReferencer.find(Collections.singletonList(asModel));
			for (EObject referencedObject : object2references.keySet()) {
				Collection<Setting> settings = object2references.get(referencedObject);
				assert settings != null;
				org.eclipse.ocl.pivot.Package containingOrphanPackage = Orphanage.basicGetContainingOrphanPackage(referencedObject);
				if ((containingOrphanPackage != null) && (containingOrphanPackage != localOrphanPackage)) {
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
		} */

		private org.eclipse.ocl.pivot.@NonNull Package getLocalOrphanPackage() {
			org.eclipse.ocl.pivot.Package localOrphanPackage2 = localOrphanPackage;
			if (localOrphanPackage2 == null) {
				localOrphanPackage = localOrphanPackage2 = Orphanage.createOrphanagePackage();
				asModel.getOwnedPackages().add(localOrphanPackage2);
				typeId2localType = new HashMap<>();
			}
			return localOrphanPackage2;
		}

		/**
		 * Prepare a pivot resource for save by redirecting all type references to shared orphans to local copies of the orphans.
		 */
		private @Nullable Collection<@NonNull EObject> localize(@NonNull EObject remoteObject, @NonNull Map<EObject, Collection<Setting>> remote2references, @Nullable Collection<@NonNull EObject> moreReferencingObjects) {
			TypeId typeId = remoteObject instanceof Type ? ((Type)remoteObject).basicGetTypeId() : null;
			if ("Collection<$0:'http://www.eclipse.org/qvtd/xtext/qvtcore/tests/UML2RDBMS/1.0/SimpleRDBMS'::Column,$1:true,$2:0,$3:*>".equals(String.valueOf(typeId))) {
				getClass();	// XXX
			}
			org.eclipse.ocl.pivot.Package orphanPackage = Orphanage.basicGetContainingOrphanPackage(remoteObject);
			if ((orphanPackage == null) || (orphanPackage == localOrphanPackage)) {
				return moreReferencingObjects;
			}
			final EObject localObject;
			EObject remoteContainer = remoteObject.eContainer();
			assert remoteContainer != null;
			if (remoteContainer != orphanPackage) {
				moreReferencingObjects = localize(remoteContainer, remote2references, moreReferencingObjects);
				localObject = get(remoteObject);
				assert localObject != null;
				System.out.println("localize-child " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " " + remoteObject + " : " + NameUtil.debugSimpleName(typeId) + " " + typeId);
			}
			else {
				org.eclipse.ocl.pivot.Package localOrphanPackage = getLocalOrphanPackage();
				Map<@NonNull TypeId, @NonNull Type> typeId2localType2 = typeId2localType;
				assert typeId2localType2 != null;
				EObject localObject2 = typeId2localType2.get(typeId);//get(remoteObject);
				if (localObject2 != null) {
					localObject = localObject2;
					System.out.println("localize-again " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " " + remoteObject + " : " + NameUtil.debugSimpleName(typeId) + " " + typeId);
				}
				else {
					localObject = copy(remoteObject);
					assert (localObject != null) && (localObject != remoteObject);
					System.out.println("localize-fresh " + NameUtil.debugSimpleName(localObject) + " : " + localObject + "\n\t=> " + NameUtil.debugSimpleName(remoteObject) + " " + remoteObject + " : " + NameUtil.debugSimpleName(typeId) + " " + typeId);
					Collection<@NonNull EObject> moreReferencingObjects2 = moreReferencingObjects;
					if (moreReferencingObjects2 == null) {
						moreReferencingObjects = moreReferencingObjects2 = new ArrayList<>();
					}
					moreReferencingObjects2.add(remoteObject);
					assert localObject instanceof org.eclipse.ocl.pivot.Class;
					org.eclipse.ocl.pivot.Class localType = (org.eclipse.ocl.pivot.Class)localObject;
					assert localType.basicGetTypeId() == null;
					localOrphanPackage.getOwnedClasses().add(localType);
					assert typeId != null;
					typeId2localType2.put(typeId, localType);
				}
			}
			// NB Do not relocate references to access the copies; this would corrupt the Resource,
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
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : PivotUtil.getOwnedClasses(sharedOrphanage.getPackage())) {
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
	//	copier.debugLocalization();
		// Could prune dead content left over from pre-existing content
	}

	/**
	 * Return the localized variant of eObject. If eObject is an orphan, localizeSpecializations should have created
	 * a local copy that is returned here. Else returns eObject.
	 */
	public @Nullable EObject resolveOrphan(@NonNull EObject eObject) {
		EObject localEObject = copier.get(eObject);
		String s = String.valueOf(eObject);
		if (s.contains("Sequence(String)") || s.contains("Sequence<$0:String,$1:true,$2:0,$3:*>")) {
			System.out.println("resolveOrphan " + NameUtil.debugSimpleName(copier) + " "   + NameUtil.debugSimpleName(eObject) + " : " + eObject + "\n\t=> " + NameUtil.debugSimpleName(localEObject) + " : " + localEObject);
		}
		EObject eObject2 = localEObject != null ? localEObject : eObject;
	//	Model containingModel = PivotUtil.getContainingModel(eObject2);
	//	assert (containingModel == null) || !Orphanage.isOrphanage(containingModel);		// ElementLiteralExp references may be anywhere.
		return eObject2;
	}
}