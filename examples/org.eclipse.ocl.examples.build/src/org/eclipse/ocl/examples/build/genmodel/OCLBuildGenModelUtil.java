/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.genmodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGenModelGeneratorAdapter;
import org.eclipse.ocl.pivot.util.DerivedConstants;


public class OCLBuildGenModelUtil
{

	public static final @NonNull String OCL_GENMODEL_COPY_AND_PASTE_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/CopyAndPaste";
	public static final @NonNull String USE_NULL_ANNOTATIONS = "Use Null Annotations";

	public static final @NonNull String OCL_GENMODEL_TO_STRING_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/ToString";

	public static final @NonNull String OCL_GENMODEL_VISITOR_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/Visitor";
	public static final @NonNull String ROOT_VISITOR_CLASS = "Root Visitor Class";
	public static final @NonNull String DERIVED_VISITOR_CLASS = "Derived Visitor Class";
	public static final @NonNull String VISITABLE_CLASSES = "Visitable Classes";
	public static final @NonNull String VISITABLE_INTERFACE = "Visitable Interface";

	public static @NonNull String atNonNull(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			String value = genAnnotation.getDetails().get(USE_NULL_ANNOTATIONS);
			if (value != null) {
			    boolean useAtNonNull = Boolean.valueOf(value);
			    if (useAtNonNull) {
			    	return "@" + genModel.getImportedName(DerivedConstants.ORG_ECLIPSE_JDT_ANNOTATION_NON_NULL) + " ";
			    }
			}
		}
		return "";
	}
	
	public static @NonNull String atNullable(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		if (genAnnotation != null) {
			String value = genAnnotation.getDetails().get(USE_NULL_ANNOTATIONS);
			if (value != null) {
			    boolean useAtNonNull = Boolean.valueOf(value);
			    if (useAtNonNull) {
			    	return "@" + genModel.getImportedName(DerivedConstants.ORG_ECLIPSE_JDT_ANNOTATION_NULLABLE) + " ";
			    }
			}
		}
		return "";
	}

	public static Object copyAndPaste(@NonNull GenClass genClass) {
		String interfaceName = genClass.getQualifiedInterfaceName();
		GenModel genModel = genClass.getGenModel();
		String javaCopyFile = GenModelUtil.getAnnotation(genModel, OCL_GENMODEL_COPY_AND_PASTE_URI, interfaceName);
		if (javaCopyFile == null) {
			return "";
		}
		URI relativeURI = URI.createURI(javaCopyFile, true);
		URI baseURI = URI.createPlatformResourceURI("/" + genModel.getModelPluginID() + "/", true);
		URI uri = relativeURI.resolve(baseURI);
		StringBuilder s = new StringBuilder();
		s.append("	/**\n");
		s.append("	 * Start of copy from " + uri + " \n");
		s.append("	 */\n");
		s.append("	@SuppressWarnings(\"unused\") private static int _START_OF_COPY_ = 0;\n");
		char[] buf = new char[4096];
		try {
			InputStream iStream = URIConverter.INSTANCE.createInputStream(uri);
			Reader reader = new InputStreamReader(iStream);
			int len = 0;
			while ((len = reader.read(buf)) > 0) {
				s.append(buf, 0, len);
			}
			s.append("	/**\n");
			s.append("	 * End of copy from " + uri + " \n");
			s.append("	 */\n");
			s.append("	@SuppressWarnings(\"unused\") private static int _END_OF_COPY_ = 0;\n");
			iStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resolveImports(genModel, s.toString());
	}

	public static /*@Nullable*/ String getVisitableClass(@NonNull GenModel genModel) {		// @Nullable breaks Xtend
		return GenModelUtil.getAnnotation(genModel, OCL_GENMODEL_VISITOR_URI, VISITABLE_INTERFACE);
	}

	public static boolean isRootVisitableClass(@NonNull GenClass genClass) {
		String interfaceName = genClass.getQualifiedInterfaceName();
		String visitableClasses = GenModelUtil.getAnnotation(genClass.getGenModel(), OCL_GENMODEL_VISITOR_URI, VISITABLE_CLASSES);
		return (visitableClasses != null) && visitableClasses.contains(interfaceName);
	}

	public static @NonNull String resolveImports(GenModel genModel, String source)
	{
		int iMax = source.length();
		int iStart = 0;
		StringBuilder s = new StringBuilder();
		while (true) {
			int iPrefix = source.indexOf(ImportUtils.IMPORTS_PREFIX, iStart);
			if (iPrefix < 0) {
				break;
			}
			int iSuffix = source.indexOf(ImportUtils.IMPORTS_SUFFIX, iPrefix);
			if (iSuffix < 0) {
				break;
			}
			s.append(source, iStart, iPrefix);
			String longName = source.substring(iPrefix+ImportUtils.IMPORTS_PREFIX.length(), iSuffix);
			s.append(genModel.getImportedName(longName));
			iStart = iSuffix + ImportUtils.IMPORTS_SUFFIX.length();
		}
		s.append(source, iStart, iMax);
		return s.toString();
	}
}
