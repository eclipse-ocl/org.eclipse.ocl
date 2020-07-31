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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.essentialocl.formatting2.EssentialOCLFormatter;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class OCLinEcoreFormatter extends EssentialOCLFormatter {
  @Inject
  @Extension
  private OCLinEcoreGrammarAccess _oCLinEcoreGrammarAccess;
  
  @Inject
  private UserModelAnalysis modelAnalysis;
  
  @Inject
  private SerializationBuilder serializationBuilder;
  
  protected void _format(final TopLevelCS topLevelCS, @Extension final IFormattableDocument document) {
    GrammarAnalysis grammarAnalysis = this.modelAnalysis.getGrammarAnalysis();
    grammarAnalysis.analyze();
    String s1 = grammarAnalysis.toString();
    System.out.println(s1);
    System.out.println("\n");
    this.modelAnalysis.analyze(topLevelCS);
    String s2 = this.modelAnalysis.toString();
    System.out.println(s2);
    this.modelAnalysis.serialize(this.serializationBuilder, topLevelCS, null);
    String s3 = this.serializationBuilder.toRenderedString();
    System.out.println(s3);
  }
  
  @Override
  public void _format(final XtextResource resource, final IFormattableDocument document) {
    super._format(resource, document);
  }
  
  public void format(final TopLevelCS topLevelCS, @Extension final IFormattableDocument document) {
    GrammarAnalysis grammarAnalysis = this.modelAnalysis.getGrammarAnalysis();
    grammarAnalysis.analyze();
    String s1 = grammarAnalysis.toString();
    System.out.println(s1);
    System.out.println("\n");
    this.modelAnalysis.analyze(topLevelCS);
    String s2 = this.modelAnalysis.toString();
    System.out.println(s2);
    this.modelAnalysis.serialize(this.serializationBuilder, topLevelCS, null);
    String s3 = this.serializationBuilder.toRenderedString();
    System.out.println(s3);
  }
  
  @Override
  public void format(final Object topLevelCS, final IFormattableDocument document) {
    if (topLevelCS instanceof TopLevelCS) {
      _format((TopLevelCS)topLevelCS, document);
      return;
    } else if (topLevelCS instanceof ContextCS) {
      _format((ContextCS)topLevelCS, document);
      return;
    } else if (topLevelCS instanceof TemplateBindingCS) {
      _format((TemplateBindingCS)topLevelCS, document);
      return;
    } else if (topLevelCS instanceof XtextResource) {
      _format((XtextResource)topLevelCS, document);
      return;
    } else if (topLevelCS instanceof PathNameCS) {
      _format((PathNameCS)topLevelCS, document);
      return;
    } else if (topLevelCS instanceof EObject) {
      _format((EObject)topLevelCS, document);
      return;
    } else if (topLevelCS == null) {
      _format((Void)null, document);
      return;
    } else if (topLevelCS != null) {
      _format(topLevelCS, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(topLevelCS, document).toString());
    }
  }
}
