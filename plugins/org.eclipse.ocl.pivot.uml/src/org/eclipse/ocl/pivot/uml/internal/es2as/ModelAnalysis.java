/*******************************************************************************
 * Copyright (c) 2014, 2026 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink (CEA List) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml.internal.es2as;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Lists;

/**
 * The ModelAnalysis captures the overall analysis of the UML M1 ProfileApplication and ElementExtensions.
 */
public class ModelAnalysis
{
	public static class ElementComparator implements Comparator<Element>
	{
		public static final @NonNull ElementComparator INSTANCE = new ElementComparator();

		@Override
		public int compare(Element o1, Element o2) {
			if (o1 instanceof NamedElement) {
				if (o2 instanceof NamedElement) {
					String n1 = ((NamedElement)o1).getName();
					String n2 = ((NamedElement)o2).getName();
					return ClassUtil.safeCompareTo(n1, n2);
				}
				else {
					return 1;
				}
			}
			else {
				if (o1 instanceof NamedElement) {
					return -1;
				}
				else {
					return o1.hashCode() - o2.hashCode();
				}
			}
		}
	}

	protected final UML2AS.@NonNull Outer converter;
	protected final @NonNull ProfileAnalysis profileAnalysis;
	protected final @NonNull EnvironmentFactory environmentFactory;

	/**
	 *	Map of all Profiles Applied to each Package, populated initially by the explicit ProfileApplications and expanded to cover the
	 *transitive applications.
	 */
	/**
	 * All the ProfileApplication elements.
	 */
	private final @NonNull List<@NonNull ProfileApplication> asProfileApplications = new ArrayList<>();
	//	private @Nullable Map<Profile, Set<Profile>> profile2allProfiles = new HashMap<>();

	/**
	 * All the Profiles that are applied to something.
	 */
	private final @NonNull Set<@NonNull Profile> appliedProfiles = new HashSet<>();

	/**
	 * Map from the each applied Profiles to all the profiles that are applied by the application of the profile.
	 */
	private final @NonNull Map<@NonNull Profile, @NonNull Set<@NonNull Profile>> appliedProfile2appliedProfileClosure = new HashMap<>();

	/**
	 * Map from the each package to all the profiles aplied to the package.
	 */
	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull Set<@NonNull Profile>> package2appliedProfileClosure = new HashMap<>();

	private final @NonNull Map<@NonNull EClass, @Nullable Type> eClass2metatype = new HashMap<>();

	/**
	 *	List of UML elements stereotyped by each UML stereotype application.
	 *	<p>
	 *	Note that the UML stereotype application object is an EDynamicObject unless the Profile has been genmodelled as
	 *	is the case for the standard UML profile(s).
	 */
	private @Nullable List<@NonNull EObject> umlStereotypeApplications = null;

	public ModelAnalysis(UML2AS.@NonNull Outer converter, @NonNull ProfileAnalysis profileAnalysis) {
		this.converter = converter;
		this.profileAnalysis = profileAnalysis;
		this.environmentFactory = converter.getEnvironmentFactory();
	}

	public void addProfile(@NonNull Profile asProfile) {
		appliedProfiles.add(asProfile);
	}

	public void addProfileApplication(@NonNull ProfileApplication asProfileApplication) {
		asProfileApplications.add(asProfileApplication);
		Profile asProfile = asProfileApplication.getAppliedProfile();
		if (asProfile != null) {
			appliedProfiles.add(asProfile);
		}
	}

	public void addStereotypeApplication(@NonNull EObject umlStereotypeApplication) {
		if (UML2AS.ADD_STEREOTYPE_APPLICATION.isActive()) {
			EClass eClass = umlStereotypeApplication.eClass();
			StringBuilder s = new StringBuilder();
			s.append("<");
			s.append(NameUtil.qualifiedNameFor(eClass));
			s.append(">");
			for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
				Object value = umlStereotypeApplication.eGet(eFeature);
				String name = eFeature.getName();
				if (!name.startsWith(UML2AS.STEREOTYPE_BASE_PREFIX) || (value != null)) {
					s.append(" " + name + "=" + NameUtil.qualifiedNameFor(value));
				}
			}
			UML2AS.ADD_STEREOTYPE_APPLICATION.println(s.toString());
		}
		List<@NonNull EObject> umlStereotypeApplications2 = umlStereotypeApplications;
		if (umlStereotypeApplications2 == null) {
			umlStereotypeApplications = umlStereotypeApplications2 = new ArrayList<>();
		}
		umlStereotypeApplications2.add(umlStereotypeApplication);
	//	EPackage ePackage = eClass.getEPackage();
	//	Resource eResource = ePackage.eResource();
	//	if (eResource != null) {
	//		//			converter.addImportedResource(eResource);	// -- leads to CCEs for the wrong ES2AS
	//	}
	}

	// XXX Migrate to ProfileAnalysis once CompleteClass in use.
	private void addStereotypeClosure(@NonNull List<@NonNull Stereotype> asStereotypes) {
		for (int i = 0; i < asStereotypes.size(); i++) {
			Stereotype asStereotype = asStereotypes.get(i);
			for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : PivotUtil.getSuperClasses(asStereotype)) {
				if ((asSuperClass instanceof Stereotype) && !asStereotypes.contains(asSuperClass)) {
					asStereotypes.add((Stereotype)asSuperClass);
				}
			}
		}
	}

	private void computeAppliedProfile2profileClosure() {
		//
		//	Determine the closure of all profiles for each actually applied profile.
		//
		for (@NonNull Profile asProfile : appliedProfiles) {
			Set<@NonNull Profile> asProfiles = new HashSet<>();
			computeProfileClosure(asProfiles, asProfile);
			appliedProfile2appliedProfileClosure.put(asProfile, asProfiles);
		}
	}

	/**
	 * Update element2elementExtension with an ElementExtension for each of the stereotypeApplications.
	 */
	private void computeExplicitElementExtensions(@NonNull Map<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> element2stereotype2extension,
			@NonNull Map<@NonNull EObject, @NonNull List<org.eclipse.uml2.uml.@NonNull Element>> umlStereotypeApplication2umlStereotypedElements,
			@NonNull Map<@NonNull Element, @NonNull List<@NonNull EObject>> asElement2umlStereotypeApplications) {
		for (@NonNull Entry<@NonNull Element, @NonNull List<@NonNull EObject>> entry : asElement2umlStereotypeApplications.entrySet()) {
			@NonNull Element asStereotypedElement = entry.getKey();
			@NonNull List<@NonNull EObject> umlStereotypeApplications = entry.getValue();
			Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = element2stereotype2extension.get(asStereotypedElement);
			if (stereotype2extension == null) {
				stereotype2extension = new HashMap<>();
				element2stereotype2extension.put(asStereotypedElement, stereotype2extension);
			}
			for (@NonNull EObject umlStereotypeApplication : umlStereotypeApplications) {
				//					EClass eClass = umlStereotypeApplication.eClass();
				List<org.eclipse.uml2.uml.@NonNull Element> umlStereotypedElements = umlStereotypeApplication2umlStereotypedElements.get(umlStereotypeApplication);
				assert umlStereotypedElements != null;
				Stereotype asStereotype = converter.resolveStereotype(umlStereotypeApplication, umlStereotypedElements);
				//				if (asStereotype == null) {
				//					asStereotype = converter.resolveStereotype(umlStereotypeApplication, umlStereotypedElements);		// FIXME debugging
				//				}
				if (asStereotype != null) {
					ElementExtension elementExtension = environmentFactory.getElementExtension(asStereotypedElement, asStereotype);
					converter.setOriginalMapping(elementExtension, umlStereotypeApplication);
					elementExtension.setIsApplied(true);
					stereotype2extension.put(asStereotype, elementExtension);
					if (UML2AS.ADD_ELEMENT_EXTENSION.isActive()) {
						UML2AS.ADD_ELEMENT_EXTENSION.println(elementExtension.toString());
					}
				}
			}
		}
	}

	/**
	 * Update element2elementExtension with an ElementExtension for each of the required ExtensionEnds for each of
	 * the profile applied to each of the packages using package2allProfiles to identiofuy the packages and their
	 * applied profiles and profile2requiredExtensionEnds to idenmtiofy the required ExtensionEnds for each profile.
	 *
	private void computeImplicitElementExtensions(@NonNull Map<Element, List<ElementExtension>> element2elementExtension,
			@NonNull Map<org.eclipse.ocl.pivot.Package, Set<Profile>> package2allProfiles,
			@NonNull Map<org.eclipse.uml2.uml.Profile, List<ExtensionEnd>> profile2requiredExtensionEnds) {
		for (org.eclipse.ocl.pivot.Package asPackage : package2allProfiles.keySet()) {
			Set<Profile> asPackageProfiles = package2allProfiles.get(asPackage);
			for (Profile asProfile : asPackageProfiles) {
				org.eclipse.uml2.uml.Profile umlProfile = (org.eclipse.uml2.uml.Profile) asProfile.getETarget();
				List<org.eclipse.uml2.uml.ExtensionEnd> requiredExtensionEnds = profile2requiredExtensionEnds.get(umlProfile);
				if (requiredExtensionEnds != null) {
					for (org.eclipse.uml2.uml.ExtensionEnd umlExtensionEnd : requiredExtensionEnds) {
						if (umlExtensionEnd != null) {
							org.eclipse.uml2.uml.Type umlStereotype = umlExtensionEnd.getType();
							if (umlStereotype != null) {
								Stereotype asStereotype = converter.getCreated(Stereotype.class, umlStereotype);
								if (asStereotype != null) {
									org.eclipse.uml2.uml.Property umlOtherEnd = umlExtensionEnd.getOtherEnd();
									if (umlOtherEnd != null) {
										org.eclipse.uml2.uml.Type umlStereotypedElement = umlOtherEnd.getType();
										if (umlStereotypedElement != null) {
											try {
												Type asStereotypedElement = metamodelManager.getPivotOf(Type.class, umlStereotypedElement);
												if (asStereotypedElement != null) {
													ElementExtension asElementExtension = metamodelManager.getElementExtension(asStereotypedElement, asStereotype);
	//												setOriginalMapping(asElementExtension, umlStereotype);
													asElementExtension.setIsApplied(true);
													List<ElementExtension> asElementExtensions = element2elementExtension.get(asStereotypedElement);
													if (asElementExtensions == null) {
														asElementExtensions = new ArrayList<>();
														element2elementExtension.put(asStereotypedElement, asElementExtensions);
													}
													asElementExtensions.add(asElementExtension);
													if (UML2AS.ADD_ELEMENT_EXTENSION.isActive()) {
														UML2AS.ADD_ELEMENT_EXTENSION.println(asElementExtension.toString());
													}
												}
											} catch (ParserException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	} */

	private void computePackage2AppliedProfileClosure() {
		//
		//	Create the closure of all profiles to each package for which any profile is applied
		//
		for (@NonNull ProfileApplication asProfileApplication : asProfileApplications) {
			org.eclipse.ocl.pivot.Package asPackage = asProfileApplication.getOwningPackage();
			if (asPackage != null) {
				Profile asProfile = asProfileApplication.getAppliedProfile();
				if (asProfile != null) {
					Set<@NonNull Profile> appliedProfileClosure1 = appliedProfile2appliedProfileClosure.get(asProfile);
					if (appliedProfileClosure1 != null) {
						Set<@NonNull Profile> appliedProfileClosure2 = package2appliedProfileClosure.get(asPackage);
						if (appliedProfileClosure2 == null) {
							appliedProfileClosure2 = new HashSet<>();
							package2appliedProfileClosure.put(asPackage, appliedProfileClosure2);
						}
						appliedProfileClosure2.addAll(appliedProfileClosure1);
					}
				}
			}
		}
	}

	private void computeProfileClosure(@NonNull Set<@NonNull Profile> allProfiles, @NonNull Profile asProfile) {
		if (allProfiles.add(asProfile)) {
			for (org.eclipse.ocl.pivot.Package asNestedPackage : asProfile.getOwnedPackages()) {
				if (asNestedPackage instanceof Profile) {
					computeProfileClosure(allProfiles, (Profile) asNestedPackage);
				}
			}
			for (org.eclipse.ocl.pivot.Package asImportedPackage : asProfile.getImportedPackages()) {
				if (asImportedPackage instanceof Profile) {
					computeProfileClosure(allProfiles, (Profile) asImportedPackage);
				}
			}
		}
	}

	/**
	 * Install implicit ElementExtensions for all the required ExtensionEnds in all the applied Profiles to the all the packages,
	 * and explicit ElementsExtenmsions for all the applied Stereotype applications.
	 * @param umlStereotypeApplications
	 */
	private void installElementExtensionPropertyValues(@NonNull Map<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> element2stereotype2extension,
			@NonNull Map<@NonNull EObject, @NonNull List<org.eclipse.uml2.uml.@NonNull Element>> umlStereotypeApplication2umlStereotypedElements) {
		//
		// Compute the list of UML stereotype application for each stereotyped pivot element.
		//
		Map<@NonNull Element, @NonNull List<@NonNull EObject>> asElement2umlStereotypeApplications = resolveStereotypeApplications(umlStereotypeApplication2umlStereotypedElements);
		//
		//	Compute and install the ElementExtensions from required ExtensionEnds and Stereotype applications
		//
		//		Map<Element, List<ElementExtension>> element2elementExtension = new HashMap<>();
		//			Map<org.eclipse.uml2.uml.Profile, List<ExtensionEnd>> profile2requiredExtensionEnds2 = profile2requiredExtensionEnds;
		//			if (profile2requiredExtensionEnds2 != null) {
		//				computeImplicitElementExtensions(element2elementExtension, package2allProfiles, profile2requiredExtensionEnds2);
		//			}
		//
		// Compute an explicit ElementExtension for each stereotype application.
		//
		computeExplicitElementExtensions(element2stereotype2extension, umlStereotypeApplication2umlStereotypedElements, asElement2umlStereotypeApplications);
		//
		//	Install all the ElementExtensions
		//
		for (@NonNull Entry<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> entry : element2stereotype2extension.entrySet()) {
			@NonNull Element asElement = entry.getKey();
			@NonNull Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = entry.getValue();
			assert stereotype2extension != null;
			List<@NonNull ElementExtension> newElementExtensions = new ArrayList<>(stereotype2extension.values());
			List<ElementExtension> oldElementExtensions = asElement.getOwnedExtensions();
			PivotUtil.refreshList(oldElementExtensions, true, newElementExtensions);
		}
		/*		if (UML2AS.ADD_ELEMENT_EXTENSION.isActive()) {
			StringBuffer s = new StringBuffer();
			List<Element> asElements = new ArrayList<>(element2elementExtension.keySet());
			Collections.sort(asElements, ElementComparator.INSTANCE);
			for (@SuppressWarnings("null")@NonNull Element asElement : asElements) {
				s.append("\n\t" + EcoreUtils.qualifiedNameFor(asElement));
				s.append(" " + ClassUtil.debugSimpleName(asElement));
				org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
				if (asPackage != null) {
					s.append(" - " + asPackage.getNsURI());
				}
				for (ElementExtension asElementExtension : asElement.getExtensions()) {
					s.append("\n\t\t" + asElementExtension);
					s.append(" " + ClassUtil.debugSimpleName(asElementExtension));
				}
			}
			UML2AS.ADD_ELEMENT_EXTENSION.println("Extensions per Type" + s.toString());
		} */
	}

	public void installStereotypes() {
		profileAnalysis.analyze();
		computeAppliedProfile2profileClosure();
		computePackage2AppliedProfileClosure();
		////				@SuppressWarnings("null")@NonNull Set<Profile> appliedProfileClosure = package2appliedProfileClosure.get(asPackage);
		//				Map<Type, Set<TypeExtension>> metatype2typeExtensions = profileAnalysis.computeMetatypes2typeExtensions(/*appliedProfileClosure*/);
		//				Map<Element, Map<Stereotype, ElementExtension>> element2stereotype2extension = new HashMap<>();
		//		for (org.eclipse.ocl.pivot.Package asPackage : package2appliedProfileClosure.keySet()) {
		//			if ((asPackage != null) /*&& !(asPackage instanceof Profile)*/) {
		Map<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> element2stereotype2extension = new HashMap<>();
		for (Entry<@NonNull Package, @NonNull Set<@NonNull Profile>> entry : package2appliedProfileClosure.entrySet()) {
			org.eclipse.ocl.pivot.@NonNull Package asPackage = entry.getKey();
			if (!(asPackage instanceof Profile)) {		// XXX why can't profiles be applied to profiles ??
				@NonNull Set<@NonNull Profile> appliedProfileClosure = entry.getValue();
				Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> metatype2typeExtensions = profileAnalysis.computeMetatypes2typeExtensions(appliedProfileClosure);
				printMetatypes2StereotypeExtensions(asPackage, metatype2typeExtensions);
				for (TreeIterator<@NonNull EObject> tit = asPackage.eAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof Element) {
						Element asElement = (Element)eObject;
						EClass eClass = asElement.eClass();
						Type metatype = eClass2metatype.get(eClass);
						if (metatype == null) {
							if (!eClass2metatype.containsKey(eClass)) {
								EPackage ePackage = eClass.getEPackage();
								String ePackageName = ePackage.getName();
								String eClassName = eClass.getName();
								if ((ePackageName != null) && (eClassName != null)) {
									metatype = profileAnalysis.getMetatype(ePackageName, eClassName);
								}
								eClass2metatype.put(eClass, metatype);
							}
						}
						else {
							Set<@NonNull StereotypeExtender> typeExtensions = metatype2typeExtensions.get(metatype);
							if (typeExtensions != null) {			// XXX Does this ever happen ??
								Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = installExtensions(asElement, typeExtensions);
								element2stereotype2extension.put(asElement, stereotype2extension);
							}
						}
					}
				}
			}
		}
		List<@NonNull EObject> umlStereotypeApplications2 = umlStereotypeApplications;
		if (umlStereotypeApplications2 != null) {
			Map<@NonNull EObject, @NonNull List<org.eclipse.uml2.uml.@NonNull Element>> umlStereotypeApplication2umlStereotypedElements = UML2ASUtil.computeAppliedStereotypes(umlStereotypeApplications2);
			installElementExtensionPropertyValues(element2stereotype2extension, umlStereotypeApplication2umlStereotypedElements);
		}
		//			Map<Metaclass<?>, List<Property>> metaclass2properties = new HashMap<>();
		//
		//	Install all the metaclass properties.
		//
		installExtensionProperties(element2stereotype2extension);
	}

	/**
	 * @since 7.0
	 */
	protected void installExtensionProperties(@NonNull Map<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> element2stereotype2extension) {
		Model thisModel = converter.basicGetPivotModel();
		assert thisModel != null;
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		List<@NonNull Element> elements = new ArrayList<>(element2stereotype2extension.keySet());
		Collections.sort(elements, ElementComparator.INSTANCE);
		for (@NonNull Element element : elements) {
			if (element instanceof org.eclipse.ocl.pivot.Class) {
				Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = element2stereotype2extension.get(element);
				assert stereotype2extension != null;
				List<@NonNull Stereotype> stereotypes = new ArrayList<>(stereotype2extension.keySet());
				addStereotypeClosure(stereotypes);
				Collections.sort(stereotypes, NameUtil.NAMEABLE_COMPARATOR);
				for (@NonNull Stereotype stereotype : stereotypes) {
					org.eclipse.ocl.pivot.@NonNull Class metaBaseClass = (org.eclipse.ocl.pivot.Class)element;
					org.eclipse.ocl.pivot.@NonNull Class metaExtensionClass = stereotype;
					org.eclipse.ocl.pivot.@NonNull Class localBaseClass = completeModel.getEquivalentClass(thisModel, metaBaseClass);
					org.eclipse.ocl.pivot.@NonNull Class localExtensionClass = completeModel.getEquivalentClass(thisModel, metaExtensionClass);
					Property localBaseProperty = PivotUtil.createProperty(UML2AS.STEREOTYPE_BASE_PREFIX + metaBaseClass.getName(), metaBaseClass);
					Property localExtensionProperty = PivotUtil.createProperty(UML2AS.STEREOTYPE_EXTENSION_PREFIX + metaExtensionClass.getName(), metaExtensionClass);
					localBaseProperty.setIsImplicit(true);
					localExtensionProperty.setIsImplicit(true);
					localBaseProperty.setOpposite(localExtensionProperty);
					localExtensionProperty.setOpposite(localBaseProperty);
					if (UML2AS.ADD_BASE_EXTENSION_PROPERTY_PAIR.isActive()) {
						UML2AS.ADD_BASE_EXTENSION_PROPERTY_PAIR.println(localExtensionClass.toString() + "::" + localBaseProperty + " # " + localBaseClass.toString() + "::" + localExtensionProperty);
					}
					converter.addProperty(localExtensionClass, localBaseProperty);
					converter.addProperty(localBaseClass, localExtensionProperty);
				}
			}
		}
	}

	private @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension> installExtensions(@NonNull Element asElement, @NonNull Iterable<@NonNull StereotypeExtender> typeExtensions) {
		Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = new HashMap<>();
		List<@NonNull StereotypeExtender> typeExtensions2 = Lists.newArrayList(typeExtensions);
		Collections.sort(typeExtensions2, ElementComparator.INSTANCE);
		for (@NonNull StereotypeExtender typeExtension : typeExtensions2) {
			Stereotype stereotype = typeExtension.getOwningStereotype();
			if (stereotype != null) {
				ElementExtension elementExtension = environmentFactory.getElementExtension(asElement, stereotype);
				elementExtension.setIsRequired(true);
				stereotype2extension.put(stereotype, elementExtension);
				if (UML2AS.ADD_ELEMENT_EXTENSION.isActive()) {
					UML2AS.ADD_ELEMENT_EXTENSION.println(asElement.toString() + " + " + elementExtension.toString());
				}
			}
		}
		return stereotype2extension;
	}

	protected void printMetatypes2StereotypeExtensions(org.eclipse.ocl.pivot.@NonNull Package asPackage,
			@NonNull Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> metatype2typeExtensions) {
		if (UML2AS.TYPE_EXTENSIONS.isActive()) {
			StringBuffer s = new StringBuffer();
			s.append(NameUtil.qualifiedNameFor(asPackage) + " : " + asPackage.getURI());
			List<@NonNull Type> metatypes = new ArrayList<>(metatype2typeExtensions.keySet());
			Collections.sort(metatypes, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull Type metatype : metatypes) {
				s.append("\n\t" + NameUtil.qualifiedNameFor(metatype) + " ++"); //+ " : " + asProfile.getNsURI());
				Set<@NonNull StereotypeExtender> typeExtensions = metatype2typeExtensions.get(metatype);
				assert typeExtensions != null;
				List<@NonNull Stereotype> stereotypes = new ArrayList<>();
				for (StereotypeExtender typeExtension : typeExtensions) {
					stereotypes.add(PivotUtil.getOwningStereotype(typeExtension));
				}
				Collections.sort(stereotypes, NameUtil.NAMEABLE_COMPARATOR);
				for (@NonNull Stereotype stereotype : stereotypes) {
					s.append(" " + NameUtil.qualifiedNameFor(stereotype) + ","); //+ " : " + asProfile.getNsURI());
				}
			}
			UML2AS.TYPE_EXTENSIONS.println(s.toString());
		}
	}

	/**
	 * Determine the UML stereotype applications for each stereotyped pivot element, from the pre-computed mapping
	 * of stereotyped UML elements for each UML stereotype application.
	 * @param umlStereotypeApplications
	 */
	private @NonNull Map<@NonNull Element, @NonNull List<@NonNull EObject>> resolveStereotypeApplications(@NonNull Map<@NonNull EObject, @NonNull List<org.eclipse.uml2.uml.@NonNull Element>> umlStereotypeApplication2umlStereotypedElements) {
		Map<@NonNull Element, @NonNull List<@NonNull EObject>> asElement2umlStereotypeApplications = new HashMap<>();
		for (@NonNull Entry<@NonNull EObject, @NonNull List<org.eclipse.uml2.uml.@NonNull Element>> entry : umlStereotypeApplication2umlStereotypedElements.entrySet()) {
			@NonNull EObject umlStereotypeApplication = entry.getKey();
			@NonNull List<org.eclipse.uml2.uml.@NonNull Element> umlStereotypedElements = entry.getValue();
			for (org.eclipse.uml2.uml.@NonNull Element umlStereotypedElement : umlStereotypedElements) {
				Element asStereotypedElement = converter.getCreated(Element.class, umlStereotypedElement);
				if (asStereotypedElement != null) {
					List<@NonNull EObject> umlPerElementStereotypeApplications = asElement2umlStereotypeApplications.get(asStereotypedElement);
					if (umlPerElementStereotypeApplications == null) {
						umlPerElementStereotypeApplications = new ArrayList<>();
						asElement2umlStereotypeApplications.put(asStereotypedElement, umlPerElementStereotypeApplications);
					}
					umlPerElementStereotypeApplications.add(umlStereotypeApplication);
				}
			}
		}
		return asElement2umlStereotypeApplications;
	}
}
