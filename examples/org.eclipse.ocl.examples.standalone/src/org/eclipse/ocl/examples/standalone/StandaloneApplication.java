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

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.emf.validation.validity.export.ValidityExporterRegistry;
import org.eclipse.ocl.examples.standalone.StandaloneCommand.CommandToken;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;

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

	public static void main(String[] args) throws IOException {
		Logger.getRootLogger().addAppender(new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT));
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		StandaloneResponse response = standaloneApplication.execute(args);
		if (response != StandaloneResponse.OK) {
			System.err.println("Standalone Application failed.");
			System.exit(1);
		}
	}

	/** The Resource Set */
	private OCL ocl = null;
	//	private ResourceSet resourceSet = null;

	private final @NonNull StandaloneCommandAnalyzer commandAnalyzer = new StandaloneCommandAnalyzer(this);
	private @Nullable String consoleText = null;

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
	 * Initializes all the needed resource factories to create ecore and ocl
	 * resources in the global registry.
	 */
	public void doEssentialOCLSetup() {
		getOCL();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			EssentialOCLStandaloneSetup.doSetup();
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
	 * @throws IOException
	 */
	public @NonNull StandaloneResponse execute(@NonNull String @NonNull [] args) throws IOException {
		StandaloneCommand command = commandAnalyzer.parse(args);
		if (command == null) {
			return StandaloneResponse.FAIL;
		}
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = command.parse(args);
		if (token2strings == null) {
			return StandaloneResponse.FAIL;
		}
		if (!command.parseCheck(token2strings)) {
			return StandaloneResponse.FAIL;
		}
		boolean isOk = command.analyze(token2strings);
		if (!isOk) {
			return StandaloneResponse.FAIL;
		}
		return command.execute();
	}

	public boolean exists(String logFileName) {
		return getURIConverter().exists(URI.createFileURI(logFileName), null);
	}

	public @NonNull Collection<StandaloneCommand> getCommands() {
		return commandAnalyzer.getCommands();
	}

	public @Nullable String getConsoleText() {
		return consoleText;
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return getOCL().getEnvironmentFactory();
	}

	public @NonNull OCL getOCL() {
		if (ocl == null) {
			ocl = OCL.newInstance(OCL.CLASS_PATH);
		}
		return ocl;
	}

	public @NonNull ResourceSet getResourceSet() {
		return getOCL().getResourceSet();
	}

	public @NonNull URIConverter getURIConverter() {
		return getResourceSet().getURIConverter();
	}

	/**
	 * Return true if console clutter is to be suppressed in a test context.
	 */
	public boolean isTest() {
		return false;
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

	public void setConsoleOutput(@NonNull String consoleText) {
		this.consoleText  = consoleText;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws IOException {
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
