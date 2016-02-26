package org.eclipse.ocl.xtext.base.cs2as;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASDiagnostic;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASException;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASTransformationExecutor;
import org.eclipse.ocl.xtext.base.cs2as.tx.CS2ASTransformer;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

public abstract class NewCS2ASConversion extends CS2ASConversion {

	
	public NewCS2ASConversion(@NonNull CS2AS converter, @NonNull IDiagnosticConsumer diagnosticsConsumer) {
		super(converter, diagnosticsConsumer);
	}
	
	@Override
	public boolean update(@NonNull BaseCSResource csResource) {
		
		ResourceSet rSet = csResource.getResourceSet();
		CS2ASTransformer tx = null;		
		try {
			CS2ASTransformationExecutor txExecutor= createTransformationExecutor();
			tx = txExecutor.getTransformer();
			
			tx.addRootObjects("leftCS", ClassUtil.nonNullState(csResource.getContents()));
			if (tx.run()) {
				URI asModelURI = csResource.getURI().appendFileExtension("xmi");
				Resource outputResource = rSet.getResource(asModelURI, false);
				if (outputResource == null) {
					outputResource = rSet.createResource(asModelURI);
				}
				outputResource.getContents().clear();
				outputResource.getContents().addAll(tx.getRootEObjects("rightAS"));
				outputResource.save(null); // FIXME
			}
			return true;
		}
		catch (CS2ASException exception) {
			addDiagnostic((ElementCS) exception.getCSObject(), exception.getMessage());
			return false;
		} catch(Exception exception) {			
			csResource.getErrors().add(new ExceptionDiagnostic(exception));
			return false;
		} finally {
			if (tx != null) {
				List<CS2ASDiagnostic> txErrors = tx.getErrors();
				for (CS2ASDiagnostic diagnostic : txErrors) {
					addDiagnostic((ElementCS)diagnostic.getCSObject(), diagnostic.getMessage());
				}
				txErrors.clear();
			}
		}
	}

	protected abstract @NonNull Class<? extends CS2ASTransformer> getCS2ASTxClass();
	
	/**
	 * Just getCS2ASTxClass() should be overridden
	 */
	protected final CS2ASTransformationExecutor createTransformationExecutor() throws ReflectiveOperationException {
		return new CS2ASTransformationExecutor(environmentFactory, getCS2ASTxClass());
	}
}
