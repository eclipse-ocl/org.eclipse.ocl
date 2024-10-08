/*******************************************************************************
 * Copyright (c) 2006, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.uml;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.internal.helper.PluginFinder;
import org.eclipse.ocl.uml.util.OCLUMLUtil;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

/**
 * Convenient subclass of the <code>OCL</code> fa&ccedil;ade that binds the
 * UML metamodel to the superclass's generic type parameters.  This frees
 * client code from the long list of parameter substitutions.  This subclass
 * also provides a shortcut to creating an <code>OCL</code> on a
 * {@link UMLEnvironmentFactory} instance using its own resource set.
 * <p>
 * The parser environments created by the UML environment factory support OCL
 * expressions at both the metamodel (M2) and user model (M1) levels.  For
 * user model constraints, use the classifier in the model as the context
 * classifier in invocations of this <code>OCL</code> or an {@link OCLHelper}
 * created by it.  For metamodel OCL (used, for example, to query the user
 * model, itself) use the metaclass of any element in the user model as the
 * context classifier.  A convenient way to obtain this metaclass is via the
 * {@link OCLUMLUtil#getMetaclass(org.eclipse.uml2.uml.Element)} method.
 * </p>
 *
 * @author Christian W. Damus (cdamus)
 *
 * @see UMLEnvironmentFactory
 */
public class OCL extends org.eclipse.ocl.OCL<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> {

	/**
	 * Initialize registries to support OCL and UML usage. This method is
	 * intended for initialization of standalone behaviors for which plugin extension
	 * registrations have not been applied.
	 *<p>
	 * A null resourceSet may be provided to initialize the global package registry
	 * and global URI mapping registry.
	 *<p>
	 * A non-null resourceSet may be provided to identify specific package
	 * and global URI mapping registries.
	 * <p>
	 * This method is used to configure the ResourceSet used to load the OCL Standard Library.

	 * @param resourceSet to be initialized or null for global initialization
	 * @return a failure reason, null if successful
	 *
	 * @since 3.0
	 */
	public static String initialize(ResourceSet resourceSet) {
		UMLResourcesUtil.init(resourceSet);
		final String oclPluginId = "org.eclipse.ocl.uml"; //$NON-NLS-1$
		final String resourcesPluginId = "org.eclipse.uml2.uml.resources"; //$NON-NLS-1$
		String oclLocation = System.getProperty(oclPluginId);
		String resourcesLocation = System.getProperty(resourcesPluginId);
		if ((oclLocation == null) || (resourcesLocation == null)) {
			PluginFinder pluginFinder = new PluginFinder(oclPluginId, resourcesPluginId);
			pluginFinder.resolve();
			if (oclLocation == null) {
				oclLocation = pluginFinder.get(oclPluginId);
				if (oclLocation == null) {
					return "'" + oclPluginId + "' not found on class-path"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			if (resourcesLocation == null) {
				resourcesLocation = pluginFinder.get(resourcesPluginId);
				if (resourcesLocation == null) {
					return "'" + resourcesPluginId + "' not found on class-path"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		Map<URI, URI> uriMap = resourceSet != null
			? resourceSet.getURIConverter().getURIMap()
			: URIConverter.URI_MAP;
		uriMap.put(URI.createURI(UMLEnvironment.OCL_STANDARD_LIBRARY_NS_URI), createURI(oclLocation, "/model/oclstdlib.uml")); //$NON-NLS-1$
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), createURI(resourcesLocation, "/profiles/")); //$NON-NLS-1$
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), createURI(resourcesLocation, "/metamodels/")); //$NON-NLS-1$
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), createURI(resourcesLocation, "/libraries/")); //$NON-NLS-1$
		return null;
	}

    private static URI createURI(String locationURI, String string) {
		while (locationURI.endsWith("/")) { //$NON-NLS-1$
			locationURI = locationURI.substring(0, locationURI.length()-1);
		}
		if (locationURI.endsWith(".jar!")) { //$NON-NLS-1$
			return URI.createURI(locationURI + string);
		} else {
			return URI.createFileURI(locationURI + string);
		}
	}

	/**
     * Initializes me with an environment factory for the UML metamodel.
     *
     * @param envFactory my environment factory
     */
	protected OCL(EnvironmentFactory<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> envFactory) {
		super(envFactory);
	}

    /**
     * Initializes me with an initial environment for the UML metamodel.
     *
     * @param env my root environment
     */
	protected OCL(Environment<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> env) {
		super(env);
	}

    /**
     * Initializes me with an environment factory for the UML metamodel and
     * a resource from which to load my root environment.
     *
     * @param envFactory my environment factory
     * @param resource my persisted root environment
     */
	protected OCL(EnvironmentFactory<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> envFactory,
			Resource resource) {
		super(envFactory, resource);
	}

    /**
     * Creates a new <code>OCL</code> using a new UML environment factory
     * that uses a private resource set and the global package registry for
     * looking up Ecore representations of UML packages.
     *
     * @return the new <code>OCL</code>
     */
	public static OCL newInstance() {
		return new OCL(new UMLEnvironmentFactory());
	}

    /**
     * Creates a new <code>OCL</code> using a new UML environment factory
     * that uses the specified resource set and its local package registry for
     * looking up Ecore representations of UML packages.
     *
     * @param rset a resource set containing UML models
     * @return the new <code>OCL</code>
     */
	public static OCL newInstance(ResourceSet rset) {
		return new OCL(new UMLEnvironmentFactory(rset));
	}

    /**
     * Creates a new <code>OCL</code> using the specified UML environment
     * factory.
     *
     * @param envFactory an environment factory for UML
     * @return the new <code>OCL</code>
     */
	public static OCL newInstance(EnvironmentFactory<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> envFactory) {
		return new OCL(envFactory);
	}

    /**
     * Creates a new <code>OCL</code> using the specified initial UML
     * environment.
     *
     * @param env an environment for UML
     * @return the new <code>OCL</code>
     */
	public static OCL newInstance(Environment<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> env) {
		return new OCL(env);
	}

    /**
     * Creates a new <code>OCL</code> using the specified UML environment
     * factory and a resource from which to load the initial environment.
     *
     * @param envFactory an environment factory for UML
     * @param resource the resource containing a persistent environment
     *    (which may be empty for an initially empty environment)
     * @return the new <code>OCL</code>
     */
	public static OCL newInstance(EnvironmentFactory<
			Package, Classifier, Operation, Property,
			EnumerationLiteral, Parameter, State,
			CallOperationAction, SendSignalAction, Constraint,
			Class, EObject> envFactory,
			Resource resource) {
		return new OCL(envFactory, resource);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The return type is narrowed to the UML binding for the generic
	 * <tt>OCLHelper&lt;C, O, P, CT&gt;</tt> type.
	 * </p>
	 */
    @Override
    public Helper createOCLHelper() {
       return new OCLHelperImpl(super.createOCLHelper());
    }

	/**
	 * {@inheritDoc}
	 * <p>
	 * The return type is narrowed to the UML binding for the generic
	 * <tt>Query&lt;C, CLS, E&gt;</tt> type.
	 * </p>
	 */
    @Override
    public Query createQuery(Constraint constraint) {
    	return new QueryImpl(super.createQuery(constraint), this);
    }

	/**
	 * {@inheritDoc}
	 * <p>
	 * The return type is narrowed to the UML binding for the generic
	 * <tt>Query&lt;C, CLS, E&gt;</tt> type.
	 * </p>
	 */
    @Override
    public Query createQuery(org.eclipse.ocl.expressions.OCLExpression<Classifier> query) {
    	return new QueryImpl(super.createQuery(query), this);
    }

    /**
     * Convenient interface aliasing the type parameter substitutions for the
     * UML environment, for ease of typing.
     *
     * @author Christian W. Damus (cdamus)
     */
    public static interface Helper extends OCLHelper<Classifier, Operation, Property, Constraint> {
    	/**
    	 * {@inheritDoc}
    	 * <p>
    	 * The return type is narrowed to the UML binding for the generic
    	 * <tt>OCLExpression&lt;C&gt;</tt> type.
    	 * </p>
    	 */
        OCLExpression createQuery(String expression) throws ParserException;

    	/**
    	 * {@inheritDoc}
    	 * <p>
    	 * The return type is narrowed to the Ecore binding for the generic
    	 * <tt>OCLExpression&lt;PK,C,O,P,EL,PM,S,COA,SSA,CT,CLS,E&gt;</tt> type.
    	 * </p>
    	 */
        OCL getOCL();
    }

    /**
     * Convenient interface aliasing the type parameter substitutions for the
     * UML environment, for ease of typing.
     *
     * @author Christian W. Damus (cdamus)
     */
    public static interface Query extends org.eclipse.ocl.Query<Classifier, Class, EObject> {
    	/**
    	 * {@inheritDoc}
    	 * <p>
    	 * The return type is narrowed to the UML binding for the generic
    	 * <tt>OCLExpression&lt;C&gt;</tt> type.
    	 * </p>
    	 */
    	OCLExpression getExpression();

    	/**
         * <p>
         * Obtains the {@link OCL} that created me.
         * </p>
         *
         * @return my originating <tt>OCL</tt> instance
    	 */
        OCL getOCL();
    }
}
