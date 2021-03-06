/*******************************************************************************
 * Copyright (c) 2013, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.AutoCodeGenOptions;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaPreVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.library.NativeProperty;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGenModelGeneratorAdapter;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AutoCodeGenerator supports generation of the content of a JavaClassFile to
 * execute a Auto transformation.
 */
public abstract class AutoCodeGenerator extends JavaCodeGenerator
{
	//	private static final Logger logger = Logger.getLogger(AutoCodeGenerator.class);

	protected final @NonNull CodeGenAnalyzer cgAnalyzer;
	protected final org.eclipse.ocl.pivot.@NonNull Package asPackage;
	protected final org.eclipse.ocl.pivot.@Nullable Package asSuperPackage;
	protected final @NonNull PivotHelper helper;
	protected final @NonNull GenModel genModel;
	protected final @NonNull GenPackage genPackage;
	protected final @Nullable GenPackage superGenPackage;
	protected final @NonNull GenPackage baseGenPackage;
	//protected final @Nullable GenPackage superGenPackage;
	protected final @NonNull Map<String, CGPackage> externalPackages = new HashMap<String, CGPackage>();


	public AutoCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory,
			org.eclipse.ocl.pivot.@NonNull Package asPackage,
			org.eclipse.ocl.pivot.@Nullable Package asSuperPackage,
			@NonNull GenPackage genPackage,
			@Nullable GenPackage superGenPackage,
			@Nullable GenPackage baseGenPackage) {
		super(environmentFactory, genPackage.getGenModel());
		this.helper = new PivotHelper(environmentFactory);
		this.genModel = ClassUtil.nonNullState(genPackage.getGenModel());
		getOptions().setUseNullAnnotations(OCLinEcoreGenModelGeneratorAdapter.useNullAnnotations(genModel));
		cgAnalyzer = new CodeGenAnalyzer(this);
		this.asPackage = asPackage;
		this.asSuperPackage = asSuperPackage;
		this.genPackage = genPackage;
		this.superGenPackage = superGenPackage;
		this.baseGenPackage = baseGenPackage != null ? baseGenPackage
			: superGenPackage != null ? superGenPackage
				: genPackage;
	}

	protected @NonNull String getProjectPrefix() {
		String prefix = genPackage.getPrefix();
		assert prefix != null;
		return prefix;
	}

	protected @Nullable String getSuperProjectPrefix() {
		if (superGenPackage != null) {
			String prefix = superGenPackage.getPrefix();
			assert prefix != null;
			return prefix;
		}
		return null;
	}

	protected @NonNull String getBasePrefix() {
		String prefix = baseGenPackage.getPrefix();
		assert prefix != null;
		return prefix;
	}


	protected @NonNull AS2CGVisitor createAS2CGVisitor() {
		return new AS2CGVisitor(cgAnalyzer);
	}

	@Override
	public abstract @NonNull CG2JavaPreVisitor createCG2JavaPreVisitor();

	protected abstract @NonNull AutoCG2JavaVisitor<@NonNull ? extends AutoCodeGenerator> createCG2JavaVisitor(@NonNull CGPackage cgPackage, @Nullable List</*@NonNull*/ CGValuedElement> sortedGlobals);

	protected abstract @NonNull List<CGPackage> createCGPackages() throws ParserException;

	protected org.eclipse.ocl.pivot.@NonNull Package createASPackage(@NonNull String packageName) {
		String nsURI = "java://"+packageName;		// java: has no significance other than diagnostic readability
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.createPackage(packageName, "viz", nsURI, IdManager.getRootPackageId(nsURI));
		Model asRoot = PivotUtil.createModel(nsURI + ".java");
		asRoot.getOwnedPackages().add(asPackage);
		metamodelManager.installRoot(asRoot);
		return asPackage;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class createASClass(org.eclipse.ocl.pivot.@NonNull Package asPackage, @NonNull String className) {
		org.eclipse.ocl.pivot.Class asVisitorClass = PivotUtil.createClass(className);
		asPackage.getOwnedClasses().add(asVisitorClass);
		return asVisitorClass;
	}

	@Override
	protected @NonNull AutoCodeGenOptions createOptions() {
		return new AutoCodeGenOptions();
	}



	public @NonNull String generateClassFile(@NonNull CGPackage cgPackage) {

		optimize(cgPackage);
		List<@NonNull CGValuedElement> sortedGlobals = prepareGlobals();
		AutoCG2JavaVisitor<@NonNull ?> generator = createCG2JavaVisitor(cgPackage, sortedGlobals);
		generator.safeVisit(cgPackage);
		ImportNameManager importNameManager = generator.getImportNameManager();
		Map<@NonNull String, @Nullable String> long2ShortImportNames = importNameManager.getLong2ShortImportNames();
		return ImportUtils.resolveImports(generator.toString(), long2ShortImportNames, false);
	}

	@Override
	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return cgAnalyzer;
	}

	protected @NonNull CGClass getExternalClass(@NonNull Class<?> javaClass, CGClass... javaGenerics) {
		String packageName = javaClass.getPackage().getName();
		String className = javaClass.getSimpleName();
		assert className != null;
		CGClass externalClass = getExternalClass(packageName, className, javaClass.isInterface());
		if (javaGenerics != null) {
			for (CGClass javaGeneric : javaGenerics) {
				if (javaGeneric == null) {
					javaGeneric = getExternalClass(null, "?", true);
				}
				externalClass.getTemplateParameters().add(javaGeneric);
			}
		}
		return externalClass;
	}

	protected @NonNull CGClass getExternalClass(@Nullable String packageName, @NonNull String className, boolean isInterface) {
		CGPackage cgPackage = externalPackages.get(packageName);
		if (cgPackage == null) {
			cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
			cgPackage.setName(packageName);
			externalPackages.put(packageName, cgPackage);
		}
		CGClass cgClass = NameUtil.getNameable(cgPackage.getClasses(), className);
		if (cgClass == null) {
			cgClass = CGModelFactory.eINSTANCE.createCGClass();
			cgClass.setName(className);
			cgClass.setInterface(isInterface);
			cgPackage.getClasses().add(cgClass);
		}
		return cgClass;
	}

	protected @Nullable CGClass getExternalClass(org.eclipse.ocl.pivot.@NonNull Class aClass) {
		return getExternalClass(genModelHelper.getEcoreInterfaceClass(aClass));
	}

	public @NonNull GenPackage getGenPackage() {
		return genPackage;
	}

	@Override
	public @NonNull AutoCodeGenOptions getOptions() {
		return (AutoCodeGenOptions) super.getOptions();
	}

	public @NonNull String getQualifiedName() {
		String className = ClassUtil.nonNullState(asPackage.getName());
		String packagePrefix = getOptions().getPackagePrefix();
		if (packagePrefix != null) {
			return packagePrefix + "." + className;
		} else {
			return className;
		}
	}

	protected @NonNull String getSourceFileName(String javaClassName) {
		return genModel.getModelDirectory() + "/" + getSourcePackageName().replace('.', '/') + "/" + javaClassName;
	}

	abstract protected @NonNull String getSourcePackageName();

	public void saveSourceFile() {
		try {
			@NonNull
			List<CGPackage> allCGPackages = createCGPackages();
			for (CGPackage cgPackage : allCGPackages){
				String className = cgPackage.getClasses().get(0).getName();
				URI uri = URI.createPlatformResourceURI(getSourceFileName(className+".java"), true);
				System.out.println("Creating " + uri);
				String javaCodeSource = generateClassFile(cgPackage);;
				try {

					System.out.println("Saving " + uri);
					OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(uri);
					Writer writer = new OutputStreamWriter(outputStream);
					writer.append(javaCodeSource);
					writer.close();
				} catch (IOException e) {
					throw new IllegalStateException("Failed to save '" + uri + "'", e);
				}
			}
		} catch (ParserException e) {
			throw new IllegalStateException("Failed to process '" + asPackage.getName() + "'", e);
		}
	}

	/**
	 * Helper method that might be used by derived generators
	 *
	 * @param op
	 * @return
	 */
	protected @NonNull ExpressionInOCL getExpressionInOCL(Operation op) {
		try {
			LanguageExpression envSpecification = ClassUtil.nonNullState(op.getBodyExpression());
			return environmentFactory.parseSpecification(envSpecification);
		} catch (ParserException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Any code generator should an IdResolverVariables.
	 *
	 * Derived classes must provide it
	 *
	 * @return the CG IdResolver variable
	 */
	abstract public @NonNull CGValuedElement getIdResolverVariable();

	protected @NonNull Property createNativeProperty(@NonNull String name, @NonNull Type asElementType,
			boolean isReadOnly, boolean isRequired) {
		Property asProperty = PivotUtil.createProperty(name, asElementType);
		asProperty.setImplementation(NativeProperty.INSTANCE);
		asProperty.setIsReadOnly(isReadOnly);
		asProperty.setIsRequired(isReadOnly);
		return asProperty;
	}

	protected @NonNull Property createNativeProperty(@NonNull String name, @NonNull Class<?> javaClass,
			boolean isReadOnly, boolean isRequired) {
		Package javaPackage = javaClass.getPackage();
		String packageName = javaPackage.getName();
		assert packageName != null;
		String className = javaClass.getSimpleName();
		assert className != null;
		RootPackageId javaPackageId = IdManager.getRootPackageId(packageName);
		Orphanage orphanage = metamodelManager.getCompleteModel().getOrphanage();
		org.eclipse.ocl.pivot.Package asPackage = NameUtil.getNameable(orphanage.getOwnedPackages(), packageName);
		if (asPackage == null) {
			asPackage = PivotUtil.createPackage(packageName, packageName, packageName, javaPackageId);
			orphanage.getOwnedPackages().add(asPackage);
		}
		org.eclipse.ocl.pivot.Class asType = NameUtil.getNameable(asPackage.getOwnedClasses(), className);
		if (asType == null) {
			asType = PivotUtil.createClass(className);
			asPackage.getOwnedClasses().add(asType);
		}
		return createNativeProperty(name, asType, isReadOnly, isRequired);
	}
}

