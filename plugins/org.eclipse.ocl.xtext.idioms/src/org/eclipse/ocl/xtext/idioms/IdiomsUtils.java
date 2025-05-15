/*******************************************************************************
 * Copyright (c) 2021, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.idioms;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.serializer.SerializationUtils;

public class IdiomsUtils extends SerializationUtils
{
	public static @NonNull IdiomsModel getIdiomsModel(@NonNull IdiomsImport idiomsImport) {
		return ClassUtil.requireNonNull(idiomsImport.getIdiomsModel());
	}

	public static @NonNull  Locator getLocator(@NonNull SubIdiom subIdiom) {
		Locator locator = subIdiom.getOwnedLocator();
		return getLocator(ClassUtil.requireNonNull(locator));
	}

	public static @NonNull Locator getLocator(@NonNull Locator locator) {
		if (locator instanceof ReferredLocator) {
			return ClassUtil.requireNonNull(((ReferredLocator)locator).getLocatorDeclaration().getOwnedLocator());
		}
		else {
			return locator;
		}
	}

	public static @NonNull LocatorDeclaration getLocatorDeclaration(@NonNull ReferredLocator referredLocator) {
		return ClassUtil.requireNonNull(referredLocator.getLocatorDeclaration());
	}

	public static @NonNull Iterable<@NonNull Idiom> getOwnedIdioms(@NonNull IdiomsModel idiomsModel) {
		return ClassUtil.nullFree(idiomsModel.getOwnedIdioms());
	}

	public static @NonNull Iterable<@NonNull EPackageDeclaration> getOwnedImports(@NonNull IdiomsModel idiomsModel) {
		return ClassUtil.nullFree(idiomsModel.getOwnedImportDeclarations());
	}

	public static @NonNull Locator getOwnedLocator(@NonNull LocatorDeclaration locatorDeclaration) {
		return ClassUtil.requireNonNull(locatorDeclaration.getOwnedLocator());
	}

	public static @NonNull  Locator getOwnedLocator(@NonNull SubIdiom subIdiom) {
		return ClassUtil.requireNonNull(subIdiom.getOwnedLocator());
	}

	public static @NonNull Segment getOwnedSegment(@NonNull SegmentDeclaration segmentDeclaration) {
		return ClassUtil.requireNonNull(segmentDeclaration.getOwnedSegment());
	}

	public static @Nullable List<@NonNull Segment> getOwnedSegments(@NonNull SubIdiom subIdiom) {
		return ClassUtil.nullFree(subIdiom.getOwnedSegments());
	}

	public static @NonNull List<@NonNull SubIdiom> getOwnedSubIdioms(@NonNull Idiom idiom) {
		return ClassUtil.nullFree(idiom.getOwnedSubIdioms());
	}

	public static @NonNull List<@NonNull IdiomsImport> getOwnedWiths(@NonNull IdiomsModel idiomsModel) {
		return ClassUtil.nullFree(idiomsModel.getOwnedWiths());
	}

	public static @NonNull Idiom getOwningIdiom(@NonNull SubIdiom subIdiom) {
		return ClassUtil.requireNonNull(subIdiom.getOwningIdiom());
	}

	public static @NonNull SegmentDeclaration getSegmentDeclaration(@NonNull ReferredSegment referredSegment) {
		return ClassUtil.requireNonNull(referredSegment.getSegmentDeclaration());
	}
}
