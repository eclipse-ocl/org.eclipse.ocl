/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.utilities;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.utilities.ParserException;

public interface External2AS
{
	void dispose();
	@NonNull Model getASModel() throws ParserException;
	@Nullable <T extends Element> T getCreated(@NonNull Class<T> requiredClass, @NonNull EObject eObject);
	@Nullable Map<@NonNull EObject, @NonNull Element> getCreatedMap();
	@Nullable Resource getResource();
	@NonNull URI getURI();
}