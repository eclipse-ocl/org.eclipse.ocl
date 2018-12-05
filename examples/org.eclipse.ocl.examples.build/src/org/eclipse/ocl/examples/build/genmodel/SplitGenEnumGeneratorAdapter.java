/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.genmodel;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenEnumGeneratorAdapter;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;

//
//	Overridden to allow static templates to be invoked standalone.
//
public class SplitGenEnumGeneratorAdapter extends GenEnumGeneratorAdapter
{
	public SplitGenEnumGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	protected void generateEnumClass(GenEnum genEnum, Monitor monitor) {
	    GenModel genModel = genEnum.getGenModel();
	    String modelDirectory = genModel.getModelDirectory();
		try {
			EClassifier eClassifier = genEnum.getEcoreClassifier();
			if (eClassifier instanceof EEnum) {
			    String interfaceModelDirectory = SplitGenModelGeneratorAdapterFactory.getInterfaceModelDirectory(genModel);
				if (interfaceModelDirectory != null) {
					genModel.setModelDirectory(interfaceModelDirectory);
				}
			}
			super.generateEnumClass(genEnum, monitor);
		}
		finally {
			genModel.setModelDirectory(modelDirectory);
		}
	}
}
