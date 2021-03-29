/*******************************************************************************
 * Copyright (c) 2013, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui;

import java.net.URL;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class OCLDebugUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.ocl.examples.debug.ui"; //$NON-NLS-1$

	// The shared instance
	private static OCLDebugUIPlugin plugin;

	private static final Logger logger = Logger.getLogger(OCLDebugUIPlugin.class);

	protected ImageRegistry imageDescriptorRegistry;

	/**
	 * The constructor
	 */
	public OCLDebugUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (imageDescriptorRegistry != null) {
			imageDescriptorRegistry.dispose();
			imageDescriptorRegistry = null;
		}
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static OCLDebugUIPlugin getDefault() {
		return plugin;
	}


	public Image createImage(String path) {

		Image image = getImageRegistry().get(path);

		if (image == null) {
			try {
				ImageDescriptor imageDescriptor = getImageDescriptor(path);
				if (imageDescriptor != null) {
					image = getImageDescriptor(path).createImage();
					if (image != null) { getImageRegistry().put(path, image); }
				}
			}
			catch(Exception e) { logger.error("Failed to createImage '" + path + "'", e); }
		}

		return image;
	}

	public ImageDescriptor getImageDescriptor(String path) {

		ImageDescriptor imageDescriptor = getImageDescriptorRegistry().getDescriptor(path);

		if (imageDescriptor == null) {
			URL url = FileLocator.find(getBundle(), new Path(path), Collections.<String, String>emptyMap());
			if (url != null) {
				imageDescriptor = ImageDescriptor.createFromURL(url);
				if (imageDescriptor != null) getImageDescriptorRegistry().put(path, imageDescriptor);
			}
		}

		return imageDescriptor;
	}

	protected ImageRegistry getImageDescriptorRegistry() {
		if (imageDescriptorRegistry == null) {
			imageDescriptorRegistry = createImageRegistry();
		}
		return imageDescriptorRegistry;
	}

	public static BasicDiagnostic createDiagnostic(String message) {
		return new BasicDiagnostic(Diagnostic.OK, PLUGIN_ID, 0, message, null);
	}

	public static Diagnostic createErrorDiagnostic(String message, Throwable throwable) {
		Object[] data = (throwable == null) ? null : new Object [] { throwable };
		return new BasicDiagnostic(Diagnostic.ERROR,  PLUGIN_ID, 0, message, data);
	}

	public static Diagnostic createWarnDiagnostic(String message) {
		return new BasicDiagnostic(Diagnostic.ERROR,  PLUGIN_ID, 0, message, null);
	}

	/**
	 * Indicates that the given diagnostic is neither error or canceled.
	 *
	 * @param diagnostic
	 *            the diagnostic to test
	 * @return <code>true</code> in case of success, <code>false</code>
	 *         otherwise
	 */
	public static boolean isSuccess(Diagnostic diagnostic) {
		int severity = diagnostic.getSeverity();
		return severity != Diagnostic.ERROR && severity != Diagnostic.CANCEL;
	}

	public static void log(int severity, int code, String message, Throwable throwable) {
		//
		// Status ctor requires a non-null message
		String msg = message == null
			? "" //$NON-NLS-1$
			: message;

		try {
			if (getDefault() != null) {
				// Eclipse environment
				log(new Status(severity, PLUGIN_ID, code, msg, throwable));
			} else {
				// not in the Eclipse environment
				//if (shouldTrace()) {
					switch (code) {
						case Diagnostic.WARNING :
							System.err.print("WARNING "); //$NON-NLS-1$
							break;
						case Diagnostic.ERROR :
						case Diagnostic.CANCEL :
							System.err.print("ERROR "); //$NON-NLS-1$
							break;
						default :
							// don't output INFO or OK messages
							return;
					}

					System.err.print(code);
					System.err.print(": "); //$NON-NLS-1$
					System.err.println(message);

					if (throwable != null) {
						throwable.printStackTrace(System.err);
					}
				//}
			}
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
	}

    public static void log(IStatus status) {
    	OCLDebugUIPlugin debugPlugin = getDefault();
		if(debugPlugin != null) {
			debugPlugin.getLog().log(status);
    	}
    }

    public static void log(Throwable e) {
        log(new Status(IStatus.ERROR, PLUGIN_ID, "Exception caught", e)); //$NON-NLS-1$
    }

	public static final Display getStandardDisplay() {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	/**
	 * Returns the active workbench window
	 *
	 * @return the active workbench window
	 */
	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Returns the active workbench shell or <code>null</code> if none
	 *
	 * @return the active workbench shell or <code>null</code> if none
	 */
	public static @Nullable Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	public static IStatus createStatus(int severity, String message) {
		return new Status(severity, PLUGIN_ID, 0, message, null);
	}

	public static IStatus createErrorStatus(String message) {
		return createStatus(IStatus.ERROR, message);
	}
}
