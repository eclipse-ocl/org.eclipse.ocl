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
package org.eclipse.ocl.xtext.essentialocl.formatting3;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.formatting3.BaseFormatter;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EssentialOCLFormatter extends BaseFormatter {
  @Inject
  @Extension
  private EssentialOCLGrammarAccess _essentialOCLGrammarAccess;
  
  protected void _format(final ContextCS contextCS, @Extension final IFormattableDocument document) {
    document.<ExpCS>format(contextCS.getOwnedExpression());
  }
  
  @Override
  protected void _format(final PathNameCS pathNameCS, @Extension final IFormattableDocument document) {
    EList<PathElementCS> _ownedPathElements = pathNameCS.getOwnedPathElements();
    for (final PathElementCS pathElementCS : _ownedPathElements) {
      document.<PathElementCS>format(pathElementCS);
    }
  }
  
  @Override
  public void format(final Object contextCS, final IFormattableDocument document) {
    if (contextCS instanceof ContextCS) {
      _format((ContextCS)contextCS, document);
      return;
    } else if (contextCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)contextCS, document);
      return;
    } else if (contextCS instanceof XtextResource) {
      _format((XtextResource)contextCS, document);
      return;
    } else if (contextCS instanceof PathNameCS) {
      _format((PathNameCS)contextCS, document);
      return;
    } else if (contextCS instanceof EObject) {
      _format((EObject)contextCS, document);
      return;
    } else if (contextCS == null) {
      _format((Void)null, document);
      return;
    } else if (contextCS != null) {
      _format(contextCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(contextCS, document).toString());
    }
  }
}
