/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.build.fragments;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.fragments.DeclarativeFormatterFragment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomModel;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

/**
 * DeclarativeFormatterFragmentXtend augments DeclarativeFormatterFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
@SuppressWarnings("all")
public class DeclarativeFormatterFragmentXtend extends DeclarativeFormatterFragment {
  @Override
  protected StringConcatenationClient doGetIdiomsProviderStubContent() {
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("// ");
        TypeReference _typeReference = new TypeReference(NonNull.class);
        _builder.append(_typeReference);
        _builder.newLineIfNotEmpty();
        _builder.append("// ");
        TypeReference _typeReference_1 = new TypeReference(Nullable.class);
        _builder.append(_typeReference_1);
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("public class ");
        String _simpleName = DeclarativeFormatterFragmentXtend.this.getIdiomsProviderClass(DeclarativeFormatterFragmentXtend.this.getGrammar()).getSimpleName();
        _builder.append(_simpleName);
        _builder.append(" extends ");
        TypeReference _idiomsProviderSuperClass = DeclarativeFormatterFragmentXtend.this.getIdiomsProviderSuperClass(DeclarativeFormatterFragmentXtend.this.getGrammar());
        _builder.append(_idiomsProviderSuperClass);
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private static @Nullable Iterable<@NonNull ");
        TypeReference _typeReference_2 = new TypeReference(Idiom.class);
        _builder.append(_typeReference_2, "\t");
        _builder.append("> idioms = null;");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public @NonNull Iterable<@NonNull ");
        TypeReference _typeReference_3 = new TypeReference(Idiom.class);
        _builder.append(_typeReference_3, "\t");
        _builder.append("> getIdioms(@NonNull ");
        TypeReference _typeReference_4 = new TypeReference(ResourceSet.class);
        _builder.append(_typeReference_4, "\t");
        _builder.append(" resourceSet) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("Iterable<@NonNull ");
        TypeReference _typeReference_5 = new TypeReference(Idiom.class);
        _builder.append(_typeReference_5, "\t\t");
        _builder.append("> idioms2 = idioms;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("if (idioms2 == null) {");
        _builder.newLine();
        _builder.append("\t\t\t");
        TypeReference _typeReference_6 = new TypeReference(IdiomModel.class);
        _builder.append(_typeReference_6, "\t\t\t");
        _builder.append(" idiomModel = getIdiomModel(getClass(), resourceSet, \"");
        String _idiomsPath = DeclarativeFormatterFragmentXtend.this.getIdiomsPath(DeclarativeFormatterFragmentXtend.this.getGrammar());
        _builder.append(_idiomsPath, "\t\t\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("idioms = idioms2 = getIdioms(idiomModel);");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return idioms2;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    return _client;
  }
}
