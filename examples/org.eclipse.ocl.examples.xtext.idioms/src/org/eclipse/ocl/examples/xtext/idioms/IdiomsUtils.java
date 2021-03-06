/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.idioms;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

public class IdiomsUtils extends SerializationUtils
{
	public static @NonNull IdiomsModel getIdiomsModel(@NonNull IdiomsImport idiomsImport) {
		return nonNullState(idiomsImport.getIdiomsModel());
	}

	public static @NonNull  Locator getLocator(@NonNull SubIdiom subIdiom) {
		Locator locator = subIdiom.getOwnedLocator();
		return getLocator(nonNullState(locator));
	}

	public static @NonNull Locator getLocator(@NonNull Locator locator) {
		if (locator instanceof ReferredLocator) {
			return nonNullState(((ReferredLocator)locator).getLocatorDeclaration().getOwnedLocator());
		}
		else {
			return locator;
		}
	}

	public static @NonNull LocatorDeclaration getLocatorDeclaration(@NonNull ReferredLocator referredLocator) {
		return nonNullState(referredLocator.getLocatorDeclaration());
	}

	public static @NonNull Iterable<@NonNull Idiom> getOwnedIdioms(@NonNull IdiomsModel idiomsModel) {
		return nullFree(idiomsModel.getOwnedIdioms());
	}

	public static @NonNull Iterable<@NonNull EPackageImport> getOwnedImports(@NonNull IdiomsModel idiomsModel) {
		return nullFree(idiomsModel.getOwnedImports());
	}

	public static @NonNull Locator getOwnedLocator(@NonNull LocatorDeclaration locatorDeclaration) {
		return nonNullState(locatorDeclaration.getOwnedLocator());
	}

	public static @NonNull  Locator getOwnedLocator(@NonNull SubIdiom subIdiom) {
		return nonNullState(subIdiom.getOwnedLocator());
	}

	public static @NonNull Segment getOwnedSegment(@NonNull SegmentDeclaration segmentDeclaration) {
		return nonNullState(segmentDeclaration.getOwnedSegment());
	}

	public static @NonNull List<@NonNull SubIdiom> getOwnedSubIdioms(@NonNull Idiom idiom) {
		return nullFree(idiom.getOwnedSubIdioms());
	}

	public static @NonNull List<@NonNull IdiomsImport> getOwnedWiths(@NonNull IdiomsModel idiomsModel) {
		return nullFree(idiomsModel.getOwnedWiths());
	}

	public static @NonNull Idiom getOwningIdiom(@NonNull SubIdiom subIdiom) {
		return nonNullState(subIdiom.getOwningIdiom());
	}

	public static @NonNull SegmentDeclaration getSegmentDeclaration(@NonNull ReferredSegment referredSegment) {
		return nonNullState(referredSegment.getSegmentDeclaration());
	}
}
