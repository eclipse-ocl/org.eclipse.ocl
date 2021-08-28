/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class EcoreASResourceImpl extends ASResourceImpl
{
	public EcoreASResourceImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri, asResourceFactory);
	//	setSaveable(false);		-- FIXME change to require explicit srtSaveable(true) policy
		System.out.println("ctor " + NameUtil.debugSimpleName(this) + " : " + uri);
	}

	@Override
	public String getURIFragment(EObject eObject) {
		String uriFragment = super.getURIFragment(eObject);
  	  if ("CPVxu".equals(uriFragment)) {
		  getClass();
	  }
		return uriFragment;
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
		@NonNull URI ecoreURI = uri.trimFileExtension();
		Ecore2AS.loadFromEcore(this, ecoreURI);
		super.load(options);
	}
}