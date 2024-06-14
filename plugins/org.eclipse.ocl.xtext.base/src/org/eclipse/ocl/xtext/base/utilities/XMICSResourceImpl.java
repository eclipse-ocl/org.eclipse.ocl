package org.eclipse.ocl.xtext.base.utilities;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * XMICSResourceImpl is an experimental variation of XMIResourceImpl that ensures that references to AS elements
 * are serialized as equivlenat CS/AS references.
 *
 */
public class XMICSResourceImpl extends XMIResourceImpl// implements ASResource
{
	protected static final class XMICSHelper extends XMIHelperImpl
	{
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;


		protected XMICSHelper(XMLResource resource) {
			super(resource);
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert environmentFactory != null : "No EnvironmentFactory when CS-saving " + NameUtil.debugSimpleName(this);
			this.environmentFactory = environmentFactory;
		}

		@Override
		public String getHREF(EObject obj) {
			if (obj instanceof ElementImpl) {
				EObject esObject = ((ElementImpl)obj).getESObject();		// complete
				if (esObject != null) {
					return super.getHREF(esObject);
				}
				CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
				if (obj instanceof org.eclipse.ocl.pivot.Package) {
					CompletePackageInternal completePackage = completeModel.getCompletePackage((org.eclipse.ocl.pivot.Package)obj);
					for (org.eclipse.ocl.pivot.Package asPackage : completePackage.getPartialPackages()) {
						esObject = asPackage.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					}
				}
				else if (obj instanceof org.eclipse.ocl.pivot.Class) {
					CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					}
				}
				else if (obj instanceof Operation) {
					Operation asOperation = (Operation)obj;
					CompleteClassInternal completeClass = completeModel.getCompleteClass(asOperation.getOwningClass());
					Iterable<@NonNull Operation> asOverloads = completeClass.getOperationOverloads(asOperation);
					if (asOverloads != null) {
						for (Operation asOverload : asOverloads) {
							esObject = asOverload.getESObject();
							if (esObject != null) {
								return super.getHREF(esObject);
							}
						}
					}
					System.out.println("No Operation when CS-saving " + NameUtil.debugSimpleName(this));
				/*	CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					} */
				}
				else if (obj instanceof Property) {
					System.out.println("No Property when CS-saving " + NameUtil.debugSimpleName(this));
				/*	CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					} */
				}
					// Look for a specific CS
					ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
					if (csi2asMapping == null) {
						ASResourceImpl.PROXIES.println("No CSI2ASMapping when CS-saving  " + NameUtil.debugSimpleName(this));
					}
					else {
						EObject csElement = csi2asMapping.getCSElement((ElementImpl)obj);
						if (csElement != null) {		// XXX never happens CS is never externally referenced
							return super.getHREF(csElement);
						//	ASResourceImpl.PROXIES.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-cs " + uri);
						}
					}
/*						if (esNotifier == null) {
						// Else any old ES
						esNotifier = resolveESNotifier(environmentFactory.getCompleteModel());
					}
				}
				if (esNotifier instanceof EObject) {
					URI uri = EcoreUtil.getURI((EObject)esNotifier);
					eSetProxyURI(uri);
				}
				else if (esNotifier instanceof Resource) {
					URI uri = ((Resource)esNotifier).getURI();
					eSetProxyURI(uri);
				}
				else {
					ASResourceImpl.PROXIES.println("No esObject when proxifying " + NameUtil.debugSimpleName(this));
				} */
			}
			return super.getHREF(obj);
		}
	}

	/**
	 * Creates an instance of the resource.
	 */
	public XMICSResourceImpl(@NonNull URI uri) {
		super(uri);
	}

	/**
	 * @since 1.18
	 */
	@Override
	protected @NonNull XMIHelperImpl createXMLHelper() {
		return new XMICSHelper(this);
	}

	/**
	 * @since 1.18
	 *
	@Override
	protected @NonNull XMLSaveImpl createXMLSave() {
		return new XMLSaveImpl(createXMLHelper());
	} */

	@Override
	public @NonNull Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> defaultSaveOptions2 = defaultSaveOptions;
		if (defaultSaveOptions2 == null) {
			defaultSaveOptions = defaultSaveOptions2 = XMIUtil.createPivotSaveOptions();
		}
		return defaultSaveOptions2;
	}

/*	@SuppressWarnings("deprecation")
	@Override
	protected EObject getEObjectByID(String id) {
		if ((unloadingContents == null) && (idToEObjectMap == null)) { // Lazy xmi:id creation needed by generated ASResources
			AS2ID.assignIds(this, null);
		}
		if (idToEObjectMap == null) {
			return null;
		}
		EObject eObject = idToEObjectMap.get(id);
		if (eObject != null) {
			return eObject;
		}
		if (isLoading()) {
			return null;
		}
		// FIXME Use getXmiidVersion() to select appropriate algorithm
		Map<@NonNull String, @NonNull EObject> legacyXMIId2eObject2 = legacyXMIId2eObject;
		if (legacyXMIId2eObject2 == null) {
			org.eclipse.ocl.pivot.internal.utilities.AS2XMIid as2id = new org.eclipse.ocl.pivot.internal.utilities.AS2XMIid();
			legacyXMIId2eObject = legacyXMIId2eObject2 = new HashMap<>();
			for (EObject eObject2 : new TreeIterable(this)) {
				if (eObject2 instanceof Element) {
					Element element = (Element)eObject2;
					org.eclipse.ocl.pivot.utilities.AS2XMIidVisitor idVisitor = asResourceFactory.createAS2XMIidVisitor(as2id);
					Boolean status = element.accept(idVisitor);
					if (status == Boolean.TRUE) {
						String legacyId = idVisitor.toString();
						if (legacyId != null) {
							legacyXMIId2eObject2.put(legacyId,  eObject2);;
						}
					}
				}
			}
		}
		EObject eObject2 = legacyXMIId2eObject2.get(id);
		return eObject2;
	} */

	@Override
	protected boolean useIDs() {
		return true;
	}
}
