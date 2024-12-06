/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlib.scoping;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.scoping.AbstractJavaClassScope;
import org.eclipse.ocl.xtext.basecs.BaseCSFactory;
import org.eclipse.ocl.xtext.basecs.JavaClassCS;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleReference;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A JavaClassScope supports lookup of Java class names from the OCLstdlib editor. Names are resolved against
 * a local cache. All names for completion assist are resolved against the full classpath.
 *<p>
 * This provides much less functionality that the Xtext JdtBased/ClasspathBased TypeScopes, but much less is
 * all that is needed.
 */
public class JavaClassScope extends AbstractJavaClassScope
{
	public static boolean SUPPRESS_WORK_THREAD = false;		// Set true to avoid WorkerThread delay when testing

	@Deprecated /* @deprecated No longer used - JavaClassScope is an OCLstdlibCS2AS field rather than CSResource adapter */
	public static @NonNull JavaClassScope getAdapter(@NonNull CSResource csResource, @NonNull ClassLoader classLoader) {
		AbstractJavaClassScope adapter = ClassUtil.getAdapter(AbstractJavaClassScope.class, csResource);
		if (adapter == null) {
			adapter = new JavaClassScope(classLoader);
			csResource.eAdapters().add(adapter);
		}
		return (JavaClassScope) adapter;
	}

	@Deprecated /* @deprecated No longer used - JavaClassScope is an OCLstdlibCS2AS field rather than CSResource adapter */
	public static @NonNull JavaClassScope getAdapter(@NonNull CSResource csResource, @NonNull List<@NonNull ClassLoader> classLoaders) {
		AbstractJavaClassScope adapter = ClassUtil.getAdapter(AbstractJavaClassScope.class, csResource);
		if (adapter == null) {
			adapter = new JavaClassScope(classLoaders);
			csResource.eAdapters().add(adapter);
		}
		return (JavaClassScope) adapter;
	}

	@Deprecated /* @deprecated No longer used - JavaClassScope is an OCLstdlibCS2AS field rather than CSResource adapter */
	public static @NonNull JavaClassScope getAdapter(@NonNull CSResource csResource, @NonNull IProject project) {
		AbstractJavaClassScope adapter = ClassUtil.getAdapter(AbstractJavaClassScope.class, csResource);
		if (adapter == null) {
			adapter = new JavaClassScope(project);
			csResource.eAdapters().add(adapter);
		}
		return (JavaClassScope) adapter;
	}

	/**
	 * ClassLoaders to help resolve references.
	 */
	private final @NonNull List<@NonNull ClassLoader> classLoaders = new ArrayList<@NonNull ClassLoader>();

	/**
	 * IProjects to help resolve references in an Eclipse context.
	 */
	private @Nullable List<@NonNull IProject> projects;

	/**
	 * Map from known class names to their allocated EObjects.
	 */
	private final @NonNull Map<@NonNull String, @NonNull JavaClassCS> name2class = new HashMap<@NonNull String, @NonNull JavaClassCS>();

	private final @NonNull Resource javaResource = new XMIResourceImpl(URI.createURI("JavaClassCS-instances"));

	private boolean doneFullScan = false;

	/* @deprecated use Iterable argument */
	@Deprecated
	public JavaClassScope(@NonNull ClassLoader classLoader) {
		this.classLoaders.add(classLoader);
		this.projects = null;
	}

	public JavaClassScope(@NonNull Iterable<@NonNull ClassLoader> classLoaders) {
		this.projects = null;
		addClassLoaders(classLoaders);
	}

	public JavaClassScope(@NonNull IProject project) {
		this.projects = Lists.newArrayList(project);
	}

	@Override
	public void addClassLoaders(@NonNull Iterable<@NonNull ClassLoader> classLoaders) {
		for (@NonNull ClassLoader classLoader : classLoaders) {
			if (!this.classLoaders.contains(classLoader)) {
				this.classLoaders.add(classLoader);
			}
		}
	}

	public void addProject(@NonNull IProject project) {
		List<@NonNull IProject> projects2 = projects;
		if (projects2 == null) {
			projects = projects2 = new ArrayList<>();
		}
		if (!projects2.contains(project)) {
			projects2.add(project);
		}
	}

	private void doFullScan() {
		Set<@NonNull String> classNames = new HashSet<@NonNull String>(65536);
		for (@NonNull ClassLoader classLoader : classLoaders) {
			if (classLoader instanceof BundleReference) {
				Bundle bundle = ((BundleReference)classLoader).getBundle();
				IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(bundle.getSymbolicName());
				IJavaProject javaProject = JavaCore.create(iProject);
				try {
					IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
					scanJavaElements(packageFragmentRoots, classNames);
				} catch (JavaModelException e) {
				}
			}
		}
		if (projects != null) {
			for (IProject project : projects) {
				IJavaProject javaProject = JavaCore.create(project);
				try {
					IPackageFragmentRoot[] packageFragmentRoots = javaProject.getAllPackageFragmentRoots();
					scanJavaElements(packageFragmentRoots, classNames);
				} catch (JavaModelException e) {
				}
			}
		}
		//		else {
		//			scanClassPath(classNames);
		//			scanBundles(classNames);
		//		}
		for (@NonNull String className : classNames) {
			getEObjectDescription(className);
		}
	}

	@Override
	@Deprecated /* @deprecated not used */
	public void getAdapter(@NonNull CSResource importedResource) {
		if (classLoaders.size() > 0) {
			getAdapter(importedResource, classLoaders);
		}
		else if (projects != null) {
			for (IProject project : projects) {
				getAdapter(importedResource, project);
			}
		}
	}

//	@Override
//	public Iterable<IEObjectDescription> getAllElements() {
//		Iterable<IEObjectDescription> allElements = super.getAllElements();
//		System.out.println("getAllElements => " + Iterables.size(allElements));
//		return allElements;
//	}

	@Override
	protected Iterable<IEObjectDescription> getAllLocalElements() {
		List<IEObjectDescription> results = new ArrayList<IEObjectDescription>();
		if (SUPPRESS_WORK_THREAD && !doneFullScan) {
			doneFullScan = true;
			doFullScan();
		}
		if (!doneFullScan) {
			doneFullScan = true;
			Thread thread = new Thread("OCLstdlib ClassPath Scan") {
				@Override
				public void run() {
					doFullScan();
				}
			};
			thread.start();
			String name = "Try again once worker thread class path scan has completed.";
			JavaClassCS csJavaClass = BaseCSFactory.eINSTANCE.createJavaClassCS();
			csJavaClass.setName(name);
			results.add(EObjectDescription.create(name, csJavaClass));
		}
		else {
			List<@NonNull String> sortedNames = new ArrayList<@NonNull String>(name2class.keySet());
			Collections.sort(sortedNames);
			for (@NonNull String className : sortedNames) {
				results.add(getEObjectDescription(className));
			}
		}
		return results;
	}

	protected IEObjectDescription getEObjectDescription(@NonNull String name) {
		JavaClassCS csJavaClass = getJavaClassCS(name);
		return EObjectDescription.create(name, csJavaClass);
	}

	@Override
	public Iterable<IEObjectDescription> getElements(QualifiedName name) {
		IEObjectDescription result = getSingleElement(name);
		if (result != null)
			return singleton(result);
		return emptySet();
	}

	public @NonNull JavaClassCS getJavaClassCS(@NonNull String name) {
		synchronized (name2class) {
			JavaClassCS csJavaClass = name2class.get(name);
			if (csJavaClass == null) {
				csJavaClass = BaseCSFactory.eINSTANCE.createJavaClassCS();
				csJavaClass.setName(name);
				name2class.put(name, csJavaClass);
				javaResource.getContents().add(csJavaClass);
			}
			return csJavaClass;
		}
	}

	@Override
	protected Iterable<IEObjectDescription> getLocalElementsByEObject(EObject object, URI uri) {
		QualifiedName qualifiedName = QualifiedName.create(((JavaClassCS)object).getName());
		return Collections.singletonList(EObjectDescription.create(qualifiedName, object));
	}

	@Override
	protected Iterable<IEObjectDescription> getLocalElementsByName(final QualifiedName name) {
		Iterable<IEObjectDescription> localElements = getAllLocalElements();
		Iterable<IEObjectDescription> result = Iterables.filter(localElements, new Predicate<IEObjectDescription>() {
			@Override
			public boolean apply(IEObjectDescription input) {
				if (isIgnoreCase()) {
					QualifiedName lowerCase = name.toLowerCase();
					QualifiedName inputLowerCase = input.getName().toLowerCase();
					return lowerCase.equals(inputLowerCase);
				} else {
					return name.equals(input.getName());
				}
			}
		});
		return result;
	}

	@Override
	public IEObjectDescription getSingleElement(QualifiedName qualifiedName) {
		String name = qualifiedName.toString();
		if (name == null) {
			return null;
		}
		JavaClassCS csJavaClass = name2class.get(name);
		if (csJavaClass == null) {
			Class<?> loadClass = null;
			IType type = null;
			for (@NonNull ClassLoader classLoader : classLoaders) {
				try {
					loadClass = classLoader.loadClass(name);
					if (loadClass != null) {
						break;
					}
				} catch (ClassNotFoundException e) {}
			}
			if (projects != null) {
				for (IProject project : projects) {
					IJavaProject javaProject = JavaCore.create(project);
					try {
						type = javaProject.findType(name);
						if (type != null) {
							break;
						}
					} catch (JavaModelException e) {}
				}
			}
			if ((loadClass == null) && (type == null)) {
				return null;
			}
		}
		return getEObjectDescription(name);
	}

	@Override
	@Deprecated /* @deprecated not used */
	public void installContents(@NonNull CSResource csResource) {}

	protected @Nullable String resolveClassName(@NonNull String name) {
		if (!name.endsWith(".class")) {
			return null;
		}
		String className = name.substring(0, name.length()-6);
		int dollarIndex = className.lastIndexOf('$');
		if ((dollarIndex <= 0) || (className.length() <= dollarIndex+1) || !Character.isDigit(className.charAt(dollarIndex+1))) {
			return className.replace("/", ".");
		}
		else {
			return null;
		}
	}

	private void scanJavaElements(IJavaElement[] elements, Set<String> classNames) {
		for (IJavaElement element : elements) {
			//			System.out.println(getClass().getSimpleName() + " : " + element);
			if (element instanceof IType) {
				IType iType = (IType)element;
				classNames.add(iType.getFullyQualifiedName());
				try {
					if (iType.hasChildren()) {
						scanJavaElements(iType.getChildren(), classNames);
					}
				} catch (JavaModelException e) {}
			}
			else if ((element instanceof IParent) && !(element instanceof IMember)) {
				try {
					IParent iParent = (IParent)element;
					if (iParent.hasChildren()) {
						scanJavaElements(iParent.getChildren(), classNames);
					}
				} catch (JavaModelException e) {}
			}
		}
	}
}
