package org.eclipse.ocl.examples.xtext.oclstdlib.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider;
import org.osgi.framework.Bundle;

/**
 * A NonXtextResourceSetBasedProjectProvider enhances an
 * XtextResourceSetBasedProjectProvider to locate a plausible Java
 * Project for JvmType resolution when running in a non-Xtext rooted
 * context such as the Sample Ecore Editor.
 */
public class NonXtextResourceSetBasedProjectProvider
		extends XtextResourceSetBasedProjectProvider {
	
	@Override
	public IJavaProject getJavaProject(ResourceSet resourceSet) {
		IJavaProject javaProject = super.getJavaProject(resourceSet);
		if (javaProject == null) {
			for (Resource resource : resourceSet.getResources()) {
				URI uri = resource.getURI();
				if (uri.isPlatformResource()) {
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
	                IProject iProject = workspace.getRoot().getProject(uri.segments()[1]);
	                if (iProject != null) {
		            	javaProject = JavaCore.create(iProject);
		            	break;
	                }
				}
				else if (uri.isPlatformPlugin()) {
		            final Bundle bundle = Platform.getBundle(uri.segment(1));
		            if (bundle != null) {
		            	// FIXME 
		            }
				}
			}
		}
		return javaProject;
	}

}
