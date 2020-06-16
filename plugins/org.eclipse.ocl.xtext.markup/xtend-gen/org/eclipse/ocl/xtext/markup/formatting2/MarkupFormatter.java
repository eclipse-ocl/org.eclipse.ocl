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
package org.eclipse.ocl.xtext.markup.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.markup.services.MarkupGrammarAccess;
import org.eclipse.ocl.xtext.markupcs.BulletElement;
import org.eclipse.ocl.xtext.markupcs.Markup;
import org.eclipse.ocl.xtext.markupcs.MarkupElement;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class MarkupFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private MarkupGrammarAccess _markupGrammarAccess;
  
  protected void _format(final Markup markup, @Extension final IFormattableDocument document) {
    EList<MarkupElement> _elements = markup.getElements();
    for (final MarkupElement markupElement : _elements) {
      document.<MarkupElement>format(markupElement);
    }
  }
  
  protected void _format(final BulletElement bulletElement, @Extension final IFormattableDocument document) {
    EList<MarkupElement> _elements = bulletElement.getElements();
    for (final MarkupElement markupElement : _elements) {
      document.<MarkupElement>format(markupElement);
    }
  }
  
  public void format(final Object bulletElement, final IFormattableDocument document) {
    if (bulletElement instanceof BulletElement) {
      _format((BulletElement)bulletElement, document);
      return;
    } else if (bulletElement instanceof Markup) {
      _format((Markup)bulletElement, document);
      return;
    } else if (bulletElement instanceof XtextResource) {
      _format((XtextResource)bulletElement, document);
      return;
    } else if (bulletElement instanceof EObject) {
      _format((EObject)bulletElement, document);
      return;
    } else if (bulletElement == null) {
      _format((Void)null, document);
      return;
    } else if (bulletElement != null) {
      _format(bulletElement, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(bulletElement, document).toString());
    }
  }
}
