/*******************************************************************************
 * Copyright (c) 2014, 2018 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink (CEA List) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml.internal.es2as;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * The ProfileAnalysis captures the overall analysis of the UML M2 Profiles and Stereotypes.
 */
public class ProfileAnalysis
{
	protected final UML2AS.@NonNull Outer converter;
	protected final @NonNull EnvironmentFactory environmentFactory;

	/**
	 * All metatypes that are extended by a TypeExtension.
	 */
	private final @NonNull Set<@NonNull Type> allExtendedMetatypes = new HashSet<>();

	/**
	 * All metatypes that are extended by a TypeExtension.
	 */
	private final @NonNull Set<org.eclipse.ocl.pivot.@NonNull Package> allExtendedMetapackages = new HashSet<>();

	/**
	 * All stereotypes that extend a metatype via a TypeExtension.
	 */
	private final @NonNull Set<@NonNull Stereotype> allExtendingStereotypes = new HashSet<>();

	/**
	 * All stereotypes.
	 */
	private final @NonNull Set<@NonNull Stereotype> allStereotypes = new HashSet<>();

	/**
	 * All profiles.
	 */
//	private final @NonNull Set<@NonNull Profile> allProfiles = new HashSet<>();

	private final @NonNull Map<@NonNull Profile, @NonNull Set<@NonNull Stereotype>> profile2ownedStereotypes = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull Type> metatypeName2metatype = new HashMap<>();
	private final @NonNull Map<@NonNull Type, @NonNull Set<@NonNull Type>> metatype2superMetatypeClosure = new HashMap<>();
	private final @NonNull Map<@NonNull Type, @NonNull Set<@NonNull Type>> metatype2subMetatypeClosure = new HashMap<>();
	private final @NonNull Map<@NonNull Stereotype, @NonNull Set<@NonNull Stereotype>> stereotype2superStereotypeClosure = new HashMap<>();
	private final @NonNull Map<@NonNull Stereotype, @NonNull Set<@NonNull Stereotype>> stereotype2subStereotypeClosure = new HashMap<>();
//	private final @NonNull Map<Type, Set<Stereotype>> metatype2stereotypeClosure = new HashMap<>();
//	private final @NonNull Map<Type, Set<Stereotype>> metatype2stereotypeClosureClosure = new HashMap<>();

	public ProfileAnalysis(UML2AS.@NonNull Outer converter) {
		this.converter = converter;
		this.environmentFactory = converter.getEnvironmentFactory();
	}

	public void addStereotype(@NonNull Stereotype asStereotype) {
		allStereotypes.add(asStereotype);
		org.eclipse.ocl.pivot.Package asPackage = asStereotype.getOwningPackage();
		if (asPackage instanceof Profile) {
			Profile asProfile = (Profile) asPackage;
			Set<@NonNull Stereotype> ownedStereotypes = profile2ownedStereotypes.get(asProfile);
			if (ownedStereotypes == null) {
				ownedStereotypes = new HashSet<>();
				profile2ownedStereotypes.put(asProfile, ownedStereotypes);
			}
			ownedStereotypes.add(asStereotype);
		}
	}

	public void addTypeExtension(@NonNull StereotypeExtender asTypeExtension) {
		org.eclipse.ocl.pivot.Class extendedMetatype = asTypeExtension.getClass_();
		Stereotype extendingStereotype = asTypeExtension.getOwningStereotype();
		if ((extendedMetatype != null) && (extendingStereotype != null)) {
			allExtendedMetatypes.add(extendedMetatype);
			allExtendingStereotypes.add(extendingStereotype);
			org.eclipse.ocl.pivot.Package metaPackage = extendedMetatype.getOwningPackage();
			if (metaPackage != null) {
				allExtendedMetapackages.add(metaPackage);
			}
		}
	}

/*		@Override
		public void addTypeExtension(@NonNull TypeExtension asTypeExtension) {
			List<TypeExtension> asTypeExtensions2 = asTypeExtensions;
			if (asTypeExtensions2 == null) {
				asTypeExtensions = asTypeExtensions2 = new ArrayList<>();
			}
			asTypeExtensions2.add(asTypeExtension);
/ *			org.eclipse.uml2.uml.Association umlExtension = umlExtensionEnd.getAssociation();
			if (umlExtension instanceof org.eclipse.uml2.uml.Extension) {
				org.eclipse.uml2.uml.Package umlProfile = umlExtension.getPackage();
				if (umlProfile instanceof org.eclipse.uml2.uml.Profile) {
					Map<org.eclipse.uml2.uml.Profile, List<org.eclipse.uml2.uml.ExtensionEnd>> profile2requiredExtensionEnds2 = profile2requiredExtensionEnds;
					if (profile2requiredExtensionEnds2 == null) {
						profile2requiredExtensionEnds = profile2requiredExtensionEnds2 = new HashMap<>();
					}
					List<org.eclipse.uml2.uml.ExtensionEnd> umlRequiredExtensionEnds = profile2requiredExtensionEnds2.get(umlProfile);
					if (umlRequiredExtensionEnds == null) {
						umlRequiredExtensionEnds = new ArrayList<>();
						profile2requiredExtensionEnds2.put((org.eclipse.uml2.uml.Profile) umlProfile, umlRequiredExtensionEnds);
					}
					umlRequiredExtensionEnds.add(umlExtensionEnd);
				}
			} * /
		} */

	public void analyze() {
		computeMetatypeName2metatype();
		computeMetatypeClosure();
		computeStereotypeClosure();
//		computeMetatype2StereotypeClosure();
//		computeMetatype2StereotypeClosureClosure();
/*		if (UML2AS.APPLICABLE_STEREOTYPES.isActive()) {
			StringBuffer s = new StringBuffer();
			List<Type> asMetatypes = new ArrayList<>(metatype2stereotypeClosureClosure.keySet());
			Collections.sort(asMetatypes, PivotUtil.NAMEABLE_COMPARATOR);
			for (@SuppressWarnings("null")@NonNull Type asMetatype : asMetatypes) {
				s.append("\n\t" + EcoreUtils.qualifiedNameFor(asMetatype));
//					s.append(" " + ClassUtil.debugSimpleName(asMetatype));
				org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asMetatype);
				if (asPackage != null) {
					s.append(" - " + asPackage.getNsURI());
				}
				s.append("\n\t\t");
				List<Stereotype> stereotypes = new ArrayList<>(metatype2stereotypeClosureClosure.get(asMetatype));
				Collections.sort(stereotypes, PivotUtil.NAMEABLE_COMPARATOR);
				for (Stereotype asStereotype : stereotypes) {
					s.append(asStereotype + ", ");
//						s.append(" " + ClassUtil.debugSimpleName(asStereotype));
				}
			}
			UML2AS.APPLICABLE_STEREOTYPES.println("Stereotypes per Metatype" + s.toString());
		} */
/*				for (Type asMetatype1 : metatypeClosure.keySet()) {
					for (Type asMetatype2 : metatypeClosure.get(asMetatype1)) {
						for (TypeExtension asTypeExtension : asMetatype2.getExtendedBys()) {
							Stereotype asStereotype1 = asTypeExtension.getStereotype();
							for (Stereotype asStereotype2 : stereotypeClosure.get(asStereotype1)) {

							}
						}
					}
				} */
	}

	/**
	 * Compute the stereotypes that may be applied to each metatype within package.
	 *
	private @NonNull Map<org.eclipse.ocl.pivot.Package, Map<Type, Set<Stereotype>>> computeApplicableStereotypes(@NonNull List<ProfileApplication> asProfileApplications, @NonNull Map<Profile, Set<Profile>> appliedProfile2allProfiles) {
		//
		//	Create the closure of all profiles to each package for which any profile is applied
		//
		Map<org.eclipse.ocl.pivot.Package, Map<Type, Set<Stereotype>>> package2metatype2applicableStereotypes = new HashMap<>();
		for (ProfileApplication asProfileApplication : asProfileApplications) {
			org.eclipse.ocl.pivot.Package asPackage = asProfileApplication.getApplyingPackage();
			if (asPackage != null) {
				Profile asProfile = asProfileApplication.getAppliedProfile();
				if (asProfile != null) {
					Set<Profile> asProfileProfiles = appliedProfile2allProfiles.get(asProfile);
					if (asProfileProfiles != null) {
						Map<Type, Set<Stereotype>> metatype2applicableStereotypes = package2metatype2applicableStereotypes.get(asPackage);
						if (metatype2applicableStereotypes == null) {
							metatype2applicableStereotypes = new HashMap<>();
							package2metatype2applicableStereotypes.put(asPackage, metatype2applicableStereotypes);
						}
						for (Profile asProfile2 : asProfileProfiles) {
							for (Type type : asProfile2.getOwnedType()) {
								if (type instanceof Stereotype) {
									Set<Stereotype> applicableStereotypeClosure = stereotype2superStereotypeClosure.get(type);
									if (applicableStereotypeClosure != null) {
										for (Stereotype applicableStereotype : applicableStereotypeClosure) {
											if (applicableStereotype != null) {
												for (TypeExtension typeExtension : applicableStereotype.getExtensionOfs()) {
													if (typeExtension != null) {
														Type metatype = typeExtension.getType();
														if (metatype != null) {
															Set<Stereotype> applicableStereotypes = metatype2applicableStereotypes.get(metatype);
															if (applicableStereotypes == null) {
																applicableStereotypes = new HashSet<>();
																metatype2applicableStereotypes.put(metatype, applicableStereotypes);
															}
															applicableStereotypes.add(applicableStereotype);
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
				}
			}
		}
		if (UML2AS.ADD_PROFILE_APPLICATION.isActive()) {
			StringBuffer s = new StringBuffer();
			for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Package asPackage : package2metatype2applicableStereotypes.keySet()) {
				s.append("\n\t" + EcoreUtils.qualifiedNameFor(asPackage) + " : " + asPackage.getNsURI());
				Map<Type, Set<Stereotype>> metatype2applicableStereotypes = package2metatype2applicableStereotypes.get(asPackage);
				for (Type metatype : metatype2applicableStereotypes.keySet()) {
					if (metatype != null) {
						s.append("\n\t\t" + EcoreUtils.qualifiedNameFor(metatype)); //+ " : " + asProfile.getNsURI());
						Set<Stereotype> asPackageStereotypes = metatype2applicableStereotypes.get(metatype);
						for (Stereotype asStereotype : asPackageStereotypes) {
							if (asStereotype != null) {
								s.append("\n\t\t\t" + EcoreUtils.qualifiedNameFor(asStereotype)); //+ " : " + asProfile.getNsURI());
							}
						}
					}
				}
			}
			UML2AS.ADD_PROFILE_APPLICATION.println("Applicable Stereotypes per Metatype per Package" + s.toString());
		}
		return package2metatype2applicableStereotypes;
	} */

	/**
	 * Compute the closure of all profiles applied to packages that have a profile application.
	 *
	protected static @NonNull Map<org.eclipse.ocl.pivot.Package, Set<Profile>> computeAppliedProfiles(@NonNull List<ProfileApplication> asProfileApplications) {
		//
		//	Determine all the profiles that are actually applied somewhere.
		//
		Set<Profile> appliedProfiles = new HashSet<>();
		for (ProfileApplication asProfileApplication : asProfileApplications) {
			Profile asProfile = asProfileApplication.getAppliedProfile();
			if (asProfile != null) {
				appliedProfiles.add(asProfile);
			}
		}
		//
		//	Determine the closure of all profiles for each actually applied profile.
		//
		Map<Profile, Set<Profile>> appliedProfile2allProfiles = new HashMap<>();
		for (@SuppressWarnings("null")@NonNull Profile asProfile : appliedProfiles) {
			Set<Profile> asProfiles = new HashSet<>();
			computeProfileClosure(asProfiles, asProfile);
			appliedProfile2allProfiles.put(asProfile, asProfiles);
		}
		//
		//	Create the closure of all profiles to each package for which any profile is applied
		//
		Map<org.eclipse.ocl.pivot.Package, Set<Profile>> package2allAppliedProfiles = new HashMap<>();
		for (ProfileApplication asProfileApplication : asProfileApplications) {
			org.eclipse.ocl.pivot.Package asPackage = asProfileApplication.getApplyingPackage();
			if (asPackage != null) {
				Profile asProfile = asProfileApplication.getAppliedProfile();
				if (asProfile != null) {
					Set<Profile> asProfileProfiles = appliedProfile2allProfiles.get(asProfile);
					if (asProfileProfiles != null) {
						Set<Profile> asPackageProfiles = package2allAppliedProfiles.get(asPackage);
						if (asPackageProfiles == null) {
							asPackageProfiles = new HashSet<>();
							package2allAppliedProfiles.put(asPackage, asPackageProfiles);
						}
						asPackageProfiles.addAll(asProfileProfiles);
					}
				}
			}
		}
		if (ADD_PROFILE_APPLICATION.isActive()) {
			StringBuffer s = new StringBuffer();
			for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Package asPackage : package2allAppliedProfiles.keySet()) {
				s.append("\n\t" + EcoreUtils.qualifiedNameFor(asPackage) + " : " + asPackage.getNsURI());
				Set<Profile> asPackageProfiles = package2allAppliedProfiles.get(asPackage);
				for (Profile asProfile : asPackageProfiles) {
					if (asProfile != null) {
						s.append("\n\t\t" + EcoreUtils.qualifiedNameFor(asProfile) + " : " + asProfile.getNsURI());
					}
				}
			}
			ADD_PROFILE_APPLICATION.println("Profiles per Package" + s.toString());
		}
		return package2allAppliedProfiles;
	} */

/*	private void computeMetatype2StereotypeClosure() {
		for (Type asMetatype : allExtendedMetatypes) {
			if (asMetatype != null) {
				Set<Stereotype> asMetatypeClosure = new HashSet<>();
				metatype2stereotypeClosure.put(asMetatype, asMetatypeClosure);
				for (TypeExtension asTypeExtension : asMetatype.getExtendedBys()) {
					Stereotype asStereotype = asTypeExtension.getStereotype();
					Set<Stereotype> superStereotypeClosure = stereotype2superStereotypeClosure.get(asStereotype);
					if (superStereotypeClosure != null) {
						asMetatypeClosure.addAll(superStereotypeClosure);
					}
					Set<Stereotype> subStereotypeClosure = stereotype2subStereotypeClosure.get(asStereotype);
					if (subStereotypeClosure != null) {
						asMetatypeClosure.addAll(subStereotypeClosure);
					}
				}
			}
		}
	} */

/*	private void computeMetatype2StereotypeClosureClosure() {
		for (Type asMetatype : metatype2stereotypeClosure.keySet()) {
			if (asMetatype != null) {
				Set<Stereotype> asMetatypeClosure = new HashSet<>();
				metatype2stereotypeClosureClosure.put(asMetatype, asMetatypeClosure);
				for (DomainType asSuperMetatype : metamodelManager.getAllSuperClasses(asMetatype)) {
					if (asSuperMetatype instanceof TypeServer) {
						asSuperMetatype = ((TypeServer)asSuperMetatype).getPivotType();
					}
					if (asSuperMetatype instanceof Type) {
						Set<Stereotype> stereotypeClosure = metatype2stereotypeClosure.get(asSuperMetatype);
						if (stereotypeClosure != null) {
							asMetatypeClosure.addAll(stereotypeClosure);
						}
					}
				}
			}
		}
	} */

	private void computeMetatypeName2metatype() {
		for (org.eclipse.ocl.pivot.@NonNull Package metapackage : allExtendedMetapackages) {
			for (org.eclipse.ocl.pivot.Class metatype : PivotUtil.getOwnedClasses(metapackage)) {
				String name = metatype.getName();
				assert name != null;
				metatypeName2metatype.put(name, metatype);
			}
		}
	}

	private void computeMetatypeClosure() {
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		for (org.eclipse.ocl.pivot.@NonNull Package metapackage : allExtendedMetapackages) {
			for (org.eclipse.ocl.pivot.Class subMetatype : metapackage.getOwnedClasses()) {
				if (subMetatype != null) {
					Set<Type> superMetatypeClosure = new HashSet<>();
					metatype2superMetatypeClosure.put(subMetatype, superMetatypeClosure);
					for (CompleteClass superCompleteClass : completeModel.getAllSuperCompleteClasses(subMetatype)) {
						org.eclipse.ocl.pivot.Class asSuperMetatype = superCompleteClass.getPrimaryClass();
						superMetatypeClosure.add(asSuperMetatype);
						Set<@NonNull Type> subMetatypeClosure = metatype2subMetatypeClosure.get(asSuperMetatype);
						if (subMetatypeClosure == null) {
							subMetatypeClosure = new HashSet<>();
							metatype2subMetatypeClosure.put(asSuperMetatype, subMetatypeClosure);
						}
						subMetatypeClosure.add(subMetatype);
					}
				}
			}
		}
	}

	private void computeStereotypeClosure() {
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		for (@NonNull Stereotype subStereotype : allStereotypes) {
			Set<@NonNull Stereotype> superStereotypeClosure = new HashSet<>();
			stereotype2superStereotypeClosure.put(subStereotype, superStereotypeClosure);
			for (CompleteClass superCompleteClass : completeModel.getAllSuperCompleteClasses(subStereotype)) {
				org.eclipse.ocl.pivot.Class asSuperStereotype = superCompleteClass.getPrimaryClass();
				if (asSuperStereotype instanceof Stereotype) {
					superStereotypeClosure.add((Stereotype)asSuperStereotype);
					Set<@NonNull Stereotype> subStereotypeClosure = stereotype2subStereotypeClosure.get(asSuperStereotype);
					if (subStereotypeClosure == null) {
						subStereotypeClosure = new HashSet<>();
						stereotype2subStereotypeClosure.put((Stereotype)asSuperStereotype, subStereotypeClosure);
					}
					subStereotypeClosure.add(subStereotype);
				}
			}
		}
	}

	public @NonNull Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> computeMetatypes2typeExtensions() {
		Set<@NonNull Stereotype> applicableStereotypes = allStereotypes; //getOwnedStereotypes(appliedProfileClosure);
		Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> extensibleMetatype2typeExtensions = getExtensibleMetatype2typeExtensions(applicableStereotypes);
/*		Map<Type, Set<TypeExtension>> metatype2typeExtensions = new HashMap<>();
		for (Type metatype : extensibleMetatype2extendingStereotypes.keySet()) {
//			@SuppressWarnings("null")@NonNull Set<Stereotype> extendingStereotypes = extensibleMetatype2extendingStereotypes.get(metatype);
//			Set<Stereotype> extendingStereotypeClosure = getStereotypeSubSuperClosure(extendingStereotypes);
//			extensibleMetatypesClosure.put(metatype, extendingStereotypeClosure);
		}
//		Map<Type, Set<TypeExtension>> metatype2typeExtensions = new HashMap<>();
		for (@SuppressWarnings("null")@NonNull Type metatype : extensibleMetatype2typeExtensions.keySet()) {
			Set<TypeExtension> someTypeExtensions = metatype2typeExtensions.get(metatype);
			if (someTypeExtensions != null) {
				Set<Type> metatypeSubClosure = getSubMetatypeClosure(metatype);
				if (metatypeSubClosure != null) {
					for (Type subMetatype : metatypeSubClosure) {
						Set<TypeExtension> allTypeExtensions = metatype2typeExtensions.get(subMetatype);
						if (allTypeExtensions == null) {
							allTypeExtensions = new HashSet<>();
							metatype2typeExtensions.put(subMetatype, allTypeExtensions);
						}
						allTypeExtensions.addAll(someTypeExtensions);
					}
				}
			}
		}
		return metatype2typeExtensions; */
		return extensibleMetatype2typeExtensions;
	}

	public @NonNull Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> computeMetatypes2typeExtensions(@NonNull Set<@NonNull Profile> appliedProfileClosure) {
		Set<@NonNull Stereotype> applicableStereotypes = getOwnedStereotypes(appliedProfileClosure);
		Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> extensibleMetatype2typeExtensions = getExtensibleMetatype2typeExtensions(applicableStereotypes);
//		Map<Type, Set<TypeExtension>> metatype2typeExtensions = new HashMap<>();
//		for (Type metatype : extensibleMetatype2extendingStereotypes.keySet()) {
//			@SuppressWarnings("null")@NonNull Set<Stereotype> extendingStereotypes = extensibleMetatype2extendingStereotypes.get(metatype);
//			Set<Stereotype> extendingStereotypeClosure = getStereotypeSubSuperClosure(extendingStereotypes);
//			extensibleMetatypesClosure.put(metatype, extendingStereotypeClosure);
//		}
		Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> metatype2typeExtensions = new HashMap<>();
		for (@NonNull Type metatype : extensibleMetatype2typeExtensions.keySet()) {
			Set<@NonNull StereotypeExtender> someTypeExtensions = metatype2typeExtensions.get(metatype);
			if (someTypeExtensions != null) {
				Set<@NonNull Type> metatypeSubClosure = getSubMetatypeClosure(metatype);
				if (metatypeSubClosure != null) {
					for (@NonNull Type subMetatype : metatypeSubClosure) {
						Set<@NonNull StereotypeExtender> allTypeExtensions = metatype2typeExtensions.get(subMetatype);
						if (allTypeExtensions == null) {
							allTypeExtensions = new HashSet<>();
							metatype2typeExtensions.put(subMetatype, allTypeExtensions);
						}
						allTypeExtensions.addAll(someTypeExtensions);
					}
				}
			}
		}
		return metatype2typeExtensions;
	}

/*	private @Nullable Set<Profile> computeProfileClosure(@NonNull ResourceSet asResourceSet) {
		Set<Profile> allProfiles = null;
		for (Resource asResource : asResourceSet.getResources()) {
			if (asResource != null) {
				for (EObject eRoot : asResource.getContents()) {
					if (eRoot instanceof Root) {
						for (org.eclipse.ocl.pivot.Package asNestedPackage : ((Root)eRoot).getNestedPackage()) {
							if (asNestedPackage instanceof Profile) {
								if (allProfiles == null) {
									allProfiles = new HashSet<>();
								}
								computeProfileClosure(allProfiles, (Profile) asNestedPackage);
							}
						}
					}
				}
			}
		}
		return allProfiles;
	} */

/*	private @NonNull Map<Type, Set<Stereotype>> getExtensibleMetatype2extendingStereotypes(@NonNull Iterable<Stereotype> applicableStereotypes) {
		Map<Type, Set<Stereotype>> extensibleMetatype2extendingStereotypes = new HashMap<>();
		for (Stereotype applicableStereotype : applicableStereotypes) {
			for (TypeExtension typeExtension : applicableStereotype.getExtensionOfs()) {
				Type extensibleMetatype = typeExtension.getType();
				if (extensibleMetatype != null) {
					Set<Stereotype> extendingStereotypes = extensibleMetatype2extendingStereotypes.get(extensibleMetatype);
					if (extendingStereotypes == null) {
						extendingStereotypes = new HashSet<>();
						extensibleMetatype2extendingStereotypes.put(extensibleMetatype, extendingStereotypes);
					}
					extendingStereotypes.add(applicableStereotype);
				}
			}
		}
		return extensibleMetatype2extendingStereotypes;
	} */

	private @NonNull Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> getExtensibleMetatype2typeExtensions(@NonNull Iterable<@NonNull Stereotype> applicableStereotypes) {
		Map<@NonNull Type, @NonNull Set<@NonNull StereotypeExtender>> extensibleMetatype2typeExtensions = new HashMap<>();
		for (@NonNull Stereotype applicableStereotype : applicableStereotypes) {
//			if (applicableStereotype.getName().contains("Parent")) {
//				System.out.println("Got it");
//			}
			for (StereotypeExtender typeExtension : applicableStereotype.getOwnedExtenders()) {
				Type extensibleMetatype = typeExtension.getClass_();
				if (extensibleMetatype != null) {
					Set<@NonNull StereotypeExtender> typeExtensions = extensibleMetatype2typeExtensions.get(extensibleMetatype);
					if (typeExtensions == null) {
						typeExtensions = new HashSet<>();
						extensibleMetatype2typeExtensions.put(extensibleMetatype, typeExtensions);
					}
					typeExtensions.add(typeExtension);
				}
			}
		}
		return extensibleMetatype2typeExtensions;
	}

	public @Nullable Type getMetatype(@NonNull String packageName, @NonNull String className) {
		return metatypeName2metatype.get(className);
	}

/*	private @NonNull Map<Type, Set<Stereotype>> getMetatype2stereotypeClosure(@NonNull Iterable<Profile> asProfiles) {
		//
		//	Create the closure of all profiles to each package for which any profile is applied
		//
		Map<Type, Set<Stereotype>> metatype2applicableStereotypes = new HashMap<>();
		for (Profile asProfile2 : asProfiles) {
			for (Type type : asProfile2.getOwnedType()) {
				if (type instanceof Stereotype) {
					Set<Stereotype> applicableStereotypeClosure = stereotype2superStereotypeClosure.get(type);
					if (applicableStereotypeClosure != null) {
						for (Stereotype applicableStereotype : applicableStereotypeClosure) {
							if (applicableStereotype != null) {
								for (TypeExtension typeExtension : applicableStereotype.getExtensionOfs()) {
									if (typeExtension != null) {
										Type metatype = typeExtension.getType();
										if (metatype != null) {
											Set<Stereotype> applicableStereotypes = metatype2applicableStereotypes.get(metatype);
											if (applicableStereotypes == null) {
												applicableStereotypes = new HashSet<>();
												metatype2applicableStereotypes.put(metatype, applicableStereotypes);
											}
											applicableStereotypes.add(applicableStereotype);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return metatype2applicableStereotypes;
	} */

	private @NonNull Set<@NonNull Stereotype> getOwnedStereotypes(@NonNull Iterable<@NonNull Profile> asProfiles) {
		Set<@NonNull Stereotype> allOwnedStereotypes = new HashSet<>();
		for (@NonNull Profile asProfile : asProfiles) {
			Set<@NonNull Stereotype> ownedStereotypes = profile2ownedStereotypes.get(asProfile);
			if (ownedStereotypes != null) {
				allOwnedStereotypes.addAll(ownedStereotypes);
			}
		}
		return allOwnedStereotypes;
	}

/*	private @NonNull Set<Stereotype> getStereotypeSubSuperClosure(@NonNull Iterable<Stereotype> asStereotypes) {
		Set<Stereotype> stereotypeClosure = new HashSet<>();
		for (Stereotype asStereotype : asStereotypes) {
			Set<Stereotype> subStereotypeClosure = stereotype2subStereotypeClosure.get(asStereotype);
			if (subStereotypeClosure != null) {
				stereotypeClosure.addAll(subStereotypeClosure);
			}
			Set<Stereotype> subperStereotypeClosure = stereotype2superStereotypeClosure.get(asStereotype);
			if (subperStereotypeClosure != null) {
				stereotypeClosure.addAll(subperStereotypeClosure);
			}
		}
		return stereotypeClosure;
	} */

	private @Nullable Set<@NonNull Type> getSubMetatypeClosure(@NonNull Type metatype) {
		return metatype2subMetatypeClosure.get(metatype);
	}
}
