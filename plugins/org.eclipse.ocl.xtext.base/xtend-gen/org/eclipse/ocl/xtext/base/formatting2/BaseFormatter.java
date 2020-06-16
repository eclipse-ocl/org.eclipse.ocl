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
package org.eclipse.ocl.xtext.base.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.services.BaseGrammarAccess;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class BaseFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private BaseGrammarAccess _baseGrammarAccess;
  
  protected void _format(final PathNameCS pathNameCS, @Extension final IFormattableDocument document) {
    EList<PathElementCS> _ownedPathElements = pathNameCS.getOwnedPathElements();
    for (final PathElementCS pathElementCS : _ownedPathElements) {
      document.<PathElementCS>format(pathElementCS);
    }
  }
  
  protected void _format(final TemplateBindingCS templateBindingCS, @Extension final IFormattableDocument document) {
    EList<TemplateParameterSubstitutionCS> _ownedSubstitutions = templateBindingCS.getOwnedSubstitutions();
    for (final TemplateParameterSubstitutionCS templateParameterSubstitutionCS : _ownedSubstitutions) {
      document.<TemplateParameterSubstitutionCS>format(templateParameterSubstitutionCS);
    }
    document.<MultiplicityCS>format(templateBindingCS.getOwnedMultiplicity());
  }
  
  public void format(final Object templateBindingCS, final IFormattableDocument document) {
    if (templateBindingCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)templateBindingCS, document);
      return;
    } else if (templateBindingCS instanceof XtextResource) {
      _format((XtextResource)templateBindingCS, document);
      return;
    } else if (templateBindingCS instanceof PathNameCS) {
      _format((PathNameCS)templateBindingCS, document);
      return;
    } else if (templateBindingCS instanceof EObject) {
      _format((EObject)templateBindingCS, document);
      return;
    } else if (templateBindingCS == null) {
      _format((Void)null, document);
      return;
    } else if (templateBindingCS != null) {
      _format(templateBindingCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(templateBindingCS, document).toString());
    }
  }
}
