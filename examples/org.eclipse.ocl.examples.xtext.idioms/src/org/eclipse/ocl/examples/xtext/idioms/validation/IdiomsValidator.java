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
package org.eclipse.ocl.examples.xtext.idioms.validation;

import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

/**
 * This class contains custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class IdiomsValidator extends AbstractIdiomsValidator {

	void checkUniqueLocatorDeclarationName(LocatorDeclaration locatorDeclaration) {
		String name = locatorDeclaration.getName();
		for (LocatorDeclaration aLocatorDeclaration : locatorDeclaration.getOwningIdiomsModel().getOwnedLocatorDeclarations()) {
			if ((aLocatorDeclaration != locatorDeclaration) && SerializationUtils.safeEquals(aLocatorDeclaration.getName(), name)) {
		 		warning("Duplicate locator name", locatorDeclaration, IdiomsPackage.Literals.LOCATOR_DECLARATION__NAME);
				break;
			}
		}
	 }

	void checkUniqueSegmentDeclarationName(SegmentDeclaration segmentDeclaration) {
		String name = segmentDeclaration.getName();
		for (SegmentDeclaration aSegmentDeclaration : segmentDeclaration.getOwningIdiomsModel().getOwnedSegmentDeclarations()) {
			if ((aSegmentDeclaration != segmentDeclaration) && SerializationUtils.safeEquals(aSegmentDeclaration.getName(), name)) {
		 		warning("Duplicate locator name", segmentDeclaration, IdiomsPackage.Literals.SEGMENT_DECLARATION__NAME);
				break;
			}
		}
	 }
}
