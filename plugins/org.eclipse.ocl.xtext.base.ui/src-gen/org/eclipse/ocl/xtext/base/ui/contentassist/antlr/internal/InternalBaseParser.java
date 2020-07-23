package org.eclipse.ocl.xtext.base.ui.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.eclipse.ocl.xtext.base.services.BaseGrammarAccess;



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
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalBaseParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_ML_DOCUMENTATION", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_SINGLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'|?'", "'*'", "'+'", "'?'", "'..'", "'['", "']'", "'::'", "','", "'extends'", "'&&'", "'('", "')'", "'|1'"
    };
    public static final int RULE_ML_DOCUMENTATION=7;
    public static final int RULE_LETTER_CHARACTER=9;
    public static final int RULE_SL_COMMENT=14;
    public static final int T__19=19;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int RULE_ESCAPED_CHARACTER=8;
    public static final int RULE_ML_SINGLE_QUOTED_STRING=12;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int RULE_SIMPLE_ID=5;
    public static final int RULE_WS=15;
    public static final int RULE_ANY_OTHER=16;
    public static final int RULE_SINGLE_QUOTED_STRING=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int RULE_DOUBLE_QUOTED_STRING=10;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_ESCAPED_ID=6;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalBaseParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBaseParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);

        }


    public String[] getTokenNames() { return InternalBaseParser.tokenNames; }
    public String getGrammarFileName() { return "InternalBase.g"; }



     	private BaseGrammarAccess grammarAccess;

        public void setGrammarAccess(BaseGrammarAccess grammarAccess) {
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




    // $ANTLR start "entryRuleCommentCS"
    // InternalBase.g:68:1: entryRuleCommentCS : ruleCommentCS EOF ;
    public final void entryRuleCommentCS() throws RecognitionException {
        try {
            // InternalBase.g:69:1: ( ruleCommentCS EOF )
            // InternalBase.g:70:1: ruleCommentCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleCommentCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentCSRule());
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
    // $ANTLR end "entryRuleCommentCS"


    // $ANTLR start "ruleCommentCS"
    // InternalBase.g:77:1: ruleCommentCS : ( ( rule__CommentCS__ValueAssignment ) ) ;
    public final void ruleCommentCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:81:2: ( ( ( rule__CommentCS__ValueAssignment ) ) )
            // InternalBase.g:82:1: ( ( rule__CommentCS__ValueAssignment ) )
            {
            // InternalBase.g:82:1: ( ( rule__CommentCS__ValueAssignment ) )
            // InternalBase.g:83:1: ( rule__CommentCS__ValueAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentCSAccess().getValueAssignment());
            }
            // InternalBase.g:84:1: ( rule__CommentCS__ValueAssignment )
            // InternalBase.g:84:2: rule__CommentCS__ValueAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__CommentCS__ValueAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentCSAccess().getValueAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCommentCS"


    // $ANTLR start "entryRuleMultiplicityBoundsCS"
    // InternalBase.g:96:1: entryRuleMultiplicityBoundsCS : ruleMultiplicityBoundsCS EOF ;
    public final void entryRuleMultiplicityBoundsCS() throws RecognitionException {
        try {
            // InternalBase.g:97:1: ( ruleMultiplicityBoundsCS EOF )
            // InternalBase.g:98:1: ruleMultiplicityBoundsCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMultiplicityBoundsCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSRule());
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
    // $ANTLR end "entryRuleMultiplicityBoundsCS"


    // $ANTLR start "ruleMultiplicityBoundsCS"
    // InternalBase.g:105:1: ruleMultiplicityBoundsCS : ( ( rule__MultiplicityBoundsCS__Group__0 ) ) ;
    public final void ruleMultiplicityBoundsCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:109:2: ( ( ( rule__MultiplicityBoundsCS__Group__0 ) ) )
            // InternalBase.g:110:1: ( ( rule__MultiplicityBoundsCS__Group__0 ) )
            {
            // InternalBase.g:110:1: ( ( rule__MultiplicityBoundsCS__Group__0 ) )
            // InternalBase.g:111:1: ( rule__MultiplicityBoundsCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getGroup());
            }
            // InternalBase.g:112:1: ( rule__MultiplicityBoundsCS__Group__0 )
            // InternalBase.g:112:2: rule__MultiplicityBoundsCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplicityBoundsCS"


    // $ANTLR start "entryRuleMultiplicityCS"
    // InternalBase.g:124:1: entryRuleMultiplicityCS : ruleMultiplicityCS EOF ;
    public final void entryRuleMultiplicityCS() throws RecognitionException {
        try {
            // InternalBase.g:125:1: ( ruleMultiplicityCS EOF )
            // InternalBase.g:126:1: ruleMultiplicityCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMultiplicityCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSRule());
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
    // $ANTLR end "entryRuleMultiplicityCS"


    // $ANTLR start "ruleMultiplicityCS"
    // InternalBase.g:133:1: ruleMultiplicityCS : ( ( rule__MultiplicityCS__Group__0 ) ) ;
    public final void ruleMultiplicityCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:137:2: ( ( ( rule__MultiplicityCS__Group__0 ) ) )
            // InternalBase.g:138:1: ( ( rule__MultiplicityCS__Group__0 ) )
            {
            // InternalBase.g:138:1: ( ( rule__MultiplicityCS__Group__0 ) )
            // InternalBase.g:139:1: ( rule__MultiplicityCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getGroup());
            }
            // InternalBase.g:140:1: ( rule__MultiplicityCS__Group__0 )
            // InternalBase.g:140:2: rule__MultiplicityCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplicityCS"


    // $ANTLR start "entryRuleMultiplicityStringCS"
    // InternalBase.g:152:1: entryRuleMultiplicityStringCS : ruleMultiplicityStringCS EOF ;
    public final void entryRuleMultiplicityStringCS() throws RecognitionException {
        try {
            // InternalBase.g:153:1: ( ruleMultiplicityStringCS EOF )
            // InternalBase.g:154:1: ruleMultiplicityStringCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleMultiplicityStringCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityStringCSRule());
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
    // $ANTLR end "entryRuleMultiplicityStringCS"


    // $ANTLR start "ruleMultiplicityStringCS"
    // InternalBase.g:161:1: ruleMultiplicityStringCS : ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) ) ;
    public final void ruleMultiplicityStringCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:165:2: ( ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) ) )
            // InternalBase.g:166:1: ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) )
            {
            // InternalBase.g:166:1: ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) )
            // InternalBase.g:167:1: ( rule__MultiplicityStringCS__StringBoundsAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAssignment());
            }
            // InternalBase.g:168:1: ( rule__MultiplicityStringCS__StringBoundsAssignment )
            // InternalBase.g:168:2: rule__MultiplicityStringCS__StringBoundsAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityStringCS__StringBoundsAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplicityStringCS"


    // $ANTLR start "entryRulePathNameCS"
    // InternalBase.g:180:1: entryRulePathNameCS : rulePathNameCS EOF ;
    public final void entryRulePathNameCS() throws RecognitionException {
        try {
            // InternalBase.g:181:1: ( rulePathNameCS EOF )
            // InternalBase.g:182:1: rulePathNameCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            rulePathNameCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSRule());
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
    // $ANTLR end "entryRulePathNameCS"


    // $ANTLR start "rulePathNameCS"
    // InternalBase.g:189:1: rulePathNameCS : ( ( rule__PathNameCS__Group__0 ) ) ;
    public final void rulePathNameCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:193:2: ( ( ( rule__PathNameCS__Group__0 ) ) )
            // InternalBase.g:194:1: ( ( rule__PathNameCS__Group__0 ) )
            {
            // InternalBase.g:194:1: ( ( rule__PathNameCS__Group__0 ) )
            // InternalBase.g:195:1: ( rule__PathNameCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getGroup());
            }
            // InternalBase.g:196:1: ( rule__PathNameCS__Group__0 )
            // InternalBase.g:196:2: rule__PathNameCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePathNameCS"


    // $ANTLR start "entryRuleFirstPathElementCS"
    // InternalBase.g:208:1: entryRuleFirstPathElementCS : ruleFirstPathElementCS EOF ;
    public final void entryRuleFirstPathElementCS() throws RecognitionException {
        try {
            // InternalBase.g:209:1: ( ruleFirstPathElementCS EOF )
            // InternalBase.g:210:1: ruleFirstPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleFirstPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFirstPathElementCSRule());
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
    // $ANTLR end "entryRuleFirstPathElementCS"


    // $ANTLR start "ruleFirstPathElementCS"
    // InternalBase.g:217:1: ruleFirstPathElementCS : ( ( rule__FirstPathElementCS__ReferredElementAssignment ) ) ;
    public final void ruleFirstPathElementCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:221:2: ( ( ( rule__FirstPathElementCS__ReferredElementAssignment ) ) )
            // InternalBase.g:222:1: ( ( rule__FirstPathElementCS__ReferredElementAssignment ) )
            {
            // InternalBase.g:222:1: ( ( rule__FirstPathElementCS__ReferredElementAssignment ) )
            // InternalBase.g:223:1: ( rule__FirstPathElementCS__ReferredElementAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementAssignment());
            }
            // InternalBase.g:224:1: ( rule__FirstPathElementCS__ReferredElementAssignment )
            // InternalBase.g:224:2: rule__FirstPathElementCS__ReferredElementAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__FirstPathElementCS__ReferredElementAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFirstPathElementCSAccess().getReferredElementAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFirstPathElementCS"


    // $ANTLR start "entryRuleNextPathElementCS"
    // InternalBase.g:236:1: entryRuleNextPathElementCS : ruleNextPathElementCS EOF ;
    public final void entryRuleNextPathElementCS() throws RecognitionException {
        try {
            // InternalBase.g:237:1: ( ruleNextPathElementCS EOF )
            // InternalBase.g:238:1: ruleNextPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleNextPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNextPathElementCSRule());
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
    // $ANTLR end "entryRuleNextPathElementCS"


    // $ANTLR start "ruleNextPathElementCS"
    // InternalBase.g:245:1: ruleNextPathElementCS : ( ( rule__NextPathElementCS__ReferredElementAssignment ) ) ;
    public final void ruleNextPathElementCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:249:2: ( ( ( rule__NextPathElementCS__ReferredElementAssignment ) ) )
            // InternalBase.g:250:1: ( ( rule__NextPathElementCS__ReferredElementAssignment ) )
            {
            // InternalBase.g:250:1: ( ( rule__NextPathElementCS__ReferredElementAssignment ) )
            // InternalBase.g:251:1: ( rule__NextPathElementCS__ReferredElementAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementAssignment());
            }
            // InternalBase.g:252:1: ( rule__NextPathElementCS__ReferredElementAssignment )
            // InternalBase.g:252:2: rule__NextPathElementCS__ReferredElementAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__NextPathElementCS__ReferredElementAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNextPathElementCSAccess().getReferredElementAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNextPathElementCS"


    // $ANTLR start "entryRuleTemplateBindingCS"
    // InternalBase.g:264:1: entryRuleTemplateBindingCS : ruleTemplateBindingCS EOF ;
    public final void entryRuleTemplateBindingCS() throws RecognitionException {
        try {
            // InternalBase.g:265:1: ( ruleTemplateBindingCS EOF )
            // InternalBase.g:266:1: ruleTemplateBindingCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTemplateBindingCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSRule());
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
    // $ANTLR end "entryRuleTemplateBindingCS"


    // $ANTLR start "ruleTemplateBindingCS"
    // InternalBase.g:273:1: ruleTemplateBindingCS : ( ( rule__TemplateBindingCS__Group__0 ) ) ;
    public final void ruleTemplateBindingCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:277:2: ( ( ( rule__TemplateBindingCS__Group__0 ) ) )
            // InternalBase.g:278:1: ( ( rule__TemplateBindingCS__Group__0 ) )
            {
            // InternalBase.g:278:1: ( ( rule__TemplateBindingCS__Group__0 ) )
            // InternalBase.g:279:1: ( rule__TemplateBindingCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getGroup());
            }
            // InternalBase.g:280:1: ( rule__TemplateBindingCS__Group__0 )
            // InternalBase.g:280:2: rule__TemplateBindingCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTemplateBindingCS"


    // $ANTLR start "entryRuleTemplateParameterSubstitutionCS"
    // InternalBase.g:292:1: entryRuleTemplateParameterSubstitutionCS : ruleTemplateParameterSubstitutionCS EOF ;
    public final void entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        try {
            // InternalBase.g:293:1: ( ruleTemplateParameterSubstitutionCS EOF )
            // InternalBase.g:294:1: ruleTemplateParameterSubstitutionCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateParameterSubstitutionCSRule());
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
    // $ANTLR end "entryRuleTemplateParameterSubstitutionCS"


    // $ANTLR start "ruleTemplateParameterSubstitutionCS"
    // InternalBase.g:301:1: ruleTemplateParameterSubstitutionCS : ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) ) ;
    public final void ruleTemplateParameterSubstitutionCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:305:2: ( ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) ) )
            // InternalBase.g:306:1: ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) )
            {
            // InternalBase.g:306:1: ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) )
            // InternalBase.g:307:1: ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterAssignment());
            }
            // InternalBase.g:308:1: ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment )
            // InternalBase.g:308:2: rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterAssignment());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTemplateParameterSubstitutionCS"


    // $ANTLR start "entryRuleTypeParameterCS"
    // InternalBase.g:322:1: entryRuleTypeParameterCS : ruleTypeParameterCS EOF ;
    public final void entryRuleTypeParameterCS() throws RecognitionException {
        try {
            // InternalBase.g:323:1: ( ruleTypeParameterCS EOF )
            // InternalBase.g:324:1: ruleTypeParameterCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTypeParameterCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSRule());
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
    // $ANTLR end "entryRuleTypeParameterCS"


    // $ANTLR start "ruleTypeParameterCS"
    // InternalBase.g:331:1: ruleTypeParameterCS : ( ( rule__TypeParameterCS__Group__0 ) ) ;
    public final void ruleTypeParameterCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:335:2: ( ( ( rule__TypeParameterCS__Group__0 ) ) )
            // InternalBase.g:336:1: ( ( rule__TypeParameterCS__Group__0 ) )
            {
            // InternalBase.g:336:1: ( ( rule__TypeParameterCS__Group__0 ) )
            // InternalBase.g:337:1: ( rule__TypeParameterCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup());
            }
            // InternalBase.g:338:1: ( rule__TypeParameterCS__Group__0 )
            // InternalBase.g:338:2: rule__TypeParameterCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeParameterCS"


    // $ANTLR start "entryRuleTypeRefCS"
    // InternalBase.g:350:1: entryRuleTypeRefCS : ruleTypeRefCS EOF ;
    public final void entryRuleTypeRefCS() throws RecognitionException {
        try {
            // InternalBase.g:351:1: ( ruleTypeRefCS EOF )
            // InternalBase.g:352:1: ruleTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeRefCSRule());
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
    // $ANTLR end "entryRuleTypeRefCS"


    // $ANTLR start "ruleTypeRefCS"
    // InternalBase.g:359:1: ruleTypeRefCS : ( ( rule__TypeRefCS__Alternatives ) ) ;
    public final void ruleTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:363:2: ( ( ( rule__TypeRefCS__Alternatives ) ) )
            // InternalBase.g:364:1: ( ( rule__TypeRefCS__Alternatives ) )
            {
            // InternalBase.g:364:1: ( ( rule__TypeRefCS__Alternatives ) )
            // InternalBase.g:365:1: ( rule__TypeRefCS__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeRefCSAccess().getAlternatives());
            }
            // InternalBase.g:366:1: ( rule__TypeRefCS__Alternatives )
            // InternalBase.g:366:2: rule__TypeRefCS__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeRefCS__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeRefCSAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeRefCS"


    // $ANTLR start "entryRuleTypedRefCS"
    // InternalBase.g:378:1: entryRuleTypedRefCS : ruleTypedRefCS EOF ;
    public final void entryRuleTypedRefCS() throws RecognitionException {
        try {
            // InternalBase.g:379:1: ( ruleTypedRefCS EOF )
            // InternalBase.g:380:1: ruleTypedRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedRefCSRule());
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
    // $ANTLR end "entryRuleTypedRefCS"


    // $ANTLR start "ruleTypedRefCS"
    // InternalBase.g:387:1: ruleTypedRefCS : ( ruleTypedTypeRefCS ) ;
    public final void ruleTypedRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:391:2: ( ( ruleTypedTypeRefCS ) )
            // InternalBase.g:392:1: ( ruleTypedTypeRefCS )
            {
            // InternalBase.g:392:1: ( ruleTypedTypeRefCS )
            // InternalBase.g:393:1: ruleTypedTypeRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedRefCSAccess().getTypedTypeRefCSParserRuleCall());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTypedTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedRefCSAccess().getTypedTypeRefCSParserRuleCall());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypedRefCS"


    // $ANTLR start "entryRuleTypedTypeRefCS"
    // InternalBase.g:406:1: entryRuleTypedTypeRefCS : ruleTypedTypeRefCS EOF ;
    public final void entryRuleTypedTypeRefCS() throws RecognitionException {
        try {
            // InternalBase.g:407:1: ( ruleTypedTypeRefCS EOF )
            // InternalBase.g:408:1: ruleTypedTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleTypedTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSRule());
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
    // $ANTLR end "entryRuleTypedTypeRefCS"


    // $ANTLR start "ruleTypedTypeRefCS"
    // InternalBase.g:415:1: ruleTypedTypeRefCS : ( ( rule__TypedTypeRefCS__Group__0 ) ) ;
    public final void ruleTypedTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:419:2: ( ( ( rule__TypedTypeRefCS__Group__0 ) ) )
            // InternalBase.g:420:1: ( ( rule__TypedTypeRefCS__Group__0 ) )
            {
            // InternalBase.g:420:1: ( ( rule__TypedTypeRefCS__Group__0 ) )
            // InternalBase.g:421:1: ( rule__TypedTypeRefCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getGroup());
            }
            // InternalBase.g:422:1: ( rule__TypedTypeRefCS__Group__0 )
            // InternalBase.g:422:2: rule__TypedTypeRefCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypedTypeRefCS"


    // $ANTLR start "entryRuleUnreservedName"
    // InternalBase.g:434:1: entryRuleUnreservedName : ruleUnreservedName EOF ;
    public final void entryRuleUnreservedName() throws RecognitionException {
        try {
            // InternalBase.g:435:1: ( ruleUnreservedName EOF )
            // InternalBase.g:436:1: ruleUnreservedName EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnreservedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleUnreservedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnreservedNameRule());
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
    // $ANTLR end "entryRuleUnreservedName"


    // $ANTLR start "ruleUnreservedName"
    // InternalBase.g:443:1: ruleUnreservedName : ( ruleUnrestrictedName ) ;
    public final void ruleUnreservedName() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:447:2: ( ( ruleUnrestrictedName ) )
            // InternalBase.g:448:1: ( ruleUnrestrictedName )
            {
            // InternalBase.g:448:1: ( ruleUnrestrictedName )
            // InternalBase.g:449:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnreservedNameAccess().getUnrestrictedNameParserRuleCall());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnreservedNameAccess().getUnrestrictedNameParserRuleCall());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnreservedName"


    // $ANTLR start "entryRuleUnrestrictedName"
    // InternalBase.g:462:1: entryRuleUnrestrictedName : ruleUnrestrictedName EOF ;
    public final void entryRuleUnrestrictedName() throws RecognitionException {
        try {
            // InternalBase.g:463:1: ( ruleUnrestrictedName EOF )
            // InternalBase.g:464:1: ruleUnrestrictedName EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnrestrictedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnrestrictedNameRule());
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
    // $ANTLR end "entryRuleUnrestrictedName"


    // $ANTLR start "ruleUnrestrictedName"
    // InternalBase.g:471:1: ruleUnrestrictedName : ( ruleIdentifier ) ;
    public final void ruleUnrestrictedName() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:475:2: ( ( ruleIdentifier ) )
            // InternalBase.g:476:1: ( ruleIdentifier )
            {
            // InternalBase.g:476:1: ( ruleIdentifier )
            // InternalBase.g:477:1: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnrestrictedNameAccess().getIdentifierParserRuleCall());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnrestrictedNameAccess().getIdentifierParserRuleCall());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnrestrictedName"


    // $ANTLR start "entryRuleWildcardTypeRefCS"
    // InternalBase.g:490:1: entryRuleWildcardTypeRefCS : ruleWildcardTypeRefCS EOF ;
    public final void entryRuleWildcardTypeRefCS() throws RecognitionException {
        try {
            // InternalBase.g:491:1: ( ruleWildcardTypeRefCS EOF )
            // InternalBase.g:492:1: ruleWildcardTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleWildcardTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSRule());
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
    // $ANTLR end "entryRuleWildcardTypeRefCS"


    // $ANTLR start "ruleWildcardTypeRefCS"
    // InternalBase.g:499:1: ruleWildcardTypeRefCS : ( ( rule__WildcardTypeRefCS__Group__0 ) ) ;
    public final void ruleWildcardTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:503:2: ( ( ( rule__WildcardTypeRefCS__Group__0 ) ) )
            // InternalBase.g:504:1: ( ( rule__WildcardTypeRefCS__Group__0 ) )
            {
            // InternalBase.g:504:1: ( ( rule__WildcardTypeRefCS__Group__0 ) )
            // InternalBase.g:505:1: ( rule__WildcardTypeRefCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getGroup());
            }
            // InternalBase.g:506:1: ( rule__WildcardTypeRefCS__Group__0 )
            // InternalBase.g:506:2: rule__WildcardTypeRefCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group__0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getGroup());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWildcardTypeRefCS"


    // $ANTLR start "entryRuleID"
    // InternalBase.g:518:1: entryRuleID : ruleID EOF ;
    public final void entryRuleID() throws RecognitionException {
        try {
            // InternalBase.g:519:1: ( ruleID EOF )
            // InternalBase.g:520:1: ruleID EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIDRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIDRule());
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
    // $ANTLR end "entryRuleID"


    // $ANTLR start "ruleID"
    // InternalBase.g:527:1: ruleID : ( ( rule__ID__Alternatives ) ) ;
    public final void ruleID() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:531:2: ( ( ( rule__ID__Alternatives ) ) )
            // InternalBase.g:532:1: ( ( rule__ID__Alternatives ) )
            {
            // InternalBase.g:532:1: ( ( rule__ID__Alternatives ) )
            // InternalBase.g:533:1: ( rule__ID__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIDAccess().getAlternatives());
            }
            // InternalBase.g:534:1: ( rule__ID__Alternatives )
            // InternalBase.g:534:2: rule__ID__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__ID__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getIDAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleID"


    // $ANTLR start "entryRuleIdentifier"
    // InternalBase.g:546:1: entryRuleIdentifier : ruleIdentifier EOF ;
    public final void entryRuleIdentifier() throws RecognitionException {
        try {
            // InternalBase.g:547:1: ( ruleIdentifier EOF )
            // InternalBase.g:548:1: ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentifierRule());
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
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // InternalBase.g:555:1: ruleIdentifier : ( ruleID ) ;
    public final void ruleIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:559:2: ( ( ruleID ) )
            // InternalBase.g:560:1: ( ruleID )
            {
            // InternalBase.g:560:1: ( ruleID )
            // InternalBase.g:561:1: ruleID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierAccess().getIDParserRuleCall());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentifierAccess().getIDParserRuleCall());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIdentifier"


    // $ANTLR start "entryRuleLOWER"
    // InternalBase.g:574:1: entryRuleLOWER : ruleLOWER EOF ;
    public final void entryRuleLOWER() throws RecognitionException {
        try {
            // InternalBase.g:575:1: ( ruleLOWER EOF )
            // InternalBase.g:576:1: ruleLOWER EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLOWERRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleLOWER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLOWERRule());
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
    // $ANTLR end "entryRuleLOWER"


    // $ANTLR start "ruleLOWER"
    // InternalBase.g:583:1: ruleLOWER : ( RULE_INT ) ;
    public final void ruleLOWER() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:587:2: ( ( RULE_INT ) )
            // InternalBase.g:588:1: ( RULE_INT )
            {
            // InternalBase.g:588:1: ( RULE_INT )
            // InternalBase.g:589:1: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLOWERAccess().getINTTerminalRuleCall());
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLOWERAccess().getINTTerminalRuleCall());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLOWER"


    // $ANTLR start "entryRuleUPPER"
    // InternalBase.g:606:1: entryRuleUPPER : ruleUPPER EOF ;
    public final void entryRuleUPPER() throws RecognitionException {
        try {
            // InternalBase.g:607:1: ( ruleUPPER EOF )
            // InternalBase.g:608:1: ruleUPPER EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUPPERRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            ruleUPPER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUPPERRule());
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
    // $ANTLR end "entryRuleUPPER"


    // $ANTLR start "ruleUPPER"
    // InternalBase.g:615:1: ruleUPPER : ( ( rule__UPPER__Alternatives ) ) ;
    public final void ruleUPPER() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:619:2: ( ( ( rule__UPPER__Alternatives ) ) )
            // InternalBase.g:620:1: ( ( rule__UPPER__Alternatives ) )
            {
            // InternalBase.g:620:1: ( ( rule__UPPER__Alternatives ) )
            // InternalBase.g:621:1: ( rule__UPPER__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUPPERAccess().getAlternatives());
            }
            // InternalBase.g:622:1: ( rule__UPPER__Alternatives )
            // InternalBase.g:622:2: rule__UPPER__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__UPPER__Alternatives();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getUPPERAccess().getAlternatives());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUPPER"


    // $ANTLR start "rule__MultiplicityCS__Alternatives_1"
    // InternalBase.g:636:1: rule__MultiplicityCS__Alternatives_1 : ( ( ruleMultiplicityBoundsCS ) | ( ruleMultiplicityStringCS ) );
    public final void rule__MultiplicityCS__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:640:1: ( ( ruleMultiplicityBoundsCS ) | ( ruleMultiplicityStringCS ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_INT) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=18 && LA1_0<=20)) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalBase.g:641:1: ( ruleMultiplicityBoundsCS )
                    {
                    // InternalBase.g:641:1: ( ruleMultiplicityBoundsCS )
                    // InternalBase.g:642:1: ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleMultiplicityBoundsCS();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:647:6: ( ruleMultiplicityStringCS )
                    {
                    // InternalBase.g:647:6: ( ruleMultiplicityStringCS )
                    // InternalBase.g:648:1: ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleMultiplicityStringCS();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());
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
    // $ANTLR end "rule__MultiplicityCS__Alternatives_1"


    // $ANTLR start "rule__MultiplicityCS__Alternatives_2"
    // InternalBase.g:658:1: rule__MultiplicityCS__Alternatives_2 : ( ( '|?' ) | ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) ) );
    public final void rule__MultiplicityCS__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:662:1: ( ( '|?' ) | ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            else if ( (LA2_0==30) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalBase.g:663:1: ( '|?' )
                    {
                    // InternalBase.g:663:1: ( '|?' )
                    // InternalBase.g:664:1: '|?'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());
                    }
                    match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:671:6: ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) )
                    {
                    // InternalBase.g:671:6: ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) )
                    // InternalBase.g:672:1: ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getIsNullFreeAssignment_2_1());
                    }
                    // InternalBase.g:673:1: ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 )
                    // InternalBase.g:673:2: rule__MultiplicityCS__IsNullFreeAssignment_2_1
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__MultiplicityCS__IsNullFreeAssignment_2_1();

                    state._fsp--;
                    if (state.failed) return ;

                    }

                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityCSAccess().getIsNullFreeAssignment_2_1());
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
    // $ANTLR end "rule__MultiplicityCS__Alternatives_2"


    // $ANTLR start "rule__MultiplicityStringCS__StringBoundsAlternatives_0"
    // InternalBase.g:682:1: rule__MultiplicityStringCS__StringBoundsAlternatives_0 : ( ( '*' ) | ( '+' ) | ( '?' ) );
    public final void rule__MultiplicityStringCS__StringBoundsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:686:1: ( ( '*' ) | ( '+' ) | ( '?' ) )
            int alt3=3;
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
            case 20:
                {
                alt3=3;
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
                    // InternalBase.g:687:1: ( '*' )
                    {
                    // InternalBase.g:687:1: ( '*' )
                    // InternalBase.g:688:1: '*'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAsteriskKeyword_0_0());
                    }
                    match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAsteriskKeyword_0_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:695:6: ( '+' )
                    {
                    // InternalBase.g:695:6: ( '+' )
                    // InternalBase.g:696:1: '+'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsPlusSignKeyword_0_1());
                    }
                    match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsPlusSignKeyword_0_1());
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalBase.g:703:6: ( '?' )
                    {
                    // InternalBase.g:703:6: ( '?' )
                    // InternalBase.g:704:1: '?'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsQuestionMarkKeyword_0_2());
                    }
                    match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsQuestionMarkKeyword_0_2());
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
    // $ANTLR end "rule__MultiplicityStringCS__StringBoundsAlternatives_0"


    // $ANTLR start "rule__TypeRefCS__Alternatives"
    // InternalBase.g:716:1: rule__TypeRefCS__Alternatives : ( ( ruleTypedRefCS ) | ( ruleWildcardTypeRefCS ) );
    public final void rule__TypeRefCS__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:720:1: ( ( ruleTypedRefCS ) | ( ruleWildcardTypeRefCS ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=RULE_SIMPLE_ID && LA4_0<=RULE_ESCAPED_ID)) ) {
                alt4=1;
            }
            else if ( (LA4_0==20) ) {
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
                    // InternalBase.g:721:1: ( ruleTypedRefCS )
                    {
                    // InternalBase.g:721:1: ( ruleTypedRefCS )
                    // InternalBase.g:722:1: ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeRefCSAccess().getTypedRefCSParserRuleCall_0());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleTypedRefCS();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTypeRefCSAccess().getTypedRefCSParserRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:727:6: ( ruleWildcardTypeRefCS )
                    {
                    // InternalBase.g:727:6: ( ruleWildcardTypeRefCS )
                    // InternalBase.g:728:1: ruleWildcardTypeRefCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeRefCSAccess().getWildcardTypeRefCSParserRuleCall_1());
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleWildcardTypeRefCS();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getTypeRefCSAccess().getWildcardTypeRefCSParserRuleCall_1());
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
    // $ANTLR end "rule__TypeRefCS__Alternatives"


    // $ANTLR start "rule__ID__Alternatives"
    // InternalBase.g:738:1: rule__ID__Alternatives : ( ( RULE_SIMPLE_ID ) | ( RULE_ESCAPED_ID ) );
    public final void rule__ID__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:742:1: ( ( RULE_SIMPLE_ID ) | ( RULE_ESCAPED_ID ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_SIMPLE_ID) ) {
                alt5=1;
            }
            else if ( (LA5_0==RULE_ESCAPED_ID) ) {
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
                    // InternalBase.g:743:1: ( RULE_SIMPLE_ID )
                    {
                    // InternalBase.g:743:1: ( RULE_SIMPLE_ID )
                    // InternalBase.g:744:1: RULE_SIMPLE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIDAccess().getSIMPLE_IDTerminalRuleCall_0());
                    }
                    match(input,RULE_SIMPLE_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIDAccess().getSIMPLE_IDTerminalRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:749:6: ( RULE_ESCAPED_ID )
                    {
                    // InternalBase.g:749:6: ( RULE_ESCAPED_ID )
                    // InternalBase.g:750:1: RULE_ESCAPED_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIDAccess().getESCAPED_IDTerminalRuleCall_1());
                    }
                    match(input,RULE_ESCAPED_ID,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIDAccess().getESCAPED_IDTerminalRuleCall_1());
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
    // $ANTLR end "rule__ID__Alternatives"


    // $ANTLR start "rule__UPPER__Alternatives"
    // InternalBase.g:760:1: rule__UPPER__Alternatives : ( ( RULE_INT ) | ( '*' ) );
    public final void rule__UPPER__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:764:1: ( ( RULE_INT ) | ( '*' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_INT) ) {
                alt6=1;
            }
            else if ( (LA6_0==18) ) {
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
                    // InternalBase.g:765:1: ( RULE_INT )
                    {
                    // InternalBase.g:765:1: ( RULE_INT )
                    // InternalBase.g:766:1: RULE_INT
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUPPERAccess().getINTTerminalRuleCall_0());
                    }
                    match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUPPERAccess().getINTTerminalRuleCall_0());
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBase.g:771:6: ( '*' )
                    {
                    // InternalBase.g:771:6: ( '*' )
                    // InternalBase.g:772:1: '*'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUPPERAccess().getAsteriskKeyword_1());
                    }
                    match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUPPERAccess().getAsteriskKeyword_1());
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
    // $ANTLR end "rule__UPPER__Alternatives"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group__0"
    // InternalBase.g:786:1: rule__MultiplicityBoundsCS__Group__0 : rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1 ;
    public final void rule__MultiplicityBoundsCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:790:1: ( rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1 )
            // InternalBase.g:791:2: rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_3);
            rule__MultiplicityBoundsCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group__0"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group__0__Impl"
    // InternalBase.g:798:1: rule__MultiplicityBoundsCS__Group__0__Impl : ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) ) ;
    public final void rule__MultiplicityBoundsCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:802:1: ( ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) ) )
            // InternalBase.g:803:1: ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) )
            {
            // InternalBase.g:803:1: ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) )
            // InternalBase.g:804:1: ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundAssignment_0());
            }
            // InternalBase.g:805:1: ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 )
            // InternalBase.g:805:2: rule__MultiplicityBoundsCS__LowerBoundAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__LowerBoundAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group__0__Impl"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group__1"
    // InternalBase.g:815:1: rule__MultiplicityBoundsCS__Group__1 : rule__MultiplicityBoundsCS__Group__1__Impl ;
    public final void rule__MultiplicityBoundsCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:819:1: ( rule__MultiplicityBoundsCS__Group__1__Impl )
            // InternalBase.g:820:2: rule__MultiplicityBoundsCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group__1"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group__1__Impl"
    // InternalBase.g:826:1: rule__MultiplicityBoundsCS__Group__1__Impl : ( ( rule__MultiplicityBoundsCS__Group_1__0 )? ) ;
    public final void rule__MultiplicityBoundsCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:830:1: ( ( ( rule__MultiplicityBoundsCS__Group_1__0 )? ) )
            // InternalBase.g:831:1: ( ( rule__MultiplicityBoundsCS__Group_1__0 )? )
            {
            // InternalBase.g:831:1: ( ( rule__MultiplicityBoundsCS__Group_1__0 )? )
            // InternalBase.g:832:1: ( rule__MultiplicityBoundsCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getGroup_1());
            }
            // InternalBase.g:833:1: ( rule__MultiplicityBoundsCS__Group_1__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalBase.g:833:2: rule__MultiplicityBoundsCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__MultiplicityBoundsCS__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group__1__Impl"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group_1__0"
    // InternalBase.g:847:1: rule__MultiplicityBoundsCS__Group_1__0 : rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1 ;
    public final void rule__MultiplicityBoundsCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:851:1: ( rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1 )
            // InternalBase.g:852:2: rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_4);
            rule__MultiplicityBoundsCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group_1__0"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group_1__0__Impl"
    // InternalBase.g:859:1: rule__MultiplicityBoundsCS__Group_1__0__Impl : ( '..' ) ;
    public final void rule__MultiplicityBoundsCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:863:1: ( ( '..' ) )
            // InternalBase.g:864:1: ( '..' )
            {
            // InternalBase.g:864:1: ( '..' )
            // InternalBase.g:865:1: '..'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());
            }
            match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group_1__0__Impl"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group_1__1"
    // InternalBase.g:878:1: rule__MultiplicityBoundsCS__Group_1__1 : rule__MultiplicityBoundsCS__Group_1__1__Impl ;
    public final void rule__MultiplicityBoundsCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:882:1: ( rule__MultiplicityBoundsCS__Group_1__1__Impl )
            // InternalBase.g:883:2: rule__MultiplicityBoundsCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group_1__1"


    // $ANTLR start "rule__MultiplicityBoundsCS__Group_1__1__Impl"
    // InternalBase.g:889:1: rule__MultiplicityBoundsCS__Group_1__1__Impl : ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) ) ;
    public final void rule__MultiplicityBoundsCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:893:1: ( ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) ) )
            // InternalBase.g:894:1: ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) )
            {
            // InternalBase.g:894:1: ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) )
            // InternalBase.g:895:1: ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundAssignment_1_1());
            }
            // InternalBase.g:896:1: ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 )
            // InternalBase.g:896:2: rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__Group_1__1__Impl"


    // $ANTLR start "rule__MultiplicityCS__Group__0"
    // InternalBase.g:910:1: rule__MultiplicityCS__Group__0 : rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1 ;
    public final void rule__MultiplicityCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:914:1: ( rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1 )
            // InternalBase.g:915:2: rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_5);
            rule__MultiplicityCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__0"


    // $ANTLR start "rule__MultiplicityCS__Group__0__Impl"
    // InternalBase.g:922:1: rule__MultiplicityCS__Group__0__Impl : ( '[' ) ;
    public final void rule__MultiplicityCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:926:1: ( ( '[' ) )
            // InternalBase.g:927:1: ( '[' )
            {
            // InternalBase.g:927:1: ( '[' )
            // InternalBase.g:928:1: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());
            }
            match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__0__Impl"


    // $ANTLR start "rule__MultiplicityCS__Group__1"
    // InternalBase.g:941:1: rule__MultiplicityCS__Group__1 : rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2 ;
    public final void rule__MultiplicityCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:945:1: ( rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2 )
            // InternalBase.g:946:2: rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__MultiplicityCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__1"


    // $ANTLR start "rule__MultiplicityCS__Group__1__Impl"
    // InternalBase.g:953:1: rule__MultiplicityCS__Group__1__Impl : ( ( rule__MultiplicityCS__Alternatives_1 ) ) ;
    public final void rule__MultiplicityCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:957:1: ( ( ( rule__MultiplicityCS__Alternatives_1 ) ) )
            // InternalBase.g:958:1: ( ( rule__MultiplicityCS__Alternatives_1 ) )
            {
            // InternalBase.g:958:1: ( ( rule__MultiplicityCS__Alternatives_1 ) )
            // InternalBase.g:959:1: ( rule__MultiplicityCS__Alternatives_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getAlternatives_1());
            }
            // InternalBase.g:960:1: ( rule__MultiplicityCS__Alternatives_1 )
            // InternalBase.g:960:2: rule__MultiplicityCS__Alternatives_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Alternatives_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getAlternatives_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__1__Impl"


    // $ANTLR start "rule__MultiplicityCS__Group__2"
    // InternalBase.g:970:1: rule__MultiplicityCS__Group__2 : rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3 ;
    public final void rule__MultiplicityCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:974:1: ( rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3 )
            // InternalBase.g:975:2: rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_6);
            rule__MultiplicityCS__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Group__3();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__2"


    // $ANTLR start "rule__MultiplicityCS__Group__2__Impl"
    // InternalBase.g:982:1: rule__MultiplicityCS__Group__2__Impl : ( ( rule__MultiplicityCS__Alternatives_2 )? ) ;
    public final void rule__MultiplicityCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:986:1: ( ( ( rule__MultiplicityCS__Alternatives_2 )? ) )
            // InternalBase.g:987:1: ( ( rule__MultiplicityCS__Alternatives_2 )? )
            {
            // InternalBase.g:987:1: ( ( rule__MultiplicityCS__Alternatives_2 )? )
            // InternalBase.g:988:1: ( rule__MultiplicityCS__Alternatives_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getAlternatives_2());
            }
            // InternalBase.g:989:1: ( rule__MultiplicityCS__Alternatives_2 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==17||LA8_0==30) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalBase.g:989:2: rule__MultiplicityCS__Alternatives_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__MultiplicityCS__Alternatives_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getAlternatives_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__2__Impl"


    // $ANTLR start "rule__MultiplicityCS__Group__3"
    // InternalBase.g:999:1: rule__MultiplicityCS__Group__3 : rule__MultiplicityCS__Group__3__Impl ;
    public final void rule__MultiplicityCS__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1003:1: ( rule__MultiplicityCS__Group__3__Impl )
            // InternalBase.g:1004:2: rule__MultiplicityCS__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityCS__Group__3__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__3"


    // $ANTLR start "rule__MultiplicityCS__Group__3__Impl"
    // InternalBase.g:1010:1: rule__MultiplicityCS__Group__3__Impl : ( ']' ) ;
    public final void rule__MultiplicityCS__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1014:1: ( ( ']' ) )
            // InternalBase.g:1015:1: ( ']' )
            {
            // InternalBase.g:1015:1: ( ']' )
            // InternalBase.g:1016:1: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getRightSquareBracketKeyword_3());
            }
            match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getRightSquareBracketKeyword_3());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__Group__3__Impl"


    // $ANTLR start "rule__PathNameCS__Group__0"
    // InternalBase.g:1037:1: rule__PathNameCS__Group__0 : rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1 ;
    public final void rule__PathNameCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1041:1: ( rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1 )
            // InternalBase.g:1042:2: rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_7);
            rule__PathNameCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group__0"


    // $ANTLR start "rule__PathNameCS__Group__0__Impl"
    // InternalBase.g:1049:1: rule__PathNameCS__Group__0__Impl : ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) ) ;
    public final void rule__PathNameCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1053:1: ( ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) ) )
            // InternalBase.g:1054:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) )
            {
            // InternalBase.g:1054:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) )
            // InternalBase.g:1055:1: ( rule__PathNameCS__OwnedPathElementsAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_0());
            }
            // InternalBase.g:1056:1: ( rule__PathNameCS__OwnedPathElementsAssignment_0 )
            // InternalBase.g:1056:2: rule__PathNameCS__OwnedPathElementsAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__OwnedPathElementsAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group__0__Impl"


    // $ANTLR start "rule__PathNameCS__Group__1"
    // InternalBase.g:1066:1: rule__PathNameCS__Group__1 : rule__PathNameCS__Group__1__Impl ;
    public final void rule__PathNameCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1070:1: ( rule__PathNameCS__Group__1__Impl )
            // InternalBase.g:1071:2: rule__PathNameCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group__1"


    // $ANTLR start "rule__PathNameCS__Group__1__Impl"
    // InternalBase.g:1077:1: rule__PathNameCS__Group__1__Impl : ( ( rule__PathNameCS__Group_1__0 )* ) ;
    public final void rule__PathNameCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1081:1: ( ( ( rule__PathNameCS__Group_1__0 )* ) )
            // InternalBase.g:1082:1: ( ( rule__PathNameCS__Group_1__0 )* )
            {
            // InternalBase.g:1082:1: ( ( rule__PathNameCS__Group_1__0 )* )
            // InternalBase.g:1083:1: ( rule__PathNameCS__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getGroup_1());
            }
            // InternalBase.g:1084:1: ( rule__PathNameCS__Group_1__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==24) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalBase.g:1084:2: rule__PathNameCS__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    rule__PathNameCS__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group__1__Impl"


    // $ANTLR start "rule__PathNameCS__Group_1__0"
    // InternalBase.g:1098:1: rule__PathNameCS__Group_1__0 : rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1 ;
    public final void rule__PathNameCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1102:1: ( rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1 )
            // InternalBase.g:1103:2: rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__PathNameCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group_1__0"


    // $ANTLR start "rule__PathNameCS__Group_1__0__Impl"
    // InternalBase.g:1110:1: rule__PathNameCS__Group_1__0__Impl : ( '::' ) ;
    public final void rule__PathNameCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1114:1: ( ( '::' ) )
            // InternalBase.g:1115:1: ( '::' )
            {
            // InternalBase.g:1115:1: ( '::' )
            // InternalBase.g:1116:1: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());
            }
            match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group_1__0__Impl"


    // $ANTLR start "rule__PathNameCS__Group_1__1"
    // InternalBase.g:1129:1: rule__PathNameCS__Group_1__1 : rule__PathNameCS__Group_1__1__Impl ;
    public final void rule__PathNameCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1133:1: ( rule__PathNameCS__Group_1__1__Impl )
            // InternalBase.g:1134:2: rule__PathNameCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group_1__1"


    // $ANTLR start "rule__PathNameCS__Group_1__1__Impl"
    // InternalBase.g:1140:1: rule__PathNameCS__Group_1__1__Impl : ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) ) ;
    public final void rule__PathNameCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1144:1: ( ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) ) )
            // InternalBase.g:1145:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) )
            {
            // InternalBase.g:1145:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) )
            // InternalBase.g:1146:1: ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_1_1());
            }
            // InternalBase.g:1147:1: ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 )
            // InternalBase.g:1147:2: rule__PathNameCS__OwnedPathElementsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__PathNameCS__OwnedPathElementsAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__Group_1__1__Impl"


    // $ANTLR start "rule__TemplateBindingCS__Group__0"
    // InternalBase.g:1161:1: rule__TemplateBindingCS__Group__0 : rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1 ;
    public final void rule__TemplateBindingCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1165:1: ( rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1 )
            // InternalBase.g:1166:2: rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__TemplateBindingCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__0"


    // $ANTLR start "rule__TemplateBindingCS__Group__0__Impl"
    // InternalBase.g:1173:1: rule__TemplateBindingCS__Group__0__Impl : ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) ) ;
    public final void rule__TemplateBindingCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1177:1: ( ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) ) )
            // InternalBase.g:1178:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) )
            {
            // InternalBase.g:1178:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) )
            // InternalBase.g:1179:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_0());
            }
            // InternalBase.g:1180:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 )
            // InternalBase.g:1180:2: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__0__Impl"


    // $ANTLR start "rule__TemplateBindingCS__Group__1"
    // InternalBase.g:1190:1: rule__TemplateBindingCS__Group__1 : rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2 ;
    public final void rule__TemplateBindingCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1194:1: ( rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2 )
            // InternalBase.g:1195:2: rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_10);
            rule__TemplateBindingCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__1"


    // $ANTLR start "rule__TemplateBindingCS__Group__1__Impl"
    // InternalBase.g:1202:1: rule__TemplateBindingCS__Group__1__Impl : ( ( rule__TemplateBindingCS__Group_1__0 )* ) ;
    public final void rule__TemplateBindingCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1206:1: ( ( ( rule__TemplateBindingCS__Group_1__0 )* ) )
            // InternalBase.g:1207:1: ( ( rule__TemplateBindingCS__Group_1__0 )* )
            {
            // InternalBase.g:1207:1: ( ( rule__TemplateBindingCS__Group_1__0 )* )
            // InternalBase.g:1208:1: ( rule__TemplateBindingCS__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getGroup_1());
            }
            // InternalBase.g:1209:1: ( rule__TemplateBindingCS__Group_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==25) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalBase.g:1209:2: rule__TemplateBindingCS__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_11);
            	    rule__TemplateBindingCS__Group_1__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__1__Impl"


    // $ANTLR start "rule__TemplateBindingCS__Group__2"
    // InternalBase.g:1219:1: rule__TemplateBindingCS__Group__2 : rule__TemplateBindingCS__Group__2__Impl ;
    public final void rule__TemplateBindingCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1223:1: ( rule__TemplateBindingCS__Group__2__Impl )
            // InternalBase.g:1224:2: rule__TemplateBindingCS__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__2"


    // $ANTLR start "rule__TemplateBindingCS__Group__2__Impl"
    // InternalBase.g:1230:1: rule__TemplateBindingCS__Group__2__Impl : ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? ) ;
    public final void rule__TemplateBindingCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1234:1: ( ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? ) )
            // InternalBase.g:1235:1: ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? )
            {
            // InternalBase.g:1235:1: ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? )
            // InternalBase.g:1236:1: ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityAssignment_2());
            }
            // InternalBase.g:1237:1: ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalBase.g:1237:2: rule__TemplateBindingCS__OwnedMultiplicityAssignment_2
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__TemplateBindingCS__OwnedMultiplicityAssignment_2();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityAssignment_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group__2__Impl"


    // $ANTLR start "rule__TemplateBindingCS__Group_1__0"
    // InternalBase.g:1253:1: rule__TemplateBindingCS__Group_1__0 : rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1 ;
    public final void rule__TemplateBindingCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1257:1: ( rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1 )
            // InternalBase.g:1258:2: rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__TemplateBindingCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group_1__0"


    // $ANTLR start "rule__TemplateBindingCS__Group_1__0__Impl"
    // InternalBase.g:1265:1: rule__TemplateBindingCS__Group_1__0__Impl : ( ',' ) ;
    public final void rule__TemplateBindingCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1269:1: ( ( ',' ) )
            // InternalBase.g:1270:1: ( ',' )
            {
            // InternalBase.g:1270:1: ( ',' )
            // InternalBase.g:1271:1: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());
            }
            match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group_1__0__Impl"


    // $ANTLR start "rule__TemplateBindingCS__Group_1__1"
    // InternalBase.g:1284:1: rule__TemplateBindingCS__Group_1__1 : rule__TemplateBindingCS__Group_1__1__Impl ;
    public final void rule__TemplateBindingCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1288:1: ( rule__TemplateBindingCS__Group_1__1__Impl )
            // InternalBase.g:1289:2: rule__TemplateBindingCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group_1__1"


    // $ANTLR start "rule__TemplateBindingCS__Group_1__1__Impl"
    // InternalBase.g:1295:1: rule__TemplateBindingCS__Group_1__1__Impl : ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) ) ;
    public final void rule__TemplateBindingCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1299:1: ( ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) ) )
            // InternalBase.g:1300:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) )
            {
            // InternalBase.g:1300:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) )
            // InternalBase.g:1301:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_1_1());
            }
            // InternalBase.g:1302:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 )
            // InternalBase.g:1302:2: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__Group_1__1__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group__0"
    // InternalBase.g:1318:1: rule__TypeParameterCS__Group__0 : rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1 ;
    public final void rule__TypeParameterCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1322:1: ( rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1 )
            // InternalBase.g:1323:2: rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_13);
            rule__TypeParameterCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group__0"


    // $ANTLR start "rule__TypeParameterCS__Group__0__Impl"
    // InternalBase.g:1330:1: rule__TypeParameterCS__Group__0__Impl : ( ( rule__TypeParameterCS__NameAssignment_0 ) ) ;
    public final void rule__TypeParameterCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1334:1: ( ( ( rule__TypeParameterCS__NameAssignment_0 ) ) )
            // InternalBase.g:1335:1: ( ( rule__TypeParameterCS__NameAssignment_0 ) )
            {
            // InternalBase.g:1335:1: ( ( rule__TypeParameterCS__NameAssignment_0 ) )
            // InternalBase.g:1336:1: ( rule__TypeParameterCS__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getNameAssignment_0());
            }
            // InternalBase.g:1337:1: ( rule__TypeParameterCS__NameAssignment_0 )
            // InternalBase.g:1337:2: rule__TypeParameterCS__NameAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__NameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getNameAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group__0__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group__1"
    // InternalBase.g:1347:1: rule__TypeParameterCS__Group__1 : rule__TypeParameterCS__Group__1__Impl ;
    public final void rule__TypeParameterCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1351:1: ( rule__TypeParameterCS__Group__1__Impl )
            // InternalBase.g:1352:2: rule__TypeParameterCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group__1"


    // $ANTLR start "rule__TypeParameterCS__Group__1__Impl"
    // InternalBase.g:1358:1: rule__TypeParameterCS__Group__1__Impl : ( ( rule__TypeParameterCS__Group_1__0 )? ) ;
    public final void rule__TypeParameterCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1362:1: ( ( ( rule__TypeParameterCS__Group_1__0 )? ) )
            // InternalBase.g:1363:1: ( ( rule__TypeParameterCS__Group_1__0 )? )
            {
            // InternalBase.g:1363:1: ( ( rule__TypeParameterCS__Group_1__0 )? )
            // InternalBase.g:1364:1: ( rule__TypeParameterCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup_1());
            }
            // InternalBase.g:1365:1: ( rule__TypeParameterCS__Group_1__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==26) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalBase.g:1365:2: rule__TypeParameterCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__TypeParameterCS__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group__1__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group_1__0"
    // InternalBase.g:1379:1: rule__TypeParameterCS__Group_1__0 : rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1 ;
    public final void rule__TypeParameterCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1383:1: ( rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1 )
            // InternalBase.g:1384:2: rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__TypeParameterCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__0"


    // $ANTLR start "rule__TypeParameterCS__Group_1__0__Impl"
    // InternalBase.g:1391:1: rule__TypeParameterCS__Group_1__0__Impl : ( 'extends' ) ;
    public final void rule__TypeParameterCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1395:1: ( ( 'extends' ) )
            // InternalBase.g:1396:1: ( 'extends' )
            {
            // InternalBase.g:1396:1: ( 'extends' )
            // InternalBase.g:1397:1: 'extends'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__0__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group_1__1"
    // InternalBase.g:1410:1: rule__TypeParameterCS__Group_1__1 : rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2 ;
    public final void rule__TypeParameterCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1414:1: ( rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2 )
            // InternalBase.g:1415:2: rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_14);
            rule__TypeParameterCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__1"


    // $ANTLR start "rule__TypeParameterCS__Group_1__1__Impl"
    // InternalBase.g:1422:1: rule__TypeParameterCS__Group_1__1__Impl : ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) ) ;
    public final void rule__TypeParameterCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1426:1: ( ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) ) )
            // InternalBase.g:1427:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) )
            {
            // InternalBase.g:1427:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) )
            // InternalBase.g:1428:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_1());
            }
            // InternalBase.g:1429:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 )
            // InternalBase.g:1429:2: rule__TypeParameterCS__OwnedExtendsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__OwnedExtendsAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__1__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group_1__2"
    // InternalBase.g:1439:1: rule__TypeParameterCS__Group_1__2 : rule__TypeParameterCS__Group_1__2__Impl ;
    public final void rule__TypeParameterCS__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1443:1: ( rule__TypeParameterCS__Group_1__2__Impl )
            // InternalBase.g:1444:2: rule__TypeParameterCS__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__2"


    // $ANTLR start "rule__TypeParameterCS__Group_1__2__Impl"
    // InternalBase.g:1450:1: rule__TypeParameterCS__Group_1__2__Impl : ( ( rule__TypeParameterCS__Group_1_2__0 )* ) ;
    public final void rule__TypeParameterCS__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1454:1: ( ( ( rule__TypeParameterCS__Group_1_2__0 )* ) )
            // InternalBase.g:1455:1: ( ( rule__TypeParameterCS__Group_1_2__0 )* )
            {
            // InternalBase.g:1455:1: ( ( rule__TypeParameterCS__Group_1_2__0 )* )
            // InternalBase.g:1456:1: ( rule__TypeParameterCS__Group_1_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup_1_2());
            }
            // InternalBase.g:1457:1: ( rule__TypeParameterCS__Group_1_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==27) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalBase.g:1457:2: rule__TypeParameterCS__Group_1_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_15);
            	    rule__TypeParameterCS__Group_1_2__0();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getGroup_1_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1__2__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group_1_2__0"
    // InternalBase.g:1473:1: rule__TypeParameterCS__Group_1_2__0 : rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1 ;
    public final void rule__TypeParameterCS__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1477:1: ( rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1 )
            // InternalBase.g:1478:2: rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__TypeParameterCS__Group_1_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group_1_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1_2__0"


    // $ANTLR start "rule__TypeParameterCS__Group_1_2__0__Impl"
    // InternalBase.g:1485:1: rule__TypeParameterCS__Group_1_2__0__Impl : ( '&&' ) ;
    public final void rule__TypeParameterCS__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1489:1: ( ( '&&' ) )
            // InternalBase.g:1490:1: ( '&&' )
            {
            // InternalBase.g:1490:1: ( '&&' )
            // InternalBase.g:1491:1: '&&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());
            }
            match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1_2__0__Impl"


    // $ANTLR start "rule__TypeParameterCS__Group_1_2__1"
    // InternalBase.g:1504:1: rule__TypeParameterCS__Group_1_2__1 : rule__TypeParameterCS__Group_1_2__1__Impl ;
    public final void rule__TypeParameterCS__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1508:1: ( rule__TypeParameterCS__Group_1_2__1__Impl )
            // InternalBase.g:1509:2: rule__TypeParameterCS__Group_1_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__Group_1_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1_2__1"


    // $ANTLR start "rule__TypeParameterCS__Group_1_2__1__Impl"
    // InternalBase.g:1515:1: rule__TypeParameterCS__Group_1_2__1__Impl : ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) ) ;
    public final void rule__TypeParameterCS__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1519:1: ( ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) ) )
            // InternalBase.g:1520:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) )
            {
            // InternalBase.g:1520:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) )
            // InternalBase.g:1521:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_2_1());
            }
            // InternalBase.g:1522:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 )
            // InternalBase.g:1522:2: rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__Group_1_2__1__Impl"


    // $ANTLR start "rule__TypedTypeRefCS__Group__0"
    // InternalBase.g:1536:1: rule__TypedTypeRefCS__Group__0 : rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1 ;
    public final void rule__TypedTypeRefCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1540:1: ( rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1 )
            // InternalBase.g:1541:2: rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_16);
            rule__TypedTypeRefCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group__0"


    // $ANTLR start "rule__TypedTypeRefCS__Group__0__Impl"
    // InternalBase.g:1548:1: rule__TypedTypeRefCS__Group__0__Impl : ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) ) ;
    public final void rule__TypedTypeRefCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1552:1: ( ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) ) )
            // InternalBase.g:1553:1: ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) )
            {
            // InternalBase.g:1553:1: ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) )
            // InternalBase.g:1554:1: ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_0());
            }
            // InternalBase.g:1555:1: ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 )
            // InternalBase.g:1555:2: rule__TypedTypeRefCS__OwnedPathNameAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__OwnedPathNameAssignment_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group__0__Impl"


    // $ANTLR start "rule__TypedTypeRefCS__Group__1"
    // InternalBase.g:1565:1: rule__TypedTypeRefCS__Group__1 : rule__TypedTypeRefCS__Group__1__Impl ;
    public final void rule__TypedTypeRefCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1569:1: ( rule__TypedTypeRefCS__Group__1__Impl )
            // InternalBase.g:1570:2: rule__TypedTypeRefCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group__1"


    // $ANTLR start "rule__TypedTypeRefCS__Group__1__Impl"
    // InternalBase.g:1576:1: rule__TypedTypeRefCS__Group__1__Impl : ( ( rule__TypedTypeRefCS__Group_1__0 )? ) ;
    public final void rule__TypedTypeRefCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1580:1: ( ( ( rule__TypedTypeRefCS__Group_1__0 )? ) )
            // InternalBase.g:1581:1: ( ( rule__TypedTypeRefCS__Group_1__0 )? )
            {
            // InternalBase.g:1581:1: ( ( rule__TypedTypeRefCS__Group_1__0 )? )
            // InternalBase.g:1582:1: ( rule__TypedTypeRefCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getGroup_1());
            }
            // InternalBase.g:1583:1: ( rule__TypedTypeRefCS__Group_1__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalBase.g:1583:2: rule__TypedTypeRefCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__TypedTypeRefCS__Group_1__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getGroup_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group__1__Impl"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__0"
    // InternalBase.g:1597:1: rule__TypedTypeRefCS__Group_1__0 : rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1 ;
    public final void rule__TypedTypeRefCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1601:1: ( rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1 )
            // InternalBase.g:1602:2: rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__TypedTypeRefCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group_1__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__0"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__0__Impl"
    // InternalBase.g:1609:1: rule__TypedTypeRefCS__Group_1__0__Impl : ( '(' ) ;
    public final void rule__TypedTypeRefCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1613:1: ( ( '(' ) )
            // InternalBase.g:1614:1: ( '(' )
            {
            // InternalBase.g:1614:1: ( '(' )
            // InternalBase.g:1615:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());
            }
            match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__0__Impl"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__1"
    // InternalBase.g:1628:1: rule__TypedTypeRefCS__Group_1__1 : rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2 ;
    public final void rule__TypedTypeRefCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1632:1: ( rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2 )
            // InternalBase.g:1633:2: rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_17);
            rule__TypedTypeRefCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group_1__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__1"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__1__Impl"
    // InternalBase.g:1640:1: rule__TypedTypeRefCS__Group_1__1__Impl : ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) ) ;
    public final void rule__TypedTypeRefCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1644:1: ( ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) ) )
            // InternalBase.g:1645:1: ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) )
            {
            // InternalBase.g:1645:1: ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) )
            // InternalBase.g:1646:1: ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingAssignment_1_1());
            }
            // InternalBase.g:1647:1: ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 )
            // InternalBase.g:1647:2: rule__TypedTypeRefCS__OwnedBindingAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__OwnedBindingAssignment_1_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingAssignment_1_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__1__Impl"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__2"
    // InternalBase.g:1657:1: rule__TypedTypeRefCS__Group_1__2 : rule__TypedTypeRefCS__Group_1__2__Impl ;
    public final void rule__TypedTypeRefCS__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1661:1: ( rule__TypedTypeRefCS__Group_1__2__Impl )
            // InternalBase.g:1662:2: rule__TypedTypeRefCS__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__TypedTypeRefCS__Group_1__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__2"


    // $ANTLR start "rule__TypedTypeRefCS__Group_1__2__Impl"
    // InternalBase.g:1668:1: rule__TypedTypeRefCS__Group_1__2__Impl : ( ')' ) ;
    public final void rule__TypedTypeRefCS__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1672:1: ( ( ')' ) )
            // InternalBase.g:1673:1: ( ')' )
            {
            // InternalBase.g:1673:1: ( ')' )
            // InternalBase.g:1674:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getRightParenthesisKeyword_1_2());
            }
            match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getRightParenthesisKeyword_1_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__Group_1__2__Impl"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__0"
    // InternalBase.g:1693:1: rule__WildcardTypeRefCS__Group__0 : rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1 ;
    public final void rule__WildcardTypeRefCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1697:1: ( rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1 )
            // InternalBase.g:1698:2: rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_12);
            rule__WildcardTypeRefCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__0"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__0__Impl"
    // InternalBase.g:1705:1: rule__WildcardTypeRefCS__Group__0__Impl : ( () ) ;
    public final void rule__WildcardTypeRefCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1709:1: ( ( () ) )
            // InternalBase.g:1710:1: ( () )
            {
            // InternalBase.g:1710:1: ( () )
            // InternalBase.g:1711:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getWildcardTypeRefCSAction_0());
            }
            // InternalBase.g:1712:1: ()
            // InternalBase.g:1714:1:
            {
            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getWildcardTypeRefCSAction_0());
            }

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__0__Impl"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__1"
    // InternalBase.g:1724:1: rule__WildcardTypeRefCS__Group__1 : rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2 ;
    public final void rule__WildcardTypeRefCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1728:1: ( rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2 )
            // InternalBase.g:1729:2: rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_13);
            rule__WildcardTypeRefCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group__2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__1"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__1__Impl"
    // InternalBase.g:1736:1: rule__WildcardTypeRefCS__Group__1__Impl : ( '?' ) ;
    public final void rule__WildcardTypeRefCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1740:1: ( ( '?' ) )
            // InternalBase.g:1741:1: ( '?' )
            {
            // InternalBase.g:1741:1: ( '?' )
            // InternalBase.g:1742:1: '?'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());
            }
            match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__1__Impl"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__2"
    // InternalBase.g:1755:1: rule__WildcardTypeRefCS__Group__2 : rule__WildcardTypeRefCS__Group__2__Impl ;
    public final void rule__WildcardTypeRefCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1759:1: ( rule__WildcardTypeRefCS__Group__2__Impl )
            // InternalBase.g:1760:2: rule__WildcardTypeRefCS__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__2"


    // $ANTLR start "rule__WildcardTypeRefCS__Group__2__Impl"
    // InternalBase.g:1766:1: rule__WildcardTypeRefCS__Group__2__Impl : ( ( rule__WildcardTypeRefCS__Group_2__0 )? ) ;
    public final void rule__WildcardTypeRefCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1770:1: ( ( ( rule__WildcardTypeRefCS__Group_2__0 )? ) )
            // InternalBase.g:1771:1: ( ( rule__WildcardTypeRefCS__Group_2__0 )? )
            {
            // InternalBase.g:1771:1: ( ( rule__WildcardTypeRefCS__Group_2__0 )? )
            // InternalBase.g:1772:1: ( rule__WildcardTypeRefCS__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getGroup_2());
            }
            // InternalBase.g:1773:1: ( rule__WildcardTypeRefCS__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalBase.g:1773:2: rule__WildcardTypeRefCS__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_2);
                    rule__WildcardTypeRefCS__Group_2__0();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getGroup_2());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group__2__Impl"


    // $ANTLR start "rule__WildcardTypeRefCS__Group_2__0"
    // InternalBase.g:1789:1: rule__WildcardTypeRefCS__Group_2__0 : rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1 ;
    public final void rule__WildcardTypeRefCS__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1793:1: ( rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1 )
            // InternalBase.g:1794:2: rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_9);
            rule__WildcardTypeRefCS__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group_2__1();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group_2__0"


    // $ANTLR start "rule__WildcardTypeRefCS__Group_2__0__Impl"
    // InternalBase.g:1801:1: rule__WildcardTypeRefCS__Group_2__0__Impl : ( 'extends' ) ;
    public final void rule__WildcardTypeRefCS__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1805:1: ( ( 'extends' ) )
            // InternalBase.g:1806:1: ( 'extends' )
            {
            // InternalBase.g:1806:1: ( 'extends' )
            // InternalBase.g:1807:1: 'extends'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());
            }
            match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group_2__0__Impl"


    // $ANTLR start "rule__WildcardTypeRefCS__Group_2__1"
    // InternalBase.g:1820:1: rule__WildcardTypeRefCS__Group_2__1 : rule__WildcardTypeRefCS__Group_2__1__Impl ;
    public final void rule__WildcardTypeRefCS__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1824:1: ( rule__WildcardTypeRefCS__Group_2__1__Impl )
            // InternalBase.g:1825:2: rule__WildcardTypeRefCS__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__Group_2__1__Impl();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group_2__1"


    // $ANTLR start "rule__WildcardTypeRefCS__Group_2__1__Impl"
    // InternalBase.g:1831:1: rule__WildcardTypeRefCS__Group_2__1__Impl : ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) ) ;
    public final void rule__WildcardTypeRefCS__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1835:1: ( ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) ) )
            // InternalBase.g:1836:1: ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) )
            {
            // InternalBase.g:1836:1: ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) )
            // InternalBase.g:1837:1: ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsAssignment_2_1());
            }
            // InternalBase.g:1838:1: ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 )
            // InternalBase.g:1838:2: rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsAssignment_2_1());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__Group_2__1__Impl"


    // $ANTLR start "rule__CommentCS__ValueAssignment"
    // InternalBase.g:1853:1: rule__CommentCS__ValueAssignment : ( RULE_ML_DOCUMENTATION ) ;
    public final void rule__CommentCS__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1857:1: ( ( RULE_ML_DOCUMENTATION ) )
            // InternalBase.g:1858:1: ( RULE_ML_DOCUMENTATION )
            {
            // InternalBase.g:1858:1: ( RULE_ML_DOCUMENTATION )
            // InternalBase.g:1859:1: RULE_ML_DOCUMENTATION
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getCommentCSAccess().getValueML_DOCUMENTATIONTerminalRuleCall_0());
            }
            match(input,RULE_ML_DOCUMENTATION,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getCommentCSAccess().getValueML_DOCUMENTATIONTerminalRuleCall_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CommentCS__ValueAssignment"


    // $ANTLR start "rule__MultiplicityBoundsCS__LowerBoundAssignment_0"
    // InternalBase.g:1868:1: rule__MultiplicityBoundsCS__LowerBoundAssignment_0 : ( ruleLOWER ) ;
    public final void rule__MultiplicityBoundsCS__LowerBoundAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1872:1: ( ( ruleLOWER ) )
            // InternalBase.g:1873:1: ( ruleLOWER )
            {
            // InternalBase.g:1873:1: ( ruleLOWER )
            // InternalBase.g:1874:1: ruleLOWER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleLOWER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__LowerBoundAssignment_0"


    // $ANTLR start "rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1"
    // InternalBase.g:1883:1: rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 : ( ruleUPPER ) ;
    public final void rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1887:1: ( ( ruleUPPER ) )
            // InternalBase.g:1888:1: ( ruleUPPER )
            {
            // InternalBase.g:1888:1: ( ruleUPPER )
            // InternalBase.g:1889:1: ruleUPPER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundUPPERParserRuleCall_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUPPER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundUPPERParserRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1"


    // $ANTLR start "rule__MultiplicityCS__IsNullFreeAssignment_2_1"
    // InternalBase.g:1898:1: rule__MultiplicityCS__IsNullFreeAssignment_2_1 : ( ( '|1' ) ) ;
    public final void rule__MultiplicityCS__IsNullFreeAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1902:1: ( ( ( '|1' ) ) )
            // InternalBase.g:1903:1: ( ( '|1' ) )
            {
            // InternalBase.g:1903:1: ( ( '|1' ) )
            // InternalBase.g:1904:1: ( '|1' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());
            }
            // InternalBase.g:1905:1: ( '|1' )
            // InternalBase.g:1906:1: '|1'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());
            }
            match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityCS__IsNullFreeAssignment_2_1"


    // $ANTLR start "rule__MultiplicityStringCS__StringBoundsAssignment"
    // InternalBase.g:1921:1: rule__MultiplicityStringCS__StringBoundsAssignment : ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) ) ;
    public final void rule__MultiplicityStringCS__StringBoundsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1925:1: ( ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) ) )
            // InternalBase.g:1926:1: ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) )
            {
            // InternalBase.g:1926:1: ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) )
            // InternalBase.g:1927:1: ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAlternatives_0());
            }
            // InternalBase.g:1928:1: ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 )
            // InternalBase.g:1928:2: rule__MultiplicityStringCS__StringBoundsAlternatives_0
            {
            pushFollow(FollowSets000.FOLLOW_2);
            rule__MultiplicityStringCS__StringBoundsAlternatives_0();

            state._fsp--;
            if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAlternatives_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicityStringCS__StringBoundsAssignment"


    // $ANTLR start "rule__PathNameCS__OwnedPathElementsAssignment_0"
    // InternalBase.g:1937:1: rule__PathNameCS__OwnedPathElementsAssignment_0 : ( ruleFirstPathElementCS ) ;
    public final void rule__PathNameCS__OwnedPathElementsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1941:1: ( ( ruleFirstPathElementCS ) )
            // InternalBase.g:1942:1: ( ruleFirstPathElementCS )
            {
            // InternalBase.g:1942:1: ( ruleFirstPathElementCS )
            // InternalBase.g:1943:1: ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleFirstPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__OwnedPathElementsAssignment_0"


    // $ANTLR start "rule__PathNameCS__OwnedPathElementsAssignment_1_1"
    // InternalBase.g:1952:1: rule__PathNameCS__OwnedPathElementsAssignment_1_1 : ( ruleNextPathElementCS ) ;
    public final void rule__PathNameCS__OwnedPathElementsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1956:1: ( ( ruleNextPathElementCS ) )
            // InternalBase.g:1957:1: ( ruleNextPathElementCS )
            {
            // InternalBase.g:1957:1: ( ruleNextPathElementCS )
            // InternalBase.g:1958:1: ruleNextPathElementCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleNextPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PathNameCS__OwnedPathElementsAssignment_1_1"


    // $ANTLR start "rule__FirstPathElementCS__ReferredElementAssignment"
    // InternalBase.g:1967:1: rule__FirstPathElementCS__ReferredElementAssignment : ( ( ruleUnrestrictedName ) ) ;
    public final void rule__FirstPathElementCS__ReferredElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1971:1: ( ( ( ruleUnrestrictedName ) ) )
            // InternalBase.g:1972:1: ( ( ruleUnrestrictedName ) )
            {
            // InternalBase.g:1972:1: ( ( ruleUnrestrictedName ) )
            // InternalBase.g:1973:1: ( ruleUnrestrictedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementCrossReference_0());
            }
            // InternalBase.g:1974:1: ( ruleUnrestrictedName )
            // InternalBase.g:1975:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementUnrestrictedNameParserRuleCall_0_1());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementUnrestrictedNameParserRuleCall_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementCrossReference_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FirstPathElementCS__ReferredElementAssignment"


    // $ANTLR start "rule__NextPathElementCS__ReferredElementAssignment"
    // InternalBase.g:1986:1: rule__NextPathElementCS__ReferredElementAssignment : ( ( ruleUnreservedName ) ) ;
    public final void rule__NextPathElementCS__ReferredElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:1990:1: ( ( ( ruleUnreservedName ) ) )
            // InternalBase.g:1991:1: ( ( ruleUnreservedName ) )
            {
            // InternalBase.g:1991:1: ( ( ruleUnreservedName ) )
            // InternalBase.g:1992:1: ( ruleUnreservedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementCrossReference_0());
            }
            // InternalBase.g:1993:1: ( ruleUnreservedName )
            // InternalBase.g:1994:1: ruleUnreservedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementUnreservedNameParserRuleCall_0_1());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnreservedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementUnreservedNameParserRuleCall_0_1());
            }

            }

            if ( state.backtracking==0 ) {
               after(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementCrossReference_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NextPathElementCS__ReferredElementAssignment"


    // $ANTLR start "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0"
    // InternalBase.g:2005:1: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 : ( ruleTemplateParameterSubstitutionCS ) ;
    public final void rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2009:1: ( ( ruleTemplateParameterSubstitutionCS ) )
            // InternalBase.g:2010:1: ( ruleTemplateParameterSubstitutionCS )
            {
            // InternalBase.g:2010:1: ( ruleTemplateParameterSubstitutionCS )
            // InternalBase.g:2011:1: ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0"


    // $ANTLR start "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1"
    // InternalBase.g:2020:1: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 : ( ruleTemplateParameterSubstitutionCS ) ;
    public final void rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2024:1: ( ( ruleTemplateParameterSubstitutionCS ) )
            // InternalBase.g:2025:1: ( ruleTemplateParameterSubstitutionCS )
            {
            // InternalBase.g:2025:1: ( ruleTemplateParameterSubstitutionCS )
            // InternalBase.g:2026:1: ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1"


    // $ANTLR start "rule__TemplateBindingCS__OwnedMultiplicityAssignment_2"
    // InternalBase.g:2035:1: rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 : ( ruleMultiplicityCS ) ;
    public final void rule__TemplateBindingCS__OwnedMultiplicityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2039:1: ( ( ruleMultiplicityCS ) )
            // InternalBase.g:2040:1: ( ruleMultiplicityCS )
            {
            // InternalBase.g:2040:1: ( ruleMultiplicityCS )
            // InternalBase.g:2041:1: ruleMultiplicityCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_2_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleMultiplicityCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_2_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateBindingCS__OwnedMultiplicityAssignment_2"


    // $ANTLR start "rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment"
    // InternalBase.g:2050:1: rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment : ( ruleTypeRefCS ) ;
    public final void rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2054:1: ( ( ruleTypeRefCS ) )
            // InternalBase.g:2055:1: ( ruleTypeRefCS )
            {
            // InternalBase.g:2055:1: ( ruleTypeRefCS )
            // InternalBase.g:2056:1: ruleTypeRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterTypeRefCSParserRuleCall_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterTypeRefCSParserRuleCall_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment"


    // $ANTLR start "rule__TypeParameterCS__NameAssignment_0"
    // InternalBase.g:2067:1: rule__TypeParameterCS__NameAssignment_0 : ( ruleUnrestrictedName ) ;
    public final void rule__TypeParameterCS__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2071:1: ( ( ruleUnrestrictedName ) )
            // InternalBase.g:2072:1: ( ruleUnrestrictedName )
            {
            // InternalBase.g:2072:1: ( ruleUnrestrictedName )
            // InternalBase.g:2073:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__NameAssignment_0"


    // $ANTLR start "rule__TypeParameterCS__OwnedExtendsAssignment_1_1"
    // InternalBase.g:2082:1: rule__TypeParameterCS__OwnedExtendsAssignment_1_1 : ( ruleTypedRefCS ) ;
    public final void rule__TypeParameterCS__OwnedExtendsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2086:1: ( ( ruleTypedRefCS ) )
            // InternalBase.g:2087:1: ( ruleTypedRefCS )
            {
            // InternalBase.g:2087:1: ( ruleTypedRefCS )
            // InternalBase.g:2088:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__OwnedExtendsAssignment_1_1"


    // $ANTLR start "rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1"
    // InternalBase.g:2097:1: rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 : ( ruleTypedRefCS ) ;
    public final void rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2101:1: ( ( ruleTypedRefCS ) )
            // InternalBase.g:2102:1: ( ruleTypedRefCS )
            {
            // InternalBase.g:2102:1: ( ruleTypedRefCS )
            // InternalBase.g:2103:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1"


    // $ANTLR start "rule__TypedTypeRefCS__OwnedPathNameAssignment_0"
    // InternalBase.g:2112:1: rule__TypedTypeRefCS__OwnedPathNameAssignment_0 : ( rulePathNameCS ) ;
    public final void rule__TypedTypeRefCS__OwnedPathNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2116:1: ( ( rulePathNameCS ) )
            // InternalBase.g:2117:1: ( rulePathNameCS )
            {
            // InternalBase.g:2117:1: ( rulePathNameCS )
            // InternalBase.g:2118:1: rulePathNameCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            rulePathNameCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__OwnedPathNameAssignment_0"


    // $ANTLR start "rule__TypedTypeRefCS__OwnedBindingAssignment_1_1"
    // InternalBase.g:2127:1: rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 : ( ruleTemplateBindingCS ) ;
    public final void rule__TypedTypeRefCS__OwnedBindingAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2131:1: ( ( ruleTemplateBindingCS ) )
            // InternalBase.g:2132:1: ( ruleTemplateBindingCS )
            {
            // InternalBase.g:2132:1: ( ruleTemplateBindingCS )
            // InternalBase.g:2133:1: ruleTemplateBindingCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTemplateBindingCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypedTypeRefCS__OwnedBindingAssignment_1_1"


    // $ANTLR start "rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1"
    // InternalBase.g:2142:1: rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 : ( ruleTypedRefCS ) ;
    public final void rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();

        try {
            // InternalBase.g:2146:1: ( ( ruleTypedRefCS ) )
            // InternalBase.g:2147:1: ( ruleTypedRefCS )
            {
            // InternalBase.g:2147:1: ( ruleTypedRefCS )
            // InternalBase.g:2148:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_2_1_0());
            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_2_1_0());
            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1"

    // Delegated rules





    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000040010L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000001C0010L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000040820000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000002400000L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000002000002L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100060L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000008000002L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000020000000L});
    }


}