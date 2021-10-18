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
 * Copyright (c) 2011, 2021 Willink Transformations and others.
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'each'", "'model'", "'.'", "'import'", "';'", "'as'", "'with'", "'locator'", "'any-assignment'", "'any-element'", "'assignment'", "'::'", "'final'", "'returns'", "'segment'", "'custom'", "'half-new-line'", "'new-line'", "'no-space'", "'pop'", "'post-comment'", "'pre-comment'", "'push'", "'soft-new-line'", "'soft-space'", "'string'", "'value'", "'wrap-anchor'", "'wrap-begin-all'", "'wrap-begin-some'", "'wrap-end'", "'wrap-here'", "'idiom'", "'for'", "'in'", "'{'", "'}'", "'at'", "'do'", "'printable'", "'mixin'", "'all'"
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


    // $ANTLR start "entryRuleLocator"
    // InternalIdioms.g:163:1: entryRuleLocator : ruleLocator EOF ;
    public final void entryRuleLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:164:1: ( ruleLocator EOF )
            // InternalIdioms.g:165:1: ruleLocator EOF
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
    // InternalIdioms.g:172:1: ruleLocator : ( ( rule__Locator__Alternatives ) ) ;
    public final void ruleLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:176:2: ( ( ( rule__Locator__Alternatives ) ) )
            // InternalIdioms.g:177:2: ( ( rule__Locator__Alternatives ) )
            {
            // InternalIdioms.g:177:2: ( ( rule__Locator__Alternatives ) )
            // InternalIdioms.g:178:3: ( rule__Locator__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorAccess().getAlternatives());
            }
            // InternalIdioms.g:179:3: ( rule__Locator__Alternatives )
            // InternalIdioms.g:179:4: rule__Locator__Alternatives
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
    // InternalIdioms.g:188:1: entryRuleAnyAssignmentLocator : ruleAnyAssignmentLocator EOF ;
    public final void entryRuleAnyAssignmentLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:189:1: ( ruleAnyAssignmentLocator EOF )
            // InternalIdioms.g:190:1: ruleAnyAssignmentLocator EOF
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
    // InternalIdioms.g:197:1: ruleAnyAssignmentLocator : ( ( rule__AnyAssignmentLocator__Group__0 ) ) ;
    public final void ruleAnyAssignmentLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:201:2: ( ( ( rule__AnyAssignmentLocator__Group__0 ) ) )
            // InternalIdioms.g:202:2: ( ( rule__AnyAssignmentLocator__Group__0 ) )
            {
            // InternalIdioms.g:202:2: ( ( rule__AnyAssignmentLocator__Group__0 ) )
            // InternalIdioms.g:203:3: ( rule__AnyAssignmentLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getGroup());
            }
            // InternalIdioms.g:204:3: ( rule__AnyAssignmentLocator__Group__0 )
            // InternalIdioms.g:204:4: rule__AnyAssignmentLocator__Group__0
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
    // InternalIdioms.g:213:1: entryRuleAnyElementLocator : ruleAnyElementLocator EOF ;
    public final void entryRuleAnyElementLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:214:1: ( ruleAnyElementLocator EOF )
            // InternalIdioms.g:215:1: ruleAnyElementLocator EOF
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
    // InternalIdioms.g:222:1: ruleAnyElementLocator : ( ( rule__AnyElementLocator__Group__0 ) ) ;
    public final void ruleAnyElementLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:226:2: ( ( ( rule__AnyElementLocator__Group__0 ) ) )
            // InternalIdioms.g:227:2: ( ( rule__AnyElementLocator__Group__0 ) )
            {
            // InternalIdioms.g:227:2: ( ( rule__AnyElementLocator__Group__0 ) )
            // InternalIdioms.g:228:3: ( rule__AnyElementLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getGroup());
            }
            // InternalIdioms.g:229:3: ( rule__AnyElementLocator__Group__0 )
            // InternalIdioms.g:229:4: rule__AnyElementLocator__Group__0
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
    // InternalIdioms.g:238:1: entryRuleAssignmentLocator : ruleAssignmentLocator EOF ;
    public final void entryRuleAssignmentLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:239:1: ( ruleAssignmentLocator EOF )
            // InternalIdioms.g:240:1: ruleAssignmentLocator EOF
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
    // InternalIdioms.g:247:1: ruleAssignmentLocator : ( ( rule__AssignmentLocator__Group__0 ) ) ;
    public final void ruleAssignmentLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:251:2: ( ( ( rule__AssignmentLocator__Group__0 ) ) )
            // InternalIdioms.g:252:2: ( ( rule__AssignmentLocator__Group__0 ) )
            {
            // InternalIdioms.g:252:2: ( ( rule__AssignmentLocator__Group__0 ) )
            // InternalIdioms.g:253:3: ( rule__AssignmentLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup());
            }
            // InternalIdioms.g:254:3: ( rule__AssignmentLocator__Group__0 )
            // InternalIdioms.g:254:4: rule__AssignmentLocator__Group__0
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
    // InternalIdioms.g:263:1: entryRuleFinalLocator : ruleFinalLocator EOF ;
    public final void entryRuleFinalLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:264:1: ( ruleFinalLocator EOF )
            // InternalIdioms.g:265:1: ruleFinalLocator EOF
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
    // InternalIdioms.g:272:1: ruleFinalLocator : ( ( rule__FinalLocator__Group__0 ) ) ;
    public final void ruleFinalLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:276:2: ( ( ( rule__FinalLocator__Group__0 ) ) )
            // InternalIdioms.g:277:2: ( ( rule__FinalLocator__Group__0 ) )
            {
            // InternalIdioms.g:277:2: ( ( rule__FinalLocator__Group__0 ) )
            // InternalIdioms.g:278:3: ( rule__FinalLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getGroup());
            }
            // InternalIdioms.g:279:3: ( rule__FinalLocator__Group__0 )
            // InternalIdioms.g:279:4: rule__FinalLocator__Group__0
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
    // InternalIdioms.g:288:1: entryRuleKeywordLocator : ruleKeywordLocator EOF ;
    public final void entryRuleKeywordLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:289:1: ( ruleKeywordLocator EOF )
            // InternalIdioms.g:290:1: ruleKeywordLocator EOF
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
    // InternalIdioms.g:297:1: ruleKeywordLocator : ( ( rule__KeywordLocator__StringAssignment ) ) ;
    public final void ruleKeywordLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:301:2: ( ( ( rule__KeywordLocator__StringAssignment ) ) )
            // InternalIdioms.g:302:2: ( ( rule__KeywordLocator__StringAssignment ) )
            {
            // InternalIdioms.g:302:2: ( ( rule__KeywordLocator__StringAssignment ) )
            // InternalIdioms.g:303:3: ( rule__KeywordLocator__StringAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getKeywordLocatorAccess().getStringAssignment());
            }
            // InternalIdioms.g:304:3: ( rule__KeywordLocator__StringAssignment )
            // InternalIdioms.g:304:4: rule__KeywordLocator__StringAssignment
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
    // InternalIdioms.g:313:1: entryRuleReturnsLocator : ruleReturnsLocator EOF ;
    public final void entryRuleReturnsLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:314:1: ( ruleReturnsLocator EOF )
            // InternalIdioms.g:315:1: ruleReturnsLocator EOF
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
    // InternalIdioms.g:322:1: ruleReturnsLocator : ( ( rule__ReturnsLocator__Group__0 ) ) ;
    public final void ruleReturnsLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:326:2: ( ( ( rule__ReturnsLocator__Group__0 ) ) )
            // InternalIdioms.g:327:2: ( ( rule__ReturnsLocator__Group__0 ) )
            {
            // InternalIdioms.g:327:2: ( ( rule__ReturnsLocator__Group__0 ) )
            // InternalIdioms.g:328:3: ( rule__ReturnsLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup());
            }
            // InternalIdioms.g:329:3: ( rule__ReturnsLocator__Group__0 )
            // InternalIdioms.g:329:4: rule__ReturnsLocator__Group__0
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
    // InternalIdioms.g:338:1: entryRuleReferredLocator : ruleReferredLocator EOF ;
    public final void entryRuleReferredLocator() throws RecognitionException {
        try {
            // InternalIdioms.g:339:1: ( ruleReferredLocator EOF )
            // InternalIdioms.g:340:1: ruleReferredLocator EOF
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
    // InternalIdioms.g:347:1: ruleReferredLocator : ( ( rule__ReferredLocator__Group__0 ) ) ;
    public final void ruleReferredLocator() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:351:2: ( ( ( rule__ReferredLocator__Group__0 ) ) )
            // InternalIdioms.g:352:2: ( ( rule__ReferredLocator__Group__0 ) )
            {
            // InternalIdioms.g:352:2: ( ( rule__ReferredLocator__Group__0 ) )
            // InternalIdioms.g:353:3: ( rule__ReferredLocator__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup());
            }
            // InternalIdioms.g:354:3: ( rule__ReferredLocator__Group__0 )
            // InternalIdioms.g:354:4: rule__ReferredLocator__Group__0
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
    // InternalIdioms.g:363:1: entryRuleSegmentDeclaration : ruleSegmentDeclaration EOF ;
    public final void entryRuleSegmentDeclaration() throws RecognitionException {
        try {
            // InternalIdioms.g:364:1: ( ruleSegmentDeclaration EOF )
            // InternalIdioms.g:365:1: ruleSegmentDeclaration EOF
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
    // InternalIdioms.g:372:1: ruleSegmentDeclaration : ( ( rule__SegmentDeclaration__Group__0 ) ) ;
    public final void ruleSegmentDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:376:2: ( ( ( rule__SegmentDeclaration__Group__0 ) ) )
            // InternalIdioms.g:377:2: ( ( rule__SegmentDeclaration__Group__0 ) )
            {
            // InternalIdioms.g:377:2: ( ( rule__SegmentDeclaration__Group__0 ) )
            // InternalIdioms.g:378:3: ( rule__SegmentDeclaration__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getGroup());
            }
            // InternalIdioms.g:379:3: ( rule__SegmentDeclaration__Group__0 )
            // InternalIdioms.g:379:4: rule__SegmentDeclaration__Group__0
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
    // InternalIdioms.g:388:1: entryRuleSegment : ruleSegment EOF ;
    public final void entryRuleSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:389:1: ( ruleSegment EOF )
            // InternalIdioms.g:390:1: ruleSegment EOF
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
    // InternalIdioms.g:397:1: ruleSegment : ( ( rule__Segment__Alternatives ) ) ;
    public final void ruleSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:401:2: ( ( ( rule__Segment__Alternatives ) ) )
            // InternalIdioms.g:402:2: ( ( rule__Segment__Alternatives ) )
            {
            // InternalIdioms.g:402:2: ( ( rule__Segment__Alternatives ) )
            // InternalIdioms.g:403:3: ( rule__Segment__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentAccess().getAlternatives());
            }
            // InternalIdioms.g:404:3: ( rule__Segment__Alternatives )
            // InternalIdioms.g:404:4: rule__Segment__Alternatives
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
    // InternalIdioms.g:413:1: entryRuleCustomSegment : ruleCustomSegment EOF ;
    public final void entryRuleCustomSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:414:1: ( ruleCustomSegment EOF )
            // InternalIdioms.g:415:1: ruleCustomSegment EOF
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
    // InternalIdioms.g:422:1: ruleCustomSegment : ( ( rule__CustomSegment__Group__0 ) ) ;
    public final void ruleCustomSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:426:2: ( ( ( rule__CustomSegment__Group__0 ) ) )
            // InternalIdioms.g:427:2: ( ( rule__CustomSegment__Group__0 ) )
            {
            // InternalIdioms.g:427:2: ( ( rule__CustomSegment__Group__0 ) )
            // InternalIdioms.g:428:3: ( rule__CustomSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getGroup());
            }
            // InternalIdioms.g:429:3: ( rule__CustomSegment__Group__0 )
            // InternalIdioms.g:429:4: rule__CustomSegment__Group__0
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
    // InternalIdioms.g:438:1: entryRuleHalfNewLineSegment : ruleHalfNewLineSegment EOF ;
    public final void entryRuleHalfNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:439:1: ( ruleHalfNewLineSegment EOF )
            // InternalIdioms.g:440:1: ruleHalfNewLineSegment EOF
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
    // InternalIdioms.g:447:1: ruleHalfNewLineSegment : ( ( rule__HalfNewLineSegment__Group__0 ) ) ;
    public final void ruleHalfNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:451:2: ( ( ( rule__HalfNewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:452:2: ( ( rule__HalfNewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:452:2: ( ( rule__HalfNewLineSegment__Group__0 ) )
            // InternalIdioms.g:453:3: ( rule__HalfNewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:454:3: ( rule__HalfNewLineSegment__Group__0 )
            // InternalIdioms.g:454:4: rule__HalfNewLineSegment__Group__0
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
    // InternalIdioms.g:463:1: entryRuleNewLineSegment : ruleNewLineSegment EOF ;
    public final void entryRuleNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:464:1: ( ruleNewLineSegment EOF )
            // InternalIdioms.g:465:1: ruleNewLineSegment EOF
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
    // InternalIdioms.g:472:1: ruleNewLineSegment : ( ( rule__NewLineSegment__Group__0 ) ) ;
    public final void ruleNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:476:2: ( ( ( rule__NewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:477:2: ( ( rule__NewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:477:2: ( ( rule__NewLineSegment__Group__0 ) )
            // InternalIdioms.g:478:3: ( rule__NewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:479:3: ( rule__NewLineSegment__Group__0 )
            // InternalIdioms.g:479:4: rule__NewLineSegment__Group__0
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
    // InternalIdioms.g:488:1: entryRuleNoSpaceSegment : ruleNoSpaceSegment EOF ;
    public final void entryRuleNoSpaceSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:489:1: ( ruleNoSpaceSegment EOF )
            // InternalIdioms.g:490:1: ruleNoSpaceSegment EOF
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
    // InternalIdioms.g:497:1: ruleNoSpaceSegment : ( ( rule__NoSpaceSegment__Group__0 ) ) ;
    public final void ruleNoSpaceSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:501:2: ( ( ( rule__NoSpaceSegment__Group__0 ) ) )
            // InternalIdioms.g:502:2: ( ( rule__NoSpaceSegment__Group__0 ) )
            {
            // InternalIdioms.g:502:2: ( ( rule__NoSpaceSegment__Group__0 ) )
            // InternalIdioms.g:503:3: ( rule__NoSpaceSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getGroup());
            }
            // InternalIdioms.g:504:3: ( rule__NoSpaceSegment__Group__0 )
            // InternalIdioms.g:504:4: rule__NoSpaceSegment__Group__0
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
    // InternalIdioms.g:513:1: entryRulePopSegment : rulePopSegment EOF ;
    public final void entryRulePopSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:514:1: ( rulePopSegment EOF )
            // InternalIdioms.g:515:1: rulePopSegment EOF
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
    // InternalIdioms.g:522:1: rulePopSegment : ( ( rule__PopSegment__Group__0 ) ) ;
    public final void rulePopSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:526:2: ( ( ( rule__PopSegment__Group__0 ) ) )
            // InternalIdioms.g:527:2: ( ( rule__PopSegment__Group__0 ) )
            {
            // InternalIdioms.g:527:2: ( ( rule__PopSegment__Group__0 ) )
            // InternalIdioms.g:528:3: ( rule__PopSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getGroup());
            }
            // InternalIdioms.g:529:3: ( rule__PopSegment__Group__0 )
            // InternalIdioms.g:529:4: rule__PopSegment__Group__0
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
    // InternalIdioms.g:538:1: entryRulePostCommentSegment : rulePostCommentSegment EOF ;
    public final void entryRulePostCommentSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:539:1: ( rulePostCommentSegment EOF )
            // InternalIdioms.g:540:1: rulePostCommentSegment EOF
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
    // InternalIdioms.g:547:1: rulePostCommentSegment : ( ( rule__PostCommentSegment__Group__0 ) ) ;
    public final void rulePostCommentSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:551:2: ( ( ( rule__PostCommentSegment__Group__0 ) ) )
            // InternalIdioms.g:552:2: ( ( rule__PostCommentSegment__Group__0 ) )
            {
            // InternalIdioms.g:552:2: ( ( rule__PostCommentSegment__Group__0 ) )
            // InternalIdioms.g:553:3: ( rule__PostCommentSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getGroup());
            }
            // InternalIdioms.g:554:3: ( rule__PostCommentSegment__Group__0 )
            // InternalIdioms.g:554:4: rule__PostCommentSegment__Group__0
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
    // InternalIdioms.g:563:1: entryRulePreCommentSegment : rulePreCommentSegment EOF ;
    public final void entryRulePreCommentSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:564:1: ( rulePreCommentSegment EOF )
            // InternalIdioms.g:565:1: rulePreCommentSegment EOF
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
    // InternalIdioms.g:572:1: rulePreCommentSegment : ( ( rule__PreCommentSegment__Group__0 ) ) ;
    public final void rulePreCommentSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:576:2: ( ( ( rule__PreCommentSegment__Group__0 ) ) )
            // InternalIdioms.g:577:2: ( ( rule__PreCommentSegment__Group__0 ) )
            {
            // InternalIdioms.g:577:2: ( ( rule__PreCommentSegment__Group__0 ) )
            // InternalIdioms.g:578:3: ( rule__PreCommentSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getGroup());
            }
            // InternalIdioms.g:579:3: ( rule__PreCommentSegment__Group__0 )
            // InternalIdioms.g:579:4: rule__PreCommentSegment__Group__0
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
    // InternalIdioms.g:588:1: entryRulePushSegment : rulePushSegment EOF ;
    public final void entryRulePushSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:589:1: ( rulePushSegment EOF )
            // InternalIdioms.g:590:1: rulePushSegment EOF
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
    // InternalIdioms.g:597:1: rulePushSegment : ( ( rule__PushSegment__Group__0 ) ) ;
    public final void rulePushSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:601:2: ( ( ( rule__PushSegment__Group__0 ) ) )
            // InternalIdioms.g:602:2: ( ( rule__PushSegment__Group__0 ) )
            {
            // InternalIdioms.g:602:2: ( ( rule__PushSegment__Group__0 ) )
            // InternalIdioms.g:603:3: ( rule__PushSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getGroup());
            }
            // InternalIdioms.g:604:3: ( rule__PushSegment__Group__0 )
            // InternalIdioms.g:604:4: rule__PushSegment__Group__0
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
    // InternalIdioms.g:613:1: entryRuleSoftNewLineSegment : ruleSoftNewLineSegment EOF ;
    public final void entryRuleSoftNewLineSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:614:1: ( ruleSoftNewLineSegment EOF )
            // InternalIdioms.g:615:1: ruleSoftNewLineSegment EOF
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
    // InternalIdioms.g:622:1: ruleSoftNewLineSegment : ( ( rule__SoftNewLineSegment__Group__0 ) ) ;
    public final void ruleSoftNewLineSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:626:2: ( ( ( rule__SoftNewLineSegment__Group__0 ) ) )
            // InternalIdioms.g:627:2: ( ( rule__SoftNewLineSegment__Group__0 ) )
            {
            // InternalIdioms.g:627:2: ( ( rule__SoftNewLineSegment__Group__0 ) )
            // InternalIdioms.g:628:3: ( rule__SoftNewLineSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getGroup());
            }
            // InternalIdioms.g:629:3: ( rule__SoftNewLineSegment__Group__0 )
            // InternalIdioms.g:629:4: rule__SoftNewLineSegment__Group__0
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
    // InternalIdioms.g:638:1: entryRuleSoftSpaceSegment : ruleSoftSpaceSegment EOF ;
    public final void entryRuleSoftSpaceSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:639:1: ( ruleSoftSpaceSegment EOF )
            // InternalIdioms.g:640:1: ruleSoftSpaceSegment EOF
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
    // InternalIdioms.g:647:1: ruleSoftSpaceSegment : ( ( rule__SoftSpaceSegment__Group__0 ) ) ;
    public final void ruleSoftSpaceSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:651:2: ( ( ( rule__SoftSpaceSegment__Group__0 ) ) )
            // InternalIdioms.g:652:2: ( ( rule__SoftSpaceSegment__Group__0 ) )
            {
            // InternalIdioms.g:652:2: ( ( rule__SoftSpaceSegment__Group__0 ) )
            // InternalIdioms.g:653:3: ( rule__SoftSpaceSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getGroup());
            }
            // InternalIdioms.g:654:3: ( rule__SoftSpaceSegment__Group__0 )
            // InternalIdioms.g:654:4: rule__SoftSpaceSegment__Group__0
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
    // InternalIdioms.g:663:1: entryRuleStringSegment : ruleStringSegment EOF ;
    public final void entryRuleStringSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:664:1: ( ruleStringSegment EOF )
            // InternalIdioms.g:665:1: ruleStringSegment EOF
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
    // InternalIdioms.g:672:1: ruleStringSegment : ( ( rule__StringSegment__Group__0 ) ) ;
    public final void ruleStringSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:676:2: ( ( ( rule__StringSegment__Group__0 ) ) )
            // InternalIdioms.g:677:2: ( ( rule__StringSegment__Group__0 ) )
            {
            // InternalIdioms.g:677:2: ( ( rule__StringSegment__Group__0 ) )
            // InternalIdioms.g:678:3: ( rule__StringSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getGroup());
            }
            // InternalIdioms.g:679:3: ( rule__StringSegment__Group__0 )
            // InternalIdioms.g:679:4: rule__StringSegment__Group__0
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
    // InternalIdioms.g:688:1: entryRuleValueSegment : ruleValueSegment EOF ;
    public final void entryRuleValueSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:689:1: ( ruleValueSegment EOF )
            // InternalIdioms.g:690:1: ruleValueSegment EOF
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
    // InternalIdioms.g:697:1: ruleValueSegment : ( ( rule__ValueSegment__Group__0 ) ) ;
    public final void ruleValueSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:701:2: ( ( ( rule__ValueSegment__Group__0 ) ) )
            // InternalIdioms.g:702:2: ( ( rule__ValueSegment__Group__0 ) )
            {
            // InternalIdioms.g:702:2: ( ( rule__ValueSegment__Group__0 ) )
            // InternalIdioms.g:703:3: ( rule__ValueSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getGroup());
            }
            // InternalIdioms.g:704:3: ( rule__ValueSegment__Group__0 )
            // InternalIdioms.g:704:4: rule__ValueSegment__Group__0
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
    // InternalIdioms.g:713:1: entryRuleWrapAnchorSegment : ruleWrapAnchorSegment EOF ;
    public final void entryRuleWrapAnchorSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:714:1: ( ruleWrapAnchorSegment EOF )
            // InternalIdioms.g:715:1: ruleWrapAnchorSegment EOF
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
    // InternalIdioms.g:722:1: ruleWrapAnchorSegment : ( ( rule__WrapAnchorSegment__Group__0 ) ) ;
    public final void ruleWrapAnchorSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:726:2: ( ( ( rule__WrapAnchorSegment__Group__0 ) ) )
            // InternalIdioms.g:727:2: ( ( rule__WrapAnchorSegment__Group__0 ) )
            {
            // InternalIdioms.g:727:2: ( ( rule__WrapAnchorSegment__Group__0 ) )
            // InternalIdioms.g:728:3: ( rule__WrapAnchorSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getGroup());
            }
            // InternalIdioms.g:729:3: ( rule__WrapAnchorSegment__Group__0 )
            // InternalIdioms.g:729:4: rule__WrapAnchorSegment__Group__0
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
    // InternalIdioms.g:738:1: entryRuleWrapBeginAllSegment : ruleWrapBeginAllSegment EOF ;
    public final void entryRuleWrapBeginAllSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:739:1: ( ruleWrapBeginAllSegment EOF )
            // InternalIdioms.g:740:1: ruleWrapBeginAllSegment EOF
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
    // InternalIdioms.g:747:1: ruleWrapBeginAllSegment : ( ( rule__WrapBeginAllSegment__Group__0 ) ) ;
    public final void ruleWrapBeginAllSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:751:2: ( ( ( rule__WrapBeginAllSegment__Group__0 ) ) )
            // InternalIdioms.g:752:2: ( ( rule__WrapBeginAllSegment__Group__0 ) )
            {
            // InternalIdioms.g:752:2: ( ( rule__WrapBeginAllSegment__Group__0 ) )
            // InternalIdioms.g:753:3: ( rule__WrapBeginAllSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getGroup());
            }
            // InternalIdioms.g:754:3: ( rule__WrapBeginAllSegment__Group__0 )
            // InternalIdioms.g:754:4: rule__WrapBeginAllSegment__Group__0
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
    // InternalIdioms.g:763:1: entryRuleWrapBeginSomeSegment : ruleWrapBeginSomeSegment EOF ;
    public final void entryRuleWrapBeginSomeSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:764:1: ( ruleWrapBeginSomeSegment EOF )
            // InternalIdioms.g:765:1: ruleWrapBeginSomeSegment EOF
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
    // InternalIdioms.g:772:1: ruleWrapBeginSomeSegment : ( ( rule__WrapBeginSomeSegment__Group__0 ) ) ;
    public final void ruleWrapBeginSomeSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:776:2: ( ( ( rule__WrapBeginSomeSegment__Group__0 ) ) )
            // InternalIdioms.g:777:2: ( ( rule__WrapBeginSomeSegment__Group__0 ) )
            {
            // InternalIdioms.g:777:2: ( ( rule__WrapBeginSomeSegment__Group__0 ) )
            // InternalIdioms.g:778:3: ( rule__WrapBeginSomeSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getGroup());
            }
            // InternalIdioms.g:779:3: ( rule__WrapBeginSomeSegment__Group__0 )
            // InternalIdioms.g:779:4: rule__WrapBeginSomeSegment__Group__0
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
    // InternalIdioms.g:788:1: entryRuleWrapEndSegment : ruleWrapEndSegment EOF ;
    public final void entryRuleWrapEndSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:789:1: ( ruleWrapEndSegment EOF )
            // InternalIdioms.g:790:1: ruleWrapEndSegment EOF
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
    // InternalIdioms.g:797:1: ruleWrapEndSegment : ( ( rule__WrapEndSegment__Group__0 ) ) ;
    public final void ruleWrapEndSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:801:2: ( ( ( rule__WrapEndSegment__Group__0 ) ) )
            // InternalIdioms.g:802:2: ( ( rule__WrapEndSegment__Group__0 ) )
            {
            // InternalIdioms.g:802:2: ( ( rule__WrapEndSegment__Group__0 ) )
            // InternalIdioms.g:803:3: ( rule__WrapEndSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getGroup());
            }
            // InternalIdioms.g:804:3: ( rule__WrapEndSegment__Group__0 )
            // InternalIdioms.g:804:4: rule__WrapEndSegment__Group__0
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
    // InternalIdioms.g:813:1: entryRuleWrapHereSegment : ruleWrapHereSegment EOF ;
    public final void entryRuleWrapHereSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:814:1: ( ruleWrapHereSegment EOF )
            // InternalIdioms.g:815:1: ruleWrapHereSegment EOF
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
    // InternalIdioms.g:822:1: ruleWrapHereSegment : ( ( rule__WrapHereSegment__Group__0 ) ) ;
    public final void ruleWrapHereSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:826:2: ( ( ( rule__WrapHereSegment__Group__0 ) ) )
            // InternalIdioms.g:827:2: ( ( rule__WrapHereSegment__Group__0 ) )
            {
            // InternalIdioms.g:827:2: ( ( rule__WrapHereSegment__Group__0 ) )
            // InternalIdioms.g:828:3: ( rule__WrapHereSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getGroup());
            }
            // InternalIdioms.g:829:3: ( rule__WrapHereSegment__Group__0 )
            // InternalIdioms.g:829:4: rule__WrapHereSegment__Group__0
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
    // InternalIdioms.g:838:1: entryRuleReferredSegment : ruleReferredSegment EOF ;
    public final void entryRuleReferredSegment() throws RecognitionException {
        try {
            // InternalIdioms.g:839:1: ( ruleReferredSegment EOF )
            // InternalIdioms.g:840:1: ruleReferredSegment EOF
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
    // InternalIdioms.g:847:1: ruleReferredSegment : ( ( rule__ReferredSegment__Group__0 ) ) ;
    public final void ruleReferredSegment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:851:2: ( ( ( rule__ReferredSegment__Group__0 ) ) )
            // InternalIdioms.g:852:2: ( ( rule__ReferredSegment__Group__0 ) )
            {
            // InternalIdioms.g:852:2: ( ( rule__ReferredSegment__Group__0 ) )
            // InternalIdioms.g:853:3: ( rule__ReferredSegment__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getGroup());
            }
            // InternalIdioms.g:854:3: ( rule__ReferredSegment__Group__0 )
            // InternalIdioms.g:854:4: rule__ReferredSegment__Group__0
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
    // InternalIdioms.g:863:1: entryRuleIdiom : ruleIdiom EOF ;
    public final void entryRuleIdiom() throws RecognitionException {
        try {
            // InternalIdioms.g:864:1: ( ruleIdiom EOF )
            // InternalIdioms.g:865:1: ruleIdiom EOF
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
    // InternalIdioms.g:872:1: ruleIdiom : ( ( rule__Idiom__Group__0 ) ) ;
    public final void ruleIdiom() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:876:2: ( ( ( rule__Idiom__Group__0 ) ) )
            // InternalIdioms.g:877:2: ( ( rule__Idiom__Group__0 ) )
            {
            // InternalIdioms.g:877:2: ( ( rule__Idiom__Group__0 ) )
            // InternalIdioms.g:878:3: ( rule__Idiom__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup());
            }
            // InternalIdioms.g:879:3: ( rule__Idiom__Group__0 )
            // InternalIdioms.g:879:4: rule__Idiom__Group__0
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
    // InternalIdioms.g:888:1: entryRuleSubIdiom : ruleSubIdiom EOF ;
    public final void entryRuleSubIdiom() throws RecognitionException {
        try {
            // InternalIdioms.g:889:1: ( ruleSubIdiom EOF )
            // InternalIdioms.g:890:1: ruleSubIdiom EOF
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
    // InternalIdioms.g:897:1: ruleSubIdiom : ( ( rule__SubIdiom__Group__0 ) ) ;
    public final void ruleSubIdiom() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:901:2: ( ( ( rule__SubIdiom__Group__0 ) ) )
            // InternalIdioms.g:902:2: ( ( rule__SubIdiom__Group__0 ) )
            {
            // InternalIdioms.g:902:2: ( ( rule__SubIdiom__Group__0 ) )
            // InternalIdioms.g:903:3: ( rule__SubIdiom__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getGroup());
            }
            // InternalIdioms.g:904:3: ( rule__SubIdiom__Group__0 )
            // InternalIdioms.g:904:4: rule__SubIdiom__Group__0
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


    // $ANTLR start "rule__IdiomsModel__Alternatives_5"
    // InternalIdioms.g:912:1: rule__IdiomsModel__Alternatives_5 : ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 ) ) );
    public final void rule__IdiomsModel__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:916:1: ( ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 ) ) | ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 ) ) | ( ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 ) ) )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt1=1;
                }
                break;
            case 25:
                {
                alt1=2;
                }
                break;
            case 43:
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
                    // InternalIdioms.g:917:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 ) )
                    {
                    // InternalIdioms.g:917:2: ( ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 ) )
                    // InternalIdioms.g:918:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_5_0());
                    }
                    // InternalIdioms.g:919:3: ( rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 )
                    // InternalIdioms.g:919:4: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsAssignment_5_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:923:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 ) )
                    {
                    // InternalIdioms.g:923:2: ( ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 ) )
                    // InternalIdioms.g:924:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_5_1());
                    }
                    // InternalIdioms.g:925:3: ( rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 )
                    // InternalIdioms.g:925:4: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsAssignment_5_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalIdioms.g:929:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 ) )
                    {
                    // InternalIdioms.g:929:2: ( ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 ) )
                    // InternalIdioms.g:930:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_5_2());
                    }
                    // InternalIdioms.g:931:3: ( rule__IdiomsModel__OwnedIdiomsAssignment_5_2 )
                    // InternalIdioms.g:931:4: rule__IdiomsModel__OwnedIdiomsAssignment_5_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__IdiomsModel__OwnedIdiomsAssignment_5_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsAssignment_5_2());
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
    // $ANTLR end "rule__IdiomsModel__Alternatives_5"


    // $ANTLR start "rule__Locator__Alternatives"
    // InternalIdioms.g:939:1: rule__Locator__Alternatives : ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleAssignmentLocator ) | ( ruleFinalLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) | ( ruleReturnsLocator ) );
    public final void rule__Locator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:943:1: ( ( ruleAnyAssignmentLocator ) | ( ruleAnyElementLocator ) | ( ruleAssignmentLocator ) | ( ruleFinalLocator ) | ( ruleKeywordLocator ) | ( ruleReferredLocator ) | ( ruleReturnsLocator ) )
            int alt2=7;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt2=1;
                }
                break;
            case 20:
                {
                alt2=2;
                }
                break;
            case 21:
                {
                alt2=3;
                }
                break;
            case 23:
                {
                alt2=4;
                }
                break;
            case RULE_STRING:
                {
                alt2=5;
                }
                break;
            case RULE_ID:
                {
                alt2=6;
                }
                break;
            case 24:
                {
                alt2=7;
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
                    // InternalIdioms.g:944:2: ( ruleAnyAssignmentLocator )
                    {
                    // InternalIdioms.g:944:2: ( ruleAnyAssignmentLocator )
                    // InternalIdioms.g:945:3: ruleAnyAssignmentLocator
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
                    // InternalIdioms.g:950:2: ( ruleAnyElementLocator )
                    {
                    // InternalIdioms.g:950:2: ( ruleAnyElementLocator )
                    // InternalIdioms.g:951:3: ruleAnyElementLocator
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
                    // InternalIdioms.g:956:2: ( ruleAssignmentLocator )
                    {
                    // InternalIdioms.g:956:2: ( ruleAssignmentLocator )
                    // InternalIdioms.g:957:3: ruleAssignmentLocator
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
                    // InternalIdioms.g:962:2: ( ruleFinalLocator )
                    {
                    // InternalIdioms.g:962:2: ( ruleFinalLocator )
                    // InternalIdioms.g:963:3: ruleFinalLocator
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
                    // InternalIdioms.g:968:2: ( ruleKeywordLocator )
                    {
                    // InternalIdioms.g:968:2: ( ruleKeywordLocator )
                    // InternalIdioms.g:969:3: ruleKeywordLocator
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
                    // InternalIdioms.g:974:2: ( ruleReferredLocator )
                    {
                    // InternalIdioms.g:974:2: ( ruleReferredLocator )
                    // InternalIdioms.g:975:3: ruleReferredLocator
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
                    // InternalIdioms.g:980:2: ( ruleReturnsLocator )
                    {
                    // InternalIdioms.g:980:2: ( ruleReturnsLocator )
                    // InternalIdioms.g:981:3: ruleReturnsLocator
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

            }
        }
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
    // InternalIdioms.g:990:1: rule__Segment__Alternatives : ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) );
    public final void rule__Segment__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:994:1: ( ( ruleCustomSegment ) | ( ruleHalfNewLineSegment ) | ( ruleNewLineSegment ) | ( ruleNoSpaceSegment ) | ( rulePopSegment ) | ( rulePostCommentSegment ) | ( rulePreCommentSegment ) | ( rulePushSegment ) | ( ruleSoftNewLineSegment ) | ( ruleSoftSpaceSegment ) | ( ruleStringSegment ) | ( ruleValueSegment ) | ( ruleWrapAnchorSegment ) | ( ruleWrapBeginAllSegment ) | ( ruleWrapBeginSomeSegment ) | ( ruleWrapEndSegment ) | ( ruleWrapHereSegment ) )
            int alt3=17;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt3=1;
                }
                break;
            case 27:
                {
                alt3=2;
                }
                break;
            case 28:
                {
                alt3=3;
                }
                break;
            case 29:
                {
                alt3=4;
                }
                break;
            case 30:
                {
                alt3=5;
                }
                break;
            case 31:
                {
                alt3=6;
                }
                break;
            case 32:
                {
                alt3=7;
                }
                break;
            case 33:
                {
                alt3=8;
                }
                break;
            case 34:
                {
                alt3=9;
                }
                break;
            case 35:
                {
                alt3=10;
                }
                break;
            case 36:
                {
                alt3=11;
                }
                break;
            case 37:
                {
                alt3=12;
                }
                break;
            case 38:
                {
                alt3=13;
                }
                break;
            case 39:
                {
                alt3=14;
                }
                break;
            case 40:
                {
                alt3=15;
                }
                break;
            case 41:
                {
                alt3=16;
                }
                break;
            case 42:
                {
                alt3=17;
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
                    // InternalIdioms.g:995:2: ( ruleCustomSegment )
                    {
                    // InternalIdioms.g:995:2: ( ruleCustomSegment )
                    // InternalIdioms.g:996:3: ruleCustomSegment
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
                    // InternalIdioms.g:1001:2: ( ruleHalfNewLineSegment )
                    {
                    // InternalIdioms.g:1001:2: ( ruleHalfNewLineSegment )
                    // InternalIdioms.g:1002:3: ruleHalfNewLineSegment
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
                    // InternalIdioms.g:1007:2: ( ruleNewLineSegment )
                    {
                    // InternalIdioms.g:1007:2: ( ruleNewLineSegment )
                    // InternalIdioms.g:1008:3: ruleNewLineSegment
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
                    // InternalIdioms.g:1013:2: ( ruleNoSpaceSegment )
                    {
                    // InternalIdioms.g:1013:2: ( ruleNoSpaceSegment )
                    // InternalIdioms.g:1014:3: ruleNoSpaceSegment
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
                    // InternalIdioms.g:1019:2: ( rulePopSegment )
                    {
                    // InternalIdioms.g:1019:2: ( rulePopSegment )
                    // InternalIdioms.g:1020:3: rulePopSegment
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
                    // InternalIdioms.g:1025:2: ( rulePostCommentSegment )
                    {
                    // InternalIdioms.g:1025:2: ( rulePostCommentSegment )
                    // InternalIdioms.g:1026:3: rulePostCommentSegment
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
                    // InternalIdioms.g:1031:2: ( rulePreCommentSegment )
                    {
                    // InternalIdioms.g:1031:2: ( rulePreCommentSegment )
                    // InternalIdioms.g:1032:3: rulePreCommentSegment
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
                    // InternalIdioms.g:1037:2: ( rulePushSegment )
                    {
                    // InternalIdioms.g:1037:2: ( rulePushSegment )
                    // InternalIdioms.g:1038:3: rulePushSegment
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
                    // InternalIdioms.g:1043:2: ( ruleSoftNewLineSegment )
                    {
                    // InternalIdioms.g:1043:2: ( ruleSoftNewLineSegment )
                    // InternalIdioms.g:1044:3: ruleSoftNewLineSegment
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
                    // InternalIdioms.g:1049:2: ( ruleSoftSpaceSegment )
                    {
                    // InternalIdioms.g:1049:2: ( ruleSoftSpaceSegment )
                    // InternalIdioms.g:1050:3: ruleSoftSpaceSegment
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
                    // InternalIdioms.g:1055:2: ( ruleStringSegment )
                    {
                    // InternalIdioms.g:1055:2: ( ruleStringSegment )
                    // InternalIdioms.g:1056:3: ruleStringSegment
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
                    // InternalIdioms.g:1061:2: ( ruleValueSegment )
                    {
                    // InternalIdioms.g:1061:2: ( ruleValueSegment )
                    // InternalIdioms.g:1062:3: ruleValueSegment
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
                    // InternalIdioms.g:1067:2: ( ruleWrapAnchorSegment )
                    {
                    // InternalIdioms.g:1067:2: ( ruleWrapAnchorSegment )
                    // InternalIdioms.g:1068:3: ruleWrapAnchorSegment
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
                    // InternalIdioms.g:1073:2: ( ruleWrapBeginAllSegment )
                    {
                    // InternalIdioms.g:1073:2: ( ruleWrapBeginAllSegment )
                    // InternalIdioms.g:1074:3: ruleWrapBeginAllSegment
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
                    // InternalIdioms.g:1079:2: ( ruleWrapBeginSomeSegment )
                    {
                    // InternalIdioms.g:1079:2: ( ruleWrapBeginSomeSegment )
                    // InternalIdioms.g:1080:3: ruleWrapBeginSomeSegment
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
                    // InternalIdioms.g:1085:2: ( ruleWrapEndSegment )
                    {
                    // InternalIdioms.g:1085:2: ( ruleWrapEndSegment )
                    // InternalIdioms.g:1086:3: ruleWrapEndSegment
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
                    // InternalIdioms.g:1091:2: ( ruleWrapHereSegment )
                    {
                    // InternalIdioms.g:1091:2: ( ruleWrapHereSegment )
                    // InternalIdioms.g:1092:3: ruleWrapHereSegment
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
    // InternalIdioms.g:1101:1: rule__Idiom__Alternatives_5 : ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) );
    public final void rule__Idiom__Alternatives_5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1105:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) ) | ( ( rule__Idiom__Group_5_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==48) ) {
                alt4=1;
            }
            else if ( (LA4_0==46) ) {
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
                    // InternalIdioms.g:1106:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    {
                    // InternalIdioms.g:1106:2: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 ) )
                    // InternalIdioms.g:1107:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_0());
                    }
                    // InternalIdioms.g:1108:3: ( rule__Idiom__OwnedSubIdiomsAssignment_5_0 )
                    // InternalIdioms.g:1108:4: rule__Idiom__OwnedSubIdiomsAssignment_5_0
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
                    // InternalIdioms.g:1112:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    {
                    // InternalIdioms.g:1112:2: ( ( rule__Idiom__Group_5_1__0 ) )
                    // InternalIdioms.g:1113:3: ( rule__Idiom__Group_5_1__0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIdiomAccess().getGroup_5_1());
                    }
                    // InternalIdioms.g:1114:3: ( rule__Idiom__Group_5_1__0 )
                    // InternalIdioms.g:1114:4: rule__Idiom__Group_5_1__0
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
    // InternalIdioms.g:1122:1: rule__SubIdiom__Alternatives_1 : ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) );
    public final void rule__SubIdiom__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1126:1: ( ( ( rule__SubIdiom__AllAssignment_1_0 ) ) | ( 'each' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==52) ) {
                alt5=1;
            }
            else if ( (LA5_0==11) ) {
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
                    // InternalIdioms.g:1127:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    {
                    // InternalIdioms.g:1127:2: ( ( rule__SubIdiom__AllAssignment_1_0 ) )
                    // InternalIdioms.g:1128:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getSubIdiomAccess().getAllAssignment_1_0());
                    }
                    // InternalIdioms.g:1129:3: ( rule__SubIdiom__AllAssignment_1_0 )
                    // InternalIdioms.g:1129:4: rule__SubIdiom__AllAssignment_1_0
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
                    // InternalIdioms.g:1133:2: ( 'each' )
                    {
                    // InternalIdioms.g:1133:2: ( 'each' )
                    // InternalIdioms.g:1134:3: 'each'
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
    // InternalIdioms.g:1143:1: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 : ( ( ruleSegment ) | ( ruleReferredSegment ) );
    public final void rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1147:1: ( ( ruleSegment ) | ( ruleReferredSegment ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=26 && LA6_0<=42)) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
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
                    // InternalIdioms.g:1148:2: ( ruleSegment )
                    {
                    // InternalIdioms.g:1148:2: ( ruleSegment )
                    // InternalIdioms.g:1149:3: ruleSegment
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
                    // InternalIdioms.g:1154:2: ( ruleReferredSegment )
                    {
                    // InternalIdioms.g:1154:2: ( ruleReferredSegment )
                    // InternalIdioms.g:1155:3: ruleReferredSegment
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
    // InternalIdioms.g:1164:1: rule__IdiomsModel__Group__0 : rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 ;
    public final void rule__IdiomsModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1168:1: ( rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1 )
            // InternalIdioms.g:1169:2: rule__IdiomsModel__Group__0__Impl rule__IdiomsModel__Group__1
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
    // InternalIdioms.g:1176:1: rule__IdiomsModel__Group__0__Impl : ( 'model' ) ;
    public final void rule__IdiomsModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1180:1: ( ( 'model' ) )
            // InternalIdioms.g:1181:1: ( 'model' )
            {
            // InternalIdioms.g:1181:1: ( 'model' )
            // InternalIdioms.g:1182:2: 'model'
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
    // InternalIdioms.g:1191:1: rule__IdiomsModel__Group__1 : rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 ;
    public final void rule__IdiomsModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1195:1: ( rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2 )
            // InternalIdioms.g:1196:2: rule__IdiomsModel__Group__1__Impl rule__IdiomsModel__Group__2
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
    // InternalIdioms.g:1203:1: rule__IdiomsModel__Group__1__Impl : ( ( rule__IdiomsModel__NamesAssignment_1 ) ) ;
    public final void rule__IdiomsModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1207:1: ( ( ( rule__IdiomsModel__NamesAssignment_1 ) ) )
            // InternalIdioms.g:1208:1: ( ( rule__IdiomsModel__NamesAssignment_1 ) )
            {
            // InternalIdioms.g:1208:1: ( ( rule__IdiomsModel__NamesAssignment_1 ) )
            // InternalIdioms.g:1209:2: ( rule__IdiomsModel__NamesAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_1());
            }
            // InternalIdioms.g:1210:2: ( rule__IdiomsModel__NamesAssignment_1 )
            // InternalIdioms.g:1210:3: rule__IdiomsModel__NamesAssignment_1
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
    // InternalIdioms.g:1218:1: rule__IdiomsModel__Group__2 : rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 ;
    public final void rule__IdiomsModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1222:1: ( rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3 )
            // InternalIdioms.g:1223:2: rule__IdiomsModel__Group__2__Impl rule__IdiomsModel__Group__3
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
    // InternalIdioms.g:1230:1: rule__IdiomsModel__Group__2__Impl : ( ( rule__IdiomsModel__Group_2__0 )* ) ;
    public final void rule__IdiomsModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1234:1: ( ( ( rule__IdiomsModel__Group_2__0 )* ) )
            // InternalIdioms.g:1235:1: ( ( rule__IdiomsModel__Group_2__0 )* )
            {
            // InternalIdioms.g:1235:1: ( ( rule__IdiomsModel__Group_2__0 )* )
            // InternalIdioms.g:1236:2: ( rule__IdiomsModel__Group_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getGroup_2());
            }
            // InternalIdioms.g:1237:2: ( rule__IdiomsModel__Group_2__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==13) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalIdioms.g:1237:3: rule__IdiomsModel__Group_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    rule__IdiomsModel__Group_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // InternalIdioms.g:1245:1: rule__IdiomsModel__Group__3 : rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 ;
    public final void rule__IdiomsModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1249:1: ( rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4 )
            // InternalIdioms.g:1250:2: rule__IdiomsModel__Group__3__Impl rule__IdiomsModel__Group__4
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
    // InternalIdioms.g:1257:1: rule__IdiomsModel__Group__3__Impl : ( ( rule__IdiomsModel__OwnedWithsAssignment_3 )* ) ;
    public final void rule__IdiomsModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1261:1: ( ( ( rule__IdiomsModel__OwnedWithsAssignment_3 )* ) )
            // InternalIdioms.g:1262:1: ( ( rule__IdiomsModel__OwnedWithsAssignment_3 )* )
            {
            // InternalIdioms.g:1262:1: ( ( rule__IdiomsModel__OwnedWithsAssignment_3 )* )
            // InternalIdioms.g:1263:2: ( rule__IdiomsModel__OwnedWithsAssignment_3 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3());
            }
            // InternalIdioms.g:1264:2: ( rule__IdiomsModel__OwnedWithsAssignment_3 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalIdioms.g:1264:3: rule__IdiomsModel__OwnedWithsAssignment_3
            	    {
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    rule__IdiomsModel__OwnedWithsAssignment_3();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedWithsAssignment_3());
            }

            }


            }

        }
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
    // InternalIdioms.g:1272:1: rule__IdiomsModel__Group__4 : rule__IdiomsModel__Group__4__Impl rule__IdiomsModel__Group__5 ;
    public final void rule__IdiomsModel__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1276:1: ( rule__IdiomsModel__Group__4__Impl rule__IdiomsModel__Group__5 )
            // InternalIdioms.g:1277:2: rule__IdiomsModel__Group__4__Impl rule__IdiomsModel__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__IdiomsModel__Group__4__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__5();

            state._fsp--;
            if (state.failed) return ;

            }

        }
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
    // InternalIdioms.g:1284:1: rule__IdiomsModel__Group__4__Impl : ( ( rule__IdiomsModel__OwnedImportsAssignment_4 )* ) ;
    public final void rule__IdiomsModel__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1288:1: ( ( ( rule__IdiomsModel__OwnedImportsAssignment_4 )* ) )
            // InternalIdioms.g:1289:1: ( ( rule__IdiomsModel__OwnedImportsAssignment_4 )* )
            {
            // InternalIdioms.g:1289:1: ( ( rule__IdiomsModel__OwnedImportsAssignment_4 )* )
            // InternalIdioms.g:1290:2: ( rule__IdiomsModel__OwnedImportsAssignment_4 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_4());
            }
            // InternalIdioms.g:1291:2: ( rule__IdiomsModel__OwnedImportsAssignment_4 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==14) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalIdioms.g:1291:3: rule__IdiomsModel__OwnedImportsAssignment_4
            	    {
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    rule__IdiomsModel__OwnedImportsAssignment_4();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedImportsAssignment_4());
            }

            }


            }

        }
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


    // $ANTLR start "rule__IdiomsModel__Group__5"
    // InternalIdioms.g:1299:1: rule__IdiomsModel__Group__5 : rule__IdiomsModel__Group__5__Impl ;
    public final void rule__IdiomsModel__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1303:1: ( rule__IdiomsModel__Group__5__Impl )
            // InternalIdioms.g:1304:2: rule__IdiomsModel__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__IdiomsModel__Group__5__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__Group__5"


    // $ANTLR start "rule__IdiomsModel__Group__5__Impl"
    // InternalIdioms.g:1310:1: rule__IdiomsModel__Group__5__Impl : ( ( rule__IdiomsModel__Alternatives_5 )* ) ;
    public final void rule__IdiomsModel__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1314:1: ( ( ( rule__IdiomsModel__Alternatives_5 )* ) )
            // InternalIdioms.g:1315:1: ( ( rule__IdiomsModel__Alternatives_5 )* )
            {
            // InternalIdioms.g:1315:1: ( ( rule__IdiomsModel__Alternatives_5 )* )
            // InternalIdioms.g:1316:2: ( rule__IdiomsModel__Alternatives_5 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getAlternatives_5());
            }
            // InternalIdioms.g:1317:2: ( rule__IdiomsModel__Alternatives_5 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==18||LA10_0==25||LA10_0==43||LA10_0==51) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalIdioms.g:1317:3: rule__IdiomsModel__Alternatives_5
            	    {
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    rule__IdiomsModel__Alternatives_5();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getAlternatives_5());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__Group__5__Impl"


    // $ANTLR start "rule__IdiomsModel__Group_2__0"
    // InternalIdioms.g:1326:1: rule__IdiomsModel__Group_2__0 : rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1 ;
    public final void rule__IdiomsModel__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1330:1: ( rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1 )
            // InternalIdioms.g:1331:2: rule__IdiomsModel__Group_2__0__Impl rule__IdiomsModel__Group_2__1
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
    // InternalIdioms.g:1338:1: rule__IdiomsModel__Group_2__0__Impl : ( '.' ) ;
    public final void rule__IdiomsModel__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1342:1: ( ( '.' ) )
            // InternalIdioms.g:1343:1: ( '.' )
            {
            // InternalIdioms.g:1343:1: ( '.' )
            // InternalIdioms.g:1344:2: '.'
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
    // InternalIdioms.g:1353:1: rule__IdiomsModel__Group_2__1 : rule__IdiomsModel__Group_2__1__Impl ;
    public final void rule__IdiomsModel__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1357:1: ( rule__IdiomsModel__Group_2__1__Impl )
            // InternalIdioms.g:1358:2: rule__IdiomsModel__Group_2__1__Impl
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
    // InternalIdioms.g:1364:1: rule__IdiomsModel__Group_2__1__Impl : ( ( rule__IdiomsModel__NamesAssignment_2_1 ) ) ;
    public final void rule__IdiomsModel__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1368:1: ( ( ( rule__IdiomsModel__NamesAssignment_2_1 ) ) )
            // InternalIdioms.g:1369:1: ( ( rule__IdiomsModel__NamesAssignment_2_1 ) )
            {
            // InternalIdioms.g:1369:1: ( ( rule__IdiomsModel__NamesAssignment_2_1 ) )
            // InternalIdioms.g:1370:2: ( rule__IdiomsModel__NamesAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getNamesAssignment_2_1());
            }
            // InternalIdioms.g:1371:2: ( rule__IdiomsModel__NamesAssignment_2_1 )
            // InternalIdioms.g:1371:3: rule__IdiomsModel__NamesAssignment_2_1
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


    // $ANTLR start "rule__EPackageImport__Group__0"
    // InternalIdioms.g:1380:1: rule__EPackageImport__Group__0 : rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1 ;
    public final void rule__EPackageImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1384:1: ( rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1 )
            // InternalIdioms.g:1385:2: rule__EPackageImport__Group__0__Impl rule__EPackageImport__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
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
    // InternalIdioms.g:1392:1: rule__EPackageImport__Group__0__Impl : ( 'import' ) ;
    public final void rule__EPackageImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1396:1: ( ( 'import' ) )
            // InternalIdioms.g:1397:1: ( 'import' )
            {
            // InternalIdioms.g:1397:1: ( 'import' )
            // InternalIdioms.g:1398:2: 'import'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getImportKeyword_0());
            }
            match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1407:1: rule__EPackageImport__Group__1 : rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2 ;
    public final void rule__EPackageImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1411:1: ( rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2 )
            // InternalIdioms.g:1412:2: rule__EPackageImport__Group__1__Impl rule__EPackageImport__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
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
    // InternalIdioms.g:1419:1: rule__EPackageImport__Group__1__Impl : ( ( rule__EPackageImport__EPackageAssignment_1 ) ) ;
    public final void rule__EPackageImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1423:1: ( ( ( rule__EPackageImport__EPackageAssignment_1 ) ) )
            // InternalIdioms.g:1424:1: ( ( rule__EPackageImport__EPackageAssignment_1 ) )
            {
            // InternalIdioms.g:1424:1: ( ( rule__EPackageImport__EPackageAssignment_1 ) )
            // InternalIdioms.g:1425:2: ( rule__EPackageImport__EPackageAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getEPackageAssignment_1());
            }
            // InternalIdioms.g:1426:2: ( rule__EPackageImport__EPackageAssignment_1 )
            // InternalIdioms.g:1426:3: rule__EPackageImport__EPackageAssignment_1
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
    // InternalIdioms.g:1434:1: rule__EPackageImport__Group__2 : rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3 ;
    public final void rule__EPackageImport__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1438:1: ( rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3 )
            // InternalIdioms.g:1439:2: rule__EPackageImport__Group__2__Impl rule__EPackageImport__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_10);
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
    // InternalIdioms.g:1446:1: rule__EPackageImport__Group__2__Impl : ( ( rule__EPackageImport__Group_2__0 )? ) ;
    public final void rule__EPackageImport__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1450:1: ( ( ( rule__EPackageImport__Group_2__0 )? ) )
            // InternalIdioms.g:1451:1: ( ( rule__EPackageImport__Group_2__0 )? )
            {
            // InternalIdioms.g:1451:1: ( ( rule__EPackageImport__Group_2__0 )? )
            // InternalIdioms.g:1452:2: ( rule__EPackageImport__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getGroup_2());
            }
            // InternalIdioms.g:1453:2: ( rule__EPackageImport__Group_2__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==16) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalIdioms.g:1453:3: rule__EPackageImport__Group_2__0
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
    // InternalIdioms.g:1461:1: rule__EPackageImport__Group__3 : rule__EPackageImport__Group__3__Impl ;
    public final void rule__EPackageImport__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1465:1: ( rule__EPackageImport__Group__3__Impl )
            // InternalIdioms.g:1466:2: rule__EPackageImport__Group__3__Impl
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
    // InternalIdioms.g:1472:1: rule__EPackageImport__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__EPackageImport__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1476:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1477:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1477:1: ( ( ';' )? )
            // InternalIdioms.g:1478:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1479:2: ( ';' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==15) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalIdioms.g:1479:3: ';'
                    {
                    match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return ;

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
    // InternalIdioms.g:1488:1: rule__EPackageImport__Group_2__0 : rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1 ;
    public final void rule__EPackageImport__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1492:1: ( rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1 )
            // InternalIdioms.g:1493:2: rule__EPackageImport__Group_2__0__Impl rule__EPackageImport__Group_2__1
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
    // InternalIdioms.g:1500:1: rule__EPackageImport__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__EPackageImport__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1504:1: ( ( 'as' ) )
            // InternalIdioms.g:1505:1: ( 'as' )
            {
            // InternalIdioms.g:1505:1: ( 'as' )
            // InternalIdioms.g:1506:2: 'as'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getAsKeyword_2_0());
            }
            match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1515:1: rule__EPackageImport__Group_2__1 : rule__EPackageImport__Group_2__1__Impl ;
    public final void rule__EPackageImport__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1519:1: ( rule__EPackageImport__Group_2__1__Impl )
            // InternalIdioms.g:1520:2: rule__EPackageImport__Group_2__1__Impl
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
    // InternalIdioms.g:1526:1: rule__EPackageImport__Group_2__1__Impl : ( ( rule__EPackageImport__AsAssignment_2_1 ) ) ;
    public final void rule__EPackageImport__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1530:1: ( ( ( rule__EPackageImport__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1531:1: ( ( rule__EPackageImport__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1531:1: ( ( rule__EPackageImport__AsAssignment_2_1 ) )
            // InternalIdioms.g:1532:2: ( rule__EPackageImport__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1533:2: ( rule__EPackageImport__AsAssignment_2_1 )
            // InternalIdioms.g:1533:3: rule__EPackageImport__AsAssignment_2_1
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
    // InternalIdioms.g:1542:1: rule__IdiomsImport__Group__0 : rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 ;
    public final void rule__IdiomsImport__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1546:1: ( rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1 )
            // InternalIdioms.g:1547:2: rule__IdiomsImport__Group__0__Impl rule__IdiomsImport__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
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
    // InternalIdioms.g:1554:1: rule__IdiomsImport__Group__0__Impl : ( 'with' ) ;
    public final void rule__IdiomsImport__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1558:1: ( ( 'with' ) )
            // InternalIdioms.g:1559:1: ( 'with' )
            {
            // InternalIdioms.g:1559:1: ( 'with' )
            // InternalIdioms.g:1560:2: 'with'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getWithKeyword_0());
            }
            match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1569:1: rule__IdiomsImport__Group__1 : rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 ;
    public final void rule__IdiomsImport__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1573:1: ( rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2 )
            // InternalIdioms.g:1574:2: rule__IdiomsImport__Group__1__Impl rule__IdiomsImport__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
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
    // InternalIdioms.g:1581:1: rule__IdiomsImport__Group__1__Impl : ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) ;
    public final void rule__IdiomsImport__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1585:1: ( ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) ) )
            // InternalIdioms.g:1586:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            {
            // InternalIdioms.g:1586:1: ( ( rule__IdiomsImport__IdiomsModelAssignment_1 ) )
            // InternalIdioms.g:1587:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelAssignment_1());
            }
            // InternalIdioms.g:1588:2: ( rule__IdiomsImport__IdiomsModelAssignment_1 )
            // InternalIdioms.g:1588:3: rule__IdiomsImport__IdiomsModelAssignment_1
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
    // InternalIdioms.g:1596:1: rule__IdiomsImport__Group__2 : rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 ;
    public final void rule__IdiomsImport__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1600:1: ( rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3 )
            // InternalIdioms.g:1601:2: rule__IdiomsImport__Group__2__Impl rule__IdiomsImport__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_10);
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
    // InternalIdioms.g:1608:1: rule__IdiomsImport__Group__2__Impl : ( ( rule__IdiomsImport__Group_2__0 )? ) ;
    public final void rule__IdiomsImport__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1612:1: ( ( ( rule__IdiomsImport__Group_2__0 )? ) )
            // InternalIdioms.g:1613:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            {
            // InternalIdioms.g:1613:1: ( ( rule__IdiomsImport__Group_2__0 )? )
            // InternalIdioms.g:1614:2: ( rule__IdiomsImport__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getGroup_2());
            }
            // InternalIdioms.g:1615:2: ( rule__IdiomsImport__Group_2__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==16) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalIdioms.g:1615:3: rule__IdiomsImport__Group_2__0
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
    // InternalIdioms.g:1623:1: rule__IdiomsImport__Group__3 : rule__IdiomsImport__Group__3__Impl ;
    public final void rule__IdiomsImport__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1627:1: ( rule__IdiomsImport__Group__3__Impl )
            // InternalIdioms.g:1628:2: rule__IdiomsImport__Group__3__Impl
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
    // InternalIdioms.g:1634:1: rule__IdiomsImport__Group__3__Impl : ( ( ';' )? ) ;
    public final void rule__IdiomsImport__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1638:1: ( ( ( ';' )? ) )
            // InternalIdioms.g:1639:1: ( ( ';' )? )
            {
            // InternalIdioms.g:1639:1: ( ( ';' )? )
            // InternalIdioms.g:1640:2: ( ';' )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());
            }
            // InternalIdioms.g:1641:2: ( ';' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalIdioms.g:1641:3: ';'
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
    // InternalIdioms.g:1650:1: rule__IdiomsImport__Group_2__0 : rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 ;
    public final void rule__IdiomsImport__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1654:1: ( rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1 )
            // InternalIdioms.g:1655:2: rule__IdiomsImport__Group_2__0__Impl rule__IdiomsImport__Group_2__1
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
    // InternalIdioms.g:1662:1: rule__IdiomsImport__Group_2__0__Impl : ( 'as' ) ;
    public final void rule__IdiomsImport__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1666:1: ( ( 'as' ) )
            // InternalIdioms.g:1667:1: ( 'as' )
            {
            // InternalIdioms.g:1667:1: ( 'as' )
            // InternalIdioms.g:1668:2: 'as'
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
    // InternalIdioms.g:1677:1: rule__IdiomsImport__Group_2__1 : rule__IdiomsImport__Group_2__1__Impl ;
    public final void rule__IdiomsImport__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1681:1: ( rule__IdiomsImport__Group_2__1__Impl )
            // InternalIdioms.g:1682:2: rule__IdiomsImport__Group_2__1__Impl
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
    // InternalIdioms.g:1688:1: rule__IdiomsImport__Group_2__1__Impl : ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) ;
    public final void rule__IdiomsImport__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1692:1: ( ( ( rule__IdiomsImport__AsAssignment_2_1 ) ) )
            // InternalIdioms.g:1693:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            {
            // InternalIdioms.g:1693:1: ( ( rule__IdiomsImport__AsAssignment_2_1 ) )
            // InternalIdioms.g:1694:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getAsAssignment_2_1());
            }
            // InternalIdioms.g:1695:2: ( rule__IdiomsImport__AsAssignment_2_1 )
            // InternalIdioms.g:1695:3: rule__IdiomsImport__AsAssignment_2_1
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
    // InternalIdioms.g:1704:1: rule__LocatorDeclaration__Group__0 : rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 ;
    public final void rule__LocatorDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1708:1: ( rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1 )
            // InternalIdioms.g:1709:2: rule__LocatorDeclaration__Group__0__Impl rule__LocatorDeclaration__Group__1
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
    // InternalIdioms.g:1716:1: rule__LocatorDeclaration__Group__0__Impl : ( 'locator' ) ;
    public final void rule__LocatorDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1720:1: ( ( 'locator' ) )
            // InternalIdioms.g:1721:1: ( 'locator' )
            {
            // InternalIdioms.g:1721:1: ( 'locator' )
            // InternalIdioms.g:1722:2: 'locator'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());
            }
            match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1731:1: rule__LocatorDeclaration__Group__1 : rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 ;
    public final void rule__LocatorDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1735:1: ( rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2 )
            // InternalIdioms.g:1736:2: rule__LocatorDeclaration__Group__1__Impl rule__LocatorDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_11);
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
    // InternalIdioms.g:1743:1: rule__LocatorDeclaration__Group__1__Impl : ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) ;
    public final void rule__LocatorDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1747:1: ( ( ( rule__LocatorDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:1748:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:1748:1: ( ( rule__LocatorDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:1749:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:1750:2: ( rule__LocatorDeclaration__NameAssignment_1 )
            // InternalIdioms.g:1750:3: rule__LocatorDeclaration__NameAssignment_1
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
    // InternalIdioms.g:1758:1: rule__LocatorDeclaration__Group__2 : rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 ;
    public final void rule__LocatorDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1762:1: ( rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3 )
            // InternalIdioms.g:1763:2: rule__LocatorDeclaration__Group__2__Impl rule__LocatorDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_12);
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
    // InternalIdioms.g:1770:1: rule__LocatorDeclaration__Group__2__Impl : ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__LocatorDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1774:1: ( ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:1775:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:1775:1: ( ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:1776:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:1777:2: ( rule__LocatorDeclaration__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:1777:3: rule__LocatorDeclaration__OwnedLocatorAssignment_2
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
    // InternalIdioms.g:1785:1: rule__LocatorDeclaration__Group__3 : rule__LocatorDeclaration__Group__3__Impl ;
    public final void rule__LocatorDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1789:1: ( rule__LocatorDeclaration__Group__3__Impl )
            // InternalIdioms.g:1790:2: rule__LocatorDeclaration__Group__3__Impl
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
    // InternalIdioms.g:1796:1: rule__LocatorDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__LocatorDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1800:1: ( ( ';' ) )
            // InternalIdioms.g:1801:1: ( ';' )
            {
            // InternalIdioms.g:1801:1: ( ';' )
            // InternalIdioms.g:1802:2: ';'
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
    // InternalIdioms.g:1812:1: rule__AnyAssignmentLocator__Group__0 : rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 ;
    public final void rule__AnyAssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1816:1: ( rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1 )
            // InternalIdioms.g:1817:2: rule__AnyAssignmentLocator__Group__0__Impl rule__AnyAssignmentLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_13);
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
    // InternalIdioms.g:1824:1: rule__AnyAssignmentLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyAssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1828:1: ( ( () ) )
            // InternalIdioms.g:1829:1: ( () )
            {
            // InternalIdioms.g:1829:1: ( () )
            // InternalIdioms.g:1830:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0());
            }
            // InternalIdioms.g:1831:2: ()
            // InternalIdioms.g:1831:3:
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
    // InternalIdioms.g:1839:1: rule__AnyAssignmentLocator__Group__1 : rule__AnyAssignmentLocator__Group__1__Impl ;
    public final void rule__AnyAssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1843:1: ( rule__AnyAssignmentLocator__Group__1__Impl )
            // InternalIdioms.g:1844:2: rule__AnyAssignmentLocator__Group__1__Impl
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
    // InternalIdioms.g:1850:1: rule__AnyAssignmentLocator__Group__1__Impl : ( 'any-assignment' ) ;
    public final void rule__AnyAssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1854:1: ( ( 'any-assignment' ) )
            // InternalIdioms.g:1855:1: ( 'any-assignment' )
            {
            // InternalIdioms.g:1855:1: ( 'any-assignment' )
            // InternalIdioms.g:1856:2: 'any-assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());
            }
            match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1866:1: rule__AnyElementLocator__Group__0 : rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 ;
    public final void rule__AnyElementLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1870:1: ( rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1 )
            // InternalIdioms.g:1871:2: rule__AnyElementLocator__Group__0__Impl rule__AnyElementLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_14);
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
    // InternalIdioms.g:1878:1: rule__AnyElementLocator__Group__0__Impl : ( () ) ;
    public final void rule__AnyElementLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1882:1: ( ( () ) )
            // InternalIdioms.g:1883:1: ( () )
            {
            // InternalIdioms.g:1883:1: ( () )
            // InternalIdioms.g:1884:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0());
            }
            // InternalIdioms.g:1885:2: ()
            // InternalIdioms.g:1885:3:
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
    // InternalIdioms.g:1893:1: rule__AnyElementLocator__Group__1 : rule__AnyElementLocator__Group__1__Impl ;
    public final void rule__AnyElementLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1897:1: ( rule__AnyElementLocator__Group__1__Impl )
            // InternalIdioms.g:1898:2: rule__AnyElementLocator__Group__1__Impl
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
    // InternalIdioms.g:1904:1: rule__AnyElementLocator__Group__1__Impl : ( 'any-element' ) ;
    public final void rule__AnyElementLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1908:1: ( ( 'any-element' ) )
            // InternalIdioms.g:1909:1: ( 'any-element' )
            {
            // InternalIdioms.g:1909:1: ( 'any-element' )
            // InternalIdioms.g:1910:2: 'any-element'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());
            }
            match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1920:1: rule__AssignmentLocator__Group__0 : rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 ;
    public final void rule__AssignmentLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1924:1: ( rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1 )
            // InternalIdioms.g:1925:2: rule__AssignmentLocator__Group__0__Impl rule__AssignmentLocator__Group__1
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
    // InternalIdioms.g:1932:1: rule__AssignmentLocator__Group__0__Impl : ( 'assignment' ) ;
    public final void rule__AssignmentLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1936:1: ( ( 'assignment' ) )
            // InternalIdioms.g:1937:1: ( 'assignment' )
            {
            // InternalIdioms.g:1937:1: ( 'assignment' )
            // InternalIdioms.g:1938:2: 'assignment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:1947:1: rule__AssignmentLocator__Group__1 : rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 ;
    public final void rule__AssignmentLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1951:1: ( rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2 )
            // InternalIdioms.g:1952:2: rule__AssignmentLocator__Group__1__Impl rule__AssignmentLocator__Group__2
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
    // InternalIdioms.g:1959:1: rule__AssignmentLocator__Group__1__Impl : ( ( rule__AssignmentLocator__Group_1__0 )? ) ;
    public final void rule__AssignmentLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1963:1: ( ( ( rule__AssignmentLocator__Group_1__0 )? ) )
            // InternalIdioms.g:1964:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:1964:1: ( ( rule__AssignmentLocator__Group_1__0 )? )
            // InternalIdioms.g:1965:2: ( rule__AssignmentLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:1966:2: ( rule__AssignmentLocator__Group_1__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==22) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // InternalIdioms.g:1966:3: rule__AssignmentLocator__Group_1__0
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
    // InternalIdioms.g:1974:1: rule__AssignmentLocator__Group__2 : rule__AssignmentLocator__Group__2__Impl ;
    public final void rule__AssignmentLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1978:1: ( rule__AssignmentLocator__Group__2__Impl )
            // InternalIdioms.g:1979:2: rule__AssignmentLocator__Group__2__Impl
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
    // InternalIdioms.g:1985:1: rule__AssignmentLocator__Group__2__Impl : ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) ;
    public final void rule__AssignmentLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:1989:1: ( ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) ) )
            // InternalIdioms.g:1990:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            {
            // InternalIdioms.g:1990:1: ( ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 ) )
            // InternalIdioms.g:1991:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureAssignment_2());
            }
            // InternalIdioms.g:1992:2: ( rule__AssignmentLocator__EStructuralFeatureAssignment_2 )
            // InternalIdioms.g:1992:3: rule__AssignmentLocator__EStructuralFeatureAssignment_2
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
    // InternalIdioms.g:2001:1: rule__AssignmentLocator__Group_1__0 : rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 ;
    public final void rule__AssignmentLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2005:1: ( rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1 )
            // InternalIdioms.g:2006:2: rule__AssignmentLocator__Group_1__0__Impl rule__AssignmentLocator__Group_1__1
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
    // InternalIdioms.g:2013:1: rule__AssignmentLocator__Group_1__0__Impl : ( ( rule__AssignmentLocator__Group_1_0__0 )? ) ;
    public final void rule__AssignmentLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2017:1: ( ( ( rule__AssignmentLocator__Group_1_0__0 )? ) )
            // InternalIdioms.g:2018:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            {
            // InternalIdioms.g:2018:1: ( ( rule__AssignmentLocator__Group_1_0__0 )? )
            // InternalIdioms.g:2019:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getGroup_1_0());
            }
            // InternalIdioms.g:2020:2: ( rule__AssignmentLocator__Group_1_0__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==22) ) {
                    int LA16_2 = input.LA(3);

                    if ( (LA16_2==RULE_ID) ) {
                        int LA16_3 = input.LA(4);

                        if ( (LA16_3==22) ) {
                            alt16=1;
                        }
                    }
                }
            }
            switch (alt16) {
                case 1 :
                    // InternalIdioms.g:2020:3: rule__AssignmentLocator__Group_1_0__0
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
    // InternalIdioms.g:2028:1: rule__AssignmentLocator__Group_1__1 : rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 ;
    public final void rule__AssignmentLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2032:1: ( rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2 )
            // InternalIdioms.g:2033:2: rule__AssignmentLocator__Group_1__1__Impl rule__AssignmentLocator__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:2040:1: rule__AssignmentLocator__Group_1__1__Impl : ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) ;
    public final void rule__AssignmentLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2044:1: ( ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) ) )
            // InternalIdioms.g:2045:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            {
            // InternalIdioms.g:2045:1: ( ( rule__AssignmentLocator__EClassAssignment_1_1 ) )
            // InternalIdioms.g:2046:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassAssignment_1_1());
            }
            // InternalIdioms.g:2047:2: ( rule__AssignmentLocator__EClassAssignment_1_1 )
            // InternalIdioms.g:2047:3: rule__AssignmentLocator__EClassAssignment_1_1
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
    // InternalIdioms.g:2055:1: rule__AssignmentLocator__Group_1__2 : rule__AssignmentLocator__Group_1__2__Impl ;
    public final void rule__AssignmentLocator__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2059:1: ( rule__AssignmentLocator__Group_1__2__Impl )
            // InternalIdioms.g:2060:2: rule__AssignmentLocator__Group_1__2__Impl
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
    // InternalIdioms.g:2066:1: rule__AssignmentLocator__Group_1__2__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2070:1: ( ( '::' ) )
            // InternalIdioms.g:2071:1: ( '::' )
            {
            // InternalIdioms.g:2071:1: ( '::' )
            // InternalIdioms.g:2072:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2082:1: rule__AssignmentLocator__Group_1_0__0 : rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 ;
    public final void rule__AssignmentLocator__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2086:1: ( rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1 )
            // InternalIdioms.g:2087:2: rule__AssignmentLocator__Group_1_0__0__Impl rule__AssignmentLocator__Group_1_0__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:2094:1: rule__AssignmentLocator__Group_1_0__0__Impl : ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) ;
    public final void rule__AssignmentLocator__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2098:1: ( ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) ) )
            // InternalIdioms.g:2099:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            {
            // InternalIdioms.g:2099:1: ( ( rule__AssignmentLocator__EPackageAssignment_1_0_0 ) )
            // InternalIdioms.g:2100:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageAssignment_1_0_0());
            }
            // InternalIdioms.g:2101:2: ( rule__AssignmentLocator__EPackageAssignment_1_0_0 )
            // InternalIdioms.g:2101:3: rule__AssignmentLocator__EPackageAssignment_1_0_0
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
    // InternalIdioms.g:2109:1: rule__AssignmentLocator__Group_1_0__1 : rule__AssignmentLocator__Group_1_0__1__Impl ;
    public final void rule__AssignmentLocator__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2113:1: ( rule__AssignmentLocator__Group_1_0__1__Impl )
            // InternalIdioms.g:2114:2: rule__AssignmentLocator__Group_1_0__1__Impl
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
    // InternalIdioms.g:2120:1: rule__AssignmentLocator__Group_1_0__1__Impl : ( '::' ) ;
    public final void rule__AssignmentLocator__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2124:1: ( ( '::' ) )
            // InternalIdioms.g:2125:1: ( '::' )
            {
            // InternalIdioms.g:2125:1: ( '::' )
            // InternalIdioms.g:2126:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2136:1: rule__FinalLocator__Group__0 : rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 ;
    public final void rule__FinalLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2140:1: ( rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1 )
            // InternalIdioms.g:2141:2: rule__FinalLocator__Group__0__Impl rule__FinalLocator__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_16);
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
    // InternalIdioms.g:2148:1: rule__FinalLocator__Group__0__Impl : ( () ) ;
    public final void rule__FinalLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2152:1: ( ( () ) )
            // InternalIdioms.g:2153:1: ( () )
            {
            // InternalIdioms.g:2153:1: ( () )
            // InternalIdioms.g:2154:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0());
            }
            // InternalIdioms.g:2155:2: ()
            // InternalIdioms.g:2155:3:
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
    // InternalIdioms.g:2163:1: rule__FinalLocator__Group__1 : rule__FinalLocator__Group__1__Impl ;
    public final void rule__FinalLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2167:1: ( rule__FinalLocator__Group__1__Impl )
            // InternalIdioms.g:2168:2: rule__FinalLocator__Group__1__Impl
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
    // InternalIdioms.g:2174:1: rule__FinalLocator__Group__1__Impl : ( 'final' ) ;
    public final void rule__FinalLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2178:1: ( ( 'final' ) )
            // InternalIdioms.g:2179:1: ( 'final' )
            {
            // InternalIdioms.g:2179:1: ( 'final' )
            // InternalIdioms.g:2180:2: 'final'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2190:1: rule__ReturnsLocator__Group__0 : rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 ;
    public final void rule__ReturnsLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2194:1: ( rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1 )
            // InternalIdioms.g:2195:2: rule__ReturnsLocator__Group__0__Impl rule__ReturnsLocator__Group__1
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
    // InternalIdioms.g:2202:1: rule__ReturnsLocator__Group__0__Impl : ( 'returns' ) ;
    public final void rule__ReturnsLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2206:1: ( ( 'returns' ) )
            // InternalIdioms.g:2207:1: ( 'returns' )
            {
            // InternalIdioms.g:2207:1: ( 'returns' )
            // InternalIdioms.g:2208:2: 'returns'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());
            }
            match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2217:1: rule__ReturnsLocator__Group__1 : rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 ;
    public final void rule__ReturnsLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2221:1: ( rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2 )
            // InternalIdioms.g:2222:2: rule__ReturnsLocator__Group__1__Impl rule__ReturnsLocator__Group__2
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
    // InternalIdioms.g:2229:1: rule__ReturnsLocator__Group__1__Impl : ( ( rule__ReturnsLocator__Group_1__0 )? ) ;
    public final void rule__ReturnsLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2233:1: ( ( ( rule__ReturnsLocator__Group_1__0 )? ) )
            // InternalIdioms.g:2234:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            {
            // InternalIdioms.g:2234:1: ( ( rule__ReturnsLocator__Group_1__0 )? )
            // InternalIdioms.g:2235:2: ( rule__ReturnsLocator__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getGroup_1());
            }
            // InternalIdioms.g:2236:2: ( rule__ReturnsLocator__Group_1__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==22) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // InternalIdioms.g:2236:3: rule__ReturnsLocator__Group_1__0
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
    // InternalIdioms.g:2244:1: rule__ReturnsLocator__Group__2 : rule__ReturnsLocator__Group__2__Impl ;
    public final void rule__ReturnsLocator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2248:1: ( rule__ReturnsLocator__Group__2__Impl )
            // InternalIdioms.g:2249:2: rule__ReturnsLocator__Group__2__Impl
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
    // InternalIdioms.g:2255:1: rule__ReturnsLocator__Group__2__Impl : ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) ;
    public final void rule__ReturnsLocator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2259:1: ( ( ( rule__ReturnsLocator__EClassAssignment_2 ) ) )
            // InternalIdioms.g:2260:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            {
            // InternalIdioms.g:2260:1: ( ( rule__ReturnsLocator__EClassAssignment_2 ) )
            // InternalIdioms.g:2261:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassAssignment_2());
            }
            // InternalIdioms.g:2262:2: ( rule__ReturnsLocator__EClassAssignment_2 )
            // InternalIdioms.g:2262:3: rule__ReturnsLocator__EClassAssignment_2
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
    // InternalIdioms.g:2271:1: rule__ReturnsLocator__Group_1__0 : rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 ;
    public final void rule__ReturnsLocator__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2275:1: ( rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1 )
            // InternalIdioms.g:2276:2: rule__ReturnsLocator__Group_1__0__Impl rule__ReturnsLocator__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:2283:1: rule__ReturnsLocator__Group_1__0__Impl : ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) ;
    public final void rule__ReturnsLocator__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2287:1: ( ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) ) )
            // InternalIdioms.g:2288:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            {
            // InternalIdioms.g:2288:1: ( ( rule__ReturnsLocator__EPackageAssignment_1_0 ) )
            // InternalIdioms.g:2289:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageAssignment_1_0());
            }
            // InternalIdioms.g:2290:2: ( rule__ReturnsLocator__EPackageAssignment_1_0 )
            // InternalIdioms.g:2290:3: rule__ReturnsLocator__EPackageAssignment_1_0
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
    // InternalIdioms.g:2298:1: rule__ReturnsLocator__Group_1__1 : rule__ReturnsLocator__Group_1__1__Impl ;
    public final void rule__ReturnsLocator__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2302:1: ( rule__ReturnsLocator__Group_1__1__Impl )
            // InternalIdioms.g:2303:2: rule__ReturnsLocator__Group_1__1__Impl
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
    // InternalIdioms.g:2309:1: rule__ReturnsLocator__Group_1__1__Impl : ( '::' ) ;
    public final void rule__ReturnsLocator__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2313:1: ( ( '::' ) )
            // InternalIdioms.g:2314:1: ( '::' )
            {
            // InternalIdioms.g:2314:1: ( '::' )
            // InternalIdioms.g:2315:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2325:1: rule__ReferredLocator__Group__0 : rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 ;
    public final void rule__ReferredLocator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2329:1: ( rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1 )
            // InternalIdioms.g:2330:2: rule__ReferredLocator__Group__0__Impl rule__ReferredLocator__Group__1
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
    // InternalIdioms.g:2337:1: rule__ReferredLocator__Group__0__Impl : ( ( rule__ReferredLocator__Group_0__0 )? ) ;
    public final void rule__ReferredLocator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2341:1: ( ( ( rule__ReferredLocator__Group_0__0 )? ) )
            // InternalIdioms.g:2342:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            {
            // InternalIdioms.g:2342:1: ( ( rule__ReferredLocator__Group_0__0 )? )
            // InternalIdioms.g:2343:2: ( rule__ReferredLocator__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getGroup_0());
            }
            // InternalIdioms.g:2344:2: ( rule__ReferredLocator__Group_0__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==22) ) {
                    alt18=1;
                }
            }
            switch (alt18) {
                case 1 :
                    // InternalIdioms.g:2344:3: rule__ReferredLocator__Group_0__0
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
    // InternalIdioms.g:2352:1: rule__ReferredLocator__Group__1 : rule__ReferredLocator__Group__1__Impl ;
    public final void rule__ReferredLocator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2356:1: ( rule__ReferredLocator__Group__1__Impl )
            // InternalIdioms.g:2357:2: rule__ReferredLocator__Group__1__Impl
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
    // InternalIdioms.g:2363:1: rule__ReferredLocator__Group__1__Impl : ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredLocator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2367:1: ( ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:2368:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:2368:1: ( ( rule__ReferredLocator__LocatorDeclarationAssignment_1 ) )
            // InternalIdioms.g:2369:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationAssignment_1());
            }
            // InternalIdioms.g:2370:2: ( rule__ReferredLocator__LocatorDeclarationAssignment_1 )
            // InternalIdioms.g:2370:3: rule__ReferredLocator__LocatorDeclarationAssignment_1
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
    // InternalIdioms.g:2379:1: rule__ReferredLocator__Group_0__0 : rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 ;
    public final void rule__ReferredLocator__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2383:1: ( rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1 )
            // InternalIdioms.g:2384:2: rule__ReferredLocator__Group_0__0__Impl rule__ReferredLocator__Group_0__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:2391:1: rule__ReferredLocator__Group_0__0__Impl : ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredLocator__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2395:1: ( ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:2396:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:2396:1: ( ( rule__ReferredLocator__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:2397:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:2398:2: ( rule__ReferredLocator__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:2398:3: rule__ReferredLocator__IdiomsModelAssignment_0_0
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
    // InternalIdioms.g:2406:1: rule__ReferredLocator__Group_0__1 : rule__ReferredLocator__Group_0__1__Impl ;
    public final void rule__ReferredLocator__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2410:1: ( rule__ReferredLocator__Group_0__1__Impl )
            // InternalIdioms.g:2411:2: rule__ReferredLocator__Group_0__1__Impl
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
    // InternalIdioms.g:2417:1: rule__ReferredLocator__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredLocator__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2421:1: ( ( '::' ) )
            // InternalIdioms.g:2422:1: ( '::' )
            {
            // InternalIdioms.g:2422:1: ( '::' )
            // InternalIdioms.g:2423:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2433:1: rule__SegmentDeclaration__Group__0 : rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 ;
    public final void rule__SegmentDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2437:1: ( rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1 )
            // InternalIdioms.g:2438:2: rule__SegmentDeclaration__Group__0__Impl rule__SegmentDeclaration__Group__1
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
    // InternalIdioms.g:2445:1: rule__SegmentDeclaration__Group__0__Impl : ( 'segment' ) ;
    public final void rule__SegmentDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2449:1: ( ( 'segment' ) )
            // InternalIdioms.g:2450:1: ( 'segment' )
            {
            // InternalIdioms.g:2450:1: ( 'segment' )
            // InternalIdioms.g:2451:2: 'segment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());
            }
            match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2460:1: rule__SegmentDeclaration__Group__1 : rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 ;
    public final void rule__SegmentDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2464:1: ( rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2 )
            // InternalIdioms.g:2465:2: rule__SegmentDeclaration__Group__1__Impl rule__SegmentDeclaration__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
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
    // InternalIdioms.g:2472:1: rule__SegmentDeclaration__Group__1__Impl : ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) ;
    public final void rule__SegmentDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2476:1: ( ( ( rule__SegmentDeclaration__NameAssignment_1 ) ) )
            // InternalIdioms.g:2477:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            {
            // InternalIdioms.g:2477:1: ( ( rule__SegmentDeclaration__NameAssignment_1 ) )
            // InternalIdioms.g:2478:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getNameAssignment_1());
            }
            // InternalIdioms.g:2479:2: ( rule__SegmentDeclaration__NameAssignment_1 )
            // InternalIdioms.g:2479:3: rule__SegmentDeclaration__NameAssignment_1
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
    // InternalIdioms.g:2487:1: rule__SegmentDeclaration__Group__2 : rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 ;
    public final void rule__SegmentDeclaration__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2491:1: ( rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3 )
            // InternalIdioms.g:2492:2: rule__SegmentDeclaration__Group__2__Impl rule__SegmentDeclaration__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_12);
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
    // InternalIdioms.g:2499:1: rule__SegmentDeclaration__Group__2__Impl : ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) ;
    public final void rule__SegmentDeclaration__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2503:1: ( ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) ) )
            // InternalIdioms.g:2504:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            {
            // InternalIdioms.g:2504:1: ( ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 ) )
            // InternalIdioms.g:2505:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentAssignment_2());
            }
            // InternalIdioms.g:2506:2: ( rule__SegmentDeclaration__OwnedSegmentAssignment_2 )
            // InternalIdioms.g:2506:3: rule__SegmentDeclaration__OwnedSegmentAssignment_2
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
    // InternalIdioms.g:2514:1: rule__SegmentDeclaration__Group__3 : rule__SegmentDeclaration__Group__3__Impl ;
    public final void rule__SegmentDeclaration__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2518:1: ( rule__SegmentDeclaration__Group__3__Impl )
            // InternalIdioms.g:2519:2: rule__SegmentDeclaration__Group__3__Impl
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
    // InternalIdioms.g:2525:1: rule__SegmentDeclaration__Group__3__Impl : ( ';' ) ;
    public final void rule__SegmentDeclaration__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2529:1: ( ( ';' ) )
            // InternalIdioms.g:2530:1: ( ';' )
            {
            // InternalIdioms.g:2530:1: ( ';' )
            // InternalIdioms.g:2531:2: ';'
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
    // InternalIdioms.g:2541:1: rule__CustomSegment__Group__0 : rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 ;
    public final void rule__CustomSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2545:1: ( rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1 )
            // InternalIdioms.g:2546:2: rule__CustomSegment__Group__0__Impl rule__CustomSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
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
    // InternalIdioms.g:2553:1: rule__CustomSegment__Group__0__Impl : ( 'custom' ) ;
    public final void rule__CustomSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2557:1: ( ( 'custom' ) )
            // InternalIdioms.g:2558:1: ( 'custom' )
            {
            // InternalIdioms.g:2558:1: ( 'custom' )
            // InternalIdioms.g:2559:2: 'custom'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2568:1: rule__CustomSegment__Group__1 : rule__CustomSegment__Group__1__Impl ;
    public final void rule__CustomSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2572:1: ( rule__CustomSegment__Group__1__Impl )
            // InternalIdioms.g:2573:2: rule__CustomSegment__Group__1__Impl
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
    // InternalIdioms.g:2579:1: rule__CustomSegment__Group__1__Impl : ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) ;
    public final void rule__CustomSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2583:1: ( ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) ) )
            // InternalIdioms.g:2584:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            {
            // InternalIdioms.g:2584:1: ( ( rule__CustomSegment__SupportClassNameAssignment_1 ) )
            // InternalIdioms.g:2585:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCustomSegmentAccess().getSupportClassNameAssignment_1());
            }
            // InternalIdioms.g:2586:2: ( rule__CustomSegment__SupportClassNameAssignment_1 )
            // InternalIdioms.g:2586:3: rule__CustomSegment__SupportClassNameAssignment_1
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
    // InternalIdioms.g:2595:1: rule__HalfNewLineSegment__Group__0 : rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 ;
    public final void rule__HalfNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2599:1: ( rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1 )
            // InternalIdioms.g:2600:2: rule__HalfNewLineSegment__Group__0__Impl rule__HalfNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_18);
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
    // InternalIdioms.g:2607:1: rule__HalfNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__HalfNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2611:1: ( ( () ) )
            // InternalIdioms.g:2612:1: ( () )
            {
            // InternalIdioms.g:2612:1: ( () )
            // InternalIdioms.g:2613:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2614:2: ()
            // InternalIdioms.g:2614:3:
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
    // InternalIdioms.g:2622:1: rule__HalfNewLineSegment__Group__1 : rule__HalfNewLineSegment__Group__1__Impl ;
    public final void rule__HalfNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2626:1: ( rule__HalfNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:2627:2: rule__HalfNewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:2633:1: rule__HalfNewLineSegment__Group__1__Impl : ( 'half-new-line' ) ;
    public final void rule__HalfNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2637:1: ( ( 'half-new-line' ) )
            // InternalIdioms.g:2638:1: ( 'half-new-line' )
            {
            // InternalIdioms.g:2638:1: ( 'half-new-line' )
            // InternalIdioms.g:2639:2: 'half-new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1());
            }
            match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2649:1: rule__NewLineSegment__Group__0 : rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 ;
    public final void rule__NewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2653:1: ( rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1 )
            // InternalIdioms.g:2654:2: rule__NewLineSegment__Group__0__Impl rule__NewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_19);
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
    // InternalIdioms.g:2661:1: rule__NewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__NewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2665:1: ( ( () ) )
            // InternalIdioms.g:2666:1: ( () )
            {
            // InternalIdioms.g:2666:1: ( () )
            // InternalIdioms.g:2667:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2668:2: ()
            // InternalIdioms.g:2668:3:
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
    // InternalIdioms.g:2676:1: rule__NewLineSegment__Group__1 : rule__NewLineSegment__Group__1__Impl ;
    public final void rule__NewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2680:1: ( rule__NewLineSegment__Group__1__Impl )
            // InternalIdioms.g:2681:2: rule__NewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:2687:1: rule__NewLineSegment__Group__1__Impl : ( 'new-line' ) ;
    public final void rule__NewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2691:1: ( ( 'new-line' ) )
            // InternalIdioms.g:2692:1: ( 'new-line' )
            {
            // InternalIdioms.g:2692:1: ( 'new-line' )
            // InternalIdioms.g:2693:2: 'new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2703:1: rule__NoSpaceSegment__Group__0 : rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 ;
    public final void rule__NoSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2707:1: ( rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1 )
            // InternalIdioms.g:2708:2: rule__NoSpaceSegment__Group__0__Impl rule__NoSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_20);
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
    // InternalIdioms.g:2715:1: rule__NoSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__NoSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2719:1: ( ( () ) )
            // InternalIdioms.g:2720:1: ( () )
            {
            // InternalIdioms.g:2720:1: ( () )
            // InternalIdioms.g:2721:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0());
            }
            // InternalIdioms.g:2722:2: ()
            // InternalIdioms.g:2722:3:
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
    // InternalIdioms.g:2730:1: rule__NoSpaceSegment__Group__1 : rule__NoSpaceSegment__Group__1__Impl ;
    public final void rule__NoSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2734:1: ( rule__NoSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:2735:2: rule__NoSpaceSegment__Group__1__Impl
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
    // InternalIdioms.g:2741:1: rule__NoSpaceSegment__Group__1__Impl : ( 'no-space' ) ;
    public final void rule__NoSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2745:1: ( ( 'no-space' ) )
            // InternalIdioms.g:2746:1: ( 'no-space' )
            {
            // InternalIdioms.g:2746:1: ( 'no-space' )
            // InternalIdioms.g:2747:2: 'no-space'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2757:1: rule__PopSegment__Group__0 : rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 ;
    public final void rule__PopSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2761:1: ( rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1 )
            // InternalIdioms.g:2762:2: rule__PopSegment__Group__0__Impl rule__PopSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_21);
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
    // InternalIdioms.g:2769:1: rule__PopSegment__Group__0__Impl : ( () ) ;
    public final void rule__PopSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2773:1: ( ( () ) )
            // InternalIdioms.g:2774:1: ( () )
            {
            // InternalIdioms.g:2774:1: ( () )
            // InternalIdioms.g:2775:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getPopSegmentAction_0());
            }
            // InternalIdioms.g:2776:2: ()
            // InternalIdioms.g:2776:3:
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
    // InternalIdioms.g:2784:1: rule__PopSegment__Group__1 : rule__PopSegment__Group__1__Impl ;
    public final void rule__PopSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2788:1: ( rule__PopSegment__Group__1__Impl )
            // InternalIdioms.g:2789:2: rule__PopSegment__Group__1__Impl
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
    // InternalIdioms.g:2795:1: rule__PopSegment__Group__1__Impl : ( 'pop' ) ;
    public final void rule__PopSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2799:1: ( ( 'pop' ) )
            // InternalIdioms.g:2800:1: ( 'pop' )
            {
            // InternalIdioms.g:2800:1: ( 'pop' )
            // InternalIdioms.g:2801:2: 'pop'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPopSegmentAccess().getPopKeyword_1());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2811:1: rule__PostCommentSegment__Group__0 : rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 ;
    public final void rule__PostCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2815:1: ( rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1 )
            // InternalIdioms.g:2816:2: rule__PostCommentSegment__Group__0__Impl rule__PostCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_22);
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
    // InternalIdioms.g:2823:1: rule__PostCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PostCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2827:1: ( ( () ) )
            // InternalIdioms.g:2828:1: ( () )
            {
            // InternalIdioms.g:2828:1: ( () )
            // InternalIdioms.g:2829:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0());
            }
            // InternalIdioms.g:2830:2: ()
            // InternalIdioms.g:2830:3:
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
    // InternalIdioms.g:2838:1: rule__PostCommentSegment__Group__1 : rule__PostCommentSegment__Group__1__Impl ;
    public final void rule__PostCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2842:1: ( rule__PostCommentSegment__Group__1__Impl )
            // InternalIdioms.g:2843:2: rule__PostCommentSegment__Group__1__Impl
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
    // InternalIdioms.g:2849:1: rule__PostCommentSegment__Group__1__Impl : ( 'post-comment' ) ;
    public final void rule__PostCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2853:1: ( ( 'post-comment' ) )
            // InternalIdioms.g:2854:1: ( 'post-comment' )
            {
            // InternalIdioms.g:2854:1: ( 'post-comment' )
            // InternalIdioms.g:2855:2: 'post-comment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1());
            }
            match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2865:1: rule__PreCommentSegment__Group__0 : rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 ;
    public final void rule__PreCommentSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2869:1: ( rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1 )
            // InternalIdioms.g:2870:2: rule__PreCommentSegment__Group__0__Impl rule__PreCommentSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_23);
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
    // InternalIdioms.g:2877:1: rule__PreCommentSegment__Group__0__Impl : ( () ) ;
    public final void rule__PreCommentSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2881:1: ( ( () ) )
            // InternalIdioms.g:2882:1: ( () )
            {
            // InternalIdioms.g:2882:1: ( () )
            // InternalIdioms.g:2883:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0());
            }
            // InternalIdioms.g:2884:2: ()
            // InternalIdioms.g:2884:3:
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
    // InternalIdioms.g:2892:1: rule__PreCommentSegment__Group__1 : rule__PreCommentSegment__Group__1__Impl ;
    public final void rule__PreCommentSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2896:1: ( rule__PreCommentSegment__Group__1__Impl )
            // InternalIdioms.g:2897:2: rule__PreCommentSegment__Group__1__Impl
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
    // InternalIdioms.g:2903:1: rule__PreCommentSegment__Group__1__Impl : ( 'pre-comment' ) ;
    public final void rule__PreCommentSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2907:1: ( ( 'pre-comment' ) )
            // InternalIdioms.g:2908:1: ( 'pre-comment' )
            {
            // InternalIdioms.g:2908:1: ( 'pre-comment' )
            // InternalIdioms.g:2909:2: 'pre-comment'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1());
            }
            match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2919:1: rule__PushSegment__Group__0 : rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 ;
    public final void rule__PushSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2923:1: ( rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1 )
            // InternalIdioms.g:2924:2: rule__PushSegment__Group__0__Impl rule__PushSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_24);
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
    // InternalIdioms.g:2931:1: rule__PushSegment__Group__0__Impl : ( () ) ;
    public final void rule__PushSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2935:1: ( ( () ) )
            // InternalIdioms.g:2936:1: ( () )
            {
            // InternalIdioms.g:2936:1: ( () )
            // InternalIdioms.g:2937:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getPushSegmentAction_0());
            }
            // InternalIdioms.g:2938:2: ()
            // InternalIdioms.g:2938:3:
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
    // InternalIdioms.g:2946:1: rule__PushSegment__Group__1 : rule__PushSegment__Group__1__Impl ;
    public final void rule__PushSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2950:1: ( rule__PushSegment__Group__1__Impl )
            // InternalIdioms.g:2951:2: rule__PushSegment__Group__1__Impl
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
    // InternalIdioms.g:2957:1: rule__PushSegment__Group__1__Impl : ( 'push' ) ;
    public final void rule__PushSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2961:1: ( ( 'push' ) )
            // InternalIdioms.g:2962:1: ( 'push' )
            {
            // InternalIdioms.g:2962:1: ( 'push' )
            // InternalIdioms.g:2963:2: 'push'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPushSegmentAccess().getPushKeyword_1());
            }
            match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:2973:1: rule__SoftNewLineSegment__Group__0 : rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 ;
    public final void rule__SoftNewLineSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2977:1: ( rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1 )
            // InternalIdioms.g:2978:2: rule__SoftNewLineSegment__Group__0__Impl rule__SoftNewLineSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_25);
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
    // InternalIdioms.g:2985:1: rule__SoftNewLineSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftNewLineSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:2989:1: ( ( () ) )
            // InternalIdioms.g:2990:1: ( () )
            {
            // InternalIdioms.g:2990:1: ( () )
            // InternalIdioms.g:2991:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0());
            }
            // InternalIdioms.g:2992:2: ()
            // InternalIdioms.g:2992:3:
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
    // InternalIdioms.g:3000:1: rule__SoftNewLineSegment__Group__1 : rule__SoftNewLineSegment__Group__1__Impl ;
    public final void rule__SoftNewLineSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3004:1: ( rule__SoftNewLineSegment__Group__1__Impl )
            // InternalIdioms.g:3005:2: rule__SoftNewLineSegment__Group__1__Impl
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
    // InternalIdioms.g:3011:1: rule__SoftNewLineSegment__Group__1__Impl : ( 'soft-new-line' ) ;
    public final void rule__SoftNewLineSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3015:1: ( ( 'soft-new-line' ) )
            // InternalIdioms.g:3016:1: ( 'soft-new-line' )
            {
            // InternalIdioms.g:3016:1: ( 'soft-new-line' )
            // InternalIdioms.g:3017:2: 'soft-new-line'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1());
            }
            match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3027:1: rule__SoftSpaceSegment__Group__0 : rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 ;
    public final void rule__SoftSpaceSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3031:1: ( rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1 )
            // InternalIdioms.g:3032:2: rule__SoftSpaceSegment__Group__0__Impl rule__SoftSpaceSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_26);
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
    // InternalIdioms.g:3039:1: rule__SoftSpaceSegment__Group__0__Impl : ( () ) ;
    public final void rule__SoftSpaceSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3043:1: ( ( () ) )
            // InternalIdioms.g:3044:1: ( () )
            {
            // InternalIdioms.g:3044:1: ( () )
            // InternalIdioms.g:3045:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0());
            }
            // InternalIdioms.g:3046:2: ()
            // InternalIdioms.g:3046:3:
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
    // InternalIdioms.g:3054:1: rule__SoftSpaceSegment__Group__1 : rule__SoftSpaceSegment__Group__1__Impl ;
    public final void rule__SoftSpaceSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3058:1: ( rule__SoftSpaceSegment__Group__1__Impl )
            // InternalIdioms.g:3059:2: rule__SoftSpaceSegment__Group__1__Impl
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
    // InternalIdioms.g:3065:1: rule__SoftSpaceSegment__Group__1__Impl : ( 'soft-space' ) ;
    public final void rule__SoftSpaceSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3069:1: ( ( 'soft-space' ) )
            // InternalIdioms.g:3070:1: ( 'soft-space' )
            {
            // InternalIdioms.g:3070:1: ( 'soft-space' )
            // InternalIdioms.g:3071:2: 'soft-space'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1());
            }
            match(input,35,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3081:1: rule__StringSegment__Group__0 : rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 ;
    public final void rule__StringSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3085:1: ( rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1 )
            // InternalIdioms.g:3086:2: rule__StringSegment__Group__0__Impl rule__StringSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
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
    // InternalIdioms.g:3093:1: rule__StringSegment__Group__0__Impl : ( 'string' ) ;
    public final void rule__StringSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3097:1: ( ( 'string' ) )
            // InternalIdioms.g:3098:1: ( 'string' )
            {
            // InternalIdioms.g:3098:1: ( 'string' )
            // InternalIdioms.g:3099:2: 'string'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringKeyword_0());
            }
            match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3108:1: rule__StringSegment__Group__1 : rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 ;
    public final void rule__StringSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3112:1: ( rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2 )
            // InternalIdioms.g:3113:2: rule__StringSegment__Group__1__Impl rule__StringSegment__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_27);
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
    // InternalIdioms.g:3120:1: rule__StringSegment__Group__1__Impl : ( ( rule__StringSegment__StringAssignment_1 ) ) ;
    public final void rule__StringSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3124:1: ( ( ( rule__StringSegment__StringAssignment_1 ) ) )
            // InternalIdioms.g:3125:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            {
            // InternalIdioms.g:3125:1: ( ( rule__StringSegment__StringAssignment_1 ) )
            // InternalIdioms.g:3126:2: ( rule__StringSegment__StringAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getStringAssignment_1());
            }
            // InternalIdioms.g:3127:2: ( rule__StringSegment__StringAssignment_1 )
            // InternalIdioms.g:3127:3: rule__StringSegment__StringAssignment_1
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
    // InternalIdioms.g:3135:1: rule__StringSegment__Group__2 : rule__StringSegment__Group__2__Impl ;
    public final void rule__StringSegment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3139:1: ( rule__StringSegment__Group__2__Impl )
            // InternalIdioms.g:3140:2: rule__StringSegment__Group__2__Impl
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
    // InternalIdioms.g:3146:1: rule__StringSegment__Group__2__Impl : ( ( rule__StringSegment__PrintableAssignment_2 ) ) ;
    public final void rule__StringSegment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3150:1: ( ( ( rule__StringSegment__PrintableAssignment_2 ) ) )
            // InternalIdioms.g:3151:1: ( ( rule__StringSegment__PrintableAssignment_2 ) )
            {
            // InternalIdioms.g:3151:1: ( ( rule__StringSegment__PrintableAssignment_2 ) )
            // InternalIdioms.g:3152:2: ( rule__StringSegment__PrintableAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintableAssignment_2());
            }
            // InternalIdioms.g:3153:2: ( rule__StringSegment__PrintableAssignment_2 )
            // InternalIdioms.g:3153:3: rule__StringSegment__PrintableAssignment_2
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
    // InternalIdioms.g:3162:1: rule__ValueSegment__Group__0 : rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 ;
    public final void rule__ValueSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3166:1: ( rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1 )
            // InternalIdioms.g:3167:2: rule__ValueSegment__Group__0__Impl rule__ValueSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_28);
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
    // InternalIdioms.g:3174:1: rule__ValueSegment__Group__0__Impl : ( () ) ;
    public final void rule__ValueSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3178:1: ( ( () ) )
            // InternalIdioms.g:3179:1: ( () )
            {
            // InternalIdioms.g:3179:1: ( () )
            // InternalIdioms.g:3180:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getValueSegmentAction_0());
            }
            // InternalIdioms.g:3181:2: ()
            // InternalIdioms.g:3181:3:
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
    // InternalIdioms.g:3189:1: rule__ValueSegment__Group__1 : rule__ValueSegment__Group__1__Impl ;
    public final void rule__ValueSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3193:1: ( rule__ValueSegment__Group__1__Impl )
            // InternalIdioms.g:3194:2: rule__ValueSegment__Group__1__Impl
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
    // InternalIdioms.g:3200:1: rule__ValueSegment__Group__1__Impl : ( 'value' ) ;
    public final void rule__ValueSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3204:1: ( ( 'value' ) )
            // InternalIdioms.g:3205:1: ( 'value' )
            {
            // InternalIdioms.g:3205:1: ( 'value' )
            // InternalIdioms.g:3206:2: 'value'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getValueSegmentAccess().getValueKeyword_1());
            }
            match(input,37,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3216:1: rule__WrapAnchorSegment__Group__0 : rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 ;
    public final void rule__WrapAnchorSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3220:1: ( rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1 )
            // InternalIdioms.g:3221:2: rule__WrapAnchorSegment__Group__0__Impl rule__WrapAnchorSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_29);
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
    // InternalIdioms.g:3228:1: rule__WrapAnchorSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapAnchorSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3232:1: ( ( () ) )
            // InternalIdioms.g:3233:1: ( () )
            {
            // InternalIdioms.g:3233:1: ( () )
            // InternalIdioms.g:3234:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0());
            }
            // InternalIdioms.g:3235:2: ()
            // InternalIdioms.g:3235:3:
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
    // InternalIdioms.g:3243:1: rule__WrapAnchorSegment__Group__1 : rule__WrapAnchorSegment__Group__1__Impl ;
    public final void rule__WrapAnchorSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3247:1: ( rule__WrapAnchorSegment__Group__1__Impl )
            // InternalIdioms.g:3248:2: rule__WrapAnchorSegment__Group__1__Impl
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
    // InternalIdioms.g:3254:1: rule__WrapAnchorSegment__Group__1__Impl : ( 'wrap-anchor' ) ;
    public final void rule__WrapAnchorSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3258:1: ( ( 'wrap-anchor' ) )
            // InternalIdioms.g:3259:1: ( 'wrap-anchor' )
            {
            // InternalIdioms.g:3259:1: ( 'wrap-anchor' )
            // InternalIdioms.g:3260:2: 'wrap-anchor'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1());
            }
            match(input,38,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3270:1: rule__WrapBeginAllSegment__Group__0 : rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 ;
    public final void rule__WrapBeginAllSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3274:1: ( rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1 )
            // InternalIdioms.g:3275:2: rule__WrapBeginAllSegment__Group__0__Impl rule__WrapBeginAllSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_30);
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
    // InternalIdioms.g:3282:1: rule__WrapBeginAllSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginAllSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3286:1: ( ( () ) )
            // InternalIdioms.g:3287:1: ( () )
            {
            // InternalIdioms.g:3287:1: ( () )
            // InternalIdioms.g:3288:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0());
            }
            // InternalIdioms.g:3289:2: ()
            // InternalIdioms.g:3289:3:
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
    // InternalIdioms.g:3297:1: rule__WrapBeginAllSegment__Group__1 : rule__WrapBeginAllSegment__Group__1__Impl ;
    public final void rule__WrapBeginAllSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3301:1: ( rule__WrapBeginAllSegment__Group__1__Impl )
            // InternalIdioms.g:3302:2: rule__WrapBeginAllSegment__Group__1__Impl
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
    // InternalIdioms.g:3308:1: rule__WrapBeginAllSegment__Group__1__Impl : ( 'wrap-begin-all' ) ;
    public final void rule__WrapBeginAllSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3312:1: ( ( 'wrap-begin-all' ) )
            // InternalIdioms.g:3313:1: ( 'wrap-begin-all' )
            {
            // InternalIdioms.g:3313:1: ( 'wrap-begin-all' )
            // InternalIdioms.g:3314:2: 'wrap-begin-all'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1());
            }
            match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3324:1: rule__WrapBeginSomeSegment__Group__0 : rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 ;
    public final void rule__WrapBeginSomeSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3328:1: ( rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1 )
            // InternalIdioms.g:3329:2: rule__WrapBeginSomeSegment__Group__0__Impl rule__WrapBeginSomeSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_31);
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
    // InternalIdioms.g:3336:1: rule__WrapBeginSomeSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapBeginSomeSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3340:1: ( ( () ) )
            // InternalIdioms.g:3341:1: ( () )
            {
            // InternalIdioms.g:3341:1: ( () )
            // InternalIdioms.g:3342:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0());
            }
            // InternalIdioms.g:3343:2: ()
            // InternalIdioms.g:3343:3:
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
    // InternalIdioms.g:3351:1: rule__WrapBeginSomeSegment__Group__1 : rule__WrapBeginSomeSegment__Group__1__Impl ;
    public final void rule__WrapBeginSomeSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3355:1: ( rule__WrapBeginSomeSegment__Group__1__Impl )
            // InternalIdioms.g:3356:2: rule__WrapBeginSomeSegment__Group__1__Impl
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
    // InternalIdioms.g:3362:1: rule__WrapBeginSomeSegment__Group__1__Impl : ( 'wrap-begin-some' ) ;
    public final void rule__WrapBeginSomeSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3366:1: ( ( 'wrap-begin-some' ) )
            // InternalIdioms.g:3367:1: ( 'wrap-begin-some' )
            {
            // InternalIdioms.g:3367:1: ( 'wrap-begin-some' )
            // InternalIdioms.g:3368:2: 'wrap-begin-some'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1());
            }
            match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3378:1: rule__WrapEndSegment__Group__0 : rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 ;
    public final void rule__WrapEndSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3382:1: ( rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1 )
            // InternalIdioms.g:3383:2: rule__WrapEndSegment__Group__0__Impl rule__WrapEndSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_32);
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
    // InternalIdioms.g:3390:1: rule__WrapEndSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapEndSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3394:1: ( ( () ) )
            // InternalIdioms.g:3395:1: ( () )
            {
            // InternalIdioms.g:3395:1: ( () )
            // InternalIdioms.g:3396:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0());
            }
            // InternalIdioms.g:3397:2: ()
            // InternalIdioms.g:3397:3:
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
    // InternalIdioms.g:3405:1: rule__WrapEndSegment__Group__1 : rule__WrapEndSegment__Group__1__Impl ;
    public final void rule__WrapEndSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3409:1: ( rule__WrapEndSegment__Group__1__Impl )
            // InternalIdioms.g:3410:2: rule__WrapEndSegment__Group__1__Impl
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
    // InternalIdioms.g:3416:1: rule__WrapEndSegment__Group__1__Impl : ( 'wrap-end' ) ;
    public final void rule__WrapEndSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3420:1: ( ( 'wrap-end' ) )
            // InternalIdioms.g:3421:1: ( 'wrap-end' )
            {
            // InternalIdioms.g:3421:1: ( 'wrap-end' )
            // InternalIdioms.g:3422:2: 'wrap-end'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1());
            }
            match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3432:1: rule__WrapHereSegment__Group__0 : rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 ;
    public final void rule__WrapHereSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3436:1: ( rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1 )
            // InternalIdioms.g:3437:2: rule__WrapHereSegment__Group__0__Impl rule__WrapHereSegment__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_17);
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
    // InternalIdioms.g:3444:1: rule__WrapHereSegment__Group__0__Impl : ( () ) ;
    public final void rule__WrapHereSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3448:1: ( ( () ) )
            // InternalIdioms.g:3449:1: ( () )
            {
            // InternalIdioms.g:3449:1: ( () )
            // InternalIdioms.g:3450:2: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0());
            }
            // InternalIdioms.g:3451:2: ()
            // InternalIdioms.g:3451:3:
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
    // InternalIdioms.g:3459:1: rule__WrapHereSegment__Group__1 : rule__WrapHereSegment__Group__1__Impl ;
    public final void rule__WrapHereSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3463:1: ( rule__WrapHereSegment__Group__1__Impl )
            // InternalIdioms.g:3464:2: rule__WrapHereSegment__Group__1__Impl
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
    // InternalIdioms.g:3470:1: rule__WrapHereSegment__Group__1__Impl : ( 'wrap-here' ) ;
    public final void rule__WrapHereSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3474:1: ( ( 'wrap-here' ) )
            // InternalIdioms.g:3475:1: ( 'wrap-here' )
            {
            // InternalIdioms.g:3475:1: ( 'wrap-here' )
            // InternalIdioms.g:3476:2: 'wrap-here'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1());
            }
            match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3486:1: rule__ReferredSegment__Group__0 : rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 ;
    public final void rule__ReferredSegment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3490:1: ( rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1 )
            // InternalIdioms.g:3491:2: rule__ReferredSegment__Group__0__Impl rule__ReferredSegment__Group__1
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
    // InternalIdioms.g:3498:1: rule__ReferredSegment__Group__0__Impl : ( ( rule__ReferredSegment__Group_0__0 )? ) ;
    public final void rule__ReferredSegment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3502:1: ( ( ( rule__ReferredSegment__Group_0__0 )? ) )
            // InternalIdioms.g:3503:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            {
            // InternalIdioms.g:3503:1: ( ( rule__ReferredSegment__Group_0__0 )? )
            // InternalIdioms.g:3504:2: ( rule__ReferredSegment__Group_0__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getGroup_0());
            }
            // InternalIdioms.g:3505:2: ( rule__ReferredSegment__Group_0__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==22) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // InternalIdioms.g:3505:3: rule__ReferredSegment__Group_0__0
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
    // InternalIdioms.g:3513:1: rule__ReferredSegment__Group__1 : rule__ReferredSegment__Group__1__Impl ;
    public final void rule__ReferredSegment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3517:1: ( rule__ReferredSegment__Group__1__Impl )
            // InternalIdioms.g:3518:2: rule__ReferredSegment__Group__1__Impl
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
    // InternalIdioms.g:3524:1: rule__ReferredSegment__Group__1__Impl : ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) ;
    public final void rule__ReferredSegment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3528:1: ( ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) ) )
            // InternalIdioms.g:3529:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            {
            // InternalIdioms.g:3529:1: ( ( rule__ReferredSegment__SegmentDeclarationAssignment_1 ) )
            // InternalIdioms.g:3530:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationAssignment_1());
            }
            // InternalIdioms.g:3531:2: ( rule__ReferredSegment__SegmentDeclarationAssignment_1 )
            // InternalIdioms.g:3531:3: rule__ReferredSegment__SegmentDeclarationAssignment_1
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
    // InternalIdioms.g:3540:1: rule__ReferredSegment__Group_0__0 : rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 ;
    public final void rule__ReferredSegment__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3544:1: ( rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1 )
            // InternalIdioms.g:3545:2: rule__ReferredSegment__Group_0__0__Impl rule__ReferredSegment__Group_0__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:3552:1: rule__ReferredSegment__Group_0__0__Impl : ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) ;
    public final void rule__ReferredSegment__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3556:1: ( ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) ) )
            // InternalIdioms.g:3557:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            {
            // InternalIdioms.g:3557:1: ( ( rule__ReferredSegment__IdiomsModelAssignment_0_0 ) )
            // InternalIdioms.g:3558:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelAssignment_0_0());
            }
            // InternalIdioms.g:3559:2: ( rule__ReferredSegment__IdiomsModelAssignment_0_0 )
            // InternalIdioms.g:3559:3: rule__ReferredSegment__IdiomsModelAssignment_0_0
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
    // InternalIdioms.g:3567:1: rule__ReferredSegment__Group_0__1 : rule__ReferredSegment__Group_0__1__Impl ;
    public final void rule__ReferredSegment__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3571:1: ( rule__ReferredSegment__Group_0__1__Impl )
            // InternalIdioms.g:3572:2: rule__ReferredSegment__Group_0__1__Impl
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
    // InternalIdioms.g:3578:1: rule__ReferredSegment__Group_0__1__Impl : ( '::' ) ;
    public final void rule__ReferredSegment__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3582:1: ( ( '::' ) )
            // InternalIdioms.g:3583:1: ( '::' )
            {
            // InternalIdioms.g:3583:1: ( '::' )
            // InternalIdioms.g:3584:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3594:1: rule__Idiom__Group__0 : rule__Idiom__Group__0__Impl rule__Idiom__Group__1 ;
    public final void rule__Idiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3598:1: ( rule__Idiom__Group__0__Impl rule__Idiom__Group__1 )
            // InternalIdioms.g:3599:2: rule__Idiom__Group__0__Impl rule__Idiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_33);
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
    // InternalIdioms.g:3606:1: rule__Idiom__Group__0__Impl : ( ( rule__Idiom__MixinAssignment_0 )? ) ;
    public final void rule__Idiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3610:1: ( ( ( rule__Idiom__MixinAssignment_0 )? ) )
            // InternalIdioms.g:3611:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            {
            // InternalIdioms.g:3611:1: ( ( rule__Idiom__MixinAssignment_0 )? )
            // InternalIdioms.g:3612:2: ( rule__Idiom__MixinAssignment_0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinAssignment_0());
            }
            // InternalIdioms.g:3613:2: ( rule__Idiom__MixinAssignment_0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==51) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalIdioms.g:3613:3: rule__Idiom__MixinAssignment_0
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
    // InternalIdioms.g:3621:1: rule__Idiom__Group__1 : rule__Idiom__Group__1__Impl rule__Idiom__Group__2 ;
    public final void rule__Idiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3625:1: ( rule__Idiom__Group__1__Impl rule__Idiom__Group__2 )
            // InternalIdioms.g:3626:2: rule__Idiom__Group__1__Impl rule__Idiom__Group__2
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
    // InternalIdioms.g:3633:1: rule__Idiom__Group__1__Impl : ( 'idiom' ) ;
    public final void rule__Idiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3637:1: ( ( 'idiom' ) )
            // InternalIdioms.g:3638:1: ( 'idiom' )
            {
            // InternalIdioms.g:3638:1: ( 'idiom' )
            // InternalIdioms.g:3639:2: 'idiom'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getIdiomKeyword_1());
            }
            match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3648:1: rule__Idiom__Group__2 : rule__Idiom__Group__2__Impl rule__Idiom__Group__3 ;
    public final void rule__Idiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3652:1: ( rule__Idiom__Group__2__Impl rule__Idiom__Group__3 )
            // InternalIdioms.g:3653:2: rule__Idiom__Group__2__Impl rule__Idiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_34);
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
    // InternalIdioms.g:3660:1: rule__Idiom__Group__2__Impl : ( ( rule__Idiom__NameAssignment_2 ) ) ;
    public final void rule__Idiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3664:1: ( ( ( rule__Idiom__NameAssignment_2 ) ) )
            // InternalIdioms.g:3665:1: ( ( rule__Idiom__NameAssignment_2 ) )
            {
            // InternalIdioms.g:3665:1: ( ( rule__Idiom__NameAssignment_2 ) )
            // InternalIdioms.g:3666:2: ( rule__Idiom__NameAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getNameAssignment_2());
            }
            // InternalIdioms.g:3667:2: ( rule__Idiom__NameAssignment_2 )
            // InternalIdioms.g:3667:3: rule__Idiom__NameAssignment_2
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
    // InternalIdioms.g:3675:1: rule__Idiom__Group__3 : rule__Idiom__Group__3__Impl rule__Idiom__Group__4 ;
    public final void rule__Idiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3679:1: ( rule__Idiom__Group__3__Impl rule__Idiom__Group__4 )
            // InternalIdioms.g:3680:2: rule__Idiom__Group__3__Impl rule__Idiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_34);
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
    // InternalIdioms.g:3687:1: rule__Idiom__Group__3__Impl : ( ( rule__Idiom__Group_3__0 )? ) ;
    public final void rule__Idiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3691:1: ( ( ( rule__Idiom__Group_3__0 )? ) )
            // InternalIdioms.g:3692:1: ( ( rule__Idiom__Group_3__0 )? )
            {
            // InternalIdioms.g:3692:1: ( ( rule__Idiom__Group_3__0 )? )
            // InternalIdioms.g:3693:2: ( rule__Idiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:3694:2: ( rule__Idiom__Group_3__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==44) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalIdioms.g:3694:3: rule__Idiom__Group_3__0
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
    // InternalIdioms.g:3702:1: rule__Idiom__Group__4 : rule__Idiom__Group__4__Impl rule__Idiom__Group__5 ;
    public final void rule__Idiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3706:1: ( rule__Idiom__Group__4__Impl rule__Idiom__Group__5 )
            // InternalIdioms.g:3707:2: rule__Idiom__Group__4__Impl rule__Idiom__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_34);
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
    // InternalIdioms.g:3714:1: rule__Idiom__Group__4__Impl : ( ( rule__Idiom__Group_4__0 )? ) ;
    public final void rule__Idiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3718:1: ( ( ( rule__Idiom__Group_4__0 )? ) )
            // InternalIdioms.g:3719:1: ( ( rule__Idiom__Group_4__0 )? )
            {
            // InternalIdioms.g:3719:1: ( ( rule__Idiom__Group_4__0 )? )
            // InternalIdioms.g:3720:2: ( rule__Idiom__Group_4__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_4());
            }
            // InternalIdioms.g:3721:2: ( rule__Idiom__Group_4__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==45) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalIdioms.g:3721:3: rule__Idiom__Group_4__0
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
    // InternalIdioms.g:3729:1: rule__Idiom__Group__5 : rule__Idiom__Group__5__Impl ;
    public final void rule__Idiom__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3733:1: ( rule__Idiom__Group__5__Impl )
            // InternalIdioms.g:3734:2: rule__Idiom__Group__5__Impl
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
    // InternalIdioms.g:3740:1: rule__Idiom__Group__5__Impl : ( ( rule__Idiom__Alternatives_5 ) ) ;
    public final void rule__Idiom__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3744:1: ( ( ( rule__Idiom__Alternatives_5 ) ) )
            // InternalIdioms.g:3745:1: ( ( rule__Idiom__Alternatives_5 ) )
            {
            // InternalIdioms.g:3745:1: ( ( rule__Idiom__Alternatives_5 ) )
            // InternalIdioms.g:3746:2: ( rule__Idiom__Alternatives_5 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getAlternatives_5());
            }
            // InternalIdioms.g:3747:2: ( rule__Idiom__Alternatives_5 )
            // InternalIdioms.g:3747:3: rule__Idiom__Alternatives_5
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
    // InternalIdioms.g:3756:1: rule__Idiom__Group_3__0 : rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 ;
    public final void rule__Idiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3760:1: ( rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1 )
            // InternalIdioms.g:3761:2: rule__Idiom__Group_3__0__Impl rule__Idiom__Group_3__1
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
    // InternalIdioms.g:3768:1: rule__Idiom__Group_3__0__Impl : ( 'for' ) ;
    public final void rule__Idiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3772:1: ( ( 'for' ) )
            // InternalIdioms.g:3773:1: ( 'for' )
            {
            // InternalIdioms.g:3773:1: ( 'for' )
            // InternalIdioms.g:3774:2: 'for'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForKeyword_3_0());
            }
            match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3783:1: rule__Idiom__Group_3__1 : rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 ;
    public final void rule__Idiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3787:1: ( rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2 )
            // InternalIdioms.g:3788:2: rule__Idiom__Group_3__1__Impl rule__Idiom__Group_3__2
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
    // InternalIdioms.g:3795:1: rule__Idiom__Group_3__1__Impl : ( ( rule__Idiom__Group_3_1__0 )? ) ;
    public final void rule__Idiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3799:1: ( ( ( rule__Idiom__Group_3_1__0 )? ) )
            // InternalIdioms.g:3800:1: ( ( rule__Idiom__Group_3_1__0 )? )
            {
            // InternalIdioms.g:3800:1: ( ( rule__Idiom__Group_3_1__0 )? )
            // InternalIdioms.g:3801:2: ( rule__Idiom__Group_3_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getGroup_3_1());
            }
            // InternalIdioms.g:3802:2: ( rule__Idiom__Group_3_1__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ID) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==22) ) {
                    alt23=1;
                }
            }
            switch (alt23) {
                case 1 :
                    // InternalIdioms.g:3802:3: rule__Idiom__Group_3_1__0
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
    // InternalIdioms.g:3810:1: rule__Idiom__Group_3__2 : rule__Idiom__Group_3__2__Impl ;
    public final void rule__Idiom__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3814:1: ( rule__Idiom__Group_3__2__Impl )
            // InternalIdioms.g:3815:2: rule__Idiom__Group_3__2__Impl
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
    // InternalIdioms.g:3821:1: rule__Idiom__Group_3__2__Impl : ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) ;
    public final void rule__Idiom__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3825:1: ( ( ( rule__Idiom__ForEClassAssignment_3_2 ) ) )
            // InternalIdioms.g:3826:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            {
            // InternalIdioms.g:3826:1: ( ( rule__Idiom__ForEClassAssignment_3_2 ) )
            // InternalIdioms.g:3827:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassAssignment_3_2());
            }
            // InternalIdioms.g:3828:2: ( rule__Idiom__ForEClassAssignment_3_2 )
            // InternalIdioms.g:3828:3: rule__Idiom__ForEClassAssignment_3_2
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
    // InternalIdioms.g:3837:1: rule__Idiom__Group_3_1__0 : rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 ;
    public final void rule__Idiom__Group_3_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3841:1: ( rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1 )
            // InternalIdioms.g:3842:2: rule__Idiom__Group_3_1__0__Impl rule__Idiom__Group_3_1__1
            {
            pushFollow(FollowSets000.FOLLOW_15);
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
    // InternalIdioms.g:3849:1: rule__Idiom__Group_3_1__0__Impl : ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) ;
    public final void rule__Idiom__Group_3_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3853:1: ( ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) ) )
            // InternalIdioms.g:3854:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            {
            // InternalIdioms.g:3854:1: ( ( rule__Idiom__ForEPackageAssignment_3_1_0 ) )
            // InternalIdioms.g:3855:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageAssignment_3_1_0());
            }
            // InternalIdioms.g:3856:2: ( rule__Idiom__ForEPackageAssignment_3_1_0 )
            // InternalIdioms.g:3856:3: rule__Idiom__ForEPackageAssignment_3_1_0
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
    // InternalIdioms.g:3864:1: rule__Idiom__Group_3_1__1 : rule__Idiom__Group_3_1__1__Impl ;
    public final void rule__Idiom__Group_3_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3868:1: ( rule__Idiom__Group_3_1__1__Impl )
            // InternalIdioms.g:3869:2: rule__Idiom__Group_3_1__1__Impl
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
    // InternalIdioms.g:3875:1: rule__Idiom__Group_3_1__1__Impl : ( '::' ) ;
    public final void rule__Idiom__Group_3_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3879:1: ( ( '::' ) )
            // InternalIdioms.g:3880:1: ( '::' )
            {
            // InternalIdioms.g:3880:1: ( '::' )
            // InternalIdioms.g:3881:2: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3891:1: rule__Idiom__Group_4__0 : rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 ;
    public final void rule__Idiom__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3895:1: ( rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1 )
            // InternalIdioms.g:3896:2: rule__Idiom__Group_4__0__Impl rule__Idiom__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
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
    // InternalIdioms.g:3903:1: rule__Idiom__Group_4__0__Impl : ( 'in' ) ;
    public final void rule__Idiom__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3907:1: ( ( 'in' ) )
            // InternalIdioms.g:3908:1: ( 'in' )
            {
            // InternalIdioms.g:3908:1: ( 'in' )
            // InternalIdioms.g:3909:2: 'in'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInKeyword_4_0());
            }
            match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3918:1: rule__Idiom__Group_4__1 : rule__Idiom__Group_4__1__Impl ;
    public final void rule__Idiom__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3922:1: ( rule__Idiom__Group_4__1__Impl )
            // InternalIdioms.g:3923:2: rule__Idiom__Group_4__1__Impl
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
    // InternalIdioms.g:3929:1: rule__Idiom__Group_4__1__Impl : ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) ;
    public final void rule__Idiom__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3933:1: ( ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) ) )
            // InternalIdioms.g:3934:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            {
            // InternalIdioms.g:3934:1: ( ( rule__Idiom__InRuleRegexAssignment_4_1 ) )
            // InternalIdioms.g:3935:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getInRuleRegexAssignment_4_1());
            }
            // InternalIdioms.g:3936:2: ( rule__Idiom__InRuleRegexAssignment_4_1 )
            // InternalIdioms.g:3936:3: rule__Idiom__InRuleRegexAssignment_4_1
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
    // InternalIdioms.g:3945:1: rule__Idiom__Group_5_1__0 : rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 ;
    public final void rule__Idiom__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3949:1: ( rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1 )
            // InternalIdioms.g:3950:2: rule__Idiom__Group_5_1__0__Impl rule__Idiom__Group_5_1__1
            {
            pushFollow(FollowSets000.FOLLOW_35);
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
    // InternalIdioms.g:3957:1: rule__Idiom__Group_5_1__0__Impl : ( '{' ) ;
    public final void rule__Idiom__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3961:1: ( ( '{' ) )
            // InternalIdioms.g:3962:1: ( '{' )
            {
            // InternalIdioms.g:3962:1: ( '{' )
            // InternalIdioms.g:3963:2: '{'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());
            }
            match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:3972:1: rule__Idiom__Group_5_1__1 : rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 ;
    public final void rule__Idiom__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3976:1: ( rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2 )
            // InternalIdioms.g:3977:2: rule__Idiom__Group_5_1__1__Impl rule__Idiom__Group_5_1__2
            {
            pushFollow(FollowSets000.FOLLOW_35);
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
    // InternalIdioms.g:3984:1: rule__Idiom__Group_5_1__1__Impl : ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) ;
    public final void rule__Idiom__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:3988:1: ( ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* ) )
            // InternalIdioms.g:3989:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            {
            // InternalIdioms.g:3989:1: ( ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )* )
            // InternalIdioms.g:3990:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getOwnedSubIdiomsAssignment_5_1_1());
            }
            // InternalIdioms.g:3991:2: ( rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==48) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalIdioms.g:3991:3: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_36);
            	    rule__Idiom__OwnedSubIdiomsAssignment_5_1_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop24;
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
    // InternalIdioms.g:3999:1: rule__Idiom__Group_5_1__2 : rule__Idiom__Group_5_1__2__Impl ;
    public final void rule__Idiom__Group_5_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4003:1: ( rule__Idiom__Group_5_1__2__Impl )
            // InternalIdioms.g:4004:2: rule__Idiom__Group_5_1__2__Impl
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
    // InternalIdioms.g:4010:1: rule__Idiom__Group_5_1__2__Impl : ( '}' ) ;
    public final void rule__Idiom__Group_5_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4014:1: ( ( '}' ) )
            // InternalIdioms.g:4015:1: ( '}' )
            {
            // InternalIdioms.g:4015:1: ( '}' )
            // InternalIdioms.g:4016:2: '}'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());
            }
            match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return ;
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
    // InternalIdioms.g:4026:1: rule__SubIdiom__Group__0 : rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 ;
    public final void rule__SubIdiom__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4030:1: ( rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1 )
            // InternalIdioms.g:4031:2: rule__SubIdiom__Group__0__Impl rule__SubIdiom__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_37);
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
    // InternalIdioms.g:4038:1: rule__SubIdiom__Group__0__Impl : ( 'at' ) ;
    public final void rule__SubIdiom__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4042:1: ( ( 'at' ) )
            // InternalIdioms.g:4043:1: ( 'at' )
            {
            // InternalIdioms.g:4043:1: ( 'at' )
            // InternalIdioms.g:4044:2: 'at'
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
    // InternalIdioms.g:4053:1: rule__SubIdiom__Group__1 : rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 ;
    public final void rule__SubIdiom__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4057:1: ( rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2 )
            // InternalIdioms.g:4058:2: rule__SubIdiom__Group__1__Impl rule__SubIdiom__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_37);
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
    // InternalIdioms.g:4065:1: rule__SubIdiom__Group__1__Impl : ( ( rule__SubIdiom__Alternatives_1 )? ) ;
    public final void rule__SubIdiom__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4069:1: ( ( ( rule__SubIdiom__Alternatives_1 )? ) )
            // InternalIdioms.g:4070:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            {
            // InternalIdioms.g:4070:1: ( ( rule__SubIdiom__Alternatives_1 )? )
            // InternalIdioms.g:4071:2: ( rule__SubIdiom__Alternatives_1 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAlternatives_1());
            }
            // InternalIdioms.g:4072:2: ( rule__SubIdiom__Alternatives_1 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==11||LA25_0==52) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalIdioms.g:4072:3: rule__SubIdiom__Alternatives_1
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
    // InternalIdioms.g:4080:1: rule__SubIdiom__Group__2 : rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 ;
    public final void rule__SubIdiom__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4084:1: ( rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3 )
            // InternalIdioms.g:4085:2: rule__SubIdiom__Group__2__Impl rule__SubIdiom__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_38);
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
    // InternalIdioms.g:4092:1: rule__SubIdiom__Group__2__Impl : ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) ;
    public final void rule__SubIdiom__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4096:1: ( ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) ) )
            // InternalIdioms.g:4097:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            {
            // InternalIdioms.g:4097:1: ( ( rule__SubIdiom__OwnedLocatorAssignment_2 ) )
            // InternalIdioms.g:4098:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedLocatorAssignment_2());
            }
            // InternalIdioms.g:4099:2: ( rule__SubIdiom__OwnedLocatorAssignment_2 )
            // InternalIdioms.g:4099:3: rule__SubIdiom__OwnedLocatorAssignment_2
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
    // InternalIdioms.g:4107:1: rule__SubIdiom__Group__3 : rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 ;
    public final void rule__SubIdiom__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4111:1: ( rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4 )
            // InternalIdioms.g:4112:2: rule__SubIdiom__Group__3__Impl rule__SubIdiom__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_38);
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
    // InternalIdioms.g:4119:1: rule__SubIdiom__Group__3__Impl : ( ( rule__SubIdiom__Group_3__0 )? ) ;
    public final void rule__SubIdiom__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4123:1: ( ( ( rule__SubIdiom__Group_3__0 )? ) )
            // InternalIdioms.g:4124:1: ( ( rule__SubIdiom__Group_3__0 )? )
            {
            // InternalIdioms.g:4124:1: ( ( rule__SubIdiom__Group_3__0 )? )
            // InternalIdioms.g:4125:2: ( rule__SubIdiom__Group_3__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getGroup_3());
            }
            // InternalIdioms.g:4126:2: ( rule__SubIdiom__Group_3__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==49) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalIdioms.g:4126:3: rule__SubIdiom__Group_3__0
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
    // InternalIdioms.g:4134:1: rule__SubIdiom__Group__4 : rule__SubIdiom__Group__4__Impl ;
    public final void rule__SubIdiom__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4138:1: ( rule__SubIdiom__Group__4__Impl )
            // InternalIdioms.g:4139:2: rule__SubIdiom__Group__4__Impl
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
    // InternalIdioms.g:4145:1: rule__SubIdiom__Group__4__Impl : ( ';' ) ;
    public final void rule__SubIdiom__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4149:1: ( ( ';' ) )
            // InternalIdioms.g:4150:1: ( ';' )
            {
            // InternalIdioms.g:4150:1: ( ';' )
            // InternalIdioms.g:4151:2: ';'
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
    // InternalIdioms.g:4161:1: rule__SubIdiom__Group_3__0 : rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 ;
    public final void rule__SubIdiom__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4165:1: ( rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1 )
            // InternalIdioms.g:4166:2: rule__SubIdiom__Group_3__0__Impl rule__SubIdiom__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_39);
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
    // InternalIdioms.g:4173:1: rule__SubIdiom__Group_3__0__Impl : ( 'do' ) ;
    public final void rule__SubIdiom__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4177:1: ( ( 'do' ) )
            // InternalIdioms.g:4178:1: ( 'do' )
            {
            // InternalIdioms.g:4178:1: ( 'do' )
            // InternalIdioms.g:4179:2: 'do'
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
    // InternalIdioms.g:4188:1: rule__SubIdiom__Group_3__1 : rule__SubIdiom__Group_3__1__Impl ;
    public final void rule__SubIdiom__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4192:1: ( rule__SubIdiom__Group_3__1__Impl )
            // InternalIdioms.g:4193:2: rule__SubIdiom__Group_3__1__Impl
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
    // InternalIdioms.g:4199:1: rule__SubIdiom__Group_3__1__Impl : ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) ;
    public final void rule__SubIdiom__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4203:1: ( ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* ) )
            // InternalIdioms.g:4204:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            {
            // InternalIdioms.g:4204:1: ( ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )* )
            // InternalIdioms.g:4205:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAssignment_3_1());
            }
            // InternalIdioms.g:4206:2: ( rule__SubIdiom__OwnedSegmentsAssignment_3_1 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID||(LA27_0>=26 && LA27_0<=42)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalIdioms.g:4206:3: rule__SubIdiom__OwnedSegmentsAssignment_3_1
            	    {
            	    pushFollow(FollowSets000.FOLLOW_40);
            	    rule__SubIdiom__OwnedSegmentsAssignment_3_1();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
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
    // InternalIdioms.g:4215:1: rule__IdiomsModel__NamesAssignment_1 : ( RULE_ID ) ;
    public final void rule__IdiomsModel__NamesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4219:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4220:2: ( RULE_ID )
            {
            // InternalIdioms.g:4220:2: ( RULE_ID )
            // InternalIdioms.g:4221:3: RULE_ID
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
    // InternalIdioms.g:4230:1: rule__IdiomsModel__NamesAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__IdiomsModel__NamesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4234:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4235:2: ( RULE_ID )
            {
            // InternalIdioms.g:4235:2: ( RULE_ID )
            // InternalIdioms.g:4236:3: RULE_ID
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


    // $ANTLR start "rule__IdiomsModel__OwnedWithsAssignment_3"
    // InternalIdioms.g:4245:1: rule__IdiomsModel__OwnedWithsAssignment_3 : ( ruleIdiomsImport ) ;
    public final void rule__IdiomsModel__OwnedWithsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4249:1: ( ( ruleIdiomsImport ) )
            // InternalIdioms.g:4250:2: ( ruleIdiomsImport )
            {
            // InternalIdioms.g:4250:2: ( ruleIdiomsImport )
            // InternalIdioms.g:4251:3: ruleIdiomsImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdiomsImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedWithsAssignment_3"


    // $ANTLR start "rule__IdiomsModel__OwnedImportsAssignment_4"
    // InternalIdioms.g:4260:1: rule__IdiomsModel__OwnedImportsAssignment_4 : ( ruleEPackageImport ) ;
    public final void rule__IdiomsModel__OwnedImportsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4264:1: ( ( ruleEPackageImport ) )
            // InternalIdioms.g:4265:2: ( ruleEPackageImport )
            {
            // InternalIdioms.g:4265:2: ( ruleEPackageImport )
            // InternalIdioms.g:4266:3: ruleEPackageImport
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleEPackageImport();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedImportsAssignment_4"


    // $ANTLR start "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0"
    // InternalIdioms.g:4275:1: rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0 : ( ruleLocatorDeclaration ) ;
    public final void rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4279:1: ( ( ruleLocatorDeclaration ) )
            // InternalIdioms.g:4280:2: ( ruleLocatorDeclaration )
            {
            // InternalIdioms.g:4280:2: ( ruleLocatorDeclaration )
            // InternalIdioms.g:4281:3: ruleLocatorDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleLocatorDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedLocatorDeclarationsAssignment_5_0"


    // $ANTLR start "rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1"
    // InternalIdioms.g:4290:1: rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1 : ( ruleSegmentDeclaration ) ;
    public final void rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4294:1: ( ( ruleSegmentDeclaration ) )
            // InternalIdioms.g:4295:2: ( ruleSegmentDeclaration )
            {
            // InternalIdioms.g:4295:2: ( ruleSegmentDeclaration )
            // InternalIdioms.g:4296:3: ruleSegmentDeclaration
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleSegmentDeclaration();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedSegmentDeclarationsAssignment_5_1"


    // $ANTLR start "rule__IdiomsModel__OwnedIdiomsAssignment_5_2"
    // InternalIdioms.g:4305:1: rule__IdiomsModel__OwnedIdiomsAssignment_5_2 : ( ruleIdiom ) ;
    public final void rule__IdiomsModel__OwnedIdiomsAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4309:1: ( ( ruleIdiom ) )
            // InternalIdioms.g:4310:2: ( ruleIdiom )
            {
            // InternalIdioms.g:4310:2: ( ruleIdiom )
            // InternalIdioms.g:4311:3: ruleIdiom
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdiom();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IdiomsModel__OwnedIdiomsAssignment_5_2"


    // $ANTLR start "rule__EPackageImport__EPackageAssignment_1"
    // InternalIdioms.g:4320:1: rule__EPackageImport__EPackageAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__EPackageImport__EPackageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4324:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4325:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4325:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4326:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0());
            }
            // InternalIdioms.g:4327:3: ( RULE_STRING )
            // InternalIdioms.g:4328:4: RULE_STRING
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
    // InternalIdioms.g:4339:1: rule__EPackageImport__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__EPackageImport__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4343:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4344:2: ( RULE_ID )
            {
            // InternalIdioms.g:4344:2: ( RULE_ID )
            // InternalIdioms.g:4345:3: RULE_ID
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
    // InternalIdioms.g:4354:1: rule__IdiomsImport__IdiomsModelAssignment_1 : ( ( RULE_STRING ) ) ;
    public final void rule__IdiomsImport__IdiomsModelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4358:1: ( ( ( RULE_STRING ) ) )
            // InternalIdioms.g:4359:2: ( ( RULE_STRING ) )
            {
            // InternalIdioms.g:4359:2: ( ( RULE_STRING ) )
            // InternalIdioms.g:4360:3: ( RULE_STRING )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());
            }
            // InternalIdioms.g:4361:3: ( RULE_STRING )
            // InternalIdioms.g:4362:4: RULE_STRING
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
    // InternalIdioms.g:4373:1: rule__IdiomsImport__AsAssignment_2_1 : ( RULE_ID ) ;
    public final void rule__IdiomsImport__AsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4377:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4378:2: ( RULE_ID )
            {
            // InternalIdioms.g:4378:2: ( RULE_ID )
            // InternalIdioms.g:4379:3: RULE_ID
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
    // InternalIdioms.g:4388:1: rule__LocatorDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__LocatorDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4392:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4393:2: ( RULE_ID )
            {
            // InternalIdioms.g:4393:2: ( RULE_ID )
            // InternalIdioms.g:4394:3: RULE_ID
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
    // InternalIdioms.g:4403:1: rule__LocatorDeclaration__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__LocatorDeclaration__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4407:1: ( ( ruleLocator ) )
            // InternalIdioms.g:4408:2: ( ruleLocator )
            {
            // InternalIdioms.g:4408:2: ( ruleLocator )
            // InternalIdioms.g:4409:3: ruleLocator
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
    // InternalIdioms.g:4418:1: rule__AssignmentLocator__EPackageAssignment_1_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EPackageAssignment_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4422:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4423:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4423:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4424:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());
            }
            // InternalIdioms.g:4425:3: ( RULE_ID )
            // InternalIdioms.g:4426:4: RULE_ID
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
    // InternalIdioms.g:4437:1: rule__AssignmentLocator__EClassAssignment_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EClassAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4441:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4442:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4442:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4443:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());
            }
            // InternalIdioms.g:4444:3: ( RULE_ID )
            // InternalIdioms.g:4445:4: RULE_ID
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
    // InternalIdioms.g:4456:1: rule__AssignmentLocator__EStructuralFeatureAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__AssignmentLocator__EStructuralFeatureAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4460:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4461:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4461:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4462:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());
            }
            // InternalIdioms.g:4463:3: ( RULE_ID )
            // InternalIdioms.g:4464:4: RULE_ID
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
    // InternalIdioms.g:4475:1: rule__KeywordLocator__StringAssignment : ( RULE_STRING ) ;
    public final void rule__KeywordLocator__StringAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4479:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4480:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4480:2: ( RULE_STRING )
            // InternalIdioms.g:4481:3: RULE_STRING
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
    // InternalIdioms.g:4490:1: rule__ReturnsLocator__EPackageAssignment_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EPackageAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4494:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4495:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4495:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4496:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());
            }
            // InternalIdioms.g:4497:3: ( RULE_ID )
            // InternalIdioms.g:4498:4: RULE_ID
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
    // InternalIdioms.g:4509:1: rule__ReturnsLocator__EClassAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__ReturnsLocator__EClassAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4513:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4514:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4514:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4515:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());
            }
            // InternalIdioms.g:4516:3: ( RULE_ID )
            // InternalIdioms.g:4517:4: RULE_ID
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
    // InternalIdioms.g:4528:1: rule__ReferredLocator__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4532:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4533:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4533:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4534:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:4535:3: ( RULE_ID )
            // InternalIdioms.g:4536:4: RULE_ID
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
    // InternalIdioms.g:4547:1: rule__ReferredLocator__LocatorDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredLocator__LocatorDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4551:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4552:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4552:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4553:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:4554:3: ( RULE_ID )
            // InternalIdioms.g:4555:4: RULE_ID
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
    // InternalIdioms.g:4566:1: rule__SegmentDeclaration__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__SegmentDeclaration__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4570:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4571:2: ( RULE_ID )
            {
            // InternalIdioms.g:4571:2: ( RULE_ID )
            // InternalIdioms.g:4572:3: RULE_ID
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
    // InternalIdioms.g:4581:1: rule__SegmentDeclaration__OwnedSegmentAssignment_2 : ( ruleSegment ) ;
    public final void rule__SegmentDeclaration__OwnedSegmentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4585:1: ( ( ruleSegment ) )
            // InternalIdioms.g:4586:2: ( ruleSegment )
            {
            // InternalIdioms.g:4586:2: ( ruleSegment )
            // InternalIdioms.g:4587:3: ruleSegment
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
    // InternalIdioms.g:4596:1: rule__CustomSegment__SupportClassNameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__CustomSegment__SupportClassNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4600:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4601:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4601:2: ( RULE_STRING )
            // InternalIdioms.g:4602:3: RULE_STRING
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
    // InternalIdioms.g:4611:1: rule__StringSegment__StringAssignment_1 : ( RULE_STRING ) ;
    public final void rule__StringSegment__StringAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4615:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4616:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4616:2: ( RULE_STRING )
            // InternalIdioms.g:4617:3: RULE_STRING
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
    // InternalIdioms.g:4626:1: rule__StringSegment__PrintableAssignment_2 : ( ( 'printable' ) ) ;
    public final void rule__StringSegment__PrintableAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4630:1: ( ( ( 'printable' ) ) )
            // InternalIdioms.g:4631:2: ( ( 'printable' ) )
            {
            // InternalIdioms.g:4631:2: ( ( 'printable' ) )
            // InternalIdioms.g:4632:3: ( 'printable' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());
            }
            // InternalIdioms.g:4633:3: ( 'printable' )
            // InternalIdioms.g:4634:4: 'printable'
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
    // InternalIdioms.g:4645:1: rule__ReferredSegment__IdiomsModelAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__IdiomsModelAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4649:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4650:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4650:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4651:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());
            }
            // InternalIdioms.g:4652:3: ( RULE_ID )
            // InternalIdioms.g:4653:4: RULE_ID
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
    // InternalIdioms.g:4664:1: rule__ReferredSegment__SegmentDeclarationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ReferredSegment__SegmentDeclarationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4668:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4669:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4669:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4670:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());
            }
            // InternalIdioms.g:4671:3: ( RULE_ID )
            // InternalIdioms.g:4672:4: RULE_ID
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
    // InternalIdioms.g:4683:1: rule__Idiom__MixinAssignment_0 : ( ( 'mixin' ) ) ;
    public final void rule__Idiom__MixinAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4687:1: ( ( ( 'mixin' ) ) )
            // InternalIdioms.g:4688:2: ( ( 'mixin' ) )
            {
            // InternalIdioms.g:4688:2: ( ( 'mixin' ) )
            // InternalIdioms.g:4689:3: ( 'mixin' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());
            }
            // InternalIdioms.g:4690:3: ( 'mixin' )
            // InternalIdioms.g:4691:4: 'mixin'
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
    // InternalIdioms.g:4702:1: rule__Idiom__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Idiom__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4706:1: ( ( RULE_ID ) )
            // InternalIdioms.g:4707:2: ( RULE_ID )
            {
            // InternalIdioms.g:4707:2: ( RULE_ID )
            // InternalIdioms.g:4708:3: RULE_ID
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
    // InternalIdioms.g:4717:1: rule__Idiom__ForEPackageAssignment_3_1_0 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEPackageAssignment_3_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4721:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4722:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4722:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4723:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());
            }
            // InternalIdioms.g:4724:3: ( RULE_ID )
            // InternalIdioms.g:4725:4: RULE_ID
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
    // InternalIdioms.g:4736:1: rule__Idiom__ForEClassAssignment_3_2 : ( ( RULE_ID ) ) ;
    public final void rule__Idiom__ForEClassAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4740:1: ( ( ( RULE_ID ) ) )
            // InternalIdioms.g:4741:2: ( ( RULE_ID ) )
            {
            // InternalIdioms.g:4741:2: ( ( RULE_ID ) )
            // InternalIdioms.g:4742:3: ( RULE_ID )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());
            }
            // InternalIdioms.g:4743:3: ( RULE_ID )
            // InternalIdioms.g:4744:4: RULE_ID
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
    // InternalIdioms.g:4755:1: rule__Idiom__InRuleRegexAssignment_4_1 : ( RULE_STRING ) ;
    public final void rule__Idiom__InRuleRegexAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4759:1: ( ( RULE_STRING ) )
            // InternalIdioms.g:4760:2: ( RULE_STRING )
            {
            // InternalIdioms.g:4760:2: ( RULE_STRING )
            // InternalIdioms.g:4761:3: RULE_STRING
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
    // InternalIdioms.g:4770:1: rule__Idiom__OwnedSubIdiomsAssignment_5_0 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4774:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:4775:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:4775:2: ( ruleSubIdiom )
            // InternalIdioms.g:4776:3: ruleSubIdiom
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
    // InternalIdioms.g:4785:1: rule__Idiom__OwnedSubIdiomsAssignment_5_1_1 : ( ruleSubIdiom ) ;
    public final void rule__Idiom__OwnedSubIdiomsAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4789:1: ( ( ruleSubIdiom ) )
            // InternalIdioms.g:4790:2: ( ruleSubIdiom )
            {
            // InternalIdioms.g:4790:2: ( ruleSubIdiom )
            // InternalIdioms.g:4791:3: ruleSubIdiom
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
    // InternalIdioms.g:4800:1: rule__SubIdiom__AllAssignment_1_0 : ( ( 'all' ) ) ;
    public final void rule__SubIdiom__AllAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4804:1: ( ( ( 'all' ) ) )
            // InternalIdioms.g:4805:2: ( ( 'all' ) )
            {
            // InternalIdioms.g:4805:2: ( ( 'all' ) )
            // InternalIdioms.g:4806:3: ( 'all' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());
            }
            // InternalIdioms.g:4807:3: ( 'all' )
            // InternalIdioms.g:4808:4: 'all'
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
    // InternalIdioms.g:4819:1: rule__SubIdiom__OwnedLocatorAssignment_2 : ( ruleLocator ) ;
    public final void rule__SubIdiom__OwnedLocatorAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4823:1: ( ( ruleLocator ) )
            // InternalIdioms.g:4824:2: ( ruleLocator )
            {
            // InternalIdioms.g:4824:2: ( ruleLocator )
            // InternalIdioms.g:4825:3: ruleLocator
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
    // InternalIdioms.g:4834:1: rule__SubIdiom__OwnedSegmentsAssignment_3_1 : ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) ;
    public final void rule__SubIdiom__OwnedSegmentsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalIdioms.g:4838:1: ( ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) ) )
            // InternalIdioms.g:4839:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            {
            // InternalIdioms.g:4839:2: ( ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 ) )
            // InternalIdioms.g:4840:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getSubIdiomAccess().getOwnedSegmentsAlternatives_3_1_0());
            }
            // InternalIdioms.g:4841:3: ( rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0 )
            // InternalIdioms.g:4841:4: rule__SubIdiom__OwnedSegmentsAlternatives_3_1_0
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
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0008080002066000L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002002L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000020002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0008080002040002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000018000L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000001B80030L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000007FFFC000000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0004000000000000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0008080002040000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0001700000000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0001800000000000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0001000000000002L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0010000001B80830L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0002000000008000L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x000007FFFC000010L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x000007FFFC000012L});
    }


}