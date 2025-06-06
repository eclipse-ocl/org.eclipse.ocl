/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;

import com.google.common.collect.Lists;

public class EcoreExecutorPackage extends PackageImpl
{
	/**
	 * @since 7.0
	 */
	public static final class StringNameable implements Nameable
	{
		private final String typeName;

		public StringNameable(String typeName) {
			this.typeName = typeName;
		}

		@Override
		public String getName() {
			return typeName;
		}
	}

//	protected final EPackage ePackage;
	private PartialStandardLibraryImpl standardLibrary = null;
	private org.eclipse.ocl.pivot.@NonNull Class[] types = null;
	private @Nullable List<org.eclipse.ocl.pivot.Package> packages = null;

	@Override
	public final EPackage getEPackage() {
		return (EPackage)getESObject();
	}

	/**
	 * @since 7.0
	 */
	public @NonNull IdResolver getIdResolver() {
		@NonNull List<EObject> emptyList = Collections.<EObject>emptyList();
		assert standardLibrary != null;
		return new EcoreIdResolver(emptyList, standardLibrary);
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Package> getOwnedPackages() {
		List<org.eclipse.ocl.pivot.@NonNull Package> packages2 = packages;
		if (packages2 == null) {
			synchronized (this) {
				packages2 = packages;
				if (packages2 == null) {
					packages2 = packages = new ArrayList<>();
					for (EPackage eSubPackage : getEPackage().getESubpackages()) {
						assert eSubPackage != null;
						org.eclipse.ocl.pivot.Package subPackage = standardLibrary.getPackage(eSubPackage);
						if (subPackage != null) {
							packages2.add(subPackage);
						}
					}
				}
			}
		}
		return packages2;
	}

	@Override
	public org.eclipse.ocl.pivot.Package getOwningPackage() {
		EPackage eSuperPackage = getEPackage().getESuperPackage();
		if (eSuperPackage == null) {
			return null;
		}
		return standardLibrary.getPackage(eSuperPackage);
	}

	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getOwnedClasses() {
		if (types != null) {
			return Lists.<org.eclipse.ocl.pivot.Class>newArrayList(types);
		}
		else {
			return Collections.emptyList();
		}
	}

	/**
	 * @since 7.0
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getOwnedClass(String typeName) {
		int index = Arrays.binarySearch(types, new StringNameable(typeName), NameUtil.NameableComparator.INSTANCE);
		if (index >= 0) {
			return types[index];
		}
		//	Should be sorted, but do linear search just in case
		for (org.eclipse.ocl.pivot.@NonNull Class type : types) {
			if (type.getName().equals(typeName)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull StandardLibrary getStandardLibrary() {
		assert standardLibrary != null;
		return standardLibrary;
	}

	/**
	 * @since 7.0
	 */
	public void init(@Nullable PartialStandardLibraryImpl standardLibrary, org.eclipse.ocl.pivot.@NonNull Class @NonNull [] types) {
		assert (this.standardLibrary == null) || (this.standardLibrary == standardLibrary);
		assert this.types == null;
		this.standardLibrary = standardLibrary;
		this.types = types;
		if (standardLibrary != null) {
			standardLibrary.addPackage(this, null);
		}
	}

	/*	public void init2(EcoreExecutorPackage execPackage, ExecutorType[] types) {
		ExecutorStandardLibrary standardLibrary = execPackage.standardLibrary;
		assert this.standardLibrary == null;
		assert this.types == null;
		this.standardLibrary = standardLibrary;
		this.types = types;
		if (standardLibrary != null) {
			standardLibrary.addPackage(this, execPackage);
		}
	} */

	//	public ExecutorType lookupType(int classIndex) {
	//		return types[classIndex];
	//	}
}