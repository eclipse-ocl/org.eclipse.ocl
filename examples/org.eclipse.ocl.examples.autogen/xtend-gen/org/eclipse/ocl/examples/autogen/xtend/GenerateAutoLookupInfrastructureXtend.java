/**
 * Copyright (c) 2015 Willink Transformations, University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 */
package org.eclipse.ocl.examples.autogen.xtend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.ocl.examples.autogen.lookup.LookupCGUtil;
import org.eclipse.ocl.examples.autogen.lookup.LookupCodeGenerator;
import org.eclipse.ocl.examples.build.utilities.GenPackageHelper;
import org.eclipse.ocl.examples.build.xtend.GenerateVisitorsWorkflowComponent;
import org.eclipse.ocl.examples.build.xtend.GenerateVisitorsXtend;
import org.eclipse.ocl.examples.build.xtend.MergeWriter;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class GenerateAutoLookupInfrastructureXtend extends GenerateVisitorsXtend {
  protected String lookupFilePath;
  
  protected String lookupArtifactsJavaPackage;
  
  protected String lookupArtifactsOutputFolder;
  
  protected String lookupPackageName;
  
  protected String superLookupPackageName;
  
  protected String baseLookupPackageName;
  
  protected GenModelHelper genModelHelper;
  
  @Override
  public void checkConfiguration(final Issues issues) {
    super.checkConfiguration(issues);
    boolean _isDefined = GenerateVisitorsWorkflowComponent.isDefined(this.lookupFilePath);
    boolean _not = (!_isDefined);
    if (_not) {
      issues.addError(this, "lookupFilePath must be specified");
    }
  }
  
  /**
   * The path inside the projectName to the Complete OCL file which contains the
   * name resolution description (e.g. "model/NameResolution.ocl"). It
   * can't be null
   */
  public String setLookupFilePath(final String lookupFilePath) {
    return this.lookupFilePath = lookupFilePath;
  }
  
  public String setLookupPackageName(final String lookupPackageName) {
    return this.lookupPackageName = lookupPackageName;
  }
  
  public String setSuperLookupPackageName(final String superLookupPackageName) {
    return this.superLookupPackageName = superLookupPackageName;
  }
  
  public String setBaseLookupPackageName(final String baseLookupPackageName) {
    return this.baseLookupPackageName = baseLookupPackageName;
  }
  
  @Override
  protected void doSetup() {
    CompleteOCLStandaloneSetup.doSetup();
    OCLstdlib.install();
  }
  
  @Override
  protected void doPropertiesConfiguration(final OCL ocl) {
    super.doPropertiesConfiguration(ocl);
    final URI genModelURI = this.getGenModelURI(this.projectName, this.genModelFile);
    final Resource genModelResource = this.getGenModelResource(ocl, genModelURI);
    final GenPackage genPackage = this.getGenPackage(genModelResource);
    GenPackageHelper helper = new GenPackageHelper(genPackage);
    if (((!GenerateVisitorsWorkflowComponent.isDefined(this.lookupPackageName)) || (this.lookupPackageName.length() == 0))) {
      String _modelPackageName = helper.getModelPackageName();
      String _plus = (_modelPackageName + ".lookup");
      this.lookupPackageName = _plus;
    }
    boolean _isDerived = this.isDerived();
    if (_isDerived) {
      final URI superGenModelURI = this.getGenModelURI(this.superProjectName, this.superGenModelFile);
      final Resource superGenModelResource = this.getGenModelResource(ocl, superGenModelURI);
      final GenPackage superGenPackage = this.getGenPackage(superGenModelResource);
      GenPackageHelper _genPackageHelper = new GenPackageHelper(superGenPackage);
      helper = _genPackageHelper;
      if (((!GenerateVisitorsWorkflowComponent.isDefined(this.superLookupPackageName)) || (this.superLookupPackageName.length() == 0))) {
        String _modelPackageName_1 = helper.getModelPackageName();
        String _plus_1 = (_modelPackageName_1 + ".lookup");
        this.superLookupPackageName = _plus_1;
      }
      final URI baseGenModelURI = this.getGenModelURI(this.baseProjectName, this.baseGenModelFile);
      final Resource baseGenModelResource = this.getGenModelResource(ocl, baseGenModelURI);
      final GenPackage baseGenPackage = this.getGenPackage(baseGenModelResource);
      GenPackageHelper _genPackageHelper_1 = new GenPackageHelper(baseGenPackage);
      helper = _genPackageHelper_1;
      if (((!GenerateVisitorsWorkflowComponent.isDefined(this.baseLookupPackageName)) || (this.baseLookupPackageName.length() == 0))) {
        String _modelPackageName_2 = helper.getModelPackageName();
        String _plus_2 = (_modelPackageName_2 + ".lookup");
        this.baseLookupPackageName = _plus_2;
      }
    } else {
      if (((!GenerateVisitorsWorkflowComponent.isDefined(this.baseLookupPackageName)) || (this.baseLookupPackageName.length() == 0))) {
        this.baseLookupPackageName = this.lookupPackageName;
      }
    }
  }
  
  @SuppressWarnings("null")
  protected void doGenerateVisitors(final GenPackage genPackage) {
    LookupCodeGenerator.generate(genPackage, this.superGenPackage, this.baseGenPackage, this.projectName, this.lookupFilePath, 
      this.lookupPackageName, this.lookupPackageName, null);
  }
  
  protected void generateAutoLookupResultItf(final EPackage ePackage) {
    try {
      boolean isDerived = this.isDerived();
      if ((!isDerived)) {
        MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + this.projectPrefix) + "LookupResult.java"));
        StringConcatenation _builder = new StringConcatenation();
        String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
        _builder.append(_generateHeaderWithTemplate);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import java.util.List;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.NonNull;");
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.Nullable;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("/**");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("* The lookup result returned by the name lookup solver");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("public interface ");
        _builder.append(this.projectPrefix);
        _builder.append("LookupResult<NE> {");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Nullable");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("NE getSingleResult();");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@NonNull");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("List<NE> getAllResults();");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("int size();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        writer.append(_builder.toString());
        writer.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateAutoLookupResultClass(final EPackage ePackage) {
    try {
      boolean isDerived = this.isDerived();
      if ((!isDerived)) {
        MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + this.projectPrefix) + "LookupResultImpl.java"));
        StringConcatenation _builder = new StringConcatenation();
        String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
        _builder.append(_generateHeaderWithTemplate);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import java.util.ArrayList;");
        _builder.newLine();
        _builder.append("import java.util.Collections;");
        _builder.newLine();
        _builder.append("import java.util.List;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.NonNull;");
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.Nullable;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public class ");
        _builder.append(this.projectPrefix);
        _builder.append("LookupResultImpl<NE> implements ");
        _builder.append(this.projectPrefix);
        _builder.append("LookupResult<NE> {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private @NonNull List<NE> results = new ArrayList<NE>();");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        _builder.append(this.projectPrefix, "\t");
        _builder.append("LookupResultImpl(List<NE> results){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("this.results.addAll(results);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public @NonNull List<NE> getAllResults() {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return Collections.unmodifiableList(results);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public @Nullable NE getSingleResult() {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return results.size() == 0 ? null : results.get(0);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public int size() {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return results.size();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        writer.append(_builder.toString());
        writer.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateAutoLookupFilterItf(final EPackage ePackage) {
    try {
      final boolean isDerived = this.isDerived();
      if ((!isDerived)) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(this.projectPrefix);
        _builder.append("LookupFilter");
        final String itfName = _builder.toString();
        final MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + itfName) + ".java"));
        StringConcatenation _builder_1 = new StringConcatenation();
        String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
        _builder_1.append(_generateHeaderWithTemplate);
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        _builder_1.append("import org.eclipse.jdt.annotation.NonNull;");
        _builder_1.newLine();
        _builder_1.append("import ");
        _builder_1.append(this.modelPackageName);
        _builder_1.append(".NamedElement;");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        _builder_1.append("/**");
        _builder_1.newLine();
        _builder_1.append(" ");
        _builder_1.append("* ");
        _builder_1.newLine();
        _builder_1.append(" ");
        _builder_1.append("*/");
        _builder_1.newLine();
        _builder_1.append("public interface ");
        _builder_1.append(itfName);
        _builder_1.append(" {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("boolean matches(@NonNull NamedElement namedElement);");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        writer.append(_builder_1.toString());
        writer.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateAutoLookupFilterClass(final EPackage ePackage) {
    try {
      final boolean isDerived = this.isDerived();
      if ((!isDerived)) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("Abstract");
        _builder.append(this.projectPrefix);
        _builder.append("LookupFilter");
        final String className = _builder.toString();
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(this.projectPrefix);
        _builder_1.append("LookupFilter");
        final String itfName = _builder_1.toString();
        final MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + className) + ".java"));
        StringConcatenation _builder_2 = new StringConcatenation();
        String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
        _builder_2.append(_generateHeaderWithTemplate);
        _builder_2.newLineIfNotEmpty();
        _builder_2.newLine();
        _builder_2.append("import org.eclipse.jdt.annotation.NonNull;");
        _builder_2.newLine();
        _builder_2.append("import ");
        _builder_2.append(this.modelPackageName);
        _builder_2.append(".NamedElement;");
        _builder_2.newLineIfNotEmpty();
        _builder_2.newLine();
        _builder_2.append("/**");
        _builder_2.newLine();
        _builder_2.append(" ");
        _builder_2.append("* ");
        _builder_2.newLine();
        _builder_2.append(" ");
        _builder_2.append("*/");
        _builder_2.newLine();
        _builder_2.append("public abstract class ");
        _builder_2.append(className);
        _builder_2.append("<C extends NamedElement> implements ");
        _builder_2.append(itfName);
        _builder_2.append(" {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("\t");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("@NonNull private Class<C> _class;");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("public ");
        _builder_2.append(className, "\t");
        _builder_2.append("(@NonNull Class<C> _class) {");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("\t\t");
        _builder_2.append("this._class = _class;");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("}");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("@SuppressWarnings(\"unchecked\")");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("@Override");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("public boolean matches(@NonNull NamedElement namedElement) {");
        _builder_2.newLine();
        _builder_2.append("\t\t");
        _builder_2.append("return _class.isInstance(namedElement) && _matches((C)namedElement);");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("}");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.newLine();
        _builder_2.append("\t");
        _builder_2.append("abstract protected Boolean _matches(@NonNull C element);");
        _builder_2.newLine();
        _builder_2.append("}");
        _builder_2.newLine();
        writer.append(_builder_2.toString());
        writer.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateSingleResultLookupEnvironment(final EPackage ePackage) {
    try {
      boolean isDerived = this.isDerived();
      String className = (this.projectPrefix + "SingleResultLookupEnvironment");
      if ((!isDerived)) {
        MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + className) + ".java"));
        StringConcatenation _builder = new StringConcatenation();
        String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
        _builder.append(_generateHeaderWithTemplate);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import java.util.ArrayList;");
        _builder.newLine();
        _builder.append("import java.util.Collection;");
        _builder.newLine();
        _builder.append("import java.util.List;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import org.eclipse.emf.ecore.EClass;");
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.NonNull;");
        _builder.newLine();
        _builder.append("import org.eclipse.jdt.annotation.Nullable;");
        _builder.newLine();
        _builder.append("import org.eclipse.ocl.pivot.evaluation.Executor;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import ");
        _builder.append(this.modelPackageName);
        _builder.append(".NamedElement;");
        _builder.newLineIfNotEmpty();
        _builder.append("import ");
        _builder.append(this.lookupPackageName);
        _builder.append(".LookupEnvironment;");
        _builder.newLineIfNotEmpty();
        _builder.append("import ");
        _builder.append(this.lookupPackageName);
        _builder.append(".impl.LookupEnvironmentImpl;");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public class ");
        _builder.append(className);
        _builder.append(" extends LookupEnvironmentImpl   {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private @NonNull Executor executor;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private @NonNull String name;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private @NonNull EClass typeFilter;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private @Nullable ");
        _builder.append(this.projectPrefix, "\t");
        _builder.append("LookupFilter expFilter;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        _builder.append(className, "\t");
        _builder.append("(@NonNull Executor executor, @NonNull EClass typeFilter, @NonNull String name,  @Nullable ");
        _builder.append(this.projectPrefix, "\t");
        _builder.append("LookupFilter expFilter) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("this.executor = executor;");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("this.name = name;");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("this.typeFilter = typeFilter;");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("this.expFilter = expFilter;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        _builder.append(className, "\t");
        _builder.append("(@NonNull Executor executor, @NonNull EClass typeFilter, @NonNull String name) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("this(executor,typeFilter, name, null);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@NonNull");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public Executor getExecutor() {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return executor;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public boolean hasFinalResult() {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("for (NamedElement element : getNamedElements()) {");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("if (name.equals(element.getName())) {");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("return true;");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return false;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@NonNull");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public LookupEnvironment addElement(@Nullable NamedElement namedElement) {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("if (namedElement != null) {");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("if (name.equals(namedElement.getName())) {");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("if (typeFilter.isInstance(namedElement)) {");
        _builder.newLine();
        _builder.append("\t\t\t\t    ");
        _builder.append(this.projectPrefix, "\t\t\t\t    ");
        _builder.append("LookupFilter expFilter2 = expFilter;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t\t");
        _builder.append("if (expFilter2 == null || expFilter2.matches(namedElement)) {");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("List<NamedElement> elements = getNamedElements();");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("if (!elements.contains(namedElement)) { \t// FIXME use a set ?");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t\t");
        _builder.append("elements.add(namedElement);");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return this;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@NonNull");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public <NE extends NamedElement > LookupEnvironment addElements(");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("@Nullable Collection<NE> namedElements) {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("if (namedElements != null) {");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("for (NamedElement namedElement : namedElements) {");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("addElement(namedElement);");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return this;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@SuppressWarnings(\"unchecked\")");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public <NE extends NamedElement> List<NE> getNamedElementsByKind(Class<NE> class_) {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("List<NE> result = new ArrayList<NE>(); ");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("for (NamedElement namedElement : getNamedElements()) {");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("if (class_.isAssignableFrom(namedElement.getClass())) {");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("result.add((NE)namedElement);");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return result;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        writer.append(_builder.toString());
        writer.close();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateAutoLookupSolver(final GenPackage genPackage, final GenPackage basePackage) {
    try {
      EPackage ePackage = genPackage.getEcorePackage();
      String fqPackageItf = this.genModelHelper.getQualifiedPackageInterfaceName(ePackage);
      List<Operation> lookupOps = this.getLookupMethods(basePackage);
      boolean isDerived = this.isDerived();
      String className = (this.projectPrefix + "LookupSolver");
      String superClassName = (this.superProjectPrefix + "LookupSolver");
      MergeWriter writer = new MergeWriter(((this.lookupArtifactsOutputFolder + className) + ".java"));
      StringConcatenation _builder = new StringConcatenation();
      String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.lookupArtifactsJavaPackage);
      _builder.append(_generateHeaderWithTemplate);
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import org.eclipse.jdt.annotation.NonNull;");
      _builder.newLine();
      _builder.append("import org.eclipse.ocl.pivot.evaluation.Executor;");
      _builder.newLine();
      {
        Set<String> _lookupTypeNames = this.getLookupTypeNames(lookupOps, "Unqualified");
        for(final String typeName : _lookupTypeNames) {
          _builder.append("import ");
          _builder.append(this.visitorPackageName);
          _builder.append(".");
          _builder.append(this.projectPrefix);
          _builder.append("Unqualified");
          _builder.append(typeName);
          _builder.append("LookupVisitor;");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        Set<String> _lookupTypeNames_1 = this.getLookupTypeNames(lookupOps, "Exported");
        for(final String typeName_1 : _lookupTypeNames_1) {
          _builder.append("import ");
          _builder.append(this.visitorPackageName);
          _builder.append(".");
          _builder.append(this.projectPrefix);
          _builder.append("Exported");
          _builder.append(typeName_1);
          _builder.append("LookupVisitor;");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        Set<String> _lookupTypeNames_2 = this.getLookupTypeNames(lookupOps, "Qualified");
        for(final String typeName_2 : _lookupTypeNames_2) {
          _builder.append("import ");
          _builder.append(this.visitorPackageName);
          _builder.append(".");
          _builder.append(this.projectPrefix);
          _builder.append("Qualified");
          _builder.append(typeName_2);
          _builder.append("LookupVisitor;");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      {
        if (isDerived) {
          _builder.append("import ");
          _builder.append(this.superLookupPackageName);
          _builder.append(".util.");
          _builder.append(superClassName);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("import ");
          _builder.append(this.baseLookupPackageName);
          _builder.append(".util.");
          String _prefix = basePackage.getPrefix();
          _builder.append(_prefix);
          _builder.append("LookupResult;");
          _builder.newLineIfNotEmpty();
          _builder.append("import ");
          _builder.append(this.baseLookupPackageName);
          _builder.append(".util.");
          String _prefix_1 = basePackage.getPrefix();
          _builder.append(_prefix_1);
          _builder.append("LookupResultImpl;");
          _builder.newLineIfNotEmpty();
          _builder.append("import ");
          _builder.append(this.baseLookupPackageName);
          _builder.append(".util.");
          String _prefix_2 = basePackage.getPrefix();
          _builder.append(_prefix_2);
          _builder.append("SingleResultLookupEnvironment;");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("public class ");
      _builder.append(className);
      {
        if (isDerived) {
          _builder.append(" extends ");
          _builder.append(superClassName);
        }
      }
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      {
        if ((!isDerived)) {
          _builder.append("protected final @NonNull Executor executor;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("public ");
      _builder.append(className, "\t");
      _builder.append(" (@NonNull Executor executor) {");
      _builder.newLineIfNotEmpty();
      {
        if (isDerived) {
          _builder.append("\t\t");
          _builder.append("super(executor);");
          _builder.newLine();
        } else {
          _builder.append("\t\t");
          _builder.append("this.executor = executor;");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      {
        for(final Operation op : lookupOps) {
          _builder.append("\t");
          final String opName = op.getName();
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final boolean isExportedLookup = this.isExportedLookupOperation(op);
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final String lookupVisitorName = this.getLookupVisitorName(op, op.getType().getName());
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final boolean hasAdditionalFilter = this.hasAdditionalFilterArgs(op);
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final String lookupVars = this.getLookupArgs(op);
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final String typeFQName = this.getTypeFQName(op.getType());
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          final String typeLiteral = this.getTypeLiteral(op.getType());
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public ");
          String _prefix_3 = basePackage.getPrefix();
          _builder.append(_prefix_3, "\t");
          _builder.append("LookupResult<");
          _builder.append(typeFQName, "\t");
          _builder.append("> ");
          _builder.append(opName, "\t");
          _builder.append("(");
          String _typeFQName = this.getTypeFQName(op.getOwningClass());
          _builder.append(_typeFQName, "\t");
          _builder.append(" context");
          {
            List<Parameter> _ownedParameters = op.getOwnedParameters();
            for(final Parameter param : _ownedParameters) {
              _builder.append(", ");
              String _typeFQName_1 = this.getTypeFQName(param.getType());
              _builder.append(_typeFQName_1, "\t");
              _builder.append(" ");
              String _name = param.getName();
              _builder.append(_name, "\t");
            }
          }
          _builder.append(") {");
          _builder.newLineIfNotEmpty();
          {
            if (hasAdditionalFilter) {
              _builder.append("\t");
              _builder.append("\t");
              String _name_1 = op.getType().getName();
              _builder.append(_name_1, "\t\t");
              _builder.append("Filter filter = new ");
              String _name_2 = op.getType().getName();
              _builder.append(_name_2, "\t\t");
              _builder.append("Filter(executor, ");
              String _filterArgs = this.getFilterArgs(op);
              _builder.append(_filterArgs, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("\t");
          String _prefix_4 = basePackage.getPrefix();
          _builder.append(_prefix_4, "\t\t");
          _builder.append("SingleResultLookupEnvironment _lookupEnv = new ");
          String _prefix_5 = basePackage.getPrefix();
          _builder.append(_prefix_5, "\t\t");
          _builder.append("SingleResultLookupEnvironment(executor, ");
          _builder.append(fqPackageItf, "\t\t");
          _builder.append(".Literals.");
          _builder.append(typeLiteral, "\t\t");
          _builder.append(lookupVars, "\t\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append(lookupVisitorName, "\t\t");
          _builder.append(" _lookupVisitor = new ");
          _builder.append(lookupVisitorName, "\t\t");
          _builder.append("(_lookupEnv");
          {
            if (isExportedLookup) {
              _builder.append(", importer");
            }
          }
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("context.accept(_lookupVisitor);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("return new ");
          String _prefix_6 = basePackage.getPrefix();
          _builder.append(_prefix_6, "\t\t");
          _builder.append("LookupResultImpl<");
          _builder.append(typeFQName, "\t\t");
          _builder.append(">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t\t");
          _builder.append("(_lookupEnv.getNamedElementsByKind(");
          _builder.append(typeFQName, "\t\t\t\t");
          _builder.append(".class));");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("}");
      _builder.newLine();
      writer.append(_builder.toString());
      writer.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void generateAutoCommonLookupVisitor(final EPackage ePackage) {
    try {
      String fqPackageItf = this.genModelHelper.getQualifiedPackageInterfaceName(ePackage);
      boolean isDerived = this.isDerived();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Abstract");
      _builder.append(this.projectPrefix);
      _builder.append("CommonLookupVisitor");
      String visitorName = _builder.toString();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Abstract");
      _builder_1.append(this.superProjectPrefix);
      _builder_1.append("CommonLookupVisitor");
      String superVisitorName = _builder_1.toString();
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("AbstractExtending");
      {
        if (isDerived) {
          _builder_2.append(this.projectPrefix);
        }
      }
      _builder_2.append("Visitor");
      String superClassName = _builder_2.toString();
      MergeWriter writer = new MergeWriter(((this.outputFolder + visitorName) + ".java"));
      StringConcatenation _builder_3 = new StringConcatenation();
      String _generateHeaderWithTemplate = this.generateHeaderWithTemplate(ePackage, this.visitorPackageName);
      _builder_3.append(_generateHeaderWithTemplate);
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("import org.eclipse.jdt.annotation.NonNull;");
      _builder_3.newLine();
      _builder_3.append("import org.eclipse.jdt.annotation.Nullable;");
      _builder_3.newLine();
      _builder_3.append("import org.eclipse.ocl.pivot.internal.evaluation.EvaluationCache;");
      _builder_3.newLine();
      {
        if ((!isDerived)) {
          _builder_3.append("import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;");
          _builder_3.newLine();
        }
      }
      _builder_3.newLine();
      _builder_3.append("import ");
      _builder_3.append(this.baseLookupPackageName);
      _builder_3.append(".LookupEnvironment;");
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("import ");
      _builder_3.append(this.visitablePackageName);
      _builder_3.append(".");
      _builder_3.append(this.visitableClassName);
      _builder_3.append(";");
      _builder_3.newLineIfNotEmpty();
      {
        if (isDerived) {
          _builder_3.append("import ");
          _builder_3.append(this.superVisitorPackageName);
          _builder_3.append(".");
          _builder_3.append(superVisitorName);
          _builder_3.append(";");
          _builder_3.newLineIfNotEmpty();
        }
      }
      _builder_3.newLine();
      _builder_3.append("public abstract class ");
      _builder_3.append(visitorName);
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t");
      _builder_3.append("extends ");
      _builder_3.append(superClassName, "\t");
      _builder_3.append("<@Nullable LookupEnvironment, @NonNull LookupEnvironment> {");
      _builder_3.newLineIfNotEmpty();
      _builder_3.newLine();
      _builder_3.append("\t");
      {
        if ((!isDerived)) {
          _builder_3.append("protected final @NonNull EvaluationCache evaluationCache;");
        }
      }
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t");
      {
        if (isDerived) {
          _builder_3.append("private ");
          _builder_3.append(superVisitorName, "\t");
          _builder_3.append(" delegate;");
        }
      }
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.append("protected ");
      _builder_3.append(visitorName, "\t");
      _builder_3.append("(@NonNull LookupEnvironment context) {");
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t\t");
      _builder_3.append("super(context);");
      _builder_3.newLine();
      _builder_3.append("\t\t");
      {
        if ((!isDerived)) {
          _builder_3.append("this.evaluationCache = ((ExecutorInternalExtension)context.getExecutor()).getEvaluationCache();");
        }
      }
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t\t");
      {
        if (isDerived) {
          _builder_3.append("this.delegate = createSuperLangVisitor();");
        }
      }
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("\t");
      _builder_3.append("}");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.append("@Override");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.append("public final LookupEnvironment visiting(@NonNull Visitable visitable) {");
      _builder_3.newLine();
      {
        if (isDerived) {
          _builder_3.append("\t\t");
          _builder_3.append("return ");
          _builder_3.append(fqPackageItf, "\t\t");
          _builder_3.append(".eINSTANCE == visitable.eClass().getEPackage()");
          _builder_3.newLineIfNotEmpty();
          _builder_3.append("\t\t");
          _builder_3.append("\t");
          _builder_3.append("? doVisiting(visitable)");
          _builder_3.newLine();
          _builder_3.append("\t\t");
          _builder_3.append("\t");
          _builder_3.append(": visitable.accept(getSuperLangVisitor());");
          _builder_3.newLine();
        } else {
          _builder_3.append("\t\t");
          _builder_3.append("return doVisiting(visitable);");
          _builder_3.newLine();
        }
      }
      _builder_3.append("\t");
      _builder_3.append("}");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.newLine();
      {
        if (isDerived) {
          _builder_3.append("\t");
          _builder_3.append("protected ");
          _builder_3.append(superVisitorName, "\t");
          _builder_3.append(" getSuperLangVisitor(){");
          _builder_3.newLineIfNotEmpty();
          _builder_3.append("\t");
          _builder_3.append("\t");
          _builder_3.append("if (delegate == null) {");
          _builder_3.newLine();
          _builder_3.append("\t");
          _builder_3.append("\t\t");
          _builder_3.append("delegate = createSuperLangVisitor();");
          _builder_3.newLine();
          _builder_3.append("\t");
          _builder_3.append("\t");
          _builder_3.append("}");
          _builder_3.newLine();
          _builder_3.append("\t");
          _builder_3.append("\t");
          _builder_3.append("return delegate;");
          _builder_3.newLine();
          _builder_3.append("\t");
          _builder_3.append("}");
          _builder_3.newLine();
        }
      }
      _builder_3.append("\t");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.append("abstract protected LookupEnvironment doVisiting(@NonNull Visitable visitable);");
      _builder_3.newLine();
      _builder_3.append("\t");
      _builder_3.newLine();
      _builder_3.append("\t");
      {
        if (isDerived) {
          _builder_3.append("abstract protected ");
          _builder_3.append(superVisitorName, "\t");
          _builder_3.append("  createSuperLangVisitor();");
        }
      }
      _builder_3.newLineIfNotEmpty();
      _builder_3.append("}");
      _builder_3.newLine();
      writer.append(_builder_3.toString());
      writer.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void generateVisitors(final GenPackage genPackage) {
    this.genModelHelper = this.createGenModelHelper(genPackage);
    this.lookupArtifactsJavaPackage = (this.lookupPackageName + ".util");
    String _replace = this.lookupArtifactsJavaPackage.replace(".", "/");
    String _plus = (this.modelFolder + _replace);
    String _plus_1 = (_plus + "/");
    this.lookupArtifactsOutputFolder = _plus_1;
    EPackage ePackage = genPackage.getEcorePackage();
    this.generateAutoLookupResultClass(ePackage);
    this.generateAutoLookupResultItf(ePackage);
    this.generateAutoLookupFilterItf(ePackage);
    this.generateAutoLookupFilterClass(ePackage);
    this.generateSingleResultLookupEnvironment(ePackage);
    this.generateAutoLookupSolver(genPackage, this.baseGenPackage);
    this.generateAutoCommonLookupVisitor(ePackage);
    this.doGenerateVisitors(genPackage);
  }
  
  private List<Operation> getLookupMethods(final GenPackage genPackage) {
    List<Operation> result = new ArrayList<Operation>();
    EnvironmentFactory envFact = PivotUtilInternal.getEnvironmentFactory(genPackage.getEcorePackage().eResource());
    List<org.eclipse.ocl.pivot.Package> _targetPackages = LookupCGUtil.getTargetPackages(genPackage, envFact, this.lookupFilePath, this.projectName);
    for (final org.eclipse.ocl.pivot.Package oclPackage : _targetPackages) {
      List<org.eclipse.ocl.pivot.Class> _ownedClasses = oclPackage.getOwnedClasses();
      for (final org.eclipse.ocl.pivot.Class oclClass : _ownedClasses) {
        List<Operation> _ownedOperations = oclClass.getOwnedOperations();
        for (final Operation oclOp : _ownedOperations) {
          boolean _isLookupOperation = this.isLookupOperation(oclOp);
          if (_isLookupOperation) {
            result.add(oclOp);
          }
        }
      }
    }
    return result;
  }
  
  private Set<String> getLookupTypeNames(final List<Operation> lookupOps, final String lookupProtocol) {
    final HashSet<String> result = CollectionLiterals.<String>newHashSet();
    for (final Operation op : lookupOps) {
      boolean _contains = op.getName().contains(lookupProtocol);
      if (_contains) {
        result.add(op.getType().getName());
      }
    }
    return result;
  }
  
  private boolean isLookupOperation(final Operation op) {
    boolean _startsWith = op.getName().startsWith("_lookup");
    boolean _not = (!_startsWith);
    if (_not) {
      return false;
    }
    List<Parameter> _ownedParameters = op.getOwnedParameters();
    for (final Parameter param : _ownedParameters) {
      boolean _equals = "env".equals(param.getName());
      if (_equals) {
        return false;
      }
    }
    return true;
  }
  
  private boolean isExportedLookupOperation(final Operation op) {
    return (this.isLookupOperation(op) && op.getName().contains("Exported"));
  }
  
  private GenModelHelper createGenModelHelper(final GenPackage genPackage) {
    PivotMetamodelManager mManager = PivotUtilInternal.getEnvironmentFactory(genPackage.getEcorePackage().eResource()).getMetamodelManager();
    return new AbstractGenModelHelper(mManager);
  }
  
  private String getTypeLiteral(final Type type) {
    GenClassifier genClassifier = this.genModelHelper.getGenClassifier(((org.eclipse.ocl.pivot.Class) type));
    return ClassUtil.<GenClassifier>nonNullState(genClassifier).getClassifierID();
  }
  
  private String getTypeFQName(final Type type) {
    String _xifexpression = null;
    if ((type instanceof CollectionType)) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("java.util.List<");
      String _typeFQName = this.getTypeFQName(((CollectionType)type).getElementType());
      _builder.append(_typeFQName);
      _builder.append(">");
      _xifexpression = _builder.toString();
    } else {
      _xifexpression = this.genModelHelper.getEcoreInterfaceName(((org.eclipse.ocl.pivot.Class) type));
    }
    return _xifexpression;
  }
  
  private String getLookupVisitorName(final Operation op, final String typeName) {
    String _xifexpression = null;
    boolean _contains = op.getName().contains("Qualified");
    if (_contains) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.projectPrefix);
      _builder.append("Qualified");
      _builder.append(typeName);
      _builder.append("LookupVisitor");
      _xifexpression = _builder.toString();
    } else {
      String _xifexpression_1 = null;
      boolean _contains_1 = op.getName().contains("Exported");
      if (_contains_1) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(this.projectPrefix);
        _builder_1.append("Exported");
        _builder_1.append(typeName);
        _builder_1.append("LookupVisitor");
        _xifexpression_1 = _builder_1.toString();
      } else {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append(this.projectPrefix);
        _builder_2.append("Unqualified");
        _builder_2.append(typeName);
        _builder_2.append("LookupVisitor");
        _xifexpression_1 = _builder_2.toString();
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  private boolean hasAdditionalFilterArgs(final Operation op) {
    boolean _xblockexpression = false;
    {
      final List<Parameter> params = op.getOwnedParameters();
      boolean _xifexpression = false;
      boolean _isExportedLookupOperation = this.isExportedLookupOperation(op);
      if (_isExportedLookupOperation) {
        int _size = params.size();
        _xifexpression = (_size > 2);
      } else {
        int _size_1 = params.size();
        _xifexpression = (_size_1 > 1);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  private String getLookupArgs(final Operation op) {
    String _xblockexpression = null;
    {
      final List<Parameter> params = op.getOwnedParameters();
      Parameter _xifexpression = null;
      boolean _isExportedLookupOperation = this.isExportedLookupOperation(op);
      if (_isExportedLookupOperation) {
        _xifexpression = params.get(1);
      } else {
        _xifexpression = params.get(0);
      }
      final Parameter nameParam = _xifexpression;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(",");
      String _name = nameParam.getName();
      _builder.append(_name);
      {
        boolean _hasAdditionalFilterArgs = this.hasAdditionalFilterArgs(op);
        if (_hasAdditionalFilterArgs) {
          _builder.append(",filter");
        }
      }
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  private String getFilterArgs(final Operation op) {
    String _xblockexpression = null;
    {
      final StringBuffer sb = new StringBuffer();
      int _xifexpression = (int) 0;
      boolean _isExportedLookupOperation = this.isExportedLookupOperation(op);
      if (_isExportedLookupOperation) {
        _xifexpression = 2;
      } else {
        _xifexpression = 1;
      }
      final int filterArgsIndex = _xifexpression;
      final List<Parameter> params = op.getOwnedParameters();
      boolean first = true;
      for (int i = filterArgsIndex; (i < params.size()); i++) {
        {
          if (first) {
            first = false;
          } else {
            sb.append(",");
          }
          StringConcatenation _builder = new StringConcatenation();
          String _name = params.get(i).getName();
          _builder.append(_name);
          sb.append(_builder);
        }
      }
      _xblockexpression = sb.toString();
    }
    return _xblockexpression;
  }
}
