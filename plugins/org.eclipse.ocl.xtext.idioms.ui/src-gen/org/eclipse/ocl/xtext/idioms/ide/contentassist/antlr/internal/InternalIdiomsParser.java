package org.eclipse.ocl.xtext.idioms.ide.contentassist.antlr.internal;

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
import org.eclipse.ocl.xtext.idioms.services.IdiomsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
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
public class InternalIdiomsParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'each'", "'model'", "'.'", "'import'", "';'", "'as'", "'grammar'", "'with'", "'locator'", "'any-assignment'", "'any-element'", "'assignment'", "'::'", "'final'", "'returns'", "'rule'", "'segment'", "'custom'", "'half-new-line'", "'new-line'", "'no-space'", "'pop'", "'post-comment'", "'pre-comment'", "'push'", "'soft-new-line'", "'soft-space'", "'string'", "'value'", "'wrap-anchor'", "'wrap-begin-all'", "'wrap-begin-some'", "'wrap-end'", "'wrap-here'", "'idiom'", "'for'", "'in'", "'{'", "'}'", "'at'", "'do'", "'printable'", "'mixin'", "'all'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalIdiomsParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalIdiomsParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);

        }


    public String[] getTokenNames() { return InternalIdiomsParser.tokenNames; }
    public String getGrammarFileName() { return "InternalIdioms.g"; }


    	private IdiomsGrammarAccess grammarAccess;

    	public void setGrammarAccess(IdiomsGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleIdiomsModel"
    // InternalIdioms.g:63:1: entryRuleIdiomsModel : ruleIdiomsModel EOF ;
    public final void entryRuleIdiomsModel() throws RecognitionException {
        try {
            // InternalIdioms.g:64:1: ( ruleIdiomsModel EOF )
            // InternalIdioms.g:65:1: ruleIdiomsModel EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleIdiomsModel();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelRule());
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
    // $ANTLR end "entryRuleIdiomsModel"


    // $ANTLR start "ruleIdiomsModel"
    // InternalIdioms.g:72:1: ruleIdiomsModel : ( ( rule__IdiomsModel__Group__0 ) ) ;
    public final void ruleIdiomsModel() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:76:2: ( ( ( rule__IdiomsModel__Group__0 ) ) )
            // InternalIdioms.g:77:2: ( ( rule__IdiomsModel__Group__0 ) )
            {
            // InternalIdioms.g:77:2: ( ( rule__IdiomsModel__Group__0 ) )
            // InternalIdioms.g:78:3: ( rule__IdiomsModel__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getGroup());
            }
            // InternalIdioms.g:79:3: ( rule__IdiomsModel__Group__0 )
            // InternalIdioms.g:79:4: rule__IdiomsModel__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getGroup());
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
    // $ANTLR end "ruleIdiomsModel"


    // $ANTLR start "entryRuleEPackageDeclaration"
    // InternalIdioms.g:88:1: entryRuleEPackageDeclaration : ruleEPackageDeclaration EOF ;
    public final void entryRuleEPackageDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:89:1: ( ruleEPackageDeclaration EOF )
            // InternalIdioms.g:90:1: ruleEPackageDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleEPackageDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationRule());
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
    // $ANTLR end "entryRuleEPackageDeclaration"


    // $ANTLR start "ruleEPackageDeclaration"
    // InternalIdioms.g:97:1: ruleEPackageDeclaration : ( ( rule__EPackageDeclaration__Group__0 ) ) ;
    public final void ruleEPackageDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:101:2: ( ( ( rule__EPackageDeclaration__Group__0 ) ) )
            // InternalIdioms.g:102:2: ( ( rule__EPackageDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:102:2: ( ( rule__EPackageDeclaration__Group__0 ) )
            // InternalIdioms.g:103:3: ( rule__EPackageDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:104:3: ( rule__EPackageDeclaration__Group__0 )
            // InternalIdioms.g:104:4: rule__EPackageDeclaration__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getGroup());
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
    // $ANTLR end "ruleEPackageDeclaration"


    // $ANTLR start "entryRuleGrammarDeclaration"
    // InternalIdioms.g:113:1: entryRuleGrammarDeclaration : ruleGrammarDeclaration EOF ;
    public final void entryRuleGrammarDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:114:1: ( ruleGrammarDeclaration EOF )
            // InternalIdioms.g:115:1: ruleGrammarDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleGrammarDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationRule());
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
    // $ANTLR end "entryRuleGrammarDeclaration"


    // $ANTLR start "ruleGrammarDeclaration"
    // InternalIdioms.g:122:1: ruleGrammarDeclaration : ( ( rule__GrammarDeclaration__Group__0 ) ) ;
    public final void ruleGrammarDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:126:2: ( ( ( rule__GrammarDeclaration__Group__0 ) ) )
            // InternalIdioms.g:127:2: ( ( rule__GrammarDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:127:2: ( ( rule__GrammarDeclaration__Group__0 ) )
            // InternalIdioms.g:128:3: ( rule__GrammarDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:129:3: ( rule__GrammarDeclaration__Group__0 )
            // InternalIdioms.g:129:4: rule__GrammarDeclaration__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGroup());
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
    // $ANTLR end "ruleGrammarDeclaration"


    // $ANTLR start "entryRuleIdiomsImport"
    // InternalIdioms.g:138:1: entryRuleIdiomsImport : ruleIdiomsImport EOF ;
    public final void entryRuleIdiomsImport() throws RecognitionException {
        try {
            // InternalIdioms.g:139:1: ( ruleIdiomsImport EOF )
            // InternalIdioms.g:140:1: ruleIdiomsImport EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleIdiomsImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportRule());
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
    // $ANTLR end "entryRuleIdiomsImport"


    // $ANTLR start "ruleIdiomsImport"
    // InternalIdioms.g:147:1: ruleIdiomsImport : ( ( rule__IdiomsImport__Group__0 ) ) ;
    public final void ruleIdiomsImport() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:151:2: ( ( ( rule__IdiomsImport__Group__0 ) ) )
            // InternalIdioms.g:152:2: ( ( rule__IdiomsImport__Group__0 ) )
            {
            // InternalIdioms.g:152:2: ( ( rule__IdiomsImport__Group__0 ) )
            // InternalIdioms.g:153:3: ( rule__IdiomsImport__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getGroup());
            }
            // InternalIdioms.g:154:3: ( rule__IdiomsImport__Group__0 )
            // InternalIdioms.g:154:4: rule__IdiomsImport__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getGroup());
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
    // $ANTLR end "ruleIdiomsImport"


    // $ANTLR start "entryRuleLocatorDeclaration"
    // InternalIdioms.g:163:1: entryRuleLocatorDeclaration : ruleLocatorDeclaration EOF ;
    public final void entryRuleLocatorDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:164:1: ( ruleLocatorDeclaration EOF )
            // InternalIdioms.g:165:1: ruleLocatorDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleLocatorDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationRule());
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
    // $ANTLR end "entryRuleLocatorDeclaration"


    // $ANTLR start "ruleLocatorDeclaration"
    // InternalIdioms.g:172:1: ruleLocatorDeclaration : ( ( rule__LocatorDeclaration__Group__0 ) ) ;
    public final void ruleLocatorDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:176:2: ( ( ( rule__LocatorDeclaration__Group__0 ) ) )
            // InternalIdioms.g:177:2: ( ( rule__LocatorDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:177:2: ( ( rule__LocatorDeclaration__Group__0 ) )
            // InternalIdioms.g:178:3: ( rule__LocatorDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:179:3: ( rule__LocatorDeclaration__Group__0 )
            // InternalIdioms.g:179:4: rule__LocatorDeclaration__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getGroup());
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
    // $ANTLR end "ruleLocatorDeclaration"


    // $ANTLR start "entryRuleLocator"
    // InternalIdioms.g:188:1: entryRuleLocator : ruleLocator EOF ;
    public final void entryRuleLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:189:1: ( ruleLocator EOF )
            // InternalIdioms.g:190:1: ruleLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorRule());
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
    // $ANTLR end "entryRuleLocator"


    // $ANTLR start "ruleLocator"
    // InternalIdioms.g:197:1: ruleLocator : ( ( rule__Locator__Alternatives ) ) ;
    public final void ruleLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:201:2: ( ( ( rule__Locator__Alternatives ) ) )
            // InternalIdioms.g:202:2: ( ( rule__Locator__Alternatives ) )
            {
            // InternalIdioms.g:202:2: ( ( rule__Locator__Alternatives ) )
            // InternalIdioms.g:203:3: ( rule__Locator__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorAccess().getAlternatives());
            }
            // InternalIdioms.g:204:3: ( rule__Locator__Alternatives )
            // InternalIdioms.g:204:4: rule__Locator__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Locator__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorAccess().getAlternatives());
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
    // $ANTLR end "ruleLocator"


    // $ANTLR start "entryRuleAnyAssignmentLocator"
    // InternalIdioms.g:213:1: entryRuleAnyAssignmentLocator : ruleAnyAssignmentLocator EOF ;
    public final void entryRuleAnyAssignmentLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:214:1: ( ruleAnyAssignmentLocator EOF )
            // InternalIdioms.g:215:1: ruleAnyAssignmentLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleAnyAssignmentLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyAssignmentLocatorRule());
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
    // $ANTLR end "entryRuleAnyAssignmentLocator"


    // $ANTLR start "ruleAnyAssignmentLocator"
    // InternalIdioms.g:222:1: ruleAnyAssignmentLocator : ( ( rule__AnyAssignmentLocator__Group__0 ) ) ;
    public final void ruleAnyAssignmentLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:226:2: ( ( ( rule__AnyAssignmentLocator__Group__0 ) ) )
            // InternalIdioms.g:227:2: ( ( rule__AnyAssignmentLocator__Group__0 ) )
            {
            // InternalIdioms.g:227:2: ( ( rule__AnyAssignmentLocator__Group__0 ) )
            // InternalIdioms.g:228:3: ( rule__AnyAssignmentLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getGroup());
            }
            // InternalIdioms.g:229:3: ( rule__AnyAssignmentLocator__Group__0 )
            // InternalIdioms.g:229:4: rule__AnyAssignmentLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyAssignmentLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyAssignmentLocatorAccess().getGroup());
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
    // $ANTLR end "ruleAnyAssignmentLocator"


    // $ANTLR start "entryRuleAnyElementLocator"
    // InternalIdioms.g:238:1: entryRuleAnyElementLocator : ruleAnyElementLocator EOF ;
    public final void entryRuleAnyElementLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:239:1: ( ruleAnyElementLocator EOF )
            // InternalIdioms.g:240:1: ruleAnyElementLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleAnyElementLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyElementLocatorRule());
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
    // $ANTLR end "entryRuleAnyElementLocator"


    // $ANTLR start "ruleAnyElementLocator"
    // InternalIdioms.g:247:1: ruleAnyElementLocator : ( ( rule__AnyElementLocator__Group__0 ) ) ;
    public final void ruleAnyElementLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:251:2: ( ( ( rule__AnyElementLocator__Group__0 ) ) )
            // InternalIdioms.g:252:2: ( ( rule__AnyElementLocator__Group__0 ) )
            {
            // InternalIdioms.g:252:2: ( ( rule__AnyElementLocator__Group__0 ) )
            // InternalIdioms.g:253:3: ( rule__AnyElementLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getGroup());
            }
            // InternalIdioms.g:254:3: ( rule__AnyElementLocator__Group__0 )
            // InternalIdioms.g:254:4: rule__AnyElementLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyElementLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyElementLocatorAccess().getGroup());
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
    // $ANTLR end "ruleAnyElementLocator"


    // $ANTLR start "entryRuleAssignmentLocator"
    // InternalIdioms.g:263:1: entryRuleAssignmentLocator : ruleAssignmentLocator EOF ;
    public final void entryRuleAssignmentLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:264:1: ( ruleAssignmentLocator EOF )
            // InternalIdioms.g:265:1: ruleAssignmentLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleAssignmentLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorRule());
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
    // $ANTLR end "entryRuleAssignmentLocator"


    // $ANTLR start "ruleAssignmentLocator"
    // InternalIdioms.g:272:1: ruleAssignmentLocator : ( ( rule__AssignmentLocator__Group__0 ) ) ;
    public final void ruleAssignmentLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:276:2: ( ( ( rule__AssignmentLocator__Group__0 ) ) )
            // InternalIdioms.g:277:2: ( ( rule__AssignmentLocator__Group__0 ) )
            {
            // InternalIdioms.g:277:2: ( ( rule__AssignmentLocator__Group__0 ) )
            // InternalIdioms.g:278:3: ( rule__AssignmentLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup());
            }
            // InternalIdioms.g:279:3: ( rule__AssignmentLocator__Group__0 )
            // InternalIdioms.g:279:4: rule__AssignmentLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getGroup());
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
    // $ANTLR end "ruleAssignmentLocator"


    // $ANTLR start "entryRuleFinalLocator"
    // InternalIdioms.g:288:1: entryRuleFinalLocator : ruleFinalLocator EOF ;
    public final void entryRuleFinalLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:289:1: ( ruleFinalLocator EOF )
            // InternalIdioms.g:290:1: ruleFinalLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFinalLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFinalLocatorRule());
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
    // $ANTLR end "entryRuleFinalLocator"


    // $ANTLR start "ruleFinalLocator"
    // InternalIdioms.g:297:1: ruleFinalLocator : ( ( rule__FinalLocator__Group__0 ) ) ;
    public final void ruleFinalLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:301:2: ( ( ( rule__FinalLocator__Group__0 ) ) )
            // InternalIdioms.g:302:2: ( ( rule__FinalLocator__Group__0 ) )
            {
            // InternalIdioms.g:302:2: ( ( rule__FinalLocator__Group__0 ) )
            // InternalIdioms.g:303:3: ( rule__FinalLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getGroup());
            }
            // InternalIdioms.g:304:3: ( rule__FinalLocator__Group__0 )
            // InternalIdioms.g:304:4: rule__FinalLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FinalLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFinalLocatorAccess().getGroup());
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
    // $ANTLR end "ruleFinalLocator"


    // $ANTLR start "entryRuleKeywordLocator"
    // InternalIdioms.g:313:1: entryRuleKeywordLocator : ruleKeywordLocator EOF ;
    public final void entryRuleKeywordLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:314:1: ( ruleKeywordLocator EOF )
            // InternalIdioms.g:315:1: ruleKeywordLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeywordLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleKeywordLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeywordLocatorRule());
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
    // $ANTLR end "entryRuleKeywordLocator"


    // $ANTLR start "ruleKeywordLocator"
    // InternalIdioms.g:322:1: ruleKeywordLocator : ( ( rule__KeywordLocator__StringAssignment ) ) ;
    public final void ruleKeywordLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:326:2: ( ( ( rule__KeywordLocator__StringAssignment ) ) )
            // InternalIdioms.g:327:2: ( ( rule__KeywordLocator__StringAssignment ) )
            {
            // InternalIdioms.g:327:2: ( ( rule__KeywordLocator__StringAssignment ) )
            // InternalIdioms.g:328:3: ( rule__KeywordLocator__StringAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeywordLocatorAccess().getStringAssignment());
            }
            // InternalIdioms.g:329:3: ( rule__KeywordLocator__StringAssignment )
            // InternalIdioms.g:329:4: rule__KeywordLocator__StringAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__KeywordLocator__StringAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeywordLocatorAccess().getStringAssignment());
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
    // $ANTLR end "ruleKeywordLocator"


    // $ANTLR start "entryRuleReturnsLocator"
    // InternalIdioms.g:338:1: entryRuleReturnsLocator : ruleReturnsLocator EOF ;
    public final void entryRuleReturnsLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:339:1: ( ruleReturnsLocator EOF )
            // InternalIdioms.g:340:1: ruleReturnsLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleReturnsLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorRule());
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
    // $ANTLR end "entryRuleReturnsLocator"


    // $ANTLR start "ruleReturnsLocator"
    // InternalIdioms.g:347:1: ruleReturnsLocator : ( ( rule__ReturnsLocator__Group__0 ) ) ;
    public final void ruleReturnsLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:351:2: ( ( ( rule__ReturnsLocator__Group__0 ) ) )
            // InternalIdioms.g:352:2: ( ( rule__ReturnsLocator__Group__0 ) )
            {
            // InternalIdioms.g:352:2: ( ( rule__ReturnsLocator__Group__0 ) )
            // InternalIdioms.g:353:3: ( rule__ReturnsLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup());
            }
            // InternalIdioms.g:354:3: ( rule__ReturnsLocator__Group__0 )
            // InternalIdioms.g:354:4: rule__ReturnsLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getGroup());
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
    // $ANTLR end "ruleReturnsLocator"


    // $ANTLR start "entryRuleReferredLocator"
    // InternalIdioms.g:363:1: entryRuleReferredLocator : ruleReferredLocator EOF ;
    public final void entryRuleReferredLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:364:1: ( ruleReferredLocator EOF )
            // InternalIdioms.g:365:1: ruleReferredLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleReferredLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorRule());
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
    // $ANTLR end "entryRuleReferredLocator"


    // $ANTLR start "ruleReferredLocator"
    // InternalIdioms.g:372:1: ruleReferredLocator : ( ( rule__ReferredLocator__Group__0 ) ) ;
    public final void ruleReferredLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:376:2: ( ( ( rule__ReferredLocator__Group__0 ) ) )
            // InternalIdioms.g:377:2: ( ( rule__ReferredLocator__Group__0 ) )
            {
            // InternalIdioms.g:377:2: ( ( rule__ReferredLocator__Group__0 ) )
            // InternalIdioms.g:378:3: ( rule__ReferredLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup());
            }
            // InternalIdioms.g:379:3: ( rule__ReferredLocator__Group__0 )
            // InternalIdioms.g:379:4: rule__ReferredLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getGroup());
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
    // $ANTLR end "ruleReferredLocator"


    // $ANTLR start "entryRuleRuleLocator"
    // InternalIdioms.g:388:1: entryRuleRuleLocator : ruleRuleLocator EOF ;
    public final void entryRuleRuleLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:389:1: ( ruleRuleLocator EOF )
            // InternalIdioms.g:390:1: ruleRuleLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleRuleLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorRule());
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
    // $ANTLR end "entryRuleRuleLocator"


    // $ANTLR start "ruleRuleLocator"
    // InternalIdioms.g:397:1: ruleRuleLocator : ( ( rule__RuleLocator__Group__0 ) ) ;
    public final void ruleRuleLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:401:2: ( ( ( rule__RuleLocator__Group__0 ) ) )
            // InternalIdioms.g:402:2: ( ( rule__RuleLocator__Group__0 ) )
            {
            // InternalIdioms.g:402:2: ( ( rule__RuleLocator__Group__0 ) )
            // InternalIdioms.g:403:3: ( rule__RuleLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getGroup());
            }
            // InternalIdioms.g:404:3: ( rule__RuleLocator__Group__0 )
            // InternalIdioms.g:404:4: rule__RuleLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getGroup());
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
    // $ANTLR end "ruleRuleLocator"


    // $ANTLR start "entryRuleSegmentDeclaration"
    // InternalIdioms.g:413:1: entryRuleSegmentDeclaration : ruleSegmentDeclaration EOF ;
    public final void entryRuleSegmentDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:414:1: ( ruleSegmentDeclaration EOF )
            // InternalIdioms.g:415:1: ruleSegmentDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleSegmentDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationRule());
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
    // $ANTLR end "entryRuleSegmentDeclaration"


    // $ANTLR start "ruleSegmentDeclaration"
    // InternalIdioms.g:422:1: ruleSegmentDeclaration : ( ( rule__SegmentDeclaration__Group__0 ) ) ;
    public final void ruleSegmentDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:426:2: ( ( ( rule__SegmentDeclaration__Group__0 ) ) )
            // InternalIdioms.g:427:2: ( ( rule__SegmentDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:427:2: ( ( rule__SegmentDeclaration__Group__0 ) )
            // InternalIdioms.g:428:3: ( rule__SegmentDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:429:3: ( rule__SegmentDeclaration__Group__0 )
            // InternalIdioms.g:429:4: rule__SegmentDeclaration__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getGroup());
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
    // $ANTLR end "ruleSegmentDeclaration"


    // $ANTLR start "entryRuleSegment"
    // InternalIdioms.g:438:1: entryRuleSegment : ruleSegment EOF ;
    public final void entryRuleSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:439:1: ( ruleSegment EOF )
            // InternalIdioms.g:440:1: ruleSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentRule());
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
    // $ANTLR end "entryRuleSegment"


    // $ANTLR start "ruleSegment"
    // InternalIdioms.g:447:1: ruleSegment : ( ( rule__Segment__Alternatives ) ) ;
    public final void ruleSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:451:2: ( ( ( rule__Segment__Alternatives ) ) )
            // InternalIdioms.g:452:2: ( ( rule__Segment__Alternatives ) )
            {
            // InternalIdioms.g:452:2: ( ( rule__Segment__Alternatives ) )
            // InternalIdioms.g:453:3: ( rule__Segment__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentAccess().getAlternatives());
            }
            // InternalIdioms.g:454:3: ( rule__Segment__Alternatives )
            // InternalIdioms.g:454:4: rule__Segment__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Segment__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentAccess().getAlternatives());
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
    // $ANTLR end "ruleSegment"


    // $ANTLR start "entryRuleCustomSegment"
    // InternalIdioms.g:463:1: entryRuleCustomSegment : ruleCustomSegment EOF ;
    public final void entryRuleCustomSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:464:1: ( ruleCustomSegment EOF )
            // InternalIdioms.g:465:1: ruleCustomSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleCustomSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCustomSegmentRule());
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
    // $ANTLR end "entryRuleCustomSegment"


    // $ANTLR start "ruleCustomSegment"
    // InternalIdioms.g:472:1: ruleCustomSegment : ( ( rule__CustomSegment__Group__0 ) ) ;
    public final void ruleCustomSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:476:2: ( ( ( rule__CustomSegment__Group__0 ) ) )
            // InternalIdioms.g:477:2: ( ( rule__CustomSegment__Group__0 ) )
            {
            // InternalIdioms.g:477:2: ( ( rule__CustomSegment__Group__0 ) )
            // InternalIdioms.g:478:3: ( rule__CustomSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getGroup());
            }
            // InternalIdioms.g:479:3: ( rule__CustomSegment__Group__0 )
            // InternalIdioms.g:479:4: rule__CustomSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CustomSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCustomSegmentAccess().getGroup());
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
    // $ANTLR end "ruleCustomSegment"


    // $ANTLR start "entryRuleHalfNewLineSegment"
    // InternalIdioms.g:488:1: entryRuleHalfNewLineSegment : ruleHalfNewLineSegment EOF ;
    public final void entryRuleHalfNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:489:1: ( ruleHalfNewLineSegment EOF )
            // InternalIdioms.g:490:1: ruleHalfNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleHalfNewLineSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHalfNewLineSegmentRule());
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
    // $ANTLR end "entryRuleHalfNewLineSegment"


    // $ANTLR start "ruleHalfNewLineSegment"
    // InternalIdioms.g:497:1: ruleHalfNewLineSegment : ( ( rule__HalfNewLineSegment__Group__0 ) ) ;
    public final void ruleHalfNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:501:2: ( ( ( rule__HalfNewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:502:2: ( ( rule__HalfNewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:502:2: ( ( rule__HalfNewLineSegment__Group__0 ) )
            // InternalIdioms.g:503:3: ( rule__HalfNewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:504:3: ( rule__HalfNewLineSegment__Group__0 )
            // InternalIdioms.g:504:4: rule__HalfNewLineSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HalfNewLineSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHalfNewLineSegmentAccess().getGroup());
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
    // $ANTLR end "ruleHalfNewLineSegment"


    // $ANTLR start "entryRuleNewLineSegment"
    // InternalIdioms.g:513:1: entryRuleNewLineSegment : ruleNewLineSegment EOF ;
    public final void entryRuleNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:514:1: ( ruleNewLineSegment EOF )
            // InternalIdioms.g:515:1: ruleNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNewLineSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineSegmentRule());
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
    // $ANTLR end "entryRuleNewLineSegment"


    // $ANTLR start "ruleNewLineSegment"
    // InternalIdioms.g:522:1: ruleNewLineSegment : ( ( rule__NewLineSegment__Group__0 ) ) ;
    public final void ruleNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:526:2: ( ( ( rule__NewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:527:2: ( ( rule__NewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:527:2: ( ( rule__NewLineSegment__Group__0 ) )
            // InternalIdioms.g:528:3: ( rule__NewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:529:3: ( rule__NewLineSegment__Group__0 )
            // InternalIdioms.g:529:4: rule__NewLineSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NewLineSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineSegmentAccess().getGroup());
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
    // $ANTLR end "ruleNewLineSegment"


    // $ANTLR start "entryRuleNoSpaceSegment"
    // InternalIdioms.g:538:1: entryRuleNoSpaceSegment : ruleNoSpaceSegment EOF ;
    public final void entryRuleNoSpaceSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:539:1: ( ruleNoSpaceSegment EOF )
            // InternalIdioms.g:540:1: ruleNoSpaceSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNoSpaceSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNoSpaceSegmentRule());
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
    // $ANTLR end "entryRuleNoSpaceSegment"


    // $ANTLR start "ruleNoSpaceSegment"
    // InternalIdioms.g:547:1: ruleNoSpaceSegment : ( ( rule__NoSpaceSegment__Group__0 ) ) ;
    public final void ruleNoSpaceSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:551:2: ( ( ( rule__NoSpaceSegment__Group__0 ) ) )
            // InternalIdioms.g:552:2: ( ( rule__NoSpaceSegment__Group__0 ) )
            {
            // InternalIdioms.g:552:2: ( ( rule__NoSpaceSegment__Group__0 ) )
            // InternalIdioms.g:553:3: ( rule__NoSpaceSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getGroup());
            }
            // InternalIdioms.g:554:3: ( rule__NoSpaceSegment__Group__0 )
            // InternalIdioms.g:554:4: rule__NoSpaceSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NoSpaceSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNoSpaceSegmentAccess().getGroup());
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
    // $ANTLR end "ruleNoSpaceSegment"


    // $ANTLR start "entryRulePopSegment"
    // InternalIdioms.g:563:1: entryRulePopSegment : rulePopSegment EOF ;
    public final void entryRulePopSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:564:1: ( rulePopSegment EOF )
            // InternalIdioms.g:565:1: rulePopSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            rulePopSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPopSegmentRule());
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
    // $ANTLR end "entryRulePopSegment"


    // $ANTLR start "rulePopSegment"
    // InternalIdioms.g:572:1: rulePopSegment : ( ( rule__PopSegment__Group__0 ) ) ;
    public final void rulePopSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:576:2: ( ( ( rule__PopSegment__Group__0 ) ) )
            // InternalIdioms.g:577:2: ( ( rule__PopSegment__Group__0 ) )
            {
            // InternalIdioms.g:577:2: ( ( rule__PopSegment__Group__0 ) )
            // InternalIdioms.g:578:3: ( rule__PopSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getGroup());
            }
            // InternalIdioms.g:579:3: ( rule__PopSegment__Group__0 )
            // InternalIdioms.g:579:4: rule__PopSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PopSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPopSegmentAccess().getGroup());
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
    // $ANTLR end "rulePopSegment"


    // $ANTLR start "entryRulePostCommentSegment"
    // InternalIdioms.g:588:1: entryRulePostCommentSegment : rulePostCommentSegment EOF ;
    public final void entryRulePostCommentSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:589:1: ( rulePostCommentSegment EOF )
            // InternalIdioms.g:590:1: rulePostCommentSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            rulePostCommentSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPostCommentSegmentRule());
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
    // $ANTLR end "entryRulePostCommentSegment"


    // $ANTLR start "rulePostCommentSegment"
    // InternalIdioms.g:597:1: rulePostCommentSegment : ( ( rule__PostCommentSegment__Group__0 ) ) ;
    public final void rulePostCommentSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:601:2: ( ( ( rule__PostCommentSegment__Group__0 ) ) )
            // InternalIdioms.g:602:2: ( ( rule__PostCommentSegment__Group__0 ) )
            {
            // InternalIdioms.g:602:2: ( ( rule__PostCommentSegment__Group__0 ) )
            // InternalIdioms.g:603:3: ( rule__PostCommentSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getGroup());
            }
            // InternalIdioms.g:604:3: ( rule__PostCommentSegment__Group__0 )
            // InternalIdioms.g:604:4: rule__PostCommentSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PostCommentSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPostCommentSegmentAccess().getGroup());
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
    // $ANTLR end "rulePostCommentSegment"


    // $ANTLR start "entryRulePreCommentSegment"
    // InternalIdioms.g:613:1: entryRulePreCommentSegment : rulePreCommentSegment EOF ;
    public final void entryRulePreCommentSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:614:1: ( rulePreCommentSegment EOF )
            // InternalIdioms.g:615:1: rulePreCommentSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            rulePreCommentSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPreCommentSegmentRule());
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
    // $ANTLR end "entryRulePreCommentSegment"


    // $ANTLR start "rulePreCommentSegment"
    // InternalIdioms.g:622:1: rulePreCommentSegment : ( ( rule__PreCommentSegment__Group__0 ) ) ;
    public final void rulePreCommentSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:626:2: ( ( ( rule__PreCommentSegment__Group__0 ) ) )
            // InternalIdioms.g:627:2: ( ( rule__PreCommentSegment__Group__0 ) )
            {
            // InternalIdioms.g:627:2: ( ( rule__PreCommentSegment__Group__0 ) )
            // InternalIdioms.g:628:3: ( rule__PreCommentSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getGroup());
            }
            // InternalIdioms.g:629:3: ( rule__PreCommentSegment__Group__0 )
            // InternalIdioms.g:629:4: rule__PreCommentSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PreCommentSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPreCommentSegmentAccess().getGroup());
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
    // $ANTLR end "rulePreCommentSegment"


    // $ANTLR start "entryRulePushSegment"
    // InternalIdioms.g:638:1: entryRulePushSegment : rulePushSegment EOF ;
    public final void entryRulePushSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:639:1: ( rulePushSegment EOF )
            // InternalIdioms.g:640:1: rulePushSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            rulePushSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPushSegmentRule());
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
    // $ANTLR end "entryRulePushSegment"


    // $ANTLR start "rulePushSegment"
    // InternalIdioms.g:647:1: rulePushSegment : ( ( rule__PushSegment__Group__0 ) ) ;
    public final void rulePushSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:651:2: ( ( ( rule__PushSegment__Group__0 ) ) )
            // InternalIdioms.g:652:2: ( ( rule__PushSegment__Group__0 ) )
            {
            // InternalIdioms.g:652:2: ( ( rule__PushSegment__Group__0 ) )
            // InternalIdioms.g:653:3: ( rule__PushSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getGroup());
            }
            // InternalIdioms.g:654:3: ( rule__PushSegment__Group__0 )
            // InternalIdioms.g:654:4: rule__PushSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PushSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPushSegmentAccess().getGroup());
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
    // $ANTLR end "rulePushSegment"


    // $ANTLR start "entryRuleSoftNewLineSegment"
    // InternalIdioms.g:663:1: entryRuleSoftNewLineSegment : ruleSoftNewLineSegment EOF ;
    public final void entryRuleSoftNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:664:1: ( ruleSoftNewLineSegment EOF )
            // InternalIdioms.g:665:1: ruleSoftNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleSoftNewLineSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftNewLineSegmentRule());
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
    // $ANTLR end "entryRuleSoftNewLineSegment"


    // $ANTLR start "ruleSoftNewLineSegment"
    // InternalIdioms.g:672:1: ruleSoftNewLineSegment : ( ( rule__SoftNewLineSegment__Group__0 ) ) ;
    public final void ruleSoftNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:676:2: ( ( ( rule__SoftNewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:677:2: ( ( rule__SoftNewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:677:2: ( ( rule__SoftNewLineSegment__Group__0 ) )
            // InternalIdioms.g:678:3: ( rule__SoftNewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:679:3: ( rule__SoftNewLineSegment__Group__0 )
            // InternalIdioms.g:679:4: rule__SoftNewLineSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftNewLineSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftNewLineSegmentAccess().getGroup());
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
    // $ANTLR end "ruleSoftNewLineSegment"


    // $ANTLR start "entryRuleSoftSpaceSegment"
    // InternalIdioms.g:688:1: entryRuleSoftSpaceSegment : ruleSoftSpaceSegment EOF ;
    public final void entryRuleSoftSpaceSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:689:1: ( ruleSoftSpaceSegment EOF )
            // InternalIdioms.g:690:1: ruleSoftSpaceSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleSoftSpaceSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftSpaceSegmentRule());
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
    // $ANTLR end "entryRuleSoftSpaceSegment"


    // $ANTLR start "ruleSoftSpaceSegment"
    // InternalIdioms.g:697:1: ruleSoftSpaceSegment : ( ( rule__SoftSpaceSegment__Group__0 ) ) ;
    public final void ruleSoftSpaceSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:701:2: ( ( ( rule__SoftSpaceSegment__Group__0 ) ) )
            // InternalIdioms.g:702:2: ( ( rule__SoftSpaceSegment__Group__0 ) )
            {
            // InternalIdioms.g:702:2: ( ( rule__SoftSpaceSegment__Group__0 ) )
            // InternalIdioms.g:703:3: ( rule__SoftSpaceSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getGroup());
            }
            // InternalIdioms.g:704:3: ( rule__SoftSpaceSegment__Group__0 )
            // InternalIdioms.g:704:4: rule__SoftSpaceSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftSpaceSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftSpaceSegmentAccess().getGroup());
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
    // $ANTLR end "ruleSoftSpaceSegment"


    // $ANTLR start "entryRuleStringSegment"
    // InternalIdioms.g:713:1: entryRuleStringSegment : ruleStringSegment EOF ;
    public final void entryRuleStringSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:714:1: ( ruleStringSegment EOF )
            // InternalIdioms.g:715:1: ruleStringSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleStringSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentRule());
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
    // $ANTLR end "entryRuleStringSegment"


    // $ANTLR start "ruleStringSegment"
    // InternalIdioms.g:722:1: ruleStringSegment : ( ( rule__StringSegment__Group__0 ) ) ;
    public final void ruleStringSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:726:2: ( ( ( rule__StringSegment__Group__0 ) ) )
            // InternalIdioms.g:727:2: ( ( rule__StringSegment__Group__0 ) )
            {
            // InternalIdioms.g:727:2: ( ( rule__StringSegment__Group__0 ) )
            // InternalIdioms.g:728:3: ( rule__StringSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getGroup());
            }
            // InternalIdioms.g:729:3: ( rule__StringSegment__Group__0 )
            // InternalIdioms.g:729:4: rule__StringSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getGroup());
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
    // $ANTLR end "ruleStringSegment"


    // $ANTLR start "entryRuleValueSegment"
    // InternalIdioms.g:738:1: entryRuleValueSegment : ruleValueSegment EOF ;
    public final void entryRuleValueSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:739:1: ( ruleValueSegment EOF )
            // InternalIdioms.g:740:1: ruleValueSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleValueSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueSegmentRule());
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
    // $ANTLR end "entryRuleValueSegment"


    // $ANTLR start "ruleValueSegment"
    // InternalIdioms.g:747:1: ruleValueSegment : ( ( rule__ValueSegment__Group__0 ) ) ;
    public final void ruleValueSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:751:2: ( ( ( rule__ValueSegment__Group__0 ) ) )
            // InternalIdioms.g:752:2: ( ( rule__ValueSegment__Group__0 ) )
            {
            // InternalIdioms.g:752:2: ( ( rule__ValueSegment__Group__0 ) )
            // InternalIdioms.g:753:3: ( rule__ValueSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getGroup());
            }
            // InternalIdioms.g:754:3: ( rule__ValueSegment__Group__0 )
            // InternalIdioms.g:754:4: rule__ValueSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ValueSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueSegmentAccess().getGroup());
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
    // $ANTLR end "ruleValueSegment"


    // $ANTLR start "entryRuleWrapAnchorSegment"
    // InternalIdioms.g:763:1: entryRuleWrapAnchorSegment : ruleWrapAnchorSegment EOF ;
    public final void entryRuleWrapAnchorSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:764:1: ( ruleWrapAnchorSegment EOF )
            // InternalIdioms.g:765:1: ruleWrapAnchorSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWrapAnchorSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapAnchorSegmentRule());
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
    // $ANTLR end "entryRuleWrapAnchorSegment"


    // $ANTLR start "ruleWrapAnchorSegment"
    // InternalIdioms.g:772:1: ruleWrapAnchorSegment : ( ( rule__WrapAnchorSegment__Group__0 ) ) ;
    public final void ruleWrapAnchorSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:776:2: ( ( ( rule__WrapAnchorSegment__Group__0 ) ) )
            // InternalIdioms.g:777:2: ( ( rule__WrapAnchorSegment__Group__0 ) )
            {
            // InternalIdioms.g:777:2: ( ( rule__WrapAnchorSegment__Group__0 ) )
            // InternalIdioms.g:778:3: ( rule__WrapAnchorSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getGroup());
            }
            // InternalIdioms.g:779:3: ( rule__WrapAnchorSegment__Group__0 )
            // InternalIdioms.g:779:4: rule__WrapAnchorSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapAnchorSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapAnchorSegmentAccess().getGroup());
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
    // $ANTLR end "ruleWrapAnchorSegment"


    // $ANTLR start "entryRuleWrapBeginAllSegment"
    // InternalIdioms.g:788:1: entryRuleWrapBeginAllSegment : ruleWrapBeginAllSegment EOF ;
    public final void entryRuleWrapBeginAllSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:789:1: ( ruleWrapBeginAllSegment EOF )
            // InternalIdioms.g:790:1: ruleWrapBeginAllSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWrapBeginAllSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginAllSegmentRule());
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
    // $ANTLR end "entryRuleWrapBeginAllSegment"


    // $ANTLR start "ruleWrapBeginAllSegment"
    // InternalIdioms.g:797:1: ruleWrapBeginAllSegment : ( ( rule__WrapBeginAllSegment__Group__0 ) ) ;
    public final void ruleWrapBeginAllSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:801:2: ( ( ( rule__WrapBeginAllSegment__Group__0 ) ) )
            // InternalIdioms.g:802:2: ( ( rule__WrapBeginAllSegment__Group__0 ) )
            {
            // InternalIdioms.g:802:2: ( ( rule__WrapBeginAllSegment__Group__0 ) )
            // InternalIdioms.g:803:3: ( rule__WrapBeginAllSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getGroup());
            }
            // InternalIdioms.g:804:3: ( rule__WrapBeginAllSegment__Group__0 )
            // InternalIdioms.g:804:4: rule__WrapBeginAllSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginAllSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginAllSegmentAccess().getGroup());
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
    // $ANTLR end "ruleWrapBeginAllSegment"


    // $ANTLR start "entryRuleWrapBeginSomeSegment"
    // InternalIdioms.g:813:1: entryRuleWrapBeginSomeSegment : ruleWrapBeginSomeSegment EOF ;
    public final void entryRuleWrapBeginSomeSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:814:1: ( ruleWrapBeginSomeSegment EOF )
            // InternalIdioms.g:815:1: ruleWrapBeginSomeSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWrapBeginSomeSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginSomeSegmentRule());
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
    // $ANTLR end "entryRuleWrapBeginSomeSegment"


    // $ANTLR start "ruleWrapBeginSomeSegment"
    // InternalIdioms.g:822:1: ruleWrapBeginSomeSegment : ( ( rule__WrapBeginSomeSegment__Group__0 ) ) ;
    public final void ruleWrapBeginSomeSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:826:2: ( ( ( rule__WrapBeginSomeSegment__Group__0 ) ) )
            // InternalIdioms.g:827:2: ( ( rule__WrapBeginSomeSegment__Group__0 ) )
            {
            // InternalIdioms.g:827:2: ( ( rule__WrapBeginSomeSegment__Group__0 ) )
            // InternalIdioms.g:828:3: ( rule__WrapBeginSomeSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup());
            }
            // InternalIdioms.g:829:3: ( rule__WrapBeginSomeSegment__Group__0 )
            // InternalIdioms.g:829:4: rule__WrapBeginSomeSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginSomeSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup());
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
    // $ANTLR end "ruleWrapBeginSomeSegment"


    // $ANTLR start "entryRuleWrapEndSegment"
    // InternalIdioms.g:838:1: entryRuleWrapEndSegment : ruleWrapEndSegment EOF ;
    public final void entryRuleWrapEndSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:839:1: ( ruleWrapEndSegment EOF )
            // InternalIdioms.g:840:1: ruleWrapEndSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWrapEndSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapEndSegmentRule());
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
    // $ANTLR end "entryRuleWrapEndSegment"


    // $ANTLR start "ruleWrapEndSegment"
    // InternalIdioms.g:847:1: ruleWrapEndSegment : ( ( rule__WrapEndSegment__Group__0 ) ) ;
    public final void ruleWrapEndSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:851:2: ( ( ( rule__WrapEndSegment__Group__0 ) ) )
            // InternalIdioms.g:852:2: ( ( rule__WrapEndSegment__Group__0 ) )
            {
            // InternalIdioms.g:852:2: ( ( rule__WrapEndSegment__Group__0 ) )
            // InternalIdioms.g:853:3: ( rule__WrapEndSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getGroup());
            }
            // InternalIdioms.g:854:3: ( rule__WrapEndSegment__Group__0 )
            // InternalIdioms.g:854:4: rule__WrapEndSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapEndSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapEndSegmentAccess().getGroup());
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
    // $ANTLR end "ruleWrapEndSegment"


    // $ANTLR start "entryRuleWrapHereSegment"
    // InternalIdioms.g:863:1: entryRuleWrapHereSegment : ruleWrapHereSegment EOF ;
    public final void entryRuleWrapHereSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:864:1: ( ruleWrapHereSegment EOF )
            // InternalIdioms.g:865:1: ruleWrapHereSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWrapHereSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapHereSegmentRule());
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
    // $ANTLR end "entryRuleWrapHereSegment"


    // $ANTLR start "ruleWrapHereSegment"
    // InternalIdioms.g:872:1: ruleWrapHereSegment : ( ( rule__WrapHereSegment__Group__0 ) ) ;
    public final void ruleWrapHereSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:876:2: ( ( ( rule__WrapHereSegment__Group__0 ) ) )
            // InternalIdioms.g:877:2: ( ( rule__WrapHereSegment__Group__0 ) )
            {
            // InternalIdioms.g:877:2: ( ( rule__WrapHereSegment__Group__0 ) )
            // InternalIdioms.g:878:3: ( rule__WrapHereSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getGroup());
            }
            // InternalIdioms.g:879:3: ( rule__WrapHereSegment__Group__0 )
            // InternalIdioms.g:879:4: rule__WrapHereSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapHereSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapHereSegmentAccess().getGroup());
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
    // $ANTLR end "ruleWrapHereSegment"


    // $ANTLR start "entryRuleReferredSegment"
    // InternalIdioms.g:888:1: entryRuleReferredSegment : ruleReferredSegment EOF ;
    public final void entryRuleReferredSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:889:1: ( ruleReferredSegment EOF )
            // InternalIdioms.g:890:1: ruleReferredSegment EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleReferredSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentRule());
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
    // $ANTLR end "entryRuleReferredSegment"


    // $ANTLR start "ruleReferredSegment"
    // InternalIdioms.g:897:1: ruleReferredSegment : ( ( rule__ReferredSegment__Group__0 ) ) ;
    public final void ruleReferredSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:901:2: ( ( ( rule__ReferredSegment__Group__0 ) ) )
            // InternalIdioms.g:902:2: ( ( rule__ReferredSegment__Group__0 ) )
            {
            // InternalIdioms.g:902:2: ( ( rule__ReferredSegment__Group__0 ) )
            // InternalIdioms.g:903:3: ( rule__ReferredSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getGroup());
            }
            // InternalIdioms.g:904:3: ( rule__ReferredSegment__Group__0 )
            // InternalIdioms.g:904:4: rule__ReferredSegment__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getGroup());
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
    // $ANTLR end "ruleReferredSegment"


    // $ANTLR start "entryRuleIdiom"
    // InternalIdioms.g:913:1: entryRuleIdiom : ruleIdiom EOF ;
    public final void entryRuleIdiom() throws RecognitionException {
        try {
            // InternalIdioms.g:914:1: ( ruleIdiom EOF )
            // InternalIdioms.g:915:1: ruleIdiom EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomRule());
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
    // $ANTLR end "entryRuleIdiom"


    // $ANTLR start "ruleIdiom"
    // InternalIdioms.g:922:1: ruleIdiom : ( ( rule__Idiom__Group__0 ) ) ;
    public final void ruleIdiom() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:926:2: ( ( ( rule__Idiom__Group__0 ) ) )
            // InternalIdioms.g:927:2: ( ( rule__Idiom__Group__0 ) )
            {
            // InternalIdioms.g:927:2: ( ( rule__Idiom__Group__0 ) )
            // InternalIdioms.g:928:3: ( rule__Idiom__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup());
            }
            // InternalIdioms.g:929:3: ( rule__Idiom__Group__0 )
            // InternalIdioms.g:929:4: rule__Idiom__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getGroup());
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
    // $ANTLR end "ruleIdiom"


    // $ANTLR start "entryRuleSubIdiom"
    // InternalIdioms.g:938:1: entryRuleSubIdiom : ruleSubIdiom EOF ;
    public final void entryRuleSubIdiom() throws RecognitionException {
        try {
            // InternalIdioms.g:939:1: ( ruleSubIdiom EOF )
            // InternalIdioms.g:940:1: ruleSubIdiom EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleSubIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomRule());
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
    // $ANTLR end "entryRuleSubIdiom"


    // $ANTLR start "ruleSubIdiom"
    // InternalIdioms.g:947:1: ruleSubIdiom : ( ( rule__SubIdiom__Group__0 ) ) ;
    public final void ruleSubIdiom() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:951:2: ( ( ( rule__SubIdiom__Group__0 ) ) )
            // InternalIdioms.g:952:2: ( ( rule__SubIdiom__Group__0 ) )
            {
            // InternalIdioms.g:952:2: ( ( rule__SubIdiom__Group__0 ) )
            // InternalIdioms.g:953:3: ( rule__SubIdiom__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getGroup());
            }
            // InternalIdioms.g:954:3: ( rule__SubIdiom__Group__0 )
            // InternalIdioms.g:954:4: rule__SubIdiom__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getGroup());
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
    // $ANTLR end "ruleSubIdiom"


    // $ANTLR start "rule__IdiomsModel__Alternatives_3"
    // InternalIdioms.g:962:1: rule__IdiomsModel__Alternatives_3 : ( ( ( rule__IdiomsModel__OwnedWithsAssignment_3_0 ) ) | ( ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 ) ) | ( ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 ) ) );
    public final void rule__IdiomsModel__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:966:1: ( ( ( rule__IdiomsModel__OwnedWithsAssignment_3_0 ) ) | ( ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 ) ) | ( ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 ) ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt1=1;
                }
                break;
            case 14:
                {
                alt1=2;
                }
                break;
            case 17:
                {
                alt1=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalIdioms.g:967:2: ( ( rule__IdiomsModel__OwnedWithsAssignment_3_0 ) )
                    {
                    // InternalIdioms.g:967:2: ( ( rule__IdiomsModel__OwnedWithsAssignment_3_0 ) )
                    // InternalIdioms.g:968:3: ( rule__IdiomsModel__OwnedWithsAssignment_3_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3_0());
                    }
                    // InternalIdioms.g:969:3: ( rule__IdiomsModel__OwnedWithsAssignment_3_0 )
                    // InternalIdioms.g:969:4: rule__IdiomsModel__OwnedWithsAssignment_3_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedWithsAssignment_3_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:973:2: ( ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 ) )
                    {
                    // InternalIdioms.g:973:2: ( ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 ) )
                    // InternalIdioms.g:974:3: ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedImportDeclarationsAssignment_3_1());
                    }
                    // InternalIdioms.g:975:3: ( rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 )
                    // InternalIdioms.g:975:4: rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedImportDeclarationsAssignment_3_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:979:2: ( ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 ) )
                    {
                    // InternalIdioms.g:979:2: ( ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 ) )
                    // InternalIdioms.g:980:3: ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedGrammarDeclarationsAssignment_3_2());
                    }
                    // InternalIdioms.g:981:3: ( rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 )
                    // InternalIdioms.g:981:4: rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedGrammarDeclarationsAssignment_3_2());
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
    // $ANTLR end "rule__IdiomsModel__Alternatives_3"


    // $ANTLR start "rule__IdiomsModel__Alternatives_4"
    // InternalIdioms.g:989:1: rule__IdiomsModel__Alternatives_4 : ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) ) );
    public final void rule__IdiomsModel__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:993:1: ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt2=1;
                }
                break;
            case 27:
                {
                alt2=2;
                }
                break;
            case 45:
            case 53:
                {
                alt2=3;
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
                    // InternalIdioms.g:994:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) )
                    {
                    // InternalIdioms.g:994:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) )
                    // InternalIdioms.g:995:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_4_0());
                    }
                    // InternalIdioms.g:996:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 )
                    // InternalIdioms.g:996:4: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_4_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1000:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) )
                    {
                    // InternalIdioms.g:1000:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) )
                    // InternalIdioms.g:1001:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_4_1());
                    }
                    // InternalIdioms.g:1002:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 )
                    // InternalIdioms.g:1002:4: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_4_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:1006:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) )
                    {
                    // InternalIdioms.g:1006:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) )
                    // InternalIdioms.g:1007:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_4_2());
                    }
                    // InternalIdioms.g:1008:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 )
                    // InternalIdioms.g:1008:4: rule__IdiomsModel__OwnedIdiomsAssignment_4_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedIdiomsAssignment_4_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_4_2());
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
    // $ANTLR end "rule__IdiomsModel__Alternatives_4"


    // $ANTLR start "rule__Locator__Alternatives"
    // InternalIdioms.g:1016:1: rule__Locator__Alternatives : ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleAssignmentLocator ) | ( ruleFinalLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) | ( ruleReturnsLocator ) | ( ruleRuleLocator ) );
    public final void rule__Locator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1020:1: ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleAssignmentLocator ) | ( ruleFinalLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) | ( ruleReturnsLocator ) | ( ruleRuleLocator ) )
            int alt3=8;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt3=1;
                }
                break;
            case 21:
                {
                alt3=2;
                }
                break;
            case 22:
                {
                alt3=3;
                }
                break;
            case 24:
                {
                alt3=4;
                }
                break;
            case RULE_STRING:
                {
                alt3=5;
                }
                break;
            case RULE_ID:
                {
                alt3=6;
                }
                break;
            case 25:
                {
                alt3=7;
                }
                break;
            case 26:
                {
                alt3=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalIdioms.g:1021:2: ( ruleAnyAssignmentLocator )
                    {
                    // InternalIdioms.g:1021:2: ( ruleAnyAssignmentLocator )
                    // InternalIdioms.g:1022:3: ruleAnyAssignmentLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleAnyAssignmentLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1027:2: ( ruleAnyElementLocator )
                    {
                    // InternalIdioms.g:1027:2: ( ruleAnyElementLocator )
                    // InternalIdioms.g:1028:3: ruleAnyElementLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleAnyElementLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:1033:2: ( ruleAssignmentLocator )
                    {
                    // InternalIdioms.g:1033:2: ( ruleAssignmentLocator )
                    // InternalIdioms.g:1034:3: ruleAssignmentLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleAssignmentLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalIdioms.g:1039:2: ( ruleFinalLocator )
                    {
                    // InternalIdioms.g:1039:2: ( ruleFinalLocator )
                    // InternalIdioms.g:1040:3: ruleFinalLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleFinalLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3());
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalIdioms.g:1045:2: ( ruleKeywordLocator )
                    {
                    // InternalIdioms.g:1045:2: ( ruleKeywordLocator )
                    // InternalIdioms.g:1046:3: ruleKeywordLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleKeywordLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4());
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalIdioms.g:1051:2: ( ruleReferredLocator )
                    {
                    // InternalIdioms.g:1051:2: ( ruleReferredLocator )
                    // InternalIdioms.g:1052:3: ruleReferredLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleReferredLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5());
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalIdioms.g:1057:2: ( ruleReturnsLocator )
                    {
                    // InternalIdioms.g:1057:2: ( ruleReturnsLocator )
                    // InternalIdioms.g:1058:3: ruleReturnsLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleReturnsLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6());
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalIdioms.g:1063:2: ( ruleRuleLocator )
                    {
                    // InternalIdioms.g:1063:2: ( ruleRuleLocator )
                    // InternalIdioms.g:1064:3: ruleRuleLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getRuleLocatorParserRuleCall_7());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleRuleLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getRuleLocatorParserRuleCall_7());
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
    // $ANTLR end "rule__Locator__Alternatives"


    // $ANTLR start "rule__Segment__Alternatives"
    // InternalIdioms.g:1073:1: rule__Segment__Alternatives : ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) );
    public final void rule__Segment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1077:1: ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) )
            int alt4=17;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt4=1;
                }
                break;
            case 29:
                {
                alt4=2;
                }
                break;
            case 30:
                {
                alt4=3;
                }
                break;
            case 31:
                {
                alt4=4;
                }
                break;
            case 32:
                {
                alt4=5;
                }
                break;
            case 33:
                {
                alt4=6;
                }
                break;
            case 34:
                {
                alt4=7;
                }
                break;
            case 35:
                {
                alt4=8;
                }
                break;
            case 36:
                {
                alt4=9;
                }
                break;
            case 37:
                {
                alt4=10;
                }
                break;
            case 38:
                {
                alt4=11;
                }
                break;
            case 39:
                {
                alt4=12;
                }
                break;
            case 40:
                {
                alt4=13;
                }
                break;
            case 41:
                {
                alt4=14;
                }
                break;
            case 42:
                {
                alt4=15;
                }
                break;
            case 43:
                {
                alt4=16;
                }
                break;
            case 44:
                {
                alt4=17;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalIdioms.g:1078:2: ( ruleCustomSegment )
                    {
                    // InternalIdioms.g:1078:2: ( ruleCustomSegment )
                    // InternalIdioms.g:1079:3: ruleCustomSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleCustomSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1084:2: ( ruleHalfNewLineSegment )
                    {
                    // InternalIdioms.g:1084:2: ( ruleHalfNewLineSegment )
                    // InternalIdioms.g:1085:3: ruleHalfNewLineSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleHalfNewLineSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:1090:2: ( ruleNewLineSegment )
                    {
                    // InternalIdioms.g:1090:2: ( ruleNewLineSegment )
                    // InternalIdioms.g:1091:3: ruleNewLineSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleNewLineSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalIdioms.g:1096:2: ( ruleNoSpaceSegment )
                    {
                    // InternalIdioms.g:1096:2: ( ruleNoSpaceSegment )
                    // InternalIdioms.g:1097:3: ruleNoSpaceSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleNoSpaceSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3());
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalIdioms.g:1102:2: ( rulePopSegment )
                    {
                    // InternalIdioms.g:1102:2: ( rulePopSegment )
                    // InternalIdioms.g:1103:3: rulePopSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    rulePopSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4());
                    }

                    }


                    }
                    break;
                case 6 :
                    // InternalIdioms.g:1108:2: ( rulePostCommentSegment )
                    {
                    // InternalIdioms.g:1108:2: ( rulePostCommentSegment )
                    // InternalIdioms.g:1109:3: rulePostCommentSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    rulePostCommentSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5());
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalIdioms.g:1114:2: ( rulePreCommentSegment )
                    {
                    // InternalIdioms.g:1114:2: ( rulePreCommentSegment )
                    // InternalIdioms.g:1115:3: rulePreCommentSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    rulePreCommentSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6());
                    }

                    }


                    }
                    break;
                case 8 :
                    // InternalIdioms.g:1120:2: ( rulePushSegment )
                    {
                    // InternalIdioms.g:1120:2: ( rulePushSegment )
                    // InternalIdioms.g:1121:3: rulePushSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    rulePushSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7());
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalIdioms.g:1126:2: ( ruleSoftNewLineSegment )
                    {
                    // InternalIdioms.g:1126:2: ( ruleSoftNewLineSegment )
                    // InternalIdioms.g:1127:3: ruleSoftNewLineSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleSoftNewLineSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8());
                    }

                    }


                    }
                    break;
                case 10 :
                    // InternalIdioms.g:1132:2: ( ruleSoftSpaceSegment )
                    {
                    // InternalIdioms.g:1132:2: ( ruleSoftSpaceSegment )
                    // InternalIdioms.g:1133:3: ruleSoftSpaceSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleSoftSpaceSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9());
                    }

                    }


                    }
                    break;
                case 11 :
                    // InternalIdioms.g:1138:2: ( ruleStringSegment )
                    {
                    // InternalIdioms.g:1138:2: ( ruleStringSegment )
                    // InternalIdioms.g:1139:3: ruleStringSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleStringSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10());
                    }

                    }


                    }
                    break;
                case 12 :
                    // InternalIdioms.g:1144:2: ( ruleValueSegment )
                    {
                    // InternalIdioms.g:1144:2: ( ruleValueSegment )
                    // InternalIdioms.g:1145:3: ruleValueSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleValueSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11());
                    }

                    }


                    }
                    break;
                case 13 :
                    // InternalIdioms.g:1150:2: ( ruleWrapAnchorSegment )
                    {
                    // InternalIdioms.g:1150:2: ( ruleWrapAnchorSegment )
                    // InternalIdioms.g:1151:3: ruleWrapAnchorSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWrapAnchorSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12());
                    }

                    }


                    }
                    break;
                case 14 :
                    // InternalIdioms.g:1156:2: ( ruleWrapBeginAllSegment )
                    {
                    // InternalIdioms.g:1156:2: ( ruleWrapBeginAllSegment )
                    // InternalIdioms.g:1157:3: ruleWrapBeginAllSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWrapBeginAllSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13());
                    }

                    }


                    }
                    break;
                case 15 :
                    // InternalIdioms.g:1162:2: ( ruleWrapBeginSomeSegment )
                    {
                    // InternalIdioms.g:1162:2: ( ruleWrapBeginSomeSegment )
                    // InternalIdioms.g:1163:3: ruleWrapBeginSomeSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWrapBeginSomeSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14());
                    }

                    }


                    }
                    break;
                case 16 :
                    // InternalIdioms.g:1168:2: ( ruleWrapEndSegment )
                    {
                    // InternalIdioms.g:1168:2: ( ruleWrapEndSegment )
                    // InternalIdioms.g:1169:3: ruleWrapEndSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWrapEndSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15());
                    }

                    }


                    }
                    break;
                case 17 :
                    // InternalIdioms.g:1174:2: ( ruleWrapHereSegment )
                    {
                    // InternalIdioms.g:1174:2: ( ruleWrapHereSegment )
                    // InternalIdioms.g:1175:3: ruleWrapHereSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWrapHereSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16());
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
    // $ANTLR end "rule__Segment__Alternatives"


    // $ANTLR start "rule__Idiom__Alternatives_5"
    // InternalIdioms.g:1184:1: rule__Idiom__Alternatives_5 : ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) );
    public final void rule__Idiom__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1188:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==50) ) {
                alt5=1;
            }
            else if ( (LA5_0==48) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalIdioms.g:1189:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    {
                    // InternalIdioms.g:1189:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    // InternalIdioms.g:1190:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0());
                    }
                    // InternalIdioms.g:1191:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    // InternalIdioms.g:1191:4: rule__Idiom__OwnedSubIdiomsAssignment_5_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__OwnedSubIdiomsAssignment_5_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1195:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    {
                    // InternalIdioms.g:1195:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    // InternalIdioms.g:1196:3: ( rule__Idiom__Group_5_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getGroup_5_1());
                    }
                    // InternalIdioms.g:1197:3: ( rule__Idiom__Group_5_1__0 )
                    // InternalIdioms.g:1197:4: rule__Idiom__Group_5_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__Group_5_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomAccess().getGroup_5_1());
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
    // $ANTLR end "rule__Idiom__Alternatives_5"


    // $ANTLR start "rule__SubIdiom__Alternatives_1"
    // InternalIdioms.g:1205:1: rule__SubIdiom__Alternatives_1 : ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) );
    public final void rule__SubIdiom__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1209:1: ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==54) ) {
                alt6=1;
            }
            else if ( (LA6_0==11) ) {
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
                    // InternalIdioms.g:1210:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    {
                    // InternalIdioms.g:1210:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    // InternalIdioms.g:1211:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0());
                    }
                    // InternalIdioms.g:1212:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    // InternalIdioms.g:1212:4: rule__SubIdiom__AllAssignment_1_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__SubIdiom__AllAssignment_1_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1216:2: ( 'each' )
                    {
                    // InternalIdioms.g:1216:2: ( 'each' )
                    // InternalIdioms.g:1217:3: 'each'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getEachKeyword_1_1());
                    }
                    match(input,11,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSubIdiomAccess().getEachKeyword_1_1());
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
    // $ANTLR end "rule__SubIdiom__Alternatives_1"


    // $ANTLR start "rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0"
    // InternalIdioms.g:1226:1: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 : ( ( ruleSegment ) | ( ruleReferredSegment ) );
    public final void rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1230:1: ( ( ruleSegment ) | ( ruleReferredSegment ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=28 && LA7_0<=44)) ) {
                alt7=1;
            }
            else if ( (LA7_0==RULE_ID) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalIdioms.g:1231:2: ( ruleSegment )
                    {
                    // InternalIdioms.g:1231:2: ( ruleSegment )
                    // InternalIdioms.g:1232:3: ruleSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1237:2: ( ruleReferredSegment )
                    {
                    // InternalIdioms.g:1237:2: ( ruleReferredSegment )
                    // InternalIdioms.g:1238:3: ruleReferredSegment
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleReferredSegment();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1());
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
    // $ANTLR end "rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0"


    // $ANTLR start "rule__IdiomsModel__Group__0"
    // InternalIdioms.g:1247:1: rule__IdiomsModel__Group__0 : rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 ;
    public final void rule__IdiomsModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1251:1: ( rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 )
            // InternalIdioms.g:1252:2: rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__IdiomsModel__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__1();

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
    // $ANTLR end "rule__IdiomsModel__Group__0"


    // $ANTLR start "rule__IdiomsModel__Group__0__Impl"
    // InternalIdioms.g:1259:1: rule__IdiomsModel__Group__0__Impl : ( 'model' ) ;
    public final void rule__IdiomsModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1263:1: ( ( 'model' ) )
            // InternalIdioms.g:1264:1: ( 'model' )
            {
            // InternalIdioms.g:1264:1: ( 'model' )
            // InternalIdioms.g:1265:2: 'model'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getModelKeyword_0());
            }
            match(input,12,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getModelKeyword_0());
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
    // $ANTLR end "rule__IdiomsModel__Group__0__Impl"


    // $ANTLR start "rule__IdiomsModel__Group__1"
    // InternalIdioms.g:1274:1: rule__IdiomsModel__Group__1 : rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 ;
    public final void rule__IdiomsModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1278:1: ( rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 )
            // InternalIdioms.g:1279:2: rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__IdiomsModel__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__2();

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
    // $ANTLR end "rule__IdiomsModel__Group__1"


    // $ANTLR start "rule__IdiomsModel__Group__1__Impl"
    // InternalIdioms.g:1286:1: rule__IdiomsModel__Group__1__Impl : ( ( rule__IdiomsModel__NamesAssignment_1 ) ) ;
    public final void rule__IdiomsModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1290:1: ( ( ( rule__IdiomsModel__NamesAssignment_1 ) ) )
            // InternalIdioms.g:1291:1: ( ( rule__IdiomsModel__NamesAssignment_1 ) )
            {
            // InternalIdioms.g:1291:1: ( ( rule__IdiomsModel__NamesAssignment_1 ) )
            // InternalIdioms.g:1292:2: ( rule__IdiomsModel__NamesAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1());
            }
            // InternalIdioms.g:1293:2: ( rule__IdiomsModel__NamesAssignment_1 )
            // InternalIdioms.g:1293:3: rule__IdiomsModel__NamesAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__NamesAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1());
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
    // $ANTLR end "rule__IdiomsModel__Group__1__Impl"


    // $ANTLR start "rule__IdiomsModel__Group__2"
    // InternalIdioms.g:1301:1: rule__IdiomsModel__Group__2 : rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 ;
    public final void rule__IdiomsModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1305:1: ( rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 )
            // InternalIdioms.g:1306:2: rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__IdiomsModel__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__3();

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
    // $ANTLR end "rule__IdiomsModel__Group__2"


    // $ANTLR start "rule__IdiomsModel__Group__2__Impl"
    // InternalIdioms.g:1313:1: rule__IdiomsModel__Group__2__Impl : ( ( rule__IdiomsModel__Group_2__0 )* ) ;
    public final void rule__IdiomsModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1317:1: ( ( ( rule__IdiomsModel__Group_2__0 )* ) )
            // InternalIdioms.g:1318:1: ( ( rule__IdiomsModel__Group_2__0 )* )
            {
            // InternalIdioms.g:1318:1: ( ( rule__IdiomsModel__Group_2__0 )* )
            // InternalIdioms.g:1319:2: ( rule__IdiomsModel__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getGroup_2());
            }
            // InternalIdioms.g:1320:2: ( rule__IdiomsModel__Group_2__0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==13) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalIdioms.g:1320:3: rule__IdiomsModel__Group_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__IdiomsModel__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getGroup_2());
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
    // $ANTLR end "rule__IdiomsModel__Group__2__Impl"


    // $ANTLR start "rule__IdiomsModel__Group__3"
    // InternalIdioms.g:1328:1: rule__IdiomsModel__Group__3 : rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 ;
    public final void rule__IdiomsModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1332:1: ( rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 )
            // InternalIdioms.g:1333:2: rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__IdiomsModel__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__4();

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
    // $ANTLR end "rule__IdiomsModel__Group__3"


    // $ANTLR start "rule__IdiomsModel__Group__3__Impl"
    // InternalIdioms.g:1340:1: rule__IdiomsModel__Group__3__Impl : ( ( rule__IdiomsModel__Alternatives_3 )* ) ;
    public final void rule__IdiomsModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1344:1: ( ( ( rule__IdiomsModel__Alternatives_3 )* ) )
            // InternalIdioms.g:1345:1: ( ( rule__IdiomsModel__Alternatives_3 )* )
            {
            // InternalIdioms.g:1345:1: ( ( rule__IdiomsModel__Alternatives_3 )* )
            // InternalIdioms.g:1346:2: ( rule__IdiomsModel__Alternatives_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getAlternatives_3());
            }
            // InternalIdioms.g:1347:2: ( rule__IdiomsModel__Alternatives_3 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==14||(LA9_0>=17 && LA9_0<=18)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalIdioms.g:1347:3: rule__IdiomsModel__Alternatives_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    rule__IdiomsModel__Alternatives_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getAlternatives_3());
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
    // $ANTLR end "rule__IdiomsModel__Group__3__Impl"


    // $ANTLR start "rule__IdiomsModel__Group__4"
    // InternalIdioms.g:1355:1: rule__IdiomsModel__Group__4 : rule__IdiomsModel__Group__4__Impl ;
    public final void rule__IdiomsModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1359:1: ( rule__IdiomsModel__Group__4__Impl )
            // InternalIdioms.g:1360:2: rule__IdiomsModel__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__4__Impl();

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
    // $ANTLR end "rule__IdiomsModel__Group__4"


    // $ANTLR start "rule__IdiomsModel__Group__4__Impl"
    // InternalIdioms.g:1366:1: rule__IdiomsModel__Group__4__Impl : ( ( rule__IdiomsModel__Alternatives_4 )* ) ;
    public final void rule__IdiomsModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1370:1: ( ( ( rule__IdiomsModel__Alternatives_4 )* ) )
            // InternalIdioms.g:1371:1: ( ( rule__IdiomsModel__Alternatives_4 )* )
            {
            // InternalIdioms.g:1371:1: ( ( rule__IdiomsModel__Alternatives_4 )* )
            // InternalIdioms.g:1372:2: ( rule__IdiomsModel__Alternatives_4 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getAlternatives_4());
            }
            // InternalIdioms.g:1373:2: ( rule__IdiomsModel__Alternatives_4 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==19||LA10_0==27||LA10_0==45||LA10_0==53) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalIdioms.g:1373:3: rule__IdiomsModel__Alternatives_4
            	    {
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    rule__IdiomsModel__Alternatives_4();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getAlternatives_4());
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
    // $ANTLR end "rule__IdiomsModel__Group__4__Impl"


    // $ANTLR start "rule__IdiomsModel__Group_2__0"
    // InternalIdioms.g:1382:1: rule__IdiomsModel__Group_2__0 : rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1 ;
    public final void rule__IdiomsModel__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1386:1: ( rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1 )
            // InternalIdioms.g:1387:2: rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__IdiomsModel__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group_2__1();

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
    // $ANTLR end "rule__IdiomsModel__Group_2__0"


    // $ANTLR start "rule__IdiomsModel__Group_2__0__Impl"
    // InternalIdioms.g:1394:1: rule__IdiomsModel__Group_2__0__Impl : ( '.' ) ;
    public final void rule__IdiomsModel__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1398:1: ( ( '.' ) )
            // InternalIdioms.g:1399:1: ( '.' )
            {
            // InternalIdioms.g:1399:1: ( '.' )
            // InternalIdioms.g:1400:2: '.'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0());
            }
            match(input,13,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0());
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
    // $ANTLR end "rule__IdiomsModel__Group_2__0__Impl"


    // $ANTLR start "rule__IdiomsModel__Group_2__1"
    // InternalIdioms.g:1409:1: rule__IdiomsModel__Group_2__1 : rule__IdiomsModel__Group_2__1__Impl ;
    public final void rule__IdiomsModel__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1413:1: ( rule__IdiomsModel__Group_2__1__Impl )
            // InternalIdioms.g:1414:2: rule__IdiomsModel__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group_2__1__Impl();

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
    // $ANTLR end "rule__IdiomsModel__Group_2__1"


    // $ANTLR start "rule__IdiomsModel__Group_2__1__Impl"
    // InternalIdioms.g:1420:1: rule__IdiomsModel__Group_2__1__Impl : ( ( rule__IdiomsModel__NamesAssignment_2_1 ) ) ;
    public final void rule__IdiomsModel__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1424:1: ( ( ( rule__IdiomsModel__NamesAssignment_2_1 ) ) )
            // InternalIdioms.g:1425:1: ( ( rule__IdiomsModel__NamesAssignment_2_1 ) )
            {
            // InternalIdioms.g:1425:1: ( ( rule__IdiomsModel__NamesAssignment_2_1 ) )
            // InternalIdioms.g:1426:2: ( rule__IdiomsModel__NamesAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1());
            }
            // InternalIdioms.g:1427:2: ( rule__IdiomsModel__NamesAssignment_2_1 )
            // InternalIdioms.g:1427:3: rule__IdiomsModel__NamesAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__NamesAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1());
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
    // $ANTLR end "rule__IdiomsModel__Group_2__1__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group__0"
    // InternalIdioms.g:1436:1: rule__EPackageDeclaration__Group__0 : rule__EPackageDeclaration__Group__0__Impl rule__EPackageDeclaration__Group__1 ;
    public final void rule__EPackageDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1440:1: ( rule__EPackageDeclaration__Group__0__Impl rule__EPackageDeclaration__Group__1 )
            // InternalIdioms.g:1441:2: rule__EPackageDeclaration__Group__0__Impl rule__EPackageDeclaration__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__EPackageDeclaration__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group__1();

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
    // $ANTLR end "rule__EPackageDeclaration__Group__0"


    // $ANTLR start "rule__EPackageDeclaration__Group__0__Impl"
    // InternalIdioms.g:1448:1: rule__EPackageDeclaration__Group__0__Impl : ( 'import' ) ;
    public final void rule__EPackageDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1452:1: ( ( 'import' ) )
            // InternalIdioms.g:1453:1: ( 'import' )
            {
            // InternalIdioms.g:1453:1: ( 'import' )
            // InternalIdioms.g:1454:2: 'import'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getImportKeyword_0());
            }
            match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getImportKeyword_0());
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
    // $ANTLR end "rule__EPackageDeclaration__Group__0__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group__1"
    // InternalIdioms.g:1463:1: rule__EPackageDeclaration__Group__1 : rule__EPackageDeclaration__Group__1__Impl rule__EPackageDeclaration__Group__2 ;
    public final void rule__EPackageDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1467:1: ( rule__EPackageDeclaration__Group__1__Impl rule__EPackageDeclaration__Group__2 )
            // InternalIdioms.g:1468:2: rule__EPackageDeclaration__Group__1__Impl rule__EPackageDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__EPackageDeclaration__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group__2();

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
    // $ANTLR end "rule__EPackageDeclaration__Group__1"


    // $ANTLR start "rule__EPackageDeclaration__Group__1__Impl"
    // InternalIdioms.g:1475:1: rule__EPackageDeclaration__Group__1__Impl : ( ( rule__EPackageDeclaration__EPackageAssignment_1 ) ) ;
    public final void rule__EPackageDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1479:1: ( ( ( rule__EPackageDeclaration__EPackageAssignment_1 ) ) )
            // InternalIdioms.g:1480:1: ( ( rule__EPackageDeclaration__EPackageAssignment_1 ) )
            {
            // InternalIdioms.g:1480:1: ( ( rule__EPackageDeclaration__EPackageAssignment_1 ) )
            // InternalIdioms.g:1481:2: ( rule__EPackageDeclaration__EPackageAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getEPackageAssignment_1());
            }
            // InternalIdioms.g:1482:2: ( rule__EPackageDeclaration__EPackageAssignment_1 )
            // InternalIdioms.g:1482:3: rule__EPackageDeclaration__EPackageAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__EPackageAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getEPackageAssignment_1());
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
    // $ANTLR end "rule__EPackageDeclaration__Group__1__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group__2"
    // InternalIdioms.g:1490:1: rule__EPackageDeclaration__Group__2 : rule__EPackageDeclaration__Group__2__Impl rule__EPackageDeclaration__Group__3 ;
    public final void rule__EPackageDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1494:1: ( rule__EPackageDeclaration__Group__2__Impl rule__EPackageDeclaration__Group__3 )
            // InternalIdioms.g:1495:2: rule__EPackageDeclaration__Group__2__Impl rule__EPackageDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__EPackageDeclaration__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group__3();

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
    // $ANTLR end "rule__EPackageDeclaration__Group__2"


    // $ANTLR start "rule__EPackageDeclaration__Group__2__Impl"
    // InternalIdioms.g:1502:1: rule__EPackageDeclaration__Group__2__Impl : ( ( rule__EPackageDeclaration__Group_2__0 )? ) ;
    public final void rule__EPackageDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1506:1: ( ( ( rule__EPackageDeclaration__Group_2__0 )? ) )
            // InternalIdioms.g:1507:1: ( ( rule__EPackageDeclaration__Group_2__0 )? )
            {
            // InternalIdioms.g:1507:1: ( ( rule__EPackageDeclaration__Group_2__0 )? )
            // InternalIdioms.g:1508:2: ( rule__EPackageDeclaration__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getGroup_2());
            }
            // InternalIdioms.g:1509:2: ( rule__EPackageDeclaration__Group_2__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==16) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalIdioms.g:1509:3: rule__EPackageDeclaration__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__EPackageDeclaration__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getGroup_2());
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
    // $ANTLR end "rule__EPackageDeclaration__Group__2__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group__3"
    // InternalIdioms.g:1517:1: rule__EPackageDeclaration__Group__3 : rule__EPackageDeclaration__Group__3__Impl ;
    public final void rule__EPackageDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1521:1: ( rule__EPackageDeclaration__Group__3__Impl )
            // InternalIdioms.g:1522:2: rule__EPackageDeclaration__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group__3__Impl();

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
    // $ANTLR end "rule__EPackageDeclaration__Group__3"


    // $ANTLR start "rule__EPackageDeclaration__Group__3__Impl"
    // InternalIdioms.g:1528:1: rule__EPackageDeclaration__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__EPackageDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1532:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1533:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1533:1: ( ( ';' )? )
            // InternalIdioms.g:1534:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1535:2: ( ';' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==15) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalIdioms.g:1535:3: ';'
                    {
                    match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getSemicolonKeyword_3());
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
    // $ANTLR end "rule__EPackageDeclaration__Group__3__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group_2__0"
    // InternalIdioms.g:1544:1: rule__EPackageDeclaration__Group_2__0 : rule__EPackageDeclaration__Group_2__0__Impl rule__EPackageDeclaration__Group_2__1 ;
    public final void rule__EPackageDeclaration__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1548:1: ( rule__EPackageDeclaration__Group_2__0__Impl rule__EPackageDeclaration__Group_2__1 )
            // InternalIdioms.g:1549:2: rule__EPackageDeclaration__Group_2__0__Impl rule__EPackageDeclaration__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__EPackageDeclaration__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group_2__1();

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
    // $ANTLR end "rule__EPackageDeclaration__Group_2__0"


    // $ANTLR start "rule__EPackageDeclaration__Group_2__0__Impl"
    // InternalIdioms.g:1556:1: rule__EPackageDeclaration__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__EPackageDeclaration__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1560:1: ( ( 'as' ) )
            // InternalIdioms.g:1561:1: ( 'as' )
            {
            // InternalIdioms.g:1561:1: ( 'as' )
            // InternalIdioms.g:1562:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getAsKeyword_2_0());
            }
            match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getAsKeyword_2_0());
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
    // $ANTLR end "rule__EPackageDeclaration__Group_2__0__Impl"


    // $ANTLR start "rule__EPackageDeclaration__Group_2__1"
    // InternalIdioms.g:1571:1: rule__EPackageDeclaration__Group_2__1 : rule__EPackageDeclaration__Group_2__1__Impl ;
    public final void rule__EPackageDeclaration__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1575:1: ( rule__EPackageDeclaration__Group_2__1__Impl )
            // InternalIdioms.g:1576:2: rule__EPackageDeclaration__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__Group_2__1__Impl();

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
    // $ANTLR end "rule__EPackageDeclaration__Group_2__1"


    // $ANTLR start "rule__EPackageDeclaration__Group_2__1__Impl"
    // InternalIdioms.g:1582:1: rule__EPackageDeclaration__Group_2__1__Impl : ( ( rule__EPackageDeclaration__AsAssignment_2_1 ) ) ;
    public final void rule__EPackageDeclaration__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1586:1: ( ( ( rule__EPackageDeclaration__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1587:1: ( ( rule__EPackageDeclaration__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1587:1: ( ( rule__EPackageDeclaration__AsAssignment_2_1 ) )
            // InternalIdioms.g:1588:2: ( rule__EPackageDeclaration__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1589:2: ( rule__EPackageDeclaration__AsAssignment_2_1 )
            // InternalIdioms.g:1589:3: rule__EPackageDeclaration__AsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageDeclaration__AsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getAsAssignment_2_1());
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
    // $ANTLR end "rule__EPackageDeclaration__Group_2__1__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group__0"
    // InternalIdioms.g:1598:1: rule__GrammarDeclaration__Group__0 : rule__GrammarDeclaration__Group__0__Impl rule__GrammarDeclaration__Group__1 ;
    public final void rule__GrammarDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1602:1: ( rule__GrammarDeclaration__Group__0__Impl rule__GrammarDeclaration__Group__1 )
            // InternalIdioms.g:1603:2: rule__GrammarDeclaration__Group__0__Impl rule__GrammarDeclaration__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__GrammarDeclaration__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group__1();

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
    // $ANTLR end "rule__GrammarDeclaration__Group__0"


    // $ANTLR start "rule__GrammarDeclaration__Group__0__Impl"
    // InternalIdioms.g:1610:1: rule__GrammarDeclaration__Group__0__Impl : ( 'grammar' ) ;
    public final void rule__GrammarDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1614:1: ( ( 'grammar' ) )
            // InternalIdioms.g:1615:1: ( 'grammar' )
            {
            // InternalIdioms.g:1615:1: ( 'grammar' )
            // InternalIdioms.g:1616:2: 'grammar'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGrammarKeyword_0());
            }
            match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGrammarKeyword_0());
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
    // $ANTLR end "rule__GrammarDeclaration__Group__0__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group__1"
    // InternalIdioms.g:1625:1: rule__GrammarDeclaration__Group__1 : rule__GrammarDeclaration__Group__1__Impl rule__GrammarDeclaration__Group__2 ;
    public final void rule__GrammarDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1629:1: ( rule__GrammarDeclaration__Group__1__Impl rule__GrammarDeclaration__Group__2 )
            // InternalIdioms.g:1630:2: rule__GrammarDeclaration__Group__1__Impl rule__GrammarDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__GrammarDeclaration__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group__2();

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
    // $ANTLR end "rule__GrammarDeclaration__Group__1"


    // $ANTLR start "rule__GrammarDeclaration__Group__1__Impl"
    // InternalIdioms.g:1637:1: rule__GrammarDeclaration__Group__1__Impl : ( ( rule__GrammarDeclaration__GrammarAssignment_1 ) ) ;
    public final void rule__GrammarDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1641:1: ( ( ( rule__GrammarDeclaration__GrammarAssignment_1 ) ) )
            // InternalIdioms.g:1642:1: ( ( rule__GrammarDeclaration__GrammarAssignment_1 ) )
            {
            // InternalIdioms.g:1642:1: ( ( rule__GrammarDeclaration__GrammarAssignment_1 ) )
            // InternalIdioms.g:1643:2: ( rule__GrammarDeclaration__GrammarAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGrammarAssignment_1());
            }
            // InternalIdioms.g:1644:2: ( rule__GrammarDeclaration__GrammarAssignment_1 )
            // InternalIdioms.g:1644:3: rule__GrammarDeclaration__GrammarAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__GrammarAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGrammarAssignment_1());
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
    // $ANTLR end "rule__GrammarDeclaration__Group__1__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group__2"
    // InternalIdioms.g:1652:1: rule__GrammarDeclaration__Group__2 : rule__GrammarDeclaration__Group__2__Impl rule__GrammarDeclaration__Group__3 ;
    public final void rule__GrammarDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1656:1: ( rule__GrammarDeclaration__Group__2__Impl rule__GrammarDeclaration__Group__3 )
            // InternalIdioms.g:1657:2: rule__GrammarDeclaration__Group__2__Impl rule__GrammarDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__GrammarDeclaration__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group__3();

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
    // $ANTLR end "rule__GrammarDeclaration__Group__2"


    // $ANTLR start "rule__GrammarDeclaration__Group__2__Impl"
    // InternalIdioms.g:1664:1: rule__GrammarDeclaration__Group__2__Impl : ( ( rule__GrammarDeclaration__Group_2__0 )? ) ;
    public final void rule__GrammarDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1668:1: ( ( ( rule__GrammarDeclaration__Group_2__0 )? ) )
            // InternalIdioms.g:1669:1: ( ( rule__GrammarDeclaration__Group_2__0 )? )
            {
            // InternalIdioms.g:1669:1: ( ( rule__GrammarDeclaration__Group_2__0 )? )
            // InternalIdioms.g:1670:2: ( rule__GrammarDeclaration__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGroup_2());
            }
            // InternalIdioms.g:1671:2: ( rule__GrammarDeclaration__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==16) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalIdioms.g:1671:3: rule__GrammarDeclaration__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__GrammarDeclaration__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGroup_2());
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
    // $ANTLR end "rule__GrammarDeclaration__Group__2__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group__3"
    // InternalIdioms.g:1679:1: rule__GrammarDeclaration__Group__3 : rule__GrammarDeclaration__Group__3__Impl ;
    public final void rule__GrammarDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1683:1: ( rule__GrammarDeclaration__Group__3__Impl )
            // InternalIdioms.g:1684:2: rule__GrammarDeclaration__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group__3__Impl();

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
    // $ANTLR end "rule__GrammarDeclaration__Group__3"


    // $ANTLR start "rule__GrammarDeclaration__Group__3__Impl"
    // InternalIdioms.g:1690:1: rule__GrammarDeclaration__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__GrammarDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1694:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1695:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1695:1: ( ( ';' )? )
            // InternalIdioms.g:1696:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1697:2: ( ';' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalIdioms.g:1697:3: ';'
                    {
                    match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getSemicolonKeyword_3());
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
    // $ANTLR end "rule__GrammarDeclaration__Group__3__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group_2__0"
    // InternalIdioms.g:1706:1: rule__GrammarDeclaration__Group_2__0 : rule__GrammarDeclaration__Group_2__0__Impl rule__GrammarDeclaration__Group_2__1 ;
    public final void rule__GrammarDeclaration__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1710:1: ( rule__GrammarDeclaration__Group_2__0__Impl rule__GrammarDeclaration__Group_2__1 )
            // InternalIdioms.g:1711:2: rule__GrammarDeclaration__Group_2__0__Impl rule__GrammarDeclaration__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__GrammarDeclaration__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group_2__1();

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
    // $ANTLR end "rule__GrammarDeclaration__Group_2__0"


    // $ANTLR start "rule__GrammarDeclaration__Group_2__0__Impl"
    // InternalIdioms.g:1718:1: rule__GrammarDeclaration__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__GrammarDeclaration__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1722:1: ( ( 'as' ) )
            // InternalIdioms.g:1723:1: ( 'as' )
            {
            // InternalIdioms.g:1723:1: ( 'as' )
            // InternalIdioms.g:1724:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getAsKeyword_2_0());
            }
            match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getAsKeyword_2_0());
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
    // $ANTLR end "rule__GrammarDeclaration__Group_2__0__Impl"


    // $ANTLR start "rule__GrammarDeclaration__Group_2__1"
    // InternalIdioms.g:1733:1: rule__GrammarDeclaration__Group_2__1 : rule__GrammarDeclaration__Group_2__1__Impl ;
    public final void rule__GrammarDeclaration__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1737:1: ( rule__GrammarDeclaration__Group_2__1__Impl )
            // InternalIdioms.g:1738:2: rule__GrammarDeclaration__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__Group_2__1__Impl();

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
    // $ANTLR end "rule__GrammarDeclaration__Group_2__1"


    // $ANTLR start "rule__GrammarDeclaration__Group_2__1__Impl"
    // InternalIdioms.g:1744:1: rule__GrammarDeclaration__Group_2__1__Impl : ( ( rule__GrammarDeclaration__AsAssignment_2_1 ) ) ;
    public final void rule__GrammarDeclaration__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1748:1: ( ( ( rule__GrammarDeclaration__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1749:1: ( ( rule__GrammarDeclaration__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1749:1: ( ( rule__GrammarDeclaration__AsAssignment_2_1 ) )
            // InternalIdioms.g:1750:2: ( rule__GrammarDeclaration__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1751:2: ( rule__GrammarDeclaration__AsAssignment_2_1 )
            // InternalIdioms.g:1751:3: rule__GrammarDeclaration__AsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__GrammarDeclaration__AsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getAsAssignment_2_1());
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
    // $ANTLR end "rule__GrammarDeclaration__Group_2__1__Impl"


    // $ANTLR start "rule__IdiomsImport__Group__0"
    // InternalIdioms.g:1760:1: rule__IdiomsImport__Group__0 : rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 ;
    public final void rule__IdiomsImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1764:1: ( rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 )
            // InternalIdioms.g:1765:2: rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__IdiomsImport__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group__1();

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
    // $ANTLR end "rule__IdiomsImport__Group__0"


    // $ANTLR start "rule__IdiomsImport__Group__0__Impl"
    // InternalIdioms.g:1772:1: rule__IdiomsImport__Group__0__Impl : ( 'with' ) ;
    public final void rule__IdiomsImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1776:1: ( ( 'with' ) )
            // InternalIdioms.g:1777:1: ( 'with' )
            {
            // InternalIdioms.g:1777:1: ( 'with' )
            // InternalIdioms.g:1778:2: 'with'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getWithKeyword_0());
            }
            match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getWithKeyword_0());
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
    // $ANTLR end "rule__IdiomsImport__Group__0__Impl"


    // $ANTLR start "rule__IdiomsImport__Group__1"
    // InternalIdioms.g:1787:1: rule__IdiomsImport__Group__1 : rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 ;
    public final void rule__IdiomsImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1791:1: ( rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 )
            // InternalIdioms.g:1792:2: rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__IdiomsImport__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group__2();

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
    // $ANTLR end "rule__IdiomsImport__Group__1"


    // $ANTLR start "rule__IdiomsImport__Group__1__Impl"
    // InternalIdioms.g:1799:1: rule__IdiomsImport__Group__1__Impl : ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) ;
    public final void rule__IdiomsImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1803:1: ( ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) )
            // InternalIdioms.g:1804:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            {
            // InternalIdioms.g:1804:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            // InternalIdioms.g:1805:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1());
            }
            // InternalIdioms.g:1806:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            // InternalIdioms.g:1806:3: rule__IdiomsImport__IdiomsModelAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__IdiomsModelAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1());
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
    // $ANTLR end "rule__IdiomsImport__Group__1__Impl"


    // $ANTLR start "rule__IdiomsImport__Group__2"
    // InternalIdioms.g:1814:1: rule__IdiomsImport__Group__2 : rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 ;
    public final void rule__IdiomsImport__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1818:1: ( rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 )
            // InternalIdioms.g:1819:2: rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__IdiomsImport__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group__3();

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
    // $ANTLR end "rule__IdiomsImport__Group__2"


    // $ANTLR start "rule__IdiomsImport__Group__2__Impl"
    // InternalIdioms.g:1826:1: rule__IdiomsImport__Group__2__Impl : ( ( rule__IdiomsImport__Group_2__0 )? ) ;
    public final void rule__IdiomsImport__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1830:1: ( ( ( rule__IdiomsImport__Group_2__0 )? ) )
            // InternalIdioms.g:1831:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            {
            // InternalIdioms.g:1831:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            // InternalIdioms.g:1832:2: ( rule__IdiomsImport__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getGroup_2());
            }
            // InternalIdioms.g:1833:2: ( rule__IdiomsImport__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==16) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalIdioms.g:1833:3: rule__IdiomsImport__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsImport__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getGroup_2());
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
    // $ANTLR end "rule__IdiomsImport__Group__2__Impl"


    // $ANTLR start "rule__IdiomsImport__Group__3"
    // InternalIdioms.g:1841:1: rule__IdiomsImport__Group__3 : rule__IdiomsImport__Group__3__Impl ;
    public final void rule__IdiomsImport__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1845:1: ( rule__IdiomsImport__Group__3__Impl )
            // InternalIdioms.g:1846:2: rule__IdiomsImport__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group__3__Impl();

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
    // $ANTLR end "rule__IdiomsImport__Group__3"


    // $ANTLR start "rule__IdiomsImport__Group__3__Impl"
    // InternalIdioms.g:1852:1: rule__IdiomsImport__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__IdiomsImport__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1856:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1857:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1857:1: ( ( ';' )? )
            // InternalIdioms.g:1858:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1859:2: ( ';' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==15) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalIdioms.g:1859:3: ';'
                    {
                    match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());
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
    // $ANTLR end "rule__IdiomsImport__Group__3__Impl"


    // $ANTLR start "rule__IdiomsImport__Group_2__0"
    // InternalIdioms.g:1868:1: rule__IdiomsImport__Group_2__0 : rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 ;
    public final void rule__IdiomsImport__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1872:1: ( rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 )
            // InternalIdioms.g:1873:2: rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__IdiomsImport__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group_2__1();

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
    // $ANTLR end "rule__IdiomsImport__Group_2__0"


    // $ANTLR start "rule__IdiomsImport__Group_2__0__Impl"
    // InternalIdioms.g:1880:1: rule__IdiomsImport__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__IdiomsImport__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1884:1: ( ( 'as' ) )
            // InternalIdioms.g:1885:1: ( 'as' )
            {
            // InternalIdioms.g:1885:1: ( 'as' )
            // InternalIdioms.g:1886:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());
            }
            match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());
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
    // $ANTLR end "rule__IdiomsImport__Group_2__0__Impl"


    // $ANTLR start "rule__IdiomsImport__Group_2__1"
    // InternalIdioms.g:1895:1: rule__IdiomsImport__Group_2__1 : rule__IdiomsImport__Group_2__1__Impl ;
    public final void rule__IdiomsImport__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1899:1: ( rule__IdiomsImport__Group_2__1__Impl )
            // InternalIdioms.g:1900:2: rule__IdiomsImport__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__Group_2__1__Impl();

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
    // $ANTLR end "rule__IdiomsImport__Group_2__1"


    // $ANTLR start "rule__IdiomsImport__Group_2__1__Impl"
    // InternalIdioms.g:1906:1: rule__IdiomsImport__Group_2__1__Impl : ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) ;
    public final void rule__IdiomsImport__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1910:1: ( ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1911:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1911:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            // InternalIdioms.g:1912:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1913:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            // InternalIdioms.g:1913:3: rule__IdiomsImport__AsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsImport__AsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1());
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
    // $ANTLR end "rule__IdiomsImport__Group_2__1__Impl"


    // $ANTLR start "rule__LocatorDeclaration__Group__0"
    // InternalIdioms.g:1922:1: rule__LocatorDeclaration__Group__0 : rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 ;
    public final void rule__LocatorDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1926:1: ( rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 )
            // InternalIdioms.g:1927:2: rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__LocatorDeclaration__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__Group__1();

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
    // $ANTLR end "rule__LocatorDeclaration__Group__0"


    // $ANTLR start "rule__LocatorDeclaration__Group__0__Impl"
    // InternalIdioms.g:1934:1: rule__LocatorDeclaration__Group__0__Impl : ( 'locator' ) ;
    public final void rule__LocatorDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1938:1: ( ( 'locator' ) )
            // InternalIdioms.g:1939:1: ( 'locator' )
            {
            // InternalIdioms.g:1939:1: ( 'locator' )
            // InternalIdioms.g:1940:2: 'locator'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());
            }
            match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());
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
    // $ANTLR end "rule__LocatorDeclaration__Group__0__Impl"


    // $ANTLR start "rule__LocatorDeclaration__Group__1"
    // InternalIdioms.g:1949:1: rule__LocatorDeclaration__Group__1 : rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 ;
    public final void rule__LocatorDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1953:1: ( rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 )
            // InternalIdioms.g:1954:2: rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__LocatorDeclaration__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__Group__2();

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
    // $ANTLR end "rule__LocatorDeclaration__Group__1"


    // $ANTLR start "rule__LocatorDeclaration__Group__1__Impl"
    // InternalIdioms.g:1961:1: rule__LocatorDeclaration__Group__1__Impl : ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) ;
    public final void rule__LocatorDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1965:1: ( ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:1966:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:1966:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:1967:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:1968:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            // InternalIdioms.g:1968:3: rule__LocatorDeclaration__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1());
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
    // $ANTLR end "rule__LocatorDeclaration__Group__1__Impl"


    // $ANTLR start "rule__LocatorDeclaration__Group__2"
    // InternalIdioms.g:1976:1: rule__LocatorDeclaration__Group__2 : rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 ;
    public final void rule__LocatorDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1980:1: ( rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 )
            // InternalIdioms.g:1981:2: rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__LocatorDeclaration__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__Group__3();

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
    // $ANTLR end "rule__LocatorDeclaration__Group__2"


    // $ANTLR start "rule__LocatorDeclaration__Group__2__Impl"
    // InternalIdioms.g:1988:1: rule__LocatorDeclaration__Group__2__Impl : ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__LocatorDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1992:1: ( ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:1993:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:1993:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:1994:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:1995:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:1995:3: rule__LocatorDeclaration__OwnedLocatorAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__OwnedLocatorAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2());
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
    // $ANTLR end "rule__LocatorDeclaration__Group__2__Impl"


    // $ANTLR start "rule__LocatorDeclaration__Group__3"
    // InternalIdioms.g:2003:1: rule__LocatorDeclaration__Group__3 : rule__LocatorDeclaration__Group__3__Impl ;
    public final void rule__LocatorDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2007:1: ( rule__LocatorDeclaration__Group__3__Impl )
            // InternalIdioms.g:2008:2: rule__LocatorDeclaration__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__LocatorDeclaration__Group__3__Impl();

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
    // $ANTLR end "rule__LocatorDeclaration__Group__3"


    // $ANTLR start "rule__LocatorDeclaration__Group__3__Impl"
    // InternalIdioms.g:2014:1: rule__LocatorDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__LocatorDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2018:1: ( ( ';' ) )
            // InternalIdioms.g:2019:1: ( ';' )
            {
            // InternalIdioms.g:2019:1: ( ';' )
            // InternalIdioms.g:2020:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3());
            }
            match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3());
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
    // $ANTLR end "rule__LocatorDeclaration__Group__3__Impl"


    // $ANTLR start "rule__AnyAssignmentLocator__Group__0"
    // InternalIdioms.g:2030:1: rule__AnyAssignmentLocator__Group__0 : rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 ;
    public final void rule__AnyAssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2034:1: ( rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 )
            // InternalIdioms.g:2035:2: rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__AnyAssignmentLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyAssignmentLocator__Group__1();

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
    // $ANTLR end "rule__AnyAssignmentLocator__Group__0"


    // $ANTLR start "rule__AnyAssignmentLocator__Group__0__Impl"
    // InternalIdioms.g:2042:1: rule__AnyAssignmentLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyAssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2046:1: ( ( () ) )
            // InternalIdioms.g:2047:1: ( () )
            {
            // InternalIdioms.g:2047:1: ( () )
            // InternalIdioms.g:2048:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0());
            }
            // InternalIdioms.g:2049:2: ()
            // InternalIdioms.g:2049:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnyAssignmentLocator__Group__0__Impl"


    // $ANTLR start "rule__AnyAssignmentLocator__Group__1"
    // InternalIdioms.g:2057:1: rule__AnyAssignmentLocator__Group__1 : rule__AnyAssignmentLocator__Group__1__Impl ;
    public final void rule__AnyAssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2061:1: ( rule__AnyAssignmentLocator__Group__1__Impl )
            // InternalIdioms.g:2062:2: rule__AnyAssignmentLocator__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyAssignmentLocator__Group__1__Impl();

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
    // $ANTLR end "rule__AnyAssignmentLocator__Group__1"


    // $ANTLR start "rule__AnyAssignmentLocator__Group__1__Impl"
    // InternalIdioms.g:2068:1: rule__AnyAssignmentLocator__Group__1__Impl : ( 'any-assignment' ) ;
    public final void rule__AnyAssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2072:1: ( ( 'any-assignment' ) )
            // InternalIdioms.g:2073:1: ( 'any-assignment' )
            {
            // InternalIdioms.g:2073:1: ( 'any-assignment' )
            // InternalIdioms.g:2074:2: 'any-assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());
            }
            match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());
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
    // $ANTLR end "rule__AnyAssignmentLocator__Group__1__Impl"


    // $ANTLR start "rule__AnyElementLocator__Group__0"
    // InternalIdioms.g:2084:1: rule__AnyElementLocator__Group__0 : rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 ;
    public final void rule__AnyElementLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2088:1: ( rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 )
            // InternalIdioms.g:2089:2: rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_13);
            rule__AnyElementLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyElementLocator__Group__1();

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
    // $ANTLR end "rule__AnyElementLocator__Group__0"


    // $ANTLR start "rule__AnyElementLocator__Group__0__Impl"
    // InternalIdioms.g:2096:1: rule__AnyElementLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyElementLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2100:1: ( ( () ) )
            // InternalIdioms.g:2101:1: ( () )
            {
            // InternalIdioms.g:2101:1: ( () )
            // InternalIdioms.g:2102:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0());
            }
            // InternalIdioms.g:2103:2: ()
            // InternalIdioms.g:2103:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AnyElementLocator__Group__0__Impl"


    // $ANTLR start "rule__AnyElementLocator__Group__1"
    // InternalIdioms.g:2111:1: rule__AnyElementLocator__Group__1 : rule__AnyElementLocator__Group__1__Impl ;
    public final void rule__AnyElementLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2115:1: ( rule__AnyElementLocator__Group__1__Impl )
            // InternalIdioms.g:2116:2: rule__AnyElementLocator__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AnyElementLocator__Group__1__Impl();

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
    // $ANTLR end "rule__AnyElementLocator__Group__1"


    // $ANTLR start "rule__AnyElementLocator__Group__1__Impl"
    // InternalIdioms.g:2122:1: rule__AnyElementLocator__Group__1__Impl : ( 'any-element' ) ;
    public final void rule__AnyElementLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2126:1: ( ( 'any-element' ) )
            // InternalIdioms.g:2127:1: ( 'any-element' )
            {
            // InternalIdioms.g:2127:1: ( 'any-element' )
            // InternalIdioms.g:2128:2: 'any-element'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());
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
    // $ANTLR end "rule__AnyElementLocator__Group__1__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group__0"
    // InternalIdioms.g:2138:1: rule__AssignmentLocator__Group__0 : rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 ;
    public final void rule__AssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2142:1: ( rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 )
            // InternalIdioms.g:2143:2: rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__AssignmentLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group__1();

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
    // $ANTLR end "rule__AssignmentLocator__Group__0"


    // $ANTLR start "rule__AssignmentLocator__Group__0__Impl"
    // InternalIdioms.g:2150:1: rule__AssignmentLocator__Group__0__Impl : ( 'assignment' ) ;
    public final void rule__AssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2154:1: ( ( 'assignment' ) )
            // InternalIdioms.g:2155:1: ( 'assignment' )
            {
            // InternalIdioms.g:2155:1: ( 'assignment' )
            // InternalIdioms.g:2156:2: 'assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());
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
    // $ANTLR end "rule__AssignmentLocator__Group__0__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group__1"
    // InternalIdioms.g:2165:1: rule__AssignmentLocator__Group__1 : rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 ;
    public final void rule__AssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2169:1: ( rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 )
            // InternalIdioms.g:2170:2: rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__AssignmentLocator__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group__2();

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
    // $ANTLR end "rule__AssignmentLocator__Group__1"


    // $ANTLR start "rule__AssignmentLocator__Group__1__Impl"
    // InternalIdioms.g:2177:1: rule__AssignmentLocator__Group__1__Impl : ( ( rule__AssignmentLocator__Group_1__0 )? ) ;
    public final void rule__AssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2181:1: ( ( ( rule__AssignmentLocator__Group_1__0 )? ) )
            // InternalIdioms.g:2182:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:2182:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            // InternalIdioms.g:2183:2: ( rule__AssignmentLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:2184:2: ( rule__AssignmentLocator__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==23) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // InternalIdioms.g:2184:3: rule__AssignmentLocator__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__AssignmentLocator__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getGroup_1());
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
    // $ANTLR end "rule__AssignmentLocator__Group__1__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group__2"
    // InternalIdioms.g:2192:1: rule__AssignmentLocator__Group__2 : rule__AssignmentLocator__Group__2__Impl ;
    public final void rule__AssignmentLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2196:1: ( rule__AssignmentLocator__Group__2__Impl )
            // InternalIdioms.g:2197:2: rule__AssignmentLocator__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group__2__Impl();

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
    // $ANTLR end "rule__AssignmentLocator__Group__2"


    // $ANTLR start "rule__AssignmentLocator__Group__2__Impl"
    // InternalIdioms.g:2203:1: rule__AssignmentLocator__Group__2__Impl : ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) ;
    public final void rule__AssignmentLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2207:1: ( ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) )
            // InternalIdioms.g:2208:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            {
            // InternalIdioms.g:2208:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            // InternalIdioms.g:2209:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2());
            }
            // InternalIdioms.g:2210:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            // InternalIdioms.g:2210:3: rule__AssignmentLocator__EStructuralFeatureAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__EStructuralFeatureAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2());
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
    // $ANTLR end "rule__AssignmentLocator__Group__2__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group_1__0"
    // InternalIdioms.g:2219:1: rule__AssignmentLocator__Group_1__0 : rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 ;
    public final void rule__AssignmentLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2223:1: ( rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 )
            // InternalIdioms.g:2224:2: rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__AssignmentLocator__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group_1__1();

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
    // $ANTLR end "rule__AssignmentLocator__Group_1__0"


    // $ANTLR start "rule__AssignmentLocator__Group_1__0__Impl"
    // InternalIdioms.g:2231:1: rule__AssignmentLocator__Group_1__0__Impl : ( ( rule__AssignmentLocator__Group_1_0__0 )? ) ;
    public final void rule__AssignmentLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2235:1: ( ( ( rule__AssignmentLocator__Group_1_0__0 )? ) )
            // InternalIdioms.g:2236:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            {
            // InternalIdioms.g:2236:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            // InternalIdioms.g:2237:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0());
            }
            // InternalIdioms.g:2238:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==23) ) {
                    int LA18_2 = input.LA(3);

                    if ( (LA18_2==RULE_ID) ) {
                        int LA18_3 = input.LA(4);

                        if ( (LA18_3==23) ) {
                            alt18=1;
                        }
                    }
                }
            }
            switch (alt18) {
                case 1 :
                    // InternalIdioms.g:2238:3: rule__AssignmentLocator__Group_1_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__AssignmentLocator__Group_1_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0());
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
    // $ANTLR end "rule__AssignmentLocator__Group_1__0__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group_1__1"
    // InternalIdioms.g:2246:1: rule__AssignmentLocator__Group_1__1 : rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 ;
    public final void rule__AssignmentLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2250:1: ( rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 )
            // InternalIdioms.g:2251:2: rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__AssignmentLocator__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group_1__2();

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
    // $ANTLR end "rule__AssignmentLocator__Group_1__1"


    // $ANTLR start "rule__AssignmentLocator__Group_1__1__Impl"
    // InternalIdioms.g:2258:1: rule__AssignmentLocator__Group_1__1__Impl : ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) ;
    public final void rule__AssignmentLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2262:1: ( ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) )
            // InternalIdioms.g:2263:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            {
            // InternalIdioms.g:2263:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            // InternalIdioms.g:2264:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1());
            }
            // InternalIdioms.g:2265:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            // InternalIdioms.g:2265:3: rule__AssignmentLocator__EClassAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__EClassAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1());
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
    // $ANTLR end "rule__AssignmentLocator__Group_1__1__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group_1__2"
    // InternalIdioms.g:2273:1: rule__AssignmentLocator__Group_1__2 : rule__AssignmentLocator__Group_1__2__Impl ;
    public final void rule__AssignmentLocator__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2277:1: ( rule__AssignmentLocator__Group_1__2__Impl )
            // InternalIdioms.g:2278:2: rule__AssignmentLocator__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group_1__2__Impl();

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
    // $ANTLR end "rule__AssignmentLocator__Group_1__2"


    // $ANTLR start "rule__AssignmentLocator__Group_1__2__Impl"
    // InternalIdioms.g:2284:1: rule__AssignmentLocator__Group_1__2__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2288:1: ( ( '::' ) )
            // InternalIdioms.g:2289:1: ( '::' )
            {
            // InternalIdioms.g:2289:1: ( '::' )
            // InternalIdioms.g:2290:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());
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
    // $ANTLR end "rule__AssignmentLocator__Group_1__2__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group_1_0__0"
    // InternalIdioms.g:2300:1: rule__AssignmentLocator__Group_1_0__0 : rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 ;
    public final void rule__AssignmentLocator__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2304:1: ( rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 )
            // InternalIdioms.g:2305:2: rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__AssignmentLocator__Group_1_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group_1_0__1();

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
    // $ANTLR end "rule__AssignmentLocator__Group_1_0__0"


    // $ANTLR start "rule__AssignmentLocator__Group_1_0__0__Impl"
    // InternalIdioms.g:2312:1: rule__AssignmentLocator__Group_1_0__0__Impl : ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) ;
    public final void rule__AssignmentLocator__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2316:1: ( ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) )
            // InternalIdioms.g:2317:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            {
            // InternalIdioms.g:2317:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            // InternalIdioms.g:2318:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0());
            }
            // InternalIdioms.g:2319:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            // InternalIdioms.g:2319:3: rule__AssignmentLocator__EPackageAssignment_1_0_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__EPackageAssignment_1_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0());
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
    // $ANTLR end "rule__AssignmentLocator__Group_1_0__0__Impl"


    // $ANTLR start "rule__AssignmentLocator__Group_1_0__1"
    // InternalIdioms.g:2327:1: rule__AssignmentLocator__Group_1_0__1 : rule__AssignmentLocator__Group_1_0__1__Impl ;
    public final void rule__AssignmentLocator__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2331:1: ( rule__AssignmentLocator__Group_1_0__1__Impl )
            // InternalIdioms.g:2332:2: rule__AssignmentLocator__Group_1_0__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__AssignmentLocator__Group_1_0__1__Impl();

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
    // $ANTLR end "rule__AssignmentLocator__Group_1_0__1"


    // $ANTLR start "rule__AssignmentLocator__Group_1_0__1__Impl"
    // InternalIdioms.g:2338:1: rule__AssignmentLocator__Group_1_0__1__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2342:1: ( ( '::' ) )
            // InternalIdioms.g:2343:1: ( '::' )
            {
            // InternalIdioms.g:2343:1: ( '::' )
            // InternalIdioms.g:2344:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());
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
    // $ANTLR end "rule__AssignmentLocator__Group_1_0__1__Impl"


    // $ANTLR start "rule__FinalLocator__Group__0"
    // InternalIdioms.g:2354:1: rule__FinalLocator__Group__0 : rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 ;
    public final void rule__FinalLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2358:1: ( rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 )
            // InternalIdioms.g:2359:2: rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
            rule__FinalLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FinalLocator__Group__1();

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
    // $ANTLR end "rule__FinalLocator__Group__0"


    // $ANTLR start "rule__FinalLocator__Group__0__Impl"
    // InternalIdioms.g:2366:1: rule__FinalLocator__Group__0__Impl : ( () ) ;
    public final void rule__FinalLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2370:1: ( ( () ) )
            // InternalIdioms.g:2371:1: ( () )
            {
            // InternalIdioms.g:2371:1: ( () )
            // InternalIdioms.g:2372:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0());
            }
            // InternalIdioms.g:2373:2: ()
            // InternalIdioms.g:2373:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FinalLocator__Group__0__Impl"


    // $ANTLR start "rule__FinalLocator__Group__1"
    // InternalIdioms.g:2381:1: rule__FinalLocator__Group__1 : rule__FinalLocator__Group__1__Impl ;
    public final void rule__FinalLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2385:1: ( rule__FinalLocator__Group__1__Impl )
            // InternalIdioms.g:2386:2: rule__FinalLocator__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FinalLocator__Group__1__Impl();

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
    // $ANTLR end "rule__FinalLocator__Group__1"


    // $ANTLR start "rule__FinalLocator__Group__1__Impl"
    // InternalIdioms.g:2392:1: rule__FinalLocator__Group__1__Impl : ( 'final' ) ;
    public final void rule__FinalLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2396:1: ( ( 'final' ) )
            // InternalIdioms.g:2397:1: ( 'final' )
            {
            // InternalIdioms.g:2397:1: ( 'final' )
            // InternalIdioms.g:2398:2: 'final'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());
            }
            match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());
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
    // $ANTLR end "rule__FinalLocator__Group__1__Impl"


    // $ANTLR start "rule__ReturnsLocator__Group__0"
    // InternalIdioms.g:2408:1: rule__ReturnsLocator__Group__0 : rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 ;
    public final void rule__ReturnsLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2412:1: ( rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 )
            // InternalIdioms.g:2413:2: rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__ReturnsLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group__1();

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
    // $ANTLR end "rule__ReturnsLocator__Group__0"


    // $ANTLR start "rule__ReturnsLocator__Group__0__Impl"
    // InternalIdioms.g:2420:1: rule__ReturnsLocator__Group__0__Impl : ( 'returns' ) ;
    public final void rule__ReturnsLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2424:1: ( ( 'returns' ) )
            // InternalIdioms.g:2425:1: ( 'returns' )
            {
            // InternalIdioms.g:2425:1: ( 'returns' )
            // InternalIdioms.g:2426:2: 'returns'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());
            }
            match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());
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
    // $ANTLR end "rule__ReturnsLocator__Group__0__Impl"


    // $ANTLR start "rule__ReturnsLocator__Group__1"
    // InternalIdioms.g:2435:1: rule__ReturnsLocator__Group__1 : rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 ;
    public final void rule__ReturnsLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2439:1: ( rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 )
            // InternalIdioms.g:2440:2: rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__ReturnsLocator__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group__2();

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
    // $ANTLR end "rule__ReturnsLocator__Group__1"


    // $ANTLR start "rule__ReturnsLocator__Group__1__Impl"
    // InternalIdioms.g:2447:1: rule__ReturnsLocator__Group__1__Impl : ( ( rule__ReturnsLocator__Group_1__0 )? ) ;
    public final void rule__ReturnsLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2451:1: ( ( ( rule__ReturnsLocator__Group_1__0 )? ) )
            // InternalIdioms.g:2452:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:2452:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            // InternalIdioms.g:2453:2: ( rule__ReturnsLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:2454:2: ( rule__ReturnsLocator__Group_1__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==23) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // InternalIdioms.g:2454:3: rule__ReturnsLocator__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ReturnsLocator__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getGroup_1());
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
    // $ANTLR end "rule__ReturnsLocator__Group__1__Impl"


    // $ANTLR start "rule__ReturnsLocator__Group__2"
    // InternalIdioms.g:2462:1: rule__ReturnsLocator__Group__2 : rule__ReturnsLocator__Group__2__Impl ;
    public final void rule__ReturnsLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2466:1: ( rule__ReturnsLocator__Group__2__Impl )
            // InternalIdioms.g:2467:2: rule__ReturnsLocator__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group__2__Impl();

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
    // $ANTLR end "rule__ReturnsLocator__Group__2"


    // $ANTLR start "rule__ReturnsLocator__Group__2__Impl"
    // InternalIdioms.g:2473:1: rule__ReturnsLocator__Group__2__Impl : ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) ;
    public final void rule__ReturnsLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2477:1: ( ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) )
            // InternalIdioms.g:2478:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            {
            // InternalIdioms.g:2478:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            // InternalIdioms.g:2479:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2());
            }
            // InternalIdioms.g:2480:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            // InternalIdioms.g:2480:3: rule__ReturnsLocator__EClassAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__EClassAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2());
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
    // $ANTLR end "rule__ReturnsLocator__Group__2__Impl"


    // $ANTLR start "rule__ReturnsLocator__Group_1__0"
    // InternalIdioms.g:2489:1: rule__ReturnsLocator__Group_1__0 : rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 ;
    public final void rule__ReturnsLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2493:1: ( rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 )
            // InternalIdioms.g:2494:2: rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__ReturnsLocator__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group_1__1();

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
    // $ANTLR end "rule__ReturnsLocator__Group_1__0"


    // $ANTLR start "rule__ReturnsLocator__Group_1__0__Impl"
    // InternalIdioms.g:2501:1: rule__ReturnsLocator__Group_1__0__Impl : ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) ;
    public final void rule__ReturnsLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2505:1: ( ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) )
            // InternalIdioms.g:2506:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            {
            // InternalIdioms.g:2506:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            // InternalIdioms.g:2507:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0());
            }
            // InternalIdioms.g:2508:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            // InternalIdioms.g:2508:3: rule__ReturnsLocator__EPackageAssignment_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__EPackageAssignment_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0());
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
    // $ANTLR end "rule__ReturnsLocator__Group_1__0__Impl"


    // $ANTLR start "rule__ReturnsLocator__Group_1__1"
    // InternalIdioms.g:2516:1: rule__ReturnsLocator__Group_1__1 : rule__ReturnsLocator__Group_1__1__Impl ;
    public final void rule__ReturnsLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2520:1: ( rule__ReturnsLocator__Group_1__1__Impl )
            // InternalIdioms.g:2521:2: rule__ReturnsLocator__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReturnsLocator__Group_1__1__Impl();

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
    // $ANTLR end "rule__ReturnsLocator__Group_1__1"


    // $ANTLR start "rule__ReturnsLocator__Group_1__1__Impl"
    // InternalIdioms.g:2527:1: rule__ReturnsLocator__Group_1__1__Impl : ( '::' ) ;
    public final void rule__ReturnsLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2531:1: ( ( '::' ) )
            // InternalIdioms.g:2532:1: ( '::' )
            {
            // InternalIdioms.g:2532:1: ( '::' )
            // InternalIdioms.g:2533:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());
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
    // $ANTLR end "rule__ReturnsLocator__Group_1__1__Impl"


    // $ANTLR start "rule__ReferredLocator__Group__0"
    // InternalIdioms.g:2543:1: rule__ReferredLocator__Group__0 : rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 ;
    public final void rule__ReferredLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2547:1: ( rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 )
            // InternalIdioms.g:2548:2: rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__ReferredLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__Group__1();

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
    // $ANTLR end "rule__ReferredLocator__Group__0"


    // $ANTLR start "rule__ReferredLocator__Group__0__Impl"
    // InternalIdioms.g:2555:1: rule__ReferredLocator__Group__0__Impl : ( ( rule__ReferredLocator__Group_0__0 )? ) ;
    public final void rule__ReferredLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2559:1: ( ( ( rule__ReferredLocator__Group_0__0 )? ) )
            // InternalIdioms.g:2560:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            {
            // InternalIdioms.g:2560:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            // InternalIdioms.g:2561:2: ( rule__ReferredLocator__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup_0());
            }
            // InternalIdioms.g:2562:2: ( rule__ReferredLocator__Group_0__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==23) ) {
                    alt20=1;
                }
            }
            switch (alt20) {
                case 1 :
                    // InternalIdioms.g:2562:3: rule__ReferredLocator__Group_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ReferredLocator__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getGroup_0());
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
    // $ANTLR end "rule__ReferredLocator__Group__0__Impl"


    // $ANTLR start "rule__ReferredLocator__Group__1"
    // InternalIdioms.g:2570:1: rule__ReferredLocator__Group__1 : rule__ReferredLocator__Group__1__Impl ;
    public final void rule__ReferredLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2574:1: ( rule__ReferredLocator__Group__1__Impl )
            // InternalIdioms.g:2575:2: rule__ReferredLocator__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__Group__1__Impl();

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
    // $ANTLR end "rule__ReferredLocator__Group__1"


    // $ANTLR start "rule__ReferredLocator__Group__1__Impl"
    // InternalIdioms.g:2581:1: rule__ReferredLocator__Group__1__Impl : ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2585:1: ( ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:2586:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:2586:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            // InternalIdioms.g:2587:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1());
            }
            // InternalIdioms.g:2588:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            // InternalIdioms.g:2588:3: rule__ReferredLocator__LocatorDeclarationAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__LocatorDeclarationAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1());
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
    // $ANTLR end "rule__ReferredLocator__Group__1__Impl"


    // $ANTLR start "rule__ReferredLocator__Group_0__0"
    // InternalIdioms.g:2597:1: rule__ReferredLocator__Group_0__0 : rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 ;
    public final void rule__ReferredLocator__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2601:1: ( rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 )
            // InternalIdioms.g:2602:2: rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__ReferredLocator__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__Group_0__1();

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
    // $ANTLR end "rule__ReferredLocator__Group_0__0"


    // $ANTLR start "rule__ReferredLocator__Group_0__0__Impl"
    // InternalIdioms.g:2609:1: rule__ReferredLocator__Group_0__0__Impl : ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredLocator__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2613:1: ( ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:2614:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:2614:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:2615:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:2616:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:2616:3: rule__ReferredLocator__IdiomsModelAssignment_0_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__IdiomsModelAssignment_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0());
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
    // $ANTLR end "rule__ReferredLocator__Group_0__0__Impl"


    // $ANTLR start "rule__ReferredLocator__Group_0__1"
    // InternalIdioms.g:2624:1: rule__ReferredLocator__Group_0__1 : rule__ReferredLocator__Group_0__1__Impl ;
    public final void rule__ReferredLocator__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2628:1: ( rule__ReferredLocator__Group_0__1__Impl )
            // InternalIdioms.g:2629:2: rule__ReferredLocator__Group_0__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredLocator__Group_0__1__Impl();

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
    // $ANTLR end "rule__ReferredLocator__Group_0__1"


    // $ANTLR start "rule__ReferredLocator__Group_0__1__Impl"
    // InternalIdioms.g:2635:1: rule__ReferredLocator__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredLocator__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2639:1: ( ( '::' ) )
            // InternalIdioms.g:2640:1: ( '::' )
            {
            // InternalIdioms.g:2640:1: ( '::' )
            // InternalIdioms.g:2641:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());
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
    // $ANTLR end "rule__ReferredLocator__Group_0__1__Impl"


    // $ANTLR start "rule__RuleLocator__Group__0"
    // InternalIdioms.g:2651:1: rule__RuleLocator__Group__0 : rule__RuleLocator__Group__0__Impl rule__RuleLocator__Group__1 ;
    public final void rule__RuleLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2655:1: ( rule__RuleLocator__Group__0__Impl rule__RuleLocator__Group__1 )
            // InternalIdioms.g:2656:2: rule__RuleLocator__Group__0__Impl rule__RuleLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__RuleLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group__1();

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
    // $ANTLR end "rule__RuleLocator__Group__0"


    // $ANTLR start "rule__RuleLocator__Group__0__Impl"
    // InternalIdioms.g:2663:1: rule__RuleLocator__Group__0__Impl : ( 'rule' ) ;
    public final void rule__RuleLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2667:1: ( ( 'rule' ) )
            // InternalIdioms.g:2668:1: ( 'rule' )
            {
            // InternalIdioms.g:2668:1: ( 'rule' )
            // InternalIdioms.g:2669:2: 'rule'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getRuleKeyword_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getRuleKeyword_0());
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
    // $ANTLR end "rule__RuleLocator__Group__0__Impl"


    // $ANTLR start "rule__RuleLocator__Group__1"
    // InternalIdioms.g:2678:1: rule__RuleLocator__Group__1 : rule__RuleLocator__Group__1__Impl rule__RuleLocator__Group__2 ;
    public final void rule__RuleLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2682:1: ( rule__RuleLocator__Group__1__Impl rule__RuleLocator__Group__2 )
            // InternalIdioms.g:2683:2: rule__RuleLocator__Group__1__Impl rule__RuleLocator__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__RuleLocator__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group__2();

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
    // $ANTLR end "rule__RuleLocator__Group__1"


    // $ANTLR start "rule__RuleLocator__Group__1__Impl"
    // InternalIdioms.g:2690:1: rule__RuleLocator__Group__1__Impl : ( ( rule__RuleLocator__Group_1__0 )? ) ;
    public final void rule__RuleLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2694:1: ( ( ( rule__RuleLocator__Group_1__0 )? ) )
            // InternalIdioms.g:2695:1: ( ( rule__RuleLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:2695:1: ( ( rule__RuleLocator__Group_1__0 )? )
            // InternalIdioms.g:2696:2: ( rule__RuleLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:2697:2: ( rule__RuleLocator__Group_1__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_ID) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==23) ) {
                    alt21=1;
                }
            }
            switch (alt21) {
                case 1 :
                    // InternalIdioms.g:2697:3: rule__RuleLocator__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__RuleLocator__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getGroup_1());
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
    // $ANTLR end "rule__RuleLocator__Group__1__Impl"


    // $ANTLR start "rule__RuleLocator__Group__2"
    // InternalIdioms.g:2705:1: rule__RuleLocator__Group__2 : rule__RuleLocator__Group__2__Impl ;
    public final void rule__RuleLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2709:1: ( rule__RuleLocator__Group__2__Impl )
            // InternalIdioms.g:2710:2: rule__RuleLocator__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group__2__Impl();

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
    // $ANTLR end "rule__RuleLocator__Group__2"


    // $ANTLR start "rule__RuleLocator__Group__2__Impl"
    // InternalIdioms.g:2716:1: rule__RuleLocator__Group__2__Impl : ( ( rule__RuleLocator__ReferredRuleAssignment_2 ) ) ;
    public final void rule__RuleLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2720:1: ( ( ( rule__RuleLocator__ReferredRuleAssignment_2 ) ) )
            // InternalIdioms.g:2721:1: ( ( rule__RuleLocator__ReferredRuleAssignment_2 ) )
            {
            // InternalIdioms.g:2721:1: ( ( rule__RuleLocator__ReferredRuleAssignment_2 ) )
            // InternalIdioms.g:2722:2: ( rule__RuleLocator__ReferredRuleAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredRuleAssignment_2());
            }
            // InternalIdioms.g:2723:2: ( rule__RuleLocator__ReferredRuleAssignment_2 )
            // InternalIdioms.g:2723:3: rule__RuleLocator__ReferredRuleAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__ReferredRuleAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredRuleAssignment_2());
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
    // $ANTLR end "rule__RuleLocator__Group__2__Impl"


    // $ANTLR start "rule__RuleLocator__Group_1__0"
    // InternalIdioms.g:2732:1: rule__RuleLocator__Group_1__0 : rule__RuleLocator__Group_1__0__Impl rule__RuleLocator__Group_1__1 ;
    public final void rule__RuleLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2736:1: ( rule__RuleLocator__Group_1__0__Impl rule__RuleLocator__Group_1__1 )
            // InternalIdioms.g:2737:2: rule__RuleLocator__Group_1__0__Impl rule__RuleLocator__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__RuleLocator__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group_1__1();

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
    // $ANTLR end "rule__RuleLocator__Group_1__0"


    // $ANTLR start "rule__RuleLocator__Group_1__0__Impl"
    // InternalIdioms.g:2744:1: rule__RuleLocator__Group_1__0__Impl : ( ( rule__RuleLocator__ReferredGrammarAssignment_1_0 ) ) ;
    public final void rule__RuleLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2748:1: ( ( ( rule__RuleLocator__ReferredGrammarAssignment_1_0 ) ) )
            // InternalIdioms.g:2749:1: ( ( rule__RuleLocator__ReferredGrammarAssignment_1_0 ) )
            {
            // InternalIdioms.g:2749:1: ( ( rule__RuleLocator__ReferredGrammarAssignment_1_0 ) )
            // InternalIdioms.g:2750:2: ( rule__RuleLocator__ReferredGrammarAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredGrammarAssignment_1_0());
            }
            // InternalIdioms.g:2751:2: ( rule__RuleLocator__ReferredGrammarAssignment_1_0 )
            // InternalIdioms.g:2751:3: rule__RuleLocator__ReferredGrammarAssignment_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__ReferredGrammarAssignment_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredGrammarAssignment_1_0());
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
    // $ANTLR end "rule__RuleLocator__Group_1__0__Impl"


    // $ANTLR start "rule__RuleLocator__Group_1__1"
    // InternalIdioms.g:2759:1: rule__RuleLocator__Group_1__1 : rule__RuleLocator__Group_1__1__Impl ;
    public final void rule__RuleLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2763:1: ( rule__RuleLocator__Group_1__1__Impl )
            // InternalIdioms.g:2764:2: rule__RuleLocator__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__RuleLocator__Group_1__1__Impl();

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
    // $ANTLR end "rule__RuleLocator__Group_1__1"


    // $ANTLR start "rule__RuleLocator__Group_1__1__Impl"
    // InternalIdioms.g:2770:1: rule__RuleLocator__Group_1__1__Impl : ( '::' ) ;
    public final void rule__RuleLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2774:1: ( ( '::' ) )
            // InternalIdioms.g:2775:1: ( '::' )
            {
            // InternalIdioms.g:2775:1: ( '::' )
            // InternalIdioms.g:2776:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getColonColonKeyword_1_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getColonColonKeyword_1_1());
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
    // $ANTLR end "rule__RuleLocator__Group_1__1__Impl"


    // $ANTLR start "rule__SegmentDeclaration__Group__0"
    // InternalIdioms.g:2786:1: rule__SegmentDeclaration__Group__0 : rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 ;
    public final void rule__SegmentDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2790:1: ( rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 )
            // InternalIdioms.g:2791:2: rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__SegmentDeclaration__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__Group__1();

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
    // $ANTLR end "rule__SegmentDeclaration__Group__0"


    // $ANTLR start "rule__SegmentDeclaration__Group__0__Impl"
    // InternalIdioms.g:2798:1: rule__SegmentDeclaration__Group__0__Impl : ( 'segment' ) ;
    public final void rule__SegmentDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2802:1: ( ( 'segment' ) )
            // InternalIdioms.g:2803:1: ( 'segment' )
            {
            // InternalIdioms.g:2803:1: ( 'segment' )
            // InternalIdioms.g:2804:2: 'segment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());
            }
            match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());
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
    // $ANTLR end "rule__SegmentDeclaration__Group__0__Impl"


    // $ANTLR start "rule__SegmentDeclaration__Group__1"
    // InternalIdioms.g:2813:1: rule__SegmentDeclaration__Group__1 : rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 ;
    public final void rule__SegmentDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2817:1: ( rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 )
            // InternalIdioms.g:2818:2: rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__SegmentDeclaration__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__Group__2();

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
    // $ANTLR end "rule__SegmentDeclaration__Group__1"


    // $ANTLR start "rule__SegmentDeclaration__Group__1__Impl"
    // InternalIdioms.g:2825:1: rule__SegmentDeclaration__Group__1__Impl : ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) ;
    public final void rule__SegmentDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2829:1: ( ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:2830:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:2830:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:2831:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:2832:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            // InternalIdioms.g:2832:3: rule__SegmentDeclaration__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1());
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
    // $ANTLR end "rule__SegmentDeclaration__Group__1__Impl"


    // $ANTLR start "rule__SegmentDeclaration__Group__2"
    // InternalIdioms.g:2840:1: rule__SegmentDeclaration__Group__2 : rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 ;
    public final void rule__SegmentDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2844:1: ( rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 )
            // InternalIdioms.g:2845:2: rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_11);
            rule__SegmentDeclaration__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__Group__3();

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
    // $ANTLR end "rule__SegmentDeclaration__Group__2"


    // $ANTLR start "rule__SegmentDeclaration__Group__2__Impl"
    // InternalIdioms.g:2852:1: rule__SegmentDeclaration__Group__2__Impl : ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) ;
    public final void rule__SegmentDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2856:1: ( ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) )
            // InternalIdioms.g:2857:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            {
            // InternalIdioms.g:2857:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            // InternalIdioms.g:2858:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2());
            }
            // InternalIdioms.g:2859:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            // InternalIdioms.g:2859:3: rule__SegmentDeclaration__OwnedSegmentAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__OwnedSegmentAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2());
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
    // $ANTLR end "rule__SegmentDeclaration__Group__2__Impl"


    // $ANTLR start "rule__SegmentDeclaration__Group__3"
    // InternalIdioms.g:2867:1: rule__SegmentDeclaration__Group__3 : rule__SegmentDeclaration__Group__3__Impl ;
    public final void rule__SegmentDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2871:1: ( rule__SegmentDeclaration__Group__3__Impl )
            // InternalIdioms.g:2872:2: rule__SegmentDeclaration__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SegmentDeclaration__Group__3__Impl();

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
    // $ANTLR end "rule__SegmentDeclaration__Group__3"


    // $ANTLR start "rule__SegmentDeclaration__Group__3__Impl"
    // InternalIdioms.g:2878:1: rule__SegmentDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__SegmentDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2882:1: ( ( ';' ) )
            // InternalIdioms.g:2883:1: ( ';' )
            {
            // InternalIdioms.g:2883:1: ( ';' )
            // InternalIdioms.g:2884:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3());
            }
            match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3());
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
    // $ANTLR end "rule__SegmentDeclaration__Group__3__Impl"


    // $ANTLR start "rule__CustomSegment__Group__0"
    // InternalIdioms.g:2894:1: rule__CustomSegment__Group__0 : rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 ;
    public final void rule__CustomSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2898:1: ( rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 )
            // InternalIdioms.g:2899:2: rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__CustomSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CustomSegment__Group__1();

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
    // $ANTLR end "rule__CustomSegment__Group__0"


    // $ANTLR start "rule__CustomSegment__Group__0__Impl"
    // InternalIdioms.g:2906:1: rule__CustomSegment__Group__0__Impl : ( 'custom' ) ;
    public final void rule__CustomSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2910:1: ( ( 'custom' ) )
            // InternalIdioms.g:2911:1: ( 'custom' )
            {
            // InternalIdioms.g:2911:1: ( 'custom' )
            // InternalIdioms.g:2912:2: 'custom'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());
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
    // $ANTLR end "rule__CustomSegment__Group__0__Impl"


    // $ANTLR start "rule__CustomSegment__Group__1"
    // InternalIdioms.g:2921:1: rule__CustomSegment__Group__1 : rule__CustomSegment__Group__1__Impl ;
    public final void rule__CustomSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2925:1: ( rule__CustomSegment__Group__1__Impl )
            // InternalIdioms.g:2926:2: rule__CustomSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CustomSegment__Group__1__Impl();

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
    // $ANTLR end "rule__CustomSegment__Group__1"


    // $ANTLR start "rule__CustomSegment__Group__1__Impl"
    // InternalIdioms.g:2932:1: rule__CustomSegment__Group__1__Impl : ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) ;
    public final void rule__CustomSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2936:1: ( ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) )
            // InternalIdioms.g:2937:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            {
            // InternalIdioms.g:2937:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            // InternalIdioms.g:2938:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1());
            }
            // InternalIdioms.g:2939:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            // InternalIdioms.g:2939:3: rule__CustomSegment__SupportClassNameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CustomSegment__SupportClassNameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1());
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
    // $ANTLR end "rule__CustomSegment__Group__1__Impl"


    // $ANTLR start "rule__HalfNewLineSegment__Group__0"
    // InternalIdioms.g:2948:1: rule__HalfNewLineSegment__Group__0 : rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 ;
    public final void rule__HalfNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2952:1: ( rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 )
            // InternalIdioms.g:2953:2: rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__HalfNewLineSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HalfNewLineSegment__Group__1();

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
    // $ANTLR end "rule__HalfNewLineSegment__Group__0"


    // $ANTLR start "rule__HalfNewLineSegment__Group__0__Impl"
    // InternalIdioms.g:2960:1: rule__HalfNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__HalfNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2964:1: ( ( () ) )
            // InternalIdioms.g:2965:1: ( () )
            {
            // InternalIdioms.g:2965:1: ( () )
            // InternalIdioms.g:2966:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2967:2: ()
            // InternalIdioms.g:2967:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__HalfNewLineSegment__Group__0__Impl"


    // $ANTLR start "rule__HalfNewLineSegment__Group__1"
    // InternalIdioms.g:2975:1: rule__HalfNewLineSegment__Group__1 : rule__HalfNewLineSegment__Group__1__Impl ;
    public final void rule__HalfNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2979:1: ( rule__HalfNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:2980:2: rule__HalfNewLineSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__HalfNewLineSegment__Group__1__Impl();

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
    // $ANTLR end "rule__HalfNewLineSegment__Group__1"


    // $ANTLR start "rule__HalfNewLineSegment__Group__1__Impl"
    // InternalIdioms.g:2986:1: rule__HalfNewLineSegment__Group__1__Impl : ( 'half-new-line' ) ;
    public final void rule__HalfNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2990:1: ( ( 'half-new-line' ) )
            // InternalIdioms.g:2991:1: ( 'half-new-line' )
            {
            // InternalIdioms.g:2991:1: ( 'half-new-line' )
            // InternalIdioms.g:2992:2: 'half-new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1());
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
    // $ANTLR end "rule__HalfNewLineSegment__Group__1__Impl"


    // $ANTLR start "rule__NewLineSegment__Group__0"
    // InternalIdioms.g:3002:1: rule__NewLineSegment__Group__0 : rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 ;
    public final void rule__NewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3006:1: ( rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 )
            // InternalIdioms.g:3007:2: rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_18);
            rule__NewLineSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NewLineSegment__Group__1();

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
    // $ANTLR end "rule__NewLineSegment__Group__0"


    // $ANTLR start "rule__NewLineSegment__Group__0__Impl"
    // InternalIdioms.g:3014:1: rule__NewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__NewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3018:1: ( ( () ) )
            // InternalIdioms.g:3019:1: ( () )
            {
            // InternalIdioms.g:3019:1: ( () )
            // InternalIdioms.g:3020:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0());
            }
            // InternalIdioms.g:3021:2: ()
            // InternalIdioms.g:3021:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewLineSegment__Group__0__Impl"


    // $ANTLR start "rule__NewLineSegment__Group__1"
    // InternalIdioms.g:3029:1: rule__NewLineSegment__Group__1 : rule__NewLineSegment__Group__1__Impl ;
    public final void rule__NewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3033:1: ( rule__NewLineSegment__Group__1__Impl )
            // InternalIdioms.g:3034:2: rule__NewLineSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NewLineSegment__Group__1__Impl();

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
    // $ANTLR end "rule__NewLineSegment__Group__1"


    // $ANTLR start "rule__NewLineSegment__Group__1__Impl"
    // InternalIdioms.g:3040:1: rule__NewLineSegment__Group__1__Impl : ( 'new-line' ) ;
    public final void rule__NewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3044:1: ( ( 'new-line' ) )
            // InternalIdioms.g:3045:1: ( 'new-line' )
            {
            // InternalIdioms.g:3045:1: ( 'new-line' )
            // InternalIdioms.g:3046:2: 'new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1());
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
    // $ANTLR end "rule__NewLineSegment__Group__1__Impl"


    // $ANTLR start "rule__NoSpaceSegment__Group__0"
    // InternalIdioms.g:3056:1: rule__NoSpaceSegment__Group__0 : rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 ;
    public final void rule__NoSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3060:1: ( rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 )
            // InternalIdioms.g:3061:2: rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_19);
            rule__NoSpaceSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NoSpaceSegment__Group__1();

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
    // $ANTLR end "rule__NoSpaceSegment__Group__0"


    // $ANTLR start "rule__NoSpaceSegment__Group__0__Impl"
    // InternalIdioms.g:3068:1: rule__NoSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__NoSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3072:1: ( ( () ) )
            // InternalIdioms.g:3073:1: ( () )
            {
            // InternalIdioms.g:3073:1: ( () )
            // InternalIdioms.g:3074:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0());
            }
            // InternalIdioms.g:3075:2: ()
            // InternalIdioms.g:3075:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NoSpaceSegment__Group__0__Impl"


    // $ANTLR start "rule__NoSpaceSegment__Group__1"
    // InternalIdioms.g:3083:1: rule__NoSpaceSegment__Group__1 : rule__NoSpaceSegment__Group__1__Impl ;
    public final void rule__NoSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3087:1: ( rule__NoSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:3088:2: rule__NoSpaceSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NoSpaceSegment__Group__1__Impl();

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
    // $ANTLR end "rule__NoSpaceSegment__Group__1"


    // $ANTLR start "rule__NoSpaceSegment__Group__1__Impl"
    // InternalIdioms.g:3094:1: rule__NoSpaceSegment__Group__1__Impl : ( 'no-space' ) ;
    public final void rule__NoSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3098:1: ( ( 'no-space' ) )
            // InternalIdioms.g:3099:1: ( 'no-space' )
            {
            // InternalIdioms.g:3099:1: ( 'no-space' )
            // InternalIdioms.g:3100:2: 'no-space'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1());
            }
            match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1());
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
    // $ANTLR end "rule__NoSpaceSegment__Group__1__Impl"


    // $ANTLR start "rule__PopSegment__Group__0"
    // InternalIdioms.g:3110:1: rule__PopSegment__Group__0 : rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 ;
    public final void rule__PopSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3114:1: ( rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 )
            // InternalIdioms.g:3115:2: rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_20);
            rule__PopSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PopSegment__Group__1();

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
    // $ANTLR end "rule__PopSegment__Group__0"


    // $ANTLR start "rule__PopSegment__Group__0__Impl"
    // InternalIdioms.g:3122:1: rule__PopSegment__Group__0__Impl : ( () ) ;
    public final void rule__PopSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3126:1: ( ( () ) )
            // InternalIdioms.g:3127:1: ( () )
            {
            // InternalIdioms.g:3127:1: ( () )
            // InternalIdioms.g:3128:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0());
            }
            // InternalIdioms.g:3129:2: ()
            // InternalIdioms.g:3129:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PopSegment__Group__0__Impl"


    // $ANTLR start "rule__PopSegment__Group__1"
    // InternalIdioms.g:3137:1: rule__PopSegment__Group__1 : rule__PopSegment__Group__1__Impl ;
    public final void rule__PopSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3141:1: ( rule__PopSegment__Group__1__Impl )
            // InternalIdioms.g:3142:2: rule__PopSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PopSegment__Group__1__Impl();

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
    // $ANTLR end "rule__PopSegment__Group__1"


    // $ANTLR start "rule__PopSegment__Group__1__Impl"
    // InternalIdioms.g:3148:1: rule__PopSegment__Group__1__Impl : ( 'pop' ) ;
    public final void rule__PopSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3152:1: ( ( 'pop' ) )
            // InternalIdioms.g:3153:1: ( 'pop' )
            {
            // InternalIdioms.g:3153:1: ( 'pop' )
            // InternalIdioms.g:3154:2: 'pop'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getPopKeyword_1());
            }
            match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPopSegmentAccess().getPopKeyword_1());
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
    // $ANTLR end "rule__PopSegment__Group__1__Impl"


    // $ANTLR start "rule__PostCommentSegment__Group__0"
    // InternalIdioms.g:3164:1: rule__PostCommentSegment__Group__0 : rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 ;
    public final void rule__PostCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3168:1: ( rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 )
            // InternalIdioms.g:3169:2: rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_21);
            rule__PostCommentSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PostCommentSegment__Group__1();

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
    // $ANTLR end "rule__PostCommentSegment__Group__0"


    // $ANTLR start "rule__PostCommentSegment__Group__0__Impl"
    // InternalIdioms.g:3176:1: rule__PostCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PostCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3180:1: ( ( () ) )
            // InternalIdioms.g:3181:1: ( () )
            {
            // InternalIdioms.g:3181:1: ( () )
            // InternalIdioms.g:3182:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0());
            }
            // InternalIdioms.g:3183:2: ()
            // InternalIdioms.g:3183:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PostCommentSegment__Group__0__Impl"


    // $ANTLR start "rule__PostCommentSegment__Group__1"
    // InternalIdioms.g:3191:1: rule__PostCommentSegment__Group__1 : rule__PostCommentSegment__Group__1__Impl ;
    public final void rule__PostCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3195:1: ( rule__PostCommentSegment__Group__1__Impl )
            // InternalIdioms.g:3196:2: rule__PostCommentSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PostCommentSegment__Group__1__Impl();

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
    // $ANTLR end "rule__PostCommentSegment__Group__1"


    // $ANTLR start "rule__PostCommentSegment__Group__1__Impl"
    // InternalIdioms.g:3202:1: rule__PostCommentSegment__Group__1__Impl : ( 'post-comment' ) ;
    public final void rule__PostCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3206:1: ( ( 'post-comment' ) )
            // InternalIdioms.g:3207:1: ( 'post-comment' )
            {
            // InternalIdioms.g:3207:1: ( 'post-comment' )
            // InternalIdioms.g:3208:2: 'post-comment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1());
            }
            match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1());
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
    // $ANTLR end "rule__PostCommentSegment__Group__1__Impl"


    // $ANTLR start "rule__PreCommentSegment__Group__0"
    // InternalIdioms.g:3218:1: rule__PreCommentSegment__Group__0 : rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 ;
    public final void rule__PreCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3222:1: ( rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 )
            // InternalIdioms.g:3223:2: rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_22);
            rule__PreCommentSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PreCommentSegment__Group__1();

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
    // $ANTLR end "rule__PreCommentSegment__Group__0"


    // $ANTLR start "rule__PreCommentSegment__Group__0__Impl"
    // InternalIdioms.g:3230:1: rule__PreCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PreCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3234:1: ( ( () ) )
            // InternalIdioms.g:3235:1: ( () )
            {
            // InternalIdioms.g:3235:1: ( () )
            // InternalIdioms.g:3236:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0());
            }
            // InternalIdioms.g:3237:2: ()
            // InternalIdioms.g:3237:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PreCommentSegment__Group__0__Impl"


    // $ANTLR start "rule__PreCommentSegment__Group__1"
    // InternalIdioms.g:3245:1: rule__PreCommentSegment__Group__1 : rule__PreCommentSegment__Group__1__Impl ;
    public final void rule__PreCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3249:1: ( rule__PreCommentSegment__Group__1__Impl )
            // InternalIdioms.g:3250:2: rule__PreCommentSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PreCommentSegment__Group__1__Impl();

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
    // $ANTLR end "rule__PreCommentSegment__Group__1"


    // $ANTLR start "rule__PreCommentSegment__Group__1__Impl"
    // InternalIdioms.g:3256:1: rule__PreCommentSegment__Group__1__Impl : ( 'pre-comment' ) ;
    public final void rule__PreCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3260:1: ( ( 'pre-comment' ) )
            // InternalIdioms.g:3261:1: ( 'pre-comment' )
            {
            // InternalIdioms.g:3261:1: ( 'pre-comment' )
            // InternalIdioms.g:3262:2: 'pre-comment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1());
            }
            match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1());
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
    // $ANTLR end "rule__PreCommentSegment__Group__1__Impl"


    // $ANTLR start "rule__PushSegment__Group__0"
    // InternalIdioms.g:3272:1: rule__PushSegment__Group__0 : rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 ;
    public final void rule__PushSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3276:1: ( rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 )
            // InternalIdioms.g:3277:2: rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_23);
            rule__PushSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PushSegment__Group__1();

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
    // $ANTLR end "rule__PushSegment__Group__0"


    // $ANTLR start "rule__PushSegment__Group__0__Impl"
    // InternalIdioms.g:3284:1: rule__PushSegment__Group__0__Impl : ( () ) ;
    public final void rule__PushSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3288:1: ( ( () ) )
            // InternalIdioms.g:3289:1: ( () )
            {
            // InternalIdioms.g:3289:1: ( () )
            // InternalIdioms.g:3290:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0());
            }
            // InternalIdioms.g:3291:2: ()
            // InternalIdioms.g:3291:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PushSegment__Group__0__Impl"


    // $ANTLR start "rule__PushSegment__Group__1"
    // InternalIdioms.g:3299:1: rule__PushSegment__Group__1 : rule__PushSegment__Group__1__Impl ;
    public final void rule__PushSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3303:1: ( rule__PushSegment__Group__1__Impl )
            // InternalIdioms.g:3304:2: rule__PushSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PushSegment__Group__1__Impl();

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
    // $ANTLR end "rule__PushSegment__Group__1"


    // $ANTLR start "rule__PushSegment__Group__1__Impl"
    // InternalIdioms.g:3310:1: rule__PushSegment__Group__1__Impl : ( 'push' ) ;
    public final void rule__PushSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3314:1: ( ( 'push' ) )
            // InternalIdioms.g:3315:1: ( 'push' )
            {
            // InternalIdioms.g:3315:1: ( 'push' )
            // InternalIdioms.g:3316:2: 'push'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getPushKeyword_1());
            }
            match(input,35,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPushSegmentAccess().getPushKeyword_1());
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
    // $ANTLR end "rule__PushSegment__Group__1__Impl"


    // $ANTLR start "rule__SoftNewLineSegment__Group__0"
    // InternalIdioms.g:3326:1: rule__SoftNewLineSegment__Group__0 : rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 ;
    public final void rule__SoftNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3330:1: ( rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 )
            // InternalIdioms.g:3331:2: rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_24);
            rule__SoftNewLineSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftNewLineSegment__Group__1();

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
    // $ANTLR end "rule__SoftNewLineSegment__Group__0"


    // $ANTLR start "rule__SoftNewLineSegment__Group__0__Impl"
    // InternalIdioms.g:3338:1: rule__SoftNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3342:1: ( ( () ) )
            // InternalIdioms.g:3343:1: ( () )
            {
            // InternalIdioms.g:3343:1: ( () )
            // InternalIdioms.g:3344:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0());
            }
            // InternalIdioms.g:3345:2: ()
            // InternalIdioms.g:3345:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SoftNewLineSegment__Group__0__Impl"


    // $ANTLR start "rule__SoftNewLineSegment__Group__1"
    // InternalIdioms.g:3353:1: rule__SoftNewLineSegment__Group__1 : rule__SoftNewLineSegment__Group__1__Impl ;
    public final void rule__SoftNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3357:1: ( rule__SoftNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:3358:2: rule__SoftNewLineSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftNewLineSegment__Group__1__Impl();

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
    // $ANTLR end "rule__SoftNewLineSegment__Group__1"


    // $ANTLR start "rule__SoftNewLineSegment__Group__1__Impl"
    // InternalIdioms.g:3364:1: rule__SoftNewLineSegment__Group__1__Impl : ( 'soft-new-line' ) ;
    public final void rule__SoftNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3368:1: ( ( 'soft-new-line' ) )
            // InternalIdioms.g:3369:1: ( 'soft-new-line' )
            {
            // InternalIdioms.g:3369:1: ( 'soft-new-line' )
            // InternalIdioms.g:3370:2: 'soft-new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1());
            }
            match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1());
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
    // $ANTLR end "rule__SoftNewLineSegment__Group__1__Impl"


    // $ANTLR start "rule__SoftSpaceSegment__Group__0"
    // InternalIdioms.g:3380:1: rule__SoftSpaceSegment__Group__0 : rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 ;
    public final void rule__SoftSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3384:1: ( rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 )
            // InternalIdioms.g:3385:2: rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_25);
            rule__SoftSpaceSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftSpaceSegment__Group__1();

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
    // $ANTLR end "rule__SoftSpaceSegment__Group__0"


    // $ANTLR start "rule__SoftSpaceSegment__Group__0__Impl"
    // InternalIdioms.g:3392:1: rule__SoftSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3396:1: ( ( () ) )
            // InternalIdioms.g:3397:1: ( () )
            {
            // InternalIdioms.g:3397:1: ( () )
            // InternalIdioms.g:3398:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0());
            }
            // InternalIdioms.g:3399:2: ()
            // InternalIdioms.g:3399:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SoftSpaceSegment__Group__0__Impl"


    // $ANTLR start "rule__SoftSpaceSegment__Group__1"
    // InternalIdioms.g:3407:1: rule__SoftSpaceSegment__Group__1 : rule__SoftSpaceSegment__Group__1__Impl ;
    public final void rule__SoftSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3411:1: ( rule__SoftSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:3412:2: rule__SoftSpaceSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SoftSpaceSegment__Group__1__Impl();

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
    // $ANTLR end "rule__SoftSpaceSegment__Group__1"


    // $ANTLR start "rule__SoftSpaceSegment__Group__1__Impl"
    // InternalIdioms.g:3418:1: rule__SoftSpaceSegment__Group__1__Impl : ( 'soft-space' ) ;
    public final void rule__SoftSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3422:1: ( ( 'soft-space' ) )
            // InternalIdioms.g:3423:1: ( 'soft-space' )
            {
            // InternalIdioms.g:3423:1: ( 'soft-space' )
            // InternalIdioms.g:3424:2: 'soft-space'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1());
            }
            match(input,37,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1());
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
    // $ANTLR end "rule__SoftSpaceSegment__Group__1__Impl"


    // $ANTLR start "rule__StringSegment__Group__0"
    // InternalIdioms.g:3434:1: rule__StringSegment__Group__0 : rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 ;
    public final void rule__StringSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3438:1: ( rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 )
            // InternalIdioms.g:3439:2: rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__StringSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__Group__1();

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
    // $ANTLR end "rule__StringSegment__Group__0"


    // $ANTLR start "rule__StringSegment__Group__0__Impl"
    // InternalIdioms.g:3446:1: rule__StringSegment__Group__0__Impl : ( 'string' ) ;
    public final void rule__StringSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3450:1: ( ( 'string' ) )
            // InternalIdioms.g:3451:1: ( 'string' )
            {
            // InternalIdioms.g:3451:1: ( 'string' )
            // InternalIdioms.g:3452:2: 'string'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringKeyword_0());
            }
            match(input,38,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getStringKeyword_0());
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
    // $ANTLR end "rule__StringSegment__Group__0__Impl"


    // $ANTLR start "rule__StringSegment__Group__1"
    // InternalIdioms.g:3461:1: rule__StringSegment__Group__1 : rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 ;
    public final void rule__StringSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3465:1: ( rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 )
            // InternalIdioms.g:3466:2: rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_26);
            rule__StringSegment__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__Group__2();

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
    // $ANTLR end "rule__StringSegment__Group__1"


    // $ANTLR start "rule__StringSegment__Group__1__Impl"
    // InternalIdioms.g:3473:1: rule__StringSegment__Group__1__Impl : ( ( rule__StringSegment__StringAssignment_1 ) ) ;
    public final void rule__StringSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3477:1: ( ( ( rule__StringSegment__StringAssignment_1 ) ) )
            // InternalIdioms.g:3478:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            {
            // InternalIdioms.g:3478:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            // InternalIdioms.g:3479:2: ( rule__StringSegment__StringAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringAssignment_1());
            }
            // InternalIdioms.g:3480:2: ( rule__StringSegment__StringAssignment_1 )
            // InternalIdioms.g:3480:3: rule__StringSegment__StringAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__StringAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getStringAssignment_1());
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
    // $ANTLR end "rule__StringSegment__Group__1__Impl"


    // $ANTLR start "rule__StringSegment__Group__2"
    // InternalIdioms.g:3488:1: rule__StringSegment__Group__2 : rule__StringSegment__Group__2__Impl ;
    public final void rule__StringSegment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3492:1: ( rule__StringSegment__Group__2__Impl )
            // InternalIdioms.g:3493:2: rule__StringSegment__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__Group__2__Impl();

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
    // $ANTLR end "rule__StringSegment__Group__2"


    // $ANTLR start "rule__StringSegment__Group__2__Impl"
    // InternalIdioms.g:3499:1: rule__StringSegment__Group__2__Impl : ( ( rule__StringSegment__PrintableAssignment_2 )? ) ;
    public final void rule__StringSegment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3503:1: ( ( ( rule__StringSegment__PrintableAssignment_2 )? ) )
            // InternalIdioms.g:3504:1: ( ( rule__StringSegment__PrintableAssignment_2 )? )
            {
            // InternalIdioms.g:3504:1: ( ( rule__StringSegment__PrintableAssignment_2 )? )
            // InternalIdioms.g:3505:2: ( rule__StringSegment__PrintableAssignment_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2());
            }
            // InternalIdioms.g:3506:2: ( rule__StringSegment__PrintableAssignment_2 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==52) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalIdioms.g:3506:3: rule__StringSegment__PrintableAssignment_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__StringSegment__PrintableAssignment_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2());
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
    // $ANTLR end "rule__StringSegment__Group__2__Impl"


    // $ANTLR start "rule__ValueSegment__Group__0"
    // InternalIdioms.g:3515:1: rule__ValueSegment__Group__0 : rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 ;
    public final void rule__ValueSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3519:1: ( rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 )
            // InternalIdioms.g:3520:2: rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_27);
            rule__ValueSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ValueSegment__Group__1();

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
    // $ANTLR end "rule__ValueSegment__Group__0"


    // $ANTLR start "rule__ValueSegment__Group__0__Impl"
    // InternalIdioms.g:3527:1: rule__ValueSegment__Group__0__Impl : ( () ) ;
    public final void rule__ValueSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3531:1: ( ( () ) )
            // InternalIdioms.g:3532:1: ( () )
            {
            // InternalIdioms.g:3532:1: ( () )
            // InternalIdioms.g:3533:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0());
            }
            // InternalIdioms.g:3534:2: ()
            // InternalIdioms.g:3534:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueSegment__Group__0__Impl"


    // $ANTLR start "rule__ValueSegment__Group__1"
    // InternalIdioms.g:3542:1: rule__ValueSegment__Group__1 : rule__ValueSegment__Group__1__Impl ;
    public final void rule__ValueSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3546:1: ( rule__ValueSegment__Group__1__Impl )
            // InternalIdioms.g:3547:2: rule__ValueSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ValueSegment__Group__1__Impl();

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
    // $ANTLR end "rule__ValueSegment__Group__1"


    // $ANTLR start "rule__ValueSegment__Group__1__Impl"
    // InternalIdioms.g:3553:1: rule__ValueSegment__Group__1__Impl : ( 'value' ) ;
    public final void rule__ValueSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3557:1: ( ( 'value' ) )
            // InternalIdioms.g:3558:1: ( 'value' )
            {
            // InternalIdioms.g:3558:1: ( 'value' )
            // InternalIdioms.g:3559:2: 'value'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getValueKeyword_1());
            }
            match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getValueSegmentAccess().getValueKeyword_1());
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
    // $ANTLR end "rule__ValueSegment__Group__1__Impl"


    // $ANTLR start "rule__WrapAnchorSegment__Group__0"
    // InternalIdioms.g:3569:1: rule__WrapAnchorSegment__Group__0 : rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 ;
    public final void rule__WrapAnchorSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3573:1: ( rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 )
            // InternalIdioms.g:3574:2: rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_28);
            rule__WrapAnchorSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapAnchorSegment__Group__1();

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
    // $ANTLR end "rule__WrapAnchorSegment__Group__0"


    // $ANTLR start "rule__WrapAnchorSegment__Group__0__Impl"
    // InternalIdioms.g:3581:1: rule__WrapAnchorSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapAnchorSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3585:1: ( ( () ) )
            // InternalIdioms.g:3586:1: ( () )
            {
            // InternalIdioms.g:3586:1: ( () )
            // InternalIdioms.g:3587:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0());
            }
            // InternalIdioms.g:3588:2: ()
            // InternalIdioms.g:3588:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WrapAnchorSegment__Group__0__Impl"


    // $ANTLR start "rule__WrapAnchorSegment__Group__1"
    // InternalIdioms.g:3596:1: rule__WrapAnchorSegment__Group__1 : rule__WrapAnchorSegment__Group__1__Impl ;
    public final void rule__WrapAnchorSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3600:1: ( rule__WrapAnchorSegment__Group__1__Impl )
            // InternalIdioms.g:3601:2: rule__WrapAnchorSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapAnchorSegment__Group__1__Impl();

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
    // $ANTLR end "rule__WrapAnchorSegment__Group__1"


    // $ANTLR start "rule__WrapAnchorSegment__Group__1__Impl"
    // InternalIdioms.g:3607:1: rule__WrapAnchorSegment__Group__1__Impl : ( 'wrap-anchor' ) ;
    public final void rule__WrapAnchorSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3611:1: ( ( 'wrap-anchor' ) )
            // InternalIdioms.g:3612:1: ( 'wrap-anchor' )
            {
            // InternalIdioms.g:3612:1: ( 'wrap-anchor' )
            // InternalIdioms.g:3613:2: 'wrap-anchor'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1());
            }
            match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1());
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
    // $ANTLR end "rule__WrapAnchorSegment__Group__1__Impl"


    // $ANTLR start "rule__WrapBeginAllSegment__Group__0"
    // InternalIdioms.g:3623:1: rule__WrapBeginAllSegment__Group__0 : rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 ;
    public final void rule__WrapBeginAllSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3627:1: ( rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 )
            // InternalIdioms.g:3628:2: rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_29);
            rule__WrapBeginAllSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginAllSegment__Group__1();

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
    // $ANTLR end "rule__WrapBeginAllSegment__Group__0"


    // $ANTLR start "rule__WrapBeginAllSegment__Group__0__Impl"
    // InternalIdioms.g:3635:1: rule__WrapBeginAllSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginAllSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3639:1: ( ( () ) )
            // InternalIdioms.g:3640:1: ( () )
            {
            // InternalIdioms.g:3640:1: ( () )
            // InternalIdioms.g:3641:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0());
            }
            // InternalIdioms.g:3642:2: ()
            // InternalIdioms.g:3642:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WrapBeginAllSegment__Group__0__Impl"


    // $ANTLR start "rule__WrapBeginAllSegment__Group__1"
    // InternalIdioms.g:3650:1: rule__WrapBeginAllSegment__Group__1 : rule__WrapBeginAllSegment__Group__1__Impl ;
    public final void rule__WrapBeginAllSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3654:1: ( rule__WrapBeginAllSegment__Group__1__Impl )
            // InternalIdioms.g:3655:2: rule__WrapBeginAllSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginAllSegment__Group__1__Impl();

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
    // $ANTLR end "rule__WrapBeginAllSegment__Group__1"


    // $ANTLR start "rule__WrapBeginAllSegment__Group__1__Impl"
    // InternalIdioms.g:3661:1: rule__WrapBeginAllSegment__Group__1__Impl : ( 'wrap-begin-all' ) ;
    public final void rule__WrapBeginAllSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3665:1: ( ( 'wrap-begin-all' ) )
            // InternalIdioms.g:3666:1: ( 'wrap-begin-all' )
            {
            // InternalIdioms.g:3666:1: ( 'wrap-begin-all' )
            // InternalIdioms.g:3667:2: 'wrap-begin-all'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1());
            }
            match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1());
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
    // $ANTLR end "rule__WrapBeginAllSegment__Group__1__Impl"


    // $ANTLR start "rule__WrapBeginSomeSegment__Group__0"
    // InternalIdioms.g:3677:1: rule__WrapBeginSomeSegment__Group__0 : rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 ;
    public final void rule__WrapBeginSomeSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3681:1: ( rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 )
            // InternalIdioms.g:3682:2: rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_30);
            rule__WrapBeginSomeSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginSomeSegment__Group__1();

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
    // $ANTLR end "rule__WrapBeginSomeSegment__Group__0"


    // $ANTLR start "rule__WrapBeginSomeSegment__Group__0__Impl"
    // InternalIdioms.g:3689:1: rule__WrapBeginSomeSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginSomeSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3693:1: ( ( () ) )
            // InternalIdioms.g:3694:1: ( () )
            {
            // InternalIdioms.g:3694:1: ( () )
            // InternalIdioms.g:3695:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0());
            }
            // InternalIdioms.g:3696:2: ()
            // InternalIdioms.g:3696:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WrapBeginSomeSegment__Group__0__Impl"


    // $ANTLR start "rule__WrapBeginSomeSegment__Group__1"
    // InternalIdioms.g:3704:1: rule__WrapBeginSomeSegment__Group__1 : rule__WrapBeginSomeSegment__Group__1__Impl ;
    public final void rule__WrapBeginSomeSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3708:1: ( rule__WrapBeginSomeSegment__Group__1__Impl )
            // InternalIdioms.g:3709:2: rule__WrapBeginSomeSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapBeginSomeSegment__Group__1__Impl();

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
    // $ANTLR end "rule__WrapBeginSomeSegment__Group__1"


    // $ANTLR start "rule__WrapBeginSomeSegment__Group__1__Impl"
    // InternalIdioms.g:3715:1: rule__WrapBeginSomeSegment__Group__1__Impl : ( 'wrap-begin-some' ) ;
    public final void rule__WrapBeginSomeSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3719:1: ( ( 'wrap-begin-some' ) )
            // InternalIdioms.g:3720:1: ( 'wrap-begin-some' )
            {
            // InternalIdioms.g:3720:1: ( 'wrap-begin-some' )
            // InternalIdioms.g:3721:2: 'wrap-begin-some'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1());
            }
            match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1());
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
    // $ANTLR end "rule__WrapBeginSomeSegment__Group__1__Impl"


    // $ANTLR start "rule__WrapEndSegment__Group__0"
    // InternalIdioms.g:3731:1: rule__WrapEndSegment__Group__0 : rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 ;
    public final void rule__WrapEndSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3735:1: ( rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 )
            // InternalIdioms.g:3736:2: rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_31);
            rule__WrapEndSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapEndSegment__Group__1();

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
    // $ANTLR end "rule__WrapEndSegment__Group__0"


    // $ANTLR start "rule__WrapEndSegment__Group__0__Impl"
    // InternalIdioms.g:3743:1: rule__WrapEndSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapEndSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3747:1: ( ( () ) )
            // InternalIdioms.g:3748:1: ( () )
            {
            // InternalIdioms.g:3748:1: ( () )
            // InternalIdioms.g:3749:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0());
            }
            // InternalIdioms.g:3750:2: ()
            // InternalIdioms.g:3750:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WrapEndSegment__Group__0__Impl"


    // $ANTLR start "rule__WrapEndSegment__Group__1"
    // InternalIdioms.g:3758:1: rule__WrapEndSegment__Group__1 : rule__WrapEndSegment__Group__1__Impl ;
    public final void rule__WrapEndSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3762:1: ( rule__WrapEndSegment__Group__1__Impl )
            // InternalIdioms.g:3763:2: rule__WrapEndSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapEndSegment__Group__1__Impl();

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
    // $ANTLR end "rule__WrapEndSegment__Group__1"


    // $ANTLR start "rule__WrapEndSegment__Group__1__Impl"
    // InternalIdioms.g:3769:1: rule__WrapEndSegment__Group__1__Impl : ( 'wrap-end' ) ;
    public final void rule__WrapEndSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3773:1: ( ( 'wrap-end' ) )
            // InternalIdioms.g:3774:1: ( 'wrap-end' )
            {
            // InternalIdioms.g:3774:1: ( 'wrap-end' )
            // InternalIdioms.g:3775:2: 'wrap-end'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1());
            }
            match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1());
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
    // $ANTLR end "rule__WrapEndSegment__Group__1__Impl"


    // $ANTLR start "rule__WrapHereSegment__Group__0"
    // InternalIdioms.g:3785:1: rule__WrapHereSegment__Group__0 : rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 ;
    public final void rule__WrapHereSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3789:1: ( rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 )
            // InternalIdioms.g:3790:2: rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__WrapHereSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapHereSegment__Group__1();

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
    // $ANTLR end "rule__WrapHereSegment__Group__0"


    // $ANTLR start "rule__WrapHereSegment__Group__0__Impl"
    // InternalIdioms.g:3797:1: rule__WrapHereSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapHereSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3801:1: ( ( () ) )
            // InternalIdioms.g:3802:1: ( () )
            {
            // InternalIdioms.g:3802:1: ( () )
            // InternalIdioms.g:3803:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0());
            }
            // InternalIdioms.g:3804:2: ()
            // InternalIdioms.g:3804:3:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WrapHereSegment__Group__0__Impl"


    // $ANTLR start "rule__WrapHereSegment__Group__1"
    // InternalIdioms.g:3812:1: rule__WrapHereSegment__Group__1 : rule__WrapHereSegment__Group__1__Impl ;
    public final void rule__WrapHereSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3816:1: ( rule__WrapHereSegment__Group__1__Impl )
            // InternalIdioms.g:3817:2: rule__WrapHereSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WrapHereSegment__Group__1__Impl();

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
    // $ANTLR end "rule__WrapHereSegment__Group__1"


    // $ANTLR start "rule__WrapHereSegment__Group__1__Impl"
    // InternalIdioms.g:3823:1: rule__WrapHereSegment__Group__1__Impl : ( 'wrap-here' ) ;
    public final void rule__WrapHereSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3827:1: ( ( 'wrap-here' ) )
            // InternalIdioms.g:3828:1: ( 'wrap-here' )
            {
            // InternalIdioms.g:3828:1: ( 'wrap-here' )
            // InternalIdioms.g:3829:2: 'wrap-here'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1());
            }
            match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1());
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
    // $ANTLR end "rule__WrapHereSegment__Group__1__Impl"


    // $ANTLR start "rule__ReferredSegment__Group__0"
    // InternalIdioms.g:3839:1: rule__ReferredSegment__Group__0 : rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 ;
    public final void rule__ReferredSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3843:1: ( rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 )
            // InternalIdioms.g:3844:2: rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__ReferredSegment__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__Group__1();

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
    // $ANTLR end "rule__ReferredSegment__Group__0"


    // $ANTLR start "rule__ReferredSegment__Group__0__Impl"
    // InternalIdioms.g:3851:1: rule__ReferredSegment__Group__0__Impl : ( ( rule__ReferredSegment__Group_0__0 )? ) ;
    public final void rule__ReferredSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3855:1: ( ( ( rule__ReferredSegment__Group_0__0 )? ) )
            // InternalIdioms.g:3856:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            {
            // InternalIdioms.g:3856:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            // InternalIdioms.g:3857:2: ( rule__ReferredSegment__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getGroup_0());
            }
            // InternalIdioms.g:3858:2: ( rule__ReferredSegment__Group_0__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ID) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==23) ) {
                    alt23=1;
                }
            }
            switch (alt23) {
                case 1 :
                    // InternalIdioms.g:3858:3: rule__ReferredSegment__Group_0__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__ReferredSegment__Group_0__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getGroup_0());
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
    // $ANTLR end "rule__ReferredSegment__Group__0__Impl"


    // $ANTLR start "rule__ReferredSegment__Group__1"
    // InternalIdioms.g:3866:1: rule__ReferredSegment__Group__1 : rule__ReferredSegment__Group__1__Impl ;
    public final void rule__ReferredSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3870:1: ( rule__ReferredSegment__Group__1__Impl )
            // InternalIdioms.g:3871:2: rule__ReferredSegment__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__Group__1__Impl();

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
    // $ANTLR end "rule__ReferredSegment__Group__1"


    // $ANTLR start "rule__ReferredSegment__Group__1__Impl"
    // InternalIdioms.g:3877:1: rule__ReferredSegment__Group__1__Impl : ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3881:1: ( ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:3882:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:3882:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            // InternalIdioms.g:3883:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1());
            }
            // InternalIdioms.g:3884:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            // InternalIdioms.g:3884:3: rule__ReferredSegment__SegmentDeclarationAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__SegmentDeclarationAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1());
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
    // $ANTLR end "rule__ReferredSegment__Group__1__Impl"


    // $ANTLR start "rule__ReferredSegment__Group_0__0"
    // InternalIdioms.g:3893:1: rule__ReferredSegment__Group_0__0 : rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 ;
    public final void rule__ReferredSegment__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3897:1: ( rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 )
            // InternalIdioms.g:3898:2: rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__ReferredSegment__Group_0__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__Group_0__1();

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
    // $ANTLR end "rule__ReferredSegment__Group_0__0"


    // $ANTLR start "rule__ReferredSegment__Group_0__0__Impl"
    // InternalIdioms.g:3905:1: rule__ReferredSegment__Group_0__0__Impl : ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredSegment__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3909:1: ( ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:3910:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:3910:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:3911:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:3912:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:3912:3: rule__ReferredSegment__IdiomsModelAssignment_0_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__IdiomsModelAssignment_0_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0());
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
    // $ANTLR end "rule__ReferredSegment__Group_0__0__Impl"


    // $ANTLR start "rule__ReferredSegment__Group_0__1"
    // InternalIdioms.g:3920:1: rule__ReferredSegment__Group_0__1 : rule__ReferredSegment__Group_0__1__Impl ;
    public final void rule__ReferredSegment__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3924:1: ( rule__ReferredSegment__Group_0__1__Impl )
            // InternalIdioms.g:3925:2: rule__ReferredSegment__Group_0__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ReferredSegment__Group_0__1__Impl();

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
    // $ANTLR end "rule__ReferredSegment__Group_0__1"


    // $ANTLR start "rule__ReferredSegment__Group_0__1__Impl"
    // InternalIdioms.g:3931:1: rule__ReferredSegment__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredSegment__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3935:1: ( ( '::' ) )
            // InternalIdioms.g:3936:1: ( '::' )
            {
            // InternalIdioms.g:3936:1: ( '::' )
            // InternalIdioms.g:3937:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());
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
    // $ANTLR end "rule__ReferredSegment__Group_0__1__Impl"


    // $ANTLR start "rule__Idiom__Group__0"
    // InternalIdioms.g:3947:1: rule__Idiom__Group__0 : rule__Idiom__Group__0__Impl rule__Idiom__Group__1 ;
    public final void rule__Idiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3951:1: ( rule__Idiom__Group__0__Impl rule__Idiom__Group__1 )
            // InternalIdioms.g:3952:2: rule__Idiom__Group__0__Impl rule__Idiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_32);
            rule__Idiom__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__1();

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
    // $ANTLR end "rule__Idiom__Group__0"


    // $ANTLR start "rule__Idiom__Group__0__Impl"
    // InternalIdioms.g:3959:1: rule__Idiom__Group__0__Impl : ( ( rule__Idiom__MixinAssignment_0 )? ) ;
    public final void rule__Idiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3963:1: ( ( ( rule__Idiom__MixinAssignment_0 )? ) )
            // InternalIdioms.g:3964:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            {
            // InternalIdioms.g:3964:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            // InternalIdioms.g:3965:2: ( rule__Idiom__MixinAssignment_0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinAssignment_0());
            }
            // InternalIdioms.g:3966:2: ( rule__Idiom__MixinAssignment_0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==53) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalIdioms.g:3966:3: rule__Idiom__MixinAssignment_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__MixinAssignment_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getMixinAssignment_0());
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
    // $ANTLR end "rule__Idiom__Group__0__Impl"


    // $ANTLR start "rule__Idiom__Group__1"
    // InternalIdioms.g:3974:1: rule__Idiom__Group__1 : rule__Idiom__Group__1__Impl rule__Idiom__Group__2 ;
    public final void rule__Idiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3978:1: ( rule__Idiom__Group__1__Impl rule__Idiom__Group__2 )
            // InternalIdioms.g:3979:2: rule__Idiom__Group__1__Impl rule__Idiom__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__Idiom__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__2();

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
    // $ANTLR end "rule__Idiom__Group__1"


    // $ANTLR start "rule__Idiom__Group__1__Impl"
    // InternalIdioms.g:3986:1: rule__Idiom__Group__1__Impl : ( 'idiom' ) ;
    public final void rule__Idiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3990:1: ( ( 'idiom' ) )
            // InternalIdioms.g:3991:1: ( 'idiom' )
            {
            // InternalIdioms.g:3991:1: ( 'idiom' )
            // InternalIdioms.g:3992:2: 'idiom'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getIdiomKeyword_1());
            }
            match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getIdiomKeyword_1());
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
    // $ANTLR end "rule__Idiom__Group__1__Impl"


    // $ANTLR start "rule__Idiom__Group__2"
    // InternalIdioms.g:4001:1: rule__Idiom__Group__2 : rule__Idiom__Group__2__Impl rule__Idiom__Group__3 ;
    public final void rule__Idiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4005:1: ( rule__Idiom__Group__2__Impl rule__Idiom__Group__3 )
            // InternalIdioms.g:4006:2: rule__Idiom__Group__2__Impl rule__Idiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_33);
            rule__Idiom__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__3();

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
    // $ANTLR end "rule__Idiom__Group__2"


    // $ANTLR start "rule__Idiom__Group__2__Impl"
    // InternalIdioms.g:4013:1: rule__Idiom__Group__2__Impl : ( ( rule__Idiom__NameAssignment_2 ) ) ;
    public final void rule__Idiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4017:1: ( ( ( rule__Idiom__NameAssignment_2 ) ) )
            // InternalIdioms.g:4018:1: ( ( rule__Idiom__NameAssignment_2 ) )
            {
            // InternalIdioms.g:4018:1: ( ( rule__Idiom__NameAssignment_2 ) )
            // InternalIdioms.g:4019:2: ( rule__Idiom__NameAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getNameAssignment_2());
            }
            // InternalIdioms.g:4020:2: ( rule__Idiom__NameAssignment_2 )
            // InternalIdioms.g:4020:3: rule__Idiom__NameAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__NameAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getNameAssignment_2());
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
    // $ANTLR end "rule__Idiom__Group__2__Impl"


    // $ANTLR start "rule__Idiom__Group__3"
    // InternalIdioms.g:4028:1: rule__Idiom__Group__3 : rule__Idiom__Group__3__Impl rule__Idiom__Group__4 ;
    public final void rule__Idiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4032:1: ( rule__Idiom__Group__3__Impl rule__Idiom__Group__4 )
            // InternalIdioms.g:4033:2: rule__Idiom__Group__3__Impl rule__Idiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_33);
            rule__Idiom__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__4();

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
    // $ANTLR end "rule__Idiom__Group__3"


    // $ANTLR start "rule__Idiom__Group__3__Impl"
    // InternalIdioms.g:4040:1: rule__Idiom__Group__3__Impl : ( ( rule__Idiom__Group_3__0 )? ) ;
    public final void rule__Idiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4044:1: ( ( ( rule__Idiom__Group_3__0 )? ) )
            // InternalIdioms.g:4045:1: ( ( rule__Idiom__Group_3__0 )? )
            {
            // InternalIdioms.g:4045:1: ( ( rule__Idiom__Group_3__0 )? )
            // InternalIdioms.g:4046:2: ( rule__Idiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:4047:2: ( rule__Idiom__Group_3__0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==46) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalIdioms.g:4047:3: rule__Idiom__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getGroup_3());
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
    // $ANTLR end "rule__Idiom__Group__3__Impl"


    // $ANTLR start "rule__Idiom__Group__4"
    // InternalIdioms.g:4055:1: rule__Idiom__Group__4 : rule__Idiom__Group__4__Impl rule__Idiom__Group__5 ;
    public final void rule__Idiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4059:1: ( rule__Idiom__Group__4__Impl rule__Idiom__Group__5 )
            // InternalIdioms.g:4060:2: rule__Idiom__Group__4__Impl rule__Idiom__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_33);
            rule__Idiom__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__5();

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
    // $ANTLR end "rule__Idiom__Group__4"


    // $ANTLR start "rule__Idiom__Group__4__Impl"
    // InternalIdioms.g:4067:1: rule__Idiom__Group__4__Impl : ( ( rule__Idiom__Group_4__0 )? ) ;
    public final void rule__Idiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4071:1: ( ( ( rule__Idiom__Group_4__0 )? ) )
            // InternalIdioms.g:4072:1: ( ( rule__Idiom__Group_4__0 )? )
            {
            // InternalIdioms.g:4072:1: ( ( rule__Idiom__Group_4__0 )? )
            // InternalIdioms.g:4073:2: ( rule__Idiom__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_4());
            }
            // InternalIdioms.g:4074:2: ( rule__Idiom__Group_4__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==47) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalIdioms.g:4074:3: rule__Idiom__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__Group_4__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getGroup_4());
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
    // $ANTLR end "rule__Idiom__Group__4__Impl"


    // $ANTLR start "rule__Idiom__Group__5"
    // InternalIdioms.g:4082:1: rule__Idiom__Group__5 : rule__Idiom__Group__5__Impl ;
    public final void rule__Idiom__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4086:1: ( rule__Idiom__Group__5__Impl )
            // InternalIdioms.g:4087:2: rule__Idiom__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group__5__Impl();

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
    // $ANTLR end "rule__Idiom__Group__5"


    // $ANTLR start "rule__Idiom__Group__5__Impl"
    // InternalIdioms.g:4093:1: rule__Idiom__Group__5__Impl : ( ( rule__Idiom__Alternatives_5 ) ) ;
    public final void rule__Idiom__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4097:1: ( ( ( rule__Idiom__Alternatives_5 ) ) )
            // InternalIdioms.g:4098:1: ( ( rule__Idiom__Alternatives_5 ) )
            {
            // InternalIdioms.g:4098:1: ( ( rule__Idiom__Alternatives_5 ) )
            // InternalIdioms.g:4099:2: ( rule__Idiom__Alternatives_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getAlternatives_5());
            }
            // InternalIdioms.g:4100:2: ( rule__Idiom__Alternatives_5 )
            // InternalIdioms.g:4100:3: rule__Idiom__Alternatives_5
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Alternatives_5();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getAlternatives_5());
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
    // $ANTLR end "rule__Idiom__Group__5__Impl"


    // $ANTLR start "rule__Idiom__Group_3__0"
    // InternalIdioms.g:4109:1: rule__Idiom__Group_3__0 : rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 ;
    public final void rule__Idiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4113:1: ( rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 )
            // InternalIdioms.g:4114:2: rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__Idiom__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_3__1();

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
    // $ANTLR end "rule__Idiom__Group_3__0"


    // $ANTLR start "rule__Idiom__Group_3__0__Impl"
    // InternalIdioms.g:4121:1: rule__Idiom__Group_3__0__Impl : ( 'for' ) ;
    public final void rule__Idiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4125:1: ( ( 'for' ) )
            // InternalIdioms.g:4126:1: ( 'for' )
            {
            // InternalIdioms.g:4126:1: ( 'for' )
            // InternalIdioms.g:4127:2: 'for'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForKeyword_3_0());
            }
            match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForKeyword_3_0());
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
    // $ANTLR end "rule__Idiom__Group_3__0__Impl"


    // $ANTLR start "rule__Idiom__Group_3__1"
    // InternalIdioms.g:4136:1: rule__Idiom__Group_3__1 : rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 ;
    public final void rule__Idiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4140:1: ( rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 )
            // InternalIdioms.g:4141:2: rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__Idiom__Group_3__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_3__2();

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
    // $ANTLR end "rule__Idiom__Group_3__1"


    // $ANTLR start "rule__Idiom__Group_3__1__Impl"
    // InternalIdioms.g:4148:1: rule__Idiom__Group_3__1__Impl : ( ( rule__Idiom__Group_3_1__0 )? ) ;
    public final void rule__Idiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4152:1: ( ( ( rule__Idiom__Group_3_1__0 )? ) )
            // InternalIdioms.g:4153:1: ( ( rule__Idiom__Group_3_1__0 )? )
            {
            // InternalIdioms.g:4153:1: ( ( rule__Idiom__Group_3_1__0 )? )
            // InternalIdioms.g:4154:2: ( rule__Idiom__Group_3_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3_1());
            }
            // InternalIdioms.g:4155:2: ( rule__Idiom__Group_3_1__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_ID) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==23) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // InternalIdioms.g:4155:3: rule__Idiom__Group_3_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__Idiom__Group_3_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getGroup_3_1());
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
    // $ANTLR end "rule__Idiom__Group_3__1__Impl"


    // $ANTLR start "rule__Idiom__Group_3__2"
    // InternalIdioms.g:4163:1: rule__Idiom__Group_3__2 : rule__Idiom__Group_3__2__Impl ;
    public final void rule__Idiom__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4167:1: ( rule__Idiom__Group_3__2__Impl )
            // InternalIdioms.g:4168:2: rule__Idiom__Group_3__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_3__2__Impl();

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
    // $ANTLR end "rule__Idiom__Group_3__2"


    // $ANTLR start "rule__Idiom__Group_3__2__Impl"
    // InternalIdioms.g:4174:1: rule__Idiom__Group_3__2__Impl : ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) ;
    public final void rule__Idiom__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4178:1: ( ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) )
            // InternalIdioms.g:4179:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            {
            // InternalIdioms.g:4179:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            // InternalIdioms.g:4180:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2());
            }
            // InternalIdioms.g:4181:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            // InternalIdioms.g:4181:3: rule__Idiom__ForEClassAssignment_3_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__ForEClassAssignment_3_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2());
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
    // $ANTLR end "rule__Idiom__Group_3__2__Impl"


    // $ANTLR start "rule__Idiom__Group_3_1__0"
    // InternalIdioms.g:4190:1: rule__Idiom__Group_3_1__0 : rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 ;
    public final void rule__Idiom__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4194:1: ( rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 )
            // InternalIdioms.g:4195:2: rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__Idiom__Group_3_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_3_1__1();

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
    // $ANTLR end "rule__Idiom__Group_3_1__0"


    // $ANTLR start "rule__Idiom__Group_3_1__0__Impl"
    // InternalIdioms.g:4202:1: rule__Idiom__Group_3_1__0__Impl : ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) ;
    public final void rule__Idiom__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4206:1: ( ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) )
            // InternalIdioms.g:4207:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            {
            // InternalIdioms.g:4207:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            // InternalIdioms.g:4208:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0());
            }
            // InternalIdioms.g:4209:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            // InternalIdioms.g:4209:3: rule__Idiom__ForEPackageAssignment_3_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__ForEPackageAssignment_3_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0());
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
    // $ANTLR end "rule__Idiom__Group_3_1__0__Impl"


    // $ANTLR start "rule__Idiom__Group_3_1__1"
    // InternalIdioms.g:4217:1: rule__Idiom__Group_3_1__1 : rule__Idiom__Group_3_1__1__Impl ;
    public final void rule__Idiom__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4221:1: ( rule__Idiom__Group_3_1__1__Impl )
            // InternalIdioms.g:4222:2: rule__Idiom__Group_3_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_3_1__1__Impl();

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
    // $ANTLR end "rule__Idiom__Group_3_1__1"


    // $ANTLR start "rule__Idiom__Group_3_1__1__Impl"
    // InternalIdioms.g:4228:1: rule__Idiom__Group_3_1__1__Impl : ( '::' ) ;
    public final void rule__Idiom__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4232:1: ( ( '::' ) )
            // InternalIdioms.g:4233:1: ( '::' )
            {
            // InternalIdioms.g:4233:1: ( '::' )
            // InternalIdioms.g:4234:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());
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
    // $ANTLR end "rule__Idiom__Group_3_1__1__Impl"


    // $ANTLR start "rule__Idiom__Group_4__0"
    // InternalIdioms.g:4244:1: rule__Idiom__Group_4__0 : rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 ;
    public final void rule__Idiom__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4248:1: ( rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 )
            // InternalIdioms.g:4249:2: rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__Idiom__Group_4__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_4__1();

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
    // $ANTLR end "rule__Idiom__Group_4__0"


    // $ANTLR start "rule__Idiom__Group_4__0__Impl"
    // InternalIdioms.g:4256:1: rule__Idiom__Group_4__0__Impl : ( 'in' ) ;
    public final void rule__Idiom__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4260:1: ( ( 'in' ) )
            // InternalIdioms.g:4261:1: ( 'in' )
            {
            // InternalIdioms.g:4261:1: ( 'in' )
            // InternalIdioms.g:4262:2: 'in'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInKeyword_4_0());
            }
            match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getInKeyword_4_0());
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
    // $ANTLR end "rule__Idiom__Group_4__0__Impl"


    // $ANTLR start "rule__Idiom__Group_4__1"
    // InternalIdioms.g:4271:1: rule__Idiom__Group_4__1 : rule__Idiom__Group_4__1__Impl ;
    public final void rule__Idiom__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4275:1: ( rule__Idiom__Group_4__1__Impl )
            // InternalIdioms.g:4276:2: rule__Idiom__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_4__1__Impl();

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
    // $ANTLR end "rule__Idiom__Group_4__1"


    // $ANTLR start "rule__Idiom__Group_4__1__Impl"
    // InternalIdioms.g:4282:1: rule__Idiom__Group_4__1__Impl : ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) ;
    public final void rule__Idiom__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4286:1: ( ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) )
            // InternalIdioms.g:4287:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            {
            // InternalIdioms.g:4287:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            // InternalIdioms.g:4288:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1());
            }
            // InternalIdioms.g:4289:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            // InternalIdioms.g:4289:3: rule__Idiom__InRuleRegexAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__InRuleRegexAssignment_4_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1());
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
    // $ANTLR end "rule__Idiom__Group_4__1__Impl"


    // $ANTLR start "rule__Idiom__Group_5_1__0"
    // InternalIdioms.g:4298:1: rule__Idiom__Group_5_1__0 : rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 ;
    public final void rule__Idiom__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4302:1: ( rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 )
            // InternalIdioms.g:4303:2: rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1
            {
            pushFollow(FollowSets000.FOLLOW_34);
            rule__Idiom__Group_5_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_5_1__1();

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
    // $ANTLR end "rule__Idiom__Group_5_1__0"


    // $ANTLR start "rule__Idiom__Group_5_1__0__Impl"
    // InternalIdioms.g:4310:1: rule__Idiom__Group_5_1__0__Impl : ( '{' ) ;
    public final void rule__Idiom__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4314:1: ( ( '{' ) )
            // InternalIdioms.g:4315:1: ( '{' )
            {
            // InternalIdioms.g:4315:1: ( '{' )
            // InternalIdioms.g:4316:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());
            }
            match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());
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
    // $ANTLR end "rule__Idiom__Group_5_1__0__Impl"


    // $ANTLR start "rule__Idiom__Group_5_1__1"
    // InternalIdioms.g:4325:1: rule__Idiom__Group_5_1__1 : rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 ;
    public final void rule__Idiom__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4329:1: ( rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 )
            // InternalIdioms.g:4330:2: rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2
            {
            pushFollow(FollowSets000.FOLLOW_34);
            rule__Idiom__Group_5_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_5_1__2();

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
    // $ANTLR end "rule__Idiom__Group_5_1__1"


    // $ANTLR start "rule__Idiom__Group_5_1__1__Impl"
    // InternalIdioms.g:4337:1: rule__Idiom__Group_5_1__1__Impl : ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) ;
    public final void rule__Idiom__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4341:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) )
            // InternalIdioms.g:4342:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            {
            // InternalIdioms.g:4342:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            // InternalIdioms.g:4343:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1());
            }
            // InternalIdioms.g:4344:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==50) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalIdioms.g:4344:3: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_35);
            	    rule__Idiom__OwnedSubIdiomsAssignment_5_1_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1());
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
    // $ANTLR end "rule__Idiom__Group_5_1__1__Impl"


    // $ANTLR start "rule__Idiom__Group_5_1__2"
    // InternalIdioms.g:4352:1: rule__Idiom__Group_5_1__2 : rule__Idiom__Group_5_1__2__Impl ;
    public final void rule__Idiom__Group_5_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4356:1: ( rule__Idiom__Group_5_1__2__Impl )
            // InternalIdioms.g:4357:2: rule__Idiom__Group_5_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__Idiom__Group_5_1__2__Impl();

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
    // $ANTLR end "rule__Idiom__Group_5_1__2"


    // $ANTLR start "rule__Idiom__Group_5_1__2__Impl"
    // InternalIdioms.g:4363:1: rule__Idiom__Group_5_1__2__Impl : ( '}' ) ;
    public final void rule__Idiom__Group_5_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4367:1: ( ( '}' ) )
            // InternalIdioms.g:4368:1: ( '}' )
            {
            // InternalIdioms.g:4368:1: ( '}' )
            // InternalIdioms.g:4369:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());
            }
            match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());
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
    // $ANTLR end "rule__Idiom__Group_5_1__2__Impl"


    // $ANTLR start "rule__SubIdiom__Group__0"
    // InternalIdioms.g:4379:1: rule__SubIdiom__Group__0 : rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 ;
    public final void rule__SubIdiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4383:1: ( rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 )
            // InternalIdioms.g:4384:2: rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_36);
            rule__SubIdiom__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__1();

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
    // $ANTLR end "rule__SubIdiom__Group__0"


    // $ANTLR start "rule__SubIdiom__Group__0__Impl"
    // InternalIdioms.g:4391:1: rule__SubIdiom__Group__0__Impl : ( 'at' ) ;
    public final void rule__SubIdiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4395:1: ( ( 'at' ) )
            // InternalIdioms.g:4396:1: ( 'at' )
            {
            // InternalIdioms.g:4396:1: ( 'at' )
            // InternalIdioms.g:4397:2: 'at'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAtKeyword_0());
            }
            match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getAtKeyword_0());
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
    // $ANTLR end "rule__SubIdiom__Group__0__Impl"


    // $ANTLR start "rule__SubIdiom__Group__1"
    // InternalIdioms.g:4406:1: rule__SubIdiom__Group__1 : rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 ;
    public final void rule__SubIdiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4410:1: ( rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 )
            // InternalIdioms.g:4411:2: rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_36);
            rule__SubIdiom__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__2();

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
    // $ANTLR end "rule__SubIdiom__Group__1"


    // $ANTLR start "rule__SubIdiom__Group__1__Impl"
    // InternalIdioms.g:4418:1: rule__SubIdiom__Group__1__Impl : ( ( rule__SubIdiom__Alternatives_1 )? ) ;
    public final void rule__SubIdiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4422:1: ( ( ( rule__SubIdiom__Alternatives_1 )? ) )
            // InternalIdioms.g:4423:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            {
            // InternalIdioms.g:4423:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            // InternalIdioms.g:4424:2: ( rule__SubIdiom__Alternatives_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAlternatives_1());
            }
            // InternalIdioms.g:4425:2: ( rule__SubIdiom__Alternatives_1 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==11||LA29_0==54) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalIdioms.g:4425:3: rule__SubIdiom__Alternatives_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__SubIdiom__Alternatives_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getAlternatives_1());
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
    // $ANTLR end "rule__SubIdiom__Group__1__Impl"


    // $ANTLR start "rule__SubIdiom__Group__2"
    // InternalIdioms.g:4433:1: rule__SubIdiom__Group__2 : rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 ;
    public final void rule__SubIdiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4437:1: ( rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 )
            // InternalIdioms.g:4438:2: rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_37);
            rule__SubIdiom__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__3();

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
    // $ANTLR end "rule__SubIdiom__Group__2"


    // $ANTLR start "rule__SubIdiom__Group__2__Impl"
    // InternalIdioms.g:4445:1: rule__SubIdiom__Group__2__Impl : ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__SubIdiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4449:1: ( ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:4450:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:4450:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:4451:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:4452:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:4452:3: rule__SubIdiom__OwnedLocatorAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__OwnedLocatorAssignment_2();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2());
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
    // $ANTLR end "rule__SubIdiom__Group__2__Impl"


    // $ANTLR start "rule__SubIdiom__Group__3"
    // InternalIdioms.g:4460:1: rule__SubIdiom__Group__3 : rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 ;
    public final void rule__SubIdiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4464:1: ( rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 )
            // InternalIdioms.g:4465:2: rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_37);
            rule__SubIdiom__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__4();

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
    // $ANTLR end "rule__SubIdiom__Group__3"


    // $ANTLR start "rule__SubIdiom__Group__3__Impl"
    // InternalIdioms.g:4472:1: rule__SubIdiom__Group__3__Impl : ( ( rule__SubIdiom__Group_3__0 )? ) ;
    public final void rule__SubIdiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4476:1: ( ( ( rule__SubIdiom__Group_3__0 )? ) )
            // InternalIdioms.g:4477:1: ( ( rule__SubIdiom__Group_3__0 )? )
            {
            // InternalIdioms.g:4477:1: ( ( rule__SubIdiom__Group_3__0 )? )
            // InternalIdioms.g:4478:2: ( rule__SubIdiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:4479:2: ( rule__SubIdiom__Group_3__0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==51) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalIdioms.g:4479:3: rule__SubIdiom__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__SubIdiom__Group_3__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getGroup_3());
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
    // $ANTLR end "rule__SubIdiom__Group__3__Impl"


    // $ANTLR start "rule__SubIdiom__Group__4"
    // InternalIdioms.g:4487:1: rule__SubIdiom__Group__4 : rule__SubIdiom__Group__4__Impl ;
    public final void rule__SubIdiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4491:1: ( rule__SubIdiom__Group__4__Impl )
            // InternalIdioms.g:4492:2: rule__SubIdiom__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group__4__Impl();

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
    // $ANTLR end "rule__SubIdiom__Group__4"


    // $ANTLR start "rule__SubIdiom__Group__4__Impl"
    // InternalIdioms.g:4498:1: rule__SubIdiom__Group__4__Impl : ( ';' ) ;
    public final void rule__SubIdiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4502:1: ( ( ';' ) )
            // InternalIdioms.g:4503:1: ( ';' )
            {
            // InternalIdioms.g:4503:1: ( ';' )
            // InternalIdioms.g:4504:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4());
            }
            match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4());
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
    // $ANTLR end "rule__SubIdiom__Group__4__Impl"


    // $ANTLR start "rule__SubIdiom__Group_3__0"
    // InternalIdioms.g:4514:1: rule__SubIdiom__Group_3__0 : rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 ;
    public final void rule__SubIdiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4518:1: ( rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 )
            // InternalIdioms.g:4519:2: rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_38);
            rule__SubIdiom__Group_3__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group_3__1();

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
    // $ANTLR end "rule__SubIdiom__Group_3__0"


    // $ANTLR start "rule__SubIdiom__Group_3__0__Impl"
    // InternalIdioms.g:4526:1: rule__SubIdiom__Group_3__0__Impl : ( 'do' ) ;
    public final void rule__SubIdiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4530:1: ( ( 'do' ) )
            // InternalIdioms.g:4531:1: ( 'do' )
            {
            // InternalIdioms.g:4531:1: ( 'do' )
            // InternalIdioms.g:4532:2: 'do'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());
            }
            match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());
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
    // $ANTLR end "rule__SubIdiom__Group_3__0__Impl"


    // $ANTLR start "rule__SubIdiom__Group_3__1"
    // InternalIdioms.g:4541:1: rule__SubIdiom__Group_3__1 : rule__SubIdiom__Group_3__1__Impl ;
    public final void rule__SubIdiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4545:1: ( rule__SubIdiom__Group_3__1__Impl )
            // InternalIdioms.g:4546:2: rule__SubIdiom__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__Group_3__1__Impl();

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
    // $ANTLR end "rule__SubIdiom__Group_3__1"


    // $ANTLR start "rule__SubIdiom__Group_3__1__Impl"
    // InternalIdioms.g:4552:1: rule__SubIdiom__Group_3__1__Impl : ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) ;
    public final void rule__SubIdiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4556:1: ( ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) )
            // InternalIdioms.g:4557:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            {
            // InternalIdioms.g:4557:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            // InternalIdioms.g:4558:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1());
            }
            // InternalIdioms.g:4559:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID||(LA31_0>=28 && LA31_0<=44)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalIdioms.g:4559:3: rule__SubIdiom__OwnedSegmentsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_39);
            	    rule__SubIdiom__OwnedSegmentsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1());
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
    // $ANTLR end "rule__SubIdiom__Group_3__1__Impl"


    // $ANTLR start "rule__IdiomsModel__NamesAssignment_1"
    // InternalIdioms.g:4568:1: rule__IdiomsModel__NamesAssignment_1 : ( RULE_ID ) ;
    public final void rule__IdiomsModel__NamesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4572:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4573:2: ( RULE_ID )
            {
            // InternalIdioms.g:4573:2: ( RULE_ID )
            // InternalIdioms.g:4574:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0());
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
    // $ANTLR end "rule__IdiomsModel__NamesAssignment_1"


    // $ANTLR start "rule__IdiomsModel__NamesAssignment_2_1"
    // InternalIdioms.g:4583:1: rule__IdiomsModel__NamesAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__IdiomsModel__NamesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4587:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4588:2: ( RULE_ID )
            {
            // InternalIdioms.g:4588:2: ( RULE_ID )
            // InternalIdioms.g:4589:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0());
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
    // $ANTLR end "rule__IdiomsModel__NamesAssignment_2_1"


    // $ANTLR start "rule__IdiomsModel__OwnedWithsAssignment_3_0"
    // InternalIdioms.g:4598:1: rule__IdiomsModel__OwnedWithsAssignment_3_0 : ( ruleIdiomsImport ) ;
    public final void rule__IdiomsModel__OwnedWithsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4602:1: ( ( ruleIdiomsImport ) )
            // InternalIdioms.g:4603:2: ( ruleIdiomsImport )
            {
            // InternalIdioms.g:4603:2: ( ruleIdiomsImport )
            // InternalIdioms.g:4604:3: ruleIdiomsImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdiomsImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedWithsAssignment_3_0"


    // $ANTLR start "rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1"
    // InternalIdioms.g:4613:1: rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1 : ( ruleEPackageDeclaration ) ;
    public final void rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4617:1: ( ( ruleEPackageDeclaration ) )
            // InternalIdioms.g:4618:2: ( ruleEPackageDeclaration )
            {
            // InternalIdioms.g:4618:2: ( ruleEPackageDeclaration )
            // InternalIdioms.g:4619:3: ruleEPackageDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedImportDeclarationsEPackageDeclarationParserRuleCall_3_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleEPackageDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedImportDeclarationsEPackageDeclarationParserRuleCall_3_1_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedImportDeclarationsAssignment_3_1"


    // $ANTLR start "rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2"
    // InternalIdioms.g:4628:1: rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2 : ( ruleGrammarDeclaration ) ;
    public final void rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4632:1: ( ( ruleGrammarDeclaration ) )
            // InternalIdioms.g:4633:2: ( ruleGrammarDeclaration )
            {
            // InternalIdioms.g:4633:2: ( ruleGrammarDeclaration )
            // InternalIdioms.g:4634:3: ruleGrammarDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedGrammarDeclarationsGrammarDeclarationParserRuleCall_3_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleGrammarDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedGrammarDeclarationsGrammarDeclarationParserRuleCall_3_2_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedGrammarDeclarationsAssignment_3_2"


    // $ANTLR start "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0"
    // InternalIdioms.g:4643:1: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 : ( ruleLocatorDeclaration ) ;
    public final void rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4647:1: ( ( ruleLocatorDeclaration ) )
            // InternalIdioms.g:4648:2: ( ruleLocatorDeclaration )
            {
            // InternalIdioms.g:4648:2: ( ruleLocatorDeclaration )
            // InternalIdioms.g:4649:3: ruleLocatorDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_4_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleLocatorDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_4_0_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0"


    // $ANTLR start "rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1"
    // InternalIdioms.g:4658:1: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 : ( ruleSegmentDeclaration ) ;
    public final void rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4662:1: ( ( ruleSegmentDeclaration ) )
            // InternalIdioms.g:4663:2: ( ruleSegmentDeclaration )
            {
            // InternalIdioms.g:4663:2: ( ruleSegmentDeclaration )
            // InternalIdioms.g:4664:3: ruleSegmentDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_4_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleSegmentDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_4_1_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1"


    // $ANTLR start "rule__IdiomsModel__OwnedIdiomsAssignment_4_2"
    // InternalIdioms.g:4673:1: rule__IdiomsModel__OwnedIdiomsAssignment_4_2 : ( ruleIdiom ) ;
    public final void rule__IdiomsModel__OwnedIdiomsAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4677:1: ( ( ruleIdiom ) )
            // InternalIdioms.g:4678:2: ( ruleIdiom )
            {
            // InternalIdioms.g:4678:2: ( ruleIdiom )
            // InternalIdioms.g:4679:3: ruleIdiom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_4_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_4_2_0());
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
    // $ANTLR end "rule__IdiomsModel__OwnedIdiomsAssignment_4_2"


    // $ANTLR start "rule__EPackageDeclaration__EPackageAssignment_1"
    // InternalIdioms.g:4688:1: rule__EPackageDeclaration__EPackageAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__EPackageDeclaration__EPackageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4692:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4693:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4693:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4694:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getEPackageEPackageCrossReference_1_0());
            }
            // InternalIdioms.g:4695:3: ( RULE_STRING )
            // InternalIdioms.g:4696:4: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getEPackageEPackageCrossReference_1_0());
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
    // $ANTLR end "rule__EPackageDeclaration__EPackageAssignment_1"


    // $ANTLR start "rule__EPackageDeclaration__AsAssignment_2_1"
    // InternalIdioms.g:4707:1: rule__EPackageDeclaration__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__EPackageDeclaration__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4711:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4712:2: ( RULE_ID )
            {
            // InternalIdioms.g:4712:2: ( RULE_ID )
            // InternalIdioms.g:4713:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());
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
    // $ANTLR end "rule__EPackageDeclaration__AsAssignment_2_1"


    // $ANTLR start "rule__GrammarDeclaration__GrammarAssignment_1"
    // InternalIdioms.g:4722:1: rule__GrammarDeclaration__GrammarAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__GrammarDeclaration__GrammarAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4726:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4727:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4727:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4728:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGrammarGrammarCrossReference_1_0());
            }
            // InternalIdioms.g:4729:3: ( RULE_STRING )
            // InternalIdioms.g:4730:4: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getGrammarGrammarSTRINGTerminalRuleCall_1_0_1());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGrammarGrammarSTRINGTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getGrammarGrammarCrossReference_1_0());
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
    // $ANTLR end "rule__GrammarDeclaration__GrammarAssignment_1"


    // $ANTLR start "rule__GrammarDeclaration__AsAssignment_2_1"
    // InternalIdioms.g:4741:1: rule__GrammarDeclaration__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__GrammarDeclaration__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4745:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4746:2: ( RULE_ID )
            {
            // InternalIdioms.g:4746:2: ( RULE_ID )
            // InternalIdioms.g:4747:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getGrammarDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getGrammarDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());
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
    // $ANTLR end "rule__GrammarDeclaration__AsAssignment_2_1"


    // $ANTLR start "rule__IdiomsImport__IdiomsModelAssignment_1"
    // InternalIdioms.g:4756:1: rule__IdiomsImport__IdiomsModelAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__IdiomsImport__IdiomsModelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4760:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4761:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4761:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4762:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());
            }
            // InternalIdioms.g:4763:3: ( RULE_STRING )
            // InternalIdioms.g:4764:4: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelSTRINGTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());
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
    // $ANTLR end "rule__IdiomsImport__IdiomsModelAssignment_1"


    // $ANTLR start "rule__IdiomsImport__AsAssignment_2_1"
    // InternalIdioms.g:4775:1: rule__IdiomsImport__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__IdiomsImport__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4779:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4780:2: ( RULE_ID )
            {
            // InternalIdioms.g:4780:2: ( RULE_ID )
            // InternalIdioms.g:4781:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0());
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
    // $ANTLR end "rule__IdiomsImport__AsAssignment_2_1"


    // $ANTLR start "rule__LocatorDeclaration__NameAssignment_1"
    // InternalIdioms.g:4790:1: rule__LocatorDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__LocatorDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4794:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4795:2: ( RULE_ID )
            {
            // InternalIdioms.g:4795:2: ( RULE_ID )
            // InternalIdioms.g:4796:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0());
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
    // $ANTLR end "rule__LocatorDeclaration__NameAssignment_1"


    // $ANTLR start "rule__LocatorDeclaration__OwnedLocatorAssignment_2"
    // InternalIdioms.g:4805:1: rule__LocatorDeclaration__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__LocatorDeclaration__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4809:1: ( ( ruleLocator ) )
            // InternalIdioms.g:4810:2: ( ruleLocator )
            {
            // InternalIdioms.g:4810:2: ( ruleLocator )
            // InternalIdioms.g:4811:3: ruleLocator
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
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
    // $ANTLR end "rule__LocatorDeclaration__OwnedLocatorAssignment_2"


    // $ANTLR start "rule__AssignmentLocator__EPackageAssignment_1_0_0"
    // InternalIdioms.g:4820:1: rule__AssignmentLocator__EPackageAssignment_1_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EPackageAssignment_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4824:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4825:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4825:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4826:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());
            }
            // InternalIdioms.g:4827:3: ( RULE_ID )
            // InternalIdioms.g:4828:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());
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
    // $ANTLR end "rule__AssignmentLocator__EPackageAssignment_1_0_0"


    // $ANTLR start "rule__AssignmentLocator__EClassAssignment_1_1"
    // InternalIdioms.g:4839:1: rule__AssignmentLocator__EClassAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EClassAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4843:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4844:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4844:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4845:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());
            }
            // InternalIdioms.g:4846:3: ( RULE_ID )
            // InternalIdioms.g:4847:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassIDTerminalRuleCall_1_1_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEClassEClassIDTerminalRuleCall_1_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());
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
    // $ANTLR end "rule__AssignmentLocator__EClassAssignment_1_1"


    // $ANTLR start "rule__AssignmentLocator__EStructuralFeatureAssignment_2"
    // InternalIdioms.g:4858:1: rule__AssignmentLocator__EStructuralFeatureAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EStructuralFeatureAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4862:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4863:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4863:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4864:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());
            }
            // InternalIdioms.g:4865:3: ( RULE_ID )
            // InternalIdioms.g:4866:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureIDTerminalRuleCall_2_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());
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
    // $ANTLR end "rule__AssignmentLocator__EStructuralFeatureAssignment_2"


    // $ANTLR start "rule__KeywordLocator__StringAssignment"
    // InternalIdioms.g:4877:1: rule__KeywordLocator__StringAssignment : ( RULE_STRING ) ;
    public final void rule__KeywordLocator__StringAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4881:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4882:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4882:2: ( RULE_STRING )
            // InternalIdioms.g:4883:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0());
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
    // $ANTLR end "rule__KeywordLocator__StringAssignment"


    // $ANTLR start "rule__ReturnsLocator__EPackageAssignment_1_0"
    // InternalIdioms.g:4892:1: rule__ReturnsLocator__EPackageAssignment_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EPackageAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4896:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4897:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4897:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4898:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());
            }
            // InternalIdioms.g:4899:3: ( RULE_ID )
            // InternalIdioms.g:4900:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageIDTerminalRuleCall_1_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());
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
    // $ANTLR end "rule__ReturnsLocator__EPackageAssignment_1_0"


    // $ANTLR start "rule__ReturnsLocator__EClassAssignment_2"
    // InternalIdioms.g:4911:1: rule__ReturnsLocator__EClassAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EClassAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4915:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4916:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4916:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4917:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());
            }
            // InternalIdioms.g:4918:3: ( RULE_ID )
            // InternalIdioms.g:4919:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassEClassIDTerminalRuleCall_2_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEClassEClassIDTerminalRuleCall_2_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());
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
    // $ANTLR end "rule__ReturnsLocator__EClassAssignment_2"


    // $ANTLR start "rule__ReferredLocator__IdiomsModelAssignment_0_0"
    // InternalIdioms.g:4930:1: rule__ReferredLocator__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4934:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4935:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4935:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4936:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:4937:3: ( RULE_ID )
            // InternalIdioms.g:4938:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
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
    // $ANTLR end "rule__ReferredLocator__IdiomsModelAssignment_0_0"


    // $ANTLR start "rule__ReferredLocator__LocatorDeclarationAssignment_1"
    // InternalIdioms.g:4949:1: rule__ReferredLocator__LocatorDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__LocatorDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4953:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4954:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4954:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4955:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:4956:3: ( RULE_ID )
            // InternalIdioms.g:4957:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationIDTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());
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
    // $ANTLR end "rule__ReferredLocator__LocatorDeclarationAssignment_1"


    // $ANTLR start "rule__RuleLocator__ReferredGrammarAssignment_1_0"
    // InternalIdioms.g:4968:1: rule__RuleLocator__ReferredGrammarAssignment_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__RuleLocator__ReferredGrammarAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4972:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4973:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4973:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4974:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredGrammarGrammarDeclarationCrossReference_1_0_0());
            }
            // InternalIdioms.g:4975:3: ( RULE_ID )
            // InternalIdioms.g:4976:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredGrammarGrammarDeclarationIDTerminalRuleCall_1_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredGrammarGrammarDeclarationIDTerminalRuleCall_1_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredGrammarGrammarDeclarationCrossReference_1_0_0());
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
    // $ANTLR end "rule__RuleLocator__ReferredGrammarAssignment_1_0"


    // $ANTLR start "rule__RuleLocator__ReferredRuleAssignment_2"
    // InternalIdioms.g:4987:1: rule__RuleLocator__ReferredRuleAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__RuleLocator__ReferredRuleAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4991:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4992:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4992:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4993:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredRuleAbstractRuleCrossReference_2_0());
            }
            // InternalIdioms.g:4994:3: ( RULE_ID )
            // InternalIdioms.g:4995:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getRuleLocatorAccess().getReferredRuleAbstractRuleIDTerminalRuleCall_2_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredRuleAbstractRuleIDTerminalRuleCall_2_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getRuleLocatorAccess().getReferredRuleAbstractRuleCrossReference_2_0());
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
    // $ANTLR end "rule__RuleLocator__ReferredRuleAssignment_2"


    // $ANTLR start "rule__SegmentDeclaration__NameAssignment_1"
    // InternalIdioms.g:5006:1: rule__SegmentDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__SegmentDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5010:1: ( ( RULE_ID ) )
            // InternalIdioms.g:5011:2: ( RULE_ID )
            {
            // InternalIdioms.g:5011:2: ( RULE_ID )
            // InternalIdioms.g:5012:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0());
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
    // $ANTLR end "rule__SegmentDeclaration__NameAssignment_1"


    // $ANTLR start "rule__SegmentDeclaration__OwnedSegmentAssignment_2"
    // InternalIdioms.g:5021:1: rule__SegmentDeclaration__OwnedSegmentAssignment_2 : ( ruleSegment ) ;
    public final void rule__SegmentDeclaration__OwnedSegmentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5025:1: ( ( ruleSegment ) )
            // InternalIdioms.g:5026:2: ( ruleSegment )
            {
            // InternalIdioms.g:5026:2: ( ruleSegment )
            // InternalIdioms.g:5027:3: ruleSegment
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleSegment();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0());
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
    // $ANTLR end "rule__SegmentDeclaration__OwnedSegmentAssignment_2"


    // $ANTLR start "rule__CustomSegment__SupportClassNameAssignment_1"
    // InternalIdioms.g:5036:1: rule__CustomSegment__SupportClassNameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__CustomSegment__SupportClassNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5040:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:5041:2: ( RULE_STRING )
            {
            // InternalIdioms.g:5041:2: ( RULE_STRING )
            // InternalIdioms.g:5042:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0());
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
    // $ANTLR end "rule__CustomSegment__SupportClassNameAssignment_1"


    // $ANTLR start "rule__StringSegment__StringAssignment_1"
    // InternalIdioms.g:5051:1: rule__StringSegment__StringAssignment_1 : ( RULE_STRING ) ;
    public final void rule__StringSegment__StringAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5055:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:5056:2: ( RULE_STRING )
            {
            // InternalIdioms.g:5056:2: ( RULE_STRING )
            // InternalIdioms.g:5057:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0());
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
    // $ANTLR end "rule__StringSegment__StringAssignment_1"


    // $ANTLR start "rule__StringSegment__PrintableAssignment_2"
    // InternalIdioms.g:5066:1: rule__StringSegment__PrintableAssignment_2 : ( ( 'printable' ) ) ;
    public final void rule__StringSegment__PrintableAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5070:1: ( ( ( 'printable' ) ) )
            // InternalIdioms.g:5071:2: ( ( 'printable' ) )
            {
            // InternalIdioms.g:5071:2: ( ( 'printable' ) )
            // InternalIdioms.g:5072:3: ( 'printable' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }
            // InternalIdioms.g:5073:3: ( 'printable' )
            // InternalIdioms.g:5074:4: 'printable'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }
            match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
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
    // $ANTLR end "rule__StringSegment__PrintableAssignment_2"


    // $ANTLR start "rule__ReferredSegment__IdiomsModelAssignment_0_0"
    // InternalIdioms.g:5085:1: rule__ReferredSegment__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5089:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:5090:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:5090:2: ( ( RULE_ID ) )
            // InternalIdioms.g:5091:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:5092:3: ( RULE_ID )
            // InternalIdioms.g:5093:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelIDTerminalRuleCall_0_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
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
    // $ANTLR end "rule__ReferredSegment__IdiomsModelAssignment_0_0"


    // $ANTLR start "rule__ReferredSegment__SegmentDeclarationAssignment_1"
    // InternalIdioms.g:5104:1: rule__ReferredSegment__SegmentDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__SegmentDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5108:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:5109:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:5109:2: ( ( RULE_ID ) )
            // InternalIdioms.g:5110:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:5111:3: ( RULE_ID )
            // InternalIdioms.g:5112:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationIDTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());
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
    // $ANTLR end "rule__ReferredSegment__SegmentDeclarationAssignment_1"


    // $ANTLR start "rule__Idiom__MixinAssignment_0"
    // InternalIdioms.g:5123:1: rule__Idiom__MixinAssignment_0 : ( ( 'mixin' ) ) ;
    public final void rule__Idiom__MixinAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5127:1: ( ( ( 'mixin' ) ) )
            // InternalIdioms.g:5128:2: ( ( 'mixin' ) )
            {
            // InternalIdioms.g:5128:2: ( ( 'mixin' ) )
            // InternalIdioms.g:5129:3: ( 'mixin' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }
            // InternalIdioms.g:5130:3: ( 'mixin' )
            // InternalIdioms.g:5131:4: 'mixin'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }
            match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
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
    // $ANTLR end "rule__Idiom__MixinAssignment_0"


    // $ANTLR start "rule__Idiom__NameAssignment_2"
    // InternalIdioms.g:5142:1: rule__Idiom__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Idiom__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5146:1: ( ( RULE_ID ) )
            // InternalIdioms.g:5147:2: ( RULE_ID )
            {
            // InternalIdioms.g:5147:2: ( RULE_ID )
            // InternalIdioms.g:5148:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0());
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
    // $ANTLR end "rule__Idiom__NameAssignment_2"


    // $ANTLR start "rule__Idiom__ForEPackageAssignment_3_1_0"
    // InternalIdioms.g:5157:1: rule__Idiom__ForEPackageAssignment_3_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEPackageAssignment_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5161:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:5162:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:5162:2: ( ( RULE_ID ) )
            // InternalIdioms.g:5163:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());
            }
            // InternalIdioms.g:5164:3: ( RULE_ID )
            // InternalIdioms.g:5165:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEPackageEPackageIDTerminalRuleCall_3_1_0_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());
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
    // $ANTLR end "rule__Idiom__ForEPackageAssignment_3_1_0"


    // $ANTLR start "rule__Idiom__ForEClassAssignment_3_2"
    // InternalIdioms.g:5176:1: rule__Idiom__ForEClassAssignment_3_2 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEClassAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5180:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:5181:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:5181:2: ( ( RULE_ID ) )
            // InternalIdioms.g:5182:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());
            }
            // InternalIdioms.g:5183:3: ( RULE_ID )
            // InternalIdioms.g:5184:4: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassEClassIDTerminalRuleCall_3_2_0_1());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEClassEClassIDTerminalRuleCall_3_2_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());
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
    // $ANTLR end "rule__Idiom__ForEClassAssignment_3_2"


    // $ANTLR start "rule__Idiom__InRuleRegexAssignment_4_1"
    // InternalIdioms.g:5195:1: rule__Idiom__InRuleRegexAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__Idiom__InRuleRegexAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5199:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:5200:2: ( RULE_STRING )
            {
            // InternalIdioms.g:5200:2: ( RULE_STRING )
            // InternalIdioms.g:5201:3: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0());
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
    // $ANTLR end "rule__Idiom__InRuleRegexAssignment_4_1"


    // $ANTLR start "rule__Idiom__OwnedSubIdiomsAssignment_5_0"
    // InternalIdioms.g:5210:1: rule__Idiom__OwnedSubIdiomsAssignment_5_0 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5214:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:5215:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:5215:2: ( ruleSubIdiom )
            // InternalIdioms.g:5216:3: ruleSubIdiom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleSubIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0());
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
    // $ANTLR end "rule__Idiom__OwnedSubIdiomsAssignment_5_0"


    // $ANTLR start "rule__Idiom__OwnedSubIdiomsAssignment_5_1_1"
    // InternalIdioms.g:5225:1: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5229:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:5230:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:5230:2: ( ruleSubIdiom )
            // InternalIdioms.g:5231:3: ruleSubIdiom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleSubIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0());
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
    // $ANTLR end "rule__Idiom__OwnedSubIdiomsAssignment_5_1_1"


    // $ANTLR start "rule__SubIdiom__AllAssignment_1_0"
    // InternalIdioms.g:5240:1: rule__SubIdiom__AllAssignment_1_0 : ( ( 'all' ) ) ;
    public final void rule__SubIdiom__AllAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5244:1: ( ( ( 'all' ) ) )
            // InternalIdioms.g:5245:2: ( ( 'all' ) )
            {
            // InternalIdioms.g:5245:2: ( ( 'all' ) )
            // InternalIdioms.g:5246:3: ( 'all' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }
            // InternalIdioms.g:5247:3: ( 'all' )
            // InternalIdioms.g:5248:4: 'all'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }
            match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
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
    // $ANTLR end "rule__SubIdiom__AllAssignment_1_0"


    // $ANTLR start "rule__SubIdiom__OwnedLocatorAssignment_2"
    // InternalIdioms.g:5259:1: rule__SubIdiom__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__SubIdiom__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5263:1: ( ( ruleLocator ) )
            // InternalIdioms.g:5264:2: ( ruleLocator )
            {
            // InternalIdioms.g:5264:2: ( ruleLocator )
            // InternalIdioms.g:5265:3: ruleLocator
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0());
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
    // $ANTLR end "rule__SubIdiom__OwnedLocatorAssignment_2"


    // $ANTLR start "rule__SubIdiom__OwnedSegmentsAssignment_3_1"
    // InternalIdioms.g:5274:1: rule__SubIdiom__OwnedSegmentsAssignment_3_1 : ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) ;
    public final void rule__SubIdiom__OwnedSegmentsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5278:1: ( ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) )
            // InternalIdioms.g:5279:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            {
            // InternalIdioms.g:5279:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            // InternalIdioms.g:5280:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0());
            }
            // InternalIdioms.g:5281:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            // InternalIdioms.g:5281:4: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0());
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
    // $ANTLR end "rule__SubIdiom__OwnedSegmentsAssignment_3_1"

    // Delegated rules





    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00202000080E6000L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000064002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0020200008080002L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000007700030L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00001FFFF0000000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0010000000000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0020200008080000L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0005C00000000000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0006000000000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0004000000000002L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0040000007700830L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0008000000008000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00001FFFF0000010L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x00001FFFF0000012L});
    }


}