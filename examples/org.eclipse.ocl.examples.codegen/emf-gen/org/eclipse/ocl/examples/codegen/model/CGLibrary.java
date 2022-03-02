/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************
 * This code is 100% auto-generated
 * from: /org.eclipse.ocl.examples.codegen/model/CGLibrary.oclstdlib
 * by: org.eclipse.ocl.examples.build.xtend.generateOCLstdlib.xtend
 * and: org.eclipse.ocl.examples.build.GenerateOCLstdlibModel.mwe2
 *
 * Do not edit it.
 *******************************************************************************/
package	org.eclipse.ocl.examples.codegen.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.AbstractContents;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLmetamodel;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * This is the http://www.eclipse.org/ocl/2015/CGLibrary Standard Library
 * auto-generated from /org.eclipse.ocl.examples.codegen/model/CGLibrary.oclstdlib.
 * It facilitates efficient library loading without the overheads of model reading.
 * <p>
 * This Standard Library may be registered as the definition of a Standard Library for
 * the OCL evaluation framework by invoking {@link #install}.
 * <p>
 * The Standard Library is normally activated when the MetamodelManager attempts
 * to locate a library type when its default Standard Library URI is the same
 * as this Standard Library.
 */
@SuppressWarnings("unused")
public class CGLibrary extends ASResourceImpl
{
	/**
	 *	The static package-of-types pivot model of the Standard Library.
	 */
	private static CGLibrary INSTANCE = null;

	/**
	 *	The URI of this Standard Library.
	 */
	public static final @NonNull String STDLIB_URI = "http://www.eclipse.org/ocl/2015/CGLibrary";

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/CGLibrary standard Library Resource.
	 *  This static definition auto-generated from /org.eclipse.ocl.examples.codegen/model/CGLibrary.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 * It cannot be unloaded or rather unloading has no effect.
	 */
	public static @NonNull CGLibrary getDefault() {
		CGLibrary oclstdlib = INSTANCE;
		if (oclstdlib == null) {
			Contents contents = new Contents("http://www.eclipse.org/ocl/2015/Library");
			String asURI = STDLIB_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION;
			oclstdlib = INSTANCE = new ReadOnly(asURI, contents.getModel());
		}
		return oclstdlib;
	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/CGLibrary standard Library model.
	 *  This static definition auto-generated from /org.eclipse.ocl.examples.codegen/model/CGLibrary.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 */
	public static @NonNull Model getDefaultModel() {
		Model model = (Model)(getDefault().getContents().get(0));
		assert model != null;
		return model;
	}

	/**
	 * Return the default http://www.eclipse.org/ocl/2015/CGLibrary standard Library package.
	 *  This static definition auto-generated from /org.eclipse.ocl.examples.codegen/model/CGLibrary.oclstdlib
	 *  is used as the default when no overriding copy is registered.
	 */
	public static @NonNull Package getDefaultPackage() {
		Package pkge = getDefaultModel().getOwnedPackages().get(0);
		assert pkge != null;
		return pkge;
	}

	/**
	 * Install this library in the {@link StandardLibraryContribution#REGISTRY}.
	 * This method may be invoked by standalone applications to replicate
	 * the registration that should appear as a standard_library plugin
	 * extension when running within Eclipse.
	 */
	public static void install() {
		StandardLibraryContribution.REGISTRY.put(STDLIB_URI, new Loader());
	}

	/**
	 * Install this library in the {@link StandardLibraryContribution#REGISTRY}
	 * unless some other library contribution has already been installed.
	 */
	public static void lazyInstall() {
		if (StandardLibraryContribution.REGISTRY.get(STDLIB_URI) == null) {
			install();
		}
	}

	/**
	 * Unnstall this library from the {@link StandardLibraryContribution#REGISTRY}.
	 * This method may be invoked by standalone applications to release the library
	 * resources for garbage collection and memory leakage detection.
	 */
	public static void uninstall() {
		StandardLibraryContribution.REGISTRY.remove(STDLIB_URI);
		INSTANCE = null;
	}

	/**
	 * The Loader shares the Standard Library instance whenever this default library
	 * is loaded from the registry of Standard Libraries populated by the standard_library
	 * extension point.
	 */
	public static class Loader implements StandardLibraryContribution
	{
		@Override
		public @NonNull StandardLibraryContribution getContribution() {
			return this;
		}

		@Override
		public @NonNull Resource getResource() {
			return getDefault();
		}
	}

	/**
	 * A ReadOnly CGLibrary overrides inherited functionality to impose immutable shared behaviour.
	 *
	 * @since 1.5
	 */
	protected static class ReadOnly extends CGLibrary implements ImmutableResource
	{
		protected ReadOnly(@NonNull String asURI, @NonNull Model libraryModel) {
			super(asURI, libraryModel);
			setSaveable(false);
		}

		/**
		 * Overridden to inhibit entry of the shared instance in any ResourceSet.
		 */
		@Override
		public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
			return notifications;
		}

		/**
		 * Overridden to inhibit unloading of the shared instance.
		 */
		@Override
		protected void doUnload() {}

		@Override
		public boolean isCompatibleWith(@NonNull String metamodelURI) {
			return OCLmetamodel.PIVOT_URI.equals(metamodelURI);
		}

		/**
		 * Overridden to trivialise loading of the shared instance.
		 */
		@Override
		public void load(Map<?, ?> options) throws IOException {
			if (this != INSTANCE) {
				super.load(options);
			}
			else {
				setLoaded(true);
			}
		}

		/**
		 * Overridden to inhibit unloading of the shared instance.
		 */
		@Override
		protected Notification setLoaded(boolean isLoaded) {
			if (isLoaded) {
				return super.setLoaded(isLoaded);
			}
			else {
				return null;
			}
		}
	}

	/**
	 *	Construct a copy of the OCL Standard Library with specified resource URI,
	 *  and package name, prefix and namespace URI.
	 */
	public static @NonNull CGLibrary create(@NonNull String asURI) {
		Contents contents = new Contents(asURI);
		return new CGLibrary(asURI, contents.getModel());
	}

	/**
	 *	Construct an OCL Standard Library with specified resource URI and library content.
	 */
	private CGLibrary(@NonNull String asURI, @NonNull Model libraryModel) {
		super(ClassUtil.nonNullState(URI.createURI(asURI)), OCLASResourceFactory.getInstance());
		assert PivotUtilInternal.isASURI(asURI);
		getContents().add(libraryModel);
	}

	private static class Contents extends AbstractContents
	{
		private final @NonNull Model model;
		private final @NonNull Library ocl;

		private Contents(@NonNull String asURI)
		{
			model = createModel(asURI);
			ocl = createLibrary("ocl", "ocl", "http://www.eclipse.org/ocl/2015/Library", IdManager.METAMODEL);
			installPackages();
			installPrimitiveTypes();
			installOperations();
			installComments();
		}

		public @NonNull Model getModel() {
			return model;
		}

		private final @NonNull Package _ocl = getPackage(org.eclipse.ocl.pivot.model.OCLstdlib.getDefaultModel(), "ocl");
		private final @NonNull Class _Boolean = getPrimitiveType(_ocl, "Boolean");
		private final @NonNull Class _Integer = getPrimitiveType(_ocl, "Integer");
		private final @NonNull AnyType _OclAny = getAnyType(_ocl, "OclAny");
		private final @NonNull Class _OclElement = getClass(_ocl, "OclElement");
		private final @NonNull Class _String = getPrimitiveType(_ocl, "String");

		private void installPackages() {
			model.getOwnedPackages().add(ocl);
			model.getOwnedImports().add(createImport(null, _ocl));
		}

		private final @NonNull PrimitiveType _String_1 = createPrimitiveType("String");

		private void installPrimitiveTypes() {
			List<Class> ownedClasses;
			PrimitiveType type;

			ownedClasses = ocl.getOwnedClasses();
			ownedClasses.add(type = _String_1);
		}

		private final @NonNull Operation op_String_getSeverity = createOperation("getSeverity", _Integer, "org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation", org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation.INSTANCE);
		private final @NonNull Operation op_String_logDiagnostic = createOperation("logDiagnostic", _Boolean, "org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation", org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation.INSTANCE);
		private final @NonNull Operation op_String_logDiagnostic_1 = createOperation("logDiagnostic", _Boolean, "org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation", org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation.INSTANCE);

		private void installOperations() {
			List<Operation> ownedOperations;
			List<Parameter> ownedParameters;
			Operation operation;
			Parameter parameter;

			ownedOperations = _String_1.getOwnedOperations();
			ownedOperations.add(operation = op_String_getSeverity);
			ownedOperations.add(operation = op_String_logDiagnostic);
			operation.setIsValidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", _OclAny, true));
			ownedParameters.add(parameter = createParameter("diagnostics", _OclAny, false));
			ownedParameters.add(parameter = createParameter("context", _OclAny, false));
			ownedParameters.add(parameter = createParameter("severity", _Integer, true));
			ownedParameters.add(parameter = createParameter("status", _Boolean, false));
			ownedParameters.add(parameter = createParameter("code", _Integer, true));
			ownedOperations.add(operation = op_String_logDiagnostic_1);
			operation.setIsValidating(true);
			ownedParameters = operation.getOwnedParameters();
			ownedParameters.add(parameter = createParameter("object", _OclAny, true));
			ownedParameters.add(parameter = createParameter("feature", _OclAny, false));
			ownedParameters.add(parameter = createParameter("diagnostics", _OclAny, false));
			ownedParameters.add(parameter = createParameter("context", _OclAny, false));
			ownedParameters.add(parameter = createParameter("message", _String, false));
			ownedParameters.add(parameter = createParameter("severity", _Integer, true));
			ownedParameters.add(parameter = createParameter("status", _OclAny, false));
			ownedParameters.add(parameter = createParameter("code", _Integer, true));
		}

		private void installComments() {
		}
	}
}
