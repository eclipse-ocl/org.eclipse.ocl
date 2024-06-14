package org.eclipse.ocl.xtext.base.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.resource.AS2ID;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * XMICSResourceImpl is an experimental variation of XMIResourceImpl that ensures that references to AS elements
 * are serialized as equivlenat CS/AS references.
 *
 */
public class OCLCSResourceImpl extends XMIResourceImpl// implements ASResource
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
			String s = getHREF2(obj);
			if (s.contains("oclas")) {
				s = getHREF2(obj);
			}
			return s;
		}

		public String getHREF2(EObject obj) {		// XXX
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
					List<org.eclipse.ocl.pivot.Class> partialClasses = completeClass.getPartialClasses();
					Iterable<@NonNull Operation> asOverloads = completeClass.getOperationOverloads(asOperation);
					if (asOverloads != null) {
						for (Operation asOverload : asOverloads) {
							if (partialClasses.contains(asOverload.getOwningClass())) {
								esObject = asOverload.getESObject();
								if (esObject != null) {
									return super.getHREF(esObject);
								}
							}
						}
					}
				//	System.out.println("No Operation when CS-saving " + NameUtil.debugSimpleName(this));
				/*	CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					} */
				}
				else if (obj instanceof Property) {
					System.out.println("No Property when CS-saving " + NameUtil.debugSimpleName(this)); // XXX
				/*	CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					} */
				}
				else if (obj instanceof ParameterVariable) {
				//	System.out.println("No Property when CS-saving " + NameUtil.debugSimpleName(this)); // XXX
				/*	CompleteClassInternal completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)obj);
					for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
						esObject = asClass.getESObject();
						if (esObject != null) {
							return super.getHREF(esObject);
						}
					} */
					obj = ((ParameterVariable)obj).getRepresentedParameter();
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
			Resource resource = obj.eResource();
			if (resource instanceof ASResource) {
				String uriFragment = ((ASResource)resource).getURIFragment(obj);
				HashMap<@NonNull Object, @Nullable Object> options = new HashMap<>();
			//	LUSSIDs lussids = ((ASResource)resource).getLUSSIDs(options);
			//	lussids.toString();
				Map<@NonNull Object, @Nullable Object> saveOptions = options;
				if (options != null) {
					for (Object key : options.keySet()) {
						saveOptions.put(String.valueOf(key), options.get(key));
					}
				}
				Object optionNormalizeContents = saveOptions.get(ASResource.OPTION_NORMALIZE_CONTENTS);
				/*	if ((optionNormalizeContents != null) && Boolean.valueOf(optionNormalizeContents.toString())) {
					asSaver.normalizeContents();
					int capacity = INDEX_LOOKUP+1;
					List<@Nullable Object> lookupTable = new ArrayList<>(capacity);
					for (int i = 0; i < capacity; i++) {
						if (i == INDEX_LOOKUP) {
							lookupTable.add(new Lookup());
						}
						else {
							lookupTable.add(null);
						}
					}
					saveOptions.put(ClassUtil.nonNullState(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE), lookupTable);
				} */
			//	ResourceSet asResourceSet = asResource.getResourceSet();
			//	if (asResourceSet != null) {
			//		AS2ID.assignIds(asResourceSet.getResources(), saveOptions);
			//	}
			//	else if (asResource instanceof ASResource){
					AS2ID.assignIds((ASResource)resource, saveOptions);		// XXX just once, whole ResourceSet
			//	}
			//	super.init(asResource, saveOptions);
					return super.getHREF(obj);
			}
			else {
				return super.getHREF(obj);
			}
		}
	}

	protected final @Nullable BaseCSResource csResource;

	/**
	 * Creates an instance of the resource.
	 */
	public OCLCSResourceImpl(@NonNull URI uri) {
		this(uri, null);
	}

	public OCLCSResourceImpl(@NonNull URI uri, @Nullable BaseCSResource csResource) {
		super(uri);
		this.csResource = csResource;
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
	public @NonNull EList<@NonNull EObject> getContents() {
		return csResource != null ? csResource.getContents() : super.getContents();
	}

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
