/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.internal.OrphanageImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *  PivotLoadImpl intercepts the load of orphans to delay their installation until
 *  they ae sufficiently characterised tp be installed.
 */
public final class PivotLoadImpl extends XMILoadImpl
{
	/**
	 * @since 1.18
	 */
	private static class PivotXMILoadHelperImpl extends XMIHelperImpl
	{
		private @Nullable Orphanage orphanage = null;
	//	private @Nullable List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses = null;

		public PivotXMILoadHelperImpl(@NonNull ASResource asResource) {
			super(asResource);
		}

		public void installOrphans() {
			Orphanage orphanage2 = orphanage;
		//	List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses2 = orphanClasses;
			if (orphanage2 != null) { //&& (orphanClasses2 != null)) {
				((OrphanageImpl)orphanage2).installProtoClasses();
			}
		}

		@Override
		public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
			if (object instanceof Orphanage) {
				if (orphanage == null) {
					orphanage = (Orphanage)object;
				}
				else {
					assert orphanage == object;
				}
			/*	if ((feature == PivotPackage.Literals.PACKAGE__OWNED_CLASSES) && (value instanceof Type)) {
					List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses2 = orphanClasses;
					if (orphanClasses2 == null) {
						orphanClasses = orphanClasses2 = new ArrayList<>();
					}
					orphanClasses2.add((org.eclipse.ocl.pivot.Class)value);
					return;
				} */
			}
			super.setValue(object, feature, value, position);
		}
	}

	public PivotLoadImpl(@NonNull ASResource asResource) {
		super(new PivotXMILoadHelperImpl(asResource));
	}

	@Override
	public void load(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException {
		PivotXMILoadHelperImpl helper2 = (PivotXMILoadHelperImpl)helper;
		super.load(resource, inputStream, options);
		helper2.installOrphans();
	}

	@Override
	public void load(XMLResource resource, InputSource inputSource, Map<?, ?> options) throws IOException {
		PivotXMILoadHelperImpl helper2 = (PivotXMILoadHelperImpl)helper;
		super.load(resource, inputSource, options);
		helper2.installOrphans();
	}

	@Override
	public void load(XMLResource resource, Node node, Map<?, ?> options) throws IOException {
		PivotXMILoadHelperImpl helper2 = (PivotXMILoadHelperImpl)helper;
		super.load(resource, node, options);
		helper2.installOrphans();
	}
}