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
package org.eclipse.ocl.xtext.oclinecore.formatting3;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.essentialocl.formatting3.EssentialOCLFormatter;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreConstraintCS;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class OCLinEcoreFormatter extends EssentialOCLFormatter {
  @Inject
  @Extension
  private OCLinEcoreGrammarAccess _oCLinEcoreGrammarAccess;
  
  protected void _format(final TopLevelCS topLevelCS, @Extension final IFormattableDocument document) {
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
    document.<SpecificationCS>format(oCLinEcoreConstraintCS.getOwnedMessageSpecification());
    document.<SpecificationCS>format(oCLinEcoreConstraintCS.getOwnedSpecification());
  }
  
  public void format(final Object oCLinEcoreConstraintCS, final IFormattableDocument document) {
    if (oCLinEcoreConstraintCS instanceof OCLinEcoreConstraintCS) {
      _format((OCLinEcoreConstraintCS)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof TopLevelCS) {
      _format((TopLevelCS)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof ContextCS) {
      _format((ContextCS)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof XtextResource) {
      _format((XtextResource)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof PathNameCS) {
      _format((PathNameCS)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS instanceof EObject) {
      _format((EObject)oCLinEcoreConstraintCS, document);
      return;
    } else if (oCLinEcoreConstraintCS == null) {
      _format((Void)null, document);
      return;
    } else if (oCLinEcoreConstraintCS != null) {
      _format(oCLinEcoreConstraintCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(oCLinEcoreConstraintCS, document).toString());
    }
  }
}
