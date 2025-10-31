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
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.ParameterableElement;

/**
 * @since 7.0
 */
public final class ClassifierTemplateParameterLabelGenerator extends AbstractLabelGenerator<ClassifierTemplateParameter>
{
	public static void initialize(Registry registry) {
		registry.install(ClassifierTemplateParameter.class, new ClassifierTemplateParameterLabelGenerator());
	}

	public ClassifierTemplateParameterLabelGenerator() {
		super(ClassifierTemplateParameter.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull ClassifierTemplateParameter object) {
		ParameterableElement parameterableElement = object.getParameteredElement();
		labelBuilder.appendObject(parameterableElement);
	}
}