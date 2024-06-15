// XXX
package org.eclipse.ocl.xtext.base.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryContribution;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.resource.ASResource;

/** XXX
 * The <b>Resource Factory</b> for the pivot and extended pivot abstract syntax.
 */
public class OCLCSResourceFactory extends ResourceFactoryImpl
{
/*	private static final @NonNull ContentHandler PIVOT_CONTENT_HANDLER = new RootXMLContentHandlerImpl(
		ASResource.CONTENT_TYPE, new String[]{ASResource.FILE_EXTENSION},
		RootXMLContentHandlerImpl.XMI_KIND, PivotPackage.eNS_URI, null);

	static {
		installContentHandler(ContentHandler.Registry.NORMAL_PRIORITY, PIVOT_CONTENT_HANDLER);
	} */

	/**
	 * Creates an instance of the resource factory.
	 */
	public OCLCSResourceFactory() {}

	@Override
	public Resource createResource(URI uri) {
		assert uri != null;
		String contentType = ASResource.COMPLETE_OCL_CONTENT_TYPE;			// XXX
		ASResourceFactoryContribution asResourceFactoryContribution = ASResourceFactoryRegistry.INSTANCE.get(contentType);
		assert asResourceFactoryContribution != null;
		ASResourceFactory asResourceFactory = asResourceFactoryContribution.getASResourceFactory();
		return new OCLCSResourceLoadImpl(uri, asResourceFactory);
	}
}
