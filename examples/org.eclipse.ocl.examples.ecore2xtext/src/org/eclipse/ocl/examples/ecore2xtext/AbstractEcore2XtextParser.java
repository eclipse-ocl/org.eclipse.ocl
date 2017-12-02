package org.eclipse.ocl.examples.ecore2xtext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;

public abstract class AbstractEcore2XtextParser
{
	public static String unquote(Object value) {
		String string = (String)value;
		int lengthMinusOne = string.length()-1;
		if (string.indexOf("&") < 0) {
			return string.substring(1, lengthMinusOne);
		}
		StringBuilder  s = new StringBuilder();
		for (int i = 1; i < lengthMinusOne; i++) {
			char c0 = string.charAt(i);
			if (c0 == '&') {
				int residue = lengthMinusOne - i - 1;
				if (residue >= 3) {
					char c1 = string.charAt(i+1);
					char c2 = string.charAt(i+2);
					char c3 = string.charAt(i+3);
					if ((c1 == 'g') && (c2 == 't') && (c3 == ';')) {
						s.append('>');
						i += 3;
						continue;
					}
					if ((c1 == 'l') && (c2 == 't') && (c3 == ';')) {
						s.append('<');
						i += 3;
						continue;
					}
					if (residue >= 4) {
						char c4 = string.charAt(i+4);
						if ((c1 == 'a') && (c2 == 'm') && (c3 == 'p') && (c4 == ';')) {
							s.append('&');
							i += 4;
							continue;
						}
						if (residue >= 5) {
							char c5 = string.charAt(i+5);
							if ((c1 == 'a') && (c2 == 'p') && (c3 == 'o') && (c4 == 's') && (c5 == ';')) {
								s.append('\'');
								i += 5;
								continue;
							}
							if ((c1 == 'q') && (c2 == 'u') && (c3 == 'o') && (c4 == 't') && (c5 == ';')) {
								s.append('"');
								i += 5;
								continue;
							}
						}
					}
				}
			}
			s.append(c0);
		}
		return s.toString();
	}

	protected @NonNull XMLResource xmlResource;
	protected EPackage.@NonNull Registry packageRegistry;
	private @NonNull String xmiVersion = null;
	protected final @NonNull Map<@NonNull String, @NonNull EPackage> xmlns2ePackage = new HashMap<>();
	private final @NonNull List<@NonNull SetEcoreReferenceFeature> references = new ArrayList<>();
	private final @NonNull Map<@NonNull String, EObject> uri2eObject = new HashMap<>();
	private int hits = 0;
	private int misses = 0;

	public AbstractEcore2XtextParser(@NonNull XMLResource xmlResource) {
		this.xmlResource = xmlResource;
		this.packageRegistry = xmlResource.getResourceSet().getPackageRegistry();
	}

	public void addReference(@NonNull SetEcoreReferenceFeature reference) {
		references.add(reference);
	}

	public EObject createEObject() {
		return EcoreFactory.eINSTANCE.createEObject();
	}

	public SetAttribute createEcoreClass(@NonNull EReference eReference, Object... setAttributes) {
		EClass xsiType = null;
		if (setAttributes != null) {
			for (Object setAttribute : setAttributes) {
				xsiType = ((SetAttribute)setAttribute).getXSIType();
				if (xsiType != null) {
					break;
				}
			}
		}
		if (xsiType == null) {
			EClass eReferenceType = eReference.getEReferenceType();
			if (eReferenceType.isAbstract()) {
				return null;
			}
			xsiType = eReferenceType;;
		}
		EFactory eFactoryInstance = xsiType.getEPackage().getEFactoryInstance();
		EObject eInstance = eFactoryInstance.create(xsiType);
		if (setAttributes != null) {
			for (Object setAttribute : setAttributes) {
				((SetAttribute)setAttribute).setAttribute(this, eInstance);
			}
		}
		return new SetEcoreContainedFeature(eReference, eInstance);
	}

	public SetAttribute createEcoreFeature(EStructuralFeature eFeature) {
		if (isMapFeature(eFeature)) {
			return new SetEcoreMapFeature(eFeature, getRhsSym(2));
		}
		else if (eFeature instanceof EAttribute) {
			return new SetEcoreAttributeFeature((EAttribute)eFeature, getRhsTokenText(3));
		}
		else {
			return new SetEcoreReferenceFeature((EReference)eFeature, getRhsTokenText(3));
		}
	}

	public Object createEcoreRoot(@NonNull EClass eClass, Object... setAttributes) {
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		EObject eInstance = eFactoryInstance.create(eClass);
		if (setAttributes != null) {
			for (Object setAttribute : setAttributes) {
				((SetAttribute)setAttribute).setAttribute(this, eInstance);
			}
		}
		xmlResource.getContents().add(eInstance);
		return eInstance;
	}

	public SetXMIAttribute createXMIAttribute(String name) {
		String xmiAttribute = getRhsTokenText(3);
		String xmiValue = unquote(getRhsSym(5));
		if ("version".equals(xmiAttribute)) {
			xmiVersion = xmiValue;
			return null;
		}
		else {
			return new SetXMIAttribute("version"/*getRhsSym(3)*/, xmiValue);
		}
	}

	public SetXMLNSAttribute createXMLNSAttribute() {
		String xmlnsAttribute = (String)getRhsSym(3);
		String xmlnsValue = unquote(getRhsSym(5));
		EPackage ePackage = packageRegistry.getEPackage(xmlnsValue);
		assert (ePackage != null) || "xmi".equals(xmlnsAttribute) || "xsi".equals(xmlnsAttribute);
		EPackage oldEPackage = xmlns2ePackage.put(xmlnsAttribute, ePackage);
		assert oldEPackage == null;
		return null;
	}

	public SetAttribute createXSIAttribute(String name) {
		String xsiAttribute = getRhsTokenText(3);
		String xsiValue = unquote(getRhsSym(5));
		if ("type".equals(xsiAttribute)) {
			String[] segments = xsiValue.split(":");
			assert segments.length == 2;
			String namespace = segments[0];
			String className = segments[1];
			EPackage ePackage = xmlns2ePackage.get(namespace);
			assert ePackage != null;
			EClassifier eClassifier = ePackage.getEClassifier(className);
			assert eClassifier instanceof EClass;
			return new SetXSITypeAttribute((EClass)eClassifier);
		}
		else {
			return new SetXSIAttribute(xsiAttribute, xsiValue);
		}
	}

	public Object createXMLDocument(Object... object) {
		Object rhsSym1 = getRhsSym(1);
		Object rhsSym2= getRhsSym(2);
		Object rhsSym3 = getRhsSym(3);
		Object rhsSym4 = getRhsSym(4);
		Object rhsSym5 = getRhsSym(5);
		return xmlResource;
	}

	protected abstract String getRhsTokenText(int i);

	public abstract Object getRhsSym(int i);

	protected boolean isMapFeature(EStructuralFeature eFeature) {
		EClassifier eType = eFeature.getEType();
		Class<?> instanceClass = eType.getInstanceClass();
		return java.util.Map.Entry.class.isAssignableFrom(instanceClass);
	}

	public void resolveReferences() {
		for (@NonNull SetEcoreReferenceFeature reference : references) {
			reference.resolveReference(this);
		}
	}

	public @NonNull EObject getEObject(int hashIndex, @NonNull String uriString) {
		EObject eObject = uri2eObject.get(uriString);
		if (eObject == null) {
			Resource resource;
			if (hashIndex > 0) {
				URI resourceURI = URI.createURI(uriString.substring(0, hashIndex), false);
				resource = xmlResource.getResourceSet().getResource(resourceURI, true);
			}
			else {
				resource = xmlResource;
			}
			if (resource != null) {
				String fragment = uriString.substring(hashIndex+1);
				eObject = resource.getEObject(fragment);
			}
			if (eObject == null) {
				eObject = EcoreFactory.eINSTANCE.createEObject();		// FIXME derived class
				((EObjectImpl)eObject).eSetProxyURI(URI.createURI(uriString));
			}
			uri2eObject.put(uriString, eObject);
			misses++;
		}
		else {
			hits++;
		}
		return eObject;
	}

	public void printStats() {
		System.out.println("uri2eObject hits = " + hits);
		System.out.println("uri2eObject misses = " + misses);
	}
}