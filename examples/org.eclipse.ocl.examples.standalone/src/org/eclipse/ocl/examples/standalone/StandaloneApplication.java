/*******************************************************************************
 * Copyright (c) 2014, 2019 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.export.ValidityExporterRegistry;
import org.eclipse.ocl.examples.standalone.StandaloneCommand.CommandToken;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;

/**
 * This class executes an OCL evaluation of a model with one or several OCL
 * file(s). This class is intended to be used only in Standalone mode. The
 * result may be saved in a XMI file or exported as a HTML report.<br>
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class StandaloneApplication implements IApplication
{
	/** The arguments Constant. */
	private static final String ARGS_KEY = "application.args"; //$NON-NLS-1$

	public static void main(String[] args) {
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		standaloneApplication.execute(args);
	}

	/** The Resource Set */
	private OCL ocl = null;
	//	private ResourceSet resourceSet = null;

	private final @NonNull StandaloneCommandAnalyzer commandAnalyzer = new StandaloneCommandAnalyzer(this);

	public StandaloneApplication() {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			ValidityExporterRegistry.initialize(ValidityExporterRegistry.INSTANCE);
		}
	}

	/**
	 * Initializes all the needed resource factories to create ecore and ocl
	 * resources in the global registry.
	 */
	public void doCompleteOCLSetup() {
		getOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			CompleteOCLStandaloneSetup.doSetup();
		}

		// Plug the OCL validation mechanism.
		//		OCLDelegateDomain.initialize(resourceSet);
	}

	/**
	 * This launch the application using the entered arguments.
	 *
	 * @param args
	 *            the application arguments.
	 * @return the application return code.
	 */
	public @NonNull StandaloneResponse execute(@NonNull String @NonNull [] args) {
		StandaloneCommand command = commandAnalyzer.parse(args);
		if (command == null) {
			return StandaloneResponse.FAIL;
		}
		Map<CommandToken, List<String>> token2strings = command.parse(args);
		if (token2strings == null) {
			return StandaloneResponse.FAIL;
		}
		boolean isOk = command.check(token2strings);
		if (!isOk) {
			return StandaloneResponse.FAIL;
		}
		return command.execute(token2strings);
	}

	public @NonNull Collection<StandaloneCommand> getCommands() {
		return commandAnalyzer.getCommands();
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return getOCL().getEnvironmentFactory();
	}

	public @NonNull OCL getOCL() {
		if (ocl == null) {
			ocl = OCL.newInstance();
		}
		return ocl;
	}

	public @NonNull ResourceSet getResourceSet() {
		return getOCL().getResourceSet();
	}

	/**
	 * Loads a file and returns The loaded resource.
	 */
	public Resource loadModelFile(URI fileUri) {
		Resource loadedResource = ocl.getResourceSet().getResource(fileUri, true);
		if (!loadedResource.isLoaded()) {
			return null;
		}

		return loadedResource;
	}

	/**
	 * Loads a file and returns The loaded resource.
	 */
	public Resource loadOCLFile(URI oclUri) {
		Resource loadedResource = getResourceSet().getResource(oclUri, true);
		if (!loadedResource.isLoaded()) {
			return null;
		}

		return loadedResource;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) {
		String[] args = (String[]) context.getArguments().get(ARGS_KEY);
		StandaloneResponse applicationCodeResponse = execute(args);
		if (StandaloneResponse.OK.equals(applicationCodeResponse)) {
			return IApplication.EXIT_OK;
		}
		return IApplication.EXIT_RELAUNCH;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		if (ocl != null) {
			ocl.dispose();
			ocl = null;
		}
		// Nothing to do
	}
}
