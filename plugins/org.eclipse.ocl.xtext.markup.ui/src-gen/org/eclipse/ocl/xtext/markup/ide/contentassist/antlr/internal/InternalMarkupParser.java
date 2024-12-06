package org.eclipse.ocl.xtext.markup.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.eclipse.ocl.xtext.markup.services.MarkupGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Copyright (c) 2011, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalMarkupParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_WORD", "RULE_INT", "RULE_WS", "RULE_STRING", "RULE_NL", "RULE_NUMBER", "RULE_LETTER", "RULE_ESCAPED", "RULE_VERTICAL_WS", "RULE_HORIZONTAL_WS", "RULE_ANY_OTHER", "'b'", "'e'", "'bullet'", "'figure'", "'figureRef'", "'footnote'", "'heading'", "'oclCode'", "'oclEval'", "'oclText'", "':'", "'#'", "','", "'['", "']'"
    };
    public static final int RULE_ESCAPED=12;
    public static final int RULE_WORD=5;
    public static final int RULE_STRING=8;
    public static final int RULE_VERTICAL_WS=13;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int RULE_ID=4;
    public static final int RULE_WS=7;
    public static final int RULE_HORIZONTAL_WS=14;
    public static final int RULE_ANY_OTHER=15;
    public static final int RULE_NUMBER=10;
    public static final int RULE_LETTER=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_NL=9;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalMarkupParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMarkupParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);

        }


    public String[] getTokenNames() { return InternalMarkupParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMarkup.g"; }


    	private MarkupGrammarAccess grammarAccess;

    	public void setGrammarAccess(MarkupGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleMarkup"
    // InternalMarkup.g:63:1: entryRuleMarkup : ruleMarkup EOF ;
    public final void entryRuleMarkup() throws RecognitionException {
        try {
            // InternalMarkup.g:64:1: ( ruleMarkup EOF )
            // InternalMarkup.g:65:1: ruleMarkup EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMarkup();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMarkup"


    // $ANTLR start "ruleMarkup"
    // InternalMarkup.g:72:1: ruleMarkup : ( ( rule__Markup__ElementsAssignment )* ) ;
    public final void ruleMarkup() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:76:2: ( ( ( rule__Markup__ElementsAssignment )* ) )
            // InternalMarkup.g:77:2: ( ( rule__Markup__ElementsAssignment )* )
            {
            // InternalMarkup.g:77:2: ( ( rule__Markup__ElementsAssignment )* )
            // InternalMarkup.g:78:3: ( rule__Markup__ElementsAssignment )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupAccess().getElementsAssignment());
            }
            // InternalMarkup.g:79:3: ( rule__Markup__ElementsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_ID && LA1_0<=RULE_WS)||LA1_0==RULE_NL||(LA1_0>=16 && LA1_0<=29)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMarkup.g:79:4: rule__Markup__ElementsAssignment
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__Markup__ElementsAssignment();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupAccess().getElementsAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMarkup"


    // $ANTLR start "entryRuleMarkupKeyword"
    // InternalMarkup.g:88:1: entryRuleMarkupKeyword : ruleMarkupKeyword EOF ;
    public final void entryRuleMarkupKeyword() throws RecognitionException {
        try {
            // InternalMarkup.g:89:1: ( ruleMarkupKeyword EOF )
            // InternalMarkup.g:90:1: ruleMarkupKeyword EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupKeywordRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMarkupKeyword();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupKeywordRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMarkupKeyword"


    // $ANTLR start "ruleMarkupKeyword"
    // InternalMarkup.g:97:1: ruleMarkupKeyword : ( ( rule__MarkupKeyword__Alternatives ) ) ;
    public final void ruleMarkupKeyword() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:101:2: ( ( ( rule__MarkupKeyword__Alternatives ) ) )
            // InternalMarkup.g:102:2: ( ( rule__MarkupKeyword__Alternatives ) )
            {
            // InternalMarkup.g:102:2: ( ( rule__MarkupKeyword__Alternatives ) )
            // InternalMarkup.g:103:3: ( rule__MarkupKeyword__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupKeywordAccess().getAlternatives());
            }
            // InternalMarkup.g:104:3: ( rule__MarkupKeyword__Alternatives )
            // InternalMarkup.g:104:4: rule__MarkupKeyword__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MarkupKeyword__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupKeywordAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMarkupKeyword"


    // $ANTLR start "entryRuleMarkupElement"
    // InternalMarkup.g:113:1: entryRuleMarkupElement : ruleMarkupElement EOF ;
    public final void entryRuleMarkupElement() throws RecognitionException {
        try {
            // InternalMarkup.g:114:1: ( ruleMarkupElement EOF )
            // InternalMarkup.g:115:1: ruleMarkupElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMarkupElement"


    // $ANTLR start "ruleMarkupElement"
    // InternalMarkup.g:122:1: ruleMarkupElement : ( ( rule__MarkupElement__Alternatives ) ) ;
    public final void ruleMarkupElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:126:2: ( ( ( rule__MarkupElement__Alternatives ) ) )
            // InternalMarkup.g:127:2: ( ( rule__MarkupElement__Alternatives ) )
            {
            // InternalMarkup.g:127:2: ( ( rule__MarkupElement__Alternatives ) )
            // InternalMarkup.g:128:3: ( rule__MarkupElement__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupElementAccess().getAlternatives());
            }
            // InternalMarkup.g:129:3: ( rule__MarkupElement__Alternatives )
            // InternalMarkup.g:129:4: rule__MarkupElement__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MarkupElement__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupElementAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMarkupElement"


    // $ANTLR start "entryRuleBulletElement"
    // InternalMarkup.g:138:1: entryRuleBulletElement : ruleBulletElement EOF ;
    public final void entryRuleBulletElement() throws RecognitionException {
        try {
            // InternalMarkup.g:139:1: ( ruleBulletElement EOF )
            // InternalMarkup.g:140:1: ruleBulletElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleBulletElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBulletElement"


    // $ANTLR start "ruleBulletElement"
    // InternalMarkup.g:147:1: ruleBulletElement : ( ( rule__BulletElement__Group__0 ) ) ;
    public final void ruleBulletElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:151:2: ( ( ( rule__BulletElement__Group__0 ) ) )
            // InternalMarkup.g:152:2: ( ( rule__BulletElement__Group__0 ) )
            {
            // InternalMarkup.g:152:2: ( ( rule__BulletElement__Group__0 ) )
            // InternalMarkup.g:153:3: ( rule__BulletElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getGroup());
            }
            // InternalMarkup.g:154:3: ( rule__BulletElement__Group__0 )
            // InternalMarkup.g:154:4: rule__BulletElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBulletElement"


    // $ANTLR start "entryRuleFontElement"
    // InternalMarkup.g:163:1: entryRuleFontElement : ruleFontElement EOF ;
    public final void entryRuleFontElement() throws RecognitionException {
        try {
            // InternalMarkup.g:164:1: ( ruleFontElement EOF )
            // InternalMarkup.g:165:1: ruleFontElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFontElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFontElement"


    // $ANTLR start "ruleFontElement"
    // InternalMarkup.g:172:1: ruleFontElement : ( ( rule__FontElement__Group__0 ) ) ;
    public final void ruleFontElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:176:2: ( ( ( rule__FontElement__Group__0 ) ) )
            // InternalMarkup.g:177:2: ( ( rule__FontElement__Group__0 ) )
            {
            // InternalMarkup.g:177:2: ( ( rule__FontElement__Group__0 ) )
            // InternalMarkup.g:178:3: ( rule__FontElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getGroup());
            }
            // InternalMarkup.g:179:3: ( rule__FontElement__Group__0 )
            // InternalMarkup.g:179:4: rule__FontElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFontElement"


    // $ANTLR start "entryRuleFigureElement"
    // InternalMarkup.g:188:1: entryRuleFigureElement : ruleFigureElement EOF ;
    public final void entryRuleFigureElement() throws RecognitionException {
        try {
            // InternalMarkup.g:189:1: ( ruleFigureElement EOF )
            // InternalMarkup.g:190:1: ruleFigureElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFigureElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFigureElement"


    // $ANTLR start "ruleFigureElement"
    // InternalMarkup.g:197:1: ruleFigureElement : ( ( rule__FigureElement__Group__0 ) ) ;
    public final void ruleFigureElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:201:2: ( ( ( rule__FigureElement__Group__0 ) ) )
            // InternalMarkup.g:202:2: ( ( rule__FigureElement__Group__0 ) )
            {
            // InternalMarkup.g:202:2: ( ( rule__FigureElement__Group__0 ) )
            // InternalMarkup.g:203:3: ( rule__FigureElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getGroup());
            }
            // InternalMarkup.g:204:3: ( rule__FigureElement__Group__0 )
            // InternalMarkup.g:204:4: rule__FigureElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFigureElement"


    // $ANTLR start "entryRuleFigureRefElement"
    // InternalMarkup.g:213:1: entryRuleFigureRefElement : ruleFigureRefElement EOF ;
    public final void entryRuleFigureRefElement() throws RecognitionException {
        try {
            // InternalMarkup.g:214:1: ( ruleFigureRefElement EOF )
            // InternalMarkup.g:215:1: ruleFigureRefElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFigureRefElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFigureRefElement"


    // $ANTLR start "ruleFigureRefElement"
    // InternalMarkup.g:222:1: ruleFigureRefElement : ( ( rule__FigureRefElement__Group__0 ) ) ;
    public final void ruleFigureRefElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:226:2: ( ( ( rule__FigureRefElement__Group__0 ) ) )
            // InternalMarkup.g:227:2: ( ( rule__FigureRefElement__Group__0 ) )
            {
            // InternalMarkup.g:227:2: ( ( rule__FigureRefElement__Group__0 ) )
            // InternalMarkup.g:228:3: ( rule__FigureRefElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getGroup());
            }
            // InternalMarkup.g:229:3: ( rule__FigureRefElement__Group__0 )
            // InternalMarkup.g:229:4: rule__FigureRefElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFigureRefElement"


    // $ANTLR start "entryRuleFootnoteElement"
    // InternalMarkup.g:238:1: entryRuleFootnoteElement : ruleFootnoteElement EOF ;
    public final void entryRuleFootnoteElement() throws RecognitionException {
        try {
            // InternalMarkup.g:239:1: ( ruleFootnoteElement EOF )
            // InternalMarkup.g:240:1: ruleFootnoteElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFootnoteElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFootnoteElement"


    // $ANTLR start "ruleFootnoteElement"
    // InternalMarkup.g:247:1: ruleFootnoteElement : ( ( rule__FootnoteElement__Group__0 ) ) ;
    public final void ruleFootnoteElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:251:2: ( ( ( rule__FootnoteElement__Group__0 ) ) )
            // InternalMarkup.g:252:2: ( ( rule__FootnoteElement__Group__0 ) )
            {
            // InternalMarkup.g:252:2: ( ( rule__FootnoteElement__Group__0 ) )
            // InternalMarkup.g:253:3: ( rule__FootnoteElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getGroup());
            }
            // InternalMarkup.g:254:3: ( rule__FootnoteElement__Group__0 )
            // InternalMarkup.g:254:4: rule__FootnoteElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFootnoteElement"


    // $ANTLR start "entryRuleHeadingElement"
    // InternalMarkup.g:263:1: entryRuleHeadingElement : ruleHeadingElement EOF ;
    public final void entryRuleHeadingElement() throws RecognitionException {
        try {
            // InternalMarkup.g:264:1: ( ruleHeadingElement EOF )
            // InternalMarkup.g:265:1: ruleHeadingElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleHeadingElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleHeadingElement"


    // $ANTLR start "ruleHeadingElement"
    // InternalMarkup.g:272:1: ruleHeadingElement : ( ( rule__HeadingElement__Group__0 ) ) ;
    public final void ruleHeadingElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:276:2: ( ( ( rule__HeadingElement__Group__0 ) ) )
            // InternalMarkup.g:277:2: ( ( rule__HeadingElement__Group__0 ) )
            {
            // InternalMarkup.g:277:2: ( ( rule__HeadingElement__Group__0 ) )
            // InternalMarkup.g:278:3: ( rule__HeadingElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getGroup());
            }
            // InternalMarkup.g:279:3: ( rule__HeadingElement__Group__0 )
            // InternalMarkup.g:279:4: rule__HeadingElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleHeadingElement"


    // $ANTLR start "entryRuleNewLineElement"
    // InternalMarkup.g:288:1: entryRuleNewLineElement : ruleNewLineElement EOF ;
    public final void entryRuleNewLineElement() throws RecognitionException {
        try {
            // InternalMarkup.g:289:1: ( ruleNewLineElement EOF )
            // InternalMarkup.g:290:1: ruleNewLineElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNewLineElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNewLineElement"


    // $ANTLR start "ruleNewLineElement"
    // InternalMarkup.g:297:1: ruleNewLineElement : ( ( rule__NewLineElement__TextAssignment ) ) ;
    public final void ruleNewLineElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:301:2: ( ( ( rule__NewLineElement__TextAssignment ) ) )
            // InternalMarkup.g:302:2: ( ( rule__NewLineElement__TextAssignment ) )
            {
            // InternalMarkup.g:302:2: ( ( rule__NewLineElement__TextAssignment ) )
            // InternalMarkup.g:303:3: ( rule__NewLineElement__TextAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineElementAccess().getTextAssignment());
            }
            // InternalMarkup.g:304:3: ( rule__NewLineElement__TextAssignment )
            // InternalMarkup.g:304:4: rule__NewLineElement__TextAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NewLineElement__TextAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineElementAccess().getTextAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNewLineElement"


    // $ANTLR start "entryRuleNullElement"
    // InternalMarkup.g:313:1: entryRuleNullElement : ruleNullElement EOF ;
    public final void entryRuleNullElement() throws RecognitionException {
        try {
            // InternalMarkup.g:314:1: ( ruleNullElement EOF )
            // InternalMarkup.g:315:1: ruleNullElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNullElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNullElement"


    // $ANTLR start "ruleNullElement"
    // InternalMarkup.g:322:1: ruleNullElement : ( ( rule__NullElement__Group__0 ) ) ;
    public final void ruleNullElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:326:2: ( ( ( rule__NullElement__Group__0 ) ) )
            // InternalMarkup.g:327:2: ( ( rule__NullElement__Group__0 ) )
            {
            // InternalMarkup.g:327:2: ( ( rule__NullElement__Group__0 ) )
            // InternalMarkup.g:328:3: ( rule__NullElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getGroup());
            }
            // InternalMarkup.g:329:3: ( rule__NullElement__Group__0 )
            // InternalMarkup.g:329:4: rule__NullElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NullElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNullElement"


    // $ANTLR start "entryRuleOCLCodeElement"
    // InternalMarkup.g:338:1: entryRuleOCLCodeElement : ruleOCLCodeElement EOF ;
    public final void entryRuleOCLCodeElement() throws RecognitionException {
        try {
            // InternalMarkup.g:339:1: ( ruleOCLCodeElement EOF )
            // InternalMarkup.g:340:1: ruleOCLCodeElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleOCLCodeElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOCLCodeElement"


    // $ANTLR start "ruleOCLCodeElement"
    // InternalMarkup.g:347:1: ruleOCLCodeElement : ( ( rule__OCLCodeElement__Group__0 ) ) ;
    public final void ruleOCLCodeElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:351:2: ( ( ( rule__OCLCodeElement__Group__0 ) ) )
            // InternalMarkup.g:352:2: ( ( rule__OCLCodeElement__Group__0 ) )
            {
            // InternalMarkup.g:352:2: ( ( rule__OCLCodeElement__Group__0 ) )
            // InternalMarkup.g:353:3: ( rule__OCLCodeElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getGroup());
            }
            // InternalMarkup.g:354:3: ( rule__OCLCodeElement__Group__0 )
            // InternalMarkup.g:354:4: rule__OCLCodeElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOCLCodeElement"


    // $ANTLR start "entryRuleOCLEvalElement"
    // InternalMarkup.g:363:1: entryRuleOCLEvalElement : ruleOCLEvalElement EOF ;
    public final void entryRuleOCLEvalElement() throws RecognitionException {
        try {
            // InternalMarkup.g:364:1: ( ruleOCLEvalElement EOF )
            // InternalMarkup.g:365:1: ruleOCLEvalElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleOCLEvalElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOCLEvalElement"


    // $ANTLR start "ruleOCLEvalElement"
    // InternalMarkup.g:372:1: ruleOCLEvalElement : ( ( rule__OCLEvalElement__Group__0 ) ) ;
    public final void ruleOCLEvalElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:376:2: ( ( ( rule__OCLEvalElement__Group__0 ) ) )
            // InternalMarkup.g:377:2: ( ( rule__OCLEvalElement__Group__0 ) )
            {
            // InternalMarkup.g:377:2: ( ( rule__OCLEvalElement__Group__0 ) )
            // InternalMarkup.g:378:3: ( rule__OCLEvalElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getGroup());
            }
            // InternalMarkup.g:379:3: ( rule__OCLEvalElement__Group__0 )
            // InternalMarkup.g:379:4: rule__OCLEvalElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOCLEvalElement"


    // $ANTLR start "entryRuleOCLTextElement"
    // InternalMarkup.g:388:1: entryRuleOCLTextElement : ruleOCLTextElement EOF ;
    public final void entryRuleOCLTextElement() throws RecognitionException {
        try {
            // InternalMarkup.g:389:1: ( ruleOCLTextElement EOF )
            // InternalMarkup.g:390:1: ruleOCLTextElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleOCLTextElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOCLTextElement"


    // $ANTLR start "ruleOCLTextElement"
    // InternalMarkup.g:397:1: ruleOCLTextElement : ( ( rule__OCLTextElement__Group__0 ) ) ;
    public final void ruleOCLTextElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:401:2: ( ( ( rule__OCLTextElement__Group__0 ) ) )
            // InternalMarkup.g:402:2: ( ( rule__OCLTextElement__Group__0 ) )
            {
            // InternalMarkup.g:402:2: ( ( rule__OCLTextElement__Group__0 ) )
            // InternalMarkup.g:403:3: ( rule__OCLTextElement__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getGroup());
            }
            // InternalMarkup.g:404:3: ( rule__OCLTextElement__Group__0 )
            // InternalMarkup.g:404:4: rule__OCLTextElement__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOCLTextElement"


    // $ANTLR start "entryRuleTextElement"
    // InternalMarkup.g:413:1: entryRuleTextElement : ruleTextElement EOF ;
    public final void entryRuleTextElement() throws RecognitionException {
        try {
            // InternalMarkup.g:414:1: ( ruleTextElement EOF )
            // InternalMarkup.g:415:1: ruleTextElement EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTextElementRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTextElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTextElementRule());
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTextElement"


    // $ANTLR start "ruleTextElement"
    // InternalMarkup.g:422:1: ruleTextElement : ( ( rule__TextElement__Alternatives ) ) ;
    public final void ruleTextElement() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:426:2: ( ( ( rule__TextElement__Alternatives ) ) )
            // InternalMarkup.g:427:2: ( ( rule__TextElement__Alternatives ) )
            {
            // InternalMarkup.g:427:2: ( ( rule__TextElement__Alternatives ) )
            // InternalMarkup.g:428:3: ( rule__TextElement__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTextElementAccess().getAlternatives());
            }
            // InternalMarkup.g:429:3: ( rule__TextElement__Alternatives )
            // InternalMarkup.g:429:4: rule__TextElement__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TextElement__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTextElementAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTextElement"


    // $ANTLR start "rule__MarkupKeyword__Alternatives"
    // InternalMarkup.g:437:1: rule__MarkupKeyword__Alternatives : ( ( 'b' ) | ( 'e' ) | ( 'bullet' ) | ( 'figure' ) | ( 'figureRef' ) | ( 'footnote' ) | ( 'heading' ) | ( 'oclCode' ) | ( 'oclEval' ) | ( 'oclText' ) );
    public final void rule__MarkupKeyword__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:441:1: ( ( 'b' ) | ( 'e' ) | ( 'bullet' ) | ( 'figure' ) | ( 'figureRef' ) | ( 'footnote' ) | ( 'heading' ) | ( 'oclCode' ) | ( 'oclEval' ) | ( 'oclText' ) )
            int alt2=10;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt2=1;
                }
                break;
            case 17:
                {
                alt2=2;
                }
                break;
            case 18:
                {
                alt2=3;
                }
                break;
            case 19:
                {
                alt2=4;
                }
                break;
            case 20:
                {
                alt2=5;
                }
                break;
            case 21:
                {
                alt2=6;
                }
                break;
            case 22:
                {
                alt2=7;
                }
                break;
            case 23:
                {
                alt2=8;
                }
                break;
            case 24:
                {
                alt2=9;
                }
                break;
            case 25:
                {
                alt2=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalMarkup.g:442:2: ( 'b' )
                    {
                    // InternalMarkup.g:442:2: ( 'b' )
                    // InternalMarkup.g:443:3: 'b'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getBKeyword_0());
                    }
                    match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getBKeyword_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalMarkup.g:448:2: ( 'e' )
                    {
                    // InternalMarkup.g:448:2: ( 'e' )
                    // InternalMarkup.g:449:3: 'e'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getEKeyword_1());
                    }
                    match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getEKeyword_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalMarkup.g:454:2: ( 'bullet' )
                    {
                    // InternalMarkup.g:454:2: ( 'bullet' )
                    // InternalMarkup.g:455:3: 'bullet'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getBulletKeyword_2());
                    }
                    match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getBulletKeyword_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalMarkup.g:460:2: ( 'figure' )
                    {
                    // InternalMarkup.g:460:2: ( 'figure' )
                    // InternalMarkup.g:461:3: 'figure'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getFigureKeyword_3());
                    }
                    match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getFigureKeyword_3());
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalMarkup.g:466:2: ( 'figureRef' )
                    {
                    // InternalMarkup.g:466:2: ( 'figureRef' )
                    // InternalMarkup.g:467:3: 'figureRef'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getFigureRefKeyword_4());
                    }
                    match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getFigureRefKeyword_4());
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalMarkup.g:472:2: ( 'footnote' )
                    {
                    // InternalMarkup.g:472:2: ( 'footnote' )
                    // InternalMarkup.g:473:3: 'footnote'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getFootnoteKeyword_5());
                    }
                    match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getFootnoteKeyword_5());
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalMarkup.g:478:2: ( 'heading' )
                    {
                    // InternalMarkup.g:478:2: ( 'heading' )
                    // InternalMarkup.g:479:3: 'heading'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getHeadingKeyword_6());
                    }
                    match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getHeadingKeyword_6());
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalMarkup.g:484:2: ( 'oclCode' )
                    {
                    // InternalMarkup.g:484:2: ( 'oclCode' )
                    // InternalMarkup.g:485:3: 'oclCode'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getOclCodeKeyword_7());
                    }
                    match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getOclCodeKeyword_7());
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalMarkup.g:490:2: ( 'oclEval' )
                    {
                    // InternalMarkup.g:490:2: ( 'oclEval' )
                    // InternalMarkup.g:491:3: 'oclEval'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getOclEvalKeyword_8());
                    }
                    match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getOclEvalKeyword_8());
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalMarkup.g:496:2: ( 'oclText' )
                    {
                    // InternalMarkup.g:496:2: ( 'oclText' )
                    // InternalMarkup.g:497:3: 'oclText'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupKeywordAccess().getOclTextKeyword_9());
                    }
                    match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupKeywordAccess().getOclTextKeyword_9());
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MarkupKeyword__Alternatives"


    // $ANTLR start "rule__MarkupElement__Alternatives"
    // InternalMarkup.g:506:1: rule__MarkupElement__Alternatives : ( ( ruleFontElement ) | ( ruleNewLineElement ) | ( ruleBulletElement ) | ( ruleFigureElement ) | ( ruleFigureRefElement ) | ( ruleFootnoteElement ) | ( ruleHeadingElement ) | ( ruleNullElement ) | ( ruleOCLCodeElement ) | ( ruleOCLEvalElement ) | ( ruleOCLTextElement ) | ( ruleTextElement ) );
    public final void rule__MarkupElement__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:510:1: ( ( ruleFontElement ) | ( ruleNewLineElement ) | ( ruleBulletElement ) | ( ruleFigureElement ) | ( ruleFigureRefElement ) | ( ruleFootnoteElement ) | ( ruleHeadingElement ) | ( ruleNullElement ) | ( ruleOCLCodeElement ) | ( ruleOCLEvalElement ) | ( ruleOCLTextElement ) | ( ruleTextElement ) )
            int alt3=12;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // InternalMarkup.g:511:2: ( ruleFontElement )
                    {
                    // InternalMarkup.g:511:2: ( ruleFontElement )
                    // InternalMarkup.g:512:3: ruleFontElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getFontElementParserRuleCall_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFontElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getFontElementParserRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalMarkup.g:517:2: ( ruleNewLineElement )
                    {
                    // InternalMarkup.g:517:2: ( ruleNewLineElement )
                    // InternalMarkup.g:518:3: ruleNewLineElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getNewLineElementParserRuleCall_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleNewLineElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getNewLineElementParserRuleCall_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalMarkup.g:523:2: ( ruleBulletElement )
                    {
                    // InternalMarkup.g:523:2: ( ruleBulletElement )
                    // InternalMarkup.g:524:3: ruleBulletElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getBulletElementParserRuleCall_2());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleBulletElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getBulletElementParserRuleCall_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalMarkup.g:529:2: ( ruleFigureElement )
                    {
                    // InternalMarkup.g:529:2: ( ruleFigureElement )
                    // InternalMarkup.g:530:3: ruleFigureElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getFigureElementParserRuleCall_3());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFigureElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getFigureElementParserRuleCall_3());
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalMarkup.g:535:2: ( ruleFigureRefElement )
                    {
                    // InternalMarkup.g:535:2: ( ruleFigureRefElement )
                    // InternalMarkup.g:536:3: ruleFigureRefElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getFigureRefElementParserRuleCall_4());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFigureRefElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getFigureRefElementParserRuleCall_4());
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalMarkup.g:541:2: ( ruleFootnoteElement )
                    {
                    // InternalMarkup.g:541:2: ( ruleFootnoteElement )
                    // InternalMarkup.g:542:3: ruleFootnoteElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getFootnoteElementParserRuleCall_5());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFootnoteElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getFootnoteElementParserRuleCall_5());
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalMarkup.g:547:2: ( ruleHeadingElement )
                    {
                    // InternalMarkup.g:547:2: ( ruleHeadingElement )
                    // InternalMarkup.g:548:3: ruleHeadingElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getHeadingElementParserRuleCall_6());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleHeadingElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getHeadingElementParserRuleCall_6());
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalMarkup.g:553:2: ( ruleNullElement )
                    {
                    // InternalMarkup.g:553:2: ( ruleNullElement )
                    // InternalMarkup.g:554:3: ruleNullElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getNullElementParserRuleCall_7());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleNullElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getNullElementParserRuleCall_7());
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalMarkup.g:559:2: ( ruleOCLCodeElement )
                    {
                    // InternalMarkup.g:559:2: ( ruleOCLCodeElement )
                    // InternalMarkup.g:560:3: ruleOCLCodeElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getOCLCodeElementParserRuleCall_8());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleOCLCodeElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getOCLCodeElementParserRuleCall_8());
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalMarkup.g:565:2: ( ruleOCLEvalElement )
                    {
                    // InternalMarkup.g:565:2: ( ruleOCLEvalElement )
                    // InternalMarkup.g:566:3: ruleOCLEvalElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getOCLEvalElementParserRuleCall_9());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleOCLEvalElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getOCLEvalElementParserRuleCall_9());
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalMarkup.g:571:2: ( ruleOCLTextElement )
                    {
                    // InternalMarkup.g:571:2: ( ruleOCLTextElement )
                    // InternalMarkup.g:572:3: ruleOCLTextElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getOCLTextElementParserRuleCall_10());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleOCLTextElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getOCLTextElementParserRuleCall_10());
                    }

                    }


                    }
                    break;
                case 12 :
                    // InternalMarkup.g:577:2: ( ruleTextElement )
                    {
                    // InternalMarkup.g:577:2: ( ruleTextElement )
                    // InternalMarkup.g:578:3: ruleTextElement
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMarkupElementAccess().getTextElementParserRuleCall_11());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleTextElement();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMarkupElementAccess().getTextElementParserRuleCall_11());
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MarkupElement__Alternatives"


    // $ANTLR start "rule__FontElement__FontAlternatives_0_0"
    // InternalMarkup.g:587:1: rule__FontElement__FontAlternatives_0_0 : ( ( 'b' ) | ( 'e' ) );
    public final void rule__FontElement__FontAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:591:1: ( ( 'b' ) | ( 'e' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            else if ( (LA4_0==17) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalMarkup.g:592:2: ( 'b' )
                    {
                    // InternalMarkup.g:592:2: ( 'b' )
                    // InternalMarkup.g:593:3: 'b'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFontElementAccess().getFontBKeyword_0_0_0());
                    }
                    match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFontElementAccess().getFontBKeyword_0_0_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalMarkup.g:598:2: ( 'e' )
                    {
                    // InternalMarkup.g:598:2: ( 'e' )
                    // InternalMarkup.g:599:3: 'e'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getFontElementAccess().getFontEKeyword_0_0_1());
                    }
                    match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getFontElementAccess().getFontEKeyword_0_0_1());
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__FontAlternatives_0_0"


    // $ANTLR start "rule__TextElement__Alternatives"
    // InternalMarkup.g:608:1: rule__TextElement__Alternatives : ( ( ( ( rule__TextElement__TextAssignment_0 ) ) ( ( rule__TextElement__TextAssignment_0 )* ) ) | ( ( rule__TextElement__TextAssignment_1 ) ) );
    public final void rule__TextElement__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:612:1: ( ( ( ( rule__TextElement__TextAssignment_0 ) ) ( ( rule__TextElement__TextAssignment_0 )* ) ) | ( ( rule__TextElement__TextAssignment_1 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=RULE_ID && LA6_0<=RULE_WS)||(LA6_0>=26 && LA6_0<=28)) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=16 && LA6_0<=25)) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMarkup.g:613:2: ( ( ( rule__TextElement__TextAssignment_0 ) ) ( ( rule__TextElement__TextAssignment_0 )* ) )
                    {
                    // InternalMarkup.g:613:2: ( ( ( rule__TextElement__TextAssignment_0 ) ) ( ( rule__TextElement__TextAssignment_0 )* ) )
                    // InternalMarkup.g:614:3: ( ( rule__TextElement__TextAssignment_0 ) ) ( ( rule__TextElement__TextAssignment_0 )* )
                    {
                    // InternalMarkup.g:614:3: ( ( rule__TextElement__TextAssignment_0 ) )
                    // InternalMarkup.g:615:4: ( rule__TextElement__TextAssignment_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextAssignment_0());
                    }
                    // InternalMarkup.g:616:4: ( rule__TextElement__TextAssignment_0 )
                    // InternalMarkup.g:616:5: rule__TextElement__TextAssignment_0
                    {
                    pushFollow(FollowSets000.FOLLOW_4);
                    rule__TextElement__TextAssignment_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextAssignment_0());
                    }

                    }

                    // InternalMarkup.g:619:3: ( ( rule__TextElement__TextAssignment_0 )* )
                    // InternalMarkup.g:620:4: ( rule__TextElement__TextAssignment_0 )*
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextAssignment_0());
                    }
                    // InternalMarkup.g:621:4: ( rule__TextElement__TextAssignment_0 )*
                    loop5:
                    do {
                        int alt5=2;
                        alt5 = dfa5.predict(input);
                        switch (alt5) {
                    	case 1 :
                    	    // InternalMarkup.g:621:5: rule__TextElement__TextAssignment_0
                    	    {
                    	    pushFollow(FollowSets000.FOLLOW_4);
                    	    rule__TextElement__TextAssignment_0();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextAssignment_0());
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMarkup.g:626:2: ( ( rule__TextElement__TextAssignment_1 ) )
                    {
                    // InternalMarkup.g:626:2: ( ( rule__TextElement__TextAssignment_1 ) )
                    // InternalMarkup.g:627:3: ( rule__TextElement__TextAssignment_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextAssignment_1());
                    }
                    // InternalMarkup.g:628:3: ( rule__TextElement__TextAssignment_1 )
                    // InternalMarkup.g:628:4: rule__TextElement__TextAssignment_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__TextElement__TextAssignment_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextAssignment_1());
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TextElement__Alternatives"


    // $ANTLR start "rule__TextElement__TextAlternatives_0_0"
    // InternalMarkup.g:636:1: rule__TextElement__TextAlternatives_0_0 : ( ( RULE_ID ) | ( RULE_WORD ) | ( RULE_INT ) | ( RULE_WS ) | ( ':' ) | ( '#' ) | ( ',' ) );
    public final void rule__TextElement__TextAlternatives_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:640:1: ( ( RULE_ID ) | ( RULE_WORD ) | ( RULE_INT ) | ( RULE_WS ) | ( ':' ) | ( '#' ) | ( ',' ) )
            int alt7=7;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt7=1;
                }
                break;
            case RULE_WORD:
                {
                alt7=2;
                }
                break;
            case RULE_INT:
                {
                alt7=3;
                }
                break;
            case RULE_WS:
                {
                alt7=4;
                }
                break;
            case 26:
                {
                alt7=5;
                }
                break;
            case 27:
                {
                alt7=6;
                }
                break;
            case 28:
                {
                alt7=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalMarkup.g:641:2: ( RULE_ID )
                    {
                    // InternalMarkup.g:641:2: ( RULE_ID )
                    // InternalMarkup.g:642:3: RULE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextIDTerminalRuleCall_0_0_0());
                    }
                    match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextIDTerminalRuleCall_0_0_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalMarkup.g:647:2: ( RULE_WORD )
                    {
                    // InternalMarkup.g:647:2: ( RULE_WORD )
                    // InternalMarkup.g:648:3: RULE_WORD
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextWORDTerminalRuleCall_0_0_1());
                    }
                    match(input,RULE_WORD,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextWORDTerminalRuleCall_0_0_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalMarkup.g:653:2: ( RULE_INT )
                    {
                    // InternalMarkup.g:653:2: ( RULE_INT )
                    // InternalMarkup.g:654:3: RULE_INT
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextINTTerminalRuleCall_0_0_2());
                    }
                    match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextINTTerminalRuleCall_0_0_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalMarkup.g:659:2: ( RULE_WS )
                    {
                    // InternalMarkup.g:659:2: ( RULE_WS )
                    // InternalMarkup.g:660:3: RULE_WS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextWSTerminalRuleCall_0_0_3());
                    }
                    match(input,RULE_WS,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextWSTerminalRuleCall_0_0_3());
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalMarkup.g:665:2: ( ':' )
                    {
                    // InternalMarkup.g:665:2: ( ':' )
                    // InternalMarkup.g:666:3: ':'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextColonKeyword_0_0_4());
                    }
                    match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextColonKeyword_0_0_4());
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalMarkup.g:671:2: ( '#' )
                    {
                    // InternalMarkup.g:671:2: ( '#' )
                    // InternalMarkup.g:672:3: '#'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextNumberSignKeyword_0_0_5());
                    }
                    match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextNumberSignKeyword_0_0_5());
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalMarkup.g:677:2: ( ',' )
                    {
                    // InternalMarkup.g:677:2: ( ',' )
                    // InternalMarkup.g:678:3: ','
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTextElementAccess().getTextCommaKeyword_0_0_6());
                    }
                    match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTextElementAccess().getTextCommaKeyword_0_0_6());
                    }

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TextElement__TextAlternatives_0_0"


    // $ANTLR start "rule__BulletElement__Group__0"
    // InternalMarkup.g:687:1: rule__BulletElement__Group__0 : rule__BulletElement__Group__0__Impl rule__BulletElement__Group__1 ;
    public final void rule__BulletElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:691:1: ( rule__BulletElement__Group__0__Impl rule__BulletElement__Group__1 )
            // InternalMarkup.g:692:2: rule__BulletElement__Group__0__Impl rule__BulletElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_5);
            rule__BulletElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__0"


    // $ANTLR start "rule__BulletElement__Group__0__Impl"
    // InternalMarkup.g:699:1: rule__BulletElement__Group__0__Impl : ( () ) ;
    public final void rule__BulletElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:703:1: ( ( () ) )
            // InternalMarkup.g:704:1: ( () )
            {
            // InternalMarkup.g:704:1: ( () )
            // InternalMarkup.g:705:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getBulletElementAction_0());
            }
            // InternalMarkup.g:706:2: ()
            // InternalMarkup.g:706:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getBulletElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__0__Impl"


    // $ANTLR start "rule__BulletElement__Group__1"
    // InternalMarkup.g:714:1: rule__BulletElement__Group__1 : rule__BulletElement__Group__1__Impl rule__BulletElement__Group__2 ;
    public final void rule__BulletElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:718:1: ( rule__BulletElement__Group__1__Impl rule__BulletElement__Group__2 )
            // InternalMarkup.g:719:2: rule__BulletElement__Group__1__Impl rule__BulletElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__BulletElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__1"


    // $ANTLR start "rule__BulletElement__Group__1__Impl"
    // InternalMarkup.g:726:1: rule__BulletElement__Group__1__Impl : ( 'bullet' ) ;
    public final void rule__BulletElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:730:1: ( ( 'bullet' ) )
            // InternalMarkup.g:731:1: ( 'bullet' )
            {
            // InternalMarkup.g:731:1: ( 'bullet' )
            // InternalMarkup.g:732:2: 'bullet'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getBulletKeyword_1());
            }
            match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getBulletKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__1__Impl"


    // $ANTLR start "rule__BulletElement__Group__2"
    // InternalMarkup.g:741:1: rule__BulletElement__Group__2 : rule__BulletElement__Group__2__Impl rule__BulletElement__Group__3 ;
    public final void rule__BulletElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:745:1: ( rule__BulletElement__Group__2__Impl rule__BulletElement__Group__3 )
            // InternalMarkup.g:746:2: rule__BulletElement__Group__2__Impl rule__BulletElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__BulletElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__2"


    // $ANTLR start "rule__BulletElement__Group__2__Impl"
    // InternalMarkup.g:753:1: rule__BulletElement__Group__2__Impl : ( ( rule__BulletElement__Group_2__0 )? ) ;
    public final void rule__BulletElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:757:1: ( ( ( rule__BulletElement__Group_2__0 )? ) )
            // InternalMarkup.g:758:1: ( ( rule__BulletElement__Group_2__0 )? )
            {
            // InternalMarkup.g:758:1: ( ( rule__BulletElement__Group_2__0 )? )
            // InternalMarkup.g:759:2: ( rule__BulletElement__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getGroup_2());
            }
            // InternalMarkup.g:760:2: ( rule__BulletElement__Group_2__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==26) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalMarkup.g:760:3: rule__BulletElement__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__BulletElement__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getGroup_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__2__Impl"


    // $ANTLR start "rule__BulletElement__Group__3"
    // InternalMarkup.g:768:1: rule__BulletElement__Group__3 : rule__BulletElement__Group__3__Impl rule__BulletElement__Group__4 ;
    public final void rule__BulletElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:772:1: ( rule__BulletElement__Group__3__Impl rule__BulletElement__Group__4 )
            // InternalMarkup.g:773:2: rule__BulletElement__Group__3__Impl rule__BulletElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__BulletElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__3"


    // $ANTLR start "rule__BulletElement__Group__3__Impl"
    // InternalMarkup.g:780:1: rule__BulletElement__Group__3__Impl : ( '[' ) ;
    public final void rule__BulletElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:784:1: ( ( '[' ) )
            // InternalMarkup.g:785:1: ( '[' )
            {
            // InternalMarkup.g:785:1: ( '[' )
            // InternalMarkup.g:786:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getLeftSquareBracketKeyword_3());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getLeftSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__3__Impl"


    // $ANTLR start "rule__BulletElement__Group__4"
    // InternalMarkup.g:795:1: rule__BulletElement__Group__4 : rule__BulletElement__Group__4__Impl rule__BulletElement__Group__5 ;
    public final void rule__BulletElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:799:1: ( rule__BulletElement__Group__4__Impl rule__BulletElement__Group__5 )
            // InternalMarkup.g:800:2: rule__BulletElement__Group__4__Impl rule__BulletElement__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__BulletElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__4"


    // $ANTLR start "rule__BulletElement__Group__4__Impl"
    // InternalMarkup.g:807:1: rule__BulletElement__Group__4__Impl : ( ( rule__BulletElement__ElementsAssignment_4 )* ) ;
    public final void rule__BulletElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:811:1: ( ( ( rule__BulletElement__ElementsAssignment_4 )* ) )
            // InternalMarkup.g:812:1: ( ( rule__BulletElement__ElementsAssignment_4 )* )
            {
            // InternalMarkup.g:812:1: ( ( rule__BulletElement__ElementsAssignment_4 )* )
            // InternalMarkup.g:813:2: ( rule__BulletElement__ElementsAssignment_4 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getElementsAssignment_4());
            }
            // InternalMarkup.g:814:2: ( rule__BulletElement__ElementsAssignment_4 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_WS)||LA9_0==RULE_NL||(LA9_0>=16 && LA9_0<=29)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalMarkup.g:814:3: rule__BulletElement__ElementsAssignment_4
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__BulletElement__ElementsAssignment_4();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getElementsAssignment_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__4__Impl"


    // $ANTLR start "rule__BulletElement__Group__5"
    // InternalMarkup.g:822:1: rule__BulletElement__Group__5 : rule__BulletElement__Group__5__Impl ;
    public final void rule__BulletElement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:826:1: ( rule__BulletElement__Group__5__Impl )
            // InternalMarkup.g:827:2: rule__BulletElement__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__5"


    // $ANTLR start "rule__BulletElement__Group__5__Impl"
    // InternalMarkup.g:833:1: rule__BulletElement__Group__5__Impl : ( ']' ) ;
    public final void rule__BulletElement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:837:1: ( ( ']' ) )
            // InternalMarkup.g:838:1: ( ']' )
            {
            // InternalMarkup.g:838:1: ( ']' )
            // InternalMarkup.g:839:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getRightSquareBracketKeyword_5());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getRightSquareBracketKeyword_5());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group__5__Impl"


    // $ANTLR start "rule__BulletElement__Group_2__0"
    // InternalMarkup.g:849:1: rule__BulletElement__Group_2__0 : rule__BulletElement__Group_2__0__Impl rule__BulletElement__Group_2__1 ;
    public final void rule__BulletElement__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:853:1: ( rule__BulletElement__Group_2__0__Impl rule__BulletElement__Group_2__1 )
            // InternalMarkup.g:854:2: rule__BulletElement__Group_2__0__Impl rule__BulletElement__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__BulletElement__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group_2__0"


    // $ANTLR start "rule__BulletElement__Group_2__0__Impl"
    // InternalMarkup.g:861:1: rule__BulletElement__Group_2__0__Impl : ( ':' ) ;
    public final void rule__BulletElement__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:865:1: ( ( ':' ) )
            // InternalMarkup.g:866:1: ( ':' )
            {
            // InternalMarkup.g:866:1: ( ':' )
            // InternalMarkup.g:867:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getColonKeyword_2_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getColonKeyword_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group_2__0__Impl"


    // $ANTLR start "rule__BulletElement__Group_2__1"
    // InternalMarkup.g:876:1: rule__BulletElement__Group_2__1 : rule__BulletElement__Group_2__1__Impl ;
    public final void rule__BulletElement__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:880:1: ( rule__BulletElement__Group_2__1__Impl )
            // InternalMarkup.g:881:2: rule__BulletElement__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group_2__1"


    // $ANTLR start "rule__BulletElement__Group_2__1__Impl"
    // InternalMarkup.g:887:1: rule__BulletElement__Group_2__1__Impl : ( ( rule__BulletElement__LevelAssignment_2_1 ) ) ;
    public final void rule__BulletElement__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:891:1: ( ( ( rule__BulletElement__LevelAssignment_2_1 ) ) )
            // InternalMarkup.g:892:1: ( ( rule__BulletElement__LevelAssignment_2_1 ) )
            {
            // InternalMarkup.g:892:1: ( ( rule__BulletElement__LevelAssignment_2_1 ) )
            // InternalMarkup.g:893:2: ( rule__BulletElement__LevelAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getLevelAssignment_2_1());
            }
            // InternalMarkup.g:894:2: ( rule__BulletElement__LevelAssignment_2_1 )
            // InternalMarkup.g:894:3: rule__BulletElement__LevelAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__BulletElement__LevelAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getLevelAssignment_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__Group_2__1__Impl"


    // $ANTLR start "rule__FontElement__Group__0"
    // InternalMarkup.g:903:1: rule__FontElement__Group__0 : rule__FontElement__Group__0__Impl rule__FontElement__Group__1 ;
    public final void rule__FontElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:907:1: ( rule__FontElement__Group__0__Impl rule__FontElement__Group__1 )
            // InternalMarkup.g:908:2: rule__FontElement__Group__0__Impl rule__FontElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__FontElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__0"


    // $ANTLR start "rule__FontElement__Group__0__Impl"
    // InternalMarkup.g:915:1: rule__FontElement__Group__0__Impl : ( ( rule__FontElement__FontAssignment_0 ) ) ;
    public final void rule__FontElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:919:1: ( ( ( rule__FontElement__FontAssignment_0 ) ) )
            // InternalMarkup.g:920:1: ( ( rule__FontElement__FontAssignment_0 ) )
            {
            // InternalMarkup.g:920:1: ( ( rule__FontElement__FontAssignment_0 ) )
            // InternalMarkup.g:921:2: ( rule__FontElement__FontAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getFontAssignment_0());
            }
            // InternalMarkup.g:922:2: ( rule__FontElement__FontAssignment_0 )
            // InternalMarkup.g:922:3: rule__FontElement__FontAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__FontAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getFontAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__0__Impl"


    // $ANTLR start "rule__FontElement__Group__1"
    // InternalMarkup.g:930:1: rule__FontElement__Group__1 : rule__FontElement__Group__1__Impl rule__FontElement__Group__2 ;
    public final void rule__FontElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:934:1: ( rule__FontElement__Group__1__Impl rule__FontElement__Group__2 )
            // InternalMarkup.g:935:2: rule__FontElement__Group__1__Impl rule__FontElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__FontElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__1"


    // $ANTLR start "rule__FontElement__Group__1__Impl"
    // InternalMarkup.g:942:1: rule__FontElement__Group__1__Impl : ( '[' ) ;
    public final void rule__FontElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:946:1: ( ( '[' ) )
            // InternalMarkup.g:947:1: ( '[' )
            {
            // InternalMarkup.g:947:1: ( '[' )
            // InternalMarkup.g:948:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getLeftSquareBracketKeyword_1());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getLeftSquareBracketKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__1__Impl"


    // $ANTLR start "rule__FontElement__Group__2"
    // InternalMarkup.g:957:1: rule__FontElement__Group__2 : rule__FontElement__Group__2__Impl rule__FontElement__Group__3 ;
    public final void rule__FontElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:961:1: ( rule__FontElement__Group__2__Impl rule__FontElement__Group__3 )
            // InternalMarkup.g:962:2: rule__FontElement__Group__2__Impl rule__FontElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__FontElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__2"


    // $ANTLR start "rule__FontElement__Group__2__Impl"
    // InternalMarkup.g:969:1: rule__FontElement__Group__2__Impl : ( ( rule__FontElement__ElementsAssignment_2 )* ) ;
    public final void rule__FontElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:973:1: ( ( ( rule__FontElement__ElementsAssignment_2 )* ) )
            // InternalMarkup.g:974:1: ( ( rule__FontElement__ElementsAssignment_2 )* )
            {
            // InternalMarkup.g:974:1: ( ( rule__FontElement__ElementsAssignment_2 )* )
            // InternalMarkup.g:975:2: ( rule__FontElement__ElementsAssignment_2 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getElementsAssignment_2());
            }
            // InternalMarkup.g:976:2: ( rule__FontElement__ElementsAssignment_2 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_WS)||LA10_0==RULE_NL||(LA10_0>=16 && LA10_0<=29)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalMarkup.g:976:3: rule__FontElement__ElementsAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__FontElement__ElementsAssignment_2();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getElementsAssignment_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__2__Impl"


    // $ANTLR start "rule__FontElement__Group__3"
    // InternalMarkup.g:984:1: rule__FontElement__Group__3 : rule__FontElement__Group__3__Impl ;
    public final void rule__FontElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:988:1: ( rule__FontElement__Group__3__Impl )
            // InternalMarkup.g:989:2: rule__FontElement__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__3"


    // $ANTLR start "rule__FontElement__Group__3__Impl"
    // InternalMarkup.g:995:1: rule__FontElement__Group__3__Impl : ( ']' ) ;
    public final void rule__FontElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:999:1: ( ( ']' ) )
            // InternalMarkup.g:1000:1: ( ']' )
            {
            // InternalMarkup.g:1000:1: ( ']' )
            // InternalMarkup.g:1001:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getRightSquareBracketKeyword_3());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getRightSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__Group__3__Impl"


    // $ANTLR start "rule__FigureElement__Group__0"
    // InternalMarkup.g:1011:1: rule__FigureElement__Group__0 : rule__FigureElement__Group__0__Impl rule__FigureElement__Group__1 ;
    public final void rule__FigureElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1015:1: ( rule__FigureElement__Group__0__Impl rule__FigureElement__Group__1 )
            // InternalMarkup.g:1016:2: rule__FigureElement__Group__0__Impl rule__FigureElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__FigureElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__0"


    // $ANTLR start "rule__FigureElement__Group__0__Impl"
    // InternalMarkup.g:1023:1: rule__FigureElement__Group__0__Impl : ( 'figure' ) ;
    public final void rule__FigureElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1027:1: ( ( 'figure' ) )
            // InternalMarkup.g:1028:1: ( 'figure' )
            {
            // InternalMarkup.g:1028:1: ( 'figure' )
            // InternalMarkup.g:1029:2: 'figure'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getFigureKeyword_0());
            }
            match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getFigureKeyword_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__0__Impl"


    // $ANTLR start "rule__FigureElement__Group__1"
    // InternalMarkup.g:1038:1: rule__FigureElement__Group__1 : rule__FigureElement__Group__1__Impl rule__FigureElement__Group__2 ;
    public final void rule__FigureElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1042:1: ( rule__FigureElement__Group__1__Impl rule__FigureElement__Group__2 )
            // InternalMarkup.g:1043:2: rule__FigureElement__Group__1__Impl rule__FigureElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__FigureElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__1"


    // $ANTLR start "rule__FigureElement__Group__1__Impl"
    // InternalMarkup.g:1050:1: rule__FigureElement__Group__1__Impl : ( ( rule__FigureElement__Group_1__0 )? ) ;
    public final void rule__FigureElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1054:1: ( ( ( rule__FigureElement__Group_1__0 )? ) )
            // InternalMarkup.g:1055:1: ( ( rule__FigureElement__Group_1__0 )? )
            {
            // InternalMarkup.g:1055:1: ( ( rule__FigureElement__Group_1__0 )? )
            // InternalMarkup.g:1056:2: ( rule__FigureElement__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getGroup_1());
            }
            // InternalMarkup.g:1057:2: ( rule__FigureElement__Group_1__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==27) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMarkup.g:1057:3: rule__FigureElement__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__FigureElement__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__1__Impl"


    // $ANTLR start "rule__FigureElement__Group__2"
    // InternalMarkup.g:1065:1: rule__FigureElement__Group__2 : rule__FigureElement__Group__2__Impl rule__FigureElement__Group__3 ;
    public final void rule__FigureElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1069:1: ( rule__FigureElement__Group__2__Impl rule__FigureElement__Group__3 )
            // InternalMarkup.g:1070:2: rule__FigureElement__Group__2__Impl rule__FigureElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__FigureElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__2"


    // $ANTLR start "rule__FigureElement__Group__2__Impl"
    // InternalMarkup.g:1077:1: rule__FigureElement__Group__2__Impl : ( '[' ) ;
    public final void rule__FigureElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1081:1: ( ( '[' ) )
            // InternalMarkup.g:1082:1: ( '[' )
            {
            // InternalMarkup.g:1082:1: ( '[' )
            // InternalMarkup.g:1083:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getLeftSquareBracketKeyword_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getLeftSquareBracketKeyword_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__2__Impl"


    // $ANTLR start "rule__FigureElement__Group__3"
    // InternalMarkup.g:1092:1: rule__FigureElement__Group__3 : rule__FigureElement__Group__3__Impl rule__FigureElement__Group__4 ;
    public final void rule__FigureElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1096:1: ( rule__FigureElement__Group__3__Impl rule__FigureElement__Group__4 )
            // InternalMarkup.g:1097:2: rule__FigureElement__Group__3__Impl rule__FigureElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__FigureElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__3"


    // $ANTLR start "rule__FigureElement__Group__3__Impl"
    // InternalMarkup.g:1104:1: rule__FigureElement__Group__3__Impl : ( ( rule__FigureElement__SrcAssignment_3 ) ) ;
    public final void rule__FigureElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1108:1: ( ( ( rule__FigureElement__SrcAssignment_3 ) ) )
            // InternalMarkup.g:1109:1: ( ( rule__FigureElement__SrcAssignment_3 ) )
            {
            // InternalMarkup.g:1109:1: ( ( rule__FigureElement__SrcAssignment_3 ) )
            // InternalMarkup.g:1110:2: ( rule__FigureElement__SrcAssignment_3 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getSrcAssignment_3());
            }
            // InternalMarkup.g:1111:2: ( rule__FigureElement__SrcAssignment_3 )
            // InternalMarkup.g:1111:3: rule__FigureElement__SrcAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__SrcAssignment_3();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getSrcAssignment_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__3__Impl"


    // $ANTLR start "rule__FigureElement__Group__4"
    // InternalMarkup.g:1119:1: rule__FigureElement__Group__4 : rule__FigureElement__Group__4__Impl rule__FigureElement__Group__5 ;
    public final void rule__FigureElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1123:1: ( rule__FigureElement__Group__4__Impl rule__FigureElement__Group__5 )
            // InternalMarkup.g:1124:2: rule__FigureElement__Group__4__Impl rule__FigureElement__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__FigureElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__4"


    // $ANTLR start "rule__FigureElement__Group__4__Impl"
    // InternalMarkup.g:1131:1: rule__FigureElement__Group__4__Impl : ( ( rule__FigureElement__Group_4__0 )? ) ;
    public final void rule__FigureElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1135:1: ( ( ( rule__FigureElement__Group_4__0 )? ) )
            // InternalMarkup.g:1136:1: ( ( rule__FigureElement__Group_4__0 )? )
            {
            // InternalMarkup.g:1136:1: ( ( rule__FigureElement__Group_4__0 )? )
            // InternalMarkup.g:1137:2: ( rule__FigureElement__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getGroup_4());
            }
            // InternalMarkup.g:1138:2: ( rule__FigureElement__Group_4__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==28) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalMarkup.g:1138:3: rule__FigureElement__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__FigureElement__Group_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getGroup_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__4__Impl"


    // $ANTLR start "rule__FigureElement__Group__5"
    // InternalMarkup.g:1146:1: rule__FigureElement__Group__5 : rule__FigureElement__Group__5__Impl ;
    public final void rule__FigureElement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1150:1: ( rule__FigureElement__Group__5__Impl )
            // InternalMarkup.g:1151:2: rule__FigureElement__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__5"


    // $ANTLR start "rule__FigureElement__Group__5__Impl"
    // InternalMarkup.g:1157:1: rule__FigureElement__Group__5__Impl : ( ']' ) ;
    public final void rule__FigureElement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1161:1: ( ( ']' ) )
            // InternalMarkup.g:1162:1: ( ']' )
            {
            // InternalMarkup.g:1162:1: ( ']' )
            // InternalMarkup.g:1163:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getRightSquareBracketKeyword_5());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getRightSquareBracketKeyword_5());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group__5__Impl"


    // $ANTLR start "rule__FigureElement__Group_1__0"
    // InternalMarkup.g:1173:1: rule__FigureElement__Group_1__0 : rule__FigureElement__Group_1__0__Impl rule__FigureElement__Group_1__1 ;
    public final void rule__FigureElement__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1177:1: ( rule__FigureElement__Group_1__0__Impl rule__FigureElement__Group_1__1 )
            // InternalMarkup.g:1178:2: rule__FigureElement__Group_1__0__Impl rule__FigureElement__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_13);
            rule__FigureElement__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_1__0"


    // $ANTLR start "rule__FigureElement__Group_1__0__Impl"
    // InternalMarkup.g:1185:1: rule__FigureElement__Group_1__0__Impl : ( '#' ) ;
    public final void rule__FigureElement__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1189:1: ( ( '#' ) )
            // InternalMarkup.g:1190:1: ( '#' )
            {
            // InternalMarkup.g:1190:1: ( '#' )
            // InternalMarkup.g:1191:2: '#'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getNumberSignKeyword_1_0());
            }
            match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getNumberSignKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_1__0__Impl"


    // $ANTLR start "rule__FigureElement__Group_1__1"
    // InternalMarkup.g:1200:1: rule__FigureElement__Group_1__1 : rule__FigureElement__Group_1__1__Impl ;
    public final void rule__FigureElement__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1204:1: ( rule__FigureElement__Group_1__1__Impl )
            // InternalMarkup.g:1205:2: rule__FigureElement__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_1__1"


    // $ANTLR start "rule__FigureElement__Group_1__1__Impl"
    // InternalMarkup.g:1211:1: rule__FigureElement__Group_1__1__Impl : ( ( rule__FigureElement__DefAssignment_1_1 ) ) ;
    public final void rule__FigureElement__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1215:1: ( ( ( rule__FigureElement__DefAssignment_1_1 ) ) )
            // InternalMarkup.g:1216:1: ( ( rule__FigureElement__DefAssignment_1_1 ) )
            {
            // InternalMarkup.g:1216:1: ( ( rule__FigureElement__DefAssignment_1_1 ) )
            // InternalMarkup.g:1217:2: ( rule__FigureElement__DefAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getDefAssignment_1_1());
            }
            // InternalMarkup.g:1218:2: ( rule__FigureElement__DefAssignment_1_1 )
            // InternalMarkup.g:1218:3: rule__FigureElement__DefAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__DefAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getDefAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_1__1__Impl"


    // $ANTLR start "rule__FigureElement__Group_4__0"
    // InternalMarkup.g:1227:1: rule__FigureElement__Group_4__0 : rule__FigureElement__Group_4__0__Impl rule__FigureElement__Group_4__1 ;
    public final void rule__FigureElement__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1231:1: ( rule__FigureElement__Group_4__0__Impl rule__FigureElement__Group_4__1 )
            // InternalMarkup.g:1232:2: rule__FigureElement__Group_4__0__Impl rule__FigureElement__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__FigureElement__Group_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__0"


    // $ANTLR start "rule__FigureElement__Group_4__0__Impl"
    // InternalMarkup.g:1239:1: rule__FigureElement__Group_4__0__Impl : ( ',' ) ;
    public final void rule__FigureElement__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1243:1: ( ( ',' ) )
            // InternalMarkup.g:1244:1: ( ',' )
            {
            // InternalMarkup.g:1244:1: ( ',' )
            // InternalMarkup.g:1245:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getCommaKeyword_4_0());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getCommaKeyword_4_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__0__Impl"


    // $ANTLR start "rule__FigureElement__Group_4__1"
    // InternalMarkup.g:1254:1: rule__FigureElement__Group_4__1 : rule__FigureElement__Group_4__1__Impl rule__FigureElement__Group_4__2 ;
    public final void rule__FigureElement__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1258:1: ( rule__FigureElement__Group_4__1__Impl rule__FigureElement__Group_4__2 )
            // InternalMarkup.g:1259:2: rule__FigureElement__Group_4__1__Impl rule__FigureElement__Group_4__2
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__FigureElement__Group_4__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__1"


    // $ANTLR start "rule__FigureElement__Group_4__1__Impl"
    // InternalMarkup.g:1266:1: rule__FigureElement__Group_4__1__Impl : ( ( rule__FigureElement__AltAssignment_4_1 ) ) ;
    public final void rule__FigureElement__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1270:1: ( ( ( rule__FigureElement__AltAssignment_4_1 ) ) )
            // InternalMarkup.g:1271:1: ( ( rule__FigureElement__AltAssignment_4_1 ) )
            {
            // InternalMarkup.g:1271:1: ( ( rule__FigureElement__AltAssignment_4_1 ) )
            // InternalMarkup.g:1272:2: ( rule__FigureElement__AltAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getAltAssignment_4_1());
            }
            // InternalMarkup.g:1273:2: ( rule__FigureElement__AltAssignment_4_1 )
            // InternalMarkup.g:1273:3: rule__FigureElement__AltAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__AltAssignment_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getAltAssignment_4_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__1__Impl"


    // $ANTLR start "rule__FigureElement__Group_4__2"
    // InternalMarkup.g:1281:1: rule__FigureElement__Group_4__2 : rule__FigureElement__Group_4__2__Impl ;
    public final void rule__FigureElement__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1285:1: ( rule__FigureElement__Group_4__2__Impl )
            // InternalMarkup.g:1286:2: rule__FigureElement__Group_4__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__2"


    // $ANTLR start "rule__FigureElement__Group_4__2__Impl"
    // InternalMarkup.g:1292:1: rule__FigureElement__Group_4__2__Impl : ( ( rule__FigureElement__Group_4_2__0 )? ) ;
    public final void rule__FigureElement__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1296:1: ( ( ( rule__FigureElement__Group_4_2__0 )? ) )
            // InternalMarkup.g:1297:1: ( ( rule__FigureElement__Group_4_2__0 )? )
            {
            // InternalMarkup.g:1297:1: ( ( rule__FigureElement__Group_4_2__0 )? )
            // InternalMarkup.g:1298:2: ( rule__FigureElement__Group_4_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getGroup_4_2());
            }
            // InternalMarkup.g:1299:2: ( rule__FigureElement__Group_4_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalMarkup.g:1299:3: rule__FigureElement__Group_4_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__FigureElement__Group_4_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getGroup_4_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4__2__Impl"


    // $ANTLR start "rule__FigureElement__Group_4_2__0"
    // InternalMarkup.g:1308:1: rule__FigureElement__Group_4_2__0 : rule__FigureElement__Group_4_2__0__Impl rule__FigureElement__Group_4_2__1 ;
    public final void rule__FigureElement__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1312:1: ( rule__FigureElement__Group_4_2__0__Impl rule__FigureElement__Group_4_2__1 )
            // InternalMarkup.g:1313:2: rule__FigureElement__Group_4_2__0__Impl rule__FigureElement__Group_4_2__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__FigureElement__Group_4_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__0"


    // $ANTLR start "rule__FigureElement__Group_4_2__0__Impl"
    // InternalMarkup.g:1320:1: rule__FigureElement__Group_4_2__0__Impl : ( ',' ) ;
    public final void rule__FigureElement__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1324:1: ( ( ',' ) )
            // InternalMarkup.g:1325:1: ( ',' )
            {
            // InternalMarkup.g:1325:1: ( ',' )
            // InternalMarkup.g:1326:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getCommaKeyword_4_2_0());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getCommaKeyword_4_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__0__Impl"


    // $ANTLR start "rule__FigureElement__Group_4_2__1"
    // InternalMarkup.g:1335:1: rule__FigureElement__Group_4_2__1 : rule__FigureElement__Group_4_2__1__Impl rule__FigureElement__Group_4_2__2 ;
    public final void rule__FigureElement__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1339:1: ( rule__FigureElement__Group_4_2__1__Impl rule__FigureElement__Group_4_2__2 )
            // InternalMarkup.g:1340:2: rule__FigureElement__Group_4_2__1__Impl rule__FigureElement__Group_4_2__2
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__FigureElement__Group_4_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4_2__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__1"


    // $ANTLR start "rule__FigureElement__Group_4_2__1__Impl"
    // InternalMarkup.g:1347:1: rule__FigureElement__Group_4_2__1__Impl : ( ( rule__FigureElement__RequiredWidthAssignment_4_2_1 ) ) ;
    public final void rule__FigureElement__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1351:1: ( ( ( rule__FigureElement__RequiredWidthAssignment_4_2_1 ) ) )
            // InternalMarkup.g:1352:1: ( ( rule__FigureElement__RequiredWidthAssignment_4_2_1 ) )
            {
            // InternalMarkup.g:1352:1: ( ( rule__FigureElement__RequiredWidthAssignment_4_2_1 ) )
            // InternalMarkup.g:1353:2: ( rule__FigureElement__RequiredWidthAssignment_4_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getRequiredWidthAssignment_4_2_1());
            }
            // InternalMarkup.g:1354:2: ( rule__FigureElement__RequiredWidthAssignment_4_2_1 )
            // InternalMarkup.g:1354:3: rule__FigureElement__RequiredWidthAssignment_4_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__RequiredWidthAssignment_4_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getRequiredWidthAssignment_4_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__1__Impl"


    // $ANTLR start "rule__FigureElement__Group_4_2__2"
    // InternalMarkup.g:1362:1: rule__FigureElement__Group_4_2__2 : rule__FigureElement__Group_4_2__2__Impl ;
    public final void rule__FigureElement__Group_4_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1366:1: ( rule__FigureElement__Group_4_2__2__Impl )
            // InternalMarkup.g:1367:2: rule__FigureElement__Group_4_2__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4_2__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__2"


    // $ANTLR start "rule__FigureElement__Group_4_2__2__Impl"
    // InternalMarkup.g:1373:1: rule__FigureElement__Group_4_2__2__Impl : ( ( rule__FigureElement__Group_4_2_2__0 )? ) ;
    public final void rule__FigureElement__Group_4_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1377:1: ( ( ( rule__FigureElement__Group_4_2_2__0 )? ) )
            // InternalMarkup.g:1378:1: ( ( rule__FigureElement__Group_4_2_2__0 )? )
            {
            // InternalMarkup.g:1378:1: ( ( rule__FigureElement__Group_4_2_2__0 )? )
            // InternalMarkup.g:1379:2: ( rule__FigureElement__Group_4_2_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getGroup_4_2_2());
            }
            // InternalMarkup.g:1380:2: ( rule__FigureElement__Group_4_2_2__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalMarkup.g:1380:3: rule__FigureElement__Group_4_2_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__FigureElement__Group_4_2_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getGroup_4_2_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2__2__Impl"


    // $ANTLR start "rule__FigureElement__Group_4_2_2__0"
    // InternalMarkup.g:1389:1: rule__FigureElement__Group_4_2_2__0 : rule__FigureElement__Group_4_2_2__0__Impl rule__FigureElement__Group_4_2_2__1 ;
    public final void rule__FigureElement__Group_4_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1393:1: ( rule__FigureElement__Group_4_2_2__0__Impl rule__FigureElement__Group_4_2_2__1 )
            // InternalMarkup.g:1394:2: rule__FigureElement__Group_4_2_2__0__Impl rule__FigureElement__Group_4_2_2__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__FigureElement__Group_4_2_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4_2_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2_2__0"


    // $ANTLR start "rule__FigureElement__Group_4_2_2__0__Impl"
    // InternalMarkup.g:1401:1: rule__FigureElement__Group_4_2_2__0__Impl : ( ',' ) ;
    public final void rule__FigureElement__Group_4_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1405:1: ( ( ',' ) )
            // InternalMarkup.g:1406:1: ( ',' )
            {
            // InternalMarkup.g:1406:1: ( ',' )
            // InternalMarkup.g:1407:2: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getCommaKeyword_4_2_2_0());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getCommaKeyword_4_2_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2_2__0__Impl"


    // $ANTLR start "rule__FigureElement__Group_4_2_2__1"
    // InternalMarkup.g:1416:1: rule__FigureElement__Group_4_2_2__1 : rule__FigureElement__Group_4_2_2__1__Impl ;
    public final void rule__FigureElement__Group_4_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1420:1: ( rule__FigureElement__Group_4_2_2__1__Impl )
            // InternalMarkup.g:1421:2: rule__FigureElement__Group_4_2_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__Group_4_2_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2_2__1"


    // $ANTLR start "rule__FigureElement__Group_4_2_2__1__Impl"
    // InternalMarkup.g:1427:1: rule__FigureElement__Group_4_2_2__1__Impl : ( ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 ) ) ;
    public final void rule__FigureElement__Group_4_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1431:1: ( ( ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 ) ) )
            // InternalMarkup.g:1432:1: ( ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 ) )
            {
            // InternalMarkup.g:1432:1: ( ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 ) )
            // InternalMarkup.g:1433:2: ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getRequiredHeightAssignment_4_2_2_1());
            }
            // InternalMarkup.g:1434:2: ( rule__FigureElement__RequiredHeightAssignment_4_2_2_1 )
            // InternalMarkup.g:1434:3: rule__FigureElement__RequiredHeightAssignment_4_2_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureElement__RequiredHeightAssignment_4_2_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getRequiredHeightAssignment_4_2_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__Group_4_2_2__1__Impl"


    // $ANTLR start "rule__FigureRefElement__Group__0"
    // InternalMarkup.g:1443:1: rule__FigureRefElement__Group__0 : rule__FigureRefElement__Group__0__Impl rule__FigureRefElement__Group__1 ;
    public final void rule__FigureRefElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1447:1: ( rule__FigureRefElement__Group__0__Impl rule__FigureRefElement__Group__1 )
            // InternalMarkup.g:1448:2: rule__FigureRefElement__Group__0__Impl rule__FigureRefElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__FigureRefElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__0"


    // $ANTLR start "rule__FigureRefElement__Group__0__Impl"
    // InternalMarkup.g:1455:1: rule__FigureRefElement__Group__0__Impl : ( 'figureRef' ) ;
    public final void rule__FigureRefElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1459:1: ( ( 'figureRef' ) )
            // InternalMarkup.g:1460:1: ( 'figureRef' )
            {
            // InternalMarkup.g:1460:1: ( 'figureRef' )
            // InternalMarkup.g:1461:2: 'figureRef'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getFigureRefKeyword_0());
            }
            match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getFigureRefKeyword_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__0__Impl"


    // $ANTLR start "rule__FigureRefElement__Group__1"
    // InternalMarkup.g:1470:1: rule__FigureRefElement__Group__1 : rule__FigureRefElement__Group__1__Impl rule__FigureRefElement__Group__2 ;
    public final void rule__FigureRefElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1474:1: ( rule__FigureRefElement__Group__1__Impl rule__FigureRefElement__Group__2 )
            // InternalMarkup.g:1475:2: rule__FigureRefElement__Group__1__Impl rule__FigureRefElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_13);
            rule__FigureRefElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__1"


    // $ANTLR start "rule__FigureRefElement__Group__1__Impl"
    // InternalMarkup.g:1482:1: rule__FigureRefElement__Group__1__Impl : ( '[' ) ;
    public final void rule__FigureRefElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1486:1: ( ( '[' ) )
            // InternalMarkup.g:1487:1: ( '[' )
            {
            // InternalMarkup.g:1487:1: ( '[' )
            // InternalMarkup.g:1488:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getLeftSquareBracketKeyword_1());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getLeftSquareBracketKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__1__Impl"


    // $ANTLR start "rule__FigureRefElement__Group__2"
    // InternalMarkup.g:1497:1: rule__FigureRefElement__Group__2 : rule__FigureRefElement__Group__2__Impl rule__FigureRefElement__Group__3 ;
    public final void rule__FigureRefElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1501:1: ( rule__FigureRefElement__Group__2__Impl rule__FigureRefElement__Group__3 )
            // InternalMarkup.g:1502:2: rule__FigureRefElement__Group__2__Impl rule__FigureRefElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_15);
            rule__FigureRefElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__2"


    // $ANTLR start "rule__FigureRefElement__Group__2__Impl"
    // InternalMarkup.g:1509:1: rule__FigureRefElement__Group__2__Impl : ( ( rule__FigureRefElement__RefAssignment_2 ) ) ;
    public final void rule__FigureRefElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1513:1: ( ( ( rule__FigureRefElement__RefAssignment_2 ) ) )
            // InternalMarkup.g:1514:1: ( ( rule__FigureRefElement__RefAssignment_2 ) )
            {
            // InternalMarkup.g:1514:1: ( ( rule__FigureRefElement__RefAssignment_2 ) )
            // InternalMarkup.g:1515:2: ( rule__FigureRefElement__RefAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getRefAssignment_2());
            }
            // InternalMarkup.g:1516:2: ( rule__FigureRefElement__RefAssignment_2 )
            // InternalMarkup.g:1516:3: rule__FigureRefElement__RefAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__RefAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getRefAssignment_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__2__Impl"


    // $ANTLR start "rule__FigureRefElement__Group__3"
    // InternalMarkup.g:1524:1: rule__FigureRefElement__Group__3 : rule__FigureRefElement__Group__3__Impl ;
    public final void rule__FigureRefElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1528:1: ( rule__FigureRefElement__Group__3__Impl )
            // InternalMarkup.g:1529:2: rule__FigureRefElement__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FigureRefElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__3"


    // $ANTLR start "rule__FigureRefElement__Group__3__Impl"
    // InternalMarkup.g:1535:1: rule__FigureRefElement__Group__3__Impl : ( ']' ) ;
    public final void rule__FigureRefElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1539:1: ( ( ']' ) )
            // InternalMarkup.g:1540:1: ( ']' )
            {
            // InternalMarkup.g:1540:1: ( ']' )
            // InternalMarkup.g:1541:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getRightSquareBracketKeyword_3());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getRightSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__Group__3__Impl"


    // $ANTLR start "rule__FootnoteElement__Group__0"
    // InternalMarkup.g:1551:1: rule__FootnoteElement__Group__0 : rule__FootnoteElement__Group__0__Impl rule__FootnoteElement__Group__1 ;
    public final void rule__FootnoteElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1555:1: ( rule__FootnoteElement__Group__0__Impl rule__FootnoteElement__Group__1 )
            // InternalMarkup.g:1556:2: rule__FootnoteElement__Group__0__Impl rule__FootnoteElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__FootnoteElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__0"


    // $ANTLR start "rule__FootnoteElement__Group__0__Impl"
    // InternalMarkup.g:1563:1: rule__FootnoteElement__Group__0__Impl : ( () ) ;
    public final void rule__FootnoteElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1567:1: ( ( () ) )
            // InternalMarkup.g:1568:1: ( () )
            {
            // InternalMarkup.g:1568:1: ( () )
            // InternalMarkup.g:1569:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getFootnoteElementAction_0());
            }
            // InternalMarkup.g:1570:2: ()
            // InternalMarkup.g:1570:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getFootnoteElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__0__Impl"


    // $ANTLR start "rule__FootnoteElement__Group__1"
    // InternalMarkup.g:1578:1: rule__FootnoteElement__Group__1 : rule__FootnoteElement__Group__1__Impl rule__FootnoteElement__Group__2 ;
    public final void rule__FootnoteElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1582:1: ( rule__FootnoteElement__Group__1__Impl rule__FootnoteElement__Group__2 )
            // InternalMarkup.g:1583:2: rule__FootnoteElement__Group__1__Impl rule__FootnoteElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__FootnoteElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__1"


    // $ANTLR start "rule__FootnoteElement__Group__1__Impl"
    // InternalMarkup.g:1590:1: rule__FootnoteElement__Group__1__Impl : ( 'footnote' ) ;
    public final void rule__FootnoteElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1594:1: ( ( 'footnote' ) )
            // InternalMarkup.g:1595:1: ( 'footnote' )
            {
            // InternalMarkup.g:1595:1: ( 'footnote' )
            // InternalMarkup.g:1596:2: 'footnote'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getFootnoteKeyword_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getFootnoteKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__1__Impl"


    // $ANTLR start "rule__FootnoteElement__Group__2"
    // InternalMarkup.g:1605:1: rule__FootnoteElement__Group__2 : rule__FootnoteElement__Group__2__Impl rule__FootnoteElement__Group__3 ;
    public final void rule__FootnoteElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1609:1: ( rule__FootnoteElement__Group__2__Impl rule__FootnoteElement__Group__3 )
            // InternalMarkup.g:1610:2: rule__FootnoteElement__Group__2__Impl rule__FootnoteElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__FootnoteElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__2"


    // $ANTLR start "rule__FootnoteElement__Group__2__Impl"
    // InternalMarkup.g:1617:1: rule__FootnoteElement__Group__2__Impl : ( '[' ) ;
    public final void rule__FootnoteElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1621:1: ( ( '[' ) )
            // InternalMarkup.g:1622:1: ( '[' )
            {
            // InternalMarkup.g:1622:1: ( '[' )
            // InternalMarkup.g:1623:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getLeftSquareBracketKeyword_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getLeftSquareBracketKeyword_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__2__Impl"


    // $ANTLR start "rule__FootnoteElement__Group__3"
    // InternalMarkup.g:1632:1: rule__FootnoteElement__Group__3 : rule__FootnoteElement__Group__3__Impl rule__FootnoteElement__Group__4 ;
    public final void rule__FootnoteElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1636:1: ( rule__FootnoteElement__Group__3__Impl rule__FootnoteElement__Group__4 )
            // InternalMarkup.g:1637:2: rule__FootnoteElement__Group__3__Impl rule__FootnoteElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__FootnoteElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__3"


    // $ANTLR start "rule__FootnoteElement__Group__3__Impl"
    // InternalMarkup.g:1644:1: rule__FootnoteElement__Group__3__Impl : ( ( rule__FootnoteElement__ElementsAssignment_3 )* ) ;
    public final void rule__FootnoteElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1648:1: ( ( ( rule__FootnoteElement__ElementsAssignment_3 )* ) )
            // InternalMarkup.g:1649:1: ( ( rule__FootnoteElement__ElementsAssignment_3 )* )
            {
            // InternalMarkup.g:1649:1: ( ( rule__FootnoteElement__ElementsAssignment_3 )* )
            // InternalMarkup.g:1650:2: ( rule__FootnoteElement__ElementsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getElementsAssignment_3());
            }
            // InternalMarkup.g:1651:2: ( rule__FootnoteElement__ElementsAssignment_3 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=RULE_ID && LA15_0<=RULE_WS)||LA15_0==RULE_NL||(LA15_0>=16 && LA15_0<=29)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalMarkup.g:1651:3: rule__FootnoteElement__ElementsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__FootnoteElement__ElementsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getElementsAssignment_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__3__Impl"


    // $ANTLR start "rule__FootnoteElement__Group__4"
    // InternalMarkup.g:1659:1: rule__FootnoteElement__Group__4 : rule__FootnoteElement__Group__4__Impl ;
    public final void rule__FootnoteElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1663:1: ( rule__FootnoteElement__Group__4__Impl )
            // InternalMarkup.g:1664:2: rule__FootnoteElement__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FootnoteElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__4"


    // $ANTLR start "rule__FootnoteElement__Group__4__Impl"
    // InternalMarkup.g:1670:1: rule__FootnoteElement__Group__4__Impl : ( ']' ) ;
    public final void rule__FootnoteElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1674:1: ( ( ']' ) )
            // InternalMarkup.g:1675:1: ( ']' )
            {
            // InternalMarkup.g:1675:1: ( ']' )
            // InternalMarkup.g:1676:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getRightSquareBracketKeyword_4());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getRightSquareBracketKeyword_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__Group__4__Impl"


    // $ANTLR start "rule__HeadingElement__Group__0"
    // InternalMarkup.g:1686:1: rule__HeadingElement__Group__0 : rule__HeadingElement__Group__0__Impl rule__HeadingElement__Group__1 ;
    public final void rule__HeadingElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1690:1: ( rule__HeadingElement__Group__0__Impl rule__HeadingElement__Group__1 )
            // InternalMarkup.g:1691:2: rule__HeadingElement__Group__0__Impl rule__HeadingElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__HeadingElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__0"


    // $ANTLR start "rule__HeadingElement__Group__0__Impl"
    // InternalMarkup.g:1698:1: rule__HeadingElement__Group__0__Impl : ( () ) ;
    public final void rule__HeadingElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1702:1: ( ( () ) )
            // InternalMarkup.g:1703:1: ( () )
            {
            // InternalMarkup.g:1703:1: ( () )
            // InternalMarkup.g:1704:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getHeadingElementAction_0());
            }
            // InternalMarkup.g:1705:2: ()
            // InternalMarkup.g:1705:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getHeadingElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__0__Impl"


    // $ANTLR start "rule__HeadingElement__Group__1"
    // InternalMarkup.g:1713:1: rule__HeadingElement__Group__1 : rule__HeadingElement__Group__1__Impl rule__HeadingElement__Group__2 ;
    public final void rule__HeadingElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1717:1: ( rule__HeadingElement__Group__1__Impl rule__HeadingElement__Group__2 )
            // InternalMarkup.g:1718:2: rule__HeadingElement__Group__1__Impl rule__HeadingElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__HeadingElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__1"


    // $ANTLR start "rule__HeadingElement__Group__1__Impl"
    // InternalMarkup.g:1725:1: rule__HeadingElement__Group__1__Impl : ( 'heading' ) ;
    public final void rule__HeadingElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1729:1: ( ( 'heading' ) )
            // InternalMarkup.g:1730:1: ( 'heading' )
            {
            // InternalMarkup.g:1730:1: ( 'heading' )
            // InternalMarkup.g:1731:2: 'heading'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getHeadingKeyword_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getHeadingKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__1__Impl"


    // $ANTLR start "rule__HeadingElement__Group__2"
    // InternalMarkup.g:1740:1: rule__HeadingElement__Group__2 : rule__HeadingElement__Group__2__Impl rule__HeadingElement__Group__3 ;
    public final void rule__HeadingElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1744:1: ( rule__HeadingElement__Group__2__Impl rule__HeadingElement__Group__3 )
            // InternalMarkup.g:1745:2: rule__HeadingElement__Group__2__Impl rule__HeadingElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__HeadingElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__2"


    // $ANTLR start "rule__HeadingElement__Group__2__Impl"
    // InternalMarkup.g:1752:1: rule__HeadingElement__Group__2__Impl : ( ( rule__HeadingElement__Group_2__0 )? ) ;
    public final void rule__HeadingElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1756:1: ( ( ( rule__HeadingElement__Group_2__0 )? ) )
            // InternalMarkup.g:1757:1: ( ( rule__HeadingElement__Group_2__0 )? )
            {
            // InternalMarkup.g:1757:1: ( ( rule__HeadingElement__Group_2__0 )? )
            // InternalMarkup.g:1758:2: ( rule__HeadingElement__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getGroup_2());
            }
            // InternalMarkup.g:1759:2: ( rule__HeadingElement__Group_2__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==26) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalMarkup.g:1759:3: rule__HeadingElement__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__HeadingElement__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getGroup_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__2__Impl"


    // $ANTLR start "rule__HeadingElement__Group__3"
    // InternalMarkup.g:1767:1: rule__HeadingElement__Group__3 : rule__HeadingElement__Group__3__Impl rule__HeadingElement__Group__4 ;
    public final void rule__HeadingElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1771:1: ( rule__HeadingElement__Group__3__Impl rule__HeadingElement__Group__4 )
            // InternalMarkup.g:1772:2: rule__HeadingElement__Group__3__Impl rule__HeadingElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__HeadingElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__3"


    // $ANTLR start "rule__HeadingElement__Group__3__Impl"
    // InternalMarkup.g:1779:1: rule__HeadingElement__Group__3__Impl : ( '[' ) ;
    public final void rule__HeadingElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1783:1: ( ( '[' ) )
            // InternalMarkup.g:1784:1: ( '[' )
            {
            // InternalMarkup.g:1784:1: ( '[' )
            // InternalMarkup.g:1785:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getLeftSquareBracketKeyword_3());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getLeftSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__3__Impl"


    // $ANTLR start "rule__HeadingElement__Group__4"
    // InternalMarkup.g:1794:1: rule__HeadingElement__Group__4 : rule__HeadingElement__Group__4__Impl rule__HeadingElement__Group__5 ;
    public final void rule__HeadingElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1798:1: ( rule__HeadingElement__Group__4__Impl rule__HeadingElement__Group__5 )
            // InternalMarkup.g:1799:2: rule__HeadingElement__Group__4__Impl rule__HeadingElement__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__HeadingElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__4"


    // $ANTLR start "rule__HeadingElement__Group__4__Impl"
    // InternalMarkup.g:1806:1: rule__HeadingElement__Group__4__Impl : ( ( rule__HeadingElement__ElementsAssignment_4 )* ) ;
    public final void rule__HeadingElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1810:1: ( ( ( rule__HeadingElement__ElementsAssignment_4 )* ) )
            // InternalMarkup.g:1811:1: ( ( rule__HeadingElement__ElementsAssignment_4 )* )
            {
            // InternalMarkup.g:1811:1: ( ( rule__HeadingElement__ElementsAssignment_4 )* )
            // InternalMarkup.g:1812:2: ( rule__HeadingElement__ElementsAssignment_4 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getElementsAssignment_4());
            }
            // InternalMarkup.g:1813:2: ( rule__HeadingElement__ElementsAssignment_4 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=RULE_ID && LA17_0<=RULE_WS)||LA17_0==RULE_NL||(LA17_0>=16 && LA17_0<=29)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalMarkup.g:1813:3: rule__HeadingElement__ElementsAssignment_4
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__HeadingElement__ElementsAssignment_4();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getElementsAssignment_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__4__Impl"


    // $ANTLR start "rule__HeadingElement__Group__5"
    // InternalMarkup.g:1821:1: rule__HeadingElement__Group__5 : rule__HeadingElement__Group__5__Impl ;
    public final void rule__HeadingElement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1825:1: ( rule__HeadingElement__Group__5__Impl )
            // InternalMarkup.g:1826:2: rule__HeadingElement__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__5"


    // $ANTLR start "rule__HeadingElement__Group__5__Impl"
    // InternalMarkup.g:1832:1: rule__HeadingElement__Group__5__Impl : ( ']' ) ;
    public final void rule__HeadingElement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1836:1: ( ( ']' ) )
            // InternalMarkup.g:1837:1: ( ']' )
            {
            // InternalMarkup.g:1837:1: ( ']' )
            // InternalMarkup.g:1838:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getRightSquareBracketKeyword_5());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getRightSquareBracketKeyword_5());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group__5__Impl"


    // $ANTLR start "rule__HeadingElement__Group_2__0"
    // InternalMarkup.g:1848:1: rule__HeadingElement__Group_2__0 : rule__HeadingElement__Group_2__0__Impl rule__HeadingElement__Group_2__1 ;
    public final void rule__HeadingElement__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1852:1: ( rule__HeadingElement__Group_2__0__Impl rule__HeadingElement__Group_2__1 )
            // InternalMarkup.g:1853:2: rule__HeadingElement__Group_2__0__Impl rule__HeadingElement__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__HeadingElement__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group_2__0"


    // $ANTLR start "rule__HeadingElement__Group_2__0__Impl"
    // InternalMarkup.g:1860:1: rule__HeadingElement__Group_2__0__Impl : ( ':' ) ;
    public final void rule__HeadingElement__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1864:1: ( ( ':' ) )
            // InternalMarkup.g:1865:1: ( ':' )
            {
            // InternalMarkup.g:1865:1: ( ':' )
            // InternalMarkup.g:1866:2: ':'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getColonKeyword_2_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getColonKeyword_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group_2__0__Impl"


    // $ANTLR start "rule__HeadingElement__Group_2__1"
    // InternalMarkup.g:1875:1: rule__HeadingElement__Group_2__1 : rule__HeadingElement__Group_2__1__Impl ;
    public final void rule__HeadingElement__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1879:1: ( rule__HeadingElement__Group_2__1__Impl )
            // InternalMarkup.g:1880:2: rule__HeadingElement__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group_2__1"


    // $ANTLR start "rule__HeadingElement__Group_2__1__Impl"
    // InternalMarkup.g:1886:1: rule__HeadingElement__Group_2__1__Impl : ( ( rule__HeadingElement__LevelAssignment_2_1 ) ) ;
    public final void rule__HeadingElement__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1890:1: ( ( ( rule__HeadingElement__LevelAssignment_2_1 ) ) )
            // InternalMarkup.g:1891:1: ( ( rule__HeadingElement__LevelAssignment_2_1 ) )
            {
            // InternalMarkup.g:1891:1: ( ( rule__HeadingElement__LevelAssignment_2_1 ) )
            // InternalMarkup.g:1892:2: ( rule__HeadingElement__LevelAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getLevelAssignment_2_1());
            }
            // InternalMarkup.g:1893:2: ( rule__HeadingElement__LevelAssignment_2_1 )
            // InternalMarkup.g:1893:3: rule__HeadingElement__LevelAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HeadingElement__LevelAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getLevelAssignment_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__Group_2__1__Impl"


    // $ANTLR start "rule__NullElement__Group__0"
    // InternalMarkup.g:1902:1: rule__NullElement__Group__0 : rule__NullElement__Group__0__Impl rule__NullElement__Group__1 ;
    public final void rule__NullElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1906:1: ( rule__NullElement__Group__0__Impl rule__NullElement__Group__1 )
            // InternalMarkup.g:1907:2: rule__NullElement__Group__0__Impl rule__NullElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__NullElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NullElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__0"


    // $ANTLR start "rule__NullElement__Group__0__Impl"
    // InternalMarkup.g:1914:1: rule__NullElement__Group__0__Impl : ( () ) ;
    public final void rule__NullElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1918:1: ( ( () ) )
            // InternalMarkup.g:1919:1: ( () )
            {
            // InternalMarkup.g:1919:1: ( () )
            // InternalMarkup.g:1920:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getNullElementAction_0());
            }
            // InternalMarkup.g:1921:2: ()
            // InternalMarkup.g:1921:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getNullElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__0__Impl"


    // $ANTLR start "rule__NullElement__Group__1"
    // InternalMarkup.g:1929:1: rule__NullElement__Group__1 : rule__NullElement__Group__1__Impl rule__NullElement__Group__2 ;
    public final void rule__NullElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1933:1: ( rule__NullElement__Group__1__Impl rule__NullElement__Group__2 )
            // InternalMarkup.g:1934:2: rule__NullElement__Group__1__Impl rule__NullElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__NullElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NullElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__1"


    // $ANTLR start "rule__NullElement__Group__1__Impl"
    // InternalMarkup.g:1941:1: rule__NullElement__Group__1__Impl : ( '[' ) ;
    public final void rule__NullElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1945:1: ( ( '[' ) )
            // InternalMarkup.g:1946:1: ( '[' )
            {
            // InternalMarkup.g:1946:1: ( '[' )
            // InternalMarkup.g:1947:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getLeftSquareBracketKeyword_1());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getLeftSquareBracketKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__1__Impl"


    // $ANTLR start "rule__NullElement__Group__2"
    // InternalMarkup.g:1956:1: rule__NullElement__Group__2 : rule__NullElement__Group__2__Impl rule__NullElement__Group__3 ;
    public final void rule__NullElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1960:1: ( rule__NullElement__Group__2__Impl rule__NullElement__Group__3 )
            // InternalMarkup.g:1961:2: rule__NullElement__Group__2__Impl rule__NullElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__NullElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NullElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__2"


    // $ANTLR start "rule__NullElement__Group__2__Impl"
    // InternalMarkup.g:1968:1: rule__NullElement__Group__2__Impl : ( ( rule__NullElement__ElementsAssignment_2 )* ) ;
    public final void rule__NullElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1972:1: ( ( ( rule__NullElement__ElementsAssignment_2 )* ) )
            // InternalMarkup.g:1973:1: ( ( rule__NullElement__ElementsAssignment_2 )* )
            {
            // InternalMarkup.g:1973:1: ( ( rule__NullElement__ElementsAssignment_2 )* )
            // InternalMarkup.g:1974:2: ( rule__NullElement__ElementsAssignment_2 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getElementsAssignment_2());
            }
            // InternalMarkup.g:1975:2: ( rule__NullElement__ElementsAssignment_2 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=RULE_ID && LA18_0<=RULE_WS)||LA18_0==RULE_NL||(LA18_0>=16 && LA18_0<=29)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalMarkup.g:1975:3: rule__NullElement__ElementsAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__NullElement__ElementsAssignment_2();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getElementsAssignment_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__2__Impl"


    // $ANTLR start "rule__NullElement__Group__3"
    // InternalMarkup.g:1983:1: rule__NullElement__Group__3 : rule__NullElement__Group__3__Impl ;
    public final void rule__NullElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1987:1: ( rule__NullElement__Group__3__Impl )
            // InternalMarkup.g:1988:2: rule__NullElement__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NullElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__3"


    // $ANTLR start "rule__NullElement__Group__3__Impl"
    // InternalMarkup.g:1994:1: rule__NullElement__Group__3__Impl : ( ']' ) ;
    public final void rule__NullElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:1998:1: ( ( ']' ) )
            // InternalMarkup.g:1999:1: ( ']' )
            {
            // InternalMarkup.g:1999:1: ( ']' )
            // InternalMarkup.g:2000:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getRightSquareBracketKeyword_3());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getRightSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__Group__3__Impl"


    // $ANTLR start "rule__OCLCodeElement__Group__0"
    // InternalMarkup.g:2010:1: rule__OCLCodeElement__Group__0 : rule__OCLCodeElement__Group__0__Impl rule__OCLCodeElement__Group__1 ;
    public final void rule__OCLCodeElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2014:1: ( rule__OCLCodeElement__Group__0__Impl rule__OCLCodeElement__Group__1 )
            // InternalMarkup.g:2015:2: rule__OCLCodeElement__Group__0__Impl rule__OCLCodeElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__OCLCodeElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__0"


    // $ANTLR start "rule__OCLCodeElement__Group__0__Impl"
    // InternalMarkup.g:2022:1: rule__OCLCodeElement__Group__0__Impl : ( () ) ;
    public final void rule__OCLCodeElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2026:1: ( ( () ) )
            // InternalMarkup.g:2027:1: ( () )
            {
            // InternalMarkup.g:2027:1: ( () )
            // InternalMarkup.g:2028:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getOCLCodeElementAction_0());
            }
            // InternalMarkup.g:2029:2: ()
            // InternalMarkup.g:2029:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getOCLCodeElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__0__Impl"


    // $ANTLR start "rule__OCLCodeElement__Group__1"
    // InternalMarkup.g:2037:1: rule__OCLCodeElement__Group__1 : rule__OCLCodeElement__Group__1__Impl rule__OCLCodeElement__Group__2 ;
    public final void rule__OCLCodeElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2041:1: ( rule__OCLCodeElement__Group__1__Impl rule__OCLCodeElement__Group__2 )
            // InternalMarkup.g:2042:2: rule__OCLCodeElement__Group__1__Impl rule__OCLCodeElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__OCLCodeElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__1"


    // $ANTLR start "rule__OCLCodeElement__Group__1__Impl"
    // InternalMarkup.g:2049:1: rule__OCLCodeElement__Group__1__Impl : ( 'oclCode' ) ;
    public final void rule__OCLCodeElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2053:1: ( ( 'oclCode' ) )
            // InternalMarkup.g:2054:1: ( 'oclCode' )
            {
            // InternalMarkup.g:2054:1: ( 'oclCode' )
            // InternalMarkup.g:2055:2: 'oclCode'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getOclCodeKeyword_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getOclCodeKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__1__Impl"


    // $ANTLR start "rule__OCLCodeElement__Group__2"
    // InternalMarkup.g:2064:1: rule__OCLCodeElement__Group__2 : rule__OCLCodeElement__Group__2__Impl rule__OCLCodeElement__Group__3 ;
    public final void rule__OCLCodeElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2068:1: ( rule__OCLCodeElement__Group__2__Impl rule__OCLCodeElement__Group__3 )
            // InternalMarkup.g:2069:2: rule__OCLCodeElement__Group__2__Impl rule__OCLCodeElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLCodeElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__2"


    // $ANTLR start "rule__OCLCodeElement__Group__2__Impl"
    // InternalMarkup.g:2076:1: rule__OCLCodeElement__Group__2__Impl : ( '[' ) ;
    public final void rule__OCLCodeElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2080:1: ( ( '[' ) )
            // InternalMarkup.g:2081:1: ( '[' )
            {
            // InternalMarkup.g:2081:1: ( '[' )
            // InternalMarkup.g:2082:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getLeftSquareBracketKeyword_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getLeftSquareBracketKeyword_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__2__Impl"


    // $ANTLR start "rule__OCLCodeElement__Group__3"
    // InternalMarkup.g:2091:1: rule__OCLCodeElement__Group__3 : rule__OCLCodeElement__Group__3__Impl rule__OCLCodeElement__Group__4 ;
    public final void rule__OCLCodeElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2095:1: ( rule__OCLCodeElement__Group__3__Impl rule__OCLCodeElement__Group__4 )
            // InternalMarkup.g:2096:2: rule__OCLCodeElement__Group__3__Impl rule__OCLCodeElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLCodeElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__3"


    // $ANTLR start "rule__OCLCodeElement__Group__3__Impl"
    // InternalMarkup.g:2103:1: rule__OCLCodeElement__Group__3__Impl : ( ( rule__OCLCodeElement__ElementsAssignment_3 )* ) ;
    public final void rule__OCLCodeElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2107:1: ( ( ( rule__OCLCodeElement__ElementsAssignment_3 )* ) )
            // InternalMarkup.g:2108:1: ( ( rule__OCLCodeElement__ElementsAssignment_3 )* )
            {
            // InternalMarkup.g:2108:1: ( ( rule__OCLCodeElement__ElementsAssignment_3 )* )
            // InternalMarkup.g:2109:2: ( rule__OCLCodeElement__ElementsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getElementsAssignment_3());
            }
            // InternalMarkup.g:2110:2: ( rule__OCLCodeElement__ElementsAssignment_3 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_WS)||LA19_0==RULE_NL||(LA19_0>=16 && LA19_0<=29)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalMarkup.g:2110:3: rule__OCLCodeElement__ElementsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__OCLCodeElement__ElementsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getElementsAssignment_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__3__Impl"


    // $ANTLR start "rule__OCLCodeElement__Group__4"
    // InternalMarkup.g:2118:1: rule__OCLCodeElement__Group__4 : rule__OCLCodeElement__Group__4__Impl ;
    public final void rule__OCLCodeElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2122:1: ( rule__OCLCodeElement__Group__4__Impl )
            // InternalMarkup.g:2123:2: rule__OCLCodeElement__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLCodeElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__4"


    // $ANTLR start "rule__OCLCodeElement__Group__4__Impl"
    // InternalMarkup.g:2129:1: rule__OCLCodeElement__Group__4__Impl : ( ']' ) ;
    public final void rule__OCLCodeElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2133:1: ( ( ']' ) )
            // InternalMarkup.g:2134:1: ( ']' )
            {
            // InternalMarkup.g:2134:1: ( ']' )
            // InternalMarkup.g:2135:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getRightSquareBracketKeyword_4());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getRightSquareBracketKeyword_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__Group__4__Impl"


    // $ANTLR start "rule__OCLEvalElement__Group__0"
    // InternalMarkup.g:2145:1: rule__OCLEvalElement__Group__0 : rule__OCLEvalElement__Group__0__Impl rule__OCLEvalElement__Group__1 ;
    public final void rule__OCLEvalElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2149:1: ( rule__OCLEvalElement__Group__0__Impl rule__OCLEvalElement__Group__1 )
            // InternalMarkup.g:2150:2: rule__OCLEvalElement__Group__0__Impl rule__OCLEvalElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_19);
            rule__OCLEvalElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__0"


    // $ANTLR start "rule__OCLEvalElement__Group__0__Impl"
    // InternalMarkup.g:2157:1: rule__OCLEvalElement__Group__0__Impl : ( () ) ;
    public final void rule__OCLEvalElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2161:1: ( ( () ) )
            // InternalMarkup.g:2162:1: ( () )
            {
            // InternalMarkup.g:2162:1: ( () )
            // InternalMarkup.g:2163:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getOCLEvalElementAction_0());
            }
            // InternalMarkup.g:2164:2: ()
            // InternalMarkup.g:2164:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getOCLEvalElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__0__Impl"


    // $ANTLR start "rule__OCLEvalElement__Group__1"
    // InternalMarkup.g:2172:1: rule__OCLEvalElement__Group__1 : rule__OCLEvalElement__Group__1__Impl rule__OCLEvalElement__Group__2 ;
    public final void rule__OCLEvalElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2176:1: ( rule__OCLEvalElement__Group__1__Impl rule__OCLEvalElement__Group__2 )
            // InternalMarkup.g:2177:2: rule__OCLEvalElement__Group__1__Impl rule__OCLEvalElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__OCLEvalElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__1"


    // $ANTLR start "rule__OCLEvalElement__Group__1__Impl"
    // InternalMarkup.g:2184:1: rule__OCLEvalElement__Group__1__Impl : ( 'oclEval' ) ;
    public final void rule__OCLEvalElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2188:1: ( ( 'oclEval' ) )
            // InternalMarkup.g:2189:1: ( 'oclEval' )
            {
            // InternalMarkup.g:2189:1: ( 'oclEval' )
            // InternalMarkup.g:2190:2: 'oclEval'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getOclEvalKeyword_1());
            }
            match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getOclEvalKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__1__Impl"


    // $ANTLR start "rule__OCLEvalElement__Group__2"
    // InternalMarkup.g:2199:1: rule__OCLEvalElement__Group__2 : rule__OCLEvalElement__Group__2__Impl rule__OCLEvalElement__Group__3 ;
    public final void rule__OCLEvalElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2203:1: ( rule__OCLEvalElement__Group__2__Impl rule__OCLEvalElement__Group__3 )
            // InternalMarkup.g:2204:2: rule__OCLEvalElement__Group__2__Impl rule__OCLEvalElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLEvalElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__2"


    // $ANTLR start "rule__OCLEvalElement__Group__2__Impl"
    // InternalMarkup.g:2211:1: rule__OCLEvalElement__Group__2__Impl : ( '[' ) ;
    public final void rule__OCLEvalElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2215:1: ( ( '[' ) )
            // InternalMarkup.g:2216:1: ( '[' )
            {
            // InternalMarkup.g:2216:1: ( '[' )
            // InternalMarkup.g:2217:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getLeftSquareBracketKeyword_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getLeftSquareBracketKeyword_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__2__Impl"


    // $ANTLR start "rule__OCLEvalElement__Group__3"
    // InternalMarkup.g:2226:1: rule__OCLEvalElement__Group__3 : rule__OCLEvalElement__Group__3__Impl rule__OCLEvalElement__Group__4 ;
    public final void rule__OCLEvalElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2230:1: ( rule__OCLEvalElement__Group__3__Impl rule__OCLEvalElement__Group__4 )
            // InternalMarkup.g:2231:2: rule__OCLEvalElement__Group__3__Impl rule__OCLEvalElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLEvalElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__3"


    // $ANTLR start "rule__OCLEvalElement__Group__3__Impl"
    // InternalMarkup.g:2238:1: rule__OCLEvalElement__Group__3__Impl : ( ( rule__OCLEvalElement__ElementsAssignment_3 )* ) ;
    public final void rule__OCLEvalElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2242:1: ( ( ( rule__OCLEvalElement__ElementsAssignment_3 )* ) )
            // InternalMarkup.g:2243:1: ( ( rule__OCLEvalElement__ElementsAssignment_3 )* )
            {
            // InternalMarkup.g:2243:1: ( ( rule__OCLEvalElement__ElementsAssignment_3 )* )
            // InternalMarkup.g:2244:2: ( rule__OCLEvalElement__ElementsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getElementsAssignment_3());
            }
            // InternalMarkup.g:2245:2: ( rule__OCLEvalElement__ElementsAssignment_3 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=RULE_ID && LA20_0<=RULE_WS)||LA20_0==RULE_NL||(LA20_0>=16 && LA20_0<=29)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMarkup.g:2245:3: rule__OCLEvalElement__ElementsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__OCLEvalElement__ElementsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getElementsAssignment_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__3__Impl"


    // $ANTLR start "rule__OCLEvalElement__Group__4"
    // InternalMarkup.g:2253:1: rule__OCLEvalElement__Group__4 : rule__OCLEvalElement__Group__4__Impl ;
    public final void rule__OCLEvalElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2257:1: ( rule__OCLEvalElement__Group__4__Impl )
            // InternalMarkup.g:2258:2: rule__OCLEvalElement__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLEvalElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__4"


    // $ANTLR start "rule__OCLEvalElement__Group__4__Impl"
    // InternalMarkup.g:2264:1: rule__OCLEvalElement__Group__4__Impl : ( ']' ) ;
    public final void rule__OCLEvalElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2268:1: ( ( ']' ) )
            // InternalMarkup.g:2269:1: ( ']' )
            {
            // InternalMarkup.g:2269:1: ( ']' )
            // InternalMarkup.g:2270:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getRightSquareBracketKeyword_4());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getRightSquareBracketKeyword_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__Group__4__Impl"


    // $ANTLR start "rule__OCLTextElement__Group__0"
    // InternalMarkup.g:2280:1: rule__OCLTextElement__Group__0 : rule__OCLTextElement__Group__0__Impl rule__OCLTextElement__Group__1 ;
    public final void rule__OCLTextElement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2284:1: ( rule__OCLTextElement__Group__0__Impl rule__OCLTextElement__Group__1 )
            // InternalMarkup.g:2285:2: rule__OCLTextElement__Group__0__Impl rule__OCLTextElement__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_20);
            rule__OCLTextElement__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__0"


    // $ANTLR start "rule__OCLTextElement__Group__0__Impl"
    // InternalMarkup.g:2292:1: rule__OCLTextElement__Group__0__Impl : ( () ) ;
    public final void rule__OCLTextElement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2296:1: ( ( () ) )
            // InternalMarkup.g:2297:1: ( () )
            {
            // InternalMarkup.g:2297:1: ( () )
            // InternalMarkup.g:2298:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getOCLTextElementAction_0());
            }
            // InternalMarkup.g:2299:2: ()
            // InternalMarkup.g:2299:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getOCLTextElementAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__0__Impl"


    // $ANTLR start "rule__OCLTextElement__Group__1"
    // InternalMarkup.g:2307:1: rule__OCLTextElement__Group__1 : rule__OCLTextElement__Group__1__Impl rule__OCLTextElement__Group__2 ;
    public final void rule__OCLTextElement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2311:1: ( rule__OCLTextElement__Group__1__Impl rule__OCLTextElement__Group__2 )
            // InternalMarkup.g:2312:2: rule__OCLTextElement__Group__1__Impl rule__OCLTextElement__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__OCLTextElement__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__1"


    // $ANTLR start "rule__OCLTextElement__Group__1__Impl"
    // InternalMarkup.g:2319:1: rule__OCLTextElement__Group__1__Impl : ( 'oclText' ) ;
    public final void rule__OCLTextElement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2323:1: ( ( 'oclText' ) )
            // InternalMarkup.g:2324:1: ( 'oclText' )
            {
            // InternalMarkup.g:2324:1: ( 'oclText' )
            // InternalMarkup.g:2325:2: 'oclText'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getOclTextKeyword_1());
            }
            match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getOclTextKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__1__Impl"


    // $ANTLR start "rule__OCLTextElement__Group__2"
    // InternalMarkup.g:2334:1: rule__OCLTextElement__Group__2 : rule__OCLTextElement__Group__2__Impl rule__OCLTextElement__Group__3 ;
    public final void rule__OCLTextElement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2338:1: ( rule__OCLTextElement__Group__2__Impl rule__OCLTextElement__Group__3 )
            // InternalMarkup.g:2339:2: rule__OCLTextElement__Group__2__Impl rule__OCLTextElement__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLTextElement__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__2"


    // $ANTLR start "rule__OCLTextElement__Group__2__Impl"
    // InternalMarkup.g:2346:1: rule__OCLTextElement__Group__2__Impl : ( '[' ) ;
    public final void rule__OCLTextElement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2350:1: ( ( '[' ) )
            // InternalMarkup.g:2351:1: ( '[' )
            {
            // InternalMarkup.g:2351:1: ( '[' )
            // InternalMarkup.g:2352:2: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getLeftSquareBracketKeyword_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getLeftSquareBracketKeyword_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__2__Impl"


    // $ANTLR start "rule__OCLTextElement__Group__3"
    // InternalMarkup.g:2361:1: rule__OCLTextElement__Group__3 : rule__OCLTextElement__Group__3__Impl rule__OCLTextElement__Group__4 ;
    public final void rule__OCLTextElement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2365:1: ( rule__OCLTextElement__Group__3__Impl rule__OCLTextElement__Group__4 )
            // InternalMarkup.g:2366:2: rule__OCLTextElement__Group__3__Impl rule__OCLTextElement__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__OCLTextElement__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__4();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__3"


    // $ANTLR start "rule__OCLTextElement__Group__3__Impl"
    // InternalMarkup.g:2373:1: rule__OCLTextElement__Group__3__Impl : ( ( rule__OCLTextElement__ElementsAssignment_3 )* ) ;
    public final void rule__OCLTextElement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2377:1: ( ( ( rule__OCLTextElement__ElementsAssignment_3 )* ) )
            // InternalMarkup.g:2378:1: ( ( rule__OCLTextElement__ElementsAssignment_3 )* )
            {
            // InternalMarkup.g:2378:1: ( ( rule__OCLTextElement__ElementsAssignment_3 )* )
            // InternalMarkup.g:2379:2: ( rule__OCLTextElement__ElementsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getElementsAssignment_3());
            }
            // InternalMarkup.g:2380:2: ( rule__OCLTextElement__ElementsAssignment_3 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=RULE_ID && LA21_0<=RULE_WS)||LA21_0==RULE_NL||(LA21_0>=16 && LA21_0<=29)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalMarkup.g:2380:3: rule__OCLTextElement__ElementsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    rule__OCLTextElement__ElementsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getElementsAssignment_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__3__Impl"


    // $ANTLR start "rule__OCLTextElement__Group__4"
    // InternalMarkup.g:2388:1: rule__OCLTextElement__Group__4 : rule__OCLTextElement__Group__4__Impl ;
    public final void rule__OCLTextElement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2392:1: ( rule__OCLTextElement__Group__4__Impl )
            // InternalMarkup.g:2393:2: rule__OCLTextElement__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__OCLTextElement__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__4"


    // $ANTLR start "rule__OCLTextElement__Group__4__Impl"
    // InternalMarkup.g:2399:1: rule__OCLTextElement__Group__4__Impl : ( ']' ) ;
    public final void rule__OCLTextElement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2403:1: ( ( ']' ) )
            // InternalMarkup.g:2404:1: ( ']' )
            {
            // InternalMarkup.g:2404:1: ( ']' )
            // InternalMarkup.g:2405:2: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getRightSquareBracketKeyword_4());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getRightSquareBracketKeyword_4());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__Group__4__Impl"


    // $ANTLR start "rule__Markup__ElementsAssignment"
    // InternalMarkup.g:2415:1: rule__Markup__ElementsAssignment : ( ruleMarkupElement ) ;
    public final void rule__Markup__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2419:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2420:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2420:2: ( ruleMarkupElement )
            // InternalMarkup.g:2421:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMarkupAccess().getElementsMarkupElementParserRuleCall_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMarkupAccess().getElementsMarkupElementParserRuleCall_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Markup__ElementsAssignment"


    // $ANTLR start "rule__BulletElement__LevelAssignment_2_1"
    // InternalMarkup.g:2430:1: rule__BulletElement__LevelAssignment_2_1 : ( RULE_INT ) ;
    public final void rule__BulletElement__LevelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2434:1: ( ( RULE_INT ) )
            // InternalMarkup.g:2435:2: ( RULE_INT )
            {
            // InternalMarkup.g:2435:2: ( RULE_INT )
            // InternalMarkup.g:2436:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getLevelINTTerminalRuleCall_2_1_0());
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getLevelINTTerminalRuleCall_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__LevelAssignment_2_1"


    // $ANTLR start "rule__BulletElement__ElementsAssignment_4"
    // InternalMarkup.g:2445:1: rule__BulletElement__ElementsAssignment_4 : ( ruleMarkupElement ) ;
    public final void rule__BulletElement__ElementsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2449:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2450:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2450:2: ( ruleMarkupElement )
            // InternalMarkup.g:2451:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getBulletElementAccess().getElementsMarkupElementParserRuleCall_4_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getBulletElementAccess().getElementsMarkupElementParserRuleCall_4_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BulletElement__ElementsAssignment_4"


    // $ANTLR start "rule__FontElement__FontAssignment_0"
    // InternalMarkup.g:2460:1: rule__FontElement__FontAssignment_0 : ( ( rule__FontElement__FontAlternatives_0_0 ) ) ;
    public final void rule__FontElement__FontAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2464:1: ( ( ( rule__FontElement__FontAlternatives_0_0 ) ) )
            // InternalMarkup.g:2465:2: ( ( rule__FontElement__FontAlternatives_0_0 ) )
            {
            // InternalMarkup.g:2465:2: ( ( rule__FontElement__FontAlternatives_0_0 ) )
            // InternalMarkup.g:2466:3: ( rule__FontElement__FontAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getFontAlternatives_0_0());
            }
            // InternalMarkup.g:2467:3: ( rule__FontElement__FontAlternatives_0_0 )
            // InternalMarkup.g:2467:4: rule__FontElement__FontAlternatives_0_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FontElement__FontAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getFontAlternatives_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__FontAssignment_0"


    // $ANTLR start "rule__FontElement__ElementsAssignment_2"
    // InternalMarkup.g:2475:1: rule__FontElement__ElementsAssignment_2 : ( ruleMarkupElement ) ;
    public final void rule__FontElement__ElementsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2479:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2480:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2480:2: ( ruleMarkupElement )
            // InternalMarkup.g:2481:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFontElementAccess().getElementsMarkupElementParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFontElementAccess().getElementsMarkupElementParserRuleCall_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FontElement__ElementsAssignment_2"


    // $ANTLR start "rule__FigureElement__DefAssignment_1_1"
    // InternalMarkup.g:2490:1: rule__FigureElement__DefAssignment_1_1 : ( RULE_ID ) ;
    public final void rule__FigureElement__DefAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2494:1: ( ( RULE_ID ) )
            // InternalMarkup.g:2495:2: ( RULE_ID )
            {
            // InternalMarkup.g:2495:2: ( RULE_ID )
            // InternalMarkup.g:2496:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getDefIDTerminalRuleCall_1_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getDefIDTerminalRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__DefAssignment_1_1"


    // $ANTLR start "rule__FigureElement__SrcAssignment_3"
    // InternalMarkup.g:2505:1: rule__FigureElement__SrcAssignment_3 : ( RULE_STRING ) ;
    public final void rule__FigureElement__SrcAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2509:1: ( ( RULE_STRING ) )
            // InternalMarkup.g:2510:2: ( RULE_STRING )
            {
            // InternalMarkup.g:2510:2: ( RULE_STRING )
            // InternalMarkup.g:2511:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getSrcSTRINGTerminalRuleCall_3_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getSrcSTRINGTerminalRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__SrcAssignment_3"


    // $ANTLR start "rule__FigureElement__AltAssignment_4_1"
    // InternalMarkup.g:2520:1: rule__FigureElement__AltAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__FigureElement__AltAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2524:1: ( ( RULE_STRING ) )
            // InternalMarkup.g:2525:2: ( RULE_STRING )
            {
            // InternalMarkup.g:2525:2: ( RULE_STRING )
            // InternalMarkup.g:2526:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getAltSTRINGTerminalRuleCall_4_1_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getAltSTRINGTerminalRuleCall_4_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__AltAssignment_4_1"


    // $ANTLR start "rule__FigureElement__RequiredWidthAssignment_4_2_1"
    // InternalMarkup.g:2535:1: rule__FigureElement__RequiredWidthAssignment_4_2_1 : ( RULE_INT ) ;
    public final void rule__FigureElement__RequiredWidthAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2539:1: ( ( RULE_INT ) )
            // InternalMarkup.g:2540:2: ( RULE_INT )
            {
            // InternalMarkup.g:2540:2: ( RULE_INT )
            // InternalMarkup.g:2541:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getRequiredWidthINTTerminalRuleCall_4_2_1_0());
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getRequiredWidthINTTerminalRuleCall_4_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__RequiredWidthAssignment_4_2_1"


    // $ANTLR start "rule__FigureElement__RequiredHeightAssignment_4_2_2_1"
    // InternalMarkup.g:2550:1: rule__FigureElement__RequiredHeightAssignment_4_2_2_1 : ( RULE_INT ) ;
    public final void rule__FigureElement__RequiredHeightAssignment_4_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2554:1: ( ( RULE_INT ) )
            // InternalMarkup.g:2555:2: ( RULE_INT )
            {
            // InternalMarkup.g:2555:2: ( RULE_INT )
            // InternalMarkup.g:2556:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureElementAccess().getRequiredHeightINTTerminalRuleCall_4_2_2_1_0());
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureElementAccess().getRequiredHeightINTTerminalRuleCall_4_2_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureElement__RequiredHeightAssignment_4_2_2_1"


    // $ANTLR start "rule__FigureRefElement__RefAssignment_2"
    // InternalMarkup.g:2565:1: rule__FigureRefElement__RefAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__FigureRefElement__RefAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2569:1: ( ( ( RULE_ID ) ) )
            // InternalMarkup.g:2570:2: ( ( RULE_ID ) )
            {
            // InternalMarkup.g:2570:2: ( ( RULE_ID ) )
            // InternalMarkup.g:2571:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getRefFigureElementCrossReference_2_0());
            }
            // InternalMarkup.g:2572:3: ( RULE_ID )
            // InternalMarkup.g:2573:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFigureRefElementAccess().getRefFigureElementIDTerminalRuleCall_2_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getRefFigureElementIDTerminalRuleCall_2_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFigureRefElementAccess().getRefFigureElementCrossReference_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FigureRefElement__RefAssignment_2"


    // $ANTLR start "rule__FootnoteElement__ElementsAssignment_3"
    // InternalMarkup.g:2584:1: rule__FootnoteElement__ElementsAssignment_3 : ( ruleMarkupElement ) ;
    public final void rule__FootnoteElement__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2588:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2589:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2589:2: ( ruleMarkupElement )
            // InternalMarkup.g:2590:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFootnoteElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFootnoteElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FootnoteElement__ElementsAssignment_3"


    // $ANTLR start "rule__HeadingElement__LevelAssignment_2_1"
    // InternalMarkup.g:2599:1: rule__HeadingElement__LevelAssignment_2_1 : ( RULE_INT ) ;
    public final void rule__HeadingElement__LevelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2603:1: ( ( RULE_INT ) )
            // InternalMarkup.g:2604:2: ( RULE_INT )
            {
            // InternalMarkup.g:2604:2: ( RULE_INT )
            // InternalMarkup.g:2605:3: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getLevelINTTerminalRuleCall_2_1_0());
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getLevelINTTerminalRuleCall_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__LevelAssignment_2_1"


    // $ANTLR start "rule__HeadingElement__ElementsAssignment_4"
    // InternalMarkup.g:2614:1: rule__HeadingElement__ElementsAssignment_4 : ( ruleMarkupElement ) ;
    public final void rule__HeadingElement__ElementsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2618:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2619:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2619:2: ( ruleMarkupElement )
            // InternalMarkup.g:2620:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHeadingElementAccess().getElementsMarkupElementParserRuleCall_4_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHeadingElementAccess().getElementsMarkupElementParserRuleCall_4_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HeadingElement__ElementsAssignment_4"


    // $ANTLR start "rule__NewLineElement__TextAssignment"
    // InternalMarkup.g:2629:1: rule__NewLineElement__TextAssignment : ( RULE_NL ) ;
    public final void rule__NewLineElement__TextAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2633:1: ( ( RULE_NL ) )
            // InternalMarkup.g:2634:2: ( RULE_NL )
            {
            // InternalMarkup.g:2634:2: ( RULE_NL )
            // InternalMarkup.g:2635:3: RULE_NL
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineElementAccess().getTextNLTerminalRuleCall_0());
            }
            match(input,RULE_NL,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineElementAccess().getTextNLTerminalRuleCall_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewLineElement__TextAssignment"


    // $ANTLR start "rule__NullElement__ElementsAssignment_2"
    // InternalMarkup.g:2644:1: rule__NullElement__ElementsAssignment_2 : ( ruleMarkupElement ) ;
    public final void rule__NullElement__ElementsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2648:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2649:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2649:2: ( ruleMarkupElement )
            // InternalMarkup.g:2650:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNullElementAccess().getElementsMarkupElementParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNullElementAccess().getElementsMarkupElementParserRuleCall_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NullElement__ElementsAssignment_2"


    // $ANTLR start "rule__OCLCodeElement__ElementsAssignment_3"
    // InternalMarkup.g:2659:1: rule__OCLCodeElement__ElementsAssignment_3 : ( ruleMarkupElement ) ;
    public final void rule__OCLCodeElement__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2663:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2664:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2664:2: ( ruleMarkupElement )
            // InternalMarkup.g:2665:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLCodeElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLCodeElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLCodeElement__ElementsAssignment_3"


    // $ANTLR start "rule__OCLEvalElement__ElementsAssignment_3"
    // InternalMarkup.g:2674:1: rule__OCLEvalElement__ElementsAssignment_3 : ( ruleMarkupElement ) ;
    public final void rule__OCLEvalElement__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2678:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2679:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2679:2: ( ruleMarkupElement )
            // InternalMarkup.g:2680:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLEvalElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLEvalElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLEvalElement__ElementsAssignment_3"


    // $ANTLR start "rule__OCLTextElement__ElementsAssignment_3"
    // InternalMarkup.g:2689:1: rule__OCLTextElement__ElementsAssignment_3 : ( ruleMarkupElement ) ;
    public final void rule__OCLTextElement__ElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2693:1: ( ( ruleMarkupElement ) )
            // InternalMarkup.g:2694:2: ( ruleMarkupElement )
            {
            // InternalMarkup.g:2694:2: ( ruleMarkupElement )
            // InternalMarkup.g:2695:3: ruleMarkupElement
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getOCLTextElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupElement();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getOCLTextElementAccess().getElementsMarkupElementParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OCLTextElement__ElementsAssignment_3"


    // $ANTLR start "rule__TextElement__TextAssignment_0"
    // InternalMarkup.g:2704:1: rule__TextElement__TextAssignment_0 : ( ( rule__TextElement__TextAlternatives_0_0 ) ) ;
    public final void rule__TextElement__TextAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2708:1: ( ( ( rule__TextElement__TextAlternatives_0_0 ) ) )
            // InternalMarkup.g:2709:2: ( ( rule__TextElement__TextAlternatives_0_0 ) )
            {
            // InternalMarkup.g:2709:2: ( ( rule__TextElement__TextAlternatives_0_0 ) )
            // InternalMarkup.g:2710:3: ( rule__TextElement__TextAlternatives_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTextElementAccess().getTextAlternatives_0_0());
            }
            // InternalMarkup.g:2711:3: ( rule__TextElement__TextAlternatives_0_0 )
            // InternalMarkup.g:2711:4: rule__TextElement__TextAlternatives_0_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TextElement__TextAlternatives_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTextElementAccess().getTextAlternatives_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TextElement__TextAssignment_0"


    // $ANTLR start "rule__TextElement__TextAssignment_1"
    // InternalMarkup.g:2719:1: rule__TextElement__TextAssignment_1 : ( ruleMarkupKeyword ) ;
    public final void rule__TextElement__TextAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalMarkup.g:2723:1: ( ( ruleMarkupKeyword ) )
            // InternalMarkup.g:2724:2: ( ruleMarkupKeyword )
            {
            // InternalMarkup.g:2724:2: ( ruleMarkupKeyword )
            // InternalMarkup.g:2725:3: ruleMarkupKeyword
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTextElementAccess().getTextMarkupKeywordParserRuleCall_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMarkupKeyword();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTextElementAccess().getTextMarkupKeywordParserRuleCall_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TextElement__TextAssignment_1"

    // $ANTLR start synpred11_InternalMarkup
    public final void synpred11_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:511:2: ( ( ruleFontElement ) )
        // InternalMarkup.g:511:2: ( ruleFontElement )
        {
        // InternalMarkup.g:511:2: ( ruleFontElement )
        // InternalMarkup.g:512:3: ruleFontElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getFontElementParserRuleCall_0());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleFontElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred11_InternalMarkup

    // $ANTLR start synpred13_InternalMarkup
    public final void synpred13_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:523:2: ( ( ruleBulletElement ) )
        // InternalMarkup.g:523:2: ( ruleBulletElement )
        {
        // InternalMarkup.g:523:2: ( ruleBulletElement )
        // InternalMarkup.g:524:3: ruleBulletElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getBulletElementParserRuleCall_2());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleBulletElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred13_InternalMarkup

    // $ANTLR start synpred14_InternalMarkup
    public final void synpred14_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:529:2: ( ( ruleFigureElement ) )
        // InternalMarkup.g:529:2: ( ruleFigureElement )
        {
        // InternalMarkup.g:529:2: ( ruleFigureElement )
        // InternalMarkup.g:530:3: ruleFigureElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getFigureElementParserRuleCall_3());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleFigureElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred14_InternalMarkup

    // $ANTLR start synpred15_InternalMarkup
    public final void synpred15_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:535:2: ( ( ruleFigureRefElement ) )
        // InternalMarkup.g:535:2: ( ruleFigureRefElement )
        {
        // InternalMarkup.g:535:2: ( ruleFigureRefElement )
        // InternalMarkup.g:536:3: ruleFigureRefElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getFigureRefElementParserRuleCall_4());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleFigureRefElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred15_InternalMarkup

    // $ANTLR start synpred16_InternalMarkup
    public final void synpred16_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:541:2: ( ( ruleFootnoteElement ) )
        // InternalMarkup.g:541:2: ( ruleFootnoteElement )
        {
        // InternalMarkup.g:541:2: ( ruleFootnoteElement )
        // InternalMarkup.g:542:3: ruleFootnoteElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getFootnoteElementParserRuleCall_5());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleFootnoteElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred16_InternalMarkup

    // $ANTLR start synpred17_InternalMarkup
    public final void synpred17_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:547:2: ( ( ruleHeadingElement ) )
        // InternalMarkup.g:547:2: ( ruleHeadingElement )
        {
        // InternalMarkup.g:547:2: ( ruleHeadingElement )
        // InternalMarkup.g:548:3: ruleHeadingElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getHeadingElementParserRuleCall_6());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleHeadingElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred17_InternalMarkup

    // $ANTLR start synpred19_InternalMarkup
    public final void synpred19_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:559:2: ( ( ruleOCLCodeElement ) )
        // InternalMarkup.g:559:2: ( ruleOCLCodeElement )
        {
        // InternalMarkup.g:559:2: ( ruleOCLCodeElement )
        // InternalMarkup.g:560:3: ruleOCLCodeElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getOCLCodeElementParserRuleCall_8());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleOCLCodeElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred19_InternalMarkup

    // $ANTLR start synpred20_InternalMarkup
    public final void synpred20_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:565:2: ( ( ruleOCLEvalElement ) )
        // InternalMarkup.g:565:2: ( ruleOCLEvalElement )
        {
        // InternalMarkup.g:565:2: ( ruleOCLEvalElement )
        // InternalMarkup.g:566:3: ruleOCLEvalElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getOCLEvalElementParserRuleCall_9());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleOCLEvalElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred20_InternalMarkup

    // $ANTLR start synpred21_InternalMarkup
    public final void synpred21_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:571:2: ( ( ruleOCLTextElement ) )
        // InternalMarkup.g:571:2: ( ruleOCLTextElement )
        {
        // InternalMarkup.g:571:2: ( ruleOCLTextElement )
        // InternalMarkup.g:572:3: ruleOCLTextElement
        {
        if ( state.backtracking==0 ) {
           before(grammarAccess.getMarkupElementAccess().getOCLTextElementParserRuleCall_10());
        }
        pushFollow(FollowSets000.FOLLOW_2);
        ruleOCLTextElement();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred21_InternalMarkup

    // $ANTLR start synpred23_InternalMarkup
    public final void synpred23_InternalMarkup_fragment() throws RecognitionException {
        // InternalMarkup.g:621:5: ( rule__TextElement__TextAssignment_0 )
        // InternalMarkup.g:621:5: rule__TextElement__TextAssignment_0
        {
        pushFollow(FollowSets000.FOLLOW_2);
        rule__TextElement__TextAssignment_0();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred23_InternalMarkup

    // Delegated rules

    public final boolean synpred16_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_InternalMarkup() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_InternalMarkup_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA5 dfa5 = new DFA5(this);
    static final String dfa_1s = "\35\uffff";
    static final String dfa_2s = "\1\4\2\0\1\uffff\5\0\1\uffff\3\0\20\uffff";
    static final String dfa_3s = "\1\35\2\0\1\uffff\5\0\1\uffff\3\0\20\uffff";
    static final String dfa_4s = "\3\uffff\1\2\5\uffff\1\10\3\uffff\1\14\6\uffff\1\1\1\3\1\4\1\5\1\6\1\7\1\11\1\12\1\13";
    static final String dfa_5s = "\1\uffff\1\0\1\1\1\uffff\1\2\1\3\1\4\1\5\1\6\1\uffff\1\7\1\10\1\11\20\uffff}>";
    static final String[] dfa_6s = {
            "\4\15\1\uffff\1\3\6\uffff\1\1\1\2\1\4\1\5\1\6\1\7\1\10\1\12\1\13\1\14\3\15\1\11",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "506:1: rule__MarkupElement__Alternatives : ( ( ruleFontElement ) | ( ruleNewLineElement ) | ( ruleBulletElement ) | ( ruleFigureElement ) | ( ruleFigureRefElement ) | ( ruleFootnoteElement ) | ( ruleHeadingElement ) | ( ruleNullElement ) | ( ruleOCLCodeElement ) | ( ruleOCLEvalElement ) | ( ruleOCLTextElement ) | ( ruleTextElement ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA3_1 = input.LA(1);


                        int index3_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_InternalMarkup()) ) {s = 20;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA3_2 = input.LA(1);


                        int index3_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_InternalMarkup()) ) {s = 20;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA3_4 = input.LA(1);


                        int index3_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_InternalMarkup()) ) {s = 21;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA3_5 = input.LA(1);


                        int index3_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_InternalMarkup()) ) {s = 22;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA3_6 = input.LA(1);


                        int index3_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred15_InternalMarkup()) ) {s = 23;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA3_7 = input.LA(1);


                        int index3_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalMarkup()) ) {s = 24;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_7);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA3_8 = input.LA(1);


                        int index3_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalMarkup()) ) {s = 25;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_8);
                        if ( s>=0 ) return s;
                        break;
                    case 7 :
                        int LA3_10 = input.LA(1);


                        int index3_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred19_InternalMarkup()) ) {s = 26;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_10);
                        if ( s>=0 ) return s;
                        break;
                    case 8 :
                        int LA3_11 = input.LA(1);


                        int index3_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalMarkup()) ) {s = 27;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 :
                        int LA3_12 = input.LA(1);


                        int index3_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred21_InternalMarkup()) ) {s = 28;}

                        else if ( (true) ) {s = 13;}


                        input.seek(index3_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\12\uffff";
    static final String dfa_8s = "\1\1\11\uffff";
    static final String dfa_9s = "\1\4\1\uffff\7\0\1\uffff";
    static final String dfa_10s = "\1\36\1\uffff\7\0\1\uffff";
    static final String dfa_11s = "\1\uffff\1\2\7\uffff\1\1";
    static final String dfa_12s = "\2\uffff\1\4\1\0\1\2\1\3\1\5\1\1\1\6\1\uffff}>";
    static final String[] dfa_13s = {
            "\1\2\1\3\1\4\1\5\1\uffff\1\1\6\uffff\12\1\1\6\1\7\1\10\2\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "()* loopback of 621:4: ( rule__TextElement__TextAssignment_0 )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA5_3 = input.LA(1);


                        int index5_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA5_7 = input.LA(1);


                        int index5_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA5_4 = input.LA(1);


                        int index5_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_4);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA5_5 = input.LA(1);


                        int index5_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA5_2 = input.LA(1);


                        int index5_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_2);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA5_6 = input.LA(1);


                        int index5_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA5_8 = input.LA(1);


                        int index5_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalMarkup()) ) {s = 9;}

                        else if ( (true) ) {s = 1;}


                        input.seek(index5_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }



    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000003FFF02F2L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000001C0000F2L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000024000000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000007FFF02F0L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000028000000L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000050000000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002000000L});
    }


}