/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.idioms;

import org.eclipse.ocl.examples.xtext.idioms.serializer.IdiomsCrossReferenceSerializer;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class IdiomsRuntimeModule extends AbstractIdiomsRuntimeModule
{
	@Override
	public Class<? extends ILinkingService> bindILinkingService() {
		return IdiomsLinkingService.class;
	}

	@SuppressWarnings("restriction")
	public Class<? extends ICrossReferenceSerializer> bindICrossReferenceSerializer() {
		return IdiomsCrossReferenceSerializer.class;
	}
}
