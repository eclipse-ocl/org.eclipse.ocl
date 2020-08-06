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
package org.eclipse.ocl.xtext.completeocl.formatting3;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.ContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;
import org.eclipse.ocl.xtext.essentialocl.formatting3.EssentialOCLFormatter;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class CompleteOCLFormatter extends EssentialOCLFormatter {
  @Inject
  @Extension
  private CompleteOCLGrammarAccess _completeOCLGrammarAccess;
  
  protected void _format(final CompleteOCLDocumentCS completeOCLDocumentCS, @Extension final IFormattableDocument document) {
    EList<ImportCS> _ownedImports = completeOCLDocumentCS.getOwnedImports();
    for (final ImportCS importCS : _ownedImports) {
      document.<ImportCS>format(importCS);
    }
    EList<PackageDeclarationCS> _ownedPackages = completeOCLDocumentCS.getOwnedPackages();
    for (final PackageDeclarationCS packageDeclarationCS : _ownedPackages) {
      document.<PackageDeclarationCS>format(packageDeclarationCS);
    }
    EList<ContextDeclCS> _ownedContexts = completeOCLDocumentCS.getOwnedContexts();
    for (final ContextDeclCS contextDeclCS : _ownedContexts) {
      document.<ContextDeclCS>format(contextDeclCS);
    }
  }
  
  protected void _format(final ClassifierContextDeclCS classifierContextDeclCS, @Extension final IFormattableDocument document) {
    document.<TemplateSignatureCS>format(classifierContextDeclCS.getOwnedSignature());
    document.<PathNameCS>format(classifierContextDeclCS.getOwnedPathName());
    EList<ConstraintCS> _ownedInvariants = classifierContextDeclCS.getOwnedInvariants();
    for (final ConstraintCS constraintCS : _ownedInvariants) {
      document.<ConstraintCS>format(constraintCS);
    }
    EList<DefCS> _ownedDefinitions = classifierContextDeclCS.getOwnedDefinitions();
    for (final DefCS defCS : _ownedDefinitions) {
      document.<DefCS>format(defCS);
    }
  }
  
  public void format(final Object classifierContextDeclCS, final IFormattableDocument document) {
    if (classifierContextDeclCS instanceof ClassifierContextDeclCS) {
      _format((ClassifierContextDeclCS)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof CompleteOCLDocumentCS) {
      _format((CompleteOCLDocumentCS)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof ContextCS) {
      _format((ContextCS)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof XtextResource) {
      _format((XtextResource)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof PathNameCS) {
      _format((PathNameCS)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS instanceof EObject) {
      _format((EObject)classifierContextDeclCS, document);
      return;
    } else if (classifierContextDeclCS == null) {
      _format((Void)null, document);
      return;
    } else if (classifierContextDeclCS != null) {
      _format(classifierContextDeclCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(classifierContextDeclCS, document).toString());
    }
  }
}
