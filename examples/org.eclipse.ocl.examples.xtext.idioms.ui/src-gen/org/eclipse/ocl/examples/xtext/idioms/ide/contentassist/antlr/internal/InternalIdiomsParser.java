package org.eclipse.ocl.examples.xtext.idioms.ide.contentassist.antlr.internal;

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
import org.eclipse.ocl.examples.xtext.idioms.services.IdiomsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
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
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalIdiomsParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'each'", "'model'", "'import'", "';'", "'as'", "'with'", "'locator'", "'any-assignment'", "'any-element'", "'assignment'", "'::'", "'{'", "'}'", "'|'", "'final'", "'returns'", "'segment'", "'custom'", "'half-new-line'", "'new-line'", "'no-space'", "'pop'", "'post-comment'", "'pre-comment'", "'push'", "'soft-new-line'", "'soft-space'", "'string'", "'value'", "'wrap-anchor'", "'wrap-begin-all'", "'wrap-begin-some'", "'wrap-end'", "'wrap-here'", "'idiom'", "'for'", "'in'", "'at'", "'do'", "'printable'", "'mixin'", "'all'"
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


    // $ANTLR start "entryRuleEPackageImport"
    // InternalIdioms.g:88:1: entryRuleEPackageImport : ruleEPackageImport EOF ;
    public final void entryRuleEPackageImport() throws RecognitionException {
        try {
            // InternalIdioms.g:89:1: ( ruleEPackageImport EOF )
            // InternalIdioms.g:90:1: ruleEPackageImport EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleEPackageImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportRule());
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
    // $ANTLR end "entryRuleEPackageImport"


    // $ANTLR start "ruleEPackageImport"
    // InternalIdioms.g:97:1: ruleEPackageImport : ( ( rule__EPackageImport__Group__0 ) ) ;
    public final void ruleEPackageImport() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:101:2: ( ( ( rule__EPackageImport__Group__0 ) ) )
            // InternalIdioms.g:102:2: ( ( rule__EPackageImport__Group__0 ) )
            {
            // InternalIdioms.g:102:2: ( ( rule__EPackageImport__Group__0 ) )
            // InternalIdioms.g:103:3: ( rule__EPackageImport__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getGroup());
            }
            // InternalIdioms.g:104:3: ( rule__EPackageImport__Group__0 )
            // InternalIdioms.g:104:4: rule__EPackageImport__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEPackageImport"


    // $ANTLR start "entryRuleIdiomsImport"
    // InternalIdioms.g:113:1: entryRuleIdiomsImport : ruleIdiomsImport EOF ;
    public final void entryRuleIdiomsImport() throws RecognitionException {
        try {
            // InternalIdioms.g:114:1: ( ruleIdiomsImport EOF )
            // InternalIdioms.g:115:1: ruleIdiomsImport EOF
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
    // InternalIdioms.g:122:1: ruleIdiomsImport : ( ( rule__IdiomsImport__Group__0 ) ) ;
    public final void ruleIdiomsImport() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:126:2: ( ( ( rule__IdiomsImport__Group__0 ) ) )
            // InternalIdioms.g:127:2: ( ( rule__IdiomsImport__Group__0 ) )
            {
            // InternalIdioms.g:127:2: ( ( rule__IdiomsImport__Group__0 ) )
            // InternalIdioms.g:128:3: ( rule__IdiomsImport__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getGroup());
            }
            // InternalIdioms.g:129:3: ( rule__IdiomsImport__Group__0 )
            // InternalIdioms.g:129:4: rule__IdiomsImport__Group__0
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
    // InternalIdioms.g:138:1: entryRuleLocatorDeclaration : ruleLocatorDeclaration EOF ;
    public final void entryRuleLocatorDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:139:1: ( ruleLocatorDeclaration EOF )
            // InternalIdioms.g:140:1: ruleLocatorDeclaration EOF
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
    // InternalIdioms.g:147:1: ruleLocatorDeclaration : ( ( rule__LocatorDeclaration__Group__0 ) ) ;
    public final void ruleLocatorDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:151:2: ( ( ( rule__LocatorDeclaration__Group__0 ) ) )
            // InternalIdioms.g:152:2: ( ( rule__LocatorDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:152:2: ( ( rule__LocatorDeclaration__Group__0 ) )
            // InternalIdioms.g:153:3: ( rule__LocatorDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:154:3: ( rule__LocatorDeclaration__Group__0 )
            // InternalIdioms.g:154:4: rule__LocatorDeclaration__Group__0
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


    // $ANTLR start "entryRuleElementLocator"
    // InternalIdioms.g:163:1: entryRuleElementLocator : ruleElementLocator EOF ;
    public final void entryRuleElementLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:164:1: ( ruleElementLocator EOF )
            // InternalIdioms.g:165:1: ruleElementLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getElementLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleElementLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getElementLocatorRule());
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
    // $ANTLR end "entryRuleElementLocator"


    // $ANTLR start "ruleElementLocator"
    // InternalIdioms.g:172:1: ruleElementLocator : ( ( rule__ElementLocator__Alternatives ) ) ;
    public final void ruleElementLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:176:2: ( ( ( rule__ElementLocator__Alternatives ) ) )
            // InternalIdioms.g:177:2: ( ( rule__ElementLocator__Alternatives ) )
            {
            // InternalIdioms.g:177:2: ( ( rule__ElementLocator__Alternatives ) )
            // InternalIdioms.g:178:3: ( rule__ElementLocator__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getElementLocatorAccess().getAlternatives());
            }
            // InternalIdioms.g:179:3: ( rule__ElementLocator__Alternatives )
            // InternalIdioms.g:179:4: rule__ElementLocator__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ElementLocator__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getElementLocatorAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleElementLocator"


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


    // $ANTLR start "entryRuleCompoundLocator"
    // InternalIdioms.g:288:1: entryRuleCompoundLocator : ruleCompoundLocator EOF ;
    public final void entryRuleCompoundLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:289:1: ( ruleCompoundLocator EOF )
            // InternalIdioms.g:290:1: ruleCompoundLocator EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleCompoundLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorRule());
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
    // $ANTLR end "entryRuleCompoundLocator"


    // $ANTLR start "ruleCompoundLocator"
    // InternalIdioms.g:297:1: ruleCompoundLocator : ( ( rule__CompoundLocator__Group__0 ) ) ;
    public final void ruleCompoundLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:301:2: ( ( ( rule__CompoundLocator__Group__0 ) ) )
            // InternalIdioms.g:302:2: ( ( rule__CompoundLocator__Group__0 ) )
            {
            // InternalIdioms.g:302:2: ( ( rule__CompoundLocator__Group__0 ) )
            // InternalIdioms.g:303:3: ( rule__CompoundLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getGroup());
            }
            // InternalIdioms.g:304:3: ( rule__CompoundLocator__Group__0 )
            // InternalIdioms.g:304:4: rule__CompoundLocator__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCompoundLocator"


    // $ANTLR start "entryRuleFinalLocator"
    // InternalIdioms.g:313:1: entryRuleFinalLocator : ruleFinalLocator EOF ;
    public final void entryRuleFinalLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:314:1: ( ruleFinalLocator EOF )
            // InternalIdioms.g:315:1: ruleFinalLocator EOF
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
    // InternalIdioms.g:322:1: ruleFinalLocator : ( ( rule__FinalLocator__Group__0 ) ) ;
    public final void ruleFinalLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:326:2: ( ( ( rule__FinalLocator__Group__0 ) ) )
            // InternalIdioms.g:327:2: ( ( rule__FinalLocator__Group__0 ) )
            {
            // InternalIdioms.g:327:2: ( ( rule__FinalLocator__Group__0 ) )
            // InternalIdioms.g:328:3: ( rule__FinalLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getGroup());
            }
            // InternalIdioms.g:329:3: ( rule__FinalLocator__Group__0 )
            // InternalIdioms.g:329:4: rule__FinalLocator__Group__0
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
    // InternalIdioms.g:338:1: entryRuleKeywordLocator : ruleKeywordLocator EOF ;
    public final void entryRuleKeywordLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:339:1: ( ruleKeywordLocator EOF )
            // InternalIdioms.g:340:1: ruleKeywordLocator EOF
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
    // InternalIdioms.g:347:1: ruleKeywordLocator : ( ( rule__KeywordLocator__StringAssignment ) ) ;
    public final void ruleKeywordLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:351:2: ( ( ( rule__KeywordLocator__StringAssignment ) ) )
            // InternalIdioms.g:352:2: ( ( rule__KeywordLocator__StringAssignment ) )
            {
            // InternalIdioms.g:352:2: ( ( rule__KeywordLocator__StringAssignment ) )
            // InternalIdioms.g:353:3: ( rule__KeywordLocator__StringAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeywordLocatorAccess().getStringAssignment());
            }
            // InternalIdioms.g:354:3: ( rule__KeywordLocator__StringAssignment )
            // InternalIdioms.g:354:4: rule__KeywordLocator__StringAssignment
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
    // InternalIdioms.g:363:1: entryRuleReturnsLocator : ruleReturnsLocator EOF ;
    public final void entryRuleReturnsLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:364:1: ( ruleReturnsLocator EOF )
            // InternalIdioms.g:365:1: ruleReturnsLocator EOF
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
    // InternalIdioms.g:372:1: ruleReturnsLocator : ( ( rule__ReturnsLocator__Group__0 ) ) ;
    public final void ruleReturnsLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:376:2: ( ( ( rule__ReturnsLocator__Group__0 ) ) )
            // InternalIdioms.g:377:2: ( ( rule__ReturnsLocator__Group__0 ) )
            {
            // InternalIdioms.g:377:2: ( ( rule__ReturnsLocator__Group__0 ) )
            // InternalIdioms.g:378:3: ( rule__ReturnsLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup());
            }
            // InternalIdioms.g:379:3: ( rule__ReturnsLocator__Group__0 )
            // InternalIdioms.g:379:4: rule__ReturnsLocator__Group__0
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
    // InternalIdioms.g:388:1: entryRuleReferredLocator : ruleReferredLocator EOF ;
    public final void entryRuleReferredLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:389:1: ( ruleReferredLocator EOF )
            // InternalIdioms.g:390:1: ruleReferredLocator EOF
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
    // InternalIdioms.g:397:1: ruleReferredLocator : ( ( rule__ReferredLocator__Group__0 ) ) ;
    public final void ruleReferredLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:401:2: ( ( ( rule__ReferredLocator__Group__0 ) ) )
            // InternalIdioms.g:402:2: ( ( rule__ReferredLocator__Group__0 ) )
            {
            // InternalIdioms.g:402:2: ( ( rule__ReferredLocator__Group__0 ) )
            // InternalIdioms.g:403:3: ( rule__ReferredLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup());
            }
            // InternalIdioms.g:404:3: ( rule__ReferredLocator__Group__0 )
            // InternalIdioms.g:404:4: rule__ReferredLocator__Group__0
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


    // $ANTLR start "rule__IdiomsModel__Alternatives_4"
    // InternalIdioms.g:962:1: rule__IdiomsModel__Alternatives_4 : ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) ) );
    public final void rule__IdiomsModel__Alternatives_4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:966:1: ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt1=1;
                }
                break;
            case 27:
                {
                alt1=2;
                }
                break;
            case 45:
            case 51:
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
                    // InternalIdioms.g:967:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) )
                    {
                    // InternalIdioms.g:967:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 ) )
                    // InternalIdioms.g:968:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_4_0());
                    }
                    // InternalIdioms.g:969:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 )
                    // InternalIdioms.g:969:4: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0
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
                    // InternalIdioms.g:973:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) )
                    {
                    // InternalIdioms.g:973:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 ) )
                    // InternalIdioms.g:974:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_4_1());
                    }
                    // InternalIdioms.g:975:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 )
                    // InternalIdioms.g:975:4: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1
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
                    // InternalIdioms.g:979:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) )
                    {
                    // InternalIdioms.g:979:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 ) )
                    // InternalIdioms.g:980:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_4_2());
                    }
                    // InternalIdioms.g:981:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_4_2 )
                    // InternalIdioms.g:981:4: rule__IdiomsModel__OwnedIdiomsAssignment_4_2
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


    // $ANTLR start "rule__ElementLocator__Alternatives"
    // InternalIdioms.g:989:1: rule__ElementLocator__Alternatives : ( ( ruleAssignmentLocator ) | ( ruleCompoundLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) );
    public final void rule__ElementLocator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:993:1: ( ( ruleAssignmentLocator ) | ( ruleCompoundLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt2=1;
                }
                break;
            case 22:
                {
                alt2=2;
                }
                break;
            case RULE_STRING:
                {
                alt2=3;
                }
                break;
            case RULE_ID:
                {
                alt2=4;
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
                    // InternalIdioms.g:994:2: ( ruleAssignmentLocator )
                    {
                    // InternalIdioms.g:994:2: ( ruleAssignmentLocator )
                    // InternalIdioms.g:995:3: ruleAssignmentLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getElementLocatorAccess().getAssignmentLocatorParserRuleCall_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleAssignmentLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getElementLocatorAccess().getAssignmentLocatorParserRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1000:2: ( ruleCompoundLocator )
                    {
                    // InternalIdioms.g:1000:2: ( ruleCompoundLocator )
                    // InternalIdioms.g:1001:3: ruleCompoundLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getElementLocatorAccess().getCompoundLocatorParserRuleCall_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleCompoundLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getElementLocatorAccess().getCompoundLocatorParserRuleCall_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:1006:2: ( ruleKeywordLocator )
                    {
                    // InternalIdioms.g:1006:2: ( ruleKeywordLocator )
                    // InternalIdioms.g:1007:3: ruleKeywordLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getElementLocatorAccess().getKeywordLocatorParserRuleCall_2());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleKeywordLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getElementLocatorAccess().getKeywordLocatorParserRuleCall_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalIdioms.g:1012:2: ( ruleReferredLocator )
                    {
                    // InternalIdioms.g:1012:2: ( ruleReferredLocator )
                    // InternalIdioms.g:1013:3: ruleReferredLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getElementLocatorAccess().getReferredLocatorParserRuleCall_3());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleReferredLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getElementLocatorAccess().getReferredLocatorParserRuleCall_3());
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
    // $ANTLR end "rule__ElementLocator__Alternatives"


    // $ANTLR start "rule__Locator__Alternatives"
    // InternalIdioms.g:1022:1: rule__Locator__Alternatives : ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleElementLocator ) | ( ruleFinalLocator ) | ( ruleReturnsLocator ) );
    public final void rule__Locator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1026:1: ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleElementLocator ) | ( ruleFinalLocator ) | ( ruleReturnsLocator ) )
            int alt3=5;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt3=1;
                }
                break;
            case 19:
                {
                alt3=2;
                }
                break;
            case RULE_ID:
            case RULE_STRING:
            case 20:
            case 22:
                {
                alt3=3;
                }
                break;
            case 25:
                {
                alt3=4;
                }
                break;
            case 26:
                {
                alt3=5;
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
                    // InternalIdioms.g:1027:2: ( ruleAnyAssignmentLocator )
                    {
                    // InternalIdioms.g:1027:2: ( ruleAnyAssignmentLocator )
                    // InternalIdioms.g:1028:3: ruleAnyAssignmentLocator
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
                    // InternalIdioms.g:1033:2: ( ruleAnyElementLocator )
                    {
                    // InternalIdioms.g:1033:2: ( ruleAnyElementLocator )
                    // InternalIdioms.g:1034:3: ruleAnyElementLocator
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
                    // InternalIdioms.g:1039:2: ( ruleElementLocator )
                    {
                    // InternalIdioms.g:1039:2: ( ruleElementLocator )
                    // InternalIdioms.g:1040:3: ruleElementLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getElementLocatorParserRuleCall_2());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleElementLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getElementLocatorParserRuleCall_2());
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalIdioms.g:1045:2: ( ruleFinalLocator )
                    {
                    // InternalIdioms.g:1045:2: ( ruleFinalLocator )
                    // InternalIdioms.g:1046:3: ruleFinalLocator
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
                    // InternalIdioms.g:1051:2: ( ruleReturnsLocator )
                    {
                    // InternalIdioms.g:1051:2: ( ruleReturnsLocator )
                    // InternalIdioms.g:1052:3: ruleReturnsLocator
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_4());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleReturnsLocator();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_4());
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
    // InternalIdioms.g:1061:1: rule__Segment__Alternatives : ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) );
    public final void rule__Segment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1065:1: ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) )
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
                    // InternalIdioms.g:1066:2: ( ruleCustomSegment )
                    {
                    // InternalIdioms.g:1066:2: ( ruleCustomSegment )
                    // InternalIdioms.g:1067:3: ruleCustomSegment
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
                    // InternalIdioms.g:1072:2: ( ruleHalfNewLineSegment )
                    {
                    // InternalIdioms.g:1072:2: ( ruleHalfNewLineSegment )
                    // InternalIdioms.g:1073:3: ruleHalfNewLineSegment
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
                    // InternalIdioms.g:1078:2: ( ruleNewLineSegment )
                    {
                    // InternalIdioms.g:1078:2: ( ruleNewLineSegment )
                    // InternalIdioms.g:1079:3: ruleNewLineSegment
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
                    // InternalIdioms.g:1084:2: ( ruleNoSpaceSegment )
                    {
                    // InternalIdioms.g:1084:2: ( ruleNoSpaceSegment )
                    // InternalIdioms.g:1085:3: ruleNoSpaceSegment
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
                    // InternalIdioms.g:1090:2: ( rulePopSegment )
                    {
                    // InternalIdioms.g:1090:2: ( rulePopSegment )
                    // InternalIdioms.g:1091:3: rulePopSegment
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
                    // InternalIdioms.g:1096:2: ( rulePostCommentSegment )
                    {
                    // InternalIdioms.g:1096:2: ( rulePostCommentSegment )
                    // InternalIdioms.g:1097:3: rulePostCommentSegment
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
                    // InternalIdioms.g:1102:2: ( rulePreCommentSegment )
                    {
                    // InternalIdioms.g:1102:2: ( rulePreCommentSegment )
                    // InternalIdioms.g:1103:3: rulePreCommentSegment
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
                    // InternalIdioms.g:1108:2: ( rulePushSegment )
                    {
                    // InternalIdioms.g:1108:2: ( rulePushSegment )
                    // InternalIdioms.g:1109:3: rulePushSegment
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
                    // InternalIdioms.g:1114:2: ( ruleSoftNewLineSegment )
                    {
                    // InternalIdioms.g:1114:2: ( ruleSoftNewLineSegment )
                    // InternalIdioms.g:1115:3: ruleSoftNewLineSegment
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
                    // InternalIdioms.g:1120:2: ( ruleSoftSpaceSegment )
                    {
                    // InternalIdioms.g:1120:2: ( ruleSoftSpaceSegment )
                    // InternalIdioms.g:1121:3: ruleSoftSpaceSegment
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
                    // InternalIdioms.g:1126:2: ( ruleStringSegment )
                    {
                    // InternalIdioms.g:1126:2: ( ruleStringSegment )
                    // InternalIdioms.g:1127:3: ruleStringSegment
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
                    // InternalIdioms.g:1132:2: ( ruleValueSegment )
                    {
                    // InternalIdioms.g:1132:2: ( ruleValueSegment )
                    // InternalIdioms.g:1133:3: ruleValueSegment
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
                    // InternalIdioms.g:1138:2: ( ruleWrapAnchorSegment )
                    {
                    // InternalIdioms.g:1138:2: ( ruleWrapAnchorSegment )
                    // InternalIdioms.g:1139:3: ruleWrapAnchorSegment
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
                    // InternalIdioms.g:1144:2: ( ruleWrapBeginAllSegment )
                    {
                    // InternalIdioms.g:1144:2: ( ruleWrapBeginAllSegment )
                    // InternalIdioms.g:1145:3: ruleWrapBeginAllSegment
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
                    // InternalIdioms.g:1150:2: ( ruleWrapBeginSomeSegment )
                    {
                    // InternalIdioms.g:1150:2: ( ruleWrapBeginSomeSegment )
                    // InternalIdioms.g:1151:3: ruleWrapBeginSomeSegment
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
                    // InternalIdioms.g:1156:2: ( ruleWrapEndSegment )
                    {
                    // InternalIdioms.g:1156:2: ( ruleWrapEndSegment )
                    // InternalIdioms.g:1157:3: ruleWrapEndSegment
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
                    // InternalIdioms.g:1162:2: ( ruleWrapHereSegment )
                    {
                    // InternalIdioms.g:1162:2: ( ruleWrapHereSegment )
                    // InternalIdioms.g:1163:3: ruleWrapHereSegment
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
    // InternalIdioms.g:1172:1: rule__Idiom__Alternatives_5 : ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) );
    public final void rule__Idiom__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1176:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==48) ) {
                alt5=1;
            }
            else if ( (LA5_0==22) ) {
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
                    // InternalIdioms.g:1177:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    {
                    // InternalIdioms.g:1177:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    // InternalIdioms.g:1178:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0());
                    }
                    // InternalIdioms.g:1179:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    // InternalIdioms.g:1179:4: rule__Idiom__OwnedSubIdiomsAssignment_5_0
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
                    // InternalIdioms.g:1183:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    {
                    // InternalIdioms.g:1183:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    // InternalIdioms.g:1184:3: ( rule__Idiom__Group_5_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getGroup_5_1());
                    }
                    // InternalIdioms.g:1185:3: ( rule__Idiom__Group_5_1__0 )
                    // InternalIdioms.g:1185:4: rule__Idiom__Group_5_1__0
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
    // InternalIdioms.g:1193:1: rule__SubIdiom__Alternatives_1 : ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) );
    public final void rule__SubIdiom__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1197:1: ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==52) ) {
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
                    // InternalIdioms.g:1198:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    {
                    // InternalIdioms.g:1198:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    // InternalIdioms.g:1199:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0());
                    }
                    // InternalIdioms.g:1200:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    // InternalIdioms.g:1200:4: rule__SubIdiom__AllAssignment_1_0
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
                    // InternalIdioms.g:1204:2: ( 'each' )
                    {
                    // InternalIdioms.g:1204:2: ( 'each' )
                    // InternalIdioms.g:1205:3: 'each'
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
    // InternalIdioms.g:1214:1: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 : ( ( ruleSegment ) | ( ruleReferredSegment ) );
    public final void rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1218:1: ( ( ruleSegment ) | ( ruleReferredSegment ) )
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
                    // InternalIdioms.g:1219:2: ( ruleSegment )
                    {
                    // InternalIdioms.g:1219:2: ( ruleSegment )
                    // InternalIdioms.g:1220:3: ruleSegment
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
                    // InternalIdioms.g:1225:2: ( ruleReferredSegment )
                    {
                    // InternalIdioms.g:1225:2: ( ruleReferredSegment )
                    // InternalIdioms.g:1226:3: ruleReferredSegment
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
    // InternalIdioms.g:1235:1: rule__IdiomsModel__Group__0 : rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 ;
    public final void rule__IdiomsModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1239:1: ( rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 )
            // InternalIdioms.g:1240:2: rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1
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
    // InternalIdioms.g:1247:1: rule__IdiomsModel__Group__0__Impl : ( 'model' ) ;
    public final void rule__IdiomsModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1251:1: ( ( 'model' ) )
            // InternalIdioms.g:1252:1: ( 'model' )
            {
            // InternalIdioms.g:1252:1: ( 'model' )
            // InternalIdioms.g:1253:2: 'model'
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
    // InternalIdioms.g:1262:1: rule__IdiomsModel__Group__1 : rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 ;
    public final void rule__IdiomsModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1266:1: ( rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 )
            // InternalIdioms.g:1267:2: rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2
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
    // InternalIdioms.g:1274:1: rule__IdiomsModel__Group__1__Impl : ( ( rule__IdiomsModel__NameAssignment_1 ) ) ;
    public final void rule__IdiomsModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1278:1: ( ( ( rule__IdiomsModel__NameAssignment_1 ) ) )
            // InternalIdioms.g:1279:1: ( ( rule__IdiomsModel__NameAssignment_1 ) )
            {
            // InternalIdioms.g:1279:1: ( ( rule__IdiomsModel__NameAssignment_1 ) )
            // InternalIdioms.g:1280:2: ( rule__IdiomsModel__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:1281:2: ( rule__IdiomsModel__NameAssignment_1 )
            // InternalIdioms.g:1281:3: rule__IdiomsModel__NameAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__NameAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNameAssignment_1());
            }

            }


            }

        }
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
    // InternalIdioms.g:1289:1: rule__IdiomsModel__Group__2 : rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 ;
    public final void rule__IdiomsModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1293:1: ( rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 )
            // InternalIdioms.g:1294:2: rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3
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
    // InternalIdioms.g:1301:1: rule__IdiomsModel__Group__2__Impl : ( ( rule__IdiomsModel__OwnedWithsAssignment_2 )* ) ;
    public final void rule__IdiomsModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1305:1: ( ( ( rule__IdiomsModel__OwnedWithsAssignment_2 )* ) )
            // InternalIdioms.g:1306:1: ( ( rule__IdiomsModel__OwnedWithsAssignment_2 )* )
            {
            // InternalIdioms.g:1306:1: ( ( rule__IdiomsModel__OwnedWithsAssignment_2 )* )
            // InternalIdioms.g:1307:2: ( rule__IdiomsModel__OwnedWithsAssignment_2 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_2());
            }
            // InternalIdioms.g:1308:2: ( rule__IdiomsModel__OwnedWithsAssignment_2 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==16) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalIdioms.g:1308:3: rule__IdiomsModel__OwnedWithsAssignment_2
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__IdiomsModel__OwnedWithsAssignment_2();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_2());
            }

            }


            }

        }
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
    // InternalIdioms.g:1316:1: rule__IdiomsModel__Group__3 : rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 ;
    public final void rule__IdiomsModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1320:1: ( rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 )
            // InternalIdioms.g:1321:2: rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4
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
    // InternalIdioms.g:1328:1: rule__IdiomsModel__Group__3__Impl : ( ( rule__IdiomsModel__OwnedImportsAssignment_3 )* ) ;
    public final void rule__IdiomsModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1332:1: ( ( ( rule__IdiomsModel__OwnedImportsAssignment_3 )* ) )
            // InternalIdioms.g:1333:1: ( ( rule__IdiomsModel__OwnedImportsAssignment_3 )* )
            {
            // InternalIdioms.g:1333:1: ( ( rule__IdiomsModel__OwnedImportsAssignment_3 )* )
            // InternalIdioms.g:1334:2: ( rule__IdiomsModel__OwnedImportsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_3());
            }
            // InternalIdioms.g:1335:2: ( rule__IdiomsModel__OwnedImportsAssignment_3 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==13) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalIdioms.g:1335:3: rule__IdiomsModel__OwnedImportsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    rule__IdiomsModel__OwnedImportsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_3());
            }

            }


            }

        }
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
    // InternalIdioms.g:1343:1: rule__IdiomsModel__Group__4 : rule__IdiomsModel__Group__4__Impl ;
    public final void rule__IdiomsModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1347:1: ( rule__IdiomsModel__Group__4__Impl )
            // InternalIdioms.g:1348:2: rule__IdiomsModel__Group__4__Impl
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
    // InternalIdioms.g:1354:1: rule__IdiomsModel__Group__4__Impl : ( ( rule__IdiomsModel__Alternatives_4 )* ) ;
    public final void rule__IdiomsModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1358:1: ( ( ( rule__IdiomsModel__Alternatives_4 )* ) )
            // InternalIdioms.g:1359:1: ( ( rule__IdiomsModel__Alternatives_4 )* )
            {
            // InternalIdioms.g:1359:1: ( ( rule__IdiomsModel__Alternatives_4 )* )
            // InternalIdioms.g:1360:2: ( rule__IdiomsModel__Alternatives_4 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getAlternatives_4());
            }
            // InternalIdioms.g:1361:2: ( rule__IdiomsModel__Alternatives_4 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==17||LA10_0==27||LA10_0==45||LA10_0==51) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalIdioms.g:1361:3: rule__IdiomsModel__Alternatives_4
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


    // $ANTLR start "rule__EPackageImport__Group__0"
    // InternalIdioms.g:1370:1: rule__EPackageImport__Group__0 : rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1 ;
    public final void rule__EPackageImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1374:1: ( rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1 )
            // InternalIdioms.g:1375:2: rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_8);
            rule__EPackageImport__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group__1();

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
    // $ANTLR end "rule__EPackageImport__Group__0"


    // $ANTLR start "rule__EPackageImport__Group__0__Impl"
    // InternalIdioms.g:1382:1: rule__EPackageImport__Group__0__Impl : ( 'import' ) ;
    public final void rule__EPackageImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1386:1: ( ( 'import' ) )
            // InternalIdioms.g:1387:1: ( 'import' )
            {
            // InternalIdioms.g:1387:1: ( 'import' )
            // InternalIdioms.g:1388:2: 'import'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getImportKeyword_0());
            }
            match(input,13,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getImportKeyword_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group__0__Impl"


    // $ANTLR start "rule__EPackageImport__Group__1"
    // InternalIdioms.g:1397:1: rule__EPackageImport__Group__1 : rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2 ;
    public final void rule__EPackageImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1401:1: ( rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2 )
            // InternalIdioms.g:1402:2: rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__EPackageImport__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group__2();

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
    // $ANTLR end "rule__EPackageImport__Group__1"


    // $ANTLR start "rule__EPackageImport__Group__1__Impl"
    // InternalIdioms.g:1409:1: rule__EPackageImport__Group__1__Impl : ( ( rule__EPackageImport__EPackageAssignment_1 ) ) ;
    public final void rule__EPackageImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1413:1: ( ( ( rule__EPackageImport__EPackageAssignment_1 ) ) )
            // InternalIdioms.g:1414:1: ( ( rule__EPackageImport__EPackageAssignment_1 ) )
            {
            // InternalIdioms.g:1414:1: ( ( rule__EPackageImport__EPackageAssignment_1 ) )
            // InternalIdioms.g:1415:2: ( rule__EPackageImport__EPackageAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1());
            }
            // InternalIdioms.g:1416:2: ( rule__EPackageImport__EPackageAssignment_1 )
            // InternalIdioms.g:1416:3: rule__EPackageImport__EPackageAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__EPackageAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group__1__Impl"


    // $ANTLR start "rule__EPackageImport__Group__2"
    // InternalIdioms.g:1424:1: rule__EPackageImport__Group__2 : rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3 ;
    public final void rule__EPackageImport__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1428:1: ( rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3 )
            // InternalIdioms.g:1429:2: rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__EPackageImport__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group__3();

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
    // $ANTLR end "rule__EPackageImport__Group__2"


    // $ANTLR start "rule__EPackageImport__Group__2__Impl"
    // InternalIdioms.g:1436:1: rule__EPackageImport__Group__2__Impl : ( ( rule__EPackageImport__Group_2__0 )? ) ;
    public final void rule__EPackageImport__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1440:1: ( ( ( rule__EPackageImport__Group_2__0 )? ) )
            // InternalIdioms.g:1441:1: ( ( rule__EPackageImport__Group_2__0 )? )
            {
            // InternalIdioms.g:1441:1: ( ( rule__EPackageImport__Group_2__0 )? )
            // InternalIdioms.g:1442:2: ( rule__EPackageImport__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getGroup_2());
            }
            // InternalIdioms.g:1443:2: ( rule__EPackageImport__Group_2__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==15) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalIdioms.g:1443:3: rule__EPackageImport__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__EPackageImport__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getGroup_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group__2__Impl"


    // $ANTLR start "rule__EPackageImport__Group__3"
    // InternalIdioms.g:1451:1: rule__EPackageImport__Group__3 : rule__EPackageImport__Group__3__Impl ;
    public final void rule__EPackageImport__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1455:1: ( rule__EPackageImport__Group__3__Impl )
            // InternalIdioms.g:1456:2: rule__EPackageImport__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group__3__Impl();

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
    // $ANTLR end "rule__EPackageImport__Group__3"


    // $ANTLR start "rule__EPackageImport__Group__3__Impl"
    // InternalIdioms.g:1462:1: rule__EPackageImport__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__EPackageImport__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1466:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1467:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1467:1: ( ( ';' )? )
            // InternalIdioms.g:1468:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1469:2: ( ';' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==14) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalIdioms.g:1469:3: ';'
                    {
                    match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group__3__Impl"


    // $ANTLR start "rule__EPackageImport__Group_2__0"
    // InternalIdioms.g:1478:1: rule__EPackageImport__Group_2__0 : rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1 ;
    public final void rule__EPackageImport__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1482:1: ( rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1 )
            // InternalIdioms.g:1483:2: rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__EPackageImport__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group_2__1();

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
    // $ANTLR end "rule__EPackageImport__Group_2__0"


    // $ANTLR start "rule__EPackageImport__Group_2__0__Impl"
    // InternalIdioms.g:1490:1: rule__EPackageImport__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__EPackageImport__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1494:1: ( ( 'as' ) )
            // InternalIdioms.g:1495:1: ( 'as' )
            {
            // InternalIdioms.g:1495:1: ( 'as' )
            // InternalIdioms.g:1496:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getAsKeyword_2_0());
            }
            match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getAsKeyword_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group_2__0__Impl"


    // $ANTLR start "rule__EPackageImport__Group_2__1"
    // InternalIdioms.g:1505:1: rule__EPackageImport__Group_2__1 : rule__EPackageImport__Group_2__1__Impl ;
    public final void rule__EPackageImport__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1509:1: ( rule__EPackageImport__Group_2__1__Impl )
            // InternalIdioms.g:1510:2: rule__EPackageImport__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__Group_2__1__Impl();

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
    // $ANTLR end "rule__EPackageImport__Group_2__1"


    // $ANTLR start "rule__EPackageImport__Group_2__1__Impl"
    // InternalIdioms.g:1516:1: rule__EPackageImport__Group_2__1__Impl : ( ( rule__EPackageImport__AsAssignment_2_1 ) ) ;
    public final void rule__EPackageImport__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1520:1: ( ( ( rule__EPackageImport__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1521:1: ( ( rule__EPackageImport__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1521:1: ( ( rule__EPackageImport__AsAssignment_2_1 ) )
            // InternalIdioms.g:1522:2: ( rule__EPackageImport__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1523:2: ( rule__EPackageImport__AsAssignment_2_1 )
            // InternalIdioms.g:1523:3: rule__EPackageImport__AsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__EPackageImport__AsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__Group_2__1__Impl"


    // $ANTLR start "rule__IdiomsImport__Group__0"
    // InternalIdioms.g:1532:1: rule__IdiomsImport__Group__0 : rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 ;
    public final void rule__IdiomsImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1536:1: ( rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 )
            // InternalIdioms.g:1537:2: rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1
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
    // InternalIdioms.g:1544:1: rule__IdiomsImport__Group__0__Impl : ( 'with' ) ;
    public final void rule__IdiomsImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1548:1: ( ( 'with' ) )
            // InternalIdioms.g:1549:1: ( 'with' )
            {
            // InternalIdioms.g:1549:1: ( 'with' )
            // InternalIdioms.g:1550:2: 'with'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getWithKeyword_0());
            }
            match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1559:1: rule__IdiomsImport__Group__1 : rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 ;
    public final void rule__IdiomsImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1563:1: ( rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 )
            // InternalIdioms.g:1564:2: rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2
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
    // InternalIdioms.g:1571:1: rule__IdiomsImport__Group__1__Impl : ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) ;
    public final void rule__IdiomsImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1575:1: ( ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) )
            // InternalIdioms.g:1576:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            {
            // InternalIdioms.g:1576:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            // InternalIdioms.g:1577:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1());
            }
            // InternalIdioms.g:1578:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            // InternalIdioms.g:1578:3: rule__IdiomsImport__IdiomsModelAssignment_1
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
    // InternalIdioms.g:1586:1: rule__IdiomsImport__Group__2 : rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 ;
    public final void rule__IdiomsImport__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1590:1: ( rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 )
            // InternalIdioms.g:1591:2: rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3
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
    // InternalIdioms.g:1598:1: rule__IdiomsImport__Group__2__Impl : ( ( rule__IdiomsImport__Group_2__0 )? ) ;
    public final void rule__IdiomsImport__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1602:1: ( ( ( rule__IdiomsImport__Group_2__0 )? ) )
            // InternalIdioms.g:1603:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            {
            // InternalIdioms.g:1603:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            // InternalIdioms.g:1604:2: ( rule__IdiomsImport__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getGroup_2());
            }
            // InternalIdioms.g:1605:2: ( rule__IdiomsImport__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==15) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalIdioms.g:1605:3: rule__IdiomsImport__Group_2__0
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
    // InternalIdioms.g:1613:1: rule__IdiomsImport__Group__3 : rule__IdiomsImport__Group__3__Impl ;
    public final void rule__IdiomsImport__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1617:1: ( rule__IdiomsImport__Group__3__Impl )
            // InternalIdioms.g:1618:2: rule__IdiomsImport__Group__3__Impl
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
    // InternalIdioms.g:1624:1: rule__IdiomsImport__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__IdiomsImport__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1628:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1629:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1629:1: ( ( ';' )? )
            // InternalIdioms.g:1630:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1631:2: ( ';' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==14) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalIdioms.g:1631:3: ';'
                    {
                    match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

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
    // InternalIdioms.g:1640:1: rule__IdiomsImport__Group_2__0 : rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 ;
    public final void rule__IdiomsImport__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1644:1: ( rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 )
            // InternalIdioms.g:1645:2: rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1
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
    // InternalIdioms.g:1652:1: rule__IdiomsImport__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__IdiomsImport__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1656:1: ( ( 'as' ) )
            // InternalIdioms.g:1657:1: ( 'as' )
            {
            // InternalIdioms.g:1657:1: ( 'as' )
            // InternalIdioms.g:1658:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());
            }
            match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1667:1: rule__IdiomsImport__Group_2__1 : rule__IdiomsImport__Group_2__1__Impl ;
    public final void rule__IdiomsImport__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1671:1: ( rule__IdiomsImport__Group_2__1__Impl )
            // InternalIdioms.g:1672:2: rule__IdiomsImport__Group_2__1__Impl
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
    // InternalIdioms.g:1678:1: rule__IdiomsImport__Group_2__1__Impl : ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) ;
    public final void rule__IdiomsImport__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1682:1: ( ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1683:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1683:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            // InternalIdioms.g:1684:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1685:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            // InternalIdioms.g:1685:3: rule__IdiomsImport__AsAssignment_2_1
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
    // InternalIdioms.g:1694:1: rule__LocatorDeclaration__Group__0 : rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 ;
    public final void rule__LocatorDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1698:1: ( rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 )
            // InternalIdioms.g:1699:2: rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1
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
    // InternalIdioms.g:1706:1: rule__LocatorDeclaration__Group__0__Impl : ( 'locator' ) ;
    public final void rule__LocatorDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1710:1: ( ( 'locator' ) )
            // InternalIdioms.g:1711:1: ( 'locator' )
            {
            // InternalIdioms.g:1711:1: ( 'locator' )
            // InternalIdioms.g:1712:2: 'locator'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());
            }
            match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1721:1: rule__LocatorDeclaration__Group__1 : rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 ;
    public final void rule__LocatorDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1725:1: ( rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 )
            // InternalIdioms.g:1726:2: rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2
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
    // InternalIdioms.g:1733:1: rule__LocatorDeclaration__Group__1__Impl : ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) ;
    public final void rule__LocatorDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1737:1: ( ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:1738:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:1738:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:1739:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:1740:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            // InternalIdioms.g:1740:3: rule__LocatorDeclaration__NameAssignment_1
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
    // InternalIdioms.g:1748:1: rule__LocatorDeclaration__Group__2 : rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 ;
    public final void rule__LocatorDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1752:1: ( rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 )
            // InternalIdioms.g:1753:2: rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3
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
    // InternalIdioms.g:1760:1: rule__LocatorDeclaration__Group__2__Impl : ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__LocatorDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1764:1: ( ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:1765:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:1765:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:1766:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:1767:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:1767:3: rule__LocatorDeclaration__OwnedLocatorAssignment_2
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
    // InternalIdioms.g:1775:1: rule__LocatorDeclaration__Group__3 : rule__LocatorDeclaration__Group__3__Impl ;
    public final void rule__LocatorDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1779:1: ( rule__LocatorDeclaration__Group__3__Impl )
            // InternalIdioms.g:1780:2: rule__LocatorDeclaration__Group__3__Impl
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
    // InternalIdioms.g:1786:1: rule__LocatorDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__LocatorDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1790:1: ( ( ';' ) )
            // InternalIdioms.g:1791:1: ( ';' )
            {
            // InternalIdioms.g:1791:1: ( ';' )
            // InternalIdioms.g:1792:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3());
            }
            match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1802:1: rule__AnyAssignmentLocator__Group__0 : rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 ;
    public final void rule__AnyAssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1806:1: ( rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 )
            // InternalIdioms.g:1807:2: rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1
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
    // InternalIdioms.g:1814:1: rule__AnyAssignmentLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyAssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1818:1: ( ( () ) )
            // InternalIdioms.g:1819:1: ( () )
            {
            // InternalIdioms.g:1819:1: ( () )
            // InternalIdioms.g:1820:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0());
            }
            // InternalIdioms.g:1821:2: ()
            // InternalIdioms.g:1821:3:
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
    // InternalIdioms.g:1829:1: rule__AnyAssignmentLocator__Group__1 : rule__AnyAssignmentLocator__Group__1__Impl ;
    public final void rule__AnyAssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1833:1: ( rule__AnyAssignmentLocator__Group__1__Impl )
            // InternalIdioms.g:1834:2: rule__AnyAssignmentLocator__Group__1__Impl
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
    // InternalIdioms.g:1840:1: rule__AnyAssignmentLocator__Group__1__Impl : ( 'any-assignment' ) ;
    public final void rule__AnyAssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1844:1: ( ( 'any-assignment' ) )
            // InternalIdioms.g:1845:1: ( 'any-assignment' )
            {
            // InternalIdioms.g:1845:1: ( 'any-assignment' )
            // InternalIdioms.g:1846:2: 'any-assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());
            }
            match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1856:1: rule__AnyElementLocator__Group__0 : rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 ;
    public final void rule__AnyElementLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1860:1: ( rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 )
            // InternalIdioms.g:1861:2: rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1
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
    // InternalIdioms.g:1868:1: rule__AnyElementLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyElementLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1872:1: ( ( () ) )
            // InternalIdioms.g:1873:1: ( () )
            {
            // InternalIdioms.g:1873:1: ( () )
            // InternalIdioms.g:1874:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0());
            }
            // InternalIdioms.g:1875:2: ()
            // InternalIdioms.g:1875:3:
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
    // InternalIdioms.g:1883:1: rule__AnyElementLocator__Group__1 : rule__AnyElementLocator__Group__1__Impl ;
    public final void rule__AnyElementLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1887:1: ( rule__AnyElementLocator__Group__1__Impl )
            // InternalIdioms.g:1888:2: rule__AnyElementLocator__Group__1__Impl
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
    // InternalIdioms.g:1894:1: rule__AnyElementLocator__Group__1__Impl : ( 'any-element' ) ;
    public final void rule__AnyElementLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1898:1: ( ( 'any-element' ) )
            // InternalIdioms.g:1899:1: ( 'any-element' )
            {
            // InternalIdioms.g:1899:1: ( 'any-element' )
            // InternalIdioms.g:1900:2: 'any-element'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());
            }
            match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1910:1: rule__AssignmentLocator__Group__0 : rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 ;
    public final void rule__AssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1914:1: ( rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 )
            // InternalIdioms.g:1915:2: rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1
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
    // InternalIdioms.g:1922:1: rule__AssignmentLocator__Group__0__Impl : ( 'assignment' ) ;
    public final void rule__AssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1926:1: ( ( 'assignment' ) )
            // InternalIdioms.g:1927:1: ( 'assignment' )
            {
            // InternalIdioms.g:1927:1: ( 'assignment' )
            // InternalIdioms.g:1928:2: 'assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());
            }
            match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1937:1: rule__AssignmentLocator__Group__1 : rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 ;
    public final void rule__AssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1941:1: ( rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 )
            // InternalIdioms.g:1942:2: rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2
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
    // InternalIdioms.g:1949:1: rule__AssignmentLocator__Group__1__Impl : ( ( rule__AssignmentLocator__Group_1__0 )? ) ;
    public final void rule__AssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1953:1: ( ( ( rule__AssignmentLocator__Group_1__0 )? ) )
            // InternalIdioms.g:1954:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:1954:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            // InternalIdioms.g:1955:2: ( rule__AssignmentLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:1956:2: ( rule__AssignmentLocator__Group_1__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==21) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // InternalIdioms.g:1956:3: rule__AssignmentLocator__Group_1__0
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
    // InternalIdioms.g:1964:1: rule__AssignmentLocator__Group__2 : rule__AssignmentLocator__Group__2__Impl ;
    public final void rule__AssignmentLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1968:1: ( rule__AssignmentLocator__Group__2__Impl )
            // InternalIdioms.g:1969:2: rule__AssignmentLocator__Group__2__Impl
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
    // InternalIdioms.g:1975:1: rule__AssignmentLocator__Group__2__Impl : ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) ;
    public final void rule__AssignmentLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1979:1: ( ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) )
            // InternalIdioms.g:1980:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            {
            // InternalIdioms.g:1980:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            // InternalIdioms.g:1981:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2());
            }
            // InternalIdioms.g:1982:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            // InternalIdioms.g:1982:3: rule__AssignmentLocator__EStructuralFeatureAssignment_2
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
    // InternalIdioms.g:1991:1: rule__AssignmentLocator__Group_1__0 : rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 ;
    public final void rule__AssignmentLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1995:1: ( rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 )
            // InternalIdioms.g:1996:2: rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1
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
    // InternalIdioms.g:2003:1: rule__AssignmentLocator__Group_1__0__Impl : ( ( rule__AssignmentLocator__Group_1_0__0 )? ) ;
    public final void rule__AssignmentLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2007:1: ( ( ( rule__AssignmentLocator__Group_1_0__0 )? ) )
            // InternalIdioms.g:2008:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            {
            // InternalIdioms.g:2008:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            // InternalIdioms.g:2009:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0());
            }
            // InternalIdioms.g:2010:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==21) ) {
                    int LA16_2 = input.LA(3);

                    if ( (LA16_2==RULE_ID) ) {
                        int LA16_3 = input.LA(4);

                        if ( (LA16_3==21) ) {
                            alt16=1;
                        }
                    }
                }
            }
            switch (alt16) {
                case 1 :
                    // InternalIdioms.g:2010:3: rule__AssignmentLocator__Group_1_0__0
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
    // InternalIdioms.g:2018:1: rule__AssignmentLocator__Group_1__1 : rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 ;
    public final void rule__AssignmentLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2022:1: ( rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 )
            // InternalIdioms.g:2023:2: rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2
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
    // InternalIdioms.g:2030:1: rule__AssignmentLocator__Group_1__1__Impl : ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) ;
    public final void rule__AssignmentLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2034:1: ( ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) )
            // InternalIdioms.g:2035:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            {
            // InternalIdioms.g:2035:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            // InternalIdioms.g:2036:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1());
            }
            // InternalIdioms.g:2037:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            // InternalIdioms.g:2037:3: rule__AssignmentLocator__EClassAssignment_1_1
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
    // InternalIdioms.g:2045:1: rule__AssignmentLocator__Group_1__2 : rule__AssignmentLocator__Group_1__2__Impl ;
    public final void rule__AssignmentLocator__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2049:1: ( rule__AssignmentLocator__Group_1__2__Impl )
            // InternalIdioms.g:2050:2: rule__AssignmentLocator__Group_1__2__Impl
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
    // InternalIdioms.g:2056:1: rule__AssignmentLocator__Group_1__2__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2060:1: ( ( '::' ) )
            // InternalIdioms.g:2061:1: ( '::' )
            {
            // InternalIdioms.g:2061:1: ( '::' )
            // InternalIdioms.g:2062:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2072:1: rule__AssignmentLocator__Group_1_0__0 : rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 ;
    public final void rule__AssignmentLocator__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2076:1: ( rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 )
            // InternalIdioms.g:2077:2: rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1
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
    // InternalIdioms.g:2084:1: rule__AssignmentLocator__Group_1_0__0__Impl : ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) ;
    public final void rule__AssignmentLocator__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2088:1: ( ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) )
            // InternalIdioms.g:2089:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            {
            // InternalIdioms.g:2089:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            // InternalIdioms.g:2090:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0());
            }
            // InternalIdioms.g:2091:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            // InternalIdioms.g:2091:3: rule__AssignmentLocator__EPackageAssignment_1_0_0
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
    // InternalIdioms.g:2099:1: rule__AssignmentLocator__Group_1_0__1 : rule__AssignmentLocator__Group_1_0__1__Impl ;
    public final void rule__AssignmentLocator__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2103:1: ( rule__AssignmentLocator__Group_1_0__1__Impl )
            // InternalIdioms.g:2104:2: rule__AssignmentLocator__Group_1_0__1__Impl
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
    // InternalIdioms.g:2110:1: rule__AssignmentLocator__Group_1_0__1__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2114:1: ( ( '::' ) )
            // InternalIdioms.g:2115:1: ( '::' )
            {
            // InternalIdioms.g:2115:1: ( '::' )
            // InternalIdioms.g:2116:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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


    // $ANTLR start "rule__CompoundLocator__Group__0"
    // InternalIdioms.g:2126:1: rule__CompoundLocator__Group__0 : rule__CompoundLocator__Group__0__Impl rule__CompoundLocator__Group__1 ;
    public final void rule__CompoundLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2130:1: ( rule__CompoundLocator__Group__0__Impl rule__CompoundLocator__Group__1 )
            // InternalIdioms.g:2131:2: rule__CompoundLocator__Group__0__Impl rule__CompoundLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
            rule__CompoundLocator__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group__1();

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
    // $ANTLR end "rule__CompoundLocator__Group__0"


    // $ANTLR start "rule__CompoundLocator__Group__0__Impl"
    // InternalIdioms.g:2138:1: rule__CompoundLocator__Group__0__Impl : ( '{' ) ;
    public final void rule__CompoundLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2142:1: ( ( '{' ) )
            // InternalIdioms.g:2143:1: ( '{' )
            {
            // InternalIdioms.g:2143:1: ( '{' )
            // InternalIdioms.g:2144:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getLeftCurlyBracketKeyword_0());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getLeftCurlyBracketKeyword_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group__0__Impl"


    // $ANTLR start "rule__CompoundLocator__Group__1"
    // InternalIdioms.g:2153:1: rule__CompoundLocator__Group__1 : rule__CompoundLocator__Group__1__Impl rule__CompoundLocator__Group__2 ;
    public final void rule__CompoundLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2157:1: ( rule__CompoundLocator__Group__1__Impl rule__CompoundLocator__Group__2 )
            // InternalIdioms.g:2158:2: rule__CompoundLocator__Group__1__Impl rule__CompoundLocator__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__CompoundLocator__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group__2();

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
    // $ANTLR end "rule__CompoundLocator__Group__1"


    // $ANTLR start "rule__CompoundLocator__Group__1__Impl"
    // InternalIdioms.g:2165:1: rule__CompoundLocator__Group__1__Impl : ( ( rule__CompoundLocator__OwnedLocatorsAssignment_1 ) ) ;
    public final void rule__CompoundLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2169:1: ( ( ( rule__CompoundLocator__OwnedLocatorsAssignment_1 ) ) )
            // InternalIdioms.g:2170:1: ( ( rule__CompoundLocator__OwnedLocatorsAssignment_1 ) )
            {
            // InternalIdioms.g:2170:1: ( ( rule__CompoundLocator__OwnedLocatorsAssignment_1 ) )
            // InternalIdioms.g:2171:2: ( rule__CompoundLocator__OwnedLocatorsAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsAssignment_1());
            }
            // InternalIdioms.g:2172:2: ( rule__CompoundLocator__OwnedLocatorsAssignment_1 )
            // InternalIdioms.g:2172:3: rule__CompoundLocator__OwnedLocatorsAssignment_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__OwnedLocatorsAssignment_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsAssignment_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group__1__Impl"


    // $ANTLR start "rule__CompoundLocator__Group__2"
    // InternalIdioms.g:2180:1: rule__CompoundLocator__Group__2 : rule__CompoundLocator__Group__2__Impl rule__CompoundLocator__Group__3 ;
    public final void rule__CompoundLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2184:1: ( rule__CompoundLocator__Group__2__Impl rule__CompoundLocator__Group__3 )
            // InternalIdioms.g:2185:2: rule__CompoundLocator__Group__2__Impl rule__CompoundLocator__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__CompoundLocator__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group__3();

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
    // $ANTLR end "rule__CompoundLocator__Group__2"


    // $ANTLR start "rule__CompoundLocator__Group__2__Impl"
    // InternalIdioms.g:2192:1: rule__CompoundLocator__Group__2__Impl : ( ( rule__CompoundLocator__Group_2__0 )* ) ;
    public final void rule__CompoundLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2196:1: ( ( ( rule__CompoundLocator__Group_2__0 )* ) )
            // InternalIdioms.g:2197:1: ( ( rule__CompoundLocator__Group_2__0 )* )
            {
            // InternalIdioms.g:2197:1: ( ( rule__CompoundLocator__Group_2__0 )* )
            // InternalIdioms.g:2198:2: ( rule__CompoundLocator__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getGroup_2());
            }
            // InternalIdioms.g:2199:2: ( rule__CompoundLocator__Group_2__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==24) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalIdioms.g:2199:3: rule__CompoundLocator__Group_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_17);
            	    rule__CompoundLocator__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getGroup_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group__2__Impl"


    // $ANTLR start "rule__CompoundLocator__Group__3"
    // InternalIdioms.g:2207:1: rule__CompoundLocator__Group__3 : rule__CompoundLocator__Group__3__Impl ;
    public final void rule__CompoundLocator__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2211:1: ( rule__CompoundLocator__Group__3__Impl )
            // InternalIdioms.g:2212:2: rule__CompoundLocator__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group__3__Impl();

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
    // $ANTLR end "rule__CompoundLocator__Group__3"


    // $ANTLR start "rule__CompoundLocator__Group__3__Impl"
    // InternalIdioms.g:2218:1: rule__CompoundLocator__Group__3__Impl : ( '}' ) ;
    public final void rule__CompoundLocator__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2222:1: ( ( '}' ) )
            // InternalIdioms.g:2223:1: ( '}' )
            {
            // InternalIdioms.g:2223:1: ( '}' )
            // InternalIdioms.g:2224:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getRightCurlyBracketKeyword_3());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getRightCurlyBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group__3__Impl"


    // $ANTLR start "rule__CompoundLocator__Group_2__0"
    // InternalIdioms.g:2234:1: rule__CompoundLocator__Group_2__0 : rule__CompoundLocator__Group_2__0__Impl rule__CompoundLocator__Group_2__1 ;
    public final void rule__CompoundLocator__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2238:1: ( rule__CompoundLocator__Group_2__0__Impl rule__CompoundLocator__Group_2__1 )
            // InternalIdioms.g:2239:2: rule__CompoundLocator__Group_2__0__Impl rule__CompoundLocator__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
            rule__CompoundLocator__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group_2__1();

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
    // $ANTLR end "rule__CompoundLocator__Group_2__0"


    // $ANTLR start "rule__CompoundLocator__Group_2__0__Impl"
    // InternalIdioms.g:2246:1: rule__CompoundLocator__Group_2__0__Impl : ( '|' ) ;
    public final void rule__CompoundLocator__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2250:1: ( ( '|' ) )
            // InternalIdioms.g:2251:1: ( '|' )
            {
            // InternalIdioms.g:2251:1: ( '|' )
            // InternalIdioms.g:2252:2: '|'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getVerticalLineKeyword_2_0());
            }
            match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getVerticalLineKeyword_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group_2__0__Impl"


    // $ANTLR start "rule__CompoundLocator__Group_2__1"
    // InternalIdioms.g:2261:1: rule__CompoundLocator__Group_2__1 : rule__CompoundLocator__Group_2__1__Impl ;
    public final void rule__CompoundLocator__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2265:1: ( rule__CompoundLocator__Group_2__1__Impl )
            // InternalIdioms.g:2266:2: rule__CompoundLocator__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__Group_2__1__Impl();

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
    // $ANTLR end "rule__CompoundLocator__Group_2__1"


    // $ANTLR start "rule__CompoundLocator__Group_2__1__Impl"
    // InternalIdioms.g:2272:1: rule__CompoundLocator__Group_2__1__Impl : ( ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 ) ) ;
    public final void rule__CompoundLocator__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2276:1: ( ( ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 ) ) )
            // InternalIdioms.g:2277:1: ( ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 ) )
            {
            // InternalIdioms.g:2277:1: ( ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 ) )
            // InternalIdioms.g:2278:2: ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsAssignment_2_1());
            }
            // InternalIdioms.g:2279:2: ( rule__CompoundLocator__OwnedLocatorsAssignment_2_1 )
            // InternalIdioms.g:2279:3: rule__CompoundLocator__OwnedLocatorsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CompoundLocator__OwnedLocatorsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsAssignment_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__Group_2__1__Impl"


    // $ANTLR start "rule__FinalLocator__Group__0"
    // InternalIdioms.g:2288:1: rule__FinalLocator__Group__0 : rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 ;
    public final void rule__FinalLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2292:1: ( rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 )
            // InternalIdioms.g:2293:2: rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_18);
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
    // InternalIdioms.g:2300:1: rule__FinalLocator__Group__0__Impl : ( () ) ;
    public final void rule__FinalLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2304:1: ( ( () ) )
            // InternalIdioms.g:2305:1: ( () )
            {
            // InternalIdioms.g:2305:1: ( () )
            // InternalIdioms.g:2306:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0());
            }
            // InternalIdioms.g:2307:2: ()
            // InternalIdioms.g:2307:3:
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
    // InternalIdioms.g:2315:1: rule__FinalLocator__Group__1 : rule__FinalLocator__Group__1__Impl ;
    public final void rule__FinalLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2319:1: ( rule__FinalLocator__Group__1__Impl )
            // InternalIdioms.g:2320:2: rule__FinalLocator__Group__1__Impl
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
    // InternalIdioms.g:2326:1: rule__FinalLocator__Group__1__Impl : ( 'final' ) ;
    public final void rule__FinalLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2330:1: ( ( 'final' ) )
            // InternalIdioms.g:2331:1: ( 'final' )
            {
            // InternalIdioms.g:2331:1: ( 'final' )
            // InternalIdioms.g:2332:2: 'final'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());
            }
            match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2342:1: rule__ReturnsLocator__Group__0 : rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 ;
    public final void rule__ReturnsLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2346:1: ( rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 )
            // InternalIdioms.g:2347:2: rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1
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
    // InternalIdioms.g:2354:1: rule__ReturnsLocator__Group__0__Impl : ( 'returns' ) ;
    public final void rule__ReturnsLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2358:1: ( ( 'returns' ) )
            // InternalIdioms.g:2359:1: ( 'returns' )
            {
            // InternalIdioms.g:2359:1: ( 'returns' )
            // InternalIdioms.g:2360:2: 'returns'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2369:1: rule__ReturnsLocator__Group__1 : rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 ;
    public final void rule__ReturnsLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2373:1: ( rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 )
            // InternalIdioms.g:2374:2: rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2
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
    // InternalIdioms.g:2381:1: rule__ReturnsLocator__Group__1__Impl : ( ( rule__ReturnsLocator__Group_1__0 )? ) ;
    public final void rule__ReturnsLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2385:1: ( ( ( rule__ReturnsLocator__Group_1__0 )? ) )
            // InternalIdioms.g:2386:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:2386:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            // InternalIdioms.g:2387:2: ( rule__ReturnsLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:2388:2: ( rule__ReturnsLocator__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==21) ) {
                    alt18=1;
                }
            }
            switch (alt18) {
                case 1 :
                    // InternalIdioms.g:2388:3: rule__ReturnsLocator__Group_1__0
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
    // InternalIdioms.g:2396:1: rule__ReturnsLocator__Group__2 : rule__ReturnsLocator__Group__2__Impl ;
    public final void rule__ReturnsLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2400:1: ( rule__ReturnsLocator__Group__2__Impl )
            // InternalIdioms.g:2401:2: rule__ReturnsLocator__Group__2__Impl
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
    // InternalIdioms.g:2407:1: rule__ReturnsLocator__Group__2__Impl : ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) ;
    public final void rule__ReturnsLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2411:1: ( ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) )
            // InternalIdioms.g:2412:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            {
            // InternalIdioms.g:2412:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            // InternalIdioms.g:2413:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2());
            }
            // InternalIdioms.g:2414:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            // InternalIdioms.g:2414:3: rule__ReturnsLocator__EClassAssignment_2
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
    // InternalIdioms.g:2423:1: rule__ReturnsLocator__Group_1__0 : rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 ;
    public final void rule__ReturnsLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2427:1: ( rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 )
            // InternalIdioms.g:2428:2: rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1
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
    // InternalIdioms.g:2435:1: rule__ReturnsLocator__Group_1__0__Impl : ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) ;
    public final void rule__ReturnsLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2439:1: ( ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) )
            // InternalIdioms.g:2440:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            {
            // InternalIdioms.g:2440:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            // InternalIdioms.g:2441:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0());
            }
            // InternalIdioms.g:2442:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            // InternalIdioms.g:2442:3: rule__ReturnsLocator__EPackageAssignment_1_0
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
    // InternalIdioms.g:2450:1: rule__ReturnsLocator__Group_1__1 : rule__ReturnsLocator__Group_1__1__Impl ;
    public final void rule__ReturnsLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2454:1: ( rule__ReturnsLocator__Group_1__1__Impl )
            // InternalIdioms.g:2455:2: rule__ReturnsLocator__Group_1__1__Impl
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
    // InternalIdioms.g:2461:1: rule__ReturnsLocator__Group_1__1__Impl : ( '::' ) ;
    public final void rule__ReturnsLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2465:1: ( ( '::' ) )
            // InternalIdioms.g:2466:1: ( '::' )
            {
            // InternalIdioms.g:2466:1: ( '::' )
            // InternalIdioms.g:2467:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2477:1: rule__ReferredLocator__Group__0 : rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 ;
    public final void rule__ReferredLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2481:1: ( rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 )
            // InternalIdioms.g:2482:2: rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:2489:1: rule__ReferredLocator__Group__0__Impl : ( ( rule__ReferredLocator__Group_0__0 )? ) ;
    public final void rule__ReferredLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2493:1: ( ( ( rule__ReferredLocator__Group_0__0 )? ) )
            // InternalIdioms.g:2494:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            {
            // InternalIdioms.g:2494:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            // InternalIdioms.g:2495:2: ( rule__ReferredLocator__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup_0());
            }
            // InternalIdioms.g:2496:2: ( rule__ReferredLocator__Group_0__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==21) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // InternalIdioms.g:2496:3: rule__ReferredLocator__Group_0__0
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
    // InternalIdioms.g:2504:1: rule__ReferredLocator__Group__1 : rule__ReferredLocator__Group__1__Impl ;
    public final void rule__ReferredLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2508:1: ( rule__ReferredLocator__Group__1__Impl )
            // InternalIdioms.g:2509:2: rule__ReferredLocator__Group__1__Impl
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
    // InternalIdioms.g:2515:1: rule__ReferredLocator__Group__1__Impl : ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2519:1: ( ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:2520:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:2520:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            // InternalIdioms.g:2521:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1());
            }
            // InternalIdioms.g:2522:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            // InternalIdioms.g:2522:3: rule__ReferredLocator__LocatorDeclarationAssignment_1
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
    // InternalIdioms.g:2531:1: rule__ReferredLocator__Group_0__0 : rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 ;
    public final void rule__ReferredLocator__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2535:1: ( rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 )
            // InternalIdioms.g:2536:2: rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1
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
    // InternalIdioms.g:2543:1: rule__ReferredLocator__Group_0__0__Impl : ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredLocator__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2547:1: ( ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:2548:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:2548:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:2549:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:2550:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:2550:3: rule__ReferredLocator__IdiomsModelAssignment_0_0
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
    // InternalIdioms.g:2558:1: rule__ReferredLocator__Group_0__1 : rule__ReferredLocator__Group_0__1__Impl ;
    public final void rule__ReferredLocator__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2562:1: ( rule__ReferredLocator__Group_0__1__Impl )
            // InternalIdioms.g:2563:2: rule__ReferredLocator__Group_0__1__Impl
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
    // InternalIdioms.g:2569:1: rule__ReferredLocator__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredLocator__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2573:1: ( ( '::' ) )
            // InternalIdioms.g:2574:1: ( '::' )
            {
            // InternalIdioms.g:2574:1: ( '::' )
            // InternalIdioms.g:2575:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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


    // $ANTLR start "rule__SegmentDeclaration__Group__0"
    // InternalIdioms.g:2585:1: rule__SegmentDeclaration__Group__0 : rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 ;
    public final void rule__SegmentDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2589:1: ( rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 )
            // InternalIdioms.g:2590:2: rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1
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
    // InternalIdioms.g:2597:1: rule__SegmentDeclaration__Group__0__Impl : ( 'segment' ) ;
    public final void rule__SegmentDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2601:1: ( ( 'segment' ) )
            // InternalIdioms.g:2602:1: ( 'segment' )
            {
            // InternalIdioms.g:2602:1: ( 'segment' )
            // InternalIdioms.g:2603:2: 'segment'
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
    // InternalIdioms.g:2612:1: rule__SegmentDeclaration__Group__1 : rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 ;
    public final void rule__SegmentDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2616:1: ( rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 )
            // InternalIdioms.g:2617:2: rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_19);
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
    // InternalIdioms.g:2624:1: rule__SegmentDeclaration__Group__1__Impl : ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) ;
    public final void rule__SegmentDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2628:1: ( ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:2629:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:2629:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:2630:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:2631:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            // InternalIdioms.g:2631:3: rule__SegmentDeclaration__NameAssignment_1
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
    // InternalIdioms.g:2639:1: rule__SegmentDeclaration__Group__2 : rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 ;
    public final void rule__SegmentDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2643:1: ( rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 )
            // InternalIdioms.g:2644:2: rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3
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
    // InternalIdioms.g:2651:1: rule__SegmentDeclaration__Group__2__Impl : ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) ;
    public final void rule__SegmentDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2655:1: ( ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) )
            // InternalIdioms.g:2656:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            {
            // InternalIdioms.g:2656:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            // InternalIdioms.g:2657:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2());
            }
            // InternalIdioms.g:2658:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            // InternalIdioms.g:2658:3: rule__SegmentDeclaration__OwnedSegmentAssignment_2
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
    // InternalIdioms.g:2666:1: rule__SegmentDeclaration__Group__3 : rule__SegmentDeclaration__Group__3__Impl ;
    public final void rule__SegmentDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2670:1: ( rule__SegmentDeclaration__Group__3__Impl )
            // InternalIdioms.g:2671:2: rule__SegmentDeclaration__Group__3__Impl
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
    // InternalIdioms.g:2677:1: rule__SegmentDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__SegmentDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2681:1: ( ( ';' ) )
            // InternalIdioms.g:2682:1: ( ';' )
            {
            // InternalIdioms.g:2682:1: ( ';' )
            // InternalIdioms.g:2683:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3());
            }
            match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2693:1: rule__CustomSegment__Group__0 : rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 ;
    public final void rule__CustomSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2697:1: ( rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 )
            // InternalIdioms.g:2698:2: rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1
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
    // InternalIdioms.g:2705:1: rule__CustomSegment__Group__0__Impl : ( 'custom' ) ;
    public final void rule__CustomSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2709:1: ( ( 'custom' ) )
            // InternalIdioms.g:2710:1: ( 'custom' )
            {
            // InternalIdioms.g:2710:1: ( 'custom' )
            // InternalIdioms.g:2711:2: 'custom'
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
    // InternalIdioms.g:2720:1: rule__CustomSegment__Group__1 : rule__CustomSegment__Group__1__Impl ;
    public final void rule__CustomSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2724:1: ( rule__CustomSegment__Group__1__Impl )
            // InternalIdioms.g:2725:2: rule__CustomSegment__Group__1__Impl
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
    // InternalIdioms.g:2731:1: rule__CustomSegment__Group__1__Impl : ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) ;
    public final void rule__CustomSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2735:1: ( ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) )
            // InternalIdioms.g:2736:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            {
            // InternalIdioms.g:2736:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            // InternalIdioms.g:2737:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1());
            }
            // InternalIdioms.g:2738:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            // InternalIdioms.g:2738:3: rule__CustomSegment__SupportClassNameAssignment_1
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
    // InternalIdioms.g:2747:1: rule__HalfNewLineSegment__Group__0 : rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 ;
    public final void rule__HalfNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2751:1: ( rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 )
            // InternalIdioms.g:2752:2: rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_20);
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
    // InternalIdioms.g:2759:1: rule__HalfNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__HalfNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2763:1: ( ( () ) )
            // InternalIdioms.g:2764:1: ( () )
            {
            // InternalIdioms.g:2764:1: ( () )
            // InternalIdioms.g:2765:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2766:2: ()
            // InternalIdioms.g:2766:3:
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
    // InternalIdioms.g:2774:1: rule__HalfNewLineSegment__Group__1 : rule__HalfNewLineSegment__Group__1__Impl ;
    public final void rule__HalfNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2778:1: ( rule__HalfNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:2779:2: rule__HalfNewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:2785:1: rule__HalfNewLineSegment__Group__1__Impl : ( 'half-new-line' ) ;
    public final void rule__HalfNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2789:1: ( ( 'half-new-line' ) )
            // InternalIdioms.g:2790:1: ( 'half-new-line' )
            {
            // InternalIdioms.g:2790:1: ( 'half-new-line' )
            // InternalIdioms.g:2791:2: 'half-new-line'
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
    // InternalIdioms.g:2801:1: rule__NewLineSegment__Group__0 : rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 ;
    public final void rule__NewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2805:1: ( rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 )
            // InternalIdioms.g:2806:2: rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_21);
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
    // InternalIdioms.g:2813:1: rule__NewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__NewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2817:1: ( ( () ) )
            // InternalIdioms.g:2818:1: ( () )
            {
            // InternalIdioms.g:2818:1: ( () )
            // InternalIdioms.g:2819:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2820:2: ()
            // InternalIdioms.g:2820:3:
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
    // InternalIdioms.g:2828:1: rule__NewLineSegment__Group__1 : rule__NewLineSegment__Group__1__Impl ;
    public final void rule__NewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2832:1: ( rule__NewLineSegment__Group__1__Impl )
            // InternalIdioms.g:2833:2: rule__NewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:2839:1: rule__NewLineSegment__Group__1__Impl : ( 'new-line' ) ;
    public final void rule__NewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2843:1: ( ( 'new-line' ) )
            // InternalIdioms.g:2844:1: ( 'new-line' )
            {
            // InternalIdioms.g:2844:1: ( 'new-line' )
            // InternalIdioms.g:2845:2: 'new-line'
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
    // InternalIdioms.g:2855:1: rule__NoSpaceSegment__Group__0 : rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 ;
    public final void rule__NoSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2859:1: ( rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 )
            // InternalIdioms.g:2860:2: rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_22);
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
    // InternalIdioms.g:2867:1: rule__NoSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__NoSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2871:1: ( ( () ) )
            // InternalIdioms.g:2872:1: ( () )
            {
            // InternalIdioms.g:2872:1: ( () )
            // InternalIdioms.g:2873:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0());
            }
            // InternalIdioms.g:2874:2: ()
            // InternalIdioms.g:2874:3:
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
    // InternalIdioms.g:2882:1: rule__NoSpaceSegment__Group__1 : rule__NoSpaceSegment__Group__1__Impl ;
    public final void rule__NoSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2886:1: ( rule__NoSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:2887:2: rule__NoSpaceSegment__Group__1__Impl
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
    // InternalIdioms.g:2893:1: rule__NoSpaceSegment__Group__1__Impl : ( 'no-space' ) ;
    public final void rule__NoSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2897:1: ( ( 'no-space' ) )
            // InternalIdioms.g:2898:1: ( 'no-space' )
            {
            // InternalIdioms.g:2898:1: ( 'no-space' )
            // InternalIdioms.g:2899:2: 'no-space'
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
    // InternalIdioms.g:2909:1: rule__PopSegment__Group__0 : rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 ;
    public final void rule__PopSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2913:1: ( rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 )
            // InternalIdioms.g:2914:2: rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_23);
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
    // InternalIdioms.g:2921:1: rule__PopSegment__Group__0__Impl : ( () ) ;
    public final void rule__PopSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2925:1: ( ( () ) )
            // InternalIdioms.g:2926:1: ( () )
            {
            // InternalIdioms.g:2926:1: ( () )
            // InternalIdioms.g:2927:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0());
            }
            // InternalIdioms.g:2928:2: ()
            // InternalIdioms.g:2928:3:
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
    // InternalIdioms.g:2936:1: rule__PopSegment__Group__1 : rule__PopSegment__Group__1__Impl ;
    public final void rule__PopSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2940:1: ( rule__PopSegment__Group__1__Impl )
            // InternalIdioms.g:2941:2: rule__PopSegment__Group__1__Impl
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
    // InternalIdioms.g:2947:1: rule__PopSegment__Group__1__Impl : ( 'pop' ) ;
    public final void rule__PopSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2951:1: ( ( 'pop' ) )
            // InternalIdioms.g:2952:1: ( 'pop' )
            {
            // InternalIdioms.g:2952:1: ( 'pop' )
            // InternalIdioms.g:2953:2: 'pop'
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
    // InternalIdioms.g:2963:1: rule__PostCommentSegment__Group__0 : rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 ;
    public final void rule__PostCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2967:1: ( rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 )
            // InternalIdioms.g:2968:2: rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_24);
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
    // InternalIdioms.g:2975:1: rule__PostCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PostCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2979:1: ( ( () ) )
            // InternalIdioms.g:2980:1: ( () )
            {
            // InternalIdioms.g:2980:1: ( () )
            // InternalIdioms.g:2981:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0());
            }
            // InternalIdioms.g:2982:2: ()
            // InternalIdioms.g:2982:3:
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
    // InternalIdioms.g:2990:1: rule__PostCommentSegment__Group__1 : rule__PostCommentSegment__Group__1__Impl ;
    public final void rule__PostCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2994:1: ( rule__PostCommentSegment__Group__1__Impl )
            // InternalIdioms.g:2995:2: rule__PostCommentSegment__Group__1__Impl
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
    // InternalIdioms.g:3001:1: rule__PostCommentSegment__Group__1__Impl : ( 'post-comment' ) ;
    public final void rule__PostCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3005:1: ( ( 'post-comment' ) )
            // InternalIdioms.g:3006:1: ( 'post-comment' )
            {
            // InternalIdioms.g:3006:1: ( 'post-comment' )
            // InternalIdioms.g:3007:2: 'post-comment'
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
    // InternalIdioms.g:3017:1: rule__PreCommentSegment__Group__0 : rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 ;
    public final void rule__PreCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3021:1: ( rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 )
            // InternalIdioms.g:3022:2: rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_25);
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
    // InternalIdioms.g:3029:1: rule__PreCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PreCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3033:1: ( ( () ) )
            // InternalIdioms.g:3034:1: ( () )
            {
            // InternalIdioms.g:3034:1: ( () )
            // InternalIdioms.g:3035:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0());
            }
            // InternalIdioms.g:3036:2: ()
            // InternalIdioms.g:3036:3:
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
    // InternalIdioms.g:3044:1: rule__PreCommentSegment__Group__1 : rule__PreCommentSegment__Group__1__Impl ;
    public final void rule__PreCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3048:1: ( rule__PreCommentSegment__Group__1__Impl )
            // InternalIdioms.g:3049:2: rule__PreCommentSegment__Group__1__Impl
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
    // InternalIdioms.g:3055:1: rule__PreCommentSegment__Group__1__Impl : ( 'pre-comment' ) ;
    public final void rule__PreCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3059:1: ( ( 'pre-comment' ) )
            // InternalIdioms.g:3060:1: ( 'pre-comment' )
            {
            // InternalIdioms.g:3060:1: ( 'pre-comment' )
            // InternalIdioms.g:3061:2: 'pre-comment'
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
    // InternalIdioms.g:3071:1: rule__PushSegment__Group__0 : rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 ;
    public final void rule__PushSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3075:1: ( rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 )
            // InternalIdioms.g:3076:2: rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_26);
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
    // InternalIdioms.g:3083:1: rule__PushSegment__Group__0__Impl : ( () ) ;
    public final void rule__PushSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3087:1: ( ( () ) )
            // InternalIdioms.g:3088:1: ( () )
            {
            // InternalIdioms.g:3088:1: ( () )
            // InternalIdioms.g:3089:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0());
            }
            // InternalIdioms.g:3090:2: ()
            // InternalIdioms.g:3090:3:
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
    // InternalIdioms.g:3098:1: rule__PushSegment__Group__1 : rule__PushSegment__Group__1__Impl ;
    public final void rule__PushSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3102:1: ( rule__PushSegment__Group__1__Impl )
            // InternalIdioms.g:3103:2: rule__PushSegment__Group__1__Impl
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
    // InternalIdioms.g:3109:1: rule__PushSegment__Group__1__Impl : ( 'push' ) ;
    public final void rule__PushSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3113:1: ( ( 'push' ) )
            // InternalIdioms.g:3114:1: ( 'push' )
            {
            // InternalIdioms.g:3114:1: ( 'push' )
            // InternalIdioms.g:3115:2: 'push'
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
    // InternalIdioms.g:3125:1: rule__SoftNewLineSegment__Group__0 : rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 ;
    public final void rule__SoftNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3129:1: ( rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 )
            // InternalIdioms.g:3130:2: rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_27);
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
    // InternalIdioms.g:3137:1: rule__SoftNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3141:1: ( ( () ) )
            // InternalIdioms.g:3142:1: ( () )
            {
            // InternalIdioms.g:3142:1: ( () )
            // InternalIdioms.g:3143:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0());
            }
            // InternalIdioms.g:3144:2: ()
            // InternalIdioms.g:3144:3:
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
    // InternalIdioms.g:3152:1: rule__SoftNewLineSegment__Group__1 : rule__SoftNewLineSegment__Group__1__Impl ;
    public final void rule__SoftNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3156:1: ( rule__SoftNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:3157:2: rule__SoftNewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:3163:1: rule__SoftNewLineSegment__Group__1__Impl : ( 'soft-new-line' ) ;
    public final void rule__SoftNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3167:1: ( ( 'soft-new-line' ) )
            // InternalIdioms.g:3168:1: ( 'soft-new-line' )
            {
            // InternalIdioms.g:3168:1: ( 'soft-new-line' )
            // InternalIdioms.g:3169:2: 'soft-new-line'
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
    // InternalIdioms.g:3179:1: rule__SoftSpaceSegment__Group__0 : rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 ;
    public final void rule__SoftSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3183:1: ( rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 )
            // InternalIdioms.g:3184:2: rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_28);
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
    // InternalIdioms.g:3191:1: rule__SoftSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3195:1: ( ( () ) )
            // InternalIdioms.g:3196:1: ( () )
            {
            // InternalIdioms.g:3196:1: ( () )
            // InternalIdioms.g:3197:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0());
            }
            // InternalIdioms.g:3198:2: ()
            // InternalIdioms.g:3198:3:
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
    // InternalIdioms.g:3206:1: rule__SoftSpaceSegment__Group__1 : rule__SoftSpaceSegment__Group__1__Impl ;
    public final void rule__SoftSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3210:1: ( rule__SoftSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:3211:2: rule__SoftSpaceSegment__Group__1__Impl
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
    // InternalIdioms.g:3217:1: rule__SoftSpaceSegment__Group__1__Impl : ( 'soft-space' ) ;
    public final void rule__SoftSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3221:1: ( ( 'soft-space' ) )
            // InternalIdioms.g:3222:1: ( 'soft-space' )
            {
            // InternalIdioms.g:3222:1: ( 'soft-space' )
            // InternalIdioms.g:3223:2: 'soft-space'
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
    // InternalIdioms.g:3233:1: rule__StringSegment__Group__0 : rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 ;
    public final void rule__StringSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3237:1: ( rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 )
            // InternalIdioms.g:3238:2: rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1
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
    // InternalIdioms.g:3245:1: rule__StringSegment__Group__0__Impl : ( 'string' ) ;
    public final void rule__StringSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3249:1: ( ( 'string' ) )
            // InternalIdioms.g:3250:1: ( 'string' )
            {
            // InternalIdioms.g:3250:1: ( 'string' )
            // InternalIdioms.g:3251:2: 'string'
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
    // InternalIdioms.g:3260:1: rule__StringSegment__Group__1 : rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 ;
    public final void rule__StringSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3264:1: ( rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 )
            // InternalIdioms.g:3265:2: rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_29);
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
    // InternalIdioms.g:3272:1: rule__StringSegment__Group__1__Impl : ( ( rule__StringSegment__StringAssignment_1 ) ) ;
    public final void rule__StringSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3276:1: ( ( ( rule__StringSegment__StringAssignment_1 ) ) )
            // InternalIdioms.g:3277:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            {
            // InternalIdioms.g:3277:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            // InternalIdioms.g:3278:2: ( rule__StringSegment__StringAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringAssignment_1());
            }
            // InternalIdioms.g:3279:2: ( rule__StringSegment__StringAssignment_1 )
            // InternalIdioms.g:3279:3: rule__StringSegment__StringAssignment_1
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
    // InternalIdioms.g:3287:1: rule__StringSegment__Group__2 : rule__StringSegment__Group__2__Impl ;
    public final void rule__StringSegment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3291:1: ( rule__StringSegment__Group__2__Impl )
            // InternalIdioms.g:3292:2: rule__StringSegment__Group__2__Impl
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
    // InternalIdioms.g:3298:1: rule__StringSegment__Group__2__Impl : ( ( rule__StringSegment__PrintableAssignment_2 ) ) ;
    public final void rule__StringSegment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3302:1: ( ( ( rule__StringSegment__PrintableAssignment_2 ) ) )
            // InternalIdioms.g:3303:1: ( ( rule__StringSegment__PrintableAssignment_2 ) )
            {
            // InternalIdioms.g:3303:1: ( ( rule__StringSegment__PrintableAssignment_2 ) )
            // InternalIdioms.g:3304:2: ( rule__StringSegment__PrintableAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2());
            }
            // InternalIdioms.g:3305:2: ( rule__StringSegment__PrintableAssignment_2 )
            // InternalIdioms.g:3305:3: rule__StringSegment__PrintableAssignment_2
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__StringSegment__PrintableAssignment_2();

            state._fsp--;
            if (state.failed) return ;

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
    // InternalIdioms.g:3314:1: rule__ValueSegment__Group__0 : rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 ;
    public final void rule__ValueSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3318:1: ( rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 )
            // InternalIdioms.g:3319:2: rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_30);
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
    // InternalIdioms.g:3326:1: rule__ValueSegment__Group__0__Impl : ( () ) ;
    public final void rule__ValueSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3330:1: ( ( () ) )
            // InternalIdioms.g:3331:1: ( () )
            {
            // InternalIdioms.g:3331:1: ( () )
            // InternalIdioms.g:3332:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0());
            }
            // InternalIdioms.g:3333:2: ()
            // InternalIdioms.g:3333:3:
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
    // InternalIdioms.g:3341:1: rule__ValueSegment__Group__1 : rule__ValueSegment__Group__1__Impl ;
    public final void rule__ValueSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3345:1: ( rule__ValueSegment__Group__1__Impl )
            // InternalIdioms.g:3346:2: rule__ValueSegment__Group__1__Impl
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
    // InternalIdioms.g:3352:1: rule__ValueSegment__Group__1__Impl : ( 'value' ) ;
    public final void rule__ValueSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3356:1: ( ( 'value' ) )
            // InternalIdioms.g:3357:1: ( 'value' )
            {
            // InternalIdioms.g:3357:1: ( 'value' )
            // InternalIdioms.g:3358:2: 'value'
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
    // InternalIdioms.g:3368:1: rule__WrapAnchorSegment__Group__0 : rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 ;
    public final void rule__WrapAnchorSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3372:1: ( rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 )
            // InternalIdioms.g:3373:2: rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_31);
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
    // InternalIdioms.g:3380:1: rule__WrapAnchorSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapAnchorSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3384:1: ( ( () ) )
            // InternalIdioms.g:3385:1: ( () )
            {
            // InternalIdioms.g:3385:1: ( () )
            // InternalIdioms.g:3386:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0());
            }
            // InternalIdioms.g:3387:2: ()
            // InternalIdioms.g:3387:3:
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
    // InternalIdioms.g:3395:1: rule__WrapAnchorSegment__Group__1 : rule__WrapAnchorSegment__Group__1__Impl ;
    public final void rule__WrapAnchorSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3399:1: ( rule__WrapAnchorSegment__Group__1__Impl )
            // InternalIdioms.g:3400:2: rule__WrapAnchorSegment__Group__1__Impl
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
    // InternalIdioms.g:3406:1: rule__WrapAnchorSegment__Group__1__Impl : ( 'wrap-anchor' ) ;
    public final void rule__WrapAnchorSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3410:1: ( ( 'wrap-anchor' ) )
            // InternalIdioms.g:3411:1: ( 'wrap-anchor' )
            {
            // InternalIdioms.g:3411:1: ( 'wrap-anchor' )
            // InternalIdioms.g:3412:2: 'wrap-anchor'
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
    // InternalIdioms.g:3422:1: rule__WrapBeginAllSegment__Group__0 : rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 ;
    public final void rule__WrapBeginAllSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3426:1: ( rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 )
            // InternalIdioms.g:3427:2: rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_32);
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
    // InternalIdioms.g:3434:1: rule__WrapBeginAllSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginAllSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3438:1: ( ( () ) )
            // InternalIdioms.g:3439:1: ( () )
            {
            // InternalIdioms.g:3439:1: ( () )
            // InternalIdioms.g:3440:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0());
            }
            // InternalIdioms.g:3441:2: ()
            // InternalIdioms.g:3441:3:
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
    // InternalIdioms.g:3449:1: rule__WrapBeginAllSegment__Group__1 : rule__WrapBeginAllSegment__Group__1__Impl ;
    public final void rule__WrapBeginAllSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3453:1: ( rule__WrapBeginAllSegment__Group__1__Impl )
            // InternalIdioms.g:3454:2: rule__WrapBeginAllSegment__Group__1__Impl
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
    // InternalIdioms.g:3460:1: rule__WrapBeginAllSegment__Group__1__Impl : ( 'wrap-begin-all' ) ;
    public final void rule__WrapBeginAllSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3464:1: ( ( 'wrap-begin-all' ) )
            // InternalIdioms.g:3465:1: ( 'wrap-begin-all' )
            {
            // InternalIdioms.g:3465:1: ( 'wrap-begin-all' )
            // InternalIdioms.g:3466:2: 'wrap-begin-all'
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
    // InternalIdioms.g:3476:1: rule__WrapBeginSomeSegment__Group__0 : rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 ;
    public final void rule__WrapBeginSomeSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3480:1: ( rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 )
            // InternalIdioms.g:3481:2: rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_33);
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
    // InternalIdioms.g:3488:1: rule__WrapBeginSomeSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginSomeSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3492:1: ( ( () ) )
            // InternalIdioms.g:3493:1: ( () )
            {
            // InternalIdioms.g:3493:1: ( () )
            // InternalIdioms.g:3494:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0());
            }
            // InternalIdioms.g:3495:2: ()
            // InternalIdioms.g:3495:3:
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
    // InternalIdioms.g:3503:1: rule__WrapBeginSomeSegment__Group__1 : rule__WrapBeginSomeSegment__Group__1__Impl ;
    public final void rule__WrapBeginSomeSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3507:1: ( rule__WrapBeginSomeSegment__Group__1__Impl )
            // InternalIdioms.g:3508:2: rule__WrapBeginSomeSegment__Group__1__Impl
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
    // InternalIdioms.g:3514:1: rule__WrapBeginSomeSegment__Group__1__Impl : ( 'wrap-begin-some' ) ;
    public final void rule__WrapBeginSomeSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3518:1: ( ( 'wrap-begin-some' ) )
            // InternalIdioms.g:3519:1: ( 'wrap-begin-some' )
            {
            // InternalIdioms.g:3519:1: ( 'wrap-begin-some' )
            // InternalIdioms.g:3520:2: 'wrap-begin-some'
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
    // InternalIdioms.g:3530:1: rule__WrapEndSegment__Group__0 : rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 ;
    public final void rule__WrapEndSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3534:1: ( rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 )
            // InternalIdioms.g:3535:2: rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_34);
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
    // InternalIdioms.g:3542:1: rule__WrapEndSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapEndSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3546:1: ( ( () ) )
            // InternalIdioms.g:3547:1: ( () )
            {
            // InternalIdioms.g:3547:1: ( () )
            // InternalIdioms.g:3548:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0());
            }
            // InternalIdioms.g:3549:2: ()
            // InternalIdioms.g:3549:3:
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
    // InternalIdioms.g:3557:1: rule__WrapEndSegment__Group__1 : rule__WrapEndSegment__Group__1__Impl ;
    public final void rule__WrapEndSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3561:1: ( rule__WrapEndSegment__Group__1__Impl )
            // InternalIdioms.g:3562:2: rule__WrapEndSegment__Group__1__Impl
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
    // InternalIdioms.g:3568:1: rule__WrapEndSegment__Group__1__Impl : ( 'wrap-end' ) ;
    public final void rule__WrapEndSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3572:1: ( ( 'wrap-end' ) )
            // InternalIdioms.g:3573:1: ( 'wrap-end' )
            {
            // InternalIdioms.g:3573:1: ( 'wrap-end' )
            // InternalIdioms.g:3574:2: 'wrap-end'
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
    // InternalIdioms.g:3584:1: rule__WrapHereSegment__Group__0 : rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 ;
    public final void rule__WrapHereSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3588:1: ( rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 )
            // InternalIdioms.g:3589:2: rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_19);
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
    // InternalIdioms.g:3596:1: rule__WrapHereSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapHereSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3600:1: ( ( () ) )
            // InternalIdioms.g:3601:1: ( () )
            {
            // InternalIdioms.g:3601:1: ( () )
            // InternalIdioms.g:3602:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0());
            }
            // InternalIdioms.g:3603:2: ()
            // InternalIdioms.g:3603:3:
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
    // InternalIdioms.g:3611:1: rule__WrapHereSegment__Group__1 : rule__WrapHereSegment__Group__1__Impl ;
    public final void rule__WrapHereSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3615:1: ( rule__WrapHereSegment__Group__1__Impl )
            // InternalIdioms.g:3616:2: rule__WrapHereSegment__Group__1__Impl
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
    // InternalIdioms.g:3622:1: rule__WrapHereSegment__Group__1__Impl : ( 'wrap-here' ) ;
    public final void rule__WrapHereSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3626:1: ( ( 'wrap-here' ) )
            // InternalIdioms.g:3627:1: ( 'wrap-here' )
            {
            // InternalIdioms.g:3627:1: ( 'wrap-here' )
            // InternalIdioms.g:3628:2: 'wrap-here'
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
    // InternalIdioms.g:3638:1: rule__ReferredSegment__Group__0 : rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 ;
    public final void rule__ReferredSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3642:1: ( rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 )
            // InternalIdioms.g:3643:2: rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1
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
    // InternalIdioms.g:3650:1: rule__ReferredSegment__Group__0__Impl : ( ( rule__ReferredSegment__Group_0__0 )? ) ;
    public final void rule__ReferredSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3654:1: ( ( ( rule__ReferredSegment__Group_0__0 )? ) )
            // InternalIdioms.g:3655:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            {
            // InternalIdioms.g:3655:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            // InternalIdioms.g:3656:2: ( rule__ReferredSegment__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getGroup_0());
            }
            // InternalIdioms.g:3657:2: ( rule__ReferredSegment__Group_0__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ID) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==21) ) {
                    alt20=1;
                }
            }
            switch (alt20) {
                case 1 :
                    // InternalIdioms.g:3657:3: rule__ReferredSegment__Group_0__0
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
    // InternalIdioms.g:3665:1: rule__ReferredSegment__Group__1 : rule__ReferredSegment__Group__1__Impl ;
    public final void rule__ReferredSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3669:1: ( rule__ReferredSegment__Group__1__Impl )
            // InternalIdioms.g:3670:2: rule__ReferredSegment__Group__1__Impl
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
    // InternalIdioms.g:3676:1: rule__ReferredSegment__Group__1__Impl : ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3680:1: ( ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:3681:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:3681:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            // InternalIdioms.g:3682:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1());
            }
            // InternalIdioms.g:3683:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            // InternalIdioms.g:3683:3: rule__ReferredSegment__SegmentDeclarationAssignment_1
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
    // InternalIdioms.g:3692:1: rule__ReferredSegment__Group_0__0 : rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 ;
    public final void rule__ReferredSegment__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3696:1: ( rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 )
            // InternalIdioms.g:3697:2: rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1
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
    // InternalIdioms.g:3704:1: rule__ReferredSegment__Group_0__0__Impl : ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredSegment__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3708:1: ( ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:3709:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:3709:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:3710:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:3711:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:3711:3: rule__ReferredSegment__IdiomsModelAssignment_0_0
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
    // InternalIdioms.g:3719:1: rule__ReferredSegment__Group_0__1 : rule__ReferredSegment__Group_0__1__Impl ;
    public final void rule__ReferredSegment__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3723:1: ( rule__ReferredSegment__Group_0__1__Impl )
            // InternalIdioms.g:3724:2: rule__ReferredSegment__Group_0__1__Impl
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
    // InternalIdioms.g:3730:1: rule__ReferredSegment__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredSegment__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3734:1: ( ( '::' ) )
            // InternalIdioms.g:3735:1: ( '::' )
            {
            // InternalIdioms.g:3735:1: ( '::' )
            // InternalIdioms.g:3736:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3746:1: rule__Idiom__Group__0 : rule__Idiom__Group__0__Impl rule__Idiom__Group__1 ;
    public final void rule__Idiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3750:1: ( rule__Idiom__Group__0__Impl rule__Idiom__Group__1 )
            // InternalIdioms.g:3751:2: rule__Idiom__Group__0__Impl rule__Idiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_35);
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
    // InternalIdioms.g:3758:1: rule__Idiom__Group__0__Impl : ( ( rule__Idiom__MixinAssignment_0 )? ) ;
    public final void rule__Idiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3762:1: ( ( ( rule__Idiom__MixinAssignment_0 )? ) )
            // InternalIdioms.g:3763:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            {
            // InternalIdioms.g:3763:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            // InternalIdioms.g:3764:2: ( rule__Idiom__MixinAssignment_0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinAssignment_0());
            }
            // InternalIdioms.g:3765:2: ( rule__Idiom__MixinAssignment_0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==51) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalIdioms.g:3765:3: rule__Idiom__MixinAssignment_0
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
    // InternalIdioms.g:3773:1: rule__Idiom__Group__1 : rule__Idiom__Group__1__Impl rule__Idiom__Group__2 ;
    public final void rule__Idiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3777:1: ( rule__Idiom__Group__1__Impl rule__Idiom__Group__2 )
            // InternalIdioms.g:3778:2: rule__Idiom__Group__1__Impl rule__Idiom__Group__2
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
    // InternalIdioms.g:3785:1: rule__Idiom__Group__1__Impl : ( 'idiom' ) ;
    public final void rule__Idiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3789:1: ( ( 'idiom' ) )
            // InternalIdioms.g:3790:1: ( 'idiom' )
            {
            // InternalIdioms.g:3790:1: ( 'idiom' )
            // InternalIdioms.g:3791:2: 'idiom'
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
    // InternalIdioms.g:3800:1: rule__Idiom__Group__2 : rule__Idiom__Group__2__Impl rule__Idiom__Group__3 ;
    public final void rule__Idiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3804:1: ( rule__Idiom__Group__2__Impl rule__Idiom__Group__3 )
            // InternalIdioms.g:3805:2: rule__Idiom__Group__2__Impl rule__Idiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_36);
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
    // InternalIdioms.g:3812:1: rule__Idiom__Group__2__Impl : ( ( rule__Idiom__NameAssignment_2 ) ) ;
    public final void rule__Idiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3816:1: ( ( ( rule__Idiom__NameAssignment_2 ) ) )
            // InternalIdioms.g:3817:1: ( ( rule__Idiom__NameAssignment_2 ) )
            {
            // InternalIdioms.g:3817:1: ( ( rule__Idiom__NameAssignment_2 ) )
            // InternalIdioms.g:3818:2: ( rule__Idiom__NameAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getNameAssignment_2());
            }
            // InternalIdioms.g:3819:2: ( rule__Idiom__NameAssignment_2 )
            // InternalIdioms.g:3819:3: rule__Idiom__NameAssignment_2
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
    // InternalIdioms.g:3827:1: rule__Idiom__Group__3 : rule__Idiom__Group__3__Impl rule__Idiom__Group__4 ;
    public final void rule__Idiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3831:1: ( rule__Idiom__Group__3__Impl rule__Idiom__Group__4 )
            // InternalIdioms.g:3832:2: rule__Idiom__Group__3__Impl rule__Idiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_36);
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
    // InternalIdioms.g:3839:1: rule__Idiom__Group__3__Impl : ( ( rule__Idiom__Group_3__0 )? ) ;
    public final void rule__Idiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3843:1: ( ( ( rule__Idiom__Group_3__0 )? ) )
            // InternalIdioms.g:3844:1: ( ( rule__Idiom__Group_3__0 )? )
            {
            // InternalIdioms.g:3844:1: ( ( rule__Idiom__Group_3__0 )? )
            // InternalIdioms.g:3845:2: ( rule__Idiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:3846:2: ( rule__Idiom__Group_3__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==46) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalIdioms.g:3846:3: rule__Idiom__Group_3__0
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
    // InternalIdioms.g:3854:1: rule__Idiom__Group__4 : rule__Idiom__Group__4__Impl rule__Idiom__Group__5 ;
    public final void rule__Idiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3858:1: ( rule__Idiom__Group__4__Impl rule__Idiom__Group__5 )
            // InternalIdioms.g:3859:2: rule__Idiom__Group__4__Impl rule__Idiom__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_36);
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
    // InternalIdioms.g:3866:1: rule__Idiom__Group__4__Impl : ( ( rule__Idiom__Group_4__0 )? ) ;
    public final void rule__Idiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3870:1: ( ( ( rule__Idiom__Group_4__0 )? ) )
            // InternalIdioms.g:3871:1: ( ( rule__Idiom__Group_4__0 )? )
            {
            // InternalIdioms.g:3871:1: ( ( rule__Idiom__Group_4__0 )? )
            // InternalIdioms.g:3872:2: ( rule__Idiom__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_4());
            }
            // InternalIdioms.g:3873:2: ( rule__Idiom__Group_4__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==47) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalIdioms.g:3873:3: rule__Idiom__Group_4__0
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
    // InternalIdioms.g:3881:1: rule__Idiom__Group__5 : rule__Idiom__Group__5__Impl ;
    public final void rule__Idiom__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3885:1: ( rule__Idiom__Group__5__Impl )
            // InternalIdioms.g:3886:2: rule__Idiom__Group__5__Impl
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
    // InternalIdioms.g:3892:1: rule__Idiom__Group__5__Impl : ( ( rule__Idiom__Alternatives_5 ) ) ;
    public final void rule__Idiom__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3896:1: ( ( ( rule__Idiom__Alternatives_5 ) ) )
            // InternalIdioms.g:3897:1: ( ( rule__Idiom__Alternatives_5 ) )
            {
            // InternalIdioms.g:3897:1: ( ( rule__Idiom__Alternatives_5 ) )
            // InternalIdioms.g:3898:2: ( rule__Idiom__Alternatives_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getAlternatives_5());
            }
            // InternalIdioms.g:3899:2: ( rule__Idiom__Alternatives_5 )
            // InternalIdioms.g:3899:3: rule__Idiom__Alternatives_5
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
    // InternalIdioms.g:3908:1: rule__Idiom__Group_3__0 : rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 ;
    public final void rule__Idiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3912:1: ( rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 )
            // InternalIdioms.g:3913:2: rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1
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
    // InternalIdioms.g:3920:1: rule__Idiom__Group_3__0__Impl : ( 'for' ) ;
    public final void rule__Idiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3924:1: ( ( 'for' ) )
            // InternalIdioms.g:3925:1: ( 'for' )
            {
            // InternalIdioms.g:3925:1: ( 'for' )
            // InternalIdioms.g:3926:2: 'for'
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
    // InternalIdioms.g:3935:1: rule__Idiom__Group_3__1 : rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 ;
    public final void rule__Idiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3939:1: ( rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 )
            // InternalIdioms.g:3940:2: rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2
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
    // InternalIdioms.g:3947:1: rule__Idiom__Group_3__1__Impl : ( ( rule__Idiom__Group_3_1__0 )? ) ;
    public final void rule__Idiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3951:1: ( ( ( rule__Idiom__Group_3_1__0 )? ) )
            // InternalIdioms.g:3952:1: ( ( rule__Idiom__Group_3_1__0 )? )
            {
            // InternalIdioms.g:3952:1: ( ( rule__Idiom__Group_3_1__0 )? )
            // InternalIdioms.g:3953:2: ( rule__Idiom__Group_3_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3_1());
            }
            // InternalIdioms.g:3954:2: ( rule__Idiom__Group_3_1__0 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_ID) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==21) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // InternalIdioms.g:3954:3: rule__Idiom__Group_3_1__0
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
    // InternalIdioms.g:3962:1: rule__Idiom__Group_3__2 : rule__Idiom__Group_3__2__Impl ;
    public final void rule__Idiom__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3966:1: ( rule__Idiom__Group_3__2__Impl )
            // InternalIdioms.g:3967:2: rule__Idiom__Group_3__2__Impl
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
    // InternalIdioms.g:3973:1: rule__Idiom__Group_3__2__Impl : ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) ;
    public final void rule__Idiom__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3977:1: ( ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) )
            // InternalIdioms.g:3978:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            {
            // InternalIdioms.g:3978:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            // InternalIdioms.g:3979:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2());
            }
            // InternalIdioms.g:3980:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            // InternalIdioms.g:3980:3: rule__Idiom__ForEClassAssignment_3_2
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
    // InternalIdioms.g:3989:1: rule__Idiom__Group_3_1__0 : rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 ;
    public final void rule__Idiom__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3993:1: ( rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 )
            // InternalIdioms.g:3994:2: rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1
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
    // InternalIdioms.g:4001:1: rule__Idiom__Group_3_1__0__Impl : ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) ;
    public final void rule__Idiom__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4005:1: ( ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) )
            // InternalIdioms.g:4006:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            {
            // InternalIdioms.g:4006:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            // InternalIdioms.g:4007:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0());
            }
            // InternalIdioms.g:4008:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            // InternalIdioms.g:4008:3: rule__Idiom__ForEPackageAssignment_3_1_0
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
    // InternalIdioms.g:4016:1: rule__Idiom__Group_3_1__1 : rule__Idiom__Group_3_1__1__Impl ;
    public final void rule__Idiom__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4020:1: ( rule__Idiom__Group_3_1__1__Impl )
            // InternalIdioms.g:4021:2: rule__Idiom__Group_3_1__1__Impl
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
    // InternalIdioms.g:4027:1: rule__Idiom__Group_3_1__1__Impl : ( '::' ) ;
    public final void rule__Idiom__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4031:1: ( ( '::' ) )
            // InternalIdioms.g:4032:1: ( '::' )
            {
            // InternalIdioms.g:4032:1: ( '::' )
            // InternalIdioms.g:4033:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4043:1: rule__Idiom__Group_4__0 : rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 ;
    public final void rule__Idiom__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4047:1: ( rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 )
            // InternalIdioms.g:4048:2: rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1
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
    // InternalIdioms.g:4055:1: rule__Idiom__Group_4__0__Impl : ( 'in' ) ;
    public final void rule__Idiom__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4059:1: ( ( 'in' ) )
            // InternalIdioms.g:4060:1: ( 'in' )
            {
            // InternalIdioms.g:4060:1: ( 'in' )
            // InternalIdioms.g:4061:2: 'in'
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
    // InternalIdioms.g:4070:1: rule__Idiom__Group_4__1 : rule__Idiom__Group_4__1__Impl ;
    public final void rule__Idiom__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4074:1: ( rule__Idiom__Group_4__1__Impl )
            // InternalIdioms.g:4075:2: rule__Idiom__Group_4__1__Impl
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
    // InternalIdioms.g:4081:1: rule__Idiom__Group_4__1__Impl : ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) ;
    public final void rule__Idiom__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4085:1: ( ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) )
            // InternalIdioms.g:4086:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            {
            // InternalIdioms.g:4086:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            // InternalIdioms.g:4087:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1());
            }
            // InternalIdioms.g:4088:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            // InternalIdioms.g:4088:3: rule__Idiom__InRuleRegexAssignment_4_1
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
    // InternalIdioms.g:4097:1: rule__Idiom__Group_5_1__0 : rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 ;
    public final void rule__Idiom__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4101:1: ( rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 )
            // InternalIdioms.g:4102:2: rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1
            {
            pushFollow(FollowSets000.FOLLOW_37);
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
    // InternalIdioms.g:4109:1: rule__Idiom__Group_5_1__0__Impl : ( '{' ) ;
    public final void rule__Idiom__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4113:1: ( ( '{' ) )
            // InternalIdioms.g:4114:1: ( '{' )
            {
            // InternalIdioms.g:4114:1: ( '{' )
            // InternalIdioms.g:4115:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4124:1: rule__Idiom__Group_5_1__1 : rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 ;
    public final void rule__Idiom__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4128:1: ( rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 )
            // InternalIdioms.g:4129:2: rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2
            {
            pushFollow(FollowSets000.FOLLOW_37);
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
    // InternalIdioms.g:4136:1: rule__Idiom__Group_5_1__1__Impl : ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) ;
    public final void rule__Idiom__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4140:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) )
            // InternalIdioms.g:4141:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            {
            // InternalIdioms.g:4141:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            // InternalIdioms.g:4142:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1());
            }
            // InternalIdioms.g:4143:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==48) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalIdioms.g:4143:3: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_38);
            	    rule__Idiom__OwnedSubIdiomsAssignment_5_1_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
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
    // InternalIdioms.g:4151:1: rule__Idiom__Group_5_1__2 : rule__Idiom__Group_5_1__2__Impl ;
    public final void rule__Idiom__Group_5_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4155:1: ( rule__Idiom__Group_5_1__2__Impl )
            // InternalIdioms.g:4156:2: rule__Idiom__Group_5_1__2__Impl
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
    // InternalIdioms.g:4162:1: rule__Idiom__Group_5_1__2__Impl : ( '}' ) ;
    public final void rule__Idiom__Group_5_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4166:1: ( ( '}' ) )
            // InternalIdioms.g:4167:1: ( '}' )
            {
            // InternalIdioms.g:4167:1: ( '}' )
            // InternalIdioms.g:4168:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4178:1: rule__SubIdiom__Group__0 : rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 ;
    public final void rule__SubIdiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4182:1: ( rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 )
            // InternalIdioms.g:4183:2: rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_39);
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
    // InternalIdioms.g:4190:1: rule__SubIdiom__Group__0__Impl : ( 'at' ) ;
    public final void rule__SubIdiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4194:1: ( ( 'at' ) )
            // InternalIdioms.g:4195:1: ( 'at' )
            {
            // InternalIdioms.g:4195:1: ( 'at' )
            // InternalIdioms.g:4196:2: 'at'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAtKeyword_0());
            }
            match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4205:1: rule__SubIdiom__Group__1 : rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 ;
    public final void rule__SubIdiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4209:1: ( rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 )
            // InternalIdioms.g:4210:2: rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_39);
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
    // InternalIdioms.g:4217:1: rule__SubIdiom__Group__1__Impl : ( ( rule__SubIdiom__Alternatives_1 )? ) ;
    public final void rule__SubIdiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4221:1: ( ( ( rule__SubIdiom__Alternatives_1 )? ) )
            // InternalIdioms.g:4222:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            {
            // InternalIdioms.g:4222:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            // InternalIdioms.g:4223:2: ( rule__SubIdiom__Alternatives_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAlternatives_1());
            }
            // InternalIdioms.g:4224:2: ( rule__SubIdiom__Alternatives_1 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==11||LA26_0==52) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalIdioms.g:4224:3: rule__SubIdiom__Alternatives_1
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
    // InternalIdioms.g:4232:1: rule__SubIdiom__Group__2 : rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 ;
    public final void rule__SubIdiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4236:1: ( rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 )
            // InternalIdioms.g:4237:2: rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_40);
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
    // InternalIdioms.g:4244:1: rule__SubIdiom__Group__2__Impl : ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__SubIdiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4248:1: ( ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:4249:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:4249:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:4250:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:4251:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:4251:3: rule__SubIdiom__OwnedLocatorAssignment_2
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
    // InternalIdioms.g:4259:1: rule__SubIdiom__Group__3 : rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 ;
    public final void rule__SubIdiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4263:1: ( rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 )
            // InternalIdioms.g:4264:2: rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_40);
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
    // InternalIdioms.g:4271:1: rule__SubIdiom__Group__3__Impl : ( ( rule__SubIdiom__Group_3__0 )? ) ;
    public final void rule__SubIdiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4275:1: ( ( ( rule__SubIdiom__Group_3__0 )? ) )
            // InternalIdioms.g:4276:1: ( ( rule__SubIdiom__Group_3__0 )? )
            {
            // InternalIdioms.g:4276:1: ( ( rule__SubIdiom__Group_3__0 )? )
            // InternalIdioms.g:4277:2: ( rule__SubIdiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:4278:2: ( rule__SubIdiom__Group_3__0 )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==49) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalIdioms.g:4278:3: rule__SubIdiom__Group_3__0
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
    // InternalIdioms.g:4286:1: rule__SubIdiom__Group__4 : rule__SubIdiom__Group__4__Impl ;
    public final void rule__SubIdiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4290:1: ( rule__SubIdiom__Group__4__Impl )
            // InternalIdioms.g:4291:2: rule__SubIdiom__Group__4__Impl
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
    // InternalIdioms.g:4297:1: rule__SubIdiom__Group__4__Impl : ( ';' ) ;
    public final void rule__SubIdiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4301:1: ( ( ';' ) )
            // InternalIdioms.g:4302:1: ( ';' )
            {
            // InternalIdioms.g:4302:1: ( ';' )
            // InternalIdioms.g:4303:2: ';'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4());
            }
            match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4313:1: rule__SubIdiom__Group_3__0 : rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 ;
    public final void rule__SubIdiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4317:1: ( rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 )
            // InternalIdioms.g:4318:2: rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_41);
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
    // InternalIdioms.g:4325:1: rule__SubIdiom__Group_3__0__Impl : ( 'do' ) ;
    public final void rule__SubIdiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4329:1: ( ( 'do' ) )
            // InternalIdioms.g:4330:1: ( 'do' )
            {
            // InternalIdioms.g:4330:1: ( 'do' )
            // InternalIdioms.g:4331:2: 'do'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());
            }
            match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4340:1: rule__SubIdiom__Group_3__1 : rule__SubIdiom__Group_3__1__Impl ;
    public final void rule__SubIdiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4344:1: ( rule__SubIdiom__Group_3__1__Impl )
            // InternalIdioms.g:4345:2: rule__SubIdiom__Group_3__1__Impl
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
    // InternalIdioms.g:4351:1: rule__SubIdiom__Group_3__1__Impl : ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) ;
    public final void rule__SubIdiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4355:1: ( ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) )
            // InternalIdioms.g:4356:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            {
            // InternalIdioms.g:4356:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            // InternalIdioms.g:4357:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1());
            }
            // InternalIdioms.g:4358:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==RULE_ID||(LA28_0>=28 && LA28_0<=44)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalIdioms.g:4358:3: rule__SubIdiom__OwnedSegmentsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_42);
            	    rule__SubIdiom__OwnedSegmentsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop28;
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


    // $ANTLR start "rule__IdiomsModel__NameAssignment_1"
    // InternalIdioms.g:4367:1: rule__IdiomsModel__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__IdiomsModel__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4371:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4372:2: ( RULE_ID )
            {
            // InternalIdioms.g:4372:2: ( RULE_ID )
            // InternalIdioms.g:4373:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNameIDTerminalRuleCall_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getNameIDTerminalRuleCall_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__NameAssignment_1"


    // $ANTLR start "rule__IdiomsModel__OwnedWithsAssignment_2"
    // InternalIdioms.g:4382:1: rule__IdiomsModel__OwnedWithsAssignment_2 : ( ruleIdiomsImport ) ;
    public final void rule__IdiomsModel__OwnedWithsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4386:1: ( ( ruleIdiomsImport ) )
            // InternalIdioms.g:4387:2: ( ruleIdiomsImport )
            {
            // InternalIdioms.g:4387:2: ( ruleIdiomsImport )
            // InternalIdioms.g:4388:3: ruleIdiomsImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdiomsImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedWithsAssignment_2"


    // $ANTLR start "rule__IdiomsModel__OwnedImportsAssignment_3"
    // InternalIdioms.g:4397:1: rule__IdiomsModel__OwnedImportsAssignment_3 : ( ruleEPackageImport ) ;
    public final void rule__IdiomsModel__OwnedImportsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4401:1: ( ( ruleEPackageImport ) )
            // InternalIdioms.g:4402:2: ( ruleEPackageImport )
            {
            // InternalIdioms.g:4402:2: ( ruleEPackageImport )
            // InternalIdioms.g:4403:3: ruleEPackageImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleEPackageImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedImportsAssignment_3"


    // $ANTLR start "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0"
    // InternalIdioms.g:4412:1: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0 : ( ruleLocatorDeclaration ) ;
    public final void rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4416:1: ( ( ruleLocatorDeclaration ) )
            // InternalIdioms.g:4417:2: ( ruleLocatorDeclaration )
            {
            // InternalIdioms.g:4417:2: ( ruleLocatorDeclaration )
            // InternalIdioms.g:4418:3: ruleLocatorDeclaration
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
    // InternalIdioms.g:4427:1: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1 : ( ruleSegmentDeclaration ) ;
    public final void rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4431:1: ( ( ruleSegmentDeclaration ) )
            // InternalIdioms.g:4432:2: ( ruleSegmentDeclaration )
            {
            // InternalIdioms.g:4432:2: ( ruleSegmentDeclaration )
            // InternalIdioms.g:4433:3: ruleSegmentDeclaration
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
    // InternalIdioms.g:4442:1: rule__IdiomsModel__OwnedIdiomsAssignment_4_2 : ( ruleIdiom ) ;
    public final void rule__IdiomsModel__OwnedIdiomsAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4446:1: ( ( ruleIdiom ) )
            // InternalIdioms.g:4447:2: ( ruleIdiom )
            {
            // InternalIdioms.g:4447:2: ( ruleIdiom )
            // InternalIdioms.g:4448:3: ruleIdiom
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


    // $ANTLR start "rule__EPackageImport__EPackageAssignment_1"
    // InternalIdioms.g:4457:1: rule__EPackageImport__EPackageAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__EPackageImport__EPackageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4461:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4462:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4462:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4463:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0());
            }
            // InternalIdioms.g:4464:3: ( RULE_STRING )
            // InternalIdioms.g:4465:4: RULE_STRING
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1());
            }
            match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getEPackageEPackageSTRINGTerminalRuleCall_1_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__EPackageAssignment_1"


    // $ANTLR start "rule__EPackageImport__AsAssignment_2_1"
    // InternalIdioms.g:4476:1: rule__EPackageImport__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__EPackageImport__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4480:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4481:2: ( RULE_ID )
            {
            // InternalIdioms.g:4481:2: ( RULE_ID )
            // InternalIdioms.g:4482:3: RULE_ID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0());
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EPackageImport__AsAssignment_2_1"


    // $ANTLR start "rule__IdiomsImport__IdiomsModelAssignment_1"
    // InternalIdioms.g:4491:1: rule__IdiomsImport__IdiomsModelAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__IdiomsImport__IdiomsModelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4495:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4496:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4496:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4497:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());
            }
            // InternalIdioms.g:4498:3: ( RULE_STRING )
            // InternalIdioms.g:4499:4: RULE_STRING
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
    // InternalIdioms.g:4510:1: rule__IdiomsImport__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__IdiomsImport__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4514:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4515:2: ( RULE_ID )
            {
            // InternalIdioms.g:4515:2: ( RULE_ID )
            // InternalIdioms.g:4516:3: RULE_ID
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
    // InternalIdioms.g:4525:1: rule__LocatorDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__LocatorDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4529:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4530:2: ( RULE_ID )
            {
            // InternalIdioms.g:4530:2: ( RULE_ID )
            // InternalIdioms.g:4531:3: RULE_ID
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
    // InternalIdioms.g:4540:1: rule__LocatorDeclaration__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__LocatorDeclaration__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4544:1: ( ( ruleLocator ) )
            // InternalIdioms.g:4545:2: ( ruleLocator )
            {
            // InternalIdioms.g:4545:2: ( ruleLocator )
            // InternalIdioms.g:4546:3: ruleLocator
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
    // InternalIdioms.g:4555:1: rule__AssignmentLocator__EPackageAssignment_1_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EPackageAssignment_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4559:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4560:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4560:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4561:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());
            }
            // InternalIdioms.g:4562:3: ( RULE_ID )
            // InternalIdioms.g:4563:4: RULE_ID
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
    // InternalIdioms.g:4574:1: rule__AssignmentLocator__EClassAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EClassAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4578:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4579:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4579:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4580:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());
            }
            // InternalIdioms.g:4581:3: ( RULE_ID )
            // InternalIdioms.g:4582:4: RULE_ID
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
    // InternalIdioms.g:4593:1: rule__AssignmentLocator__EStructuralFeatureAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EStructuralFeatureAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4597:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4598:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4598:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4599:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());
            }
            // InternalIdioms.g:4600:3: ( RULE_ID )
            // InternalIdioms.g:4601:4: RULE_ID
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


    // $ANTLR start "rule__CompoundLocator__OwnedLocatorsAssignment_1"
    // InternalIdioms.g:4612:1: rule__CompoundLocator__OwnedLocatorsAssignment_1 : ( ruleElementLocator ) ;
    public final void rule__CompoundLocator__OwnedLocatorsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4616:1: ( ( ruleElementLocator ) )
            // InternalIdioms.g:4617:2: ( ruleElementLocator )
            {
            // InternalIdioms.g:4617:2: ( ruleElementLocator )
            // InternalIdioms.g:4618:3: ruleElementLocator
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsElementLocatorParserRuleCall_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElementLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsElementLocatorParserRuleCall_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__OwnedLocatorsAssignment_1"


    // $ANTLR start "rule__CompoundLocator__OwnedLocatorsAssignment_2_1"
    // InternalIdioms.g:4627:1: rule__CompoundLocator__OwnedLocatorsAssignment_2_1 : ( ruleElementLocator ) ;
    public final void rule__CompoundLocator__OwnedLocatorsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4631:1: ( ( ruleElementLocator ) )
            // InternalIdioms.g:4632:2: ( ruleElementLocator )
            {
            // InternalIdioms.g:4632:2: ( ruleElementLocator )
            // InternalIdioms.g:4633:3: ruleElementLocator
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsElementLocatorParserRuleCall_2_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleElementLocator();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCompoundLocatorAccess().getOwnedLocatorsElementLocatorParserRuleCall_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CompoundLocator__OwnedLocatorsAssignment_2_1"


    // $ANTLR start "rule__KeywordLocator__StringAssignment"
    // InternalIdioms.g:4642:1: rule__KeywordLocator__StringAssignment : ( RULE_STRING ) ;
    public final void rule__KeywordLocator__StringAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4646:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4647:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4647:2: ( RULE_STRING )
            // InternalIdioms.g:4648:3: RULE_STRING
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
    // InternalIdioms.g:4657:1: rule__ReturnsLocator__EPackageAssignment_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EPackageAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4661:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4662:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4662:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4663:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());
            }
            // InternalIdioms.g:4664:3: ( RULE_ID )
            // InternalIdioms.g:4665:4: RULE_ID
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
    // InternalIdioms.g:4676:1: rule__ReturnsLocator__EClassAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EClassAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4680:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4681:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4681:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4682:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());
            }
            // InternalIdioms.g:4683:3: ( RULE_ID )
            // InternalIdioms.g:4684:4: RULE_ID
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
    // InternalIdioms.g:4695:1: rule__ReferredLocator__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4699:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4700:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4700:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4701:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:4702:3: ( RULE_ID )
            // InternalIdioms.g:4703:4: RULE_ID
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
    // InternalIdioms.g:4714:1: rule__ReferredLocator__LocatorDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__LocatorDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4718:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4719:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4719:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4720:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:4721:3: ( RULE_ID )
            // InternalIdioms.g:4722:4: RULE_ID
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


    // $ANTLR start "rule__SegmentDeclaration__NameAssignment_1"
    // InternalIdioms.g:4733:1: rule__SegmentDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__SegmentDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4737:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4738:2: ( RULE_ID )
            {
            // InternalIdioms.g:4738:2: ( RULE_ID )
            // InternalIdioms.g:4739:3: RULE_ID
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
    // InternalIdioms.g:4748:1: rule__SegmentDeclaration__OwnedSegmentAssignment_2 : ( ruleSegment ) ;
    public final void rule__SegmentDeclaration__OwnedSegmentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4752:1: ( ( ruleSegment ) )
            // InternalIdioms.g:4753:2: ( ruleSegment )
            {
            // InternalIdioms.g:4753:2: ( ruleSegment )
            // InternalIdioms.g:4754:3: ruleSegment
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
    // InternalIdioms.g:4763:1: rule__CustomSegment__SupportClassNameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__CustomSegment__SupportClassNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4767:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4768:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4768:2: ( RULE_STRING )
            // InternalIdioms.g:4769:3: RULE_STRING
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
    // InternalIdioms.g:4778:1: rule__StringSegment__StringAssignment_1 : ( RULE_STRING ) ;
    public final void rule__StringSegment__StringAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4782:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4783:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4783:2: ( RULE_STRING )
            // InternalIdioms.g:4784:3: RULE_STRING
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
    // InternalIdioms.g:4793:1: rule__StringSegment__PrintableAssignment_2 : ( ( 'printable' ) ) ;
    public final void rule__StringSegment__PrintableAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4797:1: ( ( ( 'printable' ) ) )
            // InternalIdioms.g:4798:2: ( ( 'printable' ) )
            {
            // InternalIdioms.g:4798:2: ( ( 'printable' ) )
            // InternalIdioms.g:4799:3: ( 'printable' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }
            // InternalIdioms.g:4800:3: ( 'printable' )
            // InternalIdioms.g:4801:4: 'printable'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }
            match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4812:1: rule__ReferredSegment__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4816:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4817:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4817:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4818:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:4819:3: ( RULE_ID )
            // InternalIdioms.g:4820:4: RULE_ID
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
    // InternalIdioms.g:4831:1: rule__ReferredSegment__SegmentDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__SegmentDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4835:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4836:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4836:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4837:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:4838:3: ( RULE_ID )
            // InternalIdioms.g:4839:4: RULE_ID
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
    // InternalIdioms.g:4850:1: rule__Idiom__MixinAssignment_0 : ( ( 'mixin' ) ) ;
    public final void rule__Idiom__MixinAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4854:1: ( ( ( 'mixin' ) ) )
            // InternalIdioms.g:4855:2: ( ( 'mixin' ) )
            {
            // InternalIdioms.g:4855:2: ( ( 'mixin' ) )
            // InternalIdioms.g:4856:3: ( 'mixin' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }
            // InternalIdioms.g:4857:3: ( 'mixin' )
            // InternalIdioms.g:4858:4: 'mixin'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }
            match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4869:1: rule__Idiom__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Idiom__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4873:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4874:2: ( RULE_ID )
            {
            // InternalIdioms.g:4874:2: ( RULE_ID )
            // InternalIdioms.g:4875:3: RULE_ID
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
    // InternalIdioms.g:4884:1: rule__Idiom__ForEPackageAssignment_3_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEPackageAssignment_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4888:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4889:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4889:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4890:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());
            }
            // InternalIdioms.g:4891:3: ( RULE_ID )
            // InternalIdioms.g:4892:4: RULE_ID
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
    // InternalIdioms.g:4903:1: rule__Idiom__ForEClassAssignment_3_2 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEClassAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4907:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4908:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4908:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4909:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());
            }
            // InternalIdioms.g:4910:3: ( RULE_ID )
            // InternalIdioms.g:4911:4: RULE_ID
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
    // InternalIdioms.g:4922:1: rule__Idiom__InRuleRegexAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__Idiom__InRuleRegexAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4926:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4927:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4927:2: ( RULE_STRING )
            // InternalIdioms.g:4928:3: RULE_STRING
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
    // InternalIdioms.g:4937:1: rule__Idiom__OwnedSubIdiomsAssignment_5_0 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4941:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:4942:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:4942:2: ( ruleSubIdiom )
            // InternalIdioms.g:4943:3: ruleSubIdiom
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
    // InternalIdioms.g:4952:1: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4956:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:4957:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:4957:2: ( ruleSubIdiom )
            // InternalIdioms.g:4958:3: ruleSubIdiom
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
    // InternalIdioms.g:4967:1: rule__SubIdiom__AllAssignment_1_0 : ( ( 'all' ) ) ;
    public final void rule__SubIdiom__AllAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4971:1: ( ( ( 'all' ) ) )
            // InternalIdioms.g:4972:2: ( ( 'all' ) )
            {
            // InternalIdioms.g:4972:2: ( ( 'all' ) )
            // InternalIdioms.g:4973:3: ( 'all' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }
            // InternalIdioms.g:4974:3: ( 'all' )
            // InternalIdioms.g:4975:4: 'all'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }
            match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4986:1: rule__SubIdiom__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__SubIdiom__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4990:1: ( ( ruleLocator ) )
            // InternalIdioms.g:4991:2: ( ruleLocator )
            {
            // InternalIdioms.g:4991:2: ( ruleLocator )
            // InternalIdioms.g:4992:3: ruleLocator
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
    // InternalIdioms.g:5001:1: rule__SubIdiom__OwnedSegmentsAssignment_3_1 : ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) ;
    public final void rule__SubIdiom__OwnedSegmentsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:5005:1: ( ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) )
            // InternalIdioms.g:5006:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            {
            // InternalIdioms.g:5006:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            // InternalIdioms.g:5007:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0());
            }
            // InternalIdioms.g:5008:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            // InternalIdioms.g:5008:4: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0
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
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0008200008032000L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000010002L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0008200008020002L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000065C0030L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000500030L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000001800000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00001FFFF0000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0004000000000000L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000080000000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0008200008020000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0001C00000400000L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0001000000800000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0001000000000002L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x00100000065C0830L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0002000000004000L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x00001FFFF0000010L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x00001FFFF0000012L});
    }


}