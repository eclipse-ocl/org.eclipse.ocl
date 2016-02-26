package org.eclipse.ocl.xtext.essentialocl.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2as.BaseCS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASTransformer;
import org.eclipse.ocl.xtext.essentialocl.cs2as._EssentialOCLCS2AS_qvtp_qvtcas.EssentialOCLCS2AS_qvtp_qvtcas;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

public class EssentialOCLCS2ASConversion extends BaseCS2ASConversion {

	public EssentialOCLCS2ASConversion(@NonNull CS2AS converter,
			@NonNull IDiagnosticConsumer diagnosticsConsumer) {
		super(converter, diagnosticsConsumer);
	}
	
	@Override
	protected @NonNull Class<? extends CS2ASTransformer> getCS2ASTxClass() {
		return EssentialOCLCS2AS_qvtp_qvtcas.class;
	}
}
