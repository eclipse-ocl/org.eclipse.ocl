/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;

public abstract class AbstractPackageIdImpl extends AbstractElementId implements PackageId
{
	protected final @NonNull Integer hashCode;

	/**
	 * Map from a nested class name to the corresponding ClassId. 
	 */
	private @Nullable WeakHashMapOfWeakReference<String, GeneralizedClassIdImpl> classes = null;

	/**
	 * Map from a nested datatype name to the corresponding DataTypeId. 
	 */
	private @Nullable WeakHashMapOfWeakReference<String, GeneralizedDataTypeIdImpl> dataTypes = null;

	/**
	 * Map from a nested type name to the corresponding NestedTypeId. 
	 */
	private @Nullable WeakHashMapOfWeakReference<String, EnumerationIdImpl> enumerations = null;

	/**
	 * Map from a nested package name to the corresponding Nested PackageId. 
	 */
	private @Nullable WeakHashMapOfWeakReference<String, NestedPackageId> packages = null;
	
	
	protected AbstractPackageIdImpl(@NonNull Integer hashCode) {
		this.hashCode = hashCode;
	}

	@Override
	public @NonNull ClassId getClassId(@NonNull String name, final int templateParameters) {
    	WeakHashMapOfWeakReference<String, GeneralizedClassIdImpl> classes2 = classes;
		if (classes2 == null) {
    		synchronized (this) {
    			classes2 = classes;
    	    	if (classes2 == null) {
    	    		classes = classes2 = new WeakHashMapOfWeakReference<String, GeneralizedClassIdImpl>()
    				{
						@Override
						protected @NonNull GeneralizedClassIdImpl newId(@NonNull String name) {
							return new GeneralizedClassIdImpl(AbstractPackageIdImpl.this, templateParameters, name);
						}
					};
    	    	}
    		}
    	}
		return classes2.getId(name);
    }

	@Override
	public @NonNull DataTypeId getDataTypeId(@NonNull String name, final int templateParameters) {
    	WeakHashMapOfWeakReference<String, GeneralizedDataTypeIdImpl> dataTypes2 = dataTypes;
		if (dataTypes2 == null) {
    		synchronized (this) {
    			dataTypes2 = dataTypes;
    	    	if (dataTypes2 == null) {
    	    		dataTypes = dataTypes2 = new WeakHashMapOfWeakReference<String, GeneralizedDataTypeIdImpl>()
    				{
						@Override
						protected @NonNull GeneralizedDataTypeIdImpl newId(@NonNull String name) {
							return new GeneralizedDataTypeIdImpl(AbstractPackageIdImpl.this, templateParameters, name);
						}
					};
    	    	}
    		}
    	}
		return dataTypes2.getId(name);
    }

	@Override
	public @NonNull EnumerationId getEnumerationId(@NonNull String name) {
    	WeakHashMapOfWeakReference<String, EnumerationIdImpl> enumerations2 = enumerations;
		if (enumerations2 == null) {
    		synchronized (this) {
    			enumerations2 = enumerations;
    	    	if (enumerations2 == null) {
    	    		enumerations = enumerations2 = new WeakHashMapOfWeakReference<String, EnumerationIdImpl>()
    				{
						@Override
						protected @NonNull EnumerationIdImpl newId(@NonNull String name) {
							return new EnumerationIdImpl(AbstractPackageIdImpl.this, name);
						}
					};
    	    	}
    		}
    	}
		return enumerations2.getId(name);
    }

	public @NonNull String getMetaTypeName() {
		return TypeId.CLASS_NAME;
	}

 	@Override
	public @NonNull NestedPackageId getNestedPackageId(@NonNull String name) {
    	WeakHashMapOfWeakReference<String, NestedPackageId> packages2 = packages;
		if (packages2 == null) {
    		synchronized (this) {
    			packages2 = packages;
    	    	if (packages2 == null) {
    	    		packages = packages2 = new WeakHashMapOfWeakReference<String, NestedPackageId>()
    				{
						@Override
						protected @NonNull NestedPackageId newId(@NonNull String name) {
							return new NestedPackageIdImpl(AbstractPackageIdImpl.this, name);
						}
					};
    	    	}
    		}
    	}
		return packages2.getId(name);
    }

	@Override
	public final int hashCode() {
		return hashCode;
	}
}