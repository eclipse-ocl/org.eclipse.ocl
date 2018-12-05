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
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class EnumerationIdImpl extends AbstractTypeId implements EnumerationId
{
	protected final @NonNull PackageId parent;
	protected final @NonNull String name;
	protected final int hashCode;

	/**
	 * Map from a nested type name to the corresponding EnumerationLiteralId. 
	 */
	private @Nullable WeakHashMapOfWeakReference<String, EnumerationLiteralId> memberEnumerationLiterals = null;

	public EnumerationIdImpl(@NonNull PackageId parent, @NonNull String name) {
		this.hashCode = 97 * parent.hashCode() + name.hashCode();
		this.parent = parent;
		this.name = name;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitEnumerationId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		if (parent instanceof NsURIPackageId) {
			return name;
		}
		else {
			return parent + "::" + name;
		}
	}

	@Override
	public @NonNull EnumerationLiteralId getEnumerationLiteralId(@NonNull String name) {
    	WeakHashMapOfWeakReference<String, EnumerationLiteralId> memberEnumerationLiterals2 = memberEnumerationLiterals;
		if (memberEnumerationLiterals2 == null) {
    		synchronized (this) {
    			memberEnumerationLiterals2 = memberEnumerationLiterals;
    	    	if (memberEnumerationLiterals2 == null) {
    	    		memberEnumerationLiterals = memberEnumerationLiterals2 = new WeakHashMapOfWeakReference<String, EnumerationLiteralId>()
    				{
						@Override
						protected @NonNull EnumerationLiteralId newId(@NonNull String name) {
							return new EnumerationLiteralIdImpl(EnumerationIdImpl.this, name);
						}
					};
	    	   }
    		}
    	}
		return memberEnumerationLiterals2.getId(name);
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return TypeId.ENUMERATION_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}
	
	@Override
	public @NonNull PackageId getParent() {
		return parent;
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}
	
	@Override
	public String toString() {
		return parent + "::" + name;
	}

}