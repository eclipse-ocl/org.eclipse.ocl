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
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

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
				if (o1 instanceof NamedElement) {
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
		@SuppressWarnings("null")@NonNull EClass eClass = umlStereotypeApplication.eClass();
		if (UML2AS.ADD_STEREOTYPE_APPLICATION.isActive()) {
			if (umlStereotypeApplication instanceof DynamicEObjectImpl) {
				UML2AS.ADD_STEREOTYPE_APPLICATION.println(NameUtil.qualifiedNameFor(eClass));
			}
			else {
				UML2AS.ADD_STEREOTYPE_APPLICATION.println(NameUtil.qualifiedNameFor(eClass));
				//					ADD_STEREOTYPE_APPLICATION.println(umlStereotypeApplication.toString());
			}
		}
		List<@NonNull EObject> umlStereotypeApplications2 = umlStereotypeApplications;
		if (umlStereotypeApplications2 == null) {
			umlStereotypeApplications = umlStereotypeApplications2 = new ArrayList<>();
		}
		umlStereotypeApplications2.add(umlStereotypeApplication);
		EPackage ePackage = eClass.getEPackage();
		Resource eResource = ePackage.eResource();
		if (eResource != null) {
			//			converter.addImportedResource(eResource);	// -- leads to CCEs for the wrong ES2AS
		}
	}

	private void computeAppliedProfile2profileClosure() {
		//
		//	Determine the closure of all profiles for each actually applied profile.
		//
		for (@NonNull Profile asProfile : appliedProfiles) {
			Set<Profile> asProfiles = new HashSet<>();
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
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
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
					ElementExtension elementExtension = metamodelManager.getElementExtension(asStereotypedElement, asStereotype);
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

	private void computeProfileClosure(@NonNull Set<Profile> allProfiles, @NonNull Profile asProfile) {
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
			if (!(asPackage instanceof Profile)) {
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
							if (typeExtensions != null) {
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
		//			for (Metaclass<?> metaclass : metaclass2properties.keySet()) {
		//				List<Property> newProperties = metaclass2properties.get(metaclass);
		//				List<Property> oldProperties = metaclass.getOwnedAttribute();
		//				assert oldProperties != null;
		//				refreshList(oldProperties, newProperties);
		//			}
	}

	/**
	 * @since 7.0
	 */
	protected void installExtensionProperties(@NonNull Map<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> element2stereotype2extension) {
		Model thisModel = converter.basicGetPivotModel();
		assert thisModel != null;
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		for (@NonNull Entry<@NonNull Element, @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension>> entry1 : element2stereotype2extension.entrySet()) {
			@NonNull Element element = entry1.getKey();
			if (element instanceof org.eclipse.ocl.pivot.Class) {
				Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = entry1.getValue();
				for (@NonNull Stereotype stereotype : stereotype2extension.keySet()) {
					for (@NonNull Property property : PivotUtil.getOwnedProperties(stereotype)) {
						String name = PivotUtil.getName(property);
						if (name.startsWith(UML2AS.STEREOTYPE_BASE_PREFIX)) {
							Property metaBaseProperty = property;
							Property metaExtensionProperty = metaBaseProperty.getOpposite();
							assert metaExtensionProperty != null;
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
							System.out.println("installExtensionProperties " + localBaseClass.toString() + "::" + localExtensionProperty + " ~ " + localExtensionClass.toString() + "::" + localBaseProperty);
							converter.addProperty(localBaseClass, localBaseProperty);
							converter.addProperty(localExtensionClass, localExtensionProperty);
						}
				//		else {
				//			System.out.println("installExtensionProperties unexpected Stereotype property " + stereotype + "::" + property);
				//		}
					}
				}
			}
		}
	}

	private @NonNull Map<@NonNull Stereotype, @NonNull ElementExtension> installExtensions(@NonNull Element asElement, @NonNull Set<@NonNull StereotypeExtender> typeExtensions) {
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Map<@NonNull Stereotype, @NonNull ElementExtension> stereotype2extension = new HashMap<>();
		for (@NonNull StereotypeExtender typeExtension : typeExtensions) {
			Stereotype stereotype = typeExtension.getOwningStereotype();
			if (stereotype != null) {
				ElementExtension elementExtension = metamodelManager.getElementExtension(asElement, stereotype);
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
			@NonNull Map<Type, @NonNull Set<StereotypeExtender>> metatype2typeExtensions) {
		if (UML2AS.TYPE_EXTENSIONS.isActive()) {
			StringBuffer s = new StringBuffer();
			s.append(NameUtil.qualifiedNameFor(asPackage) + " : " + asPackage.getURI());
			List<Type> metatypes = new ArrayList<>(metatype2typeExtensions.keySet());
			Collections.sort(metatypes, NameUtil.NAMEABLE_COMPARATOR);
			for (Type metatype : metatypes) {
				if (metatype != null) {
					s.append("\n\t" + NameUtil.qualifiedNameFor(metatype) + " ++"); //+ " : " + asProfile.getNsURI());
					Set<StereotypeExtender> typeExtensions = metatype2typeExtensions.get(metatype);
					assert typeExtensions != null;
					List<Stereotype> stereotypes = new ArrayList<>();
					for (StereotypeExtender typeExtension : typeExtensions) {
						stereotypes.add(typeExtension.getOwningStereotype());
					}
					Collections.sort(stereotypes, NameUtil.NAMEABLE_COMPARATOR);
					for (Stereotype stereotype : stereotypes) {
						if (stereotype != null) {
							s.append(" " + NameUtil.qualifiedNameFor(stereotype) + ","); //+ " : " + asProfile.getNsURI());
						}
					}
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
