/**
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
 */
package org.eclipse.ocl.xtext.oclinecore.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.utilities.AbstractGrammarResource;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.essentialocl.formatting2.EssentialOCLFormatter;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreConstraintCS;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegionsFinder;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class OCLinEcoreFormatter extends EssentialOCLFormatter {
  @Inject
  @Extension
  private OCLinEcoreGrammarAccess _oCLinEcoreGrammarAccess;
  
  @Inject
  private IValueConverterService valueConverterService;
  
  @Inject
  private LinkingHelper linkingHelper;
  
  @Inject
  private ICrossReferenceSerializer crossReferenceSerializer;
  
  protected void _format(final TopLevelCS topLevelCS, @Extension final IFormattableDocument document) {
    final IEObjectRegion regionForEObject = this.textRegionExtensions.regionForEObject(topLevelCS);
    final EObject grammarElement = regionForEObject.getGrammarElement();
    Resource _eResource = grammarElement.eResource();
    GrammarAnalysis grammarAnalysis = new GrammarAnalysis(((AbstractGrammarResource) _eResource), this.crossReferenceSerializer, this.valueConverterService, this.linkingHelper);
    grammarAnalysis.analyze();
    String s1 = grammarAnalysis.toString();
    System.out.println(s1);
    System.out.println("\n");
    UserModelAnalysis modelAnalysis = new UserModelAnalysis(grammarAnalysis);
    modelAnalysis.analyze(topLevelCS);
    String s2 = modelAnalysis.toString();
    System.out.println(s2);
    SerializationBuilder serializationBuilder = new SerializationBuilder("\n", "    ");
    modelAnalysis.serialize(serializationBuilder, topLevelCS);
    String s3 = serializationBuilder.toRenderedString();
    System.out.println(s3);
    EList<ImportCS> _ownedImports = topLevelCS.getOwnedImports();
    for (final ImportCS importCS : _ownedImports) {
      document.<ImportCS>format(importCS);
    }
    EList<PackageCS> _ownedPackages = topLevelCS.getOwnedPackages();
    for (final PackageCS packageCS : _ownedPackages) {
      document.<PackageCS>format(packageCS);
    }
  }
  
  protected void _format(final OCLinEcoreConstraintCS oCLinEcoreConstraintCS, @Extension final IFormattableDocument document) {
    final ISemanticRegionsFinder regionFor2 = this.textRegionExtensions.regionFor(oCLinEcoreConstraintCS);
    document.<SpecificationCS>format(oCLinEcoreConstraintCS.getOwnedMessageSpecification());
    document.<SpecificationCS>format(oCLinEcoreConstraintCS.getOwnedSpecification());
  }
  
  protected void _format(final ImportCS csImport, @Extension final IFormattableDocument document) {
    final IEObjectRegion regionForEObject = this.textRegionExtensions.regionForEObject(csImport);
    final EObject grammarElement = regionForEObject.getGrammarElement();
    final ISemanticRegionsFinder regionFor2 = this.textRegionExtensions.regionFor(csImport);
    final Procedure1<IHiddenRegionFormatter> _function = (IHiddenRegionFormatter it) -> {
      it.noSpace();
    };
    final Procedure1<IHiddenRegionFormatter> _function_1 = (IHiddenRegionFormatter it) -> {
      it.newLine();
    };
    document.append(document.prepend(regionFor2.keyword(";"), _function), _function_1);
  }
  
  protected void _format(final TypedRefCS csTypedRef, @Extension final IFormattableDocument document) {
    final ISemanticRegionsFinder regionFor2 = this.textRegionExtensions.regionFor(csTypedRef);
    regionFor2.keyword(";");
  }
  
  public void format(final Object csImport, final IFormattableDocument document) {
    if (csImport instanceof ImportCS) {
      _format((ImportCS)csImport, document);
      return;
    } else if (csImport instanceof OCLinEcoreConstraintCS) {
      _format((OCLinEcoreConstraintCS)csImport, document);
      return;
    } else if (csImport instanceof TopLevelCS) {
      _format((TopLevelCS)csImport, document);
      return;
    } else if (csImport instanceof TypedRefCS) {
      _format((TypedRefCS)csImport, document);
      return;
    } else if (csImport instanceof ContextCS) {
      _format((ContextCS)csImport, document);
      return;
    } else if (csImport instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)csImport, document);
      return;
    } else if (csImport instanceof XtextResource) {
      _format((XtextResource)csImport, document);
      return;
    } else if (csImport instanceof PathNameCS) {
      _format((PathNameCS)csImport, document);
      return;
    } else if (csImport instanceof EObject) {
      _format((EObject)csImport, document);
      return;
    } else if (csImport == null) {
      _format((Void)null, document);
      return;
    } else if (csImport != null) {
      _format(csImport, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(csImport, document).toString());
    }
  }
}
