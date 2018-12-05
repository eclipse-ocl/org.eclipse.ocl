/**
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation (based on URIEditorInput)
 */
package org.eclipse.ocl.examples.test.xtext;

import java.io.File;
import java.net.URISyntaxException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IURIEditorInput;

/**
 * An implementation of an {@link org.eclipse.ui.IURIEditorInput} to wrap a
 * {@link org.eclipse.emf.common.util.URI} for test purposes only.
 */
public class EMFURIEditorInput implements IURIEditorInput
{
	private final URI emfURI;

	public EMFURIEditorInput(URI emfURI) {
		this.emfURI = emfURI;
	}

	@Override
	public int hashCode() {
		return emfURI.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof EMFURIEditorInput
				&& emfURI.equals(((EMFURIEditorInput) o).emfURI);
	}

	/**
	 * @return the uri
	 */
	public URI getEMFURI() {
		return emfURI;
	}

	@Override
	public java.net.URI getURI() {
		try {
			return new java.net.URI(emfURI.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns <b>true</b> only if the URI represents a file and if this file
	 * exists.
	 *
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	@Override
	public boolean exists() {
		if (emfURI.isFile()) {
			return new File(emfURI.toFileString()).exists();
		} else {
			if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
				return EclipseUtil.exists(emfURI);
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns the <i>toString</i> value of the associated URI.
	 *
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	@Override
	public String getName() {
		return URI.decode(emfURI.isHierarchical()
			&& emfURI.lastSegment() != null
			? emfURI.lastSegment()
				: emfURI.toString());
	}

	@Override
	public String getToolTipText() {
		return emfURI.toString();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})			// Cast not needed after Mars M6
	public Object getAdapter(Class adapter) {
		if (EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE) {
			Object result = EclipseUtil.getAdatper(adapter, emfURI);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	protected String getBundleSymbolicName() {
		return CommonUIPlugin.getPlugin().getSymbolicName();
	}

	protected static class EclipseUtil
	{
		public static Object getAdatper(Class<?> adapter, URI uri) {
			if ((adapter == IFile.class || adapter == IResource.class)
					&& uri.isPlatformResource()) {
				return ResourcesPlugin.getWorkspace().getRoot()
						.getFile(new Path(uri.toPlatformString(true)));
			}
			else {
				return null;
			}
		}

		public static boolean exists(URI uri) {
			if (uri.isPlatformResource()) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				Path path = new Path(uri.toPlatformString(true));
				IFile file = root.getFile(path);
				if (file != null) {
					return file.exists();
				}
			}
			else if (uri.isPlatformPlugin()) {
				return URIConverter.INSTANCE.exists(uri, null);
			}
			return false;
		}
	}
}
