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
package org.eclipse.ocl.xtext.oclstdlib.formatting3;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.essentialocl.formatting3.EssentialOCLFormatter;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.oclstdlib.services.OCLstdlibGrammarAccess;
import org.eclipse.ocl.xtext.oclstdlibcs.LibRootPackageCS;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class OCLstdlibFormatter extends EssentialOCLFormatter {
  @Inject
  @Extension
  private OCLstdlibGrammarAccess _oCLstdlibGrammarAccess;
  
  protected void _format(final LibRootPackageCS libRootPackageCS, @Extension final IFormattableDocument document) {
    EList<ImportCS> _ownedImports = libRootPackageCS.getOwnedImports();
    for (final ImportCS importCS : _ownedImports) {
      document.<ImportCS>format(importCS);
    }
    EList<PackageCS> _ownedPackages = libRootPackageCS.getOwnedPackages();
    for (final PackageCS packageCS : _ownedPackages) {
      document.<PackageCS>format(packageCS);
    }
  }
  
  @Override
  protected void _format(final PathNameCS pathNameCS, @Extension final IFormattableDocument document) {
    EList<PathElementCS> _ownedPathElements = pathNameCS.getOwnedPathElements();
    for (final PathElementCS pathElementCS : _ownedPathElements) {
      document.<PathElementCS>format(pathElementCS);
    }
  }
  
  @Override
  public void format(final Object libRootPackageCS, final IFormattableDocument document) {
    if (libRootPackageCS instanceof LibRootPackageCS) {
      _format((LibRootPackageCS)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS instanceof ContextCS) {
      _format((ContextCS)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS instanceof XtextResource) {
      _format((XtextResource)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS instanceof PathNameCS) {
      _format((PathNameCS)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS instanceof EObject) {
      _format((EObject)libRootPackageCS, document);
      return;
    } else if (libRootPackageCS == null) {
      _format((Void)null, document);
      return;
    } else if (libRootPackageCS != null) {
      _format(libRootPackageCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(libRootPackageCS, document).toString());
    }
  }
}
