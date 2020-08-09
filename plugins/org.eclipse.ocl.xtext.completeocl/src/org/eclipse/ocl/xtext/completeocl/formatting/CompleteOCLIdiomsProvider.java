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
package org.eclipse.ocl.xtext.completeocl.formatting;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractIdiomsProvider;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;

public class CompleteOCLIdiomsProvider extends AbstractIdiomsProvider {
	
//	@Inject extension CompleteOCLGrammarAccess
//	"FOR type : types.take(2)"

//		"type.generateFormatMethod(type2ref.get(type), inheritedTypes.containsKey(type))"
//	"ENDFOR"	
	
	// TODO: implement for "types.drop(2).map[name].join(", ")"
}
