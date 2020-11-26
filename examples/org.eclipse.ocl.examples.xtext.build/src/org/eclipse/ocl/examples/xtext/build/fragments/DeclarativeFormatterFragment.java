/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.build.fragments;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.xtext.generator.formatting.Formatter2Fragment2;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

/**
 * The DeclarativeFormatterFragment collaborates with the DeclarativeSerializerFragment to replace the
 * backtracking serializer and the Xtend-dependent formatting specifiation approach of the 'new infrastructure'
 * by a statically determined serializer and declarative idiom-based formatter.
 */
public abstract class DeclarativeFormatterFragment extends Formatter2Fragment2
{
	@Override
	public void generate() {
		// The binding is in DeclarativeSerializerFragment sice the DeclarativeFormatter re-uses the
		//	DeclarativeSerializer's XXXSerializationMetaData.
	}

	@Override
	protected void doGenerateStubFile() {
		throw new UnsupportedOperationException();
	}

//	@Override
//	protected XtendFileAccess doGetXtendStubFile() {
//		throw new UnsupportedOperationException();
//	}

	@Override
	protected TypeReference getFormatter2Stub(final Grammar grammar) {
		throw new UnsupportedOperationException();
	}
}
