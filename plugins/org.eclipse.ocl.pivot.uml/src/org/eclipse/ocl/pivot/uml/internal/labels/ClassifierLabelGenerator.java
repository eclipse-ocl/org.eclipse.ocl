/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.uml.internal.labels;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.labels.AbstractLabelGenerator;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TemplateSignature;

/**
 * @since 7.0
 */
public final class ClassifierLabelGenerator extends AbstractLabelGenerator<Classifier>
{
	public static void initialize(Registry registry) {
		registry.install(Classifier.class, new ClassifierLabelGenerator());
	}

	public ClassifierLabelGenerator() {
		super(Classifier.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull Classifier object) {
		String name = object.getName();
		if (name != null)
			labelBuilder.appendString(name);
		else {
			labelBuilder.appendString("<null-named-");
			labelBuilder.appendString(object.getClass().getSimpleName());
			labelBuilder.appendString(">");
		}
		TemplateSignature templateSignature = object.getOwnedTemplateSignature();
		if (templateSignature != null) {
			labelBuilder.appendObject(templateSignature);
		}
	}
}