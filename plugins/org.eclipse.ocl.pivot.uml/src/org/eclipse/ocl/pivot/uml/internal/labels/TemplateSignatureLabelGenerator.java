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
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;

/**
 * @since 7.0
 */
public final class TemplateSignatureLabelGenerator extends AbstractLabelGenerator<TemplateSignature>
{
	public static void initialize(Registry registry) {
		registry.install(TemplateSignature.class, new TemplateSignatureLabelGenerator());
	}

	public TemplateSignatureLabelGenerator() {
		super(TemplateSignature.class);
	}

	@Override
	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull TemplateSignature object) {
		boolean isFirst = true;
		labelBuilder.appendString("<");
		for (TemplateParameter templateParameter : object.getOwnedParameters()) {
			if (!isFirst) {
				labelBuilder.appendString(",");
			}
			else {
				isFirst = false;
			}
			labelBuilder.appendObject(templateParameter);
		}
		labelBuilder.appendString(">");
	}
}