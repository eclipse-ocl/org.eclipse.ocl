// XXX
package org.eclipse.ocl.xtext.base.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

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
		return new OCLCSResourceImpl(uri);
	}
}
