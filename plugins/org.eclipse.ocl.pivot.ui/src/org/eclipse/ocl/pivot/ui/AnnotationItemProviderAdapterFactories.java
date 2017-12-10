/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ui;

import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory;
import org.eclipse.ocl.pivot.internal.validation.Ecore_OCL_AnnotationValidator;
import org.eclipse.ocl.pivot.internal.validation.OCL_Collection_AnnotationValidator;
import org.eclipse.ocl.pivot.internal.validation.OCL_Import_AnnotationValidator;

/**
 * FIXME These classes is a workaround that may be deleted once Bug 528384 provides relective EAnnotationItemProviderAdapterFactory support.
 *
 * @deprecated temporary support pending a Bug 528384 fix.
 */
@Deprecated
public class AnnotationItemProviderAdapterFactories
{
	public static class Ecore_OCL_Blank extends EAnnotationItemProviderAdapterFactory
	{
		public Ecore_OCL_Blank()
		{
			super(EcoreEditPlugin.INSTANCE, Ecore_OCL_AnnotationValidator.Blank.INSTANCE.getAssistant());
		}
	}

	public static class Ecore_OCL_Debug extends EAnnotationItemProviderAdapterFactory
	{
		public Ecore_OCL_Debug()
		{
			super(EcoreEditPlugin.INSTANCE, Ecore_OCL_AnnotationValidator.Debug.INSTANCE.getAssistant());
		}
	}

	public static class Ecore_OCL_Pivot extends EAnnotationItemProviderAdapterFactory
	{
		public Ecore_OCL_Pivot()
		{
			super(EcoreEditPlugin.INSTANCE, Ecore_OCL_AnnotationValidator.Pivot.INSTANCE.getAssistant());
		}
	}

	public static class OCL_Collection extends EAnnotationItemProviderAdapterFactory
	{
		public OCL_Collection()
		{
			super(EcoreEditPlugin.INSTANCE, OCL_Collection_AnnotationValidator.INSTANCE.getAssistant());
		}
	}

	public static class OCL_Import extends EAnnotationItemProviderAdapterFactory
	{
		public OCL_Import()
		{
			super(EcoreEditPlugin.INSTANCE, OCL_Import_AnnotationValidator.INSTANCE.getAssistant());
		}
	}
}
