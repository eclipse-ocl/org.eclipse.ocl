/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bugs 182994, 252600
 *   SAP - Bug 339052
 *******************************************************************************/

package org.eclipse.ocl.ecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.AbstractEnvironment;
import org.eclipse.ocl.AmbiguousLookupException;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.LookupException;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.ecore.internal.EcoreForeignMethods;
import org.eclipse.ocl.ecore.internal.OCLFactoryImpl;
import org.eclipse.ocl.ecore.internal.OCLStandardLibraryImpl;
import org.eclipse.ocl.ecore.internal.TypeResolverImpl;
import org.eclipse.ocl.ecore.internal.UMLReflectionImpl;
import org.eclipse.ocl.ecore.opposites.OppositeEndFinder;
import org.eclipse.ocl.ecore.utilities.ToStringVisitor;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.expressions.impl.ExpressionsPackageImpl;
import org.eclipse.ocl.internal.l10n.OCLMessages;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ocl.parser.AbstractOCLAnalyzer;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.types.TypesPackage;
import org.eclipse.ocl.util.TypeUtil;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.UMLReflection;
import org.eclipse.ocl.utilities.UtilitiesPackage;

/**
 * Implementation of the {@link Environment} for parsing OCL expressions on
 * Ecore models.  The <code>EcoreEnvironment</code> uses a client-supplied
 * package registry (or the global registry) to look up {@link EPackage}s
 * by qualified name.
 *
 * @author Edith Schonberg (edith)
 * @author Christian W. Damus (cdamus)
 */
public class EcoreEnvironment
extends AbstractEnvironment<
EPackage, EClassifier, EOperation, EStructuralFeature,
EEnumLiteral, EParameter,
EObject, CallOperationAction, SendSignalAction, Constraint,
EClass, EObject>
implements EnvironmentWithHiddenOpposites {

	/**
	 * The namespace URI of the Ecore representation of the OCL Standard Library.
	 *
	 * @since 1.3
	 */
	public static final String OCL_STANDARD_LIBRARY_NS_URI = "http://www.eclipse.org/ocl/1.1.0/oclstdlib.ecore"; //$NON-NLS-1$

	private static final Map<List<String>, EPackage> OCL_PACKAGES =
			new java.util.HashMap<List<String>, EPackage>();

	private static boolean findPackageHasReportedProblems = false;

	static {
		List<String> names = new java.util.ArrayList<String>();
		names.add(ExpressionsPackageImpl.OCL_ROOT_PACKAGE.getName());
		OCL_PACKAGES.put(names, ExpressionsPackageImpl.OCL_ROOT_PACKAGE);

		names = new java.util.ArrayList<String>(names);
		names.add(ExpressionsPackage.eINSTANCE.getName());
		OCL_PACKAGES.put(names, ExpressionsPackage.eINSTANCE);

		names = new java.util.ArrayList<String>(names);
		names.set(1, TypesPackage.eINSTANCE.getName());
		OCL_PACKAGES.put(names, TypesPackage.eINSTANCE);

		names = new java.util.ArrayList<String>(names);
		names.set(1, UtilitiesPackage.eINSTANCE.getName());
		OCL_PACKAGES.put(names, UtilitiesPackage.eINSTANCE);

		names = new java.util.ArrayList<String>(names);
		names.set(1, EcorePackage.eINSTANCE.getName());
		OCL_PACKAGES.put(names, EcorePackage.eINSTANCE);
	}

	/**
	 * The registry for package lookups.
	 */
	private EPackage.Registry registry;

	private EnvironmentFactory<
	EPackage, EClassifier, EOperation, EStructuralFeature,
	EEnumLiteral, EParameter,
	EObject, CallOperationAction, SendSignalAction, Constraint,
	EClass, EObject>
	factory;

	private TypeResolver<EClassifier, EOperation, EStructuralFeature> typeResolver;

	private OppositeEndFinder oppositeEndFinder;

	/**
	 * Initializes me with a package registry for package look-ups.
	 *
	 * @param reg a package registry
	 * @deprecated Use {@link #EcoreEnvironment(EcoreEnvironmentFactory, Resource)} instead
	 */
	@Deprecated
	protected EcoreEnvironment(EPackage.Registry reg) {
		registry = reg;
		typeResolver = createTypeResolver();
	}

	/**
	 * Initializes me with an environment factory from which package registry
	 * and opposite end finder (if any) are obtained consistently, and from a
	 * resource in which I am persisted (and from which I load myself if it
	 * already has content).
	 *
	 * @param fac
	 *            a package registry
	 * @param resource
	 *            a resource, which may or may not already have content
	 * @since 3.1
	 */
	protected EcoreEnvironment(EcoreEnvironmentFactory fac, Resource resource) {
		factory = fac;
		registry = fac.getEPackageRegistry();
		oppositeEndFinder = fac.getOppositeEndFinder();
		typeResolver = createTypeResolver(resource);
	}

	/**
	 * Initializes me with a package registry and a resource in which I am
	 * persisted (and from which I load myself if it already has content).
	 *
	 * @param reg a package registry
	 * @param resource a resource, which may or may not already have content
	 * @deprecated Use {@link #EcoreEnvironment(EcoreEnvironmentFactory, Resource)} instead
	 */
	@Deprecated
	protected EcoreEnvironment(EPackage.Registry reg, Resource resource) {
		registry = reg;
		typeResolver = createTypeResolver(resource);
	}

	/**
	 * Initializes me with a parent environment, from which I inherit such things
	 * as a package registry and a resource.
	 *
	 * @param parent my parent environment
	 */
	protected EcoreEnvironment(
			Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> parent) {

		super((EcoreEnvironment) parent);

		EcoreEnvironment eparent = (EcoreEnvironment) parent;

		if (eparent != null) {
			factory = eparent.factory;
			registry = eparent.registry;
			typeResolver = eparent.getTypeResolver();
			oppositeEndFinder = eparent.oppositeEndFinder;
		} else {
			registry = EPackage.Registry.INSTANCE;
			typeResolver = createTypeResolver();
		}
	}

	// implements the inherited specification
	@Override
	public EnvironmentFactory<
	EPackage, EClassifier, EOperation, EStructuralFeature,
	EEnumLiteral, EParameter,
	EObject, CallOperationAction, SendSignalAction, Constraint,
	EClass, EObject>
	getFactory() {
		if (factory != null) {
			return factory;
		}

		if (getInternalParent() != null) {
			factory = getInternalParent().getFactory();
			if (factory != null) {
				return factory;
			}
		}

		// obtain a reasonable default factory
		if (registry == EPackage.Registry.INSTANCE) {
			factory = EcoreEnvironmentFactory.INSTANCE;
		} else {
			factory = new EcoreEnvironmentFactory(registry);
		}

		return factory;
	}

	/**
	 * Sets the factory that created me. This method should only be invoked by
	 * that factory. If the factory is an {@link EcoreEnvironmentFactory}, its
	 * {@link EcoreEnvironmentFactory#getOppositeEndFinder() opposite end
	 * finder} will be used as this environment's {@link #oppositeEndFinder
	 * opposite end finder}.
	 *
	 * @param factory
	 *            my originating factory
	 * @deprecated {@link #factory} will become final in future releases; use
	 *             one of the constructors taking an
	 *             {@link EcoreEnvironmentFactory} argument instead
	 */
	@Deprecated
	protected void setFactory(EnvironmentFactory<
			EPackage, EClassifier, EOperation, EStructuralFeature,
			EEnumLiteral, EParameter,
			EObject, CallOperationAction, SendSignalAction, Constraint,
			EClass, EObject> factory) {
		this.factory = factory;
		if (factory instanceof EcoreEnvironmentFactory) {
			oppositeEndFinder = ((EcoreEnvironmentFactory)factory).getOppositeEndFinder();
		}
	}

	// implements the inherited specification
	@Override
	public void setParent(Environment<
			EPackage, EClassifier, EOperation, EStructuralFeature,
			EEnumLiteral, EParameter,
			EObject, CallOperationAction, SendSignalAction, Constraint,
			EClass, EObject> env) {
		super.setParent((EcoreEnvironment) env);
	}

	// implements the inherited specification
	@Override
	public OCLStandardLibrary<EClassifier> getOCLStandardLibrary() {
		return OCLStandardLibraryImpl.INSTANCE;
	}

	// implements the inherited specification
	@Override
	public TypeResolver<EClassifier, EOperation, EStructuralFeature> getTypeResolver() {
		return typeResolver;
	}

	// implements the inherited specification
	@Override
	public OCLFactory getOCLFactory() {
		return OCLFactoryImpl.INSTANCE;
	}

	// implements the inherited specification
	@Override
	public UMLReflection<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint> getUMLReflection() {
		return UMLReflectionImpl.INSTANCE;
	}

	/**
	 * Creates a new type resolver for use with this environment, persisted
	 * in a default resource.
	 *
	 * @return a new type resolver
	 *
	 * @deprecated Override the {@link #createTypeResolver(Resource)} method,
	 *     instead, handling the case where the resource is <code>null</code>
	 */
	@Deprecated
	protected TypeResolver<EClassifier, EOperation, EStructuralFeature> createTypeResolver() {
		return createTypeResolver(null);
	}

	/**
	 * <p>
	 * Creates a new type resolver for use with this environment.
	 * </p><p>
	 * Subclasses may override.
	 * </p>
	 *
	 * @param resource the resource for the type resolver's persistence
	 * @return a new type resolver
	 *
	 * @since 1.2
	 */
	protected TypeResolver<EClassifier, EOperation, EStructuralFeature> createTypeResolver(Resource resource) {
		return new TypeResolverImpl(this, resource);
	}


	/**
	 * {@inheritDoc}
	 * <p>
	 * Implements the inherited specification by looking up the qualified name
	 * in my package registry.
	 * </p>
	 */
	@Override
	public EPackage lookupPackage(List<String> path) {
		if (!path.isEmpty() && OCL_PACKAGES.containsKey(path)) {
			return OCL_PACKAGES.get(path);
		}

		EPackage pkg = null;
		EPackage currPkg = getContextPackage();

		// Check whether this package is in the default package
		if (currPkg != null) {
			List<String> lookup = path;

			while (currPkg != null) {
				pkg = currPkg;

				for (int i = 0; i < lookup.size(); i++) {
					String name = lookup.get(i);
					pkg = EcoreForeignMethods.getESubpackage(pkg, name);

					if (pkg == null) {
						break;
					}
				}

				if (pkg != null) {
					return pkg;
				}

				if ((currPkg == getContextPackage()) && (lookup.size() > 0)
						&& EcoreForeignMethods.isNamed(lookup.get(0), currPkg)) {
					// handle the case where the first part of the qualified
					// name matches the context package name
					lookup = lookup.subList(1, lookup.size());
				} else {
					lookup = path;
					currPkg = currPkg.getESuperPackage();
				}
			}
		}

		// Check whether this package exists in the global package registry
		return findPackageWithStrategy(path, registry);
	}

	// implements the inherited specification
	@Override
	public EClassifier lookupClassifier(List<String> names) {
		EPackage pkg = null;
		EPackage currPkg = getContextPackage();

		if (names.size() > 1) {
			List<String> lookup = names;

			// Check whether this package is in the default package
			if (currPkg != null) {
				while (currPkg != null) {

					pkg = currPkg;
					for (int i = 0; i < lookup.size() - 1; i++) {
						String name = lookup.get(i);
						pkg = EcoreForeignMethods.getESubpackage(pkg, name);

						if (pkg == null) {
							break;
						}
					}

					if (pkg != null) {
						return EcoreForeignMethods.getEClassifier(pkg, lookup
							.get(lookup.size() - 1));
					}

					if ((currPkg == getContextPackage()) && (lookup.size() > 1)
							&& EcoreForeignMethods.isNamed(lookup.get(0), currPkg)) {
						// handle the case where the first part of the qualified
						// name matches the context package name
						lookup = lookup.subList(1, lookup.size());
					} else {
						lookup = names;
						currPkg = currPkg.getESuperPackage();
					}
				}
			}

			// Check whether this package exists
			List<String> newNames = names.subList(0, names.size() - 1);
			pkg = findPackageWithStrategy(newNames, registry);
			if (pkg == null) {
				return null;
			}

			return EcoreForeignMethods.getEClassifier(pkg, names.get(names.size() - 1));
		} else if (getContextPackage() != null) {
			String name = names.get(0);
			EClassifier result = null;
			while (currPkg != null) {
				result = EcoreForeignMethods.getEClassifier(currPkg, name);

				if (result != null) {
					return result;
				}

				currPkg = currPkg.getESuperPackage();
			}
		}

		return null;
	}

	private EPackage findPackageWithStrategy(List<String> newNames, EPackage.Registry registry) {
		EPackage pkg;
		switch (ParsingOptions.getValue(this, ParsingOptions.PACKAGE_LOOKUP_STRATEGY)) {
			case LOOKUP_PACKAGE_BY_ALIAS:
				pkg = findPackageByAlias(newNames, registry);
				break;
			case LOOKUP_PACKAGE_BY_ALIAS_THEN_NAME:
				pkg = findPackageByAlias(newNames, registry);
				if (pkg == null) {
					pkg = findPackage(newNames, registry);
				}
				break;
			case LOOKUP_PACKAGE_BY_NAME:
				pkg = findPackage(newNames, registry);
				break;
			default:
				throw new RuntimeException("Unknown PACKAGE_LOOKUP_STRATEGY value "+ //$NON-NLS-1$
						ParsingOptions.getValue(this, ParsingOptions.PACKAGE_LOOKUP_STRATEGY));
		}
		return pkg;
	}

	/**
	 * Obtains the states matching the specified path prefix in the owner type
	 * by trying the {@link #collectStates} method on it and, recursively, its
	 * supertypes to find all matches.  For implicit (<code>null</code>) owners,
	 * looks up the innermost-scoped variable as the implicit source and tries
	 * again on this variable's type.
	 * <p>
	 * To extend this implementation, override the
	 * {@link #collectStates} method.
	 * </p>
	 */
	@Override
	public List<EObject> getStates(EClassifier owner, List<String> pathPrefix) {
		EList<EObject> result = new BasicEList<EObject>();

		collectStates(owner, pathPrefix, result);

		if (owner instanceof EClass) {
			// search supertypes
			for (EClass superclass : ((EClass) owner).getEAllSuperTypes()) {
				collectStates(superclass, pathPrefix, result);
			}
		}

		return result;
	}

	/**
	 * Implemented by subclasses to find all states in the specified owner type
	 * that match the given path name prefix and add them to the accumulator
	 * list.  The default implementation does nothing, as Ecore does not model
	 * states.
	 * <p>
	 * Implementors must only provide the states defined directly in the
	 * namespace indicated by the path prefix (i.e., only one level).
	 * </p>
	 *
	 * @param owner the owner type
	 * @param pathPrefix partial qualified name, specifying the parent of the
	 *     states to be collection
	 * @param states a list of states directly owned by the namespace indicated
	 *     by path prefix, within the owner type
	 *
	 * @see #getStates(EClassifier, List)
	 */
	protected void collectStates(EClassifier owner, List<String> pathPrefix, List<EObject> states) {
		// do nothing
	}

	// implements the inherited specification
	@Override
	public EStructuralFeature defineAttribute(
			EClassifier owner,
			org.eclipse.ocl.expressions.Variable<
			EClassifier, EParameter> variable,
			Constraint constraint) {
		resetTypeCaches();
		EStructuralFeature result;

		String name = variable.getName();
		EClassifier type = variable.getType();

		if (type instanceof EClass) {
			result = EcoreFactory.eINSTANCE.createEReference();
		} else {
			result = EcoreFactory.eINSTANCE.createEAttribute();
		}

		result.setName(name);
		result.setEType(type);

		Constraint existing = getDefinition(result);
		if (existing != null) {
			// replace existing definition
			EcoreUtil.replace(existing, constraint);
		} else {
			EAnnotation ann = result.getEAnnotation(Environment.OCL_NAMESPACE_URI);
			if (ann == null) {
				ann = EcoreFactory.eINSTANCE.createEAnnotation();
				ann.setSource(Environment.OCL_NAMESPACE_URI);
				result.getEAnnotations().add(ann);
			}

			ann.getContents().add(constraint);
		}

		addHelperProperty(owner, result);

		return result;
	}

	// implements the inherited specification
	@Override
	public EOperation defineOperation(EClassifier owner, String name,
			EClassifier type,
			List<org.eclipse.ocl.expressions.Variable<
			EClassifier, EParameter>> params,
			Constraint constraint) {
		resetTypeCaches();
		EOperation result = EcoreFactory.eINSTANCE.createEOperation();

		result.setName(name);
		result.setEType((type == null) ? getOCLStandardLibrary().getOclVoid() :
			type);

		for (Variable<EClassifier, EParameter> next : params) {
			EParameter param = EcoreFactory.eINSTANCE.createEParameter();
			param.setName(next.getName());
			param.setEType((next.getType() == null)?
				getOCLStandardLibrary().getOclVoid() : next.getType());

			result.getEParameters().add(param);
		}

		Constraint existing = getDefinition(result);
		if (existing != null) {
			// replace existing definition
			EcoreUtil.replace(existing, constraint);
		} else {
			EAnnotation ann = result.getEAnnotation(Environment.OCL_NAMESPACE_URI);
			if (ann == null) {
				ann = EcoreFactory.eINSTANCE.createEAnnotation();
				ann.setSource(Environment.OCL_NAMESPACE_URI);
				result.getEAnnotations().add(ann);
			}

			ann.getContents().add(constraint);
		}

		addHelperOperation(owner, result);

		return result;
	}

	// implements the inherited specification
	@Override
	public void undefine(Object feature) {
		Constraint definition = getDefinition(feature);

		if (definition == null) {
			throw new IllegalArgumentException(
				"not an additional feature: " + feature); //$NON-NLS-1$
		}

		EcoreUtil.remove((EObject) feature);
		EcoreUtil.remove(definition);

		definition.getConstrainedElements().clear();
		resetTypeCaches();
	}

	@Override
	public Constraint getDefinition(Object feature) {
		Constraint result = null;
		ETypedElement typedFeature = (ETypedElement) feature;

		EAnnotation ann = typedFeature.getEAnnotation(
			Environment.OCL_NAMESPACE_URI);

		if ((ann != null) && !ann.getContents().isEmpty()) {
			for (EObject o : ann.getContents()) {
				if ((o instanceof Constraint)
						&& UMLReflection.DEFINITION.equals(((Constraint) o).getStereotype())) {

					result = (Constraint) o;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Checks that the given registry is free from unstable entries that might cause a RuntimeException.
	 *
	 * Developers may invoke this routine to check the registry passed to EcoreEnvironment or EcoreEnvironmentFactory constructors.
	 *
	 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=544165#c14 for the rationale of this method.
	 *
	 * @param registry the EPackage.Registry to check
	 *
	 * @return a message detailing problems found, or null if all ok.
	 *
	 * @since 3.14
	 */
	@SuppressWarnings("nls")
	static public String checkRegistry(EPackage.Registry registry) {
		Set<Object> oldRegistry = new HashSet<Object>(registry.values());
		try {
			for (Object next : oldRegistry) {
				if (next instanceof EPackage.Descriptor) {
					next = ((EPackage.Descriptor)next).getEPackage();
				}
				if (next instanceof EPackage) {
					@SuppressWarnings("unused")
					EPackage ePackage = (EPackage) next;
				}
			}
			return null;
		}
		catch (ConcurrentModificationException e) {
			Set<Object> newRegistry = new HashSet<Object>(registry.values());
			newRegistry.removeAll(oldRegistry);
			StringBuilder s = new StringBuilder();
			s.append(e.toString());
			s.append("The following registry entries were not properly registered\n");
			s.append("Please raise Bugzillas against the entry providers.\n");
			for (Object o : newRegistry) {
				s.append("\t" + o.toString());
			}
			s.append("\nSee https://bugs.eclipse.org/bugs/show_bug.cgi?id=544165#c14 for more details.\n");
			return s.toString();
		}
		catch (RuntimeException e) {
			StringBuilder s = new StringBuilder();
			s.append(e.toString());
			s.append("\nSee https://bugs.eclipse.org/bugs/show_bug.cgi?id=544165#c14 for more details.\n");
			return s.toString();
		}
	}

	/**
	 * Looks in the EMF registry for a package with the specified qualified
	 * package name. Uses the global package registry.
	 *
	 * @param packageNames
	 *            the qualified package name
	 * @return the matching EPackage, or <code>null</code> if not found
	 */
	static public EPackage findPackage(List<String> packageNames) {
		return findPackage(packageNames, EPackage.Registry.INSTANCE);
	}

	/**
	 * Looks in the given registry for a package with the specified qualified
	 * package name.
	 *
	 * @param packageNames
	 *            the qualified package name
	 * @param registry the EPackage.Registry to look in
	 * @return the matching EPackage, or <code>null</code> if not found
	 */
	static public EPackage findPackage(List<String> packageNames, EPackage.Registry registry) {
		if (packageNames.isEmpty()) {
			return null;
		}

		if (OCL_PACKAGES.containsKey(packageNames)) {
			return OCL_PACKAGES.get(packageNames);
		}
		//
		//	First pass, just traverse the registry to find a match, optionally reporting bad entries.
		//
		Set<String> searchedPackages = null;
		boolean reportProblems = !findPackageHasReportedProblems;
		String name = packageNames.get(0);
		String key = ""; //$NON-NLS-1$
		try {
			for (Map.Entry<String,Object> entry : registry.entrySet()) {
				key = entry.getKey();							// Cache the current key in case we need to report a CME
				try {
					Object next = entry.getValue();
					if (next instanceof EPackage.Descriptor) {
						next = ((EPackage.Descriptor)next).getEPackage();
					}
					if (next instanceof EPackage) {
						EPackage ePackage = (EPackage) next;
						// only consider root-level packages when searching by name
						if ((ePackage.getESuperPackage() == null)
								&& EcoreForeignMethods.isNamed(name, ePackage)) {
							EPackage tentativeResult = findNestedPackage(
								packageNames.subList(1, packageNames.size()),
								ePackage);
							if (tentativeResult != null) {
								return tentativeResult;
							}
						}
					}
				}
				catch (Throwable t) {							// See bug 552870
					if (reportProblems) {
						if (searchedPackages == null) {
							searchedPackages = new HashSet<String>();
						}
						searchedPackages.add(entry.getKey());	// Cache errors in first pass to avoid repeated errors in second pass
						findPackageHasReportedProblems = true;
						System.err.println("OCL: erroneous global EPackage registry entry.\n" + t.toString()); //$NON-NLS-1$
					}
				}
			}
		}
		//
		//	Further passes after a CME, re-traverse the registry to find a match, optionally reporting bad entries
		//	Repeating until a cache of all visited keys is the same size as the registry.
		//
		catch (ConcurrentModificationException cme1) {				// See Bug 544165
			System.err.println("OCL: unstable global EPackage registry for '" + key + "'.\n  (See https://bugs.eclipse.org/bugs/show_bug.cgi?id=544165#c14)\n  Use org.eclipse.ocl.ecore.EcoreEnvironment.checkRegistry() to diagnose the offending contribution.\n" + cme1.toString()); //$NON-NLS-1$ //$NON-NLS-2$
			if (searchedPackages == null) {
				searchedPackages = new HashSet<String>();
			}
			while (registry.size() > searchedPackages.size()) {
				try {
					for (Map.Entry<String,Object> entry : registry.entrySet()) {
						key = entry.getKey();
						if (searchedPackages.add(key)) {
							try {
								Object next = entry.getValue();
								if (next instanceof EPackage.Descriptor) {
									next = ((EPackage.Descriptor)next).getEPackage();
								}
								if (next instanceof EPackage) {
									EPackage ePackage = (EPackage) next;
									// only consider root-level packages when searching by name
									if ((ePackage.getESuperPackage() == null)
											&& EcoreForeignMethods.isNamed(name, ePackage)) {
										EPackage tentativeResult = findNestedPackage(
											packageNames.subList(1, packageNames.size()),
											ePackage);
										if (tentativeResult != null) {
											return tentativeResult;
										}
									}
								}
							}
							catch (Throwable t) {
								if (reportProblems) {
									findPackageHasReportedProblems = true;
									System.err.println("OCL: erroneous global EPackage registry entry.\n" + t.toString()); //$NON-NLS-1$
								}
							}
						}
					}
				}
				catch (ConcurrentModificationException cme2) {				// See Bug 544165
					System.err.println("OCL: unstable global EPackage registry for '" + key + "'.\n  (See https://bugs.eclipse.org/bugs/show_bug.cgi?id=544165#c14)\n  Use org.eclipse.ocl.ecore.EcoreEnvironment.checkRegistry() to diagnose the offending contribution.\n" + cme2.toString()); //$NON-NLS-1$ //$NON-NLS-2$
					// go round again - eventually searchedPackages.size() == registry.size()
				}
			}
		}
		return findPackageByNSPrefix(packageNames, registry);
	}

	/**
	 * Looks in the given registry for an 'nsURI' matching the first element in <tt>packageNames</tt>.
	 * If found, further elements of <tt>packageNames</tt> identify nested packages.
	 * <t>
	 * This search supports the {@link ParsingOptions.LOOKUP_PACKAGE_BY_ALIAS} strategy.
	 *
	 * @param packageNames
	 *            the qualified package name
	 * @param registry
	 *            the EPackage.Registry to look in
	 * @return the matching EPackage, or <code>null</code> if not found
	 */
	static private EPackage findPackageByAlias(List<String> packageNames, EPackage.Registry registry) {
		if (packageNames.isEmpty()) {
			return null;
		}
		String name = packageNames.get(0);
		EPackage ePackage = registry.getEPackage(name);
		if (ePackage != null) {
			List<String> packageSubList = packageNames.subList(1, packageNames.size());
			ePackage = findNestedPackage(packageSubList, ePackage);
		}
		return ePackage;
	}

	/**
	 * Looks in the given package for a nested package with the specified relative
	 * package name.
	 *
	 * @param packageNames
	 *            the relativ package name
	 * @param epackage the starting package to look in
	 * @return the matching EPackage, or <code>null</code> if not found
	 */
	private static EPackage findNestedPackage(
			List<String> packageNames,
			EPackage epackage) {

		EPackage result = epackage;

		for (String name : packageNames) {
			result = EcoreForeignMethods.getESubpackage(result, name);

			if (result == null) {
				break;
			}
		}

		return result;
	}

	/**
	 * Looks in the given registry for a package with the specified qualified
	 * package name, matching the name against the namespace prefixes of the
	 * packages in the registry.
	 *
	 * @param packageNames
	 *            the qualified package name
	 * @param registry the EPackage.Registry to look in
	 * @return the NSPrefix-matching EPackage, or <code>null</code> if not found
	 */
	private static EPackage findPackageByNSPrefix(
			List<String> packageNames,
			EPackage.Registry registry) {

		StringBuilder stringBuffer = new StringBuilder();
		Iterator<String> it = packageNames.iterator();
		while (it.hasNext()) {
			stringBuffer.append(it.next());
			if (it.hasNext()) {
				stringBuffer.append(".");//$NON-NLS-1$
			}
		}

		String nsPrefix = stringBuffer.toString();

		try {
			for (Object next : registry.values()) {
				if (next instanceof EPackage) {
					EPackage ePackage = (EPackage) next;
					if (nsPrefix.equals(ePackage.getNsPrefix())) {
						return ePackage;
					}
				}
			}
		}
		catch (ConcurrentModificationException e) {				// See Bug 544165
			System.err.println(e.toString() + "\nThe EPackage registry is unstable.\nUse org.eclipse.ocl.ecore.EcoreEnvironment.checkRegistry to diagnose the offending contribution."); //$NON-NLS-1$
			for (Object next : new ArrayList<Object>(registry.values())) {
				if (next instanceof EPackage) {
					EPackage ePackage = (EPackage) next;
					if (nsPrefix.equals(ePackage.getNsPrefix())) {
						return ePackage;
					}
				}
			}
		}
		return null;
	}

	// implements the inherited specification
	@Override
	public boolean isInPostcondition(
			org.eclipse.ocl.expressions.OCLExpression<EClassifier> exp) {

		Constraint constraint = null;
		EObject parent = exp;
		while (parent != null) {
			if (parent instanceof Constraint) {
				constraint = (Constraint) parent;
				break;
			}

			parent = parent.eContainer();
		}

		return (constraint != null)
				&& UMLReflection.POSTCONDITION.equals(constraint.getStereotype());
	}

	// implements the interface method
	/**
	 * @since 3.1
	 */
	@Override
	public Variable<EClassifier, EParameter> lookupImplicitSourceForOppositeProperty(String name) {
		Variable<EClassifier, EParameter> vdcl;

		for (int i = getElementsSize() - 1; i >= 0; i--) {
			VariableEntry element = getElement(i);
			vdcl = element.getVariable();
			EClassifier owner = vdcl.getType();

			if (!element.isExplicit() && (owner != null)) {
				EReference property = safeTryLookupOppositeProperty(owner, name);
				if (property != null) {
					return vdcl;
				}
			}

		}

		// try the "self" variable, last
		vdcl = getSelfVariable();
		if (vdcl != null) {
			EClassifier owner = vdcl.getType();
			if (owner != null) {
				EReference property = safeTryLookupOppositeProperty(owner, name);
				if (property != null) {
					return vdcl;
				}
			}
		}

		return null;

	}

	/**
	 * Looks up a non-navigable association end on behalf of
	 * the specified <code>owner</code> classifier (which is at that end).
	 *
	 * @param owner
	 *            a classifier in the context of which the property is used
	 * @param name
	 *            the end name to look up
	 *
	 * @return the non-navigable end, or <code>null</code> if it cannot
	 *         be found
	 *
	 * @throws LookupException in case that multiple non-navigable properties
	 *     are found that have the same name and the problem option is ERROR
	 *     or worse
	 * @since 3.1
	 */
	@Override
	public EReference lookupOppositeProperty(EClassifier owner, String name) throws LookupException {
		if (owner == null) {
			Variable<EClassifier, EParameter> vdcl = lookupImplicitSourceForOppositeProperty(name);

			if (vdcl == null) {
				return null;
			}

			owner = vdcl.getType();
		}

		List<EReference> matches = new java.util.ArrayList<EReference>(2);
		findOppositeEnds(owner, name, matches);

		if (matches.isEmpty()) {
			return null;
		} else if (matches.size() > 1) {
			// ambiguous matches.  What to do?
			if (notOK(ProblemOption.AMBIGUOUS_ASSOCIATION_ENDS)) {
				ProblemHandler.Severity sev = getValue(ProblemOption.AMBIGUOUS_ASSOCIATION_ENDS);

				// will have to report the problem
				String message = OCLMessages.bind(OCLMessages.Ambig_AssocEnd_,
					name, getUMLReflection().getName(owner));

				if (sev.getDiagnosticSeverity() >= Diagnostic.ERROR) {
					throw new AmbiguousLookupException(message, matches);
				} else {
					getProblemHandler().analyzerProblem(sev, message,
						"lookupNonNavigableProperty", -1, -1); //$NON-NLS-1$
				}
			}
		}

		return matches.get(0);
	}

	/**
	 * This default implementation simply delegates to the
	 * {@link #lookupOppositeProperty(EClassifier, String)} method.
	 *
	 * @since 3.1
	 */
	public EReference tryLookupOppositeProperty(EClassifier owner, String name)
			throws LookupException {
		EReference result = lookupOppositeProperty(owner, name);
		if ((result == null) && AbstractOCLAnalyzer.isEscaped(name)) {
			result = lookupOppositeProperty(owner, AbstractOCLAnalyzer.unescape(name));
		}
		return result;
	}

	/**
	 * This default implementation simply delegates to the
	 * {@link Environment#lookupProperty(Object, String)} method.
	 * @since 3.1
	 */
	@Override
	public EStructuralFeature tryLookupProperty(EClassifier owner, String name)
			throws LookupException {

		EStructuralFeature result = lookupProperty(owner, name);
		// look up non-navigable/unnamed ends in any case because they may be located in
		// a specialization of result's owner, hence take precedence over
		// result:
		EReference nonNavigableEnd = (EReference) lookupNonNavigableEnd(owner, name);
		if ((nonNavigableEnd == null) && AbstractOCLAnalyzer.isEscaped(name)) {
			nonNavigableEnd = (EReference) lookupNonNavigableEnd(owner,
				AbstractOCLAnalyzer.unescape(name));
		}
		if (result != null) {
			// Ambiguous hidden opposite ends may have been found.
			// Don't consider unnamed opposite ends if a named "real" end has
			// already been found
			if (nonNavigableEnd != null && nonNavigableEnd.getName() != null) {
				EReference nonNavigableEndOpposite = nonNavigableEnd.getEOpposite();
				// check for ambiguity; note that nonNavigableEnd may be a
				// temporary property which doesn't have a container set; type
				// therefore needs
				// to be determined through opposite
				EClassifier nonNavigableEndOwner = TypeUtil.getPropertyType(this, null,
					nonNavigableEndOpposite);
				if (getUMLReflection().getAllSupertypes(nonNavigableEndOwner)
						.contains(getUMLReflection().getOwningClassifier(result))) {
					result = nonNavigableEnd;
				} else if (!getUMLReflection().getAllSupertypes(
					getUMLReflection().getOwningClassifier(result)).contains(
						TypeUtil.getPropertyType(this, null, nonNavigableEnd.getEOpposite()))) {
					ProblemHandler.Severity sev = getValue(ProblemOption.AMBIGUOUS_ASSOCIATION_ENDS);
					// will have to report the problem
					String message = OCLMessages.bind(
						OCLMessages.Ambig_AssocEnd_, name, getUMLReflection()
						.getName(owner));
					if (sev.getDiagnosticSeverity() >= Diagnostic.ERROR) {
						List<EStructuralFeature> ambiguousMatches = new ArrayList<EStructuralFeature>();
						ambiguousMatches.add(result);
						ambiguousMatches.add(nonNavigableEnd);
						throw new AmbiguousLookupException(message,
							ambiguousMatches);
					} else {
						getProblemHandler().analyzerProblem(sev, message,
							"lookupNonNavigableProperty", -1, -1); //$NON-NLS-1$
					}
				}
			}
		}
		if (result == null) {
			result = nonNavigableEnd;
		}

		return result;
	}

	/**
	 * Wrapper for the "try" operation that doesn't throw, but just returns the
	 * first ambiguous match in case of ambiguity.
	 */
	private EReference safeTryLookupOppositeProperty(EClassifier owner, String name) {
		EReference result = null;
		try {
			result = lookupOppositeProperty(owner, name);
			if ((result == null) && AbstractOCLAnalyzer.isEscaped(name)) {
				result = lookupOppositeProperty(owner, AbstractOCLAnalyzer.unescape(name));
			}
		} catch (LookupException e) {
			if (!e.getAmbiguousMatches().isEmpty()) {
				result = (EReference) e.getAmbiguousMatches().get(0);
			}
		}

		return result;
	}

	/**
	 * Searches for non-navigable association ends with the specified
	 * <tt>name</tt> at the given <tt>classifier</tt>'s end of an association.
	 * Subclasses should reimplement this method if they support non-navigable
	 * association ends.
	 *
	 * @param classifier a classifier at an association end
	 * @param name the non-navigable end name to look for
	 * @param ends collects the ends found by the subclass implementation
	 * @since 3.1
	 */
	protected void findOppositeEnds(EClassifier classifier, String name,
			List<EReference> ends) {
		if (oppositeEndFinder != null) {
			oppositeEndFinder.findOppositeEnds(classifier, name, ends);
		}
	}

	/**
	 * If a "hidden" opposite is found (because a forward reference has a "Property.oppositeRoleName"
	 * annotation specifying a name for the opposite role), this method adds a temporary
	 * {@link EReference} to the <code>ends</code> list which has no container set and as its
	 * opposite uses the forward reference. The name of this temporary reference is set to
	 * <code>name</code> which equals the value of the "Property.oppositeRoleName" annotation
	 * on the forward reference.<p>
	 *
	 * The temporary reference is not a valid reference for at least two reasons: its container
	 * is not set and its opposite does not name it as its opposite.
	 * @since 3.1
	 */
	@Override
	protected void findNonNavigableAssociationEnds(EClassifier classifier,
			String name, List<EStructuralFeature> ends) {
		List<EReference> hiddenOpposites = new ArrayList<EReference>();
		if (oppositeEndFinder != null) {
			oppositeEndFinder.findOppositeEnds(classifier, name, hiddenOpposites);
		}
		for (EReference forwardRef : hiddenOpposites) {
			EReference temporaryOppositeRef = EcoreFactory.eINSTANCE.createEReference();
			temporaryOppositeRef.setEOpposite(forwardRef);
			temporaryOppositeRef.setName(name);
			temporaryOppositeRef.setEType(forwardRef.getEContainingClass());
			ends.add(temporaryOppositeRef);
		}
	}

	/**
	 * @since 3.1
	 */
	@Override
	public EClassifier getOppositePropertyType(EClassifier owner,
			EReference property) {
		return ((UMLReflectionImpl) getUMLReflection()).getOCLCollectionType(
			property.getEContainingClass(),
			/* ordered */false, /* unique */false);
	}

	/**
	 * @since 3.1
	 */
	@Override
	public Map<String, EReference> getHiddenOppositeProperties(
			EClassifier classifier) {
		Map<String, EReference> result;
		if (oppositeEndFinder == null) {
			result = Collections.emptyMap();
		} else {
			result = oppositeEndFinder.getAllOppositeEnds(classifier);
		}
		return result;
	}

	/**
	 * @since 3.1
	 */
	public OppositeEndFinder getOppositeEndFinder() {
		return oppositeEndFinder;
	}

	/**
	 * @since 3.22
	 */
	@Override
	public OCL createOCL() {
		return OCL.newInstance(this);
	}

	/**
	 * @since 3.22
	 */
	@Override
	public ToStringVisitor createToStringVisitor(Environment<?, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, ?, ?> environment) {
		return new ToStringVisitor(environment);
	}
}