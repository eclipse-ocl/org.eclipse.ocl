/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations, University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Adolfo Sanchez-Barbudo Herrera (UoY)  - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2as._BaseCS2AS_qvtp_qvtcas.BaseCS2AS_qvtp_qvtcas;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASTransformer;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

public class BaseCS2ASConversion extends NewCS2ASConversion {

	public BaseCS2ASConversion(@NonNull CS2AS converter,
			@NonNull IDiagnosticConsumer diagnosticsConsumer) {
		super(converter, diagnosticsConsumer);
	}

	@Override
	protected @NonNull Class<? extends CS2ASTransformer> getCS2ASTxClass() {
		return BaseCS2AS_qvtp_qvtcas.class;
	}
}
