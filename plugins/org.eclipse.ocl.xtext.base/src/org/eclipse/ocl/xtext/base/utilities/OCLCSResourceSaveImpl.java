package org.eclipse.ocl.xtext.base.utilities;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * OCLCSResourceSaveImpl is an experimental variation of XMIResourceImpl that supports saving of a regular Xtext CSResource as XMI.
 * It ensures that references to AS elements within the XMI are serialized as equivalent CS/AS references.
 */
public class OCLCSResourceSaveImpl extends XMIResourceImpl
{
	protected static final class OCLCSHelper extends XMIHelperImpl
	{
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;

		protected OCLCSHelper(XMLResource resource) {
			super(resource);
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			assert environmentFactory != null : "No EnvironmentFactory when CS-saving " + NameUtil.debugSimpleName(this);
			this.environmentFactory = environmentFactory;
		}

		@Override
		public String getHREF(EObject obj) {
			String s = getHREF2(obj);
			if (s.contains("oclas")) {		// XXX debugging bad href
				s = getHREF2(obj);
			}
			return s;
		}

		private String getHREF2(EObject obj) {		// XXX
			if (obj instanceof ElementImpl) {
				//	Use known ES
				EObject esObject = ((ElementImpl)obj).getESObject();
				if (esObject != null) {
					return super.getHREF(esObject);
				}
				//	Look for complete ES
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
				}
				else if (obj instanceof Property) {
				//	System.out.println("No Property when CS-saving " + NameUtil.debugSimpleName(this)); // XXX
					CompleteClassInternal completeClass = completeModel.getCompleteClass(((Property)obj).getOwningClass());
					Iterable<@NonNull Property> asProperties = completeClass.getProperties((Property)obj);
					if (asProperties != null) {
						for (Property asProperty : asProperties) {
							esObject = asProperty.getESObject();
							if (esObject != null) {
								return super.getHREF(esObject);
							}
						}
					}
				}
			//	else if (obj instanceof ParameterVariable) {		// XXX
			//		obj = ((ParameterVariable)obj).getRepresentedParameter();
			//	}
				// Look for a specific CS
				ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
				if (csi2asMapping == null) {
					ASResourceImpl.PROXIES.println("No CSI2ASMapping when CS-saving  " + NameUtil.debugSimpleName(this));
				}
				else {
					EObject csElement = csi2asMapping.getCSElement((ElementImpl)obj);
					if (csElement != null) {		// XXX never happens CS is never externally referenced
						return super.getHREF(csElement);				// e.g. CS-defined implementation without Ecore
					//	ASResourceImpl.PROXIES.println("eSetProxyURI " + NameUtil.debugSimpleName(this) + " fixup-cs " + uri);
					}
				}
			}
			Resource resource = obj.eResource();
			if (resource instanceof ASResource) {
				return super.getHREF(obj);								// e.g. built-in oclstdlib-defined implementation without Ecore
			}
			else {
				return super.getHREF(obj);
			}
		}

		@Override
		public Object getValue(EObject obj, EStructuralFeature f) {
			Object value = super.getValue(obj, f);
			if (value instanceof ParameterVariable) {
				//
				//	ParameterVariable has no distinct CS equivalent so must reference its Parameter.
				//
				value = ((ParameterVariable)value).getRepresentedParameter();
			}
			return value;
		}
	}

	protected final @NonNull BaseCSResource csResource;

	public OCLCSResourceSaveImpl(@NonNull URI uri, @NonNull BaseCSResource csResource) {
		super(uri);
		this.csResource = csResource;
	}

	@Override
	protected @NonNull XMIHelperImpl createXMLHelper() {
		return new OCLCSHelper(this);
	}

	/**
	 * Return the top level resource contents delegating to the Xtext-friendly CSResource.
	 */
	@Override
	public @NonNull EList<@NonNull EObject> getContents() {
		return csResource.getContents();
	}

	@Override
	public @NonNull Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> defaultSaveOptions2 = defaultSaveOptions;
		if (defaultSaveOptions2 == null) {
			defaultSaveOptions = defaultSaveOptions2 = XMIUtil.createPivotSaveOptions();
		}
		return defaultSaveOptions2;
	}

	@Override
	protected boolean useIDs() {		// XXX ???
		return true;
	}
}
