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
package org.eclipse.ocl.xtext.base.formatting;

import org.eclipse.ocl.xtext.base.cs2text.AbstractIdiomsProvider;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel;

public class BaseIdiomsProvider extends AbstractIdiomsProvider
{
	private static Iterable<Idiom> idioms = null;

	@Override
	public Iterable<Idiom> getIdioms() {
		if (idioms == null) {
			IdiomModel idiomModel = getIdiomModel(getClass(), "/org/eclipse/ocl/xtext/base/Base.idioms");
			idioms = getIdioms(idiomModel);
		}
		return idioms;
	}
}