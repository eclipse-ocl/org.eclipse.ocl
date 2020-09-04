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

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.fragments.DeclarativeSerializerFragment;
import org.eclipse.ocl.examples.xtext.build.xtext.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.xtext.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.EClassValue;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;
import org.eclipse.ocl.examples.xtext.serializer.SerializationGrammarAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.TerminalRuleValue;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

/**
 * DeclarativeSerializerFragmentXtend augments DeclarativeSerializerFragment with M2T functionality
 * exploiting Xtend's string template capabilities.
 */
@SuppressWarnings("all")
public class DeclarativeSerializerFragmentXtend extends DeclarativeSerializerFragment {
  @Override
  protected StringConcatenationClient doGetSerializationMetaDataContent(final GrammarAnalysis grammarAnalysis) {
    StringConcatenationClient _xblockexpression = null;
    {
      this.newTypeReference(NonNull.class);
      this.newTypeReference(Nullable.class);
      this.initSerializationMetaDataContent(grammarAnalysis);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("public class ");
          String _simpleName = DeclarativeSerializerFragmentXtend.this.getSerializationMetaDataClass(DeclarativeSerializerFragmentXtend.this.getGrammar()).getSimpleName();
          _builder.append(_simpleName);
          _builder.append(" extends ");
          TypeReference _serializationMetaDataSuperClass = DeclarativeSerializerFragmentXtend.this.getSerializationMetaDataSuperClass(DeclarativeSerializerFragmentXtend.this.getGrammar());
          _builder.append(_serializationMetaDataSuperClass);
          _builder.newLineIfNotEmpty();
          _builder.append("{");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t ");
          _builder.append("* The metadata resulting from static analysis of the grammar.");
          _builder.newLine();
          _builder.append("\t ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private static ");
          String _newTypeReference = DeclarativeSerializerFragmentXtend.this.newTypeReference(SerializationGrammarAnalysis.class);
          _builder.append(_newTypeReference, "\t");
          _builder.append(" analysis = null;");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public ");
          String _newTypeReference_1 = DeclarativeSerializerFragmentXtend.this.newTypeReference(SerializationGrammarAnalysis.class);
          _builder.append(_newTypeReference_1, "\t");
          _builder.append(" getAnalysis() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("if (analysis == null) {");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("analysis = new ");
          String _newTypeReference_2 = DeclarativeSerializerFragmentXtend.this.newTypeReference(SerializationGrammarAnalysis.class);
          _builder.append(_newTypeReference_2, "\t\t\t");
          _builder.append("(");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t\t\t\t ");
          _builder.append("*\tThe indexable per-produceable EClass meta data.");
          _builder.newLine();
          _builder.append("\t\t\t\t ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("new ");
          String _newTypeReference_3 = DeclarativeSerializerFragmentXtend.this.newTypeReference(EClassValue.class);
          _builder.append(_newTypeReference_3, "\t\t\t\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            Iterable<EClass> _eClassIterable = DeclarativeSerializerFragmentXtend.this.getEClassIterable(grammarAnalysis);
            boolean _hasElements = false;
            for(final EClass eClass : _eClassIterable) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(",", "\t\t\t\t\t");
              }
              _builder.append("\t\t\t\t\t");
              String _eClassId = DeclarativeSerializerFragmentXtend.this.getEClassId(eClass, true);
              _builder.append(_eClassId, "\t\t\t\t\t");
              _builder.append("  /* ");
              String _name = eClass.getEPackage().getName();
              _builder.append(_name, "\t\t\t\t\t");
              _builder.append("::");
              String _name_1 = eClass.getName();
              _builder.append(_name_1, "\t\t\t\t\t");
              _builder.append(" */");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t\t\t\t");
          _builder.append("},");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t\t\t\t ");
          _builder.append("*\tThe indexable per-grammar rule meta data.");
          _builder.newLine();
          _builder.append("\t\t\t\t ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("new ");
          String _newTypeReference_4 = DeclarativeSerializerFragmentXtend.this.newTypeReference(GrammarRuleValue.class);
          _builder.append(_newTypeReference_4, "\t\t\t\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            Iterable<GrammarRuleValue> _grammarRuleValueIterator = DeclarativeSerializerFragmentXtend.this.getGrammarRuleValueIterator(grammarAnalysis);
            boolean _hasElements_1 = false;
            for(final GrammarRuleValue grammarRuleValue : _grammarRuleValueIterator) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(",", "\t\t\t\t\t");
              }
              _builder.append("\t\t\t\t\t");
              String _grammarRuleValueId = DeclarativeSerializerFragmentXtend.this.getGrammarRuleValueId(grammarRuleValue, true);
              _builder.append(_grammarRuleValueId, "\t\t\t\t\t");
              _builder.append("  /* ");
              int _index = grammarRuleValue.getIndex();
              _builder.append(_index, "\t\t\t\t\t");
              _builder.append(" : ");
              String _string = grammarRuleValue.toString();
              _builder.append(_string, "\t\t\t\t\t");
              _builder.append(" */");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append(");");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("return analysis;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateGrammarRuleVectors = DeclarativeSerializerFragmentXtend.this.generateGrammarRuleVectors(grammarAnalysis);
          _builder.append(_generateGrammarRuleVectors, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateEnumValues = DeclarativeSerializerFragmentXtend.this.generateEnumValues(grammarAnalysis);
          _builder.append(_generateEnumValues, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateMatchTerms = DeclarativeSerializerFragmentXtend.this.generateMatchTerms(grammarAnalysis);
          _builder.append(_generateMatchTerms, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateMatchSteps = DeclarativeSerializerFragmentXtend.this.generateMatchSteps(grammarAnalysis);
          _builder.append(_generateMatchSteps, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateSerializationTerms = DeclarativeSerializerFragmentXtend.this.generateSerializationTerms(grammarAnalysis);
          _builder.append(_generateSerializationTerms, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateSerializationSegments = DeclarativeSerializerFragmentXtend.this.generateSerializationSegments(grammarAnalysis);
          _builder.append(_generateSerializationSegments, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateSerializationSegmentsLists = DeclarativeSerializerFragmentXtend.this.generateSerializationSegmentsLists(grammarAnalysis);
          _builder.append(_generateSerializationSegmentsLists, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateGrammarRuleValues = DeclarativeSerializerFragmentXtend.this.generateGrammarRuleValues(grammarAnalysis);
          _builder.append(_generateGrammarRuleValues, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t\t\t\t\t\t\t\t");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateEClassValues = DeclarativeSerializerFragmentXtend.this.generateEClassValues(grammarAnalysis);
          _builder.append(_generateEClassValues, "\t");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("\t");
          CharSequence _generateSerializationRules = DeclarativeSerializerFragmentXtend.this.generateSerializationRules(grammarAnalysis);
          _builder.append(_generateSerializationRules, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _EClassValues ec;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _EnumValues ev;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _GrammarRuleVectors iv;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _MatchSteps ms;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _MatchTerms mt;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _SerializationSegmentsLists sl;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _SerializationSegments ss;");
          _builder.newLine();
          {
            Iterable<Integer> _serializationRulePageIterable = DeclarativeSerializerFragmentXtend.this.getSerializationRulePageIterable(grammarAnalysis);
            for(final Integer page : _serializationRulePageIterable) {
              _builder.append("\t");
              _builder.append("private _SerializationRules");
              _builder.append(page, "\t");
              _builder.append(" sr");
              _builder.append(page, "\t");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("private _SerializationTerms st;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("private _GrammarRuleValues gr;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t ");
          _builder.append("* Post constructor/injection initialization to avoid recursions.");
          _builder.newLine();
          _builder.append("\t ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@");
          String _newTypeReference_5 = DeclarativeSerializerFragmentXtend.this.newTypeReference(Inject.class);
          _builder.append(_newTypeReference_5, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("public void init() {");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("iv = new _GrammarRuleVectors();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("ev = new _EnumValues();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("mt = new _MatchTerms();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("ms = new _MatchSteps();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("st = new _SerializationTerms();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("ss = new _SerializationSegments();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("sl = new _SerializationSegmentsLists();");
          _builder.newLine();
          {
            Iterable<Integer> _serializationRulePageIterable_1 = DeclarativeSerializerFragmentXtend.this.getSerializationRulePageIterable(grammarAnalysis);
            for(final Integer page_1 : _serializationRulePageIterable_1) {
              _builder.append("\t\t");
              _builder.append("sr");
              _builder.append(page_1, "\t\t");
              _builder.append(" = new _SerializationRules");
              _builder.append(page_1, "\t\t");
              _builder.append("();");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t\t");
          _builder.append("gr = new _GrammarRuleValues();");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("ec = new _EClassValues();\t\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.append("//\tCommented imports ensure Xtend provides a true import allowing unqualified annotated usage");
          _builder.newLine();
          {
            Iterable<String> _importedClassNameIterable = DeclarativeSerializerFragmentXtend.this.getImportedClassNameIterable();
            for(final String importedClassName : _importedClassNameIterable) {
              int index = importedClassName.lastIndexOf(".");
              _builder.newLineIfNotEmpty();
              {
                if ((index < 0)) {
                  _builder.append("//\timport ");
                  TypeReference _typeReference = new TypeReference(importedClassName);
                  _builder.append(_typeReference);
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("//\timport ");
                  String _substring = importedClassName.substring(0, index);
                  String _replace = importedClassName.substring((index + 1)).replace("$", ".");
                  TypeReference _typeReference_1 = new TypeReference(_substring, _replace);
                  _builder.append(_typeReference_1);
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      };
      _xblockexpression = _client;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateEClassValues(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Configuration for each EClass that may be serialized.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _EClassValues");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<EClass> _eClassIterable = this.getEClassIterable(grammarAnalysis);
      for(final EClass eClass : _eClassIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(EClassValue.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" ");
        String _eClassId = this.getEClassId(eClass, false);
        _builder.append(_eClassId, "\t");
        _builder.append(" // ");
        String _name = eClass.getName();
        _builder.append(_name, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("= ");
        CharSequence _generateEClassValue_EClass = this.generateEClassValue_EClass(grammarAnalysis, eClass);
        _builder.append(_generateEClassValue_EClass, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateEClassValue_EClass(final GrammarAnalysis grammarAnalysis, final EClass eClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(EClassValue.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _emitLiteral = this.emitLiteral(eClass);
    _builder.append(_emitLiteral);
    _builder.append(",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("new @NonNull ");
    String _newTypeReference_1 = this.newTypeReference(EClassValue.SerializationRule_SegmentsList.class);
    _builder.append(_newTypeReference_1, "\t");
    _builder.append(" [] {");
    _builder.newLineIfNotEmpty();
    {
      EClassValue.SerializationRule_SegmentsList[] _serializationRuleSegmentsLists = grammarAnalysis.getEClassValue(eClass).getSerializationRuleSegmentsLists();
      boolean _hasElements = false;
      for(final EClassValue.SerializationRule_SegmentsList serializationRuleSegmentsList : _serializationRuleSegmentsLists) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        _builder.append("\t\t");
        SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("new ");
        String _newTypeReference_2 = this.newTypeReference(EClassValue.SerializationRule_SegmentsList.class);
        _builder.append(_newTypeReference_2, "\t\t");
        _builder.append("(");
        String _serializationRuleId = this.getSerializationRuleId(serializationRule, true);
        _builder.append(_serializationRuleId, "\t\t");
        _builder.append(", ");
        String _serializationSegmentsListId = this.getSerializationSegmentsListId(this.getSerializationSegmentsListString(serializationRule.getStaticSegments()), true);
        _builder.append(_serializationSegmentsListId, "\t\t");
        _builder.append(") /* ");
        String _ruleString = serializationRule.toRuleString();
        _builder.append(_ruleString, "\t\t");
        _builder.append(" */");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}, ");
    {
      SerializationRule.EReference_RuleIndexes[] _basicGetEReferenceRuleIndexes = grammarAnalysis.basicGetEReferenceRuleIndexes(eClass);
      boolean _tripleEquals = (_basicGetEReferenceRuleIndexes == null);
      if (_tripleEquals) {
        _builder.append("null");
      } else {
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("new @NonNull ");
        String _newTypeReference_3 = this.newTypeReference(SerializationRule.EReference_RuleIndexes.class);
        _builder.append(_newTypeReference_3, "\t");
        _builder.append(" [] {");
        _builder.newLineIfNotEmpty();
        {
          List<SerializationRule.EReference_RuleIndexes> _eReferenceRuleIndexesIterable = this.getEReferenceRuleIndexesIterable(grammarAnalysis, eClass);
          boolean _hasElements_1 = false;
          for(final SerializationRule.EReference_RuleIndexes eReferenceRuleIndex : _eReferenceRuleIndexesIterable) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "\t\t");
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("new ");
            String _newTypeReference_4 = this.newTypeReference(SerializationRule.EReference_RuleIndexes.class);
            _builder.append(_newTypeReference_4, "\t\t");
            _builder.append("(");
            String _emitLiteral_1 = this.emitLiteral(eReferenceRuleIndex.getEReference());
            _builder.append(_emitLiteral_1, "\t\t");
            _builder.append(",");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\t");
            String _grammarRuleVectorId = this.getGrammarRuleVectorId(eReferenceRuleIndex.getAssignedTargetRuleValueIndexes(), true);
            _builder.append(_grammarRuleVectorId, "\t\t\t");
            _builder.append(") /* ");
            {
              GrammarRuleVector _assignedTargetRuleValueIndexes = eReferenceRuleIndex.getAssignedTargetRuleValueIndexes();
              boolean _hasElements_2 = false;
              for(final Integer ruleValueIndex : _assignedTargetRuleValueIndexes) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate("|", "\t\t\t");
                }
                String _string = grammarAnalysis.getRuleValue((ruleValueIndex).intValue()).toString();
                _builder.append(_string, "\t\t\t");
              }
            }
            _builder.append(" */");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateEnumValues(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* String combinations used by assigned String EAttributes");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _EnumValues");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<EnumerationValue> _enumValueIterable = this.getEnumValueIterable(grammarAnalysis);
      for(final EnumerationValue enumValue : _enumValueIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(EnumerationValue.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" ");
        String _enumValueId = this.getEnumValueId(enumValue, false);
        _builder.append(_enumValueId, "\t");
        _builder.append(" // ");
        String _string = enumValue.toString();
        _builder.append(_string, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("= ");
        CharSequence _generateEnumValue = this.generateEnumValue(enumValue);
        _builder.append(_generateEnumValue, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateEnumValue(final EnumerationValue enumValue) {
    boolean _matched = false;
    if (enumValue instanceof EnumerationValue.EnumerationValueMultiple) {
      _matched=true;
      return this.generateEnumValue_EnumerationValueMultiple(((EnumerationValue.EnumerationValueMultiple)enumValue));
    }
    if (!_matched) {
      if (enumValue instanceof EnumerationValue.EnumerationValueOthers) {
        _matched=true;
        return this.generateEnumValue_EnumerationValueOthers(((EnumerationValue.EnumerationValueOthers)enumValue));
      }
    }
    if (!_matched) {
      if (enumValue instanceof EnumerationValue.EnumerationValueSingle) {
        _matched=true;
        return this.generateEnumValue_EnumerationValueSingle(((EnumerationValue.EnumerationValueSingle)enumValue));
      }
    }
    throw new UnsupportedOperationException();
  }
  
  protected CharSequence generateEnumValue_EnumerationValueMultiple(final EnumerationValue.EnumerationValueMultiple enumValue) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(EnumerationValue.EnumerationValueMultiple.class);
    _builder.append(_newTypeReference);
    _builder.append("(new @NonNull String[]{");
    {
      String[] _values = enumValue.getValues();
      boolean _hasElements = false;
      for(final String value : _values) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        _builder.append("\"");
        _builder.append(value);
        _builder.append("\"");
      }
    }
    _builder.append("})");
    return _builder;
  }
  
  protected CharSequence generateEnumValue_EnumerationValueOthers(final EnumerationValue.EnumerationValueOthers enumValue) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(EnumerationValue.EnumerationValueOthers.class);
    _builder.append(_newTypeReference);
    _builder.append("()");
    return _builder;
  }
  
  protected CharSequence generateEnumValue_EnumerationValueSingle(final EnumerationValue.EnumerationValueSingle enumValue) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(EnumerationValue.EnumerationValueSingle.class);
    _builder.append(_newTypeReference);
    _builder.append("(\"");
    String _name = enumValue.getName();
    _builder.append(_name);
    _builder.append("\")");
    return _builder;
  }
  
  protected CharSequence generateGrammarRuleValues(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The various serialization rules for each grammar rule.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _GrammarRuleValues");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<GrammarRuleValue> _grammarRuleValueIterator = this.getGrammarRuleValueIterator(grammarAnalysis);
      for(final GrammarRuleValue grammarRuleValue : _grammarRuleValueIterator) {
        _builder.append("\t");
        CharSequence _generateGrammarRuleValue = this.generateGrammarRuleValue(grammarAnalysis, grammarRuleValue);
        _builder.append(_generateGrammarRuleValue, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateGrammarRuleValue(final GrammarAnalysis grammarAnalysis, final GrammarRuleValue grammarRuleValue) {
    boolean _matched = false;
    if (grammarRuleValue instanceof DataTypeRuleValue) {
      _matched=true;
      return this.generateGrammarRuleValue_DataTypeRule(grammarAnalysis, ((DataTypeRuleValue)grammarRuleValue));
    }
    if (!_matched) {
      if (grammarRuleValue instanceof ParserRuleValue) {
        _matched=true;
        return this.generateGrammarRuleValue_ParserRuleValue(grammarAnalysis, ((ParserRuleValue)grammarRuleValue));
      }
    }
    if (!_matched) {
      if (grammarRuleValue instanceof TerminalRuleValue) {
        _matched=true;
        return this.generateGrammarRuleValue_TerminalRuleValue(grammarAnalysis, ((TerminalRuleValue)grammarRuleValue));
      }
    }
    throw new UnsupportedOperationException();
  }
  
  protected CharSequence generateGrammarRuleValue_DataTypeRule(final GrammarAnalysis grammarAnalysis, final DataTypeRuleValue dataTypeRuleValue) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(DataTypeRuleValue.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _grammarRuleValueId = this.getGrammarRuleValueId(dataTypeRuleValue, false);
    _builder.append(_grammarRuleValueId);
    _builder.append(" // ");
    String _name = dataTypeRuleValue.getName();
    _builder.append(_name);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(DataTypeRuleValue.class);
    _builder.append(_newTypeReference_1, "\t");
    _builder.append("(");
    int _index = dataTypeRuleValue.getIndex();
    _builder.append(_index, "\t");
    _builder.append(", \"");
    String _name_1 = dataTypeRuleValue.getName();
    _builder.append(_name_1, "\t");
    _builder.append("\");");
    return _builder;
  }
  
  protected CharSequence generateGrammarRuleValue_ParserRuleValue(final GrammarAnalysis grammarAnalysis, final ParserRuleValue parserRuleValue) {
    CharSequence _xblockexpression = null;
    {
      GrammarRuleVector subParserRuleValueIndexes = parserRuleValue.getSubParserRuleValueIndexes();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("private final @NonNull ");
      String _newTypeReference = this.newTypeReference(ParserRuleValue.class);
      _builder.append(_newTypeReference);
      _builder.append(" ");
      String _grammarRuleValueId = this.getGrammarRuleValueId(parserRuleValue, false);
      _builder.append(_grammarRuleValueId);
      _builder.append(" // ");
      String _name = parserRuleValue.getName();
      _builder.append(_name);
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("= new ");
      String _newTypeReference_1 = this.newTypeReference(ParserRuleValue.class);
      _builder.append(_newTypeReference_1, "\t");
      _builder.append("(");
      int _index = parserRuleValue.getIndex();
      _builder.append(_index, "\t");
      _builder.append(", \"");
      String _name_1 = parserRuleValue.getName();
      _builder.append(_name_1, "\t");
      _builder.append("\",");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("new @NonNull ");
      String _newTypeReference_2 = this.newTypeReference(SerializationRule.class);
      _builder.append(_newTypeReference_2, "\t\t");
      _builder.append(" [] {");
      _builder.newLineIfNotEmpty();
      {
        Iterable<SerializationRule> _serializationRulesIterable = this.getSerializationRulesIterable(grammarAnalysis, parserRuleValue);
        boolean _hasElements = false;
        for(final SerializationRule serializationRule : _serializationRulesIterable) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "\t\t\t");
          }
          _builder.append("\t\t\t");
          String _serializationRuleId = this.getSerializationRuleId(serializationRule, true);
          _builder.append(_serializationRuleId, "\t\t\t");
          _builder.append(" /* ");
          String _ruleString = serializationRule.toRuleString();
          _builder.append(_ruleString, "\t\t\t");
          _builder.append(" */");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      _builder.append("}, ");
      _builder.newLine();
      _builder.append("\t\t");
      {
        if ((subParserRuleValueIndexes != null)) {
          String _grammarRuleVectorId = this.getGrammarRuleVectorId(subParserRuleValueIndexes, true);
          _builder.append(_grammarRuleVectorId, "\t\t");
          _builder.append("); /* ");
          {
            boolean _hasElements_1 = false;
            for(final Integer index : subParserRuleValueIndexes) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate("|", "\t\t");
              }
              String _grammarRuleName = this.getGrammarRuleName(index);
              _builder.append(_grammarRuleName, "\t\t");
            }
          }
          _builder.append(" */");
        } else {
          _builder.append("null);");
        }
      }
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateGrammarRuleValue_TerminalRuleValue(final GrammarAnalysis grammarAnalysis, final TerminalRuleValue terminalRuleValue) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(TerminalRuleValue.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _grammarRuleValueId = this.getGrammarRuleValueId(terminalRuleValue, false);
    _builder.append(_grammarRuleValueId);
    _builder.append(" // ");
    String _name = terminalRuleValue.getName();
    _builder.append(_name);
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(TerminalRuleValue.class);
    _builder.append(_newTypeReference_1, "\t");
    _builder.append("(");
    int _index = terminalRuleValue.getIndex();
    _builder.append(_index, "\t");
    _builder.append(", \"");
    String _name_1 = terminalRuleValue.getName();
    _builder.append(_name_1, "\t");
    _builder.append("\");");
    return _builder;
  }
  
  protected CharSequence generateGrammarRuleVectors(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Bit vectors of useful grammar rule combinations");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _GrammarRuleVectors");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<GrammarRuleVector> _grammarRuleVectorIterable = this.getGrammarRuleVectorIterable(grammarAnalysis);
      for(final GrammarRuleVector grammarRuleVector : _grammarRuleVectorIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(GrammarRuleVector.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" ");
        String _grammarRuleVectorId = this.getGrammarRuleVectorId(grammarRuleVector, false);
        _builder.append(_grammarRuleVectorId, "\t");
        _builder.append(" // ");
        {
          boolean _hasElements = false;
          for(final Integer index : grammarRuleVector) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("|", "\t");
            }
            String _ruleName = grammarAnalysis.getRuleName((index).intValue());
            _builder.append(_ruleName, "\t");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("= new ");
        String _newTypeReference_1 = this.newTypeReference(GrammarRuleVector.class);
        _builder.append(_newTypeReference_1, "\t\t");
        _builder.append("(");
        String _wordsString = grammarRuleVector.toWordsString();
        _builder.append(_wordsString, "\t\t");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateMatchSteps(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Steps for the matching process.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _MatchSteps");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<SerializationMatchStep> _matchStepIterable = this.getMatchStepIterable(grammarAnalysis);
      for(final SerializationMatchStep matchStep : _matchStepIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(SerializationMatchStep.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" ");
        String _matchStepId = this.getMatchStepId(matchStep, false);
        _builder.append(_matchStepId, "\t");
        _builder.append(" // ");
        String _string = matchStep.toString();
        _builder.append(_string, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("= ");
        CharSequence _generateMatchStep = this.generateMatchStep(matchStep);
        _builder.append(_generateMatchStep, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateMatchStep(final SerializationMatchStep matchStep) {
    boolean _matched = false;
    if (matchStep instanceof SerializationMatchStep.MatchStep_Assert) {
      _matched=true;
      return this.generateMatchStep_Assert(((SerializationMatchStep.MatchStep_Assert)matchStep));
    }
    if (!_matched) {
      if (matchStep instanceof SerializationMatchStep.MatchStep_Assign) {
        _matched=true;
        return this.generateMatchStep_Assign(((SerializationMatchStep.MatchStep_Assign)matchStep));
      }
    }
    if (!_matched) {
      if (matchStep instanceof SerializationMatchStep.MatchStep_RuleCheck) {
        _matched=true;
        return this.generateMatchStep_RuleCheck(((SerializationMatchStep.MatchStep_RuleCheck)matchStep));
      }
    }
    if (!_matched) {
      if (matchStep instanceof SerializationMatchStep.MatchStep_ValueCheck) {
        _matched=true;
        return this.generateMatchStep_ValueCheck(((SerializationMatchStep.MatchStep_ValueCheck)matchStep));
      }
    }
    throw new UnsupportedOperationException();
  }
  
  protected CharSequence generateMatchStep_Assert(final SerializationMatchStep.MatchStep_Assert matchStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchStep.MatchStep_Assert.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchStep.getCardinalitySolution(), true);
    _builder.append(_matchTermId);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchStep_Assign(final SerializationMatchStep.MatchStep_Assign matchStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchStep.MatchStep_Assign.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    int _variableIndex = matchStep.getVariableIndex();
    _builder.append(_variableIndex);
    _builder.append(", ");
    String _matchTermId = this.getMatchTermId(matchStep.getCardinalitySolution(), true);
    _builder.append(_matchTermId);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchStep_RuleCheck(final SerializationMatchStep.MatchStep_RuleCheck matchStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchStep.MatchStep_RuleCheck.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _emitLiteral = this.emitLiteral(matchStep.getEReference());
    _builder.append(_emitLiteral);
    _builder.append(", ");
    String _grammarRuleVectorId = this.getGrammarRuleVectorId(matchStep.getRuleValueIndexes(), true);
    _builder.append(_grammarRuleVectorId);
    _builder.append("/*");
    {
      GrammarRuleVector _ruleValueIndexes = matchStep.getRuleValueIndexes();
      boolean _hasElements = false;
      for(final Integer index : _ruleValueIndexes) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("|", "");
        }
        String _ruleName = this.getGrammarAnalysis().getRuleName((index).intValue());
        _builder.append(_ruleName);
      }
    }
    _builder.append("*/)");
    return _builder;
  }
  
  protected CharSequence generateMatchStep_ValueCheck(final SerializationMatchStep.MatchStep_ValueCheck matchStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchStep.MatchStep_ValueCheck.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    int _variableIndex = matchStep.getVariableIndex();
    _builder.append(_variableIndex);
    _builder.append(", ");
    String _matchTermId = this.getMatchTermId(matchStep.getCardinalitySolution(), true);
    _builder.append(_matchTermId);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerms(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Expression terms used during the matching process.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _MatchTerms");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<SerializationMatchTerm> _matchTermIterable = this.getMatchTermIterable(grammarAnalysis);
      for(final SerializationMatchTerm matchTerm : _matchTermIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(SerializationMatchTerm.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" ");
        String _matchTermId = this.getMatchTermId(matchTerm, false);
        _builder.append(_matchTermId, "\t");
        _builder.append(" // ");
        String _string = matchTerm.toString();
        _builder.append(_string, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("= ");
        CharSequence _generateMatchTerm = this.generateMatchTerm(matchTerm);
        _builder.append(_generateMatchTerm, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateMatchTerm(final SerializationMatchTerm matchTerm) {
    boolean _matched = false;
    if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermAdd) {
      _matched=true;
      return this.generateMatchTerm_Add(((SerializationMatchTerm.SerializationMatchTermAdd)matchTerm));
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermDivide) {
        _matched=true;
        return this.generateMatchTerm_Divide(((SerializationMatchTerm.SerializationMatchTermDivide)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermEAttributeSize) {
        _matched=true;
        return this.generateMatchTerm_EAttributeSize(((SerializationMatchTerm.SerializationMatchTermEAttributeSize)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermEReferenceSize) {
        _matched=true;
        return this.generateMatchTerm_EReferenceSize(((SerializationMatchTerm.SerializationMatchTermEReferenceSize)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize) {
        _matched=true;
        return this.generateMatchTerm_EStructuralFeatureSize(((SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermGreaterThan) {
        _matched=true;
        return this.generateMatchTerm_GreaterThan(((SerializationMatchTerm.SerializationMatchTermGreaterThan)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermInteger) {
        _matched=true;
        return this.generateMatchTerm_IntegerSolution(((SerializationMatchTerm.SerializationMatchTermInteger)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermMultiply) {
        _matched=true;
        return this.generateMatchTerm_Multiply(((SerializationMatchTerm.SerializationMatchTermMultiply)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermSubtract) {
        _matched=true;
        return this.generateMatchTerm_Subtract(((SerializationMatchTerm.SerializationMatchTermSubtract)matchTerm));
      }
    }
    if (!_matched) {
      if (matchTerm instanceof SerializationMatchTerm.SerializationMatchTermVariable) {
        _matched=true;
        return this.generateMatchTerm_Variable(((SerializationMatchTerm.SerializationMatchTermVariable)matchTerm));
      }
    }
    throw new UnsupportedOperationException();
  }
  
  protected CharSequence generateMatchTerm_Add(final SerializationMatchTerm.SerializationMatchTermAdd matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermAdd.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchTerm.getLeft(), false);
    _builder.append(_matchTermId);
    _builder.append(", ");
    String _matchTermId_1 = this.getMatchTermId(matchTerm.getRight(), false);
    _builder.append(_matchTermId_1);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_Divide(final SerializationMatchTerm.SerializationMatchTermDivide matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermDivide.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchTerm.getLeft(), false);
    _builder.append(_matchTermId);
    _builder.append(", ");
    String _matchTermId_1 = this.getMatchTermId(matchTerm.getRight(), false);
    _builder.append(_matchTermId_1);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_EAttributeSize(final SerializationMatchTerm.SerializationMatchTermEAttributeSize matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermEAttributeSize.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _emitLiteral = this.emitLiteral(matchTerm.getEAttribute());
    _builder.append(_emitLiteral);
    _builder.append(", ");
    String _enumValueId = this.getEnumValueId(matchTerm.getEnumerationValue(), true);
    _builder.append(_enumValueId);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_EReferenceSize(final SerializationMatchTerm.SerializationMatchTermEReferenceSize matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermEReferenceSize.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _emitLiteral = this.emitLiteral(matchTerm.getEReference());
    _builder.append(_emitLiteral);
    _builder.append(", \"");
    String _name = matchTerm.getParserRuleValue().getName();
    _builder.append(_name);
    _builder.append("\")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_EStructuralFeatureSize(final SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _emitLiteral = this.emitLiteral(matchTerm.getEStructuralFeature());
    _builder.append(_emitLiteral);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_GreaterThan(final SerializationMatchTerm.SerializationMatchTermGreaterThan matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermGreaterThan.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchTerm.getLeft(), false);
    _builder.append(_matchTermId);
    _builder.append(", ");
    String _matchTermId_1 = this.getMatchTermId(matchTerm.getRight(), false);
    _builder.append(_matchTermId_1);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_IntegerSolution(final SerializationMatchTerm.SerializationMatchTermInteger matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermInteger.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    int _value = matchTerm.getValue();
    _builder.append(_value);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_Multiply(final SerializationMatchTerm.SerializationMatchTermMultiply matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermMultiply.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchTerm.getLeft(), false);
    _builder.append(_matchTermId);
    _builder.append(", ");
    String _matchTermId_1 = this.getMatchTermId(matchTerm.getRight(), false);
    _builder.append(_matchTermId_1);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_Subtract(final SerializationMatchTerm.SerializationMatchTermSubtract matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermSubtract.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _matchTermId = this.getMatchTermId(matchTerm.getLeft(), false);
    _builder.append(_matchTermId);
    _builder.append(", ");
    String _matchTermId_1 = this.getMatchTermId(matchTerm.getRight(), false);
    _builder.append(_matchTermId_1);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateMatchTerm_Variable(final SerializationMatchTerm.SerializationMatchTermVariable matchTerm) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationMatchTerm.SerializationMatchTermVariable.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    int _variableIndex = matchTerm.getVariableIndex();
    _builder.append(_variableIndex);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateSerializationRules(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The various serialization rules that serialize an EClass.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    {
      Iterable<Integer> _serializationRulePageIterable = this.getSerializationRulePageIterable(grammarAnalysis);
      for(final Integer page : _serializationRulePageIterable) {
        _builder.append("private class _SerializationRules");
        _builder.append(page);
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        {
          Iterable<SerializationRuleAnalysis> _serializationRuleAnalysisIterable = this.getSerializationRuleAnalysisIterable(grammarAnalysis, (page).intValue());
          for(final SerializationRuleAnalysis serializationRuleAnalysis : _serializationRuleAnalysisIterable) {
            _builder.append("\t");
            CharSequence _generateSerializationRule = this.generateSerializationRule(serializationRuleAnalysis);
            _builder.append(_generateSerializationRule, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  protected CharSequence generateSerializationRule(final SerializationRuleAnalysis serializationRuleAnalysis) {
    CharSequence _xblockexpression = null;
    {
      SerializationRule serializationRule = serializationRuleAnalysis.getRuntime();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("// ");
      String _name = serializationRuleAnalysis.getName();
      _builder.append(_name);
      _builder.append(" : ");
      String _ruleString = serializationRule.toRuleString();
      _builder.append(_ruleString);
      _builder.newLineIfNotEmpty();
      _builder.append("private @NonNull ");
      String _newTypeReference = this.newTypeReference(SerializationRule.class);
      _builder.append(_newTypeReference);
      _builder.append(" ");
      String _serializationRuleId = this.getSerializationRuleId(serializationRuleAnalysis.getRuntime(), false);
      _builder.append(_serializationRuleId);
      _builder.append(" = new ");
      String _newTypeReference_1 = this.newTypeReference(SerializationRule.class);
      _builder.append(_newTypeReference_1);
      _builder.append("(");
      int _ruleValueIndex = serializationRuleAnalysis.getRuleValueIndex();
      _builder.append(_ruleValueIndex);
      _builder.append(",");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("new @NonNull ");
      String _newTypeReference_2 = this.newTypeReference(SerializationMatchStep.class);
      _builder.append(_newTypeReference_2, "\t");
      _builder.append(" @NonNull [] {");
      _builder.newLineIfNotEmpty();
      {
        List<SerializationMatchStep> _steps = serializationRuleAnalysis.getStaticRuleMatch().getSteps();
        boolean _hasElements = false;
        for(final SerializationMatchStep solutionStep : _steps) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          _builder.append("\t\t");
          String _matchStepId = this.getMatchStepId(solutionStep, true);
          _builder.append(_matchStepId, "\t\t");
          _builder.append(" /* ");
          String _string = solutionStep.toString();
          _builder.append(_string, "\t\t");
          _builder.append(" */");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("}, ");
      _builder.newLine();
      _builder.append("\t");
      SerializationStep[] serializationSteps = serializationRule.getSerializationSteps();
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      SerializationSegment[][] segmentsList = serializationRule.getStaticSegments();
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("new @NonNull ");
      String _newTypeReference_3 = this.newTypeReference(SerializationStep.class);
      _builder.append(_newTypeReference_3, "\t");
      _builder.append(" @NonNull [] {");
      _builder.newLineIfNotEmpty();
      {
        Integer[] _integersIterable = this.integersIterable(serializationSteps.length);
        boolean _hasElements_1 = false;
        for(final Integer i : _integersIterable) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate(",", "\t\t");
          }
          _builder.append("\t\t");
          _builder.append("\t");
          SerializationStep serializationStep = serializationSteps[(i).intValue()];
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t");
          SerializationSegment[] segments = segmentsList[(i).intValue()];
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _serializationStepId = this.getSerializationStepId(serializationStep, true);
          _builder.append(_serializationStepId, "\t\t");
          _builder.append(" /* ");
          String _string_1 = serializationStep.toString();
          _builder.append(_string_1, "\t\t");
          _builder.append(" || ");
          {
            if ((segments != null)) {
              {
                boolean _hasElements_2 = false;
                for(final SerializationSegment segment : segments) {
                  if (!_hasElements_2) {
                    _hasElements_2 = true;
                  } else {
                    _builder.appendImmediate(" ", "\t\t");
                  }
                  String _string_2 = segment.toString();
                  _builder.append(_string_2, "\t\t");
                }
              }
            } else {
              _builder.append("«null»", "\t\t");
            }
          }
          _builder.append(" */");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("}, ");
      _builder.newLine();
      _builder.append("\t");
      String segmentsListString = this.getSerializationSegmentsListString(segmentsList);
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      {
        if ((segmentsList != null)) {
          String _serializationSegmentsListId = this.getSerializationSegmentsListId(segmentsListString, true);
          _builder.append(_serializationSegmentsListId, "\t");
        } else {
          _builder.append("null");
        }
      }
      _builder.append(",");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      SerializationRule.EAttribute_EnumerationValues[] eAttribute2EnumerationValues = serializationRuleAnalysis.basicGetEAttribute2EnumerationValues();
      _builder.newLineIfNotEmpty();
      {
        if ((eAttribute2EnumerationValues != null)) {
          _builder.append("\t");
          _builder.append("new @NonNull ");
          String _newTypeReference_4 = this.newTypeReference(SerializationRule.EAttribute_EnumerationValues.class);
          _builder.append(_newTypeReference_4, "\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            boolean _hasElements_3 = false;
            for(final SerializationRule.EAttribute_EnumerationValues eAttributeData : eAttribute2EnumerationValues) {
              if (!_hasElements_3) {
                _hasElements_3 = true;
              } else {
                _builder.appendImmediate(",", "\t\t");
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new ");
              String _newTypeReference_5 = this.newTypeReference(SerializationRule.EAttribute_EnumerationValues.class);
              _builder.append(_newTypeReference_5, "\t\t");
              _builder.append("(");
              String _emitLiteral = this.emitLiteral(eAttributeData.getEAttribute());
              _builder.append(_emitLiteral, "\t\t");
              _builder.append(",");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              {
                EnumerationValue[] _enumerationValues = eAttributeData.getEnumerationValues();
                boolean _hasElements_4 = false;
                for(final EnumerationValue enumerationValue : _enumerationValues) {
                  if (!_hasElements_4) {
                    _hasElements_4 = true;
                  } else {
                    _builder.appendImmediate(",", "\t\t\t");
                  }
                  String _enumValueId = this.getEnumValueId(enumerationValue, true);
                  _builder.append(_enumValueId, "\t\t\t");
                }
              }
              _builder.append(")");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("},");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("null,");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      SerializationRule.EReference_RuleIndexes[] eReference2AssignedRuleValueIndexes = serializationRuleAnalysis.basicGetEReference2AssignedRuleValueIndexes();
      _builder.newLineIfNotEmpty();
      {
        if ((eReference2AssignedRuleValueIndexes != null)) {
          _builder.append("\t");
          _builder.append("new @NonNull ");
          String _newTypeReference_6 = this.newTypeReference(SerializationRule.EReference_RuleIndexes.class);
          _builder.append(_newTypeReference_6, "\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            boolean _hasElements_5 = false;
            for(final SerializationRule.EReference_RuleIndexes eReferenceData : eReference2AssignedRuleValueIndexes) {
              if (!_hasElements_5) {
                _hasElements_5 = true;
              } else {
                _builder.appendImmediate(",", "\t\t");
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new ");
              String _newTypeReference_7 = this.newTypeReference(SerializationRule.EReference_RuleIndexes.class);
              _builder.append(_newTypeReference_7, "\t\t");
              _builder.append("(");
              String _emitLiteral_1 = this.emitLiteral(eReferenceData.getEReference());
              _builder.append(_emitLiteral_1, "\t\t");
              _builder.append(",");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              String _grammarRuleVectorId = this.getGrammarRuleVectorId(eReferenceData.getAssignedTargetRuleValueIndexes(), true);
              _builder.append(_grammarRuleVectorId, "\t\t\t");
              _builder.append(") /* ");
              {
                GrammarRuleVector _assignedTargetRuleValueIndexes = eReferenceData.getAssignedTargetRuleValueIndexes();
                boolean _hasElements_6 = false;
                for(final Integer ruleValueIndex : _assignedTargetRuleValueIndexes) {
                  if (!_hasElements_6) {
                    _hasElements_6 = true;
                  } else {
                    _builder.appendImmediate("|", "\t\t\t");
                  }
                  String _string_3 = this.getGrammarAnalysis().getRuleValue((ruleValueIndex).intValue()).toString();
                  _builder.append(_string_3, "\t\t\t");
                }
              }
              _builder.append(" */");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("},");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("null,");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      EAttribute[] needsDefaultEAttributes = serializationRuleAnalysis.basicGetNeedsDefaultEAttributes();
      _builder.newLineIfNotEmpty();
      {
        if ((needsDefaultEAttributes != null)) {
          _builder.append("\t");
          _builder.append("new /*@NonNull*/ ");
          String _newTypeReference_8 = this.newTypeReference(EAttribute.class);
          _builder.append(_newTypeReference_8, "\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            boolean _hasElements_7 = false;
            for(final EAttribute eAttribute : needsDefaultEAttributes) {
              if (!_hasElements_7) {
                _hasElements_7 = true;
              } else {
                _builder.appendImmediate(",", "\t\t");
              }
              _builder.append("\t");
              _builder.append("\t");
              String _emitLiteral_2 = this.emitLiteral(eAttribute);
              _builder.append(_emitLiteral_2, "\t\t");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("},");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("null,");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      SerializationRule.EAttribute_EnumerationValue_GrammarCardinality[] eAttribute2enumerationValue2grammarCardinalityArray = serializationRuleAnalysis.basicGetEAttribute2enumerationValue2grammarCardinality();
      _builder.newLineIfNotEmpty();
      {
        if ((eAttribute2enumerationValue2grammarCardinalityArray != null)) {
          _builder.append("\t");
          _builder.append("new @NonNull ");
          String _newTypeReference_9 = this.newTypeReference(SerializationRule.EAttribute_EnumerationValue_GrammarCardinality.class);
          _builder.append(_newTypeReference_9, "\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            boolean _hasElements_8 = false;
            for(final SerializationRule.EAttribute_EnumerationValue_GrammarCardinality eAttribute2enumerationValue2grammarCardinality : eAttribute2enumerationValue2grammarCardinalityArray) {
              if (!_hasElements_8) {
                _hasElements_8 = true;
              } else {
                _builder.appendImmediate(",", "\t\t");
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new ");
              String _newTypeReference_10 = this.newTypeReference(SerializationRule.EAttribute_EnumerationValue_GrammarCardinality.class);
              _builder.append(_newTypeReference_10, "\t\t");
              _builder.append("(");
              String _emitLiteral_3 = this.emitLiteral(eAttribute2enumerationValue2grammarCardinality.getEAttribute());
              _builder.append(_emitLiteral_3, "\t\t");
              _builder.append(",");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new @NonNull ");
              String _newTypeReference_11 = this.newTypeReference(SerializationRule.EnumerationValue_GrammarCardinality.class);
              _builder.append(_newTypeReference_11, "\t\t\t");
              _builder.append(" [] {");
              _builder.newLineIfNotEmpty();
              {
                SerializationRule.EnumerationValue_GrammarCardinality[] _enumerationValue_GrammarCardinality = eAttribute2enumerationValue2grammarCardinality.getEnumerationValue_GrammarCardinality();
                boolean _hasElements_9 = false;
                for(final SerializationRule.EnumerationValue_GrammarCardinality enumerationValue2grammarCardinality : _enumerationValue_GrammarCardinality) {
                  if (!_hasElements_9) {
                    _hasElements_9 = true;
                  } else {
                    _builder.appendImmediate(",", "\t\t\t");
                  }
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("\t");
                  EnumerationValue enumerationValue_1 = enumerationValue2grammarCardinality.getEnumerationValue();
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("new ");
                  String _newTypeReference_12 = this.newTypeReference(SerializationRule.EnumerationValue_GrammarCardinality.class);
                  _builder.append(_newTypeReference_12, "\t\t\t\t");
                  _builder.append("(");
                  String _xifexpression = null;
                  if ((enumerationValue_1 != null)) {
                    _xifexpression = this.getEnumValueId(enumerationValue_1, true);
                  } else {
                    _xifexpression = "null";
                  }
                  _builder.append(_xifexpression, "\t\t\t\t");
                  _builder.append(", ");
                  String _emitGrammarCardinality = this.emitGrammarCardinality(enumerationValue2grammarCardinality.getGrammarCardinality());
                  _builder.append(_emitGrammarCardinality, "\t\t\t\t");
                  _builder.append(")");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append(")");
              _builder.newLine();
            }
          }
          _builder.append("\t");
          _builder.append("},");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("null,");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      SerializationRule.EReference_RuleIndex_GrammarCardinality[] eReference2ruleValueIndex2grammarCardinalityArray = serializationRuleAnalysis.basicGetEReference2ruleValueIndex2grammarCardinality();
      _builder.newLineIfNotEmpty();
      {
        if ((eReference2ruleValueIndex2grammarCardinalityArray != null)) {
          _builder.append("\t");
          _builder.append("new @NonNull ");
          String _newTypeReference_13 = this.newTypeReference(SerializationRule.EReference_RuleIndex_GrammarCardinality.class);
          _builder.append(_newTypeReference_13, "\t");
          _builder.append(" [] {");
          _builder.newLineIfNotEmpty();
          {
            boolean _hasElements_10 = false;
            for(final SerializationRule.EReference_RuleIndex_GrammarCardinality eReference2ruleValueIndex2grammarCardinality : eReference2ruleValueIndex2grammarCardinalityArray) {
              if (!_hasElements_10) {
                _hasElements_10 = true;
              } else {
                _builder.appendImmediate(",", "\t\t");
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new ");
              String _newTypeReference_14 = this.newTypeReference(SerializationRule.EReference_RuleIndex_GrammarCardinality.class);
              _builder.append(_newTypeReference_14, "\t\t");
              _builder.append("(");
              String _emitLiteral_4 = this.emitLiteral(eReference2ruleValueIndex2grammarCardinality.getEReference());
              _builder.append(_emitLiteral_4, "\t\t");
              _builder.append(",");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("new @NonNull ");
              String _newTypeReference_15 = this.newTypeReference(SerializationRule.RuleIndex_GrammarCardinality.class);
              _builder.append(_newTypeReference_15, "\t\t\t");
              _builder.append(" [] {");
              _builder.newLineIfNotEmpty();
              {
                SerializationRule.RuleIndex_GrammarCardinality[] _ruleIndex_GrammarCardinality = eReference2ruleValueIndex2grammarCardinality.getRuleIndex_GrammarCardinality();
                boolean _hasElements_11 = false;
                for(final SerializationRule.RuleIndex_GrammarCardinality ruleValueIndex2grammarCardinality : _ruleIndex_GrammarCardinality) {
                  if (!_hasElements_11) {
                    _hasElements_11 = true;
                  } else {
                    _builder.appendImmediate(",", "\t\t\t");
                  }
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("new ");
                  String _newTypeReference_16 = this.newTypeReference(SerializationRule.RuleIndex_GrammarCardinality.class);
                  _builder.append(_newTypeReference_16, "\t\t\t");
                  _builder.append("(");
                  Integer _ruleIndex = ruleValueIndex2grammarCardinality.getRuleIndex();
                  _builder.append(_ruleIndex, "\t\t\t");
                  _builder.append(", ");
                  String _emitGrammarCardinality_1 = this.emitGrammarCardinality(ruleValueIndex2grammarCardinality.getGrammarCardinality());
                  _builder.append(_emitGrammarCardinality_1, "\t\t\t");
                  _builder.append(")");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append(")");
              _builder.newLine();
            }
          }
          _builder.append("\t");
          _builder.append("});");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("null);");
          _builder.newLine();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateSerializationSegments(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The various string segment sequences that may be used to serialize a serialization term.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _SerializationSegments");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<List<SerializationSegment>> _serializationSegmentsIterable = this.getSerializationSegmentsIterable(grammarAnalysis);
      for(final List<SerializationSegment> segments : _serializationSegmentsIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(SerializationSegment.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" [] ");
        String _serializationSegmentsId = this.getSerializationSegmentsId(segments, false);
        _builder.append(_serializationSegmentsId, "\t");
        _builder.append(" = new @NonNull ");
        String _newTypeReference_1 = this.newTypeReference(SerializationSegment.class);
        _builder.append(_newTypeReference_1, "\t");
        _builder.append(" @NonNull [] {");
        _builder.newLineIfNotEmpty();
        {
          boolean _hasElements = false;
          for(final SerializationSegment segment : segments) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "\t\t");
            }
            _builder.append("\t");
            _builder.append("\t");
            CharSequence _generateSerializationSegment = this.generateSerializationSegment(segment);
            _builder.append(_generateSerializationSegment, "\t\t");
            _builder.append(" /* ");
            String _string = segment.toString();
            _builder.append(_string, "\t\t");
            _builder.append(" */");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("};");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment(final SerializationSegment segment) {
    String _switchResult = null;
    boolean _matched = false;
    if (segment instanceof SerializationSegment.CustomSerializationSegment) {
      _matched=true;
      return this.generateSerializationSegment_Custom(((SerializationSegment.CustomSerializationSegment)segment));
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.HALF_NEW_LINE);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_HalfNewLine(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.NO_SPACE);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_NoSpace(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.POP);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_Pop(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.PUSH);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_Push(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.SOFT_NEW_LINE);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_SoftNewLine(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.StringSerializationSegment) {
        boolean _equals = ((SerializationSegment.StringSerializationSegment)segment).getString().equals(SerializationBuilder.SOFT_SPACE);
        if (_equals) {
          _matched=true;
          return this.generateSerializationSegment_SoftSpace(((SerializationSegment.StringSerializationSegment)segment));
        }
      }
    }
    if (!_matched) {
      if (segment instanceof SerializationSegment.ValueSerializationSegment) {
        _matched=true;
        return this.generateSerializationSegment_Value(((SerializationSegment.ValueSerializationSegment)segment));
      }
    }
    if (!_matched) {
      _switchResult = segment.getClass().getName();
    }
    return _switchResult;
  }
  
  protected CharSequence generateSerializationSegment_Custom(final SerializationSegment.CustomSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _newTypeReference = this.newTypeReference(SerializationSegment.CustomSerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append("(");
    String _newTypeReference_1 = this.newTypeReference(segment.getSupportClassName());
    _builder.append(_newTypeReference_1);
    _builder.append(".class)");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_HalfNewLine(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".HALF_NEW_LINE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_NewLine(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".NEW_LINE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_NoSpace(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".NO_SPACE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_Pop(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".POP");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_Push(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".PUSH");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_SoftNewLine(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".SOFT_NEW_LINE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_SoftSpace(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".SOFT_SPACE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_String(final SerializationSegment.StringSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".createStringSegment(");
    String _string = segment.getString();
    _builder.append(_string);
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegment_Value(final SerializationSegment.ValueSerializationSegment segment) {
    StringConcatenation _builder = new StringConcatenation();
    String _newTypeReference = this.newTypeReference(SerializationSegment.class);
    _builder.append(_newTypeReference);
    _builder.append(".VALUE");
    return _builder;
  }
  
  protected CharSequence generateSerializationSegmentsLists(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The various lists of string segment sequences that may be used to serialize a serialization rule.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _SerializationSegmentsLists");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<SerializationSegment[][]> _serializationSegmentsListIterable = this.getSerializationSegmentsListIterable(grammarAnalysis);
      for(final SerializationSegment[][] segmentsList : _serializationSegmentsListIterable) {
        _builder.append("\t");
        _builder.append("private final @NonNull ");
        String _newTypeReference = this.newTypeReference(SerializationSegment.class);
        _builder.append(_newTypeReference, "\t");
        _builder.append(" @NonNull [] @Nullable [] ");
        String _serializationSegmentsListId = this.getSerializationSegmentsListId(this.getSerializationSegmentsListString(segmentsList), false);
        _builder.append(_serializationSegmentsListId, "\t");
        _builder.append(" = new @NonNull ");
        String _newTypeReference_1 = this.newTypeReference(SerializationSegment.class);
        _builder.append(_newTypeReference_1, "\t");
        _builder.append(" @NonNull [] @Nullable [] {");
        _builder.newLineIfNotEmpty();
        {
          boolean _hasElements = false;
          for(final SerializationSegment[] segments : segmentsList) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(",", "\t\t");
            }
            _builder.append("\t");
            _builder.append("\t");
            {
              if ((segments != null)) {
                String _serializationSegmentsId = this.getSerializationSegmentsId(((List<SerializationSegment>)Conversions.doWrapArray(segments)), true);
                _builder.append(_serializationSegmentsId, "\t\t");
                _builder.append(" /* ");
                {
                  boolean _hasElements_1 = false;
                  for(final SerializationSegment segment : segments) {
                    if (!_hasElements_1) {
                      _hasElements_1 = true;
                    } else {
                      _builder.appendImmediate(" ", "\t\t");
                    }
                    String _string = segment.toString();
                    _builder.append(_string, "\t\t");
                  }
                }
                _builder.append(" */");
              } else {
                _builder.append("null");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("};");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateSerializationTerms(final GrammarAnalysis grammarAnalysis) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The various serialization term used to serialize a serialization rule.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private class _SerializationTerms");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    {
      Iterable<SerializationStep> _serializationStepIterable = this.getSerializationStepIterable(grammarAnalysis);
      for(final SerializationStep step : _serializationStepIterable) {
        _builder.append("\t");
        CharSequence _generateSerializationTerm = this.generateSerializationTerm(step);
        _builder.append(_generateSerializationTerm, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateSerializationTerm(final SerializationStep serializationStep) {
    boolean _matched = false;
    if (serializationStep instanceof SerializationStep.SerializationStepAssignKeyword) {
      _matched=true;
      return this.generateSerializationTerm_Assign(((SerializationStep.SerializationStepAssignKeyword)serializationStep));
    }
    if (!_matched) {
      if (serializationStep instanceof SerializationStep.SerializationStepAssignedRuleCall) {
        _matched=true;
        return this.generateSerializationTerm_AssignedRuleCall(((SerializationStep.SerializationStepAssignedRuleCall)serializationStep));
      }
    }
    if (!_matched) {
      if (serializationStep instanceof SerializationStep.SerializationStepAssigns) {
        _matched=true;
        return this.generateSerializationTerm_AssignsStep(((SerializationStep.SerializationStepAssigns)serializationStep));
      }
    }
    if (!_matched) {
      if (serializationStep instanceof SerializationStep.SerializationStepCrossReference) {
        _matched=true;
        return this.generateSerializationTerm_CrossReference(((SerializationStep.SerializationStepCrossReference)serializationStep));
      }
    }
    if (!_matched) {
      if (serializationStep instanceof SerializationStep.SerializationStepLiteral) {
        _matched=true;
        return this.generateSerializationTerm_Literal(((SerializationStep.SerializationStepLiteral)serializationStep));
      }
    }
    if (!_matched) {
      if (serializationStep instanceof SerializationStep.SerializationStepSequence) {
        _matched=true;
        return this.generateSerializationTerm_Sequence(((SerializationStep.SerializationStepSequence)serializationStep));
      }
    }
    throw new UnsupportedOperationException();
  }
  
  protected CharSequence generateSerializationTerm_Assign(final SerializationStep.SerializationStepAssignKeyword serializationStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepAssignKeyword.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _serializationStepId = this.getSerializationStepId(serializationStep, false);
    _builder.append(_serializationStepId);
    _builder.append(" // ");
    String _string = serializationStep.toString();
    _builder.append(_string);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepAssignKeyword.class);
    _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
    _builder.append("(");
    int _variableIndex = serializationStep.getVariableIndex();
    _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    String _emitLiteral = this.emitLiteral(serializationStep.getEStructuralFeature());
    _builder.append(_emitLiteral, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    String _enumValueId = this.getEnumValueId(serializationStep.getEnumerationValue(), true);
    _builder.append(_enumValueId, "\t\t\t\t\t\t\t");
    _builder.append(")");
    return _builder;
  }
  
  protected CharSequence generateSerializationTerm_AssignedRuleCall(final SerializationStep.SerializationStepAssignedRuleCall serializationStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepAssignedRuleCall.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _serializationStepId = this.getSerializationStepId(serializationStep, false);
    _builder.append(_serializationStepId);
    _builder.append(" // ");
    String _string = serializationStep.toString();
    _builder.append(_string);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepAssignedRuleCall.class);
    _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
    _builder.append("(");
    int _variableIndex = serializationStep.getVariableIndex();
    _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    String _emitLiteral = this.emitLiteral(serializationStep.getEStructuralFeature());
    _builder.append(_emitLiteral, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    int _calledRuleIndex = serializationStep.getCalledRuleIndex();
    _builder.append(_calledRuleIndex, "\t\t\t\t\t\t\t");
    _builder.append(" /* ");
    String _name = this.getGrammarAnalysis().getRuleValue(serializationStep.getCalledRuleIndex()).getName();
    _builder.append(_name, "\t\t\t\t\t\t\t");
    _builder.append(" */)");
    return _builder;
  }
  
  protected CharSequence generateSerializationTerm_AssignsStep(final SerializationStep.SerializationStepAssigns serializationStep) {
    CharSequence _xblockexpression = null;
    {
      EnumerationValue enumerationValue = serializationStep.getEnumerationValue();
      Integer[] calledRuleIndexes = serializationStep.getCalledRuleIndexes();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("private final @NonNull ");
      String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepAssigns.class);
      _builder.append(_newTypeReference);
      _builder.append(" ");
      String _serializationStepId = this.getSerializationStepId(serializationStep, false);
      _builder.append(_serializationStepId);
      _builder.append(" // ");
      String _string = serializationStep.toString();
      _builder.append(_string);
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t\t\t\t");
      _builder.append("= new ");
      String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepAssigns.class);
      _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
      _builder.append("(");
      int _variableIndex = serializationStep.getVariableIndex();
      _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
      _builder.append(", ");
      String _emitLiteral = this.emitLiteral(serializationStep.getEStructuralFeature());
      _builder.append(_emitLiteral, "\t\t\t\t\t\t\t");
      _builder.append(", ");
      {
        if ((enumerationValue != null)) {
          String _enumValueId = this.getEnumValueId(enumerationValue, true);
          _builder.append(_enumValueId, "\t\t\t\t\t\t\t");
        } else {
          _builder.append("null");
        }
      }
      _builder.append(", ");
      {
        if ((calledRuleIndexes != null)) {
          _builder.append("new @NonNull Integer [] { ");
          {
            boolean _hasElements = false;
            for(final Integer calledRuleIndex : calledRuleIndexes) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(",", "\t\t\t\t\t\t\t");
              }
              _builder.append(calledRuleIndex, "\t\t\t\t\t\t\t");
              _builder.append("/*");
              String _name = this.getGrammarAnalysis().getRuleValue((calledRuleIndex).intValue()).getName();
              _builder.append(_name, "\t\t\t\t\t\t\t");
              _builder.append("*/");
            }
          }
          _builder.append("}");
        } else {
          _builder.append("null");
        }
      }
      _builder.append(")");
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateSerializationTerm_CrossReference(final SerializationStep.SerializationStepCrossReference serializationStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepCrossReference.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _serializationStepId = this.getSerializationStepId(serializationStep, false);
    _builder.append(_serializationStepId);
    _builder.append(" // ");
    String _string = serializationStep.toString();
    _builder.append(_string);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepCrossReference.class);
    _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
    _builder.append("(");
    int _variableIndex = serializationStep.getVariableIndex();
    _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    String _emitLiteral = this.emitLiteral(serializationStep.getEStructuralFeature());
    _builder.append(_emitLiteral, "\t\t\t\t\t\t\t");
    _builder.append(", getCrossReference(");
    String _emitLiteral_1 = this.emitLiteral(serializationStep.getEStructuralFeature());
    _builder.append(_emitLiteral_1, "\t\t\t\t\t\t\t");
    _builder.append(", \"");
    String _emitCalledRule = this.emitCalledRule(serializationStep.getCrossReference());
    _builder.append(_emitCalledRule, "\t\t\t\t\t\t\t");
    _builder.append("\"))");
    return _builder;
  }
  
  protected CharSequence generateSerializationTerm_Literal(final SerializationStep.SerializationStepLiteral serializationStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepLiteral.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _serializationStepId = this.getSerializationStepId(serializationStep, false);
    _builder.append(_serializationStepId);
    _builder.append(" // ");
    String _string = serializationStep.toString();
    _builder.append(_string);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepLiteral.class);
    _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
    _builder.append("(");
    int _variableIndex = serializationStep.getVariableIndex();
    _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
    _builder.append(", \"");
    String _convertToJavaString = Strings.convertToJavaString(serializationStep.getString());
    _builder.append(_convertToJavaString, "\t\t\t\t\t\t\t");
    _builder.append("\")");
    return _builder;
  }
  
  protected CharSequence generateSerializationTerm_Sequence(final SerializationStep.SerializationStepSequence serializationStep) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final @NonNull ");
    String _newTypeReference = this.newTypeReference(SerializationStep.SerializationStepSequence.class);
    _builder.append(_newTypeReference);
    _builder.append(" ");
    String _serializationStepId = this.getSerializationStepId(serializationStep, false);
    _builder.append(_serializationStepId);
    _builder.append(" // ");
    String _string = serializationStep.toString();
    _builder.append(_string);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("= new ");
    String _newTypeReference_1 = this.newTypeReference(SerializationStep.SerializationStepSequence.class);
    _builder.append(_newTypeReference_1, "\t\t\t\t\t\t\t");
    _builder.append("(");
    int _variableIndex = serializationStep.getVariableIndex();
    _builder.append(_variableIndex, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    int _startIndex = serializationStep.getStartIndex();
    _builder.append(_startIndex, "\t\t\t\t\t\t\t");
    _builder.append(", ");
    int _endIndex = serializationStep.getEndIndex();
    _builder.append(_endIndex, "\t\t\t\t\t\t\t");
    _builder.append(")");
    return _builder;
  }
}
